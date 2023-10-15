MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Sweet 2 *******************************************
// ********************************* GUNS GUNS GUNS ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME sweet2

// Mission start stuff

GOSUB mission_start_sweet2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_sweet2_failed
ENDIF

GOSUB mission_cleanup_sweet2

MISSION_END
 
// Variables for mission

{
LVAR_INT wanted_tag shooting_range_blip tag_target_counter smoke_sweet2_seq_1 smoke_group_spilt_blip sweet2_chat_switch	played_smoke_samples
LVAR_INT how_much_ammo tag_index playergroup_sw2 smoke_car_blip smoke_car_blip_removed big_smokes_blip smoke_sitting_in_car	sweet2_cell_index_end 
LVAR_INT exp_barrel1 exp_barrel2 barrel2_exists smokes_decision gun_help	smoke_sweet2_seq_2 Return_Status played_the_timer get_in_counter_sweet2 
LVAR_INT smoke_sweet2_seq_3	smoke_idle_seq2	smoke_idle_seq big_smoke_group_spilt_blip smoke_shots_off emmet Return_Progress	flag_player_control_back
LVAR_INT sweet2_audio_chat[14] sweet2_index	sweet2_audio_is_playing	sweet2_cutscene_flag emmets_car	refresh_char_weapon	finished_mobile_phone_call
LVAR_INT new_visible_area print_help_sweet2	skip_emmets_cutscene
LVAR_FLOAT beer_bottleX[5] beer_bottleY[5] beer_bottleZ[5]
VAR_TEXT_LABEL $sweet2_chat[14]

// **************************************** Mission Start **********************************



sweet2_chat_switch:

SWITCH sweet2_chat_switch		   

	CONST_INT SWEET2_CHAT1 0
	CONST_INT SWEET2_CHAT2 1
	CONST_INT SWEET2_CHAT3 2
	CONST_INT SWEET2_CHAT4 3

	CASE SWEET2_CHAT1

		$sweet2_chat[0] = &SWE3_AA	//What happened to the Families, where’s the love gone?
		$sweet2_chat[1] = &SWE3_AB	//Shit happens.
		$sweet2_chat[2] = &SWE3_AC	//Seville wrongs Temple, Temple wrongs Grove.
		$sweet2_chat[3] = &SWE3_AD	//Bad blood leads to bad blood.
		$sweet2_chat[4] = &SWE3_AE	//Way of the world.
		$sweet2_chat[5] = &SWE3_AF	//Shit. The Families are a mess. Somebody should bring it all together.
		//$sweet2_chat[6] = &SWE3_AG	//Make those Ballas sit up and beg!
		$sweet2_chat[6] = &SWE3_AH	//I admire you, Carl. You’re a leader, a visionary.
		$sweet2_chat[7] = &SWE3_AJ	//Remember me as you get to the top, my friend.
		$sweet2_chat[8] = &SWE3_AK	//Give it a rest, smoke. You’re full of shit.

		sweet2_audio_chat[0] = SOUND_SWE3_AA	//What happened to the Families, where’s the love gone?
		sweet2_audio_chat[1] = SOUND_SWE3_AB	//Shit happens.
		sweet2_audio_chat[2] = SOUND_SWE3_AC	//Seville wrongs Temple, Temple wrongs Grove.
		sweet2_audio_chat[3] = SOUND_SWE3_AD	//Bad blood leads to bad blood.
		sweet2_audio_chat[4] = SOUND_SWE3_AE	//Way of the world.
		sweet2_audio_chat[5] = SOUND_SWE3_AF	//Shit. The Families are a mess. Somebody should bring it all together.
		//sweet2_audio_chat[6] = SOUND_SWE3_AG	//Make those Ballas sit up and beg!
		sweet2_audio_chat[6] = SOUND_SWE3_AH	//I admire you, Carl. You’re a leader, a visionary.
		sweet2_audio_chat[7] = SOUND_SWE3_AJ	//Remember me as you get to the top, my friend.
		sweet2_audio_chat[8] = SOUND_SWE3_AK	//Give it a rest, smoke. You’re full of shit.

		sweet2_cell_index_end = 8

	BREAK

	CASE SWEET2_CHAT2

		$sweet2_chat[0] = &SWE3_GA	//Yo. You’re killah, dude, ice cold.
		$sweet2_chat[1] = &SWE3_GB	//But remeber; real strength comes from within, my brother.
		$sweet2_chat[2] = &SWE3_GC	//Word.
		$sweet2_chat[3] = &SWE3_GD	//Guess Liberty didn’t soften you none.
		$sweet2_chat[4] = &SWE3_GE	//Let’s split.
		$sweet2_chat[5] = &SWE3_GF	//See y’round Emmet.
		$sweet2_chat[6] = &SWE3_GG	//I’m 100% behind you on this, boys.
		$sweet2_chat[7] = &SWE3_GH	//You didn’t get it from me, though. Remember that.
		$sweet2_chat[8] = &SWE3_GJ	//And remember this. Emmet is the place for guns!
		$sweet2_chat[9] = &SWE3_GK	//I always got high quality merchandise!
		$sweet2_chat[10] = &SWE3_GL	//I been proudly serving the community for 30 years!
		$sweet2_chat[11] = &SWE3_GM	//Crazy old fool. You drive
		$sweet2_chat[12] = &SWE3_GN	//I seen newer cannons than this strap. Where to?
		$sweet2_chat[13] = &SWE3_GO	//I’m real tired, dude, man, drop me off at my place.

		sweet2_audio_chat[0] = SOUND_SWE3_GA //Yo. You’re killah, dude, ice cold.
		sweet2_audio_chat[1] = SOUND_SWE3_GB //But remeber; real strength comes from within, my brother.
		sweet2_audio_chat[2] = SOUND_SWE3_GC //Word.
		sweet2_audio_chat[3] = SOUND_SWE3_GD //Guess Liberty didn’t soften you none.
		sweet2_audio_chat[4] = SOUND_SWE3_GE //Let’s split.
		sweet2_audio_chat[5] = SOUND_SWE3_GF //See y’round Emmet.
		sweet2_audio_chat[6] = SOUND_SWE3_GG //I’m 100% behind you on this, boys.
		sweet2_audio_chat[7] = SOUND_SWE3_GH //You didn’t get it from me, though. Remember that.
		sweet2_audio_chat[8] = SOUND_SWE3_GJ //And remember this. Emmet is the place for guns!
		sweet2_audio_chat[9] = SOUND_SWE3_GK //I always got high quality merchandise!
		sweet2_audio_chat[10] = SOUND_SWE3_GL //I been proudly serving the community for 30 years!
		sweet2_audio_chat[11] = SOUND_SWE3_GM //Crazy old fool. You drive
		sweet2_audio_chat[12] = SOUND_SWE3_GN //I seen newer cannons than this strap. Where to?
		sweet2_audio_chat[13] = SOUND_SWE3_GO //I’m real tired, dude, man, drop me off at my place.

		sweet2_cell_index_end = 13

	BREAK

	CASE SWEET2_CHAT3
			
		$sweet2_chat[0] = &SWE3_HA	//What’s going on here man? Shit seems pretty fucked up?
		$sweet2_chat[1] = &SWE3_HB	//People have to pen their eyes and their hearts, CJ.
		$sweet2_chat[2] = &SWE3_HC	//What are you talking about?
		$sweet2_chat[3] = &SWE3_HD	//I’m talking about the choices all men face, brother. 
		$sweet2_chat[4] = &SWE3_HE	//Sometimes, they seem real, but there’s no choice at all.
		$sweet2_chat[5] = &SWE3_HF	//You’re still talking shit. You ain’t changed a bit.
		$sweet2_chat[6] = &SWE3_HG	//Me? No, never.
			
		sweet2_audio_chat[0] = SOUND_SWE3_HA //What’s going on here man? Shit seems pretty fucked up?
		sweet2_audio_chat[1] = SOUND_SWE3_HB //People have to pen their eyes and their hearts, CJ.
		sweet2_audio_chat[2] = SOUND_SWE3_HC //What are you talking about?
		sweet2_audio_chat[3] = SOUND_SWE3_HD //I’m talking about the choices all men face, brother. 
		sweet2_audio_chat[4] = SOUND_SWE3_HE //Sometimes, they seem real, but there’s no choice at all.
		sweet2_audio_chat[5] = SOUND_SWE3_HF //You’re still talking shit. You ain’t changed a bit.
		sweet2_audio_chat[6] = SOUND_SWE3_HG //Me? No, never.
	
		sweet2_cell_index_end = 6

	BREAK

	CASE SWEET2_CHAT4

		$sweet2_chat[0] = &MSWE07A //Yo mo fo!
		$sweet2_chat[1] = &MSWE07B //I thought you was representing?
		$sweet2_chat[2] = &MSWE07C //What?
		$sweet2_chat[3] = &MSWE07D //Correct me if I’m wrong, but I thought you was rolling with Grove Street again.
		$sweet2_chat[4] = &MSWE07E //I am, man, I am! 
		$sweet2_chat[5] = &MSWE07F //I ain’t seen you in yo’colors yet!
		$sweet2_chat[6] = &MSWE07G //You gotta fly the flag, bro, 
		$sweet2_chat[7] = &MSWE07H //these freaks ain’t gonna respect some cat if he ain’t representing!
		$sweet2_chat[8] = &MSWE07J //Ok, man, I just haven’t got around to it yet!
		$sweet2_chat[9] = &MSWE07K //There’s a Binco around the corner from the gym in Ganton, go get yourself some greens!

		sweet2_audio_chat[0] = SOUND_MSWE07A //Yo mo fo!
		sweet2_audio_chat[1] = SOUND_MSWE07B //I thought you was representing?	
		sweet2_audio_chat[2] = SOUND_MSWE07C //What?
		sweet2_audio_chat[3] = SOUND_MSWE07D //Correct me if I’m wrong, but I thought you was rolling with Grove Street again.
		sweet2_audio_chat[4] = SOUND_MSWE07E //I am, man, I am! 
		sweet2_audio_chat[5] = SOUND_MSWE07F //I ain’t seen you in yo’colors yet!
		sweet2_audio_chat[6] = SOUND_MSWE07G //You gotta fly the flag, bro, 
		sweet2_audio_chat[7] = SOUND_MSWE07H //these freaks ain’t gonna respect some cat if he ain’t representing!
		sweet2_audio_chat[8] = SOUND_MSWE07J //Ok, man, I just haven’t got around to it yet!
		sweet2_audio_chat[9] = SOUND_MSWE07K //There’s a Binco around the corner from the gym in Ganton, go get yourself some greens!

		sweet2_cell_index_end = 9

	BREAK
			
ENDSWITCH

RETURN


mission_start_sweet2:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1
tag_target_counter = 0
tag_index = 0
smoke_group_spilt_blip = 0
barrel2_exists = 0
gun_help = 0
smoke_shots_off = 0
smoke_car_blip_removed = 0
smoke_sitting_in_car = 0
played_the_timer = 0
get_in_counter_sweet2 = 0

beer_bottleX[0] = 2440.58 //on dumpster
beer_bottleY[0] = -1979.89 
beer_bottleZ[0]	= 14.1

beer_bottleX[1] = 2440.14 //on washing machine
beer_bottleY[1] = -1976.5 
beer_bottleZ[1]	= 13.4

beer_bottleX[2] = 2440.95 //on dumpster2
beer_bottleY[2] = -1973.76 
beer_bottleZ[2]	= 14.2

beer_bottleX[3] = 2448.97 //on fucked car
beer_bottleY[3] = -1973.29 
beer_bottleZ[3]	= 13.35

beer_bottleX[4] = 2444.35  //on crate
beer_bottleY[4] = -1970.61 
beer_bottleZ[4]	= 13.8

WAIT 0

LOAD_MISSION_TEXT SWEET2 

// ****************************************START OF CUTSCENE********************************

SET_AREA_VISIBLE 1

LOAD_CUTSCENE SWEET3A
 
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

// *****************************************END OF CUTSCENE*********************************


FORCE_WEATHER WEATHER_SUNNY_LA

LOAD_SPECIAL_CHARACTER 10 smoke
REQUEST_MODEL glendale

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 10
OR NOT HAS_MODEL_LOADED glendale
	WAIT 0
ENDWHILE

//2504.4651 -1671.0807 12.3359 87.5066

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY smokes_decision

SWITCH_CAR_GENERATOR gen_car7 0
CLEAR_AREA 2506.23 -1675.75 12.37 6.0 TRUE

CUSTOM_PLATE_FOR_NEXT_CAR GLENDALE &_A2tmfK_
CREATE_CAR GLENDALE 2506.23 -1675.75 12.37 big_smoke_car
CHANGE_CAR_COLOUR big_smoke_car 98 14
SET_CAR_HEADING big_smoke_car 148.0
SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL

//CREATE_CHAR_AS_PASSENGER big_smoke_car PEDTYPE_CIVMALE SPECIAL01 0 big_smoke

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_GANG_GROVE PEDTYPE_MISSION1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_GANG_GROVE
 
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL10 2505.8691 -1672.7229 12.3778 big_smoke
SET_CHAR_HEADING big_smoke 160.0 
SET_CHAR_HEALTH big_smoke 500
SET_CHAR_NEVER_TARGETTED big_smoke TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS big_smoke FALSE
SET_CHAR_WEAPON_SKILL big_smoke WEAPONSKILL_STD
SET_CHAR_ACCURACY big_smoke 100
SET_CHAR_SHOOT_RATE	big_smoke 30
SET_CHAR_ONLY_DAMAGED_BY_PLAYER big_smoke TRUE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE


SET_CHAR_DECISION_MAKER big_smoke smokes_decision
	   
SET_CHAR_COORDINATES scplayer 2515.28 -1673.44 12.73
SET_CHAR_HEADING scplayer 81.20

LOAD_SCENE 2516.54 -1671.20 12.87

DO_FADE 1000 FADE_IN

SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

PRINT_NOW ( SWE2_A ) 10000 1 // Go to Emmets house to get a gun. 

SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
TASK_ENTER_CAR_AS_PASSENGER big_smoke big_smoke_car 5000 0
SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED big_smoke TRUE
ADD_BLIP_FOR_CAR big_smoke_car smoke_car_blip
SET_BLIP_AS_FRIENDLY smoke_car_blip TRUE
ADD_BLIP_FOR_COORD 2453.07 -2003.96 12.56 shooting_range_blip
REMOVE_BLIP shooting_range_blip

blob_flag = 0

//GOTO skip_to_end_cut_scene

sweet2_index = 0
sweet2_audio_is_playing = 0
sweet2_cutscene_flag = 0
sweet2_chat_switch = SWEET2_CHAT1
GOSUB sweet2_chat_switch

TIMERB = 0
played_the_timer = 0


//GOTO skip_hereeeeeeeeeeeeeeee

WHILE NOT LOCATE_CAR_3D big_smoke_car 2453.07 -2003.96 12.56 4.0 4.0 4.0 blob_flag //Emmets
OR NOT IS_CHAR_SITTING_IN_CAR big_smoke big_smoke_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS	big_smoke_car
	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_sweet2_passed
	ENDIF

	IF NOT IS_CHAR_DEAD	big_smoke
		IF NOT IS_CAR_DEAD big_smoke_car
			IF IS_CHAR_IN_CAR scplayer big_smoke_car
				IF smoke_car_blip_removed = 0
					REMOVE_BLIP smoke_car_blip
					REMOVE_BLIP shooting_range_blip
					ADD_BLIP_FOR_COORD 2453.07 -2003.96 12.56 shooting_range_blip
					blob_flag = 1
					smoke_car_blip_removed = 1
				ENDIF

				//IF played_the_timer = 0
					IF TIMERB > 5000	
						GOSUB load_and_play_audio_sweet2
						//played_the_timer = 1
					ENDIF
				//ELSE
					//GOSUB load_and_play_audio_sweet2
				//ENDIF

			ELSE
				IF smoke_car_blip_removed = 1
					REMOVE_BLIP shooting_range_blip
					REMOVE_BLIP smoke_car_blip
					ADD_BLIP_FOR_CAR big_smoke_car smoke_car_blip
					SET_BLIP_AS_FRIENDLY smoke_car_blip TRUE
					GOSUB get_back_in_the_car_sweet2
					PRINT_NOW (SWE2_L) 6000 1 //Get back in the car
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
					STOP_CHAR_FACIAL_TALK scplayer
					blob_flag = 0
					smoke_car_blip_removed = 0
				ENDIF
			ENDIF
		ELSE
			PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			GOTO mission_sweet2_failed
		ENDIF
	ELSE
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF

	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD	big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON

SET_FIXED_CAMERA_POSITION 2452.9490 -2011.8243 16.3096 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2452.9805 -2010.9032 15.9217 JUMP_CUT


IF NOT IS_CHAR_DEAD big_smoke
	IF NOT IS_CAR_DEAD big_smoke_car
		//REMOVE_CHAR_FROM_GROUP big_smoke
		
		OPEN_SEQUENCE_TASK smoke_sweet2_seq_1 //Flat1
			TASK_LEAVE_CAR -1 big_smoke_car
			FLUSH_ROUTE
			EXTEND_ROUTE 2453.5151 -1980.7709 12.5547
			EXTEND_ROUTE 2450.4863 -1981.6593 12.5547
			TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
		CLOSE_SEQUENCE_TASK smoke_sweet2_seq_1

		PERFORM_SEQUENCE_TASK big_smoke smoke_sweet2_seq_1
		CLEAR_SEQUENCE_TASK	smoke_sweet2_seq_1
		
		WAIT 800
		IF NOT IS_CAR_DEAD big_smoke_car
			TASK_LEAVE_CAR scplayer big_smoke_car
			TASK_GO_STRAIGHT_TO_COORD scplayer 2453.29 -1983.71 12.54 PEDMOVE_WALK 5000
		ENDIF
	ENDIF
ENDIF

WAIT 2000

GOSUB set_gang_density_to_zero

// ****************************************START OF CUTSCENE********************************


DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_SCRIPT_LIMIT_TO_GANG_SIZE 0

CLEAR_AREA 2448.7344 -1976.1932 12.5469 200.0 FALSE

//SET_AREA_VISIBLE 1
MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
//SWITCH_STREAMING OFF

LOAD_CUTSCENE SWEET3B
 
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

//SET_AREA_VISIBLE 0

// *****************************************END OF CUTSCENE*********************************

LOAD_SPECIAL_CHARACTER 1 emmet
REQUEST_MODEL DYN_WINE_BIG 
//REQUEST_MODEL barrel4
REQUEST_ANIMATION GANGS
REQUEST_ANIMATION CRACK
REQUEST_ANIMATION COLT45
REQUEST_ANIMATION FAT
REQUEST_MODEL Colt45 
REQUEST_MODEL tampa


LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_MODEL_LOADED DYN_WINE_BIG 
OR NOT HAS_ANIMATION_LOADED GANGS
OR NOT HAS_ANIMATION_LOADED CRACK
OR NOT HAS_MODEL_LOADED Colt45
OR NOT HAS_ANIMATION_LOADED COLT45
	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED tampa
OR NOT HAS_ANIMATION_LOADED FAT
	WAIT 0

ENDWHILE

CLEAR_AREA 2446.49 -1966.47 12.54 4.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR TAMPA &_FELTCH_
CREATE_CAR TAMPA 2446.49 -1965.0 12.54 emmets_car
SET_CAR_HEADING emmets_car 101.85
//SET_CAN_BURST_CAR_TYRES emmets_car FALSE
LOCK_CAR_DOORS emmets_car CARLOCK_LOCKED
SET_CAR_PROOFS emmets_car TRUE TRUE TRUE TRUE TRUE


CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2451.8340 -1976.8108 12.5469 emmet
SET_CHAR_HEADING emmet 75.6292 
SET_CHAR_HEALTH emmet 600
SET_CHAR_NEVER_TARGETTED emmet TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS emmet FALSE

//CREATE_OBJECT barrel4 2441.9578 -1973.0739 12.5469 exp_barrel1   
//CREATE_OBJECT barrel4 2444.3755 -1979.8214 12.5469 exp_barrel2   
//SET_OBJECT_COLLISION exp_barrel1 FALSE
//SET_OBJECT_COLLISION exp_barrel2 FALSE

REMOVE_BLIP shooting_range_blip

GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL how_much_ammo

GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL 30000
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL

IF NOT IS_CHAR_DEAD	big_smoke
	GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_PISTOL 10000
	SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_PISTOL
ENDIF


// **********************************************SHOOT BOTTLES ROUND 1*****************************************************
	CLEAR_PRINTS
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	SET_NEAR_CLIP 0.2

	LOAD_MISSION_AUDIO 1 SOUND_SWE3_BC	//Die, little glass Ballas fool!			

	DO_FADE 1000 FADE_IN

	IF NOT IS_CHAR_DEAD big_smoke
		CLEAR_CHAR_TASKS_IMMEDIATELY big_smoke
		SET_CHAR_COORDINATES big_smoke 2450.4653 -1980.5774 12.5469 
		SET_CHAR_HEADING big_smoke 79.2792
	ENDIF

	CREATE_OBJECT DYN_WINE_BIG beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] tag_target[0] //on dumpster
	//SET_OBJECT_DYNAMIC tag_target[0] FALSE
	SET_OBJECT_HEADING tag_target[0] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[0] TRUE
	
	IF NOT IS_CAR_DEAD big_smoke_car
		SET_CAR_COORDINATES big_smoke_car 2452.6460 -2003.8763 12.5540 
		SET_CAR_HEADING big_smoke_car 100.2244
	ENDIF

	SET_FIXED_CAMERA_POSITION 2445.7966 -1976.5854 14.8864 0.0 0.0 0.0 //Shot of bottle.
	POINT_CAMERA_AT_POINT 2444.9832 -1977.1511 14.7514 JUMP_CUT

	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2453.8206 -1978.7771 12.5469 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2453.8206 -1978.7771 12.5469
	ENDIF

	SET_CHAR_HEADING scplayer 89.5159

	smoke_group_spilt_blip = 0

	WAIT 2500

	SET_FIXED_CAMERA_POSITION 2451.3645 -1980.3950 14.2880 0.0 0.0 0.0 //Smoke.
	POINT_CAMERA_AT_POINT 2450.3652 -1980.3633 14.2636 JUMP_CUT


