MISSION_START
// *****************************************************************************************
// ************************************* 	finaleC ****************************************
// ************************************* Carter Block **************************************
// *********** Inspired by Zelda, Metroid, MGS, Perfect Dark, Time Crisis, GTA *************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

GOSUB mission_start_finaleC

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_finaleC_failed
ENDIF

GOSUB mission_cleanup_finaleC

MISSION_END

{

// ********************************** Variables for shootout *********************************

LVAR_INT gang1_fc
LVAR_INT gang2_fc
LVAR_INT gang3_fc
LVAR_INT gang4_fc
LVAR_INT gang5_fc
LVAR_INT gang6_fc
LVAR_INT gang7_fc
LVAR_INT gang8_fc
LVAR_INT gang9_fc
LVAR_INT gang10_fc
LVAR_INT gang11_fc
LVAR_INT gang12_fc
LVAR_INT gang13_fc
LVAR_INT gang14_fc
LVAR_INT gang15_fc
LVAR_INT tenpenny_fc 
LVAR_INT playervisible_fc


//timer
VAR_INT escapetimer_fc

//particles & fire
LVAR_INT fxPlayerSmoke_fc[9]
LVAR_INT nLoop_fc
LVAR_INT fire1_fc
LVAR_INT fire2_fc
LVAR_INT fire3_fc
LVAR_INT fire4_fc
LVAR_INT fire5_fc
LVAR_INT fire6_fc
LVAR_INT fire7_fc
LVAR_INT fire8_fc
LVAR_INT fire9_fc
LVAR_INT fire10_fc
LVAR_INT fire11_fc
LVAR_INT fire12_fc
LVAR_INT fire13_fc
LVAR_INT fire14_fc
LVAR_INT fire15_fc
LVAR_INT fire16_fc
LVAR_INT fire17_fc
LVAR_INT fire18_fc
LVAR_INT fire19_fc
LVAR_INT fire20_fc
LVAR_INT fire21_fc
LVAR_INT fire22_fc
LVAR_INT fire23_fc
LVAR_INT fire24_fc
LVAR_INT fire25_fc
LVAR_INT fire26_fc
LVAR_INT fire27_fc
LVAR_INT fire28_fc
LVAR_INT fire29_fc
LVAR_INT fire30_fc
LVAR_INT fire31_fc
LVAR_INT fire32_fc
LVAR_INT fire33_fc
LVAR_INT fire34_fc
LVAR_INT fire35_fc
LVAR_INT fire36_fc

//sequences
LVAR_INT carterc_DM
LVAR_INT stayshoot_fcseq
LVAR_INT stayshootnoduck_fcseq
LVAR_INT rolloutr_fcseq
LVAR_INT peekright_fcseq
LVAR_INT enemy_fc
LVAR_INT peekleft_fcseq
LVAR_FLOAT enemyx_fc 
LVAR_FLOAT enemyy_fc 
LVAR_FLOAT enemyz_fc
LVAR_INT enemytarget_fc
LVAR_INT enemytarget2_fc
LVAR_INT run2shoot_fcseq
LVAR_INT shootimer_s4
LVAR_FLOAT enemyx2_fc 
LVAR_FLOAT enemyy2_fc 
LVAR_FLOAT enemyz2_fc
LVAR_FLOAT heading_fc
//blips
LVAR_INT sweet_fcblip
LVAR_INT carter_fcblip
LVAR_INT extinguisher1_fcblip

//pickups
LVAR_INT armour1_fc
LVAR_INT armour2_fc
LVAR_INT armour3_fc
LVAR_INT armour4_fc
LVAR_INT health1_fc
LVAR_INT health2_fc
LVAR_INT health3_fc
LVAR_INT nightvision_fc
LVAR_INT extinguisher1_fc
LVAR_INT extinguisher2_fc

//flags
LVAR_INT finaleC_fcflag
LVAR_INT floorg_fcflag
LVAR_INT floor1_fcflag
LVAR_INT floor2_fcflag
LVAR_INT floor3_fcflag
LVAR_INT gang1_fcflag
LVAR_INT gang2_fcflag
LVAR_INT gang3_fcflag
LVAR_INT gang4_fcflag
LVAR_INT gang5_fcflag
LVAR_INT gang6_fcflag
LVAR_INT gang7_fcflag
LVAR_INT gang8_fcflag
LVAR_INT gang9_fcflag
LVAR_INT gang10_fcflag
LVAR_INT gang11_fcflag
LVAR_INT gang12_fcflag
LVAR_INT gang13_fcflag
LVAR_INT gang14_fcflag
LVAR_INT gang15_fcflag
LVAR_INT tenpennycut_fcflag 
LVAR_INT explosion_fcflag
LVAR_INT timerout_fcflag
LVAR_INT helptext_fcflag

//////////////////////////////////////////////////	chase stuff
LVAR_INT firetruck_fc
LVAR_INT benz_fc
LVAR_INT cop1_fc
LVAR_INT cop2_fc
LVAR_INT cop3_fc 
LVAR_INT train_fc
LVAR_INT copladder_fc
LVAR_INT coptruck_fc
LVAR_FLOAT carheading_fc
LVAR_FLOAT sweet_thisheading_fc
LVAR_FLOAT sweet_lastheading_fc
LVAR_FLOAT headingdiff_fc
LVAR_FLOAT sweetanimtime_fc
LVAR_INT gangcar1_fc
LVAR_INT gangcar2_fc
LVAR_INT gangcar3_fc
LVAR_INT gangcar4_fc
LVAR_INT gangcar5_fc
LVAR_FLOAT copladder_fcanimtime
LVAR_FLOAT number_of_frames_fc
LVAR_FLOAT diffx_fc
LVAR_FLOAT diffy_fc
LVAR_FLOAT diffz_fc
LVAR_FLOAT Worldx_fc
LVAR_FLOAT Worldy_fc
LVAR_FLOAT Worldz_fc
LVAR_FLOAT stepx_fc
LVAR_FLOAT stepy_fc
LVAR_FLOAT stepz_fc
LVAR_FLOAT sweetx_fc
LVAR_FLOAT sweety_fc
LVAR_FLOAT sweetz_fc
//counter
VAR_INT gripbar_fc
VAR_FLOAT fgripbardisplay_fc
VAR_INT igripbardisplay_fc //this is actual display

//sequences
LVAR_INT chasekill_fcseq

//text
LVAR_TEXT_LABEL $text_label_fc
LVAR_INT audio_label_fc
LVAR_TEXT_LABEL $input_text_fc

//flags
LVAR_INT sweethang_fcflag
LVAR_INT finaleCchase_fcflag
LVAR_INT sweetlegsup_fcflag
LVAR_INT locatestage_fcflag 
LVAR_INT speedup_fcflag
LVAR_INT createtrain_fcflag
LVAR_INT copladder_fcflag
LVAR_INT coptruck_fcflag
LVAR_INT displaygripbar_fcflag
LVAR_INT cardensity_fcflag
LVAR_INT notincar_fcflag
LVAR_INT cardead_fcflag
LVAR_INT lostsweet_fcflag
LVAR_INT progressaudio_fcflag
LVAR_INT handlingudio_fcflag
LVAR_INT stagechase_fcflag
LVAR_INT actiontext_fcflag

/////////////////////////////////////////////////

//////////////////////////////////////////////////	rails1 stuff
LVAR_INT cop4_fc
VAR_INT health_fc
LVAR_INT mexcar1_fc
LVAR_INT mexcar2_fc
LVAR_INT mexcar3_fc
LVAR_INT ecar1_fc
LVAR_INT ecar2_fc
LVAR_INT bike1_fc
LVAR_INT bike2_fc
LVAR_INT bike3_fc
LVAR_INT bike4_fc
LVAR_INT copcar1_fc
LVAR_INT copcar2_fc
LVAR_INT driverofcar_fc
LVAR_INT gang16_fc
LVAR_INT gang17_fc
LVAR_INT gang18_fc
LVAR_INT rioter1_fc
LVAR_INT rioter2_fc
LVAR_INT rioter3_fc
LVAR_INT rioter4_fc
LVAR_INT rioter5_fc
LVAR_INT rioter6_fc
LVAR_INT rioter7_fc
LVAR_INT rioter8_fc
LVAR_INT rioter9_fc
LVAR_INT rioter10_fc
LVAR_INT rioter11_fc
LVAR_INT rioter12_fc
LVAR_INT rioter13_fc
LVAR_INT rioter14_fc
LVAR_INT parkedcar_fc
//counter
VAR_INT carhealth_fc


//blips
LVAR_INT mexcar1_fcblip
LVAR_INT mexcar2_fcblip
LVAR_INT mexcar3_fcblip
LVAR_INT ecar1_fcblip
LVAR_INT ecar2_fcblip
LVAR_INT bike1_fcblip
LVAR_INT bike2_fcblip
LVAR_INT bike3_fcblip
LVAR_INT bike4_fcblip
LVAR_INT copcar1_fcblip
LVAR_INT copcar2_fcblip

//flags
VAR_INT finalerails_fcflag
VAR_INT rails_fcflag
VAR_INT rioters_fcflag
LVAR_INT copcar1_fcflag
LVAR_INT copcar2_fcflag
LVAR_INT mexcar1_fcflag
LVAR_INT mexcar2_fcflag
LVAR_INT mexcar3_fcflag
LVAR_INT ecar1_fcflag
LVAR_INT ecar2_fcflag
LVAR_INT bike1_fcflag
LVAR_INT bike2_fcflag
LVAR_INT bike3_fcflag
LVAR_INT bike4_fcflag
LVAR_INT copcar1swap_fcflag
LVAR_INT copcar2swap_fcflag
LVAR_INT mexcar1swap_fcflag
LVAR_INT mexcar2swap_fcflag
LVAR_INT mexcar3swap_fcflag
LVAR_INT ecar1swap_fcflag
LVAR_INT ecar2swap_fcflag
LVAR_INT bike1swap_fcflag
LVAR_INT bike2swap_fcflag
LVAR_INT bike3swap_fcflag
LVAR_INT bike4swap_fcflag


/////////////////////////////////////////////////

//////////////////////////////////////////////////	rails2 stuff
LVAR_INT policeheli_fc
LVAR_INT copcar3_fc
LVAR_INT cop5_fc
LVAR_INT cop6_fc

LVAR_INT copcar4_fc
LVAR_INT cop7_fc
LVAR_INT cop8_fc


//blips
LVAR_INT copcar3_fcblip
LVAR_INT copcar4_fcblip

//fx
LVAR_INT spark1_fxfc
LVAR_INT spark2_fxfc
LVAR_INT spark3_fxfc
LVAR_INT spark4_fxfc
LVAR_INT spark5_fxfc
LVAR_INT spark6_fxfc
LVAR_INT spark7_fxfc
LVAR_INT spark8_fxfc

//flags
LVAR_INT finalcut_fcflag
LVAR_INT lastrails_fcflag 
LVAR_INT playercontrol_fcflag
LVAR_INT explodecar_fcflag
LVAR_INT rioters2_fcflag
LVAR_INT copcar3_fcflag
LVAR_INT copcar3swap_fcflag
LVAR_INT copcar4_fcflag
LVAR_INT copcar4swap_fcflag
LVAR_INT skipcredits_fcflag


/////////////////////////////////////////////////

// **************************************** Mission Start ************************************

mission_start_finaleC:

flag_player_on_mission = 1

SCRIPT_NAME finaleC

WAIT 0

LOAD_MISSION_TEXT RIOT4

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY carterc_DM


// **************************************** Declare Variables ************************************

finaleC_fcflag = 11
floorg_fcflag = 0
floor1_fcflag = 0
floor2_fcflag = 0
floor3_fcflag = 0
gang1_fcflag = 0
gang2_fcflag = 0
gang3_fcflag = 0
gang4_fcflag = 0
gang5_fcflag = 0
gang6_fcflag = 0
gang7_fcflag = 0
gang8_fcflag = 0
gang9_fcflag = 0
gang10_fcflag = 0
gang11_fcflag = 0
gang12_fcflag = 0
gang13_fcflag = 0
gang14_fcflag = 0
gang15_fcflag = 0
tenpennycut_fcflag =  0
explosion_fcflag = 0
timerout_fcflag = 0
helptext_fcflag = 0
escapetimer_fc = 450000

//chase
sweethang_fcflag = 0
finaleCchase_fcflag = 0
sweetlegsup_fcflag = 0
locatestage_fcflag = 0 
speedup_fcflag = 0
createtrain_fcflag = 0
copladder_fcflag = 0
coptruck_fcflag = 0
displaygripbar_fcflag = 0
cardensity_fcflag = 0
notincar_fcflag = 0
cardead_fcflag = 0
lostsweet_fcflag = 0
progressaudio_fcflag = 0
handlingudio_fcflag = 0
stagechase_fcflag = 0

//rails 1
//flags
finalerails_fcflag = 0
rails_fcflag = 0
rioters_fcflag = 0
copcar1_fcflag = 0
copcar2_fcflag = 0
mexcar1_fcflag = 0
mexcar2_fcflag = 0
mexcar3_fcflag = 0
ecar1_fcflag = 0
ecar2_fcflag = 0
bike1_fcflag = 0
bike2_fcflag = 0
bike3_fcflag = 0
bike4_fcflag = 0
copcar1swap_fcflag = 0
copcar2swap_fcflag = 0
mexcar1swap_fcflag = 0
mexcar2swap_fcflag = 0
mexcar3swap_fcflag = 0
ecar1swap_fcflag = 0
ecar2swap_fcflag = 0
bike1swap_fcflag = 0
bike2swap_fcflag = 0
bike3swap_fcflag = 0
bike4swap_fcflag = 0
actiontext_fcflag = 0

//rails 2
//flag
finalcut_fcflag = 0
lastrails_fcflag = 0 
playercontrol_fcflag = 0
explodecar_fcflag = 0
rioters2_fcflag = 0
copcar3_fcflag = 0
copcar3swap_fcflag = 0
copcar4_fcflag = 0
copcar4swap_fcflag = 0
skipcredits_fcflag = 0
// **********************************************************************************************


SET_MAX_FIRE_GENERATIONS 0
SET_WANTED_MULTIPLIER 0.0
SWITCH_EMERGENCY_SERVICES OFF


REQUEST_MODEL FIRELA
REQUEST_MODEL LAPD1
REQUEST_MODEL GREENWOO
REQUEST_MODEL FELTZER
REQUEST_MODEL LSV2

REQUEST_MODEL BALLAS1
REQUEST_MODEL MICRO_UZI
REQUEST_MODEL MOLOTOV
REQUEST_MODEL la_fuckcar1
REQUEST_MODEL la_fuckcar2

REQUEST_ANIMATION FINALE
REQUEST_ANIMATION FINALE2
REQUEST_CAR_RECORDING 288
REQUEST_CAR_RECORDING 289
LOAD_SPECIAL_CHARACTER 1 SWEET
LOAD_SPECIAL_CHARACTER 2 TENPEN

REQUEST_CAR_RECORDING 290 //for on rails
REQUEST_CAR_RECORDING 291

LOAD_MISSION_AUDIO 1 SOUND_ROT4_HA	//Tenpenny, you motherfucking piece of shit!

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED FIRELA
	OR NOT HAS_MODEL_LOADED LAPD1
	OR NOT HAS_MODEL_LOADED GREENWOO
	OR NOT HAS_MODEL_LOADED FELTZER
	OR NOT HAS_MODEL_LOADED LSV2
	WAIT 0
ENDWHILE


WHILE NOT HAS_MODEL_LOADED BALLAS1
	OR NOT HAS_MODEL_LOADED MICRO_UZI
	OR NOT HAS_MODEL_LOADED MOLOTOV
	OR NOT HAS_MODEL_LOADED la_fuckcar1
	OR NOT HAS_MODEL_LOADED la_fuckcar2
	WAIT 0
ENDWHILE

WHILE NOT REQUEST_ANIMATION FINALE
	OR NOT REQUEST_ANIMATION FINALE2
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 288
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 289
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
	WAIT 0
ENDWHILE

WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 290
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 291
	WAIT 0
ENDWHILE

SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL PLAYER1 OFF

SET_CHAR_COORDINATES scplayer 2515.771 -1277.474 33.728
SET_CHAR_HEADING scplayer 144.159
TASK_PLAY_ANIM scplayer KO_SKID_BACK PED 1000.0 FALSE FALSE FALSE TRUE -1

WAIT 250

CREATE_CAR FIRELA 2508.649 -1295.866 33.92 firetruck_fc
SET_CAR_HEADING firetruck_fc 0.3186
CONTROL_MOVABLE_VEHICLE_PART firetruck_fc 0.7
SET_CAR_PROOFS firetruck_fc TRUE TRUE TRUE TRUE TRUE 
SET_CAR_CAN_BE_VISIBLY_DAMAGED firetruck_fc FALSE
//
CREATE_CAR GREENWOO 2508.941 -1306.517 33.92 sweet_car
SET_CAR_HEADING sweet_car 165.3186
CHANGE_CAR_COLOUR sweet_car 59 34

CUSTOM_PLATE_FOR_NEXT_CAR FELTZER &_IMY_AK
CREATE_CAR FELTZER 2515.8948 -1269.4077 33.7844 benz_fc
SET_CAR_HEADING benz_fc 359.2572 
SET_CAR_PROOFS benz_fc TRUE TRUE TRUE TRUE TRUE
SET_CAR_HEALTH benz_fc 2000
SET_CAN_BURST_CAR_TYRES benz_fc FALSE
SET_CAR_CAN_BE_VISIBLY_DAMAGED benz_fc FALSE
CHANGE_CAR_COLOUR benz_fc 1 CARCOLOUR_LIGHTBLUEGREY

CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 2506.2175 -1293.0929 33.8438 tenpenny_fc
SET_CHAR_HEADING tenpenny_fc 271.9809
SET_CHAR_PROOFS tenpenny_fc TRUE TRUE TRUE TRUE TRUE 
SET_CHAR_DECISION_MAKER tenpenny_fc carterc_DM

CREATE_CHAR_AS_PASSENGER firetruck_fc PEDTYPE_MISSION1 LAPD1 0 copladder_fc
SET_CHAR_PROOFS copladder_fc TRUE TRUE TRUE TRUE TRUE 
SET_CHAR_DECISION_MAKER copladder_fc carterc_DM


CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 0.0 0.0 0.0 sweet
SET_CHAR_PROOFS sweet TRUE TRUE TRUE TRUE TRUE 
SET_CHAR_HEADING sweet 0.3186
SET_CHAR_DECISION_MAKER sweet carterc_DM
IF NOT IS_CAR_DEAD firetruck_fc
	ATTACH_CHAR_TO_CAR sweet firetruck_fc 0.0 -8.227 -1000.156 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED //0.0 -7.48 -1000.316
ENDIF
SET_PED_DENSITY_MULTIPLIER 0.0
SET_CAR_DENSITY_MULTIPLIER 0.0
CLEAR_AREA 2506.2175 -1293.0929 200.0 200.0 FALSE 
SWITCH_RANDOM_TRAINS OFF
DELETE_ALL_TRAINS
SET_TIME_SCALE 1.0

SUPPRESS_CAR_MODEL FELTZER
SUPPRESS_CAR_MODEL FIRELA

START_SCRIPT_FIRE 2521.3916 -1269.3434 34.1609 0 3 fire10_fc
START_SCRIPT_FIRE 2521.7007 -1270.2931 34.2031 0 2 fire11_fc
START_SCRIPT_FIRE 2523.5796 -1271.2247 34.2918 0 3 fire12_fc
START_SCRIPT_FIRE 2518.5759 -1284.4672 34.0265 0 2 fire13_fc
START_SCRIPT_FIRE 2521.5996 -1270.8846 40.4338 0 3 fire14_fc
START_SCRIPT_FIRE 2521.6619 -1267.6564 43.8955 0 2 fire15_fc
START_SCRIPT_FIRE 2521.6736 -1275.7067 43.7437 0 3 fire16_fc
START_SCRIPT_FIRE 2521.5059 -1275.7814 40.4019 0 2 fire17_fc
START_SCRIPT_FIRE 2522.6797 -1280.3970 39.1158 0 3 fire18_fc
START_SCRIPT_FIRE 2521.6755 -1279.3521 39.1120 0 3 fire19_fc
START_SCRIPT_FIRE 2521.7136 -1278.6392 37.4041 0 2 fire20_fc
SHUT_CHAR_UP scplayer TRUE			
SET_RAILTRACK_RESISTANCE_MULT 0.3

WAIT 500
LOAD_SCENE_IN_DIRECTION 2504.96 -1309.16 34.44 334.0
SET_TIME_OF_DAY 17 00
SET_MAX_WANTED_LEVEL 0
DO_FADE 1000 FADE_IN

finaleC_fcflag = 11	


mission_finaleC_loop:

WAIT 0

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_finalec_passed
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////   Firetruck Cutscene   ////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF NOT IS_CAR_DEAD firetruck_fc
	IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CHAR_DEAD tenpenny_fc
			IF NOT IS_CAR_DEAD benz_fc

				IF finaleC_fcflag = 11				
					SET_FIXED_CAMERA_POSITION 2502.7925 -1296.2798 35.3797 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2503.3779 -1295.4691 35.3873 JUMP_CUT
					
					TASK_ENTER_CAR_AS_DRIVER tenpenny_fc firetruck_fc -1
					TIMERA = 0
					finaleC_fcflag = 12
				ENDIF
				
				IF finaleC_fcflag = 12
					IF TIMERA > 1750
						TASK_PLAY_ANIM scplayer GETUP_FRONT PED 8.0 FALSE FALSE FALSE FALSE -1
						finaleC_fcflag = 13
					ENDIF
				ENDIF

				IF finaleC_fcflag = 13
					IF IS_CHAR_SITTING_IN_CAR tenpenny_fc firetruck_fc
						TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_jump_on FINALE 1000.0 FALSE FALSE FALSE TRUE -1
						LOCK_CAR_DOORS firetruck_fc CARLOCK_LOCKED
						sweethang_fcflag = 1
						TIMERA = 0
						finaleC_fcflag = 14
					ENDIF
				ENDIF

				IF finaleC_fcflag = 14
					SET_FIXED_CAMERA_POSITION 2515.4448 -1299.7101 36.5622 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2514.6670 -1300.3102 36.4758 JUMP_CUT
					finaleC_fcflag = 15
				ENDIF

				IF finaleC_fcflag = 15
					IF TIMERA > 1400
						CAMERA_RESET_NEW_SCRIPTABLES
	            		CAMERA_PERSIST_TRACK TRUE                   
			            CAMERA_PERSIST_POS TRUE                       
						SET_NEAR_CLIP 0.1
			            CAMERA_SET_VECTOR_TRACK 2514.6670 -1300.3102 36.4758 2514.9463 -1282.2650 35.6876 4000 FALSE
			            CAMERA_SET_VECTOR_MOVE 2515.4448 -1299.7101 36.5622 2515.4819 -1282.2650 35.7130 4000 FALSE
						finaleC_fcflag = 16
					ENDIF
				ENDIF

				IF finaleC_fcflag = 16
					IF TIMERA > 2220 //2220
						START_PLAYBACK_RECORDED_CAR firetruck_fc 288
						SET_CHAR_COORDINATES scplayer 2513.49 -1277.77 34.19
						finaleC_fcflag = 17
					ENDIF
				ENDIF
												    
				IF finaleC_fcflag = 17
					IF NOT CAMERA_IS_VECTOR_TRACK_RUNNING
						CAMERA_RESET_NEW_SCRIPTABLES
	            		CAMERA_PERSIST_TRACK TRUE                   
			            CAMERA_PERSIST_POS TRUE                       
			            CAMERA_SET_VECTOR_MOVE 2515.4819 -1282.2650 35.7130 2515.4819 -1283.1001 35.7130 4000 FALSE
			            CAMERA_SET_VECTOR_TRACK 2514.9463 -1282.2650 35.6876 2515.7190 -1282.1357 35.6959 4000 FALSE
						finaleC_fcflag = 18
					ENDIF
				ENDIF

				IF finaleC_fcflag = 18
					IF IS_PLAYBACK_GOING_ON_FOR_CAR firetruck_fc
						IF TIMERA > 4999
							SET_PLAYBACK_SPEED firetruck_fc 0.75
							finaleC_fcflag = 19
						ENDIF
					ENDIF
				ENDIF

				IF finaleC_fcflag = 19
					IF TIMERA > 6000
						TASK_ENTER_CAR_AS_DRIVER scplayer benz_fc -1
						finaleC_fcflag = 20
					ENDIF
				ENDIF

				IF finaleC_fcflag = 20
					IF TIMERA > 10500
						IF IS_CHAR_IN_CAR scplayer benz_fc

							CAMERA_RESET_NEW_SCRIPTABLES
							RESTORE_CAMERA_JUMPCUT
							SWITCH_WIDESCREEN OFF
							SET_PLAYER_CONTROL PLAYER1 ON
							helptext_fcflag = 1
							CLEAR_PRINTS
							PRINT_NOW RM4_41 8000 1 //~s~Follow Tenpenny and make sure ~b~Sweet ~s~is safe stay close to the firetruck and do not lose Tenpenny.
							PRINT RM4_42 5000 1 //~s~Do not attempt to damage the firetruck or you may injure ~b~Sweet~s~.
							SET_PED_DENSITY_MULTIPLIER 0.2
							MARK_CAR_AS_NO_LONGER_NEEDED sweet_car
							MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO

							CREATE_OBJECT la_fuckcar2 2597.36 -1044.735 69.196 gangcar3_fc
							SET_OBJECT_DYNAMIC gangcar3_fc TRUE
							SET_OBJECT_HEADING gangcar3_fc 0.0
							SET_OBJECT_ROTATION gangcar3_fc 0.0 180.0 0.0

							CREATE_OBJECT la_fuckcar1 2598.7 -1050.94 69.23 gangcar4_fc
							SET_OBJECT_HEADING gangcar4_fc 180.0
							SET_OBJECT_DYNAMIC gangcar4_fc TRUE

							CREATE_CHAR PEDTYPE_MISSION1 LSV2 2600.99 -1050.01 69.04 gang11_fc
							SET_CHAR_HEADING gang11_fc 274.892
							SET_CHAR_DECISION_MAKER gang11_fc carterc_DM
							GIVE_WEAPON_TO_CHAR gang11_fc WEAPONTYPE_MOLOTOV 99999
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang11_fc TRUE
							enemy_fc = gang11_fc
							enemytarget_fc = tenpenny_fc
							GOSUB chasekill_fclabel

							CREATE_CHAR PEDTYPE_MISSION1 LSV2 2597.21 -1040.14 69.48 gang12_fc
							SET_CHAR_HEADING gang12_fc 274.892
							SET_CHAR_DECISION_MAKER gang12_fc carterc_DM
							GIVE_WEAPON_TO_CHAR gang12_fc WEAPONTYPE_MICRO_UZI 99999
							SET_CHAR_WEAPON_SKILL gang12_fc WEAPONSKILL_PRO
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang12_fc TRUE
							enemy_fc = gang12_fc
							enemytarget_fc = scplayer
							GOSUB chasekill_fclabel

							CREATE_CHAR PEDTYPE_MISSION1 LSV2 2604.991 -1038.172 69.619 gang13_fc
							SET_CHAR_HEADING gang13_fc 274.892
							SET_CHAR_DECISION_MAKER gang13_fc carterc_DM
							GIVE_WEAPON_TO_CHAR gang13_fc WEAPONTYPE_MOLOTOV 99999
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang13_fc TRUE
							enemy_fc = gang13_fc
							enemytarget_fc = scplayer
							GOSUB chasekill_fclabel
							START_SCRIPT_FIRE 2598.54 -1044.53 68.5 0 3 fire1_fc
							START_SCRIPT_FIRE 2600.99 -1050.01 69.04 0 3 fire2_fc
							START_SCRIPT_FIRE 2599.88 -1052.4 68.06 0 3 fire3_fc

							finaleC_fcflag = 21


						ENDIF
					ENDIF
				ENDIF

				IF finaleC_fcflag = 21
					STOP_PLAYBACK_RECORDED_CAR firetruck_fc
					START_PLAYBACK_RECORDED_CAR firetruck_fc 289
					ADD_BLIP_FOR_CHAR sweet sweet_fcblip
					SET_BLIP_AS_FRIENDLY sweet_fcblip TRUE
					CHANGE_BLIP_DISPLAY sweet_fcblip BLIP_ONLY
					TIMERA = 0
					CLEAR_MISSION_AUDIO 1
					progressaudio_fcflag = 0
					handlingudio_fcflag = 0
					finaleCchase_fcflag = 1

					finaleC_fcflag = 22
				ENDIF


				//get sweet hanging initially
				IF sweethang_fcflag = 1
					 IF IS_CHAR_PLAYING_ANIM sweet FIN_jump_on
						GET_CHAR_ANIM_CURRENT_TIME sweet FIN_jump_on sweetanimtime_fc
							IF sweetanimtime_fc = 1.0
								TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 4.0 FALSE FALSE FALSE TRUE -1
								sweethang_fcflag = 2
							ENDIF
					 ENDIF
				ENDIF

				IF finaleC_fcflag > 13
					IF helptext_fcflag = 0

						GOSUB process_audio_fc

						//play mission audio
						IF progressaudio_fcflag = 0
							IF handlingudio_fcflag = 0
								audio_label_fc = SOUND_ROT4_HA	//Tenpenny, you motherfucking piece of shit!
								$input_text_fc = ROT4_HA	//Tenpenny, you motherfucking piece of shit!
								GOSUB load_audio_fc
							ENDIF
						ENDIF
						IF progressaudio_fcflag = 1
							IF handlingudio_fcflag = 0
								audio_label_fc = SOUND_ROT4_HB	//I ain’t letting you get away with all you’ve done!
								$input_text_fc = ROT4_HB	//I ain’t letting you get away with all you’ve done!
								GOSUB load_audio_fc
							ENDIF
						ENDIF
						IF progressaudio_fcflag = 2
							IF handlingudio_fcflag = 0
								audio_label_fc = SOUND_ROT4_DB	//Sweet, no!
								$input_text_fc = ROT4_DB	//Sweet, no!
								GOSUB load_audio_fc
							ENDIF
						ENDIF

					ENDIF
				ENDIF

			ENDIF
		ENDIF
	ENDIF
ENDIF

//car dead
IF finaleCchase_fcflag = 1
OR finaleCchase_fcflag = 2
	IF IS_CAR_DEAD benz_fc
		cardead_fcflag = 1
		GOTO mission_finaleC_failed
	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////   Firetruck Chase   ///////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF finaleCchase_fcflag = 1
	IF NOT IS_CAR_DEAD firetruck_fc
		IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CHAR_DEAD tenpenny_fc
				IF NOT IS_CAR_DEAD benz_fc
					
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D sweet benz_fc 125.0 125.0 FALSE
						IF NOT IS_CHAR_ON_SCREEN sweet
							lostsweet_fcflag = 1
							GOTO mission_finaleC_failed
							PRINT_NOW RM4_50 5000 1 //~r~You lost Sweet! Next time stay closer!
						ENDIF
					ENDIF

					//not in car check
					IF notincar_fcflag = 0
						IF NOT IS_CHAR_SITTING_IN_CAR scplayer benz_fc
							REMOVE_BLIP sweet_fcblip
							ADD_BLIP_FOR_CAR benz_fc sweet_fcblip
							SET_BLIP_AS_FRIENDLY sweet_fcblip TRUE
							PRINT_NOW RM4_48 5000 1 //~s~Get back in the ~b~car~s~!
							notincar_fcflag = 1
						ENDIF
					ENDIF
					IF notincar_fcflag = 1
						IF IS_CHAR_SITTING_IN_CAR scplayer benz_fc
							REMOVE_BLIP sweet_fcblip
							ADD_BLIP_FOR_CHAR sweet sweet_fcblip
							SET_BLIP_AS_FRIENDLY sweet_fcblip TRUE
							CHANGE_BLIP_DISPLAY sweet_fcblip BLIP_ONLY
							IF displaygripbar_fcflag > 0
								CHANGE_BLIP_DISPLAY sweet_fcblip BOTH
							ENDIF
							PRINT_NOW RM4_49 5000 1 //~s~Chase the firetruck!
							notincar_fcflag = 0
						ENDIF
					ENDIF

					//speed up slow down truck
					IF NOT IS_CAR_DEAD firetruck_fc
						IF NOT IS_CHAR_DEAD sweet
							IF IS_PLAYBACK_GOING_ON_FOR_CAR firetruck_fc

								SWITCH speedup_fcflag

								    CASE 0 // car recording at 1.0
								    	IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D sweet scplayer 60.0 60.0 FALSE
											SET_PLAYBACK_SPEED firetruck_fc 0.5
											speedup_fcflag = 1
										ELSE
											IF LOCATE_CHAR_ANY_MEANS_CHAR_2D sweet scplayer 15.0 15.0 FALSE
												IF NOT locatestage_fcflag = 5 //highway
												OR NOT locatestage_fcflag = 1 //bank where rioters are
													SET_PLAYBACK_SPEED firetruck_fc 1.30 //1.15
									                speedup_fcflag = 2
												ENDIF
											ENDIF
										ENDIF
									BREAK

									CASE 1
								        IF LOCATE_CHAR_ANY_MEANS_CHAR_2D sweet scplayer 55.0 55.0 FALSE
								            SET_PLAYBACK_SPEED firetruck_fc 1.0
								            speedup_fcflag = 0
								        ENDIF
									BREAK

									CASE 2
								        IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D sweet scplayer 20.0 20.0 FALSE
								            SET_PLAYBACK_SPEED firetruck_fc 1.0
								            speedup_fcflag = 0
								        ENDIF
									BREAK

								ENDSWITCH

							ENDIF
						ENDIF
					ENDIF

					//guy climbing out
					IF NOT IS_CHAR_DEAD copladder_fc

						IF copladder_fcflag = 1
							GET_CAR_COORDINATES firetruck_fc player_x player_y player_z
							player_z = player_z - 15.0
							WARP_CHAR_FROM_CAR_TO_COORD copladder_fc player_x player_y player_z
							ATTACH_CHAR_TO_CAR copladder_fc firetruck_fc 0.0 0.656 2.408 FACING_FORWARD 0.0 WEAPONTYPE_UNARMED
						   	copladder_fcflag = 2
						ENDIF
						IF copladder_fcflag = 2
							OPEN_CAR_DOOR firetruck_fc FRONT_RIGHT_DOOR
							TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_Cop1_ClimbOut FINALE2 1000.0 FALSE FALSE FALSE TRUE -1	//FIN_copladder_fc_ClimbOut2
							copladder_fcflag = 3
						ENDIF
						IF copladder_fcflag = 3
							IF IS_CHAR_PLAYING_ANIM copladder_fc FIN_Cop1_ClimbOut
								GET_CHAR_ANIM_CURRENT_TIME copladder_fc FIN_Cop1_ClimbOut copladder_fcanimtime
									IF copladder_fcanimtime = 1.0
										DETACH_CHAR_FROM_CAR copladder_fc
										ATTACH_CHAR_TO_CAR copladder_fc firetruck_fc -0.014 -7.40 -999.253 FACING_BACK 0.0 WEAPONTYPE_UNARMED
										TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_Cop1_ClimbOut2 FINALE 1000.0 FALSE FALSE FALSE TRUE -1
										TIMERA = 0
										displaygripbar_fcflag = 1
										copladder_fcflag = 4
									ENDIF		
							ENDIF			
						ENDIF
						//stomp cop
						IF copladder_fcflag = 4
							IF IS_CHAR_PLAYING_ANIM copladder_fc FIN_Cop1_ClimbOut2
								GET_CHAR_ANIM_CURRENT_TIME copladder_fc FIN_Cop1_ClimbOut2 copladder_fcanimtime
									IF copladder_fcanimtime = 1.0
										TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_COP1_STOMP FINALE 8.0 FALSE FALSE FALSE TRUE -1
										TIMERB = 0
										PRINT_NOW RM4_43 7000 1 //~s~The cop is stamping on ~s~Sweet's fingers get your car close to him in case he loses his grip.
										copladder_fcflag = 5
									ENDIF		
							ENDIF
						ENDIF
						//slip reaction	sweet
						IF copladder_fcflag = 5
							IF TIMERB > 499
								TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_SLIP FINALE 8.0 FALSE FALSE FALSE TRUE -1
								copladder_fcflag = 6
							ENDIF
						ENDIF
						//loop cop
						IF copladder_fcflag = 6
							IF IS_CHAR_PLAYING_ANIM copladder_fc FIN_COP1_STOMP
								GET_CHAR_ANIM_CURRENT_TIME copladder_fc FIN_COP1_STOMP copladder_fcanimtime
									IF copladder_fcanimtime = 1.0
										TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_COP1_LOOP FINALE 8.0 FALSE FALSE FALSE TRUE -1
										copladder_fcflag = 7
									ENDIF
							ENDIF
						ENDIF
						//loop sweet
						IF copladder_fcflag = 7
							IF IS_CHAR_PLAYING_ANIM sweet FIN_HANG_SLIP
								GET_CHAR_ANIM_CURRENT_TIME sweet FIN_HANG_SLIP sweetanimtime_fc
								IF sweetanimtime_fc = 1.0
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 8.0 TRUE FALSE FALSE TRUE -1
									TIMERB = 0
									copladder_fcflag = 8
								ENDIF
							ENDIF
						ENDIF
						//stomp cop
						IF copladder_fcflag = 8
							IF TIMERB > 750
								IF IS_CHAR_PLAYING_ANIM copladder_fc FIN_COP1_LOOP
									GET_CHAR_ANIM_CURRENT_TIME copladder_fc FIN_COP1_LOOP copladder_fcanimtime
										IF copladder_fcanimtime = 1.0
											TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_COP1_STOMP FINALE 8.0 FALSE FALSE FALSE TRUE -1
											TIMERB = 0
											copladder_fcflag = 5
										ENDIF		
								ENDIF
							ENDIF
						ENDIF

						//grip bar
						IF displaygripbar_fcflag = 1
							IF TIMERA > 8999
								IF DOES_BLIP_EXIST sweet_fcblip	
									CHANGE_BLIP_DISPLAY sweet_fcblip BOTH
								ENDIF
								gripbar_fc = 15 //(12 + 3)
								fgripbardisplay_fc =# gripbar_fc
								fgripbardisplay_fc *= 100.0
								fgripbardisplay_fc /= 15.0
								igripbardisplay_fc =# fgripbardisplay_fc
								DISPLAY_ONSCREEN_COUNTER_WITH_STRING igripbardisplay_fc COUNTER_DISPLAY_BAR RM4_47
								TIMERA = 0
								displaygripbar_fcflag = 2
							ENDIF
						ENDIF
						IF displaygripbar_fcflag = 2	//counting down for three seconds after which player can catch
							IF TIMERA > 999
								gripbar_fc = gripbar_fc - 1
								TIMERA = 0
								displaygripbar_fcflag = 3
							ENDIF
						ENDIF
						IF displaygripbar_fcflag = 3	
							IF TIMERA > 999
								gripbar_fc = gripbar_fc - 1
								TIMERA = 0
								displaygripbar_fcflag = 4
							ENDIF
						ENDIF
						IF displaygripbar_fcflag = 4	
							IF TIMERA > 999
								gripbar_fc = gripbar_fc - 1
								TIMERA = 0
								SET_CAR_DENSITY_MULTIPLIER 0.0
								displaygripbar_fcflag = 5
							ENDIF
						ENDIF
						IF displaygripbar_fcflag = 5	//player can now catch

							IF TIMERA > 999
								gripbar_fc = gripbar_fc - 1
								stagechase_fcflag = 4
								TIMERA = 0
							
							ENDIF

							//player did not catch
							IF gripbar_fc <= 0
								gripbar_fc = 0
								copladder_fcflag = 10
								TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_COP1_LOOP FINALE 8.0 TRUE FALSE FALSE FALSE -1
								DETACH_CHAR_FROM_CAR sweet
								SET_CHAR_COLLISION sweet TRUE
								TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_Land_Die FINALE 4.0 FALSE TRUE TRUE TRUE -1
								TIMERA = 0
								displaygripbar_fcflag = 6
							ENDIF

							//player catches
							IF gripbar_fc > 0
								GET_CHAR_COORDINATES sweet player_x player_y player_z
								player_z = player_z - 1.0

								IF LOCATE_CHAR_ANY_MEANS_3D scplayer player_x player_y player_z 3.0 3.0 4.0 FALSE
									CLEAR_ONSCREEN_COUNTER gripbar_fc

									number_of_frames_fc = 4.0
									DETACH_CHAR_FROM_CAR sweet
									GET_CHAR_COORDINATES sweet sweetx_fc sweety_fc sweetz_fc
									SET_CHAR_COLLISION sweet FALSE
									sweetz_fc = sweetz_fc - 1.0
									CLEAR_CHAR_TASKS_IMMEDIATELY sweet
									SET_CHAR_COORDINATES sweet sweetx_fc sweety_fc sweetz_fc
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_LET_GO FINALE 1000.0 FALSE FALSE FALSE TRUE -1


									WAIT 0

									IF NOT IS_CAR_DEAD benz_fc 
										IF NOT IS_CHAR_DEAD sweet
											IF NOT IS_CAR_DEAD firetruck_fc

												GET_CHAR_COORDINATES sweet sweetx_fc sweety_fc sweetz_fc
												GET_OFFSET_FROM_CAR_IN_WORLD_COORDS benz_fc 0.0 1.5 0.5 WorldX_fc WorldY_fc WorldZ_fc

												diffx_fc = sweetx_fc - WorldX_fc
												diffy_fc = sweety_fc - Worldy_fc
												diffz_fc = sweetz_fc - Worldz_fc

												stepx_fc = diffx_fc / number_of_frames_fc
												stepy_fc = diffy_fc / number_of_frames_fc
												stepz_fc = diffz_fc / number_of_frames_fc

												CLEAR_MISSION_AUDIO 1
												CLEAR_PRINTS
												handlingudio_fcflag = 0
												progressaudio_fcflag = 0

												finaleCchase_fcflag = 2

											ENDIF
										ENDIF
									ENDIF

								ENDIF
							ENDIF

						ENDIF

						//get bar values spot on
						IF displaygripbar_fcflag > 1
							fgripbardisplay_fc =# gripbar_fc
							fgripbardisplay_fc *= 100.0
							fgripbardisplay_fc /= 15.0
							igripbardisplay_fc =# fgripbardisplay_fc
						ENDIF

					ENDIF

					IF displaygripbar_fcflag = 6 //next bit if caught or mission failed if dropped
						IF TIMERA > 2000
							displaygripbar_fcflag = 7
							CLEAR_MISSION_AUDIO 1
							CLEAR_PRINTS
							GOTO mission_finaleC_failed
						ENDIF
					ENDIF


					//car density 
					IF cardensity_fcflag = 1	//1st alleyway
						IF LOCATE_CAR_2D firetruck_fc 2003.29 -1097.0 20.0 20.0 FALSE
							SET_CAR_DENSITY_MULTIPLIER 1.0
							cardensity_fcflag = 2
						ENDIF
					ENDIF
					IF cardensity_fcflag = 1	//just before bridge
						IF LOCATE_CAR_2D firetruck_fc 2112.84 -1406.01 20.0 20.0 FALSE
							SET_CAR_DENSITY_MULTIPLIER 0.0
							cardensity_fcflag = 2
						ENDIF
					ENDIF
					IF cardensity_fcflag = 2	//end of the bridge
						IF LOCATE_CAR_2D firetruck_fc 2112.51 -1631.72 20.0 20.0 FALSE
							SET_CAR_DENSITY_MULTIPLIER 0.9
							cardensity_fcflag = 3
						ENDIF
					ENDIF

					//creates train
					IF createtrain_fcflag = 1
						IF HAS_MODEL_LOADED STREAK 
							CREATE_MISSION_TRAIN 15 2135.04 -1953.25 12.84 FALSE train_fc
							SET_TRAIN_SPEED train_fc 30.0
							SET_TRAIN_CRUISE_SPEED train_fc 12.5
							SET_CAR_PROOFS train_fc TRUE TRUE TRUE TRUE TRUE
							createtrain_fcflag = 2
						ELSE
							MARK_MODEL_AS_NO_LONGER_NEEDED STREAK 
							createtrain_fcflag = 2
						ENDIF
					ENDIF
					IF createtrain_fcflag = 2
						IF NOT IS_CAR_DEAD train_fc
							IF LOCATE_CAR_2D train_fc 2226.64 -1603.62 15.0 15.0 FALSE 
								MARK_MISSION_TRAINS_AS_NO_LONGER_NEEDED
								MARK_MODEL_AS_NO_LONGER_NEEDED STREAK 
								createtrain_fcflag = 3							
							ENDIF
						ELSE
							MARK_MISSION_TRAINS_AS_NO_LONGER_NEEDED
							MARK_MODEL_AS_NO_LONGER_NEEDED STREAK 
							createtrain_fcflag = 3
						ENDIF
					ENDIF
					IF createtrain_fcflag = 3
						DELETE_ALL_TRAINS
						createtrain_fcflag = 4
					ENDIF

					//tells sweet when to put his legs up/down
					//near countryside fence
					IF locatestage_fcflag = 0
						IF sweetlegsup_fcflag = 0
							IF LOCATE_CAR_2D firetruck_fc 2339.11 -1038.88 14.0 14.0 FALSE
								stagechase_fcflag = 1
								REMOVE_ALL_SCRIPT_FIRES
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar3_fc
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar4_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fc

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2246.362 -1059.161 61.2275 gang1_fc
								SET_CHAR_HEADING gang1_fc 255.0
								SET_CHAR_DECISION_MAKER gang1_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang1_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang1_fc TRUE
								enemy_fc = gang1_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2212.216 -1001.041 67.22 gang4_fc
								SET_CHAR_HEADING gang4_fc 183.4
								SET_CHAR_DECISION_MAKER gang4_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang4_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang4_fc TRUE
								enemy_fc = gang4_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2197.558 -1000.418 67.367 gang5_fc
								SET_CHAR_HEADING gang5_fc 209.7
								SET_CHAR_DECISION_MAKER gang5_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang5_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang5_fc TRUE
								enemy_fc = gang5_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2188.512 -1020.8 70.33 gang6_fc
								SET_CHAR_HEADING gang6_fc 287.39
								SET_CHAR_DECISION_MAKER gang6_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang6_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang6_fc TRUE
								SET_CHAR_ACCURACY gang6_fc 90
								enemy_fc = gang6_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel

								CREATE_OBJECT la_fuckcar2 2171.57 -1008.76 62.56 gangcar1_fc
								SET_OBJECT_DYNAMIC gangcar1_fc TRUE
								SET_OBJECT_HEADING gangcar1_fc 168.0
								SET_OBJECT_ROTATION gangcar1_fc 0.0 90.0 0.0
								START_SCRIPT_FIRE 2172.57 -1008.76 61.56 0 3 fire1_fc
								START_SCRIPT_FIRE 2171.57 -1007.76 62.56 0 3 fire2_fc

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2172.91 -1001.84 62.83 gang7_fc
								SET_CHAR_HEADING gang7_fc 258.18
								SET_CHAR_DECISION_MAKER gang7_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang7_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang7_fc TRUE
								enemy_fc = gang7_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								sweetlegsup_fcflag = 1
							ENDIF
						ENDIF
						IF sweetlegsup_fcflag = 1
							IF LOCATE_CAR_2D firetruck_fc 2252.01 -1046.41 14.0 14.0 FALSE	
								IF IS_CHAR_PLAYING_ANIM sweet FIN_LegsUp_Loop
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 1.5 TRUE FALSE FALSE TRUE -1
								ENDIF
								
								IF NOT IS_POINT_ON_SCREEN 2600.99 -1050.01 69.04 10.0
									DELETE_OBJECT gangcar3_fc
									DELETE_OBJECT gangcar4_fc
									DELETE_CHAR gang11_fc
									DELETE_CHAR gang12_fc
									DELETE_CHAR gang13_fc
								ENDIF

								CREATE_OBJECT la_fuckcar2 2033.39 -938.0 39.99 gangcar2_fc
								SET_OBJECT_HEADING gangcar2_fc 24.8
								SET_OBJECT_DYNAMIC gangcar2_fc TRUE
								SET_OBJECT_ROTATION gangcar2_fc 0.0 0.0 0.0
								START_SCRIPT_FIRE 2033.39 -938.0 38.99 0 3 fire3_fc
								START_SCRIPT_FIRE 2032.39 -937.0 38.99 0 3 fire4_fc

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2035.09 -979.33 40.9 gang8_fc
								SET_CHAR_HEADING gang8_fc 263.9
								SET_CHAR_DECISION_MAKER gang8_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang8_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang8_fc TRUE
								enemy_fc = gang8_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2053.81 -983.87 45.33 gang9_fc
								SET_CHAR_HEADING gang9_fc 287.5
								SET_CHAR_DECISION_MAKER gang9_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang9_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang9_fc TRUE
								enemy_fc = gang9_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2089.93 -971.49 54.556 gang10_fc
								SET_CHAR_HEADING gang10_fc 219.88
								SET_CHAR_DECISION_MAKER gang10_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang10_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang10_fc TRUE
								enemy_fc = gang10_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 2026.056 -971.93 41.5 gang11_fc
								SET_CHAR_HEADING gang11_fc 219.88
								SET_CHAR_DECISION_MAKER gang11_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang11_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang11_fc TRUE
								enemy_fc = gang11_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 1989.918 -1017.65 34.05 gang12_fc
								SET_CHAR_HEADING gang12_fc 317.6
								SET_CHAR_DECISION_MAKER gang12_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang12_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang12_fc TRUE
								enemy_fc = gang12_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 1999.8 -1018.65 35.33 gang13_fc
								SET_CHAR_HEADING gang13_fc 317.6
								SET_CHAR_DECISION_MAKER gang13_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang13_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang13_fc TRUE
								enemy_fc = gang13_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								//roadblock near alleyway
								CREATE_CHAR PEDTYPE_MISSION1 LSV2 1970.674 -1088.046 24.163 gang14_fc
								SET_CHAR_HEADING gang14_fc 345.24
								SET_CHAR_DECISION_MAKER gang14_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang14_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang14_fc TRUE
								enemy_fc = gang14_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel

								CREATE_CHAR PEDTYPE_MISSION1 LSV2 1977.545 -1096.913 24.35 gang15_fc
								SET_CHAR_HEADING gang15_fc 24.331
								SET_CHAR_DECISION_MAKER gang15_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang15_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang15_fc TRUE
								enemy_fc = gang15_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								CREATE_OBJECT la_fuckcar1 1975.31 -1099.27 24.118 gangcar3_fc
								SET_OBJECT_DYNAMIC gangcar3_fc TRUE
								SET_OBJECT_HEADING gangcar3_fc 270.894
								SET_OBJECT_ROTATION gangcar3_fc 0.0 180.0 0.0
								REMOVE_SCRIPT_FIRE fire1_fc
								REMOVE_SCRIPT_FIRE fire2_fc
								START_SCRIPT_FIRE 1975.31 -1099.27 24.118 0 3 fire1_fc
								START_SCRIPT_FIRE 1977.0 -1098.0 24.0 0 3 fire2_fc
								sweetlegsup_fcflag = 0
								cardensity_fcflag = 1
								locatestage_fcflag = 1
							ENDIF
						ENDIF
					ENDIF
					//alleyway
					IF locatestage_fcflag = 1
						IF sweetlegsup_fcflag = 0
							IF LOCATE_CAR_2D firetruck_fc 2073.51 -1260.18 14.0 14.0 FALSE
								stagechase_fcflag = 2
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar1_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fc

								//roadblock along the bridge
								CREATE_OBJECT la_fuckcar2 2117.46 -1490.22 22.6 gangcar5_fc
								SET_OBJECT_DYNAMIC gangcar5_fc TRUE
								SET_OBJECT_HEADING gangcar5_fc 276.0
								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2108.6 -1491.5 23.85 gang11_fc
								SET_CHAR_HEADING gang11_fc 340.0
								SET_CHAR_DECISION_MAKER gang11_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang11_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang11_fc TRUE
								enemy_fc = gang11_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel
								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2116.48 -1492.219 22.85 gang12_fc
								SET_CHAR_HEADING gang12_fc 340.0
								SET_CHAR_DECISION_MAKER gang12_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang12_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang12_fc TRUE
								enemy_fc = gang12_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel
								sweetlegsup_fcflag = 1
							ENDIF
						ENDIF
						IF sweetlegsup_fcflag = 1
							IF LOCATE_CAR_2D firetruck_fc 2268.94 -1260.26 14.0 14.0 FALSE	
								IF IS_CHAR_PLAYING_ANIM sweet FIN_LegsUp_Loop
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 1.5 TRUE FALSE FALSE TRUE -1
								ENDIF
								REMOVE_ALL_SCRIPT_FIRES
								MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar2_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang8_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang9_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang14_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang15_fc
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar3_fc
								DELETE_OBJECT gangcar1_fc
								DELETE_CHAR gang1_fc
								DELETE_CHAR gang4_fc
								DELETE_CHAR gang5_fc
								DELETE_CHAR gang6_fc
								DELETE_CHAR gang7_fc
								DELETE_OBJECT gangcar2_fc
								DELETE_CHAR gang8_fc
								REQUEST_MODEL STREAK

								//roadblock along the bridge
								CREATE_OBJECT la_fuckcar1 2109.492 -1531.4 22.82 gangcar1_fc
								SET_OBJECT_HEADING gangcar1_fc 276.274
								SET_OBJECT_DYNAMIC gangcar1_fc TRUE
								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2110.39 -1529.04 22.928 gang1_fc
								SET_CHAR_HEADING gang1_fc 10.19
								SET_CHAR_DECISION_MAKER gang1_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang1_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang1_fc TRUE
								enemy_fc = gang1_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel
								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2118.376 -1531.8 23.189 gang2_fc
								SET_CHAR_HEADING gang2_fc 340.0
								SET_CHAR_DECISION_MAKER gang2_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang2_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang2_fc TRUE
								enemy_fc = gang2_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel
								START_SCRIPT_FIRE 2116.7 -1530.846 22.99 0 3 fire1_fc
								START_SCRIPT_FIRE 2115.5 -1530.282 22.97 0 2 fire2_fc
								START_SCRIPT_FIRE 2114.06 -1529.88 22.96 0 3 fire3_fc
								START_SCRIPT_FIRE 2112.3 -1529.92 22.9 0 2 fire4_fc

								//roadblock along the bridge
								CREATE_OBJECT la_fuckcar2 2115.75 -1620.69 22.11 gangcar4_fc
								SET_OBJECT_DYNAMIC gangcar4_fc TRUE
								SET_OBJECT_HEADING gangcar4_fc 95.114
								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2111.57 -1585.135 25.1 gang3_fc
								SET_CHAR_HEADING gang3_fc 12.0
								SET_CHAR_DECISION_MAKER gang3_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang3_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang3_fc TRUE
								enemy_fc = gang3_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel
								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2104.487 -1586.327 25.07 gang4_fc
								SET_CHAR_HEADING gang4_fc 306.26
								SET_CHAR_DECISION_MAKER gang4_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang4_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang4_fc TRUE
								enemy_fc = gang4_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel
								START_SCRIPT_FIRE 2108.28 -1637.23 18.3 0 3 fire5_fc
								START_SCRIPT_FIRE 2110.65 -1637.93 18.36 0 2 fire6_fc
								START_SCRIPT_FIRE 2112.18 -1637.2 18.52 0 1 fire7_fc
								START_SCRIPT_FIRE 2115.36 -1637.43 18.47 0 2 fire8_fc
								START_SCRIPT_FIRE 2117.39 -1636.75 18.62 0 3 fire9_fc

								START_SCRIPT_FIRE 2107.91 -1586.498 24.8 0 3 fire10_fc
								START_SCRIPT_FIRE 2110.28 -1586.29 24.89 0 2 fire11_fc
								START_SCRIPT_FIRE 2112.755 -1586.081 24.8 0 2 fire12_fc

								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2094.0 -1763.0 12.5 gang5_fc
								SET_CHAR_HEADING gang5_fc 322.0
								SET_CHAR_DECISION_MAKER gang5_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang5_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang5_fc TRUE
								enemy_fc = gang5_fc
								enemytarget_fc = scplayer
								GOSUB chasekill_fclabel
								CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2088.0 -1761.0 12.5 gang6_fc
								SET_CHAR_HEADING gang6_fc 306.26
								SET_CHAR_DECISION_MAKER gang6_fc carterc_DM
								GIVE_WEAPON_TO_CHAR gang6_fc WEAPONTYPE_MOLOTOV 99999
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang6_fc TRUE
								enemy_fc = gang6_fc
								enemytarget_fc = tenpenny_fc
								GOSUB chasekill_fclabel

								sweetlegsup_fcflag = 0
								locatestage_fcflag = 2
							ENDIF
						ENDIF
					ENDIF
					//beginning of the train track
					IF locatestage_fcflag = 2
						IF sweetlegsup_fcflag = 0
							IF LOCATE_CAR_2D firetruck_fc 2203.69 -1724.39 14.0 14.0 FALSE
								createtrain_fcflag = 1
								DELETE_CHAR gang8_fc
								DELETE_CHAR gang9_fc
								DELETE_CHAR gang10_fc
								DELETE_CHAR gang11_fc
								DELETE_CHAR gang13_fc
								DELETE_CHAR gang14_fc
								DELETE_CHAR gang15_fc
								DELETE_OBJECT gangcar3_fc
								MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
								MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
								MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar1
								MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar2
								MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
								SET_CAR_DENSITY_MULTIPLIER 1.0
								sweetlegsup_fcflag = 1
							ENDIF
						ENDIF
						IF sweetlegsup_fcflag = 1
							IF LOCATE_CAR_2D firetruck_fc 2202.32 -1890.45 14.0 14.0 FALSE	
								stagechase_fcflag = 3
								IF IS_CHAR_PLAYING_ANIM sweet FIN_LegsUp_Loop
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 1.5 TRUE FALSE FALSE TRUE -1
								ENDIF
								REMOVE_ALL_SCRIPT_FIRES
								sweetlegsup_fcflag = 0
								locatestage_fcflag = 3
							ENDIF
						ENDIF
					ENDIF
					//Turning on the train track
					IF locatestage_fcflag = 3
						IF sweetlegsup_fcflag = 0
							IF LOCATE_CAR_2D firetruck_fc 2115.45 -1954.4 14.0 14.0 FALSE
								sweetlegsup_fcflag = 1
							ENDIF
						ENDIF
						IF sweetlegsup_fcflag = 1
							IF LOCATE_CAR_2D firetruck_fc 2179.19 -2017.63 14.0 14.0 FALSE	
								IF IS_CHAR_PLAYING_ANIM sweet FIN_LegsUp_Loop
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 1.5 TRUE FALSE FALSE TRUE -1
								ENDIF
								REMOVE_ALL_SCRIPT_FIRES
								//roadblock along the bridge
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar1_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fc
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar5_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fc
								MARK_OBJECT_AS_NO_LONGER_NEEDED gangcar4_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fc
								sweetlegsup_fcflag = 0
								locatestage_fcflag = 4
							ENDIF
						ENDIF
					ENDIF
					//End of the train track
					IF locatestage_fcflag = 4
						IF sweetlegsup_fcflag = 0
							IF LOCATE_CAR_2D firetruck_fc 2318.03 -2165.94 14.0 14.0 FALSE
								DELETE_OBJECT gangcar1_fc
								DELETE_CHAR gang1_fc
								DELETE_CHAR gang2_fc
								DELETE_OBJECT gangcar5_fc
								DELETE_CHAR gang11_fc
								DELETE_CHAR gang12_fc
								DELETE_OBJECT gangcar4_fc
								DELETE_CHAR gang3_fc
								DELETE_CHAR gang4_fc
								DELETE_CHAR gang5_fc
								DELETE_CHAR gang6_fc
								sweetlegsup_fcflag = 1
							ENDIF
						ENDIF
						IF sweetlegsup_fcflag = 1
							IF LOCATE_CAR_2D firetruck_fc 2452.6 -2170.27 14.0 14.0 FALSE	
								IF IS_CHAR_PLAYING_ANIM sweet FIN_LegsUp_Loop
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 1.5 TRUE FALSE FALSE TRUE -1
								ENDIF
								copladder_fcflag = 1
								sweetlegsup_fcflag = 0
								locatestage_fcflag = 5
							ENDIF
						ENDIF
					ENDIF


					//get sweet hanging initially
					IF sweethang_fcflag = 1
						 IF IS_CHAR_PLAYING_ANIM sweet FIN_jump_on
							GET_CHAR_ANIM_CURRENT_TIME sweet FIN_jump_on sweetanimtime_fc
								IF sweetanimtime_fc = 1.0
									TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 4.0 TRUE FALSE FALSE TRUE -1
									sweethang_fcflag = 2
								ENDIF
						 ENDIF
					ENDIF
					//swinging animations
					IF sweethang_fcflag > 1
						//every frame
						GET_CAR_HEADING firetruck_fc carheading_fc
						GET_DOOR_ANGLE_RATIO firetruck_fc EXTRA_DOOR sweet_thisheading_fc
						//calcs
						sweet_thisheading_fc -= 0.5
						sweet_thisheading_fc *= 2.0
						// 10 degrees
						sweet_thisheading_fc *= 10.0
						sweet_thisheading_fc += carheading_fc
						// set char heading and get roll
						headingdiff_fc = sweet_thisheading_fc - sweet_lastheading_fc
						IF headingdiff_fc < -180.0
							headingdiff_fc += 360.0
						ENDIF
						IF headingdiff_fc > 180.0
							headingdiff_fc += -360.0
						ENDIF
						sweet_lastheading_fc = sweet_thisheading_fc					


						IF copladder_fcflag = 0
						
							IF sweetlegsup_fcflag = 0
								IF headingdiff_fc < -1.5 //-2.0
									IF NOT sweethang_fcflag = 4
										TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_L FINALE 1.5 FALSE FALSE FALSE TRUE -1
										sweethang_fcflag = 4
									ENDIF
								ELSE
									IF headingdiff_fc > 1.5 //2.0
										IF NOT sweethang_fcflag = 3
											TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_R FINALE 1.5 FALSE FALSE FALSE TRUE -1
											sweethang_fcflag = 3
										ENDIF
									ELSE
										IF NOT sweethang_fcflag = 2
											TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_HANG_LOOP FINALE 2.5 TRUE FALSE FALSE TRUE -1
											sweethang_fcflag = 2
										ENDIF
									ENDIF
								ENDIF
							ELSE
								IF headingdiff_fc < -1.5 //-2.0
									IF NOT sweethang_fcflag = 4
										TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_LegsUp_L FINALE 1.5 FALSE FALSE FALSE TRUE -1
										sweethang_fcflag = 4
									ENDIF
								ELSE
									IF headingdiff_fc > 1.5 //2.0
										IF NOT sweethang_fcflag = 3
											TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_LegsUp_R FINALE 1.5 FALSE FALSE FALSE TRUE -1
											sweethang_fcflag = 3
										ENDIF
									ELSE
										IF NOT sweethang_fcflag = 2
											TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_LegsUp_Loop FINALE 2.5 TRUE FALSE FALSE TRUE -1
											sweethang_fcflag = 2
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					ENDIF

					IF finaleCchase_fcflag < 2
						//audio for chase
						GOSUB process_audio_fc

						//play mission audio
						IF progressaudio_fcflag = 0
							IF handlingudio_fcflag = 0
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 15.0 15.0 FALSE
									audio_label_fc = SOUND_ROT4_EA	///Hang on Sweet!
									$input_text_fc = ROT4_EA	///Hang on Sweet!
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF
						IF stagechase_fcflag = 1
							IF handlingudio_fcflag = 0
								IF progressaudio_fcflag < 1
									progressaudio_fcflag = 1
								ENDIF
								IF progressaudio_fcflag = 1
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
										audio_label_fc = SOUND_ROT4_HC //I’m gonna piss on your corpse, Tenpenny!
										$input_text_fc = ROT4_HC //I’m gonna piss on your corpse, Tenpenny!
										GOSUB load_audio_fc
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						IF progressaudio_fcflag = 2
							IF handlingudio_fcflag = 0
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
									audio_label_fc = SOUND_ROT4_FA	//I ain't loosin' this fool!
									$input_text_fc = ROT4_FA	//I ain't loosin' this fool!									
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF


						IF progressaudio_fcflag = 3
							IF handlingudio_fcflag = 0
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
									audio_label_fc = SOUND_ROT4_MB //Oh shiiiiit!
									$input_text_fc = ROT4_MB //Oh shiiiiit!									
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF


						IF handlingudio_fcflag = 0
							IF stagechase_fcflag = 2
								IF progressaudio_fcflag < 4
									progressaudio_fcflag = 4
								ENDIF
								IF progressaudio_fcflag = 4
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
										audio_label_fc = SOUND_ROT4_KJ //CJ, do something!
										$input_text_fc = ROT4_KJ //CJ, do something!
										GOSUB load_audio_fc
									ENDIF
								ENDIF
							ENDIF
						ENDIF

						IF progressaudio_fcflag = 5
							IF handlingudio_fcflag = 0
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
									audio_label_fc = SOUND_ROT4_EB //Just keep hanging on, bro!
									$input_text_fc = ROT4_EB //Just keep hanging on, bro!
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF

						IF handlingudio_fcflag = 0
							IF stagechase_fcflag = 3
								IF progressaudio_fcflag < 6
									progressaudio_fcflag = 6
								ENDIF
								IF progressaudio_fcflag = 6
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
										audio_label_fc = SOUND_ROT4_GB //Oh man, that was  a close call!
										$input_text_fc = ROT4_GB //Oh man, that was  a close call!
										GOSUB load_audio_fc
									ENDIF
								ENDIF
							 ENDIF
						ENDIF

						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 7
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
									audio_label_fc = SOUND_ROT4_FB //I ain’t letting this bastard go!
									$input_text_fc = ROT4_FB //I ain’t letting this bastard go!
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF

						IF handlingudio_fcflag = 0
							IF stagechase_fcflag = 4
								IF progressaudio_fcflag < 8
									progressaudio_fcflag = 8
								ENDIF
								IF progressaudio_fcflag = 8
									IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
										audio_label_fc = SOUND_ROT4_KB	//Let go, you dumb bastard!
										$input_text_fc = ROT4_KB	//Let go, you dumb bastard!
										GOSUB load_audio_fc
									ENDIF
								ENDIF
							 ENDIF
						ENDIF
						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 9
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
									audio_label_fc = SOUND_ROT4_KD	//Fuck you, pig!								
									$input_text_fc = ROT4_KD //Fuck you, pig!
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF
						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 10
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer sweet 30.0 30.0 FALSE
									audio_label_fc = SOUND_ROT4_KE //Argh! My fingers!
									$input_text_fc = ROT4_KE //Argh! My fingers!
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF

					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////   Player catches Sweet   //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF NOT IS_CAR_DEAD benz_fc 
	IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CAR_DEAD firetruck_fc

			IF finaleCchase_fcflag = 2
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS benz_fc 0.0 1.5 0.5 WorldX_fc WorldY_fc WorldZ_fc
				number_of_frames_fc -= 1.0

				diffx_fc -= stepx_fc
				diffy_fc -= stepy_fc
				diffz_fc -= stepz_fc

				sweetx_fc = WorldX_fc + diffx_fc
				sweety_fc = WorldY_fc + diffy_fc
				sweetz_fc = WorldZ_fc + diffz_fc

				sweetz_fc = sweetz_fc - 1.0				
				SET_CHAR_COORDINATES sweet sweetx_fc sweety_fc sweetz_fc

			
				IF number_of_frames_fc = 0.0
					ATTACH_CHAR_TO_CAR sweet benz_fc 0.0 1.5 0.5. FACING_FORWARD 0.0 WEAPONTYPE_UNARMED
					TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_LAND_CAR FINALE 4.0 FALSE FALSE FALSE TRUE -1
					IF DOES_BLIP_EXIST sweet_fcblip
						CHANGE_BLIP_DISPLAY sweet_fcblip BLIP_ONLY
					ENDIF
					TIMERA = 0
					finaleCchase_fcflag = 3
				ENDIF

			ENDIF


			IF finaleCchase_fcflag = 3
				IF IS_CHAR_PLAYING_ANIM sweet FIN_LAND_CAR
					GET_CHAR_ANIM_CURRENT_TIME sweet FIN_LAND_CAR sweetanimtime_fc
						IF sweetanimtime_fc = 1.0
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION sweetx_fc sweety_fc sweetz_fc SOUND_BONNET_DENT

							TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_CLIMB_IN FINALE 4.0 FALSE FALSE FALSE TRUE -1
							TIMERA = 0
							finaleCchase_fcflag = 4
						ENDIF		
				ENDIF
			ENDIF

			IF finaleCchase_fcflag < 4
				IF finaleCchase_fcflag > 1

					GOSUB process_audio_fc

					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 0
							audio_label_fc = SOUND_ROT4_MC	//Gotcha!
							$input_text_fc = ROT4_MC	//Gotcha!
							GOSUB load_audio_fc
						ENDIF
					ENDIF

				ENDIF
			ENDIF

		ENDIF
	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////   Switching Seats Cutscene   //////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF finalerails_fcflag = 0
	IF NOT IS_CAR_DEAD benz_fc
		IF NOT IS_CAR_DEAD firetruck_fc
			IF NOT IS_CHAR_DEAD sweet


				IF finaleCchase_fcflag = 4
					IF TIMERA > 2800
						CLEAR_MISSION_AUDIO 1
						CLEAR_PRINTS
						handlingudio_fcflag = 0
						progressaudio_fcflag = 0

						SET_FIXED_CAMERA_POSITION 2908.9490 -1565.1792 15.6092 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2908.9958 -1565.0142 14.6240 JUMP_CUT
						SET_INTERPOLATION_PARAMETERS 0.0 1500
						FORCE_CAR_LIGHTS firetruck_fc FORCE_CAR_LIGHTS_OFF
						SWITCH_WIDESCREEN ON
						SET_PLAYER_CONTROL PLAYER1 OFF
						SET_CAR_PROOFS benz_fc TRUE TRUE TRUE TRUE TRUE
						
						CLEAR_ONSCREEN_COUNTER igripbardisplay_fc
						DELETE_CHAR copladder_fc

						IF IS_PLAYBACK_GOING_ON_FOR_CAR firetruck_fc
							STOP_PLAYBACK_RECORDED_CAR firetruck_fc
						ENDIF

						START_PLAYBACK_RECORDED_CAR	firetruck_fc 290
						SET_PLAYBACK_SPEED firetruck_fc 0.9
						START_PLAYBACK_RECORDED_CAR benz_fc 291
						finaleCchase_fcflag = 5
					ENDIF
				ENDIF
			

				IF finaleCchase_fcflag = 5
					IF IS_PLAYBACK_GOING_ON_FOR_CAR firetruck_fc
						IF IS_PLAYBACK_GOING_ON_FOR_CAR benz_fc
							
							PAUSE_PLAYBACK_RECORDED_CAR	firetruck_fc
							PAUSE_PLAYBACK_RECORDED_CAR benz_fc

							REQUEST_CAR_RECORDING 230 //copcar1
							REQUEST_CAR_RECORDING 231 //copcar1
							REQUEST_CAR_RECORDING 232 //ballas1
							REQUEST_CAR_RECORDING 233 //ballas2 
							REQUEST_CAR_RECORDING 234 //mexcar1
							REQUEST_CAR_RECORDING 235 //mexcar2
							REQUEST_CAR_RECORDING 236 //bike1
							REQUEST_CAR_RECORDING 237 //bike2
							REQUEST_CAR_RECORDING 238 //bike3
							REQUEST_CAR_RECORDING 239 //mexcar3
							REQUEST_CAR_RECORDING 240 //bike4
							
							REQUEST_MODEL MICRO_UZI
							REQUEST_MODEL COPCARLA
							REQUEST_MODEL SANCHEZ

							REQUEST_MODEL MOLOTOV
							REQUEST_MODEL LSV3
							REQUEST_MODEL TORNADO

							LOAD_ALL_MODELS_NOW

							WHILE NOT HAS_MODEL_LOADED MICRO_UZI
								OR NOT HAS_MODEL_LOADED COPCARLA
								OR NOT HAS_MODEL_LOADED SANCHEZ
								WAIT 0
							ENDWHILE

							WHILE NOT HAS_MODEL_LOADED LSV3
								OR NOT HAS_MODEL_LOADED	TORNADO
								OR NOT HAS_MODEL_LOADED	MOLOTOV
								WAIT 0
							ENDWHILE

							WAIT 2000
							
							IF NOT IS_CHAR_DEAD sweet
								IF NOT IS_CAR_DEAD firetruck_fc
									IF NOT IS_CAR_DEAD benz_fc

										//sweet and player in car
										GET_CHAR_COORDINATES scplayer player_x player_y player_z
										player_z = player_z - 10.0
										IF IS_CHAR_IN_ANY_CAR scplayer
											WARP_CHAR_FROM_CAR_TO_COORD scplayer player_x player_y player_z
										ENDIF
										GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 99999
										
										GET_CHAR_COORDINATES sweet player_x player_y player_z
										player_z = player_z - 10.0
										IF IS_CHAR_IN_ANY_CAR sweet
											WARP_CHAR_FROM_CAR_TO_COORD sweet player_x player_y player_z
										ELSE
											DETACH_CHAR_FROM_CAR sweet
											SET_CHAR_COORDINATES sweet player_x player_y player_z
										ENDIF
										WARP_CHAR_INTO_CAR sweet benz_fc
										WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer benz_fc 0

										//cop ladder
										//DELETE_CHAR copladder_fc
										CREATE_CHAR PEDTYPE_MISSION1 LAPD1 player_x player_y player_z copladder_fc
										SET_CHAR_DECISION_MAKER copladder_fc carterc_dm
										ATTACH_CHAR_TO_CAR copladder_fc firetruck_fc -0.014 -7.40 -999.253 FACING_BACK 0.0 WEAPONTYPE_MICRO_UZI
										CONTROL_MOVABLE_VEHICLE_PART firetruck_fc 0.2
										SET_CHAR_HEALTH copladder_fc 1000
										SET_CHAR_ACCURACY copladder_fc 50
										enemy_fc = copladder_fc
										GOSUB stayshoot_fclabel

										CREATE_CHAR PEDTYPE_MISSION1 LAPD1 player_x player_y player_z coptruck_fc
										SET_CHAR_DECISION_MAKER coptruck_fc carterc_dm
										ATTACH_CHAR_TO_CAR coptruck_fc firetruck_fc 0.0 0.656 2.408 FACING_FORWARD 0.0 WEAPONTYPE_MICRO_UZI
										TASK_PLAY_ANIM_NON_INTERRUPTABLE coptruck_fc FIN_Cop1_ClimbOut FINALE2 1000.0 FALSE FALSE FALSE TRUE -1	//FIN_copladder_fc_ClimbOut2
										SET_CHAR_HEALTH coptruck_fc 1000

									ENDIF
								ENDIF
							ENDIF

							IF IS_CHAR_PLAYING_ANIM coptruck_fc FIN_Cop1_ClimbOut
								GET_CHAR_ANIM_CURRENT_TIME coptruck_fc FIN_Cop1_ClimbOut copladder_fcanimtime
									IF copladder_fcanimtime > 0.0
										SET_CHAR_ANIM_SPEED coptruck_fc FIN_Cop1_ClimbOut 0.0
									ENDIF
							ENDIF

							IF NOT IS_CHAR_DEAD sweet
								TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet FIN_Switch_S FINALE2 8.0 FALSE FALSE FALSE FALSE -1
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer FIN_Switch_P FINALE2 8.0 FALSE FALSE FALSE FALSE -1
							ENDIF

							IF NOT IS_CAR_DEAD benz_fc
								IF NOT IS_CAR_DEAD firetruck_fc
									TIMERA = 0
									finaleCchase_fcflag = 6
								ENDIF
							ENDIF

						ENDIF
					ENDIF
				ENDIF

				IF finaleCchase_fcflag = 6
					IF NOT IS_CHAR_DEAD sweet
						IF IS_CHAR_PLAYING_ANIM sweet FIN_Switch_S 
							IF IS_CHAR_PLAYING_ANIM scplayer FIN_Switch_P 
								GET_CHAR_ANIM_CURRENT_TIME scplayer FIN_Switch_P copladder_fcanimtime
									IF copladder_fcanimtime > 0.0
										SET_CHAR_ANIM_SPEED scplayer FIN_Switch_P 0.0
											GET_CHAR_ANIM_CURRENT_TIME sweet FIN_Switch_S copladder_fcanimtime
												IF copladder_fcanimtime > 0.0
													SET_CHAR_ANIM_SPEED sweet FIN_Switch_S 0.0
														//coptruck
														IF NOT IS_CHAR_DEAD coptruck_fc
															IF IS_CHAR_PLAYING_ANIM coptruck_fc FIN_Cop1_ClimbOut
	//															GET_CHAR_ANIM_CURRENT_TIME coptruck_fc FIN_Cop1_ClimbOut copladder_fcanimtime
	//																IF copladder_fcanimtime > 0.0
																		SET_CHAR_ANIM_SPEED coptruck_fc FIN_Cop1_ClimbOut 0.0
	//																ENDIF
															ENDIF
														ENDIF

													finaleCchase_fcflag = 7
												ENDIF
									ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF finaleCchase_fcflag = 7
					IF NOT IS_CAR_DEAD benz_fc
						IF NOT IS_CAR_DEAD firetruck_fc
							IF IS_PLAYBACK_GOING_ON_FOR_CAR firetruck_fc
								IF IS_PLAYBACK_GOING_ON_FOR_CAR benz_fc
									UNPAUSE_PLAYBACK_RECORDED_CAR firetruck_fc
									UNPAUSE_PLAYBACK_RECORDED_CAR benz_fc
									FORCE_CAR_LIGHTS firetruck_fc FORCE_CAR_LIGHTS_ON
									TIMERA = 0
									finaleCchase_fcflag = 8
								ENDIF

							ENDIF
						ENDIF
					ENDIF
				ENDIF


				IF finaleCchase_fcflag = 8
					IF TIMERA > 599
						IF NOT IS_CAR_DEAD benz_fc
							IF NOT IS_CAR_DEAD firetruck_fc
								POINT_CAMERA_AT_POINT 2909.1978 -1564.3035 15.1955 INTERPOLATION
								finaleCchase_fcflag = 9
							ENDIF			
						ENDIF
					ENDIF
				ENDIF

				IF finaleCchase_fcflag = 9
					IF TIMERA > 2500
						IF NOT IS_CAR_DEAD benz_fc
							IF NOT IS_CAR_DEAD firetruck_fc
								RESTORE_CAMERA
								handlingudio_fcflag = 0
								progressaudio_fcflag = 0
								finaleCchase_fcflag = 10
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF finaleCchase_fcflag = 10
					IF TIMERA > 4600
						SWITCH_WIDESCREEN OFF
						SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
						SET_CHAR_PROOFS sweet TRUE TRUE TRUE TRUE TRUE
						//dialogue here
						finaleCchase_fcflag = 11
					ENDIF
				ENDIF

				IF finaleCchase_fcflag = 11
					IF TIMERA > 5000
						IF IS_CHAR_PLAYING_ANIM scplayer FIN_Switch_P 
							IF IS_CHAR_PLAYING_ANIM sweet FIN_Switch_S 
								SET_CHAR_ANIM_SPEED scplayer FIN_Switch_P 1.0
								SET_CHAR_ANIM_SPEED sweet FIN_Switch_S 1.0
								finaleCchase_fcflag = 12
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF finaleCchase_fcflag = 12
					IF IS_CHAR_PLAYING_ANIM scplayer FIN_Switch_P 
						GET_CHAR_ANIM_CURRENT_TIME scplayer FIN_Switch_P copladder_fcanimtime
							IF copladder_fcanimtime = 1.0
								TASK_DRIVE_BY scplayer -1 -1 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								SET_PLAYER_CONTROL PLAYER1 ON
								SET_PLAYER_ENTER_CAR_BUTTON PLAYER1 FALSE //cleanup
								SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
								SET_CAR_PROOFS benz_fc FALSE TRUE TRUE TRUE TRUE
		
								SET_CAR_HEALTH benz_fc 20000
								GET_CAR_HEALTH benz_fc carhealth_fc
								carhealth_fc = carhealth_fc / 200
								DISPLAY_ONSCREEN_COUNTER_WITH_STRING carhealth_fc COUNTER_DISPLAY_BAR RM4_52

								IF NOT IS_CHAR_DEAD copladder_fc
									SET_CHAR_PROOFS copladder_fc FALSE TRUE TRUE FALSE FALSE
								ENDIF
								IF NOT IS_CHAR_DEAD coptruck_fc
									SET_CHAR_PROOFS coptruck_fc TRUE TRUE TRUE TRUE TRUE
								ENDIF

								copladder_fcflag = 0
								coptruck_fcflag = 1 //get that cop guy coming out of the truck
								finalerails_fcflag = 1
								rioters_fcflag = 1
								finaleCchase_fcflag = 13
								PRINT_HELP RM4_54  
								handlingudio_fcflag = 0
								progressaudio_fcflag = 0
							ENDIF
					ENDIF
				ENDIF



				IF finaleCchase_fcflag > 10
					IF finaleCchase_fcflag < 13
		
						//audio for chase
						GOSUB process_audio_fc
										
						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 0
								audio_label_fc = SOUND_ROT4_ME	//Take the wheel, it’s payback time!
								$input_text_fc = ROT4_ME	//Take the wheel, it’s payback time!
								GOSUB load_audio_fc
							ENDIF
						ENDIF

					ENDIF
				ENDIF

			ENDIF
		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////   Shootout part 1   //////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF finalerails_fcflag = 1
	IF NOT IS_CAR_DEAD benz_fc
		IF NOT IS_CAR_DEAD firetruck_fc

			//car health
			GET_CAR_HEALTH benz_fc carhealth_fc
			carhealth_fc = carhealth_fc - 12000 //80

			IF carhealth_fc <= 0 
				carhealth_fc = 0
				SET_CAR_PROOFS benz_fc FALSE FALSE FALSE FALSE FALSE
				SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
				IF NOT IS_CHAR_DEAD sweet
					TASK_DIE sweet
				ENDIF
				EXPLODE_CAR benz_fc
				PRINT_NOW RM4_53 5000 1 //~r~You wrecked your car
				GOTO mission_finaleC_failed
			ELSE
				carhealth_fc = carhealth_fc / 80
			ENDIF

			IF IS_CHAR_DEAD sweet
				PRINT_NOW RM4_53 5000 1 //~r~You wrecked your car
				GOTO mission_finaleC_failed
			ENDIF

			//cop ladder
			IF copladder_fcflag < 5
				IF NOT IS_CHAR_DEAD copladder_fc
					IF copladder_fcflag = 0
						GET_CHAR_HEALTH copladder_fc health_fc
							IF health_fc < 970
								SET_CHAR_PROOFS copladder_fc TRUE TRUE TRUE TRUE TRUE
								SET_CURRENT_CHAR_WEAPON copladder_fc WEAPONTYPE_UNARMED
								TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_Cop1_Shot FINALE2 1000.0 FALSE FALSE FALSE TRUE -1	
								copladder_fcflag = 1
							ENDIF
					ENDIF
					IF copladder_fcflag = 1
						IF IS_CHAR_PLAYING_ANIM copladder_fc FIN_Cop1_Shot
							GET_CHAR_ANIM_CURRENT_TIME copladder_fc FIN_Cop1_Shot copladder_fcanimtime
								IF copladder_fcanimtime = 1.0
									TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_Cop1_Swing FINALE2 1000.0 TRUE FALSE FALSE TRUE -1
									copladder_fcflag = 2
								ENDIF
						ENDIF
					ENDIF
					//has to get set to 3
					IF copladder_fcflag = 3
						DETACH_CHAR_FROM_CAR copladder_fc
						TASK_PLAY_ANIM_NON_INTERRUPTABLE copladder_fc FIN_Cop1_Shot FINALE2 1000.0 FALSE TRUE TRUE TRUE -1
						copladder_fcflag = 4
					ENDIF
					IF copladder_fcflag = 4
						IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer copladder_fc 20.0 20.0 FALSE 
							IF NOT IS_CHAR_ON_SCREEN copladder_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED copladder_fc
								copladder_fcflag = 5
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF DOES_CHAR_EXIST copladder_fc
						DETACH_CHAR_FROM_CAR copladder_fc
					ENDIF
				ENDIF
			ENDIF


			//cop truck
			IF coptruck_fcflag < 6
				IF NOT IS_CHAR_DEAD coptruck_fc
					IF coptruck_fcflag = 1
						IF IS_CHAR_PLAYING_ANIM coptruck_fc FIN_Cop1_ClimbOut
							SET_CHAR_ANIM_SPEED coptruck_fc FIN_Cop1_ClimbOut 1.0
							coptruck_fcflag = 2
						ENDIF
					ENDIF
					IF coptruck_fcflag = 2
						IF IS_CHAR_PLAYING_ANIM coptruck_fc FIN_Cop1_ClimbOut
							GET_CHAR_ANIM_CURRENT_TIME coptruck_fc FIN_Cop1_ClimbOut copladder_fcanimtime
								IF copladder_fcanimtime = 1.0
									DETACH_CHAR_FROM_CAR coptruck_fc
									ATTACH_CHAR_TO_CAR coptruck_fc firetruck_fc -0.991 -4.12 0.297 FACING_BACK 0.0 WEAPONTYPE_MICRO_UZI //-0.991 -3.802 0.297
									TASK_PLAY_ANIM_NON_INTERRUPTABLE coptruck_fc FIN_Cop2_ClimbOut FINALE2 1000.0 FALSE FALSE FALSE TRUE -1
									SET_CHAR_HEALTH coptruck_fc 1000
									SET_CHAR_ACCURACY coptruck_fc 5
									coptruck_fcflag = 3
								ENDIF
						ENDIF
					ENDIF
					IF coptruck_fcflag = 3
						IF IS_CHAR_PLAYING_ANIM coptruck_fc FIN_Cop2_ClimbOut
							GET_CHAR_ANIM_CURRENT_TIME coptruck_fc FIN_Cop2_ClimbOut copladder_fcanimtime
								IF copladder_fcanimtime = 1.0
									TIMERB = 0
									GET_CHAR_HEALTH coptruck_fc health_fc
									IF health_fc > 950
										SET_CHAR_HEALTH coptruck_fc 1100
									ELSE
										SET_CHAR_HEALTH coptruck_fc 1050
									ENDIF
									enemy_fc = coptruck_fc
									SET_CHAR_PROOFS coptruck_fc FALSE FALSE FALSE FALSE FALSE
									GOSUB stayshootnoduck_fclabel
									coptruck_fcflag = 4
								ENDIF
						ENDIF
					ENDIF
					IF coptruck_fcflag = 4
						GET_CHAR_HEALTH coptruck_fc health_fc
							IF health_fc < 1000
							OR TIMERB > 60000
								DETACH_CHAR_FROM_CAR coptruck_fc
								TASK_PLAY_ANIM_NON_INTERRUPTABLE coptruck_fc KO_SKID_BACK PED 1000.0 FALSE TRUE TRUE TRUE -1
								coptruck_fcflag = 5
							ENDIF
					ENDIF
					IF coptruck_fcflag = 5
						IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer coptruck_fc 30.0 30.0 FALSE 
							IF NOT IS_CHAR_ON_SCREEN coptruck_fc
								MARK_CHAR_AS_NO_LONGER_NEEDED coptruck_fc
								coptruck_fcflag = 6
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF DOES_CHAR_EXIST coptruck_fc
						DETACH_CHAR_FROM_CAR coptruck_fc
					ENDIF
				ENDIF
			ENDIF


			///////////////////////////////////////////////////////////////////////////////	mission cars
			IF rails_fcflag = 0
				IF LOCATE_CAR_2D firetruck_fc 2861.28 -1298.8 25.0 25.0 FALSE

					CREATE_CAR COPCARLA 2816.01 -1186.56 23.95 copcar1_fc
					SET_CAR_HEADING copcar1_fc 279.03
					SET_CAR_HEALTH copcar1_fc 400
					SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar1_fc TRUE
					CREATE_CHAR_INSIDE_CAR copcar1_fc PEDTYPE_MISSION1 LAPD1 cop1_fc
					SET_CHAR_DECISION_MAKER cop1_fc carterc_dm
					SET_CHAR_SUFFERS_CRITICAL_HITS cop1_fc FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop1_fc TRUE
					CREATE_CHAR_AS_PASSENGER copcar1_fc PEDTYPE_MISSION1 LAPD1 0 cop2_fc
					SET_CHAR_DECISION_MAKER cop2_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop2_fc TRUE
					GIVE_WEAPON_TO_CHAR cop2_fc WEAPONTYPE_MICRO_UZI 9999

					CREATE_CAR COPCARLA 2803.0 -1183.0 26.0 copcar2_fc
					SET_CAR_HEADING copcar2_fc 261.0
					SET_CAR_HEALTH copcar2_fc 600
					SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar2_fc TRUE
					CREATE_CHAR_INSIDE_CAR copcar2_fc PEDTYPE_MISSION1 LAPD1 cop3_fc
					SET_CHAR_DECISION_MAKER cop3_fc carterc_dm
					SET_CHAR_SUFFERS_CRITICAL_HITS cop3_fc FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop3_fc TRUE
					CREATE_CHAR_AS_PASSENGER copcar2_fc PEDTYPE_MISSION1 LAPD1 0 cop4_fc
					SET_CHAR_DECISION_MAKER cop4_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop4_fc TRUE
					GIVE_WEAPON_TO_CHAR cop4_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CAR_PROOFS copcar2_fc FALSE TRUE TRUE FALSE FALSE
					rails_fcflag = 1
				ENDIF			
			ENDIF

			IF rails_fcflag = 1
				IF LOCATE_CAR_2D firetruck_fc 2849.39 -1232.93 20.0 20.0 FALSE
					
					IF NOT IS_CAR_DEAD copcar1_fc
						START_PLAYBACK_RECORDED_CAR copcar1_fc 230
						SWITCH_CAR_SIREN copcar1_fc ON
						IF NOT IS_CHAR_DEAD cop2_fc
							TASK_DRIVE_BY cop2_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 80
						ENDIF
						ADD_BLIP_FOR_CAR copcar1_fc copcar1_fcblip
						CHANGE_BLIP_DISPLAY copcar1_fcblip BLIP_ONLY
						copcar1_fcflag = 1
						copcar1swap_fcflag = 1
					ENDIF
					IF NOT IS_CAR_DEAD copcar2_fc
						SWITCH_CAR_SIREN copcar2_fc ON
						START_PLAYBACK_RECORDED_CAR copcar2_fc 231
						IF NOT IS_CHAR_DEAD cop4_fc
							TASK_DRIVE_BY cop4_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 80
						ENDIF
						ADD_BLIP_FOR_CAR copcar2_fc copcar2_fcblip
						CHANGE_BLIP_DISPLAY copcar2_fcblip BLIP_ONLY
						copcar2_fcflag = 1
						copcar2swap_fcflag = 1
					ENDIF
					actiontext_fcflag = 1
					CREATE_CAR TORNADO 2735.333 -1081.0465 69.033 ecar1_fc
					SET_CAR_HEADING ecar1_fc 37.918
					SET_CAR_HEALTH ecar1_fc 275
					SET_CAR_ONLY_DAMAGED_BY_PLAYER ecar1_fc TRUE
					CREATE_CHAR_INSIDE_CAR ecar1_fc PEDTYPE_MISSION1 MALE01 gang1_fc
					SET_CHAR_DECISION_MAKER gang1_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang1_fc TRUE
					CREATE_CHAR_AS_PASSENGER ecar1_fc PEDTYPE_MISSION1 MALE01 0 gang2_fc
					SET_CHAR_DECISION_MAKER gang2_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang2_fc TRUE
					GIVE_WEAPON_TO_CHAR gang2_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CAR_PROOFS ecar1_fc FALSE TRUE TRUE FALSE FALSE

					CREATE_CAR TORNADO 2595.742 -1003.581 75.038 ecar2_fc
					SET_CAR_HEADING ecar2_fc 180.0
					SET_CAR_ONLY_DAMAGED_BY_PLAYER ecar2_fc TRUE
					CREATE_CHAR_INSIDE_CAR ecar2_fc PEDTYPE_MISSION1 MALE01 gang3_fc
					SET_CHAR_DECISION_MAKER gang3_fc carterc_dm
					SET_CHAR_SUFFERS_CRITICAL_HITS gang3_fc FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang3_fc TRUE
					CREATE_CHAR_AS_PASSENGER ecar2_fc PEDTYPE_MISSION1 MALE01 0 gang4_fc
					SET_CHAR_DECISION_MAKER gang4_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang4_fc TRUE
					GIVE_WEAPON_TO_CHAR gang4_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CAR_PROOFS ecar2_fc FALSE TRUE TRUE FALSE FALSE

					rails_fcflag = 2
				ENDIF
			ENDIF

			//imran
			IF rails_fcflag = 2
				IF LOCATE_CAR_2D benz_fc 2717.71 -1047.36 20.0 20.0 FALSE

					MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
					actiontext_fcflag = 2
					IF NOT IS_CAR_DEAD ecar1_fc
						START_PLAYBACK_RECORDED_CAR ecar1_fc 232
						IF NOT IS_CHAR_DEAD gang2_fc
							TASK_DRIVE_BY gang2_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
						ENDIF
						ADD_BLIP_FOR_CAR ecar1_fc ecar1_fcblip
						CHANGE_BLIP_DISPLAY ecar1_fcblip BLIP_ONLY
						ecar1_fcflag = 1
						ecar1swap_fcflag = 1
					ENDIF

					IF NOT IS_CAR_DEAD ecar2_fc
						START_PLAYBACK_RECORDED_CAR ecar2_fc 233
						IF NOT IS_CHAR_DEAD gang4_fc
							TASK_DRIVE_BY gang4_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
						ENDIF
						ADD_BLIP_FOR_CAR ecar2_fc ecar2_fcblip
						CHANGE_BLIP_DISPLAY ecar2_fcblip BLIP_ONLY
						ecar2_fcflag = 1
						ecar2swap_fcflag = 1
					ENDIF

					CREATE_CAR TORNADO 2707.499 -1329.559 47.3 mexcar1_fc
					SET_CAR_HEADING mexcar1_fc 265.83
					SET_CAR_ONLY_DAMAGED_BY_PLAYER mexcar1_fc TRUE
					CREATE_CHAR_INSIDE_CAR mexcar1_fc PEDTYPE_MISSION1 LSV3 gang5_fc
					SET_CHAR_DECISION_MAKER gang5_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang5_fc TRUE
					CREATE_CHAR_AS_PASSENGER mexcar1_fc PEDTYPE_MISSION1 LSV3 0 gang6_fc
					SET_CHAR_DECISION_MAKER gang6_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang6_fc TRUE
					GIVE_WEAPON_TO_CHAR gang6_fc WEAPONTYPE_MICRO_UZI 99999
					SET_CAR_PROOFS mexcar1_fc FALSE TRUE TRUE FALSE FALSE
					SET_CAR_HEALTH mexcar1_fc 300

					CREATE_CAR TORNADO 2759.875 -1373.294 38.777 mexcar2_fc
					SET_CAR_HEADING mexcar2_fc 90.9
					SET_CAR_ONLY_DAMAGED_BY_PLAYER mexcar2_fc TRUE
					CREATE_CHAR_INSIDE_CAR mexcar2_fc PEDTYPE_MISSION1 LSV3 gang7_fc
					SET_CHAR_DECISION_MAKER gang7_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang7_fc TRUE
					CREATE_CHAR_AS_PASSENGER mexcar2_fc PEDTYPE_MISSION1 LSV3 0 gang8_fc
					SET_CHAR_DECISION_MAKER gang8_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang8_fc TRUE
					GIVE_WEAPON_TO_CHAR gang8_fc WEAPONTYPE_MICRO_UZI 99999
					SET_CAR_PROOFS mexcar2_fc FALSE TRUE TRUE FALSE FALSE
					SET_CAR_HEALTH mexcar2_fc 800

					CREATE_OBJECT la_fuckcar2 2722.599 -1324.889 49.47 gangcar5_fc
					SET_OBJECT_DYNAMIC gangcar5_fc TRUE
					SET_OBJECT_HEADING gangcar5_fc 83.325

					//and on the bridge	

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2631.18 -1244.17 50.58 rioter6_fc
					SET_CHAR_HEADING rioter6_fc 62.09
					SET_CHAR_PROOFS rioter6_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter6_fc TRUE
					SET_CHAR_HEALTH rioter6_fc 10
					SET_CHAR_DECISION_MAKER rioter6_fc carterc_dm
					GIVE_WEAPON_TO_CHAR rioter6_fc WEAPONTYPE_MOLOTOV 9999
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter6_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2646.11 -1193.27 66.1 rioter10_fc
					SET_CHAR_HEADING rioter10_fc 285.845
					GIVE_WEAPON_TO_CHAR rioter10_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter10_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter10_fc 10
					SET_CHAR_DECISION_MAKER rioter10_fc carterc_dm
					enemy_fc = rioter10_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2653.935 -1211.778 61.667 rioter11_fc
					SET_CHAR_HEADING rioter11_fc 360.0
					GIVE_WEAPON_TO_CHAR rioter11_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter11_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter11_fc 10
					SET_CHAR_DECISION_MAKER rioter11_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter11_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2664.336 -1173.326 66.69 rioter12_fc
					SET_CHAR_HEADING rioter12_fc 53.93
					GIVE_WEAPON_TO_CHAR rioter12_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter12_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter12_fc 10
					SET_CHAR_DECISION_MAKER rioter12_fc carterc_dm
					enemy_fc = rioter12_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2639.27 -1175.4 66.5 rioter13_fc
					SET_CHAR_HEADING rioter13_fc 2.3
					GIVE_WEAPON_TO_CHAR rioter13_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter13_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter13_fc 10
					SET_CHAR_DECISION_MAKER rioter13_fc carterc_dm
					enemy_fc = rioter10_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel
						
					rails_fcflag = 3
				ENDIF
			ENDIF

			IF rails_fcflag = 3
				IF LOCATE_CAR_2D firetruck_fc 2737.48 -1309.88 30.0 30.0 FALSE
					actiontext_fcflag = 3						
					IF NOT IS_CAR_DEAD mexcar1_fc
						START_PLAYBACK_RECORDED_CAR mexcar1_fc 234
						IF NOT IS_CHAR_DEAD gang6_fc
							TASK_DRIVE_BY gang6_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 80
						ENDIF
						ADD_BLIP_FOR_CAR mexcar1_fc mexcar1_fcblip
						CHANGE_BLIP_DISPLAY mexcar1_fcblip BLIP_ONLY
						mexcar1_fcflag = 1
						mexcar1swap_fcflag = 1
					ENDIF

					IF NOT IS_CAR_DEAD mexcar2_fc
						START_PLAYBACK_RECORDED_CAR mexcar2_fc 235
						IF NOT IS_CHAR_DEAD gang8_fc
							TASK_DRIVE_BY gang8_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
						ENDIF
						ADD_BLIP_FOR_CAR mexcar2_fc mexcar2_fcblip
						CHANGE_BLIP_DISPLAY mexcar2_fcblip BLIP_ONLY
						mexcar2_fcflag = 1
						mexcar2swap_fcflag = 1
					ENDIF

					rails_fcflag = 4
				ENDIF
			ENDIF

			IF rails_fcflag = 4
				IF NOT IS_CAR_DEAD benz_fc
					IF LOCATE_CAR_2D benz_fc 2662.6 -1404.83 20.0 20.0 FALSE
						actiontext_fcflag = 4
						//create roadblocks
						CREATE_CAR SANCHEZ 2657.455 -1594.463 12.685 bike1_fc
						SET_CAR_HEADING bike1_fc 85.0
						CREATE_CHAR_INSIDE_CAR bike1_fc PEDTYPE_MISSION1 LSV3 gang9_fc
						SET_CHAR_DECISION_MAKER gang9_fc carterc_dm
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang9_fc TRUE
						CREATE_CHAR_AS_PASSENGER bike1_fc PEDTYPE_MISSION1 LSV3 0 gang10_fc
						SET_CHAR_DECISION_MAKER gang10_fc carterc_dm
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang10_fc TRUE
						GIVE_WEAPON_TO_CHAR gang10_fc WEAPONTYPE_MICRO_UZI 99999
						SET_CAR_ONLY_DAMAGED_BY_PLAYER bike1_fc TRUE
						SET_CAR_PROOFS bike1_fc FALSE TRUE TRUE FALSE FALSE

						CREATE_CAR SANCHEZ 2642.0 -1615.0 19.0 bike2_fc
						SET_CAR_HEADING bike2_fc 181.0
						CREATE_CHAR_INSIDE_CAR bike2_fc PEDTYPE_MISSION1 LSV3 gang11_fc
						SET_CHAR_DECISION_MAKER gang11_fc carterc_dm
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang11_fc TRUE
						CREATE_CHAR_AS_PASSENGER bike2_fc PEDTYPE_MISSION1 LSV3 0 gang12_fc
						SET_CHAR_DECISION_MAKER gang12_fc carterc_dm
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang12_fc TRUE
						GIVE_WEAPON_TO_CHAR gang12_fc WEAPONTYPE_MICRO_UZI 99999
						SET_CAR_ONLY_DAMAGED_BY_PLAYER bike2_fc TRUE
						SET_CAR_PROOFS bike2_fc FALSE TRUE TRUE FALSE FALSE

						CREATE_CAR SANCHEZ 2650.0 -1621.0 19.0 bike3_fc
						SET_CAR_HEADING bike3_fc 170.0
						CREATE_CHAR_INSIDE_CAR bike3_fc PEDTYPE_MISSION1 LSV3 gang13_fc
						SET_CHAR_DECISION_MAKER gang13_fc carterc_dm
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang13_fc TRUE
						CREATE_CHAR_AS_PASSENGER bike3_fc PEDTYPE_MISSION1 LSV3 0 gang14_fc
						SET_CHAR_DECISION_MAKER gang14_fc carterc_dm
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang14_fc TRUE
						GIVE_WEAPON_TO_CHAR gang14_fc WEAPONTYPE_MICRO_UZI 99999
						SET_CAR_ONLY_DAMAGED_BY_PLAYER bike3_fc TRUE
						SET_CAR_PROOFS bike3_fc FALSE TRUE TRUE FALSE FALSE

 						rails_fcflag = 5
					ENDIF
				ENDIF
			ENDIF


			IF rails_fcflag = 5
				IF LOCATE_CAR_2D firetruck_fc 2642.71 -1570.03 20.0 20.0 FALSE
					actiontext_fcflag = 5
					IF NOT IS_CAR_DEAD bike1_fc
						START_PLAYBACK_RECORDED_CAR bike1_fc 236
						IF NOT IS_CHAR_DEAD gang10_fc
							TASK_DRIVE_BY gang10_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
						ENDIF
						ADD_BLIP_FOR_CAR bike1_fc bike1_fcblip
						CHANGE_BLIP_DISPLAY bike1_fcblip BLIP_ONLY
						bike1_fcflag = 1
						bike1swap_fcflag = 1
					ENDIF

					IF NOT IS_CAR_DEAD bike2_fc
						START_PLAYBACK_RECORDED_CAR bike2_fc 237
						IF NOT IS_CHAR_DEAD gang12_fc
							TASK_DRIVE_BY gang12_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
						ENDIF
						ADD_BLIP_FOR_CAR bike2_fc bike2_fcblip
						CHANGE_BLIP_DISPLAY bike2_fcblip BLIP_ONLY
						bike2_fcflag = 1
						bike2swap_fcflag = 1
					ENDIF

					IF NOT IS_CAR_DEAD bike3_fc
						START_PLAYBACK_RECORDED_CAR bike3_fc 238 
						IF NOT IS_CHAR_DEAD gang14_fc
							TASK_DRIVE_BY gang14_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
						ENDIF
						ADD_BLIP_FOR_CAR bike3_fc bike3_fcblip
						CHANGE_BLIP_DISPLAY bike3_fcblip BLIP_ONLY
						bike3_fcflag = 1
						bike3swap_fcflag = 1
					ENDIF

					CREATE_CAR TORNADO 2704.0 -1654.0 10.0 mexcar3_fc
					SET_CAR_HEADING mexcar3_fc 90.0
					SET_CAR_HEALTH mexcar3_fc 300
					CREATE_CHAR_INSIDE_CAR mexcar3_fc PEDTYPE_MISSION1 LSV3 gang17_fc
					SET_CHAR_DECISION_MAKER gang17_fc carterc_dm
					SET_CHAR_SUFFERS_CRITICAL_HITS gang17_fc FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang17_fc TRUE
					CREATE_CHAR_AS_PASSENGER mexcar3_fc PEDTYPE_MISSION1 LSV3 0 gang18_fc
					SET_CHAR_DECISION_MAKER gang18_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang18_fc TRUE
					GIVE_WEAPON_TO_CHAR gang18_fc WEAPONTYPE_MICRO_UZI 99999
					SET_CAR_ONLY_DAMAGED_BY_PLAYER mexcar3_fc TRUE

					rails_fcflag = 6
				ENDIF
			ENDIF

			IF rails_fcflag = 6
				IF LOCATE_CAR_2D benz_fc 2642.98 -1619.8 20.0 20.0 FALSE

					IF NOT IS_CAR_DEAD mexcar3_fc
						START_PLAYBACK_RECORDED_CAR mexcar3_fc 239
						IF NOT IS_CHAR_DEAD gang18_fc
							TASK_DRIVE_BY gang18_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
						ENDIF
						ADD_BLIP_FOR_CAR mexcar3_fc mexcar3_fcblip
						CHANGE_BLIP_DISPLAY mexcar3_fcblip BLIP_ONLY
						mexcar3_fcflag = 1
						mexcar3swap_fcflag = 1
					ENDIF
					
					rails_fcflag = 7
				ENDIF
			ENDIF
			

			// cutscene
			IF rails_fcflag = 7
				IF LOCATE_CAR_2D firetruck_fc 2407.93 -1918.34 15.0 15.0 FALSE
					SWITCH_WIDESCREEN ON
					SET_PLAYER_CONTROL PLAYER1 OFF
					SET_CAR_PROOFS benz_fc TRUE TRUE TRUE TRUE TRUE
					SET_FIXED_CAMERA_POSITION 2409.0322 -1921.4258 14.0992 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2408.9939 -1920.4310 14.0044 JUMP_CUT
					TIMERA = 0
					rails_fcflag = 8
				ENDIF
			ENDIF

			IF rails_fcflag = 8
				IF TIMERA > 5000
					CLEAR_MISSION_AUDIO 1
					CLEAR_PRINTS
					DELETE_CHAR copladder_fc
					DELETE_CHAR coptruck_fc
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					/////////////////////////////////////////// LOAD DATA FOR NEXT PART OF THE MISSION /////////////////////////////////////////
					////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

					REQUEST_CAR_RECORDING 241 //firetruck
					REQUEST_CAR_RECORDING 242 //player car
					REQUEST_CAR_RECORDING 243 //bike1 
					REQUEST_CAR_RECORDING 244 //bike2
					REQUEST_CAR_RECORDING 245 //copcar1

					REQUEST_CAR_RECORDING 246 //copcar2
					REQUEST_CAR_RECORDING 247 //oceanic1
					REQUEST_CAR_RECORDING 248 //copcar3
					REQUEST_CAR_RECORDING 249 //copcar4
					REQUEST_CAR_RECORDING 229 //firetruck crash at the end

					REQUEST_MODEL COPCARLA

					LOAD_ALL_MODELS_NOW 

					WHILE NOT HAS_MODEL_LOADED COPCARLA
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 241
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 242
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 243
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 244
						WAIT 0
					ENDWHILE

					WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 245
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 246
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 247
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 248
						OR NOT HAS_CAR_RECORDING_BEEN_LOADED 249
						WAIT 0
					ENDWHILE

					WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 229
						WAIT 0
					ENDWHILE

					REMOVE_ANIMATION FINALE
					REMOVE_ANIMATION FINALE2

					finalerails_fcflag = 2
					rails_fcflag = 9
				ENDIF
			ENDIF


			///////////////////////////////////////////////////////////////////		mission peds
			IF rioters_fcflag = 1
				IF LOCATE_CAR_2D firetruck_fc 2835.29 -1049.49 20.0 20.0 FALSE
					//create guys at the top of the hill

					CREATE_OBJECT la_fuckcar2 2806.984 -1057.249 27.0 gangcar1_fc
					SET_OBJECT_DYNAMIC gangcar1_fc TRUE
					SET_OBJECT_HEADING gangcar1_fc 189.735
					START_SCRIPT_FIRE 2806.984 -1055.249 28.6 0 2 fire1_fc
				
					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2798.46 -1055.99 30.43 rioter1_fc
					SET_CHAR_HEADING rioter1_fc 285.845 
					GIVE_WEAPON_TO_CHAR rioter1_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter1_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter1_fc 10
					SET_CHAR_DECISION_MAKER rioter1_fc carterc_dm
					enemy_fc = rioter1_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CAR TORNADO 2785.06 -1043.915 35.916 parkedcar_fc
					SET_CAR_HEADING parkedcar_fc 4.2
					SET_CAR_HEALTH parkedcar_fc 250
					SET_CAR_ONLY_DAMAGED_BY_PLAYER parkedcar_fc TRUE

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2774.56 -1011.43 58.08 rioter2_fc
					SET_CHAR_HEADING rioter2_fc 285.845
					SET_CHAR_PROOFS rioter2_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter2_fc 10
					SET_CHAR_DECISION_MAKER rioter2_fc carterc_dm
					GIVE_WEAPON_TO_CHAR rioter2_fc WEAPONTYPE_MOLOTOV 9999
					enemy_fc = rioter2_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2782.984 -1041.284 36.62 rioter4_fc
					SET_CHAR_HEADING rioter4_fc 285.845 
					GIVE_WEAPON_TO_CHAR rioter4_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter4_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter4_fc 10
					SET_CHAR_DECISION_MAKER rioter4_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter4_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2749.393 -1063.34 53.0 rioter5_fc
					SET_CHAR_HEADING rioter5_fc 309.534
					SET_CHAR_PROOFS rioter5_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter5_fc 10
					SET_CHAR_DECISION_MAKER rioter5_fc carterc_dm
					GIVE_WEAPON_TO_CHAR rioter5_fc WEAPONTYPE_MOLOTOV 9999
					IF NOT IS_CHAR_DEAD cop1_fc
						enemy_fc = rioter5_fc
						enemytarget_fc = cop1_fc
						GOSUB chasekill_fclabel
					ELSE
						IF NOT IS_CHAR_DEAD tenpenny_fc
							enemy_fc = rioter5_fc
							enemytarget_fc = tenpenny_fc
							GOSUB chasekill_fclabel
						ENDIF
					ENDIF

					CREATE_OBJECT la_fuckcar2 2688.94 -1054.034 69.57 gangcar3_fc
					SET_OBJECT_DYNAMIC gangcar3_fc TRUE
					SET_OBJECT_HEADING gangcar3_fc 210.579
					START_SCRIPT_FIRE 2806.984 -1055.249 28.6 0 2 fire2_fc
					START_SCRIPT_FIRE 2807.984 -1056.249 28.6 0 1 fire3_fc

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2724.045 -1058.115 69.88 rioter7_fc
					SET_CHAR_HEADING rioter7_fc 360.0
					GIVE_WEAPON_TO_CHAR rioter7_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter7_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter7_fc 10
					SET_CHAR_DECISION_MAKER rioter7_fc carterc_dm
					enemy_fc = rioter2_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2632.0 -114.0 68.0 rioter8_fc
					SET_CHAR_HEADING rioter8_fc 254.974
					GIVE_WEAPON_TO_CHAR rioter8_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter8_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter8_fc 10
					SET_CHAR_DECISION_MAKER rioter8_fc carterc_dm
					enemy_fc = rioter8_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel
				
					//wreck car
					CREATE_OBJECT la_fuckcar2 2650.39 -1056.09 69.66 gangcar4_fc
					SET_OBJECT_DYNAMIC gangcar4_fc TRUE
					SET_OBJECT_HEADING gangcar4_fc 228.58
					START_SCRIPT_FIRE 2650.39 -1056.09 69.66 0 2 fire4_fc

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2649.78 -1052.49 69.44 rioter9_fc
					SET_CHAR_HEADING rioter9_fc 254.974
					GIVE_WEAPON_TO_CHAR rioter9_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter9_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter9_fc 10
					SET_CHAR_DECISION_MAKER rioter9_fc carterc_dm
					enemy_fc = rioter9_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2650.4 -1115.05 67.12 rioter6_fc
					SET_CHAR_HEADING rioter6_fc 360.0
					GIVE_WEAPON_TO_CHAR rioter6_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter6_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter6_fc 10
					SET_CHAR_DECISION_MAKER rioter6_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter6_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					rioters_fcflag = 2
				ENDIF
			ENDIF

			IF rioters_fcflag = 2
				IF LOCATE_CAR_2D benz_fc 2642.81 -1193.87 20.0 20.0 FALSE
					//get rid of guys at the bottom of the hill
					DELETE_OBJECT gangcar1_fc
					DELETE_CHAR rioter1_fc
					MARK_CAR_AS_NO_LONGER_NEEDED parkedcar_fc
					DELETE_CAR parkedcar_fc
					DELETE_CHAR rioter2_fc
					DELETE_CHAR rioter4_fc
					DELETE_CHAR rioter5_fc
					DELETE_OBJECT gangcar3_fc
					DELETE_CHAR rioter7_fc
					DELETE_OBJECT gangcar4_fc
					DELETE_CHAR rioter9_fc
					DELETE_CHAR rioter6_fc
					DELETE_CHAR rioter8_fc

					REMOVE_ALL_SCRIPT_FIRES
					rioters_fcflag = 3
				ENDIF
			ENDIF

			IF rioters_fcflag = 3
				IF LOCATE_CAR_2D benz_fc 2723.81 -1275.64 20.0 20.0 FALSE
					//get rid of guys on the bridge
					DELETE_CHAR rioter10_fc
					DELETE_CHAR rioter11_fc
					DELETE_CHAR rioter12_fc
					DELETE_CHAR rioter13_fc
					//create guys on the hill
					IF NOT IS_CAR_DEAD ecar2_fc
						SET_CAR_HEALTH ecar2_fc 250
						SET_CAR_ONLY_DAMAGED_BY_PLAYER ecar2_fc FALSE
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2718.29 -1324.342 48.5 rioter1_fc
					SET_CHAR_HEADING rioter1_fc 352.57
					GIVE_WEAPON_TO_CHAR rioter1_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter1_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter1_fc 10
					SET_CHAR_DECISION_MAKER rioter1_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter1_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2726.95 -1324.49 49.5 rioter2_fc
					SET_CHAR_HEADING rioter2_fc 327.185
					SET_CHAR_PROOFS rioter2_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter2_fc 10
					SET_CHAR_DECISION_MAKER rioter2_fc carterc_dm
					GIVE_WEAPON_TO_CHAR rioter2_fc WEAPONTYPE_MOLOTOV 9999
					enemy_fc = rioter2_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2710.591 -1315.29 74.34 rioter4_fc
					SET_CHAR_HEADING rioter4_fc 303.711
					GIVE_WEAPON_TO_CHAR rioter4_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter4_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter4_fc 10
					SET_CHAR_DECISION_MAKER rioter4_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter4_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2748.244 -1375.115 49.5 rioter5_fc
					SET_CHAR_HEADING rioter5_fc 309.534
					SET_CHAR_PROOFS rioter5_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter5_fc 10
					SET_CHAR_DECISION_MAKER rioter5_fc carterc_dm
					GIVE_WEAPON_TO_CHAR rioter5_fc WEAPONTYPE_MOLOTOV 9999
					enemy_fc = rioter5_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2750.615 -1388.75 39.0 rioter6_fc
					SET_CHAR_HEADING rioter6_fc 62.09
					SET_CHAR_PROOFS rioter6_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter6_fc 10
					SET_CHAR_DECISION_MAKER rioter6_fc carterc_dm
					GIVE_WEAPON_TO_CHAR rioter6_fc WEAPONTYPE_MOLOTOV 9999
					enemy_fc = rioter5_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel


					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2713.137 -1448.169 41.95 rioter7_fc
					SET_CHAR_HEADING rioter7_fc 267.04
					GIVE_WEAPON_TO_CHAR rioter7_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter7_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter7_fc 10
					SET_CHAR_DECISION_MAKER rioter7_fc carterc_dm
					enemy_fc = rioter7_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2711.935 -1399.32 54.0 rioter8_fc
					SET_CHAR_HEADING rioter8_fc 295.712
					GIVE_WEAPON_TO_CHAR rioter8_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter8_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter8_fc 10
					SET_CHAR_DECISION_MAKER rioter8_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter8_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2754.71 -1309.956 70.0 rioter9_fc
					SET_CHAR_HEADING rioter9_fc 77.189
					GIVE_WEAPON_TO_CHAR rioter9_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter9_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter9_fc 10
					SET_CHAR_DECISION_MAKER rioter9_fc carterc_dm
					enemy_fc = rioter9_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2750.615 -1419.473 38.5 rioter10_fc
					SET_CHAR_HEADING rioter10_fc 77.189
					GIVE_WEAPON_TO_CHAR rioter10_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter10_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter10_fc 10
					SET_CHAR_DECISION_MAKER rioter10_fc carterc_dm
					IF NOT IS_CHAR_DEAD gang7_fc
						enemy_fc = rioter10_fc
						enemytarget_fc = gang7_fc
						GOSUB chasekill_fclabel
					ELSE
						enemy_fc = rioter10_fc
						enemytarget_fc = scplayer
						GOSUB chasekill_fclabel
					ENDIF

					rioters_fcflag = 4
				ENDIF
			ENDIF

			IF rioters_fcflag = 4
				IF LOCATE_CAR_2D benz_fc 2682.55 -1494.04 20.0 20.0 FALSE
					//delete guys on hill create major rioters on the road and bridge down
					DELETE_CHAR rioter1_fc
					DELETE_CHAR rioter2_fc
					DELETE_CHAR rioter4_fc
					DELETE_CHAR rioter5_fc
					DELETE_CHAR rioter6_fc
					DELETE_CHAR rioter7_fc
					DELETE_CHAR rioter8_fc
					DELETE_CHAR rioter9_fc
					DELETE_CHAR rioter10_fc
					DELETE_OBJECT gangcar1_fc
					REMOVE_ALL_SCRIPT_FIRES 

					CREATE_OBJECT la_fuckcar2 2649.1943 -1514.1853 26.6789 gangcar1_fc
					SET_OBJECT_HEADING gangcar1_fc 329.3793
					SET_OBJECT_DYNAMIC gangcar1_fc TRUE

					CREATE_CAR TORNADO 2638.9883 -1555.9426 18.9832 parkedcar_fc
					SET_CAR_HEADING parkedcar_fc 290.7073
					SET_CAR_HEALTH parkedcar_fc 250
					SET_CAR_ONLY_DAMAGED_BY_PLAYER parkedcar_fc TRUE

					CREATE_OBJECT la_fuckcar2 2635.0969 -1576.9958 15.3271 gangcar3_fc
					SET_OBJECT_HEADING gangcar3_fc 77.1610 
					SET_OBJECT_DYNAMIC gangcar3_fc TRUE

					CREATE_OBJECT la_fuckcar2 2650.3945 -1586.5383 13.6033 gangcar4_fc
					SET_OBJECT_HEADING gangcar4_fc 295.7696
					SET_OBJECT_DYNAMIC gangcar4_fc TRUE

					CREATE_OBJECT la_fuckcar2 2649.9392 -1629.9374 9.8785 gangcar5_fc
					SET_OBJECT_HEADING gangcar5_fc 270.0938
					SET_OBJECT_DYNAMIC gangcar5_fc TRUE

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2647.5835 -1518.5471 25.7427 rioter1_fc
					SET_CHAR_HEADING rioter1_fc 12.7483
					GIVE_WEAPON_TO_CHAR rioter1_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter1_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter1_fc 10
					SET_CHAR_DECISION_MAKER rioter1_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter1_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2651.0447 -1515.1112 26.5111 rioter2_fc
					SET_CHAR_HEADING rioter2_fc 12.4201
					GIVE_WEAPON_TO_CHAR rioter2_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter2_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter2_fc 10
					SET_CHAR_DECISION_MAKER rioter2_fc carterc_dm
					enemy_fc = rioter2_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2636.1875 -1559.3972 18.5108 rioter3_fc
					SET_CHAR_HEADING rioter3_fc 276.3582
					GIVE_WEAPON_TO_CHAR rioter3_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter3_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter3_fc 10
					SET_CHAR_DECISION_MAKER rioter3_fc carterc_dm
					enemy_fc = rioter3_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2641.4182 -1558.0044 18.6112 rioter4_fc
					SET_CHAR_HEADING rioter4_fc 339.6795
					GIVE_WEAPON_TO_CHAR rioter4_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CHAR_PROOFS rioter4_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter4_fc 10
					SET_CHAR_DECISION_MAKER rioter4_fc carterc_dm
					enemy_fc = rioter4_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2649.3247 -1589.4099 13.0846 rioter5_fc
					SET_CHAR_HEADING rioter5_fc 318.9207
					GIVE_WEAPON_TO_CHAR rioter5_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CHAR_PROOFS rioter5_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter5_fc 10
					SET_CHAR_DECISION_MAKER rioter5_fc carterc_dm
					enemy_fc = rioter5_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel
					rioters_fcflag = 5
				ENDIF
			ENDIF

			IF rioters_fcflag = 5
				IF LOCATE_CAR_2D benz_fc 2652.95 -1404.16 20.0 20.0 FALSE

					START_SCRIPT_FIRE 2641.66 -1514.55 26.82 0 2 fire1_fc
					START_SCRIPT_FIRE 2638.68 -1515.3 26.5 0 3 fire1_fc
					START_SCRIPT_FIRE 2641.58 -1538.54 22.46 0 3 fire1_fc
					START_SCRIPT_FIRE 2635.64 -1605.36 10.7 0 2 fire1_fc
					START_SCRIPT_FIRE 2643.52 -1599.47 18.71 0 3 fire1_fc

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2647.5835 -1518.5471 25.7427 rioter6_fc
					SET_CHAR_HEADING rioter6_fc 12.7483
					GIVE_WEAPON_TO_CHAR rioter6_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter6_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter6_fc 10
					SET_CHAR_DECISION_MAKER rioter6_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter6_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2652.5154 -1587.5516 13.4203 rioter7_fc
					SET_CHAR_HEADING rioter7_fc 12.4201
					GIVE_WEAPON_TO_CHAR rioter7_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter7_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter7_fc 10
					SET_CHAR_DECISION_MAKER rioter7_fc carterc_dm
					enemy_fc = rioter7_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2647.3679 -1629.5948 9.7270 rioter8_fc
					SET_CHAR_HEADING rioter8_fc 276.3582
					GIVE_WEAPON_TO_CHAR rioter8_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CHAR_PROOFS rioter8_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter8_fc 10
					SET_CHAR_DECISION_MAKER rioter8_fc carterc_dm
					enemy_fc = rioter8_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2651.1484 -1631.9159 9.8767 rioter9_fc
					SET_CHAR_HEADING rioter9_fc 339.6795
					GIVE_WEAPON_TO_CHAR rioter9_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter9_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter9_fc 10
					SET_CHAR_DECISION_MAKER rioter9_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter9_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2635.9316 -1598.6659 18.7482 rioter10_fc
					SET_CHAR_HEADING rioter10_fc 318.9207
					GIVE_WEAPON_TO_CHAR rioter10_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter10_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter10_fc 10
					SET_CHAR_DECISION_MAKER rioter10_fc carterc_dm
					enemy_fc = rioter10_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2650.4944 -1600.3645 18.1152 rioter11_fc
					SET_CHAR_HEADING rioter11_fc 318.9207
					GIVE_WEAPON_TO_CHAR rioter11_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter11_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter11_fc 10
					SET_CHAR_DECISION_MAKER rioter11_fc carterc_dm
					enemy_fc = rioter11_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2635.9316 -1598.6659 18.7482 rioter12_fc
					SET_CHAR_HEADING rioter12_fc 318.9207
					GIVE_WEAPON_TO_CHAR rioter12_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter12_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter12_fc 10
					SET_CHAR_DECISION_MAKER rioter12_fc carterc_dm
					enemy_fc = rioter12_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2646.5981 -1599.9045 18.295 rioter13_fc
					SET_CHAR_HEADING rioter13_fc 318.9207
					GIVE_WEAPON_TO_CHAR rioter13_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter13_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter13_fc 10
					SET_CHAR_DECISION_MAKER rioter13_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter13_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 2634.6890 -1629.5061 18.7081 rioter14_fc
					SET_CHAR_HEADING rioter14_fc 318.9207
					GIVE_WEAPON_TO_CHAR rioter14_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter14_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter14_fc 10
					SET_CHAR_DECISION_MAKER rioter14_fc carterc_dm
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter14_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					rioters_fcflag = 6
				ENDIF
			ENDIF

			IF rioters_fcflag = 6
				IF LOCATE_CAR_2D benz_fc 2598.57 -1729.65 20.0 20.0 FALSE

					DELETE_CHAR rioter1_fc
					DELETE_CHAR rioter2_fc
					DELETE_CHAR rioter4_fc
					DELETE_CHAR rioter5_fc
					DELETE_CHAR rioter6_fc
					DELETE_CHAR rioter7_fc
					DELETE_CHAR rioter8_fc
					DELETE_CHAR rioter9_fc
					DELETE_CHAR rioter10_fc
					DELETE_CHAR rioter11_fc
					DELETE_CHAR rioter12_fc
					DELETE_CHAR rioter13_fc
					DELETE_CHAR rioter14_fc
					DELETE_CAR parkedcar_fc
					DELETE_OBJECT gangcar1_fc
					DELETE_OBJECT gangcar3_fc
					DELETE_OBJECT gangcar4_fc
					DELETE_OBJECT gangcar5_fc
					REMOVE_ALL_SCRIPT_FIRES

					rioters_fcflag = 7
				ENDIF
			ENDIF
			
			//seat swapping
			//cars
			IF copcar1swap_fcflag = 1
				IF NOT IS_CAR_DEAD copcar1_fc
					IF NOT IS_CHAR_DEAD cop1_fc
					ELSE
						IF NOT IS_CHAR_DEAD cop2_fc
							IF IS_CHAR_IN_CAR cop2_fc copcar1_fc
								GET_DRIVER_OF_CAR copcar1_fc driverofcar_fc
								IF driverofcar_fc = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop2_fc copcar1_fc
									copcar1swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR copcar1_fc
							copcar1swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF copcar1swap_fcflag = 2
				IF IS_CHAR_DEAD cop2_fc
					STOP_PLAYBACK_RECORDED_CAR copcar1_fc
					copcar1swap_fcflag = 3
				ENDIF 
			ENDIF

			IF copcar1_fcflag = 1
				IF NOT IS_CAR_DEAD copcar1_fc
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar1_fc
						MARK_CAR_AS_NO_LONGER_NEEDED copcar1_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED cop1_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED cop2_fc
						REMOVE_BLIP copcar1_fcblip 
						copcar1_fcflag = 2
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR copcar1_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED cop1_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED cop2_fc
					MARK_CAR_AS_NO_LONGER_NEEDED copcar1_fc
					REMOVE_BLIP copcar1_fcblip 
					copcar1_fcflag = 2
				ENDIF
			ENDIF

			IF copcar2swap_fcflag = 1
				IF NOT IS_CAR_DEAD copcar2_fc
					IF NOT IS_CHAR_DEAD cop3_fc
					ELSE
						IF NOT IS_CHAR_DEAD cop4_fc
							IF IS_CHAR_IN_CAR cop4_fc copcar2_fc
								GET_DRIVER_OF_CAR copcar2_fc driverofcar_fc
								IF driverofcar_fc = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop4_fc copcar2_fc
									copcar2swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR copcar2_fc
							copcar2swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF copcar2swap_fcflag = 2
				IF IS_CHAR_DEAD cop4_fc
					STOP_PLAYBACK_RECORDED_CAR copcar2_fc
					copcar2swap_fcflag = 3
				ENDIF 
			ENDIF

			IF copcar2_fcflag = 1
				IF NOT IS_CAR_DEAD copcar2_fc
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar2_fc
						MARK_CAR_AS_NO_LONGER_NEEDED copcar2_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED cop3_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED cop4_fc
						REMOVE_BLIP copcar2_fcblip 
						copcar2_fcflag = 2
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR copcar2_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED cop3_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED cop4_fc
					MARK_CAR_AS_NO_LONGER_NEEDED copcar2_fc
					REMOVE_BLIP copcar2_fcblip 
					copcar2_fcflag = 2
				ENDIF
			ENDIF

			IF ecar1swap_fcflag = 1
				IF NOT IS_CAR_DEAD ecar1_fc
					IF NOT IS_CHAR_DEAD gang1_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang2_fc
							IF IS_CHAR_IN_CAR gang2_fc ecar1_fc
								GET_DRIVER_OF_CAR ecar1_fc driverofcar_fc
								IF driverofcar_fc = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT gang2_fc ecar1_fc
									ecar1swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR ecar1_fc
							ecar1swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF ecar1swap_fcflag = 2
				IF IS_CHAR_DEAD gang2_fc
					STOP_PLAYBACK_RECORDED_CAR ecar1_fc
					ecar1swap_fcflag = 3
				ENDIF 
			ENDIF

			IF ecar1_fcflag = 1
				IF NOT IS_CAR_DEAD ecar1_fc
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR ecar1_fc
						MARK_CAR_AS_NO_LONGER_NEEDED ecar1_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fc
						REMOVE_BLIP ecar1_fcblip 
						ecar1_fcflag = 2
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR ecar1_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang1_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang2_fc
					MARK_CAR_AS_NO_LONGER_NEEDED ecar1_fc
					REMOVE_BLIP ecar1_fcblip 
					ecar1_fcflag = 2
				ENDIF
			ENDIF

			IF ecar2swap_fcflag = 1
				IF NOT IS_CAR_DEAD ecar2_fc
					IF NOT IS_CHAR_DEAD gang3_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang4_fc
							IF IS_CHAR_IN_CAR gang4_fc ecar2_fc
								GET_DRIVER_OF_CAR ecar2_fc driverofcar_fc
								IF driverofcar_fc = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT gang4_fc ecar2_fc
									ecar2swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR ecar2_fc
							ecar2swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF ecar2swap_fcflag = 2
				IF IS_CHAR_DEAD gang4_fc
					STOP_PLAYBACK_RECORDED_CAR ecar2_fc
					ecar2swap_fcflag = 3
				ENDIF 
			ENDIF

			IF ecar2_fcflag = 1
				IF NOT IS_CAR_DEAD ecar2_fc
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR ecar2_fc
						MARK_CAR_AS_NO_LONGER_NEEDED ecar2_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fc
						REMOVE_BLIP ecar2_fcblip 
						ecar2_fcflag = 2
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR ecar2_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang3_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang4_fc
					MARK_CAR_AS_NO_LONGER_NEEDED ecar2_fc
					REMOVE_BLIP ecar2_fcblip 
					ecar2_fcflag = 2
				ENDIF
			ENDIF

			IF mexcar1swap_fcflag = 1
				IF NOT IS_CAR_DEAD mexcar1_fc
					IF NOT IS_CHAR_DEAD gang5_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang6_fc
							IF IS_CHAR_IN_CAR gang6_fc mexcar1_fc
								GET_DRIVER_OF_CAR mexcar1_fc driverofcar_fc
								IF driverofcar_fc = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT gang6_fc mexcar1_fc
									mexcar1swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR mexcar1_fc
							mexcar1swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF mexcar1swap_fcflag = 2
				IF IS_CHAR_DEAD gang6_fc
					STOP_PLAYBACK_RECORDED_CAR mexcar1_fc
					mexcar1swap_fcflag = 3
				ENDIF 
			ENDIF

			IF mexcar1_fcflag = 1
				IF NOT IS_CAR_DEAD mexcar1_fc
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR mexcar1_fc
						MARK_CAR_AS_NO_LONGER_NEEDED mexcar1_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fc
						REMOVE_BLIP mexcar1_fcblip 
						mexcar1_fcflag = 2
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR mexcar1_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fc
					MARK_CAR_AS_NO_LONGER_NEEDED mexcar1_fc
					REMOVE_BLIP mexcar1_fcblip 
					mexcar1_fcflag = 2
				ENDIF
			ENDIF

			IF mexcar2swap_fcflag = 1
				IF NOT IS_CAR_DEAD mexcar2_fc
					IF NOT IS_CHAR_DEAD gang7_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang8_fc
							IF IS_CHAR_IN_CAR gang8_fc mexcar2_fc
								GET_DRIVER_OF_CAR mexcar2_fc driverofcar_fc
								IF driverofcar_fc = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT gang8_fc mexcar2_fc
									mexcar2swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR mexcar2_fc
							mexcar2swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF mexcar2swap_fcflag = 2
				IF IS_CHAR_DEAD gang8_fc
					STOP_PLAYBACK_RECORDED_CAR mexcar2_fc
					mexcar2swap_fcflag = 3
				ENDIF 
			ENDIF

			IF mexcar2_fcflag = 1
				IF NOT IS_CAR_DEAD mexcar2_fc
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR mexcar2_fc
						MARK_CAR_AS_NO_LONGER_NEEDED mexcar2_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang8_fc
						REMOVE_BLIP mexcar2_fcblip 
						mexcar2_fcflag = 2
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR mexcar2_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang7_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang8_fc
					MARK_CAR_AS_NO_LONGER_NEEDED mexcar2_fc
					REMOVE_BLIP mexcar2_fcblip 
					mexcar2_fcflag = 2
				ENDIF
			ENDIF

			IF mexcar3swap_fcflag = 1
				IF NOT IS_CAR_DEAD mexcar3_fc
					IF NOT IS_CHAR_DEAD gang17_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang18_fc
							IF IS_CHAR_IN_CAR gang18_fc mexcar3_fc
								GET_DRIVER_OF_CAR mexcar3_fc driverofcar_fc
								IF driverofcar_fc = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT gang18_fc mexcar3_fc
									mexcar3swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR mexcar3_fc
							mexcar3swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF mexcar3swap_fcflag = 2
				IF IS_CHAR_DEAD gang18_fc
					STOP_PLAYBACK_RECORDED_CAR mexcar3_fc
					mexcar3swap_fcflag = 3
				ENDIF 
			ENDIF

			IF mexcar3_fcflag = 1
				IF NOT IS_CAR_DEAD mexcar3_fc
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR mexcar3_fc
						MARK_CAR_AS_NO_LONGER_NEEDED mexcar3_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang17_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang18_fc
						REMOVE_BLIP mexcar3_fcblip 
						mexcar3_fcflag = 2
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR mexcar3_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang17_fc
					MARK_CHAR_AS_NO_LONGER_NEEDED gang18_fc
					MARK_CAR_AS_NO_LONGER_NEEDED mexcar3_fc
					REMOVE_BLIP mexcar3_fcblip 
					mexcar3_fcflag = 2
				ENDIF
			ENDIF
			//bikes
			IF bike1swap_fcflag = 1
				IF NOT IS_CAR_DEAD bike1_fc
					IF NOT IS_CHAR_DEAD gang9_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang10_fc
							IF IS_CHAR_IN_CAR gang10_fc bike1_fc
								GET_DRIVER_OF_CAR bike1_fc driverofcar_fc
								IF driverofcar_fc = -1
									GET_CAR_COORDINATES benz_fc player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD gang10_fc player_x player_y -10.0
									WARP_CHAR_INTO_CAR gang10_fc bike1_fc
									bike1swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike1_fc 18.0
							STOP_PLAYBACK_RECORDED_CAR bike1_fc
							SET_CAR_ROTATION_VELOCITY bike1_fc 3.5 7.3999 8.1003
							bike1swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike1swap_fcflag = 2
				IF IS_CHAR_DEAD gang10_fc
					STOP_PLAYBACK_RECORDED_CAR bike1_fc
					IF NOT IS_CAR_DEAD bike1_fc
						SET_CAR_FORWARD_SPEED bike1_fc 18.0
						SET_CAR_ROTATION_VELOCITY bike1_fc 3.5 7.3999 8.1003
					ENDIF
					bike1swap_fcflag = 3
				ENDIF 
			ENDIF
			IF bike1_fcflag = 1
				IF IS_CHAR_DEAD gang9_fc
					IF IS_CHAR_DEAD gang10_fc
					OR IS_CAR_DEAD bike1_fc
						STOP_PLAYBACK_RECORDED_CAR bike1_fc
						REMOVE_BLIP bike1_fcblip
						MARK_CAR_AS_NO_LONGER_NEEDED bike1_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang9_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fc
						bike1_fcflag = 2
					ENDIF
				ENDIF
			ENDIF

			IF bike2swap_fcflag = 1
				IF NOT IS_CAR_DEAD bike2_fc
					IF NOT IS_CHAR_DEAD gang11_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang12_fc
							IF IS_CHAR_IN_CAR gang12_fc bike2_fc
								GET_DRIVER_OF_CAR bike2_fc driverofcar_fc
								IF driverofcar_fc = -1
									GET_CAR_COORDINATES benz_fc player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD gang12_fc player_x player_y -10.0
									WARP_CHAR_INTO_CAR gang12_fc bike2_fc
									bike2swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike2_fc 18.0
							STOP_PLAYBACK_RECORDED_CAR bike2_fc
							SET_CAR_ROTATION_VELOCITY bike2_fc 3.5 7.3999 8.1003
							bike2swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike2swap_fcflag = 2
				IF IS_CHAR_DEAD gang12_fc
					STOP_PLAYBACK_RECORDED_CAR bike2_fc
					IF NOT IS_CAR_DEAD bike2_fc
						SET_CAR_FORWARD_SPEED bike2_fc 18.0
						SET_CAR_ROTATION_VELOCITY bike2_fc 3.5 7.3999 8.1003
					ENDIF
					bike2swap_fcflag = 3
				ENDIF 
			ENDIF
			IF bike2_fcflag = 1
				IF IS_CHAR_DEAD gang11_fc
					IF IS_CHAR_DEAD gang12_fc
					OR IS_CAR_DEAD bike2_fc
						STOP_PLAYBACK_RECORDED_CAR bike2_fc
						REMOVE_BLIP bike2_fcblip
						MARK_CAR_AS_NO_LONGER_NEEDED bike2_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fc
						bike2_fcflag = 2
					ENDIF
				ENDIF
			ENDIF

			IF bike3swap_fcflag = 1
				IF NOT IS_CAR_DEAD bike3_fc
					IF NOT IS_CHAR_DEAD gang13_fc
					ELSE
						IF NOT IS_CHAR_DEAD gang14_fc
							IF IS_CHAR_IN_CAR gang14_fc bike3_fc
								GET_DRIVER_OF_CAR bike3_fc driverofcar_fc
								IF driverofcar_fc = -1
									GET_CAR_COORDINATES benz_fc player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD gang14_fc player_x player_y -10.0
									WARP_CHAR_INTO_CAR gang14_fc bike3_fc
									bike3swap_fcflag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike3_fc 18.0
							STOP_PLAYBACK_RECORDED_CAR bike3_fc
							SET_CAR_ROTATION_VELOCITY bike3_fc 3.5 7.3999 8.1003
							bike3swap_fcflag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike3swap_fcflag = 2
				IF IS_CHAR_DEAD gang14_fc
					STOP_PLAYBACK_RECORDED_CAR bike3_fc
					IF NOT IS_CAR_DEAD bike3_fc
						SET_CAR_FORWARD_SPEED bike3_fc 18.0
						SET_CAR_ROTATION_VELOCITY bike3_fc 3.5 7.3999 8.1003
					ENDIF
					bike3swap_fcflag = 3
				ENDIF 
			ENDIF
			IF bike3_fcflag = 1
				IF IS_CHAR_DEAD gang13_fc
					IF IS_CHAR_DEAD gang14_fc
					OR IS_CAR_DEAD bike3_fc
						STOP_PLAYBACK_RECORDED_CAR bike3_fc
						REMOVE_BLIP bike3_fcblip
						MARK_CAR_AS_NO_LONGER_NEEDED bike3_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang13_fc
						MARK_CHAR_AS_NO_LONGER_NEEDED gang14_fc
						bike3_fcflag = 2
					ENDIF
				ENDIF
			ENDIF

			//audio for chase
			GOSUB process_audio_fc

			//imran
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 0
					audio_label_fc = SOUND_ROT4_MD //Motherfucker!
					$input_text_fc = ROT4_MD //Motherfucker!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 1
					audio_label_fc = SOUND_ROT4_NL //Take that pig bastard down!
					$input_text_fc = ROT4_NL //Take that pig bastard down!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF actiontext_fcflag = 1
				IF handlingudio_fcflag = 0
					IF progressaudio_fcflag = 2
						audio_label_fc = SOUND_ROT4_QA //Those firetrucks are indestructible!
						$input_text_fc = ROT4_QA //Those firetrucks are indestructible!
						GOSUB load_audio_fc
					ENDIF
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 3
					audio_label_fc = SOUND_ROT4_QB	//We ain’t gonna dent it!
					$input_text_fc = ROT4_QB	//We ain’t gonna dent it!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 4
					audio_label_fc = SOUND_ROT4_TF	//Watch our six!
					$input_text_fc = ROT4_TF	//Watch our six!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 5
					audio_label_fc = SOUND_ROT4_NR //Cops on our tail!
					$input_text_fc = ROT4_NR //Cops on our tail!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF actiontext_fcflag = 2
				IF handlingudio_fcflag = 0
					IF progressaudio_fcflag = 6
						audio_label_fc = SOUND_ROT4_NE	//Vagos rioters coming up!
						$input_text_fc = ROT4_NE	//Vagos rioters coming up!
						GOSUB load_audio_fc
					ENDIF
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 7
					audio_label_fc = SOUND_ROT4_TK	//This city’s gone nuts!
					$input_text_fc = ROT4_TK	//This city’s gone nuts!
					GOSUB load_audio_fc
				ENDIF
			ENDIF

			IF actiontext_fcflag = 3
				IF handlingudio_fcflag = 0
					IF progressaudio_fcflag = 8
						audio_label_fc = SOUND_ROT4_TM //I’ll keep up with Tenpenny, don’t you worry!!
						$input_text_fc = ROT4_TM //I’ll keep up with Tenpenny, don’t you worry!
						GOSUB load_audio_fc
					ENDIF
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 9
					audio_label_fc = SOUND_ROT4_TG //Concentrate on keepin g all these other lunatics off our case, CJ.
					$input_text_fc = ROT4_TG //Concentrate on keepin g all these other lunatics off our case, CJ.
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF actiontext_fcflag = 5
				IF handlingudio_fcflag = 0
					IF progressaudio_fcflag = 10
						audio_label_fc = SOUND_ROT4_NA	//Rioters on the bridge up ahead!
						$input_text_fc = ROT4_NA	//Rioters on the bridge up ahead!
						GOSUB load_audio_fc
					ENDIF
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 11
					audio_label_fc = SOUND_ROT4_NB	//They’re dropping shit from that bridge!
					$input_text_fc = ROT4_NB	//They’re dropping shit from that bridge!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 12
					audio_label_fc = SOUND_ROT4_TC	//Look out, CJ!
					$input_text_fc = ROT4_TC	//Look out, CJ!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 13
					audio_label_fc = SOUND_ROT4_TF //Watch our six!
					$input_text_fc = ROT4_TF //Watch our six!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
			IF handlingudio_fcflag = 0
				IF progressaudio_fcflag = 14
					audio_label_fc = SOUND_ROT4_TA	//Some rioters after us on bikes!
					$input_text_fc = ROT4_TA	//Some rioters after us on bikes!
					GOSUB load_audio_fc
				ENDIF
			ENDIF
							
		ENDIF
	ENDIF
ENDIF
	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////   Second Shooting Section   ///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF finalerails_fcflag = 2

	IF NOT IS_CAR_DEAD firetruck_fc

		IF NOT IS_CAR_DEAD benz_fc
			//car health
			GET_CAR_HEALTH benz_fc carhealth_fc
			carhealth_fc = carhealth_fc - 12000

			IF carhealth_fc <= 0 
				carhealth_fc = 0
				SET_CAR_PROOFS benz_fc FALSE FALSE FALSE FALSE FALSE
				SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
				IF NOT IS_CHAR_DEAD sweet
					TASK_DIE sweet
				ENDIF
				EXPLODE_CAR benz_fc
				PRINT_NOW RM4_53 5000 1 //~r~You wrecked your car
				GOTO mission_finaleC_failed
			ELSE
				carhealth_fc = carhealth_fc / 80
			ENDIF

			IF IS_CHAR_DEAD sweet
				PRINT_NOW RM4_53 5000 1 //~r~You wrecked your car
				GOTO mission_finaleC_failed
			ENDIF

		ENDIF


		IF lastrails_fcflag  = 0
			//delete cars and bikes and everything that was created

			copcar1_fcflag = 0
			copcar1swap_fcflag = 0
			copcar2_fcflag = 0
			copcar2swap_fcflag = 0
			mexcar1_fcflag = 0
			mexcar1swap_fcflag = 0
			bike1_fcflag = 0
			bike1swap_fcflag = 0
			bike2_fcflag = 0
			bike2swap_fcflag = 0

			REMOVE_BLIP mexcar1_fcblip
			REMOVE_BLIP mexcar2_fcblip
			REMOVE_BLIP mexcar3_fcblip
			REMOVE_BLIP ecar1_fcblip
			REMOVE_BLIP ecar2_fcblip
			REMOVE_BLIP bike1_fcblip
			REMOVE_BLIP bike2_fcblip
			REMOVE_BLIP bike3_fcblip
			REMOVE_BLIP copcar1_fcblip
			REMOVE_BLIP copcar2_fcblip

			DELETE_CAR bike1_fc
			DELETE_CHAR gang9_fc
			DELETE_CHAR gang10_fc

			DELETE_CAR bike2_fc
			DELETE_CHAR gang11_fc
			DELETE_CHAR gang12_fc

			DELETE_CAR bike3_fc
			DELETE_CHAR gang13_fc
			DELETE_CHAR gang14_fc

			DELETE_CAR copcar1_fc
			DELETE_CAR copcar2_fc
			DELETE_CAR mexcar1_fc
			DELETE_CAR mexcar2_fc
			DELETE_CAR mexcar3_fc
			DELETE_CAR ecar1_fc
			DELETE_CAR ecar2_fc


			CREATE_CAR SANCHEZ 2375.0 -1918.0 12.5 bike1_fc
			SET_CAR_HEADING bike1_fc 176.0
			CREATE_CHAR_INSIDE_CAR bike1_fc PEDTYPE_MISSION1 LSV3 gang9_fc
			SET_CHAR_DECISION_MAKER gang9_fc carterc_dm
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang9_fc TRUE
			CREATE_CHAR_AS_PASSENGER bike1_fc PEDTYPE_MISSION1 LSV3 0 gang10_fc
			SET_CHAR_HEALTH gang10_fc 10
			SET_CHAR_DECISION_MAKER gang10_fc carterc_dm
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang10_fc TRUE
			GIVE_WEAPON_TO_CHAR gang10_fc WEAPONTYPE_MICRO_UZI 99999
			SET_CAR_ONLY_DAMAGED_BY_PLAYER bike1_fc TRUE
			SET_CAR_PROOFS bike1_fc FALSE TRUE TRUE FALSE FALSE

			CREATE_CAR SANCHEZ 2377.0 -1911.0 12.5 bike2_fc
			SET_CAR_HEADING bike2_fc 186.0
			CREATE_CHAR_INSIDE_CAR bike2_fc PEDTYPE_MISSION1 LSV3 gang11_fc
			SET_CHAR_DECISION_MAKER gang11_fc carterc_dm
			SET_CHAR_HEALTH gang11_fc 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang11_fc TRUE
			CREATE_CHAR_AS_PASSENGER bike2_fc PEDTYPE_MISSION1 LSV3 0 gang12_fc
			SET_CHAR_DECISION_MAKER gang12_fc carterc_dm
			SET_CHAR_HEALTH gang12_fc 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang12_fc TRUE
			GIVE_WEAPON_TO_CHAR gang12_fc WEAPONTYPE_MICRO_UZI 99999
			SET_CAR_ONLY_DAMAGED_BY_PLAYER bike2_fc TRUE
			SET_CAR_PROOFS bike2_fc FALSE TRUE TRUE FALSE FALSE

		
			SET_FIXED_CAMERA_POSITION 2347.5261 -1914.9288 15.1567 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2348.2241 -1915.6173 14.9604 JUMP_CUT

			lastrails_fcflag = 1
		ENDIF

	ENDIF

	IF NOT IS_CAR_DEAD firetruck_fc
		IF NOT IS_CAR_DEAD benz_fc

			//rioters
			IF rioters2_fcflag = 0
				CREATE_CHAR PEDTYPE_MISSION1 MALE01 2300.89 -1947.97 24.5 rioter1_fc
				SET_CHAR_HEADING rioter1_fc 329.17
				GIVE_WEAPON_TO_CHAR rioter1_fc WEAPONTYPE_MOLOTOV 9999
				SET_CHAR_PROOFS rioter1_fc FALSE TRUE FALSE FALSE FALSE
				SET_CHAR_HEALTH rioter1_fc 10
				SET_CHAR_DECISION_MAKER rioter1_fc carterc_dm
				IF NOT IS_CHAR_DEAD tenpenny_fc
					enemy_fc = rioter1_fc
					enemytarget_fc = tenpenny_fc
					GOSUB chasekill_fclabel
				ENDIF

				CREATE_CHAR PEDTYPE_MISSION1 MALE01 2302.33 -1925.32 12.6 rioter2_fc
				SET_CHAR_HEADING rioter2_fc 311.22
				GIVE_WEAPON_TO_CHAR rioter2_fc WEAPONTYPE_MOLOTOV 9999
				SET_CHAR_PROOFS rioter2_fc FALSE TRUE FALSE FALSE FALSE
				SET_CHAR_HEALTH rioter2_fc 10
				SET_CHAR_DECISION_MAKER rioter2_fc carterc_dm
				enemy_fc = rioter2_fc
				enemytarget_fc = scplayer
				GOSUB chasekill_fclabel

				CREATE_CHAR PEDTYPE_MISSION1 MALE01 2324.569 -1951.436 20.59 rioter3_fc
				SET_CHAR_HEADING rioter3_fc 44.92
				GIVE_WEAPON_TO_CHAR rioter3_fc WEAPONTYPE_MOLOTOV 9999
				SET_CHAR_PROOFS rioter3_fc FALSE TRUE FALSE FALSE FALSE
				SET_CHAR_HEALTH rioter3_fc 10
				SET_CHAR_DECISION_MAKER rioter3_fc carterc_dm
				IF NOT IS_CHAR_DEAD tenpenny_fc
					enemy_fc = rioter3_fc
					enemytarget_fc = tenpenny_fc
					GOSUB chasekill_fclabel
				ENDIF

				CREATE_CHAR PEDTYPE_MISSION1 MALE01 2362.397 -1982.99 18.4 rioter4_fc
				SET_CHAR_HEADING rioter4_fc 44.93
				GIVE_WEAPON_TO_CHAR rioter4_fc WEAPONTYPE_MOLOTOV 9999
				SET_CHAR_PROOFS rioter4_fc FALSE TRUE FALSE FALSE FALSE
				SET_CHAR_HEALTH rioter4_fc 10
				SET_CHAR_DECISION_MAKER rioter4_fc carterc_dm
				enemy_fc = rioter4_fc
				enemytarget_fc = scplayer
				GOSUB chasekill_fclabel
				rioters2_fcflag = 1
			ENDIF




			IF lastrails_fcflag = 1

				START_PLAYBACK_RECORDED_CAR firetruck_fc 241 
				START_PLAYBACK_RECORDED_CAR benz_fc 242

				IF NOT IS_CAR_DEAD bike1_fc
					START_PLAYBACK_RECORDED_CAR bike1_fc 243
					IF NOT IS_CHAR_DEAD gang10_fc
						TASK_DRIVE_BY gang10_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
					ENDIF
					ADD_BLIP_FOR_CAR bike1_fc bike1_fcblip
					CHANGE_BLIP_DISPLAY bike1_fcblip BLIP_ONLY
					bike1_fcflag = 1
					bike1swap_fcflag = 1
				ENDIF

				IF NOT IS_CAR_DEAD bike2_fc
					START_PLAYBACK_RECORDED_CAR bike2_fc 244
					IF NOT IS_CHAR_DEAD gang12_fc
						TASK_DRIVE_BY gang12_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
					ENDIF
					ADD_BLIP_FOR_CAR bike2_fc bike2_fcblip
					CHANGE_BLIP_DISPLAY bike2_fcblip BLIP_ONLY
					bike2_fcflag = 1
					bike2swap_fcflag = 1
				ENDIF

				CREATE_CAR COPCARLA 2337.0 -2013.0 12.5 copcar1_fc
				SET_CAR_HEADING copcar1_fc 325.0
				SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar1_fc TRUE
				CREATE_CHAR_INSIDE_CAR copcar1_fc PEDTYPE_MISSION1 LAPD1 cop1_fc
				SET_CHAR_DECISION_MAKER cop1_fc carterc_dm
				SET_CHAR_SUFFERS_CRITICAL_HITS cop1_fc FALSE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop1_fc TRUE
				CREATE_CHAR_AS_PASSENGER copcar1_fc PEDTYPE_MISSION1 LAPD1 0 cop2_fc
				SET_CHAR_DECISION_MAKER cop2_fc carterc_dm
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop2_fc TRUE
				GIVE_WEAPON_TO_CHAR cop2_fc WEAPONTYPE_MICRO_UZI 9999
				SET_CAR_PROOFS copcar1_fc FALSE TRUE TRUE FALSE FALSE

				CREATE_CAR COPCARLA 2266.0 -1970.0 12.5 copcar2_fc
				SET_CAR_HEADING copcar2_fc 178.0
				SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar2_fc TRUE
				CREATE_CHAR_INSIDE_CAR copcar2_fc PEDTYPE_MISSION1 LAPD1 cop3_fc
				SET_CHAR_DECISION_MAKER cop3_fc carterc_dm
				SET_CHAR_SUFFERS_CRITICAL_HITS cop3_fc FALSE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop3_fc TRUE
				CREATE_CHAR_AS_PASSENGER copcar2_fc PEDTYPE_MISSION1 LAPD1 0 cop4_fc
				SET_CHAR_DECISION_MAKER cop4_fc carterc_dm
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop4_fc TRUE
				GIVE_WEAPON_TO_CHAR cop4_fc WEAPONTYPE_MICRO_UZI 9999
				SET_CAR_PROOFS copcar2_fc FALSE TRUE TRUE FALSE FALSE

				TIMERB = 0
				lastrails_fcflag = 2
				playercontrol_fcflag = 1
			ENDIF

			IF playercontrol_fcflag = 1
				IF TIMERB > 6000
					CLEAR_MISSION_AUDIO 1
					CLEAR_PRINTS
					handlingudio_fcflag = 0
					progressaudio_fcflag = 0
					actiontext_fcflag = 0
					RESTORE_CAMERA
					SET_PLAYER_CONTROL PLAYER1 ON
					SWITCH_WIDESCREEN OFF
					TASK_DRIVE_BY scplayer -1 -1 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 120.5 200.0
					IF NOT IS_CAR_DEAD benz_fc
						SET_CAR_PROOFS benz_fc FALSE TRUE TRUE TRUE TRUE
					ENDIF
					playercontrol_fcflag = 2
				ENDIF
			ENDIF

			IF explodecar_fcflag = 0
				IF lastrails_fcflag > 4
					IF NOT IS_CAR_DEAD copcar1_fc
						IF LOCATE_CAR_2D copcar1_fc 2210.19 -1864.8 5.0 5.0 FALSE
							SET_CAR_HEALTH copcar1_fc 100
							TIMERB = 0
							explodecar_fcflag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF explodecar_fcflag = 1
				IF NOT IS_CAR_DEAD copcar1_fc
					IF TIMERB > 1250
						EXPLODE_CAR copcar1_fc
						explodecar_fcflag = 2
					ENDIF
				ENDIF
			ENDIF

			IF lastrails_fcflag = 2
				IF LOCATE_CAR_2D firetruck_fc 2347.96 -1972.42 20.0 20.0 FALSE
					actiontext_fcflag = 1
					IF NOT IS_CAR_DEAD copcar1_fc
						START_PLAYBACK_RECORDED_CAR copcar1_fc 245
						SWITCH_CAR_SIREN copcar1_fc ON
						IF NOT IS_CHAR_DEAD cop2_fc
							TASK_DRIVE_BY cop2_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 50
						ENDIF
						ADD_BLIP_FOR_CAR copcar1_fc copcar1_fcblip
						CHANGE_BLIP_DISPLAY copcar1_fcblip BLIP_ONLY
						copcar1_fcflag = 1
						copcar1swap_fcflag = 1
					ENDIF
					IF NOT IS_CAR_DEAD copcar2_fc
						SWITCH_CAR_SIREN copcar2_fc ON
						START_PLAYBACK_RECORDED_CAR copcar2_fc 246
						IF NOT IS_CHAR_DEAD cop4_fc
							TASK_DRIVE_BY cop4_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 10
						ENDIF
						ADD_BLIP_FOR_CAR copcar2_fc copcar2_fcblip
						CHANGE_BLIP_DISPLAY copcar2_fcblip BLIP_ONLY
						copcar2_fcflag = 1
						copcar2swap_fcflag = 1
					ENDIF

					CREATE_CAR COPCARLA 2283.0 -2077.0 14.0 copcar4_fc
					SET_CAR_HEADING copcar4_fc 48.0
					SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar4_fc TRUE
					CREATE_CHAR_INSIDE_CAR copcar4_fc PEDTYPE_MISSION1 LAPD1 cop7_fc
					SET_CHAR_DECISION_MAKER cop7_fc carterc_dm
					SET_CHAR_SUFFERS_CRITICAL_HITS cop7_fc FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop7_fc TRUE
					CREATE_CHAR_AS_PASSENGER copcar4_fc PEDTYPE_MISSION1 LAPD1 0 cop8_fc
					SET_CHAR_DECISION_MAKER cop8_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop8_fc TRUE
					GIVE_WEAPON_TO_CHAR cop8_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CAR_PROOFS copcar4_fc FALSE TRUE TRUE FALSE FALSE
					SET_CAR_HEALTH copcar4_fc 700

					lastrails_fcflag = 3
				ENDIF
			ENDIF

			IF lastrails_fcflag = 3
				IF LOCATE_CAR_2D benz_fc 2260.92 -2021.7 25.0 25.0 FALSE
					actiontext_fcflag = 2
					IF NOT IS_CAR_DEAD copcar4_fc
						START_PLAYBACK_RECORDED_CAR copcar4_fc 249
						SWITCH_CAR_SIREN copcar4_fc ON
						IF NOT IS_CHAR_DEAD cop8_fc
							TASK_DRIVE_BY cop8_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 50
						ENDIF
						ADD_BLIP_FOR_CAR copcar4_fc copcar4_fcblip
						CHANGE_BLIP_DISPLAY copcar4_fcblip BLIP_ONLY
						copcar4_fcflag = 1
						copcar4swap_fcflag = 1
					ENDIF

					DELETE_CHAR rioter1_fc
					DELETE_CHAR rioter2_fc
					DELETE_CHAR rioter3_fc
					DELETE_CHAR rioter4_fc

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2226.86 -1966.5 24.5 rioter1_fc
					SET_CHAR_HEADING rioter1_fc 329.17
					GIVE_WEAPON_TO_CHAR rioter1_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter1_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter1_fc 10
					SET_CHAR_DECISION_MAKER rioter1_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter1_fc TRUE
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter1_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2224.944 -1977.119 24.5 rioter2_fc
					SET_CHAR_HEADING rioter2_fc 311.22
					GIVE_WEAPON_TO_CHAR rioter2_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter2_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter2_fc 10
					SET_CHAR_DECISION_MAKER rioter2_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter2_fc TRUE
					IF NOT IS_CHAR_DEAD cop3_fc
						enemy_fc = rioter2_fc
						enemytarget_fc = cop3_fc
						GOSUB chasekill_fclabel
					ELSE
						enemy_fc = rioter2_fc
						enemytarget_fc = scplayer
						GOSUB chasekill_fclabel
					ENDIF
					
					CREATE_OBJECT la_fuckcar2 2227.617 -1894.91 12.2 gangcar1_fc
					SET_OBJECT_DYNAMIC gangcar1_fc TRUE
					SET_OBJECT_HEADING gangcar1_fc 352.399
					//
					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2230.15 -1891.29 12.5 rioter3_fc
					SET_CHAR_HEADING rioter3_fc 329.17
					GIVE_WEAPON_TO_CHAR rioter3_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter3_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter3_fc 10
					SET_CHAR_DECISION_MAKER rioter3_fc carterc_dm
					enemy_fc = rioter3_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2229.57 -1898.48 12.415 rioter4_fc
					SET_CHAR_HEADING rioter4_fc 311.22
					GIVE_WEAPON_TO_CHAR rioter4_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter4_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter4_fc 10
					SET_CHAR_DECISION_MAKER rioter4_fc carterc_dm
					IF NOT IS_CHAR_DEAD cop3_fc
						enemy_fc = rioter4_fc
						enemytarget_fc = cop3_fc
						GOSUB chasekill_fclabel
					ELSE
						enemy_fc = rioter4_fc
						enemytarget_fc = scplayer
						GOSUB chasekill_fclabel
					ENDIF
					
					CREATE_OBJECT la_fuckcar2 2222.927 -1973.632 12.25 gangcar5_fc
					SET_OBJECT_DYNAMIC gangcar5_fc TRUE
					SET_OBJECT_HEADING gangcar5_fc 352.399

					lastrails_fcflag = 4
				ENDIF
			ENDIF

			IF lastrails_fcflag = 4
				IF LOCATE_CAR_2D firetruck_fc 2215.75 -1931.8 25.0 25.0 FALSE
					actiontext_fcflag = 3		
					DELETE_CAR bike1_fc
					DELETE_CHAR gang9_fc
					DELETE_CHAR	gang10_fc
					REMOVE_BLIP bike1_fcblip
					DELETE_CAR bike2_fc
					DELETE_CHAR gang11_fc
					DELETE_CHAR	gang12_fc
					REMOVE_BLIP bike2_fcblip
					MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
					MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ

					CREATE_CAR COPCARLA 2169.74 -1726.16 12.5 copcar3_fc
					SET_CAR_HEADING copcar3_fc 268.0
					SET_CAR_ONLY_DAMAGED_BY_PLAYER copcar3_fc TRUE
					CREATE_CHAR_INSIDE_CAR copcar3_fc PEDTYPE_MISSION1 LAPD1 cop5_fc
					SET_CHAR_DECISION_MAKER cop5_fc carterc_dm
					SET_CHAR_SUFFERS_CRITICAL_HITS cop5_fc FALSE
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop5_fc TRUE
					CREATE_CHAR_AS_PASSENGER copcar3_fc PEDTYPE_MISSION1 LAPD1 0 cop6_fc
					SET_CHAR_DECISION_MAKER cop6_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop6_fc TRUE
					GIVE_WEAPON_TO_CHAR cop6_fc WEAPONTYPE_MICRO_UZI 9999
					SET_CAR_PROOFS copcar3_fc FALSE TRUE TRUE FALSE FALSE
					SET_CAR_HEALTH copcar3_fc 600

					lastrails_fcflag = 5
				ENDIF
			ENDIF

			IF lastrails_fcflag = 5
				IF LOCATE_CAR_2D firetruck_fc 2215.93 -1765.08 20.0 20.0 FALSE

					IF NOT IS_CAR_DEAD copcar3_fc
						START_PLAYBACK_RECORDED_CAR copcar3_fc 248
						SWITCH_CAR_SIREN copcar3_fc ON
						IF NOT IS_CHAR_DEAD cop6_fc
							TASK_DRIVE_BY cop6_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 50
						ENDIF
						ADD_BLIP_FOR_CAR copcar3_fc copcar3_fcblip
						CHANGE_BLIP_DISPLAY copcar3_fcblip BLIP_ONLY
						copcar3_fcflag = 1
						copcar3swap_fcflag = 1
					ENDIF

					CREATE_CAR COPCARLA 2323.318 -1656.886 12.53 mexcar1_fc
					SET_CAR_HEADING mexcar1_fc 272.4
					SET_CAR_ONLY_DAMAGED_BY_PLAYER mexcar1_fc TRUE
					CREATE_CHAR_INSIDE_CAR mexcar1_fc PEDTYPE_MISSION1 LAPD1 gang5_fc
					SET_CHAR_DECISION_MAKER gang5_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang5_fc TRUE
					CREATE_CHAR_AS_PASSENGER mexcar1_fc PEDTYPE_MISSION1 LAPD1 0 gang6_fc
					SET_CHAR_DECISION_MAKER gang6_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER gang6_fc TRUE
					GIVE_WEAPON_TO_CHAR gang6_fc WEAPONTYPE_MICRO_UZI 99999
					SET_CAR_PROOFS mexcar1_fc FALSE TRUE TRUE FALSE FALSE


					lastrails_fcflag = 6
				ENDIF
			ENDIF

			IF lastrails_fcflag = 6
				IF LOCATE_CAR_2D firetruck_fc 2343.45 -1658.96 30.0 30.0 FALSE
					actiontext_fcflag = 4
					IF NOT IS_CAR_DEAD mexcar1_fc
						START_PLAYBACK_RECORDED_CAR mexcar1_fc 247
						IF NOT IS_CHAR_DEAD gang6_fc
							TASK_DRIVE_BY gang6_fc -1 benz_fc 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 50
						ENDIF
						ADD_BLIP_FOR_CAR mexcar1_fc mexcar1_fcblip
						CHANGE_BLIP_DISPLAY mexcar1_fcblip BLIP_ONLY
						mexcar1_fcflag = 1
						mexcar1swap_fcflag = 1
					ENDIF

					lastrails_fcflag = 7
				ENDIF
			ENDIF

			//player completed
			IF lastrails_fcflag = 7
				IF LOCATE_CAR_2D firetruck_fc 2431.0 -1644.0 30.0 30.0 FALSE
					finalerails_fcflag = 3
					lastrails_fcflag = 8
				ENDIF
			ENDIF
			
			//rioters
			IF rioters2_fcflag = 1
				IF LOCATE_CAR_2D benz_fc 2215.9 -1791.82 20.0 20.0 FALSE
					
					DELETE_CHAR rioter1_fc
					DELETE_CHAR rioter2_fc
					DELETE_CHAR rioter3_fc
					DELETE_CHAR rioter4_fc
					DELETE_OBJECT gangcar1_fc
					DELETE_OBJECT gangcar5_fc

					CREATE_OBJECT la_fuckcar2 2237.0442 -1744.5001 12.5425 gangcar1_fc
					SET_OBJECT_DYNAMIC gangcar1_fc TRUE
					SET_OBJECT_HEADING gangcar1_fc 1.2003

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2239.1538 -1747.3259 12.5420 rioter1_fc
					SET_CHAR_HEADING rioter1_fc 329.17
					GIVE_WEAPON_TO_CHAR rioter1_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter1_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter1_fc 10
					SET_CHAR_DECISION_MAKER rioter1_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter1_fc TRUE
					enemy_fc = rioter1_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel
					//
					CREATE_OBJECT la_fuckcar2 2297.8909 -1742.1051 12.5469 gangcar3_fc
					SET_OBJECT_DYNAMIC gangcar3_fc TRUE
					SET_OBJECT_HEADING gangcar3_fc 185.7601

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2300.6187 -1745.2543 12.5469 rioter2_fc
					SET_CHAR_HEADING rioter2_fc 311.22
					GIVE_WEAPON_TO_CHAR rioter2_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter2_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter2_fc 10
					SET_CHAR_DECISION_MAKER rioter2_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter2_fc TRUE
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter2_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF
					//
					CREATE_OBJECT la_fuckcar2 2355.7935 -1734.0632 12.3828 gangcar5_fc
					SET_OBJECT_DYNAMIC gangcar5_fc TRUE
					SET_OBJECT_HEADING gangcar5_fc 1.2003

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2357.7156 -1730.0507 12.3828 rioter3_fc
					SET_CHAR_HEADING rioter3_fc 329.17
					GIVE_WEAPON_TO_CHAR rioter3_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter3_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter3_fc 10
					SET_CHAR_DECISION_MAKER rioter3_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter3_fc TRUE
					enemy_fc = rioter3_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel
					//
					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2365.0671 -1758.1493 12.5469 rioter4_fc
					SET_CHAR_HEADING rioter4_fc 329.17
					GIVE_WEAPON_TO_CHAR rioter4_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter4_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter4_fc 10
					SET_CHAR_DECISION_MAKER rioter4_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter4_fc TRUE
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter4_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2308.3191 -1721.5842 12.7420 rioter5_fc
					SET_CHAR_HEADING rioter5_fc 329.17
					GIVE_WEAPON_TO_CHAR rioter5_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter5_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter5_fc 10
					SET_CHAR_DECISION_MAKER rioter5_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter5_fc TRUE
					enemy_fc = rioter5_fc
					enemytarget_fc = scplayer
					GOSUB chasekill_fclabel

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2308.3191 -1721.5842 12.7420 rioter6_fc
					SET_CHAR_HEADING rioter6_fc 329.17
					GIVE_WEAPON_TO_CHAR rioter6_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter6_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter6_fc 10
					SET_CHAR_DECISION_MAKER rioter6_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter6_fc TRUE
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter6_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					rioters2_fcflag = 2
				ENDIF
			ENDIF

			IF rioters2_fcflag = 2
				IF LOCATE_CAR_2D benz_fc 2342.56 -1603.76 20.0 20.0 FALSE
					DELETE_CHAR	rioter1_fc
					DELETE_CHAR	rioter2_fc
					DELETE_CHAR rioter3_fc
					DELETE_CHAR rioter4_fc
					DELETE_CHAR rioter5_fc
					DELETE_CHAR rioter6_fc
					DELETE_OBJECT gangcar1_fc
					DELETE_OBJECT gangcar3_fc
					DELETE_OBJECT gangcar5_fc
					rioters2_fcflag = 3
				ENDIF
			ENDIF

			//on the bridge
			IF rioters2_fcflag = 2
				IF LOCATE_CAR_2D benz_fc 2430.81 -1595.72 20.0 20.0 FALSE

					CREATE_OBJECT la_fuckcar2 2430.76 -1653.03 25.34 gangcar1_fc
					SET_OBJECT_DYNAMIC gangcar1_fc TRUE
					SET_OBJECT_HEADING gangcar1_fc 268.0927

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2426.7 -1653.77 25.595 rioter1_fc
					SET_CHAR_HEADING rioter1_fc 4.29
					GIVE_WEAPON_TO_CHAR rioter1_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter1_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter1_fc 10
					SET_CHAR_DECISION_MAKER rioter1_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter1_fc TRUE
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter1_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF

					CREATE_CHAR PEDTYPE_MISSION1 MALE01 2435.959 -1653.318 25.64 rioter2_fc
					SET_CHAR_HEADING rioter2_fc 0.0
					GIVE_WEAPON_TO_CHAR rioter2_fc WEAPONTYPE_MOLOTOV 9999
					SET_CHAR_PROOFS rioter2_fc FALSE TRUE FALSE FALSE FALSE
					SET_CHAR_HEALTH rioter2_fc 10
					SET_CHAR_DECISION_MAKER rioter2_fc carterc_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER rioter2_fc TRUE
					IF NOT IS_CHAR_DEAD tenpenny_fc
						enemy_fc = rioter2_fc
						enemytarget_fc = tenpenny_fc
						GOSUB chasekill_fclabel
					ENDIF
					rioters2_fcflag = 3
				ENDIF
			ENDIF

			//imran
			IF lastrails_fcflag < 8
				IF playercontrol_fcflag = 2

					//audio for chase
					GOSUB process_audio_fc

					//imran
					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 0
							audio_label_fc = SOUND_ROT4_TC //Look out, CJ!
							$input_text_fc = ROT4_TC //Look out, CJ!
							GOSUB load_audio_fc
						ENDIF
					ENDIF
					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 1
							audio_label_fc = SOUND_ROT4_TB	//Crazy bikers on a rampage!
							$input_text_fc = ROT4_TB	//Crazy bikers on a rampage!
							GOSUB load_audio_fc
						ENDIF
					ENDIF
					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 2
							audio_label_fc = SOUND_ROT4_TH	//I’m on Tenpenny, he ain’t getting away!
							$input_text_fc = ROT4_TH	//I’m on Tenpenny, he ain’t getting away!
							GOSUB load_audio_fc
						ENDIF
					ENDIF

					IF actiontext_fcflag = 1
						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 3
								audio_label_fc = SOUND_ROT4_NR //Cops on our tail!
								$input_text_fc = ROT4_NR //Cops on our tail!
								GOSUB load_audio_fc
							ENDIF
						ENDIF
					ENDIF

					IF actiontext_fcflag = 2
						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 4
								audio_label_fc = SOUND_ROT4_NS	//Cops up ahead!
								$input_text_fc = ROT4_NS	//Cops up ahead!
								GOSUB load_audio_fc
							ENDIF
						ENDIF
					ENDIF
					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 5
							audio_label_fc = SOUND_ROT4_TJ //Where did all these assholes come from?
							$input_text_fc = ROT4_TJ //Where did all these assholes come from?
							GOSUB load_audio_fc
						ENDIF
					ENDIF

					IF actiontext_fcflag = 3
						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 6
								audio_label_fc = SOUND_ROT4_SB //Down in flames! DOWN IN FLAMES!
								$input_text_fc = ROT4_SB //Down in flames! DOWN IN FLAMES!
								GOSUB load_audio_fc
							ENDIF
						ENDIF
					ENDIF
					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 7
							audio_label_fc = SOUND_ROT4_PC //That’s my dog!
							$input_text_fc = ROT4_PC //That’s my dog!
							GOSUB load_audio_fc
						ENDIF
					ENDIF

					IF actiontext_fcflag = 4
						IF handlingudio_fcflag = 0
							IF progressaudio_fcflag = 8
								audio_label_fc = SOUND_ROT4_OA	//It’s over, Tenpenny, OVER!
								$input_text_fc = ROT4_OA	//It’s over, Tenpenny, OVER!
								GOSUB load_audio_fc
							ENDIF
						ENDIF
					ENDIF
					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 9
							audio_label_fc = SOUND_ROT4_OD //I’m gonna stop you, motherfucker!
							$input_text_fc = ROT4_OD //I’m gonna stop you, motherfucker!
							GOSUB load_audio_fc
						ENDIF
					ENDIF

					IF actiontext_fcflag = 4
						IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2431.32 -1528.48 20.0 20.0 FALSE
							IF handlingudio_fcflag = 0
								IF progressaudio_fcflag = 10
									audio_label_fc = SOUND_ROT4_RA	//He’s losing control!
									$input_text_fc = ROT4_RA	//He’s losing control!
									GOSUB load_audio_fc
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					IF handlingudio_fcflag = 0
						IF progressaudio_fcflag = 11
							audio_label_fc = SOUND_ROT4_RD	//We’ve got the motherfucker!
							$input_text_fc = ROT4_RD	//We’ve got the motherfucker!
							GOSUB load_audio_fc
						ENDIF
					ENDIF

				ENDIF
			ENDIF



		ENDIF
	ENDIF



	//cars
	IF copcar1swap_fcflag = 1
		IF NOT IS_CAR_DEAD copcar1_fc
			IF NOT IS_CHAR_DEAD cop1_fc
			ELSE
				IF NOT IS_CHAR_DEAD cop2_fc
					IF IS_CHAR_IN_CAR cop2_fc copcar1_fc
						GET_DRIVER_OF_CAR copcar1_fc driverofcar_fc
						IF driverofcar_fc = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop2_fc copcar1_fc
							copcar1swap_fcflag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR copcar1_fc
					copcar1swap_fcflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF copcar1swap_fcflag = 2
		IF IS_CHAR_DEAD cop2_fc
			STOP_PLAYBACK_RECORDED_CAR copcar1_fc
			copcar1swap_fcflag = 3
		ENDIF 
	ENDIF

	IF copcar1_fcflag = 1
		IF NOT IS_CAR_DEAD copcar1_fc
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar1_fc
				MARK_CAR_AS_NO_LONGER_NEEDED copcar1_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop1_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop2_fc
				REMOVE_BLIP copcar1_fcblip 
				copcar1_fcflag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR copcar1_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop1_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop2_fc
			MARK_CAR_AS_NO_LONGER_NEEDED copcar1_fc
			REMOVE_BLIP copcar1_fcblip 
			copcar1_fcflag = 2
		ENDIF
	ENDIF
	//
	IF copcar2swap_fcflag = 1
		IF NOT IS_CAR_DEAD copcar2_fc
			IF NOT IS_CHAR_DEAD cop3_fc
			ELSE
				IF NOT IS_CHAR_DEAD cop4_fc
					IF IS_CHAR_IN_CAR cop4_fc copcar2_fc
						GET_DRIVER_OF_CAR copcar2_fc driverofcar_fc
						IF driverofcar_fc = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop4_fc copcar2_fc
							copcar2swap_fcflag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR copcar2_fc
					copcar2swap_fcflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF copcar2swap_fcflag = 2
		IF IS_CHAR_DEAD cop4_fc
			STOP_PLAYBACK_RECORDED_CAR copcar2_fc
			copcar2swap_fcflag = 3
		ENDIF 
	ENDIF

	IF copcar2_fcflag = 1
		IF NOT IS_CAR_DEAD copcar2_fc
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar2_fc
				MARK_CAR_AS_NO_LONGER_NEEDED copcar2_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop3_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop4_fc
				REMOVE_BLIP copcar2_fcblip 
				copcar2_fcflag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR copcar2_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop3_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop4_fc
			MARK_CAR_AS_NO_LONGER_NEEDED copcar2_fc
			REMOVE_BLIP copcar2_fcblip 
			copcar2_fcflag = 2
		ENDIF
	ENDIF
	//
	IF copcar3swap_fcflag = 1
		IF NOT IS_CAR_DEAD copcar3_fc
			IF NOT IS_CHAR_DEAD cop5_fc
			ELSE
				IF NOT IS_CHAR_DEAD cop6_fc
					IF IS_CHAR_IN_CAR cop6_fc copcar3_fc
						GET_DRIVER_OF_CAR copcar3_fc driverofcar_fc
						IF driverofcar_fc = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop6_fc copcar3_fc
							copcar3swap_fcflag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR copcar3_fc
					copcar3swap_fcflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF copcar3swap_fcflag = 2
		IF IS_CHAR_DEAD cop6_fc
			STOP_PLAYBACK_RECORDED_CAR copcar3_fc
			copcar3swap_fcflag = 3
		ENDIF 
	ENDIF

	IF copcar3_fcflag = 1
		IF NOT IS_CAR_DEAD copcar3_fc
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar3_fc
				MARK_CAR_AS_NO_LONGER_NEEDED copcar3_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop5_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop6_fc
				REMOVE_BLIP copcar3_fcblip 
				copcar3_fcflag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR copcar3_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop5_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop6_fc
			MARK_CAR_AS_NO_LONGER_NEEDED copcar3_fc
			REMOVE_BLIP copcar3_fcblip 
			copcar3_fcflag = 2
		ENDIF
	ENDIF
	//
	IF copcar4swap_fcflag = 1
		IF NOT IS_CAR_DEAD copcar4_fc
			IF NOT IS_CHAR_DEAD cop7_fc
			ELSE
				IF NOT IS_CHAR_DEAD cop8_fc
					IF IS_CHAR_IN_CAR cop8_fc copcar4_fc
						GET_DRIVER_OF_CAR copcar4_fc driverofcar_fc
						IF driverofcar_fc = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop8_fc copcar4_fc
							copcar4swap_fcflag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR copcar4_fc
					copcar4swap_fcflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF copcar4swap_fcflag = 2
		IF IS_CHAR_DEAD cop8_fc
			STOP_PLAYBACK_RECORDED_CAR copcar4_fc
			copcar4swap_fcflag = 3
		ENDIF 
	ENDIF

	IF copcar4_fcflag = 1
		IF NOT IS_CAR_DEAD copcar4_fc
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR copcar4_fc
				MARK_CAR_AS_NO_LONGER_NEEDED copcar4_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop7_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED cop8_fc
				REMOVE_BLIP copcar4_fcblip 
				copcar4_fcflag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR copcar4_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop7_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED cop8_fc
			MARK_CAR_AS_NO_LONGER_NEEDED copcar4_fc
			REMOVE_BLIP copcar4_fcblip 
			copcar4_fcflag = 2
		ENDIF
	ENDIF
	//
	IF mexcar1swap_fcflag = 1
		IF NOT IS_CAR_DEAD mexcar1_fc
			IF NOT IS_CHAR_DEAD gang5_fc
			ELSE
				IF NOT IS_CHAR_DEAD gang6_fc
					IF IS_CHAR_IN_CAR gang6_fc mexcar1_fc
						GET_DRIVER_OF_CAR mexcar1_fc driverofcar_fc
						IF driverofcar_fc = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT gang6_fc mexcar1_fc
							mexcar1swap_fcflag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR mexcar1_fc
					mexcar1swap_fcflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF mexcar1swap_fcflag = 2
		IF IS_CHAR_DEAD gang6_fc
			STOP_PLAYBACK_RECORDED_CAR mexcar1_fc
			mexcar1swap_fcflag = 3
		ENDIF 
	ENDIF

	IF mexcar1_fcflag = 1
		IF NOT IS_CAR_DEAD mexcar1_fc
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR mexcar1_fc
				MARK_CAR_AS_NO_LONGER_NEEDED mexcar1_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fc
				REMOVE_BLIP mexcar1_fcblip 
				mexcar1_fcflag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR mexcar1_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED gang5_fc
			MARK_CHAR_AS_NO_LONGER_NEEDED gang6_fc
			MARK_CAR_AS_NO_LONGER_NEEDED mexcar1_fc
			REMOVE_BLIP mexcar1_fcblip 
			mexcar1_fcflag = 2
		ENDIF
	ENDIF
	//
	IF bike1swap_fcflag = 1
		IF NOT IS_CAR_DEAD bike1_fc
			IF NOT IS_CHAR_DEAD gang9_fc
			ELSE
				IF NOT IS_CHAR_DEAD gang10_fc
					IF IS_CHAR_IN_CAR gang10_fc bike1_fc
						GET_DRIVER_OF_CAR bike1_fc driverofcar_fc
						IF driverofcar_fc = -1
							GET_CAR_COORDINATES benz_fc player_x player_y player_z
							WARP_CHAR_FROM_CAR_TO_COORD gang10_fc player_x player_y -10.0
							WARP_CHAR_INTO_CAR gang10_fc bike1_fc
							bike1swap_fcflag = 2
						ENDIF
					ENDIF
				ELSE
					SET_CAR_FORWARD_SPEED bike1_fc 18.0
					STOP_PLAYBACK_RECORDED_CAR bike1_fc
					SET_CAR_ROTATION_VELOCITY bike1_fc 3.5 7.3999 8.1003
					bike1swap_fcflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF bike1swap_fcflag = 2
		IF IS_CHAR_DEAD gang10_fc
			STOP_PLAYBACK_RECORDED_CAR bike1_fc
			IF NOT IS_CAR_DEAD bike1_fc
				SET_CAR_FORWARD_SPEED bike1_fc 18.0
				SET_CAR_ROTATION_VELOCITY bike1_fc 3.5 7.3999 8.1003
			ENDIF
			bike1swap_fcflag = 3
		ENDIF 
	ENDIF
	IF bike1_fcflag = 1
		IF IS_CHAR_DEAD gang9_fc
			IF IS_CHAR_DEAD gang10_fc
			OR IS_CAR_DEAD bike1_fc
				STOP_PLAYBACK_RECORDED_CAR bike1_fc
				REMOVE_BLIP bike1_fcblip
				MARK_CAR_AS_NO_LONGER_NEEDED bike1_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED gang9_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED gang10_fc
				bike1_fcflag = 2
			ENDIF
		ENDIF
	ENDIF

	IF bike2swap_fcflag = 1
		IF NOT IS_CAR_DEAD bike2_fc
			IF NOT IS_CHAR_DEAD gang11_fc
			ELSE
				IF NOT IS_CHAR_DEAD gang12_fc
					IF IS_CHAR_IN_CAR gang12_fc bike2_fc
						GET_DRIVER_OF_CAR bike2_fc driverofcar_fc
						IF driverofcar_fc = -1
							GET_CAR_COORDINATES benz_fc player_x player_y player_z
							WARP_CHAR_FROM_CAR_TO_COORD gang12_fc player_x player_y -10.0
							WARP_CHAR_INTO_CAR gang12_fc bike2_fc
							bike2swap_fcflag = 2
						ENDIF
					ENDIF
				ELSE
					SET_CAR_FORWARD_SPEED bike2_fc 18.0
					STOP_PLAYBACK_RECORDED_CAR bike2_fc
					SET_CAR_ROTATION_VELOCITY bike2_fc 3.5 7.3999 8.1003
					bike2swap_fcflag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF bike2swap_fcflag = 2
		IF IS_CHAR_DEAD gang12_fc
			STOP_PLAYBACK_RECORDED_CAR bike2_fc
			IF NOT IS_CAR_DEAD bike2_fc
				SET_CAR_FORWARD_SPEED bike2_fc 18.0
				SET_CAR_ROTATION_VELOCITY bike2_fc 3.5 7.3999 8.1003
			ENDIF
			bike2swap_fcflag = 3
		ENDIF 
	ENDIF
	IF bike2_fcflag = 1
		IF IS_CHAR_DEAD gang11_fc
			IF IS_CHAR_DEAD gang12_fc
			OR IS_CAR_DEAD bike2_fc
				STOP_PLAYBACK_RECORDED_CAR bike2_fc
				REMOVE_BLIP bike2_fcblip
				MARK_CAR_AS_NO_LONGER_NEEDED bike2_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED gang11_fc
				MARK_CHAR_AS_NO_LONGER_NEEDED gang12_fc
				bike2_fcflag = 2
			ENDIF
		ENDIF
	ENDIF

ENDIF
//

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////   End of Chase   ///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF finalerails_fcflag = 3

	IF finalcut_fcflag = 0
		IF NOT IS_CAR_DEAD benz_fc
			IF NOT IS_CAR_DEAD firetruck_fc

				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL PLAYER1 OFF
				SET_CAR_PROOFS benz_fc FALSE TRUE TRUE TRUE TRUE

				IF IS_PLAYBACK_GOING_ON_FOR_CAR firetruck_fc
					STOP_PLAYBACK_RECORDED_CAR firetruck_fc
				ENDIF
				STOP_PLAYBACK_RECORDED_CAR firetruck_fc

				IF IS_PLAYBACK_GOING_ON_FOR_CAR benz_fc
					STOP_PLAYBACK_RECORDED_CAR benz_fc
				ENDIF
				STOP_PLAYBACK_RECORDED_CAR benz_fc

				SET_FIXED_CAMERA_POSITION 2453.8613 -1651.5190 11.6415 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2453.1313 -1651.2875 12.2845  JUMP_CUT
				START_PLAYBACK_RECORDED_CAR firetruck_fc 229
				SET_LA_RIOTS OFF
				CLEAR_AREA 2453.8613 -1651.5190 11.6415 100.0 TRUE
				SET_CAR_DENSITY_MULTIPLIER 0.0
				SET_PED_DENSITY_MULTIPLIER 0.0
				SET_CAR_COORDINATES benz_fc 2431.88 -1600.34 25.26
				DELETE_CHAR sweet
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 2422.97 -1607.87 25.55
				ELSE
					SET_CHAR_COORDINATES scplayer 2422.97 -1607.87 25.55
				ENDIF
				TIMERA = 0
				TIMERB = 0
				DELETE_CAR benz_fc
				DELETE_CAR bike1_fc
				DELETE_CAR bike2_fc
				DELETE_CAR copcar1_fc
				DELETE_CAR copcar2_fc
				DELETE_CAR copcar3_fc
				DELETE_CAR copcar4_fc
				DELETE_CAR mexcar1_fc
				DELETE_OBJECT gangcar1_fc
				DELETE_OBJECT gangcar3_fc
				DELETE_OBJECT gangcar5_fc
//				DELETE_CHAR coptruck_fc
//				DELETE_CHAR copladder_fc
				DELETE_CHAR gang9_fc
				DELETE_CHAR gang10_fc
				DELETE_CHAR gang11_fc
				DELETE_CHAR gang12_fc
				DELETE_CHAR cop1_fc
				DELETE_CHAR cop2_fc
				DELETE_CHAR cop3_fc
				DELETE_CHAR cop4_fc
				DELETE_CHAR cop5_fc
				DELETE_CHAR cop6_fc
				DELETE_CHAR cop7_fc
				DELETE_CHAR cop8_fc
				DELETE_CHAR rioter1_fc
				DELETE_CHAR rioter2_fc
				DELETE_CHAR rioter3_fc
				DELETE_CHAR rioter4_fc
				DELETE_CHAR rioter5_fc
				DELETE_CHAR rioter6_fc

				finalcut_fcflag = 1
			ENDIF
		ENDIF
	ENDIF
				
	IF NOT IS_CAR_DEAD firetruck_fc
		IF finalcut_fcflag = 1
			IF TIMERB > 1500
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				SET_TIME_SCALE 0.8
				finalcut_fcflag = 2
			ENDIF
		ENDIF
		IF finalcut_fcflag = 2
			IF TIMERB > 1700
				SET_TIME_SCALE 0.7

				finalcut_fcflag = 3
			ENDIF
		ENDIF
		IF finalcut_fcflag = 3
			IF TIMERB > 1900
				SET_TIME_SCALE 0.6
				finalcut_fcflag = 4
			ENDIF
		ENDIF
		IF finalcut_fcflag = 4
			IF TIMERB > 2100
				SET_TIME_SCALE 0.5
				finalcut_fcflag = 5
			ENDIF
		ENDIF
		IF finalcut_fcflag = 5
			IF TIMERB > 2300
				SET_TIME_SCALE 0.4
				finalcut_fcflag = 6
			ENDIF
		ENDIF
		IF finalcut_fcflag = 6
			IF TIMERB > 2500
				SET_TIME_SCALE 0.3
				finalcut_fcflag = 7
			ENDIF
		ENDIF
		IF finalcut_fcflag = 7
			IF TIMERB > 2700
				SET_TIME_SCALE 0.2
				finalcut_fcflag = 8
			ENDIF
		ENDIF
		IF finalcut_fcflag = 8
			IF TIMERB > 3360
				CREATE_FX_SYSTEM_ON_CAR PRT_SPARK firetruck_fc -1.69 3.607 -0.271 TRUE spark1_fxfc
				PLAY_FX_SYSTEM spark1_fxfc
				CREATE_FX_SYSTEM_ON_CAR PRT_SPARK firetruck_fc -1.137 3.674 -0.271 TRUE spark2_fxfc
				PLAY_FX_SYSTEM spark2_fxfc
				CREATE_FX_SYSTEM_ON_CAR PRT_SPARK firetruck_fc -1.076 3.706 -0.271 TRUE spark3_fxfc
				PLAY_FX_SYSTEM spark3_fxfc
				finalcut_fcflag = 9
			ENDIF
		ENDIF

		IF finalcut_fcflag = 9
			IF TIMERB > 4250
				SET_FIXED_CAMERA_POSITION 2463.7458 -1663.4254 12.4388 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2462.8306 -1663.0225 12.4479 JUMP_CUT 
				SET_TIME_SCALE 0.7
				KILL_FX_SYSTEM spark1_fxfc
				KILL_FX_SYSTEM spark2_fxfc
				KILL_FX_SYSTEM spark3_fxfc
				//cab top
				CREATE_FX_SYSTEM_ON_CAR_WITH_DIRECTION PRT_SPARK firetruck_fc -1.066 3.027 1.4 1.0 0.0 0.4 TRUE spark4_fxfc
				PLAY_FX_SYSTEM spark4_fxfc
				CREATE_FX_SYSTEM_ON_CAR_WITH_DIRECTION PRT_SPARK firetruck_fc -1.066 2.444 1.4 1.0 0.0 0.4 TRUE spark5_fxfc
				PLAY_FX_SYSTEM spark5_fxfc
				CREATE_FX_SYSTEM_ON_CAR_WITH_DIRECTION PRT_SPARK firetruck_fc -1.066 1.872 1.4 1.0 0.0 0.4 TRUE spark6_fxfc
				PLAY_FX_SYSTEM spark6_fxfc

				//ladder top
				CREATE_FX_SYSTEM_ON_CAR_WITH_DIRECTION PRT_SPARK firetruck_fc -0.462 0.761 1.7 1.0 0.0 0.4 TRUE spark7_fxfc
				PLAY_FX_SYSTEM spark7_fxfc
				CREATE_FX_SYSTEM_ON_CAR_WITH_DIRECTION PRT_SPARK firetruck_fc -0.462 0.197 1.7 1.0 0.0 0.4 TRUE spark8_fxfc
				PLAY_FX_SYSTEM spark8_fxfc
				TIMERA = 0
				finalcut_fcflag = 10
			ENDIF
		ENDIF

	ENDIF

	IF finalcut_fcflag = 10
		IF TIMERA > 3000
			SET_TIME_SCALE 1.0
			//Grove street finale cut
			SET_FADING_COLOUR 0 0 0

			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			ADD_SCORE PLAYER1 250000
			CLEAR_ONSCREEN_COUNTER carhealth_fc
			DELETE_CHAR sweet
			MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
			//finaleD
			MARK_MODEL_AS_NO_LONGER_NEEDED FIRELA
			MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
			MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
			MARK_MODEL_AS_NO_LONGER_NEEDED FELTZER
			REMOVE_ANIMATION FINALE
			REMOVE_ANIMATION FINALE2
			MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
			MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
			MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar1
			MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar2
			UNLOAD_SPECIAL_CHARACTER 1
			UNLOAD_SPECIAL_CHARACTER 2
			//finaleE
			MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
			MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
			MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
			MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
			MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
			MARK_MODEL_AS_NO_LONGER_NEEDED TORNADO

			DELETE_CAR firetruck_fc
			DELETE_CHAR tenpenny_fc
			KILL_FX_SYSTEM spark1_fxfc
			KILL_FX_SYSTEM spark2_fxfc
			KILL_FX_SYSTEM spark3_fxfc
			KILL_FX_SYSTEM spark4_fxfc
			KILL_FX_SYSTEM spark5_fxfc
			KILL_FX_SYSTEM spark6_fxfc
			KILL_FX_SYSTEM spark7_fxfc
			KILL_FX_SYSTEM spark8_fxfc

			LOAD_SCENE_IN_DIRECTION 2445.64 -1650.53 14.63 0.0

			LOAD_CUTSCENE RIOT4E1
			 
			WHILE NOT HAS_CUTSCENE_LOADED
				WAIT 0
			ENDWHILE
			 
			START_CUTSCENE

			DO_FADE 1000 FADE_IN
			  
			WHILE NOT HAS_CUTSCENE_FINISHED
			            WAIT 0
			ENDWHILE

			CLEAR_CUTSCENE

			SET_PLAYER_CONTROL player1 OFF

			DO_FADE 0 FADE_OUT

			WHILE GET_FADING_STATUS
			            WAIT 0
			ENDWHILE

			IF NOT WAS_CUTSCENE_SKIPPED
				LOAD_CUTSCENE RIOT4E2
				 
				WHILE NOT HAS_CUTSCENE_LOADED
					WAIT 0
				ENDWHILE
				 
				START_CUTSCENE

				DO_FADE 1000 FADE_IN
				  
				WHILE NOT HAS_CUTSCENE_FINISHED
				            WAIT 0
				ENDWHILE

				CLEAR_CUTSCENE

				SET_PLAYER_CONTROL player1 OFF

				DO_FADE 0 FADE_OUT

				WHILE GET_FADING_STATUS
				            WAIT 0
				ENDWHILE
			ENDIF

			//Epilogue
			SET_FADING_COLOUR 0 0 0

			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
			            WAIT 0
			ENDWHILE

			SET_AREA_VISIBLE 3

			LOAD_CUTSCENE EPILOG
			 
			WHILE NOT HAS_CUTSCENE_LOADED
			            WAIT 0
			ENDWHILE
			 
			START_CUTSCENE

			DO_FADE 1000 FADE_IN
			  
			WHILE NOT HAS_CUTSCENE_FINISHED
			            WAIT 0
			ENDWHILE	 

			CLEAR_CUTSCENE

			SET_PLAYER_CONTROL player1 OFF

			DO_FADE 0 FADE_OUT

			WHILE GET_FADING_STATUS
			            WAIT 0
			ENDWHILE

			SET_AREA_VISIBLE 0
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////	CREDITS		////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			SET_PLAYER_CONTROL PLAYER1 OFF
			SWITCH_WIDESCREEN ON

			SET_CHAR_COORDINATES SCPLAYER 2523.9749 -1749.9615 58.7020 
			FREEZE_CHAR_POSITION SCPLAYER TRUE

			SET_CAR_DENSITY_MULTIPLIER 2.0 
			SET_PED_DENSITY_MULTIPLIER 1.5

			SET_LA_RIOTS OFF

			LOAD_SCENE 2514.6677 -1733.4613 56.3075

			FORCE_WEATHER_NOW WEATHER_SUNNY_SMOG_LA

			SET_TIME_OF_DAY 12 00

			PRELOAD_BEAT_TRACK 9
			
			////////////////////////////////////////////////////////////////////////////////////////////
			LVAR_INT test_credit

			WHILE NOT test_credit = 2
				WAIT 0
				GET_BEAT_TRACK_STATUS test_credit
			ENDWHILE		

			PLAY_BEAT_TRACK
			SET_MUSIC_DOES_FADE FALSE
			////////////////////////////////////////////////////////////////////////////////////////////

			SET_DARKNESS_EFFECT TRUE -1

			START_CREDITS

			DO_FADE 250 FADE_IN

			CAMERA_RESET_NEW_SCRIPTABLES


			CAMERA_SET_VECTOR_MOVE 2523.9749 -1749.9615 57.2020 2514.8875 -1733.8508 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2523.7551 -1749.5720 56.3075 2514.6677 -1733.4613 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2514.8875 -1733.8508 57.2020 2503.3440 -1713.3859 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2514.6677 -1733.4613 56.3075 2503.1243 -1712.9963 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2503.3440 -1713.3859 57.2020 2491.5549 -1692.4855 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2503.1243 -1712.9963 56.3075 2491.3352 -1692.0959 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2491.5549 -1692.4855 57.2020 2479.7659 -1671.5851 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2491.3352 -1692.0959 56.3075 2479.5461 -1671.1956 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2479.7659 -1671.5851 57.2020 2467.7312 -1650.2493 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2479.5461 -1671.1956 56.3075 2467.5115 -1649.8597 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2467.7312 -1650.2493 57.2020 2455.4509 -1628.4780 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2467.5115 -1649.8597 56.3075 2455.2312 -1628.0885 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2455.4509 -1628.4780 57.2020 2445.1355 -1610.1902 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2455.2312 -1628.0885 56.3075 2444.9158 -1609.8007 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2445.1355 -1610.1902 57.2020 2433.8376 -1590.1606 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2444.9158 -1609.8007 56.3075 2433.6179 -1589.7711 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2433.8376 -1590.1606 57.2020 2423.2766 -1571.4374 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2433.6179 -1589.7711 56.3075 2423.0569 -1571.0479 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2423.2766 -1571.4374 57.2020 2412.7156 -1552.7141 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2423.0569 -1571.0479 56.3075 2412.4958 -1552.3246 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2412.7156 -1552.7141 57.2020 2401.9089 -1533.5554 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2412.4958 -1552.3246 56.3075 2401.6892 -1533.1659 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2401.9089 -1533.5554 57.2020 2390.6111 -1513.5259 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2401.6892 -1533.1659 56.3075 2390.3914 -1513.1364 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2390.6111 -1513.5259 57.2020 2379.3132 -1493.4963 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2390.3914 -1513.1364 56.3075 2379.0935 -1493.1068 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2379.3132 -1493.4963 57.2020 2367.2786 -1472.1605 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2379.0935 -1493.1068 56.3075 2367.0588 -1471.7710 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2367.2786 -1472.1605 57.2020 2355.9807 -1452.1310 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2367.0588 -1471.7710 56.3075 2355.7610 -1451.7415 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2355.9807 -1452.1310 57.2020 2343.9460 -1430.7952 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2355.7610 -1451.7415 56.3075 2343.7263 -1430.4056 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2343.9460 -1430.7952 57.2020 2332.4026 -1410.3302 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2343.7263 -1430.4056 56.3075 2332.1829 -1409.9407 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2332.4026 -1410.3302 57.2020 2321.5959 -1391.1715 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2332.1829 -1409.9407 56.3075 2321.3762 -1390.7820 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2321.5959 -1391.1715 57.2020 2312.0173 -1374.1899 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2321.3762 -1390.7820 56.3075 2311.7976 -1373.8004 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			DO_FADE 6000 FADE_OUT

			CAMERA_SET_VECTOR_MOVE 2312.0173 -1374.1899 57.2020 2300.9651 -1354.5958 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2311.7976 -1373.8004 56.3075 2300.7454 -1354.2063 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2300.9651 -1354.5958 57.2020 2289.6672 -1334.5663 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2300.7454 -1354.2063 56.3075 2289.4475 -1334.1768 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2289.6672 -1334.5663 57.2020 2277.8782 -1313.6659 57.2020 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 2289.4475 -1334.1768 56.3075 2277.6584 -1313.2764 56.3075 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			///////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////		Country side		///////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////


			SET_CHAR_COORDINATES SCPLAYER -1014.5570 -1422.2545 186.1819 
			FREEZE_CHAR_POSITION SCPLAYER TRUE
			
			SET_TIME_OF_DAY 10 00

			FORCE_WEATHER_NOW WEATHER_SUNNY_COUNTRYSIDE

			WAIT 11000

			DO_FADE 6000 FADE_IN

			CAMERA_SET_VECTOR_MOVE -1014.5570 -1422.2545 184.6819 -1037.9375 -1423.0750 186.8606 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1015.5563 -1422.2897 184.6751 -1038.9368 -1423.1101 186.8538 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1037.9375 -1423.0750 186.8606 -1063.3068 -1423.9652 189.2248 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1038.9368 -1423.1101 186.8538 -1064.3060 -1424.0004 189.2180 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1063.3068 -1423.9652 189.2248 -1089.1586 -1425.2170 191.6353 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1064.3060 -1424.0004 189.2180 -1090.1562 -1425.2841 191.6285 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1089.1586 -1425.2170 191.6353 -1114.4905 -1426.9166 193.9995 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1090.1562 -1425.2841 191.6285 -1115.4882 -1426.9836 193.9927 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1114.4905 -1426.9166 193.9995 -1139.8224 -1428.6162 196.3636 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1115.4882 -1426.9836 193.9927 -1140.8201 -1428.6832 196.3568 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1139.8224 -1428.6162 196.3636 -1165.6510 -1430.3491 198.7742 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1140.8201 -1428.6832 196.3568 -1166.6487 -1430.4161 198.7673 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1165.6510 -1430.3491 198.7742 -1191.4796 -1432.0820 201.1847 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1166.6487 -1430.4161 198.7673 -1192.4773 -1432.1490 201.1779 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1191.4796 -1432.0820 201.1847 -1216.8115 -1433.7816 203.5488 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1192.4773 -1432.1490 201.1779 -1217.8092 -1433.8486 203.5420 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1216.8115 -1433.7816 203.5488 -1242.1434 -1435.4812 205.9130 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1217.8092 -1433.8486 203.5420 -1243.1411 -1435.5482 205.9062 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1242.1434 -1435.4812 205.9130 -1267.9720 -1437.2141 208.3235 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1243.1411 -1435.5482 205.9062 -1268.9697 -1437.2811 208.3167 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1267.9720 -1437.2141 208.3235 -1293.8007 -1438.9470 210.7341 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1268.9697 -1437.2811 208.3167 -1294.7983 -1439.0140 210.7272 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1293.8007 -1438.9470 210.7341 -1319.6293 -1440.6799 213.1446 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1294.7983 -1439.0140 210.7272 -1320.6270 -1440.7469 213.1378 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1319.6293 -1440.6799 213.1446 -1345.4579 -1442.4128 215.5551 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1320.6270 -1440.7469 213.1378 -1346.4556 -1442.4799 215.5483 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1345.4579 -1442.4128 215.5551 -1370.7898 -1444.1124 217.9193 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1346.4556 -1442.4799 215.5483 -1371.7875 -1444.1794 217.9125 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1370.7898 -1444.1124 217.9193 -1396.1217 -1445.8120 220.2834 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1371.7875 -1444.1794 217.9125 -1397.1194 -1445.8790 220.2766 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1396.1217 -1445.8120 220.2834 -1420.9569 -1447.4783 222.6012 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1397.1194 -1445.8790 220.2766 -1421.9546 -1447.5453 222.5944 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1420.9569 -1447.4783 222.6012 -1446.2888 -1449.1779 224.9654 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1421.9546 -1447.5453 222.5944 -1447.2865 -1449.2449 224.9586 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1446.2888 -1449.1779 224.9654 -1471.6207 -1450.8774 227.3296 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1447.2865 -1449.2449 224.9586 -1472.6184 -1450.9445 227.3228 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1471.6207 -1450.8774 227.3296 -1496.9526 -1452.5770 229.6937 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1472.6184 -1450.9445 227.3228 -1497.9503 -1452.6440 229.6869 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			DO_FADE 6000 FADE_OUT

			CAMERA_SET_VECTOR_MOVE -1496.9526 -1452.5770 229.6937 -1522.2845 -1454.2766 232.0579 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1497.9503 -1452.6440 229.6869 -1523.2822 -1454.3436 232.0511 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1522.2845 -1454.2766 232.0579 -1547.6165 -1455.9762 234.4221 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1523.2822 -1454.3436 232.0511 -1548.6141 -1456.0432 234.4153 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1547.6165 -1455.9762 234.4221 -1572.4517 -1457.6425 236.7399 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -1548.6141 -1456.0432 234.4153 -1573.4493 -1457.7095 236.7331 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE


			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			///////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////		San Fran 1		///////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////

			SET_DARKNESS_EFFECT FALSE -1

			SET_CHAR_COORDINATES SCPLAYER -2551.8245 1438.7454 -6.4582 
			FREEZE_CHAR_POSITION SCPLAYER TRUE

			FORCE_WEATHER_NOW WEATHER_RAINY_SF

			SET_TIME_OF_DAY 22 15

			WAIT 11000

			DO_FADE 6000 FADE_IN

			CAMERA_SET_VECTOR_MOVE -2551.8245 1438.7454 -7.9582 -2555.0500 1456.4509 -7.9582 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2552.0029 1439.7242 -8.0577 -2555.2285 1457.4298 -8.0577 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2555.0500 1456.4509 -7.9582 -2558.9810 1478.0219 -6.5912 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2555.2285 1457.4298 -8.0577 -2559.1604 1479.0054 -6.5742 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2558.9810 1478.0219 -6.5912 -2562.7661 1498.7935 -2.7267 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2559.1604 1479.0054 -6.5742 -2562.9434 1499.7650 -2.5699 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2562.7661 1498.7935 -2.7267 -2566.4202 1518.8525 4.0086 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2562.9434 1499.7650 -2.5699 -2566.5935 1519.8026 4.2679 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2566.4202 1518.8525 4.0086 -2570.0210 1538.6309 11.6223 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2566.5935 1519.8026 4.2679 -2570.1943 1539.5809 11.8816 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2570.0210 1538.6309 11.6223 -2573.7056 1558.8691 19.4130 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2570.1943 1539.5809 11.8816 -2573.8789 1559.8192 19.6724 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2573.7056 1558.8691 19.4130 -2577.2227 1578.1875 26.8497 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2573.8789 1559.8192 19.6724 -2577.3960 1579.1376 27.1090 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2577.2227 1578.1875 26.8497 -2580.9072 1598.4258 34.6405 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2577.3960 1579.1376 27.1090 -2581.0806 1599.3759 34.8998 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2580.9072 1598.4258 34.6405 -2584.5137 1618.2250 42.1997 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2581.0806 1599.3759 34.8998 -2584.6875 1619.1782 42.4465 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2584.5137 1618.2250 42.1997 -2588.2334 1638.6407 49.4991 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2584.6875 1619.1782 42.4465 -2588.4082 1639.5990 49.7249 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2588.2334 1638.6407 49.4991 -2591.9778 1659.1760 56.4439 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2588.4082 1639.5990 49.7249 -2592.1528 1660.1361 56.6616 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2591.9778 1659.1760 56.4439 -2595.6416 1679.2588 63.1876 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2592.1528 1660.1361 56.6616 -2595.8167 1680.2189 63.4053 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2595.6416 1679.2588 63.1876 -2599.3936 1699.8424 69.9817 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2595.8167 1680.2189 63.4053 -2599.5693 1700.8060 70.1827 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2599.3936 1699.8424 69.9817 -2603.1702 1720.5684 76.3148 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2599.5693 1700.8060 70.1827 -2603.3467 1721.5359 76.4955 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2603.1702 1720.5684 76.3148 -2606.9680 1741.4113 82.2359 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2603.3467 1721.5359 76.4955 -2607.1450 1742.3820 82.3978 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2606.9680 1741.4113 82.2359 -2610.6992 1761.8849 87.6284 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2607.1450 1742.3820 82.3978 -2610.8767 1762.8584 87.7717 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2610.6992 1761.8849 87.6284 -2614.4475 1782.4547 92.6294 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2610.8767 1762.8584 87.7717 -2614.6255 1783.4308 92.7541 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2614.4475 1782.4547 92.6294 -2618.2144 1803.1271 97.1743 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2614.6255 1783.4308 92.7541 -2618.3928 1804.1060 97.2734 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2618.2144 1803.1271 97.1743 -2622.0010 1823.9093 101.1663 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2618.3928 1804.1060 97.2734 -2622.1799 1824.8904 101.2393 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2622.0010 1823.9093 101.1663 -2625.8059 1844.7877 104.6021 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2622.1799 1824.8904 101.2393 -2625.9851 1845.7704 104.6488 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			DO_FADE 6000 FADE_OUT

			CAMERA_SET_VECTOR_MOVE -2625.8059 1844.7877 104.6021 -2629.4468 1864.7711 107.3578 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2625.9851 1845.7704 104.6488 -2629.6262 1865.7545 107.3795 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2629.4468 1864.7711 107.3578 -2633.2776 1885.7935 109.7129 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2629.6262 1865.7545 107.3795 -2633.4570 1886.7771 109.7149 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -2633.2776 1885.7935 109.7129 -2637.1094 1906.8315 111.8949 2000 FALSE
			CAMERA_SET_VECTOR_TRACK -2633.4570 1886.7771 109.7149 -2637.2888 1907.8152 111.8969 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE


			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			///////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////		San Fran 2		///////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////
			
			FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_LA

			SET_TIME_OF_DAY 05 30

			SET_CHAR_COORDINATES SCPLAYER -922.5345 669.1976 86.8181 
			FREEZE_CHAR_POSITION SCPLAYER TRUE

			SET_DARKNESS_EFFECT TRUE -1

			WAIT 11000

			DO_FADE 6000 FADE_IN

			CAMERA_SET_VECTOR_MOVE -922.5345 669.1976 85.3181 -944.3385 676.5142 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -923.4778 669.5142 85.2186 -945.2818 676.8308 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -944.3385 676.5142 85.3181 -968.5125 684.6262 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -945.2818 676.8308 85.2186 -969.4557 684.9427 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -968.5125 684.6262 85.3181 -993.1604 692.8972 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -969.4557 684.9427 85.2186 -994.1037 693.2137 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -993.1604 692.8972 85.3181 -1017.8083 701.1682 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -994.1037 693.2137 85.2186 -1018.7516 701.4847 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1017.8083 701.1682 85.3181 -1041.9823 709.2801 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1018.7516 701.4847 85.2186 -1042.9255 709.5967 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1041.9823 709.2801 85.3181 -1066.1562 717.3920 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1042.9255 709.5967 85.2186 -1067.0995 717.7086 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1066.1562 717.3920 85.3181 -1090.3302 725.5040 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1067.0995 717.7086 85.2186 -1091.2734 725.8206 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1090.3302 725.5040 85.3181 -1114.5042 733.6159 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1091.2734 725.8206 85.2186 -1115.4474 733.9325 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1114.5042 733.6159 85.3181 -1138.6781 741.7278 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1115.4474 733.9325 85.2186 -1139.6213 742.0444 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1138.6781 741.7278 85.3181 -1162.3781 749.6807 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1139.6213 742.0444 85.2186 -1163.3213 749.9973 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1162.3781 749.6807 85.3181 -1186.0780 757.6336 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1163.3213 749.9973 85.2186 -1187.0212 757.9502 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1186.0780 757.6336 85.3181 -1209.7780 765.5865 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1187.0212 757.9502 85.2186 -1210.7212 765.9031 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1209.7780 765.5865 85.3181 -1233.9519 773.6984 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1210.7212 765.9031 85.2186 -1234.8951 774.0150 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1233.9519 773.6984 85.3181 -1258.1259 781.8104 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1234.8951 774.0150 85.2186 -1259.0691 782.1270 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1258.1259 781.8104 85.3181 -1281.8258 789.7632 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1259.0691 782.1270 85.2186 -1282.7690 790.0798 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1281.8258 789.7632 85.3181 -1305.5258 797.7161 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1282.7690 790.0798 85.2186 -1306.4690 798.0327 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1305.5258 797.7161 85.3181 -1329.2257 805.6690 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1306.4690 798.0327 85.2186 -1330.1689 805.9856 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1329.2257 805.6690 85.3181 -1353.3997 813.7809 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1330.1689 805.9856 85.2186 -1354.3429 814.0975 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1353.3997 813.7809 85.3181 -1377.5736 821.8929 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1354.3429 814.0975 85.2186 -1378.5168 822.2095 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1377.5736 821.8929 85.3181 -1401.2736 829.8458 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1378.5168 822.2095 85.2186 -1402.2168 830.1624 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1401.2736 829.8458 85.3181 -1423.5515 837.3215 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1402.2168 830.1624 85.2186 -1424.4948 837.6381 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1423.5515 837.3215 85.3181 -1446.7775 845.1153 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1424.4948 837.6381 85.2186 -1447.7207 845.4319 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1446.7775 845.1153 85.3181 -1470.4774 853.0682 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1447.7207 845.4319 85.2186 -1471.4207 853.3848 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1470.4774 853.0682 85.3181 -1493.7034 860.8620 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1471.4207 853.3848 85.2186 -1494.6466 861.1786 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			DO_FADE 5900 FADE_OUT

			CAMERA_SET_VECTOR_MOVE -1493.7034 860.8620 85.3181 -1515.9813 868.3377 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1494.6466 861.1786 85.2186 -1516.9246 868.6543 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE
		//
			CAMERA_SET_VECTOR_MOVE -1515.9813 868.3377 85.3181 -1538.2593 875.8134 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1516.9246 868.6543 85.2186 -1539.2025 876.1300 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE -1538.2593 875.8134 85.3181 -1561.0112 883.4482 85.3181 2250 FALSE
			CAMERA_SET_VECTOR_TRACK -1539.2025 876.1300 85.2186 -1561.9545 883.7648 85.2186 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE
		//
			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			///////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////		Desert 		///////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////

			FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT

			SET_DARKNESS_EFFECT TRUE -1

		   	SET_CHAR_COORDINATES SCPLAYER 370.7745 2441.2339 19.0844 
			FREEZE_CHAR_POSITION SCPLAYER TRUE

			SET_TIME_OF_DAY 12 00

			WAIT 11000

			DO_FADE 6000 FADE_IN

			CAMERA_SET_VECTOR_MOVE 370.7745 2441.2339 17.5844 366.5777 2438.9019 17.5844 2250 FALSE
			CAMERA_SET_VECTOR_TRACK 369.9044 2440.7512 17.4849 365.7076 2438.4192 17.4849 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 366.5777 2438.9019 17.5844 362.0319 2436.3760 17.5835 2250 FALSE
			CAMERA_SET_VECTOR_TRACK 365.7076 2438.4192 17.4849 361.1612 2435.8928 17.4921 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 362.0319 2436.3760 17.5835 357.4894 2433.8516 17.6536 2250 FALSE
			CAMERA_SET_VECTOR_TRACK 361.1612 2435.8928 17.4921 356.6158 2433.3669 17.6101 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 357.4894 2433.8516 17.6536 352.8694 2431.2900 17.9960 2250 FALSE
			CAMERA_SET_VECTOR_TRACK 356.6158 2433.3669 17.6101 351.9951 2430.8049 18.0046 2250 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 352.8694 2431.2900 17.9960 348.2637 2428.7283 18.5695 2200 FALSE
			CAMERA_SET_VECTOR_TRACK 351.9951 2430.8049 18.0046 347.3847 2428.2515 18.5783 2200 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			SKIP_CUTSCENE_START
			
			CAMERA_SET_VECTOR_MOVE 348.2637 2428.7283 18.5695 343.6016 2426.2739 19.1432 2150 FALSE
			CAMERA_SET_VECTOR_TRACK 347.3847 2428.2515 18.5783 342.7046 2425.8320 19.1520 2150 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 343.6016 2426.2739 19.1432 338.9370 2424.0457 19.7060 2100 FALSE
			CAMERA_SET_VECTOR_TRACK 342.7046 2425.8320 19.1520 338.0236 2423.6387 19.7148 2100 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 338.9370 2424.0457 19.7060 334.2820 2422.0376 20.2581 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 338.0236 2423.6387 19.7148 333.3536 2421.6660 20.2669 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 334.2820 2422.0376 20.2581 329.5525 2420.2117 20.8101 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 333.3536 2421.6660 20.2669 328.6110 2419.8748 20.8189 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 329.5525 2420.2117 20.8101 324.7735 2418.5161 21.3621 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 328.6110 2419.8748 20.8189 323.8303 2418.1841 21.3710 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 324.7735 2418.5161 21.3621 320.0809 2416.8789 21.9034 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 323.8303 2418.1841 21.3710 319.1361 2416.5515 21.9122 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 320.0809 2416.8789 21.9034 315.1030 2415.1558 22.4770 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 319.1361 2416.5515 21.9122 314.1573 2414.8311 22.4858 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 315.1030 2415.1558 22.4770 310.2042 2413.5056 23.0399 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 314.1573 2414.8311 22.4858 309.2525 2413.1985 23.0487 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 310.2042 2413.5056 23.0399 305.2758 2411.9458 23.6028 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 309.2525 2413.1985 23.0487 304.3187 2411.6560 23.6116 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 305.2758 2411.9458 23.6028 300.3198 2410.4758 24.1656 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 304.3187 2411.6560 23.6116 299.3576 2410.2036 24.1744 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 300.3198 2410.4758 24.1656 295.4340 2409.1216 24.7176 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 299.3576 2410.2036 24.1744 294.4671 2408.8665 24.7265 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 295.4340 2409.1216 24.7176 290.4283 2407.8308 25.2805 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 294.4671 2408.8665 24.7265 289.4569 2407.5933 25.2893 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 290.4283 2407.8308 25.2805 285.3999 2406.6309 25.8434 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 289.4569 2407.5933 25.2893 284.4244 2406.4109 25.8522 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 285.3999 2406.6309 25.8434 280.3567 2405.4883 26.4062 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 284.4244 2406.4109 25.8522 279.3812 2405.2683 26.4150 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 280.3567 2405.4883 26.4062 275.3135 2404.3457 26.9691 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 279.3812 2405.2683 26.4150 274.3380 2404.1257 26.9779 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 275.3135 2404.3457 26.9691 270.3673 2403.2251 27.5211 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 274.3380 2404.1257 26.9779 269.3918 2403.0051 27.5299 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 270.3673 2403.2251 27.5211 265.4211 2402.1045 28.0732 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 269.3918 2403.0051 27.5299 264.4456 2401.8845 28.0820 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 265.4211 2402.1045 28.0732 262.4145 2401.4233 28.4087 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 264.4456 2401.8845 28.0820 261.4390 2401.2034 28.4175 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 262.4145 2401.4233 28.4087 258.8261 2400.6104 28.8092 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 261.4390 2401.2034 28.4175 257.8506 2400.3904 28.8180 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			DO_FADE 6000 FADE_OUT

			CAMERA_SET_VECTOR_MOVE 258.8261 2400.6104 28.8092 253.8804 2399.4897 29.3612 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 257.8506 2400.3904 28.8180 252.9049 2399.2698 29.3700 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 253.8804 2399.4897 29.3612 248.8379 2398.3472 29.9241 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 252.9049 2399.2698 29.3700 247.8624 2398.1272 29.9329 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 248.8379 2398.3472 29.9241 244.0864 2397.2705 30.4545 2000 FALSE
			CAMERA_SET_VECTOR_TRACK 247.8624 2398.1272 29.9329 243.1109 2397.0505 30.4633 2000 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE


			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			///////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////		Vegas 		///////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////


			SET_CHAR_COORDINATES SCPLAYER 2012.4438 2140.3826 78.0505 
			FREEZE_CHAR_POSITION SCPLAYER TRUE

			SET_TIME_OF_DAY 00 00

			WAIT 11000

			DO_FADE 6000 FADE_IN

			CAMERA_SET_VECTOR_MOVE 2012.4438 2140.3826 76.5505 2030.9395 2140.4187 76.5504 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2013.4388 2140.3845 76.4510 2031.9343 2140.4207 76.4497 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2030.9395 2140.4187 76.5504 2051.3752 2140.4587 75.0916 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2031.9343 2140.4207 76.4497 2052.3489 2140.4607 74.8638 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2051.3752 2140.4587 75.0916 2072.1094 2140.4998 71.7815 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2052.3489 2140.4607 74.8638 2073.0610 2140.5017 71.4748 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2072.1094 2140.4998 71.7815 2092.6179 2140.5408 67.3086 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2073.0610 2140.5017 71.4748 2093.5688 2140.5427 66.9993 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2092.6179 2140.5408 67.3086 2114.1023 2140.5837 62.6200 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2093.5688 2140.5427 66.9993 2115.0532 2140.5857 62.3108 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2114.1023 2140.5837 62.6200 2135.5867 2140.6267 57.9315 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2115.0532 2140.5857 62.3108 2136.5376 2140.6287 57.6223 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2135.5867 2140.6267 57.9315 2157.0710 2140.6697 53.2431 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2136.5376 2140.6287 57.6223 2158.0220 2140.6716 52.9338 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2157.0710 2140.6697 53.2431 2179.0437 2140.7136 48.4481 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2158.0220 2140.6716 52.9338 2179.9946 2140.7156 48.1388 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2179.0437 2140.7136 48.4481 2201.0166 2140.7576 43.6528 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2179.9946 2140.7156 48.1388 2201.9675 2140.7595 43.3440 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2201.0166 2140.7576 43.6528 2222.6650 2140.8005 39.8525 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2201.9675 2140.7595 43.3440 2223.6355 2140.8025 39.6117 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2222.6650 2140.8005 39.8525 2244.4712 2140.8435 37.0436 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2223.6355 2140.8025 39.6117 2245.4524 2140.8455 36.8518 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2244.4712 2140.8435 37.0436 2266.3867 2140.8865 35.2987 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2245.4524 2140.8455 36.8518 2267.3777 2140.8884 35.1659 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2266.3867 2140.8865 35.2987 2287.8674 2140.9285 34.9542 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2267.3777 2140.8884 35.1659 2288.8638 2140.9304 34.8693 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2287.8674 2140.9285 34.9542 2310.3564 2140.9724 35.2840 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2288.8638 2140.9304 34.8693 2311.3528 2140.9744 35.1991 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2310.3564 2140.9724 35.2840 2332.8455 2141.0164 35.6137 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2311.3528 2140.9744 35.1991 2333.8418 2141.0183 35.5288 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			SET_MUSIC_DOES_FADE TRUE

			CAMERA_SET_VECTOR_MOVE 2332.8455 2141.0164 35.6137 2354.3350 2141.0583 35.9288 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2333.8418 2141.0183 35.5288 2355.3313 2141.0603 35.8439 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			DO_FADE 4000 FADE_OUT

			CAMERA_SET_VECTOR_MOVE 2354.3350 2141.0583 35.9288 2376.8240 2141.1023 36.2586 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2355.3313 2141.0603 35.8439 2377.8203 2141.1042 36.1737 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			CAMERA_SET_VECTOR_MOVE 2376.8240 2141.1023 36.2586 2399.8127 2141.1472 36.5957 2900 FALSE
			CAMERA_SET_VECTOR_TRACK 2377.8203 2141.1042 36.1737 2400.8091 2141.1492 36.5108 2900 FALSE
			WHILE CAMERA_IS_VECTOR_MOVE_RUNNING
				WAIT 0
				GOSUB CREDITS_UPDATE_PLAYER_POS
			ENDWHILE

			///////////////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////		END		///////////////////////////////////////////
			///////////////////////////////////////////////////////////////////////////////////////////////

			////////////////////////////////////////////////////////////////////////////////////////////
			skipcredits_fcflag = 1
			SKIP_CUTSCENE_END
			////////////////////////////////////////////////////////////////////////////////////////////

			IF skipcredits_fcflag = 0
				STOP_CREDITS
			ENDIF
			
			SET_MUSIC_DOES_FADE TRUE
			
			DO_FADE 0 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			SWITCH_WIDESCREEN OFF
			RELEASE_WEATHER
			FORCE_WEATHER_NOW WEATHER_SUNNY_SMOG_LA
			STOP_BEAT_TRACK

			FREEZE_CHAR_POSITION SCPLAYER FALSE
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES scplayer 2494.483 -1684.639 12.51//2495.111 -1687.04 12.55
			SET_CHAR_HEADING scplayer 3.546
			CAMERA_RESET_NEW_SCRIPTABLES
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			
			SET_DARKNESS_EFFECT FALSE -1
			SET_CAR_DENSITY_MULTIPLIER 1.0
			SET_PED_DENSITY_MULTIPLIER 1.0

			WHILE NOT ARE_CREDITS_FINISHED
				WAIT 0
			ENDWHILE

			STOP_BEAT_TRACK
			STOP_CREDITS
			LOAD_SCENE_IN_DIRECTION 2495.111 -1687.04 12.55 0.0

			WAIT 500	


			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////


			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 300
			DO_FADE 3500 FADE_IN

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			CLEAR_WANTED_LEVEL PLAYER1
			SET_PLAYER_CONTROL PLAYER1 ON

			REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_MICRO_UZI
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 120
		
			GOTO mission_finaleC_passed
			
			finalcut_fcflag = 11

		ENDIF
	ENDIF

ENDIF

GOTO mission_finaleC_loop


load_audio_fc:
IF handlingudio_fcflag = 0
	LOAD_MISSION_AUDIO 1 audio_label_fc
	$text_label_fc = $input_text_fc
	handlingudio_fcflag = 1
ENDIF
RETURN

process_audio_fc:
IF handlingudio_fcflag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		PRINT_NOW $text_label_fc 4000 1 //Dummy message"
		PLAY_MISSION_AUDIO 1
		handlingudio_fcflag = 2
	ENDIF
ENDIF
IF handlingudio_fcflag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_fcflag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		handlingudio_fcflag = 0
	ENDIF
ENDIF
RETURN


CREDITS_UPDATE_PLAYER_POS:
	GET_ACTIVE_CAMERA_COORDINATES PLAYER_X PLAYER_Y PLAYER_Z
	PLAYER_Z += 1.5
	SET_CHAR_COORDINATES SCPLAYER PLAYER_X PLAYER_Y PLAYER_Z
RETURN


chasekill_fclabel:
OPEN_SEQUENCE_TASK chasekill_fcseq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_fc
CLOSE_SEQUENCE_TASK chasekill_fcseq
PERFORM_SEQUENCE_TASK enemy_fc chasekill_fcseq
CLEAR_SEQUENCE_TASK chasekill_fcseq
RETURN																					   

stayshoot_fclabel:
OPEN_SEQUENCE_TASK stayshoot_fcseq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshoot_fcseq
PERFORM_SEQUENCE_TASK enemy_fc stayshoot_fcseq
CLEAR_SEQUENCE_TASK stayshoot_fcseq
RETURN

stayshootnoduck_fclabel:
OPEN_SEQUENCE_TASK stayshootnoduck_fcseq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshootnoduck_fcseq
PERFORM_SEQUENCE_TASK enemy_fc stayshootnoduck_fcseq
CLEAR_SEQUENCE_TASK stayshootnoduck_fcseq
RETURN


// Mission finaleC failed
mission_finaleC_failed:
CLEAR_PRINTS
PRINT_BIG M_FAIL 5000 1

IF cardead_fcflag = 1
	PRINT_NOW RM4_46 5000 1 //~r~You wrecked your car!
ENDIF
IF displaygripbar_fcflag = 7
	PRINT_NOW RM4_45 7000 1 //~r~Sweet died! You are supposed to catch him, next time get your car underneath Sweet when he begins to lose grip!
ENDIF
IF lostsweet_fcflag = 1
	PRINT_NOW RM4_50 5000 1 //~r~You lost Sweet! Next time stay closer!
ENDIF

RETURN
   

// mission finaleC passed
mission_finaleC_passed:

CLEAR_WANTED_LEVEL player1
REGISTER_MISSION_PASSED RIOT_4
PLAYER_MADE_PROGRESS 1
SET_INT_STAT CITIES_PASSED 4
REMOVE_BLIP mansion_contact_blip
REMOVE_BLIP sweet_contact_blip
flag_riot_mission_counter++
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 150 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 1
AWARD_PLAYER_MISSION_RESPECT 150//amount of respect

RETURN
		


// mission cleanup

mission_cleanup_finaleC:

SET_RAILTRACK_RESISTANCE_MULT -1.0
//counter
CLEAR_ONSCREEN_COUNTER igripbardisplay_fc
//counter
CLEAR_ONSCREEN_COUNTER carhealth_fc
//triangle button
SET_PLAYER_ENTER_CAR_BUTTON PLAYER1 TRUE

//blips
REMOVE_BLIP sweet_fcblip
REMOVE_BLIP mexcar1_fcblip
REMOVE_BLIP mexcar2_fcblip
REMOVE_BLIP mexcar3_fcblip
REMOVE_BLIP ecar1_fcblip
REMOVE_BLIP ecar2_fcblip
REMOVE_BLIP bike1_fcblip
REMOVE_BLIP bike2_fcblip
REMOVE_BLIP bike3_fcblip
REMOVE_BLIP copcar1_fcblip
REMOVE_BLIP copcar2_fcblip
//blips
REMOVE_BLIP copcar3_fcblip
REMOVE_BLIP copcar4_fcblip
//fx
KILL_FX_SYSTEM spark1_fxfc
KILL_FX_SYSTEM spark2_fxfc
KILL_FX_SYSTEM spark3_fxfc
KILL_FX_SYSTEM spark4_fxfc
KILL_FX_SYSTEM spark5_fxfc
KILL_FX_SYSTEM spark6_fxfc
KILL_FX_SYSTEM spark7_fxfc
//remove chars
REMOVE_CHAR_ELEGANTLY sweet
REMOVE_CHAR_ELEGANTLY tenpenny_fc
REMOVE_CHAR_ELEGANTLY copladder_fc
REMOVE_CHAR_ELEGANTLY coptruck_fc

REMOVE_ALL_SCRIPT_FIRES
SET_WANTED_MULTIPLIER 1.0
SET_MAX_WANTED_LEVEL 6
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0


MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
//finaleD
MARK_MODEL_AS_NO_LONGER_NEEDED FIRELA
MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
MARK_MODEL_AS_NO_LONGER_NEEDED FELTZER
REMOVE_ANIMATION FINALE
REMOVE_ANIMATION FINALE2
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar1
MARK_MODEL_AS_NO_LONGER_NEEDED la_fuckcar2

//finaleE
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
MARK_MODEL_AS_NO_LONGER_NEEDED MOLOTOV
MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
MARK_MODEL_AS_NO_LONGER_NEEDED TORNADO

UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2
//car proofs
IF NOT IS_CAR_DEAD benz_fc
	SET_CAR_PROOFS benz_fc FALSE FALSE FALSE FALSE FALSE
ENDIF
IF NOT IS_CAR_DEAD firetruck_fc
	LOCK_CAR_DOORS firetruck_fc CARLOCK_UNLOCKED
	SET_CAR_PROOFS firetruck_fc FALSE FALSE FALSE FALSE FALSE
ENDIF
//char proofs
IF IS_PLAYER_PLAYING PLAYER1
	SHUT_CHAR_UP scplayer FALSE
	SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
ENDIF
RELEASE_WEATHER
SWITCH_RANDOM_TRAINS ON
SWITCH_EMERGENCY_SERVICES ON
SET_WANTED_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN
		

}
