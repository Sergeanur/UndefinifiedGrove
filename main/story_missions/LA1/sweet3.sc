MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* TWAR 2 ********************************************
// ********************************** CHICKEN WINGS ****************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME SWEET3

// Mission start stuff

GOSUB mission_start_sweet3

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_sweet3_failed
ENDIF

GOSUB mission_cleanup_sweet3

MISSION_END

{
 
// Variables for mission

LVAR_INT CurrentcarHealthValue COMPARE_CurrentcarHealthValue played_the_car_dialoge played_first_ouch_samples skip_sweet4_cutscene
LVAR_INT chicken_shop_blip drive_by_car1 got_to_drive_through started_to_kill_gang played_the_timer	ballas_cunts_not_dead
LVAR_INT drive_by_bloke1 drive_by_bloke2  got_to_hub_first_time	Return_Progress chicken_man1 chicken_man2  get_in_counter_sweet3
LVAR_INT drive_by_car1_blip sweet3_mate1 sweet3_mate2 sweet3_mate4 car_blow_up create_chicken_men sweet3_cell_index_end
LVAR_INT chicken_shop_blip_removed print_sweet3_text kill_them_all_sweet3D SW3_player_vehicle task_returned	baller_bloke_dead_in_car
LVAR_INT kill_them_all_sweet3 sweet3_audio_chat[13]	sweet3_chat_switch SW3_current_area_visible	drive_by_bloke_blip1 drive_by_bloke_blip2
LVAR_INT out_of_car1 out_of_car2 sweet3_audio_is_playing sweet3_index sweet3_cutscene_flag chicken_ciggy
LVAR_INT drive_by_bloke[2] drive_by_index sequence_initiated smokes_decision sweets_decision ryders_decision
LVAR_INT drive_by_bloke1_dead drive_by_bloke2_dead task_exit_burning_car1 task_exit_burning_car2 played_gang_help_text
LVAR_INT chicken_text_1 chicken_text_2 block_in_car_blocked chicken_char1 chicken_car1 sweet3_car2
LVAR_FLOAT SW3_playerX SW3_playerY SW3_playerZ
VAR_TEXT_LABEL $sweet3_chat[13]

// **************************************** Mission Start **********************************


sweet3_chat_switch:

SWITCH sweet3_chat_switch		   

	CONST_INT SWEET3_CHAT1 0
	CONST_INT SWEET3_CHAT2 1
	CONST_INT SWEET3_CHAT3 2
	CONST_INT SWEET3_CHAT4 3
	CONST_INT SWEET3_CHAT5 4
	CONST_INT SWEET3_CHAT6 5
	CONST_INT SWEET3_CHAT7 6
	CONST_INT SWEET3_CHAT8 7
	CONST_INT SWEET3_CHAT9 8
		

	CASE sweet3_CHAT2
		$sweet3_chat[0] = &SWE2_BA //How did Moms get killed? We gotta talk about it.
		$sweet3_chat[1] = &SWE2_BB //We all gotta talk about it! They was going for Sweet!
		$sweet3_chat[2] = &SWE2_BC //How you supposed to know – you know what people are like.
		$sweet3_chat[3] = &SWE2_BD //Say they have love for you, but won’t say a word. 
		$sweet3_chat[4] = &SWE2_BE //Too damn scared.
		$sweet3_chat[5] = &SWE2_BF //People say they saw a green Sabre doing the work, 
		$sweet3_chat[6] = &SWE2_BG //then speeding away.
		$sweet3_chat[7] = &SWE2_BH //Yeah, but people like to talk, don’t they?
		$sweet3_chat[8] = &SWE2_BJ //Anyway, that’s half of LA you’re talking about!
		$sweet3_chat[9] = &SWE2_BK //Yeah, that ain’t no help. Sorry.
		$sweet3_chat[10] = &SWE2_BL //Bro’?
		$sweet3_chat[11] = &SWE2_BM //They just sprayed the house. Didn’t see jack.

		sweet3_audio_chat[0] = SOUND_SWE2_BA //How did Moms get killed? We gotta talk about it.
		sweet3_audio_chat[1] = SOUND_SWE2_BB //We all gotta talk about it! They was going for Sweet!
		sweet3_audio_chat[2] = SOUND_SWE2_BC //How you supposed to know – you know what people are like.
		sweet3_audio_chat[3] = SOUND_SWE2_BD //Say they have love for you, but won’t say a word. 
		sweet3_audio_chat[4] = SOUND_SWE2_BE //Too damn scared.
		sweet3_audio_chat[5] = SOUND_SWE2_BF //People say they saw a green Sabre doing the work, 
		sweet3_audio_chat[6] = SOUND_SWE2_BG //then speeding away.
		sweet3_audio_chat[7] = SOUND_SWE2_BH //Yeah, but people like to talk, don’t they?
		sweet3_audio_chat[8] = SOUND_SWE2_BJ //Anyway, that’s half of LA you’re talking about!
		sweet3_audio_chat[9] = SOUND_SWE2_BK //Yeah, that ain’t no help. Sorry.
		sweet3_audio_chat[10] = SOUND_SWE2_BL //Bro’?
		sweet3_audio_chat[11] = SOUND_SWE2_BM //They just sprayed the house. Didn’t see jack.

		sweet3_cell_index_end = 11
	BREAK

	CASE sweet3_CHAT3
		$sweet3_chat[0] = &SWE2_CA //Hit it! Go, go ,go!
		$sweet3_chat[1] = &SWE2_CC //Hit the gas, we gottaa ice those fools!
		$sweet3_chat[2] = &SWE2_GA //Ain’t you shooting, Smoke?
		$sweet3_chat[3] = &SWE2_GB //’ll shoot when I’m done eating.
		$sweet3_chat[4] = &SWE2_GC //Stop stuffing your mouth!
		$sweet3_chat[5] = &SWE2_GD //I ain’t stuffing it, homie. I am enjoying my meal.
		$sweet3_chat[6] = &SWE2_GE //Kill that asshole.	
		$sweet3_chat[7] = &SWE2_HA //Smoke, stop stuffing and start popping Ballas!
		$sweet3_chat[8] = &SWE2_HB //I’m trying to enjoy my food!
		$sweet3_chat[9] = &SWE2_HC //And those fools are trying to enjoy our deaths!
		$sweet3_chat[10] = &SWE2_HD //Now, c’mon, Smoke, Shoot!
		$sweet3_chat[11] = &SWE2_HE //Just finishing my fries…

		sweet3_audio_chat[0] = SOUND_SWE2_CA //Hit it! Go, go ,go!
		sweet3_audio_chat[1] = SOUND_SWE2_CC //Hit the gas, we gottaa ice those fools!
		sweet3_audio_chat[2] = SOUND_SWE2_GA //Ain’t you shooting, Smoke?
		sweet3_audio_chat[3] = SOUND_SWE2_GB //’ll shoot when I’m done eating.
		sweet3_audio_chat[4] = SOUND_SWE2_GC //Stop stuffing your mouth!
		sweet3_audio_chat[5] = SOUND_SWE2_GD //I ain’t stuffing it, homie. I am enjoying my meal.
		sweet3_audio_chat[6] = SOUND_SWE2_GE //Kill that asshole.	
		sweet3_audio_chat[7] = SOUND_SWE2_HA //Smoke, stop stuffing and start popping Ballas!
		sweet3_audio_chat[8] = SOUND_SWE2_HB //I’m trying to enjoy my food!
		sweet3_audio_chat[9] = SOUND_SWE2_HC //And those fools are trying to enjoy our deaths!
		sweet3_audio_chat[10] = SOUND_SWE2_HD //Now, c’mon, Smoke, Shoot!
		sweet3_audio_chat[11] = SOUND_SWE2_HE //Just finishing my fries…

		sweet3_cell_index_end = 11
	BREAK

	CASE sweet3_CHAT4
		$sweet3_chat[0] = &SWE2_MA //Well that was some shit!
		$sweet3_chat[1] = &SWE2_MB //Yeah, heh-heh, those Ballas fools won’t try that again!
		$sweet3_chat[2] = &SWE2_MC //Carl, let’s get back to the Grove!
		$sweet3_chat[3] = &SWE2_MD //Yo, I’m on it.
		$sweet3_chat[4] = &SWE2_ME //Man, that filled a hole!
		$sweet3_chat[5] = &SWE2_MF //You chubby motherfucker! 
		$sweet3_chat[6] = &SWE2_MG //Next time, you shooting or I’ll cap you myself!
		$sweet3_chat[7] = &SWE2_MH //Smoke, you’re wide, man, wide!
		$sweet3_chat[8] = &SWE2_MJ //And that’s why you love me, homies, heh heh!

		sweet3_audio_chat[0] = SOUND_SWE2_MA //Well that was some shit!
		sweet3_audio_chat[1] = SOUND_SWE2_MB //Yeah, heh-heh, those Ballas fools won’t try that again!
		sweet3_audio_chat[2] = SOUND_SWE2_MC //Carl, let’s get back to the Grove!
		sweet3_audio_chat[3] = SOUND_SWE2_MD //Yo, I’m on it.
		sweet3_audio_chat[4] = SOUND_SWE2_ME //Man, that filled a hole!
		sweet3_audio_chat[5] = SOUND_SWE2_MF //You chubby motherfucker! 
		sweet3_audio_chat[6] = SOUND_SWE2_MG //Next time, you shooting or I’ll cap you myself!
		sweet3_audio_chat[7] = SOUND_SWE2_MH //Smoke, you’re wide, man, wide!
		sweet3_audio_chat[8] = SOUND_SWE2_MJ //And that’s why you love me, homies, heh heh!

		sweet3_cell_index_end = 8
	BREAK

	CASE sweet3_CHAT5
		$sweet3_chat[0] = &SWE2_NA //One up to the Grove!
		$sweet3_chat[1] = &SWE2_NB //Whatchoo talking about, Smoke?
		$sweet3_chat[2] = &SWE2_NC //All you managed to do was eat my damn food too!
		$sweet3_chat[3] = &SWE2_ND //It was getting cold!
		$sweet3_chat[4] = &SWE2_NE //Yo, you cats coming in for a beer?
		$sweet3_chat[5] = &SWE2_NF //No, dude, I need to get back to my house.
		$sweet3_chat[6] = &SWE2_NG //CJ, gimme a ride?
		$sweet3_chat[7] = &SWE2_NH //Sure, why not? Later, dudes.

		sweet3_audio_chat[0] = SOUND_SWE2_NA //One up to the Grove!
		sweet3_audio_chat[1] = SOUND_SWE2_NB //Whatchoo talking about, Smoke?
		sweet3_audio_chat[2] = SOUND_SWE2_NC //All you managed to do was eat my damn food too!
		sweet3_audio_chat[3] = SOUND_SWE2_ND //It was getting cold!
		sweet3_audio_chat[4] = SOUND_SWE2_NE //Yo, you cats coming in for a beer?
		sweet3_audio_chat[5] = SOUND_SWE2_NF //No, dude, I need to get back to my house.
		sweet3_audio_chat[6] = SOUND_SWE2_NG //CJ, gimme a ride?
		sweet3_audio_chat[7] = SOUND_SWE2_NH //Sure, why not? Later, dudes.

		sweet3_cell_index_end = 7
	BREAK

	CASE sweet3_CHAT6
		$sweet3_chat[0] = &SWE2_OA //What was with you back there, Smoke?
		$sweet3_chat[1] = &SWE2_OB //If you can eat your food whilst all about you are losing theirs
		$sweet3_chat[2] = &SWE2_OC //and blaming you, homie!
		$sweet3_chat[3] = &SWE2_OD //What?
		$sweet3_chat[4] = &SWE2_OE //Nothin’.
		$sweet3_chat[5] = &SWE2_OF //How come you moved out the Grove, Smoke?
		$sweet3_chat[6] = &SWE2_OG //Got some money from my Aunt - It’s a nice place.
		$sweet3_chat[7] = &SWE2_OH //But the Grove’s still my heart - all my down dogs are there.
		$sweet3_chat[8] = &SWE2_OJ //But true, holmes.


		sweet3_audio_chat[0] = SOUND_SWE2_OA //What was with you back there, Smoke?
		sweet3_audio_chat[1] = SOUND_SWE2_OB //If you can eat your food whilst all about you are losing theirs
		sweet3_audio_chat[2] = SOUND_SWE2_OC //and blaming you, homie!
		sweet3_audio_chat[3] = SOUND_SWE2_OD //What?
		sweet3_audio_chat[4] = SOUND_SWE2_OE //Nothin’.
		sweet3_audio_chat[5] = SOUND_SWE2_OF //How come you moved out the Grove, Smoke?
		sweet3_audio_chat[6] = SOUND_SWE2_OG //Got some money from my Aunt - It’s a nice place.
		sweet3_audio_chat[7] = SOUND_SWE2_OH //But the Grove’s still my heart - all my down dogs are there.
		sweet3_audio_chat[8] = SOUND_SWE2_OJ //But true, holmes.

		sweet3_cell_index_end = 8
	BREAK

	CASE sweet3_CHAT7
		$sweet3_chat[0] = &SWE2_PA	//Thanks Carl, I’ve missed having you around!
		$sweet3_chat[1] = &SWE2_PB	//I wish Sweet thought that way.
		$sweet3_chat[2] = &SWE2_PC	//He don’t mean it, CJ.
		$sweet3_chat[3] = &SWE2_PD	//He’s still all to’up about yo moms.
		$sweet3_chat[4] = &SWE2_PE	//Here, get yourself some cess or somethin’- relax.


		sweet3_audio_chat[0] = SOUND_SWE2_PA //Thanks Carl, I’ve missed having you around!
		sweet3_audio_chat[1] = SOUND_SWE2_PB //I wish Sweet thought that way.
		sweet3_audio_chat[2] = SOUND_SWE2_PC //He don’t mean it, CJ.
		sweet3_audio_chat[3] = SOUND_SWE2_PD //He’s still all to’up about yo moms.
		sweet3_audio_chat[4] = SOUND_SWE2_PE //Here, get yourself some cess or somethin’- relax.

		sweet3_cell_index_end = 4
	BREAK		

	CASE sweet3_CHAT8
		$sweet3_chat[0] = &SWE2_DA	//My Special!
		$sweet3_chat[1] = &SWE2_DB	//Aw, shit! You got ketchup all over the seat!
		$sweet3_chat[2] = &SWE2_DC	//The business at hand, motherfucker, the business at hand!
		$sweet3_chat[3] = &SWE2_DD	//But these was clean pants!


		sweet3_audio_chat[0] = SOUND_SWE2_DA //My Special!
		sweet3_audio_chat[1] = SOUND_SWE2_DB //Aw, shit! You got ketchup all over the seat!
		sweet3_audio_chat[2] = SOUND_SWE2_DC //The business at hand, motherfucker, the business at hand!
		sweet3_audio_chat[3] = SOUND_SWE2_DD //But these was clean pants!

		sweet3_cell_index_end = 3
	BREAK

	CASE sweet3_CHAT9
		$sweet3_chat[0] = &SWE2_EA	//CJ, watch the damn road!
		$sweet3_chat[1] = &SWE2_EB	//Chill. motherfucker, this ain’t a Sunday drive situation!
		$sweet3_chat[2] = &SWE2_EC	//My soda! It’s all over the fucking floor!
		$sweet3_chat[3] = &SWE2_ED	//Well you can suck it up once we’re done.
		$sweet3_chat[4] = &SWE2_EE	//Now keep you eyes on the Ballas car!


		sweet3_audio_chat[0] = SOUND_SWE2_EA	//CJ, watch the damn road!
		sweet3_audio_chat[1] = SOUND_SWE2_EB	//Chill. motherfucker, this ain’t a Sunday drive situation!
		sweet3_audio_chat[2] = SOUND_SWE2_EC	//My soda! It’s all over the fucking floor!
		sweet3_audio_chat[3] = SOUND_SWE2_ED	//Well you can suck it up once we’re done.
		sweet3_audio_chat[4] = SOUND_SWE2_EE	//Now keep you eyes on the Ballas car!

		sweet3_cell_index_end = 4
	BREAK


//SMOKE	//My Special!
//RYDER	//Aw, shit! You got ketchup all over the seat!
//SMOKE	//The business at hand, motherfucker, the business at hand!
//RYDER	//But these was clean pants!
		
//RYDER	//CJ, watch the damn road!
//SMOKE	//Chill. motherfucker, this ain’t a Sunday drive situation!
//RYDER	//My soda! It’s all over the fucking floor!
//SWEET	//Well you can suck it up once we’re done.
//SWEET	//Now keep you eyes on the Ballas car!


/*		
SWE2_KA	//We’re on fire, everybody for themselves!
SWE2_KB	//She’s gonna blow, bail out!
SWE2_KC	//Our ride’s totalled – everybody out!
		
SWE2_LA	//Dammit, we were too late!
SWE2_LB	//We let down our homies!
SWE2_LC	//We let down the ‘hood!

SWE2_JA	//Aw shit, CJ’s out!
SWE2_JB	//CJ! Sweet, CJ’s down!
SWE2_JC	//He’s bluffing, aren’t you CJ! CJ?
			
SWE2_DA	//My Special!
SWE2_DB	//Aw shit! There’s ketchup all over the seat!
SWE2_DC	//The business at hand, motherfucker, the business at hand!
SWE2_DD	//But these were clean pants!
		
SWE2_EA //CJ, watch the damn road!
SWE2_EB //Chill, motherfucker, this is not a Sunday drive situation!
SWE2_EC //My cola! It’s it’s all over the floor!
SWE2_ED //Well you can suck it up once we’re done.
SWE2_EE //Now keep your eyes open for that Ballas car!
*/
	
/*
SWE2_CA	//Hit it! Go, go ,go!
SWE2_CB	//Get after them, CJ!
SWE2_CC	//Hit the gas, we gottaa ice those fools!
	
SWE2_FA	//There they go!
SWE2_FC	//Ballas! See ‘em? Over there!
SWE2_FD	//Pull alongside, CJ.
SWE2_FE	//Ready, dogs?
SWE2_CC //Hit the gas, we gottaa ice those fools!
SWE2_FB	//Get up alongside those motherfuckers, CJ!

*/
		




ENDSWITCH

RETURN



mission_start_sweet3:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1
drive_by_bloke1_dead = 0
drive_by_bloke2_dead = 0
got_to_drive_through = 0
sequence_initiated = 0
out_of_car1 = 0
out_of_car2 = 0
drive_by_index = 0
chicken_text_1 = 0
chicken_text_2 = 0
block_in_car_blocked = 0
chicken_shop_blip_removed = 0
print_sweet3_text = 0
got_to_hub_first_time = 0
task_exit_burning_car1 = 0
task_exit_burning_car2 = 0
started_to_kill_gang = 0
car_blow_up = 0
played_the_timer = 0
get_in_counter_sweet3 = 0

drive_by_bloke[0] = drive_by_bloke1
drive_by_bloke[1] = drive_by_bloke2


WAIT 0

LOAD_MISSION_TEXT SWEET3 

// ****************************************START OF CUTSCENE********************************
REQUEST_MODEL GREENWOO

WHILE NOT HAS_MODEL_LOADED GREENWOO
	WAIT 0

ENDWHILE

SWITCH_CAR_GENERATOR gen_car7 0
CLEAR_AREA 2516.6357 -1675.6909 13.0101 15.0 TRUE
CLEAR_AREA 2508.7949 -1671.7073 12.3892 15.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO &GROVE4L_ 
CREATE_CAR GREENWOO 2508.7 -1671.7 12.3 sweet_car
SET_CAR_PROOFS sweet_car FALSE TRUE FALSE FALSE FALSE
CHANGE_CAR_COLOUR sweet_car 59 34

SET_CAR_HEADING sweet_car 166.9
SET_CAN_BURST_CAR_TYRES sweet_car FALSE
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO

LOAD_CUTSCENE SWEET2A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
//SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

// ****************************************END OF CUTSCENE**********************************

// request models
LOAD_SPECIAL_CHARACTER 1 smoke 
LOAD_SPECIAL_CHARACTER 2 sweet 
LOAD_SPECIAL_CHARACTER 3 ryder2
REQUEST_MODEL TEC9
//REQUEST_MODEL WMYBELL
REQUEST_MODEL cigar

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
OR NOT HAS_MODEL_LOADED TEC9
//OR NOT HAS_MODEL_LOADED WMYBELL
	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED cigar
	WAIT 0
ENDWHILE


IF NOT IS_CAR_DEAD sweet_car
	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2511.5139 -1671.4640 12.4607 big_smoke
	SET_ANIM_GROUP_FOR_CHAR big_smoke fatman
	SET_CHAR_HEADING big_smoke 84.5
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER big_smoke sweet_car 10000 1
	GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_TEC9 99999
	SET_CHAR_NEVER_TARGETTED big_smoke TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS big_smoke FALSE
	SET_CHAR_HEALTH big_smoke 500
	SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED big_smoke TRUE
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY smokes_decision
	SET_CHAR_DECISION_MAKER big_smoke smokes_decision
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE

	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL02 2507.8862 -1667.8005 12.3881 sweet
	SET_ANIM_GROUP_FOR_CHAR sweet gang2
	SET_CHAR_HEADING sweet 167.8
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 10000 2
	GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_TEC9 99999
	SET_CHAR_NEVER_TARGETTED sweet TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
	SET_CHAR_HEALTH sweet 500
	SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE									  
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED sweet TRUE
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY sweets_decision
	SET_CHAR_DECISION_MAKER sweet sweets_decision
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE

	CREATE_CHAR PEDTYPE_MISSION1 SPECIAL03 2506.3479 -1673.5907 12.3784 ryder
	SET_ANIM_GROUP_FOR_CHAR ryder gang1
	SET_CHAR_HEADING ryder 346.0
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER ryder sweet_car 10000 0
	GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_TEC9 99999
	SET_CHAR_NEVER_TARGETTED ryder TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
	SET_CHAR_HEALTH ryder 500
	SET_CHAR_CANT_BE_DRAGGED_OUT ryder TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED ryder TRUE
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY ryders_decision
	SET_CHAR_DECISION_MAKER ryder ryders_decision
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR ryder FALSE

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_GANG_GROVE PEDTYPE_MISSION1
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_GANG_GROVE

ENDIF

SET_RADIO_CHANNEL RS_DISCO_FUNK_SOUL 

ADD_BLIP_FOR_CAR sweet_car chicken_shop_blip
SET_BLIP_AS_FRIENDLY chicken_shop_blip TRUE

// fades the screen in 
SET_FADING_COLOUR 0 0 0

CLEAR_AREA 2516.54 -1671.20 12.87 2.0 TRUE

SET_CHAR_COORDINATES scplayer 2516.54 -1671.20 12.87
SET_CHAR_HEADING scplayer 81.20

LOAD_SCENE_IN_DIRECTION 2516.54 -1671.20 12.87 81.20

SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

DO_FADE 1000 FADE_IN

IF IS_CAR_DEAD sweet_car
	GOTO mission_sweet3_failed
ENDIF

TIMERB = 0				
			
LOAD_MISSION_AUDIO 1 SOUND_SWE2_AA //Carl, you drive.Smoke looks like he’s gonna pass out.			

WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ENDIF

ENDWHILE

PLAY_MISSION_AUDIO 1
PRINT_NOW ( SWE2_AA ) 10000 1 //Carl, you drive.Smoke looks like he’s gonna pass out. 

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
	WAIT 0
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ENDIF

ENDWHILE

sweet3_index = 0
sweet3_audio_is_playing = 0
sweet3_cutscene_flag = 0
sweet3_chat_switch = sweet3_CHAT2
GOSUB sweet3_chat_switch
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ENDIF
	
PRINT_NOW ( TWAR2_A ) 6000 1 // Get in the car.

TIMERB = 0
blob_flag = 0
chicken_shop_blip_removed = 0
	
//GOTO return_to_the_hood	 //TEST
										 
WHILE NOT LOCATE_CAR_3D sweet_car 2404.1 -1891.5 12.3 4.0 4.0 4.0 blob_flag	//GO TO THE CHICKEN DRIVE THRU
OR NOT IS_CHAR_SITTING_IN_CAR scplayer sweet_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS sweet_car
	WAIT 0
	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_sweet3_passed
	ENDIF
	/*
	IF create_chicken_men = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2397.9 -1895.5 12.3 100.0 100.0 10.0 FALSE
			CREATE_CHAR PEDTYPE_CIVMALE WMYBELL 2393.4 -1898.8 12.55 chicken_man1
			CREATE_CHAR PEDTYPE_CIVMALE WMYBELL 2392.2 -1898.8 12.55 chicken_man2
			TASK_TURN_CHAR_TO_FACE_CHAR chicken_man1 chicken_man2
			TASK_TURN_CHAR_TO_FACE_CHAR chicken_man2 chicken_man1	
			TASK_CHAT_WITH_CHAR chicken_man1 chicken_man2 TRUE TRUE
			TASK_CHAT_WITH_CHAR chicken_man2 chicken_man1 FALSE TRUE
			CREATE_OBJECT CIGAR 2393.4 -1898.8 12.55 chicken_ciggy
			TASK_PICK_UP_OBJECT chicken_man1 chicken_ciggy 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1
			MARK_MODEL_AS_NO_LONGER_NEEDED WMYBELL
			MARK_MODEL_AS_NO_LONGER_NEEDED cigar
			create_chicken_men = 1
		ENDIF 
	ENDIF
	*/	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ELSE
		IF NOT IS_CAR_DEAD sweet_car
			IF car_blow_up = 0
				IF IS_CHAR_IN_CAR scplayer sweet_car
					IF chicken_shop_blip_removed = 0
						REMOVE_BLIP chicken_shop_blip
						ADD_BLIP_FOR_COORD 2404.1 -1891.5 12.3 chicken_shop_blip
						PRINT_NOW ( TWAR2_C ) 6000 1 //drive to ~Y~Cluckin' Bell~s~ drive through.
						blob_flag = 1
						TIMERB = 0
						chicken_shop_blip_removed = 1
					ENDIF

					IF played_the_timer = 0
						IF TIMERB > 4000	
							GOSUB load_and_play_audio_sweet3
							played_the_timer = 1
						ENDIF
					ELSE
						GOSUB load_and_play_audio_sweet3
					ENDIF

				ELSE
					IF chicken_shop_blip_removed = 1
						REMOVE_BLIP chicken_shop_blip
						ADD_BLIP_FOR_CAR sweet_car chicken_shop_blip
						SET_BLIP_AS_FRIENDLY chicken_shop_blip TRUE
						GOSUB get_back_in_the_car_sweet3_2
						PRINT_NOW ( TW2_X ) 3000 1 //~s~Get back in the ~b~car~s~
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
						STOP_CHAR_FACIAL_TALK scplayer
						blob_flag = 0
						chicken_shop_blip_removed = 0
					ENDIF
				ENDIF
			ENDIF

			IF NOT IS_CAR_HEALTH_GREATER sweet_car 250
				LOAD_MISSION_AUDIO 2 SOUND_SWE2_KC
				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0

					IF IS_CHAR_DEAD	sweet
					//OR failed_sweet3 = 1
						PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	ryder
					//OR failed_sweet3 = 1
						PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	big_smoke
					//OR failed_sweet3 = 1
						PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CAR_DEAD sweet_car
					//OR failed_sweet3 = 1
						PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
						GOTO mission_sweet3_failed
					ENDIF

				ENDWHILE
				CLEAR_MISSION_AUDIO 1
				PLAY_MISSION_AUDIO 2
				PRINT_NOW ( SWE2_KC ) 2000 1 //Our ride’s totalled – everybody out!

				IF car_blow_up = 0
					IF NOT IS_CAR_DEAD sweet_car
						IF NOT IS_CHAR_DEAD	big_smoke
							TASK_LEAVE_CAR_IMMEDIATELY big_smoke sweet_car
							TASK_SMART_FLEE_CHAR big_smoke scplayer 100.0 10000
						ENDIF 
						IF NOT IS_CHAR_DEAD	ryder
							TASK_LEAVE_CAR_IMMEDIATELY ryder sweet_car
							TASK_SMART_FLEE_CHAR ryder scplayer 100.0 10000
						ENDIF
						IF NOT IS_CHAR_DEAD sweet
							TASK_LEAVE_CAR_IMMEDIATELY sweet sweet_car
							TASK_SMART_FLEE_CHAR sweet scplayer 100.0 10000
						ENDIF
						car_blow_up = 1
					ENDIF
				ENDIF

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				
					IF IS_CHAR_DEAD	sweet
						PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	ryder
						PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	big_smoke
						PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CAR_DEAD sweet_car
						PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
						GOTO mission_sweet3_failed
					ENDIF

				ENDWHILE

				PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
				GOTO mission_sweet3_failed	
			ENDIF
		ENDIF		
	ENDIF

	IF IS_CHAR_DEAD	sweet
		PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CHAR_DEAD	big_smoke
		PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ENDIF

ENDWHILE

SWITCH_WIDESCREEN ON 
SET_PLAYER_CONTROL player1 OFF
CLEAR_PRINTS

SET_FIXED_CAMERA_POSITION 2405.9749 -1882.4752 15.2036 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2405.5693 -1883.3892 15.2158 JUMP_CUT

// **************************************DRIVE THROUGH CUT SCENE******************************************************

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

//SET_CAR_DENSITY_MULTIPLIER 0.0
//SET_PED_DENSITY_MULTIPLIER 0.0
		
	//LOAD_SCENE_IN_DIRECTION 2394.2866 -1918.8990 12.3828 267.6804

	IF NOT IS_CHAR_IN_ANY_CAR scplayer
		SET_CHAR_COORDINATES scplayer 2364.1926 -1914.0768 12.4235 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2364.1926 -1914.0768 12.4235
	ENDIF
	
	DELETE_CHAR big_smoke
	DELETE_CHAR sweet
	DELETE_CHAR ryder
	DELETE_CAR sweet_car

	MARK_MODEL_AS_NO_LONGER_NEEDED FAM3
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
	MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
	MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
	//MARK_MODEL_AS_NO_LONGER_NEEDED WMYBELL
	MARK_MODEL_AS_NO_LONGER_NEEDED cigar
	
	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 2
	UNLOAD_SPECIAL_CHARACTER 3

	LOAD_SCENE_IN_DIRECTION 2397.6443 -1880.2762 23.8686 160.0

	CLEAR_AREA 2404.1 -1891.5 12.3 120.0 TRUE
 
MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
SWITCH_STREAMING OFF

LOAD_CUTSCENE SWEET2B
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE

CLEAR_AREA 2404.1 -1891.5 12.3 120.0 TRUE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
//SET_CAR_DENSITY_MULTIPLIER 1.0
//SET_PED_DENSITY_MULTIPLIER 1.0

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

CLEAR_CUTSCENE

// ****************************************END OF CUTSCENE**********************************

REQUEST_MODEL VOODOO
REQUEST_MODEL FAM3
REQUEST_MODEL FAM2
REQUEST_MODEL BALLAS2
REQUEST_MODEL GREENWOO
LOAD_SPECIAL_CHARACTER 1 smoke 
LOAD_SPECIAL_CHARACTER 2 sweet 
LOAD_SPECIAL_CHARACTER 3 ryder2
REQUEST_MODEL TEC9

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED VOODOO
OR NOT HAS_MODEL_LOADED FAM3
OR NOT HAS_MODEL_LOADED BALLAS2
OR NOT HAS_MODEL_LOADED GREENWOO
	WAIT 0

ENDWHILE

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
OR NOT HAS_MODEL_LOADED TEC9
	WAIT 0

ENDWHILE

SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0

REMOVE_BLIP chicken_shop_blip

	//TASK_CAR_DRIVE_TO_COORD -1 -1 2230.11 -1659.91 14.30 25.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS

OPEN_SEQUENCE_TASK kill_them_all_sweet3
	TASK_CAR_DRIVE_TO_COORD -1 -1 2410.7388 -1960.0266 12.3906 13.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 2326.9031 -1969.6761 12.3738 15.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 2235.2625 -1891.3008 12.3828 18.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 2094.3066 -1891.8560 12.3738 18.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 2105.8198 -1754.1766 12.3984 20.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 2321.9136 -1736.0938 12.3828 20.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 2498.32 -1658.28 12.36 20.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
	TASK_CAR_DRIVE_TO_COORD -1 -1 2502.89 -1674.71 12.37 15.0 MODE_NORMAL VOODOO DRIVINGMODE_AVOIDCARS
CLOSE_SEQUENCE_TASK kill_them_all_sweet3

	CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO &GROVE4L_ 
	CREATE_CAR GREENWOO 2396.36 -1917.96 12.38 sweet_car
	SET_CAR_PROOFS sweet_car FALSE TRUE FALSE FALSE FALSE
	CHANGE_CAR_COLOUR sweet_car 59 34
	SET_CAN_BURST_CAR_TYRES sweet_car FALSE
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO

	CREATE_CHAR_AS_PASSENGER sweet_car PEDTYPE_MISSION1 SPECIAL01 2 big_smoke
	SET_ANIM_GROUP_FOR_CHAR big_smoke fatman
	SET_CHAR_HEADING big_smoke 84.5
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER big_smoke sweet_car 10000 1
	GIVE_WEAPON_TO_CHAR big_smoke WEAPONTYPE_TEC9 99999
	SET_CHAR_NEVER_TARGETTED big_smoke TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS big_smoke FALSE
	SET_CHAR_HEALTH big_smoke 500
	SET_CHAR_CANT_BE_DRAGGED_OUT big_smoke TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED big_smoke TRUE
	SET_CHAR_DECISION_MAKER big_smoke smokes_decision
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR big_smoke FALSE

	CREATE_CHAR_AS_PASSENGER sweet_car PEDTYPE_MISSION1 SPECIAL02 1 sweet
	SET_ANIM_GROUP_FOR_CHAR sweet gang2
	SET_CHAR_HEADING sweet 167.8
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER sweet sweet_car 10000 2
	GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_TEC9 99999
	SET_CHAR_NEVER_TARGETTED sweet TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE
	SET_CHAR_HEALTH sweet 500
	SET_CHAR_CANT_BE_DRAGGED_OUT sweet TRUE									  
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED sweet TRUE
	SET_CHAR_DECISION_MAKER sweet sweets_decision
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sweet FALSE

	CREATE_CHAR_AS_PASSENGER sweet_car PEDTYPE_MISSION1 SPECIAL03 0 ryder
	SET_ANIM_GROUP_FOR_CHAR ryder gang1
	SET_CHAR_HEADING ryder 346.0
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER ryder sweet_car 10000 0
	GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_TEC9 99999
	SET_CHAR_NEVER_TARGETTED ryder TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
	SET_CHAR_HEALTH ryder 500
	SET_CHAR_CANT_BE_DRAGGED_OUT ryder TRUE
	SET_CHAR_STAY_IN_CAR_WHEN_JACKED ryder TRUE
	SET_CHAR_DECISION_MAKER ryder ryders_decision
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR ryder FALSE

IF NOT IS_CAR_DEAD sweet_car
	//FREEZE_CAR_POSITION_AND_DONT_LOAD_COLLISION sweet_car FALSE
	CLEAR_AREA 2396.36 -1917.96 12.38 10.0 TRUE
	SET_CAR_COORDINATES sweet_car 2396.36 -1917.96 12.38  
	SET_CAR_HEADING sweet_car 267.3
	IF NOT IS_CHAR_IN_CAR scplayer sweet_car
		WARP_CHAR_INTO_CAR scplayer sweet_car
	ENDIF
ENDIF

CLEAR_AREA 2411.3098 -1928.8369 12.3906 10.0 TRUE
CREATE_CAR VOODOO 2411.3098 -1928.8369 12.3906 drive_by_car1
CHANGE_CAR_COLOUR drive_by_car1 22 22
SET_CAN_BURST_CAR_TYRES drive_by_car1 FALSE
SET_LOAD_COLLISION_FOR_CAR_FLAG drive_by_car1 FALSE
SET_CAR_HEALTH drive_by_car1 3000

LOCK_CAR_DOORS drive_by_car1 CARLOCK_LOCKOUT_PLAYER_ONLY
SET_CAR_HEADING drive_by_car1 178.7106
ADD_BLIP_FOR_CAR drive_by_car1 drive_by_car1_blip

CREATE_CHAR_INSIDE_CAR drive_by_car1 PEDTYPE_MISSION2 ballas2 drive_by_bloke[0]
SET_CHAR_SUFFERS_CRITICAL_HITS drive_by_bloke[0] FALSE
//SET_CHAR_CAN_BE_SHOT_IN_VEHICLE drive_by_bloke[0] FALSE
//SET_CHAR_HEALTH drive_by_bloke[0] 120
GIVE_WEAPON_TO_CHAR drive_by_bloke[0] WEAPONTYPE_TEC9 99999
SET_CURRENT_CHAR_WEAPON drive_by_bloke[0] WEAPONTYPE_TEC9
SET_CHAR_ACCURACY drive_by_bloke[0] 40
PERFORM_SEQUENCE_TASK drive_by_bloke[0] kill_them_all_sweet3	//DRIVE TO HUB
CLEAR_SEQUENCE_TASK kill_them_all_sweet3

CREATE_CHAR_AS_PASSENGER drive_by_car1 PEDTYPE_MISSION2 ballas2 0 drive_by_bloke[1]
SET_CHAR_SUFFERS_CRITICAL_HITS drive_by_bloke[1] FALSE
//SET_CHAR_CAN_BE_SHOT_IN_VEHICLE drive_by_bloke[1] FALSE
//SET_CHAR_HEALTH drive_by_bloke[1] 120
GIVE_WEAPON_TO_CHAR drive_by_bloke[1] WEAPONTYPE_TEC9 99999
SET_CURRENT_CHAR_WEAPON drive_by_bloke[1] WEAPONTYPE_TEC9
SET_CHAR_ACCURACY drive_by_bloke[1] 40
TASK_DRIVE_BY drive_by_bloke[1] -1 sweet_car 0.0 0.0 0.0 5000.0 DRIVEBY_AI_ALL_DIRN TRUE 60


CREATE_CHAR PEDTYPE_MISSION1 FAM2 2506.45 -1681.02 12.53 sweet3_mate1 //good guy Gang bloke1
SET_CHAR_HEADING sweet3_mate1 80.0
SET_CHAR_HEALTH sweet3_mate1 30
GIVE_WEAPON_TO_CHAR sweet3_mate1 WEAPONTYPE_TEC9 99999
SET_CHAR_STAY_IN_SAME_PLACE sweet3_mate1 TRUE
SET_CHAR_NEVER_TARGETTED sweet3_mate1 TRUE

CREATE_CHAR PEDTYPE_MISSION1 FAM3 2507.3 -1680.1 13.0 sweet3_mate2 //good guy Gang bloke2
SET_CHAR_HEADING sweet3_mate2 90.0
SET_CHAR_HEALTH sweet3_mate2 30
GIVE_WEAPON_TO_CHAR sweet3_mate2 WEAPONTYPE_TEC9 99999
SET_CHAR_STAY_IN_SAME_PLACE sweet3_mate2 TRUE
SET_CHAR_NEVER_TARGETTED sweet3_mate2 TRUE

TASK_CHAT_WITH_CHAR sweet3_mate1 sweet3_mate2 TRUE TRUE
TASK_CHAT_WITH_CHAR sweet3_mate2 sweet3_mate1 FALSE TRUE

IF NOT IS_CHAR_DEAD ryder
	IF NOT IS_CAR_DEAD sweet_car
		SET_CAR_HEALTH sweet_car 3100
		IF IS_CHAR_IN_CAR ryder sweet_car
			SET_CURRENT_CHAR_WEAPON ryder WEAPONTYPE_TEC9
			TASK_DRIVE_BY ryder -1 drive_by_car1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_ALL_DIRN TRUE 45
		ENDIF
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD sweet
	IF NOT IS_CAR_DEAD sweet_car
		IF IS_CHAR_IN_CAR sweet sweet_car
			TASK_DRIVE_BY sweet -1 drive_by_car1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_ALL_DIRN FALSE 45
		ENDIF
	ENDIF
ENDIF
			

MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
MARK_MODEL_AS_NO_LONGER_NEEDED FAM3						   
RESTORE_CAMERA_JUMPCUT
SET_CAMERA_BEHIND_PLAYER
TIMERB = 0
print_sweet3_text = 0
drive_by_index = 0
chicken_shop_blip_removed = 0


SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_GANG_GROVE PEDTYPE_MISSION1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_GANG_GROVE

SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION2 PEDTYPE_PLAYER1

LOAD_SCENE_IN_DIRECTION 2396.36 -1917.96 12.38 267.3

DO_FADE 1000 FADE_IN

sweet3_index = 0
sweet3_audio_is_playing = 0
sweet3_cutscene_flag = 0
sweet3_chat_switch = sweet3_CHAT3
GOSUB sweet3_chat_switch

played_the_timer = 0
played_gang_help_text = 0

CLEAR_WANTED_LEVEL player1
ENABLE_AMBIENT_CRIME FALSE

played_the_car_dialoge = 0

//VIEW_INTEGER_VARIABLE CurrentcarHealthValue CurrentcarHealthValue
//VIEW_INTEGER_VARIABLE COMPARE_CurrentcarHealthValue	COMPARE_CurrentcarHealthValue
//VIEW_INTEGER_VARIABLE played_the_car_dialoge played_the_car_dialoge

played_first_ouch_samples = 0
baller_bloke_dead_in_car = 0

GOTO skip_this_shite
	ADD_BLIP_FOR_CHAR drive_by_bloke[0] drive_by_bloke_blip1
	ADD_BLIP_FOR_CHAR drive_by_bloke[1] drive_by_bloke_blip2
skip_this_shite:

//WHILE NOT IS_CAR_DEAD drive_by_car1	//KILL THE BALLARS CAR
WHILE NOT IS_CHAR_DEAD drive_by_bloke[0]
OR NOT IS_CHAR_DEAD drive_by_bloke[1]

	WAIT 0

	IF NOT IS_CAR_DEAD drive_by_car1

		GET_AREA_VISIBLE SW3_current_area_visible
		
		IF CAN_PLAYER_START_MISSION player1
			IF SW3_current_area_visible = 0	 //DEATH CUT SCENE
				IF LOCATE_CAR_3D drive_by_car1 2502.89 -1674.71 12.37 8.0 8.0 5.0 FALSE
				AND NOT IS_CHAR_DEAD drive_by_bloke[0]
				AND NOT IS_CHAR_DEAD drive_by_bloke[1]
				//AND Return_Progress = 6
					
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE

					SET_PLAYER_CONTROL player1 OFF
					SWITCH_WIDESCREEN ON

					GET_AREA_VISIBLE SW3_current_area_visible

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer SW3_player_vehicle
						FREEZE_CAR_POSITION SW3_player_vehicle TRUE
					ELSE
						FREEZE_CHAR_POSITION scplayer TRUE
					ENDIF

					SET_AREA_VISIBLE 0
					REQUEST_COLLISION 2497.2988 -1679.5421
					LOAD_SCENE_IN_DIRECTION 2513.5444 -1682.8500 14.1195 41.0
					CLEAR_AREA 2513.5444 -1682.8500 14.1195 70.0 TRUE 
					SET_FIXED_CAMERA_POSITION 2514.3994 -1683.3451 14.2746 0.0 0.0 0.0 
					POINT_CAMERA_AT_POINT 2513.5444 -1682.8500 14.1195 JUMP_CUT

					IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
						IF NOT IS_CAR_DEAD drive_by_car1
							TASK_CAR_DRIVE_TO_COORD drive_by_bloke[0] drive_by_car1 2386.24 -1658.95 12.38 1.0 MODE_STRAIGHTLINE VOODOO DRIVINGMODE_PLOUGHTHROUGH
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD drive_by_bloke[1]
						CLEAR_CHAR_TASKS drive_by_bloke[1]
					ENDIF

					DO_FADE 500 FADE_IN

					TIMERA = 0
					WHILE TIMERA < 500
						WAIT 0
					ENDWHILE

					IF NOT IS_CAR_DEAD drive_by_car1
						IF NOT IS_CHAR_DEAD sweet3_mate2
							IF NOT IS_CHAR_DEAD drive_by_bloke[1]
								IF IS_CHAR_IN_CAR drive_by_bloke[1] drive_by_car1
									CLEAR_CHAR_TASKS drive_by_bloke[1]
									SET_CHAR_ACCURACY drive_by_bloke[1] 50
									SET_CHAR_SHOOT_RATE	drive_by_bloke[1] 90
									TASK_DRIVE_BY drive_by_bloke[1] -1 -1 2507.6501 -1681.2400 12.8469 5000.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								ENDIF
							ENDIF
						ENDIF
					ELSE
						GOTO return_to_the_hood
					ENDIF

					TIMERA = 0
					WHILE NOT IS_CHAR_DEAD sweet3_mate2
					OR NOT IS_CHAR_DEAD sweet3_mate1
						WAIT 0
						IF TIMERA > 2500
							IF NOT IS_CHAR_DEAD sweet3_mate2
								TASK_DIE sweet3_mate2
							ENDIF
						ENDIF
						IF TIMERA > 2000
							IF NOT IS_CHAR_DEAD sweet3_mate1
								TASK_DIE sweet3_mate1
							ENDIF
						ENDIF
					ENDWHILE
					/*
					IF NOT IS_CAR_DEAD drive_by_car1
						IF NOT IS_CHAR_DEAD sweet3_mate1
							IF NOT IS_CHAR_DEAD drive_by_bloke[1]
								IF IS_CHAR_IN_CAR drive_by_bloke[1] drive_by_car1
									CLEAR_CHAR_TASKS drive_by_bloke[1]
									SET_CHAR_ACCURACY drive_by_bloke[1] 100
									SET_CHAR_SHOOT_RATE	drive_by_bloke[1] 90
									TASK_DRIVE_BY drive_by_bloke[1] sweet3_mate1 -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_ALL_DIRN TRUE 100
								ENDIF
							ENDIF
						ENDIF
					ELSE
						GOTO return_to_the_hood
					ENDIF
					*/
					/*
					TIMERA = 0
					WHILE NOT IS_CHAR_DEAD sweet3_mate1
						WAIT 0
						IF TIMERA > 3000
							IF NOT IS_CHAR_DEAD sweet3_mate1
								TASK_DIE sweet3_mate1
							ENDIF
						ENDIF
					ENDWHILE
					*/	
					IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
						IF NOT IS_CAR_DEAD drive_by_car1
							TASK_CAR_DRIVE_TO_COORD drive_by_bloke[0] drive_by_car1 2386.24 -1658.95 12.38 30.0 MODE_STRAIGHTLINE VOODOO DRIVINGMODE_PLOUGHTHROUGH
						ENDIF
					ENDIF

					TIMERA = 0
					WHILE NOT TIMERA > 3000
						WAIT 0
					ENDWHILE

					DO_FADE 500 FADE_OUT

					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE

					IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
						CLEAR_CHAR_TASKS_IMMEDIATELY drive_by_bloke[0]
					ENDIF
					IF NOT IS_CHAR_DEAD	drive_by_bloke[1]
						CLEAR_CHAR_TASKS_IMMEDIATELY drive_by_bloke[1]
					ENDIF
					DELETE_CAR drive_by_car1
					DELETE_CHAR drive_by_bloke[0]
					DELETE_CHAR drive_by_bloke[1]
					SET_AREA_VISIBLE SW3_current_area_visible

					GET_CHAR_COORDINATES scplayer SW3_playerX SW3_playerY SW3_playerZ
					LOAD_SCENE SW3_playerX SW3_playerY SW3_playerZ
					WAIT 100


					//PRINT_NOW ( SWE3_I ) 6000 1 // The Flats capped your homies!
					SET_CAMERA_BEHIND_PLAYER
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON
					RESTORE_CAMERA_JUMPCUT
					IF IS_CHAR_IN_ANY_CAR scplayer
						IF NOT IS_CAR_DEAD SW3_player_vehicle
							FREEZE_CAR_POSITION SW3_player_vehicle FALSE
						ENDIF
					ELSE
						FREEZE_CHAR_POSITION scplayer FALSE
					ENDIF
					DO_FADE 500 FADE_IN

				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF IS_CAR_DEAD drive_by_car1
		IF ballas_cunts_not_dead = 0
			REMOVE_BLIP	drive_by_car1_blip
			GOSUB drive_by_the_ballers

			PRINT_NOW ( K_BALLA ) 6000 1 // Kill the Baller gang member
			ballas_cunts_not_dead = 1
		ENDIF
	ELSE
		IF NOT IS_CAR_HEALTH_GREATER drive_by_car1 250
			IF ballas_cunts_not_dead = 0
				REMOVE_BLIP	drive_by_car1_blip
				GOSUB drive_by_the_ballers

				IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
				OR NOT IS_CHAR_DEAD	drive_by_bloke[1]
					PRINT_NOW ( K_BALLA ) 6000 1 // Kill the Baller gang member
				ENDIF
				ballas_cunts_not_dead = 1
			ENDIF
		ENDIF
	ENDIF

	IF baller_bloke_dead_in_car = 0
		IF IS_CHAR_DEAD	drive_by_bloke[0]
			IF NOT IS_CHAR_DEAD	drive_by_bloke[1]
				REMOVE_BLIP drive_by_bloke_blip1
				ADD_BLIP_FOR_CHAR drive_by_bloke[1] drive_by_bloke_blip1
				TASK_KILL_CHAR_ON_FOOT drive_by_bloke[1] scplayer
				baller_bloke_dead_in_car = 1
			ENDIF
			GOSUB drive_by_the_ballers
			IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
			OR NOT IS_CHAR_DEAD	drive_by_bloke[1]
				PRINT_NOW ( K_BALLA ) 6000 1 // Kill the Baller gang member
			ENDIF
		ENDIF
		IF IS_CHAR_DEAD	drive_by_bloke[1]
			IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
				REMOVE_BLIP drive_by_bloke_blip2
				ADD_BLIP_FOR_CHAR drive_by_bloke[0] drive_by_bloke_blip2
				TASK_KILL_CHAR_ON_FOOT drive_by_bloke[0] scplayer
				baller_bloke_dead_in_car = 1
			ENDIF
			GOSUB drive_by_the_ballers
			IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
			OR NOT IS_CHAR_DEAD	drive_by_bloke[1]
				PRINT_NOW ( K_BALLA ) 6000 1 // Kill the Baller gang member
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	drive_by_bloke[0]
		REMOVE_BLIP drive_by_bloke_blip1
	ENDIF

	IF IS_CHAR_DEAD	drive_by_bloke[1]
		REMOVE_BLIP drive_by_bloke_blip2
	ENDIF

	IF IS_CHAR_DEAD sweet3_mate1
	OR IS_CHAR_DEAD sweet3_mate2
		PRINT_NOW ( TW2_Y ) 6000 1 // The Flats capped your homies!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ELSE	
		IF NOT IS_CAR_DEAD sweet_car
			IF car_blow_up = 0
			
				IF IS_CHAR_IN_CAR scplayer sweet_car
					IF chicken_shop_blip_removed = 0
						REMOVE_BLIP chicken_shop_blip
						REMOVE_BLIP	drive_by_car1_blip
						IF NOT IS_CAR_DEAD drive_by_car1
							ADD_BLIP_FOR_CAR drive_by_car1 drive_by_car1_blip
						ENDIF
						PRINT_NOW ( SWE3_B ) 3000 1 // Chase down the gang car before he gets to the hub!

						chicken_shop_blip_removed = 1
					ENDIF
					IF played_the_car_dialoge = 0
						IF TIMERB > 2000
							GOSUB load_and_play_audio_sweet3
						ENDIF
					ENDIF
					IF played_gang_help_text = 0
						IF TIMERB > 6000
							PRINT_HELP SWE3_H
							played_gang_help_text = 1
						ENDIF
					ENDIF

					GET_CAR_HEALTH sweet_car CurrentcarHealthValue
					IF played_first_ouch_samples = 0
						IF played_the_car_dialoge = 0
							IF sweet3_index > 11
								COMPARE_CurrentcarHealthValue = CurrentcarHealthValue - 60
								sweet3_index = 0
								sweet3_audio_is_playing = 0
								sweet3_cutscene_flag = 0
								sweet3_chat_switch = sweet3_CHAT8
								played_first_ouch_samples = 1
								GOSUB sweet3_chat_switch
								played_the_car_dialoge = 1
							ENDIF
						ENDIF
					ENDIF

					IF played_first_ouch_samples = 1
						IF played_the_car_dialoge = 0
							IF sweet3_index > 3
								COMPARE_CurrentcarHealthValue = CurrentcarHealthValue - 60
								sweet3_index = 0
								sweet3_audio_is_playing = 0
								sweet3_cutscene_flag = 0
								sweet3_chat_switch = sweet3_CHAT9
								played_first_ouch_samples = 2
								GOSUB sweet3_chat_switch
								played_the_car_dialoge = 1
							ENDIF
						ENDIF
					ENDIF

					IF played_the_car_dialoge = 1
						IF NOT IS_CAR_HEALTH_GREATER sweet_car COMPARE_CurrentcarHealthValue
							played_the_car_dialoge = 0
						ENDIF
					ENDIF

				ELSE
					IF chicken_shop_blip_removed = 1
						REMOVE_BLIP chicken_shop_blip
						REMOVE_BLIP	drive_by_car1_blip
						ADD_BLIP_FOR_CAR sweet_car chicken_shop_blip
						SET_BLIP_AS_FRIENDLY chicken_shop_blip TRUE
						GOSUB get_back_in_the_car_sweet3_2
						PRINT_NOW ( TW2_X ) 3000 1 //~s~Get back in the ~b~car~s~.
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
						STOP_CHAR_FACIAL_TALK scplayer
						chicken_shop_blip_removed = 0
					ENDIF
				ENDIF

			ENDIF

			IF NOT IS_CAR_HEALTH_GREATER sweet_car 250
				LOAD_MISSION_AUDIO 2 SOUND_SWE2_KA
				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0

				ENDWHILE
				CLEAR_MISSION_AUDIO 1
				PLAY_MISSION_AUDIO 2
				PRINT_NOW ( SWE2_KA ) 2000 1 //We’re on fire, everybody for themselves!
				
				IF car_blow_up = 0
					IF NOT IS_CAR_DEAD sweet_car
						IF NOT IS_CHAR_DEAD	big_smoke
							TASK_LEAVE_CAR_IMMEDIATELY big_smoke sweet_car
							TASK_SMART_FLEE_CHAR big_smoke scplayer 100.0 10000
						ENDIF 
						IF NOT IS_CHAR_DEAD	ryder
							TASK_LEAVE_CAR_IMMEDIATELY ryder sweet_car
							TASK_SMART_FLEE_CHAR ryder scplayer 100.0 10000
						ENDIF
						IF NOT IS_CHAR_DEAD sweet
							TASK_LEAVE_CAR_IMMEDIATELY sweet sweet_car
							TASK_SMART_FLEE_CHAR sweet scplayer 100.0 10000
						ENDIF
						car_blow_up = 1
					ENDIF
				ENDIF
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				
					IF IS_CHAR_DEAD	sweet
						PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	ryder
						PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	big_smoke
						PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CAR_DEAD sweet_car
						PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
						GOTO mission_sweet3_failed
					ENDIF

				ENDWHILE

				PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
				GOTO mission_sweet3_failed
					
			ENDIF
		ENDIF
				
	ENDIF

	IF IS_CHAR_DEAD	sweet
		PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CHAR_DEAD	big_smoke
		PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CAR_DEAD drive_by_car1
	ENDIF

ENDWHILE

return_to_the_hood:

IF NOT IS_CHAR_DEAD sweet
	CLEAR_CHAR_TASKS sweet
ENDIF
IF NOT IS_CHAR_DEAD ryder
	CLEAR_CHAR_TASKS ryder
ENDIF

REMOVE_BLIP drive_by_bloke_blip1
REMOVE_BLIP drive_by_bloke_blip2

	IF NOT IS_CAR_DEAD sweet_car
		IF IS_CHAR_IN_CAR scplayer sweet_car
			blob_flag = 1
			REMOVE_BLIP drive_by_car1_blip
			ADD_BLIP_FOR_COORD 2513.33 -1672.12 12.52 drive_by_car1_blip
		ELSE
			blob_flag = 0
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	sweet
		PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
		GOTO mission_sweet3_failed
	ENDIF

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

sweet3_index = 0
sweet3_audio_is_playing = 0
sweet3_cutscene_flag = 0
sweet3_chat_switch = sweet3_CHAT4
GOSUB sweet3_chat_switch
TIMERA = 0
played_the_timer = 0

IF NOT IS_CAR_DEAD sweet_car
	IF IS_CHAR_IN_CAR scplayer sweet_car
		PRINT_NOW ( TW2_W ) 6000 1 // Go back to Sweets gaff.
	ELSE
		PRINT_NOW ( TW2_X ) 6000 1 //~s~Get back in the ~b~car~s~.
	ENDIF
ENDIF

WHILE NOT LOCATE_CAR_3D sweet_car 2513.3 -1671.9 12.52 4.0 4.0 4.0 blob_flag //Drive Sweet and Ryder home
OR NOT IS_CHAR_SITTING_IN_CAR scplayer sweet_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS sweet_car
	WAIT 0
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ELSE
		IF NOT IS_CAR_DEAD sweet_car
			IF car_blow_up = 0
				IF IS_CHAR_IN_CAR scplayer sweet_car
					IF chicken_shop_blip_removed = 0
						REMOVE_BLIP chicken_shop_blip
						REMOVE_BLIP drive_by_car1_blip
						ADD_BLIP_FOR_COORD 2513.33 -1672.12 12.52 drive_by_car1_blip
						PRINT_NOW ( TW2_W ) 6000 1 // Go back to Sweets gaff.
						blob_flag = 1
						TIMERA = 0
						chicken_shop_blip_removed = 1
					ENDIF
					IF played_the_timer = 0
						IF TIMERA > 4000
							IF IS_CHAR_IN_CAR scplayer sweet_car
								GOSUB load_and_play_audio_sweet3
								played_the_timer = 1
							ENDIF
						ENDIF
					ELSE
						GOSUB load_and_play_audio_sweet3
					ENDIF
				ELSE
					IF chicken_shop_blip_removed = 1
						REMOVE_BLIP drive_by_car1_blip
						REMOVE_BLIP chicken_shop_blip
						ADD_BLIP_FOR_CAR sweet_car chicken_shop_blip
						SET_BLIP_AS_FRIENDLY chicken_shop_blip TRUE
						GOSUB get_back_in_the_car_sweet3_2
						PRINT_NOW ( TW2_X ) 6000 1 //~s~Get back in the ~b~car~s~.
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
						STOP_CHAR_FACIAL_TALK scplayer
						blob_flag = 0
						chicken_shop_blip_removed = 0
					ENDIF
				ENDIF
			ENDIF

			IF NOT IS_CAR_HEALTH_GREATER sweet_car 250
				LOAD_MISSION_AUDIO 2 SOUND_SWE2_KB
				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0

				ENDWHILE
				CLEAR_MISSION_AUDIO 1
				PLAY_MISSION_AUDIO 2
				PRINT_NOW ( SWE2_KB ) 2000 1 //She's gonna blow, bail out!

				IF car_blow_up = 0
					IF NOT IS_CAR_DEAD sweet_car
						IF NOT IS_CHAR_DEAD	big_smoke
							TASK_LEAVE_CAR_IMMEDIATELY big_smoke sweet_car
							TASK_SMART_FLEE_CHAR big_smoke scplayer 100.0 10000
						ENDIF 
						IF NOT IS_CHAR_DEAD	ryder
							TASK_LEAVE_CAR_IMMEDIATELY ryder sweet_car
							TASK_SMART_FLEE_CHAR ryder scplayer 100.0 10000
						ENDIF
						IF NOT IS_CHAR_DEAD sweet
							TASK_LEAVE_CAR_IMMEDIATELY sweet sweet_car
							TASK_SMART_FLEE_CHAR sweet scplayer 100.0 10000
						ENDIF
						car_blow_up = 1
					ENDIF
				ENDIF
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				
					IF IS_CHAR_DEAD	sweet
						PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	ryder
						PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CHAR_DEAD	big_smoke
						PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CAR_DEAD sweet_car
						PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
						GOTO mission_sweet3_failed
					ENDIF

				ENDWHILE

				PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
				GOTO mission_sweet3_failed	
			ENDIF
		ENDIF
				
	ENDIF

	IF IS_CHAR_DEAD	sweet
		PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CHAR_DEAD	big_smoke
		PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ENDIF
	
ENDWHILE


SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL player1 OFF

SET_FIXED_CAMERA_POSITION 2502.7639 -1670.7784 18.4320 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2503.6729 -1670.8512 18.0218 JUMP_CUT


CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

sweet3_index = 0
sweet3_audio_is_playing = 0
sweet3_cutscene_flag = 0
sweet3_chat_switch = sweet3_CHAT5
GOSUB sweet3_chat_switch
played_the_timer = 0



WHILE NOT sweet3_index = 1
	WAIT 0

	GOSUB load_and_play_audio_sweet3

ENDWHILE

skip_sweet4_cutscene = 0
SKIP_CUTSCENE_START	 

IF NOT IS_CHAR_DEAD	ryder
	CLEAR_LOOK_AT scplayer
	TASK_LOOK_AT_CHAR scplayer ryder 15000
ENDIF

WHILE NOT sweet3_index = 3
	WAIT 0

	GOSUB load_and_play_audio_sweet3

ENDWHILE

//SET_FIXED_CAMERA_POSITION 2523.2974 -1679.7168 16.9665 0.0 0.0 0.0 
//POINT_CAMERA_AT_POINT 2522.5039 -1679.2631 16.5610 JUMP_CUT

CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE                       
CAMERA_SET_VECTOR_MOVE 2522.3706 -1679.2603 15.9361 2516.4795 -1680.7637 17.4286 17000 TRUE
CAMERA_SET_VECTOR_TRACK 2521.5347 -1678.7838 15.6635 2516.3811 -1679.9175 16.9049 17000 TRUE

WHILE NOT sweet3_index = 8
	WAIT 0

	GOSUB load_and_play_audio_sweet3

ENDWHILE

	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD	sweet
			TASK_LEAVE_CAR sweet sweet_car
			TASK_GO_STRAIGHT_TO_COORD sweet 2519.3884 -1678.6890 13.7070 PEDMOVE_WALK 5000
		ENDIF
	ENDIF
	WAIT 500
	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD	ryder
			TASK_LEAVE_CAR ryder sweet_car
			TASK_GO_STRAIGHT_TO_COORD ryder 2519.3884 -1678.6890 13.7070 PEDMOVE_WALK 5000
		ENDIF
	ENDIF

WAIT 6000

skip_sweet4_cutscene = 1
SKIP_CUTSCENE_END

CLEAR_LOOK_AT scplayer
CLEAR_PRINTS

IF skip_sweet4_cutscene = 0
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	WAIT 500
ENDIF

SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT
CAMERA_RESET_NEW_SCRIPTABLES

DELETE_CHAR ryder
DELETE_CHAR sweet

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

IF skip_sweet4_cutscene = 0
	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
ENDIF

sweet3_index = 0
sweet3_audio_is_playing = 0
sweet3_cutscene_flag = 0
sweet3_chat_switch = sweet3_CHAT6
GOSUB sweet3_chat_switch
played_the_timer = 0

TIMERA = 0

IF IS_CAR_DEAD sweet_car
	PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
	GOTO mission_sweet3_failed
ENDIF

IF NOT IS_CAR_DEAD sweet_car
	IF IS_CHAR_IN_CAR scplayer sweet_car
		blob_flag = 1
		REMOVE_BLIP drive_by_car1_blip
		ADD_BLIP_FOR_COORD 2066.4648 -1695.4436 12.5547 drive_by_car1_blip
	ELSE
		blob_flag = 0
	ENDIF
ENDIF

IF NOT IS_CAR_DEAD sweet_car
	IF IS_CHAR_IN_CAR scplayer sweet_car
		PRINT_NOW ( TW2_Z ) 6000 1 // Go back to Smokes gaff.
	ELSE
		PRINT_NOW ( TW2_X ) 6000 1 //~s~Get back in the ~b~car~s~.
	ENDIF
ENDIF

WHILE NOT LOCATE_CAR_3D sweet_car 2066.4648 -1695.4436 12.5547 4.0 3.5 4.0 blob_flag //Drive Smoke home
OR NOT IS_CHAR_SITTING_IN_CAR scplayer sweet_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS sweet_car
	WAIT 0
	
	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ELSE
		IF NOT IS_CAR_DEAD sweet_car
			IF car_blow_up = 0
				IF IS_CHAR_IN_CAR scplayer sweet_car
					IF chicken_shop_blip_removed = 0
						REMOVE_BLIP chicken_shop_blip
						REMOVE_BLIP drive_by_car1_blip
						ADD_BLIP_FOR_COORD 2066.4648 -1695.4436 12.5547 drive_by_car1_blip
						PRINT_NOW ( TW2_Z ) 6000 1 // Go back to Smokes gaff.
						blob_flag = 1
						TIMERA = 0
						chicken_shop_blip_removed = 1
					ENDIF
					IF played_the_timer = 0
						IF TIMERA > 4000
							IF IS_CHAR_IN_CAR scplayer sweet_car
								GOSUB load_and_play_audio_sweet3
								played_the_timer = 1
							ENDIF
						ENDIF
					ELSE
						GOSUB load_and_play_audio_sweet3
					ENDIF
				ELSE
					IF chicken_shop_blip_removed = 1
						REMOVE_BLIP drive_by_car1_blip
						REMOVE_BLIP chicken_shop_blip
						ADD_BLIP_FOR_CAR sweet_car chicken_shop_blip
						SET_BLIP_AS_FRIENDLY chicken_shop_blip TRUE
						GOSUB get_back_in_the_car_sweet3_1
						PRINT_NOW ( TW2_X ) 6000 1 //~s~Get back in the ~b~car~s~.
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
						STOP_CHAR_FACIAL_TALK scplayer
						blob_flag = 0
						chicken_shop_blip_removed = 0
					ENDIF
				ENDIF	
			ENDIF

			IF NOT IS_CAR_HEALTH_GREATER sweet_car 250
				LOAD_MISSION_AUDIO 2 SOUND_SWE2_KB
				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0
				ENDWHILE
				CLEAR_MISSION_AUDIO 1
				PLAY_MISSION_AUDIO 2
				PRINT_NOW ( SWE2_KB ) 2000 1 //She’s gonna blow, bail out!
				IF car_blow_up = 0
					IF NOT IS_CAR_DEAD sweet_car
						IF NOT IS_CHAR_DEAD	big_smoke
							TASK_LEAVE_CAR_IMMEDIATELY big_smoke sweet_car
							TASK_SMART_FLEE_CHAR big_smoke scplayer 100.0 10000
						ENDIF 
						car_blow_up = 1
					ENDIF
				ENDIF
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
				
					IF IS_CHAR_DEAD	big_smoke
						PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
						GOTO mission_sweet3_failed
					ENDIF

					IF IS_CAR_DEAD sweet_car
						PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
						GOTO mission_sweet3_failed
					ENDIF

				ENDWHILE

				PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
				GOTO mission_sweet3_failed	
			ENDIF
		ENDIF

	ENDIF

	IF IS_CHAR_DEAD	big_smoke
		PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
		GOTO mission_sweet3_failed
	ENDIF

	IF IS_CAR_DEAD sweet_car
		PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
		GOTO mission_sweet3_failed
	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

sweet3_index = 0
sweet3_audio_is_playing = 0
sweet3_cutscene_flag = 0
sweet3_chat_switch = sweet3_CHAT7
GOSUB sweet3_chat_switch
played_the_timer = 0

SWITCH_WIDESCREEN ON
SET_PLAYER_CONTROL player1 OFF

//SET_FIXED_CAMERA_POSITION 2082.4807 -1691.1003 16.0404 0.0 0.0 0.0 
//POINT_CAMERA_AT_POINT 2081.6516 -1691.6210 15.8370 JUMP_CUT

CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE                       
CAMERA_SET_VECTOR_MOVE 2079.1089 -1703.5612 17.6928 2067.1624 -1703.3151 13.4746 17000 TRUE
CAMERA_SET_VECTOR_TRACK 2078.2512 -1703.2059 17.3208 2067.1052 -1702.3304 13.6396 17000 TRUE


WAIT 500

IF NOT IS_CHAR_DEAD	big_smoke
	CLEAR_LOOK_AT scplayer
	TASK_LOOK_AT_CHAR scplayer big_smoke 15000
ENDIF

skip_sweet4_cutscene = 0
SKIP_CUTSCENE_START	 

	WHILE NOT sweet3_index = 1
		WAIT 0

			GOSUB load_and_play_audio_sweet3

	ENDWHILE

	WHILE NOT sweet3_index = 5
		WAIT 0

			GOSUB load_and_play_audio_sweet3

	ENDWHILE

	IF NOT IS_CAR_DEAD sweet_car
		IF NOT IS_CHAR_DEAD	big_smoke
			TASK_LEAVE_CAR big_smoke sweet_car
			TASK_GO_STRAIGHT_TO_COORD big_smoke 2058.4043 -1697.1659 12.5547 PEDMOVE_WALK 8000
		ENDIF
	ENDIF

WAIT 5000

skip_sweet4_cutscene = 1
SKIP_CUTSCENE_END

CLEAR_LOOK_AT scplayer
CLEAR_PRINTS

IF skip_sweet4_cutscene = 0
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	WAIT 500
ENDIF

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

CAMERA_RESET_NEW_SCRIPTABLES
DELETE_CHAR big_smoke

SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

IF skip_sweet4_cutscene = 0
	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
ENDIF

GOTO mission_sweet3_passed

 // **************************************** Mission sweet3 failed **************************

mission_sweet3_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

RETURN

   

// **************************************** mission swee t3 passed ***************************

mission_sweet3_passed:

	REGISTER_MISSION_PASSED ( SWEET_3 )
	PRINT_WITH_NUMBER_BIG ( M_PASSS ) 200 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 5
	ADD_SCORE player1 200
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	flag_sweet_mission_counter ++
	SET_INT_STAT PASSED_SWEET2 1
	PLAYER_MADE_PROGRESS 1
RETURN
		
// ********************************** mission cleanup **************************************

mission_cleanup_sweet3:

	REMOVE_CHAR_ELEGANTLY big_smoke
	REMOVE_CHAR_ELEGANTLY ryder
	REMOVE_CHAR_ELEGANTLY sweet
	REMOVE_BLIP chicken_shop_blip
	REMOVE_BLIP drive_by_car1_blip
	REMOVE_BLIP drive_by_bloke_blip1
	REMOVE_BLIP drive_by_bloke_blip2
	ENABLE_AMBIENT_CRIME TRUE
	IF NOT IS_CAR_DEAD sweet_car
		SET_CAN_BURST_CAR_TYRES sweet_car TRUE
	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM3
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS2
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
	MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
	MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
	//MARK_MODEL_AS_NO_LONGER_NEEDED WMYBELL
	MARK_MODEL_AS_NO_LONGER_NEEDED CIGAR
	MARK_CAR_AS_NO_LONGER_NEEDED drive_by_car1
	MARK_CHAR_AS_NO_LONGER_NEEDED drive_by_bloke[0]
	MARK_CHAR_AS_NO_LONGER_NEEDED drive_by_bloke[1]
	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 2
	UNLOAD_SPECIAL_CHARACTER 3
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	SWITCH_CAR_GENERATOR gen_car7 101
	GET_GAME_TIMER timer_mobile_start
	flag_player_on_mission = 0
	MISSION_HAS_FINISHED

RETURN
 



load_and_play_audio_sweet3:

	IF sweet3_audio_is_playing = 0
	OR sweet3_audio_is_playing = 1
		IF sweet3_index <= sweet3_cell_index_end
			IF TIMERA > 200
				GOSUB play_sweet3_audio
			ENDIF
		ENDIF
	ENDIF

	IF sweet3_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			GOSUB stop_talking_sweet3
			sweet3_audio_is_playing = 0
			sweet3_index ++
			sweet3_cutscene_flag = 0
			CLEAR_PRINTS
			TIMERA = 0	
		ENDIF
	ENDIF

RETURN

play_sweet3_audio:

	IF sweet3_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 sweet3_audio_chat[sweet3_index]
		sweet3_audio_is_playing = 1
	ENDIF
	IF sweet3_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $sweet3_chat[sweet3_index] ) 4000 1 //Let's head over to B-Dup's crib.
			PLAY_MISSION_AUDIO 1
			GOSUB start_talking_sweet3
			sweet3_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN
	

