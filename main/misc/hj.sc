MISSION_START

// *****************************************************************************************
// ****************************************Stunt Jump*************************************** 
// *****************************************************************************************

// Variables for mission

VAR_INT	cash
VAR_INT	car_player_is_in_hj
VAR_INT flag_takeoff_hj
VAR_INT height_int_hj
VAR_INT flag_wheels_hj
VAR_INT stunt_flags_hj
VAR_INT flag_car_upsidedown_hj
VAR_INT counter_stunt_rolls_hj
VAR_INT	height_decimals_int_hj
VAR_INT	distance_decimals_int_hj
VAR_INT jumpdistance_int_hj
VAR_INT	total_rotation_int
VAR_INT collision_counter
VAR_INT cash_reward cash_reward_temp
VAR_FLOAT height_float_hj
VAR_FLOAT x_float_hj
VAR_FLOAT y_float_hj
VAR_FLOAT z_float_hj
VAR_FLOAT takeoff_x_float_hj
VAR_FLOAT takeoff_y_float_hj
VAR_FLOAT takeoff_z_float_hj
VAR_FLOAT jumpend_x_float_hj
VAR_FLOAT jumpend_y_float_hj
VAR_FLOAT jumpdistance_float_hj
VAR_FLOAT heading_hj
VAR_FLOAT old_heading_hj
VAR_FLOAT heading_difference
VAR_FLOAT heading_difference_temp
VAR_FLOAT total_rotation

VAR_INT CarTwoWheelsTime CarTwoWheelsDistanceMeterInt CarTwoWheelsDistanceDecimalInt CarTwoWheelsDistanceFeetInt
VAR_FLOAT CarTwoWheelsDistance CarTwoWheelsDistanceMeter CarTwoWheelsDistanceFeet

VAR_INT BikeWheelieTime BikeWheelieDistanceMeterInt BikeWheelieDistanceDecimalInt BikeWheelieDistanceFeetInt
VAR_FLOAT BikeWheelieDistance BikeWheelieDistanceMeter BikeWheelieDistanceFeet

VAR_INT BikeStoppieTime BikeStoppieDistanceMeterInt BikeStoppieDistanceDecimalInt BikeStoppieDistanceFeetInt
VAR_FLOAT BikeStoppieDistance BikeStoppieDistanceMeter BikeStoppieDistanceFeet

var_int passenger_said_jump

// ****************************************Mission Start************************************

SET_DEATHARREST_STATE OFF
SCRIPT_NAME hj

mission_start_hj:

WAIT 0

IF NOT IS_PLAYER_PLAYING player1
	GOTO mission_start_hj
ENDIF

IF ARE_ANY_CAR_CHEATS_ACTIVATED
	GOTO mission_start_hj
ENDIF

IF IS_CHAR_IN_ANY_TRAIN scplayer
	GOTO mission_start_hj
ENDIF

IF IS_CHAR_IN_FLYING_VEHICLE scplayer
	GOTO mission_start_hj
ENDIF

IF IS_CHAR_IN_ANY_BOAT scplayer
	GOTO mission_start_hj
ENDIF

IF IS_CHAR_IN_model scplayer vortex
	GOTO mission_start_hj
ENDIF

if not IS_PLAYER_CONTROL_ON player1
	GOTO mission_start_hj
endif

