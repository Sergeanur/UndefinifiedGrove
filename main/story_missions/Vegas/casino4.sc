MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Casino 4 ******************************************
// *********************************** Paul & Maccer ***************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME CASINO4

//////////////////////////////////////// Mission start stuff\\\\\\\\\\\\\\\\\\\\\


/////////////////////////////request needed models


GOSUB mission_start_casino4

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_casino4_failed
ENDIF

GOSUB mission_cleanup_casino4

MISSION_END

{ 
/////////////////////////////////////// Variables for mission\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

LVAR_FLOAT cas4_car_x cas4_car_y cas4_car_z
LVAR_INT cas4_decision cas4_decision2 cas4_decision3
LVAR_FLOAT cas4_fight_x cas4_fight_y cas4_fight_z cas4_fight_heading


////////////////// FLAGS \\\\\\\\\\\\\\\\\\\\\\\\\\\
LVAR_INT cas4_paul_macca_created_flag            
LVAR_INT cas4_at_end_flag
LVAR_INT cas4_blip_added_flag
LVAR_INT cas4_player_reached_desert_flag
LVAR_INT cas4_car_created_flag
LVAR_INT cas4_played_cutscene_flag
LVAR_INT cas4_at_casino_flag 
LVAR_INT cas4_mission_progression_flag 
LVAR_INT cas4_snake_blip_flag
LVAR_INT cas4_safety_flag                          
LVAR_INT cas4_pig_in_car_flag 
LVAR_INT cas4_old_in_car_flag
LVAR_INT cas4_old_task_assigned   cas4_incest_task_assigned cas4_sister_task_assigned cas4_pig_task_assigned  cas4_snake_car_created_flag
LVAR_INT cas4_driver1_in_car cas4_driver2_in_car
LVAR_INT   cas4_crap_assigned
LVAR_INT cas4_sister_driving cas4_incest_driving




///////////////// BLIPS  \\\\\\\\\\\\\\\\\\\\\\\\\\\

LVAR_INT cas4_desert_blip
LVAR_INT cas4_paul_blip
LVAR_INT cas4_macca_blip
LVAR_INT cas4_end_blip

LVAR_INT cas4_snake_blip


///////////////// PEOPLE
LVAR_INT cas4_macca_ped
LVAR_INT cas4_paul_ped
LVAR_INT cas4_old_ped
LVAR_INT cas4_pig_ped
LVAR_INT cas4_incest_ped
LVAR_INT cas4_sister_ped




///////////////// VEHICLES \\\\\\\\\\\\\\\\\\\\\\\\\\
LVAR_INT cas4_player_car
LVAR_INT cas4_quad_car
LVAR_INT cas4_dune_car

////////////////// SEQUENCES \\\\\\\\\\\\\\\\\\\\\\\\\
LVAR_INT cas4_cutscene_macca_seq
LVAR_INT cas4_cutscene_paul_seq
LVAR_INT cas4_cutscene_player_seq
LVAR_INT cas4_old_seq
LVAR_INT cas4_pig_seq
LVAR_INT cas4_sister_seq
LVAR_INT cas4_incest_seq
LVAR_INT cas4_sister_drive_seq
LVAR_INT cas4_incest_drive_seq

LVAR_INT cas4_seq

LVAR_INT cas4_task_status
//////////////////// NUMBERS \\\\\\\\\\\\\\\\\\\
LVAR_FLOAT cas4_playerx
LVAR_FLOAT cas4_playery
LVAR_FLOAT cas4_playerz
LVAR_FLOAT cas4_animation_time
LVAR_FLOAT  cas4_carx
LVAR_FLOAT  cas4_cary
LVAR_FLOAT  cas4_carz
LVAR_INT cas4_hill_billy_stand_played 

////////////// HILL BILLY STAND OFF VARIABLES \\\\\\\\\\\\
LVAR_INT cas4_counter
LVAR_INT cas4_hill_billies_alive
LVAR_INT cas4_hill_billy_dead[4]
LVAR_INT cas4_hill_billy_blip[4]
LVAR_INT cas4_hill_billy_seq[4]
LVAR_INT cas4_temp_hill_billy[4]
LVAR_INT cas4_macca_fight_blip
LVAR_INT cas4_paul_fight_blip



//////////////////// EXTRAS ////////////////

LVAR_INT cas4_paul_with_player
LVAR_INT cas4_macca_with_player
LVAR_INT cas4_fighting
LVAR_INT cas4_vomit_status
LVAR_INT cas4_piss_status
LVAR_INT cas4_paul_busy
LVAR_INT cas4_macca_busy
LVAR_INT cas4_done_pisspuke
LVAR_INT cas4_done_piss cas4_done_puke


LVAR_INT cas4_both_with_player
LVAR_INT cas4_paul_told_to_enter 
LVAR_INT cas4_macca_told_to_enter
LVAR_INT cas4_message_dsplayed
LVAR_INT cas4_macca_playing_fx 	 
LVAR_INT cas4_paul_playing_fx


 

// coords

//LVAR_FLOAT cas4_dirx cas4_diry cas4_dirz


// other 
LVAR_INT cas4_free_seats 
LVAR_INT cas4_vomit	cas4_piss
 

///// AUDIO CRAP
VAR_TEXT_LABEL cas4_text[114]
LVAR_INT cas4_audio[114]
LVAR_INT cas4_audio_counter
LVAR_INT cas4_audio_slot1 cas4_audio_slot2 cas4_audio_playing
LVAR_INT cas4_text_loop_done
LVAR_INT cas4_mobile
LVAR_INT cas4_text_timer_diff 
LVAR_INT cas4_text_timer_end 
LVAR_INT cas4_text_timer_start
LVAR_INT cas4_ahead_counter
LVAR_INT cas4_banter1 cas4_banter1_completed
LVAR_INT cas4_banter2 cas4_banter2_completed
LVAR_INT cas4_banter3 cas4_banter3_completed
LVAR_INT cas4_banter4 cas4_banter4_completed
LVAR_INT cas4_end_of_cut  cas4_end_of_cut_completed
LVAR_INT cas4_audio_left_behind_played cas4_left_behind
LVAR_INT cas4_fight_audio1 cas4_fight_audio1_completed
LVAR_INT cas4_fight_audio2 cas4_fight_audio2_completed
LVAR_INT cas4_piss_audio cas4_piss_audio_completed
LVAR_INT cas4_puke_audio cas4_puke_audio_completed
LVAR_INT cas4_who_started_fight			
LVAR_INT cas4_no_car cas4_no_car_played

LVAR_INT cas4_sfx[2]
LVAR_INT cas4_sfx_counter 
LVAR_INT cas4_sfx_playing 

/// opening cut-scene fudge
LVAR_INT cas4_temp_car cas4_temp_ped cas4_temp_model cas4_temp_class
LVAR_INT cas4_timer_current  cas4_timer_start cas4_timer_flag
LVAR_INT cas4_time_elapsed

SET_FADING_COLOUR 0 0 0

DO_FADE 2000 FADE_OUT

WHILE GET_FADING_STATUS
            WAIT 0
ENDWHILE





 
// **************************************** Mission Start **********************************

mission_start_casino4:

REGISTER_MISSION_GIVEN


flag_player_on_mission = 1


FORCE_WEATHER WEATHER_SUNNY_VEGAS

LOAD_MISSION_TEXT CASINO4
// ****************************************START OF CUTSCENE********************************
//intro cut scene
LOAD_CUTSCENE CAS_4a
 

WHILE NOT HAS_CUTSCENE_LOADED
            WAIT 0
ENDWHILE
 
CLEAR_AREA 2027.3585 1007.7380 9.8127 10.0 TRUE





SET_CHAR_HEADING scplayer 265.5486



GET_GAME_TIMER cas4_timer_start




cas4_timer_flag = 0

IF cas4_timer_flag = 99
	CREATE_CAR cas4_temp_model 	2044.6206 1110.1982 9.6719  cas4_temp_car
	CREATE_RANDOM_CHAR_AS_DRIVER cas4_temp_car cas4_temp_ped
	CREATE_RANDOM_CHAR_AS_DRIVER cas4_temp_car cas4_old_ped
	CREATE_RANDOM_CHAR_AS_DRIVER cas4_temp_car cas4_incest_ped
	CREATE_RANDOM_CHAR_AS_DRIVER cas4_temp_car cas4_pig_ped
	CREATE_RANDOM_CHAR_AS_DRIVER cas4_temp_car cas4_sister_ped
	CREATE_FX_SYSTEM puke cas4_carx cas4_cary cas4_carz TRUE cas4_vomit
	CREATE_FX_SYSTEM puke cas4_carx cas4_cary cas4_carz TRUE cas4_piss
ENDIF

MAKE_PLAYER_GANG_DISAPPEAR
START_CUTSCENE


DO_FADE 1000 FADE_IN



TIMERA = 0 
  
WHILE NOT HAS_CUTSCENE_FINISHED     
    GET_GAME_TIMER cas4_timer_current
	cas4_time_elapsed = cas4_timer_current - cas4_timer_start
    IF 	cas4_time_elapsed > 15000 // camera switch has occurred
		IF TIMERA > 2000
			SWITCH cas4_timer_flag
				CASE 0 				  	
					GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE cas4_temp_model cas4_temp_class
					IF NOT cas4_temp_model = -1
						CREATE_CAR cas4_temp_model 	2051.5439 1100.5112 9.6719  cas4_temp_car
						SET_VEHICLE_TO_FADE_IN cas4_temp_car 0
						SET_CAR_HEADING cas4_temp_car 183.2183
						CREATE_RANDOM_CHAR_AS_DRIVER cas4_temp_car cas4_temp_ped
						CAR_WANDER_RANDOMLY cas4_temp_car
						MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_ped
						MARK_CAR_AS_NO_LONGER_NEEDED cas4_temp_car										
					ENDIF										
					cas4_timer_flag++								
				BREAK
			
				CASE 1 
					CREATE_RANDOM_CHAR 2034.8136 993.6725 9.8130 cas4_temp_ped
					SET_CHAR_HEADING cas4_temp_ped 357.2051
					TASK_FOLLOW_PATH_NODES_TO_COORD cas4_temp_ped 2033.8563 1051.8037 9.8203	PEDMOVE_WALK -2
					MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_ped															
					cas4_timer_flag++
				BREAK
				
				CASE 2 															
					GET_RANDOM_CAR_MODEL_IN_MEMORY TRUE cas4_temp_model cas4_temp_class
					IF NOT cas4_temp_model = -1						
						CREATE_CAR cas4_temp_model 	2051.5439 1100.5112 9.6719  cas4_temp_car
						SET_VEHICLE_TO_FADE_IN cas4_temp_car 0
						SET_CAR_HEADING cas4_temp_car 183.2183
						CREATE_RANDOM_CHAR_AS_DRIVER cas4_temp_car cas4_temp_ped
						CAR_WANDER_RANDOMLY cas4_temp_car
						MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_ped
						MARK_CAR_AS_NO_LONGER_NEEDED cas4_temp_car															
					ENDIF
					cas4_timer_flag++
				BREAK
				 
				CASE 3 										
					CREATE_RANDOM_CHAR 2033.8563 1041.8037 9.8203 cas4_temp_ped
					SET_CHAR_HEADING cas4_temp_ped 181.0345
					TASK_FOLLOW_PATH_NODES_TO_COORD cas4_temp_ped 2032.2571 986.0109 9.8130	PEDMOVE_WALK -2
					MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_ped																				
					cas4_timer_flag = 0
				BREAK
					cas4_timer_flag = 0
				
				DEFAULT
				BREAK
			ENDSWITCH

			TIMERA = 0
			ENDIF
		ENDIF		    
    WAIT 0
ENDWHILE


SET_PLAYER_CONTROL player1 OFF

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE

CLEAR_CUTSCENE

MAKE_PLAYER_GANG_REAPPEAR

RELEASE_WEATHER

SET_CAR_DENSITY_MULTIPLIER 1.0
SET_PED_DENSITY_MULTIPLIER 1.0

// ****************************************END OF CUTSCENE********************************


LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM cas4_decision3

SET_GROUP_DECISION_MAKER Players_Group cas4_decision3


 
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH cas4_decision
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY cas4_decision2

SET_PLAYER_CONTROL player1 OFF
DO_FADE 1500 FADE_OUT
WHILE GET_FADING_STATUS
WAIT 0
ENDWHILE

MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_ped
MARK_CAR_AS_NO_LONGER_NEEDED cas4_temp_car



WAIT 0


LOAD_SPECIAL_CHARACTER 1 MACCER //paul model
LOAD_SPECIAL_CHARACTER 2 PAUL //macca model
/*
REQUEST_MODEL  CWMOHB2 // old hillbilly 
REQUEST_MODEL  CWMYHB1  	// male hillbilly 1	pig man
REQUEST_MODEL  CWMYHB2	// male hillbilly 2	sister lover
REQUEST_MODEL   CWFYHB	// female hillbilly 
REQUEST_MODEL  DWMYLC1
*/



LOAD_ALL_MODELS_NOW

WHILE NOT  HAS_SPECIAL_CHARACTER_LOADED 1
   OR NOT  HAS_SPECIAL_CHARACTER_LOADED 2
  		   WAIT 0
ENDWHILE



SWITCH_WIDESCREEN OFF

 

/////////////////////////// set the initial flag status	   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
cas4_paul_macca_created_flag     = 0


cas4_at_end_flag                 = 0
cas4_blip_added_flag	   		 = 0
cas4_player_reached_desert_flag  = 0
cas4_car_created_flag            = 0
cas4_played_cutscene_flag        = 0
cas4_at_casino_flag              = 0
cas4_mission_progression_flag    = 0
cas4_snake_blip_flag             = 0
cas4_safety_flag                 = 0
cas4_old_in_car_flag 			 = 0
cas4_pig_in_car_flag             = 0
cas4_old_task_assigned   		 = 0
cas4_incest_task_assigned 		 = 0
cas4_sister_task_assigned		 = 0
cas4_pig_task_assigned			 = 0
cas4_snake_car_created_flag      = 0
cas4_driver1_in_car 			 = 0
cas4_driver2_in_car				 = 0
cas4_hill_billy_stand_played     = 0
cas4_crap_assigned				 = 0
cas4_sister_driving 			 = 0
cas4_incest_driving				 = 0


cas4_playerx					 = 0.0
cas4_playery					 = 0.0
cas4_playerz					 = 0.0
cas4_carx						 = 0.0
cas4_cary						 = 0.0
cas4_carz						 = 0.0

cas4_counter					 = 0
cas4_hill_billies_alive			 = 0

WHILE cas4_counter < 4
cas4_hill_billy_dead[cas4_counter] = 0

cas4_counter++
ENDWHILE




cas4_paul_with_player			= 0
cas4_macca_with_player			= 0
cas4_free_seats					= 0
cas4_fighting					= 0
cas4_vomit_status				= 0
cas4_piss_status				= 0
cas4_macca_playing_fx 			= 0
cas4_paul_playing_fx 			= 0
cas4_paul_busy					= 0
cas4_macca_busy 				= 0
cas4_done_pisspuke				= 0
cas4_paul_told_to_enter 		= 0
cas4_macca_told_to_enter 		= 0

cas4_message_dsplayed			= 99999


cas4_animation_time				= 0.0

cas4_banter1 					= 0
cas4_banter1_completed			= 0
cas4_banter2 					= 0
cas4_banter2_completed			= 0
cas4_banter3 					= 0
cas4_banter3_completed			= 0
cas4_banter4 					= 0
cas4_banter4_completed			= 0
cas4_end_of_cut  				= 0
cas4_end_of_cut_completed		= 0 
cas4_audio_left_behind_played 	= 0
cas4_left_behind				= 0
cas4_fight_audio1 				= 0
cas4_fight_audio1_completed		= 0
cas4_fight_audio2 				= 0
cas4_fight_audio2_completed		= 0
cas4_who_started_fight			= 0
cas4_piss_audio 				= 0
cas4_piss_audio_completed		= 0
cas4_puke_audio 				= 0
cas4_puke_audio_completed		= 0
cas4_done_piss 					= 0
cas4_done_puke					= 0
								


///////////////////////// AUDIO CRAP ////////////////////////////////////
	 
								
//$cas4_text[1] = CAS4_AA//That there city boy, has gone and been with my prize hog! now I don’t even get no sugar from her !“	
//$cas4_text[2] = CAS4_AB//And that un went and did my sis, I've had a terble achin' in my grinds every since !”	
//$cas4_text[3] = CAS4_AC//I'm a fixin' to give ya a whoopin' for wha you gone and done to ma Young’uns !
//$cas4_text[4] = CAS4_AD//Take a gander at 'em fellers, is that em ?
//$cas4_text[5] = CAS4_AE//Whut’n tarnation !?	
//$cas4_text[6] = CAS4_AF//I'm gonna slap you silly for giving me and ma fella the red bumpies !																		
$cas4_text[7] = CAS4_BA//]My legs are fucked and my head's fuckin' pounding!
$cas4_text[8] = CAS4_BB//]We can't walk back, mate...
$cas4_text[9] = CAS4_CA//]Your turn in the boot, ol'lad!
$cas4_text[10] = CAS4_CB//]Oooo, this is comfy!
$cas4_text[11] = CAS4_CC//]I'm the manager, I get the front seat!
$cas4_text[12] = CAS4_CD//]Hey, that's my seat!
$cas4_text[13] = CAS4_CE//]Leave him, he'll be fine!
$cas4_text[14] = CAS4_CF//]We'll come back for you, I promise!
$cas4_text[15] = CAS4_CG//]Oh no you don't, I remember Preston Guild Hall!
$cas4_text[16] = CAS4_CH//]I'm the manager, what I say, goes!
$cas4_text[17] = CAS4_CJ//]Don't make me slap you, sunshine!
$cas4_text[18] = CAS4_CK//]Don't make me sack you!
$cas4_text[19] = CAS4_CL//]You're my manager this is all your fault!
$cas4_text[20] = CAS4_DA//Where's the rest of the band, guys?
$cas4_text[21] = CAS4_DB//Oh bollox! Maccer, where are the boys?
$cas4_text[22] = CAS4_DC//I don't fuckin' know, do I.
$cas4_text[23] = CAS4_DD//I remember snakes, lots of snakes...
$cas4_text[24] = CAS4_DE//There's a snake farm not far from here, we can go check it out.
$cas4_text[25] = CAS4_EA//]I don't recognise this part of Manchester, our kid...
$cas4_text[26] = CAS4_EB//]How many times do I have to tell you, we're in America!
$cas4_text[27] = CAS4_EC//]America? Wait 'til I tell me ma about this!
$cas4_text[28] = CAS4_ED//]He's like a fucking stuck record. He'll ask about Las Venturas next.
$cas4_text[29] = CAS4_EE//]Las Venturas? Always wanted to go there, great tits.
$cas4_text[30] = CAS4_EF//]Don't start, not in this confined space.
$cas4_text[31] = CAS4_EG///]Bouncing, wobbling, creamy tits...
$cas4_text[32] = CAS4_EH//]LEAVE YOURSELF ALONE!
$cas4_text[33] = CAS4_FA//]Here we are, look familiar?
$cas4_text[34] = CAS4_FB//]Looks like Salford to me...
$cas4_text[35] = CAS4_FC//]What?
$cas4_text[36] = CAS4_GA//]Run like fuck!
$cas4_text[37] = CAS4_GB//]Everybody in the car!
$cas4_text[38] = CAS4_HA//]Wait, our kid, wait!
$cas4_text[39] = CAS4_HB//]I want to come too!
$cas4_text[40] = CAS4_HC//]Hey, wait for me, sunshine!
$cas4_text[41] = CAS4_HD//]Don't leave me in their clutches!
$cas4_text[42] = CAS4_JA//]I think we lost 'em.
$cas4_text[43] = CAS4_JB//]I can't see those Snake farmers any more!
$cas4_text[44] = CAS4_JC//]Shut up, you two!
$cas4_text[45] = CAS4_JD//]Quit squabbling!
$cas4_text[46] = CAS4_KA//]It seems you boys had a good time!
$cas4_text[47] = CAS4_KB//]What about the band?
$cas4_text[48] = CAS4_KC//]We'll just have to pray they've made it to civilization.
$cas4_text[49] = CAS4_KD//]Keyboardists and drummers are ten-a-penny anyway.
$cas4_text[50] = CAS4_KE//]I need a piss, mate!
$cas4_text[51] = CAS4_KF//]Oh fuck, we're screwed!
$cas4_text[52] = CAS4_KG//]Can't it wait?
$cas4_text[53] = CAS4_KH//]I'm fit to burst, ol'lad!
$cas4_text[54] = CAS4_KJ//]You've got to pull over, he's priapic, it'll go everywhere!
$cas4_text[55] = CAS4_LA//]I can't hold it any longer!
$cas4_text[56] = CAS4_LB//]Oh fuck!
$cas4_text[57] = CAS4_LC//]Hey, what the fuck?
$cas4_text[58] = CAS4_LD//]Argh! Stop it!
$cas4_text[59] = CAS4_LE//]Point it out the fucking window!
$cas4_text[60] = CAS4_MA//]Oh, fuck, that is goooood.
$cas4_text[61] = CAS4_NA//]Oh fuck, I've got the shakes!
$cas4_text[62] = CAS4_NB//]You look as pale as a drowned baby, mate!
$cas4_text[63] = CAS4_NC//]Oh god, I think I'm going to chuck!
$cas4_text[64] = CAS4_ND//]What you need is some food down you!
$cas4_text[65] = CAS4_NE//](Paul nearly vomits)
$cas4_text[66] = CAS4_NF//]A fried egg sanger with mayonaise will sort you out.
$cas4_text[67] = CAS4_NG//](Paul nearly vomits again)
$cas4_text[68] = CAS4_NH//]Or a pickled egg!
$cas4_text[69] = CAS4_NJ//]Pull over, NOW!
$cas4_text[70] = CAS4_OA//]Shhi- huuuurghhh -hit!
$cas4_text[71] = CAS4_OB//]I've go- huuurrrgghh!
$cas4_text[72] = CAS4_OC//]Got the- Hhhuurrgghh!
$cas4_text[73] = CAS4_OD//]Dry- hurgh!
$cas4_text[74] = CAS4_OE//]Dry- hurgh!
$cas4_text[75] = CAS4_OF//]Dry heaves!
$cas4_text[76] = CAS4_OG//]Oh, I thuh- think it's past...
$cas4_text[77] = CAS4_OH//]Or how about a pint of cod liver oil, that would sort your guts out!
$cas4_text[78] = CAS4_OJ//](Paul vomiting violently)
$cas4_text[79] = CAS4_OK//]Leave him, man.
$cas4_text[80] = CAS4_OL//]The good doctor is just trying to help!
$cas4_text[81] = CAS4_OM//]Fuh-huh-Fuck off!
$cas4_text[82] = CAS4_PA//]What kind of tits does this Rosie have?
$cas4_text[83] = CAS4_PB//]Big, floppy sausage tits?
//$cas4_text[84] = CAS4_PC//]Empty saddlebags?
$cas4_text[85] = CAS4_PD//]Bee stings?
$cas4_text[86] = CAS4_PE//]Rosie is a man!
$cas4_text[87] = CAS4_PF//]And stop touching yourself!
$cas4_text[88] = CAS4_PG//]It's just for comfort, this is a stressful situation.
$cas4_text[89] = CAS4_PH//]You're fucking telling me it is!
$cas4_text[90] = CAS4_PJ//]Can it you two!
$cas4_text[91] = CAS4_PK//]He started it!
$cas4_text[92] = CAS4_QA//]Where is this casino?
$cas4_text[93] = CAS4_QB//]It's called Caligula's, it's on the strip.
$cas4_text[94] = CAS4_RA//]Shit, here come those snake farmers!
$cas4_text[95] = CAS4_RB//]Right, I've had enough.
//$cas4_text[96] = CAS4_RC//]Let's finish this!
$cas4_text[97] = CAS4_RD//]I'm going to punch some big tits!
$cas4_text[98] = CAS4_RE//]Fuck it, in for a penny...
$cas4_text[99] = CAS4_SA//]Come on, let's go in and see Rosie.
$cas4_text[100] = CAS4_TA//]Yo, Woozie, just a quick call to say I think I've found a way 
$cas4_text[101] = CAS4_TB//]to scope Caligula's Casino without causing too much suspicion.
$cas4_text[102] = CAS4_TC//]We can talk later.		    
$cas4_text[103] = CAS4_FD//  Take a gander at them fellas, is that them?
$cas4_text[104] = CAS4_FE// That there city boy has been with my prize hog!
$cas4_text[105] = CAS4_FF// Now I don't even get no sugar from her!
$cas4_text[106] = CAS4_FG// And that’un went and did my sis! 
$cas4_text[107] = CAS4_FH// I've had a terr’ble aching in my grinds ever since!
$cas4_text[108] = CAS4_FJ// I'm gonna slap you silly for giving me and my fella the red bumpies!
$cas4_text[109] = CAS4_FK// What'n tarnation?
$cas4_text[110] = CAS4_FL// I'm a fixing to give ya a whoopin' for what you gone and done to my young'uns
$cas4_text[111] = MACX_AF//	So now you know how it really is. (macca)
$cas4_text[112] = MACX_AG//	Yeah, that's how we do it in Salford (macca)
$cas4_text[113] = MACX_AI//	He's only gone and died.(macca)


//cas4_audio[1] = SOUND_CAS4_AA//That there city boy, has gone and been with my prize hog! now I don’t even get no sugar from her !“	
//cas4_audio[2] = SOUND_CAS4_AB//And that un went and did my sis, I've had a terble achin' in my grinds every since !”	
//cas4_audio[3] = SOUND_CAS4_AC//I'm a fixin' to give ya a whoopin' for wha you gone and done to ma Young’uns !
//cas4_audio[4] = SOUND_CAS4_AD//Take a gander at 'em fellers, is that em ?
//cas4_audio[5] = SOUND_CAS4_AE//Whut'n tarnation !?	
//cas4_audio[6] = SOUND_CAS4_AF//I'm gonna slap you silly for giving me and ma fella the red bumpies !
cas4_audio[7] = SOUND_CAS4_BA//]My legs are fucked and my head's fuckin' pounding!
cas4_audio[8] = SOUND_CAS4_BB//]We can't walk back, mate...
cas4_audio[9] = SOUND_CAS4_CA//]Your turn in the boot, ol'lad!
cas4_audio[10] = SOUND_CAS4_CB//]Oooo, this is comfy!
cas4_audio[11] = SOUND_CAS4_CC//]I'm the manager, I get the front seat!
cas4_audio[12] = SOUND_CAS4_CD//]Hey, that's my seat!
cas4_audio[13] = SOUND_CAS4_CE//]Leave him, he'll be fine!
cas4_audio[14] = SOUND_CAS4_CF//]We'll come back for you, I promise!
cas4_audio[15] = SOUND_CAS4_CG//]Oh no you don't, I remember Preston Guild Hall!
cas4_audio[16] = SOUND_CAS4_CH//]I'm the manager, what I say, goes!
cas4_audio[17] = SOUND_CAS4_CJ//]Don't make me slap you, sunshine!
cas4_audio[18] = SOUND_CAS4_CK//]Don't make me sack you!
cas4_audio[19] = SOUND_CAS4_CL//]You're my manager this is all your fault!
cas4_audio[20] = SOUND_CAS4_DA//]Where's the rest of the band, guys?
cas4_audio[21] = SOUND_CAS4_DB//]Oh bollox! Maccer, where are the boys?
cas4_audio[22] = SOUND_CAS4_DC//]I don't fuckin' know, do I.
cas4_audio[23] = SOUND_CAS4_DD//]I remember snakes, lots of snakes...
cas4_audio[24] = SOUND_CAS4_DE//]There's a snake farm not far from here, we can go check it out.
cas4_audio[25] = SOUND_CAS4_EA//]I don't recognise this part of Manchester, our kid...
cas4_audio[26] = SOUND_CAS4_EB//]How many times do I have to tell you, we're in America!
cas4_audio[27] = SOUND_CAS4_EC//]America? Wait 'til I tell me ma about this!
cas4_audio[28] = SOUND_CAS4_ED//]He's like a fucking stuck record. He'll ask about Las Venturas next.
cas4_audio[29] = SOUND_CAS4_EE//]Las Venturas? Always wanted to go there, great tits.
cas4_audio[30] = SOUND_CAS4_EF//]Don't start, not in this confined space.
cas4_audio[31] = SOUND_CAS4_EG///]Bouncing, wobbling, creamy tits...
cas4_audio[32] = SOUND_CAS4_EH//]LEAVE YOURSELF ALONE!
cas4_audio[33] = SOUND_CAS4_FA//]Here we are, look familiar?
cas4_audio[34] = SOUND_CAS4_FB//]Looks like Salford to me...
cas4_audio[35] = SOUND_CAS4_FC//]What?
cas4_audio[36] = SOUND_CAS4_GA//]Run like fuck!
cas4_audio[37] = SOUND_CAS4_GB//]Everybody in the car!
cas4_audio[38] = SOUND_CAS4_HA//]Wait, our kid, wait!
cas4_audio[39] = SOUND_CAS4_HB//]I want to come too!
cas4_audio[40] = SOUND_CAS4_HC//]Hey, wait for me, sunshine!
cas4_audio[41] = SOUND_CAS4_HD//]Don't leave me in their clutches!
cas4_audio[42] = SOUND_CAS4_JA//]I think we lost 'em.
cas4_audio[43] = SOUND_CAS4_JB//]I can't see those Snake farmers any more!
cas4_audio[44] = SOUND_CAS4_JC//]Shut up, you two!
cas4_audio[45] = SOUND_CAS4_JD//]Quit squabbling!
cas4_audio[46] = SOUND_CAS4_KA//]It seems you boys had a good time!
cas4_audio[47] = SOUND_CAS4_KB//]What about the band?
cas4_audio[48] = SOUND_CAS4_KC//]We'll just have to pray they've made it to civilization.
cas4_audio[49] = SOUND_CAS4_KD//]Keyboardists and drummers are ten-a-penny anyway.
cas4_audio[50] = SOUND_CAS4_KE//]I need a piss, mate!
cas4_audio[51] = SOUND_CAS4_KF//]Oh fuck, we're screwed!
cas4_audio[52] = SOUND_CAS4_KG//]Can't it wait?
cas4_audio[53] = SOUND_CAS4_KH//]I'm fit to burst, ol'lad!
cas4_audio[54] = SOUND_CAS4_KJ//]You've got to pull over, he's priapic, it'll go everywhere!
cas4_audio[55] = SOUND_CAS4_LA//]I can't hold it any longer!
cas4_audio[56] = SOUND_CAS4_LB//]Oh fuck!
cas4_audio[57] = SOUND_CAS4_LC//]Hey, what the fuck?
cas4_audio[58] = SOUND_CAS4_LD//]Argh! Stop it!
cas4_audio[59] = SOUND_CAS4_LE//]Point it out the fucking window!
cas4_audio[60] = SOUND_CAS4_MA//]Oh, fuck, that is goooood.
cas4_audio[61] = SOUND_CAS4_NA//]Oh fuck, I've got the shakes!
cas4_audio[62] = SOUND_CAS4_NB//]You look as pale as a drowned baby, mate!
cas4_audio[63] = SOUND_CAS4_NC//]Oh god, I think I'm going to chuck!
cas4_audio[64] = SOUND_CAS4_ND//]What you need is some food down you!
cas4_audio[65] = SOUND_CAS4_NE//](Paul nearly vomits)
cas4_audio[66] = SOUND_CAS4_NF//]A fried egg sanger with mayonaise will sort you out.
cas4_audio[67] = SOUND_CAS4_NG//](Paul nearly vomits again)
cas4_audio[68] = SOUND_CAS4_NH//]Or a pickled egg!
cas4_audio[69] = SOUND_CAS4_NJ//]Pull over, NOW!
cas4_audio[70] = SOUND_CAS4_OA//]Shhi- huuuurghhh -hit!
cas4_audio[71] = SOUND_CAS4_OB//]I've go- huuurrrgghh!
cas4_audio[72] = SOUND_CAS4_OC//]Got the- Hhhuurrgghh!
cas4_audio[73] = SOUND_CAS4_OD//]Dry- hurgh!
cas4_audio[74] = SOUND_CAS4_OE//]Dry- hurgh!
cas4_audio[75] = SOUND_CAS4_OF//]Dry heaves!
cas4_audio[76] = SOUND_CAS4_OG//]Oh, I thuh- think it's past...
cas4_audio[77] = SOUND_CAS4_OH//]Or how about a pint of cod liver oil, that would sort your guts out!
cas4_audio[78] = SOUND_CAS4_OJ//](Paul vomiting violently)
cas4_audio[79] = SOUND_CAS4_OK//]Leave him, man.
cas4_audio[80] = SOUND_CAS4_OL//]The good doctor is just trying to help!
cas4_audio[81] = SOUND_CAS4_OM//]Fuh-huh-Fuck off!
cas4_audio[82] = SOUND_CAS4_PA//]What kind of tits does this Rosie have?
cas4_audio[83] = SOUND_CAS4_PB//]Big, floppy sausage tits?
//cas4_audio[84] = SOUND_CAS4_PC//]Empty saddlebags?
cas4_audio[85] = SOUND_CAS4_PD//]Bee stings?
cas4_audio[86] = SOUND_CAS4_PE//]Rosie is a man!
cas4_audio[87] = SOUND_CAS4_PF//]And stop touching yourself!
cas4_audio[88] = SOUND_CAS4_PG//]It's just for comfort, this is a stressful situation.
cas4_audio[89] = SOUND_CAS4_PH//]You're fucking telling me it is!
cas4_audio[90] = SOUND_CAS4_PJ//]Can it you two!
cas4_audio[91] = SOUND_CAS4_PK//]He started it!
cas4_audio[92] = SOUND_CAS4_QA//]Where is this casino?
cas4_audio[93] = SOUND_CAS4_QB//]It's called Caligula's, it's on the strip.
cas4_audio[94] = SOUND_CAS4_RA//]Shit, here come those snake farmers!
cas4_audio[95] = SOUND_CAS4_RB//]Right, I've had enough.
//cas4_audio[96] = SOUND_CAS4_RC//]Let's finish this!
cas4_audio[97] = SOUND_CAS4_RD//]I'm going to punch some big tits!
cas4_audio[98] = SOUND_CAS4_RE//]Fuck it, in for a penny...
cas4_audio[99] = SOUND_CAS4_SA//]Come on, let's go in and see Rosie.
cas4_audio[100] = SOUND_CAS4_TA//]Yo, Woozie, just a quick call to say I think I've found a way 
cas4_audio[101] = SOUND_CAS4_TB//]to scope Caligula's Casino without causing too much suspicion.
cas4_audio[102] = SOUND_CAS4_TC//]We can talk later.
cas4_audio[103] = SOUND_CAS4_FD//  Take a gander at them fellas, is that them?
cas4_audio[104] = SOUND_CAS4_FE// That there city boy has been with my prize hog!
cas4_audio[105] = SOUND_CAS4_FF// Now I don't even get no sugar from her!
cas4_audio[106] = SOUND_CAS4_FG// And that’un went and did my sis! 
cas4_audio[107] = SOUND_CAS4_FH// I've had a terr’ble aching in my grinds ever since!
cas4_audio[108] = SOUND_CAS4_FJ// I'm gonna slap you silly for giving me and my fella the red bumpies!
cas4_audio[109] = SOUND_CAS4_FK// What'n tarnation?
cas4_audio[110] = SOUND_CAS4_FL// I'm a fixing to give ya a whoopin' for what you gone and done to my young'uns
cas4_audio[111] = SOUND_MACX_AF//	So now you know how it really is. (macca)
cas4_audio[112] = SOUND_MACX_AG//	Yeah, that's how we do it in Salford (macca)
cas4_audio[113] = SOUND_MACX_AI//	He's only gone and died.(macca)








cas4_sfx[1] = SOUND_PISSING


cas4_audio_counter 		= 0
cas4_audio_slot1   		= 1
cas4_audio_slot2   		= 2
cas4_audio_playing 		= 0
cas4_text_loop_done		= 0
cas4_mobile				= 0
cas4_text_timer_diff	= 0 
cas4_text_timer_end 	= 0
cas4_text_timer_start 	= 0
cas4_ahead_counter	  	= 0
cas4_sfx_counter 		= 0
cas4_sfx_playing 		= 0
cas4_no_car 			= 0
cas4_no_car_played		= 0

SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
SET_SCRIPT_LIMIT_TO_GANG_SIZE 1
MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 2


WAIT 1000

// ************************************** MAIN LOOP **********************************
 
 
casino4_main_loop:

WAIT 0
 

//SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 True

/////////////////////////////////////////
///////// DEBUG KEYBOARD COMMANDS ///////
/////////////////////////////////////////


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
  SET_CHAR_COORDINATES scplayer -725.0 2392.69 128.0 // the desert where paul and macca are
ENDIF


IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
  SET_CHAR_COORDINATES scplayer -45.0 2322.0 23.0 // the snake farm
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_3
  SET_CHAR_COORDINATES scplayer 2062.0 1362.0 10.7 // the mafia casino
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_G
	TIMERA = 27000
ENDIF



IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_BSPACE
	cas4_vomit_status				= 0
	cas4_piss_status				= 0
	cas4_paul_playing_fx			= 0
	cas4_macca_playing_fx			= 0
	cas4_paul_busy					= 0
	cas4_macca_busy 				= 0
	cas4_done_pisspuke				= 0
	
cas4_animation_time				= 0.0
	
ENDIF



IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE


	cas4_audio_counter 		= 103
	cas4_audio_slot1   		= 1
	cas4_audio_slot2   		= 2
	cas4_audio_playing 		= 0
	cas4_text_loop_done		= 0
	cas4_mobile				= 0

	IF cas4_audio_playing = 0
		cas4_audio_counter = 103	// CESAR: CJ.		 
		cas4_mobile = 103
		GET_GAME_TIMER cas4_text_timer_start
	ENDIF

	casino4_text_loop19:
	WAIT 0 

	IF cas4_text_loop_done = 0
		
		
		IF NOT cas4_audio_counter = 0
			IF cas4_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
					CLEAR_MISSION_AUDIO cas4_audio_slot2
				ENDIF
				cas4_audio_playing = 1
			ENDIF

			IF cas4_audio_playing = 1
				LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
				cas4_audio_playing = 2
			ENDIF

			IF cas4_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
					PLAY_MISSION_AUDIO cas4_audio_slot1
					PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
					cas4_audio_playing = 3
				ENDIF
			ENDIF

			IF cas4_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
					CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
					IF cas4_audio_slot1 = 1
						cas4_audio_slot1 = 2
						cas4_audio_slot2 = 1
					ELSE
						cas4_audio_slot1 = 1
						cas4_audio_slot2 = 2
					ENDIF
					cas4_audio_counter = 0
					cas4_audio_playing = 0
				ELSE
					IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
						IF cas4_audio_counter < 102
							cas4_ahead_counter = cas4_audio_counter + 1
							LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF				

		GET_GAME_TIMER cas4_text_timer_end
		cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
		IF cas4_text_timer_diff > 1000
			IF cas4_audio_playing = 0
				IF cas4_mobile < 110
				cas4_mobile++
				cas4_audio_counter = cas4_mobile								
				GET_GAME_TIMER cas4_text_timer_start					
				ELSE
					cas4_text_loop_done = 1
				ENDIF
			ENDIF
		ENDIF												
		
		
		GOTO casino4_text_loop19

	ENDIF
ENDIF






IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_0
   TIMERA = 27000
   TIMERB = 27000
ENDIF












IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
   GOTO mission_casino4_passed
ENDIF





			 
///////////////////////////////////
/// Set up for start of Mission ///
///////////////////////////////////

IF cas4_player_reached_desert_flag = 0
AND cas4_car_created_flag = 0
		

	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE		

	ADD_BLIP_FOR_COORD  -735.0 2398.69 126.0 cas4_desert_blip
	SET_COORD_BLIP_APPEARANCE    cas4_desert_blip COORD_BLIP_APPEARANCE_FRIEND
	SET_CHAR_HEADING scplayer 265.5486
	SET_CAMERA_BEHIND_PLAYER
	PRINT ( CAS4_01 ) 10000 1 ///////////// " Go find Paul and Maccer "
	cas4_car_created_flag = 1


	DO_FADE 1500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	SET_PLAYER_CONTROL player1 ON

	IF played_casino4_before = 1
			SET_UP_SKIP  -698.0 2398.0 131.0 60.0//-735.0 2398.69 126.0  90.0
	ENDIF

ENDIF


////////////////////////////////////
/// Add Blip for Desert Location ///
////////////////////////////////////

IF cas4_player_reached_desert_flag = 0
AND cas4_car_created_flag = 1

	IF IS_CHAR_IN_ANY_CAR scplayer
		STORE_CAR_CHAR_IS_IN scplayer cas4_player_car
	ENDIF
	
	IF LOCATE_CHAR_ANY_MEANS_3D scplayer -735.0 2398.69 126.0 4.0 4.0  2.0 TRUE
		REMOVE_BLIP cas4_desert_blip
		cas4_player_reached_desert_flag = 1
	ELSE
		IF cas4_paul_macca_created_flag = 0  		
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer -735.0 2398.69 126.0 100.0 100.0  100.0 FALSE	

				IF played_casino4_before = 0
					played_casino4_before = 1
				ENDIF
				REQUEST_ANIMATION SUNBATHE
				WHILE NOT HAS_ANIMATION_LOADED SUNBATHE
					WAIT 0
				ENDWHILE								     


				CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 -751.1198 2395.1995 128.2378  cas4_macca_ped // black guy with shades			
				SET_CHAR_HEADING cas4_macca_ped 246.0
				SET_CHAR_DECISION_MAKER cas4_macca_ped cas4_decision2
				SET_CHAR_NEVER_TARGETTED cas4_macca_ped TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS cas4_macca_ped FALSE
				
				CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 -752.2823 2391.0537 127.8083  cas4_paul_ped  // white guy white guy with shorts
				SET_CHAR_HEADING cas4_paul_ped 220.2451
				SET_CHAR_DECISION_MAKER cas4_paul_ped cas4_decision2
				SET_CHAR_PROOFS cas4_macca_ped FALSE FALSE FALSE FALSE TRUE
				SET_CHAR_PROOFS cas4_paul_ped FALSE FALSE FALSE FALSE TRUE
				SET_CHAR_NEVER_TARGETTED cas4_paul_ped TRUE
				SET_CHAR_SUFFERS_CRITICAL_HITS cas4_paul_ped FALSE


				OPEN_SEQUENCE_TASK cas4_cutscene_paul_seq
				TASK_LOOK_AT_CHAR -1 scplayer -1
				TASK_PLAY_ANIM	 -1  SBATHE_F_LieB2Sit SUNBATHE 60000.0 FALSE FALSE FALSE TRUE -1 // latest addition			
				CLOSE_SEQUENCE_TASK cas4_cutscene_paul_seq
				PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_cutscene_paul_seq
				CLEAR_SEQUENCE_TASK cas4_cutscene_paul_seq

				OPEN_SEQUENCE_TASK cas4_cutscene_macca_seq
				TASK_LOOK_AT_CHAR -1 scplayer -1
				TASK_PLAY_ANIM	 -1  SBATHE_F_LieB2Sit SUNBATHE 60000.0 FALSE FALSE FALSE TRUE -1 // latest addition
				CLOSE_SEQUENCE_TASK cas4_cutscene_macca_seq
				PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_cutscene_macca_seq
				CLEAR_SEQUENCE_TASK cas4_cutscene_macca_seq


				SET_CHAR_RELATIONSHIP cas4_macca_ped ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1 
				SET_CHAR_RELATIONSHIP cas4_paul_ped ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1

				cas4_paul_macca_created_flag = 1
			ENDIF   
		ENDIF
	ENDIF
ENDIF


//////////////////////////////////////////
/// Create the models for desert scene ///
//////////////////////////////////////////


	


////////////////////////
/// Middle Cut Scene ///
////////////////////////

IF cas4_player_reached_desert_flag = 1
AND cas4_played_cutscene_flag = 0
	
	SET_PLAYER_CONTROL player1 OFF
	
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	SWITCH_WIDESCREEN ON								
	
	MAKE_PLAYER_GANG_DISAPPEAR
	SET_FADING_COLOUR 0 0 0

	DO_FADE 1000 FADE_OUT

	FORCE_WEATHER WEATHER_SUNNY_DESERT

	DELETE_CHAR cas4_macca_ped
	DELETE_CHAR cas4_paul_ped
	MARK_CHAR_AS_NO_LONGER_NEEDED cas4_macca_ped
	MARK_CHAR_AS_NO_LONGER_NEEDED cas4_paul_ped
	REMOVE_ANIMATION SUNBATHE

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	
	CLEAR_AREA -738.0978 2391.5923 127.9015 100.0 TRUE
	IF NOT IS_CAR_DEAD cas4_player_car		
		IF LOCATE_CAR_3D cas4_player_car -738.0 2391.0 127.0 10.0 10.0 5.0 FALSE
			SET_CAR_COORDINATES cas4_player_car	-735.0 2398.69 126.0
		ENDIF			
	ENDIF

	MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
	//SWITCH_STREAMING OFF 

	LOAD_CUTSCENE CAS_4b
	 
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

	RELEASE_WEATHER
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_VEHICLE_ON_FIRE	 	TASK_COMPLEX_LEAVE_CAR_AND_FLEE 100.0 100.0 100.0 100.0 TRUE FALSE  
	
	CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 -751.1198 2395.1995 128.2378  cas4_macca_ped // black guy with shades							
	SET_CHAR_DECISION_MAKER cas4_macca_ped cas4_decision2
	SET_CHAR_NEVER_TARGETTED cas4_macca_ped TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS cas4_macca_ped FALSE
	
	
	CREATE_CHAR PEDTYPE_SPECIAL SPECIAL02 -752.2823 2391.0537 127.8083  cas4_paul_ped  // white guy white guy with shorts				
	SET_CHAR_DECISION_MAKER cas4_paul_ped cas4_decision2
	
	SET_CHAR_PROOFS cas4_macca_ped FALSE FALSE FALSE FALSE TRUE
	SET_CHAR_PROOFS cas4_paul_ped FALSE FALSE FALSE FALSE TRUE
	SET_CHAR_NEVER_TARGETTED cas4_paul_ped TRUE
	SET_CHAR_SUFFERS_CRITICAL_HITS cas4_paul_ped FALSE				
	SET_CHAR_RELATIONSHIP cas4_macca_ped ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1 
	SET_CHAR_RELATIONSHIP cas4_paul_ped ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1
	
	SET_CHAR_MAX_HEALTH cas4_paul_ped 200
	SET_CHAR_HEALTH cas4_paul_ped 200
	SET_CHAR_MAX_HEALTH cas4_macca_ped 200
	SET_CHAR_HEALTH cas4_macca_ped 200
	
	IF NOT IS_CHAR_DEAD cas4_paul_ped
	AND NOT IS_CHAR_DEAD cas4_macca_ped						
		SET_CHAR_COORDINATES cas4_paul_ped -738.0978 2385.5923 124.9015 
		SET_CHAR_HEADING cas4_paul_ped 0.0000 	
		SET_CHAR_COORDINATES cas4_macca_ped -736.8244 2385.1021 125.0871 
		SET_CHAR_HEADING cas4_macca_ped 0.0000		
	ENDIF
	

	
		
		
	
	REQUEST_MODEL WALTON // dune buggy
	REQUEST_MODEL DWMOLC1 // pig boy stand in
	REQUEST_MODEL DWMYLC1 // old guy stand in
	
	REQUEST_ANIMATION SMOKING	
	REQUEST_MODEL COLT45

	IF NOT IS_CHAR_DEAD cas4_macca_ped
	AND NOT IS_CHAR_DEAD cas4_paul_ped
		CLEAR_CHAR_TASKS scplayer
		CLEAR_CHAR_TASKS cas4_paul_ped
		CLEAR_CHAR_TASKS cas4_macca_ped
		ADD_BLIP_FOR_CHAR cas4_macca_ped cas4_macca_blip		
		SET_BLIP_AS_FRIENDLY cas4_macca_blip TRUE 
		ADD_BLIP_FOR_CHAR cas4_paul_ped cas4_paul_blip
		SET_BLIP_AS_FRIENDLY cas4_paul_blip TRUE
	ENDIF								


	WHILE  NOT  HAS_MODEL_LOADED WALTON   	   
	OR NOT  HAS_MODEL_LOADED DWMOLC1
	OR NOT  HAS_MODEL_LOADED DWMYLC1 //DWMYLC1
	OR NOT  HAS_ANIMATION_LOADED SMOKING
	OR NOT HAS_ANIMATION_LOADED COLT45   
	   WAIT 0 
	ENDWHILE

	IF NOT IS_CHAR_IN_ANY_CAR  scplayer
		SET_CHAR_COORDINATES scplayer -738.0603 2388.3464 125.1527
	ELSE
		WARP_CHAR_FROM_CAR_TO_COORD scplayer -738.0603 2388.3464 125.1527
	ENDIF
	SET_CHAR_HEADING scplayer 176.0	

	REQUEST_ANIMATION PAULNMAC
	WHILE NOT HAS_ANIMATION_LOADED PAULNMAC
	WAIT 0
	ENDWHILE
	
	RESTORE_CAMERA_JUMPCUT
	SWITCH_WIDESCREEN OFF
		
	MAKE_PLAYER_GANG_REAPPEAR
	
	DO_FADE 1500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	
	SET_PLAYER_CONTROL player1 ON	 	 		
	CLEAR_PRINTS
	//PRINT (CAS4_BB) 5000 1 //We cant walk back mate	
	cas4_played_cutscene_flag = 1
	
ENDIF




///////////////////////////////////////////////
/// If Paul or Maccer dies fail the mission ///
/// ///////////////////////////////////////////


IF cas4_paul_macca_created_flag = 1
	IF IS_CHAR_DEAD cas4_paul_ped
	OR IS_CHAR_DEAD cas4_macca_ped
		CLEAR_PRINTS
	 	IF IS_CHAR_DEAD cas4_macca_ped
	 	AND IS_CHAR_DEAD cas4_paul_ped	 
	 		PRINT (CAS4_47) 3000 1 //~r~Paul and Maccer have been killed!
	 	ELSE
	 		IF IS_CHAR_DEAD cas4_macca_ped
	 			PRINT (CAS4_05) 3000 1	   // " macca's snuffed.....
			ELSE
				PRINT (CAS4_04) 3000 1	   // " Pauls literally.....
			ENDIF
		ENDIF	 		 	
	 	GOTO mission_casino4_failed		
	ENDIF
ENDIF


//////////////////////////////////////////////////////////////////////
/// if Paul & Maccer with player add blip for next part of mission ///
//////////////////////////////////////////////////////////////////////


IF cas4_mission_progression_flag = 0
	IF cas4_played_cutscene_flag = 1
		IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN scplayer cas4_player_car
			GET_MAXIMUM_NUMBER_OF_PASSENGERS cas4_player_car	cas4_free_seats
			IF cas4_paul_with_player = 1
				cas4_free_seats--
			ENDIF
			IF cas4_macca_with_player = 1
				cas4_free_seats--
			ENDIF
		ELSE	
			
			IF NOT IS_CHAR_DEAD cas4_paul_ped
				IF IS_CHAR_IN_ANY_CAR cas4_paul_ped			
					IF cas4_paul_with_player = 1
					   	IF NOT IS_CHAR_DEAD cas4_paul_ped
						   	IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
								TASK_LEAVE_ANY_CAR cas4_paul_ped								
							ENDIF				   	
							ADD_BLIP_FOR_CHAR cas4_paul_ped cas4_paul_blip			
							SET_BLIP_AS_FRIENDLY cas4_paul_blip TRUE
							cas4_paul_with_player = 0
						ENDIF
					ENDIF
										
					IF IS_CHAR_IN_ANY_CAR cas4_paul_ped										
						GET_SCRIPT_TASK_STATUS  cas4_paul_ped TASK_LEAVE_ANY_CAR cas4_task_status
						IF NOT cas4_task_status = PERFORMING_TASK
						AND NOT cas4_task_status = WAITING_TO_START_TASK
							TASK_LEAVE_ANY_CAR cas4_paul_ped
						ENDIF			
					ENDIF																									
				ENDIF					
			ENDIF


			IF NOT IS_CHAR_DEAD cas4_macca_ped				
				IF cas4_macca_with_player = 1
					IF NOT IS_CHAR_DEAD cas4_macca_ped												
						IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
							TASK_LEAVE_ANY_CAR cas4_macca_ped
						ENDIF				
						ADD_BLIP_FOR_CHAR cas4_macca_ped cas4_macca_blip											
						SET_BLIP_AS_FRIENDLY cas4_macca_blip TRUE
						cas4_macca_with_player = 0				
					ENDIF
				ENDIF				
				
				IF IS_CHAR_IN_ANY_CAR cas4_macca_ped					
					GET_SCRIPT_TASK_STATUS cas4_macca_ped TASK_LEAVE_ANY_CAR cas4_task_status
					IF NOT cas4_task_status = PERFORMING_TASK
					AND NOT cas4_task_status = WAITING_TO_START_TASK	
						TASK_LEAVE_ANY_CAR cas4_macca_ped
					ENDIF				
				ENDIF				
			ENDIF
			cas4_paul_told_to_enter = 0
			cas4_macca_told_to_enter = 0		
			cas4_free_seats = 0
		ENDIF
			




		//// STOP THEM FIGHTING IF PLAYER GET A BIG ENOUGH CAR ////

		IF  cas4_fighting > 0
			IF NOT IS_CHAR_DEAD cas4_paul_ped
			AND NOT IS_CHAR_DEAD cas4_macca_ped		
				IF cas4_free_seats > 1							
					CLEAR_CHAR_TASKS cas4_paul_ped
					CLEAR_CHAR_TASKS cas4_macca_ped			
					cas4_fight_audio1 				= 0
					cas4_fight_audio1_completed		= 0
					cas4_fight_audio2 				= 0
					cas4_fight_audio2_completed		= 0
					cas4_fighting = 0		
				ENDIF
			ENDIF
		ENDIF

		 
		/// CHECK ON PAUL AND MACCA AND ADD/REMOVE FROM PLAYERS GROUP IF NOT FIGHTING ////


		IF cas4_fighting = 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				IF NOT IS_CHAR_DEAD cas4_paul_ped
					//IF cas4_paul_busy = 0
						IF cas4_paul_with_player	= 0
							IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer cas4_paul_ped 8.0 8.0 5.0 FALSE			
								IF cas4_free_seats > 0
																																										
									
									IF NOT IS_CAR_DEAD cas4_player_car
										IF IS_CHAR_SITTING_IN_CAR cas4_paul_ped cas4_player_car									
											REMOVE_BLIP cas4_paul_blip
											cas4_free_seats--
											cas4_paul_with_player = 1
										ELSE
											IF cas4_paul_told_to_enter = 0
												TASK_ENTER_CAR_AS_PASSENGER cas4_paul_ped cas4_player_car -2 -1
												cas4_paul_told_to_enter = 1
											ELSE
												GET_SCRIPT_TASK_STATUS cas4_paul_ped TASK_ENTER_CAR_AS_PASSENGER cas4_task_status
												IF  cas4_task_status = FINISHED_TASK								
													CLEAR_CHAR_TASKS cas4_paul_ped
													TASK_ENTER_CAR_AS_PASSENGER cas4_paul_ped cas4_player_car -2 -1
												ENDIF
											ENDIF
										ENDIF
									ENDIF							
								ELSE
									IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
										IF cas4_macca_with_player = 1									
											IF NOT IS_CHAR_DEAD cas4_macca_ped												
												/*
												IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
													TASK_LEAVE_ANY_CAR cas4_macca_ped
												ENDIF */												
												ADD_BLIP_FOR_CHAR cas4_macca_ped cas4_macca_blip											
												SET_BLIP_AS_FRIENDLY cas4_macca_blip TRUE
												cas4_macca_with_player = 0											
											ENDIF							

											IF cas4_fighting = 0
												
												
												
												OPEN_SEQUENCE_TASK cas4_seq						   	
											   	TASK_GOTO_CHAR -1 cas4_macca_ped  -1 2.0												   												   	
											   	TASK_TURN_CHAR_TO_FACE_CHAR -1 cas4_macca_ped
											   	CLOSE_SEQUENCE_TASK cas4_seq
											   	PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_seq
											   	CLEAR_SEQUENCE_TASK cas4_seq
												
												OPEN_SEQUENCE_TASK cas4_seq						   												   	
											   	IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
													TASK_LEAVE_ANY_CAR -1
												ENDIF
											   	TASK_GOTO_CHAR -1 cas4_paul_ped -1 2.0	
											   	TASK_TURN_CHAR_TO_FACE_CHAR -1 cas4_paul_ped
											   	CLOSE_SEQUENCE_TASK cas4_seq
											   	PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_seq
											   	CLEAR_SEQUENCE_TASK cas4_seq	
												CLEAR_PRINTS												
												PRINT (CAS4_44) 10000 1//~s~They're going to keep on fighting until you find a car with enough seats for the both of them.
												cas4_who_started_fight	= 1
												cas4_message_dsplayed = 6
												cas4_fighting = 1						
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ELSE
							    GET_SCRIPT_TASK_STATUS cas4_paul_ped TASK_ENTER_CAR_AS_PASSENGER cas4_task_status
								IF cas4_task_status = PERFORMING_TASK
									CLEAR_CHAR_TASKS cas4_paul_ped					
								ENDIF
								cas4_paul_told_to_enter = 0
							ENDIF				
						ELSE
							IF NOT LOCATE_CHAR_IN_CAR_CHAR_3D scplayer cas4_paul_ped 10.0 10.0 5.0 FALSE
								IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
									TASK_LEAVE_ANY_CAR cas4_paul_ped
								ENDIF						
								//TASK_PLAY_ANIM cas4_paul_ped PnM_Loop_A PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //paul idle
								ADD_BLIP_FOR_CHAR cas4_paul_ped cas4_paul_blip			
								SET_BLIP_AS_FRIENDLY cas4_paul_blip TRUE
								cas4_paul_told_to_enter = 0
								cas4_paul_with_player = 0 
							ENDIF 
						ENDIF
					//ENDIF//	 paul_busy	
				ENDIF

				///////////////////////
				/////// MACCA /////////					  
				///////////////////////

				IF cas4_fighting = 0
					IF NOT IS_CHAR_DEAD cas4_macca_ped
						//IF cas4_macca_busy = 0
							IF cas4_macca_with_player = 0
								IF LOCATE_CHAR_IN_CAR_CHAR_3D scplayer cas4_macca_ped 8.0 8.0 5.0 FALSE
									IF cas4_free_seats > 0
																																																				
										IF NOT IS_CAR_DEAD cas4_player_car
											IF IS_CHAR_SITTING_IN_CAR cas4_macca_ped cas4_player_car	
												REMOVE_BLIP cas4_macca_blip
												cas4_free_seats--
												cas4_macca_with_player = 1							
											ELSE								
												IF cas4_macca_told_to_enter = 0
												   TASK_ENTER_CAR_AS_PASSENGER cas4_macca_ped cas4_player_car -2 -1
												   cas4_macca_told_to_enter = 1
												ELSE
													GET_SCRIPT_TASK_STATUS cas4_macca_ped TASK_ENTER_CAR_AS_PASSENGER cas4_task_status									
													IF cas4_task_status = FINISHED_TASK								
														CLEAR_CHAR_TASKS cas4_macca_ped
														TASK_ENTER_CAR_AS_PASSENGER cas4_macca_ped cas4_player_car -2 -1
													ENDIF
												ENDIF						
											ENDIF
										ENDIF
										

									ELSE
										IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
											IF cas4_paul_with_player = 1										   	
											   	IF NOT IS_CHAR_DEAD cas4_paul_ped
												   	/*
												   	IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
														TASK_LEAVE_ANY_CAR cas4_paul_ped
													ENDIF
													*/
												   	//REMOVE_CHAR_FROM_GROUP cas4_paul_ped
													ADD_BLIP_FOR_CHAR cas4_paul_ped cas4_paul_blip			
													SET_BLIP_AS_FRIENDLY cas4_paul_blip TRUE
													cas4_paul_with_player = 0
												ENDIF
													

												IF cas4_fighting = 0
													//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS cas4_paul_ped  0.0 1.3 0.0  cas4_fight_x cas4_fight_y cas4_fight_z
													
													OPEN_SEQUENCE_TASK cas4_seq						   	
												   	
												   	IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
														TASK_LEAVE_ANY_CAR -1
													ENDIF
												   	TASK_GOTO_CHAR -1 cas4_macca_ped  -1 2.0	
												   	TASK_TURN_CHAR_TO_FACE_CHAR -1 cas4_macca_ped
												   	CLOSE_SEQUENCE_TASK cas4_seq
												   	PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_seq
												   	CLEAR_SEQUENCE_TASK cas4_seq
													
													OPEN_SEQUENCE_TASK cas4_seq						   	
												    TASK_GOTO_CHAR -1 cas4_paul_ped -1 2.0													   	
												   	TASK_TURN_CHAR_TO_FACE_CHAR -1 cas4_paul_ped
												   	CLOSE_SEQUENCE_TASK cas4_seq
												   	PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_seq
												   	CLEAR_SEQUENCE_TASK cas4_seq
													CLEAR_PRINTS
																						
													PRINT (CAS4_44) 10000 1//~s~They're going to keep on fighting until you find a car with enough seats for the both of them.
													cas4_who_started_fight	=  2
													cas4_message_dsplayed = 6
													cas4_fighting = 1						
												ENDIF
											ENDIF
										ENDIF

									ENDIF
								ELSE
									GET_SCRIPT_TASK_STATUS cas4_macca_ped TASK_ENTER_CAR_AS_PASSENGER cas4_task_status
									IF cas4_task_status = PERFORMING_TASK
										CLEAR_CHAR_TASKS cas4_macca_ped
										cas4_macca_told_to_enter = 0
									ENDIF					
								ENDIF
							ELSE
								IF NOT LOCATE_CHAR_IN_CAR_CHAR_3D scplayer cas4_macca_ped 10.0 10.0 5.0 FALSE
									IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
										TASK_LEAVE_ANY_CAR cas4_macca_ped
									ENDIF							
								   //	TASK_PLAY_ANIM cas4_macca_ped PnM_Loop_B PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //paul idle
									ADD_BLIP_FOR_CHAR cas4_macca_ped cas4_macca_blip			
									SET_BLIP_AS_FRIENDLY cas4_macca_blip TRUE
									cas4_macca_told_to_enter = 0
									cas4_macca_with_player = 0 
								ENDIF 
							ENDIF
						//ENDIF	 //IF cas4_macca_busy = 0
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF cas4_fighting = 1	
				IF NOT IS_CHAR_DEAD cas4_macca_ped
				AND NOT IS_CHAR_DEAD cas4_paul_ped
					GET_SCRIPT_TASK_STATUS cas4_macca_ped PERFORM_SEQUENCE_TASK cas4_task_status														
					IF cas4_task_status = FINISHED_TASK
						GET_SCRIPT_TASK_STATUS cas4_paul_ped PERFORM_SEQUENCE_TASK cas4_task_status
						IF cas4_task_status = FINISHED_TASK	
							//GET_DISTANCE_BETWEEN_COORDS_2D X1 Y1 X2 Y2 Distance
							//TASK_GO_TO_COORD_ANY_MEANS -1 cas4_fight_x cas4_fight_y cas4_fight_z PEDMOVE_RUN -1
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS cas4_macca_ped 0.0 1.5 0.0 cas4_fight_x cas4_fight_y cas4_fight_z
							TASK_CHAR_SLIDE_TO_COORD cas4_paul_ped cas4_fight_x cas4_fight_y cas4_fight_z cas4_fight_heading 0.5

							GOSUB cas4_fight_scene
							cas4_fighting = 2
						ENDIF									
					ENDIF
						
					
				ENDIF
			ELSE
				IF cas4_fighting = 2
					
					IF NOT cas4_audio_counter = 0
						IF cas4_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								CLEAR_MISSION_AUDIO cas4_audio_slot2
							ENDIF
							cas4_audio_playing = 1
						ENDIF

						IF cas4_audio_playing = 1
							LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
							cas4_audio_playing = 2
						ENDIF

						IF cas4_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
								PLAY_MISSION_AUDIO cas4_audio_slot1
								PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
								cas4_audio_playing = 3
							ENDIF
						ENDIF

						IF cas4_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
								CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
								IF cas4_audio_slot1 = 1
									cas4_audio_slot1 = 2
									cas4_audio_slot2 = 1
								ELSE
									cas4_audio_slot1 = 1
									cas4_audio_slot2 = 2
								ENDIF
								cas4_audio_counter = 0
								cas4_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
									IF cas4_audio_counter < 110
										cas4_ahead_counter = cas4_audio_counter + 1
										LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF


				/////////// play fight audio here? //////
					
					////////////////////////////////
					IF cas4_who_started_fight	= 1	//paul																																																								
						IF cas4_fight_audio1_completed = 0
							IF NOT IS_MESSAGE_BEING_DISPLAYED
								SWITCH cas4_fight_audio1
									CASE 0
										IF cas4_audio_playing = 0										
											cas4_audio_counter = 9	//CAS4_Ca  I'm the manager i get the front seat
											cas4_fight_audio1 = 1
											GET_GAME_TIMER cas4_text_timer_start										
										ENDIF
										BREAK
									CASE 1
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0												
												cas4_audio_counter = 10 //CAS4_Cb  Hey thats my seat	
												cas4_fight_audio1 = 2
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
										ENDIF	
										BREAK
									CASE 2
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0												
												cas4_audio_counter = 11 //CAS4_Cc  Hey thats my seat	
												cas4_fight_audio1 = 3
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
										ENDIF	
										BREAK
									CASE 3
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0												
												cas4_audio_counter = 12 //CAS4_CD  Hey thats my seat	
												cas4_fight_audio1 = 4
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
										ENDIF	
										BREAK
									CASE 4
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0												
												cas4_audio_counter = 19 //CAS4_CD  Hey thats my seat	
												cas4_fight_audio1 = 5
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
										ENDIF	
										BREAK
																									
									DEFAULT
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0
											   cas4_fight_audio1_completed = 1	
											ENDIF
										ENDIF
										BREAK
								ENDSWITCH
							ENDIF
						ENDIF
					ELSE														
						IF cas4_who_started_fight	= 2 //macca
							IF cas4_fight_audio2_completed = 0													   									
								IF NOT IS_MESSAGE_BEING_DISPLAYED
									SWITCH cas4_fight_audio2
										CASE 0
											IF cas4_audio_playing = 0											
												cas4_audio_counter = 13	// CAS4_CF // We'll come back for you i promise
												cas4_fight_audio2 = 1
												GET_GAME_TIMER cas4_text_timer_start											
											ENDIF
											BREAK
										CASE 1
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 14	//CAS4_CG // Oh no you dont, I remember Preston Guild Hall
													cas4_fight_audio2 = 2
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF	
											BREAK
										CASE 2
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0												
													cas4_audio_counter = 15 //CAS4_Cc  Hey thats my seat	
													cas4_fight_audio2 = 3
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF	
											BREAK
										CASE 3
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0												
													cas4_audio_counter = 16 //CAS4_CD  Hey thats my seat	
													cas4_fight_audio2 = 4
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF	
											BREAK																
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   cas4_fight_audio2_completed = 1	
												ENDIF
											ENDIF
											BREAK
									ENDSWITCH
								ENDIF
							ENDIF
						ENDIF																								
					ENDIF
				ENDIF
			ENDIF
		ENDIF
							




		//// PRINT THE APPROPRIATE MESSAGE //////



			IF IS_CHAR_DEAD cas4_paul_ped
			OR IS_CHAR_DEAD cas4_macca_ped
				CLEAR_PRINTS
			 	IF IS_CHAR_DEAD cas4_macca_ped
			 	AND IS_CHAR_DEAD cas4_paul_ped	 
			 		PRINT (CAS4_47) 3000 1 //~r~Paul and Maccer have been killed!
			 	ELSE
			 		IF IS_CHAR_DEAD cas4_macca_ped
			 			PRINT (CAS4_05) 3000 1	   // " macca's snuffed.....
					ELSE
						PRINT (CAS4_04) 3000 1	   // " Pauls literally.....
					ENDIF
				ENDIF	 		 	
			 	GOTO mission_casino4_failed
			ENDIF







		IF cas4_fighting = 0
			IF cas4_paul_with_player = 0
			AND cas4_macca_with_player = 0				    
				IF cas4_free_seats > 1				   		
			   		IF NOT  cas4_message_dsplayed = 0
			   			
			   			CLEAR_THIS_PRINT cas4_19
						CLEAR_THIS_PRINT CAS4_03
						CLEAR_THIS_PRINT CAS4_02

			   			IF NOT IS_MESSAGE_BEING_DISPLAYED
			   			PRINT (CAS4_37) 7000 1 //~s~Go and pick up Paul and Maccer.						   
						cas4_message_dsplayed = 0
						ENDIF
					ENDIF
			   	ELSE
			   		IF NOT  cas4_message_dsplayed = 1
				   		CLEAR_THIS_PRINT CAS4_37
						CLEAR_THIS_PRINT CAS4_03
						CLEAR_THIS_PRINT CAS4_02

				   		IF NOT IS_MESSAGE_BEING_DISPLAYED
				   			PRINT (cas4_19) 7000 1 //~s~You need to find a vehicle with at least two passneger seats
							cas4_message_dsplayed = 1
						ENDIF
					ENDIF
			   	ENDIF
			ENDIF

			IF cas4_paul_with_player = 1
			AND cas4_macca_with_player = 0	    
			    IF cas4_free_seats > 0
					IF NOT  cas4_message_dsplayed = 2
						CLEAR_THIS_PRINT cas4_19
						CLEAR_THIS_PRINT CAS4_37
						CLEAR_THIS_PRINT CAS4_02
						IF NOT IS_MESSAGE_BEING_DISPLAYED
							PRINT ( CAS4_03 ) 7000 1 ////////////" Get to Maccer "
							cas4_message_dsplayed = 2
						ENDIF
					ENDIF
				ELSE
					IF NOT  cas4_message_dsplayed = 3
						
						CLEAR_THIS_PRINT cas4_37
						CLEAR_THIS_PRINT CAS4_03
						CLEAR_THIS_PRINT CAS4_02
												
						IF NOT IS_MESSAGE_BEING_DISPLAYED
							PRINT (cas4_19) 7000 1 //~s~You need to find a vehicle with at least two passneger seats
							cas4_message_dsplayed = 3
						ENDIF
					ENDIF
				ENDIF
			ENDIF	

			IF cas4_paul_with_player = 0
			AND cas4_macca_with_player = 1		
				IF cas4_free_seats > 0
					IF NOT  cas4_message_dsplayed = 4
						CLEAR_THIS_PRINT cas4_19
						CLEAR_THIS_PRINT CAS4_03
						CLEAR_THIS_PRINT CAS4_37

	   					IF NOT IS_MESSAGE_BEING_DISPLAYED
							PRINT ( CAS4_02 ) 7000 1 ///////////// " Get to Paul "
							cas4_message_dsplayed = 4
						ENDIF
					ENDIF
				ELSE
					IF NOT  cas4_message_dsplayed = 5
						
						CLEAR_THIS_PRINT cas4_37
						CLEAR_THIS_PRINT CAS4_03
						CLEAR_THIS_PRINT CAS4_02

						IF NOT IS_MESSAGE_BEING_DISPLAYED
							PRINT (CAS4_19) 7000 1 //~s~You need to find a vehicle with at least two passneger seats
							cas4_message_dsplayed = 5
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF





		//// DO THE PISSING / PUKING STOPS ///

		
		IF cas4_done_pisspuke = 0
			IF cas4_both_with_player = 1
				IF TIMERA > 30000 //cas4_doanims = 1//TIMERA > 30000
					
					///////// code to play audio goes in here? ////////
					IF NOT cas4_audio_counter = 0
						IF cas4_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								CLEAR_MISSION_AUDIO cas4_audio_slot2
							ENDIF
							cas4_audio_playing = 1
						ENDIF

						IF cas4_audio_playing = 1
							LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
							cas4_audio_playing = 2
						ENDIF

						IF cas4_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
								PLAY_MISSION_AUDIO cas4_audio_slot1
								IF NOT cas4_audio_counter = 65
									IF NOT cas4_audio_counter = 67
										IF NOT cas4_audio_counter = 78
											PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
										ENDIF
									ENDIF
								ENDIF
								cas4_audio_playing = 3
							ENDIF
						ENDIF

						IF cas4_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
								CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
								IF cas4_audio_slot1 = 1
									cas4_audio_slot1 = 2
									cas4_audio_slot2 = 1
								ELSE
									cas4_audio_slot1 = 1
									cas4_audio_slot2 = 2
								ENDIF
								cas4_audio_counter = 0
								cas4_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
									IF cas4_audio_counter < 110
										cas4_ahead_counter = cas4_audio_counter + 1
										LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
					
					
					////////////////////////////
					/// CODE TO PLAY THE SFX ///
					////////////////////////////
					IF NOT cas4_sfx_counter = 0
						IF cas4_sfx_playing = 0
							IF HAS_MISSION_AUDIO_LOADED 3
								CLEAR_MISSION_AUDIO 3
							ENDIF
							cas4_sfx_playing = 1
						ENDIF

						IF cas4_sfx_playing = 1
							LOAD_MISSION_AUDIO 3 cas4_sfx[cas4_sfx_counter]	 // audio fx to be used
							cas4_sfx_playing = 2
						ENDIF

						IF cas4_sfx_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED 3
								IF cas4_sfx_counter = 1
									IF NOT IS_CHAR_DEAD cas4_macca_ped
									  	ATTACH_MISSION_AUDIO_TO_CHAR 3 cas4_macca_ped	 
									ENDIF
								ENDIF
								PLAY_MISSION_AUDIO 3				
								cas4_sfx_playing = 3
							ENDIF
						ENDIF

						IF cas4_sfx_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED 3								
								CLEAR_MISSION_AUDIO 3
								cas4_sfx_counter = 0
								cas4_sfx_playing = 0			
							ENDIF
						ENDIF
					ENDIF


					
					///////////////////////////////////////////////////				
					IF cas4_vomit_status < 5																																								
						/// vomit code here
						IF NOT IS_CHAR_DEAD	cas4_paul_ped
						AND NOT IS_CAR_DEAD cas4_player_car
							IF cas4_vomit_status = 0
								//CLEAR_PRINTS
								//PRINT (CAS4_21) 5000 1 //urgh..I don't feel too good. You'd better pull over.														
								IF cas4_banter1_completed = 1
									IF NOT IS_MESSAGE_BEING_DISPLAYED
									SWITCH cas4_puke_audio
										CASE 0									
											
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped TRUE
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped TRUE

											IF cas4_audio_playing = 0										
												cas4_audio_counter = 62	//CAS4_NB//]You look as pale as a drowned baby, mate!
												cas4_puke_audio = 1
												GET_GAME_TIMER cas4_text_timer_start										
											ENDIF
											BREAK
										CASE 1
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 63//CAS4_NC//]Oh god, I think I'm going to chuck!	
													cas4_puke_audio = 2
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF	
											BREAK
										CASE 2
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 64//CAS4_ND//]What you need is some food down you!	
													cas4_puke_audio = 3
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 3
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 65//CAS4_NE//](Paul nearly vomits)	
													cas4_puke_audio = 4
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 4
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 66//CAS4_NF//]A fried egg sanger with mayonaise will sort you out.	
													cas4_puke_audio = 5
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 5
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 67//CAS4_NG//](Paul nearly vomits again)	
													cas4_puke_audio = 6
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 6
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 68//CAS4_NH//]Or a pickled egg!	
													cas4_puke_audio = 7
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 7
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 69//CAS4_NJ//]Pull over, NOW!	
													cas4_puke_audio = 8
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK																
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   cas4_message_dsplayed = 99
												   REQUEST_ANIMATION FOOD	
												   TIMERB = 0
												   cas4_puke_audio = 0
												   cas4_vomit_status = 1										   
												ENDIF
											ENDIF
											BREAK
									ENDSWITCH
									ENDIF 
								ENDIF //cas4_banter1_completed = 1
										
							ELSE
								IF cas4_vomit_status = 1
									IF TIMERB > 5000
										//CLEAR_PRINTS
										//PRINT_NOW (CAS4_39) 4000 1 //Huh, I don't remember eating that..
																			 									 									 									 									 									 									 
										
										 SWITCH cas4_puke_audio
											CASE 0
												IF cas4_audio_playing = 0
													cas4_audio_counter = 70// CAS4_OA//]Shhi- huuuurghhh -hit!
													cas4_puke_audio = 1
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
												BREAK
											CASE 1
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 71// CAS4_OB//]I've go- huuurrrgghh!	
														cas4_puke_audio = 2
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF	
												BREAK
											CASE 2
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 72// CAS4_OC//]Got the- Hhhuurrgghh!	
														cas4_puke_audio = 3
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 3
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 73// CAS4_OD//]Dry- hurgh!	
														cas4_puke_audio = 4
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 4
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 74// CAS4_OE//]Dry- hurgh!	
														cas4_puke_audio = 5
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 5
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 75// CAS4_OF//]Dry heaves!	
														cas4_puke_audio = 6
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 6
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 76// CAS4_OG//]Oh, I thuh- think it's past...	
														cas4_puke_audio = 7
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 7
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 77// CAS4_OH//]Or how about a pint of cod liver oil, that would sort your guts out!	
														cas4_puke_audio = 8
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 8
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 78// CAS4_OJ//](Paul vomiting violently)	
														cas4_puke_audio = 9 
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 9
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 79// CAS4_OK//]Leave him, man.	
														cas4_puke_audio = 10
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 10
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 80// CAS4_OL//]The good doctor is just trying to help!	
														cas4_puke_audio = 11
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 11
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 81// CAS4_OM//]Fuh-huh-Fuck off!	
														cas4_puke_audio = 12
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK																
											DEFAULT
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
													   TIMERA = 0									
														cas4_paul_busy = 0
														cas4_done_puke = 1
														cas4_vomit_status = 9999
													ENDIF
												ENDIF
												BREAK
										ENDSWITCH
										
									   	
										 
										 
										

										
									ELSE							
										IF HAS_ANIMATION_LOADED FOOD
									    AND IS_CAR_STOPPED cas4_player_car
											GET_OFFSET_FROM_CAR_IN_WORLD_COORDS cas4_player_car 4.0 2.0 0.0 cas4_carx cas4_cary cas4_carz			   
										   	OPEN_SEQUENCE_TASK cas4_seq
										   	TASK_LEAVE_ANY_CAR -1
										   	TASK_GO_STRAIGHT_TO_COORD -1 cas4_carx cas4_cary cas4_carz PEDMOVE_SPRINT 5000
										   	TASK_PLAY_ANIM	 -1  EAT_Vomit_P FOOD 1004.0 FALSE FALSE FALSE FALSE -1 // latest addition											   
										   	TASK_ENTER_CAR_AS_PASSENGER -1 cas4_player_car -2 -1
										   	CLOSE_SEQUENCE_TASK cas4_seq
										   	PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_seq
										   	CLEAR_SEQUENCE_TASK cas4_seq					
											cas4_vomit_status = 2
											cas4_paul_busy = 1
										ENDIF
									ENDIF
								ELSE
									
									IF NOT cas4_vomit_status = 3
									    IF cas4_vomit_status < 3
										    
											SWITCH cas4_puke_audio
												CASE 0
													IF cas4_audio_playing = 0
														cas4_audio_counter = 70// CAS4_OA//]Shhi- huuuurghhh -hit!
														cas4_puke_audio = 1
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
													BREAK
												CASE 1
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 71// CAS4_OB//]I've go- huuurrrgghh!	
															cas4_puke_audio = 2
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF	
													BREAK
												CASE 2
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 72// CAS4_OC//]Got the- Hhhuurrgghh!	
															cas4_puke_audio = 3
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 3
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 73// CAS4_OD//]Dry- hurgh!	
															cas4_puke_audio = 4
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 4
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 74// CAS4_OE//]Dry- hurgh!	
															cas4_puke_audio = 5
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 5
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 75// CAS4_OF//]Dry heaves!	
															cas4_puke_audio = 6
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 6
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 76// CAS4_OG//]Oh, I thuh- think it's past...	
															cas4_puke_audio = 7
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK																											
												CASE 7
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 77// CAS4_OH//]Or how about a pint of cod liver oil, that would sort your guts out!	
															cas4_puke_audio = 8
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												DEFAULT
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0													   										   
														   cas4_puke_audio_completed = 1
														ENDIF
													ENDIF
													BREAK
											ENDSWITCH									    									    									    
										    
										/*
										ELSE	
											IF cas4_puke_audio < 10
												cas4_puke_audio = 10
											ENDIF
											SWITCH cas4_puke_audio										
												CASE 10
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 79// CAS4_OK//]Leave him, man.	
															cas4_puke_audio = 11
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 11
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 80// CAS4_OL//]The good doctor is just trying to help!	
															cas4_puke_audio = 12
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 12
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 81// CAS4_OM//]Fuh-huh-Fuck off!	
															cas4_puke_audio = 13
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												DEFAULT
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
														   cas4_puke_audio_completed = 1													   													   										   
														ENDIF
													ENDIF
													BREAK
											ENDSWITCH */										
										ENDIF

									ENDIF
									
									
									IF cas4_vomit_status = 2						    	
										GET_SCRIPT_TASK_STATUS cas4_paul_ped PERFORM_SEQUENCE_TASK cas4_task_status														
										IF  cas4_task_status = FINISHED_TASK
									    	REMOVE_ANIMATION FOOD
									    	cas4_vomit_status = 5
											cas4_done_puke = 1
											cas4_paul_busy = 0
											
											TIMERA =0
									    ELSE							    
										    IF IS_CHAR_PLAYING_ANIM cas4_paul_ped Eat_Vomit_P															
												GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS cas4_paul_ped 0.355 -0.116 0.048 cas4_carx cas4_cary cas4_carz															
												GET_CHAR_ANIM_CURRENT_TIME cas4_paul_ped  Eat_Vomit_P cas4_animation_time						
												CREATE_FX_SYSTEM puke cas4_carx cas4_cary cas4_carz TRUE cas4_vomit
												cas4_paul_playing_fx = 0
												cas4_animation_time = 0.0
												cas4_vomit_status = 3										
											ENDIF
										ENDIF
									ELSE
										IF cas4_vomit_status = 3												
											
											GET_SCRIPT_TASK_STATUS cas4_paul_ped PERFORM_SEQUENCE_TASK cas4_task_status														
											IF  cas4_task_status = FINISHED_TASK
										    	REMOVE_ANIMATION FOOD
										    	cas4_vomit_status = 5
												cas4_done_puke = 1
												cas4_paul_busy = 0
												
												IF cas4_paul_playing_fx = 1
													STOP_FX_SYSTEM cas4_vomit
													KILL_FX_SYSTEM cas4_vomit
													cas4_paul_playing_fx = 0
												ENDIF
												TIMERA = 0
										    ENDIF
											
											IF NOT cas4_animation_time = 1.0																
												IF cas4_paul_playing_fx = 0
													IF cas4_animation_time >= 0.42
														PLAY_FX_SYSTEM cas4_vomit
														IF cas4_audio_playing = 0
															cas4_audio_counter = 78//cas4_text[78] = CAS4_OJ//](Paul vomiting violently)
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
														cas4_paul_playing_fx = 1
													ENDIF								
												ENDIF	 

												IF NOT IS_CHAR_DEAD cas4_paul_ped
													IF IS_CHAR_PLAYING_ANIM cas4_paul_ped Eat_Vomit_P
														GET_CHAR_ANIM_CURRENT_TIME cas4_paul_ped Eat_Vomit_P cas4_animation_time
													ELSE
														cas4_animation_time = 1.0
													ENDIF
												ELSE
													cas4_animation_time = 1.0	 
												ENDIF							
											ELSE
												STOP_FX_SYSTEM cas4_vomit
												KILL_FX_SYSTEM cas4_vomit											
												TIMERA = 0
												cas4_paul_playing_fx = 0
												
												cas4_paul_busy = 0
												cas4_vomit_status = 4
											ENDIF																								
										ELSE	
											GET_SCRIPT_TASK_STATUS cas4_paul_ped PERFORM_SEQUENCE_TASK cas4_task_status														
											IF  cas4_task_status = FINISHED_TASK
										    	REMOVE_ANIMATION FOOD
										    	cas4_vomit_status = 5
												cas4_done_puke = 1
												cas4_paul_busy = 0
												
												TIMERA = 0
												IF cas4_paul_playing_fx = 1
													STOP_FX_SYSTEM cas4_vomit
													KILL_FX_SYSTEM cas4_vomit
													cas4_paul_playing_fx = 0
												ENDIF
										    ENDIF									
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ELSE										
						
						
													
						
						/// piss code here
						IF cas4_piss_status < 5
							//cas4_macca_busy = 1
							IF NOT IS_CHAR_DEAD	cas4_macca_ped
							AND NOT IS_CAR_DEAD cas4_player_car
								//////////
								//////////
								IF cas4_piss_status = 0
									IF cas4_banter2_completed = 1
										IF NOT IS_MESSAGE_BEING_DISPLAYED
										SWITCH cas4_piss_audio
											CASE 0
												IF cas4_audio_playing = 0											
													cas4_audio_counter = 50 // CAS4_KE I need a piss, mate!
													cas4_piss_audio = 1
													GET_GAME_TIMER cas4_text_timer_start											
												ENDIF
												BREAK
											CASE 1
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 51 // CAS4_KF Oh fuck, we're screwed!	
														cas4_piss_audio = 2
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF	
												BREAK
											CASE 2
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 52 // CAS4_KG Can't it wait?	
														cas4_piss_audio = 3
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 3										
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 53 // CAS4_KH I'm fit to burst, ol'lad!	
														cas4_piss_audio = 5
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK
											CASE 4
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
														cas4_audio_counter = 54 // CAS4_KJ You've got to pull over, he's priapic, it'll go everywhere!
														cas4_piss_audio = 5
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF
												ENDIF
												BREAK																								
											DEFAULT
												GET_GAME_TIMER cas4_text_timer_end
												cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
												IF cas4_text_timer_diff > 1000
													IF cas4_audio_playing = 0
													   cas4_message_dsplayed = 99
													   REQUEST_ANIMATION FOOD	
													   TIMERB = 0
													   cas4_piss_audio = 0
													   cas4_piss_status = 1										   
													ENDIF
												ENDIF
												BREAK
										ENDSWITCH
										ENDIF
									ENDIF//cas4_banter2_completed = 1
									
									cas4_piss_audio_completed = 0																																																						
								ELSE
									IF cas4_piss_status = 1
										IF TIMERB > 5000
											//CLEAR_PRINTS
											//PRINT_NOW (CAS4_38) 5000 1 //Err.. never mind. I think i've had an accident.
											IF NOT IS_MESSAGE_BEING_DISPLAYED
											SWITCH cas4_piss_audio											
												CASE 0																								
													IF cas4_audio_playing = 0													
														cas4_audio_counter = 55 // CAS4_LA I can't hold it any longer!	
														cas4_piss_audio = 2
														GET_GAME_TIMER cas4_text_timer_start
													ENDIF													
													BREAK
												CASE 1
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 56 // CAS4_LB Oh fuck!	
															cas4_piss_audio = 3
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 2										
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 57 // CAS4_LC Hey, what the fuck?	
															cas4_piss_audio = 5
															cas4_sfx_counter = 1
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 3
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 58 // CAS4_LD Argh! Stop it!
															cas4_piss_audio = 5
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK																								
												CASE 4
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 58 // CAS4_LD Argh! Stop it!
															cas4_piss_audio = 5
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK
												CASE 5
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
															cas4_audio_counter = 59 // CAS4_LE Point it out the fucking window!
															cas4_piss_audio = 6
															GET_GAME_TIMER cas4_text_timer_start
														ENDIF
													ENDIF
													BREAK																																			
												DEFAULT
													GET_GAME_TIMER cas4_text_timer_end
													cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
													IF cas4_text_timer_diff > 1000
														IF cas4_audio_playing = 0
														   cas4_message_dsplayed = 99													   
														   TIMERA = 0													   										   
														   CLEAR_MISSION_AUDIO 3
															cas4_sfx_counter = 0
															cas4_sfx_playing = 0
														   cas4_macca_busy = 0
															cas4_piss_status = 9999
														ENDIF
													ENDIF
													BREAK
											ENDSWITCH
											ENDIF																																										
										ELSE
											
											IF HAS_ANIMATION_LOADED PAULNMAC
										    AND IS_CAR_STOPPED cas4_player_car
												GET_OFFSET_FROM_CAR_IN_WORLD_COORDS cas4_player_car 4.0 2.0 0.0 cas4_carx cas4_cary cas4_carz			   								   	
											   	OPEN_SEQUENCE_TASK cas4_seq									   
											   	TASK_LEAVE_ANY_CAR -1
											   	TASK_GO_STRAIGHT_TO_COORD -1 cas4_carx cas4_cary cas4_carz PEDMOVE_SPRINT 5000
											   	TASK_PLAY_ANIM -1 piss_in PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // paul idle
											    TASK_PLAY_ANIM -1 piss_loop PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // paul idle
											    TASK_PLAY_ANIM -1 piss_out PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // paul idle											   
											   	TASK_ENTER_CAR_AS_PASSENGER -1 cas4_player_car -2 -1
											   	CLOSE_SEQUENCE_TASK cas4_seq
											   	PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_seq
											   	CLEAR_SEQUENCE_TASK cas4_seq					
												cas4_piss_status = 2
												cas4_macca_busy = 1
											ENDIF
										ENDIF
									ELSE
										IF cas4_piss_status = 2						    	
											GET_SCRIPT_TASK_STATUS cas4_macca_ped PERFORM_SEQUENCE_TASK cas4_task_status														
											IF  cas4_task_status = FINISHED_TASK
										    	cas4_piss_status = 5
												cas4_macca_busy = 0
											   
										    ELSE							    								    
											    	
											    IF  cas4_task_status = PERFORMING_TASK
											    	GET_SEQUENCE_PROGRESS cas4_macca_ped  cas4_task_status
													IF cas4_task_status > 4
														IF cas4_macca_playing_fx = 1
															STOP_FX_SYSTEM cas4_piss
															KILL_FX_SYSTEM cas4_piss
															CLEAR_MISSION_AUDIO 3
															cas4_macca_playing_fx = 0
														ENDIF
													ENDIF
												ENDIF
											    
											    
											    IF IS_CHAR_PLAYING_ANIM cas4_macca_ped piss_in																	
													GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS cas4_macca_ped 0.0 0.0 -0.2 cas4_carx cas4_cary cas4_carz															
													//GET_OFFSET_FROM_CAR_IN_WORLD_COORDS cas4_player_car 0.0 0.2 0.0 cas4_dirx	cas4_diry cas4_dirz
													GET_CHAR_ANIM_CURRENT_TIME cas4_macca_ped  piss_in cas4_animation_time						
													CREATE_FX_SYSTEM_ON_CHAR_WITH_DIRECTION petrolcan cas4_macca_ped  0.0 0.116 0.048 cas4_carx cas4_cary cas4_carz TRUE cas4_piss
													ATTACH_FX_SYSTEM_TO_CHAR_BONE cas4_piss cas4_macca_ped BONE_PELVIS
													//CREATE_FX_SYSTEM_WITH_DIRECTION petrolcan cas4_carx cas4_cary cas4_carz cas4_dirx cas4_diry cas4_dirz TRUE cas4_piss
													cas4_animation_time = 0.0
													cas4_piss_status = 3	
													cas4_macca_playing_fx = 0
												ENDIF									
											ENDIF
										ELSE
											IF cas4_piss_status = 3																					
												GET_SCRIPT_TASK_STATUS cas4_macca_ped PERFORM_SEQUENCE_TASK cas4_task_status														
												IF  cas4_task_status = FINISHED_TASK
											    	cas4_piss_status = 5
													cas4_macca_busy = 0
												   
													
													IF cas4_macca_playing_fx = 1
														STOP_FX_SYSTEM cas4_piss
														KILL_FX_SYSTEM cas4_piss
														CLEAR_MISSION_AUDIO 3
														cas4_macca_playing_fx = 0
													ENDIF
												ELSE
													IF  cas4_task_status = PERFORMING_TASK
														GET_SEQUENCE_PROGRESS cas4_macca_ped  cas4_task_status
														IF cas4_task_status > 4
															IF cas4_macca_playing_fx = 1
																STOP_FX_SYSTEM cas4_piss
																KILL_FX_SYSTEM cas4_piss
																CLEAR_MISSION_AUDIO 3
																cas4_macca_playing_fx = 0
															ENDIF
														ENDIF
													ENDIF	
											    
											    ENDIF										  										
													
												IF IS_CHAR_PLAYING_ANIM cas4_macca_ped piss_in
													GET_CHAR_ANIM_CURRENT_TIME cas4_macca_ped piss_in cas4_animation_time
													IF NOT cas4_animation_time = 1.0																
														IF cas4_macca_playing_fx = 0
															IF cas4_animation_time >= 0.6
																IF cas4_audio_playing = 0
																	cas4_audio_counter = 60 // CAS4_MA Oh, fuck, that is goooood.								
																	GET_GAME_TIMER cas4_text_timer_start
																ENDIF
																PLAY_FX_SYSTEM cas4_piss
																cas4_sfx_counter = 1
																cas4_macca_playing_fx = 1
															  	cas4_piss_status = 4
															ENDIF								
														ENDIF
													ENDIF
												ENDIF
											ELSE
												
												
												
												GET_SCRIPT_TASK_STATUS cas4_macca_ped PERFORM_SEQUENCE_TASK cas4_task_status														
												IF  cas4_task_status = FINISHED_TASK
											    	cas4_piss_status = 5
													cas4_macca_busy = 0
												   
											    	IF cas4_macca_playing_fx = 1
														STOP_FX_SYSTEM cas4_piss
														KILL_FX_SYSTEM cas4_piss
														CLEAR_MISSION_AUDIO 3
														cas4_macca_playing_fx = 0
													ENDIF
											    ELSE
													IF  cas4_task_status = PERFORMING_TASK
														GET_SEQUENCE_PROGRESS cas4_macca_ped  cas4_task_status
														IF cas4_task_status > 4
															IF cas4_macca_playing_fx = 1
																STOP_FX_SYSTEM cas4_piss
																KILL_FX_SYSTEM cas4_piss
																CLEAR_MISSION_AUDIO 3
																cas4_macca_playing_fx = 0
															ENDIF
														ENDIF
													ENDIF
											    ENDIF
											    											
												IF IS_CHAR_PLAYING_ANIM cas4_macca_ped piss_out
													GET_CHAR_ANIM_CURRENT_TIME cas4_macca_ped piss_out cas4_animation_time												
													IF cas4_macca_playing_fx = 1
														IF cas4_animation_time >= 0.42
															STOP_FX_SYSTEM cas4_piss
															KILL_FX_SYSTEM cas4_piss														
															CLEAR_MISSION_AUDIO 3
															cas4_macca_playing_fx = 0
														ENDIF								
													ENDIF																								
												ENDIF																												    																																																												
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								//////////
								//////////

							ENDIF
						ELSE
							cas4_macca_busy = 0	
							cas4_done_pisspuke = 1
							IF NOT IS_CHAR_DEAD	cas4_macca_ped
							AND NOT IS_CAR_DEAD cas4_player_car
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped FALSE
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped FALSE
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			
			
			ELSE //cas4_both_with_player = 1
				IF cas4_macca_busy = 1
						IF NOT IS_CHAR_DEAD	cas4_macca_ped				
						CLEAR_CHAR_TASKS cas4_macca_ped					
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped FALSE
					ENDIF
					STOP_FX_SYSTEM cas4_piss
					KILL_FX_SYSTEM cas4_piss														
					CLEAR_MISSION_AUDIO 3
					
					cas4_piss_status = 5					
					cas4_macca_busy = 0
					cas4_macca_playing_fx = 0
					cas4_done_pisspuke = 1					
				ENDIF
				IF cas4_paul_busy = 1 
					IF NOT IS_CHAR_DEAD	cas4_paul_ped
						CLEAR_CHAR_TASKS cas4_paul_ped
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped FALSE					
					ENDIF
					STOP_FX_SYSTEM cas4_vomit
					KILL_FX_SYSTEM cas4_vomit														
					CLEAR_MISSION_AUDIO 3
					REMOVE_ANIMATION FOOD

					cas4_vomit_status = 5
					cas4_done_puke = 1
					cas4_paul_busy = 0
					cas4_paul_playing_fx = 0
				ENDIF
			
			ENDIF
		ELSE
			
			IF cas4_macca_playing_fx = 1
				STOP_FX_SYSTEM cas4_piss
				KILL_FX_SYSTEM cas4_piss
				cas4_macca_playing_fx = 0
			ENDIF
			IF cas4_paul_playing_fx = 1				
				STOP_FX_SYSTEM cas4_vomit
				KILL_FX_SYSTEM cas4_vomit
				cas4_paul_playing_fx = 0
			ENDIF				
	
		ENDIF




		//// DETERMINE WHETHER END BLIP SHOULD BE DISPLAYED ///

		IF cas4_macca_with_player = 1
		AND cas4_paul_with_player = 1
			cas4_both_with_player = 1
			IF cas4_snake_blip_flag = 0		
				IF cas4_paul_busy = 0
				AND cas4_macca_busy = 0
					IF NOT IS_CHAR_DEAD cas4_paul_ped
					AND NOT IS_CHAR_DEAD cas4_macca_ped
					AND NOT IS_CAR_DEAD cas4_player_car
						IF IS_CHAR_SITTING_IN_CAR cas4_paul_ped cas4_player_car
						AND IS_CHAR_SITTING_IN_CAR cas4_macca_ped cas4_player_car
							TIMERA = 0
							CLEAR_PRINTS
							PRINT ( CAS4_16 ) 8000 1 ///////////// " snake stop "
							ADD_BLIP_FOR_COORD -24.0 2331.0 24.0 cas4_snake_blip
							cas4_snake_blip_flag = 1
							cas4_audio_left_behind_played 	= 0
							cas4_left_behind				= 0
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ELSE
			cas4_both_with_player = 0
			IF cas4_snake_blip_flag = 1
				REMOVE_BLIP cas4_snake_blip
				cas4_snake_blip_flag = 0
			ENDIF


			
			IF cas4_audio_left_behind_played = 0				 
				IF NOT cas4_audio_counter = 0
					IF cas4_audio_playing = 0
						IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
							CLEAR_MISSION_AUDIO cas4_audio_slot2
						ENDIF
						cas4_audio_playing = 1
					ENDIF

					IF cas4_audio_playing = 1
						LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
						cas4_audio_playing = 2
					ENDIF

					IF cas4_audio_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
							PLAY_MISSION_AUDIO cas4_audio_slot1
							IF cas4_audio_counter = 7
							OR cas4_audio_counter = 8
							PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
							ENDIF
							cas4_audio_playing = 3
						ENDIF
					ENDIF

					IF cas4_audio_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
							CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
							IF cas4_audio_slot1 = 1
								cas4_audio_slot1 = 2
								cas4_audio_slot2 = 1
							ELSE
								cas4_audio_slot1 = 1
								cas4_audio_slot2 = 2
							ENDIF
							cas4_audio_counter = 0
							cas4_audio_playing = 0
						ELSE
							IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								IF cas4_audio_counter < 110
									cas4_ahead_counter = cas4_audio_counter + 1
									LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
								  
			IF NOT IS_MESSAGE_BEING_DISPLAYED				  
				IF NOT IS_CHAR_DEAD cas4_paul_ped
				AND NOT IS_CHAR_DEAD cas4_macca_ped
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cas4_paul_ped	 10.0 10.0 5.0	FALSE
					OR 	LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cas4_macca_ped 10.0 10.0 5.0 FALSE
						IF IS_CHAR_IN_ANY_CAR scplayer
							cas4_no_car_played		= 0						
							cas4_no_car 			= 1
							IF cas4_audio_left_behind_played = 0
								
								IF cas4_macca_with_player = 1
								AND cas4_paul_with_player = 0
									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cas4_paul_ped	 10.0 10.0 5.0	FALSE								
										SWITCH cas4_left_behind
										CASE 0
											IF cas4_audio_playing = 0
												cas4_audio_counter = 40 //Hey, wait for me, sunshine!
												cas4_left_behind = 1
												GET_GAME_TIMER cas4_text_timer_start								
											ENDIF
											BREAK									
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   cas4_left_behind = 0
												   cas4_audio_left_behind_played = 1
												ENDIF
											ENDIF
											BREAK
										ENDSWITCH								
									ENDIF
								ENDIF


								IF cas4_macca_with_player = 0														
								AND cas4_paul_with_player = 1
									IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer cas4_macca_ped	 10.0 10.0 5.0	FALSE								
										SWITCH cas4_left_behind
										CASE 0
											IF cas4_audio_playing = 0
												cas4_audio_counter = 39 //CAS4_HB I want to come too
												cas4_left_behind = 1
												GET_GAME_TIMER cas4_text_timer_start								
											ENDIF
											BREAK									
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   cas4_left_behind = 0
												   cas4_audio_left_behind_played = 1
												ENDIF
											ENDIF
											BREAK
										ENDSWITCH
									ENDIF
								ENDIF

								IF cas4_macca_with_player = 0																
								AND cas4_paul_with_player = 0
									SWITCH cas4_left_behind
									CASE 0
										IF cas4_audio_playing = 0
											cas4_audio_counter = 38 //CAS4_HA Wait our kid, wait!
											cas4_left_behind = 1
											GET_GAME_TIMER cas4_text_timer_start								
										ENDIF
										BREAK									
									DEFAULT
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0
											   cas4_left_behind = 0
											   cas4_audio_left_behind_played = 1
											ENDIF
										ENDIF
										BREAK
									ENDSWITCH
								ENDIF
								
							ENDIF
							
						ELSE
							cas4_audio_left_behind_played = 0
							cas4_left_behind			= 0
							IF cas4_no_car_played		= 0
								SWITCH cas4_no_car
									CASE 0
										IF cas4_audio_playing = 0
											IF NOT IS_CHAR_DEAD	cas4_macca_ped
											STOP_CHAR_FACIAL_TALK cas4_macca_ped
										 	START_CHAR_FACIAL_TALK cas4_macca_ped	 3000
											ENDIF
											cas4_audio_counter = 7 //CAS4_BA//]My legs are fucked and my head's fuckin' pounding!
											cas4_no_car = 1
											GET_GAME_TIMER cas4_text_timer_start								
										ENDIF
										BREAK
									CASE 1
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0
												IF NOT IS_CHAR_DEAD	cas4_paul_ped
												AND NOT IS_CHAR_DEAD cas4_macca_ped
												STOP_CHAR_FACIAL_TALK cas4_macca_ped
											 	START_CHAR_FACIAL_TALK cas4_paul_ped	 3000
												ENDIF
												cas4_audio_counter = 8 //CAS4_BB//]We can't walk back, mate...
												cas4_no_car = 2
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
										ENDIF	
										BREAK
									DEFAULT
										GET_GAME_TIMER cas4_text_timer_end
										cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
										IF cas4_text_timer_diff > 1000
											IF cas4_audio_playing = 0
											    IF NOT IS_CHAR_DEAD	cas4_paul_ped
												AND NOT IS_CHAR_DEAD cas4_macca_ped
												STOP_CHAR_FACIAL_TALK cas4_macca_ped
											 	STOP_CHAR_FACIAL_TALK cas4_paul_ped	 
												ENDIF
											    cas4_no_car = 0
											    cas4_no_car_played = 1
											ENDIF
										ENDIF
										BREAK
								ENDSWITCH
							ENDIF																																
						ENDIF
					ELSE
					cas4_audio_left_behind_played = 0
					cas4_left_behind			= 0
					cas4_no_car_played		= 0						
					cas4_no_car 			= 1
					ENDIF
				ENDIF
			ENDIF							
		ENDIF


		IF 	cas4_snake_blip_flag = 1
			IF cas4_paul_busy = 0
			AND cas4_macca_busy = 0
				//// in car audio banter 1 & 2 stuff goes here ? /////
				IF cas4_banter2_completed = 0
		
					IF NOT cas4_audio_counter = 0
						IF cas4_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								CLEAR_MISSION_AUDIO cas4_audio_slot2
							ENDIF
							cas4_audio_playing = 1
						ENDIF

						IF cas4_audio_playing = 1
							LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
							cas4_audio_playing = 2
						ENDIF

						IF cas4_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
								PLAY_MISSION_AUDIO cas4_audio_slot1
								PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
								cas4_audio_playing = 3
							ENDIF
						ENDIF

						IF cas4_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
								CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
								IF cas4_audio_slot1 = 1
									cas4_audio_slot1 = 2
									cas4_audio_slot2 = 1
								ELSE
									cas4_audio_slot1 = 1
									cas4_audio_slot2 = 2
								ENDIF
								cas4_audio_counter = 0
								cas4_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
									IF cas4_audio_counter < 110
										cas4_ahead_counter = cas4_audio_counter + 1
										LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
								
				
					 												
					IF cas4_banter1_completed = 0
												
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						SWITCH cas4_banter1
							CASE 0
								IF cas4_audio_playing = 0									
									IF NOT IS_CHAR_DEAD	cas4_macca_ped
									AND NOT IS_CAR_DEAD cas4_player_car
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped TRUE
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped TRUE
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
									ENDIF
									cas4_audio_counter = 20// CAS4_DA//]Where's the rest of the band, guys?
									cas4_banter1 = 1
									GET_GAME_TIMER cas4_text_timer_start									
								ENDIF
								BREAK
							CASE 1
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										cas4_audio_counter = 21//] = CAS4_DB//]Oh bollox! Maccer, where are the boys?
										cas4_banter1 = 2
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF	
								BREAK
							CASE 2
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										cas4_audio_counter = 22//] = CAS4_DC//]I don't fuckin' know, do I.	
										cas4_banter1 = 3
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 3
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										cas4_audio_counter = 23//] = CAS4_DD//]I remember snakes, lots of snakes...	
										cas4_banter1 = 4
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF
								BREAK								
							CASE 4
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										cas4_audio_counter = 24//] = CAS4_DE//]There's a snake farm not far from here, we can go check it out.
										cas4_banter1 = 5
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF
								BREAK
							DEFAULT
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
									   IF NOT IS_CHAR_DEAD	cas4_macca_ped
										AND NOT IS_CAR_DEAD cas4_player_car
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped FALSE
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped FALSE
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
										ENDIF
									   cas4_banter1_completed = 1
									   IF TIMERA > 25000
									   		TIMERA  = 25000
									   ENDIF 	
									ENDIF
								ENDIF
								BREAK
						ENDSWITCH
						ENDIF
					ELSE
						IF cas4_banter2_completed = 0
						AND	cas4_done_puke = 1
							IF NOT IS_MESSAGE_BEING_DISPLAYED
							SWITCH cas4_banter2
								CASE 0
									IF cas4_audio_playing = 0										
										IF NOT IS_CHAR_DEAD	cas4_macca_ped
										AND NOT IS_CAR_DEAD cas4_player_car
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped TRUE
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped TRUE
										ENDIF
										cas4_audio_counter = 25// = CAS4_EA//]I don't recognise this part of Manchester, our kid...										
										cas4_banter2 = 1
										GET_GAME_TIMER cas4_text_timer_start										
									ENDIF
									BREAK
								CASE 1
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
											cas4_audio_counter = 26// = CAS4_EB//]How many times do I have to tell you, we're in America!
											cas4_banter2 = 2
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF	
									BREAK
								CASE 2
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
											cas4_audio_counter = 27// = CAS4_EC//]America? Wait 'til I tell me ma about this!
											cas4_banter2 = 3
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
									BREAK
								CASE 3
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
											cas4_audio_counter = 28// = CAS4_ED//]He's like a fucking stuck record. He'll ask about Las Venturas next.
											cas4_banter2 = 4
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
									BREAK
								CASE 4
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
											cas4_audio_counter = 29// = CAS4_EE//]Las Venturas? Always wanted to go there, great tits.
											cas4_banter2 = 5
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
									BREAK
								CASE 5
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
											cas4_audio_counter = 30//  = CAS4_EF//]Don't start, not in this confined space.
											cas4_banter2 = 6
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
									BREAK
								CASE 6
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
											cas4_audio_counter = 31// = CAS4_EG///]Bouncing, wobbling, creamy tits...
											cas4_banter2 = 7 
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
									BREAK
								CASE 7
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
											cas4_audio_counter = 32// = CAS4_EH//]LEAVE YOURSELF ALONE!
											cas4_banter2 = 8
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
									BREAK
								
								DEFAULT
									GET_GAME_TIMER cas4_text_timer_end
									cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
									IF cas4_text_timer_diff > 1000
										IF cas4_audio_playing = 0
										   IF NOT IS_CHAR_DEAD	cas4_macca_ped
											AND NOT IS_CAR_DEAD cas4_player_car
												SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped FALSE
												SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped FALSE
											ENDIF
										   cas4_banter2_completed = 1	
										    IF TIMERA > 25000
									   			TIMERA  = 25000
									   		ENDIF
										ENDIF
									ENDIF
									BREAK
							ENDSWITCH
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ELSE
				IF cas4_snake_blip_flag = 1
					REMOVE_BLIP cas4_snake_blip
					cas4_snake_blip_flag = 0
				ENDIF
			ENDIF
		ENDIF




	ENDIF	 						
ENDIF //cas4_mission_progression_flag = 0





////////////////////////////////////////////////


IF cas4_played_cutscene_flag = 1
AND cas4_snake_car_created_flag = 0
    IF LOCATE_CHAR_ANY_MEANS_3D scplayer -32.0 2337.0 24.0 100.0 100.0 20.0 FALSE
	   	CUSTOM_PLATE_FOR_NEXT_CAR WALTON &__TIM___
	   	CREATE_CAR  WALTON -50.0 2358.0 24.0 cas4_quad_car	   	
	   	LOCK_CAR_DOORS cas4_quad_car CARLOCK_LOCKOUT_PLAYER_ONLY
	   	SET_CAR_CAN_BE_DAMAGED cas4_quad_car FALSE	   	
	   	SET_CAR_HEADING	cas4_quad_car 188.0	 
	   	FREEZE_CAR_POSITION cas4_quad_car TRUE
	   	
		CUSTOM_PLATE_FOR_NEXT_CAR WALTON &_TAMMY__
	   	CREATE_CAR  WALTON -46.0 2355.0 24.0 cas4_dune_car
	   	LOCK_CAR_DOORS cas4_dune_car CARLOCK_LOCKOUT_PLAYER_ONLY
	   	SET_CAR_HEADING	cas4_dune_car 188.0
	   	FREEZE_CAR_POSITION cas4_dune_car TRUE
	   	SET_CAR_CAN_BE_DAMAGED	cas4_dune_car FALSE
	   	
	   	cas4_snake_car_created_flag = 1
	ENDIF
ENDIF



/////////////////////////////////////////////////////////////////////////////////////////
//// If player at snakefarm and prerequisits have been done start hillbilly cut-scene ///
/////////////////////////////////////////////////////////////////////////////////////////

IF cas4_snake_blip_flag = 1
AND cas4_mission_progression_flag = 0
AND	cas4_played_cutscene_flag = 1
//AND cas4_snake_car_created_flag = 1

   IF NOT IS_CHAR_DEAD cas4_paul_ped
   AND NOT IS_CHAR_DEAD cas4_macca_ped
		   IF  LOCATE_CHAR_ANY_MEANS_3D scplayer -24.0 2331.0 24.0  4.0 4.0 2.0 TRUE
		   AND LOCATE_CHAR_ANY_MEANS_3D cas4_paul_ped -24.0 2331.0 24.0  4.0 4.0  2.0 FALSE
		   AND LOCATE_CHAR_ANY_MEANS_3D cas4_macca_ped -24.0 2331.0 24.0 4.0 4.0  2.0 FALSE
			   REMOVE_BLIP cas4_snake_blip
			   
				////////////////////////////// start of cut-scene \\\\\\\\\\\\\\\\\\\\
 							
 				
 				
 				//SMOKER POS stand
				CLEAR_PRINTS
				SET_FIXED_CAMERA_POSITION -21.46 2335.33 23.52 0.0 0.0 0.0				 
				POINT_CAMERA_AT_POINT -49.14 2356.75 26.09 JUMP_CUT
				
				MAKE_PLAYER_GANG_DISAPPEAR
				
				SWITCH_WIDESCREEN ON
	  		   	SET_PLAYER_CONTROL player1 OFF	  			
	  			SET_POLICE_IGNORE_PLAYER player1 ON
	  			
				CLEAR_AREA -24.0 2331.0 24.0 50.0 TRUE												

				REMOVE_ANIMATION PAULNMAC
				REMOVE_ANIMATION FOOD

				REQUEST_ANIMATION SMOKING	
				REQUEST_MODEL COLT45								

				WHILE NOT HAS_ANIMATION_LOADED SMOKING
				OR NOT HAS_MODEL_LOADED COLT45
				WAIT 0 
				ENDWHILE

				CREATE_CHAR PEDTYPE_MISSION2 DWMOLC1 -32.3702 2348.2388 23.1328  cas4_old_ped
				SET_CHAR_HEADING cas4_old_ped 180.1098
				//SET_ANIM_GROUP_FOR_CHAR cas4_old_ped drunkman
	  			SET_CHAR_IS_TARGET_PRIORITY cas4_old_ped TRUE
	  			GIVE_WEAPON_TO_CHAR cas4_old_ped WEAPONTYPE_PISTOL 3000
				
				OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]
				TASK_PLAY_ANIM -1 M_smkstnd_loop SMOKING 4.0 TRUE FALSE FALSE FALSE 	-1
				CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
			   	PERFORM_SEQUENCE_TASK cas4_old_ped cas4_hill_billy_seq[0]
				CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]

				//SMOKER PO lean
				CREATE_CHAR PEDTYPE_MISSION2 DWMYLC1 -34.2880 2350.2039 23.1328 cas4_pig_ped
				SET_CHAR_HEADING cas4_pig_ped 177.5823
				SET_CHAR_IS_TARGET_PRIORITY cas4_pig_ped TRUE
	  			GIVE_WEAPON_TO_CHAR cas4_pig_ped WEAPONTYPE_PISTOL 3000
				

				
				OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]
				TASK_PLAY_ANIM -1 M_smklean_loop SMOKING 4.0 TRUE FALSE FALSE FALSE 	-1 
				CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
			   	PERFORM_SEQUENCE_TASK cas4_pig_ped cas4_hill_billy_seq[0]
				CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]

				SET_FIXED_CAMERA_POSITION -21.46 2335.33 23.52 0.0 0.0 0.0				 
				POINT_CAMERA_AT_POINT -49.14 2356.75 26.09 JUMP_CUT
				
				


			   /// may want to warp player/paul/maccer outside of car to improve look of cut-scene ////	
			   	IF NOT IS_CHAR_DEAD cas4_paul_ped
			   	AND NOT IS_CHAR_DEAD	cas4_macca_ped
			   		CLEAR_AREA 	-25.7433 2334.6292 23.1328 100.0 TRUE
			   		IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer cas4_player_car
						SET_CAR_MISSION  cas4_player_car MISSION_EMERGENCYVEHICLE_STOP						
						WARP_CHAR_FROM_CAR_TO_COORD	scplayer  -25.7433 2334.6292 23.1328
						SET_CAR_COORDINATES	cas4_player_car -24.0 2331.0 24.0 
					ELSE
						SET_CHAR_COORDINATES scplayer  -25.7433 2334.6292 23.1328 
					ENDIF
						SET_CHAR_HEADING scplayer  35.8419
					

					IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
						WARP_CHAR_FROM_CAR_TO_COORD	cas4_paul_ped  -23.6752 2335.5312 23.1328 
					ELSE
						SET_CHAR_COORDINATES cas4_paul_ped	-23.6752 2334.5312 23.1328 
					ENDIF
						SET_CHAR_HEADING cas4_paul_ped	35.8419
					

					IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
						WARP_CHAR_FROM_CAR_TO_COORD cas4_macca_ped  -27.9403 2335.1052 23.1328
					ELSE
						SET_CHAR_COORDINATES cas4_macca_ped	 -27.9403 2334.1052 23.1328
					ENDIF
						SET_CHAR_HEADING cas4_macca_ped	  35.8419
								   			   		  
			   	ENDIF
					

				//ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE cas4_decision3 EVENT_DAMAGE TASK_SIMPLE_BE_DAMAGED 100.0 100.0 100.0 100.0 TRUE TRUE
	  				
					OPEN_SEQUENCE_TASK cas4_cutscene_player_seq
		 			IF IS_CHAR_IN_ANY_CAR scplayer
		 			TASK_LEAVE_ANY_CAR -1
					ENDIF
					TASK_GO_STRAIGHT_TO_COORD -1 -31.7411 2342.6797 23.1250 PEDMOVE_WALK -1
					TASK_ACHIEVE_HEADING  -1 35.5196  
					CLOSE_SEQUENCE_TASK	 cas4_cutscene_player_seq
					PERFORM_SEQUENCE_TASK scplayer cas4_cutscene_player_seq
					CLEAR_SEQUENCE_TASK	cas4_cutscene_player_seq
	  			

  					CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_POTENTIAL_WALK_INTO_PED
					
	 				
	  
	  			IF NOT IS_CHAR_DEAD cas4_macca_ped
	  				SET_CHAR_PROOFS cas4_macca_ped TRUE TRUE TRUE TRUE TRUE				
	  				REMOVE_CHAR_FROM_GROUP cas4_macca_ped
	  				OPEN_SEQUENCE_TASK cas4_cutscene_macca_seq
		 			IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
		 			TASK_LEAVE_ANY_CAR -1
					ENDIF
					TASK_GO_STRAIGHT_TO_COORD -1 -33.0398 2342.3535 23.1250 PEDMOVE_WALK -1
					TASK_ACHIEVE_HEADING  -1 37.5530										
					CLOSE_SEQUENCE_TASK	 cas4_cutscene_macca_seq
					PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_cutscene_macca_seq
					CLEAR_SEQUENCE_TASK	cas4_cutscene_macca_seq
	  				
					

					SET_CHAR_DECISION_MAKER  cas4_macca_ped cas4_decision2
	  			ENDIF
	  
	  			IF NOT IS_CHAR_DEAD cas4_paul_ped
	  				SET_CHAR_PROOFS cas4_paul_ped TRUE TRUE TRUE TRUE TRUE
	  				REMOVE_CHAR_FROM_GROUP cas4_paul_ped
	  				OPEN_SEQUENCE_TASK cas4_cutscene_paul_seq
		 			IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
		 			TASK_LEAVE_ANY_CAR -1
					ENDIF
					TASK_GO_STRAIGHT_TO_COORD -1  -30.0669 2344.0020 23.1250 PEDMOVE_WALK -1
					TASK_ACHIEVE_HEADING  -1 19.5560
					CLOSE_SEQUENCE_TASK	 cas4_cutscene_paul_seq
					PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_cutscene_paul_seq
					CLEAR_SEQUENCE_TASK	cas4_cutscene_paul_seq
	  				SET_CHAR_DECISION_MAKER  cas4_paul_ped cas4_decision2
				ENDIF

					
				
				
				
				cas4_audio_counter 		= 0
				cas4_audio_slot1   		= 1
				cas4_audio_slot2   		= 2
				cas4_audio_playing 		= 0
				cas4_text_loop_done		= 0
				cas4_mobile				= 0
				cas4_text_timer_diff	= 0 
				cas4_text_timer_end 	= 0
				cas4_text_timer_start 	= 0
				cas4_ahead_counter	  	= 0


				TIMERB = 0

				casino4_text_loop1:
				WAIT 0 

				IF cas4_text_loop_done = 0
					
					
					IF NOT cas4_audio_counter = 0
						IF cas4_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								CLEAR_MISSION_AUDIO cas4_audio_slot2
							ENDIF
							cas4_audio_playing = 1
						ENDIF

						IF cas4_audio_playing = 1
							LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
							cas4_audio_playing = 2
						ENDIF

						IF cas4_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
								PLAY_MISSION_AUDIO cas4_audio_slot1
								PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
								cas4_audio_playing = 3
							ENDIF
						ENDIF

						IF cas4_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
								CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
								IF cas4_audio_slot1 = 1
									cas4_audio_slot1 = 2
									cas4_audio_slot2 = 1
								ELSE
									cas4_audio_slot1 = 1
									cas4_audio_slot2 = 2
								ENDIF
								cas4_audio_counter = 0
								cas4_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
									IF cas4_audio_counter < 110
										cas4_ahead_counter = cas4_audio_counter + 1
										LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF

				
				
				
					
					SWITCH cas4_mobile
							CASE 0
								IF cas4_audio_playing = 0									
									
										STOP_CHAR_FACIAL_TALK scplayer
									 	START_CHAR_FACIAL_TALK scplayer	 3000
									
									cas4_audio_counter = 33 //CAS4_FA//]Here we are, look familiar?
									cas4_mobile = 1
									GET_GAME_TIMER cas4_text_timer_start
								ENDIF
								BREAK
							CASE 1
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										IF NOT IS_CHAR_DEAD	cas4_macca_ped										
												STOP_CHAR_FACIAL_TALK cas4_macca_ped
											 	START_CHAR_FACIAL_TALK cas4_macca_ped	 3000
										ENDIF
										cas4_audio_counter = 34 //CAS4_FB//]Looks like Salford to me...
										cas4_mobile = 2
										SKIP_CUTSCENE_START
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF	
								BREAK
							CASE 2
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										IF NOT IS_CHAR_DEAD	cas4_paul_ped
										AND NOT IS_CHAR_DEAD cas4_macca_ped
											STOP_CHAR_FACIAL_TALK cas4_macca_ped
										 	START_CHAR_FACIAL_TALK cas4_paul_ped	 3000
										ENDIF
										cas4_audio_counter = 35 //CAS4_FC//]What?
										cas4_mobile = 3
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF
								BREAK														
							DEFAULT
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
									   IF NOT IS_CHAR_DEAD	cas4_paul_ped									   
											STOP_CHAR_FACIAL_TALK cas4_paul_ped											 
									   ENDIF
									   cas4_text_loop_done = 1	
									ENDIF
								ENDIF
								BREAK
						ENDSWITCH
					
					
					GOTO casino4_text_loop1

				ENDIF				
				
				
				
				cas4_safety_flag = 0
				
				WHILE cas4_safety_flag = 0
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer -31.7411 2342.6797 23.1250 3.0 3.0 3.0 FALSE
					OR TIMERB > 10000
						cas4_safety_flag = 1
					ENDIF

				WAIT 0
				ENDWHILE	  				  											  				  				  		   						
								

			   
								
				SET_CHAR_COORDINATES scplayer -31.7411 2342.6797 23.1250
				SET_CHAR_HEADING scplayer 37.5530
				
				
				IF NOT IS_CHAR_DEAD cas4_paul_ped
					CLEAR_CHAR_TASKS cas4_paul_ped
					SET_CHAR_COORDINATES cas4_paul_ped -30.0669 2344.0020 23.1250
					SET_CHAR_HEADING cas4_paul_ped 19.5560
				ENDIF
				
				IF NOT IS_CHAR_DEAD cas4_macca_ped
					CLEAR_CHAR_TASKS cas4_macca_ped
					SET_CHAR_COORDINATES cas4_macca_ped	-33.0398 2342.3535 23.1250
					SET_CHAR_HEADING cas4_macca_ped	37.5530
				ENDIF
				
				

				
				 
				
				SET_FIXED_CAMERA_POSITION -29.85 2341.05 23.81 0.0 0.0 0.0				 
				POINT_CAMERA_AT_POINT -48.53 2366.47 31.65 JUMP_CUT
				
				REQUEST_MODEL DWFOLC // female stand in				
				REQUEST_MODEL DWMOLC2 // incest
				REQUEST_ANIMATION MISC							   		   
			   	
			   													

				//WHILE NOT IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_C
				//WAIT 0 
				//ENDWHILE
				
				
				IF NOT IS_CHAR_DEAD cas4_old_ped
				AND  NOT IS_CHAR_DEAD cas4_pig_ped
					OPEN_SEQUENCE_TASK cas4_cutscene_player_seq		 			  
					TASK_LOOK_AT_CHAR -1 cas4_old_ped 3000
					TASK_LOOK_AT_CHAR -1 cas4_pig_ped	3000
					CLOSE_SEQUENCE_TASK	 cas4_cutscene_player_seq
					PERFORM_SEQUENCE_TASK scplayer cas4_cutscene_player_seq
					CLEAR_SEQUENCE_TASK	cas4_cutscene_player_seq
				ENDIF
				




				cas4_audio_counter 		= 0
				cas4_audio_slot1   		= 1
				cas4_audio_slot2   		= 2
				cas4_audio_playing 		= 0
				cas4_text_loop_done		= 0
				cas4_mobile				= 0
				cas4_text_timer_diff	= 0 
				cas4_text_timer_end 	= 0
				cas4_text_timer_start 	= 0
				cas4_ahead_counter	  	= 0
				

				casino4_text_loop2:
				WAIT 0 

				IF cas4_text_loop_done = 0
					
					
					IF NOT cas4_audio_counter = 0
						IF cas4_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								CLEAR_MISSION_AUDIO cas4_audio_slot2
							ENDIF
							cas4_audio_playing = 1
						ENDIF

						IF cas4_audio_playing = 1
							LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
							cas4_audio_playing = 2
						ENDIF

						IF cas4_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
								PLAY_MISSION_AUDIO cas4_audio_slot1
								PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
								cas4_audio_playing = 3
							ENDIF
						ENDIF

						IF cas4_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
								CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
								IF cas4_audio_slot1 = 1
									cas4_audio_slot1 = 2
									cas4_audio_slot2 = 1
								ELSE
									cas4_audio_slot1 = 1
									cas4_audio_slot2 = 2
								ENDIF
								
								
								IF NOT IS_CHAR_DEAD	cas4_incest_ped										   
									STOP_CHAR_FACIAL_TALK cas4_incest_ped
								ENDIF
								IF NOT IS_CHAR_DEAD	cas4_old_ped										   
									STOP_CHAR_FACIAL_TALK cas4_old_ped
								ENDIF
								IF NOT IS_CHAR_DEAD	cas4_sister_ped										   
									STOP_CHAR_FACIAL_TALK cas4_sister_ped
								ENDIF
								IF NOT IS_CHAR_DEAD	cas4_pig_ped										   
									STOP_CHAR_FACIAL_TALK cas4_pig_ped
								ENDIF
								
								cas4_audio_counter = 0
								cas4_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
									IF cas4_audio_counter < 110
										cas4_ahead_counter = cas4_audio_counter + 1
										LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF

				
					
					
					
					
					
					
					
				
					
					SWITCH cas4_mobile
							CASE 0
								IF cas4_audio_playing = 0
									IF NOT IS_CHAR_DEAD	cas4_old_ped																						
									 	START_CHAR_FACIAL_TALK cas4_old_ped	 10000
									ENDIF
									cas4_audio_counter = 103// SOUND_CAS4_FD//  Take a gander at them fellas, is that them?
									cas4_mobile = 1
									GET_GAME_TIMER cas4_text_timer_start
								ENDIF
								BREAK
							CASE 1
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										IF  HAS_ANIMATION_LOADED MISC
											CLEAR_LOOK_AT scplayer				
											IF NOT IS_CHAR_DEAD cas4_macca_ped
											AND  NOT IS_CHAR_DEAD cas4_paul_ped
												TASK_LOOK_AT_CHAR scplayer cas4_macca_ped 6000
												TASK_LOOK_AT_CHAR cas4_paul_ped	cas4_macca_ped 6000

												OPEN_SEQUENCE_TASK cas4_cutscene_player_seq		 			  
												TASK_LOOK_AT_CHAR -1 cas4_paul_ped 3000
												TASK_LOOK_AT_CHAR -1 scplayer	3000
												CLOSE_SEQUENCE_TASK	 cas4_cutscene_player_seq
												PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_cutscene_player_seq
												CLEAR_SEQUENCE_TASK	cas4_cutscene_player_seq
											ENDIF
										
										IF NOT IS_CHAR_DEAD cas4_pig_ped																						
									 	START_CHAR_FACIAL_TALK cas4_pig_ped	 10000
										ENDIF
										cas4_audio_counter = 104// SOUND_CAS4_FE// That there city boy has been with my prize hog!
										cas4_mobile = 2
										GET_GAME_TIMER cas4_text_timer_start
										ELSE
											REQUEST_ANIMATION MISC
										ENDIF
									ENDIF
								ENDIF	
								BREAK
							
							///////////////////////////////
							///////////////////////////////
							CASE 2
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0										
										
										IF NOT IS_CHAR_DEAD cas4_pig_ped																						
									 	START_CHAR_FACIAL_TALK cas4_pig_ped	 10000
										ENDIF
										cas4_audio_counter = 105// SOUND_CAS4_FF// Now I don't even get no sugar from her!
										cas4_mobile = 3
										GET_GAME_TIMER cas4_text_timer_start										
									ENDIF
								ENDIF	
								BREAK

							  /////////////////////////////////
							  /////////////////////////////////



							CASE 3
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF NOT  HAS_MODEL_LOADED DWFOLC				   
								   	OR NOT  HAS_MODEL_LOADED DWMOLC2 			   	   			  	   
								  	   	REQUEST_MODEL DWFOLC // female stand in				
										REQUEST_MODEL DWMOLC2 // incest
								  	   
									ELSE
										IF cas4_audio_playing = 0
											IF NOT IS_CHAR_DEAD cas4_macca_ped
											AND  NOT IS_CHAR_DEAD cas4_paul_ped
												CLEAR_LOOK_AT scplayer
												CLEAR_LOOK_AT cas4_paul_ped
												CLEAR_LOOK_AT cas4_macca_ped
											ENDIF
																						
											IF NOT IS_CHAR_DEAD cas4_macca_ped
											AND  NOT IS_CHAR_DEAD cas4_paul_ped									
												
												OPEN_SEQUENCE_TASK cas4_cutscene_player_seq		 			  
												TASK_STAND_STILL -1 2000
												TASK_PLAY_ANIM -1  plyr_shkhead  MISC 4.0 FALSE FALSE FALSE FALSE 	-1
												CLOSE_SEQUENCE_TASK	 cas4_cutscene_player_seq
												PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_cutscene_player_seq
												CLEAR_SEQUENCE_TASK	cas4_cutscene_player_seq

												TASK_LOOK_AT_CHAR scplayer cas4_macca_ped 6000
												
												OPEN_SEQUENCE_TASK cas4_cutscene_player_seq
												TASK_STAND_STILL -1 3000
												TASK_SCRATCH_HEAD -1
												CLOSE_SEQUENCE_TASK	 cas4_cutscene_player_seq
												PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_cutscene_player_seq
												CLEAR_SEQUENCE_TASK	cas4_cutscene_player_seq
											ENDIF
											
											CLEAR_LOOK_AT scplayer

											//WALKER 1 route
											CREATE_CHAR PEDTYPE_MISSION2 DWFOLC -45.2021 2350.3196 23.1250 cas4_sister_ped				
											SET_CHAR_HEADING cas4_sister_ped 176.9760 
											SET_ANIM_GROUP_FOR_CHAR cas4_sister_ped sexywoman
								  			SET_CHAR_IS_TARGET_PRIORITY cas4_sister_ped TRUE
											SET_CHAR_DECISION_MAKER  cas4_sister_ped cas4_decision2
											FLUSH_ROUTE	
											EXTEND_ROUTE -44.0520 2348.6145 23.1250 //187.9875 
											EXTEND_ROUTE -39.8254 2346.7334 23.1250 //251.3449 
											EXTEND_ROUTE -36.6941 2346.5005 23.1250 //259.6481 
							  
											OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]				
											TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
										   	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
										   	CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
										   	PERFORM_SEQUENCE_TASK cas4_sister_ped cas4_hill_billy_seq[0]
											CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
											
											
											//WALKER 2 route
											CREATE_CHAR PEDTYPE_MISSION2 DWMOLC2 -46.1256 2351.3870 23.1250 cas4_incest_ped				
											SET_CHAR_HEADING cas4_incest_ped 176.9760 				
											SET_CHAR_DECISION_MAKER  cas4_incest_ped cas4_decision2
											FLUSH_ROUTE	
											EXTEND_ROUTE -44.0520 2348.6145 23.1250 //187.9875 
											EXTEND_ROUTE -39.8254 2346.7334 23.1250 //251.3449 
											EXTEND_ROUTE -38.2860 2346.6750 23.1250
											EXTEND_ROUTE -36.1799 2348.4119 23.1250 //265.8938 
											
											OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]
											TASK_FOLLOW_POINT_ROUTE -1 PEDMOVE_WALK FOLLOW_ROUTE_ONCE
										   	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
										   	CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
										   	PERFORM_SEQUENCE_TASK cas4_incest_ped cas4_hill_billy_seq[0]
											CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]

											SET_FIXED_CAMERA_POSITION -36.34 2345.19 24.52 0.0 0.0 0.0
											POINT_CAMERA_AT_POINT -50.23 2366.32 23.72 JUMP_CUT
											
											IF NOT IS_CHAR_DEAD	cas4_incest_ped																						
										 		START_CHAR_FACIAL_TALK cas4_incest_ped	 10000
											ENDIF
											cas4_audio_counter = 106// SOUND_CAS4_FG// And that’un went and did my sis! 
											cas4_mobile = 4
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
								ENDIF
								BREAK
							///////////////////
							///////////////////	
							CASE 4
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0										
										IF NOT IS_CHAR_DEAD	cas4_incest_ped										   											
										 	START_CHAR_FACIAL_TALK cas4_incest_ped	 10000
										ENDIF
										cas4_audio_counter = 107// SOUND_CAS4_FH// I've had a terr’ble aching in my grinds ever since!
										cas4_mobile = 5
										GET_GAME_TIMER cas4_text_timer_start										
									ENDIF
								ENDIF	
								BREAK
																					
							
							////////////////////
							////////////////////

								
								
																						
							CASE 5
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										//PRINT (cas4_18) 5000 1 //PAUL: I think I remember... hold on a mo, weren't you the one holding the video camera !														
										IF NOT IS_CHAR_DEAD cas4_sister_ped
											GET_SCRIPT_TASK_STATUS cas4_sister_ped PERFORM_SEQUENCE_TASK cas4_task_status														
											IF  cas4_task_status = FINISHED_TASK																																								
												TASK_PLAY_ANIM cas4_sister_ped  Scratchballs_01  MISC 4.0 TRUE FALSE FALSE FALSE 	-1
												cas4_audio_counter = 108// SOUND_CAS4_FJ// I'm gonna slap you silly for giving me and my fella the red bumpies!
												IF NOT IS_CHAR_DEAD cas4_sister_ped																								
											 	START_CHAR_FACIAL_TALK cas4_sister_ped	 10000
												ENDIF
												cas4_mobile = 6
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
										ELSE
											cas4_audio_counter = 108// SOUND_CAS4_FJ// I'm gonna slap you silly for giving me and my fella the red bumpies!
											cas4_mobile = 6
											GET_GAME_TIMER cas4_text_timer_start
										ENDIF
									ENDIF
								ENDIF
								BREAK
							CASE 6
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0										
										SET_FIXED_CAMERA_POSITION -30.33 2342.29 24.71 0.0 0.0 0.0
										POINT_CAMERA_AT_POINT -41.67 2352.5 23.55 JUMP_CUT
										
										IF NOT IS_CHAR_DEAD cas4_incest_ped
											TASK_PLAY_ANIM cas4_incest_ped  Scratchballs_01  MISC 4.0 TRUE FALSE FALSE FALSE 	-1
										ENDIF
																														
										IF NOT IS_CHAR_DEAD cas4_old_ped
											TASK_PLAY_ANIM cas4_old_ped M_smk_out SMOKING 4.0 FALSE FALSE FALSE FALSE 	-1
										ENDIF
										
										IF NOT IS_CHAR_DEAD cas4_old_ped																							
										 	START_CHAR_FACIAL_TALK cas4_old_ped	 10000
										ENDIF
										cas4_audio_counter = 109// SOUND_CAS4_FK// What'n tarnation?	
										cas4_mobile = 7
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 7
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										
										IF NOT IS_CHAR_DEAD cas4_old_ped
											OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]					
											TASK_PLAY_ANIM -1 roadcross PED 4.0 FALSE FALSE FALSE FALSE 	-1
											TASK_PLAY_ANIM -1 colt45_reload COLT45 4.0 FALSE FALSE FALSE FALSE 	-1
											TASK_AIM_GUN_AT_CHAR -1 scplayer 6000
											CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
										   	PERFORM_SEQUENCE_TASK cas4_old_ped cas4_hill_billy_seq[0]
											CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]					
										ENDIF
																				
										
										IF NOT IS_CHAR_DEAD cas4_old_ped																							
										 	START_CHAR_FACIAL_TALK cas4_old_ped	 10000
										ENDIF
										cas4_audio_counter = 110// SOUND_CAS4_FL// I'm a fixing to give ya a whoopin' for what you gone and done to my young'uns
										cas4_mobile = 8
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF
								BREAK
							DEFAULT
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
									   cas4_text_loop_done = 1	
									ENDIF
								ENDIF
								BREAK
						ENDSWITCH
					
					
					GOTO casino4_text_loop2

				ENDIF	

		
				SKIP_CUTSCENE_END

				MAKE_PLAYER_GANG_REAPPEAR


				CLEAR_MISSION_AUDIO cas4_audio_slot1
				CLEAR_MISSION_AUDIO cas4_audio_slot2
	  			
	  			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_POTENTIAL_WALK_INTO_PED TASK_COMPLEX_AVOID_OTHER_PED_WHILE_WANDERING 100.0 0.0 0.0 100.0 TRUE FALSE
				////////////////////// if cut-scene was skipped	 ///////////
				////////////////////////////////////////////////////////////
				
				WHILE NOT HAS_MODEL_LOADED	DWMOLC1
				OR NOT HAS_MODEL_LOADED	DWMYLC1
				OR NOT HAS_MODEL_LOADED	DWFOLC
				OR NOT HAS_MODEL_LOADED	DWMOLC2
				OR NOT HAS_MODEL_LOADED COLT45
					WAIT 0
					REQUEST_MODEL	DWMYLC1
					REQUEST_MODEL	DWFOLC
					REQUEST_MODEL	DWMOLC2
					REQUEST_MODEL COLT45
				ENDWHILE 

				
				DELETE_CHAR cas4_old_ped
				MARK_CHAR_AS_NO_LONGER_NEEDED cas4_old_ped
				CREATE_CHAR PEDTYPE_MISSION2 DWMOLC1 -32.3702 2348.2388 23.1250  cas4_old_ped
				SET_CHAR_HEADING cas4_old_ped 180.1098
				//SET_ANIM_GROUP_FOR_CHAR cas4_old_ped drunkman
	  			SET_CHAR_IS_TARGET_PRIORITY cas4_old_ped TRUE
	  			GIVE_WEAPON_TO_CHAR cas4_old_ped WEAPONTYPE_PISTOL 3000
				
								
				DELETE_CHAR cas4_pig_ped
				MARK_CHAR_AS_NO_LONGER_NEEDED cas4_pig_ped
				CREATE_CHAR PEDTYPE_MISSION2 DWMYLC1 -34.2880 2350.2039 23.1250 cas4_pig_ped
				SET_CHAR_HEADING cas4_pig_ped 177.5823
				SET_CHAR_IS_TARGET_PRIORITY cas4_pig_ped TRUE
	  			GIVE_WEAPON_TO_CHAR cas4_pig_ped WEAPONTYPE_PISTOL 3000
				
				
				
				DELETE_CHAR cas4_sister_ped
				MARK_CHAR_AS_NO_LONGER_NEEDED cas4_sister_ped
				CREATE_CHAR PEDTYPE_MISSION2 DWFOLC -36.6941 2346.5005 23.1250 cas4_sister_ped				
				SET_CHAR_HEADING cas4_sister_ped 259.6481 
				SET_ANIM_GROUP_FOR_CHAR cas4_sister_ped sexywoman
	  			SET_CHAR_IS_TARGET_PRIORITY cas4_sister_ped TRUE
				 
				DELETE_CHAR cas4_incest_ped
				MARK_CHAR_AS_NO_LONGER_NEEDED cas4_incest_ped   																
				CREATE_CHAR PEDTYPE_MISSION2 DWMOLC2 -36.1799 2348.4119 23.1250 cas4_incest_ped				
				SET_CHAR_HEADING cas4_incest_ped 265.8938 				
				
				
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -31.7411 2342.6797 23.1250
				ELSE
					SET_CHAR_COORDINATES scplayer -31.7411 2342.6797 23.1250
				ENDIF
				SET_CHAR_HEADING scplayer 37.5530
				
				
				IF NOT IS_CHAR_DEAD cas4_paul_ped
					CLEAR_CHAR_TASKS cas4_paul_ped
					IF IS_CHAR_IN_ANY_CAR  cas4_paul_ped
						WARP_CHAR_FROM_CAR_TO_COORD	cas4_paul_ped -30.0669 2344.0020 23.1250
					ELSE
					SET_CHAR_COORDINATES cas4_paul_ped -30.0669 2344.0020 23.1250
					ENDIF
					SET_CHAR_HEADING cas4_paul_ped 19.5560
				ENDIF
				
				IF NOT IS_CHAR_DEAD cas4_macca_ped
					CLEAR_CHAR_TASKS cas4_macca_ped
					IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
						WARP_CHAR_FROM_CAR_TO_COORD	 cas4_macca_ped -33.0398 2342.3535 23.1250
					ELSE
					SET_CHAR_COORDINATES cas4_macca_ped	-33.0398 2342.3535 23.1250
					ENDIF
					SET_CHAR_HEADING cas4_macca_ped	37.5530
				ENDIF

				IF NOT IS_CHAR_DEAD cas4_paul_ped
	  			AND NOT IS_CHAR_DEAD cas4_macca_ped       							
						TASK_TOGGLE_DUCK cas4_macca_ped TRUE
						TASK_TOGGLE_DUCK cas4_paul_ped TRUE						
				ENDIF
												  			
	  			//////////////////////////////////////////// old guy tasks //////////////////////////
	  			GET_CHAR_COORDINATES scplayer cas4_car_x cas4_car_y cas4_car_z

	  			IF NOT IS_CHAR_DEAD cas4_old_ped
	  			AND NOT IS_CAR_DEAD cas4_dune_car	  					
	  					FREEZE_CAR_POSITION cas4_dune_car FALSE
	  					OPEN_SEQUENCE_TASK cas4_old_seq
	  					TASK_SHOOT_AT_COORD -1 cas4_car_x cas4_car_y cas4_car_z 5000
	  					TASK_GO_STRAIGHT_TO_COORD -1 -46.3592 2346.9766 23.1348 PEDMOVE_RUN 10000	  						  					
	  					TASK_ENTER_CAR_AS_PASSENGER -1 cas4_dune_car	3000 0	  					
	  					TASK_DRIVE_BY -1 scplayer  -1 0.0 0.0 0.0 10.0 DRIVEBY_AI_ALL_DIRN TRUE 50
	  					CLOSE_SEQUENCE_TASK cas4_old_seq
	  					PERFORM_SEQUENCE_TASK	cas4_old_ped cas4_old_seq
	  					CLEAR_SEQUENCE_TASK cas4_old_seq
	  			
	  			ELSE
					IF IS_CAR_DEAD cas4_dune_car
					OR IS_CHAR_DEAD cas4_sister_ped	
						IF NOT IS_CHAR_DEAD cas4_old_ped
					   		TASK_KILL_CHAR_ON_FOOT cas4_old_ped scplayer
	  					ENDIF
					ENDIF
	  			ENDIF


				///////////////////////////////////////////// pig fancier ////////////

	  			IF NOT IS_CHAR_DEAD cas4_pig_ped
	  			AND NOT IS_CAR_DEAD cas4_quad_car	  					
	  					FREEZE_CAR_POSITION cas4_quad_car FALSE
	  					OPEN_SEQUENCE_TASK cas4_pig_seq
	  					TASK_SHOOT_AT_COORD -1 cas4_car_x cas4_car_y cas4_car_z 5000 
	  					TASK_GO_STRAIGHT_TO_COORD -1 -44.9959 2346.3806 23.1348 PEDMOVE_RUN 10000	  					
	  					TASK_ENTER_CAR_AS_PASSENGER -1 cas4_quad_car	3000 0	  					
	  					TASK_DRIVE_BY -1 scplayer  -1  0.0 0.0 0.0  10.0 DRIVEBY_AI_ALL_DIRN TRUE 50
	  					CLOSE_SEQUENCE_TASK cas4_pig_seq
	  					PERFORM_SEQUENCE_TASK	cas4_pig_ped cas4_pig_seq
	  					CLEAR_SEQUENCE_TASK cas4_pig_seq
	  			ELSE
					IF IS_CAR_DEAD cas4_quad_car
					OR IS_CHAR_DEAD cas4_incest_ped						
						IF NOT IS_CHAR_DEAD cas4_pig_ped
					   		TASK_KILL_CHAR_ON_FOOT cas4_pig_ped scplayer
						ENDIF
	  				ENDIF
	  			
	  			ENDIF



				 //////////////////////// incest guy tasks   ///////////////////////

	  			IF NOT IS_CHAR_DEAD cas4_incest_ped
	  			AND NOT IS_CAR_DEAD cas4_quad_car	  					
	  					FREEZE_CAR_POSITION cas4_quad_car FALSE
	  					OPEN_SEQUENCE_TASK cas4_incest_seq
	  					TASK_SHOOT_AT_COORD -1 cas4_car_x cas4_car_y cas4_car_z 5000 
	  					TASK_GO_STRAIGHT_TO_COORD -1 -44.9959 2346.3806 23.1348 PEDMOVE_RUN 10000	  					
	  					TASK_ENTER_CAR_AS_DRIVER -1 cas4_quad_car	3000   					
	  					CLOSE_SEQUENCE_TASK cas4_incest_seq
	  					PERFORM_SEQUENCE_TASK	cas4_incest_ped cas4_incest_seq
	  					CLEAR_SEQUENCE_TASK cas4_incest_seq
	  			ELSE
	  				IF IS_CAR_DEAD cas4_quad_car
					AND NOT IS_CHAR_DEAD cas4_incest_ped
					   TASK_KILL_CHAR_ON_FOOT cas4_incest_ped scplayer
	  				ENDIF
	  			ENDIF
	  
	  			
	  			 ////////////////////// sisters task sequence ///////////////
	  			
	  			IF NOT IS_CHAR_DEAD cas4_sister_ped
	  			AND NOT IS_CAR_DEAD cas4_dune_car	  					
	  					FREEZE_CAR_POSITION cas4_dune_car FALSE
	  					OPEN_SEQUENCE_TASK cas4_sister_seq
	  					TASK_SHOOT_AT_COORD -1 cas4_car_x cas4_car_y cas4_car_z 5000 
	  					TASK_GO_STRAIGHT_TO_COORD -1 -46.3592 2346.9766 23.1348 PEDMOVE_RUN 10000	  					
	  					TASK_ENTER_CAR_AS_DRIVER -1 cas4_dune_car	3000 	 						 					      						
	  					CLOSE_SEQUENCE_TASK cas4_sister_seq
	  					PERFORM_SEQUENCE_TASK	cas4_sister_ped cas4_sister_seq
	  					CLEAR_SEQUENCE_TASK cas4_sister_seq
	  			ELSE
				    IF IS_CAR_DEAD cas4_dune_car
					AND NOT IS_CHAR_DEAD cas4_sister_ped
					   TASK_KILL_CHAR_ON_FOOT cas4_sister_ped scplayer
	  				ENDIF
	  			
	  			ENDIF

	  			 /////////////////////////// end of assigning tasks //////////////////////////////////
	  			
	  			
	  			SET_POLICE_IGNORE_PLAYER player1 OFF
	  			IF NOT IS_CAR_DEAD  cas4_quad_car
	  				SET_CAR_CAN_BE_DAMAGED cas4_quad_car TRUE
					SET_CAR_ONLY_DAMAGED_BY_PLAYER cas4_quad_car TRUE
	  			ENDIF

	  			IF NOT IS_CAR_DEAD cas4_dune_car
	  				SET_CAR_CAN_BE_DAMAGED cas4_dune_car TRUE
					SET_CAR_ONLY_DAMAGED_BY_PLAYER cas4_dune_car TRUE
	  			ENDIF		  
	  			
	  			
	  			IF NOT IS_CHAR_DEAD cas4_macca_ped
				AND  NOT IS_CHAR_DEAD cas4_paul_ped
					CLEAR_CHAR_TASKS scplayer
					CLEAR_CHAR_TASKS cas4_paul_ped
					CLEAR_CHAR_TASKS cas4_macca_ped
					CLEAR_LOOK_AT scplayer
					CLEAR_LOOK_AT cas4_paul_ped
					CLEAR_LOOK_AT cas4_macca_ped
				ENDIF
	  			
	  			
	  			
	  			REMOVE_ANIMATION SMOKING
				REMOVE_ANIMATION MISC
	  			

				//////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////											   				
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_SHOT_FIRED_WHIZZED_BY
				CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_SHOT_FIRED
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_SHOT_FIRED_WHIZZED_BY TASK_SIMPLE_DUCK_WHILE_SHOTS_WHIZZING 100.0 100.0 100.0 100.0 TRUE TRUE
				ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_SHOT_FIRED TASK_SIMPLE_DUCK 100.0 100.0 100.0 100.0 TRUE TRUE

				CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE cas4_decision3 EVENT_DAMAGE
				ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE	 cas4_decision3 EVENT_SHOT_FIRED TASK_GROUP_USE_MEMBER_DECISION 100.0 100.0 100.0 100.0 TRUE TRUE

	  			IF NOT IS_CHAR_DEAD cas4_paul_ped
	  			AND NOT IS_CHAR_DEAD cas4_macca_ped       																									
						//MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 2
						IF NOT IS_GROUP_MEMBER cas4_paul_ped Players_Group
							SET_GROUP_MEMBER Players_Group cas4_paul_ped
						ENDIF
						IF NOT IS_GROUP_MEMBER cas4_macca_ped Players_Group
							SET_GROUP_MEMBER Players_Group cas4_macca_ped
						ENDIF  
	 			ENDIF
				
	  			
				cas4_temp_hill_billy[0] = cas4_old_ped
			  	cas4_temp_hill_billy[1] = cas4_sister_ped
			  	cas4_temp_hill_billy[2] = cas4_incest_ped
			  	cas4_temp_hill_billy[3] = cas4_pig_ped
	  			cas4_audio_left_behind_played = 0	  			
	  			cas4_left_behind = 0 
	  			RESTORE_CAMERA_JUMPCUT
	  			SWITCH_WIDESCREEN  OFF
	  			SET_PLAYER_CONTROL player1 ON
				/////////////////////////////////////////// END OF CUT-SCENE \\\\\\\\\\\\\\\\\\\\\\\\\\
				
				
				
				IF cas4_blip_added_flag =0
					ADD_BLIP_FOR_COORD  2175.21 1679.37 9.85 cas4_end_blip
					PRINT_NOW ( CAS4_06 ) 8000 1 ///////////// " Get to the Mafia Casino "
					cas4_blip_added_flag = 1
     			ENDIF
				
				cas4_mission_progression_flag = 1
				cas4_message_dsplayed  = 9999999
		   ENDIF
   ENDIF   