/*

RYDER	[]	Aw, shit! You got ketchup all over the seat!
RYDER	[]	But these was clean pants!
	//CJ, watch the damn road!
	
		  
*/

start_talking_sweet3:

	//PLAYER****************************************************************  
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BL
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_GC
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NH
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OA
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 3000
		RETURN
	ENDIF
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OF
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OJ
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_PB
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 3000
		RETURN
	ENDIF

	//SWEET*****************************************************************
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BM
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_CA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_CC
   	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_HC
   	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_HD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MH
		IF NOT IS_CHAR_DEAD	sweet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet TRUE
			START_CHAR_FACIAL_TALK sweet 3000
			RETURN
		ENDIF   
	ENDIF
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NE
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MC
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_ED	//Well you can suck it up once we’re do
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_EE	//Now keep you eyes on the Ballas car!	
		IF NOT IS_CHAR_DEAD	sweet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet TRUE
			START_CHAR_FACIAL_TALK sweet 3000
			RETURN
		ENDIF   
	ENDIF

	//RYDER******************************************************************
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MF
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MG
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NC
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_EC	//My soda! It’s all over the fucking fl
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder TRUE
			START_CHAR_FACIAL_TALK ryder 3000
			RETURN
		ENDIF
	ENDIF
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_DB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_DD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_EA
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder TRUE
			START_CHAR_FACIAL_TALK ryder 3000
			RETURN
		ENDIF
	ENDIF

	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BF
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BG
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BK
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_GA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_HA
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder TRUE
			START_CHAR_FACIAL_TALK ryder 3000
		ENDIF
	ELSE  
	//SMOKE*********************************************************************
		IF NOT IS_CHAR_DEAD	big_smoke
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke TRUE
			START_CHAR_FACIAL_TALK big_smoke 3000
		ENDIF
	ENDIF


