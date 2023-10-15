MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* GUN 1 *********************************************
// ********************************* Introduction to burglary ******************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************


{
SCRIPT_NAME GUNS1

// Mission start stuff

GOSUB mission_start_g1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_g1_failed
ENDIF

GOSUB mission_cleanup_g1

MISSION_END
 
// Variables for mission

// **************************************** Mission Start **********************************


// *****************************
// VARIABLES
// *****************************

// NORMAL VARIABLES

LVAR_INT ryd1_counter
LVAR_INT ryd1_number_gunbox_truck
LVAR_INT ryd1_gunbox_player_is_carrying
LVAR_INT ryd1_area_name
LVAR_INT ryd1_task_status
LVAR_INT ryd1_decision ryd1_decision2  ryd1_decision3  ryd1_group_decision
LVAR_INT ryd1_message_displayed ryd1_message_on_screen
LVAR_INT ryd1_noise_difference ryd1_previous_noise
LVAR_INT ryd1_boxes_left
LVAR_INT ryd1_boxes_player_needs
LVAR_INT ryd1_temp_int
LVAR_INT ryd1_reward		  		    


// FLAGS

LVAR_INT ryd1_mission_progression_flag
LVAR_INT ryd1_safety_flag
LVAR_INT ryd1_ryder_created_flag
LVAR_INT ryd1_player_in_van_flag
LVAR_INT ryd1_truck_blip_added_flag
LVAR_INT ryd1_house_blip_added_flag
LVAR_INT ryd1_ryder_blip_added_flag
LVAR_INT ryd1_ryder_told_enter_car_flag
LVAR_INT ryd1_ryder_in_van_flag

LVAR_INT ryd1_player_holding_box_flag
LVAR_INT ryd1_gunbox_picked_up_flag[7]
LVAR_INT ryd1_home_blip_added_flag
LVAR_INT ryd1_spray_blip_added_flag
LVAR_INT ryd1_cop_task_assigned
LVAR_INT ryd1_cops_attacking_flag
LVAR_INT ryd1_ryder_box_deleted_flag 
LVAR_INT ryd1_locked_car_flag 
LVAR_INT ryd1_player_in_spray_shop_flag
LVAR_INT ryd1_help_displayed ryd1_near_box
LVAR_INT ryd1_at_night_flag
LVAR_INT ryd1_spray_message_played
LVAR_INT ryd1_allowed_to_check_sound
LVAR_INT ryd1_timer_on_screen
LVAR_INT ryd1_box_blip_added[7]
LVAR_INT ryd1_clothes_changed
LVAR_INT ryd1_truck_message_displayed ryd1_box_help_been_shown 	ryd1_crate_message_shown
LVAR_INT ryd1_dark_active
LVAR_INT ryd1_restless_scene_played
 
LVAR_INT ryd1_noise_bar_added_flag
LVAR_INT ryd1_inside_house_flag
LVAR_INT ryd1_owner_awake_flag
LVAR_INT ryd1_able_to_leave_flag

// CHARS
LVAR_INT ryd1_ryder_ped
LVAR_INT ryd1_owner_ped
LVAR_INT ryd1_cop1_ped
LVAR_INT ryd1_cop2_ped

// VEHICLES
LVAR_INT ryd1_truck_car
LVAR_INT ryd1_cop_car

// BLIPS
LVAR_INT ryd1_truck_blip
LVAR_INT ryd1_house_blip
LVAR_INT ryd1_spray_blip
LVAR_INT ryd1_home_blip
LVAR_INT ryd1_ryder_blip
LVAR_INT ryd1_door_blip
LVAR_INT ryd1_gunbox_blip[7]

// OBJECTS
LVAR_INT ryd1_gunbox_obj[7]

// SEQUENCES
LVAR_INT ryd1_player_house_seq
LVAR_INT ryd1_ryder_house_seq
LVAR_INT ryd1_player_door_seq
LVAR_INT ryd1_owner_awake_seq
LVAR_INT ryd1_ryder_leave_seq
LVAR_INT ryd1_player_leave_seq
LVAR_INT ryd1_cop1_seq
LVAR_INT ryd1_cop2_seq

// COORDS
LVAR_FLOAT ryd1_gunbox_x[7]  ryd1_gunbox_y[7] ryd1_gunbox_z[7]  
LVAR_FLOAT ryd1_truck_heading  
LVAR_FLOAT ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z ryd1_driver_x ryd1_driver_y ryd1_driver_z
LVAR_FLOAT ryd1_player_x ryd1_player_y ryd1_player_z
LVAR_FLOAT ryd1_temp_x ryd1_temp_y ryd1_temp_z

// TIMERS
VAR_INT ryd1_time_to_daylight

//STEALTH STUFF 

VAR_INT ryd1_noise_bar 

LVAR_FLOAT ryd1_noise_float ryd1_noise_total 

// extra guff
LVAR_INT ryd1_timer_current ryd1_timer_start ryd1_time_elapsed ryd1_timer_started

///// AUDIO CRAP

VAR_TEXT_LABEL ryd1_text[82]
LVAR_INT ryd1_audio[82]
LVAR_INT ryd1_audio_counter
LVAR_INT ryd1_audio_slot1 ryd1_audio_slot2 ryd1_audio_playing
LVAR_INT ryd1_text_loop_done
LVAR_INT ryd1_mobile
LVAR_INT ryd1_text_timer_diff 
LVAR_INT ryd1_text_timer_end 
LVAR_INT ryd1_text_timer_start
LVAR_INT ryd1_ahead_counter

LVAR_INT ryd1_track_playing
LVAR_INT ryd1_flee_audio_counter  ryd1_flee_audio_played
LVAR_INT ryd1_safe_audio_counter  ryd1_safe_audio_played
LVAR_INT ryd1_lockup_audio_counter ryd1_lockup_audio_played
LVAR_INT ryd1_wanted_audio_counter	ryd1_wanted_audio_played

LVAR_INT ryd1_sfx[4]
LVAR_INT ryd1_sfx_counter 
LVAR_INT ryd1_sfx_playing
LVAR_INT ryd1_sfx_played


mission_start_g1:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

/*
GET_TIME_OF_DAY Hours Minutes
The game uses a 24 hours clock for the time of day.
It could be important for the script to know what time
it is (to trigger time related missions / events). The hours (0-23)
are placed in the Hours variable. A similar thing happens to the 
minutes (0-59)
SET_TIME_OF_DAY Hours Minutes
*/

WAIT 0

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

FORCE_WEATHER WEATHER_SUNNY_LA

IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
	GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
	BUILD_PLAYER_MODEL player1
ENDIF

LOAD_MISSION_TEXT RYDER1
LOAD_CUTSCENE RYDER1A
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE

CLEAR_AREA 2464.0 -1714.0 14.0 100.0 TRUE 
START_CUTSCENE

DO_FADE 1000 FADE_IN
WHILE NOT HAS_CUTSCENE_FINISHED
WAIT 0
ENDWHILE 

DO_FADE 0 FADE_OUT
WHILE GET_FADING_STATUS
WAIT 0
ENDWHILE

RELEASE_WEATHER

CLEAR_CUTSCENE
SET_PLAYER_CONTROL player1 OFF


// ******************************************** REQUEST MODELS **********************************
			   
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH ryd1_decision
COPY_CHAR_DECISION_MAKER ryd1_decision  ryd1_decision3
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY ryd1_decision2
CHANGE_GARAGE_TYPE burg_lk GARAGE_FOR_SCRIPT_TO_OPEN_AND_CLOSE


LOAD_GROUP_DECISION_MAKER  DM_GROUP_MISSION_NORM ryd1_group_decision
SET_GROUP_DECISION_MAKER Players_Group ryd1_group_decision		

CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE ryd1_group_decision EVENT_LEADER_ENTERED_CAR_AS_DRIVER  
CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE ryd1_group_decision EVENT_LEADER_EXITED_CAR_AS_DRIVER

	
// Flags

ryd1_mission_progression_flag			= 0
ryd1_safety_flag						= 0
ryd1_ryder_created_flag 				= 0
ryd1_player_in_van_flag					= 0
ryd1_truck_blip_added_flag				= 0
ryd1_house_blip_added_flag				= 0
ryd1_ryder_told_enter_car_flag          = 0
ryd1_ryder_in_van_flag					= 0
ryd1_number_gunbox_truck    			= 0
ryd1_player_holding_box_flag			= 0
ryd1_gunbox_picked_up_flag[0]			= 0
ryd1_gunbox_picked_up_flag[1]			= 0
ryd1_gunbox_picked_up_flag[2]			= 0
ryd1_gunbox_picked_up_flag[3]			= 0
ryd1_gunbox_picked_up_flag[4]			= 0
ryd1_gunbox_picked_up_flag[5]			= 0
ryd1_gunbox_picked_up_flag[6]			= 0
ryd1_counter							= 0
ryd1_noise_bar_added_flag 				= 0
ryd1_noise_bar							= 0
ryd1_owner_awake_flag					= 0
ryd1_home_blip_added_flag				= 0 
ryd1_spray_blip_added_flag	 			= 0
ryd1_gunbox_player_is_carrying			= 0
ryd1_task_status                        = 0
ryd1_cop_task_assigned                  = 0
ryd1_cops_attacking_flag				= 0
ryd1_ryder_box_deleted_flag				= 0
ryd1_locked_car_flag 					= 0
ryd1_player_in_spray_shop_flag			= 0
ryd1_help_displayed						= 0
ryd1_near_box							= 0
ryd1_message_displayed 					= 0
ryd1_message_on_screen 					= 0
ryd1_previous_noise						= 0
ryd1_at_night_flag						= 0
ryd1_inside_house_flag					= 0
ryd1_noise_total						= 0.0
ryd1_noise_float					    = 0.0
ryd1_boxes_left							= 0
ryd1_boxes_player_needs					= 2
ryd1_reward								= 0
ryd1_spray_message_played				= 0
ryd1_clothes_changed					= 0
ryd1_truck_message_displayed 			= 0
ryd1_box_help_been_shown 				= 0
ryd1_crate_message_shown				= 0
ryd1_dark_active						= 0
ryd1_restless_scene_played				= 0
ryd1_able_to_leave_flag					= 0 
ryd1_allowed_to_check_sound				= 0
ryd1_timer_on_screen					= 0
ryd1_time_to_daylight					= 0 

// gun box locations
ryd1_gunbox_x[0]						= 2809.0
ryd1_gunbox_y[0]						= -1168.0
ryd1_gunbox_z[0]						=  1024.5720

ryd1_gunbox_x[1] =  2820.0332 
ryd1_gunbox_y[1] =  -1165.8358 
ryd1_gunbox_z[1] =  1024.6959 
 
ryd1_gunbox_x[2] =  2819.5398 
ryd1_gunbox_y[2] =  -1170.5558 
ryd1_gunbox_z[2] =  1028.2973 

ryd1_gunbox_x[3] =  2817.0205 
ryd1_gunbox_y[3] =  -1165.2019 
ryd1_gunbox_z[3] =  1028.3053 
  
ryd1_gunbox_x[4] =  2812.6562 
ryd1_gunbox_y[4] =  -1168.5800 
ryd1_gunbox_z[4] =  1028.2965 
  
ryd1_gunbox_x[5] =  2812.6113 
ryd1_gunbox_y[5] =  -1172.7316 
ryd1_gunbox_z[5] =  1024.6947 
  
ryd1_gunbox_x[6] =  2803.6802 
ryd1_gunbox_y[6] =  -1161.3123 
ryd1_gunbox_z[6] =  1028.3053 

ryd1_box_blip_added[0]	= 0
ryd1_box_blip_added[1]	= 0
ryd1_box_blip_added[2]	= 0
ryd1_box_blip_added[3]	= 0
ryd1_box_blip_added[4]	= 0
ryd1_box_blip_added[5]	= 0
ryd1_box_blip_added[6]	= 0

ryd1_timer_started = 0

IF ryd1_safety_flag = 999999
ADD_BLIP_FOR_COORD 0.0 0.0 0.0 ryd1_ryder_blip
ENDIF 


//////////////////////////////////////////
///////////// AUDIO CRAP /////////////////
//////////////////////////////////////////

ryd1_audio_counter	   = 0
ryd1_audio_slot1 	   = 1
ryd1_audio_slot2 	   = 2
ryd1_audio_playing	   = 0
ryd1_text_loop_done	   = 0
ryd1_mobile			   = 0
ryd1_text_timer_diff   = 0
ryd1_text_timer_end    = 0
ryd1_text_timer_start  = 0
ryd1_ahead_counter	   = 0

ryd1_track_playing		 = 0
ryd1_flee_audio_counter  = 0
ryd1_flee_audio_played	 = 0
ryd1_safe_audio_counter  = 0
ryd1_safe_audio_played	 =	0
ryd1_lockup_audio_counter =	0
ryd1_lockup_audio_played  =	0
ryd1_wanted_audio_counter =	0
ryd1_wanted_audio_played  =	0

$ryd1_text[1] = RYD1_AA//Cuz, it's already night, your head's waterlogged!
$ryd1_text[2] = RYD1_AB//Where is this old motherfucker? Where in hell is he?
$ryd1_text[3] = RYD1_AC//Chill out. We ain't there yet.
$ryd1_text[4] = RYD1_AD//Yeah, right, Carl. You always right. 
$ryd1_text[5] = RYD1_AE//That's my homie. Mister right.
$ryd1_text[6] = RYD1_AF//Shut up.
$ryd1_text[7] = RYD1_AG//You can't stop me.
$ryd1_text[8] = RYD1_AH//Who can't?
$ryd1_text[9] = RYD1_AJ//Whatever.
$ryd1_text[10] = RYD1_BA//Let's storm the place.
$ryd1_text[11] = RYD1_BB//Hold up. Let's sneak in, grab what we can and sneak out.
$ryd1_text[12] = RYD1_BC//Ninja style?...OK.
$ryd1_text[13] = RYD1_BD//Come out you old bastard!
$ryd1_text[14] = RYD1_BE//Shut up!
$ryd1_text[15] = RYD1_BF//He can't stop me!
$ryd1_text[16] = RYD1_CA//Ok, Cuz, in you go, I'll keep watch.
$ryd1_text[17] = RYD1_CB//CJ, what we gonna grab?
$ryd1_text[18] = RYD1_CC//SShhhhh! Can it, homie!
$ryd1_text[19] = RYD1_CD//I thought you was gonna keep watch outside!
$ryd1_text[20] = RYD1_CE//That's a good idea! I'll be outside!
$ryd1_text[21] = RYD1_CF//Give me strength.
$ryd1_text[22] = RYD1_ZA//Keep it coming, CJ!
$ryd1_text[23] = RYD1_ZB//What I tell ya? We making a killing!
$ryd1_text[24] = RYD1_ZC//Get back in there and strip the place!
$ryd1_text[25] = RYD1_ZD//There's plenty more in there!
$ryd1_text[26] = RYD1_ZE//Hurry it up, CJ!
$ryd1_text[27] = RYD1_ZF//Take him for everything you can!
$ryd1_text[28] = RYD1_ZG//C'mon, CJ, we can empty that place!
$ryd1_text[29] = RYD1_ZH//Plenty more room in LB's van!
$ryd1_text[30] = RYD1_ZJ//Hit him up for everything he's got!
$ryd1_text[31] = RYD1_ZK//You're a natural house-breaker, dude!
$ryd1_text[32] = RYD1_ZL//C'mon, keep it up!
$ryd1_text[33] = RYD1_ZM//We gonna be able to retire!
$ryd1_text[34] = RYD1_ZN//One more trip, CJ, we still got time!
$ryd1_text[35] = RYD1_DA//Gooks!
$ryd1_text[36] = RYD1_DB//Get off my ridge, you viet cong bastards! 
$ryd1_text[37] = RYD1_DC//This one's for Kenny!
$ryd1_text[38] = RYD1_DD//Oh shit!
$ryd1_text[39] = RYD1_DE//Thieving commie bastards!
$ryd1_text[40] = RYD1_DF//Jesus Christ!
$ryd1_text[41] = RYD1_DG//Let's get outta here!
$ryd1_text[42] = RYD1_DH//Me first!
$ryd1_text[43] = RYD1_DJ//That pussy called the cops!
$ryd1_text[44] = RYD1_DK//I'm gonna ice them!
$ryd1_text[45] = RYD1_DL//Fuck that, let's get this stuff out of here!
$ryd1_text[46] = RYD1_EA//You dumb bastard sherm head.
$ryd1_text[47] = RYD1_EB//What I do? You the one woke that crazy old bastard!
$ryd1_text[48] = RYD1_EC//We gotta lose this heat!
$ryd1_text[49] = RYD1_ED//There's a spray shop in Idlewood should be able to take a van this big!
$ryd1_text[50] = RYD1_FA//Ok, now we're cooking with gas!
$ryd1_text[51] = RYD1_FB//LB's got a lock-up down in Seville Boulevard Families turf.
$ryd1_text[52] = RYD1_FC//I'm on it!
$ryd1_text[53] = RYD1_GA//Seriously, Ryder, you gotta give up the sherms!
$ryd1_text[54] = RYD1_GB//Tell you what. I'll give up the water if you give being a buster!
$ryd1_text[55] = RYD1_GC//Now hold it right there, homie...
$ryd1_text[56] = RYD1_GD//Buster!
$ryd1_text[57] = RYD1_GE//Forget it.
$ryd1_text[58] = RYD1_GF//Forget you first!
$ryd1_text[59] = RYD1_GG//One day you're gonna wish you hadn't pissed me off!
$ryd1_text[60] = RYD1_GH//Can I hear something?
$ryd1_text[61] = RYD1_GJ//Sounds like a buster complaining all the damn time!
$ryd1_text[62] = RYD1_GK//This is childish.
$ryd1_text[63] = RYD1_GL//I ain't talking to you no more.
$ryd1_text[64] = RYD1_HA//You blown it, CJ, with your crazy-assed driving!
$ryd1_text[65] = RYD1_HB//Back to the Pay'n'Spray!
$ryd1_text[66] = RYD1_JA//You see, simple!
$ryd1_text[67] = RYD1_JB//Yeah that was a real breeze.
$ryd1_text[68] = RYD1_KA//CJ, you gotta get it into your head that this is every day shit.
$ryd1_text[69] = RYD1_KB//Listen up, no motherfucker gonna give a gangbanging carjacker like you anything for free.
$ryd1_text[70] = RYD1_KC//So you gotta go take it.
$ryd1_text[71] = RYD1_KD//All the houses in this state full of shit to rob. 
$ryd1_text[72] = RYD1_KE//They're insured, they won't miss nothing.
$ryd1_text[73] = RYD1_KF//Just bring it back here and LB will give you a fair price!
$ryd1_text[74] = RYD1_KG//Ok, look, I'm tired, I'll see you later.
$ryd1_text[75] = RYD1_KH//Whatever, holmes, think about what I said...
$ryd1_text[76] = RYDX_AC//Get in fool!
$ryd1_text[77] = RYD1_LA//	Ryder!  Buster!
$ryd1_text[78] = RYD1_LB//	Ryder, where the hell you at, man?
$ryd1_text[79] = RYD1_LC//	Mark ass Ryder!
$ryd1_text[80] = RYDX_BG//	Oh, I'm outta here! 
$ryd1_text[81] = RYDX_BI//	Oh shit, I'm gone! 

ryd1_audio[1] = SOUND_RYD1_AA//Cuz, it's already night, your head's waterlogged!
ryd1_audio[2] = SOUND_RYD1_AB//Where is this old motherfucker? Where in hell is he?
ryd1_audio[3] = SOUND_RYD1_AC//Chill out. We ain't there yet.
ryd1_audio[4] = SOUND_RYD1_AD//Yeah, right, Carl. You always right. 
ryd1_audio[5] = SOUND_RYD1_AE//That's my homie. Mister right.
ryd1_audio[6] = SOUND_RYD1_AF//Shut up.
ryd1_audio[7] = SOUND_RYD1_AG//You can't stop me.
ryd1_audio[8] = SOUND_RYD1_AH//Who can't?
ryd1_audio[9] = SOUND_RYD1_AJ//Whatever.
ryd1_audio[10] = SOUND_RYD1_BA//Let's storm the place.
ryd1_audio[11] = SOUND_RYD1_BB//Hold up. Let's sneak in, grab what we can and sneak out.
ryd1_audio[12] = SOUND_RYD1_BC//Ninja style?...OK.
ryd1_audio[13] = SOUND_RYD1_BD//Come out you old bastard!
ryd1_audio[14] = SOUND_RYD1_BE//Shut up!
ryd1_audio[15] = SOUND_RYD1_BF//He can't stop me!
ryd1_audio[16] = SOUND_RYD1_CA//Ok, Cuz, in you go, I'll keep watch.
ryd1_audio[17] = SOUND_RYD1_CB//CJ, what we gonna grab?
ryd1_audio[18] = SOUND_RYD1_CC//SShhhhh! Can it, homie!
ryd1_audio[19] = SOUND_RYD1_CD//I thought you was gonna keep watch outside!
ryd1_audio[20] = SOUND_RYD1_CE//That's a good idea! I'll be outside!
ryd1_audio[21] = SOUND_RYD1_CF//Give me strength.
ryd1_audio[22] = SOUND_RYD1_ZA//Keep it coming, CJ!
ryd1_audio[23] = SOUND_RYD1_ZB//What I tell ya? We making a killing!
ryd1_audio[24] = SOUND_RYD1_ZC//Get back in there and strip the place!
ryd1_audio[25] = SOUND_RYD1_ZD//There's plenty more in there!
ryd1_audio[26] = SOUND_RYD1_ZE//Hurry it up, CJ!
ryd1_audio[27] = SOUND_RYD1_ZF//Take him for everything you can!
ryd1_audio[28] = SOUND_RYD1_ZG//C'mon, CJ, we can empty that place!
ryd1_audio[29] = SOUND_RYD1_ZH//Plenty more room in LB's van!
ryd1_audio[30] = SOUND_RYD1_ZJ//Hit him up for everything he's got!
ryd1_audio[31] = SOUND_RYD1_ZK//You're a natural house-breaker, dude!
ryd1_audio[32] = SOUND_RYD1_ZL//C'mon, keep it up!
ryd1_audio[33] = SOUND_RYD1_ZM//We gonna be able to retire!
ryd1_audio[34] = SOUND_RYD1_ZN//One more trip, CJ, we still got time!
ryd1_audio[35] = SOUND_RYD1_DA//Gooks!
ryd1_audio[36] = SOUND_RYD1_DB//Get off my ridge, you viet cong bastards! 
ryd1_audio[37] = SOUND_RYD1_DC//This one's for Kenny!
ryd1_audio[38] = SOUND_RYD1_DD//Oh shit!
ryd1_audio[39] = SOUND_RYD1_DE//Thieving commie bastards!
ryd1_audio[40] = SOUND_RYD1_DF//Jesus Christ!
ryd1_audio[41] = SOUND_RYD1_DG//Let's get outta here!
ryd1_audio[42] = SOUND_RYD1_DH//Me first!
ryd1_audio[43] = SOUND_RYD1_DJ//That pussy called the cops!
ryd1_audio[44] = SOUND_RYD1_DK//I'm gonna ice them!
ryd1_audio[45] = SOUND_RYD1_DL//Fuck that, let's get this stuff out of here!
ryd1_audio[46] = SOUND_RYD1_EA//You dumb bastard sherm head.
ryd1_audio[47] = SOUND_RYD1_EB//What I do? You the one woke that crazy old bastard!
ryd1_audio[48] = SOUND_RYD1_EC//We gotta lose this heat!
ryd1_audio[49] = SOUND_RYD1_ED//There's a spray shop in Idlewood should be able to take a van this big!
ryd1_audio[50] = SOUND_RYD1_FA//Ok, now we're cooking with gas!
ryd1_audio[51] = SOUND_RYD1_FB//LB's got a lock-up down in Seville Boulevard Families turf.
ryd1_audio[52] = SOUND_RYD1_FC//I'm on it!
ryd1_audio[53] = SOUND_RYD1_GA//Seriously, Ryder, you gotta give up the sherms!
ryd1_audio[54] = SOUND_RYD1_GB//Tell you what. I'll give up the water if you give being a buster!
ryd1_audio[55] = SOUND_RYD1_GC//Now hold it right there, homie...
ryd1_audio[56] = SOUND_RYD1_GD//Buster!
ryd1_audio[57] = SOUND_RYD1_GE//Forget it.
ryd1_audio[58] = SOUND_RYD1_GF//Forget you first!
ryd1_audio[59] = SOUND_RYD1_GG//One day you're gonna wish you hadn't pissed me off!
ryd1_audio[60] = SOUND_RYD1_GH//Can I hear something?
ryd1_audio[61] = SOUND_RYD1_GJ//Sounds like a buster complaining all the damn time!
ryd1_audio[62] = SOUND_RYD1_GK//This is childish.
ryd1_audio[63] = SOUND_RYD1_GL//I ain't talking to you no more.
ryd1_audio[64] = SOUND_RYD1_HA//You blown it, CJ, with your crazy-assed driving!
ryd1_audio[65] = SOUND_RYD1_HB//Back to the Pay'n'Spray!
ryd1_audio[66] = SOUND_RYD1_JA//You see, simple!
ryd1_audio[67] = SOUND_RYD1_JB//Yeah that was a real breeze.
ryd1_audio[68] = SOUND_RYD1_KA//CJ, you gotta get it into your head that this is every day shit.
ryd1_audio[69] = SOUND_RYD1_KB//Listen up, no motherfucker gonna give a gangbanging carjacker like you anything for free.
ryd1_audio[70] = SOUND_RYD1_KC//So you gotta go take it.
ryd1_audio[71] = SOUND_RYD1_KD//All the houses in this state full of shit to rob. 
ryd1_audio[72] = SOUND_RYD1_KE//They're insured, they won't miss nothing.
ryd1_audio[73] = SOUND_RYD1_KF//Just bring it back here and LB will give you a fair price!
ryd1_audio[74] = SOUND_RYD1_KG//Ok, look, I'm tired, I'll see you later.
ryd1_audio[75] = SOUND_RYD1_KH//Whatever, holmes, think about what I said...
ryd1_audio[76] = SOUND_RYDX_AC//Get in fool!
ryd1_audio[77] = SOUND_RYD1_LA//	Ryder!  Buster!
ryd1_audio[78] = SOUND_RYD1_LB//	Ryder, where the hell you at, man?
ryd1_audio[79] = SOUND_RYD1_LC//	Mark ass Ryder!
ryd1_audio[80] = SOUND_RYDX_BG//	Oh, I'm outta here! 
ryd1_audio[81] = SOUND_RYDX_BI//	Oh shit, I'm gone!

ryd1_sfx[1]  =	 SOUND_ALARM_CLOCK
ryd1_sfx[2]  =	 SOUND_SECURITY_ALARM
ryd1_sfx[3]  =	 SOUND_SNORE

ryd1_sfx_counter  = 0 
ryd1_sfx_playing  = 0
ryd1_sfx_played   = 0

disable_mod_garage = 1


    ////////////////////////////////////////
	/// CREATE RYDER AND THE BURGLAR VAN ///
	////////////////////////////////////////

	   	IF ryd1_mission_progression_flag = 0								   	
		   	
			GET_TIME_OF_DAY hours minutes
		   	ryd1_at_night_flag = 1
		   	
		   	SET_TIME_OF_DAY 22 00
			SWITCH_ENTRY_EXIT burhous FALSE
		   	REQUEST_MODEL BOXBURG
			REQUEST_ANIMATION CAR_CHAT
			LOAD_SPECIAL_CHARACTER 1 ryder2
			WHILE NOT HAS_MODEL_LOADED BOXBURG
			OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
		   	OR NOT HAS_ANIMATION_LOADED CAR_CHAT
			WAIT 0 
			ENDWHILE
		   	CLEAR_AREA 2470.6560 -1689.6487 12.5198 100.0 TRUE
		   	CUSTOM_PLATE_FOR_NEXT_CAR BOXBURG &__SWAG__
			CREATE_CAR BOXBURG	2476.6819 -1679.5656 12.3451  ryd1_truck_car		   
			SET_CAR_HEADING ryd1_truck_car 48.7370   
		   	CREATE_CHAR PEDTYPE_SPECIAL SPECIAL01 2475.1677 -1686.4825 12.5007 	ryd1_ryder_ped
		   	
			SET_CHAR_CANT_BE_DRAGGED_OUT ryd1_ryder_ped TRUE
		   	SET_CHAR_DECISION_MAKER ryd1_ryder_ped ryd1_decision
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ryd1_decision EVENT_DAMAGE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ryd1_decision EVENT_VEHICLE_ON_FIRE
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ryd1_decision EVENT_GOT_KNOCKED_OVER_BY_CAR
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ryd1_decision EVENT_VEHICLE_DAMAGE_COLLISION
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE ryd1_decision EVENT_VEHICLE_DAMAGE_WEAPON
			ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE ryd1_decision EVENT_DAMAGE TASK_SIMPLE_BE_DAMAGED 0.0 100.0 0.0 0.0 TRUE TRUE
		   	SET_ANIM_GROUP_FOR_CHAR ryd1_ryder_ped gang1
		   	SET_CHAR_NEVER_TARGETTED ryd1_ryder_ped TRUE
			SET_CHAR_HEALTH ryd1_ryder_ped 200
			SET_CHAR_SUFFERS_CRITICAL_HITS ryd1_ryder_ped FALSE
		   	SET_CAR_HEADING ryd1_truck_car 30.0 
		   	
		   	IF ryd1_at_night_flag = 999
		   	    ADD_BLIP_FOR_CAR ryd1_truck_car ryd1_truck_blip 									
			    SET_BLIP_AS_FRIENDLY ryd1_truck_blip TRUE
			ENDIF
			
			CLEAR_PRINTS
			
			ryd1_truck_blip_added_flag = 1
			ryd1_ryder_in_van_flag = 1
			ryd1_ryder_created_flag = 1
			ryd1_mission_progression_flag = 1									
		
			LOAD_SCENE 2475.0 -1699.0 15.0
			
			RESTORE_CAMERA_JUMPCUT
			
			//players drivers side to van
			SET_CHAR_COORDINATES scplayer 2473.4829 -1686.5990 12.5154 
			SET_CHAR_HEADING scplayer 14.9748 
			
			OPEN_SEQUENCE_TASK ryd1_player_leave_seq
			TASK_GO_STRAIGHT_TO_COORD -1 2473.5269 -1680.0364 12.3551  PEDMOVE_WALK 6000
			SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
			TASK_ENTER_CAR_AS_DRIVER -1 ryd1_truck_car 5000
			CLOSE_SEQUENCE_TASK  ryd1_player_leave_seq
			PERFORM_SEQUENCE_TASK	scplayer ryd1_player_leave_seq
			CLEAR_SEQUENCE_TASK  ryd1_player_leave_seq

			//ryder
			
			SET_CHAR_HEADING ryd1_ryder_ped 349.3790  
			OPEN_SEQUENCE_TASK ryd1_player_leave_seq
			TASK_GO_STRAIGHT_TO_COORD -1 2480.9248 -1682.3480 12.3381 PEDMOVE_RUN 6000
			TASK_GO_STRAIGHT_TO_COORD -1 2478.3054 -1677.0597 12.3478 PEDMOVE_RUN 6000			
			TASK_ENTER_CAR_AS_PASSENGER -1 ryd1_truck_car 3000 0
			CLOSE_SEQUENCE_TASK ryd1_player_leave_seq
			PERFORM_SEQUENCE_TASK ryd1_ryder_ped ryd1_player_leave_seq
			CLEAR_SEQUENCE_TASK ryd1_player_leave_seq
			SET_FIXED_CAMERA_POSITION 2470.3828 -1675.8162 13.2161 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2471.1660 -1676.4238 13.3470	JUMP_CUT
			
			SWITCH_WIDESCREEN ON
			CLEAR_AREA 2470.6560 -1689.6487 12.5198 100.0 TRUE
			SET_TIME_OF_DAY 22 00
			
			DO_FADE 1000 FADE_IN
			WHILE GET_FADING_STATUS
			   WAIT 0
			ENDWHILE
			SKIP_CUTSCENE_START
			TIMERA = 0
			ryd1_safety_flag = 0
			ryd1_audio_counter	   = 76	//	Get in fool
			ryd1_audio_slot1 	   = 1
			ryd1_audio_slot2 	   = 2
			ryd1_audio_playing	   = 0																								
			ryd1_ahead_counter	   = 0
			 	
			WHILE ryd1_safety_flag = 0																							 				 				 																								 								 																																																												
				
				IF NOT ryd1_audio_counter = 0
					IF ryd1_audio_playing = 0
						IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
							CLEAR_MISSION_AUDIO ryd1_audio_slot2
						ENDIF
						ryd1_audio_playing = 1
					ENDIF

					IF ryd1_audio_playing = 1
						LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
						ryd1_audio_playing = 2
					ENDIF

					IF ryd1_audio_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
							PLAY_MISSION_AUDIO ryd1_audio_slot1
							PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
							ryd1_audio_playing = 3
						ENDIF
					ENDIF

					IF ryd1_audio_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
							CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
							IF ryd1_audio_slot1 = 1
								ryd1_audio_slot1 = 2
								ryd1_audio_slot2 = 1
							ELSE
								ryd1_audio_slot1 = 1
								ryd1_audio_slot2 = 2
							ENDIF
							ryd1_audio_counter = 0
							ryd1_audio_playing = 0
						ELSE
							IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
								IF ryd1_audio_counter < 76
									ryd1_ahead_counter = ryd1_audio_counter + 1
									LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				
				IF TIMERA > 15000
					ryd1_safety_flag = 1
				ENDIF
				IF NOT IS_CHAR_DEAD ryd1_ryder_ped
					IF NOT IS_CAR_DEAD ryd1_truck_car
						IF IS_CHAR_SITTING_IN_CAR ryd1_ryder_ped ryd1_truck_car
						AND IS_CHAR_SITTING_IN_CAR scplayer ryd1_truck_car
							ryd1_safety_flag = 1
						ENDIF
					ELSE
						ryd1_safety_flag = 1					
					ENDIF
				ELSE
					ryd1_safety_flag = 1
				ENDIF
				WAIT 0 
			ENDWHILE
			
			SKIP_CUTSCENE_END

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2
			CLEAR_PRINTS

			IF ryd1_at_night_flag = 1			
				SET_EVERYONE_IGNORE_PLAYER player1 ON
																
				CLEAR_MISSION_AUDIO ryd1_audio_slot1
				CLEAR_MISSION_AUDIO ryd1_audio_slot2

				
				CLEAR_PRINTS
				FREEZE_ONSCREEN_TIMER FALSE
				ryd1_audio_counter	   = 0
				ryd1_audio_slot1 	   = 1
				ryd1_audio_slot2 	   = 2
				ryd1_audio_playing	   = 0
				ryd1_text_loop_done	   = 0
				ryd1_mobile			   = 0
				ryd1_text_timer_diff   = 0
				ryd1_text_timer_end    = 0
				ryd1_text_timer_start  = 0
				ryd1_ahead_counter	   = 0
								
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE

				IF NOT IS_CAR_DEAD ryd1_truck_car
					IF NOT IS_CHAR_DEAD ryd1_ryder_ped
						IF NOT IS_CHAR_IN_CAR ryd1_ryder_ped ryd1_truck_car
						   WARP_CHAR_INTO_CAR_AS_PASSENGER ryd1_ryder_ped ryd1_truck_car 0
						ENDIF
						IF NOT IS_CHAR_IN_CAR scplayer ryd1_truck_car
						   WARP_CHAR_INTO_CAR scplayer ryd1_truck_car							   
						ENDIF
					ENDIF
				ENDIF		  
				
				SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
		   		SET_SCRIPT_LIMIT_TO_GANG_SIZE 0
		   		MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
				
				IF NOT IS_CHAR_DEAD ryd1_ryder_ped
				IF NOT IS_GROUP_MEMBER ryd1_ryder_ped Players_Group
					SET_SCRIPT_LIMIT_TO_GANG_SIZE 1
					SET_GROUP_MEMBER Players_Group ryd1_ryder_ped				
					SET_GROUP_FOLLOW_STATUS Players_Group FALSE
					LISTEN_TO_PLAYER_GROUP_COMMANDS ryd1_ryder_ped FALSE
				ENDIF
			ENDIF

			ENDIF
			RESTORE_CAMERA_JUMPCUT
			SWITCH_WIDESCREEN OFF
			CLEAR_PRINTS
			SET_TIME_OF_DAY 22 00		   		
			REMOVE_ANIMATION CAR_CHAT
		   	SET_EVERYONE_IGNORE_PLAYER player1 OFF
			
			///////////////////////////////////////////////
			//// HAVE PLAYER PUT ON A BALACLAVA HERE ? ////
			///////////////////////////////////////////////
			
			STORE_CLOTHES_STATE
			ryd1_clothes_changed = 1
			GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1 balaclava balaclava CLOTHES_TEX_EXTRA1   	
			BUILD_PLAYER_MODEL player1
			
			CLEAR_THIS_BIG_PRINT BOXBURG
			PRINT (GUNS130) 5000 1
			DO_FADE 1000 FADE_IN
		    WHILE GET_FADING_STATUS
		    WAIT 0
		    ENDWHILE
            SET_PLAYER_CONTROL player1 ON
			IF played_ryder1_before = 1
			ENDIF
		ENDIF
		
		
   
    // START MAIN LOOP 
	
	ryder1_main_loop:

	WAIT 0
	
	//////////////////////DAVE B debug keys REMOVE LATER ////

	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
		GOTO mission_g1_passed
		CREATE_CHAR PEDTYPE_MISSION1 wmopj 2817.2693 -1169.8748 28.2720 ryd1_owner_ped			 				
	ENDIF
	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
		SET_CHAR_COORDINATES scplayer 2834.0 -1182.0 25.0
	ENDIF
	
		
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
		SET_CHAR_COORDINATES scplayer 2492.0 -1666.0 12.0
	ENDIF
	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_4
	ryd1_number_gunbox_truck = 4
	ENDIF
	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_3
		ryd1_mission_progression_flag = 8
	ENDIF
	
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_BSPACE
	ryd1_time_to_daylight = 6000000
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_0
	ryd1_time_to_daylight = 5000
	ENDIF

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
		GOTO mission_g1_passed
	ENDIF

	///////////////////////////////////////////////////////////////
	/// IF RYDER / VAN CREATED FAIL MISSION IF EITHER IS KILLED ///
	///////////////////////////////////////////////////////////////
	IF ryd1_ryder_created_flag = 1
	   IF IS_CHAR_DEAD ryd1_ryder_ped
	   		CLEAR_PRINTS
	   		IF IS_PLAYER_WEARING player1 CLOTHES_TEX_EXTRA1	balaclava
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

	   		PRINT (GUNS125) 5000 1 //"Ryders dead"	   	  	   	    	   	    	   	    
	   	    GOTO mission_g1_failed
	   ENDIF
	   
	   IF IS_CAR_DEAD ryd1_truck_car	   	  	   	  
	   	  IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
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
	   	  
	   	  PRINT (GUNS122)  5000 1 //" the truck has been destroyed "	   	  
	   	  GOTO mission_g1_failed
	   ENDIF			 		   
	
		GET_AREA_VISIBLE ryd1_area_name
		IF ryd1_area_name = 0
	   		IF ryd1_mission_progression_flag < 8
		   		IF NOT IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava			
					PRINT (RYD1_37)  5000 1 //" ~r~You no longer have the balaclava. "	   	  
		   	  		GOTO mission_g1_failed
				ENDIF
			ENDIF
		ENDIF
				
	ENDIF
    
	
////////////////////////////////////////////////////////////////////////////
///////////////////////   BEFORE PLAYER REACHES THE HOUSE //////////////////
////////////////////////////////////////////////////////////////////////////

			
			
	//////////////////////////////////////////////////////////////////////
	/// ONCE PLAYER GETS IN THE VAN REMOVE BLIP AND ADD BLIP FOR HOUSE ///
    //////////////////////////////////////////////////////////////////////	
	IF ryd1_mission_progression_flag = 1
	AND NOT IS_CAR_DEAD ryd1_truck_car
	   IF IS_CHAR_IN_CAR scplayer ryd1_truck_car
	   	  REMOVE_BLIP ryd1_truck_blip
	   	  ryd1_truck_blip_added_flag = 0	   	  
	   	  CLEAR_PRINTS
		  ADD_BLIP_FOR_COORD  2819.9 -1181.7 25.15 ryd1_house_blip		  
		  PRINT (GUNS130) 5000 1													  		   		   	   	  
	   	  ryd1_house_blip_added_flag = 1
	   	  ryd1_mission_progression_flag = 2
	   ENDIF
	ENDIF

			
   
   	IF ryd1_mission_progression_flag = 2
	
	
	   	
	////////////////////////////////////////////////////////////
	/// CHECK IF PLAYER LEAVES VAN BEFORE REACHING THE HOUSE ///
	////////////////////////////////////////////////////////////   	
	   	
		IF NOT IS_CAR_DEAD ryd1_truck_car
		AND NOT IS_CHAR_DEAD ryd1_ryder_ped
			
			/////////////////////
			/// PLAYER CHECKS ///
			/////////////////////

			IF NOT IS_CHAR_IN_CAR scplayer ryd1_truck_car
		   		IF ryd1_truck_blip_added_flag = 0
			   	   	IF ryd1_ryder_in_van_flag = 0
						IF ryd1_ryder_blip_added_flag = 1
							REMOVE_BLIP ryd1_ryder_blip
							ryd1_ryder_blip_added_flag = 0
						ENDIF
					ENDIF
					 
			   	   	
			   	   	
			   	   	REMOVE_BLIP ryd1_house_blip
				   	IF ryd1_ryder_blip_added_flag = 1
						REMOVE_BLIP ryd1_ryder_blip
						ryd1_ryder_blip_added_flag = 0
				   	ENDIF
				   	ryd1_house_blip_added_flag = 0	   	    
			   	   	IF IS_MESSAGE_BEING_DISPLAYED
			   	   		ryd1_message_on_screen = 1
			   	   	ENDIF		   	   	 
			   	   	CLEAR_PRINTS
			   	   	PRINT (GUNS128) 5000 1 //~s~You left the ~b~truck~s~! Get back to it!
			   	   	
			   	   	ryd1_audio_counter = 76
			   	   	ryd1_audio_playing = 0
					CLEAR_MISSION_AUDIO ryd1_audio_slot1
					CLEAR_MISSION_AUDIO ryd1_audio_slot2

					SET_PLAYER_GROUP_TO_FOLLOW_NEVER player1 True
			   	   	ADD_BLIP_FOR_CAR	ryd1_truck_car ryd1_truck_blip 
		   		   	SET_BLIP_AS_FRIENDLY ryd1_truck_blip TRUE
		   			ryd1_truck_blip_added_flag = 1
		   		ELSE
					IF NOT ryd1_audio_counter = 0
						IF ryd1_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
								CLEAR_MISSION_AUDIO ryd1_audio_slot2
							ENDIF
							ryd1_audio_playing = 1
						ENDIF

						IF ryd1_audio_playing = 1
							LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
							ryd1_audio_playing = 2
						ENDIF

						IF ryd1_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
								ATTACH_MISSION_AUDIO_TO_CHAR  ryd1_audio_slot1 ryd1_ryder_ped
								PLAY_MISSION_AUDIO ryd1_audio_slot1
								//PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
								ryd1_message_displayed++
								ryd1_audio_playing = 3
							ENDIF
						ENDIF

						IF ryd1_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
								//CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
								IF ryd1_audio_slot1 = 1
									ryd1_audio_slot1 = 2
									ryd1_audio_slot2 = 1
								ELSE
									ryd1_audio_slot1 = 1
									ryd1_audio_slot2 = 2
								ENDIF
								ryd1_audio_counter = 0
								ryd1_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
									IF ryd1_audio_counter < 76
										ryd1_ahead_counter = ryd1_audio_counter + 1
										LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF		   		
		   		ENDIF	
		   	ELSE
				IF ryd1_ryder_in_van_flag = 1					
					
					IF ryd1_house_blip_added_flag = 0
					   	SET_PLAYER_GROUP_TO_FOLLOW_NEVER player1 FALSE
					   	REMOVE_BLIP ryd1_truck_blip
					   	ryd1_truck_blip_added_flag = 0					   	
					   	CLEAR_PRINTS				   	   	
				   	   	PRINT (GUNS130) 5000 1 // "Park out side the house"
				   	   	ADD_BLIP_FOR_COORD  2819.9 -1181.7 25.15 ryd1_house_blip 
			   		   	ryd1_house_blip_added_flag = 1
			   		ENDIF				   						
			   	ELSE
					IF ryd1_truck_blip_added_flag = 1
						SET_PLAYER_GROUP_TO_FOLLOW_NEVER player1 FALSE
						REMOVE_BLIP ryd1_truck_blip
						ryd1_truck_blip_added_flag = 0
					ENDIF
			   	ENDIF
		   	ENDIF

			
						
			////////////////////
			/// RYDER CHECKS ///
			////////////////////
			
			
			IF IS_CHAR_IN_CAR ryd1_ryder_ped ryd1_truck_car
				IF ryd1_ryder_in_van_flag = 0
					IF ryd1_ryder_blip_added_flag = 1
						REMOVE_BLIP ryd1_ryder_blip
						ryd1_ryder_blip_added_flag = 0
					ENDIF
					ryd1_ryder_in_van_flag = 1
					ryd1_ryder_told_enter_car_flag = 0					
					ryd1_ryder_in_van_flag = 1
				ENDIF
			ELSE
				IF ryd1_ryder_in_van_flag = 1
					ryd1_ryder_in_van_flag = 0
				ENDIF

				IF ryd1_ryder_blip_added_flag = 0
				AND ryd1_truck_blip_added_flag = 0				    	
			    	REMOVE_BLIP ryd1_house_blip
		   		  	ryd1_house_blip_added_flag = 0			   	  	
			   	  	ADD_BLIP_FOR_CHAR ryd1_ryder_ped ryd1_ryder_blip
				  	SET_BLIP_AS_FRIENDLY ryd1_ryder_blip TRUE				  					  	
				  	IF IS_MESSAGE_BEING_DISPLAYED
	   	   				ryd1_message_on_screen = 1
	   	   		  	ENDIF
				  	
				  	CLEAR_PRINTS
				  	PRINT (GUNS143) 5000 1 //~s~You've left ~b~Ryder~s~ behind go and get him.
					ryd1_ryder_blip_added_flag = 1					
			   ENDIF				   

			   IF ryd1_ryder_told_enter_car_flag = 0
				  	TASK_ENTER_CAR_AS_PASSENGER ryd1_ryder_ped ryd1_truck_car -2 0	// -2 was 3000
				  	ryd1_ryder_told_enter_car_flag = 1			   
			   ELSE
					GET_SCRIPT_TASK_STATUS ryd1_ryder_ped TASK_ENTER_CAR_AS_PASSENGER ryd1_task_status
					IF NOT ryd1_task_status  = WAITING_TO_START_TASK
				    AND NOT ryd1_task_status  = PERFORMING_TASK
						ryd1_ryder_told_enter_car_flag = 0
					ENDIF
			   ENDIF					
			   
			ENDIF
		ENDIF
		

	

	//////////////////////////////////////////////////
	//// CHECK IF MESSAGE DISPLAY GETS INTERUPTED ////
	//////////////////////////////////////////////////
	
		IF ryd1_ryder_in_van_flag = 1
		AND ryd1_house_blip_added_flag = 1
		
			//IF IS_MESSAGE_BEING_DISPLAYED
				//ryd1_message_on_screen = 1
		    //ENDIF
			
			
			IF ryd1_message_on_screen = 1
				IF ryd1_message_displayed < 8
				   PRINT (GUNS135) 4000 1 //As I was saying....
				   ryd1_message_displayed--
				   IF ryd1_message_displayed < 0			   		
				   		ryd1_message_displayed = 0
				   ENDIF
				   ryd1_message_on_screen = 0
				ENDIF
			ENDIF

			IF ryd1_message_on_screen = 0
				IF ryd1_text_loop_done = 0																				 					
												
						IF NOT ryd1_audio_counter = 0
							IF ryd1_audio_playing = 0
								IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
									CLEAR_MISSION_AUDIO ryd1_audio_slot2
								ENDIF
								ryd1_audio_playing = 1
							ENDIF

							IF ryd1_audio_playing = 1
								LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
								ryd1_audio_playing = 2
							ENDIF

							IF ryd1_audio_playing = 2
							 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
									PLAY_MISSION_AUDIO ryd1_audio_slot1
									PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
									ryd1_message_displayed++
									ryd1_audio_playing = 3
								ENDIF
							ENDIF

							IF ryd1_audio_playing = 3
								IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
									CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
									IF ryd1_audio_slot1 = 1
										ryd1_audio_slot1 = 2
										ryd1_audio_slot2 = 1
									ELSE
										ryd1_audio_slot1 = 1
										ryd1_audio_slot2 = 2
									ENDIF
									ryd1_audio_counter = 0
									ryd1_audio_playing = 0
								ELSE
									IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
										IF ryd1_audio_counter < 76
											ryd1_ahead_counter = ryd1_audio_counter + 1
											LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF

						
						IF NOT IS_MESSAGE_BEING_DISPLAYED
						SWITCH ryd1_mobile
							CASE 0
								IF ryd1_audio_playing = 0									
									ryd1_audio_counter = 2	// PRINT (RYD1_AB) 4000 1 //RYDER: Where is this old motherfucker? Where in hell is he?   //(RYD1_AB)
									ryd1_mobile = 1									
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 5000
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped TRUE
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
									ENDIF
									GET_GAME_TIMER ryd1_text_timer_start
								ENDIF
								BREAK
							CASE 1
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0																				
										ryd1_audio_counter = 3 //PRINT (RYD1_AC) 4000 1 //CARL: Chill out. We ain't there yet. //(RYD1_AC)
										ryd1_mobile = 2										
										
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF	
								BREAK
							CASE 2
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
										ENDIF
										ryd1_audio_counter = 4 //PRINT (RYD1_AD) 4000 1 //RYDER: Yeah, right, Carl. You always right.  //(RYD1_AD) 	
										ryd1_mobile = 3										
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 3
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
										ENDIF
										ryd1_audio_counter = 5//PRINT (RYD1_AE) 3000 1 //That's my homie. Mister right.	 //(RYD1_AE)	
										ryd1_mobile = 4
										
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 4
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										ryd1_audio_counter = 6//PRINT (RYD1_AF) 3000 1 //CARL :Shut up. //(RYD1_AF)
										ryd1_mobile = 5
										
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 5
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										ryd1_audio_counter = 7//PRINT (RYD1_AG) 3000 1 //RYDER: You can't stop me.//(RYD1_AG)
										ryd1_mobile = 6
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 3000
										ENDIF
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 6
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										ryd1_audio_counter = 8//PRINT (RYD1_AH) 3000 1 //CARL: Who can't? //(RYD1_AH)
										ryd1_mobile = 7
										
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 7
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										ryd1_audio_counter = 9//PRINT (RYD1_AJ) 3000 1 //RYDER: Whatever. //(RYD1_AJ)
										ryd1_mobile = 8
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 3000
										ENDIF
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK 
							DEFAULT
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
									   ryd1_text_loop_done = 1	
									   IF NOT IS_CHAR_DEAD ryd1_ryder_ped
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped FALSE
											SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
											STOP_CHAR_FACIAL_TALK ryd1_ryder_ped										
										ENDIF
									   PRINT (GUNS130) 5000 1													  		   		   	   	  									   
									ENDIF
								ENDIF
								BREAK
						ENDSWITCH
						ENDIF
					
										
						/////////////////////////////////////////////////////////////////
					/////////////////////////////////////////////////////////////////																														
				ENDIF
			ENDIF
		ENDIF
	

																		
	
	////////////////////////////////////////////
	/// CHECK IF PLAYER IS OUTSIDE THE HOUSE ///
	////////////////////////////////////////////
	
		IF ryd1_ryder_in_van_flag = 1
		AND ryd1_house_blip_added_flag = 1

			IF LOCATE_CHAR_IN_CAR_3D scplayer 2819.9 -1181.7 25.15 4.0 4.0 2.5 TRUE
				
				SET_PLAYER_CONTROL player1 OFF
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
				TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
				
				REMOVE_BLIP ryd1_house_blip
				ryd1_house_blip_added_flag = 0
				ryd1_message_on_screen = 0			
				
				SET_POLICE_IGNORE_PLAYER player1 ON
				
				CLEAR_PRINTS
				CLEAR_AREA 2819.9 -1181.7 25.15 30.0 TRUE			 
				ryd1_mission_progression_flag = 3

			ENDIF
		ENDIF
	ENDIF

	
		
	IF ryd1_mission_progression_flag < 4
	AND ryd1_timer_on_screen = 1
		IF ryd1_time_to_daylight = 0
			CLEAR_PRINTS
		   	CLEAR_CHAR_TASKS scplayer
		   	IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
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
		   	PRINT (RYD1_35) 5000 1 //~r~You've waited too long to get to the house, it's no longer night time!
		   	GOTO mission_g1_failed
		ENDIF
	ENDIF
	
    
////////////////////////////////////////////////////////////////////////////
///////////////////////   WHEN PLAYER REACHES THE HOUSE   //////////////////
////////////////////////////////////////////////////////////////////////////
	

	/////////////////////////////////////////////////
	/// PLAY CUT-SCENE EXPLAINING HOW TO BREAK IN ///
	/////////////////////////////////////////////////
	
	IF ryd1_mission_progression_flag = 3
	   	
		IF played_ryder1_before = 0
			played_ryder1_before = 1
		ENDIF
	   
	   // cut-scene goes here		   	
	   SET_PLAYER_CONTROL player1 OFF
	   SWITCH_WIDESCREEN ON
	   FREEZE_ONSCREEN_TIMER TRUE
	   SKIP_CUTSCENE_START
	   
	   // view of front of van
	   SET_FIXED_CAMERA_POSITION 2798.6450 -1190.1647 31.8647  0.0 0.0 0.0 // front of van
	   POINT_CAMERA_AT_POINT 2799.4875 -1189.6843 31.6214 JUMP_CUT  // wide shot
	   
	   CLEAR_PRINTS	  	   
	   
				ryd1_audio_counter	   = 0
				ryd1_audio_slot1 	   = 1
				ryd1_audio_slot2 	   = 2
				ryd1_audio_playing	   = 0
				ryd1_text_loop_done	   = 0
				ryd1_mobile			   = 0
				ryd1_text_timer_diff   = 0
				ryd1_text_timer_end    = 0
				ryd1_text_timer_start  = 0
				ryd1_ahead_counter	   = 0
				
				ryder1_text_loop2:
				WAIT 0 

				IF ryd1_text_loop_done = 0
					
					IF NOT ryd1_audio_counter = 0
						IF ryd1_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
								CLEAR_MISSION_AUDIO ryd1_audio_slot2
							ENDIF
							ryd1_audio_playing = 1
						ENDIF

						IF ryd1_audio_playing = 1
							LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
							ryd1_audio_playing = 2
						ENDIF

						IF ryd1_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
								PLAY_MISSION_AUDIO ryd1_audio_slot1
								PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
								ryd1_audio_playing = 3
							ENDIF
						ENDIF

						IF ryd1_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
								CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
								IF ryd1_audio_slot1 = 1
									ryd1_audio_slot1 = 2
									ryd1_audio_slot2 = 1
								ELSE
									ryd1_audio_slot1 = 1
									ryd1_audio_slot2 = 2
								ENDIF
								ryd1_audio_counter = 0
								ryd1_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
									IF ryd1_audio_counter < 76
										ryd1_ahead_counter = ryd1_audio_counter + 1
										LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					
						SWITCH ryd1_mobile
							CASE 0
								IF ryd1_audio_playing = 0									
									ryd1_audio_counter = 10	//PRINT (RYD1_BA) 4000 1 //RYDER: Let's storm the place.//(RYD1_BA) 
									ryd1_mobile = 1
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped
									  
									   START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
										
									   OPEN_SEQUENCE_TASK ryd1_ryder_house_seq
									   TASK_LEAVE_ANY_CAR -1
									   TASK_GO_STRAIGHT_TO_COORD -1 2809.2810 -1181.6093 24.6181 PEDMOVE_WALK -1	   
									   CLOSE_SEQUENCE_TASK ryd1_ryder_house_seq	   
									   PERFORM_SEQUENCE_TASK ryd1_ryder_ped ryd1_ryder_house_seq	   
									   CLEAR_SEQUENCE_TASK ryd1_ryder_house_seq
								    ENDIF
									GET_GAME_TIMER ryd1_text_timer_start
								ENDIF
								BREAK
							CASE 1
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0																				
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										STOP_CHAR_FACIAL_TALK ryd1_ryder_ped
										START_CHAR_FACIAL_TALK scplayer 5000
										ENDIF
										
										OPEN_SEQUENCE_TASK ryd1_player_house_seq
										TASK_LEAVE_ANY_CAR -1
										TASK_GO_STRAIGHT_TO_COORD -1 2808.1194 -1182.2551 24.6181 PEDMOVE_WALK -1 // this need to be changed	   
										CLOSE_SEQUENCE_TASK ryd1_player_house_seq
										PERFORM_SEQUENCE_TASK scplayer ryd1_player_house_seq
										CLEAR_SEQUENCE_TASK ryd1_player_house_seq
										ryd1_audio_counter = 11 // PRINT (RYD1_BB) 4000 1 //CARL: Hold up. Let's sneak in, grab the guns and sneak out.	//(RYD1_BB)
										ryd1_mobile = 2
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF	
								BREAK
							CASE 2
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										STOP_CHAR_FACIAL_TALK scplayer
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
										ENDIF
										ryd1_audio_counter = 12 // PRINT (RYD1_BC) 4000 1 //RYDER: Ninja style?...OK.	   //(RYD1_BC)
										REQUEST_ANIMATION ON_LOOKERS
	   									REQUEST_ANIMATION GANGS
										ryd1_mobile = 3
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 3
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										WHILE NOT HAS_ANIMATION_LOADED  ON_LOOKERS
										OR NOT HAS_ANIMATION_LOADED GANGS						
											WAIT 0
										ENDWHILE
										IF ryd1_locked_car_flag = 0
									   		IF NOT IS_CAR_DEAD ryd1_truck_car
									   		AND NOT IS_CHAR_DEAD ryd1_ryder_ped	   		
									   			IF NOT IS_CHAR_IN_CAR scplayer ryd1_truck_car
									   			AND NOT IS_CHAR_IN_CAR ryd1_ryder_ped ryd1_truck_car
									   				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
													CLEAR_CHAR_TASKS_IMMEDIATELY ryd1_ryder_ped
									   				SET_CHAR_COORDINATES scplayer 2808.1194 -1182.2551 24.3693
													SET_CHAR_HEADING scplayer 353.059
													SET_CHAR_COORDINATES ryd1_ryder_ped	2809.2810 -1181.6093 24.3693
													SET_CHAR_HEADING ryd1_ryder_ped 30.6396

									   				SET_FIXED_CAMERA_POSITION  2804.0598 -1181.8337 25.8646    0.0 0.0 0.0
													POINT_CAMERA_AT_POINT 2804.9604 -1181.4055 25.9365 JUMP_CUT
									   				
									   				CLOSE_ALL_CAR_DOORS ryd1_truck_car 
									   				LOCK_CAR_DOORS ryd1_truck_car CARLOCK_LOCKED					
													FIX_CAR_DOOR ryd1_truck_car FRONT_LEFT_DOOR
													FIX_CAR_DOOR ryd1_truck_car FRONT_RIGHT_DOOR
													FREEZE_CAR_POSITION ryd1_truck_car TRUE
									   				ryd1_locked_car_flag = 1
									   			ENDIF
									   		ENDIF
										ENDIF
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
											OPEN_SEQUENCE_TASK ryd1_player_door_seq 
											TASK_PLAY_ANIM	 -1  shout_in 		ON_LOOKERS 1000.0 FALSE FALSE FALSE FALSE -1 // latest addition				
											TASK_PLAY_ANIM	 -1  shout_loop 	ON_LOOKERS 1000.0 FALSE FALSE FALSE FALSE -1 // latest addition
											TASK_PLAY_ANIM	 -1  shout_out 		ON_LOOKERS 1000.0 FALSE FALSE FALSE FALSE -1 // latest addition						
											CLOSE_SEQUENCE_TASK ryd1_player_door_seq
											PERFORM_SEQUENCE_TASK ryd1_ryder_ped ryd1_player_door_seq
											CLEAR_SEQUENCE_TASK ryd1_player_door_seq
											TASK_TURN_CHAR_TO_FACE_CHAR	scplayer ryd1_ryder_ped
										ENDIF
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
										ENDIF
										ryd1_audio_counter = 13// PRINT (RYD1_BD) 4000 1 //RYDER (shouting):Come out you old bastard!	 //(RYD1_BD)
										ryd1_mobile = 4
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 4
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										REQUEST_ANIMATION GANGS
										WHILE NOT HAS_ANIMATION_LOADED GANGS
										WAIT 0 
										ENDWHILE
										
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										STOP_CHAR_FACIAL_TALK ryd1_ryder_ped
										START_CHAR_FACIAL_TALK scplayer 3000
										ENDIF

										ryd1_audio_counter = 14// PRINT (RYD1_BE) 3000 1 //CARL: Shut up!	 //(RYD1_BE)
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
											TASK_LOOK_AT_CHAR scplayer ryd1_ryder_ped 5000
											TASK_PLAY_ANIM scplayer  prtial_gngtlkC	GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
										ENDIF
										ryd1_mobile = 5
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 5
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										STOP_CHAR_FACIAL_TALK scplayer
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
										ENDIF
										ryd1_audio_counter = 15//PRINT (RYD1_BF) 3000 1 //RYDER: He can't stop me!  //(RYD1_BF)
										
										CLEAR_CHAR_TASKS scplayer

										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
											TASK_LOOK_AT_CHAR  ryd1_ryder_ped scplayer 3000
											TASK_PLAY_ANIM ryd1_ryder_ped  prtial_gngtlkB GANGS 4.0 FALSE FALSE FALSE FALSE -1 // latest addition
										ENDIF
										ryd1_mobile = 6
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 6
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
										ENDIF
										ryd1_audio_counter = 16//PRINT (RYD1_CA) 4000 1 //(RYD1_CA)
										IF NOT IS_CHAR_DEAD ryd1_ryder_ped
											CLEAR_LOOK_AT ryd1_ryder_ped
											TASK_SHAKE_FIST ryd1_ryder_ped 
										ENDIF
										ryd1_mobile = 7
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
													
							DEFAULT
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
									   ryd1_text_loop_done = 1	
									ENDIF
								ENDIF
								BREAK
						ENDSWITCH
					
					GOTO ryder1_text_loop2
				ENDIF
	  
	   	SKIP_CUTSCENE_END

		IF NOT IS_CHAR_DEAD ryd1_ryder_ped
			STOP_CHAR_FACIAL_TALK ryd1_ryder_ped			
			STOP_CHAR_FACIAL_TALK scplayer
		ENDIF

		CLEAR_MISSION_AUDIO ryd1_audio_slot1
		CLEAR_MISSION_AUDIO ryd1_audio_slot2

		FREEZE_ONSCREEN_TIMER FALSE

		IF ryd1_locked_car_flag = 0
	   		IF NOT IS_CAR_DEAD ryd1_truck_car
	   		AND NOT IS_CHAR_DEAD ryd1_ryder_ped	   		
	   			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_CHAR_TASKS_IMMEDIATELY ryd1_ryder_ped

	   			IF NOT IS_CHAR_IN_ANY_CAR scplayer
					SET_CHAR_COORDINATES scplayer 2808.1194 -1182.2551 24.3693					
				ELSE
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 2808.1194 -1182.2551 24.3693					
				ENDIF	
	   			SET_CHAR_HEADING scplayer 10.3905

				IF NOT IS_CHAR_IN_ANY_CAR ryd1_ryder_ped
					SET_CHAR_COORDINATES_NO_OFFSET ryd1_ryder_ped 2809.2810 -1181.6093 24.6181					
				ELSE
					WARP_CHAR_FROM_CAR_TO_COORD	ryd1_ryder_ped 2809.2810 -1181.6093 24.6181					
				ENDIF	
	   			SET_CHAR_HEADING ryd1_ryder_ped 30.6396
	   				   										   					   				
   				CLOSE_ALL_CAR_DOORS ryd1_truck_car 
   				LOCK_CAR_DOORS ryd1_truck_car CARLOCK_LOCKED					
				FIX_CAR_DOOR ryd1_truck_car FRONT_LEFT_DOOR
				FIX_CAR_DOOR ryd1_truck_car FRONT_RIGHT_DOOR
				FREEZE_CAR_POSITION ryd1_truck_car TRUE
   				ryd1_locked_car_flag = 1	   				   				   			

	   		ENDIF
		ENDIF

		
		////////////////////////////////////////////////		
		
		IF NOT IS_CHAR_DEAD ryd1_ryder_ped
		CLEAR_CHAR_TASKS_IMMEDIATELY ryd1_ryder_ped
		CLEAR_LOOK_AT ryd1_ryder_ped
		ENDIF
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		CLEAR_LOOK_AT scplayer

	   REMOVE_ANIMATION ON_LOOKERS
	   CLEAR_PRINTS				  			  			  		   			   		   		   	   	   	   	   	   
	   RESTORE_CAMERA_JUMPCUT
	   
	   SWITCH_ENTRY_EXIT burhous TRUE
	   SET_PLAYER_CONTROL player1 ON
	   SWITCH_WIDESCREEN OFF	   	   	   	
	   ADD_BLIP_FOR_COORD  2808.0 -1177.0 26.0 ryd1_door_blip
	   PRINT (RYD1_39) 5000 1// Break down the ~y~door~s~.
	
	   	IF NOT IS_CHAR_DEAD ryd1_ryder_ped
	   		IF IS_GROUP_MEMBER ryd1_ryder_ped Players_Group
				REMOVE_CHAR_FROM_GROUP ryd1_ryder_ped
			ENDIF			
	   		
	   		OPEN_SEQUENCE_TASK ryd1_ryder_leave_seq			
			TASK_GO_STRAIGHT_TO_COORD -1 2829.4116 -1187.1519 23.8422 PEDMOVE_WALK -2						
			TASK_LOOK_AT_COORD -1  2833.2507 -1197.9080 23.3976 2500
			TASK_LOOK_AT_COORD -1  2831.1655 -1169.7920 23.9627 2500  
			TASK_STAND_STILL -1 5000
			TASK_GO_STRAIGHT_TO_COORD -1 2811.4375 -1185.9824 24.2707 PEDMOVE_WALK -2			
			TASK_LOOK_AT_COORD -1  2808.1223 -1190.8842 24.3397	2500
			TASK_LOOK_AT_COORD -1  2808.4304 -1177.4086 24.3621 2500
			TASK_STAND_STILL -1 5000
			SET_SEQUENCE_TO_REPEAT ryd1_ryder_leave_seq 1
			CLOSE_SEQUENCE_TASK  ryd1_ryder_leave_seq
			PERFORM_SEQUENCE_TASK	ryd1_ryder_ped ryd1_ryder_leave_seq
			CLEAR_SEQUENCE_TASK  ryd1_ryder_leave_seq
		ENDIF

	   ryd1_mission_progression_flag = 4
	ENDIF
	
	//////////////////////////////////////
	/// CHECK IF PLAYER  HAS BROKEN IN ///
	//////////////////////////////////////
    
    IF ryd1_mission_progression_flag = 4	   	   																	
		
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2808.0 	-1170.0 	1026.0 	10.0 10.0 10.0 FALSE
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2807.9600 	-1176.2361 	24.3824 1.2   1.2  3.0 FALSE
	   		CLEAR_PRINTS
			REMOVE_BLIP ryd1_door_blip
			SET_PLAYER_CONTROL Player1 OFF
			DO_FADE 500 FADE_OUT
			
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE	    	 			 	
			
			CLEAR_PRINTS
			CLEAR_HELP

			DISPLAY_RADAR FALSE
			DISPLAY_HUD FALSE
			SET_FIXED_CAMERA_POSITION 2806.7925 -1168.7496 1025.8832  0.0 0.0 0.0 // overlooking house
		   	POINT_CAMERA_AT_POINT 2807.3391 -1169.5865 1025.9030 JUMP_CUT  // wide shot
			
			SET_RADAR_ZOOM 90
			SWITCH_WIDESCREEN TRUE
			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			SET_AREA_VISIBLE 8
			LOAD_SCENE	2807.6199 -1169.8799 1024.5781
			SET_AREA_VISIBLE 8
			SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2807.9600 -1176.2361 20.0
			SET_CHAR_COORDINATES scplayer 2807.6199 -1171.8999 1024.5781
			SET_CHAR_HEADING scplayer 0.0
			ryd1_noise_total = 0.0									
			ryd1_noise_bar = 0

			SET_AREA_VISIBLE 8			 			 
			SWITCH_PED_ROADS_OFF 2775.0 -1196.40 0.0 2840.0 -1141.0 40.0
			CLEAR_AREA	2808.73 -1168.32 1024.6 100.0 TRUE
			LOAD_SCENE 2808.73 -1168.32 1024.6
			RESTORE_CAMERA_JUMPCUT

			IF NOT IS_CHAR_DEAD ryd1_ryder_ped
				CLEAR_CHAR_TASKS ryd1_ryder_ped																 
			ENDIF

			/////////////////////////////////////
			/// CREATE THE PICKUPS AND PJ PED ///
			/////////////////////////////////////
			
			REQUEST_MODEL db_ammo
			WHILE NOT HAS_MODEL_LOADED db_ammo
				WAIT 0 
			ENDWHILE

			ryd1_counter = 0
			
			WHILE (ryd1_counter < 7)
				CREATE_OBJECT db_ammo ryd1_gunbox_x[ryd1_counter] ryd1_gunbox_y[ryd1_counter] ryd1_gunbox_z[ryd1_counter] ryd1_gunbox_obj[ryd1_counter]			 
				ADD_BLIP_FOR_OBJECT ryd1_gunbox_obj[ryd1_counter] ryd1_gunbox_blip[ryd1_counter]
				GET_OBJECT_COORDINATES ryd1_gunbox_obj[ryd1_counter] ryd1_temp_x ryd1_temp_y ryd1_temp_z
				
				IF ryd1_temp_z > 700.0
					SET_BLIP_ENTRY_EXIT ryd1_gunbox_blip[ryd1_counter] 2807.8440 -1176.9855 10.0
				ENDIF
				
				ryd1_box_blip_added[ryd1_counter] = 1
				SET_OBJECT_AREA_VISIBLE ryd1_gunbox_obj[ryd1_counter] 8
				SET_OBJECT_AS_STEALABLE ryd1_gunbox_obj[ryd1_counter] TRUE
				SET_OBJECT_COLLISION ryd1_gunbox_obj[ryd1_counter] TRUE
				SET_OBJECT_DYNAMIC ryd1_gunbox_obj[ryd1_counter] TRUE
				ryd1_boxes_left++
				
				ryd1_counter++
			ENDWHILE

			REQUEST_ANIMATION INT_HOUSE
			REQUEST_ANIMATION GANGS

			WHILE NOT HAS_ANIMATION_LOADED INT_HOUSE		
			OR NOT HAS_ANIMATION_LOADED GANGS
			    WAIT 0
			ENDWHILE

			REQUEST_MODEL wmopj
			REQUEST_MODEL CHROMEGUN
			
			WHILE NOT HAS_MODEL_LOADED	wmopj
			OR NOT HAS_MODEL_LOADED CHROMEGUN
			    WAIT 0
			ENDWHILE
		    
			CREATE_CHAR PEDTYPE_MISSION1 wmopj 2816.8572 -1169.0747 1028.1719 ryd1_owner_ped			 
			SET_CHAR_AREA_VISIBLE ryd1_owner_ped 8
			SET_CHAR_SUFFERS_CRITICAL_HITS ryd1_owner_ped TRUE
			 
			SET_CHAR_USES_COLLISION_CLOSEST_OBJECT_OF_TYPE 2816.8572 -1169.0747 1028.1719 4.0 MED_BED_8 FALSE ryd1_owner_ped

			SET_CHAR_DECISION_MAKER ryd1_owner_ped ryd1_decision2
			SET_CHAR_COORDINATES ryd1_owner_ped 2816.8572 -1169.0747 1028.1719
			SET_CHAR_HEADING ryd1_owner_ped 90.0			 
			GIVE_WEAPON_TO_CHAR ryd1_owner_ped WEAPONTYPE_SHOTGUN 3000
			SET_CURRENT_CHAR_WEAPON ryd1_owner_ped   WEAPONTYPE_UNARMED			 
			 
			TASK_PLAY_ANIM ryd1_owner_ped  BED_Loop_R INT_HOUSE 4.0 TRUE FALSE FALSE FALSE -1 // latest addition
						 
			SET_FIXED_CAMERA_POSITION 2806.7925 -1168.7496 1025.8832  0.0 0.0 0.0 // overlooking house
	   		POINT_CAMERA_AT_POINT 2807.3391 -1169.5865 1025.9030 JUMP_CUT  // wide shot
			IF ryd1_inside_house_flag = 0
			    SET_PED_DENSITY_MULTIPLIER 0.0
				SET_CAR_DENSITY_MULTIPLIER 0.0 // normally 1.0			
				CLEAR_AREA 2819.9 -1181.7 1025.15 50.0 TRUE
				ryd1_inside_house_flag = 1
			ENDIF
			
			ryd1_gunbox_picked_up_flag[0] = 1		
			ryd1_number_gunbox_truck++			
			REMOVE_BLIP ryd1_gunbox_blip[0]
			ryd1_box_blip_added[0] = 0
			DELETE_OBJECT ryd1_gunbox_obj[0]			
			ryd1_boxes_left--
			
			DO_FADE 500 FADE_IN
			
			WHILE GET_FADING_STATUS
			    WAIT 0
			ENDWHILE
			
			DISPLAY_HUD TRUE			 
			
			ryd1_mission_progression_flag = 5
		
		  ENDIF
	
	ENDIF  // IF ryd1_mission_progression_flag = 4
	
	
	 //////////////////////////////////////////////////////////////
	 /// FAIL MISSION IF IT'S NO LONGER NIGHT BEFORE BRAKING IN ///
	 //////////////////////////////////////////////////////////////


	 IF ryd1_mission_progression_flag < 7	 				 	 			 			 		
	 	IF ryd1_timer_on_screen	= 0
			GET_TIME_OF_DAY hours minutes
			IF hours > 21 //got til 5 in morning
				ryd1_temp_int = 24 - hours
				ryd1_temp_int += 6
				ryd1_temp_int *= 60000
				ryd1_time_to_daylight = ryd1_temp_int
				ryd1_temp_int = 60000 - minutes 
				ryd1_time_to_daylight += minutes
			ENDIF

			IF hours < 5  //got til 5 in moring
				ryd1_temp_int = 6 - hours
				ryd1_temp_int *= 60000
				ryd1_time_to_daylight = ryd1_temp_int
				ryd1_temp_int = 60000 - minutes 
				ryd1_time_to_daylight += minutes 
			ENDIF
			
				
			DISPLAY_ONSCREEN_TIMER_WITH_STRING  ryd1_time_to_daylight TIMER_DOWN RYD1_41
			ryd1_timer_on_screen = 1												 			 		 		
		ENDIF
	ELSE		
		IF ryd1_timer_on_screen	= 1
			CLEAR_ONSCREEN_TIMER ryd1_time_to_daylight 
			ryd1_timer_on_screen = 0
		ENDIF			
	ENDIF

	////////////////////////////////////////////////////////
	/// HAVE CUT-SCENE SHOWING PLAYER HOW TO STEAL STUFF ///
	////////////////////////////////////////////////////////
	/// & ADD stealth bar //////////////////////////////////
    ////////////////////////////////////////////////////////
	
	IF ryd1_mission_progression_flag = 5
	
		CLEAR_AREA 2819.9 -1181.7 1025.15 50.0 TRUE
				
		FREEZE_ONSCREEN_TIMER TRUE
		SKIP_CUTSCENE_START
				
		CLEAR_HELP
		PRINT_HELP_FOREVER RYD1_92  // You must remain silent at all times.
		
		WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		    WAIT 0 
		ENDWHILE
		
		TIMERA = 0
		ryd1_safety_flag = 0
		WHILE ryd1_safety_flag = 0
			IF TIMERA > 4000
			OR NOT IS_PLAYER_PLAYING player1
				ryd1_safety_flag = 1
			ENDIF
		WAIT 0
		ENDWHILE		
		
		CLEAR_HELP
		
		// The crouch button is currently 86'ed.

		PRINT_HELP_FOREVER RYD1_93  

		WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		    WAIT 0 
		ENDWHILE
		
		TIMERA = 0
		ryd1_safety_flag = 0
		
		WHILE ryd1_safety_flag = 0
			IF TIMERA > 4000
			OR NOT IS_PLAYER_PLAYING player1
				ryd1_safety_flag = 1
			ENDIF
		WAIT 0
		ENDWHILE

		SET_FIXED_CAMERA_POSITION 2815.59 -1169.15 1029.45 0.0 0.0 0.0 // overlooking house
	   	POINT_CAMERA_AT_POINT 2820.48 -1173.33 1028.01 JUMP_CUT  // wide shot
		
		CLEAR_HELP
		PRINT_HELP_FOREVER RYD1_94  // If you make too much noise you will wake the inhabitants of the house.
		WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		WAIT 0 
		ENDWHILE
		
		TIMERA = 0
		ryd1_safety_flag = 0
		ryd1_sfx_counter  = 3 
		ryd1_sfx_playing  = 0
		ryd1_sfx_played   = 0
		
		WHILE ryd1_safety_flag = 0
			
			IF NOT ryd1_sfx_counter = 0
				IF ryd1_sfx_playing = 0
					IF HAS_MISSION_AUDIO_LOADED 3
						CLEAR_MISSION_AUDIO 3
					ENDIF
					ryd1_sfx_playing = 1
				ENDIF

				IF ryd1_sfx_playing = 1
					LOAD_MISSION_AUDIO 3 ryd1_sfx[ryd1_sfx_counter]	 // audio fx to be used
					ryd1_sfx_playing = 2
				ENDIF

				IF ryd1_sfx_playing = 2
				 	IF HAS_MISSION_AUDIO_LOADED 3
						PLAY_MISSION_AUDIO 3				
						ryd1_sfx_playing = 3
					ENDIF
				ENDIF

				IF ryd1_sfx_playing = 3
					IF HAS_MISSION_AUDIO_FINISHED 3								
						ryd1_sfx_counter = 0
						ryd1_sfx_playing = 0			
					ENDIF
				ENDIF
			ENDIF
			
			IF TIMERA > 4000
			OR NOT IS_PLAYER_PLAYING player1
				ryd1_safety_flag = 1
			ENDIF
		
		WAIT 0
		ENDWHILE
		
		PRINT_HELP_FOREVER RYD1_95  // Make sure the noise bar does not reach maximum.
	    
		WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
		    WAIT 0 
		ENDWHILE
	    
		TIMERA = 0
		ryd1_safety_flag = 0
		ryd1_sfx_counter = 3
		ryd1_sfx_playing = 0

		WHILE ryd1_safety_flag = 0
			
			IF NOT ryd1_sfx_counter = 0
				IF ryd1_sfx_playing = 0
					IF HAS_MISSION_AUDIO_LOADED 3
						CLEAR_MISSION_AUDIO 3
					ENDIF
					ryd1_sfx_playing = 1
				ENDIF

				IF ryd1_sfx_playing = 1
					LOAD_MISSION_AUDIO 3 ryd1_sfx[ryd1_sfx_counter]	 // audio fx to be used
					ryd1_sfx_playing = 2
				ENDIF

				IF ryd1_sfx_playing = 2
				 	IF HAS_MISSION_AUDIO_LOADED 3
						PLAY_MISSION_AUDIO 3				
						ryd1_sfx_playing = 3
					ENDIF
				ENDIF

				IF ryd1_sfx_playing = 3
					IF HAS_MISSION_AUDIO_FINISHED 3								
						ryd1_sfx_counter = 0
						ryd1_sfx_playing = 0			
					ENDIF
				ENDIF
			ENDIF
			
			
			IF TIMERA > 4000
			OR NOT IS_PLAYER_PLAYING player1
				ryd1_safety_flag = 1
			ENDIF
		WAIT 0
		ENDWHILE

		SKIP_CUTSCENE_END
		CLEAR_MISSION_AUDIO 3
		FREEZE_ONSCREEN_TIMER FALSE	    		
		CLEAR_HELP
		CLEAR_PRINTS
		
		IF NOT IS_CAR_DEAD ryd1_truck_car
		AND NOT IS_CHAR_DEAD ryd1_ryder_ped
			CLEAR_AREA 2812.9 -1181.13 1024.396 100.0 TRUE								
			SET_CAR_COORDINATES ryd1_truck_car 2811.8457 -1183.2910 24.2764 
			SET_CAR_HEADING ryd1_truck_car 246.9816
			OPEN_CAR_DOOR ryd1_truck_car REAR_LEFT_DOOR
   		 	OPEN_CAR_DOOR ryd1_truck_car REAR_RIGHT_DOOR
			CLEAR_CHAR_TASKS ryd1_ryder_ped			
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ryd1_truck_car -1.5 -4.0 0.0 ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z
			GET_CAR_HEADING ryd1_truck_car ryd1_truck_heading
			ryd1_truck_heading -= 180.0
			
			IF ryd1_truck_heading < 180.0
			   ryd1_truck_heading += 360.0
			ENDIF  
			
			CLEAR_CHAR_TASKS_IMMEDIATELY ryd1_ryder_ped
			SET_CHAR_COORDINATES ryd1_ryder_ped 2811.1216 -1186.5492 24.2718//ryd1_passenger_x ryd1_passenger_y -101.0			
			SET_CHAR_HEADING ryd1_ryder_ped 17.5866//ryd1_truck_heading
			GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ryd1_truck_car 0.0 -4.0 0.0 ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z						
			
			OPEN_SEQUENCE_TASK ryd1_ryder_leave_seq			
			TASK_GO_STRAIGHT_TO_COORD -1 2829.4116 -1187.1519 23.8422 PEDMOVE_WALK -2						
			TASK_LOOK_AT_COORD -1  2833.2507 -1197.9080 23.3976 2500
			TASK_LOOK_AT_COORD -1  2831.1655 -1169.7920 23.9627 2500  
			TASK_STAND_STILL -1 5000
			TASK_GO_STRAIGHT_TO_COORD -1 2811.4375 -1185.9824 24.2707 PEDMOVE_WALK -2			
			TASK_LOOK_AT_COORD -1  2808.1223 -1190.8842 24.3397	2500
			TASK_LOOK_AT_COORD -1  2808.4304 -1177.4086 24.3621 2500
			TASK_STAND_STILL -1 5000
			SET_SEQUENCE_TO_REPEAT ryd1_ryder_leave_seq 1
			CLOSE_SEQUENCE_TASK  ryd1_ryder_leave_seq
			PERFORM_SEQUENCE_TASK	ryd1_ryder_ped ryd1_ryder_leave_seq
			CLEAR_SEQUENCE_TASK  ryd1_ryder_leave_seq
		ENDIF
		
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
		RESTORE_CAMERA_JUMPCUT
		REMOVE_ANIMATION GANGS
		
		ryd1_noise_total = 0.0									
		ryd1_noise_bar = 0									
		
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		
		/////////////////////
		/// add noise bar ///
		/////////////////////
		
		IF ryd1_noise_bar_added_flag = 0
			ryd1_noise_total = 0.0									
			ryd1_noise_bar = 0
			ryd1_noise_bar = 0
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING ryd1_noise_bar COUNTER_DISPLAY_BAR (GUNS1_5)
			DISPLAY_RADAR FALSE
			ryd1_noise_bar_added_flag = 1
			TIMERB = 0
		ENDIF
		
		ryd1_inside_house_flag = 1
		ryd1_audio_counter	   = 0
		ryd1_audio_slot1 	   = 1
		ryd1_audio_slot2 	   = 2
		ryd1_audio_playing	   = 0
		ryd1_text_loop_done	   = 0
		ryd1_mobile			   = 0
		ryd1_text_timer_diff   = 0
		ryd1_text_timer_end    = 0
		ryd1_text_timer_start  = 0
		ryd1_ahead_counter	   = 0
	    ryd1_mission_progression_flag = 6
		ryd1_noise_bar         = 0
		ryd1_noise_total       = 0.0
	
	ENDIF

	 
/////////////////////////////////////
///////// UPDATE NOISE BAR //////////
/////////////////////////////////////

IF ryd1_owner_awake_flag = 0
AND ryd1_mission_progression_flag < 7
	
	IF ryd1_crate_message_shown = 0
		IF ryd1_mission_progression_flag = 6
			IF TIMERB > 2500
				CLEAR_PRINTS
				PRINT (RYD1_40) 5000 1 //Find a crate.
				PRINT_WITH_NUMBER (RYD1_97) 3 5000 1 //~s~You need to steal at least ~1~ crates of guns.
				ryd1_crate_message_shown = 1
			ENDIF
		ENDIF
	ENDIF

	IF ryd1_inside_house_flag = 0
		IF ryd1_noise_bar_added_flag = 0
			GET_AREA_VISIBLE ryd1_area_name
			
			IF ryd1_area_name = 8												
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2807.6199 -1171.8999 1024.5776 50.0 50.0 50.0 FALSE

					IF ryd1_owner_awake_flag = 0
						ryd1_noise_bar = 0
						ryd1_noise_total = 0.0
						IF ryd1_noise_bar_added_flag = 0
						DISPLAY_ONSCREEN_COUNTER_WITH_STRING ryd1_noise_bar COUNTER_DISPLAY_BAR (GUNS1_5)
						SET_ONSCREEN_COUNTER_FLASH_WHEN_FIRST_DISPLAYED ryd1_noise_bar FALSE						
						DISPLAY_RADAR FALSE
						//SET_DARKNESS_EFFECT TRUE -1
						ryd1_noise_bar_added_flag = 1
						ENDIF						
					ENDIF
				ENDIF
			
			ELSE
				IF ryd1_time_to_daylight = 0
					
					IF NOT IS_CHAR_DEAD ryd1_owner_ped										
						CLEAR_CHAR_TASKS ryd1_owner_ped				
						SET_CHAR_COORDINATES ryd1_owner_ped	2806.9963 -1169.8094 1024.6120
						SET_CHAR_HEADING ryd1_owner_ped	193.7598
						SET_CHAR_DECISION_MAKER ryd1_owner_ped ryd1_decision
						REMOVE_DECISION_MAKER ryd1_decision2
						SET_CHAR_RELATIONSHIP ryd1_owner_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1						
						OPEN_SEQUENCE_TASK ryd1_owner_awake_seq
						TASK_KILL_CHAR_ON_FOOT -1 scplayer					
						CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
						PERFORM_SEQUENCE_TASK ryd1_owner_ped ryd1_owner_awake_seq
						CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq																					
					ENDIF																									

					ryd1_temp_int  = 0
					WHILE ryd1_temp_int < 7		
						IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_temp_int]
							SET_OBJECT_AS_STEALABLE ryd1_gunbox_obj[ryd1_temp_int] FALSE 	
						ENDIF			 			  
						IF ryd1_box_blip_added[ryd1_temp_int] = 1
							REMOVE_BLIP ryd1_gunbox_blip[ryd1_temp_int]
							ryd1_box_blip_added[ryd1_temp_int] = 0
						ENDIF				  			  		 
						ryd1_temp_int++
					ENDWHILE

					CLEAR_ONSCREEN_TIMER ryd1_time_to_daylight
					CLEAR_ONSCREEN_COUNTER  ryd1_noise_bar
					DISPLAY_RADAR TRUE
					IF ryd1_number_gunbox_truck > 3
						CLEAR_ONSCREEN_TIMER ryd1_time_to_daylight
						// play the escape scene
						ryd1_mission_progression_flag = 7
					
					ELSE
						////////////////////////////////////////////////
						/// have cut-scene of ryder fucking off here ///
						////////////////////////////////////////////////											
						
						GOSUB check_player_is_safe
            			IF player_is_completely_safe = 1						
							IF NOT IS_CAR_DEAD ryd1_truck_car
							AND NOT IS_CHAR_DEAD ryd1_ryder_ped
									
								SET_PLAYER_CONTROL Player1 OFF
								SWITCH_WIDESCREEN ON
								CLEAR_HELP
								CLEAR_PRINTS
								LOCK_CAR_DOORS ryd1_truck_car CARLOCK_UNLOCKED
								FREEZE_CAR_POSITION ryd1_truck_car FALSE
								LOAD_SCENE	2807.8579 -1188.1034 25.4725
								SET_FIXED_CAMERA_POSITION 2807.8579 -1188.1034 25.4725  0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 2808.7920 -1187.7477 25.4449 JUMP_CUT
								
								FLUSH_ROUTE						  			
								
								EXTEND_ROUTE 2832.2573 -1182.7083 23.7924  
								EXTEND_ROUTE 2840.8108 -1211.1450 22.6279 
								
								OPEN_SEQUENCE_TASK ryd1_owner_awake_seq
								//TASK_GO_STRAIGHT_TO_COORD -1 2807.6262 -1174.2717 1024.5781 PEDMOVE_WALK  10000
								IF ryd1_mission_progression_flag > 5
									TASK_GO_STRAIGHT_TO_COORD -1 2812.0908 -1186.1982 24.2658 PEDMOVE_RUN 10000
								ENDIF
								
								TASK_ENTER_CAR_AS_DRIVER -1 ryd1_truck_car 20000
								TASK_DRIVE_POINT_ROUTE_ADVANCED -1 ryd1_truck_car 25.0 MODE_ACCURATE 0 DRIVINGMODE_SLOWDOWNFORCARS
								CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
								PERFORM_SEQUENCE_TASK ryd1_ryder_ped ryd1_owner_awake_seq
								CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
								
								ryd1_audio_counter = 0	 
								ryd1_audio_slot1   = 1
								ryd1_audio_slot2   = 2
								ryd1_audio_playing = 0																								
								ryd1_ahead_counter = 0
								ryd1_mobile	       = 0								
								ryd1_safety_flag   = 0
								 
								 TIMERA = 0
								 WHILE ryd1_safety_flag = 0
																																													
									IF NOT ryd1_audio_counter = 0
										IF ryd1_audio_playing = 0
											IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
												CLEAR_MISSION_AUDIO ryd1_audio_slot2
											ENDIF
											ryd1_audio_playing = 1
										ENDIF

										IF ryd1_audio_playing = 1
											LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
											ryd1_audio_playing = 2
										ENDIF

										IF ryd1_audio_playing = 2
										 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
												PLAY_MISSION_AUDIO ryd1_audio_slot1
												PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
												ryd1_audio_playing = 3
											ENDIF
										ENDIF

										IF ryd1_audio_playing = 3
											IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
												CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
												IF ryd1_audio_slot1 = 1
													ryd1_audio_slot1 = 2
													ryd1_audio_slot2 = 1
												ELSE
													ryd1_audio_slot1 = 1
													ryd1_audio_slot2 = 2
												ENDIF
												ryd1_audio_counter = 0
												ryd1_audio_playing = 0
											ELSE
												IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
													IF ryd1_audio_counter < 76
														ryd1_ahead_counter = ryd1_audio_counter + 1
														LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
									
									
									SWITCH ryd1_mobile
										CASE 0
											IF ryd1_audio_playing = 0																		
												IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
													START_CHAR_FACIAL_TALK ryd1_ryder_ped 5000
												ENDIF
												ryd1_audio_counter = 81//Oh shit, I'm gone!
												ryd1_mobile = 1
												GET_GAME_TIMER ryd1_text_timer_start
											ENDIF
											BREAK																							
										CASE 1
											GET_GAME_TIMER ryd1_text_timer_end
											ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
											IF ryd1_text_timer_diff > 1000
												IF ryd1_audio_playing = 0
													IF NOT IS_CHAR_DEAD ryd1_ryder_ped
														STOP_CHAR_FACIAL_TALK ryd1_ryder_ped										
													ENDIF
													START_CHAR_FACIAL_TALK scplayer 5000
													ryd1_audio_counter = 77//Ryder! Buster!
													ryd1_mobile = 2
													GET_GAME_TIMER ryd1_text_timer_start
												ENDIF
											ENDIF
											BREAK
										
										
										DEFAULT
											GET_GAME_TIMER ryd1_text_timer_end
											ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
											IF ryd1_text_timer_diff > 1000												
												IF ryd1_audio_playing = 0
												STOP_CHAR_FACIAL_TALK scplayer
												ENDIF																																		
											ENDIF
											BREAK
									ENDSWITCH																			
					
									
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										GET_SCRIPT_TASK_STATUS ryd1_ryder_ped PERFORM_SEQUENCE_TASK ryd1_task_status
									ELSE
										ryd1_safety_flag = 1
									ENDIF
									
									IF TIMERA > 15000
									OR ryd1_task_status = FINISHED_TASK
										STOP_CHAR_FACIAL_TALK scplayer
										ryd1_safety_flag = 1
									ENDIF
								 WAIT 0 
								 ENDWHILE

							ENDIF
						ENDIF
					
						CLEAR_PRINTS
						CLEAR_CHAR_TASKS scplayer		 				 		
						RESTORE_CAMERA_JUMPCUT												
						//PRINT (RYD1_43) 5000 1 //~r~You're not going to be able to get the guns now that he's awake!
						ALTER_WANTED_LEVEL player1 2												
						
						IF IS_PLAYER_WEARING player1 CLOTHES_TEX_EXTRA1	balaclava
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
						PRINT (RYD1_96) 5000 1//You did not manage to steal enough crates.	
						
						GOTO mission_g1_failed
						ENDIF
					ryd1_noise_bar_added_flag = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