ENDIF





////////////////////////////////////////////////
/// want to checks on paul/mac location here ///
////////////////////////////////////////////////







IF cas4_mission_progression_flag > 0

	 //////////////////////////////////////////
	 ////// this may have to be revised ///////
	 //////////////////////////////////////////

	 	
	
	 IF NOT IS_CHAR_DEAD cas4_macca_ped
		 IF cas4_macca_with_player = 1			 
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cas4_macca_ped  20.0 20.0 FALSE
		  		REMOVE_CHAR_FROM_GROUP cas4_macca_ped 
				IF NOT IS_CHAR_IN_ANY_CAR cas4_macca_ped
      	      		TASK_TIRED cas4_macca_ped 60000
				ELSE
					OPEN_SEQUENCE_TASK cas4_cutscene_macca_seq	
					TASK_LEAVE_ANY_CAR -1
					TASK_TIRED -1 60000	   									   					
					CLOSE_SEQUENCE_TASK cas4_cutscene_macca_seq
					PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_cutscene_macca_seq
					CLEAR_SEQUENCE_TASK cas4_cutscene_macca_seq
				ENDIF
				ADD_BLIP_FOR_CHAR cas4_macca_ped cas4_macca_blip
				SET_BLIP_AS_FRIENDLY cas4_macca_blip TRUE 
				cas4_macca_with_player = 0
			ENDIF		
		ELSE
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cas4_macca_ped  5.0 5.0 FALSE		   	
			   	
				IF NOT IS_GROUP_MEMBER cas4_macca_ped Players_Group
					//MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 2
					SET_GROUP_MEMBER Players_Group cas4_macca_ped
				ENDIF  
			   	
			   	REMOVE_BLIP cas4_macca_blip
			   	cas4_macca_with_player = 1
			ENDIF				
		ENDIF
	ENDIF				
	
   
  	IF NOT IS_CHAR_DEAD cas4_paul_ped
		IF cas4_paul_with_player = 1
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cas4_paul_ped  20.0 20.0 FALSE
	  			REMOVE_CHAR_FROM_GROUP cas4_paul_ped 
	  	      	IF NOT IS_CHAR_IN_ANY_CAR cas4_paul_ped
      	      		TASK_TIRED cas4_paul_ped 60000
				ELSE
					OPEN_SEQUENCE_TASK cas4_cutscene_macca_seq	
					TASK_LEAVE_ANY_CAR -1
					TASK_TIRED -1 60000	   									   					
					CLOSE_SEQUENCE_TASK cas4_cutscene_macca_seq
					PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_cutscene_macca_seq
					CLEAR_SEQUENCE_TASK cas4_cutscene_macca_seq
				ENDIF
	  			ADD_BLIP_FOR_CHAR cas4_paul_ped cas4_paul_blip
	  			SET_BLIP_AS_FRIENDLY cas4_paul_blip TRUE 
	  			cas4_paul_with_player = 0
			ENDIF
		ELSE			
			IF LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cas4_paul_ped  5.0 5.0 FALSE		   	
			   	IF NOT IS_GROUP_MEMBER cas4_paul_ped Players_Group
					//MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 2
					SET_GROUP_MEMBER Players_Group cas4_paul_ped
				ENDIF
	
			   	
			   	REMOVE_BLIP cas4_paul_blip
			   	cas4_paul_with_player = 1
			ENDIF						
		ENDIF
	ENDIF
      			  			
			
	IF cas4_paul_with_player = 1
	AND cas4_macca_with_player = 1
		
		
		//////// banter 3 & 4 go in here ? ///////////
		
		IF cas4_banter4_completed = 0
			
				IF NOT cas4_audio_counter = 0
					IF cas4_audio_playing = 0
						IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
							CLEAR_MISSION_AUDIO cas4_audio_slot2
						ENDIF
						cas4_audio_playing = 1
					ENDIF

					IF cas4_audio_playing = 1
						LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
						cas4_audio_playing = 2
					ENDIF

					IF cas4_audio_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
							PLAY_MISSION_AUDIO cas4_audio_slot1
							PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
							cas4_audio_playing = 3
						ENDIF
					ENDIF

					IF cas4_audio_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
							CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
							IF cas4_audio_slot1 = 1
								cas4_audio_slot1 = 2
								cas4_audio_slot2 = 1
							ELSE
								cas4_audio_slot1 = 1
								cas4_audio_slot2 = 2
							ENDIF
							cas4_audio_counter = 0
							cas4_audio_playing = 0
						ELSE
							IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								IF cas4_audio_counter < 110
									cas4_ahead_counter = cas4_audio_counter + 1
									LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			
			IF NOT IS_MESSAGE_BEING_DISPLAYED
				IF cas4_banter3_completed = 0		   		    											   	
				   IF   cas4_end_of_cut_completed  = 0				   	    										   		
				   		SWITCH cas4_end_of_cut
							CASE 0
								IF cas4_audio_playing = 0
									cas4_audio_counter = 36// CAS4_GA//]Run like fuck!
									cas4_end_of_cut = 1
									GET_GAME_TIMER cas4_text_timer_start
								ENDIF
								BREAK
							CASE 1
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
										cas4_audio_counter = 37// CAS4_GB//]Everybody in the car!	
										cas4_end_of_cut = 2
										GET_GAME_TIMER cas4_text_timer_start
									ENDIF
								ENDIF	
								BREAK																		
							DEFAULT
								GET_GAME_TIMER cas4_text_timer_end
								cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
								IF cas4_text_timer_diff > 1000
									IF cas4_audio_playing = 0
									   cas4_end_of_cut_completed = 1	
									   TIMERB = 0
									ENDIF
								ENDIF
								BREAK
						ENDSWITCH
				   
				   ELSE
				   
				   	   	IF NOT IS_MESSAGE_BEING_DISPLAYED
							IF IS_CHAR_IN_ANY_CAR scplayer
						   		IF TIMERB > 10000
								   SWITCH cas4_banter3
										CASE 0
											IF cas4_audio_playing = 0									
												IF NOT IS_CHAR_DEAD	cas4_macca_ped
												AND NOT IS_CAR_DEAD cas4_player_car
													SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped TRUE
													SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped TRUE
													SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
												ENDIF
												cas4_audio_counter = 46 //CAS4_KA//]It seems you boys had a good time!
												cas4_banter3 = 1
												GET_GAME_TIMER cas4_text_timer_start									
											ENDIF
											BREAK
										CASE 1
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 47 //CAS4_KB//]What about the band?
													cas4_banter3 = 2
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF	
											BREAK
										CASE 2
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 48 //CAS4_KC//]We'll just have to pray they've made it to civilization.	
													cas4_banter3 = 3
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 3
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 49 //CAS4_KD//]Keyboardists and drummers are ten-a-penny anyway.	
													cas4_banter3 = 4
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK								
										CASE 4
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 92 //CAS4_QA//]Where is this casino?
													cas4_banter3 = 5													
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 5
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 93 //CAS4_QB//]It's called Caligula's, it's on the strip.
													cas4_banter3 = 6
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK				
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   IF NOT IS_CHAR_DEAD	cas4_macca_ped
													AND NOT IS_CAR_DEAD cas4_player_car
														SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped FALSE
														SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped FALSE
														SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
													ENDIF
												   cas4_banter3_completed = 1	
												   TIMERB = 0
												ENDIF
											ENDIF
											BREAK
									ENDSWITCH
								ENDIF
							ENDIF
						ENDIF
					ENDIF  
				ELSE
					IF cas4_banter4_completed = 0
						IF NOT IS_MESSAGE_BEING_DISPLAYED
							IF IS_CHAR_IN_ANY_CAR scplayer
								IF TIMERB > 10000
									SWITCH cas4_banter4
										CASE 0											
											IF cas4_audio_playing = 0									
												IF NOT IS_CHAR_DEAD	cas4_macca_ped
												AND NOT IS_CAR_DEAD cas4_player_car
													SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped TRUE
													SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped TRUE
													SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
												ENDIF
												cas4_audio_counter = 82 //CAS4_PA//]What kind of tits does this Rosie have?
												cas4_banter4 = 1
												GET_GAME_TIMER cas4_text_timer_start									
											ENDIF
											BREAK
										CASE 1
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 83 //CAS4_PB//]Big, floppy sausage tits?
													cas4_banter4 = 2
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF	
											BREAK
										CASE 2
											//GET_GAME_TIMER cas4_text_timer_end
											//cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											//IF cas4_text_timer_diff > 1000
												//IF cas4_audio_playing = 0
													//cas4_audio_counter = 84 //CAS4_PC//]Empty saddlebags?	
													cas4_banter4 = 3
													//GET_GAME_TIMER cas4_text_timer_start
												//ENDIF
											//ENDIF
											BREAK
										CASE 3
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 85 //CAS4_PD//]Empty saddlebags, Bee stings?.	
													cas4_banter4 = 4
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK								
										CASE 4
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 86 //CAS4_PE//]Rosie is a man!
													cas4_banter4 = 5
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 5
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 87 //CAS4_PF//]And stop touching yourself!
													cas4_banter4 = 6
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 6 
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 88 //CAS4_PG//]It's just for comfort, this is a stressful situation.
													cas4_banter4 = 7
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 7
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 89 //CAS4_PH//]You're fucking telling me it is!
													cas4_banter4 = 8
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 8
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 90 //CAS4_PJ//]Can it you two!
													cas4_banter4 = 9
													GET_GAME_TIMER cas4_text_timer_start
												ENDIF
											ENDIF
											BREAK
										CASE 9
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
													cas4_audio_counter = 91 //CAS4_PK//]He started it!
													cas4_banter4 = 10
													GET_GAME_TIMER cas4_text_timer_start 
												ENDIF
											ENDIF
											BREAK																														
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   IF NOT IS_CHAR_DEAD	cas4_macca_ped
													AND NOT IS_CAR_DEAD cas4_player_car
														SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped FALSE
														SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped FALSE
														SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
													ENDIF
												   cas4_banter4_completed = 1	
												ENDIF
											ENDIF
											BREAK
									ENDSWITCH
								ENDIF
							ENDIF
						ENDIF
					
					ENDIF
				ENDIF
			ENDIF
		ENDIF 
		

		
		
		//////////////////////////////////////////////
		IF cas4_blip_added_flag =0
			cas4_audio_left_behind_played = 0
			ADD_BLIP_FOR_COORD  2175.21 1679.37 9.85 cas4_end_blip
			CLEAR_PRINTS
			PRINT ( CAS4_06 ) 8000 1 ///////////// " Get to the Mafia Casino "
			cas4_blip_added_flag = 1
	     ENDIF
	ELSE
		IF cas4_blip_added_flag = 1
			//CLEAR_PRINTS
			REMOVE_BLIP cas4_end_blip		
			cas4_blip_added_flag =0						
		ENDIF												
						
		 
		IF cas4_paul_with_player = 0
		AND cas4_macca_with_player = 0				    								   		
	   		IF NOT  cas4_message_dsplayed = 0
	   			CLEAR_PRINTS
	   			PRINT (CAS4_37) 7000 1 //~s~Go and pick up Paul and Maccer.						   
				cas4_message_dsplayed = 0
			ENDIF			   	
		ENDIF

		IF cas4_paul_with_player = 1
		AND cas4_macca_with_player = 0	    			    
			IF NOT  cas4_message_dsplayed = 1
				CLEAR_PRINTS
				PRINT ( CAS4_03 ) 7000 1 ////////////" Get to Maccer "
				cas4_message_dsplayed = 1
			ENDIF				
		ENDIF	

		IF cas4_paul_with_player = 0
		AND cas4_macca_with_player = 1						
			IF NOT  cas4_message_dsplayed = 2
				CLEAR_PRINTS
				PRINT ( CAS4_02 ) 7000 1 ///////////// " Get to Paul "
				cas4_message_dsplayed = 2
			ENDIF				
		ENDIF											

		
		
		
		
		IF NOT IS_MESSAGE_BEING_DISPLAYED
			IF cas4_audio_left_behind_played = 0
				 
				IF NOT cas4_audio_counter = 0
					IF cas4_audio_playing = 0
						IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
							CLEAR_MISSION_AUDIO cas4_audio_slot2
						ENDIF
						cas4_audio_playing = 1
					ENDIF

					IF cas4_audio_playing = 1
						LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
						cas4_audio_playing = 2
					ENDIF

					IF cas4_audio_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
							PLAY_MISSION_AUDIO cas4_audio_slot1
							PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
							cas4_audio_playing = 3
						ENDIF
					ENDIF

					IF cas4_audio_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
							CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
							IF cas4_audio_slot1 = 1
								cas4_audio_slot1 = 2
								cas4_audio_slot2 = 1
							ELSE
								cas4_audio_slot1 = 1
								cas4_audio_slot2 = 2
							ENDIF
							cas4_audio_counter = 0
							cas4_audio_playing = 0
						ELSE
							IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
								IF cas4_audio_counter < 110
									cas4_ahead_counter = cas4_audio_counter + 1
									LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
							  
							  
				IF NOT IS_MESSAGE_BEING_DISPLAYED	
				SWITCH cas4_left_behind
					CASE 0
						IF cas4_audio_playing = 0							
							cas4_audio_counter = 40 //CAS4_HC//]Hey, wait for me, sunshine!
							cas4_left_behind = 1
							GET_GAME_TIMER cas4_text_timer_start							
						ENDIF
						BREAK
					CASE 1
						GET_GAME_TIMER cas4_text_timer_end
						cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
						IF cas4_text_timer_diff > 1000
							IF cas4_audio_playing = 0
								cas4_audio_counter = 41 //CAS4_HD//]Don't leave me in their clutches!
								cas4_left_behind = 2
								GET_GAME_TIMER cas4_text_timer_start
							ENDIF
						ENDIF	
						BREAK
					DEFAULT
						GET_GAME_TIMER cas4_text_timer_end
						cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
						IF cas4_text_timer_diff > 1000
							IF cas4_audio_playing = 0
							   cas4_left_behind = 0
							   cas4_audio_left_behind_played = 1
							ENDIF
						ENDIF
						BREAK
				ENDSWITCH
				ENDIF
			ENDIF
		ENDIF	
	ENDIF
				
