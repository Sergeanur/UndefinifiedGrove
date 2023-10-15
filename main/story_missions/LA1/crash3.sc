MISSION_START

// *****************************************************************************************
// *********************************** Territory War 6 ************************************* 
// ***********************************  Docks shootout  ************************************
// *****************************************************************************************
// * Deal going down at the docks player must go there take everyone out and get the money *
// * loads of guys to take out.  Two dealing parties start to kick off with each other and *
// * the player.  Player must take everyone out the go to get the case.  However case is   *
// * empty then other gang members drive past and go down storm drains.  Player must chase *
// * them down and get the stuff back off them. 										   *
// *****************************************************************************************


// Mission start stuff
{
SCRIPT_NAME drugs3

GOSUB mission_start_drugs3

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_drugs3_failed
ENDIF

GOSUB mission_cleanup_drugs3

MISSION_END
 
// **************************************** Variables for mission ****************************

//outside warehouse
LVAR_INT leftdoor1_c3
LVAR_INT rightdoor1_c3
LVAR_INT leftdoor2_c3
LVAR_INT rightdoor2_c3
LVAR_INT leftdoor3_c3
LVAR_INT rightdoor3_c3
LVAR_INT leftdoor4_c3
LVAR_INT rightdoor4_c3
LVAR_INT ramp1_c3
LVAR_INT ramp2_c3
LVAR_INT ramp3_c3
LVAR_INT ramp4_c3
LVAR_INT ramp5_c3
LVAR_INT ramp6_c3
LVAR_INT ramp7_c3
LVAR_INT ramp8_c3
LVAR_INT dockshutter_c3
LVAR_INT keypad_c3
LVAR_INT crash3_DM	//decision maker
LVAR_INT crash3weak_DM	//decision maker

LVAR_INT chasecar1_c3
LVAR_INT chasecar2_c3
LVAR_INT chasecar3_c3
LVAR_INT chasecar4_c3
LVAR_INT parkedtruck1_c3
LVAR_INT parkedtruck2_c3
LVAR_INT forklift1_c3
LVAR_INT forklift2_c3
LVAR_INT forkworker1_c3
LVAR_INT forkworker2_c3
LVAR_INT workerdoor_c3
LVAR_INT workerchat_c3
LVAR_INT workerdirector_c3
LVAR_INT talkworker1_c3
LVAR_INT talkworker2_c3
LVAR_INT barrel1_c3
LVAR_INT barrel2_c3
LVAR_INT barrel3_c3
LVAR_INT barrel4_c3
LVAR_INT barrel5_c3
LVAR_INT barrel6_c3
LVAR_INT cutscenec3_seq	//sequences
LVAR_INT shuttersound_c3 //sound
LVAR_INT scriptstatus_c3


//text
LVAR_TEXT_LABEL $text_label_c3
LVAR_INT audio_label_c3
LVAR_TEXT_LABEL $input_text_c3


//inside warehouse
LVAR_INT guard1_c3
LVAR_INT guard2_c3
LVAR_INT guard3_c3
LVAR_INT guard4_c3
LVAR_INT guard5_c3
LVAR_INT guard6_c3
LVAR_INT guard7_c3
LVAR_INT guard8_c3
LVAR_INT guard9_c3
LVAR_INT guard10_c3
LVAR_INT guard11_c3
LVAR_INT guard12_c3
LVAR_INT guard13_c3
LVAR_INT guard14_c3
LVAR_INT guard15_c3
LVAR_INT guard16_c3
LVAR_INT flat1_c3
LVAR_INT flat2_c3
LVAR_INT tolotov_c3
LVAR_INT box_c3
LVAR_INT box_c3health
LVAR_INT russian1_c3
LVAR_INT russian2_c3
LVAR_INT russian3_c3
LVAR_INT russian4_c3
LVAR_INT playerbike_c3
LVAR_INT russianboss1_c3
LVAR_INT russianboss2_c3
LVAR_INT backdoor_c3
//sequence
LVAR_INT enemy_c3 
LVAR_INT tolotov_c3seq
LVAR_INT tolotovflee_c3seq
LVAR_INT tolotovdrive_c3seq
LVAR_FLOAT enemyx_c3 enemyy_c3 enemyz_c3
LVAR_FLOAT enemyx2_c3 enemyy2_c3 enemyz2_c3
LVAR_INT stayshoot_c3seq
LVAR_INT runkill_c3seq
LVAR_INT runstay_c3seq
LVAR_INT run2stay_c3seq
LVAR_INT peekleft_c3seq 
LVAR_INT rolloutr_c3seq
LVAR_INT runshootstay_c3seq
LVAR_INT stayshootnoduck_c3seq
LVAR_INT chasecar1a_seq
LVAR_INT chasecar1b_seq
LVAR_INT sequencetask_c3
//pickups
LVAR_INT armour1_c3 //2160.167 -2247.337 13.594
LVAR_INT armour2_c3 //2156.482 -2258.929 17.047
LVAR_INT health_c3 //2158.82 -2232.747 13.59
LVAR_INT weapon_c3

//blips
LVAR_INT warehouse_c3blip
LVAR_INT tolotov_c3blip
LVAR_INT russianboss1_c3blip
LVAR_INT russianboss2_c3blip
//flags

LVAR_INT crash_c3flag
LVAR_INT cutsceneshuttersound_c3flag
LVAR_INT forkworker1_c3flag
LVAR_INT forkworker2_c3flag
LVAR_INT workerdirector_c3flag
LVAR_INT barrel_c3flag
LVAR_INT playerattacks_c3flag
LVAR_INT workerdoor_c3flag
LVAR_INT keypadtext_c3flag
LVAR_INT openshutter_c3flag
LVAR_INT firstarea_c3flag
LVAR_INT secondarea_c3flag
LVAR_INT thirdarea_c3flag
LVAR_INT fourtharea_c3flag
LVAR_INT guard1_c3flag
LVAR_INT guard2_c3flag
LVAR_INT guard3_c3flag
LVAR_INT guard4_c3flag
LVAR_INT guard5_c3flag
LVAR_INT guard6_c3flag
LVAR_INT guard7_c3flag
LVAR_INT guard8_c3flag
LVAR_INT guard9_c3flag
LVAR_INT guard10_c3flag
LVAR_INT guard11_c3flag
LVAR_INT guard12_c3flag
LVAR_INT guard13_c3flag
LVAR_INT guard14_c3flag
LVAR_INT guard15_c3flag
LVAR_INT guard16_c3flag
LVAR_INT flat1_c3flag
LVAR_INT flat2_c3flag
LVAR_INT tolotov_c3flag
LVAR_INT box_c3flag
LVAR_INT trailer_c3flag
LVAR_INT doorcounter_c3flag
LVAR_INT russian1_c3flag
LVAR_INT helptext_c3flag
LVAR_INT missionpassed_c3flag
VAR_INT tripskip_c3flag //do not reset
LVAR_INT progressaudio_c3flag
LVAR_INT handlingudio_c3flag
LVAR_INT gettingincar_c3flag
LVAR_INT outofcar_c3flag
LVAR_INT playerincar_c3flag
LVAR_INT forklift1_c3flag
LVAR_INT forklift2_c3flag
LVAR_INT speedbalance_c3flag
//LVAR_INT speedofcar_c3flag
// **************************************** Mission Start ************************************

mission_start_drugs3:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT CRASH3 

SET_PED_DENSITY_MULTIPLIER 0.0
SET_CAR_DENSITY_MULTIPLIER 0.0
CLEAR_AREA 1036.86 -1336.49 20.0 20.0 FALSE

///////////////////////////////////////////	CUTSCENE START

LOAD_CUTSCENE CRASH3A
 
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

///////////////////////////////////////////	CUTSCENE END

SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL PLAYER1 ON
RESTORE_CAMERA_JUMPCUT

REQUEST_MODEL FORKLIFT
REQUEST_MODEL MULE
REQUEST_MODEL MP5LNG
REQUEST_MODEL SEC_KEYPAD
REQUEST_MODEL KMB_RAMP
REQUEST_MODEL BANSHEE
REQUEST_MODEL PCJ600
REQUEST_CAR_RECORDING 342

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED FORKLIFT
	OR NOT HAS_MODEL_LOADED MULE
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 342
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED SEC_KEYPAD
	OR NOT HAS_MODEL_LOADED MP5LNG
	OR NOT HAS_MODEL_LOADED KMB_RAMP
	OR NOT HAS_MODEL_LOADED BANSHEE
	OR NOT HAS_MODEL_LOADED PCJ600
	WAIT 0
ENDWHILE

SET_WANTED_MULTIPLIER 0.1

SET_PED_DENSITY_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY crash3_DM
CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE crash3_DM EVENT_DRAGGED_OUT_CAR
ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE crash3_DM EVENT_DRAGGED_OUT_CAR TASK_COMPLEX_KILL_PED_ON_FOOT 50.0 50.0 50.0 50.0 1 1

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK crash3weak_DM

SET_CHAR_COORDINATES scplayer 1038.0 -1339.0 12.8 
SET_CHAR_HEADING scplayer 1.0
SET_CAMERA_BEHIND_PLAYER

LOAD_SCENE_IN_DIRECTION 1038.0 -1340.99 13.94 1.0

DO_FADE 1500 FADE_IN

// ****************************** Declare Variables Values ***********************************


//flags
crash_c3flag = 0
cutsceneshuttersound_c3flag = 0
forkworker1_c3flag = 0
forkworker2_c3flag = 0
workerdirector_c3flag = 0
barrel_c3flag = 0
playerattacks_c3flag = 0
workerdoor_c3flag = 0
keypadtext_c3flag = 0
openshutter_c3flag = 0
firstarea_c3flag = 0
secondarea_c3flag = 0
thirdarea_c3flag = 0
fourtharea_c3flag = 0
guard1_c3flag = 0
guard2_c3flag = 0
guard3_c3flag = 0
guard4_c3flag = 0
guard5_c3flag = 0
guard6_c3flag = 0
guard7_c3flag = 0
guard8_c3flag = 0
guard9_c3flag = 0
guard10_c3flag = 0
guard11_c3flag = 0
guard12_c3flag = 0
guard13_c3flag = 0
guard14_c3flag = 0
guard15_c3flag = 0
guard16_c3flag = 0
flat1_c3flag = 0
flat2_c3flag = 0
tolotov_c3flag = 0
box_c3flag = 0
trailer_c3flag = 0
doorcounter_c3flag = 0
russian1_c3flag = 0
helptext_c3flag = 0
missionpassed_c3flag = 0
progressaudio_c3flag = 0
handlingudio_c3flag	= 0
gettingincar_c3flag = 0
outofcar_c3flag	= 0
playerincar_c3flag = 0
forklift1_c3flag = 0
forklift2_c3flag = 0
speedbalance_c3flag = 0

//speedofcar_c3flag = 0
// ************************************** Main mission ******************************************


//set up trip skip
IF tripskip_c3flag > 0
	SET_UP_SKIP	2263.378 -2226.33 12.4 47.416
ENDIF


dockshootoutloop:

WAIT 0

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
     GOTO mission_drugs3_passed
ENDIF

//get to the docks
IF crash_c3flag = 0
	ADD_BLIP_FOR_COORD 2247.639 -2202.144 12.59 warehouse_c3blip
	PRINT_NOW ( CSH3_1 ) 7000 1 //Go to docks
	crash_c3flag = 1

ENDIF

//player at docks
IF crash_c3flag = 1
	IF LOCATE_CHAR_ANY_MEANS_2D	scplayer 2247.639 -2202.144 3.0 3.0 TRUE


		//////////////////////////////////////////////////////////////////////	Create everything
		IF tripskip_c3flag = 0
			tripskip_c3flag = 1
		ENDIF
		CLEAR_SKIP

		SET_PLAYER_CONTROL PLAYER1 OFF
		SWITCH_EMERGENCY_SERVICES OFF
		DO_FADE 500 FADE_OUT

		WHILE GET_FADING_STATUS
		    WAIT 0
		ENDWHILE

		IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
			SET_CAR_PROOFS car FALSE TRUE TRUE FALSE FALSE
			SET_CAR_HEADING car	128.0
			SET_CAR_HEALTH car 2000
		ELSE
			SET_CHAR_HEADING scplayer 128.0
		ENDIF

		CLEAR_AREA 2174.896 -2258.914 150.0 150.0 TRUE

		REQUEST_MODEL imcmptrkdrl_LAS	
		REQUEST_MODEL imcmptrkdrr_LAS
		REQUEST_MODEL imcompmovedr1_las
		REQUEST_MODEL WMYMECH
		REQUEST_MODEL COLT45
		REQUEST_MODEL BARREL4


		REQUEST_CAR_RECORDING 310  //cars that go up the ramp
		REQUEST_CAR_RECORDING 328  //cars that go up the ramp
		REQUEST_CAR_RECORDING 329  //cars that go up the ramp
		REQUEST_CAR_RECORDING 309  //cars that go up the ramp

		REQUEST_CAR_RECORDING 347	//forklift truck

		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_MODEL_LOADED WMYMECH
			OR NOT HAS_MODEL_LOADED COLT45
			OR NOT HAS_MODEL_LOADED BARREL4
			WAIT 0
		ENDWHILE

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 310
			OR NOT HAS_CAR_RECORDING_BEEN_LOADED 328
			OR NOT HAS_CAR_RECORDING_BEEN_LOADED 329
			OR NOT HAS_CAR_RECORDING_BEEN_LOADED 309
			WAIT 0
		ENDWHILE

		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 347
			OR NOT HAS_MODEL_LOADED	imcmptrkdrl_LAS
			OR NOT HAS_MODEL_LOADED	imcmptrkdrr_LAS
			OR NOT HAS_MODEL_LOADED	imcompmovedr1_las
			WAIT 0
		ENDWHILE

		LOAD_SCENE 2212.02 -2225.45 12.24
		
		//truck 1
		CREATE_OBJECT imcmptrkdrl_LAS 2192.925 -2231.824 14.205 leftdoor1_c3
		CREATE_OBJECT imcmptrkdrr_LAS 2195.072 -2229.589 14.205 rightdoor1_c3
		SET_OBJECT_HEADING leftdoor1_c3 135.0
		SET_OBJECT_HEADING rightdoor1_c3 85.0

		//truck 2
		CREATE_OBJECT imcmptrkdrl_LAS 2200.184 -2224.419 14.205 leftdoor2_c3
		CREATE_OBJECT imcmptrkdrr_LAS 2202.432 -2222.266 14.205 rightdoor2_c3
		SET_OBJECT_HEADING leftdoor2_c3 135.0
		SET_OBJECT_HEADING rightdoor2_c3 85.0

		//truck 3
		CREATE_OBJECT imcmptrkdrl_LAS 2207.766 -2217.281 14.205 leftdoor3_c3
		CREATE_OBJECT imcmptrkdrr_LAS 2210.014 -2215.058 14.205 rightdoor3_c3
		SET_OBJECT_HEADING leftdoor3_c3 135.0
		SET_OBJECT_HEADING rightdoor3_c3 85.0

		//truck 4
		CREATE_OBJECT imcmptrkdrl_LAS 2215.096 -2210.761 14.205 leftdoor4_c3
		CREATE_OBJECT imcmptrkdrr_LAS 2217.344 -2208.537 14.205 rightdoor4_c3
		SET_OBJECT_HEADING leftdoor4_c3 135.0
		SET_OBJECT_HEADING rightdoor4_c3 85.0

		//create ramps
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2193.418 -2231.352 14.183 ramp1_c3
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2194.642 -2230.128 14.183 ramp2_c3
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2200.686 -2224.019 14.183 ramp3_c3
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2201.910 -2222.795 14.183 ramp4_c3
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2208.243 -2216.833 14.183 ramp5_c3
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2209.468 -2215.609 14.183 ramp6_c3
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2215.489 -2210.406 14.183 ramp7_c3
		CREATE_OBJECT_NO_OFFSET kmb_ramp 2216.713 -2209.182 14.183 ramp8_c3
		SET_OBJECT_ROTATION ramp1_c3 0.0 0.0 45.0 
		SET_OBJECT_ROTATION ramp2_c3 0.0 0.0 45.0
		SET_OBJECT_ROTATION ramp3_c3 0.0 0.0 45.0
		SET_OBJECT_ROTATION ramp4_c3 0.0 0.0 45.0
		SET_OBJECT_ROTATION ramp5_c3 0.0 0.0 45.0
		SET_OBJECT_ROTATION ramp6_c3 0.0 0.0 45.0
		SET_OBJECT_ROTATION ramp7_c3 0.0 0.0 45.0
		SET_OBJECT_ROTATION ramp8_c3 0.0 0.0 45.0

		//create shutter door
		CREATE_OBJECT_NO_OFFSET imcompmovedr1_las 2178.073 -2254.384 15.9 dockshutter_c3
		SET_OBJECT_HEADING dockshutter_c3 224.0

		//create keypad
		CREATE_OBJECT SEC_KEYPAD 2174.896 -2258.914 15.0 keypad_c3

		FREEZE_OBJECT_POSITION keypad_c3 TRUE
		SET_OBJECT_HEADING keypad_c3 35.23 //215.23
		SET_OBJECT_HEALTH keypad_c3 10
		SET_OBJECT_ONLY_DAMAGED_BY_PLAYER keypad_c3 TRUE


		//barrel
		CREATE_OBJECT BARREL4 2187.538 -2255.581 12.51 barrel5_c3
		SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel5_c3 TRUE


		////////////////////////////////////////////////////////////////////////////	Forklifts and trucks
		//parked trucks gets barrels from this truck
		CREATE_CAR MULE 2175.94 -2268.1 12.66 parkedtruck1_c3
		SET_CAR_HEADING parkedtruck1_c3 48.24
		SET_CAR_HEALTH parkedtruck1_c3 450

		//delivers boxes to this truck
		CREATE_CAR MULE 2159.395 -2295.29 12.688 parkedtruck2_c3
		SET_CAR_HEADING parkedtruck2_c3 44.51
		SET_CAR_HEALTH parkedtruck2_c3 450

		//create forklift trucks and drivers
		//transporting boxes
		CREATE_CAR FORKLIFT 2174.87 -2289.22 12.06 forklift1_c3
		SET_CAR_HEADING forklift1_c3 321.59
		CREATE_CHAR_INSIDE_CAR forklift1_c3 PEDTYPE_MISSION1 WMYMECH forkworker1_c3 
		SET_CAR_STRAIGHT_LINE_DISTANCE forklift1_c3 150
		SET_CHAR_DECISION_MAKER forkworker1_c3 crash3_DM

		//transporting barrels				
		CREATE_CAR FORKLIFT 2193.25 -2256.4 12.25 forklift2_c3
		SET_CAR_HEADING forklift2_c3 266.92
		CREATE_CHAR_INSIDE_CAR forklift2_c3 PEDTYPE_MISSION1 WMYMECH forkworker2_c3 
		SET_CAR_STRAIGHT_LINE_DISTANCE forklift2_c3 150
		SET_CHAR_DECISION_MAKER forkworker2_c3 crash3_DM

		///////////////////////////////////////////////	 FORKLIFT 1
		//go boxes picked up
		IF NOT IS_CAR_DEAD forklift1_c3
			IF NOT IS_CHAR_DEAD	forkworker1_c3
				IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
					CREATE_OBJECT BARREL4 2179.18 -2270.92 -14.0 barrel3_c3
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel3_c3 TRUE
					CREATE_OBJECT BARREL4 2179.18 -2270.92 -10.0 barrel4_c3
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel4_c3 TRUE
					ATTACH_OBJECT_TO_CAR barrel3_c3 forklift1_c3 0.5 0.3 0.3 0.0 0.0 0.0
					ATTACH_OBJECT_TO_CAR barrel4_c3 forklift1_c3 -0.5 0.3 0.3 0.0 0.0 0.0
					TASK_CAR_DRIVE_TO_COORD forkworker1_c3 forklift1_c3 2179.49 -2280.46 14.03 5.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
				ENDIF
			ENDIF
		ENDIF
		
		///////////////////////////////////////////////	 FORKLIFT 2
		IF NOT IS_CAR_DEAD forklift2_c3
			IF NOT IS_CHAR_DEAD	forkworker2_c3
				IF IS_CHAR_IN_CAR forkworker2_c3 forklift2_c3
					CREATE_OBJECT BARREL4 2179.18 -2270.92 -14.0 barrel1_c3
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel1_c3 TRUE
					CREATE_OBJECT BARREL4 2179.18 -2270.92 -10.0 barrel2_c3
					SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel2_c3 TRUE
					ATTACH_OBJECT_TO_CAR barrel1_c3 forklift2_c3 0.5 0.3 0.3 0.0 0.0 0.0
					ATTACH_OBJECT_TO_CAR barrel2_c3 forklift2_c3 -0.5 0.3 0.3 0.0 0.0 0.0
					TASK_CAR_DRIVE_TO_COORD forkworker2_c3 forklift2_c3 2209.1 -2253.64 12.25 6.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
				ENDIF
			ENDIF
		ENDIF
		///////////////////////////////////////////////

		/////////////////////////////////////////////////////////////////////////	   Cars that go into trucks
		CREATE_CAR BANSHEE 2207.7 -2246.157 12.597 chasecar1_c3
		CREATE_CHAR_INSIDE_CAR chasecar1_c3 PEDTYPE_MISSION1 WMYMECH guard1_c3
		SET_CHAR_DECISION_MAKER guard1_c3 crash3_DM

		CREATE_CAR PCJ600 2212.9 -2241.6 12.59 chasecar2_c3
		CREATE_CHAR_INSIDE_CAR chasecar2_c3 PEDTYPE_MISSION1 WMYMECH guard2_c3
		SET_CHAR_DECISION_MAKER guard2_c3 crash3_DM

		CREATE_CAR BANSHEE 2218.87 -2234.29 12.6 chasecar3_c3
		CREATE_CHAR_INSIDE_CAR chasecar3_c3 PEDTYPE_MISSION1 WMYMECH guard3_c3
		SET_CHAR_DECISION_MAKER guard3_c3 crash3_DM

		CREATE_CAR PCJ600 2224.3 -2228.19 12.597 chasecar4_c3
		CREATE_CHAR_INSIDE_CAR chasecar4_c3 PEDTYPE_MISSION1 WMYMECH guard4_c3
		SET_CHAR_DECISION_MAKER guard4_c3 crash3_DM
		/////////////////////////////////////////////////////////////////////////	   Peds
		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2181.97 -2252.695 13.82 workerdoor_c3	//guy guarding door
		SET_CHAR_HEADING workerdoor_c3 231.26
		GIVE_WEAPON_TO_CHAR workerdoor_c3 WEAPONTYPE_MP5 99999
		SET_CHAR_DECISION_MAKER workerdoor_c3 crash3_DM
		SET_CHAR_ACCURACY workerdoor_c3 30

		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2189.65 -2250.05 12.597 workerchat_c3	//guy who talks to the forklift driver
		SET_CHAR_HEADING workerchat_c3 229.24
		SET_CHAR_DECISION_MAKER workerchat_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR workerchat_c3 WEAPONTYPE_PISTOL 9999
		SET_CHAR_ACCURACY workerchat_c3 30

		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2193.126 -2233.95 12.59 workerdirector_c3	//guy who waves the car in guy who will hassle player
		SET_CHAR_HEADING workerdirector_c3 249.77
		SET_CHAR_DECISION_MAKER workerdirector_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR workerdirector_c3 WEAPONTYPE_MP5 9999
		SET_CHAR_ACCURACY workerdirector_c3 30
						
		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2199.71 -2257.841 12.597 talkworker1_c3	//two guys chatting in the middle
		SET_CHAR_HEADING talkworker1_c3 347.22
		SET_CHAR_DECISION_MAKER talkworker1_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR talkworker1_c3 WEAPONTYPE_PISTOL 9999
		SET_CHAR_ACCURACY talkworker1_c3 30
						
		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2200.2 -2256.49 12.597 talkworker2_c3	//two guys chatting in the middle
		SET_CHAR_HEADING talkworker2_c3 168.1
		TASK_CHAT_WITH_CHAR talkworker1_c3 talkworker2_c3 TRUE TRUE //talkworkert_c3 will lead the chatting
		SET_CHAR_DECISION_MAKER talkworker2_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR talkworker2_c3 WEAPONTYPE_MP5 9999
		SET_CHAR_ACCURACY talkworker2_c3 30
        TASK_CHAT_WITH_CHAR talkworker2_c3 talkworker1_c3 FALSE TRUE

		//overall view
		SET_FIXED_CAMERA_POSITION 2256.6230 -2213.5222 23.0389 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2255.7021 -2213.8325 22.8027 JUMP_CUT

		SWITCH_WIDESCREEN ON

		DO_FADE 500 FADE_IN

		IF NOT IS_CAR_DEAD chasecar1_c3
			IF NOT IS_CAR_DEAD chasecar2_c3
				IF NOT IS_CAR_DEAD chasecar3_c3
					IF NOT IS_CAR_DEAD chasecar4_c3
						START_PLAYBACK_RECORDED_CAR	chasecar2_c3 310
						START_PLAYBACK_RECORDED_CAR	chasecar3_c3 328
						START_PLAYBACK_RECORDED_CAR	chasecar4_c3 329
						START_PLAYBACK_RECORDED_CAR	chasecar1_c3 309
						PRINT_NOW CSH3_2 5000 1 //~s~The Russians and Ballas are having a ~r~meeting ~s~inside the warehouse.
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		crash_c3flag = 2

	ENDIF
ENDIF


IF crash_c3flag = 2

	IF NOT IS_CAR_DEAD chasecar1_c3
		IF IS_PLAYBACK_GOING_ON_FOR_CAR chasecar1_c3
			IF NOT IS_CAR_DEAD chasecar1_c3
				PAUSE_PLAYBACK_RECORDED_CAR chasecar1_c3
				TIMERA = 0
				crash_c3flag = 3
			ENDIF
		ENDIF
	ENDIF

	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	SKIP_CUTSCENE_START
	//////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////

ENDIF

IF crash_c3flag = 3
	IF TIMERA > 5499

		//zoomed in on car
		SET_FIXED_CAMERA_POSITION 2207.0144 -2244.2073 13.7376 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2206.3335 -2243.4856 13.6130 JUMP_CUT
	
		IF NOT IS_CHAR_DEAD workerdirector_c3
			TASK_PLAY_ANIM workerdirector_c3 HANDSUP PED 4.0 FALSE FALSE FALSE FALSE -1
		ENDIF

		IF NOT IS_CAR_DEAD chasecar1_c3
			IF IS_PLAYBACK_GOING_ON_FOR_CAR chasecar1_c3
				UNPAUSE_PLAYBACK_RECORDED_CAR chasecar1_c3
				TIMERA = 0
				crash_c3flag = 4
			ENDIF
		ENDIF

	ENDIF
ENDIF

IF crash_c3flag = 4
	IF TIMERA > 1799

		//move camera to side of car
		SET_FIXED_CAMERA_POSITION 2205.3713 -2235.2532 14.1153 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2204.3743 -2235.2654 14.0397 JUMP_CUT

		IF NOT IS_CHAR_DEAD workerdirector_c3
			TASK_PLAY_ANIM workerdirector_c3 ROADCROSS PED 4.0 FALSE FALSE FALSE FALSE -1
		ENDIF
		TIMERA = 0
		crash_c3flag = 5
	ENDIF
ENDIF

IF crash_c3flag = 5		
	IF TIMERA > 3150
		PRINT_NOW ( CSH3_3 ) 7000 2 //you will have to find your way inside

		SET_FIXED_CAMERA_POSITION 2210.2996 -2254.0671 16.7105 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2209.3108 -2254.1807 16.6143 JUMP_CUT		
				
		IF NOT IS_CHAR_DEAD talkworker2_c3
			TASK_PLAY_ANIM talkworker2_c3 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
		ENDIF

		IF NOT IS_CHAR_DEAD forkworker1_c3
			IF NOT IS_CAR_DEAD forklift1_c3
				IF NOT IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
					TASK_CAR_DRIVE_TO_COORD forkworker1_c3 forklift1_c3 2187.88 -2265.11 12.6 6.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
				ENDIF
			ENDIF
		ENDIF

		TIMERA = 0
		crash_c3flag = 6
	ENDIF
ENDIF


IF TIMERA > 2999
	IF crash_c3flag = 6

		//keypad guy
		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2177.95 -2261.002 13.818 guard5_c3
		SET_CHAR_HEADING guard5_c3 45.4
		SET_CHAR_DECISION_MAKER guard5_c3 crash3_DM
		//walking up to the keypad
		SET_FIXED_CAMERA_POSITION 2187.0791 -2255.2695 15.5563 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2186.0818 -2255.3162 15.5002 JUMP_CUT
		
		OPEN_SEQUENCE_TASK cutscenec3_seq
		TASK_GO_STRAIGHT_TO_COORD -1 2175.29 -2259.22 14.8 PEDMOVE_RUN -1
		TASK_PLAY_ANIM -1 ATM PED 4.0 FALSE FALSE FALSE FALSE -1
		CLOSE_SEQUENCE_TASK cutscenec3_seq
		PERFORM_SEQUENCE_TASK guard5_c3 cutscenec3_seq
		CLEAR_SEQUENCE_TASK cutscenec3_seq

		IF NOT IS_CHAR_DEAD workerdoor_c3
			TASK_PLAY_ANIM workerdoor_c3 ROADCROSS PED 4.0 FALSE FALSE FALSE FALSE -1
		ENDIF


		WHILE NOT cutsceneshuttersound_c3flag = 6
	
		WAIT 0

			IF cutsceneshuttersound_c3flag = 0
				IF NOT IS_CHAR_DEAD guard5_c3
					IF LOCATE_STOPPED_CHAR_ON_FOOT_2D guard5_c3 2175.29 -2259.22 3.0 3.0 FALSE
						TIMERB = 0
						cutsceneshuttersound_c3flag = 1
					ENDIF
				ENDIF
			ENDIF

			IF cutsceneshuttersound_c3flag = 1
				IF NOT IS_CHAR_DEAD guard5_c3
					IF LOCATE_STOPPED_CHAR_ON_FOOT_2D guard5_c3 2175.29 -2259.22 3.0 3.0 FALSE
						IF TIMERB > 1000
							TASK_ACHIEVE_HEADING guard5_c3 308.1
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT dockshutter_c3 SOUND_SHUTTER_DOOR_START
							//ADD_ONE_OFF_SOUND 2181.127 -2251.999 14.036 SOUND_POLICE_CELL_DOOR_CLUNK
							//ADD_CONTINUOUS_SOUND 2181.127 -2251.999 14.036 SOUND_POLICE_CELL_DOOR_SLIDING_LOOP shuttersound_c3
							TIMERB = 0
							guard5_c3flag = 1
							cutsceneshuttersound_c3flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF

			IF guard5_c3flag = 1
				IF NOT IS_CHAR_DEAD guard5_c3
					GET_SCRIPT_TASK_STATUS guard5_c3 TASK_ACHIEVE_HEADING scriptstatus_c3
						IF scriptstatus_c3 = FINISHED_TASK
						OR TIMERB > 1000
							TASK_GO_STRAIGHT_TO_COORD guard5_c3 2179.523 -2256.364 14.81 PEDMOVE_RUN 5000
							guard5_c3flag = 2
						ENDIF
				ENDIF
			ENDIF


			IF cutsceneshuttersound_c3flag = 2
				IF NOT IS_CHAR_DEAD guard5_c3
					IF DOES_OBJECT_EXIST dockshutter_c3
						IF SLIDE_OBJECT dockshutter_c3 2178.073 -2254.384 19.7 0.0 0.0 0.15 FALSE
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT dockshutter_c3 SOUND_SHUTTER_DOOR_STOP
							cutsceneshuttersound_c3flag = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		
			IF cutsceneshuttersound_c3flag = 3
				IF NOT IS_CHAR_DEAD guard5_c3
					IF LOCATE_CHAR_ON_FOOT_2D guard5_c3 2179.522 -2256.364 1.0 1.0 FALSE	
						TASK_GO_STRAIGHT_TO_COORD guard5_c3 2173.5 -2250.35 14.81 PEDMOVE_RUN 5000
						cutsceneshuttersound_c3flag = 4
					ENDIF
				ENDIF
			ENDIF

			IF cutsceneshuttersound_c3flag = 4
				IF NOT IS_CHAR_DEAD guard5_c3
					IF LOCATE_CHAR_ON_FOOT_2D guard5_c3 2174.31 -2251.09 1.0 1.0 FALSE	
						TASK_GO_STRAIGHT_TO_COORD guard5_c3 2168.66 -2254.9035 14.59 PEDMOVE_RUN 5000
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT dockshutter_c3 SOUND_SHUTTER_DOOR_START
						cutsceneshuttersound_c3flag = 5
					ENDIF
				ENDIF
			ENDIF

			IF cutsceneshuttersound_c3flag = 5
				IF DOES_OBJECT_EXIST dockshutter_c3
					IF SLIDE_OBJECT dockshutter_c3 2178.073 -2254.384 15.9 0.0 0.0 0.15 FALSE
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT dockshutter_c3 SOUND_SHUTTER_DOOR_STOP						
						cutsceneshuttersound_c3flag = 6
					ENDIF		
				ENDIF
			ENDIF
							
		ENDWHILE

		crash_c3flag = 7
	ENDIF
ENDIF


IF crash_c3flag = 7

	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////
	SKIP_CUTSCENE_END
	//////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////


	DO_FADE 500 FADE_OUT
	
	WHILE GET_FADING_STATUS
	    WAIT 0
	ENDWHILE

	REPORT_MISSION_AUDIO_EVENT_AT_OBJECT dockshutter_c3 SOUND_SHUTTER_DOOR_STOP

	guard5_c3flag = 0
	STOP_PLAYBACK_RECORDED_CAR chasecar1_c3
	STOP_PLAYBACK_RECORDED_CAR chasecar2_c3
	STOP_PLAYBACK_RECORDED_CAR chasecar3_c3
	STOP_PLAYBACK_RECORDED_CAR chasecar4_c3

	//REMOVE_SOUND shuttersound_c3				//remove sound

	DELETE_OBJECT dockshutter_c3 					//shutter,ramps and truck doors etc.
	DELETE_OBJECT ramp1_c3
	DELETE_OBJECT ramp2_c3
	DELETE_OBJECT ramp3_c3
	DELETE_OBJECT ramp4_c3
	DELETE_OBJECT ramp5_c3
	DELETE_OBJECT ramp6_c3
	DELETE_OBJECT ramp7_c3
	DELETE_OBJECT ramp8_c3
	DELETE_OBJECT leftdoor1_c3
	DELETE_OBJECT rightdoor1_c3
	DELETE_OBJECT leftdoor2_c3
	DELETE_OBJECT rightdoor2_c3
	DELETE_OBJECT leftdoor3_c3
	DELETE_OBJECT rightdoor3_c3
	DELETE_OBJECT leftdoor4_c3
	DELETE_OBJECT rightdoor4_c3

	DELETE_CAR chasecar1_c3
	DELETE_CAR chasecar2_c3
	DELETE_CAR chasecar3_c3
	DELETE_CAR chasecar4_c3
	DELETE_CAR forklift1_c3
	DELETE_CAR forklift2_c3
	DELETE_CHAR forkworker1_c3
	DELETE_CHAR forkworker2_c3
	DELETE_OBJECT barrel1_c3
	DELETE_OBJECT barrel2_c3
	DELETE_OBJECT barrel3_c3
	DELETE_OBJECT barrel4_c3

	DELETE_CHAR guard1_c3
	DELETE_CHAR guard2_c3
	DELETE_CHAR guard3_c3
	DELETE_CHAR guard4_c3
	DELETE_CHAR guard5_c3
	CLEAR_AREA 2192.925 -2231.824 100.0 100.0 FALSE
	//truck 1
	CREATE_OBJECT imcmptrkdrl_LAS 2192.925 -2231.824 14.205 leftdoor1_c3
	CREATE_OBJECT imcmptrkdrr_LAS 2195.072 -2229.589 14.205 rightdoor1_c3
	SET_OBJECT_HEADING leftdoor1_c3 315.0
	SET_OBJECT_HEADING rightdoor1_c3 315.0

	//truck 2
	CREATE_OBJECT imcmptrkdrl_LAS 2200.184 -2224.419 14.205 leftdoor2_c3
	CREATE_OBJECT imcmptrkdrr_LAS 2202.432 -2222.266 14.205 rightdoor2_c3
	SET_OBJECT_HEADING leftdoor2_c3 315.0
	SET_OBJECT_HEADING rightdoor2_c3 315.0

	//truck 3
	CREATE_OBJECT imcmptrkdrl_LAS 2207.766 -2217.281 14.205 leftdoor3_c3
	CREATE_OBJECT imcmptrkdrr_LAS 2210.014 -2215.058 14.205 rightdoor3_c3
	SET_OBJECT_HEADING leftdoor3_c3 315.0
	SET_OBJECT_HEADING rightdoor3_c3 315.0

	//truck 4
	CREATE_OBJECT imcmptrkdrl_LAS 2215.096 -2210.761 14.205 leftdoor4_c3
	CREATE_OBJECT imcmptrkdrr_LAS 2217.344 -2208.537 14.205 rightdoor4_c3
	SET_OBJECT_HEADING leftdoor4_c3 315.0
	SET_OBJECT_HEADING rightdoor4_c3 315.0

	CREATE_OBJECT_NO_OFFSET imcompmovedr1_las 2178.073 -2254.384 15.9 dockshutter_c3
	SET_OBJECT_HEADING dockshutter_c3 224.0


	CREATE_CAR FORKLIFT 2176.89 -2291.75 12.25 forklift1_c3
	SET_CAR_HEADING forklift1_c3 321.59
	CREATE_CHAR_INSIDE_CAR forklift1_c3 PEDTYPE_MISSION1 WMYMECH forkworker1_c3 
	GIVE_WEAPON_TO_CHAR forkworker1_c3 WEAPONTYPE_PISTOL 9999
	SET_CHAR_ACCURACY forkworker1_c3 30
	SET_CAR_STRAIGHT_LINE_DISTANCE forklift1_c3 150
	SET_CHAR_DECISION_MAKER forkworker1_c3 crash3_DM
	SET_CAR_HEALTH forklift1_c3 600
	SET_CAR_ONLY_DAMAGED_BY_PLAYER forklift1_c3 TRUE
	
	//transporting barrels				
	CREATE_CAR FORKLIFT 2210.81 -2253.72 12.598 forklift2_c3
	SET_CAR_HEADING forklift2_c3 71.09
	CREATE_CHAR_INSIDE_CAR forklift2_c3 PEDTYPE_MISSION1 WMYMECH forkworker2_c3 
	GIVE_WEAPON_TO_CHAR forkworker2_c3 WEAPONTYPE_PISTOL 9999
	SET_CHAR_ACCURACY forkworker2_c3 30
	SET_CAR_STRAIGHT_LINE_DISTANCE forklift2_c3 150
	SET_CHAR_DECISION_MAKER forkworker2_c3 crash3_DM
	SET_CAR_ONLY_DAMAGED_BY_PLAYER forklift2_c3 TRUE
	SET_CAR_HEALTH forklift2_c3 600
	
	CREATE_OBJECT BARREL4 2179.18 -2270.92 -14.0 barrel1_c3
	CREATE_OBJECT BARREL4 2179.18 -2270.92 -10.0 barrel2_c3
	SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel1_c3 TRUE
	SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel2_c3 TRUE

	ATTACH_OBJECT_TO_CAR barrel1_c3 forklift2_c3 0.5 0.3 0.3 0.0 0.0 0.0
	ATTACH_OBJECT_TO_CAR barrel2_c3 forklift2_c3 -0.5 0.3 0.3 0.0 0.0 0.0

	CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2186.507 -2244.993 15.81 armour1_c3
	CREATE_PICKUP BODYARMOUR PICKUP_ONCE 2146.559 -2244.46 13.58 armour2_c3
	CREATE_PICKUP HEALTH PICKUP_ONCE 2158.82 -2232.747 13.59 health_c3


	//MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
	//MARK_MODEL_AS_NO_LONGER_NEEDED BANSHEE

	REQUEST_MODEL BALLAS1
	REQUEST_MODEL IMY_BBOX
	REQUEST_ANIMATION SWAT
	REQUEST_ANIMATION DODGE
	REQUEST_CAR_RECORDING 347

	LOAD_SPECIAL_CHARACTER 1 ANDRE

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_MODEL_LOADED BALLAS1
		OR NOT HAS_ANIMATION_LOADED SWAT
		OR NOT HAS_MODEL_LOADED IMY_BBOX
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 347
		WAIT 0
	ENDWHILE
	
	WHILE NOT HAS_ANIMATION_LOADED DODGE
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
		WAIT 0
	ENDWHILE


	SWITCH_WIDESCREEN OFF

	SET_PLAYER_CONTROL player1 ON
	CLEAR_PRINTS
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	REMOVE_BLIP warehouse_c3blip
	ADD_BLIP_FOR_COORD 2178.38 -2254.89 13.81 warehouse_c3blip

	

	DO_FADE 500 FADE_IN

//	
	CREATE_OBJECT_NO_OFFSET Imy_la_door 2118.088 -2274.635 20.867 backdoor_c3
	SET_OBJECT_HEADING backdoor_c3 225.0

	crash_c3flag = 8

ENDIF

///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////Resume Player Control ///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////

//	This is ambient stuff for when the player is not hostile
IF crash_c3flag = 8

	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// Opening door //////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////

	//help text
	IF keypadtext_c3flag = 0
		IF DOES_OBJECT_EXIST keypad_c3
			IF NOT HAS_OBJECT_BEEN_DAMAGED keypad_c3 
				IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer keypad_c3 15.0 15.0 4.0 FALSE
					PRINT_HELP CSH3_7 //The door is locked damage the keypad that might get it open.
					keypadtext_c3flag = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF keypadtext_c3flag = 1
		IF DOES_OBJECT_EXIST keypad_c3
			IF NOT HAS_OBJECT_BEEN_DAMAGED keypad_c3 
				IF LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer keypad_c3 5.0 5.0 4.0 FALSE
					PRINT_HELP CSH3_7 //The door is locked damage the keypad that might get it open.
					keypadtext_c3flag = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF keypadtext_c3flag = 2
		IF DOES_OBJECT_EXIST keypad_c3
			IF NOT HAS_OBJECT_BEEN_DAMAGED keypad_c3 
				IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_3D scplayer keypad_c3 5.0 5.0 4.0 FALSE
					keypadtext_c3flag = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

//	//open door next part of mission
	IF DOES_OBJECT_EXIST keypad_c3
		IF HAS_OBJECT_BEEN_DAMAGED keypad_c3
			CLEAR_PRINTS
			crash_c3flag = 9
		ENDIF
	ENDIF


	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////// Russians do normal ambient stuff ///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////	
	///////////////////////////////////////////////////////////////////////////////////////////////////

	IF playerattacks_c3flag = 0

		IF forkworker2_c3flag = 0
			IF workerdirector_c3flag = 0
				IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2198.686 -2180.832 2116.212 -2263.317 82.0 FALSE
					forkworker2_c3flag = 1
					workerdirector_c3flag = 1
				ENDIF
			ENDIF
		ENDIF

		///////////////////////////////////////////////////////////////////// Russians become hostile
		
		//player drives inside area
		IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2172.36 -2207.176 2116.212 -2263.317 82.0 FALSE
			IF IS_CHAR_IN_ANY_CAR scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF				

		//player shoots or targets anything in area
		IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2198.686 -2180.832 2116.212 -2263.317 82.0 FALSE
			
			IF IS_CHAR_SHOOTING scplayer 
				playerattacks_c3flag = 1
			ENDIF

			IF IS_PLAYER_TARGETTING_ANYTHING PLAYER1 
				playerattacks_c3flag = 1
			ENDIF

			
		ENDIF

//		//guy at door warns playr to leave area
//		IF workerdoor_c3flag = 0
//			IF NOT IS_CHAR_DEAD workerdoor_c3
//				IF LOCATE_CHAR_ON_FOOT_2D scplayer 2178.94 -2254.93 8.0 8.0 FALSE
//					PRINT_NOW CSH3_14 5000 1 //Step away from the door punk, get out of here or I will shoot your brains out!
//					TASK_LOOK_AT_CHAR workerdoor_c3 scplayer 10000
//					TIMERB = 0
//					workerdoor_c3flag = 1
//				ENDIF
//			ENDIF
//		ENDIF
//
//		IF workerdoor_c3flag = 1
//			IF TIMERB > 7000
//				IF LOCATE_CHAR_ON_FOOT_2D scplayer 2178.94 -2254.93 8.0 8.0 FALSE
//					PRINT_NOW CSH3_15 5000 1 //You had it punk, time to eat bullets! Get him!
//					playerattacks_c3flag = 1
//					workerdoor_c3flag = 2
//				ELSE
//					workerdoor_c3flag = 0
//				ENDIF
//			ENDIF
//		ENDIF

		//anyone damaged
		IF DOES_CHAR_EXIST forkworker1_c3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR forkworker1_c3 scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST forkworker2_c3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR forkworker1_c3 scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST workerdoor_c3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR workerdoor_c3 scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST workerchat_c3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR workerchat_c3 scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST workerdirector_c3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR workerdirector_c3 scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST talkworker1_c3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR talkworker1_c3 scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF
		IF DOES_CHAR_EXIST talkworker2_c3
			IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR talkworker2_c3 scplayer
				playerattacks_c3flag = 1
			ENDIF
		ENDIF

		/////////////////////////////////////////////////////////////////////


		//////////////////////////////////////////////////////////////////// Guy who hassles player
		IF workerdirector_c3flag = 1
			IF NOT IS_CHAR_DEAD workerdirector_c3
				IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2198.686 -2180.832 2116.212 -2263.317 82.0 FALSE
					TASK_GOTO_CHAR_OFFSET workerdirector_c3 scplayer -1 1.5 0.0
					LOAD_MISSION_AUDIO 1 SOUND_CRA3_EA	//Hey, who the fuck are you?
					LOAD_MISSION_AUDIO 2 SOUND_CRA3_EC //You’re putting your nose where it’s not wanted!
					workerdirector_c3flag = 2
				ENDIF
			ENDIF
		ENDIF
		IF workerdirector_c3flag = 2
			IF NOT IS_CHAR_DEAD workerdirector_c3
				IF LOCATE_CHAR_ANY_MEANS_CHAR_2D workerdirector_c3 scplayer 5.0 5.0 FALSE
					ATTACH_MISSION_AUDIO_TO_CHAR 1 workerdirector_c3
					ATTACH_MISSION_AUDIO_TO_CHAR 2 workerdirector_c3 
					workerdirector_c3flag = 3
				ENDIF
			ENDIF
		ENDIF
		IF workerdirector_c3flag = 3
			IF NOT IS_CHAR_DEAD workerdirector_c3
				IF HAS_MISSION_AUDIO_LOADED 1
					PLAY_MISSION_AUDIO 1
					PRINT_NOW CRA3_EA 3000 1 //Hey, who the fuck are you?
					workerdirector_c3flag = 4
				ENDIF
			ENDIF
		ENDIF
		IF workerdirector_c3flag = 4
			IF NOT IS_CHAR_DEAD workerdirector_c3
				IF HAS_MISSION_AUDIO_FINISHED 1
					IF HAS_MISSION_AUDIO_LOADED 2
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 1
						PLAY_MISSION_AUDIO 2 //You’re putting your nose where it’s not wanted!
						PRINT_NOW CRA3_EC 3000 1
						workerdirector_c3flag = 5
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF workerdirector_c3flag = 5
			IF HAS_MISSION_AUDIO_FINISHED 2
				CLEAR_PRINTS
				playerattacks_c3flag = 1
				workerdirector_c3flag = 6
			ENDIF
		ENDIF

		////////////////////////////////////////////////////////////////////	

		//////////////////////////////////////////////////////////////////// Forklift truck with barrels moves as player enters area
		IF forkworker2_c3flag = 1
			IF NOT IS_CAR_DEAD forklift2_c3
				IF NOT IS_CHAR_DEAD	forkworker2_c3
					IF IS_CHAR_IN_CAR forkworker2_c3 forklift2_c3
						IF IS_CAR_ON_SCREEN forklift2_c3
							TASK_CAR_DRIVE_TO_COORD forkworker2_c3 forklift2_c3 2195.84 -2251.522 12.598 5.0 MODE_NORMAL FALSE DRIVINGMODE_PLOUGHTHROUGH
							forkworker2_c3flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF forkworker2_c3flag = 2
			IF NOT IS_CHAR_DEAD forkworker2_c3
				IF NOT IS_CAR_DEAD forklift2_c3
					IF IS_CHAR_IN_CAR forkworker2_c3 forklift2_c3
						IF LOCATE_STOPPED_CAR_2D forklift2_c3 2195.84 -2251.522 3.0 3.0 FALSE
							IF NOT IS_CHAR_DEAD forkworker2_c3
								IF NOT IS_CHAR_DEAD workerchat_c3
									TASK_LOOK_AT_CHAR forkworker2_c3 workerchat_c3 30000
									TASK_GO_STRAIGHT_TO_COORD workerchat_c3 2197.896 -2249.251 12.59 PEDMOVE_WALK -1
								ENDIF
								forkworker2_c3flag = 3
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF forkworker2_c3flag = 3
			IF NOT IS_CHAR_DEAD forkworker2_c3
				IF IS_CHAR_SITTING_IN_ANY_CAR forkworker2_c3
					IF NOT IS_CHAR_DEAD workerchat_c3
						IF LOCATE_STOPPED_CHAR_ON_FOOT_2D workerchat_c3 2197.896 -2249.251 3.0 3.0 FALSE
							TASK_TURN_CHAR_TO_FACE_CHAR workerchat_c3 forkworker2_c3
							TASK_LOOK_AT_CHAR forkworker2_c3 workerchat_c3 60000
							forkworker2_c3flag = 4
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF forkworker2_c3flag = 4
			IF NOT IS_CHAR_DEAD forkworker2_c3
				IF IS_CHAR_SITTING_IN_ANY_CAR forkworker2_c3
					IF NOT IS_CHAR_DEAD workerchat_c3
						IF LOCATE_STOPPED_CHAR_ON_FOOT_2D workerchat_c3 2197.896 -2249.251 3.0 3.0 FALSE
							TASK_PLAY_ANIM workerchat_c3 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
							forkworker2_c3flag = 5
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		////////////////////////////////////////////////////////////////////////////fork lift truck boxes
		//go boxes picked up
		IF forkworker1_c3flag = 0
			IF NOT IS_CAR_DEAD forklift1_c3
				IF NOT IS_CHAR_DEAD	forkworker1_c3
					IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
						forkworker1_c3flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF forkworker1_c3flag = 1
			IF NOT IS_CAR_DEAD forklift1_c3
				IF NOT IS_CHAR_DEAD	forkworker1_c3
					IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
						IF HAS_CAR_RECORDING_BEEN_LOADED 347
							START_PLAYBACK_RECORDED_CAR_LOOPED forklift1_c3 347
							
							CREATE_OBJECT BARREL4 2179.18 -2270.92 -14.0 barrel3_c3
							CREATE_OBJECT BARREL4 2179.18 -2270.92 -10.0 barrel4_c3
							ATTACH_OBJECT_TO_CAR barrel3_c3 forklift1_c3 0.5 0.3 0.3 0.0 0.0 0.0
							ATTACH_OBJECT_TO_CAR barrel4_c3 forklift1_c3 -0.5 0.3 0.3 0.0 0.0 0.0

							SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel3_c3 TRUE
							SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel4_c3 TRUE
							barrel_c3flag = 1
							forkworker1_c3flag = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//as soon as stopped
		IF forkworker1_c3flag = 2
			IF NOT IS_CAR_DEAD forklift1_c3
				IF NOT IS_CHAR_DEAD	forkworker1_c3
					IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
						IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR forklift1_c3
							TIMERA = 0
							forkworker1_c3flag = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//restart
		IF forkworker1_c3flag = 3
			IF NOT IS_CAR_DEAD forklift1_c3
				IF NOT IS_CHAR_DEAD	forkworker1_c3
					IF IS_CHAR_IN_CAR forkworker1_c3 forklift1_c3
						IF TIMERA > 500
							forkworker1_c3flag = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//drop boxes
		IF barrel_c3flag = 1
			IF NOT IS_CAR_DEAD forklift1_c3
				IF NOT IS_CHAR_DEAD	forkworker1_c3
					IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
						IF LOCATE_CAR_2D forklift1_c3 2179.18 -2270.92 2.0 2.0 FALSE
							DELETE_OBJECT barrel3_c3
							DELETE_OBJECT barrel4_c3
							barrel_c3flag = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF 
		ENDIF

		//char not in car
		IF forkworker1_c3flag < 4
			IF NOT IS_CAR_DEAD forklift1_c3
				IF NOT IS_CHAR_DEAD forkworker1_c3
					IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
					ELSE
						STOP_PLAYBACK_RECORDED_CAR forklift1_c3
						forkworker1_c3flag = 4
					ENDIF
				ELSE
					STOP_PLAYBACK_RECORDED_CAR forklift1_c3
					forkworker1_c3flag = 4
				ENDIF
			ELSE
				STOP_PLAYBACK_RECORDED_CAR forklift1_c3
				forkworker1_c3flag = 4
			ENDIF
		ENDIF

	ENDIF

	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// Attack Player /////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////


	IF playerattacks_c3flag = 1

		 //take cover then attack freely
		 IF NOT IS_CHAR_DEAD talkworker1_c3
			enemy_c3 = talkworker1_c3
			enemyx_c3 = 2203.12
			enemyy_c3 = -2264.767
			enemyz_c3 = 14.0
			GOSUB runkill_c3label
		 ENDIF
		 IF NOT IS_CHAR_DEAD talkworker2_c3
			enemy_c3 = talkworker2_c3
			enemyx_c3 = 2188.586
			enemyy_c3 = -2258.273
			enemyz_c3 = 14.0
			GOSUB runstay_c3label
		 ENDIF
		 IF NOT IS_CHAR_DEAD workerchat_c3
			enemy_c3 = workerchat_c3
			enemyx_c3 = 2207.985
			enemyy_c3 = -2244.51
			enemyz_c3 = 14.0
			GOSUB runkill_c3label
		 ENDIF
		 IF NOT IS_CHAR_DEAD workerdirector_c3
			TASK_KILL_CHAR_ON_FOOT workerdirector_c3 scplayer
		 ENDIF

		IF NOT IS_CHAR_DEAD workerdoor_C3
			enemy_c3 = workerdoor_C3
			GOSUB stayshoot_c3label
		ENDIF

		//forklift trucks
		IF NOT IS_CAR_DEAD forklift1_c3
			IF NOT IS_CHAR_DEAD forkworker1_c3
				IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
					IF NOT IS_CAR_DEAD forklift1_c3
						STOP_PLAYBACK_RECORDED_CAR forklift1_c3
						TASK_CAR_MISSION forkworker1_c3 forklift1_c3 -1 MISSION_RAMPLAYER_FARAWAY 6.0 DRIVINGMODE_PLOUGHTHROUGH
						forklift1_c3flag = 1
					ENDIF
//				ELSE
//					TASK_KILL_CHAR_ON_FOOT forkworker1_c3 scplayer 
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CAR_DEAD forklift2_c3
			IF NOT IS_CHAR_DEAD forkworker2_c3
				IF IS_CHAR_SITTING_IN_CAR forkworker2_c3 forklift2_c3
					IF NOT IS_CAR_DEAD forklift2_c3
						TASK_CAR_MISSION forkworker2_c3 forklift2_c3 -1 MISSION_RAMPLAYER_FARAWAY 5.0 DRIVINGMODE_PLOUGHTHROUGH
						forklift2_c3flag = 1
					ENDIF
//				ELSE
//					TASK_KILL_CHAR_ON_FOOT forkworker2_c3 scplayer 
				ENDIF
			ENDIF
		ENDIF

		playerattacks_c3flag = 2

	ENDIF

	//forklift truck attack patterns
	IF forklift1_c3flag = 1
		IF NOT IS_CAR_DEAD forklift1_c3
			IF NOT IS_CHAR_DEAD forkworker1_c3
				IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
					IF IS_CHAR_TOUCHING_CHAR forkworker1_c3	scplayer
						TASK_CAR_TEMP_ACTION forkworker1_c3 forklift1_c3 TEMPACT_REVERSE 2000
						forklift1_c3flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF forklift1_c3flag = 2
		IF NOT IS_CAR_DEAD forklift1_c3
			IF NOT IS_CHAR_DEAD forkworker1_c3
				IF IS_CHAR_SITTING_IN_CAR forkworker1_c3 forklift1_c3
					GET_SCRIPT_TASK_STATUS forkworker1_c3 TASK_CAR_TEMP_ACTION sequencetask_c3
						IF sequencetask_c3 = FINISHED_TASK
							TASK_CAR_MISSION forkworker1_c3 forklift1_c3 -1 MISSION_RAMPLAYER_FARAWAY 6.0 DRIVINGMODE_PLOUGHTHROUGH
							forklift1_c3flag = 1
						ENDIF 
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF forklift2_c3flag = 1
		IF NOT IS_CAR_DEAD forklift2_c3
			IF NOT IS_CHAR_DEAD forkworker2_c3
				IF IS_CHAR_SITTING_IN_CAR forkworker2_c3 forklift2_c3
					IF IS_CHAR_TOUCHING_CHAR forkworker2_c3	scplayer
						TASK_CAR_TEMP_ACTION forkworker2_c3 forklift2_c3 TEMPACT_REVERSE 2000
						forklift2_c3flag = 2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF forklift2_c3flag = 2
		IF NOT IS_CAR_DEAD forklift2_c3
			IF NOT IS_CHAR_DEAD forkworker2_c3
				IF IS_CHAR_SITTING_IN_CAR forkworker2_c3 forklift2_c3
					GET_SCRIPT_TASK_STATUS forkworker2_c3 TASK_CAR_TEMP_ACTION sequencetask_c3
						IF sequencetask_c3 = FINISHED_TASK
							TASK_CAR_MISSION forkworker2_c3 forklift2_c3 -1 MISSION_RAMPLAYER_FARAWAY 6.0 DRIVINGMODE_PLOUGHTHROUGH
							forklift2_c3flag = 1
						ENDIF 
				ENDIF
			ENDIF
		ENDIF
	ENDIF

ENDIF


	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////// INSIDE WAREHOUSE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////

//opening the shutter
IF crash_c3flag = 9
	IF openshutter_c3flag = 0
		PRINT_NOW CSH3_13 5000 1//office at back
		DELETE_OBJECT keypad_c3
		MARK_MODEL_AS_NO_LONGER_NEEDED SEC_KEYPAD
		ADD_ONE_OFF_SOUND 2181.127 -2251.999 14.036 SOUND_POLICE_CELL_DOOR_CLUNK
		ADD_CONTINUOUS_SOUND 2181.127 -2251.999 14.036 SOUND_POLICE_CELL_DOOR_SLIDING_LOOP shuttersound_c3
		REMOVE_BLIP warehouse_c3blip
		SET_MAX_FIRE_GENERATIONS 0
		//create flat boss
		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2125.8 -2274.24 19.67 tolotov_c3 //
		SET_CHAR_HEADING tolotov_c3 221.579
		SET_CHAR_DECISION_MAKER tolotov_c3 crash3_DM
		SET_CHAR_HEALTH tolotov_c3 1000
		SET_CHAR_MAX_HEALTH tolotov_c3 1000
		SET_CHAR_MONEY tolotov_c3 3000
		SET_CHAR_SUFFERS_CRITICAL_HITS tolotov_c3 FALSE
		SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY tolotov_c3 TRUE
		ADD_BLIP_FOR_CHAR tolotov_c3 tolotov_c3blip

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2129.2166 -2281.7803 19.6719 russianboss1_c3 //
		SET_CHAR_HEADING russianboss1_c3 314.7061
		SET_CHAR_DECISION_MAKER russianboss1_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR russianboss1_c3 WEAPONTYPE_PISTOL 99999
		SET_CHAR_WEAPON_SKILL russianboss1_c3 WEAPONSKILL_PRO
		SET_CHAR_ACCURACY russianboss1_c3 20
		SET_CHAR_HEALTH russianboss1_c3 50
//		ADD_BLIP_FOR_CHAR russianboss1_c3 russianboss1_c3blip

		CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2124.1536 -2277.2427 19.6719 russianboss2_c3 //
		SET_CHAR_HEADING russianboss2_c3 241.1202
		SET_CHAR_DECISION_MAKER russianboss2_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR russianboss2_c3 WEAPONTYPE_PISTOL 99999
		SET_CHAR_WEAPON_SKILL russianboss2_c3 WEAPONSKILL_PRO
		SET_CHAR_ACCURACY russianboss2_c3 20
		SET_CHAR_HEALTH russianboss2_c3 50
//		ADD_BLIP_FOR_CHAR russianboss2_c3 russianboss2_c3blip
		missionpassed_c3flag = 1

		//create guys here
		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2172.73 -2249.83 12.6 guard1_c3 //runs towards the player
		SET_CHAR_HEADING guard1_c3 227.0
		SET_CHAR_DECISION_MAKER guard1_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR guard1_c3 WEAPONTYPE_PISTOL 99999
		SET_CHAR_ACCURACY guard1_c3 20
		SET_CHAR_HEALTH guard1_c3 30

		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2168.42 -2252.714 12.59 guard2_c3 //stays at corner shooting
		SET_CHAR_HEADING guard2_c3 250.942
		SET_CHAR_DECISION_MAKER guard2_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR guard2_c3 WEAPONTYPE_PISTOL 99999
		SET_CHAR_ACCURACY guard2_c3 20
		SET_CHAR_SHOOT_RATE guard2_c3 30
		SET_CHAR_HEALTH guard2_c3 20

		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2174.66 -2248.6 12.6 guard3_c3 //runs towards the player
		SET_CHAR_HEADING guard3_c3 227.0
		SET_CHAR_DECISION_MAKER guard3_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR guard3_c3 WEAPONTYPE_MP5 99999
		SET_CHAR_ALLOWED_TO_DUCK guard3_c3 FALSE
		SET_CHAR_ACCURACY guard3_c3 20

//		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2162.758 -2268.065 19.712 guard4_c3 //right side of railing -> 2157.128 -2260.676 20.7
//		SET_CHAR_HEADING guard4_c3 35.38
//		SET_CHAR_DECISION_MAKER guard4_c3 crash3_DM
//		GIVE_WEAPON_TO_CHAR guard4_c3 WEAPONTYPE_MP5 99999
//		SET_CHAR_ALLOWED_TO_DUCK guard4_c3 FALSE
//		SET_CHAR_ACCURACY guard4_c3 20

		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2163.0717 -2246.027 12.59 guard5_c3 //peeking to his left
		SET_CHAR_HEADING guard5_c3 224.58
		SET_CHAR_DECISION_MAKER guard5_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR guard5_c3 WEAPONTYPE_MP5 99999
		SET_CHAR_ACCURACY guard5_c3 20

		CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2158.03 -2233.23 12.59 guard6_c3 //rolling right
		SET_CHAR_HEADING guard6_c3 230.37
		SET_CHAR_DECISION_MAKER guard6_c3 crash3_DM
		GIVE_WEAPON_TO_CHAR guard6_c3 WEAPONTYPE_PISTOL 99999
		SET_CHAR_ACCURACY guard6_c3 20

		IF guard1_c3flag = 0
			IF NOT IS_CHAR_DEAD guard1_c3
				TASK_KILL_CHAR_ON_FOOT guard1_c3 scplayer
				guard1_c3flag = 1
			ENDIF
		ENDIF
		IF guard3_c3flag = 0
			IF NOT IS_CHAR_DEAD guard3_c3
				TASK_KILL_CHAR_ON_FOOT guard3_c3 scplayer
				guard3_c3flag = 1
			ENDIF
		ENDIF

		IF DOES_OBJECT_EXIST dockshutter_c3
			REPORT_MISSION_AUDIO_EVENT_AT_OBJECT dockshutter_c3 SOUND_SHUTTER_DOOR_START
		ENDIF
		openshutter_c3flag = 1
		crash_c3flag = 10

	ENDIF
ENDIF

IF openshutter_c3flag = 1
	IF DOES_OBJECT_EXIST dockshutter_c3
		IF SLIDE_OBJECT dockshutter_c3 2178.073 -2254.384 19.7 0.0 0.0 0.1 FALSE
			REPORT_MISSION_AUDIO_EVENT_AT_OBJECT dockshutter_c3 SOUND_SHUTTER_DOOR_STOP
			//and more guys here
			openshutter_c3flag = 2
		ENDIF
	ENDIF
ENDIF

//start first set of guys doing their actions 
IF crash_c3flag = 10
	
	IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2167.955 -2253.054 2175.023 -2245.327 -11.5 FALSE
		crash_c3flag = 11
	ENDIF 

	IF DOES_CHAR_EXIST guard1_c3
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR guard1_c3 scplayer
			crash_c3flag = 11
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST guard2_c3
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR guard2_c3 scplayer
			crash_c3flag = 11
		ENDIF
	ENDIF

	IF DOES_CHAR_EXIST guard3_c3
		IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR guard3_c3 scplayer
			crash_c3flag = 11
		ENDIF
	ENDIF

ENDIF

IF crash_c3flag = 11

	IF helptext_c3flag = 0
		IF TIMERA > 0
			IF IS_CHAR_DEAD guard1_c3
				IF IS_CHAR_DEAD guard3_c3
					IF IS_CHAR_DEAD guard5_c3
						PRINT_HELP CSH3_10  
						TIMERA = 0
						helptext_c3flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF helptext_c3flag = 1
		IF TIMERA > 8000
			PRINT_HELP CSH3_11
			TIMERA = 0
			helptext_c3flag = 2
		ENDIF
	ENDIF
	IF helptext_c3flag = 2
		IF TIMERA > 8000
			PRINT_HELP CSH3_12
			TIMERA = 0
			helptext_c3flag = 3
		ENDIF
	ENDIF
	IF helptext_c3flag = 3
		IF TIMERA > 8000
			PRINT_HELP CSH3_52  
			TIMERA = 0
			helptext_c3flag = 4
		ENDIF
	ENDIF
	IF helptext_c3flag = 4
		IF TIMERA > 8000
			PRINT_HELP  CSH3_51 //Rolling is useful in gun fights as it allows you to dodge gunfire and surprise enemies. You can roll left or right.
			helptext_c3flag = 5
		ENDIF
	ENDIF

	IF firstarea_c3flag = 0

		IF guard2_c3flag = 0
			IF NOT IS_CHAR_DEAD guard2_c3
				enemy_c3 = guard2_c3
				GOSUB stayshoot_c3label
				guard2_c3flag = 1
			ENDIF
		ENDIF

//		IF guard4_c3flag = 0
//			IF NOT IS_CHAR_DEAD guard4_c3
//				enemy_c3 = guard4_c3
//				enemyx_c3 = 2149.83
//				enemyy_c3 = -2253.37
//				enemyz_c3 = 20.4
//				GOSUB runstay_c3label
//				guard4_c3flag = 1
//			ENDIF
//		ENDIF

		IF guard5_c3flag = 0
			IF NOT IS_CHAR_DEAD guard5_c3
				enemy_c3 = guard5_c3
				GOSUB peekleft_c3label
				guard5_c3flag = 1
			ENDIF
		ENDIF
		IF guard5_c3flag = 1
			IF NOT IS_CHAR_DEAD guard5_c3
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR guard5_c3 scplayer
				OR LOCATE_CHAR_ANY_MEANS_CHAR_2D guard5_c3 scplayer 3.0 3.0 FALSE	
					TASK_KILL_CHAR_ON_FOOT guard5_c3 scplayer
					guard5_c3flag = 2
				ENDIF
			ELSE
				MARK_CHAR_AS_NO_LONGER_NEEDED guard5_c3
				guard5_c3flag = 2
			ENDIF
		ENDIF

		IF guard6_c3flag = 0
			IF NOT IS_CHAR_DEAD guard6_c3
				IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2161.927 -2243.2519 2168.71 -2237.79 10.0 FALSE
				OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR guard6_c3 scplayer 
					enemy_c3 = guard6_c3
					GOSUB rolloutr_c3label
					guard6_c3flag = 1
				ENDIF
			ENDIF
		ENDIF

	ENDIF
	
	//create other guys
	IF secondarea_c3flag = 0
		IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2160.6587 -2244.4089 2168.71 -2237.79 12.0 FALSE
			//mark outside as no longer needed
			//MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT
			MARK_MODEL_AS_NO_LONGER_NEEDED MULE
			MARK_MODEL_AS_NO_LONGER_NEEDED SEC_KEYPAD
			MARK_MODEL_AS_NO_LONGER_NEEDED KMB_RAMP
			//MARK_MODEL_AS_NO_LONGER_NEEDED BANSHEE
			//MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
			MARK_CAR_AS_NO_LONGER_NEEDED forklift1_c3
			MARK_CAR_AS_NO_LONGER_NEEDED forklift2_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED forkworker1_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED forkworker2_c3
			MARK_OBJECT_AS_NO_LONGER_NEEDED barrel1_c3
			MARK_OBJECT_AS_NO_LONGER_NEEDED barrel2_c3
			MARK_OBJECT_AS_NO_LONGER_NEEDED barrel3_c3
			MARK_OBJECT_AS_NO_LONGER_NEEDED barrel4_c3
			MARK_OBJECT_AS_NO_LONGER_NEEDED barrel5_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED forkworker1_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED forkworker2_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED workerdoor_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED workerchat_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED workerdirector_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED talkworker1_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED talkworker2_c3
			MARK_CAR_AS_NO_LONGER_NEEDED parkedtruck1_c3
			MARK_CAR_AS_NO_LONGER_NEEDED parkedtruck2_c3

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2149.249 -2243.9577 12.6 guard7_c3	//peeking around corner
			SET_CHAR_HEADING guard7_c3 312.4132
			SET_CHAR_DECISION_MAKER guard7_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard7_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard7_c3 20
			SET_CHAR_HEALTH guard7_c3 50

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2156.04 -2250.73 12.59 guard8_c3		//run to coords shooting 2152.0188 -2246.949 12.595 
			SET_CHAR_HEADING guard8_c3 46.763
			SET_CHAR_DECISION_MAKER guard8_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard8_c3 WEAPONTYPE_PISTOL 99999
			SET_CHAR_ACCURACY guard8_c3 20

			//barrels
			CREATE_OBJECT BARREL4 2149.725 -2249.85 12.35 barrel1_c3
			CREATE_OBJECT BARREL4 2162.399 -2259.433 12.35 barrel2_c3
			CREATE_OBJECT BARREL4 2152.317 -2270.389 12.35 barrel3_c3
			CREATE_OBJECT BARREL4 2139.31 -2270.019 14.70 barrel4_c3
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel1_c3 TRUE
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel2_c3 TRUE
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel3_c3 TRUE
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel4_c3 TRUE

			//box
			CREATE_OBJECT IMY_BBOX 2140.91 -2260.2 18.1 box_c3
			SET_OBJECT_HEADING box_c3 317.068
			SET_OBJECT_HEALTH box_c3 1000
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER box_c3 TRUE

			secondarea_c3flag = 1
		ENDIF
	ENDIF

	IF secondarea_c3flag = 1

		IF guard7_c3flag = 0
			IF NOT IS_CHAR_DEAD guard7_c3
				enemy_c3 = guard7_c3
				GOSUB peekleft_c3label
				guard7_c3flag = 1
			ENDIF
		ENDIF
		IF guard7_c3flag = 1
			IF NOT IS_CHAR_DEAD guard7_c3
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR guard7_c3 scplayer
				OR LOCATE_CHAR_ANY_MEANS_CHAR_2D guard7_c3 scplayer 3.0 3.0 FALSE	
					TASK_KILL_CHAR_ON_FOOT guard7_c3 scplayer
					guard7_c3flag = 2
				ENDIF
			ELSE
				MARK_CHAR_AS_NO_LONGER_NEEDED guard7_c3
				guard7_c3flag = 2
			ENDIF
		ENDIF

		IF guard8_c3flag = 0
			IF NOT IS_CHAR_DEAD guard8_c3
				enemy_c3 = guard8_c3
				enemyx_c3 = 2152.0188
				enemyy_c3 = -2246.949
				enemyz_c3 = 12.595
				GOSUB runshootstay_c3label
				guard8_c3flag = 1
			ENDIF
		ENDIF

	ENDIF

	IF thirdarea_c3flag = 0
		IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2157.47 -2246.72 2152.737 -2251.8567 -10.0 FALSE

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2164.943 -2259.275 12.594 guard10_c3
			SET_CHAR_HEADING guard10_c3 46.776
			SET_CHAR_DECISION_MAKER guard10_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard10_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard10_c3 30
			SET_CHAR_SHOOT_RATE guard10_c3 20

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2159.296 -2256.66 12.59 guard11_c3
			SET_CHAR_HEADING guard11_c3 46.776
			SET_CHAR_DECISION_MAKER guard11_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard11_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard11_c3 20
			SET_CHAR_ALLOWED_TO_DUCK guard11_c3 FALSE
			SET_CHAR_SHOOT_RATE guard11_c3 20
						
			//for fourth area

//			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2157.615 -2272.369 12.59 guard9_c3		//top railing -> 2145.00 -2248.41 19.693
//			SET_CHAR_HEADING guard9_c3 51.16
//			SET_CHAR_DECISION_MAKER guard9_c3 crash3_DM
//			GIVE_WEAPON_TO_CHAR guard9_c3 WEAPONTYPE_PISTOL 99999
//			SET_CHAR_ACCURACY guard9_c3 20
//			SET_CHAR_HEALTH guard9_c3 20

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2137.578 -2262.216 12.59 guard12_c3 //runs to 2148.945 -2273.314 13.595
			SET_CHAR_HEADING guard12_c3 211.9
			SET_CHAR_DECISION_MAKER guard12_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard12_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard12_c3 10
		
			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2138.008 -2253.369 12.59 guard13_c3 //runs to shooting 2143.387 -2267.5 13.595
			SET_CHAR_HEADING guard13_c3 139.993
			SET_CHAR_DECISION_MAKER guard13_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard13_c3 WEAPONTYPE_PISTOL 99999
			SET_CHAR_ACCURACY guard13_c3 10

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2151.545 -2279.607 13.82 guard14_c3	//crouch walk to 2140.375 -2269.66 13.59
			SET_CHAR_HEADING guard14_c3 46.63
			SET_CHAR_DECISION_MAKER guard14_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard14_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard14_c3 10

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2140.6948 -2259.8767 12.5547 guard15_c3	//boxguy1 hit
			SET_CHAR_HEADING guard15_c3 259.7427
			SET_CHAR_DECISION_MAKER guard15_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard15_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard15_c3 10
			SET_CHAR_SHOOT_RATE guard15_c3 20

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2141.7634 -2261.2769 12.5547 guard16_c3	//boxguy2 jump
			SET_CHAR_HEADING guard16_c3 259.742
			SET_CHAR_DECISION_MAKER guard16_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard16_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard16_c3 10
			SET_CHAR_SHOOT_RATE guard16_c3 20

			CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2131.561 -2279.424 19.712 flat1_c3	//-> 2134.504 -2276.708 19.712 -> 2130.845 -2272.55 19.71
			SET_CHAR_HEADING flat1_c3 313.176
			SET_CHAR_DECISION_MAKER flat1_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR flat1_c3 WEAPONTYPE_PISTOL 99999
			SET_CHAR_ACCURACY flat1_c3 10
			SET_CHAR_SHOOT_RATE flat1_c3 20

			CREATE_CHAR PEDTYPE_MISSION1 BALLAS1 2128.952 -2282.271 19.712 flat2_c3	//-> 2134.788 -2275.941 19.712 -> 2139.879 -2270.926 18.0
			SET_CHAR_HEADING flat2_c3 313.176
			SET_CHAR_DECISION_MAKER flat2_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR flat2_c3 WEAPONTYPE_PISTOL 99999
			SET_CHAR_ACCURACY flat2_c3 10
			SET_CHAR_SHOOT_RATE flat2_c3 20

			thirdarea_c3flag = 1
		ENDIF
	ENDIF

//	IF helptext_c3flag = 3
//		IF LOCATE_CHAR_ON_FOOT_2D scplayer 2155.18 -2263.00 2.5 2.5 FALSE
//			PRINT_HELP CSH3_17 
//			helptext_c3flag = 4
//		ENDIF
//	ENDIF



	IF thirdarea_c3flag = 1

		IF guard10_c3flag = 0
			IF NOT IS_CHAR_DEAD guard10_c3
				enemy_c3 = guard10_c3
				GOSUB stayshoot_c3label
				guard10_c3flag = 1
			ENDIF
		ENDIF

		IF guard11_c3flag = 0
			IF NOT IS_CHAR_DEAD guard11_c3
				enemy_c3 = guard11_c3
				GOSUB stayshoot_c3label
				guard11_c3flag = 1
			ENDIF
		ENDIF
	

	ENDIF

	IF fourtharea_c3flag = 0
		IF IS_CHAR_IN_ANGLED_AREA_2D scplayer 2160.728 -2254.586 2152.4 -2263.3 10.0 FALSE

			IF guard12_c3flag = 0
				IF DOES_OBJECT_EXIST box_c3
				ENDIF
				IF NOT IS_CHAR_DEAD guard12_c3
					enemy_c3 = guard12_c3
					enemyx_c3 = 2148.945
					enemyy_c3 = -2273.314
					enemyz_c3 = 13.595
					SET_CHAR_ALLOWED_TO_DUCK guard12_c3 FALSE
					GOSUB runstay_c3label
					guard12_c3flag = 1
				ENDIF
			ENDIF

			IF guard13_c3flag = 0
				IF NOT IS_CHAR_DEAD guard13_c3
					enemy_c3 = guard13_c3
					enemyx_c3 = 2133.613
					enemyy_c3 = -2258.067
					enemyz_c3 = 12.59
					enemyx2_c3 = 2143.387
					enemyy2_c3 = -2267.5
					enemyz2_c3 = 13.595
					TASK_TOGGLE_DUCK guard13_c3 TRUE
					GOSUB run2stay_c3label
					guard13_c3flag = 1
				ENDIF
			ENDIF
		
			IF guard14_c3flag = 0
				IF NOT IS_CHAR_DEAD guard14_c3
					enemy_c3 = guard14_c3
					enemyx_c3 = 2140.375
					enemyy_c3 = -2269.66
					enemyz_c3 = 13.59
					TASK_TOGGLE_DUCK guard14_c3 TRUE
					GOSUB runstay_c3label
					guard14_c3flag = 1
				ENDIF
			ENDIF

			IF flat1_c3flag = 0
				IF NOT IS_CHAR_DEAD flat1_c3
					enemy_c3 = flat1_c3
					enemyx_c3 = 2134.504
					enemyy_c3 = -2276.708
					enemyz_c3 = 19.712
					enemyx2_c3 = 2130.845
					enemyy2_c3 = -2272.55
					enemyz2_c3 = 19.71
					SET_CHAR_ALLOWED_TO_DUCK flat1_c3 FALSE
					GOSUB run2stay_c3label
					flat1_c3flag = 1
				ENDIF
			ENDIF

			IF flat2_c3flag = 0
				IF NOT IS_CHAR_DEAD flat2_c3
					enemy_c3 = flat2_c3
					enemyx_c3 = 2134.788
					enemyy_c3 = -2275.941
					enemyz_c3 = 19.712
					enemyx2_c3 = 2139.879
					enemyy2_c3 = -2270.926
					enemyz2_c3 = 18.0
					SET_CHAR_ALLOWED_TO_DUCK flat2_c3 FALSE
					GOSUB run2stay_c3label
					flat2_c3flag = 1
				ENDIF
			ENDIF

			//box guys
			IF guard15_c3flag = 0
				IF NOT IS_CHAR_DEAD guard15_c3
					enemy_c3 = guard15_c3
					GOSUB stayshoot_c3label
					guard15_c3flag = 1
				ENDIF
			ENDIF

			IF guard16_c3flag = 0
				IF NOT IS_CHAR_DEAD guard16_c3
					enemy_c3 = guard16_c3
					GOSUB stayshoot_c3label
					guard16_c3flag = 1
				ENDIF
			ENDIF

				
			fourtharea_c3flag = 1
		ENDIF
	ENDIF


	//guy who runs
//	IF guard9_c3flag = 1
//		IF NOT IS_CHAR_DEAD guard9_c3
//			IF LOCATE_CHAR_ON_FOOT_2D guard9_c3 2149.02	-2262.492 2.0 2.0 FALSE
//				TASK_DIE guard9_c3
//				guard9_c3flag = 2
//			ENDIF
//		ENDIF
//	ENDIF

	//flat boss
	IF tolotov_c3flag = 0
		IF IS_CHAR_IN_ANGLED_AREA_3D scplayer 2136.48 -2266.78 28.0 2145.55 -2275.04 18.0 -20.0 FALSE

			MARK_MODEL_AS_NO_LONGER_NEEDED imcmptrkdrl_LAS	
			MARK_MODEL_AS_NO_LONGER_NEEDED imcmptrkdrr_LAS
			MARK_MODEL_AS_NO_LONGER_NEEDED imcompmovedr1_las
			
			//create tolotovs guards and cars and bikes

			PRINT_NOW CSH3_53 7000 1 //~s~Take out the ~r~Russian gang leader~s~ inside the office supplying the Ballas with arms!


			IF NOT IS_CHAR_DEAD russianboss1_c3
				enemy_c3 = russianboss1_c3
				GOSUB stayshoot_c3label
			ENDIF

			IF NOT IS_CHAR_DEAD russianboss2_c3
				TASK_KILL_CHAR_ON_FOOT russianboss2_c3 scplayer			
			ENDIF

			tolotov_c3flag = 1
		ENDIF
	ENDIF

	IF missionpassed_c3flag = 1
		IF IS_CHAR_DEAD tolotov_c3
//			IF IS_CHAR_DEAD russianboss1_c3
//				IF IS_CHAR_DEAD russianboss2_c3
//					TIMERB = 0
					missionpassed_c3flag = 2
				ENDIF
//			ENDIF
//		ENDIF
	ENDIF

	IF missionpassed_c3flag = 2
//		IF TIMERB > 2000
			GOTO mission_drugs3_passed
//		ENDIF
	ENDIF

	IF IS_CHAR_DEAD tolotov_c3
		IF DOES_BLIP_EXIST tolotov_c3blip
			REMOVE_BLIP tolotov_c3blip
		ENDIF
	ENDIF
//	IF IS_CHAR_DEAD russianboss1_c3
//		IF DOES_BLIP_EXIST russianboss1_c3blip
//			REMOVE_BLIP russianboss1_c3blip
//		ENDIF
//	ENDIF
//	IF IS_CHAR_DEAD russianboss2_c3
//		IF DOES_BLIP_EXIST russianboss2_c3blip
//			REMOVE_BLIP russianboss2_c3blip
//		ENDIF
//	ENDIF

	/////////////////////////////////////////////////////////box drop
	IF box_c3flag = 0
		IF DOES_OBJECT_EXIST box_c3
			GET_OBJECT_HEALTH box_c3 box_c3health
				IF box_C3health < 1000
					SET_OBJECT_PROOFS box_c3 TRUE TRUE TRUE TRUE TRUE
					IF NOT IS_CHAR_DEAD guard16_c3
						SET_CHAR_HEADING guard16_c3 200.025
						TASK_PLAY_ANIM_NON_INTERRUPTABLE guard16_c3 crush_jump DODGE 1000.0 FALSE TRUE TRUE TRUE -1
					ENDIF
					TIMERB = 0
					box_c3flag = 1
				ENDIF
		ENDIF
	ENDIF

	IF box_c3flag = 1
		IF TIMERB > 1500
			IF DOES_OBJECT_EXIST box_c3
				SET_OBJECT_COORDINATES box_c3 2140.91 -2260.2 18.1
				SET_OBJECT_DYNAMIC box_c3 TRUE
				SET_OBJECT_COLLISION box_c3 TRUE
				ADD_TO_OBJECT_VELOCITY box_c3 0.0 0.0 -2.0
				IF NOT IS_CHAR_DEAD guard15_c3
					SET_CHAR_COLLISION guard15_c3 FALSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE guard15_c3 crushed DODGE 1000.0 FALSE TRUE TRUE TRUE -1
				ENDIF
				TIMERB = 0
				box_c3flag = 2
			ENDIF
		ENDIF
	ENDIF

	IF box_c3flag = 2
		IF TIMERB > 600
			IF NOT IS_CHAR_DEAD guard15_c3
				SET_CHAR_NEVER_TARGETTED guard15_c3 TRUE
				REMOVE_CHAR_ELEGANTLY guard15_c3
			ENDIF
			IF NOT IS_CHAR_DEAD guard16_c3
				SET_CHAR_NEVER_TARGETTED guard16_c3 TRUE
			ENDIF
			box_c3flag = 3
		ENDIF
	ENDIF
	////////////////////////////////////////////////////////////

	//marking char's as no longer needed

	IF guard1_c3flag = 1
		IF IS_CHAR_DEAD guard1_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard1_c3
			guard1_c3flag = 2
		ENDIF
	ENDIF
	IF guard2_c3flag = 1
		IF IS_CHAR_DEAD guard2_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard2_c3
			guard2_c3flag = 2
		ENDIF
	ENDIF
	IF guard3_c3flag = 1
		IF IS_CHAR_DEAD guard3_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard3_c3
			guard3_c3flag = 2
		ENDIF
	ENDIF
//	IF guard4_c3flag = 1
//		IF IS_CHAR_DEAD guard4_c3
//			MARK_CHAR_AS_NO_LONGER_NEEDED guard4_c3
//			guard4_c3flag = 2
//		ENDIF
//	ENDIF
	IF guard5_c3flag > 0
		IF guard5_c3flag < 3
			IF IS_CHAR_DEAD guard5_c3
				MARK_CHAR_AS_NO_LONGER_NEEDED guard5_c3
				guard5_c3flag = 2
			ENDIF
		ENDIF
	ENDIF
	IF guard6_c3flag = 1
		IF IS_CHAR_DEAD guard6_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard6_c3
			guard6_c3flag = 2
		ENDIF
	ENDIF
	IF guard7_c3flag > 0
		IF guard7_c3flag < 3
			IF IS_CHAR_DEAD guard7_c3
				MARK_CHAR_AS_NO_LONGER_NEEDED guard7_c3
				guard7_c3flag = 3
			ENDIF
		ENDIF
	ENDIF
	IF guard8_c3flag = 1
		IF IS_CHAR_DEAD guard8_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard8_c3
			guard8_c3flag = 2
		ENDIF
	ENDIF
//	IF guard9_c3flag > 0
//		IF guard9_c3flag < 3
//			IF IS_CHAR_DEAD guard9_c3
//				MARK_CHAR_AS_NO_LONGER_NEEDED guard9_c3
//				guard9_c3flag = 3
//			ENDIF
//		ENDIF
//	ENDIF
	IF guard10_c3flag = 1
		IF IS_CHAR_DEAD guard10_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard10_c3
			guard10_c3flag = 2
		ENDIF
	ENDIF
	IF guard11_c3flag = 1
		IF IS_CHAR_DEAD guard11_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard11_c3
			guard11_c3flag = 2
		ENDIF
	ENDIF
	IF guard12_c3flag = 1
		IF IS_CHAR_DEAD guard12_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard12_c3
			guard12_c3flag = 2
		ENDIF
	ENDIF
	IF guard13_c3flag = 1
		IF IS_CHAR_DEAD guard13_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard13_c3
			guard13_c3flag = 2
		ENDIF
	ENDIF
	IF guard14_c3flag = 1
		IF IS_CHAR_DEAD guard14_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard14_c3
			guard14_c3flag = 2
		ENDIF
	ENDIF

	IF flat1_c3flag = 1
		IF IS_CHAR_DEAD flat1_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED flat1_c3
			flat1_c3flag = 2
		ENDIF
	ENDIF
	IF flat2_c3flag = 1
		IF IS_CHAR_DEAD flat2_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED flat2_c3
			flat2_c3flag = 2
		ENDIF
	ENDIF


	//car chase bit
	IF tolotov_c3flag = 1
		IF IS_CHAR_IN_ANGLED_AREA_3D scplayer 2125.14 -2270.97 27.0 2121.09 -2275.33 19.0 14.0 FALSE

			MARK_CHAR_AS_NO_LONGER_NEEDED guard1_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard2_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard3_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard4_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard5_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard6_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard7_c3
			MARK_CHAR_AS_NO_LONGER_NEEDED guard8_c3
			REMOVE_CHAR_ELEGANTLY guard1_c3
			REMOVE_CHAR_ELEGANTLY guard2_c3
			REMOVE_CHAR_ELEGANTLY guard3_c3
			REMOVE_CHAR_ELEGANTLY guard4_c3
			REMOVE_CHAR_ELEGANTLY guard5_c3
			REMOVE_CHAR_ELEGANTLY guard6_c3
			REMOVE_CHAR_ELEGANTLY guard7_c3
			REMOVE_CHAR_ELEGANTLY guard8_c3
			DELETE_CAR chasecar1_c3
			DELETE_CAR chasecar2_c3
			DELETE_CAR forklift1_c3
			CLEAR_AREA 2185.53 -2271.95 50.0 50.0 FALSE
			MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1

			CREATE_OBJECT BARREL4 2170.366 -2337.439 12.54 barrel6_c3
			SET_OBJECT_ONLY_DAMAGED_BY_PLAYER barrel6_c3 TRUE

			CREATE_CAR FORKLIFT 2122.0920 -2307.1411 12.5693 forklift1_c3
			SET_CAR_HEADING forklift1_c3 265.9861
			SET_CAN_BURST_CAR_TYRES forklift1_c3 FALSE

			CREATE_CAR BANSHEE 2190.07 -2269.8 12.5693 chasecar1_c3
			SET_CAR_HEADING chasecar1_c3 308.99
			SET_CAR_HEALTH chasecar1_c3 1000

			CREATE_CAR PCJ600 2175.75 -2280.57 12.5693 chasecar2_c3
			SET_CAR_HEADING chasecar2_c3 315.41
			SET_CAN_BURST_CAR_TYRES chasecar2_c3 FALSE

			CREATE_PICKUP_WITH_AMMO MP5LNG PICKUP_ONCE 250 2175.614 -2282.959 13.54 weapon_c3 //mp5

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2132.698 -2303.37 13.699 guard1_c3 //duck
			SET_CHAR_HEADING guard1_c3 826.228
			SET_CHAR_DECISION_MAKER guard1_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard1_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard1_c3 30
			enemy_c3 = guard1_c3
			GOSUB stayshoot_c3label

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2138.0598 -2308.614 19.7000 guard2_c3 //no duck mech
			SET_CHAR_HEADING guard2_c3 60.478
			SET_CHAR_DECISION_MAKER guard2_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard2_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ACCURACY guard2_c3 30
			enemy_c3 = guard2_c3
			GOSUB stayshootnoduck_c3label

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2144.0068 -2295.1943 19.6760 guard3_c3 //no duck mech
			SET_CHAR_HEADING guard3_c3 119.8487
			SET_CHAR_DECISION_MAKER guard3_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard3_c3 WEAPONTYPE_PISTOL 99999
			SET_CHAR_ALLOWED_TO_DUCK guard3_c3 FALSE
			SET_CHAR_HEALTH guard3_c3 50
			SET_CHAR_ACCURACY guard3_c3 30
			enemy_c3 = guard3_c3
			GOSUB stayshootnoduck_c3label

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2131.56 -2313.6 12.5719 guard4_c3 //duck mech
			SET_CHAR_HEADING guard4_c3 265.9861
			SET_CHAR_DECISION_MAKER guard4_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard4_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_ALLOWED_TO_DUCK guard4_c3 FALSE
			SET_CHAR_ACCURACY guard4_c3 30
			enemy_c3 = guard4_c3
			GOSUB stayshoot_c3label

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2183.367 -2293.234 12.5 guard6_c3 //stay no duck
			SET_CHAR_HEADING guard6_c3 148.671
			SET_CHAR_DECISION_MAKER guard6_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard6_c3 WEAPONTYPE_MP5 99999
			TASK_KILL_CHAR_ON_FOOT guard6_c3 scplayer
			SET_CHAR_HEALTH guard6_c3 50
			enemy_c3 = guard6_c3
			GOSUB stayshootnoduck_c3label

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2165.737 -2324.289 17.515 guard7_c3	//duck
			SET_CHAR_HEADING guard7_c3 224.6632
			SET_CHAR_DECISION_MAKER guard7_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard7_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_HEALTH guard7_c3 70
			SET_CHAR_ACCURACY guard7_c3 30
			enemy_c3 = guard7_c3
			GOSUB stayshootnoduck_c3label

			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2170.07 -2334.498 12.547 guard8_c3 //stand
			SET_CHAR_HEADING guard8_c3 89.2721
			SET_CHAR_DECISION_MAKER guard8_c3 crash3_DM
			GIVE_WEAPON_TO_CHAR guard8_c3 WEAPONTYPE_MP5 99999
			SET_CHAR_HEALTH guard8_c3 50
			enemy_c3 = guard8_c3
			GOSUB stayshootnoduck_c3label

//			CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2174.9712 -2273.7190 12.5469 guard9_c3 //stand
//			SET_CHAR_HEADING guard9_c3 89.2721
//			SET_CHAR_DECISION_MAKER guard9_c3 crash3_DM
//			GIVE_WEAPON_TO_CHAR guard9_c3 WEAPONTYPE_MP5 99999
//			SET_CHAR_ACCURACY guard9_c3 30
//			enemy_c3 = guard9_c3
//			GOSUB stayshootnoduck_c3label
//			TIMERA = 0

			IF DOES_OBJECT_EXIST backdoor_c3
				SET_OBJECT_HEADING backdoor_c3 90.0
			ELSE 
				DELETE_OBJECT backdoor_c3
			ENDIF
			
			IF NOT IS_CHAR_DEAD	tolotov_c3
				TASK_PLAY_ANIM tolotov_c3 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE 2000
			ENDIF

			tolotov_c3flag = 2
		ENDIF
	ENDIF

	//get in car
	IF tolotov_c3flag = 2
		IF NOT IS_CHAR_DEAD tolotov_c3
			IF TIMERA > 750
			OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR tolotov_c3 scplayer
			OR IS_PLAYER_TARGETTING_CHAR PLAYER1 tolotov_c3

				CREATE_CHAR PEDTYPE_MISSION1 WMYMECH 2135.46 -2296.64 12.9 guard5_c3 //free
				SET_CHAR_HEADING guard5_c3 337.5533
				SET_CHAR_DECISION_MAKER guard5_c3 crash3_DM
				GIVE_WEAPON_TO_CHAR guard5_c3 WEAPONTYPE_PISTOL 99999
				TASK_KILL_CHAR_ON_FOOT guard5_c3 scplayer
				enemy_c3 = guard5_c3
				GOSUB stayshootnoduck_c3label
	
				OPEN_SEQUENCE_TASK tolotov_c3seq
				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2128.43 -2292.01 13.75 PEDMOVE_RUN 350000
				TASK_FOLLOW_PATH_NODES_TO_COORD -1 2185.5 -2274.8 13.75 PEDMOVE_SPRINT 350000
				CLOSE_SEQUENCE_TASK tolotov_c3seq
				PERFORM_SEQUENCE_TASK tolotov_c3 tolotov_c3seq
				CLEAR_SEQUENCE_TASK tolotov_c3seq

				tolotov_c3flag = 3
			ENDIF
		ENDIF
	ENDIF

	IF tolotov_c3flag = 3
		IF NOT IS_CHAR_DEAD tolotov_c3
			IF LOCATE_CHAR_ANY_MEANS_2D tolotov_c3 2185.5 -2274.8 10.0 10.0 FALSE
				MARK_CHAR_AS_NO_LONGER_NEEDED guard4_c3
				tolotov_c3flag = 4
			ENDIF
		ENDIF
	ENDIF


	//imran
	IF NOT IS_CHAR_DEAD tolotov_c3

		IF tolotov_c3flag = 4

			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer tolotov_c3 200.0 200.0 FALSE
				IF NOT IS_CHAR_ON_SCREEN tolotov_c3
					PRINT_NOW CSH3_55 5000 1 //~r~He got away!
					GOTO mission_drugs3_failed	
				ENDIF
			ENDIF

			IF gettingincar_c3flag < 2
	
				//check which car player is using
				IF gettingincar_c3flag = 1
					IF NOT IS_CAR_DEAD chasecar1_c3
						GET_CAR_CHAR_IS_USING scplayer car
						IF car = chasecar1_c3
							GOSUB tolotovflee_c3label
							gettingincar_c3flag = 2							
						ENDIF
					ENDIF
				ENDIF
	
				IF gettingincar_c3flag = 0
					IF NOT IS_CAR_DEAD chasecar1_c3
						IF LOCATE_CAR_2D chasecar1_c3 2190.07 -2269.8 6.0 6.0 FALSE
							IF NOT IS_CHAR_IN_CAR scplayer chasecar1_c3
								IF playerincar_c3flag = 0
									//get in car
									CLEAR_CHAR_TASKS tolotov_c3
									TASK_ENTER_CAR_AS_DRIVER tolotov_c3 chasecar1_c3 -2
									gettingincar_c3flag = 1
								ELSE
									GOSUB tolotovflee_c3label
									gettingincar_c3flag = 2
								ENDIF
							ELSE
								GOSUB tolotovflee_c3label
								gettingincar_c3flag = 2
							ENDIF
						ELSE
							GOSUB tolotovflee_c3label
							gettingincar_c3flag = 2
						ENDIF
					ELSE
						GOSUB tolotovflee_c3label
						gettingincar_c3flag = 2
					ENDIF
				ENDIF

				IF gettingincar_c3flag = 1
					IF playerincar_c3flag = 0
						IF NOT IS_CAR_DEAD chasecar1_c3
							IF IS_CHAR_IN_CAR tolotov_c3 chasecar1_c3
								IF LOCATE_CHAR_ANY_MEANS_CHAR_2D tolotov_c3 scplayer 60.0 60.0 FALSE

									PRINT_NOW CSH3_54 5000 1 //~s~He has got in a ~r~car ~s~chase him down and take him out!
									SET_CHAR_HEALTH tolotov_c3 100
									SET_CHAR_SUFFERS_CRITICAL_HITS tolotov_c3 TRUE
									CAR_GOTO_COORDINATES chasecar1_c3 2246.61 -2204.78 13.65
									SET_CAR_CRUISE_SPEED chasecar1_c3 20.0
									SET_CAR_DRIVING_STYLE chasecar1_c3 DRIVINGMODE_AVOIDCARS
									SWITCH_ROADS_OFF 2203.8 -2174.63 0.0 2279.2 -2234.63 18.21
									outofcar_c3flag = 1
									gettingincar_c3flag = 2

								ENDIF
							ENDIF
						ENDIF
					ELSE
						GOSUB tolotovflee_c3label
						gettingincar_c3flag = 2
					ENDIF
				ENDIF

				//if player gets in car
				IF NOT IS_CAR_DEAD chasecar1_c3
					IF playerincar_c3flag = 0
						IF IS_CHAR_IN_CAR scplayer chasecar1_c3
							GOSUB tolotovflee_c3label
							playerincar_c3flag = 1
						ENDIF
					ENDIF
					IF playerincar_c3flag = 1
						IF NOT IS_CHAR_IN_CAR scplayer chasecar1_c3
							playerincar_c3flag = 0
						ENDIF
					ENDIF
				ELSE
					GOSUB tolotovflee_c3label
					gettingincar_c3flag = 2
				ENDIF
			
			ENDIF

		ENDIF
	
		//if he is out of car
		IF outofcar_c3flag = 1
			IF NOT IS_CHAR_SITTING_IN_ANY_CAR tolotov_c3
				TASK_SMART_FLEE_CHAR tolotov_c3 scplayer 300.0 500000
				IF NOT IS_CAR_DEAD chasecar1_c3
					IF IS_PLAYBACK_GOING_ON_FOR_CAR chasecar1_c3
						STOP_PLAYBACK_RECORDED_CAR chasecar1_c3
					ENDIF
				ENDIF
				outofcar_c3flag = 2
			ENDIF
		ENDIF

		//switch into recordings
		IF gettingincar_c3flag = 2
			IF NOT IS_CAR_DEAD chasecar1_c3
				IF IS_CHAR_SITTING_IN_CAR tolotov_c3 chasecar1_c3
					IF LOCATE_CAR_2D chasecar1_c3 2246.61 -2204.78 10.0 10.0 FALSE
						MARK_CHAR_AS_NO_LONGER_NEEDED guard1_c3
						MARK_CHAR_AS_NO_LONGER_NEEDED guard2_c3
						MARK_CHAR_AS_NO_LONGER_NEEDED guard3_c3
						MARK_CHAR_AS_NO_LONGER_NEEDED guard4_c3
						MARK_CHAR_AS_NO_LONGER_NEEDED guard5_c3
						MARK_CHAR_AS_NO_LONGER_NEEDED guard6_c3
						MARK_CHAR_AS_NO_LONGER_NEEDED guard7_c3
						MARK_CHAR_AS_NO_LONGER_NEEDED guard8_c3
						MARK_CAR_AS_NO_LONGER_NEEDED forklift1_c3
						CAR_SET_IDLE chasecar1_c3
						START_PLAYBACK_RECORDED_CAR_USING_AI chasecar1_c3 342
						SET_PLAYBACK_SPEED chasecar1_c3 0.85
						SWITCH_ROADS_OFF 2203.8 -2174.63 0.0 2279.2 -2234.63 18.21
						speedbalance_c3flag = 1
						gettingincar_c3flag = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		//balance speed when in storm drains
		IF gettingincar_c3flag = 3
			IF NOT IS_CAR_DEAD chasecar1_c3
				IF IS_CHAR_SITTING_IN_CAR tolotov_c3 chasecar1_c3
	
					IF speedbalance_c3flag = 1
						IF IS_PLAYBACK_GOING_ON_FOR_CAR chasecar1_c3
							IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D tolotov_c3 scplayer 40.0 40.0 FALSE
								SET_PLAYBACK_SPEED chasecar1_c3 0.4
								speedbalance_c3flag = 2
							ENDIF
						ENDIF
					ENDIF
					IF speedbalance_c3flag = 2
						IF IS_PLAYBACK_GOING_ON_FOR_CAR chasecar1_c3
							IF LOCATE_CHAR_ANY_MEANS_CHAR_2D tolotov_c3 scplayer 40.0 40.0 FALSE
								SET_PLAYBACK_SPEED chasecar1_c3 0.85
								speedbalance_c3flag = 1
							ENDIF
						ENDIF
					ENDIF

				ENDIF
			ENDIF
		ENDIF


		//switch into recordings
		IF gettingincar_c3flag = 3
			IF NOT IS_CAR_DEAD chasecar1_c3
				IF IS_CHAR_SITTING_IN_CAR tolotov_c3 chasecar1_c3
					IF LOCATE_CAR_2D chasecar1_c3 1440.92 -1594.59 20.0 20.0 FALSE
						IF IS_PLAYBACK_GOING_ON_FOR_CAR chasecar1_c3
							STOP_PLAYBACK_RECORDED_CAR chasecar1_c3
							CAR_SET_IDLE chasecar1_c3
						ENDIF
						TASK_CAR_DRIVE_WANDER tolotov_c3 chasecar1_c3 25.0 DRIVINGMODE_AVOIDCARS
						gettingincar_c3flag = 4
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	//dialogue
	IF tolotov_c3flag > 1
		IF NOT IS_CHAR_DEAD tolotov_c3

			GOSUB process_audio_c3

			IF progressaudio_c3flag = 0
				IF handlingudio_c3flag = 0
					audio_label_c3 = SOUND_CRA3_DB	//Stop, we could do business!
					$input_text_c3 = CRA3_DB	//Stop, we could do business!
					GOSUB load_audio_c3
				ENDIF
			ENDIF
			IF progressaudio_c3flag = 1
				IF handlingudio_c3flag = 0
					audio_label_c3 = SOUND_CRA3_DC	//Fuck you, Americansky! I’m gone!
					$input_text_c3 = CRA3_DC	//Fuck you, Americansky! I’m gone!
					GOSUB load_audio_c3
				ENDIF
			ENDIF
			IF progressaudio_c3flag = 2
				IF handlingudio_c3flag = 0
					audio_label_c3 = SOUND_CRA3_DD	//Doing business in america is dangerous!
					$input_text_c3 = CRA3_DD	//Doing business in america is dangerous!
					GOSUB load_audio_c3
				ENDIF
			ENDIF

		ENDIF
	ENDIF

ENDIF



GOTO dockshootoutloop


/////////////////////////////////////	GOSUBS
load_audio_c3:
IF handlingudio_c3flag = 0
	LOAD_MISSION_AUDIO 1 audio_label_c3
	$text_label_c3 = $input_text_c3
	handlingudio_c3flag = 1
ENDIF
RETURN

process_audio_c3:
IF handlingudio_c3flag = 1
	IF HAS_MISSION_AUDIO_LOADED 1
		PRINT_NOW $text_label_c3 4000 1 //Dummy message"
		IF NOT IS_CHAR_DEAD tolotov_c3
			ATTACH_MISSION_AUDIO_TO_CHAR 1 tolotov_c3
		ENDIF
		PLAY_MISSION_AUDIO 1
		handlingudio_c3flag = 2
	ENDIF
ENDIF
IF handlingudio_c3flag = 2
	IF HAS_MISSION_AUDIO_FINISHED 1
		progressaudio_c3flag++
		CLEAR_MISSION_AUDIO 1
		CLEAR_PRINTS
		handlingudio_c3flag = 0
	ENDIF
ENDIF
RETURN

tolotovflee_c3label:
SET_CHAR_HEALTH tolotov_c3 100
SET_CHAR_MAX_HEALTH tolotov_c3 100
OPEN_SEQUENCE_TASK tolotovflee_c3seq
TASK_FOLLOW_PATH_NODES_TO_COORD -1 2191.422 -2183.429 13.55 PEDMOVE_RUN -2
TASK_SMART_FLEE_CHAR -1 scplayer 300.0 500000
CLOSE_SEQUENCE_TASK tolotovflee_c3seq
PERFORM_SEQUENCE_TASK tolotov_c3 tolotovflee_c3seq
CLEAR_SEQUENCE_TASK tolotovflee_c3seq
RETURN

runstay_c3label:
OPEN_SEQUENCE_TASK runstay_c3seq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_c3 enemyy_c3 enemyz_c3 PEDMOVE_RUN -1
//SET_CHAR_ALLOWED_TO_DUCK -1 FALSE
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK runstay_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 runstay_c3seq
CLEAR_SEQUENCE_TASK runstay_c3seq
RETURN

run2stay_c3label:
OPEN_SEQUENCE_TASK run2stay_c3seq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_c3 enemyy_c3 enemyz_c3 PEDMOVE_RUN -1
TASK_GO_STRAIGHT_TO_COORD -1 enemyx2_c3 enemyy2_c3 enemyz2_c3 PEDMOVE_RUN -1
//SET_CHAR_ALLOWED_TO_DUCK -1 FALSE
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK run2stay_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 run2stay_c3seq
CLEAR_SEQUENCE_TASK run2stay_c3seq
RETURN

runkill_c3label:
OPEN_SEQUENCE_TASK runkill_c3seq
TASK_GO_STRAIGHT_TO_COORD -1 enemyx_c3 enemyy_c3 enemyz_c3 PEDMOVE_RUN -1
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK runkill_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 runkill_c3seq
CLEAR_SEQUENCE_TASK runkill_c3seq
RETURN

stayshoot_c3label:
OPEN_SEQUENCE_TASK stayshoot_c3seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshoot_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 stayshoot_c3seq
CLEAR_SEQUENCE_TASK stayshoot_c3seq
RETURN

stayshootnoduck_c3label:
OPEN_SEQUENCE_TASK stayshootnoduck_c3seq
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK stayshootnoduck_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 stayshootnoduck_c3seq
CLEAR_SEQUENCE_TASK stayshootnoduck_c3seq
RETURN

runshootstay_c3label:
OPEN_SEQUENCE_TASK runshootstay_c3seq
TASK_GO_TO_COORD_WHILE_SHOOTING -1 enemyx_c3 enemyy_c3 enemyz_c3 PEDMOVE_RUN 0.5 1.0 scplayer
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK runshootstay_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 runshootstay_c3seq
CLEAR_SEQUENCE_TASK runshootstay_c3seq
RETURN


rolloutr_c3label:
OPEN_SEQUENCE_TASK rolloutr_c3seq
TASK_PLAY_ANIM -1 Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_TOGGLE_DUCK -1 TRUE
TASK_KILL_CHAR_ON_FOOT -1 scplayer
//TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK rolloutr_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 rolloutr_c3seq
CLEAR_SEQUENCE_TASK rolloutr_c3seq
RETURN



peekleft_c3label:
OPEN_SEQUENCE_TASK peekleft_c3seq
TASK_PLAY_ANIM -1 swt_wllpk_L SWAT 8.0 FALSE FALSE FALSE FALSE -1
TASK_PLAY_ANIM -1 swt_wllshoot_in_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_STAY_IN_SAME_PLACE -1 TRUE
TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 1500
TASK_STAY_IN_SAME_PLACE -1 FALSE
TASK_PLAY_ANIM -1 swt_wllshoot_out_L SWAT 8.0 FALSE TRUE TRUE FALSE -1
TASK_PLAY_ANIM -1 swt_wllpk_L_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
SET_SEQUENCE_TO_REPEAT peekleft_c3seq 1
CLOSE_SEQUENCE_TASK peekleft_c3seq
PERFORM_SEQUENCE_TASK enemy_c3 peekleft_c3seq
CLEAR_SEQUENCE_TASK peekleft_c3seq
RETURN


// Mission drugs3 failed
mission_drugs3_failed:
PRINT_BIG M_FAIL 5000 1
RETURN


// mission drugs3 passed

mission_drugs3_passed:

PRINT_WITH_NUMBER_BIG M_PASSD 0 5000 1
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
//REGISTER_MISSION_PASSED XXXX
PLAYER_MADE_PROGRESS 1
REGISTER_MISSION_PASSED ( CRASH_3 )

flag_crash_mission_counter ++
REMOVE_BLIP crash_contact_blip
RETURN
		
// mission cleanup

mission_cleanup_drugs3:

IF NOT IS_CAR_DEAD car
	SET_CAR_PROOFS car FALSE FALSE FALSE FALSE FALSE
ENDIF

IF DOES_OBJECT_EXIST dockshutter_c3
	DELETE_OBJECT dockshutter_c3
ENDIF
IF DOES_OBJECT_EXIST leftdoor1_c3
	DELETE_OBJECT rightdoor1_c3
ENDIF
IF DOES_OBJECT_EXIST leftdoor2_c3
	DELETE_OBJECT rightdoor2_c3
ENDIF
IF DOES_OBJECT_EXIST leftdoor3_c3
	DELETE_OBJECT rightdoor3_c3
ENDIF
IF DOES_OBJECT_EXIST leftdoor4_c3
	DELETE_OBJECT rightdoor4_c3
ENDIF
IF DOES_OBJECT_EXIST dockshutter_c3
	DELETE_OBJECT dockshutter_c3
ENDIF
IF DOES_OBJECT_EXIST keypad_c3
	DELETE_OBJECT keypad_c3
ENDIF

SWITCH_ROADS_ON 2203.8 -2174.63 0.0 2279.2 -2234.63 18.21
//blips
REMOVE_BLIP warehouse_c3blip
//REMOVE_BLIP russianboss1_c3blip
//REMOVE_BLIP russianboss2_c3blip
REMOVE_BLIP tolotov_c3blip
//animations
REMOVE_ANIMATION SWAT
REMOVE_ANIMATION DODGE
//pickups
REMOVE_PICKUP armour1_c3
REMOVE_PICKUP armour2_c3
REMOVE_PICKUP health_c3
//object 
DELETE_OBJECT box_c3
//vehicles
MARK_CAR_AS_NO_LONGER_NEEDED parkedtruck1_c3
MARK_CAR_AS_NO_LONGER_NEEDED parkedtruck2_c3
MARK_CAR_AS_NO_LONGER_NEEDED forklift1_c3
MARK_CAR_AS_NO_LONGER_NEEDED forklift2_c3

REMOVE_PICKUP weapon_c3
REMOVE_CHAR_ELEGANTLY guard1_c3
REMOVE_CHAR_ELEGANTLY guard2_c3
REMOVE_CHAR_ELEGANTLY guard3_c3
REMOVE_CHAR_ELEGANTLY guard4_c3
REMOVE_CHAR_ELEGANTLY guard5_c3
REMOVE_CHAR_ELEGANTLY guard6_c3
REMOVE_CHAR_ELEGANTLY guard7_c3
REMOVE_CHAR_ELEGANTLY guard8_c3
REMOVE_CHAR_ELEGANTLY guard10_c3
REMOVE_CHAR_ELEGANTLY guard11_c3
REMOVE_CHAR_ELEGANTLY guard12_c3
REMOVE_CHAR_ELEGANTLY guard13_c3
REMOVE_CHAR_ELEGANTLY guard14_c3
REMOVE_CHAR_ELEGANTLY guard15_c3
REMOVE_CHAR_ELEGANTLY guard16_c3
//multipliers
SET_WANTED_MULTIPLIER 1.0
//models
MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED BARREL4
MARK_MODEL_AS_NO_LONGER_NEEDED FORKLIFT
MARK_MODEL_AS_NO_LONGER_NEEDED MULE
MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
MARK_MODEL_AS_NO_LONGER_NEEDED SEC_KEYPAD
MARK_MODEL_AS_NO_LONGER_NEEDED KMB_RAMP
MARK_MODEL_AS_NO_LONGER_NEEDED BANSHEE
MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
MARK_MODEL_AS_NO_LONGER_NEEDED imcmptrkdrl_LAS	
MARK_MODEL_AS_NO_LONGER_NEEDED imcmptrkdrr_LAS
MARK_MODEL_AS_NO_LONGER_NEEDED imcompmovedr1_las
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED IMY_BBOX
MARK_MODEL_AS_NO_LONGER_NEEDED PCJ600
UNLOAD_SPECIAL_CHARACTER 1
IF NOT IS_CAR_DEAD chasecar2_c3
	SET_CAN_BURST_CAR_TYRES chasecar2_c3 TRUE
ENDIF
IF NOT IS_CAR_DEAD forklift1_c3
	SET_CAN_BURST_CAR_TYRES forklift1_c3 TRUE
ENDIF
IF NOT IS_CAR_DEAD car
	SET_CAR_PROOFS car FALSE FALSE TRUE FALSE FALSE
ENDIF
GET_GAME_TIMER timer_mobile_start
SWITCH_EMERGENCY_SERVICES ON
flag_player_on_mission = 0
MISSION_HAS_FINISHED
RETURN


}		