smoke_shots_off = 0
/*
OPEN_SEQUENCE_TASK smoke_idle_seq 
	//TASK_ACHIEVE_HEADING -1 77.0
	SET_SEQUENCE_TO_REPEAT smoke_idle_seq 1
CLOSE_SEQUENCE_TASK smoke_idle_seq
*/

OPEN_SEQUENCE_TASK smoke_sweet2_seq_2 
	TASK_SHOOT_AT_COORD -1 beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] 1300
	TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
	TASK_PLAY_ANIM -1 Bbalbat_Idle_02 CRACK 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
	TASK_PLAY_ANIM -1 prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
CLOSE_SEQUENCE_TASK smoke_sweet2_seq_2

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0

		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 1
	PRINT_NOW ( SWE3_BC ) 10000 1 //Die, little glass Ballas fool! 
	IF NOT IS_CHAR_DEAD	big_smoke
		//TASK_PLAY_ANIM big_smoke colt45_fire COLT45 4.0 FALSE FALSE FALSE TRUE 2000
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
		START_CHAR_FACIAL_TALK big_smoke 3000
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		
		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

IF NOT IS_CHAR_DEAD	big_smoke
	CLEAR_PRINTS
	STOP_CHAR_FACIAL_TALK big_smoke
	PERFORM_SEQUENCE_TASK big_smoke smoke_sweet2_seq_2
	CLEAR_SEQUENCE_TASK	smoke_sweet2_seq_2
ENDIF

