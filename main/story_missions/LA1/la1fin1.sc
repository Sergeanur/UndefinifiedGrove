MISSION_START
// *********************************************************************************************
// *********************************************************************************************
// *********************************************************************************************
// ***************************************** LA1FIN1 *******************************************
// ********************************** Reuniting The Families ***********************************
// ********* Inspired by Perfect Dark, Goldeneye, Terminator 2, Time Crisis, Zelda *************
// *********************************************************************************************
// *********************************************************************************************
// *********************************************************************************************

SCRIPT_NAME drugs4

// Mission start stuff

GOSUB mission_start_drugs4

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_drugs4_failed
ENDIF

GOSUB mission_cleanup_drugs4

MISSION_END
{
 
// Variables for mission

//interior section
LVAR_INT grove1_f1
LVAR_INT grove2_f1
LVAR_INT grove3_f1
LVAR_INT grove4_f1
LVAR_INT grove5_f1
LVAR_INT grove6_f1

LVAR_INT extpoliceheli_f1
LVAR_INT extpoliceheli_f1health
LVAR_INT extpolicevan1_f1
LVAR_INT extpolicevan2_f1
LVAR_INT exthelidriver_f1
LVAR_INT extvan1driver_f1
LVAR_INT extvan2driver_f1
LVAR_INT swatrope1_f1
LVAR_INT swatrope2_f1
LVAR_INT swatrope3_f1
LVAR_INT swatrope4_f1
LVAR_INT swat1_f1
LVAR_INT swat2_f1
LVAR_INT swat3_f1
LVAR_INT swat4_f1
LVAR_INT motel_DM //decision maker stuff
LVAR_INT coward_DM
LVAR_INT extmotel_DM //decision maker stuff
LVAR_INT helispotlight_f1
LVAR_INT swatrope1_f1status
LVAR_INT swatrope2_f1status
LVAR_INT swatrope3_f1status
LVAR_INT swatrope4_f1status
LVAR_INT playerweapon_f1

//inside motel
LVAR_INT motel_interior //for get_visible_area command
LVAR_INT breachfx_f1 //breach particle fx
LVAR_INT woundedgrove1_f1 
LVAR_INT woundedgrove2_f1
LVAR_INT swat5_f1 
LVAR_INT swat6_f1 
LVAR_INT swat7_f1 
LVAR_INT swat8_f1 
LVAR_INT swat9_f1 
LVAR_INT swat10_f1
LVAR_INT swat11_f1 
LVAR_INT swat12_f1 
LVAR_INT swat13_f1 
LVAR_INT swat14_f1 
LVAR_INT swat15_f1
LVAR_INT swat16_f1
LVAR_INT swat17_f1
LVAR_INT swat18_f1
LVAR_INT hoochie1_f1 
LVAR_INT hoochie2_f1
LVAR_INT breachdoor_f1
LVAR_INT vent1_f1
LVAR_INT vent2_f1
LVAR_INT swtbreach01_f1	//for animations
LVAR_INT swtbreach02_f1	//for animations
LVAR_INT swtbreach03_f1	//for animations
LVAR_FLOAT swtvent01_f1	//for animations
LVAR_FLOAT swtvent02_f1	//for animations
LVAR_FLOAT upsidedownswat_f1	//for animations
LVAR_INT skylite_f1
LVAR_INT sweetstatus_f1
LVAR_INT playerstatus_f1
LVAR_FLOAT playeranim_f1
LVAR_INT trolley1_f1
LVAR_INT trolley2_f1
LVAR_INT trolley3_f1
LVAR_INT trolley4_f1
LVAR_INT trolley5_f1
LVAR_INT trolley6_f1
LVAR_INT trolley7_f1


//text
LVAR_TEXT_LABEL $text_label_f1
LVAR_INT audio_label_f1
LVAR_TEXT_LABEL $input_text_f1


//on rails section

//sequences
LVAR_INT hoochieflee_f1seq
LVAR_INT runstay_f1seq
LVAR_INT stayshoot_f1seq
LVAR_INT stayshootnoduck_f1seq
LVAR_INT stay2shoot_f1seq
LVAR_INT rolloutr_f1seq
LVAR_INT rolloutl_f1seq
LVAR_INT peekright_f1seq
LVAR_INT peekleft_f1seq
LVAR_INT enemy_f1
LVAR_INT enemytarget_f1
LVAR_INT enemytarget2_f1
LVAR_INT hoochieescape_f1seq
LVAR_FLOAT enemyx_f1 enemyy_f1 enemyz_f1
LVAR_INT shootatcoords_f1seq
LVAR_INT hoochie2_f1seq
//counters
VAR_INT sweethealth_f1

//pickups
LVAR_INT armour1_f1

//flags
LVAR_INT moteldeal_f1flag
LVAR_INT missiongo_f1flag
LVAR_INT playerincar_f1flag
LVAR_INT extvan1driver_f1flag
LVAR_INT swat1_f1flag
LVAR_INT swat2_f1flag
LVAR_INT extvan2driver_f1flag
LVAR_INT swat3_f1flag
LVAR_INT swat4_f1flag
LVAR_INT breach_f1flag
LVAR_INT swatrope1_f1flag
LVAR_INT swatrope2_f1flag
LVAR_INT swatrope3_f1flag
LVAR_INT swatrope4_f1flag
LVAR_INT skipcutscene_f1flag
LVAR_INT extpoliceheli_f1flag
LVAR_INT extpolicevan1_f1flag
LVAR_INT extpolicevan2_f1flag
LVAR_INT insidemotel_f1flag

LVAR_INT motelentrance_f1flag
LVAR_INT firstcorridor_f1flag
LVAR_INT woundedgrove1_f1flag
LVAR_INT grove2_f1flag
LVAR_INT attachaudio_f1flag

LVAR_INT grovecorner_f1flag
LVAR_INT swatcorner_f1flag
LVAR_INT swat5_f1flag
LVAR_INT swat6_f1flag
LVAR_INT swat7_f1flag
LVAR_INT swat8_f1flag
LVAR_INT swat9_f1flag
LVAR_INT swat10_f1flag
LVAR_INT swat11_f1flag
LVAR_INT coordshoot_f1flag	//sequence flag
LVAR_INT grove5_f1flag
LVAR_INT vent1a_f1flag
LVAR_INT vent1b_f1flag
LVAR_INT swat14_f1flag
LVAR_INT swat15_f1flag
LVAR_INT swat16_f1flag
LVAR_INT swat17_f1flag
LVAR_INT swat18_f1flag
LVAR_INT sweet_f1flag
LVAR_INT sweetexit_f1flag
LVAR_INT roofmotel_f1flag
LVAR_INT swatwindosmash_f1flag
LVAR_INT hoochie1_f1flag
LVAR_INT skiphelicutscene_f1flag
LVAR_INT helicrash_f1flag
LVAR_INT talkincar_f1flag
LVAR_INT progressaudio_f1flag
LVAR_INT handlingaudio_f1flag
LVAR_INT hoochie2_f1flag
LVAR_INT missionaudio2_f1flag
LVAR_INT audiosweet_f1flag
//blips
LVAR_INT sweet_f1blip
LVAR_INT motel_f1blip
LVAR_INT heli_f1blip
///////////////////////////////////////////////////////	On rails variables
///////////////////////////////////////////////////////	On rails variables

LVAR_INT policecar1_f1
LVAR_INT policecar2_f1
LVAR_INT policecar3_f1
LVAR_INT policecar4_f1
LVAR_INT policecar5_f1
LVAR_INT policecar6_f1
LVAR_INT policecar7_f1
LVAR_INT policecar8_f1
LVAR_INT policecarblock_f1
LVAR_INT cop1_f1
LVAR_INT cop2_f1
LVAR_INT cop3_f1
LVAR_INT cop4_f1
LVAR_INT cop5_f1
LVAR_INT cop6_f1
LVAR_INT cop7_f1
LVAR_INT cop8_f1
LVAR_INT cop9_f1
LVAR_INT cop10_f1
LVAR_INT cop11_f1
LVAR_INT cop12_f1
LVAR_INT cop13_f1
LVAR_INT cop14_f1
LVAR_INT cop15_f1
LVAR_INT cop16_f1
LVAR_INT train_f1
VAR_INT carhealth_f1
LVAR_INT copbike1_f1
LVAR_INT copbike2_f1
LVAR_INT copbike3_f1
LVAR_INT copbike4_f1
LVAR_INT biker1_f1
LVAR_INT biker2_f1
LVAR_INT bikerjumper3_f1
LVAR_INT bikerjumper4_f1
LVAR_FLOAT bikerjumper4_f1heading
LVAR_FLOAT bikerjumper3_f1heading
LVAR_INT sca1_f1
LVAR_INT sca2_f1
LVAR_INT sca3_f1
LVAR_INT sca4_f1
LVAR_INT sca5_f1
LVAR_INT sca6_f1
LVAR_FLOAT animframebk_jmp
LVAR_FLOAT animframebk_punch
LVAR_INT donut_f1
LVAR_INT bikerjumper4_f1health
LVAR_INT bikerjumper3_f1health
LVAR_INT copright_f1 
LVAR_INT copleft_f1 
LVAR_INT policecarblock2_f1 
LVAR_INT policecarblock3_f1
LVAR_INT jetwashfx1_f1
LVAR_FLOAT copright_f1heading
LVAR_FLOAT copleft_f1heading
LVAR_INT bloodfx1_f1
LVAR_INT bloodfx2_f1
LVAR_INT bloodfx3_f1
LVAR_INT torso_f1
LVAR_INT legr_f1
LVAR_INT legl_f1
LVAR_INT armr_f1
LVAR_INT arml_f1
LVAR_INT head_f1
LVAR_INT fence_f1
LVAR_INT fence2_f1
LVAR_INT fence3_f1
LVAR_INT fence4_f1
LVAR_INT fence5_f1
LVAR_INT fence6_f1
LVAR_INT fence7_f1
LVAR_INT fence8_f1
LVAR_INT fence9_f1
LVAR_INT fence10_f1
LVAR_INT fence11_f1
LVAR_INT fence12_f1
LVAR_INT fencel_f1
LVAR_INT fence2l_f1
LVAR_INT fence3l_f1
LVAR_INT fence4l_f1
LVAR_INT fence5l_f1
LVAR_INT fence6l_f1
LVAR_INT fence7l_f1
LVAR_INT fence8l_f1
LVAR_INT fence9l_f1
LVAR_INT fence10l_f1
LVAR_INT fence11l_f1
LVAR_INT fence12l_f1
LVAR_INT trailer_f1
LVAR_INT truck_f1
LVAR_INT car_f1
LVAR_INT advertholder_f1
LVAR_FLOAT animstate_f1
LVAR_INT fire_f1
LVAR_INT fire2_f1
LVAR_INT fire3_f1
LVAR_INT fire4_f1 
LVAR_INT fire5_f1 
LVAR_INT fire6_f1
LVAR_INT driverofcar_f1

//blips
LVAR_INT policecar1_f1blip
LVAR_INT policecar2_f1blip
LVAR_INT policecar3_f1blip
LVAR_INT policecar4_f1blip
LVAR_INT policecar5_f1blip
LVAR_INT policecar6_f1blip
LVAR_INT policecar7_f1blip
LVAR_INT policecar8_f1blip

//flags
LVAR_INT difficulty_f1flag //do not reset this

LVAR_INT rails_f1flag
LVAR_INT motelchase_f1flag
LVAR_INT copcars_f1flag
LVAR_INT policecar1_f1flag
LVAR_INT policecar2_f1flag
LVAR_INT policecar3_f1flag
LVAR_INT policecar4_f1flag
LVAR_INT policecar5_f1flag
LVAR_INT policecar6_f1flag
LVAR_INT policecar7_f1flag
LVAR_INT policecar8_f1flag
LVAR_INT sca_f1flag
LVAR_INT policecarblock_f1flag
LVAR_INT bikerjumper4_f1flag
LVAR_INT bikerjumper3_f1flag
LVAR_INT playdeathanim_f1flag
LVAR_INT playdeathanim2_f1flag
LVAR_INT helileave_f1flag
LVAR_INT chasetext_f1flag
LVAR_INT policecar1swap_f1flag
LVAR_INT policecar2swap_f1flag
LVAR_INT policecar3swap_f1flag
LVAR_INT policecar4swap_f1flag
LVAR_INT policecar5swap_f1flag
LVAR_INT policecar6swap_f1flag
LVAR_INT policecar7swap_f1flag
LVAR_INT policecar8swap_f1flag
LVAR_INT copright_f1flag
LVAR_INT copleft_f1flag
LVAR_INT turncamera_f1flag
LVAR_INT fence_f1flag
LVAR_INT helisetpiece_f1flag
LVAR_INT textrails_f1flag
LVAR_INT actiontext_f1flag
LVAR_INT policecarexplode_f1flag
LVAR_INT breakposter_f1flag
LVAR_INT finalcut_f1flag
LVAR_INT helpshoottext_f1flag
///////////////////////////////////////////////////////	On rails variables
///////////////////////////////////////////////////////	On rails variables

// **************************************** Mission Start **********************************

mission_start_drugs4:

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT LAFIN1

flag_player_on_mission = 1

WAIT 0



// ****************************************START OF CUTSCENE********************************



// ****************************************END OF CUTSCENE**********************************

// fades the screen in 

////upper body muscle
//SET_FLOAT_STAT BODY_MUSCLE 50.0
////fatness
//SET_FLOAT_STAT FAT 200.0 
//
//BUILD_PLAYER_MODEL player1
//

////////////////////////////////////////////////////////////////////	CUTSCENE START
SET_AREA_VISIBLE 1

LOAD_CUTSCENE FINAL1A
 
WHILE NOT HAS_CUTSCENE_LOADED
            WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
           WAIT 0
ENDWHILE

CLEAR_CUTSCENE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
            WAIT 0
ENDWHILE

SET_AREA_VISIBLE 0 
///////////////////////////////////////////////////////////////////  CUTSCENE END


SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL PLAYER1 ON

SET_FADING_COLOUR 0 0 0				    

WAIT 0

LOAD_SCENE 2517.237 -1671.341 12.9

FORCE_WEATHER_NOW WEATHER_SUNNY_LA

// request models



REQUEST_MODEL GREENWOO
REQUEST_MODEL FAM3
REQUEST_MODEL FAM2

REQUEST_MODEL CHROMEGUN

LOAD_SPECIAL_CHARACTER 1 SWEET
LOAD_SPECIAL_CHARACTER 2 SMOKE
LOAD_SPECIAL_CHARACTER 3 RYDER2

REQUEST_CAR_RECORDING 350


REQUEST_MODEL MP5LNG
REQUEST_MODEL COLT45
REQUEST_MODEL SWAT

LOAD_MISSION_AUDIO 2 SOUND_SMOX_AD //Come on, playa, get in!


REQUEST_CAR_RECORDING 352
REQUEST_CAR_RECORDING 353

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED GREENWOO
	OR NOT HAS_MODEL_LOADED FAM3
	OR NOT HAS_MODEL_LOADED FAM2
	OR NOT HAS_MISSION_AUDIO_LOADED 2
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED CHROMEGUN
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 350
	OR NOT HAS_MODEL_LOADED MP5LNG
	OR NOT HAS_MODEL_LOADED COLT45
	OR NOT HAS_MODEL_LOADED SWAT
	WAIT 0
ENDWHILE

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
	OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 352
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 353
	WAIT 0
ENDWHILE
		

SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
// ************************************************setting variables**************************************

//flags for interior section
moteldeal_f1flag = 0
missiongo_f1flag = 0
playerincar_f1flag = 0
extvan1driver_f1flag = 0
swat1_f1flag = 0
swat2_f1flag = 0
extvan2driver_f1flag = 0
swat3_f1flag = 0
swat4_f1flag = 0
breach_f1flag = 0
swatrope1_f1flag = 0
swatrope2_f1flag = 0
swatrope3_f1flag = 0
swatrope4_f1flag = 0
skipcutscene_f1flag = 0
extpoliceheli_f1flag = 0
extpolicevan1_f1flag = 0
extpolicevan2_f1flag = 0
insidemotel_f1flag = 0
motelentrance_f1flag = 0
firstcorridor_f1flag = 0
woundedgrove1_f1flag = 0
grove2_f1flag = 0
grovecorner_f1flag = 0
swatcorner_f1flag = 0
swat5_f1flag = 0
swat6_f1flag = 0
swat7_f1flag = 0
swat8_f1flag = 0
swat9_f1flag = 0
swat10_f1flag = 0
swat11_f1flag = 0
coordshoot_f1flag = 0	//sequence flag
grove5_f1flag = 0
vent1a_f1flag = 0
vent1b_f1flag = 0
swat14_f1flag = 0
swat15_f1flag = 0
swat16_f1flag = 0
swat17_f1flag = 0
swat18_f1flag = 0
sweet_f1flag = 0
sweetexit_f1flag = 0
roofmotel_f1flag = 0
swatwindosmash_f1flag = 0
hoochie1_f1flag = 0
skiphelicutscene_f1flag = 0
helicrash_f1flag = 0
talkincar_f1flag = 0
progressaudio_f1flag = 0
handlingaudio_f1flag = 0
hoochie2_f1flag = 0
missionaudio2_f1flag = 0
audiosweet_f1flag = 0
attachaudio_f1flag = 0

//flags for rails chase
rails_f1flag = 0
motelchase_f1flag = 0
copcars_f1flag = 0
policecar1_f1flag = 0
policecar2_f1flag = 0
policecar3_f1flag = 0
policecar4_f1flag = 0
policecar5_f1flag = 0
policecar6_f1flag = 0
policecar7_f1flag = 0
policecar8_f1flag = 0
sca_f1flag = 0
policecarblock_f1flag = 0
bikerjumper4_f1flag = 0
bikerjumper3_f1flag = 0
playdeathanim_f1flag = 0
playdeathanim2_f1flag = 0
helileave_f1flag = 0
chasetext_f1flag = 0
policecar1swap_f1flag = 0
policecar2swap_f1flag = 0
policecar3swap_f1flag = 0
policecar4swap_f1flag = 0
policecar5swap_f1flag = 0
policecar6swap_f1flag = 0
policecar7swap_f1flag = 0
policecar8swap_f1flag = 0
copright_f1flag = 0
copleft_f1flag = 0
turncamera_f1flag = 0
fence_f1flag = 0
helisetpiece_f1flag = 0
textrails_f1flag = 0
actiontext_f1flag = 0
policecarexplode_f1flag = 0
breakposter_f1flag = 0
finalcut_f1flag = 0
helpshoottext_f1flag = 0
// *******************************************************************************************************

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY motel_DM
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE motel_dm EVENT_POTENTIAL_GET_RUN_OVER

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK coward_DM

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH extmotel_DM
//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE extmotel_DM EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT_STAND_STILL 100.0 100.0 100.0 100.0 1 1
//ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE extmotel_DM EVENT_DAMAGE TASK_COMPLEX_KILL_PED_ON_FOOT 50.0 50.0 50.0 50.0 1 1

CLEAR_AREA 2514.316 -1673.395 150.0 150.0 TRUE
SET_TIME_OF_DAY 19 30
SET_CHAR_COORDINATES scplayer 2517.237 -1671.341 12.9
SET_CHAR_HEADING scplayer 63.048
RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER


//create car
CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO &GROVE4L_
CREATE_CAR GREENWOO 2508.07 -1672.114 12.0957 sweet_car
SET_CAR_CAN_BE_VISIBLY_DAMAGED sweet_car FALSE
SET_CAR_HEADING sweet_car 167.195
SET_CAR_ONLY_DAMAGED_BY_PLAYER sweet_car TRUE
ADD_BLIP_FOR_CAR sweet_car sweet_f1blip
SET_BLIP_AS_FRIENDLY sweet_f1blip TRUE
CHANGE_CAR_COLOUR sweet_car 59 34
//create buddies
CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2511.005 -1672.268 12.493 sweet
SET_CHAR_HEADING sweet 62.083
SET_CHAR_NEVER_TARGETTED sweet TRUE
SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet TRUE
TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 5000 1
SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED sweet TRUE
SET_CHAR_DECISION_MAKER sweet motel_DM
SET_CHAR_NEVER_TARGETTED sweet TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE

CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 2505.318 -1674.747 12.376 big_smoke
SET_CHAR_HEADING big_smoke 356.374
SET_CHAR_NEVER_TARGETTED big_smoke TRUE
SET_CHAR_ONLY_DAMAGED_BY_PLAYER big_smoke TRUE
TASK_ENTER_CAR_AS_PASSENGER big_smoke sweet_car 5000 0
SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED big_smoke TRUE
SET_CHAR_DECISION_MAKER big_smoke motel_DM
SET_CHAR_NEVER_TARGETTED big_smoke TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS big_smoke FALSE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE

CREATE_CHAR PEDTYPE_SPECIAL SPECIAL03 2506.57 -1667.753 12.376 ryder
SET_CHAR_HEADING ryder 171.457
SET_CHAR_NEVER_TARGETTED ryder TRUE
SET_CHAR_ONLY_DAMAGED_BY_PLAYER ryder TRUE
TASK_ENTER_CAR_AS_PASSENGER ryder sweet_car 5000 2
SET_CHAR_CANT_BE_DRAGGED_OUT ryder TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED ryder TRUE
SET_CHAR_DECISION_MAKER ryder motel_DM
SET_CHAR_NEVER_TARGETTED ryder TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR ryder FALSE

PRINT_NOW LAF1_1 10000 1 //Get in the car and drive the crew to the meet at the motel.
SHUT_CHAR_UP scplayer TRUE

SET_WANTED_MULTIPLIER 0.0
SET_MAX_FIRE_GENERATIONS 1
SWITCH_ROADS_OFF 2180.18 -1130.28 21.2 2276.82 -1151.26 28.0
SWITCH_PED_ROADS_OFF 2180.18 -1130.28 21.2 2276.82 -1151.26 28.0

SWITCH_EMERGENCY_SERVICES OFF

DO_FADE 500 FADE_IN

moteldeal_mainloop:

WAIT 0

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_drugs4_passed
ENDIF

//timeratest = TIMERA
//VIEW_INTEGER_VARIABLE timeratest timeratest
//timerbtest = TIMERB
//VIEW_INTEGER_VARIABLE timerbtest timerbtest
//VIEW_INTEGER_VARIABLE helisetpiece_f1flag helisetpiece_f1flag
//VIEW_INTEGER_VARIABLE copcars_f1flag copcars_f1flag
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////	Getting to motel and cut scene	////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//once player is in car told to go to destination

IF missiongo_f1flag = 0
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD ryder
					IF IS_CHAR_SITTING_IN_CAR sweet sweet_car
						IF IS_CHAR_SITTING_IN_CAR ryder sweet_car
							IF IS_CHAR_SITTING_IN_CAR big_smoke sweet_car
								IF IS_CHAR_SITTING_IN_CAR scplayer sweet_car
									REMOVE_BLIP sweet_f1blip

									ADD_BLIP_FOR_COORD 2256.239 -1146.909 25.121 motel_f1blip
									SET_RADIO_CHANNEL RS_NEW_JACK_SWING 
									PRINT_NOW LAF1_3 7000 1 //Drive the crew to the meet at the motel

									SWITCH_ENTRY_EXIT motel2 FALSE
									TIMERA = 0

									playerincar_f1flag = 1
									moteldeal_f1flag = 1
									missiongo_f1flag = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF moteldeal_f1flag = 1 

	GOSUB process_audio_f1

	//play mission audio
	IF progressaudio_f1flag = 0
		IF handlingaudio_f1flag = 0
			IF TIMERA > 7000
				audio_label_f1 = SOUND_FIN1_GA	//Say, CJ, you gonna crash the car again?
				$input_text_f1 = FIN1_GA	//Say, CJ, you gonna crash the car again?
				GOSUB load_audio_f1
			ENDIF
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 1
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GB	//Fuck you, Ryder.
			$input_text_f1 = FIN1_GB	//Fuck you, Ryder.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 2
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GC	//Ryder, give CJ a break, man. He’s practically turned the Grove around by himself.
			$input_text_f1 = FIN1_GC	//Ryder, give CJ a break, man. He’s practically turned the Grove around by himself.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 3
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GD	//Man, I was just telling a joke on the little nigga.
			$input_text_f1 = FIN1_GD	//Man, I was just telling a joke on the little nigga.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 4
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GE	//Everything you do is a joke, Ryder.
			$input_text_f1 = FIN1_GE	//Everything you do is a joke, Ryder.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 5
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GG	//That ain’t true!
			$input_text_f1 = FIN1_GG	//That ain’t true!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 6
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GI	//Ryder… <sighs> just chill the fuck out.
			$input_text_f1 = FIN1_GI	//Ryder… <sighs> just chill the fuck out.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 7
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GJ	//Remember, we’re uniting the Families, so no bullshit.
			$input_text_f1 = FIN1_GJ	//Remember, we’re uniting the Families, so no bullshit.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 8
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GK	//Stay cool.
			$input_text_f1 = FIN1_GK	//Stay cool.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 9
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GL	//You know me, Sweet, cool as a Shaolin monk!
			$input_text_f1 = FIN1_GL	//You know me, Sweet, cool as a Shaolin monk!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 10
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GM	//Especially you, Ryder.
			$input_text_f1 = FIN1_GM	//Especially you, Ryder.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 11
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GN	//What you mean? I resent your implication, and shit.
			$input_text_f1 = FIN1_GN	//What you mean? I resent your implication, and shit.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 12
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GO	//Sweet’s just sayin’ you’re a natural killa.
			$input_text_f1 = FIN1_GO	//Sweet’s just sayin’ you’re a natural killa.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 13
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GP	//You gotta tame that tiger and stay cool.
			$input_text_f1 = FIN1_GP	//You gotta tame that tiger and stay cool.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 14
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GQ	//Yeah, well put it like this – I understand what he’s trying to say,
			$input_text_f1 = FIN1_GQ	//Yeah, well put it like this – I understand what he’s trying to say,				
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 15
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GR	//but I’m always cool, fool!
			$input_text_f1 = FIN1_GR	//but I’m always cool, fool!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 16
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_GS	//Hey we all down with that, dog.
			$input_text_f1 = FIN1_GS	//Hey we all down with that, dog.
			GOSUB load_audio_f1
		ENDIF
	ENDIF

ENDIF




IF moteldeal_f1flag = 1
OR moteldeal_f1flag = 100

	//tell player to get back in the car if they leave it
	IF NOT IS_CAR_DEAD sweet_car

		IF playerincar_f1flag = 1
			IF NOT IS_CHAR_IN_CAR scplayer sweet_car
				REMOVE_BLIP motel_f1blip
				ADD_BLIP_FOR_CAR sweet_car sweet_f1blip	
				SET_BLIP_AS_FRIENDLY sweet_f1blip TRUE
				//PRINT_NOW LAF1_2 5000 1 //Get back in the car!
				PLAY_MISSION_AUDIO 2
				PRINT_NOW SMOX_AD 2000 1 //Come on, playa, get in!
				moteldeal_f1flag = 100
				playerincar_f1flag = 0
			ENDIF
		ENDIF

		IF playerincar_f1flag = 0
			IF IS_CHAR_IN_CAR scplayer sweet_car
				REMOVE_BLIP sweet_f1blip 
				ADD_BLIP_FOR_COORD 2256.239 -1146.909 25.121 motel_f1blip
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 2
				IF progressaudio_f1flag > 16
					PRINT_NOW LAF1_3 5000 1 //Drive the crew to the meet at the motel
				ENDIF
				moteldeal_f1flag = 1
				playerincar_f1flag = 1
			ENDIF
		ENDIF

	ENDIF
ENDIF

IF moteldeal_f1flag = 0
OR moteldeal_f1flag = 1
OR moteldeal_f1flag = 100

	//any of the crew get wasted
	IF IS_CHAR_DEAD sweet
	OR IS_CHAR_DEAD big_smoke
	OR IS_CHAR_DEAD ryder
		PRINT_NOW LAF1_71 5000 1 //Your homies died!
		GOTO mission_drugs4_failed
	ENDIF

	//car gets wasted
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW LAF1_72 5000 1 //The car is wrecked
		GOTO mission_drugs4_failed
	ENDIF

ENDIF

//////////////////////////////////////////////////////////////////////////////////	cut scene at the motel 

//player comes in from the right side of the camera
IF moteldeal_f1flag = 1
	IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CAR_DEAD sweet_car
					IF IS_CHAR_SITTING_IN_CAR scplayer sweet_car
						IF LOCATE_CAR_2D sweet_car 2256.239 -1146.909 4.4 4.4 TRUE
							CLEAR_MISSION_AUDIO 1
							CLEAR_MISSION_AUDIO 2
							SET_PLAYER_CONTROL PLAYER1 OFF
							SET_MAX_WANTED_LEVEL 0
							CLEAR_WANTED_LEVEL PLAYER1
							CLEAR_AREA 2223.769 -1141.832 200.0 200.0 TRUE
							SET_CAR_DENSITY_MULTIPLIER 0.0
							SET_PED_DENSITY_MULTIPLIER 0.0
							SET_WANTED_MULTIPLIER 0.0
							CLEAR_PRINTS
							moteldeal_f1flag = 2 //player comes in from the right
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

//player comes in from the right
IF moteldeal_f1flag = 2
	IF NOT IS_CAR_DEAD sweet_car

		CLEAR_AREA 2223.971 -1149.75 200.0 200.0 FALSE
		SWITCH_WIDESCREEN ON
		SET_PLAYER_CONTROL PLAYER1 OFF
		
		DO_FADE 500 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		
		LOAD_MISSION_AUDIO 1 SOUND_FIN1_HA //Ok, it’s strictly one rep per set – you guys are gonna have to wait here.
		LOAD_MISSION_AUDIO 2 SOUND_FIN1_HB //We’ll be here just in case, bro.

		REQUEST_MODEL POLMAV
		REQUEST_MODEL ENFORCER
		REQUEST_ANIMATION CAR_CHAT

		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_MODEL_LOADED POLMAV
			OR NOT HAS_MODEL_LOADED ENFORCER
			OR NOT HAS_MISSION_AUDIO_LOADED 1
			OR NOT HAS_MISSION_AUDIO_LOADED 2
			OR NOT HAS_ANIMATION_LOADED CAR_CHAT
			WAIT 0
		ENDWHILE


		CREATE_CHAR PEDTYPE_MISSION3 FAM3 2220.3696 -1160.3181 24.7265 grove1_f1 
		SET_CHAR_HEADING grove1_f1 272.7973 //leaning against middle car
		TASK_PLAY_ANIM grove1_f1 IDLE_CHAT PED 8.0 TRUE FALSE FALSE FALSE -1
		SET_CHAR_DECISION_MAKER grove1_f1 motel_DM
		SET_CHAR_NEVER_TARGETTED grove1_f1 TRUE
		WAIT 250
		CREATE_CHAR PEDTYPE_MISSION3 FAM2 2221.5642 -1160.3588 24.7265 grove2_f1
		SET_CHAR_HEADING grove2_f1 85.7682 //opposite that guy
		TASK_PLAY_ANIM grove2_f1 IDLE_CHAT PED 8.0 TRUE FALSE FALSE FALSE -1
		SET_CHAR_DECISION_MAKER grove2_f1 motel_DM
		SET_CHAR_NEVER_TARGETTED grove2_f1 TRUE
		CREATE_CHAR PEDTYPE_MISSION3 FAM2 2227.2244 -1172.0699 24.7265 grove3_f1
		SET_CHAR_HEADING grove3_f1 359.1032 //leaning against the left car 
		SET_CHAR_DECISION_MAKER grove3_f1 motel_DM
		SET_CHAR_NEVER_TARGETTED grove3_f1 TRUE
		CREATE_CHAR PEDTYPE_MISSION3 FAM3 2226.3889 -1171.6877 24.7265 grove4_f1
		SET_CHAR_HEADING grove4_f1 278.7767 //next to him
		TASK_PLAY_ANIM grove4_f1 IDLE_CHAT PED 8.0 TRUE FALSE FALSE FALSE -1
		SET_CHAR_DECISION_MAKER grove4_f1 motel_DM
		SET_CHAR_NEVER_TARGETTED grove4_f1 TRUE

		LOAD_SCENE_IN_DIRECTION 2236.1460 -1146.3591 25.4346 34.0
				
		IF NOT IS_CAR_DEAD sweet_car
			CAR_SET_IDLE sweet_car	
			SET_CAR_COORDINATES sweet_car 2278.411 -1147.018 25.5 
			SET_CAR_HEADING sweet_car 80.53
	
			CAMERA_RESET_NEW_SCRIPTABLES
			SET_FIXED_CAMERA_POSITION 2236.1460 -1146.3591 25.4346 0.0 0.0 0.0
//			POINT_CAMERA_AT_CAR sweet_car FIXED JUMP_CUT
		ENDIF

		LOAD_SCENE 2226.3889 -1171.6877 24.7265
		DO_FADE 250 FADE_IN
		
		IF NOT IS_CAR_DEAD sweet_car
			START_PLAYBACK_RECORDED_CAR sweet_car 350
		ENDIF
		CAMERA_SET_VECTOR_MOVE 2236.1460 -1146.3591 25.4346 2236.1460 -1146.3591 25.4346 2500 TRUE
		CAMERA_SET_VECTOR_TRACK 2235.9368 -1145.3813 25.4440 2235.3645 -1145.7352 25.444 2500 TRUE		
		TIMERA = 0
		
		moteldeal_f1flag = 3
	ENDIF
ENDIF
// camera as car comes into courtyard
IF moteldeal_f1flag = 3
	IF TIMERA > 2500//2000

		//////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////
		skipcutscene_f1flag = 0
		SKIP_CUTSCENE_START
		//////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////

		SET_FIXED_CAMERA_POSITION 2220.6746 -1161.4014 26.4187 0.0 0.0 0.0 //camera inside courtyard
		POINT_CAMERA_AT_POINT 2220.9478 -1160.4401 26.3852 JUMP_CUT
		
		IF NOT IS_CAR_DEAD sweet_car 						
			POP_CAR_PANEL sweet_car WINDSCREEN_PANEL FALSE
		ENDIF

		TIMERA = 0
		moteldeal_f1flag = 4
	ENDIF
ENDIF

// chatting in car and creating other cars and peds
IF moteldeal_f1flag = 4
	IF TIMERA > 2750
		IF NOT IS_CAR_DEAD sweet_car
			IF NOT IS_CHAR_DEAD sweet
				IF NOT IS_CHAR_DEAD ryder
					IF NOT IS_CHAR_DEAD big_smoke
						IF HAS_MISSION_AUDIO_LOADED 1	
							IF HAS_MISSION_AUDIO_LOADED 2

								IF IS_PLAYBACK_GOING_ON_FOR_CAR sweet_car
									STOP_PLAYBACK_RECORDED_CAR sweet_car
								ENDIF	
								
								SET_NEAR_CLIP 0.1

								SET_CAR_COORDINATES sweet_car 2224.3572 -1149.7413 24.8342
								SET_CAR_HEADING sweet_car 180.1644

								SET_FIXED_CAMERA_POSITION 2224.3838 -1150.7454 26.0663 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 2224.3892 -1149.7494 25.9763 JUMP_CUT

								TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1
								TASK_PLAY_ANIM sweet CAR_Sc1_BL CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1
								TASK_PLAY_ANIM big_smoke CAR_Sc1_FR CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1
								TASK_PLAY_ANIM ryder CAR_Sc1_BR CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1

								PLAY_MISSION_AUDIO 1
								PRINT_NOW FIN1_HA 5000 1 //Ok, it’s strictly one rep per set – you guys are gonna have to wait here.
								START_CHAR_FACIAL_TALK sweet 10000 
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									WAIT 0
								ENDWHILE
								CLEAR_PRINTS
								CLEAR_MISSION_AUDIO 1
								IF NOT IS_CHAR_DEAD sweet
									STOP_CHAR_FACIAL_TALK sweet
								ENDIF
								LOAD_MISSION_AUDIO 1 SOUND_FIN1_HC //thanks, homie, but I’m down with these boys.

								PLAY_MISSION_AUDIO 2
								PRINT_NOW FIN1_HB 5000 1 //We’ll be here just in case, bro.
								START_CHAR_FACIAL_TALK scplayer 10000 
								WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
									WAIT 0
								ENDWHILE
								CLEAR_PRINTS
								CLEAR_MISSION_AUDIO 2
								STOP_CHAR_FACIAL_TALK scplayer
								moteldeal_f1flag = 5
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 5
	IF HAS_MISSION_AUDIO_LOADED 1
		IF NOT IS_CHAR_DEAD sweet
			PLAY_MISSION_AUDIO 1
			PRINT_NOW FIN1_HC 5000 1 //thanks, homie, but I’m down with these boys.
			START_CHAR_FACIAL_TALK sweet 10000
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
			ENDWHILE
			CLEAR_PRINTS
			IF NOT IS_CHAR_DEAD sweet
				STOP_CHAR_FACIAL_TALK sweet
			ENDIF
			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_FIN1_HD //I don’t like this, man. Look at all them other familiy hoods! They used to be Grove Streets.
			LOAD_MISSION_AUDIO 2 SOUND_FIN1_HE	//Relax.  We straight, they straight.
			moteldeal_f1flag = 6
		ENDIF
	ENDIF
ENDIF	

//sweet leaves the car
IF moteldeal_f1flag = 6
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CHAR_DEAD big_smoke
					IF IS_CHAR_IN_CAR sweet sweet_car //IF IS_CHAR_SITTING_IN_CAR sweet sweet_car 
						TASK_LEAVE_CAR sweet sweet_car
						moteldeal_f1flag = 7
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

//sweet walks up the stairs
IF moteldeal_f1flag = 7
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD sweet
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CHAR_SITTING_IN_CAR sweet sweet_car
						SET_NEAR_CLIP 0.2
						SET_FIXED_CAMERA_POSITION 2223.6252 -1146.9951 26.1711 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2224.3557 -1147.6769 26.2099 JUMP_CUT
						FLUSH_ROUTE
						EXTEND_ROUTE 2230.81 -1159.59 24.4
						EXTEND_ROUTE 2233.75 -1159.83 25.29
						TASK_FOLLOW_POINT_ROUTE sweet PEDMOVE_RUN FOLLOW_ROUTE_ONCE
						TASK_PLAY_ANIM scplayer CAR_Sc2_FL CAR_CHAT 8.0 FALSE FALSE FALSE TRUE -1
						TIMERA = 0
						moteldeal_f1flag = 8
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 8
	IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CHAR_DEAD big_smoke
				IF LOCATE_CHAR_ON_FOOT_2D sweet 2233.75 -1159.83 2.8 2.8 FALSE //1.0 1.0
					moteldeal_f1flag = 9
				ELSE
					IF TIMERA > 10000
						SET_CHAR_COORDINATES sweet 2233.75 -1159.83 25.29 
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF 


//in car chat
IF moteldeal_f1flag = 9
	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CHAR_DEAD big_smoke
			IF HAS_MISSION_AUDIO_LOADED 1
				SET_NEAR_CLIP 0.1
				SET_FIXED_CAMERA_POSITION 2225.0601 -1148.1921 26.0902 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2224.4465 -1148.9805 26.0466 JUMP_CUT

				DELETE_CHAR sweet
				TASK_PLAY_ANIM scplayer CAR_Sc3_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM big_smoke CAR_Sc3_FR CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM ryder CAR_Sc3_BR CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
					
				PLAY_MISSION_AUDIO 1
				PRINT_NOW FIN1_HD 5000 1 //I don’t like this, man. Look at all them other familiy hoods! They used to be Grove Streets.
				IF NOT IS_CHAR_DEAD ryder
					START_CHAR_FACIAL_TALK ryder 10000
				ENDIF
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
				IF NOT IS_CHAR_DEAD ryder
					STOP_CHAR_FACIAL_TALK ryder
				ENDIF
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_FIN1_HG //Feel a little exposed, but I’m good.
				TIMERA = 0
				moteldeal_f1flag = 10
			ENDIF
		ENDIF
	ENDIF
ENDIF

//in car chat
IF moteldeal_f1flag = 10
	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CHAR_DEAD big_smoke
			IF HAS_MISSION_AUDIO_LOADED 2
				SET_FIXED_CAMERA_POSITION 2224.3921 -1148.6056 25.9857 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2224.3960 -1149.6051 25.955 JUMP_CUT
				//SET_FIXED_CAMERA_POSITION 2224.3059 -1148.6110 26.1209 0.0 0.0 0.0
				//POINT_CAMERA_AT_POINT 2224.4033 -1149.6036 26.0508 JUMP_CUT

				PLAY_MISSION_AUDIO 2
				PRINT_NOW FIN1_HE  5000 1 //Relax.  We straight, they straight.
				START_CHAR_FACIAL_TALK scplayer 10000
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				ENDWHILE
				CLEAR_PRINTS
				STOP_CHAR_FACIAL_TALK scplayer
				LOAD_MISSION_AUDIO 2 SOUND_FIN1_HH	//This is the Los Santos Police Department; Everybody stay where you are!
				moteldeal_f1flag = 11
			ENDIF
		ENDIF
	ENDIF
ENDIF

//in car chat
IF moteldeal_f1flag = 11
	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CHAR_DEAD big_smoke
			IF HAS_MISSION_AUDIO_LOADED 1

				PLAY_MISSION_AUDIO 1
				PRINT_NOW FIN1_HG 5000 1 //Feel a little exposed, but I’m good.
				IF NOT IS_CHAR_DEAD big_smoke
					START_CHAR_FACIAL_TALK big_smoke 10000
				ENDIF

				CREATE_CAR POLMAV 2227.0295 -1130.199 25.305 extpoliceheli_f1
				START_PLAYBACK_RECORDED_CAR extpoliceheli_f1 352
				IF IS_PLAYBACK_GOING_ON_FOR_CAR extpoliceheli_f1
					PAUSE_PLAYBACK_RECORDED_CAR extpoliceheli_f1
				ENDIF
				CREATE_CHAR_INSIDE_CAR extpoliceheli_f1 PEDTYPE_MISSION4 SWAT exthelidriver_f1
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
				LOCK_CAR_DOORS extpoliceheli_f1 CARLOCK_LOCKED
				SET_CHAR_CANT_BE_DRAGGED_OUT exthelidriver_f1 TRUE
				SET_CHAR_DECISION_MAKER exthelidriver_f1 motel_DM
				SET_HELI_BLADES_FULL_SPEED extpoliceheli_f1

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
				IF NOT IS_CHAR_DEAD big_smoke
					STOP_CHAR_FACIAL_TALK big_smoke
				ENDIF
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_FIN1_HI	//Oh shit!

				moteldeal_f1flag = 12
			ENDIF
		ENDIF
	ENDIF
ENDIF

// zoom shot of heli flying to motel
IF moteldeal_f1flag = 12
	IF NOT IS_CAR_DEAD extpoliceheli_f1
		IF IS_PLAYBACK_GOING_ON_FOR_CAR extpoliceheli_f1
			IF HAS_MISSION_AUDIO_LOADED 2
				SET_NEAR_CLIP 0.2
				UNPAUSE_PLAYBACK_RECORDED_CAR extpoliceheli_f1
				SET_FIXED_CAMERA_POSITION 2182.5217 -1083.6869 61.5308 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2182.9958 -1084.4630 61.115 JUMP_CUT
				TIMERA = 0
				moteldeal_f1flag = 13
			ENDIF
		ENDIF
	ENDIF
ENDIF

// Heli arriving at motel
IF moteldeal_f1flag = 13
	IF NOT IS_CAR_DEAD extpoliceheli_f1
		IF TIMERA > 3000
			TIMERA = 0
			SET_FIXED_CAMERA_POSITION 2232.1777 -1149.9021 33.2809 0.0 0.0 0.0
			POINT_CAMERA_AT_CAR extpoliceheli_f1 FIXED JUMP_CUT		
			CREATE_SEARCHLIGHT_ON_VEHICLE extpoliceheli_f1 0.0 1.0 -0.5 2228.36 -1171.48 25.82 5.0 0.4 helispotlight_f1
			MOVE_SEARCHLIGHT_BETWEEN_COORDS helispotlight_f1 2228.36 -1171.48 25.82 2215.41 -1142.51 25.5 0.5

			PLAY_MISSION_AUDIO 2
			PRINT_NOW FIN1_HH 5000 1 //This is the Los Santos Police Department; Everybody stay where you are!
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
				WAIT 0
			ENDWHILE
			CLEAR_PRINTS
			LOAD_MISSION_AUDIO 2 SOUND_FIN1_CI	//All units Rock’n’roll!
			REMOVE_ANIMATION CAR_CHAT
			moteldeal_f1flag = 14
		ENDIF
	ENDIF
ENDIF

//player getting out the car
IF moteldeal_f1flag = 14
	IF NOT IS_CAR_DEAD extpoliceheli_f1
		IF IS_PLAYBACK_GOING_ON_FOR_CAR extpoliceheli_f1
			IF TIMERA > 3000
				STOP_PLAYBACK_RECORDED_CAR extpoliceheli_f1
				SET_FIXED_CAMERA_POSITION 2226.9976 -1144.5411 25.3493 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2226.5945 -1145.4496 25.4594 JUMP_CUT
				START_PLAYBACK_RECORDED_CAR extpoliceheli_f1 353 ///////////////////////354
				//
				IF NOT IS_CHAR_DEAD grove3_f1
					TASK_GO_STRAIGHT_TO_COORD grove3_f1 2219.71 -1178.52 25.34 PEDMOVE_RUN -1
				ENDIF
				IF NOT IS_CHAR_DEAD grove4_f1
					TASK_GO_STRAIGHT_TO_COORD grove4_f1 2206.65 -1172.63 25.08 PEDMOVE_RUN -1
				ENDIF

				//
				TIMERA = 0
				moteldeal_f1flag = 15
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 15
	IF NOT IS_CAR_DEAD extpoliceheli_f1
		IF NOT IS_CAR_DEAD sweet_car
			IF IS_CHAR_IN_ANY_CAR scplayer
				IF TIMERA > 2000
					IF HAS_MISSION_AUDIO_LOADED 1
						PLAY_MISSION_AUDIO 1
						PRINT_NOW FIN1_HI 5000 1 //Oh shit!
						TASK_LEAVE_ANY_CAR scplayer
						IF NOT IS_CHAR_DEAD big_smoke
							IF NOT IS_CHAR_DEAD ryder		
								CLEAR_LOOK_AT big_smoke
								CLEAR_LOOK_AT ryder
								TASK_LOOK_AT_CHAR big_smoke scplayer 15000
								TASK_LOOK_AT_CHAR ryder scplayer 15000
							ENDIF
						ENDIF
						TIMERA = 0
						moteldeal_f1flag = 16
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 16
	IF TIMERA > 2000
		IF HAS_MISSION_AUDIO_FINISHED 1
			IF HAS_MISSION_AUDIO_LOADED 2
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				//
				IF NOT IS_CHAR_DEAD grove3_f1
					TASK_GO_STRAIGHT_TO_COORD grove3_f1 2203.25 -1178.87 25.44 PEDMOVE_RUN -1
				ENDIF
				IF NOT IS_CHAR_DEAD grove4_f1
					TASK_GO_STRAIGHT_TO_COORD grove4_f1 2204.15 -1153.74 25.58 PEDMOVE_RUN -1
				ENDIF
				//
				SET_FIXED_CAMERA_POSITION 2224.0667 -1162.7069 40.9275 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2223.8423 -1163.2263 40.1030 JUMP_CUT
				SET_TIME_SCALE 0.5
				CREATE_SWAT_ROPE PEDTYPE_MISSION4 SWAT 2223.56 -1168.05 32.28 swatrope1_f1 //front left 2223.98 -1167.86 32.39
				GIVE_WEAPON_TO_CHAR swatrope1_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_ACCURACY swatrope1_f1 30
				PLAY_MISSION_AUDIO 2 
				PRINT_NOW FIN1_CI 3000 1 //All units Rock’n’roll
				LOAD_MISSION_AUDIO 1 SOUND_FIN1_HJ	//Man, what you doin’?
				WAIT 100
				CREATE_SWAT_ROPE PEDTYPE_MISSION4 SWAT 2222.63 -1166.01 32.25 swatrope2_f1	//rear left
				GIVE_WEAPON_TO_CHAR swatrope2_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_ACCURACY swatrope2_f1 30
				WAIT 50
				CREATE_SWAT_ROPE PEDTYPE_MISSION4 SWAT 2221.58 -1168.9 32.27 swatrope3_f1	//front right
				GIVE_WEAPON_TO_CHAR swatrope3_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_ACCURACY swatrope3_f1 30
				WAIT 100
				CREATE_SWAT_ROPE PEDTYPE_MISSION4 SWAT 2220.74 -1167.28 32.22 swatrope4_f1	//rear right
				GIVE_WEAPON_TO_CHAR swatrope4_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_ACCURACY swatrope4_f1 30
				WAIT 500

				IF NOT IS_CHAR_DEAD grove3_f1
					GIVE_WEAPON_TO_CHAR grove3_f1 WEAPONTYPE_PISTOL 9999
					TASK_DUCK grove3_f1 10000
				ENDIF

				IF NOT IS_CHAR_DEAD grove4_f1
					GIVE_WEAPON_TO_CHAR grove4_f1 WEAPONTYPE_PISTOL 9999
					TASK_GO_STRAIGHT_TO_COORD grove4_f1 2221.179 -1177.662 25.767 PEDMOVE_RUN 5000
				ENDIF

				SET_FIXED_CAMERA_POSITION 2222.3960 -1165.5629 24.9959 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2222.3159 -1165.8301 25.9562 JUMP_CUT
				SET_CHAR_COORDINATES scplayer 2226.1436 -1149.9968 24.8496 
				SET_CHAR_HEADING scplayer 102.7208
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 60

				TIMERA = 0
				moteldeal_f1flag = 17

			ENDIF
		ENDIF
	ENDIF
ENDIF


IF moteldeal_f1flag = 17
	IF TIMERA > 1500 //1600
		IF HAS_MISSION_AUDIO_FINISHED 2
			IF HAS_MISSION_AUDIO_LOADED 1
				CLEAR_MISSION_AUDIO 2
				CLEAR_PRINTS
				LOAD_MISSION_AUDIO 2 SOUND_FIN1_HK	//Get back in, Carl, we’re out of here!
				SET_TIME_SCALE 1.0
				SET_FIXED_CAMERA_POSITION 2222.5940 -1150.7103 26.1976 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2223.4031 -1150.1393 26.0591 JUMP_CUT
				
				TIMERA = 0

				PLAY_MISSION_AUDIO 1
				PRINT_NOW FIN1_HJ 5000 1 //Man, what you doin’?
				IF NOT IS_CHAR_DEAD ryder
					START_CHAR_FACIAL_TALK ryder 10000
				ENDIF
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
				IF NOT IS_CHAR_DEAD ryder
					STOP_CHAR_FACIAL_TALK ryder
					TASK_LOOK_AT_CHAR scplayer ryder 3000 
				ENDIF
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_FIN1_HL //I ain't leavin' my brother, I ain't no buster!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0
				ENDWHILE
				PLAY_MISSION_AUDIO 2
				PRINT_NOW FIN1_HK 5000 1//Get back in, Carl, we’re out of here!
				IF NOT IS_CHAR_DEAD big_smoke
					START_CHAR_FACIAL_TALK big_smoke 10000
				ENDIF
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				ENDWHILE
				IF NOT IS_CHAR_DEAD big_smoke
					STOP_CHAR_FACIAL_TALK big_smoke
				ENDIF
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 2
				LOAD_MISSION_AUDIO 2 SOUND_FIN1_HM //Man, it’s every motherfucker for himself!
				
				moteldeal_f1flag = 18
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 18
	IF NOT IS_CHAR_DEAD swatrope1_f1
		IF NOT IS_CHAR_DEAD swatrope2_f1
			IF NOT IS_CHAR_DEAD swatrope3_f1
				IF NOT IS_CHAR_DEAD swatrope4_f1
					
					IF swatrope1_f1flag = 0
						GET_SCRIPT_TASK_STATUS swatrope1_f1 CREATE_SWAT_ROPE swatrope1_f1status
							IF swatrope1_f1status = FINISHED_TASK
								TASK_GO_STRAIGHT_TO_COORD swatrope1_f1 2226.43 -1155.37 25.33 PEDMOVE_RUN 5000
								swatrope1_f1flag = 1
							ENDIF
					ENDIF

					IF swatrope2_f1flag = 0
						GET_SCRIPT_TASK_STATUS swatrope2_f1 CREATE_SWAT_ROPE swatrope2_f1status
							IF swatrope2_f1status = FINISHED_TASK
								TASK_GO_STRAIGHT_TO_COORD swatrope2_f1 2230.52 -1165.15 25.63 PEDMOVE_RUN 5000
								swatrope2_f1flag = 1
							ENDIF
					ENDIF

					IF swatrope3_f1flag = 0
						GET_SCRIPT_TASK_STATUS swatrope3_f1 CREATE_SWAT_ROPE swatrope3_f1status
							IF swatrope3_f1status = FINISHED_TASK
								TASK_GO_STRAIGHT_TO_COORD swatrope3_f1 2217.53 -1163.18 25.27 PEDMOVE_RUN 5000
								swatrope3_f1flag = 1
							ENDIF
					ENDIF
					
					IF swatrope4_f1flag = 0
						GET_SCRIPT_TASK_STATUS swatrope4_f1 CREATE_SWAT_ROPE swatrope4_f1status
							IF swatrope4_f1status = FINISHED_TASK
								TASK_GO_STRAIGHT_TO_COORD swatrope4_f1 2226.25 -1167.00 25.04 PEDMOVE_RUN 5000
								swatrope4_f1flag = 1
						 	ENDIF
					ENDIF

					IF swatrope1_f1status = FINISHED_TASK
						IF swatrope2_f1status = FINISHED_TASK
							IF swatrope3_f1status = FINISHED_TASK
								IF swatrope4_f1status = FINISHED_TASK				 
									moteldeal_f1flag = 21
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					IF TIMERA > 3000
					  	TASK_GO_STRAIGHT_TO_COORD swatrope1_f1 2226.43 -1155.37 25.33 PEDMOVE_RUN 5000
					  	TASK_GO_STRAIGHT_TO_COORD swatrope2_f1 2230.52 -1165.15 25.63 PEDMOVE_RUN 5000
					  	TASK_GO_STRAIGHT_TO_COORD swatrope3_f1 2217.53 -1163.18 25.27 PEDMOVE_RUN 5000
					  	TASK_GO_STRAIGHT_TO_COORD swatrope4_f1 2226.25 -1167.00 25.04 PEDMOVE_RUN 5000
					  	moteldeal_f1flag = 19
					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF	
ENDIF


IF moteldeal_f1flag = 19
	IF TIMERA > 3000
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CAR_DEAD sweet_car
					IF HAS_MISSION_AUDIO_LOADED 1
						IF HAS_MISSION_AUDIO_LOADED 2

							REQUEST_CAR_RECORDING 354
							REQUEST_CAR_RECORDING 355
							REQUEST_CAR_RECORDING 356

							TASK_SHUFFLE_TO_NEXT_CAR_SEAT big_smoke sweet_car

							PLAY_MISSION_AUDIO 1
							PRINT_NOW FIN1_HL 5000 1 //I ain't leavin' my brother, I ain't no buster!
							IF NOT IS_CHAR_DEAD scplayer
								START_CHAR_FACIAL_TALK scplayer 10000
							ENDIF
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							IF NOT IS_CHAR_DEAD scplayer
								STOP_CHAR_FACIAL_TALK scplayer
							ENDIF
							CLEAR_PRINTS
							CLEAR_MISSION_AUDIO 1

							WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 354
								OR NOT HAS_CAR_RECORDING_BEEN_LOADED 355
								OR NOT HAS_CAR_RECORDING_BEEN_LOADED 356
								WAIT 0
							ENDWHILE

							IF NOT IS_CAR_DEAD sweet_car
								START_PLAYBACK_RECORDED_CAR sweet_car 356
							ENDIF

							PLAY_MISSION_AUDIO 2
							PRINT_NOW FIN1_HM 3000 1 //Man, it’s every motherfucker for himself!							
							IF NOT IS_CHAR_DEAD ryder
								START_CHAR_FACIAL_TALK ryder 10000
							ENDIF

							IF NOT IS_CAR_DEAD extpoliceheli_f1
								IF IS_PLAYBACK_GOING_ON_FOR_CAR extpoliceheli_f1
									STOP_PLAYBACK_RECORDED_CAR extpoliceheli_f1
								ENDIF
								HELI_GOTO_COORDS extpoliceheli_f1 2217.58 -1167.67 34.61 10.0 10.0
							ENDIF 

							TIMERA = 0
							moteldeal_f1flag = 20
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 20
	IF TIMERA > 2000

		CREATE_CAR ENFORCER 2217.236 -1019.97 30.7 extpolicevan1_f1
		SET_CAR_HEADING extpolicevan1_f1 176.5
		SWITCH_CAR_SIREN extpolicevan1_f1 ON
		START_PLAYBACK_RECORDED_CAR extpolicevan1_f1 354
		CREATE_CHAR_INSIDE_CAR extpolicevan1_f1	PEDTYPE_MISSION4 SWAT extvan1driver_f1
		GIVE_WEAPON_TO_CHAR extvan1driver_f1 WEAPONTYPE_MP5 3000
		SET_CHAR_ACCURACY extvan1driver_f1 30
		CREATE_CHAR_AS_PASSENGER extpolicevan1_f1 PEDTYPE_MISSION4 SWAT 1 swat1_f1
		GIVE_WEAPON_TO_CHAR swat1_f1 WEAPONTYPE_MP5 3000
		SET_CHAR_ACCURACY swat1_f1 30

		CREATE_CAR ENFORCER 2253.89 -1144.77 26.47 extpolicevan2_f1
		SET_CAR_HEADING extpolicevan2_f1 89.947
		SWITCH_CAR_SIREN extpolicevan2_f1 ON
		START_PLAYBACK_RECORDED_CAR extpolicevan2_f1 355
		CREATE_CHAR_INSIDE_CAR extpolicevan2_f1	PEDTYPE_MISSION4 SWAT extvan2driver_f1
		SET_CHAR_ACCURACY extvan2driver_f1 30
		GIVE_WEAPON_TO_CHAR extvan2driver_f1 WEAPONTYPE_MP5 3000
		CREATE_CHAR_AS_PASSENGER extpolicevan2_f1 PEDTYPE_MISSION4 SWAT 1 swat3_f1
		SET_CHAR_ACCURACY swat3_f1 30
		GIVE_WEAPON_TO_CHAR swat3_f1 WEAPONTYPE_MP5 3000
		
		IF NOT IS_CHAR_DEAD grove1_f1
			SET_CHAR_RELATIONSHIP grove1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove1_f1 extmotel_DM
		ENDIF
		IF NOT IS_CHAR_DEAD grove2_f1
			SET_CHAR_RELATIONSHIP grove2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove2_f1 extmotel_DM
		ENDIF
		IF NOT IS_CHAR_DEAD grove3_f1
			SET_CHAR_RELATIONSHIP grove3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove3_f1 extmotel_DM
		ENDIF
		IF NOT IS_CHAR_DEAD grove4_f1
			SET_CHAR_RELATIONSHIP grove4_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove4_f1 extmotel_DM
		ENDIF
		IF NOT IS_CHAR_DEAD swatrope1_f1
			SET_CHAR_RELATIONSHIP swatrope1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP swatrope1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER swatrope1_f1 extmotel_DM
		ENDIF
		IF NOT IS_CHAR_DEAD swatrope2_f1
			SET_CHAR_RELATIONSHIP swatrope2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP swatrope2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER swatrope2_f1 extmotel_DM
		ENDIF
		IF NOT IS_CHAR_DEAD swatrope3_f1
			SET_CHAR_RELATIONSHIP swatrope3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP swatrope3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER swatrope3_f1 extmotel_DM
		ENDIF
		IF NOT IS_CHAR_DEAD swatrope4_f1
			SET_CHAR_RELATIONSHIP swatrope4_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_DECISION_MAKER swatrope4_f1 extmotel_DM
		ENDIF

		SET_FIXED_CAMERA_POSITION 2203.1633 -1178.8915 31.8778 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2203.8975 -1178.2249 31.7493 JUMP_CUT

		TASK_TOGGLE_DUCK scplayer TRUE
		TASK_GO_STRAIGHT_TO_COORD scplayer 2231.569 -1155.75 25.85 PEDMOVE_WALK -1

		IF NOT IS_CHAR_DEAD grove1_f1
			GIVE_WEAPON_TO_CHAR grove1_f1 WEAPONTYPE_PISTOL 9999
			TASK_GO_STRAIGHT_TO_COORD grove1_f1 2228.421 -1164.66 25.766 PEDMOVE_RUN 5000
		ENDIF

		IF NOT IS_CHAR_DEAD grove2_f1
			GIVE_WEAPON_TO_CHAR grove2_f1 WEAPONTYPE_PISTOL 9999
			TASK_GO_STRAIGHT_TO_COORD grove2_f1 2221.47 -1164.66 25.766 PEDMOVE_RUN 5000
		ENDIF

		TIMERB = 0

		moteldeal_f1flag = 21
	ENDIF
ENDIF

IF moteldeal_f1flag = 21
	IF NOT IS_CAR_DEAD extpolicevan1_f1
		IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR extpolicevan1_f1
			
			IF NOT IS_CHAR_DEAD swat1_f1
				IF IS_CHAR_IN_CAR swat1_f1 extpolicevan1_f1
					TASK_LEAVE_CAR swat1_f1 extpolicevan1_f1
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD extvan1driver_f1
				IF IS_CHAR_IN_CAR extvan1driver_f1 extpolicevan1_f1
					TASK_LEAVE_CAR extvan1driver_f1 extpolicevan1_f1
				ENDIF
			ENDIF
			moteldeal_f1flag = 22
		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 22
	IF NOT IS_CAR_DEAD extpolicevan2_f1
		IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR extpolicevan2_f1

			IF NOT IS_CHAR_DEAD swat3_f1
				IF IS_CHAR_IN_CAR swat3_f1 extpolicevan2_f1
					TASK_LEAVE_CAR swat3_f1 extpolicevan2_f1
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD extvan2driver_f1
				IF IS_CHAR_IN_CAR extvan2driver_f1 extpolicevan2_f1
					TASK_LEAVE_CAR extvan2driver_f1 extpolicevan2_f1
				ENDIF
			ENDIF
			
			IF NOT IS_CHAR_DEAD extvan1driver_f1
				SET_CHAR_RELATIONSHIP extvan1driver_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
				SET_CHAR_RELATIONSHIP extvan1driver_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				SET_CHAR_DECISION_MAKER extvan1driver_f1 extmotel_DM
			ENDIF
			IF NOT IS_CHAR_DEAD extvan2driver_f1
				SET_CHAR_RELATIONSHIP extvan2driver_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
				SET_CHAR_DECISION_MAKER extvan2driver_f1 extmotel_DM
			ENDIF
			IF NOT IS_CHAR_DEAD swat1_f1
				SET_CHAR_RELATIONSHIP swat1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
				SET_CHAR_DECISION_MAKER swat1_f1 extmotel_DM
			ENDIF
			IF NOT IS_CHAR_DEAD swat3_f1
				SET_CHAR_RELATIONSHIP swat3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
				SET_CHAR_DECISION_MAKER swat3_f1 extmotel_DM
			ENDIF
			SET_CHAR_HEADING scplayer 150.0
			TIMERA = 0
			moteldeal_f1flag = 23

		ENDIF
	ENDIF
ENDIF

IF moteldeal_f1flag = 23
	
	IF extvan1driver_f1flag = 0
		IF NOT IS_CHAR_DEAD extvan1driver_f1
			IF NOT IS_CHAR_IN_ANY_CAR extvan1driver_f1
				TASK_DUCK extvan1driver_f1 5000
				extvan1driver_f1flag = 1
			ENDIF
		ENDIF
	ENDIF

	IF swat1_f1flag = 0
		IF NOT IS_CHAR_DEAD swat1_f1
			IF NOT IS_CHAR_IN_ANY_CAR swat1_f1
				TASK_GO_STRAIGHT_TO_COORD swat1_f1 2203.77 -1156.44 25.31 PEDMOVE_RUN 5000
				swat1_f1flag = 1
			ENDIF
		ENDIF
	ENDIF

	IF extvan2driver_f1flag = 0
		IF NOT IS_CHAR_DEAD extvan2driver_f1
			IF NOT IS_CHAR_IN_ANY_CAR extvan2driver_f1
				FLUSH_ROUTE
				EXTEND_ROUTE 2215.4 -1146.19 25.85
				EXTEND_ROUTE 2214.67 -1149.71 25.47
				EXTEND_ROUTE 2204.83 -1157.04 25.88
				EXTEND_ROUTE 2209.01 -1166.46 25.89
				TASK_FOLLOW_POINT_ROUTE extvan2driver_f1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
				extvan2driver_f1flag = 1
			ENDIF
		ENDIF
	ENDIF

	IF swat3_f1flag = 0
		IF NOT IS_CHAR_DEAD swat3_f1
			IF NOT IS_CHAR_IN_ANY_CAR swat3_f1
				FLUSH_ROUTE
				EXTEND_ROUTE 2222.47 -1146.52 25.43
				EXTEND_ROUTE 2215.4 -1146.19 25.85
				EXTEND_ROUTE 2204.83 -1157.04 25.88
				EXTEND_ROUTE 2206.61 -1168.51 25.89
				EXTEND_ROUTE 2211.00 -1171.72 25.55
				TASK_FOLLOW_POINT_ROUTE swat3_f1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
				swat3_f1flag = 1
			ENDIF
		ENDIF
	ENDIF

ENDIF
	
IF moteldeal_f1flag = 23
	IF TIMERA > 1000

		//////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////
		skipcutscene_f1flag = 1
		SKIP_CUTSCENE_END
		//////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////

		//PLAYER HAS SKIPPED CUTSCENE
		IF skipcutscene_f1flag = 0
		
			DO_FADE 500 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			CAMERA_RESET_NEW_SCRIPTABLES
			//set char coordinates and location
			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 2231.569 -1155.75 24.85 
				SET_CHAR_HEADING scplayer 150.0
			ELSE
				SET_CHAR_COORDINATES scplayer 2231.569 -1155.75 24.85
				SET_CHAR_HEADING scplayer 150.0
			ENDIF
			
			//delete cars and chars
			DELETE_CHAR sweet
			DELETE_CHAR big_smoke
			DELETE_CHAR ryder

			UNLOAD_SPECIAL_CHARACTER 2
			UNLOAD_SPECIAL_CHARACTER 3

			REQUEST_MODEL GREENWOO
			REQUEST_MODEL FAM3
			REQUEST_MODEL FAM2

			REQUEST_MODEL MP5LNG
			REQUEST_MODEL COLT45
			REQUEST_MODEL SWAT
			REQUEST_MODEL POLMAV
			REQUEST_MODEL ENFORCER

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_MODEL_LOADED GREENWOO
				OR NOT HAS_MODEL_LOADED FAM3
				OR NOT HAS_MODEL_LOADED FAM2
				WAIT 0
			ENDWHILE


			WHILE NOT HAS_MODEL_LOADED MP5LNG
				OR NOT HAS_MODEL_LOADED COLT45
				OR NOT HAS_MODEL_LOADED SWAT
				OR NOT HAS_MODEL_LOADED POLMAV
				OR NOT HAS_MODEL_LOADED ENFORCER
				WAIT 0
			ENDWHILE
	  
			DELETE_CHAR exthelidriver_f1
			DELETE_CHAR extvan1driver_f1
			DELETE_CHAR extvan2driver_f1
			DELETE_CHAR swatrope1_f1
			DELETE_CHAR swatrope2_f1
			DELETE_CHAR swatrope3_f1
			DELETE_CHAR swatrope4_f1
			DELETE_CHAR swat1_f1
//			DELETE_CHAR swat2_f1
			DELETE_CHAR swat3_f1
//			DELETE_CHAR swat4_f1			
			DELETE_CHAR grove1_f1
			DELETE_CHAR grove2_f1
			DELETE_CHAR grove3_f1
			DELETE_CHAR grove4_f1
//			DELETE_CHAR grove5_f1
//			DELETE_CHAR grove6_f1

			IF NOT IS_CAR_DEAD extpoliceheli_f1
				IF IS_PLAYBACK_GOING_ON_FOR_CAR extpoliceheli_f1
					STOP_PLAYBACK_RECORDED_CAR extpoliceheli_f1
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD extpolicevan1_f1
				IF IS_PLAYBACK_GOING_ON_FOR_CAR extpolicevan1_f1
					STOP_PLAYBACK_RECORDED_CAR extpolicevan1_f1
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD extpolicevan2_f1
				IF IS_PLAYBACK_GOING_ON_FOR_CAR extpolicevan2_f1
					STOP_PLAYBACK_RECORDED_CAR extpolicevan2_f1
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD sweet_car
				IF IS_PLAYBACK_GOING_ON_FOR_CAR sweet_car
					STOP_PLAYBACK_RECORDED_CAR sweet_car
				ENDIF
			ENDIF

			DELETE_CAR extpoliceheli_f1
			DELETE_CAR extpolicevan1_f1
			DELETE_CAR extpolicevan2_f1
			DELETE_CAR sweet_car
			DELETE_SEARCHLIGHT helispotlight_f1

			//create chars, set their decision makers and give them weapons

			CREATE_CAR ENFORCER 2210.2400 -1159.9757 24.7265 extpolicevan1_f1
			SET_CAR_HEADING extpolicevan1_f1 191.9611
			SWITCH_CAR_SIREN extpolicevan1_f1 ON

			CREATE_CAR ENFORCER 2218.7881 -1140.9927 24.7814 extpolicevan2_f1
			SET_CAR_HEADING extpolicevan2_f1 78.1047
			SWITCH_CAR_SIREN extpolicevan2_f1 ON

			CREATE_CAR POLMAV 2276.22 -1140.87 60.0 extpoliceheli_f1
			CREATE_CHAR_INSIDE_CAR extpoliceheli_f1 PEDTYPE_MISSION4 SWAT exthelidriver_f1
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
			LOCK_CAR_DOORS extpoliceheli_f1 CARLOCK_LOCKED
			SET_CHAR_CANT_BE_DRAGGED_OUT exthelidriver_f1 TRUE
			SET_HELI_BLADES_FULL_SPEED extpoliceheli_f1
			SET_CAR_FORWARD_SPEED extpoliceheli_f1 10.0
			HELI_GOTO_COORDS extpoliceheli_f1 2217.58 -1167.67 34.61 10.0 10.0
			SET_CAR_COORDINATES extpoliceheli_f1 2217.58 -1167.67 34.61
			CREATE_SEARCHLIGHT_ON_VEHICLE extpoliceheli_f1 0.0 1.0 -0.5 2228.36 -1171.48 25.82 5.0 0.4 helispotlight_f1
			MOVE_SEARCHLIGHT_BETWEEN_COORDS helispotlight_f1 2228.36 -1171.48 25.82 2215.41 -1142.51 25.5 0.5

			//swat 
			CREATE_CHAR PEDTYPE_MISSION4 SWAT 2209.01 -1166.46 24.89 extvan2driver_f1
			GIVE_WEAPON_TO_CHAR extvan2driver_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD extvan2driver_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY extvan2driver_f1 30

			CREATE_CHAR	PEDTYPE_MISSION4 SWAT 2213.52 -1161.97 24.77 extvan1driver_f1
			GIVE_WEAPON_TO_CHAR extvan1driver_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD extvan1driver_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY extvan1driver_f1 30

			CREATE_CHAR PEDTYPE_MISSION4 SWAT 2203.77 -1156.44 24.31 swat1_f1
			GIVE_WEAPON_TO_CHAR swat1_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD swat1_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY swat1_f1 30

			CREATE_CHAR PEDTYPE_MISSION4 SWAT 2212.95 -1179.16 24.31 swat3_f1
			GIVE_WEAPON_TO_CHAR swat3_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD swat3_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY swat3_f1 30
 										  
			CREATE_CHAR PEDTYPE_MISSION4 SWAT 2226.43 -1155.37 24.33 swatrope1_f1
			GIVE_WEAPON_TO_CHAR swatrope1_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD swatrope1_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY swatrope1_f1 30

			CREATE_CHAR PEDTYPE_MISSION4 SWAT 2230.52 -1165.15 24.63 swatrope2_f1
			GIVE_WEAPON_TO_CHAR swatrope2_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD swatrope2_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY swatrope2_f1 30

			CREATE_CHAR PEDTYPE_MISSION4 SWAT 2217.53 -1163.18 24.27 swatrope3_f1
			GIVE_WEAPON_TO_CHAR swatrope3_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD swatrope3_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY swatrope3_f1 30

			CREATE_CHAR PEDTYPE_MISSION4 SWAT 2226.25 -1167.00 24.04 swatrope4_f1
			GIVE_WEAPON_TO_CHAR swatrope4_f1 WEAPONTYPE_MP5 3000
			TASK_TURN_CHAR_TO_FACE_COORD swatrope4_f1 2217.87 -1162.6 25.33
			SET_CHAR_ACCURACY swatrope4_f1 30

			//groves
			CREATE_CHAR PEDTYPE_MISSION3 FAM3 2228.421 -1164.66 24.766 grove1_f1
			TASK_TURN_CHAR_TO_FACE_COORD grove1_f1 2217.87 -1162.6 25.33 
			SET_CHAR_DECISION_MAKER grove1_f1 extmotel_DM
			GIVE_WEAPON_TO_CHAR grove1_f1 WEAPONTYPE_PISTOL 9999
			SET_CHAR_ACCURACY grove1_f1 30
			SET_CHAR_NEVER_TARGETTED grove1_f1 TRUE

			CREATE_CHAR PEDTYPE_MISSION3 FAM3 2221.47 -1164.66 24.766 grove2_f1
			TASK_TURN_CHAR_TO_FACE_COORD grove2_f1 2217.87 -1162.6 25.33
			SET_CHAR_DECISION_MAKER grove2_f1 extmotel_DM
			GIVE_WEAPON_TO_CHAR grove2_f1 WEAPONTYPE_PISTOL 9999
			SET_CHAR_ACCURACY grove2_f1 40
			SET_CHAR_NEVER_TARGETTED grove2_f1 TRUE

			CREATE_CHAR PEDTYPE_MISSION3 FAM2 2227.2244 -1172.0699 24.7265 grove3_f1
			TASK_TURN_CHAR_TO_FACE_COORD grove3_f1 2217.87 -1162.6 25.33 
			SET_CHAR_DECISION_MAKER grove3_f1 extmotel_DM
			GIVE_WEAPON_TO_CHAR grove3_f1 WEAPONTYPE_PISTOL 9999
			SET_CHAR_ACCURACY grove3_f1 30
			SET_CHAR_NEVER_TARGETTED grove3_f1 TRUE

			CREATE_CHAR PEDTYPE_MISSION3 FAM2 2221.179 -1177.662 24.767 grove4_f1
			TASK_TURN_CHAR_TO_FACE_COORD grove4_f1 2217.87 -1162.6 25.33
			SET_CHAR_DECISION_MAKER grove4_f1 extmotel_DM
			GIVE_WEAPON_TO_CHAR grove4_f1 WEAPONTYPE_PISTOL 9999
			SET_CHAR_ACCURACY grove4_f1 40
			SET_CHAR_NEVER_TARGETTED grove4_f1 TRUE

			SET_CHAR_RELATIONSHIP grove1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove1_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP grove2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove2_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP grove3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove3_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP grove4_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
			SET_CHAR_DECISION_MAKER grove4_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP extvan1driver_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP extvan1driver_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER extvan1driver_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP extvan2driver_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP extvan2driver_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER extvan2driver_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP swatrope1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP swatrope1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER swatrope1_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP swatrope2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP swatrope2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER swatrope2_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP swatrope3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP swatrope3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER swatrope3_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP swatrope4_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_RELATIONSHIP swatrope4_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_DECISION_MAKER swatrope4_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP swat1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_DECISION_MAKER swat1_f1 extmotel_DM

			SET_CHAR_RELATIONSHIP swat3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_DECISION_MAKER swat3_f1 extmotel_DM

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			SET_TIME_SCALE 1.0
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			SET_CAMERA_BEHIND_PLAYER
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_SHOTGUN 60
			SET_CHAR_HEADING scplayer 192.0
			SET_PLAYER_CONTROL PLAYER1 ON
			DO_FADE 1500 FADE_IN

		ENDIF
		IF NOT IS_CAR_DEAD extpoliceheli_f1
			SET_CAR_HEALTH extpoliceheli_f1 700
		ENDIF
		DELETE_CHAR big_smoke
		DELETE_CHAR ryder
		DELETE_CAR sweet_car
		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
		UNLOAD_SPECIAL_CHARACTER 2
		UNLOAD_SPECIAL_CHARACTER 3
		REMOVE_ANIMATION CAR_CHAT

		CLEAR_PRINTS
		RESTORE_CAMERA_JUMPCUT
		REMOVE_BLIP motel_f1blip
		REMOVE_BLIP sweet_f1blip
		ADD_BLIP_FOR_COORD 2235.3 -1159.67 25.94 motel_f1blip
		SWITCH_WIDESCREEN OFF
		SET_CAMERA_BEHIND_PLAYER
		SET_PLAYER_CONTROL PLAYER1 ON
		PRINT_NOW LAF1_17 10000 1 //Go inside the motel and get Sweet out of there safely!
		moteldeal_f1flag = 24

	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////	Inside Motel	////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF moteldeal_f1flag = 24

	//marking cars as no longer needed if player destroys them outside the motel
	//heli and spotlight
	IF extpoliceheli_f1flag = 0
		IF IS_CAR_DEAD extpoliceheli_f1 
			MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
			DELETE_SEARCHLIGHT helispotlight_f1
			extpoliceheli_f1flag = 1
		ENDIF
	ENDIF

//	IF familycar2_f1flag = 0
//		IF IS_CAR_DEAD familycar2_f1
//			MARK_CAR_AS_NO_LONGER_NEEDED familycar2_f1
//			familycar2_f1flag = 1
//		ENDIF
//	ENDIF
//
//	IF familycar3_f1flag = 0
//		IF IS_CAR_DEAD familycar3_f1
//			MARK_CAR_AS_NO_LONGER_NEEDED familycar3_f1
//			familycar3_f1flag = 1
//		ENDIF
//	ENDIF
//
//	IF familycar2_f1flag = 0
//		IF familycar3_f1flag = 0
//			MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
//			familycar2_f1flag = 1
//			familycar3_f1flag = 1
//		ENDIF
//	ENDIF

	IF extpolicevan1_f1flag = 0
		IF IS_CAR_DEAD extpolicevan1_f1
			MARK_CAR_AS_NO_LONGER_NEEDED extpolicevan1_f1
			extpolicevan1_f1flag = 1
		ENDIF
	ENDIF

	IF extpolicevan2_f1flag = 0
		IF IS_CAR_DEAD extpolicevan2_f1
			MARK_CAR_AS_NO_LONGER_NEEDED extpolicevan2_f1
			extpolicevan2_f1flag = 1
		ENDIF
	ENDIF

	IF extpolicevan1_f1flag = 1
		IF extpolicevan2_f1flag = 1
			MARK_MODEL_AS_NO_LONGER_NEEDED ENFORCER 
			extpolicevan1_f1flag = 2
			extpolicevan2_f1flag = 2
		ENDIF
	ENDIF

	GET_CHAR_AREA_VISIBLE scplayer motel_interior
	
	IF motel_interior = 15	//player is inside motel

		DELETE_CHAR exthelidriver_f1
		DELETE_CHAR extvan1driver_f1
		DELETE_CHAR extvan2driver_f1
		DELETE_CHAR swatrope1_f1
		DELETE_CHAR swatrope2_f1
		DELETE_CHAR swatrope3_f1
		DELETE_CHAR swatrope4_f1
		DELETE_CHAR swat1_f1
		DELETE_CHAR swat3_f1
		DELETE_CHAR grove1_f1
		DELETE_CHAR grove2_f1
		DELETE_CHAR grove3_f1
		DELETE_CHAR grove4_f1
		DELETE_CAR extpoliceheli_f1
		DELETE_CAR extpolicevan1_f1
		DELETE_CAR extpolicevan2_f1
		DELETE_SEARCHLIGHT helispotlight_f1
		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
		MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
		MARK_MODEL_AS_NO_LONGER_NEEDED ENFORCER
		swat1_f1flag = 0
		swat2_f1flag = 0
		swat3_f1flag = 0
		swat4_f1flag = 0
		swatrope1_f1flag = 0
		swatrope1_f1flag = 0
		SET_PLAYER_CONTROL PLAYER1 OFF
		REMOVE_BLIP motel_f1blip
		REMOVE_ANIMATION CAR_CHAT

		CREATE_CHAR PEDTYPE_MISSION1 FAM2 2229.566 -1150.495 1029.0 grove1_f1 //grove guy that will fall off railings //.366
		SET_CHAR_HAS_USED_ENTRY_EXIT grove1_f1 2232.41 -1160.04 20.0
		SET_CHAR_HEADING grove1_f1 86.6836
		SET_CHAR_NEVER_TARGETTED grove1_f1 TRUE
		SET_CHAR_DECISION_MAKER grove1_f1 motel_DM
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER grove1_f1 motel_DM

		LOAD_MISSION_AUDIO 1 SOUND_FIN1_JC //Where the OG’s at – I gotta go get my brother, Sweet.
		LOAD_MISSION_AUDIO 2 SOUND_FIN1_AS //<loud explosion> Go Go Go!
		LOAD_MISSION_AUDIO 3 SOUND_CEILING_VENT_OPEN

		REQUEST_ANIMATION SWAT
		REQUEST_MODEL BFYPRO
		REQUEST_MODEL imy_skylight
		REQUEST_MODEL kmb_trolley
		REQUEST_ANIMATION KISSING

		LOAD_ALL_MODELS_NOW
		//LOAD_SCENE 2229.5913 -1150.6475 1028.7981

		WHILE NOT HAS_ANIMATION_LOADED SWAT
			OR NOT HAS_MODEL_LOADED BFYPRO
			OR NOT HAS_MODEL_LOADED imy_skylight
			OR NOT HAS_ANIMATION_LOADED KISSING
			OR NOT HAS_MODEL_LOADED kmb_trolley
			WAIT 0
		ENDWHILE

		WHILE NOT HAS_MISSION_AUDIO_LOADED 1
			OR NOT HAS_MISSION_AUDIO_LOADED 2
			OR NOT HAS_MISSION_AUDIO_LOADED 3
			WAIT 0
		ENDWHILE

		//pickups
		CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2240.686 -1187.269 1033.836 armour1_f1

		CREATE_OBJECT_NO_OFFSET imy_skylight 2246.07 -1191.242 1037.234 skylite_f1

		CREATE_OBJECT_NO_OFFSET lxr_motel_doorsim 2239.432 -1170.749 1029.997 breachdoor_f1
		SET_OBJECT_HEADING breachdoor_f1 270.0

		CREATE_OBJECT_NO_OFFSET lxr_motelvent 2217.072 -1188.664 1032.276 vent1_f1
		SET_OBJECT_HEADING vent1_f1 180.0

		CREATE_OBJECT lxr_motelvent 2193.188 -1164.511 1032.276 vent2_f1
		SET_OBJECT_HEADING vent2_f1 90.0
		FREEZE_OBJECT_POSITION vent1_f1 TRUE
		FREEZE_OBJECT_POSITION vent2_f1 TRUE

		CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 2227.16 -1148.02 1029.0 hoochie1_f1	//hoochie running downstairs
		SET_CHAR_HAS_USED_ENTRY_EXIT hoochie1_f1 2232.41 -1160.04 20.0
		SET_CHAR_NEVER_TARGETTED hoochie1_f1 TRUE
		SET_CHAR_DECISION_MAKER hoochie1_f1 motel_DM
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER hoochie1_f1 TRUE


		//sweet
		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2202.23 -1156.914 1029.852 sweet
		SET_CHAR_HAS_USED_ENTRY_EXIT sweet 2232.41 -1160.04 3.0
		SET_CHAR_AREA_VISIBLE sweet 15
		SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
		SET_CHAR_HEADING sweet 62.083
		SET_CHAR_NEVER_TARGETTED sweet TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet TRUE
		SET_CHAR_DECISION_MAKER sweet motel_DM
		ADD_BLIP_FOR_CHAR sweet sweet_f1blip
		SET_BLIP_AS_FRIENDLY sweet_f1blip TRUE
		SET_CHAR_HEALTH sweet 1000
		GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_MP5 9999
		SET_CHAR_PROOFS sweet FALSE TRUE TRUE TRUE TRUE

		//entrance
		CREATE_CHAR PEDTYPE_MISSION1 FAM2 2220.5242 -1137.8256 1026.7981 woundedgrove1_f1	//wounded guy that tells player where sweet is
		SET_CHAR_HAS_USED_ENTRY_EXIT woundedgrove1_f1 2232.41 -1160.04 20.0
		SET_CHAR_HEADING woundedgrove1_f1 181.8677
		TASK_PLAY_ANIM_NON_INTERRUPTABLE woundedgrove1_f1 gnstwall_injurd SWAT 8.0 TRUE FALSE FALSE FALSE -1
		SET_CHAR_PROOFS woundedgrove1_f1 TRUE TRUE TRUE TRUE TRUE
		SET_CHAR_NEVER_TARGETTED woundedgrove1_f1 TRUE
		SET_CHAR_DECISION_MAKER woundedgrove1_f1 motel_DM
		SET_CHAR_BLEEDING woundedgrove1_f1 TRUE
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER woundedgrove1_f1 TRUE

								
		CREATE_CHAR PEDTYPE_MISSION1 FAM2 2234.92 -1149.63 1029.0 grove2_f1 //explode this guys head as soon as created
		SET_CHAR_DECISION_MAKER grove2_f1 motel_DM
		SET_CHAR_HAS_USED_ENTRY_EXIT grove2_f1 2232.41 -1160.04 20.0

		CREATE_CHAR PEDTYPE_MISSION2 SWAT 2239.309 -1151.724 1028.779 swat2_f1 //swat that is peeking around first corner and shooting
		SET_CHAR_HEADING swat2_f1 177.9528
		GIVE_WEAPON_TO_CHAR swat2_f1 WEAPONTYPE_MP5 9999
		SET_CHAR_DECISION_MAKER swat2_f1 motel_DM
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat2_f1 TRUE
		SET_CHAR_HEALTH swat2_f1 100
		SET_CHAR_HAS_USED_ENTRY_EXIT swat2_f1 2232.41 -1160.04 20.0

		CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 2234.99 -1159.55 1029.84 hoochie2_f1 //hoochie running through doors
		SET_CHAR_HEADING hoochie2_f1 267.0618
		SET_CHAR_NEVER_TARGETTED hoochie2_f1 TRUE
		SET_CHAR_DECISION_MAKER hoochie2_f1 motel_DM
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER hoochie2_f1 TRUE
		SET_CHAR_HAS_USED_ENTRY_EXIT hoochie2_f1 2232.41 -1160.04 20.0
		SET_CHAR_PROOFS hoochie2_f1 FALSE TRUE FALSE FALSE FALSE

		//set piece for swat breaching the door
		//swat3 swat4 swat5
		CREATE_CHAR PEDTYPE_MISSION2 SWAT 2238.817 -1170.598 1028.8125 swat3_f1
		SET_CHAR_HEADING swat3_f1 272.0738 
		GIVE_WEAPON_TO_CHAR swat3_f1 WEAPONTYPE_MP5 9999
		SET_CHAR_DECISION_MAKER swat3_f1 motel_DM
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat3_f1 TRUE
		SET_CHAR_HEALTH swat3_f1 150
		SET_CHAR_SHOOT_RATE swat3_f1 80
		SET_CHAR_HAS_USED_ENTRY_EXIT swat3_f1 2232.41 -1160.04 20.0


		//grove controlled corner
		CREATE_CHAR PEDTYPE_MISSION3 FAM2 2244.5 -1189.627 1028.8 grove3_f1 //grove behind table
		SET_CHAR_HEADING grove3_f1 88.196
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER grove3_f1 TRUE
		GIVE_WEAPON_TO_CHAR grove3_f1 WEAPONTYPE_PISTOL 9999
		SET_CHAR_NEVER_TARGETTED grove3_f1 TRUE
		SET_CHAR_ACCURACY grove3_f1 10
		SET_CHAR_RELATIONSHIP grove3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
		SET_CHAR_DECISION_MAKER grove3_f1 motel_DM
		SET_CHAR_HAS_USED_ENTRY_EXIT grove3_f1 2232.41 -1160.04 20.0

		CREATE_CHAR PEDTYPE_MISSION3 FAM3 2241.0503 -1192.3729 1028.7981 grove4_f1 //grove behind sofa facing first corridor
		SET_CHAR_HEADING grove4_f1 358.2577
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER grove4_f1 TRUE
		GIVE_WEAPON_TO_CHAR grove4_f1 WEAPONTYPE_PISTOL 9999
		SET_CHAR_ACCURACY grove4_f1 10
		SET_CHAR_NEVER_TARGETTED grove4_f1 TRUE
		SET_CHAR_DECISION_MAKER grove4_f1 motel_DM
		SET_CHAR_HAS_USED_ENTRY_EXIT grove4_f1 2232.41 -1160.04 20.0
		

		CREATE_CHAR PEDTYPE_MISSION1 FAM2 2240.156 -1186.704 1028.7981 grove5_f1 //grove peeking around wall shooting //dies when pops out
		SET_CHAR_HEADING grove5_f1 90.1792 
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER grove5_f1 TRUE
		GIVE_WEAPON_TO_CHAR grove5_f1 WEAPONTYPE_MP5 9999
		SET_CHAR_NEVER_TARGETTED grove5_f1 TRUE
		SET_CHAR_DECISION_MAKER grove5_f1 motel_DM
		SET_CHAR_HAS_USED_ENTRY_EXIT grove5_f1 2232.41 -1160.04 20.0
						
		CREATE_CHAR PEDTYPE_MISSION1 FAM3 2234.84 -1191.202 1029.845 woundedgrove2_f1 //grove against wall near other grove guys
		SET_CHAR_HEADING woundedgrove2_f1 272.171
		SET_CHAR_ONLY_DAMAGED_BY_PLAYER woundedgrove2_f1 TRUE
		SET_CHAR_NEVER_TARGETTED woundedgrove2_f1 TRUE
		TASK_PLAY_ANIM_NON_INTERRUPTABLE woundedgrove2_f1 gnstwall_injurd SWAT 8.0 TRUE FALSE FALSE FALSE -1
		SET_CHAR_BLEEDING woundedgrove2_f1 TRUE
		SET_CHAR_DECISION_MAKER woundedgrove2_f1 motel_DM
		SET_CHAR_HAS_USED_ENTRY_EXIT woundedgrove2_f1 2232.41 -1160.04 20.0
		
		//explosion_door
		CREATE_FX_SYSTEM explosion_door 2239.51 -1170.77 1029.84 TRUE breachfx_f1

		SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2232.41 -1160.04 20.0
		SET_CHAR_COORDINATES scplayer 2216.34 -1150.509 1024.85
		SET_PLAYER_CONTROL PLAYER1 ON		 
		SET_CAMERA_BEHIND_PLAYER 
		SWITCH_ENTRY_EXIT motel1 FALSE


		PRINT_HELP LAF1_7 //You can purchase drinks at ~h~vending machines ~w~this will replenish your health.
		PRINT_NOW LAF1_6 5000 1 //~s~Make your way through the motel and find ~b~Sweet~s~.

		progressaudio_f1flag = 0
		handlingaudio_f1flag = 0

		moteldeal_f1flag = 25
		insidemotel_f1flag = 1

	ENDIF	
	
ENDIF

IF roofmotel_f1flag = 0
	IF insidemotel_f1flag = 1

		///Motel entrance
		///////////////////////////////////////////////////////////////////
		IF motelentrance_f1flag = 0
			CLEAR_PRINTS
			//create trolleys
			CREATE_OBJECT_NO_OFFSET KMB_TROLLEY 2242.433 -1166.712 1029.304 trolley1_f1
			SET_OBJECT_HEALTH trolley1_f1 150
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER trolley1_f1 TRUE

			CREATE_OBJECT_NO_OFFSET KMB_TROLLEY 2240.11 -1174.919 1029.304 trolley2_f1
			SET_OBJECT_HEALTH trolley2_f1 250

			CREATE_OBJECT_NO_OFFSET KMB_TROLLEY 2229.87 -1189.845 1029.304 trolley3_f1
			SET_OBJECT_HEADING trolley3_f1 90.0
			SET_OBJECT_HEALTH trolley3_f1 150
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER trolley3_f1 TRUE

			CREATE_OBJECT_NO_OFFSET KMB_TROLLEY 2217.569  -1187.528 1029.304 trolley4_f1
			SET_OBJECT_HEADING trolley4_f1 90.0
			SET_OBJECT_HEALTH trolley4_f1 250
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER trolley4_f1 TRUE

			CREATE_OBJECT_NO_OFFSET KMB_TROLLEY 2204.649 -1187.544 1029.304 trolley5_f1
			SET_OBJECT_HEADING trolley5_f1 90.0
			SET_OBJECT_HEALTH trolley5_f1 150
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER trolley5_f1 TRUE

			CREATE_OBJECT_NO_OFFSET KMB_TROLLEY 2194.345 -1171.269 1029.304 trolley6_f1
			SET_OBJECT_HEALTH trolley6_f1 250

			CREATE_OBJECT_NO_OFFSET KMB_TROLLEY 2192.079 -1166.364 1029.304 trolley7_f1
			SET_OBJECT_HEALTH trolley7_f1 150
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER trolley7_f1 TRUE

			//hoochie running
			IF NOT IS_CHAR_DEAD hoochie1_f1 
				SET_CHAR_HEALTH hoochie1_f1 1
				OPEN_SEQUENCE_TASK hoochieflee_f1seq
				FLUSH_ROUTE
				EXTEND_ROUTE 2227.25 -1139.12 1029.84
				EXTEND_ROUTE 2221.17 -1139.09 1027.84
				EXTEND_ROUTE 2221.64 -1145.11 1025.84
				EXTEND_ROUTE 2224.556 -1145.901 1025.85
				EXTEND_ROUTE 2224.465 -1142.276 1025.85
				TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_SPRINT FOLLOW_ROUTE_ONCE
				TASK_PLAY_ANIM -1 COWER PED 8.0 TRUE FALSE FALSE FALSE -1
				TASK_SET_CHAR_DECISION_MAKER -1 coward_DM
				CLOSE_SEQUENCE_TASK hoochieflee_f1seq
				PERFORM_SEQUENCE_TASK hoochie1_f1 hoochieflee_f1seq
				CLEAR_SEQUENCE_TASK hoochieflee_f1seq
			ENDIF

			//guy falling off railings
			IF NOT IS_CHAR_DEAD grove1_f1
				//TASK_PLAY_ANIM_NON_INTERRUPTABLE grove1_f1 Rail_fall SWAT 8.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_WITH_FLAGS grove1_f1 Rail_fall SWAT 8.0 FALSE FALSE FALSE TRUE -1 FALSE TRUE
			ENDIF
			//peeking is shooting at him
			IF NOT IS_CHAR_DEAD grove1_f1
				IF NOT IS_CHAR_DEAD swat2_f1
					enemy_f1 = swat2_f1
					enemytarget_f1 = grove1_f1
					enemytarget2_f1 = scplayer
					GOSUB stay2shoot_f1label
				ENDIF
			ENDIF

			motelentrance_f1flag = 1
		ENDIF
		
		IF motelentrance_f1flag = 1
			IF NOT IS_CHAR_DEAD	grove1_f1
				IF IS_CHAR_PLAYING_ANIM grove1_f1 Rail_fall
					GET_CHAR_ANIM_CURRENT_TIME grove1_f1 Rail_fall playeranim_f1
						IF playeranim_f1 = 1.0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE grove1_f1 Rail_fall_crawl SWAT 1000.0 FALSE FALSE FALSE TRUE -1
							//TASK_PLAY_ANIM_WITH_FLAGS grove1_f1 Rail_fall_crawl SWAT 8.0 FALSE FALSE FALSE TRUE -1 FALSE TRUE
							motelentrance_f1flag = 2
						ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		//wounded guy chatting to the player
		IF woundedgrove1_f1flag = 0
			IF NOT IS_CHAR_DEAD woundedgrove1_f1
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D woundedgrove1_f1 scplayer 4.5 4.5 3.5 FALSE

					CREATE_CHAR PEDTYPE_MISSION2 SWAT 2229.462 -1150.524 1028.845 swat1_f1 //swat that rolls out
					SET_CHAR_HAS_USED_ENTRY_EXIT swat1_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat1_f1 358.795
					GIVE_WEAPON_TO_CHAR swat1_f1 WEAPONTYPE_MP5 9999
					SET_CHAR_DECISION_MAKER swat1_f1 motel_DM
					SET_CHAR_ACCURACY swat1_f1 50
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat1_f1 TRUE
					SET_CHAR_HEALTH swat1_f1 150
					SET_CHAR_MAX_HEALTH swat1_f1 150

					DELETE_CHAR swat2_f1
					CREATE_CHAR PEDTYPE_MISSION2 SWAT 2239.309 -1151.724 1029.279 swat2_f1 //swat that is peeking around first corner and shooting
					SET_CHAR_HAS_USED_ENTRY_EXIT swat2_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat2_f1 177.9528
					GIVE_WEAPON_TO_CHAR swat2_f1 WEAPONTYPE_MP5 9999
					SET_CHAR_DECISION_MAKER swat2_f1 motel_DM
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat2_f1 TRUE
					SET_CHAR_ACCURACY swat2_f1 80

					//peeking around corner swat
					IF swat2_f1flag = 0
						IF NOT IS_CHAR_DEAD swat2_f1
							enemy_f1 = swat2_f1
							IF NOT IS_CHAR_DEAD grove5_f1
								enemytarget_f1 = grove5_f1
								coordshoot_f1flag = 1
								enemyx_f1 = 2241.78
								enemyy_f1 = -1194.52
								enemyz_f1 = 1031.38
								GOSUB peekright_f1label
								coordshoot_f1flag = 0
								swat2_f1flag = 1
							ENDIF
						ENDIF
					ENDIF

					woundedgrove1_f1flag = 1
				ENDIF
			ENDIF
		ENDIF


		///First corridor
		///////////////////////////////////////////////////////////////////
		IF firstcorridor_f1flag = 0

			//dead body
			IF grove2_f1flag = 0
				IF NOT IS_CHAR_DEAD grove2_f1
					EXPLODE_CHAR_HEAD grove2_f1
					grove2_f1flag = 1
				ENDIF
			ENDIF

			//rollout swat
			IF swat1_f1flag = 0
				IF NOT IS_CHAR_DEAD	swat1_f1
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2229.5913 -1150.6475 1028.7981 7.2 7.2 1.5 FALSE
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR swat1_f1 scplayer
						enemy_f1 = swat1_f1
						GOSUB rolloutl_f1label
						swat1_f1flag = 1
					ENDIF
				ENDIF
			ENDIF
		

			//if player gets close shoot him instead		
			IF swat2_f1flag = 1
				IF NOT IS_CHAR_DEAD swat2_f1
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR swat2_f1 scplayer
					OR LOCATE_CHAR_ANY_MEANS_CHAR_3D swat2_f1 scplayer 4.0 4.0 3.0 FALSE
						SET_CHAR_ACCURACY swat2_f1 50
						TASK_KILL_CHAR_ON_FOOT swat2_f1 scplayer
						swat2_f1flag = 2
					ENDIF
				ENDIF
			ENDIF
		
			
			//hoochie running and breach set piece
			IF breach_f1flag = 0
				IF IS_CHAR_IN_AREA_3D scplayer 2243.8 -1147.93 1025.16 2238.74 -1162.62 1033.79 FALSE

					//grove corner starts shooting
					grovecorner_f1flag = 1

					//hoochie running from one side to the other	
					IF hoochie2_f1flag = 0
						IF NOT IS_CHAR_DEAD hoochie2_f1
							SET_CHAR_HEALTH hoochie2_f1 150
							OPEN_SEQUENCE_TASK hoochie2_f1seq
							TASK_GO_STRAIGHT_TO_COORD -1 2249.355 -1159.683 1029.797 PEDMOVE_SPRINT 10000
							TASK_ACHIEVE_HEADING -1 87.888
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							CLOSE_SEQUENCE_TASK hoochie2_f1seq
							PERFORM_SEQUENCE_TASK hoochie2_f1 hoochie2_f1seq
							CLEAR_SEQUENCE_TASK hoochie2_f1seq
							hoochie2_f1flag = 1
						ENDIF
					ENDIF

					//2nd corridor
					CREATE_CHAR PEDTYPE_MISSION1 SWAT 2228.6072 -1189.7207 1028.7981 swat6_f1 //first trolley
					SET_CHAR_HAS_USED_ENTRY_EXIT swat6_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat6_f1 266.5963
					GIVE_WEAPON_TO_CHAR swat6_f1 WEAPONTYPE_MP5 9999
					SET_CHAR_DECISION_MAKER swat6_f1 motel_DM
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat6_f1 TRUE
					SET_CHAR_HEALTH swat6_f1 150
					SET_CHAR_MAX_HEALTH swat6_f1 150
					SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY swat6_f1 TRUE
							
					CREATE_CHAR PEDTYPE_MISSION1 SWAT 2225.796 -1186.783 1028.7981 swat7_f1 //right door peek and shoot
					SET_CHAR_HAS_USED_ENTRY_EXIT swat7_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat7_f1 273.6395
					GIVE_WEAPON_TO_CHAR swat7_f1 WEAPONTYPE_MP5 9999
					SET_CHAR_DECISION_MAKER swat7_f1 motel_DM
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat7_f1 TRUE
					SET_CHAR_ACCURACY swat7_f1 80
					SET_CHAR_HEALTH swat7_f1 150
					SET_CHAR_MAX_HEALTH swat7_f1 150

					TIMERB = 0
					breach_f1flag = 1
				ENDIF
			ENDIF

			IF breach_f1flag = 1
				IF IS_CHAR_IN_AREA_3D scplayer 2243.0 -1154.3 1025.16 2238.74 -1170.62 1033.79 FALSE  //2243.0 -1155.5 1025.16 2238.74 -1170.62 1033.79
					breach_f1flag = 2
				ENDIF
			ENDIF

			//breach set piece
			IF breach_f1flag = 2
//				IF TIMERB > 100 //500					//DELAY FROM WHEN PLAYER ENTERS LOCATE TO WHEN THEY COME OUT

					//swat controlled corner		
					CREATE_CHAR PEDTYPE_MISSION1 SWAT 2187.48 -1186.9 1033.3 swat8_f1	//up on stairs shoot stay in same place
					SET_CHAR_HAS_USED_ENTRY_EXIT swat8_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat8_f1 274.247			  
					GIVE_WEAPON_TO_CHAR swat8_f1 WEAPONTYPE_MP5 99999
					SET_CHAR_DECISION_MAKER swat8_f1 motel_DM
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat8_f1 TRUE
					SET_CHAR_ACCURACY swat8_f1 80
					SET_CHAR_HEALTH swat8_f1 150
					SET_CHAR_MAX_HEALTH swat8_f1 150

					CREATE_CHAR PEDTYPE_MISSION1 SWAT 2186.611 -1183.962 1033.837 swat9_f1	//runs down stairs
					SET_CHAR_HAS_USED_ENTRY_EXIT swat9_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat9_f1 286.2857
					GIVE_WEAPON_TO_CHAR swat9_f1 WEAPONTYPE_MP5 99999
					SET_CHAR_DECISION_MAKER swat9_f1 motel_DM
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat9_f1 TRUE
					SET_CHAR_HEALTH swat9_f1 150
					SET_CHAR_MAX_HEALTH swat9_f1 150
					SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY swat9_f1 TRUE
									
					CREATE_CHAR PEDTYPE_MISSION1 SWAT 2190.157 -1182.068 1033.829 swat10_f1	//up on stairs shoot stay in same place
					SET_CHAR_HAS_USED_ENTRY_EXIT swat10_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat10_f1 183.88771
					GIVE_WEAPON_TO_CHAR swat10_f1 WEAPONTYPE_MP5 99999
					SET_CHAR_DECISION_MAKER swat10_f1 motel_DM
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat10_f1 TRUE
					SET_CHAR_HEALTH swat10_f1 150
					SET_CHAR_MAX_HEALTH swat10_f1 150

					CREATE_CHAR PEDTYPE_MISSION1 SWAT 2188.5791 -1184.7800 1028.7981 swat11_f1	//duck and shoot behind the sofa				
					SET_CHAR_HAS_USED_ENTRY_EXIT swat11_f1 2232.41 -1160.04 20.0
					SET_CHAR_HEADING swat11_f1 246.3679 
					GIVE_WEAPON_TO_CHAR swat11_f1 WEAPONTYPE_MP5 99999
					SET_CHAR_DECISION_MAKER swat11_f1 motel_DM	
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat11_f1 TRUE
					SET_CHAR_HEALTH swat11_f1 150
					SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY swat11_f1 TRUE

					breach_f1flag = 3
//				ENDIF
			ENDIF

			IF breach_f1flag = 3
				IF swat3_f1flag = 0
					IF NOT IS_CHAR_DEAD swat3_f1

						IF DOES_OBJECT_EXIST breachdoor_f1
							SET_OBJECT_COLLISION breachdoor_f1 FALSE
							BREAK_OBJECT breachdoor_f1 TRUE
						ENDIF
						IF missionaudio2_f1flag = 0
							PLAY_MISSION_AUDIO 2
							missionaudio2_f1flag = 1
						ENDIF

						PLAY_AND_KILL_FX_SYSTEM breachfx_f1
						TASK_PLAY_ANIM swat3_f1 SWT_BREACH_01 SWAT 1000.0 FALSE TRUE TRUE FALSE -1
						swat3_f1flag = 1
						TIMERB = 0
						breach_f1flag = 4
					ENDIF
				ENDIF
			ENDIF
		
			IF breach_f1flag = 4
				IF swat4_f1flag = 0
					IF TIMERB > 667

							CREATE_CHAR PEDTYPE_MISSION2 SWAT 2238.317 -1170.598 1028.8125 swat4_f1 
							SET_CHAR_HEADING swat4_f1 272.0738 
							GIVE_WEAPON_TO_CHAR swat4_f1 WEAPONTYPE_MP5 9999
							SET_CHAR_DECISION_MAKER swat4_f1 motel_DM
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat4_f1 TRUE
							SET_CHAR_HEALTH swat4_f1 150
							SET_CHAR_SHOOT_RATE swat4_f1 80
							SET_CHAR_HAS_USED_ENTRY_EXIT swat4_f1 2232.41 -1160.04 20.0

							TASK_PLAY_ANIM swat4_f1 SWT_BREACH_02 SWAT 1000.0 FALSE TRUE TRUE FALSE -1
							TIMERB = 0
							swat4_f1flag = 1
							breach_f1flag = 5

					ENDIF
				ENDIF   
			ENDIF

			IF breach_f1flag = 5
				IF swat5_f1flag = 0
					IF TIMERB > 333

							CREATE_CHAR PEDTYPE_MISSION2 SWAT 2237.818 -1170.598 1028.8125 swat5_f1 
							SET_CHAR_HEADING swat5_f1 272.0738 
							GIVE_WEAPON_TO_CHAR swat5_f1 WEAPONTYPE_MP5 9999
							SET_CHAR_DECISION_MAKER swat5_f1 motel_DM
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat5_f1 TRUE
							SET_CHAR_HEALTH swat5_f1 150
							SET_CHAR_SHOOT_RATE swat5_f1 80
							SET_CHAR_HAS_USED_ENTRY_EXIT swat5_f1 2232.41 -1160.04 20.0

							TASK_PLAY_ANIM swat5_f1 SWT_BREACH_03 SWAT 1000.0 FALSE TRUE TRUE FALSE -1
							swat5_f1flag = 1
							breach_f1flag = 6
					ENDIF
				ENDIF
			ENDIF

			IF swat3_f1flag = 1
				IF NOT IS_CHAR_DEAD swat3_f1
					GET_SCRIPT_TASK_STATUS swat3_f1 TASK_PLAY_ANIM swtbreach01_f1
						IF swtbreach01_f1 = FINISHED_TASK
						OR LOCATE_CHAR_ANY_MEANS_CHAR_2D swat3_f1 scplayer 6.0 6.0 FALSE
							SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY swat3_f1 TRUE
							enemy_f1 = swat3_f1
							enemytarget_f1 = scplayer
							GOSUB stayshoot_f1label
							swat3_f1flag = 2
						ENDIF
				ENDIF
			ENDIF

			IF swat4_f1flag = 1
				IF NOT IS_CHAR_DEAD swat4_f1
					GET_SCRIPT_TASK_STATUS swat4_f1 TASK_PLAY_ANIM swtbreach02_f1
						IF swtbreach02_f1 = FINISHED_TASK
						OR LOCATE_CHAR_ANY_MEANS_CHAR_2D swat4_f1 scplayer 4.5 4.5 FALSE
							IF NOT IS_CHAR_DEAD grove4_f1
								enemy_f1 = swat4_f1
								enemytarget_f1 = scplayer
								GOSUB stayshoot_f1label
							ENDIF
							swat4_f1flag = 2
						ENDIF
				ENDIF
			ENDIF

			IF swat5_f1flag = 1
				IF NOT IS_CHAR_DEAD swat5_f1
					GET_SCRIPT_TASK_STATUS swat5_f1 TASK_PLAY_ANIM swtbreach03_f1
						IF swtbreach03_f1 = FINISHED_TASK
						OR LOCATE_CHAR_ANY_MEANS_CHAR_2D swat5_f1 scplayer 3.5 3.5 FALSE
							enemy_f1 = swat5_f1
							enemytarget_f1 = scplayer
							GOSUB stayshoot_f1label
							swat5_f1flag = 2
						ENDIF
				ENDIF
			ENDIF

			IF hoochie2_f1flag = 1
				IF NOT IS_CHAR_DEAD hoochie2_f1
					IF LOCATE_STOPPED_CHAR_ON_FOOT_3D hoochie2_f1 2249.35 -1159.683 1028.79 3.0 3.0 3.0 FALSE
						IF NOT HAS_CHAR_BEEN_DAMAGED_BY_CHAR hoochie2_f1 scplayer
							IF LOCATE_CHAR_ON_FOOT_3D scplayer 2248.35 -1159.683 1028.79 3.0 3.0 3.0 FALSE
								CLEAR_AREA 2248.35 -1159.683 20.0 20.0 FALSE
								SET_PLAYER_CONTROL PLAYER1 OFF
								SET_EVERYONE_IGNORE_PLAYER PLAYER1 FALSE
								SWITCH_WIDESCREEN ON

								SET_FIXED_CAMERA_POSITION 2247.0474 -1157.2809 1030.5811 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 2247.6182 -1158.0294 1030.2440 JUMP_CUT
								CLEAR_CHAR_TASKS_IMMEDIATELY hoochie2_f1
								SET_CHAR_COORDINATES hoochie2_f1 2249.35 -1159.683 1028.79
								SET_CHAR_HEADING hoochie2_f1 87.888

								SET_CHAR_COORDINATES scplayer 2248.35 -1159.683 1028.79
								SET_CHAR_HEADING scplayer 267.888

								CLEAR_MISSION_AUDIO 1
								IF NOT handlingaudio_f1flag = 0
									handlingaudio_f1flag = 0
									progressaudio_f1flag++
								ENDIF
								LOAD_MISSION_AUDIO 1 SOUND_FIN1_DC	//Oh baby, you're hurt! <kiss>
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE
								PLAY_MISSION_AUDIO 1
								PRINT_NOW FIN1_DC 3000 1 //Oh baby, you're hurt! <kiss>
																
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PLAYA_KISS_03 KISSING 4.0 FALSE FALSE FALSE FALSE -1
								IF NOT IS_CHAR_DEAD hoochie2_f1
									TASK_PLAY_ANIM_NON_INTERRUPTABLE hoochie2_f1 GRLFRD_KISS_03 KISSING 4.0 FALSE FALSE FALSE FALSE -1
								ENDIF

								SET_CHAR_HEALTH scplayer 100

								hoochie2_f1flag = 2
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF hoochie2_f1flag = 2
				IF NOT IS_CHAR_DEAD hoochie2_f1
					IF IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_03
						IF IS_CHAR_PLAYING_ANIM hoochie2_f1 GRLFRD_KISS_03
							hoochie2_f1flag = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF hoochie2_f1flag = 3
				IF NOT IS_CHAR_DEAD hoochie2_f1
					IF NOT IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_03
						IF NOT IS_CHAR_PLAYING_ANIM hoochie2_f1 GRLFRD_KISS_03
							SET_CHAR_HEALTH hoochie2_f1 5
							SET_PLAYER_CONTROL PLAYER1 ON
							SWITCH_WIDESCREEN OFF
							RESTORE_CAMERA_JUMPCUT
							SET_CAMERA_BEHIND_PLAYER
							CLEAR_PRINTS
							hoochie2_f1flag = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF



		ENDIF


		/// Grove controlled corner 
		///////////////////////////////////////////////////////////////////
		IF grovecorner_f1flag = 1

			//grove behind table
			IF NOT IS_CHAR_DEAD grove3_f1
				enemy_f1 = grove3_f1
				enemyx_f1 = 2232.41
				enemyy_f1 = -1188.66
				enemyz_f1 = 1030.26
				GOSUB shootatcoords_f1label
			ENDIF

			//grove behind sofa facing first corridor
			IF NOT IS_CHAR_DEAD grove4_f1
				enemy_f1 = grove4_f1
				enemyx_f1 = 2233.35
				enemyy_f1 = -1187.99
				enemyz_f1 = 1030.4
				GOSUB shootatcoords_f1label
			ENDIF
			
			//grove peeking around wall shooting //dies when pops out
			IF NOT IS_CHAR_DEAD grove5_f1
				enemy_f1 = grove5_f1
				IF NOT IS_CHAR_DEAD swat6_f1
					enemytarget_f1 = swat6_f1
					enemyx_f1 = 2186.49
					enemyy_f1 = -1189.18
					enemyz_f1 = 1030.97
					coordshoot_f1flag = 1 
					GOSUB peekright_f1label
					coordshoot_f1flag = 0
				ENDIF
			ENDIF

			//first trolley swat
			IF NOT IS_CHAR_DEAD swat6_f1
				enemy_f1 = swat6_f1
				enemytarget_f1 = scplayer
				GOSUB stayshoot_f1label
			ENDIF

			grovecorner_f1flag = 2
		ENDIF

		//once player is in grove corner get the swat corner reacting
		IF grovecorner_f1flag = 2
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2235.48 -1188.77 10.0 10.0 FALSE 

				//peeking around door
				IF NOT IS_CHAR_DEAD swat7_f1
					enemy_f1 = swat7_f1
					GOSUB peekleft_f1label
				ENDIF
				MARK_CHAR_AS_NO_LONGER_NEEDED woundedgrove1_f1
				//REMOVE_CHAR_ELEGANTLY woundedgrove1_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED hoochie1_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED grove1_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED swat1_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED grove2_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED swat2_f1
				REMOVE_CHAR_ELEGANTLY hoochie2_f1

				IF IS_CHAR_DEAD swat3_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat3_f1 
				ENDIF

				IF IS_CHAR_DEAD swat4_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat4_f1
				ENDIF

				IF IS_CHAR_DEAD swat5_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat5_f1
				ENDIF

				//create 1st vent setpiece guys
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2216.605 -1188.611 1032.27 swat12_f1
				SET_CHAR_HAS_USED_ENTRY_EXIT swat12_f1 2232.41 -1160.04 20.0
				SET_CHAR_HEADING swat12_f1 264.4329 
				GIVE_WEAPON_TO_CHAR swat12_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_DECISION_MAKER swat12_f1 motel_DM	
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat12_f1 TRUE
				SET_CHAR_HEALTH swat12_f1 150
				SET_CHAR_ACCURACY swat12_f1 80
//				SET_CHAR_COLLISION swat12_f1 FALSE
//				SET_CHAR_NEVER_TARGETTED swat12_f1 TRUE 

				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2216.131 -1188.611 1032.27 swat13_f1
				SET_CHAR_HAS_USED_ENTRY_EXIT swat13_f1 2232.41 -1160.04 20.0
				SET_CHAR_HEADING swat13_f1 264.4329
				GIVE_WEAPON_TO_CHAR swat13_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_DECISION_MAKER swat13_f1 motel_DM	
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat13_f1 TRUE
				SET_CHAR_HEALTH swat13_f1 150
				SET_CHAR_ACCURACY swat13_f1 80
//				SET_CHAR_COLLISION swat13_f1 FALSE
//				SET_CHAR_NEVER_TARGETTED swat13_f1 TRUE 

				//vent guy upside down shooting
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2193.129 -1164.661 1032.269 swat14_f1
				SET_CHAR_HAS_USED_ENTRY_EXIT swat14_f1 2232.41 -1160.04 20.0
				SET_CHAR_HEADING swat14_f1 4.98
				GIVE_WEAPON_TO_CHAR swat14_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_DECISION_MAKER swat14_f1 motel_DM	
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat14_f1 TRUE
				SET_CHAR_NEVER_TARGETTED swat14_f1 TRUE
				SET_CHAR_HEALTH swat14_f1 1000

				//right rollout guy in the final corridor
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2195.489 -1172.941 1029.859 swat15_f1
				SET_CHAR_HAS_USED_ENTRY_EXIT swat15_f1 2232.41 -1160.04 20.0
				SET_CHAR_HEADING swat15_f1 181.323
				GIVE_WEAPON_TO_CHAR swat15_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_DECISION_MAKER swat15_f1 motel_DM	
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat15_f1 TRUE
				SET_CHAR_HEALTH swat15_f1 120

				//crouch behind trolley
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2191.987 -1165.214 1029.852 swat16_f1
				SET_CHAR_HAS_USED_ENTRY_EXIT swat16_f1 2232.41 -1160.04 20.0
				SET_CHAR_HEADING swat16_f1 181.323
				GIVE_WEAPON_TO_CHAR swat16_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_DECISION_MAKER swat16_f1 motel_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat16_f1 TRUE
				SET_CHAR_HEALTH swat16_f1 150
				SET_CHAR_MAX_HEALTH swat16_f1 150

				//roll left 
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2190.6977 -1156.633 1029.859 swat17_f1
				SET_CHAR_HAS_USED_ENTRY_EXIT swat17_f1 2232.41 -1160.04 20.0
				SET_CHAR_HEADING swat17_f1 189.575
				GIVE_WEAPON_TO_CHAR swat17_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_DECISION_MAKER swat17_f1 motel_DM	
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat17_f1 TRUE
				SET_CHAR_ACCURACY swat17_f1 30

				//guy shooting sweet
				CREATE_CHAR PEDTYPE_MISSION1 SWAT 2194.593 -1156.995 1029.852 swat18_f1
				SET_CHAR_HAS_USED_ENTRY_EXIT swat18_f1 2232.41 -1160.04 20.0
				SET_CHAR_HEADING swat18_f1 275.754
				GIVE_WEAPON_TO_CHAR swat18_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_DECISION_MAKER swat18_f1 motel_DM	
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swat18_f1 TRUE

				CREATE_CHAR PEDTYPE_MISSION1 BFYPRO 2192.91 -1182.15 1029.35 hoochie1_f1	//hoochie running into player then getting shot
				SET_CHAR_HAS_USED_ENTRY_EXIT hoochie1_f1 2232.41 -1160.04 20.0
				SET_CHAR_NEVER_TARGETTED hoochie1_f1 TRUE
				SET_CHAR_DECISION_MAKER hoochie1_f1 motel_DM
				SET_CHAR_PROOFS hoochie1_f1 FALSE TRUE FALSE FALSE FALSE

				grovecorner_f1flag = 3
				swatcorner_f1flag = 1
			ENDIF
		ENDIF

		//interior abseil set piece

		IF swatwindosmash_f1flag = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 2241.34 -1186.11 1029.76 6.6 6.6 3.0 FALSE
				IF DOES_OBJECT_EXIST skylite_f1
					BREAK_OBJECT skylite_f1 TRUE
				ENDIF
				SHUT_CHAR_UP scplayer TRUE
				swatwindosmash_f1flag = 1
			ENDIF
		ENDIF

		IF swatwindosmash_f1flag = 1
			CREATE_SWAT_ROPE PEDTYPE_MISSION4 SWAT 2246.42 -1192.88 1039.5 swatrope1_f1
			GIVE_WEAPON_TO_CHAR swatrope1_f1 WEAPONTYPE_MP5 9999
			SET_CHAR_RELATIONSHIP swatrope1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			SET_CHAR_RELATIONSHIP swatrope1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
			SET_CHAR_DECISION_MAKER swatrope1_f1 extmotel_DM
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER swatrope1_f1 TRUE
			SET_CHAR_HEALTH swatrope1_f1 150
			SET_CHAR_MAX_HEALTH swatrope1_f1 150
			SET_CHAR_ACCURACY swatrope1_f1 80
			IF NOT IS_CHAR_DEAD grove4_f1
				IF NOT IS_CHAR_DEAD swatrope1_f1
					TASK_KILL_CHAR_ON_FOOT grove4_f1 swatrope1_f1
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD grove3_f1
				IF NOT IS_CHAR_DEAD swatrope1_f1
					enemy_f1 = grove3_f1
					enemytarget_f1 = swatrope1_f1
					GOSUB stayshoot_f1label
				ENDIF
			ENDIF

			TIMERA = 0
			swatwindosmash_f1flag = 2
		ENDIF

		IF swatwindosmash_f1flag = 2
			IF TIMERA > 1000

				CREATE_SWAT_ROPE PEDTYPE_MISSION4 SWAT 2246.11 -1189.55 1038.09 swatrope2_f1
				GIVE_WEAPON_TO_CHAR swatrope2_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_RELATIONSHIP swatrope2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				SET_CHAR_RELATIONSHIP swatrope2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION3
				SET_CHAR_DECISION_MAKER swatrope2_f1 extmotel_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER swatrope2_f1 TRUE
				SET_CHAR_HEALTH swatrope2_f1 125
				SET_CHAR_MAX_HEALTH swatrope2_f1 125
				SET_CHAR_ACCURACY swatrope2_f1 80

				IF NOT IS_CHAR_DEAD grove3_f1
					IF NOT IS_CHAR_DEAD swatrope1_f1
						IF NOT IS_CHAR_DEAD swatrope2_f1
							enemy_f1 = grove3_f1
							enemytarget_f1 = swatrope1_f1
							enemytarget2_f1 = swatrope2_f1
							GOSUB stay2shoot_f1label
						ENDIF
					ELSE
						IF NOT IS_CHAR_DEAD swatrope2_f1
							enemy_f1 = grove3_f1
							enemytarget_f1 = swatrope2_f1
							GOSUB stayshoot_f1label
						ENDIF
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD grove4_f1
					TASK_DIE grove4_f1
				ENDIF
				MARK_MODEL_AS_NO_LONGER_NEEDED imy_skylight
				swatwindosmash_f1flag = 3
			ENDIF
		ENDIF

		IF swatwindosmash_f1flag > 2

			IF swatrope1_f1flag = 0
				IF NOT IS_CHAR_DEAD swatrope1_f1
					GET_SCRIPT_TASK_STATUS swatrope1_f1 CREATE_SWAT_ROPE swatrope1_f1status
						IF swatrope1_f1status = FINISHED_TASK
						OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR swatrope1_f1 scplayer
							TASK_KILL_CHAR_ON_FOOT swatrope1_f1 scplayer
							IF NOT IS_CHAR_DEAD grove4_f1
								EXPLODE_CHAR_HEAD grove4_f1
							ENDIF
							swatrope1_f1flag = 1
						ENDIF
				ENDIF
			ENDIF

			IF swatrope2_f1flag = 0
				IF NOT IS_CHAR_DEAD swatrope2_f1
					GET_SCRIPT_TASK_STATUS swatrope2_f1 CREATE_SWAT_ROPE swatrope2_f1status
						IF swatrope2_f1status = FINISHED_TASK
						OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR swatrope2_f1 scplayer
							TASK_KILL_CHAR_ON_FOOT swatrope2_f1 scplayer
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER swatrope2_f1 FALSE
							swatrope2_f1flag = 1
						ENDIF
				ENDIF
			ENDIF

		ENDIF
		//
			
		/// Swat controlled corner 
		///////////////////////////////////////////////////////////////////
		IF swatcorner_f1flag = 1
			
			//1st vent set piece
			IF vent1a_f1flag = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2232.7 -1188.84 1029.4 5.0 5.0 3.5 FALSE
				OR IS_CHAR_DEAD	swat6_f1
				OR IS_CHAR_DEAD swat7_f1
					SHUT_CHAR_UP scplayer FALSE
					IF DOES_OBJECT_EXIST vent1_f1
						FREEZE_OBJECT_POSITION vent1_f1 FALSE
						SET_OBJECT_ROTATION_VELOCITY vent1_f1 0.0 0.6 0.0
						ATTACH_MISSION_AUDIO_TO_OBJECT 3 vent1_f1
						PLAY_MISSION_AUDIO 3
					ENDIF
					IF NOT IS_CHAR_DEAD grove3_f1
						CLEAR_CHAR_TASKS grove3_f1
					ENDIF

					IF missionaudio2_f1flag = 3
						PLAY_MISSION_AUDIO 2 //get into positions
					ENDIF

					vent1a_f1flag = 1
				ENDIF
			ENDIF

			IF vent1a_f1flag = 1
				IF NOT IS_CHAR_DEAD swat12_f1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE swat12_f1 swt_vent_01 SWAT 1000.0 FALSE FALSE FALSE FALSE -1
//					SET_CHAR_NEVER_TARGETTED swat12_f1 FALSE
//					SET_CHAR_HEALTH swat12_f1 20
					TIMERB = 0
					vent1a_f1flag = 2
					vent1b_f1flag = 1
				ENDIF 
			ENDIF

			IF vent1a_f1flag = 2
				IF NOT IS_CHAR_DEAD swat12_f1
					IF IS_CHAR_PLAYING_ANIM swat12_f1 swt_vent_01
						GET_CHAR_ANIM_CURRENT_TIME swat12_f1 swt_vent_01 swtvent01_f1
							IF swtvent01_f1 = 1.0
								//SET_CHAR_COLLISION swat12_f1 TRUE
								//SET_CHAR_NEVER_TARGETTED swat12_f1 FALSE
								SET_CHAR_COORDINATES swat12_f1 2217.85 -1188.86 1028.93 //1029.15
								enemy_f1 = swat12_f1
								enemyx_f1 = 2220.538
								enemyy_f1 = -1189.594
								enemyz_f1 = 1029.845
								enemytarget_f1 = scplayer
								GOSUB runstay_f1label
								vent1a_f1flag = 3
							ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF vent1b_f1flag = 1
				IF TIMERB > 3166
					IF NOT IS_CHAR_DEAD swat13_f1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE swat13_f1 swt_vent_02 SWAT 1000.0 FALSE FALSE FALSE FALSE -1
//						SET_CHAR_NEVER_TARGETTED swat13_f1 FALSE
						vent1b_f1flag = 2
					ENDIF
				ENDIF
			ENDIF


			IF vent1b_f1flag = 2
				IF NOT IS_CHAR_DEAD swat13_f1
					IF IS_CHAR_PLAYING_ANIM swat13_f1 swt_vent_02
						GET_CHAR_ANIM_CURRENT_TIME swat13_f1 swt_vent_02 swtvent02_f1
							IF swtvent02_f1 = 1.0
//								SET_CHAR_COLLISION swat13_f1 TRUE
//								SET_CHAR_NEVER_TARGETTED swat13_f1 FALSE
								SET_CHAR_COORDINATES swat13_f1 2217.85 -1188.86 1028.93 //1029.15
								TASK_KILL_CHAR_ON_FOOT swat13_f1 scplayer
								vent1b_f1flag = 3
							ENDIF
					ENDIF
				ENDIF
			ENDIF

			//hoochie running around
			IF hoochie1_f1flag = 1
				IF NOT IS_CHAR_DEAD hoochie1_f1
					SET_CHAR_HEALTH hoochie1_f1 150
					OPEN_SEQUENCE_TASK hoochieescape_f1seq
					TASK_GO_STRAIGHT_TO_COORD -1 2193.064 -1188.77 1029.845 PEDMOVE_SPRINT 10000
					TASK_GO_STRAIGHT_TO_COORD -1 2206.833 -1188.83 1029.845 PEDMOVE_SPRINT 10000
					TASK_GO_STRAIGHT_TO_COORD -1 2209.588 -1190.20 1029.845 PEDMOVE_SPRINT 10000 //()
					TASK_GO_STRAIGHT_TO_COORD -1 2209.7 -1197.323 1029.845 PEDMOVE_SPRINT 10000
					TASK_ACHIEVE_HEADING -1 358.428
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					CLOSE_SEQUENCE_TASK hoochieescape_f1seq
					PERFORM_SEQUENCE_TASK hoochie1_f1 hoochieescape_f1seq
					CLEAR_SEQUENCE_TASK hoochieescape_f1seq
					hoochie1_f1flag = 2
				ENDIF
			ENDIF

			//hoochie gives player kiss and health and armour for saving her
			IF hoochie1_f1flag = 2
				IF NOT IS_CHAR_DEAD hoochie1_f1
					IF LOCATE_STOPPED_CHAR_ON_FOOT_3D hoochie1_f1 2209.7 -1197.323 1029.845 3.0 3.0 3.0 FALSE
						IF NOT HAS_CHAR_BEEN_DAMAGED_BY_CHAR hoochie1_f1 scplayer
							IF LOCATE_CHAR_ON_FOOT_3D scplayer 2209.7 -1197.323 1029.845 3.0 3.0 3.0 FALSE
								CLEAR_AREA 2209.7 -1197.323 10.0 10.0 FALSE
								IF NOT IS_CHAR_DEAD grove3_f1
									REMOVE_CHAR_ELEGANTLY grove3_f1
								ELSE
									MARK_CHAR_AS_NO_LONGER_NEEDED grove3_f1
								ENDIF

								SET_PLAYER_CONTROL PLAYER1 OFF
								SET_EVERYONE_IGNORE_PLAYER PLAYER1 FALSE
								SWITCH_WIDESCREEN ON

								SET_FIXED_CAMERA_POSITION 2207.5286 -1195.7255 1031.2731 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 2208.2734 -1196.1141 1030.7311 JUMP_CUT
								CLEAR_CHAR_TASKS_IMMEDIATELY hoochie1_f1
								SET_CHAR_COORDINATES hoochie1_f1 2209.8 -1197.338 1028.845
								SET_CHAR_HEADING hoochie1_f1 359.354

								SET_CHAR_COORDINATES scplayer 2209.8 -1196.338 1028.845
								SET_CHAR_HEADING scplayer 178.62

								CLEAR_MISSION_AUDIO 1
								IF NOT handlingaudio_f1flag = 0
									handlingaudio_f1flag = 0
									progressaudio_f1flag++
								ENDIF
								LOAD_MISSION_AUDIO 1 SOUND_FIN1_DA	//You saved me,  have some sugar! <kiss>
								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE
								PLAY_MISSION_AUDIO 1
								PRINT_NOW FIN1_DA 3000 1 //You saved me,  have some sugar! <kiss>
																
								TASK_PLAY_ANIM scplayer PLAYA_KISS_03 KISSING 4.0 FALSE FALSE FALSE FALSE -1
								IF NOT IS_CHAR_DEAD hoochie1_f1
									TASK_PLAY_ANIM hoochie1_f1 GRLFRD_KISS_03 KISSING 4.0 FALSE FALSE FALSE FALSE -1
								ENDIF

								SET_CHAR_HEALTH scplayer 100

								hoochie1_f1flag = 3
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF hoochie1_f1flag = 3
				IF NOT IS_CHAR_DEAD hoochie1_f1
					IF IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_03
						IF IS_CHAR_PLAYING_ANIM hoochie1_f1 GRLFRD_KISS_03
							hoochie1_f1flag = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF hoochie1_f1flag = 4
				IF NOT IS_CHAR_DEAD hoochie1_f1
					IF NOT IS_CHAR_PLAYING_ANIM scplayer PLAYA_KISS_03
						IF NOT IS_CHAR_PLAYING_ANIM hoochie1_f1 GRLFRD_KISS_03
							SET_CHAR_HEALTH hoochie1_f1 5
							SET_PLAYER_CONTROL PLAYER1 ON
							SWITCH_WIDESCREEN OFF
							RESTORE_CAMERA_JUMPCUT
							SET_CAMERA_BEHIND_PLAYER
							CLEAR_PRINTS
							hoochie1_f1flag = 5
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			//kill char when player gets close
			IF grove5_f1flag = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2240.8 -1182.4 1029.41 4.0 4.0 2.5 FALSE
					IF NOT IS_CHAR_DEAD grove5_f1
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER grove5_f1 FALSE
						EXPLODE_CHAR_HEAD grove5_f1
					ENDIF
					grove5_f1flag = 1
				ENDIF
			ENDIF

			//if player gets close shoot him instead		
			IF swat7_f1flag = 0
				IF NOT IS_CHAR_DEAD swat7_f1
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR swat7_f1 scplayer
					OR LOCATE_CHAR_ANY_MEANS_CHAR_3D swat7_f1 scplayer 2.5 2.5 3.0 FALSE
						SET_CHAR_ACCURACY swat7_f1 30
						TASK_KILL_CHAR_ON_FOOT swat7_f1 scplayer
						swat7_f1flag = 1
					ENDIF
				ENDIF
			ENDIF

			//trigger stair runners
			IF swat8_f1flag = 0
				IF swat9_f1flag = 0
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2204.65 -1188.62 1029.44 5.0 5.0 3.5 FALSE
						swat8_f1flag = 1
						swat9_f1flag = 1
						hoochie1_f1flag = 1
					ENDIF
				ENDIF
			ENDIF

		 	//runs up stairs
		 	IF swat8_f1flag = 1
				IF NOT IS_CHAR_DEAD swat8_f1
					enemy_f1 = swat8_f1
					enemytarget_f1 = scplayer
					GOSUB stayshootnoduck_f1label
					swat8_f1flag = 2
				ENDIF
			ENDIF

			
		 	//run down to behind the table
		 	IF swat9_f1flag = 1
				IF NOT IS_CHAR_DEAD swat9_f1
					FLUSH_ROUTE
					EXTEND_ROUTE 2186.67 -1192.4 1031.867
					EXTEND_ROUTE 2188.44 -1193.95 1031.27
					EXTEND_ROUTE 2193.31 -1193.57 1030.34
					EXTEND_ROUTE 2196.239 -1191.459 1029.85
					TASK_FOLLOW_POINT_ROUTE swat9_f1 PEDMOVE_RUN FOLLOW_ROUTE_ONCE
					swat9_f1flag = 2
				ENDIF
			ENDIF
			IF swat9_f1flag = 2
				IF NOT IS_CHAR_DEAD swat9_f1
					IF LOCATE_STOPPED_CHAR_ANY_MEANS_2D swat9_f1 2196.239 -1191.459 2.0 2.0 FALSE
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR swat9_f1 scplayer
						enemy_f1 = swat9_f1
						enemytarget_f1 = scplayer
						GOSUB stayshootnoduck_f1label
						swat9_f1flag = 3
					ENDIF
				ENDIF
			ENDIF

		 	//duck and shoot behind the sofa
			IF swat10_f1flag = 0
				IF NOT IS_CHAR_DEAD swat10_f1
					enemy_f1 = swat10_f1
					enemytarget_f1 = scplayer
					GOSUB stayshootnoduck_f1label
					swat10_f1flag = 1
				ENDIF
			ENDIF

			//other table
			IF swat11_f1flag = 0
				IF NOT IS_CHAR_DEAD swat11_f1
					enemy_f1 = swat11_f1
					enemytarget_f1 = scplayer
					GOSUB stayshootnoduck_f1label
					swat11_f1flag = 1
				ENDIF
			ENDIF
			
			//right rollout guy in the final corridor
			IF swat15_f1flag = 0
				IF NOT IS_CHAR_DEAD swat15_f1
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2193.41 -1176.12 1029.42 4.0 4.0 3.5 FALSE
						enemy_f1 = swat15_f1
						GOSUB rolloutr_f1label
						swat15_f1flag = 1
					ENDIF
				ENDIF
			ENDIF	

		 	//crouch behind trolley
			IF swat16_f1flag = 0
				IF NOT IS_CHAR_DEAD swat16_f1
					enemy_f1 = swat16_f1
					enemytarget_f1 = scplayer
					GOSUB stayshoot_f1label
					swat16_f1flag = 1
				ENDIF
			ENDIF

			//vent guy upside down shooting set piece
			IF swat14_f1flag = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2193.4 -1173.53 1029.69 3.0 3.0 3.0 FALSE
				OR IS_CHAR_DEAD swat16_f1
					REMOVE_CHAR_ELEGANTLY grove3_f1	//remove grove's from their corner
					REMOVE_CHAR_ELEGANTLY grove4_f1
					REMOVE_CHAR_ELEGANTLY grove5_f1
					REMOVE_CHAR_ELEGANTLY woundedgrove2_f1
					REMOVE_CHAR_ELEGANTLY swatrope1_f1
					REMOVE_CHAR_ELEGANTLY swatrope2_f1
					IF DOES_OBJECT_EXIST vent2_f1
						FREEZE_OBJECT_POSITION vent2_f1 FALSE
						SET_OBJECT_ROTATION_VELOCITY vent2_f1 0.0 0.8 0.0
						ATTACH_MISSION_AUDIO_TO_OBJECT 3 vent2_f1
						PLAY_MISSION_AUDIO 3
					ENDIF
				swat14_f1flag = 1
				ENDIF
			ENDIF
			
			IF swat14_f1flag = 1
				IF NOT IS_CHAR_DEAD swat14_f1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE swat14_f1 swt_vnt_sht_in SWAT 1000.0 FALSE FALSE FALSE TRUE -1
					swat14_f1flag = 2
				ENDIF
			ENDIF




			IF swat14_f1flag = 2
				IF NOT IS_CHAR_DEAD swat14_f1
					IF IS_CHAR_PLAYING_ANIM swat14_f1 swt_vnt_sht_in
						GET_CHAR_ANIM_CURRENT_TIME swat14_f1 swt_vnt_sht_in upsidedownswat_f1
							IF upsidedownswat_f1 = 1.0
								TASK_PLAY_ANIM_NON_INTERRUPTABLE swat14_f1 swt_vnt_sht_loop SWAT 1000.0 FALSE FALSE FALSE TRUE -1
								GET_CHAR_COORDINATES scplayer player_x player_y player_z
								FIRE_SINGLE_BULLET 2193.268 -1165.441 1031.124 player_x player_y player_z 1
								REPORT_MISSION_AUDIO_EVENT_AT_CHAR swat14_f1 SOUND_MINITANK_FIRE
								ADD_BIG_GUN_FLASH 2193.268 -1165.441 1031.124 player_x player_y player_z
								SET_CHAR_NEVER_TARGETTED swat14_f1 FALSE
								SET_CHAR_IS_TARGET_PRIORITY swat14_f1 TRUE
								TIMERB = 1000 //0
								swat14_f1flag = 3
							ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF swat14_f1flag = 3
				IF TIMERB > 1000
					IF NOT IS_CHAR_DEAD swat14_f1
						IF IS_CHAR_PLAYING_ANIM swat14_f1 swt_vnt_sht_loop
							GET_CHAR_ANIM_CURRENT_TIME swat14_f1 swt_vnt_sht_loop upsidedownswat_f1
								IF upsidedownswat_f1 = 1.0 //= 1.0
									TASK_PLAY_ANIM_NON_INTERRUPTABLE swat14_f1 swt_vnt_sht_loop SWAT 1000.0 FALSE FALSE FALSE TRUE -1 //old line
									//TASK_PLAY_ANIM_WITH_FLAGS swat14_f1 swt_vnt_sht_loop SWAT 8.0 FALSE FALSE FALSE TRUE -1 FALSE TRUE //new line
									GET_CHAR_COORDINATES scplayer player_x player_y player_z
									player_z = player_z + 0.5
									//player_y = player_y + 0.0
									FIRE_SINGLE_BULLET 2193.46 -1165.83 1031.55 player_x player_y player_z 3
									REPORT_MISSION_AUDIO_EVENT_AT_CHAR swat14_f1 SOUND_MINITANK_FIRE									 								
									ADD_BIG_GUN_FLASH 2193.268 -1165.441 1031.124 player_x player_y player_z
									TIMERB = 0
								ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			//dies if player walks past him
			IF swat14_f1flag < 4
				IF NOT IS_CHAR_DEAD swat14_f1
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2193.12 -1161.86 1029.61 2.8 2.8 3.0 FALSE
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR swat14_f1 scplayer
						IF DOES_CHAR_EXIST swat14_f1
							SET_CHAR_COLLISION swat14_f1 FALSE
							SET_CHAR_COORDINATES swat14_f1 2193.129 -1164.661 1032.269
							TASK_PLAY_ANIM_NON_INTERRUPTABLE swat14_f1 swt_vnt_sht_die SWAT 1000.0 FALSE FALSE FALSE TRUE -1
							SET_CHAR_NEVER_TARGETTED swat14_f1 TRUE
						ENDIF
						swat14_f1flag = 4
					ENDIF
				ENDIF
			ENDIF
			IF swat14_f1flag = 4
				IF NOT IS_CHAR_DEAD swat14_f1
					IF IS_CHAR_PLAYING_ANIM swat14_f1 swt_vnt_sht_die
						GET_CHAR_ANIM_CURRENT_TIME swat14_f1 swt_vnt_sht_die upsidedownswat_f1
							IF upsidedownswat_f1 = 1.0
								swat14_f1flag = 5
							ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF swat14_f1flag = 5
				IF NOT IS_CHAR_DEAD swat14_f1
					IF NOT IS_CHAR_ON_SCREEN swat14_f1
					OR LOCATE_CHAR_ANY_MEANS_CHAR_2D swat14_f1 scplayer 5.0 5.0 FALSE
						REMOVE_CHAR_ELEGANTLY swat14_f1
						swat14_f1flag = 6
					ENDIF
				ENDIF
			ENDIF

			
			//roll left 
			IF swat17_f1flag = 0
				IF NOT IS_CHAR_DEAD swat17_f1
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2193.19 -1160.67 1029.54 4.0 4.0 3.5 FALSE
						enemy_f1 = swat17_f1
						GOSUB rolloutl_f1label
						//DELETE_OBJECT vent2_f1
						swat17_f1flag = 1
					ENDIF
				ENDIF
			ENDIF

			//guy shooting sweet
			IF swat18_f1flag = 0
				IF NOT IS_CHAR_DEAD swat18_f1
					IF NOT IS_CHAR_DEAD sweet
						enemy_f1 = swat18_f1
						enemytarget_f1 = sweet
						GOSUB stayshoot_f1label
						swat18_f1flag = 1
					ENDIF
				ENDIF
			ENDIF

			//sweet shooting back
			IF sweet_f1flag = 0
				IF NOT IS_CHAR_DEAD sweet
					IF NOT IS_CHAR_DEAD swat18_f1
						enemy_f1 = sweet
						enemytarget_f1 = swat18_f1
						GOSUB stayshootnoduck_f1label
						sweet_f1flag = 1
					ENDIF
				ENDIF
			ENDIF

			IF vent1a_f1flag = 3
				IF IS_CHAR_DEAD swat12_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat12_f1
					vent1a_f1flag = 4
				ENDIF
			ENDIF

			IF vent1b_f1flag = 3
				IF IS_CHAR_DEAD swat13_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat13_f1
					vent1a_f1flag = 4
				ENDIF
			ENDIF

			IF grove5_f1flag = 1
				IF IS_CHAR_DEAD grove5_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED grove5_f1
		 			grove5_f1flag = 2
				ENDIF
			ENDIF

			IF swat7_f1flag = 1
				IF IS_CHAR_DEAD swat7_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat7_f1
					swat7_f1flag = 2
				ENDIF
			ENDIF

			IF swat8_f1flag = 2
				IF IS_CHAR_DEAD swat8_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat8_f1
		 			swat8_f1flag = 3
				ENDIF
			ENDIF
					
			IF swat9_f1flag = 2
				IF IS_CHAR_DEAD swat9_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat9_f1
		 			swat9_f1flag = 4
				ENDIF
			ENDIF

			IF swat10_f1flag = 1
				IF IS_CHAR_DEAD swat10_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat10_f1
		 			swat10_f1flag = 2
				ENDIF
			ENDIF

			IF swat11_f1flag = 1
				IF IS_CHAR_DEAD swat11_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat11_f1
		 			swat11_f1flag = 2
				ENDIF
			ENDIF
			
			IF swat15_f1flag = 1
				IF IS_CHAR_DEAD swat15_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat15_f1
		 			swat15_f1flag = 2
				ENDIF
			ENDIF

			IF swat16_f1flag = 1
				IF IS_CHAR_DEAD swat16_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat16_f1
		 			swat16_f1flag = 2
				ENDIF
			ENDIF

			IF swat14_f1flag = 4
				IF IS_CHAR_DEAD swat14_f1
				   	MARK_CHAR_AS_NO_LONGER_NEEDED swat14_f1
					swat14_f1flag = 5
				ENDIF
			ENDIF

			IF swat17_f1flag = 1
				IF IS_CHAR_DEAD swat17_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat17_f1
					swat17_f1flag = 2
				ENDIF
			ENDIF

			IF swat18_f1flag = 1
				IF IS_CHAR_DEAD swat18_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED swat18_f1
					swat18_f1flag = 2
				ENDIF
			ENDIF

			IF hoochie1_f1flag > 5
				IF NOT IS_CHAR_DEAD hoochie1_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED hoochie1_f1
					REMOVE_ANIMATION KISSING
					hoochie1_f1flag = 6
				ENDIF
			ENDIF

//			IF sweetexit_f1flag = 0
//				IF swat17_f1flag = 2
//					IF swat18_f1flag = 2
//						sweetexit_f1flag = 1	//player has killed two nearby swat
//					ENDIF
//				ENDIF
//			ENDIF

			
			//sweet and player leaving the motel
			IF sweetexit_f1flag = 0
				IF NOT IS_CHAR_DEAD sweet
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D sweet scplayer 3.5 3.5 3.5 FALSE //used to be 7.5
						IF IS_CHAR_DEAD	swat17_f1
							IF IS_CHAR_DEAD swat18_f1

								GET_CURRENT_CHAR_WEAPON scplayer playerweapon_f1

								DO_FADE 250 FADE_OUT
								SET_PLAYER_CONTROL PLAYER1 OFF
								
							   	SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet FALSE

								//mark loads of stuff as no longer needed
								REMOVE_ANIMATION KISSING
								MARK_MODEL_AS_NO_LONGER_NEEDED FAM3
								MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
								MARK_MODEL_AS_NO_LONGER_NEEDED lxr_motel_doorsim
								MARK_MODEL_AS_NO_LONGER_NEEDED lxr_motelvent
								MARK_MODEL_AS_NO_LONGER_NEEDED imy_skylight
								MARK_MODEL_AS_NO_LONGER_NEEDED KMB_TROLLEY
								MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
								MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
								REMOVE_CHAR_ELEGANTLY grove1_f1
								REMOVE_CHAR_ELEGANTLY grove2_f1
								REMOVE_CHAR_ELEGANTLY grove3_f1
								REMOVE_CHAR_ELEGANTLY grove4_f1
								REMOVE_CHAR_ELEGANTLY grove5_f1
								REMOVE_CHAR_ELEGANTLY swatrope1_f1
								REMOVE_CHAR_ELEGANTLY swatrope2_f1
								REMOVE_CHAR_ELEGANTLY swat1_f1
								REMOVE_CHAR_ELEGANTLY swat2_f1
								REMOVE_CHAR_ELEGANTLY swat3_f1
								REMOVE_CHAR_ELEGANTLY swat4_f1
								REMOVE_CHAR_ELEGANTLY woundedgrove1_f1 
								REMOVE_CHAR_ELEGANTLY woundedgrove2_f1
								REMOVE_CHAR_ELEGANTLY swat5_f1 
								REMOVE_CHAR_ELEGANTLY swat6_f1 
								REMOVE_CHAR_ELEGANTLY swat7_f1 
								REMOVE_CHAR_ELEGANTLY swat8_f1 
								REMOVE_CHAR_ELEGANTLY swat9_f1 
								REMOVE_CHAR_ELEGANTLY swat10_f1 
								REMOVE_CHAR_ELEGANTLY swat11_f1 
								REMOVE_CHAR_ELEGANTLY swat12_f1 
								REMOVE_CHAR_ELEGANTLY swat13_f1 
								REMOVE_CHAR_ELEGANTLY swat14_f1 
								REMOVE_CHAR_ELEGANTLY swat15_f1
								REMOVE_CHAR_ELEGANTLY swat16_f1
								REMOVE_CHAR_ELEGANTLY swat17_f1
								REMOVE_CHAR_ELEGANTLY swat18_f1
								REMOVE_CHAR_ELEGANTLY hoochie1_f1 
								REMOVE_CHAR_ELEGANTLY hoochie2_f1
								DELETE_OBJECT breachdoor_f1
								DELETE_OBJECT vent1_f1
								DELETE_OBJECT vent2_f1
								KILL_FX_SYSTEM breachfx_f1						

								CLEAR_MISSION_AUDIO 1
								CLEAR_MISSION_AUDIO 2

								LOAD_MISSION_AUDIO 1 SOUND_FIN1_JH	//What took you? Where’s Smoke and Ryder?
								LOAD_MISSION_AUDIO 2 SOUND_FIN1_JI	//They shook on us!

								REQUEST_ANIMATION GANGS

								LOAD_ALL_MODELS_NOW
																
								WHILE NOT HAS_ANIMATION_LOADED GANGS
									OR NOT HAS_MISSION_AUDIO_LOADED 1
									OR NOT HAS_MISSION_AUDIO_LOADED 2
									WAIT 0
								ENDWHILE

								sweetexit_f1flag = 2

							ELSE
								PRINT_NOW LAF1_21 1000 1 //Take all the nearby SWAT out.  Then we can get out of here.					
							ENDIF
						ELSE
							PRINT_NOW LAF1_21 1000 1 //Take all the nearby SWAT out.  Then we can get out of here.					
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF sweetexit_f1flag = 2
	 			IF NOT IS_CHAR_DEAD sweet
					SWITCH_WIDESCREEN ON
					CLEAR_AREA 2223.971 -1149.75 200.0 200.0 FALSE
					SET_NEAR_CLIP 0.1

					DO_FADE 500 FADE_IN
					
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_CHAR_TASKS_IMMEDIATELY sweet
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
					SET_CURRENT_CHAR_WEAPON sweet WEAPONTYPE_UNARMED
					REMOVE_WEAPON_FROM_CHAR sweet WEAPONTYPE_PISTOL

					SET_FIXED_CAMERA_POSITION 2197.4873 -1157.6752 1030.3558 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2198.3828 -1157.2404 1030.2614  JUMP_CUT
					
					SET_CHAR_COORDINATES sweet 2202.1877 -1156.93 1028.844 //2202.23 -1156.914 1028.84
					SET_CHAR_HEADING sweet 85.6456
					TASK_PLAY_ANIM sweet prtial_gngtlkH GANGS 8.0 FALSE FALSE FALSE FALSE -1

					SET_CHAR_COORDINATES scplayer 2197.2 -1156.929 1028.844 //2202.23 -1156.914 1028.84
					SET_CHAR_HEADING scplayer 269.764

					TASK_GO_STRAIGHT_TO_COORD scplayer 2201.1877 -1156.93 1028.844 PEDMOVE_WALK -1

					PLAY_MISSION_AUDIO 1
					PRINT_NOW FIN1_JH 5000 1 //What took you? Where’s Smoke and Ryder?
					START_CHAR_FACIAL_TALK sweet 10000 
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
					ENDWHILE
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					IF NOT IS_CHAR_DEAD sweet
						STOP_CHAR_FACIAL_TALK sweet
					ENDIF
					LOAD_MISSION_AUDIO 1 SOUND_FIN1_JK	//Fuck it, let’s get out of here!
										
					//close up
					SET_FIXED_CAMERA_POSITION 2201.2344 -1157.5446 1030.3730  0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2201.8037 -1156.7233 1030.4058 JUMP_CUT

					//imran
		 			IF NOT IS_CHAR_DEAD sweet
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						CLEAR_CHAR_TASKS_IMMEDIATELY sweet
						SET_CHAR_COORDINATES scplayer 2201.1877 -1156.93 1028.844
						SET_CHAR_HEADING scplayer 270.0
//						SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

						TASK_PLAY_ANIM sweet hndshkfa_swt GANGS 4.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM scplayer hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
						TIMERA = 0
					ENDIF

					PLAY_MISSION_AUDIO 2 
					PRINT_NOW FIN1_JI 2000 1 //They shook on us!
					START_CHAR_FACIAL_TALK scplayer 2000
					//////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////
					SKIP_CUTSCENE_START
					//////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////
				
					sweetexit_f1flag = 3
				ENDIF
			ENDIF

			IF sweetexit_f1flag > 2
				IF sweetexit_f1flag < 6
					IF audiosweet_f1flag = 0
						IF HAS_MISSION_AUDIO_FINISHED 2
							IF HAS_MISSION_AUDIO_LOADED 1
								CLEAR_PRINTS
								STOP_CHAR_FACIAL_TALK scplayer
								PLAY_MISSION_AUDIO 1
								IF NOT IS_CHAR_DEAD sweet
									START_CHAR_FACIAL_TALK sweet 10000 
								ENDIF
								PRINT_NOW FIN1_JK 2000 1//Fuck it, let’s get out of here!
								audiosweet_f1flag = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF audiosweet_f1flag = 1
				IF NOT IS_CHAR_DEAD sweet
					IF HAS_MISSION_AUDIO_FINISHED 1
						STOP_CHAR_FACIAL_TALK sweet
						audiosweet_f1flag = 2
					ENDIF
				ENDIF
			ENDIF
					   
			IF sweetexit_f1flag = 3
				IF NOT IS_CHAR_DEAD sweet
					GET_SCRIPT_TASK_STATUS sweet TASK_PLAY_ANIM sweetstatus_f1
						IF sweetstatus_f1 = FINISHED_TASK
							GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM playerstatus_f1
								IF playerstatus_f1 = FINISHED_TASK
									IF HAS_MISSION_AUDIO_FINISHED 2
										IF HAS_MISSION_AUDIO_LOADED 1
											TIMERA = 0
											SET_FIXED_CAMERA_POSITION 2202.4089 -1157.1904 1030.5150 0.0 0.0 0.0
											POINT_CAMERA_AT_POINT 2201.4138 -1157.1287 1030.4376 JUMP_CUT
											FLUSH_ROUTE
											EXTEND_ROUTE 2193.09 -1157.16 1029.84
											EXTEND_ROUTE 2193.379 -1145.899 1029.84
											TASK_FOLLOW_POINT_ROUTE scplayer PEDMOVE_WALK FOLLOW_ROUTE_ONCE
											TASK_FOLLOW_POINT_ROUTE sweet PEDMOVE_WALK FOLLOW_ROUTE_ONCE
											sweetexit_f1flag = 4
										ENDIF
									ENDIF
								ENDIF
						ENDIF
				ENDIF
			ENDIF

			IF sweetexit_f1flag = 4
				sweetexit_f1flag = 5
			ENDIF

			IF sweetexit_f1flag = 5
				IF HAS_MISSION_AUDIO_FINISHED 1
					IF TIMERA > 1500
						//////////////////////////////////////////////////////////////////////////////////////
						//////////////////////////////////////////////////////////////////////////////////////
						SKIP_CUTSCENE_END
						//////////////////////////////////////////////////////////////////////////////////////
						//////////////////////////////////////////////////////////////////////////////////////
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						
						IF NOT IS_CHAR_DEAD sweet
							STOP_CHAR_FACIAL_TALK sweet
						ENDIF

						DO_FADE 250 FADE_OUT

						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE
						
						SET_CURRENT_CHAR_WEAPON scplayer playerweapon_f1

						CLEAR_AREA 2192.1936 -1153.241 100.0 100.0 TRUE
						
						IF NOT IS_CHAR_DEAD sweet
							STOP_CHAR_FACIAL_TALK sweet
							STOP_CHAR_FACIAL_TALK scplayer
							SWITCH_ENTRY_EXIT motel1 TRUE
							SET_CHAR_HAS_USED_ENTRY_EXIT sweet 2214.41 -1150.48 3.0
							SET_CHAR_PROOFS	sweet TRUE TRUE TRUE TRUE TRUE
							CLEAR_CHAR_TASKS_IMMEDIATELY sweet 
							CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

							SET_CHAR_COORDINATES sweet 2192.1936 -1153.241 32.56
							SET_CHAR_HEADING sweet 180.359
							SET_CHAR_COORDINATES scplayer 2193.99 -1153.14 33.56
							SET_CHAR_HEADING scplayer 180.359
							SET_CHAR_AREA_VISIBLE scplayer 0
							SET_CHAR_AREA_VISIBLE sweet 0
							SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2214.38 -1150.45 1.0
							SET_CHAR_HAS_USED_ENTRY_EXIT sweet 2214.38 -1150.45 1.0
							SET_AREA_VISIBLE 0

							SET_FIXED_CAMERA_POSITION 2201.6438 -1164.3506 38.3367 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 2201.0083 -1163.6409 38.0327 JUMP_CUT
							SWITCH_ENTRY_EXIT motel1 FALSE
						ENDIF

						REMOVE_ANIMATION GANGS

						REQUEST_MODEL POLMAV
						REQUEST_CAR_RECORDING 349
						LOAD_MISSION_AUDIO 1 SOUND_FIN1_AA //This is the Los Santos Police Department; 
						LOAD_MISSION_AUDIO 2 SOUND_FIN1_JL //CJ, that chopper’s all over us! Hit it!
						
						LOAD_ALL_MODELS_NOW

						WHILE NOT HAS_MODEL_LOADED POLMAV
							OR NOT HAS_CAR_RECORDING_BEEN_LOADED 349
							OR NOT HAS_MISSION_AUDIO_LOADED	1
							OR NOT HAS_MISSION_AUDIO_LOADED 2
							WAIT 0
						ENDWHILE
						
						TASK_GO_STRAIGHT_TO_COORD scplayer 2193.86 -1157.682 33.563 PEDMOVE_WALK -1

						WAIT 500

						IF NOT IS_CHAR_DEAD sweet
							TASK_GO_STRAIGHT_TO_COORD sweet 2192.25 -1158.078 33.56 PEDMOVE_WALK -1
						ENDIF

						
						CREATE_CAR POLMAV 2249.51 -1111.52 56.1 extpoliceheli_f1
						SET_PETROL_TANK_WEAKPOINT extpoliceheli_f1 FALSE
						SET_CAR_ONLY_DAMAGED_BY_PLAYER extpoliceheli_f1 TRUE
						SET_CAR_HEALTH extpoliceheli_f1 2000
						CREATE_CHAR_INSIDE_CAR extpoliceheli_f1 PEDTYPE_MISSION2 SWAT exthelidriver_f1
						SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
						LOCK_CAR_DOORS extpoliceheli_f1 CARLOCK_LOCKED
						SET_CHAR_CANT_BE_DRAGGED_OUT exthelidriver_f1 TRUE
						SET_CAR_FORWARD_SPEED extpoliceheli_f1 30.0
						SET_CHAR_DECISION_MAKER exthelidriver_f1 motel_DM
						SET_HELI_BLADES_FULL_SPEED extpoliceheli_f1

						CREATE_CHAR PEDTYPE_MISSION4 SWAT 2161.84 -1154.26 25.09 swat1_f1
						SET_CHAR_RELATIONSHIP swat1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
						SET_CHAR_RELATIONSHIP swat1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						SET_CHAR_DECISION_MAKER swat1_f1 extmotel_DM
						CREATE_CHAR PEDTYPE_MISSION4 SWAT 2159.84 -1154.26 25.09 swat2_f1
						SET_CHAR_RELATIONSHIP swat2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
						SET_CHAR_DECISION_MAKER swat2_f1 extmotel_DM
						CREATE_CHAR PEDTYPE_MISSION4 SWAT 2159.84 -1154.26 -5.09 swat3_f1
						SET_CHAR_RELATIONSHIP swat3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
						SET_CHAR_RELATIONSHIP swat3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						SET_CHAR_DECISION_MAKER swat3_f1 extmotel_DM
						CREATE_CHAR PEDTYPE_MISSION4 SWAT 2159.84 -1154.26 -8.09 swat4_f1
						SET_CHAR_RELATIONSHIP swat4_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
						SET_CHAR_DECISION_MAKER swat4_f1 extmotel_DM

						swat1_f1flag = 0
						swat2_f1flag = 0
						swat3_f1flag = 0
						swat4_f1flag = 0
						ATTACH_CHAR_TO_CAR swat1_f1 extpoliceheli_f1 1.4 1.3 -0.1 FACING_RIGHT 190.0 WEAPONTYPE_MP5
						SET_CHAR_HEALTH swat1_f1 150
						SET_CHAR_SHOOT_RATE swat1_f1 70
						ATTACH_CHAR_TO_CAR swat2_f1 extpoliceheli_f1 1.4 -0.8 -0.1 FACING_RIGHT 190.0 WEAPONTYPE_MP5
						SET_CHAR_HEALTH swat2_f1 150
						SET_CHAR_SHOOT_RATE swat2_f1 70
						ATTACH_CHAR_TO_CAR swat3_f1 extpoliceheli_f1 -1.4 1.3 -0.1 FACING_LEFT 190.0 WEAPONTYPE_MP5
						SET_CHAR_HEALTH swat3_f1 150
						SET_CHAR_SHOOT_RATE swat3_f1 70
						ATTACH_CHAR_TO_CAR swat4_f1 extpoliceheli_f1 -1.4 -0.8 -0.1 FACING_LEFT 190.0 WEAPONTYPE_MP5
						SET_CHAR_HEALTH swat4_f1 150
						SET_AREA_VISIBLE 0
						LOAD_SCENE 2193.86 -1157.682 33.563

						SET_TIME_OF_DAY 2 30
						
						CLEAR_EXTRA_COLOURS FALSE
						DO_FADE 500 FADE_IN
						TIMERB = 0
						sweetexit_f1flag = 6
						roofmotel_f1flag = 1
					ENDIF
				ENDIF
			ENDIF

		ENDIF
		
		//If sweet dies	
		IF IS_CHAR_DEAD sweet
			PRINT_NOW LAF1_70 5000 1 //Sweet died!
			GOTO mission_drugs4_failed //sweet died
		ENDIF


		//dialogue
		IF sweetexit_f1flag = 0

			GOSUB process_audio_f1
			
			//play mission audio
			IF progressaudio_f1flag = 0
				IF handlingaudio_f1flag = 0
					IF woundedgrove1_f1flag = 1
						audio_label_f1 = SOUND_FIN1_JC	//Where the OG’s at – I gotta go get my brother, Sweet.
						$input_text_f1 = FIN1_JC	//Where the OG’s at – I gotta go get my brother, Sweet.
						GOSUB load_audio_f1
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_f1flag = 1
				IF handlingaudio_f1flag = 0
					IF woundedgrove1_f1flag = 1
						attachaudio_f1flag = 1						
						audio_label_f1 = SOUND_FIN1_JD	//They were meeting in the back of the motel someplace…
						$input_text_f1 = FIN1_JD //They were meeting in the back of the motel someplace…
						GOSUB load_audio_f1
					ENDIF
				ENDIF
			ENDIF

			IF swatwindosmash_f1flag > 0
				IF progressaudio_f1flag = 2
					IF handlingaudio_f1flag = 0
						audio_label_f1 = SOUND_FIN1_JF	//Families! Cops comin’ in behind!
						$input_text_f1 = FIN1_JF	//Families! Cops comin’ in behind!
						GOSUB load_audio_f1
					ENDIF
				ENDIF
			ENDIF

			//corner where swat run down stairs
			IF swat8_f1flag > 0
				IF progressaudio_f1flag = 3
					IF handlingaudio_f1flag = 0
						audio_label_f1 = SOUND_FIN1_BD	//Get some suppressing fire in there!
						$input_text_f1 = FIN1_BD	//Get some suppressing fire in there!
						GOSUB load_audio_f1
					ENDIF
				ENDIF
			ENDIF

			//upside down guy
			IF swat14_f1flag > 0
				IF progressaudio_f1flag = 4
					IF handlingaudio_f1flag = 0
						audio_label_f1 = SOUND_FIN1_CK	//Unit down, repeat, unit down!
						$input_text_f1 = FIN1_CK	//Unit down, repeat, unit down!
						GOSUB load_audio_f1
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_f1flag = 5
				IF handlingaudio_f1flag = 0
					audio_label_f1 = SOUND_FIN1_BE	//Gimme some fucking covering fire!
					$input_text_f1 = FIN1_BE	//Gimme some fucking covering fire!
					GOSUB load_audio_f1
				ENDIF
			ENDIF

			IF missionaudio2_f1flag = 1
				IF HAS_MISSION_AUDIO_FINISHED 2
					CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO 2 SOUND_FIN1_AE	//Get into positions!
					missionaudio2_f1flag = 2
				ENDIF
			ENDIF
			IF missionaudio2_f1flag = 2
				IF HAS_MISSION_AUDIO_LOADED 2
					missionaudio2_f1flag = 3
				ENDIF
			ENDIF

		ENDIF

		
	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////	On Roof		////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF roofmotel_f1flag = 1
	
	//change camera angle and get heli to come up
	IF sweetexit_f1flag = 6
		IF TIMERB > 2000

			IF NOT IS_CAR_DEAD extpoliceheli_f1
				START_PLAYBACK_RECORDED_CAR extpoliceheli_f1 349
			ENDIF

			SET_FIXED_CAMERA_POSITION 2195.0986 -1157.9208 34.0291 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT 2194.1025 -1157.9785 33.9628 JUMP_CUT

			IF NOT IS_CHAR_DEAD sweet
				IF NOT IS_CHAR_DEAD exthelidriver_f1
					WAIT 200
					IF NOT IS_CHAR_DEAD exthelidriver_f1
						TASK_LOOK_AT_CHAR scplayer exthelidriver_f1 3000
					ENDIF
					WAIT 100
					IF NOT IS_CHAR_DEAD exthelidriver_f1
						IF NOT IS_CHAR_DEAD sweet
							TASK_LOOK_AT_CHAR sweet exthelidriver_f1 3000
						ENDIF
					ENDIF
					PLAY_MISSION_AUDIO 1 //FIN1_AA //This is the Los Santos Police Department; 
					PRINT_NOW FIN1_AA 3000 1
					TIMERB = 0
					sweetexit_f1flag = 7
				ENDIF
			ENDIF

		ENDIF
	ENDIF

	IF sweetexit_f1flag = 7
		IF TIMERB > 2500
			IF NOT IS_CHAR_DEAD sweet
				IF NOT IS_CAR_DEAD extpoliceheli_f1

					//////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////
					skiphelicutscene_f1flag = 0
					SKIP_CUTSCENE_START
					//////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////
					SET_FIXED_CAMERA_POSITION 2176.6411 -1179.4252 38.9175 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2177.1365 -1178.5883 38.6847 JUMP_CUT

					IF NOT IS_CHAR_DEAD swat1_f1
						IF swat1_f1flag = 0
							enemy_f1 = swat1_f1
							enemytarget_f1 = scplayer
							GOSUB stayshoot_f1label
							swat1_f1flag = 1
						ENDIF
					ENDIF

					IF NOT IS_CHAR_DEAD swat2_f1
						IF swat2_f1flag = 0
							enemy_f1 = swat2_f1
							IF NOT IS_CHAR_DEAD sweet
								enemytarget_f1 = sweet
							ENDIF
							GOSUB stayshootnoduck_f1label
							swat2_f1flag = 1
						ENDIF
					ENDIF

					IF NOT IS_CHAR_DEAD swat3_f1
						IF swat3_f1flag = 0
							enemy_f1 = swat3_f1
							IF NOT IS_CHAR_DEAD sweet
								enemytarget_f1 = sweet
							ENDIF
							GOSUB stayshootnoduck_f1label
							swat3_f1flag = 1
						ENDIF
					ENDIF

					IF NOT IS_CHAR_DEAD swat4_f1
						IF swat4_f1flag = 0
							enemy_f1 = swat4_f1
							enemytarget_f1 = scplayer
							GOSUB stayshootnoduck_f1label
							swat4_f1flag = 1
						ENDIF
					ENDIF

					TASK_GO_STRAIGHT_TO_COORD sweet 2192.683 -1168.586 33.56 PEDMOVE_RUN 5000
					TASK_GO_STRAIGHT_TO_COORD scplayer 2198.424 -1158.32 33.56 PEDMOVE_RUN 5000 //2193.91 -1164.24 32.86 PEDMOVE_RUN 5000
					TIMERB = 0
					sweetexit_f1flag = 8
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF sweetexit_f1flag = 8
		IF TIMERB > 1500
			IF NOT IS_CAR_DEAD extpoliceheli_f1
				IF NOT IS_CHAR_DEAD sweet

					IF IS_PLAYBACK_GOING_ON_FOR_CAR extpoliceheli_f1
						STOP_PLAYBACK_RECORDED_CAR extpoliceheli_f1
					ENDIF

					SET_FIXED_CAMERA_POSITION 2196.3381 -1157.2228 32.9594 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2195.7617 -1158.0323 33.0705 JUMP_CUT

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					SET_CHAR_COORDINATES scplayer 2195.914 -1159.764 32.563
					SET_CHAR_HEADING scplayer 181.0915
					TASK_PLAY_ANIM scplayer swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE TRUE -1
					PLAY_MISSION_AUDIO 2	//FIN1_JL CJ, that chopper’s all over us! Hit it!
					PRINT_NOW FIN1_JL 3000 1 //CJ, that chopper’s all over us! Hit it!

					SET_CAR_COORDINATES extpoliceheli_f1 2185.43 -1172.69 37.05
					HELI_GOTO_COORDS extpoliceheli_f1 2194.97 -1182.27 37.0 7.0 7.0
					TIMERB = 0
					sweetexit_f1flag = 9
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF sweetexit_f1flag = 9
		IF NOT IS_CHAR_DEAD sweet
			IF IS_CHAR_PLAYING_ANIM scplayer swt_wllpk_L
				GET_CHAR_ANIM_CURRENT_TIME scplayer swt_wllpk_L playeranim_f1
					IF playeranim_f1 = 1.0
						TASK_PLAY_ANIM scplayer swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1		
						IF NOT IS_CHAR_DEAD exthelidriver_f1
							enemy_f1 = sweet
							enemytarget_f1 = exthelidriver_f1
							GOSUB stayshootnoduck_f1label
						ENDIF
						sweetexit_f1flag = 10
					ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF sweetexit_f1flag = 10
		IF NOT IS_CHAR_DEAD sweet
			IF IS_CHAR_PLAYING_ANIM scplayer swt_wllshoot_in_L
				GET_CHAR_ANIM_CURRENT_TIME scplayer swt_wllshoot_in_L playeranim_f1
					IF playeranim_f1 = 1.0
						IF NOT IS_CAR_DEAD extpoliceheli_f1
							CREATE_SEARCHLIGHT_ON_VEHICLE extpoliceheli_f1 0.0 1.0 -0.5 2193.73 -1166.85 34.06 4.0 0.4 helispotlight_f1
							POINT_SEARCHLIGHT_AT_CHAR helispotlight_f1 sweet 0.2
						ENDIF
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 

						//////////////////////////////////////////////////////////////////////////////////////
						//////////////////////////////////////////////////////////////////////////////////////
						skiphelicutscene_f1flag = 1
						SKIP_CUTSCENE_END
						//////////////////////////////////////////////////////////////////////////////////////
						//////////////////////////////////////////////////////////////////////////////////////
						IF skiphelicutscene_f1flag = 0
							
							CLEAR_PRINTS

							DO_FADE 500 FADE_OUT
							
							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE
							
							CLEAR_MISSION_AUDIO 1
							CLEAR_MISSION_AUDIO 2

							//delete everything
							IF NOT IS_CAR_DEAD extpoliceheli_f1
								IF IS_PLAYBACK_GOING_ON_FOR_CAR extpoliceheli_f1
									STOP_PLAYBACK_RECORDED_CAR extpoliceheli_f1
								ENDIF
							ENDIF

							DELETE_CAR extpoliceheli_f1
							DELETE_CHAR swat1_f1
							DELETE_CHAR swat2_f1
							DELETE_CHAR swat3_f1
							DELETE_CHAR swat4_f1

							//creating everything
							REQUEST_MODEL POLMAV
							REQUEST_MODEL SWAT
							REQUEST_MODEL MP5LNG

							LOAD_ALL_MODELS_NOW

							WHILE NOT HAS_MODEL_LOADED POLMAV
								OR NOT HAS_MODEL_LOADED SWAT
								OR NOT HAS_MODEL_LOADED MP5LNG
								WAIT 0
							ENDWHILE

							IF NOT IS_CHAR_DEAD sweet
								CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
								CLEAR_CHAR_TASKS_IMMEDIATELY sweet
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet FALSE
							ENDIF

							CREATE_CAR POLMAV 2249.51 -1111.52 56.1 extpoliceheli_f1
							SET_PETROL_TANK_WEAKPOINT extpoliceheli_f1 FALSE
							SET_CAR_ONLY_DAMAGED_BY_PLAYER extpoliceheli_f1 TRUE
							SET_CAR_FORWARD_SPEED extpoliceheli_f1 30.0
							SET_CAR_HEALTH extpoliceheli_f1 2000
							CREATE_SEARCHLIGHT_ON_VEHICLE extpoliceheli_f1 0.0 1.0 -0.5 2193.73 -1166.85 34.06 3.0 0.4 helispotlight_f1
							POINT_SEARCHLIGHT_AT_CHAR helispotlight_f1 sweet 0.2
							CREATE_CHAR_INSIDE_CAR extpoliceheli_f1 PEDTYPE_MISSION2 SWAT exthelidriver_f1
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
							LOCK_CAR_DOORS extpoliceheli_f1 CARLOCK_LOCKED
							SET_CHAR_CANT_BE_DRAGGED_OUT exthelidriver_f1 TRUE
							SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
							SET_CHAR_DECISION_MAKER exthelidriver_f1 motel_DM
							SET_HELI_BLADES_FULL_SPEED extpoliceheli_f1
							SET_CAR_COORDINATES extpoliceheli_f1 2188.28 -1176.54 39.54
							SET_CAR_HEADING extpoliceheli_f1 210.0

							CREATE_CHAR PEDTYPE_MISSION4 SWAT 2161.84 -1154.26 25.09 swat1_f1
							SET_CHAR_RELATIONSHIP swat1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
							SET_CHAR_RELATIONSHIP swat1_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_DECISION_MAKER swat1_f1 extmotel_DM
							CREATE_CHAR PEDTYPE_MISSION4 SWAT 2159.84 -1154.26 25.09 swat2_f1
							SET_CHAR_RELATIONSHIP swat2_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
							SET_CHAR_DECISION_MAKER swat2_f1 extmotel_DM
							CREATE_CHAR PEDTYPE_MISSION4 SWAT 2159.84 -1154.26 -5.09 swat3_f1
							SET_CHAR_RELATIONSHIP swat3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
							SET_CHAR_RELATIONSHIP swat3_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
							SET_CHAR_DECISION_MAKER swat3_f1 extmotel_DM
							CREATE_CHAR PEDTYPE_MISSION4 SWAT 2159.84 -1154.26 -8.09 swat4_f1
							SET_CHAR_RELATIONSHIP swat4_f1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL
							SET_CHAR_DECISION_MAKER swat4_f1 extmotel_DM

							swat1_f1flag = 0
							swat2_f1flag = 0
							swat3_f1flag = 0
							swat4_f1flag = 0
							ATTACH_CHAR_TO_CAR swat1_f1 extpoliceheli_f1 1.4 1.3 -0.1 FACING_RIGHT 190.0 WEAPONTYPE_MP5
							SET_CHAR_HEALTH swat1_f1 150
							SET_CHAR_SHOOT_RATE swat1_f1 70
							ATTACH_CHAR_TO_CAR swat2_f1 extpoliceheli_f1 1.4 -0.8 -0.1 FACING_RIGHT 190.0 WEAPONTYPE_MP5
							SET_CHAR_HEALTH swat2_f1 150
							SET_CHAR_SHOOT_RATE swat2_f1 70
							ATTACH_CHAR_TO_CAR swat3_f1 extpoliceheli_f1 -1.4 1.3 -0.1 FACING_LEFT 190.0 WEAPONTYPE_MP5
							SET_CHAR_HEALTH swat3_f1 150
							SET_CHAR_SHOOT_RATE swat3_f1 70
							ATTACH_CHAR_TO_CAR swat4_f1 extpoliceheli_f1 -1.4 -0.8 -0.1 FACING_LEFT 190.0 WEAPONTYPE_MP5
							SET_CHAR_HEALTH swat4_f1 150

							LOAD_SCENE 2193.86 -1157.682 33.563

							SET_CHAR_COORDINATES scplayer 2194.53 -1159.24 32.563
							SET_CHAR_HEADING scplayer 173.208

							SET_CHAR_COORDINATES sweet 2192.638 -1169.202 32.563
							SET_CHAR_HEADING sweet 180.668
							RESTORE_CAMERA_JUMPCUT
							SET_CAMERA_BEHIND_PLAYER

							DO_FADE 750 FADE_IN

						ENDIF

						swat1_f1flag = 0
						swat2_f1flag = 0
						swat3_f1flag = 0
						swat4_f1flag = 0

						//////////////////////////////////////////////////////////////////////////////////////
						IF NOT IS_CHAR_DEAD sweet
							IF NOT IS_CHAR_DEAD exthelidriver_f1
								SET_CHAR_PROOFS	sweet FALSE TRUE TRUE TRUE FALSE
								GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_MP5 99999
								enemy_f1 = sweet
								enemytarget_f1 = exthelidriver_f1
								GOSUB stayshootnoduck_f1label
							ENDIF
						ENDIF
						IF NOT IS_CAR_DEAD extpoliceheli_f1
							SET_HELI_REACHED_TARGET_DISTANCE extpoliceheli_f1 3
							ADD_BLIP_FOR_CAR extpoliceheli_f1 heli_f1blip
						ENDIF

						SET_CHAR_HEALTH scplayer 100

						REMOVE_DECISION_MAKER coward_DM
						TIMERA = 0
						SWITCH_WIDESCREEN OFF
						SET_PLAYER_CONTROL PLAYER1 ON
						IF NOT IS_CHAR_DEAD sweet
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER sweet FALSE
							SET_CHAR_HEALTH sweet 500
							sweethealth_f1 = 100
						ENDIF
						REMOVE_ANIMATION SWAT
						RESTORE_CAMERA_JUMPCUT
						SET_CAMERA_BEHIND_PLAYER
						DISPLAY_ONSCREEN_COUNTER_WITH_STRING sweethealth_f1 COUNTER_DISPLAY_BAR LAF1_20
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						LOAD_MISSION_AUDIO 1 SOUND_FIN1_JM	//CJ, dump on that helicopter!
						LOAD_MISSION_AUDIO 2 SOUND_FIN1_CL //Mayday, mayday! Buzzard 1 is going down!
						progressaudio_f1flag = 0
						handlingaudio_f1flag = 0
						PRINT_NOW LAF1_5 5000 1 //~s~Protect ~b~Sweet ~s~and destroy the ~r~SWAT helicopter~s~.
						roofmotel_f1flag = 2
						sweetexit_f1flag = 11

					ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF sweetexit_f1flag < 11
		IF IS_CHAR_DEAD sweet
			PRINT_NOW LAF1_70 5000 1//Sweet died!
			GOTO mission_drugs4_failed //sweet died
		ENDIF
	ENDIF

ENDIF


IF roofmotel_f1flag = 2
	
	//display health bar
	IF NOT IS_CHAR_DEAD sweet
		GET_CHAR_HEALTH sweet sweethealth_f1
		sweethealth_f1 = sweethealth_f1 / 5
	ELSE
		PRINT_NOW LAF1_70 5000 1//Sweet died!
		GOTO mission_drugs4_failed //sweet died
	ENDIF

	//sweet moves across the roof and heli starts movement
	IF NOT IS_CHAR_DEAD sweet

		IF sweetexit_f1flag = 11
			IF TIMERA > 4000
				TASK_GO_STRAIGHT_TO_COORD sweet 2193.199 -1179.67 33.56 PEDMOVE_RUN -1
				sweetexit_f1flag = 12
			ENDIF
		ENDIF
		IF sweetexit_f1flag = 12
			IF LOCATE_CHAR_ANY_MEANS_3D sweet 2193.199 -1179.67 33.56 1.5 1.5 2.5 FALSE
				IF NOT IS_CHAR_DEAD exthelidriver_f1
					enemy_f1 = sweet
					enemytarget_f1 = exthelidriver_f1
					GOSUB stayshootnoduck_f1label
				ENDIF
				TIMERA = 0
				IF NOT IS_CAR_DEAD extpoliceheli_f1
					HELI_GOTO_COORDS extpoliceheli_f1 2193.199 -1179.67 40.5 10.0 10.0	
					IF DOES_SEARCHLIGHT_EXIST helispotlight_f1
						POINT_SEARCHLIGHT_AT_CHAR helispotlight_f1 scplayer 0.2
					ENDIF
				ENDIF
				sweetexit_f1flag = 13
			ENDIF
		ENDIF

		IF sweetexit_f1flag = 13
			IF TIMERA > 10000
				TASK_GO_STRAIGHT_TO_COORD sweet 2200.652 -1188.776 32.563 PEDMOVE_RUN -1
				IF DOES_SEARCHLIGHT_EXIST helispotlight_f1
					POINT_SEARCHLIGHT_AT_CHAR helispotlight_f1 sweet 0.2
				ENDIF
				IF NOT IS_CAR_DEAD extpoliceheli_f1
					SET_CAR_ONLY_DAMAGED_BY_PLAYER extpoliceheli_f1 FALSE
				ENDIF
				sweetexit_f1flag = 14
			ENDIF
		ENDIF
		IF sweetexit_f1flag = 14
			IF LOCATE_CHAR_ANY_MEANS_3D sweet 2200.652 -1188.776 32.563 1.5 1.5 3.5 FALSE
				IF NOT IS_CHAR_DEAD exthelidriver_f1
					enemy_f1 = sweet
					enemytarget_f1 = exthelidriver_f1
					GOSUB stayshootnoduck_f1label
				ENDIF
				TIMERA = 0
				IF NOT IS_CAR_DEAD extpoliceheli_f1
					HELI_GOTO_COORDS extpoliceheli_f1 2212.41 -1168.54 42.0 20.0 20.0 //2205.24 -1170.36
				ENDIF
				sweetexit_f1flag = 15
			ENDIF
		ENDIF

		IF sweetexit_f1flag = 15
			IF TIMERA > 12000
				TASK_GO_STRAIGHT_TO_COORD sweet 2209.474 -1185.644 33.56 PEDMOVE_RUN -1
//				IF NOT IS_CAR_DEAD extpoliceheli_f1
//					SET_CAR_HEALTH extpoliceheli_f1 1500
//				ENDIF
				IF DOES_SEARCHLIGHT_EXIST helispotlight_f1
					POINT_SEARCHLIGHT_AT_CHAR helispotlight_f1 scplayer 0.2
				ENDIF
				sweetexit_f1flag = 16
			ENDIF
		ENDIF
		IF sweetexit_f1flag = 16
			IF LOCATE_CHAR_ANY_MEANS_3D sweet 2209.474 -1185.644 33.56 1.5 1.5 3.5 FALSE
				IF NOT IS_CHAR_DEAD exthelidriver_f1
					enemy_f1 = sweet
					enemytarget_f1 = exthelidriver_f1
					GOSUB stayshootnoduck_f1label
				ENDIF
				TIMERA = 0
				IF NOT IS_CAR_DEAD extpoliceheli_f1
					HELI_GOTO_COORDS extpoliceheli_f1 2223.66 -1169.4 42.83 20.0 20.0 //2209.36 -1199.03 42.66
				ENDIF
				sweetexit_f1flag = 17
			ENDIF
		ENDIF

		IF sweetexit_f1flag = 17
			IF TIMERA > 5000
				TASK_GO_STRAIGHT_TO_COORD sweet 2219.186 -1188.8 33.563 PEDMOVE_RUN -1
				IF DOES_SEARCHLIGHT_EXIST helispotlight_f1
					POINT_SEARCHLIGHT_AT_CHAR helispotlight_f1 sweet 0.2
				ENDIF
				sweetexit_f1flag = 18
			ENDIF
		ENDIF

		IF sweetexit_f1flag = 18
			IF LOCATE_CHAR_ANY_MEANS_3D sweet 2219.186 -1188.8 33.563 1.5 1.5 3.5 FALSE
				IF NOT IS_CHAR_DEAD exthelidriver_f1
					enemy_f1 = sweet
					enemytarget_f1 = exthelidriver_f1
					GOSUB stayshootnoduck_f1label
				ENDIF
				TIMERA = 0
				IF NOT IS_CAR_DEAD extpoliceheli_f1
					HELI_GOTO_COORDS extpoliceheli_f1 2242.91 -1178.87 42.0 10.0 10.0 //2236.59 -1175.8
				ENDIF
				sweetexit_f1flag = 19
			ENDIF
		ENDIF

		//sweet runs to bottom of stairs when heli is shot down
		IF swat1_f1flag = 0
			IF IS_CHAR_DEAD swat1_f1
				DETACH_CHAR_FROM_CAR swat1_f1
				swat1_f1flag = 1
			ENDIF
		ENDIF

		IF swat2_f1flag = 0
			IF IS_CHAR_DEAD swat2_f1
				DETACH_CHAR_FROM_CAR swat2_f1
				swat2_f1flag = 1
			ENDIF
		ENDIF

		IF swat3_f1flag = 0
			IF IS_CHAR_DEAD swat3_f1
				DETACH_CHAR_FROM_CAR swat3_f1
				swat3_f1flag = 1
			ENDIF
		ENDIF

		IF swat4_f1flag = 0
			IF IS_CHAR_DEAD swat4_f1
				DETACH_CHAR_FROM_CAR swat4_f1
				swat4_f1flag = 1
			ENDIF
		ENDIF

		IF sweetexit_f1flag > 10
			IF NOT IS_CAR_DEAD extpoliceheli_f1
				GET_CAR_HEALTH extpoliceheli_f1 extpoliceheli_f1health
					IF extpoliceheli_f1health < 1250
						SET_CHAR_PROOFS scplayer FALSE FALSE TRUE FALSE FALSE
						REMOVE_BLIP heli_f1blip
						HELI_GOTO_COORDS extpoliceheli_f1 2220.44 -1128.29 43.31 30.0 30.0
						IF HAS_MISSION_AUDIO_LOADED 2
							PLAY_MISSION_AUDIO 2 //SOUND_FIN1_CL	//Mayday, mayday! Buzzard 1 is going down!
						ENDIF
						DELETE_SEARCHLIGHT helispotlight_f1
						TIMERA = 0
						helicrash_f1flag = 1
						PRINT_NOW LAF1_69 5000 1//Follow Sweet.
						roofmotel_f1flag = 3
						sweetexit_f1flag = 20
					ENDIF
			ELSE
				PRINT_NOW LAF1_69 1000 1//Follow Sweet.
				DELETE_SEARCHLIGHT helispotlight_f1
				roofmotel_f1flag = 3
				sweetexit_f1flag = 20
			ENDIF
		ENDIF

	ENDIF	

ENDIF

IF roofmotel_f1flag = 3
	IF sweetexit_f1flag < 23

		//display health bar
		IF NOT IS_CHAR_DEAD sweet
			GET_CHAR_HEALTH sweet sweethealth_f1
			sweethealth_f1 = sweethealth_f1 / 5
		ELSE
			PRINT_NOW LAF1_70 5000 1//Sweet died!
			GOTO mission_drugs4_failed //sweet died
		ENDIF

		IF sweetexit_f1flag = 20
			IF NOT IS_CHAR_DEAD sweet
				TASK_FOLLOW_PATH_NODES_TO_COORD sweet 2235.36 -1188.68 33.82 PEDMOVE_RUN 35000
				TIMERB = 0
				progressaudio_f1flag = 0
				handlingaudio_f1flag = 0
				CLEAR_MISSION_AUDIO 1
				CLEAR_AREA 2197.78 -1194.00 30.0 30.0 TRUE
				sweetexit_f1flag = 21
			ENDIF
		ENDIF

		IF sweetexit_f1flag = 21
			IF NOT IS_CHAR_DEAD sweet
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D sweet scplayer 4.0 4.0 3.5 FALSE
					IF IS_CHAR_ON_SCREEN sweet
						SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
						TASK_FOLLOW_PATH_NODES_TO_COORD sweet 2197.78 -1194.00 26.17 PEDMOVE_RUN 35000
						CLEAR_AREA 2197.78 -1194.00 100.0 100.0 FALSE
						sweetexit_f1flag = 22
					ENDIF
				ELSE
					IF TIMERB > 3500
						//follow sweet
						PRINT_NOW LAF1_69 1000 1//Follow Sweet.
						CLEAR_AREA 2197.78 -1194.00 100.0 100.0 FALSE
					ENDIF
				ENDIF
			ENDIF
		ENDIF	

		IF helicrash_f1flag = 1
			IF NOT IS_CAR_DEAD extpoliceheli_f1
				IF LOCATE_CAR_3D extpoliceheli_f1 2220.44 -1128.29 43.31 15.0 15.0 15.0 FALSE
					EXPLODE_CAR extpoliceheli_f1
					EXPLODE_CAR extpoliceheli_f1
					TIMERA = 0
					helicrash_f1flag = 2
				ENDIF
			ENDIF
		ENDIF
		IF helicrash_f1flag = 2
			IF TIMERA > 3000
				MARK_CAR_AS_NO_LONGER_NEEDED extpoliceheli_f1
				DELETE_CAR extpoliceheli_f1
				helicrash_f1flag = 3
			ENDIF
		ENDIF
		
		IF sweetexit_f1flag = 22
			IF NOT IS_CHAR_DEAD sweet
				IF LOCATE_CHAR_ON_FOOT_3D scplayer 2197.78 -1194.00 26.17 5.0 5.0 3.5 FALSE
					DO_FADE 250 FADE_OUT
					SET_PLAYER_CONTROL PLAYER1 OFF
					SET_CHAR_PROOFS sweet TRUE TRUE TRUE TRUE TRUE		
					WHILE GET_FADING_STATUS //added
						WAIT 0				//added
					ENDWHILE				//added

					MARK_CAR_AS_NO_LONGER_NEEDED extpoliceheli_f1
					CLEAR_AREA 2197.78 -1194.00 100.0 100.0 TRUE
					IF NOT IS_CHAR_DEAD sweet
						SET_CHAR_COORDINATES sweet 2197.78 -1194.00 25.0
					ENDIF
					sweetexit_f1flag = 23
				ELSE
					IF LOCATE_CHAR_ON_FOOT_3D sweet 2197.78 -1194.00 26.17 5.0 5.0 3.5 FALSE
						MARK_CAR_AS_NO_LONGER_NEEDED extpoliceheli_f1
						CLEAR_AREA 2197.78 -1194.00 100.0 100.0 TRUE
						DO_FADE 250 FADE_OUT
						SET_PLAYER_CONTROL PLAYER1 OFF
						SET_CHAR_PROOFS sweet TRUE TRUE TRUE TRUE TRUE

						WHILE GET_FADING_STATUS //added
							WAIT 0				//added
						ENDWHILE				//added

						sweetexit_f1flag = 23
					ENDIF
				ENDIF
			ENDIF
		ENDIF					
	
	ENDIF
ENDIF

IF roofmotel_f1flag > 1
	IF roofmotel_f1flag < 4
		IF sweetexit_f1flag < 23

			GOSUB process_audio_f1
			
			//play mission audio
			IF roofmotel_f1flag = 2
				IF NOT IS_CAR_DEAD extpoliceheli_f1

					IF progressaudio_f1flag = 0
						IF handlingaudio_f1flag = 0
							IF sweetexit_f1flag > 11
								audio_label_f1 = SOUND_FIN1_JM	//CJ, dump on that helicopter!
								$input_text_f1 = FIN1_JM	//CJ, dump on that helicopter!
								GOSUB load_audio_f1
							ENDIF
						ENDIF
					ENDIF

					IF progressaudio_f1flag = 1
						IF handlingaudio_f1flag = 0
							audio_label_f1 = SOUND_FIN1_BY //This is Buzzard 1, we are taking ground fire!
							$input_text_f1 = FIN1_BY //This is Buzzard 1, we are taking ground fire!
							GOSUB load_audio_f1
						ENDIF
					ENDIF

					IF progressaudio_f1flag = 2
						IF handlingaudio_f1flag = 0
							IF sweetexit_f1flag > 14
								audio_label_f1 = SOUND_FIN1_JL	//CJ, that chopper’s all over us! Hit it!
								$input_text_f1 = FIN1_JL	//CJ, that chopper’s all over us! Hit it!
								GOSUB load_audio_f1
							ENDIF
						ENDIF
					ENDIF

				ENDIF
			ENDIF

			IF roofmotel_f1flag = 3
				IF sweetexit_f1flag > 21
					IF progressaudio_f1flag = 0
						IF handlingaudio_f1flag = 0
							audio_label_f1 = SOUND_FIN1_JN	//C’mon, CJ, let’s go!
							$input_text_f1 = FIN1_JN //C’mon, CJ, let’s go!
							GOSUB load_audio_f1
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		ENDIF
	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////		Rails Section		////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//VIEW_INTEGER_VARIABLE rails_f1flag rails_f1flag

IF sweetexit_f1flag = 23
	IF rails_f1flag = 0
		IF NOT IS_CHAR_DEAD sweet
			SET_TIME_OF_DAY 03 15
			SET_CURRENT_CHAR_WEAPON sweet WEAPONTYPE_UNARMED
			//mark models as no longer needed
			MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
			MARK_MODEL_AS_NO_LONGER_NEEDED COLT45

			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED 
			SWITCH_WIDESCREEN ON
			CLEAR_AREA 2223.971 -1149.75 200.0 200.0 FALSE
			SET_PLAYER_CONTROL PLAYER1 OFF
			SET_NEAR_CLIP 0.1	//remember to set this back next cut
				
			SET_FIXED_CAMERA_POSITION 2190.3247 -1194.5623 25.4553 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2189.3760 -1194.5400 25.1403 JUMP_CUT

			CLEAR_CHAR_TASKS_IMMEDIATELY sweet
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			SET_CHAR_COORDINATES sweet 2192.65 -1194.78 24.2//2188.2019 -1194.1411 23.4756 
			SET_CHAR_HEADING sweet 78.5405

			SET_CHAR_COORDINATES scplayer 2192.00 -1193.78 24.2 //2189.7297 -1195.3049 23.6239 
			SET_CHAR_HEADING scplayer 85.0874

			//reset any flags that will be used
			extpoliceheli_f1flag = 0
			REMOVE_DECISION_MAKER extmotel_DM
			//request stuff here
			MARK_MODEL_AS_NO_LONGER_NEEDED SWAT
			CLEAR_ONSCREEN_COUNTER sweethealth_f1
			REQUEST_MODEL COPCARLA
			REQUEST_MODEL LAPD1
			REQUEST_MODEL AK47
			REQUEST_MODEL STREAK
			REQUEST_MODEL GREENWOO
			REQUEST_MODEL POLMAV
			REQUEST_ANIMATION GANGS
			REQUEST_ANIMATION CAR_CHAT
					
			LOAD_SPECIAL_CHARACTER 2 SMOKE
			LOAD_SPECIAL_CHARACTER 3 RYDER2

			REQUEST_CAR_RECORDING 330 //sweet car arriving
			REQUEST_CAR_RECORDING 332 //sweet car
			REQUEST_CAR_RECORDING 333 //1st police car
			REQUEST_CAR_RECORDING 334 //2nd police car
			REQUEST_CAR_RECORDING 335 //3rd police car

			REQUEST_CAR_RECORDING 336 //4th police car
			REQUEST_CAR_RECORDING 337 //5th police car
			REQUEST_CAR_RECORDING 338 //7th police car

			LOAD_MISSION_AUDIO 1 SOUND_FIN1_KA	//Ah shit, what now?
			LOAD_MISSION_AUDIO 2 SOUND_FIN1_KB	//It’s Smoke and Ryder!


			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
				OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
				OR NOT HAS_ANIMATION_LOADED GANGS
				OR NOT HAS_ANIMATION_LOADED CAR_CHAT
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED COPCARLA
				OR NOT HAS_MODEL_LOADED LAPD1
				OR NOT HAS_MODEL_LOADED AK47
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED STREAK
				OR NOT HAS_MODEL_LOADED GREENWOO
				OR NOT HAS_MODEL_LOADED POLMAV
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 330
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 332
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 333
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 334
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 335
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 336
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 337
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 338
				OR NOT HAS_MISSION_AUDIO_LOADED 1
				OR NOT HAS_MISSION_AUDIO_LOADED 2
				WAIT 0
			ENDWHILE
			
			DELETE_CAR extpoliceheli_f1
			DELETE_CHAR exthelidriver_f1
			DELETE_CHAR swat1_f1
			DELETE_CHAR swat2_f1
			DELETE_CHAR swat3_f1
			DELETE_CHAR swat4_f1
			CLEAR_AREA 2192.65 -1194.78 100.0 100.0 TRUE

			CREATE_CAR POLMAV 2130.004 -1196.324 28.198 extpoliceheli_f1
			SET_PETROL_TANK_WEAKPOINT extpoliceheli_f1 FALSE
			SET_CAR_HEADING extpoliceheli_f1 336.905
			CREATE_CHAR_INSIDE_CAR extpoliceheli_f1 PEDTYPE_MISSION1 LAPD1 exthelidriver_f1
			SET_CHAR_DECISION_MAKER exthelidriver_f1 motel_DM
			SET_CHAR_SUFFERS_CRITICAL_HITS exthelidriver_f1 FALSE
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER exthelidriver_f1 TRUE
			SET_CAR_ONLY_DAMAGED_BY_PLAYER extpoliceheli_f1 TRUE
			SET_CAR_PROOFS extpoliceheli_f1 FALSE TRUE TRUE FALSE FALSE
			SET_HELI_BLADES_FULL_SPEED extpoliceheli_f1
			SET_CAR_VISIBLE extpoliceheli_f1 FALSE

			CREATE_CAR GREENWOO 2203.498 -1217.624 23.579 sweet_car
			SET_RADIO_CHANNEL RS_MODERN_HIP_HOP
			SET_CAR_CAN_BE_VISIBLY_DAMAGED sweet_car FALSE
			SET_CAR_HEADING sweet_car 90.386
			CREATE_CHAR_INSIDE_CAR sweet_car PEDTYPE_SPECIAL SPECIAL02 big_smoke
			SET_CHAR_DECISION_MAKER big_smoke motel_DM
			CREATE_CHAR_AS_PASSENGER sweet_car PEDTYPE_SPECIAL SPECIAL03 0 ryder
			SET_CHAR_DECISION_MAKER ryder motel_DM
			CHANGE_CAR_COLOUR sweet_car 59 34
			SET_CAR_PROOFS sweet_car FALSE TRUE TRUE TRUE TRUE
			SET_CAR_HEALTH sweet_car 6000
			SET_CAN_BURST_CAR_TYRES sweet_car FALSE 

			CREATE_CAR COPCARLA 2169.1943 -1270.0591 22.8203 policecar1_f1	//1st car chasing player
			SET_PETROL_TANK_WEAKPOINT policecar1_f1 FALSE
			SET_CAR_HEADING policecar1_f1 357.0326
			SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar1_f1 TRUE
			SET_CAR_HEALTH policecar1_f1 750
			CREATE_CHAR_INSIDE_CAR policecar1_f1 PEDTYPE_MISSION1 LAPD1 cop1_f1
			SET_CHAR_DECISION_MAKER cop1_f1 motel_DM
			SET_CHAR_HEALTH cop1_f1 100
			SET_CHAR_SUFFERS_CRITICAL_HITS cop1_f1 FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop1_f1 TRUE
			CREATE_CHAR_AS_PASSENGER policecar1_f1 PEDTYPE_MISSION1 LAPD1 0 cop2_f1
			SET_CHAR_DECISION_MAKER cop2_f1 motel_dm
			SET_CHAR_HEALTH cop2_f1 200
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop2_f1 TRUE
			GIVE_WEAPON_TO_CHAR cop2_f1 WEAPONTYPE_MP5 99999
			SET_CHAR_SUFFERS_CRITICAL_HITS cop2_f1 FALSE
			CAR_SET_IDLE policecar1_f1
			SET_CAR_VISIBLE policecar1_f1 FALSE

			CREATE_CAR COPCARLA 2172.5264 -1280.7343 22.9766 policecar2_f1	//2nd car chasing player
			SET_PETROL_TANK_WEAKPOINT policecar2_f1 FALSE
			SET_CAR_HEADING policecar2_f1 0.9448
			SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar2_f1 TRUE
			SET_CAR_HEALTH policecar2_f1 500
			CREATE_CHAR_INSIDE_CAR policecar2_f1 PEDTYPE_MISSION1 LAPD1 cop3_f1
			SET_CAR_PROOFS policecar2_f1 FALSE TRUE TRUE TRUE TRUE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop3_f1 TRUE
			SET_CHAR_HEALTH cop3_f1 100
			SET_CHAR_DECISION_MAKER cop3_f1 motel_DM
			CREATE_CHAR_AS_PASSENGER policecar2_f1 PEDTYPE_MISSION1 LAPD1 0 cop4_f1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop4_f1 TRUE
			SET_CHAR_DECISION_MAKER cop4_f1 motel_dm
			SET_CHAR_HEALTH cop4_f1 200
			SET_CHAR_SUFFERS_CRITICAL_HITS cop4_f1 FALSE
			GIVE_WEAPON_TO_CHAR cop4_f1 WEAPONTYPE_MP5 99999
			CAR_SET_IDLE policecar2_f1
			SET_CAR_VISIBLE policecar2_f1 FALSE

			CREATE_CAR COPCARLA 2223.11 -1150.118 24.92 policecar3_f1	//3rd, hits train
			SET_PETROL_TANK_WEAKPOINT policecar3_f1 FALSE
			CREATE_CHAR_INSIDE_CAR policecar3_f1 PEDTYPE_MISSION1 LAPD1 cop5_f1
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cop5_f1 FALSE
			SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar3_f1 TRUE
			SET_CAR_HEALTH policecar3_f1 1000
			SET_CHAR_DECISION_MAKER cop5_f1 motel_DM
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop5_f1 TRUE
			SET_CHAR_SUFFERS_CRITICAL_HITS cop5_f1 FALSE
			CREATE_CHAR_AS_PASSENGER policecar3_f1 PEDTYPE_MISSION1 LAPD1 0 cop6_f1
			SET_CHAR_DECISION_MAKER cop6_f1 motel_dm
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop6_f1 TRUE
			SET_CHAR_HEALTH cop6_f1 200
			SET_CHAR_SUFFERS_CRITICAL_HITS cop6_f1 FALSE
			GIVE_WEAPON_TO_CHAR cop6_f1 WEAPONTYPE_MP5 99999
			CAR_SET_IDLE policecar3_f1

			CREATE_CAR COPCARLA 2388.808 -1259.431 22.94 policecar4_f1	//waiting outside garage
			SET_CAR_HEADING policecar4_f1 89.36
			SET_PETROL_TANK_WEAKPOINT policecar4_f1 FALSE
			SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar4_f1 TRUE
			SET_CAR_HEALTH policecar4_f1 900
			CREATE_CHAR_INSIDE_CAR policecar4_f1 PEDTYPE_MISSION1 LAPD1 cop7_f1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop7_f1 TRUE
			SET_CHAR_DECISION_MAKER cop7_f1 motel_DM
			SET_CHAR_SUFFERS_CRITICAL_HITS cop7_f1 FALSE
			SET_CHAR_HEALTH cop7_f1 100
			CREATE_CHAR_AS_PASSENGER policecar4_f1 PEDTYPE_MISSION1 LAPD1 0 cop8_f1
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop8_f1 TRUE
			SET_CHAR_HEALTH cop8_f1 150
			SET_CHAR_DECISION_MAKER cop8_f1 motel_dm
			GIVE_WEAPON_TO_CHAR cop8_f1 WEAPONTYPE_MP5 9999
			SET_CHAR_SUFFERS_CRITICAL_HITS cop8_f1 FALSE
			CAR_SET_IDLE policecar4_f1

			SWITCH_RANDOM_TRAINS OFF
			DELETE_ALL_TRAINS
			CREATE_MISSION_TRAIN 15 2284.991 -1186.053 24.756 FALSE train_f1
			SET_TRAIN_SPEED train_f1 0.0
			SET_TRAIN_CRUISE_SPEED train_f1 0.0
			SET_CAR_PROOFS train_f1 TRUE TRUE TRUE TRUE TRUE

			TASK_GO_STRAIGHT_TO_COORD scplayer 2183.5667 -1193.8510 23.2419 PEDMOVE_RUN 5000
			IF NOT IS_CHAR_DEAD sweet
				TASK_GO_STRAIGHT_TO_COORD sweet 2183.5315 -1195.0739 23.2266 PEDMOVE_RUN 5000
			ENDIF

			DO_FADE 250 FADE_IN

			rails_f1flag = 1
		ENDIF
	ENDIF
ENDIF

//camera looking down stairs, player and sweet run past it
IF rails_f1flag = 1
	IF NOT IS_CHAR_DEAD sweet
		IF LOCATE_CHAR_ANY_MEANS_2D sweet 2183.5315 -1195.0739 1.5 1.5 FALSE
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2183.5667 -1193.8510 2.0 2.0 FALSE
				SET_FIXED_CAMERA_POSITION 2183.6172 -1193.0881 24.8301 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2183.5400 -1194.0851 24.8217 JUMP_CUT
				TIMERA = 0
				rails_f1flag = 2
			ENDIF
		ENDIF
	ENDIF
ENDIF

//sweet and player now down the stairs looking at sweet's car
IF rails_f1flag = 2
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_DEAD sweet
				IF TIMERA > 1500
					TASK_LOOK_AT_CHAR scplayer big_smoke 3000
					START_PLAYBACK_RECORDED_CAR sweet_car 330
					TASK_PLAY_ANIM sweet prtial_gngtlkE GANGS 1000.0 FALSE TRUE TRUE TRUE -1
					PLAY_MISSION_AUDIO 1 //Ah shit, what now?
					PRINT_NOW FIN1_KA 2500 1 //Ah shit, what now?
					START_CHAR_FACIAL_TALK sweet 2000
					TIMERA = 0
					rails_f1flag = 3
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF rails_f1flag = 3
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_DEAD sweet
				IF TIMERA > 500
					TASK_PLAY_ANIM scplayer prtial_gngtlkH GANGS 4.0 FALSE TRUE TRUE FALSE -1
					rails_f1flag = 4
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF rails_f1flag = 4
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_DEAD sweet
				IF HAS_MISSION_AUDIO_FINISHED 1
					TASK_LOOK_AT_CHAR sweet big_smoke 5000
					CLEAR_PRINTS
					PLAY_MISSION_AUDIO 2 //FIN1_KB	It’s Smoke and Ryder!
					START_CHAR_FACIAL_TALK scplayer 2000
					PRINT_NOW FIN1_KB 3000 1
					STOP_CHAR_FACIAL_TALK sweet
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO 1 SOUND_FIN1_KC //Get in!
					TASK_GO_STRAIGHT_TO_COORD scplayer 2181.53 -1193.81 23.67 PEDMOVE_WALK 5000 			
					TASK_GO_STRAIGHT_TO_COORD sweet 2181.41 -1195.67 23.74 PEDMOVE_WALK 5000
					SET_FIXED_CAMERA_POSITION 2177.9189 -1190.9521 24.4917 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2178.3723 -1191.8152 24.2692 JUMP_CUT
					rails_f1flag = 5
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF rails_f1flag = 5
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD sweet
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	sweet_car
						IF HAS_MISSION_AUDIO_LOADED 1
							IF HAS_MISSION_AUDIO_FINISHED 2
								STOP_CHAR_FACIAL_TALK scplayer
								CLEAR_MISSION_AUDIO 2
								CLEAR_PRINTS
								LOAD_MISSION_AUDIO 2 SOUND_FIN1_KE //Hit the gas!
								PLAY_MISSION_AUDIO 1 //FIN1_KC //Get in!
								START_CHAR_FACIAL_TALK big_smoke 1000
								PRINT_NOW FIN1_KC 2000 1//Get in!
								SET_FIXED_CAMERA_POSITION 2177.9189 -1190.9521 24.4917 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 2178.3723 -1191.8152 24.2692 JUMP_CUT
								TASK_LOOK_AT_CHAR scplayer big_smoke 5000
								CAR_SET_IDLE sweet_car

								TASK_PLAY_ANIM big_smoke CAR_Sc4_FL CAR_CHAT 1000.0 FALSE FALSE FALSE FALSE -1
								TASK_PLAY_ANIM ryder CAR_Sc4_FR CAR_CHAT 1000.0 FALSE FALSE FALSE FALSE -1

								TASK_ENTER_CAR_AS_PASSENGER scplayer sweet_car 5000 2
								TASK_GO_STRAIGHT_TO_COORD sweet 2181.51 -1201.97 24.0 PEDMOVE_RUN 5000

								IF NOT IS_CAR_DEAD policecar1_f1
									SWITCH_CAR_SIREN policecar1_f1 ON
								ENDIF

								IF NOT IS_CAR_DEAD policecar2_f1
									SWITCH_CAR_SIREN policecar2_f1 ON								
								ENDIF

								TIMERA = 0
								rails_f1flag = 6

							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF		
	ENDIF
ENDIF

IF rails_f1flag = 6
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD sweet
					IF TIMERA > 2000
						SET_CHAR_COORDINATES sweet 2176.78 -1196.64 23.18
						TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 6000 1
						STOP_CHAR_FACIAL_TALK big_smoke 
						POP_CAR_PANEL sweet_car WINDSCREEN_PANEL FALSE
						SET_FIXED_CAMERA_POSITION 2178.8811 -1194.1964 24.3172 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2178.8660 -1195.1960 24.2959 JUMP_CUT
						rails_f1flag = 7
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF rails_f1flag = 7
	IF NOT IS_CHAR_DEAD scplayer
		IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
			TASK_PLAY_ANIM scplayer CAR_Sc4_BR CAR_CHAT 6.0 FALSE FALSE FALSE FALSE -1
			SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED 
			rails_f1flag = 8
		ENDIF
	ENDIF
ENDIF


IF rails_f1flag = 8
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD sweet
					IF HAS_MISSION_AUDIO_FINISHED 1
						IF HAS_MISSION_AUDIO_LOADED 2
							IF IS_CHAR_SITTING_IN_CAR sweet	sweet_car

								TASK_PLAY_ANIM sweet CAR_Sc4_BL CAR_CHAT 8.0 FALSE FALSE FALSE FALSE -1
								PLAY_MISSION_AUDIO 2 //SOUND_FIN1_KE //Hit the gas!
								START_CHAR_FACIAL_TALK sweet 1000
								PRINT_NOW FIN1_KE 2000 1

								CLEAR_MISSION_AUDIO 1
								LOAD_MISSION_AUDIO 1 SOUND_FIN1_KF	//(for on rails section) Eh man, I’m running low. 

								//heli follows player
								IF NOT IS_CAR_DEAD extpoliceheli_f1
									SET_CAR_VISIBLE extpoliceheli_f1 TRUE
									HELI_FOLLOW_ENTITY extpoliceheli_f1 scplayer -1 20.0
								ENDIF

								//start playing two recorded chasing police cars
								IF NOT IS_CAR_DEAD policecar1_f1
									SET_CAR_VISIBLE policecar1_f1 TRUE
									START_PLAYBACK_RECORDED_CAR policecar1_f1 333
									ADD_BLIP_FOR_CAR policecar1_f1 policecar1_f1blip
									CHANGE_BLIP_DISPLAY policecar1_f1blip BLIP_ONLY
									SET_CAR_HEALTH policecar1_f1 1000
									policecar1_f1flag = 1 //car started
									policecar1swap_f1flag = 1
								ENDIF

								IF NOT IS_CAR_DEAD policecar2_f1
									SET_CAR_VISIBLE policecar2_f1 TRUE
									START_PLAYBACK_RECORDED_CAR policecar2_f1 334
									ADD_BLIP_FOR_CAR policecar2_f1 policecar2_f1blip
									CHANGE_BLIP_DISPLAY policecar2_f1blip BLIP_ONLY
									policecar2_f1flag = 1 //car started
									policecar2swap_f1flag = 1
								ENDIF
								
								TIMERA = 0
								TIMERB = 0
								rails_f1flag = 9
							ELSE
								IF TIMERA > 5000
									WARP_CHAR_INTO_CAR_AS_PASSENGER sweet sweet_car 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF rails_f1flag = 9
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD sweet
					IF TIMERA > 500
						TIMERA = 0
						rails_f1flag = 10
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF rails_f1flag = 10
	IF NOT IS_CAR_DEAD sweet_car
		IF TIMERB > 2800
			START_PLAYBACK_RECORDED_CAR sweet_car 332
			IF NOT IS_CAR_DEAD extpoliceheli_f1
				CREATE_SEARCHLIGHT_ON_VEHICLE extpoliceheli_f1 0.0 1.0 -0.5 2193.73 -1166.85 34.06 3.0 0.5 helispotlight_f1
				POINT_SEARCHLIGHT_AT_CHAR helispotlight_f1 scplayer 0.8
			ENDIF
			TIMERB = 0
			rails_f1flag = 11
		ENDIF
	ENDIF
ENDIF

IF rails_f1flag = 11
	IF NOT IS_CAR_DEAD sweet_car
		IF TIMERB > 3500
			SET_FIXED_CAMERA_POSITION 2178.9871 -1140.6434 25.9020 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2178.9512 -1141.6425 25.8765 JUMP_CUT
			textrails_f1flag = 1
			progressaudio_f1flag = 0
			handlingaudio_f1flag = 0
			TIMERB = 0
			rails_f1flag = 12
		ENDIF
	ENDIF
ENDIF

IF rails_f1flag = 12
	IF NOT IS_CAR_DEAD sweet_car
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2178.9871 -1140.6434 1.3 1.3 FALSE
		OR TIMERB > 2250 //2500
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 2178.7998 -1161.1873 10.0
			ATTACH_CHAR_TO_CAR scplayer sweet_car 0.0 -0.5 0.8 FACING_BACK 360.0 WEAPONTYPE_AK47
			SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 90.0 180.0 
			RESTORE_CAMERA
			SET_CHAR_PROOFS scplayer FALSE TRUE TRUE FALSE FALSE
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			TIMERA = 0
			TIMERB = 0
			DELETE_CHAR sweet
			DELETE_CHAR big_smoke
			DELETE_CHAR ryder
			REMOVE_ANIMATION GANGS
			REMOVE_ANIMATION CAR_CHAT
			UNLOAD_SPECIAL_CHARACTER 1
			UNLOAD_SPECIAL_CHARACTER 2
			UNLOAD_SPECIAL_CHARACTER 3

			difficulty_f1flag++

			IF NOT IS_CHAR_DEAD cop2_f1
				IF difficulty_f1flag > 1
					TASK_DRIVE_BY cop2_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 40
				ELSE
					TASK_DRIVE_BY cop2_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 75 //60
				ENDIF
			ENDIF
			
			IF NOT IS_CHAR_DEAD cop4_f1
				TASK_DRIVE_BY cop4_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
			ENDIF
			SET_CAR_HEALTH sweet_car 6000 //5000
			GET_CAR_HEALTH sweet_car carhealth_f1
			carhealth_f1 = carhealth_f1 / 60 
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING carhealth_f1 COUNTER_DISPLAY_BAR LAF1_38
			SET_PLAYER_DUCK_BUTTON PLAYER1 FALSE
			//SET_PLAYER_FAST_RELOAD PLAYER1 TRUE

			PRINT_HELP LAF1_50 

			SET_CAR_PROOFS sweet_car FALSE TRUE TRUE TRUE TRUE
			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
			helpshoottext_f1flag = 1
			chasetext_f1flag = 1
			motelchase_f1flag = 1
			copcars_f1flag = 1
			rails_f1flag = 13
		ENDIF
	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////	Chase begins	////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF motelchase_f1flag > 0
	IF helisetpiece_f1flag < 5
		IF NOT IS_CAR_DEAD sweet_car
			GET_CAR_HEALTH sweet_car carhealth_f1
			carhealth_f1 = carhealth_f1 - 1000

			IF carhealth_f1 <= 0 
				carhealth_f1 = 0
				SET_CAR_PROOFS sweet_car FALSE FALSE FALSE FALSE FALSE
				SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
				EXPLODE_CAR sweet_car
				PRINT_NOW LAF1_4 5000 1 //~r~You wrecked the car!
				GOTO mission_drugs4_failed
			ELSE
				carhealth_f1 = carhealth_f1 / 50
			ENDIF

		ENDIF
	ENDIF
ENDIF



IF motelchase_f1flag = 1

	//help text
	IF helpshoottext_f1flag = 1
		IF IS_HELP_MESSAGE_BEING_DISPLAYED
			helpshoottext_f1flag = 2
		ENDIF
	ENDIF
	IF helpshoottext_f1flag = 2
		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			PRINT_HELP LAF1_51
		    helpshoottext_f1flag = 3
		ENDIF
	ENDIF
	
	//car going into train
	IF rails_f1flag = 13
		IF NOT IS_CAR_DEAD sweet_car
			IF LOCATE_CAR_2D sweet_car 2261.95 -1142.2 5.0 5.0 FALSE  //5.5 5.5
				IF NOT IS_CAR_DEAD train_f1
					SET_TRAIN_SPEED train_f1 10.0
					SET_TRAIN_CRUISE_SPEED train_f1 10.0		
					rails_f1flag = 14
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF rails_f1flag = 14
		IF NOT IS_CAR_DEAD policecar3_f1
			IF LOCATE_CAR_2D policecar3_f1 2284.98 -1146.61 3.0 3.0 FALSE
				TIMERB = 0
				actiontext_f1flag = 1
				SET_CAR_HEALTH policecar3_f1 750
				rails_f1flag = 15
			ENDIF
		ENDIF
	ENDIF

	IF rails_f1flag = 15
		IF NOT IS_CAR_DEAD policecar3_f1
			IF TIMERB > 1100
				EXPLODE_CAR policecar3_f1
				rails_f1flag = 16
			ENDIF
		ENDIF
	ENDIF

	//car starts that goes into train
	IF copcars_f1flag = 1
		IF NOT IS_CAR_DEAD sweet_car
			IF LOCATE_CAR_2D sweet_car 2223.11 -1150.118 20.0 20.0 FALSE
				IF NOT IS_CAR_DEAD policecar3_f1
					START_PLAYBACK_RECORDED_CAR policecar3_f1 335
					ADD_BLIP_FOR_CAR policecar3_f1 policecar3_f1blip
					CHANGE_BLIP_DISPLAY policecar3_f1blip BLIP_ONLY
					policecar3swap_f1flag = 1
					policecar3_f1flag = 1	//car started
					IF NOT IS_CHAR_DEAD cop6_f1
						TASK_DRIVE_BY cop6_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 40
					ENDIF
				ENDIF
				copcars_f1flag = 2
			ENDIF 
		ENDIF
	ENDIF

	//waiting for the player outside the garage
	IF copcars_f1flag = 2
		IF NOT IS_CAR_DEAD sweet_car
		 	IF LOCATE_CAR_2D sweet_car 2352.54 -1271.86 10.1 10.1 FALSE
				IF NOT IS_CAR_DEAD policecar4_f1
					START_PLAYBACK_RECORDED_CAR policecar4_f1 336
					ADD_BLIP_FOR_CAR policecar4_f1 policecar4_f1blip
					CHANGE_BLIP_DISPLAY policecar4_f1blip BLIP_ONLY
					policecar4_f1flag = 1	//car started
					policecar4swap_f1flag = 1
					IF NOT IS_CHAR_DEAD cop8_f1
						TASK_DRIVE_BY cop8_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 75
					ENDIF
					DELETE_MISSION_TRAIN train_f1
					DELETE_MISSION_TRAINS
					MARK_MODEL_AS_NO_LONGER_NEEDED STREAK

					CREATE_CAR COPCARLA 2500.697 -1443.776 28.28 policecar5_f1	//comes in front of the player smacks into players bumper
					SET_PETROL_TANK_WEAKPOINT policecar5_f1 FALSE
					SET_CAR_HEADING policecar5_f1 90.4588
					SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar5_f1 TRUE
					SET_CAR_HEALTH policecar5_f1 750
					CREATE_CHAR_INSIDE_CAR policecar5_f1 PEDTYPE_MISSION1 LAPD1 cop9_f1
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop9_f1 TRUE
					SET_CHAR_DECISION_MAKER cop9_f1 motel_DM
					SET_CHAR_SUFFERS_CRITICAL_HITS cop9_f1 FALSE
					SET_CHAR_HEALTH cop9_f1 200
					CREATE_CHAR_AS_PASSENGER policecar5_f1 PEDTYPE_MISSION1 LAPD1 0 cop10_f1
					SET_CHAR_DECISION_MAKER cop10_f1 motel_dm
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop10_f1 TRUE
					SET_CHAR_HEALTH cop10_f1 100
					GIVE_WEAPON_TO_CHAR cop10_f1 WEAPONTYPE_MP5 9999
					SET_CHAR_SUFFERS_CRITICAL_HITS cop10_f1 FALSE
					CAR_SET_IDLE policecar5_f1

					CREATE_CAR COPCARLA 2478.223 -1321.966 28.252 policecar7_f1	//comes out of side	
					SET_CAR_HEADING policecar7_f1 356.585
					SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar7_f1 TRUE
					SET_CAR_HEALTH policecar7_f1 280
					CAR_SET_IDLE policecar7_f1
					CREATE_CHAR_INSIDE_CAR policecar7_f1 PEDTYPE_MISSION1 LAPD1 cop11_f1
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop11_f1 TRUE
					SET_CHAR_DECISION_MAKER cop11_f1 motel_DM
					CREATE_CHAR_AS_PASSENGER policecar7_f1 PEDTYPE_MISSION1 LAPD1 0 cop12_f1
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop12_f1 TRUE
					SET_CHAR_DECISION_MAKER cop12_f1 motel_dm
					SET_CHAR_SUFFERS_CRITICAL_HITS cop12_f1 FALSE
					GIVE_WEAPON_TO_CHAR cop12_f1 WEAPONTYPE_MP5 9999
					CAR_SET_IDLE policecar7_f1
				ENDIF
				copcars_f1flag = 3
			ENDIF		
		ENDIF
	ENDIF

	//infront of player 
	IF copcars_f1flag = 3
		IF NOT IS_CAR_DEAD sweet_car
		 	IF LOCATE_CAR_2D sweet_car 2394.67 -1442.43 10.1 10.1 FALSE
				IF NOT IS_CAR_DEAD policecar5_f1
					policecar5_f1flag = 1	//car started
					policecar5swap_f1flag = 1
					START_PLAYBACK_RECORDED_CAR policecar5_f1 337
					ADD_BLIP_FOR_CAR policecar5_f1 policecar5_f1blip
					CHANGE_BLIP_DISPLAY policecar5_f1blip BLIP_ONLY
					SWITCH_CAR_SIREN policecar5_f1 ON
					IF NOT IS_CHAR_DEAD cop10_f1
						IF difficulty_f1flag > 1
							TASK_DRIVE_BY cop10_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 30
						ELSE
							TASK_DRIVE_BY cop10_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 75
						ENDIF
					ENDIF

					DELETE_ALL_TRAINS

					CREATE_CAR COPCARLA 2447.036 -1370.772 22.64 policecar6_f1 //stationary
					SET_CAR_HEADING policecar6_f1 270.267
					CAR_SET_IDLE policecar6_f1
					SET_CAR_HEALTH policecar6_f1 250
					SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar6_f1 TRUE
					ADD_BLIP_FOR_CAR policecar6_f1 policecar6_f1blip
					CHANGE_BLIP_DISPLAY policecar6_f1blip BLIP_ONLY

					CREATE_CHAR PEDTYPE_MISSION1 LAPD1 2448.77 -1368.898 23.60 cop13_f1
					SET_CHAR_DECISION_MAKER cop13_f1 motel_DM
					GIVE_WEAPON_TO_CHAR cop13_f1 WEAPONTYPE_MP5 999
					enemy_f1 = cop13_f1
					enemytarget_f1 = scplayer
					GOSUB stayshootnoduck_f1label

					CREATE_CHAR PEDTYPE_MISSION1 LAPD1 2444.387 -1369.356 24.02 cop14_f1
					SET_CHAR_DECISION_MAKER cop14_f1 motel_DM
					GIVE_WEAPON_TO_CHAR cop14_f1 WEAPONTYPE_MP5 999
					enemy_f1 = cop14_f1
					enemytarget_f1 = scplayer
					GOSUB stayshootnoduck_f1label
					policecar6_f1flag = 1 //car started
				ENDIF
				copcars_f1flag = 4
			ENDIF		
		ENDIF
	ENDIF

	//suprise car out of alley
	IF copcars_f1flag = 4
		IF NOT IS_CAR_DEAD sweet_car
			IF LOCATE_CAR_2D sweet_car 2448.94 -1311.15 30.1 30.1 FALSE
				IF NOT IS_CAR_DEAD policecar7_f1
					START_PLAYBACK_RECORDED_CAR policecar7_f1 338
					SWITCH_CAR_SIREN policecar7_f1 ON
					policecar7_f1flag = 1// car started
					policecar7swap_f1flag = 1
					IF NOT IS_CHAR_DEAD cop11_f1
						TASK_DRIVE_BY cop11_f1 -1 sweet_car 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 60
					ENDIF
					ADD_BLIP_FOR_CAR policecar7_f1 policecar7_f1blip
					CHANGE_BLIP_DISPLAY policecar7_f1blip BLIP_ONLY
				ENDIF
				copcars_f1flag = 5
			ENDIF
		ENDIF
	ENDIF

	////////////////////////////////////////////if cars are dead mark as no longer needed

	//swap seat
	IF policecar1swap_f1flag = 1
		IF NOT IS_CAR_DEAD policecar1_f1
			IF NOT IS_CHAR_DEAD cop1_f1
			ELSE
				IF NOT IS_CHAR_DEAD cop2_f1
					IF IS_CHAR_IN_CAR cop2_f1 policecar1_f1
						GET_DRIVER_OF_CAR policecar1_f1 driverofcar_f1
						IF driverofcar_f1 = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop2_f1 policecar1_f1
							policecar1swap_f1flag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR policecar1_f1
					policecar1swap_f1flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF policecar1swap_f1flag = 2
		IF IS_CHAR_DEAD cop2_f1
			STOP_PLAYBACK_RECORDED_CAR policecar1_f1
			policecar1swap_f1flag = 3
		ENDIF 
	ENDIF


	IF policecar1_f1flag = 1
		IF NOT IS_CAR_DEAD policecar1_f1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar1_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar1_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop1_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop2_f1
				REMOVE_BLIP policecar1_f1blip 
				policecar1_f1flag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR policecar1_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop1_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop2_f1
			MARK_CAR_AS_NO_LONGER_NEEDED policecar1_f1
			REMOVE_BLIP policecar1_f1blip 
			policecar1_f1flag = 2
		ENDIF

	ENDIF

	//swap seat
	IF policecar2swap_f1flag = 1
		IF NOT IS_CAR_DEAD policecar2_f1
			IF NOT IS_CHAR_DEAD cop3_f1
			ELSE
				IF NOT IS_CHAR_DEAD cop4_f1
					IF IS_CHAR_IN_CAR cop4_f1 policecar2_f1
						GET_DRIVER_OF_CAR policecar2_f1 driverofcar_f1
						IF driverofcar_f1 = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop4_f1 policecar2_f1
							policecar2swap_f1flag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR policecar2_f1
					policecar2swap_f1flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF policecar2swap_f1flag = 2
		IF IS_CHAR_DEAD cop4_f1
			STOP_PLAYBACK_RECORDED_CAR policecar2_f1
			policecar2swap_f1flag = 3
		ENDIF 
	ENDIF

	IF policecar2_f1flag = 1
		IF NOT IS_CAR_DEAD policecar2_f1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar2_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar2_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop3_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop4_f1
				REMOVE_BLIP policecar2_f1blip 
				policecar2_f1flag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR policecar2_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop3_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop4_f1
			MARK_CAR_AS_NO_LONGER_NEEDED policecar2_f1
			REMOVE_BLIP policecar2_f1blip 
			policecar2_f1flag = 2
		ENDIF
	ENDIF

	IF policecar3_f1flag = 1
		IF NOT IS_CAR_DEAD policecar3_f1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar3_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar3_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop5_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop6_f1
				REMOVE_BLIP policecar3_f1blip 
				MARK_CAR_AS_NO_LONGER_NEEDED train_f1
				MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
				policecar3_f1flag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR policecar3_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop5_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop6_f1
			MARK_CAR_AS_NO_LONGER_NEEDED policecar3_f1
			MARK_CAR_AS_NO_LONGER_NEEDED train_f1
			MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
			REMOVE_BLIP policecar3_f1blip 
			policecar3_f1flag = 2
		ENDIF
	ENDIF


	//swap seat
	IF policecar4swap_f1flag = 1
		IF NOT IS_CAR_DEAD policecar4_f1
			IF NOT IS_CHAR_DEAD cop7_f1
			ELSE
				IF NOT IS_CHAR_DEAD cop8_f1
					IF IS_CHAR_IN_CAR cop8_f1 policecar4_f1
						GET_DRIVER_OF_CAR policecar4_f1 driverofcar_f1
						IF driverofcar_f1 = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop8_f1 policecar4_f1
							policecar4swap_f1flag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR policecar4_f1
					policecar4swap_f1flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF policecar4swap_f1flag = 2
		IF IS_CHAR_DEAD cop8_f1
			STOP_PLAYBACK_RECORDED_CAR policecar4_f1
			policecar4swap_f1flag = 3
		ENDIF 
	ENDIF

	IF policecar4_f1flag = 1
		IF NOT IS_CAR_DEAD policecar4_f1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar4_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar4_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop7_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop8_f1
				REMOVE_BLIP policecar4_f1blip 
				policecar4_f1flag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR policecar4_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop7_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop8_f1
			MARK_CAR_AS_NO_LONGER_NEEDED policecar4_f1
			REMOVE_BLIP policecar4_f1blip 
			policecar4_f1flag = 2
		ENDIF
	ENDIF


	//swap seat
	IF policecar5swap_f1flag = 1
		IF NOT IS_CAR_DEAD policecar5_f1
			IF NOT IS_CHAR_DEAD cop9_f1
			ELSE
				IF NOT IS_CHAR_DEAD cop10_f1
					IF IS_CHAR_IN_CAR cop10_f1 policecar5_f1
						GET_DRIVER_OF_CAR policecar5_f1 driverofcar_f1
						IF driverofcar_f1 = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop10_f1 policecar5_f1
							policecar5swap_f1flag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR policecar5_f1
					policecar5swap_f1flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF policecar5swap_f1flag = 2
		IF IS_CHAR_DEAD cop10_f1
			STOP_PLAYBACK_RECORDED_CAR policecar5_f1
			policecar5swap_f1flag = 3
		ENDIF 
	ENDIF

	IF policecar5_f1flag = 1
		IF NOT IS_CAR_DEAD policecar5_f1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar5_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar5_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop9_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop10_f1
				REMOVE_BLIP policecar5_f1blip 
				policecar5_f1flag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR policecar5_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop9_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop10_f1
			MARK_CAR_AS_NO_LONGER_NEEDED policecar5_f1
			REMOVE_BLIP policecar5_f1blip 
			policecar5_f1flag = 2
		ENDIF
	ENDIF

	IF policecar6_f1flag = 1
		IF IS_CAR_DEAD policecar6_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop13_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop14_f1
			MARK_CAR_AS_NO_LONGER_NEEDED policecar6_f1
			REMOVE_BLIP policecar6_f1blip
			policecar6_f1flag = 2
		ENDIF
	ENDIF

	IF policecar7swap_f1flag = 1
		IF NOT IS_CAR_DEAD policecar7_f1
			IF NOT IS_CHAR_DEAD cop11_f1
			ELSE
				IF NOT IS_CHAR_DEAD cop12_f1
					IF IS_CHAR_IN_CAR cop12_f1 policecar7_f1
						GET_DRIVER_OF_CAR policecar7_f1 driverofcar_f1
						IF driverofcar_f1 = -1
							TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop12_f1 policecar7_f1
							policecar7swap_f1flag = 2
						ENDIF
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR policecar7_f1
					policecar7swap_f1flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF policecar7swap_f1flag = 2
		IF IS_CHAR_DEAD cop12_f1
			STOP_PLAYBACK_RECORDED_CAR policecar7_f1
			policecar7swap_f1flag = 3
		ENDIF 
	ENDIF


	IF policecar7_f1flag = 1
		IF NOT IS_CAR_DEAD policecar7_f1
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar7_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar7_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop11_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop12_f1
				REMOVE_BLIP policecar7_f1blip 
				policecar7_f1flag = 2
			ENDIF
		ELSE
			STOP_PLAYBACK_RECORDED_CAR policecar7_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop11_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED cop12_f1
			MARK_CAR_AS_NO_LONGER_NEEDED policecar7_f1
			REMOVE_BLIP policecar7_f1blip 
			policecar7_f1flag = 2
		ENDIF
	ENDIF

	IF extpoliceheli_f1flag = 0
		IF IS_CAR_DEAD extpoliceheli_f1
			MARK_CAR_AS_NO_LONGER_NEEDED extpoliceheli_f1
			MARK_CHAR_AS_NO_LONGER_NEEDED exthelidriver_f1
			DELETE_SEARCHLIGHT helispotlight_f1
			extpoliceheli_f1flag = 1
		ENDIF
	ENDIF

	//motor bike cut scene 
	IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2555.85 -1257.43.18 5.0 5.0 FALSE
		textrails_f1flag = 2
		DO_FADE 100 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_PLAYER_CONTROL PLAYER1 OFF
		IF NOT IS_CAR_DEAD sweet_car
			SET_CAR_PROOFS sweet_car TRUE TRUE TRUE TRUE TRUE
		ENDIF

		motelchase_f1flag = 2
	ENDIF
	

ENDIF

//dialogue
IF textrails_f1flag = 1

	//dialogue
	GOSUB process_audio_f1

	IF progressaudio_f1flag = 0
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KF	//Eh man, I’m running low.
			$input_text_f1 = FIN1_KF //Eh man, I’m running low.
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 1
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KG	//I got a ‘K here!
			$input_text_f1 = FIN1_KG //I got a ‘K here!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 2
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KH //This is a fucking antique!
			$input_text_f1 = FIN1_KH //This is a fucking antique!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 3
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KI //Yeah, well… Emmet ain’t the Pentagon!
			$input_text_f1 = FIN1_KI //Yeah, well… Emmet ain’t the Pentagon!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 4
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KJ //You got it off Emmet? Shit…
			$input_text_f1 = FIN1_KJ //You got it off Emmet? Shit…
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 5
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_ZC	//CJ, cover the rear!
			$input_text_f1 = FIN1_ZC	//CJ, cover the rear!
			GOSUB load_audio_f1
		ENDIF
	ENDIF

	//car hit by train
	IF progressaudio_f1flag = 6
		IF handlingaudio_f1flag = 0
			IF actiontext_f1flag = 1
				audio_label_f1 = SOUND_FIN1_KM	//Man, they’s got smoked!  Did you see that?
				$input_text_f1 = FIN1_KM	//Man, they’s got smoked!  Did you see that?
				GOSUB load_audio_f1
			ENDIF
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 7
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KL	//Damn! That was close!
			$input_text_f1 = FIN1_KL	//Damn! That was close!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 8
		IF handlingaudio_f1flag = 0
			IF DOES_SEARCHLIGHT_EXIST helispotlight_f1
				SET_SEARCHLIGHT_CLIP_IF_COLLIDING helispotlight_f1 TRUE
			ENDIF
			audio_label_f1 = SOUND_FIN1_KO	//Man, it’s getting intense! It’s getting worse out here!
			$input_text_f1 = FIN1_KO	//Man, it’s getting intense! It’s getting worse out here!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 9
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KP	//It’s like World War VIII!
			$input_text_f1 = FIN1_KP	//It’s like World War VIII!
			GOSUB load_audio_f1
		ENDIF
	ENDIF

	//out of the garage
	IF actiontext_f1flag = 1
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2372.35 -1283.33 15.0 15.0 FALSE
			actiontext_f1flag = 2
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 10
		IF actiontext_f1flag = 2
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_KS //Hey, CJ, watch to the left!
				$input_text_f1 = FIN1_KS //Hey, CJ, watch to the left!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 11
		IF handlingaudio_f1flag = 0
			IF DOES_SEARCHLIGHT_EXIST helispotlight_f1
				SET_SEARCHLIGHT_CLIP_IF_COLLIDING helispotlight_f1 FALSE
			ENDIF
			audio_label_f1 = SOUND_FIN1_KT	//Your left or my left?
			$input_text_f1 = FIN1_KT	//Your left or my left?
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 12
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_KU	//Hell, I don’t know, just shoot everybody, motherfucker!
			$input_text_f1 = FIN1_KU	//Hell, I don’t know, just shoot everybody, motherfucker!
			GOSUB load_audio_f1
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 13
		IF handlingaudio_f1flag = 0
			audio_label_f1 = SOUND_FIN1_CB	//Four bangers in blue 4door heading back into South Central.
			$input_text_f1 = FIN1_CB	//Four bangers in blue 4door heading back into South Central.
			GOSUB load_audio_f1
		ENDIF
	ENDIF

	//car in front
	IF actiontext_f1flag = 2
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2424.42 -1446.57 15.0 15.0 FALSE
			actiontext_f1flag = 3
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 14
		IF actiontext_f1flag = 3
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LD //Trouble up ahead!
				$input_text_f1 = FIN1_LD //Trouble up ahead!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
	ENDIF

	//car behind and to the right side
	IF actiontext_f1flag = 3
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2453.78 -1333.3 10.0 10.0 FALSE
			actiontext_f1flag = 4
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 15
		IF actiontext_f1flag = 3
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_ZD	//Carl, they all over our ass, baby!
				$input_text_f1 = FIN1_ZD	//Carl, they all over our ass, baby!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
	ENDIF

	IF actiontext_f1flag = 4
		IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2454.1 -1313.02 10.0 10.0 FALSE
			actiontext_f1flag = 5
		ENDIF
	ENDIF
	IF progressaudio_f1flag = 16
		IF handlingaudio_f1flag = 0
			IF actiontext_f1flag = 5
				audio_label_f1 = SOUND_FIN1_KX	//CJ, watch our right!
				$input_text_f1 = FIN1_KX	//CJ, watch our right!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
	ENDIF

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////	Chase begins	////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
IF motelchase_f1flag = 2

	SET_PLAYER_CONTROL PLAYER1 OFF
	IF NOT IS_CAR_DEAD sweet_car
		SET_CAR_PROOFS sweet_car TRUE TRUE TRUE TRUE TRUE
	ENDIF
	SWITCH_WIDESCREEN ON
	SET_NEAR_CLIP 0.1

	//remove all cars and peds
	STOP_PLAYBACK_RECORDED_CAR policecar1_f1
	DELETE_CAR policecar1_f1
	REMOVE_BLIP policecar1_f1blip
	STOP_PLAYBACK_RECORDED_CAR policecar2_f1
	DELETE_CAR policecar2_f1
	REMOVE_BLIP policecar2_f1blip
	STOP_PLAYBACK_RECORDED_CAR policecar3_f1
	DELETE_CAR policecar3_f1
	REMOVE_BLIP policecar3_f1blip
	STOP_PLAYBACK_RECORDED_CAR policecar4_f1
	DELETE_CAR policecar4_f1
	REMOVE_BLIP policecar4_f1blip
	STOP_PLAYBACK_RECORDED_CAR policecar5_f1
	DELETE_CAR policecar5_f1
	REMOVE_BLIP policecar5_f1blip
	DELETE_CAR policecar6_f1
	REMOVE_BLIP policecar6_f1blip
	STOP_PLAYBACK_RECORDED_CAR policecar7_f1
	DELETE_CAR policecar7_f1
	REMOVE_BLIP policecar7_f1blip
	DELETE_CHAR cop1_f1
	DELETE_CHAR cop2_f1
	DELETE_CHAR cop3_f1
	DELETE_CHAR cop4_f1
	DELETE_CHAR cop5_f1
	DELETE_CHAR cop6_f1
	DELETE_CHAR cop7_f1
	DELETE_CHAR cop8_f1
	DELETE_CHAR cop9_f1
	DELETE_CHAR cop10_f1
	DELETE_CHAR cop11_f1
	DELETE_CHAR cop12_f1
	DELETE_CHAR cop13_f1
	DELETE_CHAR cop14_f1

	//reset flags that I am going to use again
	copcars_f1flag = 0
	policecar1_f1flag =	0
	policecar2_f1flag =	0
	policecar3_f1flag =	0
	policecar4_f1flag =	0
	policecar5_f1flag =	0
	policecar6_f1flag =	0
	policecar7_f1flag =	0
	policecar8_f1flag =	0
	policecar1swap_f1flag =	0
	policecar2swap_f1flag =	0
	policecar3swap_f1flag =	0
	policecar4swap_f1flag =	0
	policecar5swap_f1flag =	0
	policecar6swap_f1flag =	0
	policecar7swap_f1flag =	0
	policecar8swap_f1flag =	0


	REQUEST_CAR_RECORDING 331
	REQUEST_MODEL DYN_SCAFFOLD_4b
	REQUEST_CAR_RECORDING 339
	REQUEST_ANIMATION MD_CHASE

	REQUEST_CAR_RECORDING 373
	REQUEST_CAR_RECORDING 374
	REQUEST_CAR_RECORDING 375
	REQUEST_CAR_RECORDING 376
	REQUEST_CAR_RECORDING 377

	REQUEST_CAR_RECORDING 371
	REQUEST_CAR_RECORDING 372
	REQUEST_CAR_RECORDING 378
	REQUEST_CAR_RECORDING 380
	REQUEST_CAR_RECORDING 381

	REQUEST_MODEL munch_donut
	LOAD_SPECIAL_CHARACTER 2 SMOKE
	LOAD_SPECIAL_CHARACTER 3 RYDER2

	REQUEST_MODEL chopcop_armr
	REQUEST_MODEL chopcop_legr
	REQUEST_MODEL chopcop_head
	REQUEST_MODEL chopcop_torso
	REQUEST_MODEL Wd_Fence_Anim

	REQUEST_MODEL LAPDM1
	REQUEST_MODEL COPBIKE
	LOAD_MISSION_AUDIO 1 SOUND_FIN1_LE	//Waste of good doughnuts.
	LOAD_MISSION_AUDIO 2 SOUND_FIN1_LF	//Let’s roll
	
	REQUEST_MODEL md_poster

	LOAD_ALL_MODELS_NOW
 
 	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 331	//sweet car
		OR NOT HAS_MODEL_LOADED DYN_SCAFFOLD_4b
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 339
		OR NOT HAS_ANIMATION_LOADED MD_CHASE
		OR NOT HAS_MODEL_LOADED LAPDM1
		OR NOT HAS_MODEL_LOADED COPBIKE
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 373
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 374
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 375
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 376
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 377
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 371
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 372
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 378
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 380
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED munch_donut
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 381
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED chopcop_armr
		OR NOT HAS_MODEL_LOADED chopcop_legr
		OR NOT HAS_MODEL_LOADED chopcop_head
		OR NOT HAS_MODEL_LOADED Wd_Fence_Anim
		OR NOT HAS_MISSION_AUDIO_LOADED 1
		OR NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0
	ENDWHILE

	CREATE_CAR COPBIKE 2571.39 -1248.083 45.2 copbike1_f1 //bike that goes off to the left goto 2604.02 -1256.38 48.84
	SET_PETROL_TANK_WEAKPOINT copbike1_f1 FALSE
	SET_CAR_HEADING copbike1_f1 181.089
	CREATE_CAR COPBIKE 2569.95 -1248.083 45.2 copbike2_f1 //bike that chases the player
	SET_PETROL_TANK_WEAKPOINT copbike2_f1 FALSE
	SET_CAR_HEADING copbike2_f1 181.089
	CREATE_CAR COPBIKE 2586.339 -1351.697 35.174 copbike3_f1 //bike jumper
	SET_PETROL_TANK_WEAKPOINT copbike3_f1 FALSE
	SET_CAR_HEADING copbike3_f1 87.221
	FORCE_CAR_LIGHTS copbike3_f1 FORCE_CAR_LIGHTS_ON
	FORCE_CAR_LIGHTS copbike1_f1 FORCE_CAR_LIGHTS_OFF
	FORCE_CAR_LIGHTS copbike2_f1 FORCE_CAR_LIGHTS_OFF
	CREATE_CHAR_INSIDE_CAR copbike1_f1 PEDTYPE_MISSION1 LAPDM1 biker1_f1
	SET_CHAR_DECISION_MAKER biker1_f1 motel_DM
	CREATE_CHAR_INSIDE_CAR copbike2_f1 PEDTYPE_MISSION1 LAPDM1 biker2_f1
	SET_CHAR_DECISION_MAKER biker2_f1 motel_DM

	CREATE_CAR COPCARLA 2505.736 -1256.742 33.64 policecar1_f1	//police car that crashes into scaffolding 
	SET_PETROL_TANK_WEAKPOINT policecar1_f1 FALSE
	SET_CAR_HEADING policecar1_f1 268.42
	CREATE_CHAR_INSIDE_CAR policecar1_f1 PEDTYPE_MISSION1 LAPD1 cop1_f1
	SET_CHAR_DECISION_MAKER cop1_f1 motel_DM
	SET_CHAR_SUFFERS_CRITICAL_HITS cop1_f1 FALSE
	SET_CHAR_CAN_BE_SHOT_IN_VEHICLE cop1_f1 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop1_f1 TRUE

	CREATE_OBJECT munch_donut 2570.59 -1247.66 44.88 donut_f1 //45.07  //47.4

	SET_FIXED_CAMERA_POSITION 2570.6357 -1245.9398 45.3765 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2570.6248 -1246.9307 45.2422 JUMP_CUT

	DO_FADE 250 FADE_IN

	IF NOT IS_CAR_DEAD sweet_car
		IF IS_PLAYBACK_GOING_ON_FOR_CAR	sweet_car
			STOP_PLAYBACK_RECORDED_CAR sweet_car
			CAR_SET_IDLE sweet_car
			FREEZE_CAR_POSITION sweet_car TRUE
		ELSE
			CAR_SET_IDLE sweet_car
			FREEZE_CAR_POSITION sweet_car TRUE
		ENDIF
	ENDIF

	CREATE_FX_SYSTEM carwashspray 2451.04 -1460.79 23.0 TRUE jetwashfx1_f1

	IF NOT IS_CAR_DEAD sweet_car
		DETACH_CHAR_FROM_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2585.74 -1264.16 46.2
		CREATE_CHAR_INSIDE_CAR sweet_car PEDTYPE_SPECIAL SPECIAL02 big_smoke
		SET_CHAR_DECISION_MAKER big_smoke motel_DM
		CREATE_CHAR_AS_PASSENGER sweet_car PEDTYPE_SPECIAL SPECIAL03 0 ryder
		SET_CHAR_DECISION_MAKER ryder motel_DM
	ENDIF

	CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 0.0 0.0 0.0 bikerjumper3_f1
	SET_CHAR_DECISION_MAKER bikerjumper3_f1 motel_DM
	SET_CHAR_SUFFERS_CRITICAL_HITS bikerjumper3_f1 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER bikerjumper3_f1 TRUE
	SET_CAR_PROOFS copbike3_f1 TRUE TRUE TRUE TRUE TRUE
	TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper3_f1 MD_BIKE_Jmp_F MD_CHASE 4.0 FALSE FALSE FALSE TRUE -1
	ATTACH_CHAR_TO_CAR bikerjumper3_f1 copbike3_f1 0.0 -0.401 0.47 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED 
	SET_CHAR_HEALTH bikerjumper3_f1 1000
	bikerjumper3_f1flag = 1


	CREATE_OBJECT DYN_SCAFFOLD_4b 2563.232 -1322.392 40.3 sca1_f1
	SET_OBJECT_HEADING sca1_f1 170.0
	CREATE_OBJECT DYN_SCAFFOLD_4b 2563.232 -1322.392 42.5 sca2_f1
	SET_OBJECT_HEADING sca2_f1 170.0
	CREATE_OBJECT DYN_SCAFFOLD_4b 2563.232 -1322.392 44.7 sca3_f1
	SET_OBJECT_HEADING sca3_f1 170.0

	CREATE_OBJECT DYN_SCAFFOLD_4b 2565.882 -1322.392 40.3 sca4_f1
	SET_OBJECT_HEADING sca4_f1 170.0
	CREATE_OBJECT DYN_SCAFFOLD_4b 2565.882 -1322.392 42.5 sca5_f1
	SET_OBJECT_HEADING sca5_f1 170.0
	CREATE_OBJECT DYN_SCAFFOLD_4b 2565.882 -1322.392 44.7 sca6_f1
	SET_OBJECT_HEADING sca6_f1 170.0
	sca_f1flag = 1

	CREATE_CAR COPCARLA 2520.49 -1485.04 22.84 policecar2_f1 //first chaser through car wash
	SET_PETROL_TANK_WEAKPOINT policecar2_f1 FALSE
	SET_CAR_HEADING policecar2_f1 1.33
	SET_CAR_HEALTH policecar2_f1 750
	SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar2_f1 TRUE
	CREATE_CHAR_INSIDE_CAR policecar2_f1 PEDTYPE_MISSION1 LAPD1 cop2_f1
	SET_CHAR_DECISION_MAKER cop2_f1 motel_DM
	SET_CHAR_HEALTH cop2_f1 100
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop2_f1 TRUE
	CREATE_CHAR_AS_PASSENGER policecar2_f1 PEDTYPE_MISSION1 LAPD1 0 cop3_f1
	SET_CHAR_DECISION_MAKER cop3_f1 motel_dm
	SET_CHAR_HEALTH cop3_f1 100
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop3_f1 TRUE
	GIVE_WEAPON_TO_CHAR cop3_f1 WEAPONTYPE_MP5 9999
	SET_CHAR_SUFFERS_CRITICAL_HITS cop3_f1 FALSE
	CAR_SET_IDLE policecar2_f1

	CREATE_CAR COPCARLA 2532.98 -1502.372 23.67 policecar3_f1 //2nd chaser thru car wash
	SET_PETROL_TANK_WEAKPOINT policecar3_f1 FALSE
	SET_CAR_HEADING policecar3_f1 266.9
	SET_CAR_HEALTH policecar3_f1 600 //800
	SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar3_f1 TRUE
	CREATE_CHAR_INSIDE_CAR policecar3_f1 PEDTYPE_MISSION1 LAPD1 cop4_f1
	SET_CHAR_DECISION_MAKER cop4_f1 motel_DM
	SET_CHAR_SUFFERS_CRITICAL_HITS cop4_f1 FALSE
	SET_CHAR_HEALTH cop4_f1 100
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop4_f1 TRUE
	CREATE_CHAR_AS_PASSENGER policecar3_f1 PEDTYPE_MISSION1 LAPD1 0 cop5_f1
	SET_CHAR_DECISION_MAKER cop5_f1 motel_dm
	SET_CHAR_HEALTH cop5_f1 100
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop5_f1 TRUE
	GIVE_WEAPON_TO_CHAR cop5_f1 WEAPONTYPE_MP5 9999
	SET_CHAR_SUFFERS_CRITICAL_HITS cop5_f1 FALSE
	CAR_SET_IDLE policecar3_f1

	CREATE_CAR COPCARLA 2510.967 -1448.138 28.398 policecar8_f1 //chase that goes away after car wash
	SET_PETROL_TANK_WEAKPOINT policecar8_f1 FALSE
	SET_CAR_HEADING policecar8_f1 72.49
	SET_CAR_HEALTH policecar8_f1 325
	SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar8_f1 TRUE
	CREATE_CHAR_INSIDE_CAR policecar8_f1 PEDTYPE_MISSION1 LAPD1 cop7_f1
	SET_CHAR_DECISION_MAKER cop7_f1 motel_DM
	SET_CHAR_SUFFERS_CRITICAL_HITS cop7_f1 FALSE
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop7_f1 TRUE
	CREATE_CHAR_AS_PASSENGER policecar8_f1 PEDTYPE_MISSION1 LAPD1 0 cop8_f1
	SET_CHAR_DECISION_MAKER cop8_f1 motel_dm
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop8_f1 TRUE
	GIVE_WEAPON_TO_CHAR cop8_f1 WEAPONTYPE_MP5 9999
	SET_CHAR_SUFFERS_CRITICAL_HITS cop8_f1 FALSE
	CAR_SET_IDLE policecar8_f1

	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CAR_DEAD policecar1_f1
			IF NOT IS_CAR_DEAD copbike2_f1
				IF NOT IS_CAR_DEAD copbike3_f1
					FREEZE_CAR_POSITION sweet_car FALSE
					START_PLAYBACK_RECORDED_CAR sweet_car 331
					START_PLAYBACK_RECORDED_CAR policecar1_f1 339
					START_PLAYBACK_RECORDED_CAR copbike2_f1 371 
					START_PLAYBACK_RECORDED_CAR copbike3_f1 372
					SWITCH_CAR_SIREN copbike3_f1 ON
					bikerjumper3_f1flag = 2
					ADD_BLIP_FOR_CAR policecar1_f1 policecar1_f1blip
					CHANGE_BLIP_DISPLAY policecar1_f1blip BLIP_ONLY
					policecar1_f1flag = 1
					SWITCH_CAR_SIREN policecar1_f1 ON
					TIMERA = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	motelchase_f1flag = 3
ENDIF

IF motelchase_f1flag = 3
	IF TIMERA > 1100
		//drop the donut
		IF DOES_OBJECT_EXIST donut_f1 
			PLAY_OBJECT_ANIM donut_f1 donutdrop MD_CHASE 4.0 FALSE TRUE
		ENDIF
		PLAY_MISSION_AUDIO 1 //Waste of good doughnuts.
		PRINT_NOW FIN1_LE 2250 1 //Waste of good doughnuts.
		motelchase_f1flag = 4
	ENDIF
ENDIF

IF motelchase_f1flag = 4
	IF TIMERA > 3800
		IF NOT IS_CAR_DEAD copbike1_f1
			//script bike moves
			PLAY_MISSION_AUDIO 2
			PRINT_NOW FIN1_LF 2000 1//Let’s roll
			CAR_GOTO_COORDINATES copbike1_f1 2604.02 -1256.38 48.84
			SET_CAR_CRUISE_SPEED copbike1_f1 25.0
			SET_CAR_DRIVING_STYLE copbike1_f1 DRIVINGMODE_AVOIDCARS
			IF NOT IS_CAR_DEAD copbike1_f1
				FORCE_CAR_LIGHTS copbike1_f1 FORCE_CAR_LIGHTS_ON
				SWITCH_CAR_SIREN copbike1_f1 ON
			ENDIF
			IF NOT IS_CAR_DEAD copbike2_f1
				FORCE_CAR_LIGHTS copbike2_f1 FORCE_CAR_LIGHTS_ON
				SWITCH_CAR_SIREN copbike2_f1 ON
			ENDIF
			motelchase_f1flag = 5
		ENDIF
	ENDIF
ENDIF

IF motelchase_f1flag = 5
	IF TIMERA > 4500
		//switch to chase view
		SET_FIXED_CAMERA_POSITION 2569.7429 -1287.6459 46.8949 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2569.8252 -1286.6519 46.8238 JUMP_CUT
		SET_CHAR_HEADING scplayer 180.0
		motelchase_f1flag = 6
	ENDIF
ENDIF

IF motelchase_f1flag = 6
	IF NOT IS_CAR_DEAD sweet_car
		IF LOCATE_CAR_2D sweet_car 2569.7429 -1287.6459 1.2 1.2 FALSE
		OR TIMERB > 50800//50500
			//switch to chase view
			DELETE_CAR copbike1_f1 
			DELETE_CHAR biker1_f1
			DELETE_OBJECT donut_f1
			DELETE_CHAR big_smoke
			DELETE_CHAR ryder
			UNLOAD_SPECIAL_CHARACTER 2
			UNLOAD_SPECIAL_CHARACTER 3

			IF NOT IS_CAR_DEAD sweet_car////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				ATTACH_CHAR_TO_CAR scplayer sweet_car 0.0 -0.5 0.8 FACING_BACK 360.0 WEAPONTYPE_AK47////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 75.0 75.0 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			ENDIF 

			RESTORE_CAMERA//////////////////////////////////////////////////////////////////////////////////////////////////////
			SWITCH_WIDESCREEN OFF///////////////////////////////////////////////////////////////////////////////////////////////
			SET_PLAYER_CONTROL PLAYER1 ON

			SET_CAR_PROOFS sweet_car FALSE TRUE TRUE TRUE TRUE
			SET_CHAR_PROOFS scplayer FALSE TRUE TRUE FALSE FALSE
			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE

			IF NOT IS_CHAR_DEAD biker2_f1
				SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE biker2_f1 KNOCKOFFBIKE_EASY
			ENDIF
			policecarexplode_f1flag = 3
			actiontext_f1flag = 0
			progressaudio_f1flag = 0
			handlingaudio_f1flag = 0
			copcars_f1flag = 1
			motelchase_f1flag = 7

		ENDIF
	ENDIF
ENDIF

//chase begins






IF NOT IS_CAR_DEAD sweet_car
	IF motelchase_f1flag > 2

		///////////////////////////////////////////////////	1st Biker jumping
		///////////////////////////////////////////////////	1st Biker jumping
		///////////////////////////////////////////////////	1st Biker jumping
		///////////////////////////////////////////////////	1st Biker jumping
		///////////////////////////////////////////////////	1st Biker jumping
		
		IF bikerjumper3_f1flag = 2
			IF NOT IS_CHAR_DEAD bikerjumper3_f1
				IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Jmp_F
					GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Jmp_F animframebk_jmp
					IF animframebk_jmp > 0.0
						SET_CHAR_ANIM_SPEED bikerjumper3_f1 MD_BIKE_Jmp_F 0.0
						bikerjumper3_f1flag = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		//bike jumps
		IF bikerjumper3_f1flag = 3
			IF TIMERA > 12600 //12500
				IF NOT IS_CHAR_DEAD bikerjumper3_f1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper3_f1 MD_BIKE_Jmp_F MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1
					SET_CHAR_ANIM_SPEED bikerjumper3_f1 MD_BIKE_Jmp_F 1.0
					actiontext_f1flag = 1
					bikerjumper3_f1flag = 4
				ENDIF
			ENDIF
		ENDIF

		//attach to the car
		IF bikerjumper3_f1flag = 4
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD bikerjumper3_f1
					IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Jmp_F
						GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Jmp_F animframebk_jmp
							IF animframebk_jmp = 1.0
								IF playdeathanim_f1flag = 1
									TASK_PLAY_ANIM bikerjumper3_f1 MD_BIKE_Lnd_Die_F MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1
									bikerjumper3_f1flag = 100
								ELSE
									TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper3_f1 MD_BIKE_Lnd_F MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1
									TIMERA = 0
									bikerjumper3_f1flag = 5
								ENDIF
								WAIT 0
								IF NOT IS_CHAR_DEAD bikerjumper3_f1
									IF NOT IS_CAR_DEAD sweet_car
										DETACH_CHAR_FROM_CAR bikerjumper3_f1
										ATTACH_CHAR_TO_CAR bikerjumper3_f1 sweet_car 0.0 3.0 0.3 FACING_BACK 360.0 WEAPONTYPE_UNARMED
									ENDIF
								ENDIF

								IF NOT IS_CAR_DEAD copbike3_f1
									STOP_PLAYBACK_RECORDED_CAR copbike3_f1
									SET_CAR_ROTATION_VELOCITY copbike3_f1 -3.0 -2.0 0.2003
									MARK_CAR_AS_NO_LONGER_NEEDED copbike3_f1
									MARK_CAR_AS_NO_LONGER_NEEDED copbike2_f1 
									MARK_CHAR_AS_NO_LONGER_NEEDED biker2_f1
								ENDIF

							ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF



		//punch player
		IF bikerjumper3_f1flag = 5
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD bikerjumper3_f1
					IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Lnd_F
						GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Lnd_F animframebk_jmp
							IF animframebk_jmp = 1.0
								TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper3_f1 MD_BIKE_Punch_F MD_CHASE 8.0 TRUE FALSE FALSE FALSE -1
								bikerjumper3_f1flag = 6
							ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//set peds heading when on bike	
		IF bikerjumper3_f1flag > 0
			IF bikerjumper3_f1flag < 5
				IF NOT IS_CAR_DEAD copbike3_f1
					IF NOT IS_CHAR_DEAD bikerjumper3_f1
						GET_CAR_HEADING copbike3_f1 bikerjumper3_f1heading
						SET_CHAR_HEADING bikerjumper3_f1 bikerjumper3_f1heading
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//set peds heading when on car
		IF bikerjumper3_f1flag > 4
			IF bikerjumper3_f1flag < 101
				IF NOT IS_CHAR_DEAD bikerjumper3_f1
					GET_CAR_HEADING sweet_car bikerjumper3_f1heading
					SET_CHAR_HEADING bikerjumper3_f1 bikerjumper3_f1heading
				ENDIF
			ENDIF
		ENDIF


		//camera bump
		IF NOT IS_CHAR_DEAD bikerjumper3_f1
			IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Punch_F
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Punch_F animframebk_punch
					IF animframebk_punch > 0.2
						IF bikerjumper3_f1flag = 6
							DO_CAMERA_BUMP -2.2 1.7
							DAMAGE_CHAR scplayer 10 TRUE
							bikerjumper3_f1flag = 7
						ENDIF
					ELSE
						bikerjumper3_f1flag = 6
					ENDIF
			ENDIF
		ENDIF


		//if is char dead
		IF NOT IS_CHAR_DEAD bikerjumper3_f1
			GET_CHAR_HEALTH bikerjumper3_f1 bikerjumper3_f1health
				IF bikerjumper3_f1health < 901
					SET_CHAR_PROOFS bikerjumper3_f1 TRUE TRUE TRUE TRUE TRUE
					playdeathanim_f1flag = 1
				ENDIF
		ENDIF		

		//before he lands
		IF bikerjumper3_f1flag = 100
			IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Lnd_Die_F
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Lnd_Die_F animframebk_jmp
					IF animframebk_jmp = 1.0
						DETACH_CHAR_FROM_CAR bikerjumper3_f1
						GET_CAR_COORDINATES sweet_car player_x player_y player_z
						player_x = player_x + 1.433
						player_y = player_y + 1.763
						player_z = player_z + 0.0
						SET_CHAR_COORDINATES bikerjumper3_f1 player_x player_y player_z
						TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper3_f1 MD_BIKE_Lnd_Roll_F MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1
						bikerjumper3_f1flag = 101
					ENDIF
			ENDIF
		ENDIF
		IF bikerjumper3_f1flag = 101
			IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Lnd_Roll_F
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Lnd_Roll_F animframebk_jmp
					IF animframebk_jmp = 1.0
						REMOVE_CHAR_ELEGANTLY bikerjumper3_f1
						bikerjumper3_f1flag = 102
					ENDIF
			ENDIF
		ENDIF
		
		//after he lands, char killed
   		IF bikerjumper3_f1flag > 4
			IF bikerjumper3_f1flag < 8
			    IF playdeathanim_f1flag = 1
				OR TIMERA > 15000
					bikerjumper3_f1flag = 200
				ENDIF
			ENDIF
		ENDIF

		IF bikerjumper3_f1flag = 200
			IF NOT IS_CHAR_DEAD bikerjumper3_f1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper3_f1 MD_BIKE_Shot_F MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1
				bikerjumper3_f1flag = 201
			ENDIF
		ENDIF
		IF bikerjumper3_f1flag = 201
			IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Shot_F
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Shot_F animframebk_jmp
					IF animframebk_jmp = 1.0
						DETACH_CHAR_FROM_CAR bikerjumper3_f1
						GET_CAR_COORDINATES sweet_car player_x player_y player_z
						player_x = player_x + 1.433
						player_y = player_y + 1.763
						player_z = player_z + 0.0
						SET_CHAR_COORDINATES bikerjumper3_f1 player_x player_y player_z
						TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper3_f1 MD_BIKE_Lnd_Roll_F MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1
						bikerjumper3_f1flag = 202
					ENDIF
			ENDIF
		ENDIF
		IF bikerjumper3_f1flag = 201
			IF IS_CHAR_PLAYING_ANIM bikerjumper3_f1 MD_BIKE_Lnd_Roll_F
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper3_f1 MD_BIKE_Lnd_Roll_F animframebk_jmp
					IF animframebk_jmp = 1.0
						DELETE_CHAR	bikerjumper3_f1
						bikerjumper3_f1flag = 202
					ENDIF
			ENDIF
		ENDIF

	ENDIF
ENDIF

IF NOT IS_CAR_DEAD sweet_car
	IF motelchase_f1flag = 7


		/////////////////////////////////////////////////////////////////////	audio
		//dialogue
		GOSUB process_audio_f1


		IF progressaudio_f1flag = 0
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LA	//Eyes front, CJ!
				$input_text_f1 = FIN1_LA	//Eyes front, CJ!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		IF policecarexplode_f1flag = 0
			IF TIMERB > 53000
				IF NOT IS_CHAR_DEAD biker2_f1
					TASK_DIE biker2_f1
					policecarexplode_f1flag = 1
				ELSE
					policecarexplode_f1flag = 1
				ENDIF
			ENDIF
		ENDIF
		IF policecarexplode_f1flag = 3
			IF TIMERB > 58900
				IF NOT IS_CAR_DEAD policecar1_f1
					EXPLODE_CAR policecar1_f1
					policecarexplode_f1flag = 4
				ELSE
					policecarexplode_f1flag = 4
				ENDIF
			ENDIF
		ENDIF
		IF policecarexplode_f1flag = 4
			IF TIMERB > 85200
				IF NOT IS_CAR_DEAD policecar2_f1
					EXPLODE_CAR	policecar2_f1
					policecarexplode_f1flag = 5
				ELSE
					policecarexplode_f1flag = 5
				ENDIF
			ENDIF
		ENDIF

		IF actiontext_f1flag = 1
			IF progressaudio_f1flag = 1
				IF handlingaudio_f1flag = 0
					audio_label_f1 = SOUND_FIN1_BK	//Surprise, homeboy!
					$input_text_f1 = FIN1_BK //Surprise, homeboy!
					GOSUB load_audio_f1
				ENDIF
			ENDIF
		ENDIF

		IF progressaudio_f1flag = 2
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_KK	//Holy shit! Look at that!
				$input_text_f1 = FIN1_KK	//Holy shit! Look at that!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		IF progressaudio_f1flag = 3
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_KR //Wish I’d stayed at home and watched the game!
				$input_text_f1 = FIN1_KR //Wish I’d stayed at home and watched the game!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		IF actiontext_f1flag = 1
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2545.15 -1460.3 15.0 15.0 FALSE
				actiontext_f1flag = 2
			ENDIF
		ENDIF

		IF actiontext_f1flag = 2
			IF progressaudio_f1flag = 4
				IF handlingaudio_f1flag = 0
					audio_label_f1 = SOUND_FIN1_ZB	//Carl, behind us!  Behind us!
					$input_text_f1 = FIN1_ZB //Carl, behind us!  Behind us!
					GOSUB load_audio_f1
				ENDIF
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 5
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LL	//Put up your windows!
				$input_text_f1 = FIN1_LL//Put up your windows!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 6
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LI	//Smoke, what the FUCK are you doin’?
				$input_text_f1 = FIN1_LI  //Smoke, what the FUCK are you doin’?
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		//soap in eyes
		IF actiontext_f1flag = 2//3
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2451.92 -1461.5 12.0 12.0 FALSE
				actiontext_f1flag = 4
			ENDIF
		ENDIF

		IF actiontext_f1flag = 4
			IF progressaudio_f1flag = 7
				IF handlingaudio_f1flag = 0
					audio_label_f1 = SOUND_FIN1_LP	//Dammit, Smoke, I got soap in my eyes!
					$input_text_f1 = FIN1_LP//Dammit, Smoke, I got soap in my eyes!
					GOSUB load_audio_f1
				ENDIF
			ENDIF
		ENDIF

		IF progressaudio_f1flag = 8
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_MK	//Smoke, you’re insane!
				$input_text_f1 = FIN1_MK	//Smoke, you’re insane!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 9
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LJ	//I like things clean!
				$input_text_f1 = FIN1_LJ	//I like things clean!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		//after the car wash
		IF actiontext_f1flag = 4
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2344.98 -1382.22 18.0 18.0 FALSE
				actiontext_f1flag = 5
			ENDIF
		ENDIF

		IF actiontext_f1flag = 5
			IF progressaudio_f1flag = 10
				IF handlingaudio_f1flag = 0
					audio_label_f1 = SOUND_FIN1_KS //Hey, CJ, watch to the left!
					$input_text_f1 = FIN1_KS //Hey, CJ, watch to the left!
					GOSUB load_audio_f1
				ENDIF
			ENDIF
		ENDIF

		//going around in circles
		IF actiontext_f1flag = 5
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2305.51 -1370.01 18.0 18.0 FALSE
				actiontext_f1flag = 6
			ENDIF
		ENDIF

		IF progressaudio_f1flag = 11
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LQ	//Hey, ain’t we been here before?
				$input_text_f1 = FIN1_LQ	//Hey, ain’t we been here before?
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 12
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LT	//Hey, I’m taking what options I have, a’ight?
				$input_text_f1 = FIN1_LT	//Hey, I’m taking what options I have, a’ight?
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 13
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LV	//Maybe if you have a nice word with these officers they’ll let us on our way!
				$input_text_f1 = FIN1_LV	//Maybe if you have a nice word with these officers they’ll let us on our way!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		//2nd bike jumper
		IF actiontext_f1flag = 7
			IF progressaudio_f1flag = 14
				IF handlingaudio_f1flag = 0
					audio_label_f1 = SOUND_FIN1_BL //Heads up, brother!
					$input_text_f1 = FIN1_BL //Heads up, brother!
					GOSUB load_audio_f1
				ENDIF
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 15
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LW	//Hey, man, some idiot’s hanging off the back of the car!
				$input_text_f1 = FIN1_LW	//Hey, man, some idiot’s hanging off the back of the car!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 16
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_LX	//Somebody shoot him!
				$input_text_f1 = FIN1_LX //Somebody shoot him!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 17
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_ZA //Oh shit!  All I got in my rearview is bad guys!
				$input_text_f1 = FIN1_ZA //Oh shit!  All I got in my rearview is bad guys!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF progressaudio_f1flag = 18
			IF handlingaudio_f1flag = 0
				audio_label_f1 = SOUND_FIN1_ZC	//CJ, cover the rear!
				$input_text_f1 = FIN1_ZC	//CJ, cover the rear!
				GOSUB load_audio_f1
			ENDIF
		ENDIF


		//from the road block to just before the cut of the car bursting through the advertising sign
		IF actiontext_f1flag = 8
			IF handlingaudio_f1flag = 0
				IF progressaudio_f1flag = 20
					audio_label_f1 = SOUND_FIN1_MA //OH SHIT! Roadblock up ahead!
					$input_text_f1 = FIN1_MA //OH SHIT! Roadblock up ahead!
					GOSUB load_audio_f1
				ENDIF
			ENDIF
		ENDIF
		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 21
				audio_label_f1 = SOUND_FIN1_MB	//The K's jammed!
				$input_text_f1 = FIN1_MB	//The K's jammed!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 22
				audio_label_f1 = SOUND_FIN1_MD	//Fuckit, I’m going through!
				$input_text_f1 = FIN1_MD	//Fuckit, I’m going through!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 23
				audio_label_f1 = SOUND_FIN1_ME	//CJ, we got the ghetto bird up ahead!
				$input_text_f1 = FIN1_ME	//CJ, we got the ghetto bird up ahead!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 24
				audio_label_f1 = SOUND_FIN1_EA //This is the LSPD do not – HEY! WHAT THE FUCK? TOO LOW!!
				$input_text_f1 = FIN1_EA //This is the LSPD do not – HEY! WHAT THE FUCK? TOO LOW!!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 25
				audio_label_f1 = SOUND_FIN1_MH //Back up, Smoke, Back up!
				$input_text_f1 = FIN1_MH //Back up, Smoke, Back up!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 26
				audio_label_f1 = SOUND_FIN1_MI //Hell no, I’m going through!
				$input_text_f1 = FIN1_MI //Hell no, I’m going through!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 27
				audio_label_f1 = SOUND_FIN1_MT //Oh man we gonna diiie!
				$input_text_f1 = FIN1_MT //Oh man we gonna diiie!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 28
				audio_label_f1 = SOUND_FIN1_ML	//Oooooooh SHIIIIIIIT!
				$input_text_f1 = FIN1_ML //Oooooooh SHIIIIIIIT!
				GOSUB load_audio_f1
			ENDIF
		ENDIF
	
		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 29
				audio_label_f1 = SOUND_FIN1_MR //Slow down, Smoke, SLOW DOWN!
				$input_text_f1 = FIN1_MR //Slow down, Smoke, SLOW DOWN!
				GOSUB load_audio_f1
			ENDIF
		ENDIF

		IF handlingaudio_f1flag = 0
			IF progressaudio_f1flag = 30
				audio_label_f1 = SOUND_FIN1_MS //Oh shit, the brakes is out!
				$input_text_f1 = FIN1_MS //Oh shit, the brakes is out!
				GOSUB load_audio_f1
			ENDIF
		ENDIF



		/////////////////////////////////////////////////////////////////////////////
		//imran


		IF sca_f1flag = 1
			IF NOT IS_CAR_DEAD policecar1_f1
				IF LOCATE_CAR_2D policecar1_f1 2566.12 -1322.35 1.5 1.5 FALSE
					IF DOES_OBJECT_EXIST sca2_f1
						BREAK_OBJECT sca2_f1 TRUE
					ENDIF
					IF DOES_OBJECT_EXIST sca3_f1
						BREAK_OBJECT sca3_f1 TRUE
					ENDIF
					sca_f1flag = 2
				ENDIF
			ENDIF
		ENDIF

		IF copcars_f1flag = 1
			IF LOCATE_CAR_2D sweet_car 2564.68 -1442.47 15.0 15.0 FALSE
				IF NOT IS_CAR_DEAD policecar2_f1
					START_PLAYBACK_RECORDED_CAR policecar2_f1 373
					ADD_BLIP_FOR_CAR policecar2_f1 policecar2_f1blip
					CHANGE_BLIP_DISPLAY policecar2_f1blip BLIP_ONLY
					SWITCH_CAR_SIREN policecar2_f1 ON
					policecar2_f1flag = 1
					policecar2swap_f1flag = 1
				ENDIF
				IF NOT IS_CAR_DEAD policecar3_f1
					START_PLAYBACK_RECORDED_CAR policecar3_f1 374
					ADD_BLIP_FOR_CAR policecar3_f1 policecar3_f1blip
					CHANGE_BLIP_DISPLAY policecar3_f1blip BLIP_ONLY
					SWITCH_CAR_SIREN policecar3_f1 ON
					policecar3_f1flag = 1
					policecar3swap_f1flag = 1
				ENDIF
				IF NOT IS_CAR_DEAD policecar8_f1
					START_PLAYBACK_RECORDED_CAR policecar8_f1 380
					ADD_BLIP_FOR_CAR policecar8_f1 policecar8_f1blip
					CHANGE_BLIP_DISPLAY policecar8_f1blip BLIP_ONLY
					SWITCH_CAR_SIREN policecar8_f1 ON
					policecar8_f1flag = 1
					policecar8swap_f1flag = 1
				ENDIF

				IF NOT IS_CHAR_DEAD cop3_f1
					TASK_DRIVE_BY cop3_f1 scplayer -1 0.0 0.0 0.0 500.0 DRIVEBY_AI_ALL_DIRN TRUE 40
				ENDIF

				IF NOT IS_CHAR_DEAD cop5_f1
					IF difficulty_f1flag > 1
						TASK_DRIVE_BY cop5_f1 -1 sweet_car 0.0 0.0 0.0 500.0 DRIVEBY_AI_ALL_DIRN TRUE 30
					ELSE
						TASK_DRIVE_BY cop5_f1 -1 sweet_car 0.0 0.0 0.0 500.0 DRIVEBY_AI_ALL_DIRN TRUE 75
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD cop8_f1
					TASK_DRIVE_BY cop8_f1 -1 sweet_car 0.0 0.0 0.0 500.0 DRIVEBY_AI_ALL_DIRN TRUE 40
				ENDIF
				
				PLAY_FX_SYSTEM jetwashfx1_f1

				DELETE_OBJECT sca1_f1
				DELETE_OBJECT sca2_f1
				DELETE_OBJECT sca3_f1
				DELETE_OBJECT sca4_f1
				DELETE_OBJECT sca5_f1
				DELETE_OBJECT sca6_f1
				MARK_MODEL_AS_NO_LONGER_NEEDED DYN_SCAFFOLD_4b
				MARK_MODEL_AS_NO_LONGER_NEEDED munch_donut
				copcars_f1flag = 2
			ENDIF
		ENDIF

		IF copcars_f1flag = 2
			IF LOCATE_CAR_2D sweet_car 2344.12 -1384.48 12.0 12.0 FALSE

				CREATE_CAR COPBIKE 2254.042 -1308.15 23.02 copbike4_f1 //bike jumper 2253.642 -1223.56 23.657
				SET_PETROL_TANK_WEAKPOINT copbike4_f1 FALSE
				SET_CAR_HEADING copbike4_f1 261.999
				FORCE_CAR_LIGHTS copbike4_f1 FORCE_CAR_LIGHTS_ON
				SWITCH_CAR_SIREN copbike4_f1 ON

				playdeathanim2_f1flag = 0
				CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 0.0 0.0 0.0 bikerjumper4_f1 //bike jumper
//				SET_CHAR_PROOFS bikerjumper4_f1 TRUE TRUE TRUE TRUE TRUE
				TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_BIKE_Jmp_BL MD_CHASE 4.0 FALSE FALSE FALSE TRUE -1
				ATTACH_CHAR_TO_CAR bikerjumper4_f1 copbike4_f1 0.0 -0.401 0.47 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED 
				SET_CHAR_HEALTH bikerjumper4_f1 1000
				bikerjumper4_f1flag = 1

				SET_CHAR_DECISION_MAKER bikerjumper4_f1 motel_DM
				SET_CHAR_SUFFERS_CRITICAL_HITS bikerjumper4_f1 FALSE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER bikerjumper4_f1 TRUE
				SET_CAR_ONLY_DAMAGED_BY_PLAYER copbike4_f1 TRUE
				SET_CAR_PROOFS copbike4_f1 TRUE TRUE TRUE TRUE TRUE

				CREATE_CAR COPCARLA 2222.98 -1303.52 22.654 policecar5_f1 //car that comes out with the bike
				SET_PETROL_TANK_WEAKPOINT policecar5_f1 FALSE
				SET_CAR_HEADING policecar5_f1 269.409
				SET_CAR_HEALTH policecar5_f1 850
				SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar5_f1 TRUE
				CREATE_CHAR_INSIDE_CAR policecar5_f1 PEDTYPE_MISSION1 LAPD1 cop9_f1
				SET_CHAR_DECISION_MAKER cop9_f1 motel_DM
				SET_CHAR_SUFFERS_CRITICAL_HITS cop9_f1 FALSE
				SET_CHAR_HEALTH cop9_f1 100
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop9_f1 TRUE
				CREATE_CHAR_AS_PASSENGER policecar5_f1 PEDTYPE_MISSION1 LAPD1 0 cop10_f1
				SET_CHAR_DECISION_MAKER cop10_f1 motel_dm
				SET_CHAR_HEALTH cop10_f1 100
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop10_f1 TRUE
				GIVE_WEAPON_TO_CHAR cop10_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_SUFFERS_CRITICAL_HITS cop10_f1 FALSE
				CAR_SET_IDLE policecar5_f1

				CREATE_CAR COPCARLA 2250.63 -1380.995 22.65 policecar6_f1 //at the bottom of the road
				SET_PETROL_TANK_WEAKPOINT policecar6_f1 FALSE
				SET_CAR_HEADING policecar6_f1 268.1
				SET_CAR_HEALTH policecar6_f1 800
				SET_CAR_PROOFS policecar6_f1 FALSE FALSE TRUE FALSE FALSE
				SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar6_f1 TRUE
				CREATE_CHAR_INSIDE_CAR policecar6_f1 PEDTYPE_MISSION1 LAPD1 cop12_f1
				SET_CHAR_DECISION_MAKER cop12_f1 motel_DM
				SET_CHAR_SUFFERS_CRITICAL_HITS cop12_f1 FALSE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop12_f1 TRUE
				CREATE_CHAR_AS_PASSENGER policecar6_f1 PEDTYPE_MISSION1 LAPD1 0 cop13_f1
				SET_CHAR_DECISION_MAKER cop13_f1 motel_dm
				SET_CHAR_HEALTH cop13_f1 200
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop13_f1 TRUE
				GIVE_WEAPON_TO_CHAR cop13_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_SUFFERS_CRITICAL_HITS cop13_f1 FALSE
				CAR_SET_IDLE policecar6_f1

//				CREATE_CAR COPCARLA 2330.692 -1304.53 23.9 policecar7_f1 // opposite the alley entrance
//				SET_CAR_HEADING policecar7_f1 87.107
//				SET_CAR_HEALTH policecar7_f1 750
//				SET_CAR_ONLY_DAMAGED_BY_PLAYER policecar7_f1 TRUE
//				CREATE_CHAR_INSIDE_CAR policecar7_f1 PEDTYPE_MISSION1 LAPD1 cop14_f1
//				SET_CHAR_DECISION_MAKER cop14_f1 motel_DM
//				SET_CHAR_SUFFERS_CRITICAL_HITS cop14_f1 FALSE
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop14_f1 TRUE
//				CREATE_CHAR_AS_PASSENGER policecar7_f1 PEDTYPE_MISSION1 LAPD1 0 cop15_f1
//				SET_CHAR_DECISION_MAKER cop15_f1 motel_dm
//				SET_CHAR_HEALTH cop15_f1 200
//				SET_CHAR_ONLY_DAMAGED_BY_PLAYER cop15_f1 TRUE

//				GIVE_WEAPON_TO_CHAR cop15_f1 WEAPONTYPE_MP5 9999
//				SET_CHAR_SUFFERS_CRITICAL_HITS cop15_f1 FALSE
//				CAR_SET_IDLE policecar7_f1
   				copcars_f1flag = 3
			ENDIF
		ENDIF

		IF copcars_f1flag = 3
			IF LOCATE_CAR_2D sweet_car 2304.185 -1364.711 20.0 20.0 FALSE 
				
				IF NOT IS_CAR_DEAD policecar6_f1
					START_PLAYBACK_RECORDED_CAR policecar6_f1 377
					ADD_BLIP_FOR_CAR policecar6_f1 policecar6_f1blip
					CHANGE_BLIP_DISPLAY policecar6_f1blip BLIP_ONLY
					SWITCH_CAR_SIREN policecar6_f1 ON
					policecar6_f1flag = 1
					policecar6swap_f1flag = 1
				ENDIF
				IF NOT IS_CHAR_DEAD cop13_f1
					TASK_DRIVE_BY cop13_f1 -1 sweet_car 0.0 0.0 0.0 500.0 DRIVEBY_AI_ALL_DIRN TRUE 30
				ENDIF

				KILL_FX_SYSTEM jetwashfx1_f1

				copcars_f1flag = 4
			ENDIF
		ENDIF

		IF copcars_f1flag = 4
			IF LOCATE_CAR_2D sweet_car 2289.817 -1319.51 25.0 25.0 FALSE

				IF NOT IS_CAR_DEAD copbike4_f1
					IF NOT IS_CHAR_DEAD bikerjumper4_f1
						START_PLAYBACK_RECORDED_CAR copbike4_f1 375
						TIMERA = 0
						bikerjumper4_f1flag = 3
					ENDIF
				ENDIF
				IF NOT IS_CAR_DEAD policecar5_f1
					START_PLAYBACK_RECORDED_CAR policecar5_f1 376
					ADD_BLIP_FOR_CAR policecar5_f1 policecar5_f1blip
					CHANGE_BLIP_DISPLAY policecar5_f1blip BLIP_ONLY
					SWITCH_CAR_SIREN policecar5_f1 ON
					policecar5_f1flag = 1
					policecar5swap_f1flag = 1
				ENDIF
//				IF NOT IS_CAR_DEAD policecar7_f1
//					START_PLAYBACK_RECORDED_CAR policecar7_f1 379
//					ADD_BLIP_FOR_CAR policecar7_f1 policecar7_f1blip
//					CHANGE_BLIP_DISPLAY policecar7_f1blip BLIP_ONLY
//					SWITCH_CAR_SIREN policecar7_f1 ON
//					policecar7_f1flag = 1
//					policecar7swap_f1flag = 1
//				ENDIF
				IF NOT IS_CHAR_DEAD cop10_f1
					TASK_DRIVE_BY cop10_f1 -1 sweet_car 0.0 0.0 0.0 500.0 DRIVEBY_AI_ALL_DIRN TRUE 40
				ENDIF
//				IF NOT IS_CHAR_DEAD cop15_f1
//					TASK_DRIVE_BY cop15_f1 -1 sweet_car 0.0 0.0 0.0 500.0 DRIVEBY_AI_ALL_DIRN TRUE 40
//				ENDIF
				DELETE_CHAR bikerjumper3_f1
				copcars_f1flag = 5
			ENDIF
		ENDIF

		IF copcars_f1flag = 5
			IF LOCATE_CAR_2D sweet_car 2221.81 -1260.26 5.5 5.5 FALSE


				//create two guys to get run over
				CREATE_CHAR PEDTYPE_MISSION1 LAPD1 2127.116 -1381.61 23.88 copleft_f1
				SET_CHAR_HEADING copleft_f1	84.93
				SET_CHAR_DECISION_MAKER copleft_f1 motel_dm
				SET_CHAR_PROOFS copleft_f1 TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER copleft_f1 TRUE
				GIVE_WEAPON_TO_CHAR copleft_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_SUFFERS_CRITICAL_HITS copleft_f1 FALSE
				SET_CHAR_ACCURACY copleft_f1 5
				enemy_f1 = copleft_f1
				enemytarget_f1 = scplayer
				GOSUB stayshootnoduck_f1label

				CREATE_CHAR PEDTYPE_MISSION1 LAPDM1 2127.09 -1382.81 23.32 copright_f1
				SET_CHAR_DECISION_MAKER copright_f1 motel_dm
				SET_CHAR_PROOFS copright_f1 TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER copright_f1 TRUE
				GIVE_WEAPON_TO_CHAR copright_f1 WEAPONTYPE_MP5 9999
				SET_CHAR_SUFFERS_CRITICAL_HITS copright_f1 FALSE
				SET_CHAR_ACCURACY copright_f1 5
				enemy_f1 = copright_f1
				enemytarget_f1 = scplayer
				GOSUB stayshootnoduck_f1label

				CREATE_CAR COPCARLA 2129.865 -1386.89 22.65 policecarblock2_f1
				SET_CAR_HEADING policecarblock2_f1 7.63
				CAR_SET_IDLE policecarblock2_f1
				CAR_SET_IDLE policecarblock2_f1
				TIMERB = 0

				IF helileave_f1flag = 0
					IF NOT IS_CAR_DEAD extpoliceheli_f1
						HELI_GOTO_COORDS extpoliceheli_f1 2168.0 -1508.0 33.29 30.0 30.0
						helileave_f1flag = 1
					ENDIF
				ENDIF
				//right
				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1470.354 25.328 fence_f1 
				SET_OBJECT_HEADING fence_f1 90.0
				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1465.688 25.328 fence2_f1 
				SET_OBJECT_HEADING fence2_f1 90.0
				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1461.021 25.328 fence3_f1 
				SET_OBJECT_HEADING fence3_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1456.353 25.328 fence4_f1 
//				SET_OBJECT_HEADING fence4_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1451.713 25.328 fence5_f1 
//				SET_OBJECT_HEADING fence5_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1447.041 25.328 fence6_f1 
//				SET_OBJECT_HEADING fence6_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1442.374 25.328 fence7_f1
//				SET_OBJECT_HEADING fence7_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1437.707 25.328 fence8_f1 
//				SET_OBJECT_HEADING fence8_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1433.04 25.328 fence9_f1 
//				SET_OBJECT_HEADING fence9_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1428.399 25.328 fence10_f1 
//				SET_OBJECT_HEADING fence10_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1423.737 25.328 fence11_f1 
//				SET_OBJECT_HEADING fence11_f1 90.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2167.763 -1419.096 25.328 fence12_f1 
//				SET_OBJECT_HEADING fence12_f1 90.0
	 			//left
				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1470.354 25.328 fencel_f1 
				SET_OBJECT_HEADING fencel_f1 270.0
				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1465.688 25.328 fence2l_f1 
				SET_OBJECT_HEADING fence2l_f1 270.0
				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1461.021 25.328 fence3l_f1 
				SET_OBJECT_HEADING fence3l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1456.353 25.328 fence4l_f1 
//				SET_OBJECT_HEADING fence4l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1451.713 25.328 fence5l_f1 
//				SET_OBJECT_HEADING fence5l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1447.041 25.328 fence6l_f1 
//				SET_OBJECT_HEADING fence6l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1442.374 25.328 fence7l_f1
//				SET_OBJECT_HEADING fence7l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1437.707 25.328 fence8l_f1 
//				SET_OBJECT_HEADING fence8l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1433.04 25.328 fence9l_f1 
//				SET_OBJECT_HEADING fence9l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1428.399 25.328 fence10l_f1 
//				SET_OBJECT_HEADING fence10l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1423.737 25.328 fence11l_f1 
//				SET_OBJECT_HEADING fence11l_f1 270.0
//				CREATE_OBJECT_NO_OFFSET Wd_Fence_Anim 2174.328 -1419.096 25.328 fence12l_f1 
//				SET_OBJECT_HEADING fence12l_f1 270.0
//
				copcars_f1flag = 6
			ENDIF
		ENDIF 

		IF copcars_f1flag = 6
			IF LOCATE_CAR_2D sweet_car 2081.93 -1261.68 6.0 6.0 FALSE

				IF NOT IS_CAR_DEAD policecar6_f1
					SET_CAR_HEALTH policecar6_f1 249
				ENDIF

				copcars_f1flag = 7
			ENDIF
		ENDIF


		IF copcars_f1flag = 7
			IF TIMERB > 3000

				IF NOT IS_CHAR_DEAD copleft_f1
					IF NOT IS_CHAR_DEAD copright_f1
						FREEZE_CHAR_POSITION copleft_f1 TRUE
						FREEZE_CHAR_POSITION copright_f1 TRUE				
						copcars_f1flag = 8
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		//turn camera around
		IF turncamera_f1flag = 0
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2074.18 -1372.28 7.0 7.0 FALSE
				CLEAR_MISSION_AUDIO 3
				LOAD_MISSION_AUDIO 3 SOUND_HELI_SLASH_PED //request heli slash audio
				SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 174.0 8.0 //want 184.0 //was 90.0
				DELETE_CAR copbike4_f1
				MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
				SET_PLAYER_FIRE_BUTTON PLAYER1 FALSE
				actiontext_f1flag = 8
				handlingaudio_f1flag = 0
				progressaudio_f1flag = 20
				turncamera_f1flag = 1
			ENDIF
		ENDIF

		IF turncamera_f1flag = 1
			IF IS_ATTACHED_PLAYER_HEADING_ACHIEVED PLAYER1
//				SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 0.0 360.0
				SET_HEADING_LIMIT_FOR_ATTACHED_CHAR scplayer FACING_FORWARD 60.0
				IF NOT IS_CAR_DEAD sweet_car
					SET_CAR_PROOFS sweet_car TRUE TRUE TRUE TRUE TRUE
				ENDIF
				SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
				DELETE_OBJECT advert_f1
				CREATE_OBJECT_NO_OFFSET md_poster 2167.82 -1518.193 20.237 advert_f1
				SET_OBJECT_HEADING advert_f1 0.0
				SET_OBJECT_COLLISION_DAMAGE_EFFECT advert_f1 FALSE
				FREEZE_OBJECT_POSITION advert_f1 TRUE

				turncamera_f1flag = 2
			ENDIF
		ENDIF
		//imran

		//heli setpiece
		IF copcars_f1flag = 8
			IF NOT IS_CHAR_DEAD copleft_f1
				IF NOT IS_CHAR_DEAD copright_f1
					IF IS_CHAR_TOUCHING_VEHICLE copleft_f1 sweet_car
					OR IS_CHAR_TOUCHING_VEHICLE copright_f1 sweet_car
						copleft_f1flag = 1
						copright_f1flag = 1
						copcars_f1flag = 9
						DELETE_CAR extpoliceheli_f1
						MARK_CHAR_AS_NO_LONGER_NEEDED exthelidriver_f1
						DELETE_SEARCHLIGHT helispotlight_f1
						CREATE_CAR POLMAV 2170.27 -1523.73 24.76 extpoliceheli_f1
						CREATE_CHAR_INSIDE_CAR extpoliceheli_f1 PEDTYPE_MISSION1 LAPD1 exthelidriver_f1
						SET_HELI_BLADES_FULL_SPEED extpoliceheli_f1
						START_PLAYBACK_RECORDED_CAR extpoliceheli_f1 381
						TIMERA = 0
						TIMERB = 0
						SET_CHAR_DECISION_MAKER exthelidriver_f1 motel_DM
						SET_CHAR_SUFFERS_CRITICAL_HITS exthelidriver_f1 FALSE
						SET_CHAR_CAN_BE_SHOT_IN_VEHICLE exthelidriver_f1 FALSE
						SET_CHAR_ONLY_DAMAGED_BY_PLAYER exthelidriver_f1 TRUE
						SET_CAR_ONLY_DAMAGED_BY_PLAYER extpoliceheli_f1 TRUE
						SET_CAR_PROOFS extpoliceheli_f1 TRUE TRUE TRUE TRUE TRUE
						CREATE_FX_SYSTEM blood_heli 2170.74 -1445.63 25.12 TRUE bloodfx1_f1
						CREATE_FX_SYSTEM blood_heli 2170.92 -1450.5 25.0 TRUE bloodfx2_f1
						CREATE_FX_SYSTEM blood_heli 2171.11 -1447.04 25.13 TRUE bloodfx3_f1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF copcars_f1flag = 9

			IF fence_f1flag = 0
				IF TIMERA > 14099
					PLAY_OBJECT_ANIM fence_f1 Fen_Choppa_R1 MD_CHASE 1000.0 FALSE TRUE
					PLAY_OBJECT_ANIM fencel_f1 Fen_Choppa_L2 MD_CHASE 1000.0 FALSE TRUE
					fence_f1flag = 1
				ENDIF
			ENDIF

			IF fence_f1flag = 1
				IF TIMERA > 14899
					PLAY_OBJECT_ANIM fence2_f1 Fen_Choppa_R2 MD_CHASE 1000.0 FALSE TRUE
					PLAY_OBJECT_ANIM fence2l_f1 Fen_Choppa_L3 MD_CHASE 1000.0 FALSE TRUE
					fence_f1flag = 2
				ENDIF
			ENDIF

			IF fence_f1flag = 2
				IF TIMERA > 15532
					PLAY_OBJECT_ANIM fence3_f1 Fen_Choppa_R3 MD_CHASE 1000.0 FALSE TRUE
					PLAY_OBJECT_ANIM fence3l_f1 Fen_Choppa_L1 MD_CHASE 1000.0 FALSE TRUE
					fence_f1flag = 3
				ENDIF
			ENDIF

//			IF fence_f1flag = 3
//				IF TIMERA > 16099
////					PLAY_OBJECT_ANIM fence4_f1 Fen_Choppa_R2 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence4l_f1 Fen_Choppa_L2 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 4
//				ENDIF
//			ENDIF
//
//			IF fence_f1flag = 4
//				IF TIMERA > 16666
////					PLAY_OBJECT_ANIM fence5_f1 Fen_Choppa_R1 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence5l_f1 Fen_Choppa_L3 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 5
//				ENDIF
//			ENDIF
//
//			IF fence_f1flag = 5
//				IF TIMERA > 17466
////					PLAY_OBJECT_ANIM fence6_f1 Fen_Choppa_R3 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence6l_f1 Fen_Choppa_L1 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 6
//				ENDIF
//			ENDIF
//
//			IF fence_f1flag = 6
//				IF TIMERA > 18266
////					PLAY_OBJECT_ANIM fence7_f1 Fen_Choppa_R1 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence7l_f1 Fen_Choppa_L2 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 7
//				ENDIF
//			ENDIF 
//
//			IF fence_f1flag = 7
//				IF TIMERA > 18899
////					PLAY_OBJECT_ANIM fence8_f1 Fen_Choppa_R2 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence8l_f1 Fen_Choppa_L3 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 8
//				ENDIF
//			ENDIF
//
//			IF fence_f1flag = 8
//				IF TIMERA > 19467
////					PLAY_OBJECT_ANIM fence9_f1 Fen_Choppa_R3 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence9l_f1 Fen_Choppa_L1 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 9
//				ENDIF
//			ENDIF
// 
//			IF fence_f1flag = 9
//				IF TIMERA > 20032
////					PLAY_OBJECT_ANIM fence10_f1 Fen_Choppa_R1 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence10l_f1 Fen_Choppa_L3 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 10
//				ENDIF
//			ENDIF
//
//			IF fence_f1flag = 10
//				IF TIMERA > 20866
////					PLAY_OBJECT_ANIM fence11_f1 Fen_Choppa_R3 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence11l_f1 Fen_Choppa_L2 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 11
//				ENDIF
//			ENDIF
//
//			IF fence_f1flag = 11
//				IF TIMERA > 21432
////					PLAY_OBJECT_ANIM fence12_f1 Fen_Choppa_R2 MD_CHASE 1000.0 FALSE TRUE
////					PLAY_OBJECT_ANIM fence12l_f1 Fen_Choppa_L1 MD_CHASE 1000.0 FALSE TRUE
//					fence_f1flag = 12
//				ENDIF
//			ENDIF

            
            		
			//tumble guy
			IF copleft_f1flag = 1
				IF NOT IS_CHAR_DEAD copleft_f1
					FREEZE_CHAR_POSITION copleft_f1 FALSE
					ATTACH_CHAR_TO_CAR copleft_f1 sweet_car -0.5 3.0 0.3 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
					TASK_PLAY_ANIM_NON_INTERRUPTABLE copleft_f1 Carhit_Tumble MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1				
					copleft_f1flag = 2
				ENDIF
			ENDIF
			IF copleft_f1flag = 2
				IF NOT IS_CHAR_DEAD copleft_f1
					IF IS_CHAR_PLAYING_ANIM copleft_f1 Carhit_Tumble
						GET_CHAR_ANIM_CURRENT_TIME copleft_f1 Carhit_Tumble animframebk_jmp
							IF animframebk_jmp > 0.7
								IF NOT IS_CAR_DEAD sweet_car
									DETACH_CHAR_FROM_CAR copleft_f1
									GET_CAR_COORDINATES sweet_car player_x player_y player_z
									player_x = player_x + 0.0
									player_y = player_y + -1.763
									player_z = player_z + -0.05
									SET_CHAR_COORDINATES copleft_f1 player_x player_y player_z
									REMOVE_CHAR_ELEGANTLY copleft_f1
									copleft_f1flag = 3
								ENDIF
							ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD copleft_f1 //heading
				GET_CAR_HEADING sweet_car copleft_f1heading
				SET_CHAR_HEADING copleft_f1 copleft_f1heading
			ENDIF
			//heli guy
			IF copright_f1flag = 1
				IF NOT IS_CHAR_DEAD copright_f1
					FREEZE_CHAR_POSITION copright_f1 FALSE
					ATTACH_CHAR_TO_CAR copright_f1 sweet_car 0.5 3.0 0.3 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
					TASK_PLAY_ANIM_NON_INTERRUPTABLE copright_f1 Carhit_Hangon MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1				
					copright_f1flag = 2
				ENDIF
			ENDIF
			IF copright_f1flag = 2
				IF NOT IS_CHAR_DEAD copright_f1
					IF IS_CHAR_PLAYING_ANIM copright_f1 Carhit_Hangon
						GET_CHAR_ANIM_CURRENT_TIME copright_f1 Carhit_Hangon animframebk_jmp
							IF animframebk_jmp = 1.0
								TASK_PLAY_ANIM_NON_INTERRUPTABLE copright_f1 Hangon_Stun_loop MD_CHASE 1000.0 TRUE FALSE FALSE FALSE -1
								copright_f1flag = 3
							ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD copright_f1 //heading
				GET_CAR_HEADING sweet_car copright_f1heading
				SET_CHAR_HEADING copright_f1 copright_f1heading
			ENDIF

			IF helisetpiece_f1flag = 0	//pause the heli
				IF TIMERB > 10499
					IF NOT IS_CAR_DEAD sweet_car
						IF IS_PLAYBACK_GOING_ON_FOR_CAR sweet_car
							PAUSE_PLAYBACK_RECORDED_CAR sweet_car
							TIMERB = 0
							helisetpiece_f1flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF helisetpiece_f1flag = 1 //play the turn around animation
				IF TIMERB > 3000
					IF copright_f1flag = 3	//turn around to see heli
						IF NOT IS_CHAR_DEAD copright_f1 //heading
							TASK_PLAY_ANIM_NON_INTERRUPTABLE copright_f1 Hangon_Stun_Turn MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1	
							TIMERB = 0
							copright_f1flag = 4
						ENDIF
					ENDIF
					helisetpiece_f1flag = 2
				ENDIF
			ENDIF


			IF helisetpiece_f1flag = 2 //play the turn around animation
				IF TIMERB > 2000
					IF IS_PLAYBACK_GOING_ON_FOR_CAR sweet_car
						UNPAUSE_PLAYBACK_RECORDED_CAR sweet_car
						DELETE_CAR policecarblock2_f1
						MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
						MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
						MARK_MODEL_AS_NO_LONGER_NEEDED LAPDM1
						MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
						MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
						MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
						DELETE_CHAR copleft_f1
						helisetpiece_f1flag = 3
					ENDIF
				ENDIF
			ENDIF



			IF helisetpiece_f1flag = 3
				IF TIMERB > 4300
					DO_CAMERA_BUMP 0.0 -1.0///////////////////////////////////////////////////// E3
					IF HAS_MISSION_AUDIO_LOADED 3
						PLAY_MISSION_AUDIO 3 //SOUND_HELI_SLASH_PED
					ENDIF
					PLAY_AND_KILL_FX_SYSTEM bloodfx1_f1
					CREATE_OBJECT chopcop_torso 2170.35 -1445.99 25.49 torso_f1
					SET_OBJECT_DYNAMIC torso_f1 TRUE
					CREATE_OBJECT chopcop_legr 2170.50 -1447.65 25.06 legr_f1
					SET_OBJECT_DYNAMIC legr_f1 TRUE
					DELETE_CHAR copright_f1
					DELETE_OBJECT fence_f1  
					DELETE_OBJECT fence2l_f1 
					DELETE_OBJECT fence3l_f1 
//					DELETE_OBJECT fence4l_f1 
//					DELETE_OBJECT fence5l_f1 
					DELETE_OBJECT fencel_f1  
					DELETE_OBJECT fence2l_f1 
					DELETE_OBJECT fence3l_f1 
//					DELETE_OBJECT fence4l_f1 
//					DELETE_OBJECT fence5l_f1 
					helisetpiece_f1flag = 4
				ENDIF
			ENDIF

			IF helisetpiece_f1flag = 4
				IF TIMERB > 4800
					actiontext_f1flag = 9
					PLAY_AND_KILL_FX_SYSTEM bloodfx2_f1
					CREATE_OBJECT chopcop_legr 2170.99 -1448.51 25.08 legl_f1
					SET_OBJECT_DYNAMIC legl_f1 TRUE
					CREATE_OBJECT chopcop_armr 2170.96 -1450.36 25.2 armr_f1
					SET_OBJECT_DYNAMIC armr_f1 TRUE
					SET_OBJECT_ROTATION armr_f1 0.2 0.2 0.2
					CREATE_OBJECT chopcop_armr 2171.16 -1451.11 25.12 arml_f1
					SET_OBJECT_DYNAMIC arml_f1 TRUE
					SET_OBJECT_ROTATION arml_f1 -0.1 0.05 0.1
					CREATE_OBJECT chopcop_head 2171.1 -1453.09 25.4 head_f1
					SET_OBJECT_DYNAMIC head_f1 TRUE
					SET_OBJECT_ROTATION head_f1 0.5 0.2 -0.1
					PLAY_AND_KILL_FX_SYSTEM bloodfx3_f1
					IF NOT IS_CAR_DEAD extpoliceheli_f1
						STOP_PLAYBACK_RECORDED_CAR extpoliceheli_f1
					ENDIF
					helisetpiece_f1flag = 5
				ENDIF
			ENDIF

			IF helisetpiece_f1flag = 5
				IF TIMERB > 5800
					SET_FIXED_CAMERA_POSITION 2169.8989 -1543.4972 38.7440 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2169.8938 -1542.5510 38.4204 JUMP_CUT
					SWITCH_WIDESCREEN ON
					DETACH_CHAR_FROM_CAR scplayer
					SET_CHAR_COORDINATES scplayer 2184.87 -1500.83 23.55
					SET_PLAYER_CONTROL PLAYER1 OFF
					IF NOT IS_CAR_DEAD extpoliceheli_f1
						SET_CAR_COORDINATES extpoliceheli_f1 2172.56 -1420.69 30.0
						EXPLODE_CAR extpoliceheli_f1
						IF NOT IS_CAR_DEAD extpoliceheli_f1
							EXPLODE_CAR extpoliceheli_f1
						ENDIF
						DELETE_CHAR exthelidriver_f1
					ENDIF
					TIMERB = 0
					helisetpiece_f1flag = 6
				ENDIF
			ENDIF



		ENDIF																			   
						
		//clear up cars
		IF helileave_f1flag = 1
			IF NOT IS_CAR_DEAD extpoliceheli_f1
				MARK_CAR_AS_NO_LONGER_NEEDED extpoliceheli_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED exthelidriver_f1
				DELETE_SEARCHLIGHT helispotlight_f1
				helileave_f1flag = 2				
			ENDIF
		ENDIF

		IF policecar1_f1flag = 1
			IF NOT IS_CAR_DEAD policecar1_f1
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar1_f1
					MARK_CAR_AS_NO_LONGER_NEEDED policecar1_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop1_f1
					REMOVE_BLIP policecar1_f1blip 
					policecar1_f1flag = 2
				ENDIF
			ELSE
				STOP_PLAYBACK_RECORDED_CAR policecar1_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop1_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar1_f1
				REMOVE_BLIP policecar1_f1blip 
				policecar1_f1flag = 2
			ENDIF
		ENDIF

		//swap seat
		IF policecar2swap_f1flag = 1
			IF NOT IS_CAR_DEAD policecar2_f1
				IF NOT IS_CHAR_DEAD cop2_f1
				ELSE
					IF NOT IS_CHAR_DEAD cop3_f1
						IF IS_CHAR_IN_CAR cop3_f1 policecar2_f1
							GET_DRIVER_OF_CAR policecar2_f1 driverofcar_f1
							IF driverofcar_f1 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop3_f1 policecar2_f1
								policecar2swap_f1flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR policecar2_f1
						policecar2swap_f1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF policecar2swap_f1flag = 2
			IF IS_CHAR_DEAD cop3_f1
				STOP_PLAYBACK_RECORDED_CAR policecar2_f1
				policecar2swap_f1flag = 3
			ENDIF 
		ENDIF

		IF policecar2_f1flag = 1
			IF NOT IS_CAR_DEAD policecar2_f1
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar2_f1
					MARK_CAR_AS_NO_LONGER_NEEDED policecar2_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop2_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop3_f1
					REMOVE_BLIP policecar2_f1blip 
					policecar2_f1flag = 2
				ENDIF
			ELSE
				STOP_PLAYBACK_RECORDED_CAR policecar2_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop2_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop3_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar2_f1
				REMOVE_BLIP policecar2_f1blip 
				policecar2_f1flag = 2
			ENDIF
		ENDIF

		//swap seat
		IF policecar3swap_f1flag = 1
			IF NOT IS_CAR_DEAD policecar3_f1
				IF NOT IS_CHAR_DEAD cop4_f1
				ELSE
					IF NOT IS_CHAR_DEAD cop5_f1
						IF IS_CHAR_IN_CAR cop5_f1 policecar3_f1
							GET_DRIVER_OF_CAR policecar3_f1 driverofcar_f1
							IF driverofcar_f1 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop5_f1 policecar3_f1
								policecar3swap_f1flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR policecar3_f1
						policecar3swap_f1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF policecar3swap_f1flag = 2
			IF IS_CHAR_DEAD cop5_f1
				STOP_PLAYBACK_RECORDED_CAR policecar3_f1
				policecar3swap_f1flag = 3
			ENDIF 
		ENDIF

		IF policecar3_f1flag = 1
			IF NOT IS_CAR_DEAD policecar3_f1
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar3_f1
					MARK_CAR_AS_NO_LONGER_NEEDED policecar3_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop4_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop5_f1
					REMOVE_BLIP policecar3_f1blip 
					policecar3_f1flag = 2
				ENDIF
			ELSE
				STOP_PLAYBACK_RECORDED_CAR policecar3_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop4_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop5_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar3_f1
				REMOVE_BLIP policecar3_f1blip 
				policecar3_f1flag = 2
			ENDIF
		ENDIF

		IF policecar4_f1flag = 1
			IF IS_CAR_DEAD policecar4_f1
				REMOVE_BLIP policecar4_f1blip
				MARK_CHAR_AS_NO_LONGER_NEEDED cop6_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar4_f1
				policecar4_f1flag = 2
			ENDIF
		ENDIF

		//swap seat
		IF policecar5swap_f1flag = 1
			IF NOT IS_CAR_DEAD policecar5_f1
				IF NOT IS_CHAR_DEAD cop9_f1
				ELSE
					IF NOT IS_CHAR_DEAD cop10_f1
						IF IS_CHAR_IN_CAR cop10_f1 policecar5_f1
							GET_DRIVER_OF_CAR policecar5_f1 driverofcar_f1
							IF driverofcar_f1 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop10_f1 policecar5_f1
								policecar5swap_f1flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR policecar5_f1
						policecar5swap_f1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF policecar5swap_f1flag = 2
			IF IS_CHAR_DEAD cop10_f1
				STOP_PLAYBACK_RECORDED_CAR policecar5_f1
				policecar5swap_f1flag = 3
			ENDIF 
		ENDIF

		IF policecar5_f1flag = 1
			IF NOT IS_CAR_DEAD policecar5_f1
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar5_f1
					MARK_CAR_AS_NO_LONGER_NEEDED policecar5_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop9_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop10_f1
					REMOVE_BLIP policecar5_f1blip 
					policecar5_f1flag = 2
				ENDIF
			ELSE
				STOP_PLAYBACK_RECORDED_CAR policecar5_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop9_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop10_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar5_f1
				REMOVE_BLIP policecar5_f1blip 
				policecar5_f1flag = 2
			ENDIF
		ENDIF

		//swap seat
		IF policecar6swap_f1flag = 1
			IF NOT IS_CAR_DEAD policecar6_f1
				IF NOT IS_CHAR_DEAD cop12_f1
				ELSE
					IF NOT IS_CHAR_DEAD cop13_f1
						IF IS_CHAR_IN_CAR cop13_f1 policecar6_f1
							GET_DRIVER_OF_CAR policecar6_f1 driverofcar_f1
							IF driverofcar_f1 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop13_f1 policecar6_f1
								policecar6swap_f1flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR policecar6_f1
						policecar6swap_f1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF policecar6swap_f1flag = 2
			IF IS_CHAR_DEAD cop13_f1
				STOP_PLAYBACK_RECORDED_CAR policecar6_f1
				policecar6swap_f1flag = 3
			ENDIF 
		ENDIF

		IF policecar6_f1flag = 1
			IF NOT IS_CAR_DEAD policecar6_f1
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar6_f1
					MARK_CAR_AS_NO_LONGER_NEEDED policecar6_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop12_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop13_f1
					REMOVE_BLIP policecar6_f1blip 
					policecar6_f1flag = 2
				ENDIF
			ELSE
				STOP_PLAYBACK_RECORDED_CAR policecar6_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop12_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop13_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar6_f1
				REMOVE_BLIP policecar6_f1blip 
				policecar6_f1flag = 2
			ENDIF
		ENDIF

		//swap seat
//		IF policecar7swap_f1flag = 1
//			IF NOT IS_CAR_DEAD policecar7_f1
//				IF NOT IS_CHAR_DEAD cop14_f1
//				ELSE
//					IF NOT IS_CHAR_DEAD cop15_f1
//						IF IS_CHAR_IN_CAR cop15_f1 policecar7_f1
//							GET_DRIVER_OF_CAR policecar7_f1 driverofcar_f1
//							IF driverofcar_f1 = -1
//								TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop15_f1 policecar7_f1
//								policecar7swap_f1flag = 2
//							ENDIF
//						ENDIF
//					ELSE
//						STOP_PLAYBACK_RECORDED_CAR policecar7_f1
//						policecar7swap_f1flag = 2
//					ENDIF
//				ENDIF
//			ENDIF
//		ENDIF
//		IF policecar7swap_f1flag = 2
//			IF IS_CHAR_DEAD cop15_f1
//				STOP_PLAYBACK_RECORDED_CAR policecar7_f1
//				policecar7swap_f1flag = 3
//			ENDIF 
//		ENDIF
//
//		IF policecar7_f1flag = 1
//			IF NOT IS_CAR_DEAD policecar7_f1
//				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar7_f1
//					MARK_CAR_AS_NO_LONGER_NEEDED policecar7_f1
//					MARK_CHAR_AS_NO_LONGER_NEEDED cop14_f1
//					MARK_CHAR_AS_NO_LONGER_NEEDED cop15_f1
//					REMOVE_BLIP policecar7_f1blip 
//					policecar7_f1flag = 2
//				ENDIF
//			ELSE
//				STOP_PLAYBACK_RECORDED_CAR policecar7_f1
//				MARK_CHAR_AS_NO_LONGER_NEEDED cop14_f1
//				MARK_CHAR_AS_NO_LONGER_NEEDED cop15_f1
//				MARK_CAR_AS_NO_LONGER_NEEDED policecar7_f1
//				REMOVE_BLIP policecar7_f1blip 
//				policecar7_f1flag = 2
//			ENDIF
//		ENDIF

		//swap seat
		IF policecar8swap_f1flag = 1
			IF NOT IS_CAR_DEAD policecar8_f1
				IF NOT IS_CHAR_DEAD cop7_f1
				ELSE
					IF NOT IS_CHAR_DEAD cop8_f1
						IF IS_CHAR_IN_CAR cop8_f1 policecar8_f1
							GET_DRIVER_OF_CAR policecar8_f1 driverofcar_f1
							IF driverofcar_f1 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT cop8_f1 policecar8_f1
								policecar8swap_f1flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR policecar8_f1
						policecar8swap_f1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF policecar8swap_f1flag = 2
			IF IS_CHAR_DEAD cop8_f1
				STOP_PLAYBACK_RECORDED_CAR policecar8_f1
				policecar8swap_f1flag = 3
			ENDIF 
		ENDIF

		IF policecar8_f1flag = 1
			IF NOT IS_CAR_DEAD policecar8_f1
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR policecar8_f1
					MARK_CAR_AS_NO_LONGER_NEEDED policecar8_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop7_f1
					MARK_CHAR_AS_NO_LONGER_NEEDED cop8_f1
					REMOVE_BLIP policecar8_f1blip
					IF policecar4_f1flag = 1
						IF IS_CAR_DEAD policecar4_f1
							REMOVE_BLIP policecar4_f1blip
							MARK_CHAR_AS_NO_LONGER_NEEDED cop6_f1
							MARK_CAR_AS_NO_LONGER_NEEDED policecar8_f1
							policecar4_f1flag = 2
						ENDIF
					ENDIF
					policecar8_f1flag = 2
				ENDIF
			ELSE
				STOP_PLAYBACK_RECORDED_CAR policecar8_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop7_f1
				MARK_CHAR_AS_NO_LONGER_NEEDED cop8_f1
				MARK_CAR_AS_NO_LONGER_NEEDED policecar8_f1
				REMOVE_BLIP policecar8_f1blip 
				IF policecar4_f1flag = 1
					IF IS_CAR_DEAD policecar4_f1
						REMOVE_BLIP policecar4_f1blip
						MARK_CHAR_AS_NO_LONGER_NEEDED cop6_f1
						MARK_CAR_AS_NO_LONGER_NEEDED policecar8_f1
						policecar4_f1flag = 2
					ENDIF
				ENDIF
				policecar8_f1flag = 2
			ENDIF
		ENDIF


		///////////////////////////////////////////////////	Second Biker jumping
		///////////////////////////////////////////////////	Second Biker jumping
		///////////////////////////////////////////////////	Second Biker jumping		
		///////////////////////////////////////////////////	Second Biker jumping
		//keep him in the seated anim
		IF bikerjumper4_f1flag = 1	
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD bikerjumper4_f1
					IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_Jmp_BL
						GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_Jmp_BL animframebk_jmp
							IF animframebk_jmp = 0.0
								SET_CHAR_ANIM_SPEED bikerjumper4_f1 MD_BIKE_Jmp_BL 0.0
								bikerjumper4_f1flag = 2
							ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//biker jumps
		IF bikerjumper4_f1flag = 3
			IF TIMERA > 3200 //3400
				IF NOT IS_CHAR_DEAD bikerjumper4_f1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_BIKE_Jmp_BL MD_CHASE 1.0 FALSE FALSE FALSE TRUE -1
					SET_CHAR_ANIM_SPEED bikerjumper4_f1 MD_BIKE_Jmp_BL 1.0
					actiontext_f1flag = 7
					bikerjumper4_f1flag = 4
				ENDIF
			ENDIF
		ENDIF
		
		//attach to the car
		IF bikerjumper4_f1flag = 4
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD bikerjumper4_f1
					IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_Jmp_BL
						GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_Jmp_BL animframebk_jmp
							IF animframebk_jmp = 1.0
								DETACH_CHAR_FROM_CAR bikerjumper4_f1
								IF playdeathanim2_f1flag = 1
									ATTACH_CHAR_TO_CAR bikerjumper4_f1 sweet_car -0.23 -4.0 0.3 FACING_BACK 360.0 WEAPONTYPE_UNARMED
									TASK_PLAY_ANIM bikerjumper4_f1 MD_BIKE_Lnd_Die_BL MD_CHASE 8.0 FALSE FALSE FALSE TRUE -1
									bikerjumper4_f1flag = 100
								ELSE
									TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_BIKE_Lnd_BL MD_CHASE 1000.0 FALSE FALSE FALSE TRUE -1
									ATTACH_CHAR_TO_CAR bikerjumper4_f1 sweet_car -0.23 -4.0 0.3 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
									TIMERA = 0
									bikerjumper4_f1flag = 5
								ENDIF
								IF NOT IS_CAR_DEAD copbike4_f1
									STOP_PLAYBACK_RECORDED_CAR copbike4_f1
									SET_CAR_ROTATION_VELOCITY copbike4_f1 -2.0 3.0 0.2003
								ENDIF
							ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//punch player
		IF bikerjumper4_f1flag = 5
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD bikerjumper4_f1
					IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_Lnd_BL
						GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_Lnd_BL animframebk_jmp
							IF animframebk_jmp > 0.037
								TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_BIKE_Punch MD_CHASE 8.0 TRUE FALSE FALSE FALSE -1
								bikerjumper4_f1flag = 6
							ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//set peds heading when on bike	
		IF bikerjumper4_f1flag > 0
			IF bikerjumper4_f1flag < 4
				IF NOT IS_CAR_DEAD copbike4_f1
					IF NOT IS_CHAR_DEAD bikerjumper4_f1
						GET_CAR_HEADING copbike4_f1 bikerjumper4_f1heading
						SET_CHAR_HEADING bikerjumper4_f1 bikerjumper4_f1heading
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//set peds heading when on bike	
		IF bikerjumper4_f1flag = 4
			IF NOT IS_CHAR_DEAD bikerjumper4_f1
				IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_Jmp_BL
					GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_Jmp_BL animframebk_jmp
					IF animframebk_jmp > 0.66
						IF NOT IS_CAR_DEAD sweet_car
							GET_CAR_HEADING sweet_car bikerjumper4_f1heading
							SET_CHAR_HEADING bikerjumper4_f1 bikerjumper4_f1heading
						ENDIF
					ELSE
						IF NOT IS_CAR_DEAD copbike4_f1
							GET_CAR_HEADING copbike4_f1 bikerjumper4_f1heading
							SET_CHAR_HEADING bikerjumper4_f1 bikerjumper4_f1heading
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//set peds heading when on bike	
		IF bikerjumper4_f1flag > 4
			IF NOT IS_CAR_DEAD sweet_car
				IF NOT IS_CHAR_DEAD bikerjumper4_f1
					GET_CAR_HEADING sweet_car bikerjumper4_f1heading
					SET_CHAR_HEADING bikerjumper4_f1 bikerjumper4_f1heading
				ENDIF
			ENDIF
		ENDIF


		//camera bump
		IF NOT IS_CHAR_DEAD bikerjumper4_f1
			IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_Punch
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_Punch animframebk_punch
					IF animframebk_punch > 0.2
						IF bikerjumper4_f1flag = 6
							DO_CAMERA_BUMP -2.2 1.7
							DAMAGE_CHAR scplayer 10 TRUE
							bikerjumper4_f1flag = 7
						ENDIF
					ELSE
						bikerjumper4_f1flag = 6
					ENDIF
			ENDIF
		ENDIF

		//if is char dead
		IF NOT IS_CHAR_DEAD bikerjumper4_f1
			GET_CHAR_HEALTH bikerjumper4_f1 bikerjumper4_f1health
				IF bikerjumper4_f1health < 901
					SET_CHAR_PROOFS bikerjumper4_f1 TRUE TRUE TRUE TRUE TRUE
					playdeathanim2_f1flag = 1
				ENDIF
		ENDIF

		//char killed before landing
		IF bikerjumper4_f1flag = 100
			IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_Lnd_Die_BL
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_Lnd_Die_BL animframebk_jmp
					IF animframebk_jmp = 1.0
						DETACH_CHAR_FROM_CAR bikerjumper4_f1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_BIKE_Lnd_Roll MD_CHASE 8.0 FALSE FALSE FALSE TRUE -1
						bikerjumper4_f1flag = 101
					ENDIF
			ENDIF
		ENDIF
		IF bikerjumper4_f1flag = 101
			IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_Lnd_Roll
				GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_Lnd_Roll animframebk_jmp
					IF animframebk_jmp = 1.0
						REMOVE_CHAR_ELEGANTLY bikerjumper4_f1
						bikerjumper4_f1flag = 102
					ENDIF
			ENDIF
		ENDIF
		
		//char killed by hanging onto car
   		IF bikerjumper4_f1flag > 4
			IF bikerjumper4_f1flag < 8
			    IF playdeathanim2_f1flag = 1
				OR TIMERA > 12000
					bikerjumper4_f1flag = 200
				ENDIF
			ENDIF
		ENDIF

		//char killed before landing
		IF bikerjumper4_f1flag = 200
			IF NOT IS_CHAR_DEAD bikerjumper4_f1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_BIKE_2_HANG MD_CHASE 8.0 FALSE FALSE FALSE TRUE -1
				TIMERA = 0
				bikerjumper4_f1flag = 201
			ENDIF
		ENDIF

		IF bikerjumper4_f1flag = 201
			IF NOT IS_CHAR_DEAD bikerjumper4_f1
				IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_2_HANG
					GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_BIKE_2_HANG animframebk_jmp
						IF animframebk_jmp = 1.0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_HANG_LOOP MD_CHASE 8.0 TRUE FALSE FALSE FALSE -1
							bikerjumper4_f1flag = 202
						ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF bikerjumper4_f1flag= 202
			IF NOT IS_CHAR_DEAD bikerjumper4_f1
				IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_BIKE_2_HANG
					SET_CHAR_PROOFS bikerjumper4_f1 FALSE FALSE FALSE FALSE FALSE
					CLEAR_CHAR_LAST_DAMAGE_ENTITY bikerjumper4_f1
					bikerjumper4_f1flag= 203
				ENDIF
			ENDIF
		ENDIF

		IF bikerjumper4_f1flag= 203
			IF NOT IS_CHAR_DEAD	bikerjumper4_f1
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR bikerjumper4_f1 scplayer
				OR TIMERA > 5000
					DETACH_CHAR_FROM_CAR bikerjumper4_f1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE bikerjumper4_f1 MD_HANG_LND_ROLL MD_CHASE 8.0 FALSE FALSE FALSE TRUE -1
					bikerjumper4_f1flag= 204
				ENDIF
			ENDIF
		ENDIF

		IF bikerjumper4_f1flag = 204
			IF NOT IS_CHAR_DEAD bikerjumper4_f1
				IF IS_CHAR_PLAYING_ANIM bikerjumper4_f1 MD_HANG_LND_ROLL
					GET_CHAR_ANIM_CURRENT_TIME bikerjumper4_f1 MD_HANG_LND_ROLL animframebk_jmp
						IF animframebk_jmp = 1.0
							DELETE_CHAR bikerjumper4_f1
							bikerjumper4_f1flag = 205
						ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF
ENDIF


//ending
IF helisetpiece_f1flag = 6
	IF TIMERB > 3000
		IF NOT IS_CAR_DEAD sweet_car
			DELETE_CAR extpoliceheli_f1
			MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
			MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
			MARK_MODEL_AS_NO_LONGER_NEEDED LAPDM1
			MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
			MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
			MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
			MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_arml
			MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_armr
			MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_legl
			MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_legr
			MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_head
			MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_torso
			DELETE_OBJECT torso_f1
			DELETE_OBJECT legr_f1
			DELETE_OBJECT legl_f1
			DELETE_OBJECT armr_f1
			DELETE_OBJECT arml_f1
			DELETE_OBJECT head_f1
			DELETE_OBJECT fence_f1  
			DELETE_OBJECT fence2l_f1 
			DELETE_OBJECT fence3l_f1 
			DELETE_OBJECT fencel_f1  
			DELETE_OBJECT fence2l_f1 
			DELETE_OBJECT fence3l_f1 
			DELETE_CAR extpoliceheli_f1
			KILL_FX_SYSTEM jetwashfx1_f1
			KILL_FX_SYSTEM bloodfx1_f1
			KILL_FX_SYSTEM bloodfx2_f1
			KILL_FX_SYSTEM bloodfx3_f1
			REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_AK47
			MARK_MODEL_AS_NO_LONGER_NEEDED AK47
			REMOVE_ANIMATION MD_CHASE
			
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_MISSION_AUDIO 3
			CLEAR_PRINTS

			REQUEST_CAR_RECORDING 351
			REQUEST_CAR_RECORDING 382
			REQUEST_CAR_RECORDING 383
			REQUEST_CAR_RECORDING 384

			REQUEST_MODEL petro
			REQUEST_MODEL PETROTR

			LOAD_SPECIAL_CHARACTER 1 SWEET
			LOAD_SPECIAL_CHARACTER 2 SMOKE
			LOAD_SPECIAL_CHARACTER 3 RYDER2

			REQUEST_ANIMATION MD_END

			LOAD_MISSION_AUDIO 1 SOUND_FIN1_MU //Shit!  That’s gonna be one hell of a story to tell when we passin’ the blunt!
			LOAD_MISSION_AUDIO 2 SOUND_FIN1_MV //Man, that was some serious shit! Woo!
			LOAD_MISSION_AUDIO 3 SOUND_CAR_SMASH_SIGN

			LOAD_ALL_MODELS_NOW

			SET_FIXED_CAMERA_POSITION 2177.3069 -1535.9014 17.7635 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2176.8547 -1535.1426 18.2324 JUMP_CUT

			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
				OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
				OR NOT HAS_MISSION_AUDIO_LOADED 1
				OR NOT HAS_MISSION_AUDIO_LOADED 2
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED petro
				OR NOT HAS_MODEL_LOADED PETROTR
				OR NOT HAS_MISSION_AUDIO_LOADED 3
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 351
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 382
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 383
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 384
				OR NOT HAS_ANIMATION_LOADED MD_END
				WAIT 0
			ENDWHILE
			
			IF NOT IS_CAR_DEAD sweet_car
				STOP_PLAYBACK_RECORDED_CAR sweet_car
			ENDIF

			CREATE_CAR PETRO 2078.002 -1524.3489 2.49 truck_f1
			SET_CAR_HEADING truck_f1 254.103
			SET_CAR_HEALTH truck_f1 5000
			SET_CAR_PROOFS truck_f1 TRUE TRUE TRUE TRUE TRUE

			CREATE_CAR PETROTR 2070.05 -1522.059 3.668 trailer_f1
			SET_CAR_HEADING trailer_f1 254.0
			SET_CAR_HEALTH trailer_f1 5000
			SET_CAR_PROOFS trailer_f1 TRUE TRUE TRUE TRUE TRUE

			CREATE_CAR GREENWOO 2060.05 -1520.059 3.668 car_f1
			SET_CAR_HEALTH car_f1 5000
			SET_CAR_PROOFS car_f1 TRUE TRUE TRUE TRUE TRUE
			CHANGE_CAR_COLOUR car_f1 CARCOLOUR_TEMPLECURTAINPURPLE CARCOLOUR_TEMPLECURTAINPURPLE

			SWITCH_PED_ROADS_OFF 2184.44 -1557.18 -5.481 2169.22 -1560.63 5.91 
			SET_CAR_DENSITY_MULTIPLIER 1.0
			CLEAR_ONSCREEN_COUNTER carhealth_f1
			TIMERB = 0
			helisetpiece_f1flag = 7

		ENDIF
	ENDIF
ENDIF

IF helisetpiece_f1flag > 6
	IF breakposter_f1flag = 0
		IF TIMERB > 1989
			IF DOES_OBJECT_EXIST advert_f1
				FREEZE_OBJECT_POSITION advert_f1 FALSE
				SET_OBJECT_COLLISION_DAMAGE_EFFECT advert_f1 TRUE
				BREAK_OBJECT advert_f1 TRUE
				PLAY_MISSION_AUDIO 3 //SOUND_CAR_SMASH_SIGN
			ENDIF
			breakposter_f1flag = 1
		ENDIF
	ENDIF
ENDIF


IF helisetpiece_f1flag = 7
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CAR_DEAD truck_f1
			IF NOT IS_CAR_DEAD trailer_f1
				START_PLAYBACK_RECORDED_CAR sweet_car 351
				START_PLAYBACK_RECORDED_CAR truck_f1 382
				START_PLAYBACK_RECORDED_CAR trailer_f1 383
				IF NOT IS_CAR_DEAD car_f1
					START_PLAYBACK_RECORDED_CAR car_f1 384
				ENDIF
				helisetpiece_f1flag = 8
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF helisetpiece_f1flag = 8
	IF NOT IS_CAR_DEAD sweet_car
		IF LOCATE_CAR_2D sweet_car 2170.2 -1518.04 5.0 5.0 FALSE
			SET_TIME_SCALE 0.3
			POINT_CAMERA_AT_CAR sweet_car FIXED INTERPOLATION
			OPEN_CAR_DOOR sweet_car FRONT_LEFT_DOOR
			OPEN_CAR_DOOR sweet_car REAR_LEFT_DOOR
			OPEN_CAR_DOOR sweet_car REAR_RIGHT_DOOR
			OPEN_CAR_DOOR sweet_car BONNET
			TIMERA = 0
			helisetpiece_f1flag = 9
		ENDIF
	ENDIF
ENDIF

IF helisetpiece_f1flag = 9
	IF TIMERA > 500
		IF NOT IS_CAR_DEAD sweet_car
//			POP_CAR_PANEL sweet_car FRONT_RIGHT_PANEL TRUE
			POP_CAR_PANEL sweet_car REAR_BUMPER TRUE
			SET_CAR_PROOFS sweet_car TRUE TRUE TRUE TRUE TRUE
			IF NOT IS_CAR_DEAD trailer_f1
				SET_CAR_PROOFS trailer_f1 TRUE TRUE TRUE TRUE TRUE
			ENDIF
			IF NOT IS_CAR_DEAD truck_f1
				SET_CAR_PROOFS truck_f1 TRUE TRUE TRUE TRUE TRUE
			ENDIF
			TIMERA = 0
			helisetpiece_f1flag = 10
		ENDIF
	ENDIF
ENDIF

IF helisetpiece_f1flag = 10
	IF NOT IS_CAR_DEAD sweet_car
		IF LOCATE_CAR_3D sweet_car 2169.24 -1553.91 2.5 10.0 10.0 6.0 FALSE
		OR TIMERA > 4000
			SET_CAR_HEALTH sweet_car 300
			SET_TIME_SCALE 0.7
			POP_CAR_PANEL sweet_car FRONT_BUMPER TRUE
			SET_TIME_OF_DAY 07 15
			SET_FIXED_CAMERA_POSITION 2204.8037 -1568.2760 1.9452 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2203.8606 -1567.9508 1.8744 JUMP_CUT
			SET_NEAR_CLIP 0.1

			CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2168.479 -1506.067 24.0 sweet
			SET_CHAR_HEADING sweet 135.0
			SET_CHAR_DECISION_MAKER sweet motel_DM
			SET_CHAR_PROOFS sweet TRUE TRUE TRUE TRUE TRUE

			CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 2168.992 -1508.601 24.0 big_smoke
			SET_CHAR_HEADING big_smoke 45.0
			SET_CHAR_DECISION_MAKER big_smoke motel_DM
			SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE

			CREATE_CHAR PEDTYPE_SPECIAL SPECIAL03 2167.054 -1507.7 24.0 ryder
			SET_CHAR_HEADING ryder 290.0
			SET_CHAR_DECISION_MAKER ryder motel_DM
			SET_CHAR_PROOFS ryder TRUE TRUE TRUE TRUE TRUE

			SET_CHAR_COORDINATES scplayer 2166.952 -1506.237 24.0
			SET_CHAR_HEADING scplayer 225.0

			TIMERA = 0
			helisetpiece_f1flag = 11
		ENDIF
	ENDIF
ENDIF

IF helisetpiece_f1flag = 11
	IF NOT IS_CAR_DEAD trailer_f1
		IF NOT IS_CAR_DEAD sweet_car
			IF NOT IS_CAR_DEAD truck_f1
				IF IS_CAR_TOUCHING_CAR sweet_car truck_f1
				OR TIMERB > 4350
//					IF NOT IS_CAR_DEAD trailer_f1
//						SET_CAR_PROOFS trailer_f1 FALSE FALSE FALSE FALSE FALSE
//					ENDIF
//					IF NOT IS_CAR_DEAD truck_f1
//						SET_CAR_PROOFS truck_f1 FALSE FALSE FALSE FALSE FALSE
//					ENDIF
//					IF NOT IS_CAR_DEAD sweet_car
//						SET_CAR_PROOFS sweet_car FALSE FALSE FALSE FALSE FALSE
//					ENDIF

					GET_CAR_COORDINATES sweet_car player_x player_y player_z
					ADD_EXPLOSION player_x player_y player_z EXPLOSION_GRENADE
					EXPLODE_CAR sweet_car
					EXPLODE_CAR trailer_f1
					SET_CAR_PROOFS truck_f1 TRUE TRUE TRUE TRUE TRUE
					ADD_EXPLOSION 2180.27 -1564.66 3.23 EXPLOSION_CAR
					ADD_EXPLOSION 2182.25 -1565.23 2.88 EXPLOSION_GRENADE

					ADD_EXPLOSION 2166.34 -1559.97 6.28 EXPLOSION_ROCKET
					ADD_EXPLOSION 2173.52 -1560.03 5.0 EXPLOSION_CAR

					ADD_EXPLOSION 2182.65 -1565.14 4.01 EXPLOSION_ROCKET
					ADD_EXPLOSION 2183.8 -1564.48 4.05 EXPLOSION_GRENADE
					TIMERA = 0
					helisetpiece_f1flag = 12
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF helisetpiece_f1flag = 12
	IF TIMERA > 250
		ADD_EXPLOSION 2187.94 -1562.73 2.85 EXPLOSION_GRENADE		
		ADD_EXPLOSION 2172.99 -1561.51 4.88 EXPLOSION_GRENADE
		ADD_EXPLOSION 2172.9 -1561.64 2.7 EXPLOSION_CAR
		helisetpiece_f1flag = 13
	ENDIF
ENDIF

IF helisetpiece_f1flag = 13
	IF TIMERA > 750

		START_SCRIPT_FIRE 2180.27 -1564.66 1.93 1 3 fire4_f1
		START_SCRIPT_FIRE 2182.25 -1565.23 1.88 1 3 fire5_f1
		START_SCRIPT_FIRE 2187.94 -1562.73 1.36 1 3 fire6_f1 

		START_SCRIPT_FIRE 2204.8037 -1568.4760 1.95 1 1 fire_f1
		START_SCRIPT_FIRE 2201.11 -1565.77 1.36 1 1 fire2_f1
		START_SCRIPT_FIRE 2202.82 -1565.01 1.35 1 3 fire3_f1

		ADD_EXPLOSION 2175.12 -1562.63 5.93 EXPLOSION_CAR
		ADD_EXPLOSION 2179.24 -1563.66 6.42 EXPLOSION_GRENADE
		ADD_EXPLOSION 2170.39 -1560.61 5.91 EXPLOSION_CAR

		ADD_EXPLOSION 2187.94 -1562.73 2.85 EXPLOSION_CAR
		ADD_EXPLOSION 2180.27 -1564.66 3.23 EXPLOSION_CAR
		ADD_EXPLOSION 2172.9 -1561.64 2.7 EXPLOSION_CAR
		ADD_EXPLOSION 2171.73 -1562.22 5.92 EXPLOSION_CAR

		ADD_EXPLOSION 2179.3 -1563.32 6.12 EXPLOSION_ROCKET

		ADD_EXPLOSION 2177.35 -1562.66 5.73 EXPLOSION_GRENADE

		ADD_EXPLOSION 2174.04 -1562.21 6.56 EXPLOSION_GRENADE
		ADD_EXPLOSION 2178.27 -1564.89 6.58 EXPLOSION_ROCKET
		ADD_EXPLOSION 2177.53 -1564.16 6.96 EXPLOSION_CAR

		helisetpiece_f1flag = 14
	ENDIF
ENDIF

IF helisetpiece_f1flag = 14
	IF TIMERA > 1250
		ADD_EXPLOSION 2166.34 -1559.97 6.28 EXPLOSION_ROCKET
		ADD_EXPLOSION 2173.52 -1560.03 5.0 EXPLOSION_CAR

		ADD_EXPLOSION 2182.65 -1565.14 4.01 EXPLOSION_ROCKET
		ADD_EXPLOSION 2183.8 -1564.48 4.05 EXPLOSION_GRENADE
		helisetpiece_f1flag = 15
	ENDIF
ENDIF

IF helisetpiece_f1flag = 15
	IF TIMERA > 1750
		ADD_EXPLOSION 2175.12 -1562.63 5.93 EXPLOSION_CAR
		ADD_EXPLOSION 2179.24 -1563.66 6.42 EXPLOSION_GRENADE
		ADD_EXPLOSION 2170.39 -1560.61 5.91 EXPLOSION_CAR

		IF NOT IS_CAR_DEAD trailer_f1
			SET_CAR_PROOFS trailer_f1 FALSE FALSE FALSE FALSE FALSE
			EXPLODE_CAR trailer_f1
		ENDIF
		IF NOT IS_CAR_DEAD truck_f1
			SET_CAR_PROOFS truck_f1 FALSE FALSE FALSE FALSE FALSE
			STOP_PLAYBACK_RECORDED_CAR truck_f1
			EXPLODE_CAR truck_f1
		ENDIF
		IF NOT IS_CAR_DEAD sweet_car
			SET_CAR_PROOFS sweet_car FALSE FALSE FALSE FALSE FALSE
			STOP_PLAYBACK_RECORDED_CAR sweet_car
			SET_CAR_HEALTH sweet_car 100
			EXPLODE_CAR sweet_car
		ENDIF

		ADD_EXPLOSION 2187.94 -1562.73 2.85 EXPLOSION_CAR
		ADD_EXPLOSION 2180.27 -1564.66 3.23 EXPLOSION_CAR
		ADD_EXPLOSION 2172.9 -1561.64 2.7 EXPLOSION_CAR
		helisetpiece_f1flag = 16
	ENDIF
ENDIF
//imran
IF helisetpiece_f1flag = 16
	IF TIMERA > 4500

		START_SCRIPT_FIRE 2201.11 -1565.77 1.36 1 1 fire2_f1

		REMOVE_ANIMATION MD_CHASE
		MARK_MODEL_AS_NO_LONGER_NEEDED petro
		MARK_MODEL_AS_NO_LONGER_NEEDED PETROTR
		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
		MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV 
		SET_TIME_SCALE 1.0
//		DO_FADE 250 FADE_OUT
//
//		WHILE GET_FADING_STATUS
//			WAIT 0
//		ENDWHILE


		IF NOT IS_CHAR_DEAD sweet
			TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet END_SC1_SWE MD_END 1000.0 FALSE FALSE FALSE TRUE -1
		ENDIF
		IF NOT IS_CHAR_DEAD ryder
			TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder END_SC1_RYD MD_END 1000.0 FALSE FALSE FALSE TRUE -1
		ENDIF
		TASK_PLAY_ANIM scplayer END_SC1_PLY MD_END 1000.0 FALSE FALSE FALSE TRUE -1
		IF NOT IS_CHAR_DEAD big_smoke
			TASK_PLAY_ANIM big_smoke END_SC1_SMO MD_END 1000.0 FALSE FALSE FALSE TRUE -1
			START_CHAR_FACIAL_TALK big_smoke 5000
		ENDIF
		
		WAIT 100
		SET_FIXED_CAMERA_POSITION 2171.5188 -1521.4374 23.7949 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2171.2869 -1520.4653 23.8322 JUMP_CUT
		PLAY_MISSION_AUDIO 1 //Shit!  That’s gonna be one hell of a story to tell when we passin’ the blunt!
		PRINT_NOW FIN1_MU 5000 1
		DELETE_CAR car_f1
		STOP_PLAYBACK_RECORDED_CAR trailer_f1
		STOP_PLAYBACK_RECORDED_CAR car_f1
		DELETE_CAR trailer_f1
		DELETE_CAR sweet_car
		DELETE_CAR truck_f1
		DELETE_CAR extpoliceheli_f1

//		DO_FADE 250 FADE_IN
		finalcut_f1flag = 1
		helisetpiece_f1flag = 17
	ENDIF
ENDIF

IF helisetpiece_f1flag > 16
	IF finalcut_f1flag = 1
		IF HAS_MISSION_AUDIO_FINISHED 1
			IF NOT IS_CHAR_DEAD big_smoke
				SET_FIXED_CAMERA_POSITION 2171.5188 -1521.4374 23.7949 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 2171.2869 -1520.4653 23.8322 JUMP_CUT
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				STOP_CHAR_FACIAL_TALK big_smoke
				LOAD_MISSION_AUDIO 1 SOUND_FIN1_MW //Fuck this! We gotta get outta here!
				PLAY_MISSION_AUDIO 2 //Man, that was some serious shit! Woo!
				PRINT_NOW FIN1_MV 4000 1
				IF NOT IS_CHAR_DEAD sweet
					START_CHAR_FACIAL_TALK sweet 5000
				ENDIF
				finalcut_f1flag = 2
			ENDIF
		ENDIF
	ENDIF
ENDIF
IF finalcut_f1flag = 2
	IF HAS_MISSION_AUDIO_FINISHED 2
		IF HAS_MISSION_AUDIO_LOADED 1
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CHAR_DEAD sweet
					CLEAR_PRINTS
					STOP_CHAR_FACIAL_TALK sweet
					PLAY_MISSION_AUDIO 1 //Fuck this! We gotta get outta here!
					PRINT_NOW FIN1_MW 5000 1
					START_CHAR_FACIAL_TALK ryder 5000
					CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO 2 SOUND_FIN1_MX //Ryder’s right, everybody split up – we’ll meet up later.
					finalcut_f1flag = 3
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF
IF finalcut_f1flag = 3
	IF HAS_MISSION_AUDIO_LOADED 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			IF NOT IS_CHAR_DEAD ryder
				IF NOT IS_CHAR_DEAD sweet
					STOP_CHAR_FACIAL_TALK ryder
					START_CHAR_FACIAL_TALK sweet 5000
					PLAY_MISSION_AUDIO 2 //Ryder’s right, everybody split up – we’ll meet up later.
					PRINT_NOW FIN1_MX 5000 1 
					finalcut_f1flag = 4
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF
IF finalcut_f1flag = 4
	IF HAS_MISSION_AUDIO_FINISHED 2
		CLEAR_PRINTS
		IF NOT IS_CHAR_DEAD sweet
			STOP_CHAR_FACIAL_TALK sweet	
		ENDIF
		finalcut_f1flag = 5
	ENDIF
ENDIF



IF helisetpiece_f1flag = 17
	IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_DEAD ryder
				IF IS_CHAR_PLAYING_ANIM big_smoke END_SC1_SMO
					GET_CHAR_ANIM_CURRENT_TIME big_smoke END_SC1_SMO animstate_f1
						IF animstate_f1 = 1.0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet END_SC2_SWE MD_END 1000.0 FALSE FALSE FALSE TRUE -1												
							TASK_PLAY_ANIM_NON_INTERRUPTABLE big_smoke END_SC2_SMO MD_END 1000.0 FALSE FALSE FALSE TRUE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE ryder END_SC2_RYD MD_END 1000.0 FALSE FALSE FALSE TRUE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer END_SC2_PLY MD_END 1000.0 FALSE FALSE FALSE FALSE -1
							SET_FIXED_CAMERA_POSITION 2168.1172 -1510.1654 24.3268 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 2168.1240 -1509.1660 24.2940 JUMP_CUT
							helisetpiece_f1flag = 18
						ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF helisetpiece_f1flag = 18
	IF NOT IS_CHAR_DEAD big_smoke
		IF finalcut_f1flag = 5					
			SET_FIXED_CAMERA_POSITION 2175.2705 -1537.7728 24.5856 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2175.0784 -1536.7964 24.6842 JUMP_CUT

			TASK_GO_STRAIGHT_TO_COORD big_smoke 2172.19 -1498.48 23.55 PEDMOVE_WALK -1
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE big_smoke 100.0
			WAIT 150
			IF NOT IS_CHAR_DEAD sweet
				TASK_GO_STRAIGHT_TO_COORD sweet 2170.77 -1492.05 23.65 PEDMOVE_WALK -1
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE sweet 100.0
			ENDIF
			WAIT 100
			IF NOT IS_CHAR_DEAD ryder
				TASK_GO_STRAIGHT_TO_COORD ryder 2163.65 -1506.92 23.01 PEDMOVE_WALK -1
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE ryder 100.0
			ENDIF
			DO_FADE 4000 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			DELETE_CAR extpoliceheli_f1

			helisetpiece_f1flag = 19
		ENDIF
	ENDIF
ENDIF

IF helisetpiece_f1flag = 19
	DELETE_CHAR big_smoke
	DELETE_CHAR ryder
	DELETE_CHAR sweet
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	SET_CHAR_COORDINATES scplayer 2170.935 -1500.675 22.9
	SET_CHAR_HEADING scplayer 355.56
	SET_CHAR_COORDINATES scplayer 2170.596 -1515.468 22.89//2170.935 -1500.675 22.9
	SET_CHAR_HEADING scplayer 356.495//355.56
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_IN_FRONT_OF_PLAYER
//	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF

	DO_FADE 2000 FADE_IN

	GOTO mission_drugs4_passed

	helisetpiece_f1flag = 20
ENDIF


GOTO moteldeal_mainloop


load_audio_f1:
IF handlingaudio_f1flag = 0
	LOAD_MISSION_AUDIO 1 audio_label_f1
	$text_label_f1 = $input_text_f1
	handlingaudio_f1flag = 1
ENDIF
RETURN

process_audio_f1:
IF handlingaudio_f1flag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		PRINT_NOW $text_label_f1 4000 1 //Dummy message"
		
		//Why are these being cleared before the player can read them?

		CLEAR_THIS_PRINT FIN1_BD  // Get some suppressing fire in there!
		CLEAR_THIS_PRINT FIN1_CK  // Unit down, repeat, unit down!
		CLEAR_THIS_PRINT FIN1_BE  // imme some fucking covering fire!
		CLEAR_THIS_PRINT FIN1_BY  // This is Buzzard 1, we are taking ground fire!
		CLEAR_THIS_PRINT FIN1_CB  // Four bangers in blue 4door heading back into South Central.
		CLEAR_THIS_PRINT FIN1_BK  // Surprise, homeboy!
		CLEAR_THIS_PRINT FIN1_BL  // Heads up, brother!

		IF attachaudio_f1flag = 1
			IF NOT IS_CHAR_DEAD woundedgrove1_f1 
				ATTACH_MISSION_AUDIO_TO_CHAR 1 woundedgrove1_f1
				attachaudio_f1flag = 2
			ENDIF
		ENDIF
		PLAY_MISSION_AUDIO 1
		handlingaudio_f1flag = 2
	ENDIF
ENDIF
IF handlingaudio_f1flag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_f1flag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		handlingaudio_f1flag = 0
	ENDIF
ENDIF
RETURN

shootatcoords_f1label:
OPEN_SEQUENCE_TASK shootatcoords_f1seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_SHOOT_AT_COORD -1 enemyx_f1 enemyy_f1 enemyz_f1 15000
SET_SEQUENCE_TO_REPEAT shootatcoords_f1seq 1
CLOSE_SEQUENCE_TASK shootatcoords_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 shootatcoords_f1seq
CLEAR_SEQUENCE_TASK shootatcoords_f1seq
RETURN

runstay_f1label:
OPEN_SEQUENCE_TASK runstay_f1seq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_f1 enemyy_f1 enemyz_f1 PEDMOVE_RUN -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
//TASK_KILL_CHAR_ON_FOOT -1 enemytarget_f1
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 enemytarget_f1 DUCK_RANDOMLY 5000 80
CLOSE_SEQUENCE_TASK runstay_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 runstay_f1seq
CLEAR_SEQUENCE_TASK runstay_f1seq
RETURN

stayshoot_f1label:
OPEN_SEQUENCE_TASK stayshoot_f1seq
TASK_TOGGLE_DUCK -1 TRUE
TASK_STAY_IN_SAME_PLACE -1 TRUE
//TASK_KILL_CHAR_ON_FOOT -1 enemytarget_f1
TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 enemytarget_f1 DUCK_WHEN_PLAYER_SHOOTING 5000 50
CLOSE_SEQUENCE_TASK stayshoot_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 stayshoot_f1seq
CLEAR_SEQUENCE_TASK stayshoot_f1seq
RETURN

stayshootnoduck_f1label:
OPEN_SEQUENCE_TASK stayshootnoduck_f1seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_f1
CLOSE_SEQUENCE_TASK stayshootnoduck_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 stayshootnoduck_f1seq
CLEAR_SEQUENCE_TASK stayshootnoduck_f1seq
RETURN

stay2shoot_f1label:
OPEN_SEQUENCE_TASK stay2shoot_f1seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_f1
TASK_KILL_CHAR_ON_FOOT -1 enemytarget2_f1
//TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING -1 enemytarget2_f1 DUCK_RANDOMLY 1500 90
CLOSE_SEQUENCE_TASK stay2shoot_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 stay2shoot_f1seq
CLEAR_SEQUENCE_TASK stay2shoot_f1seq
RETURN

rolloutr_f1label:
OPEN_SEQUENCE_TASK rolloutr_f1seq
TASK_TOGGLE_DUCK -1 TRUE
TASK_PLAY_ANIM -1 Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
//TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK rolloutr_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 rolloutr_f1seq
CLEAR_SEQUENCE_TASK rolloutr_f1seq
RETURN

rolloutl_f1label:
OPEN_SEQUENCE_TASK rolloutl_f1seq
TASK_TOGGLE_DUCK -1 TRUE
TASK_PLAY_ANIM -1 Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
//TASK_KILL_CHAR_ON_FOOT -1 scplayer
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK rolloutl_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 rolloutl_f1seq
CLEAR_SEQUENCE_TASK rolloutl_f1seq
RETURN

peekright_f1label:
OPEN_SEQUENCE_TASK peekright_f1seq
TASK_PLAY_ANIM -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
TASK_PLAY_ANIM -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
IF coordshoot_f1flag = 0
	TASK_KILL_CHAR_ON_FOOT_TIMED -1 enemytarget_f1 1500
ELSE
	TASK_SHOOT_AT_COORD -1 enemyx_f1 enemyy_f1 enemyz_f1 1500
ENDIF
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_PLAY_ANIM -1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_PLAY_ANIM -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
SET_SEQUENCE_TO_REPEAT peekright_f1seq 1
CLOSE_SEQUENCE_TASK peekright_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 peekright_f1seq
CLEAR_SEQUENCE_TASK peekright_f1seq
RETURN

peekleft_f1label:
OPEN_SEQUENCE_TASK peekleft_f1seq
TASK_PLAY_ANIM -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
TASK_PLAY_ANIM -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 1500
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_PLAY_ANIM -1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_PLAY_ANIM -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
SET_SEQUENCE_TO_REPEAT peekleft_f1seq 1
CLOSE_SEQUENCE_TASK peekleft_f1seq
PERFORM_SEQUENCE_TASK enemy_f1 peekleft_f1seq
CLEAR_SEQUENCE_TASK peekleft_f1seq
RETURN

// **************************************** Mission drugs4 failed ************************

mission_drugs4_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
DELETE_OBJECT advert_f1
CREATE_OBJECT_NO_OFFSET md_poster 2167.82 -1518.193 20.237 advert_f1
SET_OBJECT_HEADING advert_f1 0.0
DONT_REMOVE_OBJECT advert_f1
RETURN

   

// **************************************** mission drugs4 passed ************************

mission_drugs4_passed:

REGISTER_MISSION_PASSED ( LA1FIN1 )
SET_INT_STAT PASSED_LAFIN1 1
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 15 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 15//amount of respect
PLAY_MISSION_PASSED_TUNE 1
CLEAR_WANTED_LEVEL player1
PLAYER_MADE_PROGRESS 1

flag_la1fin1_mission_counter ++

RETURN
		


// ********************************** mission cleanup **************************************

mission_cleanup_drugs4:

flag_player_on_mission = 0

//counters
CLEAR_ONSCREEN_COUNTER carhealth_f1
SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
//roads on
SWITCH_ROADS_ON 2180.18 -1130.28 21.2 2276.82 -1151.26 28.0
SWITCH_PED_ROADS_ON 2180.18 -1130.28 21.2 2276.82 -1151.26 28.0
SWITCH_PED_ROADS_ON 2184.44 -1557.18 -5.481 2169.22 -1560.63 5.91 
////////////////////////////////////////////// interior section
//models
UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2
UNLOAD_SPECIAL_CHARACTER 3
REMOVE_CHAR_ELEGANTLY sweet
REMOVE_CHAR_ELEGANTLY ryder
REMOVE_CHAR_ELEGANTLY big_smoke
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
MARK_MODEL_AS_NO_LONGER_NEEDED FAM3
MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED SWAT
MARK_MODEL_AS_NO_LONGER_NEEDED POLMAV
MARK_MODEL_AS_NO_LONGER_NEEDED ENFORCER
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
REMOVE_ANIMATION SWAT
MARK_MODEL_AS_NO_LONGER_NEEDED BFYPRO
MARK_MODEL_AS_NO_LONGER_NEEDED imy_skylight
MARK_MODEL_AS_NO_LONGER_NEEDED kmb_trolley
REMOVE_ANIMATION KISSING
REMOVE_ANIMATION GANGS
//blips
REMOVE_BLIP heli_f1blip
REMOVE_BLIP sweet_f1blip
REMOVE_BLIP motel_f1blip
CLEAR_ONSCREEN_COUNTER sweethealth_f1
//particle fx
KILL_FX_SYSTEM breachfx_f1
//pickups
REMOVE_PICKUP armour1_f1
//route
FLUSH_ROUTE
////////////////////////////////////////////// rails section
MARK_MODEL_AS_NO_LONGER_NEEDED md_poster
MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
MARK_MODEL_AS_NO_LONGER_NEEDED LAPDM1
MARK_MODEL_AS_NO_LONGER_NEEDED COPBIKE
MARK_MODEL_AS_NO_LONGER_NEEDED AK47
MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
REMOVE_ANIMATION GANGS
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_SCAFFOLD_3b
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_SCAFFOLD_4b
REMOVE_ANIMATION MD_CHASE
MARK_MODEL_AS_NO_LONGER_NEEDED munch_donut
MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_arml
MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_armr
MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_legl
MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_legr
MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_head
MARK_MODEL_AS_NO_LONGER_NEEDED chopcop_torso
MARK_MODEL_AS_NO_LONGER_NEEDED Wd_Fence_Anim
REMOVE_ANIMATION CAR_CHAT
REMOVE_ANIMATION MD_END
MARK_MODEL_AS_NO_LONGER_NEEDED petro
MARK_MODEL_AS_NO_LONGER_NEEDED PETROTR
SET_MAX_WANTED_LEVEL 4 		///////		WANTED LEVEL ASK FILSHIE ABOUT WHAT IT SHOULD BE SET BACK TO AT THIS POINT
DELETE_CAR extpoliceheli_f1
//blips
REMOVE_BLIP policecar1_f1blip
REMOVE_BLIP policecar2_f1blip
REMOVE_BLIP policecar3_f1blip
REMOVE_BLIP policecar4_f1blip
REMOVE_BLIP policecar5_f1blip
REMOVE_BLIP policecar6_f1blip
REMOVE_BLIP policecar7_f1blip
REMOVE_BLIP policecar8_f1blip
//detach
IF IS_PLAYER_PLAYING PLAYER1
	SET_PLAYER_DUCK_BUTTON PLAYER1 TRUE
	SET_PLAYER_FIRE_BUTTON PLAYER1 TRUE
	SHUT_CHAR_UP scplayer FALSE
	//SET_PLAYER_FAST_RELOAD PLAYER1 FALSE
	//Interior
ENDIF

//detach bad guys
IF NOT IS_CHAR_DEAD bikerjumper3_f1
	REMOVE_CHAR_ELEGANTLY bikerjumper3_f1
ENDIF
IF NOT IS_CHAR_DEAD bikerjumper4_f1
	REMOVE_CHAR_ELEGANTLY bikerjumper4_f1
ENDIF

DETACH_CHAR_FROM_CAR scplayer
SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
REMOVE_ALL_SCRIPT_FIRES
//fx
KILL_FX_SYSTEM jetwashfx1_f1
KILL_FX_SYSTEM bloodfx1_f1
KILL_FX_SYSTEM bloodfx2_f1
KILL_FX_SYSTEM bloodfx3_f1
//entry exit
SWITCH_ENTRY_EXIT motel1 TRUE
//wanted multipliers
SET_WANTED_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0
SWITCH_EMERGENCY_SERVICES ON
RELEASE_WEATHER
GET_GAME_TIMER timer_mobile_start
MISSION_HAS_FINISHED
RETURN

 
}
 


