MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************  SWEET7  ******************************************
// ********************************  Grave Misfortune  *************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME SWEET7

// Mission start stuff

GOSUB mission_start_sweet7

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_sweet7_failed
ENDIF

GOSUB mission_cleanup_sweet7

MISSION_END

{ 
// Variables for mission

LVAR_INT sw7_visible
LVAR_INT gan1_gzs gan2_gzs // gang_zone_strengths


//cutscene stuff
LVAR_INT sw7_ctskip_needed
LVAR_INT sw7_newctskip_needed


//Mission blips

LVAR_INT sw7_sweetB
LVAR_INT sw7_kaneB
LVAR_INT sw7_hearseB
LVAR_INT sw7_cementB
LVAR_INT sw7_pursuitcarB
LVAR_INT sw7_blipswap

LVAR_INT sw7_protector1B
LVAR_INT sw7_protector2B
LVAR_INT sw7_protector3B
LVAR_INT sw7_protector4B
LVAR_INT sw7_protector5B
LVAR_INT sw7_protector6B

LVAR_INT sw7_reinforce1B
LVAR_INT sw7_reinforce2B

//Condition flags
LVAR_INT sw7_missionprogress
LVAR_INT sw7_chauffeurkilltask
LVAR_INT sw7_nextprompt

LVAR_INT sw7_protector1dead
LVAR_INT sw7_protector2dead
LVAR_INT sw7_protector3dead

LVAR_INT sw7_kanemove1
LVAR_INT sw7_goonmove1
LVAR_INT sw7_begingangfight

LVAR_INT sw7_grove1fight
LVAR_INT sw7_grove2fight
LVAR_INT sw7_sweetfight
LVAR_INT sweet_onroute
LVAR_INT sw7_grove1onroute
LVAR_INT sw7_grove2onroute

LVAR_INT sw7_cockedandloaded


LVAR_INT sw7_endanims
LVAR_INT sw7_shotanim
LVAR_INT sw7_checkkane

LVAR_INT sw7_cardamprompt


LVAR_INT sw7_finalkaneexit
LVAR_INT sw7_finalchauffexit
LVAR_INT sw7_kanearrival


//Vehicles
LVAR_INT sw7_kanehearse
LVAR_INT sw7_escapehearse
LVAR_INT sw7_sweethearse
LVAR_INT sw7_pursuitcar
LVAR_INT sw7_hearsehealth

//Peds and sequences
LVAR_INT sw7_grove1
LVAR_INT sw7_grove2
																			    
LVAR_INT sw7_sweetjumpseq
LVAR_INT sw7_sweetseqA
LVAR_INT sw7_sweetfinalseq

LVAR_INT sw7_sbeginseq
LVAR_INT sw7_mopupseq


LVAR_INT sw7_grove1seqA
LVAR_INT sw7_g1beginseq

LVAR_INT sw7_grove2seqA
LVAR_INT sw7_g2beginseq

LVAR_INT sw7_goondm
LVAR_INT sw7_emptydm
LVAR_INT sw7_toughdm
LVAR_INT sw7_normaldm

LVAR_INT sw7_preach
LVAR_INT sw7_greetinface

LVAR_INT sw7_kane
LVAR_INT sw7_kane1seq
LVAR_INT sw7_kaneseqA


LVAR_INT sw7_kanechauffeur
LVAR_INT sw7_chauffeur1seq
LVAR_INT sw7_chauffeur3seq
LVAR_INT sw7_chauffattackseq

LVAR_INT sw7_protector1
LVAR_INT sw7_protector1seqA


LVAR_INT sw7_protector2
LVAR_INT sw7_protector2seqA

LVAR_INT sw7_protector3
LVAR_INT sw7_protector3seqA


LVAR_INT sw7_protector4
LVAR_INT sw7_protector5
LVAR_INT sw7_protector6
LVAR_INT sw7_protector4seqA
LVAR_INT sw7_protector5seqA
LVAR_INT sw7_protector6seqA

LVAR_INT sw7_reinforce1
LVAR_INT sw7_reinforce1seq

LVAR_INT sw7_reinforce2
LVAR_INT sw7_reinforce2seq

LVAR_INT sw7_pursuitseq
LVAR_INT sw7_escapechauffeur
LVAR_INT sw7_mopupcount 
LVAR_INT muP1 muP2 muP3 muP4 muP5 muP6
LVAR_INT muR1 muR2

LVAR_INT sw7_pursuitunderway
LVAR_INT sw7_newcutscene



//Objects

LVAR_INT sw7_casket


//coords
LVAR_FLOAT sw7_coord_area_cemetry_min_x  			   
LVAR_FLOAT sw7_coord_area_cemetry_min_y 
LVAR_FLOAT sw7_coord_area_cemetry_max_x  
LVAR_FLOAT sw7_coord_area_cemetry_max_y


//global health bars and timers
VAR_INT sw7_kanehealth
VAR_INT sw7_kanefuckedhealth


//coordinates
LVAR_INT sw7_fleeindex // coord array indexer
LVAR_FLOAT sw7_fleeKX[10]
LVAR_FLOAT sw7_fleeKY[10]
LVAR_FLOAT sw7_fleeKZ[10]

LVAR_FLOAT sw7_flee1X[10]
LVAR_FLOAT sw7_flee1Y[10]
LVAR_FLOAT sw7_flee1Z[10]

LVAR_FLOAT sw7_flee2X[10]
LVAR_FLOAT sw7_flee2Y[10]
LVAR_FLOAT sw7_flee2Z[10]

LVAR_FLOAT sw7_flee3X[10]
LVAR_FLOAT sw7_flee3Y[10]
LVAR_FLOAT sw7_flee3Z[10]



// New group teaching vars and blips							 
LVAR_INT sw7_group
LVAR_INT sw7_groupsize
LVAR_INT sw7_leaders

LVAR_INT sw7_banger1
LVAR_INT sw7_banger2

LVAR_INT sw7_banger1b
LVAR_INT sw7_banger2b
LVAR_INT sw7_bangershouseb

LVAR_INT sw7_bang1swap
LVAR_INT sw7_bang2swap
LVAR_INT sw7_checkgangblips

LVAR_INT sw7_passedb

LVAR_INT sw7_banger1jumpseq																				   
LVAR_INT sw7_banger2jumpseq

LVAR_INT sw7_compilerfool
 

VAR_INT sw7_funeraltimer

LVAR_INT sw7_drivebyinprogress

LVAR_INT sw7_ptrfullfixflag

LVAR_INT sw7_escapeaborted
LVAR_INT sw7_helpfix
LVAR_INT sw7_disbandfix sw7_regroupfix

LVAR_INT sw7_respectvalue
LVAR_INT sw7_ballastext sw7_ballasb	sw7_sweetturn

LVAR_INT sw7_ganghelpbuffer sw7_followhelpbuffer
LVAR_INT sw7_currentwanted

LVAR_INT sw7_parkedcar


LVAR_INT sw7_respect1 sw7_respect2 sw7_respect3
LVAR_INT sw7_respect1B sw7_respect2B sw7_respect3B


LVAR_INT sw7_gangfrenzy sw7_inc1blip sw7_inc2blip sw7_inc3blip

LVAR_INT sw7_grove2guardseq sw7_grove1guardseq

LVAR_INT sw7_potentialmembers

LVAR_INT sw7_timeron
LVAR_INT sw7_newskipfix


//Dialogue and audio variables

LVAR_INT sfx_crying 

LVAR_INT sw7_dialogue sw7_audio_char
LVAR_INT sw7_text_timer_diff sw7_text_timer_end sw7_text_timer_start
LVAR_TEXT_LABEL sw7_text[74]
LVAR_INT sw7_audio[74] sw7_audio_slot sw7_alt_slot sw7_counter sw7_ahead_counter sw7_audio_playing sw7_audio_underway
LVAR_INT sw7_convo_underway sw7_convo_counter sw7_random sw7_leftcar_counter sw7_backcar_counter sw7_ganghire_counter 
LVAR_INT sw7_driveby_counter sw7_cut_counter


LVAR_INT sw7_forcehelp
sw7_forcehelp = 0

LVAR_INT sw7_banger1meetseq sw7_banger2meetseq sw7_playerwallseq testhealth




// **************************************** Mission Start **********************************

mission_start_sweet7:

REGISTER_MISSION_GIVEN
LOAD_MISSION_TEXT sweet7	

flag_player_on_mission = 1

//disable_mod_garage = 1

WAIT 0

SET_PLAYER_CONTROL player1 OFF

// ****************************************START OF CUTSCENE********************************

SET_FADING_COLOUR 0 0 0
DO_FADE 2000 FADE_OUT
FORCE_WEATHER_NOW WEATHER_SUNNY_LA

WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE 

LOAD_CUTSCENE SWEET7A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
CLEAR_AREA 2508.9753 -1671.5371 12.3853 500.0 TRUE

START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
            WAIT 0
ENDWHILE
 
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
    WAIT 0			   
ENDWHILE

CLEAR_CUTSCENE

RELEASE_WEATHER
 
  

// ****************************************END OF CUTSCENE**********************************

WAIT 0
	
SET_CHAR_COORDINATES scplayer 2515.2068 -1674.0859 12.7552
SET_CHAR_HEADING scplayer 54.1779 

LOAD_SPECIAL_CHARACTER 1 sweet

REQUEST_MODEL greenwoo
REQUEST_MODEL wmoprea
REQUEST_MODEL BFYRI

REQUEST_MODEL admiral
REQUEST_MODEL sadler

REQUEST_MODEL MICRO_UZI
REQUEST_MODEL COLT45
REQUEST_MODEL CHROMEGUN

REQUEST_MODEL BALLAS3
REQUEST_MODEL BALLAS2
REQUEST_MODEL BALLAS1

REQUEST_MODEL FAM3
REQUEST_MODEL FAM2
REQUEST_MODEL FAM1

REQUEST_ANIMATION graveyard
REQUEST_ANIMATION SMOKING

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
   	OR NOT HAS_MODEL_LOADED wmoprea
	OR NOT HAS_MODEL_LOADED BFYRI 
	OR NOT HAS_MODEL_LOADED admiral
	OR NOT HAS_MODEL_LOADED sadler
	WAIT 0
ENDWHILE


WHILE NOT HAS_MODEL_LOADED COLT45
	OR NOT HAS_MODEL_LOADED greenwoo
	OR NOT HAS_MODEL_LOADED MICRO_UZI
	OR NOT HAS_MODEL_LOADED CHROMEGUN
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED BALLAS3
	OR NOT HAS_MODEL_LOADED BALLAS2
	OR NOT HAS_MODEL_LOADED BALLAS1
	WAIT 0
ENDWHILE


WHILE NOT HAS_MODEL_LOADED FAM3
	OR NOT HAS_MODEL_LOADED FAM2
	OR NOT HAS_MODEL_LOADED FAM1
	OR NOT HAS_ANIMATION_LOADED graveyard
	OR NOT HAS_ANIMATION_LOADED SMOKING
	WAIT 0
ENDWHILE


WAIT 500

// ************************************ Declare Variables *********************************


sw7_timeron = 0
sw7_followhelpbuffer = 0
sw7_ganghelpbuffer = 0
sw7_helpfix = 0
sw7_disbandfix = 0
sw7_regroupfix = 0
sw7_ptrfullfixflag = 0
sw7_escapeaborted = 0

sw7_funeraltimer = 0
sw7_drivebyinprogress = 0

sw7_finalkaneexit = 0
sw7_finalchauffexit = 0

sw7_compilerfool = 0
sw7_newcutscene = 0
sw7_newctskip_needed = 0


sw7_ctskip_needed = 0
sw7_missionprogress = 0
sw7_fleeindex = 0
sw7_chauffeurkilltask = 0
sw7_nextprompt = -13
sw7_begingangfight = 0

sw7_protector1dead = 0
sw7_protector2dead = 0
sw7_protector3dead = 0

sw7_grove1fight = 0
sw7_grove2fight	= 0
sw7_sweetfight	= 0

sw7_newskipfix = 0

sw7_endanims = 0

sw7_checkkane = 0
sw7_pursuitunderway = 0

sw7_mopupcount = 0

sw7_coord_area_cemetry_min_x = 806.4608 			   
sw7_coord_area_cemetry_min_y = -1129.8440 
sw7_coord_area_cemetry_max_x = 952.2434 
sw7_coord_area_cemetry_max_y = -1051.8409 

sw7_fleeKX[1] = 931.9752 
sw7_fleeKY[1] = -1062.447
sw7_fleeKZ[1] = 23.6757 

sw7_flee1X[1] = 921.2714 
sw7_flee1Y[1] = -1064.7358
sw7_flee1Z[1] = 23.327 

sw7_flee2X[1] = 920.3427 
sw7_flee2Y[1] = -1061.4033
sw7_flee2Z[1] = 23.6757 

sw7_flee3X[1] = 921.2690 
sw7_flee3Y[1] = -1081.521
sw7_flee3Z[1] = 23.623 

sw7_fleeKX[2] = 922.6375 
sw7_fleeKY[2] = -1122.2816
sw7_fleeKZ[2] = 23.0028

sw7_fleeKX[3] = 899.5370 
sw7_fleeKY[3] = -1114.6938
sw7_fleeKZ[3] = 23.2320


  
sw7_flee1X[2] = 913.2869
sw7_flee1Y[2] = -1092.1680
sw7_flee1Z[2] = 23.1942

sw7_flee2X[2] = 911.2869
sw7_flee2Y[2] = -1092.1680
sw7_flee2Z[2] = 22.1942

sw7_flee3X[2] = 914.2869
sw7_flee3Y[2] = -1092.1680
sw7_flee3Z[2] = 25.1942

sw7_flee1X[3] = 886.0813
sw7_flee1Y[3] = -1114.8986
sw7_flee1Z[3] = 22.9879

sw7_flee2X[3] = 883.5623
sw7_flee2Y[3] = -1112.9398
sw7_flee2Z[3] = 23.0419

sw7_flee3X[3] = 881.4856
sw7_flee3Y[3] = -1116.9850
sw7_flee3Z[3] = 23.0419

sw7_fleeKX[4] = 907.4198 
sw7_fleeKY[4] = -1101.5787 
sw7_fleeKZ[4] = 23.2969

muP1 = 0
muP2 = 0
muP3 = 0
muP4 = 0
muP5 = 0
muP6 = 0
muR1 = 0
muR2 = 0

sw7_funeraltimer = 240000

SWITCH_ROADS_OFF 2297.0376 -1664.6005 10.8474 2324.8655 -1652.1244 14.8382

SWITCH_PED_ROADS_OFF 2297.0376 -1664.6005 10.8474 2324.8655 -1652.1244 14.8382

GET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE gan1_gzs
GET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE gan2_gzs


SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 0  //default is 40			
SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 0 //default is 40

			
sw7_missionprogress = 0	 

GET_MAX_WANTED_LEVEL sw7_currentwanted 


CLEAR_AREA 954.4976 -1103.5941 22.8015 10.0 TRUE

CUSTOM_PLATE_FOR_NEXT_CAR greenwoo GROVE4L_  

CLEAR_AREA 2508.0845 -1671.1903 12.3794 500.0 TRUE
CREATE_CAR greenwoo 2508.0845 -1671.1903 12.3794 sw7_sweethearse
SET_CAR_HEADING sw7_sweethearse 354.4729
SET_CAN_BURST_CAR_TYRES sw7_sweethearse FALSE


CHANGE_CAR_COLOUR sw7_sweethearse 59 34
OPEN_CAR_DOOR sw7_sweethearse FRONT_RIGHT_DOOR

CREATE_CAR admiral 925.7109 -1122.9683 22.9909 sw7_pursuitcar 
SET_CAR_HEADING sw7_pursuitcar 330.8056//72.9
CHANGE_CAR_COLOUR sw7_pursuitcar 59 34
SET_CAN_BURST_CAR_TYRES sw7_pursuitcar FALSE
SET_CAR_PROOFS sw7_pursuitcar FALSE FALSE TRUE FALSE FALSE

SET_CAR_PROOFS sw7_pursuitcar TRUE TRUE TRUE TRUE TRUE

LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_LOCKED

SET_FADING_COLOUR 0 0 0

SET_CAMERA_BEHIND_PLAYER 

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY sw7_goondm
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY sw7_emptydm
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH sw7_toughdm
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK sw7_normaldm


CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 885.1994 -1075.5457 23.3273 sw7_protector1
SET_CHAR_DECISION_MAKER sw7_protector1 sw7_goondm
SET_CHAR_HEADING sw7_protector1 170.0
SET_CHAR_ACCURACY sw7_protector1 90
GET_CHAR_HEALTH sw7_protector1 testhealth
SET_CHAR_HEALTH sw7_protector1 120


CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 883.2532 -1075.4960 23.3273 sw7_protector2
SET_CHAR_DECISION_MAKER sw7_protector2 sw7_goondm
SET_CHAR_HEADING sw7_protector2 175.0
SET_CHAR_ACCURACY sw7_protector2 90
SET_CHAR_HEALTH sw7_protector2 120


CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 881.4261 -1077.1143 23.3203 sw7_protector3
SET_CHAR_DECISION_MAKER sw7_protector3 sw7_goondm
SET_CHAR_HEADING sw7_protector3 275.0
SET_CHAR_ACCURACY sw7_protector3 90
SET_CHAR_HEALTH sw7_protector3 120

	
	/* test block */
	IF NOT IS_CHAR_DEAD sw7_protector1
		GIVE_WEAPON_TO_CHAR sw7_protector1 WEAPONTYPE_PISTOL 2000
		TASK_PLAY_ANIM sw7_protector1 mrnM_loop graveyard 4.0 1 FALSE FALSE FALSE -1
	ENDIF

	IF NOT IS_CHAR_DEAD sw7_protector2
		GIVE_WEAPON_TO_CHAR sw7_protector2 WEAPONTYPE_PISTOL 2000
	ENDIF

	IF NOT IS_CHAR_DEAD sw7_protector3
		GIVE_WEAPON_TO_CHAR sw7_protector3 WEAPONTYPE_PISTOL 2000
		TASK_PLAY_ANIM sw7_protector3 mrnM_loop graveyard 10.0 1 FALSE FALSE FALSE -1
	ENDIF


CREATE_OBJECT_NO_OFFSET casket_law 885.6575 -1077.4117 23.3188 sw7_casket
CREATE_CHAR PEDTYPE_CIVMALE wmoprea 886.6391 -1079.1786 23.3136 sw7_preach
TASK_PLAY_ANIM sw7_preach PRST_loopa graveyard 4.0 1 FALSE FALSE FALSE -1
SET_CHAR_DECISION_MAKER sw7_preach sw7_emptydm
SET_CHAR_HEADING sw7_preach 24.0
MARK_MODEL_AS_NO_LONGER_NEEDED wmoprea

CREATE_CHAR PEDTYPE_CIVFEMALE BFYRI 884.9761 -1079.9830 23.3133 sw7_greetinface
TASK_PLAY_ANIM sw7_greetinface mrnF_loop graveyard 4.0 1 FALSE FALSE FALSE -1
SET_CHAR_DECISION_MAKER sw7_greetinface sw7_emptydm
SET_CHAR_HEADING sw7_greetinface 4.0
MARK_MODEL_AS_NO_LONGER_NEEDED BFYRI

											 
CLEAR_AREA 2297.9709 -1628.0146 13.7028 20.0 TRUE

CREATE_CHAR PEDTYPE_GANG_GROVE FAM1 2509.4180 -1663.1340 12.5872 sw7_banger1
SET_CHAR_HEADING sw7_banger1 214.5012

SET_CHAR_DECISION_MAKER sw7_banger1 sw7_emptydm
SET_CHAR_SUFFERS_CRITICAL_HITS sw7_banger1 FALSE
SET_CHAR_CANT_BE_DRAGGED_OUT sw7_banger1 TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED sw7_banger1 TRUE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sw7_banger1 FALSE

TASK_PLAY_ANIM sw7_banger1 M_smklean_loop SMOKING 4.0 1 FALSE FALSE FALSE -1

CREATE_CHAR PEDTYPE_GANG_GROVE FAM2 2504.4707 -1675.7640 12.3709 sw7_banger2
SET_CHAR_HEADING sw7_banger2 277.5012


SET_CHAR_DECISION_MAKER sw7_banger2 sw7_emptydm
SET_CHAR_SUFFERS_CRITICAL_HITS sw7_banger2 FALSE
SET_CHAR_CANT_BE_DRAGGED_OUT sw7_banger2 TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED sw7_banger2 TRUE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sw7_banger2 FALSE

TASK_PLAY_ANIM sw7_banger2 M_smkstnd_loop SMOKING 4.0 1 FALSE FALSE FALSE -1



SWITCH_ROADS_OFF sw7_coord_area_cemetry_min_x sw7_coord_area_cemetry_min_y 0.0 sw7_coord_area_cemetry_max_x sw7_coord_area_cemetry_max_y 60.0
SWITCH_PED_ROADS_OFF sw7_coord_area_cemetry_min_x sw7_coord_area_cemetry_min_y 0.0 sw7_coord_area_cemetry_max_x sw7_coord_area_cemetry_max_y 60.0

get_int_stat RESPECT sw7_respectvalue
FIND_MAX_NUMBER_OF_GROUP_MEMBERS sw7_potentialmembers

IF sw7_potentialmembers > 1

	sw7_nextprompt = 3
	CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2509.5896 -1672.2372 12.3934 sweet
	SET_CHAR_HEADING sweet 239.0

		IF NOT IS_CAR_DEAD sw7_sweethearse
			LOCK_CAR_DOORS sw7_sweethearse CARLOCK_LOCKOUT_PLAYER_ONLY	  // was mission2 pedtype
			FREEZE_CAR_POSITION sw7_sweethearse TRUE
		ENDIF

	GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_MICRO_UZI 99999
	SET_CHAR_NEVER_TARGETTED sweet TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
	SET_CHAR_HEALTH sweet 1000
	SET_CHAR_MAX_HEALTH sweet 1000
	SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED sweet TRUE
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE








	//CREATE_CHAR_AS_PASSENGER sw7_sweethearse PEDTYPE_MISSION2 SPECIAL01 0 sweet	  // was mission2 pedtype



	//Taken out as initial driving section has been ditched	15.09.04
	//ADD_BLIP_FOR_CAR sw7_sweethearse sw7_hearseB
	//SET_BLIP_AS_FRIENDLY sw7_hearseB TRUE
	//OPEN_CAR_DOOR sw7_sweethearse FRONT_LEFT_DOOR
	SET_RADIO_CHANNEL RS_NEW_JACK_SWING 


	//sw7_nextprompt = 1
	//Taken out 15.09.04

	TIMERA = 0

ELSE


	//WRITE_DEBUG you_shouldnt_see_this_Tell_CraigF

	CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 2511.3428 -1674.9357 12.5473 sweet

	IF NOT IS_CAR_DEAD sw7_sweethearse
		LOCK_CAR_DOORS sw7_sweethearse CARLOCK_LOCKOUT_PLAYER_ONLY	  // was mission2 pedtype
		FREEZE_CAR_POSITION sw7_sweethearse TRUE
	ENDIF




	SET_CHAR_MAX_HEALTH sweet 1000
	SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE
	//07.10.04

	GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_MICRO_UZI 99999
	SET_CHAR_NEVER_TARGETTED sweet TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
	SET_CHAR_HEALTH sweet 1000

	//SET_RADIO_CHANNEL RS_NEW_JACK_SWING 




	SET_CHAR_MAX_HEALTH sweet 1000
	SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED sweet TRUE
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE







	/*
	CREATE_CAR sadler 2491.9124 -1681.3718 12.3450  sw7_parkedcar
	SET_CAR_HEADING sw7_parkedcar 87.9
	CHANGE_CAR_COLOUR sw7_parkedcar 113 8


	*/

	//After submission fix, using variable for final cut.









	//SET_CAN_BURST_CAR_TYRES sw7_parkedcar FALSE


	CREATE_CHAR PEDTYPE_GANG_FLAT BALLAS1 2262.7969 -1348.5397 22.9850 sw7_respect1
	//SET_CHAR_HEADING sw7_respect1 312.0  07.10.04
	GIVE_WEAPON_TO_CHAR sw7_respect1 WEAPONTYPE_MICRO_UZI 2000
	SET_CHAR_DECISION_MAKER sw7_respect1 sw7_emptydm


	CREATE_CHAR PEDTYPE_GANG_FLAT BALLAS2 2263.5823 -1346.6505 22.9838 sw7_respect2
	SET_CHAR_HEADING sw7_respect2 140.0
	GIVE_WEAPON_TO_CHAR sw7_respect2 WEAPONTYPE_PISTOL 2000
	SET_CHAR_DECISION_MAKER sw7_respect2 sw7_emptydm



	TASK_CHAT_WITH_CHAR sw7_respect1 sw7_respect2  true true //ped0 will lead the chatting
	TASK_CHAT_WITH_CHAR sw7_respect2 sw7_respect1 false true //ped1 will follow ped0 at chatting

	CREATE_CHAR PEDTYPE_GANG_FLAT BALLAS3 2261.8496 -1345.1008 22.9864 sw7_respect3
	SET_CHAR_HEADING sw7_respect3 224.0
	GIVE_WEAPON_TO_CHAR sw7_respect3 WEAPONTYPE_MICRO_UZI 2000
	SET_CHAR_DECISION_MAKER sw7_respect3 sw7_emptydm


	TASK_PLAY_ANIM sw7_respect3 mrnM_loop graveyard 10.0 1 FALSE FALSE FALSE -1



	UNLOAD_SPECIAL_CHARACTER 1

		ADD_BLIP_FOR_CHAR sw7_respect1 sw7_respect1B
		ADD_BLIP_FOR_CHAR sw7_respect2 sw7_respect2B
		ADD_BLIP_FOR_CHAR sw7_respect3 sw7_respect3B

		sw7_nextprompt = 0	 // was 0
		sw7_ballastext = -10	// was 0
		timerb  = 0

ENDIF


TASK_TURN_CHAR_TO_FACE_CHAR scplayer sweet

timera = 0

MARK_MODEL_AS_NO_LONGER_NEEDED sadler

SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
CLEAR_AREA 2508.16 -1666.47 13.0 10.0 TRUE

DO_FADE 1000 FADE_IN



// New group teaching commands.

//CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS sw7_group
GET_PLAYER_GROUP player1 sw7_group

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_GANG_GROVE PEDTYPE_PLAYER1
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
SET_GROUP_FOLLOW_STATUS sw7_group TRUE






IF sw7_compilerfool = 13
	ADD_BLIP_FOR_CHAR scplayer sw7_kaneb
	CREATE_CAR greenwoo 2508.9753 -1671.5371 12.3853 sw7_sweethearse
	ADD_BLIP_FOR_CAR sw7_sweethearse sw7_pursuitcarb
	CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 885.1994 -1075.5457 23.3273 sw7_kane


ENDIF



//CLEAR_ALL_SCRIPT_ROADBLOCKS


//Main mission loop



//Audio flags

sfx_crying = 0


// ---- Dialogue Flags
	sw7_audio_slot = 1
	sw7_alt_slot = 2
	sw7_counter = 0
	sw7_ahead_counter = 1
	sw7_audio_playing = 0
	sw7_audio_underway = 0



//Dialogue text


$sw7_text[1] = SWE7_AA //We're gonna need some help.
$sw7_text[2] = SWE7_AB //Call up some dogs, get us a posse?
$sw7_text[3] = SWE7_AC //Word. But I warned you about putting time in for yo'homies.
$sw7_text[4] =SWE7_AD//You ain't got enough juice to rally the troops, bro.
$sw7_text[5] =SWE7_AE//What can I do about that now?
$sw7_text[6] =SWE7_AF//Head over to Ballas turf and ice some fools,
$sw7_text[7] =SWE7_AG//Word will soread quick enough.
$sw7_text[8] =SWE7_AH//I'll be waiting for you back here.




$sw7_text[9] =SWE7_BA//Yo, we'll take my ride.
$sw7_text[10] =SWE7_BB//Yo. Where to?
$sw7_text[11] =SWE7_BC//Just along the block up there by the bridge.
$sw7_text[12] =SWE7_BD//I know a couple of Grovers we can bring along.
$sw7_text[13] =SWE7_BE//A Grove posse? I'm down for that!
$sw7_text[14] =SWE7_CA//Get out and muster the troops, CJ!
$sw7_text[15] =SWE7_CB//There they are, get out and bring 'em along.




$sw7_text[16] =SWE7_CC//That's them, go welcome them into the posse!
$sw7_text[17] =SWE7_DA//CJ, you been at Ryder's sherm? SHIT....
$sw7_text[18] =SWE7_DB//What the fuck you doing, CJ?
$sw7_text[19] =SWE7_DC//Oh man, you crazy freak!
$sw7_text[20] =SWE7_DD//Tell those guys to get lost, CJ, I got two OG's in mind.
$sw7_text[21] =SWE7_DE//I told you I had two boys in mind, dog, tell these boys to go home!
$sw7_text[22] =SWE7_EA//Ok, let's roll, got a couple of boys to meet up with!
$sw7_text[23] =SWE7_EB//We'll go around back of Los Sepulcros and sneak in over the wall.
$sw7_text[24] =SWE7_EC//Yo, we gotta go get in position before the Funeral starts.
$sw7_text[25] =SWE7_ED//Get a move on, CJ, we gonna be late!
$sw7_text[26] =SWE7_EE//We got get there early, CJ!
$sw7_text[27] =SWE7_EF//Oh, dude, we missed the damn funeral!
$sw7_text[28] =SWE7_EG//Aw, man, we blew it, the funeral's over!
$sw7_text[29] =SWE7_EH//This is it.
$sw7_text[30] =SWE7_EJ//Carl, you stay put while we hop over the wall and check it out.
$sw7_text[31] =SWE7_EK//Ok, CJ, come on over!
$sw7_text[32] =SWE7_FA//Yo, you guys know CJ, yeah?
$sw7_text[33] =SWE7_FB//These two boys been watching Front Yard Ballas set things up.
$sw7_text[34] =SWE7_GA//Ok, take up positions and wait for Kane.
$sw7_text[35] =SWE7_GB//Kane, ain't he Front Yard royalty?
$sw7_text[36] =SWE7_GC//Yeah, so if there's even a hint of trouble befoe he arriives, he won't show.
$sw7_text[37] =SWE7_HA//CJ, you psycho moron! Kane won't show now!
$sw7_text[38] =SWE7_HB//Oh, man, Kane won't show now!
$sw7_text[39] =SWE7_HC//You blew our ambush, CJ, you blew it!
$sw7_text[40] =SWE7_JA//Here he comes...
$sw7_text[41] =SWE7_JB//Looks like the coward's wearing armour,
$sw7_text[42] =SWE7_JC//Might take a round or two extra to drop him!
$sw7_text[43] =SWE7_KA//Ok, CJ, you take Kane, we'll take the rest of the trash out!
$sw7_text[44] =SWE7_KB//We got the small fry, CJ, you take Kane!
$sw7_text[45] =SWE7_KC//Don't worry, dogs, CJ will take care of Kane, just keep these cats off his case!

$sw7_text[46] =SWE7_LA//Kane's headed for that Ballas car!
$sw7_text[47] =SWE7_LB//Ballas car come to bail him out!
$sw7_text[48] =SWE7_LC//He's bailing!

$sw7_text[49] =SWE7_MA//C'mon, we'll take that car over there!
$sw7_text[50] =SWE7_MB//We can chase that bailler in this car!
$sw7_text[51] =SWE7_MC//We can take this car!
$sw7_text[52] =SWE7_MD//We can take that car!

$sw7_text[53] =SWE7_NA//Ok, I've busted in, c'mon!
$sw7_text[54] =SWE7_NB//C'mon, CJ, I've busted into this bucket!

$sw7_text[55] =SWE7_OA//Gun it, CJ, we gotta catch him before he reaches Front Yard turf!
$sw7_text[56] =SWE7_OB//Punch it, CJ, get after that fool before he gets on home turf!

$sw7_text[57] =SWE7_PA//Get me close, CJ!
$sw7_text[58] =SWE7_PB//Keep it steady, CJ!
$sw7_text[59] =SWE7_PC//Get alongside!
$sw7_text[60] =SWE7_PD//Pull up level so I can blast this fool!

$sw7_text[61] =SWE7_QA//Aw shit, he's home and dry now!
$sw7_text[62] =SWE7_QB//Man, he made it bacck to his crib!
$sw7_text[63] =SWE7_QC//Aw fuck! He outran us, FUCK!

$sw7_text[64] =SWE7_RA//Eat that you Front Yard FOOL!
$sw7_text[65] =SWE7_RB//NOBODY MESSES WITH GROVE STREET FAMILIES!
$sw7_text[66] =SWE7_RC//C'mon, we better high tail it back to Ganton!
$sw7_text[67] =SWE7_SA//Nice one, CJ!
$sw7_text[68] =SWE7_SB//I'll get us a getaway car, you guys take the rest of those Ballas!
$sw7_text[69] =SWE7_SC//Ok, everybody in, let's get out of here!
$sw7_text[70] =SWE7_TA//Man, we were tight back there!
$sw7_text[71] =SWE7_TB//Everybody go home, stay low, we ain't seen each other all day, right?
$sw7_text[72] =SWE7_TC//I'll catch you later, Carl.


// Dialogue audio

$sw7_audio[1] = SOUND_SWE7_AA //We're gonna need some help.
$sw7_audio[2] = SOUND_SWE7_AB //Call up some dogs, get us a posse?
$sw7_audio[3] = SOUND_SWE7_AC //Word. But I warned you about putting time in for yo'homies.
$sw7_audio[4] = SOUND_SWE7_AD//You ain't got enough juice to rally the troops, bro.
$sw7_audio[5] = SOUND_SWE7_AE//What can I do about that now?
$sw7_audio[6] = SOUND_SWE7_AF//Head over to Ballas turf and ice some fools,
$sw7_audio[7] = SOUND_SWE7_AG//Word will soread quick enough.
$sw7_audio[8] = SOUND_SWE7_AH//I'll be waiting for you back here.
$sw7_audio[9] = SOUND_SWE7_BA//Yo, we'll take my ride.
$sw7_audio[10] = SOUND_SWE7_BB//Yo. Where to?
$sw7_audio[11] = SOUND_SWE7_BC//Just along the block up there by the bridge.
$sw7_audio[12] = SOUND_SWE7_BD//I know a couple of Grovers we can bring along.
$sw7_audio[13] = SOUND_SWE7_BE//A Grove posse? I'm down for that!

$sw7_audio[14] = SOUND_SWE7_CA//Get out and muster the troops, CJ!
$sw7_audio[15] = SOUND_SWE7_CB//There they are, get out and bring 'em along.
$sw7_audio[16] = SOUND_SWE7_CC//That's them, go welcome them into the posse!

$sw7_audio[17] = SOUND_SWE7_DA//CJ, you been at Ryder's sherm? SHIT....
$sw7_audio[18] = SOUND_SWE7_DB//What the fuck you doing, CJ?
$sw7_audio[19] = SOUND_SWE7_DC//Oh man, you crazy freak!

$sw7_audio[20] = SOUND_SWE7_DD//Tell those guys to get lost, CJ, I got two OG's in mind.
$sw7_audio[21] = SOUND_SWE7_DE//I told you I had two boys in mind, dog, tell these boys to go home!

$sw7_audio[22] = SOUND_SWE7_EA//Ok, let's roll, got a couple of boys to meet up with!
$sw7_audio[23] = SOUND_SWE7_EB//We'll go around back of Los Sepulcros and sneak in over the wall.
$sw7_audio[24] = SOUND_SWE7_EC//Yo, we gotta go get in position before the Funeral starts.
$sw7_audio[25] = SOUND_SWE7_ED//Get a move on, CJ, we gonna be late!
$sw7_audio[26] = SOUND_SWE7_EE//We got get there early, CJ!

$sw7_audio[27] = SOUND_SWE7_EF//Oh, dude, we missed the damn funeral!
$sw7_audio[28] = SOUND_SWE7_EG//Aw, man, we blew it, the funeral's over!

$sw7_audio[29] = SOUND_SWE7_EH//This is it.
$sw7_audio[30] = SOUND_SWE7_EJ//Carl, you stay put while we hop over the wall and check it out.
$sw7_audio[31] = SOUND_SWE7_EK//Ok, CJ, come on over!
$sw7_audio[32] = SOUND_SWE7_FA//Yo, you guys know CJ, yeah?
$sw7_audio[33] = SOUND_SWE7_FB//These two boys been watching Front Yard Ballas set things up.

$sw7_audio[34] = SOUND_SWE7_GA//Ok, take up positions and wait for Kane.
$sw7_audio[35] = SOUND_SWE7_GB//Kane, ain't he Front Yard royalty?

$sw7_audio[36] = SOUND_SWE7_GC//Yeah, so if there's even a hint of trouble befoe he arriives, he won't show.

$sw7_audio[37] = SOUND_SWE7_HA//CJ, you psycho moron! Kane won't show now!
$sw7_audio[38] = SOUND_SWE7_HB//Oh, man, Kane won't show now!
$sw7_audio[39] = SOUND_SWE7_HC//You blew our ambush, CJ, you blew it!

$sw7_audio[40] = SOUND_SWE7_JA//Here he comes...
$sw7_audio[41] = SOUND_SWE7_JB//Looks like the coward's wearing armour,
$sw7_audio[42] = SOUND_SWE7_JC//Might take a round or two extra to drop him!
$sw7_audio[43] = SOUND_SWE7_KA//Ok, CJ, you take Kane, we'll take the rest of the trash out!
$sw7_audio[44] = SOUND_SWE7_KB//We got the small fry, CJ, you take Kane!
$sw7_audio[45] = SOUND_SWE7_KC//Don't worry, dogs, CJ will take care of Kane, just keep these cats off his case!
$sw7_audio[46] = SOUND_SWE7_LA//Kane's headed for that Ballas car!
$sw7_audio[47] = SOUND_SWE7_LB//Ballas car come to bail him out!
$sw7_audio[48] = SOUND_SWE7_LC//He's bailing!
$sw7_audio[49] = SOUND_SWE7_MA//C'mon, we'll take that car over there!
$sw7_audio[50] = SOUND_SWE7_MB//We can chase that bailler in this car!
$sw7_audio[51] = SOUND_SWE7_MC//We can take this car!
$sw7_audio[52] = SOUND_SWE7_MD//We can take that car!
$sw7_audio[53] = SOUND_SWE7_NA//Ok, I've busted in, c'mon!
$sw7_audio[54] = SOUND_SWE7_NB//C'mon, CJ, I've busted into this bucket!
$sw7_audio[55] = SOUND_SWE7_OA//Gun it, CJ, we gotta catch him before he reaches Front Yard turf!
$sw7_audio[56] = SOUND_SWE7_OB//Punch it, CJ, get after that fool before he gets on home turf!
$sw7_audio[57] = SOUND_SWE7_PA//Get me close, CJ!
$sw7_audio[58] = SOUND_SWE7_PB//Keep it steady, CJ!
$sw7_audio[59] = SOUND_SWE7_PC//Get alongside!
$sw7_audio[60] = SOUND_SWE7_PD//Pull up level so I can blast this fool!
$sw7_audio[61] = SOUND_SWE7_QA//Aw shit, he's home and dry now!
$sw7_audio[62] = SOUND_SWE7_QB//Man, he made it bacck to his crib!
$sw7_audio[63] = SOUND_SWE7_QC//Aw fuck! He outran us, FUCK!

$sw7_audio[64] = SOUND_SWE7_RA//Eat that you Front Yard FOOL!
$sw7_audio[65] = SOUND_SWE7_RB//NOBODY MESSES WITH GROVE STREET FAMILIES!
$sw7_audio[66] = SOUND_SWE7_RC//C'mon, we better high tail it back to Ganton!

$sw7_audio[67] = SOUND_SWE7_SA//Nice one, CJ!
$sw7_audio[68] = SOUND_SWE7_SB//I'll get us a getaway car, you guys take the rest of those Ballas!
$sw7_audio[69] = SOUND_SWE7_SC//Ok, everybody in, let's get out of here!
$sw7_audio[70] = SOUND_SWE7_TA//Man, we were tight back there!
$sw7_audio[71] = SOUND_SWE7_TB//Everybody go home, stay low, we ain't seen each other all day, right?
$sw7_audio[72] = SOUND_SWE7_TC//I'll catch you later, Carl.







//Main mission loop
																		  
sweet7_main_mission_loop:
WAIT 0


// Audio FX

IF sfx_crying = 0
	LOAD_MISSION_AUDIO 3 SOUND_MOURNERS
	sfx_crying = 1
ENDIF

IF sfx_crying = 1
	IF HAS_MISSION_AUDIO_LOADED 3
		sfx_crying = 2
	ENDIF
ENDIF

IF sfx_crying = 2
	IF NOT IS_CHAR_DEAD sw7_greetinface
		IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sw7_greetinface 200.0 200.0 FALSE
			SET_MISSION_AUDIO_POSITION 3 884.9761 -1079.9830 23.3133 // Central mourning location.
			PLAY_MISSION_AUDIO 3
			//WRITE_DEBUG AUD
			sfx_crying = 3
		ENDIF
	ENDIF
ENDIF



// ---- Load & Play Dialogue...
	IF NOT sw7_counter = 0
		IF sw7_audio_playing = 0
			IF HAS_MISSION_AUDIO_LOADED sw7_alt_slot
				CLEAR_MISSION_AUDIO sw7_alt_slot
			ENDIF
			sw7_audio_playing = 1
			sw7_audio_underway = 1
		ENDIF

		IF sw7_audio_playing = 1
			LOAD_MISSION_AUDIO sw7_audio_slot sw7_audio[sw7_counter]
			//GOSUB sw7_dialogue_pos
			//ATTACH_MISSION_AUDIO_TO_PED sw7_audio_slot sw7_audio_char
			sw7_audio_playing = 2
		ENDIF

		IF sw7_audio_playing = 2
		 	IF HAS_MISSION_AUDIO_LOADED sw7_audio_slot
				PLAY_MISSION_AUDIO sw7_audio_slot
				PRINT_NOW $sw7_text[sw7_counter] 10000 1
				sw7_audio_playing = 3
			ENDIF
		ENDIF

		IF sw7_audio_playing = 3
			IF HAS_MISSION_AUDIO_FINISHED sw7_audio_slot
				CLEAR_THIS_PRINT $sw7_text[sw7_counter]
				IF sw7_audio_slot = 1
					sw7_audio_slot = 2
					sw7_alt_slot = 1
				ELSE
					sw7_audio_slot = 1
					sw7_alt_slot = 2
				ENDIF
				sw7_counter = 0
				sw7_audio_playing = 0
			
				sw7_audio_underway = 0 // okay to cue up next piece of convo text

			ELSE
				IF NOT HAS_MISSION_AUDIO_LOADED sw7_alt_slot
					IF sw7_counter < 72
						sw7_ahead_counter = sw7_counter + 1
						LOAD_MISSION_AUDIO sw7_alt_slot sw7_audio[sw7_ahead_counter]
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
// End of dialogue loader / player






IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
            GOTO mission_sweet7_passed  
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_W
	IF NOT IS_CAR_DEAD sw7_sweethearse
           SET_CAR_COORDINATES sw7_sweethearse 794.2030 -1059.1851 23.6844	
	ENDIF
ENDIF


GET_GROUP_SIZE sw7_group sw7_leaders sw7_groupsize
//WRITE_DEBUG_WITH_INT gnum sw7_groupsize

/*
IF sw7_groupsize = 2 
	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
ENDIF*/


IF sw7_missionprogress < 1
	IF sw7_funeraltimer < 1
		CLEAR_ONSCREEN_TIMER sw7_funeraltimer
		CLEAR_PRINTS 
	    GENERATE_RANDOM_INT_IN_RANGE 0 2 sw7_random
		sw7_convo_counter = 31
		sw7_missionprogress = 999

	ENDIF

	IF NOT IS_CAR_DEAD sw7_sweethearse
	
		IF IS_CAR_ON_FIRE sw7_sweethearse
			
			CLEAR_PRINTS
			PRINT_NOW TWAR664 6000 1
			GOTO mission_sweet7_failed
		ENDIF
		
	ENDIF

ENDIF



get_int_stat RESPECT sw7_respectvalue
//WRITE_DEBUG_WITH_FLOAT rpct sw7_respectvalue








IF sw7_nextprompt = 0
	//keep checking respect



//	IF sw7_audio_underway = 0
//		IF sw7_ballastext = -10
//			sw7_counter = 1
//		    sw7_audio_underway = 1
//			sw7_ballastext = -9
//		ENDIF
//	ENDIF
//
//	IF sw7_audio_underway = 0
//
//		IF sw7_ballastext = -9
//			sw7_counter = 2
//		   	sw7_audio_underway = 1
//			sw7_ballastext = -8
//		ENDIF
//	ENDIF
//
//	IF sw7_audio_underway = 0
//
//		IF sw7_ballastext = -8
//			sw7_counter = 3
//			sw7_audio_underway = 1
//			sw7_ballastext = -7
//		ENDIF
//
//	ENDIF
//


	IF sw7_ballastext < 0
	
		IF sw7_audio_underway = 0
	
			SWITCH sw7_ballastext
				CASE -10
					sw7_counter = 1
					sw7_ballastext = -9
					BREAK
				CASE -9
					sw7_counter = 2
					sw7_ballastext = -8
					BREAK
				CASE -8
					sw7_counter = 3
					sw7_ballastext = -7
					BREAK
				CASE -7
					sw7_counter = 4
					sw7_ballastext = -6
					BREAK
				CASE -6
					sw7_counter = 5
					sw7_ballastext = -5
					BREAK

				CASE -5
					sw7_counter = 6
					sw7_ballastext = -4
					BREAK
				CASE -4
					sw7_counter = 7
					sw7_ballastext = -3
					BREAK
				CASE -3
					sw7_counter = 8
					sw7_ballastext = -2
					BREAK

				CASE -2
					sw7_counter = 0
					sw7_ballastext = 0
					BREAK


			ENDSWITCH
			//sw7_audio_underway = 1

		ENDIF
	ENDIF
		
	
	IF sw7_audio_underway = 0

		IF sw7_ballastext = 0
			//IF timerb > 7000
				
				CLEAR_PRINTS 
				PRINT_NOW TWAR612 6000 1
				sw7_ballastext = 1
				timerb = 0
		   //	ENDIF
		ENDIF

		IF sw7_ballastext = 1
		
			IF timerb > 7000
				
				CLEAR_PRINTS 
				PRINT_HELP HELP101
				sw7_ballastext = 2
			ENDIF
		ENDIF


	ENDIF


	


	
	IF sw7_sweetturn = 0
		IF TIMERA > 3000
		 	 IF NOT IS_CHAR_DEAD sweet
				IF NOT IS_CHAR_DEAD sweet
					CLEAR_CHAR_TASKS sweet
		    		TASK_TURN_CHAR_TO_FACE_CHAR sweet scplayer
					TIMERA = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	IF DOES_CHAR_EXIST sweet
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sweet scplayer
			CLEAR_PRINTS
			PRINT_NOW TWAR618 6000 1
			GOTO mission_sweet7_failed
		ENDIF
	ENDIF


	IF sw7_gangfrenzy = 0
		IF DOES_CHAR_EXIST sw7_respect1
		   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_respect1 scplayer
			//CLEAR_CHAR_TASKS sw7_respect1
			//TASK_KILL_CHAR_ON_FOOT sw7_respect1 scplayer
	
			sw7_gangfrenzy = 1
			ENDIF
		ENDIF
	ENDIF


	IF sw7_gangfrenzy = 0
		IF DOES_CHAR_EXIST sw7_respect2
		   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_respect2 scplayer
			//CLEAR_CHAR_TASKS sw7_respect1
			//TASK_KILL_CHAR_ON_FOOT sw7_respect1 scplayer
	
			sw7_gangfrenzy = 1
			ENDIF
		ENDIF
	ENDIF


	IF sw7_gangfrenzy = 0
		IF DOES_CHAR_EXIST sw7_respect3
		   IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_respect3 scplayer
			//CLEAR_CHAR_TASKS sw7_respect1
			//TASK_KILL_CHAR_ON_FOOT sw7_respect1 scplayer
	
			sw7_gangfrenzy = 1
			ENDIF
		ENDIF
	ENDIF



	IF sw7_gangfrenzy = 1
		IF NOT IS_CHAR_DEAD sw7_respect1
			CLEAR_CHAR_TASKS sw7_respect1
			TASK_KILL_CHAR_ON_FOOT sw7_respect1 scplayer
		ENDIF

		IF NOT IS_CHAR_DEAD sw7_respect2
			CLEAR_CHAR_TASKS sw7_respect2
			TASK_KILL_CHAR_ON_FOOT sw7_respect2 scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD sw7_respect3
			CLEAR_CHAR_TASKS sw7_respect3
			TASK_KILL_CHAR_ON_FOOT sw7_respect3 scplayer
		ENDIF

		sw7_gangfrenzy = 2
	ENDIF
	



	IF sw7_inc1blip = 0
	IF IS_CHAR_DEAD sw7_respect1
	   	increment_int_stat RESPECT 35	//respect total = respect total = 3.5
		REMOVE_BLIP sw7_respect1B
		get_int_stat RESPECT sw7_respectvalue

		//WRITE_DEBUG_WITH_INT rt sw7_respectvalue 
		sw7_inc1blip = 1
	ENDIF
	ENDIF

	IF sw7_inc2blip = 0
	IF IS_CHAR_DEAD sw7_respect2
	   	increment_int_stat RESPECT 35	//respect total = respect total = 3.5
		REMOVE_BLIP sw7_respect2B
		get_int_stat RESPECT sw7_respectvalue

	   //	WRITE_DEBUG_WITH_INT rt sw7_respectvalue 
		sw7_inc2blip = 1
	ENDIF
	ENDIF

	IF sw7_inc3blip = 0
	IF IS_CHAR_DEAD sw7_respect3
	   	increment_int_stat RESPECT 35	//respect total = respect total = 3.5
		REMOVE_BLIP sw7_respect3B
		get_int_stat RESPECT sw7_respectvalue

	   //	WRITE_DEBUG_WITH_INT rt sw7_respectvalue 
		sw7_inc3blip = 1
	ENDIF
	ENDIF



	IF IS_CHAR_DEAD sw7_respect1
		IF IS_CHAR_DEAD sw7_respect2
			IF IS_CHAR_DEAD sw7_respect3
				get_int_stat RESPECT sw7_respectvalue
				IF sw7_respectvalue < 100
				   SET_INT_STAT RESPECT 200
				ENDIF
			ENDIF
		ENDIF
	ENDIF




	FIND_MAX_NUMBER_OF_GROUP_MEMBERS sw7_potentialmembers

	IF sw7_potentialmembers > 1
	   	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_respect1
	   	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_respect2
	   	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_respect3

		REMOVE_BLIP sw7_respect1B
		REMOVE_BLIP sw7_respect2B
		REMOVE_BLIP sw7_respect3B
		CLEAR_PRINTS
		PRINT_NOW TWAR617 6000 1
		IF NOT IS_CAR_DEAD sw7_sweethearse
		LOCK_CAR_DOORS sw7_sweethearse CARLOCK_LOCKOUT_PLAYER_ONLY

		//OPEN_CAR_DOOR sw7_sweethearse FRONT_LEFT_DOOR
		sw7_nextprompt = 1
		sw7_sweetturn = 1
		//REMOVE_BLIP sw7_ballasB
		//ADD_BLIP_FOR_CAR sw7_sweethearse sw7_hearseB
		//SET_BLIP_AS_FRIENDLY sw7_hearseB TRUE
	   	//FREEZE_CAR_POSITION sw7_sweethearse FALSE

	   		IF NOT IS_CHAR_DEAD sweet
				CLEAR_CHAR_TASKS sweet
				TASK_ENTER_CAR_AS_PASSENGER sweet sw7_sweethearse 20000 0
				ADD_BLIP_FOR_CAR sw7_sweethearse sw7_hearseB
				SET_RADIO_CHANNEL RS_NEW_JACK_SWING 

				SET_BLIP_AS_FRIENDLY sw7_hearseB TRUE

 		    ENDIF


		 ENDIF

	ENDIF

ENDIF



IF sw7_nextprompt = 1

	IF sw7_sweetturn = 1
		IF NOT IS_CHAR_DEAD sweet 
			IF NOT IS_CAR_DEAD sw7_sweethearse
				IF IS_CHAR_SITTING_IN_CAR sweet sw7_sweethearse
					FREEZE_CAR_POSITION sw7_sweethearse FALSE
					LOCK_CAR_DOORS sw7_sweethearse CARLOCK_UNLOCKED
				   //	ADD_BLIP_FOR_CAR sw7_sweethearse sw7_hearseB
				   //	SET_BLIP_AS_FRIENDLY sw7_hearseB TRUE
					OPEN_CAR_DOOR sw7_sweethearse FRONT_LEFT_DOOR

					sw7_sweetturn = 2
					
				ENDIF
			ENDIF
		ENDIF
	ENDIF

					

	
	IF NOT IS_CAR_DEAD sw7_sweethearse
 		IF IS_CHAR_SITTING_IN_CAR scplayer sw7_sweethearse
		   REMOVE_BLIP sw7_hearseB
		   get_int_stat RESPECT sw7_respectvalue
			
			ADD_BLIP_FOR_COORD 2460.4993 -1656.5701 12.3125 sw7_bangershouseB

			IF NOT IS_CHAR_DEAD sweet
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH  sweet TRUE
													
			ENDIF

 			sw7_nextprompt = 2
			sw7_blipswap = 0
			sw7_convo_counter = 1
		 ENDIF
 	ENDIF
ENDIF



IF sw7_nextprompt = 2
	
	IF NOT IS_CAR_DEAD sw7_sweethearse


			// Intro convo
			IF IS_CHAR_IN_CAR scplayer sw7_sweethearse
				
				IF sw7_convo_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_convo_counter
							CASE 1
								sw7_counter = 10
								sw7_convo_counter =  2
								BREAK
							CASE 2
								sw7_counter = 11
								sw7_convo_counter = 3
								BREAK
							CASE 3
								sw7_counter = 12
								sw7_convo_counter = 4
								BREAK

							CASE 4
								sw7_counter = 13
								sw7_convo_counter = 5
								BREAK
							CASE 5 
								sw7_counter = 0
								PRINT_NOW TWAR650 6000 1
								IF NOT IS_CHAR_DEAD sweet
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH  sweet FALSE
								ENDIF
								sw7_convo_counter = 0 // finish convo
								BREAK


						ENDSWITCH
					   //	sw7_audio_underway = 1
					ENDIF
				ENDIF

			ENDIF


			//Get back in the car variations
				IF sw7_leftcar_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_leftcar_counter
							CASE 1
								IF sw7_random = 0
									sw7_counter = 24
								ENDIF
							 	IF sw7_random = 1
									sw7_counter = 26
								ENDIF

								sw7_leftcar_counter = 2
								BREAK
							CASE 2	
								sw7_counter = 0
								IF NOT IS_CHAR_DEAD sweet
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH  sweet FALSE
								ENDIF
								PRINT_NOW TWAR645 6000 1
								sw7_leftcar_counter = 0 // finish convo
								BREAK


						ENDSWITCH
					   //	sw7_audio_underway = 1
					ENDIF
				ENDIF




			// Back in car, get to location variations
				IF sw7_backcar_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_backcar_counter
							CASE 1
								IF sw7_random = 0
									sw7_counter = 25
								ENDIF
								sw7_backcar_counter = 2
								BREAK
							CASE 2	
								sw7_counter = 0
								PRINT_NOW TWAR650 6000 1
								sw7_backcar_counter = 0 // finish convo
								BREAK


						ENDSWITCH
					   //	sw7_audio_underway = 1
					ENDIF
				ENDIF




			get_int_stat RESPECT sw7_respectvalue
			//WRITE_DEBUG_WITH_FLOAT rpct sw7_respectvalue

			IF sw7_blipswap = 0
				IF NOT IS_CHAR_IN_CAR scplayer sw7_sweethearse
					ADD_BLIP_FOR_CAR sw7_sweethearse sw7_hearseB
					SET_RADIO_CHANNEL RS_NEW_JACK_SWING 

					SET_BLIP_AS_FRIENDLY sw7_hearseB TRUE
					REMOVE_BLIP sw7_bangershouseB
					CLEAR_PRINTS
					sw7_blipswap = 1
					sw7_convo_counter = 0 // finish convo
					sw7_leftcar_counter = 1 //Cue get back in car variations	
					GENERATE_RANDOM_INT_IN_RANGE 0 2 sw7_random	 // choice of two text options

																  
				ENDIF
			ENDIF

			IF sw7_blipswap = 1
				IF IS_CHAR_IN_CAR scplayer sw7_sweethearse
					REMOVE_BLIP sw7_hearseB							
					ADD_BLIP_FOR_COORD 2460.4993 -1656.5701 12.3125 sw7_bangershouseB
					
					CLEAR_PRINTS
		
					sw7_blipswap = 0
					sw7_convo_counter = 0 //finish convo
					sw7_backcar_counter = 1  //Cue back in car, get to location variations
					GENERATE_RANDOM_INT_IN_RANGE 0 1 sw7_random	 // only one text option

					
				ENDIF
			ENDIF


		GET_AREA_VISIBLE sw7_visible

		IF sw7_visible = 0
		 	IF NOT IS_MINIGAME_IN_PROGRESS	
				SET_PLAYER_CONTROL player1 ON
			 ENDIF
		ENDIF

		IF IS_CHAR_IN_CAR scplayer sw7_sweethearse
				
				IF LOCATE_CHAR_IN_CAR_3D scplayer 2460.4993 -1656.5701 12.3125 4.0 4.0 4.0 TRUE
					SET_PLAYER_CONTROL player1 OFF
					IF IS_CAR_STOPPED sw7_sweethearse
					   	FREEZE_CAR_POSITION sw7_sweethearse TRUE
					   	CLEAR_AREA 2268.0391 -1670.6952 14.3594 10.0 TRUE

						REMOVE_ANIMATION SMOKING

						CLEAR_PRINTS
						
						sw7_convo_counter = 1
						SET_PLAYER_CONTROL player1 ON

						SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_GANG_GROVE PEDTYPE_PLAYER1

						SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
					   
						REMOVE_BLIP sw7_bangershouseB
						IF NOT IS_CHAR_DEAD sw7_banger1
							IF NOT IS_CHAR_DEAD sw7_banger2
								
									ADD_BLIP_FOR_CHAR sw7_banger1 sw7_banger1b
										SET_BLIP_AS_FRIENDLY sw7_banger1b TRUE

									sw7_bang1swap = 1
									ADD_BLIP_FOR_CHAR sw7_banger2 sw7_banger2b
										SET_BLIP_AS_FRIENDLY sw7_banger2b TRUE

									sw7_bang2swap = 1
									sw7_ganghelpbuffer = 1
									timera = 0
									sw7_nextprompt = 4
									sw7_checkgangblips = 1
									GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random	 // choice of three text options
							ENDIF
						ENDIF
					ENDIF
				ENDIF
		ENDIF
	ENDIF
ENDIF


// New block to skip the initial convo section
IF sw7_nextprompt = 3

	FREEZE_CAR_POSITION sw7_sweethearse TRUE
	CLEAR_AREA 2268.0391 -1670.6952 14.3594 10.0 TRUE

	REMOVE_ANIMATION SMOKING

	CLEAR_PRINTS
	
	sw7_convo_counter = 1
	SET_PLAYER_CONTROL player1 ON

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_GANG_GROVE PEDTYPE_PLAYER1

	SET_PLAYER_GROUP_RECRUITMENT player1 TRUE
	
	IF NOT IS_CHAR_DEAD sw7_banger1
		IF NOT IS_CHAR_DEAD sw7_banger2
			
				ADD_BLIP_FOR_CHAR sw7_banger1 sw7_banger1b
				SET_BLIP_AS_FRIENDLY sw7_banger1b TRUE

				sw7_bang1swap = 1
				ADD_BLIP_FOR_CHAR sw7_banger2 sw7_banger2b
					SET_BLIP_AS_FRIENDLY sw7_banger2b TRUE

				sw7_bang2swap = 1
				sw7_ganghelpbuffer = 1
				timera = 0
				sw7_nextprompt = 4
				sw7_checkgangblips = 1
				GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random	 // choice of three text options

		ENDIF
	ENDIF

ENDIF

IF sw7_ganghelpbuffer = 1
	IF timera > 8000
		
		CLEAR_THIS_PRINT TWAR652  // ~s~Get those ~b~gang members~s~ to join you. They respect you enough to provide help.

		PRINT_HELP TWAR660  
		
		sw7_ganghelpbuffer = 2
	ENDIF
ENDIF


IF sw7_checkgangblips = 1
	IF sw7_bang1swap = 1
		IF NOT IS_CHAR_DEAD sw7_banger1
			IF IS_GROUP_MEMBER sw7_banger1 sw7_group
				REMOVE_BLIP sw7_banger1B
				sw7_bang1swap = 0
			ENDIF
		ENDIF
	ENDIF

	IF sw7_bang2swap = 1
		IF NOT IS_CHAR_DEAD sw7_banger2
			IF IS_GROUP_MEMBER sw7_banger2 sw7_group
			   //	SET_CHAR_NEVER_LEAVES_GROUP sw7_banger2 TRUE
				REMOVE_BLIP sw7_banger2B
				sw7_bang2swap = 0
			ENDIF
		ENDIF
	ENDIF


	IF sw7_bang1swap = 0
		IF NOT IS_CHAR_DEAD sw7_banger1
			IF NOT IS_GROUP_MEMBER sw7_banger1 sw7_group
				REMOVE_BLIP sw7_hearseB
				sw7_nextprompt = 4
				ADD_BLIP_FOR_CHAR sw7_banger1 sw7_banger1b
				SET_BLIP_AS_FRIENDLY sw7_banger1b TRUE

				sw7_bang1swap = 1
			ENDIF
		ENDIF
	ENDIF

	IF sw7_bang2swap = 0								 
		IF NOT IS_CHAR_DEAD sw7_banger2
			IF NOT IS_GROUP_MEMBER sw7_banger2 sw7_group
				REMOVE_BLIP sw7_hearseB
				sw7_nextprompt = 4
				ADD_BLIP_FOR_CHAR sw7_banger2 sw7_banger2b
				SET_BLIP_AS_FRIENDLY sw7_banger2b TRUE

				sw7_bang2swap = 1
			ENDIF
		ENDIF
	ENDIF


   GET_GROUP_SIZE sw7_group sw7_leaders sw7_groupsize

	IF sw7_groupsize > 1
		IF NOT IS_CHAR_DEAD sw7_banger1
			IF NOT IS_GROUP_MEMBER sw7_banger1 sw7_group
				CLEAR_HELP
			    PRINT_HELP_FOREVER TWAR662  

				IF sw7_ganghire_counter < 11
				    CLEAR_PRINTS
				    GENERATE_RANDOM_INT_IN_RANGE 0 2 sw7_random
				    sw7_ganghire_counter = 11
				ENDIF
			ENDIF
		ENDIF
	
		IF NOT IS_CHAR_DEAD sw7_banger2
			IF NOT IS_GROUP_MEMBER sw7_banger2 sw7_group
				CLEAR_HELP
				PRINT_HELP_FOREVER TWAR662  
                IF sw7_ganghire_counter < 11
					 CLEAR_PRINTS
					 GENERATE_RANDOM_INT_IN_RANGE 0 2 sw7_random
			         sw7_ganghire_counter = 11
			  	 ENDIF
				
			ENDIF
		ENDIF


			IF sw7_ganghire_counter  > 10
				IF sw7_audio_underway = 0
	
					SWITCH sw7_ganghire_counter
				  		CASE 11
							IF sw7_random = 0
								sw7_counter = 20
							ENDIF
							IF sw7_random = 1
								sw7_counter = 21
							ENDIF
						
							sw7_ganghire_counter = 12
							BREAK
						CASE 12	
							sw7_counter = 0
							PRINT_NOW TWAR659 6000 1
							sw7_ganghire_counter = 999 // finish convo
							BREAK
					ENDSWITCH
				ENDIF
			ENDIF

	ENDIF
	
	//okay to restart convo if player hires wrong guys AGAIN!

	IF sw7_groupsize < 2
		IF sw7_ganghelpbuffer = 2
			CLEAR_HELP
			PRINT_HELP_FOREVER TWAR660  
		
			sw7_ganghire_counter = 0   
		ENDIF
	ENDIF
ENDIF


IF sw7_nextprompt = 4
				IF sw7_convo_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_convo_counter
					  		CASE 1
								IF sw7_random = 0
									sw7_counter = 1//14

									IF NOT IS_CHAR_DEAD sweet

										CLEAR_CHAR_TASKS sweet 
										TASK_PLAY_ANIM sweet endchat_01 PED 4.0 1 FALSE FALSE FALSE -1

									ENDIF

								ENDIF
								IF sw7_random = 1
									sw7_counter = 1//14
									
									IF NOT IS_CHAR_DEAD sweet

										CLEAR_CHAR_TASKS sweet
										TASK_PLAY_ANIM sweet endchat_01 PED 4.0 1 FALSE FALSE FALSE -1

									ENDIF

								ENDIF
								IF sw7_random = 2
									sw7_counter = 1//14
									
									IF NOT IS_CHAR_DEAD sweet

										CLEAR_CHAR_TASKS sweet
										TASK_PLAY_ANIM sweet endchat_01 PED 4.0 1 FALSE FALSE FALSE -1

									ENDIF


								ENDIF

								sw7_convo_counter = 2
								BREAK
							CASE 2	

								sw7_counter = 0
								
								PRINT_NOW TWAR652 6000 1  // ~s~Get those ~b~gang members~s~ to join you. They respect you enough to provide help.
								
								IF NOT IS_CAR_DEAD sw7_sweethearse
								IF NOT IS_CHAR_DEAD sweet
									CLEAR_CHAR_TASKS sweet
									TASK_ENTER_CAR_AS_PASSENGER sweet sw7_sweethearse 20000 0
									SET_RADIO_CHANNEL RS_NEW_JACK_SWING 

								ENDIF   
					 		    ENDIF
                                
								sw7_convo_counter = 0 // finish convo
								BREAK

						ENDSWITCH
					ENDIF
				ENDIF

	
	IF NOT IS_CHAR_DEAD sw7_banger1
		IF NOT IS_CHAR_DEAD sw7_banger2
			IF IS_GROUP_MEMBER sw7_banger1 sw7_group
				IF IS_GROUP_MEMBER sw7_banger2 sw7_group
					CLEAR_HELP
					CLEAR_PRINTS
					PRINT_NOW TWAR654 5500 1
					sw7_followhelpbuffer = 1
					TIMERB = 0
					
					IF NOT IS_CAR_DEAD sw7_sweethearse
				
					IF NOT IS_CHAR_DEAD sweet 
	  				IF IS_CHAR_SITTING_IN_CAR sweet sw7_sweethearse
					//FREEZE_CAR_POSITION sw7_sweethearse FALSE
					LOCK_CAR_DOORS sw7_sweethearse CARLOCK_UNLOCKED
				   	ADD_BLIP_FOR_CAR sw7_sweethearse sw7_hearseB
				   	SET_BLIP_AS_FRIENDLY sw7_hearseB TRUE
					//OPEN_CAR_DOOR sw7_sweethearse FRONT_LEFT_DOOR
					SET_RADIO_CHANNEL RS_NEW_JACK_SWING 
								
					ENDIF
					ENDIF
	   		   
					ENDIF
					sw7_nextprompt = 10
				
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
ENDIF



IF sw7_followhelpbuffer = 1
	IF timerb > 6000
	   //	PRINT_HELP TWAR657
		sw7_followhelpbuffer = 2
	ENDIF
ENDIF
		 


IF sw7_nextprompt = 10

 	IF NOT IS_CAR_DEAD sw7_sweethearse
		IF NOT IS_CHAR_DEAD sw7_banger1
			IF NOT IS_CHAR_DEAD sw7_banger2
 				IF IS_CHAR_SITTING_IN_CAR sw7_banger1 sw7_sweethearse
					IF IS_CHAR_SITTING_IN_CAR sw7_banger2 sw7_sweethearse
	 					IF IS_CHAR_SITTING_IN_CAR scplayer sw7_sweethearse
							FREEZE_CAR_POSITION sw7_sweethearse FALSE
							CLEAR_HELP
							CLEAR_PRINTS
							SET_CAMERA_BEHIND_PLAYER
					 	    IF sw7_timeron = 0
							DISPLAY_ONSCREEN_TIMER_WITH_STRING sw7_funeraltimer TIMER_DOWN TWAR620

							SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE gan1_gzs  //back to starting value
							SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE gan2_gzs //back to starting value
							REMOVE_ANIMATION SMOKING

							sw7_timeron = 1
							ENDIF

					 		REMOVE_BLIP sw7_hearseB
							ADD_BLIP_FOR_COORD 799.0102 -1074.0321 23.0115 sw7_cementB
							
							sw7_checkgangblips = 1	// was 0
							sw7_blipswap = 0
							sw7_convo_counter = 1

		 					sw7_nextprompt = 20
							TIMERA = 0

							SWITCH_ROADS_OFF 772.2430 -1050.3231 10.2931 806.2723 -1130.1954 23.8359 
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		 ENDIF
 	ENDIF
ENDIF


IF sw7_ctskip_needed = 1 
	IF IS_BUTTON_PRESSED PAD1 CROSS
		IF sw7_missionprogress < 10
			sw7_missionprogress = 10
			sw7_ctskip_needed = 0 // Don't want to read PS2 joypad anymore
		ENDIF
	ENDIF
ENDIF

IF sw7_newctskip_needed = 1
	IF IS_BUTTON_PRESSED PAD1 CROSS
		sw7_newcutscene = 15
	ENDIF
ENDIF

IF IS_CHAR_DEAD sweet
	CLEAR_PRINTS
	PRINT_NOW TWAR638 10000 1
	GOTO mission_sweet7_failed
ENDIF


IF IS_CAR_DEAD sw7_pursuitcar
	IF NOT IS_CHAR_DEAD sweet
	 IF NOT IS_CHAR_IN_ANY_CAR sweet
	 	CLEAR_PRINTS
		PRINT_NOW TWAR648 6000 1
		GOTO mission_sweet7_failed
	 ENDIF
	ENDIF
ENDIF

IF sw7_missionprogress = 0 

   IF sw7_nextprompt = 20

	   IF NOT IS_CAR_DEAD sw7_sweethearse
			
			// Intro convo
			IF IS_CHAR_IN_CAR scplayer sw7_sweethearse
			
				IF sw7_helpfix = 0
			 	IF timera > 20000
					PRINT_HELP TWAR656
					sw7_helpfix = 1
				ENDIF
				ENDIF


				IF sw7_convo_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_convo_counter
							CASE 1
								sw7_counter = 23
								sw7_convo_counter = 2
								BREAK
						   	CASE 2 
								sw7_counter = 35
								sw7_convo_counter =  3
								BREAK
							CASE 3
								sw7_counter = 36
								sw7_convo_counter = 4

								BREAK
							CASE 4
								sw7_counter = 0
								PRINT_NOW TWAR631 6000 1
								sw7_convo_counter = 0 // finish convo
								BREAK

						ENDSWITCH
					ENDIF
				ENDIF
			ENDIF


			//Get back in the car variations
				IF sw7_leftcar_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_leftcar_counter
							CASE 1
								IF sw7_random = 0
									sw7_counter = 24
								ENDIF
							 	IF sw7_random = 1
									sw7_counter = 26
								ENDIF

								sw7_leftcar_counter = 2
								BREAK
							CASE 2	
								sw7_counter = 0
								PRINT_NOW TWAR632 6000 1
								sw7_leftcar_counter = 0 // finish convo
								BREAK

						ENDSWITCH
					ENDIF
				ENDIF


			// Back in car, get to location variations
				IF sw7_backcar_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_backcar_counter
							CASE 1
								IF sw7_random = 0
									sw7_counter = 25
								ENDIF
								sw7_backcar_counter = 2
								BREAK
							CASE 2	
								sw7_counter = 0
								PRINT_NOW TWAR631 6000 1
								sw7_backcar_counter = 0 // finish convo
								BREAK

						ENDSWITCH
					ENDIF
				ENDIF


			
			
			IF sw7_blipswap = 0
				IF NOT IS_CHAR_IN_CAR scplayer sw7_sweethearse
			   

					ADD_BLIP_FOR_CAR sw7_sweethearse sw7_hearseB
					SET_RADIO_CHANNEL RS_NEW_JACK_SWING 

					SET_BLIP_AS_FRIENDLY sw7_hearseB TRUE
					REMOVE_BLIP sw7_cementB
					CLEAR_PRINTS
				   
					sw7_blipswap = 1	
					sw7_convo_counter = 0 // finish convo
					sw7_leftcar_counter = 1 //Cue get back in car variations	
					GENERATE_RANDOM_INT_IN_RANGE 0 2 sw7_random	 // choice of two text options
											  
				ENDIF

			ENDIF

			IF sw7_blipswap = 1
				IF NOT IS_CHAR_DEAD sw7_banger1
				IF NOT IS_CHAR_DEAD sw7_banger2


					IF IS_CHAR_IN_CAR scplayer sw7_sweethearse
					IF IS_CHAR_SITTING_IN_CAR sw7_banger1 sw7_sweethearse
					IF IS_CHAR_SITTING_IN_CAR sw7_banger2 sw7_sweethearse

						REMOVE_BLIP sw7_hearseB							
						ADD_BLIP_FOR_COORD 799.0102 -1074.0321 23.0115 sw7_cementB
		
						CLEAR_PRINTS
						sw7_blipswap = 0
						sw7_convo_counter = 0 //finish convo
						sw7_backcar_counter = 1  //Cue back in car, get to location variations
						GENERATE_RANDOM_INT_IN_RANGE 0 1 sw7_random	 // only one text option
					ENDIF
					ENDIF
					ENDIF
				ENDIF
				ENDIF
			ENDIF

		   	GET_AREA_VISIBLE sw7_visible
		   	IF sw7_visible = 0
		 		IF NOT IS_MINIGAME_IN_PROGRESS	
					SET_PLAYER_CONTROL player1 ON
			 	ENDIF
			ENDIF


			IF sw7_blipswap = 0
			IF IS_CHAR_IN_CAR scplayer sw7_sweethearse

				IF LOCATE_CHAR_IN_CAR_3D scplayer  799.0102 -1074.0321 23.0115 3.8 3.8 4.0 TRUE
					SET_PLAYER_CONTROL player1 OFF

					IF IS_CAR_STOPPED sw7_sweethearse
						REMOVE_BLIP sw7_cementB
					   	SET_PLAYER_CONTROL player1 OFF
						CLEAR_ONSCREEN_TIMER sw7_funeraltimer

						DO_FADE 500 FADE_OUT
						WHILE GET_FADING_STATUS
   							 WAIT 0
						ENDWHILE
						SET_FIXED_CAMERA_POSITION 790.7784 -1072.1392 26.2131 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 791.6847 -1072.3779 25.8645 JUMP_CUT

						LOAD_SCENE 803.5742 -1075.7522 23.3957
						WAIT 0
					  

						IF NOT IS_CAR_DEAD sw7_sweethearse
						  	FREEZE_CAR_POSITION sw7_sweethearse	TRUE
						ENDIF


						SET_PLAYER_CONTROL player1 OFF
	  		
						SWITCH_WIDESCREEN ON

					    DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
    						WAIT 0
						ENDWHILE

						CLEAR_ONSCREEN_TIMER sw7_funeraltimer

						DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
    						WAIT 0
						ENDWHILE

						sw7_checkgangblips = 0
						sw7_missionprogress = 1
						sw7_newcutscene = 1
						timera = 0

						IF sw7_audio_underway = 0
						   sw7_counter = 29	
						ENDIF

						IF sw7_missionprogress = -999
						CREATE_CHAR PEDTYPE_MISSION2 FAM2 820.7578 -1078.6709 22.9313 sw7_grove1
					    SET_CHAR_HEADING sw7_grove1 258.1012
						SET_CHAR_DECISION_MAKER sw7_grove1 sw7_emptydm
						SET_CHAR_HEALTH sw7_grove1 500		
						SET_CHAR_MAX_HEALTH sw7_grove1 500							
						SET_CHAR_NEVER_TARGETTED sw7_grove1 TRUE
						GIVE_WEAPON_TO_CHAR sw7_grove1 WEAPONTYPE_MICRO_UZI 99999
						
						CREATE_CHAR PEDTYPE_MISSION2 FAM2 820.2401 -1075.7628 23.0894 sw7_grove2
					 	SET_CHAR_HEADING sw7_grove2 200.7
						SET_CHAR_DECISION_MAKER sw7_grove2 sw7_emptydm
						SET_CHAR_HEALTH sw7_grove2 500
						SET_CHAR_MAX_HEALTH sw7_grove2 500
						SET_CHAR_NEVER_TARGETTED sw7_grove2 TRUE
						GIVE_WEAPON_TO_CHAR sw7_grove2 WEAPONTYPE_MICRO_UZI 99999
						
						ENDIF

						MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
						MARK_MODEL_AS_NO_LONGER_NEEDED FAM3
						MARK_MODEL_AS_NO_LONGER_NEEDED FAM1

						IF NOT IS_CHAR_DEAD sweet
							SET_CHAR_DECISION_MAKER sweet sw7_emptydm
							CLEAR_CHAR_TASKS sweet
							ADD_BLIP_FOR_CHAR sweet sw7_sweetB
							SET_BLIP_AS_FRIENDLY sw7_sweetB TRUE
							OPEN_SEQUENCE_TASK sw7_sweetjumpseq
					  		TASK_LEAVE_ANY_CAR -1
					
						
							TASK_GO_STRAIGHT_TO_COORD -1 804.5760 -1075.7946 23.3946 PEDMOVE_WALK 10000
							TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
							CLOSE_SEQUENCE_TASK sw7_sweetjumpseq
							PERFORM_SEQUENCE_TASK sweet sw7_sweetjumpseq
							CLEAR_SEQUENCE_TASK sw7_sweetjumpseq

							timera = 0
						ENDIF

							WHILE timera < 1000
								WAIT 0
							ENDWHILE	 					
							

						IF NOT IS_CHAR_DEAD sw7_banger1
							SET_CHAR_DECISION_MAKER sw7_banger1 sw7_emptydm
							REMOVE_CHAR_FROM_GROUP sw7_banger1
							CLEAR_CHAR_TASKS sw7_banger1
							OPEN_SEQUENCE_TASK sw7_banger1jumpseq
					  		TASK_LEAVE_ANY_CAR -1
							TASK_GO_STRAIGHT_TO_COORD -1 804.8553 -1074.4220 23.4385 PEDMOVE_WALK 10000
							CLOSE_SEQUENCE_TASK sw7_banger1jumpseq
							PERFORM_SEQUENCE_TASK sw7_banger1 sw7_banger1jumpseq
							CLEAR_SEQUENCE_TASK sw7_banger1jumpseq
						ENDIF

							WHILE timera < 1750
								WAIT 0
							ENDWHILE	
							

						IF NOT IS_CHAR_DEAD sw7_banger2
							SET_CHAR_DECISION_MAKER sw7_banger2 sw7_emptydm
							REMOVE_CHAR_FROM_GROUP sw7_banger2
							CLEAR_CHAR_TASKS sw7_banger2
							OPEN_SEQUENCE_TASK sw7_banger2jumpseq
					  		TASK_LEAVE_ANY_CAR -1
							TASK_GO_STRAIGHT_TO_COORD -1 805.1254 -1072.3445 23.5187 PEDMOVE_RUN 10000

							CLOSE_SEQUENCE_TASK sw7_banger2jumpseq
							PERFORM_SEQUENCE_TASK sw7_banger2 sw7_banger2jumpseq
							CLEAR_SEQUENCE_TASK sw7_banger2jumpseq

						ENDIF
						
					ENDIF
				ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

ENDIF //sw7_missionprogress = 0 condition check


IF sw7_newcutscene = 1
	IF timera > 500
	sw7_newcutscene = 4
	timera = 0
	ENDIF
ENDIF


IF sw7_newcutscene = 4
	IF timera > 1000
	IF sw7_newskipfix = 0
	sw7_newctskip_needed = 1
	sw7_newskipfix = 1
	ENDIF

	ENDIF

	IF timera > 3000
		
  		CLEAR_AREA 804.5760 -1075.7946 23.3946 5.0 TRUE
		SET_FIXED_CAMERA_POSITION 813.1065 -1071.7083 27.3524 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 812.2476 -1072.2009 27.2129 JUMP_CUT
	
		sw7_newcutscene = 5

		IF sw7_audio_underway = 0
		ENDIF
		
		IF NOT IS_CHAR_DEAD scplayer
		   	CLEAR_CHAR_TASKS scplayer
			OPEN_SEQUENCE_TASK sw7_playerwallseq
	  		TASK_LEAVE_ANY_CAR -1
			TASK_GO_STRAIGHT_TO_COORD -1 803.7976 -1075.8824 23.3933 PEDMOVE_RUN 10000
			TASK_ACHIEVE_HEADING -1 272.0

			CLOSE_SEQUENCE_TASK sw7_playerwallseq
			PERFORM_SEQUENCE_TASK scplayer sw7_playerwallseq
			CLEAR_SEQUENCE_TASK sw7_playerwallseq
		ENDIF

		timera = 0

		IF NOT IS_CHAR_DEAD sw7_banger2
			CLEAR_CHAR_TASKS sw7_banger2
			SET_CHAR_COORDINATES sw7_banger2  805.1254 -1072.3445 23.5187
			SET_CHAR_HEADING sw7_banger2 230.0
			OPEN_SEQUENCE_TASK sw7_banger2jumpseq

			TASK_CLIMB -1 TRUE
			TASK_GO_STRAIGHT_TO_COORD -1 820.4114 -1076.6030 23.0623 PEDMOVE_RUN 10000
			TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
			CLOSE_SEQUENCE_TASK sw7_banger2jumpseq
			PERFORM_SEQUENCE_TASK sw7_banger2 sw7_banger2jumpseq
			CLEAR_SEQUENCE_TASK sw7_banger2jumpseq

		ENDIF
	ENDIF
ENDIF


IF sw7_newcutscene = 5
	IF timera > 200
		
		CLEAR_AREA 804.5760 -1075.7946 23.3946 5.0 TRUE
		IF NOT IS_CHAR_DEAD sweet
			CLEAR_CHAR_TASKS sweet
			SET_CHAR_COORDINATES sweet 804.5760 -1075.7946 23.3946
			SET_CHAR_HEADING sweet 271.0
			OPEN_SEQUENCE_TASK sw7_sweetjumpseq
						
  			TASK_CLIMB -1 TRUE
			CLOSE_SEQUENCE_TASK sw7_sweetjumpseq
			PERFORM_SEQUENCE_TASK sweet sw7_sweetjumpseq
			CLEAR_SEQUENCE_TASK sw7_sweetjumpseq

		ENDIF
		timera = 0
		sw7_newcutscene = 6
	ENDIF

ENDIF

IF sw7_newcutscene = 6
	IF timera > 1000
		IF NOT IS_CHAR_DEAD sw7_banger1 
			CLEAR_CHAR_TASKS sw7_banger1
			SET_CHAR_COORDINATES sw7_banger1 804.8553 -1074.4220 23.4385
			SET_CHAR_HEADING  sw7_banger1 262.0
			OPEN_SEQUENCE_TASK sw7_banger1jumpseq
			TASK_CLIMB -1 TRUE
			TASK_GO_STRAIGHT_TO_COORD -1 818.4114 -1076.6030 23.0623 PEDMOVE_RUN 10000
			TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
			CLOSE_SEQUENCE_TASK sw7_banger1jumpseq
			PERFORM_SEQUENCE_TASK sw7_banger1 sw7_banger1jumpseq
			CLEAR_SEQUENCE_TASK sw7_banger1jumpseq

		ENDIF
		sw7_newcutscene = 10
		timera = 0

	ENDIF
ENDIF


IF sw7_newcutscene = 10
	
	IF timera > 2000
	   
	   IF NOT IS_CHAR_DEAD sweet
		  CLEAR_CHAR_TASKS sweet
		  TASK_GO_STRAIGHT_TO_COORD sweet 818.4114 -1076.6030 23.0623 PEDMOVE_RUN 10000
		ENDIF
		IF sw7_audio_underway = 0
			sw7_counter = 31	
		ENDIF

		sw7_newcutscene = 13
		timera = 0
	ENDIF
ENDIF


IF sw7_newcutscene = 13
	IF sw7_audio_underway = 0
		IF timera > 2000
	 		sw7_newcutscene = 15
		ENDIF
	ENDIF
ENDIF


IF sw7_newcutscene = 15
	
	sw7_newctskip_needed = 0
	CLEAR_CHAR_TASKS scplayer
   	SET_CAMERA_BEHIND_PLAYER

	SWITCH_WIDESCREEN OFF 
	RESTORE_CAMERA_JUMPCUT	
					

  	CLEAR_PRINTS					   
	//DO_FADE 500 FADE_IN
	//WHILE GET_FADING_STATUS
      //  WAIT 0
	//ENDWHILE

	
	SET_PLAYER_CONTROL player1 ON
	sw7_newcutscene = 20	
	CLEAR_PRINTS
	PRINT_NOW TWAR635 6000 1
	
	IF NOT IS_CHAR_DEAD sweet
		//IF NOT IS_CHAR_IN_ANY_CAR sweet
		 	CLEAR_CHAR_TASKS_IMMEDIATELY sweet
		  	SET_CHAR_COORDINATES sweet 818.4114 -1076.6030 23.0623 
		 	SET_CHAR_HEADING sweet 279.0
			TASK_TURN_CHAR_TO_FACE_CHAR sweet scplayer
		//ENDIF
		/*
		IF IS_CHAR_IN_ANY_CAR sweet
		  	CLEAR_CHAR_TASKS_IMMEDIATELY sweet
		  	WARP_CHAR_FROM_CAR_TO_COORD sweet 818.4114 -1076.6030 23.0623 
		  	SET_CHAR_HEADING sweet 279.0
		ENDIF
		*/
	ENDIF


	IF NOT IS_CHAR_DEAD sw7_banger1
		//IF NOT IS_CHAR_IN_ANY_CAR sw7_banger1
			CLEAR_CHAR_TASKS_IMMEDIATELY sw7_banger1
			SET_CHAR_COORDINATES sw7_banger1 816.0391 -1072.6071 23.7100
			SET_CHAR_HEADING sw7_banger1 248.0 
		//ENDIF
		/*
		IF IS_CHAR_IN_ANY_CAR sw7_banger1
			CLEAR_CHAR_TASKS_IMMEDIATELY sw7_banger1
			WARP_CHAR_FROM_CAR_TO_COORD sw7_banger1 817.2468 -1077.1143 23.0443
			SET_CHAR_HEADING sw7_banger1 248.0 
		ENDIF
		 */
	ENDIF





	IF NOT IS_CHAR_DEAD sw7_banger2
		//IF NOT IS_CHAR_IN_ANY_CAR sw7_banger2
			CLEAR_CHAR_TASKS_IMMEDIATELY sw7_banger2
			SET_CHAR_COORDINATES sw7_banger2 815.9681 -1073.7233 23.5666				
			SET_CHAR_HEADING sw7_banger2 253.0 
		//ENDIF
		/*
		IF IS_CHAR_IN_ANY_CAR sw7_banger2

			CLEAR_CHAR_TASKS_IMMEDIATELY sw7_banger2
			WARP_CHAR_FROM_CAR_TO_COORD sw7_banger2 818.9229 -1075.7800 23.1123				
			SET_CHAR_HEADING sw7_banger2 260.0 
		ENDIF
		*/
	ENDIF

ENDIF



IF sw7_missionprogress = 1 
   
   IF sw7_nextprompt = 20

		IF NOT IS_CHAR_DEAD sweet
			

		  	IF sw7_cockedandloaded = 0
			  	IF LOCATE_CHAR_ANY_MEANS_3D sweet 818.4114 -1076.6030 23.0623 4.0 4.0 4.0 FALSE
					FREEZE_CHAR_POSITION sweet TRUE
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 3.0 3.0 FALSE  // was 10.0
					   CLEAR_CHAR_TASKS sweet
					   //TASK_TURN_CHAR_TO_FACE_CHAR sweet scplayer

					   IF NOT IS_CHAR_DEAD sw7_grove2
						//TASK_PLAY_ANIM sw7_grove2 RIFLE_load_pro PED 8.0 FALSE FALSE FALSE TRUE -1
						ENDIF

						IF NOT IS_CHAR_DEAD sw7_grove1
						//TASK_PLAY_ANIM sw7_grove1 M_smkstnd_loop SMOKING 8.0 FALSE FALSE FALSE TRUE -1
						ENDIF


					   	sw7_cockedandloaded = 1
						FREEZE_CHAR_POSITION sweet FALSE
						//write_debug check
					   
					ENDIF
				ENDIF
			ENDIF



			IF LOCATE_CHAR_ANY_MEANS_3D sweet 818.4114 -1076.6030 23.0623 2.0 2.0 2.0 FALSE
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 2.0 2.0 FALSE  // was 8.0
				   sw7_missionprogress = 4
				   REMOVE_BLIP sw7_sweetB
				   /*
				   IF NOT IS_CHAR_DEAD sw7_banger1
						SET_CHAR_COORDINATES sw7_banger1 814.8055 -1073.2092 23.3404
						SET_CHAR_HEADING sw7_banger1 289.0 
				   		SET_GROUP_MEMBER sw7_group sw7_banger1
				   ENDIF
				   IF NOT IS_CHAR_DEAD sw7_banger2
						SET_CHAR_COORDINATES sw7_banger2 816.2036 -1077.6571 23.0215
						SET_CHAR_HEADING sw7_banger2 234.0 

						SET_GROUP_MEMBER sw7_group sw7_banger2
				   ENDIF
					*/
				ENDIF
			ENDIF
		ENDIF
		
	  

   ENDIF
	

ENDIF //sw7_missionprogress = 1 condition check


IF sw7_missionprogress < 1
	IF IS_CAR_DEAD sw7_sweethearse
			CLEAR_PRINTS
			PRINT_NOW TWAR633 8000 1
			GOTO mission_sweet7_failed
	ENDIF
ENDIF


// Various fail conditions. Wrecking arrival car, attacking mourners before Kane arrives.
IF sw7_missionprogress < 5

		IF DOES_CHAR_EXIST sw7_banger1
		IF IS_CHAR_DEAD sw7_banger1
			CLEAR_PRINTS
		   GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
			sw7_convo_counter = 21
			sw7_missionprogress = 999
		ENDIF
		ENDIF

		IF DOES_CHAR_EXIST sw7_banger2
		IF IS_CHAR_DEAD sw7_banger2
			CLEAR_PRINTS
		  GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
			sw7_convo_counter = 21
			sw7_missionprogress = 999
		ENDIF	
		ENDIF		 
	 
		

		IF DOES_CHAR_EXIST sw7_protector1

			/*
			IF sw7_endanims = 0	  
			
			
			
				IF NOT IS_CHAR_PLAYING_ANIM sw7_protector1 mrnM_loop 
					CLEAR_CHAR_TASKS sw7_protector1
					TASK_PLAY_ANIM sw7_protector1 mrnM_loop graveyard 4.0 1 FALSE FALSE FALSE -1
					sw7_endanims = 1
				ENDIF
			ENDIF
			*/




			IF sw7_endanims = 0
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_protector1	scplayer
					sw7_endanims = 1
					IF NOT IS_CHAR_DEAD sw7_protector1
					CLEAR_CHAR_TASKS sw7_protector1
					TASK_KILL_CHAR_ON_FOOT sw7_protector1 scplayer
					ENDIF

					IF NOT IS_CHAR_DEAD sw7_protector2
						CLEAR_CHAR_TASKS sw7_protector2
						TASK_KILL_CHAR_ON_FOOT sw7_protector2 scplayer
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_protector3
						CLEAR_CHAR_TASKS sw7_protector3
						TASK_KILL_CHAR_ON_FOOT sw7_protector3 scplayer
					ENDIF

					
					IF NOT IS_CHAR_DEAD sw7_greetinface
						CLEAR_CHAR_TASKS sw7_greetinface
						TASK_SMART_FLEE_CHAR sw7_greetinface scplayer 100.0 -1
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_preach
						CLEAR_CHAR_TASKS sw7_preach
						TASK_SMART_FLEE_CHAR sw7_preach scplayer 100.0 -1
					ENDIF


					//WAIT 3000
					GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
					sw7_convo_counter = 41
					sw7_missionprogress = 999
				ENDIF
			ENDIF
		ENDIF	 

		IF sw7_endanims = 0
			IF DOES_CHAR_EXIST sw7_protector2

				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_protector2	scplayer
					
					sw7_endanims = 1
					IF NOT IS_CHAR_DEAD sw7_protector2
					CLEAR_CHAR_TASKS sw7_protector2

					TASK_KILL_CHAR_ON_FOOT sw7_protector2 scplayer
					ENDIF

					IF NOT IS_CHAR_DEAD sw7_protector1
						CLEAR_CHAR_TASKS sw7_protector1
						TASK_KILL_CHAR_ON_FOOT sw7_protector1 scplayer
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_protector3
						CLEAR_CHAR_TASKS sw7_protector3
						TASK_KILL_CHAR_ON_FOOT sw7_protector3 scplayer
					ENDIF


					IF NOT IS_CHAR_DEAD sw7_greetinface
						CLEAR_CHAR_TASKS sw7_greetinface
						TASK_SMART_FLEE_CHAR sw7_greetinface scplayer 100.0 -1
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_preach
						CLEAR_CHAR_TASKS sw7_preach
						TASK_SMART_FLEE_CHAR sw7_preach scplayer 100.0 -1
					ENDIF
					
					GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
					sw7_convo_counter = 41
					sw7_missionprogress = 999

				ENDIF
			ENDIF
		ENDIF
		
		IF sw7_endanims = 0
			IF DOES_CHAR_EXIST sw7_protector3 
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_protector3	scplayer
					sw7_endanims = 1
					IF NOT IS_CHAR_DEAD sw7_protector3
					CLEAR_CHAR_TASKS sw7_protector3

					TASK_KILL_CHAR_ON_FOOT sw7_protector3 scplayer
					ENDIF

					IF NOT IS_CHAR_DEAD sw7_protector1
						CLEAR_CHAR_TASKS sw7_protector1
						TASK_KILL_CHAR_ON_FOOT sw7_protector1 scplayer
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_protector2
						CLEAR_CHAR_TASKS sw7_protector2
						TASK_KILL_CHAR_ON_FOOT sw7_protector2 scplayer
					ENDIF




					IF NOT IS_CHAR_DEAD sw7_greetinface
						CLEAR_CHAR_TASKS sw7_greetinface
						TASK_SMART_FLEE_CHAR sw7_greetinface scplayer 100.0 -1
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_preach
						CLEAR_CHAR_TASKS sw7_preach
						TASK_SMART_FLEE_CHAR sw7_preach scplayer 100.0 -1
					ENDIF

					GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
					sw7_convo_counter = 41
					sw7_missionprogress = 999

				ENDIF
			ENDIF		
		ENDIF

		IF sw7_endanims = 0
			IF DOES_CHAR_EXIST sw7_preach 
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_preach scplayer

					sw7_endanims = 1

					IF NOT IS_CHAR_DEAD sw7_protector1
						CLEAR_CHAR_TASKS sw7_protector1
						TASK_KILL_CHAR_ON_FOOT sw7_protector1 scplayer
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_protector2
						CLEAR_CHAR_TASKS sw7_protector2
						TASK_KILL_CHAR_ON_FOOT sw7_protector2 scplayer
					ENDIF

					IF NOT IS_CHAR_DEAD sw7_protector3
						CLEAR_CHAR_TASKS sw7_protector3
						TASK_KILL_CHAR_ON_FOOT sw7_protector3 scplayer
					ENDIF


					IF NOT IS_CHAR_DEAD sw7_greetinface
						CLEAR_CHAR_TASKS sw7_greetinface
						TASK_SMART_FLEE_CHAR sw7_greetinface scplayer 100.0 -1
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_preach
						CLEAR_CHAR_TASKS sw7_preach
						TASK_SMART_FLEE_CHAR sw7_preach scplayer 100.0 -1
					ENDIF

					GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
					sw7_convo_counter = 41
					sw7_missionprogress = 999

				ENDIF
			ENDIF	
		ENDIF



		IF sw7_endanims = 0
			IF DOES_CHAR_EXIST sw7_greetinface 
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_greetinface scplayer

					sw7_endanims = 1

					IF NOT IS_CHAR_DEAD sw7_protector1
						CLEAR_CHAR_TASKS sw7_protector1
						TASK_KILL_CHAR_ON_FOOT sw7_protector1 scplayer
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_protector2
						CLEAR_CHAR_TASKS sw7_protector2
						TASK_KILL_CHAR_ON_FOOT sw7_protector2 scplayer
					ENDIF

					IF NOT IS_CHAR_DEAD sw7_protector3
						CLEAR_CHAR_TASKS sw7_protector3
						TASK_KILL_CHAR_ON_FOOT sw7_protector3 scplayer
					ENDIF


					IF NOT IS_CHAR_DEAD sw7_greetinface
						CLEAR_CHAR_TASKS sw7_greetinface
						TASK_SMART_FLEE_CHAR sw7_greetinface scplayer 100.0 -1
					ENDIF
					IF NOT IS_CHAR_DEAD sw7_preach
						CLEAR_CHAR_TASKS sw7_preach
						TASK_SMART_FLEE_CHAR sw7_preach scplayer 100.0 -1
					ENDIF

					GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
					sw7_convo_counter = 41
					sw7_missionprogress = 999

				ENDIF
			ENDIF
		ENDIF
ENDIF


IF sw7_missionprogress = 999	   // mission failed audio
	 
	 
	 IF sw7_convo_counter  > 20	  // killed one of the two guys needed for the job
	 AND sw7_convo_counter < 30
				IF sw7_audio_underway = 0
	
					SWITCH sw7_convo_counter
				  		CASE 21
							IF sw7_random = 0
								sw7_counter = 17
							ENDIF
							IF sw7_random = 1
								sw7_counter = 18
							ENDIF
							IF sw7_random = 2
								sw7_counter = 19
							ENDIF

							sw7_convo_counter = 22
							BREAK
						CASE 22	
							sw7_counter = 0
							PRINT_NOW TWAR658 6000 1
							sw7_convo_counter = 0 // finish convo
							GOTO mission_sweet7_failed
							BREAK


					ENDSWITCH
				   //	sw7_audio_underway = 1
				ENDIF
	ENDIF
	
	 IF sw7_convo_counter  > 30	  // funeral will be over, time's ran out
	 AND sw7_convo_counter < 40
				IF sw7_audio_underway = 0
	
					SWITCH sw7_convo_counter
				  		CASE 31
							IF sw7_random = 0
								sw7_counter = 27
							ENDIF
							IF sw7_random = 1
								sw7_counter = 28
							ENDIF
						  
							sw7_convo_counter = 32
							BREAK
						CASE 32	
							sw7_counter = 0
							PRINT_NOW TWAR619 6000 1
							sw7_convo_counter = 0 // finish convo
							GOTO mission_sweet7_failed
							BREAK


					ENDSWITCH
				   //	sw7_audio_underway = 1
				ENDIF
	ENDIF


	IF sw7_convo_counter  > 40	  // Started high jinks at the cemetery before kane has arrived
	AND sw7_convo_counter < 50
				IF sw7_audio_underway = 0
	
					SWITCH sw7_convo_counter
				  		CASE 41
							IF sw7_random = 0
								sw7_counter = 37
							ENDIF
							IF sw7_random = 1
								sw7_counter = 38
							ENDIF
						  		IF sw7_random = 1
								sw7_counter = 39
							ENDIF

							sw7_convo_counter = 42
							BREAK
						CASE 42	
							sw7_counter = 0
							PRINT_NOW TWAR625 6000 1
							sw7_convo_counter = 0 // finish convo
							GOTO mission_sweet7_failed
							BREAK


					ENDSWITCH
				   //	sw7_audio_underway = 1
				ENDIF
	ENDIF




	IF sw7_convo_counter  > 60	  // Kane has escaped in his getaway car
	AND sw7_convo_counter < 70
				IF sw7_audio_underway = 0
	
					SWITCH sw7_convo_counter
				  		CASE 61
							IF sw7_random = 0
								sw7_counter = 61
							ENDIF
							IF sw7_random = 1
								sw7_counter = 62
							ENDIF
						  		IF sw7_random = 2
								sw7_counter = 63
							ENDIF

							sw7_convo_counter = 62
							BREAK
						CASE 62	
							sw7_counter = 0
						
							CLEAR_PRINTS
							PRINT_NOW TWAR627 8000 1   // Kane in his own neighbourhood	fail condition
							DELETE_CHAR sw7_kane
							
							
							sw7_convo_counter = 0 // finish convo
							GOTO mission_sweet7_failed
							BREAK


					ENDSWITCH
				   //	sw7_audio_underway = 1
				ENDIF
	ENDIF






ENDIF


IF sw7_missionprogress = 4

		//IF LOCATE_CHAR_ANY_MEANS_3D scplayer 813.6328 -1102.5841 24.7974 2.0 2.0 2.0 TRUE
		  //	sw7_missionprogress = 5
		//ENDIF

			
	   //	IF HAS_PICKUP_BEEN_COLLECTED sw7_gun
			//REMOVE_BLIP sw7_mausoB
			//WAIT 1000
			sw7_missionprogress = 5

			IF NOT IS_CAR_DEAD sw7_sweethearse
				CLOSE_ALL_CAR_DOORS sw7_sweethearse

				FREEZE_CAR_POSITION sw7_sweethearse FALSE

				LOCK_CAR_DOORS sw7_sweethearse CARLOCK_LOCKOUT_PLAYER_ONLY
				MARK_CAR_AS_NO_LONGER_NEEDED sw7_sweethearse
			ENDIF


			CLEAR_AREA 894.0744 -1073.4971 23.3203 125.0 TRUE
			SET_PLAYER_CONTROL player1 OFF
			//CREATE_CAR admiral 897.3787 -1072.4325 23.3203 sw7_kanehearse
		
		//	LOAD_SCENE 897.3787 -1073.5021 23.3203
 		
 			CREATE_CAR admiral 897.3787 -1073.5021 23.3203 sw7_kanehearse
 		   //	SET_CAR_HEADING sw7_kanehearse 84.4729
			
			SET_CAR_HEADING sw7_kanehearse 90.2
			CHANGE_CAR_COLOUR sw7_kanehearse 25 40
			LOCK_CAR_DOORS sw7_kanehearse CARLOCK_LOCKOUT_PLAYER_ONLY

			SET_CAR_PROOFS sw7_kanehearse TRUE TRUE TRUE TRUE TRUE
			MARK_MODEL_AS_NO_LONGER_NEEDED admiral


			CREATE_CHAR_AS_PASSENGER sw7_kanehearse PEDTYPE_MISSION1 BALLAS3 0 sw7_kane	
			CREATE_CHAR_INSIDE_CAR sw7_kanehearse PEDTYPE_MISSION1 BALLAS2 sw7_kanechauffeur
			GIVE_WEAPON_TO_CHAR sw7_kanechauffeur WEAPONTYPE_MICRO_UZI 2000

			SET_CHAR_DECISION_MAKER sw7_kane sw7_goondm
			SET_CHAR_DECISION_MAKER sw7_kanechauffeur sw7_goondm

			/* Altered
			SET_CHAR_HEALTH sw7_kane 1000
			*/
			SET_CHAR_HEALTH sw7_kane 260
			SET_CHAR_MAX_HEALTH sw7_kane 300
			////WRITE_DEBUG settinghealth 
			SET_CHAR_ACCURACY sw7_kane 100
			ADD_ARMOUR_TO_CHAR sw7_kane 100


			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw7_kane TRUE
			SET_CHAR_SUFFERS_CRITICAL_HITS sw7_kane FALSE
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE sw7_kane FALSE
			SET_CHAR_ALLOWED_TO_DUCK sw7_kane FALSE

			CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 876.4128 -1068.8662 23.3745 sw7_protector4
			SET_CHAR_DECISION_MAKER sw7_protector4 sw7_emptydm
			SET_CHAR_HEADING sw7_protector4 166.6
			GIVE_WEAPON_TO_CHAR sw7_protector4 WEAPONTYPE_MICRO_UZI 2000
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw7_protector4 TRUE
			SET_CHAR_ACCURACY sw7_protector4 90
			SET_CHAR_HEALTH sw7_protector4 120




			CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 922.7446 -1066.2141 23.3203 sw7_protector5
			SET_CHAR_DECISION_MAKER sw7_protector5 sw7_emptydm
			SET_CHAR_HEADING sw7_protector5 66.6
			GIVE_WEAPON_TO_CHAR sw7_protector5 WEAPONTYPE_MICRO_UZI 2000
			SET_CHAR_SHOOT_RATE sw7_protector5 15
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw7_protector5 TRUE
			SET_CHAR_ACCURACY sw7_protector5 90
			SET_CHAR_HEALTH sw7_protector5 120






			CREATE_CHAR PEDTYPE_MISSION1 BALLAS2 922.4503 -1117.9081 23.0517 sw7_protector6
			SET_CHAR_DECISION_MAKER sw7_protector6 sw7_emptydm
			SET_CHAR_HEADING sw7_protector6 357.6
			GIVE_WEAPON_TO_CHAR sw7_protector6 WEAPONTYPE_MICRO_UZI 2000
			SET_CHAR_SHOOT_RATE sw7_protector6 15
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER sw7_protector6 TRUE
			SET_CHAR_ACCURACY sw7_protector6 90
			SET_CHAR_HEALTH sw7_protector6 120






	   //	ENDIF


		



	
ENDIF // sw7_missionprogress = 1 condition check

IF sw7_checkkane = 0
	IF sw7_missionprogress > 4
	AND sw7_missionprogress < 999
		IF NOT IS_CHAR_DEAD sw7_kane
		 	GET_CHAR_HEALTH sw7_kane sw7_kanefuckedhealth
			 sw7_kanehealth = sw7_kanefuckedhealth / 3

		ENDIF

		IF sw7_pursuitunderway = 0
			IF IS_CHAR_DEAD sw7_kane
    			REMOVE_BLIP sw7_kaneb
                sw7_missionprogress = 1352 // Added 07.10.04

				GOSUB sw7_mopup	
				CLEAR_PRINTS
				sw7_checkkane = 1
				sw7_grove1fight = 99
				sw7_grove2fight = 99
				sw7_sweetfight = 99
				sw7_convo_counter = 1
				IF NOT IS_CAR_DEAD sw7_pursuitcar
				
				IF NOT IS_CHAR_DEAD sweet
			        LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_UNLOCKED
				    LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_LOCKOUT_PLAYER_ONLY
				
					CLEAR_CHAR_TASKS sweet
					OPEN_SEQUENCE_TASK sw7_mopupseq
			  	  		TASK_GO_STRAIGHT_TO_COORD -1 930.8062 -1114.4586 23.0940 PEDMOVE_RUN 10000
						TASK_ENTER_CAR_AS_PASSENGER -1 sw7_pursuitcar 30000 -1
					CLOSE_SEQUENCE_TASK sw7_mopupseq
					PERFORM_SEQUENCE_TASK sweet sw7_mopupseq
					CLEAR_SEQUENCE_TASK sw7_mopupseq
					ENDIF
				ENDIF


				IF NOT IS_CHAR_DEAD sw7_grove1
					CLEAR_CHAR_TASKS sw7_grove1
					OPEN_SEQUENCE_TASK sw7_grove1guardseq
						TASK_GO_STRAIGHT_TO_COORD -1 945.3914 -1100.3757 23.2999 PEDMOVE_RUN -1	
						TASK_AIM_GUN_AT_COORD -1 969.3931 -1098.3408 22.8770 120000
						TASK_STAY_IN_SAME_PLACE -1 TRUE
					CLOSE_SEQUENCE_TASK sw7_grove1guardseq
					PERFORM_SEQUENCE_TASK sw7_grove1 sw7_grove1guardseq
					CLEAR_SEQUENCE_TASK sw7_grove1guardseq
				ENDIF

						
				IF NOT IS_CHAR_DEAD sw7_grove2
					CLEAR_CHAR_TASKS sw7_grove2
					OPEN_SEQUENCE_TASK sw7_grove2guardseq
						TASK_GO_STRAIGHT_TO_COORD -1 944.9411 -1106.7729 23.2539 PEDMOVE_RUN -1	
						TASK_AIM_GUN_AT_COORD -1 970.9636 -1107.9619 22.8672 120000
						TASK_STAY_IN_SAME_PLACE -1 TRUE

					CLOSE_SEQUENCE_TASK sw7_grove2guardseq
					PERFORM_SEQUENCE_TASK sw7_grove2 sw7_grove2guardseq
					CLEAR_SEQUENCE_TASK sw7_grove2guardseq
				ENDIF


			ENDIF
		ENDIF

		IF sw7_pursuitunderway > 0
			IF IS_CHAR_DEAD sw7_kane
				REMOVE_BLIP sw7_kaneb
				sw7_missionprogress = 1352 // Added 07.10.04
                sw7_checkkane = 4
			ENDIF
		ENDIF

	ENDIF
ENDIF


IF sw7_checkkane = 4
    IF NOT IS_CAR_DEAD sw7_pursuitcar
	IF NOT IS_CHAR_DEAD sweet
		 IF IS_CHAR_SITTING_IN_CAR sweet sw7_pursuitcar
			LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_UNLOCKED

			IF IS_CHAR_SITTING_IN_CAR scplayer sw7_pursuitcar
				sw7_ptrfullfixflag = 1
				
				CLEAR_CHAR_TASKS sweet
				REMOVE_BLIP sw7_pursuitcarb
			    ADD_BLIP_FOR_COORD 2501.6335 -1674.1307 12.3689	sw7_passedB
				SET_POLICE_IGNORE_PLAYER player1 OFF
			    SET_CAMERA_BEHIND_PLAYER
				sw7_checkkane = 8
				sw7_blipswap = 0
				sw7_convo_counter = 1
			ENDIF
		ENDIF
	ENDIF
	ENDIF
ENDIF


IF sw7_checkkane = 8
	
	IF sw7_pursuitunderway = 0
		IF sw7_convo_counter  > 0
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 1
						sw7_counter = 69
						sw7_convo_counter =  2
						BREAK
			   		CASE 2 
						sw7_counter = 0
					  	PRINT_NOW TWAR646 6000 1
						sw7_convo_counter = 0 // finish convo
						BREAK
				ENDSWITCH
			ENDIF
		ENDIF
	ENDIF


	IF sw7_pursuitunderway > 0
		//WRITE_DEBUG test
	IF sw7_convo_counter  > 0
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 1
						sw7_counter = 64
						sw7_convo_counter =  2
						BREAK
					CASE 2
						sw7_counter = 65
						sw7_convo_counter =  3
						BREAK
					CASE 3
						sw7_counter = 66
						sw7_convo_counter =  4
						BREAK

			   		CASE 4 
						sw7_counter = 0
						CLEAR_PRINTS
					  	PRINT_NOW TWAR646 6000 1
						sw7_convo_counter = 0 // finish convo
						BREAK

				ENDSWITCH
			ENDIF
		ENDIF
	ENDIF


	IF NOT IS_CAR_DEAD sw7_pursuitcar

			IF sw7_blipswap = 0
		   		IF NOT IS_CHAR_IN_CAR scplayer sw7_pursuitcar
					
					sw7_convo_counter = 0
					ADD_BLIP_FOR_CAR sw7_pursuitcar sw7_pursuitcarB
					SET_BLIP_AS_FRIENDLY sw7_pursuitcarB TRUE
					REMOVE_BLIP sw7_passedB
					CLEAR_PRINTS
					PRINT_NOW TWAR645 6000 1
					sw7_blipswap = 1												  
				ENDIF
		
			ENDIF

			IF sw7_blipswap = 1
				IF IS_CHAR_IN_CAR scplayer sw7_pursuitcar
					REMOVE_BLIP sw7_pursuitcarB							
					ADD_BLIP_FOR_COORD 2501.6335 -1674.1307 12.3689 sw7_passedB	
					SET_POLICE_IGNORE_PLAYER player1 OFF
					CLEAR_PRINTS
					PRINT_NOW TWAR647 8000 1		
					sw7_blipswap = 0
				ENDIF
			ENDIF


		  	GET_AREA_VISIBLE sw7_visible
		   	IF sw7_visible = 0
		 		IF NOT IS_MINIGAME_IN_PROGRESS	
					SET_PLAYER_CONTROL player1 ON
			 	ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD sweet
				 IF IS_CHAR_SITTING_IN_CAR sweet sw7_pursuitcar
					IF IS_CHAR_IN_CAR scplayer sw7_pursuitcar
					IF LOCATE_CHAR_IN_CAR_3D scplayer  2501.6335 -1674.1307 12.3689 4.0 4.0 4.0 TRUE
					   
						SET_PLAYER_CONTROL player1 OFF
     				  //	SET_CAR_TEMP_ACTION sw7_pursuitcar TEMPACT_HANDBRAKESTRAIGHT 20000                     

						
						IF IS_CAR_STOPPED sw7_pursuitcar
						REMOVE_BLIP sw7_passedB
						CLEAR_PRINTS
					    sw7_checkkane = 74
				  		ENDIF
					ENDIF
					ENDIF
				ENDIF
			ENDIF
	ENDIF
ENDIF


IF sw7_checkkane = 74
		SET_PLAYER_CONTROL player1 OFF
		SET_EVERYONE_IGNORE_PLAYER player1 TRUE

		CLEAR_AREA 2501.6335 -1674.1307 12.3689 5.0 TRUE
		SWITCH_WIDESCREEN ON
		SET_FIXED_CAMERA_POSITION 2499.5815 -1682.3954 15.2226 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2499.9690 -1681.4847 15.0790 JUMP_CUT

		IF NOT IS_CHAR_DEAD sweet
		  	OPEN_SEQUENCE_TASK sw7_sweetfinalseq
	  		TASK_LEAVE_ANY_CAR -1
		
			TASK_GO_STRAIGHT_TO_COORD -1 2516.6335 -1673.8468 13.9671 PEDMOVE_WALK 20000
			TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
			CLOSE_SEQUENCE_TASK sw7_sweetfinalseq
			PERFORM_SEQUENCE_TASK sweet sw7_sweetfinalseq
			CLEAR_SEQUENCE_TASK sw7_sweetfinalseq

		ENDIF
		timerb =  0 
		sw7_convo_counter = 1
		sw7_checkkane = 77

		sw7_parkedcar = 1  // new segment 07.10.04



ENDIF

//IF sw7_checkkane = 77 // taken out 07.10.04
IF sw7_parkedcar = 1 //using this variable as a stopgap

	sw7_checkkane = 77


	IF sw7_convo_counter  > 0
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 1
						sw7_counter = 70
						sw7_convo_counter =  2
						BREAK
					CASE 2
						sw7_counter = 71
						sw7_convo_counter = 3
						BREAK
					CASE 3
						sw7_counter = 72
						sw7_convo_counter = 4
						BREAK
					CASE 4 
						sw7_counter = 0
						sw7_checkkane = 80
						sw7_parkedcar = 2	// added 07.10.04
						sw7_convo_counter = 0 // finish convo
						BREAK
				ENDSWITCH
			ENDIF
		ENDIF
ENDIF

IF sw7_parkedcar = 2
	SET_CAMERA_BEHIND_PLAYER
	DELETE_CHAR sweet

	SWITCH_WIDESCREEN OFF 
	RESTORE_CAMERA_JUMPCUT	

  	CLEAR_PRINTS					   
	SET_PLAYER_CONTROL player1 ON
	SET_EVERYONE_IGNORE_PLAYER player1 FALSE

	GOTO mission_sweet7_passed
ENDIF	


IF sw7_missionprogress = 5												 
		SET_PLAYER_CONTROL player1 OFF

		WAIT 0

		DO_FADE 1500 FADE_OUT
	   	
		WHILE GET_FADING_STATUS
	   		WAIT 0
	   	ENDWHILE
			
		SET_PLAYER_CONTROL player1 OFF 
		IF NOT IS_CHAR_DEAD sw7_grove1 
		   	 SET_CHAR_SUFFERS_CRITICAL_HITS sw7_grove1 FALSE
		ENDIF

		IF NOT IS_CHAR_DEAD sw7_grove2 
			SET_CHAR_SUFFERS_CRITICAL_HITS sw7_grove2 FALSE
		ENDIF

		IF NOT IS_CHAR_DEAD sweet
		   CLEAR_CHAR_TASKS sweet
		ENDIF

		SWITCH_WIDESCREEN ON

		SET_CHAR_COORDINATES scplayer 816.8154 -1075.3832 23.3516
		SET_CHAR_HEADING scplayer 277.0
		SET_FIXED_CAMERA_POSITION 822.0925 -1077.8783 23.6895 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 821.1437 -1077.5657 23.7335 JUMP_CUT 

		LOAD_SCENE 803.9182 -1078.6259 23.3515
		WAIT 1000

		DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

		
		sw7_missionprogress = 6
		sw7_convo_counter = 1
		TIMERA = 0
		
		timerb = 0
			
ENDIF //sw7_missionprogress = 5 condition check


IF sw7_missionprogress = 6

				IF sw7_convo_counter  > 0
					IF sw7_audio_underway = 0
		
						SWITCH sw7_convo_counter
							CASE 1
								//sw7_counter = 32
								sw7_convo_counter = 2
								BREAK
							CASE 2	
							   //	sw7_counter = 33
								IF NOT IS_CHAR_DEAD sweet
								IF NOT IS_CHAR_DEAD sw7_kane
								CLEAR_CHAR_TASKS sweet
		    					TASK_TURN_CHAR_TO_FACE_CHAR sweet sw7_kane
							   	ENDIF
								ENDIF

								sw7_convo_counter = 3
								BREAK
							CASE 3
								sw7_counter = 34
								sw7_convo_counter = 4

								BREAK
							CASE 4
								sw7_counter = 0
								PRINT_HELP TWAR661  
								sw7_forcehelp = 1
								sw7_convo_counter = 0 // finish convo
								BREAK


						ENDSWITCH
					ENDIF
				ENDIF

	IF timerb > 10000
		IF sw7_forcehelp = 1
			CLEAR_HELP
			PRINT_HELP TWAR662  
			sw7_forcehelp = 2
			timerb = 0
		ENDIF
	ENDIF

	IF sw7_forcehelp = 2
	IF timerb > 8000
		CLEAR_HELP
		SET_FIXED_CAMERA_POSITION 891.9803 -1068.3428 23.6669 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 891.3828 -1069.1415 23.7373 JUMP_CUT
		 sw7_ctskip_needed = 1
		IF sw7_kanearrival = 0
		IF NOT IS_CAR_DEAD sw7_kanehearse
		IF NOT IS_CHAR_DEAD sw7_kanechauffeur
			TASK_CAR_DRIVE_TO_COORD sw7_kanechauffeur sw7_kanehearse 875.3232 -1073.5021 23.3364 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
			
			IF sw7_audio_underway = 0
				sw7_counter	= 40
			ENDIF

			sw7_kanearrival = 1
		ENDIF
		ENDIF
		ENDIF
	
		IF NOT IS_CAR_DEAD sw7_kanehearse
		IF NOT IS_CHAR_DEAD sw7_kanechauffeur

			IF LOCATE_CAR_3D sw7_kanehearse 875.3232 -1071.9413 23.3364 3.0 3.0 3.0 FALSE
			IF IS_CAR_STOPPED sw7_kanehearse
				
				sw7_missionprogress = 7

				IF sw7_audio_underway = 0
					sw7_cut_counter	 = 0
				ENDIF

				SET_FIXED_CAMERA_POSITION 871.9701 -1069.5032 25.6673 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 872.7517 -1070.0419 25.3530 JUMP_CUT 
				
				LOCK_CAR_DOORS sw7_kanehearse CARLOCK_UNLOCKED
				SET_CAR_PROOFS sw7_kanehearse FALSE FALSE FALSE FALSE FALSE


				IF NOT IS_CHAR_DEAD sw7_kane
	    			OPEN_SEQUENCE_TASK sw7_kane1seq
					TASK_LEAVE_ANY_CAR -1
					TASK_GO_STRAIGHT_TO_COORD -1 869.1639 -1071.6193 25.2208 PEDMOVE_WALK 10000 
		   			TASK_GO_STRAIGHT_TO_COORD -1 892.7105 -1077.9293 23.2684 PEDMOVE_WALK 10000
	  		    	CLOSE_SEQUENCE_TASK sw7_kane1seq

	    			PERFORM_SEQUENCE_TASK sw7_kane sw7_kane1seq
	    			CLEAR_SEQUENCE_TASK sw7_kane1seq		 
				ENDIF

			   TIMERA = 0
			  
			ENDIF

		ENDIF
 	ENDIF
	ENDIF


	ENDIF
	ENDIF


ENDIF //sw7_missionprogress = 6 conditioncheck



IF sw7_missionprogress = 7
  
	IF TIMERA > 2000


		IF NOT IS_CHAR_DEAD sw7_kanechauffeur
    		OPEN_SEQUENCE_TASK sw7_chauffeur1seq
			TASK_LEAVE_ANY_CAR -1
			TASK_GO_STRAIGHT_TO_COORD -1 873.9978 -1077.9880 23.3203 PEDMOVE_WALK 10000

			TASK_GO_STRAIGHT_TO_COORD -1 833.1347 -1095.1868 22.8281 PEDMOVE_WALK 10000
  	    	CLOSE_SEQUENCE_TASK sw7_chauffeur1seq

    		PERFORM_SEQUENCE_TASK sw7_kanechauffeur sw7_chauffeur1seq
    		CLEAR_SEQUENCE_TASK sw7_chauffeur1seq		 
		ENDIF

		CLEAR_PRINTS
		
	 	sw7_missionprogress = 8
		sw7_convo_counter = 1
	
	ENDIF


ENDIF // sw7_missionprogress = 7 condition check



IF sw7_missionprogress = 8

	IF sw7_convo_counter  > 0
		IF sw7_audio_underway = 0

			SWITCH sw7_convo_counter
				CASE 1
					sw7_counter = 41
					sw7_convo_counter = 2
					BREAK
				CASE 2	
					sw7_counter = 42
					sw7_convo_counter = 3
					BREAK
				CASE 3
					sw7_counter = 0
					sw7_convo_counter = 0 // finish convo
					BREAK


			ENDSWITCH
		   //	sw7_audio_underway = 1
		ENDIF
	ENDIF


	IF TIMERA > 8000
	 sw7_missionprogress = 10
	ENDIF


ENDIF // sw7_missionprogress = 7 condition check


IF sw7_missionprogress = 10
	
		sw7_ctskip_needed = 0
		sw7_convo_counter = 0

   	    sw7_checkgangblips = 99
		
  	  	SET_CHAR_COORDINATES scplayer 817.3972 -1076.1641 23.1065
		IF NOT IS_CHAR_DEAD sw7_banger1
			SET_CHAR_COORDINATES sw7_banger1 820.5947 -1074.5170 23.1755
			SET_CHAR_HEADING sw7_banger1 176.0 
	 	ENDIF

		IF NOT IS_CHAR_DEAD sw7_banger2	
			SET_CHAR_COORDINATES sw7_banger2 818.3846 -1079.0015 22.9290			
			SET_CHAR_HEADING sw7_banger2 291.0 
		ENDIF

	   IF NOT IS_CHAR_DEAD sw7_kane
			CLEAR_CHAR_TASKS_IMMEDIATELY sw7_kane
			IF NOT IS_CHAR_IN_ANY_CAR sw7_kane
			SET_CHAR_COORDINATES sw7_kane 890.4531 -1063.3676 23.7342//886.5172 -1067.5045 23.5271
			ENDIF

			IF IS_CHAR_IN_ANY_CAR sw7_kane
			 WARP_CHAR_FROM_CAR_TO_COORD sw7_kane 890.4531 -1063.3676 23.7342//886.5172 -1067.5045 23.5271
			ENDIF
			
			TASK_ACHIEVE_HEADING sw7_kane 84.0 
		ENDIF

		SET_CHAR_HEADING scplayer 255.0
  	  	SET_CAMERA_BEHIND_PLAYER

		SWITCH_WIDESCREEN OFF 
		RESTORE_CAMERA_JUMPCUT	
		REMOVE_ANIMATION smoking
		REMOVE_ANIMATION graveyard

		IF NOT IS_CHAR_DEAD sw7_kanechauffeur
			CLEAR_CHAR_TASKS_IMMEDIATELY sw7_kanechauffeur

			SET_CHAR_COORDINATES sw7_kanechauffeur 844.5107 -1101.6604 22.8516
						
			//SET_CHAR_COORDINATES sw7_kanechauffeur 850.0652 -1101.5718 22.8516 	
			TASK_ACHIEVE_HEADING sw7_kanechauffeur 135.0 
			GIVE_WEAPON_TO_CHAR sw7_kanechauffeur WEAPONTYPE_PISTOL 2000
			ADD_ARMOUR_TO_CHAR sw7_kanechauffeur 50


		ENDIF

	   	IF NOT IS_CAR_DEAD sw7_kanehearse
	   		SET_CAR_COORDINATES sw7_kanehearse 875.3232 -1073.5021 23.3364
	   		SET_CAR_HEADING sw7_kanehearse 89.0	  
	   			LOCK_CAR_DOORS sw7_kanehearse CARLOCK_UNLOCKED
				SET_CAR_PROOFS sw7_kanehearse FALSE FALSE FALSE FALSE FALSE
	   	ENDIF		

		SET_PLAYER_CONTROL player1 ON
		SET_GROUP_FOLLOW_STATUS sw7_group TRUE

		IF NOT IS_CHAR_DEAD sw7_banger1
												 
			IF NOT IS_GROUP_MEMBER sw7_banger1 sw7_group
			 	SET_GROUP_MEMBER sw7_group sw7_banger1
				 SET_CHAR_NEVER_LEAVES_GROUP sw7_banger1 TRUE
			ENDIF
		ENDIF
		
		IF NOT IS_CHAR_DEAD sw7_banger2
			IF NOT IS_GROUP_MEMBER sw7_banger2 sw7_group 
			  SET_GROUP_MEMBER sw7_group sw7_banger2
			  SET_CHAR_NEVER_LEAVES_GROUP sw7_banger2 TRUE

			ENDIF

		ENDIF

		
		IF NOT IS_CHAR_DEAD sw7_kanechauffeur
			SET_CHAR_DECISION_MAKER sw7_kanechauffeur sw7_emptydm
			CLEAR_CHAR_TASKS sw7_kanechauffeur
		ENDIF

        TASK_STAY_IN_SAME_PLACE sw7_kanechauffeur TRUE
        sw7_missionprogress = 15
		
		IF NOT IS_CHAR_DEAD	sw7_kane
			ADD_BLIP_FOR_CHAR sw7_kane sw7_kaneB
			CHANGE_BLIP_COLOUR sw7_kaneB RED
		ENDIF

		IF NOT IS_CAR_DEAD sw7_sweethearse
			DELETE_CAR sw7_sweethearse
		ENDIF
		CLEAR_HELP
ENDIF //sw7_missionprogress = 10 condition check



IF sw7_missionprogress = 15

	SET_POLICE_IGNORE_PLAYER player1 ON

	IF NOT IS_CHAR_DEAD sw7_kanechauffeur
		IF NOT IS_CHAR_DEAD sweet
			   
			OPEN_SEQUENCE_TASK sw7_sweetseqA
			TASK_GO_STRAIGHT_TO_COORD -1 832.3179 -1081.6274 22.9332 PEDMOVE_RUN 10000
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 833.9351 -1101.9663 22.8281 PEDMOVE_RUN 100.0 1.0 sw7_kanechauffeur
			
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 sw7_kanechauffeur	
			CLOSE_SEQUENCE_TASK sw7_sweetseqA

			PERFORM_SEQUENCE_TASK sweet sw7_sweetseqA
			CLEAR_SEQUENCE_TASK sw7_sweetseqA
					
		ENDIF

		IF NOT IS_CHAR_DEAD sw7_grove1
				CLEAR_CHAR_TASKS sw7_grove1
			   
				OPEN_SEQUENCE_TASK sw7_grove1seqA
				
				TASK_GO_STRAIGHT_TO_COORD -1 828.6603 -1080.4081 23.0300 PEDMOVE_SPRINT 10000
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 832.5559 -1104.0667 22.8281 PEDMOVE_SPRINT 100.0 1.0 sw7_kanechauffeur
				
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_KILL_CHAR_ON_FOOT -1 sw7_kanechauffeur
				CLOSE_SEQUENCE_TASK sw7_grove1seqA

				PERFORM_SEQUENCE_TASK sw7_grove1 sw7_grove1seqA
				CLEAR_SEQUENCE_TASK sw7_grove1seqA
					
		ENDIF


		IF NOT IS_CHAR_DEAD sw7_grove2
				CLEAR_CHAR_TASKS sw7_grove2
			   
				OPEN_SEQUENCE_TASK sw7_grove2seqA
				TASK_GO_STRAIGHT_TO_COORD -1 832.2366 -1077.2246 23.1896 PEDMOVE_SPRINT 10000
		  		TASK_GO_TO_COORD_WHILE_SHOOTING -1 835.7187 -1097.3250 22.8784 PEDMOVE_SPRINT 100.0 1.0 sw7_kanechauffeur
				
				TASK_STAY_IN_SAME_PLACE -1 TRUE
				TASK_KILL_CHAR_ON_FOOT -1 sw7_kanechauffeur
				CLOSE_SEQUENCE_TASK sw7_grove2seqA

				PERFORM_SEQUENCE_TASK sw7_grove2 sw7_grove2seqA
				CLEAR_SEQUENCE_TASK sw7_grove2seqA
					
		ENDIF


		GOSUB sw7_initial
		
		IF NOT IS_CHAR_DEAD sw7_kanechauffeur
			
		   IF NOT IS_CHAR_DEAD sweet
			    OPEN_SEQUENCE_TASK sw7_chauffattackseq
				TASK_KINDA_STAY_IN_SAME_PLACE -1 TRUE
	    		TASK_KILL_CHAR_ON_FOOT -1 sweet
	    		CLOSE_SEQUENCE_TASK sw7_chauffattackseq

				PERFORM_SEQUENCE_TASK sw7_kanechauffeur	 sw7_chauffattackseq
				CLEAR_SEQUENCE_TASK sw7_chauffattackseq
		   ENDIF
		ENDIF
	ENDIF
	
	IF sw7_chauffeurkilltask  = 1
	 	IF NOT IS_CHAR_DEAD sw7_greetinface
			DELETE_CHAR sw7_greetinface
	 	ENDIF

	 	IF NOT IS_CHAR_DEAD sw7_preach
	 	   DELETE_CHAR sw7_preach
	 	ENDIF
	ENDIF

	TIMERB = 0
	sw7_begingangfight = 1
	sw7_convo_counter = 1
	GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random

	CLEAR_MISSION_AUDIO 3
		
ENDIF  //sw7_missionprogress = 15 condition check



IF sw7_begingangfight = 1


	IF sw7_convo_counter  > 0
		IF sw7_audio_underway = 0

			SWITCH sw7_convo_counter
				CASE 1
					IF sw7_random = 0
						sw7_counter = 43
					ENDIF
				 	IF sw7_random = 1
						sw7_counter = 44
					ENDIF
					IF sw7_random = 1
						sw7_counter = 45
					ENDIF

					sw7_convo_counter = 2
					BREAK
				CASE 2
					PRINT_NOW TWAR636 8000 1
					sw7_counter = 0
					sw7_convo_counter = 0 // finish convo
					BREAK


			ENDSWITCH
		   //	sw7_audio_underway = 1
		ENDIF
	ENDIF

	
	IF TIMERB > 6000
	   IF sw7_shotanim = 0
	     IF NOT IS_CHAR_DEAD sw7_kanechauffeur
			CLEAR_CHAR_TASKS sw7_kanechauffeur
			TASK_PLAY_ANIM sw7_kanechauffeur KO_shot_face PED 8.0 FALSE FALSE FALSE TRUE -1
			SET_CHAR_HEALTH sw7_kanechauffeur 0

			sw7_shotanim = 1
			CLEAR_PRINTS
		 ENDIF
			
	   ENDIF
	ENDIF
	
	
	
	IF TIMERB > 7000	

	   IF NOT IS_CHAR_DEAD sw7_kanechauffeur
			CLEAR_CHAR_TASKS sw7_kanechauffeur
			TASK_DIE sw7_kanechauffeur
	   ENDIF

	   IF sw7_checkkane = 0
	   IF NOT IS_CHAR_DEAD sweet
			CLEAR_CHAR_TASKS sweet
			FLUSH_ROUTE

			EXTEND_ROUTE 872.7859 -1100.7915 22.8437
		  			
			sweet_onroute = 1
			TASK_FOLLOW_POINT_ROUTE sweet PEDMOVE_RUN FOLLOW_ROUTE_ONCE
	  	ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD sw7_grove1
			CLEAR_CHAR_TASKS sw7_grove1
			FLUSH_ROUTE
			EXTEND_ROUTE 882.4450 -1102.0406 22.8281 
					  		
			sw7_grove1onroute = 1	
			TASK_FOLLOW_POINT_ROUTE sw7_grove1 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE
	  	ENDIF

		 IF NOT IS_CHAR_DEAD sw7_grove2
			CLEAR_CHAR_TASKS sw7_grove2
			FLUSH_ROUTE
			EXTEND_ROUTE 888.5107 -1099.9352 21.8918 
		  			
			sw7_grove2onroute = 1
			TASK_FOLLOW_POINT_ROUTE sw7_grove2 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE
	  	ENDIF


   		sw7_begingangfight = 2
		CLEAR_PRINTS
		
	ENDIF

ENDIF


//was 1.5 1.5 3.0 for all guys

//A class fix, Sweet got stuck
IF sw7_checkkane = 0
IF sweet_onroute = 1
    IF NOT IS_CHAR_DEAD sweet
			IF LOCATE_CHAR_ANY_MEANS_3D sweet 872.7859 -1100.7915 22.8437 3.0 3.0 3.0 FALSE 
					CLEAR_CHAR_TASKS sweet
					TASK_GO_STRAIGHT_TO_COORD sweet 875.9534 -1079.5154 23.3203 PEDMOVE_RUN 10000
					 sweet_onroute = 2
			ENDIF
	ENDIF
ENDIF
ENDIF
								  

IF sw7_grove1onroute = 1
   IF NOT IS_CHAR_DEAD sw7_grove1
			IF LOCATE_CHAR_ANY_MEANS_3D sw7_grove1 882.4450 -1102.0406 22.8281 5.0 5.0 3.0 FALSE 
					CLEAR_CHAR_TASKS sw7_grove1
					TASK_GO_STRAIGHT_TO_COORD sw7_grove1 881.3811 -1088.8960 23.1974  PEDMOVE_RUN 10000
					 sw7_grove1onroute = 2
			ENDIF
	ENDIF

ENDIF


IF sw7_grove2onroute = 1
	IF NOT IS_CHAR_DEAD sw7_grove2
		IF LOCATE_CHAR_ANY_MEANS_3D	 sw7_grove2	888.5107 -1099.9352 21.8918 5.0 5.0 3.0 FALSE
				CLEAR_CHAR_TASKS sw7_grove2 
				TASK_GO_STRAIGHT_TO_COORD sw7_grove2 893.6727 -1077.2947 23.2759 PEDMOVE_SPRINT 10000
				sw7_grove2onroute = 2
		ENDIF
	ENDIF
ENDIF


IF sw7_begingangfight  > 0		// was  = 2
	  
	  IF sw7_checkkane = 0
	  IF sw7_sweetfight = 0
	  IF NOT IS_CHAR_DEAD sweet
			//SET_CHAR_DECISION_MAKER sweet sw7_toughdm
			IF LOCATE_CHAR_ANY_MEANS_3D sweet 875.9534 -1079.5154 23.3203 2.0 2.0 3.0 FALSE 	
				CLEAR_CHAR_TASKS sweet
		  		IF NOT IS_CHAR_DEAD sw7_protector1
					OPEN_SEQUENCE_TASK sw7_sbeginseq
		   
					 TASK_STAY_IN_SAME_PLACE -1 FALSE


		  			TASK_KILL_CHAR_ON_FOOT -1 sw7_protector1
				   
		  			CLOSE_SEQUENCE_TASK sw7_sbeginseq
					PERFORM_SEQUENCE_TASK sweet sw7_sbeginseq
					CLEAR_SEQUENCE_TASK sw7_sbeginseq


		  		ENDIF
		  		sw7_sweetfight = 1
		
		  	ENDIF
	  ENDIF
	  ENDIF
	  ENDIF	  

	  IF sw7_grove1fight = 0
	  IF NOT IS_CHAR_DEAD sw7_grove1
			//SET_CHAR_DECISION_MAKER sw7_grove1 sw7_toughdm
			IF LOCATE_CHAR_ANY_MEANS_3D sw7_grove1 881.3811 -1088.8960 23.1974  5.0 5.0 3.0 FALSE 	
				CLEAR_CHAR_TASKS sw7_grove1
		  		IF NOT IS_CHAR_DEAD sw7_protector2
					OPEN_SEQUENCE_TASK sw7_g1beginseq
				    TASK_STAY_IN_SAME_PLACE -1 FALSE
					//TASK_CLIMB sw7_grove1 TRUE
					//TASK_JUMP sw7_grove1 TRUE
		  			TASK_KILL_CHAR_ON_FOOT -1 sw7_protector2
					//SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_grove1 2.0
					CLOSE_SEQUENCE_TASK sw7_g1beginseq
					PERFORM_SEQUENCE_TASK sw7_grove1 sw7_g1beginseq
					CLEAR_SEQUENCE_TASK sw7_g1beginseq

		  		ENDIF
		  		sw7_grove1fight = 1		
		  	ENDIF
	  ENDIF
	  ENDIF

	  IF sw7_grove2fight = 0
	  IF NOT IS_CHAR_DEAD sw7_grove2
			
			IF LOCATE_CHAR_ANY_MEANS_3D sw7_grove2  893.6727 -1077.2947 23.2759 5.0 5.0 3.0 FALSE 
				
				CLEAR_CHAR_TASKS sw7_grove2
		  		IF NOT IS_CHAR_DEAD sw7_protector3
					
					OPEN_SEQUENCE_TASK sw7_g2beginseq
					TASK_STAY_IN_SAME_PLACE -1 FALSE
		  			TASK_KILL_CHAR_ON_FOOT -1 sw7_protector3
				    CLOSE_SEQUENCE_TASK sw7_g2beginseq
					PERFORM_SEQUENCE_TASK sw7_grove2 sw7_g2beginseq
					CLEAR_SEQUENCE_TASK sw7_g2beginseq
					
		  		ENDIF
		  		sw7_grove2fight = 1
		
		  	ENDIF
	  ENDIF
	  ENDIF
ENDIF


IF sw7_grove1fight = 1
	IF IS_CHAR_DEAD sw7_protector2
		IF NOT IS_CHAR_DEAD sw7_protector4
			IF NOT IS_CHAR_DEAD sw7_grove1
				CLEAR_CHAR_TASKS sw7_grove1
				TASK_KILL_CHAR_ON_FOOT sw7_grove1 sw7_protector4
			ENDIF
		ENDIF
		sw7_grove1fight = 2
	ENDIF
ENDIF

IF sw7_grove2fight = 1
	IF IS_CHAR_DEAD sw7_protector3
		IF NOT IS_CHAR_DEAD sw7_protector5
			IF NOT IS_CHAR_DEAD sw7_grove2
				CLEAR_CHAR_TASKS sw7_grove2
				TASK_KILL_CHAR_ON_FOOT sw7_grove2 sw7_protector5
			ENDIF
		ENDIF
		
	ENDIF
	sw7_grove2fight = 2


ENDIF

IF sw7_checkkane = 0
IF sw7_pursuitunderway = 0
IF sw7_sweetfight = 1
	IF IS_CHAR_DEAD sw7_protector1
		IF NOT IS_CHAR_DEAD sw7_protector6
			IF NOT IS_CHAR_DEAD sweet
				CLEAR_CHAR_TASKS sweet
				TASK_KILL_CHAR_ON_FOOT sweet sw7_protector6
			ENDIF
		ENDIF
		sw7_sweetfight = 2
	ENDIF
ENDIF
ENDIF
ENDIF


IF sw7_grove1fight = 2
	IF IS_CHAR_DEAD sw7_protector4
		IF NOT IS_CHAR_DEAD sw7_grove1
			
			IF NOT IS_CHAR_DEAD sw7_kane
		  		CLEAR_CHAR_TASKS sw7_grove1
				TASK_KILL_CHAR_ON_FOOT sw7_grove1 sw7_kane
			ENDIF
							
			 sw7_grove1fight = 3
			
		ENDIF
	ENDIF
ENDIF

IF sw7_grove2fight = 2
	IF IS_CHAR_DEAD sw7_protector5
		IF NOT IS_CHAR_DEAD sw7_grove2
			
			
		
			IF NOT IS_CHAR_DEAD sw7_kane
		  		CLEAR_CHAR_TASKS sw7_grove2
				TASK_KILL_CHAR_ON_FOOT sw7_grove2 sw7_kane
			ENDIF
				
			 sw7_grove2fight = 3
			
		ENDIF
	ENDIF
ENDIF

IF sw7_checkkane = 0
IF sw7_pursuitunderway = 0
IF sw7_sweetfight = 2
	IF IS_CHAR_DEAD sw7_protector6
		IF NOT IS_CHAR_DEAD sweet
			
			
		   IF NOT IS_CHAR_DEAD sw7_kane
		  		CLEAR_CHAR_TASKS sweet
				TASK_KILL_CHAR_ON_FOOT sweet sw7_kane
			ENDIF
			//WRITE_DEBUG Number6_is_dead
							
			 sw7_sweetfight = 3
			
		ENDIF
	ENDIF
ENDIF
ENDIF
ENDIF


IF sw7_kanemove1 = 1

	IF NOT IS_CHAR_DEAD sw7_kane
		IF LOCATE_CHAR_ANY_MEANS_3D sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] 5.0 5.0 5.0 FALSE
	   			SET_CHAR_ALLOWED_TO_DUCK sw7_kane TRUE
				CLEAR_CHAR_TASKS sw7_kane
				TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING sw7_kane scplayer DUCK_RANDOMLY 3000 50
  			 sw7_kanemove1 = 2
		ENDIF

		IF sw7_goonmove1 = 1
	  	IF LOCATE_CHAR_ANY_MEANS_3D sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] 20.0 20.0 5.0 FALSE
		  GOSUB cycle_goons
		  sw7_goonmove1 = 2
  	   	ENDIF
		ENDIF


	ENDIF
