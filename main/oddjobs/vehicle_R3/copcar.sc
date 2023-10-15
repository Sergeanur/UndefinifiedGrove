MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// ********************************* Copcar oddjob ***************************************** 
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_copcar

IF HAS_DEATHARREST_BEEN_EXECUTED
	PRINT_BIG M_FAIL 5000 1 //"Mission Failed"
	GOSUB copcar_failed
ENDIF

GOSUB mission_cleanup_copcar

MISSION_END
 
// Variables for mission
{

//CARS PEDS OBJECTS PICKUPS
LVAR_INT players_cop_car
LVAR_INT criminal1_car criminal1 criminal1_thug1 criminal1_thug2 criminal1_thug3
LVAR_INT criminal2_car criminal2	criminal2_thug1 criminal2_thug2 criminal2_thug3
LVAR_INT criminal3_car criminal3	criminal3_thug1 criminal3_thug2 criminal3_thug3
LVAR_INT ped
//FLAGS COUNTERS TIMERS	ETC
LVAR_INT copcar_level break_convoy_flag
LVAR_INT game_timer_start vigilante_score num_of_followers
LVAR_INT copcar_timer game_time_flag copcar_cancelled_flag game_time_present 
LVAR_INT game_time_difference timer_in_secs mission_end_button cop_time_limit_int
LVAR_INT players_cop_car_health timer_on_screen_flag
LVAR_INT criminal1_car_stuck_timer_start criminal1_car_stuck_flag
LVAR_INT criminal1_dead_flag criminal1_thug1_dead_flag criminal1_thug2_dead_flag criminal1_thug3_dead_flag
LVAR_INT criminal1_timera criminal1_team_dead_flag criminal2_in_car_flag
LVAR_INT criminal2_car_stuck_timer_start criminal2_car_stuck_flag
LVAR_INT criminal2_dead_flag criminal2_thug1_dead_flag criminal2_thug2_dead_flag criminal2_thug3_dead_flag
LVAR_INT criminal2_timera criminal2_team_dead_flag criminal3_in_car_flag
LVAR_INT criminal3_dead_flag criminal3_thug1_dead_flag criminal3_thug2_dead_flag criminal3_thug3_dead_flag
LVAR_INT criminal3_timera criminal3_team_dead_flag
lVAR_INT random_car_model
LVAR_INT in_copcar_nice_timer 
//BLIPS
LVAR_INT criminal1_blip criminal2_blip criminal3_blip
LVAR_INT criminal1_thug1_blip criminal1_thug2_blip criminal1_thug3_blip
LVAR_INT criminal2_thug1_blip criminal2_thug2_blip criminal2_thug3_blip
LVAR_INT criminal3_thug1_blip criminal3_thug2_blip criminal3_thug3_blip
//COORDS MATHS
LVAR_FLOAT car1_x car1_y	car1_z car2_x car2_y car2_z	car3_x car3_y car3_z random_heading
LVAR_FLOAT player_dist_from_crim
LVAR_FLOAT distance_from_player
LVAR_FLOAT criminal1_x criminal1_y 
LVAR_FLOAT gosub_car_to_slow_x gosub_car_to_slow_y temp_float_x temp_float_y 
LVAR_FLOAT gosub_target_car_x gosub_target_car_y gosub_car_to_slow_speed
LVAR_FLOAT dx dy	temp_xy	car1_infront_car2 car2_infront_car1	car2_infront_car3 car3_infront_car2
LVAR_FLOAT criminal1_car_x criminal1_car_y criminal2_car_x criminal2_car_y criminal3_car_x criminal3_car_y
LVAR_FLOAT forward_x forward_y temp_x temp_y	time_bonus_divider wanted_level_multiplier time_bonus_divider_subtractor
LVAR_FLOAT criminal1_car_stuck_x criminal1_car_stuck_y criminal1_car_stuck_z criminal1_car_speed

LVAR_FLOAT sum_x_y_temp stuck_x stuck_y stuck_z
LVAR_INT gosub_stuck_car car_stuck_flag stuck_timer_start gosub_ped
LVAR_INT gosub_car_to_slow gosub_target_entity random_int max_passengers	game_time


// ****************************************Mission Start************************************

mission_start_copcar:

flag_player_on_mission = 1

SCRIPT_NAME copcar

WAIT 0

IF done_copcar_progress = 0
	REGISTER_MISSION_GIVEN
ENDIF

LVAR_FLOAT criminal2_x criminal2_y  
LVAR_FLOAT criminal2_car_stuck_x criminal2_car_stuck_y criminal2_car_stuck_z criminal2_car_speed
LVAR_FLOAT criminal3_x criminal3_y 
LVAR_FLOAT criminal3_car_stuck_x criminal3_car_stuck_y criminal3_car_stuck_z criminal3_car_speed
LVAR_INT criminal3_car_stuck_timer_start criminal3_car_stuck_flag

LOAD_MISSION_TEXT COPCAR



//CLEAR_THIS_BIG_PRINT M_FAIL

copcar_level = 0
VAR_INT cop_time_limit
cop_time_limit = -100
copcar_cancelled_flag = 0
VAR_INT	total_criminals_killed
total_criminals_killed = 0
timer_on_screen_flag = 0
mission_end_button = 0
break_convoy_flag = 0
game_timer_start = 0
players_cop_car_health = 0
vigilante_score = 0
num_of_followers = 0
copcar_timer = 0
game_time_flag = 0
game_time_present = 0
game_time_difference = 0
timer_in_secs = 0
cop_time_limit_int = 0
random_car_model = 0
time_bonus_divider = 4.0
time_bonus_divider_subtractor = 0.0
wanted_level_multiplier = 0.9
in_copcar_nice_timer = 0

heading = 0.0
gosub_car_to_slow_speed = 0.0

criminal1_car_stuck_x = 0.0
criminal1_car_stuck_y = 0.0
criminal1_car_stuck_z = 0.0
criminal1_car_speed = 0.0

criminal2_car_stuck_x = 0.0
criminal2_car_stuck_y = 0.0
criminal2_car_stuck_z = 0.0
criminal2_car_speed = 0.0

criminal3_car_stuck_x = 0.0
criminal3_car_stuck_y = 0.0
criminal3_car_stuck_z = 0.0
criminal3_car_speed = 0.0

lvar_int vehicles[91]
vehicles[0] = fcr900 	 //2 - bike	
vehicles[1] = nrg500 	 //2 - bike	
vehicles[2] = pcj600 	 //2 - bike	
vehicles[3] = faggio 	 //2 - bike	
vehicles[4] = freeway    //2 - bike	
vehicles[5] = sanchez    //2 - bike	
vehicles[6] = bf400 	 //2 - bike	
vehicles[7] = wayfarer   //2 - bike	

vehicles[8] = infernus   //2 - fast
vehicles[9] = cheetah    //2 - fast
vehicles[10] = banshee   //2 - fast	
vehicles[11] = turismo   //2 - fast
vehicles[12] = zr350  	 //2 - fast	
vehicles[13] = comet 	 //2 - fast	
vehicles[14] = supergt   //2 - fast
vehicles[15] = bullet 	 //2 - fast	
vehicles[16] = phoenix   //2 - fast	

vehicles[17] = landstal   //2
vehicles[18] = bravura    //2	
vehicles[19] = buffalo    //2	
vehicles[20] = manana     //2	
vehicles[21] = voodoo     //2	
vehicles[22] = pony 	  //2	
vehicles[23] = esperant   //2
vehicles[24] = bobcat     //2	
vehicles[25] = bfinject   //2
vehicles[26] = hotknife   //2
vehicles[27] = previon    //2	
vehicles[28] = stallion   //2
vehicles[29] = rumpo  	  //2	
vehicles[30] = hermes     //2	
vehicles[31] = sabre  	  //2	
vehicles[32] = walton     //2	
vehicles[33] = burrito    //2	
vehicles[34] = camper     //2	
vehicles[35] = rancher    //2	
vehicles[36] = virgo  	  //2	
vehicles[37] = blistac    //2	
vehicles[38] = mesa  	  //2	
vehicles[39] = majestic   //2
vehicles[40] = buccanee   //2
vehicles[41] = fortune    //2	
vehicles[42] = cadrona    //2	
vehicles[43] = feltzer    //2	
vehicles[44] = remingtn   //2
vehicles[45] = slamvan    //2	
vehicles[46] = blade  	  //2	
vehicles[47] = clover     //2	
vehicles[48] = sadler 	  //2	
vehicles[49] = hustler    //2	
vehicles[50] = tampa 	  //2	
vehicles[51] = yosemite   //2
vehicles[52] = windsor    //2	
vehicles[53] = uranus 	  //2	
vehicles[54] = jester 	  //2	
vehicles[55] = elegy 	  //2	
vehicles[56] = flash 	  //2	
vehicles[57] = savanna    //2	
vehicles[58] = broadway   //2
vehicles[59] = tornado    //2	
vehicles[60] = huntley    //2	
vehicles[61] = euros 	  //2	
vehicles[62] = club  	  //2	
vehicles[63] = picador    //2	
vehicles[64] = alpha 	  //2	
vehicles[65] = sadlshit   //2	
vehicles[66] = romero 	  //2	

vehicles[67] = peren 	  //4
vehicles[68] = sentinel   //4
vehicles[69] = moonbeam   //4
vehicles[70] = washing    //4	
vehicles[71] = premier    //4	
vehicles[72] = admiral    //4	
vehicles[73] = solair     //4	
vehicles[74] = glendale   //4
vehicles[75] = oceanic    //4	
vehicles[76] = regina     //4	
vehicles[77] = greenwoo   //4
vehicles[78] = elegant    //4	
vehicles[79] = nebula     //4	
vehicles[80] = willard    //4	
vehicles[81] = vincent    //4	
vehicles[82] = intruder   //4
vehicles[83] = primo  	  //4	
vehicles[84] = sunrise    //4	
vehicles[85] = merit  	  //4	
vehicles[86] = sultan 	  //4	
vehicles[87] = stratum    //4	
vehicles[88] = stafford   //4
vehicles[89] = emperor	  //4	
vehicles[90] = glenshit   //4

REQUEST_MODEL COLT45
REQUEST_MODEL MICRO_UZI
REQUEST_MODEL AK47
REQUEST_MODEL CHROMEGUN
REQUEST_MODEL BAT

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_MODEL_LOADED	MICRO_UZI
OR NOT HAS_MODEL_LOADED	AK47
OR NOT HAS_MODEL_LOADED	CHROMEGUN
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED BAT
	WAIT 0
ENDWHILE

//SET_ENTER_CAR_RANGE_MULTIPLIER 6.0
//SET_THREAT_REACTION_RANGE_MULTIPLIER 3.0

IF IS_CHAR_IN_ANY_CAR scplayer
	STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer players_cop_car
	SET_CAN_BURST_CAR_TYRES players_cop_car FALSE
ENDIF

lvar_int criminal_decisions
COPY_CHAR_DECISION_MAKER -1 criminal_decisions
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE criminal_decisions EVENT_VEHICLE_DAMAGE_WEAPON

lvar_int criminal_group_decisions
COPY_group_DECISION_MAKER -1 criminal_group_decisions
CLEAR_group_DECISION_MAKER_EVENT_RESPONSE criminal_group_decisions EVENT_VEHICLE_DAMAGE_WEAPON

next_criminal_to_kill:



criminal1_team_dead_flag = 0
criminal2_team_dead_flag = 0
criminal3_team_dead_flag = 0
break_convoy_flag = 0

LVAR_INT criminal1_in_car_flag
criminal1_in_car_flag = 0
//WATCH_INTEGER_VARIABLE criminal1_in_car_flag criminal1_in_car_flag
criminal1_car_stuck_timer_start = 0
criminal1_car_stuck_flag = 0
criminal1_dead_flag = 0
criminal1_thug1_dead_flag = 0
criminal1_thug2_dead_flag = 0
criminal1_thug3_dead_flag = 0

criminal2_in_car_flag = 0
criminal2_car_stuck_timer_start = 0
criminal2_car_stuck_flag = 0
criminal2_dead_flag = 0
criminal2_thug1_dead_flag = 0
criminal2_thug2_dead_flag = 0
criminal2_thug3_dead_flag = 0

criminal3_in_car_flag = 0
criminal3_car_stuck_timer_start = 0
criminal3_car_stuck_flag = 0
criminal3_dead_flag = 0
criminal3_thug1_dead_flag = 0
criminal3_thug2_dead_flag = 0
criminal3_thug3_dead_flag = 0

car1_infront_car2 = 0.0
car2_infront_car1 = 0.0

car2_infront_car3 = 0.0
car3_infront_car2 = 0.0

LVAR_INT frame_counter
frame_counter = 0

GOSUB request_random_car_model

//IS_MODEL_IN_CDIMAGE ModelIndex
//IS_MODEL_AVAILABLE ModelName

lvar_int random_ped_model
GENERATE_RANDOM_INT_IN_RANGE 9 255 random_ped_model
while random_ped_model = FAM1
or random_ped_model = FAM2
or random_ped_model = FAM3
	GENERATE_RANDOM_INT_IN_RANGE 9 255 random_ped_model
endwhile
while not IS_MODEL_IN_CDIMAGE random_ped_model
	GENERATE_RANDOM_INT_IN_RANGE 9 255 random_ped_model
	while random_ped_model = FAM1
	or random_ped_model = FAM2
	or random_ped_model = FAM3
		GENERATE_RANDOM_INT_IN_RANGE 9 255 random_ped_model
	endwhile
endwhile
REQUEST_MODEL random_ped_model

get_random_car_coords:
	

	

GOSUB copcar_cancelled_checks
IF copcar_cancelled_flag = 1
	GOTO copcar_failed
ENDIF

LVAR_FLOAT x2 y2
GET_CHAR_COORDINATES scplayer player_x player_y z





x = player_x - 800.0  //was 700
x2 = player_x + 800.0


GENERATE_RANDOM_FLOAT_IN_RANGE x x2 car1_x


y = player_y - 800.0
y2 = player_y + 800.0

GENERATE_RANDOM_FLOAT_IN_RANGE y y2 car1_y

car1_z = 10.0
IF copcar_level	< 4
	GET_CLOSEST_CAR_NODE_WITH_HEADING car1_x car1_y car1_z car1_x car1_y car1_z	random_heading
	car1_z += 0.5
ENDIF

IF copcar_level > 3
AND	copcar_level < 8
	GET_CLOSEST_STRAIGHT_ROAD car1_x car1_y car1_z 8.0 500.0 car1_x car1_y car1_z car2_x car2_y car2_z random_heading
	IF car1_x = 0.0
	AND	car1_y = 0.0
	AND	car2_x = 0.0
	AND	car2_y = 0.0
		WAIT 0
		GOTO get_random_car_coords
	ENDIF
	//MAKES THE DISTANCE BETWEEN CAR1_XYZ AND CAR2_XYZ EQUAL TO MINLENGTH OF STRAIGHT ROAD (8.0)
	dx = car2_x - car1_x
	dy = car2_y - car1_y
	temp_x = dx * dx
	temp_y = dy * dy
	temp_xy = temp_x + temp_y
	SQRT temp_xy temp_xy
	sum_x_y_temp = 8.0 / temp_xy
	temp_x = dx * sum_x_y_temp
	car2_x = car1_x + temp_x
	temp_y = dy * sum_x_y_temp
	car2_y = car1_y + temp_y
	
	//BRINGS THE CAR1_XYZ BACK TOWARDS CAR2_XYZ BY 2.0 METERS
	sum_x_y_temp = 2.0 / temp_xy
	temp_x = dx * sum_x_y_temp
	car1_x = car1_x + temp_x
	temp_y = dy * sum_x_y_temp
	car1_y = car1_y + temp_y
	
	car1_z += 0.5
	car2_z += 0.5
ENDIF

IF copcar_level > 7
//AND	copcar_level < 12
	GET_CLOSEST_STRAIGHT_ROAD car1_x car1_y car1_z 13.0 500.0 car1_x car1_y car1_z car3_x car3_y car3_z random_heading
	IF car1_x = 0.0
	AND	car1_y = 0.0
	AND	car3_x = 0.0
	AND	car3_y = 0.0
		WAIT 0
		GOTO get_random_car_coords
	ENDIF
	
	//MAKES THE DISTANCE BETWEEN CAR1_XYZ AND CAR3_XYZ EQUAL TO MINLENGTH OF STRAIGHT ROAD (13.0)
	dx = car3_x - car1_x
	dy = car3_y - car1_y
	temp_x = dx * dx
	temp_y = dy * dy
	temp_xy = temp_x + temp_y
	SQRT temp_xy temp_xy
	sum_x_y_temp = 13.0 / temp_xy
	temp_x = dx * sum_x_y_temp
	car3_x = car1_x + temp_x
	temp_y = dy * sum_x_y_temp
	car3_y = car1_y + temp_y

	//BRINGS THE CAR1_XYZ BACK TOWARDS CAR3_XYZ BY 2.0 METERS
	sum_x_y_temp = 2.0 / temp_xy
	temp_x = dx * sum_x_y_temp
	car1_x = car1_x + temp_x
	temp_y = dy * sum_x_y_temp
	car1_y = car1_y + temp_y

	//WORKS OUT THE MIDDLE POINT OF CAR1_XYZ AND CAR3_XYZ
	car2_x = car1_x	- car3_x
	car2_x = car2_x / 2.0
	car2_x = car2_x	+ car3_x
	
	car2_y = car1_y	- car3_y
	car2_y = car2_y / 2.0
	car2_y = car2_y	+ car3_y
	
	car2_z = car1_z	- car3_z
	car2_z = car2_z / 2.0
	car2_z = car2_z	+ car3_z
	
	car1_z += 0.5
	car2_z += 0.5
	car3_z += 0.5
ENDIF

IF car1_z < 1.0
	WAIT 0
	GOTO get_random_car_coords
ENDIF

GET_CHAR_COORDINATES scplayer player_x player_y player_z
GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y car1_x car1_y distance_from_player

IF distance_from_player < 150.0
OR distance_from_player > 800.0
	WAIT 0
	GOTO get_random_car_coords
ENDIF

GET_INT_STAT CITIES_PASSED return_cities_passed
IF return_cities_passed = 0	//sanfran not open
//if flag_la1fin1_mission_counter < 2
	IF car1_x  < 78.4427   //south by la
	AND	car1_y < -699.5190
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	IF car1_x  < -252.6557 //below jutty out bit west badlands
	AND	car1_y < -285.7660
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	IF car1_x  < -948.3447 //west of jutty out bit west badlands
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	if car1_x > 1473.4481 //below vegas north east badlands
	and car1_y > 403.7353
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	if car1_y > 578.6325 //north of jutty out bit middle north badlands
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	if car1_x < 837.5551  //northwest from above jutty out bit middle north badlands
	and car1_y > 347.4097
		WAIT 0
		GOTO get_random_car_coords
	ENDIF
endif

IF return_cities_passed < 2 //vegas not open
//if flag_synd_mission_counter < 10
	if car1_x > 1473.4481 //below vegas north east badlands
	and car1_y > 403.7353
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	if car1_y > 578.6325 //north of jutty out bit middle north badlands
	and car1_x > -1528.4976
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	if car1_x < 837.5551  //northwest from above jutty out bit middle north badlands
	and car1_x > -1528.4976
	and car1_y > 347.4097
		WAIT 0
		GOTO get_random_car_coords
	ENDIF

	if car1_y > 1380.0 //north of sanfran
		WAIT 0
		GOTO get_random_car_coords
	ENDIF
	
	if car1_x >	 -2702.7197	 //SF bridge
	and car1_y > 1225.6692
	and car1_x < -2663.1455
	and car1_y < 1654.8080 
		wait 0
		goto get_random_car_coords
	endif
endif

//SF airport
if car1_x >	-1735.7512
and car1_y > -696.0117
and car1_x < -1192.8174
and car1_y < 286.3584
	wait 0
	goto get_random_car_coords
endif
  	
//SF airport
if car1_x >	 -1192.8174
and car1_y > -521.9908
and car1_x < -1125.6556
and car1_y < 536.9476
	wait 0
	goto get_random_car_coords
endif

//SF airport
if car1_x >	 -1125.6556
and car1_y > -329.5900
and car1_x < -1020.1886
and car1_y < 536.9476
	wait 0
	goto get_random_car_coords
endif

VAR_INT random_seed
GENERATE_RANDOM_INT random_seed
WHILE random_seed = 0
	GENERATE_RANDOM_INT random_seed
ENDWHILE

WHILE NOT HAS_MODEL_LOADED random_car_model
OR NOT HAS_MODEL_LOADED random_ped_model
	
	WAIT 0
	
	GOSUB copcar_cancelled_checks
	IF copcar_cancelled_flag = 1
		GOTO copcar_failed
	ENDIF

ENDWHILE

CREATE_CAR random_car_model car1_x car1_y car1_z criminal1_car
SET_CAR_HEALTH criminal1_car 800
SET_CAR_DRIVING_STYLE criminal1_car 2
if copcar_level > 3
	SET_CAR_RANDOM_ROUTE_SEED criminal1_car random_seed
endif
MARK_CAR_AS_CONVOY_CAR criminal1_car TRUE
SET_CAR_HEADING criminal1_car random_heading
SET_CAR_AVOID_LEVEL_TRANSITIONS criminal1_car TRUE
//SET_LOAD_COLLISION_FOR_CAR_FLAG criminal1_car FALSE

WHILE IS_CAR_WAITING_FOR_WORLD_COLLISION criminal1_car
	WAIT 0
	
	IF IS_CAR_DEAD criminal1_car
		GOTO copcar_failed
	ENDIF

	GOSUB copcar_cancelled_checks
	IF copcar_cancelled_flag = 1
		GOTO copcar_failed
	ENDIF

ENDWHILE

CREATE_CHAR_INSIDE_CAR criminal1_car PEDTYPE_MISSION1 random_ped_model criminal1

//POINT_CAMERA_AT_CHAR criminal1 FOLLOWPED JUMP_CUT //BEHINDCAR
//SET_CAMERA_BEHIND_PLAYER

IF flag_player_on_mission = 0
	ADD_BLIP_FOR_COORD x y z criminal1_blip
	ADD_BLIP_FOR_COORD x y z criminal2_blip
	ADD_BLIP_FOR_COORD x y z criminal3_blip
	ADD_BLIP_FOR_COORD x y z criminal1_thug1_blip 
	ADD_BLIP_FOR_COORD x y z criminal1_thug2_blip 
	ADD_BLIP_FOR_COORD x y z criminal1_thug3_blip
	ADD_BLIP_FOR_COORD x y z criminal2_thug1_blip 
	ADD_BLIP_FOR_COORD x y z criminal2_thug2_blip 
	ADD_BLIP_FOR_COORD x y z criminal2_thug3_blip
	ADD_BLIP_FOR_COORD x y z criminal3_thug1_blip 
	ADD_BLIP_FOR_COORD x y z criminal3_thug2_blip 
	ADD_BLIP_FOR_COORD x y z criminal3_thug3_blip
	CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS criminal1_group
	CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS criminal2_group
	CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS criminal3_group
ENDIF

REMOVE_BLIP	criminal1_blip
ADD_BLIP_FOR_CHAR criminal1 criminal1_blip

ped = criminal1
GOSUB setup_ped_threats_etc

IF copcar_level > 0
	LVAR_INT criminal1_group
	REMOVE_GROUP criminal1_group
	CREATE_GROUP DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR criminal1_group
	//CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS criminal1_group
	SET_GROUP_LEADER criminal1_group criminal1
	SET_GROUP_DECISION_MAKER criminal1_group criminal_group_decisions
	CREATE_CHAR_AS_PASSENGER criminal1_car PEDTYPE_MISSION1 random_ped_model 0 criminal1_thug1
	
	REMOVE_BLIP criminal1_thug1_blip
	ADD_BLIP_FOR_CHAR criminal1_thug1 criminal1_thug1_blip

	ped = criminal1_thug1
	GOSUB setup_ped_threats_etc
ELSE
	criminal1_thug1_dead_flag = 1
ENDIF

IF copcar_level > 1
	CREATE_CHAR_AS_PASSENGER criminal1_car PEDTYPE_MISSION1 random_ped_model 1 criminal1_thug2
	REMOVE_BLIP criminal1_thug2_blip
	ADD_BLIP_FOR_CHAR criminal1_thug2 criminal1_thug2_blip
	ped = criminal1_thug2
	GOSUB setup_ped_threats_etc
ELSE
	criminal1_thug2_dead_flag = 1
ENDIF

IF copcar_level > 2
	CREATE_CHAR_AS_PASSENGER criminal1_car PEDTYPE_MISSION1 random_ped_model 2 criminal1_thug3
	REMOVE_BLIP criminal1_thug3_blip
	ADD_BLIP_FOR_CHAR criminal1_thug3 criminal1_thug3_blip
	ped = criminal1_thug3
	GOSUB setup_ped_threats_etc
ELSE
	criminal1_thug3_dead_flag = 1
ENDIF

IF copcar_level > 3
	CREATE_CAR random_car_model car2_x car2_y car2_z criminal2_car
	MARK_CAR_AS_CONVOY_CAR criminal2_car TRUE
	SET_CAR_HEADING criminal2_car random_heading
	SET_CAR_AVOID_LEVEL_TRANSITIONS criminal2_car TRUE
	SET_CAR_DRIVING_STYLE criminal2_car 2
	SET_CAR_HEALTH criminal2_car 800
	SET_CAR_RANDOM_ROUTE_SEED criminal2_car random_seed
//	SET_LOAD_COLLISION_FOR_CAR_FLAG criminal2_car FALSE
	
	WHILE IS_CAR_WAITING_FOR_WORLD_COLLISION criminal2_car
		WAIT 0
		
		IF IS_CAR_DEAD criminal2_car
			GOTO copcar_failed
		ENDIF

		GOSUB copcar_cancelled_checks
		IF copcar_cancelled_flag = 1
			GOTO copcar_failed
		ENDIF

	ENDWHILE

	CREATE_CHAR_INSIDE_CAR criminal2_car PEDTYPE_MISSION1 random_ped_model criminal2

	//POINT_CAMERA_AT_CHAR criminal2 FOLLOWPED JUMP_CUT //BEHINDCAR
	//SET_CAMERA_BEHIND_PLAYER
	ped = criminal2
	GOSUB setup_ped_threats_etc
	REMOVE_BLIP	criminal2_blip
	ADD_BLIP_FOR_CHAR criminal2 criminal2_blip
ELSE
	criminal2_team_dead_flag = 1
	criminal2_dead_flag = 1
ENDIF

IF copcar_level > 4
	LVAR_INT criminal2_group
	REMOVE_GROUP criminal2_group
	CREATE_GROUP DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR criminal2_group
	//CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS criminal2_group
	SET_GROUP_LEADER criminal2_group criminal2
	SET_GROUP_DECISION_MAKER criminal2_group criminal_group_decisions
	CREATE_CHAR_AS_PASSENGER criminal2_car PEDTYPE_MISSION1 random_ped_model 0 criminal2_thug1
	REMOVE_BLIP criminal2_thug1_blip
	ADD_BLIP_FOR_CHAR criminal2_thug1 criminal2_thug1_blip

	ped = criminal2_thug1
	GOSUB setup_ped_threats_etc
ELSE
	criminal2_thug1_dead_flag = 1
ENDIF

IF copcar_level > 5
	CREATE_CHAR_AS_PASSENGER criminal2_car PEDTYPE_MISSION1 random_ped_model 1 criminal2_thug2
	REMOVE_BLIP criminal2_thug2_blip
	ADD_BLIP_FOR_CHAR criminal2_thug2 criminal2_thug2_blip
	ped = criminal2_thug2
	GOSUB setup_ped_threats_etc
ELSE
	criminal2_thug2_dead_flag = 1
ENDIF

IF copcar_level > 6
	CREATE_CHAR_AS_PASSENGER criminal2_car PEDTYPE_MISSION1 random_ped_model 2 criminal2_thug3
	REMOVE_BLIP criminal2_thug3_blip
	ADD_BLIP_FOR_CHAR criminal2_thug3 criminal2_thug3_blip
	ped = criminal2_thug3
	GOSUB setup_ped_threats_etc
ELSE
	criminal2_thug3_dead_flag = 1
ENDIF

IF copcar_level > 7
	CREATE_CAR random_car_model car3_x car3_y car3_z criminal3_car
	MARK_CAR_AS_CONVOY_CAR criminal3_car TRUE
	SET_CAR_HEADING criminal3_car random_heading
	SET_CAR_AVOID_LEVEL_TRANSITIONS criminal3_car TRUE
	SET_CAR_DRIVING_STYLE criminal3_car 2
	SET_CAR_HEALTH criminal3_car 800
	SET_CAR_RANDOM_ROUTE_SEED criminal3_car random_seed
//	SET_LOAD_COLLISION_FOR_CAR_FLAG criminal3_car FALSE
	
	WHILE IS_CAR_WAITING_FOR_WORLD_COLLISION criminal3_car
		WAIT 0
		
		IF IS_CAR_DEAD criminal3_car
			GOTO copcar_failed
		ENDIF

		GOSUB copcar_cancelled_checks
		IF copcar_cancelled_flag = 1
			GOTO copcar_failed
		ENDIF

	ENDWHILE

	CREATE_CHAR_INSIDE_CAR criminal3_car PEDTYPE_MISSION1 random_ped_model criminal3

	//POINT_CAMERA_AT_CHAR criminal3 FOLLOWPED JUMP_CUT //BEHINDCAR
	//SET_CAMERA_BEHIND_PLAYER
	ped = criminal3
	GOSUB setup_ped_threats_etc
	REMOVE_BLIP	criminal3_blip
	ADD_BLIP_FOR_CHAR criminal3 criminal3_blip
ELSE
	criminal3_team_dead_flag = 1
	criminal3_dead_flag = 1
ENDIF

IF copcar_level > 8
	LVAR_INT criminal3_group
	REMOVE_GROUP criminal3_group
	CREATE_GROUP DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR criminal3_group
	//CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS criminal3_group
	SET_GROUP_LEADER criminal3_group criminal3
	SET_GROUP_DECISION_MAKER criminal3_group criminal_group_decisions
	CREATE_CHAR_AS_PASSENGER criminal3_car PEDTYPE_MISSION1 random_ped_model 0 criminal3_thug1
	REMOVE_BLIP criminal3_thug1_blip
	ADD_BLIP_FOR_CHAR criminal3_thug1 criminal3_thug1_blip
	ped = criminal3_thug1
	GOSUB setup_ped_threats_etc
ELSE
	criminal3_thug1_dead_flag = 1
ENDIF

IF copcar_level > 9
	CREATE_CHAR_AS_PASSENGER criminal3_car PEDTYPE_MISSION1 random_ped_model 1 criminal3_thug2
	REMOVE_BLIP criminal3_thug2_blip
	ADD_BLIP_FOR_CHAR criminal3_thug2 criminal3_thug2_blip
	ped = criminal3_thug2
	GOSUB setup_ped_threats_etc
ELSE
	criminal3_thug2_dead_flag = 1
ENDIF

IF copcar_level > 10
	CREATE_CHAR_AS_PASSENGER criminal3_car PEDTYPE_MISSION1 random_ped_model 2 criminal3_thug3
	REMOVE_BLIP criminal3_thug3_blip
	ADD_BLIP_FOR_CHAR criminal3_thug3 criminal3_thug3_blip
	ped = criminal3_thug3
	GOSUB setup_ped_threats_etc
ELSE
	criminal3_thug3_dead_flag = 1
ENDIF

MARK_MODEL_AS_NO_LONGER_NEEDED random_car_model
MARK_MODEL_AS_NO_LONGER_NEEDED random_ped_model

//GOSUB print_text_for_zone_char_is_in
if not is_char_dead	criminal1
	GET_CHAR_COORDINATES criminal1 criminal1_x criminal1_y z
endif
var_text_label $criminal_zone
GET_NAME_OF_ZONE criminal1_x criminal1_y z $criminal_zone
if copcar_level = 0
	PRINT_STRING_IN_STRING_NOW C_BREIF $criminal_zone 5000 1 // ~r~Suspect ~s~last seen in the vicinity of ~a~.
else
	PRINT_STRING_IN_STRING_NOW C_BREIS $criminal_zone 5000 1 // ~r~Suspects ~s~last seen in the vicinity of ~a~.
endif
POLICE_RADIO_MESSAGE criminal1_x criminal1_y z

GET_CHAR_COORDINATES scplayer player_x player_y player_z
x_temp = player_x - criminal1_x
y_temp = player_y - criminal1_y
x_temp = x_temp * x_temp
y_temp = y_temp * y_temp
player_dist_from_crim = y_temp + y_temp
SQRT player_dist_from_crim player_dist_from_crim

if is_char_in_model scplayer HUNTER
	time_bonus_divider = 8.0
else
	time_bonus_divider = 4.0
endif
time_bonus_divider += time_bonus_divider_subtractor
player_dist_from_crim /= time_bonus_divider
player_dist_from_crim *= 1000.0
cop_time_limit_int =# player_dist_from_crim
IF cop_time_limit_int < 40000
	cop_time_limit_int = 40000
ENDIF
cop_time_limit += cop_time_limit_int

cop_time_limit_int /= 1000

var_int copcar_print_level
copcar_print_level = copcar_level + 1
IF timer_on_screen_flag = 1
	FORCE_BIG_MESSAGE_AND_COUNTER TRUE
	PRINT_WITH_NUMBER_BIG A_TIME cop_time_limit_int 4000 6	//+~1~ Seconds
ELSE
	IF cop_time_limit < 45000
		cop_time_limit = 45000
	ENDIF

	cop_time_limit += 30000

	DISPLAY_ONSCREEN_TIMER_WITH_STRING cop_time_limit TIMER_DOWN COPTIME
	timer_on_screen_flag = 1
	DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING copcar_print_level COUNTER_DISPLAY_NUMBER 1 COPLEVL
	DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING total_criminals_killed COUNTER_DISPLAY_NUMBER 2 KILLS
ENDIF

GET_GAME_TIMER game_timer
criminal1_timera = game_timer
criminal2_timera = game_timer
criminal3_timera = game_timer

IF copcar_level < 13
	time_bonus_divider_subtractor += 0.1
ENDIF

FREEZE_ONSCREEN_TIMER FALSE

copcar_oddjob_loop:

WAIT 0

GET_GAME_TIMER game_timer
GET_CHAR_COORDINATES scplayer player_x player_y player_z

GOSUB copcar_cancelled_checks
IF copcar_cancelled_flag = 1
	GOTO copcar_failed
ENDIF

IF criminal1_in_car_flag = -1
OR criminal2_in_car_flag = -1
OR criminal3_in_car_flag = -1
	break_convoy_flag = 1
ENDIF

++ frame_counter
IF frame_counter > 2
	frame_counter = 0
ENDIF

/////
////criminal1
/////
IF frame_counter = 0
	IF NOT IS_CHAR_DEAD criminal1
		IF IS_CHAR_SITTING_IN_ANY_CAR criminal1
			
			MARK_CAR_AS_NO_LONGER_NEEDED criminal1_car
			STORE_CAR_CHAR_IS_IN_NO_SAVE criminal1 criminal1_car
			
			IF criminal1_in_car_flag = -1
				GET_NUMBER_OF_FOLLOWERS criminal1 num_of_followers
				IF num_of_followers > 0
					if DOES_GROUP_EXIST criminal1_group
						SET_GROUP_DEFAULT_TASK_ALLOCATOR criminal1_group DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR
					endif
					criminal1_timera = game_timer + 2000
				ELSE
					criminal1_timera = game_timer
				ENDIF
				criminal1_in_car_flag = 0
			ENDIF
			
			IF criminal1_in_car_flag = 0
				IF criminal1_timera < game_timer
				OR criminal1_timera = game_timer
					SET_CAR_CRUISE_SPEED criminal1_car 100.0
					SET_CAR_DRIVING_STYLE criminal1_car 2
					//BREAKPOINT car_wander_randomly
					CAR_WANDER_RANDOMLY	criminal1_car
					criminal1_car_stuck_timer_start = game_timer
					criminal1_in_car_flag = 1
					criminal1_car_stuck_flag = 0
				ENDIF
			ENDIF
			
			IF criminal1_in_car_flag > 0

				IF IS_CHAR_IN_CAR scplayer criminal1_car
					GET_SCRIPT_TASK_STATUS criminal1 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal1 scplayer
					ENDIF
				ENDIF

				gosub_stuck_car = criminal1_car
				car_stuck_flag = criminal1_car_stuck_flag
				stuck_timer_start = criminal1_car_stuck_timer_start
				stuck_x = criminal1_car_stuck_x
				stuck_y = criminal1_car_stuck_y
				stuck_z = criminal1_car_stuck_z
				IF criminal1_car_speed > 0.0
					GOSUB criminal_car_stuck_checks
				ENDIF
				criminal1_car_stuck_flag = car_stuck_flag
				criminal1_car_stuck_timer_start = stuck_timer_start
				criminal1_car_stuck_x = stuck_x
				criminal1_car_stuck_y = stuck_y
				criminal1_car_stuck_z = stuck_z
				
				IF criminal1_car_stuck_flag = -9
					GET_SCRIPT_TASK_STATUS criminal1 TASK_FLEE_POINT task_status
					IF task_status = FINISHED_TASK
						GET_CHAR_COORDINATES criminal1 x y z
						TASK_FLEE_POINT criminal1 x y z 15.0 1500
					ENDIF
					criminal1_car_stuck_flag = 0
				ENDIF
		
				IF NOT IS_CAR_HEALTH_GREATER criminal1_car 251
					GET_SCRIPT_TASK_STATUS criminal1 TASK_FLEE_POINT task_status
					IF task_status = FINISHED_TASK
						GET_CHAR_COORDINATES criminal1 x y z
						TASK_FLEE_POINT criminal1 x y z 15.0 1500
					ENDIF
					criminal1_timera = game_timer + 2000
				ELSE
					criminal1_timera = game_timer
				ENDIF
				
				GOSUB criminal1_group_breaking
				
				GET_CHAR_COORDINATES criminal1 criminal1_x criminal1_y z
				x_temp = player_x - criminal1_x //	dist = (1_x - 2_x)~2  +	(1_y - 2_y)~2
				y_temp = player_y - criminal1_y
				x_temp = x_temp * x_temp
				y_temp = y_temp * y_temp
				sum_x_y_temp = x_temp + y_temp
				SQRT sum_x_y_temp sum_x_y_temp
				
				criminal1_car_speed = 1000.0 / sum_x_y_temp

				IF criminal1_car_speed > 100.0
					criminal1_car_speed = 100.0
				ENDIF
				IF criminal1_car_speed < 15.0
					criminal1_car_speed = 15.0
				ENDIF

				SET_CAR_CRUISE_SPEED criminal1_car criminal1_car_speed
			ENDIF
		ELSE
			if DOES_GROUP_EXIST criminal1_group
				SET_GROUP_DEFAULT_TASK_ALLOCATOR criminal1_group DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS
			endif
			criminal1_in_car_flag = -1
			GOSUB criminal1_group_breaking
			IF game_timer > criminal1_timera
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer criminal1 45.0 45.0 0
					IF NOT IS_CHAR_IN_ANY_CAR scplayer
						GET_SCRIPT_TASK_STATUS criminal1 TASK_KILL_CHAR_ON_FOOT task_status
						IF task_status = FINISHED_TASK
							TASK_KILL_CHAR_ON_FOOT criminal1 scplayer
						ENDIF
					ELSE
						IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer criminal1 30.0 30.0 0
							GET_SCRIPT_TASK_STATUS criminal1 TASK_KILL_CHAR_ON_FOOT task_status
							IF task_status = FINISHED_TASK
								TASK_KILL_CHAR_ON_FOOT criminal1 scplayer
							ENDIF
						ELSE
							gosub_ped = criminal1
							IF NOT IS_CAR_DEAD criminal1_car
								IF IS_CAR_HEALTH_GREATER criminal1_car 400
									IF LOCATE_CHAR_ANY_MEANS_CAR_2D	criminal1 criminal1_car 40.0 40.0 0
										GET_SCRIPT_TASK_STATUS criminal1 TASK_ENTER_CAR_AS_DRIVER task_status
										IF task_status = FINISHED_TASK
											TASK_ENTER_CAR_AS_DRIVER criminal1 criminal1_car -2
										ENDIF
									ELSE
										GOSUB criminal_steal_a_car
									ENDIF
								ELSE
									GOSUB criminal_steal_a_car
								ENDIF
							ELSE
								GOSUB criminal_steal_a_car
							ENDIF
						ENDIF
					ENDIF
				ELSE
					gosub_ped = criminal1
					IF NOT IS_CAR_DEAD criminal1_car
						IF IS_CAR_HEALTH_GREATER criminal1_car 400
							IF LOCATE_CHAR_ANY_MEANS_CAR_2D	criminal1 criminal1_car 40.0 40.0 0
								GET_SCRIPT_TASK_STATUS criminal1 TASK_ENTER_CAR_AS_DRIVER task_status
								IF task_status = FINISHED_TASK
									TASK_ENTER_CAR_AS_DRIVER criminal1 criminal1_car -2
								ENDIF
							ELSE
								GOSUB criminal_steal_a_car
							ENDIF
						ELSE
							GOSUB criminal_steal_a_car
						ENDIF
					ELSE
						GOSUB criminal_steal_a_car
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ELSE
		REMOVE_BLIP criminal1_blip
		MARK_CHAR_AS_NO_LONGER_NEEDED criminal1
		criminal1 = -1
		IF criminal1_dead_flag = 0
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal1_dead_flag = 1
		ENDIF
		if does_group_exist	criminal1_group
			IF NOT IS_CHAR_DEAD	criminal1_thug1
				IF NOT IS_GROUP_MEMBER criminal1_thug1 criminal1_group
					GET_SCRIPT_TASK_STATUS criminal1_thug1 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal1_thug1 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal1_thug1 criminal1_group
						criminal1 = criminal1_thug1
						criminal1_thug1 = -1
						criminal1_dead_flag = 0
						criminal1_thug1_dead_flag = 1
						criminal1_blip = criminal1_thug1_blip
						criminal1_thug1_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal1_thug1_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug1
				criminal1_thug1 = -1
			ENDIF
			IF NOT IS_CHAR_DEAD	criminal1_thug2
				IF NOT IS_GROUP_MEMBER criminal1_thug2 criminal1_group
					GET_SCRIPT_TASK_STATUS criminal1_thug2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal1_thug2 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal1_thug2 criminal1_group
						criminal1 = criminal1_thug2
						criminal1_thug2 = -1
						criminal1_dead_flag = 0
						criminal1_thug2_dead_flag = 1
						criminal1_blip = criminal1_thug2_blip
						criminal1_thug2_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal1_thug2_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug2
				criminal1_thug2 = -1
			ENDIF
			IF NOT IS_CHAR_DEAD	criminal1_thug3
				IF NOT IS_GROUP_MEMBER criminal1_thug3 criminal1_group
					GET_SCRIPT_TASK_STATUS criminal1_thug3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal1_thug3 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal1_thug3 criminal1_group
						criminal1 = criminal1_thug3
						criminal1_thug3 = -1
						criminal1_dead_flag = 0
						criminal1_thug3_dead_flag = 1
						criminal1_blip = criminal1_thug3_blip
						criminal1_thug3_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal1_thug3_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug3
				criminal1_thug3 = -1
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal1_thug1
		IF criminal1_thug1_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug1
			criminal1_thug1 = -1
			REMOVE_BLIP criminal1_thug1_blip	
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal1_thug1_dead_flag = 1
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal1_thug2
		IF criminal1_thug2_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug2
			criminal1_thug2 = -1
			REMOVE_BLIP criminal1_thug2_blip
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal1_thug2_dead_flag = 1
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal1_thug3
		IF criminal1_thug3_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug3
			criminal1_thug3 = -1
			REMOVE_BLIP criminal1_thug3_blip
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal1_thug3_dead_flag = 1
		ENDIF
	ENDIF

	IF criminal1_team_dead_flag = 0
		IF criminal1_dead_flag = 1
		AND	criminal1_thug1_dead_flag = 1
		AND	criminal1_thug2_dead_flag = 1
		AND	criminal1_thug3_dead_flag = 1
			criminal1_team_dead_flag = 1
		ENDIF
	ENDIF
ENDIF



/////
////criminal2
/////
IF frame_counter = 1
	IF NOT IS_CHAR_DEAD criminal2
		IF IS_CHAR_SITTING_IN_ANY_CAR criminal2
			
			MARK_CAR_AS_NO_LONGER_NEEDED criminal2_car
			STORE_CAR_CHAR_IS_IN_NO_SAVE criminal2 criminal2_car
			
			IF criminal2_in_car_flag = -1
				GET_NUMBER_OF_FOLLOWERS criminal2 num_of_followers
				IF num_of_followers > 0
					if DOES_GROUP_EXIST criminal2_group
						SET_GROUP_DEFAULT_TASK_ALLOCATOR criminal2_group DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR
					endif
					criminal2_timera = game_timer + 2000
				ELSE
					criminal2_timera = game_timer
				ENDIF
				criminal2_in_car_flag = 0
			ENDIF
			
			IF criminal2_in_car_flag = 0
				IF game_timer > criminal2_timera
					SET_CAR_CRUISE_SPEED criminal2_car 100.0
					SET_CAR_DRIVING_STYLE criminal2_car 2
					IF break_convoy_flag = 0
						IF NOT IS_CAR_DEAD criminal1_car
							CAR_WANDER_RANDOMLY	criminal2_car
						ELSE
							break_convoy_flag = 1
						ENDIF
					ENDIF
					IF break_convoy_flag = 1
						MARK_CAR_AS_CONVOY_CAR criminal2_car FALSE
						CAR_WANDER_RANDOMLY	criminal2_car
					ENDIF
					criminal2_car_stuck_timer_start = game_timer
					criminal2_in_car_flag = 1
					criminal2_car_stuck_flag = 0
				ENDIF
			ENDIF
			
			IF criminal2_in_car_flag > 0

				IF IS_CHAR_IN_CAR scplayer criminal2_car
					GET_SCRIPT_TASK_STATUS criminal2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2 scplayer
					ENDIF
				ENDIF

				gosub_stuck_car = criminal2_car
				car_stuck_flag = criminal2_car_stuck_flag
				stuck_timer_start = criminal2_car_stuck_timer_start
				stuck_x = criminal2_car_stuck_x
				stuck_y = criminal2_car_stuck_y
				stuck_z = criminal2_car_stuck_z
				IF criminal2_car_speed > 0.0
					GOSUB criminal_car_stuck_checks
				ENDIF
				criminal2_car_stuck_flag = car_stuck_flag
				criminal2_car_stuck_timer_start = stuck_timer_start
				criminal2_car_stuck_x = stuck_x
				criminal2_car_stuck_y = stuck_y
				criminal2_car_stuck_z = stuck_z
				
				IF criminal2_car_stuck_flag = -9
					GET_SCRIPT_TASK_STATUS criminal2 TASK_FLEE_POINT task_status
					IF task_status = FINISHED_TASK
						GET_CHAR_COORDINATES criminal2 x y z
						TASK_FLEE_POINT criminal2 x y z 15.0 1500
					ENDIF
					criminal2_car_stuck_flag = 0
				ENDIF
		
				IF NOT IS_CAR_HEALTH_GREATER criminal2_car 400
					GET_SCRIPT_TASK_STATUS criminal2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2 scplayer
					ENDIF
				ENDIF

				IF NOT IS_CAR_HEALTH_GREATER criminal2_car 251
					GET_SCRIPT_TASK_STATUS criminal2 TASK_FLEE_POINT task_status
					IF task_status = FINISHED_TASK
						GET_CHAR_COORDINATES criminal2 x y z
						TASK_FLEE_POINT criminal2 x y z 15.0 1500
					ENDIF
					criminal2_timera = game_timer + 2000
				ELSE
					criminal2_timera = game_timer
				ENDIF
				
				GOSUB criminal2_group_breaking
				
				GET_CHAR_COORDINATES criminal2 criminal2_x criminal2_y z
				x_temp = player_x - criminal2_x //	dist = (1_x - 2_x)~2  +	(1_y - 2_y)~2
				y_temp = player_y - criminal2_y
				x_temp = x_temp * x_temp
				y_temp = y_temp * y_temp
				sum_x_y_temp = x_temp + y_temp
				SQRT sum_x_y_temp sum_x_y_temp
				
				criminal2_car_speed = 1000.0 / sum_x_y_temp

				IF criminal2_car_speed > 100.0
					criminal2_car_speed = 100.0
				ENDIF
				IF criminal2_car_speed < 15.0
					criminal2_car_speed = 15.0
				ENDIF

				IF break_convoy_flag = 0
					IF NOT IS_CAR_DEAD criminal1_car
						gosub_car_to_slow = criminal2_car
						gosub_target_entity = criminal1_car
						GOSUB slow_criminal_if_close 
						criminal2_car_speed = gosub_car_to_slow_speed
						
						//THIS BIT OF CODE CHECKS TO SEE IF CAR1 IS INFRONT OF CAR2
						GET_CAR_FORWARD_X criminal2_car forward_x
						GET_CAR_FORWARD_Y criminal2_car forward_y
						GET_CAR_COORDINATES criminal1_car criminal1_car_x criminal1_car_y z
						GET_CAR_COORDINATES	criminal2_car criminal2_car_x criminal2_car_y z
						// IF (((forward_x * (car2_x - car1_x)) + (forward_y * (car2_y - car1_y)>) IS GREATER THAN 0.0 CAR IS IN FRONT
						temp_float_x = criminal2_car_x - criminal1_car_x
						forward_x = forward_x * temp_float_x
						temp_float_y = criminal2_car_y - criminal1_car_y
						forward_y = forward_y * temp_float_y
						car1_infront_car2 = forward_x + forward_y

						//THIS BIT OF CODE CHECKS TO SEE IF CAR2 IS INFRONT OF CAR1
						GET_CAR_FORWARD_X criminal1_car forward_x
						GET_CAR_FORWARD_Y criminal1_car forward_y
						GET_CAR_COORDINATES criminal2_car criminal2_car_x criminal2_car_y z
						GET_CAR_COORDINATES	criminal1_car criminal1_car_x criminal1_car_y z
						// IF (((forward_x * (car1_x - car2_x)) + (forward_y * (car1_y - car2_y)>) IS GREATER THAN 0.0 CAR IS IN FRONT
						temp_float_x = criminal1_car_x - criminal2_car_x
						forward_x = forward_x * temp_float_x
						temp_float_y = criminal1_car_y - criminal2_car_y
						forward_y = forward_y * temp_float_y
						car2_infront_car1 = forward_x + forward_y

						IF car1_infront_car2 > 0.0// +=INFRONT  -=BEHIND
							IF car2_infront_car1 < 0.0
								criminal2_car_speed = 0.0
							ENDIF
						ENDIF
					ELSE
						CAR_WANDER_RANDOMLY	criminal2_car
						break_convoy_flag = 1
					ENDIF
				ENDIF

				IF criminal2_car_speed = 0.0
					SET_CAR_TEMP_ACTION criminal2_car TEMPACT_WAIT 17
				ELSE
					SET_CAR_CRUISE_SPEED criminal2_car criminal2_car_speed
				ENDIF

			ENDIF
		ELSE
			if DOES_GROUP_EXIST criminal2_group
				SET_GROUP_DEFAULT_TASK_ALLOCATOR criminal2_group DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS
			endif
			criminal2_in_car_flag = -1
			GOSUB criminal2_group_breaking
			IF game_timer > criminal2_timera
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer criminal2 40.0 40.0 0
					IF NOT IS_CHAR_IN_ANY_CAR scplayer
						GET_SCRIPT_TASK_STATUS criminal2 TASK_KILL_CHAR_ON_FOOT task_status
						IF task_status = FINISHED_TASK
							TASK_KILL_CHAR_ON_FOOT criminal2 scplayer
						ENDIF
					ELSE
						IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer criminal2 25.0 25.0 0
							GET_SCRIPT_TASK_STATUS criminal2 TASK_KILL_CHAR_ON_FOOT task_status
							IF task_status = FINISHED_TASK
								TASK_KILL_CHAR_ON_FOOT criminal2 scplayer
							ENDIF
						ELSE
							gosub_ped = criminal2
							IF NOT IS_CAR_DEAD criminal2_car
								IF IS_CAR_HEALTH_GREATER criminal2_car 400
									IF LOCATE_CHAR_ANY_MEANS_CAR_2D	criminal2 criminal2_car 40.0 40.0 0
										GET_SCRIPT_TASK_STATUS criminal2 TASK_ENTER_CAR_AS_DRIVER task_status
										IF task_status = FINISHED_TASK
											TASK_ENTER_CAR_AS_DRIVER criminal2 criminal2_car -2
										ENDIF
									ELSE
										GOSUB criminal_steal_a_car
									ENDIF
								ELSE
									GOSUB criminal_steal_a_car
								ENDIF
							ELSE
								GOSUB criminal_steal_a_car
							ENDIF
						ENDIF
					ENDIF
				ELSE
					gosub_ped = criminal2
					IF NOT IS_CAR_DEAD criminal2_car
						IF IS_CAR_HEALTH_GREATER criminal2_car 400
							IF LOCATE_CHAR_ANY_MEANS_CAR_2D	criminal2 criminal2_car 40.0 40.0 0
								GET_SCRIPT_TASK_STATUS criminal2 TASK_ENTER_CAR_AS_DRIVER task_status
								IF task_status = FINISHED_TASK
									TASK_ENTER_CAR_AS_DRIVER criminal2 criminal2_car -2
								ENDIF
							ELSE
								GOSUB criminal_steal_a_car
							ENDIF
						ELSE
							GOSUB criminal_steal_a_car
						ENDIF
					ELSE
						GOSUB criminal_steal_a_car
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ELSE
		REMOVE_BLIP criminal2_blip
		MARK_CHAR_AS_NO_LONGER_NEEDED criminal2
		criminal2 = -1
		IF criminal2_dead_flag = 0
			++ total_criminals_killed			
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal2_dead_flag = 1
		ENDIF
		if does_group_exist criminal2_group
			IF NOT IS_CHAR_DEAD	criminal2_thug1
				IF NOT IS_GROUP_MEMBER criminal2_thug1 criminal2_group
					GET_SCRIPT_TASK_STATUS criminal2_thug1 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2_thug1 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal2_thug1 criminal2_group
						criminal2 = criminal2_thug1
						criminal2_thug1 = -1
						criminal2_dead_flag = 0
						criminal2_thug1_dead_flag = 1
						criminal2_blip = criminal2_thug1_blip
						criminal2_thug1_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal2_thug1_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug1
				criminal2_thug1 = -1
			ENDIF
			IF NOT IS_CHAR_DEAD	criminal2_thug2
				IF NOT IS_GROUP_MEMBER criminal2_thug2 criminal2_group
					GET_SCRIPT_TASK_STATUS criminal2_thug2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2_thug2 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal2_thug2 criminal2_group
						criminal2 = criminal2_thug2
						criminal2_thug2 = -1
						criminal2_dead_flag = 0
						criminal2_thug2_dead_flag = 1
						criminal2_blip = criminal2_thug2_blip
						criminal2_thug2_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal2_thug2_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug2
				criminal2_thug2 = -1
			ENDIF
			IF NOT IS_CHAR_DEAD	criminal2_thug3
				IF NOT IS_GROUP_MEMBER criminal2_thug3 criminal2_group
					GET_SCRIPT_TASK_STATUS criminal2_thug3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2_thug3 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal2_thug3 criminal2_group
						criminal2 = criminal2_thug3
						criminal2_thug3 = -1
						criminal2_dead_flag = 0
						criminal2_thug3_dead_flag = 1
						criminal2_blip = criminal2_thug3_blip
						criminal2_thug3_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal2_thug3_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug3
				criminal2_thug3 = -1
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal2_thug1
		IF criminal2_thug1_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug1
			criminal2_thug1 = -1
			REMOVE_BLIP criminal2_thug1_blip
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal2_thug1_dead_flag = 1
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal2_thug2
		IF criminal2_thug2_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug2
			criminal2_thug2 = -1
			REMOVE_BLIP criminal2_thug2_blip
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal2_thug2_dead_flag = 1
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal2_thug3
		IF criminal2_thug3_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug3
			criminal2_thug3 = -1
			REMOVE_BLIP criminal2_thug3_blip
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal2_thug3_dead_flag = 1
		ENDIF
	ENDIF
	IF criminal2_team_dead_flag = 0
		IF criminal2_dead_flag = 1
		AND	criminal2_thug1_dead_flag = 1
		AND	criminal2_thug2_dead_flag = 1
		AND	criminal2_thug3_dead_flag = 1
			criminal2_team_dead_flag = 1
		ENDIF
	ENDIF
ENDIF

/////
////criminal3
/////
IF frame_counter = 2
	IF NOT IS_CHAR_DEAD criminal3
		IF IS_CHAR_SITTING_IN_ANY_CAR criminal3
			
			MARK_CAR_AS_NO_LONGER_NEEDED criminal3_car
			STORE_CAR_CHAR_IS_IN_NO_SAVE criminal3 criminal3_car
			
			IF criminal3_in_car_flag = -1
				GET_NUMBER_OF_FOLLOWERS criminal3 num_of_followers
				IF num_of_followers > 0
					if DOES_GROUP_EXIST criminal3_group
						SET_GROUP_DEFAULT_TASK_ALLOCATOR criminal3_group DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR
					endif
					criminal3_timera = game_timer + 2000
				ELSE
					criminal3_timera = game_timer
				ENDIF
				criminal3_in_car_flag = 0
			ENDIF
			
			IF criminal3_in_car_flag = 0
				IF game_timer > criminal3_timera
					SET_CAR_CRUISE_SPEED criminal3_car 100.0
					SET_CAR_DRIVING_STYLE criminal3_car 2
					IF break_convoy_flag = 0
						IF NOT IS_CAR_DEAD criminal2_car
							CAR_WANDER_RANDOMLY	criminal3_car
						ELSE
							break_convoy_flag = 1
						ENDIF
					ENDIF
					IF break_convoy_flag = 1
						MARK_CAR_AS_CONVOY_CAR criminal3_car FALSE
						CAR_WANDER_RANDOMLY	criminal3_car
					ENDIF
					criminal3_car_stuck_timer_start = game_timer
					criminal3_in_car_flag = 1
					criminal3_car_stuck_flag = 0
				ENDIF
			ENDIF
			
			IF criminal3_in_car_flag > 0

				IF IS_CHAR_IN_CAR scplayer criminal3_car
					GET_SCRIPT_TASK_STATUS criminal3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3 scplayer
					ENDIF
				ENDIF

				gosub_stuck_car = criminal3_car
				car_stuck_flag = criminal3_car_stuck_flag
				stuck_timer_start = criminal3_car_stuck_timer_start
				stuck_x = criminal3_car_stuck_x
				stuck_y = criminal3_car_stuck_y
				stuck_z = criminal3_car_stuck_z
				IF criminal3_car_speed > 0.0
					GOSUB criminal_car_stuck_checks
				ENDIF
				criminal3_car_stuck_flag = car_stuck_flag
				criminal3_car_stuck_timer_start = stuck_timer_start
				criminal3_car_stuck_x = stuck_x
				criminal3_car_stuck_y = stuck_y
				criminal3_car_stuck_z = stuck_z
				
				IF criminal3_car_stuck_flag = -9
					GET_SCRIPT_TASK_STATUS criminal3 TASK_FLEE_POINT task_status
					IF task_status = FINISHED_TASK
						GET_CHAR_COORDINATES criminal3 x y z
						TASK_FLEE_POINT criminal3 x y z 15.0 1500
					ENDIF
					criminal3_car_stuck_flag = 0
				ENDIF
		
				IF NOT IS_CAR_HEALTH_GREATER criminal3_car 400
					GET_SCRIPT_TASK_STATUS criminal3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3 scplayer
					ENDIF
				ENDIF

				IF NOT IS_CAR_HEALTH_GREATER criminal3_car 251
					GET_SCRIPT_TASK_STATUS criminal3 TASK_FLEE_POINT task_status
					IF task_status = FINISHED_TASK
						GET_CHAR_COORDINATES criminal3 x y z
						TASK_FLEE_POINT criminal3 x y z 15.0 1500
					ENDIF
					criminal3_timera = game_timer + 2000
				ELSE
					criminal3_timera = game_timer
				ENDIF
				
				GOSUB criminal3_group_breaking
				
				GET_CHAR_COORDINATES criminal3 criminal3_x criminal3_y z
				x_temp = player_x - criminal3_x //	dist = (1_x - 2_x)~2  +	(1_y - 2_y)~2
				y_temp = player_y - criminal3_y
				x_temp = x_temp * x_temp
				y_temp = y_temp * y_temp
				sum_x_y_temp = x_temp + y_temp
				SQRT sum_x_y_temp sum_x_y_temp
				
				criminal3_car_speed = 1000.0 / sum_x_y_temp

				IF criminal3_car_speed > 100.0
					criminal3_car_speed = 100.0
				ENDIF
				IF criminal3_car_speed < 15.0
					criminal3_car_speed = 15.0
				ENDIF

				IF break_convoy_flag = 0
					IF NOT IS_CAR_DEAD criminal2_car
						gosub_car_to_slow = criminal3_car
						gosub_target_entity = criminal2_car
						GOSUB slow_criminal_if_close 
						criminal3_car_speed = gosub_car_to_slow_speed

						//THIS BIT OF CODE CHECKS TO SEE IF CAR2 IS INFRONT OF CAR3
						GET_CAR_FORWARD_X criminal3_car forward_x
						GET_CAR_FORWARD_Y criminal3_car forward_y
						GET_CAR_COORDINATES criminal2_car criminal2_car_x criminal2_car_y z
						GET_CAR_COORDINATES	criminal3_car criminal3_car_x criminal3_car_y z
						// IF (((forward_x * (car3_x - car2_x)) + (forward_y * (car3_y - car2_y)>) IS GREATER THAN 0.0 CAR IS IN FRONT
						temp_float_x = criminal3_car_x - criminal2_car_x
						forward_x = forward_x * temp_float_x
						temp_float_y = criminal3_car_y - criminal2_car_y
						forward_y = forward_y * temp_float_y
						car2_infront_car3 = forward_x + forward_y

						//THIS BIT OF CODE CHECKS TO SEE IF CAR3 IS INFRONT OF CAR2
						GET_CAR_FORWARD_X criminal2_car forward_x
						GET_CAR_FORWARD_Y criminal2_car forward_y
						GET_CAR_COORDINATES criminal3_car criminal3_car_x criminal3_car_y z
						GET_CAR_COORDINATES	criminal2_car criminal2_car_x criminal2_car_y z
						// IF (((forward_x * (car2_x - car3_x)) + (forward_y * (car2_y - car3_y)>) IS GREATER THAN 0.0 CAR IS IN FRONT
						temp_float_x = criminal2_car_x - criminal3_car_x
						forward_x = forward_x * temp_float_x
						temp_float_y = criminal2_car_y - criminal3_car_y
						forward_y = forward_y * temp_float_y
						car3_infront_car2 = forward_x + forward_y

						IF car2_infront_car3 > 0.0// +=INFRONT  -=BEHIND
							IF car3_infront_car2 < 0.0
								criminal3_car_speed = 0.0
							ENDIF
						ENDIF
					ELSE
						CAR_WANDER_RANDOMLY	criminal3_car
						break_convoy_flag = 1
					ENDIF
				ENDIF

				IF criminal3_car_speed = 0.0
					SET_CAR_TEMP_ACTION criminal3_car TEMPACT_WAIT 17
				ELSE
					SET_CAR_CRUISE_SPEED criminal3_car criminal3_car_speed
				ENDIF

			ENDIF
		ELSE
			if DOES_GROUP_EXIST criminal3_group
				SET_GROUP_DEFAULT_TASK_ALLOCATOR criminal3_group DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS
			endif
			criminal3_in_car_flag = -1
			GOSUB criminal3_group_breaking
			IF game_timer > criminal3_timera
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer criminal3 40.0 40.0 0
					IF NOT IS_CHAR_IN_ANY_CAR scplayer
						GET_SCRIPT_TASK_STATUS criminal3 TASK_KILL_CHAR_ON_FOOT task_status
						IF task_status = FINISHED_TASK
							TASK_KILL_CHAR_ON_FOOT criminal3 scplayer
						ENDIF
					ELSE
						IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer criminal3 25.0 25.0 0
							GET_SCRIPT_TASK_STATUS criminal3 TASK_KILL_CHAR_ON_FOOT task_status
							IF task_status = FINISHED_TASK
								TASK_KILL_CHAR_ON_FOOT criminal3 scplayer
							ENDIF
						ELSE
							gosub_ped = criminal3
							IF NOT IS_CAR_DEAD criminal3_car
								IF IS_CAR_HEALTH_GREATER criminal3_car 400
									IF LOCATE_CHAR_ANY_MEANS_CAR_2D	criminal3 criminal3_car 40.0 40.0 0
										GET_SCRIPT_TASK_STATUS criminal3 TASK_ENTER_CAR_AS_DRIVER task_status
										IF task_status = FINISHED_TASK
											TASK_ENTER_CAR_AS_DRIVER criminal3 criminal3_car -2
										ENDIF
									ELSE
										GOSUB criminal_steal_a_car
									ENDIF
								ELSE
									GOSUB criminal_steal_a_car
								ENDIF
							ELSE
								GOSUB criminal_steal_a_car
							ENDIF
						ENDIF
					ENDIF
				ELSE
					gosub_ped = criminal3
					IF NOT IS_CAR_DEAD criminal3_car
						IF IS_CAR_HEALTH_GREATER criminal3_car 400
							IF LOCATE_CHAR_ANY_MEANS_CAR_2D	criminal3 criminal3_car 40.0 40.0 0
								GET_SCRIPT_TASK_STATUS criminal3 TASK_ENTER_CAR_AS_DRIVER task_status
								IF task_status = FINISHED_TASK
									TASK_ENTER_CAR_AS_DRIVER criminal3 criminal3_car -2
								ENDIF
							ELSE
								GOSUB criminal_steal_a_car
							ENDIF
						ELSE
							GOSUB criminal_steal_a_car
						ENDIF
					ELSE
						GOSUB criminal_steal_a_car
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ELSE
		REMOVE_BLIP criminal3_blip
		MARK_CHAR_AS_NO_LONGER_NEEDED criminal3
		criminal3 = -1
		IF criminal3_dead_flag = 0
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal3_dead_flag = 1
		ENDIF
		if does_group_exist	criminal3_group
			IF NOT IS_CHAR_DEAD	criminal3_thug1
				IF NOT IS_GROUP_MEMBER criminal3_thug1 criminal3_group
					GET_SCRIPT_TASK_STATUS criminal3_thug1 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3_thug1 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal3_thug1 criminal3_group
						criminal3 = criminal3_thug1
						criminal3_thug1 = -1
						criminal3_dead_flag = 0
						criminal3_thug1_dead_flag = 1
						criminal3_blip = criminal3_thug1_blip
						criminal3_thug1_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal3_thug1_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug1
				criminal3_thug1 = -1
			ENDIF
			IF NOT IS_CHAR_DEAD	criminal3_thug2
				IF NOT IS_GROUP_MEMBER criminal3_thug2 criminal3_group
					GET_SCRIPT_TASK_STATUS criminal3_thug2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3_thug2 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal3_thug2 criminal3_group
						criminal3 = criminal3_thug2
						criminal3_thug2 = -1
						criminal3_dead_flag = 0
						criminal3_thug2_dead_flag = 1
						criminal3_blip = criminal3_thug2_blip
						criminal3_thug2_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal3_thug2_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug2
				criminal3_thug2 = -1
			ENDIF
			IF NOT IS_CHAR_DEAD	criminal3_thug3
				IF NOT IS_GROUP_MEMBER criminal3_thug3 criminal3_group
					GET_SCRIPT_TASK_STATUS criminal3_thug3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3_thug3 scplayer
					ENDIF
				ELSE
					IF IS_GROUP_LEADER criminal3_thug3 criminal3_group
						criminal3 = criminal3_thug3
						criminal3_thug3 = -1
						criminal3_dead_flag = 0
						criminal3_thug3_dead_flag = 1
						criminal3_blip = criminal3_thug3_blip
						criminal3_thug3_blip = -1
					ENDIF
				ENDIF
			ELSE
				remove_blip	criminal3_thug3_blip
				MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug3
				criminal3_thug3 = -1
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal3_thug1
		IF criminal3_thug1_dead_flag = 0
			REMOVE_BLIP criminal3_thug1_blip
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug1
			criminal3_thug1 = -1
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal3_thug1_dead_flag = 1
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal3_thug2
		IF criminal3_thug2_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug2
			criminal3_thug2 = -1
			REMOVE_BLIP criminal3_thug2_blip
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal3_thug2_dead_flag = 1
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	criminal3_thug3
		IF criminal3_thug3_dead_flag = 0
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug3
			criminal3_thug3 = -1
			REMOVE_BLIP criminal3_thug3_blip
			++ total_criminals_killed
			INCREMENT_INT_STAT CRIMINALS_CAUGHT	1
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
			criminal3_thug3_dead_flag = 1
		ENDIF
	ENDIF
	IF criminal3_team_dead_flag = 0
		IF criminal3_dead_flag = 1
		AND	criminal3_thug1_dead_flag = 1
		AND	criminal3_thug2_dead_flag = 1
		AND	criminal3_thug3_dead_flag = 1
			criminal3_team_dead_flag = 1
		ENDIF
	ENDIF
ENDIF

IF criminal1_team_dead_flag = 1
AND criminal2_team_dead_flag = 1
AND criminal3_team_dead_flag = 1
	GOTO mission_copcar_passed
ENDIF

GOTO copcar_oddjob_loop

mission_copcar_passed:

REGISTER_inT_STAT VIGILANTE_LEVEL copcar_print_level

++ copcar_level

IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
OR IS_CHAR_IN_MODEL scplayer HUNTER
//or IS_CHAR_IN_MODEL scplayer HYDRA
	STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer players_cop_car
	SET_CAN_BURST_CAR_TYRES players_cop_car FALSE
	GET_CAR_HEALTH players_cop_car players_cop_car_health
	players_cop_car_health += 100
	SET_CAR_HEALTH players_cop_car players_cop_car_health
ENDIF

MARK_CAR_AS_NO_LONGER_NEEDED criminal1_car
MARK_CHAR_AS_NO_LONGER_NEEDED criminal1
MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug1
MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug2
MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug3

MARK_CAR_AS_NO_LONGER_NEEDED criminal2_car
MARK_CHAR_AS_NO_LONGER_NEEDED criminal2
MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug1
MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug2
MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug3

MARK_CAR_AS_NO_LONGER_NEEDED criminal3_car
MARK_CHAR_AS_NO_LONGER_NEEDED criminal3
MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug1
MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug2
MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug3

REMOVE_BLIP criminal1_blip
REMOVE_BLIP criminal2_blip
REMOVE_BLIP criminal3_blip

REMOVE_BLIP criminal1_thug1_blip 
REMOVE_BLIP criminal1_thug2_blip 
REMOVE_BLIP criminal1_thug3_blip
REMOVE_BLIP criminal2_thug1_blip 
REMOVE_BLIP criminal2_thug2_blip 
REMOVE_BLIP criminal2_thug3_blip
REMOVE_BLIP criminal3_thug1_blip 
REMOVE_BLIP criminal3_thug2_blip 
REMOVE_BLIP criminal3_thug3_blip

REMOVE_GROUP criminal1_group
REMOVE_GROUP criminal2_group
REMOVE_GROUP criminal3_group

vigilante_score	= copcar_level * copcar_level
vigilante_score *= 50
FORCE_BIG_MESSAGE_AND_COUNTER TRUE

PRINT_WITH_NUMBER_BIG C_PASS vigilante_score 5000 5  // THREAT~n~ELIMINATED:~n~~w~$~1~

ADD_SCORE player1 vigilante_score

IF done_copcar_progress = 0
	IF copcar_level = 12
		
		PRINT_HELP C_COMP1  // Vigilante mission level 12 complete: Your max Body Armor increased to 150%
		PLAYER_MADE_PROGRESS 1
		INCREASE_PLAYER_MAX_ARMOUR player1 50
		ADD_ARMOUR_TO_CHAR scplayer 150
		REGISTER_ODDJOB_MISSION_PASSED
		PLAY_MISSION_PASSED_TUNE 2
		done_copcar_progress = 1
	ENDIF
ENDIF

WHILE NOT IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
AND NOT IS_CHAR_IN_MODEL scplayer HUNTER
//and not IS_CHAR_IN_MODEL scplayer HYDRA
	IF game_time_flag = 0
		GET_GAME_TIMER game_timer_start
		IF cop_time_limit > 60000 
			copcar_timer = 60000
		ELSE
			copcar_timer = cop_time_limit
		ENDIF 
		game_time_flag = 1
	ENDIF
	GET_GAME_TIMER game_time_present
	game_time_difference = game_time_present - game_timer_start
	copcar_timer -= game_time_difference
	game_timer_start = game_time_present
	timer_in_secs = copcar_timer / 1000
	IF timer_in_secs < 1
		timer_in_secs = 0
		IF in_copcar_nice_timer < game_time_present
			PRINT_NOW C_TIME 3000 1//"Your time as a law enforcer is over!"
			copcar_cancelled_flag = 1
			GOTO copcar_failed
		ENDIF
	ELSE
		in_copcar_nice_timer = game_time_present + 1000
	ENDIF
	
	IF timer_in_secs > -1
		if timer_in_secs = 1
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
			PRINT_WITH_NUMBER_NOW COPCA_T timer_in_secs 200 1	//You have ~1~ seconds to return to the car before the mission ends.
		else
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
			PRINT_WITH_NUMBER_NOW COPCART timer_in_secs 200 1	//You have ~1~ seconds to return to the car before the mission ends.
		endif
	ENDIF
	WAIT 0

ENDWHILE

FREEZE_ONSCREEN_TIMER TRUE

IF IS_CHAR_IN_ANY_CAR scplayer
	STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer players_cop_car
	SET_CAN_BURST_CAR_TYRES players_cop_car FALSE
	GET_CAR_HEALTH players_cop_car players_cop_car_health
	players_cop_car_health += 200
	SET_CAR_HEALTH players_cop_car players_cop_car_health
ENDIF

IF copcar_level < 13
	wanted_level_multiplier -= 0.075
	if wanted_level_multiplier < 0.0
		wanted_level_multiplier = 0.0
	endif
	SET_WANTED_MULTIPLIER wanted_level_multiplier
ENDIF


GOTO next_criminal_to_kill		



// Mission copcar failed

copcar_failed:
//PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
//PRINT_NOW C_TIME 3000 1//"Your time as a law enforcer is over!"
RETURN

   

// mission cleanup

mission_cleanup_copcar:

flag_player_on_mission = 0

REMOVE_BLIP criminal1_blip
REMOVE_BLIP criminal2_blip
REMOVE_BLIP criminal3_blip

REMOVE_BLIP criminal1_thug1_blip 
REMOVE_BLIP criminal1_thug2_blip 
REMOVE_BLIP criminal1_thug3_blip
REMOVE_BLIP criminal2_thug1_blip 
REMOVE_BLIP criminal2_thug2_blip 
REMOVE_BLIP criminal2_thug3_blip
REMOVE_BLIP criminal3_thug1_blip 
REMOVE_BLIP criminal3_thug2_blip 
REMOVE_BLIP criminal3_thug3_blip

REMOVE_GROUP criminal1_group
REMOVE_GROUP criminal2_group
REMOVE_GROUP criminal3_group

CLEAR_ONSCREEN_TIMER cop_time_limit
CLEAR_ONSCREEN_COUNTER copcar_print_level
CLEAR_ONSCREEN_COUNTER total_criminals_killed
MARK_MODEL_AS_NO_LONGER_NEEDED random_car_model
MARK_MODEL_AS_NO_LONGER_NEEDED random_ped_model
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED BAT

GET_GAME_TIMER timer_mobile_start

MISSION_HAS_FINISHED
RETURN




/////////////////////////////////////////////////////////////////////////////
request_random_car_model:// (MUST mark model not needed as soon as used) ////
/////////////////////////////////////////////////////////////////////////////

IF copcar_level = 0
	GENERATE_RANDOM_INT_IN_RANGE 0 91 random_int
	if random_int > 7
	and random_int < 17
		GENERATE_RANDOM_INT_IN_RANGE 0 91 random_int
	endif
ELSE
	IF copcar_level = 1
		GENERATE_RANDOM_INT_IN_RANGE 2 91 random_int
		if random_int > 7
		and random_int < 17
			GENERATE_RANDOM_INT_IN_RANGE 2 91 random_int
		endif
	ELSE
		GENERATE_RANDOM_INT_IN_RANGE 67 91 random_int
	ENDIF
ENDIF

random_car_model = vehicles[random_int]

REQUEST_MODEL random_car_model

////////////////////////////////////////////////////////////////////////////
RETURN// random_car_model ////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
//select_a_weapon:// PED ///////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

//	IF NOT IS_PLAYER_IN_ANY_CAR player1
//		IF LOCATE_PLAYER_ANY_MEANS_CHAR_2D player1 ped 25.0 25.0 0
//			GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_SHOTGUN 9999
//			RETURN
//		ENDIF
//		IF LOCATE_PLAYER_ANY_MEANS_CHAR_2D player1 ped 60.0 60.0 0
//			GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_AK47 9999//AK47
//			RETURN
//		ENDIF
//		GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_MICRO_UZI 9999
//		RETURN
//	ENDIF
//
//	IF IS_PLAYER_IN_ANY_CAR player1
//		IF NOT LOCATE_PLAYER_ANY_MEANS_CHAR_2D player1 ped 20.0 20.0 0
//			IF LOCATE_PLAYER_ANY_MEANS_CHAR_2D player1 ped 40.0 40.0 0
//				STORE_CAR_PLAYER_IS_IN_NO_SAVE player1 players_cop_car
//				GET_CAR_SPEED players_cop_car players_cop_car_speed
//				IF players_cop_car_speed < 18.0
//					GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_GRENADE 9999
//					RETURN
//				ENDIF
//			ENDIF
//		ENDIF
//		IF LOCATE_PLAYER_ANY_MEANS_CHAR_2D player1 ped 30.0 30.0 0
//			GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_SHOTGUN 9999
//			RETURN
//		ELSE
//			GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_AK47 9999
//			RETURN
//		ENDIF
//	ENDIF					
		
////////////////////////////////////////////////////////////////////////////
//RETURN////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
slow_criminal_if_close: ////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

IF gosub_target_entity = -1
	GET_CHAR_COORDINATES scplayer gosub_target_car_x gosub_target_car_y temp_float_x
ELSE
	GET_CAR_COORDINATES gosub_target_entity gosub_target_car_x	gosub_target_car_y temp_float_x
ENDIF

GET_CAR_COORDINATES gosub_car_to_slow gosub_car_to_slow_x gosub_car_to_slow_y temp_float_x

temp_float_x = gosub_car_to_slow_x - gosub_target_car_x //	dist = (1_x - 2_x)~2  +	(1_y - 2_y)~2
temp_float_y = gosub_car_to_slow_y - gosub_target_car_y
temp_float_x = temp_float_x * temp_float_x
temp_float_y = temp_float_y * temp_float_y
temp_float_x = temp_float_x + temp_float_y
SQRT temp_float_x temp_float_x

gosub_car_to_slow_speed = temp_float_x + 3.0
IF gosub_car_to_slow_speed > 100.0
	gosub_car_to_slow_speed = 100.0
ENDIF
IF gosub_car_to_slow_speed < 0.0
	gosub_car_to_slow_speed = 0.0
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
criminal_steal_a_car: //////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

IF NOT IS_CHAR_DEAD	gosub_ped
	
	GET_CHAR_COORDINATES gosub_ped coord_1_x coord_1_y coord_1_z

	IF heading = 919.9
		CREATE_CAR random_car_model	coord_1_x coord_1_y coord_1_z car
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer gosub_ped 40.0 40.0 0
		coord_2_x = coord_1_x + 40.0
		coord_2_y = coord_1_y + 40.0
		coord_1_x = coord_1_x - 40.0
		coord_1_y = coord_1_y - 40.0
		MARK_CAR_AS_NO_LONGER_NEEDED car
		car = -1
		GET_RANDOM_CAR_OF_TYPE_IN_AREA coord_1_x coord_1_y coord_2_x coord_2_y -1 car
		IF car = -1
		OR criminal1_car = car
			GET_SCRIPT_TASK_STATUS gosub_ped TASK_KILL_CHAR_ON_FOOT task_status
			IF task_status = FINISHED_TASK
				TASK_KILL_CHAR_ON_FOOT gosub_ped scplayer
			ENDIF
		ELSE
			IF IS_CAR_HEALTH_GREATER car 400
				GET_NUMBER_OF_FOLLOWERS gosub_ped num_of_followers
				GET_MAXIMUM_NUMBER_OF_PASSENGERS car max_passengers
				IF NOT num_of_followers > max_passengers
					GET_SCRIPT_TASK_STATUS gosub_ped TASK_ENTER_CAR_AS_DRIVER task_status
					IF task_status = FINISHED_TASK
						LOCK_CAR_DOORS car CARLOCK_UNLOCKED
						SET_CAR_CRUISE_SPEED car 0.0
						TASK_ENTER_CAR_AS_DRIVER gosub_ped car -2
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS gosub_ped TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT gosub_ped scplayer
					ENDIF
				ENDIF
			ELSE
				GET_SCRIPT_TASK_STATUS gosub_ped TASK_KILL_CHAR_ON_FOOT task_status
				IF task_status = FINISHED_TASK
					TASK_KILL_CHAR_ON_FOOT gosub_ped scplayer
				ENDIF
				MARK_CAR_AS_NO_LONGER_NEEDED car
				car = -1
			ENDIF
		ENDIF
	ELSE
		GET_CLOSEST_CAR_NODE_WITH_HEADING coord_1_x coord_1_y coord_1_z coord_1_x coord_1_y coord_1_z heading
		IF NOT IS_POINT_ON_SCREEN coord_1_x coord_1_y coord_1_z 3.0
			IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY coord_1_x coord_1_y coord_1_z 3.0 3.0 2.0
				IF HAS_MODEL_LOADED	random_car_model
					GET_SCRIPT_TASK_STATUS gosub_ped TASK_ENTER_CAR_AS_DRIVER task_status
					IF task_status = FINISHED_TASK
						MARK_CAR_AS_NO_LONGER_NEEDED car
						CREATE_CAR random_car_model	coord_1_x coord_1_y coord_1_z car
						IF IS_CAR_HEALTH_GREATER car 800
							SET_CAR_HEALTH car 800
						ENDIF
//						SET_CAR_DRIVING_STYLE car 2
//						SET_CAR_AVOID_LEVEL_TRANSITIONS car TRUE
//						SET_LOAD_COLLISION_FOR_CAR_FLAG car FALSE
						SET_CAR_HEADING	car heading
						TASK_ENTER_CAR_AS_DRIVER gosub_ped car -2
					ENDIF
				ENDIF
			ELSE
				IF LOCATE_CHAR_ON_FOOT_3D gosub_ped coord_1_x coord_1_y coord_1_z 3.0 3.0 2.0 0
					GET_SCRIPT_TASK_STATUS gosub_ped TASK_GO_STRAIGHT_TO_COORD task_status
					IF task_status = FINISHED_TASK
						GET_CLOSEST_CHAR_NODE coord_1_x coord_1_y coord_1_z coord_1_x coord_1_y coord_1_z
						TASK_GO_STRAIGHT_TO_COORD gosub_ped coord_1_x coord_1_y	coord_1_z PEDMOVE_RUN -2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
//ELSE
//	BREAKPOINT gosub_ped_is_dead
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
criminal_car_stuck_checks://////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

GET_GAME_TIMER game_time
GET_AREA_VISIBLE temp_integer_1
if temp_integer_1 = 0
	IF NOT IS_CAR_DEAD gosub_stuck_car
		IF IS_CAR_UPSIDEDOWN gosub_stuck_car
		AND IS_CAR_STOPPED gosub_stuck_car
			break_convoy_flag = 1
			IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer gosub_stuck_car 90.0 90.0 0
				SET_UPSIDEDOWN_CAR_NOT_DAMAGED gosub_stuck_car FALSE
				car_stuck_flag = -9
			ELSE
				IF NOT IS_CAR_ON_SCREEN gosub_stuck_car
					GET_CAR_COORDINATES gosub_stuck_car coord_c1_x coord_c1_y coord_c1_z
					GET_CLOSEST_CAR_NODE_WITH_HEADING coord_c1_x coord_c1_y coord_c1_z coord_c1_x coord_c1_y coord_c1_z heading
					IF NOT IS_POINT_ON_SCREEN coord_c1_x coord_c1_y coord_c1_z 4.0
						SET_CAR_COORDINATES gosub_stuck_car coord_c1_x coord_c1_y coord_c1_z
						SET_CAR_HEADING	gosub_stuck_car heading
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF LOCATE_CAR_3D gosub_stuck_car stuck_x stuck_y stuck_z 4.0 4.0 4.0 0
			IF car_stuck_flag = 0
				stuck_timer_start = game_time
				car_stuck_flag = 1
			ENDIF

			game_time = game_time - stuck_timer_start
			IF car_stuck_flag = 1
				IF game_time > 8000
					IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer gosub_stuck_car 90.0 90.0 0
						SET_UPSIDEDOWN_CAR_NOT_DAMAGED gosub_stuck_car FALSE
						//stuck_timer_start = game_time
						GET_GAME_TIMER stuck_timer_start
						car_stuck_flag = -9
					ELSE
						IF NOT IS_CAR_ON_SCREEN gosub_stuck_car
							GET_CAR_COORDINATES gosub_stuck_car coord_c1_x coord_c1_y coord_c1_z
							GET_CLOSEST_CAR_NODE_WITH_HEADING coord_c1_x coord_c1_y coord_c1_z coord_c1_x coord_c1_y coord_c1_z heading
							IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY coord_c1_x coord_c1_y coord_c1_z 4.0 4.0 4.0
								IF NOT IS_POINT_ON_SCREEN coord_c1_x coord_c1_y coord_c1_z 4.0
									SET_CAR_COORDINATES gosub_stuck_car coord_c1_x coord_c1_y coord_c1_z
									SET_CAR_HEADING gosub_stuck_car heading
									//stuck_timer_start = game_time
									GET_GAME_TIMER stuck_timer_start
									car_stuck_flag = 0
									break_convoy_flag = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF NOT LOCATE_CAR_3D gosub_stuck_car stuck_x stuck_y stuck_z 4.0 4.0 4.0 0
			GET_CAR_COORDINATES gosub_stuck_car stuck_x stuck_y stuck_z
			car_stuck_flag = 0
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
GET_GAME_TIMER game_timer
return
return
return

////////////////////////////////////////////////////////////////////////////
criminal1_group_breaking:////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

IF NOT IS_CHAR_DEAD	criminal1
	if does_group_exist	criminal1_group
		IF NOT IS_CHAR_DEAD	criminal1_thug1
			IF NOT IS_GROUP_MEMBER criminal1_thug1 criminal1_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal1_thug1 criminal1 30.0 30.0 0
					//BREAKPOINT SET_GROUP_MEMBER_criminal1_thug1
					SET_GROUP_MEMBER criminal1_group criminal1_thug1
				ELSE
					GET_SCRIPT_TASK_STATUS criminal1_thug1 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal1_thug1 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug1
			criminal1_thug1 = -1
		ENDIF

		IF NOT IS_CHAR_DEAD	criminal1_thug2
			IF NOT IS_GROUP_MEMBER criminal1_thug2 criminal1_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal1_thug2 criminal1 30.0 30.0 0
					SET_GROUP_MEMBER criminal1_group criminal1_thug2
				ELSE
					GET_SCRIPT_TASK_STATUS criminal1_thug2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal1_thug2 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug2
			criminal1_thug2 = -1
		ENDIF

		IF NOT IS_CHAR_DEAD	criminal1_thug3
			IF NOT IS_GROUP_MEMBER criminal1_thug3 criminal1_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal1_thug3 criminal1 30.0 30.0 0
					SET_GROUP_MEMBER criminal1_group criminal1_thug3
				ELSE
					GET_SCRIPT_TASK_STATUS criminal1_thug3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal1_thug3 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal1_thug3
			criminal1_thug3 = -1
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
criminal2_group_breaking:////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

IF NOT IS_CHAR_DEAD	criminal2
	if does_group_exist	criminal2_group
		IF NOT IS_CHAR_DEAD	criminal2_thug1
			IF NOT IS_GROUP_MEMBER criminal2_thug1 criminal2_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal2_thug1 criminal2 30.0 30.0 0
					SET_GROUP_MEMBER criminal2_group criminal2_thug1
				ELSE
					GET_SCRIPT_TASK_STATUS criminal2_thug1 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2_thug1 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug1
			criminal2_thug1 = -1
		ENDIF
		
		IF NOT IS_CHAR_DEAD	criminal2_thug2
			IF NOT IS_GROUP_MEMBER criminal2_thug2 criminal2_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal2_thug2 criminal2 30.0 30.0 0
					SET_GROUP_MEMBER criminal2_group criminal2_thug2
				ELSE
					GET_SCRIPT_TASK_STATUS criminal2_thug2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2_thug2 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug2
			criminal2_thug2 = -1
		ENDIF
		
		IF NOT IS_CHAR_DEAD	criminal2_thug3
			IF NOT IS_GROUP_MEMBER criminal2_thug3 criminal2_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal2_thug3 criminal2 30.0 30.0 0
					SET_GROUP_MEMBER criminal2_group criminal2_thug3
				ELSE
					GET_SCRIPT_TASK_STATUS criminal2_thug3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal2_thug3 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal2_thug3
			criminal2_thug3 = -1
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
criminal3_group_breaking:////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

IF NOT IS_CHAR_DEAD	criminal3
	if does_group_exist	criminal3_group
		IF NOT IS_CHAR_DEAD	criminal3_thug1
			IF NOT IS_GROUP_MEMBER criminal3_thug1 criminal3_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal3_thug1 criminal3 30.0 30.0 0
					SET_GROUP_MEMBER criminal3_group criminal3_thug1
				ELSE
					GET_SCRIPT_TASK_STATUS criminal3_thug1 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3_thug1 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug1
			criminal3_thug1 = -1
		ENDIF
		
		IF NOT IS_CHAR_DEAD	criminal3_thug2
			IF NOT IS_GROUP_MEMBER criminal3_thug2 criminal3_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal3_thug2 criminal3 30.0 30.0 0
					SET_GROUP_MEMBER criminal3_group criminal3_thug2
				ELSE
					GET_SCRIPT_TASK_STATUS criminal3_thug2 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3_thug2 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug2
			criminal3_thug2 = -1
		ENDIF
		
		IF NOT IS_CHAR_DEAD	criminal3_thug3
			IF NOT IS_GROUP_MEMBER criminal3_thug3 criminal3_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D criminal3_thug3 criminal3 30.0 30.0 0
					SET_GROUP_MEMBER criminal3_group criminal3_thug3
				ELSE
					GET_SCRIPT_TASK_STATUS criminal3_thug3 TASK_KILL_CHAR_ON_FOOT task_status
					IF task_status = FINISHED_TASK
						TASK_KILL_CHAR_ON_FOOT criminal3_thug3 scplayer
					ENDIF
				ENDIF
			ENDIF
		ELSE
			MARK_CHAR_AS_NO_LONGER_NEEDED criminal3_thug3
			criminal3_thug3 = -1
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
//print_text_for_zone_char_is_in://///////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

//IF NOT IS_CHAR_DEAD	criminal1
//
//	IF IS_CHAR_IN_ZONE criminal1 VICE_C
//		PRINT_STRING_IN_STRING_NOW C_BREIF VICE_C 5000 1 // The criminal is proceeding south in Vice City
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 BEACH1
//		PRINT_STRING_IN_STRING_NOW C_BREIF BEACH1 5000 1 // The criminal is proceeding south in Ocean Beach
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 BEACH2
//		PRINT_STRING_IN_STRING_NOW C_BREIF BEACH2 5000 1 // The criminal is proceeding south in Washington Beach
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 BEACH3
//		PRINT_STRING_IN_STRING_NOW C_BREIF BEACH3 5000 1 // The criminal is proceeding south in Vice Point
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 GOLFC
//		PRINT_STRING_IN_STRING_NOW C_BREIF GOLFC 5000 1 // The criminal is proceeding south in Leaf Links
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 STARI
//		PRINT_STRING_IN_STRING_NOW C_BREIF STARI 5000 1 // The criminal is proceeding south in Starfish Island
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 DOCKS
//		PRINT_STRING_IN_STRING_NOW C_BREIF DOCKS 5000 1 // The criminal is proceeding south in ViceporT
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 HAVANA
//		PRINT_STRING_IN_STRING_NOW C_BREIF HAVANA 5000 1 // The criminal is proceeding south in Little Havana
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 HAITI
//		PRINT_STRING_IN_STRING_NOW C_BREIF HAITI 5000 1 // The criminal is proceeding south in Little Haiti
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 PORNI
//		PRINT_STRING_IN_STRING_NOW C_BREIF PORNI 5000 1 // The criminal is proceeding south in Prawn Island
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 DTOWN
//		PRINT_STRING_IN_STRING_NOW C_BREIF DTOWN 5000 1 // The criminal is proceeding south in Downtown
//	ENDIF
//
//	IF IS_CHAR_IN_ZONE criminal1 A_PORT
//		PRINT_STRING_IN_STRING_NOW C_BREIF A_PORT 5000 1 // The criminal is proceeding south in Escobar International
//	ENDIF
//ENDIF

////////////////////////////////////////////////////////////////////////////
//RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////
setup_ped_threats_etc://////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

SET_CHAR_DECISION_MAKER ped criminal_decisions

SET_CHAR_IS_CHRIS_CRIMINAL ped TRUE
SET_CHAR_ONLY_DAMAGED_BY_PLAYER ped TRUE
SET_CHAR_FORCE_DIE_IN_CAR ped TRUE

SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_CIVMALE		  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_CIVFEMALE	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP			  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_GROVE	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_SMEX	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_NMEX	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_VIET	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_TRIAD	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_MAFIA	  	  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_DEALER		  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MEDIC		  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_FIRE		  
SET_CHAR_RELATIONSHIP ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_BUM			  

IF copcar_level = 0
	GENERATE_RANDOM_INT_IN_RANGE 0 2 random_int
ENDIF

IF copcar_level > 1
	GENERATE_RANDOM_INT_IN_RANGE 0 3 random_int
ENDIF

IF copcar_level > 4
	GENERATE_RANDOM_INT_IN_RANGE 0 4 random_int
ENDIF

IF copcar_level > 6
	GENERATE_RANDOM_INT_IN_RANGE 0 5 random_int
ENDIF

IF copcar_level > 8
	GENERATE_RANDOM_INT_IN_RANGE 0 6 random_int
ENDIF

IF random_int = 0
	GENERATE_RANDOM_INT_IN_RANGE 10 13 random_int
	IF random_int = 10
		GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_BASEBALLBAT 9999
	ENDIF
	IF random_int = 11
		GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_BASEBALLBAT 9999
	ENDIF
ENDIF

IF random_int = 1
	GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_PISTOL 9999
ENDIF

IF random_int = 2
	GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_MICRO_UZI 9999
ENDIF

IF random_int = 3
	GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_AK47 9999
ENDIF

IF random_int = 4
OR random_int = 5
	GIVE_WEAPON_TO_CHAR ped WEAPONTYPE_SHOTGUN 9999
ENDIF


////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////
copcar_cancelled_checks:////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////

IF NOT cop_time_limit = -100
	IF cop_time_limit < 1
		copcar_cancelled_flag = 1
		PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
		PRINT_NOW C_TIME 3000 1//"Your time as a law enforcer is over!"
		RETURN
	ENDIF
ENDIF

IF NOT IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
AND NOT IS_CHAR_IN_MODEL scplayer HUNTER
//and not  IS_CHAR_IN_MODEL scplayer HYDRA

	FREEZE_ONSCREEN_TIMER FALSE
	IF game_time_flag = 0
		GET_GAME_TIMER game_timer_start
		IF cop_time_limit > 60000 
			copcar_timer = 60000
		ELSE
			copcar_timer = cop_time_limit
		ENDIF 
		game_time_flag = 1
	ENDIF
	GET_GAME_TIMER game_time_present
	game_time_difference = game_time_present - game_timer_start
	copcar_timer -= game_time_difference
	game_timer_start = game_time_present
	timer_in_secs = copcar_timer / 1000
	IF timer_in_secs < 1
		timer_in_secs = 0
		IF in_copcar_nice_timer < game_time_present
			PRINT_NOW C_TIME 3000 1//"Your time as a law enforcer is over!"
			copcar_cancelled_flag = 1
			RETURN
		ENDIF
	ELSE
		in_copcar_nice_timer = game_time_present + 1000
	ENDIF
	
	
	IF timer_in_secs > -1
		if timer_in_secs = 1
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
			PRINT_WITH_NUMBER_NOW COPCA_T timer_in_secs 200 1	//You have ~1~ seconds to return to the car before the mission ends.
		else
			ADD_NEXT_MESSAGE_TO_PREVIOUS_BRIEFS	FALSE
			PRINT_WITH_NUMBER_NOW COPCART timer_in_secs 200 1	//You have ~1~ seconds to return to the car before the mission ends.
		endif
	ENDIF

ENDIF

GET_CONTROLLER_MODE controlmode

IF IS_CHAR_IN_ANY_POLICE_VEHICLE scplayer
OR IS_CHAR_IN_MODEL scplayer HUNTER
//or IS_CHAR_IN_MODEL scplayer HYDRA
	STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer players_cop_car
	SET_CAN_BURST_CAR_TYRES players_cop_car FALSE
	
	IF NOT controlmode = 3
		IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			IF mission_end_button = 1
				mission_end_button = 2
			ENDIF
		ELSE
			IF mission_end_button = 0
				mission_end_button = 1
			ENDIF
		ENDIF
	ELSE
		IF IS_BUTTON_PRESSED PAD1 SQUARE
			IF mission_end_button = 1
				mission_end_button = 2
			ENDIF
		ELSE
			IF mission_end_button = 0
				mission_end_button = 1
			ENDIF
		ENDIF
	ENDIF
	game_time_flag = 0
ENDIF

IF mission_end_button = 2
	IF NOT controlmode = 3
		IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			PRINT_NOW C_CANC 3000 1//"Police mission cancelled!"
			copcar_cancelled_flag = 1
			RETURN
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 SQUARE
			PRINT_NOW C_CANC 3000 1//"Police mission cancelled!"
			copcar_cancelled_flag = 1
			RETURN
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////
RETURN//////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////
}