ENDIF // cas4_mission_progression_flag = 1




//////////////////////////////////////////////////////////
///  add initial drive tasks for sister and incest guy ///
//////////////////////////////////////////////////////////

IF cas4_mission_progression_flag = 1
	IF IS_CHAR_IN_ANY_CAR scplayer
  	   STORE_CAR_CHAR_IS_IN scplayer cas4_player_car
  	ENDIF

	//WHILE cas4_old_in_car_flag = 0
	//WAIT 0
	IF NOT IS_CHAR_DEAD cas4_old_ped
	AND NOT IS_CAR_DEAD cas4_dune_car
		IF IS_CHAR_IN_CAR cas4_old_ped cas4_dune_car
	 		cas4_old_in_car_flag = 1 
	 	ENDIF
	ELSE
		cas4_old_in_car_flag = 1
	ENDIF
	//ENDWHILE
						   
	
	IF cas4_old_in_car_flag = 1
	AND cas4_sister_driving = 0			
		IF NOT IS_CHAR_DEAD cas4_sister_ped
		AND NOT IS_CAR_DEAD cas4_dune_car
	    AND	NOT IS_CAR_DEAD cas4_player_car
			OPEN_SEQUENCE_TASK cas4_sister_drive_seq
			TASK_CAR_DRIVE_TO_COORD -1 cas4_dune_car -44.86 2345.23 23.16   30.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
  	  		TASK_CAR_MISSION -1 cas4_dune_car cas4_player_car MISSION_RAMPLAYER_CLOSE 35.0 DRIVINGMODE_AVOIDCARS
	  	    CLOSE_SEQUENCE_TASK cas4_sister_drive_seq
	  		PERFORM_SEQUENCE_TASK	cas4_sister_ped cas4_sister_drive_seq
	  		CLEAR_SEQUENCE_TASK cas4_sister_drive_seq
			cas4_sister_driving = 1	    
		ENDIF
	ENDIF
		//////////////////////////////////////////////////// make the incest guy drive ///////////////
			
	     	
	IF NOT IS_CHAR_DEAD cas4_pig_ped
	AND NOT IS_CAR_DEAD cas4_quad_car
	    IF IS_CHAR_IN_CAR cas4_pig_ped cas4_quad_car
 			cas4_pig_in_car_flag = 1 
 		ENDIF
	ELSE
		cas4_pig_in_car_flag =1
	ENDIF
	
				
	
	IF cas4_pig_in_car_flag =1
	AND cas4_incest_driving = 0
		IF NOT IS_CHAR_DEAD cas4_incest_ped
		AND NOT IS_CAR_DEAD cas4_quad_car
		AND NOT IS_CAR_DEAD cas4_player_car
			OPEN_SEQUENCE_TASK cas4_incest_drive_seq			
	  		TASK_CAR_DRIVE_TO_COORD -1 cas4_quad_car -50.86 2349.23 23.16   30.0 MODE_STRAIGHTLINE FALSE DRIVINGMODE_PLOUGHTHROUGH
  	  		TASK_CAR_MISSION -1 cas4_quad_car cas4_player_car MISSION_RAMPLAYER_CLOSE 35.0 DRIVINGMODE_AVOIDCARS
	  		CLOSE_SEQUENCE_TASK cas4_incest_drive_seq
	  		PERFORM_SEQUENCE_TASK cas4_incest_ped cas4_incest_drive_seq
	  		CLEAR_SEQUENCE_TASK cas4_incest_drive_seq
 			cas4_incest_driving = 1	  		
		ENDIF
	ENDIF
	
	 IF cas4_pig_in_car_flag =1
	 AND cas4_old_in_car_flag = 1 
	 	IF NOT IS_CHAR_DEAD cas4_macca_ped
		AND NOT IS_CHAR_DEAD cas4_paul_ped
			SET_CHAR_PROOFS cas4_paul_ped FALSE FALSE FALSE FALSE TRUE
			SET_CHAR_PROOFS cas4_macca_ped FALSE FALSE FALSE FALSE TRUE
		ENDIF
	 	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_SHOT_FIRED_WHIZZED_BY
		CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_SHOT_FIRED
	 	cas4_mission_progression_flag = 2
	 ENDIF