ENDIF


IF sw7_kanemove1 = 3

	IF NOT IS_CHAR_DEAD sw7_kane
		IF LOCATE_CHAR_ANY_MEANS_3D sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] 5.0 5.0 5.0 FALSE
	   			
	   			CLEAR_CHAR_TASKS sw7_kane
	   			TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING sw7_kane scplayer DUCK_RANDOMLY 3000 50
				IF NOT IS_CHAR_DEAD sw7_protector5
					CLEAR_CHAR_TASKS sw7_protector5
					TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING sw7_protector5 scplayer DUCK_RANDOMLY 3000 50
				ENDIF
				
  			 sw7_kanemove1 = 4
		ENDIF

		IF sw7_goonmove1 = 2
	  	IF LOCATE_CHAR_ANY_MEANS_3D sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] 20.0 20.0 5.0 FALSE
		  GOSUB cycle_goons
		  sw7_goonmove1 = 3

  	   	ENDIF
		ENDIF
	ENDIF
ENDIF



IF sw7_kanemove1 = 5

	IF NOT IS_CHAR_DEAD sw7_kane
		IF LOCATE_CHAR_ANY_MEANS_3D sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] 5.0 5.0 5.0 FALSE
	   			
	   			CLEAR_CHAR_TASKS sw7_kane
	   																				//was 2 above
	   			TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING sw7_kane scplayer DUCK_RANDOMLY 3000 50
				sw7_kanemove1 = 6
		ENDIF

	
	ENDIF
