MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* SWEET 1 *******************************************
// ************************************** CLEAN ********************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME sweet1b

// Mission start stuff

GOSUB mission_start_sweet1b

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_sweet1b_failed
ENDIF

GOSUB mission_cleanup_sweet1b

MISSION_END

{ 
// Variables for mission
 
LVAR_INT crack_dealer crackhead1 crackhead2 crack_boss B_dups_blip ryders_blip seq_drug_buy1 crackhead1_attractor seq_drug_sell1 killed_all_crack_fucks
LVAR_INT crackhead1_blip crack_boss_blip player_grove_gang ryder_group_spilt_blip drug_buyer1 crackhead5 crackboss_help	flag_player_killed_crackhead1
LVAR_INT crackhead1_dead crack_boss_dead crackhead4 already_got_task_sweet1 already_got_task_sweet1_2 in_crack_locate sweet1b_index	crackhead10	when_crack_bosses_attack
LVAR_INT crackhead6	crackhead7 create_crack_den crackhead8 crackhead9 first_dealer_cut ReturnStatus	sweet1a_crackboss skip_crack_cutscene special_death5
LVAR_INT special_death1 special_death2 special_death3 special_death4 crack_fucks crack_bitches smoker1_attack new_visible_area pickup_baseballbat 
LVAR_INT crack_ciggy1 crack_ciggy2 crack_ciggy3	whore_cower smoker2_attack seq_drug_run	flag_sweet1_chat sweet1b_chat_switch crackhead7_blip crackhead8_blip
LVAR_INT flag_kill_the_player sweet1b_cell_index_end sweet1b_cutscene_flag sweet1b_audio_chat[8] sweet1b_audio_is_playing flag_sweet1b played_wee_sample
LVAR_INT played_up_help	help_for_melee_combat dont_play_first_batch	play_ryder_mission_audio dealers_head_is_missing get_in_counter_swee1b
LVAR_FLOAT Retured_anim_time
VAR_TEXT_LABEL $sweet1b_chat[8]

// **************************************** Mission Start **********************************

sweet1b_chat_switch:

SWITCH sweet1b_chat_switch		   

	CONST_INT SWEET1B_CHAT1 0
	CONST_INT SWEET1B_CHAT2 1
	CONST_INT SWEET1B_CHAT3 2
	CONST_INT SWEET1B_CHAT4 3
	CONST_INT SWEET1B_CHAT5	4
	CONST_INT SWEET1B_CHAT6	5
	CONST_INT SWEET1B_CHAT7	6
	CONST_INT SWEET1B_CHAT8	7

	CASE SWEET1B_CHAT1
		$sweet1b_chat[0] = &SWE1_ZF	//Let’s head over to B-Dup’s crib.				
		$sweet1b_chat[1] = &SWE1_YA	//The ‘hood’sall  messed up, huh.
		$sweet1b_chat[2] = &SWE1_YB	//Try getting any of these base heads to help you out.
		$sweet1b_chat[3] = &SWE1_YC	//No wonder the Families are on the way out.
		$sweet1b_chat[4] = &SWE1_YD	//Apart from Smoke, Sweet and yours truly, 
		$sweet1b_chat[5] = &SWE1_YE	//Grove Street don’t bang no more.
		$sweet1b_chat[6] = &SWE1_YF	//They just get high…
		$sweet1b_chat[7] = &SWE1_YG	//Word.

		sweet1b_audio_chat[0] = SOUND_SWE1_ZF //Let’s head over to B-Dup’s crib.				
		sweet1b_audio_chat[1] = SOUND_SWE1_YA //The ‘hood’s all messed up, huh.
		sweet1b_audio_chat[2] = SOUND_SWE1_YB //Try getting any of these base heads to help you out.
		sweet1b_audio_chat[3] = SOUND_SWE1_YC //No wonder the Families are on the way out.
		sweet1b_audio_chat[4] = SOUND_SWE1_YD //Apart from Smoke, Sweet and yours truly, 
		sweet1b_audio_chat[5] = SOUND_SWE1_YE //Grove Street don’t bang no more.
		sweet1b_audio_chat[6] = SOUND_SWE1_YF //They just get high…
		sweet1b_audio_chat[7] = SOUND_SWE1_YG //Word.

		sweet1b_cell_index_end = 7
	BREAK

	CASE SWEET1B_CHAT2
		$sweet1b_chat[0] = &SWE1_YM	//Hey, I know this cat!
		$sweet1b_chat[1] = &SWE1_YN	//He’s a punk used to run with a Front Yard Ballas OG from Idlewood.
		$sweet1b_chat[2] = &SWE1_YO	//I know his place, it’s just across the tracks there.
		$sweet1b_chat[3] = &SWE1_YP	//Let’s check it out!
		$sweet1b_chat[4] = &SWE1_YQ	//Front Yard turf?
		$sweet1b_chat[5] = &SWE1_YR	//You a buster?
		$sweet1b_chat[6] = &SWE1_YS	//No, I’m down, homie!

		sweet1b_audio_chat[0] = SOUND_SWE1_YM	//Hey, I know this cat!
		sweet1b_audio_chat[1] = SOUND_SWE1_YN	//He’s a punk used to run with a Front Yard Ballas OG from Idlewood.
		sweet1b_audio_chat[2] = SOUND_SWE1_YO	//I know his place, it’s just across the tracks there.
		sweet1b_audio_chat[3] = SOUND_SWE1_YP	//Let’s check it out!
		sweet1b_audio_chat[4] = SOUND_SWE1_YQ	//Front Yard turf?				   
		sweet1b_audio_chat[5] = SOUND_SWE1_YR	//You a buster?					   	
		sweet1b_audio_chat[6] = SOUND_SWE1_YS	//No, I’m down, homie!

		sweet1b_cell_index_end = 6
	BREAK

	CASE SWEET1B_CHAT3
		$sweet1b_chat[0] = &SWE1_YX	//Good afternoon, Ballas poison pushers!
		$sweet1b_chat[1] = &SWE1_YY	//Grove Street OG’s come to bust yo’ faces!

		sweet1b_audio_chat[0] = SOUND_SWE1_YX	//Good afternoon, Ballas poison pushers!
		sweet1b_audio_chat[1] = SOUND_SWE1_YY	//Grove Street OG’s come to bust yo’ faces!

		sweet1b_cell_index_end = 1
	BREAK

	CASE SWEET1B_CHAT4
		$sweet1b_chat[0] = &SWE1_TA	//Well now Ballas know Grove Street Families are on the way back up!
		$sweet1b_chat[1] = &SWE1_TB	//Word, homie. And what’s more you didn’t run out on me!
		$sweet1b_chat[2] = &SWE1_TC	//Times are changing!
		$sweet1b_chat[3] = &SWE1_TD	//They gonna be mad...
		$sweet1b_chat[4] = &SWE1_TE	//We’ll warn the gang later.
		$sweet1b_chat[5] = &SWE1_TF	//For now, let’s just bask in the silence of victory, motherfucker!

		sweet1b_audio_chat[0] = SOUND_SWE1_TA	//Well now Ballas know Grove Street Families are on the way back up!
		sweet1b_audio_chat[1] = SOUND_SWE1_TB	//Word, homie. And what’s more you didn’t run out on me!
		sweet1b_audio_chat[2] = SOUND_SWE1_TC	//Times are changing!
		sweet1b_audio_chat[3] = SOUND_SWE1_TD	//They gonna be mad...
		sweet1b_audio_chat[4] = SOUND_SWE1_TE	//We’ll warn the gang later.
		sweet1b_audio_chat[5] = SOUND_SWE1_TF	//For now, let’s just bask in the silence of victory, motherfucker!

		sweet1b_cell_index_end = 5
	BREAK

	CASE SWEET1B_CHAT5
		$sweet1b_chat[0] = &SWE1_SA	//Hey partner I'm working man, what you need?  What you need, dog?
		$sweet1b_chat[1] = &SWE1_SB	//Dog, I got quality shit right here.

		sweet1b_audio_chat[0] = SOUND_SWE1_SA //Hey partner I'm working man, what you need?  What you need, dog?
		sweet1b_audio_chat[1] = SOUND_SWE1_SB //Dog, I got quality shit right here.
		
		sweet1b_cell_index_end = 1
	BREAK

	CASE SWEET1B_CHAT6
		$sweet1b_chat[0] = &SWE1_YT	//Man, we on a serious mission now.
		$sweet1b_chat[1] = &SWE1_YU	//Man, don’t sweat it, homie – everybody in the hood knows Ballas are pussies.

		sweet1b_audio_chat[0] = SOUND_SWE1_YT	//Man, we on a serious mission now.
		sweet1b_audio_chat[1] = SOUND_SWE1_YU	//Man, don’t sweat it, homie – everybody in the hood knows Ballas are pussies.
		
		sweet1b_cell_index_end = 1
	BREAK

	CASE SWEET1B_CHAT7
		$sweet1b_chat[0] = &SWE1_YV	//Oo-ee! Man, you can smell a crack den a mile away!
		$sweet1b_chat[1] = &SWE1_YW	//Yeah, let’s shoot through and introduce ourselves.

		sweet1b_audio_chat[0] = SOUND_SWE1_YV	//Oo-ee! Man, you can smell a crack den a mile away!
		sweet1b_audio_chat[1] = SOUND_SWE1_YW	//Yeah, let’s shoot through and introduce ourselves.
		
		sweet1b_cell_index_end = 1
	BREAK

	CASE SWEET1B_CHAT8
		$sweet1b_chat[0] = &SWE1_UA	//Now that the base ain’t being pushed up in their faces…
		$sweet1b_chat[1] = &SWE1_UB	//Maybe these fools should be up for some real bangin’!
		$sweet1b_chat[2] = &SWE1_UC	//Yeah, Grove gone get back on its feet now for sure though.
		$sweet1b_chat[3] = &SWE1_UD	//Later Homie

		sweet1b_audio_chat[0] = SOUND_SWE1_UA	//Now that the base ain’t being pushed up in their faces…
		sweet1b_audio_chat[1] = SOUND_SWE1_UB	//Maybe these fools should be up for some real bangin’!
		sweet1b_audio_chat[2] = SOUND_SWE1_UC	//Yeah, Grove gone get back on its feet now for sure though.
		sweet1b_audio_chat[3] =	SOUND_INT2_ND	//Later Homie

		sweet1b_cell_index_end = 3
	BREAK
			   


ENDSWITCH

RETURN


mission_start_sweet1b:

REGISTER_MISSION_GIVEN

already_got_task_sweet1 = 0
ryder_group_spilt_blip = 0
flag_player_on_mission = 1
create_crack_den = 0
crackhead1_dead = 0
in_crack_locate = 0
special_death1 = 0
special_death2 = 0
special_death3 = 0
special_death4 = 0
smoker1_attack = 0
smoker2_attack = 0
crackboss_help = 0
killed_all_crack_fucks = 0
whore_cower = 0
flag_sweet1_chat = 0
flag_sweet1b = 0
first_dealer_cut = 0
skip_crack_cutscene = 0
flag_player_killed_crackhead1 = 0
help_for_melee_combat = 0
played_wee_sample = 0
when_crack_bosses_attack = 0

SWITCH_ROADS_OFF 2272.9219 -1649.5563 14.3311 2266.1013 -1633.2192 14.3505
SWITCH_PED_ROADS_OFF 324.1973 1116.7579 1082.8981 313.1226 1124.1968 1082.8981

WAIT 0

LOAD_MISSION_TEXT SWEET1B

// ****************************************START OF CUTSCENE********************************

// fades the screen in 

SET_AREA_VISIBLE 1

LOAD_CUTSCENE SWEET1B
 
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


REQUEST_MODEL BAT
REQUEST_MODEL GREENWOO
LOAD_SPECIAL_CHARACTER 1 RYDER2
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY crack_fucks

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED BAT
OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_MODEL_LOADED GREENWOO
	WAIT 0

ENDWHILE

ENABLE_AMBIENT_CRIME FALSE

CLEAR_AREA 2516.3916 -1676.5172 13.0041 5.0 TRUE
CLEAR_AREA 2508.7424 -1671.1321 12.3818 5.0 TRUE
CLEAR_AREA 2516.95 -1675.23 13.05 2.0 TRUE
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2516.3916 -1676.5172 13.0041 ryder
SET_CHAR_HEADING ryder 45.7
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1	PEDTYPE_PLAYER1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1 PEDTYPE_MISSION1
GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_BASEBALLBAT 3000
SET_CURRENT_CHAR_WEAPON ryder WEAPONTYPE_BASEBALLBAT
SET_CHAR_DECISION_MAKER ryder crack_fucks
SET_CHAR_ONLY_DAMAGED_BY_PLAYER ryder TRUE
//SET_CHAR_AS_PLAYER_FRIEND ryder

SET_ANIM_GROUP_FOR_CHAR ryder gang1
SET_CHAR_MAX_HEALTH	ryder 500
SET_CHAR_HEALTH ryder 500  
SET_CHAR_NEVER_TARGETTED ryder TRUE
SET_CHAR_SUFFERS_CRITICAL_HITS ryder TRUE

ADD_BLIP_FOR_CHAR ryder ryders_blip
SET_BLIP_AS_FRIENDLY ryders_blip TRUE
REMOVE_BLIP	ryders_blip
       
SET_CHAR_COORDINATES scplayer 2516.95 -1675.23 13.05 
SET_CHAR_HEADING scplayer 56.0

LOAD_SCENE 2509.81 -1670.89 12.39

GET_PLAYER_GROUP Player1 Players_Group	
SET_GROUP_MEMBER Players_Group  ryder
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
LISTEN_TO_PLAYER_GROUP_COMMANDS ryder FALSE	
SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS Player1 TRUE
ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 2165.72 -1673.14 10.0 TRUE 
ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 319.56 1117.64 10.0 TRUE
MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
SET_CHAR_SIGNAL_AFTER_KILL ryder FALSE

//GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_BASEBALLBAT 3000
//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_BASEBALLBAT
//CREATE_PICKUP_WITH_AMMO ModelName PickUpType 3000 X Y Z PickupID

//SWITCH_ENTRY_EXIT xref FALSE
//SWITCH_ENTRY_EXIT crackhouse TRUE


PRINT_NOW ( SW1B_A ) 10000 1 // Go get B Dup to help clean up the crack dealers.

SWITCH_CAR_GENERATOR gen_car7 0
CLEAR_AREA 2508.16 -1666.47 12.3983 6.0 TRUE
CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO &GROVE4L_ 
CREATE_CAR GREENWOO 2508.16 -1666.47 12.3983 sweet_car
CHANGE_CAR_COLOUR sweet_car 59 34
SET_CAR_HEADING sweet_car 16.0
SET_RADIO_CHANNEL RS_MODERN_HIP_HOP
MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO

SET_PLAYER_CONTROL player1 ON

DO_FADE 1200 FADE_IN

//GOTO SKIP_BDUPS
ADD_BLIP_FOR_COORD 2301.07 -1754.40 12.54 B_dups_blip
SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
RESTORE_CAMERA_JUMPCUT

blob_flag = 1
TIMERA = 0
SWITCH_ENTRY_EXIT lacrak FALSE

sweet1b_index = 0
sweet1b_audio_is_playing = 0
sweet1b_cutscene_flag = 0
sweet1b_chat_switch = SWEET1B_CHAT1
GOSUB sweet1b_chat_switch

CLEAR_AREA 2301.07 -1754.40 12.54 20.0 TRUE

WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2301.07 -1754.40 12.54 4.0 4.0 4.0 blob_flag //B_dups
OR NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 30.0 30.0 8.0 FALSE
	WAIT 0

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_sweet1b_passed
	ENDIF

	IF new_visible_area	= 0
		IF NOT IS_CHAR_DEAD	ryder
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 10.0 10.0 8.0 FALSE
				IF IS_CHAR_ON_FOOT scplayer
				AND IS_CHAR_ON_FOOT	ryder
					GOSUB load_and_play_audio_sweet1b
				ENDIF	
				IF NOT IS_CHAR_DEAD	ryder
					IF IS_CHAR_IN_ANY_CAR scplayer
					AND IS_CHAR_IN_ANY_CAR ryder
						GOSUB load_and_play_audio_sweet1b
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF played_up_help = 0
		IF TIMERA > 5000
			PRINT_HELP CALLRYD  
			played_up_help = 1
		ENDIF
	ENDIF

	GOSUB ryders_group_break

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE


SWITCH_WIDESCREEN ON 
SET_PLAYER_CONTROL player1 OFF
CLEAR_PRINTS

SET_FIXED_CAMERA_POSITION 2301.9182 -1746.5054 14.0090 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2301.8096 -1747.4987 14.0471 JUMP_CUT

IF NOT IS_CHAR_DEAD	ryder
	IF IS_CHAR_IN_ANY_CAR ryder
		TASK_LEAVE_ANY_CAR ryder
	ELSE
		TASK_GO_STRAIGHT_TO_COORD ryder 2298.57 -1775.44 12.56 PEDMOVE_WALK 2500	
	ENDIF
ENDIF
IF IS_CHAR_IN_ANY_CAR scplayer
	WAIT 800
	TASK_LEAVE_ANY_CAR scplayer
ELSE
	TASK_GO_STRAIGHT_TO_COORD scplayer 2298.57 -1775.44 12.56 PEDMOVE_WALK 2500	
ENDIF


MARK_CAR_AS_NO_LONGER_NEEDED sweet_car

// ****************************************START OF CUTSCENE********************************

DO_FADE 1700 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE 

SET_AREA_VISIBLE 3

MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
//SWITCH_STREAMING OFF

LOAD_CUTSCENE SWEET1C
 
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

//SKIP_BDUPS:


// ****************************************END OF CUTSCENE**********************************

REQUEST_MODEL BMYDRUG
REQUEST_MODEL FAM3
REQUEST_ANIMATION DEALER
REQUEST_ANIMATION GANGS

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH crack_dealer
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_WEAK crack_bitches

LOAD_MISSION_AUDIO 1 SOUND_SWE1_YH	//Let’s just cruise through the hood and find us somebody selling.			

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED BMYDRUG
OR NOT HAS_MODEL_LOADED FAM3
OR NOT HAS_ANIMATION_LOADED DEALER
OR NOT HAS_ANIMATION_LOADED GANGS
	WAIT 0

ENDWHILE

REMOVE_BLIP	B_dups_blip

CREATE_CHAR PEDTYPE_MISSION2 fam3 2283.6565 -1645.5992 14.1598 drug_buyer1
SET_CHAR_DECISION_MAKER drug_buyer1 crack_fucks

CREATE_CHAR PEDTYPE_MISSION2 bmydrug 2284.9465 -1645.3959 14.1413 crackhead1
SET_CHAR_MAX_HEALTH crackhead1 120
SET_CHAR_HEALTH crackhead1 120
SET_CHAR_HEADING crackhead1 114.0		
ADD_BLIP_FOR_CHAR crackhead1 crackhead1_blip
SET_CHAR_DECISION_MAKER crackhead1 crack_fucks
SET_CHAR_SHOOT_RATE crackhead1 75
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH crackhead1 TRUE

TASK_TURN_CHAR_TO_FACE_CHAR crackhead1 drug_buyer1
TASK_TURN_CHAR_TO_FACE_CHAR drug_buyer1 crackhead1

FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION crackhead1 TRUE
FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drug_buyer1 TRUE

SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

SET_CHAR_COORDINATES scplayer 2300.13 -1763.18 12.63 
SET_CHAR_HEADING scplayer 0.0

IF NOT IS_CHAR_DEAD ryder
	SET_CHAR_COORDINATES ryder 2298.15 -1762.67 12.56  
	SET_CHAR_HEADING ryder 355.37
ENDIF

LOAD_SCENE 2300.13 -1763.18 12.63

OPEN_SEQUENCE_TASK seq_drug_run //DRUG BUYER SEQUENCE
	TASK_GO_STRAIGHT_TO_COORD -1 2281.1160 -1647.5273 14.2174 PEDMOVE_RUN 10000
	TASK_GO_STRAIGHT_TO_COORD -1 2281.1331 -1650.7090 14.1714 PEDMOVE_RUN 10000
	TASK_GO_STRAIGHT_TO_COORD -1 2238.0911 -1661.7275 14.4690 PEDMOVE_RUN 10000
	TASK_WANDER_STANDARD -1
CLOSE_SEQUENCE_TASK seq_drug_run

OPEN_SEQUENCE_TASK seq_drug_sell1 //DRUG DEALER SEQUENCE
	//TASK_PLAY_ANIM -1 DEALER_IDLE GANGS 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 DEALER_IDLE DEALER 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 DEALER_DEAL GANGS 4.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT seq_drug_sell1 1
CLOSE_SEQUENCE_TASK seq_drug_sell1

OPEN_SEQUENCE_TASK seq_drug_buy1 //DRUG BUYER SEQUENCE
	TASK_LOOK_AT_CHAR -1 crackhead1 3000
	TASK_LOOK_ABOUT -1 2000
	//TASK_PLAY_ANIM -1 IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 SHOP_PAY DEALER 4.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 DRUGS_BUY GANGS 4.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT seq_drug_buy1 1
CLOSE_SEQUENCE_TASK seq_drug_buy1

flag_sweet1b = 1

DO_FADE 1000 FADE_IN
								
WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE

PLAY_MISSION_AUDIO 1
PRINT_NOW ( SWE1_YH ) 3000 1 //Let’s just cruise through the hood and find us somebody selling.

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
	WAIT 0

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE

PRINT_NOW ( SW1B_B ) 10000 1 // Go beat up the crack dealers.

// Dealer on the street****************************************************************************************************

WHILE NOT IS_CHAR_DEAD crackhead1
OR NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2284.9465 -1645.3959 14.1413 50.0 50.0 8.0 FALSE
	WAIT 0
   
   	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

	IF played_up_help = 0
		IF TIMERA > 2000
			PRINT_HELP CALLRYD  
			played_up_help = 1
		ENDIF
	ENDIF

	IF crackhead1_dead = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2284.9465 -1645.3959 14.1413 50.0 50.0 8.0 FALSE 
			IF NOT IS_CHAR_DEAD	drug_buyer1
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drug_buyer1 FALSE
			ENDIF
			IF NOT IS_CHAR_DEAD crackhead1
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION crackhead1 FALSE					
				
				IF NOT IS_CHAR_DEAD	drug_buyer1
					PERFORM_SEQUENCE_TASK crackhead1 seq_drug_sell1
					CLEAR_SEQUENCE_TASK	seq_drug_sell1
					PERFORM_SEQUENCE_TASK drug_buyer1 seq_drug_buy1
					CLEAR_SEQUENCE_TASK	seq_drug_buy1
				ENDIF
			ENDIF
			crackhead1_dead = 1	
		ENDIF	
	ELSE
		IF NOT IS_CHAR_DEAD crackhead1
			IF crackhead1_dead = 1
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D crackhead1 scplayer 6.0 6.0 8.0 FALSE 
					CLEAR_CHAR_TASKS crackhead1
					TASK_LOOK_AT_CHAR crackhead1 scplayer 3000
					TASK_TURN_CHAR_TO_FACE_CHAR crackhead1 scplayer

					SET_CHAR_DECISION_MAKER crackhead1 crack_dealer
					TASK_PLAY_ANIM crackhead1 DEALER_IDLE DEALER 4.0 TRUE FALSE FALSE FALSE -1
					IF flag_player_killed_crackhead1 = 0
						IF NOT IS_CHAR_DEAD	drug_buyer1
							FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drug_buyer1 FALSE
							CLEAR_CHAR_TASKS_IMMEDIATELY drug_buyer1
							PERFORM_SEQUENCE_TASK drug_buyer1 seq_drug_run
							CLEAR_SEQUENCE_TASK	seq_drug_run
							SET_CHAR_DECISION_MAKER drug_buyer1 crack_fucks
							flag_player_killed_crackhead1 = 1
						ENDIF
					ENDIF

					sweet1b_index = 0
					sweet1b_audio_is_playing = 0
					sweet1b_cutscene_flag = 0
					sweet1b_chat_switch = SWEET1B_CHAT5
					GOSUB sweet1b_chat_switch

					TIMERA = 0
					crackhead1_dead = 2	
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	
	IF crackhead1_dead = 2
		IF help_for_melee_combat = 0
			IF TIMERA > 2000
				IF NOT IS_CHAR_IN_ANY_CAR scplayer
					PRINT_HELP MEL_HLP  
					help_for_melee_combat = 1
				ENDIF
			ENDIF
		ENDIF
		IF help_for_melee_combat = 1
			IF TIMERA > 8000
				IF NOT IS_CHAR_IN_ANY_CAR scplayer
					PRINT_HELP ( MELHLP2 ) 
					help_for_melee_combat = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF flag_player_killed_crackhead1 = 0
		IF IS_CHAR_DEAD	crackhead1
			IF NOT IS_CHAR_DEAD	drug_buyer1
				FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drug_buyer1 FALSE
				CLEAR_CHAR_TASKS_IMMEDIATELY drug_buyer1
				PERFORM_SEQUENCE_TASK drug_buyer1 seq_drug_run
				CLEAR_SEQUENCE_TASK	seq_drug_run
				SET_CHAR_DECISION_MAKER drug_buyer1 crack_fucks
				flag_player_killed_crackhead1 = 1
			ENDIF
		ELSE
			IF NOT IS_CHAR_DEAD	drug_buyer1
				IF NOT IS_CHAR_HEALTH_GREATER crackhead1 119
				OR NOT IS_CHAR_HEALTH_GREATER drug_buyer1 99
					FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION drug_buyer1 FALSE
					CLEAR_CHAR_TASKS_IMMEDIATELY drug_buyer1
					PERFORM_SEQUENCE_TASK drug_buyer1 seq_drug_run
					CLEAR_SEQUENCE_TASK	seq_drug_run
					SET_CHAR_DECISION_MAKER drug_buyer1 crack_fucks
					IF NOT IS_CHAR_DEAD	crackhead1
						TASK_PLAY_ANIM crackhead1 DEALER_IDLE DEALER 4.0 TRUE FALSE FALSE FALSE -1
					ENDIF
					flag_player_killed_crackhead1 = 1
				ENDIF
			ELSE
				IF NOT IS_CHAR_DEAD	crackhead1
					TASK_PLAY_ANIM crackhead1 XPRESSscratch PED 4.0 FALSE FALSE FALSE FALSE -1
					flag_player_killed_crackhead1 = 1
				ENDIF
			ENDIF

		ENDIF
	ENDIF

	IF flag_kill_the_player = 0
		IF NOT IS_CHAR_DEAD	crackhead1
			IF IS_CHAR_HEALTH_GREATER crackhead1 119
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D crackhead1 scplayer 6.0 6.0 8.0 FALSE
					GOSUB load_and_play_audio_sweet1b
				ENDIF
			ELSE
				GIVE_WEAPON_TO_CHAR crackhead1 WEAPONTYPE_BASEBALLBAT 3000
				SET_CHAR_DROPS_WEAPONS_WHEN_DEAD crackhead1 FALSE
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH crackhead1 FALSE
				CLEAR_MISSION_AUDIO 1
				CLEAR_PRINTS
				CLEAR_CHAR_TASKS crackhead1
				TASK_KILL_CHAR_ON_FOOT crackhead1 scplayer
				flag_kill_the_player = 1	
			ENDIF		
		ELSE
			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			flag_kill_the_player = 1
		ENDIF
	ENDIF

	IF first_dealer_cut = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2284.9465 -1645.3959 14.1413 20.0 15.0 5.0 FALSE
			LOAD_MISSION_AUDIO 1 SOUND_SWE1_YK
			SET_PLAYER_CONTROL player1 OFF
			WAIT 1000

			SWITCH_WIDESCREEN ON 
			SET_FIXED_CAMERA_POSITION 2283.0186 -1649.4722 15.0999 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT 2283.3262 -1648.5249 15.1884 JUMP_CUT

			IF NOT IS_CHAR_DEAD	ryder
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 10.0 10.0 8.0 FALSE
					WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						WAIT 0

						IF IS_CHAR_DEAD	ryder
							PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
							GOTO mission_sweet1b_failed
						ENDIF

					ENDWHILE

					PLAY_MISSION_AUDIO 1
					PRINT_NOW SWE1_YK 5000 1 //Yo, check it. Pusher dealing to Grove Street boys!

					WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
						WAIT 0
						
						IF IS_CHAR_DEAD	ryder
							PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
							GOTO mission_sweet1b_failed
						ENDIF

					ENDWHILE
				ELSE
					WAIT 2000
				ENDIF
			ENDIF
			 
			WAIT 1000
			IF IS_CHAR_IN_ANY_CAR scplayer
				TASK_LEAVE_ANY_CAR scplayer
			ENDIF

			CLEAR_PRINTS
			SET_CAMERA_BEHIND_PLAYER
			SWITCH_WIDESCREEN OFF
			SET_PLAYER_CONTROL player1 ON
			RESTORE_CAMERA_JUMPCUT
			first_dealer_cut = 1
		ENDIF	
	ENDIF

	GOSUB ryders_group_break

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE

MARK_MODEL_AS_NO_LONGER_NEEDED fam3

TIMERA = 0

REMOVE_BLIP	crackhead1_blip

WHILE NOT TIMERA > 2000
OR NOT new_visible_area	= 0
	WAIT 0

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE

DO_FADE 500 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_AREA_VISIBLE 0

DELETE_CHAR drug_buyer1
MARK_MODEL_AS_NO_LONGER_NEEDED BMYDRUG
REMOVE_ANIMATION DEALER
		   
LOAD_SCENE 2283.0 -1644.4 14.2
CLEAR_AREA 2285.17 -1644.74 14.08 4.0 TRUE
CLEAR_AREA_OF_CARS 2288.28 -1648.13 14.00 2274.92 -1641.33 14.31
//CLEAR_AREA_OF_CHARS 2288.28 -1648.13 14.00 2274.92 -1641.33 14.31

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON

IF NOT IS_CHAR_DEAD ryder
	SET_CHAR_AREA_VISIBLE ryder 0
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE ryder TRUE
	REMOVE_CHAR_FROM_GROUP ryder
	CLEAR_CHAR_TASKS_IMMEDIATELY ryder
	CLEAR_LOOK_AT ryder
ENDIF

CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
CLEAR_LOOK_AT scplayer
SET_CHAR_AREA_VISIBLE scplayer 0
//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE

WAIT 1000

//CAMERA_PERSIST_FOV   TRUE
//SET_CINEMA_CAMERA	TRUE
//2281.7380 -1652.0873 14.1714

CLEAR_AREA 2285.17 -1644.74 14.08 5.0 TRUE

IF NOT IS_CHAR_IN_ANY_CAR scplayer
	SET_CHAR_COORDINATES scplayer 2284.4619 -1645.9248 14.1362  
ELSE
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 2284.4619 -1645.9248 14.1362
ENDIF
SET_CHAR_HEADING scplayer 333.0


IF NOT IS_CHAR_DEAD ryder
	IF NOT IS_CHAR_IN_ANY_CAR ryder
		SET_CHAR_COORDINATES ryder 2283.27 -1645.8 14.15  
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD ryder 2283.27 -1645.8 14.15
	ENDIF

	SET_CHAR_HEADING ryder 310.0
ENDIF

dealers_head_is_missing = 0

IF DOES_CHAR_EXIST crackhead1 
	IF IS_CHAR_HEAD_MISSING crackhead1
		dealers_head_is_missing = 1
	ENDIF
ENDIF

SET_FIXED_CAMERA_POSITION 2285.1775 -1644.7482 14.0885 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2284.6350 -1645.2657 14.7501 JUMP_CUT
IF dealers_head_is_missing = 0
	//CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 30000.0 15.0
	SET_PLAYER_DRUNKENNESS Player1 100
ENDIF

sweet1b_index = 0
sweet1b_audio_is_playing = 0
sweet1b_cutscene_flag = 0
sweet1b_chat_switch = SWEET1B_CHAT2
GOSUB sweet1b_chat_switch

WAIT 200

CLEAR_LOOK_AT scplayer
TASK_LOOK_AT_COORD scplayer 2285.1 -1644.7 14.0 21000

IF NOT IS_CHAR_DEAD ryder
	CLEAR_LOOK_AT ryder
	TASK_LOOK_AT_COORD ryder 2285.1 -1644.7 14.0 21000
ENDIF

DELETE_CHAR crackhead1

IF NOT IS_CHAR_DEAD ryder
	IF NOT LOCATE_CHAR_ON_FOOT_3D ryder 2283.27 -1645.8 14.15 0.5 0.5 2.0 FALSE
		IF NOT IS_CHAR_IN_ANY_CAR ryder
			SET_CHAR_COORDINATES ryder 2283.27 -1645.8 14.15  
		ELSE
			WARP_CHAR_FROM_CAR_TO_COORD ryder 2283.27 -1645.8 14.15
		ENDIF

		SET_CHAR_HEADING ryder 310.0
	ENDIF
ENDIF

WAIT 200

DO_FADE 500 FADE_IN

WAIT 700

IF NOT IS_CHAR_DEAD ryder
	IF NOT LOCATE_CHAR_ON_FOOT_3D ryder 2283.27 -1645.8 14.15 0.5 0.5 2.0 FALSE
		IF NOT IS_CHAR_IN_ANY_CAR ryder
			SET_CHAR_COORDINATES ryder 2283.27 -1645.8 14.15  
		ELSE
			WARP_CHAR_FROM_CAR_TO_COORD ryder 2283.27 -1645.8 14.15
		ENDIF

		SET_CHAR_HEADING ryder 310.0
	ENDIF
ENDIF

CLEAR_PRINTS
CLEAR_HELP
//CLEAR_WANTED_LEVEL Player1

IF dealers_head_is_missing = 0
	//CAMERA_RESET_NEW_SCRIPTABLES
	//CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 30000.0 20.0
ENDIF

SKIP_CUTSCENE_START

sweet1b_cutscene_flag = 0
WHILE sweet1b_index = 0	//PRINT_NOW ( SW1B_S ) 3000 1 //Hey, I know this cat!
	WAIT 0

		GOSUB load_and_play_audio_sweet1b

		IF sweet1b_cutscene_flag = 0
			IF sweet1b_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD ryder
					TASK_PLAY_ANIM ryder IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE 6000
					sweet1b_cutscene_flag = 1
				ENDIF	
			ENDIF
		ENDIF

ENDWHILE

sweet1b_cutscene_flag = 0
WHILE sweet1b_index = 1	//He's a punk used to run with a Front Yard Ballas OG from Idlewood.
	WAIT 0

		GOSUB load_and_play_audio_sweet1b

ENDWHILE

sweet1b_cutscene_flag = 0
WHILE sweet1b_index = 2	//I know his place, it's just across the tracks there.
	WAIT 0

		GOSUB load_and_play_audio_sweet1b

		IF sweet1b_cutscene_flag = 0
			IF sweet1b_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD ryder
					CLEAR_LOOK_AT ryder
					TASK_LOOK_AT_CHAR ryder scplayer 9000
					sweet1b_cutscene_flag = 1
				ENDIF
			ENDIF
		ENDIF

ENDWHILE

IF dealers_head_is_missing = 0
	//CAMERA_RESET_NEW_SCRIPTABLES
	//CAMERA_SET_SHAKE_SIMULATION_SIMPLE 5 30000.0 10.0
ENDIF

sweet1b_cutscene_flag = 0
WHILE sweet1b_index = 3	//Let's check it out!
	WAIT 0
		
		GOSUB load_and_play_audio_sweet1b

ENDWHILE

sweet1b_cutscene_flag = 0
WHILE sweet1b_index = 4	//Front Yard turf?
	WAIT 0

		GOSUB load_and_play_audio_sweet1b
		
		IF sweet1b_cutscene_flag = 0	
			IF sweet1b_audio_is_playing = 2
				//TASK_PLAY_ANIM scplayer prtial_gngtlkB GANGS 4.0 FALSE FALSE FALSE FALSE -1
				IF NOT IS_CHAR_DEAD ryder
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer ryder 5000
					sweet1b_cutscene_flag = 1
				ENDIF
			ENDIF
		ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD ryder
	CLEAR_LOOK_AT ryder
	TASK_LOOK_AT_CHAR ryder scplayer 5000
ENDIF

sweet1b_cutscene_flag = 0
WHILE sweet1b_index = 5	//You a buster?
	WAIT 0

		GOSUB load_and_play_audio_sweet1b

		IF sweet1b_cutscene_flag = 0
			IF sweet1b_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD ryder
					TASK_PLAY_ANIM ryder prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
					sweet1b_cutscene_flag = 1
				ENDIF
			ENDIF
		ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD	ryder
	TASK_PLAY_ANIM ryder idle_stance PED 4.0 FALSE FALSE FALSE FALSE -1
ENDIF

CLEAR_LOOK_AT scplayer
TASK_LOOK_AT_COORD scplayer 2285.1 -1644.7 14.0 20000

sweet1b_cutscene_flag = 0
WHILE sweet1b_index = 6	//No, I'm down, homie!
	WAIT 0

		GOSUB load_and_play_audio_sweet1b
		
		IF sweet1b_cutscene_flag = 0
			IF sweet1b_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD	ryder
					CLEAR_LOOK_AT ryder
					TASK_LOOK_AT_COORD ryder 2285.1 -1644.7 14.0 20000
					sweet1b_cutscene_flag = 1
				ENDIF
			ENDIF
		ENDIF

ENDWHILE

CLEAR_CHAR_TASKS scplayer

TASK_PLAY_ANIM scplayer FightA_G PED 4.0 FALSE FALSE FALSE FALSE -1

WHILE Retured_anim_time < 0.351
	WAIT 0

	IF IS_CHAR_PLAYING_ANIM scplayer FightA_G
		GET_CHAR_ANIM_CURRENT_TIME scplayer FightA_G Retured_anim_time
	ENDIF

ENDWHILE

REPORT_MISSION_AUDIO_EVENT_AT_POSITION 2285.1775 -1644.7482 14.0885 SOUND_STAMP_PED

SKIP_CUTSCENE_END

CLEAR_CHAR_TASKS scplayer

CLEAR_PRINTS

SET_MUSIC_DOES_FADE FALSE

DO_FADE 0 FADE_OUT

HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
IF NOT IS_CHAR_DEAD ryder
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE ryder FALSE
ENDIF

WAIT 1000

IF NOT IS_CHAR_DEAD ryder
	CLEAR_LOOK_AT ryder
ENDIF
CLEAR_LOOK_AT scplayer

REMOVE_ANIMATION GANGS

REMOVE_BLIP B_dups_blip
ADD_BLIP_FOR_COORD 2171.7815 -1676.7803 14.0859 B_dups_blip	//CRACK HOUSE

flag_sweet1b = 2

IF NOT IS_CHAR_DEAD ryder
	REMOVE_CHAR_FROM_GROUP ryder
	GET_PLAYER_GROUP Player1 Players_Group	
	SET_GROUP_MEMBER Players_Group  ryder
	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	LISTEN_TO_PLAYER_GROUP_COMMANDS ryder FALSE	
	ryder_group_spilt_blip = 1
ENDIF

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2


CAMERA_RESET_NEW_SCRIPTABLES
SET_PLAYER_DRUNKENNESS Player1 0

SET_CHAR_HEADING scplayer 152.2350
SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

CREATE_PICKUP bat PICKUP_ONCE 2285.7429 -1647.3091 14.0782 pickup_baseballbat

SET_MUSIC_DOES_FADE TRUE

DO_FADE 1000 FADE_IN

CLEAR_PRINTS

IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_BASEBALLBAT
	LOAD_MISSION_AUDIO 2 SOUND_SWE1_ZC	//Hey! Grab hold of that bat over there!			

	WAIT 500

	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0

	   	IF IS_CHAR_DEAD	ryder
			PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
			GOTO mission_sweet1b_failed
		ENDIF

		GOSUB ryders_group_break

		IF IS_CHAR_DEAD	ryder
			PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
			GOTO mission_sweet1b_failed
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2   	
	PRINT_NOW ( SWE1_ZC ) 3000 1 //Hey! Grab hold of that bat over there!

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0   	
	   	IF IS_CHAR_DEAD	ryder
			PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
			GOTO mission_sweet1b_failed
		ENDIF

		//GOSUB ryders_group_break

	ENDWHILE
ENDIF

sweet1b_index = 0
sweet1b_audio_is_playing = 0
sweet1b_cutscene_flag = 0
sweet1b_chat_switch = SWEET1B_CHAT6
GOSUB sweet1b_chat_switch

TIMERB = 0
TIMERA = 0

IF IS_CHAR_DEAD	ryder
	PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
	GOTO mission_sweet1b_failed
ENDIF

PRINT_NOW ( SW1B_1 ) 10000 1 // ~s~Go to ~r~crack dealer~s~

CLEAR_AREA 2171.7815 -1676.7803 14.0859 8.0 TRUE

WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2171.7815 -1676.7803 14.0859 3.5 3.5 4.0 blob_flag 	//Dealers house
OR NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 30.0 30.0 8.0 FALSE
	WAIT 0

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

	IF TIMERB > 5000
		IF LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 10.0 10.0 8.0 FALSE
			IF IS_CHAR_ON_FOOT scplayer
			AND IS_CHAR_ON_FOOT	ryder
				GOSUB load_and_play_audio_sweet1b
			ENDIF	
			IF NOT IS_CHAR_DEAD	ryder
				IF IS_CHAR_IN_ANY_CAR scplayer
				AND IS_CHAR_IN_ANY_CAR ryder
					GOSUB load_and_play_audio_sweet1b
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	GOSUB ryders_group_break

	IF help_for_melee_combat = 0
		IF TIMERA > 2000
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				PRINT_HELP MEL_HLP 
				help_for_melee_combat = 1
			ENDIF
		ENDIF
	ENDIF
	IF help_for_melee_combat = 1
		IF TIMERA > 8000
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				PRINT_HELP ( MELHLP2 ) 
				help_for_melee_combat = 2
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

sweet1b_index = 0
sweet1b_audio_is_playing = 0
sweet1b_cutscene_flag = 0
sweet1b_chat_switch = SWEET1B_CHAT7
GOSUB sweet1b_chat_switch
SWITCH_WIDESCREEN ON //Short cut of sweet and player
SWITCH_ENTRY_EXIT lacrak TRUE
SET_PLAYER_CONTROL player1 OFF
SET_FIXED_CAMERA_POSITION 2177.0715 -1682.0378 15.8487 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 2176.3433 -1681.3542 15.8969 JUMP_CUT
TIMERB = 0


WHILE NOT sweet1b_index = 1
	WAIT 0

	GOSUB load_and_play_audio_sweet1b

ENDWHILE

REMOVE_BLIP B_dups_blip
ADD_BLIP_FOR_COORD 2169.72 -1673.14 13.01 B_dups_blip	//CRACK HOUSE Door
flag_sweet1b = 3

SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
RESTORE_CAMERA_JUMPCUT

Retured_anim_time = 0.0

TIMERB = 0
dont_play_first_batch = 0
// Dealer in crack house****************************************************************************************************

WHILE NOT killed_all_crack_fucks = 1
	WAIT 0

	GET_AREA_VISIBLE new_visible_area

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_sweet1b_passed
	ENDIF

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF
	
	GOSUB load_and_play_audio_sweet1b

	IF new_visible_area	= 5
		ACTIVATE_INTERIOR_PEDS FALSE
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 319.15 1120.89 1082.87 20.0 20.0 10.0 FALSE
			IF create_crack_den = 0
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON 
				CLEAR_AREA 319.15 1120.89 1082.87 100.0 TRUE
				REMOVE_BLIP	ryders_blip

				DO_FADE 0 FADE_OUT

				REQUEST_MODEL BALLAS1
				REQUEST_MODEL HFYPRO
				REQUEST_MODEL BFYPRO
				REQUEST_MODEL BMYDRUG
				REQUEST_MODEL cigar
				REQUEST_ANIMATION CRACK
				REQUEST_ANIMATION BLOWJOBZ
				REQUEST_ANIMATION BASEBALL

				LOAD_ALL_MODELS_NOW

				WHILE NOT HAS_ANIMATION_LOADED CRACK
				OR NOT HAS_ANIMATION_LOADED BLOWJOBZ
				OR NOT HAS_ANIMATION_LOADED BASEBALL
					WAIT 0
					DO_FADE 0 FADE_OUT
				ENDWHILE

				WHILE NOT HAS_MODEL_LOADED BALLAS1
				OR NOT HAS_MODEL_LOADED BFYPRO
				OR NOT HAS_MODEL_LOADED HFYPRO
				OR NOT HAS_MODEL_LOADED cigar
				OR NOT HAS_MODEL_LOADED BMYDRUG
					WAIT 0
					DO_FADE 0 FADE_OUT
				ENDWHILE

				OPEN_SEQUENCE_TASK sweet1a_crackboss
					TASK_GO_STRAIGHT_TO_COORD -1 315.81 1122.30 1082.8 PEDMOVE_RUN 10000
					TASK_PLAY_ANIM -1 Bbalbat_Idle_01 CRACK 4.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK	sweet1a_crackboss

				SWITCH_ENTRY_EXIT lacrak FALSE

				CREATE_CHAR PEDTYPE_CIVFEMALE hfypro 309.77 1123.5 1082.87 crackhead6 //Crack whore
				SET_CHAR_AREA_VISIBLE crackhead6 5
				SET_CHAR_HEADING crackhead6 107.12	
				TASK_PLAY_ANIM_NON_INTERRUPTABLE crackhead6 CRCKIDLE4 CRACK 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER crackhead6 crack_fucks
				
				CREATE_CHAR PEDTYPE_MISSION2 hfypro 317.5892 1124.7773 1082.8672 crackhead2 //wall near kitchen
				SET_CHAR_AREA_VISIBLE crackhead2 5
				SET_CHAR_HEADING crackhead2 180.0	
				TASK_PLAY_ANIM_NON_INTERRUPTABLE crackhead2 CRCKIDLE2 CRACK 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER crackhead2 crack_fucks

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 325.0 1119.9750 1082.8750 crackhead4 //Long Sofa
				SET_CHAR_AREA_VISIBLE crackhead4 5 
				SET_CHAR_HEADING crackhead4 90.0	
				TASK_PLAY_ANIM_NON_INTERRUPTABLE crackhead4 CRCKIDLE1 CRACK 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER crackhead4 crack_fucks
				CREATE_OBJECT cigar 2440.58 -1979.89 1082.2 crack_ciggy1
				//SET_OBJECT_ROTATION crack_ciggy1 180.0 180.0 0.0
				TASK_PICK_UP_OBJECT crackhead4 crack_ciggy1 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1				
					  
				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 320.1898 1123.2921 1082.8750 crackhead5 //Sofa back wall 
				SET_CHAR_AREA_VISIBLE crackhead5 5
				SET_CHAR_HEADING crackhead5 177.9659	
				TASK_PLAY_ANIM_NON_INTERRUPTABLE crackhead5 CRCKIDLE3 CRACK 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER crackhead5 crack_fucks
				CREATE_OBJECT cigar 2440.58 -1979.89 1082.2 crack_ciggy2
				TASK_PICK_UP_OBJECT crackhead5 crack_ciggy2 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1
				//SET_OBJECT_HEADING crack_ciggy2 180.0

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 333.63 1124.51 1082.88 crackhead7 //kitchen smoker
				SET_CHAR_MAX_HEALTH	crackhead7 70
				SET_CHAR_HEALTH crackhead7 70
				SET_CHAR_AREA_VISIBLE crackhead7 5
				SET_CHAR_HEADING crackhead7 267.96	
				SET_CHAR_DECISION_MAKER crackhead7 crack_dealer
				SET_CHAR_RELATIONSHIP crackhead7 ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1
				CREATE_OBJECT cigar 2440.58 -1979.89 1082.2 crack_ciggy3
				TASK_PICK_UP_OBJECT crackhead7 crack_ciggy3 0.0 0.0 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL -1
				//SET_OBJECT_HEADING crack_ciggy3 90.0
				ADD_BLIP_FOR_CHAR crackhead7 crackhead7_blip
				SET_BLIP_ENTRY_EXIT crackhead7_blip 2167.8354 -1672.9231 2.0 

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 325.0 1129.2133 1082.8828 crackhead8 //BJ bloke
				SET_CHAR_AREA_VISIBLE crackhead8 5
				SET_CHAR_HEADING crackhead8 90.0	
				TASK_PLAY_ANIM crackhead8 BJ_Couch_Loop_P BLOWJOBZ 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER crackhead8 crack_dealer
				SET_CHAR_RELATIONSHIP crackhead8 ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1
				ADD_BLIP_FOR_CHAR crackhead8 crackhead8_blip
				SET_BLIP_ENTRY_EXIT crackhead8_blip 2167.8354 -1672.9231 2.0

				CREATE_CHAR PEDTYPE_CIVFEMALE bfypro 324.0 1129.2 1082.8828 crackhead9 //BJ burd
				SET_CHAR_AREA_VISIBLE crackhead9 5
				SET_CHAR_HEADING crackhead9 270.0	
				TASK_PLAY_ANIM crackhead9 BJ_Couch_Loop_W BLOWJOBZ 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER crackhead9 crack_fucks
				SET_CHAR_RELATIONSHIP crackhead9 ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1

				CREATE_CHAR PEDTYPE_MISSION2 BALLAS1 333.4072 1124.4619 1082.8903 crackhead10 //kitchen 
				SET_CHAR_AREA_VISIBLE crackhead10 5
				SET_CHAR_HEADING crackhead10 83.6676	
				TASK_PLAY_ANIM_NON_INTERRUPTABLE crackhead10 CRCKIDLE3 CRACK 4.0 TRUE FALSE FALSE FALSE -1
				SET_CHAR_DECISION_MAKER crackhead10 crack_fucks
				CREATE_OBJECT cigar 2440.58 -1979.89 1082.2 crack_ciggy2
				TASK_PICK_UP_OBJECT crackhead10 crack_ciggy2 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1
				//SET_OBJECT_HEADING crack_ciggy2 180.0

				CREATE_CHAR PEDTYPE_MISSION2 bmydrug 306.03 1121.86 1082.87 crack_boss //BOSS
				SET_CHAR_AREA_VISIBLE crack_boss 5
				SET_CHAR_SHOOT_RATE crack_boss 80
				//SET_CHAR_HAS_USED_ENTRY_EXIT crack_boss 2169.72 -1673.14 20.0
				SET_CHAR_MAX_HEALTH	crack_boss 150
				SET_CHAR_HEALTH crack_boss 150
				SET_CHAR_HEADING crack_boss 263.66	
				GIVE_WEAPON_TO_CHAR crack_boss WEAPONTYPE_BASEBALLBAT 3000
				SET_CHAR_DECISION_MAKER crack_boss crack_dealer
				SET_CHAR_RELATIONSHIP crack_boss ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1
				//FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION crack_boss TRUE
				ADD_BLIP_FOR_CHAR crack_boss crack_boss_blip
				SET_BLIP_ENTRY_EXIT crack_boss_blip 2167.8354 -1672.9231 2.0


				TIMERA = 0
				LOAD_MISSION_AUDIO 1 SOUND_SWE1_YX
				LOAD_MISSION_AUDIO 2 SOUND_SWE1_YY
				WHILE NOT TIMERA > 1000
					WAIT 0
					DO_FADE 0 FADE_OUT
				ENDWHILE

				IF NOT IS_CHAR_DEAD ryder
					REMOVE_CHAR_FROM_GROUP ryder
					CLEAR_CHAR_TASKS_IMMEDIATELY ryder
					CLEAR_LOOK_AT ryder
					SET_CHAR_COORDINATES ryder 320.12 1118.20 1082.89
					SET_CHAR_HEADING ryder 7.0
					SET_CHAR_HAS_USED_ENTRY_EXIT ryder 2169.72 -1673.14 20.0
					CLEAR_LOOK_AT scplayer
					TASK_LOOK_AT_CHAR scplayer ryder 6000
				ENDIF
				SET_FIXED_CAMERA_POSITION 318.2970 1115.4377 1085.0538 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 318.7138 1116.3179 1084.8268 JUMP_CUT

				WAIT 300

				DO_FADE 1000 FADE_IN
				
				IF NOT IS_CHAR_DEAD ryder
					TASK_GO_STRAIGHT_TO_COORD ryder 320.0 1122.3 1082.8 PEDMOVE_WALK 6000
				ENDIF

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						SWITCH_ENTRY_EXIT lacrak TRUE
						GOTO mission_sweet1b_failed
					ENDIF						   
				ENDWHILE					   

				PLAY_MISSION_AUDIO 1
				PRINT_NOW ( SWE1_YX ) 10000 1 //Good afternoon, Ballas poison pushers!

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						SWITCH_ENTRY_EXIT lacrak TRUE
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						GOTO mission_sweet1b_failed
					ENDIF
				ENDWHILE
			
			LOAD_MISSION_AUDIO 1 SOUND_BALLA_1 //Grove Street's going down!

			skip_crack_cutscene = 0
			SKIP_CUTSCENE_START

				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						SWITCH_ENTRY_EXIT lacrak TRUE
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						GOTO mission_sweet1b_failed
					ENDIF
				ENDWHILE

				PLAY_MISSION_AUDIO 2
				PRINT_NOW ( SWE1_YY ) 10000 1 //Grove Street OG’s come to bust yo’ faces!

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						SWITCH_ENTRY_EXIT lacrak TRUE
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						GOTO mission_sweet1b_failed
					ENDIF
				ENDWHILE

				
				LOAD_MISSION_AUDIO 2 SOUND_BALLA_2 //Fuck them Grove Street fools!

				IF NOT IS_CHAR_DEAD ryder
					WHILE NOT LOCATE_CHAR_ANY_MEANS_3D ryder 320.0 1122.3 1082.8 0.9 0.9 2.0 FALSE
						WAIT 0
					 	IF IS_CHAR_DEAD	ryder
							SWITCH_ENTRY_EXIT lacrak TRUE
							PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
							GOTO mission_sweet1b_failed
						ENDIF

					ENDWHILE
				ENDIF

				IF NOT IS_CHAR_DEAD ryder
					IF NOT IS_CHAR_DEAD crackhead5
						//TASK_KILL_CHAR_ON_FOOT ryder crackhead5
						TASK_PLAY_ANIM ryder bat_4 BASEBALL 4.0 FALSE FALSE FALSE FALSE -1
						TIMERA = 0
						WHILE NOT TIMERA > 400
							WAIT 0
						ENDWHILE
						IF NOT IS_CHAR_DEAD	crackhead5
							//REPORT_MISSION_AUDIO_EVENT_AT_CHAR crackhead5 SOUND_BASEBALL_BAT_HIT_PED
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION 320.1898 1123.2921 1082.8750 SOUND_BASEBALL_BAT_HIT_PED

							TASK_DIE_NAMED_ANIM crackhead5 CRCKDETH3 CRACK 4.0 FALSE
							DROP_OBJECT crackhead5 FALSE
						ENDIF
						sweet1b_cutscene_flag = 1 
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD crackhead8
					TASK_GO_STRAIGHT_TO_COORD crackhead8 323.80 1124.35 1082.89 PEDMOVE_WALK 10000 //BJ bloke
					IF NOT IS_CHAR_DEAD crackhead9
						CLEAR_CHAR_TASKS crackhead9
						SET_CHAR_COORDINATES crackhead9 325.3923 1131.8882 1082.8828 
						SET_CHAR_HEADING crackhead9 246.1470
						TASK_PLAY_ANIM crackhead9 cower PED 4.0 TRUE FALSE FALSE FALSE -1
					ENDIF
				ENDIF

				TIMERA = 0
				WHILE NOT TIMERA > 700
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						SWITCH_ENTRY_EXIT lacrak TRUE
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						GOTO mission_sweet1b_failed
					ENDIF
				ENDWHILE

				IF NOT IS_CHAR_DEAD crack_boss		
					//FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION crack_boss FALSE
					SET_CHAR_COORDINATES crack_boss 310.1843 1122.5980 1082.8903  
					SET_CHAR_HEADING crack_boss 270.0
					REMOVE_BLIP B_dups_blip
					//REMOVE_BLIP crack_boss_blip				
					PERFORM_SEQUENCE_TASK crack_boss sweet1a_crackboss
				ENDIF

				SET_FIXED_CAMERA_POSITION 319.2787 1121.3385 1083.1230 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 318.3951 1121.7733 1083.2970 JUMP_CUT
				
				CLEAR_LOOK_AT scplayer
					
				TIMERA = 0
				WHILE NOT TIMERA > 2500
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						SWITCH_ENTRY_EXIT lacrak TRUE
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						GOTO mission_sweet1b_failed
					ENDIF
				ENDWHILE

				IF NOT IS_CHAR_DEAD crackhead7
					SET_CHAR_COORDINATES crackhead7 329.29 1122.13 1082.88  
					SET_CHAR_HEADING crackhead7 90.0
					TASK_GO_STRAIGHT_TO_COORD crackhead7 325.25 1122.50 1082.88 PEDMOVE_RUN 10000 //kitchen smoker
				ENDIF

				SET_FIXED_CAMERA_POSITION 318.6783 1118.7411 1084.6425 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 319.4823 1119.3168 1084.4937 JUMP_CUT

				WHILE NOT HAS_MISSION_AUDIO_LOADED 2
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						SWITCH_ENTRY_EXIT lacrak TRUE
						GOTO mission_sweet1b_failed
					ENDIF						   
				ENDWHILE					   

				PLAY_MISSION_AUDIO 2
				PRINT_NOW ( BALLA_2 ) 10000 1 //Fuck them Grove Street fools!

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
					WAIT 0
					IF IS_CHAR_DEAD	ryder
						SWITCH_ENTRY_EXIT lacrak TRUE
						PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
						GOTO mission_sweet1b_failed
					ENDIF
				ENDWHILE
				
				skip_crack_cutscene = 1
				SKIP_CUTSCENE_END

				LOAD_MISSION_AUDIO 2 SOUND_RYDX_BC //Ninja these motherfuckers!

				IF skip_crack_cutscene = 0
					SET_FADING_COLOUR 0 0 0
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					CLEAR_MISSION_AUDIO 1
					CLEAR_MISSION_AUDIO 2
					LOAD_MISSION_AUDIO 1 SOUND_BALLA_1 //Grove Street's going down!
					LOAD_MISSION_AUDIO 2 SOUND_RYDX_BC //Ninja these motherfuckers!
					IF NOT IS_CHAR_DEAD crack_boss		
						//FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION crack_boss FALSE
						SET_CHAR_COORDINATES crack_boss 310.1843 1122.5980 1082.8903  //CRACK DEALER
						SET_CHAR_HEADING crack_boss 270.0
						REMOVE_BLIP B_dups_blip
						//REMOVE_BLIP crack_boss_blip
						PERFORM_SEQUENCE_TASK crack_boss sweet1a_crackboss
					ENDIF
					IF NOT IS_CHAR_DEAD crackhead8
						SET_CHAR_COORDINATES crackhead8 323.5565 1124.3616 1082.8828 
						SET_CHAR_HEADING crackhead8 132.0730
						TASK_GO_STRAIGHT_TO_COORD crackhead8 323.80 1124.35 1082.89 PEDMOVE_WALK 10000 //BJ bloke
						IF NOT IS_CHAR_DEAD crackhead9
							CLEAR_CHAR_TASKS crackhead9
							SET_CHAR_COORDINATES crackhead9 325.3923 1131.8882 1082.8828 
							SET_CHAR_HEADING crackhead9 246.1470
							TASK_PLAY_ANIM crackhead9 cower PED 4.0 TRUE FALSE FALSE FALSE -1
						ENDIF
					ENDIF
					IF NOT IS_CHAR_DEAD crackhead7
						SET_CHAR_COORDINATES crackhead7 326.96 1122.52 1082.89  
						SET_CHAR_HEADING crackhead7 90.0
						TASK_GO_STRAIGHT_TO_COORD crackhead7 325.25 1122.50 1082.88 PEDMOVE_WALK 10000 //kitchen smoker
					ENDIF
					IF NOT IS_CHAR_DEAD	crackhead5
						TASK_DIE_NAMED_ANIM crackhead5 CRCKDETH3 CRACK 4.0 FALSE
					ENDIF
					//IF NOT IS_CHAR_DEAD	crackhead5
						//DROP_OBJECT crackhead5 TRUE
					//ENDIF
					WAIT 1000
					
					CLEAR_LOOK_AT scplayer
					DO_FADE 500 FADE_IN
				ENDIF
				
				CLEAR_SEQUENCE_TASK sweet1a_crackboss
				REMOVE_ANIMATION BLOWJOBZ
				REMOVE_ANIMATION BASEBALL
				REMOVE_ANIMATION DEALER

				SET_CAMERA_BEHIND_PLAYER
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				RESTORE_CAMERA_JUMPCUT	

				IF NOT IS_CHAR_DEAD crackhead8
					TASK_KILL_CHAR_ON_FOOT crackhead8 scplayer
				ENDIF

				IF NOT IS_CHAR_DEAD crackhead7
					IF NOT IS_CHAR_DEAD ryder
						TASK_KILL_CHAR_ON_FOOT ryder crackhead7
						TASK_KILL_CHAR_ON_FOOT crackhead7 ryder
						SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1	PEDTYPE_PLAYER1
						SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1 PEDTYPE_MISSION1
						DROP_OBJECT	crackhead7 TRUE
					ENDIF
				ENDIF
				
				IF HAS_MISSION_AUDIO_LOADED 1
   					PLAY_MISSION_AUDIO 1
					PRINT_NOW ( BALLA_1 ) 2000 1 //Grove Street's going down!
				ENDIF
				TIMERA = 0

				create_crack_den = 1
			ELSE

				IF TIMERA > 2000
					IF played_wee_sample = 0
						IF HAS_MISSION_AUDIO_LOADED 2
							PLAY_MISSION_AUDIO 2
							PRINT_NOW ( RYDX_BC ) 2000 1 //	Ninja these motherfuckers!
							played_wee_sample = 1
						ENDIF
					ENDIF
				ENDIF

				IF TIMERA > 4000
					IF played_wee_sample = 1
						PRINT_NOW ( SW1B_B ) 6000 1 // ~s~Go beat up the ~r~crack dealer~s~
						played_wee_sample = 2
					ENDIF
				ENDIF

				IF NOT IS_CHAR_DEAD crack_boss
					IF NOT IS_CHAR_IN_ANY_CAR scplayer
						IF help_for_melee_combat = 0
							IF TIMERA > 3000
								IF NOT IS_CHAR_IN_ANY_CAR scplayer
										PRINT_HELP MEL_HLP 
									help_for_melee_combat = 1
								ENDIF
							ENDIF
						ENDIF
						IF help_for_melee_combat = 1
							IF TIMERA > 9000
								IF NOT IS_CHAR_IN_ANY_CAR scplayer
									PRINT_HELP ( MELHLP2 ) 
									help_for_melee_combat = 2
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF create_crack_den = 1
					IF IS_CHAR_DEAD crackhead7
					OR IS_CHAR_DEAD	crack_boss
						IF NOT IS_CHAR_DEAD ryder
							REMOVE_CHAR_FROM_GROUP ryder
							GET_PLAYER_GROUP Player1 Players_Group	
							SET_GROUP_MEMBER Players_Group  ryder
							SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
							LISTEN_TO_PLAYER_GROUP_COMMANDS ryder FALSE	
							create_crack_den = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF //create_crack_den = 0
		ENDIF			
	ENDIF


	IF create_crack_den > 0
		IF IS_CHAR_DEAD crack_boss
			REMOVE_BLIP	crack_boss_blip
		ENDIF

		IF IS_CHAR_DEAD crackhead7
			REMOVE_BLIP crackhead7_blip
		ENDIF

		IF IS_CHAR_DEAD crackhead8
			REMOVE_BLIP crackhead8_blip
		ENDIF

		IF IS_CHAR_DEAD crackhead8
		AND IS_CHAR_DEAD crackhead7
		AND IS_CHAR_DEAD crack_boss
			killed_all_crack_fucks = 1
		ENDIF 
		IF when_crack_bosses_attack = 0
			IF NOT IS_CHAR_DEAD crack_boss
				IF IS_CHAR_DEAD crackhead7
				OR IS_CHAR_DEAD crackhead8
				OR LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer crack_boss 2.0 2.0 2.0 FALSE
					TASK_KILL_CHAR_ON_FOOT crack_boss scplayer
					when_crack_bosses_attack = 1	
				ENDIF
			ENDIF
		ENDIF

	ENDIF

	IF new_visible_area	= 5
		IF NOT IS_CHAR_DEAD	crackhead2
			IF NOT IS_CHAR_HEALTH_GREATER crackhead2 80
				IF special_death1 = 0
					TASK_DIE_NAMED_ANIM crackhead2 CRCKDETH2 CRACK 4.0 FALSE
					DROP_OBJECT crackhead2 TRUE
					special_death1 = 1
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD	crackhead4
			IF NOT IS_CHAR_HEALTH_GREATER crackhead4 80
				IF special_death2 = 0
					TASK_DIE_NAMED_ANIM crackhead4 CRCKDETH1 CRACK 4.0 FALSE
					DROP_OBJECT crackhead4 TRUE
					special_death2 = 1
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD	crackhead6
			IF NOT IS_CHAR_HEALTH_GREATER crackhead6 80
				IF special_death4 = 0
					TASK_DIE_NAMED_ANIM crackhead6 CRCKDETH4 CRACK 4.0 FALSE
					DROP_OBJECT crackhead6 TRUE
					special_death4 = 1
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD	crackhead10
			IF NOT IS_CHAR_HEALTH_GREATER crackhead10 80
				IF special_death5 = 0
					TASK_DIE_NAMED_ANIM crackhead10 CRCKDETH3 CRACK 4.0 FALSE
					DROP_OBJECT crackhead10 TRUE
					special_death5 = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	GOSUB ryders_group_break

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE

WAIT 2000

SWITCH_ENTRY_EXIT lacrak TRUE

ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 2165.72 -1673.14 10.0 TRUE 
ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 319.56 1117.64 10.0 TRUE

MARK_MODEL_AS_NO_LONGER_NEEDED bmydrug
MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
MARK_MODEL_AS_NO_LONGER_NEEDED hfypro
MARK_MODEL_AS_NO_LONGER_NEEDED bfypro
MARK_MODEL_AS_NO_LONGER_NEEDED cigar

ACTIVATE_INTERIOR_PEDS TRUE

IF NOT IS_CHAR_DEAD ryder
	CLEAR_CHAR_TASKS_IMMEDIATELY ryder
	REMOVE_CHAR_FROM_GROUP ryder
	CLEAR_LOOK_AT ryder
	GET_PLAYER_GROUP Player1 Players_Group	
	SET_GROUP_MEMBER Players_Group ryder
	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
	LISTEN_TO_PLAYER_GROUP_COMMANDS ryder FALSE	
	ryder_group_spilt_blip = 1
ENDIF


PRINT_NOW ( SW1B_F ) 10000 1 // Go back to Ryders gaff.
REMOVE_BLIP crack_boss_blip
REMOVE_BLIP B_dups_blip
ADD_BLIP_FOR_COORD 2513.33 -1672.12 12.52 B_dups_blip
//ryder_group_spilt_blip = 0
blob_flag = 1
flag_sweet1b = 4

IF NOT IS_CHAR_DEAD	ryder
ENDIF

play_ryder_mission_audio = 0

sweet1b_index = 0
sweet1b_audio_is_playing = 0
sweet1b_cutscene_flag = 0
sweet1b_chat_switch = SWEET1B_CHAT4
GOSUB sweet1b_chat_switch

// Go back to Ryders gaff.
WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2513.3 -1671.9 12.52 4.0 4.0 4.0 blob_flag 
OR NOT LOCATE_CHAR_ANY_MEANS_3D ryder 2513.3 -1671.9 12.52 4.0 4.0 4.0 FALSE 

	WAIT 0

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

	IF play_ryder_mission_audio = 1
		GOSUB ryders_group_break
	ENDIF

	GET_AREA_VISIBLE new_visible_area

	IF new_visible_area	= 0
		IF play_ryder_mission_audio = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2167.6006 -1672.5919 14.0810 10.0 10.0 4.0 FALSE
				IF NOT IS_CHAR_DEAD	ryder
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 30.0 30.0 8.0 FALSE
						//CLEAR_WANTED_LEVEL player1
						CLEAR_AREA 2164.9614 -1673.7581 14.0803 1.0 TRUE
						SET_CHAR_COORDINATES ryder 2164.9614 -1673.7581 14.0803    
						SET_CHAR_HEADING ryder 280.1246
						SET_CHAR_AREA_VISIBLE ryder 0
						CLEAR_CHAR_TASKS_IMMEDIATELY ryder
						REMOVE_CHAR_FROM_GROUP ryder
						CLEAR_LOOK_AT ryder
						GET_PLAYER_GROUP Player1 Players_Group	
						SET_GROUP_MEMBER Players_Group ryder
						SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
						LISTEN_TO_PLAYER_GROUP_COMMANDS ryder FALSE	
					ENDIF
				ENDIF
				play_ryder_mission_audio = 1
			ENDIF

		ENDIF

		IF play_ryder_mission_audio = 1
			IF NOT IS_CHAR_DEAD	ryder
				IF new_visible_area	= 0
					IF NOT IS_CHAR_DEAD	ryder
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 10.0 10.0 8.0 FALSE
							SWITCH_ENTRY_EXIT lacrak FALSE
							IF IS_CHAR_ON_FOOT scplayer
							AND IS_CHAR_ON_FOOT	ryder
								GOSUB load_and_play_audio_sweet1b
							ENDIF	
							IF NOT IS_CHAR_DEAD	ryder
								IF IS_CHAR_IN_ANY_CAR scplayer
								AND IS_CHAR_IN_ANY_CAR ryder
									GOSUB load_and_play_audio_sweet1b
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF new_visible_area	= 5
		IF NOT IS_CHAR_DEAD	crackhead2
			IF NOT IS_CHAR_HEALTH_GREATER crackhead2 80
				IF special_death1 = 0
					TASK_DIE_NAMED_ANIM crackhead2 CRCKDETH2 CRACK 4.0 FALSE
					DROP_OBJECT crackhead2 TRUE
					special_death1 = 1
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD	crackhead4
			IF NOT IS_CHAR_HEALTH_GREATER crackhead4 80
				IF special_death2 = 0
					TASK_DIE_NAMED_ANIM crackhead4 CRCKDETH1 CRACK 4.0 FALSE
					DROP_OBJECT crackhead4 TRUE
					special_death2 = 1
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD	crackhead6
			IF NOT IS_CHAR_HEALTH_GREATER crackhead6 80
				IF special_death4 = 0
					TASK_DIE_NAMED_ANIM crackhead6 CRCKDETH4 CRACK 4.0 FALSE
					DROP_OBJECT crackhead6 TRUE
					special_death4 = 1
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD	crackhead10
			IF NOT IS_CHAR_HEALTH_GREATER crackhead10 80
				IF special_death5 = 0
					TASK_DIE_NAMED_ANIM crackhead10 CRCKDETH3 CRACK 4.0 FALSE
					DROP_OBJECT crackhead10 TRUE
					special_death5 = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD	ryder
		PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
		GOTO mission_sweet1b_failed
	ENDIF

ENDWHILE

sweet1b_index = 0
sweet1b_audio_is_playing = 0
sweet1b_cutscene_flag = 0
sweet1b_chat_switch = SWEET1B_CHAT8
GOSUB sweet1b_chat_switch

SET_PLAYER_CONTROL player1 OFF
SWITCH_WIDESCREEN ON
SET_NEAR_CLIP 0.2

WAIT 500

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

REQUEST_ANIMATION GANGS

WHILE NOT HAS_ANIMATION_LOADED GANGS
	WAIT 0

ENDWHILE



SET_FIXED_CAMERA_POSITION 2518.4907 -1679.4010 14.4781 0.0 0.0 0.0
POINT_CAMERA_AT_POINT 2518.0862 -1678.4993 14.6304 JUMP_CUT

IF NOT IS_CHAR_IN_ANY_CAR scplayer
	SET_CHAR_COORDINATES scplayer 2517.5244 -1678.4972 13.3912 
ELSE
	WARP_CHAR_FROM_CAR_TO_COORD scplayer 2517.5244 -1678.4972 13.3912
ENDIF

SET_CHAR_HEADING scplayer 328.5351
//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE


IF NOT IS_CHAR_DEAD ryder
	CLEAR_CHAR_TASKS_IMMEDIATELY ryder
	REMOVE_CHAR_FROM_GROUP ryder
	CLEAR_LOOK_AT ryder
	CLEAR_CHAR_TASKS ryder 
	
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE ryder TRUE

	IF NOT IS_CHAR_IN_ANY_CAR ryder
		SET_CHAR_COORDINATES ryder 2518.3142 -1676.9066 13.3726 
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD ryder 2518.3142 -1676.9066 13.3726
		
	ENDIF
	
	TASK_TURN_CHAR_TO_FACE_CHAR ryder scplayer
	TASK_TURN_CHAR_TO_FACE_CHAR scplayer ryder
	SET_CHAR_HEADING scplayer 180.0
ENDIF

WAIT 500

DO_FADE 1000 FADE_IN

/*
TASK_PLAY_ANIM ryder prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE TRUE -1
TASK_PLAY_ANIM ryder prtial_gngtlkB GANGS 4.0 FALSE FALSE FALSE TRUE -1
TASK_PLAY_ANIM ryder prtial_gngtlkA GANGS 4.0 FALSE FALSE FALSE TRUE -1
TASK_PLAY_ANIM ryder prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE TRUE -1
TASK_PLAY_ANIM ryder prtial_gngtlkE GANGS 4.0 FALSE FALSE FALSE TRUE -1
TASK_PLAY_ANIM ryder prtial_gngtlkG GANGS 4.0 FALSE FALSE FALSE TRUE -1
TASK_PLAY_ANIM ryder prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE TRUE -1
*/

WHILE NOT sweet1b_index = 3
	WAIT 0

	GOSUB load_and_play_audio_sweet1b

ENDWHILE

IF NOT IS_CHAR_DEAD ryder
	TASK_GO_STRAIGHT_TO_COORD ryder 2522.9968 -1679.1956 14.4970 PEDMOVE_WALK 4000
ENDIF

//SET_FIXED_CAMERA_POSITION 2514.4375 -1676.8171 14.4492 0.0 0.0 0.0
//POINT_CAMERA_AT_POINT 2515.3772 -1677.1587 14.4617 JUMP_CUT

WHILE NOT sweet1b_index = 4
	WAIT 0

	GOSUB load_and_play_audio_sweet1b

ENDWHILE

WAIT 1000

RESTORE_CAMERA_JUMPCUT
SWITCH_WIDESCREEN OFF

SET_PLAYER_CONTROL player1 ON

DELETE_CHAR ryder


GOTO mission_sweet1b_passed


 // **************************************** Mission sweet1b failed ***********************

mission_sweet1b_failed:


	GET_AREA_VISIBLE new_visible_area
	IF new_visible_area	= 5
		SWITCH_ENTRY_EXIT lacrak TRUE
	ENDIF

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	
	GET_AREA_VISIBLE new_visible_area
	IF new_visible_area	= 5
		IF create_crack_den = 1

			IF NOT IS_CHAR_DEAD	crackhead2
				IF special_death1 = 0
					TASK_DIE_NAMED_ANIM crackhead2 CRCKDETH2 CRACK 4.0 FALSE
					DROP_OBJECT crackhead2 TRUE
					special_death1 = 1
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD	crackhead4
				IF special_death2 = 0
					TASK_DIE_NAMED_ANIM crackhead4 CRCKDETH1 CRACK 4.0 FALSE
					DROP_OBJECT crackhead4 TRUE
					special_death2 = 1
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD	crackhead6
				IF special_death4 = 0
					TASK_DIE_NAMED_ANIM crackhead6 CRCKDETH4 CRACK 4.0 FALSE
					DROP_OBJECT crackhead6 TRUE
					special_death4 = 1
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD	crackhead10
				IF NOT IS_CHAR_HEALTH_GREATER crackhead10 80
					IF special_death5 = 0
						TASK_DIE_NAMED_ANIM crackhead10 CRCKDETH3 CRACK 4.0 FALSE
						DROP_OBJECT crackhead10 TRUE
						special_death5 = 1
					ENDIF
				ENDIF
			ENDIF

		ENDIF
	ENDIF

RETURN

   

// **************************************** mission sweet1b passed ************************

mission_sweet1b_passed:
	flag_sweet_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	REGISTER_MISSION_PASSED ( SWEET1B ) //Used in the stats 
	//PRINT_WITH_NUMBER_BIG ( M_PASS ) 100 5000 1 //"Mission Passed!"
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 3 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 3

	SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 40			
	SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 40
    //ADD_SCORE player1 100
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	PLAYER_MADE_PROGRESS 1
RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_sweet1b:
	
	entry_exit_area = 0
	entry_exit_status = FALSE
	$entry_exit_name = LACRAK
	START_NEW_SCRIPT switch_entry_exit_after_mission
	IF IS_PLAYER_PLAYING player1
		SET_PLAYER_DRUNKENNESS Player1 0
	ENDIF
	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
	SWITCH_ROADS_ON 2272.9219 -1649.5563 14.3311 2266.1013 -1633.2192 14.3505
	SWITCH_PED_ROADS_ON 324.1973 1116.7579 1082.8981 313.1226 1124.1968 1082.8981
	MARK_MODEL_AS_NO_LONGER_NEEDED bmydrug
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
	MARK_MODEL_AS_NO_LONGER_NEEDED BAT
	MARK_MODEL_AS_NO_LONGER_NEEDED fam3
	MARK_MODEL_AS_NO_LONGER_NEEDED hfypro
	MARK_MODEL_AS_NO_LONGER_NEEDED bfypro
	MARK_MODEL_AS_NO_LONGER_NEEDED cigar
	MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
	ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 2165.72 -1673.14 10.0 FALSE 
	ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 319.56 1117.64 10.0 FALSE
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION CRACK
	REMOVE_ANIMATION BLOWJOBZ
	REMOVE_ANIMATION BASEBALL
	REMOVE_ANIMATION DEALER
	REMOVE_PICKUP pickup_baseballbat
	REMOVE_BLIP	crackhead1_blip
	REMOVE_BLIP	crack_boss_blip
	REMOVE_BLIP	B_dups_blip
	REMOVE_BLIP	ryders_blip
	SWITCH_CAR_GENERATOR gen_car7 101
	REMOVE_CHAR_ELEGANTLY ryder
	UNLOAD_SPECIAL_CHARACTER 1
	ENABLE_AMBIENT_CRIME TRUE
	MISSION_HAS_FINISHED

RETURN

ryders_group_break:

	GET_AREA_VISIBLE new_visible_area

	IF new_visible_area	= 0
		IF NOT IS_CHAR_DEAD	ryder
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 30.0 30.0 8.0 FALSE  
				IF ryder_group_spilt_blip = 0
					IF killed_all_crack_fucks = 0
						SWITCH_ENTRY_EXIT lacrak FALSE
					ENDIF
					REMOVE_BLIP crack_boss_blip
					REMOVE_BLIP crackhead1_blip
					REMOVE_BLIP	ryders_blip
					REMOVE_BLIP B_dups_blip
					ADD_BLIP_FOR_CHAR ryder ryders_blip
					SET_BLIP_AS_FRIENDLY ryders_blip TRUE
					REMOVE_CHAR_FROM_GROUP ryder
					CLEAR_CHAR_TASKS ryder
					GOSUB get_back_in_ryders_group
					PRINT_NOW ( SW1B_H ) 6000 1 // You're too far away from Ryder, go back and get him.
					blob_flag = 0
					ryder_group_spilt_blip = 1
				ENDIF
			ELSE
				IF NOT IS_CHAR_DEAD	ryder
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D ryder scplayer 10.0 10.0 8.0 FALSE
						IF ryder_group_spilt_blip = 1
							IF NOT IS_CHAR_DEAD	ryder
								REMOVE_BLIP	ryders_blip
								REMOVE_CHAR_FROM_GROUP ryder
								GET_PLAYER_GROUP Player1 Players_Group	
								SET_GROUP_MEMBER Players_Group ryder
								SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
								LISTEN_TO_PLAYER_GROUP_COMMANDS ryder FALSE	
							ENDIF
							IF flag_sweet1b	= 0
								REMOVE_BLIP B_dups_blip
								ADD_BLIP_FOR_COORD 2301.07 -1754.40 12.54 B_dups_blip
								PRINT_NOW ( SW1B_A ) 7000 1 // ~s~Go to ~y~B Dup crib~s~
							ENDIF
							IF flag_sweet1b	= 1
								IF NOT IS_CHAR_DEAD	crackhead1
									REMOVE_BLIP crackhead1_blip
									ADD_BLIP_FOR_CHAR crackhead1 crackhead1_blip
								ENDIF
								PRINT_NOW ( SW1B_B ) 7000 1 // ~s~Go beat up the ~r~crack dealer~s~
							ENDIF
							IF flag_sweet1b	= 2
								IF new_visible_area	= 0
									REMOVE_BLIP B_dups_blip
									ADD_BLIP_FOR_COORD 2171.7815 -1676.7803 14.0859 B_dups_blip	//CRACK HOUSE
								ENDIF
								PRINT_NOW ( SW1B_1 ) 7000 1 // ~s~Go to ~r~crack dealer~s~
							ENDIF
							IF flag_sweet1b	= 3
								SWITCH_ENTRY_EXIT lacrak TRUE
								IF new_visible_area	= 0
									REMOVE_BLIP B_dups_blip
									ADD_BLIP_FOR_COORD 2169.72 -1673.14 13.01 B_dups_blip	//CRACK HOUSE door
								ENDIF
								PRINT_NOW ( SW1B_1 ) 7000 1 // ~s~Go to ~r~crack dealer~s~
							ENDIF
							IF flag_sweet1b	= 4
								//SWITCH_ENTRY_EXIT lacrak TRUE
								REMOVE_BLIP B_dups_blip
								ADD_BLIP_FOR_COORD 2513.33 -1672.12 12.52 B_dups_blip
								PRINT_NOW ( SW1B_F ) 7000 1 // Go back to Ryders gaff.
							ENDIF
							blob_flag = 1
							ryder_group_spilt_blip = 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

RETURN


load_and_play_audio_sweet1b:

	IF sweet1b_audio_is_playing = 0
	OR sweet1b_audio_is_playing = 1
		IF sweet1b_index <= sweet1b_cell_index_end
			IF TIMERA > 200
				GOSUB play_sweet1b_audio
			ENDIF
		ENDIF
	ENDIF

	IF sweet1b_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			GOSUB stop_talking_sweet1b
			sweet1b_audio_is_playing = 0
			sweet1b_index ++
			sweet1b_cutscene_flag = 0
			CLEAR_PRINTS
			TIMERA = 0	
		ENDIF
	ENDIF

RETURN

play_sweet1b_audio:

	IF sweet1b_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 sweet1b_audio_chat[sweet1b_index]
		sweet1b_audio_is_playing = 1
	ENDIF
	IF sweet1b_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $sweet1b_chat[sweet1b_index] ) 4000 1
			PLAY_MISSION_AUDIO 1
			GOSUB start_talking_sweet1b
			sweet1b_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN



start_talking_sweet1b:

	IF sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_SA
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_SB
		IF NOT IS_CHAR_DEAD	crackhead1
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH crackhead1 TRUE
			START_CHAR_FACIAL_TALK crackhead1 3000
			RETURN
		ENDIF   
	ENDIF
	  
	IF sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YA
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YC
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YF
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YQ
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YS
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 3000
		RETURN
	ENDIF

	IF sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_TA
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_TD
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YT
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YW
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_UC
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 3000
	ELSE
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder TRUE
			START_CHAR_FACIAL_TALK ryder 3000
		ENDIF
	ENDIF


RETURN


stop_talking_sweet1b:

	IF sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_SA
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_SB
		IF NOT IS_CHAR_DEAD	crackhead1
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH crackhead1 FALSE
			STOP_CHAR_FACIAL_TALK crackhead1
			RETURN
		ENDIF   
	ENDIF

	IF sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YA
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YC
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YF
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YQ
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YS
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
		RETURN
	ENDIF

	IF sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_TA
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_TD
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YT
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_YW
	OR sweet1b_audio_chat[sweet1b_index] = SOUND_SWE1_UC
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
	ELSE
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder FALSE
			STOP_CHAR_FACIAL_TALK ryder
		ENDIF
	ENDIF



RETURN