IF ryd1_mission_progression_flag < 7
	GET_CHAR_COORDINATES scplayer ryd1_player_x ryd1_player_y ryd1_player_z	
	IF ryd1_player_z > 900.0
		IF ryd1_dark_active = 0
			GET_AREA_VISIBLE ryd1_area_name
			IF ryd1_area_name  = 8
				ryd1_dark_active = 1
			ENDIF
		ENDIF
	ELSE
		IF ryd1_dark_active = 1
			ryd1_dark_active = 0
		ENDIF
	ENDIF
ENDIF

IF ryd1_mission_progression_flag > 2
IF ryd1_mission_progression_flag < 7
IF ryd1_noise_bar_added_flag = 1
IF ryd1_owner_awake_flag = 0
	
	GET_AREA_VISIBLE ryd1_area_name
	IF ryd1_area_name  = 0		 	
	 	IF ryd1_inside_house_flag = 1
		 	CLEAR_MISSION_AUDIO 3
		 	ryd1_sfx_counter  = 3 
			ryd1_sfx_playing  = 0
			
		 	SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0 // normally 1.0
			IF ryd1_player_holding_box_flag = 1				
				IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]
					SET_OBJECT_AREA_VISIBLE ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying] 0
				ENDIF
			ENDIF
			IF ryd1_noise_bar_added_flag = 1
				CLEAR_ONSCREEN_COUNTER	ryd1_noise_bar					
				DISPLAY_RADAR TRUE
				ryd1_noise_bar_added_flag = 0
			ENDIF
											
			ryd1_inside_house_flag = 0
			SET_RADAR_ZOOM 0
		ENDIF
										
	   	IF ryd1_noise_bar > 11
			ryd1_noise_bar-= 10	   	
	   		IF ryd1_noise_total > 11.0
	   		ryd1_noise_total-= 10.0
			ELSE
			ryd1_noise_total = 0.0
			ENDIF
	   	ELSE
			ryd1_noise_total = 0.0
			ryd1_noise_bar = 0
	   	ENDIF 
	
	ENDIF  //ryd1_area_name  != 0									
		
	IF ryd1_area_name = 8
												
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2809.0 -1169.0 1026.0 30.0 30.0 30.0 FALSE																			
			IF NOT ryd1_sfx_counter = 0
				IF ryd1_sfx_playing = 0
					IF HAS_MISSION_AUDIO_LOADED 3
						CLEAR_MISSION_AUDIO 3
					ENDIF
					ryd1_sfx_playing = 1
				ENDIF

				IF ryd1_sfx_playing = 1
					LOAD_MISSION_AUDIO 3 ryd1_sfx[ryd1_sfx_counter]	 // audio fx to be used
					ryd1_sfx_playing = 2
				ENDIF

				IF ryd1_sfx_playing = 2
				 	IF HAS_MISSION_AUDIO_LOADED 3
						IF NOT IS_CHAR_DEAD ryd1_owner_ped
							ATTACH_MISSION_AUDIO_TO_CHAR 3	ryd1_owner_ped 
							PLAY_MISSION_AUDIO 3
						ENDIF				
						ryd1_sfx_playing = 3
					ENDIF
				ENDIF

				IF ryd1_sfx_playing = 3
					IF HAS_MISSION_AUDIO_FINISHED 3								
						ryd1_sfx_counter = 0
						ryd1_sfx_playing = 0			
						TIMERA = 0
					ENDIF
				ENDIF
			ELSE
				IF TIMERA > 3000
					ryd1_sfx_counter  = 3 
				ENDIF
			ENDIF
																									
			
			IF NOT IS_CHAR_DEAD ryd1_owner_ped				
			   	GET_CHAR_COORDINATES scplayer ryd1_player_x ryd1_player_y ryd1_player_z				   	  
				IF ryd1_allowed_to_check_sound = 1 				
					GET_SOUND_LEVEL_AT_COORDS -1 ryd1_player_x ryd1_player_y ryd1_player_z ryd1_noise_float
					ryd1_noise_float *= 1.6
				ELSE
					IF TIMERB > 500
						ryd1_allowed_to_check_sound = 1					
					ENDIF
					ryd1_noise_bar = 0
					ryd1_noise_total = 0.0
				ENDIF 				
			ENDIF

			IF ryd1_inside_house_flag = 0								
				SET_PED_DENSITY_MULTIPLIER 0.0
				SET_CAR_DENSITY_MULTIPLIER 0.0 // normally 1.0			
				CLEAR_AREA 2819.9 -1181.7 1025.15 50.0 TRUE
				
				IF ryd1_player_holding_box_flag = 1
					IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]
						SET_OBJECT_AREA_VISIBLE ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying] 8
					ENDIF
				ENDIF
				SET_RADAR_ZOOM 90												
				ryd1_inside_house_flag = 1
			ENDIF
			
						
			IF  ryd1_noise_float > 20.0
				ryd1_noise_float -= 20.0				
				/// additional effectors to sound ///							
				ryd1_noise_total += ryd1_noise_float
			ENDIF
			
			ryd1_noise_total *= 0.99									
			ryd1_noise_bar =# ryd1_noise_total
				
			IF ryd1_noise_bar >= 100
				ryd1_noise_bar = 100
				IF ryd1_timer_started = 0										
					GET_GAME_TIMER ryd1_timer_start
					ryd1_timer_started = 1
				ENDIF
			ELSE		
				IF ryd1_noise_bar < 0
					ryd1_noise_bar = 0
				ENDIF
				//ryd1_timer_started = 0
			ENDIF																
				
			//////////////////////////////////////////
			///  owner being attacked and gunfire ///
			//////////////////////////////////////////
			IF NOT IS_CHAR_DEAD ryd1_owner_ped										 									
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR ryd1_owner_ped scplayer
				OR LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer ryd1_owner_ped 1.0 1.0 2.0  FALSE		   							   			   	   
			   	   ryd1_timer_started = 2
			   	   ryd1_noise_bar = 100
				ENDIF
			ENDIF
		ENDIF
	ENDIF //ryd1_area_name  = 8
	
	
	IF ryd1_timer_started = 1		

		GET_GAME_TIMER ryd1_timer_current
		ryd1_time_elapsed = ryd1_timer_current  - ryd1_timer_start 
		IF ryd1_time_elapsed > 1500	// may need to increase this		
			// can have "restless" old man here if forced to put it in  ( ; /\ ; ) //
			IF ryd1_restless_scene_played = 0
				
				CLEAR_MISSION_AUDIO 3
				ryd1_sfx_counter = 0
				ryd1_sfx_playing  = 0

				CLEAR_PRINTS
				SET_PLAYER_CONTROL Player1 OFF
				SWITCH_WIDESCREEN ON
				SET_FIXED_CAMERA_POSITION 2816.0957 -1169.5221 1029.0809   0.0 0.0 0.0 // overlooking bedroom
				POINT_CAMERA_AT_POINT 2817.0930 -1169.5881 1029.0551 JUMP_CUT

				ryd1_audio_counter	  = 0
				ryd1_audio_slot1 	  = 1
				ryd1_audio_slot2 	  = 2
				ryd1_audio_playing	  = 0
				ryd1_text_loop_done	  = 0
				ryd1_mobile			  = 0
				ryd1_text_timer_diff  = 0
				ryd1_text_timer_end   = 0
				ryd1_text_timer_start = 0
				ryd1_ahead_counter	  = 0
				
				ryder1_text_loop5:
				WAIT 0 

				IF ryd1_text_loop_done = 0
					
					IF NOT ryd1_audio_counter = 0
						IF ryd1_audio_playing = 0
							IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
								CLEAR_MISSION_AUDIO ryd1_audio_slot2
							ENDIF
							ryd1_audio_playing = 1
						ENDIF

						IF ryd1_audio_playing = 1
							LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
							ryd1_audio_playing = 2
						ENDIF

						IF ryd1_audio_playing = 2
						 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
								PLAY_MISSION_AUDIO ryd1_audio_slot1
								PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
								ryd1_audio_playing = 3
							ENDIF
						ENDIF

						IF ryd1_audio_playing = 3
							IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
								CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
								IF ryd1_audio_slot1 = 1
									ryd1_audio_slot1 = 2
									ryd1_audio_slot2 = 1
								ELSE
									ryd1_audio_slot1 = 1
									ryd1_audio_slot2 = 2
								ENDIF
								ryd1_audio_counter = 0
								ryd1_audio_playing = 0
							ELSE
								IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
									IF ryd1_audio_counter < 76
										ryd1_ahead_counter = ryd1_audio_counter + 1
										LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF

					IF NOT IS_MESSAGE_BEING_DISPLAYED
					SWITCH ryd1_mobile
							CASE 0
								IF ryd1_audio_playing = 0																		
									IF NOT IS_CHAR_DEAD ryd1_owner_ped										
										START_CHAR_FACIAL_TALK ryd1_owner_ped 5000
									ENDIF
									ryd1_audio_counter = 36 // RYD1_DB Get off my ridge, you viet cong bastards!
									ryd1_mobile = 1
									GET_GAME_TIMER ryd1_text_timer_start
								ENDIF
								BREAK													
							DEFAULT
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF NOT IS_CHAR_DEAD ryd1_owner_ped
										STOP_CHAR_FACIAL_TALK ryd1_owner_ped										
									ENDIF
									IF ryd1_audio_playing = 0
									   ryd1_text_loop_done = 1	
									ENDIF
								ENDIF
								BREAK
						ENDSWITCH
					ENDIF
					
					GOTO ryder1_text_loop5
				ENDIF


				PRINT (GUNS134) 3000 1  // ~s~You're making too much noise, you have to sneak!
				RESTORE_CAMERA_JUMPCUT
				SET_PLAYER_CONTROL Player1 ON
				SWITCH_WIDESCREEN OFF
				ryd1_timer_started = 0								
				ryd1_noise_bar = 0
				ryd1_noise_total= 0.0
				ryd1_restless_scene_played = 1
				ryd1_sfx_counter  = 3 
			ELSE
				ryd1_timer_started = 2
			ENDIF
		ENDIF	
	ENDIF


	// print the apporpriate message for current stealth level
	
	IF ryd1_timer_started = 2//ryd1_noise_bar = 100
	OR ryd1_time_to_daylight = 0 
			
			CLEAR_MISSION_AUDIO 3
			ryd1_sfx_playing  = 0
			ryd1_sfx_counter = 0

			ryd1_temp_int  = 0
			WHILE ryd1_temp_int < 7		
				IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_temp_int]
					SET_OBJECT_AS_STEALABLE ryd1_gunbox_obj[ryd1_temp_int] FALSE 	
				ENDIF			 			  
				IF ryd1_box_blip_added[ryd1_temp_int] = 1
					REMOVE_BLIP ryd1_gunbox_blip[ryd1_temp_int]
					ryd1_box_blip_added[ryd1_temp_int] = 0
				ENDIF				  			  		 
			 ryd1_temp_int++
			 ENDWHILE
		
										  										
				IF ryd1_area_name  = 8
					IF NOT IS_CHAR_DEAD ryd1_owner_ped
						GET_CHAR_HEALTH ryd1_owner_ped ryd1_temp_int
						IF ryd1_temp_int < 20
							SET_CHAR_HEALTH ryd1_owner_ped 20
						ENDIF
						CLEAR_MISSION_AUDIO 3
						CLEAR_CHAR_TASKS_IMMEDIATELY ryd1_owner_ped
						FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION ryd1_owner_ped FALSE				
						GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 1.0 1.0 0.0 ryd1_driver_x ryd1_driver_y ryd1_driver_z				
						CLEAR_AREA 2808.1228 -1172.8232 1024.6120 10.0 TRUE
										
						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer ryd1_owner_ped 1.0 1.0 2.0  FALSE		   							   			   	   
			   	   		    SET_CHAR_COORDINATES scplayer 2817.2288 -1166.1100 1028.1719 
							SET_CHAR_HEADING scplayer  94.7698
						ENDIF

						SET_CHAR_COORDINATES ryd1_owner_ped 2815.9609 -1169.3920 1028.1720
						SET_CHAR_HEADING ryd1_owner_ped  14.1



						SET_FIXED_CAMERA_POSITION 2816.2422 -1168.3579 1029.4849   0.0 0.0 0.0 // overlooking bedroom
				   		POINT_CAMERA_AT_POINT 2815.6743 -1169.1440 1029.7288 JUMP_CUT
						  
						OPEN_SEQUENCE_TASK ryd1_owner_awake_seq				 				
						TASK_SCRATCH_HEAD -1					 												
						TASK_SHAKE_FIST -1
						CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
						PERFORM_SEQUENCE_TASK ryd1_owner_ped ryd1_owner_awake_seq
						CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq

						SET_PLAYER_CONTROL player1 OFF						
						CLEAR_CHAR_TASKS scplayer
						SWITCH_WIDESCREEN ON
						CLEAR_PRINTS
						CLEAR_HELP
						SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
						
						IF  ryd1_number_gunbox_truck < 4		
					   		SET_CHAR_COORDINATES scplayer 2807.5254 -1167.0757 1024.5781 
							SET_CHAR_HEADING scplayer 169.2411
						ENDIF

						//TASK_GO_TO_COORD_ANY_MEANS scplayer  2806.9963 -1169.2094 24.6120 PEDMOVE_RUN -1
						SET_CURRENT_CHAR_WEAPON ryd1_owner_ped   WEAPONTYPE_SHOTGUN					

						SKIP_CUTSCENE_START
						CLEAR_PRINTS								
						
						IF ryd1_time_to_daylight > 0
			  		   		CLEAR_MISSION_AUDIO 3
			  		   		IF NOT IS_CHAR_DEAD ryd1_owner_ped
						 	STOP_CHAR_FACIAL_TALK ryd1_owner_ped
						 	START_CHAR_FACIAL_TALK ryd1_owner_ped	 4000									
							ryd1_audio_counter = 39
							ENDIF
			  		   		//PRINT (GUNS111) 4000 1 //I heard you for sure that time, I'm phoning the cops
			  		   	ELSE
					   		PRINT (RYD1_36) 4000 1 //SMITH: Morning already.. wait a minute something doesn't feel right here!						   
					   		ryd1_sfx_playing = 0
					   		ryd1_sfx_counter = 1
					   		
					   	ENDIF
					   	
					   	
						///// audio entry required
			  		    
						ryd1_audio_slot1 	  = 1
						ryd1_audio_slot2 	  = 2
						ryd1_audio_playing	  = 0
						ryd1_text_loop_done	  = 0
						ryd1_mobile			  = 0
						ryd1_text_timer_diff  = 0
						ryd1_text_timer_end   = 0
						ryd1_text_timer_start = 0						
					   					
						ryd1_safety_flag = 0
						
						TIMERA = 0
						WHILE ryd1_safety_flag = 0
						  
							IF NOT ryd1_sfx_counter = 0
								IF ryd1_sfx_playing = 0
									IF HAS_MISSION_AUDIO_LOADED 3
										CLEAR_MISSION_AUDIO 3
									ENDIF
									ryd1_sfx_playing = 1
								ENDIF

								IF ryd1_sfx_playing = 1
									LOAD_MISSION_AUDIO 3 ryd1_sfx[ryd1_sfx_counter]	 // audio fx to be used
									ryd1_sfx_playing = 2
								ENDIF

								IF ryd1_sfx_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED 3
										PLAY_MISSION_AUDIO 3				
										ryd1_sfx_playing = 3
									ENDIF
								ENDIF

								IF ryd1_sfx_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED 3
									OR TIMERA > 3000								
										CLEAR_MISSION_AUDIO 3
										
										
										ryd1_sfx_counter = 0
										ryd1_sfx_playing = 0			
									ENDIF
								ENDIF							
							ENDIF


							IF NOT ryd1_audio_counter = 0
								IF ryd1_audio_playing = 0
									IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
										CLEAR_MISSION_AUDIO ryd1_audio_slot2
									ENDIF
									ryd1_audio_playing = 1
								ENDIF

								IF ryd1_audio_playing = 1
									LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
									ryd1_audio_playing = 2
								ENDIF

								IF ryd1_audio_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
										PLAY_MISSION_AUDIO ryd1_audio_slot1
										PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
										ryd1_audio_playing = 3
									ENDIF
								ENDIF

								IF ryd1_audio_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
										CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
										IF NOT IS_CHAR_DEAD ryd1_owner_ped
			 								STOP_CHAR_FACIAL_TALK ryd1_owner_ped
										ENDIF
										IF ryd1_audio_slot1 = 1
											ryd1_audio_slot1 = 2
											ryd1_audio_slot2 = 1
										ELSE
											ryd1_audio_slot1 = 1
											ryd1_audio_slot2 = 2
										ENDIF
										ryd1_audio_counter = 0
										ryd1_audio_playing = 0																						
									ENDIF
								ENDIF
							ENDIF
			  		   						  		   
				  		  
						  IF  IS_CHAR_DEAD ryd1_owner_ped
							  ryd1_safety_flag = 1	 			  			  
						  ELSE
							  GET_SCRIPT_TASK_STATUS ryd1_owner_ped PERFORM_SEQUENCE_TASK  ryd1_task_status
						  	  IF  ryd1_task_status  = FINISHED_TASK 						   						   							  		   
						  		   ryd1_safety_flag = 1
							  ENDIF
						  ENDIF

						  IF TIMERA > 15000
						  	 ryd1_safety_flag = 1

						  ENDIF
						WAIT 0
						ENDWHILE
					
						SKIP_CUTSCENE_END
				
						CLEAR_MISSION_AUDIO 1
						CLEAR_MISSION_AUDIO 2
						CLEAR_MISSION_AUDIO 3

						CLEAR_PRINTS 				 				
						HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
						REMOVE_ANIMATION GANGS
						ryd1_player_holding_box_flag = 0 										
					
						IF NOT IS_CHAR_DEAD ryd1_owner_ped
							STOP_CHAR_FACIAL_TALK ryd1_owner_ped 
							IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2811.8816  -1167.5698  1029.2197  9.1000  7.2000  5.0000  FALSE
							AND ryd1_player_z > 1029.0
							// player and smith both upstairs														
								OPEN_SEQUENCE_TASK ryd1_owner_awake_seq				 							
								TASK_LOOK_AT_CHAR -1 scplayer 5000
								TASK_GOTO_CHAR -1 scplayer -1 5.0
								TASK_KILL_CHAR_ON_FOOT -1 scplayer 															
								CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
								PERFORM_SEQUENCE_TASK ryd1_owner_ped ryd1_owner_awake_seq
								CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
							ELSE
								SET_CHAR_COORDINATES ryd1_owner_ped 2804.3247 -1162.2542 1028.1797 
								SET_CHAR_HEADING ryd1_owner_ped 180.0
								OPEN_SEQUENCE_TASK ryd1_owner_awake_seq				 							
								TASK_LOOK_AT_CHAR -1 scplayer 10000
								TASK_GO_TO_COORD_WHILE_SHOOTING -1 2803.9768 -1168.1567 1025.3750   PEDMOVE_RUN 0.5 0.0 scplayer
								TASK_GO_TO_COORD_WHILE_SHOOTING -1 2806.9963 -1169.8094 1024.6120   PEDMOVE_RUN 0.5 0.0 scplayer
								TASK_KILL_CHAR_ON_FOOT -1 scplayer								
								CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
								PERFORM_SEQUENCE_TASK ryd1_owner_ped ryd1_owner_awake_seq
								CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
							ENDIF
						ENDIF

						CLEAR_CHAR_TASKS scplayer
						CLEAR_LOOK_AT scplayer
						
						IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							CLEAR_CHAR_TASKS ryd1_ryder_ped
							CLEAR_LOOK_AT ryd1_ryder_ped
						ENDIF
						CLEAR_AREA 2806.9963 -1169.2094 1024.6120 50.0 TRUE
					
						IF NOT IS_CHAR_DEAD ryd1_ryder_ped
						AND NOT IS_CAR_DEAD ryd1_truck_car
							CLEAR_CHAR_TASKS ryd1_ryder_ped			
							GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ryd1_truck_car -1.5 -4.0 0.0 ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z
							GET_CAR_HEADING ryd1_truck_car ryd1_truck_heading
							ryd1_truck_heading -= 180.0
							IF ryd1_truck_heading < 180.0
							   ryd1_truck_heading += 360.0
							ENDIF  
							CLEAR_CHAR_TASKS_IMMEDIATELY ryd1_ryder_ped
							SET_CHAR_COORDINATES ryd1_ryder_ped 2811.1216 -1186.5492 24.2718//ryd1_passenger_x ryd1_passenger_y -101.0			
							SET_CHAR_HEADING ryd1_ryder_ped 17.5866//ryd1_truck_heading
						ENDIF
												
						IF NOT IS_CHAR_DEAD ryd1_owner_ped																
							SET_CHAR_DECISION_MAKER ryd1_owner_ped ryd1_decision
							REMOVE_DECISION_MAKER ryd1_decision2
							SET_CHAR_RELATIONSHIP ryd1_owner_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1																					
							SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1																																		
						ENDIF
						REMOVE_ANIMATION INT_HOUSE						
						
						IF  ryd1_number_gunbox_truck > 3
							IF NOT IS_CHAR_DEAD ryd1_owner_ped
								OPEN_SEQUENCE_TASK ryd1_owner_awake_seq						
								TASK_KILL_CHAR_ON_FOOT -1 scplayer					
								CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
								PERFORM_SEQUENCE_TASK ryd1_owner_ped ryd1_owner_awake_seq
								CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
							ENDIF
							SET_PLAYER_CONTROL player1 ON
							SWITCH_WIDESCREEN OFF							
							RESTORE_CAMERA_JUMPCUT
							SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE 
							PRINT (RYD1_44) 4000 1 // ~s~You're not going to be able to steal any more boxes, get back to the ~b~truck~s~.
						    ryd1_message_displayed = 0
						
						ELSE							
							////////////// audio goes in here  //////////////////
							ryd1_audio_counter	  = 0
							ryd1_audio_slot1 	  = 1
							ryd1_audio_slot2 	  = 2
							ryd1_audio_playing	  = 0
							ryd1_text_loop_done	  = 0
							ryd1_mobile			  = 0
							ryd1_text_timer_diff  = 0
							ryd1_text_timer_end   = 0
							ryd1_text_timer_start = 0
							ryd1_ahead_counter	  = 0
							
							ryder1_text_loop4:
							WAIT 0 

							IF ryd1_text_loop_done = 0
								
								IF NOT ryd1_audio_counter = 0
									IF ryd1_audio_playing = 0
										IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
											CLEAR_MISSION_AUDIO ryd1_audio_slot2
										ENDIF
										ryd1_audio_playing = 1
									ENDIF

									IF ryd1_audio_playing = 1
										LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
										ryd1_audio_playing = 2
									ENDIF

									IF ryd1_audio_playing = 2
									 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
											PLAY_MISSION_AUDIO ryd1_audio_slot1
											PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
											ryd1_audio_playing = 3
										ENDIF
									ENDIF

									IF ryd1_audio_playing = 3
										IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
											CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
											IF ryd1_audio_slot1 = 1
												ryd1_audio_slot1 = 2
												ryd1_audio_slot2 = 1
											ELSE
												ryd1_audio_slot1 = 1
												ryd1_audio_slot2 = 2
											ENDIF
											ryd1_audio_counter = 0
											ryd1_audio_playing = 0
											ryd1_text_loop_done = 1	
										ENDIF
									ENDIF
								ENDIF
						  						  		   
							    											
								IF ryd1_audio_playing = 0
									IF ryd1_text_loop_done = 0																							  		   
								   		ryd1_audio_counter = 38//PRINT (RYD1_DD) 3000 1//CARL: Oh Shit!	//(RYD1_DD)
										SET_CHAR_COORDINATES scplayer 2807.5254 -1167.0757 1024.5781 
										SET_CHAR_HEADING scplayer 169.2411							
										TASK_GO_STRAIGHT_TO_COORD scplayer 2807.6262 -1178.2717 1024.5781 PEDMOVE_RUN  6000							
										CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
										SET_FIXED_CAMERA_POSITION 2809.6707 -1173.2228 1025.9940  0.0 0.0 0.0
										POINT_CAMERA_AT_POINT 2808.8162 -1172.7136 1025.8912 JUMP_CUT
									ENDIF
								ENDIF										
							
								GOTO	ryder1_text_loop4								   
							ENDIF
							
							ryd1_safety_flag = 0
							TIMERA = 0
							
							WHILE ryd1_safety_flag = 0
								GET_SCRIPT_TASK_STATUS scplayer TASK_GO_STRAIGHT_TO_COORD ryd1_task_status							   
								IF TIMERA > 10000
								OR ryd1_task_status = FINISHED_TASK
								OR LOCATE_CHAR_ANY_MEANS_3D scplayer 2807.6262 -1174.2717 1024.5781	1.3 1.3 3.0 FALSE
									ryd1_safety_flag = 1
								ENDIF
							
							WAIT 0 
							ENDWHILE
							DO_FADE 1500 FADE_OUT
							
							WHILE GET_FADING_STATUS
							    WAIT 0
							ENDWHILE
							
							CLEAR_CHAR_TASKS scplayer
							 
							 SET_CHAR_COORDINATES scplayer 2808.7842 -1178.0537 24.3559 
							 SET_CHAR_HEADING scplayer 231.7034 
							 SET_AREA_VISIBLE  0							 							 
							 SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2807.7764 -1174.7816 20.0
							 //SET_CHAR_AREA_VISIBLE scplayer 0
							 SET_FIXED_CAMERA_POSITION 2807.4727 -1176.3146 25.8373 0.0 0.0 0.0
							 POINT_CAMERA_AT_POINT 2808.3184 -1176.8474 25.8125 JUMP_CUT
							 
							 REQUEST_COLLISION	2808.7842 -1178.0537
							 LOAD_SCENE	 2808.7842 -1178.0537 24.0

							 IF NOT IS_CAR_DEAD ryd1_truck_car
								DELETE_CAR ryd1_truck_car
							 ENDIF
							 MARK_CAR_AS_NO_LONGER_NEEDED ryd1_truck_car
							 
							 IF NOT IS_CHAR_DEAD ryd1_ryder_ped
								DELETE_CHAR ryd1_ryder_ped
							 ENDIF
							 MARK_CHAR_AS_NO_LONGER_NEEDED ryd1_ryder_ped
							 
							 OPEN_SEQUENCE_TASK ryd1_owner_awake_seq
							 TASK_GO_STRAIGHT_TO_COORD -1 2814.9736 -1181.6954 24.2639 PEDMOVE_RUN  10000		
							 TASK_SCRATCH_HEAD -1
							 CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
							 PERFORM_SEQUENCE_TASK scplayer ryd1_owner_awake_seq
							 CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
