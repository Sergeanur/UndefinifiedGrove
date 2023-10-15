MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Intro 1 *******************************************
// *********************************** BMX Bandits *****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME intro1

// Mission start stuff

GOSUB mission_start_intro1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_intro1_failed
ENDIF

GOSUB mission_cleanup_intro1

MISSION_END

{ 
// Variables for mission

LVAR_INT bmx1 bmx2 bmx3 players_bmx big_smoke_blip sweet_blip ryder_blip smokes_car1 bmx_pals intro_blip2 intro_blip1 failed_mission intro1_chat_switch
LVAR_INT players_vehicle index_intro is_recording_started_intr2 drive_by_char1 drive_by_car drive_by_char2 ReturnStatus	intro_blip2_flag_on_bike
LVAR_INT everyone_finished test_car_blip car_shits_it freeze_the_bmx[3] freeze_the_gangcar dont_repeat_task intro1_cell_index_end get_in_counter_intro1
LVAR_INT bmx_gang[3] out_of_intro1_locate players_ride_int1 bmx_boys bmx_rival_gang drive_to_hub2 drive_to_hub split_up_cut	intro1_audio_is_playing
LVAR_INT bmx_bikes[3] bmx_gang_finished[3] drive_to_hub1 bmx_rider_on_bike1 bmx_rider_on_bike2 bmx_rider_on_bike3 intro1_index get_in_counter_intro1_2
LVAR_INT smokes_decision sweets_decision ryders_decision skip_funeral_cutscene switch_traffic_back_on intro1_audio_chat[14]	num_bikes_in_chase play_catch_up_audio_smoke
LVAR_INT intro1_cutscene_flag chars_decision drive_to_hub_smoke	set_up_smokes_audio flag_the_drive_by_car_is_dead killed_the_baller_cunts play_catch_up_audio
LVAR_FLOAT int1X int1Y int1Z  ClosestX ClosestY ClosestZ int1_driveX int1_driveY int1_driveZ player_bmxX player_bmxY player_bmxZ
VAR_TEXT_LABEL $intro1_chat[14]

intro1_chat_switch:

SWITCH intro1_chat_switch		   

	CONST_INT INTRO1_CHAT1 0
	CONST_INT INTRO1_CHAT2 1
	CONST_INT INTRO1_CHAT3 2
	CONST_INT INTRO1_CHAT4 3
	CONST_INT INTRO1_CHAT5 4
	CONST_INT INTRO1_CHAT6 5
	CONST_INT INTRO1_CHAT7 6

	CASE INTRO1_CHAT1
		$intro1_chat[0] = &INT1_AB	//You want to drive?
		$intro1_chat[1] = &INT1_AC	//Sure. That's dope.
		$intro1_chat[2] = &INT1_AR //Nice car, Smoke.
		$intro1_chat[3] = &INT1_AA	//You know me. It's not cut. Keep the value in it. Keep it real.

		intro1_audio_chat[0] = SOUND_INT1_AB //You want to drive?
		intro1_audio_chat[1] = SOUND_INT1_AC //Sure. That's dope.
		intro1_audio_chat[2] = SOUND_INT1_AR //Nice car, Smoke.
		intro1_audio_chat[3] = SOUND_INT1_AA //You know me. It's not cut. Keep the value in it. Keep it real.

		intro1_cell_index_end = 3

	BREAK

	CASE INTRO1_CHAT2
		$intro1_chat[0] = &INT1_AI	//Drive by. Incoming!
		$intro1_chat[1] = &INT1_AJ	//Aww man…
		$intro1_chat[2] = &INT1_AK	//W/e gotta get back to the hood. 
		$intro1_chat[3] = &INT1_AQ	//Follow my lead!

		intro1_audio_chat[0] = SOUND_INT1_AI	//Drive by. Incoming!
		intro1_audio_chat[1] = SOUND_INT1_AJ	//Aww man…
		intro1_audio_chat[2] = SOUND_INT1_AK	//W/e gotta get back to the hood. 
		intro1_audio_chat[3] = SOUND_INT1_AQ	//Follow my lead!

	   intro1_cell_index_end = 3

	BREAK

	CASE INTRO1_CHAT3
		
		$intro1_chat[0] = &INT1_GN	//I got with them motherfuckers though – showed them niggaz who’s gangsta. Ryder, nigga!
		$intro1_chat[1] = &INT1_GA //When you leaving, Carl?
		$intro1_chat[2] = &INT1_GB //I ain’t sure. Thought I might stay. 
		$intro1_chat[3] = &INT1_GC //Things are fucked up…
		$intro1_chat[4] = &INT1_GD //Last thing we need is your help.
		$intro1_chat[5] = &INT1_GE //I won’t let you down, I swear it.
		$intro1_chat[6] = &INT1_GH //We gonna call up some freaks. Chill the hell out. You want some?
		$intro1_chat[7] = &INT1_GI //I gotta lot going on. I’m tired. I’ll see you all later.
		$intro1_chat[8] = &INT1_GJ //Drop by later – we’re all hanging out.
		$intro1_chat[9] = &INT1_GL //And get yourself some colours, dude.
		$intro1_chat[10] = &INT1_GM //And a haircut, it’s an embarrassment to be seen with you!

		intro1_audio_chat[0] = SOUND_INT1_GN //I got with them motherfuckers though – showed them niggaz who’s gangsta. Ryder, nigga!
		intro1_audio_chat[1] = SOUND_INT1_GA //When you leaving, Carl?
		intro1_audio_chat[2] = SOUND_INT1_GB //I ain’t sure. Thought I might stay. 
		intro1_audio_chat[3] = SOUND_INT1_GC //Things are fucked up…
		intro1_audio_chat[4] = SOUND_INT1_GD //Last thing we need is your help.
		intro1_audio_chat[5] = SOUND_INT1_GE //I won’t let you down, I swear it.
		intro1_audio_chat[6] = SOUND_INT1_GH //We gonna call up some freaks. Chill the hell out. You want some?
		intro1_audio_chat[7] = SOUND_INT1_GI //I gotta lot going on. I’m tired. I’ll see you all later.
		intro1_audio_chat[8] = SOUND_INT1_GJ //Drop by later – we’re all hanging out.
		intro1_audio_chat[9] = SOUND_INT1_GL //And get yourself some colours, dude.
		intro1_audio_chat[10] = SOUND_INT1_GM //And a haircut, it’s an embarrassment to be seen with you!

		intro1_cell_index_end = 10
	BREAK

	CASE INTRO1_CHAT4
		$intro1_chat[0] = &INT1_CA	//Shit! Flats car is onto us – SPLIT UP!
		$intro1_chat[1] = &INT1_EI	//Keep up, motherfucker!

		intro1_audio_chat[0] = SOUND_INT1_CA //Shit! Flats car is onto us – SPLIT UP!
		intro1_audio_chat[1] = SOUND_INT1_EI //Keep up, motherfucker!	

		intro1_cell_index_end = 1
	BREAK
	 

	CASE INTRO1_CHAT5
		$intro1_chat[0] = &INT1_BA //Takes you back some, huh, CJ? Yeah! //SWEET
		$intro1_chat[1] = &INT1_BC //Things has changed round here! //CARL
		$intro1_chat[2] = &INT1_BB //CJ, watch your back ‘round here, man. //SWEET
		$intro1_chat[3] = &INT1_BD //How'd it get so bad? //CARL
		$intro1_chat[4] = &INT1_AM //I thought this was Families Turf?
		$intro1_chat[5] = &INT1_AN //Yeah, it’s Temple Drive Families – we don’t roll with them no more.

		intro1_audio_chat[0] = SOUND_INT1_BA //Takes you back some, huh, CJ? Yeah! //SWEET
		intro1_audio_chat[1] = SOUND_INT1_BC //Things has changed round here! //CARL
		intro1_audio_chat[2] = SOUND_INT1_BB //CJ, watch your back ‘round here, man. //SWEET
		intro1_audio_chat[3] = SOUND_INT1_BD //How'd it get so bad? //CARL
		intro1_audio_chat[4] = SOUND_INT1_AM //I thought this was Families Turf?
		intro1_audio_chat[5] = SOUND_INT1_AN //Yeah, it’s Temple Drive Families – we don’t roll with them no more.

		intro1_cell_index_end = 5
	BREAK

	CASE INTRO1_CHAT6
		$intro1_chat[0] = &INT1_BF //Straight back into the game, right dog?
		$intro1_chat[1] = &INT1_BG //Was it this bad before you left?
		$intro1_chat[2] = &INT1_BH //East Coast got you all thinned out, home.

		intro1_audio_chat[0] = SOUND_INT1_BF //Straight back into the game, right dog?
		intro1_audio_chat[1] = SOUND_INT1_BG //Was it this bad before you left?
		intro1_audio_chat[2] = SOUND_INT1_BH //East Coast got you all thinned out, home.

		intro1_cell_index_end = 2
	BREAK

	CASE INTRO1_CHAT7
		$intro1_chat[0] = &INT1_BI //You’re just a lialbility, CJ!
		$intro1_chat[1] = &INT1_BK //Why’d you bother coming back, CJ?

		intro1_audio_chat[0] = SOUND_INT1_BI //You’re just a lialbility, CJ!
		intro1_audio_chat[1] = SOUND_INT1_BK //Why’d you bother coming back, CJ?

		intro1_cell_index_end = 1
	BREAK

/*
SWEET [INT1_CA]	Shit! A Ballas car is onto us! Split up!
SMOKE [INT1_CB]	Damn, Ballas is chasing us down!
RYDER [INT1_CC]	BALLAS! EVERYBODY BREAK!

Player falls off ************************************************************

//GET_CHAR_HIGHEST_PRIORITY_EVENT scplayer ReturnEventType //FOR PLAYER GETTING KNOCKED OFF

//SWEET
[INT1_DA] Get up, CJ, pedal!
[INT1_DB] Move it, CJ, not far to the Grove!
[INT1_DC] You ok, CJ?

//SMOKE
[INT1_DD] Hey, hey, Carl’s down!
[INT1_DE] Oh man, that shit looked like it hurt, homie!
[INT1_DF] Get back on your bike, playa!

//RYDER
[INT1_DG] Ha ha! This fool took a fall!
[INT1_DH] He can’t even ride a bike!
[INT1_DI] CJ, you cycle like a crack ho, fool!


Player pulls ahead **********************************************************
//SWEET
[INT1_FA] Hey, look at CJ go!
[INT1_FB] Hey, CJ, wait for us, man!
[INT1_FC] CJ, you running like a bitch, man!

//SMOKE
[INT1_FD] Carl’s showing us how it’s really done.
[INT1_FE] Skinny fool’s flying ahead on his own.
[INT1_FF] CJ’s doing his thing top speed, dog!

//RYDER
[INT1_FG] Typical, CJ! Leaving the homies behind, huh?
[INT1_FH] Hey wait for the homies, fool!
[INT1_FI] Figures, moving fast as a motherfucker, you busta!
*/

ENDSWITCH

RETURN

// **************************************** Mission Start **********************************


mission_start_intro1:

flag_player_on_mission = 1

bmx_gang[0] = big_smoke
bmx_gang[1] = ryder
bmx_gang[2] = sweet

bmx_bikes[0] = bmx1
bmx_bikes[1] = bmx3
bmx_bikes[2] = bmx2

bmx_gang_finished[0] = 0
bmx_gang_finished[1] = 0
bmx_gang_finished[2] = 0

bmx_rider_on_bike1 = 0
bmx_rider_on_bike2 = 0
bmx_rider_on_bike3 = 0

freeze_the_bmx[0] = 0
freeze_the_bmx[1] = 0
freeze_the_bmx[2] = 0

intro1_index = 0
index_intro = 0
is_recording_started_intr2 = 0
been_in_a_bmx = 0
out_of_intro1_locate = 0
everyone_finished = 0
car_shits_it = 0
freeze_the_gangcar = 0
dont_repeat_task = 0
split_up_cut = 0
switch_traffic_back_on = 0
failed_mission = 0
intro_blip2_flag_on_bike = 0
num_bikes_in_chase = 3
play_catch_up_audio = 1
play_catch_up_audio_smoke = 1
get_in_counter_intro1_2 = 0

REGISTER_MISSION_GIVEN

LOAD_MISSION_TEXT INTRO1

SET_TIME_OF_DAY 09 00

// ****************************************START OF CUTSCENE********************************

CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

SET_AREA_VISIBLE 3

LOAD_CUTSCENE INTRO1A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

SET_AREA_VISIBLE 0


// ****************************************END OF CUTSCENE**********************************

SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.2

ENABLE_AMBIENT_CRIME FALSE

LOAD_SPECIAL_CHARACTER 10 smoke
REQUEST_MODEL PEREN

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED PEREN
OR NOT HAS_SPECIAL_CHARACTER_LOADED 10
	WAIT 0
ENDWHILE

SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL player1 OFF

	CLEAR_CHAR_TASKS scplayer
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL10 2498.91 -1680.5 12.37 big_smoke //Big_Smoke
	SET_ANIM_GROUP_FOR_CHAR big_smoke fatman
	SET_CHAR_HEADING big_smoke 90.0

	CREATE_CAR PEREN 2494.91 -1682.17 12.32 smokes_car1
	MARK_MODEL_AS_NO_LONGER_NEEDED PEREN
	CHANGE_CAR_COLOUR smokes_car1 CARCOLOUR_BLACK CARCOLOUR_BLACK
	SET_CAR_HEADING smokes_car1 90.0
	SET_CHAR_COORDINATES scplayer 2495.59 -1686.96 12.51

	TASK_STAND_STILL scplayer TRUE
	SET_CHAR_HEADING scplayer 32.0
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer	
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER big_smoke smokes_car1 10000 0
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_DRIVER scplayer smokes_car1 10000
			  
	CLEAR_AREA 2494.91 -1682.17 12.32 50.0 TRUE 

	//LOAD_SCENE 2498.71 -1681.89 12.37
	LOAD_SCENE_IN_DIRECTION 2495.59 -1686.96 12.51 32.20


SET_AREA_VISIBLE 0

SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL

intro1_index = 0
intro1_audio_is_playing = 0
intro1_cutscene_flag = 0
intro1_chat_switch = INTRO1_CHAT1
GOSUB intro1_chat_switch
	
	DO_FADE 1500 FADE_IN
							   
	SET_FIXED_CAMERA_POSITION 2498.9470 -1690.0001 14.4011 0.0 0.0 0.0 //Fence view
	POINT_CAMERA_AT_POINT 2498.4045 -1689.1774 14.2309 JUMP_CUT

	SKIP_CUTSCENE_START

	WHILE NOT intro1_index = 2
		WAIT 0

		GOSUB load_and_play_audio_intro1

	ENDWHILE

	CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE                   
    CAMERA_PERSIST_POS TRUE   
    
    CAMERA_SET_VECTOR_MOVE 2498.9470 -1690.0001 14.4011 2501.2363 -1678.2303 17.5252 9000 TRUE
    CAMERA_SET_VECTOR_TRACK 2498.4045 -1689.1774 14.2309 2500.2378 -1678.1876 17.4907 9000 TRUE
                    
	IF NOT IS_CAR_DEAD smokes_car1
		IF NOT IS_CHAR_DEAD big_smoke
	   
			WHILE NOT IS_CHAR_IN_CAR scplayer smokes_car1
			OR NOT IS_CHAR_IN_CAR big_smoke smokes_car1
				WAIT 0
					IF IS_CAR_DEAD smokes_car1
						GOTO mission_intro1_failed	
					ENDIF
					IF IS_CHAR_DEAD big_smoke
						GOTO mission_intro1_failed
					ENDIF

					GOSUB load_and_play_audio_intro1

			ENDWHILE

		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD smokes_car1
		TASK_CAR_DRIVE_TO_COORD scplayer smokes_car1 2414.68 -1656.38 12.38 10.0 MODE_NORMAL PEREN DRIVINGMODE_AVOIDCARS
	ENDIF

WHILE NOT intro1_index = 4
	WAIT 0

	GOSUB load_and_play_audio_intro1

ENDWHILE


SKIP_CUTSCENE_END


CLEAR_PRINTS

// ****************************************START OF CUTSCENE********************************

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

PRINT_BIG ( FUNERAL ) 1000 2 //

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CAMERA_RESET_NEW_SCRIPTABLES

DELETE_CHAR big_smoke
UNLOAD_SPECIAL_CHARACTER 10


	IF NOT IS_CAR_DEAD smokes_car1
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CAR_COORDINATES smokes_car1 956.60 -1099.47 22.73
		SET_CAR_HEADING smokes_car1 0.0
		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD scplayer 910.78 -1075.26 23.29
		ELSE
			SET_CHAR_COORDINATES scplayer 910.78 -1075.26 23.29
		ENDIF
		SET_CHAR_HEADING scplayer 265.0
	ENDIF


MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1

LOAD_SCENE_IN_DIRECTION 910.78 -1075.26 23.29 265.0

LOAD_CUTSCENE INTRO1B
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

//SET_AREA_VISIBLE 0

// ****************************************END OF CUTSCENE**********************************

SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.2

//START Funeral Cut Scene**********************************************************************************************

LOAD_SPECIAL_CHARACTER 1 sweet
LOAD_SPECIAL_CHARACTER 3 ryder2
LOAD_SPECIAL_CHARACTER 10 smoke
REQUEST_MODEL PEREN
REQUEST_MODEL BMX
REQUEST_MODEL micro_uzi
REQUEST_MODEL VOODOO
REQUEST_MODEL ballas1 
REQUEST_MODEL COLT45
REQUEST_CAR_RECORDING 201

LOAD_ALL_MODELS_NOW


WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
OR NOT HAS_SPECIAL_CHARACTER_LOADED 10
OR NOT HAS_MODEL_LOADED PEREN
OR NOT HAS_MODEL_LOADED BMX
OR NOT HAS_MODEL_LOADED VOODOO
	WAIT 0
ENDWHILE
	  
WHILE NOT HAS_MODEL_LOADED micro_uzi
OR NOT HAS_MODEL_LOADED	ballas1
OR NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 201
	WAIT 0

ENDWHILE
	 

 	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF
	SET_NEAR_CLIP 0.2

	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0

	CLEAR_AREA 943.71 -1103.50 22.85 70.0 TRUE

	LOAD_SCENE_IN_DIRECTION 943.71 -1103.50 22.85 90.0
	
	CREATE_CAR VOODOO 1038.5 -954.9 41.5 drive_by_car
	SUPPRESS_CAR_MODEL VOODOO
	CHANGE_CAR_COLOUR drive_by_car 22 22

	SET_LOAD_COLLISION_FOR_CAR_FLAG drive_by_car FALSE
	CREATE_CHAR_INSIDE_CAR drive_by_car PEDTYPE_MISSION2 ballas1 drive_by_char1 
	CREATE_CHAR_AS_PASSENGER drive_by_car PEDTYPE_MISSION2 ballas1 0 drive_by_char2
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY chars_decision
	SET_CHAR_ACCURACY drive_by_char1 25
	SET_CHAR_ACCURACY drive_by_char2 25
	SET_CHAR_SHOOT_RATE drive_by_char1 30
	SET_CHAR_SHOOT_RATE	drive_by_char2 30

	SET_CHAR_RELATIONSHIP drive_by_char1 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
	SET_CHAR_RELATIONSHIP drive_by_char2 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1


	MARK_MODEL_AS_NO_LONGER_NEEDED ballas1
	GIVE_WEAPON_TO_CHAR drive_by_char2 WEAPONTYPE_MICRO_UZI 30000
	SET_CURRENT_CHAR_WEAPON drive_by_char2 WEAPONTYPE_MICRO_UZI
	GIVE_WEAPON_TO_CHAR drive_by_char1 WEAPONTYPE_MICRO_UZI 30000
	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
	SET_CURRENT_CHAR_WEAPON drive_by_char1 WEAPONTYPE_MICRO_UZI
	LOCK_CAR_DOORS drive_by_car CARLOCK_LOCKED 

	SET_CAR_HEADING drive_by_car 100.8
	SET_CAR_HEALTH drive_by_car	2000  

	CREATE_CAR BMX 971.8 -1108.0 23.1 players_bmx
	SET_CAR_HEALTH players_bmx 2000
	SET_CAR_HEADING players_bmx 140.0	
	SET_CAN_BURST_CAR_TYRES players_bmx FALSE

	CREATE_CAR BMX 971.4 -1110.5 23.3 bmx_bikes[0] //SMOKE
	SET_CAR_HEALTH bmx_bikes[0] 2000
	SET_CAR_HEADING bmx_bikes[0] 110.0
	SET_CAN_BURST_CAR_TYRES bmx_bikes[0] FALSE
	SET_CAR_STRAIGHT_LINE_DISTANCE bmx_bikes[0] 30

	CREATE_CAR BMX 971.8 -1115.5 23.1 bmx_bikes[1] //RYDER
	SET_CAR_HEALTH bmx_bikes[1] 2000
	SET_CAR_HEADING bmx_bikes[1] 140.0
	SET_CAN_BURST_CAR_TYRES bmx_bikes[1] FALSE
	SET_CAR_STRAIGHT_LINE_DISTANCE bmx_bikes[1] 30

	CREATE_CAR BMX 971.8 -1113.0 23.1 bmx_bikes[2]	//SWEET 
	SET_CAR_HEALTH bmx_bikes[2] 2000
	SET_CAR_HEADING bmx_bikes[2] 140.0
	SET_CAN_BURST_CAR_TYRES bmx_bikes[2] FALSE
	SET_CAR_STRAIGHT_LINE_DISTANCE bmx_bikes[2] 30


	
	MARK_MODEL_AS_NO_LONGER_NEEDED BMX
	
	IF NOT IS_CAR_DEAD smokes_car1
		SET_PLAYER_CONTROL player1 OFF
		SET_CAR_CAN_BE_DAMAGED smokes_car1 TRUE
		SET_CAR_COORDINATES smokes_car1 956.60 -1099.47 22.73
		SET_CAR_HEADING smokes_car1 0.0
	ENDIF	
		
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 940.0 -1103.50 22.85
	ELSE
		SET_CHAR_COORDINATES scplayer 940.0 -1103.50 22.85 //goto coords  951.1255	-1103.50 22.85
	ENDIF
	SET_CHAR_HEADING scplayer 272.5

	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
	
	TASK_GO_STRAIGHT_TO_COORD scplayer 954.0 -1103.50 22.85 PEDMOVE_WALK 20000
	  
	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL10 942.79 -1104.41 22.85 bmx_gang[0] //SMOKE //left of CJ
	SET_ANIM_GROUP_FOR_CHAR bmx_gang[0] fatman
	SET_CHAR_HEALTH bmx_gang[0] 2000
	SET_CHAR_HEADING bmx_gang[0] 184.0 
	SET_CHAR_NEVER_TARGETTED bmx_gang[0] TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS bmx_gang[0] FALSE
	SET_CHAR_CANT_BE_DRAGGED_OUT bmx_gang[0] TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED bmx_gang[0] TRUE
	TASK_GO_STRAIGHT_TO_COORD bmx_gang[0] 954.0 -1104.41 22.85 PEDMOVE_WALK 20000
	SET_CHAR_DECISION_MAKER bmx_gang[0] chars_decision
	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE bmx_gang[0] KNOCKOFFBIKE_NEVER

	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL03 942.9 -1105.79 22.85 bmx_gang[1] //RYDER //furthest left
	SET_ANIM_GROUP_FOR_CHAR bmx_gang[1] gang1
	SET_CHAR_HEALTH bmx_gang[1] 2000 
	SET_CHAR_HEADING bmx_gang[1] 50.0
	SET_CHAR_NEVER_TARGETTED bmx_gang[1] TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS bmx_gang[1] FALSE
	SET_CHAR_CANT_BE_DRAGGED_OUT bmx_gang[1] TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED bmx_gang[1] TRUE
	TASK_GO_STRAIGHT_TO_COORD bmx_gang[1] 954.0 -1105.79 22.85 PEDMOVE_WALK 25000
	SET_CHAR_DECISION_MAKER bmx_gang[1] chars_decision
	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE bmx_gang[1] KNOCKOFFBIKE_NEVER
	
	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 942.96 -1102.0 22.85 bmx_gang[2] //SWEET //right of CJ
	SET_ANIM_GROUP_FOR_CHAR bmx_gang[2] gang2
	SET_CHAR_HEALTH bmx_gang[2] 2000
	SET_CHAR_HEADING bmx_gang[2] 96.0
	SET_CHAR_NEVER_TARGETTED bmx_gang[2] TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS bmx_gang[2] FALSE
	SET_CHAR_CANT_BE_DRAGGED_OUT bmx_gang[2] TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED bmx_gang[2] TRUE
	TASK_GO_STRAIGHT_TO_COORD bmx_gang[2] 954.0 -1102.0 22.85 PEDMOVE_WALK 20000
	SET_CHAR_DECISION_MAKER bmx_gang[2] chars_decision
	SET_CHAR_CAN_BE_KNOCKED_OFF_BIKE bmx_gang[2] KNOCKOFFBIKE_NEVER

	ADD_BLIP_FOR_CHAR bmx_gang[2] sweet_blip
	SET_BLIP_AS_FRIENDLY sweet_blip TRUE
	REMOVE_BLIP	sweet_blip

	TIMERB = 0

	WHILE TIMERB < 900
		WAIT 0
	ENDWHILE

	LOAD_SCENE_IN_DIRECTION	940.0 -1103.50 22.85 272.5

	DO_FADE 500 FADE_IN

	CLEAR_AREA 949.8 -1100.3 23.0 500.0 TRUE

	SET_FIXED_CAMERA_POSITION 947.9119 -1103.9116 23.4476 0.0 0.0 0.0 //view of 4 guys walking towards cam
	POINT_CAMERA_AT_POINT 946.9478 -1103.8223 23.6977 JUMP_CUT

	intro1_index = 0
	intro1_audio_is_playing = 0
	intro1_cutscene_flag = 0
	intro1_chat_switch = INTRO1_CHAT2
	GOSUB intro1_chat_switch

skip_funeral_cutscene = 0
SKIP_CUTSCENE_START

	TIMERB = 0

	WHILE TIMERB < 3300
		WAIT 0
	ENDWHILE

	IF NOT IS_CAR_DEAD drive_by_car
		IF NOT IS_CHAR_DEAD	drive_by_char1
			IF HAS_CAR_RECORDING_BEEN_LOADED 201
				START_PLAYBACK_RECORDED_CAR drive_by_car 201
			ENDIF	
		ENDIF
	ENDIF

	IF NOT IS_CAR_DEAD smokes_car1
		IF NOT IS_CHAR_DEAD drive_by_char2
			IF IS_CHAR_IN_ANY_CAR drive_by_char2
				TASK_DRIVE_BY drive_by_char2 -1 -1 952.92 -1102.99 22.85 100.0 DRIVEBY_AI_SIDE TRUE 90
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD drive_by_char1
			IF IS_CHAR_IN_ANY_CAR drive_by_char1
				TASK_DRIVE_BY drive_by_char1 -1 smokes_car1 0.0 0.0 0.0 100.0 DRIVEBY_FIXED_LHS FALSE 90
			ENDIF
		ENDIF
	ENDIF

	TIMERB = 0

	WHILE TIMERB < 1500
		WAIT 0
	ENDWHILE


	CAMERA_RESET_NEW_SCRIPTABLES
	SET_FIXED_CAMERA_POSITION 951.3326 -1099.6516 25.3079 0.0 0.0 0.0 //Ryder spots drive by
	POINT_CAMERA_AT_POINT 951.9009 -1100.4523 25.1184 JUMP_CUT

	TIMERB = 0

	WHILE TIMERB < 2500
		WAIT 0
	ENDWHILE

	IF NOT IS_CAR_DEAD drive_by_car
		IF NOT IS_CHAR_DEAD	bmx_gang[1]
			CLEAR_LOOK_AT bmx_gang[1]
			TASK_LOOK_AT_VEHICLE bmx_gang[1] drive_by_car 4000
		ENDIF
	ENDIF

	TIMERB = 0

	WHILE TIMERB < 1000
		WAIT 0
	ENDWHILE

	WHILE NOT intro1_index = 1 //Drive by. Incoming!
			WAIT 0

			GOSUB load_and_play_audio_intro1
			IF intro1_cutscene_flag = 0
				IF intro1_audio_is_playing = 2
					IF NOT IS_CAR_DEAD drive_by_car
						IF NOT IS_CHAR_DEAD	bmx_gang[1]
							//CLEAR_LOOK_AT bmx_gang[1]
							//TASK_LOOK_AT_VEHICLE bmx_gang[1] drive_by_car 4000
							TIMERB = 0
							TASK_LOOK_AT_VEHICLE scplayer drive_by_car 4000

							IF NOT IS_CHAR_DEAD bmx_gang[0]
								CLEAR_LOOK_AT bmx_gang[0]
								TASK_LOOK_AT_VEHICLE bmx_gang[0] drive_by_car 4000
							ENDIF
							IF NOT IS_CHAR_DEAD bmx_gang[2]
								CLEAR_LOOK_AT bmx_gang[2]
								TASK_LOOK_AT_VEHICLE bmx_gang[2] drive_by_car 4000
							ENDIF
							intro1_cutscene_flag = 1
						ENDIF
					ENDIF
					CAMERA_RESET_NEW_SCRIPTABLES
					CAMERA_PERSIST_TRACK TRUE                   
					CAMERA_PERSIST_POS TRUE                       
					CAMERA_SET_VECTOR_MOVE 951.3326 -1099.6516 25.3079 951.3326 -1099.6516 25.3079 7500 TRUE
					CAMERA_SET_VECTOR_TRACK 951.9009 -1100.4523 25.1184 951.9258 -1098.8468 25.3253 7500 TRUE
				ENDIF
			ENDIF

			IF TIMERB > 1000
				IF intro1_cutscene_flag = 1
					IF NOT IS_CHAR_DEAD	bmx_gang[1]
						TASK_DIVE_AND_GET_UP bmx_gang[1] 1.0 -3.0 1000
						IF NOT IS_CHAR_DEAD	bmx_gang[0]
							GIVE_WEAPON_TO_CHAR bmx_gang[0] WEAPONTYPE_PISTOL 50
							SET_CHAR_HEADING bmx_gang[0] 270.0
							SET_CHAR_STAY_IN_SAME_PLACE bmx_gang[0] TRUE
							IF NOT IS_CHAR_DEAD drive_by_char1
								TASK_TOGGLE_DUCK bmx_gang[0] TRUE
								TASK_KILL_CHAR_ON_FOOT bmx_gang[0] drive_by_char1
							ENDIF
						ENDIF
						IF NOT IS_CHAR_DEAD	bmx_gang[2]
							GIVE_WEAPON_TO_CHAR bmx_gang[2] WEAPONTYPE_PISTOL 50
							IF NOT IS_CHAR_DEAD drive_by_char1
								TASK_KILL_CHAR_ON_FOOT_WHILE_DUCKING bmx_gang[2] drive_by_char1 DUCK_RANDOMLY 2000 100
							ENDIF
						ENDIF

						TASK_DIVE_AND_GET_UP scplayer -1.5 -1.0 1000

					ENDIF
					intro1_cutscene_flag = 2
				ENDIF
			ENDIF

	ENDWHILE

	TIMERB = 0

	WHILE TIMERB < 1000
		WAIT 0
	ENDWHILE

	IF NOT IS_CAR_DEAD smokes_car1
		EXPLODE_CAR_IN_CUTSCENE smokes_car1
	ENDIF

	TIMERB = 0
	intro1_cutscene_flag = 0
	WHILE NOT intro1_index = 2 //Aww man!
			WAIT 0

			GOSUB load_and_play_audio_intro1

			IF TIMERB > 1600
				GOTO skip_out_smokes_audio
			ENDIF

	ENDWHILE
	
	skip_out_smokes_audio:
									   
	CLEAR_AREA 956.09 -1105.68 22.75 50.0 TRUE

	IF NOT IS_CHAR_DEAD drive_by_char2
		CLEAR_CHAR_TASKS drive_by_char2
		TASK_PLAY_ANIM drive_by_char2 CAR_sit PED 4.0 FALSE FALSE FALSE FALSE -1
	ENDIF

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	SET_CHAR_COORDINATES scplayer 945.7507 -1103.5486 23.0477
	SET_CHAR_HEADING scplayer 252.8
	TASK_STAND_STILL scplayer TRUE

	IF NOT IS_CHAR_DEAD	bmx_gang[0]
		IF NOT IS_CAR_DEAD bmx_bikes[0]
			SET_CHAR_STAY_IN_SAME_PLACE bmx_gang[0] FALSE
			TASK_STAND_STILL bmx_gang[0] TRUE
			CLEAR_LOOK_AT bmx_gang[0]
			TASK_TOGGLE_DUCK bmx_gang[0] FALSE 
			SET_CHAR_COORDINATES bmx_gang[0] 957.0725 -1106.5038 22.7146  
			SET_CHAR_HEADING bmx_gang[0] 251.1192
			TASK_ENTER_CAR_AS_DRIVER bmx_gang[0] bmx_bikes[0] -1 //SMOKE
		ENDIF
	ENDIF
	
	IF NOT IS_CHAR_DEAD	bmx_gang[1]
		IF NOT IS_CAR_DEAD bmx_bikes[1]
			SET_CHAR_STAY_IN_SAME_PLACE bmx_gang[1] FALSE
			TASK_STAND_STILL bmx_gang[1] TRUE
			CLEAR_LOOK_AT bmx_gang[1]
			TASK_ENTER_CAR_AS_DRIVER bmx_gang[1] bmx_bikes[1] -1 //RYDER
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD	bmx_gang[2]
		IF NOT IS_CAR_DEAD bmx_bikes[2]
			SET_CHAR_STAY_IN_SAME_PLACE bmx_gang[2] FALSE
			TASK_STAND_STILL bmx_gang[2] TRUE
			CLEAR_LOOK_AT bmx_gang[2]
			SET_CHAR_COORDINATES bmx_gang[2] 957.12 -1104.4 22.7146
			TASK_ENTER_CAR_AS_DRIVER bmx_gang[2] bmx_bikes[2] -1 //SWEET
		ENDIF
	ENDIF

	WAIT 100

	CAMERA_RESET_NEW_SCRIPTABLES
	SET_FIXED_CAMERA_POSITION 953.4890 -1100.4652 24.8611 0.0 0.0 0.0 //Sweet talks	again
	POINT_CAMERA_AT_POINT 954.1205 -1101.2365 24.7823 JUMP_CUT


	LOAD_MISSION_AUDIO 2 SOUND_INT1_AP	//Grab a bike and peddle. Even you ain’t forgotten that.

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 3 //BLAH BLAH
		WAIT 0

		GOSUB load_and_play_audio_intro1

		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				TASK_GO_STRAIGHT_TO_COORD scplayer 971.1810 -1108.1931 22.8672 PEDMOVE_RUN 10000
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF

	ENDWHILE

	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 2
	PRINT_NOW ( INT1_AP ) 10000 1 //Grab a bike and peddle. Even you ain't forgotten that.

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0
	ENDWHILE

skip_funeral_cutscene = 1
SKIP_CUTSCENE_END
	 
	IF skip_funeral_cutscene = 0
		CLEAR_PRINTS
		SET_FADING_COLOUR 0 0 0
		DO_FADE 1000 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
		IF NOT IS_CHAR_DEAD	bmx_gang[0]
			IF NOT IS_CAR_DEAD bmx_bikes[0]
				//CLEAR_CHAR_TASKS_IMMEDIATELY bmx_gang[0]
				SET_CHAR_COORDINATES bmx_gang[0] 969.2067 -1110.3004 22.8672 //SMOKE
				SET_CHAR_HEADING bmx_gang[0] 273.1099
				TASK_TOGGLE_DUCK bmx_gang[0] FALSE 
			ENDIF
		ENDIF
		
		IF NOT IS_CHAR_DEAD	bmx_gang[1]
			IF NOT IS_CAR_DEAD bmx_bikes[1]
				//CLEAR_CHAR_TASKS_IMMEDIATELY bmx_gang[1]
				SET_CHAR_COORDINATES bmx_gang[1] 969.1187 -1116.5605 22.8552 //RYDER
				SET_CHAR_HEADING bmx_gang[1] 298.5085
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD	bmx_gang[2]
			IF NOT IS_CAR_DEAD bmx_bikes[2]
				CLEAR_LOOK_AT bmx_gang[2]
				//CLEAR_CHAR_TASKS_IMMEDIATELY bmx_gang[2]
				SET_CHAR_COORDINATES bmx_gang[2] 969.4360 -1113.7754 22.8593 //SWEET
				SET_CHAR_HEADING bmx_gang[2] 267.2885
			ENDIF
		ENDIF


		CAMERA_RESET_NEW_SCRIPTABLES
		IF NOT IS_CAR_DEAD smokes_car1
			EXPLODE_CAR_IN_CUTSCENE smokes_car1
		ENDIF	
	ENDIF



	IF NOT IS_CHAR_DEAD	bmx_gang[0]
		IF NOT IS_CAR_DEAD bmx_bikes[0]
			TASK_TOGGLE_DUCK bmx_gang[0] FALSE 
			TASK_ENTER_CAR_AS_DRIVER bmx_gang[0] bmx_bikes[0] -1 //SMOKE
		ENDIF
	ENDIF
	
	IF NOT IS_CHAR_DEAD	bmx_gang[1]
		IF NOT IS_CAR_DEAD bmx_bikes[1]
			TASK_ENTER_CAR_AS_DRIVER bmx_gang[1] bmx_bikes[1] -1 //RYDER
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD	bmx_gang[2]
		IF NOT IS_CAR_DEAD bmx_bikes[2]
			CLEAR_LOOK_AT bmx_gang[2]
			TASK_ENTER_CAR_AS_DRIVER bmx_gang[2] bmx_bikes[2] -1 //SWEET
		ENDIF
	ENDIF

	MARK_CAR_AS_NO_LONGER_NEEDED smokes_car1
	MARK_MODEL_AS_NO_LONGER_NEEDED PEREN

	IF NOT IS_CHAR_DEAD	bmx_gang[0]
		REMOVE_WEAPON_FROM_CHAR bmx_gang[0] WEAPONTYPE_PISTOL
	ENDIF
	IF NOT IS_CHAR_DEAD	bmx_gang[2]
		REMOVE_WEAPON_FROM_CHAR bmx_gang[2] WEAPONTYPE_PISTOL
	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45

	IF NOT IS_CAR_DEAD drive_by_car
		STOP_PLAYBACK_RECORDED_CAR drive_by_car
	ELSE
		STOP_PLAYBACK_RECORDED_CAR drive_by_car
	ENDIF

	REMOVE_CAR_RECORDING 201
	
	WAIT 100
	
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
	CLEAR_LOOK_AT scplayer

	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

	IF NOT IS_CAR_DEAD players_bmx
		ADD_BLIP_FOR_CAR players_bmx intro_blip2
		SET_BLIP_AS_FRIENDLY intro_blip2 TRUE
	ENDIF
	
	IF NOT IS_CAR_DEAD drive_by_car
		GET_CAR_COORDINATES drive_by_car int1X int1Y int1Z
		int1Z = int1Z - 50.0
		SET_CAR_COORDINATES drive_by_car int1X int1Y int1Z
		FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_car TRUE
	ENDIF

	IF skip_funeral_cutscene = 0
		SET_FADING_COLOUR 0 0 0
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		SET_CHAR_COORDINATES scplayer 970.0873 -1107.7755 22.8672     
		SET_CHAR_HEADING scplayer 254.4577
		
		WAIT 1000
		
		DO_FADE 500 FADE_IN
		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE
	    
	ENDIF

				
OPEN_SEQUENCE_TASK drive_to_hub1
	TASK_CAR_DRIVE_TO_COORD -1 -1 962.5424 -1128.5996 22.6656 8.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 1036.6025 -1148.9425 22.6562 16.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 1325.5830 -1150.1833 22.6484 23.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 1644.7734 -1051.3339 22.8984 23.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS	//SPLIT UP!
CLOSE_SEQUENCE_TASK drive_to_hub1 
				
OPEN_SEQUENCE_TASK drive_to_hub2
	TASK_CAR_DRIVE_TO_COORD -1 -1 1780.85 -1111.48 23.08 21.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1786.54 -1191.30 22.95 20.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1798.67 -1270.41 12.47 19.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1891.00 -1347.78 12.54 21.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1959.12 -1450.10 12.45 21.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1987.61 -1516.69 2.39 21.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 2493.82 -1669.91 12.80 25.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //HUB
CLOSE_SEQUENCE_TASK drive_to_hub2

OPEN_SEQUENCE_TASK drive_to_hub_smoke
	TASK_CAR_DRIVE_TO_COORD -1 -1 1780.85 -1111.48 23.08 20.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1784.54 -1191.30 22.95 19.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1798.67 -1270.41 12.47 19.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1891.00 -1347.78 12.54 20.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1959.12 -1450.10 12.45 20.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 1987.61 -1516.69 2.39 20.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //
	TASK_CAR_DRIVE_TO_COORD -1 -1 2493.82 -1669.91 12.80 24.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS //HUB
CLOSE_SEQUENCE_TASK drive_to_hub_smoke


	IF IS_CAR_DEAD players_bmx
		PRINT_NOW (INT2_F4) 10000 1 //Your BMX is trashed!
		GOTO mission_intro1_failed
	ENDIF

//END Funeral Cut Scene************************************************************************************************

intro1_audio_is_playing = 0

//GOTO skip_to_this_bit_of_script2

intro1_index = 3	
WHILE NOT intro1_index = 4 //Follow my lead
	WAIT 0

	GOSUB load_and_play_audio_intro1
	
	GOSUB bmx_gang_death_check //Check Ryder, Smoke and Sweet are alive
	
	GOSUB freeze_bmxs

	IF failed_mission = 1
		GOTO mission_intro1_failed
	ENDIF

ENDWHILE


PRINT_NOW (INTRO2E) 10000 1 // Get on the ~b~bike~s~ and follow ~b~Sweet.
REQUEST_ANIMATION MISC

blob_flag = 1
TIMERA = 0
			
IF IS_CHAR_DEAD	bmx_gang[2]
	PRINT_NOW (INT2_F2) 10000 1 //~r~Sweet is dead!
	GOTO mission_intro1_failed	
ENDIF

switch_traffic_back_on = 0

killed_the_baller_cunts = 0

intro1_index = 0
intro1_audio_is_playing = 0
intro1_cutscene_flag = 0
intro1_chat_switch = INTRO1_CHAT5
GOSUB intro1_chat_switch

WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 1644.7734 -1051.3339 22.8984 10.0 12.0 20.0 FALSE
OR NOT LOCATE_CHAR_ANY_MEANS_3D bmx_gang[2] 1644.7734 -1051.3339 22.8984 10.0 12.0 20.0 FALSE
	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		SET_CHAR_COORDINATES scplayer 2473.6921 -1684.2153 12.4625
		GOTO mission_intro1_passed
	ENDIF

	GOSUB bmx_gang_death_check //Check Ryder, Smoke and Sweet are alive
	
	GOSUB freeze_bmxs

	IF NOT IS_CAR_DEAD players_bmx
		IF IS_CHAR_IN_CAR scplayer players_bmx
			CLEAR_THIS_PRINT INTRO2E	
		ENDIF
	ENDIF

	IF failed_mission = 1
		GOTO mission_intro1_failed
	ENDIF

	// ***********************************SWITCH PEDS BACK ON**************************************************************
	IF switch_traffic_back_on = 0
		IF TIMERA > 6000
			SET_CAR_DENSITY_MULTIPLIER 1.0
			SET_PED_DENSITY_MULTIPLIER 1.0
			switch_traffic_back_on = 1
		ENDIF
	ENDIF
	// ***************************************************************************************************************

	// **************************************OUTSIDE OF INITIAL LOCATE************************************************
	IF out_of_intro1_locate = 0	
		IF NOT IS_CAR_DEAD drive_by_car
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 949.4 -1102.1 23.2 50.0 60.0 25.0 FALSE
				SET_CAR_COORDINATES drive_by_car 1079.15 -1084.73 25.43 
				SET_CAR_HEADING	drive_by_car 175.95
				FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_car FALSE
				IF IS_CHAR_IN_ANY_CAR scplayer
					IF NOT IS_CHAR_DEAD drive_by_char2
						IF IS_CHAR_IN_ANY_CAR drive_by_char2
							CLEAR_CHAR_TASKS drive_by_char2
							TASK_DRIVE_BY drive_by_char2 scplayer -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_SIDE TRUE 40
						ENDIF
					ENDIF
					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer players_ride_int1
						IF NOT IS_CHAR_DEAD	drive_by_char1
							IF NOT IS_CAR_DEAD drive_by_car
								CLEAR_CHAR_TASKS drive_by_char1								   
								IF IS_CHAR_IN_ANY_CAR drive_by_char1
									TASK_CAR_MISSION drive_by_char1 drive_by_car players_ride_int1 MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ELSE
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -4.0 0.0 int1_driveX int1_driveY int1_driveZ
					IF NOT IS_CHAR_DEAD drive_by_char1
						GET_SCRIPT_TASK_STATUS drive_by_char1 TASK_CAR_DRIVE_TO_COORD ReturnStatus
						IF ReturnStatus = FINISHED_TASK
							IF NOT IS_CHAR_DEAD	drive_by_char1
								IF NOT IS_CAR_DEAD drive_by_car
									TASK_CAR_DRIVE_TO_COORD	drive_by_char1 drive_by_car int1_driveX int1_driveY int1_driveZ 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				out_of_intro1_locate = 1
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_CAR_DEAD drive_by_car
			IF NOT IS_CHAR_DEAD	drive_by_char1
				IF NOT IS_CHAR_DEAD	drive_by_char2	
					GET_CHAR_COORDINATES scplayer player_bmxX player_bmxY player_bmxZ
					IF NOT LOCATE_CAR_2D drive_by_car player_bmxX player_bmxY 50.0 50.0 FALSE // here's the rubberbanding...
					AND NOT IS_CAR_ON_SCREEN drive_by_car
						CLEAR_CHAR_TASKS drive_by_char1
						GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -40.0 0.0 int1X int1Y int1Z
						GET_CLOSEST_CAR_NODE int1X int1Y int1Z ClosestX ClosestY ClosestZ
						SET_CAR_COORDINATES drive_by_car ClosestX ClosestY ClosestZ
						TURN_CAR_TO_FACE_COORD drive_by_car int1X int1Y
						SET_CAR_FORWARD_SPEED drive_by_car 20.0
					ELSE
						IF IS_CHAR_IN_ANY_CAR scplayer
							GET_SCRIPT_TASK_STATUS drive_by_char1 TASK_CAR_MISSION ReturnStatus
							IF ReturnStatus = FINISHED_TASK
								IF IS_CHAR_IN_ANY_CAR scplayer
									STORE_CAR_CHAR_IS_IN scplayer players_ride_int1
									IF IS_CHAR_IN_ANY_CAR drive_by_char1
										TASK_CAR_MISSION drive_by_char1 drive_by_car players_ride_int1 MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
									ENDIF
								ENDIF
							ENDIF
							IF NOT IS_CHAR_DEAD	drive_by_char2
								IF IS_CHAR_IN_ANY_CAR drive_by_char2
									GET_SCRIPT_TASK_STATUS drive_by_char2 TASK_DRIVE_BY ReturnStatus
									IF ReturnStatus = FINISHED_TASK
										TASK_DRIVE_BY drive_by_char2 scplayer -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_SIDE TRUE 40
									ENDIF
								ENDIF
							ENDIF
						ELSE
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -4.0 0.0 int1_driveX int1_driveY int1_driveZ
							GET_SCRIPT_TASK_STATUS drive_by_char1 TASK_CAR_DRIVE_TO_COORD ReturnStatus
							IF ReturnStatus = FINISHED_TASK
								IF NOT IS_CHAR_DEAD	drive_by_char1
									IF NOT IS_CAR_DEAD drive_by_car
										TASK_CAR_DRIVE_TO_COORD	drive_by_char1 drive_by_car int1_driveX int1_driveY int1_driveZ 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
									ENDIF
								ENDIF
							ENDIF
							IF NOT IS_CHAR_DEAD	drive_by_char2
								IF IS_CHAR_IN_ANY_CAR drive_by_char2
									GET_SCRIPT_TASK_STATUS drive_by_char2 TASK_DRIVE_BY ReturnStatus
									IF ReturnStatus = FINISHED_TASK
										TASK_DRIVE_BY drive_by_char2 scplayer -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_SIDE TRUE 40
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF killed_the_baller_cunts = 0
						IF NOT IS_CHAR_DEAD drive_by_char1
							CLEAR_CHAR_TASKS drive_by_char1 
							TASK_LEAVE_ANY_CAR drive_by_char1
							TASK_KILL_CHAR_ON_FOOT drive_by_char1 scplayer
							killed_the_baller_cunts = 1	
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF killed_the_baller_cunts = 0
					IF NOT IS_CHAR_DEAD drive_by_char2
						CLEAR_CHAR_TASKS drive_by_char2
						TASK_LEAVE_ANY_CAR drive_by_char2
						TASK_KILL_CHAR_ON_FOOT drive_by_char2 scplayer
						killed_the_baller_cunts = 1	
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF IS_CHAR_IN_CAR scplayer players_bmx
			IF NOT IS_CHAR_DEAD bmx_gang[2]
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[2] 20.0 20.0 10.0 FALSE
					GOSUB load_and_play_audio_intro1
					//play_catch_up_audio = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
	// ***************************************************************************************************************
	// ***************************************BMX HELP TEXT***********************************************************
	
	IF IS_CHAR_IN_MODEL scplayer BMX 
		
		IF been_in_a_bmx = 0
			PRINT_HELP INTRO2C 
			
			TIMERA = 0
			been_in_a_bmx = 1
		
		ELSE
			IF been_in_a_bmx = 1
				IF TIMERA > 10000
					
					PRINT_HELP INTRO2D  
					
					TIMERA = 0
					been_in_a_bmx = 2
				ENDIF
			ENDIF
			
			IF been_in_a_bmx = 2
				IF TIMERA > 20000
					PRINT_HELP HELP3B // You can only cycle fast or sprint for a limited amount of time. 
					been_in_a_bmx = 3
					ENDIF
			ENDIF
			IF been_in_a_bmx = 3
				IF TIMERA > 28000
					PRINT_HELP HELP3B2  // The more exercise you get the higher your ~h~stamina~w~ will become, allowing you to exert yourself for longer.  
					been_in_a_bmx = 4
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// ***************************************************************************************************************


	IF IS_CHAR_DEAD bmx_gang[0]
		PRINT_NOW (INT2_F1) 10000 1 //~r~Smoke is dead!
		GOTO mission_intro1_failed
	ENDIF

	IF IS_CHAR_DEAD bmx_gang[1]
		PRINT_NOW (INT2_F3) 10000 1 //~r~Ryder is dead!
		GOTO mission_intro1_failed
	ENDIF
	
	IF IS_CHAR_DEAD bmx_gang[2]
		PRINT_NOW (INT2_F2) 10000 1 //~r~Sweet is dead!
		GOTO mission_intro1_failed
	ENDIF

ENDWHILE

//skip_to_this_bit_of_script2:

//LOAD_SCENE 1714.14 -1303.24 12.39 //REMOVE THIS

// ************************************************SPLIT UP CUT SCENE START*********************************************************************************************
	flag_the_drive_by_car_is_dead = 0

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	SET_FIXED_CAMERA_POSITION 1647.2081 -1085.2321 24.1633 0.0 0.0 0.0 //Follow Ryder
	POINT_CAMERA_AT_POINT 1646.3540 -1084.7119 24.1640 JUMP_CUT

	SET_PLAYER_CONTROL player1 OFF
	
	LOAD_SCENE_IN_DIRECTION	1637.6420 -1063.7017 22.8984 152.0807

	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	WHILE NOT HAS_ANIMATION_LOADED MISC
		WAIT 0
		REQUEST_ANIMATION MISC
		DO_FADE 0 FADE_OUT
	ENDWHILE

	num_bikes_in_chase = 2

	IF IS_CAR_DEAD drive_by_car
		flag_the_drive_by_car_is_dead = 1
		num_bikes_in_chase = 3
	ELSE
		IF IS_CAR_IN_WATER drive_by_car
		OR IS_CHAR_DEAD drive_by_char1
		OR IS_CHAR_DEAD drive_by_char2
			flag_the_drive_by_car_is_dead = 1
			num_bikes_in_chase = 3
		ENDIF
	ENDIF

	SWITCH_PED_ROADS_OFF 1782.8021 -1203.4969 0.0 1788.2726 -1236.1504 20.0
	SET_NEAR_CLIP 0.2
	intro1_index = 0
	intro1_audio_is_playing = 0
	intro1_cutscene_flag = 0
	intro1_chat_switch = INTRO1_CHAT4
	GOSUB intro1_chat_switch

	IF NOT IS_CAR_DEAD bmx_bikes[2]
		IF NOT IS_CHAR_DEAD	bmx_gang[2]
			FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[2] FALSE
			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[2] FALSE

			IF NOT IS_CHAR_IN_CAR bmx_gang[2] bmx_bikes[2]
				WARP_CHAR_INTO_CAR bmx_gang[2] bmx_bikes[2]
			ENDIF
			CLEAR_AREA 1644.4703 -1072.6582 22.9062 4.0 TRUE
			IF flag_the_drive_by_car_is_dead = 0
				SET_CAR_COORDINATES bmx_bikes[2] 1644.4703 -1072.6582 22.9062
				SET_CAR_HEADING	bmx_bikes[2] 170.0
				TASK_CAR_DRIVE_TO_COORD bmx_gang[2] bmx_bikes[2] 1540.2981 -1159.1885 22.9062 20.0 MODE_NORMAL BMX DRIVINGMODE_AVOIDCARS
				SET_CAR_FORWARD_SPEED bmx_bikes[2] 10.0
				
				IF NOT IS_CAR_DEAD drive_by_car
					CLEAR_AREA 1637.0801 -1061.0013 22.9114 4.0 TRUE
					SET_CAR_COORDINATES drive_by_car 1637.0801 -1061.0013 22.9114 
					SET_CAR_HEADING	drive_by_car 182.2789
					SET_CAR_FORWARD_SPEED drive_by_car 5.0

					IF NOT IS_CHAR_DEAD drive_by_char2
						CLEAR_CHAR_TASKS drive_by_char2
						//TASK_DRIVE_BY drive_by_char2 -1 bmx_bikes[2] 0.0 0.0 0.0 1000.0 DRIVEBY_AI_SIDE false 50
					ENDIF
					IF NOT IS_CHAR_DEAD drive_by_char1
						CLEAR_CHAR_TASKS drive_by_char1
						//TASK_DRIVE_BY drive_by_char1 -1 bmx_bikes[2] 0.0 0.0 0.0 100.0 DRIVEBY_FIXED_LHS FALSE 50
						TASK_CAR_MISSION drive_by_char1 drive_by_car bmx_bikes[2] MISSION_ESCORT_LEFT 40.0 DRIVINGMODE_AVOIDCARS //Drive by bloke		
					ENDIF
				ENDIF

			ELSE
				SET_CAR_COORDINATES bmx_bikes[2] 1638.1656 -1056.2979 22.9062 
				SET_CAR_HEADING	bmx_bikes[2] 272.6299
			ENDIF

			REMOVE_BLIP	sweet_blip

		ENDIF	
	ENDIF

	CLEAR_AREA 1638.7117 -1050.3188 22.8984 4.0 TRUE

	IF NOT IS_CHAR_DEAD bmx_gang[0]
		IF NOT IS_CAR_DEAD bmx_bikes[0]
			FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[0] FALSE
			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[0] FALSE
			IF NOT IS_CHAR_IN_CAR bmx_gang[0] bmx_bikes[0] //SMOKE
				WARP_CHAR_INTO_CAR bmx_gang[0] bmx_bikes[0]
			ENDIF
			CLEAR_AREA 1640.6490 -1055.4727 22.9062 4.0 TRUE
			SET_CAR_COORDINATES bmx_bikes[0] 1640.6490 -1055.4727 22.9062
			SET_CAR_HEADING	bmx_bikes[0] 260.0
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD bmx_gang[1]
		IF NOT IS_CAR_DEAD bmx_bikes[1]
			FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[1] FALSE
			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[1] FALSE
			IF NOT IS_CHAR_IN_CAR bmx_gang[1] bmx_bikes[1] //RYDER
				WARP_CHAR_INTO_CAR bmx_gang[1] bmx_bikes[1]
			ENDIF
			CLEAR_AREA 1644.9131 -1050.5381 22.8984 4.0 TRUE
			SET_CAR_COORDINATES bmx_bikes[1] 1644.9131 -1050.5381 22.8984 
			SET_CAR_HEADING	bmx_bikes[1] 270.0
		ENDIF
	ENDIF

	SWITCH_WIDESCREEN ON

	WAIT 100

	IF IS_CHAR_IN_ANY_CAR scplayer
	OR IS_CHAR_ON_ANY_BIKE scplayer
		STORE_CAR_CHAR_IS_IN scplayer players_ride_int1
		SET_CAR_COORDINATES players_ride_int1 1638.7117 -1050.3188 22.8984 
		SET_CAR_HEADING	players_ride_int1 252.8
	ELSE
		SET_CHAR_COORDINATES scplayer 1638.7117 -1050.3188 22.8984 
		SET_CHAR_HEADING scplayer 252.8
	ENDIF

	CLEAR_AREA 1636.0842 -1093.4589 23.7139 100.0 TRUE

	IF flag_the_drive_by_car_is_dead = 1
		GOTO the_drive_by_car_is_dead
	ENDIF

	CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE                   
    CAMERA_PERSIST_POS TRUE    
                           
    CAMERA_SET_VECTOR_MOVE 1647.2081 -1085.2321 24.1633 1647.9692 -1098.4287 24.1724  4100 TRUE
    CAMERA_SET_VECTOR_TRACK 1646.3540 -1084.7119 24.1640 1647.5226 -1099.3234 24.1731 4100 TRUE
	
	DO_FADE 500 FADE_IN

	TIMERA = 0

	WHILE TIMERA < 500
		WAIT 0

	ENDWHILE

	WHILE NOT intro1_index = 1 // Split Up!
		WAIT 0

		GOSUB load_and_play_audio_intro1

	ENDWHILE

	TIMERA = 0

	WHILE TIMERA < 1000
		WAIT 0

	ENDWHILE

	the_drive_by_car_is_dead:

	IF flag_the_drive_by_car_is_dead = 1
		intro1_index = 1
		DO_FADE 500 FADE_IN
	ENDIF

	CAMERA_RESET_NEW_SCRIPTABLES

	SET_FIXED_CAMERA_POSITION 1646.4762 -1049.4296 23.0659 0.0 0.0 0.0 //Follow Ryder
	POINT_CAMERA_AT_POINT 1645.6925 -1049.9973 23.3176 JUMP_CUT

	WHILE NOT intro1_index = 2 // Keep up, motherfucker!
			WAIT 0

			GOSUB load_and_play_audio_intro1

			IF intro1_cutscene_flag = 0
				IF intro1_audio_is_playing = 2
					
					IF NOT IS_CHAR_DEAD bmx_gang[1]
						TASK_PLAY_ANIM bmx_gang[1] BMX_COMEON MISC 4.0 FALSE FALSE FALSE FALSE -1
						GET_SCRIPT_TASK_STATUS bmx_gang[1] TASK_PLAY_ANIM ReturnStatus
					ENDIF

					intro1_cutscene_flag = 1
				ENDIF
			ENDIF

	ENDWHILE

	IF NOT IS_CHAR_DEAD bmx_gang[1]
		IF NOT IS_CAR_DEAD players_bmx
			IF IS_CHAR_IN_CAR scplayer players_bmx
				ADD_BLIP_FOR_CHAR bmx_gang[1] ryder_blip
				SET_BLIP_AS_FRIENDLY ryder_blip TRUE
			ENDIF
		ENDIF
		PERFORM_SEQUENCE_TASK bmx_gang[1] drive_to_hub2 //RYDER
	ENDIF

	IF NOT IS_CHAR_DEAD bmx_gang[0]
		IF NOT IS_CAR_DEAD bmx_bikes[0]
			PERFORM_SEQUENCE_TASK bmx_gang[0] drive_to_hub_smoke //SMOKE
			CLEAR_SEQUENCE_TASK	drive_to_hub_smoke
		ENDIF
	ENDIF

	IF flag_the_drive_by_car_is_dead = 0
		IF NOT IS_CHAR_DEAD bmx_gang[2]	 //FREEZE SWEET
			IF NOT IS_CAR_DEAD bmx_bikes[2]
				CLEAR_CHAR_TASKS_IMMEDIATELY bmx_gang[2]
				SET_CAR_COORDINATES bmx_bikes[2] 1540.2981 -1159.1885 -50.3438
				WAIT 0
				IF NOT IS_CHAR_DEAD bmx_gang[2]	 //FREEZE SWEET
					IF NOT IS_CAR_DEAD bmx_bikes[2]
						IF NOT IS_CHAR_IN_ANY_CAR bmx_gang[2]
							WARP_CHAR_INTO_CAR bmx_gang[2] bmx_bikes[2]
						ENDIF
						FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[2] TRUE
						FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[2] TRUE
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD drive_by_char1 //FREEZE DRIVE_BY_CAR
			IF NOT IS_CHAR_DEAD drive_by_char2
				IF NOT IS_CAR_DEAD drive_by_car
					CLEAR_CHAR_TASKS_IMMEDIATELY drive_by_char1
					CLEAR_CHAR_TASKS_IMMEDIATELY drive_by_char2
					SET_CAR_COORDINATES drive_by_car 1540.2981 -1171.1885 -50.3438
					WAIT 0
					IF NOT IS_CHAR_DEAD drive_by_char1 //FREEZE DRIVE_BY_CAR
						IF NOT IS_CHAR_DEAD drive_by_char2
							IF NOT IS_CAR_DEAD drive_by_car
								FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_char1 TRUE
								FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_char2 TRUE
								FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_car TRUE
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ELSE
		IF NOT IS_CHAR_DEAD bmx_gang[2]
			IF NOT IS_CAR_DEAD bmx_bikes[2]
				PERFORM_SEQUENCE_TASK bmx_gang[2] drive_to_hub2 //SWEET
			ENDIF
		ENDIF
	ENDIF

	CLEAR_SEQUENCE_TASK	drive_to_hub2

	WAIT 1000

	
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	
	RESTORE_CAMERA_JUMPCUT
	split_up_cut = 1

	IF NOT IS_CAR_DEAD players_bmx
		IF IS_CHAR_IN_CAR scplayer players_bmx
			PRINT_NOW (INTRO2K) 1000 1 // Follow Ryder! 
		ELSE
			PRINT_NOW (INTRO2E) 10000 1 // Get on the bike
		ENDIF
	ENDIF

// ************************************************SPLIT UP CUT SCENE END***************************************************************************************************************

switch_traffic_back_on = 2

IF IS_CAR_DEAD players_bmx
	PRINT_NOW (INT2_F4) 10000 1 //Your BMX is trashed!
	GOTO mission_intro1_failed	
ENDIF

IF IS_CHAR_DEAD bmx_gang[1]
	GOTO mission_intro1_failed	
ENDIF

IF NOT IS_CAR_DEAD players_bmx
	IF IS_CHAR_IN_CAR scplayer players_bmx
		blob_flag = 1
	ELSE
		blob_flag = 0
	ENDIF
ENDIF

out_of_intro1_locate = 0
car_shits_it = 0
//GOTO skip_to_this_bit_of_script

intro1_index = 0
intro1_audio_is_playing = 0
intro1_cutscene_flag = 0
intro1_chat_switch = INTRO1_CHAT7
get_in_counter_intro1 = 0
GOSUB intro1_chat_switch
TIMERA = 0
set_up_smokes_audio = 0

WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2493.82 -1669.91 12.8 4.0 3.5 4.0 TRUE //RACE BACK TO THE HOOD
OR NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[1] 60.0 60.0 20.0 FALSE
	WAIT 0
	
	GOSUB bmx_gang_death_check //Check Ryder and Smoke are alive
	
	GOSUB freeze_bmxs

	IF failed_mission = 1
		GOTO mission_intro1_failed
	ENDIF

	IF car_shits_it = 0
		IF out_of_intro1_locate = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1981.2915 -1510.2186 2.3844 40.0 40.0 15.0 FALSE
				IF NOT IS_CHAR_DEAD drive_by_char1 //FREEZE DRIVE_BY_CAR
					IF NOT IS_CHAR_DEAD drive_by_char2
						IF NOT IS_CAR_DEAD drive_by_car
							FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_char1 FALSE
							FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_char2 FALSE
							FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION drive_by_car FALSE
							SET_CAR_COORDINATES drive_by_car 1809.0 -1519.0 7.0 
							SET_CAR_HEADING drive_by_car 270.0
							IF NOT IS_CHAR_IN_ANY_CAR drive_by_char1
								WARP_CHAR_INTO_CAR drive_by_char1 drive_by_car
							ENDIF
							IF NOT IS_CHAR_IN_ANY_CAR drive_by_char2
								WARP_CHAR_INTO_CAR_AS_PASSENGER drive_by_char2 drive_by_car 0
							ENDIF
							CLEAR_CHAR_TASKS drive_by_char2
							IF IS_CHAR_IN_ANY_CAR drive_by_char2
								TASK_DRIVE_BY drive_by_char2 scplayer -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_SIDE TRUE 40
							ENDIF
						ENDIF

						IF IS_CHAR_IN_ANY_CAR scplayer
							STORE_CAR_CHAR_IS_IN scplayer players_ride_int1
							IF NOT IS_CHAR_DEAD	drive_by_char1
								IF NOT IS_CAR_DEAD drive_by_car
									CLEAR_CHAR_TASKS drive_by_char1								   
									IF IS_CHAR_IN_ANY_CAR drive_by_char1
										TASK_CAR_MISSION drive_by_char1 drive_by_car players_ride_int1 MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
									ENDIF
								ENDIF
							ENDIF
						ELSE
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -4.0 0.0 int1_driveX int1_driveY int1_driveZ
							IF NOT IS_CHAR_DEAD drive_by_char1
								GET_SCRIPT_TASK_STATUS drive_by_char1 TASK_CAR_DRIVE_TO_COORD ReturnStatus
								IF ReturnStatus = FINISHED_TASK
									IF NOT IS_CHAR_DEAD	drive_by_char1
										IF NOT IS_CAR_DEAD drive_by_car
											IF IS_CHAR_IN_ANY_CAR drive_by_char1
												TASK_CAR_DRIVE_TO_COORD	drive_by_char1 drive_by_car int1_driveX int1_driveY int1_driveZ 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF					
						out_of_intro1_locate = 1		
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD drive_by_car
				IF NOT IS_CHAR_DEAD	drive_by_char1
					IF NOT IS_CHAR_DEAD	drive_by_char2	
						GET_CHAR_COORDINATES scplayer player_bmxX player_bmxY player_bmxZ
						IF NOT LOCATE_CAR_2D drive_by_car player_bmxX player_bmxY 50.0 50.0 FALSE
						AND NOT IS_CAR_ON_SCREEN drive_by_car
							CLEAR_CHAR_TASKS drive_by_char1
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -40.0 0.0 int1X int1Y int1Z
							GET_CLOSEST_CAR_NODE int1X int1Y int1Z ClosestX ClosestY ClosestZ
							SET_CAR_COORDINATES drive_by_car ClosestX ClosestY ClosestZ
							TURN_CAR_TO_FACE_COORD drive_by_car int1X int1Y
							SET_CAR_FORWARD_SPEED drive_by_car 20.0
						ELSE
							IF IS_CHAR_IN_ANY_CAR scplayer
								GET_SCRIPT_TASK_STATUS drive_by_char1 TASK_CAR_MISSION ReturnStatus
								IF ReturnStatus = FINISHED_TASK
									IF IS_CHAR_IN_ANY_CAR scplayer
										STORE_CAR_CHAR_IS_IN scplayer players_ride_int1
										IF IS_CHAR_IN_ANY_CAR drive_by_char1
											TASK_CAR_MISSION drive_by_char1 drive_by_car players_ride_int1 MISSION_ESCORT_LEFT 30.0 DRIVINGMODE_AVOIDCARS
										ENDIF
									ENDIF
								ENDIF
								IF NOT IS_CHAR_DEAD	drive_by_char2
									IF IS_CHAR_IN_ANY_CAR drive_by_char2
										GET_SCRIPT_TASK_STATUS drive_by_char2 TASK_DRIVE_BY ReturnStatus
										IF ReturnStatus = FINISHED_TASK
											TASK_DRIVE_BY drive_by_char2 scplayer -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_SIDE TRUE 40
										ENDIF
									ENDIF
								ENDIF
							ELSE
								GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -4.0 0.0 int1_driveX int1_driveY int1_driveZ
								GET_SCRIPT_TASK_STATUS drive_by_char1 TASK_CAR_DRIVE_TO_COORD ReturnStatus
								IF ReturnStatus = FINISHED_TASK
									IF NOT IS_CHAR_DEAD	drive_by_char1
										IF NOT IS_CAR_DEAD drive_by_car
											IF IS_CHAR_IN_ANY_CAR drive_by_char1
												TASK_CAR_DRIVE_TO_COORD	drive_by_char1 drive_by_car int1_driveX int1_driveY int1_driveZ 30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								IF NOT IS_CHAR_DEAD	drive_by_char2
									IF IS_CHAR_IN_ANY_CAR drive_by_char2
										GET_SCRIPT_TASK_STATUS drive_by_char2 TASK_DRIVE_BY ReturnStatus
										IF ReturnStatus = FINISHED_TASK
											TASK_DRIVE_BY drive_by_char2 scplayer -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_SIDE TRUE 40
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

						
					ELSE
						IF killed_the_baller_cunts = 0
							IF NOT IS_CHAR_DEAD drive_by_char1
								CLEAR_CHAR_TASKS drive_by_char1
								TASK_LEAVE_ANY_CAR drive_by_char1
								TASK_KILL_CHAR_ON_FOOT drive_by_char1 scplayer
								killed_the_baller_cunts = 1	
							ENDIF
						ENDIF
					ENDIF

				ELSE
					IF killed_the_baller_cunts = 0
						IF NOT IS_CHAR_DEAD drive_by_char2
							CLEAR_CHAR_TASKS drive_by_char2
							TASK_LEAVE_ANY_CAR drive_by_char2
							TASK_KILL_CHAR_ON_FOOT drive_by_char2 scplayer
							killed_the_baller_cunts = 1	
						ENDIF
					ENDIF
				ENDIF
			ENDIF	
		ENDIF

		// *********************************GANG CAR PISSES OFF AT GROVE STREET*******************************************
		IF NOT IS_CHAR_DEAD	drive_by_char1
			IF NOT IS_CAR_DEAD drive_by_car
				IF LOCATE_CHAR_ANY_MEANS_3D drive_by_char1 2341.85 -1658.92 12.39 30.0 30.0 30.0 FALSE
					CLEAR_CHAR_TASKS drive_by_char1
					TASK_CAR_DRIVE_WANDER drive_by_char1 drive_by_car 20.0 DRIVINGMODE_AVOIDCARS
					car_shits_it = 1
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF set_up_smokes_audio = 0
		IF TIMERA > 1500
			IF NOT IS_CHAR_DEAD bmx_gang[1]
				IF NOT IS_CAR_DEAD bmx_bikes[1]
					IF IS_CHAR_IN_CAR scplayer players_bmx
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[1] 20.0 20.0 10.0 FALSE
							GOSUB load_and_play_audio_intro1
							play_catch_up_audio = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF intro1_index > 1
			set_up_smokes_audio = 1
		ENDIF
	ELSE
		IF set_up_smokes_audio = 1
			intro1_index = 0
			intro1_audio_is_playing = 0
			intro1_cutscene_flag = 0
			intro1_chat_switch = INTRO1_CHAT6
			get_in_counter_intro1 = 0
			GOSUB intro1_chat_switch
			TIMERA = 0
			set_up_smokes_audio = 2
		ENDIF
		IF set_up_smokes_audio = 2
			IF TIMERA > 1500
				IF NOT IS_CHAR_DEAD bmx_gang[0]
					IF NOT IS_CAR_DEAD bmx_bikes[0]
						IF IS_CHAR_IN_CAR scplayer players_bmx
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[0] 20.0 20.0 10.0 FALSE
								GOSUB load_and_play_audio_intro1
								play_catch_up_audio_smoke = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF intro1_index > 2
				set_up_smokes_audio = 3
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD bmx_gang[0]
		PRINT_NOW (INT2_F1) 10000 1 //~r~Smoke is dead!
		GOTO mission_intro1_failed
	ENDIF

	IF IS_CHAR_DEAD bmx_gang[1]
		PRINT_NOW (INT2_F3) 10000 1 //~r~Ryder is dead!
		GOTO mission_intro1_failed
	ENDIF
	
	IF flag_the_drive_by_car_is_dead = 1
		IF IS_CHAR_DEAD bmx_gang[2]
			PRINT_NOW (INT2_F2) 10000 1 //~r~Sweet is dead!
			GOTO mission_intro1_failed
		ENDIF
	ENDIF

ENDWHILE

		
	SET_PLAYER_CONTROL player1 OFF
	
	// *******************************Mission Passed CUT SCENE********************************************************
	
 	flag_the_drive_by_car_is_dead = 0

	IF NOT IS_CAR_DEAD players_bmx
		IF IS_CHAR_IN_CAR scplayer players_bmx
			TASK_PLAY_ANIM scplayer BMX_CELEBRATE MISC 4.0 FALSE FALSE FALSE FALSE -1
		ENDIF
	ENDIF

	WAIT 1000

	//skip_to_this_bit_of_script2:  //TEST!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	DO_FADE 1500 FADE_OUT //End cutscene

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	CLEAR_WANTED_LEVEL player1

	REQUEST_CAR_RECORDING 205
	REQUEST_CAR_RECORDING 206
	REQUEST_ANIMATION MISC
	REQUEST_ANIMATION GANGS

	LOAD_ALL_MODELS_NOW

	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 205
	OR NOT HAS_CAR_RECORDING_BEEN_LOADED 206
	OR NOT HAS_ANIMATION_LOADED MISC
	OR NOT HAS_ANIMATION_LOADED GANGS
		WAIT 0

	ENDWHILE

	SWITCH_WIDESCREEN ON
	SET_NEAR_CLIP 0.2

	intro1_index = 0
	intro1_audio_is_playing = 0
	intro1_cutscene_flag = 0
	intro1_chat_switch = INTRO1_CHAT3
	GOSUB intro1_chat_switch	
												
	IF NOT IS_CAR_DEAD players_bmx
		IF NOT IS_CHAR_IN_CAR scplayer players_bmx
			IF IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_FROM_CAR_TO_COORD scplayer 2487.3093 -1668.3717 12.3438
				SET_CHAR_HEADING scplayer 80.0
				//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				flag_the_drive_by_car_is_dead = 1
			ELSE
				SET_CHAR_COORDINATES scplayer 2487.3093 -1668.3717 12.3438
				SET_CHAR_HEADING scplayer 80.0
				//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
				flag_the_drive_by_car_is_dead = 1
			ENDIF
		ELSE
			SET_CAR_COORDINATES players_bmx 2487.3093 -1668.3717 12.3438   
			SET_CAR_HEADING players_bmx 80.0
		ENDIF
	ENDIF

	CLEAR_AREA 2487.3093 -1668.3717 12.3438 50.0 TRUE

	IF NOT IS_CHAR_DEAD	bmx_gang[0]	//SMOKE
		IF NOT IS_CAR_DEAD bmx_bikes[0]
			CLEAR_CHAR_TASKS_IMMEDIATELY bmx_gang[0]
			FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[0] FALSE
			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[0] FALSE
			IF NOT IS_CHAR_IN_ANY_CAR bmx_gang[0]
				WARP_CHAR_INTO_CAR bmx_gang[0] bmx_bikes[0]  
			ENDIF
			SET_CAR_COORDINATES bmx_bikes[0] 2487.1721 -1666.3010 12.3438    
			SET_CAR_HEADING bmx_bikes[0] 124.2698
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD	bmx_gang[1]	 //RYDER
		IF NOT IS_CAR_DEAD bmx_bikes[1]
			CLEAR_CHAR_TASKS_IMMEDIATELY bmx_gang[1]
			FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[1] FALSE
			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[1] FALSE
			IF NOT IS_CHAR_IN_ANY_CAR bmx_gang[1]
				WARP_CHAR_INTO_CAR bmx_gang[1] bmx_bikes[1]
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD	bmx_gang[2]	 //SWEET
		IF NOT IS_CAR_DEAD bmx_bikes[2]
			CLEAR_CHAR_TASKS_IMMEDIATELY bmx_gang[2]
			FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[2] FALSE
			FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[2] FALSE
			IF NOT IS_CHAR_IN_ANY_CAR bmx_gang[2]
				WARP_CHAR_INTO_CAR bmx_gang[2] bmx_bikes[2]
			ENDIF
			SET_CAR_COORDINATES bmx_bikes[2] 2371.5002 -1654.8945 12.3826
		ENDIF
	ENDIF

	DELETE_CHAR	drive_by_char1
	DELETE_CHAR	drive_by_char2
	DELETE_CAR drive_by_car

	SET_FIXED_CAMERA_POSITION 2409.0374 -1672.3240 18.3556 0.0 0.0 0.0 //Sweet Riding BMX
	POINT_CAMERA_AT_POINT 2409.9541 -1671.9441 18.2334 JUMP_CUT

	LOAD_SCENE 2480.6890 -1670.0065 13.0061

	SET_FIXED_CAMERA_POSITION 2479.8269 -1670.5059 12.9206 0.0 0.0 0.0 //Sweet Riding BMX
	POINT_CAMERA_AT_POINT 2480.6890 -1670.0065 13.0061 JUMP_CUT

	WAIT 1000

	DO_FADE 1000 FADE_IN

	IF NOT IS_CHAR_DEAD	bmx_gang[1]
		IF NOT IS_CAR_DEAD bmx_bikes[1]
			START_PLAYBACK_RECORDED_CAR bmx_bikes[1] 206
			SET_PLAYBACK_SPEED bmx_bikes[1] 1.0
		ENDIF
	ENDIF


	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 1 //I got with them motherfuckers though showed them niggaz who's gangsta. Ryder, nigga
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[1]
					CLEAR_LOOK_AT bmx_gang[1]
					TASK_LOOK_AT_CHAR bmx_gang[1] scplayer 30000
					START_CHAR_FACIAL_TALK bmx_gang[1] 20000
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer bmx_gang[1] 30000

					IF NOT IS_CHAR_DEAD	bmx_gang[0]
						TASK_PLAY_ANIM bmx_gang[0] bmx_idleloop_02 MISC 4.0 TRUE FALSE FALSE FALSE -1 //TEST
					ENDIF
						
					IF flag_the_drive_by_car_is_dead = 0				
						TASK_PLAY_ANIM scplayer bmx_idleloop_01 MISC 4.0 TRUE FALSE FALSE FALSE -1	//TEST
					ENDIF

					IF NOT IS_CHAR_DEAD	bmx_gang[2]
						IF NOT IS_CAR_DEAD bmx_bikes[2]
							START_PLAYBACK_RECORDED_CAR bmx_bikes[2] 205
							SET_PLAYBACK_SPEED bmx_bikes[2] 0.8
						ENDIF
					ENDIF

				ENDIF
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF
		
	ENDWHILE
	
	TIMERB = 0

	WHILE TIMERB < 200
		WAIT 0
	ENDWHILE
	
	SKIP_CUTSCENE_START
	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 2 //When you leaving, Carl?
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[2]
					CLEAR_LOOK_AT bmx_gang[2]
					TASK_LOOK_AT_CHAR bmx_gang[2] scplayer 30000
					START_CHAR_FACIAL_TALK bmx_gang[2] 20000
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer bmx_gang[2] 30000

					TASK_PLAY_ANIM bmx_gang[2] bmx_idleloop_01 MISC 4.0 TRUE FALSE FALSE FALSE -1 //TEST
					
					IF flag_the_drive_by_car_is_dead = 0
						TASK_PLAY_ANIM scplayer bmx_idleloop_01 MISC 4.0 TRUE FALSE FALSE FALSE -1	//TEST
					ENDIF
						 
					IF NOT IS_CHAR_DEAD	bmx_gang[0]
						TASK_PLAY_ANIM bmx_gang[0] bmx_idleloop_02 MISC 4.0 TRUE FALSE FALSE FALSE -1 //TEST
					ENDIF

				ENDIF
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF
		
	ENDWHILE

	IF NOT IS_CHAR_DEAD	bmx_gang[2]
		STOP_CHAR_FACIAL_TALK bmx_gang[2]
	ENDIF

	SET_FIXED_CAMERA_POSITION 2488.3235 -1669.9261 13.3261 0.0 0.0 0.0 //
	POINT_CAMERA_AT_POINT 2487.4849 -1669.3848 13.2679 JUMP_CUT

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 3 //I ain’t sure. Thought I might stay.
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[0]
					CLEAR_LOOK_AT bmx_gang[0]
					TASK_LOOK_AT_CHAR bmx_gang[0] scplayer 15000
					IF NOT IS_CHAR_DEAD	bmx_gang[2]
						CLEAR_LOOK_AT scplayer
						TASK_LOOK_AT_CHAR scplayer bmx_gang[2] 30000
					ENDIF
					//CLEAR_CHAR_TASKS scplayer
					START_CHAR_FACIAL_TALK scplayer 20000
					IF flag_the_drive_by_car_is_dead = 0
						TASK_PLAY_ANIM scplayer bmx_talkright_loop MISC 4.0 TRUE FALSE FALSE FALSE -1 
					ELSE
						//TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE 6000
						TASK_PLAY_ANIM scplayer prtial_gngtlkC GANGS 4.0 TRUE FALSE FALSE FALSE -1
					ENDIF
				ENDIF
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF
		
	ENDWHILE

	STOP_CHAR_FACIAL_TALK scplayer

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 4 //Things are fucked up
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				intro1_cutscene_flag = 1
				START_CHAR_FACIAL_TALK scplayer 20000
			ENDIF
		ENDIF
		
	ENDWHILE

	SET_FIXED_CAMERA_POSITION 2482.8044 -1666.7849 13.7850 0.0 0.0 0.0 //
	POINT_CAMERA_AT_POINT 2483.7778 -1666.7742 13.5566 JUMP_CUT
	CLEAR_CHAR_TASKS scplayer
	IF flag_the_drive_by_car_is_dead = 0
		TASK_PLAY_ANIM scplayer bmx_idleloop_01 MISC 4.0 TRUE FALSE FALSE FALSE -1	//TEST
	ENDIF
	STOP_CHAR_FACIAL_TALK scplayer

	intro1_cutscene_flag = 0	
	
	WHILE NOT intro1_index = 5 //Last thing we need is your help.
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[2]
					START_CHAR_FACIAL_TALK bmx_gang[2] 20000
					IF NOT IS_CHAR_DEAD	bmx_gang[0]
						CLEAR_LOOK_AT bmx_gang[0]
						TASK_LOOK_AT_CHAR bmx_gang[0] bmx_gang[2] 15000
					ENDIF
				ENDIF
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF
		
	ENDWHILE

	IF NOT IS_CHAR_DEAD	bmx_gang[2]
		STOP_CHAR_FACIAL_TALK bmx_gang[2]
	ENDIF

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 6 //I won’t let you down, I swear it.
		WAIT 0

		GOSUB load_and_play_audio_intro1

		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				intro1_cutscene_flag = 1
				START_CHAR_FACIAL_TALK scplayer 20000
				//CLEAR_CHAR_TASKS scplayer
				IF flag_the_drive_by_car_is_dead = 0
					TASK_PLAY_ANIM scplayer bmx_talkright_loop MISC 4.0 TRUE FALSE FALSE FALSE -1 
				ELSE
					TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE 6000
				ENDIF
			ENDIF
		ENDIF

	ENDWHILE

	STOP_CHAR_FACIAL_TALK scplayer
	IF flag_the_drive_by_car_is_dead = 0
		TASK_PLAY_ANIM scplayer bmx_idleloop_01 MISC 4.0 TRUE FALSE FALSE FALSE -1	//TEST
	ELSE
		CLEAR_CHAR_TASKS scplayer
	ENDIF

	SET_FIXED_CAMERA_POSITION 2482.9111 -1662.5752 14.0425 0.0 0.0 0.0 //
	POINT_CAMERA_AT_POINT 2483.3662 -1663.4563 13.9140 JUMP_CUT

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 7 //We gonna call up some freaks. Chill the hell out. You want some?
		WAIT 0

		GOSUB load_and_play_audio_intro1

		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[2]
					START_CHAR_FACIAL_TALK bmx_gang[2] 20000
					IF NOT IS_CHAR_DEAD	bmx_gang[0]
						//CLEAR_CHAR_TASKS scplayer
						CLEAR_LOOK_AT bmx_gang[0]
						TASK_LOOK_AT_CHAR bmx_gang[0] bmx_gang[2] 15000
					ENDIF
				ENDIF
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF

	ENDWHILE

	IF NOT IS_CHAR_DEAD	bmx_gang[2]
		STOP_CHAR_FACIAL_TALK bmx_gang[2]
	ENDIF

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 8 //I gotta lot going on. I’m tired. I’ll see you all later.
		WAIT 0

		GOSUB load_and_play_audio_intro1

		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				intro1_cutscene_flag = 1
				CLEAR_CHAR_TASKS scplayer
				STOP_CHAR_FACIAL_TALK scplayer
				START_CHAR_FACIAL_TALK scplayer 20000
				IF flag_the_drive_by_car_is_dead = 0
					TASK_PLAY_ANIM scplayer bmx_talkright_loop MISC 4.0 TRUE FALSE FALSE FALSE -1 
				ELSE
					TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE 6000
				ENDIF
			ENDIF
		ENDIF

	ENDWHILE

	STOP_CHAR_FACIAL_TALK scplayer
	IF flag_the_drive_by_car_is_dead = 0
		TASK_PLAY_ANIM scplayer bmx_idleloop_01 MISC 4.0 TRUE FALSE FALSE FALSE -1
	ELSE
		CLEAR_CHAR_TASKS scplayer
	ENDIF

	SET_FIXED_CAMERA_POSITION 2490.0000 -1667.5375 13.7938 0.0 0.0 0.0 //
	POINT_CAMERA_AT_POINT 2489.0439 -1667.4966 13.5038 JUMP_CUT

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 9 //You gotta loose some of that Liberty City anxiety, my brother.
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[0]					
					CLEAR_LOOK_AT bmx_gang[0]
					TASK_LOOK_AT_CHAR bmx_gang[0] scplayer 15000
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer bmx_gang[0] 15000
					//CLEAR_CHAR_TASKS bmx_gang[0]
					START_CHAR_FACIAL_TALK bmx_gang[0] 20000
					TASK_PLAY_ANIM bmx_gang[0] bmx_talkleft_loop MISC 4.0 TRUE FALSE FALSE FALSE -1
				ENDIF
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF


	ENDWHILE

	IF NOT IS_CHAR_DEAD	bmx_gang[0]
		TASK_PLAY_ANIM bmx_gang[0] bmx_idleloop_02 MISC 4.0 TRUE FALSE FALSE FALSE -1 //TEST
	ENDIF

	IF NOT IS_CHAR_DEAD	bmx_gang[0]
		STOP_CHAR_FACIAL_TALK bmx_gang[0]
	ENDIF

	TIMERB = 0

	WHILE TIMERB < 1500
		WAIT 0
	ENDWHILE

	SET_FIXED_CAMERA_POSITION 2474.3059 -1668.4775 13.4599 0.0 0.0 0.0 //
	POINT_CAMERA_AT_POINT 2475.3018 -1668.4584 13.5477 JUMP_CUT

	IF NOT IS_CAR_DEAD bmx_bikes[0]
		SET_CAR_HEADING bmx_bikes[0] 0.0	
	ENDIF

	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 10 //And get yourself some colours, dude. 
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[1]
					CLEAR_LOOK_AT bmx_gang[1]
					TASK_LOOK_AT_CHAR bmx_gang[1] scplayer 15000
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer bmx_gang[1] 15000
					//CLEAR_CHAR_TASKS bmx_gang[1]
					START_CHAR_FACIAL_TALK bmx_gang[1] 20000
					//TASK_PLAY_ANIM bmx_gang[1] bmx_talkright_loop MISC 4.0 TRUE FALSE FALSE FALSE -1
				ENDIF
				intro1_cutscene_flag = 1
			ENDIF
		ENDIF
		
	ENDWHILE

	IF NOT IS_CHAR_DEAD	bmx_gang[1]
		STOP_CHAR_FACIAL_TALK bmx_gang[1]
	ENDIF

	TIMERB = 0
	intro1_cutscene_flag = 0	
	WHILE NOT intro1_index = 11 //RYDER: And a haircut, it's an embarrassment to be seen with you!
		WAIT 0

		GOSUB load_and_play_audio_intro1
		
		IF intro1_cutscene_flag = 0
			IF intro1_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	bmx_gang[1]
					CLEAR_LOOK_AT bmx_gang[1]
					TASK_LOOK_AT_CHAR bmx_gang[1] scplayer 12500 //RYDER
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer bmx_gang[1] 12500
					START_CHAR_FACIAL_TALK bmx_gang[1] 20000
					
					intro1_cutscene_flag = 1
				ENDIF
			ENDIF
		ENDIF
		
		IF TIMERB > 3000
			IF NOT IS_CHAR_DEAD	bmx_gang[0]
				IF NOT IS_CAR_DEAD bmx_bikes[0]
					CLEAR_CHAR_TASKS bmx_gang[0]
					TASK_CAR_DRIVE_TO_COORD bmx_gang[0] bmx_bikes[0] 2501.2441 -1657.7692 12.3949 4.0 MODE_ACCURATE BMX DRIVINGMODE_AVOIDCARS
				ENDIF
			ENDIF
			
			IF NOT IS_CHAR_DEAD	bmx_gang[2]
				IF NOT IS_CAR_DEAD bmx_bikes[2]	//SWEET
					CLEAR_CHAR_TASKS bmx_gang[2]
					TASK_CAR_DRIVE_TO_COORD bmx_gang[2] bmx_bikes[2] 2497.1692 -1680.4458 12.3614 6.0 MODE_ACCURATE BMX DRIVINGMODE_AVOIDCARS
				ENDIF
			ENDIF
		ENDIF

	ENDWHILE

	IF NOT IS_CHAR_DEAD	bmx_gang[1]
		STOP_CHAR_FACIAL_TALK bmx_gang[1]
	ENDIF

	IF NOT IS_CHAR_DEAD	bmx_gang[1]
		IF NOT IS_CAR_DEAD bmx_bikes[1]
			IF IS_CHAR_IN_ANY_CAR bmx_gang[1]
				//CLEAR_CHAR_TASKS bmx_gang[1]
				CLEAR_LOOK_AT bmx_gang[1]
				TASK_CAR_DRIVE_TO_COORD bmx_gang[1] bmx_bikes[1] 2466.27 -1690.28 12.51 2.0 MODE_ACCURATE BMX DRIVINGMODE_AVOIDCARS
			ENDIF
		ENDIF
	ENDIF	

	TIMERB = 0

	WHILE TIMERB < 3000
		WAIT 0
	ENDWHILE

	SKIP_CUTSCENE_END
	CAMERA_RESET_NEW_SCRIPTABLES
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE

	SWITCH_ENTRY_EXIT CARLS	TRUE

	SKIP_CUTSCENE_START
	//SWITCH_WIDESCREEN OFF

	CLEAR_PRINTS
	SET_FIXED_CAMERA_POSITION 2499.3057 -1678.9210 13.0313 0.0 0.0 0.0 // Save the game blah dee blah...
	POINT_CAMERA_AT_POINT 2499.0439 -1679.8518 13.2865 JUMP_CUT

	DELETE_CAR bmx_bikes[0]
	DELETE_CAR bmx_bikes[1]
	DELETE_CAR bmx_bikes[2]
	DELETE_CHAR bmx_gang[0]
	DELETE_CHAR bmx_gang[1]
	DELETE_CHAR bmx_gang[2]

	PRINT_HELP SAVE_G

	TIMERB = 0

	WHILE TIMERB < 6000
		WAIT 0
	ENDWHILE

	SET_FIXED_CAMERA_POSITION 2504.2800 -1682.8573 15.1427 0.0 0.0 0.0 // Save the game blah dee blah...
	POINT_CAMERA_AT_POINT 2504.3059 -1683.8514 15.0385 JUMP_CUT
	PRINT_HELP SAVE_G2

	TIMERB = 0

	WHILE TIMERB < 6000
		WAIT 0
	ENDWHILE
	
	SET_FIXED_CAMERA_POSITION 2494.8633 -1683.0514 13.0207 0.0 0.0 0.0 // Walk into the marker enter CJ's house.
	POINT_CAMERA_AT_POINT 2494.9395 -1684.0370 13.1717 JUMP_CUT
	PRINT_HELP SAVE_G3
	
	SET_PLAYER_CONTROL player1 ON
	
	TIMERB = 0

	WHILE TIMERB < 6000
		WAIT 0
	ENDWHILE

	SKIP_CUTSCENE_END

	CLEAR_CHAR_TASKS scplayer
	SET_CHAR_HEADING scplayer 82.97
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	
	RESTORE_CAMERA_JUMPCUT

GOTO mission_intro1_passed 


 // **************************************** Mission intro1 failed *************************

mission_intro1_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

RETURN

   
// **************************************** mission intro1 passed **************************

mission_intro1_passed:

	flag_intro_mission_counter ++
	REGISTER_MISSION_PASSED ( INTRO_1 ) //Used in the stats 
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 3 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 3  
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME INT
	REMOVE_BLIP save_house_blip[0]
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[0] save_pickupY[0] save_pickupZ[0] RADAR_SPRITE_SAVEHOUSE save_house_blip[0]
	CHANGE_BLIP_DISPLAY save_house_blip[0] BLIP_ONLY
	CLEAR_WANTED_LEVEL player1 
	//ADD_SCORE player1 100
	PLAY_MISSION_PASSED_TUNE 1
	PLAYER_MADE_PROGRESS 1
	REMOVE_BLIP	intro_contact_blip
	SWITCH_ENTRY_EXIT CARLS	TRUE

RETURN
		


// ********************************** mission cleanup **************************************

mission_cleanup_intro1:

	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
	MARK_MODEL_AS_NO_LONGER_NEEDED PEREN
	MARK_MODEL_AS_NO_LONGER_NEEDED BMX
	MARK_MODEL_AS_NO_LONGER_NEEDED ballas1
	MARK_MODEL_AS_NO_LONGER_NEEDED micro_uzi
	MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
	DONT_SUPPRESS_CAR_MODEL VOODOO
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_CHAR_AS_NO_LONGER_NEEDED drive_by_char1
	MARK_CHAR_AS_NO_LONGER_NEEDED drive_by_char2
	MARK_CHAR_AS_NO_LONGER_NEEDED bmx_gang[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED bmx_gang[1]
	MARK_CHAR_AS_NO_LONGER_NEEDED bmx_gang[2]
	MARK_CAR_AS_NO_LONGER_NEEDED drive_by_car
	MARK_CAR_AS_NO_LONGER_NEEDED smokes_car1
	MARK_CAR_AS_NO_LONGER_NEEDED bmx_bikes[0]
	MARK_CAR_AS_NO_LONGER_NEEDED bmx_bikes[1]
	MARK_CAR_AS_NO_LONGER_NEEDED bmx_bikes[2]
	REMOVE_CAR_RECORDING 201
	REMOVE_CAR_RECORDING 205
	REMOVE_CAR_RECORDING 206
	REMOVE_ANIMATION MISC
	REMOVE_ANIMATION GANGS
	SWITCH_PED_ROADS_ON 1782.8021 -1203.4969 0.0 1788.2726 -1236.1504 20.0
	IF NOT IS_CAR_DEAD drive_by_car
		STOP_PLAYBACK_RECORDED_CAR drive_by_car
	ELSE
		STOP_PLAYBACK_RECORDED_CAR drive_by_car
	ENDIF
	ENABLE_AMBIENT_CRIME TRUE
	DELETE_CAR bmx_bikes[0]
	DELETE_CAR bmx_bikes[1]
	DELETE_CAR bmx_bikes[2]
	REMOVE_CHAR_ELEGANTLY bmx_gang[0]
	REMOVE_CHAR_ELEGANTLY bmx_gang[1]
	REMOVE_CHAR_ELEGANTLY bmx_gang[2]
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0
	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 10
	UNLOAD_SPECIAL_CHARACTER 3
	//REMOVE_BLIP intro_blip1
	//REMOVE_BLIP big_smoke_blip
	REMOVE_BLIP	sweet_blip
	REMOVE_BLIP	ryder_blip
	REMOVE_BLIP intro_blip2
	MISSION_HAS_FINISHED

RETURN




bmx_gang_death_check:

	IF NOT IS_CAR_DEAD players_bmx
		IF IS_CHAR_IN_CAR scplayer players_bmx

			IF switch_traffic_back_on = 1
			//OR switch_traffic_back_on = 0
				IF NOT IS_CHAR_DEAD bmx_gang[2]
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[2] 60.0 60.0 20.0 FALSE
						PRINT_NOW (INTRO2G) 6000 1  // ~s~You're too far away. Stay with ~b~Sweet~s~.
						//REMOVE_BLIP intro_blip2
						IF play_catch_up_audio = 1
							play_catch_up_audio = 0
						ENDIF
						blob_flag = 0
					ELSE
						IF play_catch_up_audio = 0
							IF intro1_chat_switch = INTRO1_CHAT5 //SWEET
								IF intro1_index > 5
									IF NOT IS_CHAR_DEAD bmx_gang[2]
										IF LOCATE_CHAR_ANY_MEANS_CHAR_2D bmx_gang[2] scplayer 20.0 20.0 FALSE
											play_catch_up_audio = 1
											GOSUB get_back_in_bike_group	
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						CLEAR_THIS_PRINT INTRO2G  // ~s~You're too far away. Stay with ~b~Sweet~s~.
						
						blob_flag = 1
					ENDIF
				ENDIF
			ENDIF
			IF switch_traffic_back_on = 2
				IF NOT IS_CHAR_DEAD bmx_gang[1]
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[1] 60.0 60.0 20.0 FALSE
						PRINT_NOW (INTRO2J) 6000 1 // too far away, Keep up with Ryder!
						//REMOVE_BLIP intro_blip2
						IF play_catch_up_audio = 1
							play_catch_up_audio = 0
						ENDIF
						blob_flag = 0	
					ELSE
						//IF TIMERA > 500
							IF play_catch_up_audio = 0
								//IF intro1_chat_switch = INTRO1_CHAT7 //RYDER
									IF intro1_index > 1
										IF NOT IS_CHAR_DEAD bmx_gang[1]
											IF LOCATE_CHAR_ANY_MEANS_CHAR_2D bmx_gang[1] scplayer 20.0 20.0 FALSE
												play_catch_up_audio = 1
												GOSUB get_back_in_bike_group	
											ENDIF
										ENDIF
									ENDIF
								//ENDIF
							ENDIF
						//ENDIF
						CLEAR_THIS_PRINT INTRO2J
						blob_flag = 1
						
					ENDIF
				ENDIF
				IF NOT IS_CHAR_DEAD bmx_gang[0]	//SMOKE
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[0] 60.0 60.0 20.0 FALSE
						IF play_catch_up_audio_smoke = 1
							play_catch_up_audio_smoke = 0
						ENDIF
					ELSE
						//IF TIMERA > 1000
							IF play_catch_up_audio_smoke = 0
								IF intro1_index > 2
									IF NOT IS_CHAR_DEAD bmx_gang[0]
										IF LOCATE_CHAR_ANY_MEANS_CHAR_2D bmx_gang[0] scplayer 20.0 20.0 FALSE
											play_catch_up_audio_smoke = 1
											GOSUB get_back_in_bike_group_smoke	
										ENDIF
									ENDIF
								ENDIF
							ENDIF
							CLEAR_THIS_PRINT INTRO2J
						//ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF intro_blip2_flag_on_bike = 0
				
				REMOVE_BLIP	intro_blip2
				blob_flag = 1
				IF switch_traffic_back_on = 0
					IF NOT IS_CHAR_DEAD bmx_gang[2]
						REMOVE_BLIP sweet_blip
						ADD_BLIP_FOR_CHAR bmx_gang[2] sweet_blip
						SET_BLIP_AS_FRIENDLY sweet_blip TRUE
					ENDIF
					SET_CAR_DENSITY_MULTIPLIER 1.0
					SET_PED_DENSITY_MULTIPLIER 1.0
					switch_traffic_back_on = 1
				ENDIF
				IF switch_traffic_back_on = 1
					IF NOT IS_CHAR_DEAD bmx_gang[2]
						REMOVE_BLIP sweet_blip
						ADD_BLIP_FOR_CHAR bmx_gang[2] sweet_blip
						SET_BLIP_AS_FRIENDLY sweet_blip TRUE
					ENDIF
				ENDIF
				IF switch_traffic_back_on = 2
					IF NOT IS_CHAR_DEAD bmx_gang[1]
						REMOVE_BLIP ryder_blip
						ADD_BLIP_FOR_CHAR bmx_gang[1] ryder_blip
						SET_BLIP_AS_FRIENDLY ryder_blip TRUE
					ENDIF
				ENDIF

				intro_blip2_flag_on_bike = 1
			ENDIF
			
		ELSE
			IF intro_blip2_flag_on_bike = 1
				REMOVE_BLIP	intro_blip2
				REMOVE_BLIP ryder_blip
				REMOVE_BLIP sweet_blip
				ADD_BLIP_FOR_CAR players_bmx intro_blip2
				SET_BLIP_AS_FRIENDLY intro_blip2 TRUE
				IF NOT switch_traffic_back_on = 0
					PRINT_NOW (INTRO2E) 10000 1 // Get on the bike
				ENDIF
				blob_flag = 0
				intro_blip2_flag_on_bike = 0
			ENDIF
		ENDIF
	ELSE
		PRINT_NOW (INT2_F4) 10000 1 //Your BMX is trashed!
		failed_mission = 1	
	ENDIF

	IF IS_CHAR_DEAD bmx_gang[0]
		PRINT_NOW (INT2_F1) 10000 1 //~r~Smoke is dead!
		failed_mission = 1
	ELSE
		IF bmx_rider_on_bike1 = 0
			IF NOT IS_CHAR_DEAD bmx_gang[0]
				IF NOT IS_CAR_DEAD bmx_bikes[0]
					IF IS_CHAR_IN_CAR bmx_gang[0] bmx_bikes[0]
						CLEAR_CHAR_TASKS bmx_gang[0]
						PERFORM_SEQUENCE_TASK bmx_gang[0] drive_to_hub1
						SET_CAR_STRAIGHT_LINE_DISTANCE bmx_bikes[0] 10
						bmx_rider_on_bike1 = 1		
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD bmx_gang[1]
		PRINT_NOW (INT2_F3) 10000 1 //~r~Ryder is dead!
		failed_mission = 1
	ELSE
		IF bmx_rider_on_bike3 = 0
			IF NOT IS_CHAR_DEAD bmx_gang[1]
				IF NOT IS_CAR_DEAD bmx_bikes[1]
					IF IS_CHAR_IN_CAR bmx_gang[1] bmx_bikes[1]
						CLEAR_CHAR_TASKS bmx_gang[1]
						PERFORM_SEQUENCE_TASK bmx_gang[1] drive_to_hub1
						SET_CAR_STRAIGHT_LINE_DISTANCE bmx_bikes[1] 10
						bmx_rider_on_bike3 = 1		
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
	IF NOT switch_traffic_back_on = 2
		IF IS_CHAR_DEAD bmx_gang[2]
			PRINT_NOW (INT2_F2) 10000 1 //~r~Sweet is dead!
			failed_mission = 1
		ELSE
			IF bmx_rider_on_bike2 = 0
				IF NOT IS_CHAR_DEAD bmx_gang[2]
					IF NOT IS_CAR_DEAD bmx_bikes[2]
						IF IS_CHAR_IN_CAR bmx_gang[2] bmx_bikes[2]
							CLEAR_CHAR_TASKS bmx_gang[2]
							PERFORM_SEQUENCE_TASK bmx_gang[2] drive_to_hub1
							SET_CAR_STRAIGHT_LINE_DISTANCE bmx_bikes[2] 10
							bmx_rider_on_bike2 = 1		
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF bmx_rider_on_bike1 = 1
	AND bmx_rider_on_bike2 = 1
	AND bmx_rider_on_bike3 = 1
		CLEAR_SEQUENCE_TASK	drive_to_hub1
		bmx_rider_on_bike1 = 2
	ENDIF

RETURN


freeze_bmxs:

	IF TIMERB > 1000
		IF NOT IS_CHAR_DEAD	bmx_gang[index_intro]	//Freeze chars if they are too far away
			IF NOT IS_CAR_DEAD bmx_bikes[index_intro]
				IF IS_CHAR_IN_CAR bmx_gang[index_intro] bmx_bikes[index_intro]
					IF LOCATE_CHAR_ANY_MEANS_CHAR_2D bmx_gang[index_intro] scplayer 130.0 130.0 FALSE
						IF freeze_the_bmx[index_intro] = 0
							FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[index_intro] FALSE
							FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[index_intro] FALSE
							//SET_CAR_CRUISE_SPEED  bmx_bikes[index_intro] 20.0
							freeze_the_bmx[index_intro] = 1
						ENDIF
					ELSE
						IF freeze_the_bmx[index_intro] = 1
							FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION bmx_gang[index_intro] TRUE
							FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION bmx_bikes[index_intro] TRUE
							//SET_CAR_CRUISE_SPEED bmx_bikes[index_intro] 0.0
							freeze_the_bmx[index_intro] = 0
						ENDIF
					ENDIF
				ENDIF
			ELSE
				CLEAR_CHAR_TASKS bmx_gang[index_intro]
				TASK_GO_TO_COORD_ANY_MEANS bmx_gang[index_intro] 2493.82 -1669.91 12.8 PEDMOVE_SPRINT -1
			ENDIF		
		ENDIF

		index_intro ++
		IF index_intro >= num_bikes_in_chase
			index_intro = 0
		ENDIF

		TIMERB = 0
	ENDIF

RETURN

load_and_play_audio_intro1:

	IF intro1_audio_is_playing = 0
	OR intro1_audio_is_playing = 1
		IF intro1_index <= intro1_cell_index_end
			IF TIMERA > 200
				GOSUB play_intro1_audio
			ENDIF
		ENDIF
	ENDIF

	IF intro1_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			intro1_audio_is_playing = 0
			intro1_index ++
			intro1_cutscene_flag = 0
			CLEAR_PRINTS
			TIMERA = 0	
		ENDIF
	ENDIF

RETURN

play_intro1_audio:

	IF intro1_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 intro1_audio_chat[intro1_index]
		intro1_audio_is_playing = 1
	ENDIF
	IF intro1_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $intro1_chat[intro1_index] ) 4000 1
			PLAY_MISSION_AUDIO 1
			intro1_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN


get_back_in_bike_group:
						  
	CLEAR_MISSION_AUDIO 2
	IF switch_traffic_back_on = 1
		IF play_catch_up_audio = 1 //SWEET
			IF get_in_counter_intro1 = 0		   
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EA //Move it, CJ, move it!
			ENDIF
			IF get_in_counter_intro1 = 1
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EB //Keep up, CJ!
			ENDIF
			IF get_in_counter_intro1 = 2
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EC //C’mon, CJ, pedal like a motherfucker!
			ENDIF
			IF get_in_counter_intro1 = 3   
				LOAD_MISSION_AUDIO 2 SOUND_INT1_DB //Move it, CJ, not far to the Grove!
			ENDIF
		ENDIF
	ENDIF
	IF switch_traffic_back_on = 2
		IF play_catch_up_audio = 1 //RYDER
			IF get_in_counter_intro1 = 0
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EH //Move it, CJ - you’re embarrassing us, nigga!
			ENDIF
			IF get_in_counter_intro1 = 1
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EG //What’s the matter, fool? You tired?
			ENDIF
			IF get_in_counter_intro1 = 2
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EI //keep up, motherfucker!
			ENDIF
			IF get_in_counter_intro1 = 3
				LOAD_MISSION_AUDIO 2 SOUND_INT1_FG //Typical, CJ! Leaving the homies behind, huh?
			ENDIF 
		ENDIF
	ENDIF
	
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1

	GOSUB load_any_audio

	PLAY_MISSION_AUDIO 2 
	IF switch_traffic_back_on = 1
		IF play_catch_up_audio = 1 //SWEET  	
			IF get_in_counter_intro1 = 0	 
				PRINT_NOW ( INT1_EA ) 3000 1 //Move it, CJ, move it!
			ENDIF								
			IF get_in_counter_intro1 = 1	
				PRINT_NOW ( INT1_EB ) 3000 1 //Keep up, CJ!
			ENDIF							   	 
			IF get_in_counter_intro1 = 2	
				PRINT_NOW ( INT1_EC ) 3000 1 //C’mon, CJ, pedal like a motherfucker!
			ENDIF						 	   
			IF get_in_counter_intro1 = 3	
				PRINT_NOW ( INT1_BD ) 3000 1 //Move it, CJ, not far to the Grove!
			ENDIF						   
		ENDIF
	ENDIF
	IF switch_traffic_back_on = 2
		IF play_catch_up_audio = 1 //RYDER
			IF get_in_counter_intro1 = 0  
				PRINT_NOW ( INT1_EH ) 3000 1 //Move it, CJ - you’re embarrassing us, nigga!	
			ENDIF
			IF get_in_counter_intro1 = 1	 
				PRINT_NOW ( INT1_EG ) 3000 1 //What’s the matter, fool? You tired?
			ENDIF							 						   	 
			IF get_in_counter_intro1 = 2	
				PRINT_NOW ( INT1_EI ) 3000 1 //I said keep up, motherfucker!
			ENDIF
			IF get_in_counter_intro1 = 3
				PRINT_NOW ( INT1_FG ) 3000 1 //Typical, CJ! Leaving the homies behind, huh?
			ENDIF 	   
		ENDIF
	ENDIF

	GOSUB finish_any_audio

	get_in_counter_intro1 ++
	IF get_in_counter_intro1 > 3
		get_in_counter_intro1 = 0
	ENDIF

	TIMERA = 0

RETURN


get_back_in_bike_group_smoke:
						  
	CLEAR_MISSION_AUDIO 2
	IF switch_traffic_back_on = 2
		IF play_catch_up_audio_smoke = 1 //SMOKE
			IF get_in_counter_intro1_2 = 0
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EJ //What’s matter, CJ?  Can’t keep up with the fat man?
			ENDIF
			IF get_in_counter_intro1_2 = 1
				LOAD_MISSION_AUDIO 2 SOUND_INT1_ED //C’mon man, you gotta keep up!
			ENDIF
			IF get_in_counter_intro1_2 = 2
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EE //Keep up, CJ!
			ENDIF
			IF get_in_counter_intro1_2 = 3
				LOAD_MISSION_AUDIO 2 SOUND_INT1_EF //Don’t lose us, CJ!
			ENDIF
		ENDIF
	ENDIF

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1
	
	GOSUB load_any_audio

	PLAY_MISSION_AUDIO 2 
	IF switch_traffic_back_on = 2
		IF play_catch_up_audio_smoke = 1 //SMOKE
			IF get_in_counter_intro1_2 = 0   	
				PRINT_NOW ( INT1_EJ ) 3000 1 //What’s matter, CJ?  Can’t keep up with the fat man? 
			ENDIF
			IF get_in_counter_intro1_2 = 1	 
				PRINT_NOW ( INT1_ED ) 3000 1 //C’mon man, you gotta keep up!
			ENDIF								
			IF get_in_counter_intro1_2 = 2  	
				PRINT_NOW ( INT1_EE ) 3000 1 //Keep up, CJ! 
			ENDIF							 
			IF get_in_counter_intro1_2 = 3  	
				PRINT_NOW ( INT1_EF ) 3000 1 //Don’t lose us, CJ! 
			ENDIF			 	 
		ENDIF
	ENDIF

	GOSUB finish_any_audio

	get_in_counter_intro1_2 ++
	IF get_in_counter_intro1_2 > 3
		get_in_counter_intro1_2 = 0
	ENDIF

	TIMERA = 0

RETURN


load_any_audio:

	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0
	
		IF IS_CAR_DEAD players_bmx
		ENDIF

		IF IS_CHAR_DEAD bmx_gang[0]
			PRINT_NOW (INT2_F1) 10000 1 //~r~Smoke is dead!
			failed_mission = 1
			RETURN
		ENDIF

		IF IS_CHAR_DEAD bmx_gang[1]
			PRINT_NOW (INT2_F3) 10000 1 //~r~Ryder is dead!
			failed_mission = 1
			RETURN
		ENDIF
		
		IF NOT switch_traffic_back_on = 2
			IF IS_CHAR_DEAD bmx_gang[2]
				PRINT_NOW (INT2_F2) 10000 1 //~r~Sweet is dead!
				failed_mission = 1
				RETURN
			ENDIF
		ENDIF

		IF switch_traffic_back_on = 1
			IF NOT IS_CHAR_DEAD bmx_gang[2]
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1644.7734 -1051.3339 22.8984 10.0 12.0 20.0 FALSE
				AND LOCATE_CHAR_ANY_MEANS_3D bmx_gang[2] 1644.7734 -1051.3339 22.8984 10.0 12.0 20.0 FALSE
					RETURN
				ENDIF
			ENDIF
		ENDIF
		IF switch_traffic_back_on = 2	
			IF NOT IS_CHAR_DEAD bmx_gang[1]
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2493.82 -1669.91 12.8 4.0 3.5 4.0 TRUE //RACE BACK TO THE HOOD
				AND LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[1] 60.0 60.0 20.0 FALSE
					RETURN
				ENDIF
			ENDIF
		ENDIF

	ENDWHILE

RETURN


finish_any_audio:

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0

		IF IS_CAR_DEAD players_bmx
		ENDIF

		IF IS_CHAR_DEAD bmx_gang[0]
			PRINT_NOW (INT2_F1) 10000 1 //~r~Smoke is dead!
			failed_mission = 1
			RETURN
		ENDIF

		IF IS_CHAR_DEAD bmx_gang[1]
			PRINT_NOW (INT2_F3) 10000 1 //~r~Ryder is dead!
			failed_mission = 1
			RETURN
		ENDIF
		
		IF NOT switch_traffic_back_on = 2
			IF IS_CHAR_DEAD bmx_gang[2]
				PRINT_NOW (INT2_F2) 10000 1 //~r~Sweet is dead!
				failed_mission = 1
				RETURN
			ENDIF
		ENDIF
		IF switch_traffic_back_on = 1
			IF NOT IS_CHAR_DEAD bmx_gang[2]
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1644.7734 -1051.3339 22.8984 10.0 12.0 20.0 FALSE
				AND LOCATE_CHAR_ANY_MEANS_3D bmx_gang[2] 1644.7734 -1051.3339 22.8984 10.0 12.0 20.0 FALSE
					RETURN
				ENDIF
			ENDIF
		ENDIF
		IF switch_traffic_back_on = 2	
			IF NOT IS_CHAR_DEAD bmx_gang[1]
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2493.82 -1669.91 12.8 4.0 3.5 4.0 TRUE //RACE BACK TO THE HOOD
				AND LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer bmx_gang[1] 60.0 60.0 20.0 FALSE
					RETURN
				ENDIF
			ENDIF
		ENDIF

	ENDWHILE

RETURN


}