RETURN


stop_talking_sweet3:

	//PLAYER**************************************************************  
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BL
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_GC
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NH
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OA
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
		RETURN
	ENDIF
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OF
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_OJ
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_PB
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
		RETURN
	ENDIF

	//SWEET**************************************************************
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BM
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_CA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_CC
   	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_HC
   	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_HD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MH
		IF NOT IS_CHAR_DEAD	sweet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
			STOP_CHAR_FACIAL_TALK sweet
			RETURN
		ENDIF   
	ENDIF
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NE
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MC
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_ED	//Well you can suck it up once we’re do
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_EE	//Now keep you eyes on the Ballas car!
		IF NOT IS_CHAR_DEAD	sweet
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sweet FALSE
			STOP_CHAR_FACIAL_TALK sweet
			RETURN
		ENDIF   
	ENDIF

	//RYDER**************************************************************
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MF
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_MG
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_NC
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_EC	//My soda! It’s all over the fucking fl
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder FALSE
			STOP_CHAR_FACIAL_TALK ryder
			RETURN
		ENDIF
	ENDIF
	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_DB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_DD
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_EA
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder FALSE
			STOP_CHAR_FACIAL_TALK ryder
			RETURN
		ENDIF
	ENDIF

	IF sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BB
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BF
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BG
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_BK
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_GA
	OR sweet3_audio_chat[sweet3_index] = SOUND_SWE2_HA
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder FALSE
			STOP_CHAR_FACIAL_TALK ryder
		ENDIF
	ELSE  
	//SMOKE**************************************************************
		IF NOT IS_CHAR_DEAD	big_smoke
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH big_smoke FALSE
			STOP_CHAR_FACIAL_TALK big_smoke
		ENDIF
	ENDIF