ENDIF 
 
   				

///////////////////////////////////////////////////////////////////////////////////////////////
/// Hill billy chase code if player gets too far away from the hillbilly's warp them closer ///
///////////////////////////////////////////////////////////////////////////////////////////////

IF cas4_mission_progression_flag = 2
AND cas4_hill_billy_stand_played = 0
    

	IF IS_CHAR_DEAD cas4_sister_ped
		cas4_driver1_in_car = 0
	ELSE
		IF NOT IS_CAR_DEAD cas4_dune_car
			IF IS_CHAR_IN_CAR cas4_sister_ped cas4_dune_car
				cas4_driver1_in_car = 1
			ELSE
			   cas4_driver1_in_car = 0
			ENDIF
		ENDIF
	ENDIF


	IF IS_CHAR_DEAD cas4_incest_ped
		cas4_driver2_in_car = 0
	ELSE
		IF NOT IS_CAR_DEAD cas4_quad_car
			IF IS_CHAR_IN_CAR cas4_incest_ped cas4_quad_car
				cas4_driver2_in_car =  1
			ELSE
				cas4_driver2_in_car = 0
			ENDIF
		ELSE
			 cas4_driver2_in_car = 0
		ENDIF
	 ENDIF
	  
	  	  
	    IF NOT IS_CHAR_DEAD cas4_old_ped	    		    	
			IF IS_CAR_DEAD cas4_dune_car   
	    	OR NOT IS_CHAR_IN_ANY_CAR cas4_old_ped
	    	OR cas4_driver1_in_car = 0 
	    	  
	    	   GET_SCRIPT_TASK_STATUS cas4_old_ped PERFORM_SEQUENCE_TASK  cas4_task_status
			   IF cas4_task_status = FINISHED_TASK

	    		   GET_SCRIPT_TASK_STATUS cas4_old_ped TASK_KILL_CHAR_ON_FOOT  cas4_task_status
	    		   IF NOT cas4_task_status  = WAITING_TO_START_TASK
				   AND NOT cas4_task_status  = PERFORMING_TASK						   		
	    		          IF  cas4_old_task_assigned = 0			
	    		   			TASK_KILL_CHAR_ON_FOOT cas4_old_ped scplayer						 
							cas4_old_task_assigned = 1
						  ENDIF
				   ELSE 
						IF cas4_task_status = FINISHED_TASK
							cas4_old_task_assigned = 0
						ENDIF
				   ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF NOT IS_CHAR_DEAD cas4_sister_ped	    		    	
			IF  IS_CAR_DEAD cas4_dune_car
		    OR  cas4_driver1_in_car = 0				    
	    	   GET_SCRIPT_TASK_STATUS cas4_sister_ped PERFORM_SEQUENCE_TASK  cas4_task_status
			   IF cas4_task_status = FINISHED_TASK

	    		   GET_SCRIPT_TASK_STATUS cas4_sister_ped TASK_KILL_CHAR_ON_FOOT  cas4_task_status
	    		   IF NOT cas4_task_status  = WAITING_TO_START_TASK
				   AND NOT cas4_task_status  = PERFORMING_TASK						   		
	    		          IF  cas4_sister_task_assigned = 0			
	    		   			TASK_KILL_CHAR_ON_FOOT cas4_sister_ped scplayer						 
							cas4_sister_task_assigned = 1
						  ENDIF
				   ELSE 
						IF cas4_task_status = FINISHED_TASK
							cas4_sister_task_assigned = 0
						ENDIF
				   ENDIF
				ENDIF
			ENDIF
		ENDIF		
		
		IF NOT IS_CHAR_DEAD cas4_pig_ped	    		    	
			IF IS_CAR_DEAD cas4_quad_car
		    OR NOT IS_CHAR_IN_ANY_CAR cas4_pig_ped
		    OR cas4_driver2_in_car = 0    
	    	   GET_SCRIPT_TASK_STATUS cas4_pig_ped PERFORM_SEQUENCE_TASK  cas4_task_status
			   IF cas4_task_status = FINISHED_TASK

	    		   GET_SCRIPT_TASK_STATUS cas4_pig_ped TASK_KILL_CHAR_ON_FOOT  cas4_task_status
	    		   IF NOT cas4_task_status  = WAITING_TO_START_TASK
				   AND NOT cas4_task_status  = PERFORMING_TASK						   		
	    		          IF  cas4_pig_task_assigned = 0			
	    		   			TASK_KILL_CHAR_ON_FOOT cas4_pig_ped scplayer						 
							cas4_pig_task_assigned = 1
						  ENDIF
				   ELSE 
						IF cas4_task_status = FINISHED_TASK
							cas4_pig_task_assigned = 0
						ENDIF
				   ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		IF NOT IS_CHAR_DEAD cas4_incest_ped	    		    	
			IF IS_CAR_DEAD cas4_quad_car
			OR cas4_driver2_in_car = 0 
				      
		       	GET_SCRIPT_TASK_STATUS cas4_incest_ped PERFORM_SEQUENCE_TASK  cas4_task_status
				IF cas4_task_status = FINISHED_TASK

		    	    GET_SCRIPT_TASK_STATUS cas4_incest_ped TASK_KILL_CHAR_ON_FOOT  cas4_task_status
		    	    IF NOT cas4_task_status  = WAITING_TO_START_TASK
				    AND NOT cas4_task_status  = PERFORMING_TASK						   		
		    	           IF  cas4_incest_task_assigned = 0			
		    	   			TASK_KILL_CHAR_ON_FOOT cas4_incest_ped scplayer						 
				 			cas4_incest_task_assigned = 1
				 		  ENDIF
				    ELSE 
				 		IF cas4_task_status = FINISHED_TASK
				 			cas4_incest_task_assigned = 0
				 		ENDIF
				    ENDIF
				
				ENDIF
			ENDIF
		ENDIF
		
		///////////////////////////////////////////////
		///////////////////	 CAR catch up stuff ///////
		///////////////////////////////////////////////					
	
		IF NOT IS_CHAR_DEAD	cas4_sister_ped
		AND NOT IS_CAR_DEAD	cas4_dune_car 
		 						
			IF IS_CHAR_IN_CAR cas4_sister_ped cas4_dune_car		      		      
				SET_CAR_CRUISE_SPEED cas4_dune_car 35.0
	  			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cas4_sister_ped 100.0 100.0  FALSE
				AND NOT IS_CAR_ON_SCREEN cas4_dune_car
				  	//IF NOT IS_CHAR_IN_AIR scplayer
					IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
					  	GET_CHAR_COORDINATES scplayer cas4_playerx cas4_playery cas4_playerz			  	
					  	GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer -10.0 -10.0 0.0 cas4_carx cas4_cary cas4_carz			  	
					    IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY cas4_carx cas4_cary cas4_carz 4.0 4.0 3.0
						 	IF NOT IS_POINT_ON_SCREEN cas4_carx cas4_cary cas4_carz 4.0
								SET_CAR_COORDINATES cas4_dune_car cas4_carx cas4_cary cas4_carz
								TURN_CAR_TO_FACE_COORD cas4_dune_car  cas4_playerx cas4_playery
								SET_CAR_MISSION cas4_dune_car MISSION_RAMPLAYER_CLOSE
							ENDIF
						ENDIF
					ENDIF				 
				ENDIF
			ENDIF
		ENDIF

		IF NOT IS_CHAR_DEAD	cas4_incest_ped
		AND NOT IS_CAR_DEAD	cas4_quad_car
			IF IS_CHAR_IN_CAR cas4_incest_ped cas4_quad_car
			SET_CAR_CRUISE_SPEED cas4_quad_car 35.0
	  			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D scplayer cas4_incest_ped 100.0 100.0  FALSE
				AND NOT IS_CAR_ON_SCREEN cas4_quad_car
				  	//IF NOT IS_CHAR_IN_AIR scplayer
					IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
					  	GET_CHAR_COORDINATES scplayer cas4_playerx cas4_playery cas4_playerz
						GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer  -13.0 -13.0 0.0 cas4_carx cas4_cary cas4_carz				  	
					    IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY cas4_carx cas4_cary cas4_carz 4.0 4.0 3.0
						 	IF NOT IS_POINT_ON_SCREEN cas4_carx cas4_cary cas4_carz 4.0
								SET_CAR_COORDINATES cas4_quad_car cas4_carx cas4_cary cas4_carz
								TURN_CAR_TO_FACE_COORD cas4_quad_car  cas4_playerx cas4_playery
								SET_CAR_MISSION cas4_quad_car MISSION_RAMPLAYER_CLOSE
							ENDIF
						ENDIF
					ENDIF				 
				ENDIF
			ENDIF
		ENDIF

