MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Heist 1 *******************************************			 
// ****************************** Photographing the Plans  *********************************			 
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME HEIST1



// Mission start stuff

GOSUB mission_start_heist1



IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_heist1_failed
ENDIF

GOSUB mission_cleanup_heist1

MISSION_END

{ 




// Variables for mission

LVAR_INT he1_visible

// Peds



LVAR_INT he1_tourist1 he1_tourist2 he1_tourist3	he1_tourist4 he1_tourist5

LVAR_INT he1_touristsnap  he1_doorsound

LVAR_FLOAT effectX effectY effectZ


LVAR_INT he1_recept1 he1_recept2
LVAR_INT he1_bystander1 he1_bystander2 he1_bystander3 he1_bystander4
LVAR_INT he1_cleaner
LVAR_INT he1_guard1 he1_guard2 he1_guard3 he1_guard4

LVAR_INT he1_swatbase1 he1_swatbase2 he1_swatbase3	he1_swatbase4 he1_swatbase5 he1_swatbase6


LVAR_INT he1_swatbase7 he1_swatbase8 he1_swatbase9


LVAR_INT he1_pop1 he1_pop2 he1_pop3 he1_pop4 he1_pop5 he1_pop6 he1_pop7 he1_pop8 he1_pop9


LVAR_INT he1_swatstair
LVAR_INT he1_swatstairPtsy


LVAR_INT he1_swatseq


LVAR_INT he1_suitgen

LVAR_INT he1_snitch
LVAR_INT he1_copconv
LVAR_INT he1_fireconv

LVAR_INT he1_dummy
LVAR_INT he1_camreserve[20]


//Decision makers
LVAR_INT he1_touristdm
LVAR_INT he1_emptydm
LVAR_INT he1_guarddm
LVAR_INT he1_civdm

// Vehicles

LVAR_INT he1_parkedescape




//Misc Objects

LVAR_INT he1_plans
LVAR_INT he1_doorR
LVAR_INT he1_doorL
LVAR_INT he1_doorpop1 he1_doorpop2
LVAR_INT he1_stairdoor


LVAR_INT he1_checkchat


//LVAR_INT he1_door // garage


LVAR_INT he1_vending1 he1_vending1health
LVAR_INT he1_vending2 he1_vending2health 
LVAR_FLOAT he1_vending2mass

LVAR_INT he1_depbox1 he1_depbox2 he1_depbox3 he1_depbox4 he1_depbox5
LVAR_INT he1_hintbox1 he1_hintbox2 he1_hintbox3

LVAR_INT he1_fire1 he1_fire2
LVAR_INT he1_smoke1 he1_smoke2 he1_smoke3 he1_smoke4 he1_smoke5	he1_smoke6 he1_smoke7 he1_smoke8 he1_smoke9

LVAR_INT he1_beginsmoke1 he1_beginsmoke2 he1_beginsmoke3  he1_vendingsmoke1 he1_vendingsmoke2 he1_vendingsmoke3
LVAR_INT he1_stopsmoke1 he1_stopsmoke2 he1_stopsmoke3

LVAR_INT he1_scriptfire1 he1_scriptfire2 he1_scriptfire3 he1_scriptfire4
LVAR_INT he1_Tempint

LVAR_INT he1_health1 he1_health2

LVAR_INT he1_cameraP



// Blips
LVAR_INT he1_cameraB
LVAR_INT he1_officesB
LVAR_INT he1_receptB
LVAR_INT he1_plansB
LVAR_INT he1_readingroomB
LVAR_INT he1_depositoryB
LVAR_INT he1_completeB
LVAR_INT he1_vending1B he1_vending2B
LVAR_INT he1_camreserveB
LVAR_INT he1_finalB
LVAR_INT he1_stairwellB
LVAR_INT he1_touristB[6]







																		  
// condition flags

LVAR_INT he1_stairprompt

LVAR_INT he1_cameraprompt
LVAR_INT he1_touristfrenzy
LVAR_INT he1_camtog

LVAR_INT he1_progress

LVAR_INT he1_drillstarted
LVAR_INT he1_inbuilding
LVAR_INT he1_failconditions

LVAR_INT he1_hideweaponcheck
LVAR_INT he1_playerwarned

LVAR_INT he1_attractattention

LVAR_INT he1_plansphotographed

LVAR_INT he1_ctskipfireneeded
LVAR_INT he1_ctskipsnitchneeded
LVAR_INT he1_ctskipcameraneeded

LVAR_INT he1_cameracheck

LVAR_INT he1_conversationOK

LVAR_INT he1_tempcount

LVAR_INT he1_vdam1
LVAR_INT he1_vdam2

LVAR_INT he1_scratch


LVAR_INT he1_byroute1 he1_byroute2 he1_byroute3


LVAR_INT he1_firescreated
LVAR_INT he1_smokebegun


LVAR_INT he1_cameracollected
LVAR_INT he1_filmremaining

LVAR_INT he1_camindex

LVAR_FLOAT he1_camX
LVAR_FLOAT he1_camY
LVAR_FLOAT he1_camZ


LVAR_INT he1_hint


LVAR_INT task_state


LVAR_INT he1_weaponaimed
LVAR_INT he1_g1aim 
LVAR_INT he1_g2aim
LVAR_INT he1_g3aim 

LVAR_INT he1_level3

//cutscene skippers

LVAR_INT he1_wuzi
LVAR_INT he1_wuzicar
LVAR_INT he1_wuzidriver
LVAR_INT he1_finalskip
LVAR_INT he1_finalsequence
LVAR_INT he1_playerseq


LVAR_INT he1_collisionfix
LVAR_INT he1_evacuation
LVAR_INT he1_locateprompt
LVAR_INT he1_airconfix

LVAR_INT he1_by1hit he1_by2hit he1_by3hit he1_by4hit

LVAR_INT he1_beenkickedout
LVAR_INT he1_reservefrenzy
LVAR_INT he1_convofix


//Dialogue and audio variables

LVAR_INT he1_dialogue he1_audio_char
LVAR_INT he1_text_timer_diff he1_text_timer_end he1_text_timer_start
LVAR_TEXT_LABEL he1_text[24]
LVAR_INT he1_audio[24] he1_audio_slot he1_alt_slot he1_counter he1_ahead_counter he1_audio_playing he1_audio_underway
LVAR_INT he1_convo_underway he1_convo_counter he1_random //he1_leftcar_counter he1_backcar_counter he1_ganghire_counter 
//LVAR_INT he1_driveby_counter he1_cut_counter


LVAR_INT sfx_aircon	sfx_bankfire

LVAR_INT light_object light_onflag
LVAR_FLOAT lightX lightY lightZ

LVAR_INT light_oldhours light_oldmins


LVAR_INT he1_wantedfix







 
// **************************************** Mission Start **********************************

mission_start_heist1:

REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT HEIST1

flag_player_on_mission = 1


disable_mod_garage = 1

//SET_CHAR_AMMO scplayer WEAPONTYPE_CAMERA 0

light_onflag = 0

WAIT 0

// ****************************************START OF CUTSCENE********************************


SET_AREA_VISIBLE 10

SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO


SET_CHAR_COORDINATES scplayer 2026.2247 1007.4234 9.8203
SET_CHAR_HEADING scplayer 307.0


//SET_CHAR_COORDINATES scplayer 2024.8704 1008.6201 9.8127
//SET_CHAR_HEADING scplayer 342.8127


GET_TIME_OF_DAY	light_oldhours light_oldmins
SET_TIME_OF_DAY 00 00 // Midnight
SET_DARKNESS_EFFECT TRUE -1
light_onflag = 0

LOAD_CUTSCENE HEIST1a
 
WHILE NOT HAS_CUTSCENE_LOADED
            WAIT 0
ENDWHILE

cs_time = 0
FORCE_WEATHER_NOW WEATHER_RAINY_COUNTRYSIDE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
            WAIT 0


GET_CUTSCENE_TIME cs_time

IF light_onflag = 0
	IF cs_time > 12666
	   light_onflag = 1

		FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_VEGAS
		SET_TIME_OF_DAY 12 00  // Midday
		SET_DARKNESS_EFFECT FALSE -1

	   //CREATE_OBJECT TriMainLite 1891.899 1018.516 34.685 light_object
	  // CREATE_OBJECT st_arch_plan 1891.899 1018.516 34.685 light_object

	  // WRITE_DEBUG switched_on

	   /*
	   IF DOES_OBJECT_EXIST trimainlite
			WRITE_DEBUG switched_on
	  		GET_OBJECT_COORDINATES trimainlite lightX lightY lightZ
	  	 	SET_OBJECT_COORDINATES trimainlite lightX lightY 1.98
	   ENDIF
	   */
	ENDIF
ENDIF

IF light_onflag = 1
	IF cs_time > 46000
	   light_onflag = 99

	   FORCE_WEATHER_NOW WEATHER_RAINY_COUNTRYSIDE

	   SET_TIME_OF_DAY 00 00
	   SET_DARKNESS_EFFECT TRUE -1


	  // IF DOES_OBJECT_EXIST light_object
	   
		//DELETE_OBJECT light_object
	   //	WRITE_DEBUG switched_off

	  // ENDIF

	   /*
	   IF DOES_OBJECT_EXIST trimainlite
			WRITE_DEBUG switched_off
	  		GET_OBJECT_COORDINATES trimainlite lightX lightY lightZ
	  	 	SET_OBJECT_COORDINATES trimainlite lightX lightY 30.98
	   ENDIF
	   */
	ENDIF
ENDIF







ENDWHILE
 
//CLEAR_CUTSCENE

SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
            WAIT 0
ENDWHILE

CLEAR_CUTSCENE

FORCE_WEATHER_NOW WEATHER_CLOUDY_VEGAS
RELEASE_WEATHER
SET_DARKNESS_EFFECT FALSE -1

 

SET_AREA_VISIBLE 0

LOAD_SCENE 2031.1681 1030.9177 9.8130
 

 
//SET_PLAYER_CONTROL player1 ON

 
 

// ****************************************END OF CUTSCENE**********************************

WAIT 0
	
//LOAD_MISSION_TEXT HEIST1

SET_FADING_COLOUR 0 0 0


SET_TIME_OF_DAY	light_oldhours light_oldmins
SET_DARKNESS_EFFECT FALSE -1


RELEASE_WEATHER



//WAIT 500


//DO_FADE 1000 FADE_IN

// request models



REQUEST_MODEL CAMERA
REQUEST_MODEL COLT45
REQUEST_MODEL CHROMEGUN


REQUEST_MODEL OMYRI
REQUEST_MODEL BFYRI
REQUEST_MODEL OFOST


//REQUEST_MODEL bravura
//REQUEST_MODEL JOURNEY
//REQUEST_MODEL FIRETRUK


REQUEST_MODEL wmosci
REQUEST_MODEL wmybu
REQUEST_MODEL bmybu
REQUEST_MODEL wmysgrd

REQUEST_MODEL OMORI
REQUEST_MODEL OMOST

//REQUEST_ANIMATION CAMCREW
REQUEST_ANIMATION CAMERA

LOAD_ALL_MODELS_NOW


WHILE NOT HAS_MODEL_LOADED OMYRI
	OR NOT HAS_MODEL_LOADED BFYRI
	OR NOT HAS_MODEL_LOADED OFOST
  	WAIT 0
ENDWHILE



WHILE NOT HAS_MODEL_LOADED COLT45
	OR NOT HAS_MODEL_LOADED CHROMEGUN
	OR NOT HAS_MODEL_LOADED CAMERA
	WAIT 0
ENDWHILE




WHILE NOT HAS_MODEL_LOADED wmosci
	OR NOT HAS_MODEL_LOADED wmybu
	OR NOT HAS_MODEL_LOADED bmybu
	OR NOT HAS_MODEL_LOADED wmysgrd
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED OMOST
	OR NOT HAS_MODEL_LOADED OMORI
	OR NOT HAS_ANIMATION_LOADED CAMERA
	WAIT 0
ENDWHILE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK he1_touristdm

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY he1_emptydm

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH he1_guarddm
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK he1_civdm

LOAD_SCENE 2031.1681 1030.9177 9.8130
REQUEST_COLLISION 2031.1681 1030.9177 //9.8130



									   

//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO
//SET_CHAR_COORDINATES scplayer 2024.8704 1008.6201 9.8127
//SET_CHAR_HEADING scplayer 342.8127
SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT

WAIT 2000


DO_FADE 1000 FADE_IN
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE

SET_CAMERA_BEHIND_PLAYER 
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON




// ************************************ Declare Variables *********************************

//flags

he1_doorsound = 0
he1_beenkickedout = 0
he1_reservefrenzy = 0 
he1_convofix = 0
he1_checkchat = 0
				
he1_progress = 0
he1_touristfrenzy = 0
he1_cameraprompt = 0
he1_failconditions = 0
he1_inbuilding = 0
he1_hideweaponcheck = 0
he1_playerwarned = 0
he1_attractattention = 0
he1_plansphotographed = 0
he1_ctskipfireneeded = 0
he1_ctskipsnitchneeded = 0
he1_cameracheck = 0
he1_conversationOK = 0
he1_tempcount = 0
he1_vdam1 = 0
he1_vdam2 = 0
he1_scratch = 0
he1_byroute1 = 0
he1_byroute2 = 0
he1_byroute3 = 0

he1_firescreated = 0
he1_smokebegun = 0
he1_camtog = 0
he1_stairprompt = 0

he1_cameracollected = 0
he1_level3 = 0
he1_finalsequence = 0
he1_finalskip = 0

he1_collisionfix = 0
he1_evacuation = 0
he1_tempint = 0
he1_locateprompt = 0
he1_by1hit = 0
he1_by2hit = 0
he1_by3hit = 0
he1_by4hit = 0

he1_airconfix = 0

he1_wantedfix = 0



// ---- Dialogue and Audio Flags
	he1_audio_slot = 1
	he1_alt_slot = 2
	he1_counter = 0
	he1_ahead_counter = 1
	he1_audio_playing = 0
	he1_audio_underway = 0
	sfx_aircon = 0
	sfx_bankfire = 0



//Dialogue text


// tourist banter
$he1_text[1] = HE1_AG//What's your beef, man?
$he1_text[2] = HE1_AH//Why are you persecuting us?
$he1_text[3] = HE1_AI//You some crazy idiot or something?
	
$he1_text[4] = HE1_AJ//We'll see what the cops have to say!
$he1_text[5] = HE1_AK//I'm going straight to the cops!
$he1_text[6] = HE1_AL//The cops won't let you get away with this!


// guard shit.
$he1_text[7] = HE1_ZA//Put that weapon away sir! This is a public building!
$he1_text[8] = HE1_ZB//Thank you for your cooperation, sir.
$he1_text[9] = HE1_ZC//This is your final warning, sir, put the weapon away!
$he1_text[10] = HE1_ZD//I'm sorry, sir, cameras are prohibited in this area.
$he1_text[11] = HE1_ZE//You are not allowed to make any copies of plans, sir!
$he1_text[12] = HEX2b//Does sir understand?
$he1_text[13] = HE1_ZG//Everybody out, this is not a drill!
$he1_text[14] = HE1_ZH//That includes you, sir!
$he1_text[15] = HE1_ZJ//Where are those damn firemen?
$he1_text[16] = HE1_ZK//Hey you, stop  right there!
$he1_text[17] = HE1_ZL//This is security, don't make me shoot!
$he1_text[18] = HE1_ZM//I have a visual on the suspect, get to my position!	
$he1_text[19] = HE1_XA//Get this film developed for Woozie.
$he1_text[20] = HE1_XB//Will do, CJ! Good work!

$he1_text[21] = HEX3//Seeing as 
						  

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//conversation shit with receptionist needs to go in here to!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



//Dialogue audio


// tourist banter
$he1_audio[1] = SOUND_HE1_AG//What's your beef, man?
$he1_audio[2] = SOUND_HE1_AH//Why are you persecuting us?
$he1_audio[3] = SOUND_HE1_AI//You some crazy idiot or something?
	
$he1_audio[4] = SOUND_HE1_AJ//We'll see what the cops have to say!
$he1_audio[5] = SOUND_HE1_AK//I'm going straight to the cops!
$he1_audio[6] = SOUND_HE1_AL//The cops won't let you get away with this!


// guard shit.
$he1_audio[7] = SOUND_HE1_ZA//Put that weapon away sir! This is a public building!
$he1_audio[8] = SOUND_HE1_ZB//Thank you for your cooperation, sir.
$he1_audio[9] = SOUND_HE1_ZC//This is your final warning, sir, put the weapon away!
$he1_audio[10] = SOUND_HE1_ZD//I'm sorry, sir, cameras are prohibited in this area.
$he1_audio[11] = SOUND_HE1_ZE//You are not allowed to make any copies of plans, sir!

$he1_audio[12] = SOUND_HEIX2b//Does sir understand?
$he1_audio[13] = SOUND_HE1_ZG//Everybody out, this is not a drill!
$he1_audio[14] = SOUND_HE1_ZH//That includes you, sir!
$he1_audio[15] = SOUND_HE1_ZJ//Where are those damn firemen?
$he1_audio[16] = SOUND_HE1_ZK//Hey you, stop  right there!
$he1_audio[17] = SOUND_HE1_ZL//This is security, don't make me shoot!
$he1_audio[18] = SOUND_HE1_ZM//I have a visual on the suspect, get to my position!

$he1_audio[19] = SOUND_HE1_XA//Get this film developed for Woozie.
$he1_audio[20] = SOUND_HE1_XB//Will do, CJ! Good work!

$he1_audio[21] = SOUND_HEIX3//Seeing as you're walking...






// ****************************************************************************************

//debug coords which start player in the vegas suburbs
//SET_CHAR_COORDINATES scplayer 2020.4745 1503.8214 9.8203
//GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 2000

//debug
/*
CREATE_CHAR PEDTYPE_MISSION1 wmybu 376.9717 192.0647 1013.1797 he1_snitch
SET_CHAR_HEADING he1_snitch 266.7879
  
CREATE_CHAR PEDTYPE_MISSION1 wmybu 377.2169 190.7318 1013.1797 he1_copconv
SET_CHAR_HEADING he1_copconv 19.7879

CREATE_CHAR PEDTYPE_MISSION1 wmybu 377.9352 192.4556 1013.1797 he1_fireconv
SET_CHAR_HEADING he1_fireconv 124.31
*/

CREATE_OBJECT st_arch_plan 346.7 164.98 1026.80 he1_plans
SET_OBJECT_ROTATION he1_plans 90.0 0.0 0.0


//CREATE_OBJECT_NO_OFFSET trdcsgrgdoor_lvs 1903.383 967.82 11.438 fourdragons_door // Safe room door
SET_OBJECT_COLLISION fourdragons_door TRUE
//SET_OBJECT_HEADING fourdragons_door 0.0




 //START_SCRIPT_FIRE 2418.7366 1109.1472 9.8203 1 1 he1_scriptfire3


/*

CREATE_CHAR PEDTYPE_CIVMALE wmosci 2376.9155 1112.3081 9.8130 he1_recept2
SET_CHAR_HEADING he1_recept2 259.367


  

CREATE_CHAR PEDTYPE_CIVMALE	OMYRI 2380.7634 1111.9441 9.8130 he1_bystander1
SET_CHAR_HEADING he1_bystander1 104.1132


CREATE_CHAR PEDTYPE_CIVMALE	wmybu 2381.1633 1135.5160 9.8130 he1_bystander2
SET_CHAR_HEADING he1_bystander2 266.3662 

CREATE_CHAR PEDTYPE_CIVMALE	bmybu 2382.5266 1136.5194 9.8130 he1_bystander3
SET_CHAR_HEADING he1_bystander3 111.3662 


  */





//SWITCH_PED_ROADS_ON 1996.4890 1519.1196 10.0625 2003.8010 1565.6416 17.3672


//SWITCH_PED_ROADS_ON	2001.2888 1532.2842 8.0 2027.0776 1553.2673 13.0
    





CREATE_CHAR PEDTYPE_MISSION1 OMOST 2024.2596 1525.7867 9.8203 he1_tourist1
SET_CHAR_DECISION_MAKER he1_tourist1 he1_touristdm
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE he1_touristdm EVENT_DAMAGE

SET_CHAR_HEADING he1_tourist1 287.789
IF NOT IS_CHAR_DEAD he1_tourist1
	//GIVE_WEAPON_TO_CHAR he1_tourist1 WEAPONTYPE_CAMERA 30000
	//SWITCH_WIDESCREEN ON
ENDIF
				
				
CREATE_CHAR PEDTYPE_MISSION1 OMORI 2022.3398 1529.5588 9.8196 he1_tourist2
SET_CHAR_DECISION_MAKER he1_tourist2 he1_touristdm
//CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE he1_touristdm EVENT_DAMAGE

SET_CHAR_HEADING he1_tourist2 223.9212

CREATE_CHAR PEDTYPE_MISSION1 OMYRI 2026.0924 1530.4283 9.8203 he1_tourist3
SET_CHAR_DECISION_MAKER he1_tourist3 he1_touristdm
SET_CHAR_ALLOWED_TO_DUCK he1_tourist3 FALSE

SET_CHAR_HEADING he1_tourist3 129.789
//TASK_STAY_IN_SAME_PLACE	he1_tourist3 TRUE

IF NOT IS_CHAR_DEAD he1_tourist3
	GIVE_WEAPON_TO_CHAR he1_tourist3 WEAPONTYPE_CAMERA 30000
	TASK_KILL_CHAR_ON_FOOT he1_tourist3 he1_tourist1
	SET_CHAR_DROPS_WEAPONS_WHEN_DEAD he1_tourist3 FALSE
	SET_CHAR_DROWNS_IN_WATER he1_tourist3 FALSE
ENDIF

CREATE_CHAR PEDTYPE_MISSION1 OMORI 2017.9242 1536.1079 9.8228 he1_tourist4
SET_CHAR_DECISION_MAKER he1_tourist4 he1_touristdm
SET_CHAR_ALLOWED_TO_DUCK he1_tourist3 FALSE
SET_CHAR_HEADING he1_tourist4 170.9212



CREATE_CHAR PEDTYPE_MISSION1 OMYRI 2017.7075 1535.1272 9.8250 he1_tourist5
SET_CHAR_DECISION_MAKER he1_tourist5 he1_touristdm
SET_CHAR_HEADING he1_tourist5 11.9212




IF NOT IS_CHAR_DEAD he1_tourist4
	IF NOT IS_CHAR_DEAD he1_tourist5
		DISABLE_CHAR_SPEECH	he1_tourist4 FALSE
		DISABLE_CHAR_SPEECH	he1_tourist5 FALSE

		TASK_CHAT_WITH_CHAR he1_tourist4 he1_tourist5  true true //ped0 will lead the chatting
		TASK_CHAT_WITH_CHAR he1_tourist5 he1_tourist4 false true //ped1 will follow ped0 at chatting
	ENDIF
ENDIF






MARK_MODEL_AS_NO_LONGER_NEEDED OMOST
MARK_MODEL_AS_NO_LONGER_NEEDED OMYRI
MARK_MODEL_AS_NO_LONGER_NEEDED OMORI
				
				
				
// Should put tourists in array, hacked in for quickness. 

IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
								   
ADD_BLIP_FOR_CHAR he1_tourist1 he1_touristB[1]
CHANGE_BLIP_SCALE he1_touristB[1] 1
ADD_BLIP_FOR_CHAR he1_tourist2 he1_touristB[2]
CHANGE_BLIP_SCALE he1_touristB[2] 1
ADD_BLIP_FOR_CHAR he1_tourist3 he1_touristB[3]
ADD_BLIP_FOR_CHAR he1_tourist4 he1_touristB[4]
CHANGE_BLIP_SCALE he1_touristB[4] 1
ADD_BLIP_FOR_CHAR he1_tourist5 he1_touristB[5]
CHANGE_BLIP_SCALE he1_touristB[5] 1

ENDIF

he1_tempint = 1
//WHILE he1_tempint < 6
  //	CHANGE_BLIP_SCALE he1_touristB[he1_tempint] 2
  //	he1_tempint ++
//ENDWHILE



//CHANGE_BLIP_COLOUR he1_cameraB GREEN


//PRINT_NOW HEI1_33 6000 1

//WAIT 6000

//CLEAR_PRINTS
//WAIT 1000 

IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA


	PRINT_NOW HEI1_41 10000 1


ENDIF



//CREATE_OBJECT_NO_OFFSET kmb_lockeddoor 350.4434 160.3806 1024.7812 he1_doorL
//SET_OBJECT_ROTATION he1_doorL 0.0 0.0 180.0
//FREEZE_OBJECT_POSITION he1_doorL TRUE

CREATE_OBJECT_NO_OFFSET Gen_doorINT01 371.2516 166.6324 1007.3595 he1_doorL
SET_OBJECT_ROTATION he1_doorL 0.0 0.0 0.0
FREEZE_OBJECT_POSITION he1_doorL TRUE		// This one is the locked one.
																								    
/*
CREATE_OBJECT_NO_OFFSET Gen_doorINT01 350.5016 163.4424 1024.7695 he1_doorR
SET_OBJECT_ROTATION he1_doorR 0.0 0.0 270.0
FREEZE_OBJECT_POSITION he1_doorR TRUE
*/

//CREATE_OBJECT_NO_OFFSET kmb_lockeddoor 350.4434 163.3706 1024.7812 he1_doorR
//SET_OBJECT_ROTATION he1_doorR 0.0 0.0 180.0
//FREEZE_OBJECT_POSITION he1_doorR TRUE

CREATE_OBJECT kmb_lockeddoor 370.8048 179.0889 1018.9576 he1_doorpop1
SET_OBJECT_HEADING he1_doorpop1 88.5679 


CREATE_OBJECT Gen_doorINT01 368.8954 161.5299 1013.2742 he1_doorpop2
SET_OBJECT_ROTATION he1_doorpop2 0.0 0.0 90.0
LOCK_DOOR he1_doorpop2 TRUE


//CREATE_OBJECT Gen_doorINT01 372.3979 166.6150 1007.3906 he1_stairdoor
//SET_OBJECT_HEADING he1_stairdoor 45.0543 
//FREEZE_OBJECT_POSITION he1_stairdoor TRUE
														  
/*
	
CREATE_CHAR PEDTYPE_CIVMALE wmybu 359.5052 160.2184 1007.3906 he1_pop1
FLUSH_ROUTE
EXTEND_ROUTE 367.3111 168.4707 1007.3906 
EXTEND_ROUTE 366.9487 215.3946 1007.3906  
EXTEND_ROUTE 366.4571 196.9371 1007.3906  
EXTEND_ROUTE 366.3924 168.5011 1007.3906  
EXTEND_ROUTE 358.3032 159.0456 1007.3906  
  			
TASK_FOLLOW_POINT_ROUTE he1_pop1 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_pop1 150.0
*/

SET_MAX_FIRE_GENERATIONS 1


	
he1_progress = 1
//reinstate for proper game flow



//debug game flow which cuts out photographer bollocks
/*
he1_progress = 5
REMOVE_BLIP	he1_cameraB
ADD_BLIP_FOR_COORD 2414.8115 1124.4351 9.8130  he1_officesB
SET_CHAR_COORDINATES scplayer 2419.7588 1125.2191 9.8047

CREATE_CHAR PEDTYPE_CIVFEMALE BFYRI 359.5017 174.3209 1007.3906 he1_recept1
SET_CHAR_HEADING he1_recept1 278.367


CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 365.3421 188.8672 1007.3906 he1_guard1
SET_CHAR_HEADING he1_guard1 172.9746
SET_CHAR_DECISION_MAKER he1_guard1 he1_emptydm
GIVE_WEAPON_TO_CHAR he1_guard1 WEAPONTYPE_PISTOL 30000


CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 376.3221 170.1705 1007.3978 he1_guard2
SET_CHAR_HEADING he1_guard2 314.7879
SET_CHAR_DECISION_MAKER he1_guard2 he1_emptydm
GIVE_WEAPON_TO_CHAR he1_guard2 WEAPONTYPE_PISTOL 30000


CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 351.5390 160.6777 1024.7812 he1_guard3
SET_CHAR_HEADING he1_guard3 0.7879
SET_CHAR_DECISION_MAKER he1_guard3 he1_emptydm
GIVE_WEAPON_TO_CHAR he1_guard3 WEAPONTYPE_PISTOL 30000


GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA 20000
*/
//debug game flow which cuts out photographer bollocks	






	

																   

//he1_counter = 12
	 
heist1_main_mission_loop:
WAIT 0



//---- load and play audio bank.


IF sfx_bankfire = 1
	
	//WRITE_DEBUG fire
   	CLEAR_MISSION_AUDIO 3
	

 	LOAD_MISSION_AUDIO 3 SOUND_OFFICE_FIRE_ALARM

   /*	WHILE NOT HAS_MISSION_AUDIO_LOADED 3
		WAIT 0
	ENDWHILE
	*/
	sfx_bankfire = 2

ENDIF




IF sfx_bankfire = 2

	IF HAS_MISSION_AUDIO_LOADED 3
		//WRITE_DEBUG fire2
		PLAY_MISSION_AUDIO 3
	  	sfx_bankfire = 3
	ENDIF

ENDIF









// ---- Load & Play Dialogue...
	IF NOT he1_counter = 0
		IF he1_audio_playing = 0
			IF HAS_MISSION_AUDIO_LOADED he1_alt_slot
				CLEAR_MISSION_AUDIO he1_alt_slot
			ENDIF
			he1_audio_playing = 1
			he1_audio_underway = 1
		ENDIF

		IF he1_audio_playing = 1
			LOAD_MISSION_AUDIO he1_audio_slot he1_audio[he1_counter]
			//GOSUB he1_dialogue_pos
			//ATTACH_MISSION_AUDIO_TO_PED he1_audio_slot he1_audio_char
			he1_audio_playing = 2
		ENDIF

		IF he1_audio_playing = 2
		 	IF HAS_MISSION_AUDIO_LOADED he1_audio_slot
				PLAY_MISSION_AUDIO he1_audio_slot
				PRINT_NOW $he1_text[he1_counter] 10000 1
				he1_audio_playing = 3
			ENDIF
		ENDIF

		IF he1_audio_playing = 3
			IF HAS_MISSION_AUDIO_FINISHED he1_audio_slot
				CLEAR_THIS_PRINT $he1_text[he1_counter]
				IF he1_audio_slot = 1
					he1_audio_slot = 2
					he1_alt_slot = 1
				ELSE
					he1_audio_slot = 1
					he1_alt_slot = 2
				ENDIF
				he1_counter = 0
				he1_audio_playing = 0
			
				he1_audio_underway = 0 // okay to cue up next piece of convo text

			ELSE
				IF NOT HAS_MISSION_AUDIO_LOADED he1_alt_slot
					IF he1_counter < 18
						he1_ahead_counter = he1_counter + 1
						LOAD_MISSION_AUDIO he1_alt_slot he1_audio[he1_ahead_counter]
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
// End of dialogue loader / player
















IF he1_progress < 35
  	CLEAR_AREA 362.2167 174.5354 1007.3984 1000.0 FALSE
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
   GOTO mission_heist1_passed  
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_C
   he1_finalsequence = 9
   he1_plansphotographed = 11 
   he1_progress = -13
   he1_failconditions = -13
   he1_inbuilding = -13 
ENDIF



																							    
IF IS_PS2_KEYBOARD_KEY_PRESSED ps2_key_O
	CLEAR_AREA 2419.8269 1120.7850 9.8203  0.5 0
	LOAD_SCENE 2419.8269 1120.7850 9.8203 
	SET_CHAR_COORDINATES scplayer 2419.8269 1120.7850 9.8203  
	SET_CHAR_HEADING scplayer 0.0
	SET_CAMERA_BEHIND_PLAYER
ENDIF


IF he1_ctskipfireneeded = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	   he1_progress = 55
	ENDIF
ENDIF


IF he1_ctskipsnitchneeded = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	   he1_plansphotographed = 5
	ENDIF
ENDIF

IF he1_ctskipcameraneeded = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
	   he1_cameracheck = 4
	ENDIF
ENDIF

IF he1_finalskip = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
		WHILE IS_BUTTON_PRESSED PAD1 CROSS
			WAIT 0
		ENDWHILE

			he1_finalsequence = 40
			he1_finalskip = 0

	ENDIF
ENDIF



 /*
IF he1_firescreated = 0
  	
	CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 0.0 2.0 TRUE he1_smoke1
	CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 2.0 1.8 TRUE he1_smoke2
	CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 -2.0 2.0 TRUE he1_smoke3
	CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 5.0 2.0 TRUE he1_smoke4
	CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 8.0 1.8 TRUE he1_smoke5
	CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 2.0 0.0 1.5 TRUE he1_smoke6
	CREATE_FX_SYSTEM_ON_CHAR teargas scplayer -2.0 0.0 1.5 TRUE he1_smoke7


	PLAY_FX_SYSTEM he1_smoke1
	PLAY_FX_SYSTEM he1_smoke2
	PLAY_FX_SYSTEM he1_smoke3
	PLAY_FX_SYSTEM he1_smoke4
	PLAY_FX_SYSTEM he1_smoke5
	PLAY_FX_SYSTEM he1_smoke6
	PLAY_FX_SYSTEM he1_smoke7

	he1_firescreated = 1
ENDIF
*/

//CLEAR_PRINTS

//PRINT_NOW HEIC1 10000 1



IF he1_progress = 1
	//PLAY_FX_SYSTEM he1_smoke1
	/*
	
	IF he1_camtog = 0
	IF NOT IS_CHAR_DEAD he1_tourist1
		IF NOT IS_CHAR_DEAD he1_tourist2
			TASK_GO_STRAIGHT_TO_COORD he1_tourist1 2014.5480 1533.3390 9.8203 PEDMOVE_WALK -2 
			TASK_GO_STRAIGHT_TO_COORD he1_tourist2 2014.5480 1533.3390 9.8203 PEDMOVE_WALK -2

			he1_camtog = 1
		   
		ENDIF
	ENDIF
	ENDIF

	IF he1_camtog = 1
	IF NOT IS_CHAR_DEAD he1_tourist1
		IF NOT IS_CHAR_DEAD he1_tourist2
			IF LOCATE_CHAR_ANY_MEANS_3D he1_tourist1 2014.5480 1533.3390 9.8203 1.5 1.5 3.0 FALSE

			TASK_GO_STRAIGHT_TO_COORD he1_tourist1 2015.6520 1535.1721 9.8203 PEDMOVE_WALK -2 
			TASK_GO_STRAIGHT_TO_COORD he1_tourist2 2023.6520 1549.1721 9.8203 PEDMOVE_WALK -2

			he1_camtog = 2
		   	ENDIF
		ENDIF
	ENDIF
	ENDIF


	IF he1_camtog = 2
	IF NOT IS_CHAR_DEAD he1_tourist1
		IF NOT IS_CHAR_DEAD he1_tourist2
			IF LOCATE_CHAR_ANY_MEANS_3D he1_tourist1 2023.6520 1549.1721 9.8203 1.5 1.5 3.0 FALSE

			TASK_GO_STRAIGHT_TO_COORD he1_tourist1 2027.4883 1531.5402 9.8203 PEDMOVE_WALK -2 
			TASK_GO_STRAIGHT_TO_COORD he1_tourist2 2027.4883 1531.5402 9.8203 PEDMOVE_WALK -2

			he1_camtog = 3
			ENDIF
		   
		ENDIF
	ENDIF
	ENDIF


	IF he1_camtog = 3
	IF NOT IS_CHAR_DEAD he1_tourist1
		IF NOT IS_CHAR_DEAD he1_tourist2
			IF LOCATE_CHAR_ANY_MEANS_3D he1_tourist1 2027.4883 1531.5402 9.8203 1.5 1.5 3.0 FALSE

			TASK_GO_STRAIGHT_TO_COORD he1_tourist1 2032.3790 1505.9111 9.8129 PEDMOVE_WALK -2 
			TASK_GO_STRAIGHT_TO_COORD he1_tourist2 2032.3790 1505.9111 9.8129 PEDMOVE_WALK -2

			he1_camtog = 0
			ENDIF
		   
		ENDIF
	ENDIF
	ENDIF

	 */


	IF he1_touristfrenzy = 0

		IF IS_CHAR_DEAD he1_tourist1
			REMOVE_BLIP he1_touristB[1]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist2
			REMOVE_BLIP he1_touristB[5]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist5
			REMOVE_BLIP he1_touristB[5]
		ENDIF


		IF he1_touristsnap = 0
		IF TIMERA > 2000
		 	 IF NOT IS_CHAR_DEAD he1_tourist1
				IF NOT IS_CHAR_DEAD he1_tourist3
				IF NOT IS_CHAR_IN_WATER he1_tourist3
					CLEAR_CHAR_TASKS he1_tourist3
					CLEAR_CHAR_TASKS he1_tourist1
		    		TASK_TURN_CHAR_TO_FACE_CHAR he1_tourist3 he1_tourist1
					TASK_TURN_CHAR_TO_FACE_CHAR he1_tourist1 he1_tourist3


				   //	CLEAR_CHAR_TASKS he1_tourist3
					//anim goes here
					// TASK_PLAY_ANIM he1_tourist3 picstnd_take CAMERA 8.0 FALSE FALSE FALSE TRUE -1

					//GET_CHAR_COORDINATES he1_tourist3 effectX effectY effectZ
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION effectX effectY effectZ SOUND_CAMERA_SHOT


					TIMERA = 0
					he1_touristsnap = 1
				ENDIF
				ENDIF
			ENDIF
		ENDIF
		ENDIF


		IF he1_touristsnap = 1
			IF TIMERA > 2000
			IF NOT IS_CHAR_DEAD he1_tourist3
				   IF NOT IS_CHAR_IN_WATER he1_tourist3
					CLEAR_CHAR_TASKS he1_tourist3
					//anim goes here
					 TASK_PLAY_ANIM he1_tourist3 picstnd_take CAMERA 8.0 FALSE FALSE FALSE TRUE -1

					GET_CHAR_COORDINATES he1_tourist3 effectX effectY effectZ
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION effectX effectY effectZ SOUND_CAMERA_SHOT


					TIMERA = 0
					he1_touristsnap = 2	  
					ENDIF
			ENDIF
			ENDIF
		ENDIF

		IF he1_touristsnap = 2	
		IF TIMERA > 1000
		 	 IF NOT IS_CHAR_DEAD he1_tourist2
				IF NOT IS_CHAR_DEAD he1_tourist3
				IF NOT IS_CHAR_IN_WATER he1_tourist3
					CLEAR_CHAR_TASKS he1_tourist3
					CLEAR_CHAR_TASKS he1_tourist2
					TASK_TURN_CHAR_TO_FACE_CHAR he1_tourist2 he1_tourist3

		    		TASK_TURN_CHAR_TO_FACE_CHAR he1_tourist3 he1_tourist2


					//CLEAR_CHAR_TASKS he1_tourist3
					//anim goes here
					// TASK_PLAY_ANIM he1_tourist3 picstnd_take CAMERA 8.0 FALSE FALSE FALSE TRUE -1

					//GET_CHAR_COORDINATES he1_tourist3 effectX effectY effectZ
				   //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION effectX effectY effectZ SOUND_CAMERA_SHOT


					TIMERA = 0
					he1_touristsnap = 3
				ENDIF
				ENDIF
			ENDIF
		ENDIF
		ENDIF


		IF he1_touristsnap = 3
			IF TIMERA > 1000
			IF NOT IS_CHAR_DEAD he1_tourist3
			IF NOT IS_CHAR_IN_WATER he1_tourist3
					CLEAR_CHAR_TASKS he1_tourist3
					//anim goes here
					 TASK_PLAY_ANIM he1_tourist3 piccrch_take CAMERA 8.0 FALSE FALSE FALSE TRUE -1

					GET_CHAR_COORDINATES he1_tourist3 effectX effectY effectZ
					REPORT_MISSION_AUDIO_EVENT_AT_POSITION effectX effectY effectZ SOUND_CAMERA_SHOT


					TIMERA = 0
					he1_touristsnap = 0
			ENDIF
			ENDIF
			ENDIF
		ENDIF


		IF NOT IS_CHAR_DEAD he1_tourist4
			IF NOT IS_CHAR_DEAD he1_tourist5
				GET_SCRIPT_TASK_STATUS he1_tourist4 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK
				GET_SCRIPT_TASK_STATUS he1_tourist5 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK

					CLEAR_CHAR_TASKS he1_tourist4
					CLEAR_CHAR_TASKS he1_tourist5
						
				   //	TASK_LOOK_ABOUT he1_pop1 -1
				   //	TASK_LOOK_ABOUT he1_pop2 -1

				   	TASK_CHAT_WITH_CHAR he1_tourist4 he1_tourist5  true true //ped0 will lead the chatting
				   	TASK_CHAT_WITH_CHAR he1_tourist5 he1_tourist4 false true //ped1 will follow ped0 at chatting
				ENDIF
				ENDIF
			ENDIF
		ENDIF



		IF TIMERB > 4000
		 	 IF NOT IS_CHAR_DEAD he1_tourist2
				IF NOT IS_CHAR_DEAD he1_tourist3
				  IF NOT IS_CHAR_DEAD he1_tourist4
					CLEAR_CHAR_TASKS he1_tourist2
		    		TASK_TURN_CHAR_TO_FACE_CHAR he1_tourist2 he1_tourist3



					//CLEAR_CHAR_TASKS he1_tourist4
					// anim goes here				
					//GET_CHAR_COORDINATES he1_tourist4 effectX effectY effectZ
					//REPORT_MISSION_AUDIO_EVENT_AT_POSITION effectX effectY effectZ SOUND_CAMERA_SHOT
	
					
					TIMERB = 0
				  ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	

	IF he1_cameraprompt = 0
		IF NOT IS_CHAR_DEAD he1_tourist3
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer he1_tourist3 5.0 5.0 FALSE
				IF NOT IS_CHAR_DEAD he1_tourist2
					CLEAR_CHAR_TASKS he1_tourist2
					TASK_LOOK_AT_CHAR he1_tourist2 scplayer 5
					//PRINT HEI1_26 4000 1// You take a picture!
					he1_cameraprompt = 99
				ENDIF
			ENDIF
		ENDIF						
	ENDIF
			  
			  
	IF he1_locateprompt = 0
		IF NOT IS_CHAR_DEAD he1_tourist3
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer he1_tourist3 35.0 35.0 FALSE
				   CLEAR_PRINTS
				   
				 // 	he1_counter = 1
					//PRINT_NOW HE1_AG 6000 1
				   //PRINT_NOW HEI1_35 6000 1// take a camera by force
				   he1_locateprompt = 99
			ENDIF
		ENDIF
	ENDIF	
		
	
	
			  
	/*
	IF he1_touristfrenzy = 0
		IF IS_CHAR_DEAD he1_tourist3
			he1_touristfrenzy = 1
		ENDIF
	ENDIF
	*/

	IF he1_touristfrenzy = 0
		IF DOES_CHAR_EXIST he1_tourist3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_tourist3 scplayer
	 			//TASK_STAY_IN_SAME_PLACE	he1_tourist3 FALSE

				CLEAR_PRINTS
				//WAIT 500
				 he1_counter = 1
	 		   //	PRINT_NOW HEI1_32 4000 1
				he1_touristfrenzy = 1
	 		ENDIF
		ENDIF
	ENDIF

	IF he1_touristfrenzy = 0
		IF DOES_CHAR_EXIST he1_tourist2
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_tourist2 scplayer
	 			//WAIT 500
				 he1_counter = 2
				//CLEAR_PRINTS
	 			//PRINT_NOW HEI1_32 4000 1
				he1_touristfrenzy = 1
	 		ENDIF
		ENDIF
	ENDIF

	IF he1_touristfrenzy = 0
		IF DOES_CHAR_EXIST he1_tourist1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_tourist1 scplayer
	 			//WAIT 500
				CLEAR_PRINTS
				 he1_counter = 3
	 			//PRINT_NOW HEI1_32 4000 1
				he1_touristfrenzy = 1
	 		ENDIF
		ENDIF
	ENDIF
	
	IF he1_touristfrenzy = 0
		IF DOES_CHAR_EXIST he1_tourist4
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_tourist4 scplayer
	 			//WAIT 500
				CLEAR_PRINTS
				 he1_counter = 4
	 		   //	PRINT_NOW HEI1_32 4000 1
				he1_touristfrenzy = 1
	 		ENDIF
		ENDIF
	ENDIF

	IF he1_touristfrenzy = 0
		IF DOES_CHAR_EXIST he1_tourist5
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_tourist5 scplayer
	 			//WAIT 500
				CLEAR_PRINTS
	 			 he1_counter = 6
	 			//PRINT_NOW HEI1_32 4000 1
				he1_touristfrenzy = 1
	 		ENDIF																 
		ENDIF
	ENDIF


	IF he1_touristfrenzy = 1
		he1_cameraprompt = 99


		IF IS_CHAR_DEAD he1_tourist1
			REMOVE_BLIP he1_touristB[1]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist2
			REMOVE_BLIP he1_touristB[2]
		ENDIF


		IF IS_CHAR_DEAD he1_tourist4
			REMOVE_BLIP he1_touristB[4]
		ENDIF


		IF IS_CHAR_DEAD he1_tourist5
			REMOVE_BLIP he1_touristB[5]
		ENDIF



		
		IF NOT IS_CHAR_DEAD he1_tourist3
			CLEAR_CHAR_TASKS he1_tourist3
			TASK_STAY_IN_SAME_PLACE	he1_tourist3 FALSE

			//CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE he1_touristdm EVENT_DAMAGE
		  	TASK_SMART_FLEE_CHAR he1_tourist3 scplayer 100.0 -1	
			//TASK_SMART_FLEE_POINT he1_tourist3 2026.2958 1542.2117 9.8203 100.0 -1
		ENDIF

		IF NOT IS_CHAR_DEAD he1_tourist2
			CLEAR_CHAR_TASKS he1_tourist2
			TASK_SMART_FLEE_CHAR he1_tourist2 scplayer 100.0 -1		
		ENDIF
		
		IF NOT IS_CHAR_DEAD he1_tourist1
			CLEAR_CHAR_TASKS he1_tourist1
		 	TASK_SMART_FLEE_CHAR he1_tourist1 scplayer 100.0 -1	
		ENDIF
		
		IF NOT IS_CHAR_DEAD he1_tourist4
			CLEAR_CHAR_TASKS he1_tourist4
			DISABLE_CHAR_SPEECH	he1_tourist4 TRUE
	
		  	TASK_SMART_FLEE_CHAR he1_tourist4 scplayer 100.0 -1	
			//TASK_SMART_FLEE_POINT he1_tourist4 2028.1533 1549.3993 9.8283 100.0 -1

		ENDIF
		
		IF NOT IS_CHAR_DEAD he1_tourist5
			CLEAR_CHAR_TASKS he1_tourist5
			DISABLE_CHAR_SPEECH	he1_tourist5 TRUE

		 	TASK_SMART_FLEE_CHAR he1_tourist5 scplayer 100.0 -1	
		ENDIF

		REMOVE_ANIMATION CAMERA


		he1_touristfrenzy = 2
	ENDIF


	IF he1_touristfrenzy = 2

		IF IS_CHAR_DEAD he1_tourist1
			REMOVE_BLIP he1_touristB[1]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist2
			REMOVE_BLIP he1_touristB[2]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist5
			REMOVE_BLIP he1_touristB[5]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist4
			REMOVE_BLIP he1_touristB[4]
		ENDIF


		IF IS_CHAR_DEAD he1_tourist3
			IF he1_audio_underway = 0
			//REMOVE_BLIP he1_cameraB
			he1_tempint = 1
			WHILE he1_tempint < 6
				REMOVE_BLIP he1_touristB[he1_tempint]
			   	he1_tempint ++
			ENDWHILE

			CLEAR_PRINTS

			//SET_CHAR_AMMO he1_tourist3 WEAPONTYPE_CAMERA 0 
			GET_DEAD_CHAR_PICKUP_COORDS he1_tourist3 he1_camX he1_camY he1_camZ
			CREATE_PICKUP_WITH_AMMO CAMERA PICKUP_ONCE 20 he1_camX he1_camY he1_camZ he1_cameraP
			ADD_BLIP_FOR_PICKUP he1_cameraP he1_cameraB
		   	CHANGE_BLIP_COLOUR he1_cameraB GREEN 
			he1_counter = 0
			CLEAR_PRINTS

			CLEAR_MISSION_AUDIO he1_alt_slot
			CLEAR_MISSION_AUDIO he1_audio_slot	 
			he1_audio_underway = 0
			he1_audio_playing = 0

		   	PRINT_NOW HEI1_39 6000 1

		   	he1_touristfrenzy = 3
			ENDIF
		ENDIF
	ENDIF

	/*
	IF he1_touristfrenzy = 2

		IF IS_CHAR_DEAD he1_tourist1
			REMOVE_BLIP he1_touristB[1]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist2
			REMOVE_BLIP he1_touristB[2]
		ENDIF

		IF IS_CHAR_DEAD he1_tourist5
			REMOVE_BLIP he1_touristB[5]
		ENDIF



		IF IS_CHAR_DEAD he1_tourist4
			//REMOVE_BLIP he1_cameraB
			he1_tempint = 1
			WHILE he1_tempint < 6
				REMOVE_BLIP he1_touristB[he1_tempint]
			   	he1_tempint ++
			ENDWHILE
			
			CLEAR_PRINTS

			//SET_CHAR_AMMO he1_tourist3 WEAPONTYPE_CAMERA 0 
			GET_DEAD_CHAR_PICKUP_COORDS he1_tourist4 he1_camX he1_camY he1_camZ
			CREATE_PICKUP_WITH_AMMO CAMERA PICKUP_ONCE 20 he1_camX he1_camY he1_camZ he1_cameraP
			ADD_BLIP_FOR_PICKUP he1_cameraP he1_cameraB
		   	CHANGE_BLIP_COLOUR he1_cameraB GREEN

			he1_counter = 0
			CLEAR_PRINTS

			CLEAR_MISSION_AUDIO he1_alt_slot
			CLEAR_MISSION_AUDIO he1_audio_slot	 
			he1_audio_underway = 0
			he1_audio_playing = 0



			PRINT_NOW HEI1_39 6000 1
			he1_touristfrenzy = 3
		ENDIF


	ENDIF
	*/

	IF HAS_PICKUP_BEEN_COLLECTED he1_cameraP
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA

		//PRINT_NOW HEI1_40 4000 1
			REMOVE_BLIP he1_cameraB
			
			he1_cameracollected = 1

			CLEAR_PRINTS
			PRINT_NOW HEI1_40 6000 1



			//he1_progress = 5
			//Reinstate to facilitate proper gameflow


			//test conversation stuff
			he1_progress = 5


			he1_hint = 1
			TIMERB = 0
			//CLEAR_PRINTS
			//PRINT HEI1_1 6000 1
			ADD_BLIP_FOR_COORD 2414.8115 1124.4351 9.8130  he1_officesB // blip for the planning department
			CHANGE_BLIP_COLOUR he1_officesB YELLOW
			SWITCH_ENTRY_EXIT paper TRUE

			//CREATE_PED_GENERATOR 366.4588 173.4304 1007.3978 0.0 wmybu TRUE he1_suitgen
			//SWITCH_PED_GENERATOR he1_suitgen 110

			/*
			CREATE_CHAR PEDTYPE_CIVFEMALE BFYRI 359.5017 174.3209 1007.3906 he1_recept1
			SET_CHAR_HEADING he1_recept1 278.367
			SET_CHAR_DECISION_MAKER he1_recept1 he1_emptydm


		  	CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 365.3421 188.8672 1007.3906 he1_guard1
			SET_CHAR_HEADING he1_guard1 172.9746
			SET_CHAR_DECISION_MAKER he1_guard1 he1_emptydm
			GIVE_WEAPON_TO_CHAR he1_guard1 WEAPONTYPE_PISTOL 30000


			CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 376.3221 170.1705 1007.3978 he1_guard2
			SET_CHAR_HEADING he1_guard2 314.7879
			SET_CHAR_DECISION_MAKER he1_guard2 he1_emptydm
			GIVE_WEAPON_TO_CHAR he1_guard2 WEAPONTYPE_PISTOL 30000

			
												 //351.5772 161.7314 1024.7812
			CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 351.6945 160.0155 1024.7888 he1_guard3
			SET_CHAR_HEADING he1_guard3 293.7879
			SET_CHAR_DECISION_MAKER he1_guard3 he1_emptydm
			GIVE_WEAPON_TO_CHAR he1_guard3 WEAPONTYPE_PISTOL 30000

			CREATE_CHAR PEDTYPE_MISSION1 wmybu 355.1732 172.5407 1024.7812 he1_bystander1
			SET_CHAR_HEADING he1_bystander1 259.56
			SET_CHAR_DECISION_MAKER he1_bystander1 he1_touristdm

			CREATE_CHAR PEDTYPE_MISSION1 bmybu 355.8736 172.4779 1024.7812 he1_bystander2
			SET_CHAR_HEADING he1_bystander2 80.67
			SET_CHAR_DECISION_MAKER he1_bystander2 he1_touristdm				

			CREATE_CHAR PEDTYPE_MISSION1 wmybu 362.3390 155.2859 1024.7812 he1_bystander3
			SET_CHAR_HEADING he1_bystander3 178.989
			SET_CHAR_DECISION_MAKER he1_bystander3 he1_touristdm
			TASK_SCRATCH_HEAD he1_bystander3


			IF NOT IS_CHAR_DEAD he1_bystander1
			IF NOT IS_CHAR_DEAD he1_bystander2
				TASK_CHAT_WITH_CHAR he1_bystander1 he1_bystander2  true true //ped0 will lead the chatting
				TASK_CHAT_WITH_CHAR he1_bystander2 he1_bystander1 false true //ped1 will follow ped0 at chatting
			ENDIF
			ENDIF

			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu

			*/



	ENDIF
	ENDIF





ENDIF //he1_progress = 1 condition check

IF he1_hint = 1
	IF TIMERB > 12000
		CLEAR_PRINTS
		PRINT HEI1_1 6000 1
		he1_hint = 2
	ENDIF
ENDIF



IF he1_cameracollected = 1
	IF he1_plansphotographed = 0
	IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
		PRINT_NOW HEI1_11 6000 1
		CREATE_CHAR PEDTYPE_CIVMALE OMYRI 2030.7025 1489.3881 9.8203 he1_camreserve[he1_camindex]
		TASK_WANDER_STANDARD he1_camreserve[he1_camindex]
		//GET_RANDOM_CHAR_IN_SPHERE 2034.6597 1563.2098 9.7628 100.0 TRUE FALSE FALSE he1_camreserve[he1_camindex]
		//IF DOES_CHAR_EXIST he1_camreserve[he1_camindex]
		ADD_BLIP_FOR_CHAR he1_camreserve[he1_camindex] he1_camreserveB
		CHANGE_BLIP_COLOUR he1_camreserveB RED
		GIVE_WEAPON_TO_CHAR he1_camreserve[he1_camindex] WEAPONTYPE_CAMERA 30000
		SET_CHAR_DROPS_WEAPONS_WHEN_DEAD he1_camreserve[he1_camindex] FALSE

	   	SET_CHAR_DECISION_MAKER he1_camreserve[he1_camindex] he1_touristdm
		he1_cameracollected = 2
	//ENDIF
	ENDIF
	ENDIF


ENDIF


IF he1_cameracollected = 2
	IF he1_plansphotographed = 0
		IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA

		IF IS_CHAR_DEAD he1_camreserve[he1_camindex]
			REMOVE_BLIP he1_camreserveB
			//SET_CHAR_AMMO he1_camreserve[he1_camindex] WEAPONTYPE_CAMERA 0 


			GET_DEAD_CHAR_PICKUP_COORDS he1_camreserve[he1_camindex] he1_camX he1_camY he1_camZ
			CREATE_PICKUP_WITH_AMMO CAMERA PICKUP_ONCE 60 he1_camX he1_camY he1_camZ he1_cameraP
			ADD_BLIP_FOR_PICKUP he1_cameraP he1_camreserveB
		   	CHANGE_BLIP_COLOUR he1_camreserveB GREEN


			MARK_CHAR_AS_NO_LONGER_NEEDED he1_camreserve[he1_camindex]
			
			IF he1_camindex < 19
			he1_camindex ++
			he1_reservefrenzy = 0
			ENDIF

		
			he1_cameracollected = 3
		ENDIF
		ENDIF
	

		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
			IF DOES_CHAR_EXIST he1_camreserve[he1_camindex]
			MARK_CHAR_AS_NO_LONGER_NEEDED he1_camreserve[he1_camindex]
			
				IF he1_camindex < 19
					he1_camindex ++
					he1_reservefrenzy = 0
				ENDIF
				
			ENDIF
			REMOVE_BLIP he1_camreserveB
			he1_cameracollected = 1
		ENDIF


	ENDIF
	
ENDIF






IF he1_cameracollected = 3
   IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
	    IF HAS_PICKUP_BEEN_COLLECTED he1_cameraP
			REMOVE_BLIP he1_camreserveB
			he1_cameracollected = 1
		ENDIF
	ENDIF
ENDIF



IF he1_reservefrenzy = 0
		IF DOES_CHAR_EXIST he1_camreserve[he1_camindex]
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_camreserve[he1_camindex] scplayer
	 			CLEAR_CHAR_TASKS he1_camreserve[he1_camindex]
	 			TASK_SMART_FLEE_CHAR he1_camreserve[he1_camindex] scplayer 100.0 -1	
	
	 			he1_reservefrenzy = 1
	 		ENDIF
		ENDIF
ENDIF


	

//check if player has used all his camera film
IF he1_cameracollected > 0	  
	IF he1_plansphotographed = 0 // player hasn't photographed plans

	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_CAMERA
		GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA he1_filmremaining
		 IF he1_filmremaining = 0
			REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_CAMERA 
			he1_cameracollected = 1
		 ENDIF
	ENDIF
	ENDIF
ENDIF




//change to array of chars and blips




IF he1_progress = 5

	// put guys in here

	IF he1_collisionfix = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2414.8115 1124.4351 9.8130 50.0 50.0 20.0 FALSE
			CREATE_CHAR PEDTYPE_CIVMALE wmybu 366.2195 178.2328 1007.3984 he1_pop1
			DISABLE_CHAR_SPEECH	he1_pop1 FALSE
			SET_CHAR_DECISION_MAKER he1_pop1 he1_touristdm

			SET_CHAR_HEADING he1_pop1 256.0
//			FLUSH_ROUTE
//			EXTEND_ROUTE 367.3111 168.4707 1007.3906 
//			EXTEND_ROUTE 366.9487 215.3946 1007.3906  
//			EXTEND_ROUTE 366.4571 196.9371 1007.3906  
//			EXTEND_ROUTE 366.3924 168.5011 1007.3906  
//			EXTEND_ROUTE 358.3032 159.0456 1007.3906  
//			  			
//			TASK_FOLLOW_POINT_ROUTE he1_pop1 PEDMOVE_WALK FOLLOW_ROUTE_LOOP
//			SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_pop1 150.0
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop1 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_CIVMALE bmybu 367.4051 178.2571 1007.3971 he1_pop2
			SET_CHAR_DECISION_MAKER he1_pop2 he1_touristdm
			DISABLE_CHAR_SPEECH	he1_pop2 FALSE

			SET_CHAR_HEADING he1_pop2 75.0
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop2 2415.3479 1123.9423 10.0


			CREATE_CHAR PEDTYPE_CIVMALE bmybu 336.2050 173.5649 1018.9844 he1_pop3
			SET_CHAR_DECISION_MAKER he1_pop3 he1_touristdm
				DISABLE_CHAR_SPEECH	he1_pop3 FALSE

			SET_CHAR_HEADING he1_pop3 21.0
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop3 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_CIVMALE wmybu 335.1483 175.8309 1018.9844 he1_pop4
			SET_CHAR_DECISION_MAKER he1_pop4 he1_touristdm
					DISABLE_CHAR_SPEECH	he1_pop4 FALSE

			SET_CHAR_HEADING he1_pop4 200.0
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop4 2415.3479 1123.9423 10.0


			CREATE_CHAR PEDTYPE_CIVMALE bmybu 347.6199 171.2935 1018.9912 he1_pop5
			SET_CHAR_DECISION_MAKER he1_pop5 he1_touristdm
					DISABLE_CHAR_SPEECH	he1_pop5 FALSE

			SET_CHAR_HEADING he1_pop5 89.0
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop5 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_CIVMALE wmybu 347.7367 172.3383 1018.9912 he1_pop6
			SET_CHAR_DECISION_MAKER he1_pop6 he1_touristdm
					DISABLE_CHAR_SPEECH	he1_pop6 FALSE

			SET_CHAR_HEADING he1_pop6 270.0
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop6 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_CIVMALE wmybu 360.7583 175.1333 1018.9844 he1_pop7
			SET_CHAR_DECISION_MAKER he1_pop7 he1_touristdm
					DISABLE_CHAR_SPEECH	he1_pop7 FALSE

			SET_CHAR_HEADING he1_pop7 162.0
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop7 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_CIVFEMALE BFYRI 361.4531 176.2417 1018.9844 he1_pop8
			SET_CHAR_DECISION_MAKER he1_pop8 he1_touristdm
			SET_CHAR_HEADING he1_pop8 0.0
					DISABLE_CHAR_SPEECH	he1_pop8 FALSE

			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop8 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_CIVMALE bmybu 359.5063 177.6107 1018.9844 he1_pop9
			SET_CHAR_DECISION_MAKER he1_pop9 he1_touristdm
			SET_CHAR_HEADING he1_pop9 184.0
					DISABLE_CHAR_SPEECH	he1_pop9 FALSE

			SET_CHAR_HAS_USED_ENTRY_EXIT he1_pop9 2415.3479 1123.9423 10.0
			TASK_SIT_DOWN he1_pop9 500000


			


			//CREATE_CHAR PEDTYPE_CIVFEMALE BFYRI 359.5056 172.7443 1007.3893  he1_recept1
			CREATE_CHAR PEDTYPE_CIVFEMALE BFYRI 359.7724 173.5793 1007.3893  he1_recept1
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_recept1 2415.3479 1123.9423 10.0

		   //	SET_CHAR_HEADING he1_recept1 278.367
			
			
		   //	SET_CHAR_HEADING he1_recept1 320.0
			SET_CHAR_HEADING he1_recept1 261.0
			SET_CHAR_DECISION_MAKER he1_recept1 he1_emptydm
			SET_CHAR_NEVER_TARGETTED he1_recept1 TRUE


		  	CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 365.3421 188.8672 1007.3906 he1_guard1
			SET_CHAR_HEADING he1_guard1 172.9746
			SET_CHAR_DECISION_MAKER he1_guard1 he1_emptydm
			GIVE_WEAPON_TO_CHAR he1_guard1 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_guard1 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 376.3221 170.1705 1007.3978 he1_guard2
			SET_CHAR_HEADING he1_guard2 314.7879
			SET_CHAR_DECISION_MAKER he1_guard2 he1_emptydm
			GIVE_WEAPON_TO_CHAR he1_guard2 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_guard2 2415.3479 1123.9423 10.0

			
												 //351.5772 161.7314 1024.7812
			CREATE_CHAR PEDTYPE_MISSION1 wmysgrd 351.8366 163.8015 1024.7863 he1_guard3//351.6945 160.0155 1024.7888 he1_guard3
			SET_CHAR_HEADING he1_guard3 239.6//293.7879
			SET_CHAR_DECISION_MAKER he1_guard3 he1_emptydm
			GIVE_WEAPON_TO_CHAR he1_guard3 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_guard3 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_MISSION1 wmybu 355.1732 172.5407 1024.7812 he1_bystander1
			SET_CHAR_HEADING he1_bystander1 259.56
			SET_CHAR_DECISION_MAKER he1_bystander1 he1_touristdm
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_bystander1 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_MISSION1 bmybu 355.8736 172.4779 1024.7812 he1_bystander2
			SET_CHAR_HEADING he1_bystander2 80.67
			SET_CHAR_DECISION_MAKER he1_bystander2 he1_touristdm
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_bystander2 2415.3479 1123.9423 10.0				

			CREATE_CHAR PEDTYPE_MISSION1 wmybu 362.3390 155.2859 1024.7812 he1_bystander3
			SET_CHAR_HEADING he1_bystander3 178.989
			SET_CHAR_DECISION_MAKER he1_bystander3 he1_touristdm
			TASK_SCRATCH_HEAD he1_bystander3
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_bystander3 2415.3479 1123.9423 10.0

			CREATE_CHAR PEDTYPE_MISSION1 bmybu 362.7949 156.2016 1024.7812 he1_bystander4
			SET_CHAR_HEADING he1_bystander4 20.989
			SET_CHAR_DECISION_MAKER he1_bystander4 he1_touristdm
			TASK_SCRATCH_HEAD he1_bystander4
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_bystander4 2415.3479 1123.9423 10.0

			IF NOT IS_CHAR_DEAD he1_pop1
			IF NOT IS_CHAR_DEAD he1_pop2
				CLEAR_CHAR_TASKS he1_pop1
				CLEAR_CHAR_TASKS he1_pop2
			   //	TASK_LOOK_ABOUT he1_pop1 -1
			   //	TASK_LOOK_ABOUT he1_pop2 -1

				TASK_CHAT_WITH_CHAR he1_pop1 he1_pop2  true true //ped0 will lead the chatting
				TASK_CHAT_WITH_CHAR he1_pop2 he1_pop1 false true //ped1 will follow ped0 at chatting
			ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop3
			IF NOT IS_CHAR_DEAD he1_pop4
				//TASK_WANDER_COP he1_pop3
				//TASK_WANDER_COP he1_pop4
				CLEAR_CHAR_TASKS he1_pop3
				CLEAR_CHAR_TASKS he1_pop4

				//TASK_LOOK_ABOUT he1_pop3 -1
				//TASK_LOOK_ABOUT he1_pop4 -1

			  	TASK_CHAT_WITH_CHAR he1_pop3 he1_pop4  true true //ped0 will lead the chatting
			   	TASK_CHAT_WITH_CHAR he1_pop4 he1_pop3 false true //ped1 will follow ped0 at chatting
			ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop5
			IF NOT IS_CHAR_DEAD he1_pop6
				//TASK_WANDER_CRIMINAL he1_pop5
				//TASK_WANDER_CRIMINAL he1_pop6
				CLEAR_CHAR_TASKS he1_pop5
				CLEAR_CHAR_TASKS he1_pop6

			   //	TASK_LOOK_ABOUT he1_pop5 -1
			   //	TASK_LOOK_ABOUT he1_pop6 -1

			  		TASK_CHAT_WITH_CHAR he1_pop5 he1_pop6  true true //ped0 will lead the chatting
			   		TASK_CHAT_WITH_CHAR he1_pop6 he1_pop5 false true //ped1 will follow ped0 at chatting
			ENDIF
			ENDIF

			
			IF NOT IS_CHAR_DEAD he1_bystander1
			IF NOT IS_CHAR_DEAD he1_bystander2
				CLEAR_CHAR_TASKS he1_bystander1
				CLEAR_CHAR_TASKS he1_bystander2

				//TASK_LOOK_ABOUT he1_bystander1 -1
				//TASK_LOOK_ABOUT he1_bystander2 -1



				TASK_CHAT_WITH_CHAR he1_bystander1 he1_bystander2  true true //ped0 will lead the chatting
				TASK_CHAT_WITH_CHAR he1_bystander2 he1_bystander1 false true //ped1 will follow ped0 at chatting
			ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD he1_bystander3
			IF NOT IS_CHAR_DEAD he1_bystander4
				CLEAR_CHAR_TASKS he1_bystander3
				CLEAR_CHAR_TASKS he1_bystander4

				//TASK_LOOK_ABOUT he1_bystander3 -1
				//TASK_LOOK_ABOUT he1_bystander4 -1

				
			 	TASK_CHAT_WITH_CHAR he1_bystander3 he1_bystander4  true true //ped0 will lead the chatting
			 	TASK_CHAT_WITH_CHAR he1_bystander4 he1_bystander3 false true //ped1 will follow ped0 at chatting
			ENDIF
			ENDIF


			MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
			MARK_MODEL_AS_NO_LONGER_NEEDED bmybu

			he1_collisionfix = 1

			//	CREATE_OBJECT cardboardbox 350.1686 191.5514 1018.9844 he1_depbox1
//
//	CREATE_OBJECT cardboardbox2 348.9597 192.6585 1018.9844 he1_depbox2
//
//	CREATE_OBJECT cardboardbox 349.0061 191.2924 1018.9844 he1_depbox3
//
//	CREATE_OBJECT cardboardbox2 379.9210 164.7143 1018.9844 he1_hintbox1
//	SET_OBJECT_ROTATION he1_hintbox1 0.0 0.0 268.7768
//
//	CREATE_OBJECT cardboardbox2 379.0733 164.2609 1018.9844 he1_hintbox2
//	SET_OBJECT_ROTATION he1_hintbox2 0.0 0.0 229.0
//
//	CREATE_OBJECT cardboardbox  379.4728 164.5040 1019.6091 he1_hintbox3
//	SET_OBJECT_ROTATION he1_hintbox3 0.0 0.0 123.7622







		ENDIF
	ENDIF





	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 387.2780 173.5760 1007.3906 5.0 5.0 2.0 FALSE	
		he1_progress = 10
	   //	CREATE_PED_GENERATOR 366.4588 173.4304 1007.3978 0.0 wmybu TRUE he1_suitgen
		//SWITCH_PED_GENERATOR he1_suitgen 100

		TIMERA = 0
		TIMERB = 0
		
		PRINT_NOW HEI1_9 4000 1 // Remember, this is a civic building
		
		//WAIT 4000
		he1_failconditions = 1
		he1_inbuilding = 1
		SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2415.3479 1123.9423 10.0    
	   	SET_RADAR_ZOOM 95



		IF NOT IS_CHAR_DEAD he1_recept1 
			
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_recept1 2415.3479 1123.9423 10.0 

			TASK_SIT_DOWN he1_recept1 500000
			REMOVE_BLIP he1_officesB
			ADD_BLIP_FOR_CHAR he1_recept1 he1_receptB
			CHANGE_BLIP_COLOUR he1_receptB BLUE
		ENDIF




	ENDIF



ENDIF //he1_progress = 5 condition check


//Blip fixing
IF he1_progress > 5
	//Need to check whether or not the player is in the planning department as we will have to fail the mission if he opens
	//fire before he has started the firedrill. Check whether fire is started and inbuilding condition flags.
	
	
	
	IF he1_inbuilding = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 387.2780 173.5760 1007.3906 5.0 5.0 2.0 FALSE
			 CLEAR_AREA 362.2167 174.5354 1007.3984 1000.0 FALSE
	
	  		 he1_inbuilding = 1 // player in building
		   //	 HIDE_ALL_FRONTEND_BLIPS TRUE
			 SET_RADAR_ZOOM 95
			 IF sfx_bankfire > 1

				sfx_bankfire = 1

			 //  REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_OFFICE_FIRE_ALARM_START
			 //  REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_OFFICE_FIRE_COUGHING_START

	

			 ENDIF


			 
			 IF he1_progress < 25
			 	REMOVE_BLIP he1_officesB
			 	IF NOT IS_CHAR_DEAD he1_recept1
					SET_CHAR_HAS_USED_ENTRY_EXIT he1_recept1 2415.3479 1123.9423 10.0 
			 		ADD_BLIP_FOR_CHAR he1_recept1 he1_receptB
			 		CHANGE_BLIP_COLOUR he1_receptB BLUE
				ENDIF
			 ENDIF
		ENDIF
	ENDIF
	

	IF he1_inbuilding = 1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2415.5999 1123.2400 9.8130 5.0 5.0 2.0 FALSE
			he1_hideweaponcheck = 0
			CLEAR_PRINTS	
   			
   			he1_inbuilding = 0 // player outside building
			//HIDE_ALL_FRONTEND_BLIPS FALSE
			SET_RADAR_ZOOM 0
			
			IF he1_progress < 25
				IF NOT IS_CHAR_DEAD he1_recept1
					REMOVE_BLIP he1_receptB
					ADD_BLIP_FOR_COORD  2414.8115 1124.4351 9.8130 he1_officesB
				ENDIF
			ENDIF

			/*
			IF he1_progress < 25
				IF NOT IS_CHAR_DEAD he1_recept1
					REMOVE_BLIP he1_receptB
					ADD_BLIP_FOR_COORD  2414.8115 1124.4351 9.8130 he1_officesB
				ENDIF
			ENDIF
			 */

			IF sfx_bankfire > 1


			  CLEAR_MISSION_AUDIO 3
			  //REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_OFFICE_FIRE_ALARM_STOP
			  // REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_OFFICE_FIRE_COUGHING_STOP

	

			ENDIF


		ENDIF
	ENDIF
	





	//CREATE_PED_GENERATOR 366.4588 173.4304 1007.3978 0.0 wmybu TRUE he1_suitgen
	//SWITCH_PED_GENERATOR he1_suitgen 100

ENDIF



//test conversation block

//When interior works will check if he1_progress = 10 and locate for receptionist not guard3


IF he1_progress = 10
	IF NOT IS_CHAR_DEAD he1_recept1
	IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer he1_recept1 4.0 4.0 FALSE
		CLEAR_CHAR_TASKS he1_recept1
		TASK_TURN_CHAR_TO_FACE_CHAR he1_recept1 scplayer
		//REMOVE_BLIP he1_receptB

	   	


		// Working below!
	   	/*
		START_SETTING_UP_CONVERSATION he1_recept1
		SET_UP_CONVERSATION_NODE HEQ1 HEQ2 HEX1b
		SET_UP_CONVERSATION_END_NODE HEX1b
		SET_UP_CONVERSATION_NODE HEQ2 HEQ4 HEX2
		SET_UP_CONVERSATION_END_NODE HEQ4
   
		
		SET_UP_CONVERSATION_NODE HEX2 HEQ4c HEQ5
		
		SET_UP_CONVERSATION_END_NODE HEQ4c
		SET_UP_CONVERSATION_END_NODE HEQ5
		

	   	FINISH_SETTING_UP_CONVERSATION
	   	*/
		//Working above!

		
	   	START_SETTING_UP_CONVERSATION he1_recept1
	   	SET_UP_CONVERSATION_NODE_WITH_SCRIPTED_SPEECH HEQ1 HEQ2 HEX1b SOUND_HEIQ1 SOUND_HEIQ1Y SOUND_HEIQ1N
	  	SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH HEX1b SOUND_HEIX1B
		SET_UP_CONVERSATION_NODE_WITH_SCRIPTED_SPEECH HEQ2 HEQ4 HEX2 SOUND_HEIQ2 SOUND_HEIQ2Y SOUND_HEIQ2N
	   	SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH HEQ4 SOUND_HEIQ4

		SET_UP_CONVERSATION_NODE_WITH_SCRIPTED_SPEECH HEX2 HEQ4c HEQ5 SOUND_HEIX2 SOUND_HEIX2Y SOUND_HEIX2N

	   	SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH HEQ4c SOUND_HEIQ4c
	  	SET_UP_CONVERSATION_END_NODE_WITH_SCRIPTED_SPEECH HEQ5 SOUND_HEIQ5

		FINISH_SETTING_UP_CONVERSATION
		  





		//SWITCH_WIDESCREEN ON										    

		he1_progress = 15

		//temp fix
		/*
		CLEAR_CONVERSATION
		CLEAR_HELP
		REMOVE_BLIP he1_receptB
		REMOVE_BLIP he1_officesB
		
		ADD_BLIP_FOR_COORD 346.9205 165.9382 1024.7812 he1_plansB
		CHANGE_BLIP_COLOUR he1_plansB YELLOW

		WRITE_DEBUG_WITH_INT progress he1_progress															 
		he1_progress = 25 */
		//end of temp fix


		/*
		[HEIQ1:HEIST1]
		Can I help you sir?

		[HEIQ1Y:HEIST1]
		~s~Yes please, I'd like to look at some plans for the Mafia casino.

		[HEIQ1N:HEIST1]
		~r~Erm... no!

		[HEIQ2:HEIST1]
		OK sir. You are aware that reproduction of such plans is prohibited?

		[HEIQ2Y:HEIST1]
		~s~Yes, I'm planning on building a scale model for my uncle's birthday. He's keen on blackjack.

		[HEIQ2N:HEIST1]
		~r~No, I didn't know that. Why?

		[HEIH:HEIST1]
		Press ~<~ for "No" or ~>~ for "Yes"

		[HEIH2:HEIST1]
		Press R1 to restart conversation

		[HEIQ3:HEIST1]
		That's lovely sir! The plans for that building are located on the top floor.

		[HEIX1:HEIST1]
		I'm rather busy sir. Come to me if you change your mind.

		[HEIX2:HEIST1]
		Well, we don't want to be party to preparation of a daring raid on the count room! Does sir understand?

		[HEIX2Y:HEIST1]
		~s~Of course. The owners wouldn't want to take a gamble on that happening!

		[HEIX2N:HEIST1]
		~r~No! I'm some sort of dumbass! I thought I could waltz in and take the plans home to peruse at my leisure! 

		[HEIQ4:HEIST1]
		Very witty sir. You'll find the plans on the top floor sir.

		[HEIQ5:HEIST1]
		I'm sorry sir, I can't make it any clearer. Come back when you've thought it over.

		*/



	ENDIF
	ENDIF
ENDIF
//end of test conversation block


IF he1_progress = 15


	/*
	IF NOT IS_CHAR_DEAD he1_recept1
		TASK_CHAT_WITH_CHAR he1_recept1 scplayer  true true //ped0 will lead the chatting
		TASK_CHAT_WITH_CHAR scplayer he1_recept1 false true //ped1 will follow ped0 at chatting
	ENDIF
	*/
	IF NOT IS_CHAR_DEAD he1_recept1
	//IF IS_CONVERSATION_AT_NODE he1_recept1 HEIQ1
	IF IS_CONVERSATION_AT_NODE he1_recept1 HEQ1

		CLEAR_HELP
		REMOVE_BLIP he1_receptB
		PRINT_HELP_FOREVER TALK_1
		he1_progress = 20
	ENDIF
	ENDIF

ENDIF

IF he1_progress = 20
	

	IF IS_BUTTON_PRESSED PAD1 DPADLEFT
	OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
		CLEAR_HELP
	ENDIF
	


	
	IF NOT IS_CHAR_DEAD he1_recept1
  //	IF IS_CONVERSATION_AT_NODE he1_recept1 HEIQ3
	
	 IF IS_CONVERSATION_AT_NODE he1_recept1 HEQ4

		//CLEAR_CONVERSATION
		CLEAR_HELP
		REMOVE_BLIP he1_receptB
		REMOVE_BLIP he1_officesB
   
		//ADD_BLIP_FOR_COORD 346.9205 165.9382 1024.7812 he1_plansB
		//CHANGE_BLIP_COLOUR he1_plansB YELLOW
		he1_progress = 24
		he1_convofix = 0

		//SET_PLAYER_CONTROL player1 ON
	   //	RESTORE_CAMERA
	
	ENDIF

	/*
	IF IS_CONVERSATION_AT_NODE he1_recept1 HEIQ1Y
		SWITCH_WIDESCREEN ON
	ENDIF
	*/


	//IF IS_CONVERSATION_AT_NODE he1_recept1 HEIQ4
	IF IS_CONVERSATION_AT_NODE he1_recept1 HEQ4c

		//CLEAR_CONVERSATION
		CLEAR_HELP
		REMOVE_BLIP he1_receptB
		REMOVE_BLIP he1_officesB
		he1_convofix = 0

		
	   //	ADD_BLIP_FOR_COORD 346.9205 165.9382 1024.7812 he1_plansB
		//CHANGE_BLIP_COLOUR he1_plansB YELLOW
		he1_progress = 24
		//SET_PLAYER_CONTROL player1 ON
		//RESTORE_CAMERA

	
	ENDIF




	IF IS_CONVERSATION_AT_NODE he1_recept1 HEX1b
	   //	CLEAR_CONVERSATION
		//WAIT 4000
		PRINT_HELP_FOREVER HEIH9
		he1_attractattention = 1
		he1_progress = 22
		he1_convofix = 0

		IF NOT IS_CHAR_DEAD he1_recept1
			CLEAR_CHAR_TASKS he1_recept1
			TASK_SIT_DOWN he1_recept1 10000
			ADD_BLIP_FOR_CHAR he1_recept1 he1_receptB
			CHANGE_BLIP_COLOUR he1_receptB BLUE


			//SET_PLAYER_CONTROL player1 ON
			//RESTORE_CAMERA

		ENDIF 


		
	ENDIF





	//IF IS_CONVERSATION_AT_NODE he1_recept1 HEIQ5
	IF IS_CONVERSATION_AT_NODE he1_recept1 HEQ5
	   //	CLEAR_CONVERSATION
		//WAIT 4000
		PRINT_HELP_FOREVER HEIH9
		he1_attractattention = 1
		he1_progress = 22
		he1_convofix = 0

		IF NOT IS_CHAR_DEAD he1_recept1
			CLEAR_CHAR_TASKS he1_recept1
			TASK_SIT_DOWN he1_recept1 10000
			ADD_BLIP_FOR_CHAR he1_recept1 he1_receptB
			CHANGE_BLIP_COLOUR he1_receptB BLUE


			//SET_PLAYER_CONTROL player1 ON
			//RESTORE_CAMERA

		ENDIF 


		
	ENDIF
	
	//IF IS_CONVERSATION_AT_NODE he1_recept1 HEIX1
	IF IS_CONVERSATION_AT_NODE he1_recept1 HEX1
	   	//CLEAR_CONVERSATION
		//WAIT 4000
		PRINT_HELP_FOREVER HEIH9
		he1_attractattention = 1
		he1_progress = 22
		he1_convofix = 0


		IF NOT IS_CHAR_DEAD he1_recept1
			CLEAR_CHAR_TASKS he1_recept1
			TASK_SIT_DOWN he1_recept1 10000
			ADD_BLIP_FOR_CHAR he1_recept1 he1_receptB
			CHANGE_BLIP_COLOUR he1_receptB BLUE


			//SET_PLAYER_CONTROL player1 ON
			//RESTORE_CAMERA

		ENDIF 

	ENDIF

	IF he1_convofix = 0
		IF IS_CONVERSATION_AT_NODE he1_recept1 HEX2
			timera = 0
			he1_convofix = 1
		ENDIF
	ENDIF

	IF he1_convofix = 1
		IF timera > 4000
			CLEAR_PRINTS
		   //PRINT_NOW HEX2b 3000 1
			he1_counter = 12 
		    he1_convofix = 2
		ENDIF
	ENDIF

	IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer he1_recept1 5.0 5.0 FALSE
	   //	CLEAR_CONVERSATION
		//WAIT 4000
		//PRINT_HELP HEIH2
		he1_attractattention = 1
		he1_progress = 22
		IF NOT IS_CHAR_DEAD he1_recept1
			CLEAR_CHAR_TASKS he1_recept1
			TASK_SIT_DOWN he1_recept1 10000
			CLEAR_PRINTS
			CLEAR_HELP
			//PRINT_NOW HEIX3 4000 1
			//PRINT_NOW HEX3 4000 1

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			he1_counter = 21
			ADD_BLIP_FOR_CHAR he1_recept1 he1_receptB
			CHANGE_BLIP_COLOUR he1_receptB BLUE
			he1_convofix = 0
		ENDIF 
	ENDIF
	ENDIF
ENDIF

IF he1_progress = 22 
	IF NOT IS_CHAR_DEAD he1_recept1
		CLEAR_CONVERSATION_FOR_CHAR he1_recept1
	ENDIF	

	he1_convofix = 0
	he1_progress = 23
 
ENDIF
  
IF he1_progress = 23

	IF NOT IS_CHAR_DEAD	he1_recept1
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer he1_recept1 10.0 10.0 FALSE //5.0 5.0 FALSE
		PRINT_HELP_FOREVER HEIH9	   
		 	IF he1_attractattention = 1

				IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
		 			he1_progress = 10
					he1_attractattention = 0
				ENDIF
			ENDIF
		ENDIF

		 IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer he1_recept1 10.0 10.0 FALSE
		 CLEAR_HELP   
	   	 ENDIF

	ENDIF

ENDIF

IF he1_progress = 24
	IF NOT IS_CHAR_DEAD he1_recept1

	 he1_convofix = 0

     CLEAR_CONVERSATION_FOR_CHAR he1_recept1		 //askit
	 ENDIF
	 	
	 ADD_BLIP_FOR_COORD 372.2530 164.4298 1007.3984 he1_stairwellB
	 SET_BLIP_ENTRY_EXIT he1_stairwellB 2415.3479 1123.9423 10.0
	 he1_progress = 25
	 he1_attractattention = 0
ENDIF


IF he1_progress = 25

	CLEAR_HELP
	he1_conversationOK = 1
	FREEZE_OBJECT_POSITION he1_doorL FALSE	
										   
    CREATE_OBJECT DYN_AIRCON 349.3042 189.4448 1018.9844 he1_vending1
	SET_OBJECT_ROTATION he1_vending1 0.0 0.0 90.0
	SET_OBJECT_HEALTH he1_vending1 450
	
	CREATE_OBJECT DYN_AIRCON 349.3042 189.4448 1019.7844 he1_vending2
	MAKE_OBJECT_TARGETTABLE	he1_vending2 TRUE
	SET_OBJECT_ROTATION he1_vending2 0.0 0.0 90.0 
	SET_OBJECT_HEALTH he1_vending2 450 

	GET_OBJECT_HEALTH he1_vending2 he1_vending2health
	GET_OBJECT_MASS he1_vending2 he1_vending2mass

	he1_progress = 27
	he1_stairprompt = 1
	timera = 0

	CLEAR_MISSION_AUDIO 3
   	LOAD_MISSION_AUDIO 3 SOUND_AIRCONDITIONER
  	sfx_aircon = 1
	
ENDIF


IF sfx_aircon = 1
	IF HAS_MISSION_AUDIO_LOADED 3
		IF DOES_OBJECT_EXIST he1_vending2
			ATTACH_MISSION_AUDIO_TO_OBJECT 3 he1_vending2
			PLAY_MISSION_AUDIO 3
			sfx_aircon = 2
		ENDIF
   ENDIF
ENDIF

IF he1_progress = 27

	IF timera > 3000
		IF he1_stairprompt = 1
			CLEAR_PRINTS 
			PRINT_NOW HEI1_33 4000 1
			he1_stairprompt = 0
		ENDIF
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 372.2530 164.4298 1007.3984 1.0 1.0 1.5 FALSE
	   	REMOVE_BLIP he1_stairwellB
		
		he1_stairprompt = 0

		CLEAR_PRINTS 
		
		PRINT_NOW HEI1_34 6000 1
		ADD_BLIP_FOR_COORD 346.9205 165.9382 1024.7812 he1_plansB
		SET_BLIP_ENTRY_EXIT he1_plansB 2415.3479 1123.9423 10.0
		CHANGE_BLIP_COLOUR he1_plansB YELLOW
		he1_progress = 30
	ENDIF


ENDIF






// Fail condition area
IF he1_failconditions  = 1
	
	IF he1_inbuilding = 1
	IF he1_drillstarted = 0 
		

		IF NOT IS_CHAR_DEAD he1_guard1 
			IF he1_progress < 40
			IF he1_stairprompt = 0
			IF he1_convofix = 0
			IF TIMERA > 2000
				IF he1_weaponaimed = 0
				CLEAR_CHAR_TASKS_IMMEDIATELY he1_guard1
				TASK_TURN_CHAR_TO_FACE_CHAR he1_guard1 scplayer
				IF NOT IS_CHAR_DEAD he1_guard2
					IF NOT IS_CHAR_DEAD he1_guard3
						CLEAR_CHAR_TASKS_IMMEDIATELY he1_guard2
						CLEAR_CHAR_TASKS_IMMEDIATELY he1_guard3
						TASK_TURN_CHAR_TO_FACE_CHAR he1_guard2 scplayer
						TASK_TURN_CHAR_TO_FACE_CHAR he1_guard3 scplayer
						TIMERA = 0
						he1_checkchat = 1
					ENDIF
				ENDIF
				ENDIF
			ENDIF
			ENDIF
			ENDIF
			ENDIF




			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_guard1 scplayer
				CLEAR_CHAR_TASKS he1_guard1
				TASK_KILL_CHAR_ON_FOOT he1_guard1 scplayer
				//WAIT 2000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
			ENDIF

			IF he1_hideweaponcheck = 0
			IF HAS_CHAR_SPOTTED_CHAR he1_guard1 scplayer
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
			   //IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SKATEBOARD
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_FLOWERS
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO1
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO2
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE1
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE2
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					he1_audio_underway = 0
					he1_audio_playing = 0

					
			   		he1_counter = 7
			   		//PRINT_NOW HEI1_44 4000 1	
					TIMERB = 0
					he1_hideweaponcheck = 1
			   //	ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF

			ENDIF
			ENDIF

			IF he1_weaponaimed = 1 
				IF he1_g1aim = 0
				CLEAR_CHAR_TASKS_IMMEDIATELY he1_guard1
				TASK_STAY_IN_SAME_PLACE he1_guard1 TRUE
				TASK_AIM_GUN_AT_CHAR he1_guard1 scplayer 5000
				//he1_weaponaimed = 2
				he1_g1aim = 1
				ENDIF
			ENDIF

		ENDIF

		
		IF NOT IS_CHAR_DEAD he1_guard2
   
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_guard2 scplayer
				CLEAR_CHAR_TASKS he1_guard2
				TASK_KILL_CHAR_ON_FOOT he1_guard2 scplayer
				//WAIT 2000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
			ENDIF
 

			IF he1_hideweaponcheck = 0
			IF HAS_CHAR_SPOTTED_CHAR he1_guard2 scplayer
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
			  // IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SKATEBOARD
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_FLOWERS
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO1
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO2
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE1
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE2
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE

					CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
					he1_audio_playing = 0

					he1_counter = 7
			    	//PRINT_NOW HEI1_44 4000 1	
					TIMERB = 0
					he1_hideweaponcheck = 1
				//ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
			ENDIF
			ENDIF

			IF he1_weaponaimed = 1
				IF he1_g2aim = 0
				CLEAR_CHAR_TASKS_IMMEDIATELY he1_guard2
				TASK_STAY_IN_SAME_PLACE he1_guard2 TRUE

				TASK_AIM_GUN_AT_CHAR he1_guard2 scplayer 5000
				//he1_weaponaimed = 2
				he1_g2aim = 1
				ENDIF
			ENDIF

		ENDIF
		
		IF NOT IS_CHAR_DEAD he1_guard3 
		/*
			IF TIMERA > 2000
				CLEAR_CHAR_TASKS he1_guard3
				TASK_TURN_CHAR_TO_FACE_CHAR he1_guard3 scplayer
				TIMERA = 0
			ENDIF
		 */
			
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_guard3 scplayer
				CLEAR_CHAR_TASKS he1_guard3
				TASK_KILL_CHAR_ON_FOOT he1_guard3 scplayer
				//WAIT 2000
				PRINT_NOW HEI1_50 4000 1
			  	GOSUB he1_kickout
				GOTO mission_heist1_failed
			ENDIF
			
		   //	IF HAS_CHAR_SPOTTED_CHAR he1_guard3 scplayer
				
				IF he1_conversationOK = 1
				IF he1_cameracheck = 0

				//Critical Area here!!!!!!!!!!!!!!!!!  Cues cutscene!!!!!!!!!!!!!!!!!!!!
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				   //	IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer he1_guard3 14.0 1.0 FALSE 
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 367.6113 162.3746 1024.7812 3.5 3.5 1.5 FALSE

					//	IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
						//PRINT_NOW HEI1_5 4000 1
						IF he1_hideweaponcheck = 0
						he1_cameracheck = 1
						ENDIF
						
					ENDIF
				ENDIF
				ENDIF

			 //ENDIF



				
				IF HAS_OBJECT_BEEN_PHOTOGRAPHED he1_plans
				   //	PRINT_NOW HEI1_51 4000 1
					//WAIT 4500
					CLEAR_PRINTS
					PRINT_NOW HEI1_52 4000 1
				  	 GOSUB he1_kickout
					GOTO mission_heist1_failed
				ENDIF
				

		   //	ENDIF
			
			IF he1_hideweaponcheck = 0
   		   //	IF HAS_CHAR_SPOTTED_CHAR he1_guard3 scplayer
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer he1_guard3 10.0 10.0 2.0 FALSE   // dodgy!
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
			   //IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
			   //IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SKATEBOARD
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_FLOWERS
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO1
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO2
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE1
			   IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE2
			    IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE


					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					he1_audio_underway = 0
					he1_audio_playing = 0
					IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
					he1_counter = 10
					ELSE
					he1_counter = 7
					ENDIF

			    	//PRINT_NOW HEI1_48 4000 1	
					TIMERB = 0
					he1_hideweaponcheck = 1
					he1_level3 = 1
				//ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
				ENDIF
			
				ENDIF

			ENDIF
			ENDIF
	 
			IF he1_weaponaimed = 1
				IF he1_g3aim = 0
				CLEAR_CHAR_TASKS_IMMEDIATELY he1_guard3
				TASK_STAY_IN_SAME_PLACE he1_guard3 TRUE

				TASK_AIM_GUN_AT_CHAR he1_guard3 scplayer 5000
				//he1_weaponaimed = 2
				he1_g3aim = 1
				ENDIF
			ENDIF



			IF he1_conversationOK = 1
			IF he1_cameracheck = 1
					SET_PLAYER_CONTROL player1 OFF 
					CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					he1_audio_underway = 0
					he1_audio_playing = 0
					he1_counter = 0
					
					DO_FADE 500 FADE_OUT
					 

					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE



				  	CLEAR_PRINTS

					
				 
					SWITCH_WIDESCREEN ON

					SET_FIXED_CAMERA_POSITION 345.0205 163.0617 1026.8260 0.0 0.0 0.0//340.6378 161.8563 1027.4790  0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 345.8946 163.5473 1026.8174 JUMP_CUT//341.5486 162.1901 1027.2361 JUMP_CUT


					//SET_FIXED_CAMERA_POSITION 345.6665 161.0074 1025.2107 0.0 0.0 0.0//340.6378 161.8563 1027.4790  0.0 0.0 0.0
					//POINT_CAMERA_AT_POINT 346.2789 161.7530 1025.4735 JUMP_CUT//341.5486 162.1901 1027.2361 JUMP_CUT
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE

		
		
					he1_cameracheck = 2
					//he1_ctskipcameraneeded = 1
					PRINT_NOW HEI1_6 4000 1

					TIMERB = 0
					ENDIF

			IF he1_cameracheck =2 
				IF TIMERB > 1000
					he1_ctskipcameraneeded = 1
				ENDIF
				IF TIMERB > 4000
					CLEAR_PRINTS
					//PRINT_NOW HEI1_7 4000 1
					he1_cameracheck = 4
					TIMERB = 0
				ENDIF
			ENDIF

		

			IF he1_cameracheck = 4
		
					he1_ctskipcameraneeded = 0
					

					DO_FADE 500 FADE_OUT 
					CLEAR_PRINTS
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					//SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT

				 
					SWITCH_WIDESCREEN OFF 
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE

					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT



				
					CLEAR_PRINTS
					
					REMOVE_BLIP he1_plansB
					ADD_BLIP_FOR_COORD 357.3549 193.4150 1018.9844 he1_depositoryB
					SET_BLIP_ENTRY_EXIT he1_depositoryB 2415.3479 1123.9423 10.0

					CHANGE_BLIP_COLOUR he1_depositoryB YELLOW
					he1_cameracheck = 99
					
					PRINT_NOW HEI1_7 6000 1

				 
				
			ENDIF
	   		ENDIF


		ENDIF
		
		 
		IF he1_hideweaponcheck = 1
			IF TIMERB > 6000
				IF he1_weaponaimed = 0
				IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
					//WAIT 1000
					CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
					he1_audio_playing = 0

					IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA

					he1_counter = 9
					ENDIF
					//PRINT_NOW HEI1_49 6000 1
					he1_weaponaimed = 1
				 ENDIF	
				
				ENDIF
			ENDIF

			IF TIMERB > 12000
				CLEAR_PRINTS 
				PRINT_NOW HEI1_50 6000 1
			  	GOSUB he1_kickout
				GOTO mission_heist1_failed
			ENDIF


			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
		 		he1_hideweaponcheck = 0
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

			 	CLEAR_MISSION_AUDIO he1_alt_slot
				CLEAR_MISSION_AUDIO he1_audio_slot
				CLEAR_PRINTS
				he1_audio_underway = 0
				he1_audio_playing = 0

				he1_counter = 8
				//PRINT_NOW HEI1_45 4000 1
			ENDIF

			
			IF NOT he1_level3 = 1
			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
				he1_hideweaponcheck	= 0
				CLEAR_MISSION_AUDIO he1_alt_slot
				CLEAR_MISSION_AUDIO he1_audio_slot
				CLEAR_PRINTS
				he1_audio_underway = 0
				he1_audio_playing = 0
				he1_counter = 8
				//PRINT_NOW HEI1_45 4000 1
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

		  	ENDIF
			ENDIF
			
			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
				he1_hideweaponcheck	= 0
				CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
				he1_audio_playing = 0

				he1_counter = 8
				//PRINT_NOW HEI1_45 4000 1
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

		  	ENDIF

//			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_SKATEBOARD
//		 		he1_hideweaponcheck = 0
//				he1_weaponaimed = 0
//				he1_g1aim = 0
//				he1_g2aim = 0
//				he1_g3aim = 0
//				he1_level3 = 0
//				CLEAR_MISSION_AUDIO he1_alt_slot
//				CLEAR_MISSION_AUDIO he1_audio_slot
//				CLEAR_PRINTS
//				he1_audio_underway = 0
//				he1_audio_playing = 0
//
//				he1_counter = 8
//				//PRINT_NOW HEI1_45 4000 1
//			ENDIF
			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_FLOWERS
				he1_hideweaponcheck	= 0
				CLEAR_MISSION_AUDIO he1_alt_slot
				CLEAR_MISSION_AUDIO he1_audio_slot
				CLEAR_PRINTS
				he1_audio_underway = 0
				he1_audio_playing = 0

				he1_counter = 8
				//PRINT_NOW HEI1_45 4000 1
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

		  	ENDIF
			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO1
				he1_hideweaponcheck	= 0
			  	CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
				he1_audio_playing = 0

			   	he1_counter = 8
			   //	PRINT_NOW HEI1_45 4000 1
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

		  	ENDIF

				IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DILDO2
		 		he1_hideweaponcheck = 0
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0
				CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
				he1_audio_playing = 0

				he1_counter = 8
				//PRINT_NOW HEI1_45 4000 1
			ENDIF
			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE1
				he1_hideweaponcheck	= 0
				CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
				he1_audio_playing = 0

				he1_counter = 8
				//PRINT_NOW HEI1_45 4000 1
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

		  	ENDIF
			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_VIBE2
				he1_hideweaponcheck	= 0
			   CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
				he1_audio_playing = 0

			   	he1_counter = 8
			   //	PRINT_NOW HEI1_45 4000 1
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

		  	ENDIF
			
			IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE
				he1_hideweaponcheck	= 0
				CLEAR_MISSION_AUDIO he1_alt_slot
					CLEAR_MISSION_AUDIO he1_audio_slot
					CLEAR_PRINTS
					he1_audio_underway = 0
				he1_audio_playing = 0

				he1_counter = 8
				//PRINT_NOW HEI1_45 4000 1
				he1_weaponaimed = 0
				he1_g1aim = 0
				he1_g2aim = 0
				he1_g3aim = 0
				he1_level3 = 0

		  	ENDIF

			IF IS_CHAR_SHOOTING scplayer
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
			ENDIF


		
		ENDIF

		
		IF DOES_CHAR_EXIST he1_pop1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop1 scplayer
				IF NOT IS_CHAR_DEAD he1_pop1
				CLEAR_CHAR_TASKS he1_pop1
		   		TASK_SMART_FLEE_CHAR he1_pop1 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_pop2
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop2 scplayer
				IF NOT IS_CHAR_DEAD he1_pop2
				CLEAR_CHAR_TASKS he1_pop2
		   		TASK_SMART_FLEE_CHAR he1_pop2 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF
		
		IF DOES_CHAR_EXIST he1_pop3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop3 scplayer
				IF NOT IS_CHAR_DEAD he1_pop3
				CLEAR_CHAR_TASKS he1_pop3
		   		TASK_SMART_FLEE_CHAR he1_pop3 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			  	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_pop4
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop4 scplayer
				IF NOT IS_CHAR_DEAD he1_pop4
				CLEAR_CHAR_TASKS he1_pop4
		   		TASK_SMART_FLEE_CHAR he1_pop4 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF
			IF DOES_CHAR_EXIST he1_pop5
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop5 scplayer
				IF NOT IS_CHAR_DEAD he1_pop5
				CLEAR_CHAR_TASKS he1_pop5
		   		TASK_SMART_FLEE_CHAR he1_pop5 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_pop6
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop6 scplayer
				IF NOT IS_CHAR_DEAD he1_pop6
				CLEAR_CHAR_TASKS he1_pop6
		   		TASK_SMART_FLEE_CHAR he1_pop6 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_pop7
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop7 scplayer
				IF NOT IS_CHAR_DEAD he1_pop7
				CLEAR_CHAR_TASKS he1_pop7
		   		TASK_SMART_FLEE_CHAR he1_pop7 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			 	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF
			IF DOES_CHAR_EXIST he1_pop8
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop8 scplayer
				IF NOT IS_CHAR_DEAD he1_pop8
				CLEAR_CHAR_TASKS he1_pop8
		   		TASK_SMART_FLEE_CHAR he1_pop8 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_pop9
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_pop9 scplayer
				IF NOT IS_CHAR_DEAD he1_pop9
				CLEAR_CHAR_TASKS he1_pop9
		   		TASK_SMART_FLEE_CHAR he1_pop9 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
				gosub he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_recept1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_recept1 scplayer
				IF NOT IS_CHAR_DEAD he1_recept1
				CLEAR_CHAR_TASKS he1_recept1
		   		TASK_SMART_FLEE_CHAR he1_recept1 scplayer 100.0 -1
				//WAIT 1000
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_bystander1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander1 scplayer
				IF NOT IS_CHAR_DEAD he1_bystander1
				CLEAR_CHAR_TASKS he1_bystander1
		   		TASK_SMART_FLEE_CHAR he1_bystander1 scplayer 100.0 -1
				//WAIT 1000
				GOSUB he1_guardresponse
				PRINT_NOW HEI1_50 4000 1
			   GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF

		IF DOES_CHAR_EXIST he1_bystander2
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander2 scplayer
				IF NOT IS_CHAR_DEAD he1_bystander2
				CLEAR_CHAR_TASKS he1_bystander2
		   		TASK_SMART_FLEE_CHAR he1_bystander2 scplayer 100.0 -1
				//WAIT 1000
				GOSUB he1_guardresponse
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF


		IF DOES_CHAR_EXIST he1_bystander3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander3 scplayer
				IF NOT IS_CHAR_DEAD he1_bystander3
				CLEAR_CHAR_TASKS he1_bystander3
		   		TASK_SMART_FLEE_CHAR he1_bystander3 scplayer 100.0 -1
				//WAIT 1000
				GOSUB he1_guardresponse
				PRINT_NOW HEI1_50 4000 1
			   	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF

			
				
		ENDIF

		IF DOES_CHAR_EXIST he1_bystander4
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander4 scplayer
				IF NOT IS_CHAR_DEAD he1_bystander4
				CLEAR_CHAR_TASKS he1_bystander4
		   		TASK_SMART_FLEE_CHAR he1_bystander4 scplayer 100.0 -1
				//WAIT 1000
				GOSUB he1_guardresponse
				PRINT_NOW HEI1_50 4000 1
			  	GOSUB he1_kickout
				GOTO mission_heist1_failed
				ENDIF
			ENDIF
		ENDIF




	ENDIF
	ENDIF
ENDIF





IF he1_progress = 30 // was 35

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 364.4255 188.2647 1018.9844 2.0 2.0 2.0 FALSE
	   IF he1_cameracheck > 1
	   IF he1_airconfix = 0
		   REMOVE_BLIP he1_depositoryB
		   //ADD_BLIP_FOR_OBJECT he1_vending1 he1_vending1B
		   //SET_BLIP_ENTRY_EXIT he1_vending1B 2415.3479 1123.9423 10.0

		   ADD_BLIP_FOR_OBJECT he1_vending1 he1_vending2B
		   SET_BLIP_ENTRY_EXIT he1_vending2B 2415.3479 1123.9423 10.0

		   CLEAR_PRINTS
		   PRINT_NOW HEI1_10 6000 1
		   he1_airconfix = 1
	   ENDIF
	   ENDIF
	   //he1_progress = 35
	ENDIF




	IF he1_vdam1 = 0
	IF HAS_OBJECT_BEEN_DAMAGED he1_vending1
		
 		GENERATE_RANDOM_INT_IN_RANGE 1 4 he1_TempInt

		BREAK_OBJECT he1_vending1 TRUE

	  	REMOVE_BLIP he1_vending2B
		he1_tempcount ++
		he1_vdam1 = 1
		he1_tempcount = 2
		MAKE_OBJECT_TARGETTABLE he1_vending1 FALSE
		
	ENDIF
	ENDIF

	IF he1_vdam2 = 0
			IF he1_tempcount < 2
				IF HAS_OBJECT_BEEN_DAMAGED he1_vending2
				   //	GET_OBJECT_HEALTH he1_vending2 he1_vending2health

					
				   //	WRITE_DEBUG_WITH_INT ding2 he1_vending2health
					

					
				   	GENERATE_RANDOM_INT_IN_RANGE 1 4 he1_TempInt
				 	
				 	BREAK_OBJECT he1_vending2 TRUE

				   	REMOVE_BLIP he1_vending2B
					he1_tempcount ++
					he1_vdam2 = 1
					he1_tempcount = 2 //bug fix c
					MAKE_OBJECT_TARGETTABLE he1_vending2 FALSE
					timera = 0
					
				ENDIF
		ENDIF
	ENDIF

	IF he1_tempcount = 2
	  CLEAR_MISSION_AUDIO 3
	  sfx_aircon = 99 // finish aircon effect
	  //sfx_bankfire = 1 // begin to load fire alarm
	
	//BREAK_OBJECT he1_vending2 TRUE
		IF TIMERA > 1000
			START_SCRIPT_FIRE 350.8387 190.3284 1019.0000 1 he1_TempInt he1_scriptfire2
		   //	sfx_bankfire = 1 
			he1_tempcount = 3
		ENDIF

	ENDIF


	IF he1_tempcount = 3
	 he1_progress = 40
	 //he1_progress = 5000
	 CREATE_FX_SYSTEM teargas 350.6574 189.2716 1019.0000 TRUE he1_vendingsmoke1
	 CREATE_FX_SYSTEM teargas 350.6574 189.2716 1020.0000 TRUE he1_vendingsmoke2
	 CREATE_FX_SYSTEM teargas 350.8387 190.3284 1019.0000 TRUE he1_vendingsmoke3

	 PLAY_FX_SYSTEM he1_vendingsmoke1
	 PLAY_FX_SYSTEM he1_vendingsmoke2
	 PLAY_FX_SYSTEM he1_vendingsmoke3

	 
	 TIMERA = 0
	 PRINT_NOW HEI1_20 4000 1
	 sfx_bankfire = 1
	// REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_OFFICE_FIRE_ALARM_START

	 //CREATE_FX_SYSTEM fire_large 350.3359 189.3490 1019.1844 TRUE he1_dummyfire1
	 //CREATE_FX_SYSTEM fire_large 350.3380 194.6215 1019.1766 TRUE he1_dummyfire2
	ENDIF


	/*If something is on fire
	  he1_failconditions = 0
	  he1_drillstarted = 1
	
	*/

ENDIF//he1_progress = 35 condition check



IF he1_progress = 40 
	IF TIMERA > 3000
	 he1_failconditions = 0
	 he1_drillstarted = 1
	 he1_progress = 45
	ENDIF
ENDIF





IF he1_progress > 9

	IF he1_checkchat = 1
	IF he1_progress < 45

			
			IF NOT IS_CHAR_DEAD he1_pop1
			IF NOT IS_CHAR_DEAD he1_pop2
				GET_SCRIPT_TASK_STATUS he1_pop1 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK
				GET_SCRIPT_TASK_STATUS he1_pop2 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK

					CLEAR_CHAR_TASKS he1_pop1
					CLEAR_CHAR_TASKS he1_pop2
						
				   //	TASK_LOOK_ABOUT he1_pop1 -1
				   //	TASK_LOOK_ABOUT he1_pop2 -1

				   	TASK_CHAT_WITH_CHAR he1_pop1 he1_pop2  true true //ped0 will lead the chatting
				   	TASK_CHAT_WITH_CHAR he1_pop2 he1_pop1 false true //ped1 will follow ped0 at chatting
				ENDIF
				ENDIF
			ENDIF
			ENDIF
			

			
			IF NOT IS_CHAR_DEAD he1_pop3
			IF NOT IS_CHAR_DEAD he1_pop4
				GET_SCRIPT_TASK_STATUS he1_pop3 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK
				GET_SCRIPT_TASK_STATUS he1_pop4 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK

					CLEAR_CHAR_TASKS he1_pop3
					CLEAR_CHAR_TASKS he1_pop4

				//TASK_WANDER_COP he1_pop3
				//TASK_WANDER_COP he1_pop4
				   //	TASK_LOOK_ABOUT he1_pop3 -1
				//TASK_LOOK_ABOUT he1_pop4 -1

				
			  	   	TASK_CHAT_WITH_CHAR he1_pop3 he1_pop4  true true //ped0 will lead the chatting
			   	   	TASK_CHAT_WITH_CHAR he1_pop4 he1_pop3 false true //ped1 will follow ped0 at chatting
				ENDIF
				ENDIF
			ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop5
			IF NOT IS_CHAR_DEAD he1_pop6
				GET_SCRIPT_TASK_STATUS he1_pop5 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK
				GET_SCRIPT_TASK_STATUS he1_pop6 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK

					CLEAR_CHAR_TASKS he1_pop5
					CLEAR_CHAR_TASKS he1_pop6

				//TASK_WANDER_CRIMINAL he1_pop5
				//TASK_WANDER_CRIMINAL he1_pop6
				   //	TASK_LOOK_ABOUT he1_pop5 -1
				   //	TASK_LOOK_ABOUT he1_pop6 -1
				


			  	   TASK_CHAT_WITH_CHAR he1_pop5 he1_pop6  true true //ped0 will lead the chatting
			   	   	TASK_CHAT_WITH_CHAR he1_pop6 he1_pop5 false true //ped1 will follow ped0 at chatting
				ENDIF
				ENDIF
			ENDIF
			ENDIF


			IF NOT IS_CHAR_DEAD he1_pop7
			IF NOT IS_CHAR_DEAD he1_pop8
				GET_SCRIPT_TASK_STATUS he1_pop7 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK
				GET_SCRIPT_TASK_STATUS he1_pop8 TASK_CHAT_WITH_CHAR task_state
				IF task_state = FINISHED_TASK

					CLEAR_CHAR_TASKS he1_pop7
					CLEAR_CHAR_TASKS he1_pop8

				   //	TASK_LOOK_ABOUT he1_pop7 -1
				   //	TASK_LOOK_ABOUT he1_pop8 -1


				//TASK_WANDER_CRIMINAL he1_pop5
				//TASK_WANDER_CRIMINAL he1_pop6
				
			  	   	TASK_CHAT_WITH_CHAR he1_pop7 he1_pop8  true true //ped0 will lead the chatting
			   	   	TASK_CHAT_WITH_CHAR he1_pop8 he1_pop7 false true //ped1 will follow ped0 at chatting
				ENDIF
				ENDIF
			ENDIF
			ENDIF


		   
	
		he1_checkchat = 0
	
	 ENDIF
   ENDIF			
ENDIF






IF he1_progress = 45

		IF NOT IS_CHAR_DEAD he1_pop3
	   		   CLEAR_CHAR_TASKS he1_pop3
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_pop3 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   	ENDIF

		IF NOT IS_CHAR_DEAD he1_pop5
	   		   CLEAR_CHAR_TASKS he1_pop5
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_pop5 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   	ENDIF

	   	IF NOT IS_CHAR_DEAD he1_pop4
	   		   CLEAR_CHAR_TASKS he1_pop4
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_pop4 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   	ENDIF

		IF NOT IS_CHAR_DEAD he1_pop6
	   		   CLEAR_CHAR_TASKS he1_pop6
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_pop6 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   	ENDIF

		IF NOT IS_CHAR_DEAD he1_pop7
	   		   CLEAR_CHAR_TASKS he1_pop7
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_pop7 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   	ENDIF

	   	IF NOT IS_CHAR_DEAD he1_pop8
	   		   CLEAR_CHAR_TASKS he1_pop8
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_pop8 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   	ENDIF

		IF NOT IS_CHAR_DEAD he1_pop9
	   		   CLEAR_CHAR_TASKS he1_pop9
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_pop9 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   	ENDIF


		IF NOT IS_CHAR_DEAD he1_bystander4
			CLEAR_CHAR_TASKS he1_bystander4
			SET_CHAR_DECISION_MAKER he1_bystander4 he1_emptydm
		  	SET_CHAR_COORDINATES he1_bystander4 377.7027 153.2545 1022.7745
		ENDIF



		DO_FADE 500 FADE_OUT

	 

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		

	 	SET_PLAYER_CONTROL player1 OFF 
		SWITCH_WIDESCREEN ON

	   //	REMOVE_SCRIPT_FIRE he1_scriptfire1
		REMOVE_SCRIPT_FIRE he1_scriptfire2
		SET_FIXED_CAMERA_POSITION 350.4867 162.1073 1026.9894  0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 351.4692 162.1195 1026.8037 JUMP_CUT

 

	
		IF NOT IS_CHAR_DEAD he1_guard3
			TASK_STAY_IN_SAME_PLACE he1_guard3 FALSE

			FLUSH_ROUTE
			EXTEND_ROUTE 353.4759 161.9657 1024.7812
			EXTEND_ROUTE 365.8191 162.2179 1024.7812			
			TASK_FOLLOW_POINT_ROUTE he1_guard3 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
			//SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_guard3 150.0
	  	ENDIF
		IF NOT IS_CHAR_DEAD he1_bystander1
			SET_CHAR_DECISION_MAKER he1_bystander1 he1_emptydm
			CLEAR_CHAR_TASKS he1_bystander1


			SET_CHAR_COORDINATES he1_bystander1 353.9879 164.0512 1024.7812
			SET_CHAR_HEADING he1_bystander1 224.0
			CLEAR_CHAR_TASKS he1_bystander1

			SET_CHAR_DECISION_MAKER he1_bystander1 he1_emptydm

			
			FLUSH_ROUTE
			EXTEND_ROUTE 355.5494 162.7226 1024.7736 
			EXTEND_ROUTE 368.7490 162.4855 1024.7736
			EXTEND_ROUTE 378.8240 162.5327 1024.7736  			
			TASK_FOLLOW_POINT_ROUTE he1_bystander1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
			//SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander1 150.0
	  		he1_byroute1 = 1
	  	ENDIF
		IF NOT IS_CHAR_DEAD he1_bystander2
			SET_CHAR_DECISION_MAKER he1_bystander2 he1_emptydm
			CLEAR_CHAR_TASKS he1_bystander2


			SET_CHAR_COORDINATES he1_bystander2 354.1077 160.1630 1024.7812
			SET_CHAR_HEADING he1_bystander2 302.0
			CLEAR_CHAR_TASKS he1_bystander2
			SET_CHAR_DECISION_MAKER he1_bystander2 he1_emptydm

			
			FLUSH_ROUTE
			EXTEND_ROUTE 355.5494 162.7226 1024.7736 
			EXTEND_ROUTE 368.7490 162.4855 1024.7736
			EXTEND_ROUTE 378.8240 162.5327 1024.7736  			
			TASK_FOLLOW_POINT_ROUTE he1_bystander2 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
			//SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander2 150.0
	  		he1_byroute2 = 2
	  	ENDIF
		IF NOT IS_CHAR_DEAD he1_bystander3
			SET_CHAR_DECISION_MAKER he1_bystander3 he1_emptydm
			CLEAR_CHAR_TASKS he1_bystander3


			SET_CHAR_COORDINATES he1_bystander3 364.2849 160.2124 1024.7886
			SET_CHAR_HEADING he1_bystander3 294.5
			CLEAR_CHAR_TASKS he1_bystander3

			SET_CHAR_DECISION_MAKER he1_bystander3 he1_emptydm
			//SET_CHAR_DECISION_MAKER he1_bystander1 he1_touristdm
			
			FLUSH_ROUTE
			//EXTEND_ROUTE 362.2583 161.3967 1024.7736 
			EXTEND_ROUTE 369.2587 162.4347 1024.7736  
			EXTEND_ROUTE 377.2099 163.5096 1024.7812 			
			TASK_FOLLOW_POINT_ROUTE he1_bystander3 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
			//SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander3 150.0
	  		he1_byroute3 = 1
	  	ENDIF
		CLEAR_PRINTS
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE


		he1_progress = 50
		TIMERB = 0
		he1_ctskipfireneeded = 1

		CLEAR_PRINTS

		CLEAR_MISSION_AUDIO he1_alt_slot
		CLEAR_MISSION_AUDIO he1_audio_slot
		he1_audio_underway = 0
		he1_audio_playing = 0

		
   		he1_counter = 13



		//PRINT_NOW HEI1_21 6000 1

	 
ENDIF

//fix

IF he1_progress = 50
  
	IF TIMERB > 4000
		he1_progress = 55
   	ENDIF

ENDIF


IF he1_progress = 55
   he1_ctskipfireneeded = 0
	DO_FADE 500 FADE_OUT 
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	//PC bug fix
	CLEAR_CHAR_TASKS scplayer
	

	SWITCH_WIDESCREEN OFF 
	DO_FADE 500 FADE_IN

	//PC bug fix
	CLEAR_CHAR_TASKS scplayer



	SET_PLAYER_CONTROL player1 ON

   
	//he1_smokebegun = 1



	he1_TempInt = 3
	START_SCRIPT_FIRE 350.6574 189.5716 1019.0000 1 he1_TempInt he1_scriptfire1
	START_SCRIPT_FIRE 350.8387 190.3284 1019.0000 1 2 he1_scriptfire2
   
	he1_progress = 60

	IF NOT IS_CHAR_DEAD he1_guard3
		SET_CHAR_DECISION_MAKER he1_guard3 he1_guarddm
		SET_CHAR_COORDINATES he1_guard3 2419.9937 1153.7103 9.8047
		//TASK_GO_STRAIGHT_TO_COORD he1_guard3 381.8217 173.7668 1007.3906 PEDMOVE_WALK -2

		
	ENDIF

	IF NOT IS_CHAR_DEAD he1_guard2
		SET_CHAR_DECISION_MAKER he1_guard2 he1_guarddm
		SET_CHAR_COORDINATES he1_guard2 2419.9937 1145.7103 9.8047
		//TASK_GO_STRAIGHT_TO_COORD he1_guard3 381.8217 173.7668 1007.3906 PEDMOVE_WALK -2

		
	ENDIF

	IF NOT IS_CHAR_DEAD he1_guard1
		SET_CHAR_DECISION_MAKER he1_guard1 he1_guarddm
		SET_CHAR_COORDINATES he1_guard1 2417.6550 1154.0846 9.8047
		//TASK_GO_STRAIGHT_TO_COORD he1_guard3 381.8217 173.7668 1007.3906 PEDMOVE_WALK -2

		
	ENDIF

	IF NOT IS_CHAR_DEAD he1_recept1 
		SET_CHAR_COORDINATES he1_recept1 2419.3633 1132.6721 9.8047
		SET_CHAR_HEADING he1_recept1 135.0
	ENDIF


	IF NOT IS_CHAR_DEAD he1_pop1
		DELETE_CHAR he1_pop1
	ENDIF
	
	IF NOT IS_CHAR_DEAD he1_pop2
		DELETE_CHAR he1_pop2
	ENDIF

	
	IF NOT IS_CHAR_DEAD he1_bystander1
		CLEAR_CHAR_TASKS he1_bystander1
		SET_CHAR_COORDINATES he1_bystander1 372.0212 163.0494 1024.7734
		SET_CHAR_HEADING he1_bystander1 276.1
	ENDIF

	IF NOT IS_CHAR_DEAD he1_bystander2
	  
	 	CLEAR_CHAR_TASKS he1_bystander2
		SET_CHAR_COORDINATES he1_bystander2 375.6833 163.8624 1024.7734
		SET_CHAR_HEADING he1_bystander2 266.1

	ENDIF

	IF NOT IS_CHAR_DEAD he1_bystander3
	 	CLEAR_CHAR_TASKS he1_bystander3
		SET_CHAR_COORDINATES he1_bystander3 378.0436 162.5748 1024.7750
		SET_CHAR_HEADING he1_bystander3 168.1	
		//TASK_GO_STRAIGHT_TO_COORD he1_bystander3 381.8217 173.7668 1007.3906 PEDMOVE_WALK -2
	ENDIF

	IF NOT IS_CHAR_DEAD he1_bystander4
		CLEAR_CHAR_TASKS he1_bystander4
		SET_CHAR_COORDINATES he1_bystander4 377.7027 153.2545 1022.7745
		SET_CHAR_HEADING he1_bystander4 101.1
	
	ENDIF
	

	CLEAR_PRINTS
	PRINT_NOW HEI1_22 6000 1
	REMOVE_BLIP he1_plansB
	ADD_BLIP_FOR_COORD 346.9205 165.9382 1024.7812 he1_plansB
	SET_BLIP_ENTRY_EXIT he1_plansB 2415.3479 1123.9423 10.0

	CHANGE_BLIP_COLOUR he1_plansB YELLOW

	//Move guys, put them on routes...

ENDIF



IF he1_progress = 60

	
	
   	//IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
	  //	PRINT_HELP HEI1_28 
   	//ENDIF
	

   //	IF NOT IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_CAMERA
		//CLEAR_HELP

   //	ENDIF


	IF he1_evacuation = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 371.0459 161.5409 1018.9844 1.5 1.5 2.0 FALSE
		  // write_debug check

		  
			IF NOT IS_CHAR_DEAD he1_bystander4
	   		   CLEAR_CHAR_TASKS he1_bystander4
			  // SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander4 150.0
	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_bystander4 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   		ENDIF
			IF NOT IS_CHAR_DEAD he1_bystander3
	   		   CLEAR_CHAR_TASKS he1_bystander3
			    //  SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander3 150.0

	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_bystander3 379.2716 173.4771 1007.3971 PEDMOVE_WALK -2
	   		ENDIF
			IF NOT IS_CHAR_DEAD he1_bystander2
	   		   CLEAR_CHAR_TASKS he1_bystander2
			      //SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander2 150.0

	   		   TASK_FOLLOW_PATH_NODES_TO_COORD  he1_bystander2 370.9119 175.3713 1007.3971 PEDMOVE_WALK -2
	   		ENDIF
			IF NOT IS_CHAR_DEAD he1_bystander1
	   		   CLEAR_CHAR_TASKS he1_bystander1
			      //SET_FOLLOW_NODE_THRESHOLD_DISTANCE he1_bystander1 150.0

	   		   TASK_FOLLOW_PATH_NODES_TO_COORD he1_bystander1 370.8770 171.6468 1007.3971 PEDMOVE_WALK -2
	   		ENDIF

		  //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_OFFICE_FIRE_COUGHING_START

		   he1_evacuation = 1
		ENDIF
	ENDIF


	IF he1_evacuation = 1
			
		IF he1_by4hit = 0
			IF NOT IS_CHAR_DEAD he1_bystander4
			   	IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander4 scplayer
	   		   	CLEAR_CHAR_TASKS he1_bystander4
		     	TASK_SMART_FLEE_CHAR he1_bystander4 scplayer 100.0 -1	
				he1_by4hit = 1
				ENDIF
			ENDIF
		ENDIF
		IF he1_by3hit = 0
			IF NOT IS_CHAR_DEAD he1_bystander3
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander3 scplayer

	   		   	CLEAR_CHAR_TASKS he1_bystander3
		     	TASK_SMART_FLEE_CHAR he1_bystander3 scplayer 100.0 -1	
				he1_by3hit = 1
				ENDIF
		    ENDIF
		ENDIF
		IF he1_by2hit = 0
			IF NOT IS_CHAR_DEAD he1_bystander2
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander2 scplayer

	   		   	CLEAR_CHAR_TASKS he1_bystander2
		     	TASK_SMART_FLEE_CHAR he1_bystander2 scplayer 100.0 -1	
				he1_by2hit = 1
				ENDIF
		    ENDIF
		ENDIF
		IF he1_by1hit = 0
			IF NOT IS_CHAR_DEAD he1_bystander1
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR he1_bystander1 scplayer

		   		CLEAR_CHAR_TASKS he1_bystander1
		     	TASK_SMART_FLEE_CHAR he1_bystander1 scplayer 100.0 -1	
				he1_by1hit = 1
				ENDIF
		    ENDIF
		ENDIF
		

	ENDIF


 		 IF HAS_OBJECT_BEEN_PHOTOGRAPHED he1_plans
		 	he1_plansphotographed = 1
			REMOVE_BLIP he1_plansB
			CLEAR_PRINTS 
			CLEAR_HELP
			PRINT_NOW HEI1_25 6000 1
			//ADD_BLIP_FOR_COORD 2418.6816 1123.3524 9.8047 he1_completeB
			ADD_BLIP_FOR_COORD 2418.6816 1123.3524 9.8047 he1_completeB

			
			CHANGE_BLIP_COLOUR he1_completeB YELLOW 

			KILL_FX_SYSTEM he1_vendingsmoke1
			KILL_FX_SYSTEM he1_vendingsmoke2
			KILL_FX_SYSTEM he1_vendingsmoke3

		   	CREATE_FX_SYSTEM teargas 369.2483 162.3460 1026.7736 TRUE he1_beginsmoke1
			CREATE_FX_SYSTEM teargas 373.3976 162.6828 1026.7736 TRUE he1_beginsmoke2
			CREATE_FX_SYSTEM teargas 378.9187 163.1306 1026.7736 TRUE he1_beginsmoke3

			PLAY_FX_SYSTEM he1_beginsmoke1
			PLAY_FX_SYSTEM he1_beginsmoke2
			PLAY_FX_SYSTEM he1_beginsmoke3
			

			IF NOT IS_CHAR_DEAD he1_bystander1
	   			DELETE_CHAR he1_bystander1
			ENDIF

			IF NOT IS_CHAR_DEAD he1_bystander2
	   			DELETE_CHAR he1_bystander2
		
			ENDIF

			IF NOT IS_CHAR_DEAD he1_bystander3
	   			DELETE_CHAR he1_bystander3
			ENDIF

			IF NOT IS_CHAR_DEAD he1_bystander4
	   			DELETE_CHAR he1_bystander4
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop3
	   			DELETE_CHAR he1_pop3
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop4
	   			DELETE_CHAR he1_pop4
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop5
	   			DELETE_CHAR he1_pop5
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop6
	   			DELETE_CHAR he1_pop6
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop7
	   			DELETE_CHAR he1_pop7
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop8
	   			DELETE_CHAR he1_pop8
			ENDIF

			IF NOT IS_CHAR_DEAD he1_pop9
	   			DELETE_CHAR he1_pop9
			ENDIF


			he1_smokebegun = 1
			TIMERA = 0
			he1_progress = 99
			LOAD_SCENE 376.9717 192.0647 1013.1797

		 ENDIF
  ENDIF 

	


IF he1_plansphotographed = 1
	IF TIMERA > 2500
	
		REQUEST_MODEL lvemt1
		REQUEST_MODEL LVPD1
		REQUEST_MODEL wmybu
	
	
		WHILE NOT HAS_MODEL_LOADED lvemt1
			OR NOT HAS_MODEL_LOADED LVPD1
		   	OR NOT HAS_MODEL_LOADED wmybu
			WAIT 0
		ENDWHILE
	
		CREATE_CHAR PEDTYPE_MISSION1 wmybu 376.9717 192.0647 1013.1797 he1_snitch
		SET_CHAR_HEADING he1_snitch 266.7879
	  	SET_CHAR_DECISION_MAKER he1_snitch he1_civdm
		SET_CHAR_HAS_USED_ENTRY_EXIT he1_snitch 2415.3479 1123.9423 10.0
	
	
		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 377.2169 190.7318 1013.1797 he1_copconv
		SET_CHAR_HEADING he1_copconv 5.7879
		SET_CHAR_DECISION_MAKER he1_copconv he1_civdm
		GIVE_WEAPON_TO_CHAR he1_copconv WEAPONTYPE_PISTOL 30000
		SET_CHAR_HAS_USED_ENTRY_EXIT he1_copconv 2415.3479 1123.9423 10.0
	
		TASK_TURN_CHAR_TO_FACE_CHAR he1_snitch he1_copconv
	
		CREATE_CHAR PEDTYPE_MISSION1 lvpd1 377.9352 192.4556 1013.1797 he1_fireconv
		SET_CHAR_HEADING he1_fireconv 124.31
		SET_CHAR_DECISION_MAKER he1_fireconv he1_civdm
		SET_CHAR_HAS_USED_ENTRY_EXIT he1_fireconv 2415.3479 1123.9423 10.0
	
	
		MARK_MODEL_AS_NO_LONGER_NEEDED lvemt1

		he1_plansphotographed = 2
	ENDIF
	
ENDIF


IF he1_plansphotographed = 2
		CLEAR_PRINTS

		DO_FADE 1000 FADE_OUT
		//LOAD_SCENE 376.9717 192.0647 1013.1797
		SET_PLAYER_CONTROL player1 OFF 
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
	 
		//SET_AREA_VISIBLE 0

		CLEAR_PRINTS
	
					
		//SET_PLAYER_CONTROL player1 OFF 
		SWITCH_WIDESCREEN ON

		LOAD_SCENE 376.9717 192.0647 1013.1797

		SET_FIXED_CAMERA_POSITION 374.0174 190.0682 1014.4938 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 374.8688 190.5916 1014.4578 JUMP_CUT
		
		/*
		IF NOT IS_CHAR_DEAD he1_snitch
		IF NOT IS_CHAR_DEAD he1_copconv
				//TASK_LOOK_ABOUT he1_snitch -1
				//TASK_LOOK_ABOUT he1_pop6 -1
			   DISABLE_CHAR_SPEECH he1_snitch FALSE
			   DISABLE_CHAR_SPEECH he1_copconv FALSE

			   TASK_CHAT_WITH_CHAR he1_snitch he1_copconv  true true //ped0 will lead the chatting
			   TASK_CHAT_WITH_CHAR he1_copconv he1_snitch false true //ped1 will follow ped0 at chatting
		ENDIF
		ENDIF
		*/

		DO_FADE 2000 FADE_IN  // was 5000
	   	WHILE GET_FADING_STATUS
		 	WAIT 0
		ENDWHILE


		
		he1_plansphotographed = 3
		he1_ctskipsnitchneeded = 1
		TIMERA = 0
ENDIF

IF he1_plansphotographed = 3
	//IF TIMERA > 1000		// 1000
//		IF NOT IS_CHAR_DEAD he1_snitch
//		IF NOT IS_CHAR_DEAD he1_copconv
//				//TASK_LOOK_ABOUT he1_snitch -1
//				//TASK_LOOK_ABOUT he1_pop6 -1
//			   DISABLE_CHAR_SPEECH he1_snitch FALSE
//			   DISABLE_CHAR_SPEECH he1_copconv FALSE
//
//			   TASK_CHAT_WITH_CHAR he1_snitch he1_copconv  true true //ped0 will lead the chatting
//			   TASK_CHAT_WITH_CHAR he1_copconv he1_snitch false true //ped1 will follow ped0 at chatting
//		ENDIF
//		ENDIF

		CLEAR_PRINTS
		he1_counter = 15
		IF NOT IS_CHAR_DEAD he1_snitch
			
			  TASK_PLAY_ANIM he1_snitch endchat_01 PED 4.0 1 FALSE FALSE FALSE -1


		ENDIF


		//PRINT_NOW HEI1_42 4000 1
		he1_plansphotographed = 4
		TIMERA = 0
	//ENDIF
ENDIF

IF he1_plansphotographed = 4
	IF TIMERA > 2000
	  		he1_plansphotographed = 5
		TIMERA = 0
	ENDIF
ENDIF




IF he1_plansphotographed = 5
		
		he1_ctskipsnitchneeded = 0
   
		IF NOT IS_CHAR_DEAD he1_snitch
		IF NOT IS_CHAR_DEAD he1_copconv
			  CLEAR_CHAR_TASKS he1_snitch
			  CLEAR_CHAR_TASKS he1_copconv
		ENDIF
		ENDIF


		DO_FADE 500 FADE_OUT 
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
   

   
		SET_PLAYER_CONTROL player1 ON
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		SWITCH_WIDESCREEN OFF 
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE



		he1_plansphotographed = 10
		DELETE_CHAR he1_snitch

   

		IF NOT IS_CHAR_DEAD he1_guard3
		   SET_CHAR_COORDINATES he1_guard3 378.2106 154.3860 1022.7819 
		   SET_CHAR_HEADING he1_guard3 312.89
		   CLEAR_CHAR_TASKS	he1_guard3
		   
		   OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_STAY_IN_SAME_PLACE -1 FALSE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_guard3 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq

		ENDIF

 
	   		

		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 371.6575 153.1287 1020.7961 he1_swatbase1
		SET_CHAR_HEADING he1_swatbase1 264.7879
		SET_CHAR_SHOOT_RATE he1_swatbase1 60

		SET_CHAR_DECISION_MAKER he1_swatbase1 he1_emptydm
		GIVE_WEAPON_TO_CHAR he1_swatbase1 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase1 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase1 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq


		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 378.6946 163.6932 1018.9844 he1_swatbase2
		SET_CHAR_HEADING he1_swatbase2 91.89
		SET_CHAR_DECISION_MAKER he1_swatbase2 he1_emptydm
		GIVE_WEAPON_TO_CHAR he1_swatbase2 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase2 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase2 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq

		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 371.9029 164.5795 1013.1797 he1_swatbase3
		SET_CHAR_HEADING he1_swatbase3 195.89
		SET_CHAR_DECISION_MAKER he1_swatbase3 he1_emptydm
		GIVE_WEAPON_TO_CHAR he1_swatbase3 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase3 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
		  	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase3 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq

		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 371.2767 154.0267 1014.9844 he1_swatbase4
		SET_CHAR_HEADING he1_swatbase4 195.89
		SET_CHAR_SHOOT_RATE he1_swatbase4 60

		SET_CHAR_DECISION_MAKER he1_swatbase4 he1_emptydm
		GIVE_WEAPON_TO_CHAR he1_swatbase4 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase4 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
		  	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase4 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq

		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 378.0627 152.9613 1011.1953 he1_swatbase5
		SET_CHAR_HEADING he1_swatbase5 285.89
		SET_CHAR_DECISION_MAKER he1_swatbase5 he1_emptydm
		GIVE_WEAPON_TO_CHAR he1_swatbase5 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase5 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_KINDA_STAY_IN_SAME_PLACE -1 FALSE
		  	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase5 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq


		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 372.2917 162.8527 1007.3893 he1_swatbase6
		SET_CHAR_HEADING he1_swatbase6 195.89
		SET_CHAR_DECISION_MAKER he1_swatbase6 he1_emptydm
		GIVE_WEAPON_TO_CHAR he1_swatbase6 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase6 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
		  	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase6 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq


		// New

		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 379.1144 153.0811 1011.1953 he1_swatbase9
		SET_CHAR_HEADING he1_swatbase9 4.89
		SET_CHAR_DECISION_MAKER he1_swatbase9 he1_emptydm
		TASK_TOGGLE_DUCK he1_swatbase9 TRUE
		SET_CHAR_SHOOT_RATE he1_swatbase9 50
		SET_CHAR_HEALTH he1_swatbase9 120


		GIVE_WEAPON_TO_CHAR he1_swatbase9 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase9 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_STAY_IN_SAME_PLACE -1 TRUE
		  	TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase9 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq







		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 369.8178 162.4074 1018.9767  he1_swatbase8	// crouching by doorway
		SET_CHAR_HEADING he1_swatbase8 217.89
		SET_CHAR_SHOOT_RATE he1_swatbase8 50
		SET_CHAR_HEALTH he1_swatbase8 120


		SET_CHAR_DECISION_MAKER he1_swatbase8 he1_emptydm
	   //	TASK_TOGGLE_DUCK he1_swatbase8 TRUE


		GIVE_WEAPON_TO_CHAR he1_swatbase8 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase8 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_STAY_IN_SAME_PLACE -1 TRUE
		  	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 70
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase8 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq







		CREATE_CHAR PEDTYPE_MISSION1 LVPD1 370.1607 179.6097 1007.3906 he1_swatbase7	// bottom floor by glass area
		SET_CHAR_HEADING he1_swatbase7 170.89
		SET_CHAR_SHOOT_RATE he1_swatbase7 50
		SET_CHAR_HEALTH he1_swatbase7 120


		SET_CHAR_DECISION_MAKER he1_swatbase7 he1_emptydm
		TASK_TOGGLE_DUCK he1_swatbase7 TRUE


		GIVE_WEAPON_TO_CHAR he1_swatbase7 WEAPONTYPE_PISTOL 30000
			SET_CHAR_HAS_USED_ENTRY_EXIT he1_swatbase7 2415.3479 1123.9423 10.0


			OPEN_SEQUENCE_TASK he1_swatseq


		   	TASK_STAY_IN_SAME_PLACE -1 TRUE
		  	TASK_KILL_CHAR_ON_FOOT -1 scplayer 
			CLOSE_SEQUENCE_TASK he1_swatseq
			PERFORM_SEQUENCE_TASK he1_swatbase7 he1_swatseq
			CLEAR_SEQUENCE_TASK he1_swatseq


	 
		IF NOT IS_CHAR_DEAD he1_guard2
	  	   CLEAR_CHAR_TASKS he1_guard2
		   SET_CHAR_COORDINATES he1_guard2 375.0372 177.4968 1007.3906
		   SET_CHAR_HEADING he1_guard2 193.0
		   TASK_STAY_IN_SAME_PLACE he1_guard2 TRUE
		   TASK_KILL_CHAR_ON_FOOT he1_guard2 scplayer 
		ENDIF

		IF NOT IS_CHAR_DEAD he1_guard1
	  	   CLEAR_CHAR_TASKS he1_guard1
		   SET_CHAR_COORDINATES he1_guard1 385.5123 174.3110 1007.3893
		   SET_CHAR_HEADING he1_guard1 82.0
		   TASK_KINDA_STAY_IN_SAME_PLACE he1_guard1 TRUE
		   TASK_KILL_CHAR_ON_FOOT he1_guard1 scplayer 
		ENDIF

		IF NOT IS_CHAR_DEAD he1_copconv
	  	   CLEAR_CHAR_TASKS he1_copconv
		   TASK_KILL_CHAR_ON_FOOT he1_copconv scplayer 
		ENDIF



		CLEAR_PRINTS 
		PRINT_NOW HEI1_46 6000 1
		ALTER_WANTED_LEVEL_NO_DROP player1 2

		REQUEST_MODEL bravura
		WHILE NOT HAS_MODEL_LOADED BRAVURA
			WAIT 0
		ENDWHILE

		CREATE_CAR BRAVURA 2423.7327 1120.2732 9.6719 he1_parkedescape
		SET_CAR_HEADING he1_parkedescape 179.4729
		MARK_MODEL_AS_NO_LONGER_NEEDED BRAVURA




ENDIF




	
IF he1_smokebegun = 1
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 369.2483 162.3460 1024.7736 2.0 2.0 2.0 FALSE
		    CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 0.0 2.0 TRUE he1_smoke1 //directly above
			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 2.0 1.8 TRUE he1_smoke2 // in front
			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 0.0 -2.0 2.0 TRUE he1_smoke3 // behind
			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 1.0 5.0 1.5 TRUE he1_smoke4  // 5m in front
			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer -1.0 8.0 1.8 TRUE he1_smoke5  // 8m in front
			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 2.0 0.0 1.5 TRUE he1_smoke6  // side
			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer -2.0 0.0 1.5 TRUE he1_smoke7 //side

			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer -2.0 2.0 1.5 TRUE he1_smoke8 //front diag
			CREATE_FX_SYSTEM_ON_CHAR teargas scplayer 2.0 2.0 1.5 TRUE he1_smoke9 //front diag
			SET_HEATHAZE_EFFECT TRUE
			//SET_DARKNESS_EFFECT TRUE -1

			//SET_DARKNESS_EFFECT TRUE
			//FORCE_WEATHER_NOW WEATHER_FOGGY_SF 


			PLAY_FX_SYSTEM he1_smoke1
			PLAY_FX_SYSTEM he1_smoke2
			PLAY_FX_SYSTEM he1_smoke3
			PLAY_FX_SYSTEM he1_smoke4
			PLAY_FX_SYSTEM he1_smoke5
			PLAY_FX_SYSTEM he1_smoke6
			PLAY_FX_SYSTEM he1_smoke7
			PLAY_FX_SYSTEM he1_smoke8
			PLAY_FX_SYSTEM he1_smoke9
			KILL_FX_SYSTEM he1_beginsmoke1
			KILL_FX_SYSTEM he1_beginsmoke2
			KILL_FX_SYSTEM he1_beginsmoke3

	
			he1_smokebegun = 2
	
	ENDIF
ENDIF






IF he1_plansphotographed = 10

 
	/*

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2473.5042 1123.1412 9.8203 3.0 3.0 3.0 FALSE
		GOTO mission_heist1_passed
	ENDIF

	*/

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2418.6816 1123.3524 9.8047 5.0 5.0 5.0 FALSE
		
		//RELEASE_WEATHER
		SET_HEATHAZE_EFFECT FALSE
	   //	SET_DARKNESS_EFFECT FALSE -1


		KILL_FX_SYSTEM he1_beginsmoke1
		KILL_FX_SYSTEM he1_beginsmoke2
		KILL_FX_SYSTEM he1_beginsmoke3

		KILL_FX_SYSTEM he1_smoke1
		KILL_FX_SYSTEM he1_smoke2
		KILL_FX_SYSTEM he1_smoke3						  
		KILL_FX_SYSTEM he1_smoke4
		KILL_FX_SYSTEM he1_smoke5
		KILL_FX_SYSTEM he1_smoke6
		KILL_FX_SYSTEM he1_smoke7
		KILL_FX_SYSTEM he1_smoke8
		KILL_FX_SYSTEM he1_smoke9
		he1_plansphotographed = 11
		REMOVE_BLIP he1_completeB
		ADD_BLIP_FOR_COORD 1918.5901 958.3391 9.8203 he1_finalB
		CLEAR_PRINTS 
		PRINT_NOW HEI1_29 6000 1
		ALTER_WANTED_LEVEL_NO_DROP player1 2
		he1_wantedfix = 1
		timerb = 0
		MARK_MODEL_AS_NO_LONGER_NEEDED wmysgrd
		MARK_MODEL_AS_NO_LONGER_NEEDED OFOST
		//GOTO mission_heist1_passed

	ENDIF


ENDIF



IF he1_wantedfix = 1
	
   IF timerb > 4000
	
	  ALTER_WANTED_LEVEL_NO_DROP player1 4
	  he1_wantedfix = 2
	  
   ENDIF

ENDIF


IF he1_plansphotographed = 11
	REQUEST_MODEL TRIADA
	REQUEST_MODEL WASHING
	REQUEST_MODEL TRIADB
	//LOAD_SPECIAL_CHARACTER 1 WUZIMU
	
	WHILE NOT HAS_MODEL_LOADED TRIADB
		 OR NOT HAS_MODEL_LOADED TRIADA
		 OR NOT HAS_MODEL_LOADED WASHING
		 WAIT 0
	ENDWHILE

	 he1_plansphotographed = 13
	//IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1988.7454 1033.1350 9.8203 5.0 5.0 5.0 TRUE
	  //		GOTO mission_heist1_passed
	//ENDIF
	he1_finalsequence = 9
ENDIF


IF he1_finalsequence = 9

//	SET_PLAYER_CONTROL player1 ON
//
//	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1918.5901 958.3391 9.8203 4.0 4.0 2.0 TRUE
//	SET_PLAYER_CONTROL player1 OFF
//	
//		
//	//IF IS_CHAR_STOPPED scplayer
//		CREATE_CAR WASHING 1902.1685 974.9742 9.8203 he1_wuzicar
//		SET_CAR_HEADING he1_wuzicar 197.2609 
//		CHANGE_CAR_COLOUR he1_wuzicar 4 2
//
//		IF NOT IS_CAR_DEAD he1_wuzicar
//			CREATE_CHAR_INSIDE_CAR he1_wuzicar PEDTYPE_MISSION2 TRIADA he1_wuzidriver
//
//			CREATE_CHAR_AS_PASSENGER he1_wuzicar PEDTYPE_MISSION2 TRIADB 1 he1_wuzi
//		ENDIF
//		//CREATE_CHAR_PEDTYPE_GANG_GROVE SPECIAL01 1903.2869 962.4689 9.8203 he1_wuzi
//		//SET_CHAR_HEADING he1_wuzi 170.0
//		he1_finalsequence = 15
//
//	ENDIF
//
//   //	ENDIF

	GET_AREA_VISIBLE he1_visible

	IF he1_visible = 0
		 IF NOT IS_MINIGAME_IN_PROGRESS	
				SET_PLAYER_CONTROL player1 ON
		  ENDIF
	ENDIF

	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1918.5901 958.3391 9.8203 4.0 4.0 2.0 TRUE

			IF IS_CHAR_IN_ANY_CAR scplayer
			
				SET_PLAYER_CONTROL player1 OFF
				IF IS_CHAR_STOPPED scplayer

		   

				CREATE_CAR WASHING 1902.1685 974.9742 9.8203 he1_wuzicar
				SET_CAR_HEADING he1_wuzicar 197.2609 
				CHANGE_CAR_COLOUR he1_wuzicar 4 2

				IF NOT IS_CAR_DEAD he1_wuzicar
					CREATE_CHAR_INSIDE_CAR he1_wuzicar PEDTYPE_MISSION2 TRIADA he1_wuzidriver

					CREATE_CHAR_AS_PASSENGER he1_wuzicar PEDTYPE_MISSION2 TRIADB 1 he1_wuzi
				ENDIF
				he1_finalsequence = 15
				ENDIF
			


			ELSE

				SET_PLAYER_CONTROL player1 OFF
			
		   

				CREATE_CAR WASHING 1902.1685 974.9742 9.8203 he1_wuzicar
				SET_CAR_HEADING he1_wuzicar 197.2609 
				CHANGE_CAR_COLOUR he1_wuzicar 4 2

				IF NOT IS_CAR_DEAD he1_wuzicar
					CREATE_CHAR_INSIDE_CAR he1_wuzicar PEDTYPE_MISSION2 TRIADA he1_wuzidriver

					CREATE_CHAR_AS_PASSENGER he1_wuzicar PEDTYPE_MISSION2 TRIADB 1 he1_wuzi
				ENDIF
				he1_finalsequence = 15


			ENDIF

	   ENDIF // locate



ENDIF // he1_finalsequence = 9 condition check



IF he1_finalsequence = 15
	SET_PLAYER_CONTROL player1 OFF 
	SET_EVERYONE_IGNORE_PLAYER player1 TRUE

	DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
      		WAIT 0
		ENDWHILE 
   

	
	CLEAR_PRINTS

	MARK_CAR_AS_NO_LONGER_NEEDED he1_parkedescape
	CLEAR_AREA 1913.6818 962.0125 9.8203 60.0 TRUE
	CLEAR_AREA 1903.4680 967.1692 9.8127 60.0 TRUE
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION 1922.2339 951.8628 12.2282 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 1921.7146 952.7101 12.1171 JUMP_CUT

	DO_FADE 1000 FADE_IN

		WHILE GET_FADING_STATUS
      		WAIT 0
		ENDWHILE 

	he1_finalsequence = 17
	timera = 0
	CLEAR_CHAR_TASKS scplayer
	
	IF IS_CHAR_IN_ANY_CAR scplayer
   		OPEN_SEQUENCE_TASK he1_playerseq
   			TASK_LEAVE_ANY_CAR -1		
			TASK_GO_STRAIGHT_TO_COORD -1 1913.6818 962.0125 9.8203 PEDMOVE_WALK -1
		CLOSE_SEQUENCE_TASK he1_playerseq
		PERFORM_SEQUENCE_TASK scplayer he1_playerseq
		CLEAR_SEQUENCE_TASK he1_playerseq
	ELSE
		OPEN_SEQUENCE_TASK he1_playerseq
   		 
			TASK_GO_STRAIGHT_TO_COORD -1 1913.6818 962.0125 9.8203 PEDMOVE_WALK -1
		CLOSE_SEQUENCE_TASK he1_playerseq
		PERFORM_SEQUENCE_TASK scplayer he1_playerseq
		CLEAR_SEQUENCE_TASK he1_playerseq

	ENDIF

ENDIF


IF he1_finalsequence = 17
	IF timera > 1000
		//he1_finalskip = 1
		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_START

		he1_finalsequence = 20
	ENDIF
ENDIF

IF he1_finalsequence = 20
	// IF timerb > 3000
	  //	he1_finalskip = 1 
	// ENDIF

	 IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.02 FALSE
			SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.02 FALSE
	 ENDIF
	 //IF SLIDE_OBJECT fourdragons_door 1903.383 967.62 15.438 0.0 0.0 0.02 FALSE

	 IF timera > 4000
	 	he1_finalsequence = 30
		IF NOT IS_CHAR_DEAD he1_wuzidriver
			IF NOT IS_CAR_DEAD he1_wuzicar
				TASK_CAR_DRIVE_TO_COORD he1_wuzidriver he1_wuzicar 1909.0400 959.7415 9.8203 7.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
				
				
				SET_FIXED_CAMERA_POSITION 1901.5867 965.5023 11.8636  0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1902.5381 965.2460 11.6931  JUMP_CUT
				SET_CHAR_COORDINATES scplayer 1913.6818 962.0125 9.8203
				SET_CHAR_HEADING scplayer 100.3
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_STOP
				CLEAR_CHAR_TASKS scplayer
				//TASK_GO_STRAIGHT_TO_COORD scplayer 1907.8975 963.2145 9.8203 PEDMOVE_WALK -1
				  TASK_GO_STRAIGHT_TO_COORD scplayer 1909.8872 962.3391 9.8203 PEDMOVE_WALK -1
				timera = 0
			ENDIF

		ENDIF

	 ENDIF
ENDIF

IF he1_finalsequence = 30
  	
  	IF NOT IS_CAR_DEAD he1_wuzicar
		//WRITE_DEBUG check30
  		IF LOCATE_CAR_2D he1_wuzicar 1909.0400 959.7415 3.0 3.0	FALSE
			he1_finalsequence = 35
			IF NOT IS_CHAR_DEAD he1_wuzi
				TASK_LOOK_AT_CHAR he1_wuzi scplayer	-1
			ENDIF
			//WRITE_DEBUG check35
			//PRINT_NOW HEI1_30 5000 1
		  //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_START

			he1_counter = 19
			timera = 0
		ENDIF
		IF timera > 10000
		   he1_finalsequence = 35
		  // REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_START
		   WRITE_DEBUG test

		   timera = 0
		ENDIF

	ENDIF
ENDIF

IF he1_finalsequence = 35

   //	IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	 // 		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	//ENDIF
	
	//IF SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	IF timera > 3000
		he1_counter = 20

		he1_finalsequence = 36
		timera = 0
	  //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_STOP
	ENDIF

ENDIF

IF he1_finalsequence = 36
   //	IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
	 // 		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.02 FALSE
   //	ENDIF
   //	IF timera > 2000
		//PRINT_NOW HEI1_31 4000 1
		
		IF timera > 1000 //4000
		IF NOT IS_CAR_DEAD he1_wuzicar
			IF NOT IS_CHAR_DEAD he1_wuzidriver
				CLEAR_CHAR_TASKS he1_wuzidriver
				he1_finalsequence = 37					  //			 1915.0400 940.7415 9.8203
			   		TASK_CAR_DRIVE_TO_COORD he1_wuzidriver he1_wuzicar 1905.2789 934.1620 9.8352 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
				timera = 0
			   //	he1_counter = 20
				IF NOT IS_CHAR_DEAD he1_wuzi
					TASK_LOOK_AT_CHAR scplayer he1_wuzi -1
				ENDIF

			ENDIF
		ENDIF
		ENDIF

	//ENDIF
ENDIF


IF he1_finalsequence = 37
	IF timera > 1000

		IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.04 FALSE
	  		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.04 FALSE
		ENDIF
		IF he1_doorsound = 0
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_START
			he1_doorsound = 1
		ENDIF

	
	ENDIF

  
	IF timera > 3000
		he1_finalsequence = 40
	ENDIF
ENDIF



IF he1_finalsequence = 40
	IF NOT SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.04 FALSE
	  		SLIDE_OBJECT fourdragons_door 1903.383 967.62 11.438 0.0 0.0 0.04 FALSE
	ENDIF
	he1_finalskip = 0
	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_SHUTTER_DOOR_STOP


	
	IF NOT IS_CHAR_DEAD he1_wuzi
		CLEAR_CHAR_TASKS_IMMEDIATELY he1_wuzi
		DELETE_CHAR he1_wuzi
	ENDIF

	IF NOT IS_CHAR_DEAD he1_wuzidriver
		CLEAR_CHAR_TASKS_IMMEDIATELY he1_wuzidriver
		DELETE_CHAR he1_wuzidriver
	ENDIF
	DELETE_CAR he1_wuzicar

	


	SWITCH_WIDESCREEN OFF
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	RESTORE_CAMERA_JUMPCUT				   
	SET_CAMERA_BEHIND_PLAYER
	SET_PLAYER_CONTROL player1 ON
		 SET_EVERYONE_IGNORE_PLAYER player1 FALSE

	SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.82 11.438
	SET_OBJECT_HEADING fourdragons_door 0.0

	he1_finalsequence = 45
	CLEAR_PRINTS
	GOTO mission_heist1_passed

ENDIF
	


GOTO heist1_main_mission_loop


he1_guardresponse:
IF NOT IS_CHAR_DEAD he1_guard3
	CLEAR_CHAR_TASKS he1_guard3
	TASK_KILL_CHAR_ON_FOOT he1_guard3 scplayer
ENDIF

RETURN

he1_kickout:

he1_beenkickedout = 1
PRINT_BIG ( M_FAIL ) 8000 1
timerb = 0

RETURN


 // **************************************** Mission heist1 failed ***********************

mission_heist1_failed:
IF NOT he1_beenkickedout = 1
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
ENDIF	
RETURN



   

// **************************************** mission heist1 passed ************************

mission_heist1_passed:

flag_heist_mission_counter ++ //Used to terminate this mission loop in the main script. These variables will be set up in the main.sc
//REGISTER_MISSION_PASSED ( HOOD_3 ) //Used in the stats 
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 10 5000 1
AWARD_PLAYER_MISSION_RESPECT 10
REGISTER_MISSION_PASSED  ( HEIST_1 )
PLAYER_MADE_PROGRESS 1
SET_INT_STAT PASSED_HEIST1 1

PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL PLAYER1

RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_heist1:
//RELEASE_WEATHER



SWITCH_ENTRY_EXIT paper FALSE

disable_mod_garage = 0



CLEAR_MISSION_AUDIO 3


//SWITCH_PED_ROADS_OFF 1996.4890 1519.1196 10.0625 2003.8010 1565.6416 17.3672


//SWITCH_PED_ROADS_OFF 2001.2888 1532.2842 8.0 2027.0776 1553.2673 13.0



IF NOT IS_CHAR_DEAD scplayer
	CLEAR_CHAR_TASKS scplayer
ENDIF

CLEAR_WANTED_LEVEL player1

IF DOES_OBJECT_EXIST fourdragons_door

SET_OBJECT_COORDINATES fourdragons_door 1903.383 967.62 11.438
SET_OBJECT_HEADING fourdragons_door 0.0

ENDIF


SET_RADAR_ZOOM 0

//HIDE_ALL_FRONTEND_BLIPS FALSE


//SET_CHAR_AMMO scplayer WEAPONTYPE_CAMERA 0 
IF NOT IS_CHAR_DEAD scplayer
   //	REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_CAMERA
ENDIF

REMOVE_BLIP he1_cameraB
REMOVE_BLIP he1_officesB
REMOVE_BLIP he1_receptB
REMOVE_BLIP he1_plansB
REMOVE_BLIP he1_depositoryB
//REMOVE_BLIP he1_vending1B
REMOVE_BLIP he1_vending2B
REMOVE_BLIP he1_completeB
REMOVE_BLIP he1_camreserveB
REMOVE_BLIP he1_finalB	
REMOVE_BLIP he1_stairwellB
he1_tempint = 1
WHILE he1_tempint < 6
	REMOVE_BLIP he1_touristB[he1_tempint]
   	he1_tempint ++
ENDWHILE


REMOVE_PICKUP he1_cameraP
//REMOVE_PICKUP he1_health1
//REMOVE_PICKUP he1_health2


REMOVE_ALL_SCRIPT_FIRES

KILL_FX_SYSTEM he1_beginsmoke1
KILL_FX_SYSTEM he1_beginsmoke2
KILL_FX_SYSTEM he1_beginsmoke3
KILL_FX_SYSTEM he1_vendingsmoke1
KILL_FX_SYSTEM he1_vendingsmoke2
KILL_FX_SYSTEM he1_vendingsmoke3


KILL_FX_SYSTEM he1_smoke1
KILL_FX_SYSTEM he1_smoke2
KILL_FX_SYSTEM he1_smoke3
KILL_FX_SYSTEM he1_smoke4
KILL_FX_SYSTEM he1_smoke5
KILL_FX_SYSTEM he1_smoke6
KILL_FX_SYSTEM he1_smoke7
KILL_FX_SYSTEM he1_smoke8
KILL_FX_SYSTEM he1_smoke9

//KILL_FX_SYSTEM he1_dummyfire1
//KILL_FX_SYSTEM he1_dummyfire2
				   

SET_HEATHAZE_EFFECT FALSE	
//SET_DARKNESS_EFFECT FALSE
//SET_DARKNESS_EFFECT FALSE -1

																	 

MARK_MODEL_AS_NO_LONGER_NEEDED OMORI
MARK_MODEL_AS_NO_LONGER_NEEDED OMOST

MARK_MODEL_AS_NO_LONGER_NEEDED CAMERA
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN

MARK_MODEL_AS_NO_LONGER_NEEDED BFYRI
MARK_MODEL_AS_NO_LONGER_NEEDED OMYRI
MARK_MODEL_AS_NO_LONGER_NEEDED OFOST

//MARK_MODEL_AS_NO_LONGER_NEEDED JOURNEY
MARK_MODEL_AS_NO_LONGER_NEEDED bravura
//MARK_MODEL_AS_NO_LONGER_NEEDED FIRETRUK

MARK_MODEL_AS_NO_LONGER_NEEDED wmysgrd
MARK_MODEL_AS_NO_LONGER_NEEDED wmosci
MARK_MODEL_AS_NO_LONGER_NEEDED wmybu
MARK_MODEL_AS_NO_LONGER_NEEDED bmybu

MARK_MODEL_AS_NO_LONGER_NEEDED LVPD1
MARK_MODEL_AS_NO_LONGER_NEEDED lvemt1

//UNLOAD_SPECIAL_CHARACTER 1
MARK_MODEL_AS_NO_LONGER_NEEDED TRIADA
MARK_MODEL_AS_NO_LONGER_NEEDED TRIADB

MARK_MODEL_AS_NO_LONGER_NEEDED WASHING

REMOVE_ANIMATION CAMERA

IF he1_beenkickedout = 1
   //	OVERRIDE_NEXT_RESTART 2419.6057 1123.5138 9.8203 264.0
	OVERRIDE_NEXT_RESTART 2337.0833 2453.8018 13.9765 90.7643 

	
	FORCE_DEATH_RESTART
   //	PRINT_BIG ( M_FAIL ) 8000 1
   //	PRINT_NOW HEI1_50 8000 1
ENDIF

GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
flag_player_on_mission = 0

MISSION_HAS_FINISHED
//UNLOAD_SPECIAL_CHARACTER 1
RETURN

 
}






