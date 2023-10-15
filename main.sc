// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ****************************************SA Main Script***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
  																				 
SCRIPT_NAME MAIN  //NEW MAIN

// ***************************************SETUP*********************************************
// *****************************************************************************************

DO_FADE 0 FADE_OUT

SET_TOTAL_NUMBER_OF_MISSIONS 0
SET_PROGRESS_TOTAL 0
SET_MISSION_RESPECT_TOTAL 0
SET_MAX_WANTED_LEVEL 6				    							 
SET_DEATHARREST_STATE OFF
SET_TIME_OF_DAY 08 00 

VAR_INT BUTTON_ACCEPT BUTTON_CANCEL BUTTON_BET_UP BUTTON_BET_DOWN

IF IS_JAPANESE_VERSION
	BUTTON_ACCEPT = CIRCLE
	BUTTON_CANCEL = CROSS
	BUTTON_BET_UP = TRIANGLE
	BUTTON_BET_DOWN = SQUARE
ELSE
	BUTTON_ACCEPT = CROSS
	BUTTON_CANCEL = TRIANGLE
	BUTTON_BET_UP = SQUARE
	BUTTON_BET_DOWN = CIRCLE
ENDIF

// *****************************************CREATE PLAYER***********************************   

VAR_INT player1 scplayer

REQUEST_COLLISION 2488.5623 -1666.8645
LOAD_SCENE 2488.5623 -1666.8645 13.3757 //LA
//LOAD_SCENE 2369.60 -1265.10 23.88
//CREATE_PLAYER 0 1500.02 -1656.27 14.10 player1 //LA Downtown square
//CREATE_PLAYER 0 -2030.9 161.5 27.8 player1 //SF
//CREATE_PLAYER 0 2198.7 1679.2 10.4 player1 //Vegas


// *****************************************SET UP STATS************************************

SET_FLOAT_STAT ENERGY 800.0
SET_FLOAT_STAT BODY_MUSCLE 50.0
SET_FLOAT_STAT FAT 200.0 
SET_FLOAT_STAT DRIVING_SKILL 0.0
SET_INT_STAT CITIES_PASSED 0
SET_INT_STAT RESPECT 0

CREATE_PLAYER 0 2488.5623 -1666.8645 12.8757 player1 //LA hub

GOTO skip_create_player2
	CREATE_PLAYER 1 2488.5623 -1666.8645 12.8757 player2
skip_create_player2:

DISPLAY_TIMER_BARS FALSE
//LA Gym

VAR_FLOAT statbikestamina_ctr
VAR_FLOAT statbikelowmuscle_ctr
VAR_FLOAT statbike_temp
VAR_FLOAT stattreadstamina_ctr
VAR_FLOAT stattreadlowmuscle_ctr
VAR_FLOAT stattread_temp

// ***************************************SET UP GANG THREATS*******************************

//VAR_INT grove_threatlist //grove gang (players gang in la1)
//VAR_INT flat_threatlist	//flat gang
//VAR_INT nmex_threatlist	//northside mex
//VAR_INT smex_threatlist	//southside mex

//VAR_INT grove_decisions	grove_group_decisions
//VAR_INT flat_decisions
//VAR_INT smex_decisions
//VAR_INT nmex_decisions 
VAR_INT gf_game_timer Players_Group

//grove (player's gang for LA1 then enemy)

//GROVE STREET
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_GROVE PEDTYPE_GANG_FLAT 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_GROVE PEDTYPE_GANG_NMEX
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_GROVE PEDTYPE_GANG_SMEX 

//FLATS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_GANG_GROVE
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_FLAT PEDTYPE_GANG_NMEX
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_FLAT PEDTYPE_GANG_SMEX 


//NORTH MEXICANS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_NMEX PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_NMEX PEDTYPE_GANG_GROVE
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_NMEX PEDTYPE_GANG_FLAT
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_NMEX PEDTYPE_GANG_SMEX 

//SOUTH MEXICANS
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_SMEX PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_SMEX PEDTYPE_GANG_GROVE
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_SMEX PEDTYPE_GANG_FLAT
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_SMEX PEDTYPE_GANG_NMEX

//SAN FRAN
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_SFMEX PEDTYPE_GANG_VIET
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_SFMEX PEDTYPE_GANG_TRIAD
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_VIET PEDTYPE_GANG_SFMEX 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_GANG_TRIAD PEDTYPE_GANG_SFMEX 
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_TRIAD PEDTYPE_GANG_VIET
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_VIET PEDTYPE_GANG_TRIAD


GET_PLAYER_GROUP Player1 Players_Group
//SET_GROUP_SEPARATION_RANGE Players_Group 30.0

// *****************************************************************************************
GET_PLAYER_CHAR player1 scplayer 							   

SET_CAMERA_BEHIND_PLAYER
SET_CHAR_HEADING scplayer 262.0

//ADD_SCORE Player1 350 //TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

LOAD_AND_LAUNCH_MISSION initial.sc
wait 0
LOAD_AND_LAUNCH_MISSION initial2.sc

// *************************************CONSTANTS*******************************************
//--- GF CONSTANTS
CONST_INT COOCHIE	0
CONST_INT MICHELLE 	1
CONST_INT KYLIE		2
CONST_INT BARBARA	3
CONST_INT SUZIE		4
CONST_INT MILLIE	5

CONST_INT COOCHIE_NOT_AT_HOME	10
CONST_INT MICHELLE_NOT_AT_HOME 	11
CONST_INT KYLIE_NOT_AT_HOME		12
CONST_INT BARBARA_NOT_AT_HOME	13
CONST_INT SUZIE_NOT_AT_HOME		14
CONST_INT MILLIE_NOT_AT_HOME	15

CONST_INT MICHELLE_BONUS_ACTIVE	21
CONST_INT KYLIE_BONUS_ACTIVE	22
CONST_INT BARBARA_BONUS_ACTIVE	23
CONST_INT SUZIE_BONUS_ACTIVE	24

CONST_INT GF_CHEAT_MODE_ON		31

//--- DATE REPORT CONSTS
CONST_INT DATE_IN_PROGRESS 		1
CONST_INT DATE_WAS_SUCCESS 		2
CONST_INT PLAYER_AGREES_TO_SEX 	3
CONST_INT SEX_WAS_GOOD 			4
CONST_INT SEX_IN_PROGRESS 		5
CONST_INT GIRL_IS_BACK_AT_HOME 	6
CONST_INT EAT_OUT 				11
CONST_INT DRIVE 				12  
CONST_INT DANCE 				13  
CONST_INT KINKY_SEX				14
CONST_INT GIRL_DRIVE			15

CONST_INT MEETING_IN_PROGRESS 	20
CONST_INT MEET_TOMORROW			21

CONST_INT FASTDATE_ON			26
CONST_INT CHEAT_EAT_OUT			27
CONST_INT CHEAT_DRIVE			28
CONST_INT CHEAT_DANCE			29
CONST_INT CHEAT_KINKY_SEX		30
CONST_INT CHEAT_GIRL_DRIVE		31

//--- AGENT CONSTS
CONST_INT APPOINTMENT_ON_FOR_COOCHIE    0 
CONST_INT APPOINTMENT_ON_FOR_MICHELLE	1 
CONST_INT APPOINTMENT_ON_FOR_KYLIE 		2
CONST_INT APPOINTMENT_ON_FOR_BARBARA	3
CONST_INT APPOINTMENT_ON_FOR_SZUIE		4
CONST_INT APPOINTMENT_ON_FOR_MILLIE		5

CONST_INT MOBILE_CALL_ANSWERED			10
CONST_INT MOBILE_CALL_SCRIPT_RUNNING	11

CONST_INT MOBILE_INACTIVE				20
CONST_INT TIME_FOR_CALL					21
CONST_INT TIME_FOR_ANSWER				22
CONST_INT TIME_FOR_DATE					23
CONST_INT MOBILE_DUMPED					24

CONST_INT KEEP_THIS_IDX					25				

CONST_INT MISSION_CLEANUP_DONE			31

//--- LIKES ON DATE CONSTS
//	FOOD & DRINK 
CONST_INT LIKES_JUNK_FOOD 						1	 	     
CONST_INT LIKES_DINERS 							2	 		     
CONST_INT LIKES_BARS 							3	 
CONST_INT LIKES_DONUTS 							4 			 
CONST_INT LIKES_SWANK_PLACES 					5
//	DRIVING 
CONST_INT LIKES_STUNTS 							11	 		  
CONST_INT LIKES_TO_GO_FAST 						12	
CONST_INT LIKES_TO_CRUISE 						13	
CONST_INT LIKES_PARKING_ROMANTIC 				14	
CONST_INT LIKES_RICH_ZONES 						15		  
CONST_INT LIKES_GANG_ZONES 						16	 		  
CONST_INT LIKES_PARK_BEACH_ZONES 				17	
CONST_INT LIKES_DESERT_COUNTRY_ZONES 			18	
CONST_INT LIKES_SHOPPING_ZONES 					19		  
CONST_INT LIKES_ENTERTAINMENT_ZONES 			20
//	UNIQUE
CONST_INT LIKES_GANG_FIGHTS 					21	
CONST_INT LIKES_TO_DRIVE 						22		  
CONST_INT LIKES_KINKY_SEX 						23	
CONST_INT LIKES_TO_CAUSE_ACCIDENTS_KILL_PEDS 	24
 //	MISC.
CONST_INT SINGS_ALONG_RADIO 					26  
CONST_INT LIKES_SNOGGING_IN_PUBLIC 				27	
CONST_INT LIKES_SEX_IN_PUBLIC 					28				  
CONST_INT LIKES_TO_BE_HIT_BY_PLAYER 			29
CONST_INT LIKES_FLYING							30
CONST_INT LIKES_BOATING							31	

//--- LIKES PLAYER TRAITS CONSTS
//	PHYSIQUE 
CONST_INT FIT 					1	     
CONST_INT OBESE 				2		     
CONST_INT NORMAL 				3	
//	CAR 
CONST_INT CAR_NORMAL_POORFAMILY		11	
CONST_INT CAR_EXECUTIVE_RICHFAMILY 	12	
CONST_INT CAR_UNIQUE_CAR 			13	
CONST_INT CAR_MOD_CAR 				14	
CONST_INT CAR_MOTORBIKE 			15	
CONST_INT CAR_COPCAR 				16	  
CONST_INT CAR_AMBULANCE 			17	
CONST_INT CAR_CABBIE_TAXI 			18	

//--- DIARY OF BUSY HOURS
CONST_INT H_MIDNIGHT	0
CONST_INT H_2AM			2
CONST_INT H_4AM			4
CONST_INT H_6AM			6
CONST_INT H_8AM			8
CONST_INT H_10AM		10
CONST_INT H_NOON		12
CONST_INT H_2PM			14
CONST_INT H_4PM			16
CONST_INT H_6PM			18
CONST_INT H_8PM			20
CONST_INT H_10PM		22

CONST_INT D_SUN			23
CONST_INT D_MON			24
CONST_INT D_TUE			25
CONST_INT D_WED			26
CONST_INT D_THU			27
CONST_INT D_FRI			28
CONST_INT D_SAT			29

CONST_INT NEXT_FREE_DAY 31

//--- MOBILE PHONE PARAMETERS 
CONST_INT CALL_DATE 	1
CONST_INT CALL_DUMP		2

//--- MISSION SPECIFIC GF CONSTS
CONST_INT MILLIE_LIKES_PLAYER_REQUIRED_FOR_KEYCARD 35


// For wager UIs.

CONST_INT WAGER_WHITE  0
CONST_INT WAGER_GREEN  1
CONST_INT WAGER_BLUE   2


// *************************************VARIABLES*******************************************

//GENERAL
VAR_INT button_pressed controlmode mission_trigger_wait_time flag_cell_nation	game_timer
VAR_INT LStickX LStickY RStickX RStickY	been_in_a_bmx tucking_contact_blip launch_shit_for_debug_build
VAR_INT game_starts_from_scratch Return_cities_passed flag_dont_start_shooting_range
VAR_INT tag_percentage cj_vomits_for_menace	trigger_phonecall_failed 
VAR_INT recording emmets_shop_blip	emmets_gun swim_stamina_check_main
VAR_INT hours minutes LA_hub_activity keycard_aquired_from_millie player_is_completely_safe	player_is_completely_safe_for_mobile
VAR_INT weekday activate_mobile_phone played_scipted_airscript_cut
VAR_INT main_visible_area trigger_final_synd_mission
VAR_INT model_index	trigger_scrash2_mission returned_respect
VAR_INT car car_class oddjob_help_flag girlfried_help_flag stealth_help_flag
VAR_INT f1_which_missions_are_open_flag	voice_over_at_hub airstrip_contact_blip
VAR_INT stop_gargae_for_neil trigger_final_LA1_missions	trigger_final_LA2_missions trigger_ice_cold_mission
VAR_INT intro_bmx intro_bmx_blip trucking_help_flag	R3_player_car
VAR_INT cat_counter funeral_mission_finished showroom_contact_blip
VAR_INT add_all_ammu_blips

VAR_FLOAT distance player_x player_y player_z
VAR_FLOAT heading 
VAR_FLOAT x y z
VAR_FLOAT on_footX on_footY	on_footZ
VAR_FLOAT in_carX in_carY in_carZ

//HELP MESSAGE VARS
VAR_INT	bike_help drive_by_help
VAR_INT	print_first_help car_help_played

VAR_INT driving_test_passed pilot_test_passed


//ODDJOB VARS
VAR_INT flag_menace_buyfood courier_timer
VAR_INT flag_kickstart_passed_1stime

VAR_INT f1_the90_best_score f1_spinrightgo_best_score f1_spinleftgo_best_score f1_burnlapright_best_score f1_burnlapleft_best_score 
VAR_INT f1_popcontrol_best_score f1_cityslicking_best_score f1_whiprightterminate_best_score f1_whipleftterminate_best_score f1_alleyoop_best_score    
VAR_INT f1_wheelieweave_best_score f1_pittechnique_best_score f1_conecoilright_best_score f1_conecoilleft_best_score
VAR_INT f1_the180_best_score camera_secret_help
VAR_INT f1_the360_best_score 

VAR_FLOAT one_sixteenth one_thirtysecond one_sixtyfourth //stuck_x stuck_y stuck_z   
VAR_FLOAT coord_1_x coord_1_y coord_1_z coord_2_x coord_2_y coord_c1_x coord_c1_y coord_c1_z

VAR_INT wasted_help wanted_star_help  // flag names
VAR_INT show_race_selection gym_contact_blip
VAR_INT stat_read_skill_temp chat_help1	chat_help1_flag

show_race_selection = FALSE

VAR_INT busted_help 

CREATE_PICKUP INFO PICKUP_ONCE 2027.77 -1420.52 16.49 wasted_help1
CREATE_PICKUP INFO PICKUP_ONCE 1180.85 -1325.57 13.08 wasted_help2
CREATE_PICKUP INFO PICKUP_ONCE 1550.68 -1675.49 15.01 busted_help1

CREATE_PICKUP INFO PICKUP_ONCE 2431.17 -1668.75 13.04 chat_help1

// *************************************LOCATE BLOB VARIABLE STUFF****************************

VAR_INT blob_flag blob_flag_shop

// zero = false no blob displayed
// one = true blob is displayed					 
// before the loop set this flag tSo the way you want it displayed or nothing will happen

// *****************************************SPECIAL CHARACTERS********************************

// Cutscene stuff
VAR_INT cs_time  // timer for cutscenes

VAR_INT big_smoke
VAR_INT sweet
VAR_INT ryder
VAR_INT cesar
VAR_INT mc_strap				   

VAR_INT big_smoke_car
VAR_INT sweet_car
VAR_INT ryder_car

VAR_FLOAT sweet_carX sweet_carY sweet_carZ sweet_carH
VAR_FLOAT ryder_carX ryder_carY ryder_carZ ryder_carH

VAR_INT truth 
VAR_INT catalina


// ODD JOB VARIABLES**************************************************************************
VAR_INT	flag_player_on_ambulance_mission // Ambulance Missions
VAR_INT	flag_player_on_fire_mission // Fire Truck Missions
VAR_INT got_siren_help_before finaleB_played_first_time_round
VAR_INT	flag_bmx_trigger	flag_nrg500_trigger
VAR_INT	current_Language
VAR_INT player2 p2 gym_at_beach 
VAR_INT flag_hhiker_trigger flag_pimping_trigger
VAR_INT been_in_freight_before flag_player_on_freight_mission flag_player_on_pimp_mission// Freight Missions
VAR_INT ft_train_level race_debug
VAR_INT flag_player_on_burglary_mission  // burglary mission


VAR_FLOAT LA_ShootX LA_ShootY LA_ShootZ
			
LA_ShootX =	292.33 
LA_ShootY =	-35.39 
LA_ShootZ =	1000.50


race_debug = 0


// 4x4/carpark Missions
VAR_INT flag_player_on_menace_mission
VAR_INT opened_badlands_up opened_sanfran_up opened_vegas_up opened_desert_up	opened_la2_up
VAR_INT flag_mtbike_trigger

// Kickstart
VAR_INT	flag_kickstart_mission1_passed

// BMX variables
VAR_FLOAT bmx_1_x bmx_1_y bmx_1_z

// Taxi mission
VAR_INT taxi_passed, R3_mission_help, flag_taxiodd_mission_launched 
VAR_INT done_taxi_help

//MOBILE PHONE
VAR_INT timer_mobile_start timer_mobile_now timer_mobile_diff

//RC HELI MISSION
VAR_INT stat_stamina_temp

//Courier
VAR_INT flag_courier_mission_passed
VAR_INT flag_courier_trigger

//Airrace
VAR_INT flag_airrace_mission_passed

//COASTGUARD MISSION
VAR_INT been_in_a_coastguard_before

// coastguard
VAR_INT flag_player_on_coastguard_mission

//coke cash courier

VAR_INT courier_type
// Cash Courier
courier_type = 1

// Coke Courier
courier_type = 2

// lowrider minigame
VAR_INT lowrider_mission_flag
VAR_INT lowrider_minigame_unlocked
lowrider_mission_flag = 0
lowrider_minigame_unlocked = 0 // gets unlocked after sweet 6


// *******************************************school variables*****************************************************
//people
VAR_INT dummy_car1 dummy_car2
VAR_INT instructor_car	croupier_help
VAR_INT trafficcone_counter trafficcones[46]

//flags
VAR_INT mission_selection swimming_help
VAR_INT car_started
VAR_FLOAT instructor_car_speed instructor_car_heading
VAR_INT instructor_car_heading_int
VAR_INT car_timer lap_counter
VAR_FLOAT perfect_positionx perfect_positiony perfect_positionz perfect_heading players_distance_from_perfectpos
VAR_INT instructor_car_health dummy_car1_health dummy_car2_health total_instructor_car_health total_dummy_car1_health total_dummy_car2_health
VAR_INT heading_score position_score player_car_damage other_car_damage time_score overall_score total_car_damage
VAR_INT variablea variablec variabled
VAR_FLOAT variableb
VAR_FLOAT car_posx car_posy
VAR_FLOAT where_to_place_cones circle_start_x circle_start_y cone_coords_x cone_coords_y cone_coords_z cone_circle_radius 
VAR_INT lap1_secs lap1_millisecs overall_lap1 lap2_secs lap2_millisecs overall_lap2 lap3_secs lap3_millisecs overall_lap3 
VAR_INT lap4_secs lap4_millisecs overall_lap4 lap5_secs	lap5_millisecs overall_lap5	overall_secs overall_millisecs
VAR_FLOAT finish_leftx finish_lefty finish_rightx finish_righty
VAR_FLOAT start_coordsx start_coordsy
VAR_FLOAT area_check1ax area_check1ay area_check1bx area_check1by area_check2ax area_check2ay area_check2bx area_check2by
VAR_FLOAT area_check3ax	area_check3ay area_check3bx	area_check3by area_check4ax	area_check4ay area_check4bx	area_check4by
VAR_INT instructor_car_dead_flag finished_watching_scores
VAR_FLOAT noticeboard_x noticeboard_y noticeboard_z
VAR_FLOAT camera_positionx camera_positiony camera_positionz
VAR_INT camera_position_int

// *******************************************RACES variables******************************************************

// AIR RACE VARIABLES
VAR_INT	race1_best_position_airrace
VAR_INT	race1_best_time_airrace
VAR_INT	race2_best_position_airrace
VAR_INT	race2_best_time_airrace
VAR_INT	race3_best_position_airrace
VAR_INT	race3_best_time_airrace
VAR_INT	race4_best_position_airrace
VAR_INT	race4_best_time_airrace
VAR_INT	race5_best_position_airrace
VAR_INT	race5_best_time_airrace
VAR_INT	race6_best_position_airrace
VAR_INT	race6_best_time_airrace
VAR_INT done_race1_progress_airrace	done_race2_progress_airrace done_race3_progress_airrace
VAR_INT done_race4_progress_airrace done_race5_progress_airrace done_race6_progress_airrace
VAR_INT race_selection

race_selection = 0

// *******************************************GIRLS variables******************************************************
	VAR_INT iGFSelfRespect[6] iGFLikesPlayer[6]	iGFDesiredSexAppeal[6] iGFLikesPlayerTraits[6]	// GF STATS
	VAR_INT iGFLikesOnDate[6] iGFDiaryOfBusyHours[6] // MORE GF STATS
	VAR_INT iGFidx //GF index for the arrays above, obviously no greater than 5			
	VAR_INT iDateReport iAgentFlags iPhoneState	iCaller
	VAR_INT iGFBonusPickupID[4]	iGFHomeBlips[6]
	VAR_TEXT_LABEL txtCurrZone // Zone string used a bit everywhere
	VAR_INT iActiveGF // Public Bitfield to record active GFs. 

//STATS*****************************************************************************************

VAR_FLOAT fatstat_gym
                                    
//BEACH
//CREATE_PICKUP bribe PICKUP_ON_STREET_SLOW 393.9 -60.2 11.5 beach_bribe1  //Not far from Construction Site behind some houses

// ***************************************MISSION VARS**********************************************

// Global variables for missions

DECLARE_MISSION_FLAG flag_player_on_mission

VAR_INT mod_garage1
VAR_INT flag_player_on_mission spray_help
VAR_INT flag_player_on_oddjob
VAR_INT total_saved_peds
VAR_INT weapon_shop1_blip 
VAR_INT mod_garage_blip 
VAR_INT wuzi
VAR_INT task_status
VAR_INT bcesar2_mission_flag
VAR_INT bcesar3_mission_flag

VAR_FLOAT chickX chickY chickZ
VAR_FLOAT shooter1X shooter1Y shooter1Z	
VAR_FLOAT gravX gravY gravZ
VAR_FLOAT wheeloX wheeloY wheeloZ 
VAR_FLOAT spaceX spaceY spaceZ

// LA1************************************************* 
VAR_INT smoke_contact_blip sweet_contact_blip ryder_contact_blip strap_contact_blip 
VAR_INT crash_contact_blip intro_contact_blip cesar_contact_blip
VAR_INT intro_blip_icon sweet_blip_icon ryder_blip_icon
VAR_INT crash_blip_icon	smoke_blip_icon strap_blip_icon cesar_blip_icon
VAR_INT flag_intro_mission_counter spray_shop1 spray_shop2 spray_shop4
VAR_INT flag_sweet_mission_counter
VAR_INT	flag_ryder_mission_counter
VAR_INT flag_smoke_mission_counter
VAR_INT	flag_strap_mission_counter
VAR_INT	flag_crash_mission_counter
VAR_INT flag_cesar_mission_counter
VAR_INT flag_la1fin1_mission_counter

VAR_FLOAT introX introY introZ
VAR_FLOAT sweetX sweetY sweetZ
VAR_FLOAT ryderX ryderY ryderZ
VAR_FLOAT smokeX smokeY smokeZ
VAR_FLOAT strapX strapY strapZ
VAR_FLOAT strap2X strap2Y strap2Z
VAR_FLOAT crashX crashY crashZ
VAR_FLOAT cesarX cesarY cesarZ
 
//BADLANDS*********************************************
VAR_INT cat_contact_blip bcrash_contact_blip bcesar_contact_blip truth_contact_blip
VAR_INT cat_blip_icon truth_blip_icon
VAR_INT flag_cat_mission_counter flag_catcutscene_counter
VAR_INT flag_truth_mission_counter
VAR_INT flag_bcesar_mission_counter
VAR_INT flag_bcrash_mission_counter

VAR_FLOAT bcrashX bcrashY bcrashZ
VAR_FLOAT truthX truthY truthZ
VAR_FLOAT truth2X truth2Y truth2Z
VAR_FLOAT bcesarX bcesarY bcesarZ
VAR_FLOAT catX[6] catY[6] catZ[6]
	 		
//SAN FRAN*********************************************
VAR_INT wuzi_contact_blip steal_contact_blip synd_contact_blip
VAR_INT zero_contact_blip trace_contact_blip[4] scrash_contact_blip garage_contact_blip
VAR_INT wuzi_blip_icon synd_blip_icon steal_blip_icon garage_blip_icon
VAR_INT trace_blip_icon zero_blip_icon trace_contact_blip2
VAR_INT flag_garage_mission_counter
VAR_INT flag_zero_mission_counter
VAR_INT	flag_wuzi_mission_counter
VAR_INT	flag_steal_mission_counter
VAR_INT	flag_synd_mission_counter
VAR_INT flag_scrash_mission_counter

VAR_FLOAT garageX garageY garageZ
VAR_FLOAT zeroX zeroY zeroZ
VAR_FLOAT wuziX wuziY wuziZ
VAR_FLOAT stealX stealY stealZ
VAR_FLOAT syndX syndY syndZ
VAR_FLOAT traceX[4] traceY[4] traceZ[4]
VAR_FLOAT scrashX scrashY scrashZ		 	   

VAR_FLOAT testsX testsY testsZ

//VEGAS************************************************
VAR_INT desert_contact_blip desert2_contact_blip casino_contact_blip vcrash_contact_blip doc_contact_blip heist_contact_blip 
VAR_INT desert_blip_icon desert2_blip_icon casino_blip_icon doc_blip_icon heist_blip_icon pilot_blip_icon pilot_contact_blip	
VAR_INT flag_desert_mission_counter	Theheist_blip_icon Theheist_contact_blip
VAR_INT dschool_contact_blip
VAR_INT	flag_casino_mission_counter
VAR_INT	flag_vcrash_mission_counter
VAR_INT	flag_doc_mission_counter
VAR_INT	flag_heist_mission_counter

VAR_FLOAT desertX desertY desertZ
VAR_FLOAT desert2X desert2Y desert2Z
VAR_FLOAT casinoX casinoY casinoZ
VAR_FLOAT TheheistX TheheistY TheheistZ
VAR_FLOAT vcrashX vcrashY vcrashZ
VAR_FLOAT docX docY docZ
VAR_FLOAT heistX heistY heistZ

//LA2**************************************************
VAR_INT mansion_contact_blip grove_contact_blip
VAR_INT mansion_blip_icon grove_blip_icon	
VAR_INT flag_mansion_mission_counter
VAR_INT flag_grove_mission_counter quarry_contact_blip
VAR_INT flag_riot_mission_counter
VAR_INT boat_school_blip got_gimp_suit
VAR_INT bike_school_blip

VAR_FLOAT mansionX mansionY mansionZ
VAR_FLOAT groveX groveY groveZ

//ODDJOBS
VAR_FLOAT RouletteX RouletteY RouletteZ
VAR_FLOAT otbX otbY otbZ
VAR_FLOAT blackjackX blackjackY blackjackZ
VAR_FLOAT driving_schoolx driving_schooly driving_schoolz
VAR_FLOAT basketballx basketbally basketballz  
VAR_FLOAT limox limoy limoz
VAR_FLOAT directorx directory directorz
VAR_FLOAT valetx valety valetz
VAR_FLOAT banditx bandity banditz
VAR_FLOAT pilotx piloty pilotz // Pilot School Stuff

intro_blip_icon = RADAR_SPRITE_CJ		     						 
sweet_blip_icon = RADAR_SPRITE_SWEET
ryder_blip_icon = RADAR_SPRITE_RYDER
smoke_blip_icon = RADAR_SPRITE_BIG_SMOKE
strap_blip_icon = RADAR_SPRITE_OGLOC
crash_blip_icon = RADAR_SPRITE_CRASH1 //This 1s the same for scrash, bcrash and vcrash
cesar_blip_icon	= RADAR_SPRITE_CESAR  //Same for bcesar

cat_blip_icon = RADAR_SPRITE_CAT_PINK
truth_blip_icon = RADAR_SPRITE_TRUTH

wuzi_blip_icon = RADAR_SPRITE_WOOZIE
synd_blip_icon = RADAR_SPRITE_LOCO
garage_blip_icon = RADAR_SPRITE_TRIADS
steal_blip_icon	= RADAR_SPRITE_CESAR

trace_blip_icon = RADAR_SPRITE_RACE
zero_blip_icon = RADAR_SPRITE_ZERO

casino_blip_icon = RADAR_SPRITE_TRIADS_CASINO
heist_blip_icon	= RADAR_SPRITE_MAFIA
Theheist_blip_icon = RADAR_SPRITE_CASH

doc_blip_icon =	RADAR_SPRITE_MADDOG

desert_blip_icon = RADAR_SPRITE_TORENO_RANCH
desert2_blip_icon = RADAR_SPRITE_AIRYARD
mansion_blip_icon = RADAR_SPRITE_CJ
pilot_blip_icon	= RADAR_SPRITE_SCHOOL

grove_blip_icon	= RADAR_SPRITE_SWEET //GET NEW BLIP FOR THIS

