MISSION_START
// *****************************************************************************************
// ***********************************    2p mission            **************************** 
// ***********************************  heist 3  / girlfriend   ****************************
// *****************************************************************************************
// ***                                                                                   ***
// *****************************************************************************************

SCRIPT_NAME heist3
// Mission start stuff

GOSUB mission_start_heist3

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_heist3_failed
ENDIF

GOSUB mission_cleanup_heist3

MISSION_END
{
////////////////////////////
// Variables for mission ///
////////////////////////////


/// others

LVAR_INT hei3_task_status
LVAR_INT hei3_temp_int
LVAR_INT hei3_distance_int
LVAR_INT hei3_timer_start hei3_time_passed hei3_current_time
LVAR_INT hei3_iGirlFriend hei3_iSpankingFlag
LVAR_INT hei3_decision hie3_dummy
LVAR_FLOAT hei3_player_speed

LVAR_INT  hei3_area_vis

//LVAR_INT hei3_spankage_rating
VAR_INT hei3_spook



LVAR_FLOAT hei3_distance_float

/// Flags
LVAR_INT hei3_progress_flag
LVAR_INT hei3_safety_flag
LVAR_INT hei3_x_button_can_be_pressed
LVAR_INT hei3_player_has_gimp_suit
LVAR_INT hei3_spooked_check
LVAR_INT hei3_flag_player_had_warning1_fm2
LVAR_INT hei3_clothes_changed
LVAR_INT hei3_pleased_millie
LVAR_INT hei3_millie_at_house

/// People
LVAR_INT hei3_millie_ped
LVAR_INT hei3_client_ped
LVAR_INT hei3_doorman_ped
LVAR_INT hei3_shop_ped1
LVAR_INT hei3_shop_ped2
LVAR_INT hei3_shop_ped3
LVAR_INT hei3_shop_ped4
LVAR_INT hei3_shop_ped5

/// Objects


/// Pickups
LVAR_INT hei3_gimp_suit_pickup


/// Vehicles
LVAR_INT hei3_millie_car
LVAR_INT hei3_client_car
LVAR_INT hei3_temp_car

/// Blips
LVAR_INT hei3_millie_blip
LVAR_INT hei3_client_blip
LVAR_INT hei3_sex_shop_blip
LVAR_INT hei3_house_blip
LVAR_INT hei3_casino_blip
LVAR_INT hei3_gimp_suit_blip

/// Sequences
LVAR_INT hei3_player_seq
LVAR_INT hei3_millie_seq
LVAR_INT hei3_client_seq


/// Co-ords
LVAR_FLOAT hei3_sex_shop_x hei3_sex_shop_y hei3_sex_shop_z
LVAR_FLOAT hei3_player_x hei3_player_y hei3_player_z  
LVAR_FLOAT hei3_millie_x hei3_millie_y hei3_millie_z 
LVAR_FLOAT hei3_house_x hei3_house_y hei3_house_z


//// Audio crap
///// AUDIO CRAP
VAR_TEXT_LABEL hei3_text[44]
LVAR_INT hei3_audio[44]
LVAR_INT hei3_audio_counter
LVAR_INT hei3_audio_slot1 hei3_audio_slot2 hei3_audio_playing
LVAR_INT hei3_text_loop_done
LVAR_INT hei3_mobile
LVAR_INT hei3_text_timer_diff 
LVAR_INT hei3_text_timer_end 
LVAR_INT hei3_text_timer_start
LVAR_INT hei3_ahead_counter


LVAR_INT hei3_sfx[5]
LVAR_INT hei3_sfx_counter 
LVAR_INT hei3_sfx_playing
LVAR_INT hei3_sfx_played

// ****************************************START OF CUTSCENE********************************




// ****************************************END OF CUTSCENE**********************************

 

// ****************************************Mission Start************************************

mission_start_heist3:

REGISTER_MISSION_GIVEN
SET_FADING_COLOUR 0 0 0
flag_player_on_mission = 1


DO_FADE 2000 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE 

IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
	//GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1  fghjk fghjk  CLOTHES_TEX_EXTRA1//  	2	
	GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
	BUILD_PLAYER_MODEL player1
ENDIF

STORE_CLOTHES_STATE

FORCE_WEATHER WEATHER_SUNNY_VEGAS
SET_AREA_VISIBLE 10
LOAD_MISSION_TEXT HEIST3

LOAD_CUTSCENE HEIST2A
 
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

SET_PLAYER_CONTROL player1 OFF
DO_FADE 1000 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE

RELEASE_WEATHER
LOAD_SCENE 2030.1027 1020.7899 9.8203

WAIT 0



//////////////////////////////
/// REQUEST MISSION MODELS ///
//////////////////////////////


REQUEST_COLLISION 2030.1027 1020.7899
LOAD_SCENE 2030.1027 1020.7899 9.8203
SET_CHAR_COORDINATES scplayer 2030.1027 1020.7899 9.8130 
SET_CHAR_HEADING scplayer 277.5123
SET_AREA_VISIBLE 0
SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO






LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY hei3_decision

////////////////////////////////
/// INITIALISE THE VARIABLES ///
////////////////////////////////
hei3_progress_flag 					= 0
hei3_safety_flag					= 0
hei3_x_button_can_be_pressed 		= 0
hei3_player_has_gimp_suit 			= 0
hei3_spooked_check					= 0
hei3_flag_player_had_warning1_fm2 	= 0
hei3_clothes_changed				= 0
hei3_pleased_millie					= 0
hei3_millie_at_house				= 0
 
hei3_player_speed					= 0.0 
hei3_distance_float					= 0.0
hei3_distance_int 					= 0
hei3_spook							= 0
//i3_spankage_rating				= 0

hei3_timer_start 					= 0
hei3_time_passed 					= 0
hei3_current_time					= 0



hei3_sex_shop_x 					= 2090.0
hei3_sex_shop_y 					= 2074.0 
hei3_sex_shop_z						= 11.0

hei3_house_x 						= 2036.9286  
hei3_house_y 						= 2721.4490 
hei3_house_z						= 10.5469




hei3_audio_counter	= 0
hei3_audio_slot1 	   = 1
hei3_audio_slot2 	   = 2
hei3_audio_playing	   = 0
hei3_text_loop_done	   = 0
hei3_mobile			   = 0
hei3_text_timer_diff   = 0
hei3_text_timer_end    = 0
hei3_text_timer_start  = 0
hei3_ahead_counter	   = 0


$hei3_text[1] = HE3_AA//There's only ever been one weak link in any security set-up, the human heart.
$hei3_text[2] = HE3_BA//Another shift over! See you tomorrow!
$hei3_text[3] = HE3_BB//That was a long shift! See you tomorrow!
$hei3_text[4] = HE3_CA//Wow, it's nice and tight!
$hei3_text[5] = HE3_CB//I'm sure you'll squeeze in just fine!
$hei3_text[6] = HE3_CC//Let's have a look.
$hei3_text[7] = HE3_CD//Perfect!
$hei3_text[8] = HE3_CE//Oh hi, Benny...
$hei3_text[9] = HE3_CF//Yes, master, I'm just trying it on.
$hei3_text[10] = HE3_CG//You got yours?
$hei3_text[11] = HE3_CH//Cool, I'll see you at my house in a short while.
$hei3_text[12] = HE3_DA//The door's open, Master!
$hei3_text[13] = HE3_DB//Come on in, I'm ready for you!
$hei3_text[14] = HE3_DC//You've been a naughty girl!
$hei3_text[15] = HE3_DD//Oh I know! I know!
$hei3_text[16] = HE3_DE//I want your security card and the entry code for Caligula's!
$hei3_text[17] = HE3_DF//Oh Benny, you minx!
$hei3_text[18] = HE3_DG//I'll never tell, you wicked man, never, NEVER!
$hei3_text[19] = HE3_EA//Where's the keycard?
$hei3_text[20] = HE3_EB//Confess!
$hei3_text[21] = HE3_EC//You can't break me!
$hei3_text[22] = HE3_FA//Enough, master!
$hei3_text[23] = HE3_FB//The card's in my car outside!
$hei3_text[24] = HE3_FC//And now, the code...
$hei3_text[25] = HE3_FD//Spit it out, you filthy worm!
$hei3_text[26] = HE3_FE//If you're good, I'll punish you more!
$hei3_text[27] = HE3_FF//I can take it!
$hei3_text[28] = HE3_FG//Do your worst!
$hei3_text[29] = HE3_GA//No more! You've broken me!
$hei3_text[30] = HE3_GB//The code, you worthless trash!
$hei3_text[31] = HE3_GC//God, you're good at this!
$hei3_text[32] = HE3_GD//I know you're not Benny! 
$hei3_text[33] = HE3_GE//Give me your number I have to see you again.
	
$hei3_text[34] = HE3_HA//ENTRY CODE!
$hei3_text[35] = HE3_HB//Oh! It's...
$hei3_text[36] = HE3_HC//A.
$hei3_text[37] = HE3_HD//B.
$hei3_text[38] = HE3_HE//C.
$hei3_text[39] = HE3_HF//D.
$hei3_text[40] = HE3_HG//E.
$hei3_text[41] = HE3_HH//F.
	
$hei3_text[42] = HE3_HJ//I'll call you, master, I promise!
$hei3_text[43] = HE3_AB//	any security set up.  The human heart.


///////////////////////////////// AUDIO

hei3_audio[1] = SOUND_HE3_AA//There's only ever been one weak link in any security set-up, the human heart.
hei3_audio[2] = SOUND_HE3_BA//Another shift over! See you tomorrow!
hei3_audio[3] = SOUND_HE3_BB//That was a long shift! See you tomorrow!
hei3_audio[4] = SOUND_HE3_CA//Wow, it's nice and tight!
hei3_audio[5] = SOUND_HE3_CB//I'm sure you'll squeeze in just fine!
hei3_audio[6] = SOUND_HE3_CC//Let's have a look.
hei3_audio[7] = SOUND_HE3_CD//Perfect!
hei3_audio[8] = SOUND_HE3_CE//Oh hi, Benny...
hei3_audio[9] = SOUND_HE3_CF//Yes, master, I'm just trying it on.
hei3_audio[10] = SOUND_HE3_CG//You got yours?
hei3_audio[11] = SOUND_HE3_CH//Cool, I'll see you at my house in a short while.
hei3_audio[12] = SOUND_HE3_DA//The door's open, Master!
hei3_audio[13] = SOUND_HE3_DB//Come on in, I'm ready for you!
hei3_audio[14] = SOUND_HE3_DC//You've been a naughty girl!
hei3_audio[15] = SOUND_HE3_DD//Oh I know! I know!
hei3_audio[16] = SOUND_HE3_DE//I want your security card and the entry code for Caligula's!
hei3_audio[17] = SOUND_HE3_DF//Oh Benny, you minx!
hei3_audio[18] = SOUND_HE3_DG//I'll never tell, you wicked man, never, NEVER!
hei3_audio[19] = SOUND_HE3_EA//Where's the keycard?
hei3_audio[20] = SOUND_HE3_EB//Confess!
hei3_audio[21] = SOUND_HE3_EC//You can't break me!
hei3_audio[22] = SOUND_HE3_FA//Enough, master!
hei3_audio[23] = SOUND_HE3_FB//The card's in my car outside!
hei3_audio[24] = SOUND_HE3_FC//And now, the code...
hei3_audio[25] = SOUND_HE3_FD//Spit it out, you filthy worm!
hei3_audio[26] = SOUND_HE3_FE//If you're good, I'll punish you more!
hei3_audio[27] = SOUND_HE3_FF//I can take it!
hei3_audio[28] = SOUND_HE3_FG//Do your worst!
hei3_audio[29] = SOUND_HE3_GA//No more! You've broken me!
hei3_audio[30] = SOUND_HE3_GB//The code, you worthless trash!
hei3_audio[31] = SOUND_HE3_GC//God, you're good at this!
hei3_audio[32] = SOUND_HE3_GD//I know you're not Benny! 
hei3_audio[33] = SOUND_HE3_GE//Give me your number I have to see you again.

hei3_audio[34] = SOUND_HE3_HA//ENTRY CODE!
hei3_audio[35] = SOUND_HE3_HB//Oh! It's...
hei3_audio[36] = SOUND_HE3_HC//A.
hei3_audio[37] = SOUND_HE3_HD//B.
hei3_audio[38] = SOUND_HE3_HE//C.
hei3_audio[39] = SOUND_HE3_HF//D.
hei3_audio[40] = SOUND_HE3_HG//E.
hei3_audio[41] = SOUND_HE3_HH//F.
hei3_audio[42] = SOUND_HE3_HJ//I'll call you, master, I promise!

hei3_audio[43] = SOUND_HE3_AB//	any security set up.  The human heart.


hei3_sfx[1]			  = SOUND_GIMP_SUIT
hei3_sfx[2]			  = SOUND_DOORBELL
hei3_sfx[3]			  = SOUND_DRESSING
hei3_sfx[4]			  = SOUND_PED_MOBRING

hei3_sfx_counter 	  = 0
hei3_sfx_playing	  = 0
hei3_sfx_played 	  = 0




//////////////////////////////
/// PLAYER INTRO CUT-SCENE ///
//////////////////////////////



ADD_BLIP_FOR_COORD 2169.0 1662.0 11.0 hei3_casino_blip



//GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1  gimpleg 	gimpleg CLOTHES_TEX_EXTRA1//  	2
//IF IS_PLAYER_WEARING	player1	0	gimp
//OR IS_PLAYER_WEARING	player1	1	gimpmask
//OR IS_PLAYER_WEARING	player1	2	gimpleg
//OR IS_PLAYER_WEARING	player1	3	gimpshoe
//OR IS_PLAYER_WEARING	player1	4	gimp

SET_CAMERA_BEHIND_PLAYER
SWITCH_WIDESCREEN ON


LOAD_SCENE 2030.1027 1020.7899 9.8203
DO_FADE 1000 FADE_IN
WHILE GET_FADING_STATUS
WAIT 0
ENDWHILE

SKIP_CUTSCENE_START

		   hei3_text_loop1:
		   WAIT 0 		
			IF hei3_text_loop_done	   = 0
				IF NOT hei3_audio_counter = 0
					IF hei3_audio_playing = 0
						IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
							CLEAR_MISSION_AUDIO hei3_audio_slot2
						ENDIF
						hei3_audio_playing = 1
					ENDIF

					IF hei3_audio_playing = 1
						LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
						hei3_audio_playing = 2
					ENDIF

					IF hei3_audio_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
							PLAY_MISSION_AUDIO hei3_audio_slot1
							PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
							hei3_audio_playing = 3
						ENDIF
					ENDIF

					IF hei3_audio_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
							CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
							IF hei3_audio_slot1 = 1
								hei3_audio_slot1 = 2
								hei3_audio_slot2 = 1
							ELSE
								hei3_audio_slot1 = 1
								hei3_audio_slot2 = 2
							ENDIF
							hei3_audio_counter = 0
							hei3_audio_playing = 0							
						ENDIF
					ENDIF
				ENDIF

				SWITCH hei3_mobile
					CASE 0
						IF hei3_audio_playing = 0
					        hei3_audio_counter = 1	//PRINT (HE3_AA) 5000 1 //There's only ever been one weak link in any security set-up - the human heart. 
							hei3_mobile = 1
							GET_GAME_TIMER hei3_text_timer_start
						ENDIF
						BREAK
					CASE 1
						GET_GAME_TIMER hei3_text_timer_end
						hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
						IF hei3_text_timer_diff > 1000
							IF hei3_audio_playing = 0
								hei3_audio_counter = 43// HE3_AB//	any security set up.  The human heart.
								hei3_mobile = 2
								GET_GAME_TIMER hei3_text_timer_start
							ENDIF
						ENDIF	
						BREAK																	
					DEFAULT
						GET_GAME_TIMER hei3_text_timer_end
						hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
						IF hei3_text_timer_diff > 1000
							IF hei3_audio_playing = 0
							   hei3_text_loop_done = 1	
							ENDIF
						ENDIF
						BREAK
				ENDSWITCH			   								
			
			GOTO hei3_text_loop1
			ENDIF



SKIP_CUTSCENE_END

IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
CLEAR_MISSION_AUDIO hei3_audio_slot1
ENDIF

CLEAR_PRINTS
SET_PLAYER_CONTROL Player1 ON
SWITCH_WIDESCREEN OFF
PRINT (HEI3_33) 5000 1 //~s~Get to the ~y~casino~s~.





///////////\\\\\\\\\\\\\\
/// MAIN MISSION LOOP \\\
///////////\\\\\\\\\\\\\\

heist3_main_loop:

WAIT 0

///////////////////
/// DEBUG STUFF ///
///////////////////

//VIEW_INTEGER_VARIABLE hei3_spankage_rating spank

IF hei3_safety_flag = 99999
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 hei3_millie_blip
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 hei3_house_blip
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 hei3_client_blip
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 hei3_sex_shop_blip
	CREATE_CHAR PEDTYPE_MISSION1 CROGRL3 2191.0 1679.0 11.0 hei3_millie_ped	
	CREATE_CAR CLUB 2175.1809 1695.5220 9.8203 hei3_millie_car
	CREATE_CHAR PEDTYPE_MISSION1 CROGRL3 2191.0 1679.0 11.0 hei3_client_ped
	CREATE_CAR FELTZER 2175.1809 1695.5220 9.8203 hei3_client_car
	CREATE_CHAR PEDTYPE_MISSION1 CROGRL3 2191.0 1679.0 11.0 hei3_doorman_ped
ENDIF


////////////////////////////////
/////////////////////////////////
/////////////////////////////////



IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
SET_BIT iActiveGF MILLIE
GOTO mission_heist3_passed
ENDIF


//////
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
	WHILE NOT HAS_MODEL_LOADED WINDSOR
	OR NOT HAS_MODEL_LOADED CROGRL3
	OR NOT HAS_MODEL_LOADED CLUB
	REQUEST_MODEL CLUB
	REQUEST_MODEL CROGRL3
	REQUEST_MODEL WINDSOR
	GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1  gimpleg 	gimpleg CLOTHES_TEX_EXTRA1//  	2
	BUILD_PLAYER_MODEL player1
	WAIT 0 
	ENDWHILE
	hei3_progress_flag = 5
ENDIF
	/////

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
SET_CHAR_COORDINATES scplayer 2152.7744 1653.3513 10.0469
ENDIF

///////////////////////////////////////
///////////////////////////////////////
///////////////////////////////////////

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_9
	REQUEST_MODEL GUN_DILDO1
	WHILE NOT HAS_MODEL_LOADED GUN_DILDO1
	WAIT 0 
	ENDWHILE
	REMOVE_BLIP hei3_casino_blip
	REMOVE_BLIP hei3_client_blip
	REMOVE_BLIP hei3_millie_blip
	DELETE_CHAR hei3_client_ped
	DELETE_CAR hei3_client_car
	DELETE_CHAR hei3_millie_ped
	DELETE_CAR hei3_millie_car
	SET_CHAR_COORDINATES scplayer hei3_house_x hei3_house_y hei3_house_z
	REMOVE_BLIP hei3_house_blip
	ADD_BLIP_FOR_COORD hei3_house_x hei3_house_y hei3_house_z hei3_house_blip
	hei3_progress_flag = 9
ENDIF






////////////////////////
/// ON WAY TO CASINO ///
////////////////////////

IF hei3_progress_flag = 0
	IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D scplayer 2169.0 1662.0 11.0 4.0 4.0 4.0 TRUE
		REMOVE_BLIP hei3_casino_blip
		SET_PLAYER_CONTROL Player1 OFF
		DO_FADE 1500 FADE_OUT
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
					   
		SWITCH_PED_ROADS_OFF 2096.3352 1656.3969 8.0 2161.6587 1728.2869 10.0
		SWITCH_ROADS_OFF 2096.3352 1656.3969 8.0 2161.6587 1728.2869 10.0
		
		

		REQUEST_MODEL CROGRL3
		REQUEST_MODEL CLUB
		REQUEST_MODEL MAFFA
		
		WHILE NOT HAS_MODEL_LOADED CROGRL3
		OR NOT HAS_MODEL_LOADED MAFFA
		OR NOT HAS_MODEL_LOADED CLUB		
		WAIT 0 
		ENDWHILE

		CLEAR_AREA 	2175.1809 1695.5220 9.8203 30.0 TRUE
		

		CUSTOM_PLATE_FOR_NEXT_CAR CLUB &_SPANK__
		CREATE_CAR CLUB 2175.1809 1695.5220 9.8203 hei3_millie_car		
		CHANGE_CAR_COLOUR hei3_millie_car 126 126		
		SET_CAN_BURST_CAR_TYRES hei3_millie_car FALSE
		LOCK_CAR_DOORS hei3_millie_car CARLOCK_LOCKOUT_PLAYER_ONLY
		SET_CAR_HEADING  hei3_millie_car 66.1016

		CREATE_CHAR PEDTYPE_MISSION1 CROGRL3 2191.0 1679.0 11.0 hei3_millie_ped
		SET_CHAR_DECISION_MAKER hei3_millie_ped hei3_decision
		SET_ANIM_GROUP_FOR_CHAR hei3_millie_ped sexywoman
		ADD_BLIP_FOR_CHAR hei3_millie_ped hei3_millie_blip
		SET_CHAR_HEADING hei3_millie_ped 76.0

		CREATE_CHAR PEDTYPE_MISSION2 MAFFA 2188.10 1686.4 10.0 hei3_doorman_ped
		SET_CHAR_HEADING hei3_doorman_ped  78.0		
		SET_CHAR_DECISION_MAKER hei3_doorman_ped hei3_decision
		//ADD_BLIP_FOR_COORD hei3_sex_shop_x hei3_sex_shop_y hei3_sex_shop_z hei3_sex_shop_blip

		//SET_CHAR_COORDINATES scplayer 2176.4304 1681.5559 9.8203
		//SET_CHAR_HEADING scplayer 360.0

		
		SWITCH_WIDESCREEN ON
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer hei3_temp_car
			SET_CAR_COORDINATES hei3_temp_car 2174.0 1662.0 9.8203
			SET_CAR_HEADING hei3_temp_car 27.0
		ELSE
			SET_CHAR_COORDINATES scplayer 2174.0 1662.0 9.8203
			SET_CHAR_HEADING scplayer 27.0
		ENDIF
		SET_FIXED_CAMERA_POSITION 2191.40 1677.08 12.48 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2182.17 1688.45 8.05 JUMP_CUT

		

		hei3_progress_flag = 1
	ENDIF
ENDIF






IF hei3_progress_flag = 1

	IF NOT IS_CHAR_DEAD hei3_millie_ped			
		IF NOT IS_CAR_DEAD hei3_millie_car
			FLUSH_ROUTE			
			  
			
			//EXTEND_ROUTE 2092.3193 1720.8373 9.6859  
			//EXTEND_ROUTE 2092.3979 1746.2247 9.6875

			//EXTEND_ROUTE 2150.7217 1864.7489 9.6719  
			EXTEND_ROUTE 2336.2190 1891.0903 9.7792  
			EXTEND_ROUTE 2355.2810 2114.8167 9.6719  
			EXTEND_ROUTE 2171.7456 2149.1462 9.6794
			
			EXTEND_ROUTE 2123.5640 2134.4167 9.6797  
			

			OPEN_SEQUENCE_TASK hei3_millie_seq
			TASK_GO_STRAIGHT_TO_COORD -1 2175.0925 1692.7054 9.8203 PEDMOVE_WALK 30000
			SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
			TASK_ENTER_CAR_AS_DRIVER -1 hei3_millie_car	20000	
			TASK_DRIVE_POINT_ROUTE_ADVANCED -1 hei3_millie_car 20.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS		
					
			TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2130.5356 2061.7173 9.6875 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS 
			TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2116.2637 2044.5940 9.8125 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
			TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2101.0 2040.0 9.8203 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
			TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2096.0 2073.0 9.8203 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
			CLOSE_SEQUENCE_TASK hei3_millie_seq
			PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
			CLEAR_SEQUENCE_TASK hei3_millie_seq
			
			
			DO_FADE 1500 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			
			CLEAR_PRINTS
			PRINT (HEI3_01) 7000 1 //~s~The ~r~Croupier ~s~has a keycard and knows the code, follow her.
			
			//PRINT (HEI3_BB) 5000 1 //CROUPIER:"That was a long shift - see you tomorrow!"

			hei3_audio_counter	= 0
			hei3_audio_slot1 	   = 1
			hei3_audio_slot2 	   = 2
			hei3_audio_playing	   = 0
			hei3_text_loop_done	   = 0
			hei3_mobile			   = 0
			hei3_text_timer_diff   = 0
			hei3_text_timer_end    = 0
			hei3_text_timer_start  = 0
			hei3_ahead_counter	   = 0

			hei3_safety_flag = 0
			hei3_temp_int = 0 
			TIMERA = 0
			WHILE hei3_safety_flag = 0
				
			
			//////////////////////////////////////// audio ////////////////
			IF hei3_text_loop_done	   = 0
				IF NOT hei3_audio_counter = 0
					IF hei3_audio_playing = 0
						IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
							CLEAR_MISSION_AUDIO hei3_audio_slot2
						ENDIF
						hei3_audio_playing = 1
					ENDIF

					IF hei3_audio_playing = 1
						LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
						hei3_audio_playing = 2
					ENDIF

					IF hei3_audio_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
							PLAY_MISSION_AUDIO hei3_audio_slot1
							PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
							hei3_audio_playing = 3
						ENDIF
					ENDIF

					IF hei3_audio_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
							IF NOT IS_CHAR_DEAD	hei3_millie_ped
							STOP_CHAR_FACIAL_TALK hei3_millie_ped
						 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
							ENDIF
							CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
							IF hei3_audio_slot1 = 1
								hei3_audio_slot1 = 2
								hei3_audio_slot2 = 1
							ELSE
								hei3_audio_slot1 = 1
								hei3_audio_slot2 = 2
							ENDIF
							hei3_audio_counter = 0
							hei3_audio_playing = 0
							hei3_text_loop_done	   = 1																
						ENDIF
					ENDIF
				ENDIF
				
				IF NOT IS_MESSAGE_BEING_DISPLAYED
					IF hei3_audio_playing = 0
						IF NOT IS_CHAR_DEAD	hei3_millie_ped
						STOP_CHAR_FACIAL_TALK hei3_millie_ped
					 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
						ENDIF
					hei3_audio_counter = 2	//PRINT (HE3_BA) 5000 1 //CROUPIER:"Another shift over! See you tomorrow!"
					ENDIF
				ENDIF
			ENDIF
			

			
			
			
				
			/////////// if cut-scene skipped ///////////
			IF IS_BUTTON_PRESSED PAD1 CROSS			
				hei3_safety_flag = 1

				IF NOT IS_CHAR_DEAD hei3_millie_ped
				AND NOT IS_CAR_DEAD hei3_millie_car

					IF NOT IS_CHAR_IN_CAR hei3_millie_ped hei3_millie_car
						WARP_CHAR_INTO_CAR hei3_millie_ped hei3_millie_car
					ENDIF
					CLOSE_ALL_CAR_DOORS hei3_millie_car
					FLUSH_ROUTE						  							

					//EXTEND_ROUTE 2150.7217 1864.7489 9.6719  
					EXTEND_ROUTE 2336.2190 1891.0903 9.7792  
					EXTEND_ROUTE 2355.2810 2114.8167 9.6719  
					EXTEND_ROUTE 2171.7456 2149.1462 9.6794
					EXTEND_ROUTE 2128.5640 2134.4167 9.6797  

					OPEN_SEQUENCE_TASK hei3_millie_seq					
					TASK_DRIVE_POINT_ROUTE_ADVANCED -1 hei3_millie_car 20.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS																		  
					TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2130.5356 2061.7173 9.6875 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS 
					TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2116.2637 2044.5940 9.8125 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
					TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2101.0 2040.0 9.8203 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
					TASK_CAR_DRIVE_TO_COORD -1 hei3_millie_car 2096.0 2073.0 9.8203 20.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS
					CLOSE_SEQUENCE_TASK hei3_millie_seq
					PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
					CLEAR_SEQUENCE_TASK hei3_millie_seq
					CLEAR_AREA 	2175.1809 1695.5220 9.8203 30.0 TRUE
				ENDIF
			ENDIF
			///////////////////////////////////////////////////
				
				
				IF NOT IS_CHAR_DEAD hei3_millie_ped
				AND NOT IS_CAR_DEAD hei3_millie_car
					GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
					IF  hei3_task_status  = PERFORMING_TASK
						GET_SEQUENCE_PROGRESS hei3_millie_ped hei3_task_status
						IF LOCATE_CHAR_ANY_MEANS_CAR_3D hei3_millie_ped hei3_millie_car 7.0 7.0 5.0 FALSE
							IF hei3_temp_int = 0
								PRINT (HEI3_02) 5000 1 //~s~If you get too close you'll spook her, but she gets too far ahead you'll lose her.							
								SET_FIXED_CAMERA_POSITION 2182.17 1694.54 11.72 0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 2108.69 1674.08 -4.60 JUMP_CUT
								IF NOT IS_CHAR_DEAD hei3_doorman_ped
									CLEAR_CHAR_TASKS hei3_doorman_ped
									TASK_GO_STRAIGHT_TO_COORD hei3_doorman_ped 2196.62 1684.18 12.41 PEDMOVE_WALK 30000
								ENDIF
								hei3_temp_int = 1
							ENDIF
						ENDIF
						IF hei3_task_status = 2
							WAIT 1000
							CLEAR_AREA 	2175.1809 1695.5220 9.8203 30.0 TRUE
							IF NOT IS_CAR_DEAD hei3_millie_car
							CLOSE_ALL_CAR_DOORS hei3_millie_car
							ENDIF
							hei3_safety_flag = 1
						ENDIF
					ENDIF
				ELSE
					hei3_safety_flag = 1
				ENDIF

			WAIT 0
			ENDWHILE

			CLEAR_PRINTS
			MARK_CHAR_AS_NO_LONGER_NEEDED hei3_doorman_ped
			REMOVE_CHAR_ELEGANTLY hei3_doorman_ped			
			MARK_MODEL_AS_NO_LONGER_NEEDED MAFFA
									
			////////////////////////////////////////////
			MARK_CAR_AS_NO_LONGER_NEEDED hei3_temp_car
			RESTORE_CAMERA_JUMPCUT			
			SET_PLAYER_CONTROL Player1 ON
			SWITCH_WIDESCREEN OFF
			
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING hei3_spook COUNTER_DISPLAY_BAR HEI3_94
			TIMERB = 0
			TIMERA = 0
			hei3_progress_flag = 2
		ENDIF
	ENDIF

 

ENDIF




////////////////////////////////////
/// FOLLOWING MILLIE TO S&M SHOP ///
////////////////////////////////////
IF hei3_progress_flag = 2
	IF NOT IS_CHAR_DEAD hei3_millie_ped
		IF NOT IS_CAR_DEAD hei3_millie_car
			GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
			IF  hei3_task_status  = FINISHED_TASK
				// cut-scene of millie entering sex shop //
				CLEAR_AREA 2096.0 2073.0 9.8203 20.0 TRUE
				CLEAR_ONSCREEN_COUNTER hei3_spook
				
				GOSUB check_player_is_safe

            	IF player_is_completely_safe = 1

					SET_PLAYER_CONTROL Player1 OFF
					SWITCH_WIDESCREEN ON
					CLEAR_PRINTS
					
					
					SET_CAR_COORDINATES hei3_millie_car 2095.9131 2074.0813 9.8203
					SET_CAR_HEADING hei3_millie_car 4.4441
					SET_FIXED_CAMERA_POSITION 2098.45 2081.99 9.88 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2056.37 2043.26 26.30 JUMP_CUT
					
					SKIP_CUTSCENE_START
					
					IF IS_CHAR_IN_ANY_CAR scplayer
						IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2088.0 2076.0 11.0 5.0 5.0 3.0  FALSE
							SET_CHAR_COORDINATES scplayer 2092.0 2063.0 11.0
							SET_CHAR_HEADING scplayer 0.0
						ENDIF				
					ENDIF
					OPEN_SEQUENCE_TASK hei3_millie_seq				
					IF IS_CHAR_IN_ANY_CAR hei3_millie_ped
						TASK_LEAVE_ANY_CAR -1
					ENDIF
					TASK_GO_STRAIGHT_TO_COORD -1 2085.7207 2074.7605 10.0579 PEDMOVE_WALK -1
					CLOSE_SEQUENCE_TASK hei3_millie_seq
					PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
					CLEAR_SEQUENCE_TASK hei3_millie_seq
					
					hei3_safety_flag = 0 
					TIMERA = 0
					WHILE hei3_safety_flag = 0
						IF NOT IS_CHAR_DEAD hei3_millie_ped
							GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
							IF  hei3_task_status  = FINISHED_TASK
								hei3_safety_flag = 1
							ENDIF
						ELSE
							hei3_safety_flag = 1
						ENDIF

					WAIT 0
					ENDWHILE 

					SKIP_CUTSCENE_END
				ENDIF

				IF NOT IS_CAR_DEAD hei3_millie_car
					SET_CAR_COORDINATES hei3_millie_car 2095.9131 2074.0813 9.8203
					SET_CAR_HEADING hei3_millie_car 4.4441
					CLOSE_ALL_CAR_DOORS hei3_millie_car
				ENDIF
				RESTORE_CAMERA_JUMPCUT
				
				
				
				REMOVE_BLIP hei3_millie_blip
				ADD_BLIP_FOR_COORD hei3_sex_shop_x hei3_sex_shop_y hei3_sex_shop_z hei3_sex_shop_blip
				DELETE_CHAR hei3_millie_ped
				MARK_CHAR_AS_NO_LONGER_NEEDED hei3_millie_ped
			
				
				SET_PLAYER_CONTROL Player1 ON
				SWITCH_WIDESCREEN OFF
				
				
				

				
				PRINT (HEI3_03) 5000 1 //~s~She's gone into the sex ~y~shop~s~, follow her inside.
				hei3_temp_int = 0
				hei3_progress_flag = 3
			ELSE
				
				///////////////////////////////
				/// spooked check code here ///
				///////////////////////////////
				
				
				GET_CHAR_COORDINATES scplayer hei3_player_x hei3_player_y hei3_player_z
				GET_CHAR_COORDINATES hei3_millie_ped hei3_millie_x hei3_millie_y hei3_millie_z
				GET_DISTANCE_BETWEEN_COORDS_3D hei3_player_x hei3_player_y hei3_player_z hei3_millie_x hei3_millie_y hei3_millie_z hei3_distance_float
				
				 
				
				hei3_distance_int =# hei3_distance_float				
				
				
				IF  hei3_task_status  = PERFORMING_TASK
					GET_SEQUENCE_PROGRESS hei3_millie_ped hei3_task_status

					IF hei3_task_status < 5
						IF hei3_spooked_check = 1
							
							IF hei3_distance_int > 140
								CLEAR_PRINTS
								IF hei3_distance_int > 168 
									// FAIL
									IF NOT IS_CHAR_ON_SCREEN hei3_millie_ped
										IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
											DO_FADE 1000 FADE_OUT
											WHILE GET_FADING_STATUS
											    WAIT 0
											ENDWHILE 	
												GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
												BUILD_PLAYER_MODEL player1

											DO_FADE 1000 FADE_IN
											WHILE GET_FADING_STATUS
											    WAIT 0
											ENDWHILE
										ENDIF
										PRINT (HEI3_16) 5000 1 //~r~You lost her!
										GOTO mission_heist3_failed
									ELSE
										PRINT (HEI3_10) 1000 1 //~s~The ~r~Croupier~s~ is almost out of sight, you need to get closer!
									ENDIF
								ELSE						
									PRINT (HEI3_10) 1000 1 //~s~The ~r~Croupier~s~ is almost out of sight, you need to get closer!
								ENDIF																					
							ENDIF
							
							
							
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hei3_millie_ped 22.5 22.5 20.0 FALSE					   
															
								IF TIMERA > 64
									++ hei3_spook
									TIMERA = 0
						   		ENDIF							

							ELSE															
								IF TIMERA > 150
									IF hei3_spook > 0
										-- hei3_spook
									ENDIF
									TIMERA = 0
								ENDIF																
							ENDIF
							
						ELSE
							IF hei3_spooked_check = 0
								IF TIMERB > 4000
									 hei3_spooked_check = 1
								ENDIF
							ENDIF				
						ENDIF
					
						IF hei3_spook < 0
							hei3_spook = 0
						ELSE
							IF hei3_spook > 100
								hei3_spook = 100
							ENDIF
						ENDIF


						
						
						IF hei3_spook > 10
							IF hei3_flag_player_had_warning1_fm2 = 0
								PRINT_NOW (HEI3_11) 5000 1 //~s~You're too close, back off before you spook her!
								hei3_flag_player_had_warning1_fm2 = 1				
							ENDIF		
						ENDIF
																
							

						IF hei3_spook = 100
							IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
								CLEAR_PRINTS
								DO_FADE 1000 FADE_OUT
								WHILE GET_FADING_STATUS
								    WAIT 0
								ENDWHILE 	
									GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
									BUILD_PLAYER_MODEL player1

								DO_FADE 1000 FADE_IN
								WHILE GET_FADING_STATUS
								    WAIT 0
								ENDWHILE
							ENDIF
							PRINT_NOW (HEI3_17) 5000 1 //~r~You got too close!
							GOTO mission_heist3_failed
						ENDIF	


					 	IF NOT IS_CHAR_IN_CAR hei3_millie_ped hei3_millie_car
						OR IS_CHAR_TOUCHING_VEHICLE scplayer hei3_millie_car
						OR HAS_CAR_BEEN_DAMAGED_BY_CHAR hei3_millie_car scplayer	
							hei3_spook = 100
							IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
								CLEAR_PRINTS
								DO_FADE 1000 FADE_OUT
								WHILE GET_FADING_STATUS
								    WAIT 0
								ENDWHILE 	
									GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
									BUILD_PLAYER_MODEL player1

								DO_FADE 1000 FADE_IN
								WHILE GET_FADING_STATUS
								    WAIT 0
								ENDWHILE
							ENDIF
							PRINT_NOW (HEI3_31) 5000 1 //"Something's spooked the croupier
							GOTO mission_heist3_failed
						ENDIF

					ENDIF
				ENDIF
				

				


				///////////////////////////
				/// end of spook checks ///
				///////////////////////////
				
			ENDIF
		ELSE			
			CLEAR_PRINTS
			IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 	
					GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
					BUILD_PLAYER_MODEL player1

				DO_FADE 1000 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
			ENDIF
			PRINT (HEI3_18) 5000 1 //~r~You destroyed her car, she's not going to help you now!
			GOTO mission_heist3_failed		
		ENDIF
	ELSE
		CLEAR_PRINTS
		IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 	
				GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
				BUILD_PLAYER_MODEL player1

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
		ENDIF
		
		PRINT (HEI3_14) 5000 1 //~r~You killed the croupier!
		GOTO mission_heist3_failed	
	ENDIF		
ENDIF

////////////////////////////
/// PLAYER IN SEX SHOP	 ///
////////////////////////////
IF hei3_progress_flag = 3
	IF IS_CAR_DEAD hei3_millie_car
		CLEAR_PRINTS
		IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 	
				GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
				BUILD_PLAYER_MODEL player1

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
		ENDIF
		PRINT (HEI3_18) 5000 1 //~r~You destroyed her car, she's not going to help you now!
		GOTO mission_heist3_failed
	ENDIF
	
	//IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 2085.7207 2074.7605 10.0579 200.0 200.0 50.0 FALSE// sex shop exterior		
		
	IF hei3_temp_int = 0
		IF LOCATE_CHAR_ON_FOOT_3D scplayer 2086.7256 2074.3516 10.0579 2.0 2.0 3.0 TRUE 
				
			hei3_temp_int = 1
			SET_PLAYER_CONTROL Player1 OFF
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE 
			CLEAR_PRINTS
			SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2086.7256 2074.3516 5.0579
			SET_AREA_VISIBLE 3
			REQUEST_COLLISION -100.4320 -23.1933
			LOAD_SCENE -100.4320 -23.1933 999.7261
			
			SET_CHAR_COORDINATES scplayer -100.4320 -23.1933 999.7261 
			SET_CHAR_HEADING scplayer -0.0000				
			
			LOAD_SPECIAL_CHARACTER 1 CROGRL2 
			REQUEST_MODEL CELLPHONE
			
			REQUEST_MODEL BMYPIMP
			REQUEST_MODEL WFYSEX
			REQUEST_MODEL BMOCHIL
			REQUEST_MODEL WMYST

			WHILE NOT HAS_MODEL_LOADED CELLPHONE
			OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
			
			WAIT 0
			ENDWHILE
																		
			WHILE NOT HAS_MODEL_LOADED	BMYPIMP
			OR NOT HAS_MODEL_LOADED WFYSEX
			OR NOT HAS_MODEL_LOADED	BMOCHIL
			OR NOT HAS_MODEL_LOADED WMYST
			WAIT 0 
			ENDWHILE
			//////////////////////////////////////////////////////
			///// NEED TO CREATE SOME AMBIENT PEDS IN HERE?? /////
			//////////////////////////////////////////////////////
			CREATE_CHAR PEDTYPE_MISSION1 BMOCHIL -100.6538 -17.6330 999.7188   	hei3_shop_ped1// customer 1
			SET_CHAR_HEADING hei3_shop_ped1 93.7683
			CREATE_CHAR PEDTYPE_MISSION1 WMYST -108.0798 -18.0669 999.7261  	hei3_shop_ped2// customer 2	
			SET_CHAR_HEADING hei3_shop_ped2 89.3157
			CREATE_CHAR PEDTYPE_MISSION1 BMYPIMP -105.6009 -13.6508 999.7261  	hei3_shop_ped3// customer 3
			SET_CHAR_HEADING hei3_shop_ped3 176.8410
			CREATE_CHAR PEDTYPE_MISSION1 WFYSEX -103.7278 -24.3711 999.7261  	hei3_shop_ped4// server 1
			SET_CHAR_HEADING hei3_shop_ped4 349.7995
			CREATE_CHAR PEDTYPE_MISSION1 WFYSEX -104.7150 -8.9155 999.7188   	hei3_shop_ped5// server2
			SET_CHAR_HEADING hei3_shop_ped5 179.3188



			/////////////////////////////////////////////////////
			/////////////////////////////////////////////////////
			/////////////////////////////////////////////////////
			
			//CREATE_CHAR PEDTYPE_SPECIAL WFYSEX -113.9837 -8.3695 999.7812  hei3_millie_ped
			CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 -113.9837 -8.3695 999.7812  hei3_millie_ped
			

			SET_CHAR_NEVER_TARGETTED hei3_millie_ped TRUE
			SET_CHAR_HEALTH hei3_millie_ped 200
			SET_CHAR_SUFFERS_CRITICAL_HITS hei3_millie_ped FALSE

			SET_CHAR_DECISION_MAKER hei3_millie_ped hei3_decision
			SET_ANIM_GROUP_FOR_CHAR hei3_millie_ped sexywoman
			SET_CHAR_HEADING hei3_millie_ped 183.0375
			
			IF HAS_ANIMATION_LOADED CLOTHES
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Torso CLOTHES 1000.0 TRUE FALSE FALSE TRUE -1 // CLO_Pose_Torso
			ENDIF
			REMOVE_BLIP hei3_sex_shop_blip
			REMOVE_BLIP hei3_millie_blip
			//ADD_BLIP_FOR_CHAR hei3_millie_ped hei3_millie_blip


			CREATE_CHAR PEDTYPE_MISSION1 WFYSEX -115.8863 -12.0374 999.7886 hei3_doorman_ped

			SET_ANIM_GROUP_FOR_CHAR hei3_doorman_ped sexywoman
			SET_CHAR_DECISION_MAKER hei3_doorman_ped hei3_decision
			SET_CHAR_HEADING hei3_doorman_ped 297.3894
			
			SET_CHAR_COORDINATES scplayer -100.4320 -23.1933 999.7261 
			SET_CHAR_HEADING scplayer -0.0000
			WAIT 1000		
			
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			SET_PLAYER_CONTROL Player1 ON
			PRINT (HEI3_29) 5000 1 //~s~The Croupier is in here somewhere, find her.
		ENDIF
	ENDIF
		
		
		
		
		
	IF hei3_temp_int = 1	
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -107.1254  -12.2446  1000.8212  15.2999  15.0999  35.2997  FALSE // sex shop interior		
			
			/////////////////////////////////////////////////////////////////////////////////////
			/// CHECK IF PLAYER IS CLOSE TO ONE OF THE COUNTER STAFF AND HAVE THEM SAY THINGS ///
			/////////////////////////////////////////////////////////////////////////////////////
			
			IF NOT IS_CHAR_DEAD hei3_shop_ped4
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hei3_shop_ped4 3.0 3.0 3.0 FALSE
					SET_CHAR_SAY_CONTEXT_IMPORTANT hei3_shop_ped4 CONTEXT_GLOBAL_EYEING_PLAYER TRUE		FALSE	FALSE hie3_dummy
				ENDIF
			ENDIF
			
			IF NOT IS_CHAR_DEAD hei3_shop_ped5
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hei3_shop_ped5 3.0 3.0 3.0 FALSE
					SET_CHAR_SAY_CONTEXT_IMPORTANT hei3_shop_ped5 CONTEXT_GLOBAL_GENERIC_HI_FEMALE TRUE		FALSE	FALSE  hie3_dummy
				ENDIF
			ENDIF
			
			/////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////////////

			
			
			
			/// INSIDE SEX SHOP											 
			IF NOT IS_CHAR_DEAD hei3_millie_ped
				IF NOT IS_CHAR_DEAD hei3_doorman_ped
											
					IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR hei3_millie_ped scplayer
					OR HAS_CHAR_BEEN_DAMAGED_BY_CHAR hei3_doorman_ped scplayer
						CLEAR_PRINTS
						IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
							DO_FADE 1000 FADE_OUT
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE 	
								GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
								BUILD_PLAYER_MODEL player1

							DO_FADE 1000 FADE_IN
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE
						ENDIF
						
						
						IF NOT IS_CHAR_DEAD hei3_millie_ped
						AND NOT IS_CHAR_HEAD_MISSING hei3_millie_ped
							GET_CHAR_HEALTH hei3_millie_ped hei3_temp_int
							IF hei3_temp_int > 0
							PRINT (HEI3_31) 5000 1//  ~r~Something's spooked the Croupier!
							ELSE
							PRINT HEI3_14 5000 1 
							ENDIF
						ELSE
							PRINT HEI3_14 5000 1 
						ENDIF
						GOTO mission_heist3_failed
					ENDIF
																					
													
					
					IF LOCATE_STOPPED_CHAR_ANY_MEANS_3D SCPLAYER -109.7757 -12.6704 999.7188  1.2 1.2  3.1000  TRUE
						//OR HAS_CHAR_SPOTTED_CHAR hei3_doorman_ped scplayer
						CLEAR_AREA -107.7856 -12.4046 999.7812 50.0 TRUE
						SET_PLAYER_CONTROL Player1 OFF
						SWITCH_WIDESCREEN ON
						CLEAR_PRINTS
						
											
						SET_FIXED_CAMERA_POSITION -113.08 -12.39 1000.76 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -119.54 -8.15 1001.83 JUMP_CUT

						SET_CHAR_COORDINATES scplayer -109.7757 -12.6704 999.7188 
						SET_CHAR_HEADING scplayer 59.7399 
						
						SKIP_CUTSCENE_START
						


						hei3_audio_counter	= 0
						hei3_audio_slot1 	   = 1
						hei3_audio_slot2 	   = 2
						hei3_audio_playing	   = 0
						hei3_text_loop_done	   = 0
						hei3_mobile			   = 0
						hei3_text_timer_diff   = 0
						hei3_text_timer_end    = 0
						hei3_text_timer_start  = 0
						hei3_ahead_counter	   = 0
						
						hei3_text_loop2:
						
						WAIT 0 

						IF hei3_text_loop_done = 0
							
							
							IF NOT hei3_audio_counter = 0
								IF hei3_audio_playing = 0
									IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
										CLEAR_MISSION_AUDIO hei3_audio_slot2
									ENDIF
									hei3_audio_playing = 1
								ENDIF

								IF hei3_audio_playing = 1
									LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
									hei3_audio_playing = 2
								ENDIF

								IF hei3_audio_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
										PLAY_MISSION_AUDIO hei3_audio_slot1
										PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
										hei3_audio_playing = 3
									ENDIF
								ENDIF

								IF hei3_audio_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
										CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
										IF hei3_audio_slot1 = 1
											hei3_audio_slot1 = 2
											hei3_audio_slot2 = 1
										ELSE
											hei3_audio_slot1 = 1
											hei3_audio_slot2 = 2
										ENDIF
										hei3_audio_counter = 0
										hei3_audio_playing = 0
									ELSE
										IF NOT HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
											IF hei3_audio_counter < 8
												hei3_ahead_counter = hei3_audio_counter + 1
												LOAD_MISSION_AUDIO hei3_audio_slot2 hei3_audio[hei3_ahead_counter]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF												   			
							
							
							
							SWITCH hei3_mobile
								CASE 0
									IF hei3_audio_playing = 0
										IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped
									 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
										ENDIF
										hei3_audio_counter = 4//PRINT (HE3_CA) 5000 1 //CROUPIER:"Wow, it's nice and tight!"						
										hei3_mobile = 1
										GET_GAME_TIMER hei3_text_timer_start
									ENDIF
									BREAK
								CASE 1
									GET_GAME_TIMER hei3_text_timer_end
									hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
									IF hei3_text_timer_diff > 1000
										IF hei3_audio_playing = 0
											IF NOT IS_CHAR_DEAD	hei3_millie_ped
											STOP_CHAR_FACIAL_TALK hei3_millie_ped
										 	ENDIF
										 	IF NOT IS_CHAR_DEAD hei3_doorman_ped
										 	START_CHAR_FACIAL_TALK hei3_doorman_ped	 3000
											ENDIF
											hei3_audio_counter = 5 //PRINT (HE3_CB) 5000 1 //"I'm sure you'll squeeze in just fine!"					
											hei3_mobile = 2
											GET_GAME_TIMER hei3_text_timer_start
										ENDIF
									ENDIF	
									BREAK
								CASE 2
									GET_GAME_TIMER hei3_text_timer_end
									hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
									IF hei3_text_timer_diff > 1000
										IF hei3_audio_playing = 0
											hei3_audio_counter = 6 	 //PRINT (HE3_CC) 5000 1 //"Let's have a look"
											hei3_mobile = 3
											GET_GAME_TIMER hei3_text_timer_start
										ENDIF
									ENDIF
									BREAK												
								DEFAULT
									GET_GAME_TIMER hei3_text_timer_end
									hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
									IF hei3_text_timer_diff > 1000
										IF hei3_audio_playing = 0
										    IF NOT IS_CHAR_DEAD hei3_doorman_ped
										 	STOP_CHAR_FACIAL_TALK hei3_doorman_ped	
											ENDIF
										   hei3_text_loop_done = 1	
										ENDIF
									ENDIF
									BREAK
							ENDSWITCH
							GOTO hei3_text_loop2
						ENDIF //hei3_text_loop_done = 1




						IF NOT IS_CHAR_DEAD hei3_millie_ped					 
							IF NOT IS_CHAR_DEAD hei3_doorman_ped
								OPEN_SEQUENCE_TASK hei3_millie_seq			
								TASK_GO_STRAIGHT_TO_COORD -1 -113.8582 -9.7549 999.7812 PEDMOVE_WALK -1	// to door of booth
								TASK_GO_STRAIGHT_TO_COORD -1 -114.2882 -11.5372 999.7886 PEDMOVE_WALK -1// to corridor
								TASK_TURN_CHAR_TO_FACE_CHAR -1 hei3_doorman_ped																	
								CLOSE_SEQUENCE_TASK hei3_millie_seq
								PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
								CLEAR_SEQUENCE_TASK hei3_millie_seq
							ENDIF
						ENDIF


						hei3_audio_counter	= 0
						hei3_audio_slot1 	   = 1
						hei3_audio_slot2 	   = 2
						hei3_audio_playing	   = 0
						hei3_text_loop_done	   = 0
						hei3_mobile			   = 0
						hei3_text_timer_diff   = 0
						hei3_text_timer_end    = 0
						hei3_text_timer_start  = 0
						hei3_ahead_counter	   = 0

						hei3_safety_flag = 0
						hei3_temp_int = 0 
						TIMERA = 0
						WHILE hei3_safety_flag = 0
								
							IF hei3_text_loop_done = 0												
								IF NOT hei3_audio_counter = 0
									IF hei3_audio_playing = 0
										IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
											CLEAR_MISSION_AUDIO hei3_audio_slot2
										ENDIF
										hei3_audio_playing = 1
									ENDIF

									IF hei3_audio_playing = 1
										LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
										hei3_audio_playing = 2
									ENDIF

									IF hei3_audio_playing = 2
									 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
											PLAY_MISSION_AUDIO hei3_audio_slot1
											PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
											hei3_audio_playing = 3
										ENDIF
									ENDIF

									IF hei3_audio_playing = 3
										IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
											IF NOT IS_CHAR_DEAD	hei3_millie_ped
											STOP_CHAR_FACIAL_TALK hei3_millie_ped										 	
											ENDIF
											CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
											IF hei3_audio_slot1 = 1
												hei3_audio_slot1 = 2
												hei3_audio_slot2 = 1
											ELSE
												hei3_audio_slot1 = 1
												hei3_audio_slot2 = 2
											ENDIF
											hei3_audio_counter = 0
											hei3_audio_playing = 0
											hei3_text_loop_done = 1									
										ENDIF
									ENDIF
								ENDIF
							ENDIF																																				
								
							IF NOT IS_CHAR_DEAD hei3_millie_ped						
								GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
								IF  hei3_task_status  = FINISHED_TASK  													
									IF hei3_audio_playing = 0
										hei3_safety_flag = 1						
									ENDIF
								ELSE
									IF hei3_temp_int = 0
										IF hei3_task_status  = PERFORMING_TASK
											GET_SEQUENCE_PROGRESS hei3_millie_ped hei3_task_status
											IF hei3_task_status = 2
												IF hei3_audio_playing = 0
													IF NOT IS_CHAR_DEAD	hei3_millie_ped
													STOP_CHAR_FACIAL_TALK hei3_millie_ped
												 	START_CHAR_FACIAL_TALK hei3_millie_ped	 2000
													ENDIF
													hei3_audio_counter = 7 	 //PRINT (HE3_CD) 5000 1 //CROUPIER:"Perfect!"
													hei3_temp_int = 1												
												ENDIF
												
											ENDIF
										ENDIF
									ENDIF											
								ENDIF
							ELSE
								hei3_safety_flag = 1
							ENDIF

						WAIT 0
						ENDWHILE

						WHILE IS_MESSAGE_BEING_DISPLAYED
						WAIT 0
						ENDWHILE

						//////////////////////
						// phone rings here //
						//////////////////////
						
						IF NOT IS_CHAR_DEAD hei3_millie_ped
							OPEN_SEQUENCE_TASK hei3_millie_seq			
							TASK_GO_STRAIGHT_TO_COORD -1 -112.6415 -12.4015 999.7812 PEDMOVE_WALK -1	// to answer phone
							TASK_ACHIEVE_HEADING -1 247.0																	
							CLOSE_SEQUENCE_TASK hei3_millie_seq
							PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
							CLEAR_SEQUENCE_TASK hei3_millie_seq
						ENDIF

						SET_FIXED_CAMERA_POSITION -112.15 -11.94 1001.16 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -123.84 -14.32 1002.43 JUMP_CUT


						hei3_safety_flag = 0
						hei3_temp_int = 0 
						TIMERA = 0
						WHILE hei3_safety_flag = 0
							
									IF NOT hei3_sfx_counter = 0
										IF hei3_sfx_playing = 0
											IF HAS_MISSION_AUDIO_LOADED 3
												CLEAR_MISSION_AUDIO 3
											ENDIF
											hei3_sfx_playing = 1
										ENDIF

										IF hei3_sfx_playing = 1
											LOAD_MISSION_AUDIO 3 hei3_sfx[hei3_sfx_counter]	 // audio fx to be used
											hei3_sfx_playing = 2
										ENDIF

										IF hei3_sfx_playing = 2
										 	IF HAS_MISSION_AUDIO_LOADED 3
												PLAY_MISSION_AUDIO 3				
												hei3_sfx_playing = 3
											ENDIF
										ENDIF

										IF hei3_sfx_playing = 3
											IF HAS_MISSION_AUDIO_FINISHED 3								
												hei3_sfx_counter = 0
												hei3_sfx_playing = 0
												TIMERA = 0			
											ENDIF
										ENDIF
									ENDIF
							
									IF hei3_sfx_playing = 0
										IF TIMERA < 2000
											hei3_sfx_counter = 4
										ENDIF
									ENDIF
							
							
							IF NOT IS_CHAR_DEAD hei3_millie_ped						
								GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
								IF  hei3_task_status  = FINISHED_TASK  																					
									hei3_safety_flag = 1																								
								ENDIF
							ELSE
								hei3_safety_flag = 1
							ENDIF

						WAIT 0
						ENDWHILE

						IF NOT IS_CHAR_DEAD hei3_millie_ped
							CLEAR_MISSION_AUDIO 3
							TASK_USE_MOBILE_PHONE hei3_millie_ped TRUE
							WAIT 2000
						ENDIF															

						
						hei3_audio_counter	= 0
						hei3_audio_slot1 	   = 1
						hei3_audio_slot2 	   = 2
						hei3_audio_playing	   = 0
						hei3_text_loop_done	   = 0
						hei3_mobile			   = 0
						hei3_text_timer_diff   = 0
						hei3_text_timer_end    = 0
						hei3_text_timer_start  = 0
						hei3_ahead_counter	   = 0
						
						hei3_text_loop3:
						
						WAIT 0 

						IF hei3_text_loop_done = 0
												
							IF NOT hei3_audio_counter = 0
								IF hei3_audio_playing = 0
									IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
										CLEAR_MISSION_AUDIO hei3_audio_slot2
									ENDIF
									hei3_audio_playing = 1
								ENDIF

								IF hei3_audio_playing = 1
									LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
									hei3_audio_playing = 2
								ENDIF

								IF hei3_audio_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
										PLAY_MISSION_AUDIO hei3_audio_slot1
										PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
										hei3_audio_playing = 3
									ENDIF
								ENDIF

								IF hei3_audio_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
										IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped									 	
										ENDIF
										CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
										IF hei3_audio_slot1 = 1
											hei3_audio_slot1 = 2
											hei3_audio_slot2 = 1
										ELSE
											hei3_audio_slot1 = 1
											hei3_audio_slot2 = 2
										ENDIF
										hei3_audio_counter = 0
										hei3_audio_playing = 0
									ELSE
										IF NOT HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
											IF hei3_audio_counter < 8
												hei3_ahead_counter = hei3_audio_counter + 1
												LOAD_MISSION_AUDIO hei3_audio_slot2 hei3_audio[hei3_ahead_counter]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							
												
							SWITCH hei3_mobile
								CASE 0
									IF hei3_audio_playing = 0
										IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped
									 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
										ENDIF
										hei3_audio_counter = 8	//PRINT (HE3_CE) 4000 1 //CROUPIER:"Oh hi Benny..."										
										hei3_mobile = 1
										GET_GAME_TIMER hei3_text_timer_start
									ENDIF
									BREAK
								CASE 1
									GET_GAME_TIMER hei3_text_timer_end
									hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
									IF hei3_text_timer_diff > 1000
										IF hei3_audio_playing = 0
											IF NOT IS_CHAR_DEAD	hei3_millie_ped										
										 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
											ENDIF
											hei3_audio_counter = 9 //PRINT (HE3_CF) 5000 1 //CROUPIER:"Yes master, I'm just trying it on."	
											hei3_mobile = 2
											GET_GAME_TIMER hei3_text_timer_start
										ENDIF
									ENDIF	
									BREAK
								CASE 2
									GET_GAME_TIMER hei3_text_timer_end
									hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
									IF hei3_text_timer_diff > 1000
										IF hei3_audio_playing = 0
											IF NOT IS_CHAR_DEAD	hei3_millie_ped											
										 	START_CHAR_FACIAL_TALK hei3_millie_ped	 2000
											ENDIF
											hei3_audio_counter = 10 //PRINT (HE3_CG) 4000 1 //CROUPIER:"You got yours?"	
											hei3_mobile = 3
											GET_GAME_TIMER hei3_text_timer_start
										ENDIF
									ENDIF
									BREAK
								CASE 3
									GET_GAME_TIMER hei3_text_timer_end
									hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
									IF hei3_text_timer_diff > 1000
										IF hei3_audio_playing = 0
											IF NOT IS_CHAR_DEAD	hei3_millie_ped											
										 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
											ENDIF
											hei3_audio_counter = 11	//PRINT (HE3_CH) 5000 1 //CROUPIER:"Cool, I'll see you at my house in a short while."
											hei3_mobile = 4
											GET_GAME_TIMER hei3_text_timer_start
										ENDIF
									ENDIF
									BREAK
								
								DEFAULT
									GET_GAME_TIMER hei3_text_timer_end
									hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
									IF hei3_text_timer_diff > 1000
										IF hei3_audio_playing = 0
										   hei3_text_loop_done = 1	
										ENDIF
									ENDIF
									BREAK
							ENDSWITCH
							GOTO hei3_text_loop3
						ENDIF

																																																	

						IF NOT IS_CHAR_DEAD hei3_millie_ped
							GET_SCRIPT_TASK_STATUS hei3_millie_ped TASK_USE_MOBILE_PHONE hei3_task_status
							IF NOT hei3_task_status = FINISHED_TASK
								TASK_USE_MOBILE_PHONE hei3_millie_ped FALSE
							ENDIF
							MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
						ENDIF
						
						IF NOT IS_CHAR_DEAD hei3_millie_ped
							OPEN_SEQUENCE_TASK hei3_millie_seq			
							TASK_ACHIEVE_HEADING -1 87.0
							TASK_GO_STRAIGHT_TO_COORD -1 -114.2882 -11.5372 999.7886 PEDMOVE_WALK -1// to corridor
							TASK_GO_STRAIGHT_TO_COORD -1 -113.8582 -9.7549 999.7812 PEDMOVE_WALK -1	// to door of booth
							TASK_GO_STRAIGHT_TO_COORD -1 -113.9837 -8.3695 999.7812 PEDMOVE_WALK -1	// to booth																													
							CLOSE_SEQUENCE_TASK hei3_millie_seq
							PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
							CLEAR_SEQUENCE_TASK hei3_millie_seq
						ENDIF

						IF NOT IS_CHAR_DEAD hei3_doorman_ped
							OPEN_SEQUENCE_TASK hei3_millie_seq			
							TASK_STAND_STILL -1 3000
							TASK_GO_STRAIGHT_TO_COORD -1 -102.0 -12.0 1001.0 PEDMOVE_WALK -1	// to answer phone						
							CLOSE_SEQUENCE_TASK hei3_millie_seq
							PERFORM_SEQUENCE_TASK hei3_doorman_ped hei3_millie_seq
							CLEAR_SEQUENCE_TASK hei3_millie_seq
						ENDIF
							
						SET_FIXED_CAMERA_POSITION -106.63 -11.88 1001.28 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -120.22 -11.77 1000.71 JUMP_CUT
							
						hei3_safety_flag = 0
						hei3_temp_int = 0 
						TIMERA = 0
						WHILE hei3_safety_flag = 0
							IF NOT IS_CHAR_DEAD hei3_millie_ped						
								GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
								IF  hei3_task_status  = FINISHED_TASK  // sitting in car																					
									hei3_safety_flag = 1						
								ELSE								
									IF hei3_task_status  = PERFORMING_TASK
										GET_SEQUENCE_PROGRESS hei3_millie_ped hei3_task_status
										IF hei3_task_status = 3											
											hei3_safety_flag = 1	
										ENDIF									
									ENDIF											
								ENDIF
							ELSE
								hei3_safety_flag = 1
							ENDIF

						WAIT 0
						ENDWHILE
						
						
						hei3_safety_flag  = 0
						hei3_sfx_counter = 3
						hei3_sfx_playing = 0 

						WHILE hei3_safety_flag = 0
						
							IF NOT hei3_sfx_counter = 0
								IF hei3_sfx_playing = 0
									IF HAS_MISSION_AUDIO_LOADED 3
										CLEAR_MISSION_AUDIO 3
									ENDIF
									hei3_sfx_playing = 1
								ENDIF

								IF hei3_sfx_playing = 1
									LOAD_MISSION_AUDIO 3 hei3_sfx[hei3_sfx_counter]	 // audio fx to be used
									hei3_sfx_playing = 2
								ENDIF

								IF hei3_sfx_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED 3
										PLAY_MISSION_AUDIO 3				
										hei3_sfx_playing = 3
									ENDIF
								ENDIF

								IF hei3_sfx_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED 3								
										hei3_sfx_counter = 0
										hei3_sfx_playing = 0
										hei3_safety_flag = 1												
									ENDIF
								ENDIF
							ENDIF
						WAIT 0 
						ENDWHILE
									

						DO_FADE 1500 FADE_OUT
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE
						
						DELETE_CHAR hei3_millie_ped
						MARK_CHAR_AS_NO_LONGER_NEEDED hei3_millie_ped
						
						
						CREATE_CHAR  PEDTYPE_MISSION1 CROGRL3 -100.3376 -20.1905 999.7812 hei3_millie_ped						 
						SET_CHAR_DECISION_MAKER hei3_millie_ped hei3_decision
						SET_ANIM_GROUP_FOR_CHAR hei3_millie_ped sexywoman
						SET_CHAR_HEADING hei3_millie_ped 183.4416	
						
						SET_FIXED_CAMERA_POSITION -101.69 -20.20 1000.67 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -84.83 -44.52 1005.63 JUMP_CUT

						DO_FADE 1500 FADE_IN
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						IF NOT IS_CHAR_DEAD hei3_millie_ped
							OPEN_SEQUENCE_TASK hei3_millie_seq									
							TASK_GO_STRAIGHT_TO_COORD -1 -100.2427 -24.2149 999.7886 PEDMOVE_WALK -1	// to exit																													
							CLOSE_SEQUENCE_TASK hei3_millie_seq
							PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
							CLEAR_SEQUENCE_TASK hei3_millie_seq
						ENDIF

						hei3_safety_flag = 0
						hei3_temp_int = 0 
						TIMERA = 0
						WHILE hei3_safety_flag = 0
							IF NOT IS_CHAR_DEAD hei3_millie_ped						
								GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
								IF  hei3_task_status  = FINISHED_TASK  																					
									hei3_safety_flag = 1																								
								ENDIF
							ELSE
								hei3_safety_flag = 1
							ENDIF
						WAIT 0
						ENDWHILE									
					
						SKIP_CUTSCENE_END
						
						
						CLEAR_MISSION_AUDIO hei3_audio_slot1
						CLEAR_MISSION_AUDIO hei3_audio_slot2
						CLEAR_PRINTS
						CLEAR_MISSION_AUDIO 3
						
						DO_FADE 500 FADE_IN
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						SWITCH_WIDESCREEN OFF
						SET_CAMERA_BEHIND_PLAYER
						RESTORE_CAMERA_JUMPCUT
						
						SET_PLAYER_CONTROL Player1 ON
						//ADD_BLIP_FOR_COORD -114.6156  -8.6308  1000.7133 hei3_gimp_suit_blip					
						//CREATE_CLOTHES_PICKUP -114.6156  -8.6308  1000.7133 1 hei3_gimp_suit_pickup
						CREATE_PICKUP clothesp PICKUP_ONCE -114.6156  -8.6308  1000.7133 hei3_gimp_suit_pickup
						ADD_BLIP_FOR_PICKUP hei3_gimp_suit_pickup hei3_gimp_suit_blip
						SET_BLIP_ENTRY_EXIT hei3_gimp_suit_blip  2090.0 2074.0  10.0
						
						PRINT (HEI3_04) 5000 1 //~s~Go and get a gimp suit.
						
						
						MARK_CHAR_AS_NO_LONGER_NEEDED hei3_millie_ped
						MARK_CHAR_AS_NO_LONGER_NEEDED hei3_doorman_ped

						MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
						UNLOAD_SPECIAL_CHARACTER 1

						DELETE_CHAR hei3_doorman_ped
						DELETE_CHAR hei3_millie_ped
						REQUEST_MODEL WINDSOR  
				
			
						hei3_temp_int = 0
						hei3_progress_flag = 4
					ENDIF // lcate in corridor								

				ELSE // if doorman is dead
					CLEAR_PRINTS 
					IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 	
							GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
							BUILD_PLAYER_MODEL player1

						DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE
					ENDIF					
					PRINT (HEI3_31) 5000 1//  ~r~Something's spooked the Croupier!
					GOTO mission_heist3_failed

				ENDIF
			ELSE				
				CLEAR_PRINTS 
				IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
					DO_FADE 1000 FADE_OUT
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE 	
						GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
						BUILD_PLAYER_MODEL player1

					DO_FADE 1000 FADE_IN
					WHILE GET_FADING_STATUS
					    WAIT 0
					ENDWHILE
				ENDIF
				
				//IF IS_CHAR_DEAD hei3_millie_ped
					PRINT (HEI3_14)5000 1 //  ~r~You killed the croupier!
				//ELSE
					
				//ENDIF
				
				GOTO mission_heist3_failed
			ENDIF // doorman dead check															
		ELSE  // check locate on interior of sex shop
			/// FAIL AS NOT NEAR SEX SHOP
			CLEAR_PRINTS
			IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 	
					GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
					BUILD_PLAYER_MODEL player1

				DO_FADE 1000 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
			ENDIF
			PRINT (HEI3_16) 5000 1 //~r~You lost her!
			GOTO mission_heist3_failed
		ENDIF	
	ENDIF //hei3_temp_int = 1
ENDIF

///////////////////////////////////////////////////
/// PLAYER BUYS A GIMP SUIT AND LEAVES SEX SHOP ///
///////////////////////////////////////////////////

IF hei3_progress_flag = 4						
	IF hei3_player_has_gimp_suit = 0
		IF hei3_temp_int = 0
			
			DISABLE_ALL_ENTRY_EXITS TRUE
			hei3_temp_int = 1
		ENDIF
		IF HAS_PICKUP_BEEN_COLLECTED  hei3_gimp_suit_pickup//LOCATE_CHAR_ANY_MEANS_3D SCPLAYER -114.6156  -8.6308  1000.7133  1.2000  1.2000  3.8000  TRUE						
			REMOVE_PICKUP hei3_gimp_suit_pickup						
			CLEAR_PRINTS
			
			
			STORE_SCORE player1 players_money
			IF players_money >= 200 				
				ADD_SCORE player1 -200	 				
			ELSE
				IF players_money > 0 
					players_money *= -1
					ADD_SCORE player1 players_money
				ENDIF
			ENDIF

			SET_PLAYER_CONTROL Player1 OFF
			
			SET_MUSIC_DOES_FADE FALSE

			DO_FADE 1500 FADE_OUT
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			SWITCH_WIDESCREEN ON
			REMOVE_BLIP	 hei3_gimp_suit_blip
			
			STORE_CLOTHES_STATE
		   	hei3_clothes_changed = 1	
		   	 
			GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1  gimpleg 	gimpleg CLOTHES_TEX_EXTRA1//  	2
						

		   	BUILD_PLAYER_MODEL player1
			hei3_safety_flag  = 0
			hei3_sfx_counter = 1
			hei3_sfx_playing = 0 

			WHILE hei3_safety_flag = 0
					
				IF NOT hei3_sfx_counter = 0
					IF hei3_sfx_playing = 0
						IF HAS_MISSION_AUDIO_LOADED 3
							CLEAR_MISSION_AUDIO 3
						ENDIF
						hei3_sfx_playing = 1
					ENDIF

					IF hei3_sfx_playing = 1
						LOAD_MISSION_AUDIO 3 hei3_sfx[hei3_sfx_counter]	 // audio fx to be used
						hei3_sfx_playing = 2
					ENDIF

					IF hei3_sfx_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED 3
							PLAY_MISSION_AUDIO 3				
							hei3_sfx_playing = 3
						ENDIF
					ENDIF

					IF hei3_sfx_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED 3								
							hei3_sfx_counter = 0
							hei3_sfx_playing = 0
							hei3_safety_flag = 1										
						ENDIF
					ENDIF
				ENDIF
			WAIT 0 
			ENDWHILE
			SET_FIXED_CAMERA_POSITION -111.19 -12.67 1002.69 0.0 0.0 0.0 
			POINT_CAMERA_AT_POINT -124.89 -3.78 997.61 JUMP_CUT 
			CLEAR_AREA  -113.9837 -8.3695 999.7812 20.0 TRUE
			SET_CHAR_COORDINATES scplayer  -113.9837 -8.3695 999.7812
			SET_CHAR_HEADING scplayer 183.0

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE

			SET_MUSIC_DOES_FADE TRUE

			OPEN_SEQUENCE_TASK hei3_player_seq
			TASK_STAND_STILL -1 1000
			TASK_GO_STRAIGHT_TO_COORD -1 -113.8582 -9.7549 999.7812 PEDMOVE_WALK -1	// to door of booth
			TASK_GO_STRAIGHT_TO_COORD -1 -114.2882 -11.5372 999.7886 PEDMOVE_WALK -1// to corridor
			TASK_ACHIEVE_HEADING -1 252.0
			TASK_SCRATCH_HEAD -1
			CLOSE_SEQUENCE_TASK hei3_player_seq
			PERFORM_SEQUENCE_TASK scplayer hei3_player_seq
			CLEAR_SEQUENCE_TASK hei3_player_seq
			
			hei3_safety_flag = 0
			WHILE hei3_safety_flag = 0
				IF NOT IS_CHAR_DEAD scplayer
					GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK hei3_task_status
					IF  hei3_task_status  = FINISHED_TASK					    												
						hei3_safety_flag = 1						
					ENDIF
				ELSE
					hei3_safety_flag = 1
				ENDIF

			WAIT 0
			ENDWHILE
			
			DISABLE_ALL_ENTRY_EXITS FALSE
			SET_CAMERA_BEHIND_PLAYER
			RESTORE_CAMERA_JUMPCUT
			SET_PLAYER_CONTROL Player1 ON
			SWITCH_WIDESCREEN OFF
			PRINT (HEI3_30) 5000 1//~s~Leave the shop and go after the Croupier.
			REMOVE_BLIP hei3_sex_shop_blip
			ADD_BLIP_FOR_COORD hei3_sex_shop_x hei3_sex_shop_y hei3_sex_shop_z hei3_sex_shop_blip
			hei3_player_has_gimp_suit = 1
		ENDIF
	ELSE	
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -100.5496 -24.1101 999.7886  1.3 1.3 50.0 FALSE// sex shop exterior
			hei3_progress_flag = 5
		ENDIF
		
		GET_AREA_VISIBLE hei3_temp_int	
		IF hei3_temp_int = 0
			hei3_progress_flag = 5
		ENDIF
		//IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2085.7207 2074.7605 10.0579 200.0 200.0 50.0 FALSE// sex shop exterior
		//	hei3_progress_flag = 5				
		//ENDIF
	ENDIF
ENDIF


//////////////////////////////////
/// MILLIE LEAVES THE SEX SHOP ///
//////////////////////////////////

IF hei3_progress_flag = 5
				
	SET_PLAYER_CONTROL Player1 OFF
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	REMOVE_BLIP hei3_sex_shop_blip

	MARK_CHAR_AS_NO_LONGER_NEEDED hei3_doorman_ped
	
	
	MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped1
	MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped2
	MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped3
	MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped4
	MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped5

	MARK_MODEL_AS_NO_LONGER_NEEDED	BMYPIMP
	MARK_MODEL_AS_NO_LONGER_NEEDED WFYSEX
	MARK_MODEL_AS_NO_LONGER_NEEDED	BMOCHIL
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYST
	
	
	
	SET_AREA_VISIBLE 0
	
	REQUEST_MODEL CWMYHB1		
	REQUEST_MODEL GUN_DILDO1
	REQUEST_MODEL WINDSOR
		
	WHILE NOT HAS_MODEL_LOADED CWMYHB1		
	OR NOT HAS_MODEL_LOADED GUN_DILDO1
	OR NOT HAS_MODEL_LOADED WINDSOR				
		WAIT 0
	ENDWHILE
	
	LOAD_SCENE 2085.1196 2077.0857 15.8434
	
	//CLEAR_AREA 	2095.9131 2074.0813 9.8203 100.0 TRUE
	CLEAR_AREA 	2105.9570 2039.1860 9.8203 		6.0 TRUE
	CLEAR_AREA  2093.9131 2047.0813 9.8203 		6.0 TRUE
	CLEAR_AREA  2093.9131 2055.0813 9.8203 		6.0 TRUE
	CLEAR_AREA  2093.9131 2060.0813 9.8203 		6.0 TRUE
	CLEAR_AREA  2093.9131 2065.0813 9.8203 		6.0 TRUE
	CLEAR_AREA  2093.9131 2070.0813 9.8203 		6.0 TRUE
	CLEAR_AREA	2088.8269 2076.0010 9.8203		12.0 TRUE
		
	
	
	CREATE_CAR WINDSOR 2103.9570 2073.1860 9.8203 hei3_temp_car 
	SET_CAR_HEADING hei3_temp_car 270.8618
	MARK_CAR_AS_NO_LONGER_NEEDED hei3_temp_car
	MARK_MODEL_AS_NO_LONGER_NEEDED WINDSOR
	//2086.3462 2074.3911 10.0579 107.4505 //sexshop outside
    //-100.3132 -25.1503 999.7261 179.091 //sex shop inside
	SET_CHAR_HAS_USED_ENTRY_EXIT scplayer -100.3132 -25.1503  20.0
	SET_CHAR_COORDINATES scplayer  2085.1196 2077.0857 15.8434	
	SET_CHAR_HEADING scplayer 268.8011
	SET_AREA_VISIBLE  0
	CREATE_CHAR PEDTYPE_MISSION1 CROGRL3 2085.7207 2074.7605 10.0579 hei3_millie_ped
	SET_CHAR_DECISION_MAKER hei3_millie_ped hei3_decision
	SET_ANIM_GROUP_FOR_CHAR hei3_millie_ped sexywoman
	ADD_BLIP_FOR_CHAR hei3_millie_ped hei3_millie_blip
	SET_CHAR_HEADING hei3_millie_ped 271.0
	FLUSH_ROUTE						
	EXTEND_ROUTE 2037.6713 2743.1963 9.6875
	
	DELETE_CAR	  hei3_millie_car
	MARK_CAR_AS_NO_LONGER_NEEDED hei3_millie_car
	
	CUSTOM_PLATE_FOR_NEXT_CAR CLUB &_SPANK__
	CREATE_CAR CLUB 2095.9131 2074.0813 9.8203 hei3_millie_car		
	CHANGE_CAR_COLOUR hei3_millie_car 126 126		
	SET_CAN_BURST_CAR_TYRES hei3_millie_car FALSE
	LOCK_CAR_DOORS hei3_millie_car CARLOCK_LOCKOUT_PLAYER_ONLY


	IF NOT IS_CAR_DEAD hei3_millie_car
		SET_CAR_COORDINATES hei3_millie_car 2095.9131 2074.0813 9.8203
		SET_CAR_HEADING hei3_millie_car 4.444
		SET_CAR_HEALTH hei3_millie_car 1000
		
		OPEN_SEQUENCE_TASK hei3_millie_seq			
		TASK_GO_STRAIGHT_TO_COORD -1 2093.4180 2073.6458 9.8203 PEDMOVE_WALK -1
		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
		TASK_ENTER_CAR_AS_DRIVER -1 hei3_millie_car	20000																	
		CLOSE_SEQUENCE_TASK hei3_millie_seq
		PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
		CLEAR_SEQUENCE_TASK hei3_millie_seq
	ENDIF
	CLEAR_PRINTS
	PRINT (HEI3_09) 5000 1 //~s~The ~r~Croupier~s~ has left the shop. Follow her but remember not to get too close.
	
	SET_FIXED_CAMERA_POSITION 2098.50 2076.12 11.80 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2075.20 2067.39 7.26 JUMP_CUT
	SWITCH_WIDESCREEN ON
	DO_FADE 1500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	


	///////////////// START OF CUT-SCENE ///////////////////////////
	hei3_x_button_can_be_pressed = 0

	hei3_safety_flag = 0
	hei3_temp_int = 0 
	TIMERA = 0
	
	WHILE hei3_safety_flag = 0
		
		IF IS_BUTTON_PRESSED PAD1 CROSS
			hei3_safety_flag = 1
			hei3_x_button_can_be_pressed = 1
		ENDIF
		
		
		IF NOT IS_CHAR_DEAD hei3_millie_ped
		AND NOT IS_CAR_DEAD hei3_millie_car
			GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
			IF  hei3_task_status  = FINISHED_TASK  // sitting in car													
				//CLEAR_AREA  2085.1196 2077.0857 15.8434	20.0 TRUE
				hei3_safety_flag = 1						
			ENDIF
		ELSE
			hei3_safety_flag = 1
		ENDIF

	WAIT 0
	ENDWHILE

	
	IF hei3_x_button_can_be_pressed = 0
		REQUEST_CAR_RECORDING 803
		
		WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 803
		WAIT 0 
		ENDWHILE

		IF NOT IS_CAR_DEAD hei3_millie_car
			START_PLAYBACK_RECORDED_CAR hei3_millie_car 803
		ENDIF
		
		LOAD_SCENE_IN_DIRECTION 2084.08 2068.62 11.22 206.8418
		SET_FIXED_CAMERA_POSITION 2088.5061 2079.9849 10.6126   0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2088.6746 2079.0000 10.5742	 JUMP_CUT
		/*
		LOAD_SCENE_IN_DIRECTION 2084.08 2068.62 11.22 250.8418
		SET_FIXED_CAMERA_POSITION 2084.08 2068.62 11.22 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2124.89 2053.98 7.72 JUMP_CUT	*/

		hei3_safety_flag = 0
		WHILE hei3_safety_flag = 0
			IF IS_BUTTON_PRESSED PAD1 CROSS
				hei3_x_button_can_be_pressed = 1
				hei3_safety_flag = 1
			ENDIF
			IF NOT IS_CAR_DEAD hei3_millie_car
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR hei3_millie_car
					hei3_safety_flag = 1
				ENDIF
			ENDIF
		WAIT 0 
		ENDWHILE

	ENDIF
	
	
	
	///////// END OF CUT-SCENE ////////
	

	REMOVE_CAR_RECORDING 803
	CLEAR_PRINTS
	IF hei3_x_button_can_be_pressed = 1
		IF NOT IS_CAR_DEAD hei3_millie_car
		AND NOT IS_CHAR_DEAD hei3_millie_ped
			IF NOT IS_CHAR_IN_CAR hei3_millie_ped hei3_millie_car
				WARP_CHAR_INTO_CAR hei3_millie_ped hei3_millie_car
			ENDIF			
			SET_CAR_COORDINATES hei3_millie_car 2109.0037 2039.7561 9.8125 
			SET_CAR_HEADING hei3_millie_car 269.4456
		ENDIF
	ENDIF
	
	IF NOT IS_CAR_DEAD hei3_millie_car
		CLOSE_ALL_CAR_DOORS hei3_millie_car
		LOCK_CAR_DOORS hei3_millie_car CARLOCK_FORCE_SHUT_DOORS
		LOCK_CAR_DOORS hei3_millie_car CARLOCK_LOCKOUT_PLAYER_ONLY
	ENDIF
				
	SET_CHAR_COORDINATES scplayer 2089.6533 2083.5618 9.8203  
	SET_CHAR_HEADING scplayer 223.4858
	RESTORE_CAMERA_JUMPCUT			
	SET_PLAYER_CONTROL Player1 ON
	SWITCH_WIDESCREEN OFF
	
	hei3_spook = 0
	DISPLAY_ONSCREEN_COUNTER_WITH_STRING hei3_spook COUNTER_DISPLAY_BAR 	 HEI3_94
	

	FLUSH_ROUTE	
	/*
	EXTEND_ROUTE 2123.3655 2030.2732 9.6719 
	EXTEND_ROUTE 2038.8033 2023.9521 9.6875  
	EXTEND_ROUTE 1930.1251 2090.3105 9.6797  
	EXTEND_ROUTE 1929.3839 2175.1289 9.7972
	EXTEND_ROUTE 1930.0311 2329.2620 9.6719
	EXTEND_ROUTE 1931.5117 2385.7969 9.6719 
	EXTEND_ROUTE 2029.4264 2417.2253 9.7143 					
	EXTEND_ROUTE 2037.6713 2743.1963 9.6875
	*/
	EXTEND_ROUTE 2123.3655 2030.2732 9.6719 
	EXTEND_ROUTE 2038.8033 2023.9521 9.6875  	
	EXTEND_ROUTE 1929.3839 2175.1289 9.7972	
	EXTEND_ROUTE 1931.5117 2385.7969 9.6719 
	EXTEND_ROUTE 2029.4264 2417.2253 9.7143 						  
	EXTEND_ROUTE 2045.0297 2745.4915 9.6719  
	EXTEND_ROUTE 2035.3170 2737.3223 9.6719  
	EXTEND_ROUTE 2035.3380 2726.5728 9.8281
	


	IF NOT IS_CAR_DEAD hei3_millie_car
	AND NOT IS_CHAR_DEAD hei3_millie_ped								
		OPEN_SEQUENCE_TASK hei3_millie_seq							
		TASK_DRIVE_POINT_ROUTE_ADVANCED -1 hei3_millie_car 20.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS_OBEYLIGHTS	
		CLOSE_SEQUENCE_TASK hei3_millie_seq
		PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
		CLEAR_SEQUENCE_TASK hei3_millie_seq
	ENDIF

	TIMERA = 0
	TIMERB = 0
	hei3_spooked_check = 0
	hei3_progress_flag = 6
ENDIF






//////////////////////////////////
/// FOLLOW MILLIE TO HER HOUSE ///
//////////////////////////////////


IF hei3_progress_flag = 6
	IF NOT IS_CHAR_DEAD hei3_millie_ped
		IF NOT IS_CAR_DEAD hei3_millie_car
			GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
			
			IF hei3_millie_at_house = 0	
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2037.8907 2749.5339 9.8125 40.0 40.0 4.0 FALSE
					LOCATE_CHAR_ANY_MEANS_3D scplayer 2037.8907 2749.5339 9.8125 4.0 4.0 4.0 TRUE
				ENDIF

				IF  hei3_task_status  = FINISHED_TASK
					REMOVE_BLIP hei3_millie_blip
					ADD_BLIP_FOR_COORD 2038.8356 2749.8032 9.8203 hei3_millie_blip
					CLEAR_PRINTS
					PRINT HEI3_96 5000 1 //~s~Park up outside the ~y~Croupier's house~s~.
					hei3_millie_at_house = 1																			
				ELSE

					GET_CHAR_COORDINATES scplayer hei3_player_x hei3_player_y hei3_player_z
					GET_CHAR_COORDINATES hei3_millie_ped hei3_millie_x hei3_millie_y hei3_millie_z
					GET_DISTANCE_BETWEEN_COORDS_3D hei3_player_x hei3_player_y hei3_player_z hei3_millie_x hei3_millie_y hei3_millie_z hei3_distance_float
					
					 
					
					
					hei3_distance_int =# hei3_distance_float				
													

					
					IF hei3_distance_int > 140
						CLEAR_PRINTS
						IF hei3_distance_int > 168 
							// FAIL
							IF NOT IS_CHAR_ON_SCREEN hei3_millie_ped
								IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
									DO_FADE 1000 FADE_OUT
									WHILE GET_FADING_STATUS
									    WAIT 0
									ENDWHILE 	
										GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
										BUILD_PLAYER_MODEL player1

									DO_FADE 1000 FADE_IN
									WHILE GET_FADING_STATUS
									    WAIT 0
									ENDWHILE
								ENDIF
								PRINT (HEI3_16) 5000 1 //~r~You lost her!
								GOTO mission_heist3_failed
							ELSE
								PRINT (HEI3_10) 1000 1 //~s~The ~r~Croupier~s~ is almost out of sight, you need to get closer!
							ENDIF
						ELSE						
							PRINT (HEI3_10) 1000 1 //~s~The ~r~Croupier~s~ is almost out of sight, you need to get closer!
						ENDIF																					
					ENDIF

					IF hei3_spooked_check = 1
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hei3_millie_ped 22.5 22.5 2.0 FALSE					   
						   IF TIMERA > 64
								++ hei3_spook
								TIMERA = 0
						   	ENDIF							
						ELSE															
							IF TIMERA > 150
								IF hei3_spook > 0
									-- hei3_spook
								ENDIF
								TIMERA = 0
							ENDIF																
						ENDIF
					ELSE
						IF hei3_spooked_check = 0
							IF TIMERB > 4000
								 hei3_spooked_check = 1
							ENDIF
						ENDIF				
					ENDIF
				
					IF hei3_spook < 0
						hei3_spook = 0
					ELSE
						IF hei3_spook > 100
							hei3_spook = 100
						ENDIF
					ENDIF


					IF hei3_spook > 10
						IF hei3_flag_player_had_warning1_fm2 = 0
							PRINT_NOW (HEI3_11) 5000 1 //~s~You're too close, back off before you spook her!
							hei3_flag_player_had_warning1_fm2 = 1				
						ENDIF		
					ENDIF	


					IF hei3_spook = 100
						IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
							CLEAR_PRINTS
							DO_FADE 1000 FADE_OUT
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE 	
								GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
								BUILD_PLAYER_MODEL player1

							DO_FADE 1000 FADE_IN
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE
						ENDIF
						PRINT_NOW (HEI3_17) 5000 1 //~r~You got too close!
						GOTO mission_heist3_failed
					ENDIF	


				 	IF NOT IS_CHAR_IN_CAR hei3_millie_ped hei3_millie_car
					OR IS_CHAR_TOUCHING_VEHICLE scplayer hei3_millie_car	
					OR HAS_CAR_BEEN_DAMAGED_BY_CHAR hei3_millie_car scplayer	
						hei3_spook = 100						
						IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
							CLEAR_PRINTS
							DO_FADE 1000 FADE_OUT
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE 	
								GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
								BUILD_PLAYER_MODEL player1

							DO_FADE 1000 FADE_IN
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE
						ENDIF
						PRINT_NOW (HEI3_31) 5000 1 //"Something's spooked the croupier
						GOTO mission_heist3_failed
					ENDIF
					
													
					///////////////////////////
					/// end of spook checks ///
					///////////////////////////


				ENDIF
			ELSE
				// cut-scene of millie entering house //
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2037.8907 2749.5339 9.8125 4.0 4.0 4.0 TRUE 	
					
					
					REMOVE_BLIP hei3_millie_blip
					CLEAR_AREA 2034.7939 2724.4646 9.8281 20.0 TRUE
					CLEAR_ONSCREEN_COUNTER hei3_spook
					SET_PLAYER_CONTROL Player1 OFF
					SWITCH_WIDESCREEN ON
					CLEAR_PRINTS
					
					
					SET_CAR_COORDINATES hei3_millie_car 2035.0103 2725.3604 9.8281 
					SET_CAR_HEADING hei3_millie_car 184.0343
					
					//LOAD_SCENE_IN_DIRECTION	 2036.0509 2734.5022 9.8203 180.1103

					//SET_FIXED_CAMERA_POSITION 2039.2190 2751.5876 11.7534  0.0 0.0 0.0
					//POINT_CAMERA_AT_POINT 2038.7681 2750.7056 11.6163 JUMP_CUT
					//LOAD_SCENE_IN_DIRECTION	 2041.2056 2753.3386 11.9601 146.1103
					SET_FIXED_CAMERA_POSITION 2041.2056 2753.3386 11.9601 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 2040.6478 2752.5151 11.8569 JUMP_CUT

					
					
					SKIP_CUTSCENE_START
					
					
					
					IF NOT IS_CHAR_DEAD hei3_millie_ped
						OPEN_SEQUENCE_TASK hei3_millie_seq									
						IF IS_CHAR_IN_ANY_CAR hei3_millie_ped
							TASK_LEAVE_ANY_CAR -1
						ENDIF
						TASK_GO_STRAIGHT_TO_COORD -1 2037.4675 2724.1423 9.8281 PEDMOVE_WALK -1
						TASK_GO_STRAIGHT_TO_COORD -1 2037.3781 2721.4490 10.5469 PEDMOVE_WALK -1				
						CLOSE_SEQUENCE_TASK hei3_millie_seq
						PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
						CLEAR_SEQUENCE_TASK hei3_millie_seq
					ENDIF

					hei3_safety_flag = 0 
					TIMERA = 0
					WHILE hei3_safety_flag = 0
						IF NOT IS_CHAR_DEAD hei3_millie_ped
							GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
							IF  hei3_task_status  = FINISHED_TASK
								hei3_safety_flag = 1
							ENDIF
						ELSE
							hei3_safety_flag = 1
						ENDIF

					WAIT 0
					ENDWHILE 

					SKIP_CUTSCENE_END

		
		
												
					
		
					CLEAR_AREA 1936.1964 2734.0112  9.8203 2.0 TRUE
					CREATE_CHAR PEDTYPE_MISSION2 CWMYHB1 1999.7885 2733.6919  9.8203 hei3_client_ped
					SET_CHAR_HEADING hei3_client_ped 354.8084
					GIVE_WEAPON_TO_CHAR hei3_client_ped WEAPONTYPE_DILDO1 0
					SET_CURRENT_CHAR_WEAPON hei3_client_ped   WEAPONTYPE_DILDO1												      
			  
					//2037.3632 2721.7859 10.2989 181.4607 // door to house
					
					OPEN_SEQUENCE_TASK hei3_client_seq
					TASK_GO_STRAIGHT_TO_COORD -1 2021.5941 2734.5757 9.8203 PEDMOVE_WALK -2 
					TASK_GO_STRAIGHT_TO_COORD -1 2029.7864 2730.5393 9.8281 PEDMOVE_WALK -2 
					TASK_GO_STRAIGHT_TO_COORD -1 2038.5051 2729.8464 9.8281 PEDMOVE_WALK -2 					
					TASK_GO_STRAIGHT_TO_COORD -1 2037.2797 2722.5671 9.8281 PEDMOVE_WALK -2 //185.0746				
					CLOSE_SEQUENCE_TASK hei3_client_seq
					PERFORM_SEQUENCE_TASK hei3_client_ped hei3_client_seq
					CLEAR_SEQUENCE_TASK hei3_client_seq		 		
															

					RESTORE_CAMERA_JUMPCUT					
					
					DELETE_CHAR hei3_millie_ped
					MARK_CHAR_AS_NO_LONGER_NEEDED hei3_millie_ped
					MARK_MODEL_AS_NO_LONGER_NEEDED CROGRL3
					SET_PLAYER_CONTROL Player1 ON
					SWITCH_WIDESCREEN OFF
					CLEAR_PRINTS
					//PRINT (HEI3_23) 5000 1//~s~Get rid of the ~r~gimp~s~.
					PRINT (HEI3_08) 8000 1 //~s~Here comes the ~r~gimp~s~ make sure he doesn't reach the ~y~house~s~. 
					GET_GAME_TIMER hei3_timer_start
					hei3_progress_flag = 8
					//hei3_progress_flag = 7
			    ENDIF
			ENDIF
		ELSE
			CLEAR_PRINTS
			
			IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 	
					GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
					BUILD_PLAYER_MODEL player1

				DO_FADE 1000 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
			ENDIF
			PRINT (HEI3_18) 5000 1 //~r~You destroyed her car, she's not going to help you now!
			GOTO mission_heist3_failed		
		ENDIF
	ELSE
		CLEAR_PRINTS
		IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 	
				GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
				BUILD_PLAYER_MODEL player1

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
		ENDIF
		PRINT (HEI3_14) 5000 1 //~r~You killed the croupier!
		GOTO mission_heist3_failed	
	ENDIF		

ENDIF



//////////////////////////////
/// PLAYER FIGHTING CLIENT ///
//////////////////////////////
IF hei3_progress_flag = 8
	IF NOT IS_CHAR_DEAD hei3_client_ped	    
		
		
		IF LOCATE_CHAR_ANY_MEANS_3D  hei3_client_ped  2037.3120  2722.2881  10.8281  1.2000  1.6000  3.0000   FALSE
		   CLEAR_PRINTS
			REMOVE_CHAR_ELEGANTLY hei3_client_ped
			IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE 	
					GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
					BUILD_PLAYER_MODEL player1

				DO_FADE 1000 FADE_IN
				WHILE GET_FADING_STATUS
				    WAIT 0
				ENDWHILE
			ENDIF
			
			PRINT HEI3_92  5000 1 //~r~The gimp has reached the house!			
			
			GOTO mission_heist3_failed
		ENDIF
		
		GET_SCRIPT_TASK_STATUS hei3_client_ped PERFORM_SEQUENCE_TASK hei3_task_status					

		

		IF hei3_task_status = PERFORMING_TASK
			GET_SEQUENCE_PROGRESS hei3_client_ped hei3_task_status
			IF hei3_task_status = 2
				CLEAR_PRINTS
				PRINT  HEI3_93 5000 1 //~s~The gimp has nearly reached the house!
				//(HEI3_12) 5000 1 //~s~The ~r~gimp~s~ is about to get away, stop him!
			ENDIF
		ELSE
			IF hei3_task_status = FINISHED_TASK
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer hei3_client_ped 100.0 100.0 20.0 FALSE
					IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE 	
							GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
							BUILD_PLAYER_MODEL player1

						DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
						    WAIT 0
						ENDWHILE
						CLEAR_PRINTS
						PRINT (HEI3_15) 5000 1 //~r~The gimp got away!
					ENDIF	
				ENDIF
			ENDIF
		ENDIF
		//ENDIF
	ELSE
		REMOVE_BLIP hei3_client_blip
		CLEAR_PRINTS
		PRINT (HEI3_22) 5000 1//~s~Now that the gimp's out fo the way go and ring the ~y~doorbell~s~.
		ADD_BLIP_FOR_COORD hei3_house_x hei3_house_y hei3_house_z hei3_house_blip
		hei3_progress_flag = 9
	ENDIF
ENDIF

		   


//////////////////////////////////////////////////////////////////////
////////////////////// END OF CHANGES/////////////////////////////////
//////////////////////////////////////////////////////////////////////






//////////////////////////////////
/// PLAYER HAS KILLED THE GIMP ///
//////////////////////////////////
											  
IF hei3_progress_flag = 9
	IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer 2037.3070 2722.1057 10.2989 1.2 1.2 3.0 TRUE	  //2037.3191 2721.8359 10.2989
		CLEAR_PRINTS
		REMOVE_BLIP hei3_house_blip

		SWITCH_WIDESCREEN ON
		SET_PLAYER_CONTROL Player1 OFF

		SET_CHAR_COORDINATES scplayer 2037.1649 2721.8945 10.2989 
		SET_CHAR_HEADING scplayer 174.4355
		
		SET_FIXED_CAMERA_POSITION 2035.66 2722.75 11.84 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 2039.39 2717.31 12.20 JUMP_CUT

		
		
					hei3_audio_counter	= 0
					hei3_audio_slot1 	   = 1
					hei3_audio_slot2 	   = 2
					hei3_audio_playing	   = 0
					hei3_text_loop_done	   = 0
					hei3_mobile			   = 0
					hei3_text_timer_diff   = 0
					hei3_text_timer_end    = 0
					hei3_text_timer_start  = 0
					hei3_ahead_counter	   = 0
					
					hei3_sfx_counter = 2
					hei3_sfx_playing = 0 					
					hei3_sfx_played = 0

					hei3_text_loop6:
					
					WAIT 0 

					IF hei3_text_loop_done = 0
						
						IF NOT hei3_sfx_counter = 0
							IF hei3_sfx_playing = 0
								IF HAS_MISSION_AUDIO_LOADED 3
									CLEAR_MISSION_AUDIO 3
								ENDIF
								hei3_sfx_playing = 1
							ENDIF

							IF hei3_sfx_playing = 1
								LOAD_MISSION_AUDIO 3 hei3_sfx[hei3_sfx_counter]	 // audio fx to be used
								hei3_sfx_playing = 2
							ENDIF

							IF hei3_sfx_playing = 2
							 	IF HAS_MISSION_AUDIO_LOADED 3
									PLAY_MISSION_AUDIO 3				
									hei3_sfx_playing = 3
								ENDIF
							ENDIF

							IF hei3_sfx_playing = 3
								IF HAS_MISSION_AUDIO_FINISHED 3								
									hei3_sfx_counter = 0
									hei3_sfx_playing = 0
									hei3_safety_flag = 1
									TIMERA = 0			
								ENDIF
							ENDIF
						ENDIF


						IF NOT hei3_audio_counter = 0
							IF hei3_audio_playing = 0
								IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
									CLEAR_MISSION_AUDIO hei3_audio_slot2
								ENDIF
								hei3_audio_playing = 1
							ENDIF

							IF hei3_audio_playing = 1
								LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
								hei3_audio_playing = 2
							ENDIF

							IF hei3_audio_playing = 2
							 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
									PLAY_MISSION_AUDIO hei3_audio_slot1
									PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
									hei3_audio_playing = 3
								ENDIF
							ENDIF

							IF hei3_audio_playing = 3
								IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
									IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped									 	
									ENDIF
									CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
									IF hei3_audio_slot1 = 1
										hei3_audio_slot1 = 2
										hei3_audio_slot2 = 1
									ELSE
										hei3_audio_slot1 = 1
										hei3_audio_slot2 = 2
									ENDIF
									hei3_audio_counter = 0
									hei3_audio_playing = 0
									hei3_text_loop_done = 1								
								ENDIF
							ENDIF
						ENDIF
						
						
						IF hei3_audio_playing = 0
							IF hei3_sfx_playing = 0
								IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped
									 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
								ENDIF
								hei3_audio_counter = 12	//PRINT (HE3_DA) 5000 1 //CROUPIER:"The door's open Master!"
							ENDIF
						ENDIF
																						
					

						
						GOTO hei3_text_loop6
					ENDIF
		//TASK_SHAKE_FIST scplayer
		
		
		WHILE IS_MESSAGE_BEING_DISPLAYED
		WAIT 0 
		ENDWHILE
		
											
		
		DO_FADE 1000 FADE_OUT
		WHILE GET_FADING_STATUS
		WAIT 0		
		ENDWHILE
		SET_AREA_VISIBLE 6

		LOAD_SPECIAL_CHARACTER 1 CROGRL2 					
		//REQUEST_ANIMATION SNM
		
		WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		//OR NOT HAS_ANIMATION_LOADED SNM
		WAIT 0 
		ENDWHILE
		
		
		REQUEST_COLLISION 344.8872 305.4298
		LOAD_SCENE 344.8872 305.4298 998.2638
		DISPLAY_RADAR FALSE

		SET_CHAR_COORDINATES scplayer 344.4120 305.0330 998.1484 
		SET_CHAR_HEADING scplayer 296.2830
			
		
		MARK_CHAR_AS_NO_LONGER_NEEDED hei3_client_ped
		MARK_CAR_AS_NO_LONGER_NEEDED hei3_client_car
		MARK_CAR_AS_NO_LONGER_NEEDED hei3_millie_car
		MARK_MODEL_AS_NO_LONGER_NEEDED 	CWMYHB1		 		
		MARK_MODEL_AS_NO_LONGER_NEEDED ELEGANT
		MARK_MODEL_AS_NO_LONGER_NEEDED CLUB

		CLEAR_CHAR_TASKS scplayer 
		CLEAR_PRINTS
	   		
		//CREATE_CHAR PEDTYPE_SPECIAL WFYSEX 349.1921 308.5705 998.1484 hei3_millie_ped	
		CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 349.1921 308.5705 998.1484 hei3_millie_ped 

		SET_CHAR_DECISION_MAKER hei3_millie_ped hei3_decision
		SET_CHAR_AREA_VISIBLE hei3_millie_ped 6
		SET_CHAR_AREA_VISIBLE scplayer 6
		SET_ANIM_GROUP_FOR_CHAR hei3_millie_ped sexywoman
		SET_CHAR_HEADING hei3_millie_ped 147.2450		
		
				 				
		
		//SET_FIXED_CAMERA_POSITION 348.52 308.21 999.58 0.0 0.0 0.0
		//POINT_CAMERA_AT_POINT 356.32 312.76 1000.65	JUMP_CUT			
		SET_FIXED_CAMERA_POSITION 348.8104 310.0784 999.4993 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT 348.6377 309.0935 999.5004 JUMP_CUT


		SET_CHAR_COORDINATES scplayer 344.4120 305.0330 998.1484 
		SET_CHAR_HEADING scplayer 296.2830
		SET_CHAR_COORDINATES hei3_millie_ped 349.1921 308.5705 998.1484
		SET_CHAR_HEADING hei3_millie_ped 147.2450
		TASK_TURN_CHAR_TO_FACE_CHAR scplayer hei3_millie_ped

		WAIT 1000

		DO_FADE 1000 FADE_IN

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		hei3_progress_flag = 10
	ENDIF
ENDIF

///////////////////////////////////////////////////////////////////
/// CHECK PLAYER IS WEARING THE GIME SUIT (fail mission if not) ///
///////////////////////////////////////////////////////////////////


IF hei3_progress_flag >= 5
AND hei3_progress_flag < 9
	GET_AREA_VISIBLE hei3_temp_int
	IF hei3_temp_int = 0
		IF NOT IS_PLAYER_WEARING player1	CLOTHES_TEX_EXTRA1	gimpleg		
		   	PRINT (HEI3_34)  5000 1 //" ~r~You no longer have the gimp suit. "	   	  
		   	GOTO mission_heist3_failed
		ENDIF
	ENDIF
ENDIF
//////////////////////////////////////////
/// PLAYER WHIPPING MILLIE INTRO SCENE ///
//////////////////////////////////////////
IF hei3_progress_flag = 10
	//SET_FIXED_CAMERA_POSITION 348.95 310.06 999.62 0.0 0.0 0.0
	//POINT_CAMERA_AT_POINT 346.34 298.35 999.28	JUMP_CUT
	
	
	SKIP_CUTSCENE_START
	CLEAR_PRINTS
	
	
	////////////////////////////////////////
	//////////////// AUDIO /////////////////
					hei3_audio_counter	= 0
					hei3_audio_slot1 	   = 1
					hei3_audio_slot2 	   = 2
					hei3_audio_playing	   = 0
					hei3_text_loop_done	   = 0
					hei3_mobile			   = 0
					hei3_text_timer_diff   = 0
					hei3_text_timer_end    = 0
					hei3_text_timer_start  = 0
					hei3_ahead_counter	   = 0
					
					hei3_text_loop4:
					
					WAIT 0 

					IF hei3_text_loop_done = 0
						
						
						IF NOT hei3_audio_counter = 0
							IF hei3_audio_playing = 0
								IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
									CLEAR_MISSION_AUDIO hei3_audio_slot2
								ENDIF
								hei3_audio_playing = 1
							ENDIF

							IF hei3_audio_playing = 1
								LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
								hei3_audio_playing = 2
							ENDIF

							IF hei3_audio_playing = 2
							 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
									PLAY_MISSION_AUDIO hei3_audio_slot1
									PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
									hei3_audio_playing = 3
								ENDIF
							ENDIF

							IF hei3_audio_playing = 3
								IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
									CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
									IF hei3_audio_slot1 = 1
										hei3_audio_slot1 = 2
										hei3_audio_slot2 = 1
									ELSE
										hei3_audio_slot1 = 1
										hei3_audio_slot2 = 2
									ENDIF
									hei3_audio_counter = 0
									hei3_audio_playing = 0
								ELSE
									IF NOT HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
										IF hei3_audio_counter < 8
											hei3_ahead_counter = hei3_audio_counter + 1
											LOAD_MISSION_AUDIO hei3_audio_slot2 hei3_audio[hei3_ahead_counter]
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					
					
					
					SWITCH hei3_mobile
						CASE 0
							IF hei3_audio_playing = 0	
								IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped
									 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
								ENDIF
								hei3_audio_counter = 13//PRINT (HEI3_DB) 5000 1 //CROUPIER:"Come on in, I'm ready for you!"									  	
								hei3_mobile = 1
								GET_GAME_TIMER hei3_text_timer_start
							ENDIF
						BREAK
							
						
						
						
						CASE 1
							
							GET_GAME_TIMER hei3_text_timer_end
							hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
							IF hei3_text_timer_diff > 1000
								IF hei3_audio_playing = 0
									IF NOT IS_CHAR_DEAD hei3_millie_ped
										OPEN_SEQUENCE_TASK hei3_player_seq
										TASK_LOOK_AT_CHAR -1 hei3_millie_ped 20000
										TASK_GO_STRAIGHT_TO_COORD -1 347.0207 306.2751 998.2088     PEDMOVE_WALK -1 // player walks here from doorway 	 
										TASK_GO_STRAIGHT_TO_COORD -1 348.2744 307.7908 998.2040     PEDMOVE_WALK -1 // player walks here from doorway 	 
										TASK_TURN_CHAR_TO_FACE_CHAR -1 hei3_millie_ped
										CLOSE_SEQUENCE_TASK hei3_player_seq
										PERFORM_SEQUENCE_TASK scplayer hei3_player_seq
										CLEAR_SEQUENCE_TASK hei3_player_seq																	
									ENDIF
									IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped
									 	START_CHAR_FACIAL_TALK scplayer	 3000
									ENDIF
									hei3_audio_counter = 14//PRINT (HE3_DC) 5000 1 //CARL:"You've been a naughty girl!"
									hei3_mobile = 2
									GET_GAME_TIMER hei3_text_timer_start
								ENDIF
							ENDIF
							BREAK
						CASE 2
							GET_GAME_TIMER hei3_text_timer_end
							hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
							IF hei3_text_timer_diff > 1000
								IF hei3_audio_playing = 0
									IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK scplayer
									 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
									ENDIF
									hei3_audio_counter = 15//PRINT (HE3_DD) 5000 1 //CROUPIER:"Oh I know, I know!"
									hei3_mobile = 3
									GET_GAME_TIMER hei3_text_timer_start
								ENDIF
							ENDIF	
							BREAK
						CASE 3
							GET_GAME_TIMER hei3_text_timer_end
							hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
							IF hei3_text_timer_diff > 1000
								IF hei3_audio_playing = 0
									hei3_safety_flag = 0
									hei3_temp_int = 0 
									TIMERA = 0
									WHILE hei3_safety_flag = 0
										IF NOT IS_CHAR_DEAD hei3_millie_ped
											GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK hei3_task_status
											IF  hei3_task_status  = FINISHED_TASK
											    IF NOT IS_MESSAGE_BEING_DISPLAYED													
													hei3_safety_flag = 1									
												ENDIF		
											ENDIF
										ELSE
											hei3_safety_flag = 1
										ENDIF

									WAIT 0
									ENDWHILE
									IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped
									 	START_CHAR_FACIAL_TALK scplayer	 3000
									ENDIF
									hei3_audio_counter = 25 //PRINT (HE3_FD) 5000 1 //CARL:"Spit it out, you filthy worm!"
									hei3_mobile = 4
									GET_GAME_TIMER hei3_text_timer_start
								ENDIF
							ENDIF
							BREAK
						CASE 4
							GET_GAME_TIMER hei3_text_timer_end
							hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
							IF hei3_text_timer_diff > 1000
								IF hei3_audio_playing = 0
									IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK scplayer
									 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
										ENDIF
									hei3_audio_counter = 21 // PRINT (HEI3_EC) 5000 1 //CROUPIER:"You can't break me!"	
									IF NOT IS_CHAR_DEAD hei3_millie_ped
										OPEN_SEQUENCE_TASK hei3_millie_seq			
										TASK_GO_STRAIGHT_TO_COORD -1 344.6502 307.6453 998.1963  PEDMOVE_WALK -1
										TASK_ACHIEVE_HEADING -1 245.3258
										//TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer  
										CLOSE_SEQUENCE_TASK hei3_millie_seq
										PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
										CLEAR_SEQUENCE_TASK hei3_millie_seq							
									ENDIF
									hei3_mobile = 5 
									GET_GAME_TIMER hei3_text_timer_start
								ENDIF
							ENDIF
							BREAK
						CASE 5
							GET_GAME_TIMER hei3_text_timer_end
							hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
							IF hei3_text_timer_diff > 1000
								IF hei3_audio_playing = 0
									hei3_safety_flag = 0
									hei3_temp_int = 0
									WHILE hei3_safety_flag = 0
									WAIT  0
										IF NOT IS_CHAR_DEAD hei3_millie_ped
											GET_SCRIPT_TASK_STATUS hei3_millie_ped PERFORM_SEQUENCE_TASK hei3_task_status
											 
											
											IF hei3_task_status = FINISHED_TASK																							
													TASK_LOOK_AT_CHAR hei3_millie_ped scplayer 10000
													hei3_safety_flag = 1
											ELSE												
												IF hei3_temp_int = 0
													SET_FIXED_CAMERA_POSITION 347.32 308.43 999.52  0.0 0.0 0.0
													POINT_CAMERA_AT_POINT 339.91 301.08 998.31 JUMP_CUT
																											
													OPEN_SEQUENCE_TASK hei3_player_seq								
													TASK_TURN_CHAR_TO_FACE_CHAR -1 hei3_millie_ped  				
													TASK_GO_STRAIGHT_TO_COORD -1 345.7446 304.2489 998.1641 PEDMOVE_WALK -1//355.4746
													TASK_TURN_CHAR_TO_FACE_CHAR -1 hei3_millie_ped  				
													CLOSE_SEQUENCE_TASK hei3_player_seq
													PERFORM_SEQUENCE_TASK scplayer hei3_player_seq
													CLEAR_SEQUENCE_TASK hei3_player_seq
													hei3_temp_int =1
												ENDIF
											ENDIF
											
										ELSE
											hei3_safety_flag = 1
										ENDIF						 
									ENDWHILE									
									IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped
									 	START_CHAR_FACIAL_TALK scplayer	 3000
										ENDIF
									hei3_audio_counter = 26 //PRINT (HE3_FE) 5000 1 //CARL:"If you're good I'll punish you more!"
									hei3_mobile = 6 
									GET_GAME_TIMER hei3_text_timer_start
								ENDIF
							ENDIF
							BREAK
						CASE 6
							GET_GAME_TIMER hei3_text_timer_end
							hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
							IF hei3_text_timer_diff > 1000
								IF hei3_audio_playing = 0
									IF NOT IS_CHAR_DEAD hei3_millie_ped
										OPEN_SEQUENCE_TASK hei3_millie_seq
										TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer  // millie ends cut-scene here
										TASK_GO_STRAIGHT_TO_COORD -1 345.3145 305.0605 998.1641 PEDMOVE_WALK -1
										TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer  // millie ends cut-scene here
										CLOSE_SEQUENCE_TASK hei3_millie_seq
										PERFORM_SEQUENCE_TASK hei3_millie_ped hei3_millie_seq
										CLEAR_SEQUENCE_TASK hei3_millie_seq
									ENDIF
									IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK scplayer
									 	START_CHAR_FACIAL_TALK hei3_millie_ped	 3000
									ENDIF
									hei3_audio_counter = 17 //PRINT (HE3_DF) 5000 1 //CROUPIER:"Oh Benny, you minx!"	
									hei3_mobile = 7 
									GET_GAME_TIMER hei3_text_timer_start
								ENDIF
							ENDIF
							BREAK


						
						DEFAULT
							GET_GAME_TIMER hei3_text_timer_end
							hei3_text_timer_diff = hei3_text_timer_end - hei3_text_timer_start
							IF hei3_text_timer_diff > 1000
								IF hei3_audio_playing = 0
								   IF NOT IS_CHAR_DEAD	hei3_millie_ped
										STOP_CHAR_FACIAL_TALK hei3_millie_ped									 	
									ENDIF
								   hei3_text_loop_done = 1	
								ENDIF
							ENDIF
							BREAK
					ENDSWITCH
					GOTO hei3_text_loop4
				ENDIF



	//////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////																		  								
																																		

		SKIP_CUTSCENE_END
		IF NOT IS_CHAR_DEAD	hei3_millie_ped
			STOP_CHAR_FACIAL_TALK hei3_millie_ped
		 	STOP_CHAR_FACIAL_TALK scplayer
		ENDIF
		CLEAR_PRINTS
		CLEAR_LOOK_AT scplayer

	
	//SWITCH_WIDESCREEN OFF
	
	hei3_x_button_can_be_pressed = 0
	hei3_iGirlFriend = MILLIE
	hei3_iSpankingFlag = TRUE
	hei3_progress_flag = 11

ENDIF



	///////////////////////////////
	/// whipping goes in here ? ///
	///////////////////////////////
IF hei3_progress_flag = 11			
	
	IF hei3_x_button_can_be_pressed = 0		
		
		
		GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT GF_Sex.sc number_of_instances_of_streamed_script
		
		IF number_of_instances_of_streamed_script = 0
			STREAM_SCRIPT GF_Sex.sc
			
			IF HAS_STREAMED_SCRIPT_LOADED GF_Sex.sc
				iDateReport = 0		
				SET_BIT iDateReport SEX_IN_PROGRESS
				DISABLE_ALL_ENTRY_EXITS TRUE
				START_NEW_STREAMED_SCRIPT GF_Sex.sc	hei3_iGirlFriend hei3_iSpankingFlag  0 2037.6515 2723.8711 9.8352 0.0702
				DO_FADE 0 FADE_OUT
				DELETE_CHAR hei3_millie_ped
				MARK_CHAR_AS_NO_LONGER_NEEDED hei3_millie_ped
				hei3_x_button_can_be_pressed = 1
			ENDIF
		ENDIF			
	ENDIF
		
		
		//START_NEW_SCRIPT GF_Sex hei3_iGirlFriend hei3_iSpankingFlag  6 345.7446 304.2489 998.1641 97.9726
		//START_NEW_SCRIPT GF_Sex hei3_iGirlFriend hei3_iSpankingFlag  0 2037.6515 2723.8711 9.8352 0.0702
		
		
		
	
	IF hei3_x_button_can_be_pressed = 1
		IF IS_BIT_SET iDateReport SEX_WAS_GOOD
			hei3_pleased_millie	= 1
			//
		ENDIF


		IF NOT IS_BIT_SET iDateReport SEX_IN_PROGRESS
			hei3_progress_flag = 12
			/*
			DO_FADE 1500 FADE_OUT
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			*/
		ENDIF
	ENDIF

	/*
	WHILE  IS_BIT_SET iDateReport SEX_IN_PROGRESS
      WAIT 0
	ENDWHILE
	hei3_progress_flag = 12
	*/	
ENDIF

///////////////////////////////////////////////////////////////
/// have player walking down the street away from the house ///
///////////////////////////////////////////////////////////////



IF hei3_progress_flag = 12		
	/*
	SET_AREA_VISIBLE 0
	CLEAR_AREA 	2036.8745 2734.2817 9.8203 50.0 TRUE
	LOAD_SCENE  2036.8745 2734.2817 9.8203
	
	SET_CHAR_COORDINATES scplayer 2037.6515 2723.8711 9.8352 
	SET_CHAR_HEADING scplayer 0.0702 
	*/
	
	
	
	SET_FIXED_CAMERA_POSITION 2033.2738 2719.8879 10.2275 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2033.7267 2720.7737 10.3282  JUMP_CUT
	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL Player1 OFF
	DISABLE_ALL_ENTRY_EXITS FALSE
	
	//RESTORE_CLOTHES_STATE
	//BUILD_PLAYER_MODEL player1

	
	/*
	DO_FADE 1500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	*/
	SKIP_CUTSCENE_START
	
	OPEN_SEQUENCE_TASK hei3_player_seq
	TASK_GO_STRAIGHT_TO_COORD -1 2036.8745 2734.2817 9.8203 PEDMOVE_WALK -1
	TASK_GO_STRAIGHT_TO_COORD -1 2029.5447 2735.6282 9.8203  PEDMOVE_WALK -1
	CLOSE_SEQUENCE_TASK hei3_player_seq
	PERFORM_SEQUENCE_TASK scplayer hei3_player_seq
	CLEAR_SEQUENCE_TASK hei3_player_seq

	IF hei3_pleased_millie = 1
			CLEAR_HELP
			PRINT_HELP_FOREVER HEI3_95  // You have another girlfriend. You will have to wine and dine her before she will give you the keycard.
			hei3_audio_counter	= 0
			hei3_audio_slot1 	   = 1
			hei3_audio_slot2 	   = 2
			hei3_audio_playing	   = 0
			hei3_text_loop_done	   = 0
			hei3_mobile			   = 0
			hei3_text_timer_diff   = 0
			hei3_text_timer_end    = 0
			hei3_text_timer_start  = 0
			hei3_ahead_counter	   = 0

/*
			hei3_text_loop5:
			   WAIT 0 		
				IF hei3_text_loop_done	   = 0
					IF NOT hei3_audio_counter = 0
						IF hei3_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot2
								CLEAR_MISSION_AUDIO hei3_audio_slot2
							ENDIF
							hei3_audio_playing = 1
						ENDIF

						IF hei3_audio_playing = 1
							LOAD_MISSION_AUDIO hei3_audio_slot1 hei3_audio[hei3_audio_counter]
							hei3_audio_playing = 2
						ENDIF

						IF hei3_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED hei3_audio_slot1
								PLAY_MISSION_AUDIO hei3_audio_slot1
								PRINT_NOW $hei3_text[hei3_audio_counter] 10000 1
								hei3_audio_playing = 3
							ENDIF
						ENDIF

						IF hei3_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED hei3_audio_slot1
								CLEAR_THIS_PRINT $hei3_text[hei3_audio_counter]
								IF hei3_audio_slot1 = 1
									hei3_audio_slot1 = 2
									hei3_audio_slot2 = 1
								ELSE
									hei3_audio_slot1 = 1
									hei3_audio_slot2 = 2
								ENDIF
								hei3_audio_counter = 0
								hei3_audio_playing = 0
								hei3_text_loop_done	   = 1																
							ENDIF
						ENDIF
					ENDIF

					
					IF hei3_audio_playing = 0
						hei3_text_loop_done	   = 1																
						//hei3_audio_counter = 42	//PRINT (HE3_HJ) 5000 1 //CROUIPER:(from inside) "I'll call you master, I promise!"	
					ENDIF
				
				GOTO hei3_text_loop5
				
				ENDIF */								
	ENDIF
	
	hei3_safety_flag = 0
	TIMERA = 0
   	WHILE hei3_safety_flag = 0
    	
    	IF  hei3_pleased_millie = 1
	    	IF NOT IS_MESSAGE_BEING_DISPLAYED
			   	IF TIMERA  > 5000
			   	hei3_safety_flag = 1
				ENDIF
			ENDIF
		ELSE
			IF TIMERA  > 7000
				hei3_safety_flag = 1
			ENDIF
		ENDIF
		
	WAIT 0 
	ENDWHILE 
	
	SKIP_CUTSCENE_END
	CLEAR_CHAR_TASKS scplayer
	SET_CHAR_COORDINATES scplayer 2029.5447 2735.6282 9.8203
	SET_CHAR_HEADING scplayer 68.4474
	RESTORE_CAMERA_JUMPCUT
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL Player1 ON
	CLEAR_HELP 	
	IF hei3_pleased_millie = 1
		SET_BIT iActiveGF MILLIE
		GOTO mission_heist3_passed
	ELSE
		CLEAR_PRINTS
		IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	gimpleg
			DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE 	
				GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
				BUILD_PLAYER_MODEL player1

			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
		ENDIF
		
		PRINT (HEI3_24) 5000 1 //~r~You failed to excite her.
		GOTO mission_heist3_failed
	ENDIF	
ENDIF
	 
//GOTO mission_heist3_passed

GOTO heist3_main_loop

///////////////////////
/// EXTRA FUNCTIONS ///
///////////////////////

///////////////////////////////////
/// Mission FAIL PASSED CLEANUP ///
///////////////////////////////////

	
// Mission XXXX 1 failed

mission_heist3_failed:

PRINT_BIG M_FAIL 5000 1
RETURN

// mission XXXX 1 passed

mission_heist3_passed:


//flag_XXXX_mission1_passed = 1
flag_heist_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
flag_player_got_gimp_suit = 1
//PRINT_WITH_NUMBER_BIG M_PASS 300 5000 1
//ADD_SCORE player1 300
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 15 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 15//amount of respect
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
REGISTER_MISSION_PASSED ( HEIST_3 )
PLAYER_MADE_PROGRESS 1

RETURN
		


// mission cleanup

mission_cleanup_heist3:

SWITCH_PED_ROADS_BACK_TO_ORIGINAL  2096.3352 1656.3969 8.0 2161.6587 1728.2869 10.0
SWITCH_ROADS_BACK_TO_ORIGINAL  2096.3352 1656.3969 8.0 2161.6587 1728.2869 10.0

MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED GF_Sex.sc

GET_GAME_TIMER timer_mobile_start
CLEAR_ONSCREEN_COUNTER hei3_spook

DISPLAY_RADAR TRUE
DISABLE_ALL_ENTRY_EXITS FALSE
//IF hei3_clothes_changed = 1
//IF IS_PLAYER_PLAYING player1
//RESTORE_CLOTHES_STATE
//BUILD_PLAYER_MODEL player1
//ENDIF
//ENDIF



MARK_MODEL_AS_NO_LONGER_NEEDED	BMYPIMP
MARK_MODEL_AS_NO_LONGER_NEEDED WFYSEX
MARK_MODEL_AS_NO_LONGER_NEEDED	BMOCHIL
MARK_MODEL_AS_NO_LONGER_NEEDED WMYST

MARK_MODEL_AS_NO_LONGER_NEEDED CROGRL3
MARK_MODEL_AS_NO_LONGER_NEEDED CLUB
MARK_MODEL_AS_NO_LONGER_NEEDED CWMYHB1
MARK_MODEL_AS_NO_LONGER_NEEDED MAFFA
MARK_MODEL_AS_NO_LONGER_NEEDED WINDSOR

MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
MARK_MODEL_AS_NO_LONGER_NEEDED GUN_DILDO1	


IF NOT IS_CAR_DEAD hei3_millie_car		
	LOCK_CAR_DOORS hei3_millie_car CARLOCK_UNLOCKED
ENDIF


MARK_CAR_AS_NO_LONGER_NEEDED hei3_millie_car
MARK_CAR_AS_NO_LONGER_NEEDED hei3_temp_car


MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped1
MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped2
MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped3
MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped4
MARK_CHAR_AS_NO_LONGER_NEEDED hei3_shop_ped5

REMOVE_CHAR_ELEGANTLY hei3_millie_ped
MARK_CHAR_AS_NO_LONGER_NEEDED hei3_millie_ped
MARK_CHAR_AS_NO_LONGER_NEEDED hei3_doorman_ped

REMOVE_PICKUP hei3_gimp_suit_pickup


REMOVE_BLIP hei3_millie_blip
REMOVE_BLIP hei3_sex_shop_blip
REMOVE_BLIP hei3_casino_blip
REMOVE_BLIP hei3_gimp_suit_blip
REMOVE_CAR_RECORDING 803

//REMOVE_ANIMATION SNM

UNLOAD_SPECIAL_CHARACTER 1
REMOVE_DECISION_MAKER hei3_decision

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN
	
/*

PRINT (HEI3_01) 5000 1 //~s~The ~r~Croupier ~s~has a keycard and knows the code, follow her.
PRINT (HEI3_02) 5000 1 //~s~If you get too close you'll spook her, but she gets too far ahead you'll lose her.
PRINT (HEI3_03) 5000 1 //~s~She's gone into the sex ~y~shop~s~, follow her inside.
PRINT (HEI3_04) 5000 1 //~s~Go and get a gimp suit.
PRINT (HEI3_05) 5000 1 //~s~Wait for the ~r~gimp ~s~to arrive, then take him out and take his place.
PRINT (HEI3_06) 5000 1 //~s~Enter the ~y~house~s~.
PRINT (HEI3_07) 5000 1 //~s~The ~r~gimps ~s~making a run for it, chase him down!
PRINT (HEI3_08) 5000 1 //~s~Here comes the ~r~gimp~s~ make sure he doesn't reach the ~y~house~s~.
PRINT (HEI3_09) 5000 1 //~s~The ~r~Croupier~s~ has left the shop. Follow her but remember not to get too close.
PRINT (HEI3_10) 5000 1 //~s~The ~r~Croupier~s~ is almost out of sight, you need to get closer!
PRINT (HEI3_11) 5000 1 //~s~You're too close, back off before you spook her!
PRINT (HEI3_12) 5000 1 //~s~The ~r~gimp~s~ is about to get away, stop him!
PRINT (HEI3_13) 5000 1 //~s~Get the keycard from her ~r~car~s~.
PRINT (HEI3_14) 5000 1 //~r~You killed the croupier!
PRINT (HEI3_15) 5000 1 //~r~The gimp got away!
PRINT (HEI3_16) 5000 1 //~r~You lost her!
PRINT (HEI3_17) 5000 1 //~r~You got too close!
PRINT (HEI3_18) 5000 1 //~r~You destroyed her car, she's not going to help you now!
PRINT (HEI3_19) 5000 1 //~r~You destroyed her car and the keycard!

PRINT (HEI3_21) 5000 1 //~s~The ~r~gimp~s~ is on his way.
PRINT (HEI3_22) 5000 1 //~r~You didn't intercept the gimp!





PRINT (HEI3_AA) 5000 1 //There's only ever been one weak link in any security set-up - the human heart.

PRINT (HEI3_AB) 5000 1 //CROUPIER:"Another shift over! See you tomorrow!"
PRINT (HEI3_AC) 5000 1 //CROUPIER:"That was a long shift - see you tomorrow!"

PRINT (HEI3_BA) 5000 1 //CROUPIER:"Wow, it's nice and tight!"
PRINT (HEI3_BB) 5000 1 //"I'm sure you'll squeeze in just fine!"
PRINT (HEI3_BC) 5000 1 //"Let's have a look"


PRINT (HEI3_BD) 5000 1 //CROUPIER:"Perfect!"
PRINT (HEI3_BE) 5000 1 //CROUPIER:"Oh hi Benny..."
PRINT (HEI3_BF) 5000 1 //CROUPIER:"Yes master, I'm just trying it on."
PRINT (HEI3_BG) 5000 1 //CROUPIER:"You got yours?"
PRINT (HEI3_BH) 5000 1 //CROUPIER:"Cool, I'll see you at my house in a short while."
PRINT (HEI3_BI) 5000 1 //CROUPIER:"Byeee!"


PRINT (HEI3_CA) 5000 1 //CROUPIER:"The door's open Master!"


PRINT (HEI3_DA) 5000 1 //CROUPIER:"Come on in, I'm ready for you!"
PRINT (HEI3_DB) 5000 1 //CARL:"You've been a naughty girl!"
PRINT (HEI3_DC) 5000 1 //CROUPIER:"Oh I know, I know!"
PRINT (HEI3_DD) 5000 1 //CARL:"I want your security card and the entry code for Caligula's!"
PRINT (HEI3_DE) 5000 1 //CROUPIER:"Oh Benny, you minx!"
PRINT (HEI3_DF) 5000 1 //CROUPIER:"I'll never tell, you wicked man, never, NEVER!"


PRINT (HEI3_EA) 5000 1 //CARL:"Where's the keycard? Confess!"
PRINT (HEI3_EB) 5000 1 //CROUPIER:"You can't break me!"


PRINT (HEI3_FA) 5000 1 //CROUPIER:"Enough master!"
PRINT (HEI3_FB) 5000 1 //CROUPIER:"The card's in my car outside!"
PRINT (HEI3_FC) 5000 1 //CARL:"And now, the code..."
PRINT (HEI3_FD) 5000 1 //CARL:"Spit it out, you filthy worm!"
PRINT (HEI3_FE) 5000 1 //CARL:"If you're good I'll punish you more!"
PRINT (HEI3_FF) 5000 1 //CROUPIER:"I can take it! Do your worst!"


PRINT (HEI3_GA) 5000 1 //CROUPIER:"No more! You've broken me!"
PRINT (HEI3_GB) 5000 1 //CARL:"The code, you worthless trash!"
PRINT (HEI3_GC) 5000 1 //CROUPIER:"God you're good at this!"
PRINT (HEI3_GD) 5000 1 //CROUPIER:"I know you're not Benny - give me your number I have to see you again."
PRINT (HEI3_GE) 5000 1 //CARL:"ENTRY CODE!"
PRINT (HEI3_GF) 5000 1 //CROUPIER: Oh! It's...
PRINT (HEI3_GG) 5000 1 //CROUIPER: A.
PRINT (HEI3_GH) 5000 1 //CROUIPER: B.
PRINT (HEI3_GI) 5000 1 //CROUIPER: C
PRINT (HEI3_GJ) 5000 1 //CROUIPER: D.
PRINT (HEI3_GK) 5000 1 //CROUIPER: E.
PRINT (HEI3_GL) 5000 1 //CROUIPER: F.

PRINT (HEI3_HA) 5000 1 //CROUIPER:(from inside) "I'll call you master, I promise!"





*/	
	
	
	
	
	
		
}