//							 SET_DARKNESS_EFFECT FALSE -1
							 
							 IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
							 GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
							 BUILD_PLAYER_MODEL player1
							 ENDIF
							 
							 
							 DO_FADE 1000 FADE_IN							 							 
							 WHILE GET_FADING_STATUS
							 WAIT 0
							 ENDWHILE
							 ryd1_audio_counter	   = 78	//	Ryder, where the hell you at, man?
							 ryd1_audio_slot1 	   = 1
							 ryd1_audio_slot2 	   = 2
							 ryd1_audio_playing	   = 0																								
							 ryd1_ahead_counter	   = 0								
							 ryd1_safety_flag = 0
							 
							 TIMERA = 0
							 WHILE ryd1_safety_flag = 0																								 								 																		
																																													
								IF NOT ryd1_audio_counter = 0
									IF ryd1_audio_playing = 0
										IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
											CLEAR_MISSION_AUDIO ryd1_audio_slot2
										ENDIF
										ryd1_audio_playing = 1
									ENDIF

									IF ryd1_audio_playing = 1
										LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
										ryd1_audio_playing = 2
									ENDIF

									IF ryd1_audio_playing = 2
									 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
											PLAY_MISSION_AUDIO ryd1_audio_slot1
											PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
											ryd1_audio_playing = 3
										ENDIF
									ENDIF

									IF ryd1_audio_playing = 3
										IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
											CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
											IF ryd1_audio_slot1 = 1
												ryd1_audio_slot1 = 2
												ryd1_audio_slot2 = 1
											ELSE
												ryd1_audio_slot1 = 1
												ryd1_audio_slot2 = 2
											ENDIF
											ryd1_audio_counter = 0
											ryd1_audio_playing = 0
										ELSE
											IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
												IF ryd1_audio_counter < 76
													ryd1_ahead_counter = ryd1_audio_counter + 1
													LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
								
								GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK ryd1_task_status
								IF TIMERA > 10000
								OR ryd1_task_status = FINISHED_TASK
									ryd1_safety_flag = 1
								ENDIF
							 WAIT 0 
							 ENDWHILE
						ENDIF
	  				ENDIF
				
				ELSE /// player already outside
					IF NOT IS_CHAR_DEAD ryd1_owner_ped																
						
							CLEAR_CHAR_TASKS ryd1_owner_ped				
							SET_CHAR_COORDINATES ryd1_owner_ped	2806.9963 -1169.8094 1024.6120
							SET_CHAR_HEADING ryd1_owner_ped	193.7598
							SET_CHAR_DECISION_MAKER ryd1_owner_ped ryd1_decision
							REMOVE_DECISION_MAKER ryd1_decision2
							SET_CHAR_RELATIONSHIP ryd1_owner_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1						
							OPEN_SEQUENCE_TASK ryd1_owner_awake_seq
							TASK_KILL_CHAR_ON_FOOT -1 scplayer					
							CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
							PERFORM_SEQUENCE_TASK ryd1_owner_ped ryd1_owner_awake_seq
							CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
						
						IF NOT ryd1_number_gunbox_truck > 3
							///////////////////////////////////////////
							/// have scene showing ryder legging it ///
							///////////////////////////////////////////
							
							IF NOT IS_CAR_DEAD ryd1_truck_car
							AND NOT IS_CHAR_DEAD ryd1_ryder_ped
								
								SET_PLAYER_CONTROL Player1 OFF
								SWITCH_WIDESCREEN ON
								CLEAR_PRINTS 
								CLEAR_HELP
								LOCK_CAR_DOORS ryd1_truck_car CARLOCK_UNLOCKED
								FREEZE_CAR_POSITION ryd1_truck_car FALSE
								SET_FIXED_CAMERA_POSITION 2807.8579 -1188.1034 25.4725  0.0 0.0 0.0
								POINT_CAMERA_AT_POINT 2808.7920 -1187.7477 25.4449 JUMP_CUT
								
								FLUSH_ROUTE						  			
								
								EXTEND_ROUTE 2832.2573 -1182.7083 23.7924  
								EXTEND_ROUTE 2840.8108 -1211.1450 22.6279 
								
								OPEN_SEQUENCE_TASK ryd1_owner_awake_seq
								//TASK_GO_STRAIGHT_TO_COORD -1 2807.6262 -1174.2717 1024.5781 PEDMOVE_WALK  10000
								IF ryd1_mission_progression_flag > 5
								TASK_GO_STRAIGHT_TO_COORD -1 2816.7273 -1185.6876 24.2433 PEDMOVE_RUN 10000
								ENDIF
								TASK_ENTER_CAR_AS_DRIVER -1 ryd1_truck_car 20000
								TASK_DRIVE_POINT_ROUTE_ADVANCED -1 ryd1_truck_car 25.0 MODE_ACCURATE 0 DRIVINGMODE_SLOWDOWNFORCARS
								CLOSE_SEQUENCE_TASK ryd1_owner_awake_seq
								PERFORM_SEQUENCE_TASK ryd1_ryder_ped ryd1_owner_awake_seq
								CLEAR_SEQUENCE_TASK ryd1_owner_awake_seq
																
								ryd1_audio_counter	   = 0
								ryd1_audio_slot1 	   = 1
								ryd1_audio_slot2 	   = 2
								ryd1_audio_playing	   = 0																								
								ryd1_ahead_counter	   = 0
								ryd1_mobile			   = 0					
								ryd1_safety_flag = 0
								 
								 TIMERA = 0
								 WHILE ryd1_safety_flag = 0																		
																																													
									IF NOT ryd1_audio_counter = 0
										IF ryd1_audio_playing = 0
											IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
												CLEAR_MISSION_AUDIO ryd1_audio_slot2
											ENDIF
											ryd1_audio_playing = 1
										ENDIF

										IF ryd1_audio_playing = 1
											LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
											ryd1_audio_playing = 2
										ENDIF

										IF ryd1_audio_playing = 2
										 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
												PLAY_MISSION_AUDIO ryd1_audio_slot1
												PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
												ryd1_audio_playing = 3
											ENDIF
										ENDIF

										IF ryd1_audio_playing = 3
											IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
												CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
												IF ryd1_audio_slot1 = 1
													ryd1_audio_slot1 = 2
													ryd1_audio_slot2 = 1
												ELSE
													ryd1_audio_slot1 = 1
													ryd1_audio_slot2 = 2
												ENDIF
												ryd1_audio_counter = 0
												ryd1_audio_playing = 0
											ELSE
												IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
													IF ryd1_audio_counter < 76
														ryd1_ahead_counter = ryd1_audio_counter + 1
														LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
									
																		
									SWITCH ryd1_mobile
										CASE 0
											IF ryd1_audio_playing = 0																		
												IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
													START_CHAR_FACIAL_TALK ryd1_ryder_ped 5000
												ENDIF
												ryd1_audio_counter = 80//Oh shit, I'm gone!
												ryd1_mobile = 1
												GET_GAME_TIMER ryd1_text_timer_start
											ENDIF
											BREAK																							
										CASE 1
											GET_GAME_TIMER ryd1_text_timer_end
											ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
											IF ryd1_text_timer_diff > 1000
												IF ryd1_audio_playing = 0
													IF NOT IS_CHAR_DEAD ryd1_ryder_ped
														STOP_CHAR_FACIAL_TALK ryd1_ryder_ped										
													ENDIF
													START_CHAR_FACIAL_TALK scplayer 5000
													ryd1_audio_counter = 77//Ryder! Buster!
													ryd1_mobile = 2
													GET_GAME_TIMER ryd1_text_timer_start
												ENDIF
											ENDIF
											BREAK
										
										DEFAULT
											GET_GAME_TIMER ryd1_text_timer_end
											ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
											IF ryd1_text_timer_diff > 1000												
												IF ryd1_audio_playing = 0
												STOP_CHAR_FACIAL_TALK scplayer
												ENDIF																																		
											ENDIF
											BREAK
									ENDSWITCH
									
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										GET_SCRIPT_TASK_STATUS ryd1_ryder_ped PERFORM_SEQUENCE_TASK ryd1_task_status
									ELSE
										ryd1_safety_flag = 1
									ENDIF
									
									IF TIMERA > 15000
									OR ryd1_task_status = FINISHED_TASK
										ryd1_safety_flag = 1
									ENDIF
								 WAIT 0 
								 ENDWHILE

							ENDIF
						ENDIF																					
					ENDIF																									
				ENDIF //ryd1_area_name  = 8

		
			CLEAR_ONSCREEN_TIMER ryd1_time_to_daylight
			CLEAR_ONSCREEN_COUNTER  ryd1_noise_bar
			DISPLAY_RADAR TRUE
			ryd1_noise_bar_added_flag = 0
			ryd1_owner_awake_flag = 1
			
		 	IF  ryd1_number_gunbox_truck > 3
				CLEAR_ONSCREEN_TIMER ryd1_time_to_daylight			
				ryd1_owner_awake_flag = 1
				ryd1_mission_progression_flag = 7			
			ELSE
				/////////////////////////////////////////////////////////////////////////////
				/// have cut-scene of player leaving the house and ryder fucking off here ///
				/////////////////////////////////////////////////////////////////////////////				 				
				
				CLEAR_PRINTS
				SET_PLAYER_CONTROL Player1 ON
				SWITCH_WIDESCREEN OFF
				RESTORE_CAMERA_JUMPCUT
				CLEAR_CHAR_TASKS scplayer		 				 		
		 		//PRINT (RYD1_43) 3000 1 //~r~You're not going to be able to get the guns now that he's awake!
		 		ALTER_WANTED_LEVEL player1 2
		 		
				IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
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
		 		PRINT (RYD1_96) 5000 1//You did not manage to steal enough crates.	
		 		
		 		GOTO mission_g1_failed
			ENDIF
		
	ELSE
		
		ryd1_noise_difference = ryd1_previous_noise	- ryd1_noise_bar
		ryd1_previous_noise	= ryd1_noise_bar
		
		IF ryd1_noise_bar > 30
		AND ryd1_noise_bar < 60
			
			IF ryd1_noise_difference < 0 // noise going up
				IF ryd1_message_on_screen = 0
					IF NOT IS_MESSAGE_BEING_DISPLAYED
					PRINT (GUNS134) 4000 1  // ~s~You're making too much noise, you have to sneak!
					ryd1_message_on_screen = 1
					ENDIF																				
				ENDIF

			ELSE // noise going down
				IF ryd1_message_on_screen = 1
					IF NOT IS_MESSAGE_BEING_DISPLAYED
					AND	NOT ryd1_message_on_screen = 2					
						PRINT (GUNS134) 3000 1  // ~s~You're making too much noise, you have to sneak!
						ryd1_message_on_screen = 2
					ENDIF
				ENDIF

			ENDIF
		ENDIF
		
		IF ryd1_noise_bar > 59		
			IF ryd1_noise_difference < 0 // noise going up
				IF ryd1_message_on_screen = 1
					IF NOT IS_MESSAGE_BEING_DISPLAYED
						CLEAR_PRINTS
						PRINT (GUNS1_7) 4000 1 //" I'm sure I heard a noise..
						ryd1_message_on_screen = 3
					ENDIF
				ENDIF
			ELSE
				IF ryd1_message_on_screen = 2
					IF NOT IS_MESSAGE_BEING_DISPLAYED
						PRINT (GUNS1_7) 4000 1 //" I'm sure I heard a noise..
					 	ryd1_message_on_screen = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		IF ryd1_noise_bar < 51
			//ryd1_message_on_screen = 0
		ENDIF

		
	ENDIF		
	

