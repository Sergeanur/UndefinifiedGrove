MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 22/09/2003 	Time: 10:37:39	   Name: Steve Taylor						 ***
// ***																					 ***
// *** Mission: Heist 4	- Street Hawk									 				 ***
// ***																					 ***
// *** Brief: Players buddy is on the highway going around vegas in an articulated truck.***
// *** Player has to steal three designated bikes from around Vegas and put drive them in***
// *** the back of the truck.  Time limit and as the player steals each bike his wanted  ***
// *** level gets higher/faces more resistance from goons.                               ***
// ***																					 ***
// *** PC Script																		 ***
// *****************************************************************************************

SCRIPT_NAME HEIST4

GOSUB mission_hst4_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_hst4_failed
ENDIF

GOSUB mission_hst4_cleanup

MISSION_END
 
// ************************************ MISSION START **************************************
{
mission_hst4_start:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT heist4

//SCRIPT_NAME HEIST4

WAIT 0

SET_AREA_VISIBLE 10

SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
//SET_CHAR_COORDINATES scplayer 2024.8704 1008.6201 9.8127
//SET_CHAR_HEADING scplayer 342.8127
SET_CHAR_COORDINATES scplayer 2033.9742 1017.2899 9.8203
SET_CHAR_HEADING scplayer 288.0

LVAR_INT hst4_group

GET_PLAYER_GROUP player1 hst4_group

SET_PLAYER_GROUP_RECRUITMENT player1 FALSE

SET_GROUP_FOLLOW_STATUS hst4_group FALSE

SET_PLAYER_GROUP_TO_FOLLOW_NEVER player1 TRUE

LOAD_CUTSCENE HEIST5a
 
WHILE NOT HAS_CUTSCENE_LOADED
            WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
            WAIT 0
ENDWHILE
 
//CLEAR_CUTSCENE

SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
            WAIT 0
ENDWHILE

CLEAR_CUTSCENE

SWITCH_COPS_ON_BIKES OFF

LOAD_SCENE 2031.1681 1030.9177 9.8130
REQUEST_COLLISION 2031.1681 1030.9177 //9.8130
// Wait 0 is important for streaming issues! Put it after area visible in order for it to work nicely!

 
SET_AREA_VISIBLE 0
WAIT 0

// Wait 0 is important for streaming issues! Put it after area visible in order for it to work nicely!

SET_PLAYER_CONTROL player1 OFF

//LOAD_SCENE 2031.1681 1030.9177 9.8130


LVAR_INT mission_blip mission_blip2 mission_flag mission_timer sequence_task players_car car_health	temp_int a
LVAR_INT r c e v m s b
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 speed temp_float

// global for delivery timer
VAR_INT hst4_deliverytimer
LVAR_INT hst4_nextprompt


LVAR_INT hst4_emptydm hst4_toughdm // decision makers
LVAR_INT hst4_pizzacop hst4_pizzabox hst4_pizzacopseq  hst4_pizzasetpiece hst4_pizzadrunkbust hst4_pizzadrunk hst4_pizzadrunkseq
LVAR_INT hst4_hotelcop hst4_hotelcopseq  hst4_hotelsetpiece	hst4_hotelthugsleave
LVAR_INT hst4_pizzabike hst4_pizzabox2
LVAR_INT hst4_larrycop hst4_staircop hst4_larrycopseq hst4_staircopseq hst4_stationsetpiece	hst4_stationcar
LVAR_INT hst4_hospitalcop hst4_hospitalcopseq hst4_hospitalcop2seq hst4_hospitalsetpiece hst4_hospitalchopper hst4_hospitalpilot hst4_choppertakeoff

LVAR_INT hst4_spawnchopper
LVAR_INT attached_bikeindex

LVAR_INT hst4_puke

LVAR_INT hst4_hotelbike1 hst4_hotelbike2 hst4_hotelbike3 
LVAR_INT hst4_hotelthug1 hst4_hotelthug2 hst4_hotelthug3 hst4_hotelthug1seq hst4_hotelthug2seq hst4_hotelthug3seq

LVAR_INT siren_fix

LVAR_INT hst4_finalcut hst4_finalcutskip hst4_newcutskip
hst4_newcutskip= 0 

LVAR_INT hst4_endseq

LVAR_INT hst4_hospitalwanted hst4_hotelwanted hst4_pizzawanted hst4_larrywanted hst4_stairwanted

LVAR_FLOAT hst4_clearX hst4_clearY hst4_clearZ

LVAR_INT bike_camera
LVAR_INT truck_thumb truck_thumbsfx
LVAR_INT truck_context
LVAR_FLOAT truck_thumbX truck_thumbY truck_thumbZ

LVAR_FLOAT slowzoneX1 slowzoneY1 slowzoneZ1 slowzoneX2 slowzoneY2 slowzoneZ2 bike_truckSpeed slowzoneSpeed
LVAR_INT slowzoneCar sztest


LVAR_INT  hst4_clockfix

//Dialogue and audio variables

LVAR_INT hst4_dialogue hst4_audio_char
LVAR_INT hst4_text_timer_diff hst4_text_timer_end hst4_text_timer_start
LVAR_TEXT_LABEL hst4_text[19]
LVAR_INT hst4_audio[19] hst4_audio_slot hst4_alt_slot hst4_counter hst4_ahead_counter hst4_audio_playing hst4_audio_underway
LVAR_INT hst4_convo_underway hst4_convo_counter hst4_random //hst4_leftcar_counter hst4_backcar_counter hst4_ganghire_counter 
//LVAR_INT hst4_driveby_counter hst4_cut_counter


LVAR_INT sfx_tyres


// ---- Dialogue Flags
	hst4_audio_slot = 1
	hst4_alt_slot = 2
	hst4_counter = 0
	hst4_ahead_counter = 1
	hst4_audio_playing = 0
	hst4_audio_underway = 0



//Dialogue text

    $hst4_text[1] = HE4_AA// Okay, that should do us
	$hst4_text[2] = HE4_AB// Take good care of those bikes
	$hst4_text[3] = HE4_AC// Now get out of here

	$hst4_text[4] = HE4_BA// Come in control, I've just been dealing with a drunk at the pizza parlour near Linden Station
	$hst4_text[5] = HE4_BB// and some punk has just stole my bike
	$hst4_text[6] = HE4_BC// Larry, some prick's trying your bike for size
	$hst4_text[7] = HE4_BD// Get him!
	$hst4_text[8] = HE4_BE// Control, this is Officer Jordan
	$hst4_text[9] = HE4_BF// I'm at the hospital and , well, some joker's stolen my bike!
	$hst4_text[10] = HE4_BG// All Units. this an APB for a serial patrol bike thief!
	$hst4_text[11] = HE4_BH//Suspect is black male, about six feet tall...
	$hst4_text[12] = HE4_BJ//And light build
	$hst4_text[13] = HE4_BK//And medium build
	$hst4_text[14] = HE4_BL//And heavy build
	$hst4_text[15] = HE4_BM//Oh, and a very silly haircut!
	
	$hst4_text[16] = HE4_CA// Ok, you get the Packer, hit the Julius Thruway and keep moving.
	$hst4_text[17] = HE4_CB// I'll steal the bikes and get them to you.




//Dialogue audio

    $hst4_audio[1] = SOUND_HE4_AA// Okay, that should do us
	$hst4_audio[2] = SOUND_HE4_AB// Take good care of those bikes
	$hst4_audio[3] = SOUND_HE4_AC// Now get out of here

	$hst4_audio[4] = SOUND_HE4_BA// Come in control, I've just been dealing with a drunk at the pizza parlour near Linden Station
	$hst4_audio[5] = SOUND_HE4_BB// and some punk has just stole my bike
	$hst4_audio[6] = SOUND_HE4_BC// Larry, some prick's trying your bike for size
	$hst4_audio[7] = SOUND_HE4_BD// Get him!
	$hst4_audio[8] = SOUND_HE4_BE// Control, this is Officer Jordan
	$hst4_audio[9] = SOUND_HE4_BF// I'm at the hospital and , well, some joker's stolen my bike!
	$hst4_audio[10] = SOUND_HE4_BG// All Units. this an APB for a serial patrol bike thief!
	$hst4_audio[11] = SOUND_HE4_BH//Suspect is black male, about six feet tall...
	$hst4_audio[12] = SOUND_HE4_BJ//And light build
	$hst4_audio[13] = SOUND_HE4_BK//And medium build
	$hst4_audio[14] = SOUND_HE4_BL//And heavy build
	$hst4_audio[15] = SOUND_HE4_BM//Oh, and a very silly haircut!

	$hst4_audio[16] = SOUND_HE4_CA// Ok, you get the Packer, hit the Julius Thruway and keep moving.
	$hst4_audio[17] = SOUND_HE4_CB// I'll steal the bikes and get them to you.


//LOAD_MISSION_TEXT heist4

//LOAD_SCENE 2035.4064 1040.5701 9.8130 

REQUEST_MODEL PACKER
REQUEST_MODEL COPBIKE
REQUEST_MODEL LAPDM1
REQUEST_MODEL COLT45
REQUEST_MODEL COPCARVG
REQUEST_MODEL DWMOLC1
//REQUEST_MODEL WMOBO
REQUEST_MODEL FREEWAY
REQUEST_MODEL DWMYLC1

REQUEST_MODEL DWMOLC2
REQUEST_MODEL WMYMECH
REQUEST_MODEL triada

REQUEST_ANIMATION SMOKING
REQUEST_ANIMATION MISC

SET_CHAR_HEADING scplayer 342.8127

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED PACKER
OR NOT HAS_MODEL_LOADED COPBIKE
OR NOT HAS_MODEL_LOADED LAPDM1
OR NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_MODEL_LOADED DWMOLC1
	WAIT 0
ENDWHILE

WHILE NOT HAS_ANIMATION_LOADED SMOKING	
	OR NOT HAS_MODEL_LOADED COPCARVG
	OR NOT HAS_ANIMATION_LOADED MISC
	OR NOT HAS_MODEL_LOADED FREEWAY
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED WMYMECH
	OR NOT HAS_MODEL_LOADED DWMOLC2
	OR NOT HAS_MODEL_LOADED DWMYLC1
	OR NOT HAS_MODEL_LOADED triada
	WAIT 0
ENDWHILE

LOAD_SCENE 2031.1681 1030.9177 9.8130

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY hst4_emptydm
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH hst4_toughdm

//SET_CHAR_HEADING scplayer 342.8127


GET_CHAR_COORDINATES scplayer hst4_clearX hst4_clearY hst4_clearZ
CLEAR_AREA hst4_clearX hst4_clearY hst4_clearZ 1000.0 FALSE


WHILE NOT IS_PLAYER_PLAYING player1
	WAIT 0
ENDWHILE

LVAR_FLOAT gotos_x[20] gotos_y[20]

gotos_x[0] = 2710.6233 
gotos_y[0] = 1397.8832 // start, view of skyscraper to right

gotos_x[1] = 2711.0706 	  //
gotos_y[1] = 1175.0027	  //

gotos_x[2] = 2590.8120 //
gotos_y[2] = 898.2437  //

gotos_x[3] =  2203.8962 //
gotos_y[3] =  849.0437	//

gotos_x[4] =  1833.4729 //
gotos_y[4] =  850.4182	//


							
gotos_x[5] = 1541.6023 //	Dodgy!
gotos_y[5] = 848.7880  //


gotos_x[6] = 1288.890
gotos_y[6] = 901.789

gotos_x[7] = 1224.9752 //
gotos_y[7] = 1240.5899 //

gotos_x[8] = 1224.0641 //
gotos_y[8] = 1744.7032 //

gotos_x[9] = 1224.1733 //
gotos_y[9] = 2071.7032 //

gotos_x[10] = 1279.2450 // 
gotos_y[10] = 2398.6830 //



gotos_x[11] = 1566.8157 //
gotos_y[11] = 2457.2327	//	  tricky bit

gotos_x[12] = 1931.4498 //
gotos_y[12] = 2521.8577	//
																																	 
gotos_x[13] = 2442.5735 //
gotos_y[13] = 2609.4287	//

gotos_x[14] = 2704.6577 //
gotos_y[14] = 2343.6537	//

gotos_x[15] = 2710.8911 //
gotos_y[15] = 2065.3169	//

gotos_x[16] = 2711.8918 //
gotos_y[16] = 1830.5001 //
						

//LOAD_SCENE 2031.1681 1030.9177 9.8130




LVAR_INT bike_truck
CLEAR_AREA 2710.5249 1567.0090 100.0 100.0 FALSE
CUSTOM_PLATE_FOR_NEXT_CAR packer CFC_1888
CREATE_CAR PACKER 2711.3264 1567.0452 6.2 bike_truck
SET_CAR_HEADING bike_truck 175.1379 
CHANGE_CAR_COLOUR bike_truck 16 16

//SET_CAR_CRUISE_SPEED bike_truck 30.0//50.0


//SET_CAR_DRIVING_STYLE bike_truck 2
SET_UPSIDEDOWN_CAR_NOT_DAMAGED bike_truck TRUE
SET_CAR_HEALTH bike_truck 2500
ADD_UPSIDEDOWN_CAR_CHECK bike_truck
ADD_STUCK_CAR_CHECK bike_truck 4.0 3000
OPEN_CAR_DOOR bike_truck FRONT_RIGHT_DOOR
SET_CAR_STAY_IN_FAST_LANE bike_truck TRUE
SET_CAR_ONLY_DAMAGED_BY_PLAYER bike_truck TRUE
LOCK_CAR_DOORS bike_truck CARLOCK_LOCKED
SET_CAR_CAN_GO_AGAINST_TRAFFIC bike_truck FALSE





LVAR_INT truck_driver
CREATE_CHAR_INSIDE_CAR bike_truck PEDTYPE_MISSION1 triada truck_driver

CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2715.3264 1569.0452 7.2 truck_thumb
SET_CHAR_PROOFS truck_thumb TRUE TRUE TRUE TRUE TRUE
SET_CHAR_NEVER_TARGETTED truck_thumb TRUE
ATTACH_CHAR_TO_CAR truck_thumb bike_truck 1.0 3.1 0.4 FACING_BACK 0.0 WEAPONTYPE_UNARMED
SET_LOAD_COLLISION_FOR_CHAR_FLAG truck_thumb FALSE

SET_CHAR_DECISION_MAKER truck_driver hst4_emptydm

SET_CHAR_DECISION_MAKER truck_thumb hst4_emptydm



//LOAD_SCENE 2035.4064 1040.5701 9.8130 

SET_LOAD_COLLISION_FOR_CHAR_FLAG truck_driver FALSE
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE truck_driver FALSE
SET_CHAR_CANT_BE_DRAGGED_OUT truck_driver TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED truck_driver TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS truck_driver FALSE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR truck_driver FALSE

SET_LOAD_COLLISION_FOR_CAR_FLAG bike_truck FALSE
																			   

MARK_MODEL_AS_NO_LONGER_NEEDED PACKER

LVAR_FLOAT motorbike_x[4] motorbike_y[4] motorbike_z[4]	motorbike_h[4]
//Steve T
motorbike_x[0] = 2770.2131 
motorbike_y[0] = 1433.8599
motorbike_z[0] = 9.3804
motorbike_h[0] = 257.0
//Zip Pizza, by highway.

motorbike_x[1] = 2620.6829//2512.6458
motorbike_y[1] = 2333.6863//2367.5801
motorbike_z[1] = 9.8203//10.8694
motorbike_h[1] = 174.5638//266.5737
//Rock Hotel

motorbike_x[2] = 2316.9353
motorbike_y[2] = 2457.0063
motorbike_z[2] = 9.8203
motorbike_h[2] = 140.3
//Las Vegas police station car park, puts wanted level up to two stars

/*
motorbike_x[3] = 1593.8617
motorbike_y[3] = 1831.1394
motorbike_z[3] = 10.8681
motorbike_h[3] = 0.0
*/
//Las Vegas hospital


motorbike_x[3] = 1610.5875 
motorbike_y[3] = 1845.6019
motorbike_z[3] = 9.8280
motorbike_h[3] = 235.7296



hst4_nextprompt = 99
hst4_deliverytimer = 720000	 // twelve minutes
hst4_pizzasetpiece = 0
hst4_hotelsetpiece = 0
hst4_stationsetpiece = 0
hst4_hospitalsetpiece = 0
hst4_choppertakeoff	= 0
hst4_pizzadrunkbust = 0
hst4_hotelthugsleave = 0

hst4_spawnchopper = 0
attached_bikeindex = 0
siren_fix = 0
hst4_finalcut = 0
hst4_finalcutskip = 0

hst4_hospitalwanted = 0
hst4_hotelwanted = 0
hst4_pizzawanted = 0
hst4_larrywanted = 0
hst4_stairwanted = 0

bike_camera = 0

hst4_clockfix = 0




m = 0
IF m = -1
	CREATE_CAR COPBIKE X Y Z motorbike[m]
	ADD_BLIP_FOR_COORD X Y Z mission_blip
	CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 X Y Z hst4_pizzacop
   //	SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_pizzacop FALSE
	CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 X Y Z hst4_hotelcop
   //	SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_hotelcop FALSE

ENDIF
WHILE m < 4
	ADD_BLIP_FOR_COORD motorbike_x[m] motorbike_y[m] motorbike_z[m] motorbike_blip[m]
	CHANGE_BLIP_COLOUR motorbike_blip[m] GREEN

	LVAR_INT motorbike_created[4]
	motorbike_created[m] = 0

	LVAR_INT bike_attached_flag[4]
	bike_attached_flag[m] = 0
	++ m
ENDWHILE

LVAR_FLOAT bike_attached_x bike_attached_y bike_attached_z
bike_attached_x = 0.6
bike_attached_y = 0.0
bike_attached_z = 1.9

/* Old values
bike_attached_x = 0.6
bike_attached_y = -3.0
bike_attached_z = 1.0
*/



LVAR_INT bikes_attached
bikes_attached = 0

LVAR_FLOAT truck_speed
truck_speed = 15.0

v = 0


LVAR_INT hst4_newcut hst4_cutguy
hst4_newcut = 0












//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
//
//SET_CHAR_COORDINATES scplayer 2024.8704 1008.6201 9.8127
//SET_CHAR_HEADING scplayer 342.8127
//SET_CAMERA_BEHIND_PLAYER 
//RESTORE_CAMERA_JUMPCUT
//
//DO_FADE 500 FADE_IN
//WHILE GET_FADING_STATUS
//    WAIT 0
//ENDWHILE
//
//SET_CAMERA_BEHIND_PLAYER 
//RESTORE_CAMERA_JUMPCUT
//SET_PLAYER_CONTROL player1 ON
//
//
//PRINT_NOW HEI4_01 8000 1//Steal the police bikes and 'deliver' them to the Packer.

//debug

/*
CREATE_CAR COPCARVG 2304.4399 2453.7700 12.8203 hst4_stationcar
SET_CAR_HEADING hst4_stationcar 185.0
OPEN_CAR_DOOR hst4_stationcar FRONT_LEFT_DOOR
SET_LOAD_COLLISION_FOR_CAR_FLAG hst4_stationcar FALSE
*/




CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 2311.0193 2438.3679 9.8203 hst4_larrycop
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_larrycop FALSE
SET_CHAR_DECISION_MAKER hst4_larrycop hst4_emptydm
SET_CHAR_HEADING hst4_larrycop 297.0
GIVE_WEAPON_TO_CHAR hst4_larrycop WEAPONTYPE_PISTOL 2000


CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 2311.8501 2439.3745 9.8203 hst4_staircop
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_staircop FALSE

SET_CHAR_DECISION_MAKER hst4_staircop hst4_emptydm
SET_CHAR_HEADING hst4_staircop 115.0
GIVE_WEAPON_TO_CHAR hst4_staircop WEAPONTYPE_PISTOL 2000


CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 1612.2440 1845.8546 9.8280 hst4_hospitalcop
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_hospitalcop FALSE

SET_CHAR_DECISION_MAKER hst4_hospitalcop hst4_emptydm
SET_CHAR_HEADING hst4_hospitalcop 110.0
GIVE_WEAPON_TO_CHAR hst4_hospitalcop WEAPONTYPE_PISTOL 2000
SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE hst4_hospitalcop KNOCKOFFBIKE_EASY

CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 2768.7971 1431.2390 9.4870 hst4_pizzacop
GIVE_WEAPON_TO_CHAR hst4_pizzacop WEAPONTYPE_PISTOL 2000
SET_CHAR_DECISION_MAKER hst4_pizzacop hst4_emptydm
SET_CHAR_HEADING hst4_pizzacop 111.0


MARK_MODEL_AS_NO_LONGER_NEEDED LAPDM1

CREATE_CHAR PEDTYPE_MISSION1 DWMYLC1  2767.5371 1424.8768 9.2195 hst4_pizzadrunk		  
SET_ANIM_GROUP_FOR_CHAR hst4_pizzadrunk drunkman
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_pizzadrunk FALSE







//TASK_PICK_UP_OBJECT hst4_pizzacop hst4_pizzabox 0.0 -0.3 -0.1 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
//GIVE_WEAPON_TO_CHAR hst4_pizzacop WEAPONTYPE_PISTOL 2000
SET_CHAR_DECISION_MAKER hst4_pizzadrunk hst4_emptydm
SET_CHAR_HEADING hst4_pizzadrunk 347.0
																			    
//CREATE_CAR FREEWAY 2760.3965 1424.7660 9.4004 hst4_pizzabike
//SET_CAR_HEADING hst4_pizzabike 163.0


CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 2619.8577 2339.2559 10.0081 hst4_hotelcop
SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE hst4_hotelcop KNOCKOFFBIKE_EASY
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_hotelcop FALSE


		  
GIVE_WEAPON_TO_CHAR hst4_hotelcop WEAPONTYPE_PISTOL 2000

SET_CHAR_DECISION_MAKER hst4_hotelcop hst4_emptydm
SET_CHAR_HEADING hst4_hotelcop 300.0


CREATE_CAR FREEWAY 2634.0110 2343.2122 9.6797 hst4_hotelbike1 
SET_CAR_HEADING hst4_hotelbike1 120.0386
SET_LOAD_COLLISION_FOR_CAR_FLAG hst4_hotelbike1 FALSE


CREATE_CAR FREEWAY 2625.6897 2336.8503 9.6875 hst4_hotelbike2
SET_CAR_HEADING hst4_hotelbike2 183.0386
SET_LOAD_COLLISION_FOR_CAR_FLAG hst4_hotelbike2 FALSE


CREATE_CAR FREEWAY 2637.8474 2347.5764 9.6797 hst4_hotelbike3
SET_CAR_HEADING hst4_hotelbike3 126.0963
SET_LOAD_COLLISION_FOR_CAR_FLAG hst4_hotelbike3 FALSE


CREATE_CHAR_INSIDE_CAR hst4_hotelbike1 PEDTYPE_MISSION1 DWMYLC1 hst4_hotelthug1
SET_CHAR_DECISION_MAKER hst4_hotelthug1 hst4_emptydm
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_hotelthug1 FALSE


CREATE_CHAR_INSIDE_CAR hst4_hotelbike2 PEDTYPE_MISSION1 DWMOLC1 hst4_hotelthug2
SET_CHAR_DECISION_MAKER hst4_hotelthug2 hst4_emptydm
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_hotelthug2 FALSE


CREATE_CHAR_INSIDE_CAR hst4_hotelbike3 PEDTYPE_MISSION1 DWMOLC2 hst4_hotelthug3
SET_CHAR_DECISION_MAKER hst4_hotelthug3 hst4_emptydm
//SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_hotelthug3 FALSE





MARK_MODEL_AS_NO_LONGER_NEEDED dwmylc1
MARK_MODEL_AS_NO_LONGER_NEEDED dwmolc1
MARK_MODEL_AS_NO_LONGER_NEEDED dwmolc2

MARK_MODEL_AS_NO_LONGER_NEEDED FREEWAY









//TASK_PLAY_ANIM hst4_staircop M_smkstnd_loop SMOKING 4.0 1 FALSE FALSE FALSE -1
//TASK_PLAY_ANIM hst4_larrycop M_smkstnd_loop SMOKING 4.0 1 FALSE FALSE FALSE -1

TASK_CHAT_WITH_CHAR hst4_staircop hst4_larrycop  true true //ped0 will lead the chatting
TASK_CHAT_WITH_CHAR hst4_larrycop hst4_staircop false true //ped1 will follow ped0 at chatting
		   


TASK_PLAY_ANIM hst4_hospitalcop M_smkstnd_loop SMOKING 4.0 1 FALSE FALSE FALSE -1
//TASK_PLAY_ANIM hst4_pizzacop M_smkstnd_loop SMOKING 4.0 1 FALSE FALSE FALSE -1

SWITCH_COPS_ON_BIKES OFF
//SWITCH_COPS_ON_BIKES OFF // so the player doesn't get confused and steal a non-mission cop bike

timera = 0 // next prompt timer
timerb = 0 // used to spawn chopper after a few seconds for script memory 


SET_CAR_DENSITY_MULTIPLIER 1.5

//debug
																																	  
//SET_CHAR_COORDINATES scplayer 2771.8772 1426.9630 9.7718 // pizza
//SET_CHAR_COORDINATES scplayer 2620.7034 2331.8621 9.8203 // rock hotel
//SET_CHAR_COORDINATES scplayer 2316.9353 2457.0063 9.8300 // police station
//SET_CHAR_COORDINATES scplayer 1593.8617 1831.1394 10.000 // hospital
//SET_CHAR_COORDINATES scplayer 1546.0275 1835.5792 8.9227 // heli check

//LOAD_SCENE 2031.1681 1030.9177 9.8130
//REQUEST_COLLISION 2031.1681 1030.9177 //9.8130
//
//
//
//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
//
//SET_CHAR_COORDINATES scplayer 2024.8704 1008.6201 9.8127
//SET_CHAR_HEADING scplayer 265.8120 //342.8127
//SET_CAMERA_BEHIND_PLAYER 
//RESTORE_CAMERA_JUMPCUT
//
//WAIT 1500
//
//
//DO_FADE 1000 FADE_IN
//WHILE GET_FADING_STATUS
//    WAIT 0
//ENDWHILE
//
//SET_CAMERA_BEHIND_PLAYER 
//RESTORE_CAMERA_JUMPCUT
//SET_PLAYER_CONTROL player1 ON


//PRINT_NOW HEI4_01 8000 1//Steal the police bikes and 'deliver' them to the Packer.




IF NOT IS_CHAR_DEAD truck_driver
IF NOT IS_CAR_DEAD bike_truck
	CLEAR_CHAR_TASKS truck_driver
	CLEAR_AREA 2710.5249 1731.0090 100.0 100.0 FALSE

	v = 0		// new!

   	TASK_CAR_DRIVE_TO_COORD truck_driver bike_truck gotos_x[v] gotos_y[v] 6.0 15.0 MODE_NORMAL FALSE DRIVINGMODE_SLOWDOWNFORCARS
ENDIF
ENDIF




// Truck attachment noise
truck_thumbsfx = 0


// Tyre screech
sfx_tyres = 0



// ************************************* MISSION LOOP **************************************



//LOAD_SCENE 2031.1681 1030.9177 9.8130
//REQUEST_COLLISION 2031.1681 1030.9177 //9.8130



									   

//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
//SET_CHAR_COORDINATES scplayer 2024.8704 1008.6201 9.8127
//SET_CHAR_HEADING scplayer 342.8127
//SET_CAMERA_BEHIND_PLAYER 
//RESTORE_CAMERA_JUMPCUT

//WAIT 1000


/*
SET_CHAR_COORDINATES scplayer 2033.9742 1017.2899 9.8203
SET_CHAR_HEADING scplayer 288.0
SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT
*/


CREATE_CHAR PEDTYPE_MISSION1 triada 2029.5605 1006.0410 9.8203 hst4_cutguy
SET_CHAR_HEADING hst4_cutguy 280.0
SET_CHAR_DECISION_MAKER hst4_cutguy hst4_emptydm

MARK_MODEL_AS_NO_LONGER_NEEDED triada
MARK_MODEL_AS_NO_LONGER_NEEDED wmymech




SET_CHAR_COORDINATES scplayer 2028.8745 1006.8702 9.8203
SET_CHAR_HEADING scplayer 265.0
CLEAR_AREA 2033.9742 1017.2899 9.8203 1000.0 FALSE

WAIT 1000

/*
DO_FADE 1000 FADE_IN
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE
PRINT_NOW HEI4_01 10000 1
*/
//SET_CHAR_COORDINATES scplayer 2033.9742 1017.2899 9.8203
//SET_CHAR_HEADING scplayer 288.0


/*
SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
*/

SWITCH_WIDESCREEN ON
SET_FIXED_CAMERA_POSITION 2025.9052 1003.5439 12.1278 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2026.7261 1004.0738 11.9155 JUMP_CUT

IF NOT IS_CHAR_DEAD hst4_cutguy
	TASK_TURN_CHAR_TO_FACE_CHAR hst4_cutguy scplayer
  	TASK_TURN_CHAR_TO_FACE_CHAR scplayer hst4_cutguy
ENDIF




DO_FADE 1000 FADE_IN
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE

/*
IF NOT IS_CHAR_DEAD scplayer
	IF NOT IS_CHAR_DEAD hst4_cutguy
		DISABLE_CHAR_SPEECH	scplayer FALSE
		DISABLE_CHAR_SPEECH	hst4_cutguy FALSE
		CLEAR_CHAR_TASKS scplayer
		CLEAR_CHAR_TASKS hst4_cutguy

		TASK_CHAT_WITH_CHAR scplayer hst4_cutguy  true true //ped0 will lead the chatting
		TASK_CHAT_WITH_CHAR hst4_cutguy scplayer false true //ped1 will follow ped0 at chatting
	ENDIF
ENDIF
*/

TASK_PLAY_ANIM scplayer IDLE_chat PED 4.0 1 FALSE FALSE FALSE -1




hst4_newcut = 1

timera = 0


mission_hst4_loop:	


IF hst4_newcutskip = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
		WHILE IS_BUTTON_PRESSED PAD1 CROSS
			WAIT 0
		ENDWHILE

		IF hst4_newcut > 1
			//write_debug shit
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
		   //	hst4_counter = 0
			hst4_newcut = 5
			hst4_newcutskip = 0
		ENDIF

		//IF ca3_gotplvan < 9
		  //	ca3_gotplvan = 9
		   //	ca3_ctskip_needed = 0 // Don't want to read PS2 joypad anymore
	   //ENDIF
	ENDIF
ENDIF





IF hst4_newcut = 1
   //IF timerb > 1000
	 hst4_counter = 16
	 //hst4_newcutskip= 1 

  	 hst4_newcut = 2
	 timera = 0
   //ENDIF
ENDIF

IF hst4_newcut = 2
       
   IF timera > 3000
	  hst4_newcutskip= 1
	
	  hst4_counter = 17
	  hst4_newcut = 3
	  timera = 0
	  CLEAR_CHAR_TASKS scplayer
	  TASK_PLAY_ANIM scplayer endchat_01 PED 4.0 1 FALSE FALSE FALSE -1

	  IF NOT IS_CHAR_DEAD hst4_cutguy
		TASK_PLAY_ANIM hst4_cutguy endchat_03 PED 4.0 1 FALSE FALSE FALSE -1
	  ENDIF	

   ENDIF
	
ENDIF

IF hst4_newcut = 3

	
	IF timera > 2000
	IF NOT IS_CHAR_DEAD hst4_cutguy
		CLEAR_CHAR_TASKS scplayer
 	
	
		CLEAR_CHAR_TASKS hst4_cutguy
		TASK_GO_STRAIGHT_TO_COORD hst4_cutguy 2030.4309 984.6912 9.8131 PEDMOVE_RUN -2
		//CLEAR_CHAR_TASKS scplayer
		//TASK_ACHIEVE_HEADING scplayer 305.0
		hst4_newcut = 4
		timera = 0
	ENDIF
	ENDIF

ENDIF




IF hst4_newcut = 4

   IF timera > 2000

	  hst4_newcut = 5
	 // CLEAR_PRINTS	
   ENDIF
ENDIF
	

IF hst4_newcut = 5
	hst4_newcutskip = 0

	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_CHAR_TASKS scplayer

   	SET_CHAR_HEADING scplayer 305.0
	SWITCH_WIDESCREEN OFF
	SET_CAMERA_BEHIND_PLAYER 
	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	ENABLE_CHAR_SPEECH scplayer 


	DELETE_CHAR hst4_cutguy
	CLEAR_PRINTS
   	//WRITE_DEBUG pish
	PRINT_NOW HEI4_01 8000 1
	timera = 0
	timerb = 0

	hst4_nextprompt = 2
	hst4_newcut = -99

ENDIF




// Audio fx

	IF sfx_tyres = 0
		LOAD_MISSION_AUDIO 3 SOUND_BIKE_GANG_WHEEL_SPIN
		sfx_tyres = 1
	ENDIF

	IF sfx_tyres = 1
	 	IF HAS_MISSION_AUDIO_LOADED 3
			sfx_tyres = 2
	 	ENDIF
	ENDIF





// ---- Load & Play Dialogue...
	IF NOT hst4_counter = 0
		IF hst4_audio_playing = 0
			IF HAS_MISSION_AUDIO_LOADED hst4_alt_slot
				CLEAR_MISSION_AUDIO hst4_alt_slot
			ENDIF
			hst4_audio_playing = 1
			hst4_audio_underway = 1
		ENDIF

		IF hst4_audio_playing = 1
			LOAD_MISSION_AUDIO hst4_audio_slot hst4_audio[hst4_counter]
			//GOSUB hst4_dialogue_pos
			//ATTACH_MISSION_AUDIO_TO_PED hst4_audio_slot hst4_audio_char
			hst4_audio_playing = 2
		ENDIF

		IF hst4_audio_playing = 2
		 	IF HAS_MISSION_AUDIO_LOADED hst4_audio_slot
				PLAY_MISSION_AUDIO hst4_audio_slot



//Potential issue resolved. If initial scripted cut was skipped while audio was loading, instructional text could be overwritten by subtitle.
				
				
				PRINT $hst4_text[hst4_counter] 10000 1

				//PRINT_NOW $hst4_text[hst4_counter] 10000 1
				hst4_audio_playing = 3
			ENDIF
		ENDIF

		IF hst4_audio_playing = 3
			IF HAS_MISSION_AUDIO_FINISHED hst4_audio_slot
				CLEAR_THIS_PRINT $hst4_text[hst4_counter]
				IF hst4_audio_slot = 1
					hst4_audio_slot = 2
					hst4_alt_slot = 1
				ELSE
					hst4_audio_slot = 1
					hst4_alt_slot = 2
				ENDIF
				hst4_counter = 0
				hst4_audio_playing = 0
			
				hst4_audio_underway = 0 // okay to cue up next piece of convo text

			ELSE
				IF NOT HAS_MISSION_AUDIO_LOADED hst4_alt_slot
					IF hst4_counter < 15
						hst4_ahead_counter = hst4_counter + 1
						LOAD_MISSION_AUDIO hst4_alt_slot hst4_audio[hst4_ahead_counter]
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
// End of dialogue loader / player



//Ramp blocking car removal segment

//GET_RANDOM_CAR_OF_TYPE_IN_AREA_NO_SAVE X1 Y1 X2 Y2 CarModel ReturnCarID
IF NOT IS_CAR_DEAD bike_truck

	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck -1.3 -40.0 -2.0 slowzoneX1 slowzoneY1 slowzoneZ1	
	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck  1.3 0.0 2.0 slowzoneX2 slowzoneY2 slowzoneZ2	    //was -7.2
	//GET_RANDOM_CAR_IN_SPHERE_NO_SAVE slowzoneX slowzoneY slowzoneZ 5.0 -1 slowzoneCar
	GET_RANDOM_CAR_OF_TYPE_IN_AREA_NO_SAVE slowzoneX1 slowzoneY1 slowzoneX2 slowzoneY2 -1 slowzoneCar

	GET_CAR_SPEED bike_truck bike_truckSpeed

ENDIF

IF NOT IS_CAR_DEAD slowzoneCar
	slowzoneSpeed = bike_truckSpeed - 4.0
	SET_CAR_CRUISE_SPEED slowzoneCar slowzoneSpeed
   //	WRITE_DEBUG_WITH_INT clear sztest
   //	WRITE_DEBUG_WITH_FLOAT bts bike_truckSpeed
   //	WRITE_DEBUG_WITH_FLOAT szc slowzoneSpeed
   //	sztest ++
   //	MARK_CAR_AS_NO_LONGER_NEEDED slowzoneCar
ENDIF

IF NOT IS_CAR_DEAD bike_truck
IF NOT IS_CHAR_DEAD truck_driver
	IF NOT IS_CHAR_IN_CAR truck_driver bike_truck
		IF NOT IS_CHAR_DEAD	 truck_thumb
			DETACH_CHAR_FROM_CAR truck_thumb
		ENDIF

		GOTO mission_hst4_failed
	ENDIF
ENDIF
ENDIF




IF bike_camera = 1
	IF timerb > 1000
		IF NOT IS_CAR_DEAD bike_truck
			GET_CAR_COORDINATES bike_truck truck_thumbX truck_thumbY truck_thumbZ
			//ADD_ONE_OFF_SOUND truck_thumbX truck_thumbY truck_thumbZ truck_thumbsfx//SOUND_BIKE_PACKER_CLUNK
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_BIKE_PACKER_CLUNK
			bike_camera = 2
		ENDIF
	ENDIF
ENDIF

IF bike_camera = 2
	IF timerb > 1500
		
		IF NOT IS_CHAR_DEAD truck_thumb
			IF NOT IS_CHAR_PLAYING_ANIM truck_thumb Hiker_Pose_L 
				TASK_PLAY_ANIM truck_thumb Hiker_Pose_L MISC 4.0 FALSE FALSE FALSE TRUE -1
			ENDIF
		ENDIF
		bike_camera = 3
	ENDIF
ENDIF

IF bike_camera = 3
	IF timerb > 2000
		
	   //	TASK_LEAVE_ANY_CAR scplayer
	    bike_camera = 4
	ENDIF 
ENDIF


IF bike_camera = 4
	IF timerb > 4000

		CLEAR_CHAR_TASKS scplayer
	  	SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT
		//FREEZE_CHAR_POSITION scplayer FALSE
		SET_PLAYER_CONTROL player1 ON
		IF bikes_attached = 4
			CLEAR_PRINTS
		 
		ENDIF
		IF bikes_attached = 1
			CLEAR_PRINTS
			PRINT_NOW HEI4_06 5000 1//First Bike Delivered! Jump off and go steal the next bike.
		ENDIF
		IF bikes_attached = 2
			CLEAR_PRINTS
			PRINT_NOW HEI4_08 5000 1//Second Bike Delivered! Jump off and go steal the next bike.
		ENDIF
		IF bikes_attached = 3
			CLEAR_PRINTS
			PRINT_NOW HEI4_09 5000 1//Third Bike Delivered! Jump off and go steal the next bike.
			timera = 0
		ENDIF

		IF NOT IS_CHAR_DEAD truck_thumb
			CLEAR_CHAR_TASKS truck_thumb
		ENDIF
		bike_camera = 0

	ENDIF
ENDIF

  
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_hst4_passed  
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_w
	hst4_deliverytimer = hst4_deliverytimer + 60000
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_p
	ALTER_WANTED_LEVEL Player1 2
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_o
	ALTER_WANTED_LEVEL Player1 0
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_q
	SET_CHAR_COORDINATES scplayer 2608.1929 2315.5396 9.6719
	SET_CHAR_HEADING scplayer 354.7109  // rock hotel
ENDIF


IF hst4_finalcutskip = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
		WHILE IS_BUTTON_PRESSED PAD1 CROSS
			WAIT 0
		ENDWHILE

		hst4_finalcut = 50
		hst4_finalcutskip = 0

	ENDIF
ENDIF


WAIT 0
	
IF siren_fix = 0	
IF NOT IS_CAR_DEAD motorbike[1]
	IF IS_CHAR_SITTING_IN_CAR scplayer motorbike[1]
		SWITCH_CAR_SIREN motorbike[1] OFF
		siren_fix = 1
	ENDIF
ENDIF
ENDIF

IF hst4_spawnchopper = 0
	IF timerb > 5000
		MARK_MODEL_AS_NO_LONGER_NEEDED COPCARVG

		REQUEST_MODEL POLMAV
		WHILE NOT HAS_MODEL_LOADED POLMAV
			WAIT 0
		ENDWHILE	
			
		CREATE_CAR POLMAV 1589.1976 1850.6193 9.8358 hst4_hospitalchopper
	   //	SET_LOAD_COLLISION_FOR_CAR_FLAG hst4_hospitalchopper FALSE

		SET_CAR_HEADING hst4_hospitalchopper 51.0
		OPEN_CAR_DOOR hst4_hospitalchopper FRONT_LEFT_DOOR
		CREATE_CHAR_INSIDE_CAR hst4_hospitalchopper PEDTYPE_MISSION1 LAPDM1 hst4_hospitalpilot
		SET_LOAD_COLLISION_FOR_CHAR_FLAG hst4_hospitalpilot FALSE
		SET_HELI_BLADES_FULL_SPEED hst4_hospitalchopper

		MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
	   //	write_debug spawnchopper

	
		hst4_spawnchopper = 1
	ENDIF


ENDIF	  




IF hst4_nextprompt = 2
	IF timera > 12000

		IF hst4_clockfix = 0
			CLEAR_PRINTS
			CLEAR_THIS_PRINT HEI4_01
		   	PRINT_NOW HEI4_11 8000 1 // The packer truck is circling the outskirts of town
			DISPLAY_ONSCREEN_TIMER_WITH_STRING hst4_deliverytimer TIMER_DOWN HEI4_12
			hst4_clockfix = 1
			hst4_nextprompt = 3
			timera = 0
		ENDIF
	 
			
	ENDIF
ENDIF

IF hst4_nextprompt = 3
	IF timera > 10000
	   //	PRINT_NOW HEI4_14 8000 1
		hst4_nextprompt = 4
	ENDIF
ENDIF


IF hst4_deliverytimer < 1
	CLEAR_PRINTS
	PRINT_NOW HEI4_10 8000 1
	GOTO mission_hst4_failed
ENDIF






IF hst4_finalcut = 0		  
IF NOT IS_CAR_DEAD bike_truck
//	SET_CAR_COORDINATES	bike_truck 0.0 0.0 40.0
//	SET_CAR_HEADING bike_truck 0.0
	IF LOCATE_CAR_2D bike_truck gotos_x[v] gotos_y[v] 15.0 15.0	0
		++ v
		IF v = 17 //5
			v = 0
		ENDIF
		//REMOVE_BLIP blip
		//ADD_BLIP_FOR_COORD gotos_x[v] gotos_y[v] 6.0 blip





		//changed
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

		//CAR_GOTO_COORDINATES bike_truck	gotos_x[v] gotos_y[v] 6.0
	   //	  
		 IF NOT IS_CHAR_DEAD truck_driver
		 	 CLEAR_CHAR_TASKS truck_driver
		 	 TASK_CAR_DRIVE_TO_COORD truck_driver bike_truck gotos_x[v] gotos_y[v] 8.0 15.0 MODE_NORMAL FALSE DRIVINGMODE_SLOWDOWNFORCARS
		 ENDIF
			
		
		
		
		//WRITE_DEBUG_WITH_INT gtvec v
	ENDIF

	m = 0
	WHILE m < 4
		IF bike_attached_flag[m] < 2
			IF NOT IS_CAR_DEAD motorbike[m]
				IF IS_CHAR_IN_CAR scplayer motorbike[m]
				   		IF bike_attached_flag[m] = 0
						a = 0
						WHILE a < 4
							REMOVE_BLIP	motorbike_blip[a]
							++ a
						ENDWHILE
						REMOVE_BLIP mission_blip
						ADD_BLIP_FOR_CAR bike_truck mission_blip
						//hst4_packerblip = 1
						SET_BLIP_AS_FRIENDLY mission_blip TRUE
						//CHANGE_BLIP_COLOUR mission_blip YELLOW
						
						//Steve T.
							
						IF NOT bikes_attached = 4
							PRINT_NOW HEI4_02 8000 1//Drive the bike onto the back of the Packer.
						ENDIF
						bike_attached_flag[m] = 1
						
						GOSUB hst4_onwhichbike_check
						
						

					ENDIF
					IF bike_attached_flag[m] = 1
						//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck -1.3 -6.2 -0.8 x y z
						//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck 1.3 -6.2 2.6 x2 y2 z2

						IF bikes_attached < 2
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck -1.3 -3.2 -0.8 x y z	   //was -7.2
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck 1.3 -3.2 2.6 x2 y2 z2		// was -7.2
						ENDIF

						IF bikes_attached > 1
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck -1.3 -8.2 -0.8 x y z	   //was -7.2
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS bike_truck 1.3 -8.2 2.6 x2 y2 z2		// was -7.2
						ENDIF

																								
						IF LOCATE_CHAR_IN_CAR_CAR_2D scplayer bike_truck 30.0 30.0 0
							PRINT_NOW HEI4_13 8000 1//Drive the bike onto the back of the Packer.
						ENDIF
						IF IS_CHAR_IN_ANGLED_AREA_IN_CAR_3D scplayer x y z x2 y2 z2 4.8 0 // was 6.8 0
							LVAR_FLOAT bike_truck_speed players_speed
							GET_CAR_SPEED bike_truck bike_truck_speed
							GET_CAR_SPEED motorbike[m] players_speed
						  	  bike_truck_speed += 10.0 //10.0
						   	  IF players_speed < bike_truck_speed
							  bike_truck_speed -= 10.0//20.0
								IF players_speed > bike_truck_speed
								  
									ATTACH_CAR_TO_CAR motorbike[m] bike_truck bike_attached_x bike_attached_y bike_attached_z 16.0 0.0 0.0
									FREEZE_CAR_POSITION motorbike[m] TRUE
									IF bikes_attached = 1
									OR bikes_attached = 3
										bike_attached_y -= 2.5
										bike_attached_z -= 0.7
									ENDIF
									IF bike_attached_x = 0.6
										bike_attached_x = -0.6
									ELSE
										bike_attached_x = 0.6
									ENDIF


									//

								   	++ bikes_attached
									//bikes_attached = 4

									// Test area







									IF bikes_attached < 4
										ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE bike_truck 3.0 5.0 2.2 bike_truck 0.0 JUMP_CUT	 //3.0 6.0 2.0
										
										//ATTACH_CAMERA_TO_VEHICLE_LOOK_AT_VEHICLE bike_truck 4.0 -6.0 2.5 bike_truck 0.0 JUMP_CUT
										bike_camera = 1
									   	//FREEZE_CHAR_POSITION scplayer TRUE

										SET_PLAYER_CONTROL player1 OFF
										CLEAR_PRINTS
										timerb = 0
									ENDIF

									truck_speed += 10.0
									bike_attached_flag[m] = 2
									REMOVE_BLIP mission_blip
									CLEAR_THIS_PRINT HEI4_02
									//WRITE_DEBUG_WITH_INT ba bikes_attached
//											IF bikes_attached = 4
//												CLEAR_PRINTS
//											 
//											ENDIF
//											IF bikes_attached = 1
//												CLEAR_PRINTS
//												PRINT_NOW HEI4_06 5000 1//First Bike Delivered! Jump off and go steal the next bike.
//											ENDIF
//											IF bikes_attached = 2
//												CLEAR_PRINTS
//												PRINT_NOW HEI4_08 5000 1//Second Bike Delivered! Jump off and go steal the next bike.
//											ENDIF
//											IF bikes_attached = 3
//												CLEAR_PRINTS
//												PRINT_NOW HEI4_09 5000 1//Third Bike Delivered! Jump off and go steal the next bike.
//												timera = 0
//											ENDIF

									a = 0
									WHILE a < 4
										IF bike_attached_flag[a] < 2
											IF motorbike_created[a] = 1
												REMOVE_BLIP motorbike_blip[a]
												IF NOT IS_CAR_DEAD motorbike[a]
													ADD_BLIP_FOR_CAR motorbike[a] motorbike_blip[a]
													CHANGE_BLIP_COLOUR motorbike_blip[a] GREEN
												ENDIF
											ELSE
												ADD_BLIP_FOR_COORD motorbike_x[a] motorbike_y[a] motorbike_z[a] motorbike_blip[a]
											ENDIF
											CHANGE_BLIP_COLOUR motorbike_blip[a] GREEN
											//SET_BLIP_AS_FRIENDLY motorbike_blip[a] FALSE

											/*
											IF bikes_attached = 4
												CLEAR_PRINTS
												PRINT_NOW HEI4_07 5000 1//All Bikes Delivered! Jump off.
											ENDIF
											IF bikes_attached = 1
												CLEAR_PRINTS
												PRINT_NOW HEI4_06 5000 1//First Bike Delivered! Jump off and go steal the next bike.
											ENDIF
											IF bikes_attached = 2
												CLEAR_PRINTS
												PRINT_NOW HEI4_08 5000 1//Second Bike Delivered! Jump off and go steal the next bike.
											ENDIF
											IF bikes_attached = 3
												CLEAR_PRINTS
												PRINT_NOW HEI4_09 5000 1//Third Bike Delivered! Jump off and go steal the next bike.
												timera = 0
											ENDIF
											*/

										ENDIF
										++ a
									ENDWHILE
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF bike_attached_flag[m] = 1
						a = 0
						WHILE a < 4
							IF bike_attached_flag[a] = 1
								bike_attached_flag[a] = 0
							ENDIF
							IF bike_attached_flag[a] < 2
								REMOVE_BLIP	motorbike_blip[a]
								IF motorbike_created[a] = 1
									if not is_car_dead motorbike[a]
										ADD_BLIP_FOR_CAR motorbike[a] motorbike_blip[a]
										CHANGE_BLIP_COLOUR motorbike_blip[a] GREEN
									else
										PRINT_NOW HEI4_04 8000 1//~r~You destroyed a bike you needed to steal.
										GOTO mission_hst4_failed
									endif
								ELSE
									ADD_BLIP_FOR_COORD motorbike_x[a] motorbike_y[a] motorbike_z[a] motorbike_blip[a]
								ENDIF
								remove_blip mission_blip
								CHANGE_BLIP_COLOUR motorbike_blip[a] GREEN
								PRINT_NOW HEI4_03 5000 1//You need to steal a cop bike.
							ENDIF
							++ a
						ENDWHILE
//						IF bike_attached_flag[m] = 1
//							ADD_BLIP_FOR_CAR motorbike[m] motorbike_blip[m]
//							bike_attached_flag[m] = 0
//						ENDIF
					ENDIF
//					IF bike_attached_flag[m] = 0
//						IF LOCATE_CHAR_ANY_MEANS_2D SCPLAYER COPBIKE_X[M] COPBIKE_Y[M]
//					ENDIF
				ENDIF
			ELSE
				IF motorbike_created[m] = 0
					IF LOCATE_CHAR_ANY_MEANS_2D scplayer motorbike_x[m]	motorbike_y[m] 200.0 200.0 0
						LVAR_INT motorbike[4] motorbike_blip[4]
					   	CLEAR_AREA motorbike_x[m] motorbike_y[m] motorbike_z[m] 40.0 FALSE
						CREATE_CAR COPBIKE motorbike_x[m] motorbike_y[m] motorbike_z[m] motorbike[m]
						SET_CAR_HEADING	motorbike[m] motorbike_h[m]
						SET_CAN_BURST_CAR_TYRES motorbike[m] FALSE
						SET_LOAD_COLLISION_FOR_CAR_FLAG motorbike[m] FALSE
						IF NOT DOES_BLIP_EXIST mission_blip //fix for blip appearing when heading for packer
							REMOVE_BLIP	motorbike_blip[m]
							ADD_BLIP_FOR_CAR motorbike[m] motorbike_blip[m]
							//write_debug check1
							CHANGE_BLIP_COLOUR motorbike_blip[m] GREEN
						ENDIF
						motorbike_created[m] = 1
					ENDIF
				ELSE
					PRINT_NOW HEI4_04 5000 1//~r~You destroyed a bike you needed to steal.
					GOTO mission_hst4_failed
				ENDIF
			ENDIF
		ENDIF
		++ m
	ENDWHILE
	
	IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer bike_truck 120.0 120.0	0
		SET_CAR_CRUISE_SPEED bike_truck	truck_speed
	ELSE
		SET_CAR_CRUISE_SPEED bike_truck	15.0 //15.0
	ENDIF

	IF IS_CAR_STUCK_ON_ROOF bike_truck
	OR IS_CAR_STUCK	bike_truck
		IF NOT IS_CAR_ON_SCREEN bike_truck
			//GET_CAR_COORDINATES bike_truck x y z
			//z += 10.0
			//SET_CAR_COORDINATES	bike_truck x y z
			//GET_CAR_LAST_ROUTE_COORDS bike_truck x y z heading
			IF NOT IS_POINT_ON_SCREEN gotos_x[v] gotos_y[v] 6.0 4.0
				//IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x y z 4.0 4.0 3.0
					CLEAR_AREA gotos_x[v] gotos_y[v] 7.0 10.0 FALSE

					SET_CAR_COORDINATES bike_truck gotos_x[v] gotos_y[v] 10.0
				   //	WRITE_DEBUG_WITH_INT stuck v
					SET_CAR_HEADING bike_truck heading
					//CAR_GOTO_COORDINATES bike_truck	gotos_x[v] gotos_y[v] 6.0
				//ENDIF
			ENDIF
		ENDIF
	ENDIF										   
ELSE
	PRINT_NOW HEI4_05 5000 1//PACKER DESTROYED!
	IF NOT IS_CHAR_DEAD truck_thumb
	
		DELETE_CHAR truck_thumb

	ENDIF

	GOTO mission_hst4_failed
ENDIF
ENDIF


IF hst4_finalcut = 0
	IF bikes_attached = 4 //should be 4
	//IF NOT IS_CHAR_IN_ANY_CAR scplayer
		CLEAR_ONSCREEN_TIMER hst4_deliverytimer

	 	hst4_finalcut = 1
	ENDIF
ENDIF

IF hst4_finalcut = 1
		
		//GOTO mission_hst4_passed
		DO_FADE 1000 FADE_OUT

		//LOAD_SCENE 1009.7808 1929.3772 9.6719
		//WAIT 0


		CLEAR_PRINTS
		CLEAR_ONSCREEN_TIMER hst4_deliverytimer

		SET_PLAYER_CONTROL player1 OFF

		CLEAR_WANTED_LEVEL player1 

	   
	  
		WHILE GET_FADING_STATUS
      		WAIT 0
		ENDWHILE 
		TASK_JUMP scplayer FALSE
	   	//FREEZE_CHAR_POSITION scplayer FALSE

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

	  	LOAD_SCENE_IN_DIRECTION 1009.7808 1929.3772 9.6719 122.0
		WAIT 0

		SWITCH_WIDESCREEN ON
		hst4_finalcut = 10
	   //	SET_FIXED_CAMERA_POSITION 1016.6241 1905.8065 12.0470 0.0 0.0 0.0
	   //	POINT_CAMERA_AT_POINT 1015.8773 1905.1543 11.9165 JUMP_CUT
		
		 SET_FIXED_CAMERA_POSITION 1022.8307 1899.5132 18.6192 0.0 0.0 0.0
		 POINT_CAMERA_AT_POINT 1021.9608 1899.5178 18.126 JUMP_CUT
		
		IF NOT IS_CHAR_DEAD truck_driver
			IF NOT IS_CAR_DEAD bike_truck
			  //	TASK_JUMP scplayer FALSE
				CLEAR_CHAR_TASKS truck_driver
				CLEAR_AREA 1009.9940 1880.0204 150.0 150.0 FALSE
				SET_CAR_COORDINATES bike_truck 1009.9940 1870.0204 9.6797
				SET_CAR_HEADING bike_truck 2.3

				ATTACH_CHAR_TO_CAR scplayer bike_truck	0.5 -10.0 -0.3 FACING_BACK 10.0 WEAPONTYPE_UNARMED
			   //SET_CHAR_COORDINATES scplayer 1010.4019 1917.8384 9.6797
				SET_CHAR_HEADING scplayer 300.0


				TASK_CAR_DRIVE_TO_COORD truck_driver bike_truck 1009.7808 1900.3772 9.6719 15.0 MODE_NORMAL FALSE DRIVINGMODE_SLOWDOWNFORCARS
			ENDIF
		ENDIF
		
   		CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE

		DO_FADE 500 FADE_IN

		WHILE GET_FADING_STATUS
      		WAIT 0
			CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE
		ENDWHILE 
		CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE

	   //	SET_PLAYER_CONTROL player1 ON

		



	//ENDIF
ENDIF

IF hst4_finalcut = 10

	

	CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE
	IF NOT IS_CAR_DEAD bike_truck
		IF NOT IS_CHAR_DEAD truck_driver
			IF IS_CAR_STOPPED bike_truck
				hst4_finalcut = 20
				hst4_finalcutskip = 1
				DETACH_CHAR_FROM_CAR scplayer
				OPEN_SEQUENCE_TASK hst4_endseq
					
					

				TASK_JUMP -1 TRUE
			   //	TASK_GO_STRAIGHT_TO_COORD -1 1013.4575 1914.2784 9.8125 PEDMOVE_RUN 10000
				TASK_GO_STRAIGHT_TO_COORD -1 1014.0134 1902.3248 9.8125 PEDMOVE_RUN 10000

			   	TASK_TURN_CHAR_TO_FACE_CHAR -1 truck_driver
		   		CLOSE_SEQUENCE_TASK hst4_endseq
		   		PERFORM_SEQUENCE_TASK scplayer hst4_endseq
		   		CLEAR_SEQUENCE_TASK hst4_endseq
				TIMERA = 0
			   //	hst4_convo_counter = 1


			ENDIF
		ENDIF
	ENDIF

	
ENDIF


IF hst4_convo_counter > 0
	IF hst4_audio_underway = 0
		
			SWITCH hst4_convo_counter
				CASE 1
					hst4_counter = 1
					hst4_convo_counter =  2
					BREAK
				CASE 2
					hst4_counter = 2
					hst4_convo_counter = 3
					BREAK
			   	CASE 3 
					hst4_counter = 3
					hst4_convo_counter =  4
					BREAK
				CASE 4
					hst4_counter = 0
					hst4_convo_counter = 0

					BREAK
			 

			ENDSWITCH
		   //	sw7_audio_underway = 1
	ENDIF

ENDIF



IF hst4_finalcut = 20

//	IF timera > 500
//	  hst4_finalcutskip = 1
//	ENDIF
	
	
	CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE

	IF timera > 3000
		hst4_convo_counter = 1
		//PRINT HEI4_30 6000 1
		hst4_finalcut = 30
		timera = 0
	ENDIF

ENDIF


IF hst4_finalcut = 30
	 CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE


	 IF timera > 3000
		IF NOT IS_CHAR_DEAD truck_driver
			IF NOT IS_CAR_DEAD bike_truck
		  
				hst4_finalcut = 40
				TASK_CAR_DRIVE_TO_COORD truck_driver bike_truck 1009.7808 2000.3772 9.6719 15.0 MODE_NORMAL FALSE DRIVINGMODE_SLOWDOWNFORCARS
				timera = 0
			ENDIF
		ENDIF

	ENDIF

ENDIF


IF hst4_finalcut = 40
	CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE


	IF timera > 500
	IF NOT IS_CHAR_DEAD truck_driver
	
		
		TASK_LOOK_AT_CHAR scplayer truck_driver	-2
		hst4_finalcut = 45
		timera = 0
	ENDIF
	ENDIF

ENDIF

IF hst4_finalcut = 45
	CLEAR_AREA 1009.9940 1910.0204 150.0 150.0 FALSE


	IF timera > 3000
		hst4_finalcut = 50

	ENDIF

ENDIF



IF hst4_finalcut = 50
	hst4_finalcutskip = 0
	DELETE_CAR motorbike[0]
	DELETE_CAR motorbike[1]
	DELETE_CAR motorbike[2]
	DELETE_CAR motorbike[3]
	DELETE_CAR bike_truck
	DELETE_CHAR truck_driver
	DELETE_CHAR truck_thumb

	

	SWITCH_WIDESCREEN OFF
	CLEAR_CHAR_TASKS scplayer
	SET_CHAR_COORDINATES scplayer 1014.0134 1902.3248 9.8125
	SET_CHAR_HEADING scplayer 59.0
	
	RESTORE_CAMERA_JUMPCUT				   
	SET_CAMERA_BEHIND_PLAYER
	SET_PLAYER_CONTROL player1 ON
  
	CLEAR_PRINTS
	GOTO mission_hst4_passed


   
ENDIF









IF hst4_finalcut = 0
	attached_bikeindex = 0
	WHILE attached_bikeindex < 4
		IF bike_attached_flag[attached_bikeindex] = 2
		   	IF IS_CAR_DEAD motorbike[attached_bikeindex]
				PRINT_NOW HEI4_04 5000 1//~r~You destroyed a bike you needed to steal.
				GOTO mission_hst4_failed
			ENDIF
		ENDIF
		attached_bikeindex ++
	ENDWHILE
ENDIF

	


// Artificial setpiece cop checks Use does_char_exist check in cas player shoots someone with sniper rifle

IF hst4_pizzasetpiece = 0
	IF DOES_CHAR_EXIST hst4_pizzacop
	   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_pizzacop scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 2
			GOSUB hst4_pizzadrunkbustsp

			IF NOT IS_CHAR_DEAD hst4_pizzacop
			hst4_pizzasetpiece = 1
			CLEAR_CHAR_TASKS hst4_pizzacop
			TASK_KILL_CHAR_ON_FOOT hst4_pizzacop scplayer
			ENDIF

			IF NOT IS_CHAR_DEAD hst4_pizzadrunk
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_pizzadrunk
			ENDIF


			//GOSUB hst4_choppertakeoff
		ENDIF

		IF NOT IS_CHAR_DEAD hst4_pizzacop
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer hst4_pizzacop 25.0 25.0 FALSE

		  		 GOSUB hst4_pizzadrunkbustsp
			ENDIF
		ENDIF
	ENDIF
	IF DOES_CHAR_EXIST hst4_pizzadrunk
	   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_pizzadrunk scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 1
			//GOSUB hst4_pizzadrunkbustsp
			IF NOT IS_CHAR_DEAD hst4_pizzadrunk
			TASK_SMART_FLEE_CHAR hst4_pizzadrunk scplayer 100.0 -1
			ENDIF

			IF NOT IS_CHAR_DEAD hst4_pizzacop
			hst4_pizzasetpiece = 1
			CLEAR_CHAR_TASKS hst4_pizzacop
			TASK_KILL_CHAR_ON_FOOT hst4_pizzacop scplayer
			ENDIF
			//GOSUB hst4_choppertakeoff
		ENDIF
	ENDIF

ENDIF

IF hst4_hotelsetpiece = 0
	IF DOES_CHAR_EXIST hst4_hotelcop
	   	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_hotelcop scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 1
 		GOSUB hst4_hotelthugsleavesp

			IF NOT IS_CHAR_DEAD hst4_hotelcop
			hst4_hotelsetpiece = 1
			CLEAR_CHAR_TASKS hst4_hotelcop
			TASK_KILL_CHAR_ON_FOOT hst4_hotelcop scplayer
			ENDIF

			IF NOT IS_CHAR_DEAD hst4_hotelthug1
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug1
			ENDIF

			IF NOT IS_CHAR_DEAD hst4_hotelthug2
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug2
			ENDIF

			IF NOT IS_CHAR_DEAD hst4_hotelthug3
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug3
			ENDIF

			//GOSUB hst4_choppertakeoff
		ENDIF

		IF NOT IS_CHAR_DEAD hst4_hotelcop
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer hst4_hotelcop 30.0 30.0 FALSE

		  		GOSUB hst4_hotelthugsleavesp

			ENDIF
		ENDIF
	ENDIF
	IF DOES_CHAR_EXIST hst4_hotelthug1
	   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_hotelthug1 scplayer

			IF NOT IS_CHAR_DEAD hst4_hotelcop
			hst4_hotelsetpiece = 1
			CLEAR_CHAR_TASKS hst4_hotelcop
			TASK_KILL_CHAR_ON_FOOT hst4_hotelcop scplayer
			ENDIF

			
			//GOSUB hst4_pizzadrunkbustsp
		  	IF NOT IS_CHAR_DEAD hst4_hotelthug1
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug1
			ENDIF
			IF NOT IS_CHAR_DEAD hst4_hotelthug2
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug2
			ENDIF
			IF NOT IS_CHAR_DEAD hst4_hotelthug3
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug3
			ENDIF


			//GOSUB hst4_choppertakeoff
		ENDIF
	ENDIF
	IF DOES_CHAR_EXIST hst4_hotelthug2
	   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_hotelthug2 scplayer

			IF NOT IS_CHAR_DEAD hst4_hotelcop
			hst4_hotelsetpiece = 1
			CLEAR_CHAR_TASKS hst4_hotelcop
			TASK_KILL_CHAR_ON_FOOT hst4_hotelcop scplayer
			ENDIF



			//GOSUB hst4_pizzadrunkbustsp
			IF NOT IS_CHAR_DEAD hst4_hotelthug1
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug1
			ENDIF

		  	IF NOT IS_CHAR_DEAD hst4_hotelthug2
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug2
			ENDIF
			IF NOT IS_CHAR_DEAD hst4_hotelthug3
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug3
			ENDIF

			//GOSUB hst4_choppertakeoff
		ENDIF
	ENDIF
	IF DOES_CHAR_EXIST hst4_hotelthug3
	   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_hotelthug3 scplayer

			IF NOT IS_CHAR_DEAD hst4_hotelcop
			hst4_hotelsetpiece = 1
			CLEAR_CHAR_TASKS hst4_hotelcop
			TASK_KILL_CHAR_ON_FOOT hst4_hotelcop scplayer
			ENDIF



			//GOSUB hst4_pizzadrunkbustsp
	   		IF NOT IS_CHAR_DEAD hst4_hotelthug1
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug1
			ENDIF
			  	IF NOT IS_CHAR_DEAD hst4_hotelthug2
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug2
			ENDIF

	   		IF NOT IS_CHAR_DEAD hst4_hotelthug3
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug3
			ENDIF
			//GOSUB hst4_choppertakeoff
		ENDIF
	ENDIF

ENDIF

IF hst4_stationsetpiece = 0
	IF DOES_CHAR_EXIST hst4_staircop
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_staircop scplayer
				ALTER_WANTED_LEVEL_NO_DROP player1 2
			GOSUB hst4_staircheck
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST hst4_larrycop
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_larrycop scplayer
					ALTER_WANTED_LEVEL_NO_DROP player1 2
			GOSUB hst4_staircheck				  
		ENDIF
	ENDIF

	/*
	IF NOT IS_CAR_DEAD hst4_stationcar
		IF HAS_CAR_BEEN_DAMAGED_BY_CHAR	hst4_stationcar scplayer
			GOSUB hst4_staircheck
		ENDIF
		
		IF IS_CHAR_IN_CAR scplayer hst4_stationcar
			GOSUB hst4_staircheck

		ENDIF

	ENDIF
	*/
ENDIF

IF hst4_hospitalsetpiece = 0
	IF DOES_CHAR_EXIST hst4_hospitalcop
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_hospitalcop scplayer
			
			ALTER_WANTED_LEVEL_NO_DROP player1 2
			GOSUB hst4_choppertakeoffsp

			
			IF NOT IS_CHAR_DEAD hst4_hospitalcop
			hst4_hospitalsetpiece = 1
			CLEAR_CHAR_TASKS hst4_hospitalcop
			TASK_KILL_CHAR_ON_FOOT hst4_hospitalcop scplayer
			ENDIF
			//GOSUB hst4_choppertakeoff
		ENDIF

		IF NOT IS_CHAR_DEAD hst4_hospitalcop
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer hst4_hospitalcop 35.0 35.0 FALSE

		  		 GOSUB hst4_choppertakeoffsp
			ENDIF
		ENDIF

	ENDIF
	



ENDIF


// New area for testing

IF hst4_hospitalwanted = 0
IF DOES_CHAR_EXIST hst4_hospitalcop
		
	
	 IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_hospitalcop scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 2
			hst4_hospitalwanted = 1
	 ENDIF

ENDIF
ENDIF


IF hst4_hotelwanted = 0
IF DOES_CHAR_EXIST hst4_hotelcop
	
	 IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_hotelcop scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 2
			hst4_hotelwanted = 1
	 ENDIF
ENDIF
ENDIF


IF hst4_pizzawanted = 0
IF DOES_CHAR_EXIST hst4_pizzacop
	
	 IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_pizzacop scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 2
	 		hst4_pizzawanted = 1
	 ENDIF
ENDIF
ENDIF


IF hst4_larrywanted = 0
IF DOES_CHAR_EXIST hst4_larrycop
	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_larrycop scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 2
	 		hst4_larrywanted = 1
	 ENDIF
ENDIF
ENDIF

IF hst4_stairwanted = 0
IF DOES_CHAR_EXIST hst4_staircop
	 IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hst4_staircop scplayer
			ALTER_WANTED_LEVEL_NO_DROP player1 2
			hst4_stairwanted = 1
	 ENDIF
ENDIF
ENDIF



//IF hst4_choppertakeoff = 1
//	IF NOT IS_CHAR_DEAD hst4_hospitalcop
//		//CLEAR_CHAR_TASKS hst4_hospitalcop
//		//TASK_KILL_CHAR_ON_FOOT hst4_hospitalcop scplayer
//		hst4_choppertakeoff = 2
//	ENDIF
//ENDIF

IF hst4_choppertakeoff = 1
   //	write_debug to
	IF NOT IS_CAR_DEAD hst4_hospitalchopper
	IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer hst4_hospitalchopper 200.0 200.0 200.0 FALSE
	   //	WRITE_DEBUG chopgone
		hst4_choppertakeoff = 2
		MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hospitalpilot
		MARK_CAR_AS_NO_LONGER_NEEDED hst4_hospitalchopper
	ENDIF
	ENDIF
ENDIF




IF NOT IS_CHAR_DEAD hst4_hospitalcop //1
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hospitalcop 120.0 120.0 120.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hospitalcop FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hospitalcop TRUE
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD hst4_hotelcop  //2
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hotelcop 120.0 120.0 120.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelcop FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelcop TRUE
	ENDIF

ENDIF

//IF NOT IS_CHAR_DEAD hst4_hospitalpilot //3
//	
//	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hospitalpilot 70.0 70.0 70.0 FALSE
//		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hospitalpilot FALSE
//	ELSE
//		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hospitalpilot TRUE
//	ENDIF
//
//ENDIF



IF hst4_hotelthugsleave = 0

IF NOT IS_CHAR_DEAD hst4_hotelthug1	 //4
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hotelthug1 150.0 150.0 150.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelthug1 FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelthug1 TRUE
	ENDIF

ENDIF





IF NOT IS_CHAR_DEAD hst4_hotelthug2	  //5
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hotelthug2 150.0 150.0 150.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelthug2 FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelthug2 TRUE
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD hst4_hotelthug3	//6
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hotelthug3 150.0 150.0 150.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelthug3 FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_hotelthug3 TRUE
	ENDIF

ENDIF


ENDIF


IF hst4_hotelthugsleave = 1

IF NOT IS_CHAR_DEAD hst4_hotelthug1	 //4
	
	IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hotelthug1 120.0 120.0 150.0 FALSE
  		MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug1
		MARK_CAR_AS_NO_LONGER_NEEDED hst4_hotelbike1
	  //	write_debug ht1
  	ENDIF

ENDIF
IF NOT IS_CHAR_DEAD hst4_hotelthug2	  //5
	
	IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hotelthug2 120.0 120.0 150.0 FALSE
		MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug2
		MARK_CAR_AS_NO_LONGER_NEEDED hst4_hotelbike2

	   //	write_debug ht2
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD hst4_hotelthug3	//6
	
	IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_hotelthug3 120.0 120.0 150.0 FALSE
  		MARK_CHAR_AS_NO_LONGER_NEEDED hst4_hotelthug3
		MARK_CAR_AS_NO_LONGER_NEEDED hst4_hotelbike3

	   //	write_debug ht3
	ENDIF

ENDIF


ENDIF





IF NOT IS_CHAR_DEAD hst4_staircop	//7
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_staircop 120.0 120.0 120.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_staircop FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_staircop TRUE
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD hst4_larrycop	 //8
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_larrycop 120.0 120.0 120.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_larrycop FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_larrycop TRUE
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD hst4_pizzacop	 //9
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_pizzacop 120.0 120.0 120.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_pizzacop FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_pizzacop TRUE
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD hst4_pizzadrunk	   //10
	
	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hst4_pizzadrunk 120.0 120.0 120.0 FALSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_pizzadrunk FALSE
	ELSE
		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION hst4_pizzadrunk TRUE
	ENDIF

ENDIF

//IF NOT IS_CHAR_DEAD truck_driver	   //11	
//	IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer truck_driver 70.0 70.0 70.0 FALSE
//		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION truck_driver FALSE
//	ELSE
//		FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION truck_driver TRUE
//	ENDIF
//
//ENDIF





GOTO mission_hst4_loop

hst4_onwhichbike_check:
	
	//WRITE_DEBUG_WITH_INT playeronbike m
	IF m = 0
		IF hst4_pizzasetpiece= 0

			IF NOT IS_CHAR_DEAD hst4_pizzacop
	  		CLEAR_CHAR_TASKS hst4_pizzacop
			OPEN_SEQUENCE_TASK hst4_pizzacopseq
				TASK_GO_STRAIGHT_TO_COORD -1 2785.7888 1439.4536 9.5579 PEDMOVE_RUN 10000
				//DROP_OBJECT hst4_pizzacop TRUE
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
				
			CLOSE_SEQUENCE_TASK hst4_pizzacopseq
			PERFORM_SEQUENCE_TASK hst4_pizzacop hst4_pizzacopseq
			CLEAR_SEQUENCE_TASK hst4_pizzacopseq
			ENDIF

			IF NOT IS_CHAR_DEAD hst4_pizzadrunk
			CLEAR_CHAR_TASKS hst4_pizzadrunk
			MARK_CHAR_AS_NO_LONGER_NEEDED hst4_pizzadrunk
			TASK_SMART_FLEE_CHAR hst4_pizzadrunk scplayer 100.0 -1
			ENDIF



			hst4_pizzasetpiece = 1
			//PRINT HEI4_20 6000 1
			
			ALTER_WANTED_LEVEL_NO_DROP player1 1 // Pizza eating cop has just seen player nick his bike
			
			

		ENDIF	
	ENDIF

	IF m = 1
		IF hst4_hotelsetpiece= 0

	   		IF NOT IS_CHAR_DEAD hst4_hotelcop
			CLEAR_CHAR_TASKS hst4_hotelcop
			OPEN_SEQUENCE_TASK hst4_hotelcopseq
				TASK_GO_STRAIGHT_TO_COORD -1 2646.5820 2348.1704 9.6797 PEDMOVE_RUN 10000
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
				
			CLOSE_SEQUENCE_TASK hst4_hotelcopseq
			PERFORM_SEQUENCE_TASK hst4_hotelcop hst4_hotelcopseq
			CLEAR_SEQUENCE_TASK hst4_hotelcopseq
		   //	PRINT HEI4_22 6000 1
			ENDIF

			hst4_hotelsetpiece = 1
			//PRINT HEI4_20 6000 1
			ALTER_WANTED_LEVEL_NO_DROP player1 1 // Cop has just seen player nick bike from the front of the hotel
			MARK_CAR_AS_NO_LONGER_NEEDED hst4_hotelbike1
			MARK_CAR_AS_NO_LONGER_NEEDED hst4_hotelbike2
			MARK_CAR_AS_NO_LONGER_NEEDED hst4_hotelbike3

		ENDIF	
	ENDIF

	IF m = 2
		IF hst4_stationsetpiece= 0

	   		IF NOT IS_CHAR_DEAD hst4_staircop
				CLEAR_CHAR_TASKS hst4_staircop
				OPEN_SEQUENCE_TASK hst4_staircopseq
					//TASK_GO_STRAIGHT_TO_COORD -1 2322.5352 2450.7109 9.8203 PEDMOVE_RUN 10000
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
					
				CLOSE_SEQUENCE_TASK hst4_staircopseq
				PERFORM_SEQUENCE_TASK hst4_staircop hst4_staircopseq
				CLEAR_SEQUENCE_TASK hst4_staircopseq
			ENDIF

			IF NOT IS_CHAR_DEAD hst4_larrycop
				CLEAR_CHAR_TASKS hst4_larrycop
				OPEN_SEQUENCE_TASK hst4_larrycopseq
					TASK_GO_STRAIGHT_TO_COORD -1 2311.0042 2436.7639 9.8203 PEDMOVE_RUN 10000
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
					
				CLOSE_SEQUENCE_TASK hst4_larrycopseq
				PERFORM_SEQUENCE_TASK hst4_larrycop hst4_larrycopseq
				CLEAR_SEQUENCE_TASK hst4_larrycopseq
				
			   	//PRINT HEI4_21 6000 1

			ENDIF


			hst4_stationsetpiece = 1
			
		   	//IF NOT IS_CAR_DEAD hst4_stationcar
			//IF IS_CHAR_IN_CAR scplayer hst4_stationcar
			//ALTER_WANTED_LEVEL_NO_DROP player1 1 // Cops have seen player nick bike from the front of the police station
			//ELSE
			ALTER_WANTED_LEVEL_NO_DROP player1 1
			//ENDIF
			//ENDIF
		ENDIF	
	ENDIF

	
	IF m = 3
		IF hst4_hospitalsetpiece= 0

	   		IF NOT IS_CHAR_DEAD hst4_hospitalcop
				CLEAR_CHAR_TASKS hst4_hospitalcop
				OPEN_SEQUENCE_TASK hst4_hospitalcopseq
					TASK_GO_STRAIGHT_TO_COORD -1 1593.2894 1836.8081 9.8281 PEDMOVE_RUN 10000
					TASK_KILL_CHAR_ON_FOOT -1 scplayer
					
				CLOSE_SEQUENCE_TASK hst4_hospitalcopseq
				PERFORM_SEQUENCE_TASK hst4_hospitalcop hst4_hospitalcopseq
				CLEAR_SEQUENCE_TASK hst4_hospitalcopseq
				//PRINT HEI4_22 6000 1

			ENDIF

	 

			hst4_hospitalsetpiece = 1
		   //	PRINT HEI4_22 6000 1
			ALTER_WANTED_LEVEL_NO_DROP player1 1 // Cops have seen player nick bike from the front of the hospital

		ENDIF	
	ENDIF



RETURN


hst4_staircheck: // called if the player gets cocky with the cops outside the station

	IF NOT IS_CHAR_DEAD hst4_staircop
		CLEAR_CHAR_TASKS hst4_staircop
		TASK_KILL_CHAR_ON_FOOT hst4_staircop scplayer
	ENDIF

	IF NOT IS_CHAR_DEAD hst4_larrycop
		CLEAR_CHAR_TASKS hst4_larrycop
		TASK_KILL_CHAR_ON_FOOT hst4_larrycop scplayer
	ENDIF
		/*
		IF NOT IS_CAR_DEAD hst4_stationcar
			IF IS_CHAR_IN_CAR scplayer hst4_stationcar
			ALTER_WANTED_LEVEL_NO_DROP player1 1 // Cops have seen player nick bike from the front of the police station
			ELSE
			ALTER_WANTED_LEVEL_NO_DROP player1 1
			ENDIF
		ENDIF
		*/

	ALTER_WANTED_LEVEL_NO_DROP player1 1


	hst4_stationsetpiece = 1 //set so that we don't call sequences for the player getting on the bike afterwards.
	
RETURN


hst4_choppertakeoffsp:

	IF hst4_choppertakeoff = 0
		IF NOT IS_CHAR_DEAD hst4_hospitalpilot
			IF NOT IS_CAR_DEAD hst4_hospitalchopper
				
				SET_CAR_CRUISE_SPEED hst4_hospitalchopper 100.0
				CLOSE_ALL_CAR_DOORS hst4_hospitalchopper
				HELI_GOTO_COORDS hst4_hospitalchopper -2303.6272 2105.2903 200.0 200.0 200.0

//				IF NOT IS_CAR_DEAD motorbike[3]
//					IF NOT IS_CHAR_DEAD hst4_hospitalcop
//				    CLEAR_CHAR_TASKS hst4_hospitalcop
//					OPEN_SEQUENCE_TASK hst4_hospitalcop2seq
//				  	TASK_ENTER_CAR_AS_DRIVER -1 motorbike[3] 6000					
//				  	TASK_CAR_DRIVE_TO_COORD -1 motorbike[3] 1570.7115 1834.1353 9.6969 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
//					//TASK_CAR_DRIVE_TO_COORD -1 motorbike[3] 1570.7115 1834.1353 9.6969 15.0 MODE_NORMAL FALSE DRIVINGMODE_SLOWDOWNFORCARS				  
//					TASK_CAR_DRIVE_WANDER -1 motorbike[3] 15.0 DRIVINGMODE_AVOIDCARS	
//				  						
//					CLOSE_SEQUENCE_TASK hst4_hospitalcop2seq
//					PERFORM_SEQUENCE_TASK hst4_hospitalcop hst4_hospitalcop2seq
//					CLEAR_SEQUENCE_TASK hst4_hospitalcop2seq
//				   	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE hst4_hospitalcop KNOCKOFFBIKE_EASY
//				  //	write_debug pish
//
//					ENDIF
//				ENDIF
			ENDIF
		ENDIF

	   IF NOT IS_CAR_DEAD motorbike[3]
			IF NOT IS_CHAR_DEAD hst4_hospitalcop
		    CLEAR_CHAR_TASKS hst4_hospitalcop
			OPEN_SEQUENCE_TASK hst4_hospitalcop2seq
		  	TASK_ENTER_CAR_AS_DRIVER -1 motorbike[3] 6000					
		  	TASK_CAR_DRIVE_TO_COORD -1 motorbike[3] 1570.7115 1834.1353 9.6969 15.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
			//TASK_CAR_DRIVE_TO_COORD -1 motorbike[3] 1570.7115 1834.1353 9.6969 15.0 MODE_NORMAL FALSE DRIVINGMODE_SLOWDOWNFORCARS				  
			TASK_CAR_DRIVE_WANDER -1 motorbike[3] 15.0 DRIVINGMODE_AVOIDCARS	
		  						
			CLOSE_SEQUENCE_TASK hst4_hospitalcop2seq
			PERFORM_SEQUENCE_TASK hst4_hospitalcop hst4_hospitalcop2seq
			CLEAR_SEQUENCE_TASK hst4_hospitalcop2seq
		   	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE hst4_hospitalcop KNOCKOFFBIKE_EASY
		  //	write_debug pish

			ENDIF
		ENDIF




	   hst4_choppertakeoff = 1
	ENDIF


RETURN



hst4_pizzadrunkbustsp:

	IF hst4_pizzadrunkbust = 0
		IF NOT IS_CHAR_DEAD hst4_pizzacop
				IF NOT IS_CHAR_DEAD hst4_pizzadrunk
				   CLEAR_CHAR_TASKS hst4_pizzadrunk
					OPEN_SEQUENCE_TASK hst4_pizzadrunkseq
						SET_SEQUENCE_TO_REPEAT hst4_pizzadrunkseq 1

				  	TASK_GO_STRAIGHT_TO_COORD -1 2762.1167 1432.3990 9.5325 PEDMOVE_WALK 30000
					TASK_FALL_AND_GET_UP -1 0 3000
					TASK_GO_STRAIGHT_TO_COORD -1 2767.5371 1424.8768 9.2195 PEDMOVE_WALK 30000
				  	CLOSE_SEQUENCE_TASK hst4_pizzadrunkseq
					PERFORM_SEQUENCE_TASK hst4_pizzadrunk hst4_pizzadrunkseq
					
//					CLEAR_SEQUENCE_TASK hst4_hospitalcop2seq
				ENDIF
//			ENDIF
		ENDIF
	   hst4_pizzadrunkbust = 1
	ENDIF


RETURN


hst4_hotelthugsleavesp:


	IF hst4_hotelthugsleave = 0


				IF sfx_tyres = 2
					PLAY_MISSION_AUDIO 3 // tyre screech
					//WRITE_DEBUG AUD
				ENDIF

				IF NOT IS_CAR_DEAD hst4_hotelbike1
					IF NOT IS_CHAR_DEAD hst4_hotelthug1
						CLEAR_CHAR_TASKS hst4_hotelthug1
						OPEN_SEQUENCE_TASK hst4_hotelthug1seq
						  TASK_CAR_DRIVE_TO_COORD -1 hst4_hotelbike1 2648.7437 2293.4197 9.6875 25.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						  TASK_CAR_DRIVE_WANDER -1 hst4_hotelbike1 25.0 DRIVINGMODE_AVOIDCARS
						 CLOSE_SEQUENCE_TASK hst4_hotelthug1seq
						 PERFORM_SEQUENCE_TASK hst4_hotelthug1 hst4_hotelthug1seq
						 CLEAR_SEQUENCE_TASK hst4_hotelthug1seq
					ENDIF
				ENDIF
				IF NOT IS_CAR_DEAD hst4_hotelbike2
					IF NOT IS_CHAR_DEAD hst4_hotelthug2
						CLEAR_CHAR_TASKS hst4_hotelthug2
						OPEN_SEQUENCE_TASK hst4_hotelthug2seq
						  TASK_CAR_DRIVE_TO_COORD -1 hst4_hotelbike2 2650.5659 2290.3152 9.6875 26.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						  TASK_CAR_DRIVE_WANDER -1 hst4_hotelbike2 25.0 DRIVINGMODE_AVOIDCARS
						 CLOSE_SEQUENCE_TASK hst4_hotelthug2seq
						 PERFORM_SEQUENCE_TASK hst4_hotelthug2 hst4_hotelthug2seq
						 CLEAR_SEQUENCE_TASK hst4_hotelthug2seq
					ENDIF
				ENDIF
				 IF NOT IS_CAR_DEAD hst4_hotelbike3
					IF NOT IS_CHAR_DEAD hst4_hotelthug3
						CLEAR_CHAR_TASKS hst4_hotelthug3
						OPEN_SEQUENCE_TASK hst4_hotelthug3seq
						  TASK_CAR_DRIVE_TO_COORD -1 hst4_hotelbike3 2651.6809 2294.8696 9.6875 25.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
						  TASK_CAR_DRIVE_WANDER -1 hst4_hotelbike3 25.0 DRIVINGMODE_AVOIDCARS
						 CLOSE_SEQUENCE_TASK hst4_hotelthug3seq
						 PERFORM_SEQUENCE_TASK hst4_hotelthug3 hst4_hotelthug3seq
						 CLEAR_SEQUENCE_TASK hst4_hotelthug3seq
					ENDIF
				ENDIF


				IF NOT IS_CAR_DEAD motorbike[1]
					IF NOT IS_CHAR_DEAD hst4_hotelcop
				    CLEAR_CHAR_TASKS hst4_hotelcop
					OPEN_SEQUENCE_TASK hst4_hotelcopseq
				  	TASK_ENTER_CAR_AS_DRIVER -1 motorbike[1] 10000					
				  	TASK_CAR_DRIVE_TO_COORD -1 motorbike[1] 2647.9409 2293.2219 9.6875 21.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS

				  	TASK_CAR_DRIVE_WANDER -1 motorbike[1] 17.0 DRIVINGMODE_AVOIDCARS
			   
				   
				  						
					CLOSE_SEQUENCE_TASK hst4_hotelcopseq
					PERFORM_SEQUENCE_TASK hst4_hotelcop hst4_hotelcopseq
					CLEAR_SEQUENCE_TASK hst4_hotelcopseq
				   	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE hst4_hotelcop KNOCKOFFBIKE_EASY

					ENDIF			   
					
				   					
					SWITCH_CAR_SIREN motorbike[1] ON
					CLEAR_MISSION_AUDIO 3
	
			   	ENDIF



		hst4_hotelthugsleave = 1
	ENDIF



RETURN


	
// ************************************ MISSION FAILED *************************************
mission_hst4_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   

// ************************************ MISSION PASSED *************************************
mission_hst4_passed:
//INCREMENT_FLOAT_STAT 64 50.0


flag_heist_mission_counter ++
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 20 5000 1
AWARD_PLAYER_MISSION_RESPECT 20

CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
REGISTER_MISSION_PASSED ( HEIST_4 )
PLAYER_MADE_PROGRESS 1
//START_NEW_SCRIPT hst4_mission_loop
RETURN
		

// *********************************** MISSION CLEANUP *************************************
mission_hst4_cleanup:

SET_PLAYER_CONTROL player1 ON

SET_PLAYER_GROUP_RECRUITMENT player1 TRUE

SET_GROUP_FOLLOW_STATUS hst4_group TRUE

SET_PLAYER_GROUP_TO_FOLLOW_NEVER player1 FALSE

//FREEZE_CHAR_POSITION scplayer FALSE
 SET_CAR_DENSITY_MULTIPLIER 1.0

CLEAR_MISSION_AUDIO 3

RELEASE_WEATHER


SWITCH_COPS_ON_BIKES ON

REMOVE_BLIP	mission_blip
REMOVE_BLIP motorbike_blip[0]
REMOVE_BLIP motorbike_blip[1]
REMOVE_BLIP motorbike_blip[2]
REMOVE_BLIP motorbike_blip[3]
//CLEAR_ONSCREEN_COUNTER
//CLEAR_ONSCREEN_TIMER
//KILL_FX_SYSTEM hst4_puke
MARK_MODEL_AS_NO_LONGER_NEEDED PACKER
MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED LAPDM1
MARK_MODEL_AS_NO_LONGER_NEEDED COPCARVG
MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
MARK_MODEL_AS_NO_LONGER_NEEDED FREEWAY
MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC2
MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1
MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH

MARK_MODEL_AS_NO_LONGER_NEEDED triada

MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC1

REMOVE_ANIMATION SMOKING
REMOVE_ANIMATION MISC

CLEAR_ONSCREEN_TIMER hst4_deliverytimer

GET_GAME_TIMER timer_mobile_start

 

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN
		
}