ENDIF






////////////////////////// change_blip_stuff /////////////


cas4_counter = 0
cas4_hill_billies_alive = 0
WHILE cas4_counter < 4
IF IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter]
   cas4_hill_billy_dead[cas4_counter] = 1
ELSE      
   cas4_hill_billy_dead[cas4_counter] = 0
   cas4_hill_billies_alive++
ENDIF
cas4_counter++	
ENDWHILE

 
///////////////////////////////////////////////////////////////////////////////
///  If player at casino and prerequisits have been done start end cutscene ///                                                                
//////////////////////////////////////////////////////////////////////////////
IF cas4_blip_added_flag = 1
OR cas4_at_end_flag = 1

	IF NOT IS_CHAR_DEAD cas4_paul_ped
	AND NOT IS_CHAR_DEAD cas4_macca_ped
		IF cas4_at_end_flag = 0
			IF cas4_hill_billies_alive > 0 
			AND cas4_hill_billy_stand_played = 0       
		        IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2175.21 1679.37 9.85 4.0 4.0  2.0 TRUE
		        	GOSUB cas4_last_stand
					cas4_hill_billy_stand_played = 1
					cas4_at_end_flag = 1
				ENDIF
		    ELSE 		        
		        
		        
		        IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2175.21 1679.37 9.85 4.0 4.0  2.0 TRUE
				AND LOCATE_CHAR_ANY_MEANS_3D cas4_paul_ped 2175.21 1679.37 9.85 6.0 6.0  2.0 FALSE
				AND LOCATE_CHAR_ANY_MEANS_3D cas4_macca_ped 2175.21 1679.37 9.85 6.0 6.0  2.0 FALSE
					
					REMOVE_BLIP cas4_end_blip
					cas4_at_end_flag = 1
					CLEAR_PRINTS									
				ENDIF
				
				
				///////////////////////////////////////////////////////
				///////////////////////////////////////////////////////
				///////////////////////////////////////////////////////
																									
			ENDIF
		ELSE
		/////////////////////////////////
		//// PLAY END CUT-SCENE HERE ////
		/////////////////////////////////
			   		IF NOT IS_CHAR_DEAD cas4_macca_ped
					AND NOT IS_CHAR_DEAD cas4_paul_ped
						
				   ////////////// PLAY CUT_SCENE \\\\\\\\\\
							CLEAR_PRINTS
							SET_PLAYER_CONTROL Player1 OFF
							DO_FADE 1500 FADE_OUT 
							WHILE GET_FADING_STATUS
							WAIT 0
							ENDWHILE
							
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_incest_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_old_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_sister_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_pig_ped
							
							MARK_CAR_AS_NO_LONGER_NEEDED cas4_dune_car
							MARK_CAR_AS_NO_LONGER_NEEDED cas4_quad_car

							
							
							SWITCH_WIDESCREEN ON					
							
							SET_FIXED_CAMERA_POSITION 2188.26 1678.00  11.19 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 2153.54 1692.94 12.04 JUMP_CUT
							IF NOT IS_CHAR_DEAD cas4_macca_ped
							AND NOT IS_CHAR_DEAD cas4_paul_ped
								IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
									WARP_CHAR_FROM_CAR_TO_COORD cas4_macca_ped 2179.7075 1680.5681 10.0469
								ELSE
									SET_CHAR_COORDINATES cas4_macca_ped 2179.7075 1680.5681 10.0469 
								ENDIF
								SET_CHAR_HEADING cas4_macca_ped 266.3517

								IF IS_CHAR_IN_ANY_CAR cas4_paul_ped	
									WARP_CHAR_FROM_CAR_TO_COORD cas4_paul_ped 2179.7075 1677.5681 10.0469 
								ELSE
									SET_CHAR_COORDINATES cas4_paul_ped 2179.7075 1677.5681 10.0469  
								ENDIF
								SET_CHAR_HEADING cas4_paul_ped  266.3517

								IF IS_CHAR_IN_ANY_CAR scplayer	
									WARP_CHAR_FROM_CAR_TO_COORD scplayer 2179.7593 1678.9568 10.0469 
								ELSE
									SET_CHAR_COORDINATES scplayer 2179.7593 1678.9568 10.0469  
								ENDIF
								SET_CHAR_HEADING scplayer   269.2490

	 							REMOVE_DECISION_MAKER cas4_decision2
	 							LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY cas4_decision2							
								CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE cas4_decision2 EVENT_POTENTIAL_WALK_INTO_PED								

								SET_CHAR_DECISION_MAKER  cas4_macca_ped cas4_decision2
								SET_CHAR_DECISION_MAKER  cas4_paul_ped cas4_decision2	
								
								TASK_GO_STRAIGHT_TO_COORD scplayer 2187.0 1680.0 11.4 PEDMOVE_WALK -1															
										   
	    						REMOVE_CHAR_FROM_GROUP cas4_macca_ped																					
								TASK_GO_STRAIGHT_TO_COORD cas4_macca_ped 2187.0 1680.0 11.4 PEDMOVE_WALK -1 							 
															 									
								REMOVE_CHAR_FROM_GROUP cas4_paul_ped									  	
								TASK_GO_STRAIGHT_TO_COORD cas4_paul_ped 2187.0 1680.0 11.4 PEDMOVE_WALK -1		
								
							ENDIF	
							 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
							DO_FADE 1000 FADE_IN 
							WHILE GET_FADING_STATUS
							WAIT 0
							ENDWHILE

							TIMERA = 0

							
							cas4_audio_counter 		= 0
							cas4_audio_slot1   		= 1
							cas4_audio_slot2   		= 2
							cas4_audio_playing 		= 0
							cas4_text_loop_done		= 0
							cas4_mobile				= 0
							cas4_text_timer_diff	= 0 
							cas4_text_timer_end 	= 0
							cas4_text_timer_start 	= 0
							cas4_ahead_counter	  	= 0



							casino4_text_loop5:
							WAIT 0 

							IF cas4_text_loop_done = 0
								
								
								IF NOT cas4_audio_counter = 0
									IF cas4_audio_playing = 0
										IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
											CLEAR_MISSION_AUDIO cas4_audio_slot2
										ENDIF
										cas4_audio_playing = 1
									ENDIF

									IF cas4_audio_playing = 1
										LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
										cas4_audio_playing = 2
									ENDIF

									IF cas4_audio_playing = 2
									 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
											PLAY_MISSION_AUDIO cas4_audio_slot1
											PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
											cas4_audio_playing = 3
										ENDIF
									ENDIF

									IF cas4_audio_playing = 3
										IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
											CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
											IF cas4_audio_slot1 = 1
												cas4_audio_slot1 = 2
												cas4_audio_slot2 = 1
											ELSE
												cas4_audio_slot1 = 1
												cas4_audio_slot2 = 2
											ENDIF
											cas4_audio_counter = 0
											cas4_audio_playing = 0
										ELSE
											IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
												IF cas4_audio_counter < 110
													cas4_ahead_counter = cas4_audio_counter + 1
													LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF

								
									SWITCH cas4_mobile
										CASE 0
											IF cas4_audio_playing = 0
												IF NOT IS_CHAR_DEAD	cas4_paul_ped												
													STOP_CHAR_FACIAL_TALK cas4_paul_ped
												 	START_CHAR_FACIAL_TALK cas4_paul_ped	 3000
												ENDIF
												cas4_audio_counter = 99 //= CAS4_SA//]Come on, let's go in and see Rosie.
												cas4_mobile = 1
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
											BREAK																				
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   IF NOT IS_CHAR_DEAD	cas4_paul_ped												
													STOP_CHAR_FACIAL_TALK cas4_paul_ped												 	
													ENDIF
												   cas4_text_loop_done = 1	
												ENDIF
											ENDIF
											BREAK
									ENDSWITCH
								
								
								GOTO casino4_text_loop5

							ENDIF							
							
							
							WHILE  cas4_at_casino_flag = 0
								
								WAIT 0	 	 					
									IF LOCATE_CHAR_ANY_MEANS_2D scplayer 2187.0 1680.0  2.0 2.0 FALSE					    
					   					cas4_at_casino_flag =1
					   				ENDIF									

								IF TIMERA > 15000	   // safety timer to exit loop after 20 seconds
									IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY 2195.35 1680.0577 11.4 1.5 1.5 3.0
										SET_CHAR_COORDINATES scplayer  2187.0 1680.0 11.4						
									ENDIF
									cas4_at_casino_flag = 1
								ENDIF
							  			

							ENDWHILE
							
															
														
							SET_FADING_COLOUR 0 0 0
							DO_FADE 1000 FADE_OUT
							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE
							FORCE_WEATHER WEATHER_SUNNY_VEGAS
							
							
							MARK_CAR_AS_NO_LONGER_NEEDED cas4_player_car
							MARK_CAR_AS_NO_LONGER_NEEDED cas4_quad_car
							MARK_CAR_AS_NO_LONGER_NEEDED cas4_dune_car
							MARK_CAR_AS_NO_LONGER_NEEDED cas4_temp_car

							REMOVE_CHAR_ELEGANTLY  cas4_paul_ped
							REMOVE_CHAR_ELEGANTLY  cas4_macca_ped
							 
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_old_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_sister_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_pig_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_incest_ped
							
							
							

							//MARK_MODEL_AS_NO_LONGER_NEEDED PAUL

							//MARK_MODEL_AS_NO_LONGER_NEEDED MACCER

							MARK_MODEL_AS_NO_LONGER_NEEDED WALTON							
							MARK_MODEL_AS_NO_LONGER_NEEDED DWFOLC
							MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC1
							MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC2
							MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1
							MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1
							MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
							
							
							/////////////////////////////////////

							UNLOAD_SPECIAL_CHARACTER 1
							UNLOAD_SPECIAL_CHARACTER 2
							
							
							MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1
							//SWITCH_STREAMING OFF 

							SET_AREA_VISIBLE 2

							LOAD_CUTSCENE CAS_4c
							 
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

							RELEASE_WEATHER
							
							RESTORE_CAMERA_JUMPCUT
							DELETE_CHAR cas4_paul_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_paul_ped
							DELETE_CHAR cas4_macca_ped
							MARK_CHAR_AS_NO_LONGER_NEEDED cas4_macca_ped
							SET_CHAR_COORDINATES scplayer  2187.0 1680.0 10.1060
							SET_CHAR_HEADING scplayer 90.0
							LOAD_SCENE 2187.0 1680.0 10.1060
							
							REQUEST_MODEL CELLPHONE
							WHILE NOT HAS_MODEL_LOADED CELLPHONE
							WAIT 0 
							ENDWHILE
							
							SET_FIXED_CAMERA_POSITION 2186.5242 1679.3633 11.6094 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT 2186.9832 1680.2462 11.7063 JUMP_CUT
							
							SET_PLAYER_CONTROL Player1 OFF
							SWITCH_WIDESCREEN ON
							
							MAKE_PLAYER_GANG_DISAPPEAR
							
							DO_FADE 1500 FADE_IN
							
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE
														
							TASK_USE_MOBILE_PHONE scplayer TRUE
							WAIT 2000
							
							cas4_audio_counter 		= 0
							cas4_audio_slot1   		= 1
							cas4_audio_slot2   		= 2
							cas4_audio_playing 		= 0
							cas4_text_loop_done		= 0
							cas4_mobile				= 0
							cas4_text_timer_diff	= 0 
							cas4_text_timer_end 	= 0
							cas4_text_timer_start 	= 0
							cas4_ahead_counter	  	= 0
							SKIP_CUTSCENE_START
							casino4_text_loop6:
							WAIT 0 

							IF cas4_text_loop_done = 0
								
								
								IF NOT cas4_audio_counter = 0
									IF cas4_audio_playing = 0
										IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
											CLEAR_MISSION_AUDIO cas4_audio_slot2
										ENDIF
										cas4_audio_playing = 1
									ENDIF

									IF cas4_audio_playing = 1
										LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
										cas4_audio_playing = 2
									ENDIF

									IF cas4_audio_playing = 2
									 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
											PLAY_MISSION_AUDIO cas4_audio_slot1
											PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
											cas4_audio_playing = 3
										ENDIF
									ENDIF

									IF cas4_audio_playing = 3
										IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
											CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
											IF cas4_audio_slot1 = 1
												cas4_audio_slot1 = 2
												cas4_audio_slot2 = 1
											ELSE
												cas4_audio_slot1 = 1
												cas4_audio_slot2 = 2
											ENDIF
											cas4_audio_counter = 0
											cas4_audio_playing = 0
										ELSE
											IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
												IF cas4_audio_counter < 110
													cas4_ahead_counter = cas4_audio_counter + 1
													LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF

								
									SWITCH cas4_mobile
										CASE 0
											IF cas4_audio_playing = 0
												
													STOP_CHAR_FACIAL_TALK scplayer
												 	START_CHAR_FACIAL_TALK scplayer	 3000
												
												cas4_audio_counter = 100// SOUND_CAS4_TA Yo, Woozie, just a quick call to say I think I've found a way 
												cas4_mobile = 1
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
											BREAK
										CASE 1
											IF cas4_audio_playing = 0
												cas4_audio_counter = 101// SOUND_CAS4_TB to scope Caligula's Casino without causing too much suspicion.
												STOP_CHAR_FACIAL_TALK scplayer
												 	START_CHAR_FACIAL_TALK scplayer	 3000
												cas4_mobile = 2
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
											BREAK
										CASE 2
											IF cas4_audio_playing = 0
												cas4_audio_counter = 102// SOUND_CAS4_TC We can talk later.
												cas4_mobile = 3
												GET_GAME_TIMER cas4_text_timer_start
											ENDIF
											BREAK
																															
										DEFAULT
											GET_GAME_TIMER cas4_text_timer_end
											cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
											IF cas4_text_timer_diff > 1000
												IF cas4_audio_playing = 0
												   STOP_CHAR_FACIAL_TALK scplayer
												   
												   cas4_text_loop_done = 1	
												ENDIF
											ENDIF
											BREAK
									ENDSWITCH
									
									
									
								
								GOTO casino4_text_loop6

							ENDIF

								SKIP_CUTSCENE_END
								
								
								MAKE_PLAYER_GANG_REAPPEAR
								STOP_CHAR_FACIAL_TALK scplayer												   
								CLEAR_MISSION_AUDIO cas4_audio_slot1
								CLEAR_MISSION_AUDIO cas4_audio_slot2

								GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE cas4_task_status
								IF NOT cas4_task_status = FINISHED_TASK
									TASK_USE_MOBILE_PHONE scplayer FALSE
								ENDIF
								MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
								SET_CAMERA_BEHIND_PLAYER
								RESTORE_CAMERA_JUMPCUT
							SWITCH_WIDESCREEN OFF
							SET_PLAYER_CONTROL player1 ON
							GOTO mission_casino4_passed

							cas4_played_cutscene_flag =1
							
															
						
				    ENDIF
								
				//////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////
				//////////////////////////////////////////////////////////
				
		
		
		
		ENDIF //cas4_at_end_flag = 0
	ENDIF