ENDIF


IF sw7_kanemove1 = 7

	IF NOT IS_CHAR_DEAD sw7_kane
		IF LOCATE_CHAR_ANY_MEANS_3D sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] 5.0 5.0 5.0 FALSE
	   			CLEAR_CHAR_TASKS sw7_kane
	   			TASK_STAY_IN_SAME_PLACE sw7_kane FALSE
				//WRITE_DEBUG_WITH_INT kanemove sw7_fleeindex

	   			TASK_KILL_CHAR_ON_FOOT sw7_kane scplayer  
				//GOSUB cycle_kane
				//put in pop-up guys here!
				sw7_kanemove1 = 8

				//begin car action!!!!
				//sw7_missionprogress = 50

		ENDIF

	
	ENDIF
ENDIF





IF sw7_missionprogress = 20

		
	  

		IF NOT sw7_missionprogress = 25
		IF NOT IS_CHAR_DEAD sw7_kane
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sw7_kane 20.0 20.0 FALSE
		   		sw7_missionprogress = 25
		   		sw7_fleeindex++
				//WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
				GOSUB cycle_kane
				sw7_kanemove1 = 1
				sw7_goonmove1 = 1
				TIMERA = 0
			ENDIF	
		ENDIF
		ENDIF



		IF NOT sw7_missionprogress = 25
		IF NOT IS_CHAR_DEAD sw7_kane
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_kane scplayer
				sw7_missionprogress = 25
		   		sw7_fleeindex++
				//WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
				//SET_CHAR_DECISION_MAKER sw7_kane sw7_emptydm

				//TASK_GO_STRAIGHT_TO_COORD sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] PEDMOVE_RUN -
		   		GOSUB cycle_kane
				sw7_kanemove1 = 1
				sw7_goonmove1 = 1

			ENDIF	
		ENDIF
		ENDIF

			
		
		IF NOT sw7_missionprogress = 25
			IF IS_CHAR_DEAD sw7_protector1
				sw7_protector1dead = 1
				sw7_missionprogress = 25
		   		sw7_fleeindex++
			 	GOSUB cycle_kane
				sw7_kanemove1 = 1
				sw7_goonmove1 = 1

			ENDIF
		ENDIF

		IF NOT sw7_missionprogress = 25
			IF IS_CHAR_DEAD sw7_protector2
				sw7_protector2dead = 1
				sw7_missionprogress = 25
		   		sw7_fleeindex++
				

		   		GOSUB cycle_kane
				sw7_kanemove1 = 1
				sw7_goonmove1 = 1

				
			ENDIF
		ENDIF

		IF NOT sw7_missionprogress = 25
			IF IS_CHAR_DEAD sw7_protector3
				sw7_protector3dead = 1
				sw7_missionprogress = 25
		   		sw7_fleeindex++
		   		
		   		GOSUB cycle_kane
				sw7_kanemove1 = 1
				sw7_goonmove1 = 1

			ENDIF
		ENDIF

		IF NOT sw7_missionprogress = 25
			IF IS_CHAR_DEAD sw7_protector4
				//sw7_protector3dead = 1
				sw7_missionprogress = 25
		   		sw7_fleeindex++
		   		
		   		GOSUB cycle_kane
				sw7_kanemove1 = 1
				sw7_goonmove1 = 1

			ENDIF
		ENDIF

		