GOTO dont_run_the_blips

	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2447.3643 -1974.4963 12.5469 RADAR_SPRITE_EMMETGUN emmets_shop_blip //Clothes
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2070.2703 -1791.0918 17.1484 RADAR_SPRITE_BARBERS barber_shop1 //Barbers
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2107.6243 -1807.5155 21.2114 RADAR_SPRITE_PIZZA food_shop1 //Pizza
 	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2243.8069 -1668.5667 20.0313 RADAR_SPRITE_TSHIRT clothes_shop1 //Clothes
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT introX introY introZ intro_blip_icon intro_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip	
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon ryder_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT smokeX smokeY smokeZ smoke_blip_icon smoke_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT strapX strapY strapZ strap_blip_icon strap_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT crashX crashY crashZ crash_blip_icon crash_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT cesarX cesarY cesarZ cesar_blip_icon cesar_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcrashX bcrashY bcrashZ crash_blip_icon bcrash_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT catX[0] catY[0] catZ[0] cat_blip_icon cat_contact_blip  //cat lodge
	//ADD_SPRITE_BLIP_FOR_CONTACT_POINT catX[5] catY[5] catZ[5] cat_blip_icon cat_contact_blip //Cat Truck Stop
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT truthX truthY truthZ truth_blip_icon truth_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcesarX bcesarY bcesarZ cesar_blip_icon bcesar_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ crash_blip_icon scrash_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT wuziX wuziY wuziZ wuzi_blip_icon wuzi_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT stealX stealY stealZ steal_blip_icon steal_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT syndX syndY syndZ crash_blip_icon synd_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT testsX testsY testsZ trace_blip_icon trace_contact_blip[0]
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT testsX testsY testsZ trace_blip_icon trace_contact_blip2
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ desert_blip_icon desert_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT desert2X desert2Y desert2Z desert2_blip_icon desert2_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip //TRIAD CASINO
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip //ROB MAFIA CASINO
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT docX docY docZ doc_blip_icon doc_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT vcrashX vcrashY vcrashZ crash_blip_icon vcrash_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon heist_contact_blip //MAFIA CASINO
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon mansion_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon grove_contact_blip	//REMOVE
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT pilotx piloty pilotz pilot_blip_icon pilot_contact_blip	
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT -2031.2 -118.0 34.3 RADAR_SPRITE_SCHOOL dschool_contact_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT boatsX boatsY boatsZ RADAR_SPRITE_SCHOOL boat_school_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT boatsX boatsY boatsZ RADAR_SPRITE_SCHOOL bike_school_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT 2228.0002 -1722.8113 12.5543 sweet_blip_icon gym_contact_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[5] save_pickupY[5] save_pickupZ[5] RADAR_SPRITE_SAVEHOUSE save_house_blip[5]
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT quarryX quarryY quarryZ RADAR_SPRITE_BULLDOZER quarry_contact_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2728.5, 212.2, 3.4 RADAR_SPRITE_MOD_GARAGE mod_garage_blips[0]
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2728.5, 212.2, 3.4 RADAR_SPRITE_MOD_GARAGE mod_garage_blips[1]

	ADD_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[0] propertyY[0] propertyZ[0] RADAR_SPRITE_PROPERTY_GREEN showroom_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[1] propertyY[1] propertyZ[1] RADAR_SPRITE_PROPERTY_GREEN zero_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[2] propertyY[2] propertyZ[2] RADAR_SPRITE_PROPERTY_GREEN airstrip_contact_blip

	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD truckX truckY truckZ RADAR_SPRITE_TRUCK tucking_contact_blip
	CREATE_PROTECTION_PICKUP 2508.359 -1676.538 12.579 10000 territory_cash territory_pickup
	CREATE_PICKUP KEYCARD PICKUP_ONCE 0.0 0.0 0.0 millies_keycard_pickup
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT 2035.7241 2727.9604 9.8281 Theheist_blip_icon Thekeycard_contact_blip
	CREATE_LOCKED_PROPERTY_PICKUP propertyX[0] propertyY[0] propertyZ[0] PROP_4 save_housepickup[0]	//SHOWROM
	CREATE_LOCKED_PROPERTY_PICKUP propertyX[1] propertyY[1] propertyZ[1] PROP_4 save_housepickup[1]	//ZEROS
	CREATE_LOCKED_PROPERTY_PICKUP propertyX[2] propertyY[2] propertyZ[2] PROP_4 save_housepickup[2]	//AIRSTRIP															 
	
	CREATE_OBJECT ad_flatdoor 1833.36 -1995.45 12.5 riot2_door[0]
	
	CREATE_OBJECT ad_flatdoor 1819.81 -1994.66 12.5 riot2_door[1]
	CREATE_OBJECT ad_flatdoor 1827.68 -1980.0 12.5 riot2_door[2]
	CREATE_OBJECT ad_flatdoor 1851.84 -1990.67 12.5 riot2_door[3]
	CREATE_OBJECT ad_flatdoor 1867.29 -1984.96 12.5 riot2_door[4]
	CREATE_OBJECT ad_flatdoor 1866.52 -1998.53 12.5 riot2_door[5]
	CREATE_OBJECT ad_flatdoor 1899.75 -1984.95 12.5 riot2_door[6]
	CREATE_OBJECT ad_flatdoor 1914.39 -1992.82 12.5 riot2_door[7]
	CREATE_OBJECT ad_flatdoor 1899.01 -1998.5 12.5 riot2_door[8]
	CREATE_OBJECT ad_flatdoor 1900.89 -2020.11 12.5 riot2_door[9]
	CREATE_OBJECT ad_flatdoor 1914.4 -2020.91 12.5 riot2_door[10]
	CREATE_OBJECT ad_flatdoor 1906.54 -2035.52 12.5 riot2_door[11]
	//CREATE_OBJECT ad_flatdoor 1851.86 -2020.14 12.5 riot2_door[12]
	//CREATE_OBJECT ad_flatdoor 1865.42 -2020.89 12.5 riot2_door[13]
	//CREATE_OBJECT ad_flatdoor 1857.55 -2035.52 12.5 riot2_door[14]

	WRITE_DEBUG AAAAAAAAA

dont_run_the_blips:



// ************************************HELP PICKUPS VARIABLES************************************

// WASTED HELP ICONS
VAR_INT wasted_help1
VAR_INT wasted_help2

//CREATE_PICKUP INFO PICKUP_ONCE 493.5 703.1 12.1 wasted_help1
//CREATE_PICKUP INFO PICKUP_ONCE -108.3 -974.4 10.4 wasted_help2

// BUSTED HELP ICONS
VAR_INT busted_help1

//CREATE_PICKUP INFO PICKUP_ONCE 508.9 506.8 11.3 busted_help1
//CREATE_PICKUP INFO PICKUP_ONCE 398.8 -469.7 11.7 busted_help2  

// ************************************PICKUPVAN************************************

//VAR_INT collectable1_van
//VAR_FLOAT collectable1_van_x collectable1_van_y collectable1_van_z collectable1_van_h 

// Zone Locate needed for each van/radio station collectable1_van_x = 2520.56 collectable1_van_y = -1461.98 collectable1_van_z = 23.79 collectable1_van_h = 270.37
//CREATE_CAR_GENERATOR collectable1_van_x collectable1_van_y collectable1_van_z collectable1_van_h NEWSVAN -1 -1 TRUE 0 0 0 10000 gen_car41 // Package Van LA

// *****************************************SWITCH ROADS OFF****************************************

SWITCH_ROADS_OFF 2500.0 -1677.0 20.0 2430.0 -1653.0 0.0	 //REMOVE (SPEAK TO JOHN)


// *****************************************SHOPS******************************************************

VAR_INT flag_store_day_food hyfra_gen1  // TEST STUFF TO COME OUT!!!!!!!!!!!!!
flag_store_day_food = 0

VAR_INT total_food_bought_per_day_shops
total_food_bought_per_day_shops = 0 

// Generic for all shops
VAR_INT skip_shopping_wait flag_changed_hair_intro2

// TO MAKE PEDS IN PIZZA SHOP PANIC
VAR_INT iSetPizzaPanic
iSetPizzaPanic = 0 // 0 = DEFAULT / 1 = Peds Cower
 
VAR_FLOAT keep_offX keep_offY keep_offZ
VAR_FLOAT tray_offX tray_offY tray_offZ keep_off_dirX keep_off_dirY 
VAR_FLOAT cam_offx cam_offy cam_offz // camera position
VAR_FLOAT ammuX ammuY ammuZ

VAR_FLOAT SHOPS_TEXT_SCALEX SHOPS_TEXT_SCALEY
VAR_INT TEXT_COL_SELECT_R TEXT_COL_SELECT_G TEXT_COL_SELECT_B TEXT_COL_SELECT_A
VAR_INT TEXT_COL_DULL_R TEXT_COL_DULL_G TEXT_COL_DULL_B TEXT_COL_DULL_A
VAR_INT TEXT_COL_NA_R TEXT_COL_NA_G TEXT_COL_NA_B TEXT_COL_NA_A

SHOPS_TEXT_SCALEX = 1.0
SHOPS_TEXT_SCALEY = 1.6

TEXT_COL_SELECT_R = 200
TEXT_COL_SELECT_G = 200
TEXT_COL_SELECT_B = 200
TEXT_COL_SELECT_A = 255

TEXT_COL_DULL_R = 100
TEXT_COL_DULL_G = 100
TEXT_COL_DULL_B = 100
TEXT_COL_DULL_A = 255

TEXT_COL_NA_R = 20
TEXT_COL_NA_G =	20
TEXT_COL_NA_B =	20
TEXT_COL_NA_A =	255

VAR_TEXT_LABEL shop_name

// REWARD CARS
CREATE_CAR_GENERATOR 2435.3018 -1671.8483 12.8007 90.0 rhino -1 -1 TRUE 0 0 0 10000 tank_gen1 // Farm car //HUB
SWITCH_CAR_GENERATOR tank_gen1 0

CREATE_CAR_GENERATOR 2527.2 -1677.1 19.2 90.0 hydra -1 -1 TRUE 0 0 0 10000 hyfra_gen1 // Farm car //HUB
SWITCH_CAR_GENERATOR hyfra_gen1 0



// **************************************** OBJECT SCRIPTS ********************************************

CONST_INT NONE_SC 0
CONST_INT DUAL_SC 1
CONST_INT SHTR_SC 2
CONST_INT GRAV_SC 3
CONST_INT OTB_SCRIPT 4
CONST_INT POOL_SCRIPT 5
CONST_INT LOWR_SCRIPT 6
CONST_INT ZERO5 7

VAR_INT load_and_launch_mission_if_poss

load_and_launch_mission_if_poss = -1

REGISTER_STREAMED_SCRIPT player_parachute.sc
REGISTER_STREAMED_SCRIPT parachute.sc
REGISTER_STREAMED_SCRIPT bcesar2.sc
REGISTER_STREAMED_SCRIPT bcesar3.sc
REGISTER_STREAMED_SCRIPT slot_machine.sc
REGISTER_STREAMED_SCRIPT roulette.sc
REGISTER_STREAMED_SCRIPT otb_script.sc
REGISTER_STREAMED_SCRIPT arcade.sc
REGISTER_STREAMED_SCRIPT vending_machine.sc
REGISTER_STREAMED_SCRIPT food_vendor.sc
REGISTER_STREAMED_SCRIPT gates_script.sc
REGISTER_STREAMED_SCRIPT gymbike.sc
REGISTER_STREAMED_SCRIPT gymbench.sc
REGISTER_STREAMED_SCRIPT gymtread.sc
REGISTER_STREAMED_SCRIPT gymdumb.sc
REGISTER_STREAMED_SCRIPT basketb.sc
REGISTER_STREAMED_SCRIPT vidpok.sc
REGISTER_STREAMED_SCRIPT blackj.sc
REGISTER_STREAMED_SCRIPT wheelo.sc
REGISTER_STREAMED_SCRIPT Dealer.sc
REGISTER_STREAMED_SCRIPT home_brains.sc
//REGISTER_STREAMED_SCRIPT pros_brains.sc
REGISTER_STREAMED_SCRIPT pool_script.sc
REGISTER_STREAMED_SCRIPT lowr_cont.sc
REGISTER_STREAMED_SCRIPT burg_brains.sc
REGISTER_STREAMED_SCRIPT GF_Meeting.sc
REGISTER_STREAMED_SCRIPT GF_Date.sc
REGISTER_STREAMED_SCRIPT GF_Sex.sc
REGISTER_STREAMED_SCRIPT Casino_ambience.sc
REGISTER_STREAMED_SCRIPT Bar_ambience.sc
REGISTER_STREAMED_SCRIPT FoodBrains.sc
REGISTER_STREAMED_SCRIPT OTB_ambience.sc
REGISTER_STREAMED_SCRIPT Strip_ambience.sc
REGISTER_STREAMED_SCRIPT planes.sc
REGISTER_STREAMED_SCRIPT trains.sc
REGISTER_STREAMED_SCRIPT Zero_ambience.sc
REGISTER_STREAMED_SCRIPT Dance.sc

//PEOPLE
REGISTER_STREAMED_SCRIPT ShopKeeper.sc
REGISTER_STREAMED_SCRIPT Customer_Panic.sc
REGISTER_STREAMED_SCRIPT Bar_Staff.sc
REGISTER_STREAMED_SCRIPT Bouncer.sc
REGISTER_STREAMED_SCRIPT OTB_Staff.sc

//ATTRACTOR BRAINS
REGISTER_STREAMED_SCRIPT PCHAIR.sc 
REGISTER_STREAMED_SCRIPT PCUSTOM.sc 
REGISTER_STREAMED_SCRIPT OTBWTCH.sc 
REGISTER_STREAMED_SCRIPT OTBSLP.sc 
REGISTER_STREAMED_SCRIPT OTBTILL.sc 
REGISTER_STREAMED_SCRIPT FBOOTHR.sc 
REGISTER_STREAMED_SCRIPT FBOOTHL.sc 
REGISTER_STREAMED_SCRIPT BARGUY.sc 
REGISTER_STREAMED_SCRIPT PEDROUL.sc 
REGISTER_STREAMED_SCRIPT PEDCARD.sc 
REGISTER_STREAMED_SCRIPT PEDSLOT.sc 
REGISTER_STREAMED_SCRIPT DANCER.sc
REGISTER_STREAMED_SCRIPT STRIPW.sc
REGISTER_STREAMED_SCRIPT STRIPM.sc


// SHOPS
REGISTER_STREAMED_SCRIPT BROWSE.sc
REGISTER_STREAMED_SCRIPT COPSIT.sc
REGISTER_STREAMED_SCRIPT COPLOOK.sc
REGISTER_STREAMED_SCRIPT TICKET.sc
REGISTER_STREAMED_SCRIPT SHOPPER.sc
REGISTER_STREAMED_SCRIPT ammu.sc
REGISTER_STREAMED_SCRIPT tattoo.sc
REGISTER_STREAMED_SCRIPT barber.sc
REGISTER_STREAMED_SCRIPT wardrobe.sc
REGISTER_STREAMED_SCRIPT clothes.sc
REGISTER_STREAMED_SCRIPT junkfud.sc
REGISTER_STREAMED_SCRIPT carmod1.sc
REGISTER_STREAMED_SCRIPT crane1.sc
REGISTER_STREAMED_SCRIPT crane2.sc
REGISTER_STREAMED_SCRIPT crane3.sc
REGISTER_STREAMED_SCRIPT carpark1.sc
REGISTER_STREAMED_SCRIPT impound.sc
REGISTER_STREAMED_SCRIPT valet.sc
REGISTER_STREAMED_SCRIPT PHOTO.sc
REGISTER_STREAMED_SCRIPT PRISONR.sc
REGISTER_STREAMED_SCRIPT camera.sc 
REGISTER_STREAMED_SCRIPT debt.sc
REGISTER_STREAMED_SCRIPT hotdog.sc

CONST_INT CASINO_OBJECT_BRAIN	1

//CHRIS R's OBJECT SCRIPTS vv
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT slot_machine.sc KB_BANDIT_U 100 6.0 CASINO_OBJECT_BRAIN
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT roulette.sc ROULETTE_TBL 60 50.0 CASINO_OBJECT_BRAIN
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT otb_script.sc OTB_MACHINE 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT arcade.sc CJ_COIN_OP_3 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT arcade.sc CJ_COIN_OP_2 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT arcade.sc CJ_COIN_OP_1 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT arcade.sc CJ_COIN_OP 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT arcade.sc SWANK_CONSOLE 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT arcade.sc SNESISH 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT arcade.sc LOW_CONSOLE 100 4.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vending_machine.sc VENDMACHFD 100 6.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vending_machine.sc VENDMACH 100 6.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vending_machine.sc VENDIN3 100 6.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vending_machine.sc CJ_SPRUNK1 100 6.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vending_machine.sc CJ_CANDYVENDOR 100 6.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vending_machine.sc CJ_EXT_CANDY 100 6.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vending_machine.sc CJ_EXT_SPRUNK 100 6.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT food_vendor.sc ICESCART_PROP 100 70.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT food_vendor.sc CHILLIDOGCART	100 70.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT food_vendor.sc NOODLECART_PROP 100 70.0 -1
VAR_INT open_gate_now_flag
open_gate_now_flag = 0
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT gates_script.sc GATE_AUTOL 100 80.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT gates_script.sc GATE_AUTOR 100 80.0 -1
//CHRIS R's OBJECT SCRIPTS ^^

//PAUL's OBJECT SCRIPTS vv
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT gymbike.sc gym_bike 100 20.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT gymbench.sc gym_bench1 100 20.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT gymtread.sc gym_treadmill 100 20.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT gymdumb.sc gym_mat1 100 20.0 -1
//PAULS's OBJECT SCRIPTS ^^

//NEILS's OBJECT SCRIPTS vv
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT basketb.sc BSKBALL_LAX 100 70.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT basketb.sc BSKBALLHUB_LAX01 100 70.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT basketb.sc VGSXREFBBALLNET 100 70.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT basketb.sc VGSXREFBBALLNET2 100 70.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT pool_script.sc K_POOLTABLESM 100 70.0 -1
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vidpok.sc NEIL_SLOT 100 4.0 CASINO_OBJECT_BRAIN
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT vidpok.sc CJ_SLOT_BANK 100 4.0 CASINO_OBJECT_BRAIN
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT blackj.sc BLCK_JACK 50 70.0 CASINO_OBJECT_BRAIN
ALLOCATE_STREAMED_SCRIPT_TO_OBJECT wheelo.sc WHEEL_O_FORTUNE 50 70.0 CASINO_OBJECT_BRAIN
//NEIL's OBJECT SCRIPTS ^^


// ADD IN SECTION TO CREATE THE TERRITORY PICKUP GENERATING CASH*************************************
VAR_INT territory_pickup
VAR_INT player_territory_owned
VAR_INT territory_cash
territory_cash = 1


// **********************************************BRAINS**********************************************

// ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED test_brain goon1 100 //TEST SCRIPT

REGISTER_SCRIPT_BRAIN_FOR_CODE_USE burg_brains.sc HOUSE
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE DANCER.sc DANCER
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE PCHAIR.sc PCHAIR
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE PCUSTOM.sc PCUSTOM
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE OTBWTCH.sc OTBWTCH
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE OTBSLP.sc OTBSLP
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE OTBTILL.sc OTBTILL
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE FBOOTHR.sc FBOOTHR
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE FBOOTHL.sc FBOOTHL
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE BARGUY.sc BARGUY
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE PEDROUL.sc PEDROUL
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE PEDCARD.sc PEDCARD
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE PEDSLOT.sc PEDSLOT
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE STRIPW.sc STRIPW
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE STRIPM.sc STRIPM
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE BROWSE.sc BROWSE
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE COPSIT.sc COPSIT
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE COPLOOK.sc COPLOOK
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE TICKET.sc TICKET
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE SHOPPER.sc SHOPPER
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE PHOTO.sc PHOTO
REGISTER_ATTRACTOR_SCRIPT_BRAIN_FOR_CODE_USE PRISONR.sc PRISONR

// ****************************************PED BRAINS********************************************
ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED Dealer.sc BMYDRUG 100
ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED Dealer.sc WMYDRUG 100
ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED Dealer.sc HMYDRUG 100
ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED Dealer.sc BIKDRUG 100

ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED hotdog.sc BMOCHIL 100

ATTACH_ANIMS_TO_MODEL BMYDRUG DEALER
ATTACH_ANIMS_TO_MODEL WMYDRUG DEALER
ATTACH_ANIMS_TO_MODEL HMYDRUG DEALER
ATTACH_ANIMS_TO_MODEL BIKDRUG DEALER

//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED home_brains.sc WMOTR1  20
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED home_brains.sc SWMOTR1 20
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED home_brains.sc BMOTR1  20
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED home_brains.sc SBMOTR2 20
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED home_brains.sc SWMOTR2 20
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED home_brains.sc SBMYTR3 20
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED home_brains.sc SWMOTR3 20

//LA
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc WFYPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc BFYPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc HFYPRO 30
//SAN FRAN
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc SWFOPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc SHFYPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc SBFYPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc SFYPRO 30

//VEGAS
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc VWFYPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc VHFYPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc VBFYPRO 30
//ALLOCATE_STREAMED_SCRIPT_TO_RANDOM_PED pros_brains.sc VBFYPR2 30

REQUEST_IPL CRACK  //REQUEST THE CRACK LAB

SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2166.86 -236.50 40.86 40.0 crackfact_SFS TRUE    // Set it up as undamaged. The model is switched in BP now in SetDamaged().

SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2185.49 -215.55 34.31 40.0 CF_ext_dem_SFS FALSE   // These commands no longer do anything but break the saves to remove...
SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2166.86 -236.50 40.86 40.0 LODcrackfact_SFS TRUE  // These commands no longer do anything but break the saves to remove... 
SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE -2185.49 -215.55 34.31 40.0 LODext_dem_SFS FALSE   // These commands no longer do anything but break the saves to remove...

// ****************************************START_NEW_SCRIPTS******************************************

//START_NEW_SCRIPT police_impound_script
START_NEW_SCRIPT Tplay_mission_loop
START_NEW_SCRIPT vehicle_oddjob_loop 
START_NEW_SCRIPT R3missions_loop
START_NEW_SCRIPT gym_loop
START_NEW_SCRIPT shoot_range_loop 
START_NEW_SCRIPT blood_loop
START_NEW_SCRIPT hot_loop
START_NEW_SCRIPT kick_loop
START_NEW_SCRIPT grove_save_loop //SAVE GAME
START_NEW_SCRIPT game_flow_loop
START_NEW_SCRIPT game_help_loop
START_NEW_SCRIPT collectibes_loop
START_NEW_SCRIPT crane_manager
START_NEW_SCRIPT buy_pro_loop
START_NEW_SCRIPT valet_loop
START_NEW_SCRIPT planes_loop
START_NEW_SCRIPT trains_loop
START_NEW_SCRIPT impound_loop
START_NEW_SCRIPT open_the_map
START_NEW_SCRIPT tri_loop

//LAUNCH_MISSION parachute.sc	//KB
LAUNCH_MISSION bbthrow.sc //NF
LAUNCH_MISSION bball_chal.sc //NF
LAUNCH_MISSION lowrider_game.sc	//NF
LAUNCH_MISSION audio_singleline.sc //NF
LAUNCH_MISSION beatdisplay.sc //NF
LAUNCH_MISSION impexp.sc //NF
LAUNCH_MISSION airports.sc //CC
LAUNCH_MISSION girls.sc	//CC
LAUNCH_MISSION Int_Manager.sc //CC
LAUNCH_MISSION audio_controller.sc //SL
LAUNCH_MISSION hj.sc //CR
LAUNCH_MISSION mobile.sc //CF


DO_FADE 0 FADE_OUT
DISPLAY_ZONE_NAMES FALSE
WAIT 0
WAIT 0

SET_MAX_WANTED_LEVEL 4	

IF IS_PLAYER_PLAYING player1

	FORCE_WEATHER_NOW WEATHER_SUNNY_LA	

	GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP player1 vest vest 0
	GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP player1 jeansdenim jeans 2
	GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP player1 sneakerbincblk sneaker 3
	GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP player1 player_face head 1
	flag_bought_from_binco = 1

	BUILD_PLAYER_MODEL player1
	STORE_CLOTHES_STATE

	//LAUNCH_MISSION debug.sc //FOR DEBUG BUILD!!!!!!
	//LAUNCH_MISSION designtools.sc //FOR  DEBUG BUILD!!!!!!!!
	SET_FADING_COLOUR 0 0  0  //FOR FINAL BUILD!!!!!!!!!!!!!!!!!
	DO_FADE 0 FADE_OUT
	game_starts_from_scratch = 0
	LOAD_AND_LAUNCH_MISSION intro.sc
	activate_mobile_phone = 1 //SET TO 0 TO SWITCH OFF MOBILE CALLS
	//SET_CLOSEST_ENTRY_EXIT_FLAG 2137.9055 1600.5658 10.0 ENTRYEXITS_FLAG_ENABLED FALSE //MAFIA CASINO
	IF IS_PLAYER_PLAYING player1
		SET_AREA_VISIBLE 0
		SET_PLAYER_CONTROL player1 on
	ENDIF

	RELEASE_WEATHER

	GOTO mission_start	  
ENDIF



{
//MAIN LOOP**************************************************************************************************

VAR_INT number_of_instances_of_streamed_script

number_of_instances_of_streamed_script = 0

mission_start:

WAIT 0

IF IS_PLAYER_PLAYING player1

	
	
	GET_AREA_VISIBLE main_visible_area
	
	GET_INT_STAT CITIES_PASSED Return_cities_passed

	GET_CURRENT_DAY_OF_WEEK	weekday

	GET_CURRENT_LANGUAGE current_Language

	GET_CITY_PLAYER_IS_IN Player1 im_players_city

    GET_GAME_TIMER game_timer_wil  
        
    IF flag_store_day_food = 1
       timer_wil = game_timer_wil - stored_shop_time 
        IF timer_wil >= 180000
            flag_store_day_food = 0
            total_food_bought_per_day_shops = 0
            timer_wil = 0
			flag_ate_too_much_food = 0
        ENDIF                                  
    ENDIF   

	// CAR MOD GARAGES
	IF IS_GARAGE_OPEN bodLAwN
	OR IS_GARAGE_OPEN modlast
	OR IS_GARAGE_OPEN mdsSFSe
	OR IS_GARAGE_OPEN mds1SFS
	OR IS_GARAGE_OPEN vEcmod

		GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT carmod1.sc number_of_instances_of_streamed_script
		
		IF number_of_instances_of_streamed_script = 0
			STREAM_SCRIPT carmod1.sc
					
			IF HAS_STREAMED_SCRIPT_LOADED carmod1.sc
				START_NEW_STREAMED_SCRIPT carmod1.sc
			ENDIF

		ENDIF

	ELSE
		MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED carmod1.sc
	ENDIF	

	// PARACHUTE
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PARACHUTE
		GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT player_parachute.sc number_of_instances_of_streamed_script
		
		IF number_of_instances_of_streamed_script = 0
			STREAM_SCRIPT player_parachute.sc
			
			IF HAS_STREAMED_SCRIPT_LOADED player_parachute.sc
				START_NEW_STREAMED_SCRIPT player_parachute.sc
			ENDIF
		ENDIF
	ELSE
		MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED player_parachute.sc
	ENDIF
	
    // CAMERA
    IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
    AND flag_player_on_mission = 0 
        GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT camera.sc number_of_instances_of_streamed_script
        
        IF number_of_instances_of_streamed_script = 0
            STREAM_SCRIPT camera.sc
                    
            IF HAS_STREAMED_SCRIPT_LOADED camera.sc
            	START_NEW_STREAMED_SCRIPT camera.sc
            ENDIF
        ENDIF
    ELSE
    	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED camera.sc
    ENDIF
	
	//LOAD AND LAUNCHED STREAMED SCRIPTS
	IF flag_player_on_mission = 0
		// --- HANDLES THE COKE COURIER SCRIPT 
		IF flag_Synd_mission_counter < 10
			IF cat_counter > 2
				IF courier_timer > 400

					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT bcesar2.sc number_of_instances_of_streamed_script
					IF number_of_instances_of_streamed_script = 0
						IF bcesar3_mission_flag = 0
							GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT bcesar3.sc number_of_instances_of_streamed_script
							IF number_of_instances_of_streamed_script = 0
								GET_CURRENT_DAY_OF_WEEK	weekday
								GET_TIME_OF_DAY hours minutes
								IF weekday = 4 // Wednesday
								OR weekday = 7 // Saturday
									IF hours >= 19
									OR hours < 7
										STREAM_SCRIPT bcesar3.sc
										IF HAS_STREAMED_SCRIPT_LOADED bcesar3.sc
											START_NEW_STREAMED_SCRIPT bcesar3.sc
											bcesar3_mission_flag = 1 // stops script getting started twice in one day.
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT bcesar3.sc number_of_instances_of_streamed_script
					IF number_of_instances_of_streamed_script = 0
						IF bcesar2_mission_flag = 0
							GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT bcesar2.sc number_of_instances_of_streamed_script
							IF number_of_instances_of_streamed_script = 0
								GET_CURRENT_DAY_OF_WEEK	weekday
								GET_TIME_OF_DAY hours minutes
								IF weekday = 2 // monday
								OR weekday = 6 // friday
									IF hours >= 5				
									AND hours < 17
										STREAM_SCRIPT bcesar2.sc
										IF HAS_STREAMED_SCRIPT_LOADED bcesar2.sc
											START_NEW_STREAMED_SCRIPT bcesar2.sc
											bcesar2_mission_flag = 1 // stops script getting started twice in one day.
										ENDIF
									ENDIF
								ENDIF
							ENDIF						
						ENDIF
					ENDIF
				ENDIF

				GET_CURRENT_DAY_OF_WEEK	weekday
				IF weekday = 1 // Sunday
				OR weekday = 3 // Tuesday
				OR weekday = 5 // Thursday
					bcesar2_mission_flag = 0
					bcesar3_mission_flag = 0
					courier_timer = 0
				ELSE
					courier_timer ++
				ENDIF

			ENDIF
		ENDIF

		// --- HANDLES THE LOWRIDER MINI GAME
		IF lowrider_minigame_unlocked = 1 
		AND flag_player_on_mission = 0
			IF lowrider_mission_flag = 0
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT lowr_cont.sc number_of_instances_of_streamed_script
				IF number_of_instances_of_streamed_script = 0
					IF IS_PLAYER_PLAYING player1
						IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1812.3690 -1929.9215 80.0 80.0 FALSE
							STREAM_SCRIPT lowr_cont.sc
							IF HAS_STREAMED_SCRIPT_LOADED lowr_cont.sc
								START_NEW_STREAMED_SCRIPT lowr_cont.sc
								lowrider_mission_flag = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF IS_PLAYER_PLAYING player1
					IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 1812.3690 -1929.9215 500.0 500.0 FALSE 
						lowrider_mission_flag = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

			SWITCH load_and_launch_mission_if_poss
	
				CASE NONE_SC
					flag_player_on_mission = 1
					LOAD_AND_LAUNCH_MISSION none.sc
				BREAK
					
				CASE DUAL_SC
					flag_player_on_mission = 1
					LOAD_AND_LAUNCH_MISSION dual.sc
				BREAK
					
				CASE SHTR_SC
					flag_player_on_mission = 1
					LOAD_AND_LAUNCH_MISSION shtr.sc
				BREAK
					
				CASE GRAV_SC
					flag_player_on_mission = 1
					LOAD_AND_LAUNCH_MISSION grav.sc
				BREAK
					
				CASE OTB_SCRIPT
					flag_player_on_mission = 1
					LOAD_AND_LAUNCH_MISSION otb.sc
				BREAK
					
				CASE POOL_SCRIPT
					flag_player_on_mission = 1
					LOAD_AND_LAUNCH_MISSION pool.sc
				BREAK
				
				CASE LOWR_SCRIPT
					flag_player_on_mission = 1
					LOAD_AND_LAUNCH_MISSION lowr.sc
				BREAK
				
				CASE ZERO5
					flag_player_on_mission = 1
					PRINT_BIG ( BEEFY ) 1000 2
					GOSUB mini_fade
					LOAD_AND_LAUNCH_MISSION zero5.sc
				BREAK
	
			ENDSWITCH

	ELSE
		IF bcesar2_mission_flag = 1
		OR bcesar3_mission_flag = 1
			courier_timer = 0
		ENDIF
		load_and_launch_mission_if_poss = -1
	ENDIF

	IF IS_PLAYER_PLAYING player1
		// CAR park vegas (emerald isle)
		IF IS_CHAR_IN_AREA_2D scplayer 2037.5359 2365.3726 2117.1765 2483.9216 0

			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT carpark1.sc number_of_instances_of_streamed_script
			
			IF number_of_instances_of_streamed_script = 0
				STREAM_SCRIPT carpark1.sc
						
				IF HAS_STREAMED_SCRIPT_LOADED carpark1.sc
					START_NEW_STREAMED_SCRIPT carpark1.sc
				ENDIF

			ENDIF

		ELSE
			MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED carpark1.sc
		ENDIF
				
		IF im_players_city = LEVEL_LOSANGELES
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 658.0068 -1866.3127 4.4537 15.0 15.0 15.0 FALSE
				IF gym_at_beach = 0
					IF gym_day > gym_final_day
					OR gym_month > gym_final_month
						gym_day_fitness = 0.0
					ENDIF
					gym_at_beach = 1
				ENDIF
			ELSE
				IF gym_at_beach = 1
					gym_at_beach = 0
				ENDIF
			ENDIF
		ENDIF
			
	ENDIF



ENDIF //IF IS_PLAYER_PLAYING player1

GOTO mission_start

}