IF IS_CHAR_IN_ANY_CAR scplayer

	STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car_player_is_in_hj
	GET_WHEELIE_STATS player1 CarTwoWheelsTime CarTwoWheelsDistance BikeWheelieTime BikeWheelieDistance BikeStoppieTime BikeStoppieDistance

	IF CarTwoWheelsDistance > 0.0
		CarTwoWheelsTime /= 1000
		CarTwoWheelsDistanceMeterInt =# CarTwoWheelsDistance
		CarTwoWheelsDistanceMeter =# CarTwoWheelsDistanceMeterInt
		VAR_FLOAT temp_float_hj
		temp_float_hj = CarTwoWheelsDistance -	CarTwoWheelsDistanceMeter
		temp_float_hj *= 100.0
		CarTwoWheelsDistanceDecimalInt =# temp_float_hj
		cash = CarTwoWheelsTime + CarTwoWheelsDistanceMeterInt
		cash /= 2
		ADD_SCORE player1 cash
		IF ARE_MEASUREMENTS_IN_METRES
			PRINT_WITH_4_NUMBERS WHEEL01 cash CarTwoWheelsDistanceMeterInt CarTwoWheelsDistanceDecimalInt CarTwoWheelsTime 3000 1//TWO WHEELS DOUBLE BONUS: $ ~1~   Distance: ~1~.~1~m   Time: ~1~ seconds
		ELSE
			CONVERT_METRES_TO_FEET CarTwoWheelsDistance CarTwoWheelsDistanceFeet
			CarTwoWheelsDistanceFeetInt =# CarTwoWheelsDistanceFeet
			PRINT_WITH_3_NUMBERS WHEEL02 cash CarTwoWheelsDistanceFeetInt CarTwoWheelsTime 3000 1//TWO WHEELS DOUBLE BONUS: $ ~1~   Distance: ~1~ feet   Time: ~1~ seconds
		ENDIF
	ENDIF
	
	IF BikeWheelieDistance > 0.0
		BikeWheelieTime	/= 1000
		BikeWheelieDistanceMeterInt =# BikeWheelieDistance
		BikeWheelieDistanceMeter =# BikeWheelieDistanceMeterInt
		temp_float_hj = BikeWheelieDistance -	BikeWheelieDistanceMeter
		temp_float_hj *= 100.0
		BikeWheelieDistanceDecimalInt =# temp_float_hj
		//cash = BikeWheelieTime + BikeWheelieDistanceMeterInt
		cash = BikeWheelieDistanceMeterInt
		cash *= 2
		cash /= 5
		cash /= 2
		ADD_SCORE player1 cash
		IF ARE_MEASUREMENTS_IN_METRES
			PRINT_WITH_4_NUMBERS WHEEL06 cash BikeWheelieDistanceMeterInt BikeWheelieDistanceDecimalInt BikeWheelieTime 3000 1//WHEELIE DOUBLE BONUS: $ ~1~   Distance: ~1~.~1~m   Time: ~1~ seconds
		ELSE
			CONVERT_METRES_TO_FEET BikeWheelieDistance BikeWheelieDistanceFeet
			BikeWheelieDistanceFeetInt =# BikeWheelieDistanceFeet
			PRINT_WITH_3_NUMBERS WHEEL07 cash BikeWheelieDistanceFeetInt BikeWheelieTime 3000 1//WHEELIE DOUBLE BONUS: $ ~1~   Distance: ~1~ feet   Time: ~1~ seconds
		ENDIF
	ENDIF

	IF BikeStoppieDistance > 0.0
		BikeStoppieTime /= 1000
		BikeStoppieDistanceMeterInt =# BikeStoppieDistance
		BikeStoppieDistanceMeter =# BikeStoppieDistanceMeterInt
		temp_float_hj = BikeStoppieDistance -	BikeStoppieDistanceMeter
		temp_float_hj *= 100.0
		BikeStoppieDistanceDecimalInt =# temp_float_hj
		//cash = BikeStoppieTime + BikeStoppieDistanceMeterInt
		cash = BikeStoppieDistanceMeterInt
		cash /= 2
		ADD_SCORE player1 cash
		IF ARE_MEASUREMENTS_IN_METRES
			PRINT_WITH_4_NUMBERS WHEEL11 cash BikeStoppieDistanceMeterInt BikeStoppieDistanceDecimalInt BikeStoppieTime 3000 1//STOPPIE DOUBLE BONUS: $ ~1~   Distance: ~1~.~1~m   Time: ~1~ seconds
		ELSE
			CONVERT_METRES_TO_FEET BikeStoppieDistance BikeStoppieDistanceFeet
			BikeStoppieDistanceFeetInt =# BikeStoppieDistanceFeet
			PRINT_WITH_3_NUMBERS WHEEL12 cash BikeStoppieDistanceFeetInt BikeStoppieTime 3000 1//STOPPIE DOUBLE BONUS: $ ~1~   Distance: ~1~ feet   Time: ~1~ seconds
		ENDIF
	ENDIF

	IF IS_CAR_IN_AIR_PROPER car_player_is_in_hj
		
		total_rotation_int			= 0
		heading_hj					= 0.0
		flag_wheels_hj				= 0
		counter_stunt_rolls_hj 		= 0
		flag_car_upsidedown_hj 		= 0
		stunt_flags_hj		  		= 0
		flag_takeoff_hj	       		= 0
		height_int_hj          		= 0
		height_float_hj		   		= -100.0
		x_float_hj		       		= 0.0
		y_float_hj			   		= 0.0
		z_float_hj			   		= 0.0
		takeoff_x_float_hj	   		= 0.0
		takeoff_y_float_hj	   		= 0.0
		takeoff_z_float_hj	   		= 0.0
		jumpend_x_float_hj	   		= 0.0
		jumpend_y_float_hj	   		= 0.0
		jumpdistance_float_hj  		= 0.0
		jumpdistance_int_hj	  		= 0
		distance_decimals_int_hj	= 0
		height_decimals_int_hj		= 0
		temp_float_hj				= 0.0
		heading_difference			= 0.0
		total_rotation				= 0.0
		heading_difference_temp		= 0.0
		old_heading_hj				= 0.0
		collision_counter			= 0
		passenger_said_jump 		= 0

    	WHILE IS_CAR_IN_AIR_PROPER car_player_is_in_hj
		OR collision_counter < 10

			++ collision_counter

    		GET_CAR_COORDINATES car_player_is_in_hj x_float_hj y_float_hj z_float_hj
    	
			old_heading_hj = heading_hj
			
    		IF flag_takeoff_hj = 0
    			GET_CAR_HEADING car_player_is_in_hj old_heading_hj
    			takeoff_x_float_hj = x_float_hj
    			takeoff_y_float_hj = y_float_hj
    			takeoff_z_float_hj = z_float_hj
    			flag_takeoff_hj = 1
    		ENDIF

    		WAIT 0

			IF IS_CAR_DEAD car_player_is_in_hj
				GOTO mission_start_hj
			ENDIF

    		IF NOT IS_PLAYER_PLAYING player1
    			GOTO mission_start_hj
    		ENDIF 			
    		
    		IF NOT IS_CHAR_IN_ANY_CAR scplayer
    			GOTO mission_start_hj
    		ENDIF

    		IF NOT IS_CAR_UPRIGHT car_player_is_in_hj
    		AND flag_car_upsidedown_hj = 0
    			flag_car_upsidedown_hj = 1
    		ENDIF

    		IF IS_CAR_UPRIGHT car_player_is_in_hj
    		AND flag_car_upsidedown_hj = 1
    			++ counter_stunt_rolls_hj
    			flag_car_upsidedown_hj = 0
    		ENDIF

    		GET_CAR_HEADING car_player_is_in_hj heading_hj
			heading_difference = heading_hj - old_heading_hj

			IF heading_difference > 180.0
				heading_difference_temp = heading_difference     
				heading_difference = 360.0 - heading_difference_temp
			ELSE
				IF heading_difference < -180.0
					heading_difference_temp = heading_difference     
					heading_difference = 360.0 + heading_difference_temp
				ENDIF
			ENDIF

			IF heading_difference < 0.0
				heading_difference_temp = heading_difference
				heading_difference = 0.0 - heading_difference_temp
			ENDIF

			total_rotation = total_rotation + heading_difference

			total_rotation_int =# total_rotation 
			
    		IF z_float_hj > height_float_hj
    			height_float_hj = z_float_hj	 
    		ENDIF
    	
    		z_float_hj = 0.0

			if passenger_said_jump = 0
				GET_CAR_COORDINATES car_player_is_in_hj jumpend_x_float_hj jumpend_y_float_hj temp_float_hj
				get_distance_between_coords_2d takeoff_x_float_hj takeoff_y_float_hj jumpend_x_float_hj jumpend_y_float_hj temp_float_hj
				if temp_float_hj > 20.0
					RANDOM_PASSENGER_SAY car_player_is_in_hj CONTEXT_GLOBAL_CAR_JUMP
					passenger_said_jump = 1
				endif
			endif
    			 
    	ENDWHILE

	ELSE
		GOTO mission_start_hj
	ENDIF