ENDIF
ENDIF
ENDIF
ENDIF
	


////////////////////////////////////////////////////////////////////////////
///////////////////////   WHEN PLAYER IN THE HOUSE   ///////////////////////
////////////////////////////////////////////////////////////////////////////
	 
	 IF ryd1_mission_progression_flag = 6	
		 ryd1_temp_int = 0
		 WHILE ryd1_temp_int < 7
			 IF ryd1_player_holding_box_flag = 0
				IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_temp_int]
				 	IF ryd1_box_blip_added[ryd1_temp_int] = 0
		 	       		ADD_BLIP_FOR_OBJECT  ryd1_gunbox_obj[ryd1_temp_int] ryd1_gunbox_blip[ryd1_temp_int]
		 	       		GET_OBJECT_COORDINATES ryd1_gunbox_obj[ryd1_temp_int] ryd1_temp_x ryd1_temp_y ryd1_temp_z
		 	       		IF ryd1_temp_z > 700.0
		 	       		SET_BLIP_ENTRY_EXIT ryd1_gunbox_blip[ryd1_temp_int] 2807.8440 -1176.9855 10.0
		 	       		ENDIF
		 	       		ryd1_box_blip_added[ryd1_temp_int] = 1
					ENDIF			 
				 ENDIF
			 ELSE			 			  
				IF ryd1_box_blip_added[ryd1_temp_int] = 1
					REMOVE_BLIP ryd1_gunbox_blip[ryd1_temp_int]
					ryd1_box_blip_added[ryd1_temp_int] = 0
				ENDIF				  			  
			 ENDIF
			 ryd1_temp_int++
		 ENDWHILE

		 IF NOT ryd1_audio_counter = 0
			IF ryd1_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
					CLEAR_MISSION_AUDIO ryd1_audio_slot2
				ENDIF
				ryd1_audio_playing = 1
			ENDIF

			IF ryd1_audio_playing = 1
				LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
				ryd1_audio_playing = 2
			ENDIF

			IF ryd1_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
					PLAY_MISSION_AUDIO ryd1_audio_slot1
					PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
					ryd1_audio_playing = 3
				ENDIF
			ENDIF

			IF ryd1_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
					CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
					IF  ryd1_boxes_left > 0
						IF ryd1_able_to_leave_flag = 0
			   				PRINT_NOW (GUNS147) 5000 1 // " get another crate"
						ELSE
						   PRINT_NOW (RYD1_2) 5000 1//Find a ~g~crate~s~, or get in the ~b~truck~s~ and go offload what you've already stashed					
						ENDIF
						
					ENDIF
					IF ryd1_audio_slot1 = 1
						ryd1_audio_slot1 = 2
						ryd1_audio_slot2 = 1
					ELSE
						ryd1_audio_slot1 = 1
						ryd1_audio_slot2 = 2
					ENDIF
					ryd1_audio_counter = 0
					ryd1_audio_playing = 0
					IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
						STOP_CHAR_FACIAL_TALK ryd1_ryder_ped 
					ENDIF								
				ENDIF
			ENDIF
		ENDIF
		
		//////////////////////////////////////////////////////////
	 	/// ALLOW THE PLAYER TO LEAE AFTER OCLLECTING 3 CRATES ///
		//////////////////////////////////////////////////////////


	 	IF ryd1_able_to_leave_flag = 1
			IF NOT IS_CAR_DEAD ryd1_truck_car
				IF IS_CHAR_SITTING_IN_CAR scplayer ryd1_truck_car
	 			   	FREEZE_CAR_POSITION ryd1_truck_car FALSE	 			   	
		     		CLOSE_ALL_CAR_DOORS ryd1_truck_car

	 			   //////
	 			   	SET_PLAYER_CONTROL player1 OFF
	 			   	SWITCH_WIDESCREEN ON
	 			   	
	 			    SET_FIXED_CAMERA_POSITION 2810.1990 -1187.9792 25.1092 0.0 0.0 0.0
	 			    POINT_CAMERA_AT_POINT 2811.0686 -1187.5039 25.2425 JUMP_CUT				    					
							   	
				   	CLEAR_PRINTS										
					CLEAR_HELP
					
					CLEAR_AREA ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z 25.0 TRUE
					DELETE_OBJECT ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]
					
				    CLEAR_ONSCREEN_COUNTER  ryd1_noise_bar
					DISPLAY_RADAR TRUE
				    ryd1_noise_bar_added_flag = 0								   
				   
				    REMOVE_BLIP ryd1_truck_blip		    	 
				    ryd1_truck_blip_added_flag = 0 
				   				   		   
				    REMOVE_CHAR_ELEGANTLY ryd1_owner_ped
				   
				    REMOVE_BLIP ryd1_gunbox_blip[0]
					REMOVE_BLIP ryd1_gunbox_blip[1]
					REMOVE_BLIP ryd1_gunbox_blip[2]
					REMOVE_BLIP ryd1_gunbox_blip[3]
					REMOVE_BLIP ryd1_gunbox_blip[4]
					REMOVE_BLIP ryd1_gunbox_blip[5]
					REMOVE_BLIP ryd1_gunbox_blip[6]

				    DELETE_OBJECT ryd1_gunbox_obj[0]		
				    DELETE_OBJECT ryd1_gunbox_obj[1]		
				    DELETE_OBJECT ryd1_gunbox_obj[2]		
				    DELETE_OBJECT ryd1_gunbox_obj[3]	   	
				    DELETE_OBJECT ryd1_gunbox_obj[4]	    
				    DELETE_OBJECT ryd1_gunbox_obj[5]	   	
				    DELETE_OBJECT ryd1_gunbox_obj[6]		
				    

 				    MARK_CHAR_AS_NO_LONGER_NEEDED ryd1_owner_ped
  				    MARK_MODEL_AS_NO_LONGER_NEEDED wmopj
   				    MARK_MODEL_AS_NO_LONGER_NEEDED db_ammo
				    MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
				    MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[0]
					MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[1]
					MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[2]
					MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[3]
					MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[4]
					MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[5]
					MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[6]
			  	   	REMOVE_ANIMATION INT_HOUSE


					IF NOT IS_CHAR_DEAD ryd1_ryder_ped

				    OPEN_SEQUENCE_TASK ryd1_ryder_leave_seq
					TASK_GO_STRAIGHT_TO_COORD -1 2812.0908 -1186.1982 24.2658 PEDMOVE_RUN 10000					
					SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
					TASK_ENTER_CAR_AS_PASSENGER -1 ryd1_truck_car 4000 0
					CLOSE_SEQUENCE_TASK  ryd1_ryder_leave_seq
					PERFORM_SEQUENCE_TASK	ryd1_ryder_ped ryd1_ryder_leave_seq
					CLEAR_SEQUENCE_TASK  ryd1_ryder_leave_seq
					ENDIF
	 			
	 				SWITCH_ENTRY_EXIT burhous FALSE
																				
				   	ryd1_audio_counter = 38			
					ryd1_audio_playing = 0
					ryd1_audio_slot1 	   = 1
					ryd1_audio_slot2 	   = 2

					TIMERB = 0
					ryd1_safety_flag = 0
					WHILE ryd1_safety_flag = 0
						
						
							IF NOT ryd1_audio_counter = 0
								IF ryd1_audio_playing = 0
									IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
										CLEAR_MISSION_AUDIO ryd1_audio_slot2
									ENDIF
									ryd1_audio_playing = 1
								ENDIF

								IF ryd1_audio_playing = 1
									LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
									ryd1_audio_playing = 2
								ENDIF

								IF ryd1_audio_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
										PLAY_MISSION_AUDIO ryd1_audio_slot1
										PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
										ryd1_audio_playing = 3
									ENDIF
								ENDIF

								IF ryd1_audio_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
										CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
										IF ryd1_audio_slot1 = 1
											ryd1_audio_slot1 = 2
											ryd1_audio_slot2 = 1
										ELSE
											ryd1_audio_slot1 = 1
											ryd1_audio_slot2 = 2
										ENDIF
										ryd1_audio_counter = 0
										ryd1_audio_playing = 0
									ELSE
										IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
											IF ryd1_audio_counter < 76
												ryd1_ahead_counter = ryd1_audio_counter + 1
												LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						
						
						
						IF NOT IS_CHAR_DEAD ryd1_ryder_ped
					   	AND NOT IS_CAR_DEAD ryd1_truck_car
					   	   IF IS_CHAR_SITTING_IN_CAR ryd1_ryder_ped ryd1_truck_car						   
						       ryd1_safety_flag = 1
						   ENDIF
					   	ELSE 
					   		IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1	balaclava
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
					   		GOTO mission_g1_failed
					   	ENDIF
					   
					    IF TIMERB > 15000									   
							ryd1_safety_flag = 1
						ENDIF

					   WAIT 0
					 ENDWHILE

					IF NOT IS_CHAR_DEAD ryd1_ryder_ped
						IF NOT IS_CAR_DEAD ryd1_truck_car
							IF NOT IS_CHAR_IN_CAR ryd1_ryder_ped ryd1_truck_car
								WARP_CHAR_INTO_CAR_AS_PASSENGER ryd1_ryder_ped ryd1_truck_car 0
							ENDIF
						ENDIF
					ENDIF

					RESTORE_CAMERA_JUMPCUT
					SWITCH_WIDESCREEN OFF
					SET_PLAYER_CONTROL player1 ON

			   		ryd1_ryder_told_enter_car_flag = 0
					ryd1_ryder_blip_added_flag = 0
				 	ryd1_truck_blip_added_flag = 0
				 	ryd1_audio_counter	   = 0
					ryd1_audio_slot1 	   = 1
					ryd1_audio_slot2 	   = 2
					ryd1_audio_playing	   = 0
					ryd1_text_loop_done	   = 0
					ryd1_mobile			   = 0
					ryd1_text_timer_diff   = 0
					ryd1_text_timer_end    = 0
					ryd1_text_timer_start  = 0
					ryd1_ahead_counter	   = 0
					ryd1_track_playing     = 1
					
				 	ryd1_mission_progression_flag = 8
					ryd1_message_displayed = 0	   			   		
					ryd1_message_on_screen = 0
		    
			   		SET_POLICE_IGNORE_PLAYER player1 OFF
					SET_PED_DENSITY_MULTIPLIER 1.0
					SET_CAR_DENSITY_MULTIPLIER 1.0 // normally 1.0   		   	   
			  
				    CLEAR_PRINTS	   	   
			   	    PRINT (GUNS124) 10000 1 // "get home"
			   	    
			   	    ADD_BLIP_FOR_COORD  2742.0 -2008.0 13.0 ryd1_home_blip		  
			   	    ryd1_home_blip_added_flag	= 1	 					 				
	 			ENDIF
			ENDIF
		ELSE /// able to leave
			 IF ryd1_able_to_leave_flag = 0
				IF ryd1_number_gunbox_truck > 3
					IF NOT IS_CAR_DEAD 	ryd1_truck_car
						LOCK_CAR_DOORS ryd1_truck_car CARLOCK_UNLOCKED
						ryd1_able_to_leave_flag = 1
					ENDIF
				ENDIF
			ENDIF												
		ENDIF
	 
	 ENDIF

	//////////////////////////////////////
	/// CHECK IF PLAYER CLOSE TO CRATE ///
	//////////////////////////////////////
	
	IF ryd1_mission_progression_flag = 6	
	AND ryd1_player_holding_box_flag = 0
		ryd1_near_box = 0
		ryd1_counter = 0					

			IF ryd1_boxes_player_needs > ryd1_boxes_left
			   CLEAR_PRINTS
			   IF IS_PLAYER_WEARING	player1	CLOTHES_TEX_EXTRA1 balaclava
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
			   PRINT (RYD1_32) 5000 1 //~r~ There isn't enough guns left to fill the truck!
			   GOTO mission_g1_failed
			ELSE
				IF ryd1_boxes_left = 0
					ryd1_mission_progression_flag = 7
				ENDIF
			ENDIF
		
			
			WHILE (ryd1_counter < 7)				 
				 
				 IF	ryd1_gunbox_picked_up_flag[ryd1_counter] = 0
					IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_counter]
						IF IS_OBJECT_IN_WATER ryd1_gunbox_obj[ryd1_counter]
							MARK_OBJECT_AS_NO_LONGER_NEEDED	ryd1_gunbox_obj[ryd1_counter]
							IF ryd1_box_blip_added[ryd1_counter] = 1
							REMOVE_BLIP ryd1_gunbox_blip[ryd1_counter]
							ryd1_box_blip_added[ryd1_counter] = 0
							ENDIF							
							ryd1_gunbox_picked_up_flag[ryd1_counter] = 1
							ryd1_boxes_left--
						ENDIF
					ELSE
						MARK_OBJECT_AS_NO_LONGER_NEEDED	ryd1_gunbox_obj[ryd1_counter]
						ryd1_gunbox_picked_up_flag[ryd1_counter] = 1
						ryd1_boxes_left--
					ENDIF
				 ENDIF

				 IF	ryd1_gunbox_picked_up_flag[ryd1_counter] = 0
				 AND ryd1_player_holding_box_flag = 0
					IF LOCATE_CHAR_ON_FOOT_OBJECT_3D scplayer ryd1_gunbox_obj[ryd1_counter] 1.5 1.5 1.5 FALSE
	 	       		   ryd1_near_box = 1
				 	   IF ryd1_help_displayed = 0				 
			   		   		IF 	ryd1_box_help_been_shown = 0
					   			PRINT_HELP_FOREVER (GUNS118)
			   		   			ryd1_box_help_been_shown = 1
	 	       		   		ENDIF
	 	       		   ryd1_help_displayed= 1
					   ENDIF

	 	       		   IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_counter]
		 	       		   IF IS_CHAR_HOLDING_OBJECT scplayer ryd1_gunbox_obj[ryd1_counter]
		 	       			  CLEAR_HELP
		 	       			  ryd1_help_displayed = 0
		 	       			  // have player pick up the box		 	       			  		 	       			  		 	       			  
		 	       			  ryd1_gunbox_player_is_carrying = ryd1_counter

		 	       			  IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							  AND ryd1_truck_blip_added_flag = 0								  								  								  
								  IF NOT IS_CAR_DEAD ryd1_truck_car
									ryd1_house_blip_added_flag = 0												  
								  	ADD_BLIP_FOR_CAR ryd1_truck_car ryd1_truck_blip
								  	SET_BLIP_AS_FRIENDLY ryd1_truck_blip TRUE
								  	CLEAR_PRINTS
								  	IF ryd1_truck_message_displayed = 0
								  		PRINT (GUNS146) 10000 1 // 
									  	ryd1_truck_message_displayed = 1
									ENDIF
								  	  ryd1_truck_blip_added_flag = 1
								  ENDIF
							  ENDIF
							  	 	       			  	 	       			  
		 	       			  ryd1_gunbox_picked_up_flag[ryd1_counter] = 1
		 	       			  ryd1_player_holding_box_flag = 1      			   			   
				   		   ENDIF			
					   ENDIF
					ENDIF

				 ENDIF
			ryd1_counter++
			ENDWHILE
			IF ryd1_near_box = 0
			   CLEAR_HELP
			   ryd1_help_displayed = 0
			ENDIF

			
	ENDIF

	/////////////////////////////
	/// LET PLAYER DROP CRATE ///
	/////////////////////////////
   
	IF ryd1_player_holding_box_flag = 1 										
		IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]
			
			IF IS_CHAR_HOLDING_OBJECT scplayer ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]				
				
			ELSE
				ryd1_gunbox_picked_up_flag[ryd1_gunbox_player_is_carrying] = 0
				ryd1_player_holding_box_flag = 0
				REMOVE_BLIP ryd1_truck_blip		    	 
				ryd1_truck_blip_added_flag = 0
				IF ryd1_house_blip_added_flag = 0
				AND NOT ryd1_number_gunbox_truck = 5					
		   			CLEAR_PRINTS
		   			IF ryd1_able_to_leave_flag = 0
		   				PRINT (RYD1_40) 5000 1 //Find a crate.
					ELSE
					   PRINT (RYD1_2) 5000 1//Find a ~g~crate~s~, or get in the ~b~truck~s~ and go offload what you've already stashed					
					ENDIF
		   			ryd1_house_blip_added_flag = 1
				ENDIF

			ENDIF
		ENDIF
	ENDIF
	
	///////////////////////////////////////////////////////////////////////
	/// IF PLAYER IS HOLDING A CRATE AND BY TRUCK PASS CRATE INTO TRUCK ///
	///////////////////////////////////////////////////////////////////////
	
	IF ryd1_player_holding_box_flag = 1
	AND ryd1_mission_progression_flag = 6
	 	
		IF NOT IS_CAR_DEAD ryd1_truck_car
	  	AND NOT IS_CHAR_DEAD ryd1_ryder_ped		  

			IF LOCATE_CHAR_ON_FOOT_3D scplayer ryd1_passenger_x ryd1_passenger_y 24.0 1.5 1.5 2.0 FALSE
		    	
		    	IF ryd1_help_displayed = 0
					OPEN_SEQUENCE_TASK ryd1_ryder_leave_seq								
					TASK_GO_STRAIGHT_TO_COORD -1 2811.4375 -1185.9824 24.2707 PEDMOVE_WALK 10000			
					TASK_TURN_CHAR_TO_FACE_CHAR	-1 scplayer 
					CLOSE_SEQUENCE_TASK  ryd1_ryder_leave_seq
					PERFORM_SEQUENCE_TASK	ryd1_ryder_ped ryd1_ryder_leave_seq
					CLEAR_SEQUENCE_TASK  ryd1_ryder_leave_seq			 		
			    	PRINT_HELP_FOREVER RYD1_30  // Walk over to the truck to drop the item.
					ryd1_help_displayed = 1
			 	ENDIF
	 	     	
	 	     	IF IS_BUTTON_PRESSED PAD1 TRIANGLE  
					
					CLEAR_HELP
			     	CLEAR_PRINTS
			     	ryd1_help_displayed = 0
			     	
			     	ryd1_number_gunbox_truck++				 				 
				    ryd1_reward += 50
				    ryd1_ryder_box_deleted_flag = 0			     		     			

					ryd1_boxes_player_needs--
					ryd1_boxes_left--

					IF ryd1_boxes_left = 0
						IF ryd1_audio_playing = 0
							IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
								START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
							ENDIF
							ryd1_audio_counter = 33 // RYD1_ZM//We gonna be able to retire!
						ENDIF						
						ryd1_mission_progression_flag = 7
					
					ELSE
						
						SWITCH ryd1_number_gunbox_truck
							CASE 2
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF
									ryd1_audio_counter = 22// RYD1_ZA//Keep it coming, CJ! 
								ENDIF
								BREAK
							CASE 3								
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF										
									ryd1_audio_counter = 23// RYD1_ZB//What I tell ya? We making a killing!																					
								ENDIF									
								BREAK
							CASE 4																
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF
									ryd1_able_to_leave_flag = 1
									
									ryd1_audio_counter = 31 // RYD1_ZK//You're a natural house-breaker, dude!
									IF NOT IS_CAR_DEAD 	ryd1_truck_car
									LOCK_CAR_DOORS ryd1_truck_car CARLOCK_UNLOCKED
									ENDIF																			
								ENDIF								
								BREAK
							CASE 5								
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF
									ryd1_audio_counter = 25 // RYD1_ZD//There's plenty more in there!										
								ENDIF								
								BREAK
							CASE 6								
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF
									ryd1_audio_counter = 27 // RYD1_ZF//Take him for everything you can!										
								ENDIF								
								BREAK
							CASE 7								
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF
									ryd1_audio_counter = 24 // RYD1_ZC//Get back in there and strip the place!																				
								ENDIF								
								BREAK
							CASE 8								
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF
									ryd1_audio_counter = 32 // RYD1_ZL//C'mon, keep it up!										
								ENDIF								
								BREAK
							
							DEFAULT								
								IF ryd1_audio_playing = 0
								   IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
										START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
									ENDIF
								   ryd1_audio_counter = 26 // RYD1_ZE//Hurry it up, CJ!	
								ENDIF								
								BREAK
						ENDSWITCH					 																																																					 					
					    
					ENDIF
					
					CLEAR_CHAR_TASKS ryd1_ryder_ped
				 	TASK_SHAKE_FIST ryd1_ryder_ped
				     
				    OPEN_SEQUENCE_TASK ryd1_ryder_leave_seq			
					TASK_GO_STRAIGHT_TO_COORD -1 2829.4116 -1187.1519 23.8422 PEDMOVE_WALK -2						
					TASK_LOOK_AT_COORD -1  2833.2507 -1197.9080 23.3976 2500
					TASK_LOOK_AT_COORD -1  2831.1655 -1169.7920 23.9627 2500  
					TASK_STAND_STILL -1 5000
					TASK_GO_STRAIGHT_TO_COORD -1 2811.4375 -1185.9824 24.2707 PEDMOVE_WALK -2			
					TASK_LOOK_AT_COORD -1  2808.1223 -1190.8842 24.3397	2500
					TASK_LOOK_AT_COORD -1  2808.4304 -1177.4086 24.3621 2500
					TASK_STAND_STILL -1 5000
					SET_SEQUENCE_TO_REPEAT ryd1_ryder_leave_seq 1
					CLOSE_SEQUENCE_TASK  ryd1_ryder_leave_seq
					PERFORM_SEQUENCE_TASK	ryd1_ryder_ped ryd1_ryder_leave_seq
					CLEAR_SEQUENCE_TASK  ryd1_ryder_leave_seq
				    					 
				     // pass over crate anim
			    	 ryd1_player_holding_box_flag = 0			     			     
				     IF NOT ryd1_able_to_leave_flag = 1
				     	REMOVE_BLIP ryd1_truck_blip		    	 		    	 	 	
		    	 	 	ryd1_truck_blip_added_flag = 0					 
					 ENDIF
			 		
			 		// delete the crate
					IF ryd1_number_gunbox_truck > 1
	       			AND ryd1_ryder_box_deleted_flag = 0
	       			  	
						ryd1_safety_flag  = 0
						TIMERB = 0
	       			  	
						IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]
	       			  	
		       			  	WHILE ryd1_safety_flag  = 0
		       			  		IF NOT IS_CHAR_HOLDING_OBJECT scplayer ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]
								OR IS_CHAR_DEAD scplayer
								OR TIMERB > 5000
									ryd1_safety_flag  = 1
								ENDIF
		       			  	    
								WAIT 0
							ENDWHILE
		       			  	
							MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]
							DELETE_OBJECT ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying] // bit drastic could usebetter method ?
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1000.0 -1000.0 -1000.0 SOUND_PART_MISSION_COMPLETE
							ryd1_ryder_box_deleted_flag = 1
						ENDIF
	       			ENDIF
			 		
			 		IF ryd1_house_blip_added_flag = 0
					AND ryd1_boxes_left > 0
			 			//ADD_BLIP_FOR_COORD  2809.64 -1168.75 25.61 ryd1_house_blip 
   		     			
   		     			ryd1_temp_int = 0
   		     			WHILE ryd1_temp_int < 7
   		     			IF DOES_OBJECT_EXIST ryd1_gunbox_obj[ryd1_temp_int]
		 	       		   IF ryd1_box_blip_added[ryd1_temp_int] = 0
		 	       		   		ADD_BLIP_FOR_OBJECT  ryd1_gunbox_obj[ryd1_temp_int] ryd1_gunbox_blip[ryd1_temp_int]
		 	       		   		GET_OBJECT_COORDINATES ryd1_gunbox_obj[ryd1_temp_int] ryd1_temp_x ryd1_temp_y ryd1_temp_z
				 	       		IF ryd1_temp_z > 700.0
				 	       		SET_BLIP_ENTRY_EXIT ryd1_gunbox_blip[ryd1_temp_int] 2807.8440 -1176.9855 10.0
				 	       		ENDIF
		 	       		   		ryd1_box_blip_added[ryd1_temp_int] = 1
						   ENDIF
					    ENDIF
						ryd1_temp_int++
						
						ENDWHILE
   		     			ryd1_house_blip_added_flag = 1
			 		ENDIF
									
			 	ENDIF // is button pressed
		  	
			ELSE
		  		CLEAR_HELP
		  		ryd1_help_displayed = 0
		  	ENDIF // locate
	  	ENDIF
	ENDIF		
	//////////////////////////////////////////////////////////////////////////////
	/// if PLAYER HAS THE REQUIRED NUMBER OF CRATES TRIGGER NEXT MISSION SECTION ///
	////////////////////////////////////////////////////////////////////////////////

	
	   
	   
	   	
	   
	