refresh_char_weapon = 0

	WHILE NOT tag_target_counter = 1
		WAIT 0
				
		IF DOES_OBJECT_EXIST tag_target[0]
			IF IS_PLAYER_TARGETTING_OBJECT player1 tag_target[0] 
				GOSUB chars_look_at_targets
   			ELSE
				GOSUB stop_chars_look_at_targets
			ENDIF

			IF HAS_OBJECT_BEEN_DAMAGED tag_target[0]
				MAKE_OBJECT_TARGETTABLE tag_target[0] FALSE
				DELETE_OBJECT tag_target[0]
				tag_target_counter ++
			ENDIF
		ENDIF
		
		IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer 2448.9602 -1973.5446 13.3074 13.0 13.0 10.0 FALSE

			IF smoke_group_spilt_blip = 0
				REMOVE_BLIP shooting_range_blip
				ADD_BLIP_FOR_COORD 2451.95 -1978.93 12.55 shooting_range_blip
				CLEAR_HELP
				PRINT_NOW ( SWE2_J ) 10000 1 //GO BACK!
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
					SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 10
				ENDIF
				smoke_group_spilt_blip = 1
			ENDIF
		ELSE

			IF refresh_char_weapon = 0
				IF NOT IS_CHAR_DEAD	big_smoke
					REMOVE_ALL_CHAR_WEAPONS big_smoke
					GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_PISTOL 10000
					SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_PISTOL
					refresh_char_weapon = 1
				ENDIF
			ENDIF

			IF smoke_group_spilt_blip = 1
				REMOVE_BLIP shooting_range_blip
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
					SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 30000
				ENDIF
				
				PRINT_HELP_FOREVER HOOD2A  

				PRINT_NOW ( SWE2_G ) 10000 1 // Shoot the bottle
				smoke_group_spilt_blip = 0
			ENDIF
		ENDIF

		IF smoke_shots_off = 0
			IF NOT IS_CHAR_DEAD	big_smoke
				GET_SCRIPT_TASK_STATUS big_smoke TASK_PLAY_ANIM Return_Status
				GET_SEQUENCE_PROGRESS big_smoke Return_Progress

				IF Return_Progress = 0
					SET_NEAR_CLIP 0.2
					SET_FIXED_CAMERA_POSITION 2439.8589 -1979.6781 14.4278 0.0 0.0 0.0 //Shot of bottle.
					POINT_CAMERA_AT_POINT 2440.8330 -1979.7941 14.2350 JUMP_CUT
					WAIT 500
					IF DOES_OBJECT_EXIST tag_target[0]
						BREAK_OBJECT tag_target[0] TRUE
					ENDIF
					WAIT 1000
					SET_CHAR_COORDINATES scplayer 2450.7402 -1978.3749 12.5469 
					SET_CHAR_HEADING scplayer 89.5159
					IF NOT IS_CHAR_DEAD emmet
						SET_CHAR_COORDINATES emmet 2451.8340 -1976.8108 12.5469 
						SET_CHAR_HEADING emmet 75.6292
					ENDIF
					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					tag_target_counter = 0
					DELETE_OBJECT tag_target[0]
					CREATE_OBJECT DYN_WINE_BIG  2440.58 -1979.89 14.2 tag_target[0]
					//SET_OBJECT_DYNAMIC tag_target[0] FALSE
					SET_OBJECT_HEADING tag_target[0] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[0] TRUE

					PRINT_HELP_FOREVER HOOD2A  

					smoke_shots_off = 1

				ENDIF
			ENDIF
		ENDIF

		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CAR_DEAD big_smoke_car
			PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

	//INCREMENT_FLOAT_STAT SHOOTING_SKILL 0.5

// **********************************************SHOOT BOTTLES ROUND 2*****************************************************

/*
[SWE3_DB]	He’s got killah all over his soul!
[SWE3_DD]	Ballas fools gonna be sorry!
[SWE3_DE]	That’s it boy!
[SWE3_DF]	That's good shootin'!
*/
	LOAD_MISSION_AUDIO 1 SOUND_SWE3_DC //Ah, Beverly’d be so proud of you, boy.

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0

		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 1
	PRINT_NOW ( SWE3_DC ) 10000 1 // Yeah, that’s real Grove Street style, now! ******************************************************************
	IF NOT IS_CHAR_DEAD	emmet
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH emmet TRUE
		START_CHAR_FACIAL_TALK emmet 3000
		TASK_PLAY_ANIM emmet prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE TRUE -1
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		
		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF
		
		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD	emmet
		CLEAR_CHAR_TASKS emmet
		CLEAR_PRINTS
		STOP_CHAR_FACIAL_TALK emmet
	ENDIF

	SET_PLAYER_CONTROL player1 OFF
	CLEAR_WANTED_LEVEL player1
	LOAD_MISSION_AUDIO 2 SOUND_SWE3_BA	//I’m the best there ever was!			
	
	WAIT 1000

	tag_target_counter = 0

	WAIT 1000

	SWITCH_WIDESCREEN ON
	SET_NEAR_CLIP 0.2

	CLEAR_HELP

	IF NOT IS_CAR_DEAD big_smoke_car
		SET_CAR_COORDINATES big_smoke_car 2452.6460 -2003.8763 12.5540 
		SET_CAR_HEADING big_smoke_car 100.2244
	ENDIF

	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] tag_target[0] //on dumpster
	//SET_OBJECT_DYNAMIC tag_target[0] FALSE
	SET_OBJECT_HEADING tag_target[0] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[0] TRUE

	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[1] beer_bottleY[1] beer_bottleZ[1] tag_target[1] //on washing machine
	//SET_OBJECT_DYNAMIC tag_target[1] FALSE
	SET_OBJECT_HEADING tag_target[1] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[1] TRUE

	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[2] beer_bottleY[2] beer_bottleZ[2] tag_target[2] //on dumpster2
	//SET_OBJECT_DYNAMIC tag_target[2] FALSE
	SET_OBJECT_HEADING tag_target[2] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[2] TRUE
	
	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2453.8206 -1978.7771 12.5469 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2453.8206 -1978.7771 12.5469
	ENDIF

	SET_CHAR_HEADING scplayer 89.5159

	SET_FIXED_CAMERA_POSITION 2447.3789 -1977.0619 15.2851 0.0 0.0 0.0	//Shot of bottle.
	POINT_CAMERA_AT_POINT 2446.3914 -1977.0115 15.1366 JUMP_CUT

	WAIT 2500

	CAMERA_RESET_NEW_SCRIPTABLES
	CAMERA_PERSIST_TRACK TRUE                   
	CAMERA_PERSIST_POS TRUE                       
	CAMERA_SET_VECTOR_MOVE 2451.3879 -1981.1035 14.5206 2451.7380 -1980.5852 14.5765 8000 TRUE
	CAMERA_SET_VECTOR_TRACK 2450.4265 -1980.9240 14.3127 2450.8577 -1980.1874 14.3180 8000 TRUE

	//SET_FIXED_CAMERA_POSITION 2451.1123 -1981.6794 14.4021 0.0 0.0 0.0 //Smoke.
	//POINT_CAMERA_AT_POINT 2450.2903 -1981.1359 14.2322 JUMP_CUT

	SET_CHAR_HEADING scplayer 65.0
	smoke_group_spilt_blip = 0
	
smoke_shots_off = 0

//CLEAR_SEQUENCE_TASK	smoke_idle_seq

OPEN_SEQUENCE_TASK smoke_idle_seq 
	//TASK_ACHIEVE_HEADING -1 77.0
	TASK_PLAY_ANIM -1 Bbalbat_Idle_02 CRACK 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
	TASK_PLAY_ANIM -1 prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
	TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
	SET_SEQUENCE_TO_REPEAT smoke_idle_seq 1
CLOSE_SEQUENCE_TASK smoke_idle_seq

OPEN_SEQUENCE_TASK smoke_sweet2_seq_2 
	//TASK_GO_STRAIGHT_TO_COORD -1 2450.15 -1979.66 12.55 PEDMOVE_WALK 4000
	//TASK_ACHIEVE_HEADING -1 80.0
	//TASK_AIM_GUN_AT_COORD -1 beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] 1000
	TASK_PLAY_ANIM -1 colt45_fire COLT45 4.0 FALSE FALSE FALSE TRUE 2000
	TASK_SHOOT_AT_COORD -1 beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] 1300
	TASK_SHOOT_AT_COORD -1 beer_bottleX[1] beer_bottleY[1] beer_bottleZ[1] 1300
	TASK_SHOOT_AT_COORD -1 beer_bottleX[2] beer_bottleY[2] beer_bottleZ[2] 1300
	//TASK_GO_STRAIGHT_TO_COORD -1 2450.48 -1981.65 12.55 PEDMOVE_WALK 4000
	PERFORM_SEQUENCE_TASK -1 smoke_idle_seq
CLOSE_SEQUENCE_TASK smoke_sweet2_seq_2


IF NOT IS_CHAR_DEAD	big_smoke
	PERFORM_SEQUENCE_TASK big_smoke smoke_sweet2_seq_2
	CLEAR_SEQUENCE_TASK	smoke_sweet2_seq_2
ENDIF

