MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** Fire missions *********************************** 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_fire

GOSUB failed

MISSION_END
{
// CARS

LVAR_INT onfire_car 
LVAR_INT onfire_car_1 
LVAR_INT onfire_car_2 
LVAR_INT onfire_car_3
LVAR_INT dave_car


// PEOPLE

LVAR_INT onfire_ped 
LVAR_INT onfire_ped_1 
LVAR_INT onfire_ped_2 
LVAR_INT onfire_ped_3 
LVAR_INT onfire_ped_4
LVAR_INT onfire_ped_5 
LVAR_INT onfire_ped_6 
LVAR_INT onfire_ped_7 
LVAR_INT onfire_ped_8 
LVAR_INT onfire_ped_9


//FIRES

LVAR_INT onfire_car_fire 
LVAR_INT onfire_car_1_fire 
LVAR_INT onfire_car_2_fire 
LVAR_INT onfire_car_3_fire
LVAR_INT onfire_ped_fire 
LVAR_INT onfire_ped_1_fire 
LVAR_INT onfire_ped_2_fire 
LVAR_INT onfire_ped_3_fire 
LVAR_INT onfire_ped_4_fire
LVAR_INT onfire_ped_5_fire 
LVAR_INT onfire_ped_6_fire 
LVAR_INT onfire_ped_7_fire 
LVAR_INT onfire_ped_8_fire 
LVAR_INT onfire_ped_9_fire


//BLIPS
LVAR_INT onfire_car_blip 
LVAR_INT onfire_car_1_blip 
LVAR_INT onfire_car_2_blip	
LVAR_INT onfire_car_3_blip
LVAR_INT onfire_ped_blip 
LVAR_INT onfire_ped_1_blip 
LVAR_INT onfire_ped_2_blip	
LVAR_INT onfire_ped_3_blip 
LVAR_INT onfire_ped_4_blip
LVAR_INT onfire_ped_5_blip 
LVAR_INT onfire_ped_6_blip 
LVAR_INT onfire_ped_7_blip 
LVAR_INT onfire_ped_8_blip 
LVAR_INT onfire_ped_9_blip


// COUNTERS AND TIMER

LVAR_INT fail_firetruck_mission 
LVAR_INT intermediate_int	
LVAR_INT score_ft 
LVAR_INT displayed_timer 
LVAR_INT displayed_counter   
LVAR_INT mission_end_button_ft
LVAR_INT total_score 
LVAR_INT players_car_health 
LVAR_INT firetruck_level_minus_4 
LVAR_INT onfire_car_health 
LVAR_INT car_health_test_timer
LVAR_INT fires_needing_extinguishing 
LVAR_INT fires_extinguished_this_round

VAR_INT db_firetruck_level
VAR_INT db_fires_extinguished
VAR_INT	fire_time_limit

// FLAGS

LVAR_INT onfire_car_flag 
LVAR_INT  onfire_ped_flag
LVAR_INT onfire_car_1_flag 
LVAR_INT onfire_car_2_flag	
LVAR_INT onfire_car_3_flag 
LVAR_INT onfire_ped_1_flag 
LVAR_INT onfire_ped_2_flag	
LVAR_INT onfire_ped_3_flag 
LVAR_INT onfire_ped_4_flag
LVAR_INT onfire_ped_5_flag 
LVAR_INT onfire_ped_6_flag 
LVAR_INT onfire_ped_7_flag 
LVAR_INT onfire_ped_8_flag 
LVAR_INT onfire_ped_9_flag
LVAR_INT fire_collision_loaded_car[3] 
LVAR_INT fire_collision_loaded_ped[12]	 
LVAR_INT fire_first_car_created
LVAR_INT fire_safety_flag

//COORD MATHS

LVAR_FLOAT time_divider
LVAR_FLOAT  fire_time_limit_float random_car_heading
LVAR_FLOAT	players_distance_from_fire
LVAR_INT	random_int 
LVAR_INT random_car_model //	players_car


// COORDINATES

LVAR_FLOAT car1_x car1_y car1_z 
LVAR_FLOAT car2_x car2_y car2_z 
LVAR_FLOAT car3_x car3_y car3_z
LVAR_FLOAT db_dx db_dy 
LVAR_FLOAT db_temp_x db_temp_y db_temp_z db_temp_xy db_sum_x_y_temp
LVAR_FLOAT dave_player_x dave_player_y dave_player_z 
LVAR_FLOAT dave_ped_x dave_ped_y dave_ped_z  
LVAR_FLOAT dave_generate_x_pos dave_generate_y_pos 
LVAR_FLOAT dave_generate_x_neg dave_generate_y_neg
LVAR_FLOAT fire_random_x fire_random_y fire_random_z 
LVAR_FLOAT fire_dist_multiplier  
LVAR_FLOAT temp_x temp_y temp_z	temp_heading temp_distance
LVAR_FLOAT fire_coord_iterations


// OTHERS

VAR_TEXT_LABEL $fire_zone_name $fire_zone_name2 	


LVAR_INT fire_generating_coords
LVAR_INT fire_task_status 
LVAR_INT fire_decision


 


// ****************************************Mission Start************************************

mission_start_fire:

flag_player_on_mission = 1
flag_player_on_fire_mission = 1

SCRIPT_NAME firetru

WAIT 0


IF done_firetruck_progress = 0
	REGISTER_MISSION_GIVEN
ENDIF

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY fire_decision	   
LOAD_MISSION_TEXT FIRETRK
SHOW_UPDATE_STATS FALSE

fire_coord_iterations 		= 0.0
fire_time_limit				= 0
score_ft					= 0
displayed_timer				= 0
db_fires_extinguished		= 0
displayed_counter			= 0
total_score					= 0
mission_end_button_ft		= 0
time_divider 				= 7.0
db_firetruck_level 			= 1
fail_firetruck_mission  	= 0
random_car_model 			= 0
intermediate_int 			= 0
players_car_health 			= 0
onfire_car_1 				= -1
onfire_car_1 				= -1
onfire_car_1 				= -1
car_health_test_timer 		= 0

onfire_ped_1_fire = 0
onfire_ped_2_fire = 0
onfire_ped_3_fire = 0
onfire_ped_4_fire = 0
onfire_ped_5_fire = 0
onfire_ped_6_fire = 0
onfire_ped_7_fire = 0
onfire_ped_8_fire = 0
onfire_ped_9_fire = 0


fire_collision_loaded_car[0] = 0
fire_collision_loaded_car[1] = 0
fire_collision_loaded_car[2] = 0

fire_collision_loaded_ped[0]   = 0
fire_collision_loaded_ped[1]   = 0
fire_collision_loaded_ped[2]   = 0
fire_collision_loaded_ped[3]   = 0
fire_collision_loaded_ped[4]   = 0
fire_collision_loaded_ped[5]   = 0
fire_collision_loaded_ped[6]   = 0
fire_collision_loaded_ped[7]   = 0
fire_collision_loaded_ped[8]   = 0
fire_collision_loaded_ped[9]   = 0
fire_collision_loaded_ped[10]  = 0
fire_collision_loaded_ped[11]  = 0

fire_first_car_created 		   = 0



VAR_INT car_or_ped_flag
VAR_INT car_numer_flag
car_numer_flag = 0
car_or_ped_flag = 0






SET_WANTED_MULTIPLIER 0.5



IF flag_player_on_mission = 0
	ADD_BLIP_FOR_COORD x y z onfire_car_blip
	ADD_BLIP_FOR_COORD x y z onfire_car_1_blip
	ADD_BLIP_FOR_COORD x y z onfire_car_2_blip
	ADD_BLIP_FOR_COORD x y z onfire_car_3_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_1_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_2_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_3_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_4_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_5_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_6_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_7_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_8_blip
	ADD_BLIP_FOR_COORD x y z onfire_ped_9_blip


	

	 CREATE_CAR PATRIOT 0.0 0.0 0.0 onfire_car_1
	 CREATE_CHAR PEDTYPE_MISSION1 LAPD1  0.0 0.0 0.0 onfire_ped
	 
	 START_CAR_FIRE  onfire_car_1 onfire_car_fire
	 START_CAR_FIRE  onfire_car_1 onfire_car_1_fire 
	 START_CAR_FIRE  onfire_car_1 onfire_car_2_fire 
	 START_CAR_FIRE  onfire_car_1 onfire_car_3_fire
	 START_CHAR_FIRE onfire_ped onfire_ped_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_1_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_2_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_3_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_4_fire
	 START_CHAR_FIRE onfire_ped onfire_ped_5_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_6_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_7_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_8_fire 
	 START_CHAR_FIRE onfire_ped onfire_ped_9_fire
ENDIF

fire_generation_start:

fires_needing_extinguishing = 0
fires_extinguished_this_round = 0

onfire_car_1_flag = 0
onfire_car_2_flag = 0
onfire_car_3_flag = 0
onfire_ped_1_flag = 0
onfire_ped_2_flag = 0
onfire_ped_3_flag = 0
onfire_ped_4_flag = 0
onfire_ped_5_flag = 0
onfire_ped_6_flag = 0
onfire_ped_7_flag = 0
onfire_ped_8_flag = 0
onfire_ped_9_flag = 0


fire_collision_loaded_car[0] = 0
fire_collision_loaded_car[1] = 0
fire_collision_loaded_car[2] = 0

fire_collision_loaded_ped[0]   = 0
fire_collision_loaded_ped[1]   = 0
fire_collision_loaded_ped[2]   = 0
fire_collision_loaded_ped[3]   = 0
fire_collision_loaded_ped[4]   = 0
fire_collision_loaded_ped[5]   = 0
fire_collision_loaded_ped[6]   = 0
fire_collision_loaded_ped[7]   = 0
fire_collision_loaded_ped[8]   = 0
fire_collision_loaded_ped[9]   = 0
fire_collision_loaded_ped[10]  = 0
fire_collision_loaded_ped[11]  = 0

/////////////////////////////////////////////////////////////////////
//REQUEST CAR MODEL	    CHECK THAT CARS HAVE 4 DOORS (dave b) ////
/////////////////////////////////////////////////////////////////////
GENERATE_RANDOM_INT_IN_RANGE 25 44 random_int

//4-door
IF random_int = 25
	random_car_model = SENTINEL//4-door saloon		125
ENDIF
IF random_int = 26
	random_car_model = MOONBEAM//people carrier		138
ENDIF
IF random_int = 27
	random_car_model = WASHING //4-door saloon		141
ENDIF
IF random_int = 28
	random_car_model = LANDSTAL//4-door offroad		120
ENDIF
IF random_int = 29
	random_car_model = PEREN   //60s station wagon	124
ENDIF
IF random_int = 30
	random_car_model = ADMIRAL  //4-door saloon		165
ENDIF
IF random_int = 31
	random_car_model = GLENDALE//4-door 60s saloon	186
ENDIF
IF random_int = 32
	random_car_model = OCEANIC //4-door 60s saloon	187
ENDIF
IF random_int = 33
	random_car_model = REGINA  //4-door 70s saloon	199
ENDIF
IF random_int = 34
	random_car_model = PONY
ENDIF
IF random_int = 35
	random_car_model = TAXI
ENDIF
IF random_int = 36
	random_car_model = CABBIE
ENDIF
IF random_int = 37
	random_car_model = RUMPO
ENDIF
IF random_int = 38
	random_car_model = MERIT
ENDIF
IF random_int = 39
	random_car_model = PATRIOT
ENDIF
IF random_int = 40
	random_car_model = BURRITO
ENDIF
IF random_int = 41
	random_car_model = INTRUDER
ENDIF
IF random_int = 42
	random_car_model = GREENWOO
ENDIF
IF random_int = 43
	random_car_model = SULTAN
ENDIF

REQUEST_MODEL random_car_model


/////////////////////////////////////////////////////////////////////
//GET RANDOM FIRE COORDS
/////////////////////////////////////////////////////////////////////

next_fire:
WAIT 0 


GOSUB do_cancelled_checks
IF fail_firetruck_mission = 1
	GOTO failed
ENDIF


////////////////////////////////////////////////////////////////////
//WAIT FOR CAR MODEL TO BE LOADED
/////////////////////////////////////////////////////////////////////
WHILE NOT HAS_MODEL_LOADED random_car_model
	WAIT 0
	REQUEST_MODEL random_car_model
	GOSUB do_cancelled_checks
	IF fail_firetruck_mission = 1
		GOTO failed
	ENDIF

ENDWHILE

/////////////////////////////////////////////////////////////////////
//CREATE THE FIRE SCENE
/////////////////////////////////////////////////////////////////////


fire_first_car_created = 0

GOSUB generate_random_fire_coord


car1_x = fire_random_x 
car1_y = fire_random_y 
car1_z = fire_random_z


CLEAR_AREA car1_x car1_y car1_z 5.0 FALSE
CREATE_CAR random_car_model car1_x car1_y car1_z onfire_car_1
FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_1 TRUE
SET_CAR_HEADING onfire_car_1 temp_heading
SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_1 FALSE
SET_CAR_PROOFS onfire_car_1 FALSE TRUE FALSE FALSE FALSE
GENERATE_RANDOM_FLOAT_IN_RANGE 0.0 359.9 random_car_heading
SET_CAR_HEADING	onfire_car_1 random_car_heading
START_CAR_FIRE onfire_car_1 onfire_car_1_fire
REMOVE_BLIP	onfire_car_1_blip
ADD_BLIP_FOR_CAR onfire_car_1 onfire_car_1_blip
SET_BLIP_AS_FRIENDLY onfire_car_1_blip TRUE
CHANGE_BLIP_DISPLAY onfire_car_1_blip BLIP_ONLY
SET_UPSIDEDOWN_CAR_NOT_DAMAGED onfire_car_1 TRUE
fire_first_car_created = 1
++ fires_needing_extinguishing



CREATE_RANDOM_CHAR_AS_DRIVER onfire_car_1 onfire_ped_1
SET_CHAR_DECISION_MAKER onfire_ped_1 fire_decision
SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_1 FALSE
SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_1 TRUE
SET_CHAR_PROOFS onfire_ped_1 FALSE TRUE TRUE FALSE FALSE
++ fires_needing_extinguishing



IF db_firetruck_level > 2
	CREATE_RANDOM_CHAR_AS_PASSENGER onfire_car_1 0 onfire_ped_2
	SET_CHAR_DECISION_MAKER onfire_ped_2 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_2 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_2 TRUE
	SET_CHAR_PROOFS onfire_ped_2 FALSE TRUE TRUE FALSE FALSE
	++ fires_needing_extinguishing
ENDIF

IF db_firetruck_level > 3
	CREATE_RANDOM_CHAR_AS_PASSENGER onfire_car_1 1 onfire_ped_3
	SET_CHAR_DECISION_MAKER onfire_ped_3 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_3 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_3 TRUE
	SET_CHAR_PROOFS onfire_ped_3 FALSE TRUE TRUE FALSE FALSE
	++ fires_needing_extinguishing
ENDIF

IF db_firetruck_level > 4	

	GOSUB generate_random_fire_coord

	
	
	car2_x = fire_random_x 
	car2_y = fire_random_y 
	car2_z = fire_random_z
	CLEAR_AREA car2_x car2_y car2_z 5.0 FALSE
	CREATE_CAR random_car_model car2_x car2_y car2_z onfire_car_2
	FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_2 TRUE
	SET_CAR_HEADING onfire_car_2 temp_heading
	SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_2 FALSE
	SET_CAR_PROOFS onfire_car_2 FALSE TRUE FALSE FALSE FALSE
	TURN_CAR_TO_FACE_COORD onfire_car_2	car1_x car1_y
	START_CAR_FIRE onfire_car_2 onfire_car_2_fire
	SET_CAR_FORWARD_SPEED onfire_car_2 20.0
	REMOVE_BLIP	onfire_car_2_blip
	ADD_BLIP_FOR_CAR onfire_car_2 onfire_car_2_blip
	SET_BLIP_AS_FRIENDLY onfire_car_2_blip TRUE
	CHANGE_BLIP_DISPLAY onfire_car_2_blip BLIP_ONLY
	SET_UPSIDEDOWN_CAR_NOT_DAMAGED onfire_car_2 TRUE
	++ fires_needing_extinguishing
ENDIF

IF db_firetruck_level > 5
	CREATE_RANDOM_CHAR_AS_DRIVER onfire_car_2 onfire_ped_4
	SET_CHAR_DECISION_MAKER onfire_ped_4 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_4 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_4 TRUE
   	SET_CHAR_PROOFS onfire_ped_4 FALSE TRUE TRUE FALSE FALSE
   	++ fires_needing_extinguishing

ENDIF

IF db_firetruck_level > 6
	CREATE_RANDOM_CHAR_AS_PASSENGER onfire_car_2 0 onfire_ped_5
	SET_CHAR_DECISION_MAKER onfire_ped_5 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_5 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_5 TRUE
	SET_CHAR_PROOFS onfire_ped_5 FALSE TRUE TRUE FALSE FALSE
		++ fires_needing_extinguishing
ENDIF

IF db_firetruck_level > 7
	CREATE_RANDOM_CHAR_AS_PASSENGER onfire_car_2 1 onfire_ped_6
	SET_CHAR_DECISION_MAKER onfire_ped_6 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_6 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_6 TRUE
	SET_CHAR_PROOFS onfire_ped_6 FALSE TRUE TRUE FALSE FALSE
		++ fires_needing_extinguishing

ENDIF



IF db_firetruck_level > 8	

	GOSUB generate_random_fire_coord

		
	car3_x = fire_random_x 
	car3_y = fire_random_y 
	car3_z = fire_random_z
	CLEAR_AREA car3_x car3_y car3_z 5.0 FALSE
	CREATE_CAR random_car_model car3_x car3_y car3_z onfire_car_3
	FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_3 TRUE
	SET_CAR_HEADING onfire_car_3 temp_heading
	SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_3 FALSE

	SET_CAR_PROOFS onfire_car_3 FALSE TRUE FALSE FALSE FALSE
	TURN_CAR_TO_FACE_COORD onfire_car_3	car1_x car1_y
	START_CAR_FIRE onfire_car_3 onfire_car_3_fire
	SET_CAR_FORWARD_SPEED onfire_car_3 20.0
	REMOVE_BLIP	onfire_car_3_blip
	ADD_BLIP_FOR_CAR onfire_car_3 onfire_car_3_blip
	SET_BLIP_AS_FRIENDLY onfire_car_3_blip TRUE
	CHANGE_BLIP_DISPLAY onfire_car_3_blip BLIP_ONLY
	SET_UPSIDEDOWN_CAR_NOT_DAMAGED onfire_car_3 TRUE
	++ fires_needing_extinguishing
ENDIF

MARK_MODEL_AS_NO_LONGER_NEEDED random_car_model

IF db_firetruck_level > 9
	CREATE_RANDOM_CHAR_AS_DRIVER onfire_car_3 onfire_ped_7
	SET_CHAR_DECISION_MAKER onfire_ped_7 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_7 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_7 TRUE
	SET_CHAR_PROOFS onfire_ped_7 FALSE TRUE TRUE FALSE FALSE
	++ fires_needing_extinguishing
ENDIF

IF db_firetruck_level > 10
	CREATE_RANDOM_CHAR_AS_PASSENGER onfire_car_3 0 onfire_ped_8
	SET_CHAR_DECISION_MAKER onfire_ped_8 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_8 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_8 TRUE
	SET_CHAR_PROOFS onfire_ped_8 FALSE TRUE TRUE FALSE FALSE
		++ fires_needing_extinguishing
ENDIF

IF db_firetruck_level > 11
	CREATE_RANDOM_CHAR_AS_PASSENGER onfire_car_3 1 onfire_ped_9
	SET_CHAR_DECISION_MAKER onfire_ped_9 fire_decision
	SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_9 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER	onfire_ped_9 TRUE
	SET_CHAR_PROOFS onfire_ped_9 FALSE TRUE TRUE FALSE FALSE
		++ fires_needing_extinguishing

ENDIF

IF NOT IS_CAR_DEAD onfire_car_1
	SET_CAR_MISSION onfire_car_1 MISSION_STOP_FOREVER
ENDIF
IF NOT IS_CAR_DEAD onfire_car_2
	SET_CAR_MISSION onfire_car_2 MISSION_STOP_FOREVER
ENDIF
IF NOT IS_CAR_DEAD onfire_car_3
	SET_CAR_MISSION onfire_car_3 MISSION_STOP_FOREVER
ENDIF

/////////////////////////////////////////////////////////////////////
//DO PROMPTS & GAMEY STUFF
/////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////GAME SPECIFIC NEED TO ADD CODE TO CHECK EACH AREA IN SA (dave b) /////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

GOSUB do_cancelled_checks
IF fail_firetruck_mission = 1
	GOTO failed
ENDIF

IF NOT IS_CHAR_DEAD onfire_ped_1   	   
   	   
	GET_CHAR_COORDINATES onfire_ped_1 dave_ped_x dave_ped_y dave_ped_z	
	GET_NAME_OF_ZONE dave_ped_x dave_ped_y dave_ped_z $fire_zone_name	
   	IF db_firetruck_level < 5
		PRINT_STRING_IN_STRING_NOW F_START $fire_zone_name 5000 1 //~g~Burning vehicle reported in the ~a~ area. Go and extinguish the fire.
	ELSE		
		PRINT_STRING_IN_STRING_NOW F_STAR1 $fire_zone_name 5000 1 //~g~Burning vehicles reported in the ~a~ area. Go and extinguish the fire.
	ENDIF   	
ENDIF






///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////END OF GAME SPECIFIC NEED TO ADD CODE TO CHECK EACH AREA IN SA (dave b)///////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF db_firetruck_level = 1
	-- fires_needing_extinguishing
	DELETE_CHAR	onfire_ped_1
ENDIF

IF displayed_timer = 0
	DISPLAY_ONSCREEN_TIMER_WITH_STRING fire_time_limit TIMER_DOWN  FIRTIME
	DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_firetruck_level	COUNTER_DISPLAY_NUMBER 1 FLEV	
	displayed_timer = 1
ELSE
	FREEZE_ONSCREEN_TIMER FALSE
ENDIF

firetruck_level_minus_4 = db_firetruck_level - 4

GET_GAME_TIMER game_timer
car_health_test_timer = game_timer + 3000

/////////////////////////////////////////////////////////////////////
firetruck_mission_loop:
/////////////////////////////////////////////////////////////////////

////////////////////////////// 
/// dave b debug key board ///
//////////////////////////////

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE

  fire_time_limit = 300000
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_ENTER 

	IF NOT IS_CAR_DEAD onfire_car_1
		SET_CAR_CAN_BE_DAMAGED onfire_car_1 FALSE
		SET_CAR_CAN_BE_DAMAGED onfire_car_1 TRUE
	ENDIF

	IF db_firetruck_level > 4
		IF NOT IS_CAR_DEAD onfire_car_2
			   SET_CAR_CAN_BE_DAMAGED onfire_car_2 FALSE
		       SET_CAR_CAN_BE_DAMAGED onfire_car_2 TRUE
		ENDIF
	ENDIF

	IF db_firetruck_level > 8
		IF NOT IS_CAR_DEAD onfire_car_3
			SET_CAR_CAN_BE_DAMAGED onfire_car_3 FALSE
			SET_CAR_CAN_BE_DAMAGED onfire_car_3 TRUE
		ENDIF
	ENDIF
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
done_firetruck_progress = 0
db_firetruck_level = 12
GOTO passed
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_Z
CLEAR_ONSCREEN_TIMER fire_time_limit
	CLEAR_ONSCREEN_COUNTER db_fires_extinguished
	CLEAR_ONSCREEN_COUNTER db_firetruck_level
	CLEAR_THIS_PRINT F_START
	CLEAR_THIS_PRINT F_STAR1
	CLEAR_THIS_PRINT FIRELVL
	
	PRINT_BIG F_PASS1 5000 5
	score_ft = db_firetruck_level * db_firetruck_level
	score_ft *= 50
	total_score += score_ft
	PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 6
	ADD_SCORE player1 score_ft

	PRINT_WITH_NUMBER_BIG F_COMP1 5000 5000 5  //Fire Fighter missions complete: $~1~
	ADD_SCORE player1 5000
	PLAY_MISSION_PASSED_TUNE 2
ENDIF
/*

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
CLEAR_PRINTS
CLEAR_THIS_PRINT F_PASS1
PRINT_BIG F_PASS1 5000 6
PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 1 //6
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
CLEAR_PRINTS
CLEAR_THIS_PRINT F_PASS1
PRINT_BIG F_PASS1 5000 6
PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 2 //6
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_3
CLEAR_PRINTS
CLEAR_THIS_PRINT F_PASS1
PRINT_BIG F_PASS1 5000 6
PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 3 //6
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_4
CLEAR_PRINTS
CLEAR_THIS_PRINT F_PASS1
PRINT_BIG F_PASS1 5000 6
PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 4 //6
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_5
CLEAR_PRINTS
CLEAR_THIS_PRINT F_PASS1
PRINT_BIG F_PASS1 5000 6
PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 5 //6
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_6
CLEAR_PRINTS
CLEAR_THIS_PRINT F_PASS1
PRINT_BIG F_PASS1 5000 6
PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 6 //6
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_7
CLEAR_PRINTS
CLEAR_THIS_PRINT F_PASS1
PRINT_BIG F_PASS1 5000 6
PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 7 //6
ENDIF
*/







//VIEW_INTEGER_VARIABLE fires_needing_extinguishing  fires_needed
//VIEW_INTEGER_VARIABLE fires_extinguished_this_round	fires_got
////////////////////////////////
///////////////////////////////
////////////////////////////////
	



	IF fail_firetruck_mission = 1
		GOTO failed
	ENDIF
	
	WAIT 0
	
	GET_GAME_TIMER game_timer
	
	GOSUB do_cancelled_checks
	IF fail_firetruck_mission = 1
		GOTO failed
	ENDIF

	/*
	IF IS_MESSAGE_BEING_DISPLAYED
	WRITE_DEBUG  yes
	ELSE
	WRITE_DEBUG no
	ENDIF
	*/
    
    IF got_siren_help_before < 2 
	    IF got_siren_help_before = 0
			IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
				IF NOT IS_MESSAGE_BEING_DISPLAYED		
					IF TIMERA > 1000				
					PRINT_HELP SPRAY_1  
					got_siren_help_before = 1
					ENDIF
				ELSE
					TIMERA = 0
				ENDIF		
			ENDIF
		ELSE			
			IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
				IF NOT IS_MESSAGE_BEING_DISPLAYED		
					IF TIMERA > 1000				
					PRINT_HELP SIREN_1 
					got_siren_help_before = 2
					ENDIF
				ELSE
					TIMERA = 0
				ENDIF		
			ENDIF			
		ENDIF
	ENDIF
	

	
	
	
	
	IF NOT IS_CAR_DEAD	onfire_car_1
		IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer onfire_car_1 50.0 50.0 10.0 FALSE
			IF fire_collision_loaded_car[0] = 0					
				SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_1 TRUE			
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_1 FALSE
				fire_collision_loaded_car[0] = 1
				IF db_firetruck_level > 1
					IF NOT IS_CHAR_DEAD onfire_ped_1
						SET_LOAD_COLLISION_FOR_CHAR_FLAG  onfire_ped_1 TRUE
					ENDIF	  					 
				ENDIF

				IF db_firetruck_level > 2
					IF NOT IS_CHAR_DEAD onfire_ped_2
						SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_2  TRUE	  					 
					ENDIF
				ENDIF
				
				IF db_firetruck_level > 3
					IF NOT IS_CHAR_DEAD onfire_ped_3
						SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_3  TRUE	  					 
					ENDIF
				ENDIF			
			ENDIF														
		ELSE		
			IF fire_collision_loaded_car[0] = 1
				SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_1 FALSE			
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_1 TRUE
				fire_collision_loaded_car[0] = 0
				IF db_firetruck_level > 1
					IF NOT IS_CHAR_DEAD onfire_ped_1
						SET_LOAD_COLLISION_FOR_CHAR_FLAG  onfire_ped_1 FALSE
					ENDIF	  					 
				ENDIF

				IF db_firetruck_level > 2
					IF NOT IS_CHAR_DEAD onfire_ped_2
						SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_2  FALSE	  					 
					ENDIF
				ENDIF
				
				IF db_firetruck_level > 3
					IF NOT IS_CHAR_DEAD onfire_ped_3
						SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_3  FALSE	  					 
					ENDIF
				ENDIF
			
			ENDIF		
		ENDIF
	ENDIF


	IF db_firetruck_level > 4
		IF NOT IS_CAR_DEAD	onfire_car_2
			IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer onfire_car_2 50.0 50.0 10.0 FALSE
				IF fire_collision_loaded_car[1] = 0					
					SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_2 TRUE			
					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_2 FALSE
					fire_collision_loaded_car[1] = 1
					IF db_firetruck_level > 5
						IF NOT IS_CHAR_DEAD onfire_ped_4
							SET_LOAD_COLLISION_FOR_CHAR_FLAG  onfire_ped_4 TRUE
						ENDIF	  					 
					ENDIF

					IF db_firetruck_level > 6
						IF NOT IS_CHAR_DEAD onfire_ped_5
							SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_5  TRUE	  					 
						ENDIF
					ENDIF
					
					IF db_firetruck_level > 7
						IF NOT IS_CHAR_DEAD onfire_ped_6
							SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_6  TRUE	  					 
						ENDIF
					ENDIF
				
				
				ENDIF		
			ELSE		
				IF fire_collision_loaded_car[1] = 1
					SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_2 FALSE			
					FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_2 TRUE
					fire_collision_loaded_car[1] = 0
					IF db_firetruck_level > 5
						IF NOT IS_CHAR_DEAD onfire_ped_4
							SET_LOAD_COLLISION_FOR_CHAR_FLAG  onfire_ped_4 FALSE
						ENDIF	  					 
					ENDIF

					IF db_firetruck_level > 6
						IF NOT IS_CHAR_DEAD onfire_ped_5
							SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_5  FALSE	  					 
						ENDIF
					ENDIF
					
					IF db_firetruck_level > 7
						IF NOT IS_CHAR_DEAD onfire_ped_6
							SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_6  FALSE	  					 
						ENDIF
					ENDIF
				ENDIF		
			ENDIF
		ENDIF
	

		IF db_firetruck_level > 8
			IF NOT IS_CAR_DEAD	onfire_car_3
				IF LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer onfire_car_3 50.0 50.0 10.0 FALSE
					IF fire_collision_loaded_car[2] = 0					
						
						SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_3 TRUE			
						FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_3 FALSE
						IF db_firetruck_level > 9
							IF NOT IS_CHAR_DEAD onfire_ped_7
								SET_LOAD_COLLISION_FOR_CHAR_FLAG  onfire_ped_7 TRUE
							ENDIF	  					 
						ENDIF

						IF db_firetruck_level > 10
							IF NOT IS_CHAR_DEAD onfire_ped_8
								SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_8  TRUE	  					 
							ENDIF
						ENDIF
						
						IF db_firetruck_level > 11
							IF NOT IS_CHAR_DEAD onfire_ped_9
								SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_9  TRUE	  					 
							ENDIF
						ENDIF																									
											
						fire_collision_loaded_car[2] = 1
					ENDIF		
				ELSE		
					IF fire_collision_loaded_car[2] = 1
						SET_LOAD_COLLISION_FOR_CAR_FLAG onfire_car_3 FALSE			
						FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION	onfire_car_3 TRUE
						fire_collision_loaded_car[2] = 0
						IF db_firetruck_level > 9
							IF NOT IS_CHAR_DEAD onfire_ped_7 
								SET_LOAD_COLLISION_FOR_CHAR_FLAG  onfire_ped_7 FALSE
							ENDIF	  					 
						ENDIF

						IF db_firetruck_level > 10
							IF NOT IS_CHAR_DEAD onfire_ped_8
								SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_8  FALSE	  					 
							ENDIF
						ENDIF
						
						IF db_firetruck_level > 11
							IF NOT IS_CHAR_DEAD onfire_ped_9
								SET_LOAD_COLLISION_FOR_CHAR_FLAG onfire_ped_9  FALSE	  					 
							ENDIF
						ENDIF
					ENDIF		
				ENDIF
			ENDIF
		ENDIF

	ENDIF





	onfire_car      = onfire_car_1
	onfire_car_fire = onfire_car_1_fire
	onfire_car_flag = onfire_car_1_flag
	onfire_car_blip = onfire_car_1_blip
	car_numer_flag = 1
	GOSUB do_fire_on_car_bit	
	onfire_car_1_fire = onfire_car_fire
	onfire_car_1_flag = onfire_car_flag
	onfire_car_1_blip = onfire_car_blip
	

	IF db_firetruck_level > 1
		onfire_ped	    = onfire_ped_1	  
		onfire_ped_flag = onfire_ped_1_flag 
		onfire_ped_fire = onfire_ped_1_fire 
		onfire_ped_blip = onfire_ped_1_blip 		
		GOSUB do_fire_on_ped_bit
		onfire_ped_1_flag = onfire_ped_flag 
		onfire_ped_1_fire = onfire_ped_fire 
		onfire_ped_1_blip = onfire_ped_blip 
	ENDIF

	IF db_firetruck_level > 2
		onfire_ped	    = onfire_ped_2	  
		onfire_ped_flag = onfire_ped_2_flag 
		onfire_ped_fire = onfire_ped_2_fire 
		onfire_ped_blip = onfire_ped_2_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_2_flag = onfire_ped_flag 
		onfire_ped_2_fire = onfire_ped_fire 
		onfire_ped_2_blip = onfire_ped_blip 
	ENDIF
	
	IF db_firetruck_level > 3
		onfire_ped	    = onfire_ped_3	  
		onfire_ped_flag = onfire_ped_3_flag 
		onfire_ped_fire = onfire_ped_3_fire 
		onfire_ped_blip = onfire_ped_3_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_3_flag = onfire_ped_flag 
		onfire_ped_3_fire = onfire_ped_fire 
		onfire_ped_3_blip = onfire_ped_blip 
	ENDIF
	
	IF db_firetruck_level > 4
		onfire_car      = onfire_car_2
		onfire_car_fire = onfire_car_2_fire
		onfire_car_flag = onfire_car_2_flag
		onfire_car_blip = onfire_car_2_blip
		car_numer_flag = 2
		GOSUB do_fire_on_car_bit
		onfire_car_2_fire = onfire_car_fire
		onfire_car_2_flag = onfire_car_flag
		onfire_car_2_blip = onfire_car_blip
	ENDIF

	IF db_firetruck_level > 5
		onfire_ped	    = onfire_ped_4	  
		onfire_ped_flag = onfire_ped_4_flag 
		onfire_ped_fire = onfire_ped_4_fire 
		onfire_ped_blip = onfire_ped_4_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_4_flag = onfire_ped_flag 
		onfire_ped_4_fire = onfire_ped_fire 
		onfire_ped_4_blip = onfire_ped_blip 
	ENDIF
	
	IF db_firetruck_level > 6
		onfire_ped	    = onfire_ped_5	  
		onfire_ped_flag = onfire_ped_5_flag 
		onfire_ped_fire = onfire_ped_5_fire 
		onfire_ped_blip = onfire_ped_5_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_5_flag = onfire_ped_flag 
		onfire_ped_5_fire = onfire_ped_fire 
		onfire_ped_5_blip = onfire_ped_blip 
	ENDIF

	IF db_firetruck_level > 7
		onfire_ped	    = onfire_ped_6	  
		onfire_ped_flag = onfire_ped_6_flag 
		onfire_ped_fire = onfire_ped_6_fire 
		onfire_ped_blip = onfire_ped_6_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_6_flag = onfire_ped_flag 
		onfire_ped_6_fire = onfire_ped_fire 
		onfire_ped_6_blip = onfire_ped_blip 
	ENDIF
	
	IF db_firetruck_level > 8
		onfire_car      = onfire_car_3
		onfire_car_fire = onfire_car_3_fire
		onfire_car_flag = onfire_car_3_flag
		onfire_car_blip = onfire_car_3_blip
		car_numer_flag = 3
		GOSUB do_fire_on_car_bit
		onfire_car_3_fire = onfire_car_fire
		onfire_car_3_flag = onfire_car_flag
		onfire_car_3_blip = onfire_car_blip
	ENDIF

	IF db_firetruck_level > 9
		onfire_ped	    = onfire_ped_7	  
		onfire_ped_flag = onfire_ped_7_flag 
		onfire_ped_fire = onfire_ped_7_fire 
		onfire_ped_blip = onfire_ped_7_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_7_flag = onfire_ped_flag 
		onfire_ped_7_fire = onfire_ped_fire 
		onfire_ped_7_blip = onfire_ped_blip 

	ENDIF
	
	IF db_firetruck_level > 10
		onfire_ped	    = onfire_ped_8	  
		onfire_ped_flag = onfire_ped_8_flag 
		onfire_ped_fire = onfire_ped_8_fire 
		onfire_ped_blip = onfire_ped_8_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_8_flag = onfire_ped_flag 
		onfire_ped_8_fire = onfire_ped_fire 
		onfire_ped_8_blip = onfire_ped_blip 
	ENDIF

	IF db_firetruck_level > 11
		onfire_ped	    = onfire_ped_9	  
		onfire_ped_flag = onfire_ped_9_flag 
		onfire_ped_fire = onfire_ped_9_fire 
		onfire_ped_blip = onfire_ped_9_blip 
		GOSUB do_fire_on_ped_bit
		onfire_ped_9_flag = onfire_ped_flag 
		onfire_ped_9_fire = onfire_ped_fire 
		onfire_ped_9_blip = onfire_ped_blip 

	ENDIF
	

	IF fires_extinguished_this_round >= fires_needing_extinguishing//firetruck_level
		GOTO passed
	ENDIF

	
GOTO firetruck_mission_loop






generate_random_fire_coord:

WAIT 0

	
	
	
	
	fire_safety_flag = 0
	TIMERA = 0
	WHILE fire_safety_flag = 0 
		
		fire_safety_flag = 1
		fire_generating_coords = 1
		
		fire_coord_iterations+= 1.0
		
		/// do_cancelled_checks ///
		

		IF NOT IS_CHAR_DEAD scplayer	
			IF NOT IS_CHAR_IN_MODEL scplayer FIRETRUK
				CLEAR_PRINTS		
				PRINT_NOW F_CANC 3000 1//"Fire truck mission cancelled!"
				fail_firetruck_mission = 1				
			ENDIF
		ELSE
			fail_firetruck_mission = 1				
		ENDIF

		GET_CONTROLLER_MODE controlmode

		IF NOT controlmode = 3
			IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
				mission_end_button_ft = 1
			ENDIF
		ELSE
			IF IS_BUTTON_PRESSED PAD1 SQUARE
				mission_end_button_ft = 1
			ENDIF
		ENDIF
		
		IF mission_end_button_ft = 1
			IF NOT controlmode = 3
				IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					CLEAR_PRINTS
					PRINT_NOW F_CANC 3000 1//"Fire truck mission cancelled!"
					fail_firetruck_mission = 1					
				ENDIF
			ELSE
				IF NOT IS_BUTTON_PRESSED PAD1 SQUARE
					CLEAR_PRINTS
					PRINT_NOW F_CANC 3000 1//"Fire truck mission cancelled!"
					fail_firetruck_mission = 1					
				ENDIF
			ENDIF
		ENDIF
			
		////////////////////////////
		IF fail_firetruck_mission = 1
			GOTO failed
		ENDIF



		GET_CHAR_COORDINATES scplayer dave_player_x dave_player_y dave_player_z
	   		 		 				
		fire_dist_multiplier =# db_firetruck_level 
		fire_dist_multiplier *= 60.0
		fire_dist_multiplier += fire_coord_iterations
		IF fire_dist_multiplier < 170.0
			fire_dist_multiplier = 170.0
		ENDIF
		
		
		dave_generate_x_pos  = dave_player_x + fire_dist_multiplier
		dave_generate_x_neg  = dave_player_x - fire_dist_multiplier
		
		dave_generate_y_pos	 = dave_player_y + fire_dist_multiplier
		dave_generate_y_neg  = dave_player_y - fire_dist_multiplier
		

		GENERATE_RANDOM_FLOAT_IN_RANGE dave_generate_x_neg dave_generate_x_pos  fire_random_x  
		GENERATE_RANDOM_FLOAT_IN_RANGE dave_generate_y_neg dave_generate_y_pos  fire_random_y
		
																		
		

		///////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////

			
			GET_CLOSEST_STRAIGHT_ROAD fire_random_x fire_random_y dave_player_z 10.0 500.0 fire_random_x fire_random_y fire_random_z temp_x temp_y temp_z temp_heading	   

			IF fire_random_x = 0.0
				IF	fire_random_y = 0.0
					IF	temp_x = 0.0
						IF 	temp_y = 0.0
							fire_safety_flag = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		
		//maybe have check on zones here to force all the cars into the same zone? //
		IF fire_safety_flag = 1
			
			GET_DISTANCE_BETWEEN_COORDS_3D dave_player_x dave_player_y dave_player_z fire_random_x fire_random_y fire_random_z players_distance_from_fire

			IF players_distance_from_fire < 140.0
			   // this distance thing does not seem to activate
				fire_safety_flag = 0
			ENDIF
			
			IF fire_first_car_created = 1			   
				IF fire_coord_iterations < 35.0
				   GET_NAME_OF_ZONE fire_random_x fire_random_y fire_random_z $fire_zone_name2 
				   IF NOT $fire_zone_name2 = $fire_zone_name
						fire_safety_flag = 0
				   ENDIF
			   ENDIF
			ELSE			   
			   GET_NAME_OF_ZONE fire_random_x fire_random_y fire_random_z $fire_zone_name	
			ENDIF



			
		ENDIF

	   
	  
	  IF fire_safety_flag = 1
		  GET_INT_STAT CITIES_PASSED Return_cities_passed
		  IF Return_cities_passed < 2	// else whole map open

				IF Return_cities_passed = 0
					
					IF fire_random_x  < 78.4427   //south by la
					AND  fire_random_y < -699.5190
							fire_safety_flag = 0
					ENDIF


					IF fire_safety_flag = 1
						IF fire_random_x  < -252.6557 //below jutty out bit west badlands
						AND     fire_random_y < -285.7660
							fire_safety_flag = 0
						ENDIF


						IF fire_safety_flag = 1
							IF fire_random_x  < -948.3447 //west of jutty out bit west badlands
								fire_safety_flag = 0
							ENDIF
						

							IF fire_safety_flag = 1
								IF fire_random_x > 1473.4481 //below vegas north east badlands
								AND fire_random_y > 403.7353
									fire_safety_flag = 0
								ENDIF

								IF fire_safety_flag = 1
									IF fire_random_y > 578.6325 //north of jutty out bit middle north badlands
										fire_safety_flag = 0
									ENDIF


									IF fire_safety_flag = 1
										IF fire_random_x < 837.5551  //northwest from above jutty out bit middle north badlands
										AND fire_random_y > 347.4097
											fire_safety_flag = 0
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF	

		 


				IF fire_safety_flag = 1
					IF Return_cities_passed = 1 //sanfran open 
						IF fire_random_x > 1473.4481 //below vegas north east badlands
						AND fire_random_y > 403.7353
							fire_safety_flag = 0
						ENDIF

						IF fire_safety_flag = 1

							IF fire_random_y > 578.6325 //north of jutty out bit middle north badlands
							AND fire_random_x > -1528.4976
								fire_safety_flag = 0
							ENDIF


							IF fire_safety_flag = 1
								IF fire_random_x < 837.5551  //northwest from above jutty out bit middle north badlands
								AND fire_random_x > -1528.4976
								AND fire_random_y > 347.4097
									fire_safety_flag = 0
								ENDIF

								IF fire_safety_flag = 1

									IF fire_random_y > 1380.0 //north of sanfran
										fire_safety_flag = 0
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF	

		ENDIF
	ENDIF

	/////////////////////////////
	// invalid placement areas //
	/////////////////////////////

	
		////////////////
		// LA AIRPORT //
		////////////////
	IF fire_safety_flag = 1
		IF fire_random_x < 2150.0  
			IF  fire_random_x > 1970.0 
				IF fire_random_y < -2274.0
					IF  fire_random_y > -2670.0   
						fire_safety_flag = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF fire_random_x < 2150.0 
			IF  fire_random_x > 1590.0
				IF fire_random_y < -2397.0
					IF  fire_random_y > -2670.0  
						fire_safety_flag = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
		
		//////////////////////
		// SAN FRAN AIRPORT //
		//////////////////////
	
	IF fire_safety_flag = 1
		IF fire_random_x < -1070.0
			IF  fire_random_x > -1737.0
				IF fire_random_y < -185.0
					IF  fire_random_y > -590.0
						fire_safety_flag = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
	
		IF fire_random_x < -1081.0
			IF  fire_random_x > -1600.0
				IF fire_random_y < 415.0
					IF  fire_random_y > -185.0
						fire_safety_flag = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	
	ENDIF
		
		
		///////////////////
		// VEGAS AIRPORT //
		///////////////////
	IF fire_safety_flag = 1
		IF fire_random_x < 1733.0 
			IF  fire_random_x > 1500.0
				IF fire_random_y < 1702.0
					IF  fire_random_y > 1529.0  
						fire_safety_flag = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		///////////////////
	ENDIF



	IF fire_safety_flag = 1
		IF db_firetruck_level > 4
			GET_DISTANCE_BETWEEN_COORDS_3D car1_x car1_y car1_z fire_random_x fire_random_y fire_random_z  temp_distance
			IF temp_distance < 20.0
			   fire_safety_flag = 0		
			ELSE
				IF db_firetruck_level > 8
					GET_DISTANCE_BETWEEN_COORDS_3D car2_x car2_y car2_z fire_random_x fire_random_y fire_random_z  temp_distance
					IF temp_distance < 20.0
			   			fire_safety_flag = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
		
	
	







WAIT 0 
ENDWHILE

								
IF db_firetruck_level > 4	
	GET_DISTANCE_BETWEEN_COORDS_3D car1_x car1_y car1_z fire_random_x fire_random_y fire_random_z  temp_distance	
	IF db_firetruck_level > 8
	/// three cars ///			
		GET_DISTANCE_BETWEEN_COORDS_3D car2_x car2_y car2_z fire_random_x fire_random_y fire_random_z  temp_distance
		temp_distance /=  4.0			
		players_distance_from_fire += temp_distance
	ELSE
	/// two cars ///	
		players_distance_from_fire += temp_distance
		temp_distance /= 8.0
	ENDIF	
ELSE
/// only one car //
ENDIF


IF db_firetruck_level < 9
	fire_time_limit_float = players_distance_from_fire / 8.0 //time_divider	 //5.5
ELSE
	fire_time_limit_float = players_distance_from_fire / 10.0 //time_divider	 //5.5
ENDIF
fire_time_limit_float = fire_time_limit_float * 1000.0
intermediate_int =#	fire_time_limit_float
fire_time_limit += intermediate_int


//IF db_fires_extinguished = 0
	IF fire_time_limit < 60000
		fire_time_limit = 60000
	ENDIF
//ENDIF


	   
fire_generating_coords = 0
fire_coord_iterations = 0.0





RETURN











/////////////////////////////////////////////////////////////////////
do_cancelled_checks:
/////////////////////////////////////////////////////////////////////
IF displayed_timer = 1
	IF fire_time_limit < 1
		CLEAR_PRINTS
		PRINT_NOW F_FAIL2 5000 1
		fail_firetruck_mission = 1
		RETURN
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD scplayer	
	IF NOT IS_CHAR_IN_MODEL scplayer FIRETRUK
		CLEAR_PRINTS		
		PRINT_NOW F_CANC 3000 1//"Fire truck mission cancelled!"
		fail_firetruck_mission = 1
		RETURN
	ENDIF
ENDIF

GET_CONTROLLER_MODE controlmode

IF NOT controlmode = 3
	IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
		mission_end_button_ft = 1
	ENDIF
ELSE
	IF IS_BUTTON_PRESSED PAD1 SQUARE
		mission_end_button_ft = 1
	ENDIF
ENDIF

IF mission_end_button_ft = 1
	IF NOT controlmode = 3
		IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			CLEAR_PRINTS
			PRINT_NOW F_CANC 3000 1//"Fire truck mission cancelled!"
			fail_firetruck_mission = 1
			RETURN
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 SQUARE
			CLEAR_PRINTS
			PRINT_NOW F_CANC 3000 1//"Fire truck mission cancelled!"
			fail_firetruck_mission = 1
			RETURN
		ENDIF
	ENDIF
ENDIF
RETURN


/////////////////////////////////////////////////////////////////////
do_fire_on_car_bit:
/////////////////////////////////////////////////////////////////////



IF NOT onfire_car_flag = 10
	IF NOT IS_CAR_DEAD onfire_car
		car_or_ped_flag = 1
		IF DOES_SCRIPT_FIRE_EXIST onfire_car_fire
			IF NOT IS_SCRIPT_FIRE_EXTINGUISHED onfire_car_fire
							
				GET_CAR_HEALTH onfire_car onfire_car_health
				
				/*
				IF car_health_test_timer < game_timer
					IF onfire_car_health < 700
						EXPLODE_CAR onfire_car
						CLEAR_PRINTS
						PRINT_NOW F_FAIL2 5000 1
						fail_firetruck_mission = 1
						RETURN
					ENDIF
				ELSE
					SET_CAR_HEALTH onfire_car 1000
				ENDIF */


				/*IF onfire_car_flag = 0
					
					// may have to take this out //
					
					IF fires_extinguished_this_round = firetruck_level_minus_4
						CAR_WANDER_RANDOMLY	onfire_car	// put this back in
						SET_CAR_CRUISE_SPEED onfire_car 15.0
						SET_CAR_DRIVING_STYLE onfire_car 2
						SET_CAR_ONLY_DAMAGED_BY_PLAYER onfire_car TRUE
						onfire_car_flag = 1
					ENDIF
					
				ENDIF */
			ELSE
				IF NOT onfire_car_flag = 10
					REMOVE_BLIP onfire_car_blip
					TASK_EVERYONE_LEAVE_CAR onfire_car	// put this back in once new compiler is made dave b								
					REMOVE_SCRIPT_FIRE onfire_car_fire
					INCREMENT_INT_STAT FIRES_EXTINGUISHED 1
					IF displayed_counter = 0
						DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_fires_extinguished	COUNTER_DISPLAY_NUMBER  2 F_EXTIN						
						displayed_counter = 1
					ENDIF
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
					onfire_car_flag = 10
					++ db_fires_extinguished
					++ fires_extinguished_this_round
				ENDIF
			ENDIF
		ELSE
			IF NOT onfire_car_flag = 10
				REMOVE_BLIP onfire_car_blip
				TASK_EVERYONE_LEAVE_CAR onfire_car	// put this back in once new compiler is made dave b								
				REMOVE_SCRIPT_FIRE onfire_car_fire
				INCREMENT_INT_STAT FIRES_EXTINGUISHED 1
				IF displayed_counter = 0
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_fires_extinguished	COUNTER_DISPLAY_NUMBER  2 F_EXTIN
					displayed_counter = 1
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				onfire_car_flag = 10
				++ db_fires_extinguished
				++ fires_extinguished_this_round
			ENDIF		
		ENDIF
	ELSE
		IF IS_CAR_IN_WATER onfire_car
			IF NOT onfire_car_flag = 10
				REMOVE_BLIP onfire_car_blip
				TASK_EVERYONE_LEAVE_CAR  onfire_car	     // put this back in once new compiler is made dave b
				INCREMENT_INT_STAT FIRES_EXTINGUISHED 1
				IF displayed_counter = 0
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_fires_extinguished	COUNTER_DISPLAY_NUMBER  2 F_EXTIN
					displayed_counter = 1
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				onfire_car_flag = 10
				++ db_fires_extinguished
				++ fires_extinguished_this_round
			ENDIF
		ELSE
			CLEAR_PRINTS
			PRINT_NOW F_FAIL4 5000 1 //~r~A vehicle has been destroyed.
			fail_firetruck_mission = 1
			RETURN
		ENDIF
	ENDIF
ELSE // fire has been put out
	/*
	IF NOT IS_CAR_PASSENGER_SEAT_FREE closest_hospital 0
		GET_CHAR_IN_CAR_PASSENGER_SEAT closest_hospital 0 injured_ped_1
		TASK_LEAVE_CAR_IMMEDIATELY injured_ped_1 closest_hospital
		MARK_CHAR_AS_NO_LONGER_NEEDED injured_ped_1
	ENDIF
	*/

ENDIF


RETURN


/////////////////////////////////////////////////////////////////////
do_fire_on_ped_bit:		
/////////////////////////////////////////////////////////////////////



IF onfire_ped_flag < 2
	IF NOT IS_CHAR_DEAD	onfire_ped
		IF onfire_ped_flag = 0
			IF NOT IS_CHAR_IN_ANY_CAR onfire_ped
				START_CHAR_FIRE onfire_ped onfire_ped_fire
				GET_CHAR_COORDINATES onfire_ped db_temp_x db_temp_y db_temp_z
				TASK_FLEE_POINT onfire_ped db_temp_x db_temp_y db_temp_z 20.0 -1
				ADD_BLIP_FOR_CHAR onfire_ped onfire_ped_blip
				SET_BLIP_AS_FRIENDLY onfire_ped_blip TRUE
				CHANGE_BLIP_DISPLAY onfire_ped_blip BLIP_ONLY
				onfire_ped_flag = 1
			ELSE
				IF onfire_car_flag = 10
					IF IS_CHAR_IN_ANY_CAR onfire_ped					
						GET_SCRIPT_TASK_STATUS onfire_ped TASK_LEAVE_ANY_CAR fire_task_status
					 	IF fire_task_status = FINISHED_TASK
							TASK_LEAVE_ANY_CAR onfire_ped
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF onfire_ped_flag = 1
			car_or_ped_flag = 2
			IF DOES_SCRIPT_FIRE_EXIST onfire_ped_fire
				IF IS_SCRIPT_FIRE_EXTINGUISHED onfire_ped_fire
				
					REMOVE_SCRIPT_FIRE onfire_ped_fire
					REMOVE_BLIP	onfire_ped_blip
					INCREMENT_INT_STAT FIRES_EXTINGUISHED 1
					IF displayed_counter = 0
						DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_fires_extinguished	COUNTER_DISPLAY_NUMBER 2 F_EXTIN
						displayed_counter = 1
					ENDIF
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
					++ db_fires_extinguished
					++ fires_extinguished_this_round
					onfire_ped_flag = 2
				ENDIF
			ELSE
				REMOVE_BLIP	onfire_ped_blip
				INCREMENT_INT_STAT FIRES_EXTINGUISHED 1
				IF displayed_counter = 0
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_fires_extinguished	COUNTER_DISPLAY_NUMBER 2 F_EXTIN
					displayed_counter = 1
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				++ db_fires_extinguished
				++ fires_extinguished_this_round
				onfire_ped_flag = 2
			ENDIF
		ENDIF
	ELSE
		IF IS_CHAR_IN_WATER	onfire_ped
			IF onfire_ped_flag = 1
				REMOVE_SCRIPT_FIRE onfire_ped_fire
				REMOVE_BLIP	onfire_ped_blip
				INCREMENT_INT_STAT FIRES_EXTINGUISHED 1
				IF displayed_counter = 0
					DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING db_fires_extinguished	COUNTER_DISPLAY_NUMBER 2 F_EXTIN
					displayed_counter = 1
				ENDIF
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
				++ db_fires_extinguished
				++ fires_extinguished_this_round
				onfire_ped_flag = 2
			ELSE
				CLEAR_PRINTS
				PRINT_NOW F_FAIL2 5000 1
				fail_firetruck_mission = 1
				RETURN
			ENDIF
		ELSE
			CLEAR_PRINTS
			PRINT_NOW F_FAIL3 5000 1 //~r~There has been a fatality.
			fail_firetruck_mission = 1			
			RETURN
		ENDIF
	ENDIF
ENDIF
RETURN






/////////////////////////////////////////////////////////////////////
passed:
/////////////////////////////////////////////////////////////////////

FREEZE_ONSCREEN_TIMER TRUE




//REGISTER_FIRE_LEVEL	firetruck_level


REMOVE_ALL_SCRIPT_FIRES

REMOVE_BLIP	onfire_car_blip
REMOVE_BLIP	onfire_car_1_blip
REMOVE_BLIP	onfire_car_2_blip
REMOVE_BLIP	onfire_car_3_blip
REMOVE_BLIP	onfire_ped_blip
REMOVE_BLIP	onfire_ped_1_blip
REMOVE_BLIP	onfire_ped_2_blip
REMOVE_BLIP	onfire_ped_3_blip
REMOVE_BLIP	onfire_ped_4_blip
REMOVE_BLIP	onfire_ped_5_blip
REMOVE_BLIP	onfire_ped_6_blip
REMOVE_BLIP	onfire_ped_7_blip
REMOVE_BLIP	onfire_ped_8_blip
REMOVE_BLIP	onfire_ped_9_blip



IF IS_CHAR_IN_ANY_CAR scplayer
	STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer dave_car
	GET_CAR_HEALTH dave_car players_car_health
	players_car_health += 150
	SET_CAR_HEALTH dave_car players_car_health
ENDIF

MARK_CAR_AS_NO_LONGER_NEEDED onfire_car_1
IF NOT IS_CAR_DEAD onfire_car_1
	SET_CAR_ONLY_DAMAGED_BY_PLAYER onfire_car_1 FALSE
ENDIF

MARK_CAR_AS_NO_LONGER_NEEDED onfire_car_2
IF NOT IS_CAR_DEAD onfire_car_2
	SET_CAR_ONLY_DAMAGED_BY_PLAYER onfire_car_2 FALSE
ENDIF

MARK_CAR_AS_NO_LONGER_NEEDED onfire_car_3
IF NOT IS_CAR_DEAD onfire_car_3
	SET_CAR_ONLY_DAMAGED_BY_PLAYER onfire_car_3 FALSE
ENDIF

//DELETE_CHAR onfire_ped_1//DEBUG!!!!
//DELETE_CHAR onfire_ped_2//DEBUG!!!!
//DELETE_CHAR onfire_ped_3//DEBUG!!!!
//DELETE_CHAR onfire_ped_4//DEBUG!!!!
//DELETE_CHAR onfire_ped_5//DEBUG!!!!
//DELETE_CHAR onfire_ped_6//DEBUG!!!!
//DELETE_CHAR onfire_ped_7//DEBUG!!!!
//DELETE_CHAR onfire_ped_8//DEBUG!!!!
//DELETE_CHAR onfire_ped_9//DEBUG!!!!

MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_1
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_2
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_3
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_4
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_5
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_6
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_7
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_8
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_9

MARK_MODEL_AS_NO_LONGER_NEEDED random_car_model





REGISTER_INT_STAT FIREFIGHTER_LEVEL db_firetruck_level
++ db_firetruck_level
IF db_firetruck_level = 13
	
	CLEAR_ONSCREEN_TIMER fire_time_limit
	CLEAR_ONSCREEN_COUNTER db_fires_extinguished
	CLEAR_ONSCREEN_COUNTER db_firetruck_level
	CLEAR_THIS_PRINT F_START
	CLEAR_THIS_PRINT F_STAR1
	CLEAR_THIS_PRINT FIRELVL
	
	PRINT_BIG F_PASS1 5000 6//5
	score_ft = db_firetruck_level * db_firetruck_level
	score_ft *= 50
	total_score += score_ft
	//PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 6
	//ADD_SCORE player1 score_ft
	score_ft+= 5000
	PRINT_WITH_NUMBER_BIG F_COMP1 13450 5000 5  //Fire Fighter missions complete: $~1~
	//PRINT_WITH_NUMBER_BIG F_COMP1 5000 5000 5  //Fire Fighter missions complete: $~1~
	//ADD_SCORE player1 5000
	ADD_SCORE player1 score_ft
	PLAY_MISSION_PASSED_TUNE 2

	IF done_firetruck_progress = 0
		SHOW_UPDATE_STATS FALSE
		MAKE_PLAYER_FIRE_PROOF player1 TRUE
		//CLEAR_HELP
		//PRINT_HELP_FOREVER FIREPRO
		
		PLAYER_MADE_PROGRESS 1
		REGISTER_ODDJOB_MISSION_PASSED
		done_firetruck_progress = 1
	ENDIF
	fail_firetruck_mission = 1
	RETURN		
ELSE
	PRINT_BIG F_PASS1 5000 6  //5
	score_ft = db_firetruck_level * db_firetruck_level
	score_ft *= 50
	total_score += score_ft
	PRINT_WITH_NUMBER_BIG REWARD score_ft 6000 7
	ADD_SCORE player1 score_ft
ENDIF


	





GOTO fire_generation_start


/////////////////////////////////////////////////////////////////////
failed:
/////////////////////////////////////////////////////////////////////

CLEAR_ONSCREEN_TIMER fire_time_limit
CLEAR_ONSCREEN_COUNTER db_fires_extinguished
CLEAR_ONSCREEN_COUNTER db_firetruck_level
CLEAR_THIS_PRINT F_START
CLEAR_THIS_PRINT F_STAR1
CLEAR_THIS_PRINT FIRELVL

//CLEAR_PRINTS
IF NOT db_firetruck_level = 13
PRINT_BIG F_FAIL1 5000 5
PRINT_WITH_NUMBER_BIG TSCORE total_score 6000 6
ELSE
//PRINT_NOW FIREPRO 6000 1 
//PRINT_HELP FIREPRO //"You will never get tired!"
//PRINT_WITH_NUMBER_BIG F_COMP1 5000 5000 5  //Fire Fighter missions complete: $~1~
ENDIF
SET_WANTED_MULTIPLIER 1.0

REMOVE_BLIP	onfire_car_blip
REMOVE_BLIP	onfire_car_1_blip
REMOVE_BLIP	onfire_car_2_blip
REMOVE_BLIP	onfire_car_3_blip
REMOVE_BLIP	onfire_ped_blip
REMOVE_BLIP	onfire_ped_1_blip
REMOVE_BLIP	onfire_ped_2_blip
REMOVE_BLIP	onfire_ped_3_blip
REMOVE_BLIP	onfire_ped_4_blip
REMOVE_BLIP	onfire_ped_5_blip
REMOVE_BLIP	onfire_ped_6_blip
REMOVE_BLIP	onfire_ped_7_blip
REMOVE_BLIP	onfire_ped_8_blip
REMOVE_BLIP	onfire_ped_9_blip


IF NOT db_firetruck_level  = 13
	IF NOT IS_CHAR_DEAD	onfire_ped_1  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_1 FALSE		
		SET_CHAR_HEALTH onfire_ped_1 10
		SET_CHAR_PROOFS onfire_ped_1 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_1
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_2  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_2 FALSE
		SET_CHAR_HEALTH onfire_ped_2 10
		SET_CHAR_PROOFS onfire_ped_2 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_2
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_3  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_3 FALSE
		SET_CHAR_HEALTH onfire_ped_3 10
		SET_CHAR_PROOFS onfire_ped_3 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_3
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_4  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_4 FALSE
		SET_CHAR_HEALTH onfire_ped_4 10
		SET_CHAR_PROOFS onfire_ped_4 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_4
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_5  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_5 FALSE
		SET_CHAR_HEALTH onfire_ped_5 10
		SET_CHAR_PROOFS onfire_ped_5 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_5
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_6  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_6 FALSE
		SET_CHAR_HEALTH onfire_ped_6 10
		SET_CHAR_PROOFS onfire_ped_6 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_6
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_7  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_7 FALSE
		SET_CHAR_HEALTH onfire_ped_7 10
		SET_CHAR_PROOFS onfire_ped_7 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_7
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_8  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_8 FALSE
		SET_CHAR_HEALTH onfire_ped_8 10
		SET_CHAR_PROOFS onfire_ped_8 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_8
	ENDIF
	IF NOT IS_CHAR_DEAD	onfire_ped_9  
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER onfire_ped_9 FALSE
		SET_CHAR_HEALTH onfire_ped_9 10
		SET_CHAR_PROOFS onfire_ped_9 FALSE FALSE FALSE FALSE FALSE
		//EXPLODE_CHAR_HEAD onfire_ped_9
	ENDIF

	IF NOT IS_CAR_DEAD onfire_car_1
		SET_CAR_ONLY_DAMAGED_BY_PLAYER onfire_car_1 FALSE
		SET_CAR_PROOFS onfire_car_1 FALSE FALSE FALSE FALSE FALSE
		IF IS_CAR_ON_FIRE onfire_car_1
			EXPLODE_CAR	onfire_car_1
		ENDIF		
	ENDIF
	IF NOT IS_CAR_DEAD onfire_car_2
		SET_CAR_ONLY_DAMAGED_BY_PLAYER onfire_car_2 FALSE		
		SET_CAR_PROOFS onfire_car_2 FALSE FALSE FALSE FALSE FALSE
		IF IS_CAR_ON_FIRE onfire_car_2
			EXPLODE_CAR	onfire_car_2
		ENDIF
	ENDIF
	IF NOT IS_CAR_DEAD onfire_car_3
		SET_CAR_ONLY_DAMAGED_BY_PLAYER onfire_car_3 FALSE
		SET_CAR_PROOFS onfire_car_3 FALSE FALSE FALSE FALSE FALSE
		IF IS_CAR_ON_FIRE onfire_car_3
			EXPLODE_CAR	onfire_car_3
		ENDIF
	ENDIF
ENDIF

MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_1
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_2
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_3
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_4
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_5
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_6
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_7
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_8
MARK_CHAR_AS_NO_LONGER_NEEDED onfire_ped_9

MARK_CAR_AS_NO_LONGER_NEEDED onfire_car_1
MARK_CAR_AS_NO_LONGER_NEEDED onfire_car_2
MARK_CAR_AS_NO_LONGER_NEEDED onfire_car_3


MARK_MODEL_AS_NO_LONGER_NEEDED random_car_model

GET_GAME_TIMER timer_mobile_start

flag_player_on_mission = 0
flag_player_on_fire_mission	= 0

CLEAR_ALL_SCRIPT_FIRE_FLAGS
//REMOVE_ALL_SCRIPT_FIRES

REMOVE_DECISION_MAKER fire_decision

MISSION_HAS_FINISHED
IF done_firetruck_progress = 1
	CLEAR_HELP
	PRINT_HELP FIREPRO //"You will never get tired!"
	done_firetruck_progress = 2 
	SHOW_UPDATE_STATS FALSE
    START_NEW_SCRIPT switch_update_stats_back_on	
ENDIF

RETURN






}