// **********************************************************************************************************
// ********************************************LA MISSIONS***************************************************
// **********************************************************************************************************

// **********************************************************************************************************
// ****************************************** INTRO MISSIONS ************************************************

intro_mission_loop:

{

SCRIPT_NAME INT

intro_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_intro_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer introX introY introZ 1.0 1.0 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_intro_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( INTRO_1 ) 1000 2 //"INTRO mission 1" //CRAIGF //BMX Bandits
						GOSUB mother_script_cut
						CLEAR_HELP
						LOAD_AND_LAUNCH_MISSION intro1.sc  
					ENDIF
				ENDIF
			ENDIF
			IF LOCATE_CHAR_ON_FOOT_3D scplayer ryderX ryderY ryderZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_intro_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( INTRO_2 ) 1000 2 //"INTRO mission 2" //CRAIGF //Haircut, Gym + robbery
						GOSUB ryder_script_cut1
						LOAD_AND_LAUNCH_MISSION intro2.sc  
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO intro_loop_inner

}

// **********************************************************************************************************
// **********************************************SWEET MISSIONS**********************************************

sweet_mission_loop:

{

SCRIPT_NAME SWEET

sweet_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_sweet_mission_counter = 9
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0

			IF LOCATE_CHAR_ON_FOOT_3D scplayer sweetX sweetY sweetZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
				   	IF flag_sweet_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( SWEET_1 ) 1000 2 //"Sweet mission 1" //CRAIGF //Tagging
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION sweet1.sc 
					ENDIF
					IF flag_sweet_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( SWEET1B ) 1000 2 //"Sweet mission 1B" //CRAIGF //Clean
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION sweet1b.sc 
					ENDIF
					IF flag_sweet_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( SWEET_3 ) 1000 2 //"Sweet mission 2" //CRAIG F //Chicken Wings
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION sweet3.sc 
					ENDIF
					IF flag_sweet_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( SWEET_2 ) 1000 2 //"Sweet mission 3" //CRAIGF //Guns Guns Guns 
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION sweet2.sc 
					ENDIF
					IF flag_sweet_mission_counter = 4
						flag_player_on_mission = 1
						PRINT_BIG ( SWEET_4 ) 1000 2 //"Sweet mission 4" //CHRIS M //Getto Drive by
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION sweet4.sc 
					ENDIF
					IF flag_sweet_mission_counter = 5
						flag_player_on_mission = 1
						PRINT_BIG ( SWEET_5 ) 1000 2 //"Sweet mission 5" //KEV B //Rescue
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION sweet5.sc 
					ENDIF
					IF flag_sweet_mission_counter = 6
						flag_player_on_mission = 1
						PRINT_BIG ( SWEET_6 ) 1000 2 //"Sweet mission 6" //NEIL //Lowrider comp	part 1
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION sweet6.sc 
					ENDIF
					IF flag_sweet_mission_counter = 8 
						FIND_MAX_NUMBER_OF_GROUP_MEMBERS returned_respect
						IF returned_respect	> 1
							GET_TIME_OF_DAY hours minutes
							IF hours >= 9
							AND hours < 17
								CLEAR_PRINTS
								flag_player_on_mission = 1
								PRINT_BIG ( SWEET_7 ) 1000 2 //"Sweet mission 8" //STEVE T //Grave Misfortune
								GOSUB sweet_script_cut1
								LOAD_AND_LAUNCH_MISSION sweet7.sc 
							ELSE
								PRINT_NOW MTIME3 1000 1 //Come back between 9:00 17:00
							ENDIF
						ELSE
							PRINT_NOW MOREREP 1000 1 //You need more respect
							IF respect_help_played = 0
								PRINT_HELP HELP101
								respect_help_played	= 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 1365.2507 -1280.1200 12.5469 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_sweet_mission_counter = 7
					AND flag_mob_la1[4] = 1
						flag_player_on_mission = 1
						PRINT_BIG ( CRASH_2 ) 1000 2 //"Sweet mission 7" //WILLIE  //Doberman
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION crash4.sc 
					ENDIF
				ENDIF
			ENDIF
			
        ENDIF
	ENDIF
	
GOTO sweet_mission_loop_inner

}

// **********************************************************************************************************
// *******************************************LA 1 CRASH MISSIONS********************************************


crash_mission_loop:

{

SCRIPT_NAME CRASH

crash_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_crash_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer crashX crashY crashZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_crash_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( CRASH_1 ) 1000 2 //"Crash mission 1" //KEITH //Burning Desire
						GOSUB crash_script_cut1
						LOAD_AND_LAUNCH_MISSION crash1.sc 
					ENDIF
					IF flag_crash_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( CRASH_3 ) 1000 2 //"Crash mission 3" //IMRAN //Docks shootout
						GOSUB crash_script_cut1
						LOAD_AND_LAUNCH_MISSION crash3.sc 
					ENDIF
				ENDIF
			ENDIF
			
		ENDIF
	ENDIF
	
GOTO crash_mission_loop_inner

}


// ****************************************************************************************************
// *******************************************RYDER MISSIONS ******************************************


ryder_mission_loop:

{

SCRIPT_NAME RYDER

ryder_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_ryder_mission_counter = 3
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer ryderX ryderY ryderZ 1.6 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_ryder_mission_counter = 0
						GET_TIME_OF_DAY hours minutes
						IF hours >= 12
						AND hours < 20
							CLEAR_PRINTS
							flag_player_on_mission = 1
							PRINT_BIG ( RYDER_1 ) 1000 2 //"Ryder mission 1" //DAVE //Burglary
							GOSUB ryder_script_cut1
							LOAD_AND_LAUNCH_MISSION ryder1.sc 
						ELSE
							PRINT_NOW MTIME6 1000 1 //Come back between 20:00 6:00
						ENDIF
					ENDIF
					IF flag_ryder_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( RYDER_3 ) 1000 2 //"Ryder mission 3" //PAUL  //Ammo Train Truck
						GOSUB ryder_script_cut1
						LOAD_AND_LAUNCH_MISSION ryder3.sc 
					ENDIF
					IF flag_ryder_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( RYDER_2 ) 1000 2 //"Ryder mission 2" //NEIL //National Guard
						GOSUB ryder_script_cut1
						LOAD_AND_LAUNCH_MISSION ryder2.sc 
					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO ryder_mission_loop_inner

}

// ***********************************************************************************************************
// *********************************************SMOKE MISSIONS************************************************

smoke_mission_loop:

{

SCRIPT_NAME SMOKE

smoke_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_smoke_mission_counter = 4
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer smokeX smokeY smokeZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_smoke_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( SMOKE_1 ) 1000 2 //"Smoke mission 1" //ANDY //Payback
						GOSUB smoke_script_cut1
						LOAD_AND_LAUNCH_MISSION smoke1.sc 
					ENDIF
					IF flag_smoke_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( SMOKE_2 ) 1000 2 //"Smoke mission 2" //JUDITH //Northen Mexican chase
						GOSUB smoke_script_cut1
						LOAD_AND_LAUNCH_MISSION smoke2.sc
					ENDIF
					IF flag_smoke_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( SMOKE_3 ) 1000 2 //"Smoke mission 3" //IMRAN //Train chase
						GOSUB smoke_script_cut1
						LOAD_AND_LAUNCH_MISSION smoke3.sc 
					ENDIF
					IF flag_smoke_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( SMOKE_4 ) 1000 2 //"Smoke mission 4" //IMRAN //Dodgy dealings
						GOSUB smoke_script_cut1
						LOAD_AND_LAUNCH_MISSION smoke4.sc 
					ENDIF
				ENDIF
			ENDIF
		ENDIF


	ENDIF
	
GOTO smoke_mission_loop_inner

}

// ***********************************************************************************************************
// *******************************************MC SRAP MISSIONS************************************************

strap_mission_loop:

{

SCRIPT_NAME STRAP

strap_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_strap_mission_counter = 5
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer strapX strapY strapZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_strap_mission_counter = 0
						GET_TIME_OF_DAY hours minutes
						IF hours >= 22
						OR hours < 6
							CLEAR_PRINTS
							flag_player_on_mission = 1
							PRINT_BIG ( STRAP_1 ) 1000 2 //"Strap mission 1" //JUDITH //GTS
							GOSUB strap_script_cut1
							LOAD_AND_LAUNCH_MISSION strap1.sc
						ELSE
							PRINT_NOW MTIME5 1000 1 //Come back between 22:00 6:00	
						ENDIF 
					ENDIF
					IF flag_strap_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( STRAP_2 ) 1000 2 //"Strap mission 2" //IMRAN //Steal from DocG
						GOSUB strap_script_cut1
						LOAD_AND_LAUNCH_MISSION strap2.sc 
					ENDIF
					IF flag_strap_mission_counter = 2
						GET_TIME_OF_DAY hours minutes
						IF hours >= 12
						OR hours < 5
							CLEAR_PRINTS
							flag_player_on_mission = 1
							PRINT_BIG ( STRAP_3 ) 1000 2 //"Strap mission 3" //ANDY //Kill G's manager
							GOSUB strap_script_cut1
							LOAD_AND_LAUNCH_MISSION strap3.sc  
						ELSE
							PRINT_NOW MTIME2 1000 1 //Come back between 12:00 6:00
						ENDIF
					ENDIF
					IF flag_strap_mission_counter = 3
						flag_player_on_mission = 1
						GOSUB strap_script_cut1
						PRINT_BIG ( STRAP_4 ) 1000 2 //"Strap mission 4" //ANDY //House party
						LOAD_AND_LAUNCH_MISSION strap4.sc
					ENDIF
				ENDIF
			ENDIF
		
			IF LOCATE_CHAR_ON_FOOT_3D scplayer strap2X strap2Y strap2Z 1.2 1.2 2.0 FALSE //housepX housepY housepZ
				IF CAN_PLAYER_START_MISSION player1
					IF flag_strap_mission_counter = 4
						GET_TIME_OF_DAY hours minutes
						IF hours >= 20
						OR hours < 6
							CLEAR_PRINTS
							flag_player_on_mission = 1
							PRINT_BIG ( STRAP_4 ) 1000 2 //"Strap mission 4" //ANDY //House party
							GOSUB strap_script_cut2
							LOAD_AND_LAUNCH_MISSION strap4.sc  
						ELSE
							PRINT_NOW MTIME1 1000 1 //Come back between 20:00 6:00
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO strap_mission_loop_inner


}


// **********************************************************************************************************
// ********************************************** CESAR MISSIONS ********************************************


cesar_mission_loop:

{

SCRIPT_NAME CESAR

cesar_mission_loop_inner:

WAIT 0

	IF flag_cesar_mission_counter = 1
		TERMINATE_THIS_SCRIPT
	ENDIF

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
            IF flag_cesar_mission_counter = 0
				IF cs1_race_is_go = 1
	                race_selection = CESAR_RACE
	                show_race_selection = FALSE
	                LOAD_AND_LAUNCH_MISSION racetour.sc 
	                cs1_race_is_go = 2 
                ENDIF      
            ENDIF
		ENDIF

		IF flag_player_on_mission = 0
			IF flag_cesar_mission_counter = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer cesarX cesarY cesarZ 4.0 4.0 3.0 TRUE
					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer cs1_player_car
						IF IS_CAR_LOW_RIDER cs1_player_car
							IF CAN_PLAYER_START_MISSION player1
								flag_player_on_mission = 1
								PRINT_BIG ( CESAR_1 ) 1000 2 //"Cesar mission 1" //CHRIS M //Race
								GOSUB cesar_script_cut1
								LOAD_AND_LAUNCH_MISSION cesar1.sc		
							ENDIF
						ELSE
							PRINT_NOW CES_CK1 1000 1 //grab yourself a lowrider
						ENDIF
					ELSE
						PRINT_NOW CES_CK1 1000 1 //grab yourself a car
					ENDIF
				ENDIF
			ENDIF
			/*
			IF flag_mob_la1[3] = 1
				IF flag_cesar_mission_counter = 1
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer cesarX cesarY cesarZ 4.0 4.0 3.0 TRUE
						IF IS_CHAR_IN_ANY_CAR scplayer
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer cs2_player_car
							GET_VEHICLE_CLASS cs2_player_car cs2_player_car_model
				            IF cs2_player_car_model = NORMAL_CAR
				            OR cs2_player_car_model = POOR_FAMILY_CAR
				            OR cs2_player_car_model = RICH_FAMILY_CAR
				            OR cs2_player_car_model = EXECUTIVE_CAR
								IF CAN_PLAYER_START_MISSION player1
									flag_player_on_mission = 1
									GOSUB cesar_script_cut1
									PRINT_BIG ( CESAR_2 ) 1000 2 //"Cesar mission 2" //KEV B //Impound
									LOAD_AND_LAUNCH_MISSION cesar2.sc			
								ENDIF
							ELSE
								PRINT_NOW CES_CK2 1000 1 //not a car
							ENDIF
						ELSE
							PRINT_NOW CES_CK3 1000 1 //grab yourself a car
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			*/
		ENDIF
	ENDIF

GOTO cesar_mission_loop_inner

}


// **********************************************************************************************************
// ************************************************ LA1 FINALE **********************************************

	
la1fin1_mission_loop:

{

SCRIPT_NAME LA1FIN

la1fin1_loop_inner:

WAIT mission_trigger_wait_time


	IF flag_la1fin1_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer sweetX sweetY sweetZ 1.0 1.0 2.0 FALSE	
				IF flag_sweet_mission_counter = 9
					IF flag_smoke_mission_counter = 4
						IF flag_strap_mission_counter = 5
							IF flag_ryder_mission_counter = 3
								IF flag_crash_mission_counter = 2
								 	IF flag_cesar_mission_counter = 1	
										IF CAN_PLAYER_START_MISSION player1
											IF flag_la1fin1_mission_counter = 0
												flag_player_on_mission = 1
												PRINT_BIG ( LA1FIN1 ) 1000 2 //"LA final mission 1" //IMRAN //Motel deal
												GOSUB sweet_script_cut1
												LOAD_AND_LAUNCH_MISSION la1fin1.sc 
											ENDIF
										
											IF flag_la1fin1_mission_counter = 1
												flag_player_on_mission = 1
												PRINT_BIG ( LA1FIN2 ) 1000 2 //"LA final mission 2" //KEV B //Killers cutlass
												GOSUB sweet_script_cut1
												LOAD_AND_LAUNCH_MISSION la1fin2.sc
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
	ENDIF
	
GOTO la1fin1_loop_inner

}

// **********************************************************************************************************
// ************************************************COUNTRY MISSIONS******************************************
// ********************************************************************************************************** 


// **********************************************************************************************************
// ********************************************BADLANDS CRASH MISSIONS***************************************

bcrash_mission_loop:

{

SCRIPT_NAME BCRASH 

bcrash_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_bcrash_mission_counter = 1
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer bcrashX bcrashY bcrashZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_bcrash_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( BCRASH1 ) 1000 2 //"crash mission 1" //CHRIS M //Witness protection
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION bcrash1.sc  
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO bcrash_mission_loop_inner

}

// **********************************************************************************************************
// *******************************************CATALINA MISSIONS**********************************************



cat_mission_loop:
{

VAR_INT	flag_cat_mission1_passed flag_cat_mission2_passed flag_cat_mission3_passed flag_cat_mission4_passed
VAR_INT load_and_launch_catalina_mission flag_trigger_trailor_cut
load_and_launch_catalina_mission = -1

SCRIPT_NAME CAT

cat_mission_loop_inner:
	
	WAIT 0

	IF IS_PLAYER_PLAYING player1

		IF flag_player_on_mission = 0
			IF CAN_PLAYER_START_MISSION player1
				IF cat_counter = 0
					IF LOCATE_CHAR_ON_FOOT_3D scplayer catX[5] catY[5] catZ[5] 1.2 1.2 2.0 FALSE //TRUCK STOP
						flag_player_on_mission = 1
						GOSUB print_cat_text
						GOSUB cat_a_script_cut
						LOAD_AND_LAUNCH_MISSION catalina.sc
					ENDIF
				ELSE
					IF flag_trailor_cutscene = 1
						IF LOCATE_CHAR_ON_FOOT_3D scplayer catX[0] catY[0] catZ[0] 1.2 1.2 2.0 FALSE //LODGE
							flag_player_on_mission = 1
							GOSUB print_cat_text
							GOSUB cat_b_script_cut
							LOAD_AND_LAUNCH_MISSION catalina.sc
						ENDIF
					ENDIF
				ENDIF
				SWITCH load_and_launch_catalina_mission
				
					CASE 0
						flag_player_on_mission = 1
						LOAD_AND_LAUNCH_MISSION cat1.sc	//ROb liquor
						GOTO cat_mission_loop_inner
					BREAK
						
					CASE 1
						flag_player_on_mission = 1
						LOAD_AND_LAUNCH_MISSION cat2.sc	//Rob bank
						GOTO cat_mission_loop_inner
					BREAK
						
					CASE 2
						flag_player_on_mission = 1
						LOAD_AND_LAUNCH_MISSION cat3.sc	//Rob gas
						GOTO cat_mission_loop_inner
					BREAK
						
					CASE 3
						flag_player_on_mission = 1
						LOAD_AND_LAUNCH_MISSION cat4.sc	//Rob OTB
						GOTO cat_mission_loop_inner
					BREAK
						
				ENDSWITCH
			ENDIF
		ELSE
			load_and_launch_catalina_mission = -1
		ENDIF

		IF flag_trigger_trailor_cut = 0
			IF cat_counter = 1
				REMOVE_BLIP cat_contact_blip
				flag_trigger_trailor_cut = 1
			ENDIF
		ENDIF

		IF flag_trigger_trailor_cut = 1
			IF flag_truth_mission_counter = 1	
				IF LOCATE_CHAR_ON_FOOT_3D scplayer bcrashX bcrashY bcrashZ 1.2 1.2 2.0 FALSE
					IF CAN_PLAYER_START_MISSION player1
						flag_player_on_mission = 1
						PRINT_BIG (TRAILER) 1000 2
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION cat_cuts.sc
						flag_trigger_trailor_cut = 2  
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF cat_counter > 3
			REMOVE_BLIP cat_contact_blip
			TERMINATE_THIS_SCRIPT	
		ENDIF

	ENDIF

GOTO cat_mission_loop_inner

}

print_cat_text:
	IF cat_counter = 0
		PRINT_BIG CATCUT1 1000 2 //"Catalina" //CHRIS R
	ENDIF
	IF cat_counter = 1
		PRINT_BIG CATCUT2 1000 2 //"Catalina" //CHRIS R
	ENDIF
	IF cat_counter = 2
		PRINT_BIG CATCUT3 1000 2 //"Catalina" //CHRIS R
	ENDIF
	IF cat_counter = 3
		PRINT_BIG CATCUT4 1000 2 //"Catalina" //CHRIS R
	ENDIF
RETURN

cat_a_script_cut:

	IF IS_PLAYER_PLAYING player1	
		flag_player_on_mission = 1
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION 664.9277 -479.6112 16.1668 0.0 0.0 0.0 //LODGE
		POINT_CAMERA_AT_POINT 665.8948 -479.5685 16.4175 JUMP_CUT
		CLEAR_AREA 681.8004 -474.1063 15.5363 3.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 681.8004 -474.1063 15.5363 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN

cat_b_script_cut:

	IF IS_PLAYER_PLAYING player1	
		flag_player_on_mission = 1
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION 856.9968 -27.9584 63.7393  0.0 0.0 0.0 //LODGE
		POINT_CAMERA_AT_POINT 857.9943 -28.0286 63.7295 JUMP_CUT
		CLEAR_AREA 870.1503 -25.3395 62.9589 3.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 870.1503 -25.3395 62.9589 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN



// ************************************************************************************************************
// *******************************************TRUTH MISSIONS***************************************************


truth_mission_loop:

{

SCRIPT_NAME TRU

truth_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_truth_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF CAN_PLAYER_START_MISSION player1
				IF flag_truth_mission_counter = 0
					IF LOCATE_CHAR_ON_FOOT_3D scplayer truth2X truth2Y truth2Z 1.2 1.2 2.0 FALSE
						flag_player_on_mission = 1
						PRINT_BIG ( TRUTH_1 ) 1000 2 //"truth mission 1" //IMRAN //Body Harvest
						GOSUB motel_script_cut1
					    LOAD_AND_LAUNCH_MISSION truth1.sc 
					ENDIF
				ENDIF
				IF flag_truth_mission_counter = 1
				AND flag_bcesar_mission_counter > 9
					IF LOCATE_CHAR_ON_FOOT_3D scplayer truthX truthY truthZ 1.2 1.2 2.0 FALSE
						flag_player_on_mission = 1
						PRINT_BIG ( TRUTH_2 ) 1000 2 //"truth mission 2" //ANDY //Full head of green
						GOSUB truthfarm_script_cut1
						LOAD_AND_LAUNCH_MISSION truth2.sc 
					ENDIF				
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO truth_mission_loop_inner

}


// ************************************************************************************************************
// ****************************************** BADLANDS CESAR MISSIONS *****************************************

bcesar_mission_loop:


{

SCRIPT_NAME BCESAR

bcesar_mission_loop_inner:

WAIT 0

	IF flag_bcesar_mission_counter = 10
		SET_INT_STAT PASSED_BCESAR4 1
		TERMINATE_THIS_SCRIPT
	ENDIF

 	IF IS_PLAYER_PLAYING player1
	    IF flag_player_on_mission = 0
            IF flag_bcesar_mission_counter = 2 //triggers fail cutscene to clear race cars.
				IF start_the_bcesar_race = 0
            		LOAD_AND_LAUNCH_MISSION bcesar4.sc
				ENDIF
            ENDIF
            IF flag_bcesar_mission_counter = 0
            //OR flag_bcesar_mission_counter = 1
			//OR flag_bcesar_mission_counter = 2
				IF start_the_bcesar_race = 0
	            	IF LOCATE_CHAR_ANY_MEANS_3D scplayer bcesarX bcesarY bcesarZ 4.0 4.0 4.0 TRUE
						IF IS_CHAR_IN_ANY_CAR scplayer
	               			IF CAN_PLAYER_START_MISSION player1
								GET_CAR_CHAR_IS_USING scplayer car
								GET_VEHICLE_CLASS car car_class
								IF car_class = NORMAL_CAR
								OR car_class = POOR_FAMILY_CAR
								OR car_class = RICH_FAMILY_CAR
								OR car_class = EXECUTIVE_CAR
								OR car_class = WORKER_CAR
								OR car_class = TAXI_CAR
			                        flag_bcesar_mission_counter = 0                                                                        
			                        flag_player_on_mission = 1
			                        PRINT_BIG ( BCESAR4 ) 1000 2 //"bcesar mission 4" //KEV B //Badlands Race1                                                               
			                        GOSUB mini_fade
									//IF cat_counter < 4
			                        //	GOSUB setup_catalina_mission
									//ENDIF
			                        LOAD_AND_LAUNCH_MISSION bcesar4.sc                                                                    
								ELSE
									PRINT_NOW BC4_7 1000 1
			                    ENDIF
		                	ENDIF
						ELSE
							PRINT_NOW BC4_2 1000 1
						ENDIF    							
					ENDIF
				ENDIF
			ENDIF

			IF flag_bcesar_mission_counter = 3
                flag_bcesar_mission_counter = 4                                                                        
                flag_player_on_mission = 1
                GOSUB mini_fade
                LOAD_AND_LAUNCH_MISSION bcesar4.sc                                                                    
            ENDIF
			
			IF flag_stage_of_bcesar_race = 1
			AND cat_counter < 4	
				REMOVE_BLIP cat_contact_blip
				ADD_SPRITE_BLIP_FOR_CONTACT_POINT catX[0] catY[0] catZ[0] cat_blip_icon cat_contact_blip //Cats lodge
				flag_stage_of_bcesar_race = 0	
			ENDIF

			IF cat_counter > 3
				IF start_the_bcesar_race = 0
					IF flag_bcesar_mission_counter = 5
				    OR flag_bcesar_mission_counter = 6
					OR flag_bcesar_mission_counter = 7

						IF LOCATE_CHAR_ANY_MEANS_3D scplayer -513.9356 -188.3140 77.4599 1.2 1.2 2.0 FALSE //Sort this out with KEV
				        	IF CAN_PLAYER_START_MISSION player1
								flag_bcesar_mission_counter = 5
		  			            flag_player_on_mission = 1
				                PRINT_BIG ( BCES4_2 ) 1000 2 //"bcesar mission 4" //KEV B //Badlands Race2
		  			            GOSUB mini_fade
				                LOAD_AND_LAUNCH_MISSION bcesar4.sc 
				            ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF flag_bcesar_mission_counter = 8
	                flag_bcesar_mission_counter = 9                                                                        
	                flag_player_on_mission = 1
	                GOSUB mini_fade
	                LOAD_AND_LAUNCH_MISSION bcesar4.sc                                                                    
	            ENDIF
			ENDIF

			IF cat_counter = 4
			AND flag_bcesar_mission_counter > 4
				REMOVE_BLIP bcesar_contact_blip
				ADD_SPRITE_BLIP_FOR_CONTACT_POINT -513.9356 -188.3140 77.4599 cesar_blip_icon bcesar_contact_blip
				cat_counter = 5
			ENDIF
		
			IF start_the_bcesar_race = 1
	            IF flag_bcesar_mission_counter = 1
	                flag_bcesar_mission_counter = 2
	                race_selection = BADLAND_RACE1
	                show_race_selection = FALSE
	                LOAD_AND_LAUNCH_MISSION racetour.sc
	                start_the_bcesar_race = 0        
	            ENDIF

	            IF flag_bcesar_mission_counter = 6
	                flag_bcesar_mission_counter = 7
	                race_selection = BADLAND_RACE2
	                show_race_selection = FALSE
	                SET_INT_STAT STARTED_CRASH1 1
	                LOAD_AND_LAUNCH_MISSION racetour.sc
	                start_the_bcesar_race = 0        
	            ENDIF
		    ENDIF
		ENDIF
    ENDIF

GOTO bcesar_mission_loop_inner


}

// **********************************************************************************************************
// ********************************************SAN FRAN MISSIONS*********************************************
// **********************************************************************************************************


// **********************************************************************************************************
// *********************************************GARAGE MISSIONS**********************************************


garage_mission_loop:

{

SCRIPT_NAME GARAGE 

garage_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_garage_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer garageX garageY garageZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					
					IF flag_garage_mission_counter = 0
					
						flag_player_on_mission = 1
						PRINT_BIG ( GAR_1 ) 1000 2 //"Garage mission 1" //KEV W //Welcome to San Fran
						GOSUB garage_script_cut1
						LOAD_AND_LAUNCH_MISSION garage1.sc  
					ENDIF
					
					IF flag_scrash_mission_counter = 1
						IF flag_garage_mission_counter = 1
							flag_player_on_mission = 1
							PRINT_BIG ( GAR_2 ) 1000 2 //"Garage mission 2" //NEIL //Demolition
							GOSUB garage_script_cut1
							LOAD_AND_LAUNCH_MISSION garage2.sc  
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO garage_mission_loop_inner

}


// **********************************************************************************************************
// ****************************************SAN FRAN CRASH MISSIONS*******************************************

scrash_mission_loop:

{

SCRIPT_NAME SCRASH 

scrash_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_scrash_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer scrashX scrashY scrashZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_scrash_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( SCRA_1 ) 1000 2 //"Scrash mission 1" //KEV B //Plant The Drugs  
						GOSUB garage_script_cut1
						LOAD_AND_LAUNCH_MISSION scrash1.sc  
					ENDIF
					IF flag_scrash_mission_counter = 1
					AND flag_Synd_mission_counter = 6
						flag_player_on_mission = 1
						PRINT_BIG ( SCRA_2 ) 1000 2 //"Scrash mission 2" //Keith //Follow the ped 
						GOSUB garage_script_cut1
						LOAD_AND_LAUNCH_MISSION scrash2.sc  
					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO scrash_mission_loop_inner

}


// ***********************************************************************************************************
// ********************************************WUZI MISSIONS**************************************************

wuzi_mission_loop:

{

SCRIPT_NAME WUZI

wuzi_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_wuzi_mission_counter = 5
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer wuziX wuziY wuziZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_wuzi_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( WUZI_1 ) 1000 2 //"Wuzi mission 1" //KEV B	//Meet the People //Mountain Cloud Boy
						GOSUB wuzi_script_cut1
						LOAD_AND_LAUNCH_MISSION wuzi1.sc
					ENDIF
					IF flag_wuzi_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( FAR_4 ) 1000 2 //"farlie mission 4" PAUL D //Airport Pickup 
						GOSUB wuzi_script_cut1
						LOAD_AND_LAUNCH_MISSION farlie4.sc
					ENDIF
					IF flag_wuzi_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( FAR_5 ) 1000 2 //"farlie mission 5" JUDITH //Cross Country Decoy 
						GOSUB wuzi_script_cut1
						LOAD_AND_LAUNCH_MISSION farlie5.sc
					ENDIF
					IF flag_wuzi_mission_counter = 3
						GET_INT_STAT UNDERWATER_BREATH_STAMINA swim_stamina_check_main
						IF swim_stamina_check_main > 50
							GET_TIME_OF_DAY hours minutes
							IF hours >= 20
							OR hours < 6
								CLEAR_PRINTS
								flag_player_on_mission = 1
								PRINT_BIG ( WUZI_2 ) 1000 2 //"Wuzi mission 2" //SIMON //Swimming with the Sharks 
								GOSUB wuzi_script_cut1
								LOAD_AND_LAUNCH_MISSION wuzi2.sc
							ELSE
								PRINT_NOW MTIME1 1000 1 //Come back between 20:00 6:00
							ENDIF
						ELSE
							// run alternate cutscene no matter what the time of day is
							CLEAR_PRINTS
							flag_player_on_mission = 1
							PRINT_BIG ( WUZI_2 ) 1000 2 //"Wuzi mission 2" //SIMON //Swimming with the Sharks 
							GOSUB wuzi_script_cut1
							LOAD_AND_LAUNCH_MISSION wuzi2.sc
							//PRINT_NOW WZI2_60 1000 1
						ENDIF
					ENDIF
					IF flag_wuzi_mission_counter = 4
						flag_player_on_mission = 1
						PRINT_BIG ( WUZI_4 ) 1000 2 //"Wuzi mission 4" //ANDY //Storm Freighter
						GOSUB wuzi_script_cut1
						LOAD_AND_LAUNCH_MISSION wuzi4.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO wuzi_mission_loop_inner

}

// ****************************************************************************************************************
// ********************************************SYNDICATE MISSIONS**************************************************