// fcr900 	 //1 - bike	
// nrg500 	 //1 - bike	
// pcj600 	 //2 - bike	
// faggio 	 //2 - bike	
// freeway   //2 - bike	
// sanchez   //2 - bike	
// bf400 	 //2 - bike	
// wayfarer  //2 - bike	
//
// infernus  //2 - fast
// cheetah   //2 - fast
// banshee   //2 - fast	
// turismo   //2 - fast
// zr350  	 //2 - fast	
// comet 	 //2 - fast	
// supergt   //2 - fast
// bullet 	 //2 - fast	
// phoenix   //2 - fast	
//
// landstal   //2
// bravura    //2	
// buffalo    //2	
// manana     //2	
// voodoo     //2	
// pony 	  //2	
// esperant   //2
// bobcat     //2	
// bfinject   //2
// hotknife   //2
// previon    //2	
// stallion   //2
// rumpo  	  //2	
// hermes     //2	
// sabre  	  //2	
// walton     //2	
// burrito    //2	
// camper     //2	
// rancher    //2	
// virgo  	  //2	
// blistac    //2	
// mesa  	  //2	
// majestic   //2
// buccanee   //2
// fortune    //2	
// cadrona    //2	
// feltzer    //2	
// remingtn   //2
// slamvan    //2	
// blade  	  //2	
// clover     //2	
// sadler 	  //2	
// hustler    //2	
// tampa 	  //2	
// yosemite   //2
// windsor    //2	
// uranus 	  //2	
// jester 	  //2	
// elegy 	  //2	
// flash 	  //2	
// savanna    //2	
// broadway   //2
// tornado    //2	
// huntley    //2	
// euros 	  //2	
// club  	  //2	
// picador    //2	
// alpha 	  //2	
// sadlshit   //2	
// romero 	  //2	
//
// peren 	  //4
// sentinel   //4
// moonbeam   //4
// washing    //4	
// premier    //4	
// admiral    //4	
// solair     //4	
// glendale   //4
// oceanic    //4	
// regina     //4	
// greenwoo   //4
// elegant    //4	
// nebula     //4	
// willard    //4	
// vincent    //4	
// intruder   //4
// primo  	  //4	
// sunrise    //4	
// merit  	  //4	
// sultan 	  //4	
// stratum    //4	
// stafford   //4
// emperor	  //4	
// glenshit   //4


