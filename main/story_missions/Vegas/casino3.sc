MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Casino 3 ******************************************
// ************************************ Fake Chips *****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME CASINO3

// Mission start stuff

GOSUB mission_start_casino3



IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_casino3_failed
ENDIF

GOSUB mission_cleanup_casino3

MISSION_END

{ 

// Variables for mission

LVAR_INT ca3_wantedlevel

LVAR_INT ca3_visible



//cutscene stuff
LVAR_INT ca3_ctskip_needed
//LVAR_INT ca3_cutscene_section


//Mission blips
LVAR_INT ca3_factoryB
//LVAR_INT ca3_plvanB

//LVAR_INT ca3_gate1B ca3_gate2B


LVAR_INT ca3_bossB
LVAR_INT ca3_exitB

LVAR_INT ca3_machineblip[11]

LVAR_INT temp_fx


LVAR_INT ca3_temp_int


//Condition flags
LVAR_INT ca3_gotplvan

LVAR_INT ca3_blip1swap

LVAR_INT ca3_BlowUpMachines

LVAR_INT ca3_gatereopened






//Vehicles
LVAR_INT ca3_parked_car
LVAR_INT ca3_mafia_car
LVAR_INT ca3_forklift1
LVAR_INT ca3_forklift2
LVAR_INT ca3_forklift3
LVAR_INT ca3_forklift4
LVAR_INT ca3_truck
LVAR_INT ca3_pony

LVAR_INT ca3_admiral1
LVAR_INT ca3_admiral2



//Peds

LVAR_INT ca3_wuzi
LVAR_INT ca3_wuzidriver
LVAR_INT ca3_wuzicar

LVAR_INT ca3_plvanD
LVAR_INT ca3_gate1P
LVAR_INT ca3_gate2P

LVAR_INT ca3_goondm //decision maker

LVAR_INT ca3_forkie1P
LVAR_INT ca3_forkie2P
LVAR_INT ca3_forkie3
LVAR_INT ca3_forkie4
LVAR_INT ca3_forkie1seq
LVAR_INT ca3_forkie2seq

LVAR_INT ca3_bossP
LVAR_INT ca3_goon1P
LVAR_INT ca3_goon2P
LVAR_INT ca3_goon3P
LVAR_INT ca3_goon4P
LVAR_INT ca3_goon5P
LVAR_INT ca3_goon6P
LVAR_INT ca3_contextgoon
LVAR_INT ca3_spottedgoon

LVAR_INT ca3_foreman1
LVAR_INT ca3_foremangroup
LVAR_INT ca3_CheckPlayerForeman
LVAR_INT ca3_foremanseq

LVAR_INT ca3_bossrammy
LVAR_INT ca3_bossinit






//Objects
LVAR_INT ca3_gateO
LVAR_INT ca3_gateswitchO

LVAR_INT ca3_portakabin

LVAR_INT ca3_cutbox

LVAR_INT ca3_fire1
LVAR_INT ca3_fire2
LVAR_INT ca3_fire3

LVAR_INT ca3_fire4
LVAR_INT ca3_fire5
LVAR_INT ca3_fire6


LVAR_INT ca3_exting

LVAR_INT ca3_rcontainer
LVAR_INT ca3_rcontainer2

LVAR_INT ca3_block1
LVAR_INT ca3_block2
LVAR_INT ca3_block3



LVAR_INT ca3_machine1
LVAR_INT ca3_machine2
LVAR_INT ca3_machine3

LVAR_INT ca3_machine4
LVAR_INT ca3_machine5
LVAR_INT ca3_machine6

LVAR_INT ca3_MachinesDestroyed

LVAR_INT ca3_increment1
LVAR_INT ca3_increment2
LVAR_INT ca3_increment3

LVAR_INT ca3_increment4
LVAR_INT ca3_increment5
LVAR_INT ca3_increment6

LVAR_INT ca3_increment7

LVAR_FLOAT ca3_machineX
LVAR_FLOAT ca3_machineY
LVAR_FLOAT ca3_machineZ





//Misc
LVAR_INT ca3_g2seq
LVAR_INT ca3_g1seq
LVAR_INT ca3_g3seq
LVAR_INT ca3_bossseq
LVAR_INT ca3_bidleseq
LVAR_INT ca3_gate1seq
LVAR_INT ca3_gate2seq
LVAR_INT ca3_cgseq
LVAR_INT ca3_sgseq	

LVAR_INT ca3_gate1cutseq
LVAR_INT ca3_gate2cutseq


LVAR_FLOAT ca3_r1z
LVAR_FLOAT ca3_r2z  

/*
LVAR_INT ca3_bomb1placed
LVAR_INT ca3_bomb2placed
LVAR_INT ca3_bomb3placed

VAR_INT ca3_detonation
VAR_INT ca3_suspicion  */




LVAR_INT ca3_CheckMachineStatus


LVAR_INT ca3_test
LVAR_INT ca3_driverflee

LVAR_INT ca3_setsequences

LVAR_INT ca3_forkout1
LVAR_INT ca3_forkout2

LVAR_INT ca3_testcam

LVAR_INT ca3_goon1proxim

LVAR_INT ca3_BeenSpotted

LVAR_INT ca3_Cover_Blown_Cut

LVAR_INT ca3_forktog
LVAR_INT ca3_cutfork

LVAR_INT ca3_contextcut
LVAR_INT ca3_contextcut2
LVAR_INT ca3_contextskip
LVAR_INT ca3_contextskip2
LVAR_INT ca3_finalskip

  /*
LVAR_FLOAT ca3_x
LVAR_FLOAT ca3_y
LVAR_FLOAT ca3_z
*/

LVAR_INT ca3_keypadtest
LVAR_INT ca3_animplaying



//VAR_INT ca3_foremanhealth
LVAR_INT ca3_machine1damage
LVAR_INT ca3_machine2damage
LVAR_INT ca3_machine3damage
LVAR_INT ca3_machine4damage
LVAR_INT ca3_machine5damage
LVAR_INT ca3_machine6damage



//new door
//LVAR_INT fourdragons_door	// in initial.sc

//new sequences
LVAR_INT ca3_playerseq
LVAR_INT ca3_forkie3leaveseq

LVAR_INT ca3_highseq


LVAR_INT ca3_cutfix
LVAR_INT shoot_fix

LVAR_INT ca3_skip



//backup guys
LVAR_INT ca3_greaser1 ca3_greaser2 ca3_greaser3
LVAR_INT ca3_greaser1seq ca3_greaser2seq ca3_greaser3seq

LVAR_INT ca3_backupdriver ca3_backupdriveby ca3_backup1 ca3_backup2

LVAR_INT ca3_backupdriverseq ca3_backup1seq ca3_backup2seq

LVAR_INT ca3_backupcar

LVAR_INT ca3_begingrease
LVAR_INT ca3_beginbackup





//Dialogue and audio variables

LVAR_INT ca3_dialogue ca3_audio_char
LVAR_INT ca3_text_timer_diff ca3_text_timer_end ca3_text_timer_start
LVAR_TEXT_LABEL ca3_text[15]
LVAR_INT ca3_audio[15] ca3_audio_slot ca3_alt_slot ca3_counter ca3_ahead_counter ca3_audio_playing ca3_audio_underway
LVAR_INT ca3_convo_underway ca3_convo_counter ca3_random //ca3_leftcar_counter ca3_backcar_counter ca3_ganghire_counter 
//LVAR_INT ca3_driveby_counter ca3_cut_counter



LVAR_INT ca3_splitfix





 
// **************************************** Mission Start **********************************

mission_start_casino3:

REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT CASINO3


flag_player_on_mission = 1



WAIT 0

// ****************************************START OF CUTSCENE********************************


 

SET_AREA_VISIBLE 11


SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
SET_CHAR_COORDINATES scplayer 2027.3912 1008.0874 9.8203 //2024.8704 1008.6201 9.8127

SET_CHAR_HEADING scplayer 297.1696 //342.8127


LOAD_CUTSCENE CAS_3
 
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



SET_AREA_VISIBLE 0

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY ca3_goondm


//SET_PLAYER_CONTROL player1 ON
 

// ****************************************END OF CUTSCENE**********************************
WAIT 0
	
	

SET_FADING_COLOUR 0 0 0

//WAIT 500


//DO_FADE 500 FADE_IN

// request models

//LOAD_SPECIAL_CHARACTER 1 WUZIMU


REQUEST_MODEL BRAVURA
REQUEST_MODEL ADMIRAL

REQUEST_MODEL wmymech
REQUEST_MODEL wmymech

REQUEST_MODEL BAT
REQUEST_MODEL COLT45
REQUEST_MODEL shotgspa
REQUEST_MODEL MICRO_UZI

REQUEST_MODEL VMAFF1
REQUEST_MODEL VMAFF2
REQUEST_MODEL VMAFF3
REQUEST_MODEL VMAFF4

REQUEST_MODEL FORKLIFT



LOAD_ALL_MODELS_NOW

//REQUEST_MODEL LINERUN
//REQUEST_MODEL PETROTR


WHILE NOT HAS_MODEL_LOADED COLT45
	OR NOT HAS_MODEL_LOADED BRAVURA
 	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED wmymech
	OR NOT HAS_MODEL_LOADED VMAFF1
	OR NOT HAS_MODEL_LOADED VMAFF2																 
   	OR NOT HAS_MODEL_LOADED shotgspa
	OR NOT HAS_MODEL_LOADED MICRO_UZI
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED FORKLIFT
	OR NOT HAS_MODEL_LOADED VMAFF4
	OR NOT HAS_MODEL_LOADED VMAFF4
  	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED wmymech
	OR NOT HAS_MODEL_LOADED BAT  
	OR NOT HAS_MODEL_LOADED ADMIRAL
	WAIT 0
ENDWHILE


LOAD_SCENE 2031.1681 1030.9177 9.8130


									   
//WAIT 500

//RESTORE_CAMERA_JUMPCUT
//SET_PLAYER_CONTROL player1 ON

// ************************************ Declare Variables *********************************


temp_fx = 99



ca3_begingrease = 0
ca3_beginbackup = 0


ca3_ctskip_needed = 0

ca3_gotplvan = 5

ca3_blip1swap = 0
ca3_BlowUpMachines = 0//to test end use 8
ca3_MachinesDestroyed = 0
ca3_test = 0
ca3_driverflee = 0
ca3_gatereopened = 0
ca3_setsequences = 0
ca3_forkout1 = 0
ca3_forkout2 = 0
ca3_testcam = 1
ca3_CheckMachineStatus = 0
ca3_CheckPlayerForeman = 0
ca3_bossrammy = 0
ca3_bossinit = 0
ca3_increment1 = 0
ca3_BeenSpotted = 0
ca3_forktog = 0
ca3_cutfork = 0
ca3_contextcut = 0
ca3_contextskip = 0
ca3_contextcut2 = 0
ca3_contextskip2 = 0
ca3_ctskip_needed = 0
ca3_finalskip = 0

ca3_increment5 = 0
ca3_increment7 = 0

ca3_cutfix = 0
shoot_fix = 0

ca3_splitfix = 0


ca3_skip = 1 // want to setup skip

// ---- Dialogue Flags
	ca3_audio_slot = 1
	ca3_alt_slot = 2
	ca3_counter = 0
	ca3_ahead_counter = 1
	ca3_audio_playing = 0
	ca3_audio_underway = 0



//Dialogue text
$ca3_text[1] = CAS3_AA//Hey, who's this character? Looks like a cop to me!
$ca3_text[2] = CAS3_AB//We better go warn the boss.
$ca3_text[3] = CAS3_AC//Hey, watch the car, I just got this baby resprayed!
$ca3_text[4] = CAS3_AD//What? He must be a fed! Let's give him a proper Sindacco welcome!
$ca3_text[5] = CAS3_BA//What's going on?
$ca3_text[6] = CAS3_BB//Someone's got a fucking deathwish if they're starting trouble 'round here!
$ca3_text[7] = CAS3_BC//Get the boys ready! Production line's gotta keep rolling!
$ca3_text[8] = CAS3_BD//Hey, who's this prick?
$ca3_text[9] = CAS3_BE//Somebody take care of this joker!
$ca3_text[10] = CAS3_CA// Hey Carl, How'd it go?
$ca3_text[11] = CAS3_CB// Tell Woozie, we'll be getting no more trouble
$ca3_text[12] = CAS3_CC// with counterfeit chips.

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//$ca3_text[10] = CAS3_?? // Will be cutscene end stuff back at the Four Dragons casino


//Dialogue audio
$ca3_audio[1] = SOUND_CAS3_AA//Hey, who's this character? Looks like a cop to me!
$ca3_audio[2] = SOUND_CAS3_AB//We better go warn the boss.
$ca3_audio[3] = SOUND_CAS3_AC//Hey, watch the car, I just got this baby resprayed!
$ca3_audio[4] = SOUND_CAS3_AD//What? He must be a fed! Let's give him a proper Sindacco welcome!
$ca3_audio[5] = SOUND_CAS3_BA//What's going on?
$ca3_audio[6] = SOUND_CAS3_BB//Someone's got a fucking deathwish if they're starting trouble 'round here!
$ca3_audio[7] = SOUND_CAS3_BC//Get the boys ready! Production line's gotta keep rolling!
$ca3_audio[8] = SOUND_CAS3_BD//Hey, who's this prick?
$ca3_audio[9] = SOUND_CAS3_BE//Somebody take care of this joker!
$ca3_audio[10] = SOUND_CAS3_CA// Hey Carl, How'd it go?
$ca3_audio[11] = SOUND_CAS3_CB// Tell Woozie, we'll be getting no more trouble with counterfeit chips.
$ca3_audio[12] = SOUND_CAS3_CC// with counterfeit chips.

//$ca3_text[10] = CAS3_?? // Will be cutscene end stuff back at the Four Dragons casino


GET_MAX_WANTED_LEVEL ca3_wantedlevel



// ****************************************************************************************



//Gate to be manipulated
//CREATE_OBJECT_NO_OFFSET shutter_vegas 1055.629 2087.67 12.469 ca3_gateO


 //Door for casino3.sc	in initial.sc
//VAR_INT ca3_loadingbay
//CREATE_OBJECT_NO_OFFSET shutter_vegas 1055.629 2087.67 12.469 ca3_loadingbay
//DONT_REMOVE_OBJECT ca3_loadingbay



IF ca3_blowupmachines = -13
	
	CREATE_CAR ADMIRAL 1029.8561 2142.8579 9.8130 ca3_backupdriveby


ENDIF

			 
//LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY ca3_goondm
			 
// SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1




//CREATE_OBJECT_NO_OFFSET trdcsgrgdoor_lvs 1903.383 967.82 11.438 fourdragons_door // 11.438Safe room door
SET_OBJECT_COLLISION fourdragons_door TRUE
//SET_OBJECT_HEADING fourdragons_door 0.0


			 



 
CREATE_CAR BRAVURA 2014.7196 1035.2380 9.8127 ca3_parked_car
SET_CAR_HEADING ca3_parked_car 1.0755 //180
CHANGE_CAR_COLOUR ca3_parked_car 10 2
   



CREATE_CAR ADMIRAL 1044.2715 2096.7136 9.8130 ca3_mafia_car
SET_CAR_PROOFS ca3_mafia_car FALSE FALSE TRUE FALSE FALSE  // explosion proof

//CREATE_CAR ADMIRAL 1029.6315 2129.3684 9.8130 ca3_mafia_car


SET_CAR_HEADING ca3_mafia_car 27.1148	
CHANGE_CAR_COLOUR ca3_mafia_car 2 1
//OPEN_CAR_DOOR ca3_mafia_car FRONT_LEFT_DOOR

//CUSTOM_PLATE_FOR_NEXT_CAR ADMIRAL G1RUYHUN  


//CREATE_CAR ADMIRAL 1029.8561 2142.8579 9.8130 ca3_backupdriveby
//SET_CAR_HEADING ca3_backupdriveby 205.1437 
//CHANGE_CAR_COLOUR ca3_backupdriveby 123 10
//SET_CAR_PROOFS ca3_backupdriveby TRUE TRUE TRUE TRUE TRUE
//LOCK_CAR_DOORS ca3_backupdriveby CARLOCK_LOCKOUT_PLAYER_ONLY	  // was mission2 pedtype
//FREEZE_CAR_POSITION ca3_backupdriveby TRUE
//SET_CAN_BURST_CAR_TYRES ca3_backupdriveby FALSE



//CREATE_CAR FORKLIFT 1083.7725 2113.3379 9.8203  ca3_forklift1
//SET_CAR_HEADING ca3_forklift1 138.0755

CREATE_CAR FORKLIFT 1063.8870 2134.7241 9.8203 ca3_forklift2
SET_CAR_HEADING ca3_forklift2 252.0755

CREATE_CAR FORKLIFT 1060.1465 2083.9863 9.8203 ca3_forklift3
SET_CAR_HEADING ca3_forklift3 233.609//53.2609 

CREATE_CHAR_INSIDE_CAR ca3_forklift3 PEDTYPE_CIVMALE wmymech ca3_forkie3
GIVE_WEAPON_TO_CHAR ca3_forkie3 WEAPONTYPE_MICRO_UZI 2000
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE ca3_forkie3 TRUE





MARK_MODEL_AS_NO_LONGER_NEEDED BRAVURA
MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT





CREATE_CHAR_INSIDE_CAR ca3_mafia_car PEDTYPE_MISSION1 VMAFF1 ca3_gate1P		  // long hair
//CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 1029.5271 2124.9849 9.820 ca3_gate1P
GIVE_WEAPON_TO_CHAR ca3_gate1P WEAPONTYPE_PISTOL 2000
SET_CHAR_HEADING ca3_gate1P 56.7240
SET_CHAR_DECISION_MAKER ca3_gate1P ca3_goondm
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE ca3_gate1P FALSE

CREATE_CHAR_AS_PASSENGER ca3_mafia_car PEDTYPE_MISSION1 VMAFF2 0 ca3_gate2P 	 // Paulie 
GIVE_WEAPON_TO_CHAR ca3_gate2P WEAPONTYPE_PISTOL 2000
SET_CHAR_HEADING ca3_gate2P 148.0260
SET_CHAR_DECISION_MAKER ca3_gate2P ca3_goondm
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE ca3_gate2P FALSE




CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 1064.2126 2140.6936 9.8203  ca3_goon1P
GIVE_WEAPON_TO_CHAR ca3_goon1P WEAPONTYPE_MICRO_UZI 2000 // brown suited guy
SET_CHAR_HEADING ca3_goon1P 156.0958
SET_CHAR_DECISION_MAKER ca3_goon1P ca3_goondm


CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 1093.0680 2107.3406 9.8203 ca3_goon2P
GIVE_WEAPON_TO_CHAR ca3_goon2P WEAPONTYPE_PISTOL 2000
SET_CHAR_HEADING ca3_goon2P 115.2591
SET_CHAR_DECISION_MAKER ca3_goon2P ca3_goondm


CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 1092.0822 2116.7922 14.3504  ca3_goon3P
GIVE_WEAPON_TO_CHAR ca3_goon3P WEAPONTYPE_PISTOL 2000	 //long hair guy on stairs
SET_CHAR_HEADING ca3_goon3P 115.4090
SET_CHAR_DECISION_MAKER ca3_goon3P ca3_goondm


CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 1083.6875 2121.5989 14.3504  ca3_bossP	   
GIVE_WEAPON_TO_CHAR ca3_bossP WEAPONTYPE_SPAS12_SHOTGUN 2000
SET_CHAR_DECISION_MAKER ca3_bossP ca3_goondm

ADD_ARMOUR_TO_CHAR ca3_bossP 50

SET_CHAR_HEADING ca3_bossP 115.6952



CREATE_CHAR PEDTYPE_MISSION1 wmymech 1056.6547 2108.5508 9.8203 ca3_forkie1P
//GIVE_WEAPON_TO_CHAR ca3_forkie1P WEAPONTYPE_MICRO_UZI 2000
SET_CHAR_HEADING ca3_forkie1P 186.9465
SET_CHAR_DECISION_MAKER ca3_forkie1P ca3_goondm
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE ca3_forkie1P TRUE



CREATE_CHAR PEDTYPE_MISSION1 wmymech 1092.6066 2116.9124 9.8203 ca3_forkie2P
//GIVE_WEAPON_TO_CHAR ca3_forkie2P WEAPONTYPE_UZI 2000
SET_CHAR_HEADING ca3_forkie2P 116.0465
SET_CHAR_DECISION_MAKER ca3_forkie2P ca3_goondm
SET_CHAR_CAN_BE_SHOT_IN_VEHICLE ca3_forkie2P TRUE


/*

CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 1086.8689 2078.6096 14.3504 ca3_foreman1
SET_CHAR_HEADING ca3_foreman1 86.4201 
GIVE_WEAPON_TO_CHAR ca3_foreman1 WEAPONTYPE_PISTOL 2000
SET_CHAR_DECISION_MAKER ca3_foreman1 ca3_goondm


CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 1086.4199 2077.2915 14.3504 ca3_contextgoon	//cutscene fuck up
SET_CHAR_HEADING ca3_contextgoon 74.4201 
GIVE_WEAPON_TO_CHAR ca3_contextgoon WEAPONTYPE_MICRO_UZI 2000
SET_CHAR_DECISION_MAKER ca3_contextgoon ca3_goondm
 
Moved to new area for bug fix.
*/

LVAR_INT ca3_fx[9]


LVAR_INT ca3_machine[9]
LVAR_INT ca3_machineinnards[9]


CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1074.9028 2139.6112 10.7203 ca3_machine[0]
SET_OBJECT_PROOFS ca3_machine[0] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET cj_chip_maker 1074.9028 2139.6112 10.7203 ca3_machineinnards[0]

SET_OBJECT_COLLISION ca3_machine[0] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[0] TRUE
SET_OBJECT_ROTATION ca3_machine[0] 0.0 0.0 180.0
SET_OBJECT_ROTATION ca3_machineinnards[0] 0.0 0.0 180.0



CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1069.4847 2139.6112 10.7203 ca3_machine[1]
SET_OBJECT_PROOFS ca3_machine[1] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET cj_chip_maker 1069.4847 2139.6112 10.7203 ca3_machineinnards[1]

SET_OBJECT_COLLISION ca3_machine[1] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[1] TRUE
SET_OBJECT_ROTATION ca3_machine[1] 0.0 0.0 180.0
SET_OBJECT_ROTATION ca3_machineinnards[1] 0.0 0.0 180.0



CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1062.4562 2139.6112 10.7203 ca3_machine[2]
SET_OBJECT_PROOFS ca3_machine[2] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET cj_chip_maker 1062.4562 2139.6112 10.7203 ca3_machineinnards[2]
SET_OBJECT_COLLISION ca3_machine[2] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[2] TRUE
SET_OBJECT_ROTATION ca3_machine[2] 0.0 0.0 180.0
SET_OBJECT_ROTATION ca3_machineinnards[2] 0.0 0.0 180.0








CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1081.2682 2123.6858 10.7203 ca3_machine[3]
SET_OBJECT_PROOFS ca3_machine[3] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET cj_chip_maker 1081.2682 2123.6858 10.7203 ca3_machineinnards[3]

SET_OBJECT_ROTATION ca3_machine[3] 0.0 0.0 90.0
SET_OBJECT_ROTATION ca3_machineinnards[3] 0.0 0.0 90.0

SET_OBJECT_COLLISION ca3_machine[3] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[6] TRUE


CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1081.2365 2130.6812 10.7203 ca3_machine[4]
SET_OBJECT_PROOFS ca3_machine[4] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET cj_chip_maker 1081.2365 2130.6812 10.7203 ca3_machineinnards[4]

SET_OBJECT_ROTATION ca3_machine[4] 0.0 0.0 90.0
SET_OBJECT_ROTATION ca3_machineinnards[4] 0.0 0.0 90.0

SET_OBJECT_COLLISION ca3_machine[4] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[7] TRUE










/*

													 //to here
CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1090.8550 2077.5319 10.7203 ca3_machine[3]
CREATE_OBJECT_NO_OFFSET cj_chip_maker 1090.8550 2077.5319 10.7203 ca3_machineinnards[3]

//SET_OBJECT_ROTATION ca3_machine[3] 0.0 0.0 90.0
SET_OBJECT_COLLISION ca3_machine[3] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[3] TRUE

Just gone

*/



CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1086.8550 2077.5319 10.7203 ca3_machine[5]
SET_OBJECT_PROOFS ca3_machine[5] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET cj_chip_maker 1086.8550 2077.5319 10.7203 ca3_machineinnards[5]

//SET_OBJECT_ROTATION ca3_machine[4] 0.0 0.0 90.0
SET_OBJECT_COLLISION ca3_machine[5] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[4] TRUE


CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1091.8185 2121.2566 10.7203 ca3_machine[6]
SET_OBJECT_PROOFS ca3_machine[6] TRUE TRUE TRUE TRUE TRUE

CREATE_OBJECT_NO_OFFSET cj_chip_maker 1091.8185 2121.2566 10.7203 ca3_machineinnards[6]

SET_OBJECT_ROTATION ca3_machine[6] 0.0 0.0 180.0
SET_OBJECT_ROTATION ca3_machineinnards[6] 0.0 0.0 180.0

SET_OBJECT_COLLISION ca3_machine[6] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[5] TRUE












/*
CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1094.1634 2081.3518 10.7203 ca3_machine[7]
CREATE_OBJECT_NO_OFFSET cj_chip_maker 1094.1634 2081.3518 10.7203 ca3_machineinnards[7]

SET_OBJECT_ROTATION ca3_machine[7] 0.0 0.0 90.0
SET_OBJECT_ROTATION ca3_machineinnards[7] 0.0 0.0 90.0 
SET_OBJECT_COLLISION ca3_machine[7] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[7] TRUE
 */





CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1094.1634 2102.3518 10.7203 ca3_machine[7]
SET_OBJECT_PROOFS ca3_machine[7] TRUE TRUE TRUE TRUE TRUE



CREATE_OBJECT_NO_OFFSET cj_chip_maker 1094.1634 2102.3518 10.7203 ca3_machineinnards[7]

SET_OBJECT_ROTATION ca3_machine[7] 0.0 0.0 90.0
SET_OBJECT_ROTATION ca3_machineinnards[7] 0.0 0.0 90.0

SET_OBJECT_COLLISION ca3_machine[7] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[8] TRUE

/*
												  
CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1094.1634 2096.8518 10.7203 ca3_machine[9]
CREATE_OBJECT_NO_OFFSET cj_chip_maker 1094.1634 2096.8518 10.7203 ca3_machineinnards[9]

SET_OBJECT_ROTATION ca3_machine[9] 0.0 0.0 90.0
SET_OBJECT_ROTATION ca3_machineinnards[9] 0.0 0.0 90.0

SET_OBJECT_COLLISION ca3_machine[9] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[9] TRUE

Just gone!
*/



CREATE_OBJECT_NO_OFFSET cj_chip_maker_bits 1094.1634 2092.3518 10.7203 ca3_machine[8]
SET_OBJECT_PROOFS ca3_machine[8] TRUE TRUE TRUE TRUE TRUE



CREATE_OBJECT_NO_OFFSET cj_chip_maker 1094.1634 2092.3518 10.7203 ca3_machineinnards[8]

SET_OBJECT_ROTATION ca3_machine[8] 0.0 0.0 90.0
SET_OBJECT_ROTATION ca3_machineinnards[8] 0.0 0.0 90.0

SET_OBJECT_COLLISION ca3_machine[8] TRUE 	  
//SET_OBJECT_DYNAMIC ca3_machine[10] TRUE



LVAR_INT ca3_machine_flag[9]
LVAR_INT c
c = 0
WHILE c < 9
	ca3_machine_flag[c] = 0
	SET_OBJECT_HEALTH ca3_machine[c] 1000
	++ c
ENDWHILE






CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_RED 1023.1812 2106.6035 14.7301 ca3_rcontainer
SET_OBJECT_ROTATION ca3_rcontainer 0.0 0.0 0.0

CREATE_OBJECT_NO_OFFSET KMB_CONTAINER_RED 1077.0142 2070.7434 14.7635 ca3_rcontainer2
SET_OBJECT_ROTATION ca3_rcontainer2 0.0 0.0 0.0

//CREATE_OBJECT_NO_OFFSET BLOCKPALLET 1021.8337 2115.3552 10.4203 ca3_cutbox	


ca3_r1z = 0.0
ca3_r2z	= 0.0



ADD_BLIP_FOR_COORD 1057.6359 2089.6936 9.8203 ca3_factoryB 
//PRINT_NOW CA3_1 10000 1
//timera = 0

//SET_CHAR_COORDINATES scplayer 2026.7286 1006.7860 9.8127




LOAD_SCENE 2031.1681 1030.9177 9.8130
REQUEST_COLLISION 2031.1681 1030.9177 //9.8130



DISPLAY_ZONE_NAMES FALSE									   

//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
//SET_CHAR_COORDINATES scplayer 2024.8704 1008.6201 9.8127
//SET_CHAR_HEADING scplayer 342.8127
SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT

WAIT 1000





DO_FADE 1000 FADE_IN
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE


PRINT_NOW CA3_1 10000 1

SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
DISPLAY_ZONE_NAMES TRUE

casino3_main_mission_loop:
WAIT 0

	 IF DOES_OBJECT_EXIST ca3_loadingbay
		SET_OBJECT_COORDINATES ca3_loadingbay 1055.629 2087.67 0.299
//	 	IF NOT SLIDE_OBJECT ca3_loadingbay 1055.629 2087.67 16.299 0.0 0.0 0.04 FALSE // was 16.299 for z
//				SLIDE_OBJECT ca3_loadingbay 1055.629 2087.67 16.299 0.0 0.0 0.04 FALSE
//	 	ENDIF
	 ENDIF




// ---- Load & Play Dialogue...
	IF NOT ca3_counter = 0
		IF ca3_audio_playing = 0
			IF HAS_MISSION_AUDIO_LOADED ca3_alt_slot
				CLEAR_MISSION_AUDIO ca3_alt_slot
			ENDIF
			ca3_audio_playing = 1
			ca3_audio_underway = 1
		ENDIF

		IF ca3_audio_playing = 1
			LOAD_MISSION_AUDIO ca3_audio_slot ca3_audio[ca3_counter]
			//GOSUB ca3_dialogue_pos
			//ATTACH_MISSION_AUDIO_TO_PED ca3_audio_slot ca3_audio_char
			ca3_audio_playing = 2
		ENDIF

		IF ca3_audio_playing = 2
		 	IF HAS_MISSION_AUDIO_LOADED ca3_audio_slot
				PLAY_MISSION_AUDIO ca3_audio_slot
				PRINT_NOW $ca3_text[ca3_counter] 10000 1
				ca3_audio_playing = 3
			ENDIF
		ENDIF

		IF ca3_audio_playing = 3
			IF HAS_MISSION_AUDIO_FINISHED ca3_audio_slot
				CLEAR_THIS_PRINT $ca3_text[ca3_counter]
				IF ca3_audio_slot = 1
					ca3_audio_slot = 2
					ca3_alt_slot = 1
				ELSE
					ca3_audio_slot = 1
					ca3_alt_slot = 2
				ENDIF
				ca3_counter = 0
				ca3_audio_playing = 0
			
				ca3_audio_underway = 0 // okay to cue up next piece of convo text

			ELSE
				IF NOT HAS_MISSION_AUDIO_LOADED ca3_alt_slot
					IF ca3_counter < 10
						ca3_ahead_counter = ca3_counter + 1
						LOAD_MISSION_AUDIO ca3_alt_slot ca3_audio[ca3_ahead_counter]
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
// End of dialogue loader / player



IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
            GOTO mission_casino3_passed  
ENDIF

SET_OBJECT_ROTATION ca3_rcontainer 0.0 0.0 ca3_r1z
IF NOT ca3_ctskip_needed = 1 
	ca3_r1z = ca3_r1z + 0.2
ENDIF

IF ca3_ctskip_needed = 1 
	ca3_r1z = ca3_r1z + 0.4
ENDIF


IF ca3_r1z > 359.0
 ca3_r1z = 0.0															 
ENDIF

SET_OBJECT_ROTATION ca3_rcontainer2 0.0 0.0 ca3_r2z
ca3_r2z = ca3_r2z + 0.25

IF ca3_r2z > 359.0
 ca3_r2z = 0.0
ENDIF





IF ca3_ctskip_needed = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		//WHILE IS_BUTTON_PRESSED PAD1 CROSS
		//	WAIT 0
		//ENDWHILE

		IF ca3_Cover_Blown_Cut > 1
			//write_debug shit
			ca3_Cover_Blown_Cut = 10
			ca3_ctskip_needed = 0
		ENDIF

		//IF ca3_gotplvan < 9
		  //	ca3_gotplvan = 9
		   //	ca3_ctskip_needed = 0 // Don't want to read PS2 joypad anymore
	   //ENDIF
	ENDIF
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_W

	SET_CHAR_COORDINATES scplayer 1002.1711 2079.4431 9.8203
	SET_CAMERA_BEHIND_PLAYER 
	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SPAS12_SHOTGUN 2000
ENDIF




IF ca3_contextskip = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		//WHILE IS_BUTTON_PRESSED PAD1 CROSS
		//	WAIT 0
		//ENDWHILE

			ca3_contextcut = 5
			ca3_contextskip = 0

	ENDIF
ENDIF

IF ca3_contextskip2 = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		//WHILE IS_BUTTON_PRESSED PAD1 CROSS
		//	WAIT 0
		//ENDWHILE

			ca3_contextcut2 = 5
			ca3_contextskip2 = 0

	ENDIF
ENDIF

IF ca3_finalskip = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		//WHILE IS_BUTTON_PRESSED PAD1 CROSS
		//	WAIT 0
		//ENDWHILE

			ca3_BlowUpMachines = 40
			ca3_finalskip = 0

	ENDIF
ENDIF

 

IF ca3_skip = 1

	IF ca3_driven_to_factory = 1
		SET_UP_SKIP	1009.6833 1987.6398 9.6796 1.8160 
		ca3_skip = 0
	ENDIF

ENDIF


IF ca3_gotplvan = 5
	IF NOT IS_CHAR_DEAD ca3_gate1P
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer ca3_gate1P 100.0 100.0 FALSE 
			CLEAR_SKIP
			ca3_skip = 0
	  		CLEAR_PRINTS
			PRINT_NOW CA3_2 6000 1	// Try and get in unnoticed!

			/*
			ADD_BLIP_FOR_CHAR ca3_gate1P ca3_gate1B
			CHANGE_BLIP_SCALE ca3_gate1B 1
		
			IF NOT IS_CHAR_DEAD ca3_gate2P
				ADD_BLIP_FOR_CHAR ca3_gate2P ca3_gate2B
				CHANGE_BLIP_SCALE ca3_gate2B 2
			ENDIF
			*/
	   		ca3_gotplvan = 10


			GET_MAX_WANTED_LEVEL ca3_wantedlevel
			//WRITE_DEBUG_WITH_INT wl ca3_wantedlevel
			SET_MAX_WANTED_LEVEL 0

			ca3_driven_to_factory = 1 // player has driven to factory once before so we can let him skip
		
		//ca3_gotplvan = -1
	
		ENDIF
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1009.6833 1987.6398 9.6796 25.0 25.0 5.0 FALSE
		CLEAR_SKIP
		ca3_skip = 0
	ENDIF


ENDIF



IF ca3_gotplvan = 10

	/*
   	IF NOT SLIDE_OBJECT ca3_gateO 1055.629 2087.67 14.899 0.0 0.0 0.04 FALSE
			SLIDE_OBJECT ca3_gateO 1055.629 2087.67 14.899 0.0 0.0 0.04 FALSE
	ENDIF
	*/

	IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 1048.4285 2093.5213 1048.8224 2081.8444 15.0 FALSE
	//write_debug inarea
	//IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 1055.9634 2093.8372 1055.8059 2081.0337 10.0 FALSE
   		ca3_BlowUpMachines = 1
		ca3_gotplvan =99
	ENDIF

	IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 1055.1740 2064.7097 1054.8353 21099.8091 15.0 FALSE
   		IF IS_CHAR_SHOOTING scplayer
					shoot_fix = 1
					timerb = 0
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST ca3_forkie3
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ca3_forkie3 scplayer

			ca3_BlowupMachines = 1
			ca3_gotplvan = 99


		ENDIF

	ENDIF

	
	IF shoot_fix = 1
		IF timerb > 500	 //1000
			ca3_BlowUpMachines = 1
			ca3_gotplvan = 99

		ENDIF
	ENDIF

	/*
	IF NOT IS_CHAR_DEAD ca3_foreman1 
	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ca3_foreman1 scplayer
		ca3_BlowUpMachines = 1
		ca3_gotplvan = 99

	ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD ca3_contextgoon 
	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ca3_contextgoon scplayer
		ca3_BlowUpMachines = 1
		ca3_gotplvan = 99

	ENDIF
	ENDIF
	

 
	IF IS_CHAR_DEAD ca3_contextgoon
		ca3_BlowUpMachines = 1
		ca3_gotplvan = 99

	ENDIF
	
	IF IS_CHAR_DEAD ca3_foreman1
		ca3_BlowUpMachines = 1
		ca3_gotplvan = 99
	ENDIF
	*/
	 

ENDIF // ca3_plgotvan = 10 condition check


IF ca3_BlowUpMachines = 0
//IF ca3_gotplvan > 8
IF ca3_gotplvan < 0

	IF ca3_forktog = 0
	IF NOT IS_CHAR_DEAD ca3_forkie3
		IF NOT IS_CAR_DEAD ca3_forklift3
			TASK_CAR_DRIVE_TO_COORD ca3_forkie3 ca3_forklift3 1030.4415 2106.3191 9.8203 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
			ca3_forktog = 1
		   
		ENDIF
	ENDIF
	ENDIF

	IF ca3_forktog = 1
	IF NOT IS_CHAR_DEAD ca3_forkie3
		IF NOT IS_CAR_DEAD ca3_forklift3

	   		IF LOCATE_CAR_3D ca3_forklift3 1030.4415 2106.3191 9.8203 3.0 3.0 3.0 FALSE
				ca3_forktog = 2
				//SET_CAR_TEMP_ACTION ca3_forklift3 TEMPACT_REVERSE 5000

	   			TASK_CAR_DRIVE_TO_COORD ca3_forkie3 ca3_forklift3 1060.1465 2084.9863 9.8203 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
				//SET_CAR_TEMP_ACTION ca3_forklift3 TEMPACT_REVERSE 5000

			ENDIF
		ENDIF
	ENDIF
	ENDIF


	IF ca3_forktog = 2
	IF NOT IS_CHAR_DEAD ca3_forkie3
		IF NOT IS_CAR_DEAD ca3_forklift3

	   		IF LOCATE_CAR_3D ca3_forklift3 1060.1465 2084.9863 9.8203 5.0 5.0 3.0 FALSE
				ca3_forktog = 0
				//WAIT 5000
				//SET_CAR_TEMP_ACTION ca3_forklift3 TEMPACT_REVERSE 5000
				
	   			//TASK_CAR_DRIVE_TO_COORD ca3_forkie3 ca3_forklift3 1059.5518 2079.1340 9.8203 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
			ENDIF
		ENDIF
	ENDIF
	ENDIF






ENDIF
ENDIF










IF ca3_BlowUpMachines = 1


	IF ca3_BeenSpotted = 0


	

		
		IF ca3_contextcut = 0

			

			SET_PLAYER_CONTROL player1 OFF
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE

			DO_FADE 500 FADE_OUT
					 

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

		
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 1083.0099 2099.1306 14.3504 ca3_foreman1
			SET_CHAR_HEADING ca3_foreman1 186.4201 
			GIVE_WEAPON_TO_CHAR ca3_foreman1 WEAPONTYPE_PISTOL 2000
			SET_CHAR_DECISION_MAKER ca3_foreman1 ca3_goondm


			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 1084.2815 2097.9480 14.3504 ca3_contextgoon	//cutscene fuck up
			SET_CHAR_HEADING ca3_contextgoon 153.4201 
			GIVE_WEAPON_TO_CHAR ca3_contextgoon WEAPONTYPE_PISTOL 2000
			SET_CHAR_DECISION_MAKER ca3_contextgoon ca3_goondm

		
		
		
			SET_CHAR_COORDINATES scplayer 1050.5325 2087.9326 9.8203
			SET_CHAR_HEADING scplayer 282.0
				
			

			

			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION 1088.8477 2094.2385 17.2308 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1087.8687 2094.2224 17.0277 JUMP_CUT
			
			 DO_FADE 500 FADE_IN
					 

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE


			
	

			timera = 0
			ca3_contextcut = 1
	  		//ca3_counter = 8

			IF NOT IS_CHAR_DEAD ca3_foreman1
					FLUSH_ROUTE

					EXTEND_ROUTE 1083.3608 2093.4502 14.3504
					//EXTEND_ROUTE 1091.5479 2122.4487 14.3504
					//EXTEND_ROUTE 1086.2125 2141.9678 14.3504
					//EXTEND_ROUTE 1068.7489 2144.6396 14.3516
					//EXTEND_ROUTE 1086.0752 2139.8628 14.3504
								
					TASK_FOLLOW_POINT_ROUTE ca3_foreman1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
					//SET_FOLLOW_NODE_THRESHOLD_DISTANCE ca3_foreman1 150.0
	  		ENDIF

			IF NOT IS_CHAR_DEAD ca3_contextgoon
					FLUSH_ROUTE

					EXTEND_ROUTE 1084.7522 2093.2078 14.3504
					//EXTEND_ROUTE 1091.5479 2122.4487 14.3504
					//EXTEND_ROUTE 1086.2125 2141.9678 14.3504
					//EXTEND_ROUTE 1068.7489 2144.6396 14.3516
					//EXTEND_ROUTE 1086.0752 2139.8628 14.3504
								
					TASK_FOLLOW_POINT_ROUTE ca3_contextgoon PEDMOVE_WALK FOLLOW_ROUTE_ONCE
					//SET_FOLLOW_NODE_THRESHOLD_DISTANCE ca3_foreman1 150.0
	  		ENDIF

			IF NOT IS_CHAR_DEAD scplayer
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
					FLUSH_ROUTE

					EXTEND_ROUTE 1057.5325 2087.9326 9.8203	
					//EXTEND_ROUTE 1091.5479 2122.4487 14.3504
					//EXTEND_ROUTE 1086.2125 2141.9678 14.3504
					//EXTEND_ROUTE 1068.7489 2144.6396 14.3516
					//EXTEND_ROUTE 1086.0752 2139.8628 14.3504
								
					TASK_FOLLOW_POINT_ROUTE scplayer PEDMOVE_WALK FOLLOW_ROUTE_ONCE
					//SET_FOLLOW_NODE_THRESHOLD_DISTANCE ca3_foreman1 150.0
			ENDIF
	  		ENDIF

	  		//PRINT_NOW CA3_10 4000 1
		ENDIF

		IF ca3_contextcut = 1
			IF timera > 1000
				ca3_contextskip = 1
			ENDIF
			IF timera > 3000
					ca3_counter = 8
					IF NOT IS_CHAR_DEAD ca3_foreman1
				 	  	
								  	
				  	CLEAR_CHAR_TASKS ca3_foreman1
					TASK_TURN_CHAR_TO_FACE_CHAR	ca3_foreman1 scplayer

					//CLEAR_CHAR_TASKS ca3_contextgoon
				 	//TASK_TURN_CHAR_TO_FACE_CHAR ca3_contextgoon ca3_foreman1
				  	  				
				  	ENDIF
	
					IF NOT IS_CHAR_DEAD ca3_contextgoon
					FLUSH_ROUTE

					EXTEND_ROUTE 1082.3755 2090.2505 14.35044
					//EXTEND_ROUTE 1091.5479 2122.4487 14.3504
					//EXTEND_ROUTE 1086.2125 2141.9678 14.3504
					//EXTEND_ROUTE 1068.7489 2144.6396 14.3516
					//EXTEND_ROUTE 1086.0752 2139.8628 14.3504
								
					TASK_FOLLOW_POINT_ROUTE ca3_contextgoon PEDMOVE_WALK FOLLOW_ROUTE_ONCE
					//SET_FOLLOW_NODE_THRESHOLD_DISTANCE ca3_foreman1 150.0
	  	   			ENDIF





					
					ca3_contextcut = 2
			 ENDIF	
			 
			 
			 	
		ENDIF
				
  		IF ca3_contextcut = 2 
		  
			IF timera > 6000
				ca3_counter = 9
				//PRINT_NOW CA3_9 4000 1
				IF NOT IS_CHAR_DEAD ca3_foreman1
					CLEAR_CHAR_TASKS ca3_foreman1
					FLUSH_ROUTE

					EXTEND_ROUTE 1088.5259 2112.6157 14.3504
					//EXTEND_ROUTE 1091.5479 2122.4487 14.3504
					//EXTEND_ROUTE 1086.2125 2141.9678 14.3504
					//EXTEND_ROUTE 1068.7489 2144.6396 14.3516
					//EXTEND_ROUTE 1086.0752 2139.8628 14.3504
								
					TASK_FOLLOW_POINT_ROUTE ca3_foreman1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					//SET_FOLLOW_NODE_THRESHOLD_DISTANCE ca3_foreman1 150.0
	  			ENDIF

	   				IF NOT IS_CHAR_DEAD ca3_forkie3
						IF NOT IS_CAR_DEAD ca3_forklift3
						
							TASK_CAR_DRIVE_TO_COORD ca3_forkie3 ca3_forklift3 1076.8090 2080.5261 9.8203 9.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
						ENDIF
					ENDIF


				IF NOT IS_CHAR_DEAD ca3_contextgoon
					CLEAR_CHAR_TASKS ca3_contextgoon
				   	
				   //	TASK_TURN_CHAR_TO_FACE_CHAR ca3_contextgoon ca3_foreman1

				   	TASK_AIM_GUN_AT_CHAR ca3_contextgoon scplayer 4000
				  // 	TASK_PLAY_ANIM ca3_contextgoon RIFLE_crouchreload PED 8.0 FALSE FALSE FALSE TRUE -1
				ENDIF
				ca3_contextcut = 3
				timera = 0
			ENDIF
		ENDIF

		IF ca3_contextcut = 3
			IF timera > 2000
				ca3_contextcut = 5
				ca3_contextskip = 0
				IF NOT IS_CHAR_DEAD ca3_forkie3
						CLEAR_CHAR_TASKS ca3_forkie3
						OPEN_SEQUENCE_TASK ca3_forkie3leaveseq
	   						TASK_LEAVE_ANY_CAR -1		
							TASK_GO_STRAIGHT_TO_COORD -1 1088.7828 2077.5725 9.8203 PEDMOVE_RUN -1
						CLOSE_SEQUENCE_TASK ca3_forkie3leaveseq
						PERFORM_SEQUENCE_TASK ca3_forkie3 ca3_forkie3leaveseq
						CLEAR_SEQUENCE_TASK ca3_forkie3leaveseq
				ENDIF

			ENDIF
		ENDIF
   		
	
		IF ca3_contextcut = 5
	    	SWITCH_WIDESCREEN OFF
			IF NOT IS_CHAR_DEAD ca3_forkie3
				IF IS_CHAR_SITTING_IN_ANY_CAR ca3_forkie3
					WARP_CHAR_FROM_CAR_TO_COORD ca3_forkie3 1088.7828 2077.5725 9.8203
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD ca3_forklift3
				SET_CAR_COORDINATES ca3_forklift3 1076.8090 2080.5261 9.8203
				SET_CAR_HEADING ca3_forklift3 273.0
			ENDIF 
			RESTORE_CAMERA_JUMPCUT				   
			SET_CAMERA_BEHIND_PLAYER
			CLEAR_CHAR_TASKS scplayer
			IF NOT IS_CAR_DEAD ca3_mafia_car
				SET_CAR_PROOFS ca3_mafia_car FALSE FALSE FALSE FALSE FALSE
			ENDIF
			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE

			ca3_BlowUpMachines = 2
			
			
	  
			ca3_CheckMachineStatus = 1
		ENDIF


	ENDIF // ca3_BeenSpotted = 0 condition check

	



	 IF ca3_BeenSpotted > 0
		IF ca3_contextcut2 = 0

			SET_EVERYONE_IGNORE_PLAYER player1 TRUE

			SET_PLAYER_CONTROL player1 OFF 
			DO_FADE 500 FADE_OUT
					 

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

		   //	IF NOT ca3_BeenSpotted = 4
			SET_CHAR_COORDINATES scplayer 1050.5325 2087.9326 9.8203
			SET_CHAR_HEADING scplayer 282.0
		   //	ENDIF
				

		
			CREATE_CHAR PEDTYPE_MISSION1 VMAFF4 1086.8689 2078.6096 14.3504 ca3_foreman1
			SET_CHAR_HEADING ca3_foreman1 86.4201 
			GIVE_WEAPON_TO_CHAR ca3_foreman1 WEAPONTYPE_PISTOL 2000
			SET_CHAR_DECISION_MAKER ca3_foreman1 ca3_goondm


			CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 1086.4199 2077.2915 14.3504 ca3_contextgoon	//cutscene fuck up
			SET_CHAR_HEADING ca3_contextgoon 74.4201 
			GIVE_WEAPON_TO_CHAR ca3_contextgoon WEAPONTYPE_PISTOL 2000
			SET_CHAR_DECISION_MAKER ca3_contextgoon ca3_goondm

		
		
				
			SET_PLAYER_CONTROL player1 OFF 
			SWITCH_WIDESCREEN ON
			SET_FIXED_CAMERA_POSITION 1075.7250 2094.5449 18.0271 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1074.8522 2094.5291 17.5392 JUMP_CUT

		   //	SET_FIXED_CAMERA_POSITION 1091.1034 2077.0864 15.6725 0.0 0.0 0.0
		   //	POINT_CAMERA_AT_POINT 1090.2365 2077.5757 15.5788 JUMP_CUT

			 DO_FADE 500 FADE_IN
					 

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE


			timera = 0
			ca3_contextcut2 = 1

			ca3_counter = 7
			//PRINT_NOW CA3_3 4000 1

			//IF NOT ca3_BeenSpotted = 4
			IF NOT IS_CHAR_DEAD scplayer
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
					FLUSH_ROUTE

					EXTEND_ROUTE 1057.5325 2087.9326 9.8203	
					//EXTEND_ROUTE 1091.5479 2122.4487 14.3504
					//EXTEND_ROUTE 1086.2125 2141.9678 14.3504
					//EXTEND_ROUTE 1068.7489 2144.6396 14.3516
					//EXTEND_ROUTE 1086.0752 2139.8628 14.3504
								
					TASK_FOLLOW_POINT_ROUTE scplayer PEDMOVE_WALK FOLLOW_ROUTE_ONCE
					//SET_FOLLOW_NODE_THRESHOLD_DISTANCE ca3_foreman1 150.0
			ENDIF
	  		ENDIF
		   //	ENDIF


			 
		ENDIF
				
  		IF ca3_contextcut2 = 1 
			IF timera > 300
				//ca3_contextskip2 = 1
				IF ca3_cutfix = 0
					IF NOT IS_CHAR_DEAD ca3_contextgoon
				 		CLEAR_CHAR_TASKS ca3_contextgoon
				 		TASK_GO_TO_COORD_WHILE_AIMING ca3_contextgoon 1079.8713 2079.3027 14.3516 PEDMOVE_WALK 0.5 0.5 scplayer 0.0 0.0 0.0
			   	 	   //	ca3_cutfix = 1
			   		ENDIF

					IF NOT IS_CHAR_DEAD ca3_forkie3
						IF NOT IS_CAR_DEAD ca3_forklift3
						
							TASK_CAR_DRIVE_TO_COORD ca3_forkie3 ca3_forklift3 1076.8090 2080.5261 9.8203 9.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
						ENDIF
					ENDIF

				

					IF NOT IS_CHAR_DEAD ca3_gate1P
						
					  	CLEAR_CHAR_TASKS ca3_gate1P
						OPEN_SEQUENCE_TASK ca3_highseq
	   						//TASK_LEAVE_ANY_CAR -1		
							TASK_GO_STRAIGHT_TO_COORD -1 1064.6775 2094.7896 13.6896 PEDMOVE_RUN -1
							TASK_TOGGLE_DUCK -1	TRUE
						CLOSE_SEQUENCE_TASK ca3_highseq
						PERFORM_SEQUENCE_TASK ca3_gate1P ca3_highseq
						CLEAR_SEQUENCE_TASK ca3_highseq
						TASK_STAY_IN_SAME_PLACE ca3_gate1P TRUE
					ENDIF


					IF NOT IS_CHAR_DEAD ca3_gate2P
					 
						TASK_TOGGLE_DUCK ca3_gate2P TRUE
						TASK_STAY_IN_SAME_PLACE ca3_gate2P TRUE
					
					ENDIF
					ca3_contextskip2 = 1

					ca3_cutfix = 1
				ENDIF

				


			ENDIF
			IF timera > 4000
				ca3_counter = 6
				//PRINT_NOW CA3_9 4000 1
			 	ca3_contextcut2 = 3
				timera = 0
			ENDIF
		
		   
				
		
		
		ENDIF

		IF ca3_contextcut2 = 3
			IF timera > 3000
				ca3_contextcut2 = 5
				ca3_contextskip2 = 0
				IF NOT IS_CHAR_DEAD ca3_forkie3
						CLEAR_CHAR_TASKS ca3_forkie3
						OPEN_SEQUENCE_TASK ca3_forkie3leaveseq
	   						TASK_LEAVE_ANY_CAR -1		
							TASK_GO_STRAIGHT_TO_COORD -1 1088.7828 2077.5725 9.8203 PEDMOVE_RUN -1
						CLOSE_SEQUENCE_TASK ca3_forkie3leaveseq
						PERFORM_SEQUENCE_TASK ca3_forkie3 ca3_forkie3leaveseq
						CLEAR_SEQUENCE_TASK ca3_forkie3leaveseq
				ENDIF

			ENDIF
		ENDIF
   		
	
		IF ca3_contextcut2 = 5
			
	    	SWITCH_WIDESCREEN OFF

			IF NOT IS_CHAR_DEAD ca3_contextgoon
				CLEAR_CHAR_TASKS ca3_contextgoon
				SET_CHAR_COORDINATES ca3_contextgoon 1079.8713 2079.3027 14.3516
			ENDIF

			IF NOT IS_CHAR_DEAD ca3_forkie3
				IF IS_CHAR_SITTING_IN_ANY_CAR ca3_forkie3
					WARP_CHAR_FROM_CAR_TO_COORD ca3_forkie3 1088.7828 2077.5725 9.8203
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD ca3_forklift3
				SET_CAR_COORDINATES ca3_forklift3 1076.8090 2080.5261 9.8203
				SET_CAR_HEADING ca3_forklift3 273.0
			ENDIF 
			
			RESTORE_CAMERA_JUMPCUT				   
			SET_CAMERA_BEHIND_PLAYER
			CLEAR_CHAR_TASKS scplayer
			IF NOT IS_CAR_DEAD ca3_mafia_car
				SET_CAR_PROOFS ca3_mafia_car FALSE FALSE FALSE FALSE FALSE
			ENDIF

			SET_PLAYER_CONTROL player1 ON
			SET_EVERYONE_IGNORE_PLAYER player1 FALSE

			ca3_BlowUpMachines = 2

			ca3_CheckMachineStatus = 1
		ENDIF


 
	 ENDIF
	











ENDIF // ca3_BlowUpMachines = 1 condition check



IF ca3_BlowUpMachines = 2
	
  
	REMOVE_BLIP ca3_factoryB
   //	REMOVE_BLIP ca3_gate1B
   //	REMOVE_BLIP ca3_gate2B

	c = 0
	WHILE c < 9
		ca3_machine_flag[c] = 0
 		ADD_BLIP_FOR_OBJECT	ca3_machine[c] ca3_machineblip[c]
		SET_OBJECT_PROOFS ca3_machine[c] FALSE FALSE FALSE FALSE FALSE
		CHANGE_BLIP_SCALE ca3_machineblip[c] 2
		SET_OBJECT_HEALTH ca3_machine[c] 1000
		MAKE_OBJECT_TARGETTABLE ca3_machine[c] TRUE

		++ c
	ENDWHILE

	CLEAR_PRINTS
	PRINT_NOW CA3_7 6000 1 // Hit those machines!

	IF NOT IS_CHAR_DEAD ca3_forkie3
		CLEAR_CHAR_TASKS ca3_forkie3
	   //	TASK_LEAVE_ANY_CAR ca3_forkie3
		TASK_KILL_CHAR_ON_FOOT ca3_forkie3 scplayer
	ENDIF


	IF ca3_BeenSpotted > 0
		GOSUB ca3_In_Factory_Was_Spotted
		ca3_BlowUpMachines = 4
	ENDIF


	IF ca3_BeenSpotted = 0
		GOSUB ca3_In_Factory_Was_Not_Spotted
		ca3_BlowUpMachines = 4
	ENDIF

	GOSUB ca3_bossfight
	
ENDIF  // ca3_BlowUpMachines = 2 condition check




IF ca3_BlowUpMachines = 8
	/*
	IF ca3_bossinit = 0
		IF NOT IS_CHAR_DEAD ca3_bossP
		
			ADD_BLIP_FOR_CHAR ca3_bossP ca3_bossB
			PRINT_NOW CA3_10 6000 1
			ca3_bossinit = 1
		ENDIF
	ENDIF

		
   	
	IF IS_CHAR_DEAD ca3_bossP
		IF ca3_bossinit = 1
			REMOVE_BLIP ca3_bossB
		ENDIF
		WAIT 2000
		PRINT_NOW CA3_23 6000 1
		ADD_BLIP_FOR_COORD 1007.0504 2170.6255 9.6719 ca3_exitB  		
		ca3_BlowUpMachines = 9
	ENDIF
	*/

   //	WAIT 2000
	PRINT_NOW CA3_23 6000 1
	SET_MAX_WANTED_LEVEL ca3_wantedlevel
	ADD_BLIP_FOR_COORD 1918.5901 958.3391 9.8203 ca3_exitB  
	CHANGE_BLIP_COLOUR ca3_exitB YELLOW	
	MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
	MARK_MODEL_AS_NO_LONGER_NEEDED wmymech

	REQUEST_MODEL TRIADA
	REQUEST_MODEL TRIADB
	REQUEST_MODEL WASHING
		
	ca3_BlowUpMachines = 9

	//LOAD_SPECIAL_CHARACTER 1 WUZIMU
	
	//WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	 WHILE NOT HAS_MODEL_LOADED TRIADA
		 OR NOT HAS_MODEL_LOADED WASHING
		 OR NOT HAS_MODEL_LOADED TRIADB
		 WAIT 0
	ENDWHILE

ENDIF



// All machines blown up, exit area defined

IF ca3_BlowUpMachines = 9

	GET_AREA_VISIBLE ca3_visible

   	IF ca3_visible = 0
		 IF NOT IS_MINIGAME_IN_PROGRESS	
				SET_PLAYER_CONTROL player1 ON
		 ENDIF
	ENDIF

   	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1918.5901 958.3391 9.8203 4.0 4.0 2.0 TRUE

			IF IS_CHAR_IN_ANY_CAR scplayer
			
				SET_PLAYER_CONTROL player1 OFF
				IF IS_CHAR_STOPPED scplayer

		   

				CREATE_CAR WASHING 1902.1685 974.9742 9.8203 ca3_wuzicar
				SET_CAR_HEADING ca3_wuzicar 197.2609 
				CHANGE_CAR_COLOUR ca3_wuzicar 4 2

				IF NOT IS_CAR_DEAD ca3_wuzicar
					CREATE_CHAR_INSIDE_CAR ca3_wuzicar PEDTYPE_MISSION2 TRIADA ca3_wuzidriver

					CREATE_CHAR_AS_PASSENGER ca3_wuzicar PEDTYPE_MISSION2 TRIADB 1 ca3_wuzi
				ENDIF
				ca3_BlowUpMachines = 15
				ENDIF
			


			ELSE

				SET_PLAYER_CONTROL player1 OFF
			
		   

				CREATE_CAR WASHING 1902.1685 974.9742 9.8203 ca3_wuzicar
				SET_CAR_HEADING ca3_wuzicar 197.2609 
				CHANGE_CAR_COLOUR ca3_wuzicar 4 2

				IF NOT IS_CAR_DEAD ca3_wuzicar
					CREATE_CHAR_INSIDE_CAR ca3_wuzicar PEDTYPE_MISSION2 TRIADA ca3_wuzidriver

					CREATE_CHAR_AS_PASSENGER ca3_wuzicar PEDTYPE_MISSION2 TRIADB 1 ca3_wuzi
				ENDIF
				ca3_BlowUpMachines = 15

			ENDIF

	   ENDIF // locate


ENDIF // ca3_BlowUpMachines = 9 condition check



IF ca3_BlowUpMachines = 15
	
		MARK_CAR_AS_NO_LONGER_NEEDED ca3_backupdriveby
		MARK_CAR_AS_NO_LONGER_NEEDED ca3_mafia_car

	SET_PLAYER_CONTROL player1 OFF 
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE

	CLEAR_PRINTS
	CLEAR_AREA 1913.6818 962.0125 9.8203 20.0 TRUE
	CLEAR_AREA 1903.4680 967.1692 9.8127 20.0 TRUE


	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION 1922.2339 951.8628 12.2282 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1921.7146 952.7101 12.1171 JUMP_CUT
	ca3_BlowUpMachines = 17
	timera = 0

	CLEAR_CHAR_TASKS scplayer
	
	IF IS_CHAR_IN_ANY_CAR scplayer
   		OPEN_SEQUENCE_TASK ca3_playerseq
   			TASK_LEAVE_ANY_CAR -1		
			TASK_GO_STRAIGHT_TO_COORD -1 1913.6818 962.0125 9.8203 PEDMOVE_WALK -1
		CLOSE_SEQUENCE_TASK ca3_playerseq
		PERFORM_SEQUENCE_TASK scplayer ca3_playerseq
		CLEAR_SEQUENCE_TASK ca3_playerseq
	ELSE
		OPEN_SEQUENCE_TASK ca3_playerseq
   		 
			TASK_GO_STRAIGHT_TO_COORD -1 1913.6818 962.0125 9.8203 PEDMOVE_WALK -1
		CLOSE_SEQUENCE_TASK ca3_playerseq
		PERFORM_SEQUENCE_TASK scplayer ca3_playerseq
		CLEAR_SEQUENCE_TASK ca3_playerseq

	ENDIF

ENDIF


IF ca3_BlowUpMachines = 17
	IF timera > 1000
	   //	ca3_finalskip = 1
		ca3_BlowUpMachines = 20
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_START


	ENDIF
ENDIF

IF ca3_BlowUpMachines = 20
//	 IF timerb > 3000
//		ca3_finalskip = 1
//	 ENDIF
	 IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.02 FALSE
			SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.02 FALSE
	 ENDIF


	 IF timera >  4000
	 //IF SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.02 FALSE
	 	ca3_BlowUpMachines = 30
		IF NOT IS_CHAR_DEAD ca3_wuzidriver
			IF NOT IS_CAR_DEAD ca3_wuzicar
				TASK_CAR_DRIVE_TO_COORD ca3_wuzidriver ca3_wuzicar 1909.0400 959.7415 9.8203 7.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
				//SET_FIXED_CAMERA_POSITION 1040.4857 2085.0986 11.8834  0.0 0.0 0.0
				//POINT_CAMERA_AT_POINT 1041.4290 2085.4268 11.8331 JUMP_CUT
				SET_FIXED_CAMERA_POSITION 1914.8311 962.6716 11.3214  0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1913.8368 962.5726 11.2813 JUMP_CUT

				SET_CHAR_COORDINATES scplayer 1913.6818 962.0125 9.8203
				SET_CHAR_HEADING scplayer 100.3
				timera = 0
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_STOP
			ENDIF

		ENDIF

	 ENDIF
ENDIF

IF ca3_BlowUpMachines = 30
  	
  	IF NOT IS_CAR_DEAD ca3_wuzicar
		//WRITE_DEBUG check30
  		IF LOCATE_CAR_2D ca3_wuzicar 1909.0400 959.7415 3.0 3.0	FALSE
			ca3_BlowUpMachines = 35
			IF NOT IS_CHAR_DEAD ca3_wuzi
				TASK_LOOK_AT_CHAR ca3_wuzi scplayer	-1
			ENDIF
			//WRITE_DEBUG check35
			//PRINT_NOW ca3_24 5000 1
			ca3_counter = 10
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_START
			timera = 0	//new
		ENDIF
		IF timera > 10000
		   ca3_BlowUpMachines = 35
		   REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_START
		   timera = 0
		ENDIF

	ENDIF
ENDIF

IF ca3_BlowUpMachines = 35

	IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	  		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	ENDIF
	
	//IF SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	IF timera > 3000
		ca3_BlowUpMachines = 36
		timera = 0
		//ca3_counter = 11
		ca3_splitfix = 1
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_STOP
	ENDIF

ENDIF



IF ca3_splitfix  > 0
		   	IF ca3_audio_underway = 0

				SWITCH ca3_splitfix 
					CASE 1
						ca3_counter = 11
					  

						ca3_splitfix = 2
						BREAK
					CASE 2	
						ca3_counter = 12
					   
						ca3_splitfix  = 0 // finish convo
						BREAK


				ENDSWITCH
			   //	sw7_audio_underway = 1
		   	ENDIF
ENDIF







IF ca3_BlowUpMachines = 36
	IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	  		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	ENDIF
	IF timera > 2000
		//PRINT_NOW ca3_25 4000 1
		IF timera > 4000
		IF NOT IS_CAR_DEAD ca3_wuzicar
			IF NOT IS_CHAR_DEAD ca3_wuzidriver
				CLEAR_CHAR_TASKS ca3_wuzidriver
				//ca3_counter = 11
				ca3_BlowUpMachines = 37
				TASK_CAR_DRIVE_TO_COORD ca3_wuzidriver ca3_wuzicar 1915.0400 940.7415 9.8203 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
				timera = 0
				IF NOT IS_CHAR_DEAD ca3_wuzi
					TASK_LOOK_AT_CHAR scplayer ca3_wuzi -1
				ENDIF

			ENDIF
		ENDIF
		ENDIF

	ENDIF
ENDIF


IF ca3_BlowUpMachines = 37
	IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	  		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	ENDIF
	IF timera > 4000
		ca3_BlowUpMachines = 40
	ENDIF
ENDIF



IF ca3_BlowUpMachines = 40
	IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	  		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	ENDIF
	ca3_finalskip = 0

	IF NOT IS_CHAR_DEAD ca3_wuzi
		CLEAR_CHAR_TASKS_IMMEDIATELY ca3_wuzi
		DELETE_CHAR ca3_wuzi
	ENDIF

	IF NOT IS_CHAR_DEAD ca3_wuzidriver
		CLEAR_CHAR_TASKS_IMMEDIATELY ca3_wuzidriver
		DELETE_CHAR ca3_wuzidriver
	ENDIF
	DELETE_CAR ca3_wuzicar

	SWITCH_WIDESCREEN OFF
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	RESTORE_CAMERA_JUMPCUT				   
	SET_CAMERA_BEHIND_PLAYER
	SET_PLAYER_CONTROL player1 ON
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE

	SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.82 11.438
	SET_OBJECT_HEADING fourdragons_door 0.0

	ca3_BlowUpMachines = 45
	CLEAR_PRINTS
	GOTO mission_casino3_passed

ENDIF

	





 // New mobile phone stuff

IF ca3_BlowUpMachines < 1 // Player should still under cover at this point, if he harms any mafia the mission is failed!
	




   //	IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 1018.6445 2100.5955 1020.0024 2145.0366 -40.0 FALSE
	IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 1030.1047 2095.7146 1054.8175 2098.7776 30.0 FALSE

		IF ca3_BeenSpotted = 0
		IF NOT IS_CHAR_DEAD ca3_gate1P
   			IF HAS_CHAR_SPOTTED_CHAR_IN_FRONT ca3_gate1P scplayer
				ca3_BeenSpotted = 1
				ca3_Cover_Blown_Cut	= 1
			ENDIF
		ENDIF
		ENDIF

		IF ca3_BeenSpotted = 0
		IF NOT IS_CHAR_DEAD ca3_gate2P
			IF HAS_CHAR_SPOTTED_CHAR_IN_FRONT ca3_gate2P scplayer
				//PRINT_NOW ca3_5 6000 1

				ca3_BeenSpotted = 1
				ca3_Cover_Blown_Cut = 1
	   			//GOSUB ca3_Cover_Blown
	   
	   
	   		ENDIF
		ENDIF
		ENDIF

	ENDIF

	IF ca3_BeenSpotted = 0
	IF DOES_CHAR_EXIST ca3_gate1P
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ca3_gate1P scplayer
			ca3_BeenSpotted = 1
				ca3_Cover_Blown_Cut = 1

		ENDIF
	ENDIF
	ENDIF

	IF ca3_BeenSpotted = 0
	IF DOES_CHAR_EXIST ca3_gate2P
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ca3_gate2P scplayer
   			 ca3_BeenSpotted = 1
				ca3_Cover_Blown_Cut = 1
   		ENDIF
	ENDIF
	ENDIF

	IF ca3_BeenSpotted = 0
	IF NOT IS_CAR_DEAD ca3_mafia_car
		IF HAS_CAR_BEEN_DAMAGED_BY_CHAR ca3_mafia_car scplayer
		   //	PRINT_NOW ca3_4 6000 1

			
			ca3_BeenSpotted = 1
			ca3_Cover_Blown_Cut = 1
   
   
   		ENDIF
	ENDIF
	ENDIF

   

	IF ca3_BeenSpotted = 3
	IF DOES_CHAR_EXIST ca3_gate1P
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ca3_gate1P scplayer
		  //	PRINT_NOW ca3_4 6000 1

		 	ca3_BeenSpotted = 4
			GOSUB ca3_Cover_Blown
				ca3_BlowUpMachines = 1
					ca3_gotplvan = 99


		ENDIF
	ENDIF
	ENDIF

	IF ca3_BeenSpotted = 3
	IF DOES_CHAR_EXIST ca3_gate2P
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ca3_gate2P scplayer
		   //	PRINT_NOW ca3_4 6000 1

			ca3_BeenSpotted = 4
   			GOSUB ca3_Cover_Blown
				ca3_BlowUpMachines = 1
					ca3_gotplvan = 99

   
   
   		ENDIF
	ENDIF
	ENDIF




ENDIF // ca3_BlowUpMachines < 1 condition check

IF ca3_Cover_Blown_Cut = 1 //100

	// Take out forklift guy


	SET_PLAYER_CONTROL player1 OFF 
	SWITCH_WIDESCREEN ON

	SET_FIXED_CAMERA_POSITION 1039.3003 2100.4346 12.2503  0.0 0.0 0.0	
	POINT_CAMERA_AT_POINT  1040.1296 2099.9058 12.0705 JUMP_CUT

	ca3_counter = 1

	IF NOT IS_CHAR_DEAD ca3_gate1p
		IF NOT IS_CHAR_DEAD ca3_gate2p
			TASK_LOOK_AT_CHAR ca3_gate1p ca3_gate2p -1
		ENDIF
	ENDIF
	ca3_counter = 1

	timerb = 0

	ca3_Cover_Blown_Cut = 3
	//ca3_ctskip_needed = 1
	 	IF NOT IS_CHAR_DEAD ca3_forkie3
			IF NOT IS_CAR_DEAD ca3_forklift3
				TASK_CAR_DRIVE_TO_COORD ca3_forkie3 ca3_forklift3 1076.8041 2080.4915 9.8203 9.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
			  						 		   
			ENDIF
		ENDIF



ENDIF


IF ca3_Cover_Blown_Cut = 3
	IF timerb > 1000
	   	ca3_ctskip_needed = 1
	ENDIF
	IF timerb > 3000
	ca3_Cover_Blown_Cut = 5
	ca3_counter = 2
	timerb = 0
 	ENDIF
ENDIF

IF ca3_Cover_Blown_Cut = 5


	IF NOT IS_CAR_DEAD ca3_mafia_car
   //	IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR ca3_mafia_car
	
	 IF NOT IS_CHAR_DEAD ca3_gate1P		   
	 	CLEAR_CHAR_TASKS ca3_gate1P		
		OPEN_SEQUENCE_TASK ca3_gate1cutseq
   			TASK_LEAVE_ANY_CAR -1		
			TASK_GO_STRAIGHT_TO_COORD -1 1062.3447 2087.3176 9.8203 PEDMOVE_RUN 10000
			//TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
		CLOSE_SEQUENCE_TASK ca3_gate1cutseq
		PERFORM_SEQUENCE_TASK ca3_gate1P ca3_gate1cutseq
		CLEAR_SEQUENCE_TASK ca3_gate1cutseq
	 ENDIF

	 ca3_Cover_Blown_Cut = 6
	 timerb = 0

	ENDIF
ENDIF

IF ca3_Cover_Blown_Cut = 6

	IF timerb > 1000

	IF NOT IS_CAR_DEAD ca3_mafia_car
   	
	 IF NOT IS_CHAR_DEAD ca3_gate2P
		CLEAR_CHAR_TASKS ca3_gate2p
		OPEN_SEQUENCE_TASK ca3_gate2cutseq
   			TASK_LEAVE_ANY_CAR -1		
			TASK_GO_STRAIGHT_TO_COORD -1 1056.8739 2084.1362 9.8203 PEDMOVE_RUN 10000
			//TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
		CLOSE_SEQUENCE_TASK ca3_gate2cutseq
		PERFORM_SEQUENCE_TASK ca3_gate2P ca3_gate2cutseq
		CLEAR_SEQUENCE_TASK ca3_gate2cutseq
	 ENDIF

	 timerb = 0

	 ca3_Cover_Blown_Cut = 8
	ENDIF
	ENDIF

ENDIF

IF ca3_Cover_blown_cut = 8
  
   	IF timerb > 3700
	 	ca3_Cover_Blown_Cut = 10
  	ENDIF

ENDIF



IF ca3_Cover_Blown_Cut = 10
	SWITCH_WIDESCREEN OFF
	ca3_ctskip_needed = 0
																						    
	SET_CHAR_HEADING scplayer 228.0
	
 	
	IF NOT IS_CHAR_DEAD ca3_gate1P
		CLEAR_CHAR_TASKS_IMMEDIATELY ca3_gate1P
	   //	SET_CHAR_COORDINATES ca3_gate1P 1066.6038 2103.0898 9.8203
	   SET_CHAR_COORDINATES	ca3_gate1P 1073.5579 2094.4126 13.6896
	   SET_CHAR_HEADING ca3_gate1P 83.0
	ENDIF



	IF NOT IS_CHAR_DEAD ca3_gate2P
		CLEAR_CHAR_TASKS_IMMEDIATELY ca3_gate2P
		SET_CHAR_COORDINATES ca3_gate2P 1066.0961 2098.8496 13.7429
		SET_CHAR_HEADING ca3_gate2P 132.0
	ENDIF
//	IF NOT IS_CAR_DEAD ca3_mafia_car 
//		STOP_PLAYBACK_RECORDED_CAR ca3_mafia_car
//
//		SET_CAR_COORDINATES ca3_mafia_car 1048.5704 2090.9443 9.8130
//		SET_CAR_HEADING ca3_mafia_car 173.5338 
// 
//	ENDIF

	IF NOT IS_CAR_DEAD ca3_forklift3
		SET_CAR_COORDINATES ca3_forklift3 1076.8041 2080.4915 9.8203
	ENDIF

	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	CLEAR_PRINTS
	PRINT_NOW ca3_8 6000 1

	//ca3_ctskip_needed = 0

	SET_PLAYER_CONTROL player1 ON
	//GOSUB ca3_Cover_Blown
	ca3_Cover_Blown_Cut = 99
	//ca3_BeenSpotted = 3
	
	ca3_BeenSpotted = 99
	//REMOVE_BLIP ca3_gate1B
	//REMOVE_BLIP ca3_gate2B
ENDIF

IF ca3_CheckMachineStatus = 1
	c = 0
	WHILE c < 9
		IF ca3_machine_flag[c] = 0
			GET_OBJECT_HEALTH ca3_machine[c] ca3_temp_int
			IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON ca3_machine[c] WEAPONTYPE_SPAS12_SHOTGUN
				ca3_temp_int = 0
			ENDIF
			IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON ca3_machine[c] WEAPONTYPE_SHOTGUN
				ca3_temp_int = 0
			ENDIF
			IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON ca3_machine[c] WEAPONTYPE_SAWNOFF_SHOTGUN
				ca3_temp_int = 0
			ENDIF


			IF ca3_temp_int = 0
				GET_OBJECT_COORDINATES ca3_machine[c] ca3_machineX ca3_machineY ca3_machineZ
				//CREATE_FX_SYSTEM ws_factorysmoke ca3_machineX ca3_machineY ca3_machineZ TRUE ca3_fx[c]
				CREATE_FX_SYSTEM overheat_car_electric ca3_machineX ca3_machineY ca3_machineZ TRUE ca3_fx[c]

				//GET_OBJECT_HEADING atm[c] h
				ca3_machineZ = ca3_machineZ + 1.0
			   	ADD_EXPLOSION_VARIABLE_SHAKE ca3_machineX ca3_machineY ca3_machineZ EXPLOSION_SMALL	1.0
				BREAK_OBJECT ca3_machine[c] TRUE
				REMOVE_BLIP ca3_machineblip[c]
				MAKE_OBJECT_TARGETTABLE ca3_machine[c] FALSE


				IF NOT temp_fx = 99
					KILL_FX_SYSTEM ca3_fx[temp_fx]
				ENDIF


			  	PLAY_FX_SYSTEM ca3_fx[c]
				temp_fx = c				

			  				//LVAR_INT broken_atm[4]
				//CREATE_OBJECT_NO_OFFSET KMB_ATM2 x y z broken_atm[c] //BROKEN ATM MODEL HERE
				//SET_OBJECT_HEADING broken_atm[c] h
				//SET_OBJECT_HEALTH broken_atm[c] 150
				++ ca3_machine_flag[c]
				ca3_MachinesDestroyed ++
			ENDIF
		ENDIF

	 	++ c
	ENDWHILE


	IF ca3_goon1proxim = 0
	IF NOT IS_CHAR_DEAD ca3_goon1p
  		IF LOCATE_CHAR_ANY_MEANS_3D ca3_goon1p 1067.5807 2122.2510 9.8203 1.5 1.5 3.0 FALSE
		CLEAR_CHAR_TASKS ca3_goon1p
			OPEN_SEQUENCE_TASK ca3_g1seq
		    TASK_STAY_IN_SAME_PLACE -1 TRUE
			//SWITCH_WIDESCREEN ON
	      	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 5000 60
    	CLOSE_SEQUENCE_TASK ca3_g1seq

    	PERFORM_SEQUENCE_TASK ca3_goon1P ca3_g1seq
    	CLEAR_SEQUENCE_TASK ca3_g1seq	
		
		ca3_goon1proxim = 1
		ENDIF
	ENDIF
	ENDIF 



	

	 

	IF ca3_MachinesDestroyed = 9
		//PRINT_NOW CA3_12 3000 1
		//WAIT 4000
		//CLEAR_PRINTS
	  	ca3_BlowUpMachines = 8
		ca3_CheckMachineStatus = 99
		
	ENDIF
	 	

ENDIF



//IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1084.5360 2078.6572 14.3504 20.0 20.0 20.0 FALSE
  //	IF ca3_bossrammy = 0
	  //	GOSUB ca3_bossfight
	//	ca3_bossrammy = 1
	   //	PRINT ca3_17 4000 1
	//ENDIF
//ENDIF






IF ca3_begingrease = 0
	IF ca3_MachinesDestroyed = 3

	//Camera coordinates
	//1018.9890 2077.7812 21.5002 1019.9006 2077.7725 21.0893 
	 // WRITE_DEBUG test
	 // WAIT 20000

	  CREATE_CHAR PEDTYPE_MISSION1 wmymech 1020.0171 2082.9004 17.9296 ca3_greaser1
	  GIVE_WEAPON_TO_CHAR ca3_greaser1 WEAPONTYPE_SPAS12_SHOTGUN 2000
	  SET_CHAR_HEADING ca3_greaser1 269.9465
	  SET_CHAR_DECISION_MAKER ca3_greaser1 ca3_goondm

	  IF NOT IS_CHAR_DEAD ca3_greaser1
						
	  	CLEAR_CHAR_TASKS ca3_greaser1
		OPEN_SEQUENCE_TASK ca3_greaser1seq
				
			TASK_GO_STRAIGHT_TO_COORD -1 1038.7166 2082.7983 13.9297 PEDMOVE_RUN -1
			TASK_TOGGLE_DUCK -1	TRUE
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
		
		CLOSE_SEQUENCE_TASK ca3_greaser1seq
		PERFORM_SEQUENCE_TASK ca3_greaser1 ca3_greaser1seq
		CLEAR_SEQUENCE_TASK ca3_greaser1seq
	 ENDIF



	  CREATE_CHAR PEDTYPE_MISSION1 wmymech 1020.2634 2097.1851 13.9297 ca3_greaser2
	  GIVE_WEAPON_TO_CHAR ca3_greaser2 WEAPONTYPE_PISTOL 2000
	  SET_CHAR_HEADING ca3_greaser2 274.9465
	  SET_CHAR_DECISION_MAKER ca3_greaser2 ca3_goondm

	  IF NOT IS_CHAR_DEAD ca3_greaser2
						
	  	CLEAR_CHAR_TASKS ca3_greaser2
		OPEN_SEQUENCE_TASK ca3_greaser2seq
				
			TASK_GO_STRAIGHT_TO_COORD -1 1038.4263 2098.6438 9.8203 PEDMOVE_RUN -2
			//TASK_TOGGLE_DUCK -1	TRUE
			//TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
		
		CLOSE_SEQUENCE_TASK ca3_greaser2seq
		PERFORM_SEQUENCE_TASK ca3_greaser2 ca3_greaser2seq
		CLEAR_SEQUENCE_TASK ca3_greaser2seq
	 ENDIF


	  CREATE_CHAR PEDTYPE_MISSION1 wmymech 1019.6710 2078.1265 13.9297 ca3_greaser3
	  GIVE_WEAPON_TO_CHAR ca3_greaser3 WEAPONTYPE_MICRO_UZI 2000
	  SET_CHAR_HEADING ca3_greaser3 267.9465
	  SET_CHAR_DECISION_MAKER ca3_greaser3 ca3_goondm

	  IF NOT IS_CHAR_DEAD ca3_greaser3
						
	  	CLEAR_CHAR_TASKS ca3_greaser3
		OPEN_SEQUENCE_TASK ca3_greaser3seq
				
		   //	TASK_GO_STRAIGHT_TO_COORD -1 1067.6794 2080.0520 9.8203 PEDMOVE_RUN -2
		    TASK_GO_STRAIGHT_TO_COORD -1 1068.7075 2085.2405 9.8203 PEDMOVE_RUN -2
			TASK_TOGGLE_DUCK -1	TRUE
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
		
		CLOSE_SEQUENCE_TASK ca3_greaser3seq
		PERFORM_SEQUENCE_TASK ca3_greaser3 ca3_greaser3seq
		CLEAR_SEQUENCE_TASK ca3_greaser3seq
	 ENDIF




	 	ca3_begingrease = 1
	 


	ENDIF
ENDIF


IF ca3_beginbackup = 0

	IF ca3_machinesdestroyed > 5
		CUSTOM_PLATE_FOR_NEXT_CAR ADMIRAL G1RUYHUN

		CREATE_CAR ADMIRAL 1029.8561 2142.8579 9.8130 ca3_backupdriveby
		SET_CAR_HEADING ca3_backupdriveby 205.1437 
		CHANGE_CAR_COLOUR ca3_backupdriveby 123 10
		SET_CAR_PROOFS ca3_backupdriveby TRUE TRUE TRUE TRUE TRUE
		LOCK_CAR_DOORS ca3_backupdriveby CARLOCK_LOCKOUT_PLAYER_ONLY	  // was mission2 pedtype
		FREEZE_CAR_POSITION ca3_backupdriveby TRUE
		SET_CAN_BURST_CAR_TYRES ca3_backupdriveby FALSE

		ca3_beginbackup = 1
	ENDIF
ENDIF



IF ca3_beginbackup = 1


	IF ca3_machinesdestroyed > 5
		IF NOT IS_CAR_DEAD ca3_backupdriveby
	
			 IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer ca3_backupdriveby 25.0 25.0 FALSE
				CREATE_CHAR_INSIDE_CAR ca3_backupdriveby PEDTYPE_MISSION1 VMAFF1 ca3_backupdriver
				//SET_CHAR_SUFFERS_CRITICAL_HITS sw7_escapechauffeur FALSE
				SET_CHAR_DECISION_MAKER ca3_backupdriver ca3_goondm
				GIVE_WEAPON_TO_CHAR ca3_backupdriver WEAPONTYPE_MICRO_UZI 2000

				CREATE_CHAR_AS_PASSENGER ca3_backupdriveby PEDTYPE_MISSION1 VMAFF2 1 ca3_backup1
				SET_CHAR_DECISION_MAKER ca3_backup1 ca3_goondm
				GIVE_WEAPON_TO_CHAR ca3_backup1 WEAPONTYPE_MICRO_UZI 2000
				TASK_DRIVE_BY ca3_backup1 scplayer -1 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 90

			 

				
				CREATE_CHAR_AS_PASSENGER ca3_backupdriveby PEDTYPE_MISSION1 VMAFF4 2 ca3_backup2
				SET_CHAR_DECISION_MAKER ca3_backup2 ca3_goondm
				GIVE_WEAPON_TO_CHAR ca3_backup2 WEAPONTYPE_MICRO_UZI 2000
				TASK_DRIVE_BY ca3_backup2 scplayer -1 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 90


				FREEZE_CAR_POSITION ca3_backupdriveby FALSE
	   
				SET_CAR_TEMP_ACTION ca3_backupdriveby TEMPACT_GOFORWARD 5500
				
				LOCK_CAR_DOORS ca3_backupdriveby CARLOCK_UNLOCKED

				ca3_beginbackup = 2
				timerb = 0
			 
			 ENDIF

		ENDIF

	ENDIF


ENDIF // ca3_beginbackup = 0 condition check





IF ca3_beginbackup = 2


	IF timerb > 6500


		IF NOT IS_CAR_DEAD ca3_backupdriveby
			IF NOT IS_CHAR_DEAD ca3_backupdriver
				IF IS_CAR_STOPPED ca3_backupdriveby

					SET_CAR_PROOFS ca3_backupdriveby FALSE FALSE FALSE FALSE FALSE

				   						
				  	CLEAR_CHAR_TASKS ca3_backupdriver
					OPEN_SEQUENCE_TASK ca3_backupdriverseq
							
					  	TASK_LEAVE_ANY_CAR -1
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					
					CLOSE_SEQUENCE_TASK ca3_backupdriverseq
					PERFORM_SEQUENCE_TASK ca3_backupdriver ca3_backupdriverseq
					CLEAR_SEQUENCE_TASK ca3_backupdriverseq



					ca3_beginbackup	= 3
					timerb = 0

				ENDIF
			ENDIF
		ENDIF


	ENDIF



   
ENDIF // ca3_beginbackup = 1 condition check




IF ca3_beginbackup = 3


	IF timerb > 15000


		IF NOT IS_CAR_DEAD ca3_backupdriveby
			IF NOT IS_CHAR_DEAD ca3_backup2
				IF IS_CAR_STOPPED ca3_backupdriveby

				   						
				  	CLEAR_CHAR_TASKS ca3_backup2
					OPEN_SEQUENCE_TASK ca3_backup2seq
							
					  	TASK_LEAVE_ANY_CAR -1
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					
					CLOSE_SEQUENCE_TASK ca3_backup2seq
					PERFORM_SEQUENCE_TASK ca3_backup2 ca3_backup2seq
					CLEAR_SEQUENCE_TASK ca3_backup2seq



					ca3_beginbackup	= 4

				ENDIF
			ENDIF
		ENDIF


	ENDIF



   
ENDIF // ca3_beginbackup = 2 condition check


IF ca3_beginbackup = 4


	IF timerb > 25000


		IF NOT IS_CAR_DEAD ca3_backupdriveby
			IF NOT IS_CHAR_DEAD ca3_backup1
				IF IS_CAR_STOPPED ca3_backupdriveby

				   						
				  	CLEAR_CHAR_TASKS ca3_backup1
					OPEN_SEQUENCE_TASK ca3_backup1seq
							
					  	TASK_LEAVE_ANY_CAR -1
						TASK_KILL_CHAR_ON_FOOT -1 scplayer
					
					CLOSE_SEQUENCE_TASK ca3_backup1seq
					PERFORM_SEQUENCE_TASK ca3_backup1 ca3_backup1seq
					CLEAR_SEQUENCE_TASK ca3_backup1seq



					ca3_beginbackup	= 5

				ENDIF
			ENDIF
		ENDIF


	ENDIF



   
ENDIF // ca3_beginbackup = 4 condition check
















GOTO casino3_main_mission_loop





// Procedures


ca3_Cover_Blown:

	IF NOT IS_CHAR_DEAD ca3_gate1P
	   TASK_KILL_CHAR_ON_FOOT ca3_gate1P scplayer
	ENDIF

  	IF NOT IS_CHAR_DEAD ca3_gate2P
	   TASK_KILL_CHAR_ON_FOOT ca3_gate2P scplayer
	ENDIF
  
	IF NOT IS_CHAR_DEAD ca3_forkie1P
		SET_CHAR_COORDINATES ca3_forkie1P 1084.1256 2107.6375 14.3504 
		SET_CHAR_HEADING ca3_forkie1P 111.4468 	
		GIVE_WEAPON_TO_CHAR ca3_forkie1P WEAPONTYPE_PISTOL 2000
	ENDIF

	/*
	IF NOT IS_CHAR_DEAD ca3_contextgoon
		SET_CHAR_COORDINATES ca3_contextgoon 1080.7861 2086.4829 9.8203 
		SET_CHAR_HEADING ca3_contextgoon 82.4468 	
	ENDIF */

	CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 1084.7100 2116.7356 14.3504 ca3_spottedgoon
	SET_CHAR_HEADING ca3_spottedgoon 94.4201 
	GIVE_WEAPON_TO_CHAR ca3_spottedgoon WEAPONTYPE_MICRO_UZI 2000
	SET_CHAR_DECISION_MAKER ca3_spottedgoon ca3_goondm

	
   	

   //	REMOVE_BLIP ca3_gate1B
   //	REMOVE_BLIP ca3_gate2B

	/*
	IF NOT IS_CHAR_DEAD ca3_forkie1P
	   TASK_KILL_CHAR_ON_FOOT ca3_forkie1P scplayer
	ENDIF
  
	IF NOT IS_CHAR_DEAD ca3_forkie2P
	   TASK_KILL_CHAR_ON_FOOT ca3_forkie2P scplayer
	ENDIF
	*/
  

RETURN






ca3_In_Factory_Was_Not_Spotted:

	IF ca3_setsequences = 0

		IF NOT IS_CHAR_DEAD ca3_contextgoon
			//GIVE_WEAPON_TO_CHAR ca3_forkie1P WEAPONTYPE_MICRO_UZI 2000

			CLEAR_CHAR_TASKS ca3_contextgoon

			OPEN_SEQUENCE_TASK ca3_cgseq

			TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
		   	//TASK_JUMP -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK ca3_cgseq
			PERFORM_SEQUENCE_TASK ca3_contextgoon ca3_cgseq
			CLEAR_SEQUENCE_TASK ca3_cgseq

		ENDIF


	
		IF NOT IS_CHAR_DEAD ca3_goon1P
		OPEN_SEQUENCE_TASK ca3_g1seq
	  	TASK_GO_STRAIGHT_TO_COORD -1 1067.5807 2122.2510 9.8203 PEDMOVE_RUN -2
    	TASK_STAY_IN_SAME_PLACE -1 TRUE
    	TASK_KILL_CHAR_ON_FOOT -1 scplayer
    	CLOSE_SEQUENCE_TASK ca3_g1seq

    	PERFORM_SEQUENCE_TASK ca3_goon1P ca3_g1seq
    	CLEAR_SEQUENCE_TASK ca3_g1seq	
		ENDIF
    

		IF NOT IS_CHAR_DEAD ca3_goon2P
    	OPEN_SEQUENCE_TASK ca3_g2seq
		TASK_GO_TO_COORD_WHILE_SHOOTING -1 1085.5961 2106.8298 9.8203 	PEDMOVE_RUN 100.0 2.0 scplayer
	   	//TASK_GO_TO_COORD_WHILE_SHOOTING -1 1079.8793 2078.3547 14.3516  PEDMOVE_RUN 100.0 2.0 scplayer
		//TASK_GO_TO_COORD_WHILE_SHOOTING -1 1079.9498 2077.4146 14.3516	PEDMOVE_JOG 100.0 2.0 scplayer
       	TASK_STAY_IN_SAME_PLACE -1 TRUE
    	TASK_KILL_CHAR_ON_FOOT -1 scplayer
    	CLOSE_SEQUENCE_TASK ca3_g2seq

    	PERFORM_SEQUENCE_TASK ca3_goon2P ca3_g2seq
    	CLEAR_SEQUENCE_TASK ca3_g2seq						 
		ENDIF
 
  
		IF NOT IS_CHAR_DEAD ca3_goon3P
    		OPEN_SEQUENCE_TASK ca3_g3seq
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 1082.3915 2091.1299 14.3504 PEDMOVE_RUN 100.0 1.0 scplayer
    		TASK_STAY_IN_SAME_PLACE -1 TRUE
    		TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
    		CLOSE_SEQUENCE_TASK ca3_g3seq

    		PERFORM_SEQUENCE_TASK ca3_goon3P ca3_g3seq
    		CLEAR_SEQUENCE_TASK ca3_g3seq						 
		ENDIF


		IF NOT IS_CHAR_DEAD ca3_foreman1
		   CLEAR_CHAR_TASKS ca3_foreman1
		  // SET_CHAR_COORDINATES ca3_foreman1 1086.0752 2139.8628 14.3504
		   TASK_KILL_CHAR_ON_FOOT ca3_foreman1 scplayer
		   TASK_STAY_IN_SAME_PLACE ca3_foreman1 TRUE

		ENDIF


	 
		IF NOT IS_CHAR_DEAD ca3_forkie1P
		   	CLEAR_CHAR_TASKS ca3_forkie1P
		   	GIVE_WEAPON_TO_CHAR ca3_forkie1P WEAPONTYPE_PISTOL 2000

		   	TASK_KILL_CHAR_ON_FOOT ca3_forkie1P scplayer
		ENDIF

		IF NOT IS_CHAR_DEAD ca3_forkie2P
			//move this guy
		   	CLEAR_CHAR_TASKS ca3_forkie2P
			GIVE_WEAPON_TO_CHAR ca3_forkie2P WEAPONTYPE_PISTOL 2000

		   	SET_CHAR_COORDINATES ca3_forkie2P 1057.0553 2122.3193 13.6896
			SET_CHAR_HEADING ca3_forkie2P 279.0
	  		OPEN_SEQUENCE_TASK ca3_forkie2seq
			   	TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK ca3_forkie2seq
			PERFORM_SEQUENCE_TASK ca3_forkie2P ca3_forkie2seq
			CLEAR_SEQUENCE_TASK ca3_forkie2seq
		ENDIF

   		IF NOT IS_CHAR_DEAD ca3_gate1P
	   		TASK_KILL_CHAR_ON_FOOT ca3_gate1P scplayer
		ENDIF

  		IF NOT IS_CHAR_DEAD ca3_gate2P
	   		TASK_KILL_CHAR_ON_FOOT ca3_gate2P scplayer
		ENDIF


		/*
		IF NOT IS_CHAR_DEAD ca3_bossP
    		OPEN_SEQUENCE_TASK ca3_bossseq
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 1071.2762 2145.0120 14.3516 PEDMOVE_RUN 100.0 5.0 scplayer
    		TASK_STAY_IN_SAME_PLACE -1 TRUE
     		TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
    		CLOSE_SEQUENCE_TASK ca3_bossseq

    		PERFORM_SEQUENCE_TASK ca3_bossP ca3_bossseq
    		CLEAR_SEQUENCE_TASK ca3_bossseq						 
		ENDIF
		*/

		ca3_setsequences = 1

		

	ENDIF 
RETURN



ca3_In_Factory_Was_Spotted:

  
	IF ca3_setsequences = 0

	IF NOT IS_CHAR_DEAD ca3_contextgoon
			//GIVE_WEAPON_TO_CHAR ca3_forkie1P WEAPONTYPE_MICRO_UZI 2000

			CLEAR_CHAR_TASKS ca3_contextgoon

			OPEN_SEQUENCE_TASK ca3_cgseq

			TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
		   	//TASK_JUMP -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK ca3_cgseq
			PERFORM_SEQUENCE_TASK ca3_contextgoon ca3_cgseq
			CLEAR_SEQUENCE_TASK ca3_cgseq

	ENDIF


	IF NOT IS_CHAR_DEAD ca3_spottedgoon
			//GIVE_WEAPON_TO_CHAR ca3_forkie1P WEAPONTYPE_MICRO_UZI 2000

			CLEAR_CHAR_TASKS ca3_spottedgoon

			OPEN_SEQUENCE_TASK ca3_sgseq

			//TASK_PLAY_ANIM -1 WeaponRollout PED 8.0 FALSE TRUE TRUE FALSE -1
		   	TASK_JUMP -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK ca3_sgseq
			PERFORM_SEQUENCE_TASK ca3_spottedgoon ca3_sgseq
			CLEAR_SEQUENCE_TASK ca3_sgseq

	ENDIF



	IF NOT IS_CHAR_DEAD ca3_forkie1P
			GIVE_WEAPON_TO_CHAR ca3_forkie1P WEAPONTYPE_PISTOL 2000

			CLEAR_CHAR_TASKS ca3_forkie1P

			OPEN_SEQUENCE_TASK ca3_forkie1seq


		   	TASK_JUMP -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK ca3_forkie1seq
			PERFORM_SEQUENCE_TASK ca3_forkie1P ca3_forkie1seq
			CLEAR_SEQUENCE_TASK ca3_forkie1seq

	ENDIF

	IF NOT IS_CHAR_DEAD ca3_forkie2P
			//Move this guy

			GIVE_WEAPON_TO_CHAR ca3_forkie2P WEAPONTYPE_MICRO_UZI 2000

			CLEAR_CHAR_TASKS ca3_forkie2P

			SET_CHAR_COORDINATES ca3_forkie2P 1057.0553 2122.3193 13.6896
			SET_CHAR_HEADING ca3_forkie2P 279.0
	  		OPEN_SEQUENCE_TASK ca3_forkie2seq
			   	TASK_TOGGLE_DUCK -1 TRUE
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK ca3_forkie2seq
			PERFORM_SEQUENCE_TASK ca3_forkie2P ca3_forkie2seq
			CLEAR_SEQUENCE_TASK ca3_forkie2seq

	ENDIF


	
	IF NOT IS_CHAR_DEAD ca3_goon1P
		OPEN_SEQUENCE_TASK ca3_g1seq
		TASK_GO_STRAIGHT_TO_COORD -1 1067.5807 2122.2510 9.8203 PEDMOVE_RUN -2
		
    	TASK_STAY_IN_SAME_PLACE -1 TRUE
    	TASK_KILL_CHAR_ON_FOOT -1 scplayer
    	CLOSE_SEQUENCE_TASK ca3_g1seq

    	PERFORM_SEQUENCE_TASK ca3_goon1P ca3_g1seq
    	CLEAR_SEQUENCE_TASK ca3_g1seq	
		ENDIF
    

		IF NOT IS_CHAR_DEAD ca3_goon2P
    	OPEN_SEQUENCE_TASK ca3_g2seq
		TASK_GO_TO_COORD_WHILE_SHOOTING -1 1085.5961 2106.8298 9.8203 PEDMOVE_RUN 100.0 2.0 scplayer
	   //	TASK_GO_TO_COORD_WHILE_SHOOTING -1 1079.8793 2078.3547 14.3516  PEDMOVE_RUN 100.0 2.0 scplayer
       	//TASK_STAY_IN_SAME_PLACE -1 TRUE
    	TASK_KILL_CHAR_ON_FOOT -1 scplayer
    	CLOSE_SEQUENCE_TASK ca3_g2seq

    	PERFORM_SEQUENCE_TASK ca3_goon2P ca3_g2seq
    	CLEAR_SEQUENCE_TASK ca3_g2seq						 
		ENDIF
 
  
		IF NOT IS_CHAR_DEAD ca3_goon3P
    		OPEN_SEQUENCE_TASK ca3_g3seq
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 1082.3915 2091.1299 14.3504 PEDMOVE_RUN 100.0 1.0 scplayer
    		TASK_STAY_IN_SAME_PLACE -1 TRUE
    		TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
    		CLOSE_SEQUENCE_TASK ca3_g3seq

    		PERFORM_SEQUENCE_TASK ca3_goon3P ca3_g3seq
    		CLEAR_SEQUENCE_TASK ca3_g3seq						 
		ENDIF


		IF NOT IS_CHAR_DEAD ca3_foreman1
		   CLEAR_CHAR_TASKS ca3_foreman1
		  // SET_CHAR_COORDINATES ca3_foreman1 1086.0752 2139.8628 14.3504
		   TASK_STAY_IN_SAME_PLACE ca3_foreman1 TRUE

		   TASK_KILL_CHAR_ON_FOOT ca3_foreman1 scplayer
		ENDIF


	 
	  

   		IF NOT IS_CHAR_DEAD ca3_gate1P
	   		TASK_KILL_CHAR_ON_FOOT ca3_gate1P scplayer
		ENDIF

  		IF NOT IS_CHAR_DEAD ca3_gate2P
	   		TASK_KILL_CHAR_ON_FOOT ca3_gate2P scplayer
		ENDIF


		/*
		IF NOT IS_CHAR_DEAD ca3_bossP
    		OPEN_SEQUENCE_TASK ca3_bossseq
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 1071.2762 2145.0120 14.3516 PEDMOVE_RUN 100.0 5.0 scplayer
    		TASK_STAY_IN_SAME_PLACE -1 TRUE
     		TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
    		CLOSE_SEQUENCE_TASK ca3_bossseq

    		PERFORM_SEQUENCE_TASK ca3_bossP ca3_bossseq
    		CLEAR_SEQUENCE_TASK ca3_bossseq						 
		ENDIF
		*/

		ca3_setsequences = 1

	ENDIF


RETURN



ca3_bossfight:


	IF NOT IS_CHAR_DEAD ca3_bossP
    		OPEN_SEQUENCE_TASK ca3_bossseq
		    //TASK_STAY_IN_SAME_PLACE -1 TRUE
     		TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
    		CLOSE_SEQUENCE_TASK ca3_bossseq

    		PERFORM_SEQUENCE_TASK ca3_bossP ca3_bossseq
    		CLEAR_SEQUENCE_TASK ca3_bossseq						 
	ENDIF
	

RETURN




 // **************************************** Mission casino3 failed ***********************

mission_casino3_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission casino3 passed ************************

mission_casino3_passed:

flag_casino_mission_counter ++ //Used to terminate this mission loop in the main script. These variables will be set up in the main.sc
REGISTER_MISSION_PASSED ( CASINO3 ) //Used in the stats 
PLAYER_MADE_PROGRESS 1

PRINT_WITH_NUMBER_BIG ( M_PASSS ) 10000 5000 1
AWARD_PLAYER_MISSION_RESPECT 20

ADD_SCORE player1 10000

//AWARD_PLAYER_MISSION_RESPECT 20

//SET_INT_STAT PASSED_CASINO3 1

PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1
REMOVE_BLIP casino_contact_blip

ADD_SPRITE_BLIP_FOR_CONTACT_POINT 2026.6028 1007.7353 9.8127 casino_blip_icon casino_contact_blip

RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_casino3:




IF NOT IS_CAR_DEAD ca3_mafia_car
	SET_CAR_PROOFS ca3_mafia_car FALSE FALSE FALSE FALSE FALSE
ENDIF

IF NOT IS_CAR_DEAD ca3_backupdriveby
	SET_CAR_PROOFS ca3_backupdriveby FALSE FALSE FALSE FALSE FALSE
ENDIF


IF DOES_OBJECT_EXIST fourdragons_door

SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.62 11.438
SET_OBJECT_HEADING fourdragons_door 0.0

ENDIF

SET_MAX_WANTED_LEVEL ca3_wantedlevel

GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission

CLEAR_WANTED_LEVEL player1

//UNLOAD_SPECIAL_CHARACTER 1

IF DOES_OBJECT_EXIST ca3_loadingbay
	SET_OBJECT_COORDINATES ca3_loadingbay 1055.629 2087.67 12.469
ENDIF


//REMOVE_BLIP ca3_plvanB
REMOVE_BLIP ca3_factoryB

//REMOVE_BLIP ca3_gate1B
//REMOVE_BLIP ca3_gate2B


REMOVE_BLIP ca3_exitB
//REMOVE_BLIP ca3_bossB


c = 0
WHILE c < 9
 	REMOVE_BLIP ca3_machineblip[c]
   //	KILL_FX_SYSTEM ca3_fx[c]
 	++ c
ENDWHILE

IF NOT temp_fx = 99
	KILL_FX_SYSTEM ca3_fx[temp_fx]
ENDIF




//REMOVE_BLIP ca3_foremanB
//REMOVE_BLIP ca3_keypadB


//MARK_ANIMATION_AS_NO_LONGER_NEEDED BOMBER

MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF4
MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB

MARK_MODEL_AS_NO_LONGER_NEEDED WASHING

MARK_MODEL_AS_NO_LONGER_NEEDED wmymech
//MARK_MODEL_AS_NO_LONGER_NEEDED wmymech

MARK_MODEL_AS_NO_LONGER_NEEDED shotgspa
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED BAT
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI

													
MARK_MODEL_AS_NO_LONGER_NEEDED ADMIRAL
MARK_MODEL_AS_NO_LONGER_NEEDED BRAVURA
MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT




REMOVE_ALL_SCRIPT_FIRES
/*
DELETE_OBJECT ca3_oilspill
DELETE_OBJECT ca3_oiltrail
DELETE_OBJECT ca3_oiltrailignite
*/

//REMOVE_PICKUP ca3_exting


flag_player_on_mission = 0

MISSION_HAS_FINISHED
//UNLOAD_SPECIAL_CHARACTER 1
RETURN

 
}