////////////////////////////////////////////////////////////////////////////
///////////////////////   WHEN PLAYER LEAVES THE HOUSE   //////////////////
////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////////////////////
	/// HAVE CUT-SCENE SHOWING PLAYER LEAVING HOUSE ///
	///////////////////////////////////////////////////


	 IF ryd1_mission_progression_flag = 7
	 	GET_AREA_VISIBLE ryd1_area_name		
		IF ryd1_area_name  = 0
		    ryd1_message_on_screen = 0
		    ryd1_message_displayed = 0
		    SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0
		    SET_PLAYER_CONTROL player1 OFF
			CLEAR_PRINTS
		    DO_FADE 1000 FADE_OUT
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			RESTORE_CAMERA_JUMPCUT
//			SET_DARKNESS_EFFECT FALSE -1
			SET_RADAR_ZOOM 0		   	
		   	CLEAR_PRINTS
			SWITCH_WIDESCREEN ON
			CLEAR_PRINTS
			CLEAR_HELP
			CLEAR_AREA ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z 25.0 TRUE
			DELETE_OBJECT ryd1_gunbox_obj[ryd1_gunbox_player_is_carrying]

			IF ryd1_owner_awake_flag = 0
		   CLEAR_ONSCREEN_COUNTER  ryd1_noise_bar
		   DISPLAY_RADAR TRUE
		   ryd1_noise_bar_added_flag = 0
		   ENDIF
		   
		   
		   REMOVE_BLIP ryd1_truck_blip		    	 
		   ryd1_truck_blip_added_flag = 0 
		   
		   REMOVE_CHAR_ELEGANTLY ryd1_owner_ped 
		   
		   DELETE_OBJECT ryd1_gunbox_obj[0]		
		   DELETE_OBJECT ryd1_gunbox_obj[1]		
		   DELETE_OBJECT ryd1_gunbox_obj[2]		
		   DELETE_OBJECT ryd1_gunbox_obj[3]	   	
		   DELETE_OBJECT ryd1_gunbox_obj[4]	    
		   DELETE_OBJECT ryd1_gunbox_obj[5]	   	
		   DELETE_OBJECT ryd1_gunbox_obj[6]		
		   
		   MARK_CHAR_AS_NO_LONGER_NEEDED ryd1_owner_ped
		   MARK_MODEL_AS_NO_LONGER_NEEDED wmopj
		   MARK_MODEL_AS_NO_LONGER_NEEDED db_ammo
		   MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
		   MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[0]
		   MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[1]
		   MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[2]
		   MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[3]
		   MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[4]
		   MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[5]
		   MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[6]
	  	   REMOVE_ANIMATION INT_HOUSE

		

			IF NOT IS_CHAR_DEAD ryd1_ryder_ped
			AND NOT IS_CAR_DEAD ryd1_truck_car
				CLEAR_CHAR_TASKS ryd1_ryder_ped			
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ryd1_truck_car -1.5 -4.0 0.0 ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z
				GET_CAR_HEADING ryd1_truck_car ryd1_truck_heading
				ryd1_truck_heading -= 180.0
				IF ryd1_truck_heading < 180.0
				   ryd1_truck_heading += 360.0
				ENDIF  
				
				CLEAR_CHAR_TASKS_IMMEDIATELY ryd1_ryder_ped
				
				SET_CHAR_COORDINATES ryd1_ryder_ped 2811.1216 -1186.5492 24.2718//ryd1_passenger_x ryd1_passenger_y -101.0			
				SET_CHAR_HEADING ryd1_ryder_ped 17.5866//ryd1_truck_heading



				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS ryd1_ryder_ped  1.0 1.0 0.0 ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z
				SET_CHAR_COORDINATES scplayer ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z
				
				SET_CHAR_COORDINATES scplayer 2806.9277 -1178.8778 24.4038 
				SET_CHAR_HEADING scplayer  260.0
			
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ryd1_truck_car  3.5 0.0 0.0 ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS ryd1_truck_car  -3.5 0.0 0.0 ryd1_driver_x ryd1_driver_y ryd1_driver_z
				LOCK_CAR_DOORS ryd1_truck_car CARLOCK_UNLOCKED
				FREEZE_CAR_POSITION ryd1_truck_car FALSE
			ENDIF


		
				
		
		/////////////////////////////////////////////////////// 		
		//////////////////if player did wake owner ////////////
		///////////////////////////////////////////////////////
			IF ryd1_owner_awake_flag = 1

				  REQUEST_MODEL COLT45
				  REQUEST_MODEL LAPD1 //lapd1
				  REQUEST_MODEL COPCARLA //cop car LA

				  WHILE NOT HAS_MODEL_LOADED LAPD1
				  OR NOT HAS_MODEL_LOADED COPCARLA
				  OR NOT HAS_MODEL_LOADED COLT45
				  WAIT 0
				  ENDWHILE
				   /////////////// create the cops
				 			  				  			  			  			  				  			  			  
				  CUSTOM_PLATE_FOR_NEXT_CAR COPCARLA &TH3_P1G5
				  CREATE_CAR COPCARLA 2841.16 -1237.64 21.56 ryd1_cop_car			  
	  			  SWITCH_CAR_SIREN  ryd1_cop_car ON
		   	      SET_CAR_HEADING ryd1_cop_car 10.0 	   	 		   			  
				  CREATE_CHAR_INSIDE_CAR ryd1_cop_car PEDTYPE_COP LAPD1  ryd1_cop1_ped			  
				  GIVE_WEAPON_TO_CHAR ryd1_cop1_ped WEAPONTYPE_PISTOL 3000
			      CREATE_CHAR_AS_PASSENGER ryd1_cop_car PEDTYPE_COP LAPD1 0 ryd1_cop2_ped
				  GIVE_WEAPON_TO_CHAR ryd1_cop2_ped WEAPONTYPE_PISTOL 3000
			      ////////////////////////////////////////

				  IF NOT IS_CAR_DEAD ryd1_truck_car
				  AND NOT IS_CAR_DEAD ryd1_cop_car
				  AND NOT IS_CHAR_DEAD ryd1_cop1_ped

					  OPEN_SEQUENCE_TASK ryd1_cop1_seq
					  TASK_CAR_DRIVE_TO_COORD -1 ryd1_cop_car  2833.26 -1186.69 23.8  30.0 MODE_NORMAL FALSE DRIVINGMODE_AVOIDCARS
					  TASK_LEAVE_CAR -1 ryd1_cop_car					  
					  TASK_AIM_GUN_AT_CHAR -1 scplayer 2000
					  TASK_SHOOT_AT_COORD -1 ryd1_passenger_x ryd1_passenger_y ryd1_passenger_z 10000
					  CLOSE_SEQUENCE_TASK   ryd1_cop1_seq
					  PERFORM_SEQUENCE_TASK	 ryd1_cop1_ped ryd1_cop1_seq			  
					  CLEAR_SEQUENCE_TASK   ryd1_cop1_seq
					  
					  OPEN_SEQUENCE_TASK ryd1_cop2_seq
					  TASK_LEAVE_CAR -1 ryd1_cop_car
					  TASK_AIM_GUN_AT_CHAR -1 scplayer 2000
					  TASK_SHOOT_AT_COORD -1 ryd1_driver_x ryd1_driver_y ryd1_driver_z 10000
					  CLOSE_SEQUENCE_TASK   ryd1_cop2_seq
				  ENDIF

				  IF NOT IS_CAR_DEAD ryd1_truck_car
		     		CLOSE_ALL_CAR_DOORS ryd1_truck_car
		     	ENDIF
				  SET_FIXED_CAMERA_POSITION 2835.0146 -1176.0179 25.3376  0.0 0.0 0.0//2835.42 -1175.28 25.23 0.0 0.0 0.0 // overlooking bedroom
			   	  POINT_CAMERA_AT_POINT 2834.2639 -1176.6774 25.3009 JUMP_CUT//2822.20 -1191.38 22.60 JUMP_CUT
				  REQUEST_COLLISION	2835.0146 -1176.0179 
				  LOAD_SCENE 2835.0146 -1176.0179 25.3376
				  DO_FADE 1000 FADE_IN
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
				  
				  TIMERA = 0
				  ryd1_safety_flag = 0

					   /////////////////////////////////////////////////////////
					   ////////////////////////////////////////////////////////
					   /////////////////////////////////////////////////////////
						ryd1_audio_counter	   = 0
						ryd1_audio_slot1 	   = 1
						ryd1_audio_slot2 	   = 2
						ryd1_audio_playing	   = 0
						ryd1_text_loop_done	   = 0
						ryd1_mobile			   = 0
						ryd1_text_timer_diff   = 0
						ryd1_text_timer_end    = 0
						ryd1_text_timer_start  = 0
						ryd1_ahead_counter	   = 0

						ryder1_text_loop6:
						WAIT 0 

						IF ryd1_text_loop_done = 0
							
							
							IF NOT ryd1_audio_counter = 0
								IF ryd1_audio_playing = 0
									IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
										CLEAR_MISSION_AUDIO ryd1_audio_slot2
									ENDIF
									ryd1_audio_playing = 1
								ENDIF

								IF ryd1_audio_playing = 1
									LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
									ryd1_audio_playing = 2
								ENDIF

								IF ryd1_audio_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
										PLAY_MISSION_AUDIO ryd1_audio_slot1
										PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
										ryd1_audio_playing = 3
									ENDIF
								ENDIF

								IF ryd1_audio_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
										CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
										IF ryd1_audio_slot1 = 1
											ryd1_audio_slot1 = 2
											ryd1_audio_slot2 = 1
										ELSE
											ryd1_audio_slot1 = 1
											ryd1_audio_slot2 = 2
										ENDIF
										ryd1_audio_counter = 0
										ryd1_audio_playing = 0
									ELSE
										IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
											IF ryd1_audio_counter < 76
												ryd1_ahead_counter = ryd1_audio_counter + 1
												LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							
							SWITCH ryd1_mobile
									CASE 0
										IF ryd1_audio_playing = 0
											SET_FIXED_CAMERA_POSITION 2835.0146 -1176.0179 25.3376  0.0 0.0 0.0//2835.42 -1175.28 25.23 0.0 0.0 0.0 // overlooking bedroom
			   	  							POINT_CAMERA_AT_POINT 2834.2639 -1176.6774 25.3009 JUMP_CUT//2822.20 -1191.38 22.60 JUMP_CUT
											ryd1_audio_counter = 43	// PRINT (RYD1_DJ) 4000 1//RYDER: That pussy called the cops!// 
											ryd1_mobile = 1
											GET_GAME_TIMER ryd1_text_timer_start
										ENDIF
										BREAK
									CASE 1
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000
											IF ryd1_audio_playing = 0
												ryd1_audio_counter = 44 //PRINT (RYD1_DK) 4000 1//~n~ I'm gonna ice them!	  //(RYD1_DK)	
												ryd1_mobile = 2
												GET_GAME_TIMER ryd1_text_timer_start
											ENDIF
										ENDIF	
										BREAK
									CASE 2
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000																										  								  		 					
											IF NOT IS_CHAR_DEAD ryd1_cop1_ped
												GET_SCRIPT_TASK_STATUS ryd1_cop1_ped PERFORM_SEQUENCE_TASK  ryd1_task_status
												IF  ryd1_task_status  = PERFORMING_TASK
													GET_SEQUENCE_PROGRESS ryd1_cop1_ped ryd1_task_status 						
													IF  ryd1_task_status  = 1
													OR  TIMERA > 10000
														IF ryd1_cop_task_assigned = 0
															IF NOT IS_CHAR_DEAD ryd1_cop2_ped
																IF NOT IS_CAR_DEAD ryd1_cop_car						  					
												  					IF ryd1_audio_playing = 0
																		ryd1_audio_counter = 45 //PRINT (RYD1_DL) 4000 1 //CARL: Fuck that, let's get this stuff out fo here!	//(RYD1_DL)	
																		ryd1_mobile = 3
																		GET_GAME_TIMER ryd1_text_timer_start											
													  					PERFORM_SEQUENCE_TASK	 ryd1_cop2_ped ryd1_cop2_seq
											  	   						CLEAR_SEQUENCE_TASK   ryd1_cop2_seq	
											  	   						  
											  	   						  SET_FIXED_CAMERA_POSITION 2807.8728 -1178.3564 24.8046 0.0 0.0 0.0 // overlooking bedroom
																	      POINT_CAMERA_AT_POINT 2808.7207 -1178.8698 24.9362 JUMP_CUT

																		  IF NOT IS_CAR_DEAD ryd1_truck_car
																			IF NOT IS_CHAR_DEAD ryd1_ryder_ped
																				
																				OPEN_SEQUENCE_TASK ryd1_player_leave_seq				   						   	
																				TASK_GO_STRAIGHT_TO_COORD -1 2814.0415 -1181.3757 24.2701 PEDMOVE_RUN 6000				   	
																		   		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
																		   		TASK_ENTER_CAR_AS_DRIVER -1 ryd1_truck_car 5000
																				CLOSE_SEQUENCE_TASK  ryd1_player_leave_seq
																				PERFORM_SEQUENCE_TASK	scplayer ryd1_player_leave_seq
																				CLEAR_SEQUENCE_TASK  ryd1_player_leave_seq

																						  
																				OPEN_SEQUENCE_TASK ryd1_ryder_leave_seq						
																				TASK_GO_STRAIGHT_TO_COORD -1 2812.0908 -1186.1982 24.2658 PEDMOVE_RUN 6000
																				SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_RUN
																				TASK_ENTER_CAR_AS_PASSENGER -1 ryd1_truck_car 3000 0
																				CLOSE_SEQUENCE_TASK  ryd1_ryder_leave_seq
																				PERFORM_SEQUENCE_TASK	ryd1_ryder_ped ryd1_ryder_leave_seq
																				CLEAR_SEQUENCE_TASK  ryd1_ryder_leave_seq
																			ENDIF
																		ENDIF				   						 												   						
											   							ryd1_cop_task_assigned = 1																			
											   						ENDIF																																								
																ENDIF
															ENDIF
														ENDIF
													ENDIF																												
												ENDIF
											ENDIF																							 																					
										ENDIF
										BREAK
									CASE 3
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000	 
											IF ryd1_audio_playing = 0
												ryd1_audio_counter = 46	//PRINT (RYD1_EA) 3000 1// CARL: You dumb bastard sherm head. //(RYD1_EA)		      
												ryd1_mobile = 4												
												
												GET_GAME_TIMER ryd1_text_timer_start														 																								
											ENDIF
										ENDIF
										BREAK
									
									DEFAULT
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000
											IF ryd1_audio_playing = 0
											   IF NOT IS_CHAR_DEAD scplayer
												AND NOT IS_CAR_DEAD ryd1_truck_car
													IF NOT IS_CHAR_IN_CAR scplayer ryd1_truck_car
												  		CLEAR_CHAR_TASKS scplayer				  	
												  		WARP_CHAR_INTO_CAR scplayer ryd1_truck_car
													ENDIF
												ENDIF
											  			  			  
											  	IF NOT IS_CHAR_DEAD ryd1_ryder_ped
													IF NOT IS_CAR_DEAD ryd1_truck_car
														IF NOT IS_CHAR_IN_CAR ryd1_ryder_ped ryd1_truck_car
															WARP_CHAR_INTO_CAR_AS_PASSENGER ryd1_ryder_ped ryd1_truck_car 0
														ENDIF
														IF NOT IS_CHAR_IN_CAR scplayer ryd1_truck_car
															WARP_CHAR_INTO_CAR scplayer ryd1_truck_car
														ENDIF
													ENDIF
												ENDIF
											   
											   ryd1_text_loop_done = 1	
											ENDIF
										ENDIF
										BREAK
								ENDSWITCH
							
							
							GOTO ryder1_text_loop6
								/////////////////////////////////////////////////////////////////
							/////////////////////////////////////////////////////////////////	
						ENDIF

					///////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////
			     			  			  			  			  			  
			  		
			  		
			  				  				  	
				  
			  
				 
				  
				  				  				  				  				  
				  
			  			  			 			  			  

			   	// end of cutscene		   		   
			   	//PRINT (RYD1_EB) 4000 1 //RYDER: What I do? You the one woke that crazy old bastard!	 //(RYD1_EB)  
				//PRINT (RYD1_EC) 4000 1 //RYDER: We gotta lose this heat! //(RYD1_EC)
				//PRINT (RYD1_ED) 4000 1 //RYDER: There's a spray shop in Idlewood should be able to to take a van this big //(RYD1_ED)
		   	   	
		   	   	

		   	   	
		   	   	
		   	   	ryd1_track_playing = 0
		   	   	
		   	   	
		   	   	
		   	   	
		   	   	PRINT (GUNS123) 10000 1 // "lose cops"
		   	   	ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY  ryd1_spray_blip 
			   	ryd1_cops_attacking_flag = 1
			   	ryd1_spray_blip_added_flag = 1
		   

		    	//ADD_BLIP_FOR_COORD 2741.00   -2006.00 14.00 ryd1_spray_blip//lock-up location		   
		   
			   	IF NOT IS_WANTED_LEVEL_GREATER player1 1
					ALTER_WANTED_LEVEL player1 2
			   	ENDIF

			   	IF NOT IS_CHAR_DEAD ryd1_cop1_ped
					TASK_KILL_CHAR_ON_FOOT ryd1_cop1_ped scplayer
				ENDIF
				
				IF NOT IS_CHAR_DEAD ryd1_cop2_ped
					TASK_KILL_CHAR_ON_FOOT ryd1_cop2_ped scplayer
				ENDIF
		   
        //////////////////////////////////////////////////////////////// 		
		////////////////// IF PLAYER DID NOT WAKE THE OWNER ////////////
		////////////////////////////////////////////////////////////////
		
			ELSE
			  
		     	IF NOT IS_CAR_DEAD ryd1_truck_car
		     		CLOSE_ALL_CAR_DOORS ryd1_truck_car
		     	ENDIF 
		     	
				GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
				BUILD_PLAYER_MODEL player1

		     	SET_FIXED_CAMERA_POSITION 2818.6704 -1182.2640 24.8706   0.0 0.0 0.0
			    POINT_CAMERA_AT_POINT 2817.7019 -1182.4490 25.0366 JUMP_CUT
		      	DO_FADE 1000 FADE_IN
				
				REQUEST_COLLISION  2814.0415 -1181.3757 
				LOAD_SCENE 2814.0415 -1181.3757 24.2701

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				IF NOT IS_CAR_DEAD ryd1_truck_car
				AND NOT IS_CHAR_DEAD ryd1_ryder_ped
				    		  
				  
				  	OPEN_SEQUENCE_TASK ryd1_player_leave_seq				   	
				   	TASK_GO_STRAIGHT_TO_COORD -1 2814.0415 -1181.3757 24.2701 PEDMOVE_WALK 6000 					
			   		SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
			   		TASK_ENTER_CAR_AS_DRIVER -1 ryd1_truck_car 6000
					CLOSE_SEQUENCE_TASK  ryd1_player_leave_seq
					PERFORM_SEQUENCE_TASK	scplayer ryd1_player_leave_seq
					CLEAR_SEQUENCE_TASK  ryd1_player_leave_seq

							  
					OPEN_SEQUENCE_TASK ryd1_ryder_leave_seq
					TASK_GO_STRAIGHT_TO_COORD -1 2812.0908 -1186.1982 24.2658 PEDMOVE_WALK 6000					
					SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
					TASK_ENTER_CAR_AS_PASSENGER -1 ryd1_truck_car 4000 0
					CLOSE_SEQUENCE_TASK  ryd1_ryder_leave_seq
					PERFORM_SEQUENCE_TASK	ryd1_ryder_ped ryd1_ryder_leave_seq
					CLEAR_SEQUENCE_TASK  ryd1_ryder_leave_seq
					
				ENDIF
				
				
				/////////// audio to go in here	 ////////////////
				
				//PRINT (RYD1_FA) 4000 1 //RYDER: Ok, now we're cooking with gas!	//(RYD1_FA)
				//PRINT (RYD1_FB) 4000 1 //RYDER: LB's got a lock-up down in Seville Boulevard Families turf. //(RYD1_FB)
				//PRINT (RYD1_FC) 4000 1 // CARL: I'm on it!  //(RYD1_FC)
				
				//$ryd1_text[50] = RYD1_FA//Ok, now we're cooking with gas!
				//$ryd1_text[51] = RYD1_FB//LB's got a lock-up down in Seville Boulevard Families turf.
				//$ryd1_text[52] = RYD1_FC//I'm on it!
				ryd1_track_playing = 1
				
				TIMERB = 0
				ryd1_safety_flag = 0
				WHILE ryd1_safety_flag = 0
					IF NOT IS_CHAR_DEAD ryd1_ryder_ped
				   	AND NOT IS_CAR_DEAD ryd1_truck_car
				   	   IF IS_CHAR_SITTING_IN_CAR ryd1_ryder_ped ryd1_truck_car
					   AND IS_CHAR_SITTING_IN_CAR scplayer ryd1_truck_car
					       ryd1_safety_flag = 1
					   ENDIF
				   	ELSE 
				   		IF IS_PLAYER_WEARING player1 CLOTHES_TEX_EXTRA1	balaclava
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
				   		GOTO mission_g1_failed
				   	ENDIF
				   
				    IF TIMERB > 10000									   
						ryd1_safety_flag = 1
					ENDIF

				   WAIT 0
				 ENDWHILE

				   
			   		   		   	   
			  // end of cutscene 
			   CLEAR_PRINTS	   	   
		   	   PRINT (GUNS124) 10000 1 // "get home"
		   	   ADD_BLIP_FOR_COORD  2742.0 -2008.0 13.0 ryd1_home_blip		  
		   	   ryd1_home_blip_added_flag	= 1
			ENDIF
		 	
		
			IF NOT IS_CHAR_DEAD ryd1_ryder_ped
				IF NOT IS_CAR_DEAD ryd1_truck_car
					IF NOT IS_CHAR_IN_CAR ryd1_ryder_ped ryd1_truck_car
						WARP_CHAR_INTO_CAR_AS_PASSENGER ryd1_ryder_ped ryd1_truck_car 0
					ENDIF
					IF NOT IS_CHAR_IN_CAR scplayer ryd1_truck_car
						WARP_CHAR_INTO_CAR scplayer ryd1_truck_car
					ENDIF
				ENDIF
			ENDIF
		
			RESTORE_CAMERA_JUMPCUT			
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			IF NOT IS_CHAR_DEAD ryd1_ryder_ped
				IF NOT IS_GROUP_MEMBER ryd1_ryder_ped Players_Group
					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
					SET_GROUP_MEMBER Players_Group ryd1_ryder_ped				
					SET_GROUP_FOLLOW_STATUS Players_Group FALSE
					LISTEN_TO_PLAYER_GROUP_COMMANDS ryd1_ryder_ped FALSE
				ENDIF							  			
			ENDIF

			SET_POLICE_IGNORE_PLAYER player1 OFF
			SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0 // normally 1.0
			
			
			
			ryd1_ryder_told_enter_car_flag = 0
			ryd1_ryder_blip_added_flag = 0
		 	ryd1_truck_blip_added_flag = 0
		 	ryd1_audio_counter	   = 0
			ryd1_audio_slot1 	   = 1
			ryd1_audio_slot2 	   = 2
			ryd1_audio_playing	   = 0
			ryd1_text_loop_done	   = 0
			ryd1_mobile			   = 0
			ryd1_text_timer_diff   = 0
			ryd1_text_timer_end    = 0
			ryd1_text_timer_start  = 0
			ryd1_ahead_counter	   = 0
			
		 	ryd1_mission_progression_flag = 8
			ryd1_message_displayed = 0 
		
		
		
		ELSE   /// player still inside the house

			IF NOT ryd1_audio_counter = 0
				IF ryd1_audio_playing = 0
					IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
						CLEAR_MISSION_AUDIO ryd1_audio_slot2
					ENDIF
					ryd1_audio_playing = 1
				ENDIF

				IF ryd1_audio_playing = 1
					LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
					ryd1_audio_playing = 2
				ENDIF

				IF ryd1_audio_playing = 2
				 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
						PLAY_MISSION_AUDIO ryd1_audio_slot1
						PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
						ryd1_audio_playing = 3
					ENDIF
				ENDIF

				IF ryd1_audio_playing = 3
					IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
						CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
						IF ryd1_audio_slot1 = 1
							ryd1_audio_slot1 = 2
							ryd1_audio_slot2 = 1
						ELSE
							ryd1_audio_slot1 = 1
							ryd1_audio_slot2 = 2
						ENDIF
						ryd1_audio_counter = 0
						ryd1_audio_playing = 0
														
					ENDIF
				ENDIF
			ENDIF
			
			
			
			IF NOT IS_CHAR_DEAD ryd1_ryder_ped
			AND ryd1_message_displayed < 3
				IF NOT IS_MESSAGE_BEING_DISPLAYED
					IF ryd1_message_displayed = 0
						IF ryd1_audio_playing = 0
																	
							START_CHAR_FACIAL_TALK scplayer 3000
							
							ryd1_audio_counter = 38//PRINT (RYD1_DD) 3000 1//CARL: Oh Shit!	//(RYD1_DD)					
			    			ryd1_message_displayed = 1
						ENDIF
			    	ELSE
						IF ryd1_message_displayed = 1
			    			IF ryd1_audio_playing = 0
								IF NOT IS_CHAR_DEAD ryd1_owner_ped										
									START_CHAR_FACIAL_TALK ryd1_owner_ped 4000
									STOP_CHAR_FACIAL_TALK scplayer
								ENDIF
								ryd1_audio_counter = 36//PRINT (RYD1_DB) 4000 1 // SMITH: Get off my ridge, you viet cong bastards!//(RYD1_DB)
								ryd1_message_displayed = 2
							ENDIF													
						ELSE
							IF ryd1_audio_playing = 0
								IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
									START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000
								ENDIF
								ryd1_audio_counter = 37//PRINT (RYD1_DC) 4000 1 //SMITH: This one's for Kenny! //(RYD1_DC)
								ryd1_message_displayed = 3
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ELSE			
				CLEAR_PRINTS
				PRINT (RYD1_44) 4000 1 // ~s~You're not going to be able to steal any more boxes, get back to the ~b~truck~s~.
			ENDIF
			
			IF ryd1_truck_blip_added_flag = 0	
				REMOVE_BLIP ryd1_truck_blip		    	 
			   	IF NOT IS_CAR_DEAD ryd1_truck_car					
					ADD_BLIP_FOR_CAR ryd1_truck_car ryd1_truck_blip
					SET_BLIP_AS_FRIENDLY ryd1_truck_blip TRUE
			   		ryd1_truck_blip_added_flag = 1
				ENDIF
			ENDIF		
		ENDIF // area_visible = 0
	 ENDIF

	
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////
	//////////////////////////////////////////////////