get_back_in_ryders_group:
	
	CLEAR_MISSION_AUDIO 2
	IF get_in_counter_swee1b = 0
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AL	//Hey buster, wait up!
	ENDIF

	IF get_in_counter_swee1b = 1
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AM	//Don't you bust on me!
	ENDIF

	IF get_in_counter_swee1b = 2
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AN	//Wait up, CJ!
	ENDIF

	IF get_in_counter_swee1b = 3			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AO	//Hold up, fool!
	ENDIF

	IF get_in_counter_swee1b = 4			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AP	//Hey, CJ, where you at?
	ENDIF

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0

		IF IS_CHAR_DEAD	ryder
			//PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
			//GOTO mission_sweet1b_failed
			RETURN
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2 
	  	
	IF get_in_counter_swee1b = 0	
		PRINT_NOW (RYDX_AL ) 3000 1 //Hey buster, wait up!
	ENDIF								
	IF get_in_counter_swee1b = 1	
		PRINT_NOW ( RYDX_AM ) 3000 1 //Don't you bust on me!
	ENDIF							   	 
	IF get_in_counter_swee1b = 2	
		PRINT_NOW ( RYDX_AN ) 3000 1 //Wait up, CJ!
	ENDIF						 	   
	IF get_in_counter_swee1b = 3	
		PRINT_NOW ( RYDX_AO	) 3000 1 //Hold up, fool!
	ENDIF							   
	IF get_in_counter_swee1b = 4	
		PRINT_NOW ( RYDX_AP ) 3000 1 //Hey, CJ, where you at? 
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0

		IF IS_CHAR_DEAD	ryder
			//PRINT_NOW (SW1B_G) 10000 1 //~r~Ryder is dead!
			//GOTO mission_sweet1b_failed
			RETURN
		ENDIF

	ENDWHILE

	get_in_counter_swee1b ++

	IF get_in_counter_swee1b > 4
		get_in_counter_swee1b = 0
	ENDIF

	IF IS_CHAR_DEAD	ryder
		
	ENDIF

RETURN



}