ENDIF // sw7_missionprogress condition check









IF sw7_missionprogress = 25
	IF sw7_kanemove1 = 2

	 	IF NOT sw7_missionprogress = 30
		IF NOT IS_CHAR_DEAD sw7_kane
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sw7_kane 20.0 20.0 FALSE
		   		sw7_missionprogress = 30
		   		sw7_fleeindex++
				//WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
				GOSUB cycle_kane
				sw7_kanemove1 = 3
				sw7_goonmove1 = 2
			ENDIF	
		ENDIF
		ENDIF



		IF NOT sw7_missionprogress = 30
		IF NOT IS_CHAR_DEAD sw7_kane
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_kane scplayer
		   		sw7_missionprogress = 30
		   		sw7_fleeindex++
			   //	WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
				//SET_CHAR_DECISION_MAKER sw7_kane sw7_emptydm

				//TASK_GO_STRAIGHT_TO_COORD sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] PEDMOVE_RUN -2
		   		GOSUB cycle_kane
				sw7_kanemove1 = 3
				sw7_goonmove1 = 2
			ENDIF	
		ENDIF
		ENDIF

			
		/*
		IF NOT sw7_missionprogress = 30
			IF IS_CHAR_DEAD sw7_protector1
				sw7_protector1dead = 1
				sw7_missionprogress = 30
		   		sw7_fleeindex++
		   		GOSUB cycle_kanegoons
				sw7_kanemove1 = 1
			ENDIF
		ENDIF

		IF NOT sw7_missionprogress = 30
			IF IS_CHAR_DEAD sw7_protector2
				sw7_protector2dead = 1
				sw7_missionprogress = 30
		   		sw7_fleeindex++
		   		GOSUB cycle_kanegoons
				sw7_kanemove1 = 1
			ENDIF
		ENDIF

		IF NOT sw7_missionprogress = 30
			IF IS_CHAR_DEAD sw7_protector3
				sw7_protector3dead = 1
				sw7_missionprogress = 30
		   		sw7_fleeindex++
		   		GOSUB cycle_kanegoons
				sw7_kanemove1 = 1
			ENDIF
		ENDIF

		 */

	ENDIF // sw7_kanemove = 2