ELSE
	GOTO mission_start_hj
ENDIF 

IF flag_takeoff_hj = 1
	GET_CAR_COORDINATES car_player_is_in_hj jumpend_x_float_hj jumpend_y_float_hj temp_float_hj
	get_distance_between_coords_2d takeoff_x_float_hj takeoff_y_float_hj jumpend_x_float_hj jumpend_y_float_hj jumpdistance_float_hj
	REGISTER_FLOAT_STAT MAX_JUMP_DISTANCE	 jumpdistance_float_hj
	jumpdistance_int_hj =# jumpdistance_float_hj
	height_float_hj = height_float_hj - takeoff_z_float_hj
	REGISTER_FLOAT_STAT MAX_JUMP_HEIGHT height_float_hj
	height_int_hj =# height_float_hj
	temp_float_hj =# jumpdistance_int_hj
	jumpdistance_float_hj = jumpdistance_float_hj - temp_float_hj
	temp_float_hj = jumpdistance_float_hj * 100.0
	distance_decimals_int_hj =# temp_float_hj
	temp_float_hj =# height_int_hj
	height_float_hj = height_float_hj - temp_float_hj
	temp_float_hj = height_float_hj * 100.0
	height_decimals_int_hj =# temp_float_hj
	REGISTER_inT_STAT MAX_JUMP_FLIPS counter_stunt_rolls_hj
	REGISTER_inT_STAT MAX_JUMP_SPINS total_rotation_int