ENDIF




GOTO casino4_main_loop


/////////////////////////////////////
/////////////  LABEL FNCTIONS ///////
/////////////////////////////////////


cas4_fight_scene:



IF NOT IS_CHAR_DEAD	cas4_macca_ped
AND NOT IS_CHAR_DEAD cas4_paul_ped
	//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer  0.0 1.3 0.0  cas4_fight_x cas4_fight_y cas4_fight_z
	
	//PRINT (CAS4_31) 4000 1 // fight over seat
	//PRINT (CAS4_32) 4000 1 // fight over seat
	//PRINT (CAS4_44) 10000 1//~s~They're going to keep on fighting until you find a car with enough seats for the both of them.
	
	OPEN_SEQUENCE_TASK cas4_seq 	
	TASK_GOTO_CHAR -1 cas4_macca_ped  -1 1.2								
	TASK_TURN_CHAR_TO_FACE_CHAR -1 cas4_macca_ped
	TASK_PLAY_ANIM -1 PnM_Loop_A PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // paul idle
	TASK_PLAY_ANIM -1 PnM_Argue2_A PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //paul pushed
	TASK_PLAY_ANIM -1 PnM_Loop_A PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //paul idle
	TASK_PLAY_ANIM -1 PnM_Argue1_A PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //paul pushing
	TASK_PLAY_ANIM -1 PnM_Loop_A PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //paul idle
	SET_SEQUENCE_TO_REPEAT cas4_seq 1
	CLOSE_SEQUENCE_TASK cas4_seq 
	PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_seq 
	CLEAR_SEQUENCE_TASK cas4_seq 
	
	OPEN_SEQUENCE_TASK cas4_seq	
	TASK_GOTO_CHAR -1 cas4_paul_ped -1 1.2
	TASK_TURN_CHAR_TO_FACE_CHAR -1 cas4_paul_ped
	TASK_PLAY_ANIM -1 PnM_Loop_B PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // macca idle
	TASK_PLAY_ANIM -1 PnM_Argue2_B PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //macca pushing
	TASK_PLAY_ANIM -1 PnM_Loop_B PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // macca idle
	TASK_PLAY_ANIM -1 PnM_Argue1_B PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 //macca pushed
	TASK_PLAY_ANIM -1 PnM_Loop_B PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // macca idle
	SET_SEQUENCE_TO_REPEAT cas4_seq 1
	CLOSE_SEQUENCE_TASK cas4_seq
	PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_seq
	CLEAR_SEQUENCE_TASK cas4_seq																									