ENDIF // sw7_missionprogress = 25 condition check


IF sw7_missionprogress = 30

	IF sw7_kanemove1 = 4
	    IF NOT sw7_missionprogress = 35
		IF NOT IS_CHAR_DEAD sw7_kane
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sw7_kane 20.0 20.0 FALSE
		   		sw7_missionprogress = 35
		   		sw7_fleeindex++
			   //WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
			   //	SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_kane 150.0

				GOSUB cycle_kane
				sw7_kanemove1 = 5
				
			ENDIF	
		ENDIF
		ENDIF



		IF NOT sw7_missionprogress = 35
		IF NOT IS_CHAR_DEAD sw7_kane
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_kane scplayer
		   		sw7_missionprogress = 35
		   		sw7_fleeindex++
				//WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
				//SET_CHAR_DECISION_MAKER sw7_kane sw7_emptydm

				//TASK_GO_STRAIGHT_TO_COORD sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] PEDMOVE_RUN -2
		   		//SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_kane 150.0

		   		GOSUB cycle_kane
				sw7_kanemove1 = 5
				
			ENDIF	
		ENDIF
		ENDIF
	ENDIF
	


ENDIF


IF sw7_missionprogress = 35

	IF sw7_kanemove1 = 6
	    IF NOT sw7_missionprogress = 40
		IF NOT IS_CHAR_DEAD sw7_kane
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sw7_kane 10.0 10.0 FALSE
		   		sw7_missionprogress = 40
		   		sw7_fleeindex++
			   //	WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
				//SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_kane 150.0

				GOSUB cycle_kane
				sw7_kanemove1 = 7
				sw7_missionprogress = 50

				
			ENDIF	
		ENDIF
		ENDIF



		IF NOT sw7_missionprogress = 40
		IF NOT IS_CHAR_DEAD sw7_kane
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR sw7_kane scplayer
		   		sw7_missionprogress = 40
		   		sw7_fleeindex++
			   //	WRITE_DEBUG_WITH_INT fleeindex sw7_fleeindex
				//SET_CHAR_DECISION_MAKER sw7_kane sw7_emptydm

				//TASK_GO_STRAIGHT_TO_COORD sw7_kane sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] PEDMOVE_RUN -2
		   		//SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_kane 150.0

		   		GOSUB cycle_kane
				sw7_kanemove1 = 7
				sw7_missionprogress = 50
				
			ENDIF	
		ENDIF
		ENDIF
	ENDIF



	