RETURN


get_back_in_the_car_sweet3_1:
	

	CLEAR_MISSION_AUDIO 2
	IF get_in_counter_sweet3 = 0
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AA //Get in
	ENDIF

	IF get_in_counter_sweet3 = 1
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AB //In the ride!
	ENDIF

	IF get_in_counter_sweet3 = 2
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AC //Get in the car!
	ENDIF

	IF get_in_counter_sweet3 = 3   
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AD //Come on, playa, get in!
	ENDIF

	IF get_in_counter_sweet3 = 4   
		LOAD_MISSION_AUDIO 2 SOUND_SMOX_AE //Come on, wise man, get in the car!
	ENDIF

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0

		IF IS_CHAR_DEAD	big_smoke
			//PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CAR_DEAD sweet_car
			//PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
			//failed_sweet3 = 1
			RETURN
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2 
	  	
	IF get_in_counter_sweet3 = 0
		PRINT_NOW ( SMOX_AA ) 3000 1 
	ENDIF
	IF get_in_counter_sweet3 = 1
		PRINT_NOW ( SMOX_AB ) 3000 1 
	ENDIF
	IF get_in_counter_sweet3 = 2
		PRINT_NOW ( SMOX_AC ) 3000 1 
	ENDIF
	IF get_in_counter_sweet3 = 3
		PRINT_NOW ( SMOX_AD ) 3000 1 
	ENDIF
	IF get_in_counter_sweet3 = 4
		PRINT_NOW ( SMOX_AE ) 3000 1 
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0

		IF IS_CHAR_DEAD	big_smoke
			//PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CAR_DEAD sweet_car
			//PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
			//failed_sweet3 = 1
			RETURN
		ENDIF

	ENDWHILE

	get_in_counter_sweet3 ++

	IF get_in_counter_sweet3 > 4
		get_in_counter_sweet3 = 0
	ENDIF