ENDIF

RETURN






cas4_last_stand: // at casino

LVAR_INT cas4_paul_target cas4_macca_target	cas4_counter2 
LVAR_INT cas4_temp_flag cas4_temp_flag2 cas4_temp_flag3
LVAR_INT cas4_temp_driver

SET_GROUP_DECISION_MAKER Players_Group -1
SET_POLICE_IGNORE_PLAYER player1 ON
SET_WANTED_MULTIPLIER 0.0

cas4_temp_hill_billy[0] = cas4_old_ped
cas4_temp_hill_billy[1]	= cas4_sister_ped
cas4_temp_hill_billy[2]	= cas4_incest_ped
cas4_temp_hill_billy[3]	= cas4_pig_ped



cas4_counter = 0
cas4_hill_billies_alive = 0
WHILE cas4_counter < 4
	IF IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter]
	   cas4_hill_billy_dead[cas4_counter] = 1
	ELSE 
	   CLEAR_CHAR_TASKS	cas4_temp_hill_billy[cas4_counter]
	   SET_CURRENT_CHAR_WEAPON cas4_temp_hill_billy[cas4_counter]   WEAPONTYPE_UNARMED  
	   cas4_hill_billy_dead[cas4_counter] = 0
	   cas4_hill_billies_alive++
	ENDIF
	cas4_counter++	
ENDWHILE






