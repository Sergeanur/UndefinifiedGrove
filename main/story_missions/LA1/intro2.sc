MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* INTRO 2 *******************************************
// ********************************* MENACE II SOCIETY *************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME intro2

// Mission start stuff

GOSUB mission_start_intro2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_intro2_failed
ENDIF

GOSUB mission_cleanup_intro2

MISSION_END

{
 
// Variables for mission
LVAR_INT rob_shop_blip hood_shop_keeper barber_text_finished ryders_car_blip ryders_car_blip_removed cigar_glow_object intro2_chat_switch skipped_pizza_cutscene
LVAR_INT intro2_sequence_4 shopkeeper_decision food_help player_sequence_1 ryders_ciggy ryders_blip menace_marker intro2_index intro2_cell_index_end
LVAR_INT intro2_sequence_2 shite_haircut smoke_effect shop_keepers_shoots_outside exhale_smoke_effect intro2_audio_is_playing stop_playing_ryder_audio
LVAR_INT new_visible_area print_help_intro2 print_into2_text played_ryder_freaking_out vomit_menace	flag_vomit_playing intro2_cutscene_flag
LVAR_INT ReturnStatus shopkeeper_goes_radge ryders_decision ryders_car_moved player_chats_a_bit	intro2_audio_chat[17] stats_help got_in_ryders_truck_first
LVAR_INT get_in_counter_intro2
LVAR_FLOAT intr2_vomitX intr2_vomitY intr2_vomitZ  return_animation_food_name
VAR_TEXT_LABEL $intro2_chat[17]	 

// **************************************** Mission Start **********************************



intro2_chat_switch:

SWITCH intro2_chat_switch		   

	CONST_INT INTRO2_CHAT1 0
	CONST_INT INTRO2_CHAT2 1
	CONST_INT INTRO2_CHAT3 2
	CONST_INT INTRO2_CHAT4 3
	CONST_INT INTRO2_CHAT5 4
		
	CASE INTRO2_CHAT1

		$intro2_chat[0] = &INT2_CA //Hey, Old Reece still run the barber shop?
		$intro2_chat[1] = &INT2_CB //Like a raggedy-assed motherfucker!
		$intro2_chat[2] = &INT2_CC //He popped his membrane years ago - 
		$intro2_chat[3] = &INT2_CD //no way I’d let THAT old fool near MY head!
		$intro2_chat[4] = &INT2_CE //I think I’ll get myself a cut.
		$intro2_chat[5] = &INT2_CF //Whatever. You got five minutes.
		$intro2_chat[6] = &INT2_CG //I’m wired like a kung fu master!	
		$intro2_chat[7] = &INT2_BA //So when you running off again?
		$intro2_chat[8] = &INT2_BB //I’m not, I’m  thinkin’ of staying.
		$intro2_chat[9] = &INT2_BC //Why?
		$intro2_chat[10] = &INT2_BD	//My homies are here.
		$intro2_chat[11] = &INT2_BE	//We always were, fool!
		$intro2_chat[12] = &INT2_BF	//Yeah, but, now I’m back, I know what I’ve been missing.
		$intro2_chat[13] = &INT2_BG	//Don’t expect me to kiss you or nothing. 
		$intro2_chat[14] = &INT2_BH	//You still a buster to me.
		$intro2_chat[15] = &INT2_BJ	//Wow, thanks, homie.
		$intro2_chat[16] = &INT2_BK	//Don’t mention it.
	
		intro2_audio_chat[0] = SOUND_INT2_CA //Hey, Old Reece still run the barber shop?	
		intro2_audio_chat[1] = SOUND_INT2_CB //Like a raggedy-assed motherfucker!
		intro2_audio_chat[2] = SOUND_INT2_CC //He popped his membrane years ago - 
		intro2_audio_chat[3] = SOUND_INT2_CD //no way I’d let THAT old fool near MY head!
		intro2_audio_chat[4] = SOUND_INT2_CE //I think I’ll get myself a cut.
		intro2_audio_chat[5] = SOUND_INT2_CF //Whatever. You got five minutes.	
		intro2_audio_chat[6] = SOUND_INT2_CG //I’m wired like a kung fu master!		
		intro2_audio_chat[7] = SOUND_INT2_BA //So when you running off again?
		intro2_audio_chat[8] = SOUND_INT2_BB //I’m not, I’m  thinkin’ of staying.
		intro2_audio_chat[9] = SOUND_INT2_BC //Why?
		intro2_audio_chat[10] = SOUND_INT2_BD //My homies are here.
		intro2_audio_chat[11] = SOUND_INT2_BE //We always were, fool!
		intro2_audio_chat[12] = SOUND_INT2_BF //Yeah, but, now I’m back, I know what I’ve been missing.
		intro2_audio_chat[13] = SOUND_INT2_BG //Don’t expect me to kiss you or nothing. 
		intro2_audio_chat[14] = SOUND_INT2_BH //You still a buster to me.
		intro2_audio_chat[15] = SOUND_INT2_BJ //Wow, thanks, homie.
		intro2_audio_chat[16] = SOUND_INT2_BK //Don’t mention it.
		

		intro2_cell_index_end = 16
	BREAK


	CASE INTRO2_CHAT2

		$intro2_chat[0] = &INT2_FA //Shit, looks ridiculous.
		$intro2_chat[1] = &INT2_FB //No respect for the neighborhood, all clean and shit.
		$intro2_chat[2] = &INT2_FC //You’re looking too skinny, CJ.
		$intro2_chat[3] = &INT2_FD //Go in and get some food – I’m going to finish this.

		intro2_audio_chat[0] = SOUND_INT2_FA //Shit, looks ridiculous.
		intro2_audio_chat[1] = SOUND_INT2_FB //No respect for the neighborhood, all clean and shit.
		intro2_audio_chat[2] = SOUND_INT2_FC //You’re looking too skinny, CJ.
		intro2_audio_chat[3] = SOUND_INT2_FD //Go in and get some food – I’m going to finish this.

		intro2_cell_index_end = 3
	BREAK
			
	CASE INTRO2_CHAT3
		
		$intro2_chat[0] = &INT2_GA //Give us the money! This is a raid!
		$intro2_chat[1] = &INT2_GB //Ryder! Not this again!
		$intro2_chat[2] = &INT2_GC //It ain’t me, fool!
		$intro2_chat[3] = &INT2_GD //No one else that small! I feel sorry for your dad!
		$intro2_chat[4] = &INT2_GE //Shit, man, you’re rumbled! Let’s get out of here!
		$intro2_chat[5] = &INT2_GF //Same old CJ, Buster! Buster!	
		$intro2_chat[6] = &INT2_HB //Motherfucker! RUN!
	
		intro2_audio_chat[0] = SOUND_INT2_GA //Give us the money! This is a raid!
		intro2_audio_chat[1] = SOUND_INT2_GB //Ryder! Not this again!
		intro2_audio_chat[2] = SOUND_INT2_GC //It ain’t me, fool!
		intro2_audio_chat[3] = SOUND_INT2_GD //No one else that small! I feel sorry for your dad!
		intro2_audio_chat[4] = SOUND_INT2_GE //Shit, man, you’re rumbled! Let’s get out of here!
		intro2_audio_chat[5] = SOUND_INT2_GF //Same old CJ, Buster! Buster!	
		intro2_audio_chat[6] = SOUND_INT2_HB //Motherfucker! RUN!

		intro2_cell_index_end = 7
	BREAK

	CASE INTRO2_CHAT4
	
		$intro2_chat[0] = &INT2_NB //You better drop by and see Sweet.
		$intro2_chat[1] = &INT2_NC //He’s been rapping on about that grafitti.
		$intro2_chat[2] = &INT2_ND //Later, dude.

		intro2_audio_chat[0] = SOUND_INT2_NB //You better drop by and see Sweet.
		intro2_audio_chat[1] = SOUND_INT2_NC //He’s been rapping on about that grafitti.
		intro2_audio_chat[2] = SOUND_INT2_ND //Later, dude.
	
		intro2_cell_index_end = 2
	BREAK	
	
	CASE INTRO2_CHAT5
	
		$intro2_chat[0] = &INT2_JA //What you waiting for?
		$intro2_chat[1] = &INT2_JB //Take us back to the Grove, motherfucker!

		intro2_audio_chat[0] = SOUND_INT2_JA //What you waiting for?
		intro2_audio_chat[1] = SOUND_INT2_JB //Take us back to the Grove, motherfucker!
	
		intro2_cell_index_end = 1
	BREAK
			
	//On the street – Player must get a car variations}		
	//INT2_IA	//CJ, we need some wheels!
	//INT2_IB	//We need a ride, super fast!
			
	//Player gets shot variations– mission failed}		
	//INT2_KA	//CJ! Damned lead magnet!
	//INT2_KB	//What you take a bullet for?
			
	//Player dies (other) variations – mission failed}		
	//INT2_LA	//CJ, quit bluffing and get up!
	//INT2_LB	//Typical CJ!
	//INT2_LC	//You ain’t long for these streets, CJ.
			
	//Ryder gets shot – mission failed }		
	//INT2_MA	//Oh shit, RYDER!
	//INT2_MB	//This is NOT dope!
			
	//Get back to Grove – mission passed }		
	//INT2_NA	//We did good! Here’s your cut.

ENDSWITCH

RETURN



mission_start_intro2:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

barber_text_finished = 0
ryders_car_blip_removed = 0
flag_changed_hair_intro2 = 0
print_help_intro2 = 0
print_into2_text = 0
flag_menace_buyfood = 0
food_help = 0
shopkeeper_goes_radge = 0
cj_vomits_for_menace = 0
ryders_car_moved = 0
played_ryder_freaking_out = 0
player_chats_a_bit = 0
menace_marker = 0
flag_vomit_playing = 0
stop_playing_ryder_audio = 0
stats_help = 0
got_in_ryders_truck_first = 0
get_in_counter_intro2 = 0
//intro2_failed_flag = 0

intr2_vomitX = 0.05
intr2_vomitY = 0.12 //1.000
intr2_vomitZ = 0.0

	SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 5 
	SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 5
	SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 5
	SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 5
	SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 5
	SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 5

WAIT 0

LOAD_MISSION_TEXT INTRO2

// ****************************************START OF CUTSCENE********************************

// fools compiler
GOTO fool_compiler_intro2
 
	CREATE_CHAR PEDTYPE_MISSION1 shopkeeper_model_shops junkfudX junkfudY junkfudZ shop_keep_junkfud
	
fool_compiler_intro2:


SET_AREA_VISIBLE 2

LOAD_CUTSCENE INTRO2A

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

SET_PLAYER_CONTROL player1 OFF


SET_AREA_VISIBLE 0

// ****************************************END OF CUTSCENE**********************************

REMOVE_BLIP spray_shop1


LOAD_SPECIAL_CHARACTER 1 ryder2
REQUEST_MODEL PICADOR // SADLER
REQUEST_MODEL cigar
REQUEST_MODEL cigar_glow

LOAD_ALL_MODELS_NOW


WHILE NOT HAS_MODEL_LOADED PICADOR
OR NOT HAS_MODEL_LOADED cigar
OR NOT HAS_MODEL_LOADED cigar_glow
OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
	WAIT 0

ENDWHILE

SWITCH_ENTRY_EXIT barbers FALSE 
SWITCH_ENTRY_EXIT barber2 FALSE 
SWITCH_ENTRY_EXIT barber3 FALSE 
SWITCH_ENTRY_EXIT FDpiza FALSE
SWITCH_ENTRY_EXIT fdchick FALSE
SWITCH_ENTRY_EXIT fdburg FALSE

CLEAR_CHAR_TASKS scplayer
CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

SWITCH_CAR_GENERATOR gen_car6 0
CLEAR_AREA ryder_carX ryder_carY ryder_carZ 6.0 TRUE


CUSTOM_PLATE_FOR_NEXT_CAR PICADOR &_SHERM__ 
CREATE_CAR PICADOR ryder_carX ryder_carY ryder_carZ ryder_car
//SET_CAR_PROOFS ryder_car TRUE TRUE TRUE FALSE FALSE
SET_CAN_BURST_CAR_TYRES ryder_car FALSE
CHANGE_CAR_COLOUR ryder_car 84 84
SET_CAR_HEADING ryder_car 0.0
SET_CAR_CAN_BE_VISIBLY_DAMAGED ryder_car FALSE
SET_RADIO_CHANNEL RS_MODERN_HIP_HOP

CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2475.29 -1687.85 12.51 ryder
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY ryders_decision
SET_CHAR_DECISION_MAKER ryder ryders_decision
SET_ANIM_GROUP_FOR_CHAR ryder gang1 
SET_CHAR_HEADING scplayer 176.0
SET_CHAR_NEVER_TARGETTED ryder TRUE
SET_CHAR_HEALTH ryder 500
SET_CHAR_MAX_HEALTH	ryder 500
SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
TASK_ENTER_CAR_AS_PASSENGER ryder ryder_car 20000 0
SET_CHAR_CANT_BE_DRAGGED_OUT ryder TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED ryder TRUE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR ryder FALSE


ADD_BLIP_FOR_CAR ryder_car ryders_car_blip
SET_BLIP_AS_FRIENDLY ryders_car_blip TRUE

ADD_BLIP_FOR_COORD 2074.4 -1800.8 12.5 rob_shop_blip
REMOVE_BLIP rob_shop_blip
 
LOAD_MISSION_AUDIO 1 SOUND_INT2_AA

//WAIT 100

CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
SET_CHAR_COORDINATES scplayer 2464.03 -1687.61 12.51   
SET_CHAR_HEADING scplayer 252.27
SET_AREA_VISIBLE 0
LOAD_SCENE 2464.03 -1687.61 12.51

SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

DO_FADE 1000 FADE_IN


IF IS_CAR_DEAD ryder_car
	GOTO mission_intro2_failed
ENDIF

IF IS_CHAR_DEAD ryder
	GOTO mission_intro2_failed
ENDIF


WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0

	IF IS_CHAR_DEAD ryder
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CAR_DEAD ryder_car
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

ENDWHILE

PLAY_MISSION_AUDIO 1
PRINT_NOW ( INT2_AA ) 10000 1 // Show me how they drive on the East Coast, homie!

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
	WAIT 0
	
	IF IS_CHAR_DEAD ryder
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CAR_DEAD ryder_car
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

ENDWHILE


blob_flag = 0

intro2_index = 0
intro2_audio_is_playing = 0
intro2_cutscene_flag = 0
intro2_chat_switch = INTRO2_CHAT1
GOSUB intro2_chat_switch

CLEAR_THIS_PRINT INT2_1

    PRINT_NOW ( INT2_6 ) 6000 1  // ~s~Get in ~b~Ryder's car~s~!
	
	IF IS_CHAR_DEAD ryder
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CAR_DEAD ryder_car
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

flag_player_on_menace_mission = 1

WHILE NOT LOCATE_CAR_3D ryder_car 2077.84 -1792.59 12.38 4.0 4.0 4.0 blob_flag
OR NOT IS_CHAR_SITTING_IN_CAR scplayer ryder_car
OR NOT IS_CHAR_IN_CAR ryder ryder_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS ryder_car

	WAIT 0
	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_intro2_passed
	ENDIF
		
	IF menace_marker = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2077.84 -1792.59 12.38 15.0 15.0 4.0 FALSE
			PRINT_HELP ( HELP44B )  // Drive your vehicle onto the red marker.
			menace_marker = 1
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD ryder
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CAR_DEAD ryder_car
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ELSE
		IF IS_CHAR_IN_CAR scplayer ryder_car
			IF ryders_car_blip_removed = 0
				REMOVE_BLIP ryders_car_blip
				REMOVE_BLIP rob_shop_blip
				ADD_BLIP_FOR_COORD 2077.84 -1792.59 12.56 rob_shop_blip
				blob_flag = 1
				PRINT_NOW ( HOOD2_A ) 6000 1 // Go to the barbers.
				TIMERB = 0
				ryders_car_blip_removed = 1
			ENDIF			
		ELSE
			IF ryders_car_blip_removed = 1 
				REMOVE_BLIP rob_shop_blip
				REMOVE_BLIP ryders_car_blip
				ADD_BLIP_FOR_CAR ryder_car ryders_car_blip
				SET_BLIP_AS_FRIENDLY ryders_car_blip TRUE
				blob_flag = 0
				GOSUB get_back_in_the_car_intro2
				PRINT_NOW ( INT2_6 ) 6000 1  // ~s~Get in ~b~Ryder's car~s~!
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
				STOP_CHAR_FACIAL_TALK scplayer
				ryders_car_blip_removed = 0
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CAR_DEAD ryder_car
			IF IS_CHAR_IN_CAR scplayer ryder_car
				IF TIMERB > 3000
					GOSUB load_and_play_audio_intro2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF IS_CHAR_DEAD ryder
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CAR_DEAD ryder_car
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF


ENDWHILE

// BARBERS CUTSCENE START**********************************************************************************************************************
SWITCH_WIDESCREEN ON //Barbers Cut Scene
SET_PLAYER_CONTROL player1 OFF
CLEAR_HELP

SWITCH_ENTRY_EXIT barbers TRUE 

SET_FIXED_CAMERA_POSITION 2086.4243 -1783.5780 16.9391 0.0 0.0 0.0 // look at barbers cut
POINT_CAMERA_AT_POINT 2085.6025 -1784.1426 16.8617 JUMP_CUT

TIMERB = 0

WHILE TIMERB < 3000
	WAIT 0

	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CAR_DEAD ryder_car
			IF IS_CHAR_IN_CAR scplayer ryder_car
				GOSUB load_and_play_audio_intro2
			ENDIF
		ENDIF
	ENDIF

ENDWHILE

SKIP_CUTSCENE_START

SET_FIXED_CAMERA_POSITION 2075.4177 -1794.7478 14.3637 0.0 0.0 0.0 // look at barbers cut
POINT_CAMERA_AT_POINT 2074.4404 -1794.5638 14.2591 JUMP_CUT

PRINT_HELP ( INT2_8 )// ~s~Go into the Barbers and get a new haircut.

//SWITCH_WIDESCREEN OFF
SET_PLAYER_CONTROL player1 ON

TIMERB = 0

WHILE TIMERB < 3000
	WAIT 0

	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CAR_DEAD ryder_car
			IF IS_CHAR_IN_CAR scplayer ryder_car
				GOSUB load_and_play_audio_intro2
			ENDIF
		ENDIF
	ENDIF

ENDWHILE

SKIP_CUTSCENE_END

SWITCH_WIDESCREEN OFF 
SET_PLAYER_CONTROL player1 ON
SET_CAMERA_BEHIND_PLAYER 							
RESTORE_CAMERA_JUMPCUT


// BARBERS CUTSCENE END ***********************************************************************************************************************

REMOVE_BLIP rob_shop_blip
ADD_BLIP_FOR_COORD 413.6397 -19.9389 1000.8120 rob_shop_blip //BARBERS SHOP
SET_BLIP_ENTRY_EXIT	rob_shop_blip 2073.0417 -1794.5634 20.0

flag_changed_hair_intro2 = 0

WHILE flag_changed_hair_intro2 = 0
OR NOT LOCATE_CHAR_ON_FOOT_3D scplayer 2072.11 -1793.80 12.56 10.0 10.0 3.0 FALSE
	WAIT 0

	IF IS_CAR_DEAD ryder_car
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CHAR_DEAD ryder
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF
	
	IF stop_playing_ryder_audio = 0
		IF NOT IS_CHAR_DEAD ryder
			IF NOT IS_CAR_DEAD ryder_car
				IF IS_CHAR_IN_CAR scplayer ryder_car
					GOSUB load_and_play_audio_intro2
				ELSE
					stop_playing_ryder_audio = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	GET_AREA_VISIBLE new_visible_area
	IF new_visible_area	= 2
		IF print_help_intro2 = 0
			//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2103.0 -1807.0 10.0
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 411.68 -21.77 1000.79 10.0 10.0 3.0 FALSE
				//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2169.72 -1673.14 20.0
				CLEAR_PRINTS
				PRINT_NOW ( INT2_9 ) 5000 1 // Walk into the corona to get your haircut.
				//REMOVE_BLIP rob_shop_blip
				REQUEST_ANIMATION SHOP
				print_help_intro2 = 1
			ENDIF
		ENDIF
	ELSE
		IF print_help_intro2 = 1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 2072.11 -1793.80 12.56 10.0 10.0 3.0 FALSE
				//REMOVE_BLIP rob_shop_blip
				//ADD_BLIP_FOR_COORD 2072.11 -1793.80 12.56 rob_shop_blip
				//ADD_BLIP_FOR_COORD 414.3000 -19.9000 1000.8120 rob_shop_blip 
				//SET_BLIP_ENTRY_EXIT	rob_shop_blip 2073.0417 -1794.5634 20.0
				PRINT_NOW ( INT2_8 ) 10000 1 // ~s~Go into the Barbers and get a new haircut.
				print_help_intro2 = 0
			ENDIF
		ENDIF
	ENDIF

	IF flag_changed_hair_intro2 = 0
		IF flag_attacked_barber = 1
			PRINT_NOW ( INT2_14 ) 10000 1 //You've freaked out the shop keeper.
			GOTO mission_intro2_failed
		ENDIF
	ENDIF

ENDWHILE
/*
REMOVE_PRICE_MODIFIER afro haircuts
REMOVE_PRICE_MODIFIER afroblond haircuts
REMOVE_PRICE_MODIFIER afrotash  haircuts
REMOVE_PRICE_MODIFIER afrobeard haircuts
REMOVE_PRICE_MODIFIER flattop haircuts

ADD_PRICE_MODIFIER pizzalow food 0 // changes the cost of the food in the shop
ADD_PRICE_MODIFIER pizzamed food 0 // changes the cost of the food in the shop
ADD_PRICE_MODIFIER pizzahigh food 0 // changes the cost of food in the shop
*/

DO_FADE 0 FADE_OUT
CLEAR_PRINTS
REMOVE_BLIP rob_shop_blip
SWITCH_ENTRY_EXIT FDpiza TRUE


// OUTSIDE BARBERS CUTSCENE START *************************************************************************************************************
SWITCH_WIDESCREEN ON //Barbers Cut Scene
SET_PLAYER_CONTROL player1 OFF
CLEAR_AREA 2072.04 -1800.92 12.55 200.0 TRUE
SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0
SET_NEAR_CLIP 0.2

TASK_STAND_STILL scplayer 10

WHILE NOT HAS_ANIMATION_LOADED SHOP
	WAIT 0
	DO_FADE 0 FADE_OUT
ENDWHILE

IF NOT IS_CAR_DEAD ryder_car
	IF NOT IS_CHAR_DEAD	ryder
		
		//CREATE_FX_SYSTEM_ON_OBJECT cigarette_smoke ryders_ciggy 0.0 0.0 0.115 TRUE smoke_effect
		//PLAY_FX_SYSTEM smoke_effect
		WHILE IS_CAR_WAITING_FOR_WORLD_COLLISION ryder_car
			WAIT 0

			IF IS_CAR_DEAD ryder_car
				PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
				GOTO mission_intro2_failed
			ENDIF
			DO_FADE 0 FADE_OUT
		ENDWHILE

		IF NOT IS_CHAR_DEAD	ryder
			SET_CHAR_COORDINATES scplayer 2072.55 -1794.67 12.56 
			SET_CHAR_HEADING scplayer 240.55
			CLEAR_CHAR_TASKS_IMMEDIATELY ryder
			IF IS_CHAR_IN_ANY_CAR ryder			  
				WARP_CHAR_FROM_CAR_TO_COORD ryder 2076.4924 -1796.4132 12.5545 
			ELSE
				SET_CHAR_COORDINATES ryder 2076.4924 -1796.4132 12.5545
			ENDIF
			SET_CHAR_HEADING ryder 80.0
			SET_CHAR_HEALTH ryder 500
			CREATE_OBJECT cigar 2440.58 -1979.89 14.2 ryders_ciggy
			TASK_PICK_UP_OBJECT ryder ryders_ciggy 0.04 0.1 -0.02 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL -1
			TASK_PLAY_ANIM ryder SMOKE_RYD SHOP 4.0 TRUE FALSE FALSE FALSE -1
		ENDIF

		IF NOT IS_CAR_DEAD ryder_car
			CLEAR_AREA 2078.0901 -1796.2098 12.3828 100.0 TRUE
			SET_CAR_COORDINATES ryder_car 2078.0901 -1796.2098 12.3828  
			SET_CAR_HEADING ryder_car 176.9536
		ENDIF
		FIX_CAR_DOOR ryder_car FRONT_LEFT_DOOR
		FIX_CAR_DOOR ryder_car FRONT_RIGHT_DOOR
		CLOSE_ALL_CAR_DOORS	ryder_car	 
		LOCK_CAR_DOORS ryder_car CARLOCK_LOCKOUT_PLAYER_ONLY
	ELSE
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed	
	ENDIF
ELSE
	PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
	GOTO mission_intro2_failed
ENDIF


TIMERA = 0
WHILE NOT TIMERA > 400
	WAIT 0
	
	DO_FADE 0 FADE_OUT

ENDWHILE

CLEAR_PRINTS

IF NOT IS_CHAR_DEAD	ryder
	OPEN_SEQUENCE_TASK player_sequence_1
		TASK_GO_STRAIGHT_TO_COORD -1 2075.53 -1796.32 12.55 PEDMOVE_WALK 10000
		CLEAR_LOOK_AT -1
		TASK_LOOK_AT_CHAR -1 ryder 4500
		//TASK_ACHIEVE_HEADING -1 270.0
	CLOSE_SEQUENCE_TASK player_sequence_1
ENDIF

PERFORM_SEQUENCE_TASK scplayer player_sequence_1
CLEAR_SEQUENCE_TASK	player_sequence_1

				   			    
SET_FIXED_CAMERA_POSITION 2074.8662 -1800.8817 13.4518 0.0 0.0 0.0 // Walking out of shop
POINT_CAMERA_AT_POINT 2075.0671 -1799.9030 13.4124 JUMP_CUT
CLEAR_PRINTS
DO_FADE 500 FADE_IN


//IF NOT IS_CAR_DEAD ryder_car
	//FREEZE_CAR_POSITION ryder_car TRUE
//ENDIF

flag_vomit_playing = 0

intro2_index = 0
intro2_audio_is_playing = 0
intro2_cutscene_flag = 0
intro2_chat_switch = INTRO2_CHAT2
GOSUB intro2_chat_switch

TIMERA = 0
WHILE NOT TIMERA > 1700
	WAIT 0
	
	GOSUB smoke_a_blunt

ENDWHILE

IF can_skip_smoking_cutscene = 1
	SKIP_CUTSCENE_START
ENDIF

IF NOT IS_CHAR_DEAD ryder
	CLEAR_LOOK_AT ryder
	TASK_LOOK_AT_CHAR ryder scplayer 2000
ENDIF

IF NOT IS_CHAR_DEAD ryder
	//CREATE_FX_SYSTEM_ON_CHAR_WITH_DIRECTION cigarette_smoke Ryder 0.183 -0.135 0.609 0.0 0.0 0.0 TRUE exhale_smoke_effect
	CREATE_FX_SYSTEM_ON_CHAR exhale Ryder intr2_vomitX intr2_vomitY intr2_vomitZ TRUE exhale_smoke_effect //
	ATTACH_FX_SYSTEM_TO_CHAR_BONE exhale_smoke_effect Ryder BONE_HEAD
ENDIF

	IF NOT IS_CHAR_DEAD ryder
		STOP_CHAR_FACIAL_TALK ryder
	ENDIF

IF IS_PLAYER_WEARING Player1 CLOTHES_TEX_HEAD tash
OR IS_PLAYER_WEARING Player1 CLOTHES_TEX_HEAD goatee
OR IS_PLAYER_WEARING Player1 CLOTHES_TEX_HEAD beard
OR IS_PLAYER_WEARING Player1 CLOTHES_TEX_HEAD player_face
OR IS_PLAYER_WEARING Player1 CLOTHES_TEX_HEAD highfade
OR IS_PLAYER_WEARING Player1 CLOTHES_TEX_HEAD flattop
	LOAD_MISSION_AUDIO 1 SOUND_INT2_DA //SHIT! I told you he was insane.
	LOAD_MISSION_AUDIO 2 SOUND_INT2_DB //You look lousy
				
	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
		
		GOSUB smoke_a_blunt

		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
			ENDIF
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 1
	PRINT_NOW ( INT2_DA ) 10000 1 //SHIT! I told you he was insane.
	IF NOT IS_CHAR_DEAD ryder
		START_CHAR_FACIAL_TALK ryder 2000
	ENDIF

	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		
		GOSUB smoke_a_blunt

		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
			ENDIF
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD ryder
		STOP_CHAR_FACIAL_TALK ryder
	ENDIF
	
	SET_FIXED_CAMERA_POSITION 2074.8267 -1797.4138 14.2330 0.0 0.0 0.0 // Close up of haircut
	POINT_CAMERA_AT_POINT 2075.4324 -1796.6637 13.9678 JUMP_CUT

	WAIT 1000

	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0
		
		GOSUB smoke_a_blunt

		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
			ENDIF
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2
	PRINT_NOW ( INT2_DB ) 10000 1 //You look lousy
	IF NOT IS_CHAR_DEAD ryder
		START_CHAR_FACIAL_TALK ryder 2000
	ENDIF

	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0
		
		GOSUB smoke_a_blunt

		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
			ENDIF
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD ryder
		STOP_CHAR_FACIAL_TALK ryder
	ENDIF
  
ELSE

	LOAD_MISSION_AUDIO 1 SOUND_INT2_EA	//I take it back, Old Reece still got it.
	LOAD_MISSION_AUDIO 2 SOUND_INT2_EB	//Word.

	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
		
		GOSUB smoke_a_blunt

		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
			ENDIF
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 1
	PRINT_NOW ( INT2_EA ) 10000 1 //I take it back, Old Reece still got it.
	IF NOT IS_CHAR_DEAD ryder
		START_CHAR_FACIAL_TALK ryder 2000
	ENDIF

	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
		
		GOSUB smoke_a_blunt

		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
				
			ENDIF
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD ryder
		STOP_CHAR_FACIAL_TALK ryder
	ENDIF

	SET_FIXED_CAMERA_POSITION 2075.1902 -1797.2825 14.4799 0.0 0.0 0.0 // Close up of haircut
	POINT_CAMERA_AT_POINT 2075.6377 -1796.4714 14.1030 JUMP_CUT

	WAIT 1000
	/*
	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0

		GOSUB smoke_a_blunt
			
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
			ENDIF
		ENDIF

	ENDWHILE


	PLAY_MISSION_AUDIO 2
	PRINT_NOW ( INT2_EB ) 10000 1 //Word.
	IF NOT IS_CHAR_DEAD ryder
		START_CHAR_FACIAL_TALK ryder 3000
	ENDIF

	intro2_cutscene_flag = 0
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0
		
		GOSUB smoke_a_blunt
		
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
				
			ENDIF
		ENDIF

	ENDWHILE
	IF NOT IS_CHAR_DEAD ryder
		STOP_CHAR_FACIAL_TALK ryder
	ENDIF
	*/
ENDIF
	

IF NOT IS_CHAR_DEAD ryder
	STOP_CHAR_FACIAL_TALK ryder
ENDIF

TIMERA = 0
WHILE NOT TIMERA > 1000
	WAIT 0
	
	GOSUB smoke_a_blunt

ENDWHILE

IF NOT IS_CHAR_DEAD ryder
	STOP_CHAR_FACIAL_TALK ryder
ENDIF

IF NOT IS_CHAR_DEAD ryder
	CLEAR_LOOK_AT ryder
	TASK_LOOK_AT_COORD ryder 2102.0 -1806.0 14.0 3000
	//START_CHAR_FACIAL_TALK ryder 2000
ENDIF
intro2_cutscene_flag = 0
WHILE NOT intro2_index = 1 //Shit looks ridiculous.
	WAIT 0

		GOSUB load_and_play_audio_intro2
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
				
				intro2_cutscene_flag = 1
			ENDIF
		ENDIF
		GOSUB smoke_a_blunt

ENDWHILE
//IF NOT IS_CHAR_DEAD ryder
	//STOP_CHAR_FACIAL_TALK ryder
//ENDIF


CLEAR_LOOK_AT scplayer
TASK_LOOK_AT_COORD scplayer 2102.0 -1806.0 14.0 3000

//SET_FIXED_CAMERA_POSITION 2074.1880 -1796.6036 14.1401 0.0 0.0 0.0 // 7/11
//POINT_CAMERA_AT_POINT 2075.1687 -1796.7465 14.0082 JUMP_CUT
	
	CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE                   
    CAMERA_PERSIST_POS TRUE                       
    CAMERA_SET_VECTOR_MOVE 2074.2297 -1795.0304 14.4282 2074.9827 -1798.6713 14.4451 12000 TRUE
    CAMERA_SET_VECTOR_TRACK 2077.1672 -1795.7289 14.1884 2075.9734 -1798.7341 14.5649 12000 TRUE


intro2_cutscene_flag = 0
WHILE NOT intro2_index = 2 //No respect for the neighbourhood, all clean and shit.
	WAIT 0

		GOSUB load_and_play_audio_intro2
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD ryder
					//START_CHAR_FACIAL_TALK ryder 2000
				ENDIF
				intro2_cutscene_flag = 1
			ENDIF
		ENDIF
		GOSUB smoke_a_blunt

ENDWHILE
//IF NOT IS_CHAR_DEAD ryder
	//STOP_CHAR_FACIAL_TALK ryder
//ENDIF

TIMERB = 0

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 3 //You're looking too skinny, CJ.
	WAIT 0

		GOSUB load_and_play_audio_intro2
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD ryder
					//START_CHAR_FACIAL_TALK ryder 2000
				ENDIF
				intro2_cutscene_flag = 1
			ENDIF
		ENDIF
		GOSUB smoke_a_blunt

ENDWHILE
//IF NOT IS_CHAR_DEAD ryder
	//STOP_CHAR_FACIAL_TALK ryder
//ENDIF

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 4 //Go in and get some food - I'm going to finish this.
	WAIT 0

		GOSUB load_and_play_audio_intro2
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
				IF NOT IS_CHAR_DEAD ryder
					//START_CHAR_FACIAL_TALK ryder 2000
				ENDIF
				intro2_cutscene_flag = 1
			ENDIF
		ENDIF
		GOSUB smoke_a_blunt

ENDWHILE

WHILE NOT TIMERB > 18000
	WAIT 0

		IF food_help = 0
			IF TIMERB > 7500
				PRINT_HELP ( FUDHLP1 ) //If you don't eat you will loose weight and eventually energy.
				FLASH_HUD_OBJECT HUD_FLASH_HEALTH
				food_help = 1
			ENDIF
		ENDIF
		IF food_help = 1
			IF TIMERB > 13500
				PRINT_HELP ( FUDHLP2 ) //If you eat you gain energy but eat too much and you'll become fat.
				FLASH_HUD_OBJECT -1
				food_help = 2
			ENDIF
		ENDIF

ENDWHILE

IF can_skip_smoking_cutscene = 1
	SKIP_CUTSCENE_END
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
ENDIF

CLEAR_HELP
CAMERA_RESET_NEW_SCRIPTABLES

PRINT_NOW ( HOOD2_B ) 10000 1 // Go to the Pizza shop
	
	SWITCH_WIDESCREEN OFF
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	
IF NOT IS_CAR_DEAD ryder_car
	SET_CAR_CAN_BE_VISIBLY_DAMAGED ryder_car TRUE
ENDIF

REMOVE_BLIP rob_shop_blip
ADD_BLIP_FOR_COORD 372.90 -118.80 1000.57 rob_shop_blip //PIZZA SHOP
SET_BLIP_ENTRY_EXIT	rob_shop_blip 2103.3762 -1806.3110 20.0

// OUTSIDE BARBERS CUTSCENE END ***************************************************************************************************************

print_help_intro2 = 0
//ACTIVATE_INTERIORS FALSE

flag_menace_buyfood = 1
SET_CAR_DENSITY_MULTIPLIER 0.5
SET_PED_DENSITY_MULTIPLIER 0.5

TIMERB = 0
blob_flag = 1

WHILE NOT LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ 1.0 1.0 3.0 FALSE
OR NOT flag_menace_buyfood = 3
	WAIT 0

	IF stats_help = 0
		IF TIMERB > 2000
			PRINT_HELP FUDHLP3 
			stats_help = 1
		ENDIF
	ENDIF

	IF IS_CAR_DEAD ryder_car
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CHAR_DEAD ryder
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	GOSUB smoke_a_blunt

	IF NOT flag_menace_buyfood = 3
		IF jfud_keep_created = 1
			IF IS_CHAR_DEAD shop_keep_junkfud
				PRINT_NOW ( INT2_10 ) 10000 1 //You killed the shop keeper
				GOTO mission_intro2_failed	
			ELSE
				IF NOT IS_CHAR_HEALTH_GREATER shop_keep_junkfud 99		
					IF NOT IS_CHAR_DEAD shop_keep_junkfud
						GIVE_WEAPON_TO_CHAR shop_keep_junkfud WEAPONTYPE_SHOTGUN 30
						SET_CURRENT_CHAR_WEAPON shop_keep_junkfud WEAPONTYPE_SHOTGUN
						SET_CHAR_RELATIONSHIP shop_keep_junkfud ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						WAIT 1000
						PRINT_NOW ( INT2_14 ) 10000 1 //You've freaked out the shop keeper.
						GOTO mission_intro2_failed
					ENDIF			
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CAR_DEAD ryder_car
			IF ryders_car_moved = 0
				IF NOT LOCATE_CAR_3D ryder_car 2078.09 -1796.20 12.38 0.2 0.2 2.0 FALSE
				OR NOT IS_CHAR_HEALTH_GREATER ryder 499
					TASK_ENTER_CAR_AS_PASSENGER ryder ryder_car 20000 0	
					ryders_car_moved = 1
				ENDIF
			ENDIF 
		ENDIF
	ENDIF

	GET_AREA_VISIBLE new_visible_area
	IF new_visible_area	= 5
		IF print_help_intro2 = 0
			//SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2169.72 -1673.14 20.0
			//CLEAR_AREA -26.91 -89.73 1002.56 100.0 TRUE
			REQUEST_ANIMATION GANGS
			REQUEST_ANIMATION FOOD
			REQUEST_MODEL COLT45
			REQUEST_MODEL CHROMEGUN
			LOAD_SPECIAL_CHARACTER 2 ryder3
			REQUEST_MODEL WMYPIZZ
			TIMERA = 0
			print_help_intro2 = 1
		ENDIF
		IF print_help_intro2 = 1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer 372.05 -130.63 1000.49 20.0 20.0 3.0 FALSE
				PRINT_NOW ( INT2_13 ) 5000 1 //Walk into the marker to buy a pizza.
				//REMOVE_BLIP rob_shop_blip
				//ADD_BLIP_FOR_COORD 374.00 -119.28 1000.50 rob_shop_blip
				print_help_intro2 = 2
			ENDIF
		ENDIF
	ELSE
		IF print_help_intro2 = 2
			CLEAR_PRINTS
			//REMOVE_BLIP rob_shop_blip
			//ADD_BLIP_FOR_COORD 2113.12 -1813.80 12.55 rob_shop_blip
			print_help_intro2 = 1			
		ENDIF
	ENDIF
	
	IF flag_attacked_keeper_food = 1
		PRINT_NOW ( INT2_14 ) 10000 1 //You've freaked out the shop keeper.
		GOTO mission_intro2_failed
	ENDIF

ENDWHILE


// PIZZA ROBBERY CUTSCENE START****************************************************************************************************************


SWITCH_WIDESCREEN ON 
SET_PLAYER_CONTROL player1 OFF
CLEAR_PRINTS
SET_NEAR_CLIP 0.1
STOP_FX_SYSTEM exhale_smoke_effect

MARK_MODEL_AS_NO_LONGER_NEEDED cigar
MARK_MODEL_AS_NO_LONGER_NEEDED cigar_glow
LOAD_MISSION_AUDIO 4 SOUND_BANK_RESTAURANT

WHILE NOT HAS_ANIMATION_LOADED FOOD
OR NOT HAS_ANIMATION_LOADED GANGS
OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
OR NOT HAS_MODEL_LOADED WMYPIZZ
OR NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_MODEL_LOADED CHROMEGUN
	WAIT 0

	IF IS_CAR_DEAD ryder_car
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car.
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CHAR_DEAD ryder
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	IF flag_attacked_keeper_food = 1
		PRINT_NOW ( INT2_14 ) 10000 1 //You've freaked out the shop keeper.
		GOTO mission_intro2_failed
	ENDIF

ENDWHILE

IF NOT IS_CHAR_DEAD ryder
	DROP_OBJECT ryder TRUE
	CLEAR_CHAR_TASKS_IMMEDIATELY ryder
	TASK_STAND_STILL ryder 100
	SET_CHAR_COORDINATES ryder 375.7327 -125.2345 1000.5296 
	SET_CHAR_HEADING ryder 201.2298
	SET_CHAR_HEALTH ryder 500
	SET_CHAR_MAX_HEALTH	ryder 500
	TASK_PLAY_ANIM ryder ROB_Shifty SHOP 4.0 FALSE FALSE FALSE FALSE -1	
ENDIF 

CLEAR_AREA 372.90 -118.80 1000.57 20.0 TRUE	//TEST
CLEAR_AREA 369.058 -4.920 1000.883 20.0 TRUE //TEST
//CLEAR_AREA_OF_CHARS 369.5132 -133.5416 999.0 372.1891 -118.8091 1005.0
//CLEAR_AREA_OF_CHARS 375.4585 -133.4865 999.0 378.1646 -119.0236 1005.0

CREATE_CHAR PEDTYPE_MISSION1 WMYPIZZ 373.14 -117.11 1000.57 hood_shop_keeper
SET_CHAR_HEADING hood_shop_keeper 180.0									   

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH shopkeeper_decision
SET_CHAR_DECISION_MAKER	hood_shop_keeper shopkeeper_decision

CLEAR_LOOK_AT hood_shop_keeper
TASK_LOOK_AT_CHAR hood_shop_keeper scplayer 4700
TASK_TURN_CHAR_TO_FACE_CHAR hood_shop_keeper scplayer 

//SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
SET_CHAR_COORDINATES scplayer 372.90 -118.80 1000.57 
TASK_TURN_CHAR_TO_FACE_CHAR scplayer hood_shop_keeper
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED


OPEN_SEQUENCE_TASK intro2_sequence_2
	//TASK_GO_STRAIGHT_TO_COORD -1 376.4 -121.6 1000.5 PEDMOVE_WALK 4000 //Ryder goes to rob shop
	TASK_GO_STRAIGHT_TO_COORD -1 375.2 -119.8 1000.5 PEDMOVE_WALK 7000 //Ryder goes to rob shop
	//TASK_AIM_GUN_AT_CHAR -1 hood_shop_keeper 3000
	TASK_PLAY_ANIM -1 ROB_StickUp_In SHOP 4.0 FALSE FALSE FALSE TRUE -1
	TASK_PLAY_ANIM -1 ROB_Loop_Threat SHOP 4.0 TRUE FALSE FALSE FALSE -1
CLOSE_SEQUENCE_TASK intro2_sequence_2

intro2_index = 0
intro2_audio_is_playing = 0
intro2_cutscene_flag = 0
intro2_chat_switch = INTRO2_CHAT3
GOSUB intro2_chat_switch


WAIT 200

IF cj_vomits_for_menace	= 0
	TASK_PLAY_ANIM scplayer EAT_Pizza FOOD 4.0 FALSE FALSE FALSE FALSE -1
ELSE
	
	GET_CHAR_COORDINATES scplayer player_X player_Y player_Z 
    intr2_vomitX = player_X + 0.355
    intr2_vomitY = player_Y - 0.116
    intr2_vomitZ = player_Z - 0.048
    CREATE_FX_SYSTEM puke intr2_vomitX intr2_vomitY intr2_vomitZ TRUE vomit_menace
	//CREATE_FX_SYSTEM_ON_CHAR puke scplayer intr2_vomitX intr2_vomitY intr2_vomitZ TRUE vomit_menace
	//ATTACH_FX_SYSTEM_TO_CHAR_BONE vomit_menace scplayer BONE_HEAD
    TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer Eat_Vomit_P FOOD 4.0 FALSE FALSE FALSE FALSE -1
	SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PAIN_CJ_PUKE sample_name_shop
	//TASK_PLAY_ANIM scplayer Eat_Vomit_P FOOD 4.0 FALSE FALSE FALSE FALSE -1
ENDIF

//BUILD_PLAYER_MODEL player1

DO_FADE 500 FADE_IN

SET_MUSIC_DOES_FADE TRUE

SET_FIXED_CAMERA_POSITION 376.4939 -126.0317 1002.3955 0.0 0.0 0.0 //Ryder putting mask on
POINT_CAMERA_AT_POINT 375.9087 -125.2215 1002.3610 JUMP_CUT

WAIT 4500
	
CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE
CAMERA_SET_VECTOR_MOVE 377.0709 -121.8843 1000.7144 377.3563 -119.1986 1002.5696 7000 TRUE
CAMERA_SET_VECTOR_TRACK 376.4134 -122.6288 1000.5984 376.4230 -119.1806 1002.2109 7000 TRUE
         
                   
//CAMERA_SET_VECTOR_MOVE 377.0709 -121.8843 1000.7144 377.5171 -121.7195 1002.3387 8000 TRUE
//CAMERA_SET_VECTOR_TRACK 376.4134 -122.6288 1000.5984 376.8156 -121.0648 1002.0571 8000 TRUE
	  
//SET_FIXED_CAMERA_POSITION 379.9358 -122.7232 1002.5712 0.0 0.0 0.0 //Player talks to shopkeeper
//POINT_CAMERA_AT_POINT 378.9661 -122.5710 1002.3801 JUMP_CUT

DELETE_CHAR	ryder
UNLOAD_SPECIAL_CHARACTER 1
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL02 376.3327 -125.2345 1000.5296 ryder
SET_ANIM_GROUP_FOR_CHAR ryder gang1 
SET_CHAR_NEVER_TARGETTED ryder TRUE
SET_CHAR_HEALTH ryder 500
SET_CHAR_MAX_HEALTH ryder 500
SET_CHAR_SUFFERS_CRITICAL_HITS ryder FALSE
SET_CHAR_CANT_BE_DRAGGED_OUT ryder TRUE
SET_CHAR_STAY_IN_CAR_WHEN_JACKED ryder TRUE
SET_CHAR_DECISION_MAKER ryder ryders_decision
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR ryder FALSE


IF NOT IS_CHAR_DEAD ryder
	GIVE_WEAPON_TO_CHAR ryder WEAPONTYPE_PISTOL 30
	SET_CURRENT_CHAR_WEAPON ryder WEAPONTYPE_PISTOL
	IF NOT IS_CHAR_DEAD hood_shop_keeper
		PERFORM_SEQUENCE_TASK ryder intro2_sequence_2
		CLEAR_SEQUENCE_TASK	intro2_sequence_2
	ENDIF
ENDIF

skipped_pizza_cutscene = 0
SKIP_CUTSCENE_START

TIMERA = 0
flag_vomit_playing = 0
	
WHILE NOT LOCATE_CHAR_ANY_MEANS_3D ryder 375.2 -119.8 1000.5 0.4 0.6 2.0 FALSE	 
	WAIT 0

	IF IS_CHAR_PLAYING_ANIM scplayer Eat_Vomit_P
		GET_CHAR_ANIM_CURRENT_TIME scplayer Eat_Vomit_P return_animation_food_name
        IF flag_vomit_playing = 0
	        IF return_animation_food_name >= 0.463
				IF HAS_MISSION_AUDIO_LOADED 4
					REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_RESTAURANT_CJ_PUKE
				ENDIF
				flag_vomit_playing = 1
			ENDIF
		ENDIF
		IF flag_vomit_playing = 1
			IF return_animation_food_name >= 0.52
		       	PLAY_FX_SYSTEM vomit_menace
		        flag_vomit_playing = 2
		    ENDIF
		ENDIF
    ENDIF     

	IF IS_CAR_DEAD ryder_car
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CHAR_DEAD ryder
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF
	
	IF flag_attacked_keeper_food = 1
		PRINT_NOW ( INT2_14 ) 10000 1 //You've freaked out the shop keeper.
		GOTO mission_intro2_failed
	ENDIF
	
	IF player_chats_a_bit = 0
		IF TIMERA > 1000
			IF cj_vomits_for_menace	= 0
				TASK_PLAY_ANIM scplayer IDLE_CHAT PED 4.0 FALSE FALSE FALSE FALSE 3000
			ENDIF
			player_chats_a_bit = 1
		ENDIF
	ENDIF
	 
ENDWHILE

IF NOT IS_CHAR_DEAD hood_shop_keeper
	IF NOT IS_CHAR_DEAD ryder
		CLEAR_CHAR_TASKS hood_shop_keeper
		CLEAR_LOOK_AT hood_shop_keeper
		TASK_LOOK_AT_CHAR hood_shop_keeper ryder 12500
		TASK_STAND_STILL scplayer 100
		CLEAR_LOOK_AT scplayer
		TASK_LOOK_AT_CHAR scplayer ryder 10000
		SET_CHAR_HEADING hood_shop_keeper 200.0
		TASK_PLAY_ANIM hood_shop_keeper SHP_Rob_HandsUp SHOP 4.0 TRUE FALSE FALSE FALSE -1
	ENDIF
ENDIF

//SET_FIXED_CAMERA_POSITION 379.4649 -118.3413 1003.0241 0.0 0.0 0.0 //High shot
//POINT_CAMERA_AT_POINT 378.5176 -118.5047 1002.7488 JUMP_CUT

//SET_FIXED_CAMERA_POSITION 378.2092 -118.5325 1002.6589 0.0 0.0 0.0 //High shot
//POINT_CAMERA_AT_POINT 377.2849 -118.6503 1002.2957 JUMP_CUT

iSetPizzaPanic = 1

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 1 //Give us the money! This is a raid!
	WAIT 0

	GOSUB load_and_play_audio_intro2
	IF intro2_cutscene_flag = 0
		IF intro2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD ryder
				CLEAR_LOOK_AT scplayer
				TASK_LOOK_AT_CHAR scplayer ryder 6000
			ENDIF
			intro2_cutscene_flag = 1
		ENDIF
	ENDIF

ENDWHILE


intro2_cutscene_flag = 0
WHILE NOT intro2_index = 2 //Ryder! Not this again!
	WAIT 0

	GOSUB load_and_play_audio_intro2
	IF intro2_cutscene_flag = 0
		IF intro2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD ryder
				TASK_PLAY_ANIM ryder ROB_Loop SHOP 4.0 TRUE FALSE FALSE FALSE -1 //IDLE
			ENDIF
			IF NOT IS_CHAR_DEAD hood_shop_keeper
				CLEAR_LOOK_AT scplayer
				TASK_LOOK_AT_CHAR scplayer hood_shop_keeper 6000
			ENDIF
			intro2_cutscene_flag = 1
		ENDIF
	ENDIF

ENDWHILE


intro2_cutscene_flag = 0
WHILE NOT intro2_index = 3 //It ain't me, fool!
	WAIT 0

	GOSUB load_and_play_audio_intro2
	IF intro2_cutscene_flag = 0
		IF intro2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD ryder
				TASK_PLAY_ANIM ryder ROB_Loop_Threat SHOP 4.0 TRUE FALSE FALSE FALSE -1 //THREAT
			ENDIF
			IF NOT IS_CHAR_DEAD ryder
				CLEAR_LOOK_AT scplayer
				TASK_LOOK_AT_CHAR scplayer ryder 6000
			ENDIF
			CAMERA_RESET_NEW_SCRIPTABLES
			SET_FIXED_CAMERA_POSITION 374.9156 -121.7312 1002.5003 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT 374.7307 -120.7778 1002.2621 JUMP_CUT
			intro2_cutscene_flag = 1
		ENDIF
	ENDIF

ENDWHILE


intro2_cutscene_flag = 0
WHILE NOT intro2_index = 4 //No one else that small! I feel sorry for your dad!
	WAIT 0

	GOSUB load_and_play_audio_intro2
	IF intro2_cutscene_flag = 0
		IF intro2_audio_is_playing = 2
			IF NOT IS_CHAR_DEAD hood_shop_keeper
				CLEAR_LOOK_AT scplayer
				TASK_LOOK_AT_CHAR scplayer hood_shop_keeper 6000
			ENDIF

			IF NOT IS_CHAR_DEAD ryder
				TASK_PLAY_ANIM ryder ROB_Loop SHOP 4.0 TRUE FALSE FALSE FALSE -1 //IDLE
			ENDIF
			intro2_cutscene_flag = 1
		ENDIF
	ENDIF

ENDWHILE

OPEN_SEQUENCE_TASK intro2_sequence_4
	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
	TASK_PLAY_ANIM -1 ROB_2Idle SHOP 4.0 FALSE FALSE FALSE TRUE -1
	TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
CLOSE_SEQUENCE_TASK intro2_sequence_4

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 5 //Shit, man, you're rumbled! Let's get out of here!
	WAIT 0

	GOSUB load_and_play_audio_intro2
	IF intro2_cutscene_flag = 0
		IF intro2_audio_is_playing = 2
			SET_FIXED_CAMERA_POSITION 371.5620 -120.1569 1002.4044 0.0 0.0 0.0 //behind CJ
			POINT_CAMERA_AT_POINT 372.4125 -119.6767 1002.1899 JUMP_CUT

			IF NOT IS_CHAR_DEAD ryder
				CLEAR_CHAR_TASKS scplayer
				CLEAR_LOOK_AT scplayer
				TASK_LOOK_AT_CHAR scplayer ryder 10000
				CLEAR_CHAR_TASKS ryder
				CLEAR_LOOK_AT ryder
				TASK_LOOK_AT_CHAR ryder scplayer 10000
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer ryder
				TASK_PLAY_ANIM scplayer prtial_gngtlkA GANGS 4.0 FALSE FALSE FALSE FALSE -1
			ENDIF
			intro2_cutscene_flag = 1
		ENDIF
	ENDIF

ENDWHILE

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 6 //Same old CJ, Buster! Buster!
	WAIT 0

	GOSUB load_and_play_audio_intro2
	IF intro2_cutscene_flag = 0
		IF intro2_audio_is_playing = 2
			SET_FIXED_CAMERA_POSITION 374.6357 -121.6844 1001.6368 0.0 0.0 0.0 //All 3 wide
			POINT_CAMERA_AT_POINT 374.5972 -120.6883 1001.7162 JUMP_CUT

			IF NOT IS_CHAR_DEAD ryder
				PERFORM_SEQUENCE_TASK ryder intro2_sequence_4
				CLEAR_SEQUENCE_TASK	intro2_sequence_4
			ENDIF
			intro2_cutscene_flag = 1
		ENDIF
	ENDIF

ENDWHILE

//CLEAR_AREA_OF_CHARS 369.5132 -133.5416 999.0 372.1891 -118.8091 1005.0
//CLEAR_AREA_OF_CHARS 375.4585 -133.4865 999.0 378.1646 -119.0236 1005.0

IF NOT IS_CHAR_DEAD ryder
	CLEAR_CHAR_TASKS ryder
ENDIF

IF NOT IS_CHAR_DEAD hood_shop_keeper
	SET_CHAR_HEADING hood_shop_keeper 180.0
	TASK_PLAY_ANIM hood_shop_keeper SHP_Gun_Grab SHOP 4.0 FALSE FALSE FALSE TRUE -1
ENDIF

WAIT 500

IF NOT IS_CHAR_DEAD hood_shop_keeper
	GIVE_WEAPON_TO_CHAR hood_shop_keeper WEAPONTYPE_SHOTGUN 30000
	SET_CURRENT_CHAR_WEAPON hood_shop_keeper WEAPONTYPE_SHOTGUN
ENDIF

WAIT 500
 
//CLEAR_AREA_OF_CHARS 369.5132 -133.5416 999.0 372.1891 -118.8091 1005.0
//CLEAR_AREA_OF_CHARS 375.4585 -133.4865 999.0 378.1646 -119.0236 1005.0

SET_FIXED_CAMERA_POSITION 373.0300 -116.1498 1002.3745 0.0 0.0 0.0 //Shopkeeper firing over shoulder
POINT_CAMERA_AT_POINT 373.0192 -117.1292 1002.1727 JUMP_CUT

WAIT 300

IF NOT IS_CHAR_DEAD hood_shop_keeper
	TASK_SHOOT_AT_COORD hood_shop_keeper 374.2905 -125.6810 1001.3077 2000
ENDIF

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 7 //Motherfucker! RUN!
	WAIT 0

	GOSUB load_and_play_audio_intro2
	IF intro2_cutscene_flag = 0
		IF intro2_audio_is_playing = 2
			 
			FLUSH_ROUTE
			EXTEND_ROUTE 372.0 -120.1 1000.5					  
			EXTEND_ROUTE 370.5 -132.3 1000.4
						 
			TASK_FOLLOW_POINT_ROUTE scplayer PEDMOVE_RUN FOLLOW_ROUTE_ONCE

			FLUSH_ROUTE
			EXTEND_ROUTE 376.3 -121.0 1000.5					  
			EXTEND_ROUTE 375.9 -132.0 1000.5
						 
			IF NOT IS_CHAR_DEAD ryder
				TASK_FOLLOW_POINT_ROUTE ryder PEDMOVE_RUN FOLLOW_ROUTE_ONCE
			ENDIF

			IF NOT IS_CHAR_DEAD ryder
			AND NOT IS_CHAR_DEAD hood_shop_keeper
				CLEAR_LOOK_AT ryder
				CLEAR_LOOK_AT scplayer
				CLEAR_LOOK_AT hood_shop_keeper
			ENDIF
			IF NOT IS_CHAR_DEAD hood_shop_keeper
				TASK_SHOOT_AT_COORD hood_shop_keeper 374.2905 -125.6810 1001.3077 1500
			ENDIF
			intro2_cutscene_flag = 1
		ENDIF
	ENDIF

ENDWHILE

skipped_pizza_cutscene = 1
SKIP_CUTSCENE_END

IF skipped_pizza_cutscene = 0

	DO_FADE 0 FADE_OUT //End cutscene

	CLEAR_PRINTS

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

ELSE

	DO_FADE 500 FADE_OUT //End cutscene

	CLEAR_PRINTS

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

ENDIF

CAMERA_RESET_NEW_SCRIPTABLES

CLEAR_AREA 372.90 -118.80 1000.57 100.0 TRUE

SET_AREA_VISIBLE 0

TASK_STAND_STILL scplayer 0
SET_CHAR_COORDINATES scplayer 2102.21 -1806.65 12.4
SET_CHAR_HEADING scplayer 75.0
SET_CHAR_CANT_BE_DRAGGED_OUT scplayer TRUE
SET_CHAR_AREA_VISIBLE scplayer 0

WAIT 0

IF NOT IS_CHAR_DEAD ryder
	IF NOT IS_CAR_DEAD ryder_car
		CLEAR_LOOK_AT ryder
		CLEAR_LOOK_AT scplayer
		CLEAR_AREA 2077.57 -1796.41 12.38 100.0 TRUE
		SET_CAR_COORDINATES ryder_car 2077.57 -1796.41 12.0
		SET_CAR_HEADING ryder_car 180.0
		SET_CAR_HEALTH ryder_car 1000
		/*
		WHILE IS_CAR_WAITING_FOR_WORLD_COLLISION ryder_car
			WAIT 0

			IF IS_CAR_DEAD ryder_car
				PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
				GOTO mission_intro2_failed
			ENDIF
			DO_FADE 0 FADE_OUT
		ENDWHILE
		*/
		CLEAR_AREA 2098.61 -1801.62 12.39 100.0 TRUE
		SET_CHAR_COORDINATES ryder 2092.61 -1801.62 12.39
		SET_CHAR_HEADING ryder 72.0
		SET_CHAR_AREA_VISIBLE ryder 0

		LOCK_CAR_DOORS ryder_car CARLOCK_UNLOCKED
		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
		TASK_ENTER_CAR_AS_PASSENGER ryder ryder_car 40000 0
	ENDIF
ENDIF

RELEASE_WEATHER
SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
CLEAR_PRINTS

CLEAR_LOADED_SHOP
CLEAR_EXTRA_COLOURS FALSE
REMOVE_ANIMATION SHOP
REMOVE_ANIMATION GANGS
REMOVE_ANIMATION FOOD
// PIZZA ROBBERY CUTSCENE END******************************************************************************************************************

ryders_car_blip_removed = 0

PRINT_NOW ( INT2_6 ) 10000 1  // ~s~Get in ~b~Ryder's car~s~!

IF IS_CHAR_DEAD ryder
	GOTO mission_intro2_failed
ELSE
	SET_CHAR_HEALTH ryder 500
	SET_CHAR_MAX_HEALTH	ryder 500
	ADD_BLIP_FOR_CHAR ryder ryders_blip
	REMOVE_BLIP ryders_blip
ENDIF

REMOVE_BLIP rob_shop_blip

IF NOT IS_CAR_DEAD ryder_car
	REMOVE_BLIP ryders_car_blip
	ADD_BLIP_FOR_CAR ryder_car ryders_car_blip
	SET_BLIP_AS_FRIENDLY ryders_car_blip TRUE
ELSE
	GOTO mission_intro2_failed
ENDIF

SWITCH_ENTRY_EXIT FDpiza FALSE
blob_flag = 1
print_into2_text = 0

OPEN_SEQUENCE_TASK shop_keepers_shoots_outside
	TASK_GO_STRAIGHT_TO_COORD -1 2102.77 -1806.35 12.55 PEDMOVE_RUN 4000
	TASK_SHOOT_AT_COORD -1 2092.67 -1803.64 15.56 1000
CLOSE_SEQUENCE_TASK shop_keepers_shoots_outside

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

LOAD_MISSION_AUDIO 1 SOUND_INT2_HA	//This pizza parlour is no push-over!				

WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	WAIT 0
	
ENDWHILE

LOAD_SCENE_IN_DIRECTION 2102.21 -1806.65 12.4 75.0

DO_FADE 500 FADE_IN


IF IS_CAR_DEAD ryder_car
	PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car.
	GOTO mission_intro2_failed
ENDIF

IF IS_CHAR_DEAD ryder
	PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
	GOTO mission_intro2_failed
ENDIF

intro2_index = 0
intro2_audio_is_playing = 0
intro2_cutscene_flag = 0
intro2_chat_switch = INTRO2_CHAT5
GOSUB intro2_chat_switch

TIMERA = 0
TIMERB = 0

CLEAR_AREA 2077.57 -1796.41 12.38 20.0 TRUE

WHILE NOT LOCATE_CAR_3D ryder_car 2473.88 -1692.81 12.52 4.0 4.0 4.0 blob_flag //Race back to the hood
OR NOT IS_CHAR_IN_CAR ryder ryder_car
OR NOT IS_CHAR_SITTING_IN_CAR scplayer ryder_car
OR NOT IS_VEHICLE_ON_ALL_WHEELS ryder_car
	WAIT 0

	IF IS_CAR_DEAD ryder_car
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CHAR_DEAD ryder
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF
  
	IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer 2104.62 -1806.54 12.55 7.0 6.0 3.0 FALSE
		IF shopkeeper_goes_radge = 0
			IF NOT IS_CHAR_DEAD hood_shop_keeper
				CLEAR_CHAR_TASKS hood_shop_keeper
				CLEAR_CHAR_TASKS_IMMEDIATELY hood_shop_keeper
				SET_CHAR_COORDINATES hood_shop_keeper 2105.32 -1806.24 12.55
				SET_CHAR_HEADING hood_shop_keeper 90.0
				GIVE_WEAPON_TO_CHAR hood_shop_keeper WEAPONTYPE_SHOTGUN 30000
				SET_CURRENT_CHAR_WEAPON hood_shop_keeper WEAPONTYPE_SHOTGUN
				SET_CHAR_ACCURACY hood_shop_keeper 30
				SET_NEAR_CLIP 0.1
				IF HAS_MISSION_AUDIO_LOADED 1
					PLAY_MISSION_AUDIO 1
					PRINT_NOW ( INT2_HA ) 2000 1 //This pizza parlour is no push-over!
				ENDIF
				PERFORM_SEQUENCE_TASK hood_shop_keeper shop_keepers_shoots_outside
				CLEAR_SEQUENCE_TASK	shop_keepers_shoots_outside
				TIMERA = 0
				shopkeeper_goes_radge = 1
			ENDIF
		ELSE
			IF TIMERA > 2000
				IF shopkeeper_goes_radge = 1
					IF NOT IS_CHAR_DEAD hood_shop_keeper
						IF LOCATE_CHAR_ON_FOOT_3D hood_shop_keeper 2102.77 -1806.35 12.55 1.0 1.0 3.0 FALSE
							SET_CHAR_RELATIONSHIP hood_shop_keeper ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	
							shopkeeper_goes_radge = 2
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF		
	ENDIF

	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CAR_DEAD ryder_car
			IF IS_CHAR_IN_CAR scplayer ryder_car
				IF IS_CHAR_SITTING_IN_CAR ryder ryder_car
					GOSUB load_and_play_audio_intro2
					IF got_in_ryders_truck_first = 0
						TIMERB = 0
						got_in_ryders_truck_first = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF NOT IS_CHAR_DEAD ryder
		IF NOT IS_CAR_DEAD ryder_car
			IF IS_CHAR_IN_CAR scplayer ryder_car
				IF IS_CHAR_SITTING_IN_CAR ryder ryder_car
					IF ryders_car_blip_removed = 0
						REMOVE_BLIP ryders_blip
						REMOVE_BLIP ryders_car_blip
						REMOVE_BLIP rob_shop_blip
						ADD_BLIP_FOR_COORD 2473.88 -1692.81 12.52 rob_shop_blip
						blob_flag = 1
						IF got_in_ryders_truck_first = 1
							IF TIMERB > 7000
								
								PRINT_NOW ( INT2_O ) 10000 1 // Drive ryder home

								ryders_car_blip_removed = 1
							ENDIF
						ENDIF
					ENDIF
				ELSE
					REMOVE_BLIP ryders_car_blip
					REMOVE_BLIP rob_shop_blip
					REMOVE_BLIP ryders_blip
					ADD_BLIP_FOR_CHAR ryder ryders_blip
					SET_BLIP_AS_FRIENDLY ryders_blip TRUE
					PRINT_NOW ( INT2_5 ) 1000 1 // ~s~You've left Ryder behind!	
					GET_SCRIPT_TASK_STATUS ryder TASK_ENTER_CAR_AS_PASSENGER ReturnStatus
					IF ReturnStatus = FINISHED_TASK
						TASK_ENTER_CAR_AS_PASSENGER ryder ryder_car 20000 0
					ENDIF
					blob_flag = 0
				ENDIF			
			ELSE
				IF ryders_car_blip_removed = 1 
					REMOVE_BLIP rob_shop_blip
					REMOVE_BLIP ryders_car_blip
					ADD_BLIP_FOR_CAR ryder_car ryders_car_blip
					SET_BLIP_AS_FRIENDLY ryders_car_blip TRUE
					blob_flag = 0
					GOSUB get_back_in_the_car_intro2
					PRINT_NOW ( INT2_6 ) 10000 1  // ~s~Get in ~b~Ryder's car~s~!
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
					STOP_CHAR_FACIAL_TALK scplayer
					ryders_car_blip_removed = 0
				ENDIF
			ENDIF
		ELSE
		 	PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car!
			GOTO mission_intro2_failed
		ENDIF	
	ENDIF

	IF IS_CHAR_DEAD ryder
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
		GOTO mission_intro2_failed
	ENDIF

	IF IS_CAR_DEAD ryder_car
	//OR intro2_failed_flag = 1
		PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
		GOTO mission_intro2_failed
	ENDIF

ENDWHILE

	SET_PLAYER_CONTROL player1 OFF

	intro2_index = 0
	intro2_audio_is_playing = 0
	intro2_cutscene_flag = 0
	intro2_chat_switch = INTRO2_CHAT4
	GOSUB intro2_chat_switch

	IF NOT IS_CHAR_DEAD	ryder
		IF NOT IS_CAR_DEAD ryder_car
			TASK_LEAVE_CAR ryder ryder_car
			WAIT 700
			IF NOT IS_CAR_DEAD ryder_car
				TASK_LEAVE_CAR scplayer ryder_car
			ENDIF
		ENDIF
	ENDIF

	DO_FADE 1600 FADE_OUT //End cutscene

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	SWITCH_WIDESCREEN ON

	DO_FADE 1500 FADE_IN


	IF NOT IS_CHAR_DEAD	ryder
		IF IS_CHAR_IN_ANY_CAR ryder
			WARP_CHAR_FROM_CAR_TO_COORD ryder 2467.3447 -1688.2196 12.5156  
		ELSE
			SET_CHAR_COORDINATES ryder 2467.3447 -1688.2196 12.5156
		ENDIF
	ENDIF

	SET_CHAR_HEADING ryder 284.0

	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 2468.3594 -1687.3903 12.518  
	ELSE
		SET_CHAR_COORDINATES scplayer 2468.3594 -1687.3903 12.5189
	ENDIF

	SET_CHAR_HEADING scplayer 108.0

  	SET_FIXED_CAMERA_POSITION 2464.7715 -1687.4926 14.2709 0.0 0.0 0.0 
	POINT_CAMERA_AT_POINT 2465.7505 -1687.6754 14.1833 JUMP_CUT
	
intro2_cutscene_flag = 0
WHILE NOT intro2_index = 1 // You better drop by and see Sweet.
	WAIT 0

		GOSUB load_and_play_audio_intro2
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2

				intro2_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 2 // He's been rapping on about that grafitti.
	WAIT 0

		GOSUB load_and_play_audio_intro2
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2

				intro2_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE

intro2_cutscene_flag = 0
WHILE NOT intro2_index = 3 // Later, dude.
	WAIT 0

		GOSUB load_and_play_audio_intro2
		IF intro2_cutscene_flag = 0
			IF intro2_audio_is_playing = 2
					IF NOT IS_CHAR_DEAD	ryder
						TASK_GO_STRAIGHT_TO_COORD ryder 2459.79 -1688.18 12.56 PEDMOVE_WALK 3000
					ENDIF
				intro2_cutscene_flag = 1
			ENDIF
		ENDIF

ENDWHILE
	
	TASK_GO_STRAIGHT_TO_COORD scplayer 2471.58 -1685.73 12.51 PEDMOVE_WALK 3000
	IF NOT IS_CHAR_DEAD	ryder
		STOP_CHAR_FACIAL_TALK ryder
	ENDIF

	WAIT 1000

	CLEAR_CHAR_TASKS scplayer
	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT

	DELETE_CHAR ryder



GOTO mission_intro2_passed


 // **************************************** Mission intro2 failed **************************

mission_intro2_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	can_skip_smoking_cutscene = 1

RETURN  

// **************************************** mission intro2 passed ***************************

mission_intro2_passed:

	REGISTER_MISSION_PASSED ( INTRO_2 ) //Used in the stats 
	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 3 5000 1 //"Mission Passed!"
	AWARD_PLAYER_MISSION_RESPECT 3
	SWITCH_CAR_GENERATOR gen_car6 101
	flag_intro_mission_counter ++
	REMOVE_BLIP intro_contact_blip
	START_NEW_SCRIPT sweet_mission_loop
	REMOVE_BLIP	sweet_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
	CLEAR_WANTED_LEVEL player1
	SWITCH_ENTRY_EXIT barbers TRUE 
	SWITCH_ENTRY_EXIT barber2 TRUE 
	SWITCH_ENTRY_EXIT barber3 TRUE 
	SWITCH_ENTRY_EXIT FDpiza TRUE
	SWITCH_ENTRY_EXIT fdchick TRUE
	SWITCH_ENTRY_EXIT fdburg TRUE
	SWITCH_ENTRY_EXIT tattoo TRUE

	REMOVE_BLIP barber_shop1
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2070.2703 -1791.0918 17.1484 RADAR_SPRITE_BARBERS barber_shop1 //Barbers
	REMOVE_BLIP food_shop1
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2107.6243 -1807.5155 21.2114 RADAR_SPRITE_PIZZA food_shop1 //Pizza
	PLAY_MISSION_PASSED_TUNE 1

	IF added_all_food_blips_before = 0
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 822.6, -1590.3, 13.5 RADAR_SPRITE_BARBERS barbers_blips[0] //BARBER2
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2570.1, 245.4, 10.3 RADAR_SPRITE_BARBERS barbers_blips[1] //BARBERS
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2726.6, -2026.4, 17.5 RADAR_SPRITE_BARBERS barbers_blips[2]
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2080.3, 2119.0, 10.8 RADAR_SPRITE_BARBERS barbers_blips[3] //BARBER2
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 675.7, -496.6, 16.8 RADAR_SPRITE_BARBERS barbers_blips[4] //BARBERS

		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1805.7, 943.2, 24.8 RADAR_SPRITE_PIZZA pizza_blips[0] //FDPIZA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2750.9, 2470.9, 11.0 RADAR_SPRITE_PIZZA pizza_blips[1] //FDPIZA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2351.8, 2529.0, 10.8 RADAR_SPRITE_PIZZA pizza_blips[2] //FDPIZA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2635.5, 1847.4, 11.0 RADAR_SPRITE_PIZZA pizza_blips[3] //FDPIZA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2083.4, 2221.0, 11.0 RADAR_SPRITE_PIZZA pizza_blips[4] //FDPIZA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1719.1, 1359.4, 8.6 RADAR_SPRITE_PIZZA pizza_blips[5] //FDPIZA
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2330.2, 75.2, 31.0 RADAR_SPRITE_PIZZA pizza_blips[6]//NEW!!!!!!!!!
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 203.2, -200.4, 6.5 RADAR_SPRITE_PIZZA pizza_blips[7] //NEW!!!!!!!!!

		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 812.9, -1616.1, 13.6 RADAR_SPRITE_BURGERSHOT burger_blips[0] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1199.1, -924.0, 43.3 RADAR_SPRITE_BURGERSHOT burger_blips[1] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2362.2, 2069.9, 10.8 RADAR_SPRITE_BURGERSHOT burger_blips[2] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2469.5, 2033.8, 10.8 RADAR_SPRITE_BURGERSHOT burger_blips[3] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2172.9, 2795.7, 10.8 RADAR_SPRITE_BURGERSHOT burger_blips[4] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1875.3, 2072.0, 10.8 RADAR_SPRITE_BURGERSHOT burger_blips[5] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1161.5, 2072.0, 10.8 RADAR_SPRITE_BURGERSHOT burger_blips[6] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2356.0, 1009.0, 49.0 RADAR_SPRITE_BURGERSHOT burger_blips[7] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1913.3, 826.2, 36.9 RADAR_SPRITE_BURGERSHOT burger_blips[8] //FDBURG
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2335.6, -165.6, 39.5 RADAR_SPRITE_BURGERSHOT burger_blips[9] //FDBURG

		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2397.8, -1895.6, 13.7 RADAR_SPRITE_CHICKEN chicken_blips[0] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2421.6, -1509.6, 24.1 RADAR_SPRITE_CHICKEN chicken_blips[1] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2671.6, 257.4, 4.6 RADAR_SPRITE_CHICKEN chicken_blips[2] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2392.4, 2046.5, 10.8 RADAR_SPRITE_CHICKEN chicken_blips[3] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2844.5, 2401.1, 11.0 RADAR_SPRITE_CHICKEN chicken_blips[4] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2635.5, 1674.3, 11.0 RADAR_SPRITE_CHICKEN chicken_blips[5] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2105.7, 2228.7, 11.0 RADAR_SPRITE_CHICKEN chicken_blips[6] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2154.0, -2461.2, 30.8 RADAR_SPRITE_CHICKEN chicken_blips[7] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1816.2, 620.8, 37.5 RADAR_SPRITE_CHICKEN chicken_blips[8] //FDCHICK
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -1216.0, 1831.4, 45.3 RADAR_SPRITE_CHICKEN chicken_blips[9] //NEW!!!!!!!!!
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 172.73, 1176.76, 13.7  RADAR_SPRITE_CHICKEN chicken_blips[10] //NEW!!!!!!!!!
		
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 1971.7, -2036.6, 13.5 RADAR_SPRITE_TATTOO tattoo_blips[0] //TATTOO
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2071.6, -1779.9, 13.5 RADAR_SPRITE_TATTOO tattoo_blips[1] //TATTOO
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2094.6, 2119.0, 10.8 RADAR_SPRITE_TATTOO tattoo_blips[2] //TATTOO
		ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD -2490.5, -40.1, 39.3 RADAR_SPRITE_TATTOO tattoo_blips[3] //TATTOO
		added_all_food_blips_before = 1
	ENDIF

	PLAYER_MADE_PROGRESS 1
RETURN
		


// ********************************** mission cleanup **************************************

mission_cleanup_intro2:
	flag_player_on_mission = 0
	IF IS_PLAYER_PLAYING player1
		SET_CHAR_CANT_BE_DRAGGED_OUT scplayer FALSE
		CLEAR_LOOK_AT scplayer
	ENDIF
	IF NOT IS_CAR_DEAD ryder_car
		SET_CAN_BURST_CAR_TYRES ryder_car TRUE
	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYPIZZ
	MARK_MODEL_AS_NO_LONGER_NEEDED PICADOR
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED cigar
	MARK_MODEL_AS_NO_LONGER_NEEDED cigar_glow
	REMOVE_ANIMATION SHOP
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION FOOD
		
	flag_menace_buyfood = 0
	flag_changed_hair_intro2 = 0
	GET_GAME_TIMER timer_mobile_start
	REMOVE_BLIP ryders_blip
	REMOVE_BLIP rob_shop_blip
	REMOVE_BLIP ryders_car_blip
	REMOVE_CHAR_ELEGANTLY ryder
	MARK_CHAR_AS_NO_LONGER_NEEDED hood_shop_keeper
	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 2
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 20 
	SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 20
	SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 20
	flag_player_on_menace_mission = 0
	//REMOVE_PRICE_MODIFIER afro haircuts
	//REMOVE_PRICE_MODIFIER afroblond haircuts
	//REMOVE_PRICE_MODIFIER afrotash  haircuts
	//REMOVE_PRICE_MODIFIER afrobeard haircuts
	//REMOVE_PRICE_MODIFIER flattop haircuts

	//REMOVE_PRICE_MODIFIER pizzalow food
	//REMOVE_PRICE_MODIFIER pizzamed food
	//REMOVE_PRICE_MODIFIER pizzahigh food

	//ACTIVATE_INTERIORS TRUE
	MISSION_HAS_FINISHED

RETURN


get_back_in_the_car_intro2:
	

	CLEAR_MISSION_AUDIO 2
	IF get_in_counter_intro2 = 0
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AA	//Hop in, CJ.
	ENDIF

	IF get_in_counter_intro2 = 1
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AB	//Jump in.
	ENDIF

	IF get_in_counter_intro2 = 2
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AC	//Get in fool!
	ENDIF

	IF get_in_counter_intro2 = 3			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AD	//All aboard, CJ.
	ENDIF

	IF get_in_counter_intro2 = 4			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AE //Guess you drivin, huh?
	ENDIF

	IF get_in_counter_intro2 = 5			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AF //C'mon CJ, earn your keep!
	ENDIF

	IF get_in_counter_intro2 = 6			
		LOAD_MISSION_AUDIO 2 SOUND_RYDX_AG //I'm tripping man, you drive.
	ENDIF

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
	STOP_CHAR_FACIAL_TALK scplayer

	CLEAR_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_LOADED 2
		WAIT 0

		IF IS_CAR_DEAD ryder_car
			//PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
			//intro2_failed_flag = 1
			RETURN
		ENDIF

		IF IS_CHAR_DEAD ryder
			//PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
			//intro2_failed_flag = 1
			RETURN
		ENDIF

	ENDWHILE

	PLAY_MISSION_AUDIO 2 
	  	
	IF get_in_counter_intro2 = 0
		PRINT_NOW ( RYDX_AA ) 3000 1 //Hop in, CJ.	
	ENDIF
	IF get_in_counter_intro2 = 1
		PRINT_NOW ( RYDX_AB ) 3000 1 //Jump in.
	ENDIF
	IF get_in_counter_intro2 = 2
		PRINT_NOW ( RYDX_AC ) 3000 1 //Get in fool!
	ENDIF
	IF get_in_counter_intro2 = 3
		PRINT_NOW ( RYDX_AD ) 3000 1 //All aboard, CJ.
	ENDIF
	IF get_in_counter_intro2 = 4
		PRINT_NOW ( RYDX_AE ) 3000 1 //Guess you drivin, huh?
	ENDIF
	IF get_in_counter_intro2 = 5
		PRINT_NOW ( RYDX_AF ) 3000 1 //C'mon CJ, earn your keep!
	ENDIF
	IF get_in_counter_intro2 = 6
		PRINT_NOW ( RYDX_AG ) 3000 1 //I'm tripping man, you drive.
	ENDIF

	WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
		WAIT 0

		IF IS_CAR_DEAD ryder_car
			//PRINT_NOW ( INT2_11 ) 10000 1 //You destroyed ryders car
			//intro2_failed_flag = 1
			RETURN
		ENDIF

		IF IS_CHAR_DEAD ryder
			//PRINT_NOW ( INT2_12 ) 10000 1 //Ryder is dead!
			//intro2_failed_flag = 1
			RETURN
		ENDIF

	ENDWHILE

	get_in_counter_intro2 ++

	IF get_in_counter_intro2 > 6
		get_in_counter_intro2 = 0
	ENDIF

RETURN


smoke_a_blunt:

	IF NOT IS_CHAR_DEAD ryder
		IF IS_CHAR_PLAYING_ANIM ryder SMOKE_RYD
			GET_CHAR_ANIM_CURRENT_TIME ryder SMOKE_RYD return_animation_food_name
			
			IF flag_vomit_playing = 0
				IF return_animation_food_name >= 0.785
					CREATE_OBJECT cigar_glow 2440.58 -1979.89 14.2 cigar_glow_object
					//TASK_PICK_UP_OBJECT ryder cigar_glow_object 0.04 0.1 -0.02 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL -1
					ATTACH_OBJECT_TO_OBJECT cigar_glow_object ryders_ciggy 0.0 0.0 0.0 0.0 0.0 0.0
					flag_vomit_playing = 1
				ENDIF
			ENDIF
			IF flag_vomit_playing = 1
				IF return_animation_food_name >= 0.15
				AND	return_animation_food_name < 0.335
				   	PLAY_FX_SYSTEM exhale_smoke_effect
					DELETE_OBJECT cigar_glow_object
					flag_vomit_playing = 2
				ENDIF
			ENDIF
			IF flag_vomit_playing = 2
				IF return_animation_food_name >= 0.335
				OR return_animation_food_name < 0.15
				   	STOP_FX_SYSTEM exhale_smoke_effect
					flag_vomit_playing = 0
				ENDIF
			ENDIF	

		ELSE
			STOP_FX_SYSTEM exhale_smoke_effect
		ENDIF
	ENDIF

RETURN



load_and_play_audio_intro2:

	IF intro2_audio_is_playing = 0
	OR intro2_audio_is_playing = 1
		IF intro2_index <= intro2_cell_index_end
			IF TIMERA > 200
				GOSUB play_intro2_audio
			ENDIF
		ENDIF
	ENDIF

	IF intro2_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			GOSUB stop_talking_intro2
			intro2_audio_is_playing = 0
			intro2_index ++
			intro2_cutscene_flag = 0
			CLEAR_PRINTS
			TIMERA = 0	
		ENDIF
	ENDIF

RETURN

play_intro2_audio:

	IF intro2_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 intro2_audio_chat[intro2_index]
		intro2_audio_is_playing = 1
	ENDIF
	IF intro2_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $intro2_chat[intro2_index] ) 4000 1 //Let's head over to B-Dup's crib.
			PLAY_MISSION_AUDIO 1
			GOSUB start_talking_intro2
			intro2_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN

start_talking_intro2:

	IF intro2_audio_chat[intro2_index] = SOUND_INT2_GB
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_GD
		IF NOT IS_CHAR_DEAD	hood_shop_keeper
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH hood_shop_keeper TRUE
			START_CHAR_FACIAL_TALK hood_shop_keeper 3000
			RETURN
		ENDIF   
	ENDIF
	  
	IF intro2_audio_chat[intro2_index] = SOUND_INT2_CA	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_CE	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_BB	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_BD	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_BF
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE						 
		START_CHAR_FACIAL_TALK scplayer 3000			 
		RETURN
	ENDIF

	IF intro2_audio_chat[intro2_index] = SOUND_INT2_BJ
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_GE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
		START_CHAR_FACIAL_TALK scplayer 3000
	ELSE
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder TRUE
			START_CHAR_FACIAL_TALK ryder 3000
		ENDIF
	ENDIF

RETURN


stop_talking_intro2:

	IF intro2_audio_chat[intro2_index] = SOUND_INT2_GB
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_GD
		IF NOT IS_CHAR_DEAD	hood_shop_keeper
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH hood_shop_keeper FALSE
			STOP_CHAR_FACIAL_TALK hood_shop_keeper
			RETURN
		ENDIF   
	ENDIF
	  
	IF intro2_audio_chat[intro2_index] = SOUND_INT2_CA	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_CE	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_BB	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_BD	 
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_BF
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE			 
		STOP_CHAR_FACIAL_TALK scplayer			 
		RETURN
	ENDIF

	IF intro2_audio_chat[intro2_index] = SOUND_INT2_BJ
	OR intro2_audio_chat[intro2_index] = SOUND_INT2_GE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		STOP_CHAR_FACIAL_TALK scplayer
	ELSE
		IF NOT IS_CHAR_DEAD	ryder
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder FALSE
			STOP_CHAR_FACIAL_TALK ryder
		ENDIF
	ENDIF

RETURN


}