/*

[HEI1_1:HEIST1]
~s~Security's strict in the ~y~planning department~s~. Don't start trouble!

[HEI1_6:HEIST1]
~s~You can't photo those plans with the guard around! You need to divert his attention.

[HEI1_7:HEIST1]
~s~There's a ~y~document depository~s~ downstairs. Head down to see if you can cause a distraction

[HEI1_9:HEIST1]
~s~This is a civic building. No visible guns or you'll be ejected from the premises

[HEI1_10:HEIST1]
~s~Those old ~g~air con units~s~ are decrepit. Destroy them anyway you can.

[HEI1_11:HEIST1]
~s~If you need another ~g~camera~s~ or film, head back to the casino area and persuade a tourist to part with theirs!

[HEI1_20:HEIST1]
~w~( wheep! wheep! )~s~ That's it! That fire alarm ought to occupy those guards.

[HEI1_21:HEIST1]
~w~Guard: Everybody out! Keep calm! 

[HEI1_22:HEIST1]
~s~Okay! They're busy with the evacuation just now. Get upstairs and photograph those ~y~plans~s~ on the wall.

[HEI1_25:HEIST1]
~s~Great! You've got a photo of those plans! Get out of the ~y~building~s~ before you're caught.

[HEI1_26:HEIST1]
~w~Tourist: Ah! You take a picture! You take a picture!

[HEI1_28:HEIST1]
Hold the R1 button and press ~o~ to take a photograph

[HEI1_29:HEIST1]
~s~You're still wanted by the police! Get that camera film back to ~y~Wuzi's casino~s~ before getting arrested.

[HEI1_30:HEIST1]
Carl? I can detect the smell of smoke in the air! Your doing I trust?

[HEI1_31:HEIST1]
Let us just hope your photography was as focused as your ability to cause mayhem!

[HEI1_32:HEIST1]
~w~Tourist: Why you injure us? Our friends go to copshop! We get you too!

[HEI1_33:HEIST1]
~s~The ~y~stairwell~s~ is now accessible. Go up to find the location of the plans.

[HEI1_34:HEIST1]
~s~The ~y~plans~s~ you need to photograph are on the top floor.

[HEI1_35:HEIST1]
~s~There are plenty of ~r~tourists~s~ on that ship. Take a camera from them by force.

[HEI1_39:HEIST1]
~s~Good! Get that ~g~camera~s~ picked up.

[HEI1_40:HEIST1]
~s~Good job! Head to the ~y~planning department~s~ building.

[HEI1_41:HEIST1]
~s~You need a camera to get a copy of those plans. Grab one from some ~r~tourists~s~ at the strip. 

[HEI1_42:HEIST1]
~w~I heard some noise from next door! Something definitely got smashed up and then the fire alarm went nuts!

[HEI1_43:HEIST1]
~w~Don't worry sir. We suspect foul play. The perpetrator will be caught!

[HEI1_44:HEIST1]
~w~Guard: Put that weapon away or you'll be apprehended!

[HEI1_45:HEIST1]
~w~Guard: Thank you for your co-operation, sir.

[HEI1_46:HEIST1]
~s~The cops can't shut the bottom doors due to the fire! Use your weapons if you need to.

[HEI1_48:HEIST1]
~w~Guard: No photography or weaponry allowed on this floor. Put it away, please!

[HEI1_49:HEIST1]
~w~Guard: This is your final warning sir! You have five seconds to put that item away!

[HEI1_50:HEIST1]
~r~You were told that this is a low-profile job and you were showing off your weaponry!

[HEI1_51:HEIST1]
~w~Guard: Lock the bottom doors and get the cops! This punk is up to no good!

[HEI1_52:HEIST1]
~r~That's just great! We should have distracted the guard before taking any photographs!

[HEIQ1:HEIST1]
~w~Can I help you sir?

[HEIQ2:HEIST1]
~w~OK sir. You are aware that reproduction of such plans is prohibited?

[HEIQ1Y:HEIST1]
~w~Yes please, I'd like to look at some plans for the Mafia casino.

[HEIQ1N:HEIST1]
~w~Erm... no!

[HEIQ2Y:HEIST1]
~w~Yes, I'm only after some reference. I'm building a scale model for my uncle's birthday. He's keen on blackjack.

[HEIQ2N:HEIST1]
~w~No, I didn't know that. Why?

[HEIX2Y:HEIST1]
~w~Of course. The owners wouldn't want to take a gamble on that happening!

[HEIX2N:HEIST1]
~w~No! I'm some sort of dumbass! I thought I could waltz in and take the plans home to peruse at my leisure! 

[HEIH:HEIST1]
Press the left directional button to answer NO or the right directional button for YES

[HEIH2:HEIST1]
Move close to the receptionist and press the R1 button to restart conversation

[HEIQ3:HEIST1]
~w~That's lovely sir! The plans for that building are located on the top floor. I've buzzed open that stairwell door.

[HEIX1:HEIST1]
~w~I'm rather busy sir. Come to me if you change your mind.

[HEIX2:HEIST1]
~w~Well, we don't want to be party to preparation of a daring raid on the count room! Does sir understand?

[HEIQ4:HEIST1]
~w~Very witty sir. You'll find the plans on the top floor. I will buzz open the stairwell door beside the entrance

[HEIQ5:HEIST1]
~w~I'm sorry sir, I can't make it any clearer. Alert me when you've thought it over.

[HEIX3]
~w~Sir, I take it you don't want to talk anymore? Alert me if you change your mind.

*/