ENDIF

IF height_float_hj > 4.0	   	//4 METERS HIGH
	++ stunt_flags_hj
ENDIF

IF jumpdistance_int_hj > 40    	//40 METERS LONG
	++ stunt_flags_hj
ENDIF

IF counter_stunt_rolls_hj > 1  	//2 ROLLS/FLIPS IN MID AIR
	++ stunt_flags_hj
ENDIF

IF total_rotation_int > 360    	//360 SPIN IN MID AIR
	++ stunt_flags_hj
ENDIF

IF stunt_flags_hj > 0
	cash_reward = counter_stunt_rolls_hj * 180
	cash_reward += total_rotation_int
	cash_reward_temp = jumpdistance_int_hj * 6
	cash_reward += cash_reward_temp
	cash_reward_temp = height_int_hj * 45
	cash_reward += cash_reward_temp
	IF flag_wheels_hj = 1
		cash_reward *= 2
	ENDIF
	cash_reward *= stunt_flags_hj
	cash_reward /= 3
	cash_reward /= 5
	ADD_SCORE player1 cash_reward

    IF stunt_flags_hj = 1
    	PRINT_WITH_NUMBER HJ_IS cash_reward 2000 1 //"INSANE STUNT BONUS"
		REGISTER_inT_STAT BEST_STUNT 1
    ENDIF

    IF stunt_flags_hj = 2
    	PRINT_WITH_NUMBER HJ_DIS cash_reward 2000 1 //"DOUBLE INSANE STUNT BONUS"
		REGISTER_inT_STAT BEST_STUNT 3
    ENDIF

    IF stunt_flags_hj = 3
    	PRINT_WITH_NUMBER HJ_TIS cash_reward 2000 1 //"TRIPLE INSANE STUNT BONUS"
		REGISTER_inT_STAT BEST_STUNT 5
    ENDIF

    IF stunt_flags_hj = 4
    	PRINT_WITH_NUMBER HJ_QIS cash_reward 2000 1 //"QUADRUPLE INSANE STUNT BONUS"
		REGISTER_inT_STAT BEST_STUNT 7
    ENDIF

	IF ARE_MEASUREMENTS_IN_METRES
	    PRINT_WITH_6_NUMBERS HJSTAT jumpdistance_int_hj distance_decimals_int_hj height_int_hj height_decimals_int_hj counter_stunt_rolls_hj total_rotation_int 5000 5
	ELSE
		CONVERT_METRES_TO_FEET_INT jumpdistance_int_hj jumpdistance_int_hj
		CONVERT_METRES_TO_FEET_INT height_int_hj height_int_hj
	    PRINT_WITH_4_NUMBERS HJSTATF jumpdistance_int_hj height_int_hj counter_stunt_rolls_hj total_rotation_int 5000 5
	ENDIF
ENDIF

GOTO mission_start_hj

MISSION_END
 