refresh_char_weapon = 0
							   
	WHILE NOT tag_target_counter = 3
		WAIT 0
		
	   	IF DOES_OBJECT_EXIST tag_target[tag_index]
			IF IS_PLAYER_TARGETTING_OBJECT player1 tag_target[tag_index] 
				GOSUB chars_look_at_targets
   			ELSE	
				GOSUB stop_chars_look_at_targets
			ENDIF

			IF HAS_OBJECT_BEEN_DAMAGED tag_target[tag_index]
				MAKE_OBJECT_TARGETTABLE tag_target[tag_index] FALSE
				DELETE_OBJECT tag_target[tag_index]
				//INCREMENT_FLOAT_STAT SHOOTING_SKILL 0.5
				tag_target_counter ++
			ENDIF
		ENDIF

		IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer 2448.9602 -1973.5446 13.3074 13.0 13.0 10.0 FALSE
			
			IF smoke_group_spilt_blip = 0
				REMOVE_BLIP shooting_range_blip
				ADD_BLIP_FOR_COORD 2451.95 -1978.93 12.55 shooting_range_blip
				CLEAR_HELP
				PRINT_NOW ( SWE2_J ) 10000 1 //GO BACK!
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
					SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 10
				ENDIF
				smoke_group_spilt_blip = 1
			ENDIF
		ELSE
			
			IF refresh_char_weapon = 0
				IF NOT IS_CHAR_DEAD	big_smoke
					REMOVE_ALL_CHAR_WEAPONS big_smoke
					GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_PISTOL 10000
					SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_PISTOL
					refresh_char_weapon = 1
				ENDIF
			ENDIF

			IF smoke_group_spilt_blip = 1
				REMOVE_BLIP shooting_range_blip
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
					SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 30000
				ENDIF
				
				PRINT_HELP_FOREVER HOOD2B  
				
				PRINT_NOW ( SWE2_H ) 10000 1 // Shoot the bottles
				smoke_group_spilt_blip = 0
			ENDIF
		ENDIF

		IF smoke_shots_off = 0
			IF NOT IS_CHAR_DEAD	big_smoke
				GET_SCRIPT_TASK_STATUS big_smoke TASK_PLAY_ANIM Return_Status

				GET_SEQUENCE_PROGRESS big_smoke Return_Progress

				IF Return_Progress = 1
					//SET_FIXED_CAMERA_POSITION 2452.8352 -1981.0620 14.5395 0.0 0.0 0.0 //Shot of bottle.
					//POINT_CAMERA_AT_POINT 2451.9146 -1980.6940 14.4089 JUMP_CUT
					WAIT 700
					IF DOES_OBJECT_EXIST tag_target[0]
						BREAK_OBJECT tag_target[0] TRUE
					ENDIF
				ENDIF

				IF Return_Progress = 2
					//SET_FIXED_CAMERA_POSITION 2452.8352 -1981.0620 14.5395 0.0 0.0 0.0 //Shot of bottle.
					//POINT_CAMERA_AT_POINT 2451.9146 -1980.6940 14.4089 JUMP_CUT
					WAIT 500
					IF DOES_OBJECT_EXIST tag_target[1]
						BREAK_OBJECT tag_target[1] TRUE
					ENDIF
				ENDIF
					
				IF Return_Progress = 3
					WHILE NOT HAS_MISSION_AUDIO_LOADED 2
						WAIT 0

						IF IS_CHAR_DEAD	big_smoke
							PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
							GOTO mission_sweet2_failed
						ENDIF

					ENDWHILE

					PLAY_MISSION_AUDIO 2
					PRINT_NOW ( SWE3_BA ) 10000 1 //I’m the best there ever was! 
					IF NOT IS_CHAR_DEAD	big_smoke
						//TASK_PLAY_ANIM big_smoke colt45_fire COLT45 4.0 FALSE FALSE FALSE TRUE 2000
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
						START_CHAR_FACIAL_TALK big_smoke 3000
					ENDIF
					WAIT 500
					IF DOES_OBJECT_EXIST tag_target[2]
						BREAK_OBJECT tag_target[2] TRUE
					ENDIF
					WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
						WAIT 0
						
						IF IS_CHAR_DEAD	big_smoke
							PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
							GOTO mission_sweet2_failed
						ENDIF

					ENDWHILE
					IF NOT IS_CHAR_DEAD	big_smoke
						CLEAR_PRINTS
						STOP_CHAR_FACIAL_TALK big_smoke
					ENDIF
					WAIT 1000
					SET_CHAR_COORDINATES scplayer 2450.7402 -1978.3749 12.5469 
					SET_CHAR_HEADING scplayer 89.5159
					IF NOT IS_CHAR_DEAD emmet
						SET_CHAR_COORDINATES emmet 2451.8340 -1976.8108 12.5469 
						SET_CHAR_HEADING emmet 75.6292
					ENDIF
					CAMERA_RESET_NEW_SCRIPTABLES
					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
					
					PRINT_HELP_FOREVER HOOD2B  
   				    
					tag_target_counter = 0
					
					IF DOES_OBJECT_EXIST tag_target[0]
						MAKE_OBJECT_TARGETTABLE tag_target[0] FALSE
					ENDIF
					DELETE_OBJECT tag_target[0]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] tag_target[0] //on dumpster
					//SET_OBJECT_DYNAMIC tag_target[0] FALSE
					SET_OBJECT_HEADING tag_target[0] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[0] TRUE

					IF DOES_OBJECT_EXIST tag_target[1]
						MAKE_OBJECT_TARGETTABLE tag_target[1] FALSE
					ENDIF
					DELETE_OBJECT tag_target[1]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[1] beer_bottleY[1] beer_bottleZ[1] tag_target[1] //on washing machine
					//SET_OBJECT_DYNAMIC tag_target[1] FALSE
					SET_OBJECT_HEADING tag_target[1] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[1] TRUE

					IF DOES_OBJECT_EXIST tag_target[2]
						MAKE_OBJECT_TARGETTABLE tag_target[2] FALSE
					ENDIF
					DELETE_OBJECT tag_target[2]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[2] beer_bottleY[2] beer_bottleZ[2] tag_target[2] //on dumpster2
					//SET_OBJECT_DYNAMIC tag_target[2] FALSE
					SET_OBJECT_HEADING tag_target[2] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[2] TRUE
					smoke_shots_off = 1
				ENDIF
			ENDIF
		ENDIF

		tag_index ++
		IF tag_index >= 3
			tag_index = 0
		ENDIF
		
		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CAR_DEAD big_smoke_car
			PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

// **********************************************SHOOT BOTTLES ROUND 3*****************************************************

	LOAD_MISSION_AUDIO 1 SOUND_SWE3_DA // Yeah, that’s real Grove Street style, now!

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0

		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 1
	PRINT_NOW ( SWE3_DA ) 10000 1 // Yeah, that’s real Grove Street style, now! ******************************************************************
	IF NOT IS_CHAR_DEAD	emmet
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH emmet TRUE
		START_CHAR_FACIAL_TALK emmet 3000
		TASK_PLAY_ANIM emmet prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE TRUE -1
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		
		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF
		
		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD	emmet
		CLEAR_CHAR_TASKS emmet
		CLEAR_PRINTS
		STOP_CHAR_FACIAL_TALK emmet
	ENDIF


	SET_PLAYER_CONTROL player1 OFF
	CLEAR_WANTED_LEVEL player1

	LOAD_MISSION_AUDIO 1 SOUND_SWE3_BD	//Cap your ass!
	LOAD_MISSION_AUDIO 2 SOUND_SWE3_BE	//And your ass!

	WAIT 1000

	tag_target_counter = 0

	WAIT 1000

	SWITCH_WIDESCREEN ON
	SET_NEAR_CLIP 0.2

	CLEAR_HELP

	IF NOT IS_CAR_DEAD big_smoke_car
		SET_CAR_COORDINATES big_smoke_car 2452.6460 -2003.8763 12.5540 
		SET_CAR_HEADING big_smoke_car 100.2244
	ENDIF

	beer_bottleX[0] = 2440.58 //on dumpster
	beer_bottleY[0] = -1979.89 
	beer_bottleZ[0]	= 14.1

	beer_bottleX[1] = 2440.14 //on small crate
	beer_bottleY[1] = -1976.5 
	beer_bottleZ[1]	= 13.4

	beer_bottleX[2] = 2440.66 //on dumpster2
	beer_bottleY[2] = -1973.76 
	beer_bottleZ[2]	= 14.1

	beer_bottleX[3] = 2448.97 //on fucked car
	beer_bottleY[3] = -1973.29 
	beer_bottleZ[3]	= 13.3

	beer_bottleX[4] = 2444.35  //on crate
	beer_bottleY[4] = -1970.61 
	beer_bottleZ[4]	= 13.7


	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] tag_target[0] //on dumpster
	//SET_OBJECT_DYNAMIC tag_target[0] FALSE
	SET_OBJECT_HEADING tag_target[0] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[0] TRUE

	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[1] beer_bottleY[1] beer_bottleZ[1] tag_target[1] //on washing machine
	//SET_OBJECT_DYNAMIC tag_target[1] FALSE
	SET_OBJECT_HEADING tag_target[1] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[1] TRUE

	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[2] beer_bottleY[2] beer_bottleZ[2] tag_target[2] //on dumpster2 
	//SET_OBJECT_DYNAMIC tag_target[2] FALSE
	SET_OBJECT_HEADING tag_target[2] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[2] TRUE

	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[3] beer_bottleY[3] beer_bottleZ[3] tag_target[3] //on fucked car
	//SET_OBJECT_DYNAMIC tag_target[3] FALSE
	SET_OBJECT_HEADING tag_target[3] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[3] TRUE

	CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[4] beer_bottleY[4] beer_bottleZ[4] tag_target[4] //on crate
	//SET_OBJECT_DYNAMIC tag_target[4] FALSE
	SET_OBJECT_HEADING tag_target[4] 90.0
	MAKE_OBJECT_TARGETTABLE tag_target[4] TRUE

	SET_FIXED_CAMERA_POSITION 2453.7554 -1978.9587 15.3890 0.0 0.0 0.0	//Shot of bottle.
	POINT_CAMERA_AT_POINT 2452.8372 -1978.6106 15.1996 JUMP_CUT

	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2453.8206 -1978.7771 12.5469 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2453.8206 -1978.7771 12.5469
	ENDIF

	SET_CHAR_HEADING scplayer 89.5159

	WAIT 2500

	SET_FIXED_CAMERA_POSITION 2449.0413 -1977.7316 12.9667 0.0 0.0 0.0 //Smoke.
	POINT_CAMERA_AT_POINT 2449.8354 -1978.3256 13.0934 JUMP_CUT

	SET_CHAR_HEADING scplayer 65.0
	smoke_group_spilt_blip = 0

smoke_shots_off = 0

CLEAR_SEQUENCE_TASK	smoke_idle_seq

OPEN_SEQUENCE_TASK smoke_idle_seq2
	TASK_STAND_STILL -1 5000
	TASK_PLAY_ANIM -1 Bbalbat_Idle_02 CRACK 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
	TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
	TASK_STAND_STILL -1 5000
	TASK_PLAY_ANIM -1 prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT smoke_idle_seq2 1
CLOSE_SEQUENCE_TASK	smoke_idle_seq2

OPEN_SEQUENCE_TASK smoke_idle_seq 
	TASK_ACHIEVE_HEADING -1 77.0
	TASK_PLAY_ANIM -1 IDLE_tired FAT 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 IDLE_tired FAT 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 IDLE_tired FAT 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 IDLE_tired FAT 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 IDLE_tired FAT 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 IDLE_tired FAT 4.0 FALSE FALSE FALSE FALSE -1
	PERFORM_SEQUENCE_TASK -1 smoke_idle_seq2
CLOSE_SEQUENCE_TASK smoke_idle_seq

OPEN_SEQUENCE_TASK smoke_sweet2_seq_3 
	TASK_TOGGLE_DUCK -1 FALSE
	TASK_GO_STRAIGHT_TO_COORD -1 2450.48 -1981.65 12.55 PEDMOVE_WALK 4000
	PERFORM_SEQUENCE_TASK -1 smoke_idle_seq
CLOSE_SEQUENCE_TASK smoke_sweet2_seq_3

OPEN_SEQUENCE_TASK smoke_sweet2_seq_2 
	TASK_TOGGLE_DUCK -1 TRUE
	TASK_PLAY_ANIM -1 Crouch_Roll_R PED 4.0 FALSE TRUE TRUE FALSE -1
	TASK_SHOOT_AT_COORD -1 beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] 1400
	TASK_SHOOT_AT_COORD -1 beer_bottleX[1] beer_bottleY[1] beer_bottleZ[1] 1400
	TASK_SHOOT_AT_COORD -1 beer_bottleX[2] beer_bottleY[2] beer_bottleZ[2] 1400
	TASK_SHOOT_AT_COORD -1 beer_bottleX[3] beer_bottleY[3] beer_bottleZ[3] 1400
	TASK_SHOOT_AT_COORD -1 beer_bottleX[4] beer_bottleY[4] beer_bottleZ[4] 1400
	PERFORM_SEQUENCE_TASK -1 smoke_sweet2_seq_3	
CLOSE_SEQUENCE_TASK smoke_sweet2_seq_2

IF NOT IS_CHAR_DEAD	big_smoke
	PERFORM_SEQUENCE_TASK big_smoke smoke_sweet2_seq_2
	CLEAR_SEQUENCE_TASK	smoke_sweet2_seq_2
ENDIF

played_smoke_samples = 0