Synd_mission_loop:

{

SCRIPT_NAME SYND

Synd_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_Synd_mission_counter = 10
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer garageX garageY garageZ 1.2 1.2 2.0 FALSE //garage
				IF CAN_PLAYER_START_MISSION player1
					IF flag_synd_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( SYND_1 ) 1000 2 //"Syndicate mission 1" //CHRIS M //Recon
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION Syn1.sc
					ENDIF
					IF flag_Synd_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( SYND_2 ) 1000 2 //"Syndicate mission 2"	//NEIL //Blonde Ambition PART1
						GOSUB garage_script_cut1
						LOAD_AND_LAUNCH_MISSION Syn2.sc
					ENDIF	  
					IF flag_Synd_mission_counter = 5
						//GOSUB garage_script_cut1
						flag_player_on_mission = 1
						PRINT_BIG ( SYND_3 ) 1000 2 //"Syndicate mission 3"	//JUDITH //Outrider
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION Syn3.sc
					ENDIF
					IF flag_Synd_mission_counter = 6
					AND flag_scrash_mission_counter = 2
						GET_TIME_OF_DAY hours minutes
						IF hours >= 20
						OR hours < 6
							flag_player_on_mission = 1
							CLEAR_PRINTS
							PRINT_BIG ( SYND_4 ) 1000 2 //"Syndicate mission 4"	//PAUL //Call to arms
							GOSUB garage_script_cut1
							LOAD_AND_LAUNCH_MISSION Syn4.sc
						ELSE
							PRINT_NOW MTIME1 1000 1 //Come back between 20:00 6:00
						ENDIF
					ENDIF
					IF flag_Synd_mission_counter = 8
						flag_player_on_mission = 1
						PRINT_BIG ( SYND_6 ) 1000 2 //"Syndicate mission 6"	//Steve T //Toreno's Plane 
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION Syn6.sc
					ENDIF
					IF flag_Synd_mission_counter = 9
					AND flag_wuzi_mission_counter = 5
						flag_player_on_mission = 1
						PRINT_BIG ( SYND_7 ) 1000 2 //"Syndicate mission 7"	//Paul D//Los Cabras Crack Lab  
						GOSUB garage_script_cut1
						LOAD_AND_LAUNCH_MISSION Syn7.sc
					ENDIF
				ENDIF
			ENDIF

			IF IS_PLAYER_PLAYING player1
				IF flag_Synd_mission_counter = 7
					IF LOCATE_CHAR_ON_FOOT_3D scplayer -1717.05 1280.91 6.23 1.2 1.2 2.5 FALSE //Pier69
						IF CAN_PLAYER_START_MISSION player1
							flag_player_on_mission = 1
							PRINT_BIG ( SYND_5 ) 1000 2 //"Syndicate mission 5"	//ANDY //Pier69 
							GOSUB mini_fade
							LOAD_AND_LAUNCH_MISSION Syn5.sc
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF IS_PLAYER_PLAYING player1
				IF LOCATE_CHAR_ON_FOOT_3D scplayer SyndX SyndY SyndZ 1.2 1.2 2.5 FALSE	//Pleasure domes
					IF CAN_PLAYER_START_MISSION player1
						IF flag_Synd_mission_counter = 2
							flag_player_on_mission = 1
							PRINT_BIG ( SYND_2 ) 1000 2 //"Syndicate mission 2"	//NEIL //Blonde Ambition PART2
							GOSUB pleasure_domes_script_cut1
							LOAD_AND_LAUNCH_MISSION Syn2.sc
						ENDIF
						IF flag_Synd_mission_counter = 3
							flag_player_on_mission = 1
							PRINT_BIG ( FAR_2 ) 1000 2 //"farlie mission 2" JUDITH //Bike Bust Up 
							GOSUB pleasure_domes_script_cut1
							LOAD_AND_LAUNCH_MISSION farlie2.sc
						ENDIF
						IF flag_Synd_mission_counter = 4
							flag_player_on_mission = 1
							PRINT_BIG ( FAR_3 ) 1000 2 //"farlie mission 3" KEV W //Hostage
							GOSUB pleasure_domes_script_cut1
							LOAD_AND_LAUNCH_MISSION farlie3.sc
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			
		ENDIF
	ENDIF
	
GOTO Synd_mission_loop_inner


}

// ***********************************************************************************************************
// ********************************************STEAL MISSIONS*************************************************

steal_mission_loop:

{

SCRIPT_NAME STEAL

steal_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_steal_mission_counter = 4
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer stealX stealY stealZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_steal_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( STEAL_1 ) 1000 2 //"steal mission 1" //CHRIS M //Follow
						GOSUB steal_script_cut1
						LOAD_AND_LAUNCH_MISSION steal1.sc
					ENDIF
					IF flag_steal_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( STEAL_2 ) 1000 2 //"steal mission 2" //ANDY	//Steal from Car Showroom 
						GOSUB steal_script_cut1
						LOAD_AND_LAUNCH_MISSION steal2.sc
					ENDIF
					IF flag_steal_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( STEAL_4 ) 1000 2 //"steal mission 4" //NEIL	//Car Crane 
						GOSUB steal_script_cut1
						LOAD_AND_LAUNCH_MISSION steal4.sc
					ENDIF
					IF flag_steal_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( STEAL_5 ) 1000 2 //"steal mission 5" //ANDY	//Stinger Trap 
						GOSUB steal_script_cut1
						LOAD_AND_LAUNCH_MISSION steal5.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF
	
GOTO steal_mission_loop_inner

}

// ***********************************************************************************************************
// ********************************************TOURNAMENT RACE MISSIONS***************************************

trace_mission_loop:

{

SCRIPT_NAME TRACE

trace_mission_loop_inner:

WAIT 0
            
    IF IS_PLAYER_PLAYING player1 
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer testsX testsY testsZ 20.0 20.0 10.0 FALSE
				GET_AREA_VISIBLE main_visible_area
				IF NOT main_visible_area = 0
					IF f1_scripted_cut = 0
						IF LOCATE_CHAR_ON_FOOT_3D scplayer -2031.4 -116.5 1034.1 1.0 1.0 1.5 TRUE  
							IF CAN_PLAYER_START_MISSION player1
								IF flag_garage_mission_counter > 0
									flag_player_on_mission = 1
									SET_FADING_COLOUR 0 0 0
									PRINT_BIG ( FAR_1 ) 1000 2 //"farlie mission 1" ANDY //The Tests 
									SET_PLAYER_CONTROL player1 OFF
									GOSUB mini_fade
									LOAD_AND_LAUNCH_MISSION farlie1.sc
								ENDIF
							ENDIF
						ENDIF
					ELSE
						IF LOCATE_CHAR_ON_FOOT_3D scplayer testsX testsY testsZ 1.0 1.0 1.5 TRUE  
							IF CAN_PLAYER_START_MISSION player1
								IF flag_garage_mission_counter > 0
									flag_player_on_mission = 1
									SET_FADING_COLOUR 0 0 0
									PRINT_BIG ( FAR_1 ) 1000 2 //"farlie mission 1" ANDY //The Tests 
									SET_PLAYER_CONTROL player1 OFF
									GOSUB mini_fade
									LOAD_AND_LAUNCH_MISSION farlie1.sc
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF IS_PLAYER_PLAYING player1
				IF CAN_PLAYER_START_MISSION player1
					IF flag_mob_sanfran[3] = 1 
						IF LOCATE_CHAR_ON_FOOT_3D scplayer traceX[0] traceY[0] traceZ[0] 1.2 1.2 2.0 FALSE
							flag_player_on_mission = 1
							SET_PLAYER_CONTROL player1 OFF
							PRINT_BIG TRACE_1 1000 2 //"trace mission 1" //CHRIS R //Race Tournament
							show_race_selection = TRUE
							menu_mode = LA_RACES 
							LOAD_AND_LAUNCH_MISSION racetour.sc
						ENDIF
						IF LOCATE_CHAR_ON_FOOT_3D scplayer traceX[1] traceY[1] traceZ[1] 1.2 1.2 2.0 FALSE
							flag_player_on_mission = 1
							SET_PLAYER_CONTROL player1 OFF
							PRINT_BIG TRACE_1 1000 2 //"trace mission 1" //CHRIS R //Race Tournament
							show_race_selection = TRUE 
							menu_mode = SF_RACES 
							LOAD_AND_LAUNCH_MISSION racetour.sc
						ENDIF
						IF LOCATE_CHAR_ON_FOOT_3D scplayer traceX[2] traceY[2] traceZ[2] 1.2 1.2 2.0 FALSE
							flag_player_on_mission = 1
							SET_PLAYER_CONTROL player1 OFF
							PRINT_BIG TRACE_1 1000 2 //"trace mission 1" //CHRIS R //Race Tournament
							show_race_selection = TRUE 
							menu_mode = LV_RACES 
							LOAD_AND_LAUNCH_MISSION racetour.sc
						ENDIF
						IF LOCATE_CHAR_ON_FOOT_3D scplayer traceX[3] traceY[3] traceZ[3] 1.2 1.2 2.0 FALSE
							flag_player_on_mission = 1
							SET_PLAYER_CONTROL player1 OFF
							PRINT_BIG TRACE_1 1000 2 //"trace mission 1" //CHRIS R //Race Tournament
							show_race_selection = TRUE 
							menu_mode = AIR_RACES 
							LOAD_AND_LAUNCH_MISSION racetour.sc
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
    ENDIF
            
GOTO trace_mission_loop_inner
                                                                                                   
}

		

// ***********************************************************************************************************
// ****************************************ZERO RC MISSIONS***************************************************

zero_mission_loop:

{

SCRIPT_NAME zero

zero_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_zero_mission_counter = 3
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer zeroX zeroY zeroZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_zero_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( ZERO_1 ) 1000 2 //"zero mission 1" //KEV W //Scramble
						GOSUB zero_script_cut1
						LOAD_AND_LAUNCH_MISSION zero1.sc
					ENDIF
					IF flag_zero_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( zero_2 ) 1000 2 //"zero mission 2" //KEV W //Rolling Thunder 
						GOSUB zero_script_cut1
						LOAD_AND_LAUNCH_MISSION zero2.sc
					ENDIF
					IF flag_zero_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( ZERO_4 ) 1000 2 //"zero mission 4" //NEIL //Return Fire
						GOSUB zero_script_cut1
						LOAD_AND_LAUNCH_MISSION zero4.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO zero_mission_loop_inner

}

	

// **********************************************************************************************************
// ********************************************DESERT MISSIONS***********************************************
// **********************************************************************************************************


// **********************************************************************************************************
// *********************************************DESERT MISSIONS**********************************************

desert_mission_loop:

{

SCRIPT_NAME DESERT 

desert_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_desert_mission_counter = 9
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer desertX desertY desertZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_desert_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( DESERT1 ) 1000 2 //"desert mission 1" //DAVE B //Monster Mash
						GOSUB ranch_script_cut1
						LOAD_AND_LAUNCH_MISSION desert1.sc  
					ENDIF
					IF flag_mob_sanfran[5] = 1
						IF flag_desert_mission_counter = 1
							flag_player_on_mission = 1
							PRINT_BIG ( DESERT2 ) 1000 2 //"desert mission 2" //ANDY //Jump linerunner //HighJack
							GOSUB ranch_script_cut1
							LOAD_AND_LAUNCH_MISSION desert2.sc  
						ENDIF
					ENDIF
					IF flag_mob_sanfran[7] = 1
						IF flag_desert_mission_counter = 2
							flag_player_on_mission = 1
							PRINT_BIG ( DESERT3 ) 1000 2 //"desert mission 3" //NEIL //Contraband
							GOSUB ranch_script_cut1
							LOAD_AND_LAUNCH_MISSION desert3.sc  
						ENDIF
					ENDIF
					IF flag_desert_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( DESERT4 ) 1000 2 //"desert mission 4" //CRAIG //Verdant Meadows
						GOSUB ranch_script_cut1
						LOAD_AND_LAUNCH_MISSION desert4.sc  
					ENDIF
				ENDIF
			ENDIF

			IF LOCATE_CHAR_ON_FOOT_3D scplayer desert2X desert2Y desert2Z 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF pilot_test_passed = 1
						IF flag_desert_mission_counter = 5
							flag_player_on_mission = 1
							PRINT_BIG ( DESERT6 ) 1000 2 //"desert mission 6" //Keith //N.O.E. 
							GOSUB airstrip_script_cut2
							LOAD_AND_LAUNCH_MISSION desert6.sc  
						ENDIF
					ENDIF
					IF flag_desert_mission_counter = 6
						flag_player_on_mission = 1
						PRINT_BIG ( DESERT9 ) 1000 2 //"desert mission 9" //KEV B //C3 Shootout 
						GOSUB airstrip_script_cut2
						LOAD_AND_LAUNCH_MISSION desert9.sc  
					ENDIF
					IF flag_desert_mission_counter = 7
						GET_TIME_OF_DAY hours minutes
						IF hours >= 20
						OR hours < 6
							CLEAR_PRINTS
							flag_player_on_mission = 1
							PRINT_BIG ( DESERT8 ) 1000 2 //"desert mission 8" //IMRAN //Steal Jetpack  
							GOSUB airstrip_script_cut2
							LOAD_AND_LAUNCH_MISSION desert8.sc
						ELSE
							PRINT_NOW MTIME1 1000 1 //Come back between 20:00 6:00
						ENDIF  
					ENDIF
					IF flag_desert_mission_counter = 8
					AND flag_mob_vegas[11] = 1
						flag_player_on_mission = 1
						PRINT_BIG ( DESER10 ) 1000 2 //"desert mission 10" //CHRIS R //Train Heist
						GOSUB airstrip_script_cut2
						LOAD_AND_LAUNCH_MISSION desert10.sc  
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO desert_mission_loop_inner

}

// ************************************************************************************************************ 
// ************************************* Pilot School Missions ************************************************  	 

// pilot school


pilot_school_loop:

{
SCRIPT_NAME PSCH

pilot_school_loop_inner:

WAIT 0
			
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer pilotX pilotY pilotZ 1.0 1.0 1.0 TRUE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_desert_mission_counter > 3	
						SET_FADING_COLOUR 0 0 0
						flag_player_on_mission = 1
						IF played_scipted_airscript_cut = 0
							GOSUB airstrip_script_cut1
							played_scipted_airscript_cut = 1
						ELSE
							SET_FADING_COLOUR 0 0 0
							IF pilot_test_passed = 0
								PRINT_BIG ( DESERT5 ) 1000 2 //"Pilot School"
							ENDIF
							SET_PLAYER_CONTROL player1 OFF
							GOSUB mini_fade
						ENDIF
						LOAD_AND_LAUNCH_MISSION desert5.sc
					ENDIF
				ENDIF

			ENDIF
		ENDIF
	ENDIF
			
GOTO pilot_school_loop_inner

}


// **********************************************************************************************************
// ********************************************VEGAS MISSIONS************************************************
// **********************************************************************************************************

// ***********************************************************************************************************
// ********************************************Casino Missions************************************************

casino_mission_loop:

{

SCRIPT_NAME CASINO

casino_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_casino_mission_counter = 9
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF CAN_PLAYER_START_MISSION player1
				IF LOCATE_CHAR_ON_FOOT_3D scplayer casinoX casinoY casinoZ 1.2 1.2 2.0 FALSE
					IF flag_casino_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( CASINO1 ) 1000 2 //"casino mission 1" //CHRIS M	 //Wind up 
						GOSUB triad_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino1.sc
					ENDIF
					IF flag_casino_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( CASEEN2 ) 1000 2 //"casino mission 2" //PAUL  //Kickstart Quarry //Explosive situation
						GOSUB triad_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino2.sc 
					ENDIF
					IF flag_casino_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( CASINO3 ) 1000 2 //"casino mission 3" //STEVE //Fake Chips 
						GOSUB triad_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino3.sc
					ENDIF
					IF flag_casino_mission_counter = 6
						flag_player_on_mission = 1
						PRINT_BIG ( CASINO7 ) 1000 2 //"casino mission 7" //CRAIG //Clear & Present Danger
						GOSUB triad_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino7_cut.sc
					ENDIF
				ENDIF

				IF LOCATE_CHAR_ON_FOOT_3D scplayer 2026.6028 1007.7353 9.8127 1.2 1.2 2.0 FALSE
					IF flag_casino_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( CASINO4 ) 1000 2 //"casino mission 4" //DAVE //Paul & Maccer 
						GOSUB triad_casino_script_cut3
						LOAD_AND_LAUNCH_MISSION casino4.sc
					ENDIF
				ENDIF

				IF LOCATE_CHAR_ON_FOOT_3D scplayer heistX heistY heistZ 1.2 1.2 2.0 FALSE
					IF flag_casino_mission_counter = 4
					AND flag_mob_vegas[2] = 1
						flag_player_on_mission = 1
						PRINT_BIG ( CASINO5 ) 1000 2 //"casino mission 5" //SIMON //Hospital Hi Jinx 
						GOSUB mafia_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino5.sc
					ENDIF
					IF flag_casino_mission_counter = 5
						flag_player_on_mission = 1
						PRINT_BIG ( CASINO6 ) 1000 2 //"casino mission 6" //SIMON //Abattoir //Meat business
						GOSUB mafia_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino6.sc
					ENDIF
					IF flag_casino_mission_counter = 7
					AND flag_mob_vegas[3] = 1
						flag_player_on_mission = 1
						PRINT_BIG ( CASINO9 ) 1000 2 //"casino mission 9" //SIMON //Freefall 
						GOSUB mafia_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino9.sc
					ENDIF
					IF flag_casino_mission_counter = 8
					AND flag_mob_vegas[4] = 1
						flag_player_on_mission = 1
						PRINT_BIG ( CASIN10 ) 1000 2 //"casino mission 10" //WILLIE //St Marks Bistro
						GOSUB mafia_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION casino10.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO casino_mission_loop_inner

}
	

// ***********************************************************************************************************
// ********************************************Vcrash Missions**************************************************

vcrash_mission_loop:

{

SCRIPT_NAME VCRASH

vcrash_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_vcrash_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF CAN_PLAYER_START_MISSION player1
				IF LOCATE_CHAR_ON_FOOT_3D scplayer vcrashX vcrashY vcrashZ 1.2 1.2 2.0 FALSE
					IF flag_vcrash_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( VCRASH1 ) 1000 2 //"vcrash mission 1" //CHRIS R //Uber Chase
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION vcrash1.sc
					ENDIF
				ENDIF

				IF flag_vcrash_mission_counter = 1
				AND flag_mob_vegas[0] = 1
					IF LOCATE_CHAR_ON_FOOT_3D scplayer -378.75 2235.85 41.42 1.2 1.2 2.0 FALSE //Triggered from mobile (need coords for desert town)
						flag_player_on_mission = 1
						PRINT_BIG ( VCRASH2 ) 1000 2 //"vcrash mission 2" //CHRIS R //High Noon
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION vcrash2.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO vcrash_mission_loop_inner

}


// ***********************************************************************************************************
// ********************************************Doc G Missions*************************************************

doc_mission_loop:

{

SCRIPT_NAME DOC

doc_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_doc_mission_counter = 1
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer docX docY docZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_doc_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( DOC_2 ) 1000 2 //"doc mission 2" //PAUL	//Jumper
						GOSUB mini_fade
						LOAD_AND_LAUNCH_MISSION doc2.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO doc_mission_loop_inner

}

	
// ***********************************************************************************************************
// ********************************************Heist Missions*************************************************

heist_mission_loop:

{

SCRIPT_NAME HEIST

//VIEW_INTEGER_VARIABLE flag_heist_mission_counter flag_heist_mission_counter

heist_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_heist_mission_counter = 6
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer TheheistX TheheistY TheheistZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_heist_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( HEIST_1 ) 1000 2 //"heist mission 1" //STEVE //Photo Plans 
						GOSUB triad_casino_script_cut2
						LOAD_AND_LAUNCH_MISSION heist1.sc
					ENDIF
					IF flag_heist_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( HEIST_3 ) 1000 2 //"heist mission 2" //DAVE //Girlfriend 
						GOSUB triad_casino_script_cut2
						LOAD_AND_LAUNCH_MISSION heist3.sc
					ENDIF
					IF flag_heist_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( HEIST_2 ) 1000 2 //"heist mission 2" //DAVE //Dam charges 
						GOSUB triad_casino_script_cut2
						LOAD_AND_LAUNCH_MISSION heist2.sc
					ENDIF
					IF flag_heist_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( HEIST_4 ) 1000 2 //"heist mission 4" //STEVE T //Street Hawk 
						GOSUB triad_casino_script_cut2
						LOAD_AND_LAUNCH_MISSION heist4.sc
					ENDIF						
					IF flag_heist_mission_counter = 4
						GET_TIME_OF_DAY hours minutes
						IF hours >= 20
						OR hours < 6
							flag_player_on_mission = 1
							CLEAR_PRINTS
							PRINT_BIG ( HEIST_5 ) 1000 2 //"heist mission 5" //WILLIE //Steal Heli Magnate
							GOSUB triad_casino_script_cut2
							LOAD_AND_LAUNCH_MISSION heist5.sc
						ELSE
							PRINT_NOW MTIME1 1000 1 //Come back between 20:00 6:00
						ENDIF
					ENDIF
					IF flag_heist_mission_counter = 5
					AND flag_mob_vegas[6] = 1
						IF keycard_aquired_from_millie = 1
							flag_player_on_mission = 1
							PRINT_BIG ( HEIST_9 ) 1000 2 //"heist mission 9" //PAUL D //The Heist 
							GOSUB triad_casino_script_cut2
							LOAD_AND_LAUNCH_MISSION heist9.sc
						ELSE
							PRINT_NOW NEEDKEY 1000 1 //Need Key card
						ENDIF
					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO heist_mission_loop_inner

}



// **********************************************************************************************************
// *******************************************(LA2) MISSIONS*************************************************
// **********************************************************************************************************


// **********************************************************************************************************
// ******************************************Mansion Missions************************************************	

mansion_mission_loop:

{

SCRIPT_NAME MANSION

mansion_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_mansion_mission_counter = 4
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF CAN_PLAYER_START_MISSION player1
				IF LOCATE_CHAR_ON_FOOT_3D scplayer casinoX casinoY casinoZ 1.2 1.2 2.0 FALSE
					IF flag_mansion_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( MAN_1 ) 1000 2 //"mansion mission 1" //SIMON //Take Back G's Mansion
						GOSUB triad_casino_script_cut1
						LOAD_AND_LAUNCH_MISSION mansion1.sc
					ENDIF
				ENDIF
				IF LOCATE_CHAR_ON_FOOT_3D scplayer mansionX mansionY mansionZ 1.2 1.2 2.0 FALSE
					IF flag_mansion_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( MAN_2 ) 1000 2 //"mansion mission 2" //CRAIG //Steal Harrier
						GOSUB LA_mansion_script_cut1
						LOAD_AND_LAUNCH_MISSION mansion2.sc 
					ENDIF
					IF flag_mansion_mission_counter = 2
						flag_player_on_mission = 1
						PRINT_BIG ( MAN_3 ) 1000 2 //"mansion mission 3" //PAUL //Pick up sweet
						GOSUB LA_mansion_script_cut1
						LOAD_AND_LAUNCH_MISSION mansion3.sc
					ENDIF
					IF flag_mansion_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( MAN_5 ) 1000 2 //"mansion mission 5" //SIMON //Take down MC Strap 
						GOSUB LA_mansion_script_cut1
						LOAD_AND_LAUNCH_MISSION mansion5.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO mansion_mission_loop_inner

}


// ***********************************************************************************************************
// ******************************************Grove Missions***************************************************
	

grove_mission_loop:

{

SCRIPT_NAME GROVE

grove_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_grove_mission_counter = 2
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF CAN_PLAYER_START_MISSION player1
				IF LOCATE_CHAR_ON_FOOT_3D scplayer introX introY introZ 1.2 1.2 2.0 FALSE
					IF flag_grove_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( GROVE_1 ) 1000 2 //"grove mission 1" //WILLIE //Beat Down on b Dup 
						GOSUB mother_script_cut
						LOAD_AND_LAUNCH_MISSION grove1.sc
					ENDIF
				ENDIF

				IF LOCATE_CHAR_ON_FOOT_3D scplayer sweetX sweetY sweetZ 1.2 1.2 2.0 FALSE
					IF flag_grove_mission_counter = 1
						flag_player_on_mission = 1
						PRINT_BIG ( GROVE_2 ) 1000 2 //"grove mission 2" //PAUL //Grove 4 life 
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION grove2.sc
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
GOTO grove_mission_loop_inner

}


// ***********************************************************************************************************
// ******************************************Riot Missions****************************************************



riot_mission_loop:

{

SCRIPT_NAME RIOT

riot_mission_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_riot_mission_counter = 5
		TERMINATE_THIS_SCRIPT
	ENDIF
	
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer mansionX mansionY mansionZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_riot_mission_counter = 0
						flag_player_on_mission = 1
						PRINT_BIG ( RIOT_1 ) 1000 2 //"riot mission 1" //ANDY //RIOT! 
						GOSUB LA_mansion_script_cut1
						LOAD_AND_LAUNCH_MISSION riot1.sc //andy
					ENDIF
				ENDIF
			ENDIF

			IF LOCATE_CHAR_ON_FOOT_3D scplayer sweetX sweetY sweetZ 1.2 1.2 2.0 FALSE
				IF CAN_PLAYER_START_MISSION player1
					IF flag_riot_mission_counter = 1
						FIND_MAX_NUMBER_OF_GROUP_MEMBERS returned_respect
						IF returned_respect	> 1
							flag_player_on_mission = 1
							PRINT_BIG ( RIOT_2 ) 1000 2 //"riot mission 2" //ANDY //DESPERADOS
							GOSUB sweet_script_cut1
							LOAD_AND_LAUNCH_MISSION riot2.sc
						ELSE
							PRINT_NOW MOREREP 1000 1 //You need more respect
							IF respect_help_played = 0
								PRINT_HELP HELP101
								respect_help_played	= 1
							ENDIF
						ENDIF
					ENDIF
					IF flag_mob_LA2[3] = 1
						IF flag_mob_LA2[2] = 1
							IF flag_riot_mission_counter = 2
								flag_player_on_mission = 1
								PRINT_BIG ( RIOT_4 ) 1000 2 //"riot mission 4" //IMRAN //CARTER BLOCK 
								GOSUB sweet_script_cut1
								LOAD_AND_LAUNCH_MISSION finaleA.sc
							ENDIF	
						ELSE
							PRINT_NOW MORTURF 2000 1 //Take over gang territories
						ENDIF
					ENDIF
					IF flag_riot_mission_counter = 3
						flag_player_on_mission = 1
						PRINT_BIG ( RIOT_4 ) 1000 2 //"riot mission 4" //IMRAN //CARTER BLOCK 
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION finaleB.sc
					ENDIF
					IF flag_riot_mission_counter = 4
						flag_player_on_mission = 1
						PRINT_BIG ( RIOT_4 ) 1000 2 //"riot mission 4" //IMRAN //CARTER BLOCK 
						GOSUB sweet_script_cut1
						LOAD_AND_LAUNCH_MISSION finaleC.sc
					ENDIF
				ENDIF
			ENDIF

			IF flag_riot_mission_counter = 3
			AND finaleB_played_first_time_round	= 0
				finaleB_played_first_time_round = 1	
				flag_player_on_mission = 1
				LOAD_AND_LAUNCH_MISSION finaleB.sc
			ENDIF

			IF flag_riot_mission_counter = 4
			AND finaleB_played_first_time_round	= 1
				finaleB_played_first_time_round = 2
				flag_player_on_mission = 1
				LOAD_AND_LAUNCH_MISSION finaleC.sc
			ENDIF
				
		ENDIF
	ENDIF
	
GOTO riot_mission_loop_inner

}

// *********************************************AMBIENT STUFF**************************************************


// ************************************************************************************************************
// ********************************************ODD JOBS********************************************************
// ************************************************************************************************************

// *******************************************Shooting Range Missions******************************************

// Shoot Mission 1
 

shoot_range_loop:

{

SCRIPT_NAME SHOOT
								

shoot_range_loop_inner:

WAIT 125

IF IS_PLAYER_PLAYING player1
    IF flag_player_on_mission = 0  
    	IF NOT main_visible_area = 0
	    	IF flag_shooting_range_mission = 0                  
		    	//find which range the player is in
		    	IF flag_dont_start_shooting_range = 0
		    		sr_i = 0
		            WHILE sr_i < 4
		                IF LOCATE_CHAR_ON_FOOT_3D scplayer Srange_X[sr_i] Srange_Y[sr_i] Srange_Z[sr_i] 20.0 20.0 10.0 FALSE
				            IF CAN_PLAYER_START_MISSION player1
				                flag_player_on_mission = 1
		                        SR_range_id = sr_i
		                        LOAD_AND_LAUNCH_MISSION sh_range.sc
							ENDIF
		                ENDIF
		                sr_i ++
		            ENDWHILE
			    ENDIF
			ENDIF
		ENDIF
    ELSE
		IF NOT main_visible_area = 0
        	IF flag_dont_start_shooting_range = 0
				flag_dont_start_shooting_range = 1	
	        ENDIF
        ENDIF
	ENDIF

	IF flag_dont_start_shooting_range = 1
		IF main_visible_area = 0
			flag_dont_start_shooting_range = 0
		ENDIF
	ENDIF
            
ENDIF
            
GOTO shoot_range_loop_inner

}


// ************************************************************************************************************
// *******************************************gym**************************************************************

VAR_INT main_visible_area_char

gym_loop:

{

SCRIPT_NAME gym

gym_loop_inner:

WAIT 70

    IF flag_player_on_mission = 0
        
		IF flag_mob_la1[6] = 1  // WD_SCRIPT_BYPASS // now activates when you mission jump
		    IF switch_the_gym_interiors_off = 0
				SWITCH_ENTRY_EXIT gym1 TRUE
				SWITCH_ENTRY_EXIT gym2 TRUE
				SWITCH_ENTRY_EXIT gym3 TRUE
				switch_the_gym_interiors_off = 1
			ENDIF
		ENDIF

        IF IS_PLAYER_PLAYING player1
        	IF CAN_PLAYER_START_MISSION player1    
				IF LOCATE_CHAR_ON_FOOT_3D scplayer 767.1537 4.8323 999.7185 50.0 50.0 10.0 FALSE					
					GET_CHAR_AREA_VISIBLE scplayer main_visible_area_char
					IF main_visible_area_char = 5
						LOAD_AND_LAUNCH_MISSION gymLS.sc // PAUL D
	                ENDIF
				ENDIF
				IF LOCATE_CHAR_ON_FOOT_3D scplayer 768.4777 -37.1737 999.6865 50.0 50.0 10.0 FALSE
					GET_CHAR_AREA_VISIBLE scplayer main_visible_area_char
					IF main_visible_area_char = 6
						LOAD_AND_LAUNCH_MISSION gymSF.sc // PAUL D
	                ENDIF
				ENDIF
				IF LOCATE_CHAR_ON_FOOT_3D scplayer 766.5975 -65.2930 999.6562 50.0 50.0 10.0 FALSE
					GET_CHAR_AREA_VISIBLE scplayer main_visible_area_char
					IF main_visible_area_char = 7
						LOAD_AND_LAUNCH_MISSION gymLV.sc // PAUL D
	                ENDIF
				ENDIF
            ENDIF
        ENDIF
    ELSE
		IF flag_mob_la1[6] = 1
			IF main_visible_area = 0
				IF switch_the_gym_interiors_off = 1
					SWITCH_ENTRY_EXIT gym1 FALSE
					SWITCH_ENTRY_EXIT gym2 FALSE
					SWITCH_ENTRY_EXIT gym3 FALSE
					switch_the_gym_interiors_off = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF

GOTO gym_loop_inner
                                                
}
 

