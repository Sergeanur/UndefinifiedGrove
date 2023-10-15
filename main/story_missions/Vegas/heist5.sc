MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Heist 5 *******************************************
// ********************************* Steal a sky crane *************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME HEIST5

// Mission start stuff

GOSUB mission_start_heist5

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_heist5_failed
ENDIF

GOSUB mission_cleanup_heist5

MISSION_END

{ 
// Variables for mission

LVAR_INT heli_heist5 heli_blip_heist5 flag_player_had_message_heist5 blip_coord1_heist5 dummy_blip_heist5

LVAR_FLOAT locateX_heist5 locateY_heist5 locateZ_heist5

LVAR_INT guardnumber[7]

LVAR_INT guardnumber0_dead_heist5

LVAR_INT guardnumber1_dead_heist5

LVAR_INT guardnumber2_dead_heist5

LVAR_INT guardnumber3_dead_heist5

LVAR_INT guardnumber4_dead_heist5

LVAR_INT guardnumber5_dead_heist5

LVAR_INT guardnumber6_dead_heist5

LVAR_INT dm_empty_heist5 dm_duck_heist5 dm_noduck_heist5

// sequences
LVAR_INT kill_player_heist5 // used for the perimiter guards to attack the player using the special code

LVAR_INT kill2_player_heist5

// Perimiter guards
LVAR_INT perimiter_guard1_heist5 perimiter_guard1_dead_heist5 

LVAR_INT perimiter_guard2_heist5 perimiter_guard2_dead_heist5

LVAR_INT perimiter_guard3_heist5 perimiter_guard3_dead_heist5

// Warehouse guards
LVAR_INT guard1_warehouse_heist5 guard1_warehouse_dead_heist5
LVAR_INT guard2_warehouse_heist5 guard2_warehouse_dead_heist5
LVAR_INT guard3_warehouse_heist5 guard3_warehouse_dead_heist5
LVAR_INT guard4_warehouse_heist5 guard4_warehouse_dead_heist5
LVAR_INT guard5_warehouse_heist5 guard5_warehouse_dead_heist5
//LVAR_INT guard6_warehouse_heist5 guard6_warehouse_dead_heist5
LVAR_INT guard7_warehouse_heist5 guard7_warehouse_dead_heist5
LVAR_INT guard8_warehouse_heist5 guard8_warehouse_dead_heist5

// on skyway
LVAR_INT guard9_warehouse_heist5 guard9_warehouse_dead_heist5
LVAR_INT guard10_warehouse_heist5 guard10_warehouse_dead_heist5
LVAR_INT guard11_warehouse_heist5 guard11_warehouse_dead_heist5
LVAR_INT guard12_warehouse_heist5 guard12_warehouse_dead_heist5 

// general flags
LVAR_INT guards_killing_player_heist5

LVAR_INT flag_cleanup2_done_heist5

// Guards that run sequences
LVAR_INT guard1_warehouse_task_heist5 guard2_warehouse_task_heist5 guard9_warehouse_task_heist5 guard10_warehouse_task_heist5
LVAR_INT guard11_warehouse_task_heist5 guard12_warehouse_task_heist5

// gate guard to hassle player
LVAR_INT gateguard1_heist5 gateguard1_dead_heist5

LVAR_INT player_entered_depot_heist5

LVAR_INT flag_base_alerted_heist5

LVAR_INT flag_warn_player_heist5

LVAR_INT task_status2

LVAR_INT flag_gateguard1_got_ai_heist5

// Heli stuff
LVAR_INT heli_locked_heist5

LVAR_FLOAT heli1X_heist5 heli1Y_heist5 heli1Z_heist5 dis_heli_van_heist5

// minigun stuff
LVAR_INT minigun_heist5 minigun_blip_heist5 flag_player_at_minigun

// ambush guys called as back up
LVAR_INT counter_backup_dead_heist5

LVAR_INT flag_done_mid_cleanup_heist5

// backup reserves
LVAR_INT chopper1_backup_heist5 chopper1_backup_blip_heist5 chopper1_backup_dead_heist5
LVAR_INT heli_pilot1_heist5

LVAR_INT chopper2_backup_heist5 chopper2_backup_blip_heist5 chopper2_backup_dead_heist5
LVAR_INT heli_pilot2_heist5

// bank vans to be picked up
LVAR_INT bankvan1_heist5 bankvan1_blip_heist5 bankvan1_dead_heist5

LVAR_INT correct_vehicle_on_winch_heist5

LVAR_INT vehicle_on_winch_heist5 char_on_winch_heist5 object_on_winch_heist5

LVAR_INT bankvan1_blip_on_heist5

VAR_INT bankvan1_health_heist5 // needs to be a global var_int to display in counter

LVAR_FLOAT bankvan1X_heist5 bankvan1Y_heist5 bankvan1Z_heist5

LVAR_FLOAT warp_heli1X_heist5 warp_heli1Y_heist5 warp_heli1Z_heist5

LVAR_FLOAT warp_heli2X_heist5 warp_heli2Y_heist5 warp_heli2Z_heist5

LVAR_INT flag_end_blip_on_heist5 // used to say of the desert airstrip blip is on or off

LVAR_INT flag_wheels_message_heist5 // used to tell player van has to be on its wheels

LVAR_INT van_in_bad_area_heist5 // used to tell me van is dropped in a place the player cannot get at it.

LVAR_INT flag_heli_wheels_cool_heist5 // used to tell me that the van is on its wheels and that is cool

// animation stuff
LVAR_INT flag_move_rocket_guys_heist5 // used to move the guards position after player has been spotted

VAR_FLOAT bankvan1_upright_value_heist5 // change this to a LVAR once I am not displaying it

// Landing heli and van at the end
LVAR_INT heli_land_blip_heist5

LVAR_FLOAT heli_locateX_heist5 heli_locateY_heist5 heli_locateZ_heist5

// wuzi
LVAR_INT wuzi_heist5

// car driving out of base stuff
VAR_INT driver_gate_car_heist5 gate_drive_car_heist5 gate_drive_car_ai_heist5

// sphere
LVAR_INT sphere_heist5

// new minigun stuff
LVAR_INT flag_leave_gun_heist5 flag_mingun_help_heist5 flag_player_using_gun_heist5 helis_created_heist5

// new gate driver stuff
LVAR_INT gate_driver_attacking_player

// grenades
LVAR_INT no_player_grenades_heist5 grenades_heist5 armour_heist5 health_heist5

// mission audio for alarm
LVAR_INT alarm_loop_on_heist5

// guards location
LVAR_FLOAT gateguard1X_heist5 gateguard1Y_heist5 gateguard1Z_heist5

// new winch message
LVAR_INT flag_winch_message_heist5 flag_watched_end_cutscene_heist5

LVAR_INT stored_vehicle_heist5 // used for cutscenes

// **************************************** Mission Start **********************************

mission_start_heist5:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

// Helicopter stuff
locateX_heist5 = 406.9890 // 384.783 
locateY_heist5 = 2516.9006 // 2599.899 
locateZ_heist5 = 15.4918 // 15.524

flag_player_had_message_heist5 = 0

heli_locked_heist5 = 0

// Base stuff
flag_base_alerted_heist5 = 0

player_entered_depot_heist5 = 0

guards_killing_player_heist5 = 0

// gateguard1
flag_warn_player_heist5 = 0

task_status2 = 0

flag_gateguard1_got_ai_heist5 = 0

// guard dead flags
perimiter_guard1_dead_heist5 = 0

perimiter_guard2_dead_heist5 = 0

perimiter_guard3_dead_heist5 = 0

guardnumber0_dead_heist5 = 0

guardnumber1_dead_heist5 = 0

guardnumber2_dead_heist5 = 0

guardnumber3_dead_heist5 = 0

guardnumber4_dead_heist5 = 0

guardnumber5_dead_heist5 = 0

guardnumber6_dead_heist5 = 0

guard1_warehouse_dead_heist5 = 0

guard2_warehouse_dead_heist5 = 0

guard3_warehouse_dead_heist5 = 0

guard4_warehouse_dead_heist5 = 0

guard5_warehouse_dead_heist5 = 0

//guard6_warehouse_dead_heist5 = 0

guard7_warehouse_dead_heist5 = 0

guard8_warehouse_dead_heist5 = 0

gateguard1_dead_heist5 = 0

// guards who fall from rail
guard9_warehouse_dead_heist5 = 0

guard10_warehouse_dead_heist5 = 0

guard11_warehouse_dead_heist5 = 0

guard12_warehouse_dead_heist5 = 0

// backup guys
counter_backup_dead_heist5 = 0

flag_done_mid_cleanup_heist5 = 0

warp_heli1X_heist5 = 0.0

warp_heli1Y_heist5 = 0.0

warp_heli1Z_heist5 = 0.0

warp_heli2X_heist5 = 0.0

warp_heli2Y_heist5 = 0.0 

warp_heli2Z_heist5 = 0.0


// minigun stuff
flag_player_at_minigun = 0

// Bank van and winch stuff
correct_vehicle_on_winch_heist5 = 0

bankvan1_health_heist5 = 1000

bankvan1_dead_heist5 = 0

vehicle_on_winch_heist5 = 0

char_on_winch_heist5 = 0 

object_on_winch_heist5 = 0

bankvan1_blip_on_heist5 = 0

flag_end_blip_on_heist5 = 0

bankvan1_upright_value_heist5 = 0.0

dis_heli_van_heist5 = 0.0

flag_wheels_message_heist5 = 0

van_in_bad_area_heist5 = 0

flag_heli_wheels_cool_heist5 = 0

// general flags
flag_cleanup2_done_heist5 = 0

// flag to move guards
flag_move_rocket_guys_heist5 = 0

// Landing heli and van at the end
heli_locateX_heist5 = 365.356
heli_locateY_heist5 = 2538.113
heli_locateZ_heist5 = 15.712

// new car driving out of gate stuff
gate_drive_car_ai_heist5 = 0

// new mingun stuff
flag_leave_gun_heist5 = 0		
flag_mingun_help_heist5 = 0
flag_player_using_gun_heist5 = 0
helis_created_heist5 = 0

// new gate driver stuff
gate_driver_attacking_player = 0

// grenades
no_player_grenades_heist5 = 0

// alarm sound
alarm_loop_on_heist5 = 0

// gate guard location
gateguard1X_heist5 = 0.0
gateguard1Y_heist5 = 0.0
gateguard1Z_heist5 = 0.0

// new winch message
flag_winch_message_heist5 = 0

flag_watched_end_cutscene_heist5 = 0			 		

LOAD_MISSION_TEXT HEIST5
			
WAIT 0

CLEAR_AREA 2030.108 1007.929 9.852 1.0 FALSE 
SET_CHAR_COORDINATES scplayer 2030.108 1007.929 9.852
SET_CHAR_HEADING scplayer 270.0

// ****************************************START OF CUTSCENE********************************

iTerminateAllAmbience = 1

SET_AREA_VISIBLE 10

LOAD_CUTSCENE HEIST6a
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

SET_AREA_VISIBLE 0

iTerminateAllAmbience = 0

// ****************************************END OF CUTSCENE**********************************

SWITCH_CAR_GENERATOR d5_bronze_generator 0
SWITCH_CAR_GENERATOR d5_silver_generator 0
SWITCH_CAR_GENERATOR d5_gold_generator 0

SWITCH_AMBIENT_PLANES FALSE
SWITCH_POLICE_HELIS FALSE

GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_GRENADE no_player_grenades_heist5

IF no_player_grenades_heist5 < 5
	CREATE_PICKUP GRENADE PICKUP_ONCE 2550.9670 2824.3425 10.6 grenades_heist5
ENDIF

CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2557.337 2817.809 10.820 armour_heist5
CREATE_PICKUP HEALTH PICKUP_ONCE 2573.734 2814.910 10.820 health_heist5 

SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO

//CLEAR_AREA 2030.108 1007.929 9.852 1.0 FALSE 
//SET_CHAR_COORDINATES scplayer 2030.108 1007.929 9.852
//SET_CHAR_HEADING scplayer 270.0

LOAD_SCENE 2030.108 1007.929 9.852 

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

//Adds sequences
OPEN_SEQUENCE_TASK kill_player_heist5
	TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK kill_player_heist5

OPEN_SEQUENCE_TASK kill2_player_heist5
  	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK kill2_player_heist5

// Guards that have to run into position
OPEN_SEQUENCE_TASK guard1_warehouse_task_heist5
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2604.0 2837.0 9.860 PEDMOVE_RUN 10000
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK guard1_warehouse_task_heist5

OPEN_SEQUENCE_TASK guard2_warehouse_task_heist5
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2608.0 2827.0 9.860 PEDMOVE_RUN 10000
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK guard2_warehouse_task_heist5

OPEN_SEQUENCE_TASK guard9_warehouse_task_heist5
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2585.0 2815.0 18.999 PEDMOVE_RUN 10000
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK guard9_warehouse_task_heist5

OPEN_SEQUENCE_TASK guard10_warehouse_task_heist5
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2593.0 2835.0 18.999 PEDMOVE_RUN 10000 
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK guard10_warehouse_task_heist5

OPEN_SEQUENCE_TASK guard11_warehouse_task_heist5
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2585.0 2809.0 18.999 PEDMOVE_RUN 10000
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK guard11_warehouse_task_heist5

OPEN_SEQUENCE_TASK guard12_warehouse_task_heist5
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 2575.0 2833.0 18.999 PEDMOVE_RUN 10000
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK guard12_warehouse_task_heist5

// Decision makers for mission
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm_duck_heist5
ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_duck_heist5 EVENT_SHOT_FIRED_WHIZZED_BY TASK_SIMPLE_DUCK_WHILE_SHOTS_WHIZZING 0.0 100.0 100.0 100.0 FALSE TRUE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm_noduck_heist5

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm_empty_heist5

// requesting models
REQUEST_MODEL LEVIATHN
REQUEST_MODEL ARMY
REQUEST_MODEL M4
REQUEST_MODEL ROCKETLA
REQUEST_MODEL HUNTER
REQUEST_MODEL minigun_base
REQUEST_MODEL minigun
REQUEST_MODEL SECURICA
REQUEST_MODEL PATRIOT

LOAD_MISSION_AUDIO 3 SOUND_SECURITY_ALARM // Alarm ringing

LOAD_ALL_MODELS_NOW 

// waiting for the models to load
WHILE NOT HAS_MODEL_LOADED LEVIATHN 
OR NOT HAS_MODEL_LOADED  ARMY
OR NOT HAS_MODEL_LOADED M4
OR NOT HAS_MODEL_LOADED ROCKETLA
OR NOT HAS_MODEL_LOADED PATRIOT
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED HUNTER
OR NOT HAS_MODEL_LOADED minigun_base
OR NOT HAS_MODEL_LOADED minigun
OR NOT HAS_MODEL_LOADED SECURICA
OR NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0
ENDWHILE

SET_FADING_COLOUR 0 0 0
DO_FADE 1500 FADE_IN

WHILE GET_FADING_STATUS			   
	WAIT 0
ENDWHILE

SET_PLAYER_CONTROL player1 ON

// Done this way as the model loading is taking a while
PRINT_NOW (HM5_1) 5000 1 //"Steal the helicopter from the military fuel dump."

ADD_BLIP_FOR_COORD 2616.408 2721.367 35.563 dummy_blip_heist5
SET_BLIP_AS_FRIENDLY dummy_blip_heist5 TRUE 

// creates the minigun object
CREATE_OBJECT minigun_base 2595.549 2758.040 22.832 minigun_heist5
SET_OBJECT_HEADING minigun_heist5 180.0

LOAD_MISSION_AUDIO 1 SOUND_HE5_BA
LOAD_MISSION_AUDIO 2 SOUND_HE5_BD

WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2502.702 2778.550 9.868 200.0 200.0 400.0 FALSE
OR NOT HAS_MISSION_AUDIO_LOADED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
    	GOTO mission_heist5_passed  
	ENDIF
	
ENDWHILE 

// creates the helicopter
CREATE_CAR LEVIATHN 2614.603 2720.745 35.563 heli_heist5
SET_CAR_HEADING heli_heist5 90.0
ATTACH_WINCH_TO_HELI heli_heist5 WINCHTYPE_MAGNET
LOCK_CAR_DOORS heli_heist5 CARLOCK_LOCKED
SET_CAR_PROOFS heli_heist5 TRUE TRUE TRUE TRUE FALSE
FREEZE_CAR_POSITION heli_heist5 TRUE
heli_locked_heist5 = 1 

// gate guard to hassle player
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2502.702 2778.550 9.868  gateguard1_heist5 // Guard gate1
GIVE_WEAPON_TO_CHAR gateguard1_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING gateguard1_heist5 90.0
SET_CHAR_ACCURACY gateguard1_heist5 70
SET_CHAR_SHOOT_RATE gateguard1_heist5 40
SET_CHAR_DECISION_MAKER gateguard1_heist5 dm_empty_heist5 

// ************************************ Creates the spanner guys **********************************
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2615.0 2835.0 9.860 guard1_warehouse_heist5 // righthand side back door 
GIVE_WEAPON_TO_CHAR guard1_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard1_warehouse_heist5 105.0
SET_CHAR_DECISION_MAKER guard1_warehouse_heist5 dm_duck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard1_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard1_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard1_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard1_warehouse_heist5 40

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2615.0 2817.0 9.860  guard2_warehouse_heist5  // lefthand side back door
GIVE_WEAPON_TO_CHAR guard2_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard2_warehouse_heist5 105.0
SET_CHAR_DECISION_MAKER guard2_warehouse_heist5 dm_duck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard2_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard2_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard2_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard2_warehouse_heist5 40
 
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2585.927 2837.060 9.820 guard3_warehouse_heist5 // guy crouching by open box left hand side
GIVE_WEAPON_TO_CHAR guard3_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard3_warehouse_heist5 127.0 
SET_CHAR_DECISION_MAKER guard3_warehouse_heist5 dm_duck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard3_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard3_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard3_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard3_warehouse_heist5 50

TASK_PLAY_ANIM guard3_warehouse_heist5 WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1
 
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2568.0 2821.0 9.860 guard4_warehouse_heist5  // creates right infront of doors
GIVE_WEAPON_TO_CHAR guard4_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard4_warehouse_heist5 80.0 
SET_CHAR_DECISION_MAKER guard4_warehouse_heist5 dm_duck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard4_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard4_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard4_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard4_warehouse_heist5 40
 
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2562.0 2818.0 9.860 guard5_warehouse_heist5  // small boxes left hand side close to door
GIVE_WEAPON_TO_CHAR guard5_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard5_warehouse_heist5 96.0 
SET_CHAR_DECISION_MAKER guard5_warehouse_heist5 dm_duck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard5_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard5_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard5_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard5_warehouse_heist5 45

TASK_PLAY_ANIM guard5_warehouse_heist5 WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1

//CREATE_CHAR PEDTYPE_MISSION1 ARMY 2562.0 2839.0 9.860 guard6_warehouse_heist5 // boxes right hand side close to door
//GIVE_WEAPON_TO_CHAR guard6_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
//SET_CHAR_HEADING guard6_warehouse_heist5 121.0 
//SET_CHAR_DECISION_MAKER guard6_warehouse_heist5 dm_duck_heist5
//CLEAR_ALL_CHAR_RELATIONSHIPS guard6_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
//SET_CHAR_RELATIONSHIP guard6_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
//SET_CHAR_ACCURACY guard6_warehouse_heist5 70
//SET_CHAR_SHOOT_RATE guard6_warehouse_heist5 45
  
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2571.933 2831.912 9.820 guard7_warehouse_heist5 // right hand side by the small boxes 2575.0 2838.0 9.860
GIVE_WEAPON_TO_CHAR guard7_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard7_warehouse_heist5 70.0 
SET_CHAR_DECISION_MAKER guard7_warehouse_heist5 dm_duck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard7_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard7_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard7_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard7_warehouse_heist5 40

TASK_PLAY_ANIM guard7_warehouse_heist5 WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2595.0 2840.0 9.860 guard8_warehouse_heist5 // large boxes right hand side by the exit door
GIVE_WEAPON_TO_CHAR guard8_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard8_warehouse_heist5 96.0 
SET_CHAR_DECISION_MAKER guard8_warehouse_heist5 dm_duck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard8_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard8_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard8_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard8_warehouse_heist5 50

// skyway guys
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2613.0 2806.0 18.999 guard9_warehouse_heist5 // top railings back right
GIVE_WEAPON_TO_CHAR guard9_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard9_warehouse_heist5 96.0
SET_CHAR_HEALTH guard9_warehouse_heist5 100  
SET_CHAR_DECISION_MAKER guard9_warehouse_heist5 dm_noduck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard9_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard9_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard9_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard9_warehouse_heist5 45

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2614.0 2848.0 18.999 guard10_warehouse_heist5 // top railings back left
GIVE_WEAPON_TO_CHAR guard10_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard10_warehouse_heist5 96.0
SET_CHAR_HEALTH guard10_warehouse_heist5 100 
SET_CHAR_DECISION_MAKER guard10_warehouse_heist5 dm_noduck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard10_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard10_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard10_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard10_warehouse_heist5 40

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2545.0 2806.0 18.999 guard11_warehouse_heist5 // top railings back left
GIVE_WEAPON_TO_CHAR guard11_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard11_warehouse_heist5 274.0
SET_CHAR_HEALTH guard11_warehouse_heist5 100
SET_CHAR_DECISION_MAKER guard11_warehouse_heist5 dm_noduck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard11_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard11_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard11_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard11_warehouse_heist5 45

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2563.0 2848.0 18.999 guard12_warehouse_heist5 // top railings back left
GIVE_WEAPON_TO_CHAR guard12_warehouse_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING guard12_warehouse_heist5 274.0  
SET_CHAR_HEALTH guard12_warehouse_heist5 100
SET_CHAR_DECISION_MAKER guard12_warehouse_heist5 dm_noduck_heist5
CLEAR_ALL_CHAR_RELATIONSHIPS guard12_warehouse_heist5  ACQUAINTANCE_TYPE_PED_HATE
SET_CHAR_RELATIONSHIP guard12_warehouse_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
SET_CHAR_ACCURACY guard12_warehouse_heist5 70
SET_CHAR_SHOOT_RATE guard12_warehouse_heist5 40

// **************************************** Creates the heli guards *******************************
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2621.0 2804.0 9.860 guardnumber[0]
SET_CHAR_DECISION_MAKER guardnumber[0] dm_noduck_heist5
GIVE_WEAPON_TO_CHAR guardnumber[0] WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_ACCURACY guardnumber[0] 70
SET_CHAR_SHOOT_RATE guardnumber[0] 50
 
FLUSH_ROUTE  
EXTEND_ROUTE 2629.0 2795.0 9.860 
EXTEND_ROUTE 2642.0 2788.0 9.860
EXTEND_ROUTE 2659.0 2786.0 9.860
EXTEND_ROUTE 2621.0 2804.0 9.860

TASK_FOLLOW_POINT_ROUTE guardnumber[0] PEDMOVE_WALK FOLLOW_ROUTE_LOOP

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2649.0 2768.0 18.375 guardnumber[1]
SET_CHAR_DECISION_MAKER guardnumber[1] dm_noduck_heist5
GIVE_WEAPON_TO_CHAR guardnumber[1] WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_STAY_IN_SAME_PLACE guardnumber[1] TRUE
SET_CHAR_HEADING guardnumber[1] 330.0
SET_CHAR_ACCURACY guardnumber[1] 70
SET_CHAR_SHOOT_RATE guardnumber[1] 40

TASK_PLAY_ANIM guardnumber[1] WEAPON_CROUCH PED 4.0 TRUE FALSE FALSE FALSE -1

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2626.370 2800.007 9.820 guardnumber[2]
SET_CHAR_DECISION_MAKER guardnumber[2] dm_noduck_heist5
SET_CHAR_HEADING guardnumber[2] 14.7 
GIVE_WEAPON_TO_CHAR guardnumber[2] WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_ACCURACY guardnumber[2] 70
SET_CHAR_SHOOT_RATE guardnumber[2] 45
TASK_TOGGLE_DUCK guardnumber[2] TRUE
SET_CHAR_STAY_IN_SAME_PLACE guardnumber[2] TRUE 

//FLUSH_ROUTE
//EXTEND_ROUTE 2598.0 2756.0 22.872   
//EXTEND_ROUTE 2613.0 2755.0 22.872 
//EXTEND_ROUTE 2633.0 2755.0 29.946  
//EXTEND_ROUTE 2644.0 2755.0 33.548
//EXTEND_ROUTE 2633.0 2755.0 29.946 
//EXTEND_ROUTE 2613.0 2755.0 22.872 
//EXTEND_ROUTE 2598.0 2756.0 22.872 
//EXTEND_ROUTE 2597.0 2742.0 22.872 
//
//TASK_FOLLOW_POINT_ROUTE guardnumber[2] PEDMOVE_WALK FOLLOW_ROUTE_LOOP

// ************************************* Perimiter guards using the new special code **************
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2504.0 2763.0 9.860 perimiter_guard1_heist5 // left hand side of the guard box
GIVE_WEAPON_TO_CHAR perimiter_guard1_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING perimiter_guard1_heist5 364.0
SET_CHAR_DECISION_MAKER perimiter_guard1_heist5 dm_noduck_heist5
SET_CHAR_ACCURACY perimiter_guard1_heist5 70
SET_CHAR_SHOOT_RATE perimiter_guard1_heist5 45

FLUSH_ROUTE
EXTEND_ROUTE 2504.0 2753.0 9.860 
EXTEND_ROUTE 2504.0 2737.0 9.860
EXTEND_ROUTE 2505.0 2711.0 9.860
EXTEND_ROUTE 2504.0 2737.0 9.860 
EXTEND_ROUTE 2504.0 2753.0 9.860
EXTEND_ROUTE 2504.0 2763.0 9.860

TASK_FOLLOW_POINT_ROUTE perimiter_guard1_heist5 PEDMOVE_WALK FOLLOW_ROUTE_LOOP

// Guard2
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2509.0 2791.0 9.860 perimiter_guard2_heist5 // right hand side of guard box
GIVE_WEAPON_TO_CHAR perimiter_guard2_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING perimiter_guard2_heist5 90.0
SET_CHAR_DECISION_MAKER perimiter_guard2_heist5 dm_noduck_heist5
SET_CHAR_ACCURACY perimiter_guard2_heist5 70
SET_CHAR_SHOOT_RATE perimiter_guard2_heist5 40

FLUSH_ROUTE 
EXTEND_ROUTE 2511.0 2809.0 9.860
EXTEND_ROUTE 2511.0 2833.0 9.860
EXTEND_ROUTE 2511.0 2844.0 9.860 
EXTEND_ROUTE 2527.0 2844.0 9.860
EXTEND_ROUTE 2511.0 2844.0 9.860
EXTEND_ROUTE 2511.0 2833.0 9.860
EXTEND_ROUTE 2511.0 2809.0 9.860
EXTEND_ROUTE 2509.0 2791.0 9.860

TASK_FOLLOW_POINT_ROUTE perimiter_guard2_heist5 PEDMOVE_WALK FOLLOW_ROUTE_LOOP

// guard3
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2583.0 2801.0 9.860 perimiter_guard3_heist5 // alley beside main block
GIVE_WEAPON_TO_CHAR perimiter_guard3_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_HEADING perimiter_guard3_heist5 184.0
SET_CHAR_DECISION_MAKER perimiter_guard3_heist5 dm_noduck_heist5
SET_CHAR_ACCURACY perimiter_guard3_heist5 70
SET_CHAR_SHOOT_RATE perimiter_guard3_heist5 50

FLUSH_ROUTE 
EXTEND_ROUTE 2568.0 2801.0 9.860
EXTEND_ROUTE 2551.0 2801.0 9.860
EXTEND_ROUTE 2538.0 2801.0 9.845
EXTEND_ROUTE 2551.0 2801.0 9.845
EXTEND_ROUTE 2568.0 2801.0 9.855
EXTEND_ROUTE 2583.0 2801.0 9.845

TASK_FOLLOW_POINT_ROUTE perimiter_guard3_heist5 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
 
// ********************************** Rocket Launcher guys ****************************************
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2606.119 2733.683 35.563 guardnumber[3] // Rocket Launcher guy 1
SET_CHAR_DECISION_MAKER guardnumber[3] dm_noduck_heist5
GIVE_WEAPON_TO_CHAR guardnumber[3] WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_STAY_IN_SAME_PLACE guardnumber[3] TRUE
SET_CHAR_ACCURACY guardnumber[3] 70
SET_CHAR_SHOOT_RATE guardnumber[3] 50
   
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2628.508 2733.410 35.564 guardnumber[4] // Rocket Launcher guy 2
SET_CHAR_DECISION_MAKER guardnumber[4] dm_noduck_heist5
GIVE_WEAPON_TO_CHAR guardnumber[4] WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_STAY_IN_SAME_PLACE guardnumber[4] TRUE
SET_CHAR_ACCURACY guardnumber[4] 70
SET_CHAR_SHOOT_RATE guardnumber[4] 40

CREATE_CHAR PEDTYPE_MISSION1 ARMY 2595.279 2755.393 22.822 guardnumber[5] // rocket Launcher guy 3
SET_CHAR_HEADING guardnumber[5] 80.0
SET_CHAR_DECISION_MAKER guardnumber[5] dm_noduck_heist5
GIVE_WEAPON_TO_CHAR guardnumber[5] WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_STAY_IN_SAME_PLACE guardnumber[5] TRUE
SET_CHAR_ACCURACY guardnumber[5] 70
SET_CHAR_SHOOT_RATE guardnumber[5] 45
  
CREATE_CHAR PEDTYPE_MISSION1 ARMY 2658.506 2784.133 14.930 guardnumber[6] // Rocket Launcher guy 4
SET_CHAR_HEADING guardnumber[6] 54.818
SET_CHAR_DECISION_MAKER guardnumber[6] dm_noduck_heist5
GIVE_WEAPON_TO_CHAR guardnumber[6] WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_STAY_IN_SAME_PLACE guardnumber[6] TRUE
SET_CHAR_ACCURACY guardnumber[6] 70
SET_CHAR_SHOOT_RATE guardnumber[6] 50

// Car to drive out of compound
CREATE_CAR PATRIOT 2520.737 2769.963 9.816 gate_drive_car_heist5
SET_CAR_HEADING gate_drive_car_heist5 0.563
CREATE_CHAR_INSIDE_CAR gate_drive_car_heist5 PEDTYPE_MISSION1 ARMY driver_gate_car_heist5
SET_CHAR_DECISION_MAKER driver_gate_car_heist5 dm_empty_heist5
GIVE_WEAPON_TO_CHAR driver_gate_car_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
SET_CHAR_ACCURACY driver_gate_car_heist5 70
SET_CHAR_SHOOT_RATE driver_gate_car_heist5 40
CAR_SET_IDLE gate_drive_car_heist5


// waiting for the player to destroy all the guards and helicopters 

WHILE NOT counter_backup_dead_heist5 = 2
    
	WAIT 0

	IF flag_base_alerted_heist5 >= 1

		IF alarm_loop_on_heist5 = 0			
			SET_MISSION_AUDIO_POSITION 3 2502.943 2778.928 10.820
			PLAY_MISSION_AUDIO 3
			alarm_loop_on_heist5 = 1
		ENDIF

		IF flag_gateguard1_got_ai_heist5 = 1

			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_THIS_PRINT (HE5_BD)
				flag_gateguard1_got_ai_heist5 = 2		
			ENDIF

		ENDIF
	ENDIF
		
		/* this resets the jeep coming out of the gate
		IF NOT IS_CAR_DEAD gate_drive_car_heist5
			IF NOT LOCATE_CAR_3D gate_drive_car_heist5 2506.3396 2772.6904 9.8203 420.0 420.0 100.0 FALSE

				// Car to drive out of compound
				CREATE_CAR PATRIOT 2520.737 2769.963 9.816 gate_drive_car_heist5
				SET_CAR_HEADING gate_drive_car_heist5 0.563
				CREATE_CHAR_INSIDE_CAR gate_drive_car_heist5 PEDTYPE_MISSION1 ARMY driver_gate_car_heist5
				SET_CHAR_DECISION_MAKER driver_gate_car_heist5 dm_empty_heist5
				GIVE_WEAPON_TO_CHAR driver_gate_car_heist5 WEAPONTYPE_M4 30000 // Set to infinate ammo
				SET_CHAR_ACCURACY driver_gate_car_heist5 70
				SET_CHAR_SHOOT_RATE driver_gate_car_heist5 40
				CAR_SET_IDLE gate_drive_car_heist5

				gate_drive_car_ai_heist5 = 0
			
			ENDIF
		ENDIF
		*/


	IF gate_driver_attacking_player = 0

		IF gate_drive_car_ai_heist5 = 0

			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2502.702 2778.550 9.868 80.0 80.0 80.0 FALSE
			OR player_entered_depot_heist5 = 1
			OR flag_base_alerted_heist5 = 1  

				IF NOT IS_CAR_DEAD gate_drive_car_heist5
				
					IF NOT IS_CHAR_DEAD driver_gate_car_heist5
					
						IF IS_CHAR_IN_CAR driver_gate_car_heist5 gate_drive_car_heist5

							IF flag_base_alerted_heist5 >= 1

								IF gate_driver_attacking_player = 0
									TASK_KILL_CHAR_ON_FOOT driver_gate_car_heist5 scplayer
									gate_stay_open = 0
									gate_driver_attacking_player = 1
								ENDIF

							ENDIF

							IF gate_driver_attacking_player = 0
						   		TASK_CAR_DRIVE_TO_COORD driver_gate_car_heist5 gate_drive_car_heist5 2506.3396 2772.6904 9.8203 10.0 MODE_ACCURATE 0 DRIVINGMODE_PLOUGHTHROUGH 		
						   		CLEAR_AREA 2501.4944 2775.1995 9.8203 3.0 FALSE 
								gate_drive_car_ai_heist5 = 1
							ENDIF

						ELSE

							IF gate_driver_attacking_player = 0
								TASK_KILL_CHAR_ON_FOOT driver_gate_car_heist5 scplayer
								gate_stay_open = 0
								gate_driver_attacking_player = 1
							ENDIF
							
						ENDIF
						
					ENDIF
					
				ENDIF

			ENDIF
			
		ENDIF

		IF gate_drive_car_ai_heist5 = 1 

			IF NOT IS_CAR_DEAD gate_drive_car_heist5
				
				IF NOT IS_CHAR_DEAD driver_gate_car_heist5
					
					IF IS_CHAR_IN_CAR driver_gate_car_heist5 gate_drive_car_heist5

						IF flag_base_alerted_heist5 >= 1

							IF gate_driver_attacking_player = 0
								TASK_KILL_CHAR_ON_FOOT driver_gate_car_heist5 scplayer
								gate_stay_open = 0
								gate_driver_attacking_player = 1
							ENDIF

						ENDIF

						IF gate_driver_attacking_player = 0

							IF LOCATE_CAR_3D gate_drive_car_heist5 2506.3396 2772.6904 9.8203 2.0 2.0 2.0 FALSE
								open_gate_now_flag = 1
								gate_stay_open = 1
								gate_drive_car_ai_heist5 = 2
							ENDIF

						ENDIF
						
					ELSE
					
						IF gate_driver_attacking_player = 0
							TASK_KILL_CHAR_ON_FOOT driver_gate_car_heist5 scplayer
							gate_stay_open = 0
							gate_driver_attacking_player = 1
						ENDIF 									   																
										
					ENDIF
						
				ENDIF

			ENDIF
		
		ENDIF

		IF gate_drive_car_ai_heist5 = 2

			IF NOT IS_CAR_DEAD gate_drive_car_heist5

				IF NOT IS_CAR_IN_AREA_3D gate_drive_car_heist5 2497.387 2858.018 8.0 2676.413 2683.661 60.0 FALSE
					CLEAR_AREA 2497.2893 2772.6062 9.8262 5.0 FALSE 
					gate_stay_open = 0	
				ENDIF
				
				IF NOT IS_CHAR_DEAD driver_gate_car_heist5
					
					IF IS_CHAR_IN_CAR driver_gate_car_heist5 gate_drive_car_heist5

						IF open_gate_now_flag = 3

							IF gate_driver_attacking_player = 0
								TASK_CAR_DRIVE_TO_COORD driver_gate_car_heist5 gate_drive_car_heist5 2472.1882 2775.9163 9.7097 20.0 MODE_ACCURATE 0 DRIVINGMODE_AVOIDCARS 		
								CLEAR_AREA 2472.1882 2775.9163 9.7097 3.0 FALSE 
								gate_drive_car_ai_heist5 = 3
							ENDIF

						ELSE

							IF flag_base_alerted_heist5 >= 1

								IF gate_driver_attacking_player = 0
									TASK_KILL_CHAR_ON_FOOT driver_gate_car_heist5 scplayer
									gate_stay_open = 0
									gate_driver_attacking_player = 1
								ENDIF

							ENDIF
							
						ENDIF

					ELSE

						IF gate_driver_attacking_player = 0
							TASK_KILL_CHAR_ON_FOOT driver_gate_car_heist5 scplayer
							gate_stay_open = 0
							gate_driver_attacking_player = 1
						ENDIF

					ENDIF

				ENDIF

			ELSE
				CLEAR_AREA 2497.2893 2772.6062 9.8262 5.0 FALSE
				gate_stay_open = 0
			ENDIF

		ENDIF

		IF gate_drive_car_ai_heist5 = 3
		
			IF NOT IS_CAR_DEAD gate_drive_car_heist5

				IF NOT IS_CAR_IN_AREA_3D gate_drive_car_heist5 2497.387 2858.018 8.0 2676.413 2683.661 60.0 FALSE
					CLEAR_AREA 2497.2893 2772.6062 9.8262 5.0 FALSE 
					gate_stay_open = 0	
				ENDIF
				
				IF NOT IS_CHAR_DEAD driver_gate_car_heist5
					
					IF IS_CHAR_IN_CAR driver_gate_car_heist5 gate_drive_car_heist5		

						IF LOCATE_CAR_3D gate_drive_car_heist5 2472.1882 2775.9163 9.7097 2.0 2.0 2.0 FALSE
							TASK_CAR_DRIVE_WANDER driver_gate_car_heist5 gate_drive_car_heist5 30.0 2
							gate_drive_car_ai_heist5 = 4
						ENDIF

					ELSE

						IF gate_driver_attacking_player = 0
							TASK_KILL_CHAR_ON_FOOT driver_gate_car_heist5 scplayer
							gate_stay_open = 0
							gate_driver_attacking_player = 1
						ENDIF

					ENDIF

				ENDIF

			ELSE
				CLEAR_AREA 2497.2893 2772.6062 9.8262 5.0 FALSE 
				gate_stay_open = 0
			ENDIF

		ENDIF

		IF gate_stay_open = 1 

			IF gate_drive_car_ai_heist5 <= 4

				IF NOT IS_CAR_DEAD gate_drive_car_heist5 
				 	
					IF NOT IS_CAR_IN_AREA_3D gate_drive_car_heist5 2497.387 2858.018 8.0 2676.413 2683.661 60.0 FALSE
						CLEAR_AREA 2497.2893 2772.6062 9.8262 5.0 FALSE 
						gate_stay_open = 0	
					ENDIF
					
				ELSE
					CLEAR_AREA 2497.2893 2772.6062 9.8262 5.0 FALSE 
					gate_stay_open = 0
				ENDIF 

			ENDIF

		ENDIF

	ENDIF
	
	IF player_entered_depot_heist5 = 0 
	 	
		IF IS_CHAR_IN_AREA_3D scplayer 2497.387 2858.018 8.0 2676.413 2683.661 100.0 FALSE 
			REMOVE_BLIP dummy_blip_heist5

			IF NOT IS_CAR_DEAD heli_heist5
				ADD_BLIP_FOR_CAR heli_heist5 heli_blip_heist5
				SET_BLIP_AS_FRIENDLY heli_blip_heist5 TRUE
//				PRINT (HM5_19) 8000 1 //"Steal the helicopter!"
			ELSE
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2
				PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
				GOTO mission_heist5_failed
			ENDIF

			CLEAR_WANTED_LEVEL player1
			SET_WANTED_MULTIPLIER 0.0 

			player_entered_depot_heist5 = 1		
		ENDIF

	ENDIF
		
	GOSUB array_guard_check_heist5
   	
	GOSUB guard_death_checks_heist5
	
	IF player_entered_depot_heist5 = 1
		 		 
		GOSUB guard1_code_heist5
		 
		GOSUB rocket_check_heist5
												
		IF flag_base_alerted_heist5 = 1

			IF guards_killing_player_heist5 = 0
				GOSUB guard_checks_heist5
			ELSE
				GOSUB warehouse_area_checks_heist5
			ENDIF

		ENDIF
		
	ENDIF	
   	
	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

	IF IS_CHAR_IN_AREA_ON_FOOT_3D scplayer 2656.523 2782.107 17.0 2594.178 2695.773 60.0 FALSE
 
		IF flag_done_mid_cleanup_heist5 = 0
			GOSUB mid_mission_cleanup_heist5
			flag_done_mid_cleanup_heist5 = 1
		ENDIF 

	ENDIF

	IF flag_done_mid_cleanup_heist5 = 1

		IF IS_CHAR_IN_AREA_ON_FOOT_3D scplayer 2646.055 2782.106 21.0 2594.268 2724.261 60.0 FALSE
			flag_done_mid_cleanup_heist5 = 2
		ENDIF

	ENDIF

	IF flag_done_mid_cleanup_heist5 = 2

		IF flag_player_at_minigun = 0
			CREATE_CAR hunter 2253.509 2806.941 15.0 chopper1_backup_heist5
			ADD_BLIP_FOR_CAR chopper1_backup_heist5 chopper1_backup_blip_heist5
			SET_CAR_HEALTH chopper1_backup_heist5 4000 
			CREATE_CHAR_INSIDE_CAR chopper1_backup_heist5 PEDTYPE_MISSION1 ARMY heli_pilot1_heist5
			SET_CAR_FORWARD_SPEED chopper1_backup_heist5 20.0
			SELECT_WEAPONS_FOR_VEHICLE chopper1_backup_heist5 SELECTEDWEAPON_GUNS
			HELI_ATTACK_PLAYER chopper1_backup_heist5 player1 50.0
			ADD_STUCK_CAR_CHECK chopper1_backup_heist5 2.0 5000

			CREATE_CAR hunter 2306.756 2806.941 15.0 chopper2_backup_heist5
			ADD_BLIP_FOR_CAR chopper2_backup_heist5 chopper2_backup_blip_heist5
			SET_CAR_HEALTH chopper2_backup_heist5 4000
			CREATE_CHAR_INSIDE_CAR chopper2_backup_heist5 PEDTYPE_MISSION1 ARMY heli_pilot2_heist5
			SET_CAR_FORWARD_SPEED chopper2_backup_heist5 20.0
			SELECT_WEAPONS_FOR_VEHICLE chopper2_backup_heist5 SELECTEDWEAPON_GUNS
			HELI_ATTACK_PLAYER chopper2_backup_heist5 player1 50.0
			ADD_STUCK_CAR_CHECK chopper2_backup_heist5 2.0 5000
		   							
			PRINT_NOW (HM5_6) 12000 1 //"They have sent two gunships to intercept you, use the mini gun to destroy them."
			ADD_BLIP_FOR_COORD 2595.549 2758.040 22.832 minigun_blip_heist5
			ADD_SPHERE 2596.589 2758.158 22.862 1.0 sphere_heist5
			TIMERB = 0
			helis_created_heist5 = 1
			flag_player_at_minigun = 1


		ENDIF

		IF flag_player_at_minigun = 1
				   		
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 2596.589 2758.158 22.862 1.0 1.0 3.0 FALSE

				IF flag_mingun_help_heist5 = 0
					PRINT_HELP HM5_20
					flag_mingun_help_heist5 = 1
				ENDIF

				IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						
					IF flag_player_using_gun_heist5 = 0
						CLEAR_THIS_PRINT HM5_20
						CLEAR_HELP
						REMOVE_BLIP minigun_blip_heist5
						REMOVE_SPHERE sphere_heist5
						ATTACH_CHAR_TO_OBJECT scplayer minigun_heist5 0.0 0.0 2.0  FACING_LEFT 360.0 WEAPONTYPE_MINIGUN
						SET_OBJECT_VISIBLE minigun_heist5 FALSE
						flag_player_at_minigun = 2
						flag_player_using_gun_heist5 = 1
					ENDIF

				ENDIF

			ELSE
				CLEAR_HELP
				flag_mingun_help_heist5 = 0
			ENDIF
			
		ENDIF
		
		IF flag_player_at_minigun = 2
			IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
				flag_player_at_minigun = 3
			ENDIF
		ENDIF	
	
		IF flag_player_at_minigun = 3

			IF flag_leave_gun_heist5 = 0
				PRINT_HELP HM5_21  
				flag_leave_gun_heist5 = 1
			ENDIF

			IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
				flag_player_at_minigun = 4
			ENDIF

		ENDIF

		IF flag_player_at_minigun = 4 
	
			IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
			
				IF flag_player_using_gun_heist5 = 1
				
					DETACH_CHAR_FROM_CAR scplayer
					CLEAR_AREA 2596.129 2757.112 22.862 1.0 FALSE 
					SET_CHAR_COORDINATES scplayer 2596.129 2757.112 22.862
					SET_CHAR_HEADING scplayer 217.0
					SET_OBJECT_VISIBLE minigun_heist5 TRUE
					CLEAR_THIS_PRINT HM5_21
					CLEAR_HELP
					
					REMOVE_BLIP minigun_blip_heist5
					REMOVE_SPHERE sphere_heist5
					ADD_BLIP_FOR_COORD 2595.549 2758.040 22.832 minigun_blip_heist5
					ADD_SPHERE 2596.589 2758.158 22.862 1.0 sphere_heist5
					flag_leave_gun_heist5 = 0
					flag_player_at_minigun = 1
					flag_player_using_gun_heist5 = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF helis_created_heist5 = 1

		IF chopper1_backup_dead_heist5 = 0
			
			IF IS_CAR_DEAD chopper1_backup_heist5 
				REMOVE_BLIP chopper1_backup_blip_heist5
				++ counter_backup_dead_heist5
				chopper1_backup_dead_heist5 = 1
			ELSE

				IF IS_CAR_STUCK chopper1_backup_heist5

					GET_CHAR_COORDINATES scplayer player_x player_y player_z

					warp_heli1X_heist5 = player_x + 80.0
					warp_heli1Y_heist5 = player_y + 80.0
					warp_heli1Z_heist5 = player_z + 50.0 
					SET_CAR_COORDINATES chopper1_backup_heist5 warp_heli1X_heist5 warp_heli1Y_heist5 warp_heli1Z_heist5
			  	ENDIF
			ENDIF
		ENDIF
	
		IF chopper2_backup_dead_heist5 = 0
		
			IF IS_CAR_DEAD chopper2_backup_heist5 
				REMOVE_BLIP chopper2_backup_blip_heist5
				++ counter_backup_dead_heist5
				chopper2_backup_dead_heist5 = 1
			ELSE

				IF IS_CAR_STUCK chopper2_backup_heist5

					GET_CHAR_COORDINATES scplayer player_x player_y player_z

					warp_heli2X_heist5 = player_x + 90.0
					warp_heli2Y_heist5 = player_y + 90.0
					warp_heli2Z_heist5 = player_z + 60.0 
					SET_CAR_COORDINATES chopper2_backup_heist5 warp_heli2X_heist5 warp_heli2Y_heist5 warp_heli2Z_heist5 
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	
	IF TIMERB > 240000 // 4mins

		IF chopper1_backup_dead_heist5 = 0
			
			IF NOT IS_CAR_DEAD chopper1_backup_heist5
				EXPLODE_CAR chopper1_backup_heist5
				EXPLODE_CAR chopper1_backup_heist5 // needs to be twice to get the car to explode instantly  
				REMOVE_BLIP chopper1_backup_blip_heist5 
				++ counter_backup_dead_heist5
				chopper1_backup_dead_heist5 = 1
			ENDIF

		ENDIF

		IF chopper2_backup_dead_heist5 = 0
			
			IF NOT IS_CAR_DEAD chopper2_backup_heist5
				EXPLODE_CAR chopper2_backup_heist5
				EXPLODE_CAR chopper2_backup_heist5 
				REMOVE_BLIP chopper2_backup_blip_heist5 
				++ counter_backup_dead_heist5
				chopper2_backup_dead_heist5 = 1
			ENDIF
		ENDIF
	ENDIF
ENDWHILE


gate_stay_open = 0

CLEAR_THIS_PRINT HM5_20
CLEAR_THIS_PRINT HM5_21

CLEAR_HELP

REMOVE_SPHERE sphere_heist5

REMOVE_BLIP dummy_blip_heist5
REMOVE_BLIP minigun_blip_heist5
REMOVE_BLIP chopper1_backup_blip_heist5
REMOVE_BLIP chopper2_backup_blip_heist5

MARK_MODEL_AS_NO_LONGER_NEEDED HUNTER
MARK_MODEL_AS_NO_LONGER_NEEDED minigun

IF flag_player_using_gun_heist5 = 1
	DETACH_CHAR_FROM_CAR scplayer
	CLEAR_AREA 2596.129 2757.112 22.862 1.0 FALSE 
	SET_CHAR_COORDINATES scplayer 2596.129 2757.112 22.862
	SET_CHAR_HEADING scplayer 217.0
ENDIF

IF DOES_OBJECT_EXIST minigun_heist5 
	SET_OBJECT_VISIBLE minigun_heist5 TRUE
ENDIF

IF NOT IS_CAR_DEAD heli_heist5 
	LOCK_CAR_DOORS heli_heist5 CARLOCK_UNLOCKED
	SET_CAR_PROOFS heli_heist5 FALSE FALSE FALSE FALSE FALSE
	FREEZE_CAR_POSITION heli_heist5 FALSE
ELSE
	PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
	GOTO mission_heist5_failed
ENDIF

PRINT_NOW (HM5_7) 8000 1 //"The coast is clear get to the chopper and get out of here."

// Waiting for the char to be in the helicopter
WHILE NOT IS_CHAR_IN_CAR scplayer heli_heist5

	WAIT 0

	GOSUB array_guard_check_heist5
   	 
	GOSUB guard_death_checks_heist5	
	
	IF player_entered_depot_heist5 = 1
		 		 
//		GOSUB guard1_code_heist5

		IF flag_base_alerted_heist5 = 1

			IF guards_killing_player_heist5 = 0
				GOSUB guard_checks_heist5
			ELSE
				GOSUB warehouse_area_checks_heist5
			ENDIF

		ENDIF
		
	ENDIF
		
	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ELSE

		IF LOCATE_CHAR_ON_FOOT_3D scplayer 2614.603 2720.745 35.563 4.0 4.0 4.0 FALSE
			LOCK_CAR_DOORS heli_heist5 CARLOCK_UNLOCKED
			SET_CAR_PROOFS heli_heist5 FALSE FALSE FALSE FALSE FALSE
			FREEZE_CAR_POSITION heli_heist5 FALSE
		ENDIF

	ENDIF
	 
ENDWHILE

IF alarm_loop_on_heist5 = 1
	CLEAR_MISSION_AUDIO 3
	alarm_loop_on_heist5 = 0
ENDIF

CLEAR_WANTED_LEVEL player1 

REMOVE_BLIP heli_blip_heist5
MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA

// creates the bank van
CREATE_CAR SECURICA 2847.3911 904.2733 9.7500 bankvan1_heist5  // 2847.417 898.813 9.750
//SET_CAR_PROOFS bankvan1_heist5 FALSE FALSE FALSE TRUE FALSE
SET_UPSIDEDOWN_CAR_NOT_DAMAGED bankvan1_heist5 TRUE
LOCK_CAR_DOORS bankvan1_heist5 CARLOCK_LOCKED // CARLOCK_FORCE_SHUT_DOORS 
SET_CAR_HEADING bankvan1_heist5 353.511
//SET_CAR_STRONG bankvan1_heist5 TRUE
ADD_BLIP_FOR_CAR bankvan1_heist5 bankvan1_blip_heist5
SET_BLIP_AS_FRIENDLY bankvan1_blip_heist5 TRUE

GET_CAR_UPRIGHT_VALUE bankvan1_heist5 bankvan1_upright_value_heist5
  
bankvan1_blip_on_heist5 = 1

PRINT_NOW (HM5_8) 8000 1//"Go to the depot and pick up the bank van using the helicopters winch."

TIMERA = 0

WHILE correct_vehicle_on_winch_heist5 = 0 

	WAIT 0

	IF bankvan1_dead_heist5 = 0
	
		IF IS_CAR_DEAD bankvan1_heist5

			IF bankvan1_blip_on_heist5 = 1
				REMOVE_BLIP bankvan1_blip_heist5
				bankvan1_blip_on_heist5 = 0
			ENDIF

			PRINT_NOW (HM5_9) 8000 1//"You destroyed the van."
			GOTO mission_heist5_failed
			bankvan1_dead_heist5 = 1
		ELSE
			GET_CAR_HEALTH bankvan1_heist5 bankvan1_health_heist5
			bankvan1_health_heist5 = bankvan1_health_heist5 / 10

			GET_CAR_UPRIGHT_VALUE bankvan1_heist5 bankvan1_upright_value_heist5

		ENDIF
		
	ENDIF
		
	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ELSE
	   
		IF IS_CHAR_IN_CAR scplayer heli_heist5

			IF flag_winch_message_heist5 = 0

				IF TIMERA >= 5000
					CLEAR_THIS_PRINT (HM5_8)
					PRINT_HELP (WINCH_V)  
					flag_winch_message_heist5 = 1
				ENDIF

			ENDIF

			IF flag_cleanup2_done_heist5 = 0

				IF NOT IS_CHAR_IN_AREA_3D scplayer 2497.387 2858.018 8.0 2676.413 2683.661 80.0 FALSE 
					GOSUB cleanup2_heist5
				ENDIF

			ENDIF

			GRAB_ENTITY_ON_WINCH heli_heist5 vehicle_on_winch_heist5 char_on_winch_heist5 object_on_winch_heist5

			IF NOT IS_CAR_DEAD bankvan1_heist5
				
				IF vehicle_on_winch_heist5 = bankvan1_heist5
					GET_CAR_HEALTH bankvan1_heist5 bankvan1_health_heist5
					bankvan1_health_heist5 = bankvan1_health_heist5 / 10
//					DISPLAY_ONSCREEN_COUNTER_WITH_STRING bankvan1_health_heist5 COUNTER_DISPLAY_BAR (HM5_11)
					correct_vehicle_on_winch_heist5 = 1	
				ENDIF
				
			ENDIF
						 
			
			IF flag_player_had_message_heist5 = 1
				blob_flag = 1
				REMOVE_BLIP heli_blip_heist5
				
				IF NOT IS_CAR_DEAD bankvan1_heist5
					
					IF bankvan1_blip_on_heist5 = 0
						ADD_BLIP_FOR_CAR bankvan1_heist5 bankvan1_blip_heist5
						SET_BLIP_AS_FRIENDLY bankvan1_blip_heist5 TRUE
						PRINT_NOW (HM5_10) 8000 1 //"Pick up the van!"
						bankvan1_blip_on_heist5 = 1
					ENDIF

				ENDIF							 
			   
				flag_player_had_message_heist5 = 0
			ENDIF

		ELSE
		
			IF flag_player_had_message_heist5 = 0
				blob_flag = 0

				IF NOT IS_CAR_DEAD bankvan1_heist5
					
					IF bankvan1_blip_on_heist5 = 1
			 			REMOVE_BLIP bankvan1_blip_heist5
						bankvan1_blip_on_heist5 = 0
					ENDIF

				ENDIF
								
				ADD_BLIP_FOR_CAR heli_heist5 heli_blip_heist5
				SET_BLIP_AS_FRIENDLY heli_blip_heist5 TRUE
				PRINT_NOW (IN_VEH) 5000 1 //"Get back in the helicopter!"
				flag_player_had_message_heist5 = 1 
			ENDIF
					
		ENDIF

	ENDIF
	
ENDWHILE

IF NOT IS_CAR_DEAD bankvan1_heist5

	IF bankvan1_blip_on_heist5 = 1
		REMOVE_BLIP bankvan1_blip_heist5
		SET_CAR_PROOFS bankvan1_heist5 FALSE FALSE FALSE FALSE FALSE
		bankvan1_blip_on_heist5 = 0
	ENDIF

ELSE
	IF bankvan1_blip_on_heist5 = 1
		REMOVE_BLIP bankvan1_blip_heist5
		bankvan1_blip_on_heist5 = 0
	ENDIF

	PRINT_NOW (HM5_9) 8000 1//"You destroyed the van."
	GOTO mission_heist5_failed
	bankvan1_dead_heist5 = 1

ENDIF

// Requests the wuzi model 
MARK_MODEL_AS_NO_LONGER_NEEDED ARMY
REQUEST_MODEL TRIADA 

PRINT_NOW (HM5_2) 5000 1 //"Good now take the helicopter to the Aircraft Graveyard in the desert."

ADD_BLIP_FOR_COORD locateX_heist5 locateY_heist5 locateZ_heist5 blip_coord1_heist5
flag_end_blip_on_heist5 = 1

flag_player_had_message_heist5 = 0
bankvan1_blip_on_heist5 = 0

blob_flag = 1

// waiting for the heli and van to be in correct location
WHILE NOT flag_heli_wheels_cool_heist5= 1
OR NOT LOCATE_STOPPED_CAR_3D bankvan1_heist5 locateX_heist5 locateY_heist5 locateZ_heist5 6.0 6.0 5.0 blob_flag 
OR NOT HAS_MODEL_LOADED TRIADA 

	WAIT 0
   
	IF bankvan1_dead_heist5 = 0
	
		IF IS_CAR_DEAD bankvan1_heist5

			IF bankvan1_blip_on_heist5 = 1
				REMOVE_BLIP bankvan1_blip_heist5
				bankvan1_blip_on_heist5 = 0
			ENDIF

			PRINT_NOW (HM5_9) 8000 1//"You destroyed the van."
			GOTO mission_heist5_failed
			bankvan1_dead_heist5 = 1
		ELSE
			GET_CAR_HEALTH bankvan1_heist5 bankvan1_health_heist5
			bankvan1_health_heist5 = bankvan1_health_heist5 / 10

			GET_CAR_UPRIGHT_VALUE bankvan1_heist5 bankvan1_upright_value_heist5

			IF LOCATE_STOPPED_CAR_3D bankvan1_heist5 locateX_heist5 locateY_heist5 locateZ_heist5 6.0 6.0 5.0 FALSE
				
				IF flag_wheels_message_heist5 = 0

					IF  bankvan1_upright_value_heist5 <= 1.0
					AND bankvan1_upright_value_heist5 > 0.8

						IF NOT IS_CAR_IN_AIR_PROPER bankvan1_heist5
							WINCH_CAN_PICK_VEHICLE_UP bankvan1_heist5 FALSE
//							CLEAR_ONSCREEN_COUNTER bankvan1_health_heist5
							flag_heli_wheels_cool_heist5 = 1
						ELSE
							flag_heli_wheels_cool_heist5 = 0
						ENDIF
						
					ELSE
						flag_heli_wheels_cool_heist5 = 0		
					ENDIF

					IF flag_heli_wheels_cool_heist5 = 0
						PRINT_NOW (HM5_12) 8000 1 //"The van has to be on it's wheels!"
						flag_wheels_message_heist5 = 1
					ENDIF

				ELSE

					IF flag_heli_wheels_cool_heist5 = 1
						CLEAR_THIS_PRINT (HM5_12)
					ENDIF

				ENDIF
				
			ELSE
				flag_wheels_message_heist5 = 0
			ENDIF  

		ENDIF
		
	ENDIF
		
   	   		
	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ELSE
	   
		IF IS_CHAR_IN_CAR scplayer heli_heist5

			GRAB_ENTITY_ON_WINCH heli_heist5 vehicle_on_winch_heist5 char_on_winch_heist5 object_on_winch_heist5

			IF NOT IS_CAR_DEAD bankvan1_heist5
				
				IF vehicle_on_winch_heist5 = bankvan1_heist5

					IF bankvan1_blip_on_heist5 = 1
						REMOVE_BLIP bankvan1_blip_heist5
						GET_CAR_HEALTH bankvan1_heist5 bankvan1_health_heist5
						bankvan1_health_heist5 = bankvan1_health_heist5 / 10
//						DISPLAY_ONSCREEN_COUNTER_WITH_STRING bankvan1_health_heist5 COUNTER_DISPLAY_BAR (HM5_11)
						bankvan1_blip_on_heist5 = 0
					ENDIF

					IF flag_end_blip_on_heist5 = 0
						REMOVE_BLIP blip_coord1_heist5   
						ADD_BLIP_FOR_COORD locateX_heist5 locateY_heist5 locateZ_heist5 blip_coord1_heist5
						PRINT_NOW (HM5_2) 5000 1 //"Good now take the helicopter to the Aircraft Graveyard in the desert."
						blob_flag = 1
						flag_end_blip_on_heist5 = 1
					ENDIF

					van_in_bad_area_heist5 = 0

					correct_vehicle_on_winch_heist5 = 1
						
				ELSE

					GET_CAR_COORDINATES bankvan1_heist5 bankvan1X_heist5 bankvan1Y_heist5 bankvan1Z_heist5
					GET_CAR_COORDINATES heli_heist5	heli1X_heist5 heli1Y_heist5	heli1Z_heist5
					GET_DISTANCE_BETWEEN_COORDS_2D heli1X_heist5 heli1Y_heist5 bankvan1X_heist5 bankvan1Y_heist5 dis_heli_van_heist5

					IF dis_heli_van_heist5 < 10.0

						IF NOT IS_LINE_OF_SIGHT_CLEAR heli1X_heist5 heli1Y_heist5 heli1Z_heist5 bankvan1X_heist5 bankvan1Y_heist5 bankvan1Z_heist5 TRUE FALSE FALSE FALSE FALSE

							IF van_in_bad_area_heist5 = 0
								TIMERA = 0
								van_in_bad_area_heist5 = 1
							ELSE
								
								IF TIMERA >= 10000 
									PRINT_NOW (HM5_14) 8000 1 //"You have got the van stuck!
									GOTO mission_heist5_failed
								ENDIF

							ENDIF

						ENDIF

					ENDIF

					correct_vehicle_on_winch_heist5 = 0

					IF flag_end_blip_on_heist5 = 1
						REMOVE_BLIP blip_coord1_heist5
						flag_end_blip_on_heist5 = 0
					ENDIF

					IF bankvan1_blip_on_heist5 = 0
						REMOVE_BLIP bankvan1_blip_heist5 
						ADD_BLIP_FOR_CAR bankvan1_heist5 bankvan1_blip_heist5
						SET_BLIP_AS_FRIENDLY bankvan1_blip_heist5 TRUE
						PRINT_NOW (HM5_10) 8000 1 //"Pick up the bank van."
						blob_flag = 0
//						CLEAR_ONSCREEN_COUNTER bankvan1_health_heist5						
						bankvan1_blip_on_heist5 = 1
					ENDIF

				ENDIF
				
			ENDIF					   
			
			IF flag_player_had_message_heist5 = 1
				
				REMOVE_BLIP heli_blip_heist5	
				
				IF correct_vehicle_on_winch_heist5 = 1
				
					IF flag_end_blip_on_heist5 = 0
						REMOVE_BLIP blip_coord1_heist5   
						ADD_BLIP_FOR_COORD locateX_heist5 locateY_heist5 locateZ_heist5 blip_coord1_heist5
						//PRINT_NOW (HM5_2) 5000 1 //"Good now take the helicopter to the Aircraft Graveyard in the desert."
						//blob_flag = 1
						flag_end_blip_on_heist5 = 1
					ENDIF

					PRINT_NOW (HM5_2) 5000 1 //"Good now take the helicopter to the Aircraft Graveyard in the desert."
					blob_flag = 1
					flag_player_had_message_heist5 = 0
				ELSE

					IF NOT IS_CAR_DEAD bankvan1_heist5

						IF bankvan1_blip_on_heist5 = 0
							REMOVE_BLIP bankvan1_blip_heist5 
							ADD_BLIP_FOR_CAR bankvan1_heist5 bankvan1_blip_heist5
							SET_BLIP_AS_FRIENDLY bankvan1_blip_heist5 TRUE
							PRINT_NOW (HM5_10) 8000 1 //"Pick up the bank van."
							blob_flag = 0
//							CLEAR_ONSCREEN_COUNTER bankvan1_health_heist5

							GET_CAR_COORDINATES bankvan1_heist5 bankvan1X_heist5 bankvan1Y_heist5 bankvan1Z_heist5
							GET_CAR_COORDINATES heli_heist5	heli1X_heist5 heli1Y_heist5	heli1Z_heist5
							GET_DISTANCE_BETWEEN_COORDS_2D heli1X_heist5 heli1Y_heist5 bankvan1X_heist5 bankvan1Y_heist5 dis_heli_van_heist5

							IF NOT IS_LINE_OF_SIGHT_CLEAR heli1X_heist5 heli1Y_heist5 heli1Z_heist5 bankvan1X_heist5 bankvan1Y_heist5 bankvan1Z_heist5 TRUE FALSE FALSE FALSE FALSE

								IF van_in_bad_area_heist5 = 0
									TIMERA = 0
									van_in_bad_area_heist5 = 1
								ELSE
									
									IF TIMERA >= 10000 
										PRINT_NOW (HM5_14) 8000 1 //"You have got the van stuck!
										GOTO mission_heist5_failed
									ENDIF

								ENDIF

							ENDIF

							bankvan1_blip_on_heist5 = 1
						ENDIF

					ENDIF
					
					flag_player_had_message_heist5 = 0								

				ENDIF
								
			ENDIF

		// player not in helicopter
		ELSE
		
			IF flag_player_had_message_heist5 = 0
				blob_flag = 0

				IF flag_end_blip_on_heist5 = 1
					REMOVE_BLIP blip_coord1_heist5
					flag_end_blip_on_heist5 = 0
				ENDIF

				IF bankvan1_blip_on_heist5 = 1
					REMOVE_BLIP bankvan1_blip_heist5
					bankvan1_blip_on_heist5 = 0
				ENDIF
				
				REMOVE_BLIP heli_blip_heist5 
				ADD_BLIP_FOR_CAR heli_heist5 heli_blip_heist5
				SET_BLIP_AS_FRIENDLY heli_blip_heist5 TRUE
				PRINT_NOW (IN_VEH) 5000 1 //"Get back in the helicopter!"
				flag_player_had_message_heist5 = 1 
			ENDIF
					
		ENDIF

	ENDIF
						
ENDWHILE

CLEAR_THIS_PRINT (HM5_12) // message about van not being on wheels

REMOVE_BLIP blip_coord1_heist5

IF NOT IS_CAR_DEAD bankvan1_heist5
	REMOVE_BLIP bankvan1_blip_heist5

	IF NOT IS_CAR_DEAD heli_heist5
		RELEASE_ENTITY_FROM_WINCH heli_heist5
	ELSE
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

ELSE
	REMOVE_BLIP bankvan1_blip_heist5
	PRINT_NOW (HM5_9) 8000 1//"You destroyed the van."
	GOTO mission_heist5_failed
ENDIF

PRINT_NOW (HM5_13) 8000 1 //"Now land the helicopter."
ADD_BLIP_FOR_COORD heli_locateX_heist5 heli_locateY_heist5 heli_locateZ_heist5 heli_land_blip_heist5

flag_player_had_message_heist5 = 0
blob_flag = 1

LOAD_MISSION_AUDIO 1 SOUND_HE5_AA
LOAD_MISSION_AUDIO 2 SOUND_HE5_AB

WHILE NOT LOCATE_STOPPED_CAR_3D heli_heist5 heli_locateX_heist5 heli_locateY_heist5 heli_locateZ_heist5 20.0 20.0 4.0 blob_flag
OR NOT IS_CHAR_SITTING_IN_CAR scplayer heli_heist5
OR NOT HAS_MISSION_AUDIO_LOADED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2 

	WAIT 0

	IF IS_CAR_DEAD bankvan1_heist5
		REMOVE_BLIP bankvan1_blip_heist5  
		PRINT_NOW (HM5_9) 8000 1//"You destroyed the van."
		GOTO mission_heist5_failed
	ENDIF
	
	// helicopter checks   		
	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ELSE

		IF IS_CHAR_IN_CAR scplayer heli_heist5

			IF flag_player_had_message_heist5 = 1
				REMOVE_BLIP heli_blip_heist5  
				PRINT_NOW (HM5_13) 8000 1 //"Now land the helicopter."
				ADD_BLIP_FOR_COORD heli_locateX_heist5 heli_locateY_heist5 heli_locateZ_heist5 heli_land_blip_heist5
				blob_flag = 1
				flag_player_had_message_heist5 = 0
			ENDIF
	
		ELSE

			IF flag_player_had_message_heist5 = 0
				REMOVE_BLIP heli_land_blip_heist5
				ADD_BLIP_FOR_CAR heli_heist5 heli_blip_heist5
				SET_BLIP_AS_FRIENDLY heli_blip_heist5 TRUE
				PRINT_NOW (IN_VEH) 5000 1 //"Get back in the helicopter!"
				blob_flag = 0 
				flag_player_had_message_heist5 = 1
			ENDIF 

		ENDIF
	
	ENDIF 

ENDWHILE

SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL player1 OFF

REMOVE_BLIP heli_land_blip_heist5
REMOVE_BLIP heli_blip_heist5

// ********************************* END CUTSCENE WITH PLAYER AND WUZI AT AIRFIELD *****************

SET_POLICE_IGNORE_PLAYER player1 TRUE
SET_EVERYONE_IGNORE_PLAYER player1 TRUE

DELETE_CAR bankvan1_heist5
MARK_MODEL_AS_NO_LONGER_NEEDED SECURICA

MAKE_PLAYER_GANG_DISAPPEAR  

heli_locateX_heist5 = 365.870 
heli_locateY_heist5 = 2537.868

IF NOT IS_CAR_DEAD heli_heist5
	SET_CAR_COORDINATES heli_heist5 heli_locateX_heist5 heli_locateY_heist5 heli_locateZ_heist5
	SET_CAR_HEADING heli_heist5 214.0
	SET_CAR_PROOFS heli_heist5 TRUE TRUE TRUE TRUE TRUE 
ELSE
	PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
	GOTO mission_heist5_failed
ENDIF

SET_FIXED_CAMERA_POSITION 371.7336 2544.8562 16.6086 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 371.3514 2543.9441 16.7564 JUMP_CUT

WAIT 2000

SKIP_CUTSCENE_START

IF NOT IS_CAR_DEAD heli_heist5
	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
	TASK_LEAVE_CAR scplayer heli_heist5
ELSE
	PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
	GOTO mission_heist5_failed
ENDIF

WHILE IS_CHAR_IN_ANY_CAR scplayer

	WAIT 0

	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF
	
ENDWHILE

SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

CREATE_CHAR PEDTYPE_CIVMALE TRIADA 375.081 2535.866 15.640 wuzi_heist5
SET_CHAR_DECISION_MAKER wuzi_heist5 dm_empty_heist5
SET_CHAR_ONLY_DAMAGED_BY_PLAYER wuzi_heist5 TRUE
SET_CHAR_PROOFS wuzi_heist5 TRUE TRUE TRUE TRUE TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH wuzi_heist5 TRUE 

TASK_GO_STRAIGHT_TO_COORD scplayer 370.364 2538.753 15.643 PEDMOVE_WALK -1

WHILE NOT LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 370.364 2538.753 15.643 1.0 1.0 2.0 FALSE

	WAIT 0

	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

	IF IS_CHAR_DEAD wuzi_heist5
		PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
		GOTO mission_heist5_failed
	ENDIF
	
ENDWHILE

IF NOT IS_CHAR_DEAD wuzi_heist5
	TASK_GOTO_CHAR wuzi_heist5 scplayer -1 1.3
	PLAY_MISSION_AUDIO 1
	PRINT_NOW (HE5_AA) 10000 1 //"Heeey, CJ, you never cease to amaze me!"
	START_CHAR_FACIAL_TALK wuzi_heist5 999999
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  wuzi_heist5 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
	TASK_TURN_CHAR_TO_FACE_CHAR scplayer wuzi_heist5
ELSE
	PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
	GOTO mission_heist5_failed
ENDIF

TIMERA = 0						  

WHILE NOT LOCATE_CHAR_ON_FOOT_CHAR_3D wuzi_heist5 scplayer 1.2 1.2 2.0 FALSE
OR NOT HAS_MISSION_AUDIO_FINISHED 1

	WAIT 0

	IF IS_CAR_DEAD heli_heist5
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (HE5_AA)
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

	IF IS_CHAR_DEAD wuzi_heist5
		PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
		GOTO mission_heist5_failed
	ELSE
		
		IF TIMERA >= 15000
			SET_CHAR_COORDINATES wuzi_heist5 371.364 2538.753 15.643
		ENDIF 
	
	ENDIF
	
ENDWHILE

IF NOT IS_CHAR_DEAD wuzi_heist5
	CLEAR_CHAR_TASKS wuzi_heist5
ELSE
	PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
	GOTO mission_heist5_failed
ENDIF

CLEAR_MISSION_AUDIO 1
CLEAR_THIS_PRINT (HE5_AA)

IF NOT IS_CHAR_DEAD wuzi_heist5 
	STOP_CHAR_FACIAL_TALK wuzi_heist5
ELSE
	PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
	GOTO mission_heist5_failed
ENDIF

IF IS_PLAYER_PLAYING player1
	PLAY_MISSION_AUDIO 2
	PRINT_NOW (HE5_AB) 10000 1 //"Heh heh, where’s Woozie?"
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
	START_CHAR_FACIAL_TALK scplayer 999999

	IF NOT IS_CHAR_DEAD wuzi_heist5
		TASK_TURN_CHAR_TO_FACE_CHAR scplayer wuzi_heist5
	ELSE
		PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
		GOTO mission_heist5_failed
	ENDIF											   

ELSE
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_THIS_PRINT (HE5_AB)	
ENDIF
 
LOAD_MISSION_AUDIO 1 SOUND_HE5_AC

WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
OR NOT HAS_MISSION_AUDIO_LOADED 1

	WAIT 0
	
	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

	IF IS_CHAR_DEAD wuzi_heist5
		PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
		GOTO mission_heist5_failed
	ENDIF
	
	IF NOT IS_PLAYER_PLAYING player1
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (HE5_AB)
	ENDIF 
	
ENDWHILE

CLEAR_MISSION_AUDIO 2
CLEAR_THIS_PRINT (HE5_AB)
CLEAR_CHAR_TASKS scplayer
STOP_CHAR_FACIAL_TALK scplayer

IF NOT IS_CHAR_DEAD wuzi_heist5
	PLAY_MISSION_AUDIO 1
	PRINT_NOW (HE5_AC) 10000 1 //"He insisted on driving here himself. He could be anywhere."
	START_CHAR_FACIAL_TALK wuzi_heist5 999999
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  wuzi_heist5 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1  
ELSE
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_THIS_PRINT (HE5_AC)
	PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
	GOTO mission_heist5_failed
ENDIF
 
LOAD_MISSION_AUDIO 2 SOUND_HE5_AD

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2

	WAIT 0

	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

	IF IS_CHAR_DEAD wuzi_heist5
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (HE5_AC)
		PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
		GOTO mission_heist5_failed
	ENDIF
		
ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_THIS_PRINT (HE5_AC)

IF NOT IS_CHAR_DEAD wuzi_heist5 
	STOP_CHAR_FACIAL_TALK wuzi_heist5
ELSE
	PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
	GOTO mission_heist5_failed
ENDIF

IF NOT IS_CHAR_DEAD wuzi_heist5
	CLEAR_CHAR_TASKS wuzi_heist5
ELSE
	PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
	GOTO mission_heist5_failed
ENDIF

IF IS_PLAYER_PLAYING player1
	PLAY_MISSION_AUDIO 2
	PRINT_NOW (HE5_AD) 10000 1 //"Ok, dude, I’m out of here, I’ll see you guys later."
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
	START_CHAR_FACIAL_TALK scplayer 999999
ELSE
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_THIS_PRINT (HE5_AD)
ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 2

	WAIT 0

	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

	IF IS_CHAR_DEAD wuzi_heist5
		PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
		GOTO mission_heist5_failed
	ENDIF
	
	IF NOT IS_PLAYER_PLAYING player1
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (HE5_AD)
	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 2
CLEAR_THIS_PRINT (HE5_AD)
CLEAR_CHAR_TASKS scplayer
STOP_CHAR_FACIAL_TALK scplayer

IF NOT IS_CHAR_DEAD wuzi_heist5
	TASK_GO_STRAIGHT_TO_COORD wuzi_heist5 378.998 2534.098 15.624 PEDMOVE_WALK -1
ELSE
	PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
	GOTO mission_heist5_failed
ENDIF

WAIT 1200

TASK_GO_STRAIGHT_TO_COORD scplayer 379.998 2534.098 15.624 PEDMOVE_WALK -1

TIMERA = 0

WHILE TIMERA <= 3000
	
	WAIT 0

	IF IS_CAR_DEAD heli_heist5
		PRINT_NOW (HM5_3) 5000 1 //"You destroyed the copter!"
		GOTO mission_heist5_failed
	ENDIF

	IF IS_CHAR_DEAD wuzi_heist5
		PRINT_NOW (HM5_16) 8000 1 //"Wuzi is dead!"
		GOTO mission_heist5_failed
	ENDIF


ENDWHILE

DELETE_CAR heli_heist5
DELETE_CHAR wuzi_heist5 

SET_CHAR_COORDINATES scplayer 388.007 2532.546 15.594
SET_CAMERA_BEHIND_PLAYER

flag_watched_end_cutscene_heist5 = 1
SKIP_CUTSCENE_END

IF flag_watched_end_cutscene_heist5 = 0

	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_OUT

	WHILE GET_FADING_STATUS

		WAIT 0

	ENDWHILE

	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_HELP
	CLEAR_PRINTS

	SWITCH_WIDESCREEN OFF
	CLEAR_AREA 388.007 2532.546 15.594 5.0 TRUE
	
	IF IS_CHAR_IN_FLYING_VEHICLE scplayer
		STORE_CAR_CHAR_IS_IN scplayer stored_vehicle_heist5

		WARP_CHAR_FROM_CAR_TO_COORD scplayer 388.007 2532.546 15.594

		WHILE IS_CHAR_IN_FLYING_VEHICLE scplayer

			WAIT 0

			IF IS_CAR_DEAD heli_heist5
				
			ENDIF

			IF IS_CHAR_DEAD wuzi_heist5
				
			ENDIF

		ENDWHILE

		SET_CHAR_HEADING scplayer 270.0
		
	ELSE	 
		SET_CHAR_COORDINATES scplayer 388.007 2532.546 15.594
		SET_CHAR_HEADING scplayer 270.0
	ENDIF

	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	DELETE_CAR heli_heist5
	DELETE_CHAR wuzi_heist5 
	
	SET_FADING_COLOUR 0 0 0
	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS
		
		WAIT 0

	ENDWHILE

ENDIF

MAKE_PLAYER_GANG_REAPPEAR

SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_POLICE_IGNORE_PLAYER player1 FALSE
SET_EVERYONE_IGNORE_PLAYER player1 FALSE
 
GOTO mission_heist5_passed

 // **************************************** Mission heist5 failed ***********************

mission_heist5_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission heist5 passed ************************

mission_heist5_passed:

flag_heist_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
REGISTER_MISSION_PASSED (HEIST_5) //Used in the stats
PLAYER_MADE_PROGRESS 1 
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 0 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 25 //amount of respect
CLEAR_WANTED_LEVEL player1

IF flag_mob_vegas[6] = 0
	REMOVE_BLIP theheist_contact_blip
ENDIF

SWITCH_CAR_GENERATOR hm5_car_gen 101

PLAY_MISSION_PASSED_TUNE 1 
RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_heist5:

DETACH_CHAR_FROM_CAR scplayer

SET_WANTED_MULTIPLIER 1.0

IF d5_bronze_generator_unlocked = 1
	SWITCH_CAR_GENERATOR d5_bronze_generator 101
ENDIF

IF d5_silver_generator_unlocked = 1 
	SWITCH_CAR_GENERATOR d5_silver_generator 101
ENDIF

IF d5_gold_generator_unlocked = 1 
	SWITCH_CAR_GENERATOR d5_gold_generator 101
ENDIF

IF NOT HAS_PICKUP_BEEN_COLLECTED grenades_heist5
	REMOVE_PICKUP grenades_heist5 
ENDIF

IF NOT HAS_PICKUP_BEEN_COLLECTED armour_heist5
	REMOVE_PICKUP armour_heist5 
ENDIF

IF NOT HAS_PICKUP_BEEN_COLLECTED health_heist5
	REMOVE_PICKUP health_heist5 
ENDIF

gate_stay_open = 0

SWITCH_AMBIENT_PLANES TRUE
SWITCH_POLICE_HELIS TRUE

flag_player_on_mission = 0
GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission

REMOVE_SPHERE sphere_heist5

MARK_MODEL_AS_NO_LONGER_NEEDED LEVIATHN
MARK_MODEL_AS_NO_LONGER_NEEDED ARMY
MARK_MODEL_AS_NO_LONGER_NEEDED M4
MARK_MODEL_AS_NO_LONGER_NEEDED ROCKETLA
MARK_MODEL_AS_NO_LONGER_NEEDED HUNTER
MARK_MODEL_AS_NO_LONGER_NEEDED minigun_base
MARK_MODEL_AS_NO_LONGER_NEEDED minigun
MARK_MODEL_AS_NO_LONGER_NEEDED SECURICA
MARK_MODEL_AS_NO_LONGER_NEEDED PATRIOT
MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA

REMOVE_DECISION_MAKER dm_duck_heist5
REMOVE_DECISION_MAKER dm_noduck_heist5
REMOVE_DECISION_MAKER dm_empty_heist5
  
REMOVE_BLIP heli_blip_heist5
REMOVE_BLIP blip_coord1_heist5
REMOVE_BLIP dummy_blip_heist5
REMOVE_BLIP heli_land_blip_heist5

REMOVE_BLIP minigun_blip_heist5
REMOVE_BLIP chopper1_backup_blip_heist5 
REMOVE_BLIP chopper2_backup_blip_heist5

REMOVE_BLIP bankvan1_blip_heist5

CLEAR_SEQUENCE_TASK kill_player_heist5
CLEAR_SEQUENCE_TASK kill2_player_heist5
CLEAR_SEQUENCE_TASK guard1_warehouse_task_heist5
CLEAR_SEQUENCE_TASK guard2_warehouse_task_heist5
CLEAR_SEQUENCE_TASK guard9_warehouse_task_heist5
CLEAR_SEQUENCE_TASK guard10_warehouse_task_heist5
CLEAR_SEQUENCE_TASK guard11_warehouse_task_heist5
CLEAR_SEQUENCE_TASK guard12_warehouse_task_heist5

//CLEAR_ONSCREEN_COUNTER bankvan1_health_heist5

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2
CLEAR_MISSION_AUDIO 3

MISSION_HAS_FINISHED
RETURN

// Checks to see if the player is in any flying vehicle.
rocket_check_heist5:

IF NOT IS_CHAR_DEAD guardnumber[3]

	IF IS_CHAR_IN_FLYING_VEHICLE scplayer
	OR IS_PLAYER_USING_JETPACK player1
	OR player_fall_state > 0
		REMOVE_ALL_CHAR_WEAPONS guardnumber[3] 
		GIVE_WEAPON_TO_CHAR guardnumber[3] WEAPONTYPE_ROCKETLAUNCHER 30000 // Set to infinate ammo
		SET_CHAR_ACCURACY guardnumber[3] 85
		SET_CHAR_SHOOT_RATE guardnumber[3] 40
	ELSE
		REMOVE_ALL_CHAR_WEAPONS guardnumber[3]
		GIVE_WEAPON_TO_CHAR guardnumber[3] WEAPONTYPE_M4 30000 // Set to infinate ammo
		SET_CHAR_ACCURACY guardnumber[3] 70
		SET_CHAR_SHOOT_RATE guardnumber[3] 40
	ENDIF 

ENDIF

IF NOT IS_CHAR_DEAD guardnumber[4]

	IF IS_CHAR_IN_FLYING_VEHICLE scplayer
	OR IS_PLAYER_USING_JETPACK player1
	OR player_fall_state > 0
		REMOVE_ALL_CHAR_WEAPONS guardnumber[4] 
		GIVE_WEAPON_TO_CHAR guardnumber[4] WEAPONTYPE_ROCKETLAUNCHER 30000 // Set to infinate ammo
		SET_CHAR_ACCURACY guardnumber[4] 85
		SET_CHAR_SHOOT_RATE guardnumber[4] 40
	ELSE
		REMOVE_ALL_CHAR_WEAPONS guardnumber[4]
		GIVE_WEAPON_TO_CHAR guardnumber[4] WEAPONTYPE_M4 30000 // Set to infinate ammo
		SET_CHAR_ACCURACY guardnumber[4] 70
		SET_CHAR_SHOOT_RATE guardnumber[4] 45
	ENDIF 

ENDIF

IF NOT IS_CHAR_DEAD guardnumber[5]

	IF IS_CHAR_IN_FLYING_VEHICLE scplayer
	OR IS_PLAYER_USING_JETPACK player1
	OR player_fall_state > 0
		REMOVE_ALL_CHAR_WEAPONS guardnumber[5] 
		GIVE_WEAPON_TO_CHAR guardnumber[5] WEAPONTYPE_ROCKETLAUNCHER 30000 // Set to infinate ammo
		SET_CHAR_ACCURACY guardnumber[5] 85
		SET_CHAR_SHOOT_RATE guardnumber[5] 40
	ELSE
		REMOVE_ALL_CHAR_WEAPONS guardnumber[5]
		GIVE_WEAPON_TO_CHAR guardnumber[5] WEAPONTYPE_M4 30000 // Set to infinate ammo
		SET_CHAR_ACCURACY guardnumber[5] 70
		SET_CHAR_SHOOT_RATE guardnumber[5] 50
	ENDIF 

ENDIF

RETURN

// Tells the snipers to attack the player
guard_checks_heist5:

	IF guards_killing_player_heist5 = 0

		IF NOT IS_CHAR_DEAD perimiter_guard1_heist5
			TASK_KILL_CHAR_ON_FOOT perimiter_guard1_heist5 scplayer	
		ENDIF

		IF NOT IS_CHAR_DEAD perimiter_guard2_heist5
			TASK_KILL_CHAR_ON_FOOT perimiter_guard2_heist5 scplayer	
		ENDIF

		IF NOT IS_CHAR_DEAD perimiter_guard3_heist5
			TASK_KILL_CHAR_ON_FOOT perimiter_guard3_heist5 scplayer	
		ENDIF
		
		IF NOT IS_CHAR_DEAD guard1_warehouse_heist5
			PERFORM_SEQUENCE_TASK guard1_warehouse_heist5 guard1_warehouse_task_heist5 
		ENDIF
			
		IF NOT IS_CHAR_DEAD guard2_warehouse_heist5
		   	PERFORM_SEQUENCE_TASK guard2_warehouse_heist5 guard2_warehouse_task_heist5
		ENDIF
		
		IF NOT IS_CHAR_DEAD guard3_warehouse_heist5
			TASK_KILL_CHAR_ON_FOOT guard3_warehouse_heist5 scplayer
		   	SET_CHAR_STAY_IN_SAME_PLACE guard3_warehouse_heist5 TRUE
		ENDIF
		
		IF NOT IS_CHAR_DEAD guard4_warehouse_heist5
			TASK_KILL_CHAR_ON_FOOT guard4_warehouse_heist5 scplayer
		   	SET_CHAR_STAY_IN_SAME_PLACE guard4_warehouse_heist5 TRUE
		ENDIF
			
		IF NOT IS_CHAR_DEAD guard5_warehouse_heist5
			TASK_KILL_CHAR_ON_FOOT guard5_warehouse_heist5 scplayer
		   	SET_CHAR_STAY_IN_SAME_PLACE guard5_warehouse_heist5 TRUE
		ENDIF
		
//		IF NOT IS_CHAR_DEAD guard6_warehouse_heist5
//			TASK_KILL_CHAR_ON_FOOT guard6_warehouse_heist5 scplayer
//		   	SET_CHAR_STAY_IN_SAME_PLACE guard6_warehouse_heist5 TRUE
//		ENDIF
		
		IF NOT IS_CHAR_DEAD guard7_warehouse_heist5
		   	TASK_KILL_CHAR_ON_FOOT guard7_warehouse_heist5 scplayer
			SET_CHAR_STAY_IN_SAME_PLACE guard7_warehouse_heist5 TRUE
		ENDIF
		
		IF NOT IS_CHAR_DEAD guard8_warehouse_heist5
		   TASK_KILL_CHAR_ON_FOOT guard8_warehouse_heist5 scplayer
		   SET_CHAR_STAY_IN_SAME_PLACE guard8_warehouse_heist5 TRUE
		ENDIF
	
		IF NOT IS_CHAR_DEAD guard9_warehouse_heist5
		   PERFORM_SEQUENCE_TASK guard9_warehouse_heist5 guard9_warehouse_task_heist5
		ENDIF
		
		IF NOT IS_CHAR_DEAD guard10_warehouse_heist5
		   PERFORM_SEQUENCE_TASK guard10_warehouse_heist5 guard10_warehouse_task_heist5
		ENDIF
		
		IF NOT IS_CHAR_DEAD guard11_warehouse_heist5
		   PERFORM_SEQUENCE_TASK guard11_warehouse_heist5 guard11_warehouse_task_heist5
		ENDIF
		
		IF NOT IS_CHAR_DEAD guard12_warehouse_heist5
		   PERFORM_SEQUENCE_TASK guard12_warehouse_heist5 guard12_warehouse_task_heist5
		ENDIF	

		guards_killing_player_heist5 = 1

	ENDIF

RETURN

// Checks for the player being close to the guards and sets them not to duck.
warehouse_area_checks_heist5:

	IF NOT IS_CHAR_DEAD guard1_warehouse_heist5 

		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard1_warehouse_heist5 3.0 3.0 2.0 FALSE 
			SET_CHAR_DECISION_MAKER guard1_warehouse_heist5 dm_noduck_heist5	
		ELSE
			SET_CHAR_DECISION_MAKER guard1_warehouse_heist5 dm_duck_heist5
		ENDIF

	ENDIF

	IF NOT IS_CHAR_DEAD guard2_warehouse_heist5 

		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard2_warehouse_heist5 3.0 3.0 2.0 FALSE 
			SET_CHAR_DECISION_MAKER guard2_warehouse_heist5 dm_noduck_heist5	
		ELSE
			SET_CHAR_DECISION_MAKER guard2_warehouse_heist5 dm_duck_heist5
		ENDIF

	ENDIF

	IF NOT IS_CHAR_DEAD guard3_warehouse_heist5 

		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard3_warehouse_heist5 3.0 3.0 2.0 FALSE
			SET_CHAR_DECISION_MAKER guard3_warehouse_heist5 dm_noduck_heist5	
		ELSE
			SET_CHAR_DECISION_MAKER guard3_warehouse_heist5 dm_duck_heist5
		ENDIF

	ENDIF

	IF NOT IS_CHAR_DEAD guard4_warehouse_heist5 

		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard4_warehouse_heist5 3.0 3.0 2.0 FALSE 
			SET_CHAR_DECISION_MAKER guard4_warehouse_heist5 dm_noduck_heist5	
		ELSE
			SET_CHAR_DECISION_MAKER guard4_warehouse_heist5 dm_duck_heist5
		ENDIF

	ENDIF
	
	IF NOT IS_CHAR_DEAD guard5_warehouse_heist5 

		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard5_warehouse_heist5 3.0 3.0 2.0 FALSE 
			SET_CHAR_DECISION_MAKER guard5_warehouse_heist5 dm_noduck_heist5	
		ELSE
			SET_CHAR_DECISION_MAKER guard5_warehouse_heist5 dm_duck_heist5
		ENDIF

	ENDIF
	
//	IF NOT IS_CHAR_DEAD guard6_warehouse_heist5 
//
//		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard6_warehouse_heist5 3.0 3.0 2.0 FALSE 
//			SET_CHAR_DECISION_MAKER guard6_warehouse_heist5 dm_noduck_heist5	
//		ELSE
//		   SET_CHAR_DECISION_MAKER guard6_warehouse_heist5 dm_duck_heist5
//		ENDIF
//
//	ENDIF

	IF NOT IS_CHAR_DEAD guard7_warehouse_heist5 

		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard7_warehouse_heist5 3.0 3.0 2.0 FALSE
	  		SET_CHAR_DECISION_MAKER guard7_warehouse_heist5 dm_noduck_heist5	
		ELSE
	    	SET_CHAR_DECISION_MAKER guard7_warehouse_heist5 dm_duck_heist5
		ENDIF

	ENDIF

	IF NOT IS_CHAR_DEAD guard8_warehouse_heist5 

		IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer guard8_warehouse_heist5 3.0 3.0 2.0 FALSE
	    	SET_CHAR_DECISION_MAKER guard8_warehouse_heist5 dm_noduck_heist5	
		ELSE
			SET_CHAR_DECISION_MAKER guard8_warehouse_heist5 dm_duck_heist5
		ENDIF

	ENDIF

RETURN

// guard one to give player a warning.
guard1_code_heist5:

	IF NOT IS_CHAR_DEAD gateguard1_heist5

		IF flag_base_alerted_heist5 = 0

			IF IS_PLAYER_TARGETTING_CHAR player1 gateguard1_heist5
			OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON gateguard1_heist5 WEAPONTYPE_ANYWEAPON
				flag_base_alerted_heist5 = 1
			ENDIF

			IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
				
				IF NOT IS_PLAYER_USING_JETPACK player1

					IF player_fall_state = 0   

						IF HAS_CHAR_SPOTTED_CHAR gateguard1_heist5 scplayer
						OR IS_CHAR_IN_AREA_3D scplayer 2497.387 2858.018 8.0 2676.413 2683.661 60.0 FALSE
							GET_CHAR_COORDINATES scplayer player_X player_Y player_Z

							IF flag_warn_player_heist5 = 0

								IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer gateguard1_heist5 8.0 8.0 8.0 FALSE
									PRINT_NOW (HE5_BA) 8000 1 //"You have 5 second to leave or die!"
								ENDIF

								GET_CHAR_COORDINATES gateguard1_heist5 gateguard1X_heist5 gateguard1Y_heist5 gateguard1Z_heist5 
								SET_MISSION_AUDIO_POSITION 1 gateguard1X_heist5 gateguard1Y_heist5 gateguard1Z_heist5
								PLAY_MISSION_AUDIO 1
								START_CHAR_FACIAL_TALK gateguard1_heist5  999999
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH gateguard1_heist5 TRUE 
								TIMERB = 0
								TASK_GOTO_CHAR gateguard1_heist5 scplayer -1 2.0
								flag_warn_player_heist5 = 1
							ENDIF


							IF flag_warn_player_heist5 = 1
								
								GET_SCRIPT_TASK_STATUS gateguard1_heist5 TASK_GOTO_CHAR task_status2
							
								IF task_status2 = FINISHED_TASK
									flag_warn_player_heist5 = 2
								ENDIF

							ENDIF

							IF flag_warn_player_heist5 >= 1 

								WHILE TIMERB < 3000

									WAIT 0

									IF HAS_MISSION_AUDIO_FINISHED 1
										CLEAR_THIS_PRINT (HE5_BA)

										IF NOT IS_CHAR_DEAD gateguard1_heist5 
											STOP_CHAR_FACIAL_TALK gateguard1_heist5
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH gateguard1_heist5 FALSE
										ELSE
											gateguard1_dead_heist5 = 1
										ENDIF

									ENDIF
									
									IF flag_base_alerted_heist5 = 1
									
										IF NOT IS_CHAR_DEAD gateguard1_heist5
											
											IF flag_gateguard1_got_ai_heist5 = 0							 
												CLEAR_ALL_CHAR_RELATIONSHIPS gateguard1_heist5 ACQUAINTANCE_TYPE_PED_HATE
												SET_CHAR_RELATIONSHIP gateguard1_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
												TASK_KILL_CHAR_ON_FOOT gateguard1_heist5 scplayer
												flag_gateguard1_got_ai_heist5 = 1
											ENDIF

										ELSE
											
											IF gateguard1_dead_heist5 = 0
												CLEAR_MISSION_AUDIO 1
												CLEAR_MISSION_AUDIO 2
												CLEAR_THIS_PRINT (HE5_BA)
												CLEAR_THIS_PRINT (HE5_BB) 	
												gateguard1_dead_heist5 = 1
											ENDIF

										ENDIF

									ELSE

										IF gateguard1_dead_heist5 = 0

											IF IS_CHAR_DEAD gateguard1_heist5
												CLEAR_MISSION_AUDIO 1
												CLEAR_MISSION_AUDIO 2
												CLEAR_THIS_PRINT (HE5_BA)
												CLEAR_THIS_PRINT (HE5_BB)
												flag_base_alerted_heist5 = 1  	
												gateguard1_dead_heist5 = 1
											ELSE

												IF IS_PLAYER_TARGETTING_CHAR player1 gateguard1_heist5
												OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON gateguard1_heist5 WEAPONTYPE_ANYWEAPON	
													flag_base_alerted_heist5 = 1
												ENDIF

											ENDIF

										ENDIF
																		 
									ENDIF 

								ENDWHILE

								IF IS_CHAR_IN_AREA_3D scplayer 2497.387 2858.018 8.0 2676.413 2683.661 60.0 FALSE
								
									IF NOT IS_CHAR_DEAD gateguard1_heist5

										IF flag_base_alerted_heist5 >= 0

											IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer gateguard1_heist5 8.0 8.0 8.0 FALSE
												PRINT_NOW (HE5_BD) 8000 1 //"OPEN FIRE!"
											ENDIF
																						
											START_CHAR_FACIAL_TALK gateguard1_heist5  999999
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH gateguard1_heist5 TRUE
											GET_CHAR_COORDINATES gateguard1_heist5 gateguard1X_heist5 gateguard1Y_heist5 gateguard1Z_heist5 
											SET_MISSION_AUDIO_POSITION 2 gateguard1X_heist5 gateguard1Y_heist5 gateguard1Z_heist5
											PLAY_MISSION_AUDIO 2 
										ENDIF

										IF flag_gateguard1_got_ai_heist5 = 0
											CLEAR_ALL_CHAR_RELATIONSHIPS gateguard1_heist5 ACQUAINTANCE_TYPE_PED_HATE
											SET_CHAR_RELATIONSHIP gateguard1_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
											TASK_KILL_CHAR_ON_FOOT gateguard1_heist5 scplayer
											flag_gateguard1_got_ai_heist5 = 1
										ENDIF

										flag_base_alerted_heist5 = 1

									ELSE

										IF gateguard1_dead_heist5 = 0
											CLEAR_MISSION_AUDIO 1
											CLEAR_MISSION_AUDIO 2
											CLEAR_THIS_PRINT (HE5_BA)
											CLEAR_THIS_PRINT (HE5_BB)  	
											gateguard1_dead_heist5 = 1
										ENDIF
										
										flag_base_alerted_heist5 = 1
									ENDIF 

								ENDIF

							ENDIF

						ENDIF

					ELSE
						flag_base_alerted_heist5 = 1
					ENDIF

				ELSE
					flag_base_alerted_heist5 = 1					
				ENDIF

			ELSE
				flag_base_alerted_heist5 = 1
			ENDIF

		ELSE
			IF NOT IS_CHAR_DEAD gateguard1_heist5

				IF flag_gateguard1_got_ai_heist5 = 0
					CLEAR_ALL_CHAR_RELATIONSHIPS gateguard1_heist5 ACQUAINTANCE_TYPE_PED_HATE
					CLEAR_ALL_CHAR_RELATIONSHIPS gateguard1_heist5 ACQUAINTANCE_TYPE_PED_LIKE
					SET_CHAR_RELATIONSHIP gateguard1_heist5 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
					SET_CHAR_RELATIONSHIP gateguard1_heist5 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_MISSION1	
					TASK_KILL_CHAR_ON_FOOT gateguard1_heist5 scplayer
					flag_gateguard1_got_ai_heist5 = 1
				ENDIF

			ELSE

				IF gateguard1_dead_heist5 = 0
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					CLEAR_THIS_PRINT (HE5_BA)
					CLEAR_THIS_PRINT (HE5_BB)  	
					gateguard1_dead_heist5 = 1
				ENDIF
				
				flag_base_alerted_heist5 = 1
			ENDIF
				
		ENDIF		 

	ELSE

		IF gateguard1_dead_heist5 = 0
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_THIS_PRINT (HE5_BA)
			CLEAR_THIS_PRINT (HE5_BB)  	
			gateguard1_dead_heist5 = 1
		ENDIF
		
		flag_base_alerted_heist5 = 1
	ENDIF	
				
RETURN

// gaurd death checks
guard_death_checks_heist5:

// perimiter guards
IF perimiter_guard1_dead_heist5 = 0

	IF IS_CHAR_DEAD perimiter_guard1_heist5
	   	flag_base_alerted_heist5 = 1 
		perimiter_guard1_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 perimiter_guard1_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON perimiter_guard1_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR perimiter_guard1_heist5 scplayer
							flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF	 

	ENDIF

ENDIF

IF perimiter_guard2_dead_heist5 = 0

	IF IS_CHAR_DEAD perimiter_guard2_heist5
	  	flag_base_alerted_heist5 = 1 
		perimiter_guard2_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 perimiter_guard2_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON perimiter_guard2_heist5 WEAPONTYPE_ANYWEAPON
			  		flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR perimiter_guard2_heist5 scplayer 
			   				flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF	

	ENDIF

ENDIF

IF perimiter_guard3_dead_heist5 = 0

	IF IS_CHAR_DEAD perimiter_guard3_heist5
	   	flag_base_alerted_heist5 = 1 
		perimiter_guard3_dead_heist5 = 1
	ELSE
		
		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 perimiter_guard3_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON perimiter_guard3_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR perimiter_guard3_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

// warehouse guards
IF guard1_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard1_warehouse_heist5
	   	flag_base_alerted_heist5 = 1
		guard1_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard1_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard1_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard1_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

IF guard2_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard2_warehouse_heist5
	   	flag_base_alerted_heist5 = 1
		guard2_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard2_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard2_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard2_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF
		
	ENDIF

ENDIF

IF guard3_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard3_warehouse_heist5
	 	flag_base_alerted_heist5 = 1
		guard3_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard3_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard3_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard3_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

IF guard4_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard4_warehouse_heist5
   		flag_base_alerted_heist5 = 1
		guard4_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard4_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard4_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard4_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

IF guard5_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard5_warehouse_heist5
   		flag_base_alerted_heist5 = 1
		guard5_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard5_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard5_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard5_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

//IF guard6_warehouse_dead_heist5 = 0
//
//	IF IS_CHAR_DEAD guard6_warehouse_heist5
//	  	flag_base_alerted_heist5 = 1
//		guard6_warehouse_dead_heist5 = 1
//	ELSE
//
//		IF player_entered_depot_heist5 = 1
//
//			IF flag_base_alerted_heist5 = 0
//
//				IF IS_PLAYER_TARGETTING_CHAR player1 guard6_warehouse_heist5
//				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard6_warehouse_heist5 WEAPONTYPE_ANYWEAPON
//					flag_base_alerted_heist5 = 1
//				ENDIF
//
//				IF flag_warn_player_heist5 > 1
//
//					IF TIMERB > 3000
//
//						IF HAS_CHAR_SPOTTED_CHAR guard6_warehouse_heist5 scplayer
//					  		flag_base_alerted_heist5 = 1
//						ENDIF
//
//					ENDIF
//					
//				ENDIF			   
//
//			ENDIF
//		
//		ENDIF
//
//	ENDIF
//
//ENDIF

IF guard7_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard7_warehouse_heist5
		flag_base_alerted_heist5 = 1
		guard7_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard7_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard7_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard7_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

IF guard8_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard8_warehouse_heist5
	  	flag_base_alerted_heist5 = 1
		guard8_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard8_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard8_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard8_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

IF guard9_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard9_warehouse_heist5
	 	flag_base_alerted_heist5 = 1
		guard9_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard9_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard9_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard9_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF


IF guard10_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard10_warehouse_heist5
	 	flag_base_alerted_heist5 = 1
		guard10_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard10_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard10_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard10_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF 

ENDIF


IF guard11_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard11_warehouse_heist5
	  	flag_base_alerted_heist5 = 1
		guard11_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard11_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard11_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard11_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

IF guard12_warehouse_dead_heist5 = 0

	IF IS_CHAR_DEAD guard12_warehouse_heist5
	  	flag_base_alerted_heist5 = 1
		guard12_warehouse_dead_heist5 = 1
	ELSE

		IF player_entered_depot_heist5 = 1

			IF flag_base_alerted_heist5 = 0

				IF IS_PLAYER_TARGETTING_CHAR player1 guard12_warehouse_heist5
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guard12_warehouse_heist5 WEAPONTYPE_ANYWEAPON
					flag_base_alerted_heist5 = 1
				ENDIF

				IF flag_warn_player_heist5 > 1

					IF TIMERB > 3000

						IF HAS_CHAR_SPOTTED_CHAR guard12_warehouse_heist5 scplayer
					  		flag_base_alerted_heist5 = 1
						ENDIF

					ENDIF
					
				ENDIF			   

			ENDIF
		
		ENDIF

	ENDIF

ENDIF

// gate guard
IF gateguard1_dead_heist5 = 0

	IF IS_CHAR_DEAD gateguard1_heist5
		flag_base_alerted_heist5 = 1  	
		gateguard1_dead_heist5 = 1
	ENDIF

ENDIF

// heli guards
IF guardnumber0_dead_heist5 = 0

	IF IS_CHAR_DEAD guardnumber[0]
		flag_base_alerted_heist5 = 1
		guardnumber0_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber1_dead_heist5 = 0

	IF IS_CHAR_DEAD guardnumber[1]
		flag_base_alerted_heist5 = 1
		guardnumber1_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber2_dead_heist5 = 0

	IF IS_CHAR_DEAD guardnumber[2]
		flag_base_alerted_heist5 = 1
		guardnumber2_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber3_dead_heist5 = 0

	IF IS_CHAR_DEAD guardnumber[3]
		flag_base_alerted_heist5 = 1
		guardnumber3_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber4_dead_heist5 = 0

	IF IS_CHAR_DEAD guardnumber[4]
		flag_base_alerted_heist5 = 1
		guardnumber4_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber5_dead_heist5 = 0

	IF IS_CHAR_DEAD guardnumber[5]
		flag_base_alerted_heist5 = 1
		guardnumber5_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber6_dead_heist5 = 0

	IF IS_CHAR_DEAD guardnumber[6]
		flag_base_alerted_heist5 = 1
		guardnumber6_dead_heist5 = 1
	ENDIF

ENDIF

RETURN

// sets up the array guards
array_guard_check_heist5:

	LVAR_INT dex // This is used to represent the index number of each part of the array 0,1,2,3
	dex = 0

	WHILE dex < 7
		WAIT 0

	   	GOSUB guard_death_checks_heist5	
		
	   	IF player_entered_depot_heist5 = 1

	   		GOSUB guard1_code_heist5

	   		GOSUB rocket_check_heist5

	   		IF flag_base_alerted_heist5 = 1
	   
	   			IF guards_killing_player_heist5 = 0
	   				GOSUB guard_checks_heist5
	   			ELSE
	   				GOSUB warehouse_area_checks_heist5
				ENDIF
	   
	   		ENDIF

			IF flag_base_alerted_heist5 >= 1

				IF flag_gateguard1_got_ai_heist5 = 1

					IF HAS_MISSION_AUDIO_FINISHED 2
						CLEAR_THIS_PRINT (HE5_BD)

						IF gateguard1_dead_heist5 = 0

							IF NOT IS_CHAR_DEAD gateguard1_heist5 
								STOP_CHAR_FACIAL_TALK gateguard1_heist5 
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH gateguard1_heist5 FALSE
					 		ELSE
								gateguard1_dead_heist5 = 1
							ENDIF

						ENDIF
						
						flag_gateguard1_got_ai_heist5 = 2		
					ENDIF

				ENDIF

			ENDIF
			
	   	ENDIF
		
		IF NOT IS_CHAR_DEAD guardnumber[dex] // this means guardnumber[0 or 1 dex value is increased to change to each guard]
				
			GET_SCRIPT_TASK_STATUS guardnumber[dex] TASK_KILL_CHAR_ON_FOOT task_status // this is needed to stop char doing task over and over
				
			IF task_status = FINISHED_TASK // this is needed to stop char doing task over and over.

				IF player_entered_depot_heist5 = 1

					IF flag_base_alerted_heist5 = 0

						IF IS_PLAYER_TARGETTING_CHAR player1 guardnumber[dex]
						OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guardnumber[dex] WEAPONTYPE_ANYWEAPON
							flag_base_alerted_heist5 = 1
						ENDIF

						IF flag_warn_player_heist5 > 1

							IF TIMERB > 3000

								IF HAS_CHAR_SPOTTED_CHAR guardnumber[dex] scplayer
							  		flag_base_alerted_heist5 = 1
								ENDIF

							ENDIF
							
						ENDIF			   

					ELSE
						TASK_KILL_CHAR_ON_FOOT guardnumber[dex] scplayer

					ENDIF
				
				ENDIF
				
//				IF HAS_CHAR_SPOTTED_CHAR guardnumber[dex] scplayer
//				OR IS_PLAYER_TARGETTING_CHAR player1 guardnumber[dex]
//				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON guardnumber[dex] WEAPONTYPE_ANYWEAPON
//				 //	flag_base_alerted_heist5 = 1
//					TASK_KILL_CHAR_ON_FOOT guardnumber[dex] scplayer
//				ENDIF

			ENDIF
										   	
		ELSE
			flag_base_alerted_heist5 = 1	
		ENDIF

		++ dex

	ENDWHILE

RETURN

// removes guards that I don't need anymore
mid_mission_cleanup_heist5:

IF perimiter_guard1_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD perimiter_guard1_heist5 
		DELETE_CHAR perimiter_guard1_heist5 
		perimiter_guard1_dead_heist5 = 1
	ENDIF

ENDIF

IF perimiter_guard2_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD perimiter_guard2_heist5 
		DELETE_CHAR perimiter_guard2_heist5 
		perimiter_guard2_dead_heist5 = 1
	ENDIF

ENDIF

IF perimiter_guard3_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD perimiter_guard3_heist5 
		DELETE_CHAR perimiter_guard3_heist5 
		perimiter_guard3_dead_heist5 = 1
	ENDIF

ENDIF

IF guard1_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard1_warehouse_heist5
		DELETE_CHAR guard1_warehouse_heist5
		guard1_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard2_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard2_warehouse_heist5
		DELETE_CHAR guard2_warehouse_heist5
		guard2_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard3_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard3_warehouse_heist5
		DELETE_CHAR guard3_warehouse_heist5
		guard3_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard4_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard4_warehouse_heist5
		DELETE_CHAR guard4_warehouse_heist5
		guard4_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard5_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard5_warehouse_heist5
		DELETE_CHAR guard5_warehouse_heist5
		guard5_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

//IF guard6_warehouse_dead_heist5 = 0
//
//	IF NOT IS_CHAR_DEAD guard6_warehouse_heist5
//		DELETE_CHAR guard6_warehouse_heist5
//		guard6_warehouse_dead_heist5 = 1
//	ENDIF
//
//ENDIF

IF guard7_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard7_warehouse_heist5
		DELETE_CHAR guard7_warehouse_heist5
		guard7_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard8_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard8_warehouse_heist5
		DELETE_CHAR guard8_warehouse_heist5
		guard8_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard9_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard9_warehouse_heist5
		DELETE_CHAR guard9_warehouse_heist5
		guard9_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard10_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard10_warehouse_heist5
		DELETE_CHAR guard10_warehouse_heist5
		guard10_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard11_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard11_warehouse_heist5
		DELETE_CHAR guard11_warehouse_heist5
		guard11_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF guard12_warehouse_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guard12_warehouse_heist5
		DELETE_CHAR guard12_warehouse_heist5
		guard12_warehouse_dead_heist5 = 1
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD driver_gate_car_heist5
	DELETE_CHAR driver_gate_car_heist5
ENDIF

IF NOT IS_CAR_DEAD gate_drive_car_heist5
	DELETE_CAR gate_drive_car_heist5 
ENDIF

MARK_MODEL_AS_NO_LONGER_NEEDED PATRIOT

RETURN

// cleansup all the compound guards
cleanup2_heist5:

// gate guard
IF gateguard1_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD gateguard1_heist5
		DELETE_CHAR gateguard1_heist5   	
		gateguard1_dead_heist5 = 1
	ENDIF

ENDIF

// heli guards
IF guardnumber0_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guardnumber[0]
		DELETE_CHAR guardnumber[0] 
		guardnumber0_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber1_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guardnumber[1]
		DELETE_CHAR guardnumber[1]
		guardnumber1_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber2_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guardnumber[2]
		DELETE_CHAR guardnumber[2]
		guardnumber2_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber3_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guardnumber[3]
		DELETE_CHAR guardnumber[3]
		guardnumber3_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber4_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guardnumber[4]
		DELETE_CHAR guardnumber[4]
		guardnumber4_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber5_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guardnumber[5]
		DELETE_CHAR guardnumber[5]
		guardnumber5_dead_heist5 = 1
	ENDIF

ENDIF

IF guardnumber6_dead_heist5 = 0

	IF NOT IS_CHAR_DEAD guardnumber[6]
		DELETE_CHAR guardnumber[6]
		guardnumber6_dead_heist5 = 1
	ENDIF

ENDIF

flag_cleanup2_done_heist5 = 1

RETURN


}