refresh_char_weapon = 0

	WHILE NOT tag_target_counter = 5
		WAIT 0
		  
		IF DOES_OBJECT_EXIST tag_target[tag_index]
			IF IS_PLAYER_TARGETTING_OBJECT player1 tag_target[tag_index] 
				GOSUB chars_look_at_targets
   			ELSE
				GOSUB stop_chars_look_at_targets
			ENDIF

			IF HAS_OBJECT_BEEN_DAMAGED tag_target[tag_index]
				MAKE_OBJECT_TARGETTABLE tag_target[tag_index] FALSE
				DELETE_OBJECT tag_target[tag_index]
				//INCREMENT_FLOAT_STAT SHOOTING_SKILL 0.5
				tag_target_counter ++
			ENDIF
		ENDIF

		IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer 2448.9602 -1973.5446 13.3074 13.0 13.0 10.0 FALSE
			
			IF smoke_group_spilt_blip = 0
				REMOVE_BLIP shooting_range_blip
				ADD_BLIP_FOR_COORD 2451.95 -1978.93 12.55 shooting_range_blip
				CLEAR_HELP
				PRINT_NOW ( SWE2_J ) 10000 1 //GO BACK!
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
					SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 10
				ENDIF
				smoke_group_spilt_blip = 1
			ENDIF
		ELSE
			
			IF refresh_char_weapon = 0
				IF NOT IS_CHAR_DEAD	big_smoke
					REMOVE_ALL_CHAR_WEAPONS big_smoke
					GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_PISTOL 10000
					SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_PISTOL
					refresh_char_weapon = 1
				ENDIF
			ENDIF

			IF smoke_group_spilt_blip = 1
				REMOVE_BLIP shooting_range_blip
				GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL 10
				
				PRINT_HELP_FOREVER HOOD2F  
				
				PRINT_NOW ( SWE2_I ) 10000 1 // Try Shooting the bottles while crouching
				smoke_group_spilt_blip = 0
			ENDIF
		ENDIF

		IF smoke_shots_off = 0
			IF NOT IS_CHAR_DEAD	big_smoke
				GET_SCRIPT_TASK_STATUS big_smoke TASK_PLAY_ANIM Return_Status

				GET_SEQUENCE_PROGRESS big_smoke Return_Progress

				IF Return_Progress = 2
					SET_FIXED_CAMERA_POSITION 2452.8652 -1977.8590 13.7390 0.0 0.0 0.0 //Shot of bottle.
					POINT_CAMERA_AT_POINT 2451.9014 -1978.1173 13.6752 JUMP_CUT
					IF played_smoke_samples = 0
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0

							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE

						PLAY_MISSION_AUDIO 1
						PRINT_NOW ( SWE3_BD ) 10000 1 //Cap your ass ******************************************************************
						IF NOT IS_CHAR_DEAD	big_smoke
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
							START_CHAR_FACIAL_TALK big_smoke 3000
						ENDIF
						WAIT 500
						IF DOES_OBJECT_EXIST tag_target[0]
							BREAK_OBJECT tag_target[0] TRUE
						ENDIF

						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
							
							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE
						

						IF NOT IS_CHAR_DEAD	big_smoke
							CLEAR_PRINTS
							STOP_CHAR_FACIAL_TALK big_smoke
						ENDIF
						
						LOAD_MISSION_AUDIO 1 SOUND_SWE3_BF //You want some too?

						played_smoke_samples = 1
					ENDIF
				ENDIF

				IF Return_Progress = 3
					SET_FIXED_CAMERA_POSITION 2452.9795 -1980.7344 13.8660 0.0 0.0 0.0 //Shot of bottle.
					POINT_CAMERA_AT_POINT 2452.0886 -1980.2820 13.8231 JUMP_CUT
					
					IF played_smoke_samples = 1
						WHILE NOT HAS_MISSION_AUDIO_LOADED 2
							WAIT 0

							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE

						PLAY_MISSION_AUDIO 2
						PRINT_NOW ( SWE3_BE ) 10000 1 //and your ass ******************************************************************
						IF NOT IS_CHAR_DEAD	big_smoke
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
							START_CHAR_FACIAL_TALK big_smoke 3000
						ENDIF
						WAIT 500
						IF DOES_OBJECT_EXIST tag_target[1]
							BREAK_OBJECT tag_target[1] TRUE
						ENDIF

						WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
							WAIT 0
							
							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE
						

						IF NOT IS_CHAR_DEAD	big_smoke
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
							START_CHAR_FACIAL_TALK big_smoke 3000
						ENDIF
						LOAD_MISSION_AUDIO 2 SOUND_SWE3_BG //Ice cold!

						played_smoke_samples = 2
					ENDIF
				ENDIF

				IF Return_Progress = 4
					SET_FIXED_CAMERA_POSITION 2453.3035 -1979.4598 14.4320 0.0 0.0 0.0 //Shot of bottle.
					POINT_CAMERA_AT_POINT 2452.3540 -1979.2430 14.2053 JUMP_CUT
					IF refresh_char_weapon = 1
						IF NOT IS_CHAR_DEAD	big_smoke
							REMOVE_ALL_CHAR_WEAPONS big_smoke
							GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_PISTOL 10000
							SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_PISTOL
							refresh_char_weapon = 2
						ENDIF
					ENDIF

					IF played_smoke_samples = 2
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0

							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE

						PLAY_MISSION_AUDIO 1
						PRINT_NOW ( SWE3_BF ) 10000 1 //you want some too? ******************************************************************
						IF NOT IS_CHAR_DEAD	big_smoke
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
							START_CHAR_FACIAL_TALK big_smoke 3000
						ENDIF
						WAIT 500
						IF DOES_OBJECT_EXIST tag_target[2]
							BREAK_OBJECT tag_target[2] TRUE
						ENDIF

						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
							
							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE
						
						IF NOT IS_CHAR_DEAD	big_smoke
							CLEAR_PRINTS
							STOP_CHAR_FACIAL_TALK big_smoke
						ENDIF
						LOAD_MISSION_AUDIO 1 SOUND_SWE3_BB //I knew I was the chosen one!
						played_smoke_samples = 3
					ENDIF

				ENDIF

				IF Return_Progress = 5
					SET_FIXED_CAMERA_POSITION 2449.3628 -1971.9142 13.7680 0.0 0.0 0.0 //Shot of bottle.
					POINT_CAMERA_AT_POINT 2449.4919 -1972.8959 13.6288 JUMP_CUT	
					
					IF played_smoke_samples = 3
						WHILE NOT HAS_MISSION_AUDIO_LOADED 2
							WAIT 0

							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE

						PLAY_MISSION_AUDIO 2
						PRINT_NOW ( SWE3_BG ) 10000 1 //Ice cold! ******************************************************************
						IF NOT IS_CHAR_DEAD	big_smoke
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
							START_CHAR_FACIAL_TALK big_smoke 3000
						ENDIF
						WAIT 500
						IF DOES_OBJECT_EXIST tag_target[3]
							BREAK_OBJECT tag_target[3] TRUE
						ENDIF

						WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
							WAIT 0
							
							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE
						LOAD_MISSION_AUDIO 2 SOUND_SWE3_ZZ	//Oh, man, check out ‘Special Agent Big Smoke’

						IF NOT IS_CHAR_DEAD	big_smoke
							CLEAR_PRINTS
							STOP_CHAR_FACIAL_TALK big_smoke
						ENDIF
						played_smoke_samples = 4
					ENDIF	
				ENDIF

				IF Return_Progress = 6
					SET_FIXED_CAMERA_POSITION 2442.5032 -1967.3361 14.1262 0.0 0.0 0.0 //Shot of bottle.
					POINT_CAMERA_AT_POINT 2443.0920 -1968.1328 13.9918 JUMP_CUT
					IF refresh_char_weapon = 2
						IF NOT IS_CHAR_DEAD	big_smoke
							REMOVE_ALL_CHAR_WEAPONS big_smoke
							GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_PISTOL 10000
							SET_CURRENT_CHAR_WEAPON big_smoke WEAPONTYPE_PISTOL
							refresh_char_weapon = 3
						ENDIF
					ENDIF
					IF played_smoke_samples = 4
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0

							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE

						PLAY_MISSION_AUDIO 1
						PRINT_NOW ( SWE3_BB ) 10000 1 //I knew I was the chosen one! ******************************************************************
						IF NOT IS_CHAR_DEAD	big_smoke
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
							START_CHAR_FACIAL_TALK big_smoke 3000
						ENDIF
						WAIT 500
						IF DOES_OBJECT_EXIST tag_target[4]
							BREAK_OBJECT tag_target[4] TRUE
						ENDIF

						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
							
							IF IS_CHAR_DEAD	big_smoke
								PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
								GOTO mission_sweet2_failed
							ENDIF

						ENDWHILE
						
						IF NOT IS_CHAR_DEAD	big_smoke
							CLEAR_PRINTS
							STOP_CHAR_FACIAL_TALK big_smoke
						ENDIF
						played_smoke_samples = 5
					ENDIF

					//CJ's LINE
					WHILE NOT HAS_MISSION_AUDIO_LOADED 2
						WAIT 0

						IF IS_CHAR_DEAD	big_smoke
							PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
							GOTO mission_sweet2_failed
						ENDIF

					ENDWHILE

					PLAY_MISSION_AUDIO 2
					PRINT_NOW ( SWE3_ZZ ) 10000 1 //Oh, man, check out ‘Special Agent Big Smoke’ ******************************************************************

					WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
						WAIT 0
						
						IF IS_CHAR_DEAD	big_smoke
							PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
							GOTO mission_sweet2_failed
						ENDIF

					ENDWHILE

					PRINT_NOW ( SWE2_I ) 10000 1 // Try Shooting the bottles while crouching

					SET_CHAR_COORDINATES scplayer 2450.7402 -1978.3749 12.5469 
					SET_CHAR_HEADING scplayer 89.5159
					IF NOT IS_CHAR_DEAD emmet
						SET_CHAR_COORDINATES emmet 2451.8340 -1976.8108 12.5469 
						SET_CHAR_HEADING emmet 75.6292
					ENDIF
					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON
					SET_CAMERA_BEHIND_PLAYER
										
					PRINT_HELP_FOREVER HOOD2F  
					
					tag_target_counter = 0
					IF DOES_OBJECT_EXIST tag_target[0]
						MAKE_OBJECT_TARGETTABLE tag_target[0] FALSE
					ENDIF
					DELETE_OBJECT tag_target[0]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[0] beer_bottleY[0] beer_bottleZ[0] tag_target[0] //on dumpster
					//SET_OBJECT_DYNAMIC tag_target[0] FALSE
					SET_OBJECT_HEADING tag_target[0] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[0] TRUE
					
					IF DOES_OBJECT_EXIST tag_target[1]
						MAKE_OBJECT_TARGETTABLE tag_target[1] FALSE
					ENDIF
					DELETE_OBJECT tag_target[1]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[1] beer_bottleY[1] beer_bottleZ[1] tag_target[1] //on washing machine
					SET_OBJECT_HEADING tag_target[1] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[1] TRUE
					
					IF DOES_OBJECT_EXIST tag_target[2]
						MAKE_OBJECT_TARGETTABLE tag_target[2] FALSE
					ENDIF
					DELETE_OBJECT tag_target[2]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[2] beer_bottleY[2] beer_bottleZ[2] tag_target[2] //on dumpster2
					SET_OBJECT_HEADING tag_target[2] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[2] TRUE

					IF DOES_OBJECT_EXIST tag_target[3]
						MAKE_OBJECT_TARGETTABLE tag_target[3] FALSE
					ENDIF
					DELETE_OBJECT tag_target[3]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[3] beer_bottleY[3] beer_bottleZ[3] tag_target[3]  //on fucked car	
					SET_OBJECT_HEADING tag_target[3] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[3] TRUE

					IF DOES_OBJECT_EXIST tag_target[4]
						MAKE_OBJECT_TARGETTABLE tag_target[4] FALSE
					ENDIF
					DELETE_OBJECT tag_target[4]
					CREATE_OBJECT DYN_WINE_BIG  beer_bottleX[4] beer_bottleY[4] beer_bottleZ[4] tag_target[4] //on crate
					SET_OBJECT_HEADING tag_target[4] 90.0
					MAKE_OBJECT_TARGETTABLE tag_target[4] TRUE
					smoke_shots_off = 1
				ENDIF
			ENDIF
		ENDIF

		tag_index ++
		IF tag_index >= 5
			tag_index = 0
		ENDIF
		
		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CAR_DEAD big_smoke_car
			PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			GOTO mission_sweet2_failed
		ENDIF
					
	ENDWHILE

	LOAD_MISSION_AUDIO 1 SOUND_SWE3_DG //Aw, your makin’ me so proud of you!

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0

		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 1
	PRINT_NOW ( SWE3_DG ) 10000 1 //Aw, your makin’ me so proud of you! ******************************************************************
	IF NOT IS_CHAR_DEAD	emmet
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH emmet TRUE
		START_CHAR_FACIAL_TALK emmet 3000
		TASK_PLAY_ANIM emmet prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE TRUE -1
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		
		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF
		
		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD	emmet
		CLEAR_CHAR_TASKS emmet
		CLEAR_PRINTS
		STOP_CHAR_FACIAL_TALK emmet
	ENDIF

	CLEAR_WANTED_LEVEL player1
	SET_PLAYER_CONTROL player1 OFF

	WAIT 1000

	tag_target_counter = 0

	WAIT 1000

