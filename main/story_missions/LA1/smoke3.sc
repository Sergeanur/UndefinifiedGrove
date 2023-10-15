MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* SMOKE 3 *******************************************
// ********************************** Train-ing Day*****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME smoke3

// Mission start stuff

GOSUB mission_start_smoke3

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_smoke3_failed
ENDIF

GOSUB mission_cleanup_smoke3

MISSION_END

{
 
// Variables for mission

//mexican peds
LVAR_INT mex1_s3
LVAR_INT mex2_s3
LVAR_INT mex3_s3
LVAR_INT mex4_s3
//getting task status
LVAR_INT taskstatus_s3
LVAR_FLOAT finishanim_s3
//train
LVAR_INT train_s3
LVAR_INT opptrain_s3
LVAR_INT opptrain2_s3
LVAR_INT carriage1_s3
LVAR_INT carriage2_s3
LVAR_FLOAT train_s3heading
LVAR_FLOAT carriage1x_s3 carriage1y_s3 carriage1z_s3
LVAR_FLOAT carriage2x_s3 carriage2y_s3 carriage2z_s3
//object
LVAR_INT crane_s3
LVAR_INT barrier_s3
//bike
LVAR_INT bike_s3
//train speed
//decisions
LVAR_INT std1ped_s3
//block car
LVAR_INT blockcar_s3
LVAR_INT othercar_s3
LVAR_INT othercardriver1_s3
LVAR_INT othercardriver2_s3
LVAR_INT blockcardriver_s3
//sequences
LVAR_INT enemy_s3
LVAR_INT jump_s3seq
LVAR_INT ontopoftrain_s3seq 
LVAR_INT cutscene_s3seq
LVAR_INT killsmoke_s3seq
//train speed
LVAR_FLOAT playercarx_s3 playercary_s3 playercarz_s3
LVAR_FLOAT trainx_s3 trainy_s3 trainz_s3
LVAR_FLOAT distance_s3
LVAR_INT trainspeed_s3 /////////////////////////////////////////////////////////////////////////
LVAR_INT smoke3_DM //decision maker
LVAR_INT smoke3mex_DM //decision maker
LVAR_INT emptysmoke3_DM
LVAR_INT groupsmoke3_DM
LVAR_INT smokecar_s3
LVAR_INT smoke3_area

//flags
LVAR_INT smoke_s3flag
LVAR_INT firstcutscene_s3flag
LVAR_INT mex1jump_s3flag
LVAR_INT mex2jump_s3flag
LVAR_INT mex3jump_s3flag
LVAR_INT mex4jump_s3flag
LVAR_INT trainismoving_s3flag
LVAR_INT mex1dead_s3flag
LVAR_INT mex2dead_s3flag
LVAR_INT mex3dead_s3flag
LVAR_INT mex4dead_s3flag
LVAR_INT opptrain_s3flag
LVAR_INT smokegroupstation_s3flag
LVAR_INT blockcar_s3flag
LVAR_INT outoffirstgroup_s3flag
LVAR_INT smokegroupchase_s3flag
LVAR_INT trainspeed_s3flag///////////////////////////////////////////////////////////////////////
LVAR_INT speedlimit_s3flag
LVAR_INT difficulty_s3flag		//do not reset this flag
LVAR_INT run_s2flag
LVAR_INT drivebyfirsttime_s3flag
LVAR_INT skipcutscene_s3flag
LVAR_INT smokeincar_s3flag
LVAR_INT dialogue_s3flag
LVAR_INT duck_s3flag
LVAR_INT mex2_s3flag
LVAR_INT mex4_s3flag
LVAR_INT locatesmoke_s3flag
LVAR_INT explode_s3flag 
LVAR_INT missionplaying_s3flag
LVAR_INT progressaudio_s3flag
LVAR_INT handlingudio_s3flag
LVAR_INT playerinbike_s3flag
LVAR_INT audiowayback_s3flag
LVAR_INT bikecreated_s3flag
LVAR_INT getsmoke_s3flag
LVAR_INT mexdead_s3counter
LVAR_INT infotext_s3flag
//blips
LVAR_INT trainstation_s3blip
LVAR_INT mex1_s3blip
LVAR_INT mex2_s3blip
LVAR_INT mex3_s3blip
LVAR_INT mex4_s3blip
LVAR_INT smokecar_s3blip
LVAR_INT smokehouse_s3blip
LVAR_INT bike_s3blip



LVAR_TEXT_LABEL $text_label_s3
LVAR_INT audio_label_s3
LVAR_TEXT_LABEL $input_text_s3


//debug
//VAR_INT health1
//VAR_INT health2
//VAR_INT health3
//VAR_INT health4	timertest
//
// **************************************** Mission Start **********************************

mission_start_smoke3:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

WAIT 0


// ****************************************START OF CUTSCENE********************************

LOAD_MISSION_TEXT SMOKE3

FORCE_WEATHER_NOW WEATHER_SUNNY_LA
CLEAR_AREA 2077.58 -1695.929 150.0 150.0 TRUE

SET_FADING_COLOUR 0 0 0



DO_FADE 2000 FADE_OUT

WHILE GET_FADING_STATUS
            WAIT 0
ENDWHILE

REQUEST_MODEL Lae_smokecutscene

WHILE NOT HAS_MODEL_LOADED Lae_smokecutscene
	WAIT 0
ENDWHILE

LVAR_INT smokeobject_s3
CREATE_OBJECT_NO_OFFSET Lae_smokecutscene 2055.0 -1695.0 15.0 smokeobject_s3


LOAD_CUTSCENE SMOKE3A
 
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

RELEASE_WEATHER

MARK_MODEL_AS_NO_LONGER_NEEDED Lae_smokecutscene
DELETE_OBJECT smokeobject_s3


// ****************************************END OF CUTSCENE**********************************

// fades the screen in 


SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL PLAYER1 ON

//LOAD_CHAR_DECISION_MAKER imran/std1_is std1ped_s3

SET_FADING_COLOUR 0 0 0

DO_FADE 1500 FADE_IN

// request models

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON


REQUEST_MODEL STREAK
REQUEST_MODEL STREAKC
REQUEST_MODEL IMMMCRAN
REQUEST_MODEL GLENDALE
REQUEST_MODEL TEC9

CLEAR_MISSION_AUDIO 1
LOAD_MISSION_AUDIO 1 SOUND_SMO3_AA	//Where to, Smoke?
CLEAR_MISSION_AUDIO 2
LOAD_MISSION_AUDIO 2 SOUND_SMOX_AD //Come on, playa, get in!

LOAD_SPECIAL_CHARACTER 1 SMOKE





LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	OR NOT HAS_MISSION_AUDIO_LOADED 2
	WAIT 0
ENDWHILE

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED STREAK
	OR NOT HAS_MODEL_LOADED STREAKC
	OR NOT HAS_MODEL_LOADED IMMMCRAN
	OR NOT HAS_MODEL_LOADED TEC9
	OR NOT HAS_MODEL_LOADED GLENDALE
	WAIT 0
ENDWHILE




//REQUEST_ANIMATION RIOT

//	OR NOT HAS_ANIMATION_LOADED RIOT


//decision maker
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY smoke3_DM
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY emptysmoke3_DM
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE emptysmoke3_DM EVENT_DAMAGE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY smoke3mex_DM
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE smoke3mex_DM EVENT_DAMAGE
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE smoke3mex_DM EVENT_POTENTIAL_GET_RUN_OVER
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE smoke3mex_DM EVENT_POTENTIAL_WALK_INTO_VEHICLE
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1


//create train
CLEAR_AREA 1682.6184 -1957.8800 150.0 150.0 FALSE

//Go with Smoke to the Train Station where the mexicans are doing the deal.
SWITCH_RANDOM_TRAINS OFF
DELETE_ALL_TRAINS

CREATE_MISSION_TRAIN 11 1725.55 -1953.278 12.5 FALSE train_s3 //1725 was 30
SET_TRAIN_SPEED train_s3 0.0
SET_TRAIN_CRUISE_SPEED train_s3 0.0
GET_TRAIN_CARRIAGE train_s3 1 carriage1_s3
GET_TRAIN_CARRIAGE train_s3 2 carriage2_s3
SET_CAR_PROOFS train_s3 TRUE TRUE TRUE TRUE TRUE
SET_TRAIN_FORCED_TO_SLOW_DOWN train_s3 FALSE
LOCK_CAR_DOORS train_s3 CARLOCK_LOCKED
LOCK_CAR_DOORS carriage1_s3 CARLOCK_LOCKED
LOCK_CAR_DOORS carriage2_s3 CARLOCK_LOCKED
SET_CAR_PROOFS carriage1_s3 TRUE TRUE TRUE TRUE TRUE
SET_CAR_PROOFS carriage2_s3 TRUE TRUE TRUE TRUE TRUE


//Set player's and smokes coordinates after cutscene
//create smoke's car
CLEAR_AREA 2077.58 -1695.929 150.0 150.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR GLENDALE &_A2tmfK_
CREATE_CAR GLENDALE 2058.86 -1694.612 12.297 smokecar_S3
CHANGE_CAR_COLOUR smokecar_S3 98 14
SET_CAR_HEADING smokecar_S3 271.822
SET_CAR_HEALTH smokecar_S3 1000
CREATE_CHAR_AS_PASSENGER smokecar_S3 PEDTYPE_SPECIAL SPECIAL01 0 big_smoke
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE
SET_CHAR_HEALTH big_smoke 500
SET_CHAR_ONLY_DAMAGED_BY_PLAYER big_smoke TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS big_smoke FALSE
SET_CHAR_NEVER_TARGETTED big_smoke TRUE
SET_CHAR_DECISION_MAKER big_smoke smoke3_DM
SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED big_smoke TRUE
SET_MAX_FIRE_GENERATIONS 1
SET_RADIO_CHANNEL RS_MODERN_HIP_HOP
WARP_CHAR_INTO_CAR scplayer smokecar_S3


RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER

SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
SET_RAILTRACK_RESISTANCE_MULT 0.3

DO_FADE 500 FADE_IN

SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE


//  ************************************ DECLARE VARIABLES ********************************

smoke_s3flag = 0
firstcutscene_s3flag = 0
mex1jump_s3flag = 0
mex2jump_s3flag = 0
mex3jump_s3flag = 0
mex4jump_s3flag = 0
trainismoving_s3flag = 0
mex1dead_s3flag = 0
mex2dead_s3flag = 0
mex3dead_s3flag = 0
mex4dead_s3flag = 0
opptrain_s3flag = 0
smokegroupstation_s3flag = 0
blockcar_s3flag = 0
outoffirstgroup_s3flag = 0
trainspeed_s3flag = 0
smokegroupchase_s3flag = 0
run_s2flag = 0
drivebyfirsttime_s3flag = 0
skipcutscene_s3flag = 0
smokeincar_s3flag = 0
dialogue_s3flag = 0	
duck_s3flag = 0
speedlimit_s3flag = 0
locatesmoke_s3flag = 0
mex2_s3flag	= 0
mex4_s3flag	= 0
explode_s3flag = 0
missionplaying_s3flag = 0
progressaudio_s3flag = 0
handlingudio_s3flag	= 0
playerinbike_s3flag = 0
bikecreated_s3flag = 0
getsmoke_s3flag = 0
mexdead_s3counter = 0
infotext_s3flag = 0
//	************************************ MISSION LOOP *************************************

IF smoke_s3flag = 0	
	ADD_BLIP_FOR_COORD 1783.211 -1933.133 12.597 trainstation_s3blip
	dialogue_s3flag = 1
	smoke_s3flag = 1
ENDIF


smoke4_main_mission_loop:

WAIT 0


//VIEW_INTEGER_VARIABLE playerinbike_s3flag playerinbike_s3flag //mex1dead_s3flag mex1dead_s3flag
//VIEW_INTEGER_VARIABLE mex2dead_s3flag mex2dead_s3flag
//VIEW_INTEGER_VARIABLE mex3dead_s3flag mex3dead_s3flag
//VIEW_INTEGER_VARIABLE mex4dead_s3flag mex4dead_s3flag
//VIEW_INTEGER_VARIABLE duck_s3flag duck_s3flag 
//VIEW_INTEGER_VARIABLE smoke_s3flag smoke_s3flag 
//VIEW_INTEGER_VARIABLE smokegroupchase_s3flag smokegroupchase_s3flag


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_smoke3_passed
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Getting to train station	////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



///////////////////////////////////////////////////////////////////////////group stuff before the train station

IF smoke_s3flag = 1
	IF NOT IS_CHAR_DEAD	big_smoke
		IF NOT IS_CAR_DEAD smokecar_s3
			IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s3

				IF dialogue_s3flag = 1
					PLAY_MISSION_AUDIO 1
					PRINT_NOW SMO3_AA 5000 1 //Where to
					dialogue_s3flag = 2
				ENDIF
				IF dialogue_s3flag = 2
					IF HAS_MISSION_AUDIO_FINISHED 1
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_SMO3_AB //Unity Station.
						dialogue_s3flag = 3
					ENDIF
				ENDIF
				IF dialogue_s3flag = 3
					IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s3
						IF HAS_MISSION_AUDIO_LOADED 1
							PLAY_MISSION_AUDIO 1
							PRINT_NOW SMO3_AB 5000 1 //Unity Station.
							dialogue_s3flag = 4
							
						ENDIF
					ENDIF

				
				ENDIF
				IF dialogue_s3flag = 4
					IF HAS_MISSION_AUDIO_FINISHED 1
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						PRINT_NOW SMK3_1 5000 1 //The Mexicans are meeting up at the Los Santos Central Train Station, go with Smoke and see what they are up to.
						dialogue_s3flag = 5
					ENDIF
				ENDIF

			ENDIF
		ENDIF
	ENDIF
ENDIF


IF outoffirstgroup_s3flag = 0
	IF smoke_s3flag = 1
	OR smoke_s3flag = 10

		IF smokegroupstation_s3flag = 0
			IF NOT IS_CAR_DEAD smokecar_s3
				IF NOT IS_CHAR_SITTING_IN_CAR scplayer smokecar_s3
					ADD_BLIP_FOR_CAR smokecar_s3 smokecar_s3blip
					SET_BLIP_AS_FRIENDLY smokecar_s3blip TRUE
					REMOVE_BLIP trainstation_s3blip
					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					//PRINT SMK3_19 5000 1 //Get back in the car!
					PLAY_MISSION_AUDIO 2
					PRINT_NOW SMOX_AD 2000 1 //Come on, playa, get in!
					smoke_s3flag = 10
					smokegroupstation_s3flag = 1
				ENDIF
			ENDIF
		ENDIF

		IF smokegroupstation_s3flag = 1
			IF NOT IS_CAR_DEAD smokecar_s3
				IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s3
					REMOVE_BLIP smokecar_s3blip
					ADD_BLIP_FOR_COORD 1783.211 -1933.133 12.597 trainstation_s3blip
					IF dialogue_s3flag = 5
						PRINT_NOW SMK3_1 12000 1 //The Mexicans are meeting up at the Los Santos Central Train Station, go with Smoke and see what they are up to.
					ENDIF
					smoke_s3flag = 1
					smokegroupstation_s3flag = 0
				ENDIF
			ENDIF
		ENDIF

	ENDIF	
ENDIF	

IF smoke_s3flag = 1
OR smoke_s3flag = 10
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW SMK3_21 5000 1 //~r~Smoke died!
		GOTO mission_smoke3_failed
	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////	Train station cutscene	////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

IF smoke_s3flag = 1
	IF NOT IS_CHAR_DEAD big_smoke
		IF NOT IS_CAR_DEAD smokecar_s3
			IF firstcutscene_s3flag = 0
				IF IS_CHAR_SITTING_IN_CAR big_smoke smokecar_s3
					IF IS_CHAR_SITTING_IN_CAR scplayer smokecar_s3
						IF LOCATE_CAR_2D smokecar_s3 1783.211 -1933.133 4.2 4.2 TRUE
							
							SWITCH_WIDESCREEN ON
							SET_PLAYER_CONTROL PLAYER1 OFF

							DO_FADE 500 FADE_OUT

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE

							IF NOT IS_CAR_DEAD smokecar_s3
								SET_CAR_COORDINATES smokecar_s3 1783.4182 -1933.0547 12.3862 
								SET_CAR_HEADING smokecar_s3 178.4615 
							ENDIF


							REQUEST_MODEL LSV1
							REQUEST_MODEL LSV2
							REQUEST_MODEL LSV3
							REQUEST_MODEL MP5LNG
							REQUEST_MODEL SANCHEZ
							REQUEST_MODEL TRNTRK8_LAS

							REQUEST_ANIMATION TRAIN
							REQUEST_ANIMATION GANGS

							CLEAR_MISSION_AUDIO 1
							LOAD_MISSION_AUDIO 1 SOUND_SMO3_BA //What we looking for, Smoke?
							CLEAR_MISSION_AUDIO 2
							LOAD_MISSION_AUDIO 2 SOUND_SMO3_BB //Some Vagos cats meeting some San Fierro Rifa and cutting some kind of deal..

							LOAD_SPECIAL_CHARACTER 1 SMOKE

							REQUEST_ANIMATION GANGS
							REQUEST_ANIMATION TRAIN

							LOAD_ALL_MODELS_NOW

							WHILE NOT HAS_ANIMATION_LOADED GANGS 
								OR NOT HAS_ANIMATION_LOADED TRAIN
								WAIT 0
							ENDWHILE

							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								OR NOT HAS_MISSION_AUDIO_LOADED 2
								WAIT 0
							ENDWHILE

							WHILE NOT HAS_MODEL_LOADED LSV1
								OR NOT HAS_MODEL_LOADED LSV2
								OR NOT HAS_MODEL_LOADED LSV3
								OR NOT HAS_MODEL_LOADED SANCHEZ
								OR NOT HAS_MODEL_LOADED MP5LNG
								WAIT 0
							ENDWHILE

							LOAD_SCENE 1684.53 -1948.48 20.66

							CLEAR_AREA 1682.6184 -1957.88 150.0 150.0 FALSE

							SET_CAR_DENSITY_MULTIPLIER 0.0
							SET_PED_DENSITY_MULTIPLIER 0.0

							outoffirstgroup_s3flag = 1

							REMOVE_BLIP trainstation_s3blip
							
							CLEAR_AREA 1699.31 -1938.799 100.0 100.0 FALSE
						
							//create object
							CREATE_OBJECT_NO_OFFSET IMMMCRAN 2194.438 -1912.756 11.907 crane_s3 //2194.684 -1913.144 15.361
							SET_OBJECT_HEADING crane_s3 0.0

							//create mexicans
							CREATE_CHAR PEDTYPE_MISSION1 LSV1 1682.546 -1959.222 20.99 mex1_s3 //create mexicans
							SET_CHAR_HEADING mex1_s3 0.0
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE mex1_s3 500.0
							SET_CHAR_PROOFS mex1_s3 TRUE TRUE TRUE TRUE TRUE
							SET_CHAR_DECISION_MAKER mex1_s3 smoke3mex_DM

							CREATE_CHAR PEDTYPE_MISSION1 LSV3 1682.546 -1958.222 20.99 mex2_s3	//-1958.756
							SET_CHAR_HEADING mex2_s3 180.0
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE mex2_s3 500.0
							SET_CHAR_PROOFS mex2_s3 TRUE TRUE TRUE TRUE TRUE
							SET_CHAR_DECISION_MAKER mex2_s3 smoke3mex_DM

							CREATE_CHAR PEDTYPE_MISSION1 LSV2 1681.038 -1958.03 20.99 mex3_s3
							SET_CHAR_HEADING mex3_s3 182.467
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE mex3_s3 500.0
							SET_CHAR_PROOFS mex3_s3 TRUE TRUE TRUE TRUE TRUE
							SET_CHAR_DECISION_MAKER mex3_s3 smoke3mex_DM

							CREATE_CHAR PEDTYPE_MISSION1 LSV3 1681.129 -1959.63 20.99 mex4_s3
							SET_CHAR_HEADING mex4_s3 6.22
							SET_FOLLOW_NODE_THRESHOLD_DISTANCE mex4_s3 500.0
							SET_CHAR_PROOFS mex4_s3 TRUE TRUE TRUE TRUE TRUE
							SET_CHAR_DECISION_MAKER mex4_s3 smoke3mex_DM


							firstcutscene_s3flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 1
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CAR_DEAD smokecar_s3

						CLEAR_AREA 1682.6184 -1957.8800 150.0 150.0 FALSE

						//set camera coords and position player and smoke
						SET_FIXED_CAMERA_POSITION 1780.7285 -1937.9547 13.4430 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 1781.3113 -1937.1478 13.3475  JUMP_CUT

						LOAD_SCENE 1687.04 -1946.6 21.79
						LOAD_SCENE_IN_DIRECTION 1735.16 -1945.29 14.44 88.46

						DO_FADE 750 FADE_IN

						PLAY_MISSION_AUDIO 1
						START_CHAR_FACIAL_TALK scplayer 10000
						PRINT_NOW SMO3_BA 5000 1 //What we looking for, Smoke?

						TIMERA = 0
						firstcutscene_s3flag = 2

					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 2
			IF TIMERA > 1000	
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CAR_DEAD smokecar_s3
						IF HAS_MISSION_AUDIO_FINISHED 1

							CLEAR_PRINTS
							CLEAR_MISSION_AUDIO 1
							STOP_CHAR_FACIAL_TALK scplayer
							LOAD_MISSION_AUDIO 1 SOUND_SMO3_BC	//San Fierro?

							PLAY_MISSION_AUDIO 2
							START_CHAR_FACIAL_TALK big_smoke 10000
							PRINT_NOW SMO3_BB 5000 1 //Some Vagos cats meeting some San Fierro Rifa and cutting some kind of deal..

							//////////////////////////////////////////////////////////////////////////////////////
							//////////////////////////////////////////////////////////////////////////////////////
							skipcutscene_s3flag = 0
							SKIP_CUTSCENE_START
							//////////////////////////////////////////////////////////////////////////////////////
							//////////////////////////////////////////////////////////////////////////////////////

							TASK_LEAVE_CAR big_smoke smokecar_s3
							WAIT 100
							IF NOT IS_CHAR_DEAD big_smoke
								TASK_LOOK_AT_CHAR big_smoke scplayer 3500
							ENDIF
							IF NOT IS_CAR_DEAD smokecar_s3
								TASK_LEAVE_CAR scplayer smokecar_s3
							ENDIF
							TIMERA = 0
							firstcutscene_s3flag = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 3
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CAR_DEAD smokecar_s3
						IF NOT IS_CHAR_IN_ANY_CAR big_smoke
							IF NOT IS_CHAR_IN_ANY_CAR scplayer

								SET_FIXED_CAMERA_POSITION 1778.6099 -1938.6750 12.9064 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 1779.2753 -1937.9441 13.0581 JUMP_CUT
								
								SET_CHAR_COORDINATES scplayer 1783.92 -1936.33 12.6
								SET_CHAR_HEADING scplayer 117.0
								TASK_GO_STRAIGHT_TO_COORD big_smoke 1775.3 -1939.23 12.9 PEDMOVE_WALK -1

								OPEN_SEQUENCE_TASK cutscene_s3seq
								TASK_LOOK_AT_CHAR -1 big_smoke 10000
								TASK_GO_STRAIGHT_TO_COORD -1 1776.63 -1940.18 13.66 PEDMOVE_WALK -1 //1777.098 -1940.277 12.609
   								CLOSE_SEQUENCE_TASK cutscene_s3seq
								PERFORM_SEQUENCE_TASK scplayer cutscene_s3seq
								CLEAR_SEQUENCE_TASK cutscene_s3seq

								TIMERA = 0

								firstcutscene_s3flag = 4

							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 4
			IF NOT IS_CHAR_DEAD scplayer
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CHAR_DEAD mex1_s3
						IF NOT IS_CHAR_DEAD mex2_s3
							IF NOT IS_CHAR_DEAD mex3_s3
								IF NOT IS_CHAR_DEAD mex4_s3
									IF NOT IS_CAR_DEAD smokecar_s3
										IF NOT IS_CHAR_SITTING_IN_ANY_CAR big_smoke
											IF NOT IS_CHAR_SITTING_IN_ANY_CAR scplayer
												IF HAS_MISSION_AUDIO_FINISHED 2
													IF TIMERA > 3000
														PLAY_MISSION_AUDIO 1
														START_CHAR_FACIAL_TALK scplayer 10000
														PRINT_NOW SMO3_BC 5000 1	//San Fierro?

														SET_FIXED_CAMERA_POSITION 1732.4696 -1945.0454 12.9171 0.0 0.0 0.0
														POINT_CAMERA_AT_POINT 1731.4921 -1945.1290 13.1107 JUMP_CUT

														SET_CHAR_COORDINATES big_smoke 1737.2 -1944.2 12.6
														SET_CHAR_HEADING big_smoke 88.5

														SET_CHAR_COORDINATES scplayer 1737.2 -1945.7 12.6
														SET_CHAR_HEADING scplayer 88.5

														TASK_GO_STRAIGHT_TO_COORD big_smoke 1718.04 -1944.2 13.6 PEDMOVE_WALK -1 //x is lower to walk further away
														TASK_GO_STRAIGHT_TO_COORD scplayer 1718.04 -1945.7 13.6 PEDMOVE_WALK -1

														CLEAR_MISSION_AUDIO 2
														LOAD_MISSION_AUDIO 2 SOUND_SMO3_BD //I thought Northern Mexicans don’t mix with Los Santos esse’s.

														TIMERA = 0
														firstcutscene_s3flag = 5
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

		IF firstcutscene_s3flag = 5
			IF HAS_MISSION_AUDIO_FINISHED 1	

				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				STOP_CHAR_FACIAL_TALK scplayer
				LOAD_MISSION_AUDIO 1 SOUND_SMO3_BE //You got me.

				PLAY_MISSION_AUDIO 2
				START_CHAR_FACIAL_TALK scplayer 10000
				PRINT_NOW SMO3_BD 5000 1 //I thought Northern Mexicans don't mix with Los Santos esse's.

				firstcutscene_s3flag = 6
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 6
			IF HAS_MISSION_AUDIO_FINISHED 2

				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 2
				STOP_CHAR_FACIAL_TALK scplayer
				LOAD_MISSION_AUDIO 2 SOUND_SMO3_BF	//That look like them!
				IF NOT IS_CHAR_DEAD big_smoke
					PLAY_MISSION_AUDIO 1
					START_CHAR_FACIAL_TALK big_smoke 3000
					PRINT_NOW SMO3_BE 5000 1	//You got me.
				ENDIF
				TIMERA = 0 //added
				firstcutscene_s3flag = 7
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 7
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD mex1_s3
					IF NOT IS_CHAR_DEAD mex2_s3
						IF NOT IS_CHAR_DEAD mex3_s3
							IF NOT IS_CHAR_DEAD mex4_s3
								IF TIMERA > 1500 //added was 5500
									IF HAS_MISSION_AUDIO_FINISHED 1	

										//SET_FIXED_CAMERA_POSITION 1679.9171 -1959.6146 22.7919 0.0 0.0 0.0
										//POINT_CAMERA_AT_POINT 1680.7834 -1959.1910 22.5273 JUMP_CUT

										SET_FIXED_CAMERA_POSITION 1726.0277 -1944.9240 13.9132 0.0 0.0 0.0
										POINT_CAMERA_AT_POINT 1725.0594 -1945.1676 13.9694 JUMP_CUT

										TASK_PLAY_ANIM mex2_s3 hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1
										TASK_PLAY_ANIM mex1_s3 hndshkfa GANGS 4.0 FALSE FALSE FALSE FALSE -1

										TASK_PLAY_ANIM mex4_s3 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
										TASK_PLAY_ANIM mex3_s3 prtial_gngtlkG GANGS 4.0 TRUE FALSE FALSE FALSE -1

										CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
										SET_CHAR_COORDINATES scplayer 1724.08 -1946.27 12.5
										SET_CHAR_HEADING scplayer 86.93

										CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
										SET_CHAR_COORDINATES big_smoke 1723.93 -1944.52 12.5
										SET_CHAR_HEADING big_smoke 93.27

										PLAY_MISSION_AUDIO 2
										START_CHAR_FACIAL_TALK scplayer 10000
										PRINT_NOW SMO3_BF 5000 1	//That look like them!

										CLEAR_MISSION_AUDIO 1
										LOAD_MISSION_AUDIO 1 SOUND_SMO3_BG	//Motherfuckers clocked us!
										TIMERA = 0
										firstcutscene_s3flag = 8
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 8
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD mex1_s3
					IF NOT IS_CHAR_DEAD mex2_s3
						IF NOT IS_CHAR_DEAD mex3_s3
							IF NOT IS_CHAR_DEAD mex4_s3
								GET_SCRIPT_TASK_STATUS mex1_s3 TASK_PLAY_ANIM taskstatus_s3
  								IF taskstatus_s3 = FINISHED_TASK
								OR TIMERA > 1750
									TASK_LOOK_AT_CHAR mex1_s3 big_smoke 10000
									IF NOT IS_CHAR_DEAD mex2_s3
										TASK_LOOK_AT_CHAR mex2_s3 scplayer 10000
									ENDIF
									IF NOT IS_CHAR_DEAD mex3_s3
										TASK_LOOK_AT_CHAR mex3_s3 scplayer 10000
									ENDIF
									CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
									TIMERA = 0
									firstcutscene_s3flag = 9
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 9
			IF NOT IS_CHAR_DEAD big_smoke
				IF NOT IS_CHAR_DEAD mex1_s3
					IF NOT IS_CHAR_DEAD mex2_s3
						IF NOT IS_CHAR_DEAD mex3_s3
							IF NOT IS_CHAR_DEAD mex4_s3
								IF HAS_MISSION_AUDIO_FINISHED 2
									IF HAS_MISSION_AUDIO_LOADED 1
										IF TIMERA > 0
											CLEAR_MISSION_AUDIO 2
											LOAD_MISSION_AUDIO 2 SOUND_SMO3_BH //We got to get those fools!

											SET_FIXED_CAMERA_POSITION 1721.3442 -1943.8755 13.8732 0.0 0.0 0.0
											POINT_CAMERA_AT_POINT 1722.2112 -1944.3728 13.8443 JUMP_CUT
											TASK_LOOK_AT_CHAR scplayer big_smoke 10000
					
											PLAY_MISSION_AUDIO 1
											START_CHAR_FACIAL_TALK big_smoke 3000
											PRINT_NOW SMO3_BG 5000 1	//Motherfuckers clocked us!
											TASK_PLAY_ANIM big_smoke prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE FALSE -1
											TIMERB = 0
											firstcutscene_s3flag = 10

										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		IF firstcutscene_s3flag = 10
			IF NOT IS_CHAR_DEAD big_smoke
				IF HAS_MISSION_AUDIO_FINISHED 1	
					CLEAR_PRINTS
					CREATE_CAR SANCHEZ 1778.6237 -1939.9049 12.5598 bike_s3
					SET_CAR_HEADING bike_s3 176.7993 
					SET_CAR_HEALTH bike_s3 10000
					SET_CAN_BURST_CAR_TYRES bike_s3 FALSE
					SET_CAR_PROOFS bike_s3 TRUE TRUE TRUE TRUE TRUE
					CHANGE_CAR_COLOUR bike_s3 6 6
					bikecreated_s3flag = 1

					IF NOT IS_CAR_DEAD smokecar_s3
						SET_CAR_HEALTH smokecar_s3 10000
						SET_CAN_BURST_CAR_TYRES smokecar_s3 FALSE
					ENDIF

					IF NOT IS_CHAR_DEAD mex1_s3
						CLEAR_CHAR_TASKS_IMMEDIATELY mex1_s3
						SET_CHAR_COORDINATES mex1_s3 1682.254 -1953.64 20.985
						SET_CHAR_HEADING mex1_s3 266.36
						TASK_GO_STRAIGHT_TO_COORD mex1_s3 1799.49 -1953.73 12.23 PEDMOVE_RUN 50000
					ENDIF

					IF NOT IS_CHAR_DEAD mex2_s3
						CLEAR_CHAR_TASKS_IMMEDIATELY mex2_s3
						SET_CHAR_COORDINATES mex2_s3 1679.256 -1953.635 20.985 //9
						SET_CHAR_HEADING mex2_s3 266.36
						TASK_GO_STRAIGHT_TO_COORD mex2_s3 1799.49 -1953.73 12.23 PEDMOVE_RUN 50000
					ENDIF

					WAIT 250

					IF NOT IS_CHAR_DEAD mex3_s3
						CLEAR_CHAR_TASKS_IMMEDIATELY mex3_s3
						SET_CHAR_COORDINATES mex3_s3 1676.74 -1953.59 20.985 //1676.11 -1953.64 20.985//1676.256 -1953.635 20.985//6
						SET_CHAR_HEADING mex3_s3 266.36
						TASK_GO_STRAIGHT_TO_COORD mex3_s3 1799.49 -1953.73 12.23 PEDMOVE_RUN 50000
					ENDIF

					WAIT 250

					IF NOT IS_CHAR_DEAD mex4_s3
						CLEAR_CHAR_TASKS_IMMEDIATELY mex4_s3
						SET_CHAR_COORDINATES mex4_s3 1675.3 -1953.67 20.985 //1675.4 -1953.23 20.953 //1675.256 -1953.735 20.985
						SET_CHAR_HEADING mex4_s3 266.389//266.36
						TASK_GO_STRAIGHT_TO_COORD mex4_s3 1799.49 -1953.73 12.23 PEDMOVE_RUN 50000
					ENDIF

					IF NOT IS_CAR_DEAD train_s3
						SET_TRAIN_CRUISE_SPEED train_s3 15.0	//5.0
						trainismoving_s3flag = 1
					ENDIF

					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_PERSIST_TRACK TRUE                   
					CAMERA_PERSIST_POS TRUE                       
					CAMERA_SET_VECTOR_MOVE 1703.5477 -1951.4958 16.9714 1750.5477 -1951.4958 16.9714 10000 TRUE
					CAMERA_SET_VECTOR_TRACK 1702.5494 -1951.5137 16.9159 1702.5494 -1951.5137 16.9159 10000 TRUE
					TIMERB = 0

					IF NOT IS_CHAR_DEAD big_smoke
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
						SET_CHAR_COORDINATES scplayer 1715.0 -1946.79 12.6
						SET_CHAR_COORDINATES big_smoke 1712.36 -1944.47 12.6
					ENDIF
					firstcutscene_s3flag = 11

				ENDIF					  //
			ENDIF
		ENDIF

//timertest = TIMERB
//VIEW_INTEGER_VARIABLE timertest timertest

		IF firstcutscene_s3flag > 10
			IF firstcutscene_s3flag < 14
				IF NOT IS_CHAR_DEAD big_smoke
					IF NOT IS_CHAR_DEAD mex1_s3
						IF NOT IS_CHAR_DEAD mex2_s3
							IF NOT IS_CHAR_DEAD mex3_s3
								IF NOT IS_CHAR_DEAD mex4_s3							
									IF NOT IS_CAR_DEAD carriage1_s3
										IF NOT IS_CAR_DEAD carriage2_s3


												GET_OFFSET_FROM_CAR_IN_WORLD_COORDS carriage1_s3 0.0 9.84 3.0 carriage1x_s3 carriage1y_s3 carriage1z_s3
												GET_OFFSET_FROM_CAR_IN_WORLD_COORDS carriage2_s3 0.0 9.84 3.0 carriage2x_s3 carriage2y_s3 carriage2z_s3
												IF NOT firstcutscene_s3flag > 11
													GET_CHAR_COORDINATES mex2_s3 player_x player_y player_z
													POINT_CAMERA_AT_POINT player_x player_y 18.0 JUMP_CUT
												ENDIF

												IF mex1jump_s3flag = 0
													IF LOCATE_CHAR_ANY_MEANS_3D mex1_s3 carriage2x_s3 carriage2y_s3 carriage2z_s3 2.0 2.0 2.0 FALSE
														TASK_PLAY_ANIM scplayer FUCKU PED 4.0 FALSE FALSE FALSE FALSE -1
														enemy_s3 = mex1_s3
														GOSUB jump_s3label
														mex1jump_s3flag = 1
													ENDIF
												ENDIF
												IF mex1jump_s3flag = 1
													IF LOCATE_CHAR_ANY_MEANS_3D mex1_s3 carriage1x_s3 carriage1y_s3 carriage1z_s3 2.0 2.0 2.0 FALSE
														enemy_s3 = mex1_s3
														GOSUB jump_s3label
														mex1jump_s3flag = 2
													ENDIF
												ENDIF
												
												IF mex2jump_s3flag = 0
													IF LOCATE_CHAR_ANY_MEANS_3D mex2_s3 carriage2x_s3 carriage2y_s3 carriage2z_s3 2.0 2.0 2.0 FALSE
														enemy_s3 = mex2_s3
														GOSUB jump_s3label
														mex2jump_s3flag = 1
													ENDIF
												ENDIF
												IF mex2jump_s3flag = 1
													IF LOCATE_CHAR_ANY_MEANS_3D mex2_s3 carriage1x_s3 carriage1y_s3 carriage1z_s3 2.0 2.0 2.0 FALSE
														enemy_s3 = mex2_s3
														GOSUB jump_s3label
														mex2jump_s3flag = 2
													ENDIF
												ENDIF
												
												IF mex3jump_s3flag = 0
													IF LOCATE_CHAR_ANY_MEANS_3D mex3_s3 carriage2x_s3 carriage2y_s3 carriage2z_s3 2.0 2.0 2.0 FALSE
														enemy_s3 = mex3_s3
														GOSUB jump_s3label
														mex3jump_s3flag = 1
													ENDIF
												ENDIF
												IF mex3jump_s3flag = 1
													IF LOCATE_CHAR_ANY_MEANS_3D mex3_s3 carriage1x_s3 carriage1y_s3 carriage1z_s3 2.0 2.0 2.0 FALSE
														enemy_s3 = mex3_s3
														GOSUB jump_s3label
														mex3jump_s3flag = 2
													ENDIF
												ENDIF
									
												IF mex4jump_s3flag = 0
													IF LOCATE_CHAR_ANY_MEANS_3D mex4_s3 carriage2x_s3 carriage2y_s3 carriage2z_s3 2.0 2.0 2.0 FALSE
														enemy_s3 = mex4_s3
														GOSUB jump_s3label
														mex4jump_s3flag = 1
													ENDIF
												ENDIF
												IF mex4jump_s3flag = 1
													IF LOCATE_CHAR_ANY_MEANS_3D mex4_s3 carriage1x_s3 carriage1y_s3 carriage1z_s3 2.0 2.0 2.0 FALSE
														enemy_s3 = mex4_s3
														GOSUB jump_s3label
														mex4jump_s3flag = 2
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

		
		IF firstcutscene_s3flag = 11
			IF TIMERB > 4200
				IF NOT IS_CHAR_DEAD big_smoke
					TASK_GO_STRAIGHT_TO_COORD big_smoke 1765.99 -1944.25 13.75 PEDMOVE_RUN 100000
					TASK_GO_STRAIGHT_TO_COORD scplayer 1771.1 -1946.12 13.75 PEDMOVE_RUN 100000
					PLAY_MISSION_AUDIO 2
					PRINT_NOW SMO3_BH 5000 1//We got to get those fools!

					firstcutscene_s3flag = 12
				ENDIF	
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 12
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_MISSION_AUDIO 2
				CLEAR_PRINTS
				firstcutscene_s3flag = 13
			ENDIF
		ENDIF 

		IF firstcutscene_s3flag = 13
			IF TIMERB > 8000
				DO_FADE 500 FADE_OUT

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				firstcutscene_s3flag = 14
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 14
			IF NOT IS_CAR_DEAD train_s3
				IF NOT IS_CHAR_DEAD big_smoke
					
//					SET_TRAIN_SPEED train_s3 25.0
					SET_MISSION_TRAIN_COORDINATES train_s3 1880.06 -1953.67 12.44
					SET_TRAIN_CRUISE_SPEED train_s3 10.0

					DELETE_CHAR mex1_s3
					DELETE_CHAR mex2_s3
					DELETE_CHAR mex3_s3
					DELETE_CHAR mex4_s3

					CREATE_CHAR PEDTYPE_MISSION1 LSV1 1682.6184 -1957.8800 20.9453 mex1_s3 //create mexicans
					enemy_s3 = mex1_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex1_s3 FALSE

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 1682.99 -1947.17 20.03 mex2_s3
					enemy_s3 = mex2_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex2_s3 FALSE

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 1681.6184 -1957.8800 20.9453 mex3_s3
					enemy_s3 = mex3_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex3_s3 FALSE

					CREATE_CHAR PEDTYPE_MISSION4 LSV3 1682.5 -1945.17 20.033 mex4_s3
					enemy_s3 = mex4_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex4_s3 FALSE

					SET_CHAR_PROOFS mex1_s3 FALSE FALSE TRUE FALSE FALSE
					SET_CHAR_PROOFS mex2_s3 FALSE FALSE TRUE FALSE FALSE
					SET_CHAR_PROOFS mex3_s3 FALSE FALSE TRUE FALSE FALSE

					ATTACH_CHAR_TO_CAR mex1_s3 train_s3 0.0 5.0 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5
					ATTACH_CHAR_TO_CAR mex2_s3 train_s3 0.0 2.0 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5
					ATTACH_CHAR_TO_CAR mex3_s3 train_s3 0.0 -3.0 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5
					ATTACH_CHAR_TO_CAR mex4_s3 train_s3 0.0 -6.1 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5

					SET_CHAR_DECISION_MAKER mex1_s3 smoke3mex_DM
					SET_CHAR_DECISION_MAKER mex2_s3 smoke3mex_DM
					SET_CHAR_DECISION_MAKER mex3_s3 smoke3mex_DM
					SET_CHAR_DECISION_MAKER mex4_s3 emptysmoke3_DM

					SET_CHAR_ACCURACY mex1_s3 10
					SET_CHAR_ACCURACY mex2_s3 10
					SET_CHAR_ACCURACY mex3_s3 10
					SET_CHAR_ACCURACY mex4_s3 10

					SET_CHAR_SHOOT_RATE mex1_s3 20
					SET_CHAR_SHOOT_RATE mex2_s3 20
					SET_CHAR_SHOOT_RATE mex3_s3	30
					SET_CHAR_SHOOT_RATE	mex4_s3 20

					IF difficulty_s3flag = 0
						SET_CHAR_MAX_HEALTH mex1_s3 500
						SET_CHAR_MAX_HEALTH mex2_s3 500
						SET_CHAR_MAX_HEALTH mex3_s3 500
						SET_CHAR_HEALTH mex1_s3 500
						SET_CHAR_HEALTH mex2_s3	500
						SET_CHAR_HEALTH mex3_s3 500
					ENDIF
					IF difficulty_s3flag = 1
						SET_CHAR_MAX_HEALTH mex1_s3 480
						SET_CHAR_MAX_HEALTH mex2_s3 500
						SET_CHAR_MAX_HEALTH mex3_s3 450
						SET_CHAR_HEALTH mex1_s3 480
						SET_CHAR_HEALTH mex2_s3	500
						SET_CHAR_HEALTH mex3_s3 450
					ENDIF
					IF difficulty_s3flag = 2
						SET_CHAR_MAX_HEALTH mex1_s3 450
						SET_CHAR_MAX_HEALTH mex2_s3 450
						SET_CHAR_MAX_HEALTH mex3_s3 450
						SET_CHAR_HEALTH mex1_s3 450
						SET_CHAR_HEALTH mex2_s3	450
						SET_CHAR_HEALTH mex3_s3 450
					ENDIF
					IF difficulty_s3flag = 3
						SET_CHAR_MAX_HEALTH mex1_s3 350
						SET_CHAR_MAX_HEALTH mex2_s3 350
						SET_CHAR_MAX_HEALTH mex3_s3 350
						SET_CHAR_HEALTH mex1_s3 350
						SET_CHAR_HEALTH mex2_s3	350
						SET_CHAR_HEALTH mex3_s3 350
					ENDIF
					IF difficulty_s3flag > 3
						SET_CHAR_MAX_HEALTH mex1_s3 300
						SET_CHAR_MAX_HEALTH mex2_s3 300
						SET_CHAR_MAX_HEALTH mex3_s3 300
						SET_CHAR_HEALTH mex1_s3 300
						SET_CHAR_HEALTH mex2_s3	300
						SET_CHAR_HEALTH mex3_s3 300
					ENDIF

					SET_CHAR_PROOFS mex4_s3 TRUE TRUE TRUE TRUE TRUE

					ADD_BLIP_FOR_CHAR mex1_s3 mex1_s3blip
					ADD_BLIP_FOR_CHAR mex2_s3 mex2_s3blip
					ADD_BLIP_FOR_CHAR mex3_s3 mex3_s3blip
					ADD_BLIP_FOR_CHAR mex4_s3 mex4_s3blip

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke

					TIMERA = 0
					firstcutscene_s3flag = 15
				ENDIF
			ENDIF
		ENDIF

		IF firstcutscene_s3flag = 15
			IF NOT IS_CHAR_DEAD big_smoke

				//////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////
				skipcutscene_s3flag = 1
				SKIP_CUTSCENE_END
				//////////////////////////////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////////////////////////////

				//PLAYER HAS SKIPPED CUTSCENE
				IF skipcutscene_s3flag = 0
					
					DO_FADE 0 FADE_OUT

					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE

					CLEAR_PRINTS
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2

					DELETE_CHAR mex1_s3
					DELETE_CHAR mex2_s3
					DELETE_CHAR mex3_s3
					DELETE_CHAR mex4_s3
					REMOVE_BLIP mex1_s3blip
					REMOVE_BLIP mex2_s3blip
					REMOVE_BLIP mex3_s3blip
					REMOVE_BLIP mex4_s3blip
					DELETE_MISSION_TRAINS

					//create object
					DELETE_OBJECT crane_s3
					CREATE_OBJECT_NO_OFFSET IMMMCRAN 2194.438 -1912.756 11.907 crane_s3 //2194.684 -1913.144 15.361
					SET_OBJECT_HEADING crane_s3 0.0

					CREATE_MISSION_TRAIN 11 1880.06 -1953.67 12.44 FALSE train_s3 //1725 was 30
					SET_TRAIN_FORCED_TO_SLOW_DOWN train_s3 FALSE
					SET_TRAIN_SPEED train_s3 10.0
					SET_TRAIN_CRUISE_SPEED train_s3 10.0
					GET_TRAIN_CARRIAGE train_s3 1 carriage1_s3
					GET_TRAIN_CARRIAGE train_s3 2 carriage2_s3
					SET_CAR_PROOFS train_s3 TRUE TRUE TRUE TRUE TRUE
					LOCK_CAR_DOORS train_s3 CARLOCK_LOCKED
					LOCK_CAR_DOORS carriage1_s3 CARLOCK_LOCKED
					LOCK_CAR_DOORS carriage2_s3 CARLOCK_LOCKED
					SET_CAR_PROOFS carriage1_s3 TRUE TRUE TRUE TRUE TRUE
					SET_CAR_PROOFS carriage2_s3 TRUE TRUE TRUE TRUE TRUE

					//create mexicans
					CREATE_CHAR PEDTYPE_MISSION1 LSV1 1682.6184 -1957.8800 20.9453 mex1_s3 //create mexicans
					enemy_s3 = mex1_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex1_s3 FALSE

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 1682.99 -1947.17 20.03 mex2_s3
					enemy_s3 = mex2_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex2_s3 FALSE

					CREATE_CHAR PEDTYPE_MISSION1 LSV3 1681.6184 -1957.8800 20.9453 mex3_s3
					enemy_s3 = mex3_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex3_s3 FALSE

					CREATE_CHAR PEDTYPE_MISSION4 LSV3 1682.5 -1945.17 20.033 mex4_s3
					enemy_s3 = mex4_s3
					GOSUB ontopoftrain_labels3
					SET_CHAR_SUFFERS_CRITICAL_HITS mex4_s3 FALSE

					SET_CHAR_PROOFS mex1_s3 FALSE FALSE TRUE FALSE FALSE
					SET_CHAR_PROOFS mex2_s3 FALSE FALSE TRUE FALSE FALSE
					SET_CHAR_PROOFS mex3_s3 FALSE FALSE TRUE FALSE FALSE

					ATTACH_CHAR_TO_CAR mex1_s3 train_s3 0.0 5.0 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5
					ATTACH_CHAR_TO_CAR mex2_s3 train_s3 0.0 2.0 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5
					ATTACH_CHAR_TO_CAR mex3_s3 train_s3 0.0 -3.0 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5
					ATTACH_CHAR_TO_CAR mex4_s3 train_s3 0.0 -6.1 3.0 FACING_FORWARD 360.0 WEAPONTYPE_MP5

					SET_CHAR_DECISION_MAKER mex1_s3 smoke3mex_DM
					SET_CHAR_DECISION_MAKER mex2_s3 smoke3mex_DM
					SET_CHAR_DECISION_MAKER mex3_s3 smoke3mex_DM
					SET_CHAR_DECISION_MAKER mex4_s3 emptysmoke3_DM

					SET_CHAR_ACCURACY mex1_s3 10
					SET_CHAR_ACCURACY mex2_s3 10
					SET_CHAR_ACCURACY mex3_s3 10
					SET_CHAR_ACCURACY mex4_s3 10

					SET_CHAR_SHOOT_RATE mex1_s3 20
					SET_CHAR_SHOOT_RATE mex2_s3 20
					SET_CHAR_SHOOT_RATE mex3_s3	30
					SET_CHAR_SHOOT_RATE	mex4_s3 20

					IF difficulty_s3flag = 0
						SET_CHAR_MAX_HEALTH mex1_s3 500
						SET_CHAR_MAX_HEALTH mex2_s3 500
						SET_CHAR_MAX_HEALTH mex3_s3 500
						SET_CHAR_HEALTH mex1_s3 500
						SET_CHAR_HEALTH mex2_s3	500
						SET_CHAR_HEALTH mex3_s3 500
					ENDIF
					IF difficulty_s3flag = 1
						SET_CHAR_MAX_HEALTH mex1_s3 480
						SET_CHAR_MAX_HEALTH mex2_s3 500
						SET_CHAR_MAX_HEALTH mex3_s3 450
						SET_CHAR_HEALTH mex1_s3 480
						SET_CHAR_HEALTH mex2_s3	500
						SET_CHAR_HEALTH mex3_s3 450
					ENDIF
					IF difficulty_s3flag = 2
						SET_CHAR_MAX_HEALTH mex1_s3 450
						SET_CHAR_MAX_HEALTH mex2_s3 450
						SET_CHAR_MAX_HEALTH mex3_s3 450
						SET_CHAR_HEALTH mex1_s3 450
						SET_CHAR_HEALTH mex2_s3	450
						SET_CHAR_HEALTH mex3_s3 450
					ENDIF
					IF difficulty_s3flag = 3
						SET_CHAR_MAX_HEALTH mex1_s3 300
						SET_CHAR_MAX_HEALTH mex2_s3 350
						SET_CHAR_MAX_HEALTH mex3_s3 350
						SET_CHAR_HEALTH mex1_s3 300
						SET_CHAR_HEALTH mex2_s3	350
						SET_CHAR_HEALTH mex3_s3 350
					ENDIF
					IF difficulty_s3flag > 3
						SET_CHAR_MAX_HEALTH mex1_s3 300
						SET_CHAR_MAX_HEALTH mex2_s3 300
						SET_CHAR_MAX_HEALTH mex3_s3 300
						SET_CHAR_HEALTH mex1_s3 300
						SET_CHAR_HEALTH mex2_s3	300
						SET_CHAR_HEALTH mex3_s3 300
					ENDIF


					ADD_BLIP_FOR_CHAR mex1_s3 mex1_s3blip
					ADD_BLIP_FOR_CHAR mex2_s3 mex2_s3blip
					ADD_BLIP_FOR_CHAR mex3_s3 mex3_s3blip
					ADD_BLIP_FOR_CHAR mex4_s3 mex4_s3blip

					IF bikecreated_s3flag = 0
						CREATE_CAR SANCHEZ 1778.6237 -1939.9049 12.5598 bike_s3
						SET_CAR_HEADING bike_s3 176.7993 
						SET_CAR_HEALTH bike_s3 10000
						SET_CAR_PROOFS bike_s3 TRUE TRUE TRUE TRUE TRUE
						SET_CAN_BURST_CAR_TYRES bike_s3 FALSE
						CHANGE_CAR_COLOUR bike_s3 6 6
					ENDIF

					IF NOT IS_CAR_DEAD smokecar_s3
						SET_CAR_HEALTH smokecar_s3 10000
						SET_CAN_BURST_CAR_TYRES smokecar_s3 FALSE
					ENDIF
					SET_CHAR_PROOFS mex4_s3 TRUE TRUE TRUE TRUE TRUE

					IF NOT IS_CHAR_DEAD big_smoke
						IF IS_CHAR_IN_ANY_CAR big_smoke
							WARP_CHAR_FROM_CAR_TO_COORD big_smoke 1772.918 -1941.495 12.567
							SET_CHAR_HEADING big_smoke 269.216
						ELSE
							SET_CHAR_COORDINATES big_smoke 1772.918 -1941.495 12.567
							SET_CHAR_HEADING big_smoke 269.216
						ENDIF
					ENDIF

					IF IS_CHAR_IN_ANY_CAR scplayer
						WARP_CHAR_FROM_CAR_TO_COORD scplayer 1774.065 -1943.003 12.558
						SET_CHAR_HEADING scplayer 263.195
					ELSE
						SET_CHAR_COORDINATES scplayer 1774.065 -1943.003 12.558
						SET_CHAR_HEADING scplayer 263.195			
					ENDIF
				
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					SWITCH_WIDESCREEN OFF
				ENDIF

				IF NOT IS_CHAR_DEAD big_smoke
					SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
					SET_SCRIPT_LIMIT_TO_GANG_SIZE 1
					SET_CHAR_COORDINATES scplayer 1774.065 -1943.003 12.558 //1767.06 -1942.17 12.573
					SET_CHAR_HEADING scplayer 263.195 //263.195

					IF NOT IS_CHAR_DEAD big_smoke
						SET_CHAR_COORDINATES big_smoke 1772.918 -1941.495 12.567 //1765.614 -1941.731 12.57
						SET_CHAR_HEADING big_smoke 269.216 //269.216
					ENDIF

					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
					CAMERA_RESET_NEW_SCRIPTABLES
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER

					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
					SET_GROUP_MEMBER Players_Group big_smoke
					SET_GROUP_SEPARATION_RANGE Players_Group 30.0	////////////////////////////////////////////////////////////
					LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM groupsmoke3_DM
					CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE groupsmoke3_DM EVENT_DAMAGE
					CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE groupsmoke3_DM EVENT_VEHICLE_DAMAGE_WEAPON
					CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE groupsmoke3_DM EVENT_ACQUAINTANCE_PED_HATE
					SET_GROUP_DECISION_MAKER Players_Group groupsmoke3_DM
					SET_CHAR_RELATIONSHIP big_smoke ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
					SET_CHAR_DECISION_MAKER big_smoke smoke3_DM 
					TASK_TOGGLE_PED_THREAT_SCANNER big_smoke FALSE TRUE FALSE
					ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE groupsmoke3_DM EVENT_ACQUAINTANCE_PED_HATE TASK_GROUP_USE_MEMBER_DECISION 0.0 100.0 0.0 0.0 TRUE FALSE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE smoke3_DM EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE

					SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS PLAYER1 TRUE

					SET_CAMERA_BEHIND_PLAYER
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL PLAYER1 ON
					SET_CAR_DENSITY_MULTIPLIER 0.5
					SET_PED_DENSITY_MULTIPLIER 0.5
				ENDIF

				CLEAR_PRINTS

				IF NOT IS_CAR_DEAD bike_s3
					ADD_BLIP_FOR_CAR bike_s3 bike_s3blip
					SET_BLIP_AS_FRIENDLY bike_s3blip TRUE
				ENDIF

				IF NOT IS_CHAR_DEAD mex4_s3
					SET_CHAR_ACCURACY mex4_s3 20
					enemy_s3 = mex4_s3
					GOSUB killsmoke_s3label
				ENDIF

				SWITCH_ROADS_OFF 2261.14 -1141.45 18.0 2308.19 -1156.38 35.0 
				SWITCH_ROADS_OFF 2270.69 -1391.75 20.28 2315.81 -1375.58 26.88
				SWITCH_ROADS_OFF 2257.3 -1491.95 20.63  2299.7 -1477.32 26.12
				SWITCH_ROADS_OFF 2235.25 -1656.59 12.68 2176.75 -1629.89 15.0
				SWITCH_ROADS_OFF 2221.43 -1721.07 10.09 2174.01 -172.72 15.73
				SWITCH_ROADS_OFF 2234.09 -1723.3 14.0 2178.22 -1742.47 10.0

				SWITCH_ROADS_OFF 2261.14 -1141.45 18.0 2308.19 -1156.38 35.0
				SWITCH_ROADS_OFF 1956.91 -1936.01 10.07 1965.61 -1978.99 14.45
				SWITCH_ROADS_OFF 2181.3 -1892.0 10.0 222.36 -1897.71 15.0


				PRINT_NOW SMK3_2 7000 1 //~s~Get on the motorbike and chase down the ~r~Vagos gang members ~s~and take them out!
				REMOVE_ANIMATION GANGS
				MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
				trainspeed_s3flag = 1
				smoke_s3flag = 2
				difficulty_s3flag++ //increment difficulty
				smokeincar_s3flag = 1
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE smoke3mex_DM EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE
				DO_FADE 1000 FADE_IN
				MARK_CAR_AS_NO_LONGER_NEEDED smokecar_s3
				SET_VEHICLE_CAMERA_TWEAK SANCHEZ 1.0 1.0 0.12
				MARK_MODEL_AS_NO_LONGER_NEEDED TRNTRK8_LAS
				firstcutscene_s3flag = 14

			ENDIF
		ENDIF
		

	ENDIF
ENDIF


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////// Group stuff and blip swapping for chase	////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


IF smokeincar_s3flag = 1
	IF NOT IS_CHAR_DEAD big_smoke
		IF IS_CHAR_SITTING_IN_ANY_CAR big_smoke
			REMOVE_BLIP bike_s3blip
			GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_TEC9 99999
			SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_TEC9
			SET_CHAR_ACCURACY big_smoke 70
			smokeincar_s3flag = 2
		ENDIF
	ENDIF
ENDIF

IF firstcutscene_s3flag = 14
	IF playerinbike_s3flag = 0
		IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
			REMOVE_BLIP bike_s3blip
			SET_PLAYER_IN_CAR_CAMERA_MODE ZOOM_THREE
			playerinbike_s3flag = 1
		ENDIF
	ENDIF
ENDIF

IF smoke_s3flag = 2
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////   Chase	////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	///////////////////////////////////////////////////////////////////////////		car coming onto the track
	IF blockcar_s3flag < 2

		IF blockcar_s3flag = 0
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2272.57 -1494.89 40.0 40.0 FALSE 
				CREATE_CAR GLENDALE 2295.777 -1481.268 22.91 blockcar_s3
				CREATE_CHAR_INSIDE_CAR blockcar_s3 PEDTYPE_CIVMALE LSV1 blockcardriver_s3
				SET_CAR_HEADING blockcar_s3 88.47
				CAR_GOTO_COORDINATES_ACCURATE blockcar_s3 2280.45 -1480.961 22.483
				SET_CAR_CRUISE_SPEED blockcar_s3 6.5
				SET_CAR_DRIVING_STYLE blockcar_s3 DRIVINGMODE_STOPFORCARS
				blockcar_s3flag = 1
			ENDIF
		ENDIF

		IF blockcar_s3flag = 1
			IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2280.45 -1480.961 50.0 50.0 FALSE
				MARK_CAR_AS_NO_LONGER_NEEDED blockcar_s3
				MARK_CHAR_AS_NO_LONGER_NEEDED blockcardriver_s3
				MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
				blockcar_s3flag = 2
			ENDIF
		ENDIF

	ENDIF


	IF NOT IS_CAR_DEAD train_s3


//		VIEW_INTEGER_VARIABLE difficulty_s3flag difficulty_s3flag
//		VIEW_FLOAT_VARIABLE x x
//		VIEW_FLOAT_VARIABLE y y

		//train speeds
	    IF trainspeed_s3flag = 1
		    IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer train_s3 50.0 50.0 FALSE
				IF speedlimit_s3flag = 1
			    	SET_TRAIN_CRUISE_SPEED train_s3 15.0
				ENDIF
				IF speedlimit_s3flag > 1
			    	SET_TRAIN_CRUISE_SPEED train_s3 18.0
				ENDIF
				IF speedlimit_s3flag = 0
			    	SET_TRAIN_CRUISE_SPEED train_s3 10.0
				ENDIF
		     	trainspeed_s3flag = 2
		    ENDIF
		ENDIF

	  	IF trainspeed_s3flag = 2
			IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer train_s3 45.0 45.0 FALSE
				IF speedlimit_s3flag = 0
					speedlimit_s3flag = 1
				ENDIF
		    	SET_TRAIN_CRUISE_SPEED train_s3 28.0
				trainspeed_s3flag = 1
			ENDIF
		ENDIF

	   	IF trainspeed_s3flag = 1
		    IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer train_s3 20.0 20.0 FALSE
				IF speedlimit_s3flag > 1
					SET_TRAIN_CRUISE_SPEED train_s3 32.5 //32.0//35.0 //38.0
				ELSE
			    	SET_TRAIN_CRUISE_SPEED train_s3 30.0 //29.5 //32.0 //35.0
				ENDIF
				trainspeed_s3flag = 3
			ENDIF
		ENDIF

		IF trainspeed_s3flag = 3
		    IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer train_s3 20.0 20.0 FALSE
				trainspeed_s3flag = 2
			ENDIF
		ENDIF

		//////////////////////////////////////////////////////////////////////////	Incoming trains

		IF opptrain_s3flag < 15
			//first incoming train
			IF opptrain_s3flag = 0
				IF LOCATE_CAR_2D train_s3 2282.12 -1439.76 20.0 20.0 FALSE
					IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2289.69 -1034.14 20.0 20.0 FALSE
						CREATE_MISSION_TRAIN 11 2289.69 -1034.14 26.29 TRUE opptrain_s3
						SET_TRAIN_SPEED opptrain_s3 30.0
						SET_TRAIN_CRUISE_SPEED opptrain_s3 15.0
						
						CREATE_OBJECT imy_track_barrier 2151.289 -664.906 52.10 barrier_s3
						SET_OBJECT_HEADING barrier_s3 50.36

						speedlimit_s3flag = 2
						opptrain_s3flag = 1
					ELSE
						opptrain_s3flag = 15
					ENDIF
				ENDIF
			ENDIF
			IF opptrain_s3flag = 1
				IF NOT IS_CAR_DEAD opptrain_s3
					IF LOCATE_CAR_2D opptrain_s3 2288.31 -1408.91 20.0 20.0 FALSE
						SET_TRAIN_CRUISE_SPEED opptrain_s3 0.0
						opptrain_s3flag = 2
					ENDIF
				ENDIF
			ENDIF
			IF opptrain_s3flag = 2
				IF NOT IS_CAR_DEAD opptrain_s3
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer opptrain_s3 40.0 40.0 FALSE
						IF NOT IS_CAR_ON_SCREEN opptrain_s3
							DELETE_MISSION_TRAIN opptrain_s3			
							opptrain_s3flag = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			//second incoming train
			IF opptrain_s3flag = 3
				IF LOCATE_CAR_2D train_s3 2176.87 -689.51 20.0 20.0 FALSE
					IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2283.36 -336.9 30.0 30.0 FALSE
						CREATE_MISSION_TRAIN 11 2283.36 -336.9 37.27 TRUE opptrain_s3
						SET_TRAIN_SPEED opptrain_s3 30.0
						SET_TRAIN_CRUISE_SPEED opptrain_s3 15.0
						opptrain_s3flag = 4
					ELSE
						opptrain_s3flag = 15
					ENDIF
				ENDIF
			ENDIF
			IF opptrain_s3flag = 4
				IF NOT IS_CAR_DEAD opptrain_s3
					IF LOCATE_CAR_2D opptrain_s3 2052.08 -583.31 20.0 20.0 FALSE //2012.88 -419.28
						SET_TRAIN_CRUISE_SPEED opptrain_s3 0.0
						opptrain_s3flag = 5
					ENDIF
				ENDIF
			ENDIF
			IF opptrain_s3flag = 5
				IF NOT IS_CAR_DEAD opptrain_s3
					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer opptrain_s3 40.0 40.0 FALSE
						IF NOT IS_CAR_ON_SCREEN opptrain_s3
							DELETE_MISSION_TRAIN opptrain_s3			
							opptrain_s3flag = 6
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		//////////////////////////////////////////////////////////////////////////	duck locations
		IF duck_s3flag = 0
			IF LOCATE_CAR_2D train_s3 2198.0 -1913.0 12.0 12.0 FALSE
				IF NOT IS_CHAR_DEAD mex1_s3
					TASK_DUCK mex1_s3 -1
				ENDIF
				IF NOT IS_CHAR_DEAD mex2_s3
					TASK_DUCK mex2_s3 -1
				ENDIF
				IF NOT IS_CHAR_DEAD mex3_s3
					TASK_DUCK mex3_s3 -1
				ENDIF

				CREATE_CAR GLENDALE 2200.07 -1731.94 12.28 othercar_s3
				SET_CAR_HEADING othercar_s3 277.116
				CREATE_CHAR PEDTYPE_MISSION3 MALE01 2201.55 -1728.59 12.43 othercardriver1_s3
				SET_CHAR_HEADING othercardriver1_s3 285.1
				CREATE_CHAR PEDTYPE_MISSION3 MALE01 2202.93 -1728.36 12.43 othercardriver2_s3
				SET_CHAR_HEADING othercardriver2_s3 393.56
				TASK_PLAY_ANIM othercardriver1_s3 IDLE_CHAT PED	4.0 TRUE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM othercardriver2_s3 IDLE_CHAT PED	4.0 TRUE FALSE FALSE FALSE -1
				speedlimit_s3flag = 1
				duck_s3flag = 1
			ENDIF
		ENDIF
		IF duck_s3flag = 1
			IF NOT LOCATE_CAR_2D train_s3 2198.0 -1913.0 12.0 12.0 FALSE
				IF NOT IS_CHAR_DEAD mex1_s3
					enemy_s3 = mex1_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				IF NOT IS_CHAR_DEAD mex2_s3
					enemy_s3 = mex2_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				IF NOT IS_CHAR_DEAD mex3_s3
					enemy_s3 = mex3_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				duck_s3flag = 2
			ENDIF
		ENDIF
		IF duck_s3flag = 2
			IF LOCATE_CAR_2D train_s3 2285.58 -1353.75 20.0 20.0 FALSE
				IF NOT IS_CHAR_DEAD mex1_s3
					TASK_DUCK mex1_s3 -1 
				ENDIF
				IF NOT IS_CHAR_DEAD mex2_s3
					TASK_DUCK mex2_s3 -1 
				ENDIF
				IF NOT IS_CHAR_DEAD mex3_s3
					TASK_DUCK mex3_s3 -1 
				ENDIF
				duck_s3flag = 3
			ENDIF
		ENDIF
		IF duck_s3flag = 3
			IF NOT LOCATE_CAR_2D train_s3 2285.58 -1353.75 20.0 20.0 FALSE
				IF NOT IS_CHAR_DEAD mex1_s3
					enemy_s3 = mex1_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				IF NOT IS_CHAR_DEAD mex2_s3
					enemy_s3 = mex2_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				IF NOT IS_CHAR_DEAD mex3_s3
					enemy_s3 = mex3_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				IF NOT IS_CHAR_DEAD mex4_s3
					TASK_DIE mex4_s3
				ENDIF
				DELETE_OBJECT crane_s3
				MARK_MODEL_AS_NO_LONGER_NEEDED IMMMCRAN
				MARK_CAR_AS_NO_LONGER_NEEDED othercar_s3
				MARK_CHAR_AS_NO_LONGER_NEEDED othercardriver1_s3
				MARK_CHAR_AS_NO_LONGER_NEEDED othercardriver2_s3
				mex2_s3flag = 1
				duck_s3flag = 4
			ENDIF
		ENDIF
		IF duck_s3flag = 4
			IF LOCATE_CAR_2D train_s3 2121.0 -361.0 18.0 18.0 FALSE
				IF NOT IS_CHAR_DEAD mex1_s3
					TASK_DUCK mex1_s3 -1 
				ENDIF
				IF NOT IS_CHAR_DEAD mex3_s3
					TASK_DUCK mex3_s3 -1 
				ENDIF
				duck_s3flag = 5
			ENDIF
		ENDIF
		IF duck_s3flag = 5
			IF NOT LOCATE_CAR_2D train_s3 2121.0 -361.0 18.0 18.0 FALSE
				IF NOT IS_CHAR_DEAD mex1_s3
					enemy_s3 = mex1_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				IF NOT IS_CHAR_DEAD mex3_s3
					enemy_s3 = mex3_s3
					GOSUB ontopoftrain_labels3
				ENDIF
				duck_s3flag = 6
			ENDIF
		ENDIF
		IF duck_s3flag = 6
			IF NOT LOCATE_CAR_2D train_s3 2121.0 -361.0 18.0 18.0 FALSE
				speedlimit_s3flag = 3
				duck_s3flag = 7
			ENDIF
		ENDIF
		

		//mex2
		IF mex2_s3flag = 1
			IF NOT IS_CHAR_DEAD mex2_s3
				IF LOCATE_CAR_2D train_s3 2285.53 -1120.59 20.0 20.0 FALSE
					SET_CHAR_DECISION_MAKER mex2_s3 emptysmoke3_DM
					mex2_s3flag = 2
				ENDIF
			ENDIF
		ENDIF
		IF mex2_s3flag = 2
			IF NOT IS_CHAR_DEAD mex2_s3
				IF LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer train_s3 30.0 30.0 FALSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE mex2_s3 tran_stmb TRAIN 4.0 FALSE FALSE FALSE TRUE -1		
					SET_CHAR_PROOFS mex2_s3 TRUE TRUE TRUE TRUE TRUE
					mex2_s3flag = 3
				ENDIF
			ENDIF
		ENDIF
		IF mex2_s3flag = 3
			IF NOT IS_CHAR_DEAD mex2_s3
				IF IS_CHAR_PLAYING_ANIM mex2_s3 tran_stmb
					GET_CHAR_ANIM_CURRENT_TIME mex2_s3 tran_stmb finishanim_s3
						IF finishanim_s3 = 1.0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE mex2_s3 tran_hng TRAIN 4.0 TRUE FALSE FALSE FALSE -1
							TIMERA = 0
							mex2_s3flag = 4
						ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF mex2_s3flag = 4
			IF TIMERA > 10000
				IF NOT IS_CHAR_DEAD mex2_s3
					TASK_PLAY_ANIM_NON_INTERRUPTABLE mex2_s3 tran_gtup TRAIN 4.0 FALSE FALSE FALSE FALSE -1 	
					mex2_s3flag = 5
				ENDIF
			ENDIF
		ENDIF
		IF mex2_s3flag = 5
			IF NOT IS_CHAR_DEAD mex2_s3
				IF IS_CHAR_PLAYING_ANIM mex2_s3 tran_gtup
					GET_CHAR_ANIM_CURRENT_TIME mex2_s3 tran_gtup finishanim_s3
						IF finishanim_s3 = 1.0
							SET_CHAR_DECISION_MAKER mex2_s3 smoke3mex_DM
							enemy_s3 = mex2_s3
							GOSUB ontopoftrain_labels3
							REMOVE_ANIMATION TRAIN
							SET_CHAR_PROOFS mex2_s3 FALSE FALSE FALSE FALSE FALSE
							mex2_s3flag = 6
						ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF mex2_s3flag > 0
			IF mex2_s3flag < 6
				IF NOT IS_CHAR_DEAD mex2_s3
					GET_CAR_HEADING train_s3 train_s3heading
					SET_CHAR_HEADING mex2_s3 train_s3heading
				ENDIF
			ENDIF
		ENDIF

		IF mex4_s3flag = 0
			IF DOES_OBJECT_EXIST crane_s3 
				IF NOT IS_CHAR_DEAD mex4_s3
					IF LOCATE_CHAR_ANY_MEANS_2D mex4_s3 2197.53 -1913.39 3.0 3.0 FALSE
						TASK_TOGGLE_PED_THREAT_SCANNER mex4_s3 FALSE FALSE FALSE
						DETACH_CHAR_FROM_CAR mex4_s3
						SET_CHAR_COLLISION mex4_s3 FALSE
						ATTACH_CHAR_TO_OBJECT mex4_s3 crane_s3 2.85 -0.70 5.9 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
						SET_CHAR_HEADING mex4_s3 0.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE mex4_s3 tran_ouch TRAIN 4.0 FALSE FALSE FALSE TRUE -1
						//IF NOT IS_CHAR_DEAD big_smoke
							//IF LOCATE_CHAR_ANY_MEANS_2D big_smoke 2197.53 -1913.39 15.0 15.0 FALSE
							//ENDIF
						//ENDIF
						mex4dead_s3flag = 1
						mex4_s3flag = 1
						REMOVE_BLIP mex4_s3blip
					ENDIF
				ENDIF
			ELSE
				IF NOT IS_CHAR_DEAD mex4_s3
					SET_CHAR_PROOFS mex4_s3 FALSE FALSE FALSE FALSE FALSE
				ENDIF
			ENDIF
		ENDIF
		IF mex4_s3flag = 1
			IF DOES_OBJECT_EXIST crane_s3 
				GET_OBJECT_HEADING crane_s3 train_s3heading
				IF NOT IS_CHAR_DEAD mex4_s3
					SET_CHAR_HEADING mex4_s3 train_s3heading
				ENDIF
			ENDIF

			IF NOT IS_CHAR_DEAD mex4_s3
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer mex4_s3 50.0 50.0 FALSE
					REMOVE_CHAR_ELEGANTLY mex4_s3
					mex4_s3flag = 2
				ENDIF
			ENDIF
		ENDIF
			

	ENDIF

	//////////////////////////////////////
	IF mex1dead_s3flag = 0
		IF IS_CHAR_DEAD mex1_s3
			mex1dead_s3flag = 1
			mexdead_s3counter++
			IF DOES_BLIP_EXIST mex1_s3blip
				REMOVE_BLIP mex1_s3blip
			ENDIF
		ENDIF
	ENDIF

	IF mex2dead_s3flag = 0
		IF IS_CHAR_DEAD mex2_s3
			mex2dead_s3flag = 1
			mexdead_s3counter++
			IF DOES_BLIP_EXIST mex2_s3blip
				REMOVE_BLIP mex2_s3blip
			ENDIF	
		ENDIF
	ENDIF

	IF mex3dead_s3flag = 0
		IF IS_CHAR_DEAD mex3_s3
			mex3dead_s3flag = 1
			mexdead_s3counter++
			IF DOES_BLIP_EXIST mex3_s3blip
				REMOVE_BLIP mex3_s3blip	
			ENDIF	
		ENDIF
	ENDIF

	IF mex4dead_s3flag = 0
		IF IS_CHAR_DEAD mex4_s3
			mex4dead_s3flag = 1
			mexdead_s3counter++
			IF DOES_BLIP_EXIST mex4_s3blip
				REMOVE_BLIP mex4_s3blip	
			ENDIF
		ENDIF
	ENDIF

	//Explosive car
	IF explode_s3flag = 0
		IF NOT IS_CAR_DEAD othercar_s3
			IF HAS_CAR_BEEN_DAMAGED_BY_CAR othercar_s3 train_s3
				EXPLODE_CAR othercar_s3
				explode_s3flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF explode_s3flag = 1
		MARK_CAR_AS_NO_LONGER_NEEDED othercar_s3
		explode_s3flag = 2
	ENDIF

	////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////	 Chase	////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//player passes mission if they are all taken out
	IF mex1dead_s3flag = 1
		IF mex2dead_s3flag = 1
			IF mex3dead_s3flag = 1
				IF mex4dead_s3flag = 1
					IF NOT IS_CHAR_DEAD big_smoke
						IF IS_GROUP_MEMBER big_smoke Players_Group
							ADD_BLIP_FOR_COORD 2061.68 -1694.71 12.554 smokehouse_s3blip
							locatesmoke_s3flag = 1
							missionplaying_s3flag = 1
							progressaudio_s3flag = 0
							handlingudio_s3flag = 0
							getsmoke_s3flag = 1
						ELSE
							getsmoke_s3flag = 2
							PRINT_NOW SMK3_18 5000 1 //~s~You left ~b~Smoke ~s~behind go get him!
						ENDIF
					ENDIF
					SET_RAILTRACK_RESISTANCE_MULT -1.0
					MARK_CHAR_AS_NO_LONGER_NEEDED mex1_s3
					MARK_CHAR_AS_NO_LONGER_NEEDED mex2_s3
					MARK_CHAR_AS_NO_LONGER_NEEDED mex3_s3
					MARK_CHAR_AS_NO_LONGER_NEEDED mex4_s3
					MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
					MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
					MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
					MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
					MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
					MARK_MODEL_AS_NO_LONGER_NEEDED STREAKC
					MARK_MISSION_TRAIN_AS_NO_LONGER_NEEDED train_s3
					SET_CAR_DENSITY_MULTIPLIER 1.0
					SET_PED_DENSITY_MULTIPLIER 1.0
					SWITCH_ROADS_ON 2261.14 -1141.45 18.0 2308.19 -1156.38 35.0
					SWITCH_ROADS_ON 2270.69 -1391.75 20.28 2315.81 -1375.58 26.88
					SWITCH_ROADS_ON 2268.75 -1390.8 20.86 2330.03 -1375.53 23.10
					SWITCH_ROADS_ON 2257.3 -1491.95 20.63  2299.7 -1477.32 26.12
					SWITCH_ROADS_ON 2235.25 -1656.59 12.68 2176.75 -1629.89 15.0
					SWITCH_ROADS_ON 2221.43 -1721.07 10.09 2174.01 -172.72 15.73
					SWITCH_ROADS_ON 2234.09 -1723.3 14.0 2178.22 -1742.47 10.0
					SWITCH_ROADS_ON 2261.14 -1141.45 18.0 2308.19 -1156.38 35.0
					SWITCH_ROADS_ON 1956.91 -1936.01 10.07 1965.61 -1978.99 14.45
					SWITCH_ROADS_ON 2181.3 -1892.0 10.0 222.36 -1897.71 15.0
					smoke_s3flag = 3		
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	////////////////////////////////////////////////////	Mission passed


	////////////////////////////////////////////////////	Mission failed
	//Smoke died
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW SMK3_21 5000 1 //~r~Smoke died!
		GOTO mission_smoke3_failed
	ENDIF

	//out of range
	IF smoke_s3flag = 2

		IF speedlimit_s3flag = 0
			x =	320.0
			y = 320.0
		ELSE
			x = 200.0
			y = 200.0
		ENDIF

		IF NOT IS_CAR_DEAD train_s3
			IF NOT IS_CAR_ON_SCREEN train_s3
				IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer train_s3 x y FALSE
					IF NOT IS_CHAR_DEAD mex1_s3
					OR NOT IS_CHAR_DEAD mex2_s3
					OR NOT IS_CHAR_DEAD mex3_s3
					OR NOT IS_CHAR_DEAD mex4_s3
						SWITCH_WIDESCREEN ON
						SET_PLAYER_CONTROL PLAYER1 OFF

						/////////////
						GET_AREA_VISIBLE smoke3_area

						IF NOT smoke3_area = 0
							DO_FADE 250 FADE_OUT
							IF NOT IS_CHAR_DEAD	big_smoke
								SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE
							ENDIF
							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE

							IF NOT IS_CHAR_DEAD big_smoke
								SET_AREA_VISIBLE 0 
								FREEZE_CHAR_POSITION scplayer TRUE
								GET_CHAR_COORDINATES big_smoke x y z
								SET_CHAR_PROOFS big_smoke TRUE TRUE TRUE TRUE TRUE
								LOAD_SCENE x y z 
								DO_FADE 250 FADE_IN
							ENDIF

						ENDIF
						/////////////

						IF NOT IS_CHAR_DEAD big_smoke
							missionplaying_s3flag = 1
							SET_CAMERA_IN_FRONT_OF_CHAR	big_smoke
							TASK_WANDER_STANDARD big_smoke
							GET_CHAR_COORDINATES big_smoke x y z
							CLEAR_AREA x y 100.0 100.0 FALSE
							CLEAR_MISSION_AUDIO 1
							CLEAR_MISSION_AUDIO 2
						ENDIF

						LOAD_MISSION_AUDIO 2 SOUND_SMO3_MC //We just had to follow the damn train, CJ!

						WHILE NOT HAS_MISSION_AUDIO_LOADED 2
							WAIT 0
						ENDWHILE

						PRINT_NOW ( SMO3_MC ) 4000 1
						PLAY_MISSION_AUDIO 2

						WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
							WAIT 0
						ENDWHILE

						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 2

						////////////////////
						IF NOT smoke3_area = 0
							DO_FADE 500 FADE_OUT

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE
							SET_AREA_VISIBLE smoke3_area
							FREEZE_CHAR_POSITION scplayer FALSE
							GET_CHAR_COORDINATES scplayer x y z
							LOAD_SCENE x y z
							DO_FADE 250 FADE_IN
						ENDIF
						////////////////////		

						SWITCH_WIDESCREEN OFF
						SET_PLAYER_CONTROL PLAYER1 ON
						RESTORE_CAMERA_JUMPCUT
						SET_CAMERA_BEHIND_PLAYER 
						DELETE_CHAR big_smoke

						PRINT_NOW SMK3_13 5000 1
						GOTO mission_smoke3_failed
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	//get to certain destination
	IF smoke_s3flag = 2
		IF NOT IS_CAR_DEAD train_s3
			IF LOCATE_CAR_2D train_s3 2790.52 223.63 15.0 15.0 FALSE

				SET_PLAYER_CONTROL PLAYER1 OFF
				SWITCH_WIDESCREEN ON

				SET_TRAIN_SPEED train_s3 20.0
				SET_TRAIN_CRUISE_SPEED train_s3 20.0
				SET_FIXED_CAMERA_POSITION 2796.8391 222.9677 10.7646 0.0 0.0 0.0
				POINT_CAMERA_AT_CAR train_s3 FIXED JUMP_CUT

				IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					SET_CAR_COORDINATES car 2814.828 154.135 17.2
					SET_CAR_HEADING car 9.0 
					SET_CAR_CRUISE_SPEED car 0.0
				ELSE
					SET_CHAR_COORDINATES scplayer 2814.828 154.135 17.2
					SET_CHAR_HEADING scplayer 9.0
				ENDIF

				WAIT 3000
				RESTORE_CAMERA_JUMPCUT
				WAIT 0
				missionplaying_s3flag = 1
				IF NOT IS_CHAR_DEAD big_smoke
					SET_CAMERA_IN_FRONT_OF_CHAR	big_smoke
					TASK_WANDER_STANDARD big_smoke
					GET_CHAR_COORDINATES big_smoke x y z
				ENDIF
				CLEAR_AREA x y 100.0 100.0 FALSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_MISSION_AUDIO 2

				LOAD_MISSION_AUDIO 2 SOUND_SMO3_MC //We just had to follow the damn train, CJ!

				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0
				ENDWHILE

				PRINT_NOW ( SMO3_MC ) 4000 1
				PLAY_MISSION_AUDIO 2

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				ENDWHILE

				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 2

				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL PLAYER1 ON
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER 
				DELETE_CHAR big_smoke

				
				PRINT_NOW SMK3_20 10000 1 //~r~They got away!
	 			GOTO mission_smoke3_failed

			ENDIF
		ENDIF
	ENDIF

	//Smoke Dialogue
	IF missionplaying_s3flag = 0
		IF IS_GROUP_MEMBER big_smoke Players_Group
			IF IS_CHAR_SITTING_IN_ANY_CAR big_smoke
				IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 

					GOSUB process_audio_s3

					//play mission audio
					IF progressaudio_s3flag = 0
						IF handlingudio_s3flag = 0
							audio_label_s3 = SOUND_SMO3_CA //Follow that train!
							$input_text_s3 = SMO3_CA //Follow that train!
							GOSUB load_audio_s3
							TIMERB = 0
						ENDIF
					ENDIF

					IF progressaudio_s3flag = 1
						IF handlingudio_s3flag = 0
							IF TIMERB > 4000
								audio_label_s3 = SOUND_SMO3_EB //Get me close, CJ, I'm gonnna cap those mothers!
								$input_text_s3 = SMO3_EB //Get me close, CJ, I'm gonnna cap those mothers!
								GOSUB load_audio_s3
								TIMERB = 0
							ENDIF
						ENDIF
					ENDIF

					IF progressaudio_s3flag = 2
						IF handlingudio_s3flag = 0
							IF NOT IS_CAR_DEAD train_s3
								IF LOCATE_CHAR_ANY_MEANS_CAR_2D big_smoke train_s3 50.0 50.0 FALSE
									audio_label_s3 = SOUND_SMO3_EA	//Pull alongside, I can get a shot!
									$input_text_s3 = SMO3_EA	//Pull alongside, I can get a shot!
									infotext_s3flag = 1
									GOSUB load_audio_s3
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					IF progressaudio_s3flag = 3
						IF handlingudio_s3flag = 0
							IF NOT IS_CAR_DEAD opptrain_s3
								IF LOCATE_CHAR_ANY_MEANS_CAR_2D big_smoke opptrain_s3 165.0.0 165.0 FALSE
									audio_label_s3 = SOUND_SMO3_FA	//Holy fuck! On-coming train
									$input_text_s3 = SMO3_FA	//Holy fuck! On-coming train
									GOSUB load_audio_s3
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					IF progressaudio_s3flag = 4
						IF handlingudio_s3flag = 0
							IF LOCATE_CHAR_ANY_MEANS_2D big_smoke 2180.22 -689.43 60.0 60.0 FALSE
								audio_label_s3 = SOUND_SMO3_HA	//Take the high road on the right, CJ!
								$input_text_s3 = SMO3_HA	//Take the high road on the right, CJ!
								GOSUB load_audio_s3
							ENDIF
						ENDIF
					ENDIF

					IF progressaudio_s3flag = 5
						IF handlingudio_s3flag = 0
							IF NOT IS_CAR_DEAD opptrain_s3
								IF LOCATE_CHAR_ANY_MEANS_CAR_2D big_smoke opptrain_s3 100.0 100.0 FALSE
									audio_label_s3 = SOUND_SMO3_FC	//Look-the-fuck-out – TRAIN!
									$input_text_s3 = SMO3_FC	//Look-the-fuck-out – TRAIN!
									GOSUB load_audio_s3
									TIMERB = 0
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					IF progressaudio_s3flag = 6
						IF handlingudio_s3flag = 0
							IF TIMERB > 11000
								audio_label_s3 = SOUND_SMO3_EC	//Match their speed and I’ll ice those fools!
								$input_text_s3 = SMO3_EC	//Match their speed and I’ll ice those fools!
								IF DOES_OBJECT_EXIST barrier_s3
									IF NOT IS_OBJECT_ON_SCREEN barrier_s3
										DELETE_OBJECT barrier_s3
									ENDIF
								ENDIF
								MARK_MODEL_AS_NO_LONGER_NEEDED imy_track_barrier
								IF NOT IS_CHAR_DEAD big_smoke
									IF mexdead_s3counter < 3
										SET_CHAR_ACCURACY big_smoke 85
									ENDIF
								ENDIF
								GOSUB load_audio_s3
							ENDIF
						ENDIF
					ENDIF

				ENDIF
			ENDIF
		ENDIF
	ENDIF


ENDIF


IF smoke_s3flag = 3
	IF getsmoke_s3flag = 1

		IF audiowayback_s3flag = 0
			CLEAR_MISSION_AUDIO	1
			LOAD_MISSION_AUDIO 1 SOUND_SMO3_PC //Let’s hightail it back to mine before the cops show! 
			MARK_OBJECT_AS_NO_LONGER_NEEDED barrier_s3
			MARK_MODEL_AS_NO_LONGER_NEEDED imy_track_barrier
			audiowayback_s3flag = 1
		ENDIF
		IF audiowayback_s3flag = 1
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW ( SMO3_PC ) 4000 1
				PLAY_MISSION_AUDIO 1
				audiowayback_s3flag = 2
			ENDIF
		ENDIF
		IF audiowayback_s3flag = 2
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				CLEAR_MISSION_AUDIO 1
				audiowayback_s3flag = 3
				TIMERB = 0
			ENDIF
		ENDIF
		IF audiowayback_s3flag = 3
			IF TIMERB > 500
				PRINT_NOW SMK3_22 6000 1 //~s~Drive Big Smoke back to his ~y~house~s~.
				TIMERB = 0
				audiowayback_s3flag = 4
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD big_smoke
			IF audiowayback_s3flag = 4
				IF IS_GROUP_MEMBER big_smoke Players_Group
					IF IS_CHAR_SITTING_IN_ANY_CAR big_smoke
						IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 

							GOSUB process_audio_s3

							//play mission audio
							IF progressaudio_s3flag = 0
								IF handlingudio_s3flag = 0
									IF TIMERB > 7000
										audio_label_s3 = SOUND_SMO3_RA	//Was it always like this?
										$input_text_s3 = SMO3_RA	//Was it always like this?
										GOSUB load_audio_s3
										TIMERB = 0
									ENDIF
								ENDIF
							ENDIF

							IF progressaudio_s3flag = 1
								IF handlingudio_s3flag = 0
									audio_label_s3 = SOUND_SMO3_RB	//Was what?
									$input_text_s3 = SMO3_RB	//Was what?
									GOSUB load_audio_s3
								ENDIF
							ENDIF

							IF progressaudio_s3flag = 2
								IF handlingudio_s3flag = 0
									audio_label_s3 = SOUND_SMO3_RC	//Always fucked up around here.  Or is it because of the drugs?
									$input_text_s3 = SMO3_RC	//Always fucked up around here.  Or is it because of the drugs?
									GOSUB load_audio_s3
								ENDIF
							ENDIF

							IF progressaudio_s3flag = 3
								IF handlingudio_s3flag = 0
									audio_label_s3 = SOUND_SMO3_RD //What do you think?
									$input_text_s3 = SMO3_RD //What do you think?
									GOSUB load_audio_s3
								ENDIF
							ENDIF

							IF progressaudio_s3flag = 4
								IF handlingudio_s3flag = 0
									audio_label_s3 = SOUND_SMO3_RE	//I don’t know. That’s why I’m asking you.
									$input_text_s3 = SMO3_RE	//I don’t know. That’s why I’m asking you.
									GOSUB load_audio_s3
								ENDIF
							ENDIF

							IF progressaudio_s3flag = 5
								IF handlingudio_s3flag = 0
									audio_label_s3 = SOUND_SMO3_RF	//Don’t ask a wise man, friend. Ask a fool.
									$input_text_s3 = SMO3_RF	//Don’t ask a wise man, friend. Ask a fool.
									GOSUB load_audio_s3
								ENDIF
							ENDIF

							IF progressaudio_s3flag = 6
								IF handlingudio_s3flag = 0
									audio_label_s3 = SOUND_SMO3_RG	//That’s what I was doing.
									$input_text_s3 = SMO3_RG	//That’s what I was doing.
									GOSUB load_audio_s3
								ENDIF
							ENDIF

							IF progressaudio_s3flag = 7
								IF handlingudio_s3flag = 0
									audio_label_s3 = SOUND_SMO3_RH	//Well, if you’re going to make it personal, I ain’t speaking no more.
									$input_text_s3 = SMO3_RH	//Well, if you’re going to make it personal, I ain’t speaking no more.
									GOSUB load_audio_s3
								ENDIF
							ENDIF

						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF


		IF locatesmoke_s3flag = 1
			IF NOT IS_CHAR_DEAD big_smoke
				IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2061.68 -1694.71 4.0 4.0 TRUE
					IF LOCATE_CHAR_ANY_MEANS_2D big_smoke 2061.68 -1694.71 4.0 4.0 FALSE

						SET_PLAYER_CONTROL PLAYER1 OFF
						DO_FADE 1000 FADE_OUT
						SWITCH_WIDESCREEN ON
						audiowayback_s3flag = 5
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2

						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE
						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
						IF NOT IS_CHAR_DEAD big_smoke
							REMOVE_CHAR_FROM_GROUP big_smoke
							CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
							SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_UNARMED
						ENDIF
						IF IS_CHAR_IN_ANY_CAR scplayer
							WARP_CHAR_FROM_CAR_TO_COORD scplayer 2060.3254 -1694.8049 12.5547
							SET_CHAR_HEADING scplayer 91.3046
						ELSE
							SET_CHAR_COORDINATES scplayer 2060.3254 -1694.8049 12.5547
							SET_CHAR_HEADING scplayer 91.3046
						ENDIF
						IF IS_CHAR_IN_ANY_CAR big_smoke
							WARP_CHAR_FROM_CAR_TO_COORD big_smoke 2057.75 -1694.79 12.554
							SET_CHAR_HEADING big_smoke 273.188
						ELSE
							SET_CHAR_COORDINATES big_smoke 2057.75 -1694.79 12.554
							SET_CHAR_HEADING big_smoke 273.188
						ENDIF
						CLEAR_AREA 2057.75 -1694.79 100.0 100.0 TRUE
						IF NOT IS_CAR_DEAD bike_s3
							SET_CAR_COORDINATES bike_s3 2060.3 -1697.975 9.0
							SET_CAR_HEADING bike_s3 88.04
						ENDIF
						REQUEST_ANIMATION GANGS
						LOAD_MISSION_AUDIO 1 SOUND_SMO3_SA //You better clear out, CJ, 
						LOAD_MISSION_AUDIO 2 SOUND_SMO3_SB //I don’t want those CRASH fools getting their claws into you.

						LOAD_ALL_MODELS_NOW

						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							OR NOT HAS_MISSION_AUDIO_LOADED 2
							OR NOT HAS_ANIMATION_LOADED GANGS
							WAIT 0
						ENDWHILE

						smoke_s3flag = 4
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF IS_CHAR_DEAD big_smoke
			PRINT_NOW SMK3_21 5000 1 //~r~Smoke died!
			GOTO mission_smoke3_failed
		ENDIF

	ENDIF
ENDIF


IF smoke_s3flag = 4
	SET_FIXED_CAMERA_POSITION 2061.8142 -1692.7729 13.1148 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2061.0894 -1693.4531 13.2238 JUMP_CUT
	DO_FADE 500 FADE_IN
	IF NOT IS_CHAR_DEAD big_smoke
		TASK_PLAY_ANIM big_smoke prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE FALSE -1
		PLAY_MISSION_AUDIO 1 //You better clear out, CJ,
		START_CHAR_FACIAL_TALK big_smoke 10000
		PRINT_NOW SMO3_SA 4000 1 //You better clear out, CJ,
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	LOAD_MISSION_AUDIO 1 SOUND_SMO3_SC	//Alright, homie.  You be careful with those cats.
	PLAY_MISSION_AUDIO 2 //I don’t want those CRASH fools getting their claws into you.
	PRINT_NOW SMO3_SB 4000 1 //I don’t want those CRASH fools getting their claws into you.
	IF NOT IS_CHAR_DEAD big_smoke
		TASK_PLAY_ANIM big_smoke IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
	ENDIF
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0
	ENDWHILE
	IF NOT IS_CHAR_DEAD big_smoke
		CLEAR_CHAR_TASKS big_smoke 
		STOP_CHAR_FACIAL_TALK big_smoke
	ENDIF

	CLEAR_MISSION_AUDIO 2
	CLEAR_PRINTS
	LOAD_MISSION_AUDIO 2 SOUND_SMO3_SD	//I'll see you later.
	SET_FIXED_CAMERA_POSITION 2055.8511 -1692.6440 14.0837 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2056.5813 -1693.3239 14.0171 JUMP_CUT
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	ENDWHILE
	PLAY_MISSION_AUDIO 1  //Alright, homie.  You be careful with those cats.
	PRINT_NOW SMO3_SC 5000 1
	TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_PRINTS
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0
	ENDWHILE
	PLAY_MISSION_AUDIO 2 //I don’t want those CRASH fools getting their claws into you.
	PRINT_NOW SMO3_SD 5000 1 //I’ll see you later.
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0
	ENDWHILE
	DO_FADE 1500 FADE_OUT
	IF NOT IS_CHAR_DEAD big_smoke
		TASK_GO_STRAIGHT_TO_COORD big_smoke 2058.027 -1697.166 13.55 PEDMOVE_WALK -1
		TASK_GO_STRAIGHT_TO_COORD scplayer 2072.723 -1694.359 13.55 PEDMOVE_WALK -1
	ENDIF
	
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE 

	DELETE_CHAR big_smoke
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
	SET_CHAR_COORDINATES scplayer 2072.723 -1694.359 12.54
	SET_CHAR_HEADING scplayer 272.637
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL PLAYER1 OFF
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	DO_FADE 500 FADE_IN
	CLEAR_PRINTS
	GOTO mission_smoke3_passed
ENDIF





// blip swapping for chase and the way back

IF smoke_s3flag = 2
OR smoke_s3flag = 3

	IF NOT IS_CHAR_DEAD big_smoke

		IF smokegroupchase_s3flag = 0 
			IF NOT IS_GROUP_MEMBER big_smoke Players_Group

				PRINT_NOW SMK3_18 5000 1 //~s~You left ~b~Smoke ~s~behind go get him!
		        ADD_BLIP_FOR_CHAR big_smoke trainstation_s3blip
				SET_BLIP_AS_FRIENDLY trainstation_s3blip TRUE
				
				IF smoke_s3flag = 2
					REMOVE_BLIP mex1_s3blip
					REMOVE_BLIP mex2_s3blip
					REMOVE_BLIP mex3_s3blip
					REMOVE_BLIP mex4_s3blip
				ENDIF

				IF smoke_s3flag = 3
					REMOVE_BLIP smokehouse_s3blip
					locatesmoke_s3flag = 0
				ENDIF

			    smokegroupchase_s3flag = 1
		    ENDIF
		ENDIF

		IF smokegroupchase_s3flag = 1
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer big_smoke 10.0 10.0 8.0 FALSE
				IF IS_CHAR_ON_SCREEN big_smoke
	    			IF NOT IS_GROUP_MEMBER big_smoke Players_Group
			           SET_GROUP_MEMBER Players_Group big_smoke 
					   SET_GROUP_SEPARATION_RANGE Players_Group 30.0
					ENDIF
					REMOVE_BLIP trainstation_s3blip

					IF smoke_s3flag = 2
						IF NOT IS_CHAR_DEAD mex1_s3
							ADD_BLIP_FOR_CHAR mex1_s3 mex1_s3blip
						ENDIF
						IF NOT IS_CHAR_DEAD mex2_s3
							ADD_BLIP_FOR_CHAR mex2_s3 mex2_s3blip
						ENDIF
						IF NOT IS_CHAR_DEAD mex3_s3
							ADD_BLIP_FOR_CHAR mex3_s3 mex3_s3blip
						ENDIF
						IF NOT IS_CHAR_DEAD mex4_s3
							ADD_BLIP_FOR_CHAR mex4_s3 mex4_s3blip
						ENDIF
						PRINT_NOW SMK3_3 5000 1//~s~Chase down the ~r~Vagos gang members ~s~and take them out!
					ENDIF

					IF smoke_s3flag = 3
						IF getsmoke_s3flag = 1
							PRINT_NOW SMK3_22 5000 1 //~s~Drive Big Smoke back to his ~y~house~s~.
							ADD_BLIP_FOR_COORD 2061.68 -1694.71 12.554 smokehouse_s3blip
							locatesmoke_s3flag = 1
						ENDIF
					ENDIF

					IF getsmoke_s3flag = 2
						IF smoke_s3flag = 3
							ADD_BLIP_FOR_COORD 2061.68 -1694.71 12.554 smokehouse_s3blip
							locatesmoke_s3flag = 1
							missionplaying_s3flag = 1
							progressaudio_s3flag = 0
							handlingudio_s3flag = 0
							getsmoke_s3flag = 1
						ENDIF
					ENDIF


					smokegroupchase_s3flag = 0
				ENDIF
			ENDIF
		ENDIF

	ENDIF

ENDIF

   
GOTO smoke4_main_mission_loop

////////////////////////////////////////////////////////	SEQUENCES

load_audio_s3:
IF handlingudio_s3flag = 0
	LOAD_MISSION_AUDIO 1 audio_label_s3
	$text_label_s3 = $input_text_s3
	handlingudio_s3flag = 1
ENDIF
RETURN

process_audio_s3:
IF handlingudio_s3flag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		PRINT_NOW $text_label_s3 4000 1 //Dummy message"
		PLAY_MISSION_AUDIO 1
		handlingudio_s3flag = 2
	ENDIF
ENDIF
IF handlingudio_s3flag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_s3flag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		IF infotext_s3flag = 1
			PRINT_NOW SMK3_25 6000 1 //~s~Keep up with the front carriage of the train so Big Smoke can shoot the ~r~Vagos gang members~s~.
			infotext_s3flag = 2
		ENDIF
		handlingudio_s3flag = 0
	ENDIF
ENDIF
RETURN


jump_s3label:
OPEN_SEQUENCE_TASK jump_s3seq
TASK_JUMP -1 TRUE
TASK_GO_STRAIGHT_TO_COORD -1 1799.49 -1953.73 12.23 PEDMOVE_RUN 50000
CLOSE_SEQUENCE_TASK jump_s3seq
PERFORM_SEQUENCE_TASK enemy_s3 jump_s3seq
CLEAR_SEQUENCE_TASK jump_s3seq
RETURN

ontopoftrain_labels3:
OPEN_SEQUENCE_TASK ontopoftrain_s3seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_PED_THREAT_SCANNER -1 TRUE FALSE FALSE
CLOSE_SEQUENCE_TASK ontopoftrain_s3seq
PERFORM_SEQUENCE_TASK enemy_s3 ontopoftrain_s3seq
CLEAR_SEQUENCE_TASK ontopoftrain_s3seq
RETURN

killsmoke_s3label:
OPEN_SEQUENCE_TASK killsmoke_s3seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
IF NOT IS_CHAR_DEAD big_smoke
	TASK_KILL_CHAR_ON_FOOT -1 big_smoke
ENDIF
CLOSE_SEQUENCE_TASK killsmoke_s3seq
PERFORM_SEQUENCE_TASK enemy_s3 killsmoke_s3seq
CLEAR_SEQUENCE_TASK killsmoke_s3seq
RETURN

 // **************************************** Mission smoke3 failed ************************

mission_smoke3_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission smoke3 passed *************************

mission_smoke3_passed:

flag_smoke_mission_counter ++
REGISTER_MISSION_PASSED ( SMOKE_3 )
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 1
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect

CLEAR_WANTED_LEVEL player1
RETURN
		


// ********************************** mission cleanup ************************************

mission_cleanup_smoke3:

flag_player_on_mission = 0


RESTORE_CAMERA_JUMPCUT
SET_PLAYER_IN_CAR_CAMERA_MODE ZOOM_TWO
MARK_OBJECT_AS_NO_LONGER_NEEDED barrier_s3
MARK_OBJECT_AS_NO_LONGER_NEEDED crane_s3
MARK_MODEL_AS_NO_LONGER_NEEDED imy_track_barrier

SET_RAILTRACK_RESISTANCE_MULT -1.0

IF IS_PLAYER_PLAYING PLAYER1
	SET_PLAYER_CONTROL PLAYER1 ON
	SWITCH_WIDESCREEN OFF
ENDIF

IF NOT IS_CAR_DEAD bike_s3
	SET_CAN_BURST_CAR_TYRES bike_s3 TRUE
ENDIF

MARK_MISSION_TRAINS_AS_NO_LONGER_NEEDED
SWITCH_RANDOM_TRAINS ON
SET_PED_DENSITY_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0

REMOVE_CHAR_ELEGANTLY mex1_s3
REMOVE_CHAR_ELEGANTLY mex2_s3
REMOVE_CHAR_ELEGANTLY mex3_s3
REMOVE_CHAR_ELEGANTLY mex4_s3
REMOVE_CHAR_ELEGANTLY big_smoke


SWITCH_ROADS_ON 2261.14 -1141.45 18.0 2308.19 -1156.38 35.0
SWITCH_ROADS_ON 2270.69 -1391.75 20.28 2315.81 -1375.58 26.88
SWITCH_ROADS_ON 2268.75 -1390.8 20.86 2330.03 -1375.53 23.10
SWITCH_ROADS_ON 2257.3 -1491.95 20.63  2299.7 -1477.32 26.12
SWITCH_ROADS_ON 2235.25 -1656.59 12.68 2176.75 -1629.89 15.0
SWITCH_ROADS_ON 2221.43 -1721.07 10.09 2174.01 -172.72 15.73
SWITCH_ROADS_ON 2234.09 -1723.3 14.0 2178.22 -1742.47 10.0
SWITCH_ROADS_ON 2261.14 -1141.45 18.0 2308.19 -1156.38 35.0
SWITCH_ROADS_ON 1956.91 -1936.01 10.07 1965.61 -1978.99 14.45
SWITCH_ROADS_ON 2181.3 -1892.0 10.0 222.36 -1897.71 15.0

MARK_MODEL_AS_NO_LONGER_NEEDED LSV1
MARK_MODEL_AS_NO_LONGER_NEEDED LSV2
MARK_MODEL_AS_NO_LONGER_NEEDED LSV3
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
MARK_MODEL_AS_NO_LONGER_NEEDED STREAK
MARK_MODEL_AS_NO_LONGER_NEEDED STREAKC
MARK_MODEL_AS_NO_LONGER_NEEDED IMMMCRAN
MARK_MODEL_AS_NO_LONGER_NEEDED ESPERANT
MARK_MODEL_AS_NO_LONGER_NEEDED GLENDALE
MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
MARK_MODEL_AS_NO_LONGER_NEEDED TRNTRK8_LAS
UNLOAD_SPECIAL_CHARACTER 1

REMOVE_ANIMATION TRAIN
REMOVE_ANIMATION GANGS
REMOVE_BLIP trainstation_s3blip
REMOVE_BLIP mex1_s3blip
REMOVE_BLIP mex2_s3blip
REMOVE_BLIP mex3_s3blip
REMOVE_BLIP mex4_s3blip
REMOVE_BLIP smokecar_s3blip
REMOVE_BLIP smokehouse_s3blip
REMOVE_BLIP bike_s3blip

IF NOT IS_CAR_DEAD train_s3
	SET_TRAIN_FORCED_TO_SLOW_DOWN train_s3 TRUE
ENDIF
RESET_VEHICLE_CAMERA_TWEAK
IF NOT IS_CAR_DEAD bike_s3
	SET_CAR_HEALTH bike_s3 1000
	SET_CAN_BURST_CAR_TYRES bike_s3 TRUE
	SET_CAR_PROOFS bike_s3 FALSE FALSE FALSE FALSE FALSE
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF
RELEASE_WEATHER
IF NOT IS_CAR_DEAD smokecar_s3
	SET_CAR_HEALTH smokecar_s3 1000
	SET_CAN_BURST_CAR_TYRES smokecar_s3 TRUE
ENDIF
MARK_CAR_AS_NO_LONGER_NEEDED bike_s3
MARK_CAR_AS_NO_LONGER_NEEDED smokecar_s3

GET_GAME_TIMER timer_mobile_start
MISSION_HAS_FINISHED
RETURN

 
}



