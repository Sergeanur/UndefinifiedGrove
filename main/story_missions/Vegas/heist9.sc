MISSION_START

// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Heist 9 *******************************************
// *****************************************************************************************
// ************************************ The Heist  *****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME heist9

GOSUB mission_start_heist9

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_heist9_failed
ENDIF

GOSUB mission_cleanup_heist9										    

MISSION_END

{ 

VAR_INT h9_health h9_safe_timer

LVAR_FLOAT h9_door_Z

LVAR_INT flag_open_keypaddoor_heist9 h9_kpad_result	h9_guy_is_peeking

LVAR_INT h9_mission_status h9_casino_blip h9_heli_chasing

LVAR_INT h9_door_a h9_exitdoor h9_spawned_end_guys

LVAR_INT h9_blip_keycode h9_swipecard_blip

LVAR_INT h9_forklift h9_forklift_blip

LVAR_INT h9_door_d h9_door_e h9_forklift_message

LVAR_INT h9_getaway_car	h9_all_dead	h9_sequence_task

LVAR_INT h9_gang[4] v seq_walk_to_safe 

LVAR_INT h9_door_g h9_bag[4] h9_generator_blip

LVAR_INT h9_charge_set[4] h9_no_of_charges h9_charges[4]

LVAR_INT h9_fx1 h9_fx2 seq_walk_to_van

LVAR_INT h9_going_to_van[4] h9_van_count h9_seat_count

LVAR_INT h9_elevator h9_in_van[4] h9_decision

LVAR_INT h9_roller_door_blip

LVAR_INT h9_gang_blip[4] h9_back_in_the_forklift

LVAR_INT h9_swipe_pad h9_zero

LVAR_INT h9_parachute h9_chute_deleted h9_wanted_level h9_parachute1

LVAR_INT h9_safe_house h9_elevator_message h9_outside 

LVAR_INT h9_msg_displayed h9_closing h9_keycode_active h9_gas_blip

LVAR_INT h9_safe_guards[5] h9_vent h9_power_cut

LVAR_INT h9_body_count h9_closing_1	h9_closing_2 h9_blown h9_empty_decision

LVAR_INT h9_sync h9_fx_dyna	h9_picked_up_bag[5]	

LVAR_INT h9_safe_charge[2] h9_seq_pillar

LVAR_INT h9_bag_count h9_seq_mafia_in_safe_L h9_seq_mafia_in_safe_R h9_attack_safe[5]

LVAR_INT h9_gas_fx_[1] h9_cash_dialog

LVAR_INT h9_seq_stairs h9_gang_health[5] h9_ai h9_hlth[5] 

LVAR_INT h9_elevator_message_1 h9_seq_corridor h9_is_running[4]

LVAR_INT h9_seq_corridor_1 h9_seq_bag[5] h9_seq_room h9_rollin

LVAR_INT h9_seq_corridor_a h9_seq_corridor_b h9_seq_corridor_c

LVAR_INT h9_power_on h9_generator_A h9_generator_B

LVAR_INT h9_projected_man h9_van_not_needed_anymore 

LVAR_INT h9_grenade_count 

LVAR_FLOAT h9_bomb_heading x1 y1 z1

LVAR_FLOAT h9_x[4] h9_y[4] h9_z[4]

LVAR_INT temp_heading

LVAR_INT h9_help_1 h9_help_2

LVAR_INT h9_cutscene_man

LVAR_INT h9_fake_door

LVAR_INT h9_darkness h9_helicopter

LVAR_INT h9_bk_door	h9_zero_created

LVAR_INT h9_explosion_fx[6]

LVAR_INT h9_safe_door_blip

LVAR_INT h9_goggle_blip

LVAR_INT h9_spawn_forklift

LVAR_INT h9_used_vent

LVAR_INT h9_once_only

LVAR_INT h9_peekright		  

LVAR_INT h9_peekleft

LVAR_INT h9_satchel_bomb

LVAR_INT h9_start_conv

LVAR_INT h9_cut_test

LVAR_INT h9_weak_decision

LVAR_INT h9_workers[10]

LVAR_INT h9_gen_blip_a

LVAR_INT h9_gen_blip_b	

LVAR_INT h9_clothes_changed

LVAR_INT h9_playing 

LVAR_INT h9_audio h9_safe_blip

LVAR_INT h9_guys_at_safe

LVAR_INT h9_seq_safe_cover[4]
																										   
LVAR_INT h9_in_cover[4]

LVAR_INT h9_inside_txt

LVAR_INT h9_vent_room

LVAR_INT h9_decision_tough

LVAR_INT h9_gas_txt h9_chopper_blip

LVAR_INT h9_has_a_gun

LVAR_INT h9_service_txt

LVAR_INT h9_team_is_spawned

LVAR_INT h9_play_safe_dialogue

LVAR_INT h9_player_in_safe

LVAR_INT h9_spawn_the_dudes

LVAR_INT h9_trigger_lights

LVAR_INT h9_lift_conv h9_never_again

LVAR_INT h9_first_node h9_second_node h9_third_node 

LVAR_INT h9_seq_run_first_node[4] h9_seq_run_second_node h9_seq_run_third_node

LVAR_INT h9_second[4] h9_third[4]

LVAR_INT h9_kicked[4]

LVAR_INT h9_run_van_conv

LVAR_INT h9_bike_a h9_bike_b h9_biker_a h9_biker_b

LVAR_INT h9_spawned_end_guys_1

LVAR_INT h9_last_grenade

LVAR_INT h9_health_1 h9_kill_seconds

LVAR_FLOAT h9_heading

VAR_INT h9_player_in_casino

LVAR_TEXT_LABEL h9_print

LVAR_INT very_start_conv

LVAR_INT h9_run_to_van

LVAR_INT h9_roof_guy h9_roof_guy_1 h9_roof_trigger 

LVAR_INT h9_generator_mafia[3]

VAR_FLOAT h9_offset	h9_z1 

VAR_FLOAT h9_calculus h9_under_door

LVAR_INT h9_para_pickup	h9_satchel_blip h9_guns_out	h9_requested

LVAR_INT h9_behind_door h9_is_downstairs

LVAR_INT h9_guys_run_in	h9_door_sfx	h9_door_trig h9_missed_chopper

VAR_INT h9_TIMERC h9_can_see h9_fuck_off

// **************************************** Mission Start **********************************

mission_start_heist9:
	  
IF d5_bronze_generator_unlocked = 1

	SWITCH_CAR_GENERATOR d5_bronze_generator 0

ENDIF
IF d5_silver_generator_unlocked = 1

	SWITCH_CAR_GENERATOR d5_silver_generator 0

ENDIF
IF d5_gold_generator_unlocked = 1

	SWITCH_CAR_GENERATOR d5_gold_generator 0

ENDIF

SET_ZONE_NO_COPS MEAD TRUE

LOAD_MISSION_TEXT HEIST9
										  
h9_health = 0

h9_z1 = 0.0

h9_offset = 0.0

h9_calculus = 0.0

h9_under_door = 0.0

h9_TIMERC = 0

h9_can_see = 0

SET_FADING_COLOUR 0 0 0

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

iSetCasinoPanic = 0

h9_playing = 2

// Bug fix for Will M
// *****************************************************************************************

disable_mod_garage = 1

// *****************************************************************************************

WAIT 0

// *************************************** START OF CUTSCENE *******************************

IF NOT IS_CHAR_DEAD scplayer
 
	STORE_CLOTHES_STATE  
 
	GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1 croupier valet CLOTHES_TEX_EXTRA1

    GIVE_PLAYER_CLOTHES player1 0 0 1  // should remove any hat 

    GIVE_PLAYER_CLOTHES player1 0 0 13 // should remove any hat 
	       
    GIVE_PLAYER_CLOTHES player1 0 0 15 // should remove any hat 

    GIVE_PLAYER_CLOTHES player1 0 0 16 // should remove any hat      

	BUILD_PLAYER_MODEL player1

ENDIF

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

// *****************************************************************************************
// *																					   *
// *                                   Player Co-Ords				   					   *
// *																					   *
// *****************************************************************************************
 
IF NOT IS_CHAR_DEAD scplayer

	SET_AREA_VISIBLE 0

	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO

	LOAD_SCENE 2028.9316 1023.2923 9.8130

	CLEAR_AREA 2028.9316 1023.2923 9.8130 20.0 TRUE

	SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2028.9316 1023.2923 9.8130

	SET_CHAR_HEADING scplayer 278.4092

ENDIF

SET_AREA_VISIBLE 0

LOAD_CUTSCENE HEIST8a
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE

CLEAR_AREA 1908.4333 957.3687 9.8203 40.0 TRUE
 
START_CUTSCENE

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE

SET_FADING_COLOUR 0 0 0
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE 
 
CLEAR_CUTSCENE 

//SET_INT_STAT CITIES_PASSED 2

// *****************************************************************************************
// *																					   *
// *                                      Decisions     			   					   *
// *																					   *
// *****************************************************************************************

ADD_BLIP_FOR_COORD 2196.7146 1677.1975 11.3750 h9_casino_blip

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK h9_weak_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_NORM h9_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY h9_empty_decision

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH h9_decision_tough

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION3 PEDTYPE_MISSION3

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_MISSION3 PEDTYPE_PLAYER1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE h9_decision EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 0.0 100.0 0.0 0.0 TRUE TRUE

ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE h9_decision_tough EVENT_ACQUAINTANCE_PED_DISLIKE TASK_SIMPLE_LOOK_AT_ENTITY_OR_COORD 0.0 100.0 0.0 0.0 TRUE TRUE

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************

REQUEST_MODEL silenced
REQUEST_MODEL teargas
REQUEST_MODEL NVGOGGLES
REQUEST_MODEL bodyarmour
REQUEST_MODEL MP5LNG
REQUEST_MODEL KNIFECUR
REQUEST_MODEL COLT45
REQUEST_ANIMATION HEIST9

WHILE NOT HAS_MODEL_LOADED silenced
OR NOT HAS_MODEL_LOADED teargas
OR NOT HAS_MODEL_LOADED NVGOGGLES
OR NOT HAS_MODEL_LOADED bodyarmour
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED MP5LNG
OR NOT HAS_MODEL_LOADED KNIFECUR
OR NOT HAS_ANIMATION_LOADED HEIST9
OR NOT HAS_MODEL_LOADED COLT45
	WAIT 0
ENDWHILE

h9_safe_timer = 240000

CLEAR_WANTED_LEVEL player1

SET_WANTED_MULTIPLIER 0.0

SET_PLAYER_CONTROL player1 ON

CREATE_OBJECT GENERATOR_BIG 2145.05 1613.49 999.9764 h9_generator_A

SET_OBJECT_AREA_VISIBLE h9_generator_A 1
														
SET_OBJECT_HEADING h9_generator_A 1.2043 

CREATE_OBJECT GENERATOR_BIG 2148.8 1613.49 999.9764 h9_generator_B

SET_OBJECT_AREA_VISIBLE h9_generator_B 1

SET_OBJECT_HEADING h9_generator_B 354.0372

// **************************************************************************************

OPEN_SEQUENCE_TASK h9_peekright

	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R HEIST9 8.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_R HEIST9 8.0 FALSE TRUE TRUE FALSE -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 2000
	TASK_STAY_IN_SAME_PLACE -1 FALSE
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_R HEIST9 8.0 FALSE TRUE TRUE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_R_back HEIST9 8.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT h9_peekright 1

CLOSE_SEQUENCE_TASK h9_peekright

OPEN_SEQUENCE_TASK h9_peekleft

	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L HEIST9 8.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_in_L HEIST9 8.0 FALSE TRUE TRUE FALSE -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 2000
	TASK_STAY_IN_SAME_PLACE -1 FALSE
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllshoot_out_L HEIST9 8.0 FALSE TRUE TRUE FALSE -1
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 swt_wllpk_L_back HEIST9 8.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT h9_peekleft 1

CLOSE_SEQUENCE_TASK h9_peekleft

// **************************************************************************************

SET_GROUP_SEPARATION_RANGE Players_Group 300.0

SET_GROUP_DEFAULT_TASK_ALLOCATOR Players_Group DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS

GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL_SILENCED 300

GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_TEARGAS 10

GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MP5 300

GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_NIGHTVISION 300

GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_KNIFE 300
 
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_TEARGAS

MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
MARK_MODEL_AS_NO_LONGER_NEEDED SILENCED
MARK_MODEL_AS_NO_LONGER_NEEDED NVGOGGLES
MARK_MODEL_AS_NO_LONGER_NEEDED TEARGAS

// **************************************************************************************


// Keypad Door

CREATE_OBJECT_NO_OFFSET ab_casdorLok 2148.5600 1604.6091 1006.5000 h9_door_a

SET_OBJECT_AREA_VISIBLE h9_door_a 1

SET_OBJECT_HEADING h9_door_a 180.0000

// Back-Tracking Door

CREATE_OBJECT_NO_OFFSET ab_casdorLok 2177.1600 1594.3218 1000.4000 h9_door_d

SET_OBJECT_AREA_VISIBLE h9_door_d 1

SET_OBJECT_HEADING h9_door_d 180.0000

// Roller Door

CREATE_OBJECT_NO_OFFSET warehouse_door2b 2220.1880 1571.7629 1001.6000 h9_door_e

SET_OBJECT_AREA_VISIBLE h9_door_e 1

SET_OBJECT_HEADING h9_door_e 270.0000

SET_OBJECT_COLLISION_DAMAGE_EFFECT h9_door_e FALSE

// Top For Roller Door

CREATE_OBJECT_NO_OFFSET warehouse_door2b 2220.1880 1571.7000 1005.8000 h9_fake_door

SET_OBJECT_AREA_VISIBLE h9_fake_door 1

SET_OBJECT_HEADING h9_fake_door 270.0000

SET_OBJECT_COLLISION h9_fake_door FALSE

SET_OBJECT_COLLISION_DAMAGE_EFFECT h9_door_e FALSE

CREATE_OBJECT_NO_OFFSET warehouse_door2b 2203.2004 1551.7429 1009.0000 h9_exitdoor

SET_OBJECT_AREA_VISIBLE h9_exitdoor 1

SET_OBJECT_HEADING h9_exitdoor 0.0000

SET_OBJECT_COLLISION_DAMAGE_EFFECT h9_exitdoor FALSE

h9_door_Z = 1001.4000

// Safe Door
										
CREATE_OBJECT_NO_OFFSET ab_vaultDoor 2144.1800 1626.9470 994.2800 h9_door_g

SET_OBJECT_HEADING h9_door_g 180.0000  

SET_OBJECT_AREA_VISIBLE h9_door_g 1

// **************************************************************************************
								    
CREATE_OBJECT_NO_OFFSET sec_keypad 2168.5000 1617.3353 1000.4000 h9_swipe_pad

SET_OBJECT_AREA_VISIBLE h9_swipe_pad 1

SET_OBJECT_HEADING h9_swipe_pad 270.0000

SET_OBJECT_COLLISION h9_swipe_pad FALSE

// **************************************************************************************

// Health inside the small room

CREATE_PICKUP bodyarmour PICKUP_ONCE 2176.4712 1597.3944 1000.0000 h9_health

// **************************************************************************************

// Health inside the safe

CREATE_PICKUP bodyarmour PICKUP_ONCE 2144.3389 1640.6660 993.5000 h9_health_1

// **************************************************************************************

GOTO h9_skip_loop

spawn_the_gang:

	REQUEST_MODEL TRIADA
	REQUEST_MODEL SECURICA

	LOAD_SPECIAL_CHARACTER 2 WUZIMU

	LOAD_SPECIAL_CHARACTER 3 JANITOR

    WHILE NOT HAS_MODEL_LOADED TRIADA
	OR NOT HAS_MODEL_LOADED SECURICA
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
		WAIT 0
	ENDWHILE

	CREATE_CAR SECURICA 2220.3445 1551.7666 1003.7188 h9_getaway_car

	SET_VEHICLE_AREA_VISIBLE h9_getaway_car 1

	SET_CAR_HEADING	h9_getaway_car 176.7739

	SET_CAR_ALWAYS_CREATE_SKIDS h9_getaway_car FALSE

 	LOCK_CAR_DOORS h9_getaway_car CARLOCK_LOCKOUT_PLAYER_ONLY

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_MISSION2

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

	// Wuzimu
	CREATE_CHAR_INSIDE_CAR h9_getaway_car PEDTYPE_MISSION2 SPECIAL02 h9_gang[0]
	SET_CHAR_AREA_VISIBLE h9_gang[0] 1

	// Janitor
	CREATE_CHAR_AS_PASSENGER h9_getaway_car PEDTYPE_MISSION2 TRIADA 2 h9_gang[1]  
	SET_CHAR_AREA_VISIBLE h9_gang[1] 1

	// A brother
	CREATE_CHAR_AS_PASSENGER h9_getaway_car PEDTYPE_MISSION2 TRIADA 1 h9_gang[2]  
	SET_CHAR_AREA_VISIBLE h9_gang[2] 1

	// Second
	CREATE_CHAR_AS_PASSENGER h9_getaway_car PEDTYPE_MISSION2 SPECIAL03 0 h9_gang[3]
	SET_CHAR_AREA_VISIBLE h9_gang[3] 1

	SET_ANIM_GROUP_FOR_CHAR h9_gang[0] blindman

	REPEAT 4 v

		SET_FOLLOW_NODE_THRESHOLD_DISTANCE h9_gang[v] 2.0

		GIVE_WEAPON_TO_CHAR h9_gang[v] WEAPONTYPE_MP5 30000
		 
		SET_CURRENT_CHAR_WEAPON h9_gang[v] WEAPONTYPE_MP5

		SET_CHAR_DECISION_MAKER h9_gang[v] h9_empty_decision

		SET_CHAR_NEVER_TARGETTED h9_gang[v] TRUE

		SET_CHAR_PROOFS h9_gang[v] FALSE FALSE FALSE TRUE FALSE

		SET_CHAR_SUFFERS_CRITICAL_HITS h9_gang[v] FALSE

		SET_CHAR_MAX_HEALTH h9_gang[v] 500

		SET_CHAR_HEALTH h9_gang[v] 500

		SET_CHAR_ACCURACY h9_gang[v] 60

		SET_CHAR_SIGNAL_AFTER_KILL h9_gang[v] FALSE

	ENDREPEAT

	h9_team_is_spawned = 1

RETURN

h9_skip_loop:

IF NOT IS_CHAR_DEAD scplayer

	SET_CHAR_HEADING scplayer 270.0000
	
	SET_CAMERA_BEHIND_PLAYER  

ENDIF

GOSUB h9_fade_in

h9_start_conv = 1

h9_playing = 2

// *****************************************************************************************
// *													   								   *
// *							        START LOOP    									   *
// *																					   *
// *****************************************************************************************

h9_mission_status = 1

h9_keycode_active = 0

TIMERB = 0

// Turn off those Entry points

SWITCH_ENTRY_EXIT JUMP1 FALSE

SWITCH_ENTRY_EXIT JUMP2 FALSE

SWITCH_ENTRY_EXIT MAFCAS2 FALSE

// ************************************************************************************************
// *																							  *
// *                                   Skip the beginning 										  *
// *																							  *
// ************************************************************************************************


//begin0

	very_start_conv = 1

// GOING TO SAFE
/*	IF NOT IS_CHAR_DEAD scplayer
								   
		SET_AREA_VISIBLE 1

		LOAD_SCENE 2221.1829 1583.0457 998.9752

		REQUEST_COLLISION 2221.1829 1583.0457

		CLEAR_AREA 2221.1829 1583.0457 998.9752 10.0 TRUE

		SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2221.1829 1583.0457 998.9752  
		
		SET_CHAR_HEADING scplayer 166.3300
			
		SET_CHAR_AREA_VISIBLE scplayer 1

		RESTORE_CAMERA_JUMPCUT

		SET_CAMERA_BEHIND_PLAYER

	ENDIF 	  */

  //	h9_mission_status = 6

   //	GOSUB spawn_the_gang 

/*  	SET_OBJECT_HEADING h9_door_a 180.0000

	DELETE_OBJECT h9_door_c

	REQUEST_MODEL FORKLIFT
	REQUEST_MODEL BMYBOUN
	REQUEST_MODEL SWAT

	WHILE NOT HAS_MODEL_LOADED FORKLIFT
	OR NOT HAS_MODEL_LOADED BMYBOUN
	OR NOT HAS_MODEL_LOADED SWAT
		WAIT 0
	ENDWHILE
						
	REQUEST_MODEL satchel
	REQUEST_MODEL bomb

	WHILE NOT HAS_MODEL_LOADED satchel
	OR NOT HAS_MODEL_LOADED bomb
		WAIT 0
	ENDWHILE

	REQUEST_MODEL TRIADA
	REQUEST_MODEL TRIBOSS
	REQUEST_MODEL SECURICA
	REQUEST_ANIMATION SWAT
	REQUEST_MODEL 497

	LOAD_SPECIAL_CHARACTER 2 WUZIMU

    WHILE NOT HAS_MODEL_LOADED TRIADA
	OR NOT HAS_MODEL_LOADED TRIBOSS
	OR NOT HAS_MODEL_LOADED SECURICA
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
	OR NOT HAS_ANIMATION_LOADED SWAT
	OR NOT HAS_MODEL_LOADED 497
		WAIT 0
	ENDWHILE  */  

 /*	CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 2135.4968 1625.5176 992.5681 h9_gang[0]

	CREATE_CHAR PEDTYPE_MISSION2 TRIADA 2152.9541 1625.0764 992.5681 h9_gang[1]

	CREATE_CHAR PEDTYPE_MISSION2 TRIADA 2135.4968 1625.5176 992.5681 h9_gang[2]

	CREATE_CHAR PEDTYPE_MISSION2 TRIBOSS 2135.4968 1625.5176 992.5681 h9_gang[3]

	SET_ANIM_GROUP_FOR_CHAR h9_gang[0] blindman

	REPEAT 4 v

		SET_FOLLOW_NODE_THRESHOLD_DISTANCE h9_gang[v] 2.0

		GIVE_WEAPON_TO_CHAR h9_gang[v] WEAPONTYPE_MP5 30000
		 
		SET_CURRENT_CHAR_WEAPON h9_gang[v] WEAPONTYPE_MP5

		SET_CHAR_DECISION_MAKER h9_gang[v] h9_empty_decision

		SET_CHAR_NEVER_TARGETTED h9_gang[v] TRUE

		SET_CHAR_PROOFS h9_gang[v] FALSE FALSE FALSE TRUE FALSE

		SET_CHAR_SUFFERS_CRITICAL_HITS h9_gang[v] FALSE

		SET_CHAR_MAX_HEALTH h9_gang[v] 1000

		SET_CHAR_HEALTH h9_gang[v] 1000

		SET_CHAR_ACCURACY h9_gang[v] 60

		SET_CHAR_SIGNAL_AFTER_KILL h9_gang[v] FALSE

		SET_SENSE_RANGE h9_gang[v] 50.0

	ENDREPEAT */ 
/*
	// Creates forklift
	CREATE_CAR FORKLIFT 2173.6206 1585.9639 998.9722 h9_forklift

	SET_VEHICLE_AREA_VISIBLE h9_forklift 1 

	SET_CAR_HEADING h9_forklift 270.0

	CHANGE_CAR_COLOUR h9_forklift CARCOLOUR_TAXIYELLOW CARCOLOUR_TAXIYELLOW
				
	h9_spawn_forklift = 1													   

	IF NOT IS_CAR_DEAD h9_forklift 

		SET_VEHICLE_AREA_VISIBLE h9_forklift 1

		WARP_CHAR_INTO_CAR scplayer h9_forklift

	ENDIF 	  */

	// AT THE SAFE
 /*	IF NOT IS_CHAR_DEAD scplayer
								   
		SET_AREA_VISIBLE 1

		LOAD_SCENE 2221.1829 1583.0457 998.9752

		REQUEST_COLLISION 2221.1829 1583.0457

		CLEAR_AREA 2221.1829 1583.0457 998.9752 10.0 TRUE

		SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2144.2825 1619.3787 992.6882  
		
		SET_CHAR_HEADING scplayer 1.1102
			
		SET_CHAR_AREA_VISIBLE scplayer 1

		RESTORE_CAMERA_JUMPCUT

		SET_CAMERA_BEHIND_PLAYER

	ENDIF 	 */

	// AT THE VAN
/*	IF NOT IS_CHAR_DEAD scplayer
								   
		SET_AREA_VISIBLE 1

		LOAD_SCENE 2224.1978 1592.6315 998.9697

		REQUEST_COLLISION 2224.1978 1592.6315

		CLEAR_AREA 2224.1978 1592.6315 998.9697 10.0 TRUE

		SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2224.1978 1592.6315 998.9697    
		
		SET_CHAR_HEADING scplayer 357.8299
			
		SET_CHAR_AREA_VISIBLE scplayer 1

		RESTORE_CAMERA_JUMPCUT

		SET_CAMERA_BEHIND_PLAYER

	ENDIF  */

 //	h9_mission_status = 13

 //	h9_blown = 1 

	// ************************************************************************************************
	// *																							  *
	// *                                  		 New stuff		 									  *
	// *																							  *
	// ************************************************************************************************

	LVAR_INT h9_crt_0 h9_crt_1 h9_crt_2 h9_crt_3 h9_stop_door

	// Keypad Door

	CREATE_OBJECT_NO_OFFSET tmp_bin 2188.8450 1619.1359  999.3000 h9_crt_0

	SET_OBJECT_AREA_VISIBLE h9_crt_0 1

	SET_OBJECT_HEADING h9_crt_0 270.0000

 /*	CREATE_OBJECT_NO_OFFSET k_smashboxes 2147.1306 1613.9847 992.5000 h9_crt_1

	SET_OBJECT_AREA_VISIBLE h9_crt_1 1

	SET_OBJECT_HEADING h9_crt_1 355.3719 */

	CREATE_OBJECT_NO_OFFSET k_smashboxes 2139.3428 1617.6035 992.5000 h9_crt_2

	SET_OBJECT_AREA_VISIBLE h9_crt_2 1

	SET_OBJECT_HEADING h9_crt_2 0.1132

	CREATE_OBJECT_NO_OFFSET k_smashboxes 2148.0000 1622.1074 992.5000 h9_crt_3

	SET_OBJECT_AREA_VISIBLE h9_crt_3 1

	SET_OBJECT_HEADING h9_crt_3 179.6736  

 //	h9_is_downstairs = 1

//	GOTO try_here

 //	GOTO try_again_here

	// *** For Chopper *****************************************************************************

  /*DISABLE_PLAYER_SPRINT player1 TRUE 

 	h9_mission_status = 17

	h9_roof_trigger = 1

	h9_chute_deleted = 1 

 	REQUEST_MODEL gun_para

	WHILE NOT HAS_MODEL_LOADED gun_para
		WAIT 0
	ENDWHILE

	CREATE_PICKUP gun_para PICKUP_ONCE 2267.9888 1699.6678 101.4000 h9_para_pickup

	para_freefall_Vz = -6.0

	para_freefall_Vy = 35.0

	para_float_Vy = 15.0

	para_flare_Vy = 20.0 //for when player lifts legs up

	SET_INT_STAT CITIES_PASSED 2

	GOTO to_here */ 

	// *** End Chopper *****************************************************************************
	 

// ************************************************************************************************
// *																							  *
// *                                   Skip the beginning 										  *
// *																							  *
// ************************************************************************************************

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0

	GOSUB h9_keys 

	GOSUB h9_play_sample
	
	GET_AREA_VISIBLE h9_fuck_off 

	// ********************************************************************************************
	// *																						  *
	// *						   Dialogue when inside mafia casino							  *
	// *																						  *
	// ********************************************************************************************

	IF h9_safe_timer <= 0

		GOSUB h9_fix_for_failed

		PRINT_NOW ( HM9_1B ) 4000 1 // ~r~You ran out of time

		GOTO mission_heist9_failed

		CLEAR_ONSCREEN_TIMER h9_safe_timer

	ENDIF

	IF NOT very_start_conv = 0 
		
		IF h9_playing = 2
		AND very_start_conv = 1

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

			ENDIF

			$h9_print = &HE8_AA	// Ok, Carl, can you hear me ok?
			h9_audio = SOUND_HE8_AA
			GOSUB h9_load_sample

			very_start_conv = 2

		ENDIF
		
		IF h9_playing = 2
		AND very_start_conv = 2

			IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 10000

			ENDIF

			$h9_print = &HE8_AB	// Loud and clear!
			h9_audio = SOUND_HE8_AB
			GOSUB h9_load_sample

			very_start_conv = 3

		ENDIF
		
		IF h9_playing = 2
		AND very_start_conv = 3

			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			$h9_print = &HE8_AC	// Ok, we're en route in the armoured truck.
			h9_audio = SOUND_HE8_AC
			GOSUB h9_load_sample

			very_start_conv = 4

		ENDIF
		
		IF h9_playing = 2
		AND very_start_conv = 4

			IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 10000

			ENDIF

			$h9_print = &HE8_AD	// Ok, I'm gonna get a move on!
			h9_audio = SOUND_HE8_AD
			GOSUB h9_load_sample

			very_start_conv = 5

		ENDIF

		IF h9_playing = 2
		AND very_start_conv = 5

			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			PRINT_NOW ( HM9_28 ) 9000 1 // ~s~Enter the ~y~Mafia Casino~s~!

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

			ENDIF

			very_start_conv = 6

		ENDIF

	ENDIF

	GET_AREA_VISIBLE h9_can_see
	
	IF h9_can_see = 0

		IF NOT IS_CHAR_DEAD scplayer

			DISABLE_PLAYER_SPRINT player1 FALSE

			iSetCasinoPanic = 0

			h9_player_in_casino = 0

			SET_RADAR_ZOOM 0

		ENDIF

	ELSE

		IF NOT IS_CHAR_DEAD scplayer

			DISABLE_PLAYER_SPRINT player1 TRUE

			SET_RADAR_ZOOM 100

		ENDIF

	ENDIF 

	// Is player inside the mafia casino
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2233.9099 1712.5991 1010.8772 50.0 50.0 50.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2265.7898 1619.5800 1089.4453 50.0 50.0 50.0 FALSE
	OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2267.0557 1675.3851 1089.4453 50.0 50.0 50.0 FALSE

		SET_RADAR_ZOOM 100

		h9_player_in_casino = 1

	ENDIF

	IF h9_is_downstairs = 0
	AND h9_player_in_casino = 1

		IF NOT IS_CHAR_DEAD scplayer
			
			IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_NIGHTVISION
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_TEARGAS
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
			AND NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_KNIFE

				SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_CIVMALE PEDTYPE_PLAYER1

			    PRINT_NOW ( HM9_99 ) 100 1 // ~s~Holster your weapon until your passed the staff door!

				h9_guns_out = 1

			ELSE

				SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_CIVMALE PEDTYPE_PLAYER1

				h9_guns_out = 0

			ENDIF

		ENDIF

	ENDIF

	IF NOT IS_PLAYER_WEARING player1 CLOTHES_TEX_EXTRA1	croupier

		PRINT_NOW ( HM9_52 ) 4000 1 // ~r~You blew your disguise, scrub the mission!

		GOTO mission_heist9_failed

	ENDIF

	IF iSetCasinoPanic = 1
	AND h9_closing = 0
	AND h9_player_in_casino = 1

		GOSUB h9_fix_for_failed

		PRINT_NOW ( HM9_52 ) 4000 1 // ~r~You blew your disguise, scrub the mission!

		GOTO mission_heist9_failed

	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2180.9810 1677.8132 10.0609 30.0 30.0 30.0 FALSE
		AND h9_inside_txt = 0

			PRINT_NOW ( HM9_54 ) 7000 1 // Remember not to attract attention or you'll blow your cover
				
			h9_inside_txt = 1

		ENDIF
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S

		GOTO mission_heist9_passed 

	ENDIF

	IF h9_mission_status <= 2
	AND NOT IS_CHAR_DEAD scplayer
	AND h9_last_grenade = 0

		GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_TEARGAS h9_grenade_count

		IF h9_grenade_count <= 0
		AND h9_mission_status <= 2

			h9_last_grenade = 1

			TIMERA = 0

		ENDIF 
		
	ENDIF

	IF TIMERA > 2000
	AND h9_last_grenade = 1
	AND h9_projected_man = 0

		IF h9_player_in_casino = 1
		OR h9_can_see = 0

			IF h9_player_in_casino = 1

				GOSUB h9_fix_for_failed				  

			ELSE										   

				GOSUB h9_rebuild_the_player	   

			ENDIF

			PRINT_NOW ( HM9_48 ) 4000 1 // ~r~You idiot you wasted all the grenades!	 

			GOTO mission_heist9_failed	   

		ENDIF											  

	ENDIF

	IF IS_CAR_DEAD h9_getaway_car
	AND h9_van_not_needed_anymore = 0
	AND h9_team_is_spawned = 1

		GOSUB h9_fix_for_failed							    

		PRINT_NOW ( HM9_15 ) 4000 1 // ~r~The van is gone, there is no way to transport the money.

		GOTO mission_heist9_failed

	ENDIF												    

	h9_TIMERC ++

	IF h9_mission_status = 1
											 
		IF h9_player_in_casino = 1

			IF DOES_BLIP_EXIST h9_casino_blip
				REMOVE_BLIP h9_casino_blip
			ENDIF

			CLEAR_PRINTS
																	 
			h9_mission_status = 2

			h9_start_conv = 1

			TIMERB = 0

			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

			ADD_BLIP_FOR_COORD 2148.9954 1605.0804 1005.1725 h9_blip_keycode 
			SET_BLIP_ENTRY_EXIT h9_blip_keycode 2196.6353 1676.8411 10.0
					  
			REQUEST_MODEL BMYBOUN
			REQUEST_MODEL WMYBOUN

			WHILE NOT HAS_MODEL_LOADED BMYBOUN													  
			OR NOT HAS_MODEL_LOADED WMYBOUN				    

				WAIT 0

			ENDWHILE				  

			CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2146.5798 1622.2280 992.6882 h9_safe_guards[0]

			SET_CHAR_AREA_VISIBLE h9_safe_guards[0] 1

			SET_CHAR_HEADING h9_safe_guards[0] 83.6070 
			
			CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2141.4099 1626.0682 992.6882 h9_safe_guards[1]

			SET_CHAR_AREA_VISIBLE h9_safe_guards[1] 1

			SET_CHAR_HEADING h9_safe_guards[1] 188.011 
						
			CREATE_CHAR PEDTYPE_CIVMALE WMYBOUN 2151.1873 1605.8324 1005.1875 h9_safe_guards[2]
														   
			SET_CHAR_AREA_VISIBLE h9_safe_guards[2] 1

			SET_CHAR_HEADING h9_safe_guards[2] 359.3460
																								 
			SET_CHAR_DECISION_MAKER h9_safe_guards[2] h9_empty_decision

			TASK_STAY_IN_SAME_PLACE h9_safe_guards[2] TRUE

			REPEAT 2 v
						
			   	GIVE_WEAPON_TO_CHAR h9_safe_guards[v] WEAPONTYPE_MP5 30000

			  	SET_CURRENT_CHAR_WEAPON h9_safe_guards[v] WEAPONTYPE_MP5

				ADD_ARMOUR_TO_CHAR h9_safe_guards[v] 200

			 	TASK_STAY_IN_SAME_PLACE h9_safe_guards[v] TRUE

				SET_CHAR_NEVER_TARGETTED h9_safe_guards[v] TRUE

			ENDREPEAT

		ENDIF		  

	ENDIF 

	// *************************************************************************************************
	// *																							   *
	// *					   	           GO TO THE KEYCODE DOOR				          		       *
	// *                                  													   		   *
	// *************************************************************************************************
	
	IF h9_mission_status = 2

		IF NOT IS_CHAR_DEAD h9_safe_guards[2]

			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR h9_safe_guards[2] scplayer
				  
				GOSUB h9_fix_for_failed
								
				PRINT_NOW ( HM9_52 ) 4000 1 //  ~r~You blew your disguise, scrub the mission

				GOTO mission_heist9_failed

			ENDIF

		ENDIF

		IF IS_CHAR_DEAD h9_safe_guards[2]				   
		AND h9_closing = 0 

			GOSUB h9_fix_for_failed

			PRINT_NOW ( HM9_52 ) 4000 1 //  ~r~You blew your disguise, scrub the mission

			GOTO mission_heist9_failed

		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *						   Dialogue when inside mafia casino							  *
		// *																						  *
		// ********************************************************************************************

		IF NOT h9_start_conv = 0 
			
			IF h9_start_conv = 1

				h9_safe_timer = 240000

				DISPLAY_ONSCREEN_TIMER_WITH_STRING h9_safe_timer TIMER_DOWN HM9_1A // TIME

				PRINT_WITH_NUMBER_NOW ( HM9_1D ) 4 5000 1 // ~s~You have ~1~ minutes to get the team inside!

				TIMERB = 0

				h9_start_conv = 2

			ENDIF

			IF TIMERB > 4000
			AND h9_start_conv = 2

				IF NOT IS_CHAR_DEAD scplayer

					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

				ENDIF

				$h9_print = &HE8_BA	// Ok, this is it. Play it cool, play it cool.
				h9_audio = SOUND_HE8_BA
				GOSUB h9_load_sample

				h9_start_conv = 3

			ENDIF
			
			IF h9_playing = 2
			AND h9_start_conv = 3

				IF NOT IS_CHAR_DEAD scplayer

					START_CHAR_FACIAL_TALK scplayer 10000

				ENDIF

				$h9_print = &HE8_BB	// Hey, I’m cool!
				h9_audio = SOUND_HE8_BB
				GOSUB h9_load_sample

				h9_start_conv = 4
											    
			ENDIF
			
			IF h9_playing = 2
			AND h9_start_conv = 4

				IF NOT IS_CHAR_DEAD scplayer

					STOP_CHAR_FACIAL_TALK scplayer

				ENDIF

				$h9_print = &HE8_BC	// You sure? You sound edgy to me!
				h9_audio = SOUND_HE8_BC
				GOSUB h9_load_sample

				h9_start_conv = 5

			ENDIF
			
			IF h9_playing = 2
			AND h9_start_conv = 5

				IF NOT IS_CHAR_DEAD scplayer

					START_CHAR_FACIAL_TALK scplayer 10000

				ENDIF

				$h9_print = &HE8_BD	// I’m cool, ok?
				h9_audio = SOUND_HE8_BD
				GOSUB h9_load_sample

				h9_start_conv = 6

			ENDIF

			IF h9_playing = 2
			AND h9_start_conv = 6

				IF NOT IS_CHAR_DEAD scplayer

					STOP_CHAR_FACIAL_TALK scplayer

				ENDIF

				$h9_print = &HE8_BE	// Ok, ok!
				h9_audio = SOUND_HE8_BE
				GOSUB h9_load_sample

				h9_start_conv = 7

			ENDIF 

			IF h9_playing = 2
			AND h9_start_conv = 7

				$h9_print = &HE8_BF	// Now, make your way to the staff door.
				h9_audio = SOUND_HE8_BF
				GOSUB h9_load_sample

				h9_start_conv = 8

			ENDIF

			IF h9_playing = 2
			AND h9_start_conv = 8

				IF NOT IS_CHAR_DEAD scplayer

					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

				ENDIF

				PRINT_NOW ( HM9_2 ) 7000 1 // ~s~Find and open the ~y~Keycode door~s~!

				h9_start_conv = 0

			ENDIF							    

		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *					   	     Dialogue when confronting Guard							  *
		// *																						  *
		// ********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2147.7559 1605.4012 1005.1703 3.0 3.0 3.0 FALSE
		AND h9_cut_test = 0

			IF h9_guns_out = 1

				GOSUB h9_fix_for_failed
					
				PRINT_NOW ( HM9_52 ) 4000 1 // ~r~You blew your disguise, scrub the mission!

				GOTO mission_heist9_failed

			ENDIF

			GOSUB h9_set_camera

			IF NOT IS_CHAR_DEAD scplayer				    
			AND NOT IS_CHAR_DEAD h9_safe_guards[2]

				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2147.6750 1607.3992 1005.1875 
				
				SET_CHAR_HEADING scplayer 188.6317 

				SET_PLAYER_CONTROL player1 OFF

				TASK_TURN_CHAR_TO_FACE_CHAR scplayer h9_safe_guards[2]

				TASK_TURN_CHAR_TO_FACE_CHAR h9_safe_guards[2] scplayer

			ENDIF

			IF NOT IS_CHAR_DEAD h9_safe_guards[2]
										
				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_safe_guards[2] 2151.1873 1605.8324 1005.1875 

				SET_CHAR_HEADING h9_safe_guards[2] 359.3460

			ENDIF

			SET_FIXED_CAMERA_POSITION 2152.1250 1606.5023 1006.7156 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2151.1345 1606.3650 1006.7191 JUMP_CUT

			PRINT_NOW ( HE8_CA ) 4000 1 // Ain't seen you around here before. You new?
			
			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_CA // Ain't seen you around here before. You new?

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1
													   
			WAIT 1000

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_talk_to_guy
				ENDIF
			ENDWHILE

			IF NOT IS_CHAR_DEAD scplayer

				TASK_PLAY_ANIM scplayer IDLE_chat PED 1.0 FALSE FALSE FALSE FALSE -1
												  
			ENDIF

			PRINT_NOW ( HE8_CB ) 4000 1 // Yeah, I'm, eerr standing in for Jerry he's ill.

			CLEAR_MISSION_AUDIO 1						 

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_CB // Yeah, I'm, eerr standing in for Jerry he's ill.

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1
																		  
			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1	
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_talk_to_guy
				ENDIF
			ENDWHILE

			PRINT_NOW ( HE8_CC ) 4000 1 // Who's Jerry?

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_CC // Who's Jerry?

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0								    
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_talk_to_guy
				ENDIF
			ENDWHILE

			PRINT_NOW ( HE8_CD ) 2000 1 // Shut up.

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_CD // Shut up.

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1		  
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_talk_to_guy
				ENDIF
			ENDWHILE

			PRINT_NOW ( HE8_CE ) 2000 1 // What?

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_CE // What?

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_talk_to_guy
				ENDIF
			ENDWHILE

			IF NOT IS_CHAR_DEAD scplayer

				TASK_PLAY_ANIM scplayer IDLE_tired PED 1.0 FALSE FALSE FALSE FALSE -1

			ENDIF

			PRINT_NOW ( HE8_CF ) 3000 1 // (Cough) Think I got Jerry's cough!

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_CF // (Cough) Think I got Jerry's cough!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_talk_to_guy
				ENDIF
			ENDWHILE

			h9_talk_to_guy:

			CLEAR_MISSION_AUDIO 1

			IF NOT IS_CHAR_DEAD scplayer
			AND NOT IS_CHAR_DEAD h9_safe_guards[2]

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				SET_CHAR_HEADING h9_safe_guards[2] 3.8184  

				SET_CHAR_HEADING scplayer 207.6449 

			ENDIF
	
			GOSUB h9_restore_camera

			h9_start_conv = 0

			h9_cut_test = 1

		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *						     Spawn workers when door is opened							  *
		// *																						  *
		// ********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2149.0076 1605.2992 1005.1722 2.0 2.0 2.0 FALSE
		AND h9_kpad_result = 0

		 	IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 
		 		PRINT_HELP_FOREVER HM9_29
		 	ENDIF

			IF IS_BUTTON_PRESSED PAD1 TRIANGLE

				IF NOT IS_CHAR_DEAD scplayer
				AND NOT IS_PLAYER_DEAD player1

					SET_PLAYER_CONTROL player1 OFF
					
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2149.0076 1605.2992 1005.1722 

					SET_CHAR_HEADING scplayer 188.7539

					TASK_PLAY_ANIM scplayer Use_SwipeCard HEIST9 2.0 FALSE FALSE FALSE FALSE -1
					
				ENDIF	

				WAIT 500

	 			CLEAR_MISSION_AUDIO 3

				LOAD_MISSION_AUDIO 3 SOUND_DOOR_BUZZER

				WHILE NOT HAS_MISSION_AUDIO_LOADED 3
					WAIT 0
				ENDWHILE

				IF HAS_MISSION_AUDIO_LOADED 3

					PLAY_MISSION_AUDIO 3

				ENDIF

				WAIT 1000

				IF NOT IS_CHAR_DEAD scplayer
				AND NOT IS_PLAYER_DEAD player1

					SET_PLAYER_CONTROL player1 ON

				ENDIF

				h9_is_downstairs = 1

				h9_kpad_result = 1

		    ENDIF

		ELSE

			CLEAR_HELP 
		    		
		ENDIF
									  
    	IF h9_kpad_result = 1
		AND h9_keycode_active = 0

			CLEAR_HELP						  

			REQUEST_MODEL VBFYCRP
			REQUEST_MODEL VWFYCRP
														  
			WHILE NOT HAS_MODEL_LOADED VWFYCRP
			OR NOT HAS_MODEL_LOADED VBFYCRP
				WAIT 0
			ENDWHILE

			OPEN_SEQUENCE_TASK h9_sequence_task

				FLUSH_ROUTE

				EXTEND_ROUTE 2144.0095 1603.0858 1000.9680 
				EXTEND_ROUTE 2163.4092 1602.7787 998.9785 
				EXTEND_ROUTE 2158.4568 1602.3599 998.9719  
				EXTEND_ROUTE 2158.1924 1597.8748 998.9711  
				EXTEND_ROUTE 2157.9609 1612.8350 998.9677  

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE

				FLUSH_ROUTE 

				EXTEND_ROUTE 2158.3953 1618.5536 998.9647
				EXTEND_ROUTE 2157.9609 1612.8350 998.9677 
				EXTEND_ROUTE 2158.1924 1597.8748 998.9711 
				EXTEND_ROUTE 2158.4568 1602.3599 998.9719 
				EXTEND_ROUTE 2163.4092 1602.7787 998.9785

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE

				FLUSH_ROUTE	

				SET_SEQUENCE_TO_REPEAT h9_sequence_task 1

			CLOSE_SEQUENCE_TASK h9_sequence_task

			CREATE_CHAR PEDTYPE_MISSION3 VWFYCRP 2144.0095 1603.0858 1000.9680 h9_workers[0]
																				 
			SET_CHAR_AREA_VISIBLE h9_workers[0] 1
			
			SET_CHAR_DECISION_MAKER h9_workers[0] h9_decision

			PERFORM_SEQUENCE_TASK h9_workers[0] h9_sequence_task

			CREATE_CHAR PEDTYPE_MISSION3 VBFYCRP 2148.3943 1596.7374 998.7680 h9_workers[1]
																				 
			SET_CHAR_AREA_VISIBLE h9_workers[1] 1
			
			SET_CHAR_DECISION_MAKER h9_workers[1] h9_decision

			CREATE_CHAR PEDTYPE_MISSION3 VWFYCRP 2149.3550 1597.1329 998.7680 h9_workers[2]
																				 
			SET_CHAR_AREA_VISIBLE h9_workers[2] 1
			
			SET_CHAR_DECISION_MAKER h9_workers[2] h9_decision 

			CREATE_CHAR PEDTYPE_MISSION3 BMYBOUN 2146.8313 1603.9417 1000.9680 h9_workers[4]   

			SET_CHAR_HEADING h9_workers[4] 187.4626  
																				 
			SET_CHAR_AREA_VISIBLE h9_workers[4] 1

			GIVE_WEAPON_TO_CHAR h9_workers[4] WEAPONTYPE_PISTOL 30000
																	    
			SET_CURRENT_CHAR_WEAPON h9_workers[4] WEAPONTYPE_PISTOL

			SET_CHAR_DECISION_MAKER h9_workers[4] h9_decision_tough

			TASK_STAY_IN_SAME_PLACE h9_workers[4] TRUE

			CREATE_CHAR PEDTYPE_MISSION3 VWFYCRP 2170.6738 1603.3708 998.9696 h9_workers[5]
																				 
			SET_CHAR_AREA_VISIBLE h9_workers[5] 1
			
			SET_CHAR_DECISION_MAKER h9_workers[5] h9_decision

			CREATE_CHAR PEDTYPE_MISSION3 VWFYCRP 2169.8433 1605.2988 998.9706 h9_workers[6]
																				 
			SET_CHAR_AREA_VISIBLE h9_workers[6] 1
			
			SET_CHAR_DECISION_MAKER h9_workers[6] h9_decision

			TASK_CHAT_WITH_CHAR h9_workers[1] h9_workers[2] TRUE TRUE

			TASK_CHAT_WITH_CHAR h9_workers[2] h9_workers[1] FALSE TRUE

			TASK_CHAT_WITH_CHAR h9_workers[5] h9_workers[6] TRUE TRUE

			TASK_CHAT_WITH_CHAR h9_workers[6] h9_workers[5] FALSE TRUE

			h9_closing_1 = 1

			IF NOT IS_CHAR_DEAD scplayer

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
															  
			ENDIF

			CLEAR_SEQUENCE_TASK h9_sequence_task

			REMOVE_BLIP h9_blip_keycode

			warp_me_to_here_now:

        	flag_open_keypaddoor_heist9 = 1

			ADD_BLIP_FOR_COORD 2143.0459 1620.6610 999.9680 h9_gas_blip
			SET_BLIP_ENTRY_EXIT h9_gas_blip 2196.6353 1676.8411 10.0	 

			h9_keycode_active = 1

			h9_behind_door = 2

			TIMERA = 0

		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *						   Activate Dialogue after keycode  							  *
		// *																						  *
		// ********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2147.8630 1599.6487 1003.5341 2.0 2.0 2.0 FALSE
		AND h9_closing = 0

			h9_closing = 1
		
			CLEAR_AREA 2192.6055 1601.9197 1004.0625 100.0 TRUE

			SET_PED_DENSITY_MULTIPLIER 0.0

			DELETE_CHAR h9_safe_guards[2]

			TIMERB = 0

							  		
		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *						        Dialogue after keycode  								  *
		// *																						  *
		// ********************************************************************************************

		IF h9_playing = 2
		AND h9_behind_door = 2
		AND TIMERA > 1000

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

			ENDIF

			$h9_print = &HE8_DB	// Right. Next stop, the backup generator room.
			h9_audio = SOUND_HE8_DB
			GOSUB h9_load_sample

			h9_behind_door = 3

		ENDIF

		IF h9_playing = 2
		AND h9_behind_door = 3

			$h9_print = &HE8_DD	// It’s down one level.
			h9_audio = SOUND_HE8_DD
			GOSUB h9_load_sample

			h9_behind_door = 0

		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *						     Dialogue when inside Vent Room								  *
		// *																						  *
		// ********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2148.2520 1605.9012 1000.1144 1.2 1.2 1.2 FALSE
	 	AND h9_vent_room = 0
 
			h9_vent_room = 1

			h9_behind_door = 0

		ENDIF

		IF h9_playing = 2
		AND h9_vent_room = 1

			h9_is_downstairs = 1

			IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 10000

			ENDIF

			$h9_print = &HE8_EA	// Ok, I’m in the generator room.
			h9_audio = SOUND_HE8_EA
			GOSUB h9_load_sample

			h9_vent_room = 2

		ENDIF

		IF h9_playing = 2
		AND h9_vent_room = 2

			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			$h9_print = &HE8_EB	// Ok, the ventilation grills are on the back wall. 
			h9_audio = SOUND_HE8_EB
			GOSUB h9_load_sample

			h9_vent_room = 3

		ENDIF

		IF h9_playing = 2
		AND h9_vent_room = 3

			$h9_print = &HE8_EC	// Throw the gas down one of these. 
			h9_audio = SOUND_HE8_EC
			GOSUB h9_load_sample

			h9_vent_room = 4

		ENDIF

		IF h9_playing = 2
		AND h9_vent_room = 4

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

			ENDIF

			$h9_print = &HE8_ED	// You got it.
			h9_audio = SOUND_HE8_ED
			GOSUB h9_load_sample

			h9_vent_room = 5							 

		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *						         If at swipe-card door  								  *
		// *																						  *
		// ********************************************************************************************
			
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2254.5432 1706.5000 1.1746 1.2 1.2 1.2 FALSE

			PRINT_NOW ( HM9_36 ) 1000 1 // Gas the guards first!

		ENDIF

		IF h9_closing < 45
		AND NOT h9_closing = 0

			ROTATE_OBJECT h9_door_a 180.0000 2.0 FALSE
			
			h9_closing ++

		ENDIF
		
		IF h9_closing_1 < 45
		AND NOT h9_closing_1 = 0

			ROTATE_OBJECT h9_door_a 270.0000 2.0 FALSE
			
			h9_closing_1 ++

		ENDIF 

		REPEAT 2 v

			IF NOT IS_CHAR_DEAD h9_safe_guards[v]

				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR h9_safe_guards[v] scplayer

					GOSUB h9_fix_for_failed

					PRINT_NOW ( HM9_52 ) 4000 1 // ~r~You blew your disguise, scrub the mission!

					GOTO mission_heist9_failed

				ENDIF

				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR scplayer h9_safe_guards[v]

					GOSUB h9_fix_for_failed

					PRINT_NOW ( HM9_52 ) 4000 1 // ~r~You blew your disguise, scrub the mission!

					GOTO mission_heist9_failed

				ENDIF
					
				IF HAS_CHAR_SPOTTED_CHAR h9_safe_guards[v] scplayer

					GOSUB h9_fix_for_failed

					PRINT_NOW ( HM9_37 ) 4000 1 // The guards saw you and raised the alarm

					GOTO mission_heist9_failed

				ENDIF

			ENDIF

		ENDREPEAT 

		// ********************************************************************************************
		// *																						  *
		// *						           Activates the vent      								  *
		// *																						  *
		// ********************************************************************************************

   		IF IS_PROJECTILE_IN_AREA 2144.4636 1621.2595 1002.1362 2136.2090 1626.8770 997.7474	    
		AND h9_projected_man = 0

			h9_projected_man = 1

			h9_behind_door = 0

			h9_vent_room = 0

			// Kill all of the above ambient scripts

			SWITCH_OBJECT_BRAINS CASINO_OBJECT_BRAIN FALSE

			iTerminateAllAmbience = 1 

			TIMERA = 0

			GOSUB h9_fade_out									   

			GOSUB spawn_the_gang

		ENDIF

		// ********************************************************************************************
		// *																						  *
		// *						           Inside the vent room    								  *
		// *																						  *
		// ********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2145.7903 1613.8511 999.9680 5.0 5.0 5.0 FALSE
		AND h9_vent = 0
		AND h9_projected_man = 0 

			h9_vent = 1

			CLEAR_AREA 2144.3232 1622.6840 992.5681 30.0 TRUE

			REPEAT 2 v

				IF NOT IS_CHAR_DEAD h9_safe_guards[v]
				
					CLEAR_CHAR_TASKS_IMMEDIATELY h9_safe_guards[v]

				ENDIF
															    
			ENDREPEAT

		ENDIF  

		// ********************************************************************************************
		// *																						  *
		// *						             Play gas cutscene       							  *
		// *																						  *
		// ********************************************************************************************

		//gas0
		IF h9_projected_man = 1

			h9_shortcut:

			h9_is_downstairs = 1

			CLEAR_PRINTS

			SWITCH_ENTRY_EXIT MAFCAS FALSE

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

			ENDIF

			GOSUB h9_set_camera
									   
			SET_FIXED_CAMERA_POSITION 2139.8501 1609.2365 995.7649 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2140.2295 1610.1453 995.5919 JUMP_CUT

			REQUEST_MODEL teargas 

			WHILE NOT HAS_MODEL_LOADED teargas
				WAIT 0
			ENDWHILE

			CLEAR_AREA 2144.3232 1622.6840 997.0000 10.0 TRUE

			CLEAR_AREA 2143.2656 1618.1451 999.9764 10.0 TRUE
			
			LVAR_FLOAT h9_gas_Z

			LVAR_INT h9_grenade

			CREATE_OBJECT teargas 2144.3232 1622.6840 997.0000 h9_grenade

			GOSUB h9_fade_in

			GET_OBJECT_COORDINATES h9_grenade x y h9_gas_Z

			WHILE h9_gas_Z > 992.6000

				WAIT 20

				GET_OBJECT_COORDINATES h9_grenade x y h9_gas_Z

				h9_gas_Z = h9_gas_Z - 0.3

				SET_OBJECT_COORDINATES h9_grenade x y h9_gas_Z

			ENDWHILE
									   
		  	CREATE_FX_SYSTEM teargasAD 2144.3232 1622.6840 992.5681 TRUE h9_gas_fx_[0]

		  	PLAY_FX_SYSTEM h9_gas_fx_[0]

			WAIT 1500

			REPEAT 2 v

				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF

				IF NOT IS_CHAR_DEAD h9_safe_guards[v]

					IF v = 0  
																	    
						CLEAR_CHAR_TASKS_IMMEDIATELY h9_safe_guards[v]
						TASK_PLAY_ANIM h9_safe_guards[v] CAS_G2_GASKO HEIST9 1.0 FALSE FALSE FALSE TRUE -1   
						WAIT 200

					ENDIF

					IF v = 1

						CLEAR_CHAR_TASKS_IMMEDIATELY h9_safe_guards[v]
						TASK_PLAY_ANIM h9_safe_guards[v] CAS_G2_GASKO HEIST9 1.0 FALSE FALSE FALSE TRUE -1   
						WAIT 100

					ENDIF

				ENDIF

				IF NOT IS_CHAR_DEAD h9_safe_guards[v]
					
					SET_CHAR_COLLISION h9_safe_guards[v] FALSE

				ENDIF

			ENDREPEAT

			TIMERB = 0											 
			WHILE TIMERB < 1000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF
			ENDWHILE

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_FA // (Coughing/choking and thumps as they hit the floor)

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0											 
			WHILE TIMERB < 1500
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF
			ENDWHILE

			IF NOT IS_CHAR_DEAD h9_safe_guards[1]

				REPORT_MISSION_AUDIO_EVENT_AT_CHAR h9_safe_guards[1] SOUND_PED_COLLAPSE

			ENDIF

			TIMERB = 0
			WHILE TIMERB < 500
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF
			ENDWHILE

			PRINT_NOW ( HE8_FB ) 2000 1 // Won’t know if that worked until we’re down there, I guess...

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_FB // Won’t know if that worked until we’re down there, I guess...

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF
			ENDWHILE

			IF NOT IS_CHAR_DEAD scplayer

				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2143.3765 1619.6550 999.9764

				SET_CHAR_HEADING scplayer 182.7419   

			ENDIF

		    STOP_FX_SYSTEM h9_gas_fx_[0]

			KILL_FX_SYSTEM_NOW h9_gas_fx_[0]

			CLEAR_AREA 2144.3232 1622.6840 997.0000 10.0 TRUE

			CLEAR_AREA 2143.2656 1618.1451 999.9764 10.0 TRUE

			SET_FIXED_CAMERA_POSITION 2142.7495 1617.8359 1001.1431 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2142.9971 1618.8032 1001.1974 JUMP_CUT

			PRINT_NOW ( HE8_FC ) 2000 1 // Don’t worry about it.

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_FC // Don’t worry about it.

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF
			ENDWHILE

			PRINT_NOW ( HE8_FD ) 2000 1 // Right now we got a schedule to stick to!

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_FD // Right now we got a schedule to stick to!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1				 
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF
			ENDWHILE

			PRINT_NOW ( HE8_FE ) 2000 1 // Head to the security door and use Millie’s swipe card!

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_FE // Head to the security door and use Millie’s swipe card!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_the_gas_cut
				ENDIF
			ENDWHILE
						
			h9_skip_the_gas_cut:			

			DELETE_OBJECT h9_grenade

			MARK_MODEL_AS_NO_LONGER_NEEDED teargas

			IF NOT IS_CHAR_DEAD scplayer

				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2143.2205 1618.3719 999.9688  

				SET_CHAR_HEADING scplayer 175.5027  

			ENDIF

			IF NOT IS_CHAR_DEAD h9_safe_guards[0]

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_safe_guards[0]
				TASK_PLAY_ANIM_NON_INTERRUPTABLE h9_safe_guards[0] CAS_G2_GASKO HEIST9 1.0 FALSE FALSE FALSE TRUE -1
				TASK_TOGGLE_PED_THREAT_SCANNER h9_safe_guards[0] FALSE FALSE FALSE
				SET_CHAR_DECISION_MAKER h9_safe_guards[0] h9_empty_decision

			ENDIF

			IF NOT IS_CHAR_DEAD h9_safe_guards[1]

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_safe_guards[1]
				TASK_PLAY_ANIM_NON_INTERRUPTABLE h9_safe_guards[1] CAS_G2_GASKO HEIST9 1.0 FALSE FALSE FALSE TRUE -1  
				TASK_TOGGLE_PED_THREAT_SCANNER h9_safe_guards[1] FALSE FALSE FALSE
				SET_CHAR_DECISION_MAKER h9_safe_guards[1] h9_empty_decision

			ENDIF

			GOSUB h9_restore_camera

		    STOP_FX_SYSTEM h9_gas_fx_[0]

			KILL_FX_SYSTEM_NOW h9_gas_fx_[0]

			h9_txt_work:

			PRINT_NOW ( HM9_12 ) 5000 1 // ~s~Find the swipe card door and open it.

			REMOVE_BLIP h9_gas_blip

			ADD_BLIP_FOR_COORD 2168.1729 1616.9865 998.9700 h9_swipecard_blip
			SET_BLIP_ENTRY_EXIT h9_swipecard_blip 2196.6353 1676.8411 10.0

			h9_mission_status = 3

			h9_gas_txt = 1

		ENDIF

		IF h9_body_count = 4

			h9_body_count = 5

			GOTO h9_txt_work

		ENDIF

	ENDIF  

	// *************************************************************************************************
	// *																							   *
	// *					   	      PRESS TRIANGLE AT SWIPE-CARD DOOR              		       	   *
	// *                                  													   		   *
	// *************************************************************************************************
	
	// In main loop
	IF NOT IS_MESSAGE_BEING_DISPLAYED
	AND NOT h9_gas_txt = 12
	AND NOT h9_gas_txt = 0

		IF h9_playing = 2
		AND h9_gas_txt = 1

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

			ENDIF

			$h9_print = &HE8_GB	// Ok, I've hacked their emergency lighting protocols.
			h9_audio = SOUND_HE8_GB
			GOSUB h9_load_sample			
		
			h9_gas_txt = 2

		ENDIF

		IF h9_playing = 2
		AND h9_gas_txt = 2

			$h9_print = &HE8_GC	// I'm going to blow the charges you placed at the dam.
			h9_audio = SOUND_HE8_GC
			GOSUB h9_load_sample			
		
			h9_gas_txt = 3

		ENDIF

		IF h9_playing = 2
		AND h9_gas_txt = 3

			$h9_print = &HE8_GD	// Here goes nothing...
			h9_audio = SOUND_HE8_GD 
			GOSUB h9_load_sample

			h9_gas_txt = 4

		ENDIF

		IF h9_playing = 2
		AND h9_gas_txt = 4
					
			SET_DARKNESS_EFFECT TRUE 220

			CLEAR_MISSION_AUDIO 3

			LOAD_MISSION_AUDIO 3 SOUND_LIGHTS_POWER_DOWN
				
			WHILE NOT HAS_MISSION_AUDIO_LOADED 3
				WAIT 0
			ENDWHILE

			IF HAS_MISSION_AUDIO_LOADED 3	 

				PLAY_MISSION_AUDIO 3

			ENDIF

			WAIT 100

			$h9_print = &HE8_GE	// Beautiful! I didn't think that was going to work!
			h9_audio = SOUND_HE8_GE
			GOSUB h9_load_sample			

			OPEN_SEQUENCE_TASK h9_sequence_task

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2157.5972 1597.8632 998.9694 PEDMOVE_RUN -1
				
				TASK_LOOK_AT_COORD -1 2155.7815 1598.0634 998.9686 -2

				TASK_PLAY_ANIM -1 IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1

				TASK_PAUSE -1 1000
				
				SET_SEQUENCE_TO_REPEAT h9_sequence_task 1

			CLOSE_SEQUENCE_TASK h9_sequence_task			

			REPEAT 7 v
			
				IF NOT IS_CHAR_DEAD h9_workers[v]

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_workers[v]
						  
					PERFORM_SEQUENCE_TASK h9_workers[v] h9_sequence_task

				ENDIF

			ENDREPEAT

			CLEAR_SEQUENCE_TASK h9_sequence_task

			h9_gas_txt = 5

		ENDIF

		IF h9_playing = 2
		AND h9_gas_txt = 5

			IF NOT IS_NIGHT_VISION_ACTIVE

				IF NOT IS_CHAR_DEAD scplayer

					START_CHAR_FACIAL_TALK scplayer 10000

				ENDIF

				$h9_print = &HE8_GF	// Can't see jack!
				h9_audio = SOUND_HE8_GF
				GOSUB h9_load_sample			

			ENDIF

			h9_gas_txt = 9

		ENDIF

		IF h9_playing = 2
		AND h9_gas_txt = 9

			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer 

			ENDIF

			PRINT_NOW HM9_49 5000 1 

			h9_gas_txt = 10

			TIMERB = 0

		ENDIF

		IF TIMERB > 5000
		AND h9_service_txt = 0
																						    
			IF h9_mission_status = 5
			AND h9_playing = 2
			AND h9_gas_txt = 10
				h9_gas_txt = 11
			ENDIF
			IF h9_mission_status = 5
			AND h9_playing = 2
			AND h9_gas_txt = 11

				IF NOT IS_CHAR_DEAD scplayer
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
				ENDIF

				$h9_print = &HE8_HB	// You heard the man, head down to the service bay!
				h9_audio = SOUND_HE8_HB
				GOSUB h9_load_sample

				h9_gas_txt = 12

			ENDIF

		ENDIF

	ENDIF 
	
	IF h9_mission_status = 3

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2167.599 1617.412 998.9715 2.0 2.0 2.0 FALSE

		 	IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED 

		 		PRINT_HELP_FOREVER HM9_29

		 	ENDIF

			IF IS_BUTTON_PRESSED PAD1 TRIANGLE

				REPEAT 7 v
					DELETE_CHAR h9_workers[v]
				ENDREPEAT

				CLEAR_MISSION_AUDIO 3

				LOAD_MISSION_AUDIO 3 SOUND_DOOR_BUZZER

				WHILE NOT HAS_MISSION_AUDIO_LOADED 3

					WAIT 0

				ENDWHILE

				MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP
				MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN
				MARK_MODEL_AS_NO_LONGER_NEEDED VWFYWAI

			  	REMOVE_BLIP h9_swipecard_blip
					
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2167.5994 1617.2870 998.9715 

				SET_CHAR_HEADING scplayer 270.0000

				TASK_PLAY_ANIM scplayer Use_SwipeCard HEIST9 2.0 FALSE FALSE FALSE FALSE -1

				WAIT 500
					
				IF HAS_MISSION_AUDIO_LOADED 3

					PLAY_MISSION_AUDIO 3

				ENDIF

				WAIT 500

				v = 0

				WHILE NOT v = 33

					WAIT 0

					ROTATE_OBJECT h9_door_c 180.0000 3.0 FALSE
				
					v ++

				ENDWHILE

				h9_mission_status = 5

				CLEAR_HELP

				h9_start_conv = 0

				TIMERA = 0

			ENDIF

		ELSE

			CLEAR_HELP

		ENDIF

	ENDIF  

	// *************************************************************************************************
	// *																							   *
	// *						 	   ACTIVATE NIGHT-VISION GOGGLES								   *
	// *                                  															   *
	// *************************************************************************************************
			
    IF h9_mission_status = 5

	// *************************************************************************************************
	// *																							   *
	// *						 	      Create Forklift and Blips									   *
	// *                                  															   *
	// *************************************************************************************************

		IF h9_playing = 2
		AND h9_start_conv = 0

  	        REQUEST_MODEL FORKLIFT

			WHILE NOT HAS_MODEL_LOADED FORKLIFT
				WAIT 0
			ENDWHILE

			// Creates forklift
			CREATE_CAR FORKLIFT 2173.6206 1585.9639 998.9722 h9_forklift

			SET_VEHICLE_AREA_VISIBLE h9_forklift 1 

			SET_CAR_HEADING h9_forklift 270.0

			CHANGE_CAR_COLOUR h9_forklift CARCOLOUR_TAXIYELLOW CARCOLOUR_TAXIYELLOW
						
			h9_spawn_forklift = 1

			IF NOT IS_CAR_DEAD h9_forklift 

				SET_VEHICLE_AREA_VISIBLE h9_forklift 1

			ENDIF 

			ADD_BLIP_FOR_COORD 2220.5430 1572.6249 998.9531 h9_roller_door_blip
			SET_BLIP_ENTRY_EXIT h9_roller_door_blip 2196.6353 1676.8411 10.0
		
			h9_start_conv = 1  

		ENDIF

	// *************************************************************************************************
	// *																							   *
	// *						 	   Player enters the service area								   *
	// *                                  															   *
	// *************************************************************************************************
 
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2224.4524 1595.4354 998.9753 1.2 1.2 1.2 FALSE
		AND h9_service_txt = 0

			h9_service_txt = 1

		ENDIF

		IF h9_playing = 2
		AND h9_service_txt = 1

			$h9_print = &HE8_JA	// Ok, with the power down, the gate ain’t locked.
			h9_audio = SOUND_HE8_JA
			GOSUB h9_load_sample

			h9_service_txt = 2

		ENDIF
		IF h9_playing = 2
		AND h9_service_txt = 2

			$h9_print = &HE8_JB	// But you’re going to have to raise it yourself.
			h9_audio = SOUND_HE8_JB
			GOSUB h9_load_sample

			h9_service_txt = 3

		ENDIF
		IF h9_playing = 2
		AND h9_service_txt = 3

		/*	IF NOT IS_CHAR_DEAD scplayer

				START_CHAR_FACIAL_TALK scplayer 10000

			ENDIF

			$h9_print = &HE8_JC	// It’s ok, I’ve had an idea!
			h9_audio = SOUND_HE8_JC
			GOSUB h9_load_sample  */

			IF DOES_BLIP_EXIST h9_roller_door_blip

				REMOVE_BLIP h9_roller_door_blip

			ENDIF

			IF NOT IS_CAR_DEAD h9_forklift 

				ADD_BLIP_FOR_CAR h9_forklift h9_forklift_blip

				SET_BLIP_ENTRY_EXIT h9_forklift_blip 2196.6353 1676.8411 10.0

				SET_BLIP_AS_FRIENDLY h9_forklift_blip TRUE

			ENDIF

			h9_service_txt = 4

		ENDIF

		IF h9_playing = 2
		AND h9_service_txt = 4

			PRINT_NOW ( HM9_51 ) 7000 1 // ~s~You'll need something big to lift this door up.

			h9_service_txt = 5

		ENDIF

		// *************************************************************************************************
		// *																							   *
		// *						 	     Player enters the forklift 								   *
		// *                                  															   *
		// *************************************************************************************************
   
		IF h9_spawn_forklift = 1
			IF NOT IS_CAR_DEAD h9_forklift

				IF IS_CHAR_IN_CAR scplayer h9_forklift

					IF h9_forklift_message = 0
					AND IS_CHAR_IN_CAR scplayer h9_forklift

						PRINT_NOW ( HM9_5 ) 5000 1 // ~s~Use the forklift to open the roller door.

						h9_forklift_message = 1

						h9_mission_status = 6

						h9_once_only = 1

						IF DOES_BLIP_EXIST h9_roller_door_blip

							REMOVE_BLIP h9_roller_door_blip

						ENDIF

						ADD_BLIP_FOR_COORD 2220.5430 1572.6249 998.9531 h9_roller_door_blip
						SET_BLIP_ENTRY_EXIT h9_roller_door_blip 2196.6353 1676.8411 10.0

					ENDIF

				ENDIF

			ENDIF
		ENDIF

	ENDIF  

	IF IS_CAR_DEAD h9_forklift
	AND h9_mission_status < 7
	AND h9_spawn_forklift = 1	   

		GOSUB h9_fix_for_failed

		PRINT_NOW ( HM9_13 ) 4000 1 // ~r~You idiot you have destroyed the forklift, the only way to open the roller door!

		GOTO mission_heist9_failed

	ENDIF 

	// *************************************************************************************************
	// *																							   *
	// *								CHANGE BLIP DISPLAY FOR FORKLIFT							   *
	// *                                  															   *
	// *************************************************************************************************

	IF h9_mission_status = 6    

		IF NOT IS_CAR_DEAD h9_forklift

			IF IS_CHAR_IN_CAR scplayer h9_forklift
			AND h9_back_in_the_forklift = 0

				CHANGE_BLIP_DISPLAY h9_forklift_blip NEITHER
				CHANGE_BLIP_DISPLAY h9_roller_door_blip BOTH 
				h9_back_in_the_forklift = 1

			ENDIF

			IF NOT IS_CHAR_IN_CAR scplayer h9_forklift
			AND h9_back_in_the_forklift = 1

				PRINT_NOW ( HM9_23 ) 3000 1 // ~s~Get back in the forklift!

				CHANGE_BLIP_DISPLAY h9_forklift_blip BOTH
				CHANGE_BLIP_DISPLAY h9_roller_door_blip NEITHER
				h9_back_in_the_forklift = 0

			ENDIF

		ENDIF

		IF NOT IS_CAR_DEAD h9_forklift

			GET_CAR_MOVING_COMPONENT_OFFSET h9_forklift h9_offset

			IF IS_CHAR_IN_CAR scplayer h9_forklift

				IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 2220.5427 1573.1407 998.9672 6.0 6.0 6.0 FALSE
					PRINT_HELP_FOREVER ( HM9_53 ) 
				ELSE
					CLEAR_HELP
				ENDIF
				
				GET_CHAR_COORDINATES scplayer x y z

				GET_CHAR_HEADING scplayer h9_heading

			  	h9_calculus = h9_offset 

				h9_calculus = h9_calculus + 1001.6000

				h9_offset = h9_offset + z

				IF  h9_heading > 140.0
				AND h9_heading < 220.0

					IF  y > 1572.8000 
					AND y < 1573.8000

						IF IS_BUTTON_PRESSED PAD1 RIGHTSTICKY
							
							GET_OBJECT_COORDINATES h9_door_e x1 y1 h9_z1

							RStickY = 0

							GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY

							h9_z1 = h9_calculus - 0.1

							SET_OBJECT_COORDINATES h9_door_e 2220.1880 1571.7629 h9_z1

							IF h9_door_sfx = 0

						  	    REPORT_MISSION_AUDIO_EVENT_AT_OBJECT h9_door_e SOUND_SHUTTER_DOOR_SLOW_START

								h9_door_sfx = 1

							ENDIF

						ELSE

							IF h9_door_sfx = 1

						  	    REPORT_MISSION_AUDIO_EVENT_AT_OBJECT h9_door_e SOUND_SHUTTER_DOOR_SLOW_STOP

								h9_door_sfx = 0
																							    
							ENDIF

						ENDIF
							
					ENDIF
								   
				ENDIF					

			ENDIF			

		ENDIF

		// *************************************************************************************************
		// *																							   *
		// *							     FORKLIFT IS AT THE ROLLERDOOR 								   *
		// *                                  															   *
		// *************************************************************************************************

		IF h9_z1 >= 1002.8000   
		AND NOT IS_CHAR_DEAD scplayer 

			//van0
			try_here:   

			IF IS_CHAR_IN_MODEL scplayer FORKLIFT
											
				CREATE_PICKUP_WITH_AMMO satchel PICKUP_ONCE 10 2146.4871 1622.8053 993.5000 h9_satchel_bomb

			   	IF NOT IS_CHAR_DEAD h9_workers[7]
					
					DELETE_CHAR h9_workers[7]

				ENDIF

				IF NOT IS_CHAR_DEAD h9_workers[8]
					
					DELETE_CHAR h9_workers[8]

				ENDIF

				IF NOT IS_CHAR_DEAD h9_workers[9]
					
					DELETE_CHAR h9_workers[9]

				ENDIF	

				IF NOT IS_CHAR_DEAD scplayer

					SET_PLAYER_CONTROL player1 OFF

				ENDIF

				REMOVE_BLIP h9_roller_door_blip

				CLEAR_HELP

				REQUEST_CAR_RECORDING 499
				REQUEST_CAR_RECORDING 487
	
				WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 499
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 487

					WAIT 0
						
				ENDWHILE

				IF NOT IS_CAR_DEAD h9_getaway_car
				 
					SET_VEHICLE_AREA_VISIBLE h9_getaway_car 1

					SWITCH_CAR_SIREN h9_getaway_car ON

				ENDIF 
				
				IF NOT IS_CAR_DEAD h9_forklift

					SET_CAR_COORDINATES h9_forklift 2220.5427 1573.1407 998.9672 

					SET_CAR_HEADING h9_forklift 180.0000 
					
				ENDIF
 
		  		GOSUB h9_set_camera

		   	 	SET_FIXED_CAMERA_POSITION 2224.6938 1577.6321 1002.6334 0.0 0.0 0.0 // High bike
		   	 	POINT_CAMERA_AT_POINT 2224.1992 1576.8566 1002.2411 JUMP_CUT

				IF HAS_CAR_RECORDING_BEEN_LOADED 487
				AND NOT IS_CAR_DEAD h9_forklift

				  	START_PLAYBACK_RECORDED_CAR h9_forklift 487

				ENDIF

				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT h9_door_e SOUND_SHUTTER_DOOR_START

				IF DOES_OBJECT_EXIST h9_door_e

					GET_OBJECT_COORDINATES h9_door_e x1 y1 h9_z1

				ENDIF

				WHILE NOT h9_z1 > 1005.4000

					WAIT 50

					h9_z1 = h9_z1 + 0.1

					SET_OBJECT_COORDINATES h9_door_e 2220.1880 1571.7629 h9_z1

				ENDWHILE

				REPORT_MISSION_AUDIO_EVENT_AT_OBJECT h9_door_e SOUND_SHUTTER_DOOR_STOP

				IF HAS_CAR_RECORDING_BEEN_LOADED 499
				AND NOT IS_CAR_DEAD h9_getaway_car

				  	START_PLAYBACK_RECORDED_CAR h9_getaway_car 499

				ENDIF

				IF NOT IS_CAR_DEAD h9_forklift

					IF IS_PLAYBACK_GOING_ON_FOR_CAR h9_forklift

						STOP_PLAYBACK_RECORDED_CAR h9_forklift 

					ENDIF

					SET_CAR_COORDINATES h9_forklift 2218.0386 1592.2324 998.9783  

					SET_CAR_HEADING h9_forklift 26.1233  
					
				ENDIF

				IF NOT IS_CHAR_DEAD scplayer
					
					IF IS_CHAR_IN_ANY_CAR scplayer

						WARP_CHAR_FROM_CAR_TO_COORD scplayer 2217.1147 1589.6826 998.9709  
					
					ELSE
					
						SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2217.1147 1589.6826 998.9709 

					ENDIF
					
					SET_CHAR_HEADING scplayer 209.5064  

				ENDIF
 								
				REPEAT 7 v

					IF NOT IS_CHAR_DEAD h9_workers[v]

						DELETE_CHAR h9_workers[v]
						
					ENDIF
					
				ENDREPEAT
					
				REQUEST_MODEL satchel
				REQUEST_MODEL bomb
				REQUEST_MODEL mp5lng
				REQUEST_MODEL WMYBOUN
				
				WHILE NOT HAS_MODEL_LOADED satchel
				OR NOT HAS_MODEL_LOADED bomb
				OR NOT HAS_MODEL_LOADED mp5lng
				OR NOT HAS_MODEL_LOADED WMYBOUN
					WAIT 0
				ENDWHILE
 
			 	SET_FIXED_CAMERA_POSITION 2217.3696 1580.5952 1000.5817 0.0 0.0 0.0 // High bike
			 	POINT_CAMERA_AT_POINT 2217.6626 1579.6394 1000.5587 JUMP_CUT

				TIMERB = 0
				WHILE TIMERB < 3000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				SET_FIXED_CAMERA_POSITION 2215.1323 1594.2478 1002.7781 0.0 0.0 0.0 // High bike
			 	POINT_CAMERA_AT_POINT 2215.4087 1593.3190 1002.5314 JUMP_CUT

				IF NOT IS_CAR_DEAD h9_getaway_car

				   	FREEZE_CAR_POSITION h9_getaway_car TRUE

				ENDIF
				 
				PRINT_NOW ( HE8_KA ) 4000 1 // Well done, Carl.
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KA // Well done, Carl.

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1  
												 
				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping			  
					ENDIF
				ENDWHILE

				IF NOT IS_CAR_DEAD h9_getaway_car

					OPEN_SEQUENCE_TASK h9_sequence_task  

						TASK_PAUSE -1 500

						TASK_LEAVE_CAR -1 h9_getaway_car

						FLUSH_ROUTE

						EXTEND_ROUTE 2224.1553 1579.6910 998.9739  
						EXTEND_ROUTE 2223.6360 1585.5488 998.9675  
						
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_PLAY_ANIM -1 IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1			

					CLOSE_SEQUENCE_TASK h9_sequence_task
					IF NOT IS_CHAR_DEAD h9_gang[0]
						PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task
					ENDIF										
					CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task

						TASK_LEAVE_CAR -1 h9_getaway_car
						
						FLUSH_ROUTE

						EXTEND_ROUTE 2220.0332 1584.6139 998.9719  
						
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_TURN_CHAR_TO_FACE_COORD -1 2216.4399 1589.1302 998.9790

					CLOSE_SEQUENCE_TASK h9_sequence_task 
					IF NOT IS_CHAR_DEAD h9_gang[1]
						PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task
					ENDIF	
					CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task

						TASK_PAUSE -1 500

						TASK_LEAVE_CAR -1 h9_getaway_car

						FLUSH_ROUTE

						EXTEND_ROUTE 2221.4946 1584.9478 998.9701  

						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_TURN_CHAR_TO_FACE_COORD -1 2216.4399 1589.1302 998.9790

					CLOSE_SEQUENCE_TASK h9_sequence_task
					IF NOT IS_CHAR_DEAD h9_gang[2]
						PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task
					ENDIF	
					CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task

						TASK_PAUSE -1 1000

						TASK_LEAVE_CAR -1 h9_getaway_car

						FLUSH_ROUTE

						EXTEND_ROUTE 2216.6995 1578.3629 998.9761  
						EXTEND_ROUTE 2217.5381 1583.6400 998.9749  
						
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_TURN_CHAR_TO_FACE_COORD -1 2216.4399 1589.1302 998.9790

					CLOSE_SEQUENCE_TASK h9_sequence_task
					IF NOT IS_CHAR_DEAD h9_gang[3]
						PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task
					ENDIF
					CLEAR_SEQUENCE_TASK h9_sequence_task

				ENDIF

				// ********************************************************************************************
				// *																						  *
				// *							 	     Team assembles 									  *
				// *																						  *
				// ********************************************************************************************

				PRINT_NOW ( HE8_KB ) 4000 1 // Now it’s time for us to do our part!
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KB // Now it’s time for us to do our part!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				PRINT_NOW ( HE8_KC ) 4000 1 // Try to stay close!
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KC // Try to stay close!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				PRINT_NOW ( HE8_KD ) 4000 1 // Ok team, I’ve gone over the layout to this place so I know it back to front.

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KD // Ok team, I’ve gone over the layout to this place so I know it back to front.

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE
										    
				SET_FIXED_CAMERA_POSITION 2219.6533 1584.9146 1001.2105 0.0 0.0 0.0 // High bike
			 	POINT_CAMERA_AT_POINT 2220.1802 1585.7460 1001.0345 JUMP_CUT

				PRINT_NOW ( HE8_KE ) 4000 1 // Everybody follow me!

				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KE // Everybody follow me!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				IF NOT IS_CHAR_DEAD h9_gang[0]

					OPEN_SEQUENCE_TASK h9_sequence_task

						FLUSH_ROUTE
					 	
						EXTEND_ROUTE 2224.1919 1589.1930 998.9668  
						EXTEND_ROUTE 2225.4651 1592.9969 998.9677  
						EXTEND_ROUTE 2226.7390 1597.7960 998.9755  
						
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_PLAY_ANIM -1 getup_front PED 1.0 FALSE FALSE FALSE FALSE -1
						 
					CLOSE_SEQUENCE_TASK h9_sequence_task

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[0]

					PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task

					CLEAR_SEQUENCE_TASK h9_sequence_task

				ENDIF

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				TIMERB = 0
				WHILE TIMERB < 2500
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				SHAKE_CAM 30

				TIMERB = 0
				WHILE TIMERB < 1500
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				IF NOT IS_CHAR_DEAD h9_gang[0]

					IF NOT IS_CHAR_DEAD h9_gang[1]
												  
						CLEAR_CHAR_TASKS h9_gang[1]

						TASK_TURN_CHAR_TO_FACE_CHAR h9_gang[1] h9_gang[0]

					ENDIF

					IF NOT IS_CHAR_DEAD h9_gang[2]

						CLEAR_CHAR_TASKS h9_gang[2]

						TASK_TURN_CHAR_TO_FACE_CHAR h9_gang[2] h9_gang[0]

					ENDIF

					WAIT 100

					IF NOT IS_CHAR_DEAD h9_gang[3]
					AND NOT IS_CHAR_DEAD h9_gang[0]

						CLEAR_CHAR_TASKS h9_gang[3]

						TASK_TURN_CHAR_TO_FACE_CHAR h9_gang[3] h9_gang[0]

					ENDIF

					WAIT 100

					IF NOT IS_CHAR_DEAD scplayer
					AND NOT IS_CHAR_DEAD h9_gang[0]

						CLEAR_CHAR_TASKS scplayer

						TASK_TURN_CHAR_TO_FACE_CHAR scplayer h9_gang[0]

					ENDIF
				
				ENDIF

				PRINT_NOW ( HE8_KF ) 4000 1 // Damn! The devious bastards have changed the layout!
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KF // Damn! The devious bastards have changed the layout!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				IF NOT IS_CHAR_DEAD scplayer
				AND NOT IS_CHAR_DEAD h9_gang[0]

					CLEAR_CHAR_TASKS h9_gang[0]

					TASK_TURN_CHAR_TO_FACE_CHAR h9_gang[0] scplayer

				ENDIF
										  
				SET_FIXED_CAMERA_POSITION 2216.9634 1581.5256 1000.5791 0.0 0.0 0.0 // High bike
			 	POINT_CAMERA_AT_POINT 2217.4033 1582.4163 1000.4652 JUMP_CUT

				PRINT_NOW ( HE8_KG ) 4000 1 // I’ll take the lead, boss.
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KG // I’ll take the lead, boss.

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				PRINT_NOW ( HE8_KH ) 4000 1 //  Good idea, everybody follow him!
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_KH //  Good idea, everybody follow him!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
						GOTO h9_skipping
					ENDIF
				ENDWHILE

				h9_skipping:

				// First Section
				//--------------------------------------------------------------------------------------

				//second0
								
				LVAR_INT h9_first_room[5] h9_first_inv[15]

				//First guy at snack machine - Crouch shoot must be killed - 200 hp - 50 acc
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2201.1262 1617.8768 998.9827 h9_first_room[0]

				SET_CHAR_AREA_VISIBLE h9_first_room[0] 1

				SET_CHAR_HEADING h9_first_room[0] 267.4834 

				TASK_TOGGLE_DUCK h9_first_room[0] TRUE

				//Second guy standing in doorway - dual pistols no duck 
				CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2196.0835 1618.6842 998.9766 h9_first_room[1]

				SET_CHAR_AREA_VISIBLE h9_first_room[1] 1

				SET_CHAR_HEADING h9_first_room[1] 263.4616 

				//thrid guy behind snack machine - Crouch Shoot must be killed			
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2207.9170 1620.3722 998.9827 h9_first_room[2]
															   
				SET_CHAR_AREA_VISIBLE h9_first_room[2] 1

				SET_CHAR_HEADING h9_first_room[2] 267.4754

				TASK_TOGGLE_DUCK h9_first_room[2] TRUE

				SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_first_room[2] TRUE

				REPEAT 3 v
									
					SET_CHAR_MONEY h9_first_room[v] 0

					IF NOT v = 2							

						SET_CHAR_HEALTH h9_first_room[v] 200
							
						SET_CHAR_MAX_HEALTH h9_first_room[v] 200

					ENDIF
		
					ADD_BLIP_FOR_CHAR h9_first_room[v] h9_first_inv[v]

					SET_BLIP_ENTRY_EXIT h9_first_inv[v] 2196.6353 1676.8411 10.0
						
					CHANGE_BLIP_DISPLAY h9_first_inv[v] BLIP_ONLY

				   	GIVE_WEAPON_TO_CHAR h9_first_room[v] WEAPONTYPE_MP5 30000

				  	SET_CURRENT_CHAR_WEAPON h9_first_room[v] WEAPONTYPE_MP5

					SET_CHAR_ACCURACY h9_first_room[v] 20

					SET_CHAR_SHOOT_RATE h9_first_room[v] 20

					SET_CHAR_DECISION_MAKER h9_first_room[v] h9_decision_tough

				 	TASK_STAY_IN_SAME_PLACE h9_first_room[v] TRUE

				ENDREPEAT

				// Second Section
				//--------------------------------------------------------------------------------------
			
				LVAR_INT h9_second_room[15]	h9_seconds_inv[15]

				//Enemy stand and shoot behind red box 
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2180.0754 1611.5725 998.9766 h9_second_room[0]

				SET_CHAR_AREA_VISIBLE h9_second_room[0] 1

				SET_CHAR_HEADING h9_second_room[0] 273.7555 

				SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_second_room[0] TRUE

				//Enemy crouching left of shelf
				CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2186.1421 1612.3982 998.9777 h9_second_room[1]

				SET_CHAR_AREA_VISIBLE h9_second_room[1] 1

				SET_CHAR_HEADING h9_second_room[1] 306.4445 

				TASK_TOGGLE_DUCK h9_second_room[1] TRUE

				//Enemy at front near washing machine crouching			
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2183.5205 1615.2456 998.9766 h9_second_room[2]
															   
				SET_CHAR_AREA_VISIBLE h9_second_room[2] 1

				SET_CHAR_HEADING h9_second_room[2] 294.2185
				 
				// Enemies in Second room  

				//Enemy near red box crouching 
				CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2182.6118 1623.6973 998.9742 h9_second_room[3]

				SET_CHAR_AREA_VISIBLE h9_second_room[3] 1

				SET_CHAR_HEADING h9_second_room[3] 248.6147 

				TASK_TOGGLE_DUCK h9_second_room[3] TRUE

				//Enemy behind washing machine standing
				CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2179.6814 1622.4037 998.9764 h9_second_room[4]

				SET_CHAR_AREA_VISIBLE h9_second_room[4] 1

				SET_CHAR_HEADING h9_second_room[4] 268.7008 

				SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_second_room[4] TRUE

				//Enemy standing behind red box			
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2178.8525 1625.6030 998.9709 h9_second_room[5]
															   
				SET_CHAR_AREA_VISIBLE h9_second_room[5] 1

				SET_CHAR_HEADING h9_second_room[5] 250.7106

				// Thrid Section
				//--------------------------------------------------------------------------------------

				//Enemy near red box crouching 
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2176.3633 1606.5989 998.9827 h9_second_room[6]

				SET_CHAR_AREA_VISIBLE h9_second_room[6] 1

				SET_CHAR_HEADING h9_second_room[6] 0.8709

				SET_SENSE_RANGE h9_second_room[6] 5.0

				TASK_TOGGLE_DUCK h9_second_room[6] TRUE

				//Enemy behind washing machine standing
				CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2176.4915 1600.8971 998.9827 h9_second_room[7]

				SET_CHAR_AREA_VISIBLE h9_second_room[7] 1

				SET_SENSE_RANGE h9_second_room[7] 5.0

				SET_CHAR_HEADING h9_second_room[7] 352.9370 

				//Enemy standing behind red box			
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2169.1174 1618.6256 998.9766 h9_second_room[8]
															   
				SET_CHAR_AREA_VISIBLE h9_second_room[8] 1

				SET_CHAR_HEADING h9_second_room[8] 272.9878

				//Enemy peeking around doorway			
				CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2161.2080 1602.7756 998.9812 h9_second_room[9]
															   
				SET_CHAR_AREA_VISIBLE h9_second_room[9] 1

				SET_CHAR_HEADING h9_second_room[9] 4.2093

				//Enemy behind snack machine		
				CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2156.5396 1605.2523 998.9725 h9_second_room[10]
															   
				SET_CHAR_AREA_VISIBLE h9_second_room[10] 1

				SET_CHAR_HEADING h9_second_room[10] 353.7050

				REPEAT 11 v
														
					SET_CHAR_MONEY h9_second_room[v] 0

					IF NOT v = 0
					AND NOT v = 4
							
						SET_CHAR_HEALTH h9_second_room[v] 200
							
						SET_CHAR_MAX_HEALTH h9_second_room[v] 200

					ENDIF
							
					ADD_BLIP_FOR_CHAR h9_second_room[v] h9_seconds_inv[v]
					
					SET_BLIP_ENTRY_EXIT h9_seconds_inv[v] 2196.6353 1676.8411 10.0
						
					CHANGE_BLIP_DISPLAY h9_seconds_inv[v] BLIP_ONLY

				   	GIVE_WEAPON_TO_CHAR h9_second_room[v] WEAPONTYPE_MP5 30000

				  	SET_CURRENT_CHAR_WEAPON h9_second_room[v] WEAPONTYPE_MP5

					SET_CHAR_SHOOT_RATE h9_second_room[v] 20

					SET_CHAR_ACCURACY h9_second_room[v] 20

					SET_CHAR_DECISION_MAKER h9_second_room[v] h9_decision_tough

				 	TASK_STAY_IN_SAME_PLACE h9_second_room[v] TRUE

				ENDREPEAT

				h9_safe_timer = 420000

				CLEAR_ONSCREEN_TIMER h9_safe_timer

				DISPLAY_ONSCREEN_TIMER_WITH_STRING h9_safe_timer TIMER_DOWN HM9_1A // TIME

				GOSUB h9_restore_camera

				DELETE_OBJECT h9_door_e

				IF NOT IS_CAR_DEAD h9_getaway_car

					IF IS_PLAYBACK_GOING_ON_FOR_CAR h9_getaway_car

						STOP_PLAYBACK_RECORDED_CAR h9_getaway_car 

					ENDIF

					SET_CAR_COORDINATES h9_getaway_car 2220.3899 1577.6234 998.9670   
					
					SET_CAR_HEADING h9_getaway_car 179.9804

					SKIP_TO_END_AND_STOP_PLAYBACK_RECORDED_CAR h9_getaway_car

				ENDIF	
				// tag0
				IF NOT IS_CAR_DEAD h9_getaway_car
					IF NOT IS_CHAR_DEAD h9_gang[0]
					AND NOT IS_CHAR_DEAD h9_gang[1]
					AND NOT IS_CHAR_DEAD h9_gang[2]
					AND NOT IS_CHAR_DEAD h9_gang[3]

						LVAR_INT h9_goto_first_room	h9_enter_crouch

						// --------------------------------------------------------------------------

						OPEN_SEQUENCE_TASK h9_kill_seconds

							TASK_PAUSE -1 10

							IF NOT IS_CHAR_DEAD h9_first_room[0]
								TASK_KILL_CHAR_ON_FOOT -1 h9_first_room[0]
							ENDIF
							IF NOT IS_CHAR_DEAD h9_first_room[1]
								TASK_KILL_CHAR_ON_FOOT -1 h9_first_room[1]
							ENDIF
							IF NOT IS_CHAR_DEAD h9_first_room[2]
								TASK_KILL_CHAR_ON_FOOT -1 h9_first_room[2]
							ENDIF

						CLOSE_SEQUENCE_TASK h9_kill_seconds

						// --------------------------------------------------------------------------

						OPEN_SEQUENCE_TASK h9_goto_first_room

							FLUSH_ROUTE
				 	
							EXTEND_ROUTE 2221.9524 1586.5632 998.9695  
							EXTEND_ROUTE 2223.3901 1591.6100 998.9678 
							EXTEND_ROUTE 2224.2534 1597.9062 998.9766
							EXTEND_ROUTE 2224.3589 1599.6816 998.9830
							 
							TASK_TOGGLE_DUCK -1 FALSE

							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
							
							FLUSH_ROUTE

						CLOSE_SEQUENCE_TASK h9_goto_first_room

						// --------------------------------------------------------------------------

						OPEN_SEQUENCE_TASK h9_enter_crouch
							
							FLUSH_ROUTE
							 
							TASK_TOGGLE_DUCK -1 TRUE
							  
							EXTEND_ROUTE 2224.5933 1602.6691 998.9816  
							EXTEND_ROUTE 2224.7900 1609.5209 998.9750  
							
							TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

							FLUSH_ROUTE

						CLOSE_SEQUENCE_TASK h9_enter_crouch

						// --------------------------------------------------------------------------

					//First guy around corner

						OPEN_SEQUENCE_TASK h9_sequence_task

							IF IS_CHAR_IN_CAR h9_gang[1] h9_getaway_car 
								TASK_LEAVE_CAR -1 h9_getaway_car
							ENDIF

						    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room

							PERFORM_SEQUENCE_TASK -1 h9_enter_crouch

							TASK_FOLLOW_PATH_NODES_TO_COORD -1 2222.4519 1617.0216 998.9678 PEDMOVE_RUN -1

							TASK_STAY_IN_SAME_PLACE -1 TRUE

							PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

						CLOSE_SEQUENCE_TASK h9_sequence_task

						CLEAR_CHAR_TASKS h9_gang[1] 

						PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task

						// --------------------------------------------------------------------------

					//second guy at back wall
						CLEAR_SEQUENCE_TASK h9_sequence_task

						OPEN_SEQUENCE_TASK h9_sequence_task

							TASK_PAUSE -1 500

							IF IS_CHAR_IN_CAR h9_gang[2] h9_getaway_car 
								TASK_LEAVE_CAR -1 h9_getaway_car
							ENDIF

						    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room
																						
							TASK_PAUSE -1 1000

							PERFORM_SEQUENCE_TASK -1 h9_enter_crouch

							TASK_FOLLOW_PATH_NODES_TO_COORD -1 2222.9434 1620.6359 998.9709 PEDMOVE_RUN -1

							TASK_STAY_IN_SAME_PLACE -1 TRUE

							PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

						CLOSE_SEQUENCE_TASK h9_sequence_task

						CLEAR_CHAR_TASKS h9_gang[2] 

						PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task

						// --------------------------------------------------------------------------

					//two guys sit down around corner 
						CLEAR_SEQUENCE_TASK h9_sequence_task

						OPEN_SEQUENCE_TASK h9_sequence_task

							TASK_PAUSE -1 1500

							IF IS_CHAR_IN_CAR h9_gang[3] h9_getaway_car 
								TASK_LEAVE_CAR -1 h9_getaway_car
							ENDIF

						    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room
																													
							TASK_PAUSE -1 3000

							PERFORM_SEQUENCE_TASK -1 h9_enter_crouch

							TASK_FOLLOW_PATH_NODES_TO_COORD -1 2223.1331 1610.9663 998.9728 PEDMOVE_WALK -1

							TASK_STAY_IN_SAME_PLACE -1 TRUE

						CLOSE_SEQUENCE_TASK h9_sequence_task

						CLEAR_CHAR_TASKS h9_gang[3] 

						PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task

						// --------------------------------------------------------------------------

					//two guys sit down around corner
						CLEAR_SEQUENCE_TASK h9_sequence_task

						OPEN_SEQUENCE_TASK h9_sequence_task

							TASK_PAUSE -1 2000 

							IF IS_CHAR_IN_CAR h9_gang[0] h9_getaway_car 
								TASK_LEAVE_CAR -1 h9_getaway_car
							ENDIF

						    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room

							TASK_PAUSE -1 2500
							
							PERFORM_SEQUENCE_TASK -1 h9_enter_crouch

							TASK_FOLLOW_PATH_NODES_TO_COORD -1 2223.1331 1610.9663 998.9728 PEDMOVE_WALK -1

							TASK_STAY_IN_SAME_PLACE -1 TRUE

						CLOSE_SEQUENCE_TASK h9_sequence_task

						CLEAR_CHAR_TASKS h9_gang[0] 

						PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task

						CLEAR_SEQUENCE_TASK h9_kill_seconds
						CLEAR_SEQUENCE_TASK h9_sequence_task
						CLEAR_SEQUENCE_TASK h9_goto_first_room
						CLEAR_SEQUENCE_TASK h9_enter_crouch

						// --------------------------------------------------------------------------

					ENDIF
				ENDIF

				REPEAT 4 v

					IF NOT IS_CHAR_DEAD h9_gang[v] 
					AND NOT IS_CAR_DEAD h9_getaway_car

						ADD_BLIP_FOR_CHAR h9_gang[v] h9_gang_blip[v]

						SET_BLIP_ENTRY_EXIT h9_gang_blip[v] 2196.6353 1676.8411 10.0
					
						SET_BLIP_AS_FRIENDLY h9_gang_blip[v] TRUE

					ENDIF

				ENDREPEAT 

				h9_mission_status = 7

			ELSE
				
				PRINT_NOW ( HM9_14 ) 5000 1 // ~s~Get into the forklift.

			ENDIF

		ENDIF

	ENDIF

	// *************************************************************************************************
	// *																							   *
	// *								  TELL TEAM TO GO TO SAFE      								   *
	// *                                  															   *
	// *************************************************************************************************

	IF h9_mission_status = 7

		REQUEST_ANIMATION BOMBER

		WHILE NOT HAS_ANIMATION_LOADED BOMBER 
			WAIT 0
		ENDWHILE

		h9_mission_status = 8

		TIMERA = 0 

	ENDIF

	// *************************************************************************************************
	// *																							   *
	// *								  GUYS TALKING ON WAY TO SAFE  								   *
	// *                                  															   *
	// *************************************************************************************************
	// tag1
	IF h9_mission_status = 8

		REPEAT 3 v

			IF IS_CHAR_DEAD h9_first_room[v]
			AND NOT h9_first_inv[v] = 0

				IF DOES_BLIP_EXIST h9_first_inv[v]

					REMOVE_BLIP h9_first_inv[v]

					h9_first_inv[v] = 0

					MARK_CHAR_AS_NO_LONGER_NEEDED h9_first_room[v]

				ENDIF

			ENDIF

		ENDREPEAT 

		REPEAT 11 v

			IF IS_CHAR_DEAD h9_second_room[v]
			AND NOT h9_seconds_inv[v] = 0

				IF DOES_BLIP_EXIST h9_seconds_inv[v]

					REMOVE_BLIP h9_seconds_inv[v]

					h9_seconds_inv[v] = 0

					MARK_CHAR_AS_NO_LONGER_NEEDED h9_second_room[v]

				ENDIF

			ENDIF

		ENDREPEAT 

		IF IS_CHAR_DEAD h9_first_room[0]
		AND IS_CHAR_DEAD h9_first_room[1]
		AND IS_CHAR_DEAD h9_first_room[2]
		AND h9_first_node = 0

			IF NOT IS_CHAR_DEAD h9_gang[0]
			AND NOT IS_CHAR_DEAD h9_gang[1]
			AND NOT IS_CHAR_DEAD h9_gang[2]
			AND NOT IS_CHAR_DEAD h9_gang[3]

				// ---------------------------------------------------------------------------------------------------

				OPEN_SEQUENCE_TASK h9_kill_seconds

					TASK_PAUSE -1 1

					IF NOT IS_CHAR_DEAD h9_second_room[0]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[0]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[2]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[2]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[1]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[1]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[6]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[6]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[7]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[7]
					ENDIF

				CLOSE_SEQUENCE_TASK h9_kill_seconds

				// ---------------------------------------------------------------------------------------------------

				OPEN_SEQUENCE_TASK h9_goto_first_room

					FLUSH_ROUTE

		 			TASK_TOGGLE_DUCK -1 TRUE

					EXTEND_ROUTE 2217.4683 1619.0314 998.9766  
					EXTEND_ROUTE 2209.8867 1618.9891 998.9766  

					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					
					FLUSH_ROUTE

		 			TASK_TOGGLE_DUCK -1 TRUE

					EXTEND_ROUTE 2203.7397 1619.1674 998.9827  
					EXTEND_ROUTE 2199.1189 1618.8575 998.9827   
					
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE

				CLOSE_SEQUENCE_TASK h9_goto_first_room

				// ---------------------------------------------------------------------------------------------------

			//Buddy crouched at the boxes above

				OPEN_SEQUENCE_TASK h9_sequence_task

				    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2196.1443 1618.7194 998.9766 PEDMOVE_RUN -1
					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2192.7258 1618.6276 998.9766 PEDMOVE_RUN -1

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2190.5312 1617.3726 998.9766 PEDMOVE_RUN -1

					TASK_STAY_IN_SAME_PLACE -1 TRUE

					PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

				CLOSE_SEQUENCE_TASK h9_sequence_task

				CLEAR_CHAR_TASKS h9_gang[1]

				PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task

				// ---------------------------------------------------------------------------------------------------

				CLEAR_SEQUENCE_TASK h9_kill_seconds

				OPEN_SEQUENCE_TASK h9_kill_seconds

					TASK_PAUSE -1 1

					IF NOT IS_CHAR_DEAD h9_second_room[3]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[3]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[4]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[4]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[5]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[5]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[6]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[6]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[7]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[7]
					ENDIF

				CLOSE_SEQUENCE_TASK h9_kill_seconds

			//Buddy standing  has dual pistols
				CLEAR_SEQUENCE_TASK h9_sequence_task

				OPEN_SEQUENCE_TASK h9_sequence_task

				    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2196.1443 1618.7194 998.9766 PEDMOVE_RUN -1

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2191.2144 1620.2288 998.9802 PEDMOVE_RUN -1

					TASK_TOGGLE_DUCK -1 FALSE

					TASK_STAY_IN_SAME_PLACE -1 TRUE

					PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

				CLOSE_SEQUENCE_TASK h9_sequence_task

				CLEAR_CHAR_TASKS h9_gang[2]

				PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task

			//two guys sit down around corner 
				CLEAR_SEQUENCE_TASK h9_sequence_task

				OPEN_SEQUENCE_TASK h9_sequence_task

					TASK_PAUSE -1 500

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2223.3911 1619.1503 998.9672 PEDMOVE_RUN -1

				    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2198.9421 1617.9740 998.9766 PEDMOVE_RUN -1
					  
					TASK_STAY_IN_SAME_PLACE -1 TRUE

				CLOSE_SEQUENCE_TASK h9_sequence_task

				CLEAR_CHAR_TASKS h9_gang[3]

				PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task

			//two guys sit down around corner
				CLEAR_SEQUENCE_TASK h9_sequence_task

				OPEN_SEQUENCE_TASK h9_sequence_task

					TASK_PAUSE -1 500

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2223.3911 1619.1503 998.9672 PEDMOVE_RUN -1

				    PERFORM_SEQUENCE_TASK -1 h9_goto_first_room

					TASK_FOLLOW_PATH_NODES_TO_COORD -1 2197.8162 1617.8267 998.9766 PEDMOVE_RUN -1

					TASK_STAY_IN_SAME_PLACE -1 TRUE

				CLOSE_SEQUENCE_TASK h9_sequence_task
			
				CLEAR_CHAR_TASKS h9_gang[0]

				PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task

				CLEAR_SEQUENCE_TASK h9_kill_seconds
				CLEAR_SEQUENCE_TASK h9_sequence_task
				CLEAR_SEQUENCE_TASK h9_goto_first_room

			ENDIF

			h9_first_node = 1

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2158.4385 1618.7395 998.9650 4.0 4.0 4.0 FALSE
		AND h9_guy_is_peeking = 0
	
			IF NOT IS_CHAR_DEAD h9_second_room[9] 

				CLEAR_CHAR_TASKS h9_second_room[9]

				PERFORM_SEQUENCE_TASK h9_second_room[9] h9_peekright

			ENDIF

			h9_guy_is_peeking = 1

		ENDIF

		//tag2
		IF IS_CHAR_DEAD h9_second_room[0]
		AND IS_CHAR_DEAD h9_second_room[1]
		AND IS_CHAR_DEAD h9_second_room[2]
		AND IS_CHAR_DEAD h9_second_room[3]
		AND h9_second_node = 0

			IF IS_CHAR_DEAD h9_second_room[4]
			AND IS_CHAR_DEAD h9_second_room[5]
			AND h9_first_node = 1

				IF NOT IS_CHAR_DEAD h9_gang[0]
				AND NOT IS_CHAR_DEAD h9_gang[1]
				AND NOT IS_CHAR_DEAD h9_gang[2]
				AND NOT IS_CHAR_DEAD h9_gang[3]

					OPEN_SEQUENCE_TASK h9_kill_seconds

						TASK_PAUSE -1 1

						IF NOT IS_CHAR_DEAD h9_second_room[8]
							TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[8]
						ENDIF

						IF NOT IS_CHAR_DEAD h9_second_room[6]
							TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[6]
						ENDIF

						IF NOT IS_CHAR_DEAD h9_second_room[7]
							TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[7]
						ENDIF

					CLOSE_SEQUENCE_TASK h9_kill_seconds

				//Buddy crouched at the boxes above

					OPEN_SEQUENCE_TASK h9_sequence_task

						FLUSH_ROUTE

			 			TASK_TOGGLE_DUCK -1 TRUE

						EXTEND_ROUTE 2188.9575 1615.0433 998.9766   
						EXTEND_ROUTE 2184.9683 1614.8361 998.9766    
						EXTEND_ROUTE 2181.0605 1612.7831 998.9766  
 						   
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_STAY_IN_SAME_PLACE -1 TRUE

						PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

					CLOSE_SEQUENCE_TASK h9_sequence_task

					CLEAR_CHAR_TASKS h9_gang[1]

					PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task

				//Buddy standing  has dual pistols
					CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task

						FLUSH_ROUTE

			 			TASK_TOGGLE_DUCK -1 TRUE

						EXTEND_ROUTE 2187.5317 1623.4857 998.9746  
						EXTEND_ROUTE 2182.6426 1624.1395 998.9734    
						EXTEND_ROUTE 2180.7778 1624.2372 998.9733  
						EXTEND_ROUTE 2178.4937 1623.0142 998.9754 
												
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_STAY_IN_SAME_PLACE -1 TRUE

						PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

					CLOSE_SEQUENCE_TASK h9_sequence_task

					CLEAR_CHAR_TASKS h9_gang[2]

					PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task

				//two guys sit down around corner 
					CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task

						FLUSH_ROUTE

			 			TASK_TOGGLE_DUCK -1 TRUE

						EXTEND_ROUTE 2195.9978 1618.9650 998.9766  
						EXTEND_ROUTE 2190.1567 1620.4567 998.9797    
						
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

					CLOSE_SEQUENCE_TASK h9_sequence_task

					CLEAR_CHAR_TASKS h9_gang[3]

					PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task

				//two guys sit down around corner
					CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task

						FLUSH_ROUTE

			 			TASK_TOGGLE_DUCK -1 TRUE

						EXTEND_ROUTE 2195.9978 1618.9650 998.9766  
						EXTEND_ROUTE 2190.4348 1616.8110 998.9766    
						
						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

					CLOSE_SEQUENCE_TASK h9_sequence_task
				
					CLEAR_CHAR_TASKS h9_gang[0]

					PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task

					CLEAR_SEQUENCE_TASK h9_sequence_task
					CLEAR_SEQUENCE_TASK h9_kill_seconds

					h9_second_node = 1

				ENDIF

			ENDIF

		ENDIF

		//tag3
		IF IS_CHAR_DEAD h9_second_room[6]
		AND IS_CHAR_DEAD h9_second_room[7]
		AND IS_CHAR_DEAD h9_second_room[8]
		AND h9_second_node = 1

			IF NOT IS_CHAR_DEAD h9_gang[0]
			AND NOT IS_CHAR_DEAD h9_gang[1]
			AND NOT IS_CHAR_DEAD h9_gang[2]
			AND NOT IS_CHAR_DEAD h9_gang[3]

				OPEN_SEQUENCE_TASK h9_kill_seconds

					TASK_PAUSE -1 1

					IF NOT IS_CHAR_DEAD h9_second_room[10]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[10]
					ENDIF

					IF NOT IS_CHAR_DEAD h9_second_room[9]
						TASK_KILL_CHAR_ON_FOOT -1 h9_second_room[9]
					ENDIF

				CLOSE_SEQUENCE_TASK h9_kill_seconds

			//Buddy crouched at the boxes above

				OPEN_SEQUENCE_TASK h9_sequence_task

					FLUSH_ROUTE

		 			TASK_TOGGLE_DUCK -1 TRUE

					EXTEND_ROUTE 2177.6843 1614.6310 998.9766  
					EXTEND_ROUTE 2173.2959 1618.3744 998.9766 
					EXTEND_ROUTE 2169.0750 1618.5891 998.9766 
					EXTEND_ROUTE 2165.5625 1618.5685 998.9752 
					EXTEND_ROUTE 2159.5325 1617.0564 998.9662  
						   
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE

					TASK_STAY_IN_SAME_PLACE -1 TRUE

					PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

				CLOSE_SEQUENCE_TASK h9_sequence_task

				CLEAR_CHAR_TASKS h9_gang[1]

				PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task

			//Buddy standing  has dual pistols
				CLEAR_SEQUENCE_TASK h9_sequence_task

				OPEN_SEQUENCE_TASK h9_sequence_task

					FLUSH_ROUTE

		 			TASK_TOGGLE_DUCK -1 TRUE

					EXTEND_ROUTE 2174.7092 1621.0681 998.978
					EXTEND_ROUTE 2169.1543 1618.6456 998.9766 
					EXTEND_ROUTE 2161.4009 1619.6282 998.9706  
					EXTEND_ROUTE 2155.9819 1619.0601 998.9666  
											
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE

					TASK_ACHIEVE_HEADING -1 180.0000  

					TASK_STAY_IN_SAME_PLACE -1 TRUE

					PERFORM_SEQUENCE_TASK -1 h9_kill_seconds

				CLOSE_SEQUENCE_TASK h9_sequence_task

				CLEAR_CHAR_TASKS h9_gang[2]

				PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task

			//two guys sit down around corner 
				CLEAR_SEQUENCE_TASK h9_sequence_task

				OPEN_SEQUENCE_TASK h9_sequence_task

					FLUSH_ROUTE

		 			TASK_TOGGLE_DUCK -1 TRUE

					EXTEND_ROUTE 2190.0032 1622.4248 998.9764 
					EXTEND_ROUTE 2186.1348 1623.3064 998.9749  
					EXTEND_ROUTE 2182.4402 1624.3328 998.9731  
					EXTEND_ROUTE 2178.6387 1623.8950 998.9739 
					EXTEND_ROUTE 2174.7563 1622.6923 998.9760  
					EXTEND_ROUTE 2169.4045 1620.6099 998.9795  
					
					TASK_ACHIEVE_HEADING -1 172.0000  

					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE

				CLOSE_SEQUENCE_TASK h9_sequence_task

				CLEAR_CHAR_TASKS h9_gang[3]

				PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task

			//two guys sit down around corner
				CLEAR_SEQUENCE_TASK h9_sequence_task

				OPEN_SEQUENCE_TASK h9_sequence_task

					FLUSH_ROUTE

		 			TASK_TOGGLE_DUCK -1 TRUE

					EXTEND_ROUTE 2188.5374 1615.5266 998.9766 
					EXTEND_ROUTE 2184.4114 1614.4672 998.9766  
					EXTEND_ROUTE 2180.2236 1614.0902 998.9766  
					EXTEND_ROUTE 2175.8552 1615.6554 998.9766 
					EXTEND_ROUTE 2169.4797 1617.1849 998.9766  
					
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE

				CLOSE_SEQUENCE_TASK h9_sequence_task
							
				CLEAR_CHAR_TASKS h9_gang[0]

				PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task
				
				CLEAR_SEQUENCE_TASK h9_sequence_task

				CLEAR_SEQUENCE_TASK h9_kill_seconds

				h9_second_node = 2

			ENDIF

		ENDIF

		//tag4
		IF IS_CHAR_DEAD h9_second_room[9]
		AND IS_CHAR_DEAD h9_second_room[10]
		AND h9_second_node = 2

			OPEN_SEQUENCE_TASK h9_sequence_task

				TASK_TOGGLE_DUCK -1 FALSE

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2157.8721 1603.2488 998.9730 PEDMOVE_RUN -1

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2144.3005 1610.4442 992.6882 PEDMOVE_RUN -1

			CLOSE_SEQUENCE_TASK h9_sequence_task

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD h9_gang[v]
					
					CLEAR_CHAR_TASKS h9_gang[v]

					PERFORM_SEQUENCE_TASK h9_gang[v] h9_sequence_task

				ENDIF

			ENDREPEAT

			CLEAR_SEQUENCE_TASK h9_sequence_task

			LVAR_INT h9_safe_dialogue

			h9_safe_dialogue = 1

			TIMERB = 0

			h9_second_node = 3

		ENDIF

		// ****************************************************************************************************
		// *																								  *
		// *							   		  First super node    										  *
		// *																								  *
		// ****************************************************************************************************
		//run0

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2195.0410 1618.9049 998.9766 2.0 2.0 2.0 FALSE

			IF NOT IS_CAR_DEAD h9_forklift
			AND NOT IS_CHAR_DEAD scplayer

				IF NOT IS_CHAR_IN_CAR scplayer h9_forklift

					DELETE_CAR h9_forklift

					MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT

				ENDIF

			ENDIF

			IF h9_guys_run_in = 0

				IF NOT IS_CHAR_DEAD h9_second_room[6]

					OPEN_SEQUENCE_TASK h9_sequence_task
						
						TASK_STAY_IN_SAME_PLACE -1 FALSE

						TASK_PAUSE -1 3000

						FLUSH_ROUTE

						EXTEND_ROUTE 2176.6279 1610.4725 998.9766  
						EXTEND_ROUTE 2173.5601 1623.9150 998.9739  

						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_ACHIEVE_HEADING -1 270.0000

					CLOSE_SEQUENCE_TASK h9_sequence_task

					PERFORM_SEQUENCE_TASK h9_second_room[6] h9_sequence_task

					CLEAR_SEQUENCE_TASK h9_sequence_task

				ENDIF

				IF NOT IS_CHAR_DEAD h9_second_room[7]

					CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task
						
						TASK_STAY_IN_SAME_PLACE -1 FALSE

						TASK_PAUSE -1 3000

						FLUSH_ROUTE

						EXTEND_ROUTE 2176.6279 1610.4725 998.9766  
						EXTEND_ROUTE 2175.9719 1613.8531 998.9766  

						TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

						FLUSH_ROUTE

						TASK_ACHIEVE_HEADING -1 270.0000

					CLOSE_SEQUENCE_TASK h9_sequence_task

					PERFORM_SEQUENCE_TASK h9_second_room[7] h9_sequence_task

					CLEAR_SEQUENCE_TASK h9_sequence_task

				ENDIF

				h9_guys_run_in = 1

			ENDIF
						
		ENDIF

		IF h9_play_safe_dialogue = 0

			IF h9_playing = 2
			AND h9_msg_displayed = 0

				PRINT_WITH_NUMBER_NOW ( HM9_1C ) 7 5000 1 // ~s~You have ~1~ minutes to get the money and back!

				h9_msg_displayed = 1

				TIMERB = 0
			ENDIF

			IF TIMERB > 5000
			AND h9_msg_displayed = 1

				PRINT_NOW ( HM9_21 ) 6000 1 // ~s~Clear the corridors of ~r~security ~s~so the team can reach the safe

				h9_msg_displayed = 2

				TIMERB = 0

			ENDIF

			IF TIMERB > 6000
			AND h9_msg_displayed = 2

				PRINT_NOW ( HM9_1G ) 7000 1 // ~s~Remember to stay close to the team 

				h9_msg_displayed = 3

			ENDIF

		ENDIF

		IF NOT h9_safe_dialogue = 0

			IF h9_safe_dialogue = 1

				REPEAT 3 v
					
					DELETE_CHAR h9_first_room[v]
					
				ENDREPEAT

				PRINT_NOW ( HM9_1E ) 5000 1 // ~s~Follow the ~b~team ~s~down to the safe

				h9_safe_dialogue = 2

				TIMERA = 0

			ENDIF

			IF TIMERA > 4000
			AND h9_safe_dialogue = 2

				$h9_print = &HE8_LA	// Not far now, keep alert.
				h9_audio = SOUND_HE8_LA
				GOSUB h9_load_sample

				h9_safe_dialogue = 3

			ENDIF

			IF h9_playing = 2
			AND h9_safe_dialogue = 3

				$h9_print = &HE8_LB	// Hey, I was just about to say that!
				h9_audio = SOUND_HE8_LB
				GOSUB h9_load_sample

				h9_safe_dialogue = 4

			ENDIF

			IF h9_playing = 2
			AND h9_safe_dialogue = 4

				$h9_print = &HE8_LC	// Sorry boss!
				h9_audio = SOUND_HE8_LC
				GOSUB h9_load_sample

				h9_safe_dialogue = 5

			ENDIF

			IF h9_playing = 2
			AND h9_safe_dialogue = 5

				$h9_print = &HE8_LD	// Not far now, everybody!
				h9_audio = SOUND_HE8_LD
				GOSUB h9_load_sample

				h9_safe_dialogue = 6

			ENDIF


			IF h9_playing = 2
			AND h9_safe_dialogue = 6

				$h9_print = &HE8_LE	// Stay alert!
				h9_audio = SOUND_HE8_LE
				GOSUB h9_load_sample

				h9_safe_dialogue = 7
				   
			ENDIF

			IF h9_playing = 2
			AND h9_safe_dialogue = 7

				$h9_print = &HE8_LF	// Oh yeah, stay alert!
				h9_audio = SOUND_HE8_LF
				GOSUB h9_load_sample

				h9_safe_dialogue = 0
				   
			ENDIF

		ENDIF

		h9_guys_at_safe = 0

		// ****************************************************************************************************
		// *																								  *
		// *							   			Dialog at safe											  *
		// *																								  *
		// ****************************************************************************************************

		IF h9_play_safe_dialogue > 1
		AND h9_player_in_safe = 1

			IF h9_playing = 2
			AND h9_msg_displayed = 0

				REPEAT 11 v
					
					DELETE_CHAR h9_second_room[v]
					
				ENDREPEAT

				ADD_BLIP_FOR_COORD 2144.1013 1623.4532 993.5677 h9_safe_blip
				SET_BLIP_ENTRY_EXIT h9_safe_blip 2196.6353 1676.8411 10.0

				CHANGE_BLIP_DISPLAY h9_safe_blip NEITHER

				$h9_print = &HE8_MA	// Ok, we'll set the charges while you watch the door!
				h9_audio = SOUND_HE8_MA
				GOSUB h9_load_sample

				h9_msg_displayed = 1

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 1

				$h9_print = &HE8_MB	// Ok, boss!
				h9_audio = SOUND_HE8_MB
				GOSUB h9_load_sample

				h9_msg_displayed = 2

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 2

				$h9_print = &HE8_MC	// I’m on it!
				h9_audio = SOUND_HE8_MC
				GOSUB h9_load_sample

				h9_msg_displayed = 3

				TIMERA = 0

			ENDIF

			// ****************************************************************************************************
			// *																								  *
			// *							   		Dialog for blowing safe										  *
			// *																								  *
			// ****************************************************************************************************

			IF h9_playing = 2
			AND h9_msg_displayed = 3

				$h9_print = &HE8_NA	// Hurry it up, you guys, they know something’s wrong!
				h9_audio = SOUND_HE8_NA
				GOSUB h9_load_sample

				h9_msg_displayed = 4

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 4

				$h9_print = &HE8_NB	// Someone else is in the system!
				h9_audio = SOUND_HE8_NB
				GOSUB h9_load_sample

				h9_msg_displayed = 5

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 5

				$h9_print = &HE8_NC	// What’s the problem?
				h9_audio = SOUND_HE8_NC
				GOSUB h9_load_sample

				h9_msg_displayed = 6

				TIMERA = 0

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 6

				$h9_print = &HE8_ND	// Someboy’s trying to bring the emergency generators back up!!
				h9_audio = SOUND_HE8_ND
				GOSUB h9_load_sample

				h9_msg_displayed = 7

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 7

				$h9_print = &HE8_NE	// I’ll head back up to the generator room and shut them down for good!
				h9_audio = SOUND_HE8_NE
				GOSUB h9_load_sample

				h9_msg_displayed = 8

				TIMERA = 0

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 8

				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE

					REQUEST_MODEL BMYBOUN
					
					WHILE NOT HAS_MODEL_LOADED BMYBOUN
						WAIT 0
					ENDWHILE

					CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2146.6653 1611.4515 999.9764 h9_generator_mafia[0]

					SET_CHAR_AREA_VISIBLE h9_generator_mafia[0] 1

					SET_CHAR_HEADING h9_generator_mafia[0] 189.8734 
					 
				    SET_CHAR_DECISION_MAKER h9_generator_mafia[0] h9_decision

					GIVE_WEAPON_TO_CHAR h9_generator_mafia[0] WEAPONTYPE_MP5 30000

					SET_CURRENT_CHAR_WEAPON h9_generator_mafia[0] WEAPONTYPE_MP5
					
					TASK_STAY_IN_SAME_PLACE h9_generator_mafia[0] TRUE

					SET_CHAR_MONEY h9_generator_mafia[0] 0 

					CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2147.9817 1605.2539 1000.7520 h9_generator_mafia[1]

					SET_CHAR_AREA_VISIBLE h9_generator_mafia[1] 1

					SET_CHAR_HEADING h9_generator_mafia[1] 191.7168 

					GIVE_WEAPON_TO_CHAR h9_generator_mafia[1] WEAPONTYPE_MP5 30000
					 
				    SET_CHAR_DECISION_MAKER h9_generator_mafia[1] h9_decision

					SET_CURRENT_CHAR_WEAPON h9_generator_mafia[1] WEAPONTYPE_MP5 
								
					TASK_STAY_IN_SAME_PLACE h9_generator_mafia[1] TRUE

					SET_CHAR_MONEY h9_generator_mafia[1] 0 

					IF DOES_BLIP_EXIST h9_safe_blip

						REMOVE_BLIP h9_safe_blip

					ENDIF

					REPEAT 4 v

						IF NOT IS_CHAR_DEAD h9_gang[v]

							SET_CHAR_PROOFS h9_gang[v] FALSE TRUE TRUE TRUE FALSE

						ENDIF

					ENDREPEAT

					PRINT_NOW ( HM9_22 ) 4000 1 //~s~Destroy the back up ~y~Generators~s~!

					h9_mission_status = 13
					
					ADD_BLIP_FOR_OBJECT h9_generator_A h9_gen_blip_a

					SET_BLIP_ENTRY_EXIT h9_gen_blip_a 2196.6353 1676.8411 10.0

					ADD_BLIP_FOR_OBJECT h9_generator_B h9_gen_blip_b

					SET_BLIP_ENTRY_EXIT h9_gen_blip_b 2196.6353 1676.8411 10.0

				ELSE

					PRINT_NOW ( HM9_PK ) 4000 1 //~s~Pick up the ~g~satchel charge~s~!

					ADD_BLIP_FOR_PICKUP h9_satchel_bomb h9_satchel_blip

					SET_BLIP_ENTRY_EXIT h9_satchel_blip 2196.6353 1676.8411 10.0

				ENDIF

				h9_msg_displayed = 101

				TIMERA = 0

			ENDIF

		ENDIF

		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE
		AND h9_msg_displayed = 101
		AND NOT h9_mission_status = 13

			REQUEST_MODEL BMYBOUN
			
			WHILE NOT HAS_MODEL_LOADED BMYBOUN
				WAIT 0
			ENDWHILE

			CREATE_CHAR PEDTYPE_MISSION1 WMYBOUN 2146.6653 1611.4515 999.9764 h9_generator_mafia[0]

			SET_CHAR_AREA_VISIBLE h9_generator_mafia[0] 1

			SET_CHAR_HEADING h9_generator_mafia[0] 189.8734 
			 
		    SET_CHAR_DECISION_MAKER h9_generator_mafia[0] h9_decision

			GIVE_WEAPON_TO_CHAR h9_generator_mafia[0] WEAPONTYPE_MP5 30000

			SET_CURRENT_CHAR_WEAPON h9_generator_mafia[0] WEAPONTYPE_MP5
			
			TASK_STAY_IN_SAME_PLACE h9_generator_mafia[0] TRUE

			CREATE_CHAR PEDTYPE_MISSION1 BMYBOUN 2147.9817 1605.2539 1000.7520 h9_generator_mafia[1]

			SET_CHAR_AREA_VISIBLE h9_generator_mafia[1] 1

			SET_CHAR_HEADING h9_generator_mafia[1] 191.7168 

			GIVE_WEAPON_TO_CHAR h9_generator_mafia[1] WEAPONTYPE_MP5 30000
			 
		    SET_CHAR_DECISION_MAKER h9_generator_mafia[1] h9_decision

			SET_CURRENT_CHAR_WEAPON h9_generator_mafia[1] WEAPONTYPE_MP5 
						
			TASK_STAY_IN_SAME_PLACE h9_generator_mafia[1] TRUE

			IF DOES_BLIP_EXIST h9_safe_blip

				REMOVE_BLIP h9_safe_blip

			ENDIF

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD h9_gang[v]

					SET_CHAR_PROOFS h9_gang[v] FALSE TRUE TRUE TRUE FALSE

				ENDIF

			ENDREPEAT

			IF DOES_BLIP_EXIST h9_satchel_blip

				REMOVE_BLIP h9_satchel_blip 

			ENDIF

			PRINT_NOW ( HM9_22 ) 4000 1 //~s~Destroy the back up ~y~Generators~s~!

			h9_mission_status = 13
			
			ADD_BLIP_FOR_OBJECT h9_generator_A h9_gen_blip_a

			SET_BLIP_ENTRY_EXIT h9_gen_blip_a 2196.6353 1676.8411 10.0

			ADD_BLIP_FOR_OBJECT h9_generator_B h9_gen_blip_b

			SET_BLIP_ENTRY_EXIT h9_gen_blip_b 2196.6353 1676.8411 10.0

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2144.3933 1616.6534 992.5681 20.0 20.0 2.0 FALSE

			IF NOT h9_msg_displayed = 0
			AND NOT h9_msg_displayed = 9
			AND h9_play_safe_dialogue > 1

				IF DOES_BLIP_EXIST h9_safe_blip
					CHANGE_BLIP_DISPLAY h9_safe_blip NEITHER
				ENDIF

			ENDIF

			h9_player_in_safe = 1
			
		ELSE

			IF NOT h9_msg_displayed = 0
			AND NOT h9_msg_displayed = 9
			AND h9_play_safe_dialogue > 1

				IF DOES_BLIP_EXIST h9_safe_blip
					CHANGE_BLIP_DISPLAY h9_safe_blip BOTH
				ENDIF

				PRINT_NOW ( HM9_56 ) 1000 1 // ~s~The team needs you at the ~y~Safe~s~.

			ENDIF

			h9_player_in_safe = 0	

		ENDIF

		REPEAT 4 v

			IF NOT IS_CHAR_DEAD h9_gang[v]

				IF LOCATE_CHAR_ANY_MEANS_3D h9_gang[v] 2143.6934 1610.6278 992.6882 4.0 4.0 2.0 FALSE
				AND h9_in_cover[v] = 0

						IF v = 0
						AND NOT IS_CHAR_DEAD h9_gang[0]

							OPEN_SEQUENCE_TASK h9_sequence_task 

								FLUSH_ROUTE

 								EXTEND_ROUTE 2144.2024 1618.6958 992.6882 
								EXTEND_ROUTE 2143.0361 1626.4974 992.5681  

								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

								FLUSH_ROUTE

								TASK_TURN_CHAR_TO_FACE_COORD -1 2144.2314 1629.5068 992.5703

								TASK_STAY_IN_SAME_PLACE -1 TRUE

								TASK_PLAY_ANIM -1 BOM_plant_loop BOMBER 1.0 TRUE FALSE FALSE FALSE -1

								TASK_STAY_IN_SAME_PLACE -1 TRUE

								SET_SEQUENCE_TO_REPEAT h9_sequence_task 1

							CLOSE_SEQUENCE_TASK h9_sequence_task

							PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task

						ENDIF

						IF v = 1
						AND NOT IS_CHAR_DEAD h9_gang[1]

							OPEN_SEQUENCE_TASK h9_sequence_task 

								FLUSH_ROUTE

 								EXTEND_ROUTE 2144.2024 1618.6958 992.6882 
								EXTEND_ROUTE 2145.6960 1626.4962 992.5681  

								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

								FLUSH_ROUTE

								TASK_TURN_CHAR_TO_FACE_COORD -1 2144.2314 1629.5068 992.5703

								TASK_STAY_IN_SAME_PLACE -1 TRUE

								TASK_PLAY_ANIM -1 BOM_plant_loop BOMBER 1.0 TRUE FALSE FALSE FALSE -1

								SET_SEQUENCE_TO_REPEAT h9_sequence_task 1

							CLOSE_SEQUENCE_TASK h9_sequence_task

							PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task

						ENDIF

						IF v = 2
						AND NOT IS_CHAR_DEAD h9_gang[2]

							OPEN_SEQUENCE_TASK h9_sequence_task 

								FLUSH_ROUTE

  								EXTEND_ROUTE 2144.2024 1618.6958 992.6882
								EXTEND_ROUTE 2147.7441 1620.4473 992.6882  

								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

								FLUSH_ROUTE

								TASK_STAY_IN_SAME_PLACE -1 TRUE
																									
								TASK_TURN_CHAR_TO_FACE_COORD -1 2144.2314 1629.5068 992.5703

							CLOSE_SEQUENCE_TASK h9_sequence_task

							PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task

						ENDIF

						IF v = 3
						AND NOT IS_CHAR_DEAD h9_gang[3]

							OPEN_SEQUENCE_TASK h9_sequence_task 

								FLUSH_ROUTE

 								EXTEND_ROUTE 2144.2024 1618.6958 992.6882
								EXTEND_ROUTE 2136.2537 1621.6277 992.6882  

								TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

								FLUSH_ROUTE

								TASK_STAY_IN_SAME_PLACE -1 TRUE

								TASK_TURN_CHAR_TO_FACE_COORD -1 2144.2314 1629.5068 992.5703

							CLOSE_SEQUENCE_TASK h9_sequence_task

							PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task

						ENDIF

					CLEAR_SEQUENCE_TASK h9_sequence_task

					SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_gang[v] FALSE

					CHANGE_BLIP_DISPLAY h9_gang_blip[v] NEITHER

					h9_in_cover[v] = 1

					IF h9_play_safe_dialogue = 0
						h9_msg_displayed = 0
					ENDIF

					h9_play_safe_dialogue ++

				ENDIF

			ENDIF

		ENDREPEAT

	ENDIF

	// *************************************************************************************************
	// *																							   *
	// *								  BLOWING THE GENERATORS					  				   *
	// *                                  															   *
	// *************************************************************************************************
 																							   
	IF h9_mission_status = 13
	AND TIMERA > 5000

		IF h9_blown = 0

			IF h9_playing = 2
			AND h9_msg_displayed = 0

				$h9_print = &HE8_OA	// Berkley! It’s him, I know it!
				h9_audio = SOUND_HE8_OA
				GOSUB h9_load_sample

				h9_msg_displayed = 1

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 1

				$h9_print = &HE8_OB	// What?
				h9_audio = SOUND_HE8_OB
				GOSUB h9_load_sample

				h9_msg_displayed = 2

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 2

				$h9_print = &HE8_OC	// I know the tactics of my arch rival!
				h9_audio = SOUND_HE8_OC
				GOSUB h9_load_sample

				h9_msg_displayed = 3

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 3

				$h9_print = &HE8_OD	// BERKLEY YOU BASTARD!
				h9_audio = SOUND_HE8_OD
				GOSUB h9_load_sample

				h9_msg_displayed = 4

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 4


				$h9_print = &HE8_OE	// Quit shouting in my ear!
				h9_audio = SOUND_HE8_OE
				GOSUB h9_load_sample

				h9_msg_displayed = 5

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 5

				$h9_print = &HE8_OF	// Sorry.
				h9_audio = SOUND_HE8_OF
				GOSUB h9_load_sample

				h9_msg_displayed = 8

			ENDIF

			IF h9_playing = 2
			AND h9_msg_displayed = 8

				$h9_print = &HE8_OJ	// Whatever. Just deal with the fool!
				h9_audio = SOUND_HE8_OJ
				GOSUB h9_load_sample

				h9_msg_displayed = 9

			ENDIF   

		ENDIF
		
		IF NOT IS_CHAR_DEAD scplayer

			GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE h9_grenade_count

		ENDIF

		IF NOT IS_ANY_PICKUP_AT_COORDS 2146.4871 1622.8053 993.0000
			IF h9_grenade_count <= 0
			AND h9_blown = 0

				IF NOT DOES_PICKUP_EXIST h9_satchel_bomb

					CREATE_PICKUP_WITH_AMMO satchel PICKUP_ONCE 5 2146.4871 1622.8053 993.0000 h9_satchel_bomb

				ENDIF

			ENDIF
		ENDIF

		IF IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE 2150.9658 1618.8569 999.9688 2142.4719 1608.0663 1003.0000
		OR IS_EXPLOSION_IN_AREA EXPLOSION_MOLOTOV 2150.9658 1618.8569 999.9688 2142.4719 1608.0663 1003.0000
		OR IS_EXPLOSION_IN_AREA EXPLOSION_ROCKET_WEAK 2150.9658 1618.8569 999.9688 2142.4719 1608.0663 1003.0000
		OR IS_EXPLOSION_IN_AREA EXPLOSION_ROCKET 2150.9658 1618.8569 999.9688 2142.4719 1608.0663 1003.0000

			IF h9_blown = 0

				// Roller Door

				CREATE_OBJECT_NO_OFFSET warehouse_door2b 2195.7693 1585.1992 1002.0000 h9_stop_door

				SET_OBJECT_AREA_VISIBLE h9_stop_door 1

				SET_OBJECT_HEADING h9_stop_door 180.0000

				SET_OBJECT_COLLISION_DAMAGE_EFFECT h9_stop_door FALSE

				GOSUB h9_set_camera

				SET_OBJECT_COLLISION_DAMAGE_EFFECT h9_door_g TRUE

				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2146.1462 1613.8185 999.9680 7.0 7.0 2.0 FALSE
				AND NOT IS_CHAR_DEAD scplayer

					TASK_DIE scplayer				

				ENDIF

				IF DOES_BLIP_EXIST h9_gen_blip_a
					REMOVE_BLIP h9_gen_blip_a
				ENDIF

				IF DOES_BLIP_EXIST h9_gen_blip_b			
					REMOVE_BLIP h9_gen_blip_b
				ENDIF

			 	SET_FIXED_CAMERA_POSITION 2141.3608 1619.8289 1000.6694 0.0 0.0 0.0 // Charges Ignited!!!
			 	POINT_CAMERA_AT_POINT 2141.9250 1619.0051 1000.7231 JUMP_CUT
				
				GOSUB explosion_proof

				DELETE_OBJECT h9_generator_A

				DELETE_OBJECT h9_generator_B
				
				MARK_MODEL_AS_NO_LONGER_NEEDED GENERATOR_BIG

				CREATE_OBJECT GENERATOR_BIG_d 2145.05 1613.49 999.9764 h9_generator_A
															
				SET_OBJECT_HEADING h9_generator_A 1.2043 

				CREATE_OBJECT GENERATOR_BIG_d 2148.8 1613.49 999.9764 h9_generator_B 
												
				SET_OBJECT_HEADING h9_generator_B 354.0372 

				LVAR_INT h9_loop 

				REPEAT 3 h9_loop

					ADD_EXPLOSION 2143.1069 1611.3320 1000.5000 EXPLOSION_SMALL

					CREATE_FX_SYSTEM EXPLOSION_SMALL 2143.1069 1611.3320 1000.5000 TRUE h9_explosion_fx[0]

					PLAY_FX_SYSTEM h9_explosion_fx[0]

					WAIT 150

					ADD_EXPLOSION 2149.5103 1614.5864 1000.5000 EXPLOSION_SMALL

					CREATE_FX_SYSTEM EXPLOSION_SMALL 2149.5103 1614.5864 1000.5000 TRUE h9_explosion_fx[1] 

					PLAY_FX_SYSTEM h9_explosion_fx[1]
					
					WAIT 10

					REPEAT 6 v

						STOP_FX_SYSTEM h9_explosion_fx[v]

						KILL_FX_SYSTEM h9_explosion_fx[v]

					ENDREPEAT

				ENDREPEAT

				WAIT 2000 

				REPEAT 4 v

					CHANGE_BLIP_DISPLAY h9_gang_blip[v] BOTH

					IF NOT IS_CHAR_DEAD h9_gang[v] 

						TASK_STAY_IN_SAME_PLACE h9_gang[v] FALSE

						SET_CHAR_PROOFS h9_gang[v] FALSE FALSE FALSE TRUE FALSE

					ENDIF

				ENDREPEAT
	 
				h9_msg_displayed = 6

				GOSUB h9_restore_camera

				GOSUB not_explosion_proof

				TIMERA = 0

				h9_blown = 1

				CLEAR_HELP

				REPEAT 4 v

				ENDREPEAT

				IF IS_ANY_PICKUP_AT_COORDS 2146.4871 1622.8053 993.0000

					REMOVE_PICKUP h9_satchel_bomb

				ENDIF

				PRINT_NOW ( HM9_40 ) 5000 1 // ~s~Head downstairs and check on the team!

			ENDIF

		ENDIF

		// **********************************************************************************************
		// *																							*
		// *								 Dialog check on the team  									*
		// *																							*
		// **********************************************************************************************

		IF h9_blown = 1

			IF h9_playing = 2
			AND h9_msg_displayed = 0

				$h9_print = &HE8_OK	// Ok, I’m heading back down to the vault!
				h9_audio = SOUND_HE8_OK
				GOSUB h9_load_sample

				h9_msg_displayed = 10

			ENDIF

		ENDIF

		IF h9_sync = 0
		AND h9_blown = 1

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2143.9749 1624.4430 992.5681 20.0 20.0 2.0 FALSE

				try_again_here:

				h9_msg_displayed = 10

				h9_sync = 1

				REPEAT 4 v

					CHANGE_BLIP_DISPLAY h9_gang_blip[v] NEITHER

				ENDREPEAT

				GOSUB h9_set_camera
	
				IF NOT IS_CHAR_DEAD h9_gang[2]
				
					SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[2] 2147.7441 1620.4473 992.6882

					SET_CHAR_HEADING h9_gang[2] 346.8824 

				ENDIF
					
				IF NOT IS_CHAR_DEAD h9_gang[3]
				
					SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[3] 2136.2537 1621.6277 992.6882	

					SET_CHAR_HEADING h9_gang[3] 358.8987

				ENDIF

				SET_FIXED_CAMERA_POSITION 2140.4893 1617.6891 993.7817 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 2140.8308 1618.6289 993.7844 JUMP_CUT

				WAIT 1000

				PRINT_NOW ( HE8_QA ) 4000 1 // Everybody take cover!
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_QA // Everybody take cover!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
					//	GOTO h9_blowing_door
					ENDIF
				ENDWHILE

			   	PRINT_NOW ( HE8_QC ) 4000 1 // Oh shit!  Where do I go?  Where do I go?
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_QC // Oh shit!  Where do I go?  Where do I go?

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				IF NOT IS_CHAR_DEAD h9_gang[0]

					TASK_PAUSE h9_gang[0] 1000

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[0]
					 
					FLUSH_ROUTE
	 	
					EXTEND_ROUTE 2135.4968 1625.5176 992.56812   
					 
					TASK_TOGGLE_DUCK h9_gang[0] TRUE

					TASK_FOLLOW_POINT_ROUTE h9_gang[0] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE

				ENDIF

				IF NOT IS_CHAR_DEAD h9_gang[1]

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[1]
										 
					FLUSH_ROUTE
	 	
					EXTEND_ROUTE 2152.9541 1625.0764 992.5681   
					 
					TASK_TOGGLE_DUCK h9_gang[1] TRUE

					TASK_FOLLOW_POINT_ROUTE h9_gang[1] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE

				ENDIF

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
					//	GOTO h9_blowing_door
					ENDIF
				ENDWHILE  

				PRINT_NOW ( HE8_QB ) 4000 1 // Fire in the hold!
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_QB // Fire in the hold!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
					//	GOTO h9_blowing_door
					ENDIF
				ENDWHILE

				REPEAT 4 v

					IF NOT IS_CHAR_DEAD h9_gang[v]

						CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[v]

						TASK_TOGGLE_DUCK h9_gang[v] TRUE

					ENDIF

					CHANGE_BLIP_DISPLAY h9_gang_blip[v] BOTH

				ENDREPEAT

				LVAR_INT h9_door_explode[3]	h9_cash_room_blip

				ADD_EXPLOSION 2144.5332 1626.7472 992.7070 EXPLOSION_SMALL

				CREATE_FX_SYSTEM EXPLOSION_SMALL 2144.5332 1626.7472 992.7070 TRUE h9_door_explode[0] 

				PLAY_AND_KILL_FX_SYSTEM h9_door_explode[0]

				ADD_EXPLOSION 2144.5332 1626.7472 994.0000 EXPLOSION_SMALL

				WAIT 500

				CREATE_FX_SYSTEM EXPLOSION_SMALL 2144.5332 1626.7472 994.0000 TRUE h9_door_explode[1] 

				PLAY_AND_KILL_FX_SYSTEM h9_door_explode[1]

				ADD_EXPLOSION 2144.5332 1626.7472 996.0000 EXPLOSION_SMALL

				DELETE_OBJECT h9_door_g

				WAIT 500

				CREATE_FX_SYSTEM EXPLOSION_SMALL 2144.5332 1626.7472 996.0000 TRUE h9_door_explode[2] 

				PLAY_AND_KILL_FX_SYSTEM h9_door_explode[2]

				PRINT_NOW ( HE8_RA ) 4000 1 // Ok people, load up the cash!
				
				CLEAR_MISSION_AUDIO 1

			 	LOAD_MISSION_AUDIO 1 SOUND_HE8_RA // Ok people, load up the cash!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
					WAIT 0
				ENDWHILE

				PLAY_MISSION_AUDIO 1

				TIMERB = 0
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
				    WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					OR IS_BUTTON_PRESSED PAD1 CIRCLE
					//	GOTO h9_blowing_door
					ENDIF
				ENDWHILE

				IF NOT IS_CHAR_DEAD scplayer

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2144.2659 1610.6492 992.5681 

					SET_CHAR_HEADING scplayer 359.3881 						

				ENDIF

				GOSUB h9_restore_camera

				PRINT_NOW ( HM9_58 ) 7000 1 // ~s~Enter the ~y~Safe~s~.
												   
				CREATE_OBJECT CJ_MONEY_BAG 2146.2703 1636.3137 992.5683 h9_bag[0]

				SET_OBJECT_AREA_VISIBLE h9_bag[0] 1
																		
				SET_OBJECT_HEADING h9_bag[0] 268.4277 

				SET_OBJECT_COLLISION h9_bag[0] FALSE

				CREATE_OBJECT CJ_MONEY_BAG 2146.1367 1632.4038 992.5683 h9_bag[1]

				SET_OBJECT_AREA_VISIBLE h9_bag[1] 1

				SET_OBJECT_HEADING h9_bag[1] 89.5044 

				SET_OBJECT_COLLISION h9_bag[1] FALSE

				CREATE_OBJECT CJ_MONEY_BAG 2142.1482 1632.4264 992.5683 h9_bag[2]

				SET_OBJECT_AREA_VISIBLE h9_bag[2] 1

				SET_OBJECT_HEADING h9_bag[2] 97.3067  

				SET_OBJECT_COLLISION h9_bag[2] FALSE

				CREATE_OBJECT CJ_MONEY_BAG 2142.0696 1636.1366 992.5683 h9_bag[3]

				SET_OBJECT_AREA_VISIBLE h9_bag[3] 1

				SET_OBJECT_HEADING h9_bag[3] 51.5319

				SET_OBJECT_COLLISION h9_bag[3] FALSE
				
				IF NOT IS_CHAR_DEAD h9_gang[0]
				AND NOT IS_CHAR_DEAD h9_gang[1]
				AND NOT IS_CHAR_DEAD h9_gang[2]
				AND NOT IS_CHAR_DEAD h9_gang[3]

					LVAR_INT h9_escape_seq

					OPEN_SEQUENCE_TASK h9_escape_seq

						TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer

						TASK_PAUSE -1 500

						SET_SEQUENCE_TO_REPEAT h9_escape_seq 1

					CLOSE_SEQUENCE_TASK h9_escape_seq

					// ------------------------------------------------------------------------------------------------------------

					OPEN_SEQUENCE_TASK h9_sequence_task 

						TASK_TOGGLE_DUCK -1 TRUE

						TASK_PAUSE -1 3000

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 2146.2703 1636.3137 992.5683 PEDMOVE_RUN -1

						TASK_PICK_UP_OBJECT -1 h9_bag[0] 0.0 0.0 -0.5 PED_HANDR HOLD_ORIENTATE_PEDHEADING NULL NULL FALSE

						TASK_TOGGLE_DUCK -1 FALSE

						TASK_STAY_IN_SAME_PLACE -1 TRUE

						PERFORM_SEQUENCE_TASK -1 h9_escape_seq 

					CLOSE_SEQUENCE_TASK h9_sequence_task

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[0]

					TASK_TOGGLE_DUCK h9_gang[0] FALSE

					PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task

					// ------------------------------------------------------------------------------------------------------------

			 		CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task 

						TASK_PAUSE -1 100

						TASK_TOGGLE_DUCK -1 TRUE

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 2146.1367 1632.4038 992.5683 PEDMOVE_RUN -1

						TASK_PICK_UP_OBJECT -1 h9_bag[1] 0.0 0.0 -0.5 PED_HANDR HOLD_ORIENTATE_PEDHEADING NULL NULL FALSE

						TASK_TOGGLE_DUCK -1 FALSE

						TASK_STAY_IN_SAME_PLACE -1 TRUE

						PERFORM_SEQUENCE_TASK -1 h9_escape_seq 

					CLOSE_SEQUENCE_TASK h9_sequence_task   

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[1]

					TASK_TOGGLE_DUCK h9_gang[1] FALSE

					PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task

					// ------------------------------------------------------------------------------------------------------------

			 		CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task 
										   
						TASK_PAUSE -1 200

						TASK_TOGGLE_DUCK -1 TRUE

						TASK_PAUSE -1 2000

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 2142.1482 1632.4264 992.5683 PEDMOVE_RUN -1

						TASK_PICK_UP_OBJECT -1 h9_bag[2] 0.0 0.0 -0.5 PED_HANDR HOLD_ORIENTATE_PEDHEADING NULL NULL FALSE

						TASK_TOGGLE_DUCK -1 FALSE

						TASK_STAY_IN_SAME_PLACE -1 TRUE

						PERFORM_SEQUENCE_TASK -1 h9_escape_seq 

					CLOSE_SEQUENCE_TASK h9_sequence_task 

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[2]

					TASK_TOGGLE_DUCK h9_gang[2] FALSE

					PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task

					// ------------------------------------------------------------------------------------------------------------

			 		CLEAR_SEQUENCE_TASK h9_sequence_task

					OPEN_SEQUENCE_TASK h9_sequence_task 
					   
						TASK_PAUSE -1 100

						TASK_TOGGLE_DUCK -1 TRUE

						TASK_PAUSE -1 3000

						TASK_FOLLOW_PATH_NODES_TO_COORD -1 2142.0696 1636.1366 992.5683 PEDMOVE_RUN -1

						TASK_PICK_UP_OBJECT -1 h9_bag[3] 0.0 0.0 -0.5 PED_HANDR HOLD_ORIENTATE_PEDHEADING NULL NULL FALSE

						TASK_TOGGLE_DUCK -1 FALSE

						TASK_STAY_IN_SAME_PLACE -1 TRUE

						PERFORM_SEQUENCE_TASK -1 h9_escape_seq 

					CLOSE_SEQUENCE_TASK h9_sequence_task

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[3]

					TASK_TOGGLE_DUCK h9_gang[3] FALSE

					PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task

					// ------------------------------------------------------------------------------------------------------------

					REMOVE_ANIMATION BOMBER

					ADD_BLIP_FOR_COORD 2144.1521 1636.4618 992.5703 h9_cash_room_blip
					SET_BLIP_ENTRY_EXIT h9_cash_room_blip 2196.6353 1676.8411 10.0

					CLEAR_SEQUENCE_TASK h9_escape_seq
					CLEAR_SEQUENCE_TASK h9_sequence_task

				ENDIF

			ENDIF

		ENDIF

		//safe0
		IF h9_sync = 1
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2144.1521 1636.4618 992.5703 10.0 10.0 2.0 FALSE
			AND DOES_BLIP_EXIST h9_cash_room_blip

				IF DOES_BLIP_EXIST h9_cash_room_blip
					REMOVE_BLIP h9_cash_room_blip
				ENDIF

				h9_cash_dialog = 1
				
				h9_sync = 2

				TIMERB = 0

				REPEAT 4 v

					IF NOT IS_CHAR_DEAD h9_gang[v]

						GET_CHAR_HEALTH h9_gang[v] h9_gang_health[v]

						h9_hlth[v] = h9_gang_health[v]
								
					ENDIF
					
				ENDREPEAT

			ENDIF
		ENDIF

		IF h9_playing = 2
		AND h9_cash_dialog = 1

			$h9_print = &HE8_RB	// Carl, you’ve got Mafia gorillas coming down to the vault and-
			h9_audio = SOUND_HE8_RB
			GOSUB h9_load_sample

			h9_cash_dialog = 2

		ENDIF

		IF h9_playing = 2
		AND h9_cash_dialog = 2

			$h9_print = &HE8_RC	// CURSE YOU BERKLEY, CURSE YOU!
			h9_audio = SOUND_HE8_RC
			GOSUB h9_load_sample

			TIMERA = 0

			h9_cash_dialog = 3

		ENDIF	

		IF h9_playing = 2
		AND h9_cash_dialog = 3
		AND TIMERA > 3000

			GOSUB h9_fade_out

			GOSUB h9_set_camera

		    h9_bag_count = 4

			REPEAT 11 v

				IF NOT IS_CHAR_DEAD h9_second_room[v]
												  
					DELETE_CHAR h9_second_room[v]

				ENDIF

			ENDREPEAT 
		    
			REPEAT 3 v
				
				DELETE_CHAR h9_safe_guards[v]

			ENDREPEAT

		    DELETE_CHAR h9_generator_mafia[0]

		    DELETE_CHAR h9_generator_mafia[1]
		    	
			LVAR_INT h9_run_into_safe

			MARK_MODEL_AS_NO_LONGER_NEEDED BMYBOUN
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN

			REQUEST_MODEL VMAFF1
			REQUEST_MODEL VMAFF2

			WHILE NOT HAS_MODEL_LOADED VMAFF1
			OR NOT HAS_MODEL_LOADED VMAFF2

				WAIT 0

			ENDWHILE

			// ----------------------------------------------------------------------------------------------

			//Players Left pillar enemy 
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2150.8308 1611.0328 992.6882 h9_second_room[0]

			SET_CHAR_AREA_VISIBLE h9_second_room[0] 1

			SET_CHAR_HEADING h9_second_room[0] 353.2278 

			SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_second_room[0] TRUE

			// ----------------------------------------------------------------------------------------------

			//Players right enemy pillar
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2135.7017 1611.0641 992.6882 h9_second_room[1]

			SET_CHAR_AREA_VISIBLE h9_second_room[1] 1

			SET_CHAR_HEADING h9_second_room[1] 354.2745 

			// ----------------------------------------------------------------------------------------------

			OPEN_SEQUENCE_TASK h9_run_into_safe

				FLUSH_ROUTE
	 	
				EXTEND_ROUTE 2144.1404 1610.0145 992.6882   
				EXTEND_ROUTE 2146.0854 1611.8956 992.6882
				 
				TASK_TOGGLE_DUCK -1 TRUE

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
									
				TASK_ACHIEVE_HEADING -1 6.3138

				TASK_STAND_STILL -1 TRUE

				FLUSH_ROUTE

				IF NOT IS_CHAR_DEAD scplayer
					TASK_KILL_CHAR_ON_FOOT -1 scplayer 
				ENDIF

			CLOSE_SEQUENCE_TASK h9_run_into_safe

			// ----------------------------------------------------------------------------------------------

			//Enemy starts crouching			
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2144.0288 1605.3518 992.5684 h9_second_room[2]
														   
			SET_CHAR_AREA_VISIBLE h9_second_room[2] 1

			SET_CHAR_HEADING h9_second_room[2] 356.6950

			PERFORM_SEQUENCE_TASK h9_second_room[2] h9_run_into_safe

			// ----------------------------------------------------------------------------------------------

			CLEAR_SEQUENCE_TASK h9_run_into_safe

			OPEN_SEQUENCE_TASK h9_run_into_safe

				FLUSH_ROUTE
	 	
				EXTEND_ROUTE 2144.1404 1610.0145 992.6882   
				EXTEND_ROUTE 2139.8154 1615.4709 992.6882
				 
				TASK_TOGGLE_DUCK -1 TRUE

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
								 
				TASK_TOGGLE_DUCK -1 FALSE
	
				TASK_ACHIEVE_HEADING -1 335.8946 

				TASK_STAND_STILL -1 TRUE
			
				FLUSH_ROUTE

				IF NOT IS_CHAR_DEAD scplayer
					TASK_KILL_CHAR_ON_FOOT -1 scplayer 
				ENDIF

			CLOSE_SEQUENCE_TASK h9_run_into_safe

			// ----------------------------------------------------------------------------------------------

			//Enemy starts 
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2144.2205 1601.9634 992.5684 h9_second_room[3]

			SET_CHAR_AREA_VISIBLE h9_second_room[3] 1

			SET_CHAR_HEADING h9_second_room[3] 358.3628 

			PERFORM_SEQUENCE_TASK h9_second_room[3] h9_run_into_safe

			// ----------------------------------------------------------------------------------------------

			//Reinforcements
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2146.6050 1597.7847 994.5676 h9_second_room[4]

			SET_CHAR_AREA_VISIBLE h9_second_room[4] 1

			SET_CHAR_HEADING h9_second_room[4] 89.7654 

			SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_second_room[4] TRUE

			TASK_STAY_IN_SAME_PLACE h9_second_room[4] TRUE

			SET_SENSE_RANGE h9_second_room[4] 5.0

			// ----------------------------------------------------------------------------------------------

			//Reinforcements			
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2147.7700 1596.4790 994.5676 h9_second_room[5]
														   
			SET_CHAR_AREA_VISIBLE h9_second_room[5] 1

			SET_CHAR_HEADING h9_second_room[5] 78.7631 

			TASK_STAY_IN_SAME_PLACE h9_second_room[5] TRUE

			SET_SENSE_RANGE h9_second_room[5] 5.0

			// ----------------------------------------------------------------------------------------------

			//Reinforcements			
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2148.6267 1602.2352 996.7766 h9_second_room[6]
														   
			SET_CHAR_AREA_VISIBLE h9_second_room[6] 1

			SET_CHAR_HEADING h9_second_room[6] 170.4726 

			TASK_STAY_IN_SAME_PLACE h9_second_room[6] TRUE

			SET_SENSE_RANGE h9_second_room[6] 5.0
								 
			// ----------------------------------------------------------------------------------------------

			//Reinforcements			
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2148.3579 1603.7339 996.7766 h9_second_room[7]
														   
			SET_CHAR_AREA_VISIBLE h9_second_room[7] 1

			SET_CHAR_HEADING h9_second_room[7] 158.8790 

			TASK_STAY_IN_SAME_PLACE h9_second_room[7] TRUE

			SET_SENSE_RANGE h9_second_room[7] 5.0

			// ----------------------------------------------------------------------------------------------

			CLEAR_SEQUENCE_TASK h9_run_into_safe

			REPEAT 8 v

				SET_CHAR_KEEP_TASK h9_second_room[v] TRUE

				SET_CHAR_MONEY h9_second_room[v] 0

				IF NOT v = 0
				AND NOT v = 4
						
					SET_CHAR_HEALTH h9_second_room[v] 200
						
					SET_CHAR_MAX_HEALTH h9_second_room[v] 200

				ENDIF

				IF v = 3
				OR v = 4
				OR v = 7

					SET_CHAR_WEAPON_SKILL h9_second_room[v] WEAPONSKILL_PRO

					GIVE_WEAPON_TO_CHAR h9_second_room[v] WEAPONTYPE_PISTOL 9999

				ELSE

				   	GIVE_WEAPON_TO_CHAR h9_second_room[v] WEAPONTYPE_MP5 9999

				  	SET_CURRENT_CHAR_WEAPON h9_second_room[v] WEAPONTYPE_MP5
				
				ENDIF	

				ADD_BLIP_FOR_CHAR h9_second_room[v] h9_seconds_inv[v]
				
				SET_BLIP_ENTRY_EXIT h9_seconds_inv[v] 2196.6353 1676.8411 10.0
											
				CHANGE_BLIP_DISPLAY h9_seconds_inv[v] BLIP_ONLY

				SET_CHAR_SHOOT_RATE h9_second_room[v] 30

				SET_CHAR_ACCURACY h9_second_room[v] 20

				SET_CHAR_DECISION_MAKER h9_second_room[v] h9_decision_tough

			ENDREPEAT

			// ----------------------------------------------------------------------------------------------

			SET_FIXED_CAMERA_POSITION 2142.2483 1611.1133 993.2602 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2142.6694 1610.2107 993.3487 JUMP_CUT
							
			IF NOT IS_CHAR_DEAD scplayer

				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2144.0476 1627.9856 992.5703 

				SET_CHAR_HEADING scplayer 181.8141

			ENDIF

			GOSUB h9_fade_in
 
			PRINT_NOW ( HM9_57 ) 5000 1 // ~s~Kill the ~r~Mafia whilst the team gets the cash.

			WAIT 2000

			SET_FIXED_CAMERA_POSITION 2140.7847 1619.8314 993.4654 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2141.0776 1618.8760 993.4291 JUMP_CUT
										
			IF NOT IS_CHAR_DEAD h9_second_room[3]

				CLEAR_CHAR_TASKS h9_second_room[3]
				
				TASK_TOGGLE_DUCK h9_second_room[3] TRUE

				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_second_room[3] 2140.2488 1615.1196 992.6882  

				SET_CHAR_HEADING h9_second_room[3] 340.6346 

			ENDIF

			WAIT 3000

			IF NOT IS_CHAR_DEAD h9_second_room[0]
				PERFORM_SEQUENCE_TASK h9_second_room[0] h9_peekright
			ENDIF

			IF NOT IS_CHAR_DEAD h9_second_room[1]
				PERFORM_SEQUENCE_TASK h9_second_room[1] h9_peekleft
			ENDIF
						
			IF DOES_OBJECT_EXIST h9_crt_0

				SET_OBJECT_COORDINATES h9_crt_0 2173.8669 1619.4147 999.3000

			ENDIF

			GOSUB h9_restore_camera

			$h9_print = &HE8_RD	// Ok, we're about to have company!
			h9_audio = SOUND_HE8_RD
			GOSUB h9_load_sample

			h9_cash_dialog = 4

			LVAR_INT h9_given_ai

			TIMERA = 0

		ENDIF

		IF h9_cash_dialog = 4

			IF IS_CHAR_DEAD h9_second_room[0]
			AND IS_CHAR_DEAD h9_second_room[1]
			AND IS_CHAR_DEAD h9_second_room[2]
			AND IS_CHAR_DEAD h9_second_room[3]
			AND h9_given_ai = 0

				// ----------------------------------------------------------------------------------------------

				CLEAR_SEQUENCE_TASK h9_run_into_safe

				OPEN_SEQUENCE_TASK h9_run_into_safe

					FLUSH_ROUTE
	 			 
					EXTEND_ROUTE 2143.9272 1597.6738 994.5676 
					EXTEND_ROUTE 2144.1401 1603.1108 992.5684  
					EXTEND_ROUTE 2142.9067 1614.1505 992.6882  
					EXTEND_ROUTE 2143.1052 1621.5706 992.6882
 
					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
				
					FLUSH_ROUTE
									
					TASK_TOGGLE_DUCK -1 TRUE

				CLOSE_SEQUENCE_TASK h9_run_into_safe
								
				// ----------------------------------------------------------------------------------------------

				IF NOT IS_CHAR_DEAD h9_second_room[4]

					CLEAR_CHAR_TASKS h9_second_room[4]

					PERFORM_SEQUENCE_TASK h9_second_room[4] h9_run_into_safe

				ENDIF

				// ----------------------------------------------------------------------------------------------

				IF NOT IS_CHAR_DEAD h9_second_room[5]

					CLEAR_CHAR_TASKS h9_second_room[5]

					PERFORM_SEQUENCE_TASK h9_second_room[5] h9_run_into_safe

				ENDIF

				// ----------------------------------------------------------------------------------------------

				h9_given_ai = 1

			ENDIF

			IF IS_CHAR_DEAD h9_second_room[4]
			AND IS_CHAR_DEAD h9_second_room[5]
			AND h9_given_ai = 1

				// ----------------------------------------------------------------------------------------------

				CLEAR_SEQUENCE_TASK h9_run_into_safe

				OPEN_SEQUENCE_TASK h9_run_into_safe

					FLUSH_ROUTE
		 			 
					EXTEND_ROUTE 2147.7717 1597.5520 994.5676 
					EXTEND_ROUTE 2144.1267 1597.7916 994.5676  
					EXTEND_ROUTE 2144.3777 1603.6888 992.5684  
					EXTEND_ROUTE 2143.9868 1609.4673 992.6882
					EXTEND_ROUTE 2143.7219 1618.0503 992.6882

					TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
				
					FLUSH_ROUTE
									
					TASK_TOGGLE_DUCK -1 TRUE

				CLOSE_SEQUENCE_TASK h9_run_into_safe
								
				// ----------------------------------------------------------------------------------------------

				IF NOT IS_CHAR_DEAD h9_second_room[6]

					CLEAR_CHAR_TASKS h9_second_room[6]

					PERFORM_SEQUENCE_TASK h9_second_room[6] h9_run_into_safe

				ENDIF

				// ----------------------------------------------------------------------------------------------

				IF NOT IS_CHAR_DEAD h9_second_room[7]

					CLEAR_CHAR_TASKS h9_second_room[7]

					PERFORM_SEQUENCE_TASK h9_second_room[7] h9_run_into_safe

				ENDIF

				// ----------------------------------------------------------------------------------------------

				h9_given_ai = 2

			ENDIF

			REPEAT 8 v

				IF IS_CHAR_DEAD h9_second_room[v]
				AND NOT h9_seconds_inv[v] = 0

					IF DOES_BLIP_EXIST h9_seconds_inv[v]

						REMOVE_BLIP h9_seconds_inv[v]

						h9_seconds_inv[v] = 0

						MARK_CHAR_AS_NO_LONGER_NEEDED h9_second_room[v]

					ENDIF

				ENDIF

			ENDREPEAT 

		ENDIF

		IF h9_cash_dialog > 3
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2144.2446 1606.8209 992.6882 3.0 3.0 3.0 FALSE
		AND h9_never_again = 0

			// ----------------------------------------------------------------------------------------------

			CLEAR_SEQUENCE_TASK h9_run_into_safe

			OPEN_SEQUENCE_TASK h9_run_into_safe

				FLUSH_ROUTE
 			 
				EXTEND_ROUTE 2143.9272 1597.6738 994.5676 
				EXTEND_ROUTE 2144.1401 1603.1108 992.5684  
				EXTEND_ROUTE 2142.9067 1614.1505 992.6882  
				EXTEND_ROUTE 2143.1052 1621.5706 992.6882

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
			
				FLUSH_ROUTE
								
				TASK_TOGGLE_DUCK -1 TRUE

			CLOSE_SEQUENCE_TASK h9_run_into_safe
							
			// ----------------------------------------------------------------------------------------------

			IF NOT IS_CHAR_DEAD h9_second_room[4]

				CLEAR_CHAR_TASKS h9_second_room[4]

				PERFORM_SEQUENCE_TASK h9_second_room[4] h9_run_into_safe

			ENDIF

			// ----------------------------------------------------------------------------------------------

			IF NOT IS_CHAR_DEAD h9_second_room[5]

				CLEAR_CHAR_TASKS h9_second_room[5]

				PERFORM_SEQUENCE_TASK h9_second_room[5] h9_run_into_safe

			ENDIF

			// ----------------------------------------------------------------------------------------------

			CLEAR_SEQUENCE_TASK h9_run_into_safe

			OPEN_SEQUENCE_TASK h9_run_into_safe

				FLUSH_ROUTE
	 			 
				EXTEND_ROUTE 2147.7717 1597.5520 994.5676 
				EXTEND_ROUTE 2144.1267 1597.7916 994.5676  
				EXTEND_ROUTE 2144.3777 1603.6888 992.5684  
				EXTEND_ROUTE 2143.9868 1609.4673 992.6882
				EXTEND_ROUTE 2143.7219 1618.0503 992.6882

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
			
				FLUSH_ROUTE
								
				TASK_TOGGLE_DUCK -1 TRUE

			CLOSE_SEQUENCE_TASK h9_run_into_safe
							
			// ----------------------------------------------------------------------------------------------

			IF NOT IS_CHAR_DEAD h9_second_room[6]

				CLEAR_CHAR_TASKS h9_second_room[6]

				PERFORM_SEQUENCE_TASK h9_second_room[6] h9_run_into_safe

			ENDIF

			// ----------------------------------------------------------------------------------------------

			IF NOT IS_CHAR_DEAD h9_second_room[7]

				CLEAR_CHAR_TASKS h9_second_room[7]

				PERFORM_SEQUENCE_TASK h9_second_room[7] h9_run_into_safe

			ENDIF

			// ----------------------------------------------------------------------------------------------

			h9_given_ai = 2

			h9_never_again = 1

		ENDIF

		IF h9_sync = 2

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD h9_gang[v]

					h9_hlth[v] = h9_gang_health[v]				

					GET_CHAR_HEALTH h9_gang[v] h9_gang_health[v]

					IF NOT h9_hlth[v] = h9_gang_health[v]

						PRINT_NOW ( HM9_44 ) 4000 1 // ZERO : The team's getting shot, your supposed to be protecting them!

					ENDIF
							
				ENDIF
					
			ENDREPEAT

		ENDIF

		IF h9_sync = 2

			IF h9_cash_dialog = 4
			AND h9_all_dead = 0

				GOSUB h9_check_all_goons_dead

			ENDIF

		ENDIF

		//safe1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2146.3289 1597.4089 998.7676 4.0 4.0 4.0 FALSE
		AND h9_all_dead = 1
		
			REPEAT 8 v

				DELETE_CHAR h9_second_room[v] 

			ENDREPEAT

			// ----------------------------------------------------------------------------------------------

			//Enemy standing on stairs - behind kill
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2152.0149 1602.5098 1000.9710 h9_second_room[0]

			SET_CHAR_AREA_VISIBLE h9_second_room[0] 1

			SET_CHAR_HEADING h9_second_room[0] 270.0812 

			TASK_STAY_IN_SAME_PLACE h9_second_room[0] TRUE

			// ----------------------------------------------------------------------------------------------

			//Enemy peeking around corner
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2160.9849 1616.6895 998.9657 h9_second_room[1]

			SET_CHAR_AREA_VISIBLE h9_second_room[1] 1

			SET_CHAR_HEADING h9_second_room[1] 178.5078 

			PERFORM_SEQUENCE_TASK h9_second_room[1] h9_peekleft
				
			// ----------------------------------------------------------------------------------------------

			//Enemy crouching at bin
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2181.3201 1625.3347 998.9714 h9_second_room[2]

			SET_CHAR_AREA_VISIBLE h9_second_room[2] 1

			SET_CHAR_HEADING h9_second_room[2] 117.5403 
			
			TASK_STAY_IN_SAME_PLACE h9_second_room[2] TRUE

			TASK_TOGGLE_DUCK h9_second_room[2] TRUE
							
			// ----------------------------------------------------------------------------------------------

			//Enemies standing still
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2183.6533 1623.6595 998.9743 h9_second_room[3]

			SET_CHAR_AREA_VISIBLE h9_second_room[3] 1

			SET_CHAR_HEADING h9_second_room[3] 98.1814 
			
			TASK_STAY_IN_SAME_PLACE h9_second_room[3] TRUE
			  		
			// ----------------------------------------------------------------------------------------------

			//Enemies standing still
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2182.4937 1611.8462 998.9766 h9_second_room[4]

			SET_CHAR_AREA_VISIBLE h9_second_room[4] 1

			SET_CHAR_HEADING h9_second_room[4] 57.0007 
			
			TASK_STAY_IN_SAME_PLACE h9_second_room[4] TRUE
			  		
			// ----------------------------------------------------------------------------------------------

			//Enemy free-roam
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2191.2781 1619.0514 998.9766 h9_second_room[5]

			SET_CHAR_AREA_VISIBLE h9_second_room[5] 1

			SET_CHAR_HEADING h9_second_room[5] 85.6714 

			// ----------------------------------------------------------------------------------------------

			//Enemy Peek - Low acc
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2222.6541 1616.0117 998.9688 h9_second_room[6]

			SET_CHAR_AREA_VISIBLE h9_second_room[6] 1

			SET_CHAR_HEADING h9_second_room[6] 97.3112 

			PERFORM_SEQUENCE_TASK h9_second_room[6] h9_peekleft

			// ----------------------------------------------------------------------------------------------

			//Enemy Crouch - Low acc
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 2210.1057 1620.3871 998.9766 h9_second_room[7]

			SET_CHAR_AREA_VISIBLE h9_second_room[7] 1

			SET_CHAR_HEADING h9_second_room[7] 90.3344

			TASK_TOGGLE_DUCK h9_second_room[7] TRUE

			TASK_STAY_IN_SAME_PLACE h9_second_room[7] TRUE
							
			// ----------------------------------------------------------------------------------------------

			//Enemy Crouching
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 2224.5779 1619.1136 998.9679 h9_second_room[8]

			SET_CHAR_AREA_VISIBLE h9_second_room[8] 1

			SET_CHAR_HEADING h9_second_room[8] 86.7328 
			
			TASK_TOGGLE_DUCK h9_second_room[8] TRUE

			TASK_STAY_IN_SAME_PLACE h9_second_room[8] TRUE
											 
			// ----------------------------------------------------------------------------------------------

			REPEAT 9 v

				SET_CHAR_MONEY h9_second_room[v] 0 

				ADD_BLIP_FOR_CHAR h9_second_room[v] h9_seconds_inv[v]
								
				SET_BLIP_ENTRY_EXIT h9_seconds_inv[v] 2196.6353 1676.8411 10.0
											
				CHANGE_BLIP_DISPLAY h9_seconds_inv[v] BLIP_ONLY
		
				SET_CHAR_HEALTH h9_second_room[v] 200
						
				SET_CHAR_MAX_HEALTH h9_second_room[v] 200

			   	GIVE_WEAPON_TO_CHAR h9_second_room[v] WEAPONTYPE_MP5 30000

			  	SET_CURRENT_CHAR_WEAPON h9_second_room[v] WEAPONTYPE_MP5

				SET_CHAR_SHOOT_RATE h9_second_room[v] 30

				SET_CHAR_ACCURACY h9_second_room[v] 20

				SET_CHAR_DECISION_MAKER h9_second_room[v] h9_decision_tough

			ENDREPEAT

			h9_mission_status = 15

		ENDIF

	ENDIF
 
	// ****************************************************************************************************
	// *																								  *
	// *                                       TEAM RUNS TO VAN	   									  	  *
	// *																								  *
	// ****************************************************************************************************
		
	IF h9_mission_status = 15

		REPEAT 9 v

			IF IS_CHAR_DEAD h9_second_room[v]
			AND NOT h9_seconds_inv[v] = 0

				IF DOES_BLIP_EXIST h9_seconds_inv[v]

					REMOVE_BLIP h9_seconds_inv[v]

					h9_seconds_inv[v] = 0

					MARK_CHAR_AS_NO_LONGER_NEEDED h9_second_room[v]

				ENDIF

			ENDIF

		ENDREPEAT 

		IF TIMERB > 7000
		AND h9_run_van_conv = 1

			$h9_print = &HE8_TA	// Ok team, just how we practiced, two by two.
			h9_audio = SOUND_HE8_TA
			GOSUB h9_load_sample

			h9_run_van_conv = 2

		ENDIF

		IF h9_playing = 2
		AND h9_run_van_conv = 2

			$h9_print = &HE8_TB	// Ow, fuck!
			h9_audio = SOUND_HE8_TB
			GOSUB h9_load_sample

			h9_run_van_conv = 3

		ENDIF

		IF h9_playing = 2
		AND h9_run_van_conv = 3

			$h9_print = &HE8_TC	// Scratch that!  Everybody follow Carl!
			h9_audio = SOUND_HE8_TC
			GOSUB h9_load_sample

			h9_run_van_conv = 0

		ENDIF

		// ****************************************************************************************************
		// *																								  *
		// *							   		  First super node    										  *
		// *																								  *
		// ****************************************************************************************************
		
		IF IS_CHAR_DEAD h9_second_room[0]
		AND h9_first_node = 0

			CLEAR_SEQUENCE_TASK h9_sequence_task

			OPEN_SEQUENCE_TASK h9_sequence_task

			//	TASK_TOGGLE_DUCK -1 TRUE

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2157.6289 1602.6041 998.9766 PEDMOVE_RUN -1
				
		   //	TASK_TOGGLE_DUCK -1 FALSE
								
			CLOSE_SEQUENCE_TASK h9_sequence_task

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD h9_gang[v] 

					PERFORM_SEQUENCE_TASK h9_gang[v] h9_sequence_task					

				ENDIF
	
			ENDREPEAT
				
			CLEAR_SEQUENCE_TASK h9_sequence_task 

			h9_first_node = 1

		ENDIF
											   
		
		IF IS_CHAR_DEAD h9_second_room[1]
		AND h9_first_node = 1


			OPEN_SEQUENCE_TASK h9_sequence_task 
								
			//	TASK_TOGGLE_DUCK -1 TRUE

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2165.4233 1618.3859 998.9747 PEDMOVE_RUN -1
								
			//	TASK_TOGGLE_DUCK -1 FALSE
								
			CLOSE_SEQUENCE_TASK h9_sequence_task

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD h9_gang[v] 

					PERFORM_SEQUENCE_TASK h9_gang[v] h9_sequence_task						

				ENDIF
	
			ENDREPEAT 
							
			CLEAR_SEQUENCE_TASK h9_sequence_task 

			h9_first_node = 2

		ENDIF
 	
		IF IS_CHAR_DEAD h9_second_room[2]
		AND IS_CHAR_DEAD h9_second_room[3]
		AND IS_CHAR_DEAD h9_second_room[4]
		AND IS_CHAR_DEAD h9_second_room[5]
		AND h9_first_node = 2

			OPEN_SEQUENCE_TASK h9_sequence_task
			 
		   		FLUSH_ROUTE 

				EXTEND_ROUTE 2169.0452 1618.7301 998.9766
				EXTEND_ROUTE 2171.0967 1618.6219 998.9766 
				EXTEND_ROUTE 2172.7483 1621.9347 998.9772 
				EXTEND_ROUTE 2179.5940 1623.4889 998.9745 
				EXTEND_ROUTE 2184.2002 1623.9084 998.9739 
				EXTEND_ROUTE 2187.7639 1622.7878 998.9758 
				EXTEND_ROUTE 2190.1296 1618.6642 998.9766

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

				FLUSH_ROUTE	 
								
			CLOSE_SEQUENCE_TASK h9_sequence_task

			IF NOT IS_CHAR_DEAD h9_gang[0] 

				PERFORM_SEQUENCE_TASK h9_gang[0] h9_sequence_task						

			ENDIF

			IF NOT IS_CHAR_DEAD h9_gang[1] 

				PERFORM_SEQUENCE_TASK h9_gang[1] h9_sequence_task						

			ENDIF
										
			CLEAR_SEQUENCE_TASK h9_sequence_task 

			OPEN_SEQUENCE_TASK h9_sequence_task
			 
		   		FLUSH_ROUTE 

				EXTEND_ROUTE 2169.0188 1618.7637 998.9766  
				EXTEND_ROUTE 2171.7104 1618.2516 998.9766  
				EXTEND_ROUTE 2174.5386 1615.0054 998.9766  
				EXTEND_ROUTE 2180.3789 1614.2458 998.9766  
				EXTEND_ROUTE 2185.4451 1614.7389 998.9766  
				EXTEND_ROUTE 2190.1296 1618.6642 998.9766

				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE

				FLUSH_ROUTE	 
								
			CLOSE_SEQUENCE_TASK h9_sequence_task
			
			IF NOT IS_CHAR_DEAD h9_gang[2] 

				PERFORM_SEQUENCE_TASK h9_gang[2] h9_sequence_task						
											   
			ENDIF

			IF NOT IS_CHAR_DEAD h9_gang[3] 

				PERFORM_SEQUENCE_TASK h9_gang[3] h9_sequence_task						

			ENDIF
				
			CLEAR_SEQUENCE_TASK h9_sequence_task 

			h9_first_node = 3

		ENDIF

		IF h9_first_node = 3

			REQUEST_MODEL COPBIKE
			REQUEST_MODEL lapdm1

			LOAD_SPECIAL_CHARACTER 1 ZERO

		    WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
			OR NOT HAS_MODEL_LOADED COPBIKE
			OR NOT HAS_MODEL_LOADED lapdm1
				WAIT 0
			ENDWHILE

			REQUEST_CAR_RECORDING 494

			LOAD_SPECIAL_CHARACTER 1 ZERO

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 494

				WAIT 0
					
			ENDWHILE 

			CLEAR_AREA 2220.3396 1582.0681 998.9764 10.0 TRUE

			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2220.3396 1582.0681 998.9764 h9_zero

			SET_CHAR_AREA_VISIBLE h9_zero 1
		
			SET_CHAR_HEADING h9_zero 350.4443

			SET_CHAR_NEVER_TARGETTED h9_zero TRUE

			// Cop Bikes 

			CREATE_CAR COPBIKE 2223.6995 1582.7799 998.0000 h9_bike_a

			SET_VEHICLE_AREA_VISIBLE h9_bike_a 1

			SET_CAR_HEADING	h9_bike_a 175.8434
			
 			LOCK_CAR_DOORS h9_bike_a CARLOCK_LOCKOUT_PLAYER_ONLY

			FREEZE_CAR_POSITION h9_bike_a TRUE

			CREATE_CAR COPBIKE 2217.5493 1583.6671 998.0000 h9_bike_b

			SET_VEHICLE_AREA_VISIBLE h9_bike_b 1

			SET_CAR_HEADING	h9_bike_b 175.3717

 			LOCK_CAR_DOORS h9_bike_b CARLOCK_LOCKOUT_PLAYER_ONLY

			FREEZE_CAR_POSITION h9_bike_b TRUE

			h9_first_node = 4

		ENDIF

		//safe2
		IF IS_CHAR_DEAD h9_second_room[6]
		AND IS_CHAR_DEAD h9_second_room[7]
		AND IS_CHAR_DEAD h9_second_room[8]
		AND h9_first_node = 4

			OPEN_SEQUENCE_TASK h9_sequence_task

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2222.9954 1618.7257 998.9672 PEDMOVE_RUN -1

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2220.9082 1585.6951 998.9708 PEDMOVE_RUN -1
								
			CLOSE_SEQUENCE_TASK h9_sequence_task

			REPEAT 4 v

				IF NOT IS_CHAR_DEAD h9_gang[v] 

					PERFORM_SEQUENCE_TASK h9_gang[v] h9_sequence_task						

				ENDIF
	
			ENDREPEAT 

			CLEAR_SEQUENCE_TASK h9_sequence_task

			h9_first_node = 5

		ENDIF


		IF h9_first_node >= 4

			IF IS_CHAR_DEAD h9_zero

				GOSUB h9_fix_for_failed
				
				PRINT_NOW ( HM9_63 ) 4000 1 // ~r~Zero has been killed

				GOTO mission_heist9_failed

			ENDIF

			IF IS_CAR_DEAD h9_bike_a

				GOSUB h9_fix_for_failed
				
				PRINT_NOW ( HM9_60 ) 4000 1 // ~r~You destroyed the police bike!

				GOTO mission_heist9_failed

			ENDIF

			IF IS_CAR_DEAD h9_bike_b

				GOSUB h9_fix_for_failed
				
				PRINT_NOW ( HM9_60 ) 4000 1 // ~r~You destroyed the police bike!

				GOTO mission_heist9_failed

			ENDIF

		ENDIF

		REPEAT 4 v

			IF NOT IS_CHAR_DEAD h9_gang[v]

				h9_hlth[v] = h9_gang_health[v]				

				GET_CHAR_HEALTH h9_gang[v] h9_gang_health[v]

				IF NOT h9_hlth[v] = h9_gang_health[v]

					PRINT_NOW ( HM9_44 ) 5000 1 // ZERO : The team's getting shot, your supposed to be protecting them!

				ENDIF
						
			ENDIF
				
		ENDREPEAT

		REPEAT 4 v

			IF NOT IS_CHAR_DEAD h9_gang[v]

				IF LOCATE_CHAR_ANY_MEANS_3D h9_gang[v] 2220.2048 1585.4218 998.9717 5.0 5.0 5.0 FALSE 
				AND h9_going_to_van[v] = 0

					h9_van_count ++

					h9_going_to_van[v] = 1

				ENDIF

			ENDIF

		ENDREPEAT

		IF NOT h9_first_node = 5

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2220.2048 1585.4218 998.9717 10.0 10.0 10.0 FALSE 

				PRINT_NOW ( HM9_1F ) 4000 1 // ~s~Go back and kill any remaining ~r~security~s~.

			ENDIF

		ENDIF

		IF h9_first_node = 5
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2220.2048 1585.4218 998.9717 10.0 10.0 10.0 FALSE  

			vein_child:

			CLEAR_PRINTS

			GOSUB h9_fade_out
				
			IF DOES_BLIP_EXIST h9_roller_door_blip

				REMOVE_BLIP h9_roller_door_blip

			ENDIF

			IF NOT IS_CHAR_DEAD h9_gang[0] 

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[0]

				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[0] 2224.0417 1589.3666 998.9670

				SET_CHAR_HEADING h9_gang[0] 165.2519 

			ENDIF

			IF NOT IS_CHAR_DEAD h9_gang[1] 

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[1]

				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[1] 2222.8931 1589.5830 998.9684

				SET_CHAR_HEADING h9_gang[1] 173.9364 

			ENDIF

			IF NOT IS_CHAR_DEAD h9_gang[2] 

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[2]

				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[2] 2221.8657 1589.2455 998.9696

				SET_CHAR_HEADING h9_gang[2] 177.0563 

			ENDIF

			IF NOT IS_CHAR_DEAD h9_gang[3] 

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[3]

				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[3] 2220.7229 1589.2239 998.9710

				SET_CHAR_HEADING h9_gang[3] 171.7417 

			ENDIF

			IF NOT IS_CHAR_DEAD h9_zero 

				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_zero 2220.3396 1582.0681 998.9764

				SET_CHAR_HEADING h9_zero 350.4443 

			ENDIF

			IF NOT IS_CHAR_DEAD scplayer 

				IF IS_CHAR_IN_ANY_CAR scplayer

					WARP_CHAR_FROM_CAR_TO_COORD scplayer 2225.5405 1586.8341 998.9651  

				ELSE

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2225.5405 1586.8341 998.9651  

				ENDIF

				SET_CHAR_HEADING scplayer 124.4066 

			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_a

				FREEZE_CAR_POSITION h9_bike_a FALSE 

			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_b

				FREEZE_CAR_POSITION h9_bike_b FALSE 

			ENDIF

			CLEAR_ONSCREEN_TIMER h9_safe_timer
				
			GOSUB h9_set_camera

			SET_FIXED_CAMERA_POSITION 2216.7207 1579.8098 1000.8326 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2217.3821 1580.5382 1000.6542 JUMP_CUT

			GOSUB h9_fade_in

			IF NOT IS_CHAR_DEAD h9_zero
				
				CLEAR_CHAR_TASKS h9_zero 

				TASK_PLAY_ANIM h9_zero IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1  
	
			ENDIF

			PRINT_NOW ( HE8_UA ) 4000 1 // I unloaded the police bikes!
			
			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_UA // I unloaded the police bikes!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_enter_van
				ENDIF
			ENDWHILE

			PRINT_NOW ( HE8_UB ) 4000 1 // Everybody in! You two, change into your police uniforms!
			
			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_UB // Everybody in! You two, change into your police uniforms!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_enter_van
				ENDIF
			ENDWHILE

	 	   	DELETE_CHAR h9_zero
	 				
			DELETE_CHAR h9_gang[2]
			  
			DELETE_CHAR h9_gang[3]

			SET_FIXED_CAMERA_POSITION 2216.9141 1571.6224 999.7229 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2217.2698 1572.5570 999.7267 JUMP_CUT

			CREATE_CHAR PEDTYPE_MISSION2 lapdm1 2220.7996 1581.1633 998.9764 h9_biker_a

			SET_CHAR_HEADING h9_biker_a 196.0524  
			SET_CHAR_AREA_VISIBLE h9_biker_a 1

			CREATE_CHAR PEDTYPE_MISSION2 lapdm1 2220.0188 1581.1787 998.9772 h9_biker_b

			SET_CHAR_HEADING h9_biker_b 74.4668
			SET_CHAR_AREA_VISIBLE h9_biker_b 1

			IF NOT IS_CAR_DEAD h9_getaway_car
			AND NOT IS_CHAR_DEAD h9_gang[0]
				TASK_ENTER_CAR_AS_DRIVER h9_gang[0] h9_getaway_car 8000
			ENDIF

			IF NOT IS_CAR_DEAD h9_getaway_car
			AND NOT IS_CHAR_DEAD h9_gang[1]
				TASK_ENTER_CAR_AS_PASSENGER h9_gang[1] h9_getaway_car 8000 0
			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_a
			AND NOT IS_CHAR_DEAD h9_biker_a
				TASK_ENTER_CAR_AS_DRIVER h9_biker_a h9_bike_a 8000
			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_b
			AND NOT IS_CHAR_DEAD h9_biker_b
				TASK_ENTER_CAR_AS_DRIVER h9_biker_b h9_bike_b 8000
			ENDIF

			TIMERB = 0
			WHILE TIMERB < 8000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_enter_van
				ENDIF
			ENDWHILE

			SET_FIXED_CAMERA_POSITION 2223.8503 1572.0138 1001.6227 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 2223.3713 1572.8435 1001.3364 JUMP_CUT

			IF HAS_CAR_RECORDING_BEEN_LOADED 494
			AND NOT IS_CAR_DEAD h9_getaway_car
				START_PLAYBACK_RECORDED_CAR h9_getaway_car 494
		   	ENDIF

			TIMERB = 0
			WHILE TIMERB < 500
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_enter_van
				ENDIF
			ENDWHILE

			IF NOT IS_CAR_DEAD h9_bike_a
			AND NOT IS_CHAR_DEAD h9_biker_a
				SWITCH_CAR_SIREN h9_bike_a ON
				TASK_CAR_DRIVE_TO_COORD h9_biker_a h9_bike_a 2223.4553 1564.8922 1000.8730 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_b
			AND NOT IS_CHAR_DEAD h9_biker_b
				SWITCH_CAR_SIREN h9_bike_b ON
				TASK_CAR_DRIVE_TO_COORD h9_biker_b h9_bike_b 2217.3186 1566.5996 1000.3005 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
			ENDIF

			TIMERB = 0
			WHILE TIMERB < 2000
				WAIT 0
				IF IS_BUTTON_PRESSED PAD1 CROSS
				OR IS_BUTTON_PRESSED PAD1 CIRCLE
					GOTO h9_skip_enter_van
				ENDIF
			ENDWHILE

			h9_skip_enter_van:

			REPEAT 4 v
				
				IF DOES_BLIP_EXIST h9_gang_blip[v]

					REMOVE_BLIP h9_gang_blip[v]

				 	DELETE_CHAR h9_gang[v]

				ENDIF

			ENDREPEAT

			GOSUB h9_restore_camera

			IF NOT IS_CHAR_DEAD h9_zero

			   	DELETE_CHAR h9_zero

			ENDIF

			IF NOT IS_CHAR_DEAD h9_biker_a

			   	DELETE_CHAR h9_biker_a

			ENDIF

			IF NOT IS_CHAR_DEAD h9_biker_b

			   	DELETE_CHAR h9_biker_b

			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_a

			   	DELETE_CAR h9_bike_a

			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_b

			   	DELETE_CAR h9_bike_b

			ENDIF

			IF NOT IS_CAR_DEAD h9_getaway_car

			   	DELETE_CAR h9_getaway_car

			ENDIF

			MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA

			MARK_MODEL_AS_NO_LONGER_NEEDED lapdm1

			MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE

			MARK_MODEL_AS_NO_LONGER_NEEDED SECURICA

			UNLOAD_SPECIAL_CHARACTER 3

			UNLOAD_SPECIAL_CHARACTER 2

			UNLOAD_SPECIAL_CHARACTER 1

			h9_van_not_needed_anymore = 1

			h9_mission_status = 16

			TIMERA = 0

		ENDIF

	ENDIF

	// *************************************************************************************************
	// *																							   *
	// *								    GOING TO THE ROOF										   *
	// *                                  															   *
	// *************************************************************************************************

	//safe4
	IF h9_mission_status = 16
		
		REPEAT 9 v

			DELETE_CHAR h9_second_room[v] 

		ENDREPEAT
 
		MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
		MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1

		REQUEST_MODEL VMAFF3
		REQUEST_MODEL VMAFF4

		WHILE NOT HAS_MODEL_LOADED VMAFF3
		OR NOT HAS_MODEL_LOADED VMAFF4
			WAIT 0
		ENDWHILE

		// ----------------------------------------------------------------------------------------------

		//Enemy Peeker
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 2221.3396 1616.9712 998.9719 h9_second_room[0]

		SET_CHAR_AREA_VISIBLE h9_second_room[0] 1

		SET_CHAR_HEADING h9_second_room[0] 173.3951 

		TASK_STAY_IN_SAME_PLACE h9_second_room[0] TRUE

		PERFORM_SEQUENCE_TASK h9_second_room[0] h9_peekright

 		// ----------------------------------------------------------------------------------------------

		//Enemy roll to left
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 2204.9114 1615.9915 998.9820 h9_second_room[1]

		SET_CHAR_AREA_VISIBLE h9_second_room[1] 1

		SET_CHAR_HEADING h9_second_room[1] 266.9222 

		TASK_STAY_IN_SAME_PLACE h9_second_room[1] TRUE

 		// ----------------------------------------------------------------------------------------------

		//Runs towards player
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 2196.6636 1618.7592 998.9766 h9_second_room[2]

		SET_CHAR_AREA_VISIBLE h9_second_room[2] 1

		SET_CHAR_HEADING h9_second_room[2] 266.9185 

 		// ----------------------------------------------------------------------------------------------

		//Enemies free-roam
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 2172.9958 1616.3293 998.9766 h9_second_room[3]

		SET_CHAR_AREA_VISIBLE h9_second_room[3] 1

		SET_CHAR_HEADING h9_second_room[3] 263.6073

 		// ----------------------------------------------------------------------------------------------

		//Enemies free-roam
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 2173.0029 1622.6256 998.9761 h9_second_room[4]

		SET_CHAR_AREA_VISIBLE h9_second_room[4] 1

		SET_CHAR_HEADING h9_second_room[4] 261.2549

 		// ----------------------------------------------------------------------------------------------

		//Enemy crouch - roll to left
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 2179.7273 1611.8108 998.9766 h9_second_room[5]

		SET_CHAR_AREA_VISIBLE h9_second_room[5] 1

		SET_CHAR_HEADING h9_second_room[5] 263.1769

		TASK_TOGGLE_DUCK h9_second_room[5] TRUE

		TASK_STAY_IN_SAME_PLACE h9_second_room[5] TRUE

 		// ----------------------------------------------------------------------------------------------

		//Enemy crouch - roll to left
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 2181.5430 1622.6567 998.9760 h9_second_room[6]

		SET_CHAR_AREA_VISIBLE h9_second_room[6] 1

		SET_CHAR_HEADING h9_second_room[6] 263.6053

		TASK_TOGGLE_DUCK h9_second_room[6] TRUE

		TASK_STAY_IN_SAME_PLACE h9_second_room[6] TRUE

  		// ----------------------------------------------------------------------------------------------

		//Enemy standing top of stairs
		CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 2151.5779 1602.3218 1000.9707 h9_second_room[7]

		SET_CHAR_AREA_VISIBLE h9_second_room[7] 1

		SET_CHAR_HEADING h9_second_room[7] 264.2166 

		TASK_STAY_IN_SAME_PLACE h9_second_room[7] TRUE

		// ----------------------------------------------------------------------------------------------

		REPEAT 8 v

			h9_seconds_inv[v] = 1

			SET_CHAR_MONEY h9_second_room[v] 0 
								
			SET_CHAR_HEALTH h9_second_room[v] 200
					
			SET_CHAR_MAX_HEALTH h9_second_room[v] 200

			IF v = 3
			OR v = 4
			OR v = 7

				SET_CHAR_WEAPON_SKILL h9_second_room[v] WEAPONSKILL_PRO

				GIVE_WEAPON_TO_CHAR h9_second_room[v] WEAPONTYPE_PISTOL 9999

			ELSE

			   	GIVE_WEAPON_TO_CHAR h9_second_room[v] WEAPONTYPE_MP5 9999

			  	SET_CURRENT_CHAR_WEAPON h9_second_room[v] WEAPONTYPE_MP5
			
			ENDIF		

			SET_CHAR_SHOOT_RATE h9_second_room[v] 30

			SET_CHAR_ACCURACY h9_second_room[v] 30

			SET_CHAR_DECISION_MAKER h9_second_room[v] h9_decision_tough

		ENDREPEAT

		// ----------------------------------------------------------------------------------------------

		PRINT_NOW ( HM9_10 ) 7000 1 // ~s~Get to the ~y~service elevator~s~, remember you are the decoy.

		debbie_death:

		ADD_BLIP_FOR_COORD 2156.1880 1597.9803 998.9766 h9_elevator

		SET_BLIP_ENTRY_EXIT h9_elevator 2196.6353 1676.8411 10.0
 
 		//SET_OBJECT_HEADING h9_door_a 90.0000

		h9_mission_status = 17

		h9_lift_conv = 1

		TIMERA = 0

	ENDIF

	IF h9_mission_status = 17

		REPEAT 8 v

			IF IS_CHAR_DEAD h9_second_room[v]
			AND NOT h9_seconds_inv[v] = 0

				h9_seconds_inv[v] = 0

				MARK_CHAR_AS_NO_LONGER_NEEDED h9_second_room[v]

			ENDIF

		ENDREPEAT 

		IF NOT IS_CHAR_DEAD h9_second_room[1]
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2208.0205 1619.1246 998.9827 5.0 5.0 5.0 FALSE

			IF h9_seconds_inv[1] = 1

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_second_room[1]

				OPEN_SEQUENCE_TASK h9_sequence_task

					TASK_TOGGLE_DUCK -1 TRUE	

					TASK_WEAPON_ROLL -1 FALSE

					TASK_TOGGLE_DUCK -1 TRUE									

				CLOSE_SEQUENCE_TASK h9_sequence_task

				PERFORM_SEQUENCE_TASK h9_second_room[1] h9_sequence_task						

				CLEAR_SEQUENCE_TASK h9_sequence_task

				h9_seconds_inv[1] = 2

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD h9_second_room[2]
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2224.0203 1618.9269 998.9655 5.0 5.0 5.0 FALSE

			IF h9_seconds_inv[2] = 1

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_second_room[2]

				FLUSH_ROUTE

				EXTEND_ROUTE 2224.0203 1618.9269 998.9655 

				TASK_FOLLOW_POINT_ROUTE h9_second_room[2] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

				FLUSH_ROUTE	

				h9_seconds_inv[2] = 2

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD h9_second_room[5]
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2183.9070 1613.5718 998.9766 5.0 5.0 5.0 FALSE

			IF h9_seconds_inv[5] = 1

				TASK_TOGGLE_DUCK h9_second_room[5] FALSE

				h9_seconds_inv[5] = 2

				IF NOT IS_CHAR_DEAD h9_second_room[3]

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_second_room[3]

					FLUSH_ROUTE

					EXTEND_ROUTE 2183.9070 1613.5718 998.9766 

					TASK_TOGGLE_DUCK h9_second_room[3] TRUE

					TASK_FOLLOW_POINT_ROUTE h9_second_room[3] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE	

				ENDIF

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD h9_second_room[6]
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2185.5793 1624.4309 998.9730 5.0 5.0 5.0 FALSE

			IF h9_seconds_inv[6] = 1

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_second_room[6]

				OPEN_SEQUENCE_TASK h9_sequence_task

					TASK_TOGGLE_DUCK -1 TRUE	

					TASK_WEAPON_ROLL -1 FALSE

					TASK_TOGGLE_DUCK -1 TRUE									

				CLOSE_SEQUENCE_TASK h9_sequence_task

				PERFORM_SEQUENCE_TASK h9_second_room[6] h9_sequence_task						

				CLEAR_SEQUENCE_TASK h9_sequence_task

				h9_seconds_inv[6] = 2

				IF NOT IS_CHAR_DEAD h9_second_room[4]

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_second_room[4]

					FLUSH_ROUTE

					EXTEND_ROUTE 2185.5793 1624.4309 998.9730 

					TASK_TOGGLE_DUCK h9_second_room[4] TRUE

					TASK_FOLLOW_POINT_ROUTE h9_second_room[4] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

					FLUSH_ROUTE	
					
				ENDIF

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD h9_second_room[7]
		AND LOCATE_CHAR_ANY_MEANS_3D scplayer 2160.2791 1618.6678 998.9676 5.0 5.0 5.0 FALSE

			IF h9_seconds_inv[7] = 1

				CLEAR_CHAR_TASKS_IMMEDIATELY h9_second_room[7]

				FLUSH_ROUTE

				EXTEND_ROUTE 2157.5803 1601.9437 998.9766 

				EXTEND_ROUTE 2157.8323 1616.0239 998.9669 

				TASK_FOLLOW_POINT_ROUTE h9_second_room[7] PEDMOVE_RUN FOLLOW_ROUTE_ONCE

				FLUSH_ROUTE	

				h9_seconds_inv[7] = 2

			ENDIF

		ENDIF

		IF TIMERA > 7000
		AND h9_lift_conv = 1

			$h9_print = &HE8_VA	// Ok, CJ, you’re on your own now!
			h9_audio = SOUND_HE8_VA
			GOSUB h9_load_sample

			h9_lift_conv = 2

		ENDIF
		IF h9_playing = 2
		AND h9_lift_conv = 2

			$h9_print = &HE8_VB	// Time to lead these motherfuckers a merry dance!
			h9_audio = SOUND_HE8_VB
			GOSUB h9_load_sample

			h9_lift_conv = 3

		ENDIF
		IF h9_playing = 2
		AND h9_lift_conv = 3

			$h9_print = &HE8_VC	// You want talked to the roof?
			h9_audio = SOUND_HE8_VC
			GOSUB h9_load_sample

			h9_lift_conv = 4

		ENDIF
		IF h9_playing = 2
		AND h9_lift_conv = 4

			$h9_print = &HE8_VD	// Anything that helps, dude!
			h9_audio = SOUND_HE8_VD
			GOSUB h9_load_sample

			h9_lift_conv = 101

		ENDIF

		// ***********************************************************************************************
		// *																							 *
		// *									Lights turn back on										 *
		// *																							 *
		// ***********************************************************************************************

		IF h9_lift_conv = 101
		AND h9_trigger_lights = 0

		  	SET_DARKNESS_EFFECT FALSE 0

		   	SWITCH_ENTRY_EXIT JUMP1 TRUE

			SWITCH_ENTRY_EXIT JUMP2 FALSE

			IF NOT IS_CHAR_DEAD scplayer

				PLAYER_TAKE_OFF_GOGGLES player1 TRUE

			ENDIF

			SET_NIGHT_VISION FALSE

    		h9_trigger_lights = 1

		ENDIF

		// ***********************************************************************************************
		// *																							 *
		// *								Lights turn back on	dialog									 *
		// *																							 *
		// ***********************************************************************************************

		IF NOT h9_trigger_lights = 0
		AND NOT h9_trigger_lights = 7

			IF h9_playing = 2
			AND h9_trigger_lights = 1

				$h9_print = &HE8_WA	// What happened?
				h9_audio = SOUND_HE8_WA
				GOSUB h9_load_sample
		 
				h9_trigger_lights = 2

			ENDIF
			IF h9_playing = 2
			AND h9_trigger_lights = 2

				$h9_print = &HE8_WB	// Curse you, Berkley, curse you!
				h9_audio = SOUND_HE8_WB
				GOSUB h9_load_sample
		 
				h9_trigger_lights = 3

			ENDIF
			IF h9_playing = 2
			AND h9_trigger_lights = 3

				IF NOT IS_CHAR_DEAD scplayer

					START_CHAR_FACIAL_TALK scplayer 10000

				ENDIF

				$h9_print = &HE8_WC	// C’mon, dude, talk to me!
				h9_audio = SOUND_HE8_WC
				GOSUB h9_load_sample
				h9_trigger_lights = 4

			ENDIF
			IF h9_playing = 2
			AND h9_trigger_lights = 4

				IF NOT IS_CHAR_DEAD scplayer

					STOP_CHAR_FACIAL_TALK scplayer 

				ENDIF

				$h9_print = &HE8_WE	// Now head through the casino to the lifts on the far side!
				h9_audio = SOUND_HE8_WE
				GOSUB h9_load_sample
		 
				h9_trigger_lights = 5

			ENDIF
			IF h9_playing = 2
			AND h9_trigger_lights = 5
									 
				$h9_print = &HE8_WF	// Take the lift all the way to the roof!
				h9_audio = SOUND_HE8_WF
				GOSUB h9_load_sample
		 
				h9_trigger_lights = 7

			ENDIF

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2156.1880 1597.9803 998.9766 1.2 1.2 2.0 TRUE
		AND h9_elevator_message_1 = 0

			IF NOT IS_CHAR_DEAD scplayer

				SET_PLAYER_CONTROL player1 OFF

			ENDIF
			
			GOSUB h9_fade_out

			IF DOES_BLIP_EXIST h9_elevator

				REMOVE_BLIP h9_elevator

			ENDIF

			LOAD_SCENE 2266.5110 1647.5168 1083.2408

			IF NOT IS_CHAR_DEAD scplayer
			
				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2266.5110 1647.5168 1083.2408

				SET_CHAR_HEADING scplayer 268.9270  

				SET_CAMERA_BEHIND_PLAYER

				RESTORE_CAMERA_JUMPCUT

			ENDIF
						 
			LVAR_INT h9_roof_blip

			ADD_BLIP_FOR_COORD 2266.2253 1619.7657 1089.4453 h9_roof_blip
			SET_BLIP_ENTRY_EXIT h9_roof_blip 2196.6353 1676.8411 10.0

			GOSUB h9_clean_interior
			
 			CLEAR_MISSION_AUDIO 3
			
			LOAD_MISSION_AUDIO 3 SOUND_LIFT_PING

			WHILE NOT HAS_MISSION_AUDIO_LOADED 3
				WAIT 0
			ENDWHILE

			IF HAS_MISSION_AUDIO_LOADED 3

				PLAY_MISSION_AUDIO 3

			ENDIF

			GOSUB h9_fade_in

			IF NOT IS_CHAR_DEAD scplayer

				SET_PLAYER_CONTROL player1 ON

			ENDIF

			PRINT_NOW ( HM9_45 ) 4000 1 // ~s~Get onto the roof!

			h9_elevator_message_1 = 1

		ENDIF

	ENDIF

 	IF h9_mission_status = 17
	OR h9_mission_status = 18

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2266.5984 1647.3784 1083.2408 1.2 1.2 2.0 FALSE 
		AND h9_roof_trigger = 0
						
			REPEAT 8 v

				DELETE_CHAR h9_second_room[v] 

			ENDREPEAT

			REQUEST_MODEL gun_para

			WHILE NOT HAS_MODEL_LOADED gun_para
				WAIT 0
			ENDWHILE

			CREATE_PICKUP gun_para PICKUP_ONCE 2267.9888 1699.6678 101.4000 h9_para_pickup

			para_freefall_Vz = -6.0

			para_freefall_Vy = 35.0

			CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 2269.7322 1620.3453 1085.2500 h9_roof_guy

			SET_CHAR_AREA_VISIBLE h9_roof_guy 1

			SET_CHAR_HEADING h9_roof_guy 0.7663  

			GIVE_WEAPON_TO_CHAR h9_roof_guy WEAPONTYPE_MP5 30000

			SET_CURRENT_CHAR_WEAPON h9_roof_guy WEAPONTYPE_MP5 

			SET_CHAR_ACCURACY h9_roof_guy 80

			SET_CHAR_DECISION_MAKER h9_roof_guy h9_decision

			FLUSH_ROUTE

				EXTEND_ROUTE 2269.5769 1624.2461 1083.2578 

				EXTEND_ROUTE 2269.9858 1628.8032 1083.2451  

				EXTEND_ROUTE 2268.9478 1641.6399 1083.2344 

				TASK_FOLLOW_POINT_ROUTE h9_roof_guy PEDMOVE_RUN FOLLOW_ROUTE_ONCE

			FLUSH_ROUTE	

			SET_CHAR_MONEY h9_roof_guy 0
												  
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 2265.8320 1624.9385 1087.4485 h9_roof_guy_1

			SET_CHAR_AREA_VISIBLE h9_roof_guy_1 1

			SET_CHAR_HEADING h9_roof_guy_1 178.1353  

			GIVE_WEAPON_TO_CHAR h9_roof_guy_1 WEAPONTYPE_MP5 30000

			SET_CURRENT_CHAR_WEAPON h9_roof_guy_1 WEAPONTYPE_MP5 

			SET_CHAR_ACCURACY h9_roof_guy_1 80

			SET_CHAR_DECISION_MAKER h9_roof_guy_1 h9_decision

			TASK_TOGGLE_DUCK h9_roof_guy_1 TRUE

			TASK_STAY_IN_SAME_PLACE h9_roof_guy_1 TRUE

			SET_CHAR_MONEY h9_roof_guy_1 0

			MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
			MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
			MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
			MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4

			h9_roof_trigger = 1
			
		ENDIF

		//roof0
		IF h9_wanted_level = 0

		   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2269.0676 1624.6476 93.9220 10.0 10.0 10.0 FALSE
			AND h9_wanted_level = 0

				to_here:

				SET_RADAR_ZOOM 0

				SET_WANTED_MULTIPLIER 1.0

				DO_FADE 0 FADE_OUT

				SET_TIME_OF_DAY 00 00

				GOSUB h9_set_camera

				IF NOT IS_CHAR_DEAD scplayer

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2268.6658 1620.9098 93.9220
					  
					SET_CHAR_HEADING scplayer 270.0000

				ENDIF
					
				LOAD_SCENE 2269.8110 1625.4415 93.9220 

				LOAD_SCENE_IN_DIRECTION 2270.0952 1692.9395 101.2986 129.3904

				REQUEST_MODEL POLMAV

				WHILE NOT HAS_MODEL_LOADED POLMAV
					WAIT 0
				ENDWHILE

				// Creates forklift
				CREATE_CAR POLMAV 2295.6223 1839.8036 38.2713 h9_helicopter

				SET_CAR_HEADING h9_helicopter 191.5123

				REQUEST_MODEL SWAT
				REQUEST_CAR_RECORDING 484
					
				WHILE NOT HAS_MODEL_LOADED SWAT
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 484
					WAIT 0
				ENDWHILE

				//heli0
				LVAR_INT h9_chopper h9_pilot h9_spotlight h9_heli_man[4]

				CREATE_CAR POLMAV 2253.0676 1626.5624 87.9584 h9_chopper
				CREATE_CHAR_INSIDE_CAR h9_chopper PEDTYPE_MISSION4 SWAT h9_pilot
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE h9_pilot FALSE
				LOCK_CAR_DOORS h9_chopper CARLOCK_LOCKED
				SET_CHAR_CANT_BE_DRAGGED_OUT h9_pilot TRUE
				SET_HELI_BLADES_FULL_SPEED h9_chopper
				SET_CAR_FORWARD_SPEED h9_chopper 10.0

				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2253.0676 1626.5624 87.9584 h9_heli_man[0]
				SET_CHAR_DECISION_MAKER h9_heli_man[0] h9_decision_tough

				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2253.0676 1626.5624 87.9584 h9_heli_man[1]
				SET_CHAR_DECISION_MAKER h9_heli_man[1] h9_decision_tough

				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2253.0676 1626.5624 87.9584 h9_heli_man[2]
				SET_CHAR_DECISION_MAKER h9_heli_man[2] h9_decision_tough	
						 
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2253.0676 1626.5624 87.9584 h9_heli_man[3]
				SET_CHAR_DECISION_MAKER h9_heli_man[3] h9_decision_tough

				ATTACH_CHAR_TO_CAR h9_heli_man[0] h9_chopper 1.4 1.3 -0.1 FACING_RIGHT 190.0 WEAPONTYPE_MP5

				ATTACH_CHAR_TO_CAR h9_heli_man[1] h9_chopper 1.4 -0.8 -0.1 FACING_RIGHT 190.0 WEAPONTYPE_MP5

				ATTACH_CHAR_TO_CAR h9_heli_man[2] h9_chopper -1.4 1.3 -0.1 FACING_LEFT 190.0 WEAPONTYPE_MP5

				ATTACH_CHAR_TO_CAR h9_heli_man[3] h9_chopper -1.4 -0.8 -0.1 FACING_LEFT 190.0 WEAPONTYPE_MP5

				REPEAT 4 v

					SET_CHAR_MONEY h9_heli_man[v] 0

					SET_CHAR_HEALTH h9_heli_man[v] 150

					SET_CHAR_SHOOT_RATE h9_heli_man[v] 40

					SET_CHAR_ACCURACY h9_heli_man[v] 40

					TASK_STAY_IN_SAME_PLACE h9_heli_man[v] TRUE

				ENDREPEAT

			 	CREATE_SEARCHLIGHT_ON_VEHICLE h9_chopper 0.0 1.0 -0.5 2269.4558 1625.4415 93.9220 5.0 0.4 h9_spotlight

				POINT_SEARCHLIGHT_AT_CHAR h9_spotlight scplayer 10.0

				IF NOT IS_CAR_DEAD h9_chopper
					IF HAS_CAR_RECORDING_BEEN_LOADED 484

					  	START_PLAYBACK_RECORDED_CAR h9_chopper 484

						PAUSE_PLAYBACK_RECORDED_CAR h9_chopper

					ENDIF
				ENDIF

				// ----------------------------------------------------------------------------------------------

				//Cop at the chopper
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2293.6802 1837.0165 38.2713 h9_second_room[0]

				SET_CHAR_HEADING h9_second_room[0] 164.9221 

				TASK_STAND_STILL h9_second_room[0] TRUE

				// ----------------------------------------------------------------------------------------------

				//Cop at the chopper
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2298.4917 1836.9462 38.2713 h9_second_room[1]

				SET_CHAR_HEADING h9_second_room[1] 170.0207 

				TASK_STAND_STILL h9_second_room[1] TRUE

				TASK_TOGGLE_DUCK h9_second_room[1] TRUE

				// ----------------------------------------------------------------------------------------------

				REPEAT 2 v

					h9_seconds_inv[v] = 1
							
					SET_CHAR_HEALTH h9_second_room[v] 200
							
					SET_CHAR_MAX_HEALTH h9_second_room[v] 200

				   	GIVE_WEAPON_TO_CHAR h9_second_room[v] WEAPONTYPE_MP5 30000

				  	SET_CURRENT_CHAR_WEAPON h9_second_room[v] WEAPONTYPE_MP5

					SET_CHAR_SHOOT_RATE h9_second_room[v] 30

					SET_CHAR_ACCURACY h9_second_room[v] 20

					SET_CHAR_DECISION_MAKER h9_second_room[v] h9_decision_tough

				ENDREPEAT

				// ----------------------------------------------------------------------------------------------

				SWITCH_ENTRY_EXIT JUMP1 FALSE

				SWITCH_ENTRY_EXIT JUMP2 FALSE

				CLEAR_AREA 2270.0208 1623.4861 93.9220 20.0 TRUE

				IF DOES_BLIP_EXIST h9_elevator

					REMOVE_BLIP h9_elevator

				ENDIF

				IF DOES_BLIP_EXIST h9_roof_blip

					REMOVE_BLIP h9_roof_blip

				ENDIF

				LVAR_INT h9_para_blip

				ADD_BLIP_FOR_PICKUP h9_para_pickup h9_para_blip

				SET_CAR_DENSITY_MULTIPLIER 1.0

				SET_FIXED_CAMERA_POSITION 2305.3381 1721.0996 126.2864 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 2304.8130 1720.4073 125.7915 JUMP_CUT

				GOSUB h9_fade_in

				SET_INTERPOLATION_PARAMETERS 0.0 8000
				
				SET_FIXED_CAMERA_POSITION 2307.4092 1599.5317 118.4341 0.0 0.0 0.0 // High bike
				POINT_CAMERA_AT_POINT 2306.7485 1600.1638 118.0293 INTERPOLATION
												
				PRINT_NOW ( HM9_25 ) 8000 1 // ~s~Follow the rooftops and collect the ~g~parachute~s~. 

				WAIT 3000

				IF NOT IS_CAR_DEAD h9_chopper
					IF HAS_CAR_RECORDING_BEEN_LOADED 484

						UNPAUSE_PLAYBACK_RECORDED_CAR h9_chopper

					ENDIF
				ENDIF
				
				WAIT 5000

				GOSUB h9_restore_camera
				
				LVAR_INT h9_armour_1

				CREATE_PICKUP bodyarmour PICKUP_ONCE 2265.8247 1617.0693 94.5000 h9_armour_1

				IF NOT IS_CHAR_DEAD scplayer
					
					CLEAR_CHAR_TASKS scplayer

					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2269.1387 1622.5756 93.9220  

					SET_CHAR_HEADING scplayer 0.0000

					SET_CAMERA_BEHIND_PLAYER

				ENDIF

				h9_wanted_level = 1

				TIMERB = 0
			ENDIF

		ENDIF

		IF IS_CAR_DEAD h9_chopper

			IF DOES_SEARCHLIGHT_EXIST h9_spotlight 

				DELETE_SEARCHLIGHT h9_spotlight  	  

			ENDIF

		ENDIF

	    IF h9_wanted_level = 1
	    AND TIMERB > 10800
		AND NOT IS_CAR_DEAD h9_chopper

	    	IF IS_PLAYBACK_GOING_ON_FOR_CAR h9_chopper

				PAUSE_PLAYBACK_RECORDED_CAR h9_chopper

			ENDIF
				
			REPEAT 4 v

				IF NOT IS_CHAR_DEAD h9_heli_man[v]
					
					GET_CHAR_COORDINATES h9_heli_man[v] x y z

					DELETE_CHAR h9_heli_man[v]

					CREATE_SWAT_ROPE PEDTYPE_MISSION1 SWAT x y z h9_heli_man[v]

					SET_CHAR_SHOOT_RATE h9_heli_man[v] 70

					SET_CHAR_DECISION_MAKER h9_heli_man[v] h9_decision_tough

				   	GIVE_WEAPON_TO_CHAR h9_heli_man[v] WEAPONTYPE_MP5 30000

				  	SET_CURRENT_CHAR_WEAPON h9_heli_man[v] WEAPONTYPE_MP5

					TASK_STAY_IN_SAME_PLACE h9_heli_man[v] TRUE

				ENDIF

			ENDREPEAT
	
			OPEN_SEQUENCE_TASK h9_sequence_task

				TASK_TOGGLE_DUCK -1 TRUE

				TASK_STAY_IN_SAME_PLACE -1 TRUE

				TASK_ACHIEVE_HEADING -1 170.7766

				TASK_TOGGLE_DUCK -1 FALSE

				TASK_PAUSE -1 3000

				TASK_SHOOT_AT_CHAR -1 scplayer -1

			CLOSE_SEQUENCE_TASK h9_sequence_task

			IF NOT IS_CHAR_DEAD h9_heli_man[0]

				PERFORM_SEQUENCE_TASK h9_heli_man[0] h9_sequence_task 

			ENDIF

			CLEAR_SEQUENCE_TASK h9_sequence_task

			OPEN_SEQUENCE_TASK h9_sequence_task

				TASK_TOGGLE_DUCK -1 TRUE

				TASK_STAY_IN_SAME_PLACE -1 TRUE

				TASK_ACHIEVE_HEADING -1 170.7766

				TASK_PAUSE -1 3000

				TASK_SHOOT_AT_CHAR -1 scplayer -1

			CLOSE_SEQUENCE_TASK h9_sequence_task

			IF NOT IS_CHAR_DEAD h9_heli_man[1]

				PERFORM_SEQUENCE_TASK h9_heli_man[1] h9_sequence_task 

			ENDIF

			CLEAR_SEQUENCE_TASK h9_sequence_task

			OPEN_SEQUENCE_TASK h9_sequence_task

				TASK_TOGGLE_DUCK -1 TRUE

				TASK_STAY_IN_SAME_PLACE -1 TRUE

				TASK_ACHIEVE_HEADING -1 170.7766

				TASK_TOGGLE_DUCK -1 FALSE

				TASK_PAUSE -1 3000

				TASK_SHOOT_AT_CHAR -1 scplayer -1

			CLOSE_SEQUENCE_TASK h9_sequence_task
			
			IF NOT IS_CHAR_DEAD h9_heli_man[2]

				PERFORM_SEQUENCE_TASK h9_heli_man[2] h9_sequence_task 

			ENDIF

			CLEAR_SEQUENCE_TASK h9_sequence_task

			OPEN_SEQUENCE_TASK h9_sequence_task
			
				TASK_TOGGLE_DUCK -1 TRUE

				TASK_STAY_IN_SAME_PLACE -1 TRUE

				TASK_ACHIEVE_HEADING -1 170.7766

				TASK_PAUSE -1 3000

				TASK_SHOOT_AT_CHAR -1 scplayer -1

			CLOSE_SEQUENCE_TASK h9_sequence_task
			
			IF NOT IS_CHAR_DEAD h9_heli_man[3]

				PERFORM_SEQUENCE_TASK h9_heli_man[3] h9_sequence_task 

			ENDIF

			CLEAR_SEQUENCE_TASK h9_sequence_task

			h9_wanted_level = 2

	    ENDIF

		IF h9_wanted_level = 2
	    AND TIMERB > 18000
		AND NOT IS_CAR_DEAD h9_chopper

	    	IF IS_PLAYBACK_GOING_ON_FOR_CAR h9_chopper

				UNPAUSE_PLAYBACK_RECORDED_CAR h9_chopper

			ENDIF

			h9_wanted_level = 3

		ENDIF

		IF h9_roof_trigger = 1

		    IF HAS_PICKUP_BEEN_COLLECTED h9_para_pickup
				
				IF DOES_BLIP_EXIST h9_para_blip

				 	REMOVE_BLIP h9_para_blip

				ENDIF

				IF NOT IS_CAR_DEAD h9_helicopter

					ADD_BLIP_FOR_CAR h9_helicopter h9_chopper_blip
	
					SET_BLIP_AS_FRIENDLY h9_chopper_blip TRUE

				ENDIF

				h9_mission_status = 18	
			
				h9_chute_deleted = 2

				IF NOT IS_CAR_DEAD h9_helicopter

					GOSUB h9_set_camera

					SET_FIXED_CAMERA_POSITION 2267.8804 1692.8203 111.0014 0.0 0.0 0.0 // High bike
					POINT_CAMERA_AT_POINT 2267.8757 1693.3955 110.1835 JUMP_CUT

					WAIT 100

					SET_INTERPOLATION_PARAMETERS 0.0 6000
									
					SET_FIXED_CAMERA_POSITION 2293.7092 1832.2788 41.1442 0.0 0.0 0.0 // High bike
					POINT_CAMERA_AT_POINT 2294.0574 1833.1499 40.7979 INTERPOLATION

					PRINT_NOW ( HM9_24 ) 6000 1
					
					WAIT 6000

					GOSUB h9_restore_camera

					PRINT_NOW ( HM9_1H ) 6000 1 // ~s~Parachute off and escape in the ~b~helicopter~s~.

				ELSE

					ADD_BLIP_FOR_COORD 365.5647 2536.7600 15.6637 h9_safe_house

					PRINT_NOW ( HM9_X ) 6000 1 // ~s~Parachute off and escape to the ~y~safe house~s~.

				ENDIF

 				IF NOT IS_CHAR_DEAD scplayer
					
					SET_CHAR_HEADING scplayer 353.9519 

				ENDIF

				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE

			ENDIF

		ENDIF
			
		IF IS_CAR_DEAD h9_helicopter 
		AND DOES_BLIP_EXIST h9_chopper_blip

			IF NOT DOES_BLIP_EXIST h9_safe_house

				ADD_BLIP_FOR_COORD 365.5647 2536.7600 15.6637 h9_safe_house

				PRINT_NOW ( HM9_1J ) 6000 1 // ~s~Escape to the ~y~safe house~s~.

			ENDIF

			REMOVE_BLIP h9_chopper_blip

		ENDIF

		IF NOT IS_CAR_DEAD h9_chopper
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR h9_chopper
			AND h9_heli_chasing = 0
				
				POLICE_HELI_CHASE_ENTITY h9_chopper scplayer -1 20.0

				h9_heli_chasing = 1

			ENDIF
		ENDIF

	ENDIF

	IF h9_mission_status = 18
					
		IF IS_CAR_DEAD h9_helicopter 
		AND DOES_BLIP_EXIST h9_chopper_blip

			REMOVE_BLIP h9_chopper_blip

		ENDIF

		IF NOT IS_CAR_DEAD h9_helicopter 
		AND NOT IS_CHAR_DEAD scplayer

			IF DOES_BLIP_EXIST h9_chopper_blip
			AND IS_CHAR_IN_CAR scplayer h9_helicopter

				IF NOT DOES_BLIP_EXIST h9_safe_house

					ADD_BLIP_FOR_COORD 365.5647 2536.7600 15.6637 h9_safe_house

					PRINT_NOW ( HM9_1J ) 6000 1 // ~s~Escape to the ~y~safe house~s~.

				ENDIF

				REMOVE_BLIP h9_chopper_blip

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer
		AND DOES_BLIP_EXIST h9_para_blip
			
			GET_CHAR_COORDINATES scplayer x y z
				
			IF z < 90.0000

				IF DOES_BLIP_EXIST h9_para_blip
				AND h9_can_see = 0

					REMOVE_BLIP h9_para_blip

				ENDIF

			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer
		AND DOES_BLIP_EXIST h9_chopper_blip

			IF NOT IS_CAR_DEAD h9_helicopter
				
				GET_CHAR_COORDINATES scplayer x y z
					
				IF z < 36.0000

					IF DOES_BLIP_EXIST h9_chopper_blip
					AND h9_can_see = 0
					AND h9_missed_chopper = 0

						REMOVE_BLIP h9_chopper_blip

						ADD_BLIP_FOR_COORD 365.5647 2536.7600 15.6637 h9_safe_house

						PRINT_NOW ( HM9_1J ) 6000 1 // ~s~Escape to the ~y~safe house~s~.

						h9_missed_chopper = 1

					ENDIF

				ENDIF

			ENDIF

		ENDIF


		// -----------------------------------------------------------------------------------------------------
									   
	 	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 365.5647 2536.7600 15.6637 4.0 4.0 4.0 TRUE

			// -----------------------------------------------------------------------------------------------------

			IF NOT IS_CHAR_DEAD scplayer

				SET_PLAYER_CONTROL player1 OFF

			ENDIF

			//last0
			h9_spawned_end_guys = 1
			REMOVE_BLIP h9_safe_house

			GOSUB h9_fade_out 

			IF NOT IS_CHAR_DEAD scplayer

				IF IS_CHAR_IN_ANY_CAR scplayer

					STORE_CAR_CHAR_IS_IN scplayer car

				ENDIF

			ENDIF 

			IF NOT IS_CHAR_DEAD scplayer

				IF IS_CHAR_IN_ANY_CAR scplayer
					
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 345.3890 2539.2097 15.7622  

				ELSE
												 
					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 345.3890 2539.2097 15.7622  

				ENDIF

				SET_CHAR_HEADING scplayer 300.8022

			ENDIF

			IF NOT IS_CAR_DEAD h9_chopper
				DELETE_CAR h9_chopper
			ENDIF
						
			IF NOT IS_CAR_DEAD h9_helicopter 
				DELETE_CAR h9_helicopter
			ENDIF

			IF NOT IS_CAR_DEAD car 
				DELETE_CAR car
			ENDIF

			REQUEST_MODEL SECURICA
			REQUEST_MODEL COPBIKE
			REQUEST_MODEL lapdm1
			
			LOAD_SPECIAL_CHARACTER 1 ZERO
			LOAD_SPECIAL_CHARACTER 2 WUZIMU

			WHILE NOT HAS_MODEL_LOADED SECURICA
			OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
			OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
			OR NOT HAS_MODEL_LOADED COPBIKE
			OR NOT HAS_MODEL_LOADED lapdm1
				WAIT 0
			ENDWHILE

			// -----------------------------------------------------------------------------------------------------

			CREATE_OBJECT CJ_MONEY_BAG 344.9779 2545.2131 15.7642 h9_bag[0]
			CREATE_OBJECT CJ_MONEY_BAG 347.9360 2543.9722 15.7498 h9_bag[1]
			CREATE_OBJECT CJ_MONEY_BAG 348.9375 2545.8411 15.7383 h9_bag[2]
			CREATE_OBJECT CJ_MONEY_BAG 343.8135 2545.7217 15.7699 h9_bag[3]	

			CLEAR_AREA 345.2756 2546.8237 15.7628 50.0 TRUE
				
			CREATE_CAR SECURICA 345.2756 2546.8237 15.7628 h9_getaway_car

			SET_CAR_HEADING	h9_getaway_car 58.9413

	 		LOCK_CAR_DOORS h9_getaway_car CARLOCK_LOCKOUT_PLAYER_ONLY

			FREEZE_CAR_POSITION h9_getaway_car TRUE

			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL02 343.7633 2545.0225 15.7702 h9_gang[0]

			SET_CHAR_HEADING h9_gang[0] 202.9705

			SET_ANIM_GROUP_FOR_CHAR h9_gang[0] blindman

			TASK_STAY_IN_SAME_PLACE h9_gang[0] TRUE

			SET_CHAR_DECISION_MAKER h9_gang[0] h9_empty_decision

			SET_CHAR_NEVER_TARGETTED h9_gang[0] TRUE

			SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_gang[0] TRUE 

			// -----------------------------------------------------------------------------------------------------

			CLEAR_AREA 344.5244 2540.5625 15.7743 200.0 TRUE

			CLEAR_AREA 346.5559 2548.0271 15.7244 2.0 TRUE

			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 346.5559 2548.0271 15.7244 h9_zero
		
			SET_CHAR_HEADING h9_zero 242.1164 

			SET_CHAR_NEVER_TARGETTED h9_zero TRUE 

			TASK_STAY_IN_SAME_PLACE h9_zero TRUE

			SET_CHAR_DECISION_MAKER h9_zero h9_empty_decision

			SET_CHAR_NEVER_TARGETTED h9_zero TRUE

			SET_CHAR_ONLY_DAMAGED_BY_PLAYER h9_zero TRUE 

			CREATE_CAR COPBIKE 360.2574 2547.4604 15.5491 h9_bike_a

			SET_CAR_HEADING	h9_bike_a 107.4242

			CREATE_CAR COPBIKE 362.5870 2542.7148 15.6180 h9_bike_b

			SET_CAR_HEADING	h9_bike_b 95.4990 

			CREATE_CHAR_INSIDE_CAR h9_bike_a PEDTYPE_MISSION2 lapdm1 h9_biker_a

			CREATE_CHAR_INSIDE_CAR h9_bike_b PEDTYPE_MISSION2 lapdm1 h9_biker_b

			CLEAR_WANTED_LEVEL player1

			LOAD_SCENE_IN_DIRECTION 345.6064 2536.5103 15.7612 4.4301 

			IF NOT IS_CHAR_DEAD h9_gang[0]

				SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[0] 342.5135 2545.1472 15.7763   

				SET_CHAR_HEADING h9_gang[0] 215.4312

			ENDIF

			GOSUB h9_set_camera

			REQUEST_ANIMATION FIGHT_B
				
			WHILE NOT HAS_ANIMATION_LOADED FIGHT_B
				WAIT 0
			ENDWHILE

			IF NOT IS_CHAR_DEAD scplayer
			AND NOT IS_CHAR_DEAD h9_zero
			AND NOT IS_CHAR_DEAD h9_gang[0]

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

				OPEN_SEQUENCE_TASK h9_sequence_task

					TASK_GO_TO_COORD_ANY_MEANS -1 347.2988 2541.4834 15.7529 PEDMOVE_WALK -1

					TASK_TURN_CHAR_TO_FACE_CHAR -1 h9_zero

					TASK_PAUSE -1 4500

					TASK_TURN_CHAR_TO_FACE_CHAR -1 h9_zero

					TASK_PLAY_ANIM -1 FIGHTB_M FIGHT_B 4.0 FALSE FALSE FALSE FALSE -1

					TASK_PAUSE -1 3000

					TASK_TURN_CHAR_TO_FACE_CHAR -1 h9_gang[0]

				CLOSE_SEQUENCE_TASK h9_sequence_task

				PERFORM_SEQUENCE_TASK scplayer h9_sequence_task

				CLEAR_SEQUENCE_TASK h9_sequence_task

			ENDIF
							  
			SET_FIXED_CAMERA_POSITION 346.2450 2537.9404 16.7555 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 346.7908 2538.7783 16.7555 JUMP_CUT

			IF NOT IS_CAR_DEAD h9_bike_a
			AND NOT IS_CAR_DEAD h9_bike_b
			AND NOT IS_CHAR_DEAD h9_biker_a
			AND NOT IS_CHAR_DEAD h9_biker_b

				OPEN_SEQUENCE_TASK h9_sequence_task

					TASK_CAR_DRIVE_TO_COORD -1 h9_bike_a 351.2343 2544.1245 15.7337 5.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				
					TASK_PAUSE -1 2000

					TASK_LEAVE_CAR -1 h9_bike_a

					SWITCH_CAR_SIREN h9_bike_a OFF

				CLOSE_SEQUENCE_TASK h9_sequence_task

				PERFORM_SEQUENCE_TASK h9_biker_a h9_sequence_task
	
				CLEAR_SEQUENCE_TASK h9_sequence_task

				OPEN_SEQUENCE_TASK h9_sequence_task

					TASK_CAR_DRIVE_TO_COORD -1 h9_bike_b 351.4331 2542.7527 15.7337 5.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
				
					TASK_PAUSE -1 2000

					TASK_LEAVE_CAR -1 h9_bike_b

					SWITCH_CAR_SIREN h9_bike_b OFF

				CLOSE_SEQUENCE_TASK h9_sequence_task

				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

				PERFORM_SEQUENCE_TASK h9_biker_b h9_sequence_task
					
				CLEAR_SEQUENCE_TASK h9_sequence_task

			ENDIF
	
			IF NOT IS_CAR_DEAD h9_bike_a
			AND NOT IS_CHAR_DEAD h9_biker_a
				SWITCH_CAR_SIREN h9_bike_a ON
			ENDIF

			IF NOT IS_CAR_DEAD h9_bike_b
			AND NOT IS_CHAR_DEAD h9_biker_b
				SWITCH_CAR_SIREN h9_bike_b ON
			ENDIF

			GOSUB h9_fade_in
					
			PRINT_NOW ( HE8_ZA ) 4000 1 // Zero, where are you?
			
			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_ZA // Zero, where are you?

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			IF NOT IS_CHAR_DEAD h9_zero

				FLUSH_ROUTE

				EXTEND_ROUTE 350.6611 2546.7346 15.6957 

				EXTEND_ROUTE 348.9894 2543.4763 15.7446
				
				EXTEND_ROUTE 347.4687 2541.7935 15.7521          

				TASK_FOLLOW_POINT_ROUTE h9_zero PEDMOVE_RUN FOLLOW_ROUTE_ONCE
				
				FLUSH_ROUTE				

			ENDIF

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
			ENDWHILE

			PRINT_NOW ( HE8_ZB ) 4000 1 // I didn’t mean to tell Berkley, it just kinda came out!

			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_HE8_ZB // I didn’t mean to tell Berkley, it just kinda came out!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
			ENDWHILE
		    
			IF NOT IS_CHAR_DEAD h9_zero

				TASK_PLAY_ANIM h9_zero KO_shot_face PED 4.0 FALSE FALSE FALSE TRUE -1

			ENDIF
	
			PRINT_NOW ( ZER1_DB ) 4000 1 // Will you watch it, you idiot!
			
			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_ZER1_DB // Will you watch it, you idiot!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
			ENDWHILE

 			WAIT 500

			SET_INTERPOLATION_PARAMETERS 0.0 3000 
	
			SET_FIXED_CAMERA_POSITION 348.9764 2538.0286 16.6197 0.0 0.0 0.0 // High bike
			POINT_CAMERA_AT_POINT 348.7430 2539.0007 16.6362 INTERPOLATION

			PRINT_NOW ( WUZX_BR ) 4000 1 // Hey, CJ, calm down!
			
			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_WUZX_BR // Hey, CJ, calm down!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
			ENDWHILE
	
 			WAIT 1000

			PRINT_NOW ( WUZX_AK ) 4000 1 // You better take me home, CJ!
			
			CLEAR_MISSION_AUDIO 1

		 	LOAD_MISSION_AUDIO 1 SOUND_WUZX_AK // You better take me home, CJ!

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
				WAIT 0
			ENDWHILE

			PLAY_MISSION_AUDIO 1

			TIMERB = 0
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
			    WAIT 0
			ENDWHILE

			GOSUB h9_fade_out

		 	DELETE_CHAR h9_gang[0]

			DELETE_CHAR h9_zero

			DELETE_CHAR h9_biker_a

			DELETE_CHAR h9_biker_b

			DELETE_CAR h9_bike_a

			DELETE_CAR h9_bike_b

			DELETE_CAR h9_getaway_car

			REMOVE_ANIMATION FIGHT_B

			DELETE_OBJECT h9_bag[0]
			DELETE_OBJECT h9_bag[1]
			DELETE_OBJECT h9_bag[2]
			DELETE_OBJECT h9_bag[3]

			h9_clothes_changed = 1

			IF NOT IS_CHAR_DEAD scplayer

				RESTORE_CLOTHES_STATE

				BUILD_PLAYER_MODEL player1

			ENDIF	 
						 
			IF NOT IS_CHAR_DEAD scplayer

				LOAD_SCENE 2023.7832 1007.8450 9.8203

				CLEAR_AREA 2023.7832 1007.8450 9.8203 20.0 TRUE

				REQUEST_COLLISION 2023.7832 1007.8450

				SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2023.7832 1007.8450 9.8130

				SET_CHAR_HEADING scplayer 270.9639

			ENDIF

			GOSUB h9_restore_camera

			WAIT 500

			GOSUB h9_fade_in

			GOTO mission_heist9_passed 	

		ENDIF

	ENDIF

	// *************************************************************************************************
	// *																							   *
	// *							      Cutscene of team guy dying								   *
	// *                                  															   *
	// *************************************************************************************************

	IF h9_mission_status < 16
	AND h9_van_not_needed_anymore = 0
	AND h9_team_is_spawned = 1

		REPEAT 4 v

			IF NOT IS_CHAR_DEAD h9_gang[v]

				GET_CHAR_COORDINATES h9_gang[v] h9_x[v] h9_y[v] h9_z[v]

			ENDIF

			IF IS_CHAR_DEAD h9_gang[v] 

				IF DOES_BLIP_EXIST h9_gang_blip[v]

					REMOVE_BLIP h9_gang_blip[v]

				ENDIF

				GOSUB h9_set_camera

				h9_z[v] = h9_z[v] + 2.5 

				SET_FIXED_CAMERA_POSITION h9_x[v] h9_y[v] h9_z[v] 0.0 0.0 0.0 // Keycode Door

				h9_z[v] = h9_z[v] - 2.5 

				POINT_CAMERA_AT_POINT h9_x[v] h9_y[v] h9_z[v] JUMP_CUT

				WAIT 4000

				GOSUB h9_fix_for_failed

				PRINT_NOW ( HM9_9 ) 4000 1 // ~r~You failed to protect the guys!

				GOTO mission_heist9_failed
			
			ENDIF

		ENDREPEAT

	ENDIF

ENDWHILE

GOTO mission_heist9_failed

// ***************************** Teleport Team Upstairs **********************************
teleport_team_into_place:

	DELETE_CAR h9_forklift

	MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT

	// Door Right
	IF NOT IS_CHAR_DEAD h9_gang[0]
		
		CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[0]

		SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[0] 2145.1458 1626.4639 992.5681 

		SET_CHAR_HEADING h9_gang[0] 11.1673  

		TASK_DUCK h9_gang[0] -1

	ENDIF

	// Door Left
	IF NOT IS_CHAR_DEAD h9_gang[1]

		CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[1]

		SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[1] 2143.2502 1626.4819 992.5681 

		SET_CHAR_HEADING h9_gang[1] 3.4561   

		TASK_DUCK h9_gang[1] -1

	ENDIF

	// Pillar Left
	IF NOT IS_CHAR_DEAD h9_gang[2]

		CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[2]

		SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[2] 2140.7527 1616.2489 992.5681

		SET_CHAR_HEADING h9_gang[2] 342.9477  

	ENDIF

	// Pillar Right
	IF NOT IS_CHAR_DEAD h9_gang[3]

		CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[3]

		SET_CHAR_COORDINATES_DONT_WARP_GANG h9_gang[3] 2147.7385 1616.7034 992.5681

		SET_CHAR_HEADING h9_gang[3] 33.9273  

	ENDIF

RETURN

explosion_proof:

	REPEAT 4 v

		IF NOT IS_CHAR_DEAD h9_gang[v]

			SET_CHAR_PROOFS h9_gang[v] FALSE FALSE TRUE TRUE FALSE

		ENDIF

	ENDREPEAT

RETURN

not_explosion_proof:

	REPEAT 4 v

		IF NOT IS_CHAR_DEAD h9_gang[v]

			SET_CHAR_PROOFS h9_gang[v] FALSE FALSE FALSE TRUE FALSE

		ENDIF
			
	ENDREPEAT

RETURN

h9_check_all_goons_dead:

	IF IS_CHAR_DEAD h9_second_room[0]
	AND IS_CHAR_DEAD h9_second_room[1]
	AND IS_CHAR_DEAD h9_second_room[2]
	AND IS_CHAR_DEAD h9_second_room[3]
	AND IS_CHAR_DEAD h9_second_room[4]

		IF IS_CHAR_DEAD h9_second_room[5]
		AND IS_CHAR_DEAD h9_second_room[6]
		AND IS_CHAR_DEAD h9_second_room[7]

			// Run to the super nodes
			OPEN_SEQUENCE_TASK h9_sequence_task 

				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2148.0173 1597.4574 998.7676 PEDMOVE_RUN -1
				
			CLOSE_SEQUENCE_TASK h9_sequence_task

			REPEAT 4 v

				h9_second[v] = 0

				h9_third[v] = 0

				IF NOT IS_CHAR_DEAD h9_gang[v]
				AND NOT IS_CHAR_DEAD scplayer

					CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[v]

					PERFORM_SEQUENCE_TASK h9_gang[v] h9_sequence_task

				ENDIF

				IF NOT IS_CHAR_DEAD h9_gang[v]

					h9_hlth[v] = h9_gang_health[v]				

					GET_CHAR_HEALTH h9_gang[v] h9_gang_health[v]

					IF NOT h9_hlth[v] = h9_gang_health[v]

						PRINT_NOW ( HM9_44 ) 5000 1 // ZERO : The team's getting shot, your supposed to be protecting them!

					ENDIF
							
				ENDIF
					
			ENDREPEAT
				
			CLEAR_SEQUENCE_TASK h9_sequence_task

			h9_first_node = 0

			h9_second_node = 0

			h9_run_van_conv = 1

			TIMERB = 0

			REPEAT 4 v

				CHANGE_BLIP_DISPLAY h9_gang_blip[v] BOTH

			ENDREPEAT

			IF DOES_BLIP_EXIST h9_roller_door_blip
				REMOVE_BLIP h9_roller_door_blip
			ENDIF
			
			ADD_BLIP_FOR_COORD 2220.5430 1572.6249 998.9531 h9_roller_door_blip
			SET_BLIP_ENTRY_EXIT h9_roller_door_blip 2196.6353 1676.8411 10.0

			PRINT_NOW ( HM9_8 ) 7000 1 // ~s~Lead the team safely back to the ~y~van~s~.

			h9_all_dead = 1
		
		ENDIF

	ENDIF

RETURN

// **************************************** Mission heist9 failed ***********************

mission_heist9_failed:

	IF NOT IS_CHAR_DEAD scplayer

		DISABLE_PLAYER_SPRINT player1 FALSE

		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	ENDIF

	PRINT_BIG ( M_FAIL ) 5000 1 // "Mission Failed"

RETURN

// **************************************** Mission heist9 passed ************************

mission_heist9_passed:
	// night vision goggles

	CREATE_PICKUP_WITH_AMMO NVGOGGLES PICKUP_ON_STREET_SLOW 1 102.7728 1899.1920 33.1572 goggle_pickups[4]       // up a tower in area 51 in desert

	CREATE_PICKUP_WITH_AMMO NVGOGGLES PICKUP_ON_STREET_SLOW 1 1274.3005 -825.7809 1085.0795 goggle_pickups[5]       // mad dogs mansion in LA in desert

	CREATE_PICKUP_WITH_AMMO NVGOGGLES PICKUP_ON_STREET_SLOW 1 -948.3487 1858.0232 8.3237 goggle_pickups[6]       // inside sherman dam 

	CREATE_PICKUP_WITH_AMMO NVGOGGLES PICKUP_ON_STREET_SLOW 1 -1956.2742 -853.7687 31.8730 goggle_pickups[7]       // silicone valley

	IF NOT IS_CHAR_DEAD scplayer

		DISABLE_PLAYER_SPRINT player1 FALSE

		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	ENDIF

	IF DOES_BLIP_EXIST theheist_contact_blip 

		REMOVE_BLIP theheist_contact_blip

	ENDIF

	flag_heist_mission_counter ++ // Used to terminate this mission loop in the main script. These variables will be set up in the main.sc

	PLAYER_MADE_PROGRESS 1

	REGISTER_MISSION_PASSED ( HEIST_9 ) // Used in the stats
	 
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 100000 5000 1 //"Mission Passed!" //100 being the amount of cash

	ADD_SCORE player1 100000 // Amount of cash

	AWARD_PLAYER_MISSION_RESPECT 100 //amount of respect

	PLAY_MISSION_PASSED_TUNE 1

	CLEAR_WANTED_LEVEL player1

	CLEAR_AREA 2444.7725 655.0391 10.3874 30.0 TRUE

	flag_player_got_casino_uniform = 1

	SET_INT_STAT PASSED_HEIST8 1

RETURN

// ********************************** cleanup interior ***********************************
h9_clean_interior:

	MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT
	MARK_MODEL_AS_NO_LONGER_NEEDED SECURICA
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4
	MARK_MODEL_AS_NO_LONGER_NEEDED silenced
	MARK_MODEL_AS_NO_LONGER_NEEDED teargas
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED GENERATOR_BIG
	MARK_MODEL_AS_NO_LONGER_NEEDED satchel
	MARK_MODEL_AS_NO_LONGER_NEEDED NVGOGGLES
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_MONEY_BAG
	MARK_MODEL_AS_NO_LONGER_NEEDED GENERATOR_BIG_d
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
	MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
	MARK_MODEL_AS_NO_LONGER_NEEDED lapdm1
	MARK_MODEL_AS_NO_LONGER_NEEDED bodyarmour
	MARK_MODEL_AS_NO_LONGER_NEEDED bomb
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
	MARK_MODEL_AS_NO_LONGER_NEEDED VBFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
	MARK_MODEL_AS_NO_LONGER_NEEDED gun_para
	MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR

	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 2
	UNLOAD_SPECIAL_CHARACTER 3

	REMOVE_ANIMATION BOMBER
				
	REMOVE_CAR_RECORDING 499
	REMOVE_CAR_RECORDING 487
	REMOVE_CAR_RECORDING 494
	REMOVE_CAR_RECORDING 484

RETURN

// ********************************** mission cleanup ***********************************

mission_cleanup_heist9:

	disable_mod_garage = 0

	IF NOT IS_CHAR_DEAD scplayer

		STOP_CHAR_FACIAL_TALK scplayer

	ENDIF

	SET_WANTED_MULTIPLIER 1.0

	SET_RADAR_ZOOM 0
	
	REPEAT 11 v

		IF DOES_BLIP_EXIST h9_seconds_inv[v]

			REMOVE_BLIP h9_seconds_inv[v]

		ENDIF

		IF DOES_BLIP_EXIST h9_first_inv[v]

			REMOVE_BLIP h9_first_inv[v]

		ENDIF

	ENDREPEAT

	IF d5_bronze_generator_unlocked = 1

		SWITCH_CAR_GENERATOR d5_bronze_generator 101

	ENDIF

	IF d5_silver_generator_unlocked = 1

		SWITCH_CAR_GENERATOR d5_silver_generator 101

	ENDIF

	IF d5_gold_generator_unlocked = 1

		SWITCH_CAR_GENERATOR d5_gold_generator 101

	ENDIF

	IF NOT IS_CHAR_DEAD h9_zero
			
		REMOVE_CHAR_ELEGANTLY h9_zero

	ENDIF

	IF NOT IS_CHAR_DEAD h9_biker_a
			
		REMOVE_CHAR_ELEGANTLY h9_biker_a


	ENDIF

	IF NOT IS_CHAR_DEAD h9_biker_b
			
		REMOVE_CHAR_ELEGANTLY h9_biker_b

	ENDIF

	SET_ZONE_NO_COPS MEAD FALSE

	CLEAR_ONSCREEN_TIMER h9_safe_timer

	IF player_fall_state > 0
		player_fall_state = 6 
	ENDIF

	para_float_Vy = 5.0

	para_flare_Vy = 8.5 //for when player lifts legs up

	para_freefall_Vz = -30.0

	para_freefall_Vy = 32.0

	iTerminateAllAmbience = 0 

	SWITCH_OBJECT_BRAINS CASINO_OBJECT_BRAIN TRUE

	IF NOT IS_CHAR_DEAD scplayer

		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_NIGHTVISION
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_TEARGAS

	ENDIF

	IF NOT IS_CHAR_DEAD scplayer

		DISABLE_PLAYER_SPRINT player1 FALSE

		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	ENDIF

	SET_OBJECT_HEADING h9_door_c 270.0000

	SWITCH_ENTRY_EXIT MAFCAS TRUE

	SWITCH_ENTRY_EXIT MAFCAS2 TRUE

	SWITCH_ENTRY_EXIT JUMP1 TRUE

	SWITCH_ENTRY_EXIT JUMP2 TRUE

	SET_DARKNESS_EFFECT FALSE 0
		
	SET_NIGHT_VISION FALSE

	flag_player_on_mission = 0

	CLEAR_WANTED_LEVEL player1

	DELETE_OBJECT h9_door_a

	REPEAT 4 v

		IF NOT IS_CHAR_DEAD h9_gang[v]
			
			REMOVE_CHAR_ELEGANTLY h9_gang[v]

		ENDIF

		IF NOT IS_CHAR_DEAD h9_safe_guards[v]

			MARK_CHAR_AS_NO_LONGER_NEEDED h9_safe_guards[v]

		ENDIF

		IF DOES_BLIP_EXIST h9_gang_blip[v] 

			REMOVE_BLIP h9_gang_blip[v]

		ENDIF

		IF DOES_OBJECT_EXIST h9_bag[v]

			DELETE_OBJECT h9_bag[v]

		ENDIF
			 
	ENDREPEAT

	IF DOES_BLIP_EXIST h9_satchel_blip

		REMOVE_BLIP h9_satchel_blip 

	ENDIF

	IF DOES_BLIP_EXIST h9_safe_blip
		REMOVE_BLIP h9_safe_blip
	ENDIF

	IF DOES_BLIP_EXIST h9_swipecard_blip
		REMOVE_BLIP h9_swipecard_blip
	ENDIF

	IF DOES_BLIP_EXIST h9_gas_blip
		REMOVE_BLIP h9_gas_blip
	ENDIF

	IF DOES_BLIP_EXIST h9_casino_blip
		REMOVE_BLIP h9_casino_blip
	ENDIF

	IF DOES_BLIP_EXIST h9_blip_keycode
		REMOVE_BLIP h9_blip_keycode
	ENDIF

	IF DOES_BLIP_EXIST h9_forklift_blip
		REMOVE_BLIP h9_forklift_blip
	ENDIF

	IF DOES_BLIP_EXIST h9_roller_door_blip
		REMOVE_BLIP h9_roller_door_blip
	ENDIF

	IF DOES_BLIP_EXIST h9_para_blip

		REMOVE_BLIP h9_para_blip

	ENDIF

	IF DOES_BLIP_EXIST h9_roof_blip

		REMOVE_BLIP h9_roof_blip

	ENDIF

	IF DOES_BLIP_EXIST h9_elevator
		REMOVE_BLIP h9_elevator
	ENDIF

	IF DOES_BLIP_EXIST h9_safe_house
		REMOVE_BLIP h9_safe_house
	ENDIF

	IF DOES_BLIP_EXIST h9_cash_room_blip
		REMOVE_BLIP h9_cash_room_blip
	ENDIF

	IF DOES_BLIP_EXIST h9_gen_blip_a
		REMOVE_BLIP h9_gen_blip_a
	ENDIF

	IF DOES_BLIP_EXIST h9_gen_blip_b
		REMOVE_BLIP h9_gen_blip_b
	ENDIF

	IF IS_ANY_PICKUP_AT_COORDS 2176.4712 1597.3944 1000.0000
		REMOVE_PICKUP h9_health
	ENDIF

	IF IS_ANY_PICKUP_AT_COORDS 2144.3389 1640.6660 993.5000 
		REMOVE_PICKUP h9_health_1

	ENDIF

	IF IS_ANY_PICKUP_AT_COORDS 2146.4871 1622.8053 993.0000
		REMOVE_PICKUP h9_satchel_bomb
	ENDIF

	IF IS_ANY_PICKUP_AT_COORDS 2267.9888 1699.6678 101.4000
		REMOVE_PICKUP h9_para_pickup
	ENDIF

	IF IS_ANY_PICKUP_AT_COORDS 2265.8247 1617.0693 94.5000
		REMOVE_PICKUP h9_armour_1
	ENDIF

	GET_GAME_TIMER timer_mobile_start // Used to reset the mobile phone timer so it doesn't ring immediately after the mission

	MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT
	MARK_MODEL_AS_NO_LONGER_NEEDED SECURICA
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2 
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4
	MARK_MODEL_AS_NO_LONGER_NEEDED para_pack
	MARK_MODEL_AS_NO_LONGER_NEEDED silenced
	MARK_MODEL_AS_NO_LONGER_NEEDED teargas
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYBOUN
	MARK_MODEL_AS_NO_LONGER_NEEDED GENERATOR_BIG
	MARK_MODEL_AS_NO_LONGER_NEEDED satchel
	MARK_MODEL_AS_NO_LONGER_NEEDED NVGOGGLES
	MARK_MODEL_AS_NO_LONGER_NEEDED CJ_MONEY_BAG
	MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
	MARK_MODEL_AS_NO_LONGER_NEEDED GENERATOR_BIG_d
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
	MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
	MARK_MODEL_AS_NO_LONGER_NEEDED lapdm1
	MARK_MODEL_AS_NO_LONGER_NEEDED bodyarmour
	MARK_MODEL_AS_NO_LONGER_NEEDED bomb
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
	MARK_MODEL_AS_NO_LONGER_NEEDED VBFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED VWFYCRP
	MARK_MODEL_AS_NO_LONGER_NEEDED mp5lng
	MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR
	MARK_MODEL_AS_NO_LONGER_NEEDED SWAT
	MARK_MODEL_AS_NO_LONGER_NEEDED gun_para

	UNLOAD_SPECIAL_CHARACTER 1

	UNLOAD_SPECIAL_CHARACTER 2

	UNLOAD_SPECIAL_CHARACTER 3

	REMOVE_ANIMATION HEIST9
	REMOVE_ANIMATION BOMBER
	REMOVE_ANIMATION FIGHT_B

	MISSION_HAS_FINISHED

	SET_PED_DENSITY_MULTIPLIER 1.0

	SET_CAR_DENSITY_MULTIPLIER 1.0

RETURN

h9_set_camera:

	CLEAR_PRINTS
	CLEAR_HELP
	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

RETURN

h9_restore_camera:

	CLEAR_PRINTS
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
 
RETURN

h9_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

h9_fade_in:
	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN
 
h9_keys:

LVAR_INT h9_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Y

		IF NOT IS_CHAR_DEAD scplayer
						
		 	SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2179.3540 1673.1804 10.0469      
 	
		 	SET_CHAR_HEADING scplayer 285.6208 

			SET_CAMERA_BEHIND_PLAYER

			IF NOT IS_CHAR_DEAD scplayer

				STOP_CHAR_FACIAL_TALK scplayer

			ENDIF

			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2176.5828 1600.4617 998.9749 
			
			SET_CHAR_HEADING scplayer 179.7799 

		ENDIF
				
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		GOSUB h9_set_camera
		
		SET_FIXED_CAMERA_POSITION 2144.6770 1614.0612 1001.4688 0.0 0.0 0.0 // High bike
		POINT_CAMERA_AT_POINT 2144.4128 1615.0156 1001.3306 JUMP_CUT
		
		IF NOT IS_CHAR_DEAD scplayer
		
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			SET_CHAR_VELOCITY scplayer 0.0 0.0 0.0

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2142.6643 1617.3972 999.9680   

			SET_CHAR_HEADING scplayer 352.2108 

			TASK_SHOOT_AT_COORD scplayer 2142.6643 1620.3972 1003.0000 1000

		ENDIF
			
		WAIT 4000

		CLEAR_AREA 2143.1243 1618.4272 999.9680 10.0 TRUE
				
		GOSUB h9_restore_camera

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_B

		SET_AREA_VISIBLE 1

		LOAD_SCENE 2147.7783 1611.9717 1005.1875 
		
		IF NOT IS_CHAR_DEAD	scplayer
			
			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2147.7783 1611.9717 1005.1875   
			   
			SET_CHAR_HEADING scplayer 182.1343   

			SET_CAMERA_BEHIND_PLAYER

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K
	   
		GOSUB h9_fade_out

		GOSUB h9_set_camera

		REQUEST_MODEL cellphone

		LOAD_SPECIAL_CHARACTER 1 zero

		WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		OR NOT HAS_MODEL_LOADED cellphone
			WAIT 0
		ENDWHILE 
		
		SET_NIGHT_VISION FALSE

		SET_DARKNESS_EFFECT TRUE 220

		SET_FIXED_CAMERA_POSITION 2219.4563 1587.3701 999.1768 0.0 0.0 0.0 // Keycode Door
		POINT_CAMERA_AT_POINT 2219.6799 1586.4089 999.3383 JUMP_CUT
	 
		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2221.1221 1582.9735 998.9675 h9_zero

		SET_CHAR_AREA_VISIBLE h9_zero 1

		SET_CHAR_HEADING h9_zero 4.8671
		
		TASK_USE_MOBILE_PHONE h9_zero TRUE   

		GOSUB h9_fade_in

		PRINT_NOW (	HM9_46 ) 4000 1 // ZERO : Watch out Carl the powers back online!

		WAIT 2000

		SET_DARKNESS_EFFECT FALSE 0

		WAIT 2000

		GOSUB h9_restore_camera

		PRINT_NOW ( HM9_8 ) 5000 1 // ~s~Protect the team while they carry the cash back to the van.

		h9_msg_displayed = 1

		h9_zero_created = 1

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2144.0215 1612.8673 992.5681  
 
			SET_CHAR_HEADING scplayer 0.0000

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_E

		IF NOT IS_CHAR_DEAD scplayer

			RESTORE_CLOTHES_STATE

			BUILD_PLAYER_MODEL player1

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_P

		h9_mission_status = 6

		// Creates forklift
		CREATE_CAR FORKLIFT 2173.6206 1585.9639 998.9722 h9_forklift

		SET_VEHICLE_AREA_VISIBLE h9_forklift 1 

		SET_CAR_HEADING h9_forklift 270.0

		CHANGE_CAR_COLOUR h9_forklift CARCOLOUR_TAXIYELLOW CARCOLOUR_TAXIYELLOW

		MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT
					
		h9_spawn_forklift = 1

		IF NOT IS_CAR_DEAD h9_forklift 

			SET_VEHICLE_AREA_VISIBLE h9_forklift 1

		ENDIF 
			
		SET_AREA_VISIBLE 1

		LOAD_SCENE 2256.5513 1704.7623 1.1747 

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2220.3704 1574.4839 998.9695  

			SET_CHAR_HEADING scplayer 180.0000 

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_L

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 1962.8406 1060.6331 993.4688 
			
			SET_CHAR_HEADING scplayer 346.1133 

		ENDIF

	ENDIF
	
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_U
	
		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 362.6579 2525.2478 15.6336  
  
			SET_CHAR_HEADING scplayer 11.8402 

		ENDIF

    	h9_mission_status = 18

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 346.9504 2539.0254 15.7546  

			SET_CHAR_HEADING scplayer 2.8895

		ENDIF

		GOSUB h9_set_camera

		SET_FIXED_CAMERA_POSITION 350.1020 2536.9951 16.9325 0.0 0.0 0.0 // High bike
		POINT_CAMERA_AT_POINT 349.5165 2537.8008 16.8440 JUMP_CUT

		IF NOT IS_CHAR_DEAD scplayer

			TASK_PLAY_ANIM scplayer IDLE_chat PED 1.0 FALSE FALSE FALSE FALSE -1			

		ENDIF
					
		PRINT_NOW ( HE8_ZA ) 4000 1 // Zero, where are you?
		
		CLEAR_MISSION_AUDIO 1

	 	LOAD_MISSION_AUDIO 1 SOUND_HE8_ZA // Zero, where are you?

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
			WAIT 0
		ENDWHILE

		PLAY_MISSION_AUDIO 1

		TIMERB = 0
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
		    WAIT 0
		ENDWHILE
			
		PRINT_NOW ( HE8_ZB ) 4000 1 // I didn’t mean to tell Berkley, it just kinda came out!

		CLEAR_MISSION_AUDIO 1

	 	LOAD_MISSION_AUDIO 1 SOUND_HE8_ZB // I didn’t mean to tell Berkley, it just kinda came out!

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
			WAIT 0
		ENDWHILE

		PLAY_MISSION_AUDIO 1

		TIMERB = 0
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
		    WAIT 0
		ENDWHILE
			
		PRINT_NOW ( HE8_ZC ) 4000 1 // Hey, it doesn’t matter, we pulled it off, Caligula’s has been emptied!
		
		CLEAR_MISSION_AUDIO 1

	 	LOAD_MISSION_AUDIO 1 SOUND_HE8_ZC // Hey, it doesn’t matter, we pulled it off, Caligula’s has been emptied!

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
			WAIT 0
		ENDWHILE

		PLAY_MISSION_AUDIO 1

		TIMERB = 0
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
		    WAIT 0
		ENDWHILE
			
		PRINT_NOW ( HE8_ZD ) 4000 1 // Hell I’ll drink to that!
		
		CLEAR_MISSION_AUDIO 1

	 	LOAD_MISSION_AUDIO 1 SOUND_HE8_ZD // Hell I’ll drink to that!

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
			WAIT 0
		ENDWHILE

		PLAY_MISSION_AUDIO 1

		TIMERB = 0
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
		    WAIT 0
		ENDWHILE

		GOSUB h9_fade_out

		DELETE_CHAR h9_gang[0]

		DELETE_CHAR h9_zero

		DELETE_CAR h9_getaway_car

		DELETE_OBJECT h9_bag[0]
		DELETE_OBJECT h9_bag[1]
		DELETE_OBJECT h9_bag[2]
		DELETE_OBJECT h9_bag[3]

		IF NOT IS_CHAR_DEAD scplayer

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 347.1670 2538.4841 15.7535 

			SET_CHAR_HEADING scplayer 191.9870

		ENDIF

		GOSUB h9_restore_camera

		GOSUB h9_fade_in



	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_O

		SET_RADAR_ZOOM 0

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_R

		SET_RADAR_ZOOM 100

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_T

		LVAR_INT h9_truck

		REQUEST_MODEL TRIADA
		REQUEST_MODEL TRIBOSS
		REQUEST_MODEL SECURICA
		REQUEST_MODEL COPBIKE
		REQUEST_MODEL lapdm1

		LOAD_SPECIAL_CHARACTER 1 ZERO

	    WHILE NOT HAS_MODEL_LOADED TRIADA
		OR NOT HAS_MODEL_LOADED TRIBOSS
		OR NOT HAS_MODEL_LOADED SECURICA
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
		OR NOT HAS_MODEL_LOADED COPBIKE
		OR NOT HAS_MODEL_LOADED lapdm1
			WAIT 0
		ENDWHILE

		REQUEST_CAR_RECORDING 494

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 494

			WAIT 0
				
		ENDWHILE

		DELETE_OBJECT h9_door_e

		CREATE_CAR SECURICA 2220.3894 1577.6233 998.0000 h9_truck

		SET_VEHICLE_AREA_VISIBLE h9_truck 1

		SET_CAR_HEADING	h9_truck 179.9804
		 		  
		CREATE_CHAR PEDTYPE_MISSION2 TRIADA 2224.0417 1589.3666 998.9670 h9_gang[0]

		SET_CHAR_AREA_VISIBLE h9_gang[0] 1

		SET_CHAR_HEADING h9_gang[0] 165.2519  

		CREATE_CHAR PEDTYPE_MISSION2 TRIBOSS 2222.8931 1589.5830 998.9684 h9_gang[1]

		SET_CHAR_AREA_VISIBLE h9_gang[1] 1

		SET_CHAR_HEADING h9_gang[1] 173.9364

		CREATE_CHAR PEDTYPE_MISSION2 TRIBOSS 2221.8657 1589.2455 998.9696 h9_gang[2]

		SET_CHAR_AREA_VISIBLE h9_gang[2] 1

		SET_CHAR_HEADING h9_gang[2] 177.0563

		CREATE_CHAR PEDTYPE_MISSION2 TRIBOSS 2220.7229 1589.2239 998.9710 h9_gang[3]

		SET_CHAR_AREA_VISIBLE h9_gang[3] 1

		SET_CHAR_HEADING h9_gang[3] 171.7417

		GOSUB h9_set_camera

		SET_FIXED_CAMERA_POSITION 2216.7207 1579.8098 1000.8326 0.0 0.0 0.0 // High bike
		POINT_CAMERA_AT_POINT 2217.3821 1580.5382 1000.6542 JUMP_CUT

		PRINT_NOW ( HE8_UA ) 4000 1 // I unloaded the police bikes!
		
		CLEAR_MISSION_AUDIO 1

	 	LOAD_MISSION_AUDIO 1 SOUND_HE8_UA // I unloaded the police bikes!

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
			WAIT 0
		ENDWHILE

		PLAY_MISSION_AUDIO 1

		TIMERB = 0
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
		    WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				GOTO h9_beginning_0
			ENDIF
		ENDWHILE

		PRINT_NOW ( HE8_UB ) 4000 1 // Everybody in! You two, change into your police uniforms!
		
		CLEAR_MISSION_AUDIO 1

	 	LOAD_MISSION_AUDIO 1 SOUND_HE8_UB // Everybody in! You two, change into your police uniforms!

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
			WAIT 0
		ENDWHILE

		PLAY_MISSION_AUDIO 1

		TIMERB = 0
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1			   
		    WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				GOTO h9_beginning_0
			ENDIF
		ENDWHILE

 	   	DELETE_CHAR h9_zero
 				
		DELETE_CHAR h9_gang[2]
		  
		DELETE_CHAR h9_gang[3]

		SET_FIXED_CAMERA_POSITION 2216.9141 1571.6224 999.7229 0.0 0.0 0.0 // High bike
		POINT_CAMERA_AT_POINT 2217.2698 1572.5570 999.7267 JUMP_CUT

		CREATE_CHAR PEDTYPE_MISSION2 lapdm1 2220.7996 1581.1633 998.9764 h9_biker_a

		SET_CHAR_HEADING h9_biker_a 196.0524  
		SET_CHAR_AREA_VISIBLE h9_biker_a 1

		CREATE_CHAR PEDTYPE_MISSION2 lapdm1 2220.0188 1581.1787 998.9772 h9_biker_b

		SET_CHAR_HEADING h9_biker_b 74.4668
		SET_CHAR_AREA_VISIBLE h9_biker_b 1

		IF NOT IS_CAR_DEAD h9_truck
		AND NOT IS_CHAR_DEAD h9_gang[0]
			TASK_ENTER_CAR_AS_DRIVER h9_gang[0] h9_truck 8000
		ENDIF

		IF NOT IS_CAR_DEAD h9_truck
		AND NOT IS_CHAR_DEAD h9_gang[1]
			TASK_ENTER_CAR_AS_PASSENGER h9_gang[1] h9_truck 8000 0
		ENDIF
		
		TIMERB = 0
		WHILE TIMERB < 4000
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				GOTO h9_beginning_0
			ENDIF
		ENDWHILE

		IF NOT IS_CAR_DEAD h9_bike_a
		AND NOT IS_CHAR_DEAD h9_biker_a
			TASK_ENTER_CAR_AS_DRIVER h9_biker_a h9_bike_a 4000
		ENDIF

		IF NOT IS_CAR_DEAD h9_bike_b
		AND NOT IS_CHAR_DEAD h9_biker_b
			TASK_ENTER_CAR_AS_DRIVER h9_biker_b h9_bike_b 4000
		ENDIF

		TIMERB = 0
		WHILE TIMERB < 4000
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				GOTO h9_beginning_0
			ENDIF
		ENDWHILE

		SET_FIXED_CAMERA_POSITION 2223.8503 1572.0138 1001.6227 0.0 0.0 0.0 // High bike
		POINT_CAMERA_AT_POINT 2223.3713 1572.8435 1001.3364 JUMP_CUT

		IF HAS_CAR_RECORDING_BEEN_LOADED 494
		AND NOT IS_CAR_DEAD h9_truck
			START_PLAYBACK_RECORDED_CAR h9_truck 494
	   	ENDIF

		TIMERB = 0
		WHILE TIMERB < 500
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				GOTO h9_beginning_0
			ENDIF
		ENDWHILE

		IF NOT IS_CAR_DEAD h9_bike_a
		AND NOT IS_CHAR_DEAD h9_biker_a
			TASK_CAR_DRIVE_TO_COORD h9_biker_a h9_bike_a 2223.4858 1564.4629 1001.0159 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
		ENDIF

		IF NOT IS_CAR_DEAD h9_bike_b
		AND NOT IS_CHAR_DEAD h9_biker_b
			TASK_CAR_DRIVE_TO_COORD h9_biker_b h9_bike_b 2217.3186 1566.5996 1000.3005 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
		ENDIF

		TIMERB = 0
		WHILE TIMERB < 2000
			WAIT 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
			OR IS_BUTTON_PRESSED PAD1 CIRCLE
				GOTO h9_beginning_0
			ENDIF
		ENDWHILE

		h9_beginning_0:

		REPEAT 4 v
			
			IF DOES_BLIP_EXIST h9_gang_blip[v]

				REMOVE_BLIP h9_gang_blip[v]

			 	DELETE_CHAR h9_gang[v]

			ENDIF

		ENDREPEAT

		IF NOT IS_CAR_DEAD h9_truck

		   	DELETE_CAR h9_truck

		ENDIF
		IF NOT IS_CAR_DEAD h9_bike_a

		   	DELETE_CAR h9_bike_a

		ENDIF
		IF NOT IS_CAR_DEAD h9_bike_b

		   	DELETE_CAR h9_bike_b

		ENDIF
		IF NOT IS_CHAR_DEAD h9_zero

		   	DELETE_CHAR h9_zero

		ENDIF

		GOSUB h9_restore_camera

		MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA

		MARK_MODEL_AS_NO_LONGER_NEEDED TRIBOSS

		MARK_MODEL_AS_NO_LONGER_NEEDED SWAT

		UNLOAD_SPECIAL_CHARACTER 2

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_I

		IF NOT IS_CHAR_DEAD scplayer

			PLAYER_TAKE_OFF_GOGGLES	player1	TRUE

		ENDIF

	ENDIF 

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W

		h9_mission_status = 18

		IF NOT IS_CHAR_DEAD scplayer 

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 338.0000 2540.5625 15.7743

		ENDIF

	ENDIF

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_J

		REQUEST_MODEL COPBIKE
		REQUEST_MODEL lapdm1

		LOAD_SPECIAL_CHARACTER 1 ZERO

	    WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		OR NOT HAS_MODEL_LOADED COPBIKE
		OR NOT HAS_MODEL_LOADED lapdm1
		OR NOT HAS_MODEL_LOADED lapdm1

			WAIT 0
		ENDWHILE

		REQUEST_CAR_RECORDING 494

		LOAD_SPECIAL_CHARACTER 1 ZERO

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 494

			WAIT 0
				
		ENDWHILE

		SWITCH_ENTRY_EXIT JUMP1 TRUE

		SWITCH_ENTRY_EXIT JUMP2 FALSE

		h9_closing = 1

		CLEAR_AREA 2220.3396 1582.0681 998.9764 10.0 TRUE

		CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2220.3396 1582.0681 998.9764 h9_zero

		SET_CHAR_AREA_VISIBLE h9_zero 1
	
		SET_CHAR_HEADING h9_zero 350.4443

		// Cop Bikes 

		CREATE_CAR COPBIKE 2223.6995 1582.7799 998.0000 h9_bike_a

		SET_VEHICLE_AREA_VISIBLE h9_bike_a 1

		SET_CAR_HEADING	h9_bike_a 175.8434
		
			LOCK_CAR_DOORS h9_bike_a CARLOCK_LOCKOUT_PLAYER_ONLY

		FREEZE_CAR_POSITION h9_bike_a TRUE

		CREATE_CAR COPBIKE 2217.5493 1583.6671 998.0000 h9_bike_b

		SET_VEHICLE_AREA_VISIBLE h9_bike_b 1

		SET_CAR_HEADING	h9_bike_b 175.3717

			LOCK_CAR_DOORS h9_bike_b CARLOCK_LOCKOUT_PLAYER_ONLY

		FREEZE_CAR_POSITION h9_bike_b TRUE

		GOSUB spawn_the_gang

		IF NOT IS_CAR_DEAD h9_getaway_car

			SET_CAR_COORDINATES h9_getaway_car 2220.2939 1578.0703 998.9756  

			SET_CAR_HEADING h9_getaway_car 177.9097

		ENDIF

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2216.0305 1618.6458 998.9766 
			
			SET_CHAR_HEADING scplayer 269.4771

		ENDIF

		IF NOT IS_CHAR_DEAD h9_gang[0]
			WARP_CHAR_FROM_CAR_TO_COORD h9_gang[0] 2219.7578 1585.2850 998.9722
			CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[0]
		ENDIF
				 
		IF NOT IS_CHAR_DEAD h9_gang[1]
			WARP_CHAR_FROM_CAR_TO_COORD h9_gang[1] 2221.8833 1584.2611 998.9696   		
			CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[1]
		ENDIF
				 
		IF NOT IS_CHAR_DEAD h9_gang[2]
			WARP_CHAR_FROM_CAR_TO_COORD h9_gang[2] 2222.7046 1586.2660 998.9686  		
			CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[2]
		ENDIF
				 
		IF NOT IS_CHAR_DEAD h9_gang[3]
			WARP_CHAR_FROM_CAR_TO_COORD h9_gang[3] 2220.3015 1586.5684 998.9716 		
			CLEAR_CHAR_TASKS_IMMEDIATELY h9_gang[3]
		ENDIF
				 
		h9_mission_status = 14
			
	ENDIF  

RETURN

h9_fix_for_failed:

	LVAR_INT h9_visible_area

	GET_AREA_VISIBLE h9_visible_area

	GOSUB h9_fade_out

	SET_NIGHT_VISION FALSE

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_MINIGAME_IN_PROGRESS

		SET_AREA_VISIBLE 0

		LOAD_SCENE 2185.7393 1677.3958 10.0946
		
		CLEAR_AREA 2185.7393 1677.3958 10.0946 10.0 TRUE

		REQUEST_COLLISION 2185.7393 1677.3958
		
		IF IS_CHAR_IN_ANY_CAR scplayer 

			WARP_CHAR_FROM_CAR_TO_COORD scplayer 2185.7393 1677.3958 10.0946

		ELSE

			SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer 2185.7393 1677.3958 10.0946  

		ENDIF
		
		SET_CHAR_HEADING scplayer 88.4776
			
		SET_CHAR_AREA_VISIBLE scplayer 0

		RESTORE_CAMERA_JUMPCUT

		SET_CAMERA_BEHIND_PLAYER

	ENDIF 

	IF NOT IS_CHAR_DEAD scplayer

		RESTORE_CLOTHES_STATE

		BUILD_PLAYER_MODEL player1

		h9_clothes_changed = 1

	ENDIF

	GOSUB h9_fade_in
  
RETURN

h9_rebuild_the_player:

	GOSUB h9_fade_out

	IF NOT IS_CHAR_DEAD scplayer

		RESTORE_CLOTHES_STATE

		BUILD_PLAYER_MODEL player1
			 
		h9_clothes_changed = 1

	ENDIF

	GOSUB h9_fade_in

RETURN

h9_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 h9_audio

	h9_playing = 0

RETURN

h9_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND h9_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $h9_print ) 20000 1  

		h9_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND h9_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $h9_print

		h9_playing = 2

	ENDIF
	
RETURN

}