// **********************************************SHOOT THE PETROL TANK*****************************************************

	SWITCH_WIDESCREEN ON
	SET_NEAR_CLIP 0.2

	CLEAR_HELP
	
	IF NOT IS_CAR_DEAD emmets_car
		//SET_CAN_BURST_CAR_TYRES emmets_car TRUE
		SET_CAR_COORDINATES emmets_car 2446.49 -1966.47 12.54
		SET_CAR_HEADING emmets_car 101.85
	
		SET_FIXED_CAMERA_POSITION 2447.2234 -1971.3845 14.5714 0.0 0.0 0.0	//Shot of the car.
		POINT_CAMERA_AT_POINT 2447.0703 -1970.4343 14.2998 JUMP_CUT
	ENDIF
	
	IF NOT IS_CHAR_DEAD emmet
		SET_CHAR_COORDINATES emmet 2452.6899 -1980.2181 12.5469  
		SET_CHAR_HEADING emmet 44.7926	
	ENDIF

	IF NOT IS_CAR_DEAD big_smoke_car
		SET_CAR_COORDINATES big_smoke_car 2452.6460 -2003.8763 12.5540 
		SET_CAR_HEADING big_smoke_car 100.2244
	ENDIF
	
	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2446.11 -1974.53 12.54 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2446.11 -1974.53 12.54
	ENDIF

	SET_CHAR_HEADING scplayer 349.81

	PRINT_NOW SWE2_F 10000 1 // ~s~Use manual aim to shoot the car's gas tank.

	IF NOT IS_CAR_DEAD emmets_car
		WAIT 3000
		SET_FIXED_CAMERA_POSITION 2448.2490 -1968.6077 13.7351 0.0 0.0 0.0 //Shot of bottle.
		POINT_CAMERA_AT_POINT 2447.9189 -1967.7069 13.4528 JUMP_CUT
		WAIT 2000
	ENDIF

	PRINT_HELP_FOREVER HOOD2C  // To manually aim, touch the middle of the screen and move to the left and right whilst shooting.

	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON

	smoke_group_spilt_blip = 0

	smoke_shots_off = 0
	WHILE NOT IS_CAR_DEAD emmets_car
		WAIT 0

		IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer 2451.95 -1978.93 12.55 13.0 13.0 10.0 FALSE		
			IF smoke_group_spilt_blip = 0
				REMOVE_BLIP shooting_range_blip
				ADD_BLIP_FOR_COORD 2451.95 -1978.93 12.55 shooting_range_blip
				CLEAR_HELP
				PRINT_NOW ( SWE2_J ) 10000 1 //GO BACK!
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
					SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 10
				ENDIF
				smoke_group_spilt_blip = 1
			ENDIF
		ELSE		
			IF smoke_group_spilt_blip = 1
				REMOVE_BLIP shooting_range_blip
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
					SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 30000
				ENDIF
				
				PRINT_HELP_FOREVER HOOD2C  // To manually aim, touch the middle of the screen and move to the left and right whilst shooting.
				
				PRINT_NOW SWE2_F 10000 1   // ~s~Use manual aim to shoot the car's gas tank.

				smoke_group_spilt_blip = 0
			ENDIF
		ENDIF

		IF smoke_shots_off = 0
			IF NOT IS_CHAR_DEAD	big_smoke
				RESTORE_CAMERA_JUMPCUT
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				SET_CAMERA_BEHIND_PLAYER
				IF NOT IS_CHAR_DEAD emmet 
					SET_CHAR_PROOFS emmet FALSE FALSE TRUE FALSE FALSE
					SET_CHAR_HEALTH emmet 600
				ENDIF
				IF NOT IS_CAR_DEAD emmets_car
					SET_CAR_PROOFS emmets_car FALSE FALSE FALSE FALSE FALSE
				ENDIF
				SET_CHAR_PROOFS scplayer FALSE FALSE TRUE FALSE FALSE

				smoke_shots_off = 1
			ENDIF
		ENDIF

		IF IS_CHAR_DEAD	big_smoke
			PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CHAR_DEAD	emmet
			PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
			GOTO mission_sweet2_failed
		ENDIF

		IF IS_CAR_DEAD big_smoke_car
			PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			GOTO mission_sweet2_failed
		ENDIF

	ENDWHILE

CLEAR_HELP
CLEAR_WANTED_LEVEL player1

MARK_MODEL_AS_NO_LONGER_NEEDED tampa

WAIT 2000

CLEAR_WANTED_LEVEL player1




// **********************************************LEAVING EMMETS CUT SCENE START*************************************************
DO_FADE 500 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

LOAD_SCENE_IN_DIRECTION 2450.5669 -1975.6414 12.5469 288.0

SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON
SET_NEAR_CLIP 0.2
REQUEST_ANIMATION GANGS

WHILE NOT HAS_ANIMATION_LOADED GANGS
	WAIT 0

ENDWHILE

sweet2_index = 0
sweet2_audio_is_playing = 0
sweet2_cutscene_flag = 0
sweet2_chat_switch = SWEET2_CHAT2
GOSUB sweet2_chat_switch

IF NOT IS_CHAR_IN_ANY_CAR scplayer
	SET_CHAR_COORDINATES scplayer 2450.5669 -1975.6414 12.5469 
ELSE
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 2450.5669 -1975.6414 12.5469
ENDIF

SET_CHAR_HEADING scplayer 288.2500

IF NOT IS_CHAR_DEAD	big_smoke
	SET_CHAR_COORDINATES big_smoke 2452.7415 -1976.4500 12.5469 
	SET_CHAR_HEADING big_smoke 345.3302

	IF NOT IS_CHAR_DEAD	emmet
		SET_CHAR_HEALTH emmet 600
		SET_CHAR_COORDINATES emmet 2452.8459 -1975.1003 12.5469 
		SET_CHAR_HEADING emmet 160.2500
		TASK_TURN_CHAR_TO_FACE_CHAR big_smoke scplayer
		TASK_TURN_CHAR_TO_FACE_CHAR emmet scplayer
		CLEAR_LOOK_AT emmet
		TASK_LOOK_AT_CHAR emmet scplayer 30000
	ENDIF
ENDIF

IF NOT IS_CAR_DEAD big_smoke_car
	SET_CAR_COORDINATES big_smoke_car 2452.6460 -2003.8763 12.5540 
	SET_CAR_HEADING big_smoke_car 100.2244
ENDIF
 
CLEAR_AREA 2453.0293 -2001.0591 12.5540 30.0 TRUE
CLEAR_HELP
CLEAR_PRINTS

DO_FADE 500 FADE_IN

SET_FIXED_CAMERA_POSITION 2450.1067 -1977.5526 14.0919 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2450.7771 -1976.8383 13.8915 JUMP_CUT

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 1
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD big_smoke
				//START_CHAR_FACIAL_TALK big_smoke 20000
				TASK_PLAY_ANIM big_smoke prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE TRUE -1
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

skip_emmets_cutscene = 0

SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0
CLEAR_AREA 2452.6460 -2003.8763 12.5540 50.0 TRUE

SKIP_CUTSCENE_START

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 2
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD big_smoke
				//START_CHAR_FACIAL_TALK big_smoke 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD big_smoke
	STOP_CHAR_FACIAL_TALK big_smoke
ENDIF

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 3
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD emmet
				//START_CHAR_FACIAL_TALK emmet 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE


sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 4
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD big_smoke
				//START_CHAR_FACIAL_TALK big_smoke 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE


sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 5
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD big_smoke
				//START_CHAR_FACIAL_TALK big_smoke 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

TASK_GO_STRAIGHT_TO_COORD scplayer 2452.6416 -1998.2188 12.5540 PEDMOVE_WALK 20000

IF NOT IS_CHAR_DEAD	big_smoke
	TASK_GO_STRAIGHT_TO_COORD big_smoke 2454.9512 -1998.6581 12.5540 PEDMOVE_WALK 20000
	//TASK_WALK_ALONGSIDE_CHAR big_smoke scplayer
	//TASK_WALK_ALONGSIDE_CHAR scplayer big_smoke
ENDIF

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 6
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD big_smoke
				//START_CHAR_FACIAL_TALK big_smoke 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD	emmet			
	TASK_GO_STRAIGHT_TO_COORD emmet 2453.3206 -1987.7144 12.5469 PEDMOVE_WALK 20000
	SET_ANIM_GROUP_FOR_CHAR emmet gang1
ENDIF

SET_CHAR_COORDINATES scplayer 2452.0786 -1978.8445 12.5469 
SET_CHAR_HEADING scplayer 180.0
CLEAR_CHAR_TASKS scplayer
TASK_GO_STRAIGHT_TO_COORD scplayer 2452.6416 -1998.2188 12.5540 PEDMOVE_WALK 20000

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 7
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD emmet
				//START_CHAR_FACIAL_TALK emmet 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

SET_FIXED_CAMERA_POSITION 2453.5879 -1992.3597 14.7070 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2453.5383 -1991.4121 14.3914 JUMP_CUT

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 8
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD emmet
				//START_CHAR_FACIAL_TALK emmet 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 9
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD emmet
				//START_CHAR_FACIAL_TALK emmet 20000
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

sweet2_cutscene_flag = 0
WHILE NOT sweet2_index = 10
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF sweet2_cutscene_flag = 0
		IF sweet2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD emmet
				//START_CHAR_FACIAL_TALK emmet 20000
				TASK_PLAY_ANIM emmet prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE TRUE -1
				sweet2_cutscene_flag = 1
			ENDIF
		ENDIF
	ENDIF
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

WHILE NOT sweet2_index = 11
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD emmet
	CLEAR_CHAR_TASKS emmet
ENDIF

CLEAR_AREA 2453.0293 -2001.0591 12.5540 10.0 TRUE

SET_FIXED_CAMERA_POSITION 2453.5691 -1992.4054 16.0824 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2453.6028 -1993.3175 15.6741 JUMP_CUT

IF NOT IS_CAR_DEAD big_smoke_car
	IF NOT IS_CHAR_DEAD big_smoke
		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
		TASK_ENTER_CAR_AS_PASSENGER big_smoke big_smoke_car 50000 0
		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
		TASK_ENTER_CAR_AS_DRIVER scplayer big_smoke_car 50000
	ENDIF
ENDIF

WHILE NOT sweet2_index = 12
	WAIT 0

	GOSUB load_and_play_audio_sweet2
	IF IS_CHAR_DEAD	emmet
		PRINT_NOW (SWE2_K) 10000 1 //~r~Emmet is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD	big_smoke
	IF NOT IS_CAR_DEAD big_smoke_car
		WHILE NOT IS_CHAR_SITTING_IN_CAR scplayer big_smoke_car
		OR NOT IS_CHAR_SITTING_IN_CAR big_smoke	big_smoke_car
			WAIT 0

			IF IS_CHAR_DEAD big_smoke
				PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
				GOTO mission_sweet2_failed
			ENDIF			
			IF IS_CAR_DEAD big_smoke_car
				PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
				GOTO mission_sweet2_failed
			ENDIF

		ENDWHILE
	ENDIF
ENDIF

SET_FIXED_CAMERA_POSITION 2456.7581 -1999.2529 15.2969 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2456.2307 -2000.0271 14.9470 JUMP_CUT

WHILE NOT sweet2_index = 13
	WAIT 0

	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF			
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

	GOSUB load_and_play_audio_sweet2

ENDWHILE

WHILE NOT sweet2_index = 14
	WAIT 0

	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF			
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF

	GOSUB load_and_play_audio_sweet2

ENDWHILE

skip_emmets_cutscene = 1
SKIP_CUTSCENE_END

SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0