// ************************************************************************************************************
// *******************************************trucking*********************************************************


trucking_loop:

{

SCRIPT_NAME TRUCKS

trucking_loop_inner:

WAIT mission_trigger_wait_time

    IF flag_player_on_mission = 0
        IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer truckX truckY truckZ 1.2 1.2 1.5 FALSE
				IF CAN_PLAYER_START_MISSION player1
					SET_PLAYER_CONTROL player1 OFF
					DRAW_ODDJOB_TITLE_BEFORE_FADE FALSE
					PRINT_BIG ( TRUCK ) 3000 5 //Trucking
					GOSUB mini_fade
					LOAD_AND_LAUNCH_MISSION truck.sc
				ENDIF
			ENDIF
		ENDIF
    ENDIF

GOTO trucking_loop_inner
                                                
} 
 

// ************************************************************************************************************
// *********************************************quarry*********************************************************


quarry_loop:

{

SCRIPT_NAME QUARRYS

quarry_loop_inner:

WAIT 0

    IF flag_player_on_mission = 0
        IF IS_PLAYER_PLAYING player1
            IF LOCATE_CHAR_ON_FOOT_3D scplayer quarryX quarryY quarryZ 1.5 1.8 1.5 FALSE
				IF CAN_PLAYER_START_MISSION player1
					SET_PLAYER_CONTROL player1 OFF
					DRAW_ODDJOB_TITLE_BEFORE_FADE FALSE
                    PRINT_BIG ( QUARRY ) 3000 5 //Quarry
					GOSUB mini_fade
                	LOAD_AND_LAUNCH_MISSION quarry.sc
                ENDIF
			ENDIF
        ENDIF
    ENDIF



GOTO quarry_loop_inner
                                                
} 


// ************************************************************************************************************
// ********************************************Boat School**************************************************


boats_school_loop:																				  
																									  
{																									  

SCRIPT_NAME BSCHOO

boats_school_loop_inner:

WAIT 0

	IF flag_player_on_mission = 0
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer boatsX boatsY boatsZ 1.0 1.0 1.5 TRUE
				IF CAN_PLAYER_START_MISSION player1
					SET_PLAYER_CONTROL player1 OFF
					GOSUB mini_fade
					LOAD_AND_LAUNCH_MISSION boat.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF

GOTO boats_school_loop_inner

}


// ************************************************************************************************************
// ********************************************Bike School**************************************************



bikes_school_loop:																				  
																									  
{																									  

SCRIPT_NAME BIKES

bikes_school_loop_inner:

WAIT 0

	IF flag_player_on_mission = 0
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer bikesX bikesY bikesZ 1.0 1.0 1.5 TRUE
				IF CAN_PLAYER_START_MISSION player1	    
					SET_PLAYER_CONTROL player1 OFF
					GOSUB mini_fade
					LOAD_AND_LAUNCH_MISSION bikesch.sc
				ENDIF
			ENDIF
		ENDIF
	ENDIF

GOTO bikes_school_loop_inner

}


// *********************************************R3 MISSIONS****************************************************
// ************************************************************************************************************

// ************************************************************************************************************
// ********************************************R3 Missions*****************************************************

// Taxi Mission 1

R3missions_loop:

{
SCRIPT_NAME	R3

R3missions_loop_inner:
	
	WAIT 70

	IF flag_player_on_mission = 0

		IF IS_PLAYER_PLAYING player1
			IF IS_CHAR_IN_ANY_CAR scplayer
				IF NOT IS_MINIGAME_IN_PROGRESS	
					IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
						 GOTO R3missions_loop_date
					ELSE					
					
						IF IS_CHAR_IN_TAXI scplayer
						OR IS_CHAR_IN_MODEL scplayer AMBULAN
						OR IS_CHAR_IN_MODEL scplayer firetruk
						OR IS_CHAR_IN_MODEL scplayer HUNTER
						OR IS_CHAR_IN_MODEL scplayer BOXBURG
						OR IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer R3_player_car
							IF NOT IS_CAR_IN_WATER R3_player_car
								
								IF IS_CHAR_IN_TAXI scplayer
									IF NOT IS_PLAYER_IN_SHORTCUT_TAXI Player1  //TAXI Mission*************************************************************************
										IF flag_taxiodd_mission_launched = 0
											IF R3_mission_help = 0
												IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
													PRINT_HELP ( TTUTOR )  
													R3_mission_help = 1
												ENDIF
											ENDIF
											
											IF NOT controlmode = 3
												IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

													WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
													
														WAIT 0
														IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
														IF NOT IS_PLAYER_PLAYING player1
														OR NOT IS_CHAR_IN_TAXI scplayer
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_MINIGAME_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
													ENDWHILE

													PRINT_BIG ( TAXI_M ) 6000 5
													WAIT 0
													LOAD_AND_LAUNCH_MISSION taxiodd.sc	
													flag_taxiodd_mission_launched = 1
													GOTO R3missions_loop_inner
												ENDIF
											ELSE
												IF IS_BUTTON_PRESSED PAD1 SQUARE

													WHILE IS_BUTTON_PRESSED PAD1 SQUARE
													
														WAIT 0
														IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
														IF NOT IS_PLAYER_PLAYING player1
														OR NOT IS_CHAR_IN_TAXI scplayer
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_MINIGAME_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
													ENDWHILE

													PRINT_BIG ( TAXI_M ) 6000 5
													WAIT 0
													LOAD_AND_LAUNCH_MISSION taxiodd.sc	
													flag_taxiodd_mission_launched = 1
													GOTO R3missions_loop_inner
												ENDIF
											ENDIF
											
										ENDIF
									ENDIF //IS_PLAYER_IN_SHORTCUT_TAXI Player1F
								ENDIF	// IF IS_CHAR_IN_TAXI scplayer


								IF IS_CHAR_IN_MODEL scplayer AMBULAN //AMBULANCE MISSION**************************************************************************
									IF flag_player_on_ambulance_mission = 0

										IF R3_mission_help = 0
											IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
												PRINT_HELP ( ATUTOR ) //Press RIGHTSHOCK to start
												R3_mission_help = 1
											ENDIF
										ENDIF

										IF NOT controlmode = 3
											IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
											
												WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

													WAIT 0
													IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
													IF NOT IS_PLAYER_PLAYING player1
													OR NOT IS_CHAR_IN_MODEL scplayer ambulan
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_MINIGAME_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
												ENDWHILE

												PRINT_BIG ( AMBUL_M ) 6000 5
												WAIT 0
												LOAD_AND_LAUNCH_MISSION ambulance.sc	
												//been_in_ambulance_before = 1
												GOTO R3missions_loop_inner
											ENDIF
										ELSE
											IF IS_BUTTON_PRESSED PAD1 SQUARE
											
												WHILE IS_BUTTON_PRESSED PAD1 SQUARE

													WAIT 0
													IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
													IF NOT IS_PLAYER_PLAYING player1
													OR NOT IS_CHAR_IN_MODEL scplayer ambulan
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_MINIGAME_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
												ENDWHILE

												PRINT_BIG ( AMBUL_M ) 6000 5
												WAIT 0
												LOAD_AND_LAUNCH_MISSION ambulance.sc	
												//been_in_ambulance_before = 1
												GOTO R3missions_loop_inner
											ENDIF
										ENDIF
										
									ENDIF
								ENDIF	//	IF IS_CHAR_IN_MODEL scplayer ambula


								IF IS_CHAR_IN_MODEL scplayer FIRETRUK //FIRETRUCK MISSION*************************************************************************
									IF flag_player_on_fire_mission = 0

										IF R3_mission_help = 0
											IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
												PRINT_HELP ( FTUTOR ) //Press RIGHTSHOCK to start
												R3_mission_help = 1
											ENDIF
										ENDIF

										IF NOT controlmode = 3
											IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

												WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

													WAIT 0
													IF flag_player_on_mission = 1
														GOTO R3missions_loop_inner	
													ENDIF
													IF NOT IS_PLAYER_PLAYING player1
													OR NOT IS_CHAR_IN_MODEL scplayer firetruk
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_MINIGAME_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
												ENDWHILE

												PRINT_BIG ( FIRE_M ) 6000 5
												WAIT 0
												LOAD_AND_LAUNCH_MISSION firetruck.sc	
												//been_in_a_firetruk_before = 1
												GOTO R3missions_loop_inner
											ENDIF
										ELSE
											IF IS_BUTTON_PRESSED PAD1 SQUARE

												WHILE IS_BUTTON_PRESSED PAD1 SQUARE

													WAIT 0
													IF flag_player_on_mission = 1
														GOTO R3missions_loop_inner	
													ENDIF
													IF NOT IS_PLAYER_PLAYING player1
													OR NOT IS_CHAR_IN_MODEL scplayer firetruk
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_MINIGAME_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
												ENDWHILE

												PRINT_BIG ( FIRE_M ) 6000 5
												WAIT 0
												LOAD_AND_LAUNCH_MISSION firetruck.sc	
												//been_in_a_firetruk_before = 1
												GOTO R3missions_loop_inner
											ENDIF
										ENDIF
									ENDIF
								ENDIF	//	IF IS_CHAR_IN_MODEL scplayer firetruk


								IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer //COP MISSION***************************************************************************
								OR IS_CHAR_IN_MODEL scplayer HUNTER

									IF R3_mission_help = 0
										IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
											PRINT_HELP ( CTUTOR ) //Press RIGHTSHOCK to start
											R3_mission_help = 1
										ENDIF
									ENDIF
									IF NOT controlmode = 3
										IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
											
											WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

												WAIT 0
												IF flag_player_on_mission = 1
													GOTO R3missions_loop_inner	
												ENDIF
												IF NOT IS_PLAYER_PLAYING player1
													GOTO R3missions_loop_inner
												ENDIF
												IF NOT IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
												AND NOT IS_CHAR_IN_MODEL scplayer HUNTER
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_MINIGAME_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
											ENDWHILE

											IF IS_CHAR_IN_MODEL scplayer HUNTER
												PRINT_BIG COP_M3 6000 5
											ELSE
												IF IS_CHAR_IN_MODEL scplayer CHEETAH
													PRINT_BIG COP_M2 6000 5
												ELSE
													PRINT_BIG COP_M 6000 5
												ENDIF
											ENDIF

											WAIT 0
											LOAD_AND_LAUNCH_MISSION copcar.sc//CHRIS R
											//been_in_a_copcar_before = 1
											GOTO R3missions_loop_inner
										ENDIF
									ELSE
										IF IS_BUTTON_PRESSED PAD1 SQUARE
											
											WHILE IS_BUTTON_PRESSED PAD1 SQUARE

												WAIT 0
												IF flag_player_on_mission = 1
													GOTO R3missions_loop_inner	
												ENDIF
												IF NOT IS_PLAYER_PLAYING player1
													GOTO R3missions_loop_inner
												ENDIF
												IF NOT IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
												AND NOT IS_CHAR_IN_MODEL scplayer HUNTER
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_MINIGAME_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
											ENDWHILE

											IF IS_CHAR_IN_MODEL scplayer HUNTER
												PRINT_BIG COP_M3 6000 5
											ELSE
												IF IS_CHAR_IN_MODEL scplayer CHEETAH
													PRINT_BIG COP_M2 6000 5
												ELSE
													PRINT_BIG COP_M 6000 5
												ENDIF
											ENDIF

											WAIT 0
											LOAD_AND_LAUNCH_MISSION copcar.sc//CHRIS R
											//been_in_a_copcar_before = 1
											GOTO R3missions_loop_inner
										ENDIF
									ENDIF
									
								ENDIF // If in some kind of cop car

								IF IS_CHAR_IN_MODEL scplayer BOXBURG //BURGLARY MISSION**************************************************************************
						            IF flag_player_on_burglary_mission = 0

						               	GET_TIME_OF_DAY hours minutes
										IF hours >= 20
										OR hours < 6
							                IF R3_mission_help = 0
							                	IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
								                	PRINT_HELP ( BTUTOR2 ) //Press RIGHTSHOCK to start
													R3_mission_help = 1
												ENDIF
											ENDIF
										ENDIF

										IF NOT controlmode = 3
											IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

												GET_TIME_OF_DAY hours minutes
												IF hours >= 20
												OR hours < 6
													
													WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

														WAIT 0
														IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
														IF NOT IS_PLAYER_PLAYING player1
														OR NOT IS_CHAR_IN_MODEL scplayer BOXBURG
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_MINIGAME_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
													ENDWHILE
													DRAW_ODDJOB_TITLE_BEFORE_FADE FALSE
													PRINT_BIG ( BURGLAR ) 6000 5
													WAIT 0
													LOAD_AND_LAUNCH_MISSION burglary.sc                                                                               
													flag_player_on_burglary_mission = 1
													GOTO R3missions_loop_inner
												ELSE
													PRINT_NOW BURGHEL 1000 1 // ~s~You can only trigger the burglary missions between 20:00 and 6:00.
												ENDIF
											ENDIF
										ELSE
											IF IS_BUTTON_PRESSED PAD1 SQUARE

												GET_TIME_OF_DAY hours minutes
												IF hours >= 20
												OR hours < 6
													
													WHILE IS_BUTTON_PRESSED PAD1 SQUARE

														WAIT 0
														IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
														IF NOT IS_PLAYER_PLAYING player1
														OR NOT IS_CHAR_IN_MODEL scplayer BOXBURG
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_MINIGAME_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
														IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
															GOTO R3missions_loop_inner
														ENDIF
													ENDWHILE
													DRAW_ODDJOB_TITLE_BEFORE_FADE FALSE
													PRINT_BIG ( BURGLAR ) 6000 5
													WAIT 0
													LOAD_AND_LAUNCH_MISSION burglary.sc                                                                               
													flag_player_on_burglary_mission = 1
													GOTO R3missions_loop_inner
												ELSE
													PRINT_NOW BURGHEL 1000 1 // ~s~You can only trigger the burglary missions between 20:00 and 6:00.
												ENDIF
											ENDIF
										ENDIF
										
									ENDIF
								ENDIF   // IF IS_CHAR_IN_MODEL scplayer boxville
									
							ENDIF
						ENDIF
						
						IF Return_cities_passed > 1
							IF IS_CHAR_IN_MODEL scplayer freight 
							OR IS_CHAR_IN_MODEL scplayer streak
								STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer R3_player_car
								IF NOT IS_CAR_IN_WATER R3_player_car
								AND NOT HAS_TRAIN_DERAILED R3_player_car

		 							IF flag_player_on_freight_mission = 0
										IF R3_mission_help = 0
											IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
												PRINT_HELP ( FTUTORA ) //Press RIGHTSHOCK to start
												R3_mission_help = 1
											ENDIF
										ENDIF

										IF NOT controlmode = 3
											IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

												WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

													WAIT 0
													IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
													IF NOT IS_PLAYER_PLAYING player1
														GOTO R3missions_loop_inner
													ENDIF
													IF NOT IS_CHAR_IN_MODEL scplayer freight
													AND NOT IS_CHAR_IN_MODEL scplayer streak
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_MINIGAME_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
												ENDWHILE

												//PRINT_BIG ( FREIGH ) 6000 5
												WAIT 0
												LOAD_AND_LAUNCH_MISSION freight.sc	
												been_in_freight_before = 1
												GOTO R3missions_loop_inner
											ENDIF
										ELSE
											IF IS_BUTTON_PRESSED PAD1 SQUARE

												WHILE IS_BUTTON_PRESSED PAD1 SQUARE

													WAIT 0
													IF flag_player_on_mission = 1
															GOTO R3missions_loop_inner	
														ENDIF
													IF NOT IS_PLAYER_PLAYING player1
														GOTO R3missions_loop_inner
													ENDIF
													IF NOT IS_CHAR_IN_MODEL scplayer freight
													AND NOT IS_CHAR_IN_MODEL scplayer streak
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_MINIGAME_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
													IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
														GOTO R3missions_loop_inner
													ENDIF
												ENDWHILE

												//PRINT_BIG ( FREIGH ) 6000 5
												WAIT 0
												LOAD_AND_LAUNCH_MISSION freight.sc	
												been_in_freight_before = 1
												GOTO R3missions_loop_inner
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF // IF IS_CHAR_IN_MODEL scplayer freight train
							
						ENDIF

						/* removed in JP version
							// ************************************PIMPING*************************************************************
						IF IS_CHAR_IN_MODEL scplayer BROADWAY 
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer R3_player_car
							IF NOT IS_CAR_IN_WATER R3_player_car	
								IF flag_player_on_pimp_mission = 0
									IF R3_mission_help = 0
										IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
											PRINT_HELP ( PTUTORA ) //Press RIGHTSHOCK to start
											R3_mission_help = 1
										ENDIF
									ENDIF
									IF NOT controlmode = 3
										IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

											WHILE IS_BUTTON_PRESSED PAD1 RIGHTSHOCK

												WAIT 0
												IF flag_player_on_mission = 1
													GOTO R3missions_loop_inner	
												ENDIF
												IF NOT IS_PLAYER_PLAYING player1
													GOTO R3missions_loop_inner
												ENDIF
												IF NOT IS_CHAR_IN_MODEL scplayer BROADWAY
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_MINIGAME_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
											ENDWHILE

											PRINT_BIG ( PIMP ) 6000 5
											WAIT 0
											LOAD_AND_LAUNCH_MISSION pimp.sc	
											flag_pimping_trigger = 1
											GOTO R3missions_loop_inner
										ENDIF
									ELSE
										IF IS_BUTTON_PRESSED PAD1 SQUARE

											WHILE IS_BUTTON_PRESSED PAD1 SQUARE

												WAIT 0
												IF flag_player_on_mission = 1
													GOTO R3missions_loop_inner	
												ENDIF
												IF NOT IS_PLAYER_PLAYING player1
													GOTO R3missions_loop_inner
												ENDIF
												IF NOT IS_CHAR_IN_MODEL scplayer BROADWAY
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_MINIGAME_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
												IF IS_BIT_SET iDateReport DATE_IN_PROGRESS
													GOTO R3missions_loop_inner
												ENDIF
											ENDWHILE

											PRINT_BIG ( PIMP ) 6000 5
											WAIT 0
											LOAD_AND_LAUNCH_MISSION pimp.sc	
											flag_pimping_trigger = 1
											GOTO R3missions_loop_inner
										ENDIF
									ENDIF
								ENDIF
							ENDIF					
						ENDIF // IF IS_CHAR_IN_MODEL scplayer freight train
						*/
					ENDIF
				ENDIF
			
			ELSE
				IF R3_mission_help = 1
					CLEAR_THIS_PRINT TTUTOR 
					CLEAR_THIS_PRINT ATUTOR
					CLEAR_THIS_PRINT FTUTOR
					CLEAR_THIS_PRINT CTUTOR
					CLEAR_THIS_PRINT BTUTOR2
					CLEAR_THIS_PRINT FTUTORA
					CLEAR_THIS_PRINT PTUTORA
					R3_mission_help = 0
				ENDIF
			ENDIF
			
		ENDIF
	ENDIF //flag_player_on_mission 

GOTO R3missions_loop_inner

R3missions_loop_date:
    
	WAIT 70
	
	IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
		WAIT 5000
		GOTO R3missions_loop_inner
	ENDIF

GOTO R3missions_loop_date

} 


text_after_freight:

{
    SCRIPT_NAME FRTEXT

    WAIT 5000

    CLEAR_HELP

    PRINT_HELP ( FREI_16 ) // The freight oddjob has been passed, you can replay it to make extra cash

    TERMINATE_THIS_SCRIPT

}


// ************************************************************************************************************
// *****************************************  STADIUM SCRIPTS  ************************************************


// ************************************************************************************************************
// **************************************BLOOD RING (Destruction Derby)****************************************



blood_loop:																				  
																									  
{																									  

SCRIPT_NAME BLOODR

blood_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_player_on_mission = 0
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer bloodX bloodY bloodZ 1.4 1.8 1.5 FALSE

				VAR_INT stat

				IF CAN_PLAYER_START_MISSION player1
					GOSUB mini_fade
					PRINT_BIG ( STAD_04 ) 1000 2 //Blood ring
					LOAD_AND_LAUNCH_MISSION blood.sc //ANDY
				ENDIF

			ENDIF
		ENDIF
	ENDIF

GOTO blood_loop_inner

}

// ************************************************************************************************************
// ********************************************HOT RING (RACE)*************************************************



hot_loop:																				  
																									  
{																									  

SCRIPT_NAME HOTR

hot_loop_inner:

WAIT mission_trigger_wait_time

	IF flag_player_on_mission = 0
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer hotringX hotringY hotringZ 1.4 1.8 1.5 FALSE
				
				GET_INT_STAT DRIVING_SKILL stat
				
				IF stat > 199
				
					IF CAN_PLAYER_START_MISSION player1

						PRINT_BIG ( STAD_03 ) 1000 2//8-Track
						GOSUB mini_fade
						race_selection = NASCAR_RACE
						show_race_selection = FALSE
						LOAD_AND_LAUNCH_MISSION racetour.sc	//CHRIS R
					ENDIF
				ELSE
					print_now STATCAR 500 1 // Your driving skill is not high enough to enter this race.
				ENDIF
			ENDIF
		ENDIF
	ENDIF


GOTO hot_loop_inner

}


// ************************************************************************************************************
// ***********************************DIRT RING (KICKSTART)****************************************************

kick_loop:                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                              
{                                                                                                                                                                                                                                                                                                             

SCRIPT_NAME KICKS

kick_loop_inner:

WAIT mission_trigger_wait_time

    IF flag_player_on_mission = 0
        IF IS_PLAYER_PLAYING player1
        	IF LOCATE_CHAR_ON_FOOT_3D scplayer kickX kickY kickZ 1.4 1.8 1.5 FALSE

				IF CAN_PLAYER_START_MISSION player1
					
                	GET_CURRENT_DAY_OF_WEEK	weekday
					IF weekday = 0
					OR weekday = 2
					OR weekday = 4
						get_int_stat BIKE_SKILL	stat
						IF stat > 199

							PRINT_BIG ( STAD_01 ) 1000 2//Dirt Track
							GOSUB mini_fade
							race_selection = DIRTBIKE_STADIUM
							show_race_selection = FALSE
							LOAD_AND_LAUNCH_MISSION racetour.sc
						ELSE
							print_now STATBIK 500 1 // Your bike skill is not high enough to enter this race.
						ENDIF
                	ELSE

						PRINT_BIG ( STAD_02 ) 1000 2//Kickstart
		            	GOSUB mini_fade
	                	LOAD_AND_LAUNCH_MISSION kickstart.sc 
					ENDIF
                ENDIF

            ENDIF
        ENDIF
    ENDIF

GOTO kick_loop_inner

}


// ************************************************************************************************************
// ***********************************TRIALTHALON RACE****************************************************

VAR_INT flag_cycling_skill_help_tri	flag_stamina_skill_help_tri	flag_cycling_skill_help2 map_opened_tri

tri_loop:                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                              
{                                                                                                                                                                                                                                                                                                             

SCRIPT_NAME TRI

tri_loop_inner:

WAIT 0

	IF flag_player_on_mission = 0
		IF IS_PLAYER_PLAYING player1
			IF weekday = 1
			OR weekday = 7
				IF LOCATE_CHAR_ON_FOOT_3D scplayer 181.0 -1878.0 2.0 1.4 1.8 1.5 TRUE  // the small one on LS Beach
				OR LOCATE_CHAR_ON_FOOT_3D scplayer 2135.4319 -67.9555 1.8 1.4 1.8 1.5 TRUE // the big one in Fishers Lagoon
					GET_INT_STAT CITIES_PASSED Return_cities_passed
					IF Return_cities_passed > 1
						GET_INT_STAT CYCLE_SKILL stat_read_skill_temp
						IF stat_read_skill_temp >= 500   // skill has to be over 200
							GET_INT_STAT STAMINA stat_stamina_temp
							IF stat_stamina_temp >=500
								IF CAN_PLAYER_START_MISSION player1
									PRINT_BIG ( BOTTY ) 1000 2
									GOSUB mini_fade
									IF IS_PLAYER_PLAYING player1
									    IF LOCATE_CHAR_ON_FOOT_3D scplayer 181.0 -1878.0 2.0 20.4 20.8 20.5 FALSE  // the small one on LS Beach
									    	triathalon_selection = 1
									    ENDIF

									    IF LOCATE_CHAR_ON_FOOT_3D scplayer 2135.4319 -67.9555 20.8 20.4 20.8 20.5 FALSE // the big one in Fishers Lagoon
									    	triathalon_selection = 2
									    ENDIF
									ENDIF
									LOAD_AND_LAUNCH_MISSION triathalon.sc
								ENDIF
							ELSE
								IF flag_stamina_skill_help_tri = 0
								    PRINT_HELP STASKIL //Stamina has to be over 500
								    flag_stamina_skill_help_tri = 1
								ENDIF 
							ENDIF
						ELSE
							IF flag_cycling_skill_help_tri = 0
								PRINT_HELP MTBSKIL //Skill has to be over 500
								flag_cycling_skill_help_tri = 1
							ENDIF
						ENDIF
					ELSE
						IF map_opened_tri = 0
							PRINT_HELP NOCOCK //Maps not opened
							map_opened_tri = 1
						ENDIF
					ENDIF

				ELSE
					IF flag_cycling_skill_help_tri = 1
						flag_cycling_skill_help_tri = 0
					ENDIF
					IF flag_stamina_skill_help_tri = 1
						flag_stamina_skill_help_tri = 0
					ENDIF
					IF map_opened_tri = 1
						map_opened_tri = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


GOTO tri_loop_inner

}

// ************************************************************************************************************
// *********************************** Vehicle location Oddjob Mission ****************************************

vehicle_oddjob_loop:

VAR_INT stunt_course

{
SCRIPT_NAME ODDVEH

vehicle_oddjob_loop_inner:

WAIT mission_trigger_wait_time


IF IS_PLAYER_PLAYING player1
    IF flag_player_on_mission = 0
		IF IS_CHAR_IN_ANY_CAR scplayer
	 
			// ************************************COURIER*************************************************************************
			IF IS_CHAR_IN_MODEL scplayer BMX
			    IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer cour1X cour1Y cour1Z 3.0 3.0 3.0 FALSE //LA
					flag_courier_trigger = 1
				ENDIF
			ENDIF
			IF IS_CHAR_IN_MODEL scplayer FREEWAY
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer cour2X cour2Y cour2Z 3.0 3.0 3.0 FALSE //SF
					flag_courier_trigger = 1
				ENDIF
			ENDIF
			IF IS_CHAR_IN_MODEL scplayer FAGGIO
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer cour3X cour3Y cour3Z 3.0 3.0 3.0 FALSE //LV
			        flag_courier_trigger = 1
				ENDIF
			ENDIF
	        IF IS_CHAR_IN_MODEL scplayer BMX
			    IF flag_courier_trigger = 0
			        PRINT_BIG ( COUR1 ) 1000 2 //COURIER MISSION LA
					LOAD_AND_LAUNCH_MISSION courier.sc  
					flag_courier_trigger = 1          
				ENDIF
			ENDIF
			IF IS_CHAR_IN_MODEL scplayer FREEWAY
				IF flag_courier_trigger = 0
			        PRINT_BIG ( COUR1 ) 1000 2 //COURIER MISSION SF
					LOAD_AND_LAUNCH_MISSION courier.sc 
					flag_courier_trigger = 1           
				ENDIF
			ENDIF
			IF IS_CHAR_IN_MODEL scplayer FAGGIO   
				IF flag_courier_trigger = 0
			        PRINT_BIG ( COUR1 ) 1000 2 //COURIER MISSION LV
					LOAD_AND_LAUNCH_MISSION courier.sc 
					flag_courier_trigger = 1               
				ENDIF
			ENDIF

     		// ************************************MOUNTAIN BIKE*******************************************************************
			IF IS_CHAR_IN_MODEL scplayer MTBIKE
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer mountbX mountbY mountbZ 10.5 10.5 10.5 FALSE //
					flag_mtbike_trigger = 1
				ENDIF
			ENDIF

			IF IS_CHAR_IN_MODEL scplayer MTBIKE
			AND flag_mtbike_trigger = 0
				CLEAR_PRINTS
				GET_INT_STAT CYCLE_SKILL stat_read_skill_temp
				IF stat_read_skill_temp >= 400   // skill has to be over 400
					GET_TIME_OF_DAY hours minutes
					IF hours >= 7     // from 7am to 6pm
					AND hours < 18	
						PRINT_BIG ( MOUNTN ) 1000 2 //mtbike MISSION
						LOAD_AND_LAUNCH_MISSION mtbikerace.sc
						flag_mtbike_trigger = 1  
					ELSE
						PRINT_NOW MTIME4 1000 1 //Come back between 9:00 17:00
					ENDIF
				ELSE
					IF flag_cycling_skill_help = 0
						PRINT_HELP MTBSKIL  //Skill has to be over 200
						flag_cycling_skill_help = 1
					ENDIF
				ENDIF

			ELSE
				IF flag_cycling_skill_help = 1
					flag_cycling_skill_help = 0
				ENDIF
			ENDIF

			// *********************************BMX*************************************************************************
			IF IS_CHAR_IN_MODEL scplayer BMX
		        IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer stunt_bmx1_x stunt_bmx1_y stunt_bmx1_z 4.0 4.0 3.0 FALSE //
		        	flag_bmx_trigger = 1
				ENDIF
	        ENDIF
	        IF IS_CHAR_IN_MODEL scplayer BMX
	        AND flag_bmx_trigger = 0
	        	GET_INT_STAT CYCLE_SKILL stat_read_skill_temp
				IF stat_read_skill_temp >= 200   // skill has to be over 200
		        	PRINT_BIG ( BMX ) 1000 2 //BMX MISSION
					stunt_course = 0
					LOAD_AND_LAUNCH_MISSION stunt.sc
					flag_bmx_trigger = 1
				ELSE
					IF flag_cycling_skill_help2 = 0
						PRINT_HELP MTBSKI2  //Skill has to be over 200
						flag_cycling_skill_help2 = 1
					ENDIF
				ENDIF           
	        ELSE
				IF flag_cycling_skill_help2 = 1
					flag_cycling_skill_help2 = 0
				ENDIF
			ENDIF

			IF IS_CHAR_IN_MODEL scplayer nrg500
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer stunt_nrg500_x stunt_nrg500_y stunt_nrg500_z 4.0 4.0 3.0 FALSE
					flag_nrg500_trigger = 1
				ENDIF
			ENDIF
			IF IS_CHAR_IN_MODEL scplayer nrg500
			AND flag_nrg500_trigger = 0
				PRINT_BIG ( NRG500 ) 1000 2 //NRG500 MISSION
				stunt_course = 1
				LOAD_AND_LAUNCH_MISSION stunt.sc
				flag_nrg500_trigger = 1            
			ENDIF
			
		ELSE
			flag_hhiker_trigger = 0
			flag_courier_trigger = 0
			flag_mtbike_trigger = 0
			flag_bmx_trigger = 0
			flag_nrg500_trigger = 0
		ENDIF
	ENDIF
ENDIF


GOTO vehicle_oddjob_loop_inner

}

	