ENDIF






IF sw7_missionprogress = 50

	CREATE_CAR greenwoo 960.0995 -1032.0989 29.0858 sw7_escapehearse
	SET_CAR_HEADING sw7_escapehearse 180.4729
	CHANGE_CAR_COLOUR sw7_escapehearse 0 2
	SET_CAN_BURST_CAR_TYRES sw7_escapehearse FALSE


	CREATE_CHAR_INSIDE_CAR sw7_escapehearse PEDTYPE_CIVMALE BALLAS2 sw7_escapechauffeur
	SET_CHAR_SUFFERS_CRITICAL_HITS sw7_escapechauffeur FALSE
	SET_CHAR_DECISION_MAKER sw7_escapechauffeur sw7_emptydm

	CREATE_CHAR_AS_PASSENGER sw7_escapehearse PEDTYPE_MISSION1 BALLAS2 1 sw7_reinforce1
	SET_CHAR_DECISION_MAKER sw7_reinforce1 sw7_emptydm
	GIVE_WEAPON_TO_CHAR sw7_reinforce1 WEAPONTYPE_SHOTGUN 2000
	SET_CHAR_SHOOT_RATE sw7_reinforce1 15
   //	SET_CHAR_ACCURACY sw7_protector1 70.0


	
	CREATE_CHAR_AS_PASSENGER sw7_escapehearse PEDTYPE_MISSION1 BALLAS1 2 sw7_reinforce2
	SET_CHAR_DECISION_MAKER sw7_reinforce2 sw7_emptydm
	GIVE_WEAPON_TO_CHAR sw7_reinforce2 WEAPONTYPE_MICRO_UZI 2000
	SET_CHAR_SHOOT_RATE sw7_reinforce2 15

	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
	


	LOCK_CAR_DOORS sw7_escapehearse CARLOCK_LOCKOUT_PLAYER_ONLY

	/*
	CREATE_CAR admiral 955.9408 -1086.9865 23.5250 sw7_pursuitcar
	SET_CAR_HEADING sw7_pursuitcar 177.9

	LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_LOCKED
	*/


	//SET_CAR_PROOFS sw7_escapehearse TRUE TRUE TRUE TRUE TRUE

	IF NOT IS_CHAR_DEAD sw7_escapechauffeur
	IF NOT IS_CAR_DEAD sw7_escapehearse
	TASK_CAR_DRIVE_TO_COORD sw7_escapechauffeur sw7_escapehearse 955.9623 -1100.1404 22.7516 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_AVOIDCARS
	sw7_missionprogress = 55
	
	ENDIF
	ENDIF



