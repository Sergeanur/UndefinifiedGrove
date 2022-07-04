MISSION_START
MISSION_END


// ******************************************************************************************
//						   launch projectile
// ******************************************************************************************

{
launch_projectile:
SCRIPT_NAME PROJECT

// input variables
LVAR_INT proj_obj
LVAR_FLOAT start_x start_y start_z
LVAR_FLOAT target_x target_y target_z
LVAR_FLOAT arch_height // above starting or finishing position
LVAR_INT missed_shot
LVAR_INT this_hoop

// flags & workings
LVAR_INT flag
LVAR_INT start_time

CONST_FLOAT  	gravity_accel		9.8

LVAR_INT this_time
LVAR_INT time_since_launch
LVAR_FLOAT time_since_launch_f
LVAR_INT temp_int

LVAR_FLOAT total_travel_time

LVAR_FLOAT temp_float temp_float2 temp_float3 
LVAR_FLOAT x2 y2 z2
LVAR_FLOAT x3 y3 z3

LVAR_FLOAT travel_vec_x travel_vec_y 

LVAR_FLOAT vertex_time	 
LVAR_FLOAT a b c

// initialise any required variables
flag = 0	 
points_scored = 0
bball_throw_active = 1


//VIEW_INTEGER_VARIABLE points_scored points_scored

// fake creates
IF flag = -1
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0  proj_obj
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0  this_hoop
ENDIF

projectile_loop:
WAIT 0

	// calculate launch data
	IF flag = 0

		// alter target slightly for missed shot
		IF missed_shot = 1
			GENERATE_RANDOM_FLOAT_IN_RANGE -0.2 0.2 temp_float
			target_x += temp_float
			GENERATE_RANDOM_FLOAT_IN_RANGE -0.2 0.2 temp_float
			target_y += temp_float
		ENDIF

		// adjust the target
		target_z += 0.1

		// make sure input data is valid (height of arch)
			IF start_z > target_z
				IF arch_height < 0.0 
					arch_height = 0.0
				ENDIF 
			ELSE
				temp_float = start_z + arch_height
				temp_float2 = temp_float - target_z
				IF temp_float2 < 0.5 // min height
					temp_float = target_z + 0.5
					arch_height = temp_float - start_z
				ENDIF
			ENDIF

		// 1. figure out time this should take (t = sqrt(d/(0.5*a))
			// going up
			temp_float = 0.5 * gravity_accel
			temp_float2 = arch_height / temp_float
			//WRITE_DEBUG_WITH_FLOAT dist_to_travel_up arch_height
			SQRT temp_float2 vertex_time

			// coming down
			temp_float = start_z + arch_height
			temp_float -= target_z	 // distance to travel
			//WRITE_DEBUG_WITH_FLOAT dist_to_travel_down temp_float
			temp_float2 = 0.5 * gravity_accel
			temp_float3 = temp_float / temp_float2
			SQRT temp_float3 temp_float 
			//WRITE_DEBUG_WITH_FLOAT coming_down_time temp_float

			// total
			total_travel_time = vertex_time + temp_float


		// 2. figure out the quadratic equation of the ball path
			
			// a
			temp_float = 0.0 - arch_height
			temp_float2 = vertex_time * vertex_time
			a = temp_float / temp_float2

			// b
			temp_float = vertex_time * a
			b = temp_float * -2.0

			// c
			c = 0.0

			//WRITE_DEBUG_WITH_FLOAT a a


		// 3. get the 2d vector the projectile travels
			travel_vec_x = target_x - start_x
			travel_vec_y = target_y - start_y	


		// 4. get initial start time, and set object initial settings
			GET_GAME_TIMER start_time

			IF DOES_OBJECT_EXIST proj_obj
				SET_OBJECT_RECORDS_COLLISIONS proj_obj TRUE
				SET_OBJECT_COLLISION proj_obj TRUE
				FREEZE_OBJECT_POSITION proj_obj TRUE
			ENDIF

		flag++
	ENDIF

	// position projectile
	IF flag = 1

		IF DOES_OBJECT_EXIST proj_obj

			IF NOT HAS_OBJECT_COLLIDED_WITH_ANYTHING proj_obj

				// work out height of ball, based on time elapsed
				GET_GAME_TIMER this_time	
				time_since_launch = this_time - start_time

				time_since_launch_f =# time_since_launch
				time_since_launch_f /= 1000.0

				IF time_since_launch_f < total_travel_time
					// use the quadratic equation to calculate height
					temp_float = time_since_launch_f * time_since_launch_f
					temp_float *= a

					temp_float2 = b * time_since_launch_f

					z = temp_float + temp_float2

					z += c

					// work out how far along the travel vector the object has gone
					temp_float = time_since_launch_f / total_travel_time
					x = travel_vec_x * temp_float
					y = travel_vec_y * temp_float

					// work out objects new position
					x2 = start_x + x
					y2 = start_y + y
					z2 = start_z + z

					SET_OBJECT_COORDINATES_AND_VELOCITY  proj_obj x2 y2 z2
				ELSE
					SET_OBJECT_COORDINATES_AND_VELOCITY proj_obj target_x target_y target_z
					flag++	
				ENDIF
			ELSE
				//WRITE_DEBUG hit_something

				GOSUB bbthrow_get_random_velocity

				FREEZE_OBJECT_POSITION proj_obj FALSE
				SET_OBJECT_DYNAMIC proj_obj TRUE
				SET_OBJECT_COLLISION proj_obj TRUE
				SET_OBJECT_VELOCITY proj_obj x3 y3 z3
				IF NOT IS_MESSAGE_BEING_DISPLAYED
					PRINT_NOW BB_06 4000 1 // missed shot
				ENDIF

				flag = 99
			ENDIF
		ENDIF

	ENDIF

	// hit bbhoop or netted.
	IF flag = 2
		
		IF missed_shot = 1
			
			IF DOES_OBJECT_EXIST this_hoop
				GET_OBJECT_MODEL this_hoop temp_int
				IF HAS_MODEL_LOADED	temp_int

					GOSUB bbthrow_get_random_velocity

//					GET_OBJECT_COORDINATES this_hoop x y z
//
//					GENERATE_RANDOM_FLOAT_IN_RANGE -1.0 1.0 temp_float
//					GENERATE_RANDOM_FLOAT_IN_RANGE  0.2 1.0 temp_float2
//					GENERATE_RANDOM_FLOAT_IN_RANGE  1.0 2.0 temp_float3
//					GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS this_hoop temp_float temp_float2 temp_float3 x2 y2 z2
//					x3 = x2 - x
//					y3 = y2 - y
//					z3 = z2 - z
//					
//					GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 temp_float
//					x3 /= temp_float
//					y3 /= temp_float
//					z3 /= temp_float
//
//					// give random speed 
//					GENERATE_RANDOM_FLOAT_IN_RANGE 5.0 8.0 temp_float
//
//					x3 *= temp_float
//					y3 *= temp_float
//					z3 *= temp_float
						
					FREEZE_OBJECT_POSITION proj_obj FALSE
					SET_OBJECT_DYNAMIC proj_obj TRUE
					SET_OBJECT_COLLISION proj_obj TRUE
					SET_OBJECT_VELOCITY proj_obj x3 y3 z3
					IF NOT IS_MESSAGE_BEING_DISPLAYED
						PRINT_NOW BB_06 4000 1 // missed shot
					ENDIF
   	
				ENDIF
			ENDIF

			// play sound hitting off ring
			REPORT_MISSION_AUDIO_EVENT_AT_OBJECT proj_obj SOUND_BASKETBALL_HIT_HOOP

			points_scored = -1
			flag = 99
	
		ELSE	   
			target_z += -0.5
			IF bball_shot_dist < 6
				IF NOT IS_MESSAGE_BEING_DISPLAYED
					PRINT_WITH_2_NUMBERS_NOW BB_05 bball_shot_dist bball_shot_dist_decimal 5000 1
				ENDIF
				points_scored = 1
			ELSE
				IF NOT IS_MESSAGE_BEING_DISPLAYED
					PRINT_WITH_2_NUMBERS_NOW BB_04 bball_shot_dist bball_shot_dist_decimal 5000 1
				ENDIF
				points_scored = 2
			ENDIF
			temp_float =# bball_shot_dist
			temp_float2 =# bball_shot_dist_decimal
			temp_float2 *= 0.1
			temp_float += temp_float2
			
			IF NOT ARE_MEASUREMENTS_IN_METRES
				CONVERT_METRES_TO_FEET temp_float temp_float
			ENDIF

			REGISTER_FLOAT_STAT LONGEST_BASKETBALL temp_float	
			flag++
		ENDIF 

	ENDIF

	// go in 
	IF flag = 3
		GET_OBJECT_COORDINATES proj_obj x y z
		GET_DISTANCE_BETWEEN_COORDS_3D x y z target_x target_y target_z temp_float

		// play sound going in
		REPORT_MISSION_AUDIO_EVENT_AT_OBJECT proj_obj SOUND_BASKETBALL_SCORE

		IF temp_float < 0.1
			FREEZE_OBJECT_POSITION proj_obj FALSE			
			SET_OBJECT_COLLISION proj_obj TRUE
			SET_OBJECT_DYNAMIC proj_obj TRUE
			SET_OBJECT_VELOCITY proj_obj 0.0 0.0 -0.1
			flag = 99
		ELSE
			x2 = target_x - x
			y2 = target_y - y
			z2 = target_z - z
			x2 *= 0.3
			y2 *= 0.3
			z2 *= 0.3 
			x += x2
			y += y2
			z += z2
			SET_OBJECT_COORDINATES proj_obj x y z
		ENDIF

	ENDIF



	// finish projectile script
	IF flag = 99
		bball_throw_active = 0
		TERMINATE_THIS_SCRIPT
	ENDIF

GOTO projectile_loop

bbthrow_get_random_velocity:

	GET_OBJECT_COORDINATES this_hoop x y z

	GENERATE_RANDOM_FLOAT_IN_RANGE -1.0 1.0 temp_float
	GENERATE_RANDOM_FLOAT_IN_RANGE  0.2 1.0 temp_float2
	GENERATE_RANDOM_FLOAT_IN_RANGE  1.2 2.2 temp_float3
	GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS this_hoop temp_float temp_float2 temp_float3 x2 y2 z2
	x3 = x2 - x
	y3 = y2 - y
	z3 = z2 - z
	
	GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 temp_float
	x3 /= temp_float
	y3 /= temp_float
	z3 /= temp_float

	// give random speed 
	//WRITE_DEBUG_WITH_FLOAT total_travel_time total_travel_time
	//GENERATE_RANDOM_FLOAT_IN_RANGE 5.0 7.0 temp_float
	temp_float = total_travel_time * 5.0 

	x3 *= temp_float
	y3 *= temp_float
	z3 *= temp_float

RETURN
	
}