/*
[HEI4_01]
~s~We need to steal four ~r~police bikes~s~ and get them safely onto the back of a Packer truck.

[HEI4_02]
~s~Okay, drive the bike near the back of the moving ~y~Packer~s~ truck. We need to get it onboard.

[HEI4_03]
~s~Hey, we need ~r~police bikes~s~ for the heist! Grab one!

[HEI4_04]
~r~We needed all those bikes in good working order! Is someone going to carry his now you've wrecked one? 

[HEI4_05]
~r~The Packer is destroyed! How are we going to deliver the bikes now?

[HEI4_06]
~s~Good! The first bike's attached. Jump off and get another ~r~police bike~s~ for the job.

[HEI4_07]
~s~Excellent! That's all the bike's safely delivered! Preparations for the heist are going well!

[HEI4_08]
~s~Another one safe! Great, we're halfway there. More ~r~police bikes~s~ needed.

[HEI4_09]
~s~Number 3 present and correct. Jump off and get that final ~r~police bike~s~ delivered.

[HEI4_10]
~r~ Out of time! The Packer truck needs juice!

[HEI4_11]
~s~The Packer's circling the outskirts of town but you'll have to be quick. It doesn't run on fresh air!

[HEI4_12]
time left:

[HEI4_13]
~s~There's the ~y~Packer~s~ up ahead. Drive up the ramp at a steady speed so it can be attached.

[HEI4_14]
~s~Some of the bikes are in conspicuous locations. Look lively if trouble starts!

[HEI4_20]
~w~Cop: Come in control, I've just been in to pick up a Four Seasons and some punk has helped himself to my bike!

[HEI4_21]
~w~Cop: Larry! Some big-balled prick is trying out your bike for size! Get him!

[HEI4_22]
~w~Cop: What the fu..? I'm at the hospital and some guy has a death wish! He's got my wheels!
*/