ENDIF

IF sw7_escapeaborted = 0
	IF sw7_missionprogress > 50
	AND sw7_missionprogress < 999
		IF IS_CHAR_DEAD sw7_escapechauffeur
			IF NOT IS_CHAR_DEAD sw7_kane
				CLEAR_CHAR_TASKS sw7_kane
				TASK_KILL_CHAR_ON_FOOT sw7_kane scplayer
			ENDIF
		
			sw7_escapeaborted = 1
		ENDIF

	ENDIF
ENDIF


/*
IF sw7_finalcut = 1
	// cut removed 
	
	
	SET_FIXED_CAMERA_POSITION 943.2144 -1116.2319 34.7449 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 942.3730 -1115.8092 34.4082 JUMP_CUT


   sw7_finalcut = 2
   
ENDIF
*/
	



IF sw7_missionprogress = 55

	IF NOT IS_CHAR_DEAD sw7_escapechauffeur
	IF NOT IS_CAR_DEAD sw7_escapehearse
	IF LOCATE_CAR_3D sw7_escapehearse 955.9623 -1100.1404 22.7516 3.0 3.0 3.0 FALSE

	  IF NOT IS_CHAR_DEAD sw7_escapechauffeur
	  IF NOT IS_CAR_DEAD sw7_escapehearse

	  TASK_CAR_DRIVE_TO_COORD sw7_escapechauffeur sw7_escapehearse 929.9791 -1103.6449 22.8506 10.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
  	  sw7_missionprogress = 60
			
		IF NOT IS_CHAR_DEAD sw7_kane
		   //	CLEAR_CHAR_TASKS sw7_kane
		ENDIF
	
		
	  ENDIF
	  ENDIF
	
	
	
	ENDIF
	ENDIF
	ENDIF


ENDIF

IF sw7_missionprogress = 60

	



	IF NOT IS_CAR_DEAD sw7_escapehearse
	IF LOCATE_CAR_3D sw7_escapehearse 929.9791 -1103.6449 22.8506 5.0 5.0 3.0 FALSE
	IF IS_CAR_STOPPED sw7_escapehearse
	  
		
		IF NOT IS_CHAR_DEAD sw7_kane
			
			CLEAR_CHAR_TASKS sw7_kane
			//WAIT 500
			
			//dodgy area!!!!!!!!!!!!!!!!!!!!!
			IF NOT IS_CHAR_DEAD sw7_kane
			IF NOT IS_CAR_DEAD sw7_escapehearse
				
				 sw7_convo_counter = 1
			 	 GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random


		  		TASK_ENTER_CAR_AS_PASSENGER	sw7_kane sw7_escapehearse -2 0
				sw7_missionprogress = 65
			ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD sw7_reinforce1
			CLEAR_CHAR_TASKS sw7_reinforce1
			OPEN_SEQUENCE_TASK sw7_reinforce1seq
				TASK_LEAVE_ANY_CAR -1
				TASK_GO_STRAIGHT_TO_COORD -1 916.3773 -1107.9373 23.1694 PEDMOVE_RUN 10000
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
				//TASK_GO_STRAIGHT_TO_COORD -1 833.1347 -1095.1868 22.8281 PEDMOVE_WALK 10000
	  	    CLOSE_SEQUENCE_TASK sw7_reinforce1seq
			PERFORM_SEQUENCE_TASK sw7_reinforce1 sw7_reinforce1seq
			CLEAR_SEQUENCE_TASK sw7_reinforce1seq
			sw7_missionprogress = 65
		ENDIF

		IF NOT IS_CHAR_DEAD sw7_reinforce2
			CLEAR_CHAR_TASKS sw7_reinforce2
			OPEN_SEQUENCE_TASK sw7_reinforce2seq
				TASK_LEAVE_ANY_CAR -1
				TASK_GO_STRAIGHT_TO_COORD -1 918.4471 -1094.8062 23.4897 PEDMOVE_RUN 10000

				TASK_KILL_CHAR_ON_FOOT -1 scplayer
				//TASK_GO_STRAIGHT_TO_COORD -1 833.1347 -1095.1868 22.8281 PEDMOVE_WALK 10000
	  	    CLOSE_SEQUENCE_TASK sw7_reinforce2seq
			PERFORM_SEQUENCE_TASK sw7_reinforce2 sw7_reinforce2seq
			CLEAR_SEQUENCE_TASK sw7_reinforce2seq
			sw7_missionprogress = 65
		ENDIF




	ENDIF	
	ENDIF
	ENDIF
ENDIF // sw7_missionprogress = 60 condition check



IF sw7_missionprogress = 65


   //	IF NOT IS_CHAR_DEAD sw7_kane	// Added 07.10.04


		IF sw7_convo_counter  > 0
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 1
						IF sw7_random = 0
							sw7_counter = 46
						ENDIF
					 	IF sw7_random = 1
							sw7_counter = 47
						ENDIF
						IF sw7_random = 2
							sw7_counter = 48
						ENDIF


						sw7_convo_counter = 2
						BREAK
					CASE 2	
						sw7_counter = 0
						PRINT_NOW TWAR626 6000 1
						sw7_convo_counter = 0 // finish convo
						BREAK


				ENDSWITCH
			   //	sw7_audio_underway = 1
			ENDIF
		ENDIF


	
	//ENDIF




	IF IS_CHAR_DEAD sw7_kane
		IF NOT IS_CAR_DEAD sw7_escapehearse
			IF NOT IS_CHAR_DEAD sw7_escapechauffeur
				CLEAR_CHAR_TASKS sw7_escapechauffeur

				OPEN_SEQUENCE_TASK sw7_chauffeur3seq
				TASK_LEAVE_ANY_CAR -1
				//TASK_GO_STRAIGHT_TO_COORD -1 918.4471 -1094.8062 23.4897 PEDMOVE_RUN 10000

				TASK_KILL_CHAR_ON_FOOT -1 scplayer
				//TASK_GO_STRAIGHT_TO_COORD -1 833.1347 -1095.1868 22.8281 PEDMOVE_WALK 10000
	  	    	CLOSE_SEQUENCE_TASK sw7_chauffeur3seq
				PERFORM_SEQUENCE_TASK sw7_escapechauffeur sw7_chauffeur3seq
				CLEAR_SEQUENCE_TASK sw7_chauffeur3seq
				sw7_missionprogress = 90

			ENDIF
		ENDIF 
	ENDIF	




	IF NOT IS_CAR_DEAD sw7_escapehearse
	IF NOT IS_CHAR_DEAD sw7_kane
		IF NOT IS_CHAR_DEAD sw7_escapechauffeur
			IF IS_CHAR_SITTING_IN_CAR sw7_kane sw7_escapehearse
				REMOVE_BLIP sw7_kaneB	  
				SET_CAR_TEMP_ACTION sw7_escapehearse TEMPACT_REVERSE 6000	//was 5000
				LOCK_CAR_DOORS sw7_escapehearse CARLOCK_LOCKOUT_PLAYER_ONLY
				SET_CHAR_CANT_BE_DRAGGED_OUT sw7_kane TRUE
				SET_CHAR_CANT_BE_DRAGGED_OUT sw7_kane TRUE

				SET_CHAR_STAY_IN_CAR_WHEN_JACKED sw7_kane TRUE


			   	//DELETE_CHAR sw7_kane

				sw7_missionprogress = 70
				TIMERA = 0
				CLEAR_PRINTS
			   
			     sw7_convo_counter = 11 // only want to do this once!
				 GENERATE_RANDOM_INT_IN_RANGE 0 4 sw7_random

				IF NOT IS_CAR_DEAD sw7_pursuitcar
				LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_UNLOCKED
				LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_LOCKOUT_PLAYER_ONLY
				ENDIF

				IF NOT IS_CHAR_DEAD sweet
					ADD_BLIP_FOR_CHAR sweet sw7_sweetB
					SET_BLIP_AS_FRIENDLY sw7_sweetB TRUE

					//write_debug late
					CLEAR_CHAR_TASKS sweet
					OPEN_SEQUENCE_TASK sw7_pursuitseq
			  		TASK_GO_STRAIGHT_TO_COORD -1  930.8062 -1114.4586 23.0940 PEDMOVE_RUN 10000
					TASK_ENTER_CAR_AS_PASSENGER -1 sw7_pursuitcar 30000 -1
					CLOSE_SEQUENCE_TASK sw7_pursuitseq
					PERFORM_SEQUENCE_TASK sweet sw7_pursuitseq
					CLEAR_SEQUENCE_TASK sw7_pursuitseq
					sw7_pursuitunderway = 1
													    
				ENDIF

				

		ENDIF
	ENDIF
	ENDIF
	ENDIF
ENDIF

IF sw7_missionprogress = 70
		IF sw7_convo_counter  > 10
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 11
						IF sw7_random = 0
							sw7_counter = 49
						ENDIF
					 	IF sw7_random = 1
							sw7_counter = 50
						ENDIF
						IF sw7_random = 2
							sw7_counter = 51
						ENDIF
						IF sw7_random = 3
							sw7_counter = 52
						ENDIF


						sw7_convo_counter = 12
						BREAK
					CASE 12	
						sw7_counter = 0
						PRINT_NOW TWAR629 6000 1
						sw7_convo_counter = 0 // finish convo
						BREAK


				ENDSWITCH
			   //	sw7_audio_underway = 1
			ENDIF
		ENDIF

	IF TIMERA > 5000
		IF NOT IS_CAR_DEAD sw7_escapehearse
				IF NOT IS_CHAR_DEAD sw7_escapechauffeur

					TASK_CAR_DRIVE_TO_COORD sw7_escapechauffeur sw7_escapehearse 1952.4320 -1136.2377 24.6210 20.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
                    CLEAR_PRINTS
					sw7_missionprogress = 75
				ENDIF
		ENDIF
	ENDIF
ENDIF
																							
IF sw7_missionprogress = 75
		IF sw7_convo_counter  > 10
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 11
						IF sw7_random = 0
							sw7_counter = 49
						ENDIF
					 	IF sw7_random = 1
							sw7_counter = 50
						ENDIF
						IF sw7_random = 2
							sw7_counter = 51
						ENDIF
						IF sw7_random = 3
							sw7_counter = 52
						ENDIF


						sw7_convo_counter = 12
						BREAK
					CASE 12	
						sw7_counter = 0
						CLEAR_PRINTS
						PRINT_NOW TWAR629 6000 1
						sw7_convo_counter = 0 // finish convo
						BREAK


				ENDSWITCH
			   //	sw7_audio_underway = 1
			ENDIF
		ENDIF

	
	IF NOT IS_CHAR_DEAD sw7_kane
		IF LOCATE_CHAR_ANY_MEANS_3D sw7_kane 1952.4320 -1136.2377 24.6210 5.0 5.0 5.0 FALSE
			
			sw7_missionprogress = 80
			IF NOT IS_CAR_DEAD sw7_escapehearse
					IF NOT IS_CHAR_DEAD sw7_escapechauffeur

						CLEAR_CHAR_TASKS sw7_escapechauffeur
						TASK_CAR_DRIVE_WANDER sw7_escapechauffeur sw7_escapehearse 30.0	DRIVINGMODE_AVOIDCARS
		    		ENDIF
			ENDIF




		ENDIF
	
		IF NOT IS_CAR_DEAD sw7_escapehearse
			
			IF NOT IS_CHAR_IN_CAR sw7_kane sw7_escapehearse
				IF sw7_finalkaneexit = 0
				CLEAR_CHAR_TASKS sw7_kane
				TASK_KILL_CHAR_ON_FOOT sw7_kane scplayer
				sw7_finalkaneexit = 1
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_escapechauffeur
				IF NOT IS_CHAR_IN_CAR sw7_escapechauffeur sw7_escapehearse
					IF sw7_finalchauffexit = 0
					CLEAR_CHAR_TASKS sw7_escapechauffeur
					TASK_KILL_CHAR_ON_FOOT sw7_escapechauffeur scplayer
					sw7_finalchauffexit = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
	
	
	
	ENDIF

	

ENDIF

IF sw7_missionprogress = 80

		IF NOT IS_CAR_DEAD sw7_escapehearse
		IF NOT IS_CHAR_DEAD sw7_kane

			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer sw7_kane 50.0 50.0 50.0 FALSE

				IF NOT IS_CAR_ON_SCREEN sw7_escapehearse 
					GENERATE_RANDOM_INT_IN_RANGE 0 3 sw7_random
					sw7_convo_counter = 61
					sw7_missionprogress = 999
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD sw7_escapehearse
				IF NOT IS_CHAR_IN_CAR sw7_kane sw7_escapehearse
					IF sw7_finalkaneexit = 0

					CLEAR_CHAR_TASKS sw7_kane
					TASK_KILL_CHAR_ON_FOOT sw7_kane scplayer
						sw7_finalkaneexit = 1
					ENDIF
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_escapechauffeur
				IF NOT IS_CHAR_IN_CAR sw7_escapechauffeur sw7_escapehearse
					IF sw7_finalchauffexit = 0

					CLEAR_CHAR_TASKS sw7_escapechauffeur
					TASK_KILL_CHAR_ON_FOOT sw7_escapechauffeur scplayer
						sw7_finalchauffexit = 1
					ENDIF

				ENDIF
			ENDIF

		ENDIF
		ENDIF

ENDIF





IF sw7_checkkane = 1

	GOSUB sw7_mopdown
	/*IF muP1 = 1
		IF IS_CHAR_DEAD sw7_protector1
			REMOVE_BLIP sw7_protector1B
			sw7_mopupcount --
			muP1 = 2
		ENDIF
	ENDIF

	IF muP2 = 1
		IF IS_CHAR_DEAD sw7_protector2
			REMOVE_BLIP sw7_protector2B
			sw7_mopupcount --
			muP2 = 2
		ENDIF
	ENDIF
	IF muP3 = 1
		IF IS_CHAR_DEAD sw7_protector3
			REMOVE_BLIP sw7_protector3B
			sw7_mopupcount --
			muP3 = 2
		ENDIF
	ENDIF
	 IF muP4 = 1
		IF IS_CHAR_DEAD sw7_protector4
			REMOVE_BLIP sw7_protector4B
			sw7_mopupcount --
			muP4 = 2
		ENDIF
	ENDIF
	IF muP5 = 1
		IF IS_CHAR_DEAD sw7_protector5
			sw7_mopupcount --
			REMOVE_BLIP sw7_protectorB
			muP5 = 2
		ENDIF
	ENDIF
	IF muP6 = 1
		IF IS_CHAR_DEAD sw7_protector6
			REMOVE_BLIP sw7_protector6B
			sw7_mopupcount --
			muP6 = 2
		ENDIF
	ENDIF

	IF muR1 = 1
		IF IS_CHAR_DEAD sw7_reinforce1
			REMOVE_BLIP sw7_reinforce1B
			sw7_mopupcount --
			muR1 = 2
		ENDIF
	ENDIF
	
	IF muR2 = 1
		IF IS_CHAR_DEAD sw7_reinforce2
			REMOVE_BLIP sw7_reinforce2B
			sw7_mopupcount --
			muR2 = 2
		ENDIF
	ENDIF

	*/

		IF sw7_convo_counter  > 0
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 1
						sw7_counter = 67
						sw7_convo_counter =  2
						BREAK
					CASE 2
						sw7_counter = 68
						sw7_convo_counter =  3
						BREAK

			   		CASE 3 
						sw7_counter = 0
					  	PRINT_NOW TWAR628 6000 1
						sw7_convo_counter = 0 // finish convo
						BREAK


				ENDSWITCH
			   //	sw7_audio_underway = 1
			ENDIF
		ENDIF





	IF sw7_mopupcount = 0
		IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CAR_DEAD sw7_pursuitcar
		ADD_BLIP_FOR_CAR sw7_pursuitcar sw7_pursuitcarb
	   //	LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_UNLOCKED	
		SET_BLIP_AS_FRIENDLY sw7_pursuitcarb TRUE
		CLEAR_PRINTS
		PRINT_NOW TWAR644 6000 1
		IF IS_CHAR_SITTING_IN_CAR sweet	sw7_pursuitcar
		LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_UNLOCKED
		ENDIF
		//SET_CAR_HEALTH sw7_pursuitcar 1000
		SET_CAR_PROOFS sw7_pursuitcar FALSE FALSE FALSE FALSE FALSE
		sw7_checkkane = 4
		ENDIF
		ENDIF
	ENDIF
ENDIF


									   

IF sw7_pursuitunderway = 1

	IF NOT IS_CAR_DEAD sw7_pursuitcar
		IF NOT IS_CHAR_DEAD sweet
			IF IS_CHAR_SITTING_IN_CAR sweet sw7_pursuitcar
				CLEAR_PRINTS
			    sw7_convo_counter = 1
			  	GENERATE_RANDOM_INT_IN_RANGE 0 2 sw7_random
                LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_UNLOCKED
				SET_CAR_HEALTH sw7_pursuitcar 1000
				sw7_pursuitunderway = 2
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF sw7_pursuitunderway = 2
		IF sw7_convo_counter  > 0
			IF sw7_audio_underway = 0

				SWITCH sw7_convo_counter
					CASE 1
						IF sw7_random = 0
							sw7_counter = 53
						ENDIF
					 	IF sw7_random = 1
							sw7_counter = 54
						ENDIF

						sw7_convo_counter = 2
						BREAK
					CASE 2	
						sw7_counter = 0
						CLEAR_PRINTS
						PRINT_NOW TWAR640 6000 1
						sw7_convo_counter = 0 // finish convo
						BREAK


				ENDSWITCH
			   //	sw7_audio_underway = 1
			ENDIF
		ENDIF

	
	IF NOT IS_CAR_DEAD sw7_pursuitcar
		IF IS_CHAR_SITTING_IN_CAR scplayer sw7_pursuitcar
			SET_CAR_PROOFS sw7_pursuitcar FALSE FALSE FALSE FALSE FALSE
			LOCK_CAR_DOORS sw7_pursuitcar CARLOCK_UNLOCKED


			REMOVE_BLIP sw7_sweetB
			IF NOT IS_CHAR_DEAD sw7_kane
				ADD_BLIP_FOR_CHAR sw7_kane sw7_kaneB
				CLEAR_PRINTS
			    sw7_convo_counter = 1
			  	GENERATE_RANDOM_INT_IN_RANGE 0 2 sw7_random

				sw7_blipswap = 0
			ENDIF
			
			sw7_ptrfullfixflag = 1

			sw7_pursuitunderway = 3
			sw7_drivebyinprogress = 1
			sw7_driveby_counter = 1
		ENDIF
	ENDIF
ENDIF

IF sw7_pursuitunderway = 3
	IF NOT IS_CHAR_DEAD sw7_kane
	
	IF NOT IS_CAR_DEAD sw7_pursuitcar
														 
		IF IS_CHAR_IN_CAR scplayer sw7_pursuitcar
			IF sw7_convo_counter  > 0
				IF sw7_audio_underway = 0
	
					SWITCH sw7_convo_counter
						CASE 1
							IF sw7_random = 0
								sw7_counter = 55
							ENDIF
						 	IF sw7_random = 1
								sw7_counter = 56
							ENDIF

							sw7_convo_counter = 2
							BREAK
						CASE 2	
							sw7_counter = 0
							PRINT_NOW TWAR641 6000 1
							sw7_convo_counter = 0 // finish convo
							BREAK


					ENDSWITCH
				   //	sw7_audio_underway = 1
				ENDIF
			ENDIF

		ENDIF



				IF sw7_blipswap = 0
				IF NOT IS_CHAR_IN_CAR scplayer sw7_pursuitcar
					ADD_BLIP_FOR_CAR sw7_pursuitcar sw7_pursuitcarB
					SET_BLIP_AS_FRIENDLY sw7_pursuitcarB TRUE
					REMOVE_BLIP sw7_kaneB
					CLEAR_PRINTS
					PRINT_NOW TWAR643 6000 1
					sw7_blipswap = 1												  
				ENDIF

				ENDIF

				IF sw7_blipswap = 1
				IF IS_CHAR_IN_CAR scplayer sw7_pursuitcar
					REMOVE_BLIP sw7_pursuitcarB							
					IF NOT IS_CHAR_DEAD sw7_kane
						ADD_BLIP_FOR_CHAR sw7_kane sw7_kaneB
						PRINT_NOW TWAR641 6000 1
					ENDIF			
					sw7_blipswap = 0
				ENDIF
				ENDIF
	ENDIF
	ENDIF

ENDIF


IF sw7_drivebyinprogress = 1
	
	IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CHAR_DEAD sw7_kane
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D sweet sw7_kane 30.0 30.0 FALSE
					CLEAR_CHAR_TASKS sweet
					TASK_DRIVE_BY sweet sw7_kane -1 0.0 0.0 0.0 50.0 DRIVEBY_AI_ALL_DIRN FALSE 90
					sw7_drivebyinprogress = 2
					sw7_driveby_counter = 1
			 		 GENERATE_RANDOM_INT_IN_RANGE 0 4 sw7_random


				ENDIF
			ENDIF
	ENDIF

ENDIF