// ************************************************************************************************************
// ************************************************************************************************************
// *******************************************END OF MISSIONS**************************************************
// ************************************************************************************************************
// ************************************************************************************************************

// ************************************************************************************************************
// ************************************** SHOWROOM BUYING *****************************************************

// CAR SHOWROOM PURCHASING SCRIPT

showroom_buy_loop:


{

SCRIPT_NAME	BUY1

save_houseprice[0] = 50000
REMOVE_PICKUP save_housepickup[0]
CREATE_FORSALE_PROPERTY_PICKUP propertyX[0] propertyY[0] propertyZ[0] save_houseprice[0] PROP_3 save_housepickup[0]


showroom_buy_loop_inner:

	WAIT mission_trigger_wait_time

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			//IF CAN_PLAYER_START_MISSION player1
				IF HAS_PICKUP_BEEN_COLLECTED save_housepickup[0]
					buying_property_switch = PROP_BUY0
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION propertyX[0] propertyY[0] propertyZ[0] SOUND_PROPERTY_PURCHASED
					LOAD_AND_LAUNCH_MISSION buypro.sc
					TERMINATE_THIS_SCRIPT
				ENDIF
			//ENDIF
		ENDIF
	ENDIF
	
	GOTO showroom_buy_loop_inner
}

// ************************************************************************************************************
// ************************************* ZERO SHOP BUYING *****************************************************

// ZERO PURCHASING SCRIPT

zero_buy_loop:


{

SCRIPT_NAME	BUY2

save_houseprice[1] = 30000
REMOVE_PICKUP save_housepickup[1]
CREATE_FORSALE_PROPERTY_PICKUP propertyX[1] propertyY[1] propertyZ[1] save_houseprice[1] PROP_3 save_housepickup[1]


zero_buy_loop_inner:

	WAIT mission_trigger_wait_time

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			//IF CAN_PLAYER_START_MISSION player1
				IF HAS_PICKUP_BEEN_COLLECTED save_housepickup[1]				
					buying_property_switch = PROP_BUY1
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION propertyX[1] propertyY[1] propertyZ[1] SOUND_PROPERTY_PURCHASED
					LOAD_AND_LAUNCH_MISSION buypro.sc
					TERMINATE_THIS_SCRIPT
				ENDIF
			//ENDIF
		ENDIF
	ENDIF
	
	GOTO zero_buy_loop_inner
}

// ************************************************************************************************************
// ************************************** AIRSTRIP BUYING *****************************************************

// AIRSTRIP PURCHASING SCRIPT

airstrip_buy_loop:


{

SCRIPT_NAME	BUY3

save_houseprice[2] = 80000
REMOVE_PICKUP save_housepickup[2]
CREATE_FORSALE_PROPERTY_PICKUP propertyX[2] propertyY[2] propertyZ[2] save_houseprice[2] PROP_3 save_housepickup[2]


airstrip_buy_loop_inner:

	WAIT mission_trigger_wait_time

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			//IF CAN_PLAYER_START_MISSION player1
				IF HAS_PICKUP_BEEN_COLLECTED save_housepickup[2]
					buying_property_switch = PROP_BUY2
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION propertyX[2] propertyY[2] propertyZ[2] SOUND_PROPERTY_PURCHASED
					LOAD_AND_LAUNCH_MISSION buypro.sc
					TERMINATE_THIS_SCRIPT
				ENDIF
			//ENDIF
		ENDIF
	ENDIF
	
	GOTO airstrip_buy_loop_inner
}



// ************************************************************************************************************
// ************************************** PROPERTY BUYING *****************************************************

VAR_INT save_house_index already_bought_house[32] prorerty_switch[32]

buy_pro_loop:


{

SCRIPT_NAME	BUY_PRO


buy_pro_loop_inner:

	WAIT 0

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			//IF CAN_PLAYER_START_MISSION player1
				IF already_bought_house[save_house_index] = 0
					IF HAS_PICKUP_BEEN_COLLECTED save_housepickup[save_house_index]
						buying_property_switch = prorerty_switch[save_house_index]
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION propertyX[save_house_index] propertyY[save_house_index] propertyZ[save_house_index] SOUND_PROPERTY_PURCHASED
						LOAD_AND_LAUNCH_MISSION buypro.sc
						already_bought_house[save_house_index] = 1
					ENDIF
				ENDIF
			//ENDIF
		ENDIF
	ENDIF	

	save_house_index ++
	IF save_house_index >= 32 
		save_house_index = 3	 	
	ENDIF

GOTO buy_pro_loop_inner
}


// **********************************************************************************************************
// ************************************** IMPOUND SCRIPT ****************************************************

impound_loop:

{


SCRIPT_NAME IMPND_L

impound_loop_inner:

	WAIT 100

	IF IS_PLAYER_PLAYING player1						
		
		IF im_players_city = LEVEL_LOSANGELES
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT impound.sc number_of_instances_of_streamed_script
			IF number_of_instances_of_streamed_script = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1579.4248 -1636.4630 14.5812 120.0 120.0 80.0 FALSE
					impound_area = 1

					STREAM_SCRIPT impound.sc

					IF HAS_STREAMED_SCRIPT_LOADED impound.sc
						START_NEW_STREAMED_SCRIPT impound.sc
					ENDIF												
				ELSE
					IF impound_Area = 1
						MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED impound.sc
						impound_Area = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
			
		IF im_players_city = LEVEL_SANFRANCISCO
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT impound.sc number_of_instances_of_streamed_script
			IF number_of_instances_of_streamed_script = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1624.7710 679.6637 8.5690 120.0 120.0 80.0 FALSE
					IF tlf_underway = 0
						impound_area = 2

						STREAM_SCRIPT impound.sc

						IF HAS_STREAMED_SCRIPT_LOADED impound.sc
							START_NEW_STREAMED_SCRIPT impound.sc
						ENDIF                                                                                                                                       
					ENDIF
				ELSE
					IF tlf_underway = 2 //set in Steve’s mission cleanup
						tlf_underway = 0
					ENDIF
					IF impound_Area = 2
						MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED impound.sc
						impound_Area = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF im_players_city = LEVEL_SANFRANCISCO
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT impound.sc number_of_instances_of_streamed_script
			IF number_of_instances_of_streamed_script = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1624.7710 679.6637 8.5690 120.0 120.0 80.0 FALSE
					impound_area = 2

					STREAM_SCRIPT impound.sc

					IF HAS_STREAMED_SCRIPT_LOADED impound.sc
						START_NEW_STREAMED_SCRIPT impound.sc
					ENDIF												
				ELSE
					IF impound_Area = 2
						MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED impound.sc
						impound_Area = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF im_players_city = LEVEL_LASVEGAS
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT impound.sc number_of_instances_of_streamed_script
			IF number_of_instances_of_streamed_script = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2284.5920 2466.8379 12.2306 120.0 120.0 80.0 FALSE
					impound_area = 3

					STREAM_SCRIPT impound.sc

					IF HAS_STREAMED_SCRIPT_LOADED impound.sc
						START_NEW_STREAMED_SCRIPT impound.sc
					ENDIF												
				ELSE
					IF impound_Area = 3
						MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED impound.sc
						impound_Area = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	


GOTO impound_loop_inner 



}

// **********************************************************************************************************
// ************************************** VALET PARKING *******************************************************

valet_loop:

{


SCRIPT_NAME VALET_L

valet_loop_inner:

	WAIT 0

	IF valet_unlocked = 1 
		IF im_players_city = LEVEL_SANFRANCISCO
			GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT valet.sc number_of_instances_of_streamed_script
			IF number_of_instances_of_streamed_script = 0
				IF IS_PLAYER_PLAYING player1								
					IF IS_CHAR_IN_AREA_2D scplayer -1893.4186 1119.2267 -1617.9149 828.850 FALSE
	   
						val_Area = 2

						STREAM_SCRIPT valet.sc
						IF HAS_STREAMED_SCRIPT_LOADED valet.sc
							START_NEW_STREAMED_SCRIPT valet.sc
						ENDIF												
					ELSE
						IF val_Area = 2
							MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED valet.sc
							val_Area = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


GOTO valet_loop_inner 


}

// **********************************************************************************************************
// ************************************** TRAINS ************************************************************

trains_loop:

{


SCRIPT_NAME TRAINSL

trains_loop_inner:

	WAIT 0


	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_ANY_CAR scplayer 
			IF IS_CHAR_IN_MODEL scplayer streakc
			 
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT trains.sc number_of_instances_of_streamed_script
				IF number_of_instances_of_streamed_script = 0
					STREAM_SCRIPT trains.sc
					IF HAS_STREAMED_SCRIPT_LOADED trains.sc
						START_NEW_STREAMED_SCRIPT trains.sc
					ENDIF												
				ENDIF
			ENDIF
		ENDIF
	ENDIF


GOTO trains_loop_inner 


}

// ************************************************************************************************************
// ******************************************* PLANES TICKET MACHINE ******************************************

VAR_INT created_marker[3] airport_marker[3]

planes_loop:

{


SCRIPT_NAME ADPLANE

planes_loop_inner:

	WAIT 0

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0

			IF im_players_city = LEVEL_LOSANGELES
				IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS 
					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT planes.sc number_of_instances_of_streamed_script
					IF number_of_instances_of_streamed_script = 0
						IF created_marker[0] = 0
							CREATE_USER_3D_MARKER 1685.7 -2238.9 14.0 HUD_COLOUR_ENTRYEXIT_YELLOW airport_marker[0] //LEVEL_LOSANGELES
							created_marker[0] = 1
						ENDIF
						IF CAN_PLAYER_START_MISSION player1
							IF LOCATE_CHAR_ON_FOOT_3D scplayer 1685.7 -2238.9 12.5 1.2 1.2 1.2 FALSE
								SET_PLAYER_CONTROL player1 Off
								GOSUB mini_fade
								STREAM_SCRIPT planes.sc
								flag_dozer_passed_1stime = 1
							//ELSE
								//MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED planes.sc
							ENDIF
							
							IF HAS_STREAMED_SCRIPT_LOADED planes.sc
							AND flag_dozer_passed_1stime = 1
								START_NEW_STREAMED_SCRIPT planes.sc
								flag_dozer_passed_1stime = 0
							ENDIF
						ENDIF												
					ENDIF
				ELSE
					IF created_marker[0] = 1
						REMOVE_USER_3D_MARKER airport_marker[0]
						created_marker[0] = 0
					ENDIF
				ENDIF
			ELSE
				IF created_marker[0] = 1
					REMOVE_USER_3D_MARKER airport_marker[0]
					created_marker[0] = 0
				ENDIF
			ENDIF

			IF im_players_city = LEVEL_SANFRANCISCO
				IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS 
					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT planes.sc number_of_instances_of_streamed_script
					IF number_of_instances_of_streamed_script = 0
						IF created_marker[1] = 0
							CREATE_USER_3D_MARKER -1421.5 -287.2 14.6 HUD_COLOUR_ENTRYEXIT_YELLOW airport_marker[1] //LEVEL_SANFRANCISCO
							created_marker[1] = 1
						ENDIF
						IF CAN_PLAYER_START_MISSION player1
							IF LOCATE_CHAR_ON_FOOT_3D scplayer -1421.5 -287.2 14.6 1.2 1.2 1.2 FALSE
								SET_PLAYER_CONTROL player1 Off
								GOSUB mini_fade
								STREAM_SCRIPT planes.sc
								flag_dozer_passed_1stime = 1
							//ELSE
								//MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED planes.sc
							ENDIF

							IF HAS_STREAMED_SCRIPT_LOADED planes.sc
							AND flag_dozer_passed_1stime = 1
								START_NEW_STREAMED_SCRIPT planes.sc
								flag_dozer_passed_1stime = 0
							ENDIF
						ENDIF												
					ENDIF
				ELSE
					IF created_marker[1] = 1
						REMOVE_USER_3D_MARKER airport_marker[1]
						created_marker[1] = 0
					ENDIF
				ENDIF
			ELSE
				IF created_marker[1] = 1
					REMOVE_USER_3D_MARKER airport_marker[1]
					created_marker[1] = 0
				ENDIF
			ENDIF

			IF im_players_city = LEVEL_LASVEGAS
				IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS 
					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT planes.sc number_of_instances_of_streamed_script
					IF number_of_instances_of_streamed_script = 0
						IF created_marker[2] = 0
							CREATE_USER_3D_MARKER 1663.0 1423.6 11.2 HUD_COLOUR_ENTRYEXIT_YELLOW airport_marker[2] //LEVEL_LASVEGAS
							created_marker[2] = 1
						ENDIF
						IF CAN_PLAYER_START_MISSION player1
							IF LOCATE_CHAR_ON_FOOT_3D scplayer 1663.0 1423.6 11.2 1.2 1.2 1.2 FALSE
								SET_PLAYER_CONTROL player1 Off
								GOSUB mini_fade
								STREAM_SCRIPT planes.sc	
								flag_dozer_passed_1stime = 1								
							//ELSE
								//MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED planes.sc
							ENDIF

							IF HAS_STREAMED_SCRIPT_LOADED planes.sc
							AND flag_dozer_passed_1stime = 1
								START_NEW_STREAMED_SCRIPT planes.sc
								flag_dozer_passed_1stime = 0
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF created_marker[2] = 1
						REMOVE_USER_3D_MARKER airport_marker[2]
						created_marker[2] = 0
					ENDIF
				ENDIF
			ELSE
				IF created_marker[2] = 1
					REMOVE_USER_3D_MARKER airport_marker[2]
					created_marker[2] = 0
				ENDIF
			ENDIF

		ENDIF
	ENDIF

GOTO planes_loop_inner 

//MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED planes.sc
//MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED planes.sc
//MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED planes.sc

}

// ******************************COLLECTIBLES******************************************************************************

VAR_INT Returned_oysters Returned_shoehorses Returned_snapshots	Returned_tags game_complete	
VAR_INT all_tags_pickup1 all_tags_pickup2 all_tags_pickup3 all_tags_pickup4
VAR_INT all_photos_pickup1 all_photos_pickup2 all_photos_pickup3 all_photos_pickup4
VAR_INT all_horse_pickup1 all_horse_pickup2 all_horse_pickup3 all_horse_pickup4
VAR_FLOAT Return_progress_Percentage 

collectibes_loop:


{												
	
												
SCRIPT_NAME COLLS							

collectibes_loop_inner:

	WAIT 3000

	GET_PROGRESS_PERCENTAGE Return_progress_Percentage


	IF returned_oysters_flag = 0
        GET_INT_STAT OYSTERS_COLLECTED Returned_oysters
        IF returned_oysters = 50
            PLAY_MISSION_PASSED_TUNE 2
            PLAYER_MADE_PROGRESS 1
            SET_INT_STAT UNDERWATER_BREATH_STAMINA 1000
            //--- Girlfriends bonus: all dead or dumped girls come back on the map 
            IF iGFLikesPlayer[SUZIE] <= 0
            	iGFLikesPlayer[SUZIE] = 45
            ENDIF
            IF iGFLikesPlayer[MICHELLE] <= 0
            	iGFLikesPlayer[MICHELLE] = 45
            ENDIF
            IF iGFLikesPlayer[BARBARA] <= 0
            	iGFLikesPlayer[BARBARA] = 45
            ENDIF
            IF iGFLikesPlayer[KYLIE] <= 0
            	iGFLikesPlayer[KYLIE] = 45
            ENDIF

            //--- All girls love the player even if he smells   
            iGFDesiredSexAppeal[COOCHIE] = -100
            iGFDesiredSexAppeal[MICHELLE] = -100
            iGFDesiredSexAppeal[BARBARA] = -100
            iGFDesiredSexAppeal[KYLIE] = -100
            iGFDesiredSexAppeal[SUZIE] = -100
            iGFDesiredSexAppeal[MILLIE] = -100
            //---  All girls are his bitches now
            iGFSelfRespect[COOCHIE] = 1
            iGFSelfRespect[MICHELLE] = 1
            iGFSelfRespect[BARBARA] = 1
            iGFSelfRespect[KYLIE] = 1
            iGFSelfRespect[SUZIE] = 1
            iGFSelfRespect[MILLIE] = 1
            CLEAR_HELP
            PRINT_HELP OYST100
            returned_oysters_flag = 1
        ENDIF
    ENDIF

	IF flag_returned_shoehorses = 0
		GET_INT_STAT HORSESHOES_COLLECTED Returned_shoehorses
		IF Returned_shoehorses = 50
			PLAY_MISSION_PASSED_TUNE 2
			PLAYER_MADE_PROGRESS 1
			CREATE_PICKUP_WITH_AMMO M4 PICKUP_ON_STREET_SLOW 60 2021.8792 1001.4669 10.3203 all_horse_pickup1 
			CREATE_PICKUP_WITH_AMMO MP5LNG PICKUP_ON_STREET_SLOW 120 2025.2858 1001.4957 10.3203 all_horse_pickup2 
			CREATE_PICKUP_WITH_AMMO SHOTGSPA PICKUP_ON_STREET_SLOW 120 2021.3268 1013.3495 10.3203 all_horse_pickup3 
			CREATE_PICKUP_WITH_AMMO SATCHEL PICKUP_ON_STREET_SLOW 20 2023.7754 1013.5269 10.5203 all_horse_pickup4
			CLEAR_HELP
			PRINT_HELP LUCK100
			flag_returned_shoehorses = 1
		ENDIF
	ENDIF
	IF flag_returned_snapshots = 0
		GET_INT_STAT SNAPSHOTS_TAKEN Returned_snapshots
		IF Returned_snapshots = 50
			PLAY_MISSION_PASSED_TUNE 2
			PLAYER_MADE_PROGRESS 1
			CREATE_PICKUP_WITH_AMMO SNIPER PICKUP_ON_STREET_SLOW 60 -2035.7729 139.4337 28.3359 all_photos_pickup1 
			CREATE_PICKUP_WITH_AMMO MICRO_UZI PICKUP_ON_STREET_SLOW 120 -2038.4298 139.6281 28.3359 all_photos_pickup2 
			CREATE_PICKUP_WITH_AMMO CHROMEGUN PICKUP_ON_STREET_SLOW 120 -2038.6641 137.4694 28.3359 all_photos_pickup3 
			CREATE_PICKUP_WITH_AMMO GRENADE PICKUP_ON_STREET_SLOW 20 -2035.4735 137.2511 28.3359 all_photos_pickup4
			CLEAR_HELP
			PRINT_HELP FOTO100
			flag_returned_snapshots = 1
		ENDIF														   
	ENDIF															   
	IF flag_returned_tags = 0
		FIND_NUMBER_TAGS_TAGGED	Returned_tags
		IF Returned_tags = 100
			PLAY_MISSION_PASSED_TUNE 2
			PLAYER_MADE_PROGRESS 1
			SET_GANG_WEAPONS GANG_GROVE WEAPONTYPE_DESERT_EAGLE WEAPONTYPE_MP5 WEAPONTYPE_KNIFE
			CREATE_PICKUP_WITH_AMMO AK47 PICKUP_ON_STREET_SLOW 120 2499.3901 -1707.4629 1014.2496 all_tags_pickup1
			CREATE_PICKUP_WITH_AMMO TEC9 PICKUP_ON_STREET_SLOW 120 2499.5139 -1709.6403 1014.2496 all_tags_pickup2
			CREATE_PICKUP_WITH_AMMO SAWNOFF PICKUP_ON_STREET_SLOW 60 2493.4911 -1708.2368 1014.9316 all_tags_pickup3
			CREATE_PICKUP_WITH_AMMO MOLOTOV PICKUP_ON_STREET_SLOW 20 2493.5529 -1706.8634 1015.1316 all_tags_pickup4
			CLEAR_HELP
			PRINT_HELP TAG_100
			flag_returned_tags = 1
		ENDIF
	ENDIF

	IF game_complete = 0
		IF Return_progress_Percentage >= 100.0
			IF IS_PLAYER_PLAYING player1
				SWITCH_CAR_GENERATOR tank_gen1 101
				SWITCH_CAR_GENERATOR hyfra_gen1 101
				ADD_SCORE player1 1000000
				SET_INT_STAT UNDERWATER_BREATH_STAMINA 1000
				SET_INT_STAT STAMINA 1000
				SET_INT_STAT SEX_APPEAL 1000
				SET_INT_STAT SEX_APPEAL_CLOTHES 1000
				SET_INT_STAT RESPECT 1000
				SET_INT_STAT RESPECT_GIRLFRIEND	1000
				SET_INT_STAT RESPECT_CLOTHES 1000
				SET_INT_STAT CYCLE_SKILL 1000
				WAIT 3000
				CLEAR_HELP
				PRINT_HELP GAME100
				game_complete = 1
			ENDIF
		ENDIF
	ENDIF

GOTO collectibes_loop_inner

}

// ******************************TERITORY PICKUP******************************************************************************


VAR_INT terminted_territory_pickup

territory_cash_loop:
{
//	Should be called before main loop

SCRIPT_NAME TCASH

territory_cash_loop_inner:
	
	WAIT 20000

	IF terminted_territory_pickup = 1
		REMOVE_PICKUP territory_pickup
		terminted_territory_pickup = 0
		TERMINATE_THIS_SCRIPT
	ENDIF

	GET_TERRITORY_UNDER_CONTROL_PERCENTAGE player_territory_owned
	 
	IF Return_cities_passed > 1
		territory_cash = player_territory_owned * 100
	ELSE
		territory_cash = player_territory_owned * 50
	ENDIF
	 
	UPDATE_PICKUP_MONEY_PER_DAY territory_pickup territory_cash

GOTO territory_cash_loop_inner

}


// ******************************GIRLFRIEND PICKUP******************************************************************************

VAR_INT millies_keycard_pickup millies_keycard_pickup_flag not_entered_millies_house millies_double_ender millie_marker[2] created_millie_marker

millies_keycard_loop:
{

GET_INT_STAT GIRLFRIEND_MILLIE millies_like_stat
IF millies_like_stat >= MILLIE_LIKES_PLAYER_REQUIRED_FOR_KEYCARD
	CREATE_PICKUP Gun_dildo1 PICKUP_ONCE 345.2063 308.9788 998.6484 millies_double_ender //DILDO
ENDIF
SET_CLOSEST_ENTRY_EXIT_FLAG 345.5621 306.2212 10.0 ENTRYEXITS_FLAG_ENABLED FALSE
CREATE_USER_3D_MARKER 2037.3492 2722.0714 12.0281 HUD_COLOUR_ENTRYEXIT_YELLOW millie_marker[0]
CREATE_USER_3D_MARKER 343.9969 305.1040 999.6557 HUD_COLOUR_ENTRYEXIT_YELLOW millie_marker[1]

//	Should be called before main loop
SCRIPT_NAME KEYCARD

millies_keycard_loop_inner:
	
	WAIT 0

	GET_AREA_VISIBLE main_visible_area

	IF main_visible_area = 0 
		not_entered_millies_house = 0
	ELSE
		not_entered_millies_house = 1
	ENDIF

	IF IS_PLAYER_PLAYING player1
		IF not_entered_millies_house = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 2037.3492 2722.0714 9.8281 1.2 1.2 2.0 FALSE 
				SET_PLAYER_CONTROL player1 OFF
				GOSUB mini_fade
				SET_AREA_VISIBLE 6
				REQUEST_COLLISION 345.5621 306.2212
				LOAD_SCENE 345.5621 306.2212 998.1484 
				SET_EXTRA_COLOURS 1 FALSE
				SET_PLAYER_IS_IN_STADIUM TRUE
				IF IS_PLAYER_PLAYING player1
					SET_CHAR_AREA_VISIBLE scplayer 6
					SET_CHAR_COORDINATES scplayer 345.5621 306.2212 998.4484
					SET_CHAR_HEADING scplayer 260.0
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
				ENDIF
				WAIT 1000
				DO_FADE 500 FADE_IN
				IF IS_PLAYER_PLAYING player1
					SET_PLAYER_CONTROL player1 ON
				ENDIF
				not_entered_millies_house = 1
			ENDIF
		ELSE
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 343.9969 305.1040 998.1557 1.2 1.2 2.0 FALSE 
				SET_PLAYER_CONTROL player1 OFF
				GOSUB mini_fade
				SET_AREA_VISIBLE 0
				REQUEST_COLLISION 2037.5408 2727.6865
				LOAD_SCENE 2037.5408 2727.6865 9.8281 
				CLEAR_EXTRA_COLOURS FALSE
				SET_PLAYER_IS_IN_STADIUM FALSE
				IF IS_PLAYER_PLAYING player1
					SET_CHAR_AREA_VISIBLE scplayer 0
					SET_CHAR_COORDINATES scplayer 2037.5408 2727.6865 9.8281
					SET_CHAR_HEADING scplayer 0.0
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
				ENDIF
				WAIT 1000
				DO_FADE 500 FADE_IN
				IF IS_PLAYER_PLAYING player1
					SET_PLAYER_CONTROL player1 ON
				ENDIF
				not_entered_millies_house = 0
			ENDIF
		ENDIF
	ELSE
		SET_PLAYER_IS_IN_STADIUM FALSE
	ENDIF

	IF HAS_PICKUP_BEEN_COLLECTED millies_keycard_pickup
		IF millies_keycard_pickup_flag = 0
			PRINT_NOW (GOTKEY) 6000 1 //You have acquired the keycard for the casino heist
			REMOVE_BLIP Thekeycard_contact_blip
			keycard_aquired_from_millie = 1
			millies_keycard_pickup_flag = 1
		ENDIF
	ENDIF

	GET_AREA_VISIBLE main_visible_area
	IF millies_keycard_pickup_flag = 1
	AND	main_visible_area = 0
		REMOVE_USER_3D_MARKER millie_marker[0]
		REMOVE_USER_3D_MARKER millie_marker[1]
		TERMINATE_THIS_SCRIPT
	ENDIF


GOTO millies_keycard_loop_inner


}

// ***********************************************************************************************************
// **********************************************2player minigames********************************************

VAR_INT Tplayer_pickup[16] recreate_tplay_pickup recreate_gfriend_pickup[6]

Tplay_mission_loop:


{


SCRIPT_NAME TPLAYER

Tplay_mission_loop_inner:

WAIT 75

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0

			IF recreate_tplay_pickup = 0
				GOSUB remove_2player_pickups
				CREATE_PICKUP killfrenzy2plyr PICKUP_2P 1481.9551 -1656.1458 13.5469 Tplayer_pickup[0] //2player Ram
				CREATE_PICKUP killfrenzy2plyr PICKUP_2P 1196.4912 249.3210 19.0618 Tplayer_pickup[1] //2player bike
				CREATE_PICKUP killfrenzy2plyr PICKUP_2P -2102.8484 653.8868 51.8671 Tplayer_pickup[2] //2player cars
				CREATE_PICKUP killfrenzy2plyr PICKUP_2P -252.9156 2583.7788 63.0703 Tplayer_pickup[3] //2player heli
				CREATE_PICKUP killfrenzy2plyr PICKUP_2P 2510.6331 1207.9175 10.3281 Tplayer_pickup[4] //2player peds
				CREATE_PICKUP twoplayer PICKUP_2P 2069.3376 -1556.9296 12.9243 Tplayer_pickup[5] //Run-around LS
				CREATE_PICKUP twoplayer PICKUP_2P 2138.1689 1483.5952 10.3203 Tplayer_pickup[6] //Run-around LV
				CREATE_PICKUP twoplayer PICKUP_2P -2197.6665 292.0621 34.6230 Tplayer_pickup[7] //Run-around SF
				CREATE_PICKUP twoplayer PICKUP_2P -1520.8643 2608.2073 55.3437 Tplayer_pickup[8] //Run-around DE
				CREATE_PICKUP twoplayer PICKUP_2P 711.0688 -569.3774 15.8359 Tplayer_pickup[9] //Run-around CO
				recreate_tplay_pickup = 1
			ENDIF
			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[0] //2player Ram
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY1 ) 1000 2 
	            LOAD_AND_LAUNCH_MISSION ram_2p.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[1] //2player bike
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY2 ) 1000 2 
	            LOAD_AND_LAUNCH_MISSION bike_2p.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[2] //2player cars
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY3 ) 1000 2 
	            LOAD_AND_LAUNCH_MISSION cars_2p.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF
				
			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[3] //2player heli
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY4 ) 1000 2 
				LOAD_AND_LAUNCH_MISSION heli_2p.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[4] //2player peds
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY5 ) 1000 2 
				LOAD_AND_LAUNCH_MISSION peds_2p.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[5] //Run-around LS
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY6 ) 1000 2 
				LOAD_AND_LAUNCH_MISSION run_LS.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[6] //Run-around LV
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY7 ) 1000 2 
				LOAD_AND_LAUNCH_MISSION run_LV.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[7] //Run-around SF
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY8 ) 1000 2 
				LOAD_AND_LAUNCH_MISSION run_SF.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[8] //Run-around DE
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY9 ) 1000 2 
				LOAD_AND_LAUNCH_MISSION run_DE.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[9] //Run-around CO
	            flag_player_on_mission = 1
	            GOSUB mini_fade
	            PRINT_BIG ( TPLAY10 ) 1000 2 
				LOAD_AND_LAUNCH_MISSION run_CO.sc //PAUL D
				recreate_tplay_pickup = 0
			ENDIF

			IF IS_BIT_SET iGFAvailableFor2Player MILLIE
				IF recreate_gfriend_pickup[0] = 0
					CREATE_PICKUP twoplayer PICKUP_2P 2024.5217 2731.1960 10.3281 Tplayer_pickup[10] 
					recreate_gfriend_pickup[0] = 1
				ENDIF
				IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[10] 
					flag_player_on_mission = 1
		            GOSUB mini_fade
		            //PRINT_BIG ( TPLAY10 ) 1000 2 
					LOAD_AND_LAUNCH_MISSION r_mily.sc //PAUL D
					recreate_tplay_pickup = 0
				ENDIF
			ELSE
				IF recreate_gfriend_pickup[0] = 1
					REMOVE_PICKUP Tplayer_pickup[10]
					recreate_gfriend_pickup[0] = 0
				ENDIF
			ENDIF

			IF IS_BIT_SET iGFAvailableFor2Player SUZIE
				IF recreate_gfriend_pickup[1] = 0
					CREATE_PICKUP twoplayer PICKUP_2P -2576.7827 1151.9084 55.2333 Tplayer_pickup[11] 
					recreate_gfriend_pickup[1] = 1
				ENDIF
				IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[11]
					flag_player_on_mission = 1
		            GOSUB mini_fade
		            //PRINT_BIG ( TPLAY10 ) 1000 2 
					LOAD_AND_LAUNCH_MISSION r_suzi.sc //PAUL D
					recreate_tplay_pickup = 0
				ENDIF
			ELSE
				IF recreate_gfriend_pickup[1] = 1
					REMOVE_PICKUP Tplayer_pickup[11]
					recreate_gfriend_pickup[1] = 0
				ENDIF
			ENDIF

			IF IS_BIT_SET iGFAvailableFor2Player BARBARA
				IF recreate_gfriend_pickup[2] = 0	
					CREATE_PICKUP twoplayer PICKUP_2P -1390.1707 2637.5149 55.5000 Tplayer_pickup[12]
					recreate_gfriend_pickup[2] = 1
				ENDIF 
				IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[12]
					flag_player_on_mission = 1
		            GOSUB mini_fade
		            //PRINT_BIG ( TPLAY10 ) 1000 2 
					LOAD_AND_LAUNCH_MISSION r_barb.sc //PAUL D
					recreate_tplay_pickup = 0
				ENDIF
			ELSE
				IF recreate_gfriend_pickup[2] = 1
					REMOVE_PICKUP Tplayer_pickup[12]
					recreate_gfriend_pickup[2] = 0
				ENDIF
			ENDIF

			IF IS_BIT_SET iGFAvailableFor2Player KYLIE
				IF recreate_gfriend_pickup[3] = 0	
					CREATE_PICKUP twoplayer PICKUP_2P -381.8657 -1426.2695 25.5000 Tplayer_pickup[13]
					recreate_gfriend_pickup[3] = 1
				ENDIF 
				IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[13]
					flag_player_on_mission = 1
		            GOSUB mini_fade
		            //PRINT_BIG ( TPLAY10 ) 1000 2 
					LOAD_AND_LAUNCH_MISSION r_kylie.sc //PAUL D
					recreate_tplay_pickup = 0
				ENDIF
			ELSE
				IF recreate_gfriend_pickup[3] = 1
					REMOVE_PICKUP Tplayer_pickup[13]
					recreate_gfriend_pickup[3] = 0
				ENDIF
			ENDIF


			IF IS_BIT_SET iGFAvailableFor2Player COOCHIE
				IF recreate_gfriend_pickup[4] = 0
					CREATE_PICKUP twoplayer PICKUP_2P 2402.4358 -1715.4941 14.0000 Tplayer_pickup[14] 
					recreate_gfriend_pickup[4] = 1
				ENDIF
				IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[14]
					flag_player_on_mission = 1
		            GOSUB mini_fade
		            //PRINT_BIG ( TPLAY10 ) 1000 2 
					LOAD_AND_LAUNCH_MISSION r_coch.sc //PAUL D
					recreate_tplay_pickup = 0
				ENDIF
			ELSE
				IF recreate_gfriend_pickup[4] = 1
					REMOVE_PICKUP Tplayer_pickup[14]
					recreate_gfriend_pickup[4] = 0
				ENDIF
			ENDIF

			IF IS_BIT_SET iGFAvailableFor2Player MICHELLE
				IF recreate_gfriend_pickup[5] = 0
					CREATE_PICKUP twoplayer PICKUP_2P -1803.6143 1199.3320 25.0000 Tplayer_pickup[15]
					recreate_gfriend_pickup[5] = 1
				ENDIF
				IF HAS_PICKUP_BEEN_COLLECTED Tplayer_pickup[15]
					flag_player_on_mission = 1
		            GOSUB mini_fade
		            //PRINT_BIG ( TPLAY10 ) 1000 2 
					LOAD_AND_LAUNCH_MISSION r_mich.sc //PAUL D
					recreate_tplay_pickup = 0
				ENDIF
			ELSE
				IF recreate_gfriend_pickup[5] = 1
					REMOVE_PICKUP Tplayer_pickup[15]
					recreate_gfriend_pickup[5] = 0
				ENDIF
			ENDIF

		ELSE
			IF recreate_tplay_pickup = 1
				GOSUB remove_2player_pickups
				recreate_tplay_pickup = 0
			ENDIF
		ENDIF
	ELSE
		IF recreate_tplay_pickup = 1
			GOSUB remove_2player_pickups
			recreate_tplay_pickup = 0
		ENDIF
	ENDIF
	