RETURN


get_back_in_the_car_sweet3_2:

	CLEAR_MISSION_AUDIO 2

	IF get_in_counter_sweet3 = 0			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AF //C'mon CJ, earn your keep!
	ENDIF
	IF get_in_counter_sweet3 = 1
		LOAD_MISSION_AUDIO 2 SOUND_SWE1_BM //Get in, nigga!
	ENDIF
	IF get_in_counter_sweet3 = 2
		LOAD_MISSION_AUDIO 2 SOUND_SWEX_BP //Don't be a buster, CJ!
	ENDIF
	IF get_in_counter_sweet3 = 3
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AC //Get in fool!
	ENDIF
	IF get_in_counter_sweet3 = 4
		LOAD_MISSION_AUDIO 2 SOUND_SWEX_BS //CJ, for once, don't be a punk!
	ENDIF
	IF get_in_counter_sweet3 = 5			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AD //All aboard, CJ.
	ENDIF
	IF get_in_counter_sweet3 = 6			
		LOAD_MISSION_AUDIO 2 SOUND_SWE1_BG // CJ, GET IN!
	ENDIF

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0

		IF IS_CHAR_DEAD	sweet
			//PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CHAR_DEAD	ryder
			//PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CHAR_DEAD	big_smoke
			//PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CAR_DEAD sweet_car
			//PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
			//failed_sweet3 = 1
			RETURN
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2 
	  	
	IF get_in_counter_sweet3 = 0
		PRINT_NOW ( RYDX_AF ) 3000 1 //C'mon CJ, earn your keep!				 
	ENDIF
	IF get_in_counter_sweet3 = 1
		PRINT_NOW ( SWE1_BM ) 3000 1 //Get in, nigga!							 
	ENDIF
	IF get_in_counter_sweet3 = 2
		PRINT_NOW ( SWEX_BP ) 3000 1 //Don't be a buster, CJ!					 
	ENDIF
	IF get_in_counter_sweet3 = 3
		PRINT_NOW ( RYDX_AC ) 3000 1 //Get in fool!								 
	ENDIF
	IF get_in_counter_sweet3 = 4
		PRINT_NOW ( SWEX_BS ) 3000 1 //CJ, for once, don't be a punk!			 
	ENDIF
	IF get_in_counter_sweet3 = 5														
		PRINT_NOW ( RYDX_AD ) 3000 1 //All aboard, CJ.							 
	ENDIF
	IF get_in_counter_sweet3 = 6														
		PRINT_NOW ( SWE1_BG ) 3000 1 // CJ, GET IN!								 
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0

		IF IS_CHAR_DEAD	sweet
			//PRINT_NOW ( SWE3_E ) 2000 1 //Sweet is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CHAR_DEAD	ryder
			//PRINT_NOW ( SWE3_F ) 2000 1 //Ryder is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CHAR_DEAD	big_smoke
			//PRINT_NOW ( SWE3_G ) 2000 1 //Smoke is dead!
			//failed_sweet3 = 1
			RETURN
		ENDIF
		IF IS_CAR_DEAD sweet_car
			//PRINT_NOW ( SWE3_D ) 2000 1 //The car is trashed!
			//failed_sweet3 = 1
			RETURN
		ENDIF

	ENDWHILE

	get_in_counter_sweet3 ++

	IF get_in_counter_sweet3 > 6
		get_in_counter_sweet3 = 0
	ENDIF