//400,	landstal,  2
//401, 	bravura,   2	
//402, 	buffalo,   2	
//404, 	peren,	   4
//405, 	sentinel,  4
//410, 	manana,    2	
//411, 	infernus,  2 - fast
//412, 	voodoo,    2	
//413, 	pony,	   2	
//415, 	cheetah,   2 - fast
//418, 	moonbeam,  4
//419, 	esperant,  2
//421, 	washing,   4	
//422, 	bobcat,    2	
//424, 	bfinject,  2
//426, 	premier,   4	
//429, 	banshee,   2 - fast	
//434, 	hotknife,  2
//436, 	previon,   2	
//439, 	stallion,  2
//440, 	rumpo, 	   2	
//442, 	romero,	   4	
//445, 	admiral,   4	
//451, 	turismo,   2 - fast
//458, 	solair,    4	
//461,	pcj600,	   2 - bike	
//462,	faggio,	   2 - bike	
//463,	freeway,   2 - bike	
//466, 	glendale,  4
//467, 	oceanic,   4	
//468,	sanchez,   2 - bike	
//474, 	hermes,    2	
//475, 	sabre, 	   2	
//477, 	zr350, 	   2 - fast	
//478, 	walton,    2	
//479, 	regina,    4	
//480,	comet,	   2 - fast	
//482, 	burrito,   2	
//483, 	camper,    2	
//489,	rancher,   2	
//491, 	virgo, 	   2	
//492, 	greenwoo,  4
//495,	sandking,  2 - special
//496,	blistac,   2	
//500,	mesa, 	   2	
//506, 	supergt,   2 - fast
//507, 	elegant,   4	
//516, 	nebula,    4	
//517, 	majestic,  2
//518, 	buccanee,  2
//521,	fcr900,	   1 - bike	
//522,	nrg500,	   1 - bike	
//526, 	fortune,   2	
//527, 	cadrona,   2	
//529, 	willard,   4	
//533,	feltzer,   2	
//534, 	remingtn,  2
//535, 	slamvan,   2	
//536, 	blade, 	   2	
//540, 	vincent,   4	
//541, 	bullet,	   2 - fast	
//542, 	clover,    2	
//543,	sadler,	   2	
//545, 	hustler,   2	
//546, 	intruder,  4
//547, 	primo, 	   4	
//549,	tampa,	   2	
//550, 	sunrise,   4	
//551, 	merit, 	   4	
//554,	yosemite,  2
//555,	windsor,   2	
//558,	uranus,	   2	
//559,	jester,	   2	
//560,	sultan,	   4	
//561,	stratum,   4	
//562,	elegy,	   2	
//565,	flash,	   2	
//567, 	savanna,   2	
//575, 	broadway,  2
//576, 	tornado,   2	
//579,	huntley,   2	
//580, 	stafford,  4
//581,	bf400,	   2 - bike	
//585,	emperor	   4	
//586,	wayfarer   2 - bike	
//587, 	euros,	   2	
//589,	club, 	   2	
//600,	picador,   2	
//602,	alpha,	   2	
//603, 	phoenix,   2 - fast	
//604, 	glenshit,  4
//605,	sadlshit,  2	
