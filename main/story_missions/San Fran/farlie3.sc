MISSION_START

// *****************************************************************************************
// *****************************************************************************************
// ***************************************** "THE HOSTAGE"     *****************************
// ******************************************** DRIVER 3 ***********************************
// *****************************************************************************************
// *****************************************************************************************

{

SCRIPT_NAME driv3

// Mission start stuff
GOSUB mission_start_driv3
	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_driv3
	ENDIF
GOSUB mission_cleanup_driv3

MISSION_END


// *****************************
// VARIABLES
// *****************************

// VEHICLES
LVAR_INT get_away_car

LVAR_INT car_van_with_buddy_inside

LVAR_INT car_escorts_farlie3[2]
LVAR_INT car_players_car_farlie3

LVAR_INT passenger_seats_farlie3



// OBJECTS

//LVAR_INT invisible_object_farlie3


// CHARACTERS
LVAR_INT char_toreno_farlie3
LVAR_INT van_driver_farlie3
LVAR_INT char_baddies_farlie3[9]
LVAR_INT char_escorts_farlie3[2]

LVAR_INT char_tbone_farlie3

// GROUPS

LVAR_INT random_location_number


LVAR_INT pickup_gun_next_to_guard
 

//COORDS
LVAR_FLOAT coord_random_location_x coord_random_location_y coord_random_location_z

LVAR_FLOAT coord_base_x coord_base_y coord_base_z 
LVAR_FLOAT coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z

LVAR_FLOAT coord_van_x coord_van_y coord_van_z


LVAR_FLOAT coord_current_players_X_driv3 coord_current_players_y_driv3 coord_current_players_z_driv3
LVAR_FLOAT coord_airport_x_driv3 coord_airport_y_driv3 coord_airport_z_driv3

LVAR_FLOAT offset_toreno_farlie3_x offset_toreno_farlie3_y offset_toreno_farlie3_z 
LVAR_FLOAT coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z

LVAR_FLOAT coord_spray_shop_x_farlie3 coord_spray_shop_y_farlie3 coord_spray_shop_z_farlie3



LVAR_FLOAT cops_heading_farlie3
LVAR_FLOAT van_heading_farlie3
LVAR_FLOAT heading_toreno

// Decision makers

LVAR_INT dm_goons_farlie3
LVAR_INT dm_buddies_farlie3
	   


//CAMERAS

LVAR_FLOAT float_distance_player_girl 
//VAR_INT int_distance_player_girl  


//FLAGS
// LVAR_INT flag_get_to_payphone
LVAR_INT flag_get_to_location_of_girl
LVAR_INT flag_shoot_correct_container

LVAR_INT flag_get_back_to_base
LVAR_INT flag_mission_passed_farlie3 
LVAR_INT flag_mission_failed_farlie3
VAR_INT flag_phone_dialog_driv3
LVAR_INT flag_torch_truck_farlie3 
LVAR_INT flag_van_drives_off 
LVAR_INT flag_has_truck_been_destroyed_farlie3
LVAR_INT flag_player_away_from_truck_farlie3
LVAR_INT flag_goons_get_out
LVAR_INT flag_initialise_farlie3 
LVAR_INT flag_reached_airport_exit 
LVAR_INT flag_cops_see_cocaine 
LVAR_INT flag_converstion_going_on_farlie3
LVAR_INT flag_battery_power_comments

LVAR_INT flag_has_player_got_wanted_level

LVAR_INT flag_display_flee_warning_farlie3

LVAR_INT play_audio_flag_farlie3 

LVAR_INT flag_cops_go_after_player
LVAR_INT flag_player_starts_conversation_with_toreno


LVAR_INT flag_is_tbone_in_group_farlie3
LVAR_INT flag_is_toreno_in_group_farlie3
LVAR_INT flag_toreno_comment
LVAR_INT flag_is_player_in_car 

LVAR_INT max_number_in_players_group




//BLIPS
LVAR_INT blip_players_car
LVAR_INT blip_van_farlie3
LVAR_INT blip_airport_farlie3
LVAR_INT blip_van_driver
LVAR_INT blip_escorts_farlie3[2]  
LVAR_INT blip_base
LVAR_INT blip_torneo
LVAR_INT blip_tbone
LVAR_INT blip_airport_exit
LVAR_INT blip_baddies_passengers[3]	
LVAR_INT blip_destination_farlie3

LVAR_INT blip_paint_and_spray_farlie3

//COUNTER
// VAR_INT timer_countdown_farlie3


// Calculation LVARiables
LVAR_FLOAT float_temp_calculation_a_driv3 float_temp_calculation_b_driv3 

LVAR_INT index_farlie3 index_dialogue_farlie3


// SOUND VARIABLES

LVAR_INT farlie3_audio[40] 
VAR_TEXT_LABEL $farlie3_text[40]







// START MISSION  *****************************

mission_start_driv3:
	flag_player_on_mission = 1
	REGISTER_MISSION_GIVEN
	WAIT 0
// ****************************************************************************

// INITIALISE VARIABLES

SET_PLAYER_CONTROL Player1 OFF
DO_FADE 1000 FADE_OUT 
SET_FADING_COLOUR 0 0 0

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

LOAD_MISSION_TEXT FARLIE3


SET_AREA_VISIBLE 3

LOAD_CUTSCENE FARL_3A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
            WAIT 0
ENDWHILE

CLEAR_CUTSCENE 

SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE 

SET_AREA_VISIBLE 0


//LVAR_INT stored_flying_skill


//GET_INT_STAT FLYING_SKILL stored_flying_skill
//SET_INT_STAT FLYING_SKILL 300




ADD_BLIP_FOR_COORD -2071.0 209.0 35.0 blip_destination_farlie3
REMOVE_BLIP blip_destination_farlie3 

coord_spray_shop_x_farlie3 = -1906.656
coord_spray_shop_y_farlie3 = 283.016
coord_spray_shop_z_farlie3 = 41.0


flag_has_player_got_wanted_level = 0

flag_display_flee_warning_farlie3 = 0

play_audio_flag_farlie3 = 0

index_dialogue_farlie3 = 0


flag_cops_go_after_player = 0
flag_is_player_in_car = 0
flag_player_starts_conversation_with_toreno = 0


coord_base_x = -2615.0
coord_base_y = 1397.0
coord_base_z = 6.0


flag_cops_see_cocaine = 0
int_distance_player_girl = 0


flag_is_farlie3_running = 1  // a flag that lets christians airport ped brains know the mission is running


flag_battery_power_comments = 0

coord_airport_x_driv3 = -1550.4158 	
coord_airport_y_driv3 = -436.4436 
coord_airport_z_driv3 =	 6.9871

timer_countdown_farlie3 = 0

offset_toreno_farlie3_x = 0.0
offset_toreno_farlie3_y = -20.0
offset_toreno_farlie3_z = 0.0

flag_reached_airport_exit = 0

flag_converstion_going_on_farlie3 = 0

flag_is_tbone_in_group_farlie3 = 0
flag_is_toreno_in_group_farlie3	= 0




//FLAGS
// flag_get_to_payphone = 1
flag_get_to_location_of_girl = 1
flag_shoot_correct_container = 0
flag_get_back_to_base = 0
flag_mission_passed_farlie3 = 0
flag_mission_failed_farlie3 = 0
flag_phone_dialog_driv3 = 0
flag_torch_truck_farlie3 = 0
flag_van_drives_off = 0
flag_goons_get_out = 0
flag_toreno_comment = 0
flag_initialise_farlie3 = 0


flag_has_truck_been_destroyed_farlie3 =	0
flag_player_away_from_truck_farlie3	= 0


// REMOVING SPRAYSHOP BLIPS

IF DOES_BLIP_EXIST spray_shop[4]
	REMOVE_BLIP spray_shop[4] //Near Hub
ENDIF



	

CREATE_PICKUP_WITH_AMMO DESERT_EAGLE PICKUP_ON_STREET_SLOW 30 -1542.0222 -437.7364 5.9258 pickup_gun_next_to_guard



// first batch of clues
	$farlie3_text[0] = &FAR3_CA  // tbone HE CAN HEAR GULS
	$farlie3_text[1] = &FAR3_CB  // tbone Mike can hear gulls!
	$farlie3_text[2] = &FAR3_DA	 //	carl Gulls? Shit, could be anywhere in this town! 
	$farlie3_text[3] = &FAR3_CC  //	He can hear heavy machinery!
	$farlie3_text[4] = &FAR3_DB  //	Gulls and heavy machinery? A building site, or a landfill?	
	$farlie3_text[5] = &FAR3_EA  ////	There’s a building site in Doherty!		 


	farlie3_audio[0] = SOUND_FAR3_CA // tbone HE CAN HEAR GULS
	farlie3_audio[1] = SOUND_FAR3_CB // tbone Mike can hear gulls!
	farlie3_audio[2] = SOUND_FAR3_DA //	carl Gulls? Shit, could be anywhere in this town! 
	farlie3_audio[3] = SOUND_FAR3_CC //	He can hear heavy machinery!
	farlie3_audio[4] = SOUND_FAR3_DB //	Gulls and heavy machinery? A building site, or a landfill?	
	farlie3_audio[5] = SOUND_FAR3_EA ////	There’s a building site in Doherty!		

							 
// second batch of clues
	$farlie3_text[6] = &FAR3_CF // he can hear a truck reversing
	$farlie3_text[7] = &FAR3_CD	// He says it’s busy, like a freight depot or something.
	$farlie3_text[8] = &FAR3_DC //	Freight? They must be down at the docks!	
	$farlie3_text[9] = &FAR3_EB //	Get to the docks in Easter Basin!

	farlie3_audio[6]  = SOUND_FAR3_CF // he can hear a truck reversing
	farlie3_audio[7]  = SOUND_FAR3_CD	// He says it’s busy, like a freight depot or something.
	farlie3_audio[8]  = SOUND_FAR3_DC //	Freight? They must be down at the docks!	
	farlie3_audio[9]  = SOUND_FAR3_EB //	Get to the docks in Easter Basin!


// 3rd set of clues
	$farlie3_text[10] = &FAR3_CJ   //Shit, he says they stopped, then he heard gunfire.
	$farlie3_text[11] = &FAR3_CK //	He thinks they just shot there way through a security gate! 
	$farlie3_text[12] = &FAR3_DD // CJ:  They don’t have heavy security at the docks,
	$farlie3_text[13] = &FAR3_DE // CJ:  But they do at the airport!!
	$farlie3_text[14] = &FAR3_EC // tbone get to the airport!!


	farlie3_audio[10]  =SOUND_FAR3_CJ //Shit, he says they stopped, then he heard gunfire.
	farlie3_audio[11]  =SOUND_FAR3_CK//	He thinks they just shot there way through a security gate!  
	farlie3_audio[12]  =SOUND_FAR3_DD // CJ:  They don’t have heavy security at the docks, 
	farlie3_audio[13]  =SOUND_FAR3_DE // CJ:  But they do at the airport!! 
	farlie3_audio[14]  =SOUND_FAR3_EC // tbone get to the airport!!

// arriving at the airport
	$farlie3_text[15] = &FAR3_HA    //Theres teh gate! 
	$farlie3_text[16] = &FAR3_HB    //the dead security
	$farlie3_text[17] = &FAR3_HC    //Keep your eyes peeled
		  		  
	farlie3_audio[15]  = SOUND_FAR3_HA//Theres teh gate! 
	farlie3_audio[16]  = SOUND_FAR3_HB//the dead security
	farlie3_audio[17]  = SOUND_FAR3_HC//Keep your eyes peeled

// instructions on how to use the signal reader

	$farlie3_text[18] = &FAR3_JA// 	ITS WORKING!
	$farlie3_text[19] = &FAR3_JB //	Tag? What you talking about?
	$farlie3_text[20] = &FAR3_JC //	 After the last bit of trouble, Mike hid transponder in the white.
	$farlie3_text[21] = &FAR3_JD //	 We was going to follow it to the gang, but something must have fucked up 
	$farlie3_text[22] = &FAR3_JE //	 and now we gotta use it to find that van and rescue Mike!
	$farlie3_text[23] = &FAR3_JF 	// How’s it work?
	$farlie3_text[24] = &FAR3_JG // Simple. Closer we get, the stronger the signal



				  


	farlie3_audio[18]  = SOUND_FAR3_JA //TITS WORKING!
	farlie3_audio[19]  = SOUND_FAR3_JB // Tag? What you talking about?
	farlie3_audio[20]  = SOUND_FAR3_JC //  After the last bit of trouble, Mike hid transponder in the white.
	farlie3_audio[21]  = SOUND_FAR3_JD //  We was going to follow it to the gang, but something must have fucked up 
	farlie3_audio[22]  = SOUND_FAR3_JE //  and now we gotta use it to find that van and rescue Mike!
	farlie3_audio[23]  = SOUND_FAR3_JF // // How’s it work?
	farlie3_audio[24]  = SOUND_FAR3_JG // Simple. Closer we get, the stronger the signal



// bANTER BACK TO THE PLEASURE DOMES

	$farlie3_text[25] = &FAR3_MA		//How long you been working for Jizzy, I haven’t seen you before.
	$farlie3_text[26] = &FAR3_MB		//Just got into town last week, done a couple of jobs here and there.
	$farlie3_text[27] = &FAR3_MC		//Just got into town, eh? Where were you before that?
	$farlie3_text[28] = &FAR3_MD		//What is this?
	$farlie3_text[29] = &FAR3_ME		//Just answer the fucking question.
	$farlie3_text[30] = &FAR3_MF		//Ok, dudes, chill. I been in Los Santos, with my family, ok?
	$farlie3_text[31] = &FAR3_MG		//Gimme his wallet.
	$farlie3_text[32] = &FAR3_MH		//What? Hey, get off!
	$farlie3_text[33] = &FAR3_MJ		//Quit struggling and concentrate on the road!
	$farlie3_text[34] = &FAR3_MK		//Here y’go, Mike.
	$farlie3_text[35] = &FAR3_ML		//Carl Johnson, eh.
	$farlie3_text[36] = &FAR3_MM		//Ok. I’ve seen enough. Here.
	$farlie3_text[37] = &FAR3_MN		//There was a twenty spot in there, it batter still be there, man.
	$farlie3_text[38] = &FAR3_MO		//Shut the fuck up.

	farlie3_audio[25] = SOUND_FAR3_MA		//How long you been working for Jizzy, I haven’t seen you before.
	farlie3_audio[26] = SOUND_FAR3_MB		//Just got into town last week, done a couple of jobs here and there.
	farlie3_audio[27] = SOUND_FAR3_MC		//Just got into town, eh? Where were you before that?
	farlie3_audio[28] = SOUND_FAR3_MD		//What is this?
	farlie3_audio[29] = SOUND_FAR3_ME		//Just answer the fucking question.
	farlie3_audio[30] = SOUND_FAR3_MF		//Ok, dudes, chill. I been in Los Santos, with my family, ok?
	farlie3_audio[31] = SOUND_FAR3_MG		//Gimme his wallet.
	farlie3_audio[32] = SOUND_FAR3_MH		//What? Hey, get off!
	farlie3_audio[33] = SOUND_FAR3_MJ		//Quit struggling and concentrate on the road!
	farlie3_audio[34] = SOUND_FAR3_MK		//Here y’go, Mike.
	farlie3_audio[35] = SOUND_FAR3_ML		//Carl Johnson, eh.
	farlie3_audio[36] = SOUND_FAR3_MM		//Ok. I’ve seen enough. Here.
	farlie3_audio[37] = SOUND_FAR3_MN		//There was a twenty spot in there, it batter still be there, man.
	farlie3_audio[38] = SOUND_FAR3_MO		//Shut the fuck up.


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm_goons_farlie3
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm_buddies_farlie3





/* ************** Mission Start ********************************* */




	// ******************************************** REQUEST MODELS **********************************

   	REQUEST_MODEL micro_uzi
	REQUEST_MODEL BURRITO
	LOAD_SPECIAL_CHARACTER 2 tbone
	LOAD_ALL_MODELS_NOW
	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 2
	OR NOT HAS_MODEL_LOADED micro_uzi
	OR NOT HAS_MODEL_LOADED BURRITO
		WAIT 0
	ENDWHILE






	SET_CHAR_HEADING scplayer 214.0
	SET_CAMERA_BEHIND_PLAYER


	// ******************************************** START MAIN LOOP **********************************
		
	 main_loop:
		WAIT 0
			
			
		// get_to_the_girl
			IF flag_get_to_location_of_girl = 1
				GOSUB get_to_location_of_girl
			ENDIF

		// shoot correct container
			IF flag_shoot_correct_container = 1
				GOSUB shoot_correct_container
			ENDIF


		// police are coming. Must torch the cocaine truck
			IF flag_torch_truck_farlie3 = 1
				GOSUB torch_truck_farlie3
			ENDIF

			IF flag_get_back_to_base = 1
				GOSUB get_back_to_base
			ENDIF


			IF flag_mission_passed_farlie3 = 1
				GOTO mission_passed_driv3
			ENDIF

			IF flag_mission_failed_farlie3 = 1
				GOTO mission_failed_driv3
			ENDIF
  	   	

		GOTO main_loop

// ************************************     Sub Functions *****************************************



// *****************************
// *******  get_to_location_of_girl
// *****************************
get_to_location_of_girl:




	

   	GENERATE_RANDOM_INT_IN_RANGE 1 4 random_location_number
 	//random_location_number = 3

	//Hangers
	IF random_location_number = 1
	// int variables
		coord_random_location_x = -1303.7601  
		coord_random_location_y = -615.4308
		coord_random_location_z = 13.1485 

		coord_van_end_point_x = -1271.8324
		coord_van_end_point_y = -180.8179 
		coord_van_end_point_z = 13.1443
		
	   van_heading_farlie3 = 0.0
		


	ENDIF							 

	// behind termainal
	 IF random_location_number = 2
		coord_random_location_x = -1216.2330  
		coord_random_location_y = 51.3660 
		coord_random_location_z = 13.0 
	 //	van_heading_farlie3 = 260.0
  /*		coord_van_end_point_x = -1283.9607 
		coord_van_end_point_y = -156.9417 
		coord_van_end_point_z = 8.1484	  */

		coord_van_end_point_x = -1288.8324 
		coord_van_end_point_y = -159.8179 
		coord_van_end_point_z = 13.1443

	 	van_heading_farlie3 = 120.0







	ENDIF

	// End of runway
	IF random_location_number = 3
		coord_random_location_x = -1125.3193 				 
		coord_random_location_y = 368.3488 
		coord_random_location_z = 13.1329

	   //	van_heading_farlie3 = 0.0

	 /*	coord_van_end_point_x = -1384.2681 
		coord_van_end_point_y = 10.4847 
		coord_van_end_point_z = 8.1329   */

		coord_van_end_point_x = -1319.8324
		coord_van_end_point_y = -96.8179 
		coord_van_end_point_z = 13.8443

	   van_heading_farlie3 = 140.0



	ENDIF



	/*
	//AMMUNATION
	IF location_number = 3
		coord_random_location_x =
		coord_random_location_y =
		coord_random_location_z =
	ENDIF

	// CAR CRUSHERS
	IF location_number = 4
		coord_random_location_x =
		coord_random_location_y =
		coord_random_location_z =
	ENDIF
	 */


   //	ADD_BLIP_FOR_COORD coord_random_location_x coord_random_location_y coord_random_location_z blip_van_farlie3 //temp

	//GET_CHAR_COORDINATES scplayer coord_players_X_driv3 coord_players_y_driv3 coord_players_z_driv3
	//GET_DISTANCE_BETWEEN_COORDS_3D coord_players_X_driv3 coord_players_y_driv3 coord_players_z_driv3 coord_random_location_x coord_random_location_y coord_random_location_z distance_player_girl

	REQUEST_MODEL PREMIER
	WHILE NOT HAS_MODEL_LOADED PREMIER
		WAIT 0
	ENDWHILE

	CREATE_CAR PREMIER -2620.0 1398.0 6.5 car_players_car_farlie3
	SET_CAR_HEADING car_players_car_farlie3 204.0 
	ADD_BLIP_FOR_CAR car_players_car_farlie3 blip_players_car
	SET_BLIP_AS_FRIENDLY blip_players_car TRUE 

	SET_CAR_HEALTH car_players_car_farlie3 2500
   //	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 2

	FIND_MAX_NUMBER_OF_GROUP_MEMBERS	max_number_in_players_group
	SET_SCRIPT_LIMIT_TO_GANG_SIZE max_number_in_players_group
	//GET_INT_STAT CYCLE_SKILL stat_read_skill_temp


	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 2

	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

	ENDIF

  //	CREATE_CHAR PEDTYPE_MISSION2 wmymoun -2623.64 1403.9 6.2 char_tbone_farlie3

	
	CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 -2623.64 1403.9 6.2 char_tbone_farlie3
	 SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR char_tbone_farlie3 FALSE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddies_farlie3 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
	SET_CHAR_ACCURACY char_tbone_farlie3 95
	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH char_tbone_farlie3 TRUE 
	TASK_ENTER_CAR_AS_PASSENGER char_tbone_farlie3 car_players_car_farlie3 -1 0
	SET_CHAR_DROWNS_IN_WATER char_tbone_farlie3 TRUE 
	SET_CHAR_RELATIONSHIP char_tbone_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
	SET_CHAR_RELATIONSHIP char_tbone_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
 	SET_CHAR_DECISION_MAKER char_tbone_farlie3 dm_buddies_farlie3
	GIVE_WEAPON_TO_CHAR char_tbone_farlie3 WEAPONTYPE_MICRO_UZI 1000
	
	SET_CHAR_NEVER_TARGETTED char_tbone_farlie3 TRUE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER char_tbone_farlie3 TRUE	

	LOAD_SCENE_IN_DIRECTION	-2623.64 1403.9 6.2 	214.0

	DO_FADE 1000 FADE_IN 

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


  	SET_PLAYER_CONTROL player1 ON  
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT	 

	timer_countdown_farlie3 += 330000
	DISPLAY_ONSCREEN_TIMER_WITH_STRING timer_countdown_farlie3 TIMER_DOWN ( dri2_9 ) // The Timer	
	CLEAR_MISSION_AUDIO 1
	LOAD_MISSION_AUDIO 1 SOUND_FAR3_FA
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	 	WAIT 0
	ENDWHILE  

	PRINT_NOW FAR3_FA 10000 1 // Gotta make this qick, Mike hasn’t got much battery time left!
	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT FAR3_FA

	PRINT_NOW dri2_1 5000 1

	REQUEST_MODEL dnb1 
	REQUEST_MODEL dnb2	
	REQUEST_MODEL dnb3
	WHILE NOT HAS_MODEL_LOADED dnb1 
		OR NOT HAS_MODEL_LOADED dnb2
		OR NOT HAS_MODEL_LOADED dnb3	
		WAIT 0
	ENDWHILE 




	IF NOT IS_CAR_DEAD car_players_car_farlie3
		WHILE NOT IS_CHAR_IN_CAR scplayer car_players_car_farlie3
			WAIT 0
			IF IS_CHAR_DEAD	char_tbone_farlie3

		            flag_get_to_location_of_girl = 0
					flag_shoot_correct_container = 0
				    flag_torch_truck_farlie3 = 0
					flag_get_back_to_base = 0
					flag_mission_passed_farlie3 = 0
					flag_mission_failed_farlie3 = 1
					PRINT_NOW dri2_31 5000 1 // tbone is dead
					RETURN
			ENDIF

			IF IS_CAR_DEAD car_players_car_farlie3
				GOTO exit_get_in_car_loop_farlie3
			ENDIF

			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	            flag_get_to_location_of_girl = 0
				flag_shoot_correct_container = 0
			    flag_torch_truck_farlie3 = 0
				flag_get_back_to_base = 0
				flag_mission_passed_farlie3 = 1
				flag_mission_failed_farlie3 = 0
				RETURN
			ENDIF

			// if player runs out of time
			IF timer_countdown_farlie3 <= 0
				flag_get_to_location_of_girl = 0
				flag_mission_failed_farlie3 = 1
				// play a sound of an explosion over the phone
				CLEAR_MISSION_AUDIO 1

				LOAD_MISSION_AUDIO 1 SOUND_FAR3_FD
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				 	WAIT 0
				ENDWHILE  

				PRINT_NOW FAR3_FD 10000 1 // shit, the phones gone dead!
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
			   //	CLEAR_THIS_PRINT FAR3_FD
			   //	PRINT_NOW ( FAR3_FD ) 5000 1	 // shit, the phones gone dead!









				RETURN
			ENDIF
		ENDWHILE
	ENDIF

exit_get_in_car_loop_farlie3:

	IF NOT IS_CHAR_DEAD char_tbone_farlie3
	   	CLEAR_THIS_PRINT dri2_1
		IF NOT IS_GROUP_MEMBER char_tbone_farlie3 Players_Group
			SET_GROUP_MEMBER Players_Group char_tbone_farlie3
		ENDIF
	ENDIF

		REMOVE_BLIP blip_players_car 
		 
		MARK_MODEL_AS_NO_LONGER_NEEDED PREMIER








	TIMERB = 0

	
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD coord_airport_x_driv3 coord_airport_y_driv3 coord_airport_z_driv3 RADAR_SPRITE_MAP_HERE blip_airport_farlie3
	REMOVE_BLIP blip_airport_farlie3 
  //	flag_phone_dialog_driv3 = 8
	




   //	PRINT_NOW ( dri2_6 ) 5000 1	//FIND THE GIRL BEFORE THE BOMB GOES OFF

	// Wait until player is in visinity of girls location
	IF NOT IS_CHAR_DEAD scplayer 
	   	WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_airport_x_driv3 coord_airport_y_driv3 coord_airport_z_driv3 35.0 35.0 35.0 FALSE
  //		WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_airport_x_driv3 coord_airport_y_driv3 coord_airport_z_driv3 35.0 35.0 35.0 FALSE

			WAIT 0
		 	   //	VIEW_INTEGER_VARIABLE index_dialogue_farlie3 index_dialogue_farlie3



			GOSUB tbone_checks_farlie3
			IF flag_mission_failed_farlie3 = 1
				RETURN
			ENDIF

			IF flag_is_tbone_in_group_farlie3 = 0
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN scplayer car_players_car_farlie3 	
				ENDIF


				// first batch of clues
				IF flag_phone_dialog_driv3 >= 0
				AND flag_phone_dialog_driv3 < 6 
					GOSUB load_and_play_audio_farlie3	
				ENDIF

			
				IF flag_phone_dialog_driv3 = 6
					IF TIMERA > 15000
				   //	Just hang in there, Mike, help is on the way!

						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_FAR3_GC
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						 	WAIT 0
						ENDWHILE  

						PRINT_NOW FAR3_GC 10000 1 
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT FAR3_GC

						TIMERA=0



 
						flag_phone_dialog_driv3 = 7
						flag_converstion_going_on_farlie3 = 0
					ENDIF
				ENDIF
			





 

		//next set of clues. telling player to get to the harbour
				IF flag_phone_dialog_driv3 = 7
					IF NOT IS_CHAR_DEAD char_tbone_farlie3
						IF IS_CHAR_IN_ZONE scplayer DOH
					 	AND IS_CHAR_IN_ZONE char_tbone_farlie3 DOH
							flag_phone_dialog_driv3 = 8
							index_dialogue_farlie3 = 6 	// He says it’s busy, like a freight depot or something.
						ENDIF
					ENDIF
				ENDIF

		// SECOND batch of clues
				IF flag_phone_dialog_driv3 >= 8
				AND flag_phone_dialog_driv3 < 12 
					GOSUB load_and_play_audio_farlie3	
				ENDIF




				IF flag_phone_dialog_driv3 = 12
					IF TIMERA > 10000
					   //	PRINT_NOW ( FAR3_GB ) 5000 1	//	We’ll be there any minute, Mike!	
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_FAR3_GB
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						 	WAIT 0
						ENDWHILE  

						PRINT_NOW FAR3_GB 10000 1 
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT FAR3_GB
	

	  
						TIMERA=0

						flag_phone_dialog_driv3 = 13
						flag_converstion_going_on_farlie3 = 0
					ENDIF
				ENDIF
			






			//next set of clues. telling player to get to the airport
				IF flag_phone_dialog_driv3 = 13
					IF NOT IS_CHAR_DEAD char_tbone_farlie3
						IF IS_CHAR_IN_ZONE scplayer EASB
					 	AND IS_CHAR_IN_ZONE char_tbone_farlie3 EASB
							flag_phone_dialog_driv3 = 14
							index_dialogue_farlie3 = 10 	// He says it’s busy, like a freight depot or something.

							TIMERA=0
						ENDIF
					ENDIF
				ENDIF


			// third batch of clues
				IF flag_phone_dialog_driv3 >= 14
				AND flag_phone_dialog_driv3 < 19 
					GOSUB load_and_play_audio_farlie3	
				ENDIF


				IF flag_phone_dialog_driv3 = 19
					IF TIMERA > 8000
						TIMERA=0
						flag_phone_dialog_driv3 = 20
				 //		PRINT_NOW FAR3_CH 5000 1 // tbone: he can hear planes taking off

							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_FAR3_CH
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							ENDWHILE  

							PRINT_NOW FAR3_CH 10000 1 
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT FAR3_CH

						
					ENDIF
				ENDIF

				IF flag_phone_dialog_driv3 = 20
					IF TIMERA > 8000
						TIMERA=0
						flag_phone_dialog_driv3 = 21
				  //		PRINT_NOW FAR3_GA 5000 1 // tbone: we're nearly there
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_FAR3_GA
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							ENDWHILE  

							PRINT_NOW FAR3_GA 10000 1 
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT FAR3_GA

						flag_converstion_going_on_farlie3 = 0
					ENDIF
				ENDIF






				IF flag_converstion_going_on_farlie3 = 0
					IF flag_battery_power_comments = 0
						IF timer_countdown_farlie3 <= 200000  
					  //		PRINT_NOW FAR3_FB 5000 1 // His battery running low!
							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_FAR3_FB
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							ENDWHILE  

							PRINT_NOW FAR3_FB 10000 1 
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT FAR3_FB




							flag_battery_power_comments = 1
						ENDIF
					ENDIF
				ENDIF

				IF flag_converstion_going_on_farlie3 = 0
					IF flag_battery_power_comments = 1
						IF timer_countdown_farlie3 <= 100000  
						   CLEAR_MISSION_AUDIO 1
						   LOAD_MISSION_AUDIO 1 SOUND_FAR3_FC
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							ENDWHILE  

							PRINT_NOW FAR3_FC 10000 1 
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT FAR3_FC				
							flag_battery_power_comments = 2
						ENDIF
					ENDIF
				ENDIF




				// if player runs out of time
				IF timer_countdown_farlie3 <= 0
					flag_get_to_location_of_girl = 0
					flag_mission_failed_farlie3 = 1
					// play a sound of an explosion over the phone
					//PRINT_NOW ( FAR3_FD ) 5000 1	 // shit, the phones gone dead!

					 CLEAR_MISSION_AUDIO 1

					 LOAD_MISSION_AUDIO 1 SOUND_FAR3_FD
					 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					  	WAIT 0
					 ENDWHILE  

					 PRINT_NOW FAR3_FD 10000 1 
					 PLAY_MISSION_AUDIO 1
					 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					 	WAIT 0
					 ENDWHILE
				   //	 CLEAR_THIS_PRINT FAR3_FD	




					RETURN
				ENDIF








			ENDIF
		ENDWHILE
	ENDIF
	
  //	REMOVE_BLIP blip_airport_driv3


	
    //DISPLAY_ONSCREEN_COUNTER_WITH_STRING int_distance_player_girl COUNTER_DISPLAY_NUMBER dri2_10	// The distance	(counter
	CLEAR_HELP
	REMOVE_BLIP blip_airport_farlie3
	REMOVE_BLIP blip_destination_farlie3







	REQUEST_MODEL freeway

	WHILE NOT HAS_MODEL_LOADED freeway
		WAIT 0
	ENDWHILE

	  //		IF flag_phone_dialog_driv3 >= 24
				DISPLAY_ONSCREEN_COUNTER_WITH_STRING int_distance_player_girl COUNTER_DISPLAY_BAR dri2_17	// The distance	(bar form)
	//		ENDIF

		 



   //	CREATE_CAR BURRITO coord_random_location_x coord_random_location_y coord_random_location_z car_van_with_buddy_inside

   	  //	CREATE_CAR BURRITO coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z car_van_with_buddy_inside

		CREATE_CAR BURRITO coord_random_location_x coord_random_location_y coord_random_location_z car_van_with_buddy_inside
		CHANGE_CAR_COLOUR car_van_with_buddy_inside 6 CARCOLOUR_BLACK
		ADD_BLIP_FOR_CAR car_van_with_buddy_inside blip_van_farlie3
		CHANGE_BLIP_DISPLAY blip_van_farlie3 MARKER_ONLY
		SET_CAR_HEADING car_van_with_buddy_inside van_heading_farlie3

  //	IF NOT IS_CAR_DEAD car_van_with_buddy_inside

		

		CREATE_CHAR_INSIDE_CAR car_van_with_buddy_inside PEDTYPE_MISSION1 dnb3 van_driver_farlie3
		
		SET_CHAR_PROOFS van_driver_farlie3 TRUE FALSE FALSE FALSE FALSE
		SET_CHAR_SUFFERS_CRITICAL_HITS van_driver_farlie3 FALSE
		SET_CHAR_DECISION_MAKER van_driver_farlie3 dm_goons_farlie3
		SET_CHAR_RELATIONSHIP van_driver_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	
		SET_CHAR_RELATIONSHIP van_driver_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1
		ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_goons_farlie3 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE

		SET_CAR_HEALTH car_van_with_buddy_inside 1000
		LOCK_CAR_DOORS car_van_with_buddy_inside CARLOCK_LOCKED_BUT_CAN_BE_DAMAGED
		SET_CAR_ONLY_DAMAGED_BY_PLAYER car_van_with_buddy_inside TRUE


		// creating van passengers
		index_farlie3 = 0
		WHILE index_farlie3 < 3
			IF index_farlie3 = 0
			OR index_farlie3 = 2
				CREATE_CHAR_AS_PASSENGER car_van_with_buddy_inside PEDTYPE_MISSION1 dnb1 index_farlie3 char_baddies_farlie3[index_farlie3]
			ENDIF

			IF index_farlie3 = 1
				CREATE_CHAR_AS_PASSENGER car_van_with_buddy_inside PEDTYPE_MISSION1 dnb2 index_farlie3 char_baddies_farlie3[index_farlie3]
			ENDIF
			
			GIVE_WEAPON_TO_CHAR  char_baddies_farlie3[index_farlie3] WEAPONTYPE_MICRO_UZI 1000
		   	SET_CURRENT_CHAR_WEAPON char_baddies_farlie3[index_farlie3] WEAPONTYPE_MICRO_UZI
			IF index_farlie3 = 0 
			  //	TASK_DRIVE_BY char_baddies_farlie3[0] scplayer -1 -1.0 0.0 0.0 20.0 DRIVEBY_AI_ALL_DIRN TRUE 100
				SET_CHAR_DECISION_MAKER char_baddies_farlie3[0] dm_goons_farlie3
				SET_CHAR_RELATIONSHIP char_baddies_farlie3[0] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				SET_CHAR_RELATIONSHIP char_baddies_farlie3[0] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1	
			ENDIF



			index_farlie3++
		ENDWHILE

		// creating escorts for van
		IF NOT IS_CAR_DEAD car_van_with_buddy_inside
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 2.0 -6.0 -0.3 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
			CREATE_CAR freeway coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z car_escorts_farlie3[0]
			SET_CAR_HEADING car_escorts_farlie3[0] van_heading_farlie3
			CREATE_CHAR_INSIDE_CAR car_escorts_farlie3[0] PEDTYPE_MISSION1 dnb2 char_escorts_farlie3[0]
			GIVE_WEAPON_TO_CHAR  char_escorts_farlie3[0] WEAPONTYPE_MICRO_UZI 1000
			SET_CURRENT_CHAR_WEAPON char_escorts_farlie3[0] WEAPONTYPE_MICRO_UZI
			SET_CHAR_DECISION_MAKER char_escorts_farlie3[0] dm_goons_farlie3
			SET_CHAR_RELATIONSHIP char_escorts_farlie3[0] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_RELATIONSHIP char_escorts_farlie3[0] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1		
	 //		SET_CAR_ESCORT_CAR_RIGHT car_escorts_farlie3[0] car_van_with_buddy_inside
			SET_CAR_STRAIGHT_LINE_DISTANCE car_escorts_farlie3[0] 255
			

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside -2.0 -6.0 -0.3 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
			CREATE_CAR freeway coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z car_escorts_farlie3[1]
			SET_CAR_HEADING car_escorts_farlie3[1] van_heading_farlie3
			CREATE_CHAR_INSIDE_CAR car_escorts_farlie3[1] PEDTYPE_MISSION1 dnb1 char_escorts_farlie3[1]
			GIVE_WEAPON_TO_CHAR  char_escorts_farlie3[1] WEAPONTYPE_MICRO_UZI 1000
			SET_CURRENT_CHAR_WEAPON char_escorts_farlie3[1] WEAPONTYPE_MICRO_UZI
			SET_CHAR_DECISION_MAKER char_escorts_farlie3[1] dm_goons_farlie3
			SET_CHAR_RELATIONSHIP char_escorts_farlie3[1] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_RELATIONSHIP char_escorts_farlie3[1] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1
		  //	SET_CAR_ESCORT_CAR_LEFT car_escorts_farlie3[1] car_van_with_buddy_inside
			SET_CAR_STRAIGHT_LINE_DISTANCE car_escorts_farlie3[1] 255
		ENDIF		
	





// STORING INITIAL DISTANCE
 /*	GET_CHAR_COORDINATES scplayer coord_current_players_X_driv3 coord_current_players_y_driv3 coord_current_players_z_driv3
	GET_DISTANCE_BETWEEN_COORDS_3D coord_current_players_X_driv3 coord_current_players_y_driv3 coord_current_players_z_driv3 oord_van_x coord_van_y coord_van_z float_initial_distance_player_girl
	*/


		IF NOT flag_phone_dialog_driv3 = 21

			index_dialogue_farlie3 = 18 	// its working!
			flag_phone_dialog_driv3 = 24	 // IF PLAYER DOES A CHEEKY ONE AND GETS TO THE AIRPORT EARLY
		ENDIF



	

   //	WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_random_location_x coord_random_location_y coord_random_location_z 30.0 30.0 20.0 FALSE

	WHILE NOT flag_goons_get_out = 1
		WAIT 0						   

		GOSUB tbone_checks_farlie3
		IF flag_mission_failed_farlie3 = 1
			RETURN
		ENDIF


	   // THEY ARRIVE AT THE AIRPORT
		IF flag_phone_dialog_driv3 >= 21
		AND flag_phone_dialog_driv3 < 24 
			flag_converstion_going_on_farlie3 = 1
			GOSUB load_and_play_audio_farlie3	
		ENDIF




	   // EXPLAINING TEH SIGNAL STENGTH
		IF flag_phone_dialog_driv3 >= 24
		AND flag_phone_dialog_driv3 <= 30 
			GOSUB load_and_play_audio_farlie3	
			flag_converstion_going_on_farlie3 = 1

		ENDIF

		IF flag_van_drives_off = 0
			IF flag_phone_dialog_driv3 = 31
				PRINT_NOW dri2_18 5000 1	 // use signal  strength to find farlie
				flag_phone_dialog_driv3 = 32
				TIMERA = 0
			ENDIF
		ELSE
			flag_phone_dialog_driv3 = 32
		ENDIF


		IF flag_phone_dialog_driv3 = 32
			IF TIMERA > 7000 
				flag_converstion_going_on_farlie3 = 0
				flag_phone_dialog_driv3 = 33
			ENDIF
		ENDIF


		IF NOT IS_CAR_DEAD car_van_with_buddy_inside 


			IF LOCATE_CHAR_ANY_MEANS_3D scplayer coord_van_x coord_van_y coord_van_z 30.0 30.0 30.0 FALSE
			AND LOCATE_CAR_3D car_van_with_buddy_inside coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z  40.0 40.0 40.0 FALSE
				flag_goons_get_out = 1
			ENDIF
			 

			IF flag_van_drives_off = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer coord_van_x coord_van_y coord_van_z 100.0 100.0 100.0 FALSE
				AND IS_CAR_ON_SCREEN car_van_with_buddy_inside
				  //	 SET_CAR_CRUISE_SPEED car_van_with_buddy_inside 20.0
					// CAR_GOTO_COORDINATES car_van_with_buddy_inside  coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 
					// SET_CAR_DRIVING_STYLE  car_van_with_buddy_inside DRIVINGMODE_AVOIDCARS
					IF NOT IS_CHAR_DEAD van_driver_farlie3
						TASK_CAR_DRIVE_TO_COORD van_driver_farlie3 car_van_with_buddy_inside coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 25.0 MODE_ACCURATE 0 DRIVINGMODE_PLOUGHTHROUGH
					ENDIF

					IF NOT	IS_CAR_DEAD car_escorts_farlie3[0]
						SET_CAR_ESCORT_CAR_RIGHT car_escorts_farlie3[0] car_van_with_buddy_inside
					ENDIF

					IF NOT	IS_CAR_DEAD car_escorts_farlie3[1]
						SET_CAR_ESCORT_CAR_LEFT car_escorts_farlie3[1] car_van_with_buddy_inside
					ENDIF



				


				 //	PRINT_NOW FAR3_KD 5000 1   // Shit, we're driving off again!

					 CLEAR_MISSION_AUDIO 1
					 LOAD_MISSION_AUDIO 1 SOUND_FAR3_KD
					 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					  	WAIT 0
					 ENDWHILE  

					 PRINT_NOW FAR3_KD 10000 1 
					 PLAY_MISSION_AUDIO 1
					 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					 	WAIT 0
					 ENDWHILE
					 CLEAR_THIS_PRINT FAR3_KD	



					CHANGE_BLIP_DISPLAY blip_van_farlie3 BOTH
					CLEAR_ONSCREEN_TIMER timer_countdown_farlie3

					CLEAR_ONSCREEN_COUNTER int_distance_player_girl

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer car_players_car_farlie3 	
					ENDIF


					flag_van_drives_off = 1
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD car_van_with_buddy_inside
				IF flag_van_drives_off = 1
				OR flag_van_drives_off = 2
					IF LOCATE_CAR_3D car_van_with_buddy_inside coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 20.0 20.0 20.0 FALSE

						SET_CAR_CRUISE_SPEED car_van_with_buddy_inside 0.0
						SET_CAR_MISSION car_van_with_buddy_inside MISSION_STOP_FOREVER 
						

						flag_van_drives_off = 3
					ENDIF
				ENDIF


																												   		  
		   		IF NOT IS_CAR_DEAD car_van_with_buddy_inside
					GET_CAR_COORDINATES car_van_with_buddy_inside coord_van_x coord_van_y coord_van_z
				ENDIF


				GET_CHAR_COORDINATES scplayer coord_current_players_X_driv3 coord_current_players_y_driv3 coord_current_players_z_driv3
				GET_DISTANCE_BETWEEN_COORDS_3D coord_current_players_X_driv3 coord_current_players_y_driv3 coord_current_players_z_driv3 coord_van_x coord_van_y coord_van_z float_distance_player_girl
		   	
  				IF random_location_number = 3	  // End of run way is too far away
				OR random_location_number = 2
					IF float_distance_player_girl > 1000.0
						float_distance_player_girl = 1000.0	
					ELSE
						float_temp_calculation_a_driv3 = 1000.0 - float_distance_player_girl
						float_temp_calculation_a_driv3 /= 10.0	
						
					ENDIF
		 		ELSE   

					IF float_distance_player_girl > 500.0
						float_distance_player_girl = 500.0	
					ELSE
						float_temp_calculation_a_driv3 = 500.0 - float_distance_player_girl
						float_temp_calculation_a_driv3 /= 5.0	
						
					ENDIF
		   		ENDIF
			ENDIF
				 
			
		   	
		  /* 	// ********** Converting to scale with bar (start from top of bar)*************************
		   	float_distance_player_girl *=100.0
			float_distance_player_girl /= float_initial_distance_player_girl	   
			// ****************************************************************		  			  */

		   	// ********** Converting to scale with bar (start from bottom of bar)*************************
  /*		float_temp_calculation_a_driv3 = float_initial_distance_player_girl - float_distance_player_girl

			// scaling to percentage
		   	float_temp_calculation_b_driv3 = 100.0 / float_temp_calculation_a_driv3		  */

			// scaling to percentage
		   /*	float_temp_calculation_a_driv3 *=100.0
			float_temp_calculation_a_driv3 /= float_initial_distance_player_girl					 
		      
			// ****************************************************************		  */			  

			IF float_temp_calculation_a_driv3 > 0.0 
		   		int_distance_player_girl =# float_temp_calculation_a_driv3
			ENDIF			 




			// FAILED CONDITION
			//IF TIMERA >= 120000
		  //	IF flag_van_drives_off < 1
		   

			IF NOT flag_van_drives_off = 1
	  			IF flag_is_tbone_in_group_farlie3 = 0

	
					IF flag_converstion_going_on_farlie3 = 0
						IF flag_battery_power_comments = 0
							IF timer_countdown_farlie3 <= 200000  
						  //		PRINT_NOW FAR3_FB 5000 1 // His battery running low!
								CLEAR_MISSION_AUDIO 1

								LOAD_MISSION_AUDIO 1 SOUND_FAR3_FB
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								 	WAIT 0
								ENDWHILE  

								PRINT_NOW FAR3_FB 10000 1 
								PLAY_MISSION_AUDIO 1
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									WAIT 0
								ENDWHILE
								CLEAR_THIS_PRINT FAR3_FB




								flag_battery_power_comments = 1
							ENDIF
						ENDIF
					ENDIF

					IF flag_converstion_going_on_farlie3 = 0
						IF flag_battery_power_comments = 1
							IF timer_countdown_farlie3 <= 100000  
							   CLEAR_MISSION_AUDIO 1

							   LOAD_MISSION_AUDIO 1 SOUND_FAR3_FC
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								 	WAIT 0
								ENDWHILE  

								PRINT_NOW FAR3_FC 10000 1 
								PLAY_MISSION_AUDIO 1
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									WAIT 0
								ENDWHILE
								CLEAR_THIS_PRINT FAR3_FC				
								flag_battery_power_comments = 2
							ENDIF
						ENDIF
					ENDIF




					// if player runs out of time
					IF timer_countdown_farlie3 <= 0
						flag_get_to_location_of_girl = 0
						flag_mission_failed_farlie3 = 1
						// play a sound of an explosion over the phone
						//PRINT_NOW ( FAR3_FD ) 5000 1	 // shit, the phones gone dead!

						 CLEAR_MISSION_AUDIO 1

						 LOAD_MISSION_AUDIO 1 SOUND_FAR3_FD
						 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						  	WAIT 0
						 ENDWHILE  

						 PRINT_NOW FAR3_FD 10000 1 
						 PLAY_MISSION_AUDIO 1
						 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						 	WAIT 0
						 ENDWHILE
					  //	 CLEAR_THIS_PRINT FAR3_FD	




						RETURN
					ENDIF




				ENDIF



				IF flag_van_drives_off = 0
					IF timer_countdown_farlie3 <= 0
						flag_get_to_location_of_girl = 0
						flag_mission_failed_farlie3 = 1
						// play a sound of an explosion over the phone
						PRINT_NOW ( dri2_5 ) 5000 1
						RETURN
					ENDIF
				ENDIF
			ENDIF
			


			IF flag_van_drives_off = 1
					
			 //	IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 500.0 500.0 500.0 FALSE
				IF NOT IS_CAR_DEAD car_van_with_buddy_inside
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer car_van_with_buddy_inside 400.0 400.0 400.0 FALSE
						flag_shoot_correct_container = 0
						flag_get_to_location_of_girl = 0

						flag_mission_failed_farlie3 = 1
						PRINT_NOW ( dri2_26 ) 5000 1	//  you fled
						RETURN
					ENDIF	
					
				ENDIF
			ENDIF




		ELSE		 // if van is dead
			flag_get_to_location_of_girl = 0
			flag_mission_failed_farlie3 = 1
			PRINT_NOW ( dri2_5 ) 5000 1
			RETURN

		ENDIF



	ENDWHILE
	

	// NEXT SUBFUNCTION
		flag_get_to_location_of_girl = 0
		flag_shoot_correct_container = 1
	  //	REMOVE_BLIP blip_airport_farlie3

RETURN

// *****************************
// ***** shoot_correct_container
// *****************************





shoot_correct_container: 


	LVAR_INT temp_seq_farlie3

	 

	OPEN_SEQUENCE_TASK temp_seq_farlie3
		TASK_LEAVE_CAR -1 car_van_with_buddy_inside
		TASK_KILL_CHAR_ON_FOOT -1 scplayer
	  //	CLEAR_SEQUENCE_TASK temp_seq_farlie3

	CLOSE_SEQUENCE_TASK temp_seq_farlie3

	IF IS_CHAR_IN_ANY_CAR scplayer
		STORE_CAR_CHAR_IS_IN scplayer car_players_car_farlie3 	
	ENDIF





	index_farlie3 = 0
	WHILE index_farlie3 < 3
		IF NOT IS_CHAR_DEAD char_baddies_farlie3[index_farlie3] 

			ADD_BLIP_FOR_CHAR char_baddies_farlie3[index_farlie3] blip_baddies_passengers[index_farlie3] 
			
			IF NOT IS_CAR_DEAD car_van_with_buddy_inside  
				PERFORM_SEQUENCE_TASK char_baddies_farlie3[index_farlie3] temp_seq_farlie3

				SET_CHAR_DECISION_MAKER char_baddies_farlie3[index_farlie3] dm_goons_farlie3
				SET_CHAR_RELATIONSHIP char_baddies_farlie3[index_farlie3] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				SET_CHAR_RELATIONSHIP char_baddies_farlie3[index_farlie3] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1	


			ELSE
				flag_shoot_correct_container = 0
				flag_mission_failed_farlie3 = 1
				PRINT_NOW ( dri2_5 ) 5000 1
				RETURN

			ENDIF
			
			

		ELSE
			flag_shoot_correct_container = 0
			flag_mission_failed_farlie3 = 1
			PRINT_NOW ( dri2_5 ) 5000 1
			RETURN

		ENDIF
		index_farlie3++
	ENDWHILE

	IF DOES_BLIP_EXIST blip_van_farlie3
		CHANGE_BLIP_DISPLAY blip_van_farlie3 BLIP_ONLY

		   //	ADD_BLIP_FOR_CAR car_van_with_buddy_inside blip_van_farlie3 
 	ENDIF


	

	IF NOT IS_CHAR_DEAD van_driver_farlie3
		SET_CHAR_PROOFS van_driver_farlie3 FALSE FALSE FALSE FALSE FALSE
		SET_CHAR_SUFFERS_CRITICAL_HITS van_driver_farlie3 TRUE
		ADD_BLIP_FOR_CHAR van_driver_farlie3  blip_van_driver
		PERFORM_SEQUENCE_TASK van_driver_farlie3 temp_seq_farlie3

	ENDIF

	CLEAR_SEQUENCE_TASK temp_seq_farlie3

	IF NOT IS_CAR_DEAD car_van_with_buddy_inside 
	 //	CAR_SET_IDLE  car_van_with_buddy_inside 
		FREEZE_CAR_POSITION car_van_with_buddy_inside TRUE
		LOCK_CAR_DOORS car_van_with_buddy_inside CARLOCK_LOCKED

		IF NOT IS_CHAR_DEAD char_escorts_farlie3[0]
			IF NOT	IS_CAR_DEAD car_van_with_buddy_inside
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D char_escorts_farlie3[0] car_van_with_buddy_inside 40.0 40.0 40.0 FALSE
					SET_CHAR_HEALTH char_escorts_farlie3[0] 0 
					MARK_CHAR_AS_NO_LONGER_NEEDED char_escorts_farlie3[0]
					IF NOT IS_CAR_DEAD car_escorts_farlie3[0]
						MARK_CAR_AS_NO_LONGER_NEEDED car_escorts_farlie3[0]
					ENDIF
						
				ELSE
					IF NOT IS_CAR_DEAD car_escorts_farlie3[0]
					
						OPEN_SEQUENCE_TASK temp_seq_farlie3
							TASK_LEAVE_CAR -1 car_escorts_farlie3[0]
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq_farlie3
						PERFORM_SEQUENCE_TASK char_escorts_farlie3[0] temp_seq_farlie3
						CLEAR_SEQUENCE_TASK temp_seq_farlie3

						ADD_BLIP_FOR_CHAR char_escorts_farlie3[0] blip_escorts_farlie3[0] 

					ENDIF
				ENDIF
			ENDIF

		ENDIF



		IF NOT IS_CHAR_DEAD char_escorts_farlie3[1]
			IF NOT IS_CAR_DEAD car_van_with_buddy_inside

				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D char_escorts_farlie3[1] car_van_with_buddy_inside 40.0 40.0 40.0 FALSE
					SET_CHAR_HEALTH char_escorts_farlie3[1] 0 
					MARK_CHAR_AS_NO_LONGER_NEEDED char_escorts_farlie3[1]

					IF NOT IS_CAR_DEAD car_escorts_farlie3[1]
						MARK_CAR_AS_NO_LONGER_NEEDED car_escorts_farlie3[1]
					ENDIF
						


						
				ELSE
					IF NOT IS_CAR_DEAD car_escorts_farlie3[1]
					
						OPEN_SEQUENCE_TASK temp_seq_farlie3
							TASK_LEAVE_CAR -1 car_escorts_farlie3[1]
							TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK temp_seq_farlie3
						PERFORM_SEQUENCE_TASK char_escorts_farlie3[1] temp_seq_farlie3
						CLEAR_SEQUENCE_TASK temp_seq_farlie3

						ADD_BLIP_FOR_CHAR char_escorts_farlie3[1] blip_escorts_farlie3[1] 

					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// create a car. player must break open the boot 
	//CLEAR_ONSCREEN_COUNTER int_distance_player_girl

	// TASK_CAR_DRIVE_TO_COORD CharID VehicleID DestX DestY DestZ CruiseSpeed DriveToCoordMode CreateCarModelIndexIfRequired DrivingStyle

	PRINT_NOW ( dri2_12 ) 5000 1	// Break open container	  

 	//WHILE NOT TIMERA > 120000  
 	WHILE timer_countdown_farlie3 > 0
 		
		WAIT 0
  		
		//timer_countdown_farlie3 -= 1
		// IF PLAYER IS ARMED 
		//			SET_CHAR_ANSWERING_MOBILE scplayer TRUE
		// ENDIF

		GOSUB tbone_checks_farlie3
		IF flag_mission_failed_farlie3 = 1
			RETURN
		ENDIF

		IF flag_is_tbone_in_group_farlie3 = 0
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 100.0 100.0 100.0 FALSE
				IF flag_display_flee_warning_farlie3 = 0

					PRINT_NOW dri2_47 5000 1 // get back!
					flag_display_flee_warning_farlie3 = 1
				ENDIF

				
				IF flag_display_flee_warning_farlie3 = 1
					IF flag_van_drives_off = 1
							
					 //	IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 500.0 500.0 500.0 FALSE
						IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer car_van_with_buddy_inside 400.0 400.0 400.0 FALSE
							flag_shoot_correct_container = 0
							flag_get_to_location_of_girl = 0

							flag_mission_failed_farlie3 = 1
							PRINT_NOW ( dri2_26 ) 5000 1	//  you fled
							RETURN
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		// remove_blips
		index_farlie3 = 0
		WHILE index_farlie3 < 3
			IF IS_CHAR_DEAD char_baddies_farlie3[index_farlie3]
				IF DOES_BLIP_EXIST blip_baddies_passengers[index_farlie3] 
					REMOVE_BLIP blip_baddies_passengers[index_farlie3]
				ENDIF
			ENDIF
			index_farlie3++
		ENDWHILE

		index_farlie3 = 0
		WHILE index_farlie3 < 2
			IF IS_CHAR_DEAD char_escorts_farlie3[index_farlie3]
				IF DOES_BLIP_EXIST blip_escorts_farlie3[index_farlie3] 
					REMOVE_BLIP blip_escorts_farlie3[index_farlie3]
				ENDIF
			ENDIF
			index_farlie3++
		ENDWHILE

		IF IS_CHAR_DEAD van_driver_farlie3 
			IF DOES_BLIP_EXIST blip_van_driver
				REMOVE_BLIP blip_van_driver
			ENDIF
		ENDIF

	 //	IF flag_is_tbone_in_group_farlie3 = 0
			IF NOT IS_CAR_DEAD car_van_with_buddy_inside
				IF IS_CHAR_DEAD char_baddies_farlie3[0]
				AND IS_CHAR_DEAD char_baddies_farlie3[1]
				AND IS_CHAR_DEAD char_baddies_farlie3[2]
				AND IS_CHAR_DEAD van_driver_farlie3
					REMOVE_BLIP blip_baddies_passengers[0]
					REMOVE_BLIP blip_baddies_passengers[1]
					REMOVE_BLIP blip_baddies_passengers[2]
				    REMOVE_BLIP blip_van_driver
				    
				    	
			   		IF IS_CHAR_DEAD char_escorts_farlie3[0]
						REMOVE_BLIP blip_escorts_farlie3[0] 

					 
				 		IF IS_CHAR_DEAD char_escorts_farlie3[1] 
							REMOVE_BLIP blip_escorts_farlie3[1]
							flag_shoot_correct_container = 0
							flag_torch_truck_farlie3 = 1
							RETURN
					 	ENDIF
				 	ENDIF
				   	
				ENDIF
			ELSE
				flag_shoot_correct_container = 0
				flag_mission_failed_farlie3 = 1
				PRINT_NOW ( dri2_5 ) 5000 1
				RETURN
			ENDIF
	 //	ENDIF

	ENDWHILE

	flag_shoot_correct_container = 0
	flag_mission_failed_farlie3 = 1
	EXPLODE_CAR car_van_with_buddy_inside
	// play a sound of an explosion over the phone
	PRINT_NOW ( dri2_5 ) 5000 1	
		
RETURN

torch_truck_farlie3:	

	IF flag_initialise_farlie3 = 0

	////////////////////////////////////////////////////////
		// CUTSCENE STUFF
	////////////////////////////////////////////////////////


		// Creating cop cars for cutscene


		LVAR_INT car_cop1_farlie3 car_cop2_farlie3
		LVAR_INT char_cop1_farlie3 char_cop2_farlie3 
		LVAR_INT flag_cutscene_farlie3

		flag_cutscene_farlie3 = 0


		WAIT 2000

		SET_PLAYER_CONTROL player1 OFF
		SWITCH_WIDESCREEN ON

		WAIT 2000


		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		REQUEST_MODEL stretch

		WHILE NOT HAS_MODEL_LOADED stretch
			WAIT 0
		ENDWHILE

		IF NOT IS_CAR_DEAD car_van_with_buddy_inside

			SET_CAR_COORDINATES car_van_with_buddy_inside -1321.8 -189.79 13.46
			SET_CAR_HEADING car_van_with_buddy_inside 60.0

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 6.0 10.5 -0.2 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z

			CREATE_CAR stretch coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z get_away_car
		ENDIF

		MARK_MODEL_AS_NO_LONGER_NEEDED stretch

		IF NOT IS_CAR_DEAD car_van_with_buddy_inside
		   	IF DOES_BLIP_EXIST blip_van_farlie3
				CHANGE_BLIP_DISPLAY blip_van_farlie3 BOTH

		   //	ADD_BLIP_FOR_CAR car_van_with_buddy_inside blip_van_farlie3 
		 	ENDIF

			// setting players coordinates
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 6.0 -6.5 -0.5 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
			
			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z 
			ELSE 
				SET_CHAR_COORDINATES scplayer coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
			ENDIF

			// make player run to back
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside -1.0 -3.5 -0.2 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z

			IF NOT IS_CAR_DEAD car_escorts_farlie3[0] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside -8.0 -8.0 -0.9 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
				SET_CAR_COORDINATES  car_escorts_farlie3[0] coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
			ENDIF

			IF NOT IS_CAR_DEAD car_escorts_farlie3[1] 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 8.0 -8.0 -0.9 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
				SET_CAR_COORDINATES  car_escorts_farlie3[1] coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
			ENDIF


			IF NOT IS_CAR_DEAD car_players_car_farlie3 
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 15.0 -20.0 -0.9 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
				SET_CAR_COORDINATES  car_players_car_farlie3 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
			ENDIF

			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside -2.0 -6.5 -0.5 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
		   	SET_FIXED_CAMERA_POSITION coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z 0.0 0.0 0.0 // by drop off zone
			POINT_CAMERA_AT_CAR car_van_with_buddy_inside FIXED JUMP_CUT
			LOCK_CAR_DOORS car_van_with_buddy_inside CARLOCK_UNLOCKED
		  
			LOAD_SPECIAL_CHARACTER 1 torino

			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				WAIT 0
			ENDWHILE
		// 	CREATE_CHAR_AS_PASSENGER car_van_with_buddy_inside PEDTYPE_MISSION2 WMYST 2 char_toreno_farlie3
			
			IF NOT IS_CAR_DEAD car_van_with_buddy_inside
				//CREATE_CHAR_AS_PASSENGER car_van_with_buddy_inside PEDTYPE_MISSION2 SPECIAL01 2 char_toreno_farlie3
			
		   		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside -6.0 -20.0 0.0 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z	
		   		CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z char_toreno_farlie3
				SET_CHAR_DROWNS_IN_WATER char_toreno_farlie3 TRUE 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH char_toreno_farlie3 TRUE
				 SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR char_toreno_farlie3 FALSE

				SET_CHAR_STAY_IN_SAME_PLACE char_toreno_farlie3 TRUE
		   	
		   		GET_CHAR_HEADING char_toreno_farlie3 heading_toreno
							
				SET_CHAR_RELATIONSHIP char_toreno_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1
				SET_CHAR_DECISION_MAKER char_toreno_farlie3 dm_buddies_farlie3

				GIVE_WEAPON_TO_CHAR char_toreno_farlie3 WEAPONTYPE_MICRO_UZI 1000
				SET_CHAR_PROOFS char_toreno_farlie3 FALSE FALSE TRUE FALSE FALSE
				SET_CHAR_NEVER_TARGETTED char_toreno_farlie3 TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER char_toreno_farlie3 TRUE	
				TASK_LEAVE_CAR char_toreno_farlie3 car_van_with_buddy_inside
				
				SET_CAR_HEALTH car_van_with_buddy_inside 1000 

			//SET_CHAR_COORDINATES char_toreno_farlie3 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z 
		  //	CREATE_CHAR PEDTYPE_MISSION2 WMYST coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z char_toreno_farlie3
			ENDIF

		ELSE

			flag_torch_truck_farlie3 = 0
			flag_mission_failed_farlie3 = 1
			PRINT_NOW dri2_22 5000 1 // toreno is dead
			RETURN
		ENDIF

		MARK_MODEL_AS_NO_LONGER_NEEDED dnb1 
		MARK_MODEL_AS_NO_LONGER_NEEDED dnb2
		MARK_MODEL_AS_NO_LONGER_NEEDED dnb3 
		MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi

		IF NOT IS_CHAR_DEAD char_tbone_farlie3
			IF NOT IS_CHAR_DEAD char_toreno_farlie3
				IF NOT IS_CHAR_DEAD scplayer
					SET_CHAR_VISIBLE scplayer FALSE
					SET_CHAR_VISIBLE char_tbone_farlie3	FALSE
					SET_CHAR_VISIBLE char_toreno_farlie3 FALSE
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD car_van_with_buddy_inside
		   	IF NOT IS_CHAR_DEAD char_tbone_farlie3 

		   		IF IS_GROUP_MEMBER char_tbone_farlie3 Players_Group
		   			REMOVE_CHAR_FROM_GROUP char_tbone_farlie3
		   		ENDIF

		   	  	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 6.0 -20.0 0.0 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z	
		   	  	

				IF IS_CHAR_IN_ANY_CAR char_tbone_farlie3
					WARP_CHAR_FROM_CAR_TO_COORD char_tbone_farlie3 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z 
				ELSE 
					SET_CHAR_COORDINATES char_tbone_farlie3 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
				ENDIF

			   //	SET_CHAR_COORDINATES char_tbone_farlie3 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z 
		   		SET_CHAR_STAY_IN_SAME_PLACE char_tbone_farlie3 TRUE

				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 3.0 -20.0 0.0 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z	
				SET_CHAR_COORDINATES scplayer coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
				TASK_TURN_CHAR_TO_FACE_COORD scplayer coord_random_location_x coord_random_location_y coord_random_location_z
			ENDIF

		ELSE

			flag_torch_truck_farlie3 = 0
			flag_mission_failed_farlie3 = 1
			PRINT_NOW dri2_22 5000 1 // toreno is dead
			RETURN
		ENDIF 

		DELETE_CHAR char_baddies_farlie3[0]
		DELETE_CHAR char_baddies_farlie3[1]
		DELETE_CHAR char_baddies_farlie3[2]
		DELETE_CHAR van_driver_farlie3
		DELETE_CHAR char_escorts_farlie3[0]
		DELETE_CHAR char_escorts_farlie3[1]

		DELETE_CHAR char_escorts_farlie3[0]
		DELETE_CHAR char_escorts_farlie3[1]
		
		DELETE_CAR car_escorts_farlie3[0]
		DELETE_CAR car_escorts_farlie3[1]

 		CLEAR_AREA coord_random_location_x coord_random_location_y coord_random_location_z 1500.0 TRUE
		LOAD_CUTSCENE FARL_3B
		 
		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
		
		DO_FADE 1000 FADE_IN
 		CLEAR_AREA coord_random_location_x coord_random_location_y coord_random_location_z 1500.0 TRUE
		START_CUTSCENE
  
		WHILE NOT HAS_CUTSCENE_FINISHED
			WAIT 0
			CLEAR_AREA coord_random_location_x coord_random_location_y coord_random_location_z 1500.0 TRUE
		ENDWHILE
		 
		CLEAR_CUTSCENE

		IF NOT IS_CHAR_DEAD char_tbone_farlie3
			SET_CHAR_VISIBLE char_tbone_farlie3	TRUE
		ENDIF
		
		IF NOT IS_CHAR_DEAD char_toreno_farlie3
			SET_CHAR_VISIBLE char_toreno_farlie3 TRUE		
		ENDIF

		IF IS_PLAYER_PLAYING player1
			SET_CHAR_VISIBLE scplayer TRUE
		ENDIF
		
		IF NOT IS_CAR_DEAD car_van_with_buddy_inside 
			FREEZE_CAR_POSITION car_van_with_buddy_inside FALSE
			LOCK_CAR_DOORS car_van_with_buddy_inside CARLOCK_LOCKED
			
			IF NOT IS_CHAR_DEAD char_toreno_farlie3
				TASK_DESTROY_CAR char_toreno_farlie3 car_van_with_buddy_inside
			ENDIF

			IF NOT IS_CHAR_DEAD char_tbone_farlie3
				IF IS_GROUP_MEMBER char_tbone_farlie3 Players_Group 
					REMOVE_CHAR_FROM_GROUP char_tbone_farlie3	
				ENDIF
				
				TASK_DESTROY_CAR char_tbone_farlie3 car_van_with_buddy_inside
			ENDIF
		ENDIF

		DO_FADE 0 FADE_OUT
		IF NOT IS_CAR_DEAD car_van_with_buddy_inside
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_van_with_buddy_inside 6.0 -6.5 -0.5 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
		  //	SET_CHAR_COORDINATES scplayer coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z

			GET_CAR_COORDINATES car_van_with_buddy_inside coord_random_location_x coord_random_location_y coord_random_location_z
			GET_HEADING_FROM_VECTOR_2D coord_random_location_x coord_random_location_y cops_heading_farlie3
		  //	SET_CHAR_HEADING scplayer cops_heading_farlie3 
	  		

			TASK_TURN_CHAR_TO_FACE_COORD scplayer coord_random_location_x coord_random_location_y coord_random_location_z

		ENDIF

		CLEAR_AREA coord_random_location_x coord_random_location_y coord_random_location_z 1000.0 TRUE
		WAIT 2000

		   //	IF NOT IS_CHAR_DEAD char_toreno_farlie3
		   //		GET_CHAR_HEADING char_toreno_farlie3 heading_toreno
		  //	ENDIF

			//SET_CHAR_HEADING scplayer heading_toreno
		  		
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		DO_FADE 1000 FADE_IN
		
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE 

		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		SET_PLAYER_CONTROL player1 ON

		SET_POLICE_IGNORE_PLAYER player1 ON

		PRINT_NOW dri2_33 10000 1 // destroy the coke

		flag_initialise_farlie3 = 1
	ENDIF



   // Toreno Checks
	IF NOT IS_CHAR_DEAD char_toreno_farlie3


	ELSE
		flag_torch_truck_farlie3 = 0
		flag_mission_failed_farlie3 = 1
		PRINT_NOW dri2_22 5000 1 // toreno is dead
		RETURN
	ENDIF

	IF IS_CHAR_DEAD	char_tbone_farlie3

	        flag_get_to_location_of_girl = 0
			flag_shoot_correct_container = 0
		    flag_torch_truck_farlie3 = 0
			flag_get_back_to_base = 0
			flag_mission_passed_farlie3 = 0
			flag_mission_failed_farlie3 = 1
			PRINT_NOW dri2_31 5000 1 // tbone is dead
			RETURN
	ENDIF



	
	IF flag_has_truck_been_destroyed_farlie3 =	0


		IF IS_CAR_DEAD car_van_with_buddy_inside 



			flag_has_truck_been_destroyed_farlie3 =	1
		  	REMOVE_BLIP blip_van_farlie3
		 	ALTER_WANTED_LEVEL_NO_DROP Player1 2





		   //	flag_phone_dialog_driv3 = 0
			TIMERB=0
			SET_GROUP_SEPARATION_RANGE Players_Group 30.0

			IF NOT IS_CHAR_DEAD char_toreno_farlie3
				SET_CHAR_STAY_IN_SAME_PLACE char_toreno_farlie3 FALSE
				IF NOT IS_GROUP_MEMBER char_toreno_farlie3 Players_Group
					SET_GROUP_MEMBER Players_Group char_toreno_farlie3 
				ENDIF
				SET_CHAR_PROOFS char_toreno_farlie3 FALSE FALSE FALSE FALSE FALSE
			   //	SET_GROUP_MEMBER players_group_farlie3 char_toreno_farlie3
			ELSE

				flag_torch_truck_farlie3 = 0
				flag_mission_failed_farlie3 = 1
				PRINT_NOW dri2_22 5000 1 // toreno is dead
				RETURN	
			ENDIF


		   IF NOT IS_CHAR_DEAD char_tbone_farlie3	
				SET_CHAR_STAY_IN_SAME_PLACE char_tbone_farlie3 FALSE
				IF NOT IS_GROUP_MEMBER char_tbone_farlie3 Players_Group

					SET_GROUP_MEMBER Players_Group char_tbone_farlie3
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD char_tbone_farlie3	
				IF NOT IS_CHAR_DEAD char_toreno_farlie3
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer char_toreno_farlie3 30.0 30.0 30.0 FALSE
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer char_tbone_farlie3 30.0 30.0 30.0 FALSE
						  	PRINT_NOW dri2_4 5000 1 // get the hell outta here before the cops come!
							IF IS_CAR_IN_WATER car_van_with_buddy_inside
								PRINT dri2_34 10000 1	// get out of the airport
							ELSE
					   			PRINT dri2_24 10000 1	  // get away from teh burning car
							ENDIF

							IF flag_reached_airport_exit = 0
							
								ADD_BLIP_FOR_COORD -1734.05 -579.94 15.56 blip_airport_exit							
							//	CHANGE_BLIP_COLOUR blip_airport_exit YELLOW
							ELSE
								ADD_BLIP_FOR_COORD coord_base_x coord_base_y coord_base_z blip_base	

								IF flag_player_starts_conversation_with_toreno = 0
									flag_player_starts_conversation_with_toreno = 1
								ENDIF
						
						  //		CHANGE_BLIP_COLOUR blip_base YELLOW
							ENDIF
						ENDIF
					ENDIF	
				ENDIF
			ENDIF
		ENDIF

   	  /*	IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 100.0 100.0 100.0 FALSE
			flag_torch_truck_farlie3 = 0
			flag_mission_failed_farlie3 = 1
			PRINT_NOW ( dri2_26 ) 5000 1	//  you fled
			RETURN
		ENDIF  		 */

	ENDIF

	IF flag_cops_go_after_player = 0
		
		IF NOT IS_CHAR_DEAD char_toreno_farlie3 
			IF NOT IS_CHAR_DEAD char_tbone_farlie3
				IF IS_CHAR_IN_ANY_CAR scplayer
					IF IS_CHAR_IN_ANY_CAR char_toreno_farlie3 
						IF IS_CHAR_IN_ANY_CAR char_tbone_farlie3
							SET_POLICE_IGNORE_PLAYER Player1 OFF
							flag_cops_go_after_player = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF



   // Toreno Checks
	IF flag_has_truck_been_destroyed_farlie3 =	1

		GOSUB check_toreno_farlie3

		IF flag_mission_failed_farlie3 = 1
			RETURN
		ENDIF

		GOSUB tbone_checks_farlie3
		IF flag_mission_failed_farlie3 = 1
			RETURN
		ENDIF
	ENDIF

   /*	IF flag_phone_dialog_driv3 = 0
		IF TIMERB > 5000
			PRINT_NOW dri2_24 10000 1
			flag_phone_dialog_driv3 = 1
		ENDIF
	ENDIF	  */





	IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_random_location_x coord_random_location_y coord_random_location_z 150.0 150.0 150.0 FALSE
		IF flag_player_away_from_truck_farlie3	= 0
			flag_player_away_from_truck_farlie3	= 1	   
		ENDIF
	ELSE
		IF flag_player_away_from_truck_farlie3	= 1
			flag_player_away_from_truck_farlie3	= 0	   
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD car_van_with_buddy_inside
		GET_CAR_COORDINATES car_van_with_buddy_inside coord_random_location_x coord_random_location_y coord_random_location_z
	ENDIF 



	
   /*	GOSUB tbone_checks_farlie3
	IF flag_mission_failed_farlie3 = 1
		RETURN
	ENDIF
			*/






	// PASS CONDITIONS
		IF flag_has_truck_been_destroyed_farlie3 =	1 

			// cutscene of player and toreno parting? and cops drive past?

		  	REMOVE_BLIP blip_van_farlie3

			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer char_tbone_farlie3 30.0 30.0 30.0 FALSE
				IF flag_reached_airport_exit = 0
					IF NOT DOES_BLIP_EXIST blip_airport_exit		   
						ADD_BLIP_FOR_COORD -1734.05 -579.94 15.56 blip_airport_exit
						PRINT_NOW dri2_34 5000 1 //let just get out of here

					ENDIF							
				//	CHANGE_BLIP_COLOUR blip_airport_exit YELLOW
				ELSE
					IF NOT DOES_BLIP_EXIST blip_base
						ADD_BLIP_FOR_COORD coord_base_x coord_base_y coord_base_z blip_base
						PRINT_NOW dri2_25 5000 1 // get back to the pleasure domes

								IF flag_player_starts_conversation_with_toreno = 0
									flag_player_starts_conversation_with_toreno = 1
								ENDIF

		
					ENDIF					
			  //		CHANGE_BLIP_COLOUR blip_base YELLOW
				ENDIF

				IF NOT IS_CHAR_DEAD char_toreno_farlie3
					IF NOT IS_GROUP_MEMBER char_toreno_farlie3 Players_Group
						SET_GROUP_MEMBER Players_Group char_toreno_farlie3
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD char_tbone_farlie3
					IF NOT IS_GROUP_MEMBER char_tbone_farlie3 Players_Group
						SET_GROUP_MEMBER Players_Group char_tbone_farlie3
					ENDIF
				ENDIF

			ENDIF

			







	

			 
			MARK_CAR_AS_NO_LONGER_NEEDED get_away_car
	  /*		MARK_CAR_AS_NO_LONGER_NEEDED car_cop2_farlie3
			MARK_CAR_AS_NO_LONGER_NEEDED  car_cop1_farlie3
			MARK_CHAR_AS_NO_LONGER_NEEDED char_cop1_farlie3
			MARK_CHAR_AS_NO_LONGER_NEEDED char_cop2_farlie3	  */

		/*	IF NOT IS_CHAR_DEAD char_cop1_farlie3
				SET_CHAR_DECISION_MAKER char_cop1_farlie3 dm_goons_farlie3
			ENDIF

			IF NOT IS_CHAR_DEAD char_cop2_farlie3

				SET_CHAR_DECISION_MAKER char_cop2_farlie3 dm_goons_farlie3
			ENDIF	  */



			MARK_CAR_AS_NO_LONGER_NEEDED car_players_car_farlie3
			MARK_MODEL_AS_NO_LONGER_NEEDED BURRITO


			MARK_MODEL_AS_NO_LONGER_NEEDED stretch

	   //		PRINT_NOW dri2_34 10000 1	  // lets get out of the airport



			flag_get_back_to_base = 1			


			


			flag_torch_truck_farlie3 = 0
		   //	flag_mission_passed_farlie3 = 1
			RETURN
		ENDIF
	
RETURN



get_back_to_base:




	GOSUB check_toreno_farlie3

	IF flag_mission_failed_farlie3 = 1
   		RETURN
   	ENDIF

	GOSUB tbone_checks_farlie3
	IF flag_mission_failed_farlie3 = 1
		RETURN
	ENDIF

	IF flag_reached_airport_exit = 1
		GOSUB check_players_wanted_level
	ENDIF


	IF flag_cops_go_after_player = 0
		
		IF NOT IS_CHAR_DEAD char_toreno_farlie3 
			IF NOT IS_CHAR_DEAD char_tbone_farlie3
				IF IS_CHAR_IN_ANY_CAR scplayer
					IF IS_CHAR_IN_ANY_CAR char_toreno_farlie3 
						IF IS_CHAR_IN_ANY_CAR char_tbone_farlie3
							SET_POLICE_IGNORE_PLAYER Player1 OFF
							flag_cops_go_after_player = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	// MAke toreno say car is not big enough
	IF NOT IS_CHAR_DEAD char_toreno_farlie3 
		IF flag_is_toreno_in_group_farlie3 = 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				IF flag_is_player_in_car = 0
				
					STORE_CAR_CHAR_IS_IN scplayer car_players_car_farlie3	
					GET_MAXIMUM_NUMBER_OF_PASSENGERS car_players_car_farlie3 passenger_seats_farlie3
					IF passenger_seats_farlie3 < 2

						CLEAR_MISSION_AUDIO 1

						IF flag_toreno_comment = 0
							LOAD_MISSION_AUDIO 1 SOUND_FAR3_LC
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							ENDWHILE  

							PRINT_NOW FAR3_LC 10000 1 ///		[FAR3_LC]	We’re not going to fit in that, think on your feet, kid!
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT FAR3_LC
						ENDIF

						IF flag_toreno_comment = 1
							LOAD_MISSION_AUDIO 1 SOUND_FAR3_LA
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							ENDWHILE  

							PRINT_NOW FAR3_LA 10000 1 //	TORENO	[FAR3_LA]	We need a bigger car, come on, move it!
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT FAR3_LA
						ENDIF

						IF flag_toreno_comment = 2
							LOAD_MISSION_AUDIO 1 SOUND_FAR3_LB
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							ENDWHILE  

							PRINT_NOW FAR3_LB 10000 1 //			[FAR3_LB]	Too small, come on, think!
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT FAR3_LB
						ENDIF

						flag_toreno_comment++
						IF flag_toreno_comment >=3
							flag_toreno_comment = 0
						ENDIF

						
					ENDIF
					flag_is_player_in_car = 1	
					   
				ENDIF

			ELSE
				flag_is_player_in_car = 0

			ENDIF
		ENDIF
	ENDIF

   // to fix Dave Murdocks insane  bug about droping group members ONE BY ONE
	IF flag_reached_airport_exit = 0

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1734.05 -579.94 15.56 20.0 20.0 20.0 FALSE
		OR NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_van_end_point_x coord_van_end_point_y coord_van_end_point_z 600.0 600.0 600.0 FALSE

			flag_reached_airport_exit = 1

				IF NOT IS_WANTED_LEVEL_GREATER Player1 3						
					ALTER_WANTED_LEVEL_NO_DROP Player1 3
				ENDIF

				SET_POLICE_IGNORE_PLAYER Player1 OFF
				flag_cops_go_after_player = 1

					
		ENDIF
	ENDIF




	IF flag_is_toreno_in_group_farlie3 = 0
		IF flag_is_tbone_in_group_farlie3 = 0


			IF flag_reached_airport_exit = 0
			 //	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1546.76 -430.94 5.56 20.0 20.0 20.0 FALSE
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1734.05 -579.94 15.56 20.0 20.0 20.0 FALSE
		 
					IF DOES_BLIP_EXIST blip_airport_exit
						REMOVE_BLIP blip_airport_exit	
						
						IF NOT IS_WANTED_LEVEL_GREATER Player1 3						
							ALTER_WANTED_LEVEL_NO_DROP Player1 3
						ENDIF


						ADD_SPRITE_BLIP_FOR_COORD coord_spray_shop_x_farlie3 coord_spray_shop_y_farlie3 coord_spray_shop_z_farlie3 RADAR_SPRITE_SPRAY blip_paint_and_spray_farlie3  			
					 	PRINT dri2_46 5000 1   // GET TO THE PAINT AND SPRAY
						flag_has_player_got_wanted_level = 1

							IF NOT IS_CHAR_DEAD char_toreno_farlie3
								CLEAR_CHAR_RELATIONSHIP char_toreno_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP
								SET_CHAR_RELATIONSHIP char_toreno_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
							ENDIF  

							IF NOT IS_CHAR_DEAD char_tbone_farlie3
								CLEAR_CHAR_RELATIONSHIP char_tbone_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP
								SET_CHAR_RELATIONSHIP char_tbone_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
							ENDIF  

						




						flag_reached_airport_exit = 1
					ENDIF
				ENDIF

				IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1734.05 -579.94 15.56 4.0 4.0 4.0 TRUE
				ENDIF

			ENDIF

			IF flag_player_starts_conversation_with_toreno = 1
			  // banter on the way back
				IF flag_reached_airport_exit = 1
				  	IF flag_phone_dialog_driv3 >= 33
				  	AND flag_phone_dialog_driv3 < 47 
						IF IS_CHAR_IN_ANY_CAR scplayer
							IF NOT IS_CHAR_DEAD char_toreno_farlie3	
								IF NOT IS_CHAR_DEAD char_tbone_farlie3
									IF IS_CHAR_IN_ANY_CAR char_tbone_farlie3
										IF IS_CHAR_IN_ANY_CAR char_toreno_farlie3

											IF flag_phone_dialog_driv3 = 33
												index_dialogue_farlie3 = 25	 //How long you been working for Jizzy, I haven’t seen you before.

												IF TIMERA > 10000
													GOSUB load_and_play_audio_farlie3
												ENDIF
											ELSE

												GOSUB load_and_play_audio_farlie3
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
				  	ENDIF
				ENDIF




				IF LOCATE_CHAR_ANY_MEANS_3D scplayer coord_base_x coord_base_y coord_base_z 4.0 4.0 4.0 TRUE 
					flag_get_back_to_base = 0	
					flag_mission_passed_farlie3 = 1


				 	flag_cutscene_farlie3 = 0

						SET_PLAYER_CONTROL player1 OFF
						SWITCH_WIDESCREEN ON

						TIMERA = 0
						TIMERB = 0

						WHILE TIMERA < 8000 
							WAIT 0


							IF flag_cutscene_farlie3 = 0
								GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 3.0 8.0 0.4 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
								SET_FIXED_CAMERA_POSITION coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z 0.0 0.0 0.0
								POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT
								CLEAR_AREA coord_base_x coord_base_y coord_base_z 100.0 TRUE 
							  //	PRINT_NOW FAR3_NA 2000 1 //you did good cj
								CLEAR_MISSION_AUDIO 1

								LOAD_MISSION_AUDIO 1 SOUND_FAR3_NA
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								 	WAIT 0
								ENDWHILE  

								PRINT_NOW FAR3_NA 10000 1 //you did good cj
								PLAY_MISSION_AUDIO 1
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									WAIT 0
								ENDWHILE
								CLEAR_THIS_PRINT FAR3_NA
 
								flag_cutscene_farlie3 = 1
								TIMERB = 0
							ENDIF

							IF flag_cutscene_farlie3 = 1

								IF TIMERB > 500
								   

									IF NOT IS_CHAR_DEAD char_toreno_farlie3
										GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS char_toreno_farlie3 3.0 -2.0 0.5 coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z
										SET_FIXED_CAMERA_POSITION coord_toreno_farlie3_x coord_toreno_farlie3_y coord_toreno_farlie3_z 0.0 0.0 0.0
										POINT_CAMERA_AT_CHAR char_toreno_farlie3 FIXED JUMP_CUT
										

								   		IF IS_GROUP_MEMBER char_toreno_farlie3 Players_Group
								   			REMOVE_CHAR_FROM_GROUP char_toreno_farlie3
								   		ENDIF

										IF IS_CHAR_IN_ANY_CAR char_toreno_farlie3
											TASK_LEAVE_ANY_CAR char_toreno_farlie3
										ENDIF

									//	PRINT_NOW FAR3_NB 4000 1	 // now scram
										CLEAR_MISSION_AUDIO 1

										LOAD_MISSION_AUDIO 1 SOUND_FAR3_NB
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
										 	WAIT 0
										ENDWHILE  

										PRINT_NOW FAR3_NB 10000 1  // now scram
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
										CLEAR_THIS_PRINT FAR3_NB

										TIMERB = 0
										flag_cutscene_farlie3 = 2
									ENDIF

								ENDIF
							ENDIF


							IF flag_cutscene_farlie3 = 2
								IF TIMERB > 500
									 flag_cutscene_farlie3 = 3
									IF NOT IS_CHAR_DEAD char_toreno_farlie3
										TASK_GO_STRAIGHT_TO_COORD char_toreno_farlie3 -2624.57 1410.46 6.5 PEDMOVE_WALK -1
									ENDIF

							   

									 IF NOT IS_CHAR_DEAD char_tbone_farlie3

   										IF IS_GROUP_MEMBER char_tbone_farlie3 Players_Group
								   			REMOVE_CHAR_FROM_GROUP char_tbone_farlie3
								   		ENDIF

									 	IF IS_CHAR_IN_ANY_CAR char_tbone_farlie3
					 				 	   //	STORE_CAR_CHAR_IS_IN char_tbone_farlie3 car_players_car_farlie3
									 		TASK_LEAVE_ANY_CAR char_tbone_farlie3 
									 	   //	MARK_CAR_AS_NO_LONGER_NEEDED car_players_car_farlie3	   
									 		TIMERB= 0	
									 	ENDIF
									 ENDIF
								ENDIF
							ENDIF


							IF flag_cutscene_farlie3 = 3

								IF TIMERB > 200
									flag_cutscene_farlie3 = 4


									IF NOT IS_CHAR_DEAD char_tbone_farlie3
										TASK_GO_STRAIGHT_TO_COORD char_tbone_farlie3 -2625.57 1409.46 6.5 PEDMOVE_WALK -1
									ENDIF


									TIMERB = 0
								ENDIF
							ENDIF




						ENDWHILE


						DELETE_CHAR char_toreno_farlie3
						DELETE_CHAR char_tbone_farlie3
						MARK_CAR_AS_NO_LONGER_NEEDED car_players_car_farlie3
						RESTORE_CAMERA_JUMPCUT
						SET_CAMERA_BEHIND_PLAYER
						SET_PLAYER_CONTROL player1 ON
						SWITCH_WIDESCREEN OFF	
				ENDIF
			ENDIF
		ENDIF
	ENDIF


RETURN 



check_players_wanted_level:

	IF flag_reached_airport_exit = 1
		IF flag_has_player_got_wanted_level = 0
			IF flag_is_toreno_in_group_farlie3 = 0
			AND flag_is_tbone_in_group_farlie3 = 0

				IF IS_WANTED_LEVEL_GREATER player1 0
					flag_has_player_got_wanted_level = 1
					IF NOT DOES_BLIP_EXIST blip_paint_and_spray_farlie3
						ADD_SPRITE_BLIP_FOR_COORD coord_spray_shop_x_farlie3 coord_spray_shop_y_farlie3 coord_spray_shop_z_farlie3 RADAR_SPRITE_SPRAY blip_paint_and_spray_farlie3  			
					 	PRINT dri2_46 5000 1   // GET TO THE PAINT AND SPRAY
					ENDIF

					IF DOES_BLIP_EXIST blip_airport_exit
						REMOVE_BLIP blip_airport_exit	
					ENDIF


					IF DOES_BLIP_EXIST blip_base
						REMOVE_BLIP blip_base
					ENDIF 

					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddies_farlie3 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE

					IF NOT IS_CHAR_DEAD char_toreno_farlie3
						SET_CHAR_RELATIONSHIP char_toreno_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
						CLEAR_CHAR_RELATIONSHIP char_toreno_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP	
					ENDIF  

					IF NOT IS_CHAR_DEAD char_tbone_farlie3
						SET_CHAR_RELATIONSHIP char_tbone_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
						CLEAR_CHAR_RELATIONSHIP char_tbone_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP
					ENDIF  
				ENDIF
			ENDIF	
		ENDIF



		IF flag_has_player_got_wanted_level = 1

			IF NOT IS_WANTED_LEVEL_GREATER player1 0

				IF NOT IS_CHAR_DEAD char_toreno_farlie3
					CLEAR_CHAR_RELATIONSHIP char_toreno_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP	
					IF IS_GROUP_MEMBER char_toreno_farlie3 Players_Group
						REMOVE_CHAR_FROM_GROUP char_toreno_farlie3
					ENDIF

					CLEAR_CHAR_TASKS char_toreno_farlie3
					
			   		IF NOT IS_GROUP_MEMBER char_toreno_farlie3 Players_Group
						SET_GROUP_MEMBER Players_Group char_toreno_farlie3 
						SET_GROUP_FOLLOW_STATUS Players_Group TRUE
					ENDIF 
					SET_CHAR_RELATIONSHIP char_toreno_farlie3 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP
				ENDIF  

				IF NOT IS_CHAR_DEAD char_tbone_farlie3
					CLEAR_CHAR_RELATIONSHIP char_tbone_farlie3 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP	
					IF IS_GROUP_MEMBER char_tbone_farlie3 Players_Group
						REMOVE_CHAR_FROM_GROUP char_tbone_farlie3
					ENDIF

					CLEAR_CHAR_TASKS char_tbone_farlie3
					
			   		IF NOT IS_GROUP_MEMBER char_tbone_farlie3 Players_Group
						SET_GROUP_MEMBER Players_Group char_tbone_farlie3 
						SET_GROUP_FOLLOW_STATUS Players_Group TRUE
					ENDIF 
				ENDIF  
				//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddies_farlie3 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
				
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddies_farlie3 EVENT_ACQUAINTANCE_PED_HATE

				flag_has_player_got_wanted_level = 0
				TIMERA = 0
				IF NOT DOES_BLIP_EXIST blip_base
					ADD_BLIP_FOR_COORD coord_base_x coord_base_y coord_base_z blip_base							
					PRINT_NOW dri2_25 5000 1 // get back to the pleasure domes
					IF flag_player_starts_conversation_with_toreno = 0
						flag_player_starts_conversation_with_toreno = 1
					ENDIF
				ENDIF


				IF DOES_BLIP_EXIST blip_paint_and_spray_farlie3
					REMOVE_BLIP blip_paint_and_spray_farlie3
				ENDIF 
			ENDIF


		ENDIF
	ENDIF

RETURN




check_toreno_farlie3:

		IF NOT IS_CHAR_DEAD char_toreno_farlie3 
		
			IF flag_is_toreno_in_group_farlie3 = 0
				
										
				// check if toreno is in teh group
	   			IF NOT IS_GROUP_MEMBER char_toreno_farlie3 Players_Group  
	   	    	    PRINT_NOW dri2_3 5000 1 //"WHERE ARE U GOING?"

					IF DOES_BLIP_EXIST blip_base
						REMOVE_BLIP blip_base
					ENDIF

					IF DOES_BLIP_EXIST blip_airport_exit
						REMOVE_BLIP blip_airport_exit
					ENDIF

					IF DOES_BLIP_EXIST blip_destination_farlie3
						REMOVE_BLIP	blip_destination_farlie3
					ENDIF


		    	    ADD_BLIP_FOR_CHAR char_toreno_farlie3 blip_torneo
				  //	CHANGE_BLIP_COLOUR blip_torneo BLUE 
					SET_BLIP_AS_FRIENDLY blip_torneo TRUE
					flag_is_toreno_in_group_farlie3 = 1
				ENDIF
			ENDIF


			IF flag_is_toreno_in_group_farlie3 = 1
			  //	IF IS_GROUP_MEMBER char_toreno_farlie3 Players_Group
				  //	WAIT 0

						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer char_toreno_farlie3 8.0 8.0 8.0 FALSE

							IF NOT IS_GROUP_MEMBER char_toreno_farlie3 Players_Group
						   		 SET_GROUP_MEMBER Players_Group char_toreno_farlie3
							ENDIF

						    flag_is_toreno_in_group_farlie3 = 0                
							REMOVE_BLIP blip_torneo
							IF flag_is_tbone_in_group_farlie3 = 0
								IF flag_reached_airport_exit = 1
									IF flag_has_player_got_wanted_level = 0
										IF NOT DOES_BLIP_EXIST blip_base
											ADD_BLIP_FOR_COORD coord_base_x coord_base_y coord_base_z blip_base							
										 //	CHANGE_BLIP_COLOUR blip_base YELLOW
										   PRINT_NOW dri2_25 5000 1 // get back to the pleasure domes
										   IF flag_player_starts_conversation_with_toreno = 0
												flag_player_starts_conversation_with_toreno = 1
											ENDIF
										ENDIF
									ELSE
										IF NOT DOES_BLIP_EXIST blip_paint_and_spray_farlie3
											ADD_SPRITE_BLIP_FOR_COORD coord_spray_shop_x_farlie3 coord_spray_shop_y_farlie3 coord_spray_shop_z_farlie3 RADAR_SPRITE_SPRAY blip_paint_and_spray_farlie3  			
					 					ENDIF
										PRINT_NOW dri2_46 5000 1   // GET TO THE PAINT AND SPRAY

										IF DOES_BLIP_EXIST blip_airport_exit
											REMOVE_BLIP blip_airport_exit
										ENDIF	
	  	 							ENDIF
								ELSE
									IF NOT DOES_BLIP_EXIST blip_airport_exit
										ADD_BLIP_FOR_COORD  -1734.05 -579.94 15.56 blip_airport_exit	
									ENDIF
									PRINT_NOW dri2_34 5000 1 // get out of the airport
								 //	CHANGE_BLIP_COLOUR blip_base YELLOW
								ENDIF
							ENDIF

						ENDIF


				
		    ENDIF

		ELSE

			flag_torch_truck_farlie3 = 0
			flag_torch_truck_farlie3 = 0
			flag_mission_failed_farlie3 = 1
			PRINT_NOW dri2_22 5000 1 // toreno is dead
					
			RETURN
		ENDIF

	RETURN



tbone_checks_farlie3:

		IF NOT IS_CHAR_DEAD char_tbone_farlie3
		//   	IF IS_CHAR_PLAYING_ANIM char_tbone_farlie3 man_run
		   //		SET_CHAR_ANIM_SPEED char_tbone_farlie3 man_run 10.0
		   //	ENDIF		 
		
			IF flag_is_tbone_in_group_farlie3 = 0
	   			IF NOT IS_GROUP_MEMBER char_tbone_farlie3 Players_Group  
	   		        PRINT_NOW dri2_32 5000 1 //"WHERE ARE U GOING?"

					IF DOES_BLIP_EXIST blip_base
						REMOVE_BLIP blip_base
					ENDIF

					IF DOES_BLIP_EXIST blip_destination_farlie3
						REMOVE_BLIP	blip_destination_farlie3
					ENDIF

					IF DOES_BLIP_EXIST blip_airport_exit
						REMOVE_BLIP blip_airport_exit
					ENDIF

					IF DOES_BLIP_EXIST blip_airport_farlie3
						REMOVE_BLIP blip_airport_farlie3
					ENDIF


			        ADD_BLIP_FOR_CHAR char_tbone_farlie3 blip_tbone
				  //	CHANGE_BLIP_COLOUR blip_tbone BLUE 
					SET_BLIP_AS_FRIENDLY blip_tbone TRUE

					flag_is_tbone_in_group_farlie3 = 1
				ENDIF
			ENDIF





				IF flag_is_tbone_in_group_farlie3 = 1
			  //	WHILE NOT IS_GROUP_MEMBER char_tbone_farlie3 Players_Group
				//	WAIT 0


					IF NOT IS_CHAR_DEAD char_tbone_farlie3 

						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer char_tbone_farlie3 8.0 8.0 8.0 FALSE
							IF NOT IS_GROUP_MEMBER char_tbone_farlie3 Players_Group 
						   		 SET_GROUP_MEMBER Players_Group char_tbone_farlie3
							ENDIF
						    flag_is_tbone_in_group_farlie3 = 0               
							REMOVE_BLIP blip_tbone

							IF flag_is_toreno_in_group_farlie3 = 0
								IF flag_get_back_to_base = 1
									IF flag_reached_airport_exit = 1
	  									IF flag_has_player_got_wanted_level = 0
											IF NOT DOES_BLIP_EXIST blip_base
												ADD_BLIP_FOR_COORD coord_base_x coord_base_y coord_base_z blip_base							
											 //	CHANGE_BLIP_COLOUR blip_base YELLOW
											   PRINT_NOW dri2_25 5000 1 // get back to the pleasure domes

											   IF flag_player_starts_conversation_with_toreno = 0
													flag_player_starts_conversation_with_toreno = 1
												ENDIF


											ENDIF
										ELSE
											IF NOT DOES_BLIP_EXIST blip_paint_and_spray_farlie3
												ADD_SPRITE_BLIP_FOR_COORD coord_spray_shop_x_farlie3 coord_spray_shop_y_farlie3 coord_spray_shop_z_farlie3 RADAR_SPRITE_SPRAY blip_paint_and_spray_farlie3  			
						 					ENDIF
											PRINT_NOW dri2_46 5000 1   // GET TO THE PAINT AND SPRAY

											IF DOES_BLIP_EXIST blip_airport_exit
												REMOVE_BLIP blip_airport_exit
											ENDIF	
		  	 							ENDIF
									ELSE
										IF NOT DOES_BLIP_EXIST blip_airport_exit
											ADD_BLIP_FOR_COORD  -1734.05 -579.94 15.56 blip_airport_exit
										ENDIF
										PRINT_NOW dri2_34 5000 1 // get out of the airport						
									  //	CHANGE_BLIP_COLOUR blip_base YELLOW
									ENDIF
								ENDIF
							ENDIF

							IF flag_get_to_location_of_girl = 1
								IF flag_phone_dialog_driv3 >= 5	 // the construction site
								AND flag_phone_dialog_driv3 < 8 
									IF NOT DOES_BLIP_EXIST blip_destination_farlie3	
										ADD_BLIP_FOR_COORD -2071.0 209.0 35.0 blip_destination_farlie3 
									ENDIF
								ENDIF

								IF flag_phone_dialog_driv3 >= 9	 // docks
								AND flag_phone_dialog_driv3 < 12 
									IF NOT DOES_BLIP_EXIST blip_destination_farlie3
										ADD_BLIP_FOR_COORD -2071.0 209.0 35.0 blip_destination_farlie3 
									ENDIF

								ENDIF

								IF flag_phone_dialog_driv3 >= 15	 // airport 
								AND flag_phone_dialog_driv3 < 24 
												
									IF NOT DOES_BLIP_EXIST blip_destination_farlie3
										ADD_BLIP_FOR_COORD coord_airport_x_driv3 coord_airport_y_driv3 coord_airport_z_driv3 blip_airport_farlie3
									ENDIF 
								ENDIF






								// check if counting is running during the first part of mission
								IF flag_get_to_location_of_girl = 1
									// if player runs out of time
									IF timer_countdown_farlie3 <= 0
										flag_get_to_location_of_girl = 0
										flag_mission_failed_farlie3 = 1
										// play a sound of an explosion over the phone
										PRINT_NOW ( FAR3_FD ) 5000 1	 // shit, the phones gone dead!
										RETURN
									ENDIF
								ENDIF
							ENDIF




						ENDIF



					ELSE
						flag_shoot_correct_container = 0
						flag_get_to_location_of_girl = 0
						flag_torch_truck_farlie3 = 0
						flag_mission_failed_farlie3 = 1
						PRINT_NOW dri2_31 5000 1 // tbone is dead
						RETURN	
					ENDIF
			   //	ENDWHILE
				ENDIF


		  //  ENDIF
		ELSE
				flag_shoot_correct_container = 0
				flag_get_to_location_of_girl = 0
				flag_torch_truck_farlie3 = 0
				flag_mission_failed_farlie3 = 1
				PRINT_NOW dri2_31 5000 1 // tbone is dead
				RETURN	
			RETURN
		ENDIF
RETURN


// *********************************** audio******************************		 
load_and_play_audio_farlie3:

	IF play_audio_flag_farlie3 = 0
		CLEAR_MISSION_AUDIO 1

		LOAD_MISSION_AUDIO 1 farlie3_audio[index_dialogue_farlie3]

		play_audio_flag_farlie3 = 1
	ENDIF

	IF play_audio_flag_farlie3 = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PLAY_MISSION_AUDIO 1
			PRINT_NOW $farlie3_text[index_dialogue_farlie3] 10000 1

			// adding blips during conversations//////////////////////
			IF flag_phone_dialog_driv3 = 0
				flag_converstion_going_on_farlie3 = 1
			ENDIF

			IF flag_phone_dialog_driv3 = 5
				IF NOT DOES_BLIP_EXIST blip_destination_farlie3
					ADD_BLIP_FOR_COORD -2071.0 209.0 35.0 blip_destination_farlie3 
				ENDIF
			ENDIF

			IF flag_phone_dialog_driv3 = 8
				REMOVE_BLIP blip_destination_farlie3
				flag_converstion_going_on_farlie3 = 1
			ENDIF

			IF flag_phone_dialog_driv3 = 11
				ADD_BLIP_FOR_COORD -1772.0 152.0 152.0 blip_destination_farlie3 
			ENDIF	
			
			IF flag_phone_dialog_driv3 = 14
				flag_converstion_going_on_farlie3 = 1
				REMOVE_BLIP blip_destination_farlie3
			ENDIF

			IF flag_phone_dialog_driv3 = 18
			 //	IF NOT DOES_BLIP_EXIST blip_destination_farlie3
					
					ADD_BLIP_FOR_COORD coord_airport_x_driv3 coord_airport_y_driv3 coord_airport_z_driv3 blip_airport_farlie3
			//	ENDIF
			ENDIF




			
			
			////////////////////////////////////////////////////////////

			play_audio_flag_farlie3 = 2
		ENDIF
	ENDIF



	IF play_audio_flag_farlie3 = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
		   //	temp_int = c3_counter - 1
			TIMERA = 0
			play_audio_flag_farlie3 = 0
			CLEAR_THIS_PRINT $farlie3_text[index_dialogue_farlie3]
			index_dialogue_farlie3++
			flag_phone_dialog_driv3++
		ENDIF
	ENDIF



RETURN






 
// ******************************** MISSION PASSED! ********************************************
			mission_passed_driv3:
				PLAY_MISSION_PASSED_TUNE 1
				CLEAR_WANTED_LEVEL player1

				REMOVE_BLIP synd_contact_blip



				PRINT_WITH_NUMBER_BIG ( M_PASSS ) 7000 5000 1 //"Mission Passed!" //100 being the amount of cash
				ADD_SCORE player1 7000//amount of cash

				//INCREMENT_INT_STAT RESPECT 25//amount of respect
				AWARD_PLAYER_MISSION_RESPECT 25


			 	flag_synd_mission_counter ++

				REGISTER_MISSION_PASSED ( FAR_3 )
				PLAYER_MADE_PROGRESS 1

				REMOVE_BLIP synd_contact_blip

				ADD_SPRITE_BLIP_FOR_CONTACT_POINT garageX garageY garageZ synd_blip_icon synd_contact_blip
 				SET_INT_STAT PASSED_FARLIE3 1

			RETURN


// ******************************** MISSION FAILED!*********************************************

			mission_failed_driv3:
				PRINT_BIG ( M_FAIL ) 5000 1	//"Mission Failed!
			RETURN


// ******************************** MISSION CLEANED!*********************************************
			mission_cleanup_driv3:
				flag_player_on_mission = 0

				flag_is_farlie3_running = 0
				


  	  			// restoring flying skill
	   //			SET_INT_STAT FLYING_SKILL stored_flying_skill


				IF IS_PLAYER_PLAYING player1
			   //		SHUT_CHAR_UP scplayer TRUE
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

				ENDIF


				SET_POLICE_IGNORE_PLAYER Player1 OFF

			   	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi

				MARK_MODEL_AS_NO_LONGER_NEEDED BURRITO
				MARK_MODEL_AS_NO_LONGER_NEEDED dnb1 
				MARK_MODEL_AS_NO_LONGER_NEEDED dnb2
				MARK_MODEL_AS_NO_LONGER_NEEDED dnb3



				REMOVE_PICKUP pickup_gun_next_to_guard


				MARK_MODEL_AS_NO_LONGER_NEEDED stretch

				index_farlie3 = 0
				WHILE index_farlie3 < 3
					MARK_CHAR_AS_NO_LONGER_NEEDED char_baddies_farlie3[index_farlie3]
					index_farlie3++
				ENDWHILE

				MARK_CHAR_AS_NO_LONGER_NEEDED char_escorts_farlie3[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED char_escorts_farlie3[1]
				

			  //	DELETE_OBJECT invisible_object_farlie3  
				REMOVE_CHAR_ELEGANTLY char_toreno_farlie3
				REMOVE_CHAR_ELEGANTLY char_tbone_farlie3
				//MARK_CHAR_AS_NO_LONGER_NEEDED char_toreno_farlie3

				UNLOAD_SPECIAL_CHARACTER 1 // unloading toreno
				UNLOAD_SPECIAL_CHARACTER 2 // unloading tbone


		/*		MARK_CAR_AS_NO_LONGER_NEEDED car_cop1_farlie3 
				MARK_CAR_AS_NO_LONGER_NEEDED car_cop2_farlie3

				MARK_CHAR_AS_NO_LONGER_NEEDED char_cop1_farlie3 
				MARK_CHAR_AS_NO_LONGER_NEEDED char_cop2_farlie3   */

				MARK_CAR_AS_NO_LONGER_NEEDED  car_van_with_buddy_inside

				MARK_CAR_AS_NO_LONGER_NEEDED car_escorts_farlie3[0] 
				MARK_CAR_AS_NO_LONGER_NEEDED car_escorts_farlie3[1]

				MARK_CAR_AS_NO_LONGER_NEEDED get_away_car
				MARK_MODEL_AS_NO_LONGER_NEEDED freeway
				MARK_CAR_AS_NO_LONGER_NEEDED car_escorts_farlie3[0]
				MARK_CAR_AS_NO_LONGER_NEEDED car_escorts_farlie3[1]
				MARK_CAR_AS_NO_LONGER_NEEDED car_players_car_farlie3

				REMOVE_BLIP blip_baddies_passengers[0]
				REMOVE_BLIP blip_baddies_passengers[1]
				REMOVE_BLIP blip_baddies_passengers[2]
			  	REMOVE_BLIP blip_players_car
			   	REMOVE_BLIP blip_van_farlie3
				REMOVE_BLIP blip_airport_farlie3
				REMOVE_BLIP blip_van_driver
				REMOVE_BLIP blip_base
				REMOVE_BLIP blip_torneo
				REMOVE_BLIP blip_tbone
				REMOVE_BLIP blip_airport_exit
				REMOVE_BLIP	blip_destination_farlie3

				REMOVE_BLIP blip_escorts_farlie3[0]
				REMOVE_BLIP blip_escorts_farlie3[1] 
				REMOVE_BLIP blip_paint_and_spray_farlie3

				MARK_CAR_AS_NO_LONGER_NEEDED car_players_car_farlie3 
				MARK_MODEL_AS_NO_LONGER_NEEDED PREMIER


				
				CLEAR_ONSCREEN_TIMER timer_countdown_farlie3
				CLEAR_ONSCREEN_COUNTER int_distance_player_girl

				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1908.9, 292.3, 40.0 RADAR_SPRITE_SPRAY spray_shop[4] 


				GET_GAME_TIMER timer_mobile_start

				MISSION_HAS_FINISHED
		
			RETURN

	}