GOTO Tplay_mission_loop_inner

}

remove_2player_pickups:

	REMOVE_PICKUP Tplayer_pickup[0]
	REMOVE_PICKUP Tplayer_pickup[1]
	REMOVE_PICKUP Tplayer_pickup[2]
	REMOVE_PICKUP Tplayer_pickup[3]
	REMOVE_PICKUP Tplayer_pickup[4]
	REMOVE_PICKUP Tplayer_pickup[5]
	REMOVE_PICKUP Tplayer_pickup[6]
	REMOVE_PICKUP Tplayer_pickup[7]
	REMOVE_PICKUP Tplayer_pickup[8]
	REMOVE_PICKUP Tplayer_pickup[9]
	REMOVE_PICKUP Tplayer_pickup[10]
	REMOVE_PICKUP Tplayer_pickup[11]
	REMOVE_PICKUP Tplayer_pickup[12]
	REMOVE_PICKUP Tplayer_pickup[13]
	REMOVE_PICKUP Tplayer_pickup[14]
	REMOVE_PICKUP Tplayer_pickup[15]

RETURN

// **********************************GROVE SAVE LOOP*********************************************

VAR_INT remove_grove_pickup[18] grove_save_pickup[18] save_index created_save_blips	number_of_save_icons
VAR_FLOAT save_pickupX[18] save_pickupY[18] save_pickupZ[18] 
VAR_FLOAT save_playerX[18] save_playerY[18] save_playerZ[18] save_playerH[18]

																			 
grove_save_loop:
{
//	Should be called before main loop

SCRIPT_NAME PSAVE1

number_of_save_icons = 13 //18

grove_save_loop_inner:

	WAIT 0
 
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF flag_on_courier_mission = 0
			AND NOT IS_BIT_SET iDateReport MEETING_IN_PROGRESS
			AND NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS

				IF created_save_blips = 0
					
					GOSUB remove_all_save_pickups
					CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[0] save_pickupY[0] save_pickupZ[0] grove_save_pickup[0]
					CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[1] save_pickupY[1] save_pickupZ[1] grove_save_pickup[1]											 
					CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[2] save_pickupY[2] save_pickupZ[2] grove_save_pickup[2]
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
					IF number_of_save_icons >= 14
						CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[13] save_pickupY[13] save_pickupZ[13] grove_save_pickup[13] //BADLANDS TRAILOR//remove
					ENDIF
					IF number_of_save_icons >= 15
						CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[14] save_pickupY[14] save_pickupZ[14] grove_save_pickup[14] //CATS LODGE//remove
					ENDIF
					IF number_of_save_icons >= 16
						CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[15] save_pickupY[15] save_pickupZ[15] grove_save_pickup[15] //SAN FRAN GARAGE//remove
					ENDIF
					IF number_of_save_icons >= 17
						CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[16] save_pickupY[16] save_pickupZ[16] grove_save_pickup[16] //TORENOS RANCH//remove
					ENDIF
					IF number_of_save_icons >= 18
						CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[17] save_pickupY[17] save_pickupZ[17] grove_save_pickup[17] //TRIAD CASINO//remove					
					ENDIF
																						 
					created_save_blips = 1
				ENDIF

				IF HAS_PICKUP_BEEN_COLLECTED grove_save_pickup[save_index]
					GOSUB save_the_game
					IF IS_PLAYER_PLAYING player1
						REMOVE_PICKUP grove_save_pickup[save_index]
						CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[save_index] save_pickupY[save_index] save_pickupZ[save_index] grove_save_pickup[save_index]
						CLEAR_AREA save_playerX[save_index] save_playerY[save_index] save_playerZ[save_index] 1.0 TRUE
						SET_CHAR_COORDINATES scplayer save_playerX[save_index] save_playerY[save_index] save_playerZ[save_index] 
						SET_CHAR_HEADING scplayer save_playerH[save_index]
						WAIT 0
						DO_FADE 1000 FADE_IN
					ENDIF
					GOSUB save_the_game2
					created_save_blips = 0
				ENDIF
			ELSE
				IF created_save_blips = 1
					GOSUB remove_all_save_pickups
					created_save_blips = 0
				ENDIF
			ENDIF
		ELSE
			IF created_save_blips = 1
				GOSUB remove_all_save_pickups
				created_save_blips = 0
			ENDIF
		ENDIF		

		save_index ++
		IF save_index >= number_of_save_icons 
			save_index = 0	 	
		ENDIF

 	ENDIF // IF IS_PLAYER_PLAYING player1


GOTO grove_save_loop_inner

}

//SAVE GAME SETUP*************************************************************************************

remove_all_save_pickups:

	REMOVE_PICKUP grove_save_pickup[0]
	REMOVE_PICKUP grove_save_pickup[1]
	REMOVE_PICKUP grove_save_pickup[2]
	REMOVE_PICKUP grove_save_pickup[3]
	REMOVE_PICKUP grove_save_pickup[4]
	REMOVE_PICKUP grove_save_pickup[5]
	REMOVE_PICKUP grove_save_pickup[6]
	REMOVE_PICKUP grove_save_pickup[7]
	REMOVE_PICKUP grove_save_pickup[8]
	REMOVE_PICKUP grove_save_pickup[9]
	REMOVE_PICKUP grove_save_pickup[10]
	REMOVE_PICKUP grove_save_pickup[11]
	REMOVE_PICKUP grove_save_pickup[12]
	REMOVE_PICKUP grove_save_pickup[13]
	REMOVE_PICKUP grove_save_pickup[14]
	REMOVE_PICKUP grove_save_pickup[15]
	REMOVE_PICKUP grove_save_pickup[16]
	REMOVE_PICKUP grove_save_pickup[17]

RETURN

save_the_game:

	flag_player_on_mission = 1
	SET_PLAYER_CONTROL player1 Off
		
	ACTIVATE_SAVE_MENU //THE GAME SAVES/RE-LOADS HERE!!!!!
							
	//WAIT 0
								
	WHILE NOT HAS_SAVE_GAME_FINISHED
		WAIT 0

	ENDWHILE

	SET_FADING_COLOUR 0 0 0

	DO_FADE 1000 FADE_OUT

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_CONTROL player1 OFF
	ENDIF

RETURN

save_the_game2:

	IF IS_PLAYER_PLAYING player1
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
	ENDIF

	WAIT 500

	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_CONTROL player1 on
		flag_player_on_mission = 0
	ENDIF

RETURN


//STORY MISSION SETUP********************************************************************************

cutscene_fading_status:

  	//WAIT 1500

  	SET_FADING_COLOUR 0 0 0
	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
			CLEAR_PRINTS
			CLEAR_HELP
	ENDWHILE

	IF IS_PLAYER_PLAYING player1
		DO_FADE 0 FADE_OUT
		RESTORE_CAMERA_JUMPCUT
		CLEAR_CHAR_TASKS scplayer
		DO_FADE 0 FADE_OUT
	ENDIF
  
RETURN


//SHORT SCRIPTED CUTS*********************************************************************************

mother_script_cut:

	IF IS_PLAYER_PLAYING player1	
		CLEAR_HELP
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION 2503.6008 -1676.7875 13.9236 0.0 0.0 0.0 //Mothers house
		POINT_CAMERA_AT_POINT 2503.2595 -1677.7181 14.0548 JUMP_CUT
		CLEAR_AREA 2495.3652 -1690.7665 13.7734 1.0 TRUE
		CLEAR_AREA 2503.2595 -1677.7181 14.0548 2.0 TRUE
		CLEAR_AREA introX introY introZ	2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2495.3652 -1690.7665 13.7734 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


sweet_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		
		SET_PLAYER_CONTROL player1 OFF
		
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 2512.2329 -1673.5321 12.6635 0.0 0.0 0.0 //Sweets house
		POINT_CAMERA_AT_POINT 2513.1387 -1673.8250 12.9696 JUMP_CUT
		CLEAR_AREA 2513.1387 -1673.8250 12.9696 2.0 TRUE
		CLEAR_AREA 2521.35 -1678.83 14.32 1.0 TRUE
		CLEAR_AREA sweetX sweetY sweetZ	2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2521.35 -1678.83 14.32 PEDMOVE_WALK 3000
		
		GOSUB cutscene_fading_status
	ENDIF

RETURN


ryder_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 2451.1482 -1677.7863 16.3581 0.0 0.0 0.0 //Ryder house
		POINT_CAMERA_AT_POINT 2451.5637 -1678.6765 16.1721 JUMP_CUT
		CLEAR_AREA 2451.5637 -1678.6765 16.1721 2.0 TRUE
		CLEAR_AREA 2459.44 -1691.58 12.58 1.0 TRUE
		CLEAR_AREA ryderX ryderY ryderZ 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2459.44 -1691.58 12.58 PEDMOVE_WALK 3000
										   
		GOSUB cutscene_fading_status
	ENDIF

RETURN


smoke_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 2075.6997 -1687.8783 16.0883 0.0 0.0 0.0 //Smoke house
		POINT_CAMERA_AT_POINT 2074.9836 -1688.5559 15.9215 JUMP_CUT
		CLEAR_AREA 2065.4 -1703.4 13.1 1.0 TRUE
		CLEAR_AREA 2074.9836 -1688.5559 15.9215 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2065.4 -1703.4 13.1 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


crash_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 1054.6445 -1312.2812 14.9353 0.0 0.0 0.0 //Crash house
		POINT_CAMERA_AT_POINT 1054.2712 -1313.2002 15.0617 JUMP_CUT
		CLEAR_AREA 1038.04 -1339.23 12.55 1.0 TRUE
		CLEAR_AREA 1054.2712 -1313.2002 15.0617 2.0 TRUE
		CLEAR_AREA crashX crashY crashZ 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 1038.04 -1339.23 12.55 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


strap_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 825.1696 -1636.5719 23.1761 0.0 0.0 0.0 //Fast food house
		POINT_CAMERA_AT_POINT 824.3510 -1636.0514 22.9332 JUMP_CUT
		CLEAR_AREA 793.59 -1625.07 12.38 1.0 TRUE
		CLEAR_AREA 824.3510 -1636.0514 22.9332 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 793.59 -1625.07 12.38 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


strap_script_cut2:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 2486.8477 -1660.3274 12.5441 0.0 0.0 0.0 //House Party
		POINT_CAMERA_AT_POINT 2486.8464 -1659.3627 12.8073 JUMP_CUT
		CLEAR_AREA 2486.94 -1646.76 13.07 1.0 TRUE
		CLEAR_AREA 2486.8464 -1659.3627 12.8073 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2486.94 -1646.76 13.07 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


cesar_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 1792.8767 -2105.3684 17.6519 0.0 0.0 0.0 //Cesar house
		POINT_CAMERA_AT_POINT 1793.1516 -2106.3013 17.4194 JUMP_CUT
		CLEAR_AREA 1793.1516 -2106.3013 17.4194 2.0 TRUE
		//IF NOT flag_cesar_mission_counter = 0
			//TASK_GO_STRAIGHT_TO_COORD scplayer 1793.89 -2124.42 12.56 PEDMOVE_WALK 3000
		//ENDIF

		GOSUB cutscene_fading_status
	ENDIF

RETURN


motel_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -2187.8699 -2269.8076 29.4158 0.0 0.0 0.0 //Motel
		POINT_CAMERA_AT_POINT -2188.3757 -2269.0212 29.7703 JUMP_CUT
		CLEAR_AREA -2194.3713 -2256.4980 29.6841 1.0 TRUE
		CLEAR_AREA -2188.3757 -2269.0212 29.7703 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -2194.3713 -2256.4980 29.6841 PEDMOVE_WALK 3000
		GOSUB cutscene_fading_status
	ENDIF

RETURN

truthfarm_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -919.0773 -1715.6240 78.4517 0.0 0.0 0.0 //Motel
	   	POINT_CAMERA_AT_POINT -920.0563 -1715.8236 78.4112 JUMP_CUT
		CLEAR_AREA -932.2975 -1718.8911 76.5703 1.0 TRUE
		CLEAR_AREA -920.0563 -1715.8236 78.4112 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -932.2975 -1718.8911 76.5703 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


garage_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -2010.9739 154.9329 33.4883 0.0 0.0 0.0 //Triad Casino house
		POINT_CAMERA_AT_POINT -2011.9657 154.9767 33.3685 JUMP_CUT
		CLEAR_AREA -2034.5591 148.8178 27.8359 1.0 TRUE
		CLEAR_AREA -2011.9657 154.9767 33.3685 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -2034.5591 148.8178 27.8359 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


steal_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -2031.3595 196.2776 34.8120 0.0 0.0 0.0 //Triad Casino house
		POINT_CAMERA_AT_POINT -2031.6392 195.3477 34.5735 JUMP_CUT
		CLEAR_AREA -2038.5095 178.4871 27.8359 1.0 TRUE
		CLEAR_AREA -2031.6392 195.3477 34.5735 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -2038.5095 178.4871 27.8359 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


wuzi_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -2129.1858 640.2543 72.0002 0.0 0.0 0.0 //Triad Casino house
		POINT_CAMERA_AT_POINT -2130.1023 640.2417 71.6005 JUMP_CUT
		CLEAR_AREA -2156.56 645.08 51.35 1.0 TRUE
		CLEAR_AREA -2130.1023 640.2417 71.6005 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -2156.56 645.08 51.35 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


triad_casino_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 1979.0204 1004.7916 995.4213 0.0 0.0 0.0 //Triad Casino house
		POINT_CAMERA_AT_POINT 1978.5657 1003.9200 995.2380 JUMP_CUT
		CLEAR_AREA 1963.4 972.2 993.4 1.0 TRUE
		CLEAR_AREA 1979.0204 1004.7916 995.4213 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 1963.4 972.2 993.4 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN

triad_casino_script_cut2:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 1954.5709 1041.4943 993.1794 0.0 0.0 0.0 //Triad Casino house
		POINT_CAMERA_AT_POINT 1954.9624 1042.4143 993.1676 JUMP_CUT
		CLEAR_AREA 1963.5 1063.3 993.4 1.0 TRUE
		CLEAR_AREA 1954.9624 1042.4143 993.1676 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 1963.5 1063.3 993.4 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN

triad_casino_script_cut3:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 2073.9473 991.3126 17.4461 0.0 0.0 0.0 //Triad Casino house
		POINT_CAMERA_AT_POINT 2072.9534 991.4046 17.5078 JUMP_CUT
		CLEAR_AREA 2020.0 1008.83 9.82 1.0 TRUE
		CLEAR_AREA 2072.9534 991.4046 17.5078 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2020.0 1008.83 9.82 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN

pleasure_domes_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -2601.6772 1386.1322 6.8077 0.0 0.0 0.0 //Pleasure domes house
		POINT_CAMERA_AT_POINT -2602.3279 1386.8549 7.0409 JUMP_CUT
		CLEAR_AREA -2624.1951 1410.7644 6.1015 1.0 TRUE
		CLEAR_AREA -2602.3279 1386.8549 7.0409 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -2624.1951 1410.7644 6.1015 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN

ranch_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -690.6180 906.0824 13.0468 0.0 0.0 0.0 //Torino Ranch
		POINT_CAMERA_AT_POINT -690.5204 907.0766 13.0919 JUMP_CUT
		CLEAR_AREA -684.9820 927.3704 12.6293 1.0 TRUE
		CLEAR_AREA -690.5204 907.0766 13.0919 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -684.9820 927.3704 12.6293 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN

airstrip_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 427.1954 2527.6433 19.4052 0.0 0.0 0.0 //Pilot School
		POINT_CAMERA_AT_POINT 426.2472 2527.9556 19.4635 JUMP_CUT
		CLEAR_AREA 426.2472 2527.9556 19.4635 2.0 TRUE
		//TASK_GO_STRAIGHT_TO_COORD scplayer -684.9820 927.3704 12.6293 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN

airstrip_script_cut2:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 330.1037 2587.7505 18.3215 0.0 0.0 0.0 //Air strip
		POINT_CAMERA_AT_POINT 330.0515 2586.7527 18.3620 JUMP_CUT
		CLEAR_AREA 330.0515 2586.7527 18.3620 2.0 TRUE
		//TASK_GO_STRAIGHT_TO_COORD scplayer -684.9820 927.3704 12.6293 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


mafia_casino_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 2257.1345 1587.1329 1010.0352 0.0 0.0 0.0 //Mafia Casino house
		POINT_CAMERA_AT_POINT 2257.2671 1588.1194 1009.9393 JUMP_CUT
		CLEAR_AREA 2270.6 1637.9 1007.3 1.0 TRUE
		CLEAR_AREA 2257.2671 1588.1194 1009.9393 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2270.6 1637.9 1007.3 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


LA_mansion_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION 1278.0908 -849.6337 90.7593 0.0 0.0 0.0 //Triad Casino house
		POINT_CAMERA_AT_POINT 1278.0381 -848.6531 90.5709 JUMP_CUT
		CLEAR_AREA 1258.1578 -785.2752 91.0302 1.0 TRUE
		CLEAR_AREA 1278.0381 -848.6531 90.5709 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 1258.1578 -785.2752 91.0302 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


zero_script_cut1:

	IF IS_PLAYER_PLAYING player1	
		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON	     
		SET_FIXED_CAMERA_POSITION -2278.0042 134.3340 39.6060 0.0 0.0 0.0 //Zero's shop
		POINT_CAMERA_AT_POINT -2277.0078 134.3560 39.6897 JUMP_CUT
		CLEAR_AREA -2242.8120 128.7572 34.3203 1.0 TRUE
		CLEAR_AREA -2277.0078 134.3560 39.6897 2.0 TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer -2242.8120 128.7572 34.3203 PEDMOVE_WALK 3000

		GOSUB cutscene_fading_status
	ENDIF

RETURN


// ****************************************HELP TEXT*************************************************
game_help_loop:

{

	SCRIPT_NAME	HELP

game_help_loop_inner:
												 
	WAIT 70

	IF IS_PLAYER_PLAYING player1
	  
		IF game_starts_from_scratch = 1

			// added to game_help_loop_inner around line 5938

			IF wasted_help = 0
				IF HAS_PICKUP_BEEN_COLLECTED wasted_help1

					IF IS_PLAYER_PLAYING player1 
						SET_PLAYER_CONTROL player1 OFF
						SWITCH_WIDESCREEN ON
					ENDIF

					SET_FIXED_CAMERA_POSITION 2022.6125 -1432.0500 17.6541 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2022.9888 -1431.1235 17.6552 JUMP_CUT

					REMOVE_PICKUP wasted_help1
					REMOVE_PICKUP wasted_help2
				   	
				   	// You have been wasted
					CLEAR_HELP
					PRINT_HELP_FOREVER HOSP_1   // If your health reaches zero, you will pass out and you will be treated at the local medical center.
					WAIT 8000
					PRINT_HELP_FOREVER HOSP_2   // To replenish your health you can eat food, use health pick-ups or save at a safehouse.
					WAIT 8000
					PRINT_HELP_FOREVER HOSP_3   //Before you are discharged, hospital staff will confiscate your weapons and bill you for the healthcare you received.
					WAIT 8000
					
					CLEAR_HELP

					wasted_help = 1

					RESTORE_CAMERA_JUMPCUT
					IF IS_PLAYER_PLAYING player1
						SET_PLAYER_CONTROL player1 ON
						SWITCH_WIDESCREEN OFF
					ENDIF

				ENDIF

				IF HAS_PICKUP_BEEN_COLLECTED wasted_help2

					IF IS_PLAYER_PLAYING player1 
						SET_PLAYER_CONTROL player1 OFF
						SWITCH_WIDESCREEN ON
					ENDIF

					SET_FIXED_CAMERA_POSITION 1184.3470 -1313.4890 19.7079 0.0 0.0 0.0
                    POINT_CAMERA_AT_POINT 1183.6447 -1314.0889 19.3248 JUMP_CUT	

					REMOVE_PICKUP wasted_help1
					REMOVE_PICKUP wasted_help2
				   	
				   	// You have been wasted
					CLEAR_HELP
					PRINT_HELP_FOREVER HOSP_1   // If your health reaches zero, you will pass out and you will be treated at the local medical center.
					WAIT 8000
					PRINT_HELP_FOREVER HOSP_2   // To replenish your health you can eat food, use health pick-ups or save at a safehouse.
					WAIT 8000
					PRINT_HELP_FOREVER HOSP_3   //Before you are discharged, hospital staff will confiscate your weapons and bill you for the healthcare you received.
					WAIT 8000
					
					CLEAR_HELP

					RESTORE_CAMERA_JUMPCUT
					IF IS_PLAYER_PLAYING player1
						SET_PLAYER_CONTROL player1 ON
						SWITCH_WIDESCREEN OFF
					ENDIF
					wasted_help = 1
				ENDIF

			ENDIF

			IF busted_help = 0
				IF HAS_PICKUP_BEEN_COLLECTED busted_help1

					IF IS_PLAYER_PLAYING player1 
						SET_PLAYER_CONTROL player1 OFF
						SWITCH_WIDESCREEN ON
					ENDIF
											   
					SET_FIXED_CAMERA_POSITION 1536.5765 -1687.1343 26.7069 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1537.2947 -1686.5641 26.3081 JUMP_CUT

					REMOVE_PICKUP busted_help1
					
					// You have been arrested
					CLEAR_HELP
					PRINT_HELP_FOREVER BUST_1 //When you have a wanted level, the local police force will try to arrest ('bust') you.
					WAIT 8000
					PRINT_HELP_FOREVER BUST_2 //As your wanted level gets higher, different enforcement agencies will become involved and the likelihood of them trying to kill you will increase.
					WAIT 8000
					PRINT_HELP_FOREVER BUST_3 //If you are 'busted' by law enforcement you will be processed at a local police precinct.
					WAIT 8000
					PRINT_HELP_FOREVER BUST_4 //The officers will strip you of your weapons and take some of your cash as a bribe.
					WAIT 8000
					CLEAR_HELP

					RESTORE_CAMERA_JUMPCUT
					IF IS_PLAYER_PLAYING player1
						SET_PLAYER_CONTROL player1 ON
						SWITCH_WIDESCREEN OFF
					ENDIF
					busted_help = 1
				ENDIF
			ENDIF

 			IF chat_help1_flag = 0
				IF HAS_PICKUP_BEEN_COLLECTED chat_help1
					PRINT_HELP CHATBAK	
					chat_help1_flag = 1
				ENDIF
			ENDIF

			IF swimming_help < 7
				IF swimming_help = 0
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SWIMMING scplayer
							PRINT_HELP WZI2_A0 //Use the ~h~left analog stick ~w~to move around in the water.
							swimming_help = 1
							WAIT 5000
						ENDIF
					ENDIF
				ENDIF
				IF swimming_help = 1
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SWIMMING scplayer
							PRINT_HELP WZI2_A5  
							swimming_help = 2
							WAIT 5000
						ENDIF
					ENDIF
				ENDIF
				IF swimming_help = 2
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SWIMMING scplayer
														
							PRINT_HELP WZI2_A6  

							swimming_help = 3
							WAIT 5000
						ENDIF
					ENDIF
				ENDIF
				IF swimming_help = 3
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SWIMMING scplayer
							
							PRINT_HELP WZI2_A1  

							swimming_help = 4
							WAIT 5000
						ENDIF
					ENDIF
				ENDIF
				IF swimming_help = 4
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SWIMMING scplayer
							PRINT_HELP WZI2_A2 //The blue bar at the top right of the screen is your ~h~breath meter ~h~this represents how long you can stay underwater.
							FLASH_HUD_OBJECT HUD_FLASH_BREATH
							WAIT 5000
							swimming_help = 5
							FLASH_HUD_OBJECT -1
						ENDIF
					ENDIF
				ENDIF
				IF swimming_help = 5
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SWIMMING scplayer
							PRINT_HELP WZI2_A3 //Your breath will decrease while you're underwater. Once your breath reaches zero, your health will start to decrease.
							swimming_help = 6
							WAIT 5000
						ENDIF
					ENDIF
				ENDIF
				IF swimming_help = 6
					IF IS_PLAYER_PLAYING player1
						IF IS_CHAR_SWIMMING scplayer
							PRINT_HELP WZI2_A7 //Swim back up to the surface to get your breath back. The breath meter will slowly increase back to its maximum.
							swimming_help = 7
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF print_first_help = 0
					
				WAIT 2000
				
				IF IS_PLAYER_PLAYING player1
					IF been_in_a_bmx = 0
						IF IS_CHAR_IN_MODEL scplayer BMX
							
							PRINT_HELP INTRO2F  
                            
							been_in_a_bmx = 1
						ENDIF
					ENDIF
				ENDIF

				WAIT 5000

				PRINT_HELP ( HELP42 ) // "This is the ~h~radar~w~. Use it to navigate the city. Follow the ~h~GPS route~w~ on the ~h~radar~w~ to get back to the hood."
				
				FLASH_HUD_OBJECT HUD_FLASH_RADAR
				
				WAIT 7000

				FLASH_HUD_OBJECT -1
				
				print_first_help = 1
			
			ELSE

				IF spray_help = 0
					IF flag_sweet_mission_counter = 1
						WAIT 4000
						PRINT_HELP (SPRAY_C) //Spray can
						spray_help = 1
					ENDIF
				ENDIF

				IF voice_over_at_hub = 1
					IF IS_PLAYER_PLAYING player1
						IF LOCATE_CHAR_ANY_MEANS_3D scplayer introX introY introZ 25.0 25.0 5.0 FALSE

							IF IS_CHAR_IN_ANY_CAR scplayer
								PRINT_HELP HELP21B  
							ENDIF
							WAIT 5000
							
							PRINT_HELP HELP44  // Walk into the red marker to continue.

							voice_over_at_hub = 2
						ENDIF
					ENDIF
				ENDIF

				IF IS_PLAYER_PLAYING player1
					IF been_in_a_bmx = 0
						IF IS_CHAR_IN_MODEL scplayer BMX
						OR IS_CHAR_IN_MODEL scplayer bike
						OR IS_CHAR_IN_MODEL scplayer mtbike
							WAIT 2000
							
							PRINT_HELP INTRO2F  
							
							been_in_a_bmx = 1
						ENDIF
					ENDIF
				ENDIF

				IF IS_PLAYER_PLAYING player1
					IF bike_help = 0
						IF IS_CHAR_IN_MODEL scplayer BMX
						OR IS_CHAR_IN_MODEL scplayer bike
						OR IS_CHAR_IN_MODEL scplayer mtbike
							IF bike_help = 0
							AND been_in_a_bmx = 1
								
								WAIT 6000
                                PRINT_HELP HELP5_A  
								WAIT 8000
								
								PRINT_HELP HELP27  
								
                                WAIT 2000

								bike_help = 1
							ENDIF
						ENDIF	
					ENDIF


					IF IS_PLAYER_PLAYING player1
						IF flag_intro_mission_counter > 0
							IF car_help_played = 0
								IF main_visible_area = 0
									IF NOT IS_CHAR_ON_ANY_BIKE scplayer
										IF IS_CHAR_IN_ANY_CAR scplayer
											
											WAIT 2000
											
											PRINT_HELP GF_RAD  // Swipe the bottom left corner of the screen to change radio stations. CONDITION_FLAG_RADIO_STATION
											
											WAIT 5000
											PRINT_HELP HELP56  
											
											car_help_played = 1
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							IF drive_by_help = 0
								IF car_help_played = 1
									IF IS_PLAYER_PLAYING player1
										GET_AREA_VISIBLE main_visible_area
										IF main_visible_area = 0
											IF IS_CHAR_IN_ANY_CAR scplayer
											AND NOT IS_CHAR_ON_ANY_BIKE scplayer
												IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_MICRO_UZI 
												OR IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_MP5

												    PRINT_HELP HELP31  
													WAIT 6000
													PRINT_HELP HELP32
													WAIT 6000
                                                    PRINT_HELP HELP34  // You must have a sub machine gun to perform a drive-by.
												   	drive_by_help = 1
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF


				ENDIF //IS_PLAYER_PLAYING

			ENDIF //print_first_help = 0

			IF drive_by_help = 1
				IF car_help_played = 1
					IF voice_over_at_hub = 1
						IF chat_help1_flag = 1
							TERMINATE_THIS_SCRIPT
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		ENDIF

	ENDIF

GOTO game_help_loop_inner

}