/////////////////////////////////////////////////////		Speed of the train
//IF smoke_s3flag = 2
//	IF NOT IS_CAR_DEAD train_s3
//
//		IF trainspeed_s3flag = 0
//			IF LOCATE_CAR_2D train_s3 2121.57 -1955.66 10.0 10.0 FALSE
//				trainspeed_s3flag = 1
//			ENDIF
//		ENDIF
//		
//		IF trainspeed_s3flag = 1
//			IF LOCATE_CAR_2D train_s3 2200.74 -1817.98 10.0 10.0 FALSE
//				trainspeed_s3flag = 2
//			ENDIF
//		ENDIF
//
//		IF trainspeed_s3flag = 2
//			IF LOCATE_CAR_2D train_s3 2287.55 -1387.63 10.0 10.0 FALSE
//				trainspeed_s3flag = 3
//			ENDIF
//		ENDIF
//
//		IF trainspeed_s3flag = 0
//			IF IS_PLAYER_PLAYING player1
//				GET_CHAR_COORDINATES scplayer playercarx_s3 playercary_s3 playercarz_s3
//			ENDIF
//
//			GET_CAR_COORDINATES train_s3 trainx_s3 trainy_s3 trainz_s3
//			GET_DISTANCE_BETWEEN_COORDS_2D playercarx_s3 playercary_s3 trainx_s3 trainy_s3 distance_s3
//			trainspeed_s3 = 3000.0 / distance_s3 //< THIS WILL SPEED THE CAR UP IF IT GETS CLOSER (INCREASE NUMBER TO MAKE CAR GO FASTER)
//
//			IF trainspeed_s3 > 25.0
//				trainspeed_s3 = 25.0
//			ENDIF
//
//			IF trainspeed_s3 < 12.0
//				trainspeed_s3 = 12.0
//			ENDIF
//
//			SET_TRAIN_CRUISE_SPEED train_s3 trainspeed_s3
//
//		ENDIF
//
//
//		IF trainspeed_s3flag = 1
//			IF IS_PLAYER_PLAYING player1
//				GET_CHAR_COORDINATES scplayer playercarx_s3 playercary_s3 playercarz_s3
//			ENDIF
//
//			GET_CAR_COORDINATES train_s3 trainx_s3 trainy_s3 trainz_s3
//			GET_DISTANCE_BETWEEN_COORDS_2D playercarx_s3 playercary_s3 trainx_s3 trainy_s3 distance_s3
//			trainspeed_s3 = 1000.0 / distance_s3 //< THIS WILL SPEED THE CAR UP IF IT GETS CLOSER (INCREASE NUMBER TO MAKE CAR GO FASTER)
//
//			IF trainspeed_s3 > 25.0
//				trainspeed_s3 = 25.0
//			ENDIF
//
//			IF trainspeed_s3 < 10.0
//				trainspeed_s3 = 10.0
//			ENDIF
//
//			SET_TRAIN_CRUISE_SPEED train_s3 trainspeed_s3
//
//		ENDIF
//
//		IF trainspeed_s3flag = 2
//			IF IS_PLAYER_PLAYING player1
//				GET_CHAR_COORDINATES scplayer playercarx_s3 playercary_s3 playercarz_s3
//			ENDIF
//
//			GET_CAR_COORDINATES train_s3 trainx_s3 trainy_s3 trainz_s3
//			GET_DISTANCE_BETWEEN_COORDS_2D playercarx_s3 playercary_s3 trainx_s3 trainy_s3 distance_s3
//			trainspeed_s3 = 1000.0 / distance_s3 //< THIS WILL SPEED THE CAR UP IF IT GETS CLOSER (INCREASE NUMBER TO MAKE CAR GO FASTER)
//
//			IF trainspeed_s3 > 30.0
//				trainspeed_s3 = 30.0
//			ENDIF
//
//			IF trainspeed_s3 < 12.0
//				trainspeed_s3 = 12.0
//			ENDIF
//
//			SET_TRAIN_CRUISE_SPEED train_s3 trainspeed_s3
//
//		ENDIF
//
//		IF trainspeed_s3flag = 3
//			IF IS_PLAYER_PLAYING player1
//				GET_CHAR_COORDINATES scplayer playercarx_s3 playercary_s3 playercarz_s3
//			ENDIF
//
//			GET_CAR_COORDINATES train_s3 trainx_s3 trainy_s3 trainz_s3
//			GET_DISTANCE_BETWEEN_COORDS_2D playercarx_s3 playercary_s3 trainx_s3 trainy_s3 distance_s3
//			trainspeed_s3 = 1000.0 / distance_s3 //< THIS WILL SPEED THE CAR UP IF IT GETS CLOSER (INCREASE NUMBER TO MAKE CAR GO FASTER)
//
//			IF trainspeed_s3 > 30.0 //35.0
//				trainspeed_s3 = 30.0 //35.0
//			ENDIF
//
//			IF trainspeed_s3 < 12.0
//				trainspeed_s3 = 12.0
//			ENDIF
//
//			SET_TRAIN_CRUISE_SPEED train_s3 trainspeed_s3
//
//		ENDIF
//