IF ryd1_mission_progression_flag = 8 
	
	///// AUDIO CODE TO GO HERE /////////////
	
		IF NOT ryd1_audio_counter = 0
			IF ryd1_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
					CLEAR_MISSION_AUDIO ryd1_audio_slot2
				ENDIF
				ryd1_audio_playing = 1
			ENDIF

			IF ryd1_audio_playing = 1
				LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
				ryd1_audio_playing = 2
			ENDIF

			IF ryd1_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
					PLAY_MISSION_AUDIO ryd1_audio_slot1
					IF NOT ryd1_audio_counter = 76
					PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
					ENDIF
					ryd1_audio_playing = 3
				ENDIF
			ENDIF

			IF ryd1_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
					CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
					IF ryd1_audio_slot1 = 1
						ryd1_audio_slot1 = 2
						ryd1_audio_slot2 = 1
					ELSE
						ryd1_audio_slot1 = 1
						ryd1_audio_slot2 = 2
					ENDIF
					ryd1_audio_counter = 0
					ryd1_audio_playing = 0
													
				ENDIF
			ENDIF
		ENDIF
	
	
	

/*
ryd1_track_playing		 = 0
ryd1_flee_audio_counter  = 0
ryd1_flee_audio_played	 = 0
ryd1_safe_audio_counter  = 0
ryd1_safe_audio_played	 =	0
ryd1_lockup_audio_counter =	0
ryd1_lockup_audio_played  =	0
ryd1_wanted_audio_counter =	0
ryd1_wanted_audio_played  =	0
*/


