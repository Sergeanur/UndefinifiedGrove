MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ***********************************    Truth 1   ****************************************
// *********************************** Body Harvest ****************************************
// ***** Player has to steal a combine harvester for thr truth from a rival drugs farm *****
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME truth1

// Mission start stuff

GOSUB mission_start_truth1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_truth1_failed
ENDIF

GOSUB mission_cleanup_truth1

MISSION_END

{
 
// ********************************** Variables for mission ********************************

//variables
LVAR_INT combine_t1
LVAR_INT tractor_t1
LVAR_INT bike_t1
LVAR_INT quad_t1
LVAR_INT combinedriver_t1
LVAR_INT tractordriver_t1
LVAR_FLOAT pipex_t1 
LVAR_FLOAT pipey_t1 
LVAR_FLOAT pipez_t1 
LVAR_INT bail1_t1
LVAR_INT bail2_t1
LVAR_INT bail3_t1
LVAR_INT bail4_t1
LVAR_INT bail5_t1
LVAR_INT bail6_t1
LVAR_INT worker1_t1
LVAR_INT worker2_t1
LVAR_INT worker3_t1
LVAR_INT worker4_t1
LVAR_INT worker5_t1
LVAR_INT worker6_t1
LVAR_INT worker7_t1
LVAR_INT tractorchase1_t1
LVAR_INT tractorchasedriver1_t1
LVAR_INT tractorchase2_t1
LVAR_INT tractorchasedriver2_t1
LVAR_INT haystack1_t1
LVAR_INT haystack2_t1
LVAR_INT haystack3_t1
LVAR_INT haystack4_t1
LVAR_INT haystack5_t1
LVAR_INT haystack6_t1
LVAR_INT haystack7_t1
LVAR_INT chaser1_t1
LVAR_INT chaser2_t1
LVAR_INT chaser3_t1
LVAR_INT chaser4_t1
LVAR_INT chaser5_t1
LVAR_INT chaser6_t1
LVAR_INT chaser7_t1
LVAR_INT chaser8_t1
LVAR_INT chaser9_t1
LVAR_INT chaser10_t1
LVAR_INT chaser11_t1
LVAR_INT chaser12_t1
LVAR_INT chaser13_t1
LVAR_INT chaser14_t1
LVAR_INT chaser15_t1
LVAR_INT chaser16_t1
LVAR_INT chaser17_t1
LVAR_INT chaser18_t1
LVAR_INT chaser19_t1
LVAR_INT chaser20_t1
LVAR_INT walton1_t1
LVAR_INT walton2_t1
LVAR_INT truth_t1
LVAR_INT tractorforplayer_t1
LVAR_INT fieldguard1_t1
LVAR_INT fieldguard2_t1
LVAR_INT fieldguard3_t1
LVAR_INT fieldguard4_t1
LVAR_INT fieldguard5_t1
LVAR_INT fieldguard6_t1
//decision maker
LVAR_INT truth1_DM
LVAR_INT truth1tough_DM
//sequences
LVAR_INT combine_t1seq
LVAR_INT chase_t1seq
LVAR_INT enemycar_t1
LVAR_INT enemy_t1
LVAR_FLOAT enemyx_t1 enemyy_t1 enemyz_t1
LVAR_INT driver_t1
LVAR_INT escapecombine_t1seq
LVAR_INT runfromcombine_t1seq
LVAR_INT attackcombine_t1seq
LVAR_INT sequencetask_t1
//text
LVAR_TEXT_LABEL $text_label_t1
LVAR_INT audio_label_t1
LVAR_TEXT_LABEL $input_text_t1

//blips
LVAR_INT combineblip_t1

//flags
LVAR_INT truth1_flag
LVAR_INT triggeredaction_tflag
LVAR_INT combinedriver_t1flag
LVAR_INT createbail_t1flag
LVAR_INT skipcutscene_t1flag
LVAR_INT foolcompiler_t1flag
LVAR_INT worker1_t1flag
LVAR_INT tractorchase1_t1flag
LVAR_INT tractorchase2_t1flag
LVAR_INT farmerchase1_t1flag
LVAR_INT farmerchase2_t1flag
LVAR_INT combinedriverattack_t1flag
LVAR_INT playerincombine_t1flag
LVAR_INT blipswap_t1flag
LVAR_INT createguyswayback_t1flag
LVAR_INT chaser1_t1flag
LVAR_INT chaser2_t1flag
LVAR_INT chaser3_t1flag
LVAR_INT chaser4_t1flag
LVAR_INT chaser5_t1flag
LVAR_INT chaser6_t1flag
LVAR_INT chaser7_t1flag
LVAR_INT chaser8_t1flag
LVAR_INT chaser9_t1flag
LVAR_INT createguyswayback2_t1flag
LVAR_INT walton2_t1flag
LVAR_INT audio_t1flag
VAR_INT tripskip_t2flag	//do not reset this variable
LVAR_INT progressaudio_t1flag
LVAR_INT handlingudio_t1flag
LVAR_INT skipphonecall_t1flag
// *****************************************************************************************


// **************************************** Mission Start **********************************

mission_start_truth1:

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT TRU1

flag_player_on_mission = 1

WAIT 0


SET_AREA_VISIBLE 12

LOAD_CUTSCENE TRUTH_1
 
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
// ****************************************START OF CUTSCENE********************************



// ****************************************END OF CUTSCENE**********************************

// fades the screen in 



SET_FADING_COLOUR 0 0 0


// request models

REQUEST_MODEL COMBINE
REQUEST_MODEL TRACTOR
REQUEST_MODEL KMB_MARIJUANA
REQUEST_CAR_RECORDING 348
REQUEST_MODEL sw_haybreak02
REQUEST_MODEL CUNTGUN
REQUEST_MODEL WALTON
REQUEST_MODEL SHOVEL

REQUEST_MODEL CWFYHB
REQUEST_MODEL CWMOHB2
REQUEST_MODEL CWMYHB1

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED COMBINE
	OR NOT HAS_MODEL_LOADED TRACTOR
	OR NOT HAS_MODEL_LOADED KMB_MARIJUANA
	OR NOT HAS_MODEL_LOADED CWFYHB
	OR NOT HAS_MODEL_LOADED CWMOHB2
	WAIT 0
ENDWHILE

WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 348
	OR NOT HAS_MODEL_LOADED sw_haybreak02
	OR NOT HAS_MODEL_LOADED CUNTGUN
	OR NOT HAS_MODEL_LOADED CWMYHB1
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED WALTON
	OR NOT HAS_MODEL_LOADED SHOVEL
	WAIT 0
ENDWHILE
 
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
SWITCH_WIDESCREEN OFF

ADD_BLIP_FOR_COORD -1047.593 -1347.254 129.216 combineblip_t1

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY truth1_DM
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE truth1_DM EVENT_DRAGGED_OUT_CAR
ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE truth1_DM EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_KILL_PED_ON_FOOT 50.0 50.0 50.0 50.0 1 1

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH truth1tough_DM
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1

SET_CHAR_COORDINATES scplayer -2195.9 -2258.029 30.0
SET_CHAR_HEADING scplayer 141.0

LOAD_SCENE_IN_DIRECTION -2196.46 -2256.96 31.43 141.0

DO_FADE 500 FADE_IN


// ************************************ Declare Variables *********************************

//flags
truth1_flag = 0
combinedriver_t1flag = 0
createbail_t1flag = 0
skipcutscene_t1flag = 0
foolcompiler_t1flag = 0
worker1_t1flag = 0
tractorchase1_t1flag = 0
tractorchase2_t1flag = 0
farmerchase1_t1flag = 0
farmerchase2_t1flag = 0
combinedriverattack_t1flag = 0
playerincombine_t1flag = 0
blipswap_t1flag = 0
createguyswayback_t1flag = 0
chaser1_t1flag = 0
chaser2_t1flag = 0
chaser3_t1flag = 0
chaser4_t1flag = 0
chaser5_t1flag = 0
chaser6_t1flag = 0
chaser7_t1flag = 0
chaser8_t1flag = 0
chaser9_t1flag = 0
createguyswayback2_t1flag = 0
walton2_t1flag = 0
triggeredaction_tflag = 0
audio_t1flag = 0
progressaudio_t1flag = 0
handlingudio_t1flag = 0
skipphonecall_t1flag = 0
// ****************************************************************************************


//debug set player coordinates
//SET_CHAR_COORDINATES scplayer -1054.05 -1345.78 133.3


PRINT_NOW TRU1_1 10000 1 //~s~Get to the farm where the combine harvester is located.

IF foolcompiler_t1flag = 100
	CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail1_t1
	CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail2_t1
	CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail3_t1
	CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail4_t1
	CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail5_t1
	CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail6_t1
	foolcompiler_t1flag = 101
ENDIF


SET_WANTED_MULTIPLIER 0.3


IF tripskip_t2flag > 0
	SET_UP_SKIP	-1084.219 -1341.156 128.365 257.771
ENDIF

var_int recordflag

truth1_main_mission_loop:

WAIT 0


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_truth1_passed  
ENDIF
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////	Player has to get the combine harvester	////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//IF recordflag = 0
//	IF IS_CHAR_IN_ANY_CAR scplayer
//		STORE_CAR_CHAR_IS_IN scplayer car
//		SET_CAR_COORDINATES car	-1029.287 -1183.826 128.184
//		SET_CAR_HEADING car 90.725
//		recordflag = 1
//	ENDIF
//ENDIF
//IF recordflag = 1
//	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_CTRL
//		IF NOT IS_CAR_DEAD car
//			SET_CAR_FORWARD_SPEED car 10.0
//			recordflag = 2
//		ENDIF
//	ENDIF
//ENDIF

//VIEW_INTEGER_VARIABLE tractorchase2_t1flag tractorchase2_t1flag
//VIEW_INTEGER_VARIABLE tractorchase1_t1flag tractorchase1_t1flag


IF triggeredaction_tflag = 0

	//player in locate
	IF truth1_flag = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -1047.593 -1347.254 129.39 4.0 4.0 4.0 TRUE

			SWITCH_WIDESCREEN ON
			SET_PLAYER_CONTROL PLAYER1 OFF
			REMOVE_BLIP combineblip_t1

			DO_FADE 500 FADE_OUT

			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE

			IF tripskip_t2flag = 0
				tripskip_t2flag = 1
			ENDIF
			CLEAR_SKIP

			IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
			ENDIF

			//create car
			CREATE_CAR COMBINE -1060.2495 -1044.2095 128.2188 combine_t1 //-1063.2495
			SET_CAR_HEADING combine_t1 84.2638
			SET_CAR_STRAIGHT_LINE_DISTANCE combine_t1 100
			CAR_GOTO_COORDINATES combine_t1 -1136.78 -1044.2095 128.2188
			SET_CAR_CRUISE_SPEED combine_t1 7.0
			CREATE_CHAR_INSIDE_CAR combine_t1 PEDTYPE_MISSION1 CWMOHB2 combinedriver_t1			
			WAIT 500
			DO_FADE 1000 FADE_IN

			SET_FIXED_CAMERA_POSITION -1073.8022 -1038.2028 128.4920 0.0 0.0 0.0
			IF NOT IS_CAR_DEAD combine_t1
				POINT_CAMERA_AT_CAR combine_t1 FIXED JUMP_CUT
			ENDIF

			IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				SET_CAR_HEADING car 355.69
			ELSE
				SET_CHAR_HEADING scplayer 355.73
			ENDIF
			
			CREATE_CAR TRACTOR -1043.989 -1312.677 128.772 tractorforplayer_t1
			SET_CAR_HEADING tractorforplayer_t1 1.243
			SET_CAN_BURST_CAR_TYRES tractorforplayer_t1 FALSE
			SET_CAR_HEALTH tractorforplayer_t1 2000

			//create hay stack
			CREATE_OBJECT_NO_OFFSET sw_haybreak02 -1053.7919 -1187.6237 129.396 haystack1_t1 //129.196
			CREATE_OBJECT_NO_OFFSET sw_haybreak02 -1038.5063 -1154.7223 129.699 haystack2_t1
			CREATE_OBJECT_NO_OFFSET sw_haybreak02 -1105.4194 -1112.4508 128.864 haystack3_t1
			CREATE_OBJECT_NO_OFFSET sw_haybreak02 -1182.3462 -1043.7972 129.699 haystack4_t1
			CREATE_OBJECT_NO_OFFSET sw_haybreak02 -1141.3888 -1008.2543 129.735 haystack5_t1
			CREATE_OBJECT_NO_OFFSET sw_haybreak02 -1197.3849 -1084.9507 129.743 haystack6_t1
			CREATE_OBJECT_NO_OFFSET sw_haybreak02 -1161.3527 -1117.1976 129.049 haystack7_t1

			//create tractor 
			CREATE_CAR TRACTOR -1115.569 -1115.231 128.366 tractor_t1
			CREATE_CHAR_INSIDE_CAR tractor_t1 PEDTYPE_MISSION1 CWMOHB2 tractordriver_t1
			SET_CAR_HEADING tractor_t1 274.604
			//create everything else
			
			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1064.341 -1283.63 128.247 worker1_t1	//farmer leaning next to the farm
			SET_CHAR_HEADING worker1_t1 116.09
			TASK_PLAY_ANIM worker1_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_DECISION_MAKER worker1_t1 truth1_DM
			SET_CHAR_HEALTH worker1_t1 50
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker1_t1 100.0
			GIVE_WEAPON_TO_CHAR worker1_t1 WEAPONTYPE_COUNTRYRIFLE 99999

			CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1074.646 -1095.812 128.245 worker2_t1
			SET_CHAR_HEADING worker2_t1 96.069
			TASK_PLAY_ANIM worker2_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_DECISION_MAKER worker2_t1 truth1_DM
			SET_CHAR_HEALTH worker2_t1 50
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker2_t1 100.0

			CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1135.339 -1134.299 128.259 worker4_t1
			SET_CHAR_HEADING worker4_t1 90.157
			TASK_PLAY_ANIM worker4_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_DECISION_MAKER worker4_t1 truth1_DM
			SET_CHAR_HEALTH worker4_t1 50
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker4_t1 100.0

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1121.739 -1095.64 128.247 worker5_t1
			SET_CHAR_HEADING worker5_t1 94.64
			TASK_PLAY_ANIM worker5_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_DECISION_MAKER worker5_t1 truth1_DM
			SET_CHAR_HEALTH worker5_t1 50

			CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1121.713 -1084.165 128.247 worker6_t1
			SET_CHAR_HEADING worker6_t1 79.746
			TASK_PLAY_ANIM worker6_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_DECISION_MAKER worker6_t1 truth1_DM
			SET_CHAR_HEALTH worker6_t1 50
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker6_t1 100.0

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1159.367 -1134.375 128.259 worker7_t1
			SET_CHAR_HEADING worker7_t1 270.749
			TASK_PLAY_ANIM worker7_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_DECISION_MAKER worker7_t1 truth1_DM
			SET_CHAR_HEALTH worker7_t1 50
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker7_t1 100.0
					
			//combine harvester shot
	//		SET_FIXED_CAMERA_POSITION -1073.8022 -1038.2028 128.4920 0.0 0.0 0.0
	//		POINT_CAMERA_AT_CAR combine_t1 FIXED JUMP_CUT
			
			PRINT_NOW TRU1_2 7000 1 //The combine harvester is located at the field towards the back of the farm.

			WAIT 200

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1074.332 -1084.388 128.244 worker3_t1
			SET_CHAR_HEADING worker3_t1 60.659
			TASK_PLAY_ANIM worker3_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
			SET_CHAR_DECISION_MAKER worker3_t1 truth1_DM
			SET_CHAR_HEALTH worker3_t1 50
			SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker3_t1 30.0

			TIMERA = 0
			TIMERB = 0
			truth1_flag = 1
		ENDIF
	ENDIF

	IF TIMERB > 1800
		IF truth1_flag = 1
			//////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////
			skipcutscene_t1flag = 0
			SKIP_CUTSCENE_START
			//////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////
			IF NOT IS_CAR_DEAD tractor_t1
				SET_CAR_STRAIGHT_LINE_DISTANCE tractor_t1 100
				CAR_GOTO_COORDINATES tractor_t1 -1061.0586 -1116.129 128.366
				SET_CAR_CRUISE_SPEED tractor_t1 10.0
			ENDIF

			TIMERB = 0
			truth1_flag = 2
		ENDIF
	ENDIF

	//second shot showing workers
	IF truth1_flag = 2
		IF TIMERB > 5000
			
			SET_FIXED_CAMERA_POSITION -1057.8749 -1120.5917 136.4117 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1058.7036 -1120.0885 136.1667 JUMP_CUT

			IF NOT IS_CAR_DEAD combine_t1
				SET_CAR_COORDINATES combine_t1 -1082.138 -1046.893 129.29
				SET_CAR_HEADING combine_t1 84.2638
				CAR_GOTO_COORDINATES combine_t1 -1195.58 -1045.87 129.1
				SET_CAR_CRUISE_SPEED combine_t1 7.0
			ENDIF

			PRINT_NOW TRU1_3 7000 1 //There are several groups of survivalists working, they will not take kindly to any strangers.
			TIMERB = 0
			truth1_flag = 3
		ENDIF
	ENDIF

	//third shot showing the entrance
	IF truth1_flag = 3
		IF TIMERB > 7000
			SET_FIXED_CAMERA_POSITION -1035.7823 -1331.0408 134.0494 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1036.1844 -1330.1340 133.9223 JUMP_CUT
			PRINT_NOW TRU1_4 10000 1 //Get into the farm and steal the combine harvester.
			TIMERB = 0
			truth1_flag = 4
		ENDIF
	ENDIF

	IF truth1_flag = 4
		IF TIMERB > 5000
			
			//////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////
			skipcutscene_t1flag = 1
			SKIP_CUTSCENE_END
			//////////////////////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////////////////////
			
			//PLAYER HAS SKIPPED CUTSCENE
			IF skipcutscene_t1flag = 0

				DO_FADE 500 FADE_OUT
				
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE

				DELETE_CAR combine_t1
				DELETE_CAR tractor_t1
				DELETE_OBJECT bail1_t1
				DELETE_OBJECT bail2_t1
				DELETE_OBJECT bail3_t1
				DELETE_OBJECT bail4_t1
				DELETE_OBJECT bail5_t1
				DELETE_OBJECT bail6_t1
				DELETE_CHAR combinedriver_t1
				DELETE_CHAR worker1_t1
				DELETE_CHAR worker2_t1
				DELETE_CHAR worker3_t1
				DELETE_CHAR worker4_t1
				DELETE_CHAR worker5_t1
				DELETE_CHAR worker6_t1
				DELETE_CHAR worker7_t1

				CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1064.341 -1283.63 128.247 worker1_t1	//farmer leaning next to the farm
				SET_CHAR_HEADING worker1_t1 116.09
				TASK_PLAY_ANIM worker1_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER worker1_t1 truth1_DM
				SET_CHAR_HEALTH worker1_t1 50
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker1_t1 100.0
				GIVE_WEAPON_TO_CHAR worker1_t1 WEAPONTYPE_COUNTRYRIFLE 99999

				CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1074.646 -1095.812 128.245 worker2_t1
				SET_CHAR_HEADING worker2_t1 96.069
				TASK_PLAY_ANIM worker2_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER worker2_t1 truth1_DM
				SET_CHAR_HEALTH worker2_t1 50
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker2_t1 100.0

				CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1074.332 -1084.388 128.244 worker3_t1	//on road 
				SET_CHAR_HEADING worker3_t1 60.659
				TASK_PLAY_ANIM worker3_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER worker3_t1 truth1_DM
				SET_CHAR_HEALTH worker3_t1 50
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker3_t1 100.0

				CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1135.339 -1134.299 128.259 worker4_t1
				SET_CHAR_HEADING worker4_t1 90.157
				TASK_PLAY_ANIM worker4_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER worker4_t1 truth1_DM
				SET_CHAR_HEALTH worker4_t1 50
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker4_t1 100.0

				CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1121.739 -1095.64 128.247 worker5_t1
				SET_CHAR_HEADING worker5_t1 94.64
				TASK_PLAY_ANIM worker5_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER worker5_t1 truth1_DM
				SET_CHAR_HEALTH worker5_t1 50

				CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1121.713 -1084.165 128.247 worker6_t1 //on road
				SET_CHAR_HEADING worker6_t1 79.746
				TASK_PLAY_ANIM worker6_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER worker6_t1 truth1_DM
				SET_CHAR_HEALTH worker6_t1 50
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker6_t1 100.0

				CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1159.367 -1134.375 128.259 worker7_t1	//on road
				SET_CHAR_HEADING worker7_t1 270.749
				TASK_PLAY_ANIM worker7_t1 ATM PED 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER worker7_t1 truth1_DM
				SET_CHAR_HEALTH worker7_t1 50
				SET_FOLLOW_NODE_THRESHOLD_DISTANCE worker7_t1 100.0

				LOAD_SCENE -1049.08 -1268.08 127.81

				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SWITCH_WIDESCREEN OFF
				CLEAR_PRINTS

				DO_FADE 500 FADE_IN
				
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE

			ENDIF

			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL PLAYER1 ON
			PRINT_NOW TRU1_4 7000 1 //Get into the farm and steal the combine harvester.

			DELETE_CAR combine_t1
			DELETE_CHAR combinedriver_t1
			DELETE_CHAR tractordriver_t1
			DELETE_CAR tractor_t1


			CREATE_CAR COMBINE -1023.706 -933.357 129.292 combine_t1
			SET_CAR_HEADING combine_t1 177.348
			SET_CAR_STRAIGHT_LINE_DISTANCE combine_t1 20
			CREATE_CHAR_INSIDE_CAR combine_t1 PEDTYPE_MISSION1 CWMOHB2 combinedriver_t1
			SET_CHAR_DECISION_MAKER combinedriver_t1 truth1_DM
			SET_CHAR_ACCURACY combinedriver_t1 30
			SET_CAR_HEALTH combine_t1 9000
			ADD_BLIP_FOR_CAR combine_t1 combineblip_t1
			SET_BLIP_AS_FRIENDLY combineblip_t1 TRUE
			SET_CAN_BURST_CAR_TYRES combine_t1 FALSE
			 
			//chase tractors
			CREATE_CAR TRACTOR -1029.287 -1183.826 128.184 tractorchase1_t1 //this is the recorded car
			SET_CAR_HEADING tractorchase1_t1 90.725
			CREATE_CHAR_INSIDE_CAR tractorchase1_t1 PEDTYPE_MISSION1 CWMYHB1 tractorchasedriver1_t1
			SET_CHAR_DECISION_MAKER tractorchasedriver1_t1 truth1_DM
			GIVE_WEAPON_TO_CHAR	tractorchasedriver1_t1 WEAPONTYPE_SHOVEL 1
			SET_CHAR_ACCURACY tractorchasedriver1_t1 50

			CREATE_CAR TRACTOR -1067.1282 -1153.5743 128.2188 tractorchase2_t1 //script controlled -> -1045.1467 -1152.7241 127.5258 275.8103
			SET_CAR_HEADING tractorchase2_t1 272.1696
			CREATE_CHAR_INSIDE_CAR tractorchase2_t1 PEDTYPE_MISSION1 CWFYHB tractorchasedriver2_t1
			SET_CHAR_DECISION_MAKER tractorchasedriver2_t1 truth1_DM
			GIVE_WEAPON_TO_CHAR	tractorchasedriver2_t1 WEAPONTYPE_SHOVEL 1
			SET_CHAR_ACCURACY tractorchasedriver2_t1 50
			triggeredaction_tflag = 1

			IF NOT IS_CHAR_DEAD worker2_t1
				GIVE_WEAPON_TO_CHAR worker2_t1 WEAPONTYPE_COUNTRYRIFLE 99999
			ENDIF
			IF NOT IS_CHAR_DEAD worker4_t1
				GIVE_WEAPON_TO_CHAR worker4_t1 WEAPONTYPE_SHOVEL 1
			ENDIF
			IF NOT IS_CHAR_DEAD worker5_t1
				GIVE_WEAPON_TO_CHAR worker5_t1 WEAPONTYPE_SHOVEL 1
			ENDIF

			IF NOT IS_CHAR_DEAD worker3_t1
				SET_CHAR_COORDINATES worker3_t1 -1070.71 -1126.36 128.23
				SET_CHAR_HEADING worker3_t1 345.592
				GIVE_WEAPON_TO_CHAR worker3_t1 WEAPONTYPE_COUNTRYRIFLE 99999
			ENDIF	
			IF NOT IS_CHAR_DEAD worker6_t1
				SET_CHAR_COORDINATES worker6_t1 -1111.92 -1107.797 127.892
				SET_CHAR_HEADING worker6_t1 212.87
				GIVE_WEAPON_TO_CHAR worker6_t1 WEAPONTYPE_COUNTRYRIFLE 99999
			ENDIF
			IF NOT IS_CHAR_DEAD worker7_t1
				SET_CHAR_COORDINATES worker7_t1 -1154.72 -1116.097 127.27
				SET_CHAR_HEADING worker7_t1 238.5
				GIVE_WEAPON_TO_CHAR worker7_t1 WEAPONTYPE_COUNTRYRIFLE 99999
			ENDIF

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1043.28 -1055.5 128.22 fieldguard1_t1 //bottom right
			SET_CHAR_HEADING fieldguard1_t1 175.56
			GIVE_WEAPON_TO_CHAR fieldguard1_t1 WEAPONTYPE_COUNTRYRIFLE 99999
			SET_CHAR_DECISION_MAKER fieldguard1_t1 truth1tough_DM
			SET_SENSE_RANGE fieldguard1_t1 80.0

			CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1147.91 -1023.34 128.22 fieldguard2_t1 //bottom left
			GIVE_WEAPON_TO_CHAR fieldguard2_t1 WEAPONTYPE_SHOVEL 1
			SET_CHAR_DECISION_MAKER fieldguard2_t1 truth1tough_DM
			SET_SENSE_RANGE fieldguard2_t1 80.0

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1094.89 -999.528 128.21 fieldguard3_t1 //middle
			SET_CHAR_HEADING fieldguard3_t1 175.56
			GIVE_WEAPON_TO_CHAR fieldguard3_t1 WEAPONTYPE_SHOVEL 1
			SET_CHAR_ACCURACY fieldguard3_t1 50
			SET_CHAR_DECISION_MAKER fieldguard3_t1 truth1tough_DM
			SET_SENSE_RANGE fieldguard3_t1 150.0

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1181.3696 -992.4972 128.2188 fieldguard4_t1 //middle
			SET_CHAR_HEADING fieldguard4_t1 170.5759
			GIVE_WEAPON_TO_CHAR fieldguard4_t1 WEAPONTYPE_COUNTRYRIFLE 1
			SET_CHAR_ACCURACY fieldguard4_t1 50
			SET_CHAR_DECISION_MAKER fieldguard4_t1 truth1tough_DM
			SET_SENSE_RANGE fieldguard4_t1 150.0

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1008.9893 -998.6920 128.2188 fieldguard5_t1 //middle
			SET_CHAR_HEADING fieldguard5_t1 170.5759
			GIVE_WEAPON_TO_CHAR fieldguard5_t1 WEAPONTYPE_SHOVEL 1
			SET_CHAR_ACCURACY fieldguard5_t1 50
			SET_CHAR_DECISION_MAKER fieldguard5_t1 truth1tough_DM
			SET_SENSE_RANGE fieldguard5_t1 150.0

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1151.0012 -1011.5167 128.2188 fieldguard6_t1 //middle
			SET_CHAR_HEADING fieldguard6_t1 170.5759
			GIVE_WEAPON_TO_CHAR fieldguard6_t1 WEAPONTYPE_COUNTRYRIFLE 1
			SET_CHAR_ACCURACY fieldguard6_t1 50
			SET_CHAR_DECISION_MAKER fieldguard6_t1 truth1tough_DM
			SET_SENSE_RANGE fieldguard6_t1 150.0


			truth1_flag = 5
		ENDIF
	ENDIF

ENDIF

IF triggeredaction_tflag = 1

	/////////////////////////////////////////////	hassling farmer	//////////////////////////////////////////////////
	IF worker1_t1flag = 0
		IF NOT IS_CHAR_DEAD worker1_t1
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D worker1_t1 scplayer 15.0 15.0 FALSE
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker1_t1 scplayer
			OR IS_CHAR_IN_AREA_2D scplayer -1074.33 -1321.1 -1022.5 -1271.43 FALSE
				audio_t1flag = 1
				TASK_KILL_CHAR_ON_FOOT worker1_t1 scplayer 
				worker1_t1flag = 1
			ENDIF
		ENDIF
	ENDIF
	/////////////////////////////////////////////	chasing tractors	//////////////////////////////////////////////

	IF IS_CHAR_IN_AREA_2D scplayer -1096.488 -1170.56 -1032.682 -1249.599 FALSE

		IF tractorchase1_t1flag = 0
			IF NOT IS_CAR_DEAD tractorchase1_t1
				IF NOT IS_CHAR_DEAD tractorchasedriver1_t1
					IF IS_CHAR_SITTING_IN_CAR tractorchasedriver1_t1 tractorchase1_t1
						START_PLAYBACK_RECORDED_CAR tractorchase1_t1 348
						tractorchase1_t1flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF tractorchase2_t1flag = 0
			IF NOT IS_CAR_DEAD tractorchase2_t1
				IF NOT IS_CHAR_DEAD tractorchasedriver2_t1
					IF IS_CHAR_SITTING_IN_CAR tractorchasedriver2_t1 tractorchase2_t1
						SET_CAR_FORWARD_SPEED tractorchase2_t1 12.0
						TASK_CAR_DRIVE_TO_COORD tractorchasedriver2_t1 tractorchase2_t1 -1045.1467 -1152.7241 127.5258 20.0 MODE_NORMAL FALSE DRIVINGMODE_STOPFORCARS
						tractorchase2_t1flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF tractorchase1_t1flag = 1
		IF NOT IS_CAR_DEAD tractorchase1_t1
			IF NOT IS_CHAR_DEAD tractorchasedriver1_t1
				IF IS_CHAR_SITTING_IN_CAR tractorchasedriver1_t1 tractorchase1_t1
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR tractorchase1_t1
						audio_t1flag = 8
						enemy_t1 = tractorchasedriver1_t1 
						enemycar_t1 = tractorchase1_t1
						GOSUB chase_t1label 
						tractorchase1_t1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF tractorchase2_t1flag = 1
		IF NOT IS_CAR_DEAD tractorchase2_t1
			IF NOT IS_CHAR_DEAD tractorchasedriver2_t1
				IF IS_CHAR_SITTING_IN_CAR tractorchasedriver2_t1 tractorchase2_t1
					IF LOCATE_CAR_2D tractorchase2_t1 -1045.1467 -1152.7241 6.0 6.0 FALSE
						enemy_t1 = tractorchasedriver2_t1 
						enemycar_t1 = tractorchase2_t1
						GOSUB chase_t1label 
						tractorchase2_t1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	/////////////////////////////////////////////	chasing tractor AI		/////////////////////////////////////////////

	IF tractorchase1_t1flag = 2
		IF NOT IS_CAR_DEAD tractorchase1_t1
			IF NOT IS_CHAR_DEAD tractorchasedriver1_t1
				IF IS_CHAR_SITTING_IN_CAR tractorchasedriver1_t1 tractorchase1_t1
					IF IS_CHAR_TOUCHING_CHAR tractorchasedriver1_t1	scplayer
						TASK_CAR_TEMP_ACTION tractorchasedriver1_t1 tractorchase1_t1 TEMPACT_REVERSE 3000
						tractorchase1_t1flag = 3
					ENDIF
				ENDIF
			ENDIF 
		ENDIF
	ENDIF
	IF tractorchase1_t1flag = 3
		IF NOT IS_CAR_DEAD tractorchase1_t1
			IF NOT IS_CHAR_DEAD tractorchasedriver1_t1
				IF IS_CHAR_SITTING_IN_CAR tractorchasedriver1_t1 tractorchase1_t1
					GET_SCRIPT_TASK_STATUS tractorchasedriver1_t1 TASK_CAR_TEMP_ACTION sequencetask_t1
						IF sequencetask_t1 = FINISHED_TASK 
							enemy_t1 = tractorchasedriver1_t1 
							enemycar_t1 = tractorchase1_t1
							GOSUB chase_t1label 
							tractorchase1_t1flag = 2
						ENDIF
				ENDIF 
			ENDIF
		ENDIF
	ENDIF

	IF tractorchase2_t1flag = 2
		IF NOT IS_CAR_DEAD tractorchase2_t1
			IF NOT IS_CHAR_DEAD tractorchasedriver2_t1
				IF IS_CHAR_SITTING_IN_CAR tractorchasedriver2_t1 tractorchase2_t1
					IF IS_CHAR_TOUCHING_CHAR tractorchasedriver2_t1	scplayer
						TASK_CAR_TEMP_ACTION tractorchasedriver2_t1 tractorchase2_t1 TEMPACT_REVERSE 3000
						tractorchase2_t1flag = 3
					ENDIF
				ENDIF
			ENDIF 
		ENDIF
	ENDIF
	IF tractorchase2_t1flag = 3
		IF NOT IS_CAR_DEAD tractorchase2_t1
			IF NOT IS_CHAR_DEAD tractorchasedriver2_t1
				IF IS_CHAR_SITTING_IN_CAR tractorchasedriver2_t1 tractorchase2_t1
					GET_SCRIPT_TASK_STATUS tractorchasedriver2_t1 TASK_CAR_TEMP_ACTION sequencetask_t1
						IF sequencetask_t1 = FINISHED_TASK 
							enemy_t1 = tractorchasedriver2_t1 
							enemycar_t1 = tractorchase2_t1
							GOSUB chase_t1label 
							tractorchase2_t1flag = 2
						ENDIF
				ENDIF 
			ENDIF
		ENDIF
	ENDIF

	/////////////////////////////////////////////	chasing farmers		//////////////////////////////////////////////

	IF farmerchase1_t1flag = 0


		IF IS_CHAR_IN_AREA_2D scplayer -1033.47 -1067.18 -1202.46 -1149.65 FALSE

			IF IS_CHAR_SHOOTING scplayer 
				farmerchase1_t1flag = 1
			ENDIF

			IF IS_PLAYER_TARGETTING_ANYTHING PLAYER1 
				farmerchase1_t1flag = 1
			ENDIF

		ENDIF

		IF IS_CHAR_IN_AREA_2D scplayer -1033.47 -1067.18 -1202.46 -1149.65 FALSE //-1063.7 -1064.75 -1202.46 -1149.65
			farmerchase1_t1flag = 1
		ENDIF

		IF DOES_CHAR_EXIST worker2_t1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker2_t1 scplayer
				farmerchase1_t1flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST worker2_t1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker2_t1 scplayer
				farmerchase1_t1flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST worker3_t1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker3_t1 scplayer
				farmerchase1_t1flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST worker4_t1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker4_t1 scplayer
				farmerchase1_t1flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST worker5_t1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker5_t1 scplayer
				farmerchase1_t1flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST worker6_t1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker6_t1 scplayer
				farmerchase1_t1flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST worker7_t1
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR worker7_t1 scplayer
				farmerchase1_t1flag = 1
			ENDIF
		ENDIF

	ENDIF

	IF farmerchase1_t1flag = 1

		audio_t1flag = 12

		IF NOT IS_CHAR_DEAD worker2_t1
			TASK_KILL_CHAR_ON_FOOT worker2_t1 scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD worker3_t1
			TASK_KILL_CHAR_ON_FOOT worker3_t1 scplayer
			SET_CHAR_ACCURACY worker3_t1 50
		ENDIF	
		IF NOT IS_CHAR_DEAD worker4_t1
			TASK_KILL_CHAR_ON_FOOT worker4_t1 scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD worker5_t1
			TASK_KILL_CHAR_ON_FOOT worker5_t1 scplayer
		ENDIF
		IF NOT IS_CHAR_DEAD worker6_t1
			TASK_KILL_CHAR_ON_FOOT worker6_t1 scplayer
			SET_CHAR_ACCURACY worker6_t1 60
		ENDIF
		IF NOT IS_CHAR_DEAD worker7_t1
			TASK_KILL_CHAR_ON_FOOT worker7_t1 scplayer
			SET_CHAR_ACCURACY worker7_t1 70
		ENDIF

		farmerchase1_t1flag = 2
	ENDIF

	////////////////////////////////////////	combine harvester driver	//////////////////////////////////////////
	IF combinedriverattack_t1flag = 0
		IF NOT IS_CHAR_DEAD combinedriver_t1
			IF NOT IS_CAR_DEAD combine_t1
				IF IS_CHAR_SITTING_IN_CAR combinedriver_t1 combine_t1
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS combinedriver_t1 0.0 10.0 0.0 player_x player_y player_z
						IF LOCATE_STOPPED_CHAR_ANY_MEANS_2D scplayer player_x player_y 12.0 12.0 FALSE
						OR LOCATE_CHAR_ANY_MEANS_2D scplayer player_x player_y 12.0 12.0 FALSE
							GIVE_WEAPON_TO_CHAR combinedriver_t1 WEAPONTYPE_COUNTRYRIFLE 50
							SET_CHAR_ACCURACY combinedriver_t1 20
							TASK_KILL_CHAR_ON_FOOT combinedriver_t1 scplayer
							audio_t1flag = 23
							combinedriverattack_t1flag = 1
						ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	////////////////////////////////////////	combine harvester movement	//////////////////////////////////////////
	IF truth1_flag > 4

		IF combinedriver_t1flag = 0
			IF combinedriverattack_t1flag = 0
				IF NOT IS_CHAR_DEAD combinedriver_t1
					IF NOT IS_CAR_DEAD combine_t1
						IF IS_CHAR_SITTING_IN_CAR combinedriver_t1 combine_t1
							GOSUB combine_t1label
							combinedriver_t1flag = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF combinedriver_t1flag = 1
			IF NOT IS_CHAR_DEAD combinedriver_t1
				IF NOT IS_CAR_DEAD combine_t1
					IF IS_CHAR_SITTING_IN_CAR combinedriver_t1 combine_t1
						IF LOCATE_CAR_2D combine_t1 -1031.46 -935.51 10.0 10.0 FALSE
							combinedriver_t1flag = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF combinedriver_t1flag >= 0
		IF NOT IS_CAR_DEAD combine_t1
	 		IF IS_CAR_IN_AREA_2D combine_t1 -1201.518 -926.777 -1016.98 -1052.652 FALSE
				GET_DRIVER_OF_CAR combine_t1 driver_t1
					IF NOT driver_t1 = -1

						IF createbail_t1flag = 100
							CREATE_OBJECT KMB_MARIJUANA 1.1 2.2 3.3 bail1_t1	//fool compiler
							CREATE_OBJECT KMB_MARIJUANA 1.1 2.2 3.3 bail2_t1	//fool compiler
							CREATE_OBJECT KMB_MARIJUANA 1.1 2.2 3.3 bail3_t1	//fool compiler
							CREATE_OBJECT KMB_MARIJUANA 1.1 2.2 3.3 bail4_t1	//fool compiler
							CREATE_OBJECT KMB_MARIJUANA 1.1 2.2 3.3 bail5_t1	//fool compiler
							CREATE_OBJECT KMB_MARIJUANA 1.1 2.2 3.3 bail6_t1	//fool compiler
						ENDIF

						IF createbail_t1flag = 0
							IF TIMERA > 1250
								IF DOES_OBJECT_EXIST bail1_t1
									DELETE_OBJECT bail1_t1
								ENDIF
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS combine_t1 -1.3 -3.3.9 1.3 pipex_t1 pipey_t1 pipez_t1 
								CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail1_t1
								SET_OBJECT_DYNAMIC bail1_t1 TRUE
								SET_OBJECT_VELOCITY bail1_t1 0.0 0.0 -1.0
								ROTATE_OBJECT bail1_t1 68.0 10.0 FALSE
								SORT_OUT_OBJECT_COLLISION_WITH_CAR bail1_t1 combine_t1 
								TIMERA = 0
								createbail_t1flag = 1
							ENDIF
						ENDIF

						IF createbail_t1flag = 1
							IF TIMERA > 2250
								IF DOES_OBJECT_EXIST bail2_t1
									DELETE_OBJECT bail2_t1
								ENDIF
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS combine_t1 -1.3 -3.3 1.3 pipex_t1 pipey_t1 pipez_t1 
								CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail2_t1
								SET_OBJECT_DYNAMIC bail2_t1 TRUE
								SET_OBJECT_VELOCITY bail2_t1 0.0 0.0 -1.0
								ROTATE_OBJECT bail2_t1 73.0 10.0 FALSE
								SORT_OUT_OBJECT_COLLISION_WITH_CAR bail2_t1 combine_t1 
								createbail_t1flag = 2
							ENDIF
						ENDIF

						IF createbail_t1flag = 2
							IF TIMERA > 4250
								IF DOES_OBJECT_EXIST bail3_t1
									DELETE_OBJECT bail3_t1
								ENDIF
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS combine_t1 -1.3 -3.3 1.3 pipex_t1 pipey_t1 pipez_t1 
								CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail3_t1
								SET_OBJECT_DYNAMIC bail3_t1 TRUE
								SET_OBJECT_VELOCITY bail3_t1 0.0 0.0 -1.0
								ROTATE_OBJECT bail3_t1 90.0 10.0 FALSE
								SORT_OUT_OBJECT_COLLISION_WITH_CAR bail3_t1 combine_t1 
								createbail_t1flag = 3
							ENDIF
						ENDIF	

						IF createbail_t1flag = 3
							IF TIMERA > 6250
								IF DOES_OBJECT_EXIST bail4_t1
									DELETE_OBJECT bail4_t1
								ENDIF
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS combine_t1 -1.3 -3.3 1.3 pipex_t1 pipey_t1 pipez_t1 
								CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail4_t1
								SET_OBJECT_DYNAMIC bail4_t1 TRUE
								SET_OBJECT_VELOCITY bail4_t1 0.0 0.0 -1.0
								ROTATE_OBJECT bail4_t1 180.0 10.0 FALSE
								SORT_OUT_OBJECT_COLLISION_WITH_CAR bail4_t1 combine_t1 
								createbail_t1flag = 4
							ENDIF
						ENDIF

						IF createbail_t1flag = 4
							IF TIMERA > 8250
								IF DOES_OBJECT_EXIST bail5_t1
									DELETE_OBJECT bail5_t1
								ENDIF
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS combine_t1 -1.3 -3.3 1.3 pipex_t1 pipey_t1 pipez_t1 
								CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail5_t1
								SET_OBJECT_DYNAMIC bail5_t1 TRUE
								SET_OBJECT_VELOCITY bail5_t1 0.0 0.0 -1.0
								ROTATE_OBJECT bail5_t1 80.0 10.0 FALSE
								SORT_OUT_OBJECT_COLLISION_WITH_CAR bail5_t1 combine_t1 
								createbail_t1flag = 5
							ENDIF
						ENDIF

						IF createbail_t1flag = 5
							IF TIMERA > 10250
								IF DOES_OBJECT_EXIST bail6_t1
									DELETE_OBJECT bail6_t1
								ENDIF
								GET_OFFSET_FROM_CAR_IN_WORLD_COORDS combine_t1 -1.3 -3.3 1.3 pipex_t1 pipey_t1 pipez_t1 
								CREATE_OBJECT KMB_MARIJUANA pipex_t1 pipey_t1 pipez_t1 bail6_t1
								SET_OBJECT_DYNAMIC bail6_t1 TRUE
								SET_OBJECT_VELOCITY bail6_t1 0.0 0.0 -1.0
								ROTATE_OBJECT bail6_t1 180.0 10.0 FALSE
								SORT_OUT_OBJECT_COLLISION_WITH_CAR bail6_t1 combine_t1 
								TIMERA = 0
								createbail_t1flag = 0
							ENDIF
						ENDIF

					ENDIF
			ENDIF
		ENDIF
	ENDIF
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//////////////////////////////////////////	Create guys for the chase on the way back	///////////////////////////////////////

	IF createguyswayback_t1flag = 0
		IF playerincombine_t1flag = 1

			DELETE_CHAR worker1_t1
			DELETE_CHAR worker2_t1
			DELETE_CHAR worker3_t1
			DELETE_CHAR worker4_t1
			DELETE_CHAR worker5_t1

			OPEN_SEQUENCE_TASK attackcombine_t1seq
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_KILL_CHAR_ON_FOOT -1 scplayer
			CLOSE_SEQUENCE_TASK attackcombine_t1seq 

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1205.8123 -1092.7255 127.2578 chaser1_t1
			SET_CHAR_HEADING chaser1_t1 359.4852 
			GIVE_WEAPON_TO_CHAR chaser1_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser1_t1 truth1_DM
			SET_CHAR_HEALTH chaser1_t1 50
			PERFORM_SEQUENCE_TASK chaser1_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1205.8123 -1090.7255 127.2578 chaser2_t1
			SET_CHAR_HEADING chaser2_t1 359.4852 
			GIVE_WEAPON_TO_CHAR chaser2_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser2_t1 truth1_DM
			SET_CHAR_HEALTH chaser2_t1 50
			PERFORM_SEQUENCE_TASK chaser2_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1181.8000 -1116.7347 127.2578 chaser3_t1
			SET_CHAR_HEADING chaser3_t1 79.3899
			GIVE_WEAPON_TO_CHAR chaser3_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser3_t1 truth1_DM
			SET_CHAR_HEALTH chaser3_t1 50
			PERFORM_SEQUENCE_TASK chaser3_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1211.4215 -1090.9771 127.2578 chaser4_t1
			SET_CHAR_HEADING chaser4_t1 2.4516
			GIVE_WEAPON_TO_CHAR chaser4_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser4_t1 truth1_DM
			SET_CHAR_HEALTH chaser4_t1 50
			PERFORM_SEQUENCE_TASK chaser4_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1179.2832 -1113.8230 127.2578 chaser5_t1
			SET_CHAR_HEADING chaser5_t1 74.1823
			GIVE_WEAPON_TO_CHAR chaser5_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser5_t1 truth1_DM
			SET_CHAR_HEALTH chaser5_t1 50
			PERFORM_SEQUENCE_TASK chaser5_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1145.6510 -1111.6699 127.2595 chaser6_t1 //-1113.777 1108.22 128.22
			SET_CHAR_HEADING chaser6_t1 90.0989
			GIVE_WEAPON_TO_CHAR chaser6_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser6_t1 truth1_DM
			SET_CHAR_HEALTH chaser6_t1 50
			PERFORM_SEQUENCE_TASK chaser6_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1146.6011 -1114.9557 127.2595 chaser7_t1 //-1055.94 -1120.62 128.56
			SET_CHAR_HEADING chaser7_t1 102.0699
			GIVE_WEAPON_TO_CHAR chaser7_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser7_t1 truth1_DM
			SET_CHAR_HEALTH chaser7_t1 50
			PERFORM_SEQUENCE_TASK chaser7_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1051.4973 -1133.6509 127.2669 chaser8_t1
			SET_CHAR_HEADING chaser8_t1 10.9064
			GIVE_WEAPON_TO_CHAR chaser8_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser8_t1 truth1_DM
			SET_CHAR_HEALTH chaser8_t1 50
			PERFORM_SEQUENCE_TASK chaser8_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1048.5880 -1131.9415 127.2595 chaser9_t1
			SET_CHAR_HEADING chaser9_t1 57.544
			GIVE_WEAPON_TO_CHAR chaser9_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser9_t1 truth1_DM
			SET_CHAR_HEALTH chaser9_t1 50
			PERFORM_SEQUENCE_TASK chaser9_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1118.8184 -1116.1959 127.2595 chaser10_t1
			SET_CHAR_HEADING chaser10_t1 90.5502
			GIVE_WEAPON_TO_CHAR chaser10_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser10_t1 truth1_DM
			SET_CHAR_HEALTH chaser10_t1 50
			PERFORM_SEQUENCE_TASK chaser10_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1120.9978 -1112.7286 127.2595 chaser11_t1
			SET_CHAR_HEADING chaser11_t1 57.544
			GIVE_WEAPON_TO_CHAR chaser11_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser11_t1 truth1_DM
			SET_CHAR_HEALTH chaser11_t1 50
			PERFORM_SEQUENCE_TASK chaser11_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1115.5956 -1115.5242 127.2595 chaser12_t1
			SET_CHAR_HEADING chaser12_t1 57.544
			GIVE_WEAPON_TO_CHAR chaser12_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser12_t1 truth1_DM
			SET_CHAR_HEALTH chaser12_t1 50
			PERFORM_SEQUENCE_TASK chaser12_t1 attackcombine_t1seq

			CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1117.8945 -1111.2660 127.2595 chaser13_t1
			SET_CHAR_HEADING chaser13_t1 57.544
			GIVE_WEAPON_TO_CHAR chaser13_t1 WEAPONTYPE_COUNTRYRIFLE 9999
			SET_CHAR_DECISION_MAKER chaser13_t1 truth1_DM
			SET_CHAR_HEALTH chaser13_t1 50
			PERFORM_SEQUENCE_TASK chaser13_t1 attackcombine_t1seq
			CLEAR_SEQUENCE_TASK attackcombine_t1seq

			createguyswayback_t1flag = 1
		ENDIF
	ENDIF

	IF createguyswayback_t1flag = 1
		IF NOT IS_CAR_DEAD combine_t1

			IF chaser1_t1flag = 0
				IF LOCATE_CAR_2D combine_t1 -1205.8123 -1090.7255 20.0 20.0 FALSE

					OPEN_SEQUENCE_TASK escapecombine_t1seq
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_SMART_FLEE_CHAR -1 scplayer 30.0 30000
					CLOSE_SEQUENCE_TASK escapecombine_t1seq 
					
					IF NOT IS_CHAR_DEAD	chaser1_t1
						PERFORM_SEQUENCE_TASK chaser1_t1 escapecombine_t1seq
					ENDIF

					IF NOT IS_CHAR_DEAD	chaser2_t1
						PERFORM_SEQUENCE_TASK chaser2_t1 escapecombine_t1seq
					ENDIF
					
					audio_t1flag = 28

					CLEAR_SEQUENCE_TASK escapecombine_t1seq

					chaser1_t1flag = 1
				ENDIF
			ENDIF

			IF chaser2_t1flag = 0
				IF LOCATE_CAR_2D combine_t1 -1181.8000 -1116.7347 20.0 20.0 FALSE

					OPEN_SEQUENCE_TASK escapecombine_t1seq
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_SMART_FLEE_CHAR -1 scplayer 30.0 30000
					CLOSE_SEQUENCE_TASK escapecombine_t1seq 
					
					IF NOT IS_CHAR_DEAD	chaser3_t1
						PERFORM_SEQUENCE_TASK chaser3_t1 escapecombine_t1seq
					ENDIF

					IF NOT IS_CHAR_DEAD	chaser5_t1
						PERFORM_SEQUENCE_TASK chaser5_t1 escapecombine_t1seq
					ENDIF

					CLEAR_SEQUENCE_TASK escapecombine_t1seq

					chaser2_t1flag = 1
				ENDIF
			ENDIF

			IF chaser3_t1flag = 0
				IF LOCATE_CAR_2D combine_t1 -1211.4215 -1090.9771 25.0 25.0 FALSE

					OPEN_SEQUENCE_TASK escapecombine_t1seq
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_SMART_FLEE_CHAR -1 scplayer 30.0 30000
					CLOSE_SEQUENCE_TASK escapecombine_t1seq
					
					IF NOT IS_CHAR_DEAD	chaser4_t1
						PERFORM_SEQUENCE_TASK chaser4_t1 escapecombine_t1seq
					ENDIF

					CLEAR_SEQUENCE_TASK escapecombine_t1seq

					chaser3_t1flag = 1
				ENDIF
			ENDIF

			IF chaser4_t1flag = 0
				IF LOCATE_CAR_2D combine_t1 -1145.6510 -1111.6699 20.0 20.0 FALSE
					
					IF NOT IS_CHAR_DEAD	chaser6_t1
						enemy_t1 = chaser6_t1
						enemyx_t1 = -1113.777
						enemyy_t1 = 1128.22
						enemyz_t1 = 128.22
						GOSUB runfromcombine_t1lable
					ENDIF

					IF NOT IS_CHAR_DEAD	chaser7_t1
						enemy_t1 = chaser7_t1
						enemyx_t1 = -1055.94
						enemyy_t1 = -1120.62
						enemyz_t1 = 128.56
						GOSUB runfromcombine_t1lable
					ENDIF
					
					chaser4_t1flag = 1
				ENDIF
			ENDIF

			IF chaser5_t1flag = 0
				IF IS_CAR_IN_AREA_2D combine_t1 -1224.06 -1135.61 -999.06 -1063.3 FALSE

					OPEN_SEQUENCE_TASK escapecombine_t1seq
					TASK_STAY_IN_SAME_PLACE -1 FALSE
					TASK_SMART_FLEE_CHAR -1 scplayer 30.0 30000
					CLOSE_SEQUENCE_TASK escapecombine_t1seq
					
					IF NOT IS_CHAR_DEAD	chaser10_t1
						PERFORM_SEQUENCE_TASK chaser10_t1 escapecombine_t1seq
					ENDIF

					IF NOT IS_CHAR_DEAD	chaser11_t1
						PERFORM_SEQUENCE_TASK chaser11_t1 escapecombine_t1seq
					ENDIF

					IF NOT IS_CHAR_DEAD	chaser12_t1
						PERFORM_SEQUENCE_TASK chaser12_t1 escapecombine_t1seq
					ENDIF

					IF NOT IS_CHAR_DEAD	chaser13_t1
						PERFORM_SEQUENCE_TASK chaser13_t1 escapecombine_t1seq
					ENDIF

					CLEAR_SEQUENCE_TASK escapecombine_t1seq

					createguyswayback2_t1flag = 1

					chaser5_t1flag = 1
				ENDIF
			ENDIF


		ENDIF
	ENDIF

	//second group of guys
	IF createguyswayback2_t1flag = 1
		
		IF NOT IS_CHAR_DEAD worker6_t1
			IF NOT IS_CHAR_ON_SCREEN worker6_t1
				DELETE_CHAR worker6_t1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD worker7_t1
			IF NOT IS_CHAR_ON_SCREEN worker7_t1
				DELETE_CHAR worker7_t1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD worker1_t1
			IF NOT IS_CHAR_ON_SCREEN worker1_t1
				DELETE_CHAR chaser1_t1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD worker2_t1
			IF NOT IS_CHAR_ON_SCREEN worker2_t1
				DELETE_CHAR chaser2_t1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD worker3_t1
			IF NOT IS_CHAR_ON_SCREEN worker3_t1
				DELETE_CHAR chaser3_t1
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD worker4_t1
			IF NOT IS_CHAR_ON_SCREEN worker4_t1
				DELETE_CHAR chaser4_t1
			ENDIF
		ENDIF

		OPEN_SEQUENCE_TASK attackcombine_t1seq
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 scplayer
		CLOSE_SEQUENCE_TASK attackcombine_t1seq 

		CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1050.9415 -1171.5502 127.4132 chaser14_t1
		SET_CHAR_HEADING chaser14_t1 57.544
		GIVE_WEAPON_TO_CHAR chaser14_t1 WEAPONTYPE_COUNTRYRIFLE 9999
		SET_CHAR_DECISION_MAKER chaser14_t1 truth1_DM
		SET_CHAR_HEALTH chaser14_t1 50
		PERFORM_SEQUENCE_TASK chaser14_t1 attackcombine_t1seq

		CREATE_CHAR PEDTYPE_MISSION1 CWMOHB2 -1048.3793 -1172.3512 127.4308 chaser15_t1
		SET_CHAR_HEADING chaser15_t1 57.544
		GIVE_WEAPON_TO_CHAR chaser15_t1 WEAPONTYPE_COUNTRYRIFLE 9999
		SET_CHAR_DECISION_MAKER chaser15_t1 truth1_DM
		SET_CHAR_HEALTH chaser15_t1 50
		PERFORM_SEQUENCE_TASK chaser15_t1 attackcombine_t1seq

		CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1047.9266 -1177.5592 127.5070 chaser16_t1
		SET_CHAR_HEADING chaser16_t1 57.544
		GIVE_WEAPON_TO_CHAR chaser16_t1 WEAPONTYPE_COUNTRYRIFLE 9999
		SET_CHAR_DECISION_MAKER chaser16_t1 truth1_DM
		SET_CHAR_HEALTH chaser16_t1 50
		PERFORM_SEQUENCE_TASK chaser16_t1 attackcombine_t1seq

		CREATE_CHAR PEDTYPE_MISSION1 CWFYHB -1051.1592 -1180.1293 127.5367 chaser17_t1
		SET_CHAR_HEADING chaser17_t1 57.544
		GIVE_WEAPON_TO_CHAR chaser17_t1 WEAPONTYPE_COUNTRYRIFLE 9999
		SET_CHAR_DECISION_MAKER chaser17_t1 truth1_DM
		SET_CHAR_HEALTH chaser17_t1 50
		PERFORM_SEQUENCE_TASK chaser17_t1 attackcombine_t1seq
		
		CUSTOM_PLATE_FOR_NEXT_CAR WALTON &__HOM__
		CREATE_CAR WALTON -1049.0 -1296.0 128.3 walton1_t1
		SET_CAR_HEADING walton1_t1 277.3820 
		CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1049.1896 -1291.9401 127.7206 chaser18_t1
		SET_CHAR_HEADING chaser18_t1 57.544
		GIVE_WEAPON_TO_CHAR chaser18_t1 WEAPONTYPE_COUNTRYRIFLE 9999
		SET_CHAR_DECISION_MAKER chaser18_t1 truth1_DM
		SET_CHAR_HEALTH chaser18_t1 50
		PERFORM_SEQUENCE_TASK chaser18_t1 attackcombine_t1seq
		
		CUSTOM_PLATE_FOR_NEXT_CAR WALTON &__HOM__
		CREATE_CAR WALTON -1154.086 -1339.716 126.61 walton2_t1 //-1166.521 -1333.421 125.99
		SET_CAR_HEADING walton2_t1 272.428
		CREATE_CHAR_INSIDE_CAR walton2_t1 PEDTYPE_MISSION1 CWFYHB chaser20_t1
		CREATE_CHAR PEDTYPE_MISSION1 CWMYHB1 -1049.1896 -1295.9401 0.0 chaser19_t1
		SET_CHAR_DECISION_MAKER chaser19_t1 truth1_DM
		ATTACH_CHAR_TO_CAR chaser19_t1 walton2_t1 0.0 -1.0 0.9 FACING_FORWARD 360.0 WEAPONTYPE_COUNTRYRIFLE
		TASK_STAY_IN_SAME_PLACE chaser19_t1 TRUE

		CLEAR_SEQUENCE_TASK attackcombine_t1seq

		createguyswayback2_t1flag = 2
	ENDIF

	IF createguyswayback2_t1flag = 2
		IF NOT IS_CAR_DEAD combine_t1
			IF LOCATE_CAR_2D combine_t1 -1050.9415 -1171.5502 15.0 15.0 FALSE

				OPEN_SEQUENCE_TASK escapecombine_t1seq
				TASK_STAY_IN_SAME_PLACE -1 FALSE
				TASK_SMART_FLEE_CHAR -1 scplayer 30.0 30000
				CLOSE_SEQUENCE_TASK escapecombine_t1seq
			
				IF NOT IS_CHAR_DEAD	chaser14_t1
					PERFORM_SEQUENCE_TASK chaser14_t1 escapecombine_t1seq
				ENDIF

				IF NOT IS_CHAR_DEAD	chaser15_t1
					PERFORM_SEQUENCE_TASK chaser15_t1 escapecombine_t1seq
				ENDIF

				IF NOT IS_CHAR_DEAD	chaser16_t1
					PERFORM_SEQUENCE_TASK chaser16_t1 escapecombine_t1seq
				ENDIF

				IF NOT IS_CHAR_DEAD	chaser17_t1
					PERFORM_SEQUENCE_TASK chaser17_t1 escapecombine_t1seq
				ENDIF

				CLEAR_SEQUENCE_TASK escapecombine_t1seq

				createguyswayback2_t1flag = 3
			ENDIF
		ENDIF
	ENDIF

	IF createguyswayback2_t1flag > 1
		IF walton2_t1flag = 0
			IF IS_CHAR_IN_AREA_2D scplayer -974.32 -1287.37 -1129.14 -1330.41 FALSE
				IF NOT IS_CAR_DEAD walton2_t1
					IF NOT IS_CHAR_DEAD	chaser20_t1
						SET_CAR_FORWARD_SPEED walton2_t1 30.0
						IF NOT IS_CHAR_DEAD chaser19_t1
							TASK_KILL_CHAR_ON_FOOT chaser19_t1 scplayer
						ENDIF
						TASK_CAR_MISSION chaser20_t1 walton2_t1 walton2_t1 MISSION_BLOCKPLAYER_FARAWAY 30.0 DRIVINGMODE_PLOUGHTHROUGH
					ENDIF
				ENDIF
				audio_t1flag = 100
				MARK_MODEL_AS_NO_LONGER_NEEDED KMB_MARIJUANA
				MARK_MODEL_AS_NO_LONGER_NEEDED sw_haybreak02
				MARK_OBJECT_AS_NO_LONGER_NEEDED haystack1_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED haystack2_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED haystack3_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED haystack4_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED haystack5_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED haystack6_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED haystack7_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED bail1_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED bail2_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED bail3_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED bail4_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED bail5_t1
				MARK_OBJECT_AS_NO_LONGER_NEEDED bail6_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser1_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser2_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser3_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser4_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser5_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser6_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser7_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser8_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser9_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser10_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser11_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser12_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED chaser13_t1
				MARK_CAR_AS_NO_LONGER_NEEDED tractorforplayer_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED fieldguard1_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED fieldguard2_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED fieldguard3_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED fieldguard4_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED fieldguard5_t1
				MARK_CHAR_AS_NO_LONGER_NEEDED fieldguard6_t1
				walton2_t1flag = 1
			ENDIF
		ENDIF
	ENDIF


	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////	Player has the harvester	////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//blip swapping if player is in or out of combine harvester
	IF NOT IS_CAR_DEAD combine_t1
		IF blipswap_t1flag = 0
			IF IS_CHAR_SITTING_IN_CAR scplayer combine_t1
				MARK_CAR_AS_NO_LONGER_NEEDED tractorforplayer_t1
				playerincombine_t1flag = 1
				PRINT_NOW TRU1_5 5000 1 //Take the combine harvester back to The Truths farm.
				REMOVE_BLIP combineblip_t1
				ADD_BLIP_FOR_COORD -1114.25 -1619.96 76.68 combineblip_t1
				blipswap_t1flag = 1
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD combine_t1
		IF blipswap_t1flag = 1
			IF NOT IS_CHAR_IN_CAR scplayer combine_t1
				IF NOT playerincombine_t1flag > 1
					PRINT_NOW TRU1_7 5000 1	//Get back in the combine harvester and get it back to The Truth!
					REMOVE_BLIP combineblip_t1
					ADD_BLIP_FOR_CAR combine_t1 combineblip_t1	
					SET_BLIP_AS_FRIENDLY combineblip_t1 TRUE
					blipswap_t1flag = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	//player passes if they get it to the location
	IF playerincombine_t1flag = 1
		IF blipswap_t1flag = 1
			IF NOT IS_CAR_DEAD combine_t1
				IF LOCATE_CAR_3D combine_t1 -1100.0071 -1620.9491 75.3750 5.0 5.0 5.0 TRUE
					IF IS_CHAR_SITTING_IN_CAR scplayer combine_t1

						IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						ENDIF

						SET_PLAYER_CONTROL PLAYER1 OFF

						DO_FADE 1000 FADE_OUT

						WHILE GET_FADING_STATUS
				            WAIT 0
						ENDWHILE
						SWITCH_WIDESCREEN ON
						playerincombine_t1flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF playerincombine_t1flag = 2
		IF NOT IS_CAR_DEAD combine_t1

			MARK_CAR_AS_NO_LONGER_NEEDED tractorchase1_t1
			DELETE_CAR tractorchase1_t1
			MARK_CHAR_AS_NO_LONGER_NEEDED tractorchasedriver1_t1
			MARK_CAR_AS_NO_LONGER_NEEDED tractorchase2_t1
			DELETE_CAR tractorchase2_t1
			MARK_CHAR_AS_NO_LONGER_NEEDED tractorchasedriver2_t1
			MARK_CAR_AS_NO_LONGER_NEEDED walton2_t1
			MARK_CHAR_AS_NO_LONGER_NEEDED chaser19_t1
			MARK_CHAR_AS_NO_LONGER_NEEDED chaser20_t1
			MARK_MODEL_AS_NO_LONGER_NEEDED TRACTOR
			MARK_MODEL_AS_NO_LONGER_NEEDED KMB_MARIJUANA
			MARK_MODEL_AS_NO_LONGER_NEEDED sw_haybreak02
			MARK_MODEL_AS_NO_LONGER_NEEDED CUNTGUN
			MARK_MODEL_AS_NO_LONGER_NEEDED WALTON
			MARK_MODEL_AS_NO_LONGER_NEEDED SHOVEL

			LOAD_SPECIAL_CHARACTER 1 TRUTH
			REQUEST_MODEL BFINJECT

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			LOAD_MISSION_AUDIO 1 SOUND_TRU1_ZA
			LOAD_MISSION_AUDIO 2 SOUND_TRU1_ZB

			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
				OR NOT HAS_MISSION_AUDIO_LOADED 1
				OR NOT HAS_MISSION_AUDIO_LOADED 2
				OR NOT HAS_MODEL_LOADED BFINJECT
				WAIT 0
			ENDWHILE

			TIMERA = 0
			TIMERB = 0

			CLEAR_AREA -1100.0071 -1620.9491 200.0 200.0 TRUE

			IF NOT IS_CAR_DEAD combine_t1
				SET_CAR_COORDINATES combine_t1 -1102.21 -1620.017 77.345 //-1100.0071 -1620.9491 75.3750
				SET_CAR_HEADING combine_t1 90.738 //88.2983
			ENDIF
			
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				OR NOT HAS_MISSION_AUDIO_LOADED 2
				WAIT 0
			ENDWHILE

			CREATE_CAR BFINJECT -1074.53 -1643.624 75.066 tractorchase1_t1
			SET_CAR_HEADING tractorchase1_t1 84.918
	
			CREATE_CHAR PEDTYPE_MISSION2 SPECIAL01 -1088.84 -1623.997 75.375 truth_t1
			SET_CHAR_HEADING truth_t1 91.269
			SHUT_CHAR_UP truth_t1 TRUE
			SET_CHAR_PROOFS truth_t1 TRUE TRUE TRUE TRUE TRUE
			SET_CHAR_DECISION_MAKER truth_t1 truth1_DM

			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer -1098.84 -1623.997 75.375
				SET_CHAR_HEADING scplayer 264.123
			ELSE
				SET_CHAR_COORDINATES scplayer -1098.84 -1623.997 75.375
				SET_CHAR_HEADING scplayer 264.123
			ENDIF

			SET_FIXED_CAMERA_POSITION -1087.7332 -1625.1243 76.7194 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -1088.6616 -1624.7576 76.6608 JUMP_CUT
			REMOVE_BLIP combineblip_t1
			DO_FADE 1000 FADE_IN
			IF NOT IS_CHAR_DEAD truth_t1
				TASK_GO_STRAIGHT_TO_COORD truth_t1 -1093.1890 -1624.0206 75.3750 PEDMOVE_WALK 5000
			ENDIF
			TASK_GO_STRAIGHT_TO_COORD scplayer -1094.931 -1623.946 75.375 PEDMOVE_WALK 5000
			TIMERA = 0
			playerincombine_t1flag = 3
		ENDIF
	ENDIF
 

	IF playerincombine_t1flag = 3
		IF NOT IS_CHAR_DEAD truth_t1
			IF TIMERA > 2500
			OR LOCATE_STOPPED_CHAR_ON_FOOT_2D truth_t1 -1093.1890 -1624.0206 1.5 1.5 FALSE
				SET_FIXED_CAMERA_POSITION -1092.2964 -1624.9174 76.9985 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1093.1168 -1624.3716 76.8283 JUMP_CUT

				CLEAR_CHAR_TASKS_IMMEDIATELY truth_t1
				SET_CHAR_COORDINATES truth_t1 -1093.1890 -1624.0206 75.3750
				SET_CHAR_HEADING truth_t1 88.0345

				SET_CHAR_COORDINATES scplayer -1094.931 -1623.946 75.375
				SET_CHAR_HEADING scplayer 264.123

				TASK_PLAY_ANIM truth_t1 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
				playerincombine_t1flag = 4
			ENDIF
		ENDIF
	ENDIF

	IF playerincombine_t1flag = 4
		PRINT_NOW ( TRU1_ZA ) 4000 1
		PLAY_MISSION_AUDIO 1
		playerincombine_t1flag = 5
	ENDIF

	IF playerincombine_t1flag = 5
		IF HAS_MISSION_AUDIO_FINISHED 1
			CLEAR_PRINTS
			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_TRU1_ZC
			PRINT_NOW ( TRU1_ZB ) 4000 1
			PLAY_MISSION_AUDIO 2
			playerincombine_t1flag = 6
		ENDIF
	ENDIF

	IF playerincombine_t1flag = 6
		IF HAS_MISSION_AUDIO_FINISHED 2
			IF HAS_MISSION_AUDIO_LOADED 1
				PRINT_NOW ( TRU1_ZC ) 4000 1
				PLAY_MISSION_AUDIO 1
				playerincombine_t1flag = 7
			ENDIF
		ENDIF
	ENDIF

	IF playerincombine_t1flag = 7
		IF HAS_MISSION_AUDIO_FINISHED 1
			CLEAR_PRINTS
			TASK_GO_STRAIGHT_TO_COORD scplayer -1092.25 -1629.82 76.0 PEDMOVE_WALK 5000
			WAIT 250
			IF NOT IS_CHAR_DEAD truth_t1
				TASK_GO_STRAIGHT_TO_COORD truth_t1 -1099.96 -1623.7 76.4 PEDMOVE_WALK 5000
			ENDIF
			TIMERA = 0
			playerincombine_t1flag = 8
		ENDIF
	ENDIF		

	IF playerincombine_t1flag = 8
		IF TIMERA > 1000

			DO_FADE 1000 FADE_OUT

			WHILE GET_FADING_STATUS
	            WAIT 0
			ENDWHILE

			REQUEST_MODEL CELLPHONE
			LOAD_MISSION_AUDIO 3 SOUND_MOBRING
			LOAD_ALL_MODELS_NOW

			WHILE NOT HAS_MODEL_LOADED CELLPHONE
				OR NOT HAS_MISSION_AUDIO_LOADED 3
				WAIT 0
			ENDWHILE

			DELETE_CAR combine_t1
			DELETE_CHAR truth_t1
			SWITCH_WIDESCREEN OFF
			SET_CHAR_COORDINATES scplayer -1092.561 -1633.628 75.37
			SET_CHAR_HEADING scplayer 273.438

			DO_FADE 1000 FADE_IN

			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			PLAY_MISSION_AUDIO 3
			SET_PLAYER_CONTROL PLAYER1 ON
			TIMERA = 0
			//GOTO mission_truth1_passed
			playerincombine_t1flag = 9
		ENDIF
	ENDIF

	IF playerincombine_t1flag = 9
		IF TIMERA > 1000
			TASK_USE_MOBILE_PHONE scplayer TRUE
			CLEAR_MISSION_AUDIO 3
			TIMERA = 0
			playerincombine_t1flag = 10
		ENDIF
	ENDIF

	IF playerincombine_t1flag = 10
		IF TIMERA > 2000
			progressaudio_t1flag = 1
			handlingudio_t1flag = 0
			playerincombine_t1flag = 11
		ENDIF
	ENDIF

	IF playerincombine_t1flag = 11

		GOSUB process_audio_t1

		IF progressaudio_t1flag = 1
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05A //  Eh.
				$input_text_t1 = MCES05A //  Eh.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 2
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05B //  Carl, is Cesar.
				$input_text_t1 = MCES05B //  Carl, is Cesar.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 3
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05C //  Whassup?
				$input_text_t1 = MCES05C //  Whassup?
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 4
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05D //  Bad stinking shit, that’s wassup, holmes.
				$input_text_t1 = MCES05D //  Bad stinking shit, that’s wassup, holmes.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 5
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05E //  What’s wrong – where’s Kendl, is she ok?
				$input_text_t1 = MCES05E //  What’s wrong – where’s Kendl, is she ok?
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 6
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05F //  She’s with me – she ok for now.
				$input_text_t1 = MCES05F //  She’s with me – she ok for now.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 7
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05G //  The Varrios Los Aztecas; it’s over, all gone.
				$input_text_t1 = MCES05G //  The Varrios Los Aztecas; it’s over, all gone.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 8
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05H //  There’s a price on my head – maybe Kendl’s too
				$input_text_t1 = MCES05H //  There’s a price on my head – maybe Kendl’s too
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 9
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05J //  What happened?
				$input_text_t1 = MCES05J //  What happened?
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 10
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05K //  Trust and respect, honor; they don’t mean jack in Los Santos now.
				$input_text_t1 = MCES05K //  Trust and respect, honor; they don’t mean jack in Los Santos now.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 11
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05L //  My OG’s, my esse’s – all dead or in hiding, eh.
				$input_text_t1 = MCES05L //  My OG’s, my esse’s – all dead or in hiding, eh.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 12
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05M //  Get out of town.
				$input_text_t1 = MCES05M //  Get out of town.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 13
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05N //  Head over to Angel Pine, rent you and Kendl a trailer I’ll meet you there.
				$input_text_t1 = MCES05N //  Head over to Angel Pine, rent you and Kendl a trailer I’ll meet you there.
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 14
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05O //  Ok, I just got some shit to take of and –
				$input_text_t1 = MCES05O //  Ok, I just got some shit to take of and –
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 15
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05P //  No! Just get my sister out of town and someplace safe!
				$input_text_t1 = MCES05P //  No! Just get my sister out of town and someplace safe!
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 16
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05Q //  Please don’t shit with me on this one, I can’t lose her, man!
				$input_text_t1 = MCES05Q //  Please don’t shit with me on this one, I can’t lose her, man!
				GOSUB load_audio_t1
			ENDIF
		ENDIF
		IF progressaudio_t1flag = 17
			IF handlingudio_t1flag = 0
				audio_label_t1 = SOUND_MCES05R //  Sure thing, holmes, We’ll see you in Angel Pine.
				$input_text_t1 = MCES05R //  Sure thing, holmes, We’ll see you in Angel Pine.
				GOSUB load_audio_t1
			ENDIF
		ENDIF

		IF progressaudio_t1flag = 18
			TASK_USE_MOBILE_PHONE scplayer FALSE	
			TIMERA = 0
			progressaudio_t1flag = 19
		ENDIF

		IF progressaudio_t1flag = 19
			IF TIMERA > 2000
				GOTO mission_truth1_passed
				progressaudio_t1flag = 20
			ENDIF
		ENDIF

		IF skipphonecall_t1flag = 0
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				CLEAR_MISSION_AUDIO 1
				handlingudio_t1flag = 0
				progressaudio_t1flag = 18
				skipphonecall_t1flag = 1
			ENDIF
		ENDIF

	ENDIF
			


	IF truth1_flag > 4
		IF IS_CAR_DEAD combine_t1
			IF playerincombine_t1flag < 8
				PRINT_NOW TRU1_16 5000 1 //The combine harvester is wrecked!
				GOTO mission_truth1_failed
			ENDIF
		ENDIF
	ENDIF


	//////////////////////////////////////////////////////////////////////////////	audio 
	//1st guy at fence
	IF audio_t1flag < 100

		IF audio_t1flag = 1
			IF NOT IS_CHAR_DEAD worker1_t1 //CWMOHB2
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_TRU1_DF 
				audio_t1flag = 2
			ENDIF
		ENDIF
		IF audio_t1flag = 2
			IF NOT IS_CHAR_DEAD worker1_t1 //CWMOHB2
				IF HAS_MISSION_AUDIO_LOADED 1
					SHUT_CHAR_UP worker1_t1 TRUE
					PRINT_NOW ( TRU1_DF ) 4000 1 //You come to the wrong farm, boy.
					PLAY_MISSION_AUDIO 1
					audio_t1flag = 3
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 3
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				audio_t1flag = 4
			ENDIF
		ENDIF
		IF audio_t1flag = 4
			IF NOT IS_CHAR_DEAD worker1_t1 //CWMOHB2
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_TRU1_DG 
				audio_t1flag = 5
			ENDIF
		ENDIF
		IF audio_t1flag = 5
			IF NOT IS_CHAR_DEAD worker1_t1 //CWMOHB2
				IF HAS_MISSION_AUDIO_LOADED 1
					PRINT_NOW ( TRU1_DG ) 4000 1 //This ain’t no country club, fella!
					ATTACH_MISSION_AUDIO_TO_CHAR 1 worker1_t1
					PLAY_MISSION_AUDIO 1
					audio_t1flag = 6
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 6
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD worker1_t1
					SHUT_CHAR_UP worker1_t1 FALSE
				ENDIF
				audio_t1flag = 7
			ENDIF
		ENDIF

		//tractor
		IF audio_t1flag = 8			 
			IF NOT IS_CHAR_DEAD tractorchasedriver2_t1 //CWFYHB
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_TRU1_FD 
				audio_t1flag = 9
			ENDIF
		ENDIF
		IF audio_t1flag = 9
			IF NOT IS_CHAR_DEAD tractorchasedriver2_t1 //CWFYHB
				IF HAS_MISSION_AUDIO_LOADED 1
					PRINT_NOW ( TRU1_FD ) 4000 1 //Beat him down, it’s all he’s good for!
					PLAY_MISSION_AUDIO 1
					SHUT_CHAR_UP tractorchasedriver2_t1 TRUE
					audio_t1flag = 10
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 10
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD tractorchasedriver2_t1
					SHUT_CHAR_UP tractorchasedriver2_t1 FALSE
				ENDIF
				audio_t1flag = 11
			ENDIF
		ENDIF

		//near glass bits
		//tractor
		IF audio_t1flag = 12
			IF NOT IS_CHAR_DEAD worker4_t1 //CWFYHB
				IF HAS_MISSION_AUDIO_FINISHED 1
					CLEAR_MISSION_AUDIO 1
					LOAD_MISSION_AUDIO 1 SOUND_TRU1_FA //Hey, who’s this guy?
					audio_t1flag = 13
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 13
			IF NOT IS_CHAR_DEAD worker4_t1 //CWFYHB
				IF HAS_MISSION_AUDIO_LOADED 1
					PRINT_NOW ( TRU1_FA ) 4000 1 //Hey, who’s this guy?
					PLAY_MISSION_AUDIO 1
					audio_t1flag = 14
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 14
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				audio_t1flag = 15
			ENDIF
		ENDIF
		IF audio_t1flag = 15
			IF NOT IS_CHAR_DEAD worker5_t1 //CWMYHB1
				SHUT_CHAR_UP worker5_t1 TRUE
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_TRU1_AB	//Dunno but he looks kinda shifty!
				audio_t1flag = 16
			ENDIF
		ENDIF
		IF audio_t1flag = 16
			IF NOT IS_CHAR_DEAD worker5_t1 //CWMYHB1
				IF HAS_MISSION_AUDIO_LOADED 1
					PRINT_NOW ( TRU1_AB ) 4000 1 //Dunno but he looks kinda shifty!
					PLAY_MISSION_AUDIO 1
					audio_t1flag = 17
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 17
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				audio_t1flag = 18
			ENDIF
		ENDIF
		IF audio_t1flag = 18
			IF NOT IS_CHAR_DEAD worker5_t1 //CWMYHB1
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_TRU1_AD //Roll him in the pig shit!
				audio_t1flag = 19
			ENDIF
		ENDIF
		IF audio_t1flag = 19
			IF NOT IS_CHAR_DEAD worker5_t1 //CWMYHB1
				IF HAS_MISSION_AUDIO_LOADED 1
					PRINT_NOW TRU1_AD 4000 1 //Roll him in the pig shit!
					PLAY_MISSION_AUDIO 1
					audio_t1flag = 20
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 21
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD worker5_t1
					SHUT_CHAR_UP worker5_t1 FALSE
				ENDIF
				audio_t1flag = 22
			ENDIF
		ENDIF

		IF audio_t1flag = 23
			IF NOT IS_CHAR_DEAD combinedriver_t1 //CWMYHB1
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_TRU1_DE	//He’s a combine rustler!!
				audio_t1flag = 24
			ENDIF
		ENDIF
		IF audio_t1flag = 24
			IF NOT IS_CHAR_DEAD combinedriver_t1 //CWMYHB1
				IF HAS_MISSION_AUDIO_LOADED 1
					SHUT_CHAR_UP combinedriver_t1 TRUE
					PRINT_NOW TRU1_DE 4000 1 //He’s a combine rustler!!
					PLAY_MISSION_AUDIO 1
					audio_t1flag = 25
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 26
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD combinedriver_t1
					SHUT_CHAR_UP combinedriver_t1 FALSE
				ENDIF
				audio_t1flag = 27
			ENDIF
		ENDIF

		IF audio_t1flag = 28
			IF NOT IS_CHAR_DEAD chaser2_t1 //CWMYHB1
				CLEAR_MISSION_AUDIO 1
				LOAD_MISSION_AUDIO 1 SOUND_TRU1_AC //Stop the dang varmint!!
				audio_t1flag = 29
			ENDIF
		ENDIF
		IF audio_t1flag = 29
			IF NOT IS_CHAR_DEAD chaser2_t1 //CWMYHB1
				IF HAS_MISSION_AUDIO_LOADED 1
					SHUT_CHAR_UP chaser2_t1 TRUE
					PRINT_NOW TRU1_AC 4000 1 //Stop the dang varmint!!
					PLAY_MISSION_AUDIO 1
					audio_t1flag = 30
				ENDIF
			ENDIF
		ENDIF
		IF audio_t1flag = 30
			IF HAS_MISSION_AUDIO_FINISHED 1
				CLEAR_PRINTS
				IF NOT IS_CHAR_DEAD chaser2_t1
					SHUT_CHAR_UP chaser2_t1 FALSE
				ENDIF
				audio_t1flag = 31
			ENDIF
		ENDIF

	ENDIF



ENDIF //triggeredaction_tflag = 1

GOTO truth1_main_mission_loop


//////////////////////////////	GOSUBS



load_audio_t1:
IF handlingudio_t1flag = 0
	LOAD_MISSION_AUDIO 1 audio_label_t1
	$text_label_t1 = $input_text_t1
	handlingudio_t1flag = 1
ENDIF
RETURN

process_audio_t1:
IF handlingudio_t1flag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		PRINT_NOW $text_label_t1 4000 1 //Dummy message"
		PLAY_MISSION_AUDIO 1
		handlingudio_t1flag = 2
	ENDIF
ENDIF
IF handlingudio_t1flag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_t1flag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		handlingudio_t1flag = 0
	ENDIF
ENDIF
RETURN



runfromcombine_t1lable:
OPEN_SEQUENCE_TASK runfromcombine_t1seq
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_t1 enemyy_t1 enemyz_t1 PEDMOVE_RUN -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK runfromcombine_t1seq 
PERFORM_SEQUENCE_TASK enemy_t1 runfromcombine_t1seq
CLEAR_SEQUENCE_TASK runfromcombine_t1seq
RETURN

combine_t1label:
OPEN_SEQUENCE_TASK combine_t1seq
TASK_CAR_DRIVE_TO_COORD -1 combine_t1 -1025.67 -1029.39 130.5 6.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
TASK_CAR_DRIVE_TO_COORD -1 combine_t1 -1154.06 -1041.32 130.5 6.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
TASK_CAR_DRIVE_TO_COORD -1 combine_t1 -1166.82 -941.32 130.21 6.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
TASK_CAR_DRIVE_TO_COORD -1 combine_t1 -1031.46 -935.51 130.19 6.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
CLOSE_SEQUENCE_TASK combine_t1seq
PERFORM_SEQUENCE_TASK combinedriver_t1 combine_t1seq
CLEAR_SEQUENCE_TASK combine_t1seq
RETURN

chase_t1label:
OPEN_SEQUENCE_TASK chase_t1seq
TASK_CAR_MISSION -1 enemycar_t1 enemycar_t1 MISSION_RAMPLAYER_FARAWAY 20.0 DRIVINGMODE_PLOUGHTHROUGH
CLOSE_SEQUENCE_TASK chase_t1seq
PERFORM_SEQUENCE_TASK enemy_t1 chase_t1seq
CLEAR_SEQUENCE_TASK chase_t1seq
RETURN


 // **************************************** Mission truth1 failed ************************

mission_truth1_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   

// **************************************** mission truth1 passed ************************

mission_truth1_passed:

REMOVE_BLIP bcrash_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcrashX bcrashY bcrashZ cesar_blip_icon bcrash_contact_blip //TRAILOR

SWITCH_CAR_GENERATOR iFieldHarvester[0] 101
SWITCH_CAR_GENERATOR iFieldHarvester[1] 101
SWITCH_CAR_GENERATOR iFieldHarvester[2] 101
SWITCH_CAR_GENERATOR iFieldHarvester[3] 101
SWITCH_CAR_GENERATOR iFieldHarvester[4] 101
SWITCH_CAR_GENERATOR iFieldHarvester[5] 101
SWITCH_CAR_GENERATOR iFieldHarvester[6] 101
SWITCH_CAR_GENERATOR iFieldHarvester[7] 101

REGISTER_MISSION_PASSED ( TRUTH_1 )
PLAYER_MADE_PROGRESS 1
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 1
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect
CLEAR_WANTED_LEVEL player1
flag_truth_mission_counter ++
REMOVE_BLIP truth_contact_blip
RETURN
		


// ********************************** mission cleanup **************************************

mission_cleanup_truth1:

flag_player_on_mission = 0

MARK_MODEL_AS_NO_LONGER_NEEDED COMBINE
MARK_MODEL_AS_NO_LONGER_NEEDED TRACTOR
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_MARIJUANA
MARK_MODEL_AS_NO_LONGER_NEEDED sw_haybreak02
MARK_MODEL_AS_NO_LONGER_NEEDED CUNTGUN
MARK_MODEL_AS_NO_LONGER_NEEDED WALTON
MARK_MODEL_AS_NO_LONGER_NEEDED CWFYHB
MARK_MODEL_AS_NO_LONGER_NEEDED CWMOHB2
MARK_MODEL_AS_NO_LONGER_NEEDED CWMYHB1
MARK_MODEL_AS_NO_LONGER_NEEDED BFINJECT 
MARK_MODEL_AS_NO_LONGER_NEEDED SHOVEL
UNLOAD_SPECIAL_CHARACTER 1
REMOVE_BLIP combineblip_t1
MARK_CAR_AS_NO_LONGER_NEEDED tractorchase1_t1

SET_WANTED_MULTIPLIER 1.0

GET_GAME_TIMER timer_mobile_start
MISSION_HAS_FINISHED
RETURN

 
}