IF cas4_hill_billies_alive > 0
			
	
	REMOVE_BLIP cas4_end_blip
	cas4_blip_added_flag = 0  

	///// HAVE CUT-SCENE /////
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	//////// HAVE DECENT CAMERA ANGLE GO IN HERE ///
	CLEAR_AREA 2165.6121 1680.6188 9.8047 50.0 TRUE
	CLEAR_PRINTS
	
	
	
	
	SET_FIXED_CAMERA_POSITION 2171.91 1669.08 10.32	0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 2164.93 1708.54 16.85 JUMP_CUT
	
	REQUEST_ANIMATION MISC
	REQUEST_ANIMATION GANGS
	
	SET_PED_DENSITY_MULTIPLIER 0.0
	SET_CAR_DENSITY_MULTIPLIER 0.0

	
	IF NOT IS_CAR_DEAD cas4_quad_car
		IF NOT IS_CHAR_DEAD	cas4_incest_ped
			IF IS_CHAR_IN_CAR cas4_incest_ped cas4_quad_car
				SET_CAR_COORDINATES cas4_quad_car 2096.1365 1643.9520 9.7768 
				SET_CAR_HEADING cas4_quad_car 270.9418		
				TASK_CAR_DRIVE_TO_COORD cas4_incest_ped cas4_quad_car 2158.5183 1681.4022 9.8047 30.0 MODE_ACCURATE 0 DRIVINGMODE_SLOWDOWNFORCARS				
			ENDIF
		ENDIF
	ENDIF
	 
	IF NOT IS_CAR_DEAD cas4_dune_car
		IF NOT IS_CHAR_DEAD cas4_sister_ped
			IF IS_CHAR_IN_CAR cas4_sister_ped cas4_dune_car
				SET_CAR_COORDINATES cas4_dune_car 2080.9336 1643.6493 9.6757 
				SET_CAR_HEADING cas4_dune_car 271.0448					
				TASK_CAR_DRIVE_TO_COORD cas4_sister_ped cas4_dune_car 2154.2747 1664.9645 9.8125 30.0 MODE_ACCURATE 0 DRIVINGMODE_SLOWDOWNFORCARS				
 			ENDIF
		ENDIF
	ENDIF
			 
	IF IS_CHAR_IN_ANY_CAR scplayer 
		STORE_CAR_CHAR_IS_IN scplayer cas4_player_car						
		IF NOT IS_CAR_DEAD cas4_player_car
			SET_CAR_MISSION  cas4_player_car MISSION_STOP_FOREVER
			cas4_safety_flag = 0
			TIMERB = 0			
			WHILE cas4_safety_flag = 0
  				IF cas4_crap_assigned = 0
					IF NOT IS_CAR_DEAD cas4_player_car														
						IF IS_CAR_STOPPED cas4_player_car							
			    			OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]								
							IF IS_CHAR_IN_ANY_CAR scplayer
								TASK_LEAVE_ANY_CAR -1
							ENDIF
			   				TASK_GO_STRAIGHT_TO_COORD -1 2171.0 1671.0 11.0   PEDMOVE_WALK -1
							CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
							PERFORM_SEQUENCE_TASK scplayer cas4_hill_billy_seq[0]								 
							CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
							 
							IF NOT IS_CHAR_DEAD cas4_macca_ped
								REMOVE_CHAR_FROM_GROUP cas4_macca_ped
								OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]
								IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
									TASK_LEAVE_ANY_CAR -1
								ENDIF
							    TASK_GO_STRAIGHT_TO_COORD -1 2172.0 1669.0 10.8125 PEDMOVE_WALK -1//82.2107 
							    CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
								PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_hill_billy_seq[0]								 
								CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
					  		ENDIF

					  		IF NOT IS_CHAR_DEAD cas4_paul_ped
						  		REMOVE_CHAR_FROM_GROUP cas4_paul_ped
						  		OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]
								IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
									TASK_LEAVE_ANY_CAR -1
								ENDIF
						  		TASK_GO_STRAIGHT_TO_COORD -1 2170.0 1673.0 10.8203 PEDMOVE_WALK -1//101.6816 
								CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
								PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_hill_billy_seq[0]								 
								CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
							ENDIF
							cas4_safety_flag = 1 
							cas4_crap_assigned = 1
						ENDIF
					 ENDIF
				ENDIF																	 													
									
				IF TIMERB > 20000
					cas4_safety_flag = 1
						
				ENDIF				    														
			WAIT 0 
			ENDWHILE							
		ENDIF
	ELSE
		OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]										
		IF IS_CHAR_IN_ANY_CAR scplayer
			TASK_LEAVE_ANY_CAR -1
		ENDIF
		TASK_GO_STRAIGHT_TO_COORD -1 2165.6121 1680.6188 9.8047   PEDMOVE_WALK -1		
		CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
		PERFORM_SEQUENCE_TASK scplayer cas4_hill_billy_seq[0]								 
		CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
		 
		IF NOT IS_CHAR_DEAD cas4_macca_ped
			OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]			
		    IF IS_CHAR_IN_ANY_CAR cas4_macca_ped
				TASK_LEAVE_ANY_CAR -1
			ENDIF
		    TASK_GO_STRAIGHT_TO_COORD -1 2166.4429 1678.6064 9.8125 PEDMOVE_WALK -1//82.2107 
		    CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
			PERFORM_SEQUENCE_TASK cas4_macca_ped cas4_hill_billy_seq[0]								 
			CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
  		ENDIF

  		IF NOT IS_CHAR_DEAD cas4_paul_ped
	  		OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]			
	  		IF IS_CHAR_IN_ANY_CAR cas4_paul_ped
	  			TASK_LEAVE_ANY_CAR -1
			ENDIF
	  		TASK_GO_STRAIGHT_TO_COORD -1 2166.7063 1683.2908 9.8203 PEDMOVE_WALK -1//101.6816 
			CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
			PERFORM_SEQUENCE_TASK cas4_paul_ped cas4_hill_billy_seq[0]								 
			CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
		ENDIF

	
	ENDIF // IS_CHAR_IN_ANY_CAR scplayer




	cas4_audio_counter 		= 0
	cas4_audio_slot1   		= 1
	cas4_audio_slot2   		= 2
	cas4_audio_playing 		= 0
	cas4_text_loop_done		= 0
	cas4_mobile				= 0
	cas4_text_timer_diff	= 0 
	cas4_text_timer_end 	= 0
	cas4_text_timer_start 	= 0
	cas4_ahead_counter	  	= 0

	cas4_safety_flag = 0
	cas4_temp_flag 	 = 0
	cas4_temp_flag2  = 0
	cas4_temp_flag3	 = 0
	TIMERB = 0


	casino4_text_loop4:
	WAIT 0 

	IF cas4_text_loop_done = 0
		
		
		IF NOT cas4_audio_counter = 0
			IF cas4_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
					CLEAR_MISSION_AUDIO cas4_audio_slot2
				ENDIF
				cas4_audio_playing = 1
			ENDIF

			IF cas4_audio_playing = 1
				LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
				cas4_audio_playing = 2
			ENDIF

			IF cas4_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
					PLAY_MISSION_AUDIO cas4_audio_slot1
					PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
					cas4_audio_playing = 3
				ENDIF
			ENDIF

			IF cas4_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
					STOP_CHAR_FACIAL_TALK scplayer
					CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
					IF cas4_audio_slot1 = 1
						cas4_audio_slot1 = 2
						cas4_audio_slot2 = 1
					ELSE
						cas4_audio_slot1 = 1
						cas4_audio_slot2 = 2
					ENDIF
					cas4_audio_counter = 0
					cas4_audio_playing = 0
				ELSE
					IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
						IF cas4_audio_counter < 110
							cas4_ahead_counter = cas4_audio_counter + 1
							LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		
		SWITCH cas4_mobile
				CASE 0
					IF cas4_audio_playing = 0
						
						START_CHAR_FACIAL_TALK scplayer	 3000
						cas4_audio_counter = 94// CAS4_RA Shit here come those snake farmers
						cas4_mobile = 1
						GET_GAME_TIMER cas4_text_timer_start
					ENDIF
					BREAK
				CASE 1
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 1000
						IF cas4_audio_playing = 0
							
							START_CHAR_FACIAL_TALK scplayer	 3000
							cas4_audio_counter = 95//CAS4_RB Right I've had enough	
							cas4_mobile = 2
							GET_GAME_TIMER cas4_text_timer_start
						ENDIF
					ENDIF	
					BREAK								
				DEFAULT
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 1000
						IF cas4_audio_playing = 0
						   IF cas4_temp_flag = 1
								IF cas4_temp_flag2 = 1
									IF cas4_temp_flag3 = 1										
										cas4_text_loop_done = 1
									ENDIF
								ENDIF
							ENDIF
						   	
						ENDIF
					ENDIF
					BREAK
			ENDSWITCH
			
			
			GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  cas4_task_status
			IF cas4_task_status = FINISHED_TASK
				cas4_temp_flag = 1
			ENDIF

			IF cas4_temp_flag2 = 0
				IF NOT IS_CAR_DEAD cas4_dune_car
					GET_DRIVER_OF_CAR cas4_dune_car cas4_temp_driver
					IF NOT cas4_temp_driver = -1
						IF LOCATE_CAR_3D cas4_dune_car 2154.2747 1664.9645 9.8125 4.0 4.0 3.0 FALSE
							SET_CAR_MISSION  cas4_dune_car MISSION_STOP_FOREVER	
							TASK_EVERYONE_LEAVE_CAR cas4_dune_car
							cas4_temp_flag2 = 1
						ENDIF
					ELSE
						cas4_temp_flag2 = 1
					ENDIF
				ELSE
					cas4_temp_flag2 = 1
				ENDIF
			ENDIF

			IF cas4_temp_flag3 = 0
				IF NOT IS_CAR_DEAD cas4_quad_car
					GET_DRIVER_OF_CAR cas4_quad_car cas4_temp_driver
					IF NOT cas4_temp_driver = -1
						IF LOCATE_CAR_3D cas4_quad_car 2158.5183 1681.4022 9.8047 4.0 4.0 3.0 FALSE
							SET_CAR_MISSION  cas4_quad_car MISSION_STOP_FOREVER	  //MISSION_EMERGENCYVEHICLE_STOP	
							TASK_EVERYONE_LEAVE_CAR cas4_quad_car
							cas4_temp_flag3 = 1
						ENDIF
					ELSE
						cas4_temp_flag = 1
					ENDIF
				ELSE
					cas4_temp_flag3 = 1
				ENDIF
			ENDIF

			
			
			IF TIMERB > 20000
				cas4_text_loop_done = 1
			ENDIF
			
			GOTO casino4_text_loop4

	ENDIF


	///////////////////////////////// audio has to be finished by this point  //////////////////////////////////////



	//////////////////////////////////////////////////
	// do a camera switch and set everyone's coords //
	//////////////////////////////////////////////////
	
	
	MARK_CAR_AS_NO_LONGER_NEEDED cas4_quad_car
	MARK_CAR_AS_NO_LONGER_NEEDED cas4_dune_car
	MARK_MODEL_AS_NO_LONGER_NEEDED WALTON 

	WHILE NOT HAS_ANIMATION_LOADED GANGS
	OR NOT HAS_ANIMATION_LOADED MISC
		REQUEST_ANIMATION GANGS
		REQUEST_ANIMATION MISC
	WAIT 0 
	ENDWHILE

	 /*
	 DO_FADE 1000 FADE_OUT
	 WHILE GET_FADING_STATUS
	 WAIT 0
	 ENDWHILE  */

	
	 
	
	CLEAR_AREA 2175.21 1679.37 9.85  50.0 TRUE
	 

	// warp player to fight coords
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		IF IS_CHAR_IN_ANY_CAR scplayer
			WARP_CHAR_FROM_CAR_TO_COORD	scplayer 2171.0 1671.0  9.8125
		ELSE
			SET_CHAR_COORDINATES scplayer 2171.0 1671.0  9.8125
		ENDIF
		SET_CHAR_HEADING scplayer 109.2628 
		
	IF NOT IS_CHAR_DEAD cas4_paul_ped
		SET_CHAR_DECISION_MAKER cas4_paul_ped cas4_decision		
	   
		cas4_paul_with_player = 0
		CLEAR_CHAR_TASKS_IMMEDIATELY cas4_paul_ped
		IF IS_CHAR_IN_ANY_CAR  cas4_paul_ped
			WARP_CHAR_FROM_CAR_TO_COORD	 cas4_paul_ped 2170.0 1673.0 9.8125
		ELSE
			SET_CHAR_COORDINATES cas4_paul_ped 2170.0 1673.0 9.8125
		ENDIF
		SET_CHAR_HEADING cas4_paul_ped 153.2628
		
	ENDIF

	IF NOT IS_CHAR_DEAD cas4_macca_ped
		SET_CHAR_DECISION_MAKER cas4_macca_ped cas4_decision2		
		cas4_macca_with_player = 0
		CLEAR_CHAR_TASKS_IMMEDIATELY cas4_macca_ped
		IF IS_CHAR_IN_ANY_CAR  cas4_macca_ped
		    WARP_CHAR_FROM_CAR_TO_COORD	cas4_macca_ped 2172.0 1669.0 9.8125
		ELSE
			SET_CHAR_COORDINATES cas4_macca_ped 2172.0 1669.0 9.8125
		ENDIF
		SET_CHAR_HEADING cas4_macca_ped 133.2628
		
	ENDIF
	

	
	GET_CHAR_COORDINATES scplayer cas4_playerx cas4_playery cas4_playerz	

	

	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[0]
		IF IS_CHAR_IN_ANY_CAR	cas4_temp_hill_billy[0] 
			WARP_CHAR_FROM_CAR_TO_COORD cas4_temp_hill_billy[0]	2163.4297 1660.7207 9.8203
		ELSE
			SET_CHAR_COORDINATES cas4_temp_hill_billy[0] 2163.4297 1660.7207 9.8203
		ENDIF
		SET_CHAR_HEADING cas4_temp_hill_billy[0] 327.8448
		OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]									   	
	   	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
	   	TASK_PLAY_ANIM -1  Scratchballs_01  MISC 4.0 TRUE FALSE FALSE FALSE 	-1
	   	CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
	   	PERFORM_SEQUENCE_TASK cas4_temp_hill_billy[0] cas4_hill_billy_seq[0]
		CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
	ENDIF

	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[1]
		IF IS_CHAR_IN_ANY_CAR	cas4_temp_hill_billy[1] 
			WARP_CHAR_FROM_CAR_TO_COORD cas4_temp_hill_billy[1]	2162.6914 1664.2230 9.8203
		ELSE
			SET_CHAR_COORDINATES cas4_temp_hill_billy[1] 2162.6914 1664.2230 9.8203
		ENDIF								  
		SET_CHAR_HEADING cas4_temp_hill_billy[1] 355.144
		OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]									   	
	   	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
	   	TASK_PLAY_ANIM -1  Scratchballs_01  MISC 4.0 TRUE FALSE FALSE FALSE 	-1
	   	CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
	   	PERFORM_SEQUENCE_TASK cas4_temp_hill_billy[1] cas4_hill_billy_seq[0]
		CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
		
	ENDIF

	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[2]
		IF IS_CHAR_IN_ANY_CAR	cas4_temp_hill_billy[2] 
			WARP_CHAR_FROM_CAR_TO_COORD cas4_temp_hill_billy[2] 2166.5659 1661.4977 9.8203
		ELSE
			SET_CHAR_COORDINATES cas4_temp_hill_billy[2] 2166.5659 1661.4977 9.8203
		ENDIF
		SET_CHAR_HEADING cas4_temp_hill_billy[2] 343.0
		OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]									   	
	   	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
	   	TASK_PLAY_ANIM -1  Scratchballs_01  MISC 4.0 TRUE FALSE FALSE FALSE 	-1
	   	CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
	   	PERFORM_SEQUENCE_TASK cas4_temp_hill_billy[2] cas4_hill_billy_seq[0]
		CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]		
	ENDIF

	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[3]
		IF IS_CHAR_IN_ANY_CAR	cas4_temp_hill_billy[3] 
			WARP_CHAR_FROM_CAR_TO_COORD cas4_temp_hill_billy[3]	2160.9082 1664.8752 9.8047
		ELSE
			SET_CHAR_COORDINATES cas4_temp_hill_billy[3] 2160.9082 1664.8752 9.8047
		ENDIF
		SET_CHAR_HEADING cas4_temp_hill_billy[3] 315.7
		OPEN_SEQUENCE_TASK cas4_hill_billy_seq[0]									   	
	   	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
	   	TASK_PLAY_ANIM -1  Scratchballs_01  MISC 4.0 TRUE FALSE FALSE FALSE 	-1
	   	CLOSE_SEQUENCE_TASK cas4_hill_billy_seq[0]
	   	PERFORM_SEQUENCE_TASK cas4_temp_hill_billy[3] cas4_hill_billy_seq[0]
		CLEAR_SEQUENCE_TASK cas4_hill_billy_seq[0]
	ENDIF

	

	SET_FIXED_CAMERA_POSITION  2171.65 1673.89 11.12 0.0 0.0 0.0 
	POINT_CAMERA_AT_POINT 2158.40 1649.68 11.08 JUMP_CUT
	
	MAKE_PLAYER_GANG_DISAPPEAR
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	cas4_audio_counter 		= 0
	cas4_audio_slot1   		= 1
	cas4_audio_slot2   		= 2
	cas4_audio_playing 		= 0
	cas4_text_loop_done		= 0
	cas4_mobile				= 0
	cas4_text_timer_diff	= 0 
	cas4_text_timer_end 	= 0
	cas4_text_timer_start 	= 0
	cas4_ahead_counter	  	= 0

	SKIP_CUTSCENE_START

	casino4_text_loop3:
	WAIT 0 

	IF cas4_text_loop_done = 0
		
		
		IF NOT cas4_audio_counter = 0
			IF cas4_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
					CLEAR_MISSION_AUDIO cas4_audio_slot2
				ENDIF
				cas4_audio_playing = 1
			ENDIF

			IF cas4_audio_playing = 1
				LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
				cas4_audio_playing = 2
			ENDIF

			IF cas4_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
					PLAY_MISSION_AUDIO cas4_audio_slot1
					PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
					cas4_audio_playing = 3
				ENDIF
			ENDIF

			IF cas4_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
					CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
					IF cas4_audio_slot1 = 1
						cas4_audio_slot1 = 2
						cas4_audio_slot2 = 1
					ELSE
						cas4_audio_slot1 = 1
						cas4_audio_slot2 = 2
					ENDIF
					cas4_audio_counter = 0
					cas4_audio_playing = 0
				ELSE
					IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
						IF cas4_audio_counter < 110
							cas4_ahead_counter = cas4_audio_counter + 1
							LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	 	SWITCH cas4_mobile
				CASE 0
					IF cas4_audio_playing = 0
						//cas4_audio_counter = 96//CAS4_RC Let's finish this	 	
						cas4_mobile = 1
						IF NOT IS_CHAR_DEAD cas4_paul_ped
							//TASK_PLAY_ANIM cas4_paul_ped  prtial_gngtlkF GANGS 4.0 FALSE FALSE FALSE FALSE 	-1
							IF NOT IS_CHAR_DEAD cas4_macca_ped
								TASK_LOOK_AT_CHAR cas4_macca_ped cas4_paul_ped 10000
							ENDIF
							TASK_LOOK_AT_CHAR scplayer cas4_paul_ped  10000
						ENDIF
						
						
						//TASK_PLAY_ANIM scplayer  prtial_gngtlkA GANGS 4.0 FALSE FALSE FALSE FALSE 	-1
						
						IF NOT IS_CHAR_DEAD cas4_macca_ped
							//TASK_SCRATCH_HEAD cas4_macca_ped	
						ENDIF
						//GET_GAME_TIMER cas4_text_timer_start
					ENDIF
					BREAK
				CASE 1
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 1000
						IF cas4_audio_playing = 0
							
							IF NOT IS_CHAR_DEAD cas4_macca_ped
								START_CHAR_FACIAL_TALK cas4_macca_ped 3000
							ENDIF
							cas4_audio_counter = 97 // CAS4_RD I'm going to punch some big tits
							cas4_mobile = 2																					
							cas4_counter2 = 0
							WHILE cas4_counter2 < 4
								IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter2]
									TASK_GO_STRAIGHT_TO_COORD cas4_temp_hill_billy[cas4_counter2] 2167.9988 1666.1212 9.8203 PEDMOVE_WALK -1
								ENDIF
								cas4_counter2++
							ENDWHILE
							IF NOT IS_CHAR_DEAD cas4_paul_ped 
								//TASK_PLAY_ANIM cas4_paul_ped  prtial_gngtlkC GANGS 4.0 FALSE FALSE FALSE FALSE 	-1
							ENDIF

							IF NOT IS_CHAR_DEAD cas4_macca_ped
								//TASK_PLAY_ANIM cas4_macca_ped  prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE 	-1	
							ENDIF

							//TASK_PLAY_ANIM scplayer plyr_shkhead MISC 4.0 FALSE FALSE FALSE FALSE 	-1

							GET_GAME_TIMER cas4_text_timer_start
						ENDIF
					ENDIF	
					BREAK
				CASE 2
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 1000
						IF cas4_audio_playing = 0
							IF NOT IS_CHAR_DEAD cas4_macca_ped
								STOP_CHAR_FACIAL_TALK cas4_macca_ped 
							ENDIF
							IF NOT IS_CHAR_DEAD cas4_paul_ped
								START_CHAR_FACIAL_TALK cas4_paul_ped 3000
							ENDIF
							cas4_audio_counter = 98 // CAS4_RE Fuck it,in for a penny...
							cas4_mobile = 3
							GET_GAME_TIMER cas4_text_timer_start
						ENDIF
					ENDIF
					BREAK								
				DEFAULT
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 1000
						IF cas4_audio_playing = 0
						   IF NOT IS_CHAR_DEAD cas4_paul_ped
								STOP_CHAR_FACIAL_TALK cas4_paul_ped 
							ENDIF
						   cas4_text_loop_done = 1	
						ENDIF
					ENDIF
					BREAK
			ENDSWITCH
		
		
		GOTO casino4_text_loop3

	ENDIF
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////	
													
	SKIP_CUTSCENE_END

	MAKE_PLAYER_GANG_REAPPEAR

	IF NOT IS_CHAR_DEAD cas4_macca_ped
		STOP_CHAR_FACIAL_TALK cas4_macca_ped 
	ENDIF
	IF NOT IS_CHAR_DEAD cas4_paul_ped
		STOP_CHAR_FACIAL_TALK cas4_paul_ped 
	ENDIF
	CLEAR_MISSION_AUDIO cas4_audio_slot1
	CLEAR_MISSION_AUDIO cas4_audio_slot2 
	
	   	 
	/////////////////////////////////////////////////////
	////////////// FIGHT CODE FROM HERE ON //////////////
	/////////////////////////////////////////////////////


	 cas4_counter = 3
	 WHILE cas4_counter > -1
	 IF NOT IS_CHAR_DEAD cas4_macca_ped
	 	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter]
		 	CLEAR_LOOK_AT cas4_macca_ped
		 	TASK_KILL_CHAR_ON_FOOT cas4_macca_ped cas4_temp_hill_billy[cas4_counter]
		 	 cas4_macca_target = cas4_temp_hill_billy[cas4_counter]
		 	SET_CHAR_RELATIONSHIP cas4_macca_ped ACQUAINTANCE_TYPE_PED_HATE    PEDTYPE_MISSION2 // macca hate hillbill
		 	ADD_BLIP_FOR_CHAR cas4_macca_ped cas4_macca_fight_blip
			SET_BLIP_AS_FRIENDLY cas4_macca_fight_blip TRUE 
			CHANGE_BLIP_SCALE cas4_macca_fight_blip 2
			cas4_counter = -1
		ENDIF
	 ENDIF
	 cas4_counter--
	 ENDWHILE	 
	 			
	 		
	 	cas4_counter = 0
	 	WHILE cas4_counter < 4
		    IF NOT IS_CHAR_DEAD	cas4_paul_ped
		    	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter]
					   CLEAR_LOOK_AT cas4_paul_ped
					   TASK_KILL_CHAR_ON_FOOT cas4_paul_ped cas4_temp_hill_billy[cas4_counter] 
					   cas4_paul_target = cas4_temp_hill_billy[cas4_counter]
					   SET_CHAR_RELATIONSHIP cas4_paul_ped  ACQUAINTANCE_TYPE_PED_HATE    PEDTYPE_MISSION2 // paul hate hillbill	
					   ADD_BLIP_FOR_CHAR cas4_paul_ped cas4_paul_fight_blip
					   SET_BLIP_AS_FRIENDLY cas4_paul_fight_blip TRUE 
					   CHANGE_BLIP_SCALE cas4_paul_fight_blip 2
					   cas4_counter  = 4
				ENDIF
			ENDIF
		cas4_counter++
		ENDWHILE
			 		 		 	 	 	 	

	 cas4_counter = 0
	 WHILE cas4_counter < 4
	 IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter]
		 ADD_BLIP_FOR_CHAR cas4_temp_hill_billy[cas4_counter] cas4_hill_billy_blip[cas4_counter]
		 CHANGE_BLIP_DISPLAY cas4_hill_billy_blip[cas4_counter] BLIP_ONLY
		 CHANGE_BLIP_SCALE cas4_hill_billy_blip[cas4_counter] 2		 
		 SET_CHAR_RELATIONSHIP cas4_temp_hill_billy[cas4_counter] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_SPECIAL 
		 //SET_CHAR_RELATIONSHIP cas4_temp_hill_billy[cas4_counter] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION4
		 SET_CHAR_RELATIONSHIP cas4_temp_hill_billy[cas4_counter] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1	 	 	 	 
	 	 IF cas4_counter < 2
			IF NOT IS_CHAR_DEAD cas4_paul_ped
				TASK_KILL_CHAR_ON_FOOT cas4_temp_hill_billy[cas4_counter] cas4_paul_ped
			ENDIF
		 ELSE
			IF NOT IS_CHAR_DEAD cas4_macca_ped
				TASK_KILL_CHAR_ON_FOOT cas4_temp_hill_billy[cas4_counter] cas4_macca_ped
			ENDIF
		 ENDIF

	 ENDIF
	 cas4_counter++
	 ENDWHILE
	 
  	
						  
	 /// assign tasks
	 
	 
	 
	 RESTORE_CAMERA_JUMPCUT
	 SET_PED_DENSITY_MULTIPLIER 1.0
	 SET_CAR_DENSITY_MULTIPLIER 1.0
	 CLEAR_CHAR_TASKS scplayer
	 CLEAR_LOOK_AT scplayer
	 SET_PLAYER_CONTROL player1 ON
	 SWITCH_WIDESCREEN OFF
	 REMOVE_ANIMATION MISC
	 REMOVE_ANIMATION GANGS

	 cas4_audio_counter 		= 0
	cas4_audio_slot1   		= 1
	cas4_audio_slot2   		= 2
	cas4_audio_playing 		= 0
	cas4_text_loop_done		= 0
	cas4_mobile				= 0
	cas4_text_timer_diff	= 0 
	cas4_text_timer_end 	= 0
	cas4_text_timer_start 	= 0
	cas4_ahead_counter	  	= 0


	TIMERB = 0
	cas4_safety_flag = 0
	WHILE cas4_safety_flag = 0
		  // the fights going on

		   ///////////////
		   /// AUDIO ? ///
		   ///////////////

			IF NOT cas4_audio_counter = 0
				IF cas4_audio_playing = 0
					IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
						CLEAR_MISSION_AUDIO cas4_audio_slot2
					ENDIF
					cas4_audio_playing = 1
				ENDIF

				IF cas4_audio_playing = 1
					LOAD_MISSION_AUDIO cas4_audio_slot1 cas4_audio[cas4_audio_counter]
					cas4_audio_playing = 2
				ENDIF

				IF cas4_audio_playing = 2
				 	IF HAS_MISSION_AUDIO_LOADED cas4_audio_slot1
						PLAY_MISSION_AUDIO cas4_audio_slot1
						PRINT_NOW $cas4_text[cas4_audio_counter] 10000 1
						cas4_audio_playing = 3
					ENDIF
				ENDIF

				IF cas4_audio_playing = 3
					IF HAS_MISSION_AUDIO_FINISHED cas4_audio_slot1
						CLEAR_THIS_PRINT $cas4_text[cas4_audio_counter]
						IF NOT IS_CHAR_DEAD cas4_macca_ped
							STOP_CHAR_FACIAL_TALK cas4_macca_ped
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped FALSE
						ENDIF
						
						IF NOT IS_CHAR_DEAD cas4_paul_ped
							STOP_CHAR_FACIAL_TALK cas4_paul_ped
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped FALSE
						ENDIF
						
						
						IF cas4_audio_slot1 = 1
							cas4_audio_slot1 = 2
							cas4_audio_slot2 = 1
						ELSE
							cas4_audio_slot1 = 1
							cas4_audio_slot2 = 2
						ENDIF
						cas4_audio_counter = 0
						cas4_audio_playing = 0
					ELSE
						IF NOT HAS_MISSION_AUDIO_LOADED cas4_audio_slot2
							IF cas4_audio_counter < 110
								cas4_ahead_counter = cas4_audio_counter + 1
								LOAD_MISSION_AUDIO cas4_audio_slot2 cas4_audio[cas4_ahead_counter]
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		   


			SWITCH cas4_mobile
				CASE 0
					IF TIMERB > 5000
					IF cas4_audio_playing = 0																			
						IF NOT IS_CHAR_DEAD cas4_macca_ped						
					 	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped TRUE
					 	START_CHAR_FACIAL_TALK cas4_macca_ped	 3000
					 	ENDIF	
						cas4_audio_counter = 112 // = MACX_AG//	Yeah, that's how we do it in Salford (macca)
						cas4_mobile = 1
						GET_GAME_TIMER cas4_text_timer_start
					ENDIF
					ENDIF
					BREAK
				CASE 1
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 7000
						IF cas4_audio_playing = 0
							cas4_audio_counter = 17  // = CAS4_CJ//]Don't make me slap you, sunshine! (paul
							IF NOT IS_CHAR_DEAD cas4_paul_ped						
						 	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_paul_ped TRUE
						 	START_CHAR_FACIAL_TALK cas4_paul_ped	 3000
						 	ENDIF
							cas4_mobile = 2
							GET_GAME_TIMER cas4_text_timer_start
						ENDIF
					ENDIF
					BREAK
				CASE 2
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 7000
						IF cas4_audio_playing = 0
							IF NOT IS_CHAR_DEAD cas4_macca_ped						
						 	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH cas4_macca_ped TRUE
						 	START_CHAR_FACIAL_TALK cas4_macca_ped	 3000
						 	ENDIF
							cas4_audio_counter = 111 // = MACX_AF//	So now you know how it really is. (macca)
							cas4_mobile = 3
							GET_GAME_TIMER cas4_text_timer_start
						ENDIF
					ENDIF
					BREAK
																									
				DEFAULT
					GET_GAME_TIMER cas4_text_timer_end
					cas4_text_timer_diff = cas4_text_timer_end - cas4_text_timer_start
					IF cas4_text_timer_diff > 1000
						IF cas4_audio_playing = 0						   						   
						   cas4_text_loop_done = 1	
						ENDIF
					ENDIF
					BREAK
			ENDSWITCH









		   ///////////////////////////////
		   // UPDATE Paul Macca targets //
		   ///////////////////////////////
		   		   		   		   	
		   /*
		   IF TIMERB > 10000
		   AND TIMERB < 10500
		   		CLEAR_PRINTS
		   		PRINT (cas4_26) 3000 1 //I think I broke a nail
		   ELSE
		   		IF TIMERB > 20000
		   		AND TIMERB < 20500
		   			CLEAR_PRINTS
		   			PRINT (cas4_27) 3000 1 //I'm too pretty to fight
		   		ELSE
		   			IF TIMERB > 30000
				    AND TIMERB < 30500
		   				CLEAR_PRINTS
		   				PRINT (cas4_29) 3000 1 //What's that smell? 					
		   				PRINT (cas4_30) 3000 1 //Oh wait a minute it's me.	
					ELSE						
						PRINT (CAS4_34) 2000 1 //Protect Paul and Macca,
					ENDIF
					
				ENDIF
		   ENDIF
		   */
			
			
			cas4_counter = 0
			WHILE cas4_counter < 4	
				IF cas4_hill_billy_dead[cas4_counter] = 0
					IF IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter]					   
					   IF cas4_macca_target = cas4_temp_hill_billy[cas4_counter]
					   	  cas4_counter2 = 3
						 	WHILE cas4_counter2 > -1
						 	   IF NOT IS_CHAR_DEAD	cas4_macca_ped
							    	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter2]
										   TASK_KILL_CHAR_ON_FOOT cas4_macca_ped cas4_temp_hill_billy[cas4_counter2] 
										   cas4_macca_target = cas4_temp_hill_billy[cas4_counter2]										   
										   cas4_counter2  = -1
									ENDIF
								ENDIF
							cas4_counter2--
							ENDWHILE
					   
					   ENDIF

					   IF cas4_paul_target = cas4_temp_hill_billy[cas4_counter]
					   	  cas4_counter2 = 0
						 	WHILE cas4_counter2 < 4
							    IF NOT IS_CHAR_DEAD	cas4_paul_ped
							    	IF NOT IS_CHAR_DEAD cas4_temp_hill_billy[cas4_counter2]
										   TASK_KILL_CHAR_ON_FOOT cas4_paul_ped cas4_temp_hill_billy[cas4_counter2] 
										   cas4_paul_target = cas4_temp_hill_billy[cas4_counter2]										   
										   cas4_counter2  = 4
									ENDIF
								ENDIF
							cas4_counter2++
							ENDWHILE					   
					   ENDIF
					   REMOVE_BLIP cas4_hill_billy_blip[cas4_counter]
					   MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_hill_billy[cas4_counter]
					   cas4_hill_billy_dead[cas4_counter] = 1
					   cas4_hill_billies_alive--
					ENDIF	   
				ENDIF
			cas4_counter++	
			ENDWHILE	

			IF cas4_hill_billies_alive < 1
			   cas4_safety_flag = 1		   
			   IF cas4_blip_added_flag = 0
				  // ADD_BLIP_FOR_COORD  2175.21 1679.37 9.85 cas4_end_blip	    						   
				   MARK_CAR_AS_NO_LONGER_NEEDED cas4_player_car
				   CLEAR_AREA 2175.21 1679.37 9.85 10.0 TRUE
				   cas4_blip_added_flag = 1
			   ENDIF
			   //CLEAR_PRINTS
			   //PRINT ( CAS4_06 ) 8000 1 ///////////// " Get to the Mafia Casino "
			   // fight over
			ENDIF   	

			IF IS_CHAR_DEAD cas4_paul_ped
			OR IS_CHAR_DEAD cas4_macca_ped	 		 
		 		 CLEAR_PRINTS
		 		 cas4_safety_flag = 1
			ENDIF

	WAIT 0 
	ENDWHILE

	
	CLEAR_MISSION_AUDIO cas4_audio_slot2
	CLEAR_MISSION_AUDIO cas4_audio_slot1

	SET_WANTED_MULTIPLIER 1.0
	REMOVE_BLIP cas4_paul_fight_blip
	REMOVE_BLIP cas4_macca_fight_blip

	IF NOT IS_CHAR_DEAD cas4_paul_ped
	AND NOT IS_CHAR_DEAD cas4_macca_ped
		STOP_CHAR_FACIAL_TALK cas4_macca_ped
		STOP_CHAR_FACIAL_TALK cas4_paul_ped
		CLEAR_CHAR_TASKS_IMMEDIATELY cas4_paul_ped
		CLEAR_CHAR_TASKS_IMMEDIATELY cas4_macca_ped	
		SET_GROUP_DEFAULT_TASK_ALLOCATOR Players_Group DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS
	ENDIF


ENDIF  //hill_billies_alive > 0




RETURN










///////////////////
/// TEXT LABELS ///
///////////////////
/*
[CAS4_BA]My legs are fucked and my head's fuckin' pounding!
[CAS4_BB]We can't walk back, mate...
[CAS4_CA]Your turn in the boot, ol'lad!
[CAS4_CB]Oooo, this is comfy!
[CAS4_CC]I'm the manager, I get the front seat!
[CAS4_CD]Hey, that's my seat!
[CAS4_CE]Leave him, he'll be fine!
[CAS4_CF]We'll come back for you, I promise!
[CAS4_CG]Oh no you don't, I remember Preston Guild Hall!
[CAS4_CH]I'm the manager, what I say, goes!
[CAS4_CJ]Don't make me slap you, sunshine!
[CAS4_CK]Don't make me sack you!
[CAS4_CL]You're my manager this is all your fault!
[CAS4_DA]Where's the rest of the band, guys?
[CAS4_DB]Oh bollox! Maccer, where are the boys?
[CAS4_DC]I don't fuckin' know, do I.
[CAS4_DD]I remember snakes, lots of snakes...
[CAS4_DE]There's a snake farm not far from here, we can go check it out.
[CAS4_EA]I don't recognise this part of Manchester, our kid...
[CAS4_EB]How many times do I have to tell you, we're in America!
[CAS4_EC]America? Wait 'til I tell me ma about this!
[CAS4_ED]He's like a fucking stuck record. He'll ask about Las Venturas next.
[CAS4_EE]Las Venturas? Always wanted to go there, great tits.
[CAS4_EF]Don't start, not in this confined space.
[CAS4_EG]Bouncing, wobbling, creamy tits...
[CAS4_EH]LEAVE YOURSELF ALONE!
[CAS4_FA]Here we are, look familiar?
[CAS4_FB]Looks like Salford to me...
[CAS4_FC]What?
[CAS4_GA]Run like fuck!
[CAS4_GB]Everybody in the car!
[CAS4_HA]Wait, our kid, wait!
[CAS4_HB]I want to come too!
[CAS4_HC]Hey, wait for me, sunshine!
[CAS4_HD]Don't leave me in their clutches!
[CAS4_JA]I think we lost 'em.
[CAS4_JB]I can't see those Snake farmers any more!
[CAS4_JC]Shut up, you two!
[CAS4_JD]Quit squabbling!
[CAS4_KA]It seems you boys had a good time!
[CAS4_KB]What about the band?
[CAS4_KC]We'll just have to pray they've made it to civilization.
[CAS4_KD]Keyboardists and drummers are ten-a-penny anyway.
[CAS4_KE]I need a piss, mate!
[CAS4_KF]Oh fuck, we're screwed!
[CAS4_KG]Can't it wait?
[CAS4_KH]I'm fit to burst, ol'lad!
[CAS4_KJ]You've got to pull over, he's priapic, it'll go everywhere!
[CAS4_LA]I can't hold it any longer!
[CAS4_LB]Oh fuck!
[CAS4_LC]Hey, what the fuck?
[CAS4_LD]Argh! Stop it!
[CAS4_LE]Point it out the fucking window!
[CAS4_MA]Oh, fuck, that is goooood.
[CAS4_NA]Oh fuck, I've got the shakes!
[CAS4_NB]You look as pale as a drowned baby, mate!
[CAS4_NC]Oh god, I think I'm going to chuck!
[CAS4_ND]What you need is some food down you!
[CAS4_NE](Paul nearly vomits)
[CAS4_NF]A fried egg sanger with mayonaise will sort you out.
[CAS4_NG](Paul nearly vomits again)
[CAS4_NH]Or a pickled egg!
[CAS4_NJ]Pull over, NOW!
[CAS4_OA]Shhi- huuuurghhh -hit!
[CAS4_OB]I've go- huuurrrgghh!
[CAS4_OC]Got the- Hhhuurrgghh!
[CAS4_OD]Dry- hurgh!
[CAS4_OE]Dry- hurgh!
[CAS4_OF]Dry heaves!
[CAS4_OG]Oh, I thuh- think it's past...
[CAS4_OH]Or how about a pint of cod liver oil, that would sort your guts out!
[CAS4_OJ](Paul vomiting violently)
[CAS4_OK]Leave him, man.
[CAS4_OL]The good doctor is just trying to help!
[CAS4_OM]Fuh-huh-Fuck off!
[CAS4_PA]What kind of tits does this Rosie have?
[CAS4_PB]Big, floppy sausage tits?
[CAS4_PC]Empty saddlebags?
[CAS4_PD]Bee stings?
[CAS4_PE]Rosie is a man!
[CAS4_PF]And stop touching yourself!
[CAS4_PG]It's just for comfort, this is a stressful situation.
[CAS4_PH]You're fucking telling me it is!
[CAS4_PJ]Can it you two!
[CAS4_PK]He started it!
[CAS4_QA]Where is this casino?
[CAS4_QB]It's called Caligula's, it's on the strip.
[CAS4_RA]Shit, here come those snake farmers!
[CAS4_RB]Right, I've had enough.
[CAS4_RC]Let's finish this!
[CAS4_RD]I'm going to punch some big tits!
[CAS4_RE]Fuck it, in for a penny...
[CAS4_SA]Come on, let's go in and see Rosie.
[CAS4_TA]Yo, Woozie, just a quick call to say I think I've found a way 
[CAS4_TB]to scope Caligula's Casino without causing too much suspicion.
[CAS4_TC]We can talk later.
*/




// **************************************** Mission casino4 failed ***********************

mission_casino4_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   





// **************************************** mission casino4 passed ************************

mission_casino4_passed:

flag_casino_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
SET_INT_STAT PASSED_CASINO4 1
REGISTER_MISSION_PASSED ( CASINO4 ) //Used in the stats 
//PRINT_WITH_NUMBER_BIG ( M_PASS ) 100 5000 1 //"Mission Passed!"
//ADD_SCORE player1 100
//REMOVE_BLIP casino_contact_blip
//ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip

PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 1
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect
CLEAR_WANTED_LEVEL player1

PLAYER_MADE_PROGRESS 1
REMOVE_BLIP casino_contact_blip
 


//CLEAR_WANTED_LEVEL player1
RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_casino4:

GET_GAME_TIMER timer_mobile_start
SET_POLICE_IGNORE_PLAYER player1 OFF


IF IS_PLAYER_PLAYING player1			   
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
ENDIF

KILL_FX_SYSTEM cas4_vomit
KILL_FX_SYSTEM cas4_piss

SET_PED_DENSITY_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0

/////////////////////////    REMOVE_BLIPS
REMOVE_BLIP cas4_end_blip
REMOVE_BLIP cas4_macca_blip
REMOVE_BLIP cas4_paul_blip
REMOVE_BLIP cas4_snake_blip
REMOVE_BLIP cas4_desert_blip

REMOVE_BLIP cas4_hill_billy_blip[0]
REMOVE_BLIP cas4_hill_billy_blip[1]
REMOVE_BLIP cas4_hill_billy_blip[2]
REMOVE_BLIP cas4_hill_billy_blip[3]

REMOVE_BLIP cas4_macca_fight_blip
REMOVE_BLIP cas4_paul_fight_blip

//////////////////////// REMOVE_GROUP
 
 
/////////////////////// CLEAR_ONSCREEN_COUNTER

//////////////////////  CLEAR_ONSCREEN_TIMER


/////////////////////  MARK MODELs AS NO LONGER NEEDED 
MARK_CAR_AS_NO_LONGER_NEEDED cas4_player_car
MARK_CAR_AS_NO_LONGER_NEEDED cas4_quad_car
MARK_CAR_AS_NO_LONGER_NEEDED cas4_dune_car
MARK_CAR_AS_NO_LONGER_NEEDED cas4_temp_car

REMOVE_CHAR_ELEGANTLY  cas4_paul_ped
REMOVE_CHAR_ELEGANTLY  cas4_macca_ped
 
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_old_ped
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_sister_ped
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_pig_ped
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_incest_ped
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_driver
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_hill_billy[0]
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_hill_billy[1]
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_hill_billy[2]
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_hill_billy[3]
MARK_CHAR_AS_NO_LONGER_NEEDED cas4_temp_ped

//MARK_MODEL_AS_NO_LONGER_NEEDED PAUL

//MARK_MODEL_AS_NO_LONGER_NEEDED MACCER

MARK_MODEL_AS_NO_LONGER_NEEDED WALTON

MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE


MARK_MODEL_AS_NO_LONGER_NEEDED DWFOLC
MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC1
MARK_MODEL_AS_NO_LONGER_NEEDED DWMOLC2
MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1
MARK_MODEL_AS_NO_LONGER_NEEDED DWMYLC1
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45

REMOVE_ANIMATION SUNBATHE
REMOVE_ANIMATION PAULNMAC
REMOVE_ANIMATION FOOD
REMOVE_ANIMATION SMOKING
REMOVE_ANIMATION MISC
REMOVE_ANIMATION GANGS

REMOVE_DECISION_MAKER cas4_decision
REMOVE_DECISION_MAKER cas4_decision2
REMOVE_DECISION_MAKER cas4_decision3
/////////////////////////////////////

UNLOAD_SPECIAL_CHARACTER 1
UNLOAD_SPECIAL_CHARACTER 2

flag_player_on_mission = 0
GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
MISSION_HAS_FINISHED
//UNLOAD_SPECIAL_CHARACTER 1
RETURN

 
}