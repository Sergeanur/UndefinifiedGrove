MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* DRUGS 1 *******************************************
// ********************************* Mission Description ***********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME drugs1

// Mission start stuff

GOSUB mission_start_drugs1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_drugs1_failed
ENDIF

GOSUB mission_cleanup_drugs1

MISSION_END

{
 
//////////////////////// variables for shootout
// Variables for mission
LVAR_INT smokecar_s4
//decision maker
LVAR_INT smoke4_DM
LVAR_INT smoke4duck_DM
LVAR_INT people1_s4
LVAR_INT people2_s4
LVAR_INT people3_s4
LVAR_INT people4_s4
LVAR_INT people5_s4
LVAR_INT russian1_s4
LVAR_INT russian2_s4
LVAR_INT russian3_s4
LVAR_INT russian4_s4
LVAR_INT russian5_s4
LVAR_INT russian6_s4
LVAR_INT russian7_s4
LVAR_INT russian8_s4
LVAR_INT russian9_s4
LVAR_INT russian10_s4
LVAR_INT russian11_s4
LVAR_INT russian12_s4
LVAR_INT russian13_s4
LVAR_INT russian14_s4
LVAR_INT russian15_s4
LVAR_INT russian16_s4
LVAR_INT russian17_s4
LVAR_INT russian18_s4
LVAR_INT russian19_s4
LVAR_INT russian20_s4
VAR_INT smokehealth_s4
LVAR_FLOAT animtime_s4
LVAR_INT hoochie_s4
LVAR_INT visiblearea_s4

//sequences
LVAR_INT run2kill_s4seq
LVAR_INT runkill_s4seq
LVAR_INT stayshoot_s4seq
LVAR_INT stayshootnoduck_s4seq
LVAR_INT smoke1_s4seq
LVAR_INT smoke2_s4seq
LVAR_INT smoke3_s4seq
LVAR_INT smoke4_s4seq
LVAR_INT smoke5_s4seq
LVAR_INT smoke6_s4seq
LVAR_INT smoke7_s4seq
LVAR_INT smoke8_s4seq
LVAR_INT sequencetask_s4
LVAR_INT runkillstay_s4seq
LVAR_INT enemy_s4 enemytarget_s4
LVAR_FLOAT enemyx_s4 enemyy_s4 enemyz_s4
LVAR_FLOAT enemyx2_s4 enemyy2_s4 enemyz2_s4
//blips
LVAR_INT smokecar_s4blip
LVAR_INT atrium_s4blip
LVAR_INT smoke_s4blip

//flags
LVAR_INT smoke_s4flag
VAR_INT smokeshoot_s4flag
LVAR_INT smokegroupstation_s4flag
LVAR_INT outoffirstgroup_s4flag
LVAR_INT skipcutscene_s4flag
LVAR_INT russian3_s4flag
LVAR_INT russian12_s4flag
LVAR_INT russian7_s4flag
LVAR_INT russian11_s4flag
LVAR_INT russian13_s4flag
LVAR_INT cutscene_s4flag

//flags
LVAR_INT russian8_s4flag
LVAR_INT russian9_s4flag
LVAR_INT russian1remove_s4flag
LVAR_INT russian2remove_s4flag
LVAR_INT russian3remove_s4flag
LVAR_INT russian4remove_s4flag
LVAR_INT russian5remove_s4flag
LVAR_INT russian6remove_s4flag
LVAR_INT russian7remove_s4flag
LVAR_INT russian8remove_s4flag
LVAR_INT russian9remove_s4flag
LVAR_INT russian10remove_s4flag
LVAR_INT russian11remove_s4flag
LVAR_INT russian12remove_s4flag
LVAR_INT russian13remove_s4flag
LVAR_INT russian14remove_s4flag
LVAR_INT russian15remove_s4flag
LVAR_INT russian16remove_s4flag
LVAR_INT russian17remove_s4flag
LVAR_INT russian18remove_s4flag
LVAR_INT russian19remove_s4flag
LVAR_INT russian20remove_s4flag
LVAR_INT text_s4flag
LVAR_INT shootout_s4flag
LVAR_INT russianseliminated_s3counter
LVAR_INT helpshoottext_s4flag
LVAR_INT warp_s4flag
LVAR_INT oneoff_s4flag
LVAR_INT slowdownstart_s4flag
LVAR_INT stoptalk_s4flag
//////////////////////// variables for on rails section

LVAR_FLOAT cam_pos_X_s4 cam_pos_Y_s4 cam_pos_Z_s4 cam_look_X_s4 cam_look_Y_s4 cam_look_Z_s4
LVAR_FLOAT cam_P_acc_X_s4 cam_P_acc_Y_s4 cam_P_acc_Z_s4 cam_P_speed_X_s4 cam_P_speed_Y_s4 cam_P_speed_Z_s4
LVAR_FLOAT cam_L_acc_X_s4 cam_L_acc_Y_s4 cam_L_acc_Z_s4 cam_L_speed_X_s4 cam_L_speed_Y_s4 cam_L_speed_Z_s4 
LVAR_FLOAT cam_P_Y_cap_speed cam_L_Y_cap_speed cam_L_Z_cap_speed

LVAR_INT timescale_status_s4 grate_s4 barrel1_s4 barrel2_s4 barrel3_s4 barrel4_s4 barrel5_s4
LVAR_FLOAT timescale_s4
LVAR_INT camera_pos_status_s4
LVAR_INT camera_look_status_s4
LVAR_INT smokebike_s4
LVAR_INT skip_s4
LVAR_INT coach1_s4
LVAR_INT coach2_s4
LVAR_INT car1_s4
LVAR_INT car2_s4
LVAR_INT car3_s4
LVAR_INT car4_s4
LVAR_INT car5_s4
LVAR_INT car6_s4
LVAR_INT car7_s4
LVAR_INT car8_s4
LVAR_INT car9_s4
LVAR_INT car12_s4
LVAR_INT car13_s4
LVAR_INT coach3_s4
LVAR_INT driver1_s4
LVAR_INT driver2_s4
LVAR_INT driver3_s4
LVAR_INT driver4_s4
LVAR_INT driver5_s4
LVAR_INT driver6_s4
LVAR_INT driver7_s4
LVAR_INT driver8_s4
LVAR_INT driver9_s4
LVAR_INT driver12_s4
LVAR_INT driver13_s4
LVAR_INT packer_s4
LVAR_INT bike1_s4
LVAR_INT rcar1_s4 
LVAR_INT rcar2_s4 
LVAR_INT rcar3_s4
LVAR_INT rcar4_s4
LVAR_INT bike2_s4

LVAR_INT bike3_s4
LVAR_INT bike4_s4
LVAR_INT packerdriver_s4
LVAR_INT packerpassenger_s4
LVAR_FLOAT cam_X_pos_s4 cam_Y_pos_s4 cam_Z_pos_s4
LVAR_FLOAT cam_X_look_s4 cam_Y_look_s4 cam_Z_look_s4
LVAR_INT driverofcar_s4

LVAR_INT bike5_s4

LVAR_INT packercar2_s4
LVAR_INT packercar1_s4

VAR_INT bikehealth_s4

//blips
LVAR_INT packer_s4blip
LVAR_INT car1_s4blip
LVAR_INT car2_s4blip
LVAR_INT car3_s4blip
LVAR_INT car4_s4blip
LVAR_INT bike1_s4blip
LVAR_INT bike2_s4blip
LVAR_INT bike3_s4blip
LVAR_INT bike4_s4blip
LVAR_INT bike5_s4blip
LVAR_INT bike6_s4blip
LVAR_INT bike7_s4blip
LVAR_INT bike8_s4blip
LVAR_INT bike9_s4blip
LVAR_INT bike10_s4blip
LVAR_INT bike11_s4blip
LVAR_INT bike12_s4blip
LVAR_INT russian13_s4blip
LVAR_INT russian14_s4blip
LVAR_INT rcar3_s4blip
LVAR_INT rcar4_s4blip
LVAR_INT rcar1_s4blip
LVAR_INT russian7_s4blip
LVAR_INT russian8_s4blip
LVAR_INT russian9_s4blip
LVAR_INT russian10_s4blip
LVAR_INT russian12_s4blip
LVAR_INT russian20_s4blip
//text
LVAR_TEXT_LABEL $text_label_s4
LVAR_INT audio_label_s4
LVAR_TEXT_LABEL $input_text_s4

//flags
LVAR_INT ambientcar_s4flag
LVAR_INT firstchase_s4flag
LVAR_INT notonscreen_s4flag
LVAR_INT storm1_s4flag
LVAR_INT gopacker_s4flag
LVAR_INT bike1swap_s4flag
LVAR_INT bike2swap_s4flag
LVAR_INT bike3swap_s4flag
LVAR_INT bike4swap_s4flag
LVAR_INT bike5swap_s4flag
LVAR_INT bike6swap_s4flag
LVAR_INT bike7swap_s4flag
LVAR_INT bike8swap_s4flag
LVAR_INT car1_s4flag
LVAR_INT car2_s4flag
LVAR_INT car3_s4flag
LVAR_INT car4_s4flag
LVAR_INT car5_s4flag
LVAR_INT bike1_s4flag
LVAR_INT bike2_s4flag
LVAR_INT bike3_s4flag
LVAR_INT bike4_s4flag
LVAR_INT bike5_s4flag
LVAR_INT car1swap_s4flag
LVAR_INT car2swap_s4flag
LVAR_INT car3swap_s4flag
LVAR_INT car4swap_s4flag
LVAR_INT packercar1_s4flag
LVAR_INT packercar2_s4flag
LVAR_INT startrecording_s4flag
LVAR_INT thirdchase_s4flag
LVAR_INT packersmash1_s4flag
LVAR_INT packersmash2_s4flag
LVAR_INT packerstatus_s4flag
LVAR_INT spark_s4flag
LVAR_INT finalcut_s4flag 
LVAR_INT playershoot_s4flag 
LVAR_INT jumpchase_s4flag	
LVAR_INT finalchase_s4flag 
LVAR_INT grate_s4flag
LVAR_INT progressaudio_s4flag
LVAR_INT handlingudio_s4flag
LVAR_INT getonbikecut_s4flag
LVAR_INT truckaudio_s4flag
LVAR_INT explodecar5_s4flag
LVAR_INT explodecar6_s4flag
LVAR_INT difficulty_s4flag //do not reset this flag
LVAR_INT difficulty_s4value //do not reset this

// **************************************** Mission Start **********************************

mission_start_drugs1:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

WAIT 0

LOAD_MISSION_TEXT SMOKE4

// ****************************************START OF CUTSCENE********************************

CLEAR_AREA 2060.0 -1694.0 50.0 50.0 TRUE

FORCE_WEATHER_NOW WEATHER_SUNNY_LA

REQUEST_MODEL Lae_smokecutscene

WHILE NOT HAS_MODEL_LOADED Lae_smokecutscene
	WAIT 0
ENDWHILE

LVAR_INT smokeobject_s4
CREATE_OBJECT_NO_OFFSET Lae_smokecutscene 2055.0 -1695.0 15.0 smokeobject_s4


LOAD_CUTSCENE SMOKE4A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
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


MARK_MODEL_AS_NO_LONGER_NEEDED Lae_smokecutscene
DELETE_OBJECT smokeobject_s4

// ****************************************END OF CUTSCENE**********************************

// fades the screen in 

SET_FADING_COLOUR 0 0 0


WAIT 500


// request models
REQUEST_MODEL GLENDALE
REQUEST_MODEL WFYRI
REQUEST_MODEL WFYST
REQUEST_MODEL MAFFA
REQUEST_MODEL MAFFB

LOAD_SPECIAL_CHARACTER 1 SMOKE

LOAD_MISSION_AUDIO 2 SOUND_SMOX_AC //Get in the car!

LOAD_ALL_MODELS_NOW 

WHILE NOT HAS_MODEL_LOADED GLENDALE
	OR NOT HAS_MODEL_LOADED MAFFA
	OR NOT HAS_MODEL_LOADED MAFFA
	OR NOT HAS_MODEL_LOADED WFYRI
	OR NOT HAS_MODEL_LOADED WFYST
	WAIT 0
ENDWHILE

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	OR NOT HAS_MISSION_AUDIO_LOADED 2
	WAIT 0
ENDWHILE



// ****************************** Declare Variables Values ***********************************

//////////////////////// variables for shootout

//flags
smoke_s4flag = 0
smokeshoot_s4flag = 0
smokegroupstation_s4flag = 0
outoffirstgroup_s4flag = 0
skipcutscene_s4flag = 0
russian3_s4flag = 0
russian12_s4flag = 0
russian7_s4flag = 0
russian11_s4flag = 0
russian13_s4flag = 0
cutscene_s4flag = 0

russian8_s4flag = 0
russian9_s4flag = 0
russian1remove_s4flag = 0
russian2remove_s4flag = 0
russian3remove_s4flag = 0
russian4remove_s4flag = 0
russian5remove_s4flag = 0
russian6remove_s4flag = 0
russian7remove_s4flag = 0
russian8remove_s4flag = 0
russian9remove_s4flag = 0
russian10remove_s4flag = 0
russian11remove_s4flag = 0
russian12remove_s4flag = 0
russian13remove_s4flag = 0
russian14remove_s4flag = 0
russian15remove_s4flag = 0
russian16remove_s4flag = 0
russian17remove_s4flag = 0
russian18remove_s4flag = 0
russian19remove_s4flag = 0
russian20remove_s4flag = 0
text_s4flag = 0
shootout_s4flag = 0
russianseliminated_s3counter = 0
helpshoottext_s4flag = 0
warp_s4flag = 0
progressaudio_s4flag = 0
handlingudio_s4flag	= 0
oneoff_s4flag = 0
slowdownstart_s4flag = 0
stoptalk_s4flag = 0
//////////////////////// variables for on rails section

//flags
ambientcar_s4flag = 0
firstchase_s4flag = 0
notonscreen_s4flag = 0
storm1_s4flag = 0
gopacker_s4flag = 0
bike1swap_s4flag = 0
bike2swap_s4flag = 0
bike3swap_s4flag = 0
bike4swap_s4flag = 0
bike5swap_s4flag = 0
bike6swap_s4flag = 0
bike7swap_s4flag = 0
bike8swap_s4flag = 0
car1_s4flag = 0
car2_s4flag = 0
car3_s4flag = 0
car4_s4flag = 0
car5_s4flag = 0
bike1_s4flag = 0
bike2_s4flag = 0
bike3_s4flag = 0
bike4_s4flag = 0
bike5_s4flag = 0
car1swap_s4flag = 0
car2swap_s4flag = 0
car3swap_s4flag = 0
car4swap_s4flag = 0
packercar1_s4flag = 0
packercar2_s4flag = 0
startrecording_s4flag = 0
thirdchase_s4flag = 0
packersmash1_s4flag = 0
packersmash2_s4flag = 0
packerstatus_s4flag = 0
spark_s4flag = 0
finalcut_s4flag = 0 
playershoot_s4flag = 0 
jumpchase_s4flag = 0	
finalchase_s4flag = 0 
grate_s4flag = 0
getonbikecut_s4flag = 0
truckaudio_s4flag = 0
explodecar5_s4flag = 0
explodecar6_s4flag = 0
 // **************************************** Mission drugs1 failed *******************************


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY smoke4_DM

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY smoke4duck_DM
ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE smoke4duck_DM EVENT_SHOT_FIRED_WHIZZED_BY TASK_SIMPLE_DUCK_WHILE_SHOTS_WHIZZING 0.0 100.0 100.0 100.0 FALSE TRUE

//create smoke's car
CLEAR_AREA 2077.58 -1695.929 150.0 150.0 TRUE
//SET_CHAR_COORDINATES scplayer 2073.572 -1700.066 12.602 //1716.4 -1941.476 12.51
//SET_CHAR_HEADING scplayer 273.887
CUSTOM_PLATE_FOR_NEXT_CAR GLENDALE &_A2tmfK_
CREATE_CAR GLENDALE 2058.86 -1694.612 12.297 smokecar_s4
SET_RADIO_CHANNEL RS_MODERN_HIP_HOP
CHANGE_CAR_COLOUR smokecar_s4 98 14
SET_CAR_HEADING smokecar_s4 271.822
SET_CAR_HEALTH smokecar_s4 1000
CREATE_CHAR_AS_PASSENGER smokecar_s4 PEDTYPE_SPECIAL SPECIAL01 0 big_smoke
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE
SET_CHAR_SIGNAL_AFTER_KILL big_smoke FALSE

//SET_ANIM_GROUP_FOR_CHAR big_smoke fatman
SET_CHAR_HEALTH big_smoke 500
SET_CHAR_SUFFERS_CRITICAL_HITS big_smoke FALSE
SET_CHAR_NEVER_TARGETTED big_smoke TRUE
SET_CHAR_DECISION_MAKER big_smoke smoke4_DM
//ADD_BLIP_FOR_CAR smokecar_s4 smokecar_s4blip
SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED big_smoke TRUE
//SET_BLIP_AS_FRIENDLY smokecar_s4blip TRUE
SET_MAX_FIRE_GENERATIONS 1
WARP_CHAR_INTO_CAR scplayer smokecar_s4

SET_WANTED_MULTIPLIER 0.0
SWITCH_EMERGENCY_SERVICES OFF

DO_FADE 1500 FADE_IN

RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
SWITCH_WIDESCREEN OFF
SHUT_CHAR_UP scplayer TRUE

SWITCH_ROADS_OFF 1694.12 -1604.33 5.0 1805.94 -1593.12 15.0

PRINT_NOW DGS1_1 6000 1 //~s~Take Big Smoke to ~y~Downtown~s
TIMERB = 0


SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

smoke4_main_loop:

WAIT 0

//imran
//VIEW_INTEGER_VARIABLE difficulty_s4value difficulty_s4value

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_drugs1_passed
ENDIF

IF NOT finalchase_s4flag > 7
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW DGS1_60 7000 1 //~r~Smoke died!
		GOTO mission_drugs1_failed
	ENDIF 
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Getting to Atrium	////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF shootout_s4flag = 0

	IF smoke_s4flag	= 11
		IF smokeshoot_s4flag < 6
			IF NOT IS_CHAR_IN_AREA_2D scplayer 1744.2 -1686.68 1698.34 -1616.4 FALSE
				IF NOT IS_CHAR_ON_SCREEN big_smoke
					EXPLODE_CHAR_HEAD big_smoke
					PRINT_NOW DGS1_3 7000 1//~r~You left Smoke alone and he died! Next time stick a bit closer!
					GOTO mission_drugs1_failed
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF smoke_s4flag = 0	
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CAR_DEAD smokecar_s4
				IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s4
					IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s4
						ADD_BLIP_FOR_COORD 1722.568 -1597.92 12.405 atrium_s4blip
						PRINT_NOW DGS1_1 10000 1 //~s~Take Big Smoke to ~y~Downtown~s~.
//						REMOVE_BLIP	smokecar_s4blip
						smoke_s4flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	///////////////////////////////////////////////////////////////////////////group stuff before the atrium


	IF outoffirstgroup_s4flag = 0
		IF smoke_s4flag = 1
		OR smoke_s4flag = 100

			IF smokegroupstation_s4flag = 0
				IF NOT IS_CAR_DEAD smokecar_s4
					IF NOT IS_CHAR_SITTING_IN_CAR scplayer smokecar_s4
						ADD_BLIP_FOR_CAR smokecar_s4 smokecar_s4blip
						SET_BLIP_AS_FRIENDLY smokecar_s4blip TRUE
						REMOVE_BLIP atrium_s4blip
						//PRINT_NOW DGS1_57 5000 1 //~s~Get back in the car!
						smoke_s4flag = 100
						CLEAR_MISSION_AUDIO 1
						CLEAR_PRINTS
						PLAY_MISSION_AUDIO 2 
						PRINT_NOW SMOX_AC 2000 1 //Get in the car!
						smokegroupstation_s4flag = 1
					ENDIF
				ENDIF
			ENDIF

			IF smokegroupstation_s4flag = 1
				IF NOT IS_CAR_DEAD smokecar_s4
					IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s4
						REMOVE_BLIP smokecar_s4blip
						ADD_BLIP_FOR_COORD 1722.568 -1597.92 12.405 atrium_s4blip
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 2
						IF progressaudio_s4flag > 12
							PRINT_NOW DGS1_1 5000 1 //~s~Take Big Smoke to ~y~Downtown~s~.
						ENDIF
						smoke_s4flag = 1
						smokegroupstation_s4flag = 0
					ENDIF
				ENDIF
			ENDIF

		ENDIF	
	ENDIF	

	///////////////////////////////////////////////////////////////////////////Dialogue before atrium
	IF smoke_s4flag = 1
		
		GOSUB process_audio_s4

		//play mission audio
		IF progressaudio_s4flag = 0
			IF handlingudio_s4flag = 0
				IF TIMERB > 6500
					audio_label_s4 = SOUND_SMO4_AA	//This better now be another cop errand, man.
					$input_text_s4 = SMO4_AA	//This better now be another cop errand, man.
					GOSUB load_audio_s4
				ENDIF
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 1
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AB	//No, this is strictly for the homies.
				$input_text_s4 = SMO4_AB	//No, this is strictly for the homies.
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 2
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AC //I gotta be honest, CJ, could be some heavy shit where we going.
				$input_text_s4 = SMO4_AC //I gotta be honest, CJ, could be some heavy shit where we going.
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 3
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AD	//Man, what is you into, Smoke?
				$input_text_s4 = SMO4_AD	//Man, what is you into, Smoke?
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 4
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AE	//Lot of shit going down, Carl. 
				$input_text_s4 = SMO4_AE	//Lot of shit going down, Carl. 
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 5
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AF	//Families coming back, Ballas pushing base, 
				$input_text_s4 = SMO4_AF	//Families coming back, Ballas pushing base,
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 6
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AG	//Russian cats with nothing to lose...
				$input_text_s4 = SMO4_AG	//Russian cats with nothing to lose...
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 7
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AH //Russians?
				$input_text_s4 = SMO4_AH //Russians?
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 7
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AJ //	Yeah, go figure.
				$input_text_s4 = SMO4_AJ //	Yeah, go figure.
				GOSUB load_audio_s4

			ENDIF
		ENDIF

		IF progressaudio_s4flag = 8
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AK	//All my life I been told to fear the Ruskies, but I never even met one. 
				$input_text_s4 = SMO4_AK	//All my life I been told to fear the Ruskies, but I never even met one. 
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 9
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AL	//Then the wall comes down and we’re all supposed to be friends.
				$input_text_s4 = SMO4_AL	//Then the wall comes down and we’re all supposed to be friends.
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 10
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AM //Five minutes later my cuz gets laid out by some Rigger fresh off the boat.
				$input_text_s4 = SMO4_AM //Five minutes later my cuz gets laid out by some Rigger fresh off the boat.
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 11
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AN	//For real?
				$input_text_s4 = SMO4_AN	//For real?
				GOSUB load_audio_s4
			ENDIF
		ENDIF

		IF progressaudio_s4flag = 12
			IF handlingudio_s4flag = 0
				audio_label_s4 = SOUND_SMO4_AO	//Word.
				$input_text_s4 = SMO4_AO	//Word.
				GOSUB load_audio_s4
			ENDIF
		ENDIF

	
	ENDIF
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////	Arrived at Atrium	////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	IF smoke_s4flag = 1
		IF NOT IS_CAR_DEAD smokecar_s4
			IF NOT IS_CHAR_DEAD big_smoke
				IF IS_CHAR_SITTING_IN_CAR big_smoke smokecar_s4
					IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s4
						IF LOCATE_CAR_3D smokecar_s4 1722.568 -1597.92 12.45 4.0 4.0 5.0 TRUE

							SET_PLAYER_CONTROL PLAYER1 OFF
							SWITCH_WIDESCREEN ON
							DISABLE_ALL_ENTRY_EXITS TRUE
							SET_MAX_WANTED_LEVEL 0
							text_s4flag = 0
							CLEAR_AREA 1722.568 -1597.92 300.0 300.0 TRUE
							SET_CAR_DENSITY_MULTIPLIER 0.0
							SET_PED_DENSITY_MULTIPLIER 0.0
								
							SET_FIXED_CAMERA_POSITION 1711.4487 -1576.9242 30.3531 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 1711.6277 -1577.8209 29.9486 JUMP_CUT

							TASK_LEAVE_ANY_CAR big_smoke
							TASK_LEAVE_ANY_CAR scplayer

							DO_FADE 2000 FADE_OUT 

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE


							SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

							REQUEST_MODEL MICRO_UZI
							REQUEST_MODEL COLT45
							REQUEST_MODEL MP5LNG
							REQUEST_ANIMATION JST_BUISNESS

							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_SMO4_BA //Before I walk in there I just gotta know you’re down for this.
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_SMO4_BB	//Look, Smoke, we go way back.  We Groves, man!

							LOAD_ALL_MODELS_NOW
							
							WHILE NOT HAS_MODEL_LOADED COLT45
								OR NOT HAS_MODEL_LOADED MP5LNG
								OR NOT HAS_ANIMATION_LOADED JST_BUISNESS
								OR NOT HAS_MISSION_AUDIO_LOADED 1
								OR NOT HAS_MISSION_AUDIO_LOADED	2
								WAIT 0
							ENDWHILE

							WHILE NOT HAS_MODEL_LOADED MP5LNG
								WAIT 0
							ENDWHILE

							
							CREATE_CHAR PEDTYPE_MISSION1 WFYST 1718.2826 -1633.1780 19.2035 people1_s4
							SHUT_CHAR_UP people1_s4 TRUE
							SET_CHAR_HEADING people1_s4 59.5412 
							TASK_PLAY_ANIM_NON_INTERRUPTABLE people1_s4 SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE -1
							SET_CHAR_DECISION_MAKER people1_s4 smoke4_DM
							SHUT_CHAR_UP people1_s4 TRUE
							
							CREATE_CHAR PEDTYPE_MISSION1 WFYST 1717.7198 -1633.9523 19.2029 people2_s4
							SET_CHAR_HEADING people2_s4 313.3566 
							SHUT_CHAR_UP people2_s4 TRUE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE people2_s4 SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE -1
							SET_CHAR_DECISION_MAKER people2_s4 smoke4_DM
							SHUT_CHAR_UP people2_s4 TRUE
							
							CREATE_CHAR PEDTYPE_MISSION1 WFYST 1712.8112 -1629.4536 19.2010 people3_s4
							SET_CHAR_HEADING people3_s4 48.5227//90.7262 
							SHUT_CHAR_UP people3_s4 TRUE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE people3_s4 SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE -1
							SET_CHAR_DECISION_MAKER people3_s4 smoke4_DM
							SHUT_CHAR_UP people3_s4 TRUE
							
							CREATE_CHAR PEDTYPE_MISSION1 WFYST 1712.1465 -1630.1652 19.2003 people4_s4
							SET_CHAR_HEADING people4_s4 322.1413
							SHUT_CHAR_UP people4_s4 TRUE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE people4_s4 SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE -1
							SET_CHAR_DECISION_MAKER people4_s4 smoke4_DM
							SHUT_CHAR_UP people4_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 WFYST 1732.5283 -1627.4497 19.2044 people5_s4
							SET_CHAR_HEADING people5_s4 275.6598 
							SHUT_CHAR_UP people5_s4 TRUE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE people5_s4 SEAT_IDLE PED 4.0 TRUE FALSE FALSE FALSE -1
							SET_CHAR_DECISION_MAKER people5_s4 smoke4_DM
							SHUT_CHAR_UP people5_s4 TRUE

							//create russians
							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1729.973 -1649.115 19.274 russian1_s4
							SET_CHAR_HEADING russian1_s4 11.051 //duck and shoot smoke
							SET_CHAR_DECISION_MAKER russian1_s4 smoke4_DM
							GIVE_WEAPON_TO_CHAR russian1_s4 WEAPONTYPE_MP5 9999
							SET_CHAR_AREA_VISIBLE russian1_s4 18
							SET_CHAR_HEALTH russian1_s4 35
							ADD_BLIP_FOR_CHAR russian1_s4 bike1_s4blip
							CHANGE_BLIP_DISPLAY bike1_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike1_s4blip 2
							SET_CHAR_KEEP_TASK russian1_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1729.971 -1657.139 19.278 russian2_s4
							SET_CHAR_HEADING russian2_s4  21.106 //2 -> no duck stay shoot player (bullets)
							SET_CHAR_HEALTH russian2_s4 10
							SET_CHAR_DECISION_MAKER russian2_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian2_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian2_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_ACCURACY russian2_s4 60
							SET_CHAR_AREA_VISIBLE russian2_s4 18
							ADD_BLIP_FOR_CHAR russian2_s4 bike2_s4blip
							CHANGE_BLIP_DISPLAY bike2_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike2_s4blip 2
							SET_CHAR_KEEP_TASK russian2_s4 TRUE
							SET_CHAR_SHOOT_RATE russian2_s4 50

							CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1722.311 -1658.636 19.108 russian3_s4
							SET_CHAR_HEADING russian3_s4 352.526 //3 duck stay and shoot smoke (bullets)
							SET_CHAR_DECISION_MAKER russian3_s4 smoke4duck_DM
							SET_CHAR_WEAPON_SKILL russian3_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian3_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_AREA_VISIBLE russian3_s4 18
							ADD_BLIP_FOR_CHAR russian3_s4 bike3_s4blip
							CHANGE_BLIP_DISPLAY bike3_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike3_s4blip 2
							SET_CHAR_KEEP_TASK russian3_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1712.399 -1657.463 19.26 russian4_s4
							SET_CHAR_HEALTH russian4_s4 150
							SET_CHAR_HEADING russian4_s4 323.108 //4 - shoot player duck stay and shoot (bullets)
							SET_CHAR_DECISION_MAKER russian4_s4 smoke4duck_DM
							SET_CHAR_WEAPON_SKILL russian4_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian4_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_AREA_VISIBLE russian4_s4 18
							SET_CHAR_ACCURACY russian4_s4 50
							ADD_BLIP_FOR_CHAR russian4_s4 bike4_s4blip
							CHANGE_BLIP_DISPLAY bike4_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike4_s4blip 2
							SET_CHAR_KEEP_TASK russian4_s4 TRUE
							SET_CHAR_MAX_HEALTH russian4_s4 150
							SET_CHAR_HEALTH russian4_s4 150
							SET_CHAR_SHOOT_RATE russian4_s4 50

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1712.763 -1664.816 19.273 russian5_s4
							SET_CHAR_HEADING russian5_s4 329.331 //5 shoot player no duck (bullets)
							SET_CHAR_DECISION_MAKER russian5_s4 smoke4duck_DM
							GIVE_WEAPON_TO_CHAR russian5_s4 WEAPONTYPE_MP5 9999
							SET_CHAR_ACCURACY russian5_s4 50
							SET_CHAR_MAX_HEALTH russian5_s4 150
							SET_CHAR_HEALTH russian5_s4 150
							SET_CHAR_AREA_VISIBLE russian5_s4 18
							ADD_BLIP_FOR_CHAR russian5_s4 bike5_s4blip
							CHANGE_BLIP_DISPLAY bike5_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike5_s4blip 2
							SET_CHAR_KEEP_TASK russian5_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1718.711 -1655.182 19.108 russian6_s4
							SET_CHAR_HEADING russian6_s4 327.782 //6 shoot player stay duck 
							SET_CHAR_DECISION_MAKER russian6_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian6_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian6_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_AREA_VISIBLE russian6_s4 18
							SET_CHAR_ACCURACY russian6_s4 60
							ADD_BLIP_FOR_CHAR russian6_s4 bike6_s4blip
							CHANGE_BLIP_DISPLAY bike6_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike6_s4blip 2
							SET_CHAR_KEEP_TASK russian6_s4 TRUE

							//create russians
							CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1701.8 -1650.28 19.26 russian7_s4
							SET_CHAR_HEADING russian7_s4 265.623 //7 -> kill normally  1720.069 -1649.467 19.27
							SET_CHAR_DECISION_MAKER russian7_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian7_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian7_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_MAX_HEALTH russian7_s4 150
							SET_CHAR_HEALTH russian7_s4 150
							SET_CHAR_AREA_VISIBLE russian7_s4 18
							ADD_BLIP_FOR_CHAR russian7_s4 bike7_s4blip
							CHANGE_BLIP_DISPLAY bike7_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike7_s4blip 2
							SET_CHAR_KEEP_TASK russian7_s4 TRUE
							SET_CHAR_SHOOT_RATE russian7_s4 50

							CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1719.6 -1671.764 22.741 russian10_s4
							SET_CHAR_HEALTH russian10_s4 5
							SET_CHAR_HEADING russian10_s4 11.45 //10 stay and shoot at player no duck
							SET_CHAR_DECISION_MAKER russian10_s4 smoke4_DM
							GIVE_WEAPON_TO_CHAR russian10_s4 WEAPONTYPE_MP5 9999
							SET_CHAR_ALLOWED_TO_DUCK russian10_s4 FALSE
							SET_CHAR_AREA_VISIBLE russian10_s4 18
							ADD_BLIP_FOR_CHAR russian10_s4 bike10_s4blip
							CHANGE_BLIP_DISPLAY bike10_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike10_s4blip 2
							SET_CHAR_KEEP_TASK russian10_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1701.897 -1668.039 19.23 russian11_s4
							SET_CHAR_HEADING russian11_s4 276.178 //11 stay and shoot at player no duck
							SET_CHAR_DECISION_MAKER russian11_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian11_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian11_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_AREA_VISIBLE russian11_s4 18
							SET_CHAR_ACCURACY russian11_s4 60
							ADD_BLIP_FOR_CHAR russian11_s4 bike11_s4blip
							CHANGE_BLIP_DISPLAY bike11_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike11_s4blip 2
							SET_CHAR_KEEP_TASK russian11_s4 TRUE
							SET_CHAR_MAX_HEALTH russian11_s4 150
							SET_CHAR_HEALTH russian11_s4 150


							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1725.037 -1672.69 22.735 russian12_s4
							SET_CHAR_HEADING russian12_s4 127.283 //->1728.416 -1669.709 22.64935 -> go to shiwl shoooting ->1721.637 -1662.946 19.27 kill normally
							SET_CHAR_DECISION_MAKER russian12_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian12_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian12_s4 WEAPONTYPE_PISTOL 9999		
							SET_CHAR_ACCURACY russian12_s4 60
							SET_CHAR_AREA_VISIBLE russian12_s4 18
							ADD_BLIP_FOR_CHAR russian12_s4 bike12_s4blip
							CHANGE_BLIP_DISPLAY bike12_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike12_s4blip 2
							SET_CHAR_KEEP_TASK russian12_s4 TRUE
							SET_CHAR_SHOOT_RATE russian12_s4 50

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1733.358 -1664.599 22.747 russian13_s4
							SET_CHAR_HEADING russian13_s4 141.981 // -> 1729.928 -1667.75 21.65 ->1725.52 -1663.737 20.5 -> kill player
							SET_CHAR_DECISION_MAKER russian13_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian13_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian13_s4 WEAPONTYPE_PISTOL 9999		
							SET_CHAR_ACCURACY russian13_s4 50
							SET_CHAR_AREA_VISIBLE russian13_s4 18
							ADD_BLIP_FOR_CHAR russian13_s4 russian13_s4blip
							CHANGE_BLIP_DISPLAY russian13_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE russian13_s4blip 2
							SET_CHAR_KEEP_TASK russian13_s4 TRUE
							SET_CHAR_MAX_HEALTH russian13_s4 150
							SET_CHAR_HEALTH russian13_s4 150

							LOAD_SCENE_IN_DIRECTION 1726.993 -1628.007 20.2 8.98
							LOAD_SCENE_IN_DIRECTION	1726.993 -1628.007 20.2 169.7

							smoke_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	//
	IF smoke_s4flag = 2
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_IN_ANY_CAR big_smoke
				IF NOT IS_CHAR_IN_ANY_CAR scplayer

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
					SET_CHAR_COORDINATES scplayer 1726.04 -1632.521 19.18
					SET_CHAR_HEADING scplayer 180.0
					SET_CHAR_COORDINATES big_smoke 	1726.815 -1633.001 19.18
					SET_CHAR_HEADING big_smoke 180.0
					CREATE_CHAR PEDTYPE_MISSION1 WFYRI 1727.757 -1632.918 19.18 hoochie_s4
					SET_CHAR_HEADING hoochie_s4 270.0
					SET_CHAR_DECISION_MAKER hoochie_s4 smoke4_DM
					SHUT_CHAR_UP hoochie_s4 TRUE
					TIMERB = 0
					SET_FIXED_CAMERA_POSITION 1728.489 -1633.539 20.16 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1726.404 -1632.053 20.279 JUMP_CUT
					TIMERA = 0

					cutscene_s4flag = 1
					smoke_s4flag = 8
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF smoke_s4flag = 8

		IF cutscene_s4flag = 1
			IF TIMERA > 1000
				CLEAR_PRINTS
				DO_FADE 500 FADE_IN
				cutscene_s4flag = 2
			ENDIF
		ENDIF

		IF cutscene_s4flag = 2
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD hoochie_s4
					SET_CHAR_COLLISION hoochie_s4 FALSE
					SET_CHAR_COLLISION big_smoke FALSE
					SET_CHAR_COLLISION scplayer FALSE	//////////////////////////////////////SET THIS BACK IF SKIPPED
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer player_01 JST_BUISNESS 1000.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE big_smoke smoke_01 JST_BUISNESS 1000.0 FALSE FALSE FALSE TRUE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE hoochie_s4 girl_01 JST_BUISNESS 1000.0 FALSE FALSE FALSE TRUE -1
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED 
	           		TIMERB = 0
					//////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////
					skipcutscene_s4flag = 0
					SKIP_CUTSCENE_START
					//////////////////////////////////////////////////////////////////////////////////////
					//////////////////////////////////////////////////////////////////////////////////////
					cutscene_s4flag = 3
				ENDIF
			ENDIF
		ENDIF

		//camera angles
		IF cutscene_s4flag = 3
			IF TIMERB > 2066
				SET_FIXED_CAMERA_POSITION 1726.3505 -1630.8242 20.7289 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1726.4110 -1631.8223 20.7195 JUMP_CUT
				PLAY_MISSION_AUDIO 1
				IF NOT IS_CHAR_DEAD big_smoke
					START_CHAR_FACIAL_TALK big_smoke 10000
				ENDIF
				PRINT_NOW SMO4_BA 5000 1 //Before I walk in there I just gotta know you’re down for this.
				cutscene_s4flag = 4
			ENDIF
		ENDIF

		IF cutscene_s4flag = 4
			IF TIMERB > 4999
				IF HAS_MISSION_AUDIO_FINISHED 1
					SET_FIXED_CAMERA_POSITION 1726.3701 -1633.3800 20.6705 0.0 0.0 0.0 //1726.3096 -1632.9805 20.7301 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1726.4155 -1632.3811 20.6667 JUMP_CUT		
					CLEAR_PRINTS
					PLAY_MISSION_AUDIO 2
					IF NOT IS_CHAR_DEAD big_smoke
						STOP_CHAR_FACIAL_TALK big_smoke
					ENDIF
					PRINT_NOW SMO4_BB 5000 1//Look, Smoke, we go way back.  We Groves, man!
					START_CHAR_FACIAL_TALK scplayer 10000
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO 1 SOUND_SMO4_BC //That’s my dog!
					cutscene_s4flag = 5
				ENDIF
			ENDIF
		ENDIF

		IF cutscene_s4flag = 5
			IF HAS_MISSION_AUDIO_FINISHED 2
				IF HAS_MISSION_AUDIO_LOADED 1
					STOP_CHAR_FACIAL_TALK scplayer
					PLAY_MISSION_AUDIO 1
					IF NOT IS_CHAR_DEAD big_smoke
						START_CHAR_FACIAL_TALK big_smoke 10000
					ENDIF
					PRINT_NOW SMO4_BC 5000 1 //That's my dog!
					CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO 2 SOUND_SMO4_BD	//If you hear the shit start to fly, come in blasting!
					cutscene_s4flag = 6
				ENDIF
			ENDIF
		ENDIF

		IF cutscene_s4flag = 6
			IF HAS_MISSION_AUDIO_FINISHED 1
				IF HAS_MISSION_AUDIO_LOADED 2
					PLAY_MISSION_AUDIO 2
					PRINT_NOW SMO4_BD 5000 1	//If you hear the shit start to fly, come in blasting!
					LOAD_MISSION_AUDIO 1 SOUND_SMO4_BE //I'm down, dog.
					cutscene_s4flag = 7
				ENDIF
			ENDIF
		ENDIF


		IF cutscene_s4flag = 7
			IF TIMERB > 9999
				IF NOT IS_CHAR_DEAD hoochie_s4
					IF HAS_MISSION_AUDIO_FINISHED 2
						IF HAS_MISSION_AUDIO_LOADED 1
							IF NOT IS_CHAR_DEAD big_smoke
								STOP_CHAR_FACIAL_TALK big_smoke
							ENDIF
							CLEAR_CHAR_TASKS_IMMEDIATELY hoochie_s4
							SET_CHAR_COORDINATES hoochie_s4 1732.785 -1633.043 19.2
							SET_CHAR_HEADING hoochie_s4 0.0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE hoochie_s4 girl_02 JST_BUISNESS 1000.0 FALSE FALSE FALSE TRUE -1
							PLAY_MISSION_AUDIO 1
							PRINT_NOW SMO4_BE 3000 1 //I'm down, dog.
							SET_NEAR_CLIP 0.09
							START_CHAR_FACIAL_TALK scplayer	2000
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_SMO4_BF	//Hey, baby, want company?
							stoptalk_s4flag = 1
							cutscene_s4flag = 8
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF stoptalk_s4flag = 1
			IF HAS_MISSION_AUDIO_FINISHED 1
				STOP_CHAR_FACIAL_TALK scplayer
				stoptalk_s4flag = 2
			ENDIF
		ENDIF

		IF cutscene_s4flag = 8
			IF TIMERB > 18500//17165
				IF HAS_MISSION_AUDIO_LOADED 2
					IF HAS_MISSION_AUDIO_FINISHED 1
						SET_FIXED_CAMERA_POSITION 1725.0935 -1632.4459 20.7074 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 1726.0913 -1632.3909 20.6711 JUMP_CUT
						SET_NEAR_CLIP 0.1
						PLAY_MISSION_AUDIO 2
						PRINT_NOW SMO4_BF 3000 1 //Hey, baby, want company?
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SMO4_BG	//Motherfuckers!
						cutscene_s4flag = 9
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF cutscene_s4flag = 9
			IF HAS_MISSION_AUDIO_FINISHED 2
				IF HAS_MISSION_AUDIO_LOADED 1
					STOP_CHAR_FACIAL_TALK scplayer
					PLAY_MISSION_AUDIO 1
					PRINT_NOW SMO4_BG 5000 1 //Motherfuckers!
					cutscene_s4flag = 10
				ENDIF
			ENDIF
		ENDIF

		IF cutscene_s4flag = 10
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD big_smoke
					IF IS_CHAR_PLAYING_ANIM scplayer player_01
						GET_CHAR_ANIM_CURRENT_TIME scplayer player_01 animtime_s4
						IF animtime_s4 = 1.0
							smoke_s4flag = 10
							cutscene_s4flag = 11
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF smoke_s4flag = 10
		IF TIMERA > 2000
			IF NOT IS_CHAR_DEAD big_smoke

				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2

				CLEAR_PRINTS

				//////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////
				skipcutscene_s4flag = 1
				SKIP_CUTSCENE_END
				//////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////

				DO_FADE 1000 FADE_OUT

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2

				CLEAR_PRINTS
				SET_AREA_VISIBLE 18
				LOAD_SCENE 1722.81 -1658.54 20.19

				//PLAYER HAS SKIPPED CUTSCENE
				IF skipcutscene_s4flag = 0
					CLEAR_PRINTS

					DELETE_CHAR people1_s4
					DELETE_CHAR people2_s4
					DELETE_CHAR people3_s4
					DELETE_CHAR people4_s4
					DELETE_CHAR people5_s4
					DELETE_CHAR russian1_s4
					DELETE_CHAR russian2_s4
					DELETE_CHAR russian3_s4
					DELETE_CHAR russian4_s4
					DELETE_CHAR russian5_s4
					DELETE_CHAR russian6_s4
					DELETE_CHAR russian7_s4
					DELETE_CHAR russian10_s4
					DELETE_CHAR russian11_s4
					DELETE_CHAR russian12_s4
					DELETE_CHAR russian13_s4

					MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
					MARK_MODEL_AS_NO_LONGER_NEEDED WFYST
					DELETE_CAR smokecar_s4
					MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE

					//create russians
					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1729.973 -1649.115 19.274 russian1_s4
					SET_CHAR_HEADING russian1_s4 11.051 //duck and shoot smoke
					SET_CHAR_DECISION_MAKER russian1_s4 smoke4_DM
					GIVE_WEAPON_TO_CHAR russian1_s4 WEAPONTYPE_MP5 9999
					SET_CHAR_AREA_VISIBLE russian1_s4 18
					SET_CHAR_HEALTH russian1_s4 35
					ADD_BLIP_FOR_CHAR russian1_s4 bike1_s4blip
					CHANGE_BLIP_DISPLAY bike1_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike1_s4blip 2
					SET_CHAR_KEEP_TASK russian1_s4 TRUE

					CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1729.971 -1657.139 19.278 russian2_s4
					SET_CHAR_HEALTH russian2_s4 10
					SET_CHAR_HEADING russian2_s4  21.106 //2 -> no duck stay shoot player (bullets)
					SET_CHAR_DECISION_MAKER russian2_s4 smoke4_DM
					SET_CHAR_WEAPON_SKILL russian2_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian2_s4 WEAPONTYPE_PISTOL 9999
					SET_CHAR_ACCURACY russian2_s4 60
					SET_CHAR_AREA_VISIBLE russian2_s4 18
					ADD_BLIP_FOR_CHAR russian2_s4 bike2_s4blip
					CHANGE_BLIP_DISPLAY bike2_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike2_s4blip 2
					SET_CHAR_SHOOT_RATE russian2_s4 50
					SET_CHAR_KEEP_TASK russian2_s4 TRUE

					CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1722.311 -1658.636 19.108 russian3_s4
					SET_CHAR_HEADING russian3_s4 352.526 //3 duck stay and shoot smoke (bullets)
					SET_CHAR_DECISION_MAKER russian3_s4 smoke4duck_DM
					SET_CHAR_WEAPON_SKILL russian3_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian3_s4 WEAPONTYPE_PISTOL 9999
					SET_CHAR_AREA_VISIBLE russian3_s4 18
					ADD_BLIP_FOR_CHAR russian3_s4 bike3_s4blip
					CHANGE_BLIP_DISPLAY bike3_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike3_s4blip 2
					SET_CHAR_KEEP_TASK russian3_s4 TRUE

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1712.399 -1657.463 19.26 russian4_s4
					SET_CHAR_HEALTH russian4_s4 150
					SET_CHAR_HEADING russian4_s4 323.108 //4 - shoot player duck stay and shoot (bullets)
					SET_CHAR_DECISION_MAKER russian4_s4 smoke4duck_DM
					SET_CHAR_WEAPON_SKILL russian4_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian4_s4 WEAPONTYPE_PISTOL 9999
					SET_CHAR_AREA_VISIBLE russian4_s4 18
					ADD_BLIP_FOR_CHAR russian4_s4 bike4_s4blip
					CHANGE_BLIP_DISPLAY bike4_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike4_s4blip 2
					SET_CHAR_ACCURACY russian4_s4 60
					SET_CHAR_KEEP_TASK russian4_s4 TRUE
					SET_CHAR_MAX_HEALTH russian4_s4 150
					SET_CHAR_HEALTH russian4_s4 150
					SET_CHAR_SHOOT_RATE russian4_s4 50

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1712.763 -1664.816 19.273 russian5_s4
					SET_CHAR_HEADING russian5_s4 329.331 //5 shoot player no duck (bullets)
					SET_CHAR_DECISION_MAKER russian5_s4 smoke4duck_DM
					GIVE_WEAPON_TO_CHAR russian5_s4 WEAPONTYPE_MP5 9999
					SET_CHAR_ACCURACY russian5_s4 70
					SET_CHAR_MAX_HEALTH russian5_s4 150
					SET_CHAR_HEALTH russian5_s4 150
					SET_CHAR_AREA_VISIBLE russian5_s4 18
					ADD_BLIP_FOR_CHAR russian5_s4 bike5_s4blip
					CHANGE_BLIP_DISPLAY bike5_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike5_s4blip 2
					SET_CHAR_KEEP_TASK russian5_s4 TRUE

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1718.711 -1655.182 19.108 russian6_s4
					SET_CHAR_HEADING russian6_s4 327.782 //6 shoot player stay duck 
					SET_CHAR_DECISION_MAKER russian6_s4 smoke4_DM
					SET_CHAR_WEAPON_SKILL russian6_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian6_s4 WEAPONTYPE_PISTOL 9999
					SET_CHAR_AREA_VISIBLE russian6_s4 18
					ADD_BLIP_FOR_CHAR russian6_s4 bike6_s4blip
					CHANGE_BLIP_DISPLAY bike6_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike6_s4blip 2
					SET_CHAR_ACCURACY russian6_s4 60
					SET_CHAR_KEEP_TASK russian6_s4 TRUE

					//create russians
					CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1701.8 -1650.28 19.26 russian7_s4
					SET_CHAR_HEADING russian7_s4 265.623 //7 -> kill normally  1720.069 -1649.467 19.27
					SET_CHAR_DECISION_MAKER russian7_s4 smoke4_DM
					SET_CHAR_WEAPON_SKILL russian7_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian7_s4 WEAPONTYPE_PISTOL 9999
					SET_CHAR_MAX_HEALTH russian7_s4 150
					SET_CHAR_HEALTH russian7_s4 150
					SET_CHAR_AREA_VISIBLE russian7_s4 18
					ADD_BLIP_FOR_CHAR russian7_s4 bike7_s4blip
					CHANGE_BLIP_DISPLAY bike7_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike7_s4blip 2
					SET_CHAR_KEEP_TASK russian7_s4 TRUE
					SET_CHAR_SHOOT_RATE russian7_s4 60

					CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1719.6 -1671.764 22.741 russian10_s4
					SET_CHAR_HEALTH russian10_s4 5
					SET_CHAR_HEADING russian10_s4 11.45 //10 stay and shoot at player no duck
					SET_CHAR_DECISION_MAKER russian10_s4 smoke4_DM
					GIVE_WEAPON_TO_CHAR russian10_s4 WEAPONTYPE_MP5 9999
					SET_CHAR_ALLOWED_TO_DUCK russian10_s4 FALSE
					SET_CHAR_AREA_VISIBLE russian10_s4 18
					ADD_BLIP_FOR_CHAR russian10_s4 bike10_s4blip
					CHANGE_BLIP_DISPLAY bike10_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike10_s4blip 2
					SET_CHAR_KEEP_TASK russian10_s4 TRUE

					CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1701.897 -1668.039 19.23 russian11_s4
					SET_CHAR_HEADING russian11_s4 276.178 //11 stay and shoot at player no duck
					SET_CHAR_DECISION_MAKER russian11_s4 smoke4_DM
					SET_CHAR_WEAPON_SKILL russian11_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian11_s4 WEAPONTYPE_PISTOL 9999
					SET_CHAR_AREA_VISIBLE russian11_s4 18
					SET_CHAR_ACCURACY russian11_s4 60
					ADD_BLIP_FOR_CHAR russian11_s4 bike11_s4blip
					CHANGE_BLIP_DISPLAY bike11_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike11_s4blip 2
					SET_CHAR_KEEP_TASK russian11_s4 TRUE
					SET_CHAR_MAX_HEALTH russian11_s4 150
					SET_CHAR_HEALTH russian11_s4 150

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1725.037 -1672.69 22.735 russian12_s4
					SET_CHAR_HEADING russian12_s4 127.283 //->1728.416 -1669.709 22.64935 -> go to shiwl shoooting ->1721.637 -1662.946 19.27 kill normally
					SET_CHAR_DECISION_MAKER russian12_s4 smoke4_DM
					SET_CHAR_WEAPON_SKILL russian12_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian12_s4 WEAPONTYPE_PISTOL 9999		
					SET_CHAR_ACCURACY russian12_s4 60
					SET_CHAR_AREA_VISIBLE russian12_s4 18
					ADD_BLIP_FOR_CHAR russian12_s4 bike12_s4blip
					CHANGE_BLIP_DISPLAY bike12_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike12_s4blip 2
					SET_CHAR_KEEP_TASK russian12_s4 TRUE
					SET_CHAR_SHOOT_RATE russian12_s4 50

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1733.358 -1664.599 22.747 russian13_s4
					SET_CHAR_HEADING russian13_s4 141.981 // -> 1729.928 -1667.75 21.65 ->1725.52 -1663.737 20.5 -> kill player
					SET_CHAR_DECISION_MAKER russian13_s4 smoke4_DM
					SET_CHAR_WEAPON_SKILL russian13_s4 WEAPONSKILL_PRO
					GIVE_WEAPON_TO_CHAR russian13_s4 WEAPONTYPE_PISTOL 9999		
					SET_CHAR_ACCURACY russian13_s4 65
					SET_CHAR_AREA_VISIBLE russian13_s4 18
					ADD_BLIP_FOR_CHAR russian13_s4 russian13_s4blip
					CHANGE_BLIP_DISPLAY russian13_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE russian13_s4blip 2
					SET_CHAR_KEEP_TASK russian13_s4 TRUE
					SET_CHAR_MAX_HEALTH russian13_s4 150
					SET_CHAR_HEALTH russian13_s4 150


					//////////////////////////////////////

				ENDIF

				IF smokeshoot_s4flag = 0
					IF NOT IS_CHAR_DEAD big_smoke 
						CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
						SET_CHAR_COLLISION big_smoke TRUE
						SET_CHAR_COLLISION scplayer TRUE

						STOP_CHAR_FACIAL_TALK big_smoke
						STOP_CHAR_FACIAL_TALK scplayer

						SET_CHAR_COORDINATES big_smoke 1727.8838 -1641.1274 19.2323 
						SET_CHAR_HEADING big_smoke 182.5173
						SET_CHAR_HAS_USED_ENTRY_EXIT big_smoke 1727.009 -1635.826 1.0
						ADD_BLIP_FOR_CHAR big_smoke smoke_s4blip
						SET_CHAR_ACCURACY big_smoke 100
						SET_CHAR_AREA_VISIBLE big_smoke 18
						SET_BLIP_AS_FRIENDLY smoke_s4blip TRUE

						SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 1727.009 -1635.826 1.0
						RESTORE_CAMERA_JUMPCUT
						SET_CAMERA_BEHIND_PLAYER				
						SWITCH_WIDESCREEN OFF

						LOAD_SCENE 1727.8838 -1641.1274 19.2323
						GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_MICRO_UZI 99999
						SET_CHAR_MAX_HEALTH big_smoke 500
						SET_CHAR_HEALTH big_smoke 500
						GET_CHAR_HEALTH big_smoke smokehealth_s4
						SET_CHAR_SHOOT_RATE big_smoke 55
						smokehealth_s4 = smokehealth_s4 / 5
						DISPLAY_ONSCREEN_COUNTER_WITH_STRING smokehealth_s4 COUNTER_DISPLAY_BAR DGS1_58
						TASK_TOGGLE_DUCK big_smoke TRUE
						smokeshoot_s4flag = 1
					ENDIF
				ENDIF

				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_SMO4_BL
				SWITCH_WIDESCREEN ON

				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_COORDINATES scplayer 1725.9333 -1638.3741 19.2232 
				SET_CHAR_HEADING scplayer 174.7897 
				IF NOT IS_CHAR_DEAD big_smoke
					SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE
				ENDIF

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE

				DO_FADE 250 FADE_IN

				IF HAS_MISSION_AUDIO_LOADED 1
					PLAY_MISSION_AUDIO 1 //CJ, get in he(re)- oh there you are!
					PRINT_NOW SMO4_BL 2500 1
				ENDIF


				IF NOT IS_CHAR_DEAD russian1_s4
					IF NOT IS_CHAR_DEAD big_smoke
						enemy_s4 = russian1_s4
						enemytarget_s4 = big_smoke
						GOSUB stayshoot_s4label
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD russian3_s4
					IF NOT IS_CHAR_DEAD big_smoke
						enemy_s4 = russian3_s4
						enemytarget_s4 = big_smoke
						GOSUB stayshootnoduck_s4label
					ENDIF
				ENDIF

				SET_FIXED_CAMERA_POSITION 1726.5278 -1637.8292 21.2570 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1726.4767 -1638.7794 20.9496 JUMP_CUT


				TASK_TOGGLE_DUCK scplayer TRUE

				TASK_GO_STRAIGHT_TO_COORD scplayer 1724.626 -1640.693 20.2245 PEDMOVE_WALK -1
				TIMERA = 0
				WHILE NOT LOCATE_STOPPED_CHAR_ANY_MEANS_2D scplayer 1724.626 -1640.693 1.5 1.5 FALSE
					OR NOT TIMERA > 1500
					WAIT 0
				ENDWHILE

				IF HAS_MISSION_AUDIO_FINISHED 1
					CLEAR_PRINTS
				ENDIF

				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 120
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				SET_CHAR_HEADING scplayer 180.0
				SWITCH_WIDESCREEN OFF
				SET_CHAR_COLLISION scplayer TRUE
				REMOVE_ANIMATION JST_BUISNESS
				REMOVE_BLIP atrium_s4blip
				DELETE_CHAR people1_s4
				DELETE_CHAR people2_s4
				DELETE_CHAR people3_s4
				DELETE_CHAR people4_s4
				MARK_MODEL_AS_NO_LONGER_NEEDED WFYST
				MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
				DELETE_CHAR hoochie_s4
				DELETE_CAR smokecar_s4
				MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
				SET_PLAYER_CONTROL PLAYER1 ON
				TASK_TOGGLE_DUCK scplayer TRUE				 
				IF NOT IS_CHAR_DEAD big_smoke
					SET_CHAR_PROOFS big_smoke FALSE FALSE FALSE FALSE FALSE
				ENDIF
				CLEAR_MISSION_AUDIO 1				
				shootout_s4flag = 1
				progressaudio_s4flag = 0
				handlingudio_s4flag = 0
  				helpshoottext_s4flag = 1
				smoke_s4flag = 11
				TIMERA = 0

				slowdownstart_s4flag = 2 //1
			ENDIF
		ENDIF
	ENDIF

ENDIF
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////	Shootout inside Atrium	///////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//IF slowdownstart_s4flag = 1
//	IF TIMERA > 1500
//	OR NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 1725.629 -1641.05 4.0 4.0 FALSE
//		slowdownstart_s4flag = 2
//	ENDIF
//ENDIF

IF smoke_s4flag = 11
	IF slowdownstart_s4flag = 2
	
		IF NOT IS_CHAR_DEAD big_smoke
			GET_CHAR_HEALTH big_smoke smokehealth_s4
			smokehealth_s4 = smokehealth_s4 / 5
		ENDIF
		
		IF warp_s4flag = 0
		
			IF smokeshoot_s4flag = 1

				IF NOT IS_CHAR_DEAD russian2_s4
					enemy_s4 = russian2_s4
					enemytarget_s4 = scplayer
					GOSUB stayshootnoduck_s4label
				ENDIF

				IF NOT IS_CHAR_DEAD russian4_s4
					enemy_s4 = russian4_s4
					enemytarget_s4 = scplayer
					GOSUB stayshoot_s4label
				ENDIF

				IF NOT IS_CHAR_DEAD russian5_s4
					IF NOT IS_CHAR_DEAD big_smoke
						enemy_s4 = russian5_s4
						enemytarget_s4 = big_smoke
						GOSUB stayshootnoduck_s4label
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD russian6_s4
					enemy_s4 = russian6_s4
					enemytarget_s4 = scplayer
					GOSUB stayshoot_s4label
				ENDIF

				IF NOT IS_CHAR_DEAD russian10_s4
					enemy_s4 = russian10_s4
					enemytarget_s4 = scplayer
					GOSUB stayshootnoduck_s4label
				ENDIF

				IF NOT IS_CHAR_DEAD russian12_s4
					enemy_s4 = russian12_s4
					enemytarget_s4 = scplayer
					enemyx_s4 = 1728.416
					enemyy_s4 = -1669.709
					enemyz_s4 = 22.84935 
					enemyx2_s4 = 1721.637
					enemyy2_s4 = -1662.946
					enemyz2_s4 = 20.27 
					GOSUB run2kill_s4label
				ENDIF

				IF NOT IS_CHAR_DEAD big_smoke
					//PRINT_NOW DGS1_56 7000 1 //~s~Get inside and protect Big Smoke!
					text_s4flag = 1
					OPEN_SEQUENCE_TASK smoke1_s4seq
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					IF NOT IS_CHAR_DEAD russian1_s4
						TASK_KILL_CHAR_ON_FOOT -1 russian1_s4
					ENDIF
					IF NOT IS_CHAR_DEAD russian2_s4
						TASK_KILL_CHAR_ON_FOOT -1 russian2_s4
					ENDIF
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_GO_STRAIGHT_TO_COORD -1 1727.7167 -1658.271 20.277 PEDMOVE_RUN -1
					CLOSE_SEQUENCE_TASK smoke1_s4seq
					PERFORM_SEQUENCE_TASK big_smoke smoke1_s4seq
					CLEAR_SEQUENCE_TASK smoke1_s4seq
				ENDIF
				TIMERA = 0
				smokeshoot_s4flag = 2
			ENDIF



			//////////////////////////	smoke behaviour
			IF smokeshoot_s4flag = 2
				IF NOT IS_CHAR_DEAD big_smoke
					GET_SCRIPT_TASK_STATUS big_smoke PERFORM_SEQUENCE_TASK sequencetask_s4
						IF sequencetask_s4 = FINISHED_TASK //OR LOCATE_STOPPED_CHAR_ON_FOOT_2D big_smoke 1727.7167 -1658.271 1.0 1.0 FALSE 
							SET_CHAR_ACCURACY big_smoke 50
							OPEN_SEQUENCE_TASK smoke2_s4seq
							text_s4flag = 2
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD russian3_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian3_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian11_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian11_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian12_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian12_s4
							ENDIF
							TASK_STAY_IN_SAME_PLACE -1 FALSE
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 1719.854 -1666.667 20.271 PEDMOVE_RUN -1
							//TASK_GO_STRAIGHT_TO_COORD -1 1719.854 -1666.667 20.271 PEDMOVE_RUN -1
							CLOSE_SEQUENCE_TASK smoke2_s4seq
							PERFORM_SEQUENCE_TASK big_smoke smoke2_s4seq
							CLEAR_SEQUENCE_TASK smoke2_s4seq
							smokeshoot_s4flag = 3
						ENDIF
				ENDIF
			ENDIF

			IF smokeshoot_s4flag = 3
				IF NOT IS_CHAR_DEAD big_smoke
					GET_SCRIPT_TASK_STATUS big_smoke PERFORM_SEQUENCE_TASK sequencetask_s4
						IF sequencetask_s4 = FINISHED_TASK //OR LOCATE_STOPPED_CHAR_ON_FOOT_2D big_smoke 1727.7167 -1658.271 1.0 1.0 FALSE 
							text_s4flag = 3
							OPEN_SEQUENCE_TASK smoke3_s4seq
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD russian5_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian5_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian6_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian6_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian7_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian7_s4
							ENDIF
							TASK_STAY_IN_SAME_PLACE -1 FALSE
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 1713.96 -1668.24 19.27 PEDMOVE_RUN -1
							CLOSE_SEQUENCE_TASK smoke3_s4seq
							PERFORM_SEQUENCE_TASK big_smoke smoke3_s4seq
							CLEAR_SEQUENCE_TASK smoke3_s4seq
							smokeshoot_s4flag = 4
						ENDIF
				ENDIF
			ENDIF

			IF smokeshoot_s4flag = 4
				IF NOT IS_CHAR_DEAD big_smoke
					GET_SCRIPT_TASK_STATUS big_smoke PERFORM_SEQUENCE_TASK sequencetask_s4
						IF sequencetask_s4 = FINISHED_TASK //OR LOCATE_STOPPED_CHAR_ON_FOOT_2D big_smoke 1727.7167 -1658.271 1.0 1.0 FALSE 
							text_s4flag = 4
							OPEN_SEQUENCE_TASK smoke4_s4seq
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							TASK_TOGGLE_DUCK -1 TRUE
							IF NOT IS_CHAR_DEAD russian4_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian4_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian10_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian10_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian13_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian13_s4
							ENDIF
							CLOSE_SEQUENCE_TASK smoke4_s4seq
							PERFORM_SEQUENCE_TASK big_smoke smoke4_s4seq
							CLEAR_SEQUENCE_TASK smoke4_s4seq
							smokeshoot_s4flag = 5
						ENDIF
				ENDIF
			ENDIF

			//out the atrium
			IF smokeshoot_s4flag = 5
				IF NOT IS_CHAR_DEAD big_smoke
					GET_SCRIPT_TASK_STATUS big_smoke PERFORM_SEQUENCE_TASK sequencetask_s4
						IF sequencetask_s4 = FINISHED_TASK 
							text_s4flag = 5
							OPEN_SEQUENCE_TASK smoke5_s4seq
							TASK_STAY_IN_SAME_PLACE -1 FALSE
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 1704.45 -1667.71 19.68 PEDMOVE_RUN -1
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							CLOSE_SEQUENCE_TASK smoke5_s4seq
							PERFORM_SEQUENCE_TASK big_smoke smoke5_s4seq
							CLEAR_SEQUENCE_TASK smoke5_s4seq
							smokeshoot_s4flag = 6
						ENDIF
				ENDIF
			ENDIF


			///////////////////////////////////////////	FIRST SET OF GUYS

			IF russian3_s4flag = 0
				IF NOT IS_CHAR_DEAD russian3_s4
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR russian3_s4 scplayer
						TASK_STAY_IN_SAME_PLACE russian3_s4 FALSE
						TASK_KILL_CHAR_ON_FOOT russian3_s4 scplayer
						russian3_s4flag = 1
					ENDIF
				ENDIF
			ENDIF

			IF russian12_s4flag = 0
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1727.0 -1637.0 20.0 3.0 3.0 5.0 FALSE
				OR LOCATE_CHAR_ANY_MEANS_3D scplayer 1700.19 -1667.58 20.0 6.5 6.5 5.0 FALSE

					REMOVE_CHAR_ELEGANTLY people5_s4


					russian12_s4flag = 1
				ENDIF
			ENDIF

			IF russian7_s4flag = 0
				IF DOES_CHAR_EXIST russian7_s4 
					IF IS_CHAR_IN_AREA_3D scplayer 1707.16 -1643.73 17.0 1735.9 -1653.46 22.0 FALSE
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR russian7_s4 scplayer
					OR IS_CHAR_DEAD russian3_s4

						IF NOT IS_CHAR_DEAD russian7_s4
							enemy_s4 = russian7_s4
							enemytarget_s4 = scplayer
							enemyx_s4 = 1725.069
							enemyy_s4 = -1649.467
							enemyz_s4 = 19.27
							SET_CHAR_MONEY russian7_s4 250
							GOSUB runkill_s4label
						ENDIF

						IF NOT IS_CHAR_DEAD russian11_s4
							enemy_s4 = russian11_s4
							enemytarget_s4 = scplayer
							enemyx_s4 = 1717.0
							enemyy_s4 = -1667.75
							enemyz_s4 = 20.27
							enemyx2_s4 = 1715.609
							enemyy2_s4 = -1657.258
							enemyz2_s4 = 20.271
							GOSUB run2kill_s4label
						ENDIF

					    russian7_s4flag = 1
					ENDIF
				ENDIF
			ENDIF



			IF russian13_s4flag = 0
				IF DOES_CHAR_EXIST russian13_s4 
					IF IS_CHAR_IN_AREA_3D scplayer 1738.42 -1651.07 17.0 1698.7 -1659.61 22.0 FALSE
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR russian13_s4 scplayer
					OR IS_CHAR_DEAD russian4_s4
						
						IF NOT IS_CHAR_DEAD russian13_s4
							enemy_s4 = russian13_s4
							enemytarget_s4 = scplayer
							enemyx_s4 = 1729.928
							enemyy_s4 = -1667.75
							enemyz_s4 = 22.85 
							enemyx2_s4 = 1725.52
							enemyy2_s4 = -1663.737
							enemyz2_s4 = 20.5
							GOSUB run2kill_s4label
						ENDIF
						russian13_s4flag = 1
					ENDIF
				ENDIF
			ENDIF

			IF smokeshoot_s4flag < 7

				IF russian1remove_s4flag = 0
					IF IS_CHAR_DEAD russian1_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian1_s4
						REMOVE_BLIP bike1_s4blip
						russianseliminated_s3counter ++
						russian1remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian2remove_s4flag = 0
					IF IS_CHAR_DEAD russian2_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian2_s4
						REMOVE_BLIP bike2_s4blip
						russianseliminated_s3counter ++
						russian2remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian3remove_s4flag = 0
					IF IS_CHAR_DEAD russian3_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian3_s4
						REMOVE_BLIP bike3_s4blip
						russianseliminated_s3counter ++
						russian3remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian4remove_s4flag = 0
					IF IS_CHAR_DEAD russian4_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian4_s4
						REMOVE_BLIP bike4_s4blip
						russianseliminated_s3counter ++
						russian4remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian5remove_s4flag = 0
					IF IS_CHAR_DEAD russian5_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian5_s4
						REMOVE_BLIP bike5_s4blip
						russianseliminated_s3counter ++
						russian5remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian6remove_s4flag = 0
					IF IS_CHAR_DEAD russian6_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian6_s4
						REMOVE_BLIP bike6_s4blip
						russianseliminated_s3counter ++
						russian6remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian7remove_s4flag = 0
					IF IS_CHAR_DEAD russian7_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian7_s4
						REMOVE_BLIP bike7_s4blip
						russianseliminated_s3counter ++
						russian7remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian10remove_s4flag = 0
					IF IS_CHAR_DEAD russian10_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian10_s4
						REMOVE_BLIP bike10_s4blip
						russianseliminated_s3counter ++
						russian10remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian11remove_s4flag = 0
					IF IS_CHAR_DEAD russian11_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian11_s4
						REMOVE_BLIP bike11_s4blip
						russianseliminated_s3counter ++
						russian11remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian12remove_s4flag = 0
					IF IS_CHAR_DEAD russian12_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian12_s4
						REMOVE_BLIP bike12_s4blip
						russianseliminated_s3counter ++
						russian12remove_s4flag = 1
					ENDIF
				ENDIF
				IF russian13remove_s4flag = 0
					IF IS_CHAR_DEAD russian13_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian13_s4
						REMOVE_BLIP russian13_s4blip
						russianseliminated_s3counter ++
						russian13remove_s4flag = 1
					ENDIF
				ENDIF

				
			ENDIF

			//dialogue
			IF smoke_s4flag = 11
				IF warp_s4flag = 0
					
					GOSUB process_audio_s4

					//play mission audio
					IF progressaudio_s4flag = 0
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_BO	//Use some cover, they’s blasting like fools!
							$input_text_s4 = SMO4_BO	//Use some cover, they’s blasting like fools!
							GOSUB load_audio_s4
						ENDIF
					ENDIF

					IF progressaudio_s4flag = 1
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_EK //Ice those fools!
							$input_text_s4 = SMO4_EK //Ice those fools!
							GOSUB load_audio_s4
						ENDIF
					ENDIF

					IF progressaudio_s4flag = 2
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_DA	//Big Smoke, you made big mistake, huh!
							$input_text_s4 = SMO4_DA	//Big Smoke, you made big mistake, huh!
							GOSUB load_audio_s4
						ENDIF
					ENDIF

					IF progressaudio_s4flag = 3
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_BN	//Watch yourself, CJ, I think they’s pissed!
							$input_text_s4 = SMO4_BN	//Watch yourself, CJ, I think they’s pissed!
							GOSUB load_audio_s4
						ENDIF
					ENDIF

					IF progressaudio_s4flag = 4
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_BP	//Take the right and cover my ass! 
							$input_text_s4 = SMO4_BP	//Take the right and cover my ass! 
							GOSUB load_audio_s4
						ENDIF
					ENDIF

					IF progressaudio_s4flag = 5
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_EG	//Keep it up, CJ!
							$input_text_s4 = SMO4_EG	//Keep it up, CJ!
							GOSUB load_audio_s4
						ENDIF
					ENDIF

//					IF progressaudio_s4flag = 9
//						IF handlingudio_s4flag = 0
//							audio_label_s4 = SOUND_SMO4_ED //Back me up, here!
//							$input_text_s4 = SMO4_ED //Back me up, here!
//							GOSUB load_audio_s4
//						ENDIF
//					ENDIF

					IF progressaudio_s4flag = 6
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_EJ	//My homie is making y’all pay! 
							$input_text_s4 = SMO4_EJ	//My homie is making y’all pay! 
							GOSUB load_audio_s4
						ENDIF
					ENDIF

					IF oneoff_s4flag = 0
						IF smokeshoot_s4flag = 6
						OR russianseliminated_s3counter = 11
							handlingudio_s4flag = 0
							progressaudio_s4flag = 12
							oneoff_s4flag = 1
						ENDIF
					ENDIF

					IF progressaudio_s4flag = 12
						IF handlingudio_s4flag = 0
							audio_label_s4 = SOUND_SMO4_DJ	//Stick with me, CJ, we’re out of here! 
							$input_text_s4 = SMO4_DJ	//Stick with me, CJ, we’re out of here! 
							GOSUB load_audio_s4
						ENDIF
					ENDIF

				ENDIF
			ENDIF

			IF helpshoottext_s4flag = 1
				PRINT_HELP DGS1_63 //Shooting whilst you are crouching improves your accuracy.
				TIMERB = 0
				helpshoottext_s4flag = 2
			ENDIF

			IF helpshoottext_s4flag = 2
				IF TIMERB > 10000
					PRINT_HELP DGS1_2 //If enemies are using cover it may be better to use precision aimong on the ~h~right analoge stick ~w~whilst shooting.
					TIMERB = 0
					helpshoottext_s4flag = 3
				ENDIF
			ENDIF

			//warp smoke outside
			IF NOT IS_CHAR_DEAD big_smoke
				IF LOCATE_CHAR_ON_FOOT_3D big_smoke 1704.45 -1667.71 19.68 6.0 6.0 4.0 FALSE
					IF LOCATE_CHAR_ON_FOOT_3D scplayer 1700.49 -1667.87 19.46 8.5 8.5 4.0 FALSE
						IF smokeshoot_s4flag = 6
						OR russianseliminated_s3counter = 11

							SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE

							DO_FADE 500 FADE_OUT

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE

							SET_PLAYER_CONTROL PLAYER1 OFF

							CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

							IF NOT IS_CHAR_DEAD big_smoke	

								SET_AREA_VISIBLE 0
								SET_CHAR_AREA_VISIBLE big_smoke 0
								CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke

								SET_CHAR_AREA_VISIBLE scplayer 0
								//used entry exit
								SET_CHAR_COORDINATES big_smoke 1696.229 -1669.133 19.23
								SET_CHAR_HEADING big_smoke 86.7366

								SET_CHAR_COORDINATES scplayer 1696.339 -1666.897 19.2
								SET_CHAR_HEADING scplayer 85.4

							ENDIF

							// 8 stay and shoot
							CREATE_CHAR PEDTYPE_MISSION1 MAFFB 1682.685 -1671.516 19.24 russian8_s4
							SET_CHAR_HEADING russian8_s4 289.407 //stay in same place shoot smoke stayshoot_s4label
							SET_CHAR_DECISION_MAKER russian8_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian8_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian8_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_ACCURACY russian8_s4 50
							SET_CHAR_HEALTH russian8_s4 150
							ADD_BLIP_FOR_CHAR russian8_s4 bike1_s4blip
							CHANGE_BLIP_DISPLAY bike1_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike1_s4blip 2
							SET_CHAR_KEEP_TASK russian8_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1676.529 -1676.516 20.47 russian14_s4
							SET_CHAR_HEADING russian14_s4 299.59 //stay in same place shoot player no duck stayshootnoduck_s4label
							SET_CHAR_DECISION_MAKER russian14_s4 smoke4_DM
							GIVE_WEAPON_TO_CHAR russian14_s4 WEAPONTYPE_MP5 9999
							SET_CHAR_ACCURACY russian14_s4 30
							SET_CHAR_HEALTH russian14_s4 150
							SET_CHAR_ALLOWED_TO_DUCK russian14_s4 FALSE
							ADD_BLIP_FOR_CHAR russian14_s4 bike2_s4blip
							CHANGE_BLIP_DISPLAY bike2_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike2_s4blip 2
							SET_CHAR_KEEP_TASK russian14_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1645.896 -1667.693 20.472 russian15_s4 //attack player
							SET_CHAR_HEADING russian15_s4 270.18 //run twice ->1654.724 -1667.792 20.469 -> 1675.149 -1667.818 20.47 ideally stay in smale place and kill
							SET_CHAR_DECISION_MAKER russian15_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian15_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian15_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_ACCURACY russian15_s4 60
							SET_CHAR_HEALTH russian15_s4 150
							ADD_BLIP_FOR_CHAR russian15_s4 bike3_s4blip
							CHANGE_BLIP_DISPLAY bike3_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike3_s4blip 2
							SET_CHAR_KEEP_TASK russian15_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1663.022 -1685.541 20.469 russian16_s4
							SET_CHAR_HEADING russian16_s4 301.304 //stay in same place shoot player  
							SET_CHAR_DECISION_MAKER russian16_s4 smoke4_DM
							GIVE_WEAPON_TO_CHAR russian16_s4 WEAPONTYPE_MP5 9999
							SET_CHAR_ACCURACY russian16_s4 50
							SET_CHAR_HEALTH russian16_s4 125
							ADD_BLIP_FOR_CHAR russian16_s4 bike4_s4blip
							CHANGE_BLIP_DISPLAY bike4_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike4_s4blip 2
							SET_CHAR_KEEP_TASK russian16_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1663.079 -1676.61 20.468 russian17_s4
							SET_CHAR_HEADING russian17_s4 268.5 //stay in same place shoot player  stayshootnoduck_s4label
							SET_CHAR_DECISION_MAKER russian17_s4 smoke4_DM
							GIVE_WEAPON_TO_CHAR russian17_s4 WEAPONTYPE_MP5 9999
							SET_CHAR_ACCURACY russian17_s4 60
							SET_CHAR_MAX_HEALTH russian17_s4 125
							SET_CHAR_HEALTH russian17_s4 125
							SET_CHAR_ALLOWED_TO_DUCK russian17_s4 FALSE
							ADD_BLIP_FOR_CHAR russian17_s4 bike5_s4blip
							CHANGE_BLIP_DISPLAY bike5_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike5_s4blip 2
							SET_CHAR_KEEP_TASK russian17_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1662.793 -1670.124 20.463 russian18_s4
							SET_CHAR_HEADING russian18_s4 268.5 //stay in same place shoot smoke 
							SET_CHAR_DECISION_MAKER russian18_s4 smoke4_DM
							GIVE_WEAPON_TO_CHAR russian18_s4 WEAPONTYPE_MP5 9999
							SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian18_s4 TRUE
							SET_CHAR_ACCURACY russian18_s4 60
							SET_CHAR_MAX_HEALTH russian18_s4 150
							SET_CHAR_HEALTH russian18_s4 150
							ADD_BLIP_FOR_CHAR russian18_s4 bike6_s4blip
							CHANGE_BLIP_DISPLAY bike6_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike6_s4blip 2
							SET_CHAR_KEEP_TASK russian18_s4 TRUE

							CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1698.51 -1678.459 19.233 russian9_s4
							SET_CHAR_HEADING russian9_s4 99.699 //run twice -> 1691.416 -1678.243 20.24 -> 1669.863 -1679.125 20.47 ideally stay in smale place and kill
							SET_CHAR_DECISION_MAKER russian9_s4 smoke4_DM
							SET_CHAR_WEAPON_SKILL russian9_s4 WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR russian9_s4 WEAPONTYPE_PISTOL 9999
							SET_CHAR_ACCURACY russian9_s4 30
							SET_CHAR_HEALTH russian9_s4 150
							ADD_BLIP_FOR_CHAR russian9_s4 bike7_s4blip
							CHANGE_BLIP_DISPLAY bike7_s4blip BLIP_ONLY
							CHANGE_BLIP_SCALE bike7_s4blip 2
							SET_CHAR_KEEP_TASK russian9_s4 TRUE
							
							TASK_TOGGLE_DUCK scplayer TRUE
							
							WAIT 500

							IF NOT IS_CHAR_DEAD big_smoke
								SET_CHAR_PROOFS big_smoke FALSE FALSE FALSE FALSE FALSE
							ENDIF

							SET_CAMERA_BEHIND_PLAYER
							SET_PLAYER_CONTROL PLAYER1 ON
							DO_FADE 750 FADE_IN
							russianseliminated_s3counter = 0
							smokeshoot_s4flag = 6
							progressaudio_s4flag = 0
							handlingudio_s4flag = 0
	 						warp_s4flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF

		ENDIF //warp flag

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////	Shootout outside Atrium	///////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


		IF warp_s4flag = 1

			IF smokeshoot_s4flag = 6
				IF NOT IS_CHAR_DEAD big_smoke
					SET_CHAR_ACCURACY big_smoke 80
					OPEN_SEQUENCE_TASK smoke6_s4seq
					TASK_TOGGLE_DUCK -1 TRUE
					TASK_GO_STRAIGHT_TO_COORD -1 1690.24 -1669.363 21.46 PEDMOVE_RUN -1
					TASK_STAY_IN_SAME_PLACE -1 TRUE
					IF NOT IS_CHAR_DEAD russian8_s4
						TASK_KILL_CHAR_ON_FOOT -1 russian8_s4
					ENDIF
					IF NOT IS_CHAR_DEAD russian14_s4
						TASK_KILL_CHAR_ON_FOOT -1 russian14_s4
					ENDIF
					IF NOT IS_CHAR_DEAD russian15_s4
						TASK_KILL_CHAR_ON_FOOT -1 russian15_s4
					ENDIF
					CLOSE_SEQUENCE_TASK smoke6_s4seq
					PERFORM_SEQUENCE_TASK big_smoke smoke6_s4seq
					CLEAR_SEQUENCE_TASK smoke6_s4seq
					smokeshoot_s4flag = 7
				ENDIF
			ENDIF

			IF smokeshoot_s4flag = 7
				IF NOT IS_CHAR_DEAD big_smoke
					GET_SCRIPT_TASK_STATUS big_smoke PERFORM_SEQUENCE_TASK sequencetask_s4
						IF sequencetask_s4 = FINISHED_TASK 
							SET_CHAR_ACCURACY big_smoke 60
							OPEN_SEQUENCE_TASK smoke7_s4seq
							TASK_FOLLOW_PATH_NODES_TO_COORD -1 1669.82 -1671.917 21.43 PEDMOVE_RUN -1
							TASK_STAY_IN_SAME_PLACE -1 TRUE
							IF NOT IS_CHAR_DEAD russian17_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian17_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian15_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian15_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian18_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian18_s4
							ENDIF
							CLOSE_SEQUENCE_TASK smoke7_s4seq
							PERFORM_SEQUENCE_TASK big_smoke smoke7_s4seq
							CLEAR_SEQUENCE_TASK smoke7_s4seq
							smokeshoot_s4flag = 8
						ENDIF
				ENDIF
			ENDIF

			IF smokeshoot_s4flag = 8
				IF NOT IS_CHAR_DEAD big_smoke
					GET_SCRIPT_TASK_STATUS big_smoke PERFORM_SEQUENCE_TASK sequencetask_s4
						IF sequencetask_s4 = FINISHED_TASK 
							OPEN_SEQUENCE_TASK smoke8_s4seq
							TASK_TOGGLE_DUCK -1 TRUE
							IF NOT IS_CHAR_DEAD russian9_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian9_s4
							ENDIF
							IF NOT IS_CHAR_DEAD russian16_s4
								TASK_KILL_CHAR_ON_FOOT -1 russian16_s4
							ENDIF
							CLOSE_SEQUENCE_TASK smoke8_s4seq
							PERFORM_SEQUENCE_TASK big_smoke smoke8_s4seq
							CLEAR_SEQUENCE_TASK smoke8_s4seq
							smokeshoot_s4flag = 9
						ENDIF
				ENDIF
			ENDIF

			IF smokeshoot_s4flag = 9
				IF NOT IS_CHAR_DEAD big_smoke
					GET_SCRIPT_TASK_STATUS big_smoke PERFORM_SEQUENCE_TASK sequencetask_s4
						IF sequencetask_s4 = FINISHED_TASK
						OR russianseliminated_s3counter = 7
							TIMERB = 0
							getonbikecut_s4flag = 1
							smoke_s4flag = 12
						ENDIF
				ENDIF
			ENDIF

			IF russianseliminated_s3counter = 7
				TIMERB = 0
				getonbikecut_s4flag = 1
				smoke_s4flag = 12
			ENDIF
			///////////////////////////////////////////		SECOND SET OF GUYS

			IF russian8_s4flag = 0
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CHAR_DEAD russian8_s4
						IF NOT IS_CHAR_DEAD big_smoke
							enemy_s4 = russian8_s4
							enemytarget_s4 = big_smoke
							GOSUB stayshootnoduck_s4label
						ENDIF
					ENDIF

					IF NOT IS_CHAR_DEAD russian14_s4
						enemy_s4 = russian14_s4
						enemytarget_s4 = scplayer
						GOSUB stayshootnoduck_s4label
					ENDIF

					IF NOT IS_CHAR_DEAD russian16_s4
						IF NOT IS_CHAR_DEAD big_smoke
							enemy_s4 = russian16_s4
							enemytarget_s4 = scplayer
							GOSUB stayshoot_s4label
						ENDIF
					ENDIF

					IF NOT IS_CHAR_DEAD russian17_s4
						enemy_s4 = russian17_s4
						enemytarget_s4 = scplayer
						GOSUB stayshootnoduck_s4label
					ENDIF

					IF NOT IS_CHAR_DEAD russian18_s4
						IF NOT IS_CHAR_DEAD big_smoke
							enemy_s4 = russian18_s4
							enemytarget_s4 = big_smoke
							GOSUB stayshoot_s4label
						ENDIF
					ENDIF
					russian8_s4flag = 1
				ENDIF
			ENDIF

			IF russian9_s4flag = 0
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CHAR_DEAD russian9_s4
						enemy_s4 = russian9_s4
						enemytarget_s4 = scplayer
						enemyx_s4 = 1669.863
						enemyy_s4 = -1679.125
						enemyz_s4 = 20.47
						GOSUB runkillstay_s4label
					ENDIF

					IF NOT IS_CHAR_DEAD russian15_s4
						enemy_s4 = russian15_s4
						enemytarget_s4 = scplayer
						enemyx_s4 = 1675.149
						enemyy_s4 = -1667.818
						enemyz_s4 = 20.47
						GOSUB runkillstay_s4label
					ENDIF
					russian9_s4flag = 1
				ENDIF
			ENDIF

			//play mission audio
			IF progressaudio_s4flag = 0
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_EB	//Stick real close, Carl!
					$input_text_s4 = SMO4_EB	//Stick real close, Carl!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 1
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_BM	//Keep your head down – the air’s thick with lead!
					$input_text_s4 = SMO4_BM	//Keep your head down – the air’s thick with lead!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 2
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_DG	//Smoke, you and your friend are dead men!
					$input_text_s4 = SMO4_DG	//Smoke, you and your friend are dead men!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 3
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_BG	//Motherfuckers!
					$input_text_s4 = SMO4_BG	//Motherfuckers!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 4
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_ED	//Back me up, here!
					$input_text_s4 = SMO4_ED	//Back me up, here!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 5
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_EF	//That’s my dog, CJ!
					$input_text_s4 = SMO4_EF	//That’s my dog, CJ!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 6
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_EH	//Holmes, you are ice cold!
					$input_text_s4 = SMO4_EH	//Holmes, you are ice cold!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			//audio
			GOSUB process_audio_s4

			// counter
			IF russian8remove_s4flag = 0
				IF IS_CHAR_DEAD russian8_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian8_s4
					REMOVE_BLIP bike1_s4blip
					russianseliminated_s3counter ++
					russian8remove_s4flag = 1
				ENDIF
			ENDIF
			IF russian14remove_s4flag = 0
				IF IS_CHAR_DEAD russian14_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian14_s4
					REMOVE_BLIP bike2_s4blip
					russianseliminated_s3counter ++
					russian14remove_s4flag = 1
				ENDIF
			ENDIF
			IF russian15remove_s4flag = 0
				IF IS_CHAR_DEAD russian15_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian15_s4
					REMOVE_BLIP bike3_s4blip
					russianseliminated_s3counter ++
					russian15remove_s4flag = 1
				ENDIF
			ENDIF
			IF russian16remove_s4flag = 0
				IF IS_CHAR_DEAD russian16_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian16_s4
					REMOVE_BLIP bike4_s4blip
					russianseliminated_s3counter ++
					russian16remove_s4flag = 1
				ENDIF
			ENDIF
			IF russian17remove_s4flag = 0
				IF IS_CHAR_DEAD russian17_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian17_s4
					REMOVE_BLIP bike5_s4blip
					russianseliminated_s3counter ++
					russian17remove_s4flag = 1
				ENDIF
			ENDIF
			IF russian18remove_s4flag = 0
				IF IS_CHAR_DEAD russian18_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian18_s4
					REMOVE_BLIP bike6_s4blip
					russianseliminated_s3counter ++
					russian18remove_s4flag = 1
				ENDIF
			ENDIF
			IF russian9remove_s4flag = 0
				IF IS_CHAR_DEAD russian9_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian9_s4
					REMOVE_BLIP bike7_s4blip
					russianseliminated_s3counter ++
					russian9remove_s4flag = 1
				ENDIF
			ENDIF


		ENDIF

	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////		Getting on Bike		////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF getonbikecut_s4flag = 1
	IF TIMERB > 2000
		getonbikecut_s4flag = 2
	ENDIF
ENDIF

IF getonbikecut_s4flag = 2
	
	IF smoke_s4flag = 12
		IF NOT IS_CHAR_DEAD big_smoke

			CLEAR_PRINTS
			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL PLAYER1 OFF
			SET_PLAYER_ENTER_CAR_BUTTON PLAYER1 FALSE ////////////////////////////////////////////////////////////////////////////
			CLEAR_AREA 1672.3206 -1697.2590 200.0 200.0 FALSE
			SET_FIXED_CAMERA_POSITION 1672.3206 -1697.2590 20.6216 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 1672.0695 -1696.5005 21.2228 JUMP_CUT
			CLEAR_ONSCREEN_COUNTER smokehealth_s4
			REMOVE_BLIP smoke_s4blip
			CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_CHAR_COORDINATES big_smoke 1668.756 -1689.291 20.869
			SET_CHAR_HEADING big_smoke 203.5

			SET_CHAR_COORDINATES scplayer 1667.26 -1683.5 20.869
			SET_CHAR_HEADING scplayer 203.5

			MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
			MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
			MARK_MODEL_AS_NO_LONGER_NEEDED MAFFB

			CLEAR_MISSION_AUDIO 2
			LOAD_MISSION_AUDIO 2 SOUND_SMO4_EO //Time to return the favour, CJ, get on!

			REQUEST_CAR_RECORDING 250
			REQUEST_CAR_RECORDING 251
			REQUEST_CAR_RECORDING 252
			REQUEST_MODEL BF400

			WAIT 100
			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 250
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 251
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 252
				OR NOT HAS_MODEL_LOADED BF400
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MISSION_AUDIO_LOADED 2
				WAIT 0
			ENDWHILE
			
			DELETE_CHAR hoochie_s4
			CLEAR_AREA 1668.67 -1694.68 19.97 200.0 FALSE
			CUSTOM_PLATE_FOR_NEXT_CAR BF400 &_IMY_AK
			CREATE_CAR BF400 1671.7914 -1695.1062 19.4800 smokebike_s4
			SET_CAR_HEADING smokebike_s4 117.5347
			SET_CAR_PROOFS smokebike_s4 FALSE TRUE TRUE TRUE TRUE
			SET_INTERPOLATION_PARAMETERS 0.0 3000
			SET_CAN_BURST_CAR_TYRES smokebike_s4 FALSE
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 99999
			SET_CAR_HEALTH smokebike_s4 2000
			SET_CAN_BURST_CAR_TYRES smokebike_s4 FALSE
			TIMERA = 0
			smoke_s4flag = 13
		ENDIF
	ENDIF

	IF smoke_s4flag = 13
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CAR_DEAD smokebike_s4
				SET_FIXED_CAMERA_POSITION 1672.3206 -1697.2590 20.6216 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1672.0695 -1696.5005 20.6212 INTERPOLATION
				TIMERA = 0
				smoke_s4flag = 14
			ENDIF
		ENDIF
	ENDIF

	IF smoke_s4flag = 14
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CAR_DEAD smokebike_s4
				IF TIMERA > 900
					TASK_ENTER_CAR_AS_DRIVER big_smoke smokebike_s4 5000
					TASK_ENTER_CAR_AS_PASSENGER scplayer smokebike_s4 5000 0
					CLEAR_MISSION_AUDIO 1
					PLAY_MISSION_AUDIO 2 
					PRINT_NOW SMO4_EO 5000 1 //Time to return the favour, CJ, get on!
					oneoff_s4flag = 2
					smoke_s4flag = 15
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF smoke_s4flag = 15
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CAR_DEAD smokebike_s4	
				IF IS_CHAR_SITTING_IN_CAR big_smoke smokebike_s4
					SET_FIXED_CAMERA_POSITION 1672.9832 -1692.0907 19.8891 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 1672.5699 -1692.9985 19.9589 JUMP_CUT
					POINT_CAMERA_AT_CAR smokebike_s4 FIXED JUMP_CUT
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					SET_CHAR_COORDINATES scplayer 1674.371 -1695.0973 19.814
					SET_CHAR_HEADING scplayer 90.0
					TASK_ENTER_CAR_AS_PASSENGER scplayer smokebike_s4 5000 0
					TASK_LOOK_AT_CHAR big_smoke scplayer 2000
					SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE scplayer KNOCKOFFBIKE_NEVER
					SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE big_smoke KNOCKOFFBIKE_NEVER

					smoke_s4flag = 16
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF smoke_s4flag = 16
		IF NOT IS_CHAR_DEAD big_smoke 
			IF NOT IS_CAR_DEAD smokebike_s4
				IF IS_CHAR_SITTING_IN_CAR scplayer smokebike_s4
					SET_RADIO_CHANNEL RS_MODERN_ROCK
					CLEAR_LOOK_AT big_smoke
					START_PLAYBACK_RECORDED_CAR smokebike_s4 250
					TIMERA = 0
					smoke_s4flag = 17
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF smoke_s4flag = 17
		IF NOT IS_CHAR_DEAD big_smoke 
			IF NOT IS_CAR_DEAD smokebike_s4
				IF IS_CHAR_SITTING_IN_CAR scplayer smokebike_s4
					IF TIMERA > 1600
						SET_FIXED_CAMERA_POSITION 1623.3313 -1705.4485 19.7991 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 1624.3309 -1705.4746 19.7991 JUMP_CUT
						STOP_PLAYBACK_RECORDED_CAR smokebike_s4
						WAIT 0
						IF NOT IS_CAR_DEAD smokebike_s4
							START_PLAYBACK_RECORDED_CAR smokebike_s4 251
						ENDIF
						TIMERA = 0
						getonbikecut_s4flag = 3
						smoke_s4flag = 18
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF oneoff_s4flag = 2
		IF HAS_MISSION_AUDIO_FINISHED 2	
			CLEAR_THIS_PRINT SMO4_EO
			oneoff_s4flag = 3
		ENDIF
	ENDIF

ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////	  CITY CHASE SECTION CITY	////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
IF firstchase_s4flag = 0
OR firstchase_s4flag = 1
OR firstchase_s4flag = 2
OR firstchase_s4flag = 3
	IF NOT IS_CAR_DEAD smokebike_s4
		IF NOT finalchase_s4flag > 3
			GET_CAR_HEALTH smokebike_s4 bikehealth_s4
			bikehealth_s4 = bikehealth_s4 - 1000 //1000
			
			IF bikehealth_s4 <= 0
				bikehealth_s4 = 0
				SET_CAR_PROOFS smokebike_s4 FALSE FALSE FALSE FALSE FALSE
				SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
				IF NOT IS_CHAR_DEAD big_smoke
					SET_CHAR_PROOFS big_smoke FALSE FALSE FALSE FALSE FALSE
					TASK_DIE big_smoke
				ENDIF
				EXPLODE_CAR smokebike_s4
				PRINT_NOW DGS1_60 7000 1 //~r~Smoke died!
				GOTO mission_drugs1_failed
			ELSE
				bikehealth_s4 = bikehealth_s4 / 10
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF firstchase_s4flag = 0

	IF smoke_s4flag = 18
		IF TIMERA > 700

		    z = 19.7991

			REQUEST_CAR_RECORDING 253 //coach
			REQUEST_CAR_RECORDING 254 //reverse car car1
			REQUEST_CAR_RECORDING 255 //at the beginning crashes out car2
			REQUEST_CAR_RECORDING 256 //first enemy bike
			REQUEST_CAR_RECORDING 257 //second enemy bike

			REQUEST_MODEL SULTAN
			REQUEST_MODEL KMB_SKIP
			REQUEST_MODEL NEBULA
			REQUEST_MODEL COACH
			REQUEST_MODEL PACKER

			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_SMO4_FA //Hey man, we got bikes all on us!


			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 253
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 254
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 255
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 256
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 257
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_MODEL_LOADED SULTAN
				OR NOT HAS_MODEL_LOADED KMB_SKIP
				OR NOT HAS_MODEL_LOADED NEBULA
				OR NOT HAS_MODEL_LOADED COACH
				OR NOT HAS_MODEL_LOADED PACKER
				OR NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE

			CREATE_OBJECT KMB_SKIP 1563.6030 -1437.1074 12.3470 skip_s4
			SET_OBJECT_HEADING skip_s4 90.4568
			FREEZE_OBJECT_POSITION skip_s4 TRUE

			CREATE_CAR COACH 1552.2089 -1437.2998 12.3828 coach1_s4
			SET_CAR_HEADING coach1_s4 90.2081
			FREEZE_CAR_POSITION coach1_s4 TRUE

			CREATE_CAR COACH 1539.7588 -1437.3400 12.3906 coach2_s4
			SET_CAR_HEADING coach2_s4 90.2081
			FREEZE_CAR_POSITION coach2_s4 TRUE

			CREATE_CAR NEBULA 1680.473 -1589.874 13.23 car1_s4
			SET_CAR_HEADING car1_s4 87.883
			CHANGE_CAR_COLOUR car1_s4 CARCOLOUR_CHERRYRED CARCOLOUR_CHERRYRED
			CREATE_CHAR_INSIDE_CAR car1_s4 PEDTYPE_MISSION1 MALE01 driver1_s4
			SET_CHAR_DECISION_MAKER driver1_s4 smoke4_DM

			CREATE_CAR NEBULA 1594.553 -1595.0389 13.243 car2_s4
			SET_CAR_HEADING car2_s4 271.189
			CHANGE_CAR_COLOUR car2_s4 CARCOLOUR_LIGHTBLUEGREY CARCOLOUR_LIGHTBLUEGREY
			CREATE_CHAR_INSIDE_CAR car2_s4 PEDTYPE_MISSION1 MALE01 driver2_s4
			SET_CHAR_DECISION_MAKER driver2_s4 smoke4_DM

			CREATE_CAR NEBULA 1655.527 -1550.667 13.226 car3_s4
			SET_CAR_HEADING car3_s4 180.735
			CHANGE_CAR_COLOUR car3_s4 51 51
			CREATE_CHAR_INSIDE_CAR car3_s4 PEDTYPE_MISSION1 MALE01 driver3_s4
			SET_CHAR_DECISION_MAKER driver3_s4 smoke4_DM

			CREATE_CAR NEBULA 1655.391 -1463.511 13.22 car4_s4
			SET_CAR_HEADING car4_s4 180.735
			CHANGE_CAR_COLOUR car4_s4 CARCOLOUR_MIDNIGHTBLUE CARCOLOUR_MIDNIGHTBLUE
			SET_CAR_HEAVY car4_s4 TRUE
			CREATE_CHAR_INSIDE_CAR car4_s4 PEDTYPE_MISSION1 MALE01 driver4_s4
			SET_CHAR_DECISION_MAKER driver4_s4 smoke4_DM

			CREATE_CAR NEBULA 1660.788 -1563.222 13.23 car5_s4	//-1553
			SET_CAR_HEADING car5_s4 358.832 
			CHANGE_CAR_COLOUR car5_s4 73 73
			CREATE_CHAR_INSIDE_CAR car5_s4 PEDTYPE_MISSION1 MALE01 driver5_s4
			SET_CHAR_DECISION_MAKER driver5_s4 smoke4_DM

			CREATE_CAR NEBULA 1660.594 -1522.353 13.225 car6_s4
			SET_CAR_HEADING car6_s4 358.832 
			CHANGE_CAR_COLOUR car6_s4 99 99
			CREATE_CHAR_INSIDE_CAR car6_s4 PEDTYPE_MISSION1 MALE01 driver6_s4
			SET_CHAR_DECISION_MAKER driver6_s4 smoke4_DM

			CREATE_CAR NEBULA 1693.236 -1589.973 13.224 car7_s4
			SET_CAR_HEADING car7_s4 91.298
			CHANGE_CAR_COLOUR car7_s4 CARCOLOUR_POLICECARBLUE CARCOLOUR_POLICECARBLUE
			SET_CAR_HEAVY car7_s4 TRUE
			CREATE_CHAR_INSIDE_CAR car7_s4 PEDTYPE_MISSION1 MALE01 driver7_s4
			SET_CHAR_DECISION_MAKER driver7_s4 smoke4_DM

			CREATE_CAR PACKER 1590.891 -1590.689 13.09 packer_s4
			SET_PETROL_TANK_WEAKPOINT packer_s4 FALSE
			SET_CAR_HEADING packer_s4 273.516
			SET_CAR_HEALTH packer_s4 3000
			SET_CAR_ONLY_DAMAGED_BY_PLAYER packer_s4 TRUE
			CREATE_CHAR_INSIDE_CAR packer_s4 PEDTYPE_MISSION1 MAFFA russian1_s4
			SET_CHAR_DECISION_MAKER russian1_s4 smoke4_DM
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE russian1_s4 FALSE
			ADD_BLIP_FOR_CAR packer_s4 packer_s4blip
			CHANGE_BLIP_DISPLAY packer_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE packer_s4blip 2
			SET_CAR_PROOFS packer_s4 FALSE TRUE TRUE TRUE TRUE
			packerstatus_s4flag = 1

			TIMERA = 0
	        WHILE z > 13.8442 //12.6442
				WAIT 0
		        z -=@ 0.02
	            SET_FIXED_CAMERA_POSITION 1623.3313 -1705.4485 z 0.0 0.0 0.0
	            POINT_CAMERA_AT_POINT 1624.3309 -1705.4746 z JUMP_CUT
				
				IF startrecording_s4flag = 0
					IF TIMERA > 1000 //1500
						IF NOT IS_CAR_DEAD smokebike_s4
							IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR smokebike_s4

								CREATE_CAR BF400 1665.25 -1716.98 12.75 bike1_s4
								SET_PETROL_TANK_WEAKPOINT bike1_s4 FALSE
								SET_CAR_HEADING bike1_s4 84.0
								SET_CAR_HEALTH bike1_s4 800
								CREATE_CHAR_INSIDE_CAR bike1_s4 PEDTYPE_MISSION1 MAFFA russian2_s4
								SET_CHAR_DECISION_MAKER russian2_s4 smoke4_DM
								SET_CHAR_HEALTH russian2_s4 10
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian2_s4 TRUE
								CREATE_CHAR_AS_PASSENGER bike1_s4 PEDTYPE_MISSION1 MAFFA 0 russian3_s4
								GIVE_WEAPON_TO_CHAR russian3_s4 WEAPONTYPE_MICRO_UZI 99999
								SET_CHAR_DECISION_MAKER russian3_s4 smoke4_DM
								SET_CHAR_HEALTH russian3_s4 10
								SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian3_s4 TRUE
								ADD_BLIP_FOR_CAR bike1_s4 bike1_s4blip
								CHANGE_BLIP_DISPLAY bike1_s4blip BLIP_ONLY
								CHANGE_BLIP_SCALE bike1_s4blip 2
								bike1swap_s4flag = 1
								bike1_s4flag = 1

								WAIT 0
								
								IF NOT IS_CAR_DEAD smokebike_s4
									START_PLAYBACK_RECORDED_CAR smokebike_s4 252 //SET_INTERPOLATION_PARAMETERS 0.0 3000
									SET_PLAYBACK_SPEED smokebike_s4 0.9
								ENDIF
								IF NOT IS_CAR_DEAD bike1_s4
									START_PLAYBACK_RECORDED_CAR bike1_s4 256 //SET_INTERPOLATION_PARAMETERS 0.0 3000
									SET_PLAYBACK_SPEED bike1_s4 0.9
								ENDIF

								startrecording_s4flag = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF

	        ENDWHILE

			smoke_s4flag = 19
		ENDIF
	ENDIF

	IF smoke_s4flag = 19
		IF TIMERA > 8500
			IF NOT IS_CAR_DEAD smokebike_s4
				IF NOT IS_CHAR_DEAD big_smoke
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 99999
					TASK_DRIVE_BY scplayer -1 -1 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 95.5 200.0
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL PLAYER1 ON
					//SET_PLAYER_FAST_RELOAD PLAYER1 TRUE
					DISPLAY_ONSCREEN_COUNTER_WITH_STRING bikehealth_s4 COUNTER_DISPLAY_BAR DGS1_59
					difficulty_s4flag++
					IF difficulty_s4flag > 1
						difficulty_s4value = 30
					ELSE
						difficulty_s4value = 92
					ENDIF

					IF NOT IS_CHAR_DEAD russian2_s4
						GIVE_WEAPON_TO_CHAR russian2_s4 WEAPONTYPE_MICRO_UZI 99999
						TASK_DRIVE_BY russian2_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
					ENDIF
					IF NOT IS_CHAR_DEAD russian3_s4
						TASK_DRIVE_BY russian3_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					ENDIF
					SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
					SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE
					
					PRINT_HELP DGS1_64  // To look around on the bike use the camera controls.
					
					progressaudio_s4flag = 0
					handlingudio_s4flag = 0
					TIMERB = 0
					text_s4flag = 200
					smoke_s4flag = 20
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF text_s4flag = 200
		IF IS_HELP_MESSAGE_BEING_DISPLAYED
			text_s4flag = 201
		ENDIF
	ENDIF
	//propose to get rid of this
	IF text_s4flag = 201
		IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			PRINT_HELP DGS1_65
			text_s4flag = 202
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD smokebike_s4

			IF ambientcar_s4flag = 0
				IF LOCATE_CAR_2D smokebike_s4 1620.59 -1612.14 7.0 7.0 FALSE

					IF NOT IS_CAR_DEAD car1_s4
						CAR_GOTO_COORDINATES car1_s4 1571.424 -1590.02 13.23
						SET_CAR_CRUISE_SPEED car1_s4 13.5
						SET_CAR_FORWARD_SPEED car1_s4 13.5
						SET_CAR_DRIVING_STYLE car1_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					IF NOT IS_CAR_DEAD car2_s4
						CAR_GOTO_COORDINATES car2_s4 1681.473 -1595.0593 13.22
						SET_CAR_CRUISE_SPEED car2_s4 13.5
						SET_CAR_FORWARD_SPEED car2_s4 4.5
						SET_CAR_DRIVING_STYLE car2_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					IF NOT IS_CAR_DEAD car7_s4
						CAR_GOTO_COORDINATES car7_s4 1582.411 -1590.115 13.228
						SET_CAR_CRUISE_SPEED car7_s4 9.0
						SET_CAR_DRIVING_STYLE car7_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					IF NOT IS_CAR_DEAD packer_s4
						START_PLAYBACK_RECORDED_CAR packer_s4 255
						SET_PLAYBACK_SPEED packer_s4 0.9
					ENDIF

					ambientcar_s4flag = 1
				ENDIF
			ENDIF

			IF ambientcar_s4flag = 1
				IF LOCATE_CAR_2D smokebike_s4 1654.1 -1589.48 10.0 10.0 FALSE

					IF NOT IS_CAR_DEAD car3_s4
						CAR_GOTO_COORDINATES car3_s4 1655.839 -1576.842 13.226
						SET_CAR_CRUISE_SPEED car3_s4 9.0
						SET_CAR_DRIVING_STYLE car3_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					IF NOT IS_CAR_DEAD car4_s4
						CAR_GOTO_COORDINATES car4_s4 1655.593 -1559.511 13.227
						SET_CAR_CRUISE_SPEED car4_s4 9.0
						SET_CAR_FORWARD_SPEED car4_s4 9.0
						SET_CAR_DRIVING_STYLE car4_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					IF NOT IS_CAR_DEAD car5_s4
						CAR_GOTO_COORDINATES car5_s4 1702.0 -1444.0 13.0
						SET_CAR_CRUISE_SPEED car5_s4 9.0
						SET_CAR_FORWARD_SPEED car5_s4 10.8
						SET_CAR_DRIVING_STYLE car5_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					IF NOT IS_CAR_DEAD car6_s4
						CAR_GOTO_COORDINATES car6_s4 1702.0 -1444.0 13.0
						SET_CAR_CRUISE_SPEED car6_s4 9.0
						SET_CAR_FORWARD_SPEED car6_s4 9.0
						SET_CAR_DRIVING_STYLE car6_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF
		 
					ambientcar_s4flag = 2
				ENDIF
			ENDIF

			IF ambientcar_s4flag = 2
				IF LOCATE_CAR_2D smokebike_s4 1657.56 -1537.29 10.0 10.0 FALSE

					CREATE_CAR BF400 1702.127 -1496.87 12.95 bike2_s4
					SET_PETROL_TANK_WEAKPOINT bike2_s4 FALSE
					SET_CAR_HEADING bike2_s4 26.5
					SET_CAR_HEALTH bike2_s4 800
					CREATE_CHAR_INSIDE_CAR bike2_s4 PEDTYPE_MISSION1 MAFFA russian8_s4
					SET_CHAR_DECISION_MAKER russian8_s4 smoke4_DM
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian8_s4 TRUE
					SET_CHAR_HEALTH russian8_s4 10
					CREATE_CHAR_AS_PASSENGER bike2_s4 PEDTYPE_MISSION1 MAFFA 0 russian9_s4
					GIVE_WEAPON_TO_CHAR russian9_s4 WEAPONTYPE_MICRO_UZI 99999
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian9_s4 TRUE
					SET_CHAR_DECISION_MAKER russian9_s4 smoke4_DM
					SET_CHAR_HEALTH russian9_s4 10
					ADD_BLIP_FOR_CAR bike2_s4 bike2_s4blip
					CHANGE_BLIP_DISPLAY bike2_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE bike2_s4blip 2
					bike2swap_s4flag = 1
					bike2_s4flag = 1
			
					CREATE_CAR NEBULA 1622.005 -1443.498 13.229 car8_s4
					SET_CAR_HEADING car8_s4 266.928
					CHANGE_CAR_COLOUR car8_s4 115 115
					SET_CAR_HEAVY car8_s4 TRUE
					CREATE_CHAR_INSIDE_CAR car8_s4 PEDTYPE_MISSION1 MALE01 driver8_s4
					SET_CHAR_DECISION_MAKER driver8_s4 smoke4_DM

					CREATE_CAR NEBULA 1536.288 -1443.049 13.224 car9_s4
					SET_CAR_HEADING car9_s4 269.939
					CHANGE_CAR_COLOUR car9_s4 122 122
					SET_CAR_HEAVY car9_s4 TRUE
					CREATE_CHAR_INSIDE_CAR car9_s4 PEDTYPE_MISSION1 MALE01 driver9_s4
					SET_CHAR_DECISION_MAKER driver9_s4 smoke4_DM

					ambientcar_s4flag = 3
				ENDIF
			ENDIF

			IF ambientcar_s4flag = 3
				IF LOCATE_CAR_2D smokebike_s4 1657.94 -1468.93 15.0 15.0 FALSE

					IF NOT IS_CAR_DEAD bike2_s4
						START_PLAYBACK_RECORDED_CAR bike2_s4 257
					ENDIF
					IF NOT IS_CHAR_DEAD russian8_s4
						IF difficulty_s4flag < 3
							GIVE_WEAPON_TO_CHAR russian8_s4 WEAPONTYPE_MICRO_UZI 99999
							TASK_DRIVE_BY russian8_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
						ENDIF
					ENDIF

					IF NOT IS_CHAR_DEAD russian9_s4
						TASK_DRIVE_BY russian9_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					ENDIF

					DELETE_CAR car1_s4
					DELETE_CAR car2_s4
					DELETE_CAR car7_s4
					
					IF NOT IS_CAR_DEAD car8_s4
						CAR_GOTO_COORDINATES car8_s4 1720.037 -1444.137 13.222
						SET_CAR_CRUISE_SPEED car8_s4 9.0
						SET_CAR_DRIVING_STYLE car8_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					IF NOT IS_CAR_DEAD car9_s4
						CAR_GOTO_COORDINATES car9_s4 1654.817 -1492.993 13.22
						SET_CAR_CRUISE_SPEED car9_s4 13.5
						SET_CAR_FORWARD_SPEED car9_s4 20.0
						SET_CAR_DRIVING_STYLE car9_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					ENDIF

					ambientcar_s4flag = 4
				ENDIF
			ENDIF

			IF ambientcar_s4flag = 4
				IF LOCATE_CAR_2D smokebike_s4 1631.77 -1441.31 12.0 12.0 FALSE
					ambientcar_s4flag = 5
				ENDIF
			ENDIF
			
			IF ambientcar_s4flag = 5
				IF LOCATE_CAR_2D smokebike_s4 1545.68 -1437.35 10.0 10.0 FALSE

					CREATE_CAR NEBULA 1394.751 -1427.224 13.24 car13_s4
					SET_CAR_HEADING car13_s4 184.525
					CHANGE_CAR_COLOUR car13_s4 99 99
					CAR_GOTO_COORDINATES car13_s4 1441.028 -1443.271 13.234
					SET_CAR_CRUISE_SPEED car13_s4 13.5
					SET_CAR_DRIVING_STYLE car13_s4 DRIVINGMODE_STOPFORCARS_IGNORELIGHTS
					CREATE_CHAR_INSIDE_CAR car13_s4 PEDTYPE_MISSION1 MALE01 driver5_s4
					SET_CHAR_DECISION_MAKER driver5_s4 smoke4_DM

					ambientcar_s4flag = 6
				ENDIF
			ENDIF

			IF ambientcar_s4flag = 6
				IF LOCATE_CAR_2D smokebike_s4 1508.7 -1440.86 10.0 10.0 FALSE
					DELETE_CAR car8_s4
					DELETE_CAR car3_s4
					DELETE_CAR car4_s4

					CREATE_CAR COACH 1457.689 -1371.728 13.235 coach3_s4 
					SET_CAR_HEADING coach3_s4 177.712
					START_PLAYBACK_RECORDED_CAR coach3_s4 253
					SET_PLAYBACK_SPEED coach3_s4 0.9

					CREATE_CAR SULTAN 1374.507 -1394.312 13.272 rcar1_s4
					SET_CAR_HEALTH rcar1_s4 400
					SET_CAR_HEADING rcar1_s4 193.922
					CREATE_CHAR_AS_PASSENGER rcar1_s4 PEDTYPE_MISSION1 MAFFA 0 russian11_s4
					GIVE_WEAPON_TO_CHAR russian11_s4 WEAPONTYPE_MICRO_UZI 99999
					SET_CHAR_DECISION_MAKER russian11_s4 smoke4_DM
					TASK_DRIVE_BY russian11_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 70
					ADD_BLIP_FOR_CAR rcar1_s4 rcar1_s4blip
					CHANGE_BLIP_DISPLAY rcar1_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE rcar1_s4blip 2

					CREATE_CAR SULTAN 1374.577 -1401.141 13.102 rcar2_s4
					SET_CAR_HEADING rcar2_s4 349.825
					CHANGE_CAR_COLOUR rcar2_s4 0 0
					SET_CAR_HEALTH rcar2_s4 500

					CREATE_CAR SULTAN 1374.362 -1407.883 13.239 rcar3_s4
					SET_CAR_HEADING rcar3_s4 194.852
					SET_CAR_HEALTH rcar3_s4 500
//					CREATE_CHAR_AS_PASSENGER rcar3_s4 PEDTYPE_MISSION1 MAFFA 0 russian12_s4
//					GIVE_WEAPON_TO_CHAR russian12_s4 WEAPONTYPE_MICRO_UZI 99999
//					SET_CHAR_DECISION_MAKER russian12_s4 smoke4_DM
//					TASK_DRIVE_BY russian12_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 80
					ADD_BLIP_FOR_CAR rcar3_s4 rcar3_s4blip
					CHANGE_BLIP_DISPLAY rcar3_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE rcar3_s4blip 2

					//new added car
					CREATE_CAR SULTAN 1401.0576 -1430.8915 12.3905 rcar4_s4
					SET_CAR_HEADING rcar4_s4 10.385
					SET_CAR_HEALTH rcar4_s4 500
					ADD_BLIP_FOR_CAR rcar4_s4 rcar4_s4blip
					CHANGE_BLIP_DISPLAY rcar4_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE rcar4_s4blip 2
					OPEN_CAR_DOOR rcar4_s4 FRONT_LEFT_DOOR
					OPEN_CAR_DOOR rcar4_s4 FRONT_RIGHT_DOOR

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1399.4642 -1431.6041 12.3905 russian12_s4
					SET_CHAR_DECISION_MAKER russian12_s4 smoke4_DM
					GIVE_WEAPON_TO_CHAR russian12_s4 WEAPONTYPE_MICRO_UZI 99999
					SET_CHAR_HEALTH russian12_s4 100
					enemy_s4 = russian12_s4
					enemytarget_s4 = scplayer
					GOSUB stayshootnoduck_s4label
					ADD_BLIP_FOR_CHAR russian12_s4 russian12_s4blip
					CHANGE_BLIP_DISPLAY russian12_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE russian12_s4blip 2

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1401.4662 -1427.3888 12.3905 russian20_s4
					SET_CHAR_DECISION_MAKER russian20_s4 smoke4_DM
					GIVE_WEAPON_TO_CHAR russian20_s4 WEAPONTYPE_MICRO_UZI 99999
					SET_CHAR_HEALTH russian20_s4 100
					enemy_s4 = russian20_s4
					enemytarget_s4 = scplayer
					GOSUB stayshootnoduck_s4label
					ADD_BLIP_FOR_CHAR russian20_s4 russian20_s4blip
					CHANGE_BLIP_DISPLAY russian20_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE russian20_s4blip 2
					//

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1374.992 -1397.639 12.438 russian13_s4
					SET_CHAR_DECISION_MAKER russian13_s4 smoke4_DM
					GIVE_WEAPON_TO_CHAR russian13_s4 WEAPONTYPE_MICRO_UZI 99999
					SET_CHAR_HEALTH russian13_s4 100
					enemy_s4 = russian13_s4
					enemytarget_s4 = scplayer
					GOSUB stayshoot_s4label
					ADD_BLIP_FOR_CHAR russian13_s4 russian13_s4blip
					CHANGE_BLIP_DISPLAY russian13_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE russian13_s4blip 2

					CREATE_CHAR PEDTYPE_MISSION1 MAFFA 1374.543 -1404.678 12.425 russian14_s4
					SET_CHAR_DECISION_MAKER russian14_s4 smoke4_DM
					GIVE_WEAPON_TO_CHAR russian14_s4 WEAPONTYPE_MICRO_UZI 99999
					SET_CHAR_HEALTH russian14_s4 100
					enemy_s4 = russian14_s4
					enemytarget_s4 = scplayer
					GOSUB stayshoot_s4label
					ADD_BLIP_FOR_CHAR russian14_s4 russian14_s4blip
					CHANGE_BLIP_DISPLAY russian14_s4blip BLIP_ONLY
					CHANGE_BLIP_SCALE russian14_s4blip 2

					ambientcar_s4flag = 7
				ENDIF
			ENDIF

			IF ambientcar_s4flag = 7
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR smokebike_s4
					IF LOCATE_CAR_2D smokebike_s4 1395.844 -1408.105 5.0 5.0 FALSE
						FREEZE_CAR_POSITION smokebike_s4 TRUE
						REMOVE_BLIP packer_s4blip
						STOP_PLAYBACK_RECORDED_CAR bike1_s4
						REMOVE_BLIP bike1_s4blip
						STOP_PLAYBACK_RECORDED_CAR bike2_s4
						REMOVE_BLIP bike2_s4blip
						MARK_CHAR_AS_NO_LONGER_NEEDED driver1_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver2_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver3_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver4_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver5_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver6_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver7_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver8_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED driver9_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car1_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car2_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car3_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car4_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car7_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car8_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car13_s4
						MARK_CAR_AS_NO_LONGER_NEEDED coach3_s4
						DELETE_CAR car5_s4
						DELETE_CAR car6_s4
						DELETE_CAR car9_s4
						MARK_MODEL_AS_NO_LONGER_NEEDED COACH
						MARK_MODEL_AS_NO_LONGER_NEEDED KMB_SKIP
						MARK_MODEL_AS_NO_LONGER_NEEDED NEBULA
						DELETE_OBJECT skip_s4
						DELETE_CAR coach1_s4 
						DELETE_CAR coach2_s4
						MARK_CAR_AS_NO_LONGER_NEEDED coach3_s4
						DELETE_CAR bike2_s4
						DELETE_CHAR russian8_s4
						DELETE_CHAR russian9_s4
						DELETE_CAR bike1_s4
						DELETE_CAR packer_s4
						DELETE_CHAR russian1_s4
						DELETE_CHAR russian2_s4
						DELETE_CHAR russian3_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car13_s4
						TIMERA = 0
						ambientcar_s4flag = 8
					ENDIF
				ENDIF
			ENDIF

			IF ambientcar_s4flag > 7
				IF notonscreen_s4flag = 0
					IF NOT IS_POINT_ON_SCREEN 1400.41 -1447.12 12.3 10.0
						CLEAR_AREA 1400.41 -1447.12 12.3 50.0 FALSE
						notonscreen_s4flag = 1
						ambientcar_s4flag < 9
					ENDIF
				ENDIF
			ENDIF

			IF ambientcar_s4flag = 8
				REQUEST_CAR_RECORDING 258 //smokebike
				REQUEST_CAR_RECORDING 259 //bike1
				REQUEST_CAR_RECORDING 260 //bike2
				REQUEST_CAR_RECORDING 261 //bike3
				REQUEST_CAR_RECORDING 262 //car1
				REQUEST_CAR_RECORDING 263 //car2
				REQUEST_CAR_RECORDING 264 //bike4
				REQUEST_CAR_RECORDING 265 //packer in cut
				REQUEST_CAR_RECORDING 266 //bike1 in cut
				REQUEST_CAR_RECORDING 267 //car1 in cut
				REQUEST_CAR_RECORDING 268 //second bike
				ambientcar_s4flag = 9
			ENDIF

			IF ambientcar_s4flag = 9
				IF TIMERA > 10000
					IF HAS_CAR_RECORDING_BEEN_LOADED 258
						IF HAS_CAR_RECORDING_BEEN_LOADED 259
							IF HAS_CAR_RECORDING_BEEN_LOADED 260
								IF HAS_CAR_RECORDING_BEEN_LOADED 261
									IF HAS_CAR_RECORDING_BEEN_LOADED 262
										IF HAS_CAR_RECORDING_BEEN_LOADED 263
											IF HAS_CAR_RECORDING_BEEN_LOADED 264
												IF HAS_CAR_RECORDING_BEEN_LOADED 265
													IF HAS_CAR_RECORDING_BEEN_LOADED 266
														IF HAS_CAR_RECORDING_BEEN_LOADED 267
															IF HAS_CAR_RECORDING_BEEN_LOADED 268
																firstchase_s4flag = 1
																ambientcar_s4flag = 10
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
				ENDIF
			ENDIF


			//spin/stop bikes if the both guys are not in car
			IF packerstatus_s4flag = 1
				IF IS_CAR_DEAD packer_s4
					STOP_PLAYBACK_RECORDED_CAR packer_s4 
					packerstatus_s4flag = 2
				ENDIF
			ENDIF


			IF bike1swap_s4flag = 1
				IF NOT IS_CAR_DEAD bike1_s4
					IF NOT IS_CHAR_DEAD russian2_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian3_s4
							IF IS_CHAR_IN_CAR russian3_s4 bike1_s4
								GET_DRIVER_OF_CAR bike1_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD russian3_s4 player_x player_y -10.0
									WARP_CHAR_INTO_CAR russian3_s4 bike1_s4
									TASK_DRIVE_BY russian3_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
									bike1swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike1_s4 18.0
							STOP_PLAYBACK_RECORDED_CAR bike1_s4
							SET_CAR_ROTATION_VELOCITY bike1_s4 3.5 7.3999 8.1003
							bike1swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike1swap_s4flag = 2
				IF IS_CHAR_DEAD russian3_s4
					STOP_PLAYBACK_RECORDED_CAR bike1_s4
					IF NOT IS_CAR_DEAD bike1_s4
						SET_CAR_FORWARD_SPEED bike1_s4 18.0
						SET_CAR_ROTATION_VELOCITY bike1_s4 3.5 7.3999 8.1003
					ENDIF
					bike1swap_s4flag = 3
				ENDIF 
			ENDIF
			IF bike1_s4flag = 1
				IF IS_CHAR_DEAD russian2_s4
					IF IS_CHAR_DEAD russian3_s4
					OR IS_CAR_DEAD bike1_s4
						STOP_PLAYBACK_RECORDED_CAR bike1_s4
						REMOVE_BLIP bike1_s4blip
						MARK_CAR_AS_NO_LONGER_NEEDED bike1_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian2_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian3_s4
						bike1_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

			IF bike2swap_s4flag = 1
				IF NOT IS_CAR_DEAD bike2_s4
					IF NOT IS_CHAR_DEAD russian8_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian9_s4
							IF IS_CHAR_IN_CAR russian9_s4 bike2_s4
								GET_DRIVER_OF_CAR bike2_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD russian9_s4 player_x player_y -10.0
									WARP_CHAR_INTO_CAR russian9_s4 bike2_s4
									TASK_DRIVE_BY russian9_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
									bike2swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike2_s4 15.0
							STOP_PLAYBACK_RECORDED_CAR bike2_s4
							SET_CAR_ROTATION_VELOCITY bike2_s4 3.5 7.3999 8.1003
							bike2swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike2swap_s4flag = 2
				IF IS_CHAR_DEAD russian9_s4
					STOP_PLAYBACK_RECORDED_CAR bike2_s4
					IF NOT IS_CAR_DEAD bike2_s4
						SET_CAR_FORWARD_SPEED bike2_s4 15.0
						SET_CAR_ROTATION_VELOCITY bike2_s4 3.5 7.3999 8.1003
					ENDIF
					bike2swap_s4flag = 3
				ENDIF 
			ENDIF
			IF bike2_s4flag = 1
				IF IS_CHAR_DEAD russian8_s4
					IF IS_CHAR_DEAD russian9_s4
					OR IS_CAR_DEAD bike2_s4
						STOP_PLAYBACK_RECORDED_CAR bike2_s4
						REMOVE_BLIP bike2_s4blip
						MARK_CAR_AS_NO_LONGER_NEEDED bike2_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian8_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian9_s4
						bike2_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

			IF IS_CHAR_DEAD russian13_s4
				IF DOES_BLIP_EXIST russian13_s4blip
					REMOVE_BLIP russian13_s4blip
				ENDIF
			ENDIF

			IF IS_CHAR_DEAD russian14_s4
				IF DOES_BLIP_EXIST russian14_s4blip
					REMOVE_BLIP russian14_s4blip
				ENDIF
			ENDIF

			IF IS_CAR_DEAD rcar1_s4
				IF DOES_BLIP_EXIST rcar1_s4blip
					REMOVE_BLIP rcar1_s4blip
				ENDIF
			ENDIF

			IF IS_CAR_DEAD rcar3_s4
				IF DOES_BLIP_EXIST rcar3_s4blip
					REMOVE_BLIP rcar3_s4blip
				ENDIF
			ENDIF

			IF explodecar5_s4flag = 0
				IF NOT IS_CAR_DEAD car5_s4
					IF NOT IS_CAR_DEAD packer_s4
						IF HAS_CAR_BEEN_DAMAGED_BY_CAR car5_s4 packer_s4
							EXPLODE_CAR car5_s4
							explodecar5_s4flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF explodecar6_s4flag = 0
				IF NOT IS_CAR_DEAD car6_s4
					IF NOT IS_CAR_DEAD packer_s4
						IF HAS_CAR_BEEN_DAMAGED_BY_CAR car6_s4 packer_s4
							EXPLODE_CAR car6_s4
							explodecar6_s4flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF


	ENDIF

ENDIF


IF smoke_s4flag = 20
OR firstchase_s4flag = 1
		IF storm1_s4flag < 3

			//audio
			IF progressaudio_s4flag = 0
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_FB	//They after us on a bike, Smoke!
					$input_text_s4 = SMO4_FB	//They after us on a bike, Smoke!!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 1
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_ES	//Waste any motherfucker that follows us!
					$input_text_s4 = SMO4_ES	//Waste any motherfucker that follows us!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF truckaudio_s4flag = 0
				IF LOCATE_CAR_2D smokebike_s4 1659.49 -1580.97 50.0 50.0 FALSE //32.0
					truckaudio_s4flag = 1
				ENDIF
			ENDIF

			//truck then traffic
			IF progressaudio_s4flag = 2
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 1
						audio_label_s4 = SOUND_SMO4_FD	//Shit, man, they coming fo’ us in a truck!
						$input_text_s4 = SMO4_FD	//Shit, man, they coming fo’ us in a truck!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 3
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_FE	//Don’t tell me about it, toast that fucker!
					$input_text_s4 = SMO4_FE		//Don’t tell me about it, toast that fucker!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 4
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_FF	//Look at all this traffic
					$input_text_s4 = SMO4_FF	//Look at all this traffic 
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 5
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_FG	//I’m coming through!
					$input_text_s4 = SMO4_FG	//I’m coming through!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

//			IF progressaudio_s4flag = 6
//				IF handlingudio_s4flag = 0
//					audio_label_s4 = SOUND_SMO4_FH	//Don’t just sound your horn, get out the damn way!
//					$input_text_s4 = SMO4_FH	//Don’t just sound your horn, get out the damn way!
//					GOSUB load_audio_s4
//				ENDIF
//			ENDIF

			//second bike
			IF truckaudio_s4flag = 1
				IF LOCATE_CAR_2D smokebike_s4 1638.05 -1440.64 60.0 60.0 FALSE //25.0
					truckaudio_s4flag = 2
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 6
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 2
						audio_label_s4 = SOUND_SMO4_FC	//We got bikes on our six—smash it Smoke!
						$input_text_s4 = SMO4_FC	//We got bikes on our six—smash it Smoke!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF

			//jumping the coach
			IF truckaudio_s4flag = 2
				IF LOCATE_CAR_2D smokebike_s4 1579.33 -1438.05 55.0 55.0 FALSE //35.0
					truckaudio_s4flag = 3
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 7
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 3
						audio_label_s4 = SOUND_SMO4_FJ	//Hold on tight, homie!
						$input_text_s4 = SMO4_FJ	//Hold on tight, homie!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 8
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_FK	//Ah Smoke,! Nooooooo!
					$input_text_s4 = SMO4_FK	//Ah Smoke,! Nooooooo!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			//hit the coach
			IF truckaudio_s4flag = 3
				IF NOT IS_CAR_DEAD packer_s4
					IF LOCATE_CAR_2D smokebike_s4 1432.61 -1438.66 12.0 12.0 FALSE
						truckaudio_s4flag = 4
					ENDIF
				ELSE
					truckaudio_s4flag = 4
					progressaudio_s4flag = 12
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 9
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 4
						audio_label_s4 = SOUND_SMO4_FL	//Ah, they hit a bus!
						$input_text_s4 = SMO4_FL	//Ah, they hit a bus!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 10
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_FM	//I’ll never dis public transport again!
					$input_text_s4 = SMO4_FM //I’ll never dis public transport again!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			//roadblock
			IF truckaudio_s4flag = 4
				IF LOCATE_CAR_2D smokebike_s4 1406.73 -1393.15 10.0 10.0 FALSE
					truckaudio_s4flag = 5
				ENDIF
			ENDIF

			IF progressaudio_s4flag = 11
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 5
						audio_label_s4 = SOUND_SMO4_GA	//Damn, road-blocked!
						$input_text_s4 = SMO4_GA //Damn, road-blocked!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 12
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GB	//Ah shit Smoke, these cats is organized,
					$input_text_s4 = SMO4_GB //Ah shit Smoke, these cats is organized,
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 13
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GD	//Look, Carl, all I know is they’s real pissed with us.
					$input_text_s4 = SMO4_GD	//Look, Carl, all I know is they’s real pissed with us.
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 14
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GE	//Hold on, I got an idea!
					$input_text_s4 = SMO4_GE	//Hold on, I got an idea!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 15
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GF	//Smoke, what you thinking? Flood control’s a dead end, man!
					$input_text_s4 = SMO4_GF	//Smoke, what you thinking? Flood control’s a dead end, man!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 16
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GG	//We had to lose those cars!
					$input_text_s4 = SMO4_GG	//We had to lose those cars!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 17
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GH	//Don’t worry, I know a way out, up past Grove street!
					$input_text_s4 = SMO4_GH	//Don’t worry, I know a way out, up past Grove street!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 18
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GJ	//The old sewer tunnel? Oh man....
					$input_text_s4 = SMO4_GJ	//The old sewer tunnel? Oh man....
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 19
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_ER	//Watch our backs!
					$input_text_s4 = SMO4_ER	//Watch our backs!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			
			//more bikes
			IF truckaudio_s4flag = 5
				IF LOCATE_CAR_2D smokebike_s4 1362.39 -1590.12 15.0 15.0 FALSE
					truckaudio_s4flag = 6
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 20
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 6
						audio_label_s4 = SOUND_SMO4_GK	//Smoke, it’s more bikes!
						$input_text_s4 = SMO4_GK	//Smoke, it’s more bikes!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 21
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_EL	//Pop their brains, CJ!
					$input_text_s4 = SMO4_EL	//Pop their brains, CJ!
					GOSUB load_audio_s4
				ENDIF
			ENDIF


			//audio
			GOSUB process_audio_s4

		ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////	  INTO THE STORM DRAINS 	  //////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF firstchase_s4flag = 1
	IF NOT IS_CAR_DEAD smokebike_s4

		IF storm1_s4flag = 0

			CREATE_CAR BF400 1446.923 -1429.569 12.1 bike1_s4
			SET_PETROL_TANK_WEAKPOINT bike1_s4 FALSE
			SET_CAR_HEADING bike1_s4 174.719
			CREATE_CHAR_INSIDE_CAR bike1_s4 PEDTYPE_MISSION1 MAFFA russian1_s4
			SET_CHAR_DECISION_MAKER russian1_s4 smoke4_DM
			SET_CHAR_HEALTH russian1_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian1_s4 TRUE
			CREATE_CHAR_AS_PASSENGER bike1_s4 PEDTYPE_MISSION1 MAFFA 0 russian2_s4
			GIVE_WEAPON_TO_CHAR russian2_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_HEALTH russian2_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian2_s4 TRUE
			SET_CHAR_DECISION_MAKER russian2_s4 smoke4_DM
			ADD_BLIP_FOR_CAR bike1_s4 bike1_s4blip
			CHANGE_BLIP_DISPLAY bike1_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike1_s4blip 2
			bike1swap_s4flag = 1
			bike1_s4flag = 1

			CREATE_CAR BF400 1377.421 -1571.321 12.1 bike2_s4
			SET_PETROL_TANK_WEAKPOINT bike2_s4 FALSE
			SET_CAR_HEADING bike2_s4 166.577
			CREATE_CHAR_INSIDE_CAR bike2_s4 PEDTYPE_MISSION1 MAFFA russian3_s4
			SET_CHAR_DECISION_MAKER russian3_s4 smoke4_DM
			SET_CHAR_HEALTH russian3_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian3_s4 TRUE
			CREATE_CHAR_AS_PASSENGER bike2_s4 PEDTYPE_MISSION1 MAFFA 0 russian4_s4
			GIVE_WEAPON_TO_CHAR russian4_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_HEALTH russian4_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian4_s4 TRUE
			SET_CHAR_DECISION_MAKER russian4_s4 smoke4_DM
			ADD_BLIP_FOR_CAR bike2_s4 bike2_s4blip
			CHANGE_BLIP_DISPLAY bike2_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike2_s4blip 2
			bike2swap_s4flag = 1
			bike2_s4flag = 1

			CREATE_CAR BF400 1361.086 -1665.478 12.62 bike3_s4
			SET_PETROL_TANK_WEAKPOINT bike3_s4 FALSE
			SET_CAR_HEADING bike3_s4 187.657
			CREATE_CHAR_INSIDE_CAR bike3_s4 PEDTYPE_MISSION1 MAFFA russian5_s4
			SET_CHAR_DECISION_MAKER russian5_s4 smoke4_DM
			SET_CHAR_HEALTH russian5_s4 50
			CREATE_CHAR_AS_PASSENGER bike3_s4 PEDTYPE_MISSION1 MAFFA 0 russian6_s4
			GIVE_WEAPON_TO_CHAR russian6_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_DECISION_MAKER russian6_s4 smoke4_DM
			SET_CHAR_HEALTH russian6_s4 10
			ADD_BLIP_FOR_CAR bike3_s4 bike3_s4blip
			CHANGE_BLIP_DISPLAY bike3_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike3_s4blip 2
			bike3swap_s4flag = 1
			bike3_s4flag = 1

			CREATE_CAR BF400 1365.219 -1420.908 12.104 bike4_s4
			SET_PETROL_TANK_WEAKPOINT bike4_s4 FALSE
			SET_CAR_HEADING bike4_s4 353.946
			CREATE_CHAR_INSIDE_CAR bike4_s4 PEDTYPE_MISSION1 MAFFA russian15_s4
			SET_CHAR_DECISION_MAKER russian15_s4 smoke4_DM
			SET_CHAR_HEALTH russian15_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian15_s4 TRUE
			CREATE_CHAR_AS_PASSENGER bike4_s4 PEDTYPE_MISSION1 MAFFA 0 russian16_s4
			GIVE_WEAPON_TO_CHAR russian16_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_HEALTH russian16_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian16_s4 TRUE
			SET_CHAR_DECISION_MAKER russian16_s4 smoke4_DM
			ADD_BLIP_FOR_CAR bike4_s4 bike4_s4blip
			CHANGE_BLIP_DISPLAY bike4_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike4_s4blip 2
			bike4swap_s4flag = 1
			bike4_s4flag = 1

			CREATE_CAR SULTAN 1374.372 -1665.108 12.91 car1_s4
			SET_PETROL_TANK_WEAKPOINT car1_s4 FALSE
			SET_CAR_HEADING car1_s4 176.22
			CREATE_CHAR_INSIDE_CAR car1_s4 PEDTYPE_MISSION1 MAFFA russian7_s4
			SET_CHAR_DECISION_MAKER russian7_s4 smoke4_DM
			CREATE_CHAR_AS_PASSENGER car1_s4 PEDTYPE_MISSION1 MAFFA 0 russian8_s4
			GIVE_WEAPON_TO_CHAR russian8_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_HEALTH russian8_s4 10
			SET_CHAR_DECISION_MAKER russian8_s4 smoke4_DM
			ADD_BLIP_FOR_CAR car1_s4 car1_s4blip
			CHANGE_BLIP_DISPLAY car1_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE car1_s4blip 2
			car1swap_s4flag = 1
			car1_s4flag = 1

			CREATE_CAR SULTAN 1373.704 -1633.619 12.935 car2_s4
			SET_PETROL_TANK_WEAKPOINT car2_s4 FALSE
			SET_CAR_HEADING car2_s4 176.22
			CREATE_CHAR_INSIDE_CAR car2_s4 PEDTYPE_MISSION1 MAFFA russian9_s4
			SET_CHAR_DECISION_MAKER russian9_s4 smoke4_DM
			CREATE_CHAR_AS_PASSENGER car2_s4 PEDTYPE_MISSION1 MAFFA 0 russian10_s4
			GIVE_WEAPON_TO_CHAR russian10_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_DECISION_MAKER russian10_s4 smoke4_DM
			SET_CHAR_HEALTH russian10_s4 10
			ADD_BLIP_FOR_CAR car2_s4 car2_s4blip
			CHANGE_BLIP_DISPLAY car2_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE car2_s4blip 2
			car2swap_s4flag = 1
			car2_s4flag = 1

			//WARP_CHAR_INTO_CAR scplayer car1_s4
			WAIT 0

			IF NOT IS_CAR_DEAD smokebike_s4
				FREEZE_CAR_POSITION smokebike_s4 FALSE
				START_PLAYBACK_RECORDED_CAR smokebike_s4 258
			ENDIF
			
			IF NOT IS_CAR_DEAD bike1_s4
				IF NOT IS_CHAR_DEAD russian1_s4
					START_PLAYBACK_RECORDED_CAR bike1_s4 259
					IF NOT IS_CHAR_DEAD russian1_s4
						IF difficulty_s4flag < 2
							GIVE_WEAPON_TO_CHAR russian1_s4 WEAPONTYPE_MICRO_UZI 99999
							TASK_DRIVE_BY russian1_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD russian2_s4
						TASK_DRIVE_BY russian2_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					ENDIF
				ENDIF
			ENDIF

			IF NOT IS_CAR_DEAD bike4_s4
				IF NOT IS_CHAR_DEAD russian15_s4
					START_PLAYBACK_RECORDED_CAR bike4_s4 264
				ENDIF
				IF NOT IS_CHAR_DEAD russian15_s4
					IF difficulty_s4flag < 3
						GIVE_WEAPON_TO_CHAR russian15_s4 WEAPONTYPE_MICRO_UZI 99999
						TASK_DRIVE_BY russian15_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD russian16_s4
					TASK_DRIVE_BY russian16_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 90
				ENDIF
			ENDIF
			storm1_s4flag = 1
		ENDIF

		IF storm1_s4flag = 1
			IF LOCATE_CAR_2D smokebike_s4 1374.62 -1549.64 10.0 10.0 FALSE
				IF NOT IS_CAR_DEAD bike2_s4
					IF NOT IS_CHAR_DEAD russian3_s4
						START_PLAYBACK_RECORDED_CAR bike2_s4 260
						IF difficulty_s4flag < 2
							GIVE_WEAPON_TO_CHAR russian3_s4 WEAPONTYPE_MICRO_UZI 99999
							TASK_DRIVE_BY russian3_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD russian4_s4
						TASK_DRIVE_BY russian4_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					ENDIF
				ENDIF

				DELETE_CAR coach3_s4 
				DELETE_CAR rcar1_s4
				DELETE_CAR rcar2_s4
				DELETE_CAR rcar3_s4
				DELETE_CAR rcar4_s4
				DELETE_CHAR russian11_s4
				DELETE_CHAR russian12_s4
				DELETE_CHAR russian13_s4
				DELETE_CHAR russian14_s4
				DELETE_CHAR russian20_s4
				REMOVE_BLIP russian12_s4blip
				REMOVE_BLIP russian13_s4blip
				REMOVE_BLIP russian14_s4blip
				REMOVE_BLIP russian20_s4blip
				REMOVE_BLIP rcar1_s4blip
				REMOVE_BLIP rcar3_s4blip
				REMOVE_BLIP rcar4_s4blip
				storm1_s4flag = 2
			ENDIF
		ENDIF

		IF storm1_s4flag = 2
			IF LOCATE_CAR_2D smokebike_s4 1361.086 -1665.478 10.0 10.0 FALSE
					IF NOT IS_CAR_DEAD bike3_s4
						IF NOT IS_CHAR_DEAD russian5_s4
							START_PLAYBACK_RECORDED_CAR bike3_s4 261
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD russian6_s4
						TASK_DRIVE_BY russian6_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					ENDIF

					IF NOT IS_CAR_DEAD car1_s4 
						IF NOT IS_CHAR_DEAD russian7_s4
							START_PLAYBACK_RECORDED_CAR car1_s4 262
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD russian8_s4
						TASK_DRIVE_BY russian8_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 10
					ENDIF

					IF NOT IS_CAR_DEAD car2_s4
						IF NOT IS_CHAR_DEAD russian9_s4
							START_PLAYBACK_RECORDED_CAR car2_s4 263
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD russian10_s4
						TASK_DRIVE_BY russian10_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 10
					ENDIF
				storm1_s4flag = 3
			ENDIF
		ENDIF

		//camera switch
		IF storm1_s4flag = 3
			IF LOCATE_CAR_2D smokebike_s4 1530.0 -1742.0 10.0 10.0 TRUE
				
				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL PLAYER1 OFF				
				IF NOT IS_CAR_DEAD smokebike_s4
					SET_CAR_PROOFS smokebike_s4 TRUE TRUE TRUE TRUE TRUE
				ENDIF
				cam_offz = 0.2
				cam_Z_look_s4 = 0.0
				ATTACH_CAMERA_TO_VEHICLE smokebike_s4 0.0 -2.0 cam_offz 0.0 0.0 cam_Z_look_s4 0.0 JUMP_CUT
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer smokebike_s4 0

				STOP_PLAYBACK_RECORDED_CAR bike1_s4
				DELETE_CAR bike1_s4
				REMOVE_BLIP bike1_s4blip
				DELETE_CHAR russian1_s4
				DELETE_CHAR russian2_s4

				STOP_PLAYBACK_RECORDED_CAR bike2_s4
				DELETE_CAR bike2_s4
				REMOVE_BLIP bike2_s4blip
				DELETE_CHAR russian3_s4
				DELETE_CHAR russian4_s4

				STOP_PLAYBACK_RECORDED_CAR bike3_s4
				DELETE_CAR bike3_s4
				REMOVE_BLIP bike3_s4blip
				DELETE_CHAR russian5_s4
				DELETE_CHAR russian6_s4

				STOP_PLAYBACK_RECORDED_CAR bike4_s4
				DELETE_CAR bike4_s4
				REMOVE_BLIP bike4_s4blip
				DELETE_CHAR russian15_s4
				DELETE_CHAR russian16_s4

				STOP_PLAYBACK_RECORDED_CAR car1_s4
				DELETE_CAR car1_s4
				REMOVE_BLIP car1_s4blip
				DELETE_CHAR russian7_s4
				DELETE_CHAR russian8_s4

				STOP_PLAYBACK_RECORDED_CAR car2_s4
				DELETE_CAR car2_s4
				REMOVE_BLIP car2_s4blip
				DELETE_CHAR russian9_s4
				DELETE_CHAR russian10_s4

				CREATE_CAR BF400 1505.103 -1733.09 3.05 bike1_s4
				SET_PETROL_TANK_WEAKPOINT bike1_s4 FALSE
				SET_CAR_HEADING bike1_s4 258.539
				CREATE_CHAR_INSIDE_CAR bike1_s4 PEDTYPE_MISSION1 MAFFA russian1_s4
				SET_CHAR_DECISION_MAKER russian1_s4 smoke4_DM
				SET_CHAR_HEALTH russian1_s4 10
				CREATE_CHAR_AS_PASSENGER bike1_s4 PEDTYPE_MISSION1 MAFFA 0 russian2_s4
				GIVE_WEAPON_TO_CHAR russian2_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_HEALTH russian2_s4 10
				SET_CHAR_DECISION_MAKER russian2_s4 smoke4_DM

				CREATE_CAR BF400 1487.213 -1734.792 3.25 bike2_s4
				SET_PETROL_TANK_WEAKPOINT bike2_s4 FALSE
				SET_CAR_HEADING bike2_s4 250.829
				CREATE_CHAR_INSIDE_CAR bike2_s4 PEDTYPE_MISSION1 MAFFA russian3_s4
				SET_CHAR_DECISION_MAKER russian3_s4 smoke4_DM
				SET_CHAR_HEALTH russian3_s4 10
				CREATE_CHAR_AS_PASSENGER bike2_s4 PEDTYPE_MISSION1 MAFFA 0 russian4_s4
				GIVE_WEAPON_TO_CHAR russian4_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_HEALTH russian4_s4 20
				SET_CHAR_DECISION_MAKER russian4_s4 smoke4_DM

				CREATE_CAR SULTAN 1496.864 -1731.303 6.3 car1_s4
				SET_PETROL_TANK_WEAKPOINT car1_s4 FALSE
				SET_CAR_HEADING car1_s4 255.305
				CREATE_CHAR_INSIDE_CAR car1_s4 PEDTYPE_MISSION1 MAFFA russian7_s4
				SET_CHAR_DECISION_MAKER russian7_s4 smoke4_DM
				CREATE_CHAR_AS_PASSENGER car1_s4 PEDTYPE_MISSION1 MAFFA 0 russian8_s4
				GIVE_WEAPON_TO_CHAR russian8_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_DECISION_MAKER russian8_s4 smoke4_DM
				SET_CHAR_HEALTH russian8_s4 10

				CREATE_CAR PACKER 1550.873 -1737.1218 14.1055 packer_s4
				SET_PETROL_TANK_WEAKPOINT packer_s4 FALSE
				SET_CAR_HEADING packer_s4 245.140
				CREATE_CHAR_INSIDE_CAR packer_s4 PEDTYPE_MISSION1 MAFFA packerdriver_s4
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE packerdriver_s4 FALSE
				SET_CAR_PROOFS packer_s4 TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_PROOFS packerdriver_s4 TRUE TRUE TRUE TRUE TRUE
				SET_CHAR_DECISION_MAKER packerdriver_s4 smoke4_DM
				CREATE_CHAR_AS_PASSENGER packer_s4 PEDTYPE_MISSION1 MAFFA 0 packerpassenger_s4
				GIVE_WEAPON_TO_CHAR packerpassenger_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_DECISION_MAKER packerpassenger_s4 smoke4_DM

				CREATE_CAR SULTAN 1550.873 -1737.1218 -14.1055 packercar1_s4
				SET_PETROL_TANK_WEAKPOINT packercar1_s4 FALSE
				SET_CAR_HEADING packercar1_s4 256.415
				SET_CAR_PROOFS packercar1_s4 TRUE TRUE TRUE TRUE TRUE
				ATTACH_CAR_TO_CAR packercar1_s4 packer_s4 0.0 -4.6 0.65 15.0 0.0 0.0
				packercar1_s4flag = 0
				
				CREATE_CAR SULTAN 1550.873 -1737.1218 14.1055 packercar2_s4
				SET_PETROL_TANK_WEAKPOINT packercar2_s4 FALSE
				SET_CAR_HEADING packercar2_s4 256.415
				SET_CAR_PROOFS packercar2_s4 TRUE TRUE TRUE TRUE TRUE
				ATTACH_CAR_TO_CAR packercar2_s4 packer_s4 0.0 1.15 2.4 15.0 0.0 0.0
				packercar2_s4flag = 0

				TIMERA = 0
				gopacker_s4flag = 1			
				storm1_s4flag = 4
			ENDIF
		ENDIF

		IF gopacker_s4flag = 1
			IF LOCATE_CAR_2D smokebike_s4 1550.52 -1749.19 10.0 10.0 TRUE

				IF NOT IS_CAR_DEAD packer_s4
					START_PLAYBACK_RECORDED_CAR packer_s4 265
				ENDIF

				IF NOT IS_CAR_DEAD bike1_s4
					START_PLAYBACK_RECORDED_CAR bike1_s4 266
				ENDIF

				IF NOT IS_CAR_DEAD car1_s4
					START_PLAYBACK_RECORDED_CAR car1_s4 267
				ENDIF

				IF NOT IS_CAR_DEAD bike2_s4
					START_PLAYBACK_RECORDED_CAR bike2_s4 268
				ENDIF

				gopacker_s4flag = 2
			ENDIF
		ENDIF


		IF storm1_s4flag = 4
			IF TIMERA > 250

				// IF CAMERA Z POSITION STARTS AT 0.0 THIS WILL STOP THE CAMERA ONCE IT HAS GONE METRES HIGH
				IF cam_offz < 0.8 
					cam_offz +=@ 0.008
					cam_Z_look_s4 +=@ 0.008
					ATTACH_CAMERA_TO_VEHICLE smokebike_s4 0.0 -2.0 cam_offz 0.0 0.0 cam_Z_look_s4 0.0 JUMP_CUT
				ELSE
					storm1_s4flag = 5
				ENDIF

			ENDIF
		ENDIF

		IF storm1_s4flag = 5
			IF TIMERA > 2500
				SET_FIXED_CAMERA_POSITION 1605.5912 -1766.7932 3.3342 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 1604.7743 -1766.2546 3.5404 JUMP_CUT
				SET_TIME_SCALE 0.9
				TIMERA = 0
				TIMERB = 0
				spark_s4flag = 1
				storm1_s4flag = 6
			ENDIF
		ENDIF

		IF storm1_s4flag = 6
			IF TIMERA > 800
			    z = 0.3
				storm1_s4flag = 7
			ENDIF
		ENDIF

		IF storm1_s4flag = 7
			IF z < 0.6
		     	z+= 0.001
		        SET_TIME_SCALE z
			ELSE
				IF TIMERA > 2500
				OR z > 0.6
					storm1_s4flag = 7
				    SET_TIME_SCALE 0.5
				ENDIF
			ENDIF
		ENDIF

		IF storm1_s4flag = 7
			IF TIMERA > 2000
				POINT_CAMERA_AT_POINT 1605.7826 -1765.8336 3.5404 INTERPOLATION
				z = 0.5
				TIMERA = 0
				storm1_s4flag = 8
			ENDIF
		ENDIF

		IF storm1_s4flag = 8
			IF z < 1.0
		     	z+= 0.01
		        SET_TIME_SCALE z
			ELSE
				IF TIMERA > 1000
				OR z > 0.9
					storm1_s4flag = 9
					TIMERA = 0
				    SET_TIME_SCALE 1.0
				ENDIF
			ENDIF
		ENDIF		

		IF storm1_s4flag = 9
			IF TIMERA > 2800
				firstchase_s4flag = 2
				storm1_s4flag = 10
			ENDIF
		ENDIF

		IF spark_s4flag = 1
			IF TIMERB >	1242
				ADD_SPARKS 1580.85 -1752.32 12.91 0.0 0.0 1.0 60
				ADD_SPARKS 1580.75 -1749.51 12.92 0.0 0.0 1.0 100
				spark_s4flag = 2
			ENDIF
		ENDIF

		IF spark_s4flag = 2
			IF TIMERB >	1400
				ADD_SPARKS 1580.74 -1752.68 12.87 0.0 0.0 1.0 100
				ADD_SPARKS 1580.85 -1749.75 12.91 0.0 0.0 1.0 60
				spark_s4flag = 3
			ENDIF
		ENDIF

		IF spark_s4flag = 3
			IF TIMERB >	1500
				ADD_SPARKS 1580.85 -1752.32 12.91 0.0 0.0 1.0 100
				ADD_SPARKS 1580.85 -1752.32 12.91 0.0 0.0 1.0 80
				spark_s4flag = 4
			ENDIF
		ENDIF

		IF spark_s4flag = 4
			IF TIMERB > 1600
				ADD_SPARKS 1580.85 -1752.32 12.91 0.0 0.0 1.0 100
				ADD_SPARKS 1580.75 -1749.51 12.92 0.0 0.0 1.0 60
				spark_s4flag = 5
			ENDIF
		ENDIF

		IF spark_s4flag = 5
			IF TIMERB > 1700
				ADD_SPARKS 1580.74 -1752.68 12.87 0.0 0.0 1.0 80
				ADD_SPARKS 1580.85 -1749.75 12.91 0.0 0.0 1.0 100
				spark_s4flag = 6
			ENDIF
		ENDIF

		IF spark_s4flag = 6
			IF TIMERB >	1800
				ADD_SPARKS 1580.85 -1752.32 12.91 0.0 0.0 1.0 60
				ADD_SPARKS 1580.85 -1752.32 12.91 0.0 0.0 1.0 80
				spark_s4flag = 7
			ENDIF
		ENDIF

		IF spark_s4flag = 7
			IF TIMERB >	2404
				ADD_SPARKS 1599.28 -1757.56 3.11 0.0 0.0 1.0 60
				spark_s4flag = 8
			ENDIF
		ENDIF

		IF spark_s4flag = 8
			IF TIMERB >	3418 //2517
				ADD_SPARKS 1599.76 1757.96 3.14 0.0 0.0 1.0 100
				ADD_SPARKS 1601.69 -1758.73 3.18 0.0 0.0 1.0 60
				spark_s4flag = 9
			ENDIF
		ENDIF

		IF spark_s4flag = 9
			IF TIMERB >	3598 //2848
				ADD_SPARKS 1605.46 -1763.14 3.18 0.0 0.0 1.0 100
				spark_s4flag = 10
			ENDIF
		ENDIF

		IF spark_s4flag = 10
			IF TIMERB > 3600 //2800
				ADD_SPARKS 1605.46 -1763.14 3.18 0.0 0.0 1.0 80
				spark_s4flag = 11
			ENDIF
		ENDIF



		//spin/stop bikes if the both guys are not in car
		IF storm1_s4flag < 4

			IF bike1swap_s4flag = 1
				IF NOT IS_CAR_DEAD bike1_s4
					IF NOT IS_CHAR_DEAD russian1_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian2_s4
							IF IS_CHAR_IN_CAR russian2_s4 bike1_s4
								GET_DRIVER_OF_CAR bike1_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD russian2_s4 player_x player_y -10.0
									WARP_CHAR_INTO_CAR russian2_s4 bike1_s4
									TASK_DRIVE_BY russian2_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
									bike1swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike1_s4 18.0
							STOP_PLAYBACK_RECORDED_CAR bike1_s4
							SET_CAR_ROTATION_VELOCITY bike1_s4 1.5 3.3999 4.1003
							bike1swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike1swap_s4flag = 2
				IF IS_CHAR_DEAD russian2_s4
					STOP_PLAYBACK_RECORDED_CAR bike1_s4
					IF NOT IS_CAR_DEAD bike1_s4
						SET_CAR_FORWARD_SPEED bike1_s4 18.0
						SET_CAR_ROTATION_VELOCITY bike1_s4 1.5 3.3999 4.1003
					ENDIF
					bike1swap_s4flag = 3
				ENDIF 
			ENDIF
			IF bike1_s4flag = 1
				IF IS_CHAR_DEAD russian1_s4
					IF IS_CHAR_DEAD russian2_s4
					OR IS_CAR_DEAD bike1_s4
						STOP_PLAYBACK_RECORDED_CAR bike1_s4
						REMOVE_BLIP bike1_s4blip
						MARK_CAR_AS_NO_LONGER_NEEDED bike1_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian1_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian2_s4
						bike1_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

			IF bike2swap_s4flag = 1
				IF NOT IS_CAR_DEAD bike2_s4
					IF NOT IS_CHAR_DEAD russian3_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian4_s4
							IF IS_CHAR_IN_CAR russian4_s4 bike2_s4
								GET_DRIVER_OF_CAR bike2_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD russian4_s4 player_x player_y -10.0
									WARP_CHAR_INTO_CAR russian4_s4 bike2_s4
									TASK_DRIVE_BY russian4_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
									bike2swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike2_s4 15.0
							STOP_PLAYBACK_RECORDED_CAR bike2_s4
							SET_CAR_ROTATION_VELOCITY bike2_s4 5.5 3.3999 5.1003
							bike2swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike2swap_s4flag = 2
				IF IS_CHAR_DEAD russian4_s4
					STOP_PLAYBACK_RECORDED_CAR bike2_s4
					IF NOT IS_CAR_DEAD bike2_s4
						SET_CAR_FORWARD_SPEED bike2_s4 15.0
						SET_CAR_ROTATION_VELOCITY bike2_s4 5.5 3.3999 5.1003
					ENDIF
					bike2swap_s4flag = 3
				ENDIF 
			ENDIF
			IF bike2_s4flag = 1
				IF IS_CHAR_DEAD russian3_s4
					IF IS_CHAR_DEAD russian4_s4
					OR IS_CAR_DEAD bike2_s4
						STOP_PLAYBACK_RECORDED_CAR bike2_s4
						REMOVE_BLIP bike2_s4blip
						MARK_CAR_AS_NO_LONGER_NEEDED bike2_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian3_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian4_s4
						bike2_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

			IF bike3swap_s4flag = 1
				IF NOT IS_CAR_DEAD bike3_s4
					IF NOT IS_CHAR_DEAD russian5_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian6_s4
							IF IS_CHAR_IN_CAR russian6_s4 bike3_s4
								GET_DRIVER_OF_CAR bike3_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD russian6_s4 player_x player_y -10.0
									WARP_CHAR_INTO_CAR russian6_s4 bike3_s4
									TASK_DRIVE_BY russian6_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
									bike3swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike3_s4 15.0
							STOP_PLAYBACK_RECORDED_CAR bike3_s4
							SET_CAR_ROTATION_VELOCITY bike3_s4 1.5 4.3999 5.1003
							bike3swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike3swap_s4flag = 2
				IF IS_CHAR_DEAD russian6_s4
					STOP_PLAYBACK_RECORDED_CAR bike3_s4
					IF NOT IS_CAR_DEAD bike3_s4
						SET_CAR_FORWARD_SPEED bike3_s4 15.0
						SET_CAR_ROTATION_VELOCITY bike3_s4 1.5 4.3999 5.1003
					ENDIF
					bike3swap_s4flag = 3
				ENDIF 
			ENDIF
			IF bike3_s4flag = 1
				IF IS_CHAR_DEAD russian5_s4
					IF IS_CHAR_DEAD russian6_s4
					OR IS_CAR_DEAD bike3_s4
						STOP_PLAYBACK_RECORDED_CAR bike3_s4
						REMOVE_BLIP bike3_s4blip
						MARK_CAR_AS_NO_LONGER_NEEDED bike3_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian5_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian6_s4
						bike3_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

			IF bike4swap_s4flag = 1
				IF NOT IS_CAR_DEAD bike4_s4
					IF NOT IS_CHAR_DEAD russian15_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian16_s4
							IF IS_CHAR_IN_CAR russian16_s4 bike4_s4
								GET_DRIVER_OF_CAR bike4_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
									WARP_CHAR_FROM_CAR_TO_COORD russian16_s4 player_x player_y -10.0
									WARP_CHAR_INTO_CAR russian16_s4 bike4_s4
									TASK_DRIVE_BY russian16_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
									bike4swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							SET_CAR_FORWARD_SPEED bike4_s4 15.0
							STOP_PLAYBACK_RECORDED_CAR bike4_s4
							SET_CAR_ROTATION_VELOCITY bike4_s4 2.5 3.3999 3.1003
							bike4swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF bike4swap_s4flag = 2
				IF IS_CHAR_DEAD russian16_s4
					STOP_PLAYBACK_RECORDED_CAR bike4_s4
					IF NOT IS_CAR_DEAD bike4_s4
						SET_CAR_FORWARD_SPEED bike4_s4 15.0
						SET_CAR_ROTATION_VELOCITY bike4_s4 2.5 3.3999 3.1003
					ENDIF
					bike4swap_s4flag = 3
				ENDIF 
			ENDIF
			IF bike4_s4flag = 1
				IF IS_CHAR_DEAD russian15_s4
					IF IS_CHAR_DEAD russian16_s4
					OR IS_CAR_DEAD bike4_s4
						STOP_PLAYBACK_RECORDED_CAR bike4_s4
						REMOVE_BLIP bike4_s4blip
						MARK_CAR_AS_NO_LONGER_NEEDED bike4_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian15_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian16_s4
						bike4_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

			IF car1swap_s4flag = 1
				IF NOT IS_CAR_DEAD car1_s4
					IF NOT IS_CHAR_DEAD russian7_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian8_s4
							IF IS_CHAR_IN_CAR russian8_s4 car1_s4
								GET_DRIVER_OF_CAR car1_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT russian8_s4 car1_s4
									car1swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR car1_s4
							car1swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF car1swap_s4flag = 2
				IF IS_CHAR_DEAD russian8_s4
					STOP_PLAYBACK_RECORDED_CAR car1_s4
					car1swap_s4flag = 3
				ENDIF 
			ENDIF
			IF car1_s4flag = 1
				IF IS_CHAR_DEAD russian7_s4
					IF IS_CHAR_DEAD russian8_s4
					OR IS_CAR_DEAD car1_s4
						STOP_PLAYBACK_RECORDED_CAR car1_s4
						REMOVE_BLIP car1_s4blip
						MARK_CAR_AS_NO_LONGER_NEEDED car1_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian7_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian8_s4
						car1_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

			IF car2swap_s4flag = 1
				IF NOT IS_CAR_DEAD car2_s4
					IF NOT IS_CHAR_DEAD russian9_s4
					ELSE
						IF NOT IS_CHAR_DEAD russian10_s4
							IF IS_CHAR_IN_CAR russian10_s4 car2_s4
								GET_DRIVER_OF_CAR car2_s4 driverofcar_s4
								IF driverofcar_s4 = -1
									TASK_SHUFFLE_TO_NEXT_CAR_SEAT russian10_s4 car2_s4
									car2swap_s4flag = 2
								ENDIF
							ENDIF
						ELSE
							STOP_PLAYBACK_RECORDED_CAR car2_s4
							car2swap_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF car2swap_s4flag = 2
				IF IS_CHAR_DEAD russian10_s4
					STOP_PLAYBACK_RECORDED_CAR car2_s4
					car2swap_s4flag = 3
				ENDIF 
			ENDIF
			IF car2_s4flag = 1
				IF IS_CHAR_DEAD russian9_s4
					IF IS_CHAR_DEAD russian10_s4
					OR IS_CAR_DEAD car2_s4
						STOP_PLAYBACK_RECORDED_CAR car2_s4
						MARK_CAR_AS_NO_LONGER_NEEDED car2_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian9_s4
						MARK_CHAR_AS_NO_LONGER_NEEDED russian10_s4
						REMOVE_BLIP car2_s4blip
						car2_s4flag = 2
					ENDIF
				ENDIF
			ENDIF

	   	ENDIF


	ENDIF
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////	  PACKER CHASE	  //////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF firstchase_s4flag = 2
	IF NOT IS_CAR_DEAD smokebike_s4

		IF thirdchase_s4flag = 0
			DO_FADE 0 FADE_OUT
			STOP_PLAYBACK_RECORDED_CAR bike1_s4
			DELETE_CAR bike1_s4
			DELETE_CHAR russian1_s4
			DELETE_CHAR russian2_s4

			STOP_PLAYBACK_RECORDED_CAR bike2_s4
			DELETE_CAR bike2_s4
			DELETE_CHAR russian3_s4
			DELETE_CHAR russian4_s4

			STOP_PLAYBACK_RECORDED_CAR car1_s4
			DELETE_CAR car1_s4
			DELETE_CHAR russian7_s4
			DELETE_CHAR russian8_s4

			DELETE_CAR packercar1_s4
			DELETE_CAR packercar2_s4

			STOP_PLAYBACK_RECORDED_CAR packer_s4
			DELETE_CAR packer_s4
			DELETE_CHAR packerdriver_s4

			IF NOT IS_CAR_DEAD smokebike_s4
				STOP_PLAYBACK_RECORDED_CAR smokebike_s4
			ENDIF


			REQUEST_CAR_RECORDING 269	
			REQUEST_CAR_RECORDING 270
			REQUEST_CAR_RECORDING 271
			REQUEST_CAR_RECORDING 272

			REQUEST_CAR_RECORDING 273 //bike 1 not in cutscene
			REQUEST_CAR_RECORDING 274 //bike 1 not in cutscene
			REQUEST_CAR_RECORDING 275 //car that crashes into packer
			REQUEST_CAR_RECORDING 276 //car 2 that goes into packer
			REQUEST_CAR_RECORDING 277 //car that goes all the way
			REQUEST_CAR_RECORDING 278 //bike that comes from slip road

			REQUEST_CAR_RECORDING 279 //bikes that come from the slip road to the left

			REQUEST_CAR_RECORDING 280 //jumps in from the bridge
			REQUEST_CAR_RECORDING 281 //car that comes down the embankment

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED	273
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 274
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 275
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 276
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 277
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED	278
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 279
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 280
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 281
				WAIT 0
			ENDWHILE

			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED	269
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 270
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 271
				OR NOT HAS_CAR_RECORDING_BEEN_LOADED 272
				WAIT 0
			ENDWHILE

			CREATE_CAR PACKER 1601.9146 -1755.917 4.6 packer_s4
			SET_PETROL_TANK_WEAKPOINT packer_s4 FALSE
			SET_CAR_HEADING packer_s4 256.415
			CREATE_CHAR_INSIDE_CAR packer_s4 PEDTYPE_MISSION1 MAFFA packerdriver_s4
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE packerdriver_s4 FALSE
			SET_CAR_PROOFS packer_s4 TRUE TRUE TRUE TRUE TRUE
			SET_CHAR_PROOFS packerdriver_s4 TRUE TRUE TRUE TRUE TRUE
			SET_CHAR_DECISION_MAKER packerdriver_s4 smoke4_DM
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER packerdriver_s4 TRUE
			ADD_BLIP_FOR_CAR packer_s4 packer_s4blip
			CHANGE_BLIP_DISPLAY packer_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE packer_s4blip 2

			CREATE_CAR SULTAN 1550.873 -1737.1218 -14.1055 packercar1_s4
			SET_PETROL_TANK_WEAKPOINT packercar1_s4 FALSE
			SET_CAR_HEADING packercar1_s4 256.415
			SET_CAR_PROOFS packercar1_s4 TRUE TRUE TRUE TRUE TRUE
			ATTACH_CAR_TO_CAR packercar1_s4 packer_s4 0.0 -4.6 0.65 15.0 0.0 0.0
			packersmash1_s4flag = 1

			CREATE_CAR SULTAN 1550.873 -1737.1218 14.1055 packercar2_s4
			SET_PETROL_TANK_WEAKPOINT packercar2_s4 FALSE
			SET_CAR_HEADING packercar2_s4 256.415
			SET_CAR_PROOFS packercar2_s4 TRUE TRUE TRUE TRUE TRUE
			ATTACH_CAR_TO_CAR packercar2_s4 packer_s4 0.0 1.15 2.4 15.0 0.0 0.0
			packersmash2_s4flag = 1

			CREATE_CAR BF400 1505.103 -1733.09 3.05 bike1_s4
			SET_PETROL_TANK_WEAKPOINT bike1_s4 FALSE
			SET_CAR_HEADING bike1_s4 258.539
			SET_CAR_PROOFS bike1_s4 FALSE TRUE TRUE TRUE FALSE
			CREATE_CHAR_INSIDE_CAR bike1_s4 PEDTYPE_MISSION1 MAFFA russian1_s4
			SET_CHAR_HEALTH russian1_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian1_s4 10
			SET_CHAR_DECISION_MAKER russian1_s4 smoke4_DM
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian1_s4 TRUE
			CREATE_CHAR_AS_PASSENGER bike1_s4 PEDTYPE_MISSION1 MAFFA 0 russian2_s4
			GIVE_WEAPON_TO_CHAR russian2_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_HEALTH russian2_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian2_s4 10
			SET_CHAR_DECISION_MAKER russian2_s4 smoke4_DM
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian2_s4 TRUE
			bike1swap_s4flag = 1
			bike1_s4flag = 1
			ADD_BLIP_FOR_CAR bike1_s4 bike1_s4blip
			CHANGE_BLIP_DISPLAY bike1_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike1_s4blip 2

			CREATE_CAR BF400 1487.213 -1734.792 3.25 bike2_s4
			SET_PETROL_TANK_WEAKPOINT bike2_s4 FALSE
			SET_CAR_HEADING bike2_s4 258.716
			SET_CAR_PROOFS bike2_s4 FALSE TRUE TRUE TRUE FALSE
			CREATE_CHAR_INSIDE_CAR bike2_s4 PEDTYPE_MISSION1 MAFFA russian3_s4
			SET_CHAR_DECISION_MAKER russian3_s4 smoke4_DM
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian3_s4 TRUE
			SET_CHAR_HEALTH russian3_s4 20
			CREATE_CHAR_AS_PASSENGER bike2_s4 PEDTYPE_MISSION1 MAFFA 0 russian4_s4
			GIVE_WEAPON_TO_CHAR russian4_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_DECISION_MAKER russian4_s4 smoke4_DM
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian4_s4 TRUE
			SET_CHAR_HEALTH russian4_s4 30
			bike2swap_s4flag = 1		
			bike2_s4flag = 1
			ADD_BLIP_FOR_CAR bike2_s4 bike2_s4blip
			CHANGE_BLIP_DISPLAY bike2_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike2_s4blip 2

			CREATE_CAR SULTAN 1496.864 -1731.303 6.3 car1_s4
			SET_PETROL_TANK_WEAKPOINT car1_s4 FALSE
			SET_CAR_HEADING car1_s4 257.724
			CREATE_CHAR_INSIDE_CAR car1_s4 PEDTYPE_MISSION1 MAFFA russian5_s4
			SET_CHAR_DECISION_MAKER russian5_s4 smoke4_DM
			SET_CHAR_CAN_BE_SHOT_IN_VEHICLE russian5_s4 FALSE
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian5_s4 TRUE
			CREATE_CHAR_AS_PASSENGER car1_s4 PEDTYPE_MISSION1 MAFFA 0 russian6_s4
			GIVE_WEAPON_TO_CHAR russian6_s4 WEAPONTYPE_MICRO_UZI 99999
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian6_s4 TRUE
			SET_CHAR_DECISION_MAKER russian6_s4 smoke4_DM
			SET_CHAR_HEALTH russian6_s4 10
			car1swap_s4flag = 1
			car1_s4flag = 1
			ADD_BLIP_FOR_CAR car1_s4 car1_s4blip
			CHANGE_BLIP_DISPLAY car1_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE car1_s4blip 2
			SET_CAR_HEALTH car1_s4 1000

			WAIT 0

			IF NOT IS_CAR_DEAD packer_s4
				START_PLAYBACK_RECORDED_CAR packer_s4 269	
			ENDIF
			IF NOT IS_CAR_DEAD packercar1_s4
				START_PLAYBACK_RECORDED_CAR packercar1_s4 270
			ENDIF
			IF NOT IS_CAR_DEAD packercar2_s4
				START_PLAYBACK_RECORDED_CAR packercar2_s4 271
			ENDIF
			IF NOT IS_CAR_DEAD smokebike_s4
				START_PLAYBACK_RECORDED_CAR smokebike_s4 272
			ENDIF
			IF NOT IS_CAR_DEAD packercar1_s4
				DETACH_CAR packercar1_s4 20.0 180.0 0.0 FALSE
			ENDIF
			IF NOT IS_CAR_DEAD packercar2_s4
				DETACH_CAR packercar2_s4 20.0 180.0 0.0 FALSE
			ENDIF
			IF NOT IS_CAR_DEAD bike1_s4
				START_PLAYBACK_RECORDED_CAR bike1_s4 273
			ENDIF
			IF NOT IS_CAR_DEAD bike2_s4
				START_PLAYBACK_RECORDED_CAR bike2_s4 274
			ENDIF
			IF NOT IS_CAR_DEAD car1_s4
				START_PLAYBACK_RECORDED_CAR car1_s4 275
			ENDIF

			TASK_DRIVE_BY scplayer -1 -1 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 100
			SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 346.2 359.0
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			IF NOT IS_CAR_DEAD smokebike_s4
				SET_CAR_PROOFS smokebike_s4 FALSE TRUE TRUE TRUE TRUE
			ENDIF
			DO_FADE 250 FADE_IN
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			truckaudio_s4flag = 0
			progressaudio_s4flag = 0
			handlingudio_s4flag = 0


			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
			IF NOT IS_CHAR_DEAD big_smoke
				SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE
			ENDIF

			TIMERB = 0
			IF NOT IS_CHAR_DEAD russian2_s4
				TASK_DRIVE_BY russian2_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 90
			ENDIF
			IF NOT IS_CHAR_DEAD russian1_s4
				IF difficulty_s4flag < 3
					GIVE_WEAPON_TO_CHAR russian1_s4 WEAPONTYPE_MICRO_UZI 99999
					TASK_DRIVE_BY russian1_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD russian3_s4
				IF difficulty_s4flag < 2
					GIVE_WEAPON_TO_CHAR russian3_s4 WEAPONTYPE_MICRO_UZI 99999
					TASK_DRIVE_BY russian3_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD russian4_s4
				TASK_DRIVE_BY russian4_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 90
			ENDIF
			IF NOT IS_CHAR_DEAD russian6_s4
				TASK_DRIVE_BY russian6_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 90
			ENDIF

			thirdchase_s4flag = 1
		ENDIF

		IF thirdchase_s4flag = 1
			IF LOCATE_CAR_2D smokebike_s4 1905.28 -1831.57 12.0 12.0 FALSE

				CREATE_CAR SULTAN 2002.102 -1890.368 8.108 car2_s4
				SET_PETROL_TANK_WEAKPOINT car2_s4 FALSE
				SET_CAR_HEADING car2_s4 44.1
				START_PLAYBACK_RECORDED_CAR car2_s4 276
				CREATE_CHAR_INSIDE_CAR car2_s4 PEDTYPE_MISSION1 MAFFA russian7_s4
				SET_CHAR_DECISION_MAKER russian7_s4 smoke4_DM
				SET_CHAR_CAN_BE_SHOT_IN_VEHICLE russian7_s4 FALSE
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian7_s4 TRUE
				CREATE_CHAR_AS_PASSENGER car2_s4 PEDTYPE_MISSION1 MAFFA 0 russian8_s4
				GIVE_WEAPON_TO_CHAR russian8_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian8_s4 TRUE
				SET_CHAR_DECISION_MAKER russian8_s4 smoke4_DM
				SET_CHAR_HEALTH russian8_s4 10
				car2swap_s4flag = 1
				car2_s4flag = 1
				ADD_BLIP_FOR_CAR car2_s4 car2_s4blip
				CHANGE_BLIP_DISPLAY car2_s4blip BLIP_ONLY
				CHANGE_BLIP_SCALE car2_s4blip 2
				SET_CAR_HEALTH car2_s4 1100
				SET_CAR_PROOFS car2_s4 FALSE TRUE FALSE FALSE FALSE

				CREATE_CAR SULTAN 2008.843 -1897.323 8.1 car3_s4
				SET_PETROL_TANK_WEAKPOINT car3_s4 FALSE
				SET_CAR_HEADING car3_s4 44.1
				START_PLAYBACK_RECORDED_CAR car3_s4 277
				SET_CAR_PROOFS car3_s4 FALSE TRUE TRUE TRUE FALSE
				SET_CAR_ONLY_DAMAGED_BY_PLAYER car3_s4 TRUE
				CREATE_CHAR_INSIDE_CAR car3_s4 PEDTYPE_MISSION1 MAFFA russian9_s4
				SET_CHAR_DECISION_MAKER russian9_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian9_s4 TRUE
				CREATE_CHAR_AS_PASSENGER car3_s4 PEDTYPE_MISSION1 MAFFA 0 russian10_s4
				GIVE_WEAPON_TO_CHAR russian10_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian10_s4 TRUE
				SET_CHAR_DECISION_MAKER russian10_s4 smoke4_DM
				SET_CHAR_HEALTH russian10_s4 10
				car3swap_s4flag = 1
				car3_s4flag = 1
				ADD_BLIP_FOR_CAR car3_s4 car3_s4blip
				CHANGE_BLIP_DISPLAY car3_s4blip BLIP_ONLY
				CHANGE_BLIP_SCALE car3_s4blip 2
				SET_CAR_HEALTH car3_s4 700

				IF NOT IS_CHAR_DEAD russian8_s4
					TASK_DRIVE_BY russian8_s4 -1 smokebike_s4 0.0 0.0 0.0 250.0 DRIVEBY_AI_ALL_DIRN TRUE 10
				ENDIF
				IF NOT IS_CHAR_DEAD russian10_s4
					TASK_DRIVE_BY russian10_s4 -1 smokebike_s4 0.0 0.0 0.0 250.0 DRIVEBY_AI_ALL_DIRN TRUE 10
				ENDIF
				thirdchase_s4flag = 2
			ENDIF
		ENDIF

		IF thirdchase_s4flag = 2
			IF LOCATE_CAR_2D smokebike_s4 2101.55 -1850.81 12.0 12.0 FALSE

				CREATE_CAR BF400 2093.446 -1836.207 12.72 bike4_s4
				SET_PETROL_TANK_WEAKPOINT bike4_s4 FALSE
				SET_CAR_HEADING bike4_s4 267.18
				SET_CAR_PROOFS bike4_s4 FALSE TRUE TRUE TRUE FALSE
				CREATE_CHAR_INSIDE_CAR bike4_s4 PEDTYPE_MISSION1 MAFFA russian11_s4
				SET_CHAR_HEALTH russian11_s4 50
				SET_CHAR_DECISION_MAKER russian11_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian11_s4 TRUE
				CREATE_CHAR_AS_PASSENGER bike4_s4 PEDTYPE_MISSION1 MAFFA 0 russian12_s4
				GIVE_WEAPON_TO_CHAR russian12_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_DECISION_MAKER russian12_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian12_s4 TRUE
				SET_CHAR_HEALTH russian12_s4 10
				bike4swap_s4flag = 1
				bike4_s4flag = 1
				ADD_BLIP_FOR_CAR bike4_s4 bike4_s4blip
				CHANGE_BLIP_DISPLAY bike4_s4blip BLIP_ONLY
				CHANGE_BLIP_SCALE bike4_s4blip 2

				START_PLAYBACK_RECORDED_CAR bike4_s4 279

				CREATE_CAR BF400 2018.735 -1900.925 8.108 bike3_s4
				SET_PETROL_TANK_WEAKPOINT bike3_s4 FALSE
				SET_CAR_HEADING bike3_s4 258.716
				SET_CAR_PROOFS bike3_s4 FALSE TRUE TRUE TRUE FALSE
				CREATE_CHAR_INSIDE_CAR bike3_s4 PEDTYPE_MISSION1 MAFFA russian13_s4
				SET_CHAR_DECISION_MAKER russian13_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian13_s4 TRUE
				CREATE_CHAR_AS_PASSENGER bike3_s4 PEDTYPE_MISSION1 MAFFA 0 russian14_s4
				GIVE_WEAPON_TO_CHAR russian14_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_DECISION_MAKER russian14_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian14_s4 TRUE
				SET_CHAR_HEALTH russian13_s4 10
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian13_s4 10
				SET_CHAR_HEALTH russian14_s4 30
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian14_s4 10
				bike3swap_s4flag = 1
				bike3_s4flag = 1
				ADD_BLIP_FOR_CAR bike3_s4 bike3_s4blip
				CHANGE_BLIP_DISPLAY bike3_s4blip BLIP_ONLY
				CHANGE_BLIP_SCALE bike3_s4blip 2

				START_PLAYBACK_RECORDED_CAR bike3_s4 278

				IF NOT IS_CHAR_DEAD russian11_s4
					IF difficulty_s4flag < 4
						GIVE_WEAPON_TO_CHAR russian11_s4 WEAPONTYPE_MICRO_UZI 99999
						TASK_DRIVE_BY russian11_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD russian13_s4
					IF difficulty_s4flag < 4
						GIVE_WEAPON_TO_CHAR russian13_s4 WEAPONTYPE_MICRO_UZI 99999
						TASK_DRIVE_BY russian13_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD russian12_s4
					TASK_DRIVE_BY russian12_s4 -1 smokebike_s4 0.0 0.0 0.0 250.0 DRIVEBY_AI_ALL_DIRN TRUE 100
				ENDIF
				IF NOT IS_CHAR_DEAD russian14_s4
					TASK_DRIVE_BY russian14_s4 -1 smokebike_s4 0.0 0.0 0.0 250.0 DRIVEBY_AI_ALL_DIRN TRUE 100
				ENDIF
				thirdchase_s4flag = 3
			ENDIF
		ENDIF

		IF thirdchase_s4flag = 3
			IF LOCATE_CAR_2D smokebike_s4 2425.93 -1874.76 10.0 10.0 FALSE

				DELETE_CAR packercar1_s4
				DELETE_CAR packercar2_s4
				DELETE_CAR bike1_s4
				DELETE_CAR bike2_s4
				DELETE_CHAR russian1_s4
				DELETE_CHAR russian2_s4
				DELETE_CHAR russian3_s4
				DELETE_CHAR russian4_s4
				DELETE_CAR car1_s4
				DELETE_CAR car2_s4
				DELETE_CHAR russian5_s4
				DELETE_CHAR russian6_s4
				DELETE_CHAR russian7_s4
				DELETE_CHAR russian8_s4

				CREATE_CAR BF400 2417.1 -1876.578 12.92 bike5_s4
				SET_PETROL_TANK_WEAKPOINT bike5_s4 FALSE
				SET_CAR_HEADING bike5_s4 271.4
				START_PLAYBACK_RECORDED_CAR bike5_s4 280
				SET_CAR_PROOFS bike5_s4 FALSE TRUE TRUE TRUE FALSE
				CREATE_CHAR_INSIDE_CAR bike5_s4 PEDTYPE_MISSION1 MAFFA russian15_s4
				SET_CHAR_DECISION_MAKER russian15_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian15_s4 TRUE
				CREATE_CHAR_AS_PASSENGER bike5_s4 PEDTYPE_MISSION1 MAFFA 0 russian16_s4
				GIVE_WEAPON_TO_CHAR russian16_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_DECISION_MAKER russian16_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian16_s4 TRUE
				SET_CHAR_HEALTH russian15_s4 10
				SET_CHAR_HEALTH russian16_s4 10
				bike5swap_s4flag = 1
				bike5_s4flag = 1
				ADD_BLIP_FOR_CAR bike5_s4 bike5_s4blip
				CHANGE_BLIP_DISPLAY bike5_s4blip BLIP_ONLY
				CHANGE_BLIP_SCALE bike5_s4blip 2
				IF NOT IS_CHAR_DEAD russian16_s4
					TASK_DRIVE_BY russian16_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 90
				ENDIF
				IF NOT IS_CHAR_DEAD russian15_s4
					GIVE_WEAPON_TO_CHAR russian15_s4 WEAPONTYPE_MICRO_UZI 99999
					TASK_DRIVE_BY russian15_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
				ENDIF

				thirdchase_s4flag = 4
			ENDIF
		ENDIF

		IF thirdchase_s4flag = 4
			IF LOCATE_CAR_2D smokebike_s4 2499.36 -1871.699 10.0 10.0 FALSE
				CREATE_CAR SULTAN 2529.256 -1892.9 13.24 car4_s4
				SET_PETROL_TANK_WEAKPOINT car4_s4 FALSE
				SET_CAR_HEADING car4_s4 269.177
				START_PLAYBACK_RECORDED_CAR	car4_s4 281
				SET_CAR_HEALTH car4_s4 600
				CREATE_CHAR_INSIDE_CAR car4_s4 PEDTYPE_MISSION1 MAFFA russian17_s4
				SET_CHAR_DECISION_MAKER russian17_s4 smoke4_DM
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian17_s4 TRUE
				CREATE_CHAR_AS_PASSENGER car4_s4 PEDTYPE_MISSION1 MAFFA 0 russian18_s4
				GIVE_WEAPON_TO_CHAR russian18_s4 WEAPONTYPE_MICRO_UZI 99999
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian18_s4 TRUE
				SET_CHAR_DECISION_MAKER russian18_s4 smoke4_DM
				car4swap_s4flag = 1
				car4_s4flag = 1
				ADD_BLIP_FOR_CAR car4_s4 car4_s4blip
				CHANGE_BLIP_DISPLAY car4_s4blip BLIP_ONLY
				CHANGE_BLIP_SCALE car4_s4blip 2
				SET_CAR_HEALTH car4_s4 800
				IF NOT IS_CHAR_DEAD russian18_s4
					TASK_DRIVE_BY russian18_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 90
				ENDIF

				thirdchase_s4flag = 5
			ENDIF
		ENDIF

		IF thirdchase_s4flag = 5
			IF NOT IS_CAR_DEAD packer_s4
				IF NOT IS_RECORDING_GOING_ON_FOR_CAR packer_s4
					FREEZE_CAR_POSITION packer_s4 TRUE
					thirdchase_s4flag = 6
				ENDIF
			ENDIF
		ENDIF
		
		//need to add
		IF thirdchase_s4flag = 6
			IF jumpchase_s4flag = 0
				IF firstchase_s4flag = 2
					jumpchase_s4flag = 1
					finalcut_s4flag = 1 //go to final bit of the chase
				ENDIF
			ENDIF
		ENDIF


		IF packersmash1_s4flag = 1
			IF NOT IS_CAR_DEAD car1_s4
				IF NOT IS_CAR_DEAD packercar1_s4
					IF TIMERB > 26250 //25851
						IF NOT car1swap_s4flag = 3
						OR NOT car1_s4flag = 2
							SET_CAR_PROOFS packercar1_s4 FALSE FALSE FALSE FALSE FALSE
							STOP_PLAYBACK_RECORDED_CAR packercar1_s4
							SET_CAR_HEALTH packercar1_s4 200
							SET_CAR_ROTATION_VELOCITY packercar1_s4 1.5 -2.3999 4.1003
							EXPLODE_CAR	packercar1_s4
							packersmash1_s4flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF packersmash1_s4flag = 2
			IF NOT IS_CAR_DEAD car1_s4 
				IF TIMERB > 26900
					EXPLODE_CAR	car1_s4
					packersmash1_s4flag = 3
				ENDIF
			ENDIF
		ENDIF

		IF packersmash2_s4flag = 1
			IF NOT IS_CAR_DEAD car2_s4
				IF NOT IS_CAR_DEAD packercar2_s4
					IF TIMERB > 35647//35100
						SET_CAR_PROOFS packercar2_s4 FALSE FALSE FALSE FALSE FALSE
						SET_CAR_HEALTH packercar2_s4 500
						packersmash2_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF packersmash2_s4flag = 2
			IF TIMERB > 35800
				IF NOT IS_CAR_DEAD packercar2_s4
					EXPLODE_CAR packercar2_s4
					packersmash2_s4flag = 3
				ENDIF
			ENDIF
		ENDIF
		IF packersmash2_s4flag = 3
			IF TIMERB > 36500
				IF NOT IS_CAR_DEAD car2_s4
					EXPLODE_CAR car2_s4
					packersmash2_s4flag = 4
				ENDIF
			ENDIF
		ENDIF

		//spin/stop bikes if the both guys are not in car
		IF bike1swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike1_s4
				IF NOT IS_CHAR_DEAD russian1_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian2_s4
						IF IS_CHAR_IN_CAR russian2_s4 bike1_s4
							GET_DRIVER_OF_CAR bike1_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian2_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian2_s4 bike1_s4
								TASK_DRIVE_BY russian2_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike1swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike1_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike1_s4
						SET_CAR_ROTATION_VELOCITY bike1_s4 3.5 7.3999 8.1003
						bike1swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike1swap_s4flag = 2
			IF IS_CHAR_DEAD russian2_s4
				STOP_PLAYBACK_RECORDED_CAR bike1_s4
				IF NOT IS_CAR_DEAD bike1_s4
					SET_CAR_FORWARD_SPEED bike1_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike1_s4 3.5 7.3999 8.1003
				ENDIF
				bike1swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike1_s4flag = 1
			IF IS_CHAR_DEAD russian1_s4
				IF IS_CHAR_DEAD russian2_s4
				OR IS_CAR_DEAD bike1_s4
					STOP_PLAYBACK_RECORDED_CAR bike1_s4
					REMOVE_BLIP bike1_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike1_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian1_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian2_s4
					bike1_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF bike2swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike2_s4
				IF NOT IS_CHAR_DEAD russian3_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian4_s4
						IF IS_CHAR_IN_CAR russian4_s4 bike2_s4
							GET_DRIVER_OF_CAR bike2_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian4_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian4_s4 bike2_s4
								TASK_DRIVE_BY russian4_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike2swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike2_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike2_s4
						SET_CAR_ROTATION_VELOCITY bike2_s4 3.5 7.3999 8.1003
						bike2swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike2swap_s4flag = 2
			IF IS_CHAR_DEAD russian4_s4
				STOP_PLAYBACK_RECORDED_CAR bike2_s4
				IF NOT IS_CAR_DEAD bike2_s4
					SET_CAR_FORWARD_SPEED bike2_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike2_s4 3.5 7.3999 8.1003
				ENDIF
				bike2swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike2_s4flag = 1
			IF IS_CHAR_DEAD russian3_s4
				IF IS_CHAR_DEAD russian4_s4
				OR IS_CAR_DEAD bike2_s4
					STOP_PLAYBACK_RECORDED_CAR bike2_s4
					REMOVE_BLIP bike2_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike2_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian3_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian4_s4
					bike2_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF car1swap_s4flag = 1
			IF NOT IS_CAR_DEAD car1_s4
				IF NOT IS_CHAR_DEAD russian5_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian6_s4
						IF IS_CHAR_IN_CAR russian6_s4 car1_s4
							GET_DRIVER_OF_CAR car1_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT russian6_s4 car1_s4
								car1swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR car1_s4
						car1swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF car1swap_s4flag = 2
			IF IS_CHAR_DEAD russian6_s4
				STOP_PLAYBACK_RECORDED_CAR car1_s4
				IF NOT IS_CAR_DEAD car1_s4
					CAR_SET_IDLE car1_s4
				ENDIF
				car1swap_s4flag = 3
			ENDIF 
		ENDIF
		IF car1_s4flag = 1
			IF IS_CHAR_DEAD russian5_s4
				IF IS_CHAR_DEAD russian6_s4
				OR IS_CAR_DEAD car1_s4
					STOP_PLAYBACK_RECORDED_CAR car1_s4
					IF NOT IS_CAR_DEAD car1_s4
						CAR_SET_IDLE car1_s4
					ENDIF
					REMOVE_BLIP car1_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED car1_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian5_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian6_s4
					car1_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF car2swap_s4flag = 1
			IF NOT IS_CAR_DEAD car2_s4
				IF NOT IS_CHAR_DEAD russian7_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian8_s4
						IF IS_CHAR_IN_CAR russian8_s4 car2_s4
							GET_DRIVER_OF_CAR car2_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT russian8_s4 car2_s4
								car2swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR car2_s4
						car2swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF car2swap_s4flag = 2
			IF IS_CHAR_DEAD russian8_s4
				STOP_PLAYBACK_RECORDED_CAR car2_s4
				IF NOT IS_CAR_DEAD car2_s4
					CAR_SET_IDLE car2_s4
				ENDIF
				car2swap_s4flag = 3
			ENDIF 
		ENDIF
		IF car2_s4flag = 1
			IF IS_CHAR_DEAD russian7_s4
				IF IS_CHAR_DEAD russian8_s4
				OR IS_CAR_DEAD car2_s4
					STOP_PLAYBACK_RECORDED_CAR car2_s4
					IF NOT IS_CAR_DEAD car2_s4
						CAR_SET_IDLE car2_s4
					ENDIF
					REMOVE_BLIP car2_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED car2_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian7_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian8_s4
					car2_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF car3swap_s4flag = 1
			IF NOT IS_CAR_DEAD car3_s4
				IF NOT IS_CHAR_DEAD russian9_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian10_s4
						IF IS_CHAR_IN_CAR russian10_s4 car3_s4
							GET_DRIVER_OF_CAR car3_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT russian10_s4 car3_s4
								car3swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR car3_s4
						car3swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF car3swap_s4flag = 2
			IF IS_CHAR_DEAD russian10_s4
				STOP_PLAYBACK_RECORDED_CAR car3_s4
					IF NOT IS_CAR_DEAD car3_s4
						CAR_SET_IDLE car3_s4
					ENDIF
				car3swap_s4flag = 3
			ENDIF 
		ENDIF
		IF car3_s4flag = 1
			IF IS_CHAR_DEAD russian9_s4
				IF IS_CHAR_DEAD russian10_s4
				OR IS_CAR_DEAD car3_s4
					STOP_PLAYBACK_RECORDED_CAR car3_s4
					REMOVE_BLIP car3_s4blip
					IF NOT IS_CAR_DEAD car3_s4
						CAR_SET_IDLE car3_s4
					ENDIF
					MARK_CAR_AS_NO_LONGER_NEEDED car3_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian9_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian10_s4
					car3_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF bike4swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike4_s4
				IF NOT IS_CHAR_DEAD russian11_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian12_s4
						IF IS_CHAR_IN_CAR russian12_s4 bike4_s4
							GET_DRIVER_OF_CAR bike4_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian12_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian12_s4 bike4_s4
								TASK_DRIVE_BY russian12_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike4swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike4_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike4_s4
						SET_CAR_ROTATION_VELOCITY bike4_s4 3.5 7.3999 8.1003
						bike4swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike4swap_s4flag = 2
			IF IS_CHAR_DEAD russian12_s4
				STOP_PLAYBACK_RECORDED_CAR bike4_s4
				IF NOT IS_CAR_DEAD bike4_s4
					SET_CAR_FORWARD_SPEED bike4_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike4_s4 3.5 7.3999 8.1003
				ENDIF
				bike4swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike4_s4flag = 1
			IF IS_CHAR_DEAD russian11_s4
				IF IS_CHAR_DEAD russian12_s4
				OR IS_CAR_DEAD bike4_s4
					STOP_PLAYBACK_RECORDED_CAR bike4_s4
					REMOVE_BLIP bike4_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike4_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian11_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian12_s4
					bike4_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF bike3swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike3_s4
				IF NOT IS_CHAR_DEAD russian13_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian14_s4
						IF IS_CHAR_IN_CAR russian14_s4 bike3_s4
							GET_DRIVER_OF_CAR bike3_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian14_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian14_s4 bike3_s4
								TASK_DRIVE_BY russian14_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike3swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike3_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike3_s4
						SET_CAR_ROTATION_VELOCITY bike3_s4 3.5 7.3999 8.1003
						bike3swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike3swap_s4flag = 2
			IF IS_CHAR_DEAD russian14_s4
				STOP_PLAYBACK_RECORDED_CAR bike3_s4
				IF NOT IS_CAR_DEAD bike3_s4
					SET_CAR_FORWARD_SPEED bike3_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike3_s4 3.5 7.3999 8.1003
				ENDIF
				bike3swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike3_s4flag = 1
			IF IS_CHAR_DEAD russian13_s4
				IF IS_CHAR_DEAD russian14_s4
				OR IS_CAR_DEAD bike3_s4
					STOP_PLAYBACK_RECORDED_CAR bike3_s4
					REMOVE_BLIP bike3_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike3_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian13_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian14_s4
					bike3_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF bike5swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike5_s4
				IF NOT IS_CHAR_DEAD russian15_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian16_s4
						IF IS_CHAR_IN_CAR russian16_s4 bike5_s4
							GET_DRIVER_OF_CAR bike5_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian16_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian16_s4 bike5_s4
								TASK_DRIVE_BY russian16_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike5swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike5_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike5_s4
						SET_CAR_ROTATION_VELOCITY bike5_s4 3.5 7.3999 8.1003
						bike5swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike5swap_s4flag = 2
			IF IS_CHAR_DEAD russian16_s4
				STOP_PLAYBACK_RECORDED_CAR bike5_s4
				IF NOT IS_CAR_DEAD bike5_s4
					SET_CAR_FORWARD_SPEED bike5_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike5_s4 3.5 7.3999 8.1003
				ENDIF
				bike5swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike5_s4flag = 1
			IF IS_CHAR_DEAD russian15_s4
				IF IS_CHAR_DEAD russian16_s4
				OR IS_CAR_DEAD bike5_s4
					STOP_PLAYBACK_RECORDED_CAR bike5_s4
					REMOVE_BLIP bike5_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike5_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian15_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian16_s4
					bike5_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF car4swap_s4flag = 1
			IF NOT IS_CAR_DEAD car4_s4
				IF NOT IS_CHAR_DEAD russian17_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian18_s4
						IF IS_CHAR_IN_CAR russian18_s4 car4_s4
							GET_DRIVER_OF_CAR car4_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								TASK_SHUFFLE_TO_NEXT_CAR_SEAT russian18_s4 car4_s4
								car4swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						STOP_PLAYBACK_RECORDED_CAR car4_s4
						car4swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF car4swap_s4flag = 2
			IF IS_CHAR_DEAD russian18_s4
				STOP_PLAYBACK_RECORDED_CAR car4_s4
					IF NOT IS_CAR_DEAD car4_s4
						CAR_SET_IDLE car4_s4
					ENDIF
				car4swap_s4flag = 3
			ENDIF 
		ENDIF
		IF car4_s4flag = 1
			IF IS_CHAR_DEAD russian17_s4
				IF IS_CHAR_DEAD russian18_s4
				OR IS_CAR_DEAD car4_s4
					STOP_PLAYBACK_RECORDED_CAR car4_s4
					IF NOT IS_CAR_DEAD car4_s4
						CAR_SET_IDLE car4_s4
					ENDIF
					REMOVE_BLIP car4_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED car4_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian17_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian18_s4
					car4_s4flag = 2
				ENDIF
			ENDIF
		ENDIF


		//audio
		IF thirdchase_s4flag > 0

			IF progressaudio_s4flag = 0
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HA	//Oh shit, now the truck’s found us again!
					$input_text_s4 = SMO4_HA	//They after us on a bike, Smoke!!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 1
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HB	//Man, you’re so negative!
					$input_text_s4 = SMO4_HB	//Man, you’re so negative!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 2
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HC	//Focus on the good news!
					$input_text_s4 = SMO4_HC	//Focus on the good news!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 3
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HD	//Such as?
					$input_text_s4 = SMO4_HD	//Such as?
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 4
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HE	//We ain’t dead and your trigger finger still works!
					$input_text_s4 = SMO4_HE	//Waste any motherfucker that follows us!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 5
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HF	//I think the gearbox is screwed on this thing!!
					$input_text_s4 = SMO4_HF	//I think the gearbox is screwed on this thing!!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 6
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HH	//Yeah, who negative now, bitch?
					$input_text_s4 = SMO4_HH	//Yeah, who negative now, bitch?
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 7
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HJ	//Point taken. I’ll shut up!
					$input_text_s4 = SMO4_HJ	//Point taken. I’ll shut up!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 8
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_ER	//Watch our backs!
					$input_text_s4 = SMO4_ER	//Watch our backs!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			//first car falling off
			IF truckaudio_s4flag = 0
				IF LOCATE_CAR_2D smokebike_s4 2047.59 -1850.32 12.0 12.0 FALSE
					truckaudio_s4flag = 1
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 9
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 1
						audio_label_s4 = SOUND_SMO4_HN //OH SHIT!
						$input_text_s4 = SMO4_HN //OH SHIT!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF
			
			//slip road
			IF truckaudio_s4flag = 1
				IF LOCATE_CAR_2D smokebike_s4 2121.17 -1853.67 12.0 12.0 FALSE
					truckaudio_s4flag = 2
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 10
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 2
						audio_label_s4 = SOUND_SMO4_GM	//Oh man, the cars found a ramp!
						$input_text_s4 = SMO4_GM	//Oh man, the cars found a ramp!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 11
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GP //Don’t tell me about it – shoot!
					$input_text_s4 = SMO4_GP //Don’t tell me about it – shoot!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			//second car
			IF truckaudio_s4flag = 2
				IF LOCATE_CAR_2D smokebike_s4 2216.75 -1854.73 12.0 12.0 FALSE
					truckaudio_s4flag = 3
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 12
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 3
						audio_label_s4 = SOUND_SMO4_HP	//Hold on!
						$input_text_s4 = SMO4_HP	//Hold on!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 13
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HS	//Get us up that ramp!
					$input_text_s4 = SMO4_HS	//Get us up that ramp!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 14
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_HT	//I’m on it!
					$input_text_s4 = SMO4_HT	//I’m on it!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			//bike off bridge
			IF truckaudio_s4flag = 3
				IF LOCATE_CAR_2D smokebike_s4 2435.94 -1874.57 12.0 12.0 FALSE
					truckaudio_s4flag = 4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 15
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 4
						audio_label_s4 = SOUND_SMO4_GL //We got more bikes on us, man!
						$input_text_s4 = SMO4_GL //We got more bikes on us, man!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 16
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_GN	//Quit whining and shoot as many assholes as you can!
					$input_text_s4 = SMO4_GN	//Quit whining and shoot as many assholes as you can!
					GOSUB load_audio_s4
				ENDIF
			ENDIF

			//talking about the truck
			IF truckaudio_s4flag = 4
				IF LOCATE_CAR_2D smokebike_s4 2539.11 -1865.31 12.0 12.0 FALSE
					truckaudio_s4flag = 5
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 17
				IF handlingudio_s4flag = 0
					IF truckaudio_s4flag = 4
						audio_label_s4 = SOUND_SMO4_JA //Fools totalled their truck!
						$input_text_s4 = SMO4_JA //Fools totalled their truck!!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 18
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_JB //Go around ‘em, man!
					$input_text_s4 = SMO4_JB //Go around ‘em, man!
					GOSUB load_audio_s4
				ENDIF
			ENDIF
			IF progressaudio_s4flag = 19
				IF handlingudio_s4flag = 0
					audio_label_s4 = SOUND_SMO4_JC //Screw that, We’re taking the scenic route
					$input_text_s4 = SMO4_JC //Screw that, We’re taking the scenic route
					GOSUB load_audio_s4
				ENDIF
			ENDIF


			//audio
			GOSUB process_audio_s4

		ENDIF

	ENDIF					
ENDIF

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////	  JUMPING OVER PACKER CUT SCENE ////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF jumpchase_s4flag = 1
	IF NOT IS_CAR_DEAD smokebike_s4

		IF finalcut_s4flag = 1
			IF LOCATE_CAR_2D smokebike_s4 2565.98 -1747.12 12.0 12.0 FALSE
				DO_FADE 0 FADE_OUT

				//request car recordings
				REQUEST_CAR_RECORDING 282 //player bike
				REQUEST_CAR_RECORDING 283 //bike1
				REQUEST_CAR_RECORDING 284 //bike2
				REQUEST_CAR_RECORDING 285 //bike3
				REQUEST_CAR_RECORDING 286 //bike4
				REQUEST_MODEL storm_drain_cover
				REQUEST_MODEL barrel4


				SWITCH_WIDESCREEN ON
				SET_PLAYER_CONTROL PLAYER1 OFF
				SET_CAR_PROOFS smokebike_s4 TRUE TRUE TRUE TRUE TRUE
				IF NOT IS_CHAR_DEAD big_smoke
					SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE
				ENDIF
				SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE

				IF NOT IS_CAR_DEAD packer_s4
					OPEN_CAR_DOOR packer_s4 FRONT_LEFT_DOOR
				ENDIF

				cam_pos_X_s4 = 2569.1440  
				cam_pos_Y_s4 = -1676.1851
				cam_pos_Z_s4 = 2.9150

				cam_look_X_s4 =	2569.5117
				cam_look_Y_s4 =	-1677.1125
				cam_look_Z_s4 =	2.8487
				//initial shot
				SET_FIXED_CAMERA_POSITION 2578.4055 -1668.3281 1.3204 0.0 0.0 0.0 //cam_pos_X_s4 cam_pos_Y_s4 cam_pos_Z_s4
				POINT_CAMERA_AT_POINT 2577.8525 -1669.1570 1.4051 JUMP_CUT //cam_look_X_s4 cam_look_Y_s4 cam_look_Z_s4

				STOP_PLAYBACK_RECORDED_CAR bike1_s4
				DELETE_CAR bike1_s4
				DELETE_CHAR russian1_s4
				DELETE_CHAR russian2_s4

				STOP_PLAYBACK_RECORDED_CAR bike2_s4
				DELETE_CAR bike2_s4
				DELETE_CHAR russian3_s4
				DELETE_CHAR russian4_s4

				STOP_PLAYBACK_RECORDED_CAR car1_s4
				DELETE_CAR car1_s4
				DELETE_CHAR russian5_s4
				DELETE_CHAR russian6_s4

				STOP_PLAYBACK_RECORDED_CAR car2_s4
				DELETE_CAR car2_s4
				DELETE_CHAR russian7_s4
				DELETE_CHAR russian8_s4

				STOP_PLAYBACK_RECORDED_CAR car3_s4
				DELETE_CAR car3_s4
				DELETE_CHAR russian9_s4
				DELETE_CHAR russian10_s4

				STOP_PLAYBACK_RECORDED_CAR bike4_s4
				DELETE_CAR bike4_s4
				DELETE_CHAR russian11_s4
				DELETE_CHAR russian12_s4

				STOP_PLAYBACK_RECORDED_CAR bike3_s4
				DELETE_CAR bike3_s4
				DELETE_CHAR russian13_s4
				DELETE_CHAR russian14_s4

				STOP_PLAYBACK_RECORDED_CAR bike5_s4
				DELETE_CAR bike5_s4
				DELETE_CHAR russian15_s4
				DELETE_CHAR russian16_s4

				STOP_PLAYBACK_RECORDED_CAR car4_s4
				DELETE_CAR car4_s4
				DELETE_CHAR russian17_s4
				DELETE_CHAR russian18_s4

				CREATE_CAR SULTAN 2575.8711 -1676.5568 0.7246 car1_s4
				SET_CAR_HEADING car1_s4 85.0235 //carleft
				SET_CAR_PROOFS car1_s4 TRUE FALSE FALSE FALSE FALSE

				CREATE_CAR SULTAN 2567.3477 -1675.9116 0.7398 car2_s4
				SET_CAR_HEADING car2_s4 263.7667 //carright
				SET_CAR_PROOFS car2_s4 TRUE FALSE FALSE FALSE FALSE

				CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2578.0220 -1674.7611 0.7667 people1_s4
				SET_CHAR_HEADING people1_s4 162.2371
				SET_CHAR_DECISION_MAKER people1_s4 smoke4_DM
				GIVE_WEAPON_TO_CHAR people1_s4 WEAPONTYPE_MICRO_UZI 9999
				TASK_SHOOT_AT_COORD people1_s4 2574.87 -1686.48 2.02 3000

				CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2574.3364 -1674.4777 0.7734 people2_s4
				SET_CHAR_HEADING people2_s4 162.2371
				SET_CHAR_DECISION_MAKER people2_s4 smoke4_DM
				GIVE_WEAPON_TO_CHAR people2_s4 WEAPONTYPE_MICRO_UZI 9999
				TASK_SHOOT_AT_COORD people2_s4 2572.21 -1686.56 3.13 3000
				TASK_TOGGLE_DUCK people2_s4 TRUE

				CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2569.2949 -1674.3998 0.7752 people3_s4
				SET_CHAR_HEADING people3_s4 170.9985
				SET_CHAR_DECISION_MAKER people3_s4 smoke4_DM
				GIVE_WEAPON_TO_CHAR people3_s4 WEAPONTYPE_MICRO_UZI 9999
				TASK_SHOOT_AT_COORD people3_s4 2566.95 -1686.14 2.5 3000

				CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2565.3142 -1673.9213 0.7864 people4_s4
				SET_CHAR_HEADING people4_s4 170.9985
				SET_CHAR_DECISION_MAKER people4_s4 smoke4_DM
				GIVE_WEAPON_TO_CHAR people4_s4 WEAPONTYPE_MICRO_UZI 9999
				TASK_TOGGLE_DUCK people4_s4 TRUE
				TASK_SHOOT_AT_COORD people4_s4 2564.35 -1786.51 2.18 3000

				IF IS_PLAYBACK_GOING_ON_FOR_CAR smokebike_s4
					STOP_PLAYBACK_RECORDED_CAR smokebike_s4
					//WAIT 0
				ENDIF

				firstchase_s4flag = 3
				finalcut_s4flag = 2
			ENDIF
		ENDIF

		IF finalcut_s4flag = 2
			IF HAS_CAR_RECORDING_BEEN_LOADED 282
				IF HAS_CAR_RECORDING_BEEN_LOADED 283
					IF HAS_CAR_RECORDING_BEEN_LOADED 284
						IF HAS_CAR_RECORDING_BEEN_LOADED 285
							IF HAS_CAR_RECORDING_BEEN_LOADED 286
								IF HAS_MODEL_LOADED storm_drain_cover
									IF HAS_MODEL_LOADED barrel4
										finalcut_s4flag = 3
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SWITCH finalcut_s4flag

			CASE 3
				IF NOT IS_CAR_DEAD smokebike_s4
					TIMERA = 0
					START_PLAYBACK_RECORDED_CAR smokebike_s4 282
					IF NOT IS_CAR_DEAD packer_s4
						SET_CAR_HEALTH packer_s4 300
					ENDIF
					z = 1.0
					finalcut_s4flag = 7
					

					
					IF NOT IS_CAR_DEAD smokebike_s4
						//SET_NEAR_CLIP 0.1
					ENDIF

				ENDIF

			BREAK 

			CASE 7
				//IF TIMERA > 1600
					finalcut_s4flag = 8
					DO_FADE 250 FADE_IN
//					SWITCH_WIDESCREEN ON
					TIMERA = 0
					TIMERB = 0 
					timescale_status_s4 = 0
					camera_pos_status_s4 = 0
					camera_look_status_s4 = 0

				//ENDIF
			BREAK

			CASE 8
				GOSUB mission_smoke4_CUT_timescale
				GOSUB mission_smoke4_CUT_camera_position
				GOSUB mission_smoke4_CUT_camera_lookat

				IF TIMERA > 1800//1200
//					write_debug setting_camera
					IF playershoot_s4flag = 0
						playershoot_s4flag = 1
					ENDIF
					SET_FIXED_CAMERA_POSITION cam_pos_X_s4 cam_pos_Y_s4 cam_pos_Z_s4 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT cam_look_X_s4 cam_look_Y_s4 cam_look_Z_s4 JUMP_CUT
				ENDIF

			BREAK

//			CASE 9
//				cam_pos_X_s4 -= 0.1
//				cam_pos_Y_s4 += 0.7
//				SET_FIXED_CAMERA_POSITION cam_pos_X_s4 cam_pos_Y_s4 cam_pos_Z_s4 0.0 0.0 0.0
//				 
//				GET_CAR_COORDINATES smokebike_s4 cam_look_X_s4 cam_look_Y_s4 cam_look_Z_s4
//				POINT_CAMERA_AT_POINT cam_look_X_s4 cam_look_Y_s4 cam_look_Z_s4 JUMP_CUT
//			BREAK


		ENDSWITCH
			
		IF playershoot_s4flag = 1
			IF NOT IS_CHAR_DEAD people1_s4
				enemy_s4 = people1_s4
				enemytarget_s4 = scplayer
				GOSUB stayshoot_s4label
			ENDIF
			IF NOT IS_CHAR_DEAD people2_s4
				enemy_s4 = people2_s4
				enemytarget_s4 = scplayer
				GOSUB stayshootnoduck_s4label
			ENDIF

			IF NOT IS_CHAR_DEAD people3_s4
				enemy_s4 = people3_s4
				enemytarget_s4 = scplayer
				GOSUB stayshoot_s4label
			ENDIF

			IF NOT IS_CHAR_DEAD people4_s4
				enemy_s4 = people4_s4
				enemytarget_s4 = scplayer
				GOSUB stayshootnoduck_s4label
			ENDIF
			playershoot_s4flag = 2
		ENDIF

		IF playershoot_s4flag = 2
			IF TIMERB > 3400
				playershoot_s4flag = 3
			ENDIF
		ENDIF

		IF playershoot_s4flag = 3
			IF TIMERB > 3480
				IF NOT IS_CAR_DEAD packer_s4
					FREEZE_CAR_POSITION packer_s4 FALSE
					EXPLODE_CAR packer_s4
					ADD_EXPLOSION 2570.2278 -1684.9486 2.2595 EXPLOSION_GRENADE
					ADD_EXPLOSION 2570.2278 -1684.9486 2.2595 EXPLOSION_MOLOTOV
					playershoot_s4flag = 4
				ENDIF
			ENDIF
		ENDIF

		IF playershoot_s4flag = 4
			IF TIMERB > 3550
				IF NOT IS_CAR_DEAD car1_s4
					EXPLODE_CAR car1_s4
					ADD_EXPLOSION 2575.2690 -1677.0764 1.0414 EXPLOSION_MOLOTOV
				ENDIF
				playershoot_s4flag = 5
			ENDIF
		ENDIF

		IF playershoot_s4flag = 5
			IF NOT IS_CAR_DEAD car2_s4
				IF TIMERB > 3625
					EXPLODE_CAR car2_s4
					ADD_EXPLOSION 2568.7490 -1676.1389 1.2090 EXPLOSION_ROCKET
					playershoot_s4flag = 6
				ENDIF
			ENDIF
		ENDIF


		IF finalcut_s4flag > 7
			IF TIMERB > 4599
				TASK_DRIVE_BY scplayer -1 -1 0.0 0.0 0.0 300.0 DRIVEBY_AI_ALL_DIRN TRUE 100
				jumpchase_s4flag = 2
				finalcut_s4flag = 10
			ENDIF
		ENDIF

	ENDIF
ENDIF
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////	  FINAL PART OF CHASE  /////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF jumpchase_s4flag = 2
	IF NOT IS_CAR_DEAD smokebike_s4

		IF finalchase_s4flag = 0
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			SET_HEADING_FOR_ATTACHED_PLAYER PLAYER1 263.0 350.0 //want 353.0
			RESTORE_CAMERA_JUMPCUT
			SET_CAR_PROOFS smokebike_s4 FALSE TRUE TRUE TRUE TRUE
			IF NOT IS_CHAR_DEAD big_smoke
				SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE
			ENDIF
			SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
			CREATE_OBJECT_NO_OFFSET storm_drain_cover 2631.852 -1482.75 18.109 grate_s4
			grate_s4flag = 1
			progressaudio_s4flag = 0
			handlingudio_s4flag = 0
			truckaudio_s4flag = 0
			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_SMO4_JE //Fry, motherfuckers!
			SET_OBJECT_HEALTH grate_s4 150
			CREATE_OBJECT barrel4 2786.521 -1416.051 15.31 barrel1_s4
			CREATE_OBJECT barrel4 2787.548 -1418.3228 15.31 barrel2_s4 
			CREATE_OBJECT barrel4 2824.324 -1418.704 15.31 barrel3_s4
			CREATE_OBJECT barrel4 2822.16 -1419.988 15.31 barrel4_s4
			CREATE_OBJECT barrel4 2823.05 -1418.747 15.3 barrel5_s4
			MARK_MODEL_AS_NO_LONGER_NEEDED PACKER

			CREATE_CAR SULTAN 2601.6887 -1600.1074 3.1106 car4_s4
			SET_CAR_HEADING car4_s4 61.4469 
			car4_s4flag = 1
			CREATE_CAR SULTAN 2595.7173 -1558.2898 8.4514 car5_s4
			SET_CAR_HEADING car5_s4 287.8971 
			car5_s4flag = 1
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2601.4409 -1597.4613 3.2207 russian7_s4 
			SET_CHAR_HEADING russian7_s4 135.3988 
			SET_CHAR_HEALTH russian7_s4 20
			SET_CHAR_DECISION_MAKER russian7_s4 smoke4_DM 
			GIVE_WEAPON_TO_CHAR russian7_s4 WEAPONTYPE_MICRO_UZI 3000
			ADD_BLIP_FOR_CHAR russian7_s4 russian7_s4blip
			CHANGE_BLIP_DISPLAY russian7_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE russian7_s4blip 2

			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2596.7649 -1559.5886 8.2770 russian8_s4 
			SET_CHAR_HEADING russian8_s4 201.1592 
			SET_CHAR_HEALTH russian8_s4 20
			SET_CHAR_DECISION_MAKER russian8_s4 smoke4_DM 
			GIVE_WEAPON_TO_CHAR russian8_s4 WEAPONTYPE_MICRO_UZI 3000
			ADD_BLIP_FOR_CHAR russian8_s4 russian8_s4blip
			CHANGE_BLIP_DISPLAY russian8_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE russian8_s4blip 2

			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2596.9414 -1555.8549 8.9717 russian9_s4
			SET_CHAR_HEADING russian9_s4 201.1592 //d
			SET_CHAR_HEALTH russian9_s4 20
			SET_CHAR_DECISION_MAKER russian9_s4 smoke4_DM 
			GIVE_WEAPON_TO_CHAR russian9_s4 WEAPONTYPE_MICRO_UZI 3000
			ADD_BLIP_FOR_CHAR russian9_s4 russian9_s4blip
			CHANGE_BLIP_DISPLAY russian9_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE russian9_s4blip 2

			//guys at front of tunnel
			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2624.8191 -1494.0024 15.4086 russian10_s4 
			SET_CHAR_HEADING russian10_s4 145.1087
			SET_CHAR_HEALTH russian10_s4 10
			SET_CHAR_DECISION_MAKER russian10_s4 smoke4_DM
			GIVE_WEAPON_TO_CHAR russian10_s4 WEAPONTYPE_MICRO_UZI 3000
			ADD_BLIP_FOR_CHAR russian10_s4 russian10_s4blip
			CHANGE_BLIP_DISPLAY russian10_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE russian10_s4blip 2

			CREATE_CHAR PEDTYPE_MISSION1 MAFFA 2625.4133 -1496.5874 15.3563 russian12_s4 
			SET_CHAR_HEADING russian12_s4 153.8693
			SET_CHAR_HEALTH russian12_s4 10
			SET_CHAR_DECISION_MAKER russian12_s4 smoke4_DM 
			GIVE_WEAPON_TO_CHAR russian12_s4 WEAPONTYPE_MICRO_UZI 3000
			ADD_BLIP_FOR_CHAR russian12_s4 russian12_s4blip
			CHANGE_BLIP_DISPLAY russian12_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE russian12_s4blip 2

			CREATE_CAR BF400 2539.3440 -1712.7698 12.4719 bike1_s4
			SET_CAR_HEADING bike1_s4 287.1995 
			CREATE_CHAR_INSIDE_CAR bike1_s4 PEDTYPE_MISSION1 MAFFA russian1_s4
			SET_CHAR_DECISION_MAKER russian1_s4 smoke4_DM
			CREATE_CHAR_AS_PASSENGER bike1_s4 PEDTYPE_MISSION1 MAFFA 0 russian2_s4
			GIVE_WEAPON_TO_CHAR russian2_s4 WEAPONTYPE_MICRO_UZI 9999
			SET_CHAR_DECISION_MAKER russian2_s4 smoke4_DM
			ADD_BLIP_FOR_CAR bike1_s4 bike1_s4blip
			CHANGE_BLIP_DISPLAY bike1_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike1_s4blip 2
			SET_CHAR_HEALTH russian1_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian1_s4 10
			SET_CHAR_HEALTH russian2_s4 10
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian2_s4 10
			bike1swap_s4flag = 1
			bike1_s4flag = 1

			CREATE_CAR BF400 2533.5684 -1714.5688 12.4874 bike2_s4
			SET_PETROL_TANK_WEAKPOINT bike2_s4 FALSE
			SET_CAR_HEADING bike2_s4 287.5564
			CREATE_CHAR_INSIDE_CAR bike2_s4 PEDTYPE_MISSION1 MAFFA russian3_s4
			SET_CHAR_DECISION_MAKER russian3_s4 smoke4_DM
			CREATE_CHAR_AS_PASSENGER bike2_s4 PEDTYPE_MISSION1 MAFFA 0 russian4_s4
			GIVE_WEAPON_TO_CHAR russian4_s4 WEAPONTYPE_MICRO_UZI 9999
			SET_CHAR_DECISION_MAKER russian4_s4 smoke4_DM
			ADD_BLIP_FOR_CAR bike2_s4 bike2_s4blip
			CHANGE_BLIP_DISPLAY bike2_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike2_s4blip 2
			SET_CHAR_HEALTH russian3_s4 50
			SET_CHAR_HEALTH russian4_s4 50
			bike2swap_s4flag = 1
			bike2_s4flag = 1

			CREATE_CAR BF400 2529.6826 -1715.8032 12.4991 bike3_s4
			SET_PETROL_TANK_WEAKPOINT bike3_s4 FALSE
			SET_CAR_HEADING bike3_s4 287.7722
			CREATE_CHAR_INSIDE_CAR bike3_s4 PEDTYPE_MISSION1 MAFFA russian5_s4
			SET_CHAR_DECISION_MAKER russian5_s4 smoke4_DM
			CREATE_CHAR_AS_PASSENGER bike3_s4 PEDTYPE_MISSION1 MAFFA 0 russian6_s4
			GIVE_WEAPON_TO_CHAR russian6_s4 WEAPONTYPE_MICRO_UZI 9999
			SET_CHAR_DECISION_MAKER russian6_s4 smoke4_DM
			ADD_BLIP_FOR_CAR bike3_s4 bike3_s4blip
			CHANGE_BLIP_DISPLAY bike3_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike3_s4blip 2
			SET_CHAR_HEALTH russian5_s4 30
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian5_s4 10
			SET_CHAR_HEALTH russian6_s4 30
			SET_CHAR_ONLY_DAMAGED_BY_PLAYER russian6_s4 10
			bike3swap_s4flag = 1
			bike3_s4flag = 1

			CREATE_CAR BF400 2524.5420 -1717.4609 12.5175 bike4_s4
			SET_PETROL_TANK_WEAKPOINT bike4_s4 FALSE
			SET_CAR_HEADING bike4_s4 287.8176
			CREATE_CHAR_INSIDE_CAR bike4_s4 PEDTYPE_MISSION1 MAFFA russian15_s4
			SET_CHAR_DECISION_MAKER russian15_s4 smoke4_DM
			CREATE_CHAR_AS_PASSENGER bike4_s4 PEDTYPE_MISSION1 MAFFA 0 russian16_s4
			GIVE_WEAPON_TO_CHAR russian16_s4 WEAPONTYPE_MICRO_UZI 9999
			SET_CHAR_DECISION_MAKER russian16_s4 smoke4_DM
			ADD_BLIP_FOR_CAR bike4_s4 bike4_s4blip
			CHANGE_BLIP_DISPLAY bike4_s4blip BLIP_ONLY
			CHANGE_BLIP_SCALE bike4_s4blip 2
			bike4swap_s4flag = 1
			bike4_s4flag = 1

			IF NOT IS_CHAR_DEAD russian7_s4
				enemy_s4 = russian7_s4
				enemytarget_s4 = scplayer
				GOSUB stayshoot_s4label
			ENDIF
			IF NOT IS_CHAR_DEAD russian8_s4
				enemy_s4 = russian8_s4
				enemytarget_s4 = scplayer
				GOSUB stayshootnoduck_s4label
			ENDIF
			IF NOT IS_CHAR_DEAD russian9_s4
				enemy_s4 = russian9_s4
				enemytarget_s4 = scplayer
				GOSUB stayshoot_s4label
			ENDIF
			IF NOT IS_CHAR_DEAD russian10_s4
				enemy_s4 = russian10_s4
				enemytarget_s4 = scplayer
				GOSUB stayshootnoduck_s4label
			ENDIF
			IF NOT IS_CHAR_DEAD russian12_s4
				enemy_s4 = russian12_s4
				enemytarget_s4 = scplayer
				GOSUB stayshootnoduck_s4label
			ENDIF

			finalchase_s4flag = 1
		ENDIF


		IF finalchase_s4flag = 1
			IF LOCATE_CAR_2D smokebike_s4 2580.74 -1621.47 15.0 15.0 FALSE
				
				IF NOT IS_CAR_DEAD bike1_s4
					START_PLAYBACK_RECORDED_CAR bike1_s4 283
				ENDIF
				IF NOT IS_CAR_DEAD bike2_s4
					START_PLAYBACK_RECORDED_CAR bike2_s4 284
				ENDIF
				IF NOT IS_CAR_DEAD bike3_s4
					START_PLAYBACK_RECORDED_CAR bike3_s4 285
				ENDIF
				IF NOT IS_CAR_DEAD bike4_s4
					START_PLAYBACK_RECORDED_CAR bike4_s4 286
				ENDIF
				IF NOT IS_CHAR_DEAD russian2_s4
					TASK_DRIVE_BY russian2_s4 -1 smokebike_s4 0.0 0.0 0.0 200.0 DRIVEBY_AI_ALL_DIRN TRUE 90
				ENDIF
				IF NOT IS_CHAR_DEAD russian4_s4
					TASK_DRIVE_BY russian4_s4 -1 smokebike_s4 0.0 0.0 0.0 200.0 DRIVEBY_AI_ALL_DIRN TRUE difficulty_s4value
				ENDIF
				IF NOT IS_CHAR_DEAD russian6_s4
					TASK_DRIVE_BY russian6_s4 -1 smokebike_s4 0.0 0.0 0.0 200.0 DRIVEBY_AI_ALL_DIRN TRUE 90
				ENDIF
				IF NOT IS_CHAR_DEAD russian16_s4
					TASK_DRIVE_BY russian16_s4 -1 smokebike_s4 0.0 0.0 0.0 200.0 DRIVEBY_AI_ALL_DIRN TRUE 90
				ENDIF
				MARK_CAR_AS_NO_LONGER_NEEDED car1_s4
				MARK_CAR_AS_NO_LONGER_NEEDED packer_s4
				MARK_CHAR_AS_NO_LONGER_NEEDED packerdriver_s4
				MARK_CAR_AS_NO_LONGER_NEEDED car2_s4
				MARK_CHAR_AS_NO_LONGER_NEEDED people1_s4
				MARK_CHAR_AS_NO_LONGER_NEEDED people2_s4
				MARK_CHAR_AS_NO_LONGER_NEEDED people3_s4
				MARK_CHAR_AS_NO_LONGER_NEEDED people4_s4

				finalchase_s4flag = 2
			ENDIF
		ENDIF

		IF finalchase_s4flag = 2
			IF LOCATE_CAR_2D smokebike_s4 2676.96 -1432.1 12.0 12.0 FALSE
				DELETE_CHAR russian7_s4
				DELETE_CHAR russian8_s4
				DELETE_CHAR russian9_s4
				DELETE_CAR car4_s4
				DELETE_CAR car5_s4
				DELETE_CAR packer_s4
				MARK_CHAR_AS_NO_LONGER_NEEDED russian10_s4
				MARK_CHAR_AS_NO_LONGER_NEEDED russian12_s4

				//guys at front of tunnel
				REMOVE_BLIP russian7_s4blip
				REMOVE_BLIP russian8_s4blip
				REMOVE_BLIP russian9_s4blip
				REMOVE_BLIP russian10_s4blip
				REMOVE_BLIP russian12_s4blip
				CLEAR_MISSION_AUDIO 2
				LOAD_MISSION_AUDIO 2 SOUND_SMO4_JS //We lost ‘em, Smoke!
					
				finalchase_s4flag = 3
			ENDIF
		ENDIF



		///////////////////////////////////////////////////	  PLAYER PASSES  /////////////////////////////////////////////////
		IF finalchase_s4flag = 3
			IF NOT IS_CAR_DEAD smokebike_s4	
				IF LOCATE_CAR_2D smokebike_s4 2825.37 -1538.43 7.0 7.0 FALSE
					SWITCH_WIDESCREEN ON
					SET_PLAYER_CONTROL PLAYER1 OFF
					SET_FIXED_CAMERA_POSITION 2823.7549 -1537.1378 10.0736 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2823.2505 -1537.9661 10.3176 JUMP_CUT
					CLEAR_MISSION_AUDIO 1
					CLEAR_PRINTS
					LOAD_MISSION_AUDIO 1 SOUND_SMO4_KA //Man, we better split up.
					WHILE NOT HAS_MISSION_AUDIO_LOADED 2
						WAIT 0
					ENDWHILE
					PLAY_MISSION_AUDIO 2
					PRINT_NOW SMO4_JS 5000 1//We lost ‘em, Smoke!
					finalchase_s4flag = 4
				ENDIF
			ENDIF
		ENDIF

		IF finalchase_s4flag = 4
			IF NOT IS_CAR_DEAD smokebike_s4	
				IF LOCATE_STOPPED_CAR_2D smokebike_s4 2825.37 -1538.43 5.0 5.0 FALSE
					IF NOT IS_CHAR_DEAD big_smoke
						IF HAS_MISSION_AUDIO_FINISHED 2
							IF HAS_MISSION_AUDIO_LOADED 1

								CLEAR_PRINTS
								PLAY_MISSION_AUDIO 1 //Man, we better split up.
								PRINT_NOW SMO4_KA 5000 1//Man, we better split up.
								CLEAR_MISSION_AUDIO 2
								LOAD_MISSION_AUDIO 2 SOUND_SMO4_KC	//I’ll take it another block and dump it.

								DELETE_CHAR russian2_s4
								DELETE_CHAR russian3_s4
								DELETE_CAR bike1_s4 
								REMOVE_BLIP bike1_s4blip

								DELETE_CHAR russian4_s4
								DELETE_CHAR russian5_s4
								DELETE_CAR bike2_s4 
								REMOVE_BLIP bike2_s4blip

								DELETE_CHAR russian6_s4
								DELETE_CHAR russian7_s4
								DELETE_CAR bike3_s4 
								REMOVE_BLIP bike3_s4blip

								DELETE_CHAR russian15_s4
								DELETE_CHAR russian16_s4
								DELETE_CAR bike4_s4 
								REMOVE_BLIP bike4_s4blip

								TASK_LOOK_AT_CHAR big_smoke scplayer 10000
								TIMERA = 0
								finalchase_s4flag = 5
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF finalchase_s4flag = 5
			IF HAS_MISSION_AUDIO_FINISHED 1
				IF HAS_MISSION_AUDIO_LOADED 2
					SET_FIXED_CAMERA_POSITION 2819.6658 -1542.1078 10.2063 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2820.4011 -1541.4436 10.3396 JUMP_CUT
					CLEAR_PRINTS
					IF NOT IS_CHAR_DEAD big_smoke
						START_CHAR_FACIAL_TALK big_smoke 10000
					ENDIF
					PLAY_MISSION_AUDIO 2 
					PRINT_NOW SMO4_KC 5000 1//I’ll take it another block and dump it.
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO 1 SOUND_SMO4_KD	//That was some shit back there.
					SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE scplayer KNOCKOFFBIKE_DEFAULT
					REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_MICRO_UZI
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_MICRO_UZI 80
					IF IS_CHAR_IN_ANY_CAR scplayer
						TASK_LEAVE_ANY_CAR scplayer
					ENDIF
					finalchase_s4flag = 6
				ENDIF
			ENDIF
		ENDIF

		IF finalchase_s4flag = 6
			IF NOT IS_CHAR_SITTING_IN_ANY_CAR scplayer
				IF NOT IS_CHAR_DEAD big_smoke
					TASK_LOOK_AT_CHAR scplayer big_smoke 15000
					TASK_LOOK_AT_CHAR big_smoke scplayer 15000
					finalchase_s4flag = 7
				ENDIF
			ENDIF
		ENDIF

		IF finalchase_s4flag = 7
			IF HAS_MISSION_AUDIO_FINISHED 2
				IF HAS_MISSION_AUDIO_LOADED 1
					CLEAR_PRINTS
					PLAY_MISSION_AUDIO 1
					PRINT_NOW SMO4_KD 5000 1 //That was some shit back there
					CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO 2 SOUND_SMO4_KE //Yeah, for sure.
					finalchase_s4flag = 8
				ENDIF
			ENDIF
		ENDIF

		IF finalchase_s4flag = 8
			IF HAS_MISSION_AUDIO_FINISHED 1
				IF HAS_MISSION_AUDIO_LOADED 2
					IF NOT IS_CHAR_DEAD big_smoke
						STOP_CHAR_FACIAL_TALK big_smoke
					ENDIF
					CLEAR_PRINTS
					PLAY_MISSION_AUDIO 2
					PRINT_NOW SMO4_KE 5000 1 //Yeah, for sure.
					START_CHAR_FACIAL_TALK scplayer 10000
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO 1 SOUND_SMO4_KF	//Listen we can’t hang around here—I’ll see you later, homie.
					finalchase_s4flag = 9
				ENDIF
			ENDIF
		ENDIF

		IF finalchase_s4flag = 9
			IF HAS_MISSION_AUDIO_FINISHED 2
				IF HAS_MISSION_AUDIO_LOADED 1
					CLEAR_PRINTS
					PLAY_MISSION_AUDIO 1
					PRINT_NOW SMO4_KF 5000 1 //Listen we can’t hang around here—I’ll see you later, homie.					
					CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO 2 SOUND_SMO4_KG	//Big love, CJ.
					TIMERA = 0
					finalchase_s4flag = 10
				ENDIF
			ENDIF
		ENDIF


		IF finalchase_s4flag = 10
			IF NOT IS_CAR_DEAD smokebike_s4
				IF NOT IS_CHAR_DEAD big_smoke
					IF TIMERA > 3000
						IF HAS_MISSION_AUDIO_FINISHED 1
							IF HAS_MISSION_AUDIO_LOADED 2
								STOP_CHAR_FACIAL_TALK scplayer
								SET_CAR_STRAIGHT_LINE_DISTANCE smokebike_S4 200
								TASK_CAR_DRIVE_TO_COORD big_smoke smokebike_s4 2804.5 -1538.95 10.38 6.0 MODE_NORMAL 0 DRIVINGMODE_PLOUGHTHROUGH
								PLAY_MISSION_AUDIO 2
								PRINT_NOW SMO4_KG 2500 1 //Big love, CJ. 
								IF NOT IS_CHAR_DEAD big_smoke
									START_CHAR_FACIAL_TALK big_smoke 10000
								ENDIF
								TIMERA = 0
								finalchase_s4flag = 11
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF finalchase_s4flag = 11
			IF TIMERA > 2000
				IF HAS_MISSION_AUDIO_FINISHED 2
					IF NOT IS_CHAR_DEAD big_smoke
						STOP_CHAR_FACIAL_TALK big_smoke
					ENDIF
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL PLAYER1 ON
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					DELETE_CAR smokebike_S4
					DELETE_CHAR big_smoke
					CLEAR_ONSCREEN_COUNTER bikehealth_s4
					CLEAR_PRINTS
					GOTO mission_drugs1_passed
					//clear counters
					finalchase_s4flag = 12
				ENDIF
			ENDIF
		ENDIF


		//gate check
		IF grate_s4flag = 1
			IF HAS_OBJECT_OF_TYPE_BEEN_SMASHED 2631.852 -1482.75 18.109 10.0 storm_drain_cover 
				IF HAS_OBJECT_BEEN_DAMAGED_BY_WEAPON grate_s4 WEAPONTYPE_MICRO_UZI
					CLEAR_PRINTS
					grate_s4flag = 2
				ELSE
					GET_CAR_HEALTH smokebike_s4 bikehealth_s4
					bikehealth_s4 = bikehealth_s4 - 100
					SET_CAR_HEALTH smokebike_s4 bikehealth_s4
					bikehealth_s4 = bikehealth_s4 / 10
					//WRITE_DEBUG_WITH_INT smokebike_s4 smokebike_s4

					CLEAR_PRINTS
					grate_s4flag = 3
				ENDIF
			ENDIF
		ENDIF

		//audio
		IF finalchase_s4flag > 0
			IF finalchase_s4flag < 4

				IF progressaudio_s4flag = 0
					IF handlingudio_s4flag = 0
						audio_label_s4 = SOUND_SMO4_JE	//Fry, motherfuckers!
						$input_text_s4 = SMO4_JE	//Fry, motherfuckers!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
				IF progressaudio_s4flag = 1
					IF handlingudio_s4flag = 0
						audio_label_s4 = SOUND_SMO4_JF	//There’s the old sewer up ahead!
						$input_text_s4 = SMO4_JF	//There’s the old sewer up ahead!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
				IF progressaudio_s4flag = 2
					IF handlingudio_s4flag = 0
						audio_label_s4 = SOUND_SMO4_JG	//Shoot out the gate!
						$input_text_s4 = SMO4_JG	//Shoot out the gate!
						GOSUB load_audio_s4
					ENDIF
				ENDIF

				IF progressaudio_s4flag = 3
					IF handlingudio_s4flag = 0
						IF grate_s4flag = 2
							audio_label_s4 = SOUND_SMO4_JH	//Nice one, CJ, here we go!
							$input_text_s4 = SMO4_JH	//Nice one, CJ, here we go!
							GOSUB load_audio_s4
						ELSE
							IF grate_s4flag = 3
								audio_label_s4 = SOUND_SMO4_JJ	//The gate, Carl, the GATE!
								$input_text_s4 = SMO4_JJ	//The gate, Carl, the GATE!
								GOSUB load_audio_s4
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF truckaudio_s4flag = 0
					IF LOCATE_CAR_2D smokebike_s4 2647.31 -1457.67 10.0 10.0 FALSE
						truckaudio_s4flag = 1
					ENDIF
				ENDIF

				IF progressaudio_s4flag = 4
					IF handlingudio_s4flag = 0
						IF truckaudio_s4flag = 1
							audio_label_s4 = SOUND_SMO4_JK	//Man, I used to hate this tunnel when we was kids.
							$input_text_s4 = SMO4_JK	//Man, I used to hate this tunnel when we was kids.
							GOSUB load_audio_s4
						ENDIF
					ENDIF
				ENDIF
				IF progressaudio_s4flag = 5
					IF handlingudio_s4flag = 0
						audio_label_s4 = SOUND_SMO4_JL	//We can reminisce later – we still got company!
						$input_text_s4 = SMO4_JL	//We can reminisce later – we still got company!
						GOSUB load_audio_s4
					ENDIF
				ENDIF
				IF progressaudio_s4flag = 6
					IF handlingudio_s4flag = 0
						audio_label_s4 = SOUND_SMO4_JP	//Don’t these guys ever give up?
						$input_text_s4 = SMO4_JP	//Don’t these guys ever give up?
						GOSUB load_audio_s4
					ENDIF
				ENDIF



				GOSUB process_audio_s4

			ENDIF
		ENDIF


		//if cars get damaged set them on fire instantly
		IF car4_s4flag = 1
			IF NOT IS_CAR_DEAD car4_s4
				IF HAS_CAR_BEEN_DAMAGED_BY_CHAR car4_s4	scplayer
					EXPLODE_CAR car4_s4
					car4_s4flag = 2
				ENDIF
			ENDIF
		ENDIF

		IF car5_s4flag = 1
			IF NOT IS_CAR_DEAD car5_s4
				IF HAS_CAR_BEEN_DAMAGED_BY_CHAR car5_s4	scplayer
					EXPLODE_CAR car5_s4
					car5_s4flag = 2
				ENDIF
			ENDIF
		ENDIF

		//spin/stop bikes if the both guys are not in car
		IF bike1swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike1_s4
				IF NOT IS_CHAR_DEAD russian1_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian2_s4
						IF IS_CHAR_IN_CAR russian2_s4 bike1_s4
							GET_DRIVER_OF_CAR bike1_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian2_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian2_s4 bike1_s4
								TASK_DRIVE_BY russian2_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike1swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike1_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike1_s4
						SET_CAR_ROTATION_VELOCITY bike1_s4 3.5 7.3999 8.1003
						bike1swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike1swap_s4flag = 2
			IF IS_CHAR_DEAD russian2_s4
				STOP_PLAYBACK_RECORDED_CAR bike1_s4
				IF NOT IS_CAR_DEAD bike1_s4
					SET_CAR_FORWARD_SPEED bike1_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike1_s4 3.5 7.3999 8.1003
				ENDIF
				bike1swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike1_s4flag = 1
			IF IS_CHAR_DEAD russian1_s4
				IF IS_CHAR_DEAD russian2_s4
				OR IS_CAR_DEAD bike1_s4
					STOP_PLAYBACK_RECORDED_CAR bike1_s4
					REMOVE_BLIP bike1_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike1_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian1_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian2_s4
					bike1_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF bike2swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike2_s4
				IF NOT IS_CHAR_DEAD russian3_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian4_s4
						IF IS_CHAR_IN_CAR russian4_s4 bike2_s4
							GET_DRIVER_OF_CAR bike2_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian4_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian4_s4 bike2_s4
								TASK_DRIVE_BY russian4_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike2swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike2_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike2_s4
						SET_CAR_ROTATION_VELOCITY bike2_s4 3.5 7.3999 8.1003
						bike2swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike2swap_s4flag = 2
			IF IS_CHAR_DEAD russian4_s4
				STOP_PLAYBACK_RECORDED_CAR bike2_s4
				IF NOT IS_CAR_DEAD bike2_s4
					SET_CAR_FORWARD_SPEED bike2_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike2_s4 3.5 7.3999 8.1003
				ENDIF
				bike2swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike2_s4flag = 1
			IF IS_CHAR_DEAD russian3_s4
				IF IS_CHAR_DEAD russian4_s4
				OR IS_CAR_DEAD bike2_s4
					STOP_PLAYBACK_RECORDED_CAR bike2_s4
					REMOVE_BLIP bike2_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike2_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian3_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian4_s4
					bike2_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF bike3swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike3_s4
				IF NOT IS_CHAR_DEAD russian5_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian6_s4
						IF IS_CHAR_IN_CAR russian6_s4 bike3_s4
							GET_DRIVER_OF_CAR bike3_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian6_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian6_s4 bike3_s4
								TASK_DRIVE_BY russian6_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike3swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike3_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike3_s4
						SET_CAR_ROTATION_VELOCITY bike3_s4 3.5 7.3999 8.1003
						bike3swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike3swap_s4flag = 2
			IF IS_CHAR_DEAD russian6_s4
				STOP_PLAYBACK_RECORDED_CAR bike3_s4
				IF NOT IS_CAR_DEAD bike3_s4
					SET_CAR_FORWARD_SPEED bike3_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike3_s4 3.5 7.3999 8.1003
				ENDIF
				bike3swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike3_s4flag = 1
			IF IS_CHAR_DEAD russian5_s4
				IF IS_CHAR_DEAD russian6_s4
				OR IS_CAR_DEAD bike3_s4
					STOP_PLAYBACK_RECORDED_CAR bike3_s4
					REMOVE_BLIP bike3_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike3_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian5_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian6_s4
					bike3_s4flag = 2
				ENDIF
			ENDIF
		ENDIF
		//
		IF bike4swap_s4flag = 1
			IF NOT IS_CAR_DEAD bike4_s4
				IF NOT IS_CHAR_DEAD russian15_s4
				ELSE
					IF NOT IS_CHAR_DEAD russian16_s4
						IF IS_CHAR_IN_CAR russian16_s4 bike4_s4
							GET_DRIVER_OF_CAR bike4_s4 driverofcar_s4
							IF driverofcar_s4 = -1
								GET_CAR_COORDINATES smokebike_s4 player_x player_y player_z
								WARP_CHAR_FROM_CAR_TO_COORD russian16_s4 player_x player_y -10.0
								WARP_CHAR_INTO_CAR russian16_s4 bike4_s4
								TASK_DRIVE_BY russian16_s4 -1 smokebike_s4 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								bike4swap_s4flag = 2
							ENDIF
						ENDIF
					ELSE
						SET_CAR_FORWARD_SPEED bike4_s4 18.0
						STOP_PLAYBACK_RECORDED_CAR bike4_s4
						SET_CAR_ROTATION_VELOCITY bike4_s4 3.5 7.3999 8.1003
						bike4swap_s4flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF bike4swap_s4flag = 2
			IF IS_CHAR_DEAD russian16_s4
				STOP_PLAYBACK_RECORDED_CAR bike4_s4
				IF NOT IS_CAR_DEAD bike4_s4
					SET_CAR_FORWARD_SPEED bike4_s4 18.0
					SET_CAR_ROTATION_VELOCITY bike4_s4 3.5 7.3999 8.1003
				ENDIF
				bike4swap_s4flag = 3
			ENDIF 
		ENDIF
		IF bike4_s4flag = 1
			IF IS_CHAR_DEAD russian15_s4
				IF IS_CHAR_DEAD russian16_s4
				OR IS_CAR_DEAD bike4_s4
					STOP_PLAYBACK_RECORDED_CAR bike4_s4
					REMOVE_BLIP bike4_s4blip
					MARK_CAR_AS_NO_LONGER_NEEDED bike4_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian15_s4
					MARK_CHAR_AS_NO_LONGER_NEEDED russian16_s4
					bike4_s4flag = 2
				ENDIF
			ENDIF
		ENDIF


	ENDIF
ENDIF







GOTO smoke4_main_loop


mission_smoke4_CUT_timescale:

	SWITCH timescale_status_s4

		CASE 0
			SET_TIME_SCALE 0.8
			timescale_s4 = 0.8
			timescale_status_s4 ++
		BREAK
		CASE 1
			IF TIMERB > 3200
				
				timescale_s4 -=@ 0.05
				IF timescale_s4 < 0.3
					timescale_s4 = 0.3
					timescale_status_s4 ++
				ENDIF

				SET_TIME_SCALE timescale_s4	
			ENDIF
		BREAK
		CASE 2
			IF TIMERB > 4000
				timescale_s4 +=@ 0.05
				IF timescale_s4 > 1.0
					timescale_s4 = 1.0
					timescale_status_s4 ++
				ENDIF
				SET_TIME_SCALE timescale_s4	
			ENDIF
		BREAK
	ENDSWITCH

RETURN


mission_smoke4_CUT_camera_position:

SWITCH camera_pos_status_s4
	CASE 0
		GET_CAR_COORDINATES smokebike_s4 cam_look_X_s4 cam_look_Y_s4 cam_look_Z_s4
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS smokebike_s4 1.0 -2.5 0.0 cam_pos_X_s4 cam_pos_Y_s4 cam_pos_Z_s4

		cam_pos_X_s4 = 2563.2803 
		cam_pos_Y_s4 = -1737.3275 
		cam_pos_Z_s4 = 1.1412 

		cam_look_X_s4 = 2562.6504 
		cam_look_Y_s4 = -1734.7252 
		cam_look_Z_s4 = 1.1991 

		cam_P_acc_X_s4 = 0.001 
		cam_P_acc_Y_s4 = 0.000000000003 
		
		cam_P_speed_X_s4 = 0.03 
		cam_P_speed_Y_s4 = 0.1 
		cam_P_Y_cap_speed = 0.47
		cam_P_speed_Z_s4 = 0.003 
		
		camera_pos_status_s4 ++
	CASE 1
		IF TIMERB < 2900
			cam_P_speed_X_s4 +=@ cam_P_acc_X_s4
			
			cam_P_acc_Y_s4 +=@ cam_P_acc_Y_s4
			cam_P_speed_Y_s4 +=@ cam_P_acc_Y_s4
			IF cam_P_speed_Y_s4 > cam_P_Y_cap_speed
				cam_P_speed_Y_s4 = cam_P_Y_cap_speed
			ENDIF

			cam_pos_X_s4 +=@ cam_P_speed_X_s4
			cam_pos_Y_s4 +=@ cam_P_speed_Y_s4
			cam_pos_Z_s4 +=@ cam_P_speed_Z_s4

			BREAK
		ELSE
			cam_P_acc_X_s4 = -0.01
			camera_pos_status_s4 ++
		ENDIF
	CASE 2
		IF TIMERB < 4000
			cam_P_speed_X_s4 +=@ cam_P_acc_X_s4
			
			cam_P_acc_Y_s4 +=@ cam_P_acc_Y_s4
			cam_P_speed_Y_s4 +=@ cam_P_acc_Y_s4
			IF cam_P_speed_Y_s4 > cam_P_Y_cap_speed
				cam_P_speed_Y_s4 = cam_P_Y_cap_speed
			ENDIF

			cam_pos_X_s4 +=@ cam_P_speed_X_s4
			cam_pos_Y_s4 +=@ cam_P_speed_Y_s4
			cam_pos_Z_s4 +=@ cam_P_speed_Z_s4

			BREAK
		ELSE
			camera_pos_status_s4 ++
		ENDIF
ENDSWITCH

RETURN



mission_smoke4_CUT_camera_lookat:

	SWITCH camera_look_status_s4

		CASE 0
			cam_L_acc_Y_s4 = 0.007
			cam_L_acc_Z_s4 = 0.012 

			cam_L_speed_X_s4 = 0.045 
			cam_L_speed_Y_s4 = 0.06
			cam_L_Y_cap_speed = 0.32 				
			cam_L_speed_Z_s4 = 0.005 
			cam_L_Z_cap_speed = 0.1

			camera_look_status_s4 ++

		CASE 1
			IF TIMERB < 2800

				cam_L_speed_Y_s4 +=@ cam_L_acc_Y_s4
				IF cam_L_speed_Y_s4 > cam_L_Y_cap_speed
					cam_L_speed_Y_s4 = cam_L_Y_cap_speed
				ENDIF
				
				cam_look_X_s4 +=@ cam_L_speed_X_s4
				cam_look_Y_s4 +=@ cam_L_speed_Y_s4
				cam_look_Z_s4 +=@ cam_L_speed_Z_s4
				
				BREAK
			ELSE
				camera_look_status_s4 ++
			ENDIF

		CASE 2
			IF TIMERB < 3600

				cam_L_speed_Y_s4 +=@ cam_L_acc_Y_s4
				IF cam_L_speed_Y_s4 > cam_L_Y_cap_speed
					cam_L_speed_Y_s4 = cam_L_Y_cap_speed
				ENDIF
				
				cam_L_speed_Z_s4 +=@ cam_L_acc_Z_s4
				IF cam_L_speed_Z_s4 > cam_L_Z_cap_speed
					cam_L_speed_Z_s4 = cam_L_Z_cap_speed
				ENDIF

				cam_look_X_s4 +=@ cam_L_speed_X_s4
				cam_look_Y_s4 +=@ cam_L_speed_Y_s4
				cam_look_Z_s4 +=@ cam_L_speed_Z_s4

			ELSE
				camera_look_status_s4 ++		
			ENDIF			

		BREAK

	ENDSWITCH

RETURN

load_audio_s4:
IF handlingudio_s4flag = 0
	LOAD_MISSION_AUDIO 1 audio_label_s4
	$text_label_s4 = $input_text_s4
	handlingudio_s4flag = 1
ENDIF
RETURN

process_audio_s4:
IF handlingudio_s4flag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		PRINT_NOW $text_label_s4 4000 1 //Dummy message"
		PLAY_MISSION_AUDIO 1
		handlingudio_s4flag = 2
	ENDIF
ENDIF
IF handlingudio_s4flag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_s4flag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		handlingudio_s4flag = 0
	ENDIF
ENDIF
RETURN


runkillstay_s4label:
OPEN_SEQUENCE_TASK runkillstay_s4seq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_s4 enemyy_s4 enemyz_s4 PEDMOVE_RUN -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_s4
CLOSE_SEQUENCE_TASK runkillstay_s4seq
PERFORM_SEQUENCE_TASK enemy_s4 runkillstay_s4seq
CLEAR_SEQUENCE_TASK runkillstay_s4seq
RETURN

run2kill_s4label:
OPEN_SEQUENCE_TASK run2kill_s4seq
TASK_GO_TO_COORD_WHILE_SHOOTING -1 enemyx_s4 enemyy_s4 enemyz_s4 PEDMOVE_RUN 0.5 1.0 scplayer
TASK_GO_TO_COORD_WHILE_SHOOTING -1 enemyx2_s4 enemyy2_s4 enemyz2_s4 PEDMOVE_RUN 0.5 1.0 scplayer
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_s4
CLOSE_SEQUENCE_TASK run2kill_s4seq
PERFORM_SEQUENCE_TASK enemy_s4 run2kill_s4seq
CLEAR_SEQUENCE_TASK run2kill_s4seq
RETURN

runkill_s4label:
OPEN_SEQUENCE_TASK runkill_s4seq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_s4 enemyy_s4 enemyz_s4 PEDMOVE_RUN -1
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_s4
CLOSE_SEQUENCE_TASK runkill_s4seq
PERFORM_SEQUENCE_TASK enemy_s4 runkill_s4seq
CLEAR_SEQUENCE_TASK runkill_s4seq
RETURN

stayshoot_s4label:
OPEN_SEQUENCE_TASK stayshoot_s4seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_s4
CLOSE_SEQUENCE_TASK stayshoot_s4seq
PERFORM_SEQUENCE_TASK enemy_s4 stayshoot_s4seq
CLEAR_SEQUENCE_TASK stayshoot_s4seq
RETURN

stayshootnoduck_s4label:
OPEN_SEQUENCE_TASK stayshootnoduck_s4seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 enemytarget_s4
CLOSE_SEQUENCE_TASK stayshootnoduck_s4seq
PERFORM_SEQUENCE_TASK enemy_s4 stayshootnoduck_s4seq
CLEAR_SEQUENCE_TASK stayshootnoduck_s4seq
RETURN


mission_drugs1_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission drugs1 passed ************************

mission_drugs1_passed:

flag_smoke_mission_counter ++
REMOVE_BLIP smoke_contact_blip
REGISTER_MISSION_PASSED ( SMOKE_4 )
PLAYER_MADE_PROGRESS 1
//REGISTER_MISSION_PASSED ( )
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 10 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 1
AWARD_PLAYER_MISSION_RESPECT 10//amount of respect

CLEAR_WANTED_LEVEL player1
//START_NEW_SCRIPT hood_mission4_loop
RETURN
		


// ********************************** mission cleanup **************************************

mission_cleanup_drugs1:

flag_player_on_mission = 0

SWITCH_ROADS_ON 1694.12 -1604.33 5.0 1805.94 -1593.12 15.0
REMOVE_CHAR_ELEGANTLY big_smoke
UNLOAD_SPECIAL_CHARACTER 1
DISABLE_ALL_ENTRY_EXITS FALSE
//blips
REMOVE_BLIP smokecar_s4blip
REMOVE_BLIP atrium_s4blip
REMOVE_BLIP smoke_s4blip
REMOVE_BLIP packer_s4blip
REMOVE_BLIP car1_s4blip
REMOVE_BLIP car2_s4blip
REMOVE_BLIP car3_s4blip
REMOVE_BLIP car4_s4blip
REMOVE_BLIP bike1_s4blip
REMOVE_BLIP bike2_s4blip
REMOVE_BLIP bike3_s4blip
REMOVE_BLIP bike4_s4blip
REMOVE_BLIP bike5_s4blip
REMOVE_BLIP bike6_s4blip
REMOVE_BLIP bike7_s4blip
REMOVE_BLIP rcar4_s4blip
//REMOVE_BLIP bike9_s4blip
REMOVE_BLIP bike10_s4blip
REMOVE_BLIP bike11_s4blip
REMOVE_BLIP bike12_s4blip
REMOVE_BLIP russian13_s4blip
REMOVE_BLIP russian14_s4blip
REMOVE_BLIP rcar3_s4blip
REMOVE_BLIP rcar4_s4blip
REMOVE_BLIP rcar1_s4blip
REMOVE_BLIP russian7_s4blip
REMOVE_BLIP russian8_s4blip
REMOVE_BLIP russian9_s4blip
REMOVE_BLIP russian10_s4blip
REMOVE_BLIP russian12_s4blip
REMOVE_BLIP russian13_s4blip
REMOVE_BLIP russian14_s4blip
REMOVE_BLIP russian20_s4blip

SET_PLAYER_GROUP_RECRUITMENT player1 TRUE 
SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

//mark models
MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
MARK_MODEL_AS_NO_LONGER_NEEDED WFYRI
MARK_MODEL_AS_NO_LONGER_NEEDED WFYST
MARK_MODEL_AS_NO_LONGER_NEEDED MAFFA
MARK_MODEL_AS_NO_LONGER_NEEDED MAFFB
MARK_MODEL_AS_NO_LONGER_NEEDED BF400
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
REMOVE_ANIMATION JST_BUISNESS
MARK_MODEL_AS_NO_LONGER_NEEDED SULTAN
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_SKIP
MARK_MODEL_AS_NO_LONGER_NEEDED NEBULA
MARK_MODEL_AS_NO_LONGER_NEEDED COACH
MARK_MODEL_AS_NO_LONGER_NEEDED PACKER
MARK_MODEL_AS_NO_LONGER_NEEDED storm_drain_cover
MARK_MODEL_AS_NO_LONGER_NEEDED barrel4
//density's
SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0
//multiplier
SET_WANTED_MULTIPLIER 1.0
SET_MAX_WANTED_LEVEL 4
//counters
CLEAR_ONSCREEN_COUNTER smokehealth_s4 
CLEAR_ONSCREEN_COUNTER bikehealth_s4
//other
IF IS_PLAYER_PLAYING PLAYER1
	//SET_PLAYER_FAST_RELOAD PLAYER1 FALSE
	SHUT_CHAR_UP scplayer FALSE
	SET_PLAYER_ENTER_CAR_BUTTON PLAYER1 TRUE
	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE scplayer KNOCKOFFBIKE_DEFAULT
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
IF NOT IS_CHAR_DEAD big_smoke
	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE big_smoke KNOCKOFFBIKE_DEFAULT
ENDIF
RELEASE_WEATHER
DETACH_CHAR_FROM_CAR scplayer
SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
SWITCH_EMERGENCY_SERVICES ON
GET_GAME_TIMER timer_mobile_start
MISSION_HAS_FINISHED

IF IS_PLAYER_PLAYING PLAYER1
	IF NOT IS_CHAR_DEAD russian1_s4
		TASK_KILL_CHAR_ON_FOOT russian1_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian2_s4
		TASK_KILL_CHAR_ON_FOOT russian2_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian3_s4
		TASK_KILL_CHAR_ON_FOOT russian3_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian4_s4
		TASK_KILL_CHAR_ON_FOOT russian4_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian5_s4
		TASK_KILL_CHAR_ON_FOOT russian5_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian6_s4
		TASK_KILL_CHAR_ON_FOOT russian6_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian7_s4
		TASK_KILL_CHAR_ON_FOOT russian7_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian8_s4
		TASK_KILL_CHAR_ON_FOOT russian8_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian9_s4
		TASK_KILL_CHAR_ON_FOOT russian9_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian10_s4
		TASK_KILL_CHAR_ON_FOOT russian10_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian11_s4
		TASK_KILL_CHAR_ON_FOOT russian11_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian12_s4
		TASK_KILL_CHAR_ON_FOOT russian12_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian13_s4
		TASK_KILL_CHAR_ON_FOOT russian13_s4 scplayer
	ENDIF
	IF NOT IS_CHAR_DEAD russian14_s4
		TASK_KILL_CHAR_ON_FOOT russian14_s4 scplayer
	ENDIF
ENDIF

RETURN

 
}