////////////////////////////////////////	Player loses Smoke during the chase
//IF smoke_s3flag = 2
//	
//	IF NOT IS_CHAR_DEAD big_smoke
//
//		IF smokegroupchase_s3flag = 0
//		    IF NOT IS_GROUP_MEMBER big_smoke Players_Group
//		        PRINT SMK3_18 5000 1 //You left Smoke behind go get him if you wish the extra firepower will help!
//		        ADD_BLIP_FOR_CHAR big_smoke trainstation_s3blip
//				SET_BLIP_AS_FRIENDLY trainstation_s3blip TRUE
//		        smokegroupchase_s3flag = 1
//		    ENDIF
//		ENDIF
//
//		IF smokegroupchase_s3flag = 1
//			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer big_smoke 10.0 10.0 8.0 FALSE
//				IF IS_CHAR_ON_SCREEN big_smoke
//	    			IF NOT IS_GROUP_MEMBER big_smoke Players_Group
//			           SET_GROUP_MEMBER Players_Group big_smoke 
//					ENDIF
//					//REMOVE_BLIP trainstation_s3blip
//					smokegroupchase_s3flag = 0
//				ENDIF
//			ENDIF
//		ENDIF
//
//	ENDIF
//
//ENDIF

//


///////////////////////////////////////////////////////////////////////////////
////speed_train_if_close_tom_player: //////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
//
//	IF IS_PLAYER_PLAYING player1
//		GET_CHAR_COORDINATES scplayer gosub_target_car_x gosub_target_car_y z
//	ENDIF
//
//	GET_CAR_COORDINATES gosub_car_to_slow gosub_car_to_slow_x gosub_car_to_slow_y z
//	GET_DISTANCE_BETWEEN_COORDS_2D gosub_car_to_slow_x gosub_car_to_slow_y gosub_target_car_x gosub_target_car_y distance
//
//	//gosub_car_to_slow_speed = distance + 3.0 //< THIS WILL SLOW THE CAR DOWN IF IT GETS CLOSER
//	gosub_car_to_slow_speed = 2000.0 / distance //< THIS WILL SPEED THE CAR UP IF IT GETS CLOSER (INCREASE NUMBER TO MAKE CAR GO FASTER)
//
//	IF gosub_car_to_slow_speed > 50.0
//		gosub_car_to_slow_speed = 50.0
//	ENDIF
//	IF gosub_car_to_slow_speed < 15.0
//		gosub_car_to_slow_speed = 15.0
//	ENDIF
//	SET_CAR_CRUISE_SPEED gosub_car_to_slow gosub_car_to_slow_speed
//
///////////////////////////////////////////////////////////////////////////////
////RETURN/////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////