IF skip_emmets_cutscene = 0
	CLEAR_PRINTS
	SET_FADING_COLOUR 0 0 0

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_MISSION_AUDIO 3
	CAMERA_RESET_NEW_SCRIPTABLES
	IF NOT IS_CAR_DEAD big_smoke_car
		IF NOT IS_CHAR_DEAD big_smoke
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				WARP_CHAR_INTO_CAR scplayer big_smoke_car
			ENDIF
			IF NOT IS_CHAR_IN_ANY_CAR big_smoke
				WARP_CHAR_INTO_CAR_AS_PASSENGER big_smoke big_smoke_car 0
			ENDIF
		ENDIF
	ENDIF
	WAIT 100
	DO_FADE 500 FADE_IN
ENDIF


DELETE_CHAR emmet
RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
SET_SCRIPT_LIMIT_TO_GANG_SIZE 99

// **********************************************LEAVING EMMETS CUT SCENE END*************************************************

     
how_much_ammo = how_much_ammo + 60
IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
	SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL how_much_ammo
ENDIF       

PRINT_NOW ( SWE2_D ) 10000 1 // Drive Smoke home. 

IF NOT IS_CAR_DEAD big_smoke_car
	REMOVE_BLIP	shooting_range_blip
	REMOVE_BLIP	smoke_car_blip
	ADD_BLIP_FOR_CAR big_smoke_car smoke_car_blip
	SET_BLIP_AS_FRIENDLY smoke_car_blip TRUE
ENDIF

IF NOT IS_CHAR_DEAD big_smoke
	ADD_BLIP_FOR_CHAR big_smoke	big_smokes_blip
    SET_BLIP_AS_FRIENDLY big_smokes_blip TRUE
	REMOVE_BLIP big_smokes_blip
ENDIF

//skip_to_end_cut_scene:

ADD_BLIP_FOR_COORD 2066.4648 -1695.4436 12.5547 shooting_range_blip
REMOVE_BLIP shooting_range_blip

blob_flag = 1
smoke_sitting_in_car = 0
smoke_car_blip_removed = 0
smoke_group_spilt_blip = 0
big_smoke_group_spilt_blip = 0
gun_help = 0

TIMERA = 0
TIMERB = 0

sweet2_index = 0
sweet2_audio_is_playing = 0
sweet2_cutscene_flag = 0
sweet2_chat_switch = SWEET2_CHAT3
GOSUB sweet2_chat_switch

GOSUB reset_gang_density
played_the_timer = 0

RELEASE_WEATHER

REMOVE_ANIMATION GANGS
REMOVE_ANIMATION CRACK
REMOVE_ANIMATION COLT45
REMOVE_ANIMATION FAT
MARK_MODEL_AS_NO_LONGER_NEEDED Colt45
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_WINE_BIG 
MARK_MODEL_AS_NO_LONGER_NEEDED tampa
UNLOAD_SPECIAL_CHARACTER 1


//skip_hereeeeeeeeeeeeeeee:

WHILE NOT LOCATE_CAR_3D big_smoke_car 2066.4648 -1695.4436 12.5547 4.0 4.0 4.0 blob_flag //GO to Smokes
OR NOT IS_CHAR_SITTING_IN_CAR big_smoke big_smoke_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS	big_smoke_car
	WAIT 0

		
	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		IF TIMERA > 5000
			IF gun_help = 1
				
				PRINT_HELP HELP53  
				
				TIMERA = 0
				gun_help = 2
			ENDIF
		ENDIF
		IF TIMERA > 5000
			IF gun_help = 2
				PRINT_HELP ( HOOD2D )  // Your ~h~gun stat~w~ will increase the more you use your weapon.
				TIMERA = 0
				gun_help = 3
			ENDIF
		ENDIF
		IF TIMERA > 5000
			IF gun_help = 3
				PRINT_HELP ( HOOD2E )  
				TIMERA = 0
				gun_help = 4
			ENDIF
		ENDIF
	ELSE
		IF TIMERA > 2000
			IF gun_help = 0
				PRINT_HELP ( EMMET_G ) // Get guns from Emmet.
				REMOVE_BLIP emmets_shop_blip
				ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2447.3643 -1974.4963 12.5469 RADAR_SPRITE_EMMETGUN emmets_shop_blip //Emmet
				TIMERA = 0
				gun_help = 1
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD	big_smoke
		IF NOT IS_CAR_DEAD big_smoke_car
			IF IS_CHAR_IN_CAR scplayer big_smoke_car
				IF smoke_car_blip_removed = 0
					REMOVE_BLIP smoke_car_blip
					REMOVE_BLIP shooting_range_blip
					PRINT_NOW ( SWE2_D ) 6000 1 // Drive Smoke home. 
					ADD_BLIP_FOR_COORD 2066.4648 -1695.4436 12.5547 shooting_range_blip
					blob_flag = 1
					smoke_car_blip_removed = 1
				ENDIF

				//IF played_the_timer = 0
					IF TIMERB > 6000	
						GOSUB load_and_play_audio_sweet2
						played_the_timer = 1
					ENDIF
				//ELSE
					//GOSUB load_and_play_audio_sweet2
				//ENDIF

			ELSE
				IF smoke_car_blip_removed = 1
					REMOVE_BLIP shooting_range_blip
					REMOVE_BLIP smoke_car_blip
					ADD_BLIP_FOR_CAR big_smoke_car smoke_car_blip
					SET_BLIP_AS_FRIENDLY smoke_car_blip TRUE
					GOSUB get_back_in_the_car_sweet2
					PRINT_NOW (SWE2_L) 6000 1 //Get back in the car
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
					STOP_CHAR_FACIAL_TALK scplayer
					blob_flag = 0
					smoke_car_blip_removed = 0
				ENDIF
			ENDIF
		ELSE
			PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			GOTO mission_sweet2_failed
		ENDIF
	ELSE
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF
	
	IF IS_CAR_DEAD big_smoke_car
		PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
		GOTO mission_sweet2_failed
	ENDIF
	IF IS_CHAR_DEAD	big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

 
SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON
SET_NEAR_CLIP 0.2

SET_FIXED_CAMERA_POSITION 2086.0100 -1699.6982 17.7495 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2085.0596 -1699.8313 17.4682 JUMP_CUT


REQUEST_MODEL cellphone

WAIT 1000

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

WHILE NOT HAS_MODEL_LOADED cellphone
	WAIT 0
ENDWHILE

	IF NOT IS_CHAR_DEAD	big_smoke
		//REMOVE_CHAR_FROM_GROUP big_smoke
		IF NOT IS_CHAR_IN_ANY_CAR big_smoke
			SET_CHAR_COORDINATES big_smoke 2071.1694 -1703.0597 12.5538 
		ELSE
			WARP_CHAR_FROM_CAR_TO_COORD big_smoke 2071.1694 -1703.0597 12.5538
		ENDIF
		SET_CHAR_HEADING big_smoke 162.3527
		CLEAR_CHAR_TASKS big_smoke
		CLEAR_LOOK_AT big_smoke
		TASK_LOOK_AT_CHAR big_smoke scplayer 4000
	ENDIF

	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2071.5537 -1704.3748 12.5538 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2071.5537 -1704.3748 12.5538
	ENDIF
	SET_CHAR_HEADING scplayer 12.5041
	CLEAR_CHAR_TASKS scplayer

	//SET_FIXED_CAMERA_POSITION 2073.7661 -1704.6256 14.2777 0.0 0.0 0.0
	//POINT_CAMERA_AT_POINT 2072.8076 -1704.3674 14.1562 JUMP_CUT

	SET_FIXED_CAMERA_POSITION 2069.6067 -1704.6298 14.2313 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2070.4746 -1704.1737 14.0353 JUMP_CUT

WAIT 1000 


DO_FADE 1000 FADE_IN

LOAD_MISSION_AUDIO 1 SOUND_SWE3_JA
LOAD_MISSION_AUDIO 2 SOUND_SWE3_JB
LOAD_MISSION_AUDIO 3 SOUND_MOBRING

WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0
	
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

PLAY_MISSION_AUDIO 1
PRINT_NOW ( SWE3_JA ) 10000 1 //Thanks, CJ, I’ll see you around.
GOSUB start_talking_sweet2

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
	WAIT 0
	
	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD	big_smoke
	CLEAR_LOOK_AT big_smoke
ENDIF
GOSUB stop_talking_sweet2

WHILE NOT HAS_MISSION_AUDIO_LOADED 2
	WAIT 0

	IF IS_CHAR_DEAD big_smoke
		PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
		GOTO mission_sweet2_failed
	ENDIF

ENDWHILE

PLAY_MISSION_AUDIO 2
PRINT_NOW ( SWE3_JB ) 10000 1 //Later, homie.
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
START_CHAR_FACIAL_TALK scplayer 3000
//GOSUB start_talking_sweet2

IF NOT IS_CHAR_DEAD	big_smoke
	TASK_GO_STRAIGHT_TO_COORD big_smoke 2065.10 -1703.40 13.15 PEDMOVE_WALK 4000
ENDIF

WAIT 1000

IF NOT IS_CHAR_DEAD	big_smoke
	TASK_GO_STRAIGHT_TO_COORD scplayer 2073.2866 -1702.1045 12.5469 PEDMOVE_WALK 4000
ENDIF

SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
STOP_CHAR_FACIAL_TALK scplayer

CLEAR_PRINTS

WAIT 1000

SET_FIXED_CAMERA_POSITION 2072.7229 -1699.7369 14.1283 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2072.9346 -1700.6702 13.8382 JUMP_CUT

DELETE_CHAR big_smoke

WHILE NOT HAS_MISSION_AUDIO_LOADED 3
	WAIT 0

ENDWHILE

PLAY_MISSION_AUDIO 3

TIMERA = 0

WHILE NOT HAS_MISSION_AUDIO_FINISHED 3
	WAIT 0

	IF TIMERA > 1500
		GOTO task_use_the_phone_sweet2
	ENDIf

ENDWHILE

task_use_the_phone_sweet2:

sweet2_index = 0
sweet2_audio_is_playing = 0
sweet2_cutscene_flag = 0
sweet2_chat_switch = SWEET2_CHAT4
GOSUB sweet2_chat_switch

CLEAR_CHAR_TASKS scplayer


TASK_USE_MOBILE_PHONE scplayer TRUE

WAIT 1800


finished_mobile_phone_call = 0
flag_player_control_back = 0

REMOVE_ANIMATION GANGS
REMOVE_ANIMATION CRACK
REMOVE_ANIMATION COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED Colt45
MARK_MODEL_AS_NO_LONGER_NEEDED glendale
MARK_MODEL_AS_NO_LONGER_NEEDED DYN_WINE_BIG 
MARK_MODEL_AS_NO_LONGER_NEEDED tampa
UNLOAD_SPECIAL_CHARACTER 10
UNLOAD_SPECIAL_CHARACTER 1

WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2246.9719 -1660.7789 14.2856 3.5 4.0 4.0 TRUE //Go to Binco
	WAIT 0

	IF finished_mobile_phone_call = 0
		GOSUB load_and_play_audio_sweet2
		IF sweet2_index > 0
			IF sweet2_index	= 10
			OR IS_BUTTON_PRESSED PAD1 TRIANGLE
				CLEAR_MISSION_AUDIO 1
				CLEAR_PRINTS
				TASK_USE_MOBILE_PHONE scplayer FALSE
				MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
				PRINT_NOW ( COLORS ) 6000 1 //Get over to ~y~Binco~s~ and get yourself some Grove Street colors!

				finished_mobile_phone_call = 1
			ENDIF
		ENDIF
	ENDIF

	IF flag_player_control_back = 0
		IF sweet2_index > 0
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON

			REMOVE_BLIP shooting_range_blip
			ADD_BLIP_FOR_COORD 2246.9719 -1660.7789 14.2856 shooting_range_blip
			flag_player_control_back = 1
		ENDIF
	ENDIF

ENDWHILE

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON

SET_FIXED_CAMERA_POSITION 2253.0986 -1644.2246 16.6501 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2252.7742 -1645.1615 16.5203 JUMP_CUT

PRINT_NOW (S2HELP1) 6000 1 //Go in shop