RETURN

drive_by_the_ballers:

IF NOT IS_CHAR_DEAD	drive_by_bloke[0]
	REMOVE_BLIP drive_by_bloke_blip1
	ADD_BLIP_FOR_CHAR drive_by_bloke[0] drive_by_bloke_blip1
	TASK_KILL_CHAR_ON_FOOT drive_by_bloke[0] scplayer
	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CAR_DEAD sweet_car
			IF IS_CHAR_IN_CAR ryder sweet_car
				CLEAR_CHAR_TASKS ryder
				TASK_DRIVE_BY ryder drive_by_bloke[0] -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_ALL_DIRN TRUE 70
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF NOT IS_CHAR_DEAD	drive_by_bloke[1] 
	REMOVE_BLIP drive_by_bloke_blip2
	ADD_BLIP_FOR_CHAR drive_by_bloke[1] drive_by_bloke_blip2
	TASK_KILL_CHAR_ON_FOOT drive_by_bloke[1] scplayer	
	IF NOT IS_CHAR_DEAD sweet
		IF NOT IS_CAR_DEAD sweet_car
			IF IS_CHAR_IN_CAR sweet sweet_car
				CLEAR_CHAR_TASKS sweet
				TASK_DRIVE_BY sweet drive_by_bloke[1] -1 0.0 0.0 0.0 5000.0 DRIVEBY_AI_ALL_DIRN FALSE 70
			ENDIF
		ENDIF
	ENDIF
ENDIF

RETURN

}