/*
[CA3_1:CASINO3]
~s~Head towards the ~y~factory unit~s~ on the outskirts of town. You're going to throw a major spanner in the works.

[CA3_2:CASINO3]
~s~Those mobsters have some ~r~guards~s~ watching the main entrance. Your job will be easier if you can get into the factory unnoticed.

[CA3_3:CASINO3]
~w~You'll wish you never brought your sorry ass in here. You're going to be crunched into one-dollar chips!

[CA3_4:CASINO3]
~w~Shit! I got my momma's birthday party tomorra and look at my shirt!

[CA3_5:CASINO3]
~w~We ain't expecting any delivery today? Might be trouble brewing, we're coming in for support!

[CA3_6:CASINO3]
~w~C'mon, I want a good spot in case any action starts! Park up gentle this time!

[CA3_7:CASINO3]
~s~Hit those ~g~machines~s~ with everything you've got! 

[CA3_8:CASINO3]
~s~They're expecting visitors now! Get into the ~y~factory~s~ and start acting blue-collar!

[CA3_9:CASINO3]
~w~Get the crew ready, this punk ain't here for an interview!

[CA3_10:CASINO3]
~w~What the fuck are you doing round here? This ain't a sideshow! 

[CA3_11:CASINO3]
~s~Don't let this guy get wasted! We won't be able to complete the job if he's toast! 

[CA3_12:CASINO3]
~s~Excellent! Those machines are outta commission! 

[CA3_17:CASINO3]
~w~Boss: Stay away! I'll make ya eat your fucking shoes! 

[CA3_20:CASINO3]
~r~What the fuck? I said no rough stuff! The mafia are gonna be on red alert now! Dumbass!

[CA3_22:CASINO3]
~s~Heads up! It's the minimum wage crew! Get safely away from the factory to complete the job!

[CA3_23:CASINO3]
~s~Great work, the job's done! Get back to ~y~Woozie's casino~s~ in one piece!
*/