IF ryd1_track_playing = 0
// player fleeing house after waking owner //
	IF ryd1_flee_audio_played = 0
		IF NOT IS_MESSAGE_BEING_DISPLAYED
			SWITCH ryd1_flee_audio_counter		
				CASE 0
					IF ryd1_audio_playing = 0
						IF NOT IS_CHAR_DEAD ryd1_ryder_ped
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped TRUE
						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
						ENDIF
						//ryd1_audio_counter = 46 //RYD1_EA//You dumb bastard sherm head.	
						ryd1_flee_audio_counter = 1
						GET_GAME_TIMER ryd1_text_timer_start
					ENDIF
					BREAK
				CASE 1
					GET_GAME_TIMER ryd1_text_timer_end
					ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
					IF ryd1_text_timer_diff > 1000
						IF ryd1_audio_playing = 0
							ryd1_audio_counter = 47 //RYD1_EB//What I do? You the one woke that crazy old bastard!
							ryd1_flee_audio_counter = 2
							GET_GAME_TIMER ryd1_text_timer_start
						ENDIF
					ENDIF	
					BREAK
				CASE 2
					GET_GAME_TIMER ryd1_text_timer_end
					ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
					IF ryd1_text_timer_diff > 1000
						IF ryd1_audio_playing = 0  
							ryd1_audio_counter = 48 //RYD1_EC//We gotta lose this heat!
							ryd1_flee_audio_counter = 3
							GET_GAME_TIMER ryd1_text_timer_start
						ENDIF
					ENDIF
					BREAK
				CASE 3
					GET_GAME_TIMER ryd1_text_timer_end
					ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
					IF ryd1_text_timer_diff > 1000
						IF ryd1_audio_playing = 0  
							ryd1_audio_counter = 49 //RYD1_ED//There's a spray shop in Idlewood should be able to take a van this big!
							ryd1_flee_audio_counter = 4
							GET_GAME_TIMER ryd1_text_timer_start
						ENDIF
					ENDIF
					BREAK																
				DEFAULT
					GET_GAME_TIMER ryd1_text_timer_end
					ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
					IF ryd1_text_timer_diff > 1000
						IF ryd1_audio_playing = 0
						   IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped FALSE
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
							ENDIF
						   ryd1_flee_audio_played = 1
						ENDIF
					ENDIF
					BREAK
			ENDSWITCH
		ENDIF
	ENDIF	

ELSE
	IF ryd1_track_playing = 1
	// player does not have wanted rating heading to lockup //
		IF ryd1_safe_audio_played  = 0
			IF NOT IS_MESSAGE_BEING_DISPLAYED
				SWITCH ryd1_safe_audio_counter
					CASE 0
						IF ryd1_audio_playing = 0
							IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped TRUE
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
							ENDIF
							ryd1_audio_counter = 50 //RYD1_FA//Ok, now we're cooking with gas!	
							ryd1_safe_audio_counter = 1
							GET_GAME_TIMER ryd1_text_timer_start
						ENDIF
						BREAK
					CASE 1
						GET_GAME_TIMER ryd1_text_timer_end
						ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
						IF ryd1_text_timer_diff > 1000
							IF ryd1_audio_playing = 0
								ryd1_audio_counter = 51 //RYD1_FB//LB's got a lock-up down in Seville Boulevard Families turf.
								ryd1_safe_audio_counter = 2
								GET_GAME_TIMER ryd1_text_timer_start
							ENDIF
						ENDIF	
						BREAK
					CASE 2
						GET_GAME_TIMER ryd1_text_timer_end
						ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
						IF ryd1_text_timer_diff > 1000
							IF ryd1_audio_playing = 0  
								ryd1_audio_counter = 52 //RYD1_FC//I'm on it!
								ryd1_safe_audio_counter = 3
								GET_GAME_TIMER ryd1_text_timer_start
							ENDIF
						ENDIF
						BREAK																			
					DEFAULT
						GET_GAME_TIMER ryd1_text_timer_end
						ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
						IF ryd1_text_timer_diff > 1000
							IF ryd1_audio_playing = 0
							   IF NOT IS_CHAR_DEAD ryd1_ryder_ped
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped FALSE
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
								ENDIF
							   ryd1_safe_audio_played = 1
							   ryd1_track_playing = 3
							ENDIF
						ENDIF
						BREAK
				ENDSWITCH
			ENDIF
		ENDIF
		 
	ELSE
		IF ryd1_track_playing = 2
			// player has regained a wanted level //
			IF ryd1_wanted_audio_played = 0
				SWITCH ryd1_wanted_audio_counter
					CASE 0
						IF ryd1_audio_playing = 0
							IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped TRUE
							SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
							ENDIF
							ryd1_audio_counter = 64 //RYD1_HA//You blown it, CJ, with your crazy-assed driving!	
							ryd1_wanted_audio_counter = 1
							GET_GAME_TIMER ryd1_text_timer_start
						ENDIF
						BREAK
					CASE 1
						GET_GAME_TIMER ryd1_text_timer_end
						ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
						IF ryd1_text_timer_diff > 1000
							IF ryd1_audio_playing = 0
								ryd1_audio_counter = 65 //RYD1_HB//Back to the Pay'n'Spray!
								ryd1_wanted_audio_counter = 2
								GET_GAME_TIMER ryd1_text_timer_start
							ENDIF
						ENDIF	
						BREAK																								
					DEFAULT
						GET_GAME_TIMER ryd1_text_timer_end
						ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
						IF ryd1_text_timer_diff > 1000
							IF ryd1_audio_playing = 0
							   IF NOT IS_CHAR_DEAD ryd1_ryder_ped
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped FALSE
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
								ENDIF
							   ryd1_wanted_audio_played = 1
							ENDIF
						ENDIF
						BREAK
				ENDSWITCH
			ENDIF			
		ELSE
			IF ryd1_track_playing = 3
				// while driving to the lockup //
				IF ryd1_lockup_audio_played = 0
					IF NOT IS_MESSAGE_BEING_DISPLAYED
						SWITCH ryd1_lockup_audio_counter
							CASE 0
								IF ryd1_audio_playing = 0
									IF NOT IS_CHAR_DEAD ryd1_ryder_ped
									SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped TRUE
									SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
									ENDIF
									ryd1_audio_counter = 53 //RYD1_GA//Seriously, Ryder, you gotta give up the sherms!	
									ryd1_lockup_audio_counter = 1
									GET_GAME_TIMER ryd1_text_timer_start
								ENDIF
								BREAK
							CASE 1
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
										ryd1_audio_counter = 54 //RYD1_GB//Tell you what. I'll give up the water if you give being a buster!
										ryd1_lockup_audio_counter = 2
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF	
								BREAK
							CASE 2
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 55 //RYD1_GC//Now hold it right there, homie...
										ryd1_lockup_audio_counter = 3
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 3
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 56 //RYD1_GD//Buster!
										ryd1_lockup_audio_counter = 4
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 4
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 57 //RYD1_GE//Forget it.
										ryd1_lockup_audio_counter = 5
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 5
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 58 //RYD1_GF//Forget you first!
										ryd1_lockup_audio_counter = 6
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 6
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 59 //RYD1_GG//One day you're gonna wish you hadn't pissed me off!
										ryd1_lockup_audio_counter = 7
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 7
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 60 //RYD1_GH//Can I hear something?
										ryd1_lockup_audio_counter = 8
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 8
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 61 //RYD1_GJ//Sounds like a buster complaining all the damn time!
										ryd1_lockup_audio_counter = 9
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 9
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 62 //RYD1_GK//This is childish.
										ryd1_lockup_audio_counter = 10
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK
							CASE 10
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0  
										ryd1_audio_counter = 63 //RYD1_GL//I ain't talking to you no more.
										ryd1_lockup_audio_counter = 11
										GET_GAME_TIMER ryd1_text_timer_start
									ENDIF
								ENDIF
								BREAK																
							DEFAULT
								GET_GAME_TIMER ryd1_text_timer_end
								ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
								IF ryd1_text_timer_diff > 1000
									IF ryd1_audio_playing = 0
									   IF NOT IS_CHAR_DEAD ryd1_ryder_ped
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped FALSE
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
										ENDIF
									   ryd1_lockup_audio_played = 1
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





///////////////////////////////////////////
///////////////////////////////////////////
///////////////////////////////////////////

 
	IF NOT IS_CHAR_DEAD ryd1_ryder_ped
		IF NOT IS_CAR_DEAD ryd1_truck_car
			IF NOT IS_CHAR_IN_CAR scplayer ryd1_truck_car
				ryd1_player_in_van_flag = 0
				/// remove all the other blips ///
				IF ryd1_truck_blip_added_flag = 0
					IF ryd1_home_blip_added_flag = 1
						REMOVE_BLIP ryd1_home_blip
						ryd1_home_blip_added_flag = 0
					ENDIF

					IF ryd1_spray_blip_added_flag = 1
						REMOVE_BLIP ryd1_spray_blip
						ryd1_spray_blip_added_flag = 0
					ENDIF
					
					IF ryd1_ryder_blip_added_flag = 1
						REMOVE_BLIP ryd1_ryder_blip
						ryd1_ryder_blip_added_flag = 0
					ENDIF
					
					/// add blip for the truck ///
					
					
					IF IS_MESSAGE_BEING_DISPLAYED
					ryd1_message_on_screen = 1
					ENDIF
					CLEAR_PRINTS
					PRINT (GUNS128) 2000 1 //"You left the truck.."
					SET_PLAYER_GROUP_TO_FOLLOW_NEVER player1 True
					ADD_BLIP_FOR_CAR	ryd1_truck_car ryd1_truck_blip 
					SET_BLIP_AS_FRIENDLY ryd1_truck_blip TRUE
					ryd1_truck_blip_added_flag = 1
					ryd1_audio_counter = 76
			   	   	ryd1_audio_playing = 0
					CLEAR_MISSION_AUDIO ryd1_audio_slot1
					CLEAR_MISSION_AUDIO ryd1_audio_slot2
					
					ryd1_track_playing = 99
				
					
				ENDIF				
								
			ELSE		// player is in car
				ryd1_player_in_van_flag = 1
				IF ryd1_truck_blip_added_flag = 1					
					SET_PLAYER_GROUP_TO_FOLLOW_NEVER player1 FALSE
					REMOVE_BLIP ryd1_truck_blip
					ryd1_track_playing = 3
					ryd1_truck_blip_added_flag = 0
				ELSE
					IF NOT IS_CHAR_IN_CAR ryd1_ryder_ped ryd1_truck_car
						ryd1_ryder_in_van_flag = 0
						IF ryd1_ryder_blip_added_flag = 0
							/// remove all the other blips ///
											
							IF ryd1_home_blip_added_flag = 1
								REMOVE_BLIP ryd1_home_blip
								ryd1_home_blip_added_flag = 0
							ENDIF

							IF ryd1_spray_blip_added_flag = 1
								REMOVE_BLIP ryd1_spray_blip
								ryd1_spray_blip_added_flag = 0
							ENDIF

							/// add blip for ryder ///
														
							IF IS_MESSAGE_BEING_DISPLAYED
					   	   		ryd1_message_on_screen = 1
					   	   	ENDIF
							CLEAR_PRINTS
							PRINT (GUNS143) 2000 1 // "you've left Ryder behind..

							ADD_BLIP_FOR_CHAR ryd1_ryder_ped ryd1_ryder_blip
							SET_BLIP_AS_FRIENDLY ryd1_ryder_blip TRUE
							ryd1_track_playing = 99
							ryd1_ryder_blip_added_flag = 1
						ENDIF

						IF ryd1_ryder_told_enter_car_flag = 0
						  	TASK_ENTER_CAR_AS_PASSENGER ryd1_ryder_ped ryd1_truck_car -2 0
						  	ryd1_ryder_told_enter_car_flag = 1			   
					   ELSE
							GET_SCRIPT_TASK_STATUS ryd1_ryder_ped TASK_ENTER_CAR_AS_PASSENGER ryd1_task_status
							IF NOT ryd1_task_status  = WAITING_TO_START_TASK
						    AND NOT ryd1_task_status  = PERFORMING_TASK
								ryd1_ryder_told_enter_car_flag = 0
							ENDIF
					   ENDIF

					ELSE 		// Ryder with the player
						IF ryd1_ryder_blip_added_flag = 1
							REMOVE_BLIP ryd1_ryder_blip 
							ryd1_ryder_blip_added_flag = 0
							ryd1_track_playing = 3
						ENDIF
						
						//IF ryd1_track_playing > 2

						
						//ENDIF
						
						ryd1_ryder_in_van_flag = 1
					ENDIF	

				ENDIF
			ENDIF
		ENDIF
	ENDIF
	


					

	 
	 
	 	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	/// CHECK PLAYERS LOCATION AND WANTED LEVEL AND PASS MISSION IF NOT WANTED AND AT GANG HOUSE ///
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
		IF ryd1_ryder_in_van_flag = 1
		AND ryd1_player_in_van_flag = 1
			/// location	check


			IF ryd1_home_blip_added_flag = 1	      
				IF LOCATE_CHAR_IN_CAR_3D scplayer  2742.0 -2008.0 13.0  15.0 15.0 4.0 FALSE
					CLEAR_PRINTS
					PRINT (RYD1_34) 3000 1//Park the truck inside the lockup.
					IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer  2741.2412  -2011.2740  13.5869  2.9000  3.2000  5.0000  FALSE 					   	   					   			   

						//////////////////////////////
						/// START OF END CUT SCENE ///
						//////////////////////////////


						SET_PLAYER_CONTROL player1 OFF												
						CLEAR_PRINTS
						DO_FADE 1500 FADE_OUT
						WHILE GET_FADING_STATUS
						WAIT 0
						ENDWHILE
						CLEAR_AREA 2741.2412  -2011.2740  13.5869 10.0 TRUE
						SWITCH_WIDESCREEN ON
