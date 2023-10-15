MISSION_START
// *****************************************************************************************
// *********************************** Oddjob mission  ************************************* 
// ****************************************  burglary **************************************
// ********************************************DB*******************************************
// ***                                                                                   ***
// *****************************************************************************************

SCRIPT_NAME BURGJB
// Mission start stuff

GOSUB mission_start_burgl

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_burgl_failed
ENDIF

GOSUB mission_cleanup_burgl

MISSION_END
{
////////////////////////////
// Variables for mission ///
////////////////////////////


/// others

LVAR_INT burglary_load_value 
LVAR_INT burglary_city
LVAR_INT burglary_temp_int
LVAR_INT burglary_current_area
LVAR_INT burglary_current_score burglary_old_score burglary_score_dif

LVAR_FLOAT  burglary_player_sound_total
LVAR_FLOAT burglary_garage_distance burglary_distance_temp
LVAR_FLOAT burglary_temp_float


LVAR_FLOAT burglary_total_made_f  burglary_load_value_f
LVAR_INT burglary_session_items 
VAR_INT burglary_session_made
LVAR_INT burglary_items_in_truck

/// Objects
LVAR_INT burglary_object


/// Vehicles
LVAR_INT burglary_truck 



/// Blips
LVAR_INT burglary_drop_off_blip
LVAR_INT burglary_van_blip
LVAR_INT burglary_entry_exit_blip

/// Coords
LVAR_FLOAT burglary_drop_x[3] burglary_drop_y[3] burglary_drop_z[3]
LVAR_FLOAT burglary_player_x burglary_player_y burglary_player_z
LVAR_FLOAT burglary_car_x burglary_car_y burglary_car_z
LVAR_FLOAT burglary_entry_exit_x burglary_entry_exit_y burglary_entry_exit_z burglary_entry_exit_heading
LVAR_FLOAT burglary_int_x burglary_int_y burglary_int_z
LVAR_FLOAT burglary_garage_x[3] burglary_garage_y[3]	 burglary_garage_z[3]
LVAR_FLOAT burglary_temp_x 	burglary_temp_y	 burglary_temp_z



VAR_TEXT_LABEL burglary_garage[3]

/// Sequences


//// Counters and timers
//VAR_INT  burglary_player_stealth
VAR_INT  burglary_time_to_daylight
VAR_INT burglary_time_to_get_out
VAR_INT burglary_time_to_lockup


LVAR_INT burglary_time_to_daylight_temp
LVAR_INT burglary_timer_start
LVAR_INT burglary_timer_current
LVAR_INT burglary_time_elapsed


/// Flags
LVAR_INT burglary_player_in_house
LVAR_INT burglary_player_in_truck
LVAR_INT burglary_player_holding_object
LVAR_INT burgl_mission_failed
LVAR_INT burgl_mission_ended
LVAR_INT burglary_van_blip_vis
LVAR_INT burglary_player_discovered
LVAR_INT burglary_drop_off_blip_vis 
LVAR_INT burglary_help_displayed
LVAR_INT burglary_bar_displayed
LVAR_INT burglary_switch_flag
LVAR_INT burglary_has_been_in_a_house
LVAR_INT burglary_safety_flag
LVAR_INT burglary_times_discovered	
LVAR_INT burglary_timer_started
LVAR_INT burglary_entry_exit_blip_added
LVAR_INT burglary_garages_setup

LVAR_INT burglary_cops_called
LVAR_INT burglary_out_of_time
LVAR_INT burglary_brains_active
LVAR_INT burglary_new_house





//VAR_INT burglary_player_spotted


IF done_burglary_progress = 0
	//REGISTER_MISSION_GIVEN
ENDIF 

// ****************************************Mission Start************************************

mission_start_burgl:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

WAIT 0


SET_FADING_COLOUR 0 0 0

//CLEAR_THIS_PRINT_BIG_NOW 1
LOAD_MISSION_TEXT BURGLAR

ENABLE_BURGLARY_HOUSES           TRUE


//////////////////////////////
/// REQUEST MISSION MODELS ///
//////////////////////////////



////////////////////////////////
/// INITIALISE THE VARIABLES ///
////////////////////////////////

/// flags
burglary_player_in_house		= 0	
burglary_player_in_truck		= 0
burglary_player_holding_object	= 0
burgl_mission_failed			= 0
burgl_mission_ended 			= 0
burglary_van_blip_vis			= 0
burglary_bar_displayed			= 0
burglary_player_discovered		= 0
burglary_drop_off_blip_vis 		= 0
burglary_help_displayed			= 0
burglary_has_been_in_a_house	= 0
burglary_switch_flag			= 0
burglary_entry_exit_blip_added	= 0
burglary_garages_setup			= 0
burglary_cops_called			= 0	
burglary_out_of_time			= 0
burglary_new_house				= 0

burglary_session_items 			= 0
burglary_session_made			= 0


burglary_timer_current			= 0
burglary_timer_start			= 0
burglary_timer_started			= 0

burglary_time_to_get_out		= 10
burglary_time_to_lockup			= 300000
burglary_time_elapsed			= 0
burglary_times_discovered	    = 0
						 
burglary_player_sound_total 	= 0.0
burglary_noise					= 0.0

burglary_garage_distance 		= 9999999.0
burglary_distance_temp			= 9999999.0




/// Co-ords
// LA	   2472.00, -2007.00, 14.00
burglary_drop_x[0] 				= 2741.0  
burglary_drop_y[0] 				= -2006.8068
burglary_drop_z[0]				= 14.00
$burglary_garage[0]				= burg_lk
burglary_garage_x[0] 			= 2741.2412      
burglary_garage_y[0]	 		= -2011.2740
burglary_garage_z[0]			= 13.5869

// San Fran
burglary_drop_x[1] 				=  -2102.5396               	   
burglary_drop_y[1] 				=  -15.5931 
burglary_drop_z[1]				=  34.3203
$burglary_garage[1]				= brgSFSE
burglary_garage_x[1] 			= -2109.3201
burglary_garage_y[1]	 		=   -15.7967
burglary_garage_z[1]			=  34.3203

// Vegas
burglary_drop_x[2] 				= 2609.2476
burglary_drop_y[2] 				= 1439.3466
burglary_drop_z[2]				= 9.8203  
$burglary_garage[2]				= vgElock
burglary_garage_x[2] 			= 2609.0117
burglary_garage_y[2]	 		= 1445.9362
burglary_garage_z[2]			= 9.8203

/// others						
burglary_load_value				= 0
burglary_items_in_truck			= 0
burglary_player_spotted			= 0
burglary_brains_active			= 0
burglary_player_stealth 		= 0


disable_mod_garage = 1




STORE_SCORE player1 burglary_current_score 
STORE_SCORE player1 burglary_old_score


IF burglary_items_in_truck	= 99
	CREATE_OBJECT briefcase 0.0 0.0 0.0 burglary_object
	ADD_BLIP_FOR_COORD  0.0 0.0 0.0	burglary_drop_off_blip
	ADD_BLIP_FOR_COORD  0.0 0.0 0.0 burglary_entry_exit_blip
ENDIF


IF  NOT IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
	DRAW_ODDJOB_TITLE_BEFORE_FADE FALSE
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
		GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1  balaclava  balaclava CLOTHES_TEX_EXTRA1   	
		BUILD_PLAYER_MODEL player1
		 
	DO_FADE 1000 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	DRAW_ODDJOB_TITLE_BEFORE_FADE TRUE	
ENDIF



	   



//GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1  0  0 CLOTHES_TEX_EXTRA1
//BUILD_PLAYER_MODEL player1

//////////////////////////////
/// PLAYER INTRO CUT-SCENE ///
//////////////////////////////



GET_TIME_OF_DAY hours minutes
IF hours >= 20	
	burglary_time_to_daylight_temp = hours * 60
	burglary_time_to_daylight_temp += minutes	
	burglary_temp_int = 1440 - burglary_time_to_daylight_temp // time til 24:00 in mins
	burglary_temp_int += 360 // time til 06:00 in mins
	burglary_temp_int *= 1000 // time in miliseconds to 06:00 
	//burglary_temp_int += 10000
	burglary_time_to_daylight = burglary_temp_int // time until daylight								
ENDIF

IF hours < 6  //got til 6 in moring	
	burglary_time_to_daylight_temp = hours * 60
	burglary_time_to_daylight_temp += minutes
	burglary_temp_int  = 360 - burglary_time_to_daylight_temp // time in minutes until 06:00
	burglary_temp_int *= 1000 // time in miliseconds to 06:00
	//burglary_temp_int += 10000
	burglary_time_to_daylight = burglary_temp_int // time until daylight		 
ENDIF

DISPLAY_ONSCREEN_TIMER_WITH_STRING  burglary_time_to_daylight TIMER_DOWN BURG52
DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING burglary_session_made COUNTER_DISPLAY_NUMBER	 1 BURG51	 //or can use burglary_session_made
//set entry exits on here


WAIT 0

IF IS_CHAR_IN_ANY_CAR scplayer
	STORE_CAR_CHAR_IS_IN scplayer burglary_truck
ENDIF


///////////\\\\\\\\\\\\\\
/// MAIN MISSION LOOP \\\
///////////\\\\\\\\\\\\\\


burgl_main_loop:

WAIT 0

///////////////////
/// DEBUG STUFF ///
///////////////////

//////////////////////////////////////////////////////////////////////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE 						//
   burglary_time_to_daylight = 10000000
ENDIF										 						//
											 						//
								 						//
										 						//
//////////////////////////////////////////////////////////////////////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1							//
	SET_CHAR_COORDINATES scplayer 2741.9282 -1993.2313 12.3669 
	SET_CHAR_HEADING scplayer 173.5173				//
ENDIF																//
//////////////////////////////////////////////////////////////////////	
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2	 						//
	SET_CHAR_COORDINATES scplayer 2639.0 2039.9108 9.8052			//
ENDIF																//
//////////////////////////////////////////////////////////////////////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_BSPACE 						//
   burglary_time_to_daylight = 5000
ENDIF																//
//////////////////////////////////////////////////////////////////////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_0	 						//
	burglary_time_to_lockup = 5000
ENDIF																//
//////////////////////////////////////////////////////////////////////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_5
burglary_total_made = 11000
		 						//
ENDIF																//
//////////////////////////////////////////////////////////////////////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_6							//
	burglary_items_in_truck = 3				 						//
ENDIF																//
//////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////

	
IF burglary_out_of_time = 0
	IF burglary_time_to_daylight = 0
		CLEAR_PRINTS 
		// set entry exits off here
		ENABLE_BURGLARY_HOUSES           FALSE							
		
		IF burglary_player_in_house = 1
			SET_PLAYER_CONTROL Player1 OFF
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			CLEAR_HELP
			SET_AREA_VISIBLE 0
			REQUEST_COLLISION burglary_entry_exit_x burglary_entry_exit_y 
			LOAD_SCENE burglary_entry_exit_x burglary_entry_exit_y burglary_entry_exit_z
			SET_CHAR_COORDINATES_NO_OFFSET scplayer burglary_entry_exit_x burglary_entry_exit_y burglary_entry_exit_z 
			SHUT_CHAR_UP scplayer FALSE
			burglary_entry_exit_heading += 180.0
			SET_CHAR_HEADING scplayer burglary_entry_exit_heading			
			SET_CHAR_HAS_USED_ENTRY_EXIT scplayer burglary_int_x burglary_int_y 5.0
			LOAD_SCENE burglary_entry_exit_x burglary_entry_exit_y burglary_entry_exit_z
			//SET_CAMERA_BEHIND_PLAYER 
			IF burglary_items_in_truck = 0					   			
				IF  IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
					GIVE_PLAYER_CLOTHES Player1  0 0  CLOTHES_TEX_EXTRA1   	
					BUILD_PLAYER_MODEL player1
				ENDIF
			ENDIF
			burglary_player_in_house = 0
			burglary_player_in_truck = 0
			IF NOT IS_MINIGAME_IN_PROGRESS
				RESTORE_CAMERA_JUMPCUT
			ENDIF
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			SET_PLAYER_CONTROL Player1 ON												
		ELSE
			IF burglary_items_in_truck = 0
				IF  IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
					
					IF IS_PLAYER_CONTROL_ON	Player1
					SET_PLAYER_CONTROL Player1 OFF
					ENDIF
					DO_FADE 1000 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE		
						GIVE_PLAYER_CLOTHES Player1  0 0  CLOTHES_TEX_EXTRA1   	
						BUILD_PLAYER_MODEL player1
					IF NOT IS_MINIGAME_IN_PROGRESS
					RESTORE_CAMERA_JUMPCUT
					ENDIF
				
					DO_FADE 1000 FADE_IN
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					//SET_PLAYER_CONTROL Player1 ON
				ENDIF
			ENDIF		
		ENDIF		
		
		CLEAR_ONSCREEN_TIMER burglary_time_to_daylight
		
		IF burglary_items_in_truck > 0
			DISPLAY_ONSCREEN_TIMER_WITH_STRING  burglary_time_to_lockup TIMER_DOWN BURG3		
			CLEAR_PRINTS	   	    	    	
	    	PRINT (BURG2) 5000 1 //~s~You will not be able to enter any more houses, offload what you've already stashed within the time limit.
			IF burglary_player_in_truck = 1
			   PRINT (BURG26) 5000 1 //~s~Go and offload the goods
		   	   burglary_switch_flag = 2
		   ELSE
			   IF burglary_player_holding_object	= 1
				   PRINT (BURG27) 5000 1 //~s~Put the goods in the truck			   			   
				   burglary_switch_flag = 1
			   ELSE
				   PRINT BURG1	5000 1 //~s~get in the truck and go offload what you've stashed. 
				   burglary_switch_flag = 3
			   ENDIF
			ENDIF
		ELSE
			burglary_time_to_lockup = 0
			DELETE_OBJECT burglary_object
	   		MARK_OBJECT_AS_NO_LONGER_NEEDED burglary_object
		    PRINT (BURG53) 5000 1 //~r~You are out of time, come back another night and finish the job
		    GOTO mission_burgl_failed
		ENDIF
		
		burglary_out_of_time = 1		
	ELSE
		// find out how long until 6 am \\
		
		GET_TIME_OF_DAY hours minutes
		IF hours >= 20	
			burglary_time_to_daylight_temp = hours * 60
			burglary_time_to_daylight_temp += minutes	
			burglary_temp_int = 1440 - burglary_time_to_daylight_temp // time til 24:00 in mins
			burglary_temp_int += 360 // time til 06:00 in mins
			burglary_temp_int++
			burglary_temp_int *= 1000 // time in miliseconds to 06:00 			
			burglary_time_to_daylight = burglary_temp_int // time until daylight								
		ENDIF

		IF hours < 6  //got til 6 in moring	
			burglary_time_to_daylight_temp = hours * 60
			burglary_time_to_daylight_temp += minutes
			burglary_temp_int  = 360 - burglary_time_to_daylight_temp // time in minutes until 06:00
			burglary_temp_int++
			burglary_temp_int *= 1000 // time in miliseconds to 06:00			
			burglary_time_to_daylight = burglary_temp_int // time until daylight		 
		ENDIF		
	ENDIF
ELSE
	IF burglary_time_to_lockup = 0
	   CLEAR_PRINTS	   
	   IF IS_PLAYER_CONTROL_ON	Player1
		SET_PLAYER_CONTROL Player1 OFF
		ENDIF
	   
	   DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		IF  IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
		GIVE_PLAYER_CLOTHES Player1  0 0  CLOTHES_TEX_EXTRA1   	
		BUILD_PLAYER_MODEL player1
		ENDIF
		IF NOT IS_MINIGAME_IN_PROGRESS
		RESTORE_CAMERA_JUMPCUT
		ENDIF
		DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		//SET_PLAYER_CONTROL Player1 ON
	   PRINT (BURG53) 5000 1 //~r~You are out of time, come back another night and finish the job
	   
	   DELETE_OBJECT burglary_object
	   MARK_OBJECT_AS_NO_LONGER_NEEDED burglary_object
	   
	   GOTO mission_burgl_failed
	ENDIF
ENDIF





IF burgl_mission_failed = 0		
	//////////////////////////////
	//// Check for cancelation ////
	///////////////////////////////					
	GOSUB burgl_cancelled_checks


		GET_AREA_VISIBLE burglary_current_area
		IF burglary_current_area = 0
	   		IF NOT IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava			
				PRINT (BURG62)  5000 1 //" ~r~You no longer have the balaclava. "	   	  
	   	  		GOTO mission_burgl_failed
			ENDIF
		ENDIF




	/////////////////////////////////
	/////////////////////////////////
IF burglary_player_in_truck = 1
	
	IF IS_WANTED_LEVEL_GREATER player1 0
	AND	burglary_items_in_truck > 0				
	   	IF NOT burglary_switch_flag = 5
		   	CLEAR_PRINTS 		   	
		   	PRINT (BURG50) 5000 1 // ~s~You have to lose the heat before you can offload the goods.
		   	burglary_switch_flag = 5
	   	ENDIF
	ELSE				   
		IF NOT burglary_switch_flag = 2
			CLEAR_PRINTS
			IF burglary_items_in_truck > 0
				IF burglary_out_of_time = 0
					PRINT (BURG4) 5000 1 //~s~Go and offload the goods at the ~y~lockup~s~, or find a house to break into.
				ELSE
					PRINT (BURG26) 5000 1 //~s~Go and offload the goods
				ENDIF
			ELSE
				IF burglary_out_of_time = 0
				PRINT (BURG29) 5000 1 //~s~Go and find a house to break into.
				ENDIF												
			ENDIF
			burglary_switch_flag = 2	
		ENDIF
	ENDIF
ELSE
	IF burglary_out_of_time = 0
		IF burglary_player_holding_object	= 1
			
			IF NOT burglary_switch_flag = 1			
				IF burglary_player_in_house = 0
				CLEAR_PRINTS
				PRINT (BURG27) 5000 1 //~s~Put the goods in the truck
				burglary_switch_flag = 1
				ENDIF
			ENDIF
		ELSE
			IF burglary_player_in_house = 1			 
				IF NOT burglary_switch_flag = 4
					CLEAR_PRINTS
					PRINT (BURG30) 5000 1 //~s~Find something worth stealing.
					burglary_switch_flag = 4
				ENDIF
			ELSE
				IF NOT burglary_switch_flag = 3
					CLEAR_PRINTS
					IF burglary_items_in_truck = 0
						PRINT (BURG29) 5000 1 //~s~Go and find a house to break into.
					ELSE
						PRINT (BURG31) 5000 1 //~s~Go and find a house to break into, or get in the truck and go offload what you've already stashed.
					ENDIF
						burglary_switch_flag = 3
				ENDIF			 
			ENDIF		
		ENDIF
	ELSE
		IF burglary_player_holding_object  = 1			
			IF NOT burglary_switch_flag = 1			
				IF burglary_player_in_house = 0
				CLEAR_PRINTS
				PRINT (BURG27) 5000 1 //~s~Put the goods in the truck
				burglary_switch_flag = 1
				ENDIF
			ENDIF
		ELSE			
			IF NOT burglary_switch_flag = 3
				PRINT BURG1	5000 1 //~s~get in the truck and go offload what you've stashed.
				burglary_switch_flag = 3
			ENDIF			 				
		ENDIF	
	ENDIF		
ENDIF





	/////////////////////////////////
	/////////////////////////////////
	
	IF NOT IS_CAR_DEAD burglary_truck
		
		////////////////////////////////////////////
		///	CHECK IF PLAYER IS IN BURGLARY TRUCK ///
		////////////////////////////////////////////
		
		IF burglary_player_in_house = 0				   		   		
			IF NOT IS_CHAR_IN_CAR scplayer burglary_truck
				
				burglary_player_in_truck = 0				
				
				IF burglary_drop_off_blip_vis = 1
					REMOVE_BLIP burglary_drop_off_blip
					burglary_drop_off_blip_vis = 0
				ENDIF
				
				IF burglary_van_blip_vis = 0
					ADD_BLIP_FOR_CAR burglary_truck burglary_van_blip
					CHANGE_BLIP_DISPLAY burglary_van_blip BLIP_ONLY
					SET_BLIP_AS_FRIENDLY burglary_van_blip TRUE
					burglary_van_blip_vis = 1
				ENDIF
			ELSE

				burglary_player_in_truck = 1				
				IF burglary_van_blip_vis = 1										
					burglary_garages_setup  = 0
					REMOVE_BLIP burglary_van_blip
					burglary_van_blip_vis = 0
				ENDIF
										
			
				IF burglary_items_in_truck > 0
					IF IS_WANTED_LEVEL_GREATER player1 0
						IF burglary_drop_off_blip_vis = 1							
							REMOVE_BLIP burglary_drop_off_blip
							burglary_drop_off_blip_vis = 0
						ENDIF
					ELSE //IS_WANTED_LEVEL_GREATER player1 0
						IF burglary_drop_off_blip_vis = 0							
							ADD_BLIP_FOR_COORD burglary_drop_x[burglary_city] burglary_drop_y[burglary_city] burglary_drop_z[burglary_city] burglary_drop_off_blip
							burglary_drop_off_blip_vis = 1
						ENDIF
					ENDIF					
				ENDIF
			
			ENDIF
		ENDIF   
	







		///////////////////////
		//// GARAGE CHECKS ////
		///////////////////////
		
		IF burglary_garages_setup  = 0
			/// find the closest garage
			GET_INT_STAT CITIES_PASSED Return_cities_passed
			IF Return_cities_passed = 0
			// la only open
				burglary_city = 0
			ELSE
				IF Return_cities_passed = 1
				// la and sanfran open
					GET_CHAR_COORDINATES scplayer burglary_player_x burglary_player_y burglary_player_z
				   	GET_CITY_FROM_COORDS burglary_player_x burglary_player_y burglary_player_z burglary_city								
					IF burglary_city > 0
						burglary_city-= 1
					ELSE
						GET_DISTANCE_BETWEEN_COORDS_3D burglary_player_x burglary_player_y burglary_player_z burglary_drop_x[0] burglary_drop_y[0] burglary_drop_z[0] burglary_garage_distance
						burglary_city = 0
						burglary_distance_temp = burglary_garage_distance
						GET_DISTANCE_BETWEEN_COORDS_3D burglary_player_x burglary_player_y burglary_player_z burglary_drop_x[1] burglary_drop_y[1] burglary_drop_z[1] burglary_garage_distance
						IF burglary_garage_distance < burglary_distance_temp
							burglary_city = 1						
						ENDIF
					ENDIF
				ELSE
					// all cities opened
					GET_CHAR_COORDINATES scplayer burglary_player_x burglary_player_y burglary_player_z
				   	GET_CITY_FROM_COORDS burglary_player_x burglary_player_y burglary_player_z burglary_city
					
					
					IF burglary_city > 0
						burglary_city-= 1
					ELSE //player not in a city
						//FIND CLOSEST GARAGE ?
													
						GET_DISTANCE_BETWEEN_COORDS_3D burglary_player_x burglary_player_y burglary_player_z burglary_drop_x[0] burglary_drop_y[0] burglary_drop_z[0] burglary_garage_distance
						burglary_city = 0
						burglary_distance_temp = burglary_garage_distance
						GET_DISTANCE_BETWEEN_COORDS_3D burglary_player_x burglary_player_y burglary_player_z burglary_drop_x[1] burglary_drop_y[1] burglary_drop_z[1] burglary_garage_distance
						IF burglary_garage_distance < burglary_distance_temp
							burglary_city = 1
							burglary_distance_temp = burglary_garage_distance
						ENDIF
						GET_DISTANCE_BETWEEN_COORDS_3D burglary_player_x burglary_player_y burglary_player_z burglary_drop_x[2] burglary_drop_y[2] burglary_drop_z[2] burglary_garage_distance
						IF burglary_garage_distance < burglary_distance_temp
							burglary_city = 2						
						ENDIF							 
					ENDIF							
				ENDIF
			ENDIF
					
			/// set up the garage to appropriate type
																			
			CHANGE_GARAGE_TYPE $burglary_garage[burglary_city] GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE
			burglary_garages_setup  = 1			 

		ENDIF						 						
		
		
		///////////////////////////////////////////////
		///////////////////////////////////////////////
		///////////////////////////////////////////////

		
					
	IF NOT IS_CAR_DEAD burglary_truck
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer  burglary_drop_x[burglary_city] burglary_drop_y[burglary_city] burglary_drop_z[burglary_city] 20.0 20.0 4.0 FALSE
		OR LOCATE_CAR_3D burglary_truck burglary_drop_x[burglary_city] burglary_drop_y[burglary_city] burglary_drop_z[burglary_city] 20.0 20.0 4.0 FALSE	
			IF NOT IS_GARAGE_OPEN $burglary_garage[burglary_city]
				OPEN_GARAGE $burglary_garage[burglary_city]
			ENDIF

			IF burglary_garages_setup  = 1
				IF burglary_player_in_truck = 1
					IF burglary_items_in_truck > 0
						IF IS_WANTED_LEVEL_GREATER player1 0
							IF burglary_drop_off_blip_vis = 1
								CLEAR_PRINTS 
								PRINT (BURG50) 5000 1 // ~s~You have to lose the heat before you can offload the goods.
								REMOVE_BLIP burglary_drop_off_blip
								burglary_drop_off_blip_vis = 0
							ENDIF
						ELSE //IS_WANTED_LEVEL_GREATER player1 0
							IF burglary_drop_off_blip_vis = 0
								CLEAR_PRINTS
								PRINT (BURG26) 5000 1 //~s~Go and offload the goods
								ADD_BLIP_FOR_COORD burglary_drop_x[burglary_city] burglary_drop_y[burglary_city] burglary_drop_z[burglary_city] burglary_drop_off_blip
								burglary_drop_off_blip_vis = 1
							ELSE //burglary_drop_off_blip_vis = 0

	
								PRINT (BURG40) 1000 1//~s~Park the truck in the ~y~lockup~s~.								 
								IF LOCATE_CHAR_IN_CAR_3D scplayer  burglary_garage_x[burglary_city] burglary_garage_y[burglary_city]  burglary_garage_z[burglary_city]  2.9000  3.2000  5.0000  TRUE 					   	   
								/// unload the truck here
									SET_PLAYER_CONTROL player1 OFF
									SET_POLICE_IGNORE_PLAYER player1 TRUE
									CLEAR_PRINTS						   			   
									CLEAR_AREA burglary_garage_x[burglary_city] burglary_garage_y[burglary_city]  burglary_garage_z[burglary_city] 10.0 TRUE
									IF burglary_out_of_time = 1												
										CLEAR_ONSCREEN_TIMER  burglary_time_to_lockup
										burglary_time_to_lockup = 999999
									ENDIF

									IF NOT IS_GARAGE_CLOSED $burglary_garage[burglary_city]
										CLOSE_GARAGE $burglary_garage[burglary_city]
									ENDIF
									burglary_safety_flag = 0
									WHILE burglary_safety_flag = 0
										WAIT 0 
										IF IS_GARAGE_CLOSED	$burglary_garage[burglary_city]
											burglary_safety_flag = 1
										ENDIF
									ENDWHILE

									REMOVE_BLIP burglary_drop_off_blip					   
									burglary_load_value = burglary_items_in_truck * 20
									burglary_load_value *= burglary_items_in_truck
									burglary_total_items += burglary_items_in_truck
									burglary_total_made += burglary_load_value
									
									burglary_total_made_f	=# burglary_total_made
									burglary_load_value_f =# burglary_load_value

									burglary_session_items += burglary_items_in_truck
									burglary_session_made +=  burglary_load_value
									
									REGISTER_FLOAT_STAT LARGEST_BURGLARY_SWAG burglary_load_value_f
									INCREMENT_FLOAT_STAT MONEY_MADE_BURGLARY burglary_load_value_f //burglary_total_made_f
									INCREMENT_INT_STAT STOLEN_ITEMS_SOLD burglary_items_in_truck

									IF burglary_out_of_time = 1									
										IF IS_PLAYER_CONTROL_ON	Player1
										SET_PLAYER_CONTROL Player1 OFF
										ENDIF
									   
									    DO_FADE 1000 FADE_OUT
										WHILE GET_FADING_STATUS
											WAIT 0
										ENDWHILE
										IF  IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
										GIVE_PLAYER_CLOTHES Player1  0 0  CLOTHES_TEX_EXTRA1   	
										BUILD_PLAYER_MODEL player1
										ENDIF
										
										IF NOT IS_MINIGAME_IN_PROGRESS
										//RESTORE_CAMERA_JUMPCUT
										ENDIF

										DO_FADE 1000 FADE_IN
										WHILE GET_FADING_STATUS
											WAIT 0
										ENDWHILE
									ELSE
										IF burglary_total_made >= 10000
										AND done_burglary_progress = 0
											IF IS_PLAYER_CONTROL_ON	Player1
												SET_PLAYER_CONTROL Player1 OFF
											ENDIF
										   
										    DO_FADE 1000 FADE_OUT
											WHILE GET_FADING_STATUS
												WAIT 0
											ENDWHILE
											IF  IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
												GIVE_PLAYER_CLOTHES Player1  0 0  CLOTHES_TEX_EXTRA1   	
												BUILD_PLAYER_MODEL player1
											ENDIF
											
											IF NOT IS_MINIGAME_IN_PROGRESS
												//RESTORE_CAMERA_JUMPCUT
											ENDIF

											DO_FADE 1000 FADE_IN
											WHILE GET_FADING_STATUS
												WAIT 0
											ENDWHILE
										ENDIF		
									ENDIF

									REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
									IF burglary_items_in_truck  < 2									   	
										PRINT_WITH_2_NUMBERS_BIG BURG39 burglary_items_in_truck burglary_load_value 5000 6//6  //~s~This load: ~1~ item = $~1~
									ELSE
										PRINT_WITH_2_NUMBERS_BIG BURG36 burglary_items_in_truck burglary_load_value 5000 6//6  //~s~This load: ~1~ items = $~1~								   	
									ENDIF									   

									
									
									ADD_SCORE player1 burglary_load_value
									TIMERA = 0
									WHILE IS_MESSAGE_BEING_DISPLAYED 
									OR TIMERA < 4000
										WAIT 0
									ENDWHILE
												   									   
									IF NOT IS_GARAGE_OPEN $burglary_garage[burglary_city]
										OPEN_GARAGE $burglary_garage[burglary_city]
									ENDIF

									burglary_safety_flag = 0
									WHILE burglary_safety_flag = 0
										WAIT 0 
										IF IS_GARAGE_OPEN  $burglary_garage[burglary_city]
											burglary_safety_flag = 1
										ENDIF
									ENDWHILE

									IF burglary_total_made >= 10000
									AND done_burglary_progress = 0
										SET_PLAYER_CONTROL player1 TRUE
										SET_POLICE_IGNORE_PLAYER player1 FALSE
										GOTO mission_burgl_passed
									ELSE
										IF burglary_out_of_time = 0
											PRINT (BURG29) 5000 1 //~s~Go and find a house to break into.									   
										ENDIF
										burglary_drop_off_blip_vis = 0
										burglary_load_value = 0
										burglary_items_in_truck = 0
										burglary_times_discovered = 0								   	
									ENDIF
					   									   																												
									SET_PLAYER_CONTROL player1 TRUE
									SET_POLICE_IGNORE_PLAYER player1 FALSE
									IF burglary_out_of_time = 1		
									   //PRINT (BURG53) 5000 1 //~r~You are out of time, come back another night and finish the job
		    						   GOTO mission_burgl_failed									   									   
									   burglary_time_to_lockup = 0
									ENDIF

									 


								ENDIF
							ENDIF
							
						ENDIF

					ENDIF //burglary_drop_off_blip_vis = 0
				ENDIF
			
			ENDIF
		ELSE
			IF NOT IS_GARAGE_CLOSED $burglary_garage[burglary_city]
				CLOSE_GARAGE $burglary_garage[burglary_city]
			ENDIF								
		ENDIF
	ENDIF				
		
		
		
			
		

		////////////////////////////////////////////////////
		/// IF PLAYER OUT OF TRUCK CHECK IF IN/OUT HOUSE ///
		////////////////////////////////////////////////////
		
			
			
		 
		IF burglary_player_in_truck = 0
			IF IS_PROCEDURAL_INTERIOR_ACTIVE INTERIOR_HOUSE
				IF burglary_entry_exit_blip_added = 0
			  		//SET_DARKNESS_EFFECT TRUE -1
			  		GET_POSITION_OF_ENTRY_EXIT_CHAR_USED scplayer	burglary_entry_exit_x burglary_entry_exit_y burglary_entry_exit_z burglary_entry_exit_heading
					GET_CHAR_COORDINATES scplayer burglary_int_x burglary_int_y burglary_int_z					
					
					IF DOES_OBJECT_EXIST burglary_object
						IF IS_CHAR_HOLDING_OBJECT scplayer burglary_object
							GET_AREA_VISIBLE burglary_current_area
							SET_OBJECT_AREA_VISIBLE	 burglary_object burglary_current_area 
						ENDIF
					ENDIF
					REMOVE_BLIP	burglary_van_blip
					SHUT_CHAR_UP scplayer TRUE
					SET_RADAR_ZOOM 90
					STORE_SCORE player1	burglary_old_score
					burglary_entry_exit_blip_added = 1
				ENDIF
				burglary_player_in_house = 1
				
				//// CHECK IF PLAYER SPOTTED ETC /////
				 
													
			ELSE				
				IF burglary_entry_exit_blip_added = 1
				   //SET_DARKNESS_EFFECT FALSE -1
				   	SHUT_CHAR_UP scplayer FALSE
				   	IF DOES_OBJECT_EXIST burglary_object
						IF IS_CHAR_HOLDING_OBJECT scplayer burglary_object
							GET_AREA_VISIBLE burglary_current_area
							SET_OBJECT_AREA_VISIBLE	 burglary_object burglary_current_area 
						ENDIF
					ENDIF
				   
				   	

				   	//REMOVE_BLIP burglary_entry_exit_blip
				   	IF NOT IS_CAR_DEAD burglary_truck 
				   		ADD_BLIP_FOR_CAR burglary_truck burglary_van_blip
				   		SET_BLIP_AS_FRIENDLY burglary_van_blip TRUE
				   	ENDIF
				   	SET_RADAR_ZOOM 0
				   	burglary_entry_exit_blip_added = 0




					IF burglary_player_holding_object = 1
						/////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////
						burglary_new_house = 1
						IF burglary_temp_x	= burglary_entry_exit_x
							IF burglary_temp_y	= burglary_entry_exit_y 
								IF burglary_temp_z = burglary_entry_exit_z
								   burglary_new_house = 0	
								ENDIF
							ENDIF					
						ENDIF
						
						IF burglary_new_house = 1
							burglary_temp_x	= burglary_entry_exit_x
							burglary_temp_y	= burglary_entry_exit_y
							burglary_temp_z	= burglary_entry_exit_z
							INCREMENT_INT_STAT HOUSES_BURGLED 1
							
						ENDIF
						/////////////////////////////////////////////////////////
						/////////////////////////////////////////////////////////					   					   
					ENDIF

				ENDIF
				
				
				
				
				burglary_player_in_house = 0			  	
			ENDIF
		ENDIF				
		
						



		//////////////////////////////////
		/// IF PLAYER OUTSIDE OF HOUSE ///
		//////////////////////////////////
		IF burglary_player_in_truck = 0			
			IF burglary_player_in_house = 0
				
				
				IF burglary_player_holding_object = 0												
											
					GET_CHAR_COORDINATES scplayer burglary_player_x burglary_player_y burglary_player_z
					GET_CLOSEST_STEALABLE_OBJECT burglary_player_x burglary_player_y burglary_player_z 5.0 burglary_object
	                
					IF NOT burglary_object = -1																
						
						IF  IS_CHAR_HOLDING_OBJECT scplayer burglary_object //burglary_object
							IF burglary_help_displayed = 1									
								CLEAR_HELP							
								burglary_help_displayed = 0
							ENDIF
							burglary_player_holding_object = 1																	
						ELSE										
							IF LOCATE_CHAR_ON_FOOT_OBJECT_3D scplayer burglary_object 1.7 1.7 2.0 FALSE
								IF burglary_help_displayed = 0
									PRINT_HELP_FOREVER BURG23  // Get close to the item to pick it up.
									burglary_help_displayed = 1
								ENDIF														
							ELSE
								IF burglary_help_displayed = 1
									CLEAR_HELP
									burglary_help_displayed = 0
								ENDIF
							ENDIF
						ENDIF															
					ENDIF																			
				ELSE
					IF DOES_OBJECT_EXIST burglary_object
						IF NOT IS_CHAR_HOLDING_OBJECT scplayer burglary_object //burglary_object					   													
							burglary_player_holding_object = 0																	
						ENDIF 
					ELSE
						burglary_player_holding_object = 0	
					ENDIF														
				ENDIF																																																																																																													
			    																	


				
				
				IF burglary_player_spotted = 1
				OR burglary_player_discovered = 1					
					burglary_player_spotted = 0
					burglary_player_discovered = 0
					/// clear counter					
					 CLEAR_ONSCREEN_COUNTER burglary_time_to_get_out
					 burglary_time_to_get_out = 10
					 burglary_cops_called = 0
				ENDIF
														
				

				IF burglary_bar_displayed = 1					
					
					CLEAR_ONSCREEN_COUNTER burglary_player_stealth
					burglary_noise = 0.0
					burglary_player_sound_total = 0.0
		  		    burglary_player_stealth = 0
					burglary_bar_displayed = 0
				ENDIF
				
				
				IF burglary_player_holding_object = 1				   	
				   	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS burglary_truck 0.0 -6.0 0.0 burglary_car_x burglary_car_y burglary_car_z				   	
				   	IF LOCATE_CHAR_ON_FOOT_3D scplayer burglary_car_x burglary_car_y burglary_car_z 3.5 3.5 2.0 FALSE						
						IF burglary_help_displayed = 0
							PRINT_HELP_FOREVER BURG24  // Walk over to the back of the truck to drop off the item.
							burglary_help_displayed = 1
						ENDIF
						
						IF IS_BUTTON_PRESSED PAD1 TRIANGLE
							CLEAR_HELP
							burglary_items_in_truck++
							TIMERB = 0
							burglary_safety_flag = 0
							IF DOES_OBJECT_EXIST burglary_object
	       			  	
			       			  	WHILE  burglary_safety_flag = 0
			       			  		IF NOT IS_CHAR_HOLDING_OBJECT scplayer burglary_object
									OR IS_CHAR_DEAD scplayer
									OR TIMERB > 5000
										burglary_safety_flag  = 1
									ENDIF
			       			  	WAIT 0
								ENDWHILE
			       			  	DELETE_OBJECT burglary_object
								MARK_OBJECT_AS_NO_LONGER_NEEDED burglary_object
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
								burglary_player_holding_object = 0
							ENDIF
							
							
							burglary_help_displayed = 0
						ENDIF

					ELSE
						IF burglary_help_displayed = 1
							CLEAR_HELP
							burglary_help_displayed = 0
						ENDIF
					ENDIF																								 			   																
				ENDIF
			ENDIF				   		
		ENDIF
		
		
		//////////////////////////////
		/// IF PLAYER INSIDE HOUSE ///
		//////////////////////////////	
		
		
		IF burglary_player_in_house = 1
			IF burglary_player_discovered = 0
			AND burglary_player_spotted = 0
				IF burglary_bar_displayed = 0																			
					
					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT burg_brains.sc burglary_brains_active
					IF	burglary_brains_active > 0
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING burglary_player_stealth  COUNTER_DISPLAY_BAR  2 BURG25	 
					//DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING burglary_player_stealth COUNTER_DISPLAY_BAR 4 BURG25	 
				   	SHUT_CHAR_UP scplayer TRUE
				   	burglary_bar_displayed = 1
				   	ENDIF			   	  				   	  				   	  					   
				ELSE
					/////////////////////
					/// STEALTH CHECK ///
					/////////////////////								     
					GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT burg_brains.sc burglary_brains_active
					IF NOT burglary_brains_active =  0
						GET_CHAR_COORDINATES scplayer burglary_player_x burglary_player_y burglary_player_z
						GET_SOUND_LEVEL_AT_COORDS -1 burglary_player_x burglary_player_y burglary_player_z burglary_noise					

						burglary_noise *= 1.6
						
						IF  burglary_noise > 20.0
							burglary_noise -= 20.0				
							/// additional effectors to sound ///							
							burglary_player_sound_total += burglary_noise
						ENDIF
						
						burglary_player_sound_total *= 0.99									
						burglary_player_stealth =# burglary_player_sound_total
							
						IF burglary_player_stealth >= 100
							burglary_player_stealth = 100
							IF burglary_timer_started = 0										
								GET_GAME_TIMER burglary_timer_start
								burglary_timer_started = 1
							ENDIF
						ELSE		
							IF burglary_player_stealth < 0
								burglary_player_stealth = 0
							ENDIF
							burglary_timer_started = 0						
						ENDIF
						
						IF burglary_timer_started = 1
							GET_GAME_TIMER burglary_timer_current
							burglary_time_elapsed = burglary_timer_current  - burglary_timer_start 
							IF burglary_time_elapsed > 200			
								burglary_player_discovered = 1								
								burglary_times_discovered++								
							ENDIF
						ENDIF	
					
					///////////////////////////////
					// no-one alive in the house //
					///////////////////////////////
					
					ELSE
						CLEAR_ONSCREEN_COUNTER burglary_player_stealth
						burglary_noise = 0.0
						burglary_player_sound_total = 0.0
			  		    burglary_player_stealth = 0
						burglary_bar_displayed = 0
						SHUT_CHAR_UP scplayer FALSE
					ENDIF
															

				ENDIF // burglary_bar_displayed = 0
			ELSE
				IF burglary_bar_displayed = 1										
					
					CLEAR_ONSCREEN_COUNTER burglary_player_stealth
					burglary_time_to_get_out = 10
					CLEAR_PRINTS
					PRINT (BURG55) 5000 1 //~s~You have been detected!					
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING burglary_time_to_get_out  COUNTER_DISPLAY_NUMBER  2 BURG61 //COPS

					GET_GAME_TIMER burglary_timer_start
					burglary_noise = 0.0
					burglary_player_sound_total = 0.0
		  		    burglary_player_stealth = 0
					burglary_bar_displayed = 0
				ELSE
					IF burglary_cops_called = 0
						GET_GAME_TIMER burglary_timer_current
						burglary_time_elapsed = burglary_timer_current  -  burglary_timer_start						
						burglary_time_elapsed /= 1000
						burglary_time_to_get_out = 10 - burglary_time_elapsed
						
						

						IF burglary_time_to_get_out < 0
						OR burglary_time_to_get_out = 0							
							burglary_time_to_get_out = 0
							IF NOT IS_WANTED_LEVEL_GREATER player1 4
								ALTER_WANTED_LEVEL_NO_DROP Player1 3
							ENDIF
							burglary_cops_called = 1
							CLEAR_PRINTS
							PRINT BURG60 5000 1 //the cops are on the way
							CLEAR_ONSCREEN_COUNTER burglary_time_to_get_out
						ELSE
							CLEAR_THIS_PRINT BURG58
							CLEAR_THIS_PRINT BURG59
							ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS FALSE
							IF burglary_time_to_get_out > 1								
								PRINT_WITH_NUMBER (BURG58) burglary_time_to_get_out 1000   1 // ~you have ~1~ seconds to get out of the house
							ELSE
								PRINT_WITH_NUMBER (BURG59) burglary_time_to_get_out 1000   1 // ~you have ~1~ seconds to get out of the house
							ENDIF
						ENDIF
					ENDIF 
				ENDIF														
			ENDIF // burglary_player_discovered = 0					

			IF burglary_player_holding_object = 0												
												
				GET_CHAR_COORDINATES scplayer burglary_player_x burglary_player_y burglary_player_z
				GET_CLOSEST_STEALABLE_OBJECT burglary_player_x burglary_player_y burglary_player_z 5.0 burglary_object
                
				IF NOT burglary_object = -1																
					
					IF  IS_CHAR_HOLDING_OBJECT scplayer burglary_object //burglary_object
						IF burglary_help_displayed = 1
							CLEAR_PRINTS
							PRINT (BURG57) 5000 1  //~s~Take the item back to the truck, the ~y~exit~s~ is displayed on the radar.
							CLEAR_HELP							
							burglary_help_displayed = 0
						ENDIF
						burglary_player_holding_object = 1																	
					ELSE										
						IF LOCATE_CHAR_ON_FOOT_OBJECT_3D scplayer burglary_object 1.7 1.7 2.0 FALSE
							IF burglary_help_displayed = 0
								PRINT_HELP_FOREVER BURG23  // Get close to the item to pick it up.
								burglary_help_displayed = 1
							ENDIF														
						ELSE
							IF burglary_help_displayed = 1
								CLEAR_HELP
								burglary_help_displayed = 0
							ENDIF
						ENDIF
					ENDIF										
				ENDIF
				
				/////////////////////////////////////
								
			ELSE
				IF DOES_OBJECT_EXIST burglary_object
					IF NOT IS_CHAR_HOLDING_OBJECT scplayer burglary_object //burglary_object
						IF burglary_player_holding_object = 1
							CLEAR_PRINTS
							PRINT (BURG30) 5000 1 //~s~Find something worth stealing.
						ENDIF
						burglary_player_holding_object = 0																	
					ENDIF 
				ENDIF				
			ENDIF			 
		ENDIF // burglary_player_in_house = 1		
	   
	ELSE // burglary_truck has been destroyed  
	   IF  IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
			IF IS_PLAYER_CONTROL_ON	Player1
				SET_PLAYER_CONTROL Player1 OFF
			ENDIF
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
				GIVE_PLAYER_CLOTHES Player1  0 0  CLOTHES_TEX_EXTRA1   	
				BUILD_PLAYER_MODEL player1
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE				
		ENDIF
	   GOTO mission_burgl_failed
	   burgl_mission_failed = 1	
	ENDIF // is burglary cary_truck not dead
								 		
				

ELSE
	IF  IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
			GIVE_PLAYER_CLOTHES Player1  0 0  CLOTHES_TEX_EXTRA1   	
			BUILD_PLAYER_MODEL player1
			DO_FADE 1000 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE	
	ENDIF
	GOTO mission_burgl_failed
ENDIF

GOTO burgl_main_loop


/////////////////////
/////////////////////
/////////////////////

burgl_cancelled_checks:

GET_CONTROLLER_MODE controlmode

IF NOT controlmode = 3
	IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
		IF NOT IS_CAR_DEAD burglary_truck
			IF IS_CHAR_IN_CAR scplayer burglary_truck
				burgl_mission_ended = 1
			ENDIF
		ENDIF
	ENDIF
ELSE
	IF IS_BUTTON_PRESSED PAD1 SQUARE
		IF NOT IS_CAR_DEAD burglary_truck
			IF IS_CHAR_IN_CAR scplayer burglary_truck
				burgl_mission_ended = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF burgl_mission_ended = 1
	IF NOT controlmode = 3
		IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			IF NOT IS_CAR_DEAD burglary_truck
				IF  IS_CHAR_IN_CAR scplayer burglary_truck
					CLEAR_PRINTS
					PRINT_NOW BURG41 5000 1  // ~r~Burglary mission cancelled!
					burgl_mission_failed = 1
				ELSE
					burgl_mission_ended = 0
				ENDIF
				RETURN
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 SQUARE
			IF NOT IS_CAR_DEAD burglary_truck
				IF IS_CHAR_IN_CAR scplayer burglary_truck
					CLEAR_PRINTS
					PRINT_NOW BURG41 5000 1  // ~r~Burglary mission cancelled!
					burgl_mission_failed = 1
				ELSE
					burgl_mission_ended = 0
				ENDIF
				RETURN
			ENDIF
		ENDIF
	ENDIF
ENDIF
RETURN


///////////////////////////	
// Mission XXXX 1 failed///
///////////////////////////
mission_burgl_failed:

//CLEAR_ALL_VIEW_VARIABLES
PRINT_BIG B_FAIL1 5000 5


PRINT_WITH_2_NUMBERS_BIG BURG37  burglary_session_items burglary_session_made 5000 1 //6	//TOTAL: ~1~ items worth $~1~
RETURN



   

// mission XXXX 1 passed

mission_burgl_passed:

//flag_XXXX_mission1_passed = 1


	
		IF done_burglary_progress = 0
			SET_PLAYER_NEVER_GETS_TIRED player1 TRUE			
			PRINT_WITH_NUMBER_BIG BURG54 3000 5000 1
			ADD_SCORE player1 3000
			CLEAR_WANTED_LEVEL player1
			PLAY_MISSION_PASSED_TUNE 2			
			done_burglary_progress = 1
		ENDIF
		RETURN

RETURN



/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////




// mission cleanup

mission_cleanup_burgl:

disable_mod_garage = 0

IF IS_PLAYER_PLAYING player1			   
	SHUT_CHAR_UP scplayer FALSE
ENDIF


DRAW_ODDJOB_TITLE_BEFORE_FADE   TRUE
ENABLE_BURGLARY_HOUSES           FALSE
CHANGE_GARAGE_TYPE $burglary_garage[0] GARAGE_CLOSE_WHEN_EMPTY
CHANGE_GARAGE_TYPE $burglary_garage[1] GARAGE_CLOSE_WHEN_EMPTY
CHANGE_GARAGE_TYPE $burglary_garage[2] GARAGE_CLOSE_WHEN_EMPTY

//// Vehicles
MARK_CAR_AS_NO_LONGER_NEEDED burglary_truck


//// People

//// Blips
REMOVE_BLIP burglary_van_blip
REMOVE_BLIP burglary_drop_off_blip


//// Pickups

//// Counters

CLEAR_ONSCREEN_COUNTER burglary_player_stealth
CLEAR_ONSCREEN_TIMER burglary_time_to_daylight
CLEAR_ONSCREEN_TIMER burglary_time_to_lockup
CLEAR_ONSCREEN_COUNTER  burglary_session_made
CLEAR_ONSCREEN_COUNTER burglary_time_to_get_out

flag_player_on_burglary_mission = 0
flag_player_on_mission = 0
GET_GAME_TIMER timer_mobile_start


// set entry exits off here

MISSION_HAS_FINISHED
RETURN





		
}