// ****************************************OPEN UP MISSIONS*************************************************

intro_stuff_loop:

{

SCRIPT_NAME	INTROST

intro_stuff_loop_inner:

	WAIT 0

	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			IF voice_over_at_hub = 0
				IF IS_PLAYER_PLAYING player1
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2471.7 -1668.0 12.3 90.0 40.0 4.0 FALSE
						LOAD_MISSION_AUDIO 1 SOUND_VO_AF //Grove Street - Home.
						LOAD_MISSION_AUDIO 2 SOUND_VO_AG // At least it was before I fucked everything up.
						
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0

							IF NOT IS_PLAYER_PLAYING player1
								GOTO skip_loading_audio
							ENDIF

						ENDWHILE

						PRINT_NOW ( VO_AF ) 4000 1 
						PLAY_MISSION_AUDIO 1 //Grove Street - Home.
						
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0

							IF NOT IS_PLAYER_PLAYING player1
								GOTO skip_loading_audio
							ENDIF

						ENDWHILE
						
						PRINT_NOW ( VO_AG ) 4000 1 
						PLAY_MISSION_AUDIO 2 // At least it was before I fucked everything up.

						WHILE NOT HAS_MISSION_AUDIO_LOADED 2
							WAIT 0

							IF NOT IS_PLAYER_PLAYING player1
								GOTO skip_loading_audio
							ENDIF

						ENDWHILE

						skip_loading_audio:
						voice_over_at_hub = 1

					ENDIF
				ENDIF
			ENDIF
		
			IF funeral_mission_finished = 0
				IF flag_intro_mission_counter = 1
					WAIT 5000
					PRINT_NOW ( INT_CJ1 ) 8000 1 // ~s~go into CJ's house.
					funeral_mission_finished = 1
				ENDIF
			ENDIF
			IF funeral_mission_finished = 1
				IF flag_intro_mission_counter = 1
					GET_AREA_VISIBLE main_visible_area
					IF main_visible_area = 3
						IF IS_PLAYER_PLAYING player1
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2496.0505 -1698.0128 1013.7500 10.0 10.0 5.0 FALSE
								SET_PLAYER_CONTROL player1 OFF
								CLEAR_PRINTS
								SET_FIXED_CAMERA_POSITION 2496.3799 -1694.3336 1015.4313 0.0 0.0 0.0 // look at savepoint
								POINT_CAMERA_AT_POINT 2496.3677 -1695.3247 1015.2991 JUMP_CUT

								PRINT_HELP SAVE

								WAIT 5000
								IF IS_PLAYER_PLAYING player1
									SET_PLAYER_CONTROL player1 ON
									RESTORE_CAMERA_JUMPCUT
									funeral_mission_finished = 2
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF funeral_mission_finished = 2
				IF flag_intro_mission_counter = 1
					GET_AREA_VISIBLE main_visible_area
					IF main_visible_area = 0
						IF IS_PLAYER_PLAYING player1
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2495.3298 -1689.1830 13.2716 20.0 20.0 6.0 FALSE
								TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME INT
								START_NEW_SCRIPT intro_mission_loop
								REMOVE_BLIP	intro_contact_blip
								ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon intro_contact_blip
								PRINT_NOW ( INT2_1 ) 8000 1 // Go and see Ryder, he lives across the street.
								WAIT 2000
								PRINT_HELP ( RYBLIP ) //Follow the ~h~blip~w~ to get back to the hood.
								WAIT 4000
								funeral_mission_finished = 3
								TERMINATE_THIS_SCRIPT
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

GOTO intro_stuff_loop_inner

}

game_flow_loop:

{

SCRIPT_NAME	FLOW

game_flow_loop_inner:
												 
	WAIT 1000

	IF IS_PLAYER_PLAYING player1

		IF flag_player_on_mission = 0

			IF girlfried_help_flag = 0 
				IF flag_crash_mission_counter = 1
					WAIT 5500
					CLEAR_HELP
					PRINT_HELP ( GF_H000 ) //Girlfriend help
					girlfried_help_flag = 1
				ENDIF
			ENDIF

			IF oddjob_help_flag = 0 
				IF flag_scrash_mission_counter = 1
				//AND NOT demo_debug_flag = 1
					WAIT 6000
					CLEAR_HELP
					PRINT_HELP ( VAL_HLP ) //Valet help
					WAIT 5000
					CLEAR_HELP
					PRINT_HELP ( VAL_GOT ) //Valet uniform
					oddjob_help_flag = 1
					//demo_debug_flag = 0
				ENDIF
			ENDIF

			IF trucking_help_flag = 0
				IF flag_cat_mission3_passed = 1	//truck help
					WAIT 6000
					CLEAR_HELP
					PRINT_HELP ( TRUCK_H )
					trucking_help_flag = 1
				ENDIF
			ENDIF

			IF stealth_help_flag = 0
				IF flag_strap_mission_counter > 1 //Stealth help
					WAIT 6000
					CLEAR_HELP
					PRINT_HELP ( STELF )
					stealth_help_flag = 1
				ENDIF
			ENDIF

			IF got_gimp_suit = 0
				IF flag_heist_mission_counter = 2 //Got gimp outfit
					WAIT 6000
					CLEAR_HELP
					PRINT_HELP ( GIMPGOT )
					got_gimp_suit = 1
				ENDIF
			ENDIF

			IF camera_secret_help = 0
				IF flag_synd_mission_counter = 1 //Camera
					WAIT 6000
					CLEAR_HELP
					PRINT_HELP ( SECRET	)
					camera_secret_help = 1
				ENDIF
			ENDIF
		
			IF croupier_help = 0
				IF flag_heist_mission_counter = 6 //croupier uniform
					WAIT 6000
					CLEAR_HELP
					PRINT_HELP ( CRO_GOT )
					croupier_help = 1
				ENDIF
			ENDIF

			// trigger LA1 finale*****************************************************************************************
			IF trigger_final_LA1_missions = 0
				IF flag_sweet_mission_counter = 9
					IF flag_smoke_mission_counter = 4
						IF flag_strap_mission_counter = 5
							IF flag_ryder_mission_counter = 3
								IF flag_crash_mission_counter = 2
								 	IF flag_cesar_mission_counter = 1
										START_NEW_SCRIPT la1fin1_mission_loop
										REMOVE_BLIP	sweet_contact_blip
										ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
										trigger_final_LA1_missions = 1
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			// Triggers the Scrash2 mission
			IF trigger_scrash2_mission = 0
				IF flag_scrash_mission_counter = 1
				AND flag_Synd_mission_counter = 6
					REMOVE_BLIP garage_contact_blip
					ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ crash_blip_icon garage_contact_blip
					trigger_scrash2_mission = 1
				ENDIF
			ENDIF

			// Triggers the outrider mission
			IF trigger_ice_cold_mission = 0
				IF flag_Synd_mission_counter = 5
					REMOVE_BLIP synd_contact_blip
					REMOVE_BLIP garage_contact_blip
					ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
					trigger_ice_cold_mission = 1
				ENDIF
			ENDIF

			// Triggers the final Synd mission
			IF trigger_final_synd_mission = 0
				IF flag_Synd_mission_counter = 9
				AND flag_wuzi_mission_counter = 5
					REMOVE_BLIP garage_contact_blip
					ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ garage_blip_icon garage_contact_blip
					trigger_final_synd_mission = 1
				ENDIF
			ENDIF

			// trigger LA2 finale*****************************************************************************************
			IF trigger_final_LA2_missions = 0
				IF flag_grove_mission_counter = 2
				AND flag_mansion_mission_counter = 4
					START_NEW_SCRIPT riot_mission_loop //"riot mission 1" //CRAIG //RIOT!
					REMOVE_BLIP grove_contact_blip
					REMOVE_BLIP mansion_contact_blip
					ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon mansion_contact_blip
					trigger_final_LA2_missions = 1
					TERMINATE_THIS_SCRIPT
				ENDIF
			ENDIF

		ENDIF //FLAG PLAYER ON MISSION
			
	ENDIF // IF IS_PLAYER_PLAYING player1

GOTO game_flow_loop_inner

}


andys_door_madness:

{
SCRIPT_NAME DOORS
	WAIT 0
	
	////////////////////////////////////////////////////////////////////////////
	// DOORS FOR RIOT2///////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	//NW house 0
	ROTATE_OBJECT riot2_door[3] 0.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1852.0 -1990.7 1849.8 -1989.6 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1854.0 -1990.0 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//NW house 1
	ROTATE_OBJECT riot2_door[4] 270.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1867.3 -1985.2 1868.2 -1982.7 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1867.4 -1987.1 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF            
	//NW house 2
	ROTATE_OBJECT riot2_door[5] 90.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1866.5 -1998.3 1865.4 -2000.4 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1866.0 -1996.7 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF
	//NE house 0
	ROTATE_OBJECT riot2_door[6] 270.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1899.6 -1985.2 1900.9 -1982.7 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1899.8 -1986.7 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//NE house 1
	ROTATE_OBJECT riot2_door[7] 180.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1914.1 -1992.7 1916.8 -1994.2 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1912.3 -1993.2 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//NE house 2
	ROTATE_OBJECT riot2_door[8] 90.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1899.0 -1998.1 1897.9 -2000.4 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1898.7 -1996.1 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//SE house 0
	ROTATE_OBJECT riot2_door[9] 0.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1901.0 -2020.1 1899.1 -2019.1 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1902.7 -2019.6 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//SE house 1
	ROTATE_OBJECT riot2_door[10] 180.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1914.1 -2020.8 1916.2 -2021.8 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1912.4 -2021.3 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//SE house 2
	ROTATE_OBJECT riot2_door[11] 90.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1906.5 -2035.3 1905.5 -2037.2 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1905.8 -2033.4 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//SW house 0
	ROTATE_OBJECT riot2_door[12] 0.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1852.0 -2020.1 1850.1 -2019.2 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1853.7 -2019.4 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//SW house 1
	ROTATE_OBJECT riot2_door[13] 180.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1865.0 -2020.7 1867.1 -2021.8 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1863.0 -2021.3 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

	//SW house 2
	ROTATE_OBJECT riot2_door[14] 90.0 360.0 FALSE
	IF IS_PLAYER_PLAYING player1
		IF IS_CHAR_IN_AREA_2D scplayer 1857.6 -2035.2 1856.5 -2037.2 FALSE
		    GOSUB mini_fade 
		    IF IS_PLAYER_PLAYING player1
			    CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
			    SET_CHAR_COORDINATES scplayer 1857.1 -2033.3 12.5
			ENDIF
		    DO_FADE 500 FADE_IN
		    WHILE GET_FADING_STATUS
		        WAIT 0
		    ENDWHILE 
		ENDIF
	ENDIF

TERMINATE_THIS_SCRIPT
}


switch_update_stats_back_on:

{
	SCRIPT_NAME BACKON
    WAIT 5000
    SHOW_UPDATE_STATS TRUE
    TERMINATE_THIS_SCRIPT
}




{///////////////////////////////////////////////////////////////////////////////
DISPLAY_WIN_TEXT:// Used for casino games //////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME cashwin

LVAR_INT cash_win
LVAR_INT time_to_print
LVAR_INT racetour_flag
LVAR_INT timer
GET_GAME_TIMER game_timer
IF time_to_print = 0
	timer = game_timer + 3000
ELSE
	timer = game_timer + time_to_print
ENDIF

display_win_text_loop:
	WAIT 0

	IF NOT is_player_playing player1
		terminate_this_script
	ENDIF
	GET_GAME_TIMER game_timer
	IF timer < game_timer
		TERMINATE_THIS_SCRIPT
	ENDIF

	IF cash_win = 0
		GOSUB setup_win_text
		SET_TEXT_COLOUR 180 180 180 255
		IF racetour_flag = 69
			DISPLAY_TEXT 320.0 180.333 BJ_PUSH
		ELSE
			DISPLAY_TEXT 320.0 180.333 NOWIN 
		ENDIF
	ELSE
		IF cash_win > 0
			GOSUB setup_win_text
			
			SWITCH racetour_flag
				CASE 45
					DISPLAY_TEXT_WITH_NUMBER 320.0 155.333 ALLRACE cash_win
				BREAK
				CASE 80
					DISPLAY_TEXT_WITH_NUMBER 320.0 155.333 PL_07 cash_win
				BREAK
				DEFAULT
					DISPLAY_TEXT_WITH_NUMBER 320.0 155.333 WINNER cash_win
				BREAK
			ENDSWITCH

		ELSE
			temp_integer_1 = cash_win * -1
			GOSUB setup_win_text
			SWITCH racetour_flag
				CASE 80
					DISPLAY_TEXT_WITH_NUMBER 320.0 155.333 PL_10 temp_integer_1
				BREAK
				DEFAULT
					DISPLAY_TEXT_WITH_NUMBER 320.0 155.333 LOSER temp_integer_1
				BREAK
			ENDSWITCH
		ENDIF
	ENDIF

GOTO display_win_text_loop

setup_win_text:
	SET_TEXT_RIGHT_JUSTIFY OFF
	SET_TEXT_JUSTIFY OFF
	SET_TEXT_WRAPX 640.0
	SET_TEXT_PROPORTIONAL ON
	SET_TEXT_BACKGROUND OFF
	SET_TEXT_SCALE 1.3 3.36
	SET_TEXT_CENTRE ON
	SET_TEXT_EDGE 2 0 0 0 255
	set_text_font font_heading
	GET_HUD_COLOUR HUD_COLOUR_YELLOW temp_integer_2 temp_integer_3 temp_integer_4 an
	set_text_colour	temp_integer_2 temp_integer_3 temp_integer_4 255
RETURN
}

{
crane_manager:
SCRIPT_NAME CRANES

	LVAR_INT num_of_crane_scripts_running

	GOTO skip_crane_bit
		CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 magno_base
	skip_crane_bit:


	crane_manager_loop:
		WAIT 100
		
		IF IS_PLAYER_PLAYING player1

			// crane type 1 -----------------------------

			// san fran
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer -2080.441 256.015 10.0 10.0 FALSE
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT crane1.sc num_of_crane_scripts_running
				IF num_of_crane_scripts_running = 0
					STREAM_SCRIPT crane1.sc
					IF HAS_STREAMED_SCRIPT_LOADED crane1.sc
						START_NEW_STREAMED_SCRIPT crane1.sc	 sf_crane1_base
						num_of_crane_scripts_running++
					ENDIF
				ELSE
					MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED crane1.sc
				ENDIF	
			ENDIF
			// vegas
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2399.202 1879.139 10.0 10.0 FALSE
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT crane1.sc num_of_crane_scripts_running
				IF num_of_crane_scripts_running = 0
					STREAM_SCRIPT crane1.sc
					IF HAS_STREAMED_SCRIPT_LOADED crane1.sc
						START_NEW_STREAMED_SCRIPT crane1.sc	 lv_base
						num_of_crane_scripts_running++
					ENDIF
				ELSE
					MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED crane1.sc
				ENDIF	
			ENDIF

			// DOCK CRANE
			IF DOES_OBJECT_EXIST magno_base
				IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer	magno_base 50.0 50.0 FALSE
					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT crane2.sc num_of_crane_scripts_running
					IF num_of_crane_scripts_running = 0
						STREAM_SCRIPT crane2.sc
						IF HAS_STREAMED_SCRIPT_LOADED crane2.sc
							START_NEW_STREAMED_SCRIPT crane2.sc	 magno_base
							num_of_crane_scripts_running++
						ENDIF
					ELSE
						MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED crane2.sc
					ENDIF
				ENDIF
			ENDIF

			// QUARRY CRANE
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 709.45 915.93 10.0 10.0 FALSE
				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT crane3.sc num_of_crane_scripts_running
				IF num_of_crane_scripts_running = 0
					STREAM_SCRIPT crane3.sc
					IF HAS_STREAMED_SCRIPT_LOADED crane3.sc
						START_NEW_STREAMED_SCRIPT crane3.sc	 quarry_base quarry_stand quarry_arm
						num_of_crane_scripts_running++		 
					ENDIF
				ELSE
					MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED crane3.sc									  
				ENDIF
			ENDIF 
			
		ENDIF

	GOTO crane_manager_loop

}


VAR_INT entry_exit_area entry_exit_status
VAR_TEXT_LABEL entry_exit_name
{
	switch_entry_exit_after_mission:
	SCRIPT_NAME ENTEXT

	LVAR_INT current_area
	current_area = -1
	             
    WHILE NOT current_area = entry_exit_area
    	WAIT 0
        GET_AREA_VISIBLE current_area
    ENDWHILE
    SWITCH_ENTRY_EXIT $entry_exit_name entry_exit_status
	                        
	TERMINATE_THIS_SCRIPT  
}



{
	
	check_player_is_safe:

		player_is_completely_safe = 0

		IF IS_PLAYER_PLAYING player1
			IF main_visible_area = 0
				IF flag_cell_nation = 0 //phone call not in progress
					IF player_fall_state = 0 //Parachute
						IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS	
							IF NOT IS_BIT_SET iDateReport MEETING_IN_PROGRESS
								IF NOT IS_MINIGAME_IN_PROGRESS
									IF CAN_PLAYER_START_MISSION player1
										player_is_completely_safe = 1
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	RETURN
}


{
	
	check_player_is_safe_for_mobile:

		player_is_completely_safe_for_mobile = 0

		IF IS_PLAYER_PLAYING player1
			IF flag_player_on_mission = 0
				IF main_visible_area = 0
					IF flag_cell_nation = 0 //phone call not in progress
						IF player_fall_state = 0 //Parachute
							IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS	
								IF NOT IS_BIT_SET iDateReport MEETING_IN_PROGRESS
									IF NOT IS_MINIGAME_IN_PROGRESS
										IF CAN_PLAYER_START_MISSION player1
											IF IS_CHAR_ON_FOOT scplayer
												IF NOT IS_GANG_WAR_FIGHTING_GOING_ON
													IF NOT IS_WANTED_LEVEL_GREATER player1 0
														GET_GAME_TIMER timer_mobile_now
														timer_mobile_diff = timer_mobile_now - timer_mobile_start
														player_is_completely_safe_for_mobile = 1
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
			ENDIF
		ENDIF

	RETURN
}


{

open_the_map:

SCRIPT_NAME OPENUP

open_the_map_inner:

	WAIT 2000

	IF Return_cities_passed > 0
		IF opened_badlands_up = 0
			//BADLANDS
			GOSUB remove_prop_blips1
			CREATE_FORSALE_PROPERTY_PICKUP propertyX[8] propertyY[8] propertyZ[8] save_houseprice[8] PROP_3 save_housepickup[8] //Small town suburban house with lawn and porch
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[8] propertyY[8] propertyZ[8] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[8]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[8] BLIP_ONLY

			CREATE_FORSALE_PROPERTY_PICKUP propertyX[19] propertyY[19] propertyZ[19] save_houseprice[19] PROP_3 save_housepickup[19] //Farm
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[19] propertyY[19] propertyZ[19] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[19]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[19] BLIP_ONLY

			CREATE_FORSALE_PROPERTY_PICKUP propertyX[22] propertyY[22] propertyZ[22] save_houseprice[22] PROP_3 save_housepickup[22] //just off main Angel Pine town, behind Sawmill   
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[22] propertyY[22] propertyZ[22] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[22]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[22] BLIP_ONLY

			CREATE_FORSALE_PROPERTY_PICKUP propertyX[25] propertyY[25] propertyZ[25] save_houseprice[25] PROP_3 save_housepickup[25] //Las Barrancas (southern town)
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[25] propertyY[25] propertyZ[25] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[25]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[25] BLIP_ONLY

			CREATE_FORSALE_PROPERTY_PICKUP propertyX[31] propertyY[31] propertyZ[31] save_houseprice[31] PROP_3 save_housepickup[31] //North Badlands
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[31] propertyY[31] propertyZ[31] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[31]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[31] BLIP_ONLY

			opened_badlands_up = 1
		ENDIF
	ENDIF

	IF Return_cities_passed > 0
		IF flag_truth_mission_counter > 1
		OR launch_shit_for_debug_build = 1 //TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			IF opened_sanfran_up = 0
				//SAN FRAN
				GOSUB remove_prop_blips2
				CREATE_FORSALE_PROPERTY_PICKUP propertyX[11] propertyY[11] propertyZ[11] save_houseprice[11] PROP_3 save_housepickup[11] //big swanky SF savehouse at the top of lombard street
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[11] propertyY[11] propertyZ[11] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[11]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[11] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[13] propertyY[13] propertyZ[13] save_houseprice[13] PROP_3 save_housepickup[13] //small flat near hospital
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[13] propertyY[13] propertyZ[13] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[13]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[13] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[14] propertyY[14] propertyZ[14] save_houseprice[14] PROP_3 save_housepickup[14] //large garage in alley behind apartments in Hashbury
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[14] propertyY[14] propertyZ[14] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[14]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[14] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[18] propertyY[18] propertyZ[18] save_houseprice[18] PROP_3 save_housepickup[18] //small chinatown pad
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[18] propertyY[18] propertyZ[18] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[18]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[18] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[20] propertyY[20] propertyZ[20] save_houseprice[20] PROP_3 save_housepickup[20] //large pad next to driving school   
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[20] propertyY[20] propertyZ[20] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[20]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[20] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[21] propertyY[21] propertyZ[21] save_houseprice[21] PROP_3 save_housepickup[21] //Vank Hoff In The Park Hotel Room    
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[21] propertyY[21] propertyZ[21] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[21]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[21] BLIP_ONLY

				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1458.7, -1140.0, 24.2 RADAR_SPRITE_TSHIRT clothes_blips[0] //CLOTHGP //SAN FRAN
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1886.1, 862.4, 35.2 RADAR_SPRITE_TSHIRT clothes_blips[1] //CLOTHGP //SAN FRAN
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2574.3, 1901.7, 11.0 RADAR_SPRITE_TSHIRT clothes_blips[2] //CLOTHGP //SAN FRAN
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2090.5, 2221.0, 11.0 RADAR_SPRITE_TSHIRT clothes_blips[3] //CLOTHGP //SAN FRAN
				SWITCH_ENTRY_EXIT clothgp TRUE //ZIP //SAN FRAN

				opened_sanfran_up = 1
			ENDIF
		ENDIF
	ENDIF

	IF Return_cities_passed > 1
		IF opened_desert_up = 0
			//DESERT
			GOSUB remove_prop_blips3
			CREATE_FORSALE_PROPERTY_PICKUP propertyX[5] propertyY[5] propertyZ[5] save_houseprice[5] PROP_3 save_housepickup[5] //Fort Carson old house savehouse near river BADLANDS
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[5] propertyY[5] propertyZ[5] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[5]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[5] BLIP_ONLY

			CREATE_FORSALE_PROPERTY_PICKUP propertyX[23] propertyY[23] propertyZ[23] save_houseprice[23] PROP_3 save_housepickup[23] //In El Quebrados (most northerly town)
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[23] propertyY[23] propertyZ[23] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[23]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[23] BLIP_ONLY

			CREATE_FORSALE_PROPERTY_PICKUP propertyX[24] propertyY[24] propertyZ[24] save_houseprice[24] PROP_3 save_housepickup[24] //Las Barrancas (southern town)
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[24] propertyY[24] propertyZ[24] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[24]
			CHANGE_BLIP_DISPLAY prop_save_house_blip[24] BLIP_ONLY

			opened_desert_up = 1
		ENDIF
	ENDIF

	IF Return_cities_passed	> 1
		IF flag_mob_sanfran[8] = 1
		OR launch_shit_for_debug_build = 1 //TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			IF opened_vegas_up = 0
				//VEGAS
				GOSUB remove_prop_blips4
				CREATE_FORSALE_PROPERTY_PICKUP propertyX[4] propertyY[4] propertyZ[4] save_houseprice[4] PROP_3 save_housepickup[4] //Shabbyhouse in Vegas East
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[4] propertyY[4] propertyZ[4] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[4]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[4] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[6] propertyY[6] propertyZ[6] save_houseprice[6] PROP_3 save_housepickup[6] //Medium house in nice suburb of Venturas
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[6] propertyY[6] propertyZ[6] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[6]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[6] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[7] propertyY[7] propertyZ[7] save_houseprice[7] PROP_3 save_housepickup[7] //shabby house in rundown residential area
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[7] propertyY[7] propertyZ[7] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[7]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[7] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[9] propertyY[9] propertyZ[9] save_houseprice[9] PROP_3 save_housepickup[9] //shabby house in rundown residential area
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[9] propertyY[9] propertyZ[9] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[9]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[9] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[16] propertyY[16] propertyZ[16] save_houseprice[16] PROP_3 save_housepickup[16] //Pirates In Men’s Pants Casino
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[16] propertyY[16] propertyZ[16] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[16]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[16] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[17] propertyY[17] propertyZ[17] save_houseprice[17] PROP_3 save_housepickup[17] //The Camels Toe Casino
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[17] propertyY[17] propertyZ[17] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[17]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[17] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[27] propertyY[27] propertyZ[27] save_houseprice[27] PROP_3 save_housepickup[27] //CASINO
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[27] propertyY[27] propertyZ[27] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[27]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[27] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[28] propertyY[28] propertyZ[28] save_houseprice[28] PROP_3 save_housepickup[28] //Casino
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[28] propertyY[28] propertyZ[28] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[28]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[28] BLIP_ONLY

				CREATE_FORSALE_PROPERTY_PICKUP propertyX[29] propertyY[29] propertyZ[29] save_houseprice[29] PROP_3 save_housepickup[29] //Vegas house
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[29] propertyY[29] propertyZ[29] RADAR_SPRITE_PROPERTY_GREEN prop_save_house_blip[29]
				CHANGE_BLIP_DISPLAY prop_save_house_blip[29] BLIP_ONLY

				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 457.2, -1500.7, 31.3 RADAR_SPRITE_TSHIRT clothes_blips[11] //CSDESGN //VEGAS
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2795.2, 2424.6, 11.0 RADAR_SPRITE_TSHIRT clothes_blips[12] //CSDESGN //VEGAS
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1692.9, 952.8, 26.3 RADAR_SPRITE_TSHIRT clothes_blips[15] //CSDESGN //SAN FRAN
				
				SWITCH_ENTRY_EXIT csdesgn TRUE	//VICTIM //VEGAS

				opened_vegas_up = 1 
			ENDIF
		ENDIF
	ENDIF

	IF Return_cities_passed	> 2
		IF opened_la2_up = 0
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 451.9, -1480.0, 30.9 RADAR_SPRITE_TSHIRT clothes_blips[13] //CSEXL //LA2
			SWITCH_ENTRY_EXIT csexl	TRUE  //DIDIERSACHS //LA2
			TERMINATE_THIS_SCRIPT
			opened_la2_up = 1
		ENDIF
	ENDIF


GOTO open_the_map_inner

}


remove_prop_blips1:

	REMOVE_BLIP prop_save_house_blip[8]
	REMOVE_BLIP prop_save_house_blip[19]
	REMOVE_BLIP prop_save_house_blip[22]
	REMOVE_BLIP prop_save_house_blip[25]
	REMOVE_BLIP prop_save_house_blip[31]

	REMOVE_PICKUP save_housepickup[8]
	REMOVE_PICKUP save_housepickup[19]
	REMOVE_PICKUP save_housepickup[22]
	REMOVE_PICKUP save_housepickup[25]
	REMOVE_PICKUP save_housepickup[31]

RETURN

remove_prop_blips2:

	REMOVE_BLIP prop_save_house_blip[11]
	REMOVE_BLIP prop_save_house_blip[13]
	REMOVE_BLIP prop_save_house_blip[14]
	REMOVE_BLIP prop_save_house_blip[18]
	REMOVE_BLIP prop_save_house_blip[20]
	REMOVE_BLIP prop_save_house_blip[21]

	REMOVE_PICKUP save_housepickup[11]
	REMOVE_PICKUP save_housepickup[13]
	REMOVE_PICKUP save_housepickup[14]
	REMOVE_PICKUP save_housepickup[18]
	REMOVE_PICKUP save_housepickup[20]
	REMOVE_PICKUP save_housepickup[21]

RETURN

remove_prop_blips3:

	REMOVE_BLIP prop_save_house_blip[5]
	REMOVE_BLIP prop_save_house_blip[23]
	REMOVE_BLIP prop_save_house_blip[24]

	REMOVE_PICKUP save_housepickup[5]
	REMOVE_PICKUP save_housepickup[23]
	REMOVE_PICKUP save_housepickup[24]

RETURN

remove_prop_blips4:

	REMOVE_BLIP prop_save_house_blip[4]
	REMOVE_BLIP prop_save_house_blip[6]
	REMOVE_BLIP prop_save_house_blip[7]
	REMOVE_BLIP prop_save_house_blip[9]
	REMOVE_BLIP prop_save_house_blip[16]
	REMOVE_BLIP prop_save_house_blip[17]
	REMOVE_BLIP prop_save_house_blip[27]
	REMOVE_BLIP prop_save_house_blip[28]
	REMOVE_BLIP prop_save_house_blip[29]

	REMOVE_PICKUP save_housepickup[4]
	REMOVE_PICKUP save_housepickup[6]
	REMOVE_PICKUP save_housepickup[7]
	REMOVE_PICKUP save_housepickup[9]
	REMOVE_PICKUP save_housepickup[16]
	REMOVE_PICKUP save_housepickup[17]
	REMOVE_PICKUP save_housepickup[27]
	REMOVE_PICKUP save_housepickup[28]
	REMOVE_PICKUP save_housepickup[29]

RETURN


little_casino_cut:

{

SCRIPT_NAME LITCAS

little_casino_cut_inner:

	WAIT 100

	IF IS_PLAYER_PLAYING player1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1941.1461 997.5668 20.0 100.0 100.0 20.0 FALSE
			GOSUB mini_fade
			IF IS_PLAYER_PLAYING player1
				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL player1 OFF
			ENDIF
			LOAD_SCENE 2039.7322 1006.7711 14.7353
			SET_FIXED_CAMERA_POSITION 2040.7255 1006.7479 14.8482 0.0 0.0 0.0 //shot of Casino
			POINT_CAMERA_AT_POINT 2039.7322 1006.7711 14.7353 JUMP_CUT
			DO_FADE 500 FADE_IN
		    CLEAR_PRINTS
			PRINT_NOW WUZ_HLP 5000 1 //walk into the casino

		    WAIT 5000
			GOSUB mini_fade
			IF IS_PLAYER_PLAYING player1
				SET_CAMERA_BEHIND_PLAYER
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				RESTORE_CAMERA_JUMPCUT
				CLEAR_PRINTS
			ENDIF
			DO_FADE 500 FADE_IN
		    TERMINATE_THIS_SCRIPT
		ENDIF
	ENDIF

GOTO little_casino_cut_inner
	
}


mini_fade:

	SET_FADING_COLOUR 0 0 0
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