SWITCH_ENTRY_EXIT cschp	TRUE
SWITCH_ENTRY_EXIT cssprt TRUE
SWITCH_ENTRY_EXIT lacs1	TRUE

WAIT 2500

RESTORE_CAMERA_JUMPCUT
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
print_help_sweet2 = 0

WHILE NOT print_help_sweet2 = 2
	WAIT 0

	GET_AREA_VISIBLE new_visible_area
	
	IF new_visible_area	= 15
		IF print_help_sweet2 = 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 208.0279 -107.9499 1004.1328 10.0 10.0 5.0 FALSE
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				CLEAR_PRINTS
				WAIT 1500
				SET_PLAYER_CONTROL player1 OFF
				SET_FIXED_CAMERA_POSITION 213.0277 -102.9124 1006.3765 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 213.7582 -102.2797 1006.1193 JUMP_CUT
				PRINT_NOW (S2HELP2) 6000 1 //get clothes blah blah
				WAIT 4000
				RESTORE_CAMERA_JUMPCUT
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				PRINT_HELP HELWARD
				print_help_sweet2 = 1
			ENDIF
		ENDIF 
	ENDIF

	IF print_help_sweet2 = 1
		IF new_visible_area	= 0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 2244.4800 -1664.0599 14.4690	10.0 10.0 5.0 FALSE
				REMOVE_BLIP shooting_range_blip
				WAIT 2000
				print_help_sweet2 = 2
			ENDIF
		ENDIF
	ENDIF

ENDWHILE


RESTORE_CAMERA_JUMPCUT
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON

GOTO mission_sweet2_passed

 // **************************************** Mission sweet2 failed ************************

mission_sweet2_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
		SET_CHAR_AMMO scplayer WEAPONTYPE_PISTOL 5
	ENDIF
	REMOVE_BLIP emmets_shop_blip
	SWITCH_ENTRY_EXIT cschp	FALSE //BINCO //LA
	SWITCH_ENTRY_EXIT cssprt FALSE //PROLAPS //LA
	SWITCH_ENTRY_EXIT lacs1	FALSE //SUBURBAN //LA

RETURN

   

// **************************************** mission sweet2 passed *************************

mission_sweet2_passed:

	flag_sweet_mission_counter ++
	REGISTER_MISSION_PASSED ( SWEET_2 ) //Used in the stats 
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 4 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 4
	START_NEW_SCRIPT smoke_mission_loop
	REMOVE_BLIP smoke_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT smokeX smokeY smokeZ smoke_blip_icon smoke_contact_blip
	
	REMOVE_BLIP clothes_shop1
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2243.8069 -1668.5667 20.0313 RADAR_SPRITE_TSHIRT clothes_shop1 //Clothes

	REMOVE_BLIP emmets_shop_blip
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2447.3643 -1974.4963 12.5469 RADAR_SPRITE_EMMETGUN emmets_shop_blip //Emmet
	CREATE_PICKUP_WITH_AMMO COLT45 PICKUP_ON_STREET_SLOW 16 2447.7732 -1975.6631 13.0 emmets_gun

	IF added_all_clothes_blips_before = 0
		SWITCH_ENTRY_EXIT cschp	TRUE //BINCO //LA
		SWITCH_ENTRY_EXIT cssprt TRUE //PROLAPS //LA
		SWITCH_ENTRY_EXIT lacs1	TRUE //SUBURBAN //LA

		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2376.4, 909.2, 45.4 RADAR_SPRITE_TSHIRT clothes_blips[6] //CSCHP	//LA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1654.0, 1733.4, 11.0 RADAR_SPRITE_TSHIRT clothes_blips[7] //CSCHP	//LA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2105.7, 2257.4, 11.0 RADAR_SPRITE_TSHIRT clothes_blips[8] //CSCHP	//LA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2371.1, 910.2, 47.2 RADAR_SPRITE_TSHIRT clothes_blips[14] //CSCHP //LA

		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 501.7, -1358.5, 16.4 RADAR_SPRITE_TSHIRT clothes_blips[9] //CSSPRT //LA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2818.6, 2401.5, 11.0 RADAR_SPRITE_TSHIRT clothes_blips[10] //CSSPRT //LA

		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2112.8, -1214.7, 23.9 RADAR_SPRITE_TSHIRT clothes_blips[4] //LACS1 //LA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2772.0, 2447.6, 11.0 RADAR_SPRITE_TSHIRT clothes_blips[5] //LACS1	//LA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2489.0, -26.9, 32.6 RADAR_SPRITE_TSHIRT clothes_blips[16] //LACS1	//LA

		added_all_clothes_blips_before = 1
	ENDIF

	PLAY_MISSION_PASSED_TUNE 1
	CLEAR_WANTED_LEVEL player1
	PLAYER_MADE_PROGRESS 1
RETURN
		

// ********************************** mission cleanup ************************************

mission_cleanup_sweet2:

	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start
	REMOVE_CHAR_ELEGANTLY big_smoke
	REMOVE_CHAR_ELEGANTLY emmet
	//REMOVE_ANIMATION gymnasium
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION CRACK
	REMOVE_ANIMATION COLT45
	REMOVE_ANIMATION FAT
	SWITCH_CAR_GENERATOR gen_car7 101
	MARK_MODEL_AS_NO_LONGER_NEEDED Colt45
	MARK_MODEL_AS_NO_LONGER_NEEDED glendale
	MARK_MODEL_AS_NO_LONGER_NEEDED DYN_WINE_BIG 
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
	//MARK_MODEL_AS_NO_LONGER_NEEDED barrel4
	MARK_MODEL_AS_NO_LONGER_NEEDED tampa
	GOSUB reset_gang_density
	UNLOAD_SPECIAL_CHARACTER 10
	UNLOAD_SPECIAL_CHARACTER 1
	REMOVE_BLIP shooting_range_blip
	REMOVE_BLIP smoke_car_blip
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99
	RELEASE_WEATHER
	IF NOT IS_CAR_DEAD emmets_car
		SET_CAR_PROOFS emmets_car FALSE FALSE FALSE FALSE FALSE
	ENDIF
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0
	CLEAR_HELP
	MISSION_HAS_FINISHED

RETURN


chars_look_at_targets:

	IF DOES_OBJECT_EXIST tag_target[tag_index]
		IF NOT IS_CHAR_DEAD big_smoke
			TASK_LOOK_AT_OBJECT big_smoke tag_target[tag_index] 5000
		ENDIF
	ENDIF

RETURN

stop_chars_look_at_targets:

	IF DOES_OBJECT_EXIST tag_target[tag_index]
		IF NOT IS_CHAR_DEAD big_smoke
			CLEAR_LOOK_AT big_smoke
		ENDIF
	ENDIF

RETURN


sweet2_death_check:


RETURN


load_and_play_audio_sweet2:

	IF sweet2_audio_is_playing = 0
	OR sweet2_audio_is_playing = 1
		IF sweet2_index <= sweet2_cell_index_end
			IF TIMERA > 200
				GOSUB play_sweet2_audio
			ENDIF
		ENDIF
	ENDIF

	IF sweet2_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			GOSUB stop_talking_sweet2
			sweet2_audio_is_playing = 0
			sweet2_index ++
			sweet2_cutscene_flag = 0
			CLEAR_PRINTS
			TIMERA = 0	
		ENDIF
	ENDIF

RETURN

play_sweet2_audio:

	IF sweet2_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 sweet2_audio_chat[sweet2_index]
		sweet2_audio_is_playing = 1
	ENDIF
	IF sweet2_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $sweet2_chat[sweet2_index] ) 4000 1 
			PLAY_MISSION_AUDIO 1
			GOSUB start_talking_sweet2
			sweet2_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN


get_back_in_the_car_sweet2:
	

	CLEAR_MISSION_AUDIO 2
	IF get_in_counter_sweet2 = 0
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AA //Get in
	ENDIF

	IF get_in_counter_sweet2 = 1
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AB //In the ride!
	ENDIF

	IF get_in_counter_sweet2 = 2
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AC //Get in the car!
	ENDIF

	IF get_in_counter_sweet2 = 3   
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AD //Come on, playa, get in!
	ENDIF

	IF get_in_counter_sweet2 = 4   
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AE //Come on, wise man, get in the car!
	ENDIF

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0

		IF IS_CAR_DEAD big_smoke_car
			//PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			//GOTO mission_sweet2_failed
			RETURN
		ENDIF
		IF IS_CHAR_DEAD	big_smoke
			//PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			//GOTO mission_sweet2_failed
			RETURN
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2 
	  	
	IF get_in_counter_sweet2 = 0
		PRINT_NOW ( SMOX_AA ) 3000 1 
	ENDIF
	IF get_in_counter_sweet2 = 1
		PRINT_NOW ( SMOX_AB ) 3000 1 
	ENDIF
	IF get_in_counter_sweet2 = 2
		PRINT_NOW ( SMOX_AC ) 3000 1 
	ENDIF
	IF get_in_counter_sweet2 = 3
		PRINT_NOW ( SMOX_AD ) 3000 1 
	ENDIF
	IF get_in_counter_sweet2 = 4
		PRINT_NOW ( SMOX_AE ) 3000 1 
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0

		IF IS_CAR_DEAD big_smoke_car
			//PRINT_NOW (SWE2_C) 10000 1 //~r~Trashed smokes ride!
			//GOTO mission_sweet2_failed
			RETURN
		ENDIF
		IF IS_CHAR_DEAD	big_smoke
			//PRINT_NOW (SWE2_B) 10000 1 //~r~Smoke is dead! 
			//GOTO mission_sweet2_failed
			RETURN
		ENDIF

	ENDWHILE

	get_in_counter_sweet2 ++

	IF get_in_counter_sweet2 > 4
		get_in_counter_sweet2 = 0
	ENDIF

RETURN





start_talking_sweet2:
  
	IF sweet2_audio_chat[sweet2_index] = SOUND_SWE3_AA
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_AF
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_AK
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GN
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_HA
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_HC
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 3000
		RETURN
	ENDIF

	IF sweet2_audio_chat[sweet2_index] = SOUND_SWE3_HF
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07A
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07C
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07E
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07J
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_JB
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 3000
		RETURN
	ENDIF

	IF sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GC
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GG
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GH
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GJ
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GK
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GL
		IF NOT IS_CHAR_DEAD	emmet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH emmet TRUE
			START_CHAR_FACIAL_TALK emmet 3000
		ENDIF
	ELSE
		IF NOT IS_CHAR_DEAD	big_smoke
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
			START_CHAR_FACIAL_TALK big_smoke 3000
		ENDIF
	ENDIF

RETURN


stop_talking_sweet2:

	IF sweet2_audio_chat[sweet2_index] = SOUND_SWE3_AA
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_AF
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_AK
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GN
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_HA
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_HC
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
		RETURN
	ENDIF

	IF sweet2_audio_chat[sweet2_index] = SOUND_SWE3_HF
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07A
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07C
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07E
	OR sweet2_audio_chat[sweet2_index] = SOUND_MSWE07J
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_JB
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
		RETURN
	ENDIF

	IF sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GC
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GG
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GH
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GJ
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GK
	OR sweet2_audio_chat[sweet2_index] = SOUND_SWE3_GL
		IF NOT IS_CHAR_DEAD	emmet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH emmet FALSE
			STOP_CHAR_FACIAL_TALK emmet
		ENDIF
	ELSE
		IF NOT IS_CHAR_DEAD	big_smoke
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke FALSE
			STOP_CHAR_FACIAL_TALK big_smoke
		ENDIF
	ENDIF

RETURN


set_gang_density_to_zero:

	SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT 0

	SET_ZONE_GANG_STRENGTH LIND1a GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH LIND1b GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH LIND2a GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH LIND2b GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH LIND3 GANG_FLAT 0

	SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 0 
	SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 0

RETURN

reset_gang_density:

	SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT 30

	SET_ZONE_GANG_STRENGTH LIND1a GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH LIND1b GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH LIND2a GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH LIND2b GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH LIND3 GANG_FLAT 20

	SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 30 
	SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 30
	SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 30

RETURN


}