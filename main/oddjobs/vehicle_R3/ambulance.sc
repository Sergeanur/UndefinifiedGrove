MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *********************************** Ambulance missions ********************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_ambulance

GOSUB ambulance_failed

MISSION_END
 
{
 // Variables for mission

VAR_INT	ped_time_limit 
LVAR_INT players_ambulance ped_time_limit_temp ped_counter	number_of_injured_peds brackets_var
LVAR_INT ambulance_health_last ambulance_health_now time_drop max_peds_in_car peds_in_car score_am bonus_time_flag
LVAR_INT saved_peds hospital_blip time_chunk time_chunk_in_secs car_full_flag 
VAR_INT db_ambulance_level ///////////////// NEEDS TO BE CHANGED
LVAR_INT drop_off_time_bonus	hospital_blip_flag mission_end_button_ambulance players_ambulance_health	saved_peds_this_go
LVAR_INT injured_ped_6 injured_ped_6_blip injured_ped_6_flag
LVAR_INT injured_ped_7 injured_ped_7_blip injured_ped_7_flag
LVAR_INT injured_ped_8 injured_ped_8_blip injured_ped_8_flag
LVAR_INT injured_ped_9 injured_ped_9_blip injured_ped_9_flag
LVAR_INT injured_ped_10 injured_ped_10_blip injured_ped_10_flag
LVAR_INT injured_ped_11 injured_ped_11_blip injured_ped_11_flag
LVAR_INT injured_ped_12 injured_ped_12_blip injured_ped_12_flag
LVAR_INT	ped_var_for_gosub  ped_var_for_gosub_blip	player_failed_mission
LVAR_INT ped_var_for_gosub_flag 

LVAR_INT injured_ped_1 injured_ped_1_blip injured_ped_1_flag	
LVAR_INT injured_ped_2 injured_ped_2_blip injured_ped_2_flag
LVAR_INT injured_ped_3 injured_ped_3_blip injured_ped_3_flag
LVAR_INT injured_ped_4 injured_ped_4_blip injured_ped_4_flag
LVAR_INT injured_ped_5 injured_ped_5_blip injured_ped_5_flag


LVAR_INT dave_player_location
LVAR_INT dave_task_assigned_flag
LVAR_INT amublance_task_status

LVAR_INT dave_counter
LVAR_INT dave_cord_blip_vis[13]
LVAR_FLOAT dave_ped_coord_x[13] dave_ped_coord_y[13] dave_ped_coord_z[13]
LVAR_INT amb_time_addition[13]

LVAR_FLOAT random_x random_y	hospital_x hospital_y hospital_z
LVAR_FLOAT player1_a_x	player1_a_y player1_a_z	hospital_door_x	hospital_door_y	hospital_door_z
LVAR_FLOAT ped_coord_x ped_coord_y	ped_coord_z	amb_sound_x amb_sound_y amb_sound_z
LVAR_FLOAT difference_x_float_a difference_y_float_a	sum_difference_a_xy	
LVAR_FLOAT players_distance_from_ped peds_distance_from_hospital ped_time_limit_float random_ped_heading	time_chunk_divider
LVAR_FLOAT ambulance_min_x ambulance_min_y ambulance_min_z
LVAR_FLOAT ambulance_max_x ambulance_max_y	ambulance_max_z
LVAR_FLOAT ambulance_temp_float
LVAR_FLOAT ambulance_min_pos_x ambulance_max_pos_x ambulance_min_pos_y ambulance_max_pos_y


LVAR_FLOAT davex davey davez
LVAR_FLOAT hospital_distance_1 
LVAR_FLOAT hospital_distance_2 
LVAR_FLOAT hospital_distance_3 
LVAR_FLOAT hospital_distance_4 
LVAR_FLOAT hospital_distance_5 
LVAR_FLOAT hospital_distance_6 
LVAR_FLOAT hospital_distance_7 
LVAR_FLOAT hospital_distance_8 
LVAR_FLOAT hospital_distance_temp
LVAR_INT closest_hospital 
LVAR_INT gang_dislike[10]
LVAR_INT gang_hate[10]
LVAR_INT gangs_changed


LVAR_INT amb_decision
LVAR_INT amb_dummy_sample

LVAR_FLOAT amb_dist_multiplier 
LVAR_FLOAT amb_iterations
VAR_INT amb_free_seats

LVAR_INT amb_locate_counter	 amb_locate_counter2

// ****************************************Mission Start************************************

mission_start_ambulance:

flag_player_on_mission = 1
flag_player_on_ambulance_mission = 1
SCRIPT_NAME ambulan






IF done_ambulance_progress = 0
	REGISTER_MISSION_GIVEN
ENDIF

WAIT 0

SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
IF IS_CHAR_IN_ANY_CAR scplayer
	STORE_CAR_CHAR_IS_IN scplayer closest_hospital
	IF NOT IS_CAR_PASSENGER_SEAT_FREE closest_hospital 0
		GET_CHAR_IN_CAR_PASSENGER_SEAT closest_hospital 0 injured_ped_1
		TASK_LEAVE_CAR_IMMEDIATELY injured_ped_1 closest_hospital
		MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_1
	ENDIF
	IF NOT IS_CAR_PASSENGER_SEAT_FREE closest_hospital 1
		GET_CHAR_IN_CAR_PASSENGER_SEAT closest_hospital 1 injured_ped_1
		TASK_LEAVE_CAR_IMMEDIATELY injured_ped_1 closest_hospital
		MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_1
	ENDIF
	IF NOT IS_CAR_PASSENGER_SEAT_FREE closest_hospital 2
		GET_CHAR_IN_CAR_PASSENGER_SEAT closest_hospital 2 injured_ped_1
		TASK_LEAVE_CAR_IMMEDIATELY injured_ped_1 closest_hospital
		MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_1
	ENDIF
	
	MARK_CAR_AS_NO_LONGER_NEEDED closest_hospital
			 
ENDIF

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY amb_decision
LOAD_MISSION_TEXT AMBULAE
SHOW_UPDATE_STATS FALSE


ped_time_limit 			= 20000
players_ambulance  		= 0
ambulance_health_last  	= 0
ambulance_health_now  	= 0
time_drop  				= 0
max_peds_in_car  		= 0
peds_in_car  			= 0
number_of_injured_peds	= 1//change
ped_counter 			= 0
injured_ped_1_flag		= 0
injured_ped_2_flag		= 0
injured_ped_3_flag		= 0
injured_ped_4_flag		= 0
injured_ped_5_flag		= 0
injured_ped_6_flag		= 0
injured_ped_7_flag		= 0
injured_ped_8_flag		= 0
injured_ped_9_flag		= 0
injured_ped_10_flag		= 0
injured_ped_11_flag		= 0
injured_ped_12_flag		= 0
saved_peds				= 0
time_chunk_divider		= 10.0
db_ambulance_level		= 1  //change

time_chunk				= 0 
time_chunk_in_secs 		= 0
score_am				= 0
bonus_time_flag			= 0
drop_off_time_bonus		= 0
hospital_blip_flag		= 0
mission_end_button_ambulance = 0
car_full_flag = 0
saved_peds_this_go = 0
player_failed_mission = 0
ped_coord_x = 0.0
ped_coord_y = 0.0
ped_coord_z = 0.0

dave_counter = 0

WHILE dave_counter < 13
dave_cord_blip_vis[dave_counter] = 0
dave_ped_coord_x[dave_counter] = 0.0
dave_ped_coord_y[dave_counter] = 0.0
dave_ped_coord_z[dave_counter] = 0.0
amb_time_addition[dave_counter]	= 0

IF dave_counter < 10
	gang_dislike[dave_counter] = 0
 	gang_hate[dave_counter]	   = 0
	
ENDIF
dave_counter++
ENDWHILE

dave_counter = 0

dave_player_location = 0
dave_task_assigned_flag = 0
gangs_changed = 0
amb_free_seats = 0

amb_iterations = 0.0



/////////////////////////////// store and alter relation ship of those who hate the player to prevent being dragged out of ambulance ///

WHILE dave_counter < 10
IF IS_RELATIONSHIP_SET ACQUAINTANCE_TYPE_PED_HATE dave_counter PEDTYPE_PLAYER1
	  gang_hate[dave_counter] = 1
		  gangs_changed++
	  CLEAR_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE dave_counter PEDTYPE_PLAYER1
ELSE
	IF IS_RELATIONSHIP_SET ACQUAINTANCE_TYPE_PED_DISLIKE  dave_counter PEDTYPE_PLAYER1
	   gang_dislike[dave_counter] = 1
		  gangs_changed++
	   CLEAR_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE dave_counter PEDTYPE_PLAYER1
	ENDIF
ENDIF
dave_counter++
ENDWHILE

dave_counter = 0


PRINT_NOW ATUTOR2 3000 1 // Take the injured people to the Hospital

//WAIT 3000

SET_WANTED_MULTIPLIER 0.5

GET_CHAR_COORDINATES scplayer davex davey davez

hospital_distance_temp = 9999999.0

GET_INT_STAT CITIES_PASSED Return_cities_passed

  	IF Return_cities_passed < 2	// else whole map open

		IF Return_cities_passed = 0	 // la only
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 2004.96 -1442.96 12.56 hospital_distance_1 // (LA EAST)
			IF hospital_distance_1 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_1
				closest_hospital = 1
			ENDIF
			
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 1180.85 -1325.57 12.58 hospital_distance_2 // (LA WEST)
			IF hospital_distance_2 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_2
				closest_hospital = 2
			ENDIF		    		    
		
			// BADLANDS
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 1244.4370 331.2261 18.5547  hospital_distance_5// (Badlands north east)
			IF hospital_distance_5 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_5
				closest_hospital = 5
			ENDIF
		
		ELSE
			IF Return_cities_passed = 1 //la and sanfran
			   // LA
				GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 2004.96 -1442.96 12.56 hospital_distance_1 // (LA EAST)
				IF hospital_distance_1 < hospital_distance_temp
					hospital_distance_temp = hospital_distance_1
					closest_hospital = 1
				ENDIF
				
				GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 1180.85 -1325.57 12.58 hospital_distance_2 // (LA WEST)
				IF hospital_distance_2 < hospital_distance_temp
					hospital_distance_temp = hospital_distance_2					
					closest_hospital = 2
				ENDIF 

				
				// BADLANDS
				GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 1244.4370 331.2261 18.5547  hospital_distance_5// (Badlands north east)
				IF hospital_distance_5 < hospital_distance_temp
					hospital_distance_temp = hospital_distance_5
					closest_hospital = 5
				ENDIF
				
				GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez -2198.6931 -2290.1052 29.6250  hospital_distance_6// (Badlands south west)
				IF hospital_distance_6 < hospital_distance_temp
					hospital_distance_temp = hospital_distance_6
					closest_hospital = 6
				ENDIF
				 
				//SAN FRAN
				GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez -2670.2854 616.4364 13.4531  hospital_distance_7// (San Fran north)
				IF hospital_distance_7 < hospital_distance_temp
					hospital_distance_temp = hospital_distance_7
					closest_hospital = 7
				ENDIF
			
			ENDIF
		ENDIF
	ELSE // all areas open
		 // LA
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 2004.96 -1442.96 12.56 hospital_distance_1 // (LA EAST)
			IF hospital_distance_1 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_1
				closest_hospital = 1
			ENDIF
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 1180.85 -1325.57 12.58 hospital_distance_2 // (LA WEST)
			IF hospital_distance_2 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_2
				closest_hospital = 2
			ENDIF 

			// DESERT
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez -316.3832 1056.0450 18.7344  hospital_distance_3	// (Desert South east)
			IF hospital_distance_3 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_3
				closest_hospital = 3
			ENDIF
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez -1514.8230 2527.1189 54.7443 hospital_distance_4 // (Desert North west)
			IF hospital_distance_4 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_4
				closest_hospital = 4
			ENDIF

			// BADLANDS
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 1244.4370 331.2261 18.5547  hospital_distance_5// (Badlands north east)
			IF hospital_distance_5 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_5
				closest_hospital = 5
			ENDIF
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez -2198.6931 -2290.1052 29.6250  hospital_distance_6// (Badlands south west)
			IF hospital_distance_6 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_6
				closest_hospital = 6
			ENDIF
			 
			//SAN FRAN
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez -2670.2854 616.4364 13.4531  hospital_distance_7// (San Fran north)
			IF hospital_distance_7 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_7
				closest_hospital = 7
			ENDIF


			// VEGAS
			GET_DISTANCE_BETWEEN_COORDS_3D davex davey davez 1578.4460 1770.6816 9.8358  hospital_distance_8// (VEGAS)
			IF hospital_distance_8 < hospital_distance_temp
				hospital_distance_temp = hospital_distance_8
				closest_hospital = 8
			ENDIF				
	ENDIF
		
			
			SWITCH closest_hospital
				CASE 1 //la
					hospital_x = 2004.96 
					hospital_y = -1442.96 
					hospital_z = 12.56
					hospital_door_x	= 2026.0 
					hospital_door_y = -1423.0
					hospital_door_z = 17.0
					
					ambulance_min_pos_x = 1200.0
					ambulance_max_pos_x = 2930.0
					ambulance_min_pos_y = -2213.0
					ambulance_max_pos_y	= -1012.0
					
					
					BREAK
				CASE 2 //la					
					hospital_x = 1180.85 
					hospital_y = -1325.57 
					hospital_z = 12.58
					hospital_door_x	= 1172.0 
					hospital_door_y = -1324.0
					hospital_door_z = 15.0
					
					ambulance_min_pos_x = 450.0
					ambulance_max_pos_x = 1800.0
					ambulance_min_pos_y = -1900.0
					ambulance_max_pos_y	= -870.0

					BREAK
				CASE 3	//desert
					hospital_x = -316.3832 
					hospital_y = 1056.0450 
					hospital_z = 18.7344
					hospital_door_x	= -316.0 
					hospital_door_y = 1050.0
					hospital_door_z = 20.0
					
					ambulance_min_pos_x =  -860.0
					ambulance_max_pos_x =  185.0
					ambulance_min_pos_y =  749.0
					ambulance_max_pos_y	= 1616.0
					
					BREAK
				CASE 4	 //desert  El quadrado					
					hospital_x = -1514.0060 
					hospital_y = 2532.0127 
					hospital_z = 54.7443
					hospital_door_x	= -1515.0 
					hospital_door_y = 2520.0
					hospital_door_z = 56.0
					
				     	
					ambulance_min_pos_x = -1684.0
					ambulance_max_pos_x = -702.0
					ambulance_min_pos_y = 1465.0
					ambulance_max_pos_y	= 2749.9
					
					BREAK
				CASE 5	  //badlands					
					hospital_x = 1225.0
					hospital_y = 302.0
					hospital_z = 20.0
					hospital_door_x	= 1227.0  
					hospital_door_y = 289.5
					hospital_door_z = 22.64
					
					ambulance_min_pos_x = 100.0
					ambulance_max_pos_x = 2604.0
					ambulance_min_pos_y = -720.0
					ambulance_max_pos_y	= 465.0
					
					BREAK
				
				/////////////////////////////
				CASE 6	  //badlans					
					hospital_x = -2198.6931   
					hospital_y = -2290.1052 
					hospital_z = 29.6250
					hospital_door_x	= -2204.7283  
					hospital_door_y = -2296.3018 
					hospital_door_z = 29.6181
					
					ambulance_min_pos_x = -2598.0
					ambulance_max_pos_x =  -1500.0
					ambulance_min_pos_y = -2700.0
					ambulance_max_pos_y	= -1700.0
					
					
					BREAK
				//////////////////////////
				
				CASE 7 //sanfran
					hospital_x = -2670.2854 
					hospital_y = 616.4364 
					hospital_z = 13.4531 
					hospital_door_x	= -2677.0  
					hospital_door_y = 632.0
					hospital_door_z = 14.0
					
					
					ambulance_min_pos_x = -2820.0
					ambulance_max_pos_x = -1784.0
					ambulance_min_pos_y = 29.0
					ambulance_max_pos_y	= 1180.0
					
					BREAK
				CASE 8 // vega
					hospital_x = 1578.4460 
					hospital_y = 1770.6816
					hospital_z = 9.8358
					hospital_door_x	= 1582.0 
					hospital_door_y = 1765.0
					hospital_door_z = 11.0
					
					
					ambulance_min_pos_x = 1000.0
					ambulance_max_pos_x = 2600.0
					ambulance_min_pos_y = 772.0 
					ambulance_max_pos_y	= 2400.0
					
					BREAK
				
				DEFAULT
					hospital_x = 2004.96 
					hospital_y = -1442.96 
					hospital_z = 12.56
					hospital_door_x	= 2026.0 
					hospital_door_y = -1423.0
					hospital_door_z = 17.0
					
					ambulance_min_pos_x = 1200.0
					ambulance_max_pos_x = 2930.0
					ambulance_min_pos_y = -2213.0
					ambulance_max_pos_y	= -1012.0
					
					BREAK
			ENDSWITCH


DISPLAY_ONSCREEN_TIMER_WITH_STRING ped_time_limit TIMER_DOWN  AMBTIME //TIME LEFT
DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING amb_free_seats COUNTER_DISPLAY_NUMBER 2 AMBSEAT
DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_ambulance_level	COUNTER_DISPLAY_NUMBER 1 ALEV

IF flag_player_on_mission = 0
	ADD_BLIP_FOR_COORD hospital_x hospital_y hospital_z hospital_blip
ENDIF


mission_root:


//PRINT_WITH_NUMBER ALEVEL db_ambulance_level 5000 4 // Ambulance Mission Level ~1~

CLEAR_ONSCREEN_TIMER ped_time_limit
//CLEAR_ONSCREEN_COUNTER amb_free_seats
//CLEAR_ONSCREEN_COUNTER db_ambulance_level




//ped_time_limit = 0

//VIEW_FLOAT_VARIABLE	 amb_iterations amb_iterations
///////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////// THIS NEEDS TO BE CHANGED TO SA HOSPITALS (dave b) /////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////
/// FIND PLAYERS LOCATION ///
/////////////////////////////


 










//////////////////////////
/// HOSPITAL LOCATIONS ///
//////////////////////////

												
					
		


///////////////////////////////////////////// END OF HOSIPAL LOCATIONS ////////////////////////////////////

WHILE number_of_injured_peds > ped_counter
	GOSUB generate_random_coord
	dave_ped_coord_x[ped_counter] = ped_coord_x 
	dave_ped_coord_y[ped_counter] = ped_coord_y 
	dave_ped_coord_z[ped_counter] = ped_coord_z
	  
	IF player_failed_mission = 1
		GOTO ambulance_failed
	ENDIF
					
	GOSUB generate_timelimit // returns ped_time_limit
	amb_time_addition[ped_counter]	= ped_time_limit_temp		
	IF amb_time_addition[ped_counter] > 45000
		amb_time_addition[ped_counter] = 45000
	ELSE
		IF amb_time_addition[ped_counter] < 15000
			amb_time_addition[ped_counter] = 15000
		ENDIF
	ENDIF
	++ ped_counter
ENDWHILE


GOSUB check_if_in_area

IF number_of_injured_peds > 3
	bonus_time_flag = 1
ELSE
	bonus_time_flag = 0
ENDIF



drop_off_time_bonus		= 0

IF number_of_injured_peds < 2
	    			
	time_chunk = ped_time_limit_temp / number_of_injured_peds
	time_chunk /= 2
	brackets_var = number_of_injured_peds + 1
	time_chunk *= brackets_var
	ped_time_limit += time_chunk
	ped_time_limit = ped_time_limit / number_of_injured_peds
	
				
	time_chunk = ped_time_limit_temp
	time_chunk /= 3
	IF time_chunk > 15000
		time_chunk = 15000
	ENDIF
ELSE
	time_chunk = ped_time_limit / number_of_injured_peds
	time_chunk /= 2
	brackets_var = number_of_injured_peds + 1
	time_chunk *= brackets_var
	ped_time_limit += time_chunk
	ped_time_limit = ped_time_limit / number_of_injured_peds
	time_chunk = ped_time_limit
	time_chunk /= 3
ENDIF

IF 	ped_time_limit < 60000
	ped_time_limit = 60000
ENDIF

IF time_chunk < 25000
	time_chunk = 25000
ELSE
	IF time_chunk > 60000
		time_chunk = 60000
	ENDIF
		
ENDIF


//or 
//time_chunk = ( ped_time_limit * (number_of_injured_peds + 1) )	/ ( number_of_injured_peds * ( ( 3 * number_of_injured_peds ) - 1 ) ) 

DISPLAY_ONSCREEN_TIMER_WITH_STRING ped_time_limit TIMER_DOWN  AMBTIME //TIME LEFT
amb_locate_counter = 0
amb_locate_counter2 = 0


GOTO ambulance_loop

////////////////


check_if_in_area:
IF number_of_injured_peds > 0
    
   	   
   	    CREATE_RANDOM_CHAR dave_ped_coord_x[0] dave_ped_coord_y[0] dave_ped_coord_z[0] injured_ped_1
   	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_1 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_1	 FALSE
   	    injured_ped_1_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_1 amb_decision
		SET_CHAR_BLEEDING injured_ped_1 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_1 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_1 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_1 injured_ped_1_blip
		SET_BLIP_AS_FRIENDLY injured_ped_1_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_1_blip 2   	   
	    
	    
	    dave_cord_blip_vis[0] = 1										  
ENDIF

IF number_of_injured_peds > 1
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[1] dave_ped_coord_y[1] dave_ped_coord_z[1] injured_ped_2
   	    injured_ped_2_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_2 amb_decision
		SET_CHAR_BLEEDING injured_ped_2 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_2 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_2 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_2 injured_ped_2_blip
		SET_BLIP_AS_FRIENDLY injured_ped_2_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_2_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_2 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_2	 FALSE
	   dave_cord_blip_vis[1] = 1										  

ENDIF

IF number_of_injured_peds > 2
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[2] dave_ped_coord_y[2] dave_ped_coord_z[2] injured_ped_3
   	    injured_ped_3_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_3 amb_decision
		SET_CHAR_BLEEDING injured_ped_3 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_3 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_3 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_3 injured_ped_3_blip
		SET_BLIP_AS_FRIENDLY injured_ped_3_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_3_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_3 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_3	 FALSE
	   dave_cord_blip_vis[2] = 1										  

ENDIF

IF number_of_injured_peds > 3
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[3] dave_ped_coord_y[3] dave_ped_coord_z[3] injured_ped_4
   	    injured_ped_4_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_4 amb_decision
		SET_CHAR_BLEEDING injured_ped_4 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_4 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_4 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_4 injured_ped_4_blip
		SET_BLIP_AS_FRIENDLY injured_ped_4_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_4_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_4 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_4	 FALSE
	   dave_cord_blip_vis[3] = 1										  

ENDIF

IF number_of_injured_peds > 4
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[4] dave_ped_coord_y[4] dave_ped_coord_z[4] injured_ped_5
   	    injured_ped_5_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_5 amb_decision
		SET_CHAR_BLEEDING injured_ped_5 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_5 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_5 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_5 injured_ped_5_blip
		SET_BLIP_AS_FRIENDLY injured_ped_5_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_5_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_5 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_5	 FALSE
	   dave_cord_blip_vis[4] = 1										  

ENDIF

IF number_of_injured_peds > 5
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[5] dave_ped_coord_y[5] dave_ped_coord_z[5] injured_ped_6
   	    injured_ped_6_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_6 amb_decision
		SET_CHAR_BLEEDING injured_ped_6 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_6 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_6 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_6 injured_ped_6_blip
		SET_BLIP_AS_FRIENDLY injured_ped_6_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_6_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_6 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_6	 FALSE
	   dave_cord_blip_vis[5] = 1										  

ENDIF

IF number_of_injured_peds > 6
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[6] dave_ped_coord_y[6] dave_ped_coord_z[6] injured_ped_7 
   	    injured_ped_7_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_7 amb_decision
		SET_CHAR_BLEEDING injured_ped_7 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_7 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_7 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_7 injured_ped_7_blip
		SET_BLIP_AS_FRIENDLY injured_ped_7_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_7_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_7 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_7	 FALSE
	   dave_cord_blip_vis[6] = 1										  

ENDIF

IF number_of_injured_peds > 7
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[7] dave_ped_coord_y[7] dave_ped_coord_z[7] injured_ped_8
   	    injured_ped_8_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_8 amb_decision
		SET_CHAR_BLEEDING injured_ped_8 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_8 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_8 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_8 injured_ped_8_blip
		SET_BLIP_AS_FRIENDLY injured_ped_8_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_8_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_8 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_8	 FALSE
	   dave_cord_blip_vis[7] = 1										  

ENDIF

IF number_of_injured_peds > 8
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[8] dave_ped_coord_y[8] dave_ped_coord_z[8] injured_ped_9
   	    injured_ped_9_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_9 amb_decision
		SET_CHAR_BLEEDING injured_ped_9 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_9 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_9 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_9 injured_ped_9_blip
		SET_BLIP_AS_FRIENDLY injured_ped_9_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_9_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_9 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_9	 FALSE
	   dave_cord_blip_vis[8] = 1										  

ENDIF

IF number_of_injured_peds > 9
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[9] dave_ped_coord_y[9] dave_ped_coord_z[9] injured_ped_10
   	    injured_ped_10_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_10 amb_decision
		SET_CHAR_BLEEDING injured_ped_10 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_10 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_10 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_10 injured_ped_10_blip
		SET_BLIP_AS_FRIENDLY injured_ped_10_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_10_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_10 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_10	 FALSE
	   dave_cord_blip_vis[9] = 1										  

ENDIF

IF number_of_injured_peds > 10
   
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[10] dave_ped_coord_y[10] dave_ped_coord_z[10] injured_ped_11
   	    injured_ped_11_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_11 amb_decision
		SET_CHAR_BLEEDING injured_ped_11 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_11 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_11 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_11 injured_ped_11_blip
		SET_BLIP_AS_FRIENDLY injured_ped_11_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_11_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_11 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_11	 FALSE
	   dave_cord_blip_vis[10] = 1										  

ENDIF

IF number_of_injured_peds > 11
    
   	   CREATE_RANDOM_CHAR dave_ped_coord_x[11] dave_ped_coord_y[11] dave_ped_coord_z[11] injured_ped_12
   	    injured_ped_12_flag = 1		
		SET_CHAR_DECISION_MAKER injured_ped_12 amb_decision
		SET_CHAR_BLEEDING injured_ped_12 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_12 TRUE
		GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_ped_heading
		SET_CHAR_HEADING injured_ped_12 random_ped_heading			
		ADD_BLIP_FOR_CHAR injured_ped_12 injured_ped_12_blip
		SET_BLIP_AS_FRIENDLY injured_ped_12_blip TRUE
		CHANGE_BLIP_SCALE injured_ped_12_blip 2   	   
	    
	    FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_12 TRUE
   	    SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_12	 FALSE
	   dave_cord_blip_vis[11] = 1										  

ENDIF

RETURN


create_random_injured_ped:



	SWITCH amb_locate_counter
		CASE  0	
			IF number_of_injured_peds > 0
				IF injured_ped_1_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_1
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_1  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[0] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_1 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_1	 TRUE
								SET_CHAR_COORDINATES injured_ped_1 dave_ped_coord_x[0] dave_ped_coord_y[0] dave_ped_coord_z[0] 
								dave_cord_blip_vis[0] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[0] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_1 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_1	 FALSE
								dave_cord_blip_vis[0] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK
		
		
		CASE 1
			IF number_of_injured_peds > 1
				IF injured_ped_2_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_2
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_2  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[1] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_2 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_2	 TRUE
								SET_CHAR_COORDINATES injured_ped_2 dave_ped_coord_x[1] dave_ped_coord_y[1] dave_ped_coord_z[1] 
								dave_cord_blip_vis[1] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[1] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_2 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_2	 FALSE								
								dave_cord_blip_vis[1] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 2
			IF number_of_injured_peds > 2
				IF injured_ped_3_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_3
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_3  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[2] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_3 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_3	 TRUE
								SET_CHAR_COORDINATES injured_ped_3 dave_ped_coord_x[2] dave_ped_coord_y[2] dave_ped_coord_z[2] 
								dave_cord_blip_vis[2] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[2] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_3 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_3	 FALSE
								
								dave_cord_blip_vis[2] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 3		
			IF number_of_injured_peds > 3
				IF injured_ped_4_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_4
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_4  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[3] = 1		    	   
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_4 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_4	 TRUE
								SET_CHAR_COORDINATES injured_ped_4 dave_ped_coord_x[3] dave_ped_coord_y[3] dave_ped_coord_z[3] 
								dave_cord_blip_vis[3] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[3] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_4 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_4	 FALSE
								
								dave_cord_blip_vis[3] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 4
			IF number_of_injured_peds > 4
				IF injured_ped_5_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_5
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_5  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[4] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_5 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_5	 TRUE
								SET_CHAR_COORDINATES injured_ped_5 dave_ped_coord_x[4] dave_ped_coord_y[4] dave_ped_coord_z[4] 
								dave_cord_blip_vis[4] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[4] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_5 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_5	 FALSE
								
								dave_cord_blip_vis[4] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 5
			IF number_of_injured_peds > 5
				IF injured_ped_6_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_6
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_6  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[5] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_6 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_6	 TRUE
								SET_CHAR_COORDINATES injured_ped_6 dave_ped_coord_x[5] dave_ped_coord_y[5] dave_ped_coord_z[5] 
								dave_cord_blip_vis[5] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[5] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_6 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_6	 FALSE								
								dave_cord_blip_vis[5] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK
			
	
		CASE 6					
			IF number_of_injured_peds > 6
				IF injured_ped_7_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_7
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_7  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[6] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_7 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_7	 TRUE
								SET_CHAR_COORDINATES injured_ped_7 dave_ped_coord_x[6] dave_ped_coord_y[6] dave_ped_coord_z[6] 
								dave_cord_blip_vis[6] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[6] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_7 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_7	 FALSE
								
								dave_cord_blip_vis[6] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 7
			IF number_of_injured_peds > 7
				IF injured_ped_8_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_8
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_8  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[7] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_8 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_8	 TRUE
								SET_CHAR_COORDINATES injured_ped_8 dave_ped_coord_x[7] dave_ped_coord_y[7] dave_ped_coord_z[7] 
								dave_cord_blip_vis[7] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[7] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_8 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_8	 FALSE								
								dave_cord_blip_vis[7] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 8
			IF number_of_injured_peds > 8
				IF injured_ped_9_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_9
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_9  50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[8] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_9 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_9	 TRUE
								SET_CHAR_COORDINATES injured_ped_9 dave_ped_coord_x[8] dave_ped_coord_y[8] dave_ped_coord_z[8] 
								dave_cord_blip_vis[8] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[8] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_9 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_9	 FALSE								
								dave_cord_blip_vis[8] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 9		
			IF number_of_injured_peds > 9
				IF injured_ped_10_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_10
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_10 50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[9] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_10 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_10 TRUE
								SET_CHAR_COORDINATES injured_ped_10 dave_ped_coord_x[9] dave_ped_coord_y[9] dave_ped_coord_z[9] 
								dave_cord_blip_vis[9] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[9] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_10 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_10 FALSE								
								dave_cord_blip_vis[9] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK
		
		
		CASE 10
			IF number_of_injured_peds > 10
				IF injured_ped_11_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_11
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_11 50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[10] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_11 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_11 TRUE
								SET_CHAR_COORDINATES injured_ped_11 dave_ped_coord_x[10] dave_ped_coord_y[10] dave_ped_coord_z[10] 
								dave_cord_blip_vis[10] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[10] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_11 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_11 FALSE								
								dave_cord_blip_vis[10] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK


		CASE 11

			IF number_of_injured_peds > 11
				IF injured_ped_12_flag < 3
					IF NOT IS_CHAR_DEAD injured_ped_12
						IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer injured_ped_12 50.0  50.0 20.0 FALSE
							IF dave_cord_blip_vis[11] = 1		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_12 FALSE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_12 TRUE
								SET_CHAR_COORDINATES injured_ped_12 dave_ped_coord_x[11] dave_ped_coord_y[11] dave_ped_coord_z[11] 
								dave_cord_blip_vis[11] = 0
							ENDIF
						ELSE
						   IF dave_cord_blip_vis[11] = 0		    
						    	FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION injured_ped_12 TRUE   	
						    	SET_LOAD_COLLISION_FOR_CHAR_FLAG  injured_ped_12 FALSE								
								dave_cord_blip_vis[11] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			amb_locate_counter++
		BREAK
		
		DEFAULT 
			amb_locate_counter = 0 
		BREAK

		ENDSWITCH
			

RETURN

////////////////

generate_random_coord:

WAIT 0

GET_CHAR_COORDINATES scplayer player1_a_x player1_a_y player1_a_z

//////////////////////////////////////////////////////////////////////////////////////
////////////////////////   NEEDS TO BE UPDATED TO SA COORDS (dave b)  ////////////////
//////////////////////////////////////////////////////////////////////////////////////

//GET_CLOSEST_CHAR_NODE InX InY InZ CloseX CloseY CloseZ


ambulance_min_x = hospital_x
ambulance_max_x = hospital_x
ambulance_min_y = hospital_y
ambulance_max_y = hospital_y
ambulance_min_z = hospital_z
ambulance_max_z = hospital_z


GET_CITY_FROM_COORDS player1_a_x player1_a_y player1_a_z dave_player_location


// have ped distance from hospital increase as level increases

amb_dist_multiplier =# db_ambulance_level 
amb_dist_multiplier *= 60.0
amb_dist_multiplier += amb_iterations

IF dave_player_location = 0
	amb_iterations += 15.0
ELSE
	amb_iterations += 1.0
ENDIF

IF amb_dist_multiplier < 100.0
	amb_dist_multiplier = 100.0
ENDIF



IF 	amb_iterations > 10000.0
	IF db_ambulance_level = 1 //fist level
		//fail mission not finding co-ords
		CLEAR_THIS_PRINT ALEVEL
		PRINT_NOW A_CANC 3000 1//"~r~Ambulance mission cancelled!"
		//player_failed_mission = 1
		//RETURN
	ENDIF
ENDIF
//amb_dist_multiplier = 200.0 

ambulance_min_x	-= amb_dist_multiplier
ambulance_max_x += amb_dist_multiplier
ambulance_min_y	-= amb_dist_multiplier
ambulance_max_y += amb_dist_multiplier



IF db_ambulance_level < 8
	IF ambulance_min_x < ambulance_min_pos_x 
	   ambulance_min_x	 = ambulance_min_pos_x
	ENDIF

	IF ambulance_max_x > ambulance_max_pos_x 
	   ambulance_max_x	= ambulance_max_pos_x
	ENDIF

	IF ambulance_min_y < ambulance_min_pos_y 			
	   ambulance_min_y	 = ambulance_min_pos_y
	ENDIF

	IF random_y > ambulance_max_pos_y
	   ambulance_max_y	= ambulance_max_pos_y
	ENDIF
ELSE	 
   ambulance_min_x	 = ambulance_min_pos_x		
   ambulance_max_x	= ambulance_max_pos_x		
   ambulance_min_y	 = ambulance_min_pos_y		
   ambulance_max_y	= ambulance_max_pos_y	
ENDIF

GENERATE_RANDOM_FLOAT_IN_RANGE ambulance_min_x ambulance_max_x  random_x
GENERATE_RANDOM_FLOAT_IN_RANGE ambulance_min_y ambulance_max_y random_y 

///////////////////////   ENDOF UPDATE ////////////////////

GET_CONTROLLER_MODE controlmode

IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
	mission_end_button_ambulance = 1
ENDIF


IF mission_end_button_ambulance = 1
	IF NOT controlmode = 3
		IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			CLEAR_THIS_PRINT ALEVEL
			PRINT_NOW A_CANC 3000 1//"~r~Ambulance mission cancelled!"
			player_failed_mission = 1
			RETURN
		ENDIF
	ELSE
		GET_CONTROLLER_MODE controlmode
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD scplayer
	IF NOT IS_CHAR_IN_MODEL scplayer AMBULAN
		CLEAR_THIS_PRINT ALEVEL
		PRINT_NOW A_CANC 3000 1//"~r~Ambulance mission cancelled!"
		player_failed_mission = 1
		RETURN
	ENDIF
ENDIF

GET_CLOSEST_CHAR_NODE random_x random_y hospital_z ped_coord_x ped_coord_y ped_coord_z

IF ped_coord_x < ambulance_min_pos_x 
   GOTO generate_random_coord
ELSE
	IF ped_coord_x > ambulance_max_pos_x 
		GOTO generate_random_coord
	ELSE
		IF ped_coord_y < ambulance_min_pos_y 			
			GOTO generate_random_coord
		ELSE
			IF ped_coord_y > ambulance_max_pos_y
				GOTO generate_random_coord
			ENDIF		
		ENDIF	
	ENDIF
ENDIF





IF ped_coord_z < 6.0
	GOTO generate_random_coord
ENDIF

GET_DISTANCE_BETWEEN_COORDS_3D player1_a_x player1_a_y player1_a_z ped_coord_x ped_coord_y ped_coord_z  players_distance_from_ped

IF players_distance_from_ped < 50.0
	GOTO generate_random_coord
ENDIF


GET_DISTANCE_BETWEEN_COORDS_3D hospital_x hospital_y hospital_z ped_coord_x ped_coord_y ped_coord_z  peds_distance_from_hospital


IF peds_distance_from_hospital < 30.0
	GOTO generate_random_coord
ENDIF

//////////////////////////////////////////////////////////////////// 
/// checkthat they are not spawne din cities player cannot reach ///
////////////////////////////////////////////////////////////////////
GET_INT_STAT CITIES_PASSED Return_cities_passed
  IF Return_cities_passed < 2	// else whole map open

	IF Return_cities_passed = 0
		IF ped_coord_x  < 78.4427   //south by la
		AND  ped_coord_y < -699.5190
			WAIT 0
			GOTO generate_random_coord
		ENDIF



		IF ped_coord_x  < -252.6557 //below jutty out bit west badlands
		AND     ped_coord_y < -285.7660
			WAIT 0
			GOTO generate_random_coord
		ENDIF



		IF ped_coord_x  < -948.3447 //west of jutty out bit west badlands
			WAIT 0
			GOTO generate_random_coord
		ENDIF



		IF ped_coord_x > 1473.4481 //below vegas north east badlands
		AND ped_coord_y > 403.7353
			WAIT 0
			GOTO generate_random_coord
		ENDIF


		IF ped_coord_y > 578.6325 //north of jutty out bit middle north badlands
			WAIT 0
			GOTO generate_random_coord
		ENDIF



		IF ped_coord_x < 837.5551  //northwest from above jutty out bit middle north badlands
		AND ped_coord_y > 347.4097
			WAIT 0
			GOTO generate_random_coord
		ENDIF
	ENDIF	





	IF Return_cities_passed = 1 //sanfran open 
		IF ped_coord_x > 1473.4481 //below vegas north east badlands
		AND ped_coord_y > 403.7353
			WAIT 0
			GOTO generate_random_coord
		ENDIF



		IF ped_coord_y > 578.6325 //north of jutty out bit middle north badlands
		AND ped_coord_x > -1528.4976
			WAIT 0
			GOTO generate_random_coord
		ENDIF



		IF ped_coord_x < 837.5551  //northwest from above jutty out bit middle north badlands
		AND ped_coord_x > -1528.4976
		AND ped_coord_y > 347.4097
			WAIT 0
			GOTO generate_random_coord
		ENDIF



		IF ped_coord_y > 1380.0 //north of sanfran
			WAIT 0
			GOTO generate_random_coord
		ENDIF
	ENDIF

ENDIF


///////////////////////////////////////////////////////////////////////////////////
/// check distance between the patients, want to have more than 25 meters apart ///
///////////////////////////////////////////////////////////////////////////////////
IF number_of_injured_peds > 0//injured_ped_1_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[0] dave_ped_coord_y[0] dave_ped_coord_z[0] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 1//injured_ped_2_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[0] dave_ped_coord_y[0] dave_ped_coord_z[0] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 2//injured_ped_3_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[1] dave_ped_coord_y[1] dave_ped_coord_z[1] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 3//injured_ped_4_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[2] dave_ped_coord_y[2] dave_ped_coord_z[2] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 4//injured_ped_5_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[3] dave_ped_coord_y[3] dave_ped_coord_z[3] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 5//injured_ped_6_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[4] dave_ped_coord_y[4] dave_ped_coord_z[4] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 6//injured_ped_7_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[5] dave_ped_coord_y[5] dave_ped_coord_z[5] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 7//injured_ped_8_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[6] dave_ped_coord_y[6] dave_ped_coord_z[6] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 8//injured_ped_9_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[7] dave_ped_coord_y[7] dave_ped_coord_z[7] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 9//injured_ped_10_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[8] dave_ped_coord_y[8] dave_ped_coord_z[8] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF


IF number_of_injured_peds > 10//injured_ped_11_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[9] dave_ped_coord_y[9] dave_ped_coord_z[9] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF number_of_injured_peds > 11//injured_ped_12_flag > 0
	GET_DISTANCE_BETWEEN_COORDS_3D dave_ped_coord_x[10] dave_ped_coord_y[10] dave_ped_coord_z[10] ped_coord_x ped_coord_y ped_coord_z  hospital_distance_temp
	IF hospital_distance_temp < 25.0
		GOTO generate_random_coord
	ENDIF
ENDIF

IF IS_POINT_OBSCURED_BY_A_MISSION_ENTITY ped_coord_x ped_coord_y ped_coord_y 25.0 25.0  10.0
   GOTO generate_random_coord
ENDIF




////////////////////////////////
/// areas player can't reach ///
////////////////////////////////

////////
// LA //
////////

// horse-shoe mall
IF ped_coord_x < 1186.0 
	IF ped_coord_x > 1047.0 
		IF  ped_coord_y	< -1406.0
			IF ped_coord_y > -1567.0
				GOTO generate_random_coord
			ENDIF
		ENDIF
	ENDIF
ENDIF


// plaza type rooftop
IF ped_coord_x < 1444.0 
	IF ped_coord_x > 1339.0 
		IF  ped_coord_y	< -1450.0
			IF ped_coord_y > -1504.0
				GOTO generate_random_coord
			ENDIF
		ENDIF
	ENDIF
ENDIF

// hospital courtyard

IF ped_coord_x < 2041.9791 
	IF ped_coord_x > 1999.3875 
		IF  ped_coord_y	< -1408.7080
			IF ped_coord_y > -1445.4619
				GOTO generate_random_coord
			ENDIF
		ENDIF
	ENDIF
ENDIF



IF ped_coord_x < 1202.0
	IF ped_coord_x > 1162.0 
		IF  ped_coord_y	< -1238.0
			IF ped_coord_y > -1250.0
				GOTO generate_random_coord
			ENDIF
		ENDIF
	ENDIF
ENDIF




//////////////
// SAN FRAN	//
//////////////

IF ped_coord_x < 1176.0
	IF  ped_coord_x > 1158.0 
		IF  ped_coord_y	< 1364.0
			IF  ped_coord_y > 1345.0
			 	GOTO generate_random_coord				
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF ped_coord_x < -2307.0
	IF  ped_coord_x > -2333.0 
		IF  ped_coord_y	< 1100.0
			IF  ped_coord_y > 1036.0
			 	GOTO generate_random_coord				
			ENDIF
		ENDIF
	ENDIF
ENDIF

/////////////
/// VEGAS ///
/////////////

// boat on the strip

IF ped_coord_x < 2009.0
	IF  ped_coord_x > 1989.0 
		IF  ped_coord_y	< 1571.0
			IF  ped_coord_y > 1513.0
			 	GOTO generate_random_coord				
			ENDIF
		ENDIF
	ENDIF
ENDIF



///////////////////
/// COUNTRYSIDE ///
///////////////////
//staricase

IF ped_coord_x < 2632.8184 
	IF ped_coord_x > 2546.4634 
		IF  ped_coord_y	< -1129.6729
			IF ped_coord_y > -1144.0120
				GOTO generate_random_coord
			ENDIF
		ENDIF
	ENDIF
ENDIF

// ufo bar under mountain in country-side
 
IF ped_coord_x < -222.5044
	IF ped_coord_x > -229.0960
		IF  ped_coord_y	< 1412.0863
			IF ped_coord_y > 1393.8168
				GOTO generate_random_coord
			ENDIF
		ENDIF
	ENDIF
ENDIF


/// BOUNDARY ZONES  (FOR WHEN MAP IS CLOSED TO PREVENT PATIENTS BEING INACCESSABLE) ///




amb_iterations= 0.0

RETURN

////////////////

generate_timelimit:

IF number_of_injured_peds < 2	
    IF 	players_distance_from_ped > peds_distance_from_hospital
    	ped_time_limit_float = players_distance_from_ped / time_chunk_divider	 //2.0?
	ELSE
		ped_time_limit_float = peds_distance_from_hospital / time_chunk_divider	 //2.0?
	ENDIF
	ped_time_limit_float = ped_time_limit_float * 1000.0
	ped_time_limit_temp =# ped_time_limit_float
	ped_time_limit += ped_time_limit_temp
ELSE
	ped_time_limit_float = peds_distance_from_hospital / time_chunk_divider	 //2.0?
	ped_time_limit_float = ped_time_limit_float * 1000.0
	ped_time_limit_temp =# ped_time_limit_float
	ped_time_limit += ped_time_limit_temp
ENDIF

RETURN

///////////////

ambulance_loop:




WAIT 0



////////////////////////////// 
/// dave b debug key board ///
//////////////////////////////


//IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
  //ped_time_limit = 999999
//ENDIF

//IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
//done_ambulance_progress = 0
//saved_peds = number_of_injured_peds
//db_ambulance_level = 12
//ENDIF


//VIEW_INTEGER_VARIABLE 	  gangs_changed relationships_chngd
////////////////////////////////
///////////////////////////////
////////////////////////////////

IF ped_time_limit = 0
	PRINT_NOW A_FAIL2 3000 1//"Your too late"
	player_failed_mission = 1
	GOTO ambulance_failed
ENDIF

GET_CONTROLLER_MODE controlmode

IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
	mission_end_button_ambulance = 1
ENDIF

IF mission_end_button_ambulance = 1	
	IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
		PRINT_NOW A_CANC 3000 1//"~r~Ambulance mission cancelled!"
		GOTO ambulance_failed
	ENDIF	
ENDIF

IF NOT IS_CHAR_IN_MODEL scplayer AMBULAN
	PRINT_NOW A_CANC 3000 1//"~r~Ambulance mission cancelled!"
	GOTO ambulance_failed
ELSE
	STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer players_ambulance
	IF NOT IS_CAR_DEAD 	players_ambulance
		GET_NUMBER_OF_PASSENGERS players_ambulance peds_in_car
		GET_MAXIMUM_NUMBER_OF_PASSENGERS players_ambulance max_peds_in_car
		amb_free_seats = max_peds_in_car - peds_in_car
	//ELSE
		//GOTO ambulance_failed
	ENDIF
ENDIF







IF number_of_injured_peds > 6
AND	bonus_time_flag = 2
AND drop_off_time_bonus = 0
	bonus_time_flag = 1
	++ drop_off_time_bonus
ENDIF

IF number_of_injured_peds > 9
AND	bonus_time_flag = 2
AND drop_off_time_bonus = 1
	bonus_time_flag = 1
	++ drop_off_time_bonus
ENDIF

IF number_of_injured_peds > 12
AND	bonus_time_flag = 2
AND drop_off_time_bonus = 2
	bonus_time_flag = 1
	++ drop_off_time_bonus
ENDIF


//////////////////////////////////////////////// 
/////////// LOCATION CHECKS GO HERE ? //////////
////////////////////////////////////////////////

GOSUB create_random_injured_ped

/////////////////


IF hospital_blip_flag = 1
   LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer hospital_x hospital_y hospital_z 6.0 6.0 2.0 TRUE
ENDIF

SWITCH amb_locate_counter2
	CASE 0
		IF injured_ped_1_flag > 0
			
			ped_var_for_gosub = injured_ped_1
			ped_var_for_gosub_flag = injured_ped_1_flag
			ped_var_for_gosub_blip = injured_ped_1_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_1_flag = ped_var_for_gosub_flag

		ENDIF
		amb_locate_counter2++
	BREAK
		/////////////////
	CASE 1
		IF injured_ped_2_flag > 0
			ped_var_for_gosub = injured_ped_2
			ped_var_for_gosub_flag = injured_ped_2_flag
			ped_var_for_gosub_blip = injured_ped_2_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_2_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	 BREAK
		//////////////////
	 CASE 2
		IF injured_ped_3_flag > 0
			ped_var_for_gosub = injured_ped_3
			ped_var_for_gosub_flag = injured_ped_3_flag
			ped_var_for_gosub_blip = injured_ped_3_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_3_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	 BREAK
		////////////////
	 CASE 3
		IF injured_ped_4_flag > 0
			ped_var_for_gosub = injured_ped_4
			ped_var_for_gosub_flag = injured_ped_4_flag
			ped_var_for_gosub_blip = injured_ped_4_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_4_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	 BREAK
		////////////////
	 CASE 4
		IF injured_ped_5_flag > 0
			ped_var_for_gosub = injured_ped_5
			ped_var_for_gosub_flag = injured_ped_5_flag
			ped_var_for_gosub_blip = injured_ped_5_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_5_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++

		////////////////
	 BREAK
		
	 CASE 5	
		IF injured_ped_6_flag > 0
			ped_var_for_gosub = injured_ped_6
			ped_var_for_gosub_flag = injured_ped_6_flag
			ped_var_for_gosub_blip = injured_ped_6_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_6_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	BREAK
		////////////////
	CASE 6
		IF injured_ped_7_flag > 0
			ped_var_for_gosub = injured_ped_7
			ped_var_for_gosub_flag = injured_ped_7_flag
			ped_var_for_gosub_blip = injured_ped_7_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_7_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	BREAK
		////////////////
	CASE 7
		IF injured_ped_8_flag > 0
			ped_var_for_gosub = injured_ped_8
			ped_var_for_gosub_flag = injured_ped_8_flag
			ped_var_for_gosub_blip = injured_ped_8_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_8_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	BREAK
		////////////////
	CASE 8
		IF injured_ped_9_flag > 0
			ped_var_for_gosub = injured_ped_9
			ped_var_for_gosub_flag = injured_ped_9_flag
			ped_var_for_gosub_blip = injured_ped_9_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_9_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	BREAK
		////////////////
	CASE 9
		IF injured_ped_10_flag > 0
			ped_var_for_gosub = injured_ped_10
			ped_var_for_gosub_flag = injured_ped_10_flag
			ped_var_for_gosub_blip = injured_ped_10_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_10_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	BREAK
		////////////////
	CASE 10
		IF injured_ped_11_flag > 0
			ped_var_for_gosub = injured_ped_11
			ped_var_for_gosub_flag = injured_ped_11_flag
			ped_var_for_gosub_blip = injured_ped_11_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_11_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	BREAK
		////////////////
	CASE 11
		IF injured_ped_12_flag > 0
			ped_var_for_gosub = injured_ped_12
			ped_var_for_gosub_flag = injured_ped_12_flag
			ped_var_for_gosub_blip = injured_ped_12_blip
			GOSUB injured_ped_checks
			IF player_failed_mission = 1
				GOTO ambulance_failed
			ENDIF
			injured_ped_12_flag = ped_var_for_gosub_flag
		ENDIF
		amb_locate_counter2++
	BREAK

	DEFAULT
		amb_locate_counter2 = 0
	BREAK

ENDSWITCH


////////////////


IF saved_peds = number_of_injured_peds
	score_am = db_ambulance_level * db_ambulance_level
	score_am *= 50
	FORCE_BIG_MESSAGE_AND_COUNTER	TRUE	
	PRINT_WITH_NUMBER_BIG REWARD score_am 6000 6
	total_saved_peds += saved_peds
	saved_peds_this_go += saved_peds
	++ number_of_injured_peds
	saved_peds = 0
	ped_counter = 0
	ADD_SCORE player1 score_am
	hospital_blip_flag = 0
	REMOVE_BLIP hospital_blip
	REMOVE_BLIP injured_ped_1_blip
	REMOVE_BLIP injured_ped_2_blip
	REMOVE_BLIP injured_ped_3_blip
	REMOVE_BLIP injured_ped_4_blip
	REMOVE_BLIP injured_ped_5_blip
	REMOVE_BLIP injured_ped_6_blip
	REMOVE_BLIP injured_ped_7_blip
	REMOVE_BLIP injured_ped_8_blip
	REMOVE_BLIP injured_ped_9_blip
	REMOVE_BLIP injured_ped_10_blip
	REMOVE_BLIP injured_ped_11_blip
	REMOVE_BLIP injured_ped_12_blip
	
	//REGISTER_AMBULANCE_LEVEL db_ambulance_level // old call
	REGISTER_INT_STAT AMBULANCE_LEVEL db_ambulance_level	 // new call for SA (dave b)
	++ db_ambulance_level
	IF db_ambulance_level = 13
		FORCE_BIG_MESSAGE_AND_COUNTER	TRUE
		PRINT_WITH_NUMBER_BIG A_COMP1 5000 5000 5 //"Ambulance missions complete: $ ~1~"
		ADD_SCORE player1 5000
		PLAY_MISSION_PASSED_TUNE 2
		IF done_ambulance_progress = 0			
			//CLEAR_HELP
			//PRINT_HELP A_COMP2 //"You will never get tired!"
			PLAYER_MADE_PROGRESS 1												
			PRINT  A_COMP3 5000 1 //"you will never get tired
			//SET_PLAYER_NEVER_GETS_TIRED player1 TRUE
			//INCREMENT_FLOAT_STAT MAX_HEALTH	150.0
			GET_FLOAT_STAT MAX_HEALTH ambulance_temp_float
			ambulance_temp_float *= 1.5
			INCREMENT_FLOAT_STAT_NO_MESSAGE MAX_HEALTH	ambulance_temp_float

			REGISTER_ODDJOB_MISSION_PASSED
			done_ambulance_progress = 1
		ENDIF
		RETURN
	ENDIF
	GOTO mission_root
ENDIF

GOTO ambulance_loop


ambulance_failed:

CLEAR_ONSCREEN_TIMER ped_time_limit
CLEAR_ONSCREEN_COUNTER db_ambulance_level
CLEAR_ONSCREEN_COUNTER amb_free_seats
CLEAR_THIS_PRINT ALEVEL
IF NOT db_ambulance_level = 13	
	PRINT_BIG A_FAIL1 5000 5
	PRINT_WITH_NUMBER_BIG A_SAVES saved_peds_this_go 6000 6//PEOPLE SAVED: ~1~
ENDIF



////////////////////////////
//// head popping stuff ////
////////////////////////////

IF NOT db_ambulance_level = 13
	IF NOT IS_CHAR_DEAD injured_ped_1 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_1 FALSE
		EXPLODE_CHAR_HEAD injured_ped_1
		REMOVE_CHAR_ELEGANTLY injured_ped_1
	ENDIF

		  
	IF NOT IS_CHAR_DEAD injured_ped_2 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_2 FALSE
		EXPLODE_CHAR_HEAD injured_ped_2
		REMOVE_CHAR_ELEGANTLY injured_ped_2
	ENDIF

	IF NOT IS_CHAR_DEAD injured_ped_3 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_3 FALSE
		EXPLODE_CHAR_HEAD injured_ped_3
		REMOVE_CHAR_ELEGANTLY injured_ped_3
	ENDIF

	IF NOT IS_CHAR_DEAD injured_ped_4 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_4 FALSE
		EXPLODE_CHAR_HEAD injured_ped_4
		REMOVE_CHAR_ELEGANTLY injured_ped_4
	ENDIF

	IF NOT IS_CHAR_DEAD injured_ped_5 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_5 FALSE
		EXPLODE_CHAR_HEAD injured_ped_5
		REMOVE_CHAR_ELEGANTLY injured_ped_5
	ENDIF 

	IF NOT IS_CHAR_DEAD injured_ped_6 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_6 FALSE
		EXPLODE_CHAR_HEAD injured_ped_6
		REMOVE_CHAR_ELEGANTLY injured_ped_6
	ENDIF


	IF NOT IS_CHAR_DEAD injured_ped_7 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_7 FALSE
		EXPLODE_CHAR_HEAD injured_ped_7
		REMOVE_CHAR_ELEGANTLY injured_ped_7
	ENDIF

		
	IF NOT IS_CHAR_DEAD injured_ped_8 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_8 FALSE
		EXPLODE_CHAR_HEAD injured_ped_8
		REMOVE_CHAR_ELEGANTLY injured_ped_8
	ENDIF

	IF NOT IS_CHAR_DEAD injured_ped_9 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_9 FALSE
		EXPLODE_CHAR_HEAD injured_ped_9	   
		REMOVE_CHAR_ELEGANTLY injured_ped_9
	ENDIF

	IF NOT IS_CHAR_DEAD injured_ped_10 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_10 FALSE
		EXPLODE_CHAR_HEAD injured_ped_10
		REMOVE_CHAR_ELEGANTLY injured_ped_10
	ENDIF

	IF NOT IS_CHAR_DEAD injured_ped_11 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_11 FALSE
		EXPLODE_CHAR_HEAD injured_ped_11
		REMOVE_CHAR_ELEGANTLY injured_ped_11
	ENDIF

	IF NOT IS_CHAR_DEAD injured_ped_12 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER	injured_ped_12 FALSE
		EXPLODE_CHAR_HEAD injured_ped_12
		REMOVE_CHAR_ELEGANTLY injured_ped_12
	ENDIF
ENDIF




MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_1
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_2
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_3
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_4
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_5
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_6
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_7
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_8
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_9
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_10
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_11
MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_12



hospital_blip_flag = 0
REMOVE_BLIP hospital_blip
REMOVE_BLIP injured_ped_1_blip
REMOVE_BLIP injured_ped_2_blip
REMOVE_BLIP injured_ped_3_blip
REMOVE_BLIP injured_ped_4_blip
REMOVE_BLIP injured_ped_5_blip
REMOVE_BLIP injured_ped_6_blip
REMOVE_BLIP injured_ped_7_blip
REMOVE_BLIP injured_ped_8_blip
REMOVE_BLIP injured_ped_9_blip
REMOVE_BLIP injured_ped_10_blip
REMOVE_BLIP injured_ped_11_blip
REMOVE_BLIP injured_ped_12_blip

REMOVE_DECISION_MAKER amb_decision

SET_WANTED_MULTIPLIER 1.0

GET_GAME_TIMER timer_mobile_start



///////////////////////// restore relationships that were stored earlier //////////


dave_counter = 0
WHILE dave_counter < 10

   IF gang_hate[dave_counter] = 1
	  SET_RELATIONSHIP  ACQUAINTANCE_TYPE_PED_HATE dave_counter PEDTYPE_PLAYER1
	  	  gangs_changed--
	  gang_hate[dave_counter] = 0 
   ENDIF

   IF gang_dislike[dave_counter] = 1
	  SET_RELATIONSHIP  ACQUAINTANCE_TYPE_PED_DISLIKE dave_counter PEDTYPE_PLAYER1
	  		  gangs_changed--
	  gang_dislike[dave_counter] = 0
   ENDIF

dave_counter++
ENDWHILE


flag_player_on_mission = 0
flag_player_on_ambulance_mission = 0
MISSION_HAS_FINISHED
IF done_ambulance_progress = 1
	//CLEAR_HELP
	//PRINT_HELP A_COMP3 //"You will never get tired!"
	done_ambulance_progress = 2
	SHOW_UPDATE_STATS FALSE
    START_NEW_SCRIPT switch_update_stats_back_on
ENDIF
RETURN


//////////////////////////////////////////////////////////////////////
injured_ped_checks:
	IF IS_CHAR_DEAD ped_var_for_gosub
		PRINT_NOW A_FAIL3 3000 1//The patient is dead
		player_failed_mission = 1
		RETURN
	ENDIF
		
	IF ped_time_limit = 0
		EXPLODE_CHAR_HEAD ped_var_for_gosub
		REMOVE_CHAR_ELEGANTLY ped_var_for_gosub
		PRINT_NOW A_FAIL2 3000 1//"Your too late"
		player_failed_mission = 1
		RETURN
	ENDIF

	IF ped_var_for_gosub_flag = 3
		GET_CHAR_COORDINATES ped_var_for_gosub amb_sound_x amb_sound_y amb_sound_z
		GET_CAR_HEALTH players_ambulance ambulance_health_now
		IF ambulance_health_now < ambulance_health_last
			time_drop = ambulance_health_last - ambulance_health_now
			time_drop = time_drop * 50
			 
			ped_time_limit_temp = ped_time_limit - time_drop
			IF ped_time_limit_temp > 10
				ped_time_limit = ped_time_limit - time_drop
			ENDIF

			
			SET_CHAR_SAY_CONTEXT ped_var_for_gosub	CONTEXT_GLOBAL_PAIN_HIGH	amb_dummy_sample 

			IF ped_time_limit < 0
				ped_time_limit = 0
			ENDIF		
			ambulance_health_last = ambulance_health_now
		ENDIF
	ENDIF

	IF ped_var_for_gosub_flag = 1
		IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer ped_var_for_gosub 10.0 10.0 2.0 0
			
			GET_NUMBER_OF_PASSENGERS players_ambulance peds_in_car
			GET_MAXIMUM_NUMBER_OF_PASSENGERS players_ambulance max_peds_in_car

			IF peds_in_car = max_peds_in_car
				IF car_full_flag = 0					
					PRINT_NOW A_FULL 5000 1 //"Ambulance full!!!"					
				ENDIF
				car_full_flag = 1
			ELSE
				car_full_flag = 0
			ENDIF

			GET_CAR_HEALTH players_ambulance ambulance_health_last
			IF car_full_flag = 0
			  IF dave_task_assigned_flag = 0
				TASK_ENTER_CAR_AS_PASSENGER ped_var_for_gosub players_ambulance	-2 -1
			  	dave_task_assigned_flag = 1
			  ELSE
				 GET_SCRIPT_TASK_STATUS ped_var_for_gosub TASK_ENTER_CAR_AS_PASSENGER amublance_task_status
				 IF amublance_task_status = FINISHED_TASK
				 	TASK_ENTER_CAR_AS_PASSENGER ped_var_for_gosub players_ambulance	-2 -1
				 ENDIF					
			  ENDIF	
			ELSE				
				PRINT_NOW A_FULL 5000 1 //"Ambulance full!!!"
			ENDIF
			ped_var_for_gosub_flag = 2
		ENDIF
	ENDIF

	IF ped_var_for_gosub_flag = 2
		IF NOT LOCATE_CHAR_IN_CAR_CHAR_2D scplayer ped_var_for_gosub 20.0 20.0 0
			ped_var_for_gosub_flag = 1
		ENDIF		
		IF NOT IS_CHAR_SITTING_IN_ANY_CAR ped_var_for_gosub
			ped_var_for_gosub_flag = 1
		ENDIF
		
	ENDIF	

	IF ped_var_for_gosub_flag = 2
		IF IS_CHAR_SITTING_IN_ANY_CAR ped_var_for_gosub 
			dave_task_assigned_flag = 0
			REMOVE_BLIP ped_var_for_gosub_blip
			IF hospital_blip_flag = 0
				ADD_BLIP_FOR_COORD hospital_x hospital_y hospital_z hospital_blip
				hospital_blip_flag = 1
			ENDIF
			
			
			//time_chunk_in_secs = time_chunk / 1000			
			 
			time_chunk_in_secs = amb_time_addition[amb_locate_counter2] / 1000

			
			PRINT_WITH_NUMBER_BIG A_TIME time_chunk_in_secs 6000 6	//+~1~ Seconds
			//ped_time_limit += time_chunk
			ped_time_limit += amb_time_addition[amb_locate_counter2]
			ped_var_for_gosub_flag = 3
		ENDIF
	ENDIF

	IF ped_var_for_gosub_flag = 3
		IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer hospital_x hospital_y hospital_z 6.0 6.0 2.0 FALSE
			TASK_GO_STRAIGHT_TO_COORD  ped_var_for_gosub hospital_door_x hospital_door_y hospital_door_z PEDMOVE_RUN -1
			ped_var_for_gosub_flag = 4
		ENDIF	
	ENDIF

	IF ped_var_for_gosub_flag = 4		
		IF NOT IS_CHAR_SITTING_IN_ANY_CAR ped_var_for_gosub	
			
			TASK_GO_STRAIGHT_TO_COORD  ped_var_for_gosub hospital_door_x hospital_door_y hospital_door_z PEDMOVE_RUN -1
			
			FORCE_BIG_MESSAGE_AND_COUNTER	TRUE
			PRINT_BIG A_PASS 3000 5
			IF bonus_time_flag = 1
				time_chunk_in_secs = time_chunk / 1000
				PRINT_WITH_NUMBER_BIG A_TIME time_chunk_in_secs 6000 6	//+~1~ Seconds
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer players_ambulance
				GET_CAR_HEALTH players_ambulance players_ambulance_health
				players_ambulance_health += 110
				SET_CAR_HEALTH players_ambulance players_ambulance_health
				ped_time_limit += time_chunk
				++ bonus_time_flag
			ENDIF
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
			
			++ saved_peds
			INCREMENT_INT_STAT LIVES_SAVED 1
			ped_var_for_gosub_flag = 0
			SET_CHAR_BLEEDING ped_var_for_gosub FALSE
			//REMOVE_CHAR_ELEGANTLY ped_var_for_gosub
			SET_CHAR_KEEP_TASK ped_var_for_gosub TRUE
			MARK_CHAR_AS_NO_LONGER_NEEDED ped_var_for_gosub
		ENDIF
	ENDIF
RETURN







}