//						SET_DARKNESS_EFFECT FALSE -1	

						REMOVE_BLIP ryd1_home_blip




						CLEAR_PRINTS
										

						IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							IF IS_GROUP_MEMBER ryd1_ryder_ped Players_Group
								REMOVE_CHAR_FROM_GROUP ryd1_ryder_ped
							ENDIF
						ENDIF

						LOAD_SCENE 2745.47 -2003.59 15.53

						SET_FIXED_CAMERA_POSITION 2745.47 -2003.59 15.53 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2737.34 -2002.24 11.65 JUMP_CUT
						IF ryd1_clothes_changed = 1
							//RESTORE_CLOTHES_STATE
							GIVE_PLAYER_CLOTHES Player1 0 0 CLOTHES_TEX_EXTRA1
							BUILD_PLAYER_MODEL player1
							ryd1_clothes_changed = 0
						ENDIF
						IF IS_CHAR_IN_ANY_CAR scplayer 
							WARP_CHAR_FROM_CAR_TO_COORD scplayer 2741.0 -2007.14 13.0
						ELSE
							SET_CHAR_COORDINATES scplayer 2741.0 -2008.14 13.0
						ENDIF

						IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							IF IS_CHAR_IN_ANY_CAR ryd1_ryder_ped
								WARP_CHAR_FROM_CAR_TO_COORD ryd1_ryder_ped 2740.0 -2008.14 13.0
							ELSE						 
								SET_CHAR_COORDINATES ryd1_ryder_ped 2740.0 -2007.14 13.0
							ENDIF
						ENDIF


						IF NOT IS_CAR_DEAD ryd1_truck_car
							DELETE_CAR ryd1_truck_car
							MARK_CAR_AS_NO_LONGER_NEEDED ryd1_truck_car
						ENDIF

						CLEAR_AREA 2741.0 -2005.0 14.14 50.0 TRUE
						
						
						
						/////// audio goes in here ////////////
						ryd1_audio_counter	   = 0
						ryd1_audio_slot1 	   = 1
						ryd1_audio_slot2 	   = 2
						ryd1_audio_playing	   = 0
						ryd1_text_loop_done	   = 0
						ryd1_mobile			   = 0
						ryd1_text_timer_diff   = 0
						ryd1_text_timer_end    = 0
						ryd1_text_timer_start  = 0
						ryd1_ahead_counter	   = 0
						
						ryder1_text_loop7:
						WAIT 0 

						IF ryd1_text_loop_done = 0
							
							
							IF NOT ryd1_audio_counter = 0
								IF ryd1_audio_playing = 0
									IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
										CLEAR_MISSION_AUDIO ryd1_audio_slot2
									ENDIF
									ryd1_audio_playing = 1
								ENDIF

								IF ryd1_audio_playing = 1
									LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
									ryd1_audio_playing = 2
								ENDIF

								IF ryd1_audio_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
										PLAY_MISSION_AUDIO ryd1_audio_slot1
										PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
										ryd1_audio_playing = 3
									ENDIF
								ENDIF

								IF ryd1_audio_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
										CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
										IF ryd1_audio_slot1 = 1
											ryd1_audio_slot1 = 2
											ryd1_audio_slot2 = 1
										ELSE
											ryd1_audio_slot1 = 1
											ryd1_audio_slot2 = 2
										ENDIF
										ryd1_audio_counter = 0
										ryd1_audio_playing = 0
									ELSE
										IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
											IF ryd1_audio_counter < 76
												ryd1_ahead_counter = ryd1_audio_counter + 1
												LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							
							SWITCH ryd1_mobile
									CASE 0
										IF ryd1_audio_playing = 0
											IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
												START_CHAR_FACIAL_TALK ryd1_ryder_ped 4000											
												SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryd1_ryder_ped TRUE						
											ENDIF
											ryd1_audio_counter = 66	// PRINT (RYD1_JA) 3000 1// RYDER: You see, simple! //(RYD1_JA)
											ryd1_mobile = 1
											GET_GAME_TIMER ryd1_text_timer_start
											
											IF NOT IS_CHAR_DEAD ryd1_ryder_ped
												OPEN_SEQUENCE_TASK ryd1_player_leave_seq						
												TASK_GO_STRAIGHT_TO_COORD -1 2741.0 -2005.0 14.14 PEDMOVE_WALK 6000
												TASK_GO_STRAIGHT_TO_COORD -1 2741.0 -2001.0  14.14 PEDMOVE_WALK 6000
												TASK_TURN_CHAR_TO_FACE_CHAR -1 ryd1_ryder_ped																								
												CLOSE_SEQUENCE_TASK ryd1_player_leave_seq
												PERFORM_SEQUENCE_TASK scplayer ryd1_player_leave_seq
												CLEAR_SEQUENCE_TASK ryd1_player_leave_seq
																							
												OPEN_SEQUENCE_TASK ryd1_player_leave_seq						
												TASK_GO_STRAIGHT_TO_COORD -1 2740.0 -2005.0 14.14 PEDMOVE_WALK 6000
												TASK_GO_STRAIGHT_TO_COORD -1 2740.0 -2001.0  14.14 PEDMOVE_WALK 6000
												TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer												
												TASK_PLAY_ANIM	-1 IDLE_chat PED 4.0 FALSE FALSE FALSE FALSE -1
												CLOSE_SEQUENCE_TASK ryd1_player_leave_seq
												PERFORM_SEQUENCE_TASK ryd1_ryder_ped ryd1_player_leave_seq
												CLEAR_SEQUENCE_TASK ryd1_player_leave_seq

											ENDIF
											DO_FADE 1500 FADE_IN
											WHILE GET_FADING_STATUS
											WAIT 0
											ENDWHILE
										ENDIF
										BREAK
									CASE 1
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000
											IF ryd1_audio_playing = 0
												IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
													START_CHAR_FACIAL_TALK scplayer 4000
													STOP_CHAR_FACIAL_TALK ryd1_ryder_ped
												ENDIF
												ryd1_audio_counter = 67 //PRINT (RYD1_JB) 3000 1// CARL: Yeah that was a real breeze.	//(RYD1_JB)	
												ryd1_safety_flag = 0
												TIMERA = 0
												ryd1_mobile = 2
												GET_GAME_TIMER ryd1_text_timer_start
											ENDIF
										ENDIF	
										BREAK
									CASE 2
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000																																	
											IF ryd1_audio_playing = 0
												GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  ryd1_task_status
												IF  ryd1_task_status  = FINISHED_TASK
												OR TIMERA > 8000
													IF NOT IS_CHAR_DEAD ryd1_ryder_ped	
													ENDIF
													CLOSE_GARAGE burg_lk
													WHILE NOT IS_GARAGE_CLOSED burg_lk
													WAIT 0 
													ENDWHILE
													IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
													START_CHAR_FACIAL_TALK ryd1_ryder_ped 5000
													STOP_CHAR_FACIAL_TALK scplayer
													ENDIF
													ryd1_audio_counter = 68 //PRINT (RYD1_KA) 5000 1//RYDER: CJ, you gotta get it into your head that this is every day shit.	//(RYD1_KA)	
													ryd1_mobile = 3
													GET_GAME_TIMER ryd1_text_timer_start
																												
												ENDIF																																				
											ENDIF
										ENDIF
										BREAK																		
									DEFAULT
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000
											IF ryd1_audio_playing = 0
											   CLEAR_CHAR_TASKS scplayer
											   IF NOT IS_CHAR_DEAD ryd1_ryder_ped
													CLEAR_CHAR_TASKS ryd1_ryder_ped
											   ENDIF
											   ryd1_text_loop_done = 1	
											ENDIF
										ENDIF
										BREAK
								ENDSWITCH
							
							
							GOTO ryder1_text_loop7
						ENDIF						
														


						////////////////////// end of audio ////////////////////////




						// camera cuts showing houses player can rob

						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE
						
						SET_CHAR_COORDINATES scplayer 2161.0 -1664.0 22.0
						
						SET_FIXED_CAMERA_POSITION 2168.1545 -1686.3741 15.6932  0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2168.0940 -1685.3768 15.7351 JUMP_CUT																										
						REQUEST_COLLISION 2168.1545 -1686.3741
						LOAD_SCENE 2168.1545 -1686.3741 15.6932
						
						DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE

						CLEAR_HELP
						PRINT_HELP_FOREVER (RYD1_88)  // You can perform burglaries at night when not on a mission.												
						WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							WAIT 0 
						ENDWHILE												
						
						TIMERA = 0
						ryd1_safety_flag = 0
						WHILE ryd1_safety_flag = 0
							IF TIMERA > 4000
							OR NOT IS_PLAYER_PLAYING player1
								ryd1_safety_flag = 1
							ENDIF
							WAIT 0
						ENDWHILE


						CLEAR_HELP
						PRINT_HELP_FOREVER (RYD1_90)  // Many houses can be broken into, and goods stolen from the owners.
						WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							WAIT 0 
						ENDWHILE
						TIMERA = 0
						ryd1_safety_flag = 0
						WHILE ryd1_safety_flag = 0
							IF TIMERA > 4000
							OR NOT IS_PLAYER_PLAYING player1
								ryd1_safety_flag = 1
							ENDIF
							WAIT 0
						ENDWHILE

						CLEAR_HELP

						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE
						SET_CHAR_COORDINATES scplayer 2272.0 -1792.0 14.0
						
						SET_FIXED_CAMERA_POSITION 2255.5391 -1783.7028 13.6049  0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2254.8816 -1784.4452 13.7333 JUMP_CUT
						REQUEST_COLLISION  2254.2080 -1790.2325
						LOAD_SCENE 2254.2080 -1790.2325 13.1057
						///////////////////////////////
						/// ADD A BRUGLARY_VAN_HERE ///
						///////////////////////////////
						SWITCH_CAR_GENERATOR gen_car19 0
						CLEAR_AREA 2251.0281 -1788.6610  12.7625 10.0 TRUE
						CREATE_CAR BOXBURG	2251.0281 -1788.6610  12.9625    ryd1_truck_car
						SET_CAR_HEADING ryd1_truck_car 358.9591 
						
						DO_FADE 1000 FADE_IN
						WHILE GET_FADING_STATUS
							WAIT 0
						ENDWHILE


						PRINT_HELP_FOREVER (RYD1_89)  // You need to get in this type of van to start the burglary.					
						WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							WAIT 0 
						ENDWHILE
						TIMERA = 0
						ryd1_safety_flag = 0
						WHILE ryd1_safety_flag = 0
							IF TIMERA > 4000
							OR NOT IS_PLAYER_PLAYING player1
								ryd1_safety_flag = 1
							ENDIF
							WAIT 0
						ENDWHILE

						CLEAR_HELP 

						PRINT_HELP_FOREVER (RYD1_91)  // Load up the truck and then take it back to the lockup.
						WHILE NOT IS_HELP_MESSAGE_BEING_DISPLAYED
							WAIT 0 
						ENDWHILE
						TIMERA = 0
						ryd1_safety_flag = 0
						WHILE ryd1_safety_flag = 0
							IF TIMERA > 4000
							OR NOT IS_PLAYER_PLAYING player1
								ryd1_safety_flag = 1
							ENDIF
							WAIT 0
						ENDWHILE

						CLEAR_HELP

						DO_FADE 1000 FADE_OUT
						WHILE GET_FADING_STATUS
						WAIT 0
						ENDWHILE
						
						IF NOT IS_CAR_DEAD ryd1_truck_car
							DELETE_CAR ryd1_truck_car
							MARK_CAR_AS_NO_LONGER_NEEDED ryd1_truck_car
						ENDIF

						SET_CHAR_COORDINATES scplayer 2741.0 -2001.0  12.5547
						SET_CHAR_HEADING scplayer 80.0						
						
						SWITCH_WIDESCREEN ON
						SET_FIXED_CAMERA_POSITION 2745.47 -2003.59 15.53 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT 2737.34 -2002.24 11.65 JUMP_CUT
						REQUEST_COLLISION 2745.47 -2003.59
						LOAD_SCENE 2745.47 -2003.59 15.53						
						IF NOT IS_CHAR_DEAD ryd1_ryder_ped
							 SET_CHAR_COORDINATES ryd1_ryder_ped 2740.0 -2001.0  12.5547
							 SET_CHAR_HEADING ryd1_ryder_ped 230.0
							
							TASK_TURN_CHAR_TO_FACE_CHAR scplayer ryd1_ryder_ped
							TASK_TURN_CHAR_TO_FACE_CHAR ryd1_ryder_ped scplayer
						ENDIF

						DO_FADE 1000 FADE_IN						
						WHILE GET_FADING_STATUS
						WAIT 0
						ENDWHILE

						
						///////////////////////// audio goes in here /////////////////////////
						ryd1_audio_counter	   = 0
						ryd1_audio_slot1 	   = 1
						ryd1_audio_slot2 	   = 2
						ryd1_audio_playing	   = 0
						ryd1_text_loop_done	   = 0
						ryd1_mobile			   = 0
						ryd1_text_timer_diff   = 0
						ryd1_text_timer_end    = 0
						ryd1_text_timer_start  = 0
						ryd1_ahead_counter	   = 0
						
						ryder1_text_loop8:
						WAIT 0 

						IF ryd1_text_loop_done = 0
							
							
							IF NOT ryd1_audio_counter = 0
								IF ryd1_audio_playing = 0
									IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
										CLEAR_MISSION_AUDIO ryd1_audio_slot2
									ENDIF
									ryd1_audio_playing = 1
								ENDIF

								IF ryd1_audio_playing = 1
									LOAD_MISSION_AUDIO ryd1_audio_slot1 ryd1_audio[ryd1_audio_counter]
									ryd1_audio_playing = 2
								ENDIF

								IF ryd1_audio_playing = 2
								 	IF HAS_MISSION_AUDIO_LOADED ryd1_audio_slot1
										PLAY_MISSION_AUDIO ryd1_audio_slot1
										PRINT_NOW $ryd1_text[ryd1_audio_counter] 10000 1
										ryd1_audio_playing = 3
									ENDIF
								ENDIF

								IF ryd1_audio_playing = 3
									IF HAS_MISSION_AUDIO_FINISHED ryd1_audio_slot1
										CLEAR_THIS_PRINT $ryd1_text[ryd1_audio_counter]
										IF ryd1_audio_slot1 = 1
											ryd1_audio_slot1 = 2
											ryd1_audio_slot2 = 1
										ELSE
											ryd1_audio_slot1 = 1
											ryd1_audio_slot2 = 2
										ENDIF
										ryd1_audio_counter = 0
										ryd1_audio_playing = 0
									ELSE
										IF NOT HAS_MISSION_AUDIO_LOADED ryd1_audio_slot2
											IF ryd1_audio_counter < 76
												ryd1_ahead_counter = ryd1_audio_counter + 1
												LOAD_MISSION_AUDIO ryd1_audio_slot2 ryd1_audio[ryd1_ahead_counter]
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF

							
							SWITCH ryd1_mobile
									CASE 0
										IF ryd1_audio_playing = 0
											IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
												START_CHAR_FACIAL_TALK scplayer 5000
												STOP_CHAR_FACIAL_TALK ryd1_ryder_ped
											ENDIF
											TASK_PLAY_ANIM	scplayer IDLE_chat PED 4.0 FALSE FALSE FALSE FALSE -1
											ryd1_audio_counter = 74	// PRINT (RYD1_KG) 4000 1//CARL: Ok, look, I'm tired, I'll see you later.	//(RYD1_KG)	
											ryd1_mobile = 1
											GET_GAME_TIMER ryd1_text_timer_start
										ENDIF
										BREAK
									CASE 1
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000
											IF ryd1_audio_playing = 0
												IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
													START_CHAR_FACIAL_TALK ryd1_ryder_ped 5000
													STOP_CHAR_FACIAL_TALK scplayer
													CLEAR_CHAR_TASKS scplayer
												ENDIF
												ryd1_audio_counter = 75 //PRINT (RYD1_KH) 4000 1//RYDER: Whatever, holmes, think about what I said  //(RYD1_KH)	
												ryd1_mobile = 2
												TIMERA = 0
												IF NOT IS_CHAR_DEAD ryd1_ryder_ped
													OPEN_SEQUENCE_TASK ryd1_player_leave_seq						
													TASK_GO_STRAIGHT_TO_COORD -1 2761.0 -2002.0 14.0 PEDMOVE_WALK 10000						
													CLOSE_SEQUENCE_TASK ryd1_player_leave_seq
													PERFORM_SEQUENCE_TASK ryd1_ryder_ped ryd1_player_leave_seq
													CLEAR_SEQUENCE_TASK ryd1_player_leave_seq
												ENDIF
												GET_GAME_TIMER ryd1_text_timer_start
											ENDIF
										ENDIF	
										BREAK																		
									DEFAULT
										GET_GAME_TIMER ryd1_text_timer_end
										ryd1_text_timer_diff = ryd1_text_timer_end - ryd1_text_timer_start
										IF ryd1_text_timer_diff > 1000
											IF ryd1_audio_playing = 0
											   IF NOT IS_CHAR_DEAD ryd1_ryder_ped										
													STOP_CHAR_FACIAL_TALK ryd1_ryder_ped
													STOP_CHAR_FACIAL_TALK scplayer
												ENDIF
											   ryd1_text_loop_done = 1	
											ENDIF
										ENDIF
										BREAK
								ENDSWITCH
							
							
							GOTO ryder1_text_loop8							 	
						ENDIF																		

						

						

						ryd1_safety_flag = 0
						
						WHILE	ryd1_safety_flag = 0			  								  		 											
							GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  ryd1_task_status
							IF  ryd1_task_status  = FINISHED_TASK
							OR TIMERA > 6000 
								ryd1_safety_flag = 1
							ENDIF
							WAIT 0 
						ENDWHILE


						
						/////////////////////// end of audio /////////////////////////////
						SWITCH_WIDESCREEN OFF
						SET_PLAYER_CONTROL player1 ON
						RESTORE_CAMERA_JUMPCUT

						SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

						GOTO mission_g1_passed



					//////////////////////////
					//////////////////////////
					//////////////////////////
						 					
					ELSE
						LOCATE_CHAR_ANY_MEANS_3D scplayer  2741.0 -2010.0 13.5  4.0  4.0  5.0000  TRUE	
						IF NOT IS_GARAGE_OPEN burg_lk
							OPEN_GARAGE burg_lk
						ENDIF
					ENDIF					   
				ELSE
					LOCATE_CHAR_ANY_MEANS_3D scplayer  2742.0 -2000.0 13.0  4.0  4.0  5.0000  TRUE
					IF NOT IS_GARAGE_CLOSED burg_lk
						CLOSE_GARAGE burg_lk
					ENDIF
				ENDIF



			ENDIF
		/// wanted level check

		IF IS_WANTED_LEVEL_GREATER player1 0
			ryd1_safe_audio_played = 0
			IF ryd1_owner_awake_flag = 1

				IF LOCATE_CHAR_IN_CAR_2D scplayer 2067.4 -1831.2  15.0 15.0 FALSE // spray shop

					IF ryd1_cops_attacking_flag = 1  // scripted cops chasing player
					AND ryd1_player_in_spray_shop_flag = 0
						IF NOT IS_CHAR_DEAD ryd1_cop1_ped
						AND NOT IS_CAR_DEAD ryd1_cop_car
						AND NOT IS_CAR_DEAD ryd1_truck_car
							SWITCH_CAR_SIREN  ryd1_cop_car OFF
							TASK_CAR_MISSION ryd1_cop1_ped ryd1_cop_car ryd1_truck_car MISSION_EMERGENCYVEHICLE_STOP 20.0 DRIVINGMODE_STOPFORCARS		     		     				     	
						ENDIF		  										

						ryd1_player_in_spray_shop_flag = 1
						ryd1_cops_attacking_flag = 0
					ENDIF

				ELSE

					IF ryd1_player_in_spray_shop_flag = 1
					AND ryd1_cops_attacking_flag = 0
						IF NOT IS_CHAR_DEAD ryd1_cop1_ped
						AND NOT IS_CAR_DEAD ryd1_cop_car
						AND NOT IS_CAR_DEAD ryd1_truck_car
							SWITCH_CAR_SIREN  ryd1_cop_car ON
							TASK_CAR_MISSION ryd1_cop1_ped ryd1_cop_car ryd1_truck_car MISSION_RAMPLAYER_CLOSE 33.0 DRIVINGMODE_PLOUGHTHROUGH		     		     				     	
						ENDIF
						ryd1_player_in_spray_shop_flag = 0
						ryd1_cops_attacking_flag = 1
					ENDIF
				ENDIF
			ENDIF //IF ryd1_owner_awake_flag = 1   	      	      	      

			IF ryd1_spray_blip_added_flag = 0 
				IF ryd1_home_blip_added_flag = 1
					REMOVE_BLIP ryd1_home_blip
					CLEAR_PRINTS 
					ryd1_track_playing = 2
					//PRINT (RYD1_HA) 4000 1 //RYDER: You blown, CJ, with your crazy-assed driving!
					//PRINT (RYD1_HB) 4000 1 // ~n~Back to the Pay' n' Spray!	 //(RYD1_HA) //(RYD1_HB)	
					ryd1_home_blip_added_flag = 0
				ENDIF	
				ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY  ryd1_spray_blip
				ryd1_spray_blip_added_flag = 1
				CLEAR_PRINTS
				PRINT (GUNS123) 5000 1 // "lose cops"
				IF ryd1_spray_message_played = 1					
					ryd1_track_playing = 2	
					ryd1_spray_message_played = 0					
				ENDIF 
			ENDIF

		ELSE	  //IS_WANTED_LEVEL_GREATER player1 0

			//////////////////////////////////////////////
			//////////////////////////////////////////////
			//////////////////////////////////////////////
			ryd1_wanted_audio_played  = 0

			IF ryd1_message_on_screen = 1
				IF ryd1_message_displayed < 11
					PRINT (GUNS135) 4000 1 //As I was saying....
					ryd1_message_displayed--
					IF ryd1_message_displayed < 0			   		
						ryd1_message_displayed = 0
					ENDIF
						ryd1_message_on_screen = 0					
					ENDIF
				ENDIF
				
				

				///////////////////////////////////////////////////
				///////////////////////////////////////////////////
				///////////////////////////////////////////////////	

				IF ryd1_home_blip_added_flag = 0
					IF ryd1_spray_blip_added_flag = 1
						REMOVE_BLIP ryd1_spray_blip	
						ryd1_spray_blip_added_flag = 0
					ENDIF
					CLEAR_PRINTS


					IF LOCATE_CHAR_IN_CAR_2D scplayer 2067.4 -1831.2  15.0 15.0 FALSE // spray shop			 	
						WAIT 3000 				
						PRINT (GUNS124) 5000 1 // "get home"
					ELSE
						PRINT (GUNS124) 5000 1 // "get home"
					ENDIF



					IF ryd1_spray_message_played = 0
						IF  ryd1_owner_awake_flag = 1																					 																																																	
							ryd1_track_playing = 1						
						ENDIF
					ELSE
						ryd1_track_playing = 3
					ENDIF
					ADD_BLIP_FOR_COORD  2742.0 -2008.0 13.0 ryd1_home_blip    		   	 					 
					ryd1_home_blip_added_flag = 1
				ENDIF

				IF ryd1_cops_attacking_flag = 1  // scripted cops chasing player
				AND ryd1_player_in_spray_shop_flag = 0
					IF NOT IS_CHAR_DEAD ryd1_cop1_ped
					AND NOT IS_CAR_DEAD ryd1_cop_car
					AND NOT IS_CAR_DEAD ryd1_truck_car
						SWITCH_CAR_SIREN  ryd1_cop_car OFF
						TASK_CAR_MISSION ryd1_cop1_ped ryd1_cop_car ryd1_truck_car MISSION_CRUISE 20.0 DRIVINGMODE_AVOIDCARS		     		     		     	
						ryd1_cops_attacking_flag = 0
					ENDIF
				ENDIF

			ENDIF //IF IS_WANTED_LEVEL_GREATER player1 0

		ENDIF //AND ryd1_ryder_in_van_flag = 1
	ENDIF //IF ryd1_mission_progression_flag = 8 


		
		
		
		/////////////////////////////////////////////
		/// FUDGE TO STOP PEDS GENERATED IN HOUSE ///
		///////////////////////////////////////////// 
		/*
		GET_AREA_VISIBLE ryd1_area_name
		IF ryd1_area_name  = 0
			 IF ryd1_inside_house_flag = 1
				 	IF NOT IS_CAR_DEAD ryd1_cop_car
						FREEZE_CAR_POSITION ryd1_cop_car FALSE
					ENDIF
				 	SET_PED_DENSITY_MULTIPLIER 1.0
					SET_CAR_DENSITY_MULTIPLIER 1.0 // normally 1.0
					ryd1_inside_house_flag = 0
			ENDIF
		ELSE
			IF ryd1_area_name = 8
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2809.0 -1169.0 26.0 30.0 30.0 30.0 FALSE				
					IF ryd1_inside_house_flag = 0
						IF NOT IS_CAR_DEAD ryd1_cop_car
							FREEZE_CAR_POSITION ryd1_cop_car TRUE
						ENDIF
						SET_PED_DENSITY_MULTIPLIER 0.0
						SET_CAR_DENSITY_MULTIPLIER 0.0 // normally 1.0			
						CLEAR_AREA 2819.9 -1181.7 25.15 50.0 TRUE
						ryd1_inside_house_flag = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		*/

		///////////////////////////////////////////////
		///////////////////////////////////////////////



//VIEW_INTEGER_VARIABLE ryd1_boxes_left boxes_left
GOTO ryder1_main_loop

/////////////////////////
//// EXTRA FUNCTIONS ////
/////////////////////////

ryder1_leaving_house_cut_scene:



RETURN






// **************************** MISSION FAILED ***********************
	
		
		mission_g1_failed:
		PRINT_BIG M_FAIL 5000 1
		//GOTO mission_cleanup_g1 		
		
		RETURN
		


// ***************************** MISSION PASSED **********************

		   
		// mission pp1 passed
		mission_g1_passed:
		//flag_pp1_mission1_passed = 1
		ryd1_reward = ryd1_number_gunbox_truck * 50
		//PRINT_WITH_NUMBER_BIG M_PASS ryd1_reward 5000 1		
		PRINT_WITH_NUMBER_BIG ( M_PASSR ) 3 5000 1 //"Mission Passed!"
		AWARD_PLAYER_MISSION_RESPECT 3//amount of respect


		SET_POLICE_IGNORE_PLAYER player1 OFF
		//ADD_SCORE player1 ryd1_reward
		CLEAR_WANTED_LEVEL player1
		PLAY_MISSION_PASSED_TUNE 1
		PLAYER_MADE_PROGRESS 1
		REGISTER_MISSION_PASSED ( RYDER_1 )
		
	   

		flag_ryder_mission_counter ++


	   //	GOTO mission_cleanup_g1 
		RETURN	

	  

	 // **************************************** start MISSION CLEANUP *********************************

	mission_cleanup_g1:
		
		flag_player_on_mission = 0
		disable_mod_garage = 0
		
		GET_GAME_TIMER timer_mobile_start
		SWITCH_CAR_GENERATOR gen_car19 101
		
		 SET_RADAR_ZOOM 0
		 
		 IF IS_PLAYER_PLAYING player1			   
		 	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE
		 ENDIF
		 SET_POLICE_IGNORE_PLAYER player1 OFF		 
		 SWITCH_ENTRY_EXIT burhous TRUE

		 HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
	/////////////////////
	/// REMOVE BLIPS ///
	////////////////////
  		REMOVE_BLIP ryd1_ryder_blip
		REMOVE_BLIP ryd1_house_blip
		REMOVE_BLIP ryd1_truck_blip
		REMOVE_BLIP ryd1_door_blip
		REMOVE_BLIP ryd1_spray_blip
		REMOVE_BLIP ryd1_home_blip
		REMOVE_BLIP ryd1_gunbox_blip[0]
		REMOVE_BLIP ryd1_gunbox_blip[1]
		REMOVE_BLIP ryd1_gunbox_blip[2]
		REMOVE_BLIP ryd1_gunbox_blip[3]
		REMOVE_BLIP ryd1_gunbox_blip[4]
		REMOVE_BLIP ryd1_gunbox_blip[5]
		REMOVE_BLIP ryd1_gunbox_blip[6]


		//ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
	////////////////////
	/// REMOVE_GROUP ///
	////////////////////
 
 	//////////////////////////////
	/// CLEAR_ONSCREEN_COUNTER ///
	//////////////////////////////
		CLEAR_ONSCREEN_COUNTER  ryd1_noise_bar
		DISPLAY_RADAR TRUE
		DISPLAY_HUD TRUE
	//////////////////////////////
	///  CLEAR_ONSCREEN_TIMER  ///
	//////////////////////////////
		CLEAR_ONSCREEN_TIMER ryd1_time_to_daylight
	///////////////////////////////////////
	/// MARK MODELS AS NO LONGER NEEDED ///
	///////////////////////////////////////

		
		
		MARK_MODEL_AS_NO_LONGER_NEEDED BOXBURG
		MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
		MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
		MARK_MODEL_AS_NO_LONGER_NEEDED wmopj
		MARK_MODEL_AS_NO_LONGER_NEEDED LAPD1
		MARK_MODEL_AS_NO_LONGER_NEEDED COPCARLA
		MARK_MODEL_AS_NO_LONGER_NEEDED db_ammo
		
		
		REMOVE_ANIMATION CARRY
		REMOVE_ANIMATION GANGS
		REMOVE_ANIMATION POLICE 
		REMOVE_ANIMATION INT_HOUSE
		REMOVE_ANIMATION ON_LOOKERS
		REMOVE_ANIMATION CAR_CHAT

		REMOVE_CHAR_ELEGANTLY ryd1_ryder_ped

		MARK_CHAR_AS_NO_LONGER_NEEDED ryd1_owner_ped
		MARK_CHAR_AS_NO_LONGER_NEEDED ryd1_cop1_ped
		MARK_CHAR_AS_NO_LONGER_NEEDED ryd1_cop2_ped
		
		
		MARK_CAR_AS_NO_LONGER_NEEDED ryd1_cop_car
		MARK_CAR_AS_NO_LONGER_NEEDED ryd1_truck_car
		

		MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[0]
		MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[1]
		MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[2]
		MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[3]
		MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[4]
		MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[5]
		MARK_OBJECT_AS_NO_LONGER_NEEDED ryd1_gunbox_obj[6]
	   	
		IF NOT IS_CHAR_DEAD ryd1_owner_ped																
				SET_CHAR_DECISION_MAKER ryd1_owner_ped ryd1_decision3								
				SET_CHAR_RELATIONSHIP ryd1_owner_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				//SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1 PEDTYPE_PLAYER1				
		ENDIF
		UNLOAD_SPECIAL_CHARACTER 1				 
		REMOVE_DECISION_MAKER ryd1_decision
		REMOVE_DECISION_MAKER ryd1_decision2
		REMOVE_DECISION_MAKER ryd1_decision3
		REMOVE_DECISION_MAKER ryd1_group_decision

	 //REMOVE_DECISION_MAKER 
 	 //REMOVE_THREAT_LIST threat_buddy_g1
		//SWITCH_PED_ROADS_ON 2775.0 -1196.40 20.0 2840.0 -1141.0 35.0 
		//SWITCH_ROADS_BACK_TO_ORIGINAL
		SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2775.0 -1196.40 20.0 2840.0 -1141.0 35.0 

//		CLEAR_ALL_VIEW_VARIABLES

		
		MISSION_HAS_FINISHED

		RETURN
}


//////////////////////////////////////////
////////////////// TEXT LABELS ///////////
//////////////////////////////////////////
/*

PRINT (GUNS1_5) 3000 1 //Noise Gauge
PRINT (GUNS1_6) 3000 1 //SMITH: ZZZZZZzzzzZZ
PRINT (GUNS1_7) 3000 1 //SMITH: I'm sure I heard a noise....
PRINT (GUNS111) 3000 1 //SMITH: I heard you for sure that time, I'm phoning the cops! 
PRINT (GUNS117) 3000 1 //Ryder: Ok, seems like you got everything. Now lets get out of here!
PRINT (GUNS120) 3000 1 //~t~ = Break Open Door.
PRINT (GUNS121) 3000 1 //Ryder: Quit playing, fool.
PRINT (GUNS122) 3000 1 //~r~The truck has been destroyed!
PRINT (GUNS123) 3000 1 //~s~You got too much heat on you. Lose the cops.
PRINT (GUNS124) 3000 1 //~s~Get safely back to the ~y~lockup~s~.
PRINT (GUNS125) 3000 1 //~r~Ryder's dead!
PRINT (GUNS128) 3000 1 //~s~You left the ~b~truck~s~! Get back to it!
PRINT (GUNS129) 3000 1 //Ryder: Ok, you gotta go through that door.
PRINT (GUNS130) 3000 1 //~s~Park the truck near the ~y~house~s~.
PRINT (GUNS134) 3000 1 // ~s~You're making too much noise, you have to sneak!
PRINT (GUNS135) 3000 1 //RYDER: As I was saying....
PRINT (GUNS136) 3000 1 //RYDER: Ok, we need 3 more crates
PRINT (GUNS137) 3000 1 //RYDER: Faster man, this snooping around is giving me the willies.
PRINT (GUNS138) 3000 1 //RYDER:We need one more bro, then we're out of here.
PRINT (GUNS143) 3000 1 //~s~You've left ~b~Ryder~s~ behind go and get him.
PRINT (GUNS146) 3000 1 //~s~Take the guns back to the ~y~truck~s~.
PRINT (GUNS147) 3000 1 //~s~Go and fetch another box.

PRINT (RYD1_18) 3000 1 //SMITH: Gooks!
PRINT (RYD1_19) 3000 1 //RYDER: Jesus Christ! Can't stop me!																									   
PRINT (RYD1_20) 3000 1 // SMITH: Get off my ridge, you viet cong bastards! 																 
PRINT (RYD1_21) 3000 1 //CARL: Let's get outta here.																																			 
PRINT (RYD1_22) 3000 1 //RYDER: I'll fight him.																																								
	 
PRINT (RYD1_30) 3000 1 //Walk over to the truck to drop the item.
PRINT (RYD1_32) 3000 1 //~r~There aren't enough guns left to fill the truck!
PRINT (RYD1_34) 3000 1 //~s~Park the truck inside the ~y~lockup~s~.
PRINT (RYD1_35) 3000 1 //~r~ You've waited too long to get to the house, it's no longer night time!
PRINT (RYD1_36) 3000 1 //SMITH: Morning already.. wait a minute something doesn't feel right here!
PRINT (RYD1_37) 3000 1 //SMITH: This one's for Kenny!?
PRINT (RYD1_39) 3000 1 //Enther the ~y~house~s~.
PRINT (RYD1_40) 3000 1 //Find a crate.
PRINT (RYD1_41) 3000 1 //DAYLIGHT:
PRINT (RYD1_42) 3000 1 //~r~You took too long to get the guns, it's no longer night time!
PRINT (RYD1_43) 3000 1 //~r~You're not going to be able to get the guns now that he's awake!
*/