IF sw7_drivebyinprogress = 2
	IF sw7_driveby_counter  > 0
			IF sw7_audio_underway = 0

				SWITCH sw7_driveby_counter
					CASE 1
						IF sw7_random = 0
							sw7_counter = 57
						ENDIF
					 	IF sw7_random = 1
							sw7_counter = 58
						ENDIF
						IF sw7_random = 2
							sw7_counter = 59
						ENDIF
					 	IF sw7_random = 3
							sw7_counter = 60
						ENDIF

						sw7_driveby_counter = 2
						BREAK
					CASE 2	
						sw7_counter = 0
						PRINT_NOW TWAR649 6000 1
						sw7_driveby_counter = 0 // finish convo
						BREAK


				ENDSWITCH
			   //	sw7_audio_underway = 1
			ENDIF
		ENDIF

 
	IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CHAR_DEAD sw7_kane
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D sweet sw7_kane 40.0 40.0 FALSE
					CLEAR_CHAR_TASKS sweet
					sw7_drivebyinprogress = 1
				ENDIF
			ENDIF
	ENDIF
ENDIF




IF sw7_ptrfullfixflag = 1
	IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 875.3232 -1071.9413 23.3364 400.0 400.0 100.0 FALSE
		//write_debug mopping_up
		GOSUB sw7_ptrfullfix
		sw7_ptrfullfixflag = 2
	ENDIF
ENDIF

IF sw7_disbandfix = 1
	IF timerb > 6000 
		CLEAR_HELP
		PRINT_HELP TWAR801  
		sw7_disbandfix = 2
		sw7_regroupfix = 1
		timerb = 0
	ENDIF
ENDIF

GOTO sweet7_main_mission_loop


cycle_kane:

			IF NOT IS_CHAR_DEAD sw7_kane
			   //	SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_kane 150.0
				 //CLEAR_CHAR_TASKS sw7_kane

			   	OPEN_SEQUENCE_TASK sw7_kaneseqA
				//CLEAR_CHAR_TASKS sw7_kane
				TASK_TOGGLE_PED_THREAT_SCANNER -1 FALSE FALSE FALSE
				
				
				//TASK_FOLLOW_PATH_NODES_TO_COORD -1 sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] PEDMOVE_SPRINT -2
				TASK_GO_STRAIGHT_TO_COORD -1 sw7_fleeKX[sw7_fleeindex] sw7_fleeKY[sw7_fleeindex] sw7_fleeKZ[sw7_fleeindex] PEDMOVE_SPRINT -2
				//TASK_GO_TO_COORD_WHILE_SHOOTING -1 875.9781 -1056.9521 27.1413 PEDMOVE_RUN 100.0 4.0 scplayer
				//WRITE_DEBUG going
    			//TASK_STAY_IN_SAME_PLACE -1 TRUE
    		  	//TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50

    			CLOSE_SEQUENCE_TASK sw7_kaneseqA

    			PERFORM_SEQUENCE_TASK sw7_kane sw7_kaneseqA
    			CLEAR_SEQUENCE_TASK sw7_kaneseqA
				
			ENDIF
			 /*
			IF sw7_fleeindex = 1
				IF NOT IS_CHAR_DEAD sw7_protector1	
					CLEAR_CHAR_TASKS sw7_protector1
					TASK_DIE sw7_protector1
				ENDIF
			ENDIF

			IF sw7_fleeindex = 2
				IF NOT IS_CHAR_DEAD sw7_protector2	
					CLEAR_CHAR_TASKS sw7_protector2
					TASK_DIE sw7_protector2
				ENDIF
			ENDIF
			
			IF sw7_fleeindex = 3
				IF NOT IS_CHAR_DEAD sw7_protector3	
					CLEAR_CHAR_TASKS sw7_protector3
					TASK_DIE sw7_protector3
				ENDIF
			ENDIF
			
			IF sw7_fleeindex = 4
				IF NOT IS_CHAR_DEAD sw7_protector4	
					CLEAR_CHAR_TASKS sw7_protector4
					TASK_DIE sw7_protector4
				ENDIF
			ENDIF
		   	*/

			
RETURN

cycle_goons:
			
			IF NOT IS_CHAR_DEAD sw7_protector1
		   
				OPEN_SEQUENCE_TASK sw7_protector1seqA
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 sw7_flee1X[sw7_fleeindex] sw7_flee1Y[sw7_fleeindex] sw7_flee1Z[sw7_fleeindex] PEDMOVE_RUN 100.0 1.0 scplayer
				//TASK_GO_TO_COORD_WHILE_SHOOTING -1 875.9781 -1056.9521 27.1413 PEDMOVE_RUN 100.0 4.0 scplayer

    			TASK_STAY_IN_SAME_PLACE -1 FALSE	// was true
    			TASK_KILL_CHAR_ON_FOOT -1 scplayer
    			CLOSE_SEQUENCE_TASK sw7_protector1seqA

    			PERFORM_SEQUENCE_TASK sw7_protector1 sw7_protector1seqA
    			CLEAR_SEQUENCE_TASK sw7_protector1seqA
				
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_protector2
			  
				OPEN_SEQUENCE_TASK sw7_protector2seqA
			
				//TASK_FOLLOW_PATH_NODES_TO_COORD_SHOOTING -1 sw7_flee2X[sw7_fleeindex] sw7_flee2Y[sw7_fleeindex] sw7_flee2Z[sw7_fleeindex] PEDMOVE_RUN -2 scplayer

				TASK_GO_TO_COORD_WHILE_SHOOTING -1 sw7_flee2X[sw7_fleeindex] sw7_flee2Y[sw7_fleeindex] sw7_flee2Z[sw7_fleeindex] PEDMOVE_RUN 100.0 1.0 scplayer
				//TASK_GO_TO_COORD_WHILE_SHOOTING -1 875.9781 -1056.9521 27.1413 PEDMOVE_RUN 100.0 4.0 scplayer

    			TASK_STAY_IN_SAME_PLACE -1 FALSE // was true
    			TASK_KILL_CHAR_ON_FOOT -1 scplayer
				TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 5000 70

    			CLOSE_SEQUENCE_TASK sw7_protector2seqA

    			PERFORM_SEQUENCE_TASK sw7_protector2 sw7_protector2seqA
    			CLEAR_SEQUENCE_TASK sw7_protector2seqA
				
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_protector3
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_protector3 150.0

					OPEN_SEQUENCE_TASK sw7_protector3seqA
				   //	TASK_FOLLOW_PATH_NODES_TO_COORD_SHOOTING -1 sw7_flee3X[sw7_fleeindex] sw7_flee3Y[sw7_fleeindex] sw7_flee3Z[sw7_fleeindex] PEDMOVE_RUN -2 scplayer

					TASK_GO_TO_COORD_WHILE_SHOOTING -1 sw7_flee3X[sw7_fleeindex] sw7_flee3Y[sw7_fleeindex] sw7_flee3Z[sw7_fleeindex] PEDMOVE_RUN 100.0 1.0 scplayer
					//TASK_GO_TO_COORD_WHILE_SHOOTING -1 875.9781 -1056.9521 27.1413 PEDMOVE_RUN 100.0 4.0 scplayer

    				TASK_STAY_IN_SAME_PLACE -1 FALSE // was true
    				TASK_KILL_CHAR_ON_FOOT -1 scplayer
    				CLOSE_SEQUENCE_TASK sw7_protector3seqA

    				PERFORM_SEQUENCE_TASK sw7_protector3 sw7_protector3seqA
    				CLEAR_SEQUENCE_TASK sw7_protector3seqA
				
			ENDIF

				   
	 
		   

RETURN	// cycle_goons procedure


sw7_initial:
	//IF NOT IS_CHAR_DEAD sw7_kanechauffeur
		    sw7_missionprogress = 20
			sw7_chauffeurkilltask  = 1
			sw7_disbandfix = 1
			timerb = 0
			
			
			IF NOT IS_CHAR_DEAD sw7_protector1
				GIVE_WEAPON_TO_CHAR sw7_protector1 WEAPONTYPE_PISTOL 2000
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_protector2
				GIVE_WEAPON_TO_CHAR sw7_protector2 WEAPONTYPE_PISTOL 2000
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_protector3
				GIVE_WEAPON_TO_CHAR sw7_protector3 WEAPONTYPE_PISTOL 2000
			ENDIF


			IF NOT IS_CHAR_DEAD sw7_kane
				GIVE_WEAPON_TO_CHAR sw7_kane WEAPONTYPE_PISTOL 2000
			ENDIF
			


			IF NOT IS_CHAR_DEAD sw7_kane
				//SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_kane 150.0

				OPEN_SEQUENCE_TASK sw7_kaneseqA
				IF sw7_fleeindex = 3
				  TASK_GO_STRAIGHT_TO_COORD -1 889.4055 -1057.0601 24.0241 PEDMOVE_SPRINT -2
				ENDIF

		  		IF sw7_fleeindex < 3
				    TASK_GO_STRAIGHT_TO_COORD -1 889.4055 -1057.0601 24.0241 PEDMOVE_SPRINT -2
				ENDIF

    			TASK_STAY_IN_SAME_PLACE -1 TRUE
    		  	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 3000 50

    			CLOSE_SEQUENCE_TASK sw7_kaneseqA

    			PERFORM_SEQUENCE_TASK sw7_kane sw7_kaneseqA
    			CLEAR_SEQUENCE_TASK sw7_kaneseqA
				
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_protector1
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_protector1 150.0

				OPEN_SEQUENCE_TASK sw7_protector1seqA
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 884.8275 -1057.9972 24.8115 PEDMOVE_RUN 100.0 1.0 scplayer
				//TASK_GO_TO_COORD_WHILE_SHOOTING -1 875.9781 -1056.9521 27.1413 PEDMOVE_RUN 100.0 4.0 scplayer

    			TASK_STAY_IN_SAME_PLACE -1 TRUE
    			TASK_KILL_CHAR_ON_FOOT -1 scplayer
    			CLOSE_SEQUENCE_TASK sw7_protector1seqA

    			PERFORM_SEQUENCE_TASK sw7_protector1 sw7_protector1seqA
    			CLEAR_SEQUENCE_TASK sw7_protector1seqA
				
			ENDIF

			IF NOT IS_CHAR_DEAD sw7_protector2
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_protector2 150.0

				OPEN_SEQUENCE_TASK sw7_protector2seqA
				TASK_GO_TO_COORD_WHILE_SHOOTING -1 880.5971 -1057.4679 25.9345 PEDMOVE_RUN 100.0 1.0 scplayer
				//TASK_GO_TO_COORD_WHILE_SHOOTING -1 875.9781 -1056.9521 27.1413 PEDMOVE_RUN 100.0 4.0 scplayer

    			TASK_STAY_IN_SAME_PLACE -1 TRUE
    			TASK_KILL_CHAR_ON_FOOT -1 scplayer
				TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 5000 70

    			CLOSE_SEQUENCE_TASK sw7_protector2seqA

    			PERFORM_SEQUENCE_TASK sw7_protector2 sw7_protector2seqA
    			CLEAR_SEQUENCE_TASK sw7_protector2seqA
				
			ENDIF

				IF NOT IS_CHAR_DEAD sw7_protector3
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_protector3 150.0

					OPEN_SEQUENCE_TASK sw7_protector3seqA
					TASK_GO_TO_COORD_WHILE_SHOOTING -1 881.8948 -1061.7740 24.2952 PEDMOVE_RUN 100.0 1.0 scplayer
					//TASK_GO_TO_COORD_WHILE_SHOOTING -1 875.9781 -1056.9521 27.1413 PEDMOVE_RUN 100.0 4.0 scplayer

    				TASK_STAY_IN_SAME_PLACE -1 TRUE
    				TASK_KILL_CHAR_ON_FOOT -1 scplayer
    				CLOSE_SEQUENCE_TASK sw7_protector3seqA

    				PERFORM_SEQUENCE_TASK sw7_protector3 sw7_protector3seqA
    				CLEAR_SEQUENCE_TASK sw7_protector3seqA
				
				ENDIF

				IF NOT IS_CHAR_DEAD sw7_protector4
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_protector4 150.0

					OPEN_SEQUENCE_TASK sw7_protector4seqA
					
    				TASK_STAY_IN_SAME_PLACE -1 FALSE
    			  	TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 scplayer DUCK_RANDOMLY 5000 70

    				CLOSE_SEQUENCE_TASK sw7_protector4seqA

    				PERFORM_SEQUENCE_TASK sw7_protector4 sw7_protector4seqA
    				CLEAR_SEQUENCE_TASK sw7_protector4seqA
				
				ENDIF

				IF NOT IS_CHAR_DEAD sw7_protector5
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_protector5 150.0

					OPEN_SEQUENCE_TASK sw7_protector5seqA
					
    				TASK_STAY_IN_SAME_PLACE -1 FALSE
    				TASK_KILL_CHAR_ON_FOOT -1 scplayer
    				CLOSE_SEQUENCE_TASK sw7_protector5seqA

    				PERFORM_SEQUENCE_TASK sw7_protector5 sw7_protector5seqA
    				CLEAR_SEQUENCE_TASK sw7_protector5seqA
				
				ENDIF

				IF NOT IS_CHAR_DEAD sw7_protector6
					SET_FOLLOW_NODE_THRESHOLD_DISTANCE sw7_protector6 150.0

					OPEN_SEQUENCE_TASK sw7_protector6seqA
					
    			   	TASK_STAY_IN_SAME_PLACE -1 FALSE
    				TASK_KILL_CHAR_ON_FOOT -1 scplayer
    				CLOSE_SEQUENCE_TASK sw7_protector6seqA

    				PERFORM_SEQUENCE_TASK sw7_protector6 sw7_protector6seqA
    				CLEAR_SEQUENCE_TASK sw7_protector6seqA
				
				ENDIF
 RETURN


sw7_mopup:

	IF NOT IS_CHAR_DEAD sw7_protector1
		ADD_BLIP_FOR_CHAR sw7_protector1 sw7_protector1B
		CHANGE_BLIP_SCALE sw7_protector1B 2

		sw7_mopupcount ++
		muP1 = 1
	ENDIF
	IF NOT IS_CHAR_DEAD sw7_protector2
		ADD_BLIP_FOR_CHAR sw7_protector2 sw7_protector2B
		CHANGE_BLIP_SCALE sw7_protector2B 2

		sw7_mopupcount ++
		muP2 = 1
	ENDIF
	IF NOT IS_CHAR_DEAD sw7_protector3
		ADD_BLIP_FOR_CHAR sw7_protector3 sw7_protector3B
		CHANGE_BLIP_SCALE sw7_protector3B 2

		sw7_mopupcount ++
		muP3 = 1
	ENDIF
	IF NOT IS_CHAR_DEAD sw7_protector4
		ADD_BLIP_FOR_CHAR sw7_protector4 sw7_protector4B
		CHANGE_BLIP_SCALE sw7_protector4B 2

		sw7_mopupcount ++
		muP4 = 1
	ENDIF
	IF NOT IS_CHAR_DEAD sw7_protector5
		ADD_BLIP_FOR_CHAR sw7_protector5 sw7_protector5B
		CHANGE_BLIP_SCALE sw7_protector5B 2

		sw7_mopupcount ++
		muP5 = 1
	ENDIF
	IF NOT IS_CHAR_DEAD sw7_protector6
		ADD_BLIP_FOR_CHAR sw7_protector6 sw7_protector6B
		CHANGE_BLIP_SCALE sw7_protector6B 2

		sw7_mopupcount ++
		muP6 = 1
	ENDIF
	IF NOT IS_CHAR_DEAD sw7_reinforce1
		//PC bug fix
			CLEAR_CHAR_TASKS sw7_reinforce1
			OPEN_SEQUENCE_TASK sw7_reinforce1seq
				TASK_LEAVE_ANY_CAR -1
			   //	TASK_GO_STRAIGHT_TO_COORD -1 916.3773 -1107.9373 23.1694 PEDMOVE_RUN 10000
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
				//TASK_GO_STRAIGHT_TO_COORD -1 833.1347 -1095.1868 22.8281 PEDMOVE_WALK 10000
	  	    CLOSE_SEQUENCE_TASK sw7_reinforce1seq
			PERFORM_SEQUENCE_TASK sw7_reinforce1 sw7_reinforce1seq
			CLEAR_SEQUENCE_TASK sw7_reinforce1seq
		// End PC bug fix


		ADD_BLIP_FOR_CHAR sw7_reinforce1 sw7_reinforce1B
		CHANGE_BLIP_SCALE sw7_reinforce1B 2

		sw7_mopupcount ++
		muR1 = 1

	ENDIF
	IF NOT IS_CHAR_DEAD sw7_reinforce2
		// PC bug fix
			CLEAR_CHAR_TASKS sw7_reinforce2
			OPEN_SEQUENCE_TASK sw7_reinforce2seq
				TASK_LEAVE_ANY_CAR -1
				//TASK_GO_STRAIGHT_TO_COORD -1 916.3773 -1107.9373 23.1694 PEDMOVE_RUN 10000
				TASK_KILL_CHAR_ON_FOOT -1 scplayer
				//TASK_GO_STRAIGHT_TO_COORD -1 833.1347 -1095.1868 22.8281 PEDMOVE_WALK 10000
	  	    CLOSE_SEQUENCE_TASK sw7_reinforce2seq
			PERFORM_SEQUENCE_TASK sw7_reinforce2 sw7_reinforce2seq
			CLEAR_SEQUENCE_TASK sw7_reinforce2seq
		// End PC bug fix



		ADD_BLIP_FOR_CHAR sw7_reinforce2 sw7_reinforce2B
		CHANGE_BLIP_SCALE sw7_reinforce2B 2

		sw7_mopupcount ++
		muR2 = 1
	ENDIF


	// New PC section 08.12.04
	IF NOT IS_CHAR_DEAD sw7_escapechauffeur
		CLEAR_CHAR_TASKS sw7_escapechauffeur
		TASK_SMART_FLEE_CHAR sw7_escapechauffeur scplayer 100.0 -1

	ENDIF
	// End PC section.


RETURN

 sw7_mopdown:

	IF muP1 = 1
		IF IS_CHAR_DEAD sw7_protector1
			REMOVE_BLIP sw7_protector1B
			sw7_mopupcount --
			muP1 = 2
		ENDIF
	ENDIF

	IF muP2 = 1
		IF IS_CHAR_DEAD sw7_protector2
			REMOVE_BLIP sw7_protector2B
			sw7_mopupcount --
			muP2 = 2
		ENDIF
	ENDIF
	IF muP3 = 1
		IF IS_CHAR_DEAD sw7_protector3
			REMOVE_BLIP sw7_protector3B
			sw7_mopupcount --
			muP3 = 2
		ENDIF
	ENDIF
	 IF muP4 = 1
		IF IS_CHAR_DEAD sw7_protector4
			REMOVE_BLIP sw7_protector4B
			sw7_mopupcount --
			muP4 = 2
		ENDIF
	ENDIF
	IF muP5 = 1
		IF IS_CHAR_DEAD sw7_protector5
			sw7_mopupcount --
			REMOVE_BLIP sw7_protector5B
			muP5 = 2
		ENDIF
	ENDIF
	IF muP6 = 1
		IF IS_CHAR_DEAD sw7_protector6
			REMOVE_BLIP sw7_protector6B
			sw7_mopupcount --
			muP6 = 2
		ENDIF
	ENDIF

	IF muR1 = 1
		IF IS_CHAR_DEAD sw7_reinforce1
			REMOVE_BLIP sw7_reinforce1B
			sw7_mopupcount --
			muR1 = 2
		ENDIF
	ENDIF
	
	IF muR2 = 1
		IF IS_CHAR_DEAD sw7_reinforce2
			REMOVE_BLIP sw7_reinforce2B
			sw7_mopupcount --
			muR2 = 2
		ENDIF
	ENDIF


RETURN

sw7_ptrfullfix:
	IF NOT IS_CAR_DEAD sw7_pursuitcar
		IF NOT IS_CHAR_DEAD sw7_banger1
			IF NOT IS_CHAR_SITTING_IN_CAR sw7_banger1 sw7_pursuitcar
				MARK_CHAR_AS_NO_LONGER_NEEDED sw7_banger1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD sw7_banger2
			IF NOT IS_CHAR_SITTING_IN_CAR sw7_banger2 sw7_pursuitcar
				MARK_CHAR_AS_NO_LONGER_NEEDED sw7_banger2
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CHAR_DEAD sw7_kane
		IF NOT IS_CAR_DEAD sw7_escapehearse
			IF NOT IS_CHAR_SITTING_IN_CAR sw7_kane sw7_escapehearse
				MARK_CAR_AS_NO_LONGER_NEEDED sw7_escapehearse
			ENDIF
		ENDIF
	ENDIF
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_grove1
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_grove2
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_reinforce1
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_reinforce2
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_preach
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_greetinface
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_protector1
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_protector2
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_protector3
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_protector4
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_protector5
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_protector6
	MARK_CAR_AS_NO_LONGER_NEEDED sw7_kanehearse
	MARK_CAR_AS_NO_LONGER_NEEDED sw7_sweethearse
   //	WRITE_DEBUG charsremoved
  




RETURN

 // **************************************** Mission casino3 failed ***********************

mission_sweet7_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission casino3 passed ************************

mission_sweet7_passed:

	get_int_stat RESPECT sw7_respectvalue
   //	WRITE_DEBUG_WITH_FLOAT rpct sw7_respectvalue
   
	//AWARD_PLAYER_MISSION_RESPECT 10
	get_int_stat RESPECT sw7_respectvalue
	//WRITE_DEBUG_WITH_FLOAT rpct sw7_respectvalue

	REGISTER_MISSION_PASSED ( SWEET_7 )
	PLAYER_MADE_PROGRESS 1
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 10 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 10
	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL PLAYER1

	flag_sweet_mission_counter ++
	REMOVE_BLIP sweet_contact_blip
   	SET_POLICE_IGNORE_PLAYER player1 OFF



RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_sweet7:

SET_EVERYONE_IGNORE_PLAYER player1 FALSE
SWITCH_ROADS_ON 2297.0376 -1664.6005 10.8474 2324.8655 -1652.1244 14.8382
SWITCH_PED_ROADS_ON 2297.0376 -1664.6005 10.8474 2324.8655 -1652.1244 14.8382
SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE gan1_gzs  //back to starting value
SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE gan2_gzs //back to starting value

CLEAR_MISSION_AUDIO 3

CLEAR_HELP

GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission

SET_POLICE_IGNORE_PLAYER player1 OFF
CLEAR_WANTED_LEVEL player1

REMOVE_CHAR_ELEGANTLY sweet
RELEASE_WEATHER
UNLOAD_SPECIAL_CHARACTER 1

IF NOT IS_CHAR_DEAD sw7_banger1
   
	SET_CHAR_SUFFERS_CRITICAL_HITS sw7_banger1 TRUE
	SET_CHAR_HEALTH sw7_banger1 100

	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_banger1

ENDIF
IF NOT IS_CHAR_DEAD sw7_banger2
	SET_CHAR_SUFFERS_CRITICAL_HITS sw7_banger2 TRUE
	SET_CHAR_HEALTH sw7_banger2 100
	MARK_CHAR_AS_NO_LONGER_NEEDED sw7_banger2
ENDIF

REMOVE_BLIP sw7_kaneB
REMOVE_BLIP sw7_sweetB
REMOVE_BLIP sw7_hearseB
REMOVE_BLIP sw7_cementB
REMOVE_BLIP sw7_pursuitcarB

REMOVE_BLIP sw7_protector1B
REMOVE_BLIP sw7_protector2B
REMOVE_BLIP sw7_protector3B
REMOVE_BLIP sw7_protector4B
REMOVE_BLIP sw7_protector5B
REMOVE_BLIP sw7_protector6B

REMOVE_BLIP sw7_reinforce1B
REMOVE_BLIP sw7_reinforce2B

REMOVE_BLIP sw7_bangershouseB

REMOVE_BLIP sw7_banger1B
REMOVE_BLIP sw7_banger2B

REMOVE_BLIP sw7_respect1B
REMOVE_BLIP sw7_respect2B
REMOVE_BLIP sw7_respect3B

REMOVE_BLIP sw7_passedb

CLEAR_ONSCREEN_TIMER sw7_funeraltimer

REMOVE_ANIMATION graveyard
REMOVE_ANIMATION SMOKING

MARK_MODEL_AS_NO_LONGER_NEEDED greenwoo
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED admiral

MARK_MODEL_AS_NO_LONGER_NEEDED sadler

MARK_MODEL_AS_NO_LONGER_NEEDED FAM3
MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
MARK_MODEL_AS_NO_LONGER_NEEDED FAM1

MARK_MODEL_AS_NO_LONGER_NEEDED wmoprea
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS3
MARK_MODEL_AS_NO_LONGER_NEEDED BFYRI

SWITCH_ROADS_ON 772.2430 -1050.3231 10.2931 806.2723 -1130.1954 23.8359 

SWITCH_ROADS_ON sw7_coord_area_cemetry_min_x sw7_coord_area_cemetry_min_y 0.0 sw7_coord_area_cemetry_max_x sw7_coord_area_cemetry_max_y 60.0
SWITCH_PED_ROADS_ON sw7_coord_area_cemetry_min_x sw7_coord_area_cemetry_min_y 0.0 sw7_coord_area_cemetry_max_x sw7_coord_area_cemetry_max_y 60.0

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN
}