// ****************************************************************************************



//			//third incoming train
//			IF opptrain_s3flag = 6
//				IF LOCATE_CAR_2D train_s3 2526.1 -281.49 20.0 20.0 FALSE
//					IF NOT LOCATE_CHAR_ANY_MEANS_2D scplayer 2832.86 -2.04 40.0 40.0 FALSE
//						CREATE_MISSION_TRAIN 11 2832.86 -2.04 28.79 TRUE opptrain_s3
//						SET_TRAIN_SPEED opptrain_s3 5.0
//						SET_TRAIN_CRUISE_SPEED opptrain_s3 15.0
//						opptrain_s3flag = 7
//					ELSE
//						opptrain_s3flag = 15
//					ENDIF
//				ENDIF
//			ENDIF
//			IF opptrain_s3flag = 7
//				IF NOT IS_CAR_DEAD opptrain_s3
//					IF LOCATE_CAR_2D opptrain_s3 2462.77 -279.44 20.0 20.0 FALSE
//						SET_TRAIN_CRUISE_SPEED opptrain_s3 0.0
//						opptrain_s3flag = 8
//					ENDIF
//				ENDIF
//			ENDIF
//			IF opptrain_s3flag = 8
//				IF NOT IS_CAR_DEAD opptrain_s3
//					IF NOT LOCATE_CHAR_ANY_MEANS_CAR_2D scplayer opptrain_s3 40.0 40.0 FALSE
//						IF NOT IS_CAR_ON_SCREEN opptrain_s3
//							DELETE_MISSION_TRAIN opptrain_s3			
//							opptrain_s3flag = 9
//						ENDIF
//					ENDIF
//				ENDIF
//			ENDIF












  