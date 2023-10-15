MISSION_START
// *****************************************************************************************
// ************************************* GARAGE 1		 ***********************************
// *************************************   WELCOME TO SAN FRAN *****************************
// *****************************************************************************************
// *****************************************************************************************

// Mission start stuff

SCRIPT_NAME garag1	

GOSUB mission_start_garag1

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_garag1_failed
ENDIF

GOSUB mission_cleanup_garag1

MISSION_END


// Variables for mission

{

//RC VEHICLES	
LVAR_INT car_player_garag1
LVAR_INT car_mech2_garag1
LVAR_INT car_mech1_garag1
LVAR_INT rc_heli_garage1
LVAR_INT car_zombietech_garage1


LVAR_INT car_mech3_garag1

LVAR_FLOAT heading_car_player_garage1
LVAR_INT object_zeros_bench	dwaynes_blunt

LVAR_FLOAT heading_players_car


LVAR_INT noise_of_board_rolling

// COORDS
LVAR_FLOAT coords_hospital_x coords_hospital_y coords_hospital_z 
LVAR_FLOAT coords_police_x coords_police_y coords_police_z 
LVAR_FLOAT  mech_offset_x mech_offset_y mech_offset_z

LVAR_FLOAT coords_garage_garag1_x[5] coords_garage_garag1_y[5] coords_garage_garag1_z[5]
LVAR_FLOAT coords_hub_garag1_x coords_hub_garag1_y coords_hub_garag1_z

LVAR_FLOAT coord_mech_garage1_x[5] coord_mech_garage1_y[5] coord_mech_garage1_z[5]

LVAR_FLOAT offset_players_car_x offset_players_car_y offset_players_car_z
LVAR_FLOAT offset_players_car2_x offset_players_car2_y offset_players_car2_z


// CHARS
LVAR_INT char_truth_garag1 
LVAR_INT char_mech_garag1[4]
LVAR_INT driver_of_car_mech3_garag1
LVAR_INT pony_driver_garage1


LVAR_INT temp_passenger_to_be_deleted_garage1
 
// FLAGS

LVAR_INT flag_go_to_mechanic1_garag1
LVAR_INT flag_go_to_mechanic2_garag1
LVAR_INT flag_go_to_mechanic3_garag1
LVAR_INT flag_go_to_mechanic4_garag1
LVAR_INT flag_go_to_hospital_garag1
LVAR_INT flag_go_to_police_station_garag1
LVAR_INT flag_go_to_hub_garag1 
LVAR_INT flag_loose_mech3_garag1

LVAR_INT flag_mission_garag1_passed  
LVAR_INT flag_mission_garag1_failed 	

LVAR_INT flag_is_mech3_off_bike_garag1 
LVAR_INT flag_player_out_of_car_garag1 

LVAR_INT flag_garage_doors
LVAR_INT flag_cutscene_garage1	
VAR_INT flag_is_zero_on_bike 

LVAR_INT flag_is_truth_in_car
LVAR_INT flag_is_mechanic2_in_car
LVAR_INT flag_is_mechanic1_in_car

LVAR_INT flag_help_text_garage1 

LVAR_INT flag_mech_summoned_garage1

LVAR_INT flag_lod_close_to_desstination 

LVAR_INT flag_text_garage1



// BLIP
LVAR_INT blip_mech_garag1[5]
LVAR_INT blip_players_car_garag1
LVAR_INT blip_hospital blip_police

// SEQUENCES

LVAR_INT seq_truth_talking_to_mech1_garag1  //jethro
LVAR_INT seq_truth_talking_to_mech2_garag1
LVAR_INT seq_truth_talking_to_mech4_garag1 //zero
LVAR_INT seq_mech1_garage1


LVAR_INT index_garag1


// DECISION MAKERS
LVAR_INT dm_buddies_garage1


// AUDIO 

LVAR_INT garage1_audio[106] 
VAR_TEXT_LABEL $garage1_text[106]
LVAR_INT char_who_is_talking[106]

LVAR_INT index_dialogue_garage1

LVAR_INT play_audio_flag_garage1


// SMoke effects

LVAR_INT exhale_smoke_effect



// ****************************************Mission Start************************************

mission_start_garag1:

	flag_player_on_mission = 1

	FORCE_WEATHER_NOW WEATHER_SUNNY_SF	  // can be wither sunny, foggy or extra sunny


	REGISTER_MISSION_GIVEN
	 
	LOAD_MISSION_TEXT GARAGE1

	SET_PLAYER_CONTROL player1 OFF 
	
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE


// CUTSCENE
		
		SET_AREA_VISIBLE 1

		LOAD_CUTSCENE GARAG1B
		 
		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
		 
		START_CUTSCENE

		DO_FADE 1000 FADE_IN
		WAIT 1000  
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

// end cutscene


	LOAD_SPECIAL_CHARACTER 3 jethro

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 3
		WAIT 0
	ENDWHILE   


	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm_buddies_garage1 
	
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddies_garage1 EVENT_POTENTIAL_GET_RUN_OVER 
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddies_garage1 EVENT_POTENTIAL_WALK_INTO_PED 
	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_buddies_garage1 EVENT_POTENTIAL_WALK_INTO_VEHICLE
	
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 3
	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 3

// INITIALISING VARIABLES


coords_garage_garag1_x[0] = -1669.0 	  // GARAGE IN VIETNAM
coords_garage_garag1_y[0] =	439.773 
coords_garage_garag1_z[0] =	6.219

heading_players_car = 80.0

coords_garage_garag1_x[1] =	-2230.1680 	   // in china town
coords_garage_garag1_y[1] =	544.7148 
coords_garage_garag1_z[1] =	34.2219

coords_garage_garag1_x[2] =	-2248.1006	 // zeros toyshop
coords_garage_garag1_y[2] =	132.8547 
coords_garage_garag1_z[2] =	35.3302

coords_garage_garag1_x[3] =	-2052.1006 	
coords_garage_garag1_y[3] =	-147.8547 
coords_garage_garag1_z[3] =	28.3302

coords_garage_garag1_x[4] =	-2059.5530 
coords_garage_garag1_y[4] =	-22.1661 
coords_garage_garag1_z[4] =	34.3050 

coords_hospital_x =	-2584.0
coords_hospital_y = 585.0
coords_hospital_z = 14.0

coords_police_x = -1624.41
coords_police_y = 725.25
coords_police_z = 14.11

coords_hub_garag1_x = -2042.03 
coords_hub_garag1_y = 178.11 
coords_hub_garag1_z	= 28.15

flag_go_to_mechanic1_garag1 = 1
flag_go_to_mechanic2_garag1 = 0
flag_go_to_mechanic3_garag1	= 0
flag_go_to_mechanic4_garag1 = 0
flag_go_to_hub_garag1 = 0
flag_go_to_hospital_garag1 = 0
flag_go_to_police_station_garag1 = 0

flag_cutscene_garage1 = 0

flag_lod_close_to_desstination = 0

flag_text_garage1 = 0

flag_garage_doors = 0

flag_mission_garag1_passed = 0
flag_mission_garag1_failed = 0
flag_player_out_of_car_garag1 = 0

flag_loose_mech3_garag1 = 0
flag_is_mech3_off_bike_garag1 = 0
flag_is_zero_on_bike = 0

flag_is_truth_in_car = 0
flag_is_mechanic2_in_car = 0
flag_is_mechanic1_in_car = 0

flag_help_text_garage1 = 0


coord_mech_garage1_x[0] = -1674.21  // viet garage
coord_mech_garage1_y[0] = 438.50 
coord_mech_garage1_z[0]	= 6.2 

coord_mech_garage1_x[1] = -2237.0 	   // triad garage
coord_mech_garage1_y[1] = 542.0782 
coord_mech_garage1_z[1]	= 33.901 

coord_mech_garage1_x[2] = -2243.1006	 // zero's toyshop
coord_mech_garage1_y[2] = 128.8547 
coord_mech_garage1_z[2]	= 34.3302

coord_mech_garage1_x[3] = -2052.1006 		//hub
coord_mech_garage1_y[3] = -147.8547 
coord_mech_garage1_z[3]	= 28.3302

flag_mech_summoned_garage1 = 0

index_dialogue_garage1 = 0

index_garag1 = 0

play_audio_flag_garage1 = 0

IF play_audio_flag_garage1 = 1
	CREATE_CHAR PEDTYPE_CIVMALE 0 0.0 0.0 0.0 char_who_is_talking[0]
	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL02 -2031.2517 164.0169 27.8429 char_truth_garag1
	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL03 coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] char_mech_garag1[0]
ENDIF


// intro
$garage1_text[0] = GAR1_AA//C’mon. there’s these two guys I know – used to work on marine engines,
$garage1_text[1] = GAR1_AB//until the mob bought their business over in Vice.
$garage1_text[2] = GAR1_AC//Now they try and make ends meet taking any old job.
$garage1_text[3] = GAR1_AD//They’re a little dulled by their habit,
$garage1_text[4] = GAR1_AE//but the smoke don’t get in the way of their skills with an engine.
$garage1_text[5] = GAR1_AF//We’ll pick up Jethro first.
$garage1_text[6] = GAR1_AG//Last I heard he was working at a garage over Easter Basin way.

// not needed
$garage1_text[7] = GAR1_AH//Last I heard he was working at a garage over East Esplanade way.
$garage1_text[8] = GAR1_AJ//Last I heard he was working at a garage over North Esplanade way.

// driving to mech 1
$garage1_text[9] = GAR1_BA//	How’d you meet these dudes?
$garage1_text[10] = GAR1_BB//	I met them at the ’89 Fierro Love-in, apparently.
$garage1_text[11] = GAR1_BC//	Apparently?
$garage1_text[12] = GAR1_BD//	You know how it is; 
$garage1_text[13] = GAR1_BE//	a field of tents, crazy-assed music, 
$garage1_text[14] = GAR1_BF//	a quart of mescaline vodka, polar bears…
$garage1_text[15] = GAR1_BG//	Polar bears?
$garage1_text[16] = GAR1_BH//	Yeah. Go figure. But they were funny guys – great sense of humour.
			  

// entering vietnam
$garage1_text[17] = GAR1_CA//This here’s Vietnamese gang territory. 
$garage1_text[18] = GAR1_CB//Da Nang Boys, Shining Razors, Butterfly Children.
$garage1_text[19] = GAR1_CC//Watch yourself, these cats are real serious.


//picking up mech 1
$garage1_text[20] = GAR1_DA//Hey, Jethro!
$garage1_text[21] = GAR1_DF//Hop in. I’ve landed you a real job.

$garage1_text[22] = GAR1_DB//Hey there Truth dude!
$garage1_text[23] = GAR1_DC//Oh man, do I owe you?
$garage1_text[24] = GAR1_DD//Coz I swear I paid for that weed, dude!
$garage1_text[25] = GAR1_DE//No, man, we’re good – I think.
			  
$garage1_text[26] = GAR1_EA//Jethro, Carl. Carl, jethro.
$garage1_text[27] = GAR1_EB//Yo, man.
$garage1_text[28] = GAR1_EC//Can we swing by the hospital, it’s over in Santa Flora district, West of here.
$garage1_text[29] = GAR1_ED//Yeah sure, you ill?
$garage1_text[30] = GAR1_EE//	No. The government is, but that’s a long story.

// driving to hospitl
$garage1_text[31] = GAR1_FA//	So, like, y’know, what’s the deal, dudes?
$garage1_text[32] = GAR1_FB//	I’m opening a garage in Doherty by that derelict ground.
$garage1_text[33] = GAR1_FC//	We’re gonna do car mods, lowriders, all that shit.
$garage1_text[34] = GAR1_FD//	You down?
$garage1_text[35] = GAR1_FE//	Do polar bears shit in the woods?
$garage1_text[36] = GAR1_FF//	No, but they’ve been known to shit in the liquor tent, if I remember rightly.
$garage1_text[37] = GAR1_FG//	Yeah, that was, like, so far gone, man.
			  

// at the hospital
$garage1_text[38] = GAR1_GA//	What we here for?
$garage1_text[39] = GAR1_GB//	Nothin’.
$garage1_text[40] = GAR1_GC//	Don’t look.
$garage1_text[41] = GAR1_GD//	Cover your faces.
$garage1_text[42] = GAR1_GE//	Think about a yellow rubber duck.
$garage1_text[43] = GAR1_GF//	You tripping again?
$garage1_text[44] = GAR1_GG//	Sshhh!
$garage1_text[45] = GAR1_GH//	Ok, I’ve seen enough.
$garage1_text[46] = GAR1_GI//	Let’s go see if we can find Dwaine.
$garage1_text[47] = GAR1_GJ//	He’s working a hotdog van at the tram terminal in King’s

//driving to mech 2
$garage1_text[48] = GAR1_HA//	C’mon, dude, what was all that about?
$garage1_text[49] = GAR1_HB//	You don’t want to know.
$garage1_text[50] = GAR1_HC//	Why?
$garage1_text[51] = GAR1_HD//	Do you know what a sub-dermal neurophone is?
$garage1_text[52] = GAR1_HE//	A what?
$garage1_text[53] = GAR1_HF//	Exactly.
$garage1_text[54] = GAR1_HG//	Sometimes it’s best to stay in the dark, kid.
			  
// talking to mech2
$garage1_text[55] = GAR1_JA//	Yo, Dwaine, how’s the noodle business.
$garage1_text[56] = GAR1_JB//	Utterly shit. What’s happenin’?
$garage1_text[57] = GAR1_JC//	My friend Carl here is opening a chop shop.
$garage1_text[58] = GAR1_JD//	Jethro’s in, how about you?
$garage1_text[59] = GAR1_JE//	Sure thing,
$garage1_text[60] = GAR1_JF//	I’ve, like, got some some shit to deal with first.
$garage1_text[61] = GAR1_JG//	Where is it, I’ll, like, meet you dudes there.
$garage1_text[62] = GAR1_JH//	The old garage on the waste ground in Doherty.
$garage1_text[63] = GAR1_JJ//	See you later.

$garage1_text[64] = GAR1_KA//	Ok, next stop the cop station downtown.
$garage1_text[65] = GAR1_KB//	What? Are you off your mind? Why?
$garage1_text[66] = GAR1_KC//	If I told you, the likelihood is you’d get a probe up your ass within a month.
$garage1_text[67] = GAR1_KD//	Like, listen to the man, dude, he’s real serious about that shit.
$garage1_text[68] = GAR1_KE//	Whuh? Oh, ok. But you’re starting to freak me a little.


// arriving at cop sation
$garage1_text[69] = GAR1_LA//	Ok, you know the drill.
$garage1_text[70] = GAR1_LB//	Don’t look interested in anything.
$garage1_text[71] = GAR1_LC//	Picture a pink golfball in your mind.
$garage1_text[72] = GAR1_LD//	Ok, we’re good to go.
			  
$garage1_text[73] = GAR1_MA//	Where to next, El Kozmo?
$garage1_text[74] = GAR1_MB//	There’s an electronics guy I’ve had dealings with, 
$garage1_text[75] = GAR1_MC//	goes by the name of ZERO.
$garage1_text[76] = GAR1_MD//	He could fix a supercomputer with a paperclip.
$garage1_text[77] = GAR1_ME//	He’s got his own shop, 
$garage1_text[78] = GAR1_MF//	but he’s always ready to help fellow travellers along the path.
$garage1_text[79] = GAR1_MG//	Let’s go introduce you to him

// picking up zero mch3
$garage1_text[80] = GAR1_NA//	Look, what’s goin’ on, Truth?
$garage1_text[81] = GAR1_NB//	Who were those guys?
$garage1_text[82] = GAR1_NC//	Don’t go there, man.
$garage1_text[83] = GAR1_ND//	Listen to Jethro.
$garage1_text[84] = GAR1_NE//	What if I told you we never went to the moon, 
$garage1_text[85] = GAR1_NF//	JFK lives in Scotland with Janis Joplin 
$garage1_text[86] = GAR1_NG//	and the only reason we’ve been in a cold war for the last fortyfive years 
$garage1_text[87] = GAR1_NH//	was because snake-headed aliens run the oil business?
$garage1_text[88] = GAR1_NJ//	I’d say you’d dropped another microdot.
$garage1_text[89] = GAR1_NK//	Good. Keep it that way.
			  
$garage1_text[91] = GAR1_OA//	Blow the horn.

// arriving at zeros
$garage1_text[92] = GAR1_OB//	Leave me alone Berkley!
$garage1_text[93] = GAR1_OC//	This is stalking!
$garage1_text[94] = GAR1_OD//	Oh, hi!
$garage1_text[95] = GAR1_OE//	Get in, I’ll fill you in as we drive.
$garage1_text[96] = GAR1_OF//	Home, James!
			  

// on the way back to garage
$garage1_text[97] = GAR1_PA//	Carl, Zero, Zero, Carl.
$garage1_text[98] = GAR1_PB//	Yo.
$garage1_text[99] = GAR1_PC//	Carl here is opening a garage around the corner here. 
$garage1_text[100] = GAR1_PD//	I told him you’re the man to speak to when it comes to electronics.
$garage1_text[101] = GAR1_PE//	I’m the ONLY man to talk to. 
$garage1_text[102] = GAR1_PJ//Grade A fuckin’ genius, that’s me. 
$garage1_text[103] = GAR1_PF//	You should drop by the shop sometime, see some of my shit.
$garage1_text[104] = GAR1_PG//	I’ll do just that.


$garage1_text[105] = GAR1_PH//	Yo, we’re here.





// intro
garage1_audio[0] = SOUND_GAR1_AA//C’mon. there’s these two guys I know – used to work on marine engines,
garage1_audio[1] = SOUND_GAR1_AB//until the mob bought their business over in Vice.
garage1_audio[2] = SOUND_GAR1_AC//Now they try and make ends meet taking any old job.
garage1_audio[3] = SOUND_GAR1_AD//They’re a little dulled by their habit,
garage1_audio[4] = SOUND_GAR1_AE//but the smoke don’t get in the way of their skills with an engine.
garage1_audio[5] = SOUND_GAR1_AF//We’ll pick up Jethro first.
garage1_audio[6] = SOUND_GAR1_AG//Last I heard he was working at a garage over Easter Basin way.

			
// not needed
garage1_audio[7] = SOUND_GAR1_AH//Last I heard he was working at a garage over East Esplanade way.
garage1_audio[8] = SOUND_GAR1_AJ//Last I heard he was working at a garage over North Esplanade way.

// driving to mech 1
garage1_audio[9] = SOUND_GAR1_BA //	How’d you meet these dudes?
garage1_audio[10] = SOUND_GAR1_BB//	I met them at the ’89 Fierro Love-in, apparently.
garage1_audio[11] = SOUND_GAR1_BC//	Apparently?
garage1_audio[12] = SOUND_GAR1_BD//	You know how it is; 
garage1_audio[13] = SOUND_GAR1_BE//	a field of tents, crazy-assed music, 
garage1_audio[14] = SOUND_GAR1_BF//	a quart of mescaline vodka, polar bears…
garage1_audio[15] = SOUND_GAR1_BG//	Polar bears?
garage1_audio[16] = SOUND_GAR1_BH//	Yeah. Go figure. But they were funny guys – great sense of humour.

// entering vietnam
garage1_audio[17] = SOUND_GAR1_CA//This here’s Vietnamese gang territory. 
garage1_audio[18] = SOUND_GAR1_CB//Da Nang Boys, Shining Razors, Butterfly Children.
garage1_audio[19] = SOUND_GAR1_CC//Watch yourself, these cats are real serious.

//picking up mech 1
garage1_audio[20] = SOUND_GAR1_DA//Hey, Jethro!
garage1_audio[21] = SOUND_GAR1_DF//Hop in. I’ve landed you a real job.
garage1_audio[22] = SOUND_GAR1_DB//Hey there Truth dude!
garage1_audio[23] = SOUND_GAR1_DC//Oh man, do I owe you?
garage1_audio[24] = SOUND_GAR1_DD//Coz I swear I paid for that weed, dude!
garage1_audio[25] = SOUND_GAR1_DE//No, man, we’re good – I think.
  
garage1_audio[26] = SOUND_GAR1_EA//Jethro, Carl. Carl, jethro.
garage1_audio[27] = SOUND_GAR1_EB//Yo, man.
garage1_audio[28] = SOUND_GAR1_EC//Can we swing by the hospital, it’s over in Santa Flora district, West of here.
garage1_audio[29] = SOUND_GAR1_ED//Yeah sure, you ill?
garage1_audio[30] = SOUND_GAR1_EE//	No. The government is, but that’s a long story.

// driving to hospitl
garage1_audio[31] = SOUND_GAR1_FA//	So, like, y’know, what’s the deal, dudes?
garage1_audio[32] = SOUND_GAR1_FB//	I’m opening a garage in Doherty by that derelict ground.
garage1_audio[33] = SOUND_GAR1_FC//	We’re gonna do car mods, lowriders, all that shit.
garage1_audio[34] = SOUND_GAR1_FD//	You down?
garage1_audio[35] = SOUND_GAR1_FE//	Do polar bears shit in the woods?
garage1_audio[36] = SOUND_GAR1_FF//	No, but they’ve been known to shit in the liquor tent, if I remember rightly.
garage1_audio[37] = SOUND_GAR1_FG//	Yeah, that was, like, so far gone, man.

// at the hospital
garage1_audio[38] = SOUND_GAR1_GA//	What we here for?
garage1_audio[39] = SOUND_GAR1_GB//	Nothin’.
garage1_audio[40] = SOUND_GAR1_GC//	Don’t look.
garage1_audio[41] = SOUND_GAR1_GD//	Cover your faces.
garage1_audio[42] = SOUND_GAR1_GE//	Think about a yellow rubber duck.
garage1_audio[43] = SOUND_GAR1_GF//	You tripping again?
garage1_audio[44] = SOUND_GAR1_GG//	Sshhh!
garage1_audio[45] = SOUND_GAR1_GH//	Ok, I’ve seen enough.
garage1_audio[46] = SOUND_GAR1_GI//	Let’s go see if we can find Dwaine.
garage1_audio[47] = SOUND_GAR1_GJ//	He’s working a hotdog van at the tram terminal in King’s




//driving to mech 2
garage1_audio[48] = SOUND_GAR1_HA//	C’mon, dude, what was all that about?
garage1_audio[49] = SOUND_GAR1_HB//	You don’t want to know.
garage1_audio[50] = SOUND_GAR1_HC//	Why?
garage1_audio[51] = SOUND_GAR1_HD//	Do you know what a sub-dermal neurophone is?
garage1_audio[52] = SOUND_GAR1_HE//	A what?
garage1_audio[53] = SOUND_GAR1_HF//	Exactly.
garage1_audio[54] = SOUND_GAR1_HG//	Sometimes it’s best to stay in the dark, kid.





// talking to mech2
garage1_audio[55] = SOUND_GAR1_JA//	Yo, Dwaine, how’s the noodle business.
garage1_audio[56] = SOUND_GAR1_JB//	Utterly shit. What’s happenin’?
garage1_audio[57] = SOUND_GAR1_JC//	My friend Carl here is opening a chop shop.
garage1_audio[58] = SOUND_GAR1_JD//	Jethro’s in, how about you?
garage1_audio[59] = SOUND_GAR1_JE//	Sure thing,
garage1_audio[60] = SOUND_GAR1_JF//	I’ve, like, got some some shit to deal with first.
garage1_audio[61] = SOUND_GAR1_JG//	Where is it, I’ll, like, meet you dudes there.
garage1_audio[62] = SOUND_GAR1_JH//	The old garage on the waste ground in Doherty.
garage1_audio[63] = SOUND_GAR1_JJ//	See you later.	
garage1_audio[64] = SOUND_GAR1_KA//	Ok, next stop the cop station downtown.
garage1_audio[65] = SOUND_GAR1_KB//	What? Are you off your mind? Why?
garage1_audio[66] = SOUND_GAR1_KC//	If I told you, the likelihood is you’d get a probe up your ass within a month.
garage1_audio[67] = SOUND_GAR1_KD//	Like, listen to the man, dude, he’s real serious about that shit.
garage1_audio[68] = SOUND_GAR1_KE//	Whuh? Oh, ok. But you’re starting to freak me a little.



// arriving at cop sation
garage1_audio[69] = SOUND_GAR1_LA//	Ok, you know the drill.
garage1_audio[70] = SOUND_GAR1_LB//	Don’t look interested in anything.
garage1_audio[71] = SOUND_GAR1_LC//	Picture a pink golfball in your mind.
garage1_audio[72] = SOUND_GAR1_LD//	Ok, we’re good to go.
garage1_audio[73] = SOUND_GAR1_MA//	Where to next, El Kozmo?
garage1_audio[74] = SOUND_GAR1_MB//	There’s an electronics guy I’ve had dealings with, 
garage1_audio[75] = SOUND_GAR1_MC//	goes by the name of ZERO.
garage1_audio[76] = SOUND_GAR1_MD//	He could fix a supercomputer with a paperclip.
garage1_audio[77] = SOUND_GAR1_ME//	He’s got his own shop, 
garage1_audio[78] = SOUND_GAR1_MF//	but he’s always ready to help fellow travellers along the path.
garage1_audio[79] = SOUND_GAR1_MG//	Let’s go introduce you to him



  // picking up zero mch3
garage1_audio[80] = SOUND_GAR1_NA//	Look, what’s goin’ on, Truth?
garage1_audio[81] = SOUND_GAR1_NB//	Who were those guys?
garage1_audio[82] = SOUND_GAR1_NC//	Don’t go there, man.
garage1_audio[83] = SOUND_GAR1_ND//	Listen to Jethro.
garage1_audio[84] = SOUND_GAR1_NE//	What if I told you we never went to the moon, 
garage1_audio[85] = SOUND_GAR1_NF//	JFK lives in Scotland with Janis Joplin 
garage1_audio[86] = SOUND_GAR1_NG//	and the only reason we’ve been in a cold war for the last fortyfive years 
garage1_audio[87] = SOUND_GAR1_NH//	was because snake-headed aliens run the oil business?
garage1_audio[88] = SOUND_GAR1_NJ//	I’d say you’d dropped another microdot.
garage1_audio[89] = SOUND_GAR1_NK//	Good. Keep it that way.
garage1_audio[91] = SOUND_GAR1_OA//	Blow the horn.

// arriving at zeros
garage1_audio[92] = SOUND_GAR1_OB//	Leave me alone Berkley!
garage1_audio[93] = SOUND_GAR1_OC//	This is stalking!
garage1_audio[94] = SOUND_GAR1_OD//	Oh, hi!
garage1_audio[95] = SOUND_GAR1_OE//	Get in, I’ll fill you in as we drive.
garage1_audio[96] = SOUND_GAR1_OF//	Home, James!

// on the way back t garag
garage1_audio[97] = SOUND_GAR1_PA//	Carl, Zero, Zero, Carl.
garage1_audio[98] = SOUND_GAR1_PB//	Yo.
garage1_audio[99] = SOUND_GAR1_PC//	Carl here is opening a garage around the corner here. 
garage1_audio[100] = SOUND_GAR1_PD//	I told him you’re the man to speak to when it comes to electronics.
garage1_audio[101] = SOUND_GAR1_PE//	I’m the ONLY man to talk to.  
garage1_audio[102] = SOUND_GAR1_PJ//	Grade A fuckin’ genius, that’s me. 

garage1_audio[103] = SOUND_GAR1_PF//	You should drop by the shop sometime, see some of my shit.
garage1_audio[104] = SOUND_GAR1_PG//	I’ll do just that.
garage1_audio[105] = SOUND_GAR1_PH//	Yo, we’re here.



					 


TIMERA = 0


//LOAD_SCENE -2028.7728 154.0429 27.2416
CLEAR_AREA -2028.7728 154.0429 27.2416 10000.0 TRUE

DISABLE_ALL_ENTRY_EXITS TRUE






// LOAD MODELS
 
 




REQUEST_MODEL emperor
WHILE NOT HAS_MODEL_LOADED emperor
	WAIT 0
ENDWHILE


CREATE_CAR emperor -2030.6282 170.7956 27.8359  car_player_garag1 
SET_CAR_HEALTH car_player_garag1 3000  


//SET_TARGET_CAR_FOR_MISSION_GARAGE hbgdsfs car_player_garag1

//MARK_MODEL_AS_NO_LONGER_NEEDED emperor

  
SET_CHAR_COORDINATES scplayer -2031.0 162.0 27.83
SET_CAR_HEADING car_player_garag1 270.0 	



//CREATE_CHAR_AS_PASSENGER car_player_garag1 PEDTYPE_CIVMALE truth 0 char_truth_garag1

	LOAD_SPECIAL_CHARACTER 2 truth

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 2
		WAIT 0
	ENDWHILE   



//CREATE_CHAR PEDTYPE_CIVMALE truth -2031.2517 164.0169 27.8429 char_truth_garag1 
CREATE_CHAR PEDTYPE_CIVMALE SPECIAL02 -2031.2517 164.0169 27.8429 char_truth_garag1
SET_CHAR_NEVER_TARGETTED char_truth_garag1 TRUE
SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR char_truth_garag1 FALSE

//MARK_MODEL_AS_NO_LONGER_NEEDED truth


SET_CHAR_RELATIONSHIP char_truth_garag1 ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
SET_CHAR_DECISION_MAKER char_truth_garag1 dm_buddies_garage1 

SET_CHAR_HEADING char_truth_garag1 180.0
SET_CHAR_PROOFS char_truth_garag1 FALSE FALSE FALSE TRUE FALSE


 

SET_CHAR_HEALTH char_truth_garag1 200
SET_CHAR_SUFFERS_CRITICAL_HITS char_truth_garag1 FALSE
//WARP_CHAR_INTO_CAR scplayer car_player_garag1

SET_CHAR_HEADING scplayer 0.0
SET_CAMERA_BEHIND_PLAYER







// ******************************************** START MAIN LOOP **********************************
		
	main_garag1_loop:
		WAIT 0 
		IF flag_go_to_mechanic1_garag1 = 1
			GOSUB go_to_mechanic1_garag1
		ENDIF					 

		IF flag_go_to_mechanic2_garag1 = 1
			GOSUB go_to_mechanic2_garag1
		ENDIF		

		IF flag_go_to_mechanic3_garag1 = 1
			GOSUB go_to_mechanic3_garag1
		ENDIF	

	  /*	IF flag_go_to_mechanic4_garag1 = 1
			GOSUB go_to_mechanic4_garage
		ENDIF  */

		IF flag_go_to_hospital_garag1 = 1
			GOSUB go_to_hospital_garag1
		ENDIF


		IF flag_go_to_police_station_garag1 = 1
			GOSUB go_to_police_station_garag1
		ENDIF
		
		IF flag_go_to_hub_garag1 = 1
			GOSUB go_to_hub_garag1
		ENDIF		
	
		IF flag_mission_garag1_passed = 1
			GOTO mission_garag1_passed
		ENDIF

		IF flag_mission_garag1_failed = 1
			GOTO mission_garag1_failed
		ENDIF

	GOTO main_garag1_loop



// ////////////////////////////////// ////////////////////////////////// ////////////////////////////////
// ************************************     Sub Functions 		*****************************************
// ////////////////////////////////// ////////////////////////////////// ////////////////////////////////


// ////////////////////////////////
// go_to_mechanic1_garag1
// ////////////////////////////////


go_to_mechanic1_garag1:

// ASSIGNING A TALKING FOR LIP MOVING

	char_who_is_talking[0] = char_truth_garag1 //C’mon. there’s these two guys I know – used to work on marine engines,
	char_who_is_talking[1] = char_truth_garag1 //until the mob bought their business over in Vice.
	char_who_is_talking[2] = char_truth_garag1 //Now they try and make ends meet taking any old job.
	char_who_is_talking[3] = char_truth_garag1 //They’re a little dulled by their habit,
	char_who_is_talking[4] = char_truth_garag1 //but the smoke don’t get in the way of their skills with an engine.
	char_who_is_talking[5] = char_truth_garag1 //We’ll pick up Jethro first.
	char_who_is_talking[6] = char_truth_garag1 //Last I heard he was working at a garage over Easter Basin way.

	char_who_is_talking[9]  = scplayer						//	How’d you meet these dudes?	  
	char_who_is_talking[10] = char_truth_garag1				//	I met them at the ’89 Fierro Love-in, apparently.
	char_who_is_talking[11] = scplayer						//	Apparently?
	char_who_is_talking[12] = char_truth_garag1				//	You know how it is; 
	char_who_is_talking[13] = char_truth_garag1				//	a field of tents, crazy-assed music, 
	char_who_is_talking[14] = char_truth_garag1				//	a quart of mescaline vodka, polar bears…
	char_who_is_talking[15] = scplayer						//	Polar bears?
	char_who_is_talking[16] = char_truth_garag1				//	Yeah. Go figure. But they were funny guys – great sense of humour.

	char_who_is_talking[17] = char_truth_garag1			//This here’s Vietnamese gang territory. 
	char_who_is_talking[18] = char_truth_garag1			//Da Nang Boys, Shining Razors, Butterfly Children.
	char_who_is_talking[19] = char_truth_garag1			//Watch yourself, these cats are real serious.


	REQUEST_ANIMATION GANGS

	WHILE NOT HAS_ANIMATION_LOADED GANGS
		WAIT 0
	ENDWHILE

	SET_FIXED_CAMERA_POSITION -2021.4834 159.1135 30.5880 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -2022.1533 159.8494 30.4897 JUMP_CUT

    CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE                   
    CAMERA_PERSIST_POS TRUE                       
    CAMERA_SET_VECTOR_MOVE  -2019.9155 157.5772 33.4490 -2019.9509 157.5985 31.8268  4500 TRUE    // two sets of coord for cam start point and end point + time for pan
    CAMERA_SET_VECTOR_TRACK -2020.7695 158.0734 33.2929 -2020.7866 158.0842 31.5706  4500 TRUE    // two sets of coords for cam aim at start and end point + time for pan
							 
    	 
 		 





	 

  //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
  ///	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 



	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0

	CLEAR_AREA -2031.77 161.21 29.42 1000.0 TRUE
	



	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED


	DO_FADE 1000 FADE_IN
	SWITCH_WIDESCREEN ON
	WHILE GET_FADING_STATUS
    	WAIT 0
	ENDWHILE


	


// Neeed a cutscene here. Of truth giving player instructions

	flag_cutscene_garage1 = 0
	TIMERA = 0
	TIMERB = 0

	

	WHILE NOT flag_cutscene_garage1 = 10
		WAIT 0

		IF flag_cutscene_garage1 = 0
			SKIP_CUTSCENE_START
			

			GOSUB load_and_play_audio_garage1 
		ENDIF

		IF flag_cutscene_garage1 = 1
			IF NOT IS_CHAR_DEAD char_truth_garag1
				OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	// truth speaking to the mechanics
					TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkA GANGS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM -1 prtial_gngtlkD GANGS 4.0 FALSE FALSE FALSE FALSE -1

				CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
				PERFORM_SEQUENCE_TASK char_truth_garag1 seq_truth_talking_to_mech1_garag1 
				CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1

			ENDIF

			GOSUB load_and_play_audio_garage1
			TIMERA = 0
		ENDIF

					
		IF flag_cutscene_garage1 >= 2
		AND flag_cutscene_garage1 <= 3
			
			GOSUB load_and_play_audio_garage1
	   	ENDIF


		IF flag_cutscene_garage1 = 4


			CAMERA_RESET_NEW_SCRIPTABLES // do another of these once your camera pan has finished.

			SET_FIXED_CAMERA_POSITION 	-2030.0735 161.3698 28.8915   0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 		-2030.5933 162.2026 29.0817  	JUMP_CUT

		  //	IF IS_PLAYER_PLAYING player1
		  //ENDIF


			GOSUB load_and_play_audio_garage1
	   	ENDIF

		IF flag_cutscene_garage1 >= 5
	   	OR flag_cutscene_garage1 <= 6

	 		GOSUB load_and_play_audio_garage1
		ENDIF

		IF flag_cutscene_garage1 = 7
	   	    GOSUB load_and_play_audio_garage1
			flag_cutscene_garage1 = 10
		ENDIF

	    IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			flag_cutscene_garage1 = 10
			CLEAR_MISSION_AUDIO 1
		ENDIF
	   
		IF TIMERB > 23500
			flag_cutscene_garage1 = 10
		ENDIF

			
	ENDWHILE

	SKIP_CUTSCENE_END
	CLEAR_MISSION_AUDIO 1
	SET_PLAYER_CONTROL player1 ON 
	CAMERA_RESET_NEW_SCRIPTABLES
	RESTORE_CAMERA_JUMPCUT
	SWITCH_WIDESCREEN OFF
	CLEAR_PRINTS

	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

	ENDIF

	SET_RADIO_CHANNEL RS_CLASSIC_ROCK


  //	SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
  //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0



	index_dialogue_garage1 = 9
	play_audio_flag_garage1 = 0
	flag_cutscene_garage1 = 0


	IF NOT IS_CHAR_DEAD char_truth_garag1
		IF NOT IS_CAR_DEAD car_player_garag1 
			TASK_ENTER_CAR_AS_PASSENGER char_truth_garag1 car_player_garag1 -2 0
			SET_CHAR_CANT_BE_DRAGGED_OUT char_truth_garag1 TRUE
			SET_CHAR_STAY_IN_CAR_WHEN_JACKED char_truth_garag1 TRUE
		ENDIF
	ENDIF

	REMOVE_ANIMATION GANGS








   //	ADD_BLIP_FOR_COORD coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] blip_mech_garag1[index_garag1]


	////////////////////////////////////
	// WAITING FOR PLAYER TO ENTER CAR
	///////////////////////////////////
	IF NOT IS_CAR_DEAD car_player_garag1
		ADD_BLIP_FOR_CAR car_player_garag1 blip_players_car_garag1
	   //	CHANGE_BLIP_COLOUR blip_players_car_garag1 BLUE
		SET_BLIP_AS_FRIENDLY blip_players_car_garag1 TRUE
		PRINT_NOW GAR1_22 5000 1	  // get inside the car

		WHILE NOT IS_CHAR_IN_CAR scplayer car_player_garag1
			WAIT 0 

			IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
				flag_go_to_mechanic1_garag1 = 0
				flag_go_to_mechanic2_garag1 = 0
				flag_go_to_mechanic3_garag1 = 0
				flag_go_to_hospital_garag1 = 0
				flag_go_to_police_station_garag1 = 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_passed = 1
				flag_mission_garag1_failed = 0
				RETURN
			ENDIF

			IF IS_CAR_DEAD car_player_garag1
				PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!
				flag_go_to_mechanic1_garag1 = 0
				flag_go_to_mechanic2_garag1 = 0
				flag_go_to_mechanic3_garag1 = 0
				flag_go_to_hospital_garag1 = 0
				flag_go_to_police_station_garag1 = 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_passed = 0
				flag_mission_garag1_failed = 1
				RETURN
			ENDIF

			IF IS_CHAR_DEAD char_truth_garag1
				PRINT_NOW (GAR1_17) 5000 1 //TRUTH DIED
				flag_go_to_mechanic1_garag1 = 0
				flag_go_to_mechanic2_garag1 = 0
				flag_go_to_mechanic3_garag1 = 0
				flag_go_to_hospital_garag1 = 0
				flag_go_to_police_station_garag1 = 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_passed = 0
				flag_mission_garag1_failed = 1
				RETURN
			ENDIF

			IF IS_CAR_ON_FIRE car_player_garag1			  // MAKE CAR EXPLODE IF ON FIRE 
				EXPLODE_CAR car_player_garag1

				PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!

				flag_go_to_mechanic1_garag1 = 0
				flag_go_to_mechanic2_garag1 = 0
				flag_go_to_mechanic3_garag1 = 0
				flag_go_to_mechanic4_garag1 = 0
				flag_go_to_hub_garag1 = 0



				flag_mission_garag1_failed = 1
				RETURN


			ENDIF 

	//		IF IS_CHAR_IN_AREA_3D scplayer -2056.86 150.93 23.16 -2039.3 182.7 32.87 FALSE
	   //		ENDIF






		ENDWHILE

		REMOVE_BLIP blip_players_car_garag1 
		ADD_BLIP_FOR_COORD coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] blip_mech_garag1[index_garag1]
	  //	CHANGE_BLIP_COLOUR blip_mech_garag1[index_garag1] BLUE 
		SET_COORD_BLIP_APPEARANCE blip_mech_garag1[index_garag1] COORD_BLIP_APPEARANCE_FRIEND 
	//	SET_BLIP_AS_FRIENDLY blip_mech_garag1[index_garag1] TRUE
	   //	PRINT_NOW (GAR1_01) 5000 1 //lets go pick up mech 1

	ELSE

		PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!
		flag_go_to_mechanic1_garag1 = 0
		flag_mission_garag1_failed = 1
		RETURN

	ENDIF

	REQUEST_MODEL towtruck
	REQUEST_ANIMATION CAR

	WHILE NOT HAS_MODEL_LOADED towtruck
		OR NOT HAS_ANIMATION_LOADED CAR
		WAIT 0
	ENDWHILE




  	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL03 coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] char_mech_garag1[index_garag1]
  	SET_CHAR_NEVER_TARGETTED char_mech_garag1[index_garag1] TRUE

  	SET_CHAR_HEADING char_mech_garag1[index_garag1] 120.0
	SET_CHAR_PROOFS char_mech_garag1[index_garag1] FALSE FALSE FALSE TRUE FALSE
   //	SET_CHAR_COLLISION char_mech_garag1[index_garag1] FALSE


  	CREATE_CAR towtruck -1676.0 440.0 6.7 car_mech1_garag1 
  	SET_CAR_HEADING car_mech1_garag1 229.0
  	SET_CHAR_RELATIONSHIP char_mech_garag1[index_garag1] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
	SET_CHAR_DECISION_MAKER char_mech_garag1[index_garag1] dm_buddies_garage1 
 

	DELETE_CAR car_mech1_garag1 
	DELETE_CHAR char_mech_garag1[index_garag1]



	GOSUB going_to_destinations_garage1

	IF flag_mission_garag1_failed = 1 
		RETURN
	ENDIF

	
	TIMERA = 0

	REQUEST_ANIMATION CAR_CHAT

	WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
		WAIT 0
	ENDWHILE

	char_who_is_talking[20] = char_truth_garag1			//Hey, Jethro!
	char_who_is_talking[21] = char_truth_garag1			//Hop in. I’ve landed you a real job.
	char_who_is_talking[22] = char_mech_garag1[0]			//Hey there Truth dude!
	char_who_is_talking[23] = char_mech_garag1[0]			//Oh man, do I owe you?
	char_who_is_talking[24] = char_truth_garag1			//Coz I swear I paid for that weed, dude!
	char_who_is_talking[25] = char_truth_garag1			//No, man, we’re good – I think.
				
	char_who_is_talking[26] = char_truth_garag1			//Jethro, Carl. Carl, jethro.
	char_who_is_talking[27] = scplayer					//Yo, man.
	char_who_is_talking[28] = char_truth_garag1		//Can we swing by the hospital, it’s over in Santa Flora district, West of here.
	char_who_is_talking[29] = char_truth_garag1		//Yeah sure, you ill?
	char_who_is_talking[30] = char_truth_garag1			//	No. The government is, but that’s a long story.

	char_who_is_talking[31] = char_mech_garag1[0]		//	So, like, y’know, what’s the deal, dudes?
	char_who_is_talking[32] = scplayer				//	I’m opening a garage in Doherty by that derelict ground.
	char_who_is_talking[33] = scplayer				//	We’re gonna do car mods, lowriders, all that shit.
	char_who_is_talking[34] = scplayer				//	You down?
	char_who_is_talking[35] = char_mech_garag1[0]		//	Do polar bears shit in the woods?
	char_who_is_talking[36] = char_truth_garag1		//	No, but they’ve been known to shit in the liquor tent, if I remember rightly.
	char_who_is_talking[37] = char_mech_garag1[0]		//	Yeah, that was, like, so far gone, man.





	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON

   //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
  //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0

	CLEAR_AREA -2031.77 161.21 29.42 1000.0 TRUE
	
	CLEAR_MISSION_AUDIO 1



	//CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE

	///////////////////////////////////////////
	// Setting players car position for cutscene
	//////////////////////////////////////////////

  //	CAR_GOTO_COORDINATES car_player_garag1 coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1]
  //	SET_CAR_CRUISE_SPEED car_player_garag1 30.0 




	// SET_CAR_HEADING car_player_garag1 55.0


	flag_cutscene_garage1 = 0
	TIMERA = 0
	TIMERB = 0

	CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE
	



	IF NOT IS_CAR_DEAD car_player_garag1
		IF NOT IS_CAR_PASSENGER_SEAT_FREE car_player_garag1 2

			GET_CHAR_IN_CAR_PASSENGER_SEAT car_player_garag1 2 temp_passenger_to_be_deleted_garage1
			DELETE_CHAR temp_passenger_to_be_deleted_garage1
		ENDIF 
	ENDIF
	

	WHILE NOT flag_cutscene_garage1 = 20
		WAIT 0

   
		IF flag_cutscene_garage1 = 0
			// ///////////////////////////////////////
			// STOP CAR
			// ///////////////////////////////////////
			SKIP_CUTSCENE_START
			IF NOT IS_CAR_DEAD car_player_garag1
				IF NOT IS_CHAR_DEAD char_truth_garag1

					  	SET_FIXED_CAMERA_POSITION -1674.2632 438.2627 11.0956 0.0 0.0 0.0	  // close up of mech fixing car
						POINT_CAMERA_AT_POINT  -1674.2458 438.3686 10.1014 JUMP_CUT	   


						IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
							WARP_CHAR_INTO_CAR scplayer car_player_garag1
						ENDIF 
	 
						SET_CAR_COORDINATES car_player_garag1 coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1]  
						SET_CAR_HEADING car_player_garag1 heading_players_car
						 
						SET_CAR_CRUISE_SPEED car_player_garag1 0.0
						index_dialogue_garage1 = 20

 						GOSUB cutscene_audio


				ELSE
					flag_go_to_mechanic1_garag1 = 0
					flag_mission_garag1_failed = 1
					RETURN

				ENDIF

			ELSE
				PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!
				flag_go_to_mechanic1_garag1 = 0
				flag_mission_garag1_failed = 1
				RETURN
			ENDIF
 
		ENDIF
   


		IF flag_cutscene_garage1 = 1
			IF TIMERA > 2000



				IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1] 
					IF NOT IS_CAR_DEAD car_player_garag1

						SET_FIXED_CAMERA_POSITION -1672.6583 431.5702 7.2947 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT -1672.7198 432.5628 7.3989 JUMP_CUT
						OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	// truth speaking to the mechanics
							TASK_PLAY_ANIM -1 Fixn_Car_Out CAR 4.0 FALSE FALSE FALSE FALSE 0
						   	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer

															// TASK GOTO  -1669.653 440.619 6.219
							TASK_GO_STRAIGHT_TO_COORD -1 -1668.853 442.619 6.219 PEDMOVE_RUN -1
							TASK_ACHIEVE_HEADING -1 193.0
								  //	TASK_PLAY_ANIM -1 prtial_gngtlkD PED 4.0 FALSE FALSE FALSE FALSE 0
							TASK_ENTER_CAR_AS_PASSENGER -1 car_player_garag1 -1 2

						CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
						PERFORM_SEQUENCE_TASK char_mech_garag1[index_garag1] seq_truth_talking_to_mech1_garag1 
						CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
					 	flag_cutscene_garage1 = 2

						LOAD_MISSION_AUDIO 3 SOUND_BANK_MECHANIC
						WHILE NOT HAS_MISSION_AUDIO_LOADED 3
							WAIT 0
						ENDWHILE   


					  	REPORT_MISSION_AUDIO_EVENT_AT_CHAR char_mech_garag1[index_garag1] SOUND_MECHANIC_SLIDE_OUT

					   //	 ADD_ONE_OFF_SOUND -1672.6583 431.5702 7.2947 SOUND_MECHANIC_SLIDE_OUT 
						 
		 		//		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1672.6583 431.5702 7.2947 SOUND_MECHANIC_SLIDE_OUT 

 			  	 /*	  	LOAD_MISSION_AUDIO 3 SOUND_MECHANIC_SLIDE_OUT
						WHILE NOT HAS_MISSION_AUDIO_LOADED 3
							WAIT 0
						ENDWHILE   


					   	PLAY_MISSION_AUDIO 3
 				   */

			//	ADD_CONTINUOUS_SOUND -1672.6583 431.5702 7.2947 SOUND_BANK_MECHANIC noise_of_board_rolling
						TIMERA = 0
					ENDIF								  
				ENDIF
			ENDIF
		ENDIF

		// Make guys enter car
		IF flag_cutscene_garage1 = 2
	//		REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1672.6583 431.5702 7.2947 SOUND_BANK_MECHANIC
			 
			IF TIMERA > 2000
			   //	REPORT_MISSION_AUDIO_EVENT_AT_POSITION -1672.6583 431.5702 7.2947 SOUND_BANK_MECHANIC 

				SET_FIXED_CAMERA_POSITION -1665.5226 443.0056 7.5433 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -1666.4623 442.6743 7.4596 JUMP_CUT
					
					IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
					//	SET_CHAR_COORDINATES char_mech_garag1[index_garag1] -1673.16 440.31 6.68 
						IF NOT IS_CAR_DEAD car_player_garag1

						  /*		OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	// truth speaking to the mechanics
									// TASK GOTO  -1669.653 440.619 6.219
									TASK_GO_STRAIGHT_TO_COORD -1 -1668.853 442.619 6.219 PEDMOVE_RUN -1
									TASK_ACHIEVE_HEADING -1 193.0
								  //	TASK_PLAY_ANIM -1 prtial_gngtlkD PED 4.0 FALSE FALSE FALSE FALSE 0
									TASK_ENTER_CAR_AS_PASSENGER -1 car_player_garag1 -1 2
								CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
							   	PERFORM_SEQUENCE_TASK char_mech_garag1[index_garag1] seq_truth_talking_to_mech1_garag1 
								CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
								 */
						ENDIF
					ENDIF
			   //			flag_cutscene_garage1 = 3

				  	IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
						SET_CHAR_HEADING char_mech_garag1[index_garag1]	228.0
							
						SET_CHAR_COLLISION char_mech_garag1[index_garag1] TRUE
					ENDIF
					 
				 

				GOSUB cutscene_audio
 

				TIMERA = 0
			ENDIF
		ENDIF


		// Make guys enter car
		IF flag_cutscene_garage1 = 3
			GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?
		ENDIF

		IF flag_cutscene_garage1 = 4
			GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?
		ENDIF					 

		IF flag_cutscene_garage1 = 5
			IF TIMERA > 1500
	
				IF NOT IS_CHAR_DEAD char_truth_garag1
					TASK_PLAY_ANIM char_truth_garag1 CAR_Sc4_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
				ENDIF

				

				GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?	

			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 6
				
				IF NOT IS_CAR_DEAD car_player_garag1
					SET_FIXED_CAMERA_POSITION -1670.6049 442.2138 7.3642 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT  -1669.8915 441.5237 7.2424  JUMP_CUT
				ENDIF
				TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1

			

				GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?

		ENDIF

		IF flag_cutscene_garage1 = 7
				GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?				TIMERA = 0
		ENDIF

		IF flag_cutscene_garage1 = 8
			GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 9
			IF TIMERA > 1500
				SET_FIXED_CAMERA_POSITION -1648.6838 411.5628 18.7936 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT  -1649.0903 412.4462 18.5605  JUMP_CUT

				

				GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?
			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 10
			IF TIMERA > 1500
				GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?

			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 11
			IF TIMERA > 1500
				GOSUB cutscene_audio //	PRINT_NOW GAR1_DC 1500 1		 // JETHRO Do i owe you?
			ENDIF
		ENDIF

	 	IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			flag_cutscene_garage1 = 20
			CLEAR_MISSION_AUDIO 1
		ENDIF
	 
		IF TIMERB > 21500
			flag_cutscene_garage1 = 20	
		ENDIF

	ENDWHILE 
	SKIP_CUTSCENE_END
	GOSUB checking_buddies_garage1
	IF flag_mission_garag1_failed = 1 
		RETURN
	ENDIF


	REMOVE_ANIMATION CAR
	MARK_MODEL_AS_NO_LONGER_NEEDED towtruck
	IF NOT IS_CAR_DEAD car_mech1_garag1 
		FREEZE_CAR_POSITION car_mech1_garag1 FALSE
	ENDIF
	MARK_CAR_AS_NO_LONGER_NEEDED car_mech1_garag1 

		
	//SET_CAMERA_IN_FRONT_OF_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

	REMOVE_ANIMATION CAR_CHAT

	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

	ENDIF





	//SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
  //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0


	CLEAR_PRINTS
	flag_cutscene_garage1 = 0
	index_dialogue_garage1 = 31
	play_audio_flag_garage1 = 0



	// ///////// WARP TRUTH AND MECHANIC INTO CAR  ///////////////////////
	IF NOT IS_CAR_DEAD car_player_garag1
		SET_CAR_STATUS car_player_garag1 STATUS_PLAYER

		IF NOT IS_CHAR_DEAD char_truth_garag1		 
			IF NOT IS_CHAR_IN_CAR char_truth_garag1 car_player_garag1
				IF IS_CAR_PASSENGER_SEAT_FREE car_player_garag1 0
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_truth_garag1 car_player_garag1 0

				ELSE

					GET_CHAR_IN_CAR_PASSENGER_SEAT car_player_garag1 0 temp_passenger_to_be_deleted_garage1
					DELETE_CHAR temp_passenger_to_be_deleted_garage1
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_truth_garag1 car_player_garag1 0
				ENDIF
			ENDIF
		
		ENDIF

	   	IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
			IF NOT IS_CHAR_IN_CAR char_mech_garag1[index_garag1] car_player_garag1


				IF IS_CAR_PASSENGER_SEAT_FREE car_player_garag1 2
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_mech_garag1[index_garag1] car_player_garag1 2

				ELSE

					GET_CHAR_IN_CAR_PASSENGER_SEAT car_player_garag1 2 temp_passenger_to_be_deleted_garage1
					DELETE_CHAR temp_passenger_to_be_deleted_garage1
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_mech_garag1[index_garag1] car_player_garag1 2
		   		ENDIF 	   
		   	ENDIF	
	   	ENDIF

	ENDIF
	///////////////////////////////////////////////////////////////////////

	index_garag1 = 1
	flag_go_to_mechanic1_garag1 = 0
	flag_go_to_hospital_garag1 = 1

RETURN


// Go to the hospital

go_to_hospital_garag1:

  // 	ADD_BLIP_FOR_COORD coords_hospital_x coords_hospital_y coords_hospital_z blip_hospital
  	


	REQUEST_MODEL pony 
				 

	WHILE NOT HAS_MODEL_LOADED pony
		WAIT 0
	ENDWHILE



	CREATE_CAR pony -2607.11 623.91 14.66 car_zombietech_garage1

	MARK_MODEL_AS_NO_LONGER_NEEDED pony

	SET_CAR_HEADING car_zombietech_garage1 277.0
	DELETE_CAR car_zombietech_garage1 

	ADD_SPRITE_BLIP_FOR_COORD coords_hospital_x coords_hospital_y coords_hospital_z RADAR_SPRITE_HOSPITAL blip_hospital

  	GOSUB going_to_destinations_garage1

	IF flag_mission_garag1_failed = 1 
		RETURN
	ENDIF

	char_who_is_talking[38] = scplayer				//	What we here for?
	char_who_is_talking[39] = char_truth_garag1				//	Nothin’.
	char_who_is_talking[40] = char_truth_garag1				//	Don’t look.
	char_who_is_talking[41] = char_truth_garag1				//	Cover your faces.
	char_who_is_talking[42] = char_truth_garag1	//	Think about a yellow rubber duck.
	char_who_is_talking[43] = scplayer		//	You tripping again?
	char_who_is_talking[44] = char_truth_garag1	//	Sshhh!
	char_who_is_talking[45] = char_truth_garag1 	//	Ok, I’ve seen enough.
	char_who_is_talking[46] = char_truth_garag1		//	Let’s go see if we can find Dwaine.
	char_who_is_talking[47] = char_truth_garag1	//	He’s working a hotdog van at the tram terminal in King’s


	char_who_is_talking[48] = scplayer		  		 //	C’mon, dude, what was all that about?
	char_who_is_talking[49] = char_truth_garag1	   	//	You don’t want to know.
	char_who_is_talking[50] = scplayer 				//	Why?
	char_who_is_talking[51] = char_truth_garag1		//	Do you know what a sub-dermal neurophone is?
	char_who_is_talking[52] = scplayer				//	A what?
	char_who_is_talking[53] = char_truth_garag1		//	Exactly.
	char_who_is_talking[54] = char_truth_garag1		//	Sometimes it’s best to stay in the dark, kid.




	REMOVE_BLIP blip_hospital

	REQUEST_MODEL pony 
				 

	WHILE NOT HAS_MODEL_LOADED pony
		WAIT 0
	ENDWHILE


	REQUEST_ANIMATION CAR_CHAT

	WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
		WAIT 0
	ENDWHILE



	CREATE_CAR pony -2607.11 623.91 14.66 car_zombietech_garage1

	MARK_MODEL_AS_NO_LONGER_NEEDED pony
	LOCK_CAR_DOORS car_zombietech_garage1 CARLOCK_LOCKED

	SET_CAR_HEADING car_zombietech_garage1 180.0
	FREEZE_CAR_POSITION car_zombietech_garage1 TRUE





	index_dialogue_garage1 = 38 // what are we doing here?
	
// cutscene of strange goings on
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	CLEAR_MISSION_AUDIO 1

	//SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
  //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0


	
	
   //	PRINT_NOW (GAR1_GA) 1500 1
   //	SET_NEAR_CLIP 0.1

	
	flag_cutscene_garage1 = 0
	TIMERA = 0
	TIMERB = 0

	IF NOT IS_CAR_DEAD car_player_garag1
		SET_CAR_COORDINATES car_player_garag1 coords_hospital_x coords_hospital_y coords_hospital_z
		GET_CAR_HEADING car_player_garag1 heading_car_player_garage1
	
	
		IF heading_car_player_garage1 > 0.0
		AND heading_car_player_garage1 < 180.0 
		   //	SET_CAR_COORDINATES car_player_garag1 -2690.0598 894.6722 78.5344
			SET_CAR_HEADING car_player_garag1 90.0


		/*   	CAMERA_RESET_NEW_SCRIPTABLES
		    CAMERA_PERSIST_TRACK TRUE                   
		    CAMERA_PERSIST_POS TRUE                       
		    CAMERA_SET_VECTOR_MOVE  -2588.1543 589.8139 23.1815  -2583.6855 582.2637 14.6789   4500 TRUE    // two sets of coord for cam start point and end point + time for pan
		    CAMERA_SET_VECTOR_TRACK -2588.6619 590.5887 23.5580  -2583.8630 583.2451 14.7525   4500 TRUE    // two sets of coords for cam aim at start and end point + time for pan
		   */	  
		ELSE
		 //  	SET_CAR_COORDINATES car_player_garag1 -2674.3975 890.5623 78.6200
			SET_CAR_HEADING car_player_garag1 270.0


		 /*  	CAMERA_RESET_NEW_SCRIPTABLES
		    CAMERA_PERSIST_TRACK TRUE                   
		    CAMERA_PERSIST_POS TRUE                       
		    CAMERA_SET_VECTOR_MOVE  -2578.2324 584.9357 18.1784  -2581.8386 585.5460 14.9879   4500 TRUE    // two sets of coord for cam start point and end point + time for pan
		    CAMERA_SET_VECTOR_TRACK -2578.9768 585.4704 18.5781  -2582.8013 585.4057 14.7572   4500 TRUE    // two sets of coords for cam aim at start and end point + time for pan
		   */	  

		 //	SET_FIXED_CAMERA_POSITION -2582.32 582.00 15.33 0.0 0.0 0.0
		//	POINT_CAMERA_AT_POINT -2628.64 631.49 -2.14  JUMP_CUT
		ENDIF

		IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
			WARP_CHAR_INTO_CAR scplayer car_player_garag1
		ENDIF 

		  	SET_FIXED_CAMERA_POSITION 	 -2581.9397 580.4200 17.8189   0.0 0.0 0.0
		 	POINT_CAMERA_AT_POINT 		 -2582.4910 581.1530 18.2173     JUMP_CUT


			


	  	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 0.8 2.2 0.8 offset_players_car_x offset_players_car_y offset_players_car_z
	  	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 0.0 -1.5 0.5 offset_players_car2_x offset_players_car2_y offset_players_car2_z
	  //	SET_FIXED_CAMERA_POSITION offset_players_car_x offset_players_car_y offset_players_car_z 0.0 0.0 0.0
	   //	POINT_CAMERA_AT_POINT offset_players_car2_x offset_players_car2_y offset_players_car2_z  JUMP_CUT
	
		CLEAR_AREA -2607.11 623.91 14.66 1000.0 TRUE
	

	ENDIF

	IF NOT IS_CHAR_DEAD char_truth_garag1
		TASK_PLAY_ANIM char_truth_garag1 CAR_Sc3_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
	ENDIF



	GOSUB cutscene_audio



	CLEAR_AREA coords_hospital_x coords_hospital_y coords_hospital_z 1000.0 TRUE
	

	flag_cutscene_garage1 = 0
	WHILE NOT flag_cutscene_garage1 = 10
		WAIT 0

		 
		GOSUB checking_buddies_garage1

		IF flag_mission_garag1_failed = 1 
			RETURN
		ENDIF



		IF flag_cutscene_garage1 = 0
			IF TIMERA > 1500
				SKIP_CUTSCENE_START
				IF NOT IS_CHAR_DEAD char_mech_garag1[0]
					TASK_PLAY_ANIM char_mech_garag1[0] CAR_Sc3_BR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
				ENDIF

				   //	SET_CAR_COORDINATES car_player_garag1 -2690.0598 894.6722 78.5344
				   //	SET_CAR_HEADING car_player_garag1 90.0

				 // 	SET_FIXED_CAMERA_POSITION 	-2581.9397 580.4200 17.8189  0.0 0.0 0.0
				 //	POINT_CAMERA_AT_POINT 			-2582.4910 581.1530 18.2173    JUMP_CUT

				   	CAMERA_RESET_NEW_SCRIPTABLES
				    CAMERA_PERSIST_TRACK TRUE                   
				    CAMERA_PERSIST_POS TRUE                       
				    CAMERA_SET_VECTOR_MOVE  -2581.9397 580.4200 17.8189    -2583.32 581.73 15.39   5000 TRUE    // two sets of coord for cam start point and end point + time for pan
				    CAMERA_SET_VECTOR_TRACK -2582.4910 581.1530 18.2173   -2607.11 623.91 14.66   5000 TRUE    // two sets of coords for cam aim at start and end point + time for pan




				TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1

				GOSUB cutscene_audio
				TIMERA = 0
			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 1
			IF TIMERA > 1500
				GOSUB cutscene_audio
				IF NOT IS_CAR_DEAD car_zombietech_garage1 
					CREATE_CHAR_INSIDE_CAR car_zombietech_garage1 PEDTYPE_CIVMALE male01 pony_driver_garage1
				ENDIF
			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 2
			IF TIMERA > 1500
				GOSUB cutscene_audio	
				CAMERA_RESET_NEW_SCRIPTABLES

				SET_FIXED_CAMERA_POSITION -2583.32 581.73 15.39 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2607.11 623.91 14.66 JUMP_CUT

			   
			ENDIF

			
		ENDIF


		IF flag_cutscene_garage1 = 3
			IF TIMERA > 1500
				GOSUB cutscene_audio
			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 4
			IF TIMERA > 1500

				IF NOT IS_CAR_DEAD car_zombietech_garage1 
					FREEZE_CAR_POSITION car_zombietech_garage1 FALSE

				   //	POINT_CAMERA_AT_CAR car_zombietech_garage1 FIXED INTERPOLATION


					IF NOT IS_CHAR_DEAD pony_driver_garage1
						   //	CAR_GOTO_COORDINATES car_zombietech_garage1 -1564.43 728.96 6.0
						IF IS_CHAR_IN_CAR pony_driver_garage1 car_zombietech_garage1
							TASK_CAR_DRIVE_TO_COORD pony_driver_garage1 car_zombietech_garage1 -2607.95 528.47 13.98 25.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_AVOIDCARS
						ENDIF
						   //	SET_CAR_CRUISE_SPEED car_zombietech_garage1 30.0 
					ENDIF
						

				ENDIF	
				GOSUB cutscene_audio
			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 5
			IF TIMERA > 1500
				GOSUB cutscene_audio	 ///sheeeeesh

				SET_FIXED_CAMERA_POSITION 	-2614.4614 573.4770 16.9766  0.0 0.0 0.0
				POINT_CAMERA_AT_POINT  		-2613.7998 574.2240 16.9110  JUMP_CUT
				TIMERA = 0
			


			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 6
			IF TIMERA > 2500
				GOSUB cutscene_audio

				IF NOT IS_CHAR_DEAD char_truth_garag1
					TASK_PLAY_ANIM char_truth_garag1 CAR_Sc3_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
				ENDIF

				IF NOT IS_CHAR_DEAD char_mech_garag1[0]
					TASK_PLAY_ANIM char_mech_garag1[0] CAR_Sc3_BR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
				ENDIF



				TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1



		   /*		IF heading_car_player_garage1 > 0.0
				AND heading_car_player_garage1 < 180.0 
				   //	POINT_CAMERA_AT_POINT -2556.56 725.64 -8.84  INTERPOLATION
					POINT_CAMERA_AT_POINT -2533.64 683.49 -11.14  INTERPOLATION

					SET_INTERPOLATION_PARAMETERS 5.0 2000
				ELSE
				   //	POINT_CAMERA_AT_POINT -2780.23 739.21 -28.12  INTERPOLATION
					POINT_CAMERA_AT_POINT -2628.64 631.49 -2.14  INTERPOLATION

					SET_INTERPOLATION_PARAMETERS 5.0 2000
				ENDIF  */

				SET_FIXED_CAMERA_POSITION offset_players_car_x offset_players_car_y offset_players_car_z 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT offset_players_car2_x offset_players_car2_y offset_players_car2_z  JUMP_CUT



			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 7
			IF TIMERA > 1500
  				GOSUB cutscene_audio
  			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 8
			IF TIMERA > 1500
				GOSUB cutscene_audio
				flag_cutscene_garage1 = 10
			ENDIF
		ENDIF

		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			flag_cutscene_garage1 = 10
			CLEAR_MISSION_AUDIO 1
		ENDIF
   
		IF TIMERB > 20500
			flag_cutscene_garage1 = 10
		ENDIF

			
	ENDWHILE

	SKIP_CUTSCENE_END

	//SET_CAMERA_IN_FRONT_OF_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF
	CLEAR_PRINTS
	CAMERA_RESET_NEW_SCRIPTABLES
	REMOVE_ANIMATION CAR_CHAT
	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

	ENDIF




  //	SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
  //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0


	flag_cutscene_garage1 = 0
	index_dialogue_garage1 = 48
	play_audio_flag_garage1 = 0


	DELETE_CHAR pony_driver_garage1

	DELETE_CAR car_zombietech_garage1 

	flag_go_to_hospital_garag1 = 0
	flag_go_to_mechanic2_garag1 = 1
RETURN







// ////////////////////////////////
// go_to_mechanic2_garag2
// ////////////////////////////////


go_to_mechanic2_garag1:

	REQUEST_MODEL HOTDOG
 

	WHILE NOT HAS_MODEL_LOADED HOTDOG
		WAIT 0
	ENDWHILE

  	REQUEST_ANIMATION SMOKING

  	WHILE NOT HAS_ANIMATION_LOADED SMOKING
		WAIT 0
	ENDWHILE
	
	LOAD_SPECIAL_CHARACTER 4 Dwayne

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 4
		WAIT 0
	ENDWHILE  	 

	REQUEST_MODEL cigar

	WHILE NOT HAS_MODEL_LOADED cigar
		WAIT 0
	ENDWHILE
	  



	 


	ADD_BLIP_FOR_COORD coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] blip_mech_garag1[index_garag1]
  // 	CHANGE_BLIP_COLOUR blip_mech_garag1[index_garag1] BLUE 
	SET_COORD_BLIP_APPEARANCE blip_mech_garag1[index_garag1] COORD_BLIP_APPEARANCE_FRIEND
   //	SET_BLIP_AS_FRIENDLY blip_mech_garag1[index_garag1] TRUE
	//PRINT_NOW (GAR1_02) 5000 1 //lets go pick up mech 1

	IF flag_mission_garag1_failed = 14			// to fool the compiler
	 	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL04 coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] char_mech_garag1[index_garag1]
		CREATE_CAR HOTDOG -2238.8884 541.5267 34.3750 car_mech2_garag1 
	 	CREATE_OBJECT cigar coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] dwaynes_blunt

		
		DELETE_CAR car_mech2_garag1
		DELETE_CHAR char_mech_garag1[index_garag1]
		DELETE_OBJECT dwaynes_blunt
	ENDIF



	GOSUB going_to_destinations_garage1

	IF flag_mission_garag1_failed = 1 
		RETURN
	ENDIF


	IF DOES_OBJECT_EXIST dwaynes_blunt
		DELETE_OBJECT dwaynes_blunt
						  //	TASK_PICK_UP_OBJECT char_mech_garag1[index_garag1] dwaynes_blunt 0.04 0.1 -0.02 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
	ENDIF


	REQUEST_ANIMATION CAR_CHAT

	WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
		WAIT 0
	ENDWHILE

	char_who_is_talking[55] = char_truth_garag1//	Yo, Dwaine, how’s the noodle business.
	char_who_is_talking[56] = char_mech_garag1[1]//	Utterly shit. What’s happenin’?
	char_who_is_talking[57] = char_truth_garag1//	My friend Carl here is opening a chop shop.
	char_who_is_talking[58] = char_truth_garag1//	Jethro’s in, how about you?
	char_who_is_talking[59] = char_mech_garag1[1]//	Sure thing,
	char_who_is_talking[60] = char_mech_garag1[1]//	I’ve, like, got some some shit to deal with first.
	char_who_is_talking[61] = char_mech_garag1[1]//	Where is it, I’ll, like, meet you dudes there.
	char_who_is_talking[62] = scplayer		//	The old garage on the waste ground in Doherty.
	char_who_is_talking[63] = char_mech_garag1[1]//	See you later.	
	char_who_is_talking[64] = char_truth_garag1//	Ok, next stop the cop station downtown.
	char_who_is_talking[65] = scplayer		//	What? Are you off your mind? Why?
	char_who_is_talking[66] = char_truth_garag1//	If I told you, the likelihood is you’d get a probe up your ass within a month.
	char_who_is_talking[67] = char_mech_garag1[0]//	Like, listen to the man, dude, he’s real serious about that shit.
	char_who_is_talking[68] = scplayer//	Whuh? Oh, ok. But you’re starting to freak me a little.



																		 
																		 
																		 
	IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
   		IF NOT DOES_OBJECT_EXIST dwaynes_blunt
		   //	 DROP_OBJECT char_mech_garag1[index_garag1] FALSE
			CREATE_OBJECT cigar coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] dwaynes_blunt
			TASK_PICK_UP_OBJECT char_mech_garag1[index_garag1] dwaynes_blunt 0.04 0.1 0.05 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
	
			CREATE_FX_SYSTEM_ON_CHAR exhale char_mech_garag1[index_garag1] 0.05 0.12 0.0 TRUE exhale_smoke_effect 
			ATTACH_FX_SYSTEM_TO_CHAR_BONE exhale_smoke_effect char_mech_garag1[index_garag1] BONE_HEAD
		//	PLAY_FX_SYSTEM exhale_smoke_effect


		ENDIF
		
	ENDIF






	// ///////////////////////////////////////
	// Make truth run out and fetch mechanic
	// ///////////////////////////////////////

	 ///////////////////////////////////////////
	// Setting players car position for cutscene
	//////////////////////////////////////////////
	IF NOT IS_CAR_DEAD car_player_garag1
		GET_CAR_HEADING car_player_garag1 heading_car_player_garage1

		SET_CAR_COORDINATES car_player_garag1 coords_garage_garag1_x[1] coords_garage_garag1_y[1] coords_garage_garag1_z[1]

		IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
			WARP_CHAR_INTO_CAR scplayer car_player_garag1
		ENDIF 

   		SET_FIXED_CAMERA_POSITION -2235.8147 541.4094 35.7451 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT -2236.5208 542.1174 35.7312 JUMP_CUT 	
		CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE 

		IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
			TASK_PLAY_ANIM_NON_INTERRUPTABLE char_mech_garag1[index_garag1] M_smk_out SMOKING 4.0 FALSE FALSE FALSE FALSE 0
		ENDIF

		IF heading_car_player_garage1 > 90.0
		AND heading_car_player_garage1 < 270.0 
		   //	SET_CAR_COORDINATES car_player_garag1 -2690.0598 894.6722 78.5344
			SET_CAR_HEADING car_player_garag1 180.0

			IF NOT IS_CHAR_DEAD char_truth_garag1
				TASK_PLAY_ANIM char_truth_garag1 CAR_Sc4_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE -1
			ENDIF

			TASK_PLAY_ANIM scplayer CAR_Sc4_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1

		ELSE
		 //  	SET_CAR_COORDINATES car_player_garag1 -2674.3975 890.5623 78.6200
			SET_CAR_HEADING car_player_garag1 0.0

			IF NOT IS_CHAR_DEAD char_truth_garag1
				TASK_PLAY_ANIM char_truth_garag1 CAR_Sc3_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
			ENDIF
			TASK_PLAY_ANIM scplayer CAR_Sc2_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
		ENDIF
	ENDIF

    index_dialogue_garage1 = 55

	GOSUB cutscene_audio



	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
   CLEAR_MISSION_AUDIO 1
	
   //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
   //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0




	




 /*	OPEN_SEQUENCE_TASK seq_truth_talking_to_mech2_garag1	// truth speaking to the mechanics
		TASK_LEAVE_CAR -1 car_player_garag1
	  //	TASK_GOTO_CHAR -1 char_mech_garag1[1] 6000 0.5
		TASK_GOTO_CHAR_OFFSET -1 char_mech_garag1[1] 6000 1.0 0.0

		TASK_CHAT_WITH_CHAR -1 char_mech_garag1[1] TRUE FALSE
		TASK_ENTER_CAR_AS_PASSENGER -1 car_player_garag1 5000 0

	   //	SET_SEQUENCE_TO_REPEAT seq_preachers_reading_sweet7 1
	CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech2_garag1	  */

	flag_cutscene_garage1 = 0
	TIMERA = 0
	TIMERB = 0

	WHILE NOT flag_cutscene_garage1 = 20
		WAIT 0

	   //	CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE 

		GOSUB checking_buddies_garage1

		IF flag_mission_garag1_failed = 1 
			RETURN
		ENDIF



		IF flag_cutscene_garage1 = 0
			SKIP_CUTSCENE_START
			IF TIMERA > 2000			
	   		   //	PRINT_NOW GAR1_JB 2000 1
				PLAY_FX_SYSTEM exhale_smoke_effect

				IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
					START_CHAR_FACIAL_TALK char_mech_garag1[index_garag1] 4000
				ENDIF
			

				GOSUB cutscene_audio
				IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
					TASK_PLAY_ANIM_NON_INTERRUPTABLE char_mech_garag1[index_garag1] M_smk_out SMOKING 4.0 FALSE FALSE FALSE FALSE 0
				
					
					FREEZE_CHAR_POSITION char_mech_garag1[index_garag1] FALSE
					
				ENDIF




				IF heading_car_player_garage1 > 90.0
				AND heading_car_player_garage1 < 270.0 
				   //	SET_CAR_COORDINATES car_player_garag1 -2690.0598 894.6722 78.5344

					IF NOT IS_CHAR_DEAD char_truth_garag1
						TASK_PLAY_ANIM char_truth_garag1 CAR_Sc4_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE -1
					ENDIF

					TASK_PLAY_ANIM scplayer CAR_Sc4_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1

		   /*			SET_FIXED_CAMERA_POSITION -2227.35 541.37 35.61 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2398.33 634.21 18.66  JUMP_CUT	*/

				ELSE
				 //  	SET_CAR_COORDINATES car_player_garag1 -2674.3975 890.5623 78.6200

					IF NOT IS_CHAR_DEAD char_truth_garag1
						TASK_PLAY_ANIM char_truth_garag1 CAR_Sc3_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
					ENDIF
					TASK_PLAY_ANIM scplayer CAR_Sc2_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1

			 /*	 	SET_FIXED_CAMERA_POSITION -2228.18 547.44 35.75 0.0 0.0 0.0
				 	POINT_CAMERA_AT_POINT -2372.61 445.56 20.49  JUMP_CUT	*/
				ENDIF





			ENDIF

		ENDIF

		IF flag_cutscene_garage1 = 1
			IF TIMERA > 2000

				IF NOT IS_CAR_DEAD car_mech2_garag1
					LOCK_CAR_DOORS car_mech2_garag1 CARLOCK_UNLOCKED

						IF NOT IS_CHAR_DEAD char_mech_garag1[1]
							TASK_GO_STRAIGHT_TO_COORD char_mech_garag1[1] -2232.07 545.04 34.39 PEDMOVE_WALK -1
							REMOVE_ANIMATION SMOKING
							

					  //	REMOVE_ANIMATION PARK


					   //	TASK_LEAVE_CAR char_truth_garag1 car_player_garag1
				   	   //   PERFORM_SEQUENCE_TASK char_truth_garag1 seq_truth_talking_to_mech2_garag1 

						IF heading_car_player_garage1 > 90.0
						AND heading_car_player_garage1 < 270.0 

						 	SET_FIXED_CAMERA_POSITION -2233.6887 541.4159 35.2249 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT  -2233.1394 542.2441 35.1142 JUMP_CUT 


						ELSE

					   		SET_FIXED_CAMERA_POSITION -2228.6204 540.7846 35.5936 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT -2229.1992 541.5954 35.5075 JUMP_CUT	






						ENDIF

					ELSE
						PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!

						flag_go_to_mechanic2_garag1 = 0
						flag_mission_garag1_failed = 1
						RETURN

					ENDIF



					IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
						FREEZE_CAR_POSITION car_mech2_garag1 FALSE
					  //	TASK_ENTER_CAR_AS_DRIVER char_mech_garag1[index_garag1] car_mech2_garag1 5000
						GOSUB cutscene_audio						
					ELSE
						flag_go_to_mechanic2_garag1 = 0
						flag_mission_garag1_failed = 1
						RETURN
					ENDIF

				ELSE
					PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!

					flag_go_to_mechanic2_garag1 = 0
					flag_mission_garag1_failed = 1
					RETURN
				ENDIF
 			ENDIF
		ENDIF




		IF TIMERA > 1500
			IF flag_cutscene_garage1 = 2 
			   GOSUB cutscene_audio
				IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
				 //	SET_CHAR_COORDINATES char_mech_garag1[index_garag1] -2232.84 543.99 34.1
					SET_CHAR_HEADING char_mech_garag1[index_garag1] 270.0

				  	OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	// truth speaking to the mechanics
						TASK_GO_STRAIGHT_TO_COORD -1 -2232.07 545.04 34.39 PEDMOVE_WALK -1
					
						TASK_PLAY_ANIM -1 car_talkm_in CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0
						
						TASK_PLAY_ANIM -1 car_talkm_loop CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0

						TASK_PLAY_ANIM -1 car_talkm_out CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0



						   //	TASK_TURN_CHAR_TO_FACE_COORD -1 coords_garage_garag1_x[0] coords_garage_garag1_y[0] coords_garage_garag1_z[0]
					 //   TASK_ACHIEVE_HEADING -1 246.0
					 //  	TASK_PLAY_ANIM -1 wave_loop ON_LOOKERS 4.0 FALSE FALSE FALSE FALSE 1500
						
					CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
					PERFORM_SEQUENCE_TASK char_mech_garag1[1] seq_truth_talking_to_mech1_garag1 
					CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
					
					
  


				  //	TASK_CHAT_WITH_CHAR char_mech_garag1[index_garag1] scplayer FALSE TRUE
				  //	TASK_CHAT_WITH_CHAR scplayer char_mech_garag1[index_garag1] TRUE FALSE

					

				ENDIF






			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 3 
			GOSUB cutscene_audio
		ENDIF
		

		IF flag_cutscene_garage1 = 4 
			GOSUB cutscene_audio
		ENDIF
		

		IF flag_cutscene_garage1 = 5 
			GOSUB cutscene_audio
 


  
		ENDIF
		





		IF flag_cutscene_garage1 = 6
		   GOSUB cutscene_audio
			TIMERA = 0
			IF NOT IS_CAR_DEAD car_mech2_garag1
				IF NOT IS_CHAR_DEAD char_mech_garag1[1] 

					OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	// truth speaking to the mechanics
					TASK_GO_STRAIGHT_TO_COORD -1 -2241.66 547.19 34.49 PEDMOVE_RUN -1
				
			/*		TASK_PLAY_ANIM -1 car_talkm_in CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0
					
					TASK_PLAY_ANIM -1 car_talkm_loop CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0

					TASK_PLAY_ANIM -1 car_talkm_out CAR_CHAT 4.0 FALSE FALSE FALSE FALSE 0	 */

					TASK_ENTER_CAR_AS_DRIVER -1 car_mech2_garag1 -1




					   //	TASK_TURN_CHAR_TO_FACE_COORD -1 coords_garage_garag1_x[0] coords_garage_garag1_y[0] coords_garage_garag1_z[0]
				 //   TASK_ACHIEVE_HEADING -1 246.0
				 //  	TASK_PLAY_ANIM -1 wave_loop ON_LOOKERS 4.0 FALSE FALSE FALSE FALSE 1500
					
					CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
					PERFORM_SEQUENCE_TASK char_mech_garag1[1] seq_truth_talking_to_mech1_garag1 
					CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1  
				ENDIF
			ENDIF

		ENDIF
		


		IF flag_cutscene_garage1 = 7
			GOSUB cutscene_audio

			SET_FIXED_CAMERA_POSITION -2229.1545 533.4247 41.4992 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT  -2229.6589 534.1230 40.9914 JUMP_CUT

			
				IF NOT IS_CAR_DEAD car_mech2_garag1
					IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
						WARP_CHAR_INTO_CAR char_mech_garag1[index_garag1] car_mech2_garag1
						
						IF IS_CHAR_IN_CAR char_mech_garag1[index_garag1] car_mech2_garag1

							OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	// truth speaking to the mechanics
								TASK_CAR_DRIVE_TO_COORD -1 car_mech2_garag1 -2240.43 557.96 34.0 10.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
								TASK_CAR_DRIVE_TO_COORD -1 car_mech2_garag1 -2185.43 567.96 34.0 50.0 MODE_STRAIGHTLINE 0 DRIVINGMODE_PLOUGHTHROUGH
							CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
							PERFORM_SEQUENCE_TASK char_mech_garag1[1] seq_truth_talking_to_mech1_garag1 
							CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1  


						 		   
						ENDIF
					ENDIF
				ENDIF

		ENDIF
		

		IF TIMERA > 4000
			IF flag_cutscene_garage1 = 8
				GOSUB cutscene_audio
				IF NOT IS_CAR_DEAD car_player_garag1
				  /*	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 0.5 1.5 0.8 offset_players_car_x offset_players_car_y offset_players_car_z
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 0.0 -1.5 0.5 offset_players_car2_x offset_players_car2_y offset_players_car2_z
					SET_FIXED_CAMERA_POSITION offset_players_car_x offset_players_car_y offset_players_car_z 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT offset_players_car2_x offset_players_car2_y offset_players_car2_z  JUMP_CUT
						*/

					SET_FIXED_CAMERA_POSITION -2235.0105 538.8505 34.3478 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT  -2234.1826 539.4094 34.3942 JUMP_CUT


					IF NOT IS_CHAR_DEAD char_truth_garag1
						TASK_PLAY_ANIM char_truth_garag1 CAR_Sc3_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000
					ENDIF



				ENDIF
				STOP_FX_SYSTEM exhale_smoke_effect















				IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
					DELETE_CHAR char_mech_garag1[index_garag1]
				ENDIF

				IF NOT IS_CAR_DEAD car_mech2_garag1
			   		DELETE_CAR car_mech2_garag1
				ENDIF

				IF DOES_OBJECT_EXIST dwaynes_blunt
					DELETE_OBJECT dwaynes_blunt
				ENDIF

			ENDIF
		ENDIF

		IF TIMERA > 1500
			IF flag_cutscene_garage1 = 9
				GOSUB cutscene_audio
			ENDIF
		ENDIF

		IF TIMERA > 1500
			IF flag_cutscene_garage1 = 10
				GOSUB cutscene_audio
			ENDIF
		ENDIF

		IF TIMERA > 1500
			IF flag_cutscene_garage1 = 11
				GOSUB cutscene_audio
			ENDIF
		ENDIF

		IF TIMERA > 1500
			IF flag_cutscene_garage1 = 12
				GOSUB cutscene_audio
				flag_cutscene_garage1 = 20
			ENDIF
		ENDIF
					
		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			flag_cutscene_garage1 = 20
			STOP_FX_SYSTEM exhale_smoke_effect
			IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
				DELETE_CHAR char_mech_garag1[index_garag1]
			ENDIF
			IF NOT IS_CAR_DEAD car_mech2_garag1
		   		DELETE_CAR car_mech2_garag1
			ENDIF

			IF DOES_OBJECT_EXIST dwaynes_blunt
				DELETE_OBJECT dwaynes_blunt
			ENDIF
			CLEAR_MISSION_AUDIO 1

		ENDIF

		IF TIMERB > 40000
			flag_cutscene_garage1 = 20
		ENDIF

			
	ENDWHILE

	SKIP_CUTSCENE_END

	//SET_CAMERA_IN_FRONT_OF_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF

	REMOVE_ANIMATION CAR_CHAT
	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

	ENDIF




  //	SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
   //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0


	
	flag_cutscene_garage1 = 0
	play_audio_flag_garage1 = 0

	CLEAR_PRINTS

	// ///////// WARP TRUTH AND MECHANIC INTO CAR  ///////////////////////
	IF NOT IS_CAR_DEAD car_player_garag1
		SET_CAR_STATUS car_player_garag1 STATUS_PLAYER

		IF NOT IS_CHAR_DEAD char_truth_garag1		 
			IF NOT IS_CHAR_IN_CAR char_truth_garag1 car_player_garag1
				IF IS_CAR_PASSENGER_SEAT_FREE car_player_garag1 0
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_truth_garag1 car_player_garag1 0
				ELSE
					GET_CHAR_IN_CAR_PASSENGER_SEAT car_player_garag1 0 temp_passenger_to_be_deleted_garage1
					DELETE_CHAR temp_passenger_to_be_deleted_garage1
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_truth_garag1 car_player_garag1 0
		   		ENDIF 	 
			ENDIF
		ENDIF

	   	IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
			IF NOT IS_CAR_DEAD car_mech2_garag1
				IF NOT IS_CHAR_IN_CAR char_mech_garag1[index_garag1] car_mech2_garag1
					WARP_CHAR_INTO_CAR char_mech_garag1[index_garag1] car_mech2_garag1
			   	ENDIF 
			ENDIF
	   	ENDIF

	ENDIF	 
   	MARK_MODEL_AS_NO_LONGER_NEEDED HOTDOG
	MARK_MODEL_AS_NO_LONGER_NEEDED cigar
	UNLOAD_SPECIAL_CHARACTER 4 // unloading zero


	 IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1]
	 	DELETE_CHAR char_mech_garag1[index_garag1]
	 ENDIF

	 IF NOT IS_CAR_DEAD car_mech2_garag1
	 	DELETE_CAR car_mech2_garag1
	 ENDIF

	IF DOES_OBJECT_EXIST dwaynes_blunt
		DELETE_OBJECT dwaynes_blunt
	ENDIF


	


	index_garag1 = 2

	flag_go_to_mechanic2_garag1 = 0
	flag_go_to_police_station_garag1 = 1

RETURN

// Goto police station

go_to_police_station_garag1:
  	//ADD_BLIP_FOR_COORD coords_police_x coords_police_y coords_police_z blip_police
  	ADD_SPRITE_BLIP_FOR_COORD coords_police_x coords_police_y coords_police_z RADAR_SPRITE_POLICE blip_police


	GOSUB going_to_destinations_garage1

	IF flag_mission_garag1_failed = 1 
		RETURN
	ENDIF

		REQUEST_MODEL pony 
		REQUEST_ANIMATION CAR_CHAT
		 

		WHILE NOT HAS_MODEL_LOADED pony
		OR NOT HAS_ANIMATION_LOADED CAR_CHAT
			WAIT 0
		ENDWHILE


	
		CREATE_CAR pony -1659.92 751.31 17.29 car_zombietech_garage1
		
		LOCK_CAR_DOORS car_zombietech_garage1 CARLOCK_LOCKED

		SET_CAR_HEADING car_zombietech_garage1 180.0
		FREEZE_CAR_POSITION car_zombietech_garage1 TRUE




		
		IF NOT IS_CAR_DEAD car_zombietech_garage1
			CREATE_CHAR_INSIDE_CAR car_zombietech_garage1 PEDTYPE_CIVMALE male01 pony_driver_garage1
			FREEZE_CAR_POSITION car_zombietech_garage1 FALSE
			SET_CAR_CRUISE_SPEED car_zombietech_garage1 30.0 
		ENDIF

		char_who_is_talking[69] = char_truth_garag1//	Ok, you know the drill.
		char_who_is_talking[70] = char_truth_garag1//	Don’t look interested in anything.
		char_who_is_talking[71] = char_truth_garag1//	Picture a pink golfball in your mind.
		char_who_is_talking[72] = char_truth_garag1//	Ok, we’re good to go.
		char_who_is_talking[73] = scplayer	//	Where to next, El Kozmo?
		char_who_is_talking[74] = char_truth_garag1//	There’s an electronics guy I’ve had dealings with, 
		char_who_is_talking[75] = char_truth_garag1//	goes by the name of ZERO.
		char_who_is_talking[76] = char_truth_garag1//	He could fix a supercomputer with a paperclip.
		char_who_is_talking[77] = char_truth_garag1//	He’s got his own shop, 
		char_who_is_talking[78] = char_truth_garag1//	but he’s always ready to help fellow travellers along the path.
		char_who_is_talking[79] = char_truth_garag1//	Let’s go introduce you to him

		char_who_is_talking[80] = scplayer //	Look, what’s goin’ on, Truth?
		char_who_is_talking[81] = scplayer	//	Who were those guys?
		char_who_is_talking[82] = char_mech_garag1[0] //	Don’t go there, man.
		char_who_is_talking[83] = char_truth_garag1//	Listen to Jethro.
		char_who_is_talking[84] = char_truth_garag1//	What if I told you we never went to the moon, 
		char_who_is_talking[85] = char_truth_garag1//	JFK lives in Scotland with Janis Joplin 
		char_who_is_talking[86] = char_truth_garag1//	and the only reason we’ve been in a cold war for the last fortyfive years 
		char_who_is_talking[87] = char_truth_garag1//	was because snake-headed aliens run the oil business?
		char_who_is_talking[88] = scplayer//	I’d say you’d dropped another microdot.
		char_who_is_talking[89] = char_truth_garag1//	Good. Keep it that way.
		char_who_is_talking[91] = char_truth_garag1//	Blow the horn.
					
	



 

	// cutscene of strange goings on
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	CLEAR_MISSION_AUDIO 1

  //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
   //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0





	 IF NOT IS_CAR_DEAD car_player_garag1

	 	IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
			WARP_CHAR_INTO_CAR scplayer car_player_garag1
		ENDIF 
		
		GET_CAR_HEADING car_player_garag1 heading_car_player_garage1
		SET_CAR_COORDINATES car_player_garag1 -1626.1670 725.0057 13.4609 
		IF heading_car_player_garage1 > 0.0
		AND heading_car_player_garage1 < 180.0 
			SET_CAR_HEADING car_player_garag1 90.0
		ELSE
			SET_CAR_HEADING car_player_garag1 270.0
		ENDIF

		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 1.0 1.5 0.8 offset_players_car_x offset_players_car_y offset_players_car_z
		GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 0.0 -1.5 0.5 offset_players_car2_x offset_players_car2_y offset_players_car2_z
		SET_FIXED_CAMERA_POSITION offset_players_car_x offset_players_car_y offset_players_car_z 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT offset_players_car2_x offset_players_car2_y offset_players_car2_z  JUMP_CUT

		CLEAR_AREA -1659.92 751.31 17.29 1000.0 TRUE 
		
		


	 ENDIF	   

 


	 IF NOT IS_CHAR_DEAD char_truth_garag1
	 	TASK_PLAY_ANIM char_truth_garag1 CAR_Sc1_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000

		//START_CHAR_FACIAL_TALK char_truth_garag1 29000
				

	 ENDIF


	/*	IF NOT IS_CAR_DEAD car_player_garag1 
		POINT_CAMERA_AT_CAR car_player_garag1 FIXED JUMP_CUT
	ENDIF		   */


	flag_cutscene_garage1 = 0
	index_dialogue_garage1 = 69
	TIMERA = 0
	TIMERB = 0


   WHILE NOT flag_cutscene_garage1 = 20
   		WAIT 0

   		//CLEAR_AREA coords_police_x coords_police_y coords_police_z 1000.0 TRUE 

   		GOSUB checking_buddies_garage1


   		IF flag_mission_garag1_failed = 1 
   			RETURN
   		ENDIF



   		IF flag_cutscene_garage1 = 0
   			SKIP_CUTSCENE_START
   			IF TIMERA > 1500
   			
   				 IF NOT IS_CHAR_DEAD char_truth_garag1
					START_CHAR_FACIAL_TALK char_truth_garag1 29000
				 ENDIF
			
   				GOSUB cutscene_audio
   			ENDIF
   		ENDIF

   		IF flag_cutscene_garage1 = 1
   			IF TIMERA > 2000			
   				GOSUB cutscene_audio 


   			  //	SET_FIXED_CAMERA_POSITION -1675.4844 717.8833 37.3235 0.0 0.0 0.0
   			  //	POINT_CAMERA_AT_POINT -1674.8118 718.2260 36.6677  JUMP_CUT



   				IF NOT IS_CAR_DEAD car_zombietech_garage1
   					IF NOT IS_CHAR_DEAD pony_driver_garage1
   					   //	CAR_GOTO_COORDINATES car_zombietech_garage1 -1564.43 728.96 6.0
   						IF IS_CHAR_IN_CAR pony_driver_garage1 car_zombietech_garage1
   							TASK_CAR_DRIVE_TO_COORD pony_driver_garage1 car_zombietech_garage1 -1716.03 732.96 24.9 15.0 MODE_NORMAL 0 DRIVINGMODE_AVOIDCARS
   						ENDIF
   					   //	SET_CAR_CRUISE_SPEED car_zombietech_garage1 30.0 
   					ENDIF
   				ENDIF

   			   	SET_FIXED_CAMERA_POSITION 	-1671.5474 729.8510 18.6107  0.0 0.0 0.0
   			 	POINT_CAMERA_AT_POINT 		-1670.6207 730.2037 18.7409   JUMP_CUT 		

				  
				  
   				TIMERA = 0
   			ENDIF
   		ENDIF

   		IF flag_cutscene_garage1 = 2
   		   	IF TIMERA > 2000			
   			   //	PRINT_NOW GAR1_LC 2000 1  //	Picture a pink golfball in your mind.



   				GOSUB cutscene_audio 
   		  	ENDIF
   		ENDIF

   		IF flag_cutscene_garage1 = 3
   			IF TIMERA > 6000
   			//	SET_FIXED_CAMERA_POSITION -1615.4164 722.5748 13.7408 0.0 0.0 0.0
   			 //	POINT_CAMERA_AT_POINT -1616.2449 723.0778 13.9868  JUMP_CUT 
   			 
				SET_FIXED_CAMERA_POSITION 	-1624.6388 723.3417 14.5936 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 		-1625.4730 723.8466 14.8151 JUMP_CUT

				 TASK_PLAY_ANIM scplayer CAR_Sc1_FL CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1
				 START_CHAR_FACIAL_TALK scplayer 3000

				 IF NOT IS_CHAR_DEAD char_truth_garag1
				 	TASK_PLAY_ANIM char_truth_garag1 CAR_Sc1_FR CAR_CHAT 4.0 TRUE FALSE FALSE FALSE 9000

									

				 ENDIF

				
		   			 
   			 
   			  
	
   				GOSUB cutscene_audio 

				 
				 IF NOT IS_CHAR_DEAD char_truth_garag1
				 	START_CHAR_FACIAL_TALK char_truth_garag1 20000
				 ENDIF




   			ENDIF
   		ENDIF






   		IF flag_cutscene_garage1 >= 4
   		AND flag_cutscene_garage1 <= 7
   			GOSUB cutscene_audio 
   		ENDIF

    

   		IF flag_cutscene_garage1 = 8
   			IF TIMERA > 2000
   			
   				SET_FIXED_CAMERA_POSITION -1628.7999 727.3613 14.7421 0.0 0.0 0.0
   				POINT_CAMERA_AT_POINT -1627.8439 727.0695 14.7728  JUMP_CUT
   	
   				GOSUB cutscene_audio
   			/*	GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 1.0 1.5 0.8 offset_players_car_x offset_players_car_y offset_players_car_z
   				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS car_player_garag1 0.0 -1.5 0.5 offset_players_car2_x offset_players_car2_y offset_players_car2_z
   				SET_FIXED_CAMERA_POSITION offset_players_car_x offset_players_car_y offset_players_car_z 0.0 0.0 0.0
   				POINT_CAMERA_AT_POINT offset_players_car2_x offset_players_car2_y offset_players_car2_z  JUMP_CUT
   				*/


   				TIMERA = 0
   			ENDIF
   		ENDIF

   		IF flag_cutscene_garage1 >= 9
   		AND flag_cutscene_garage1 <= 11
   			GOSUB cutscene_audio
   		ENDIF

   		IF flag_cutscene_garage1 >= 12
   				flag_cutscene_garage1 = 20
   		ENDIF

   		//	IF flag_cutscene_garage1 > 1

   		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
   			flag_cutscene_garage1 = 20
			CLEAR_MISSION_AUDIO 1
   		ENDIF
    	
   		IF TIMERB > 29000
   			flag_cutscene_garage1 = 20
   		ENDIF

   		
   ENDWHILE
   SKIP_CUTSCENE_END

	 IF NOT IS_CHAR_DEAD char_truth_garag1

		STOP_CHAR_FACIAL_TALK char_truth_garag1
				

	 ENDIF



		//SET_CAMERA_IN_FRONT_OF_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF
	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

	ENDIF


	REMOVE_ANIMATION CAR_CHAT
	MARK_MODEL_AS_NO_LONGER_NEEDED pony



  //	SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
  //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0


	flag_cutscene_garage1 = 0
	index_dialogue_garage1 = 80
	play_audio_flag_garage1 = 0

	CLEAR_PRINTS

	REMOVE_BLIP blip_police
	DELETE_CHAR pony_driver_garage1
	DELETE_CAR car_zombietech_garage1 

	flag_go_to_police_station_garag1 = 0
	flag_go_to_mechanic3_garag1 = 1
	REMOVE_ANIMATION CAR_CHAT
RETURN


// ////////////////////////////////
// go_to_mechanic3	   ZERO
// ////////////////////////////////


go_to_mechanic3_garag1:

	ADD_BLIP_FOR_COORD coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] blip_mech_garag1[index_garag1]
  //	CHANGE_BLIP_COLOUR blip_mech_garag1[index_garag1] BLUE 
	SET_COORD_BLIP_APPEARANCE blip_mech_garag1[index_garag1] COORD_BLIP_APPEARANCE_FRIEND
  	//SET_BLIP_AS_FRIENDLY blip_mech_garag1[index_garag1] TRUE
  //	PRINT_NOW (GAR1_13) 5000 1 //lets go pick up zero
		
	REQUEST_ANIMATION CRIB
	REQUEST_MODEL rcraider

	WHILE NOT HAS_ANIMATION_LOADED CRIB
		OR NOT HAS_MODEL_LOADED rcraider

		WAIT 0
	ENDWHILE

		
	CREATE_OBJECT parkbench1 -2242.6 136.91 34.65 object_zeros_bench
	DELETE_OBJECT object_zeros_bench 

	CREATE_CAR rcraider -2250.33 139.91 44.66 rc_heli_garage1
	DELETE_CAR rc_heli_garage1


	LOAD_SPECIAL_CHARACTER 1 zero

	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 1
		WAIT 0
	ENDWHILE   

	REQUEST_MODEL bomb
	REQUEST_MODEL SATCHEL 
	WHILE NOT HAS_MODEL_LOADED bomb
		OR NOT HAS_MODEL_LOADED SATCHEL
		WAIT 0
	ENDWHILE 




	
	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 coord_mech_garage1_x[2] coord_mech_garage1_y[2] coord_mech_garage1_z[2] char_mech_garag1[2]
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER char_mech_garag1[2] TRUE
	GIVE_WEAPON_TO_CHAR char_mech_garag1[2] WEAPONTYPE_REMOTE_SATCHEL_CHARGE 10
	SET_CHAR_NEVER_TARGETTED char_mech_garag1[2] TRUE
	GIVE_WEAPON_TO_CHAR char_mech_garag1[2] WEAPONTYPE_DETONATOR 1 
  	SET_CHAR_RELATIONSHIP char_mech_garag1[2] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
	SET_CHAR_DECISION_MAKER char_mech_garag1[2] dm_buddies_garage1 
	SET_CURRENT_CHAR_WEAPON char_mech_garag1[2] WEAPONTYPE_DETONATOR



	DELETE_CHAR char_mech_garag1[2]



 
	GOSUB going_to_destinations_garage1

	REQUEST_ANIMATION CAR_CHAT

	WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
		WAIT 0
	ENDWHILE


	IF flag_mission_garag1_failed = 1 
		RETURN
	ENDIF


	char_who_is_talking[92] = char_mech_garag1[2]//	Leave me alone Berkley!
	char_who_is_talking[93] = char_mech_garag1[2]//	This is stalking!
	char_who_is_talking[94] = char_mech_garag1[2]//	Oh, hi!
	char_who_is_talking[95] = char_truth_garag1//	Get in, I’ll fill you in as we drive.
	char_who_is_talking[96] = char_truth_garag1//	Home, James!

	char_who_is_talking[97] = char_truth_garag1//	Carl, Zero, Zero, Carl.
	char_who_is_talking[98] = scplayer //	Yo.
	char_who_is_talking[99] = char_truth_garag1//	Carl here is opening a garage around the corner here. 
	char_who_is_talking[100] = char_truth_garag1//	I told him you’re the man to speak to when it comes to electronics.
	char_who_is_talking[101] = char_mech_garag1[2]//	I’m the ONLY man to talk to.  
	char_who_is_talking[102] = char_mech_garag1[2]//	Grade A fuckin’ genius, that’s me. 

	char_who_is_talking[103] = char_mech_garag1[2]//	You should drop by the shop sometime, see some of my shit.
	char_who_is_talking[104] = scplayer//	I’ll do just that.
	char_who_is_talking[105] = scplayer//	Yo, we’re here.



	


 	// CUTSCENE HERE:-

	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	CLEAR_MISSION_AUDIO 1

   //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
  //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0

	SWITCH_RANDOM_TRAINS OFF
	//CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE


	//Setting Up The Camera Positioning
 //	SET_FIXED_CAMERA_POSITION -2251.1777 123.6392 37.5361 0.0 0.0 0.0

	IF NOT IS_CAR_DEAD rc_heli_garage1
		SET_CAR_COORDINATES rc_heli_garage1 -2244.56 127.11 40.0
	 	HELI_LAND_AT_COORDS rc_heli_garage1 -2244.48 129.14 34.56 0.0 0.0 
		SET_CAR_CRUISE_SPEED rc_heli_garage1 21.0

	   //	SET_HELI_STABILISER rc_heli_garage1 FALSE
	ENDIF


	SET_FIXED_CAMERA_POSITION -2250.3059 140.9439 41.7533 0.0 0.0 0.0	  //1st
	POINT_CAMERA_AT_POINT -2249.8042 140.3430 41.1311 JUMP_CUT
	CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE 
	



	IF NOT IS_CAR_DEAD car_player_garag1

		IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
			WARP_CHAR_INTO_CAR scplayer car_player_garag1
		ENDIF 
		
		GET_CAR_HEADING car_player_garag1 heading_car_player_garage1

		IF heading_car_player_garage1 > 90.0
		AND heading_car_player_garage1 < 270.0 
		   	SET_CAR_HEADING car_player_garag1 180.0
		ELSE
			SET_CAR_HEADING car_player_garag1 0.0
		ENDIF


		IF NOT IS_CAR_PASSENGER_SEAT_FREE car_player_garag1 1

			GET_CHAR_IN_CAR_PASSENGER_SEAT car_player_garag1 1 temp_passenger_to_be_deleted_garage1
			DELETE_CHAR temp_passenger_to_be_deleted_garage1
		ENDIF 

	ENDIF



	IF NOT IS_CHAR_DEAD char_mech_garag1[2]
	  //	POINT_CAMERA_AT_CHAR char_mech_garag1[2] FIXED JUMP_CUT 

		SET_CHAR_HEALTH char_mech_garag1[2] 200
		SET_CHAR_SUFFERS_CRITICAL_HITS char_mech_garag1[2] FALSE
		SET_CHAR_PROOFS char_mech_garag1[2] TRUE TRUE TRUE TRUE TRUE

		
	ENDIF




	index_dialogue_garage1 = 92
	flag_cutscene_garage1 = 0
	TIMERA = 0
	TIMERB = 0
	WHILE NOT flag_cutscene_garage1 = 10
		WAIT 0

	   //	CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE 

	   /*	GOSUB checking_buddies_garage1
		IF flag_mission_garag1_failed = 1 
			RETURN
		ENDIF  */

		IF flag_cutscene_garage1 = 0
			IF TIMERA > 1500	
				SKIP_CUTSCENE_START


				GOSUB cutscene_audio
			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 1
			IF TIMERA > 2000	
				GOSUB cutscene_audio

			/*	SET_FIXED_CAMERA_POSITION -2246.42 136.7 35.55 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2069.62 137.41 27.17 JUMP_CUT   */

			  //	SET_FIXED_CAMERA_POSITION -2242.4797 139.2066 35.0826 0.0 0.0 0.0	  //2nd
			//	POINT_CAMERA_AT_POINT -2242.8848 138.2930 35.0484 JUMP_CUT


			  	SET_FIXED_CAMERA_POSITION  -2244.4487 135.8613 34.9713  0.0 0.0 0.0	  //2nd
				POINT_CAMERA_AT_POINT 	   -2244.8445 134.9551 35.1198  JUMP_CUT






			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 2
			IF TIMERA > 3000	
				GOSUB cutscene_audio

				IF NOT IS_CHAR_DEAD char_mech_garag1[2]

					IF NOT IS_CAR_DEAD car_player_garag1
						IF NOT IS_CHAR_IN_ANY_CAR char_mech_garag1[2]
							SET_CHAR_COLLISION char_mech_garag1[2] TRUE
						ENDIF
						SET_CHAR_COORDINATES char_mech_garag1[2] -2245.34 134.11 34.5
					 //	SET_CHAR_HEADING  char_mech_garag1[2] 90.0
				  //		SET_CURRENT_CHAR_WEAPON char_mech_garag1[2] WEAPONTYPE_UNARMED
						IF heading_car_player_garage1 > 90.0
						AND heading_car_player_garage1 < 270.0 
							OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1  
								TASK_ACHIEVE_HEADING -1 93.0
							   //	TASK_GO_STRAIGHT_TO_COORD -1 -2252.853 138.619 34.219 PEDMOVE_RUN -1
								TASK_ENTER_CAR_AS_PASSENGER -1 car_player_garag1 6000 1
							CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
						   	PERFORM_SEQUENCE_TASK char_mech_garag1[2] seq_truth_talking_to_mech1_garag1 
							CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
						ELSE
							OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1  
								TASK_ACHIEVE_HEADING -1 93.0
								TASK_GO_STRAIGHT_TO_COORD -1 -2252.853 138.619 34.219 PEDMOVE_RUN -1
								TASK_ENTER_CAR_AS_PASSENGER -1 car_player_garag1 6000 1
							CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
						   	PERFORM_SEQUENCE_TASK char_mech_garag1[2] seq_truth_talking_to_mech1_garag1 
							CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1

						ENDIF


					ELSE
						PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!
						flag_go_to_mechanic3_garag1 = 0
						flag_mission_garag1_failed = 1
						RETURN										
																	
					ENDIF

				   /*	SET_FIXED_CAMERA_POSITION -2242.1472 139.2691 34.9073  0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2242.6929 138.4313 34.8864 JUMP_CUT		 */

					SET_FIXED_CAMERA_POSITION -2251.4207 130.1502 35.5076 0.0 0.0 0.0	 //3rd
					POINT_CAMERA_AT_POINT  -2250.7454 130.8858 35.4549  JUMP_CUT
				ENDIF

			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 3
			IF TIMERA > 3000	

				SET_FIXED_CAMERA_POSITION -2253.4512 139.6341 38.4909 0.0 0.0 0.0	  //4th
				POINT_CAMERA_AT_POINT  -2252.7021 139.0740 38.1371  JUMP_CUT



				GOSUB cutscene_audio
			ENDIF
		ENDIF

		IF flag_cutscene_garage1 = 4
			IF TIMERA > 3000	
				GOSUB cutscene_audio
				flag_cutscene_garage1 = 5
				TIMERA = 0
			ENDIF
		ENDIF


		IF flag_cutscene_garage1 = 5
			IF TIMERA > 2000	
			  //	GOSUB cutscene_audio
				flag_cutscene_garage1 = 10
				
			ENDIF
		ENDIF


		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			flag_cutscene_garage1 = 10
			CLEAR_MISSION_AUDIO 1
		ENDIF

	
		IF TIMERB > 13500
			flag_cutscene_garage1 = 10
		ENDIF
	
	ENDWHILE 

	SKIP_CUTSCENE_END

	IF NOT IS_CHAR_DEAD char_mech_garag1[2]
		SET_CURRENT_CHAR_WEAPON char_mech_garag1[2] WEAPONTYPE_UNARMED	

		IF NOT IS_CAR_DEAD car_player_garag1
			IF NOT IS_CHAR_IN_CAR char_mech_garag1[2] car_player_garag1


				IF IS_CAR_PASSENGER_SEAT_FREE car_player_garag1 1



	
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_mech_garag1[2] car_player_garag1 1
				ELSE

					GET_CHAR_IN_CAR_PASSENGER_SEAT car_player_garag1 1 temp_passenger_to_be_deleted_garage1
					DELETE_CHAR temp_passenger_to_be_deleted_garage1
					WARP_CHAR_INTO_CAR_AS_PASSENGER char_mech_garag1[index_garag1] car_player_garag1 1
		   		ENDIF 


				SET_CURRENT_CHAR_WEAPON char_mech_garag1[2] WEAPONTYPE_UNARMED
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER char_mech_garag1[2] FALSE
			ENDIF  
		ENDIF
		SET_CHAR_PROOFS char_mech_garag1[2] FALSE FALSE FALSE FALSE FALSE
	ENDIF

	IF NOT IS_CAR_DEAD rc_heli_garage1
		SET_CAR_COORDINATES rc_heli_garage1 -2242.34 128.00 34.2
		SET_CAR_PROOFS rc_heli_garage1 FALSE FALSE FALSE FALSE FALSE 
		SET_CAR_CRUISE_SPEED rc_heli_garage1 0.0
		SET_CAR_HEALTH rc_heli_garage1 400 
		FREEZE_CAR_POSITION rc_heli_garage1 TRUE 
	   //	SET_HELI_STABILISER rc_heli_garage1 FALSE
	ENDIF




			
	//SET_CAMERA_IN_FRONT_OF_PLAYER
	RESTORE_CAMERA_JUMPCUT
	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF
	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

	ENDIF

	SWITCH_RANDOM_TRAINS ON

	//SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
   //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0


	index_dialogue_garage1 = 97
	flag_cutscene_garage1 = 0
	play_audio_flag_garage1 = 0

	CLEAR_PRINTS
	MARK_OBJECT_AS_NO_LONGER_NEEDED object_zeros_bench
	MARK_MODEL_AS_NO_LONGER_NEEDED bomb
	MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL

	REMOVE_ANIMATION CRIB
   //	DELETE_CAR rc_heli_garage1
	MARK_CAR_AS_NO_LONGER_NEEDED rc_heli_garage1

	REMOVE_ANIMATION CAR_CHAT




  /*	IF NOT IS_CAR_DEAD car_player_garag1
		OPEN_GARAGE hbgdsfs
	ENDIF		  */

	index_garag1 = 3
	flag_go_to_mechanic3_garag1 = 0

  	flag_go_to_hub_garag1 = 1

   //	MARK_CAR_AS_NO_LONGER_NEEDED rc_heli_garage1
	MARK_MODEL_AS_NO_LONGER_NEEDED rcraider

	
   //	flag_go_to_mechanic4_garag1 = 1

RETURN





/// RETURN TO HUB WITH GIRL FOLLOWING PLAYER ON SPORTS BIKE


go_to_hub_garag1:

	ADD_BLIP_FOR_COORD coords_hub_garag1_x coords_hub_garag1_y coords_hub_garag1_z blip_mech_garag1[index_garag1]
   //	CHANGE_BLIP_COLOUR blip_mech_garag1[index_garag1] BLUE 
   //	SET_COORD_BLIP_APPEARANCE blip_mech_garag1[index_garag1] COORD_BLIP_APPEARANCE_FRIEND
	//SET_BLIP_AS_FRIENDLY blip_mech_garag1[index_garag1] TRUE


	REQUEST_MODEL hotdog

	WHILE NOT HAS_MODEL_LOADED hotdog
		WAIT 0
	ENDWHILE


	GOSUB going_to_destinations_garage1
														  
	IF flag_mission_garag1_failed = 1 
		RETURN
	ENDIF

							
							
							





	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	CLEAR_MISSION_AUDIO 1
	//SET_AREA_VISIBLE 1 
 //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
 //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


	SET_CAR_DENSITY_MULTIPLIER 0.0
	SET_PED_DENSITY_MULTIPLIER 0.0

	TIMERA = 0

		//Setting Up The Camera Positioning
		//SET_FIXED_CAMERA_POSITION -2053.05 175.81 28.81 0.0 0.0 0.0
  //		SET_FIXED_CAMERA_POSITION -2018.3113 155.8209 31.0081   0.0 0.0 0.0
  //		POINT_CAMERA_AT_POINT -2018.9553 156.5835 30.9495 JUMP_CUT

	SET_FIXED_CAMERA_POSITION 	-2040.6302 180.9374 29.2617   0.0 0.0 0.0
	POINT_CAMERA_AT_POINT 		-2039.7999 180.3834 29.3231    JUMP_CUT
	CLEAR_AREA coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 1000.0 TRUE









		IF NOT IS_CAR_DEAD car_player_garag1
			
			SET_CAR_COORDINATES car_player_garag1 -2033.9316 178.5836 27.6359 
			SET_CAR_HEADING car_player_garag1 86.5989 


			IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
				WARP_CHAR_INTO_CAR scplayer car_player_garag1
			ENDIF 


			TASK_CAR_DRIVE_TO_COORD scplayer car_player_garag1 -2039.97 179.98 28.08 20.0  MODE_STRAIGHTLINE 1 DRIVINGMODE_PLOUGHTHROUGH
			

				





		ENDIF


		 	LOAD_MISSION_AUDIO 1 SOUND_GAR1_PH
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE  

			PRINT_NOW ( GAR1_PH ) 10000 1		//	we'rehere
			PLAY_MISSION_AUDIO 1
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
			ENDWHILE
			CLEAR_THIS_PRINT GAR1_PH


	


		flag_cutscene_garage1 = 0
		WHILE TIMERA < 4000
			WAIT 0




			IF flag_cutscene_garage1 = 0
				IF TIMERA > 1000

					IF NOT IS_CAR_DEAD car_player_garag1
						IF NOT IS_CHAR_DEAD char_mech_garag1[0]
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS char_mech_garag1[0] 1.0 5.0 0.0 mech_offset_x mech_offset_y mech_offset_z

							OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	
								TASK_LEAVE_CAR -1 car_player_garag1
								TASK_GO_STRAIGHT_TO_COORD -1 mech_offset_x mech_offset_y mech_offset_z PEDMOVE_WALK -1
							CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
							PERFORM_SEQUENCE_TASK char_mech_garag1[0] seq_truth_talking_to_mech1_garag1 
							CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
						ENDIF
					ENDIF

					WAIT 1000

					IF NOT IS_CAR_DEAD car_player_garag1

						IF NOT IS_CHAR_DEAD char_mech_garag1[2]
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS char_mech_garag1[2] -5.0 0.0 0.0 mech_offset_x mech_offset_y mech_offset_z
							
							OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	
								TASK_LEAVE_CAR -1 car_player_garag1
								TASK_GO_STRAIGHT_TO_COORD -1 mech_offset_x mech_offset_y mech_offset_z PEDMOVE_WALK -1
							CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
							PERFORM_SEQUENCE_TASK char_mech_garag1[2] seq_truth_talking_to_mech1_garag1 
							CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1

						ENDIF
					ENDIF

					WAIT 500

					IF NOT IS_CAR_DEAD car_player_garag1
						IF NOT IS_CHAR_DEAD char_truth_garag1

							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS char_truth_garag1 1.0 5.0 0.0 mech_offset_x mech_offset_y mech_offset_z
							
							OPEN_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1	
								TASK_LEAVE_CAR -1 car_player_garag1
								TASK_GO_STRAIGHT_TO_COORD -1 mech_offset_x mech_offset_y mech_offset_z PEDMOVE_WALK -1
							CLOSE_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
							PERFORM_SEQUENCE_TASK char_truth_garag1 seq_truth_talking_to_mech1_garag1 
							CLEAR_SEQUENCE_TASK seq_truth_talking_to_mech1_garag1
						ENDIF
					ENDIF

					flag_cutscene_garage1 = 1
				
				ENDIF
			ENDIF

			IF flag_cutscene_garage1 = 1
				IF TIMERA > 2500

					SET_FADING_COLOUR 0 0 0
					DO_FADE 1000 FADE_OUT
					flag_cutscene_garage1 = 2
				ENDIF
			ENDIF

			IF flag_mission_garag1_failed = 1 
				RETURN
			ENDIF


		ENDWHILE 
	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer -2028.0 177.0 28.2
	ENDIF	




	REMOVE_ANIMATION smoking

	REMOVE_ANIMATION PARK
	REMOVE_ANIMATION CAR
	REMOVE_ANIMATION CRIB
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION CAR_CHAT

   	MARK_MODEL_AS_NO_LONGER_NEEDED bomb
   	MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL
	MARK_MODEL_AS_NO_LONGER_NEEDED emperor
	MARK_MODEL_AS_NO_LONGER_NEEDED HOTDOG
	MARK_MODEL_AS_NO_LONGER_NEEDED pony
	MARK_MODEL_AS_NO_LONGER_NEEDED rcraider
	MARK_MODEL_AS_NO_LONGER_NEEDED towtruck

	UNLOAD_SPECIAL_CHARACTER 1 // unloading zero
	UNLOAD_SPECIAL_CHARACTER 2 // unloading zero
	UNLOAD_SPECIAL_CHARACTER 3 // unloading zero




	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	SET_CHAR_COORDINATES scplayer -2028.0 177.0 28.2
	SET_CHAR_HEADING scplayer 267.0


	DELETE_CHAR char_truth_garag1
	DELETE_CHAR	char_mech_garag1[0]
	DELETE_CHAR	char_mech_garag1[1]
	DELETE_CHAR	char_mech_garag1[2]
	DELETE_CHAR	char_mech_garag1[3]
	DELETE_CHAR pony_driver_garage1
	DELETE_CAR car_mech2_garag1	 
	DELETE_CAR car_player_garag1 



	 
	LOAD_CUTSCENE GARAG1C
	SET_AREA_VISIBLE 1 
	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	WAIT 1500 
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
	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	play_audio_flag_garage1 = 0

	flag_go_to_hub_garag1 = 0
	flag_mission_garag1_passed = 1

RETURN

///////////////////////////////////// GOSUBS /////////////////////////////////////

////////////////////////////////////////////////
//        going_to_destinations_garage1:
////////////////////////////////////////////////

going_to_destinations_garage1:
	IF NOT IS_CAR_DEAD car_player_garag1 
		WHILE NOT flag_mech_summoned_garage1 = 1
				
			WAIT 0

			GOSUB checking_buddies_garage1
			IF flag_mission_garag1_failed = 1 
				RETURN
			ENDIF

			// LOD stuff
			IF flag_go_to_mechanic1_garag1 = 1	 
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 200.0 200.0 200.0 FALSE
					IF flag_lod_close_to_desstination = 0																											  
					  	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL03 coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] char_mech_garag1[index_garag1]
					  	SET_CHAR_NEVER_TARGETTED char_mech_garag1[index_garag1] TRUE
					  	SET_CHAR_COLLISION char_mech_garag1[index_garag1] FALSE
					  	SET_CHAR_HEADING char_mech_garag1[index_garag1] 228.0
						SET_CHAR_PROOFS char_mech_garag1[index_garag1] FALSE FALSE FALSE TRUE FALSE
						SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR char_mech_garag1[index_garag1] FALSE
					  	SET_CHAR_RELATIONSHIP char_mech_garag1[index_garag1] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
						SET_CHAR_DECISION_MAKER char_mech_garag1[index_garag1] dm_buddies_garage1 


						SET_CHAR_NEVER_TARGETTED char_mech_garag1[index_garag1] TRUE
					  	CREATE_CAR towtruck -1676.0 440.0 6.7 car_mech1_garag1 
						LOCK_CAR_DOORS car_mech1_garag1 CARLOCK_LOCKED
				   //		FORCE_CAR_LIGHTS car_mech1_garag1 FORCE_CAR_LIGHTS_ON
						SET_CAR_ENGINE_ON 	car_mech1_garag1 TRUE
						SET_CAR_LIGHTS_ON car_mech1_garag1 TRUE

					  	SET_CAR_HEADING car_mech1_garag1 313.0 
						SET_CAR_HEALTH car_mech1_garag1 500 
						FREEZE_CAR_POSITION car_mech1_garag1 TRUE
						POP_CAR_DOOR car_mech1_garag1 BONNET FALSE
					   //	TASK_PLAY_ANIM char_mech_garag1[index_garag1] Fixn_Car_Loop CAR 4.0 TRUE FALSE FALSE TRUE 0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE char_mech_garag1[index_garag1] Fixn_Car_Loop CAR 4.0 TRUE FALSE FALSE TRUE 0
						flag_lod_close_to_desstination = 1
					ENDIF
				ELSE
					IF flag_lod_close_to_desstination = 1
						IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1] 
							DELETE_CHAR char_mech_garag1[index_garag1]
						ENDIF

						IF NOT IS_CAR_DEAD car_mech1_garag1
							DELETE_CAR car_mech1_garag1
						ENDIF 
						flag_lod_close_to_desstination = 0
					ENDIF
				ENDIF
			ENDIF

			IF flag_go_to_mechanic2_garag1 = 1	 
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 200.0 200.0 200.0 FALSE
					IF flag_lod_close_to_desstination = 0
						CREATE_CAR HOTDOG -2238.8884 541.5267 34.3750 car_mech2_garag1 
						LOCK_CAR_DOORS car_mech2_garag1 CARLOCK_LOCKED
					 //	FORCE_CAR_LIGHTS car_mech2_garag1 FORCE_CAR_LIGHTS_ON
						SET_CAR_ENGINE_ON 	car_mech2_garag1 TRUE
						SET_CAR_LIGHTS_ON car_mech2_garag1 TRUE

						SET_CAR_HEADING car_mech2_garag1 0.0
						FREEZE_CAR_POSITION car_mech2_garag1 TRUE

					   //	ADD_STUCK_CAR_CHECK_WITH_WARP car_mech2_garag1 1.0 2000 TRUE TRUE TRUE 1

					   	CREATE_CHAR PEDTYPE_CIVMALE SPECIAL04 coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] char_mech_garag1[index_garag1]
					  	SET_CHAR_NEVER_TARGETTED char_mech_garag1[index_garag1] TRUE
					  	SET_CHAR_HEADING char_mech_garag1[index_garag1] 270.0 

						CREATE_OBJECT cigar coord_mech_garage1_x[index_garag1] coord_mech_garage1_y[index_garag1] coord_mech_garage1_z[index_garag1] dwaynes_blunt
						TASK_PICK_UP_OBJECT char_mech_garag1[index_garag1] dwaynes_blunt 0.04 0.1 0.05 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
  					//	TASK_PICK_UP_OBJECT ryder ryders_ciggy 0.04 0.1 -0.02 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL -1
					  //	SET_CHAR_COLLISION char_mech_garag1[index_garag1] FALSE
						FREEZE_CHAR_POSITION char_mech_garag1[index_garag1] TRUE
					  	SET_CHAR_RELATIONSHIP char_mech_garag1[index_garag1] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
						SET_CHAR_DECISION_MAKER char_mech_garag1[index_garag1] dm_buddies_garage1 
						SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR char_mech_garag1[index_garag1] FALSE

					 	TASK_PLAY_ANIM char_mech_garag1[index_garag1] M_smklean_loop SMOKING 4.0 TRUE FALSE FALSE TRUE 0
						flag_lod_close_to_desstination = 1
					ENDIF

					// check hotdog van
			
				ELSE
					IF flag_lod_close_to_desstination = 1
						IF NOT IS_CHAR_DEAD char_mech_garag1[index_garag1] 
							DELETE_CHAR char_mech_garag1[index_garag1]
						ENDIF

						IF NOT IS_CAR_DEAD car_mech2_garag1
							DELETE_CAR car_mech2_garag1
						ENDIF 

						IF DOES_OBJECT_EXIST dwaynes_blunt
							DELETE_OBJECT dwaynes_blunt
						ENDIF 

						flag_lod_close_to_desstination = 0
					ENDIF
				ENDIF
			ENDIF


			IF flag_go_to_mechanic3_garag1 = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 200.0 200.0 200.0 FALSE
					IF flag_lod_close_to_desstination = 0

						CREATE_OBJECT parkbench1 -2244.55 133.91 34.4 object_zeros_bench

						SET_OBJECT_HEADING object_zeros_bench 0.0 
						FREEZE_OBJECT_POSITION object_zeros_bench TRUE 
						CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 -2244.7 133.91 33.82 char_mech_garag1[2]
						SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR char_mech_garag1[2] FALSE

						SET_CHAR_NEVER_TARGETTED char_mech_garag1[2] TRUE
					   	TASK_PLAY_ANIM_NON_INTERRUPTABLE char_mech_garag1[2] CRIB_Console_Loop CRIB 4.0 TRUE FALSE FALSE TRUE 0
						SET_CHAR_COLLISION char_mech_garag1[2] FALSE 

					  	SET_CHAR_RELATIONSHIP char_mech_garag1[2] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
						SET_CHAR_DECISION_MAKER char_mech_garag1[2] dm_buddies_garage1 

					   	SET_CHAR_HEADING char_mech_garag1[2] 280.0 

						SET_CHAR_ONLY_DAMAGED_BY_PLAYER char_mech_garag1[2] TRUE
						GIVE_WEAPON_TO_CHAR char_mech_garag1[2] WEAPONTYPE_REMOTE_SATCHEL_CHARGE 10
						SET_CHAR_NEVER_TARGETTED char_mech_garag1[2] TRUE
						GIVE_WEAPON_TO_CHAR char_mech_garag1[2] WEAPONTYPE_DETONATOR 1 
					  	SET_CHAR_RELATIONSHIP char_mech_garag1[2] ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_PLAYER1	
						SET_CHAR_DECISION_MAKER char_mech_garag1[2] dm_buddies_garage1 
						SET_CURRENT_CHAR_WEAPON char_mech_garag1[2] WEAPONTYPE_DETONATOR


						CREATE_CAR rcraider -2250.33 139.91 44.66 rc_heli_garage1
						SET_CAR_PROOFS rc_heli_garage1 TRUE TRUE TRUE TRUE TRUE  
						SET_CAR_CRUISE_SPEED rc_heli_garage1 30.0
						SET_HELI_BLADES_FULL_SPEED rc_heli_garage1
						HELI_GOTO_COORDS rc_heli_garage1 -2258.7 132.54 37.72 5.0 35.0

						
						flag_lod_close_to_desstination = 1
					ENDIF
				ELSE
					IF flag_lod_close_to_desstination = 1
						IF DOES_OBJECT_EXIST object_zeros_bench
							DELETE_OBJECT object_zeros_bench
						ENDIF 
						
						IF NOT IS_CHAR_DEAD char_mech_garag1[2]
							DELETE_CHAR char_mech_garag1[2]
						ENDIF

						IF NOT IS_CAR_DEAD rc_heli_garage1
							DELETE_CAR rc_heli_garage1
						ENDIF

						flag_lod_close_to_desstination = 0
					ENDIF
				ENDIF
			ENDIF


			IF flag_go_to_hub_garag1 = 1	 
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer coords_hub_garag1_x coords_hub_garag1_y coords_hub_garag1_z 300.0 300.0 300.0 FALSE
					IF flag_lod_close_to_desstination = 0
						CREATE_CAR HOTDOG -2026.0 169.0 28.2 car_mech2_garag1 
						LOCK_CAR_DOORS car_mech2_garag1 CARLOCK_LOCKED

						SET_CAR_HEADING car_mech2_garag1 120.0

						flag_lod_close_to_desstination = 1
					ENDIF

			
				ELSE
					IF flag_lod_close_to_desstination = 1

						IF NOT IS_CAR_DEAD car_mech2_garag1
							DELETE_CAR car_mech2_garag1
						ENDIF 
						flag_lod_close_to_desstination = 0
					ENDIF
				ENDIF
			ENDIF





			IF NOT IS_CAR_DEAD car_player_garag1
			   ///////////////////////////////////////////////////////////////////
			   // The honking horns pickups
			   ///////////////////////////////////////////////////////////////////
				IF flag_go_to_mechanic1_garag1 = 1
				OR flag_go_to_mechanic2_garag1 = 1
				OR flag_go_to_mechanic3_garag1 = 1

					IF LOCATE_STOPPED_CAR_3D car_player_garag1 coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 4.0 4.0 4.0 TRUE
						IF LOCATE_STOPPED_CAR_3D car_player_garag1 coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] 2.5 2.5 2.5 FALSE

							IF flag_help_text_garage1 = 0
								PRINT_HELP GAR1_23
								flag_help_text_garage1 = 1
							ENDIF

							IF IS_PLAYER_PRESSING_HORN player1
								IF IS_CHAR_SITTING_IN_ANY_CAR scplayer
									SET_CHAR_CANT_BE_DRAGGED_OUT scplayer TRUE

									IF NOT IS_CAR_DEAD car_player_garag1
										LOCK_CAR_DOORS car_player_garag1 CARLOCK_LOCKED_PLAYER_INSIDE
									ENDIF

									WAIT 500
									SET_PLAYER_CONTROL player1 OFF
									SWITCH_WIDESCREEN ON
									CLEAR_MISSION_AUDIO 1

									CLEAR_HELP
									
									WAIT 1000
								
							//		SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
							   //		SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 


									SET_CAR_DENSITY_MULTIPLIER 0.0
									SET_PED_DENSITY_MULTIPLIER 0.0

								
									IF NOT IS_CAR_DEAD car_player_garag1

										IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
											WARP_CHAR_INTO_CAR scplayer car_player_garag1 
										ENDIF
									ENDIF

									IF NOT IS_CAR_DEAD car_player_garag1
										LOCK_CAR_DOORS car_player_garag1 CARLOCK_UNLOCKED
									ENDIF
									flag_mech_summoned_garage1 = 1
								ENDIF
							ENDIF

						ELSE
							CLEAR_HELP
							flag_help_text_garage1 = 0	
						ENDIF

					ENDIF

				ENDIF

				///////////////////////////////////////////////////////////////////
				// going to the hospital
				///////////////////////////////////////////////////////////////////
				IF flag_go_to_hospital_garag1 = 1
				   //	IF LOCATE_STOPPED_CAR_3D car_player_garag1 coords_hospital_x coords_hospital_y coords_hospital_z 4.0 4.0 4.0 TRUE
					IF LOCATE_CHAR_IN_CAR_3D scplayer coords_hospital_x coords_hospital_y coords_hospital_z 4.0 4.0 4.0 TRUE
						flag_mech_summoned_garage1 = 1
						SET_PLAYER_CONTROL player1 OFF
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000

					ENDIF

				ENDIF	
				////////////////////////////////////////////////////////////////////

				///////////////////////////////////////////////////////////////////
				// going to the police station
				///////////////////////////////////////////////////////////////////
				IF flag_go_to_police_station_garag1 = 1
				   //	IF LOCATE_STOPPED_CAR_3D car_player_garag1 coords_police_x coords_police_y coords_police_z 4.0 4.0 4.0 TRUE
					IF LOCATE_CHAR_IN_CAR_3D scplayer coords_police_x coords_police_y coords_police_z 4.0 4.0 4.0 TRUE
						flag_mech_summoned_garage1 = 1
						SET_PLAYER_CONTROL player1 OFF
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
						TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
					ENDIF


				ENDIF	
				////////////////////////////////////////////////////////////////////




				///////////////////////////////////////////////////////////////////
				// Stopping at the hub (mission end)
				///////////////////////////////////////////////////////////////////
				IF flag_go_to_hub_garag1 = 1
					IF NOT IS_CAR_DEAD car_player_garag1
						IF flag_garage_doors = 0

							GET_CAR_HEADING car_player_garag1 heading_car_player_garage1

							//	IF LOCATE_CHAR_IN_CAR_3D scplayer -2034.59 180.23 28.43 4.0 4.0 4.0 TRUE
							   
							IF LOCATE_CAR_3D car_player_garag1 -2034.59 180.23 28.43 4.0 4.0 4.0 TRUE

								IF heading_car_player_garage1 > 0.0
								AND heading_car_player_garage1 < 180.0 

									OPEN_GARAGE hbgdsfs
									flag_garage_doors = 1
									SET_PLAYER_CONTROL player1 OFF
									STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
									TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
									flag_mech_summoned_garage1 = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF	
				////////////////////////////////////////////////////////////////////

				// BAnter and text
				// Starting the journey with truth and player
				IF flag_go_to_mechanic1_garag1 = 1
					IF flag_cutscene_garage1 >= 0
					AND flag_cutscene_garage1 <= 7
						GOSUB load_and_play_audio_garage1
					ENDIF
				
					//	IS char in vietnam area
					IF IS_CHAR_IN_ZONE scplayer EASB
						IF flag_cutscene_garage1 = 8
							GOSUB load_and_play_audio_garage1
						ENDIF
					ENDIF 

					IF flag_cutscene_garage1 = 9
					OR flag_cutscene_garage1 = 10 
						GOSUB load_and_play_audio_garage1
					ENDIF 
				ENDIF

				// going to the hospital
				IF flag_go_to_hospital_garag1 = 1
					IF flag_cutscene_garage1 >= 0
					AND flag_cutscene_garage1 <= 6
						GOSUB load_and_play_audio_garage1
					ENDIF
				ENDIF


				// Picking up 2nd mech
				IF flag_go_to_mechanic2_garag1 = 1

					IF flag_cutscene_garage1 >= 0
					AND flag_cutscene_garage1 <= 6
 						GOSUB load_and_play_audio_garage1
					ENDIF
						
				ENDIF


				// Picking up zero 
				IF flag_go_to_mechanic3_garag1 = 1

					IF flag_cutscene_garage1 >= 0
					AND flag_cutscene_garage1 <= 9
						GOSUB load_and_play_audio_garage1
					ENDIF
				ENDIF


				IF flag_go_to_hub_garag1 = 1
					IF flag_cutscene_garage1 >= 0
					AND flag_cutscene_garage1 <= 7
						GOSUB load_and_play_audio_garage1
					ENDIF

				ENDIF		

		 


			


				// checking if player is in car
				IF NOT IS_CAR_DEAD car_player_garag1 
					IF NOT IS_CHAR_IN_CAR scplayer car_player_garag1
						REMOVE_BLIP blip_mech_garag1[index_garag1]

						REMOVE_BLIP blip_hospital 
						REMOVE_BLIP blip_police

						ADD_BLIP_FOR_CAR car_player_garag1 blip_players_car_garag1 
  						SET_BLIP_AS_FRIENDLY blip_players_car_garag1 TRUE 
							
						CLEAR_MISSION_AUDIO 1
						LOAD_MISSION_AUDIO 1 SOUND_TRUX_AC
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0
						ENDWHILE  

						PRINT_NOW ( TRUX_AC ) 10000 1		//	Dont try and dump me CARL!	
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT TRUX_AC 				   






						//PRINT_NOW ( GAR1_10 ) 5000 1 //get back in here
						IF NOT IS_CAR_DEAD car_player_garag1
							WHILE NOT IS_CHAR_IN_CAR scplayer car_player_garag1
								WAIT 0
								IF flag_player_out_of_car_garag1 = 0
									flag_player_out_of_car_garag1 = 1
								ENDIF

								IF NOT IS_CAR_DEAD car_player_garag1

								ELSE 
									PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!

									flag_go_to_mechanic1_garag1 = 0
									flag_go_to_mechanic2_garag1 = 0
									flag_go_to_mechanic3_garag1 = 0
									flag_go_to_mechanic4_garag1 = 0
									flag_go_to_hub_garag1 = 0



									flag_mission_garag1_failed = 1
									RETURN
								ENDIF


								IF IS_CAR_ON_FIRE car_player_garag1			  // MAKE CAR EXPLODE IF ON FIRE 
									EXPLODE_CAR car_player_garag1

									PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!

									flag_go_to_mechanic1_garag1 = 0
									flag_go_to_mechanic2_garag1 = 0
									flag_go_to_mechanic3_garag1 = 0
									flag_go_to_mechanic4_garag1 = 0
									flag_go_to_hub_garag1 = 0



									flag_mission_garag1_failed = 1
									RETURN


								ENDIF 
   

								GOSUB checking_buddies_garage1
								
								IF flag_mission_garag1_failed = 1 
									RETURN
								ENDIF



							ENDWHILE
						ENDIF
						flag_player_out_of_car_garag1 = 0
						REMOVE_BLIP blip_players_car_garag1 
					  //	REMOVE_BLIP blip_mech_garag1[index_garag1]
 

						IF flag_go_to_mechanic1_garag1 = 1
						OR flag_go_to_mechanic2_garag1 = 1	
						OR flag_go_to_mechanic3_garag1 = 1
							ADD_BLIP_FOR_COORD coords_garage_garag1_x[index_garag1] coords_garage_garag1_y[index_garag1] coords_garage_garag1_z[index_garag1] blip_mech_garag1[index_garag1]
							SET_COORD_BLIP_APPEARANCE blip_mech_garag1[index_garag1] COORD_BLIP_APPEARANCE_FRIEND
						ENDIF


						IF flag_go_to_hospital_garag1 = 1
							ADD_SPRITE_BLIP_FOR_COORD coords_hospital_x coords_hospital_y coords_hospital_z RADAR_SPRITE_HOSPITAL blip_hospital
						ENDIF


						IF flag_go_to_police_station_garag1 = 1
							ADD_SPRITE_BLIP_FOR_COORD coords_police_x coords_police_y coords_police_z RADAR_SPRITE_POLICE blip_police

						 /*	ADD_BLIP_FOR_COORD coords_hub_garag1_x coords_hub_garag1_y coords_hub_garag1_z blip_mech_garag1[index_garag1]
							SET_COORD_BLIP_APPEARANCE blip_mech_garag1[index_garag1] COORD_BLIP_APPEARANCE_FRIEND  */

						ENDIF

						IF flag_go_to_hub_garag1 = 1
						  //	GOSUB go_to_hub_garag1

							ADD_BLIP_FOR_COORD coords_hub_garag1_x coords_hub_garag1_y coords_hub_garag1_z blip_mech_garag1[index_garag1]	
						ENDIF		



					ENDIF // if player is not in car 
				ENDIF


			ELSE
				PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!
				flag_go_to_mechanic1_garag1 = 0
				flag_go_to_mechanic2_garag1 = 0
				flag_go_to_mechanic3_garag1 = 0
				flag_go_to_mechanic4_garag1 = 0
				flag_go_to_hub_garag1 = 0 


				flag_mission_garag1_failed = 1
				RETURN
			ENDIF

		ENDWHILE

	ELSE 
		PRINT_NOW (GAR1_07) 5000 1 //the cars blown up!

		flag_go_to_mechanic1_garag1 = 0
		flag_go_to_mechanic2_garag1 = 0
		flag_go_to_mechanic3_garag1 = 0
		flag_go_to_mechanic4_garag1 = 0

		flag_mission_garag1_failed = 1
		RETURN


		
	ENDIF


	flag_mech_summoned_garage1 = 0
	REMOVE_BLIP blip_mech_garag1[index_garag1]
	flag_help_text_garage1 = 0
	flag_lod_close_to_desstination = 0
	flag_cutscene_garage1 = 0
	// make char fix a car?
	CLEAR_HELP
RETURN




checking_buddies_garage1:

//////////////////////////////////////////////////////////
// If buddies are out of the car, make them run back in.
//////////////////////////////////////////////////////////

	IF NOT IS_CAR_DEAD car_player_garag1


		IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer car_player_garag1 300.0 300.0 300.0 FALSE

			flag_go_to_hub_garag1 = 0
			flag_go_to_mechanic1_garag1	= 0
			flag_go_to_mechanic2_garag1	= 0
			flag_go_to_mechanic3_garag1	= 0
			flag_go_to_mechanic4_garag1	= 0
			flag_go_to_hub_garag1 = 0
			flag_mission_garag1_failed = 1 
			PRINT_NOW GAR1_18 5000 1 // You	 abandoned the car
		ENDIF



		// truth
		IF NOT IS_CHAR_DEAD char_truth_garag1	 
			IF NOT IS_CHAR_IN_CAR char_truth_garag1 car_player_garag1

				IF flag_is_truth_in_car = 0
					TASK_ENTER_CAR_AS_PASSENGER char_truth_garag1 car_player_garag1 -1 0
	
					flag_is_truth_in_car = 1
				ENDIF

				IF flag_is_truth_in_car = 1
					IF NOT LOCATE_CHAR_ON_FOOT_CHAR_3D char_truth_garag1 scplayer 300.0 300.0 300.0 FALSE

						flag_go_to_hub_garag1 = 0
						flag_go_to_mechanic1_garag1	= 0
						flag_go_to_mechanic2_garag1	= 0
						flag_go_to_mechanic3_garag1	= 0
					 	flag_go_to_mechanic4_garag1	= 0
						flag_go_to_hub_garag1 = 0
						flag_mission_garag1_failed = 1 
						PRINT_NOW GAR1_19 5000 1 // You	 abandoned truth
					ENDIF
				ENDIF
						

			ELSE

				IF flag_is_truth_in_car = 1
					flag_is_truth_in_car = 0
				ENDIF

			ENDIF

		ENDIF

		// mechanics 1
		IF NOT flag_go_to_mechanic1_garag1 = 1
			IF NOT IS_CHAR_DEAD char_mech_garag1[0]	 
				IF NOT IS_CHAR_IN_CAR char_mech_garag1[0] car_player_garag1

					IF flag_is_mechanic1_in_car = 0
						TASK_ENTER_CAR_AS_PASSENGER char_mech_garag1[0] car_player_garag1 -1 2	

						flag_is_mechanic1_in_car = 1
					ENDIF

					IF flag_is_mechanic1_in_car = 1
						IF NOT LOCATE_CHAR_ON_FOOT_CHAR_3D char_mech_garag1[0] scplayer 300.0 300.0 300.0 FALSE

							flag_go_to_hub_garag1 = 0
							flag_go_to_mechanic1_garag1	= 0
							flag_go_to_mechanic2_garag1	= 0
							flag_go_to_mechanic3_garag1	= 0
						 	flag_go_to_mechanic4_garag1	= 0
							flag_go_to_hub_garag1 = 0
							flag_mission_garag1_failed = 1 
							PRINT_NOW GAR1_20 5000 1 // You	 abandoned jethro
						ENDIF
					ENDIF
							

				ELSE

					IF flag_is_mechanic1_in_car = 1
						flag_is_mechanic1_in_car = 0
					ENDIF

				ENDIF

			ENDIF
		ENDIF

		// mechanics 2
		IF NOT flag_go_to_mechanic2_garag1 = 1

			IF NOT IS_CHAR_DEAD char_mech_garag1[1]	 
				IF NOT IS_CHAR_IN_CAR char_mech_garag1[1] car_player_garag1

					IF flag_is_mechanic2_in_car = 0
						TASK_ENTER_CAR_AS_PASSENGER char_mech_garag1[1] car_player_garag1 -1 0	

					  //	SET_CHAR_CANT_BE_DRAGGED_OUT char_mech_garag1[1] TRUE
					  //	SET_CHAR_STAY_IN_CAR_WHEN_JACKED char_mech_garag1[1] TRUE

						flag_is_mechanic2_in_car = 1
					ENDIF

					IF NOT flag_is_mechanic2_in_car = 1
						IF NOT LOCATE_CHAR_ON_FOOT_CHAR_3D char_mech_garag1[1] scplayer 300.0 300.0 300.0 FALSE

							flag_go_to_hub_garag1 = 0
							flag_go_to_mechanic1_garag1	= 0
							flag_go_to_mechanic2_garag1	= 0
							flag_go_to_mechanic3_garag1	= 0
						 	flag_go_to_mechanic4_garag1	= 0
							flag_go_to_hub_garag1 = 0
							flag_mission_garag1_failed = 1 
							PRINT_NOW GAR1_21 5000 1 // You	 abandoned Dwayne
						ENDIF
					ENDIF
							

				ELSE

					

					IF flag_is_mechanic2_in_car = 1
						flag_is_mechanic2_in_car = 0
					ENDIF

				ENDIF

			ENDIF	   
		ENDIF


	ENDIF






// Checking if characters are deed






	IF IS_CHAR_DEAD char_truth_garag1	 
		flag_go_to_hub_garag1 = 0
		flag_go_to_mechanic1_garag1	= 0
		flag_go_to_mechanic2_garag1	= 0
		flag_go_to_mechanic3_garag1	= 0
	 	flag_go_to_mechanic4_garag1	= 0
		flag_go_to_hub_garag1 = 0
		flag_mission_garag1_failed = 1 
		PRINT_NOW GAR1_17 5000 1 // Truth died
		RETURN
	ENDIF



	
	IF flag_go_to_mechanic1_garag1 = 1
		IF flag_lod_close_to_desstination = 1

			IF IS_CAR_DEAD car_mech1_garag1
				flag_go_to_hub_garag1 = 0
				flag_go_to_mechanic1_garag1	= 0
				flag_go_to_mechanic2_garag1	= 0
				flag_go_to_mechanic3_garag1	= 0
			 	flag_go_to_mechanic4_garag1	= 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_failed = 1 
				PRINT_NOW GAR1_14 5000 1 // jethros dead

				RETURN
			ENDIF


			IF IS_CHAR_DEAD char_mech_garag1[0]
				flag_go_to_hub_garag1 = 0
				flag_go_to_mechanic1_garag1	= 0
				flag_go_to_mechanic2_garag1	= 0
				flag_go_to_mechanic3_garag1	= 0
			 	flag_go_to_mechanic4_garag1	= 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_failed = 1 
				PRINT_NOW GAR1_14 5000 1 // jethros dead
				RETURN
			ENDIF
		ENDIF
	ENDIF	
							

	IF flag_go_to_mechanic2_garag1 = 1
  //	OR flag_go_to_hospital_garag1 = 1
  //	OR flag_go_to_police_station_garag1 = 1

		IF IS_CHAR_DEAD char_mech_garag1[0]
			flag_go_to_hub_garag1 = 0
			flag_go_to_mechanic1_garag1	= 0
			flag_go_to_mechanic2_garag1	= 0
			flag_go_to_mechanic3_garag1	= 0
		 	flag_go_to_mechanic4_garag1	= 0
			flag_go_to_hub_garag1 = 0
			flag_mission_garag1_failed = 1 
			PRINT_NOW GAR1_14 5000 1 // jethros dead
			RETURN
		ENDIF



		IF flag_lod_close_to_desstination = 1
			IF IS_CHAR_DEAD char_mech_garag1[1]
				flag_go_to_hub_garag1 = 0
				flag_go_to_mechanic1_garag1	= 0
				flag_go_to_mechanic2_garag1	= 0
				flag_go_to_mechanic3_garag1	= 0
			 	flag_go_to_mechanic4_garag1	= 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_failed = 1 
				PRINT_NOW GAR1_15 5000 1 // dwaign dead
				RETURN
			ENDIF

			IF IS_CAR_DEAD car_mech2_garag1
				flag_go_to_hub_garag1 = 0
				flag_go_to_mechanic1_garag1	= 0
				flag_go_to_mechanic2_garag1	= 0
				flag_go_to_mechanic3_garag1	= 0
			 	flag_go_to_mechanic4_garag1	= 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_failed = 1 
				PRINT_NOW GAR1_15 5000 1 // dwaign dead
				RETURN
			ENDIF
					 

		ENDIF
	ENDIF
	
			

	IF flag_go_to_mechanic3_garag1 = 1
		IF IS_CHAR_DEAD char_mech_garag1[0]
			flag_go_to_hub_garag1 = 0
			flag_go_to_mechanic1_garag1	= 0
			flag_go_to_mechanic2_garag1	= 0
			flag_go_to_mechanic3_garag1	= 0
		 	flag_go_to_mechanic4_garag1	= 0
			flag_go_to_hub_garag1 = 0
			flag_mission_garag1_failed = 1 
			PRINT_NOW GAR1_14 5000 1 // jethros dead
			RETURN
		ENDIF

		IF flag_lod_close_to_desstination = 1
			IF IS_CHAR_DEAD char_mech_garag1[2]
				flag_go_to_hub_garag1 = 0
				flag_go_to_mechanic1_garag1	= 0
				flag_go_to_mechanic2_garag1	= 0
				flag_go_to_mechanic3_garag1	= 0
			 	flag_go_to_mechanic4_garag1	= 0
				flag_go_to_hub_garag1 = 0
				flag_mission_garag1_failed = 1
				PRINT_NOW GAR1_16 5000 1 // zeros dead
				RETURN
			ENDIF

			IF NOT IS_CHAR_DEAD  char_mech_garag1[2]
				IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR char_mech_garag1[2] scplayer
					IF NOT IS_CHAR_IN_ANY_CAR char_mech_garag1[2]
						SET_CHAR_COLLISION char_mech_garag1[2] TRUE
					ENDIF
					SET_CHAR_HEALTH  char_mech_garag1[2] 0

					flag_mission_garag1_failed = 1
					PRINT_NOW GAR1_16 5000 1 // zeros dead
					RETURN

				ENDIF

				IF DOES_OBJECT_EXIST object_zeros_bench

					IF HAS_OBJECT_BEEN_DAMAGED object_zeros_bench
						IF NOT IS_CHAR_IN_ANY_CAR char_mech_garag1[2]
							SET_CHAR_COLLISION char_mech_garag1[2] TRUE
						ENDIF
						SET_CHAR_HEALTH  char_mech_garag1[2] 0

						flag_mission_garag1_failed = 1
						PRINT_NOW GAR1_16 5000 1 // zeros dead
						RETURN

					ENDIF



				ENDIF


			ENDIF

		ENDIF
	ENDIF

	IF flag_go_to_hub_garag1 = 1

		IF IS_CHAR_DEAD char_mech_garag1[0]
			flag_go_to_hub_garag1 = 0
			flag_go_to_mechanic1_garag1	= 0
			flag_go_to_mechanic2_garag1	= 0
			flag_go_to_mechanic3_garag1	= 0
		 	flag_go_to_mechanic4_garag1	= 0
			flag_go_to_hub_garag1 = 0
			flag_mission_garag1_failed = 1 
			PRINT_NOW GAR1_14 5000 1 // jethros dead
			RETURN
		ENDIF

		IF IS_CHAR_DEAD char_mech_garag1[2]
			flag_go_to_hub_garag1 = 0
			flag_go_to_mechanic1_garag1	= 0
			flag_go_to_mechanic2_garag1	= 0
			flag_go_to_mechanic3_garag1	= 0
		 	flag_go_to_mechanic4_garag1	= 0
			flag_go_to_hub_garag1 = 0
			flag_mission_garag1_failed = 1
			PRINT_NOW GAR1_16 5000 1 // zeros dead
			RETURN
		ENDIF



	ENDIF
	
	
  	IF flag_go_to_hospital_garag1 = 1
	OR flag_go_to_police_station_garag1 = 1
		IF IS_CHAR_DEAD char_mech_garag1[0]
			flag_go_to_hub_garag1 = 0
			flag_go_to_mechanic1_garag1	= 0
			flag_go_to_mechanic2_garag1	= 0
			flag_go_to_mechanic3_garag1	= 0
		 	flag_go_to_mechanic4_garag1	= 0
			flag_go_to_hub_garag1 = 0
			flag_mission_garag1_failed = 1 
			PRINT_NOW GAR1_14 5000 1 // jethros dead
			RETURN
		ENDIF
	ENDIF			

   
RETURN


///////////////////////////////////////
// AUDIO
///////////////////////////////////////

load_and_play_audio_garage1:

	IF play_audio_flag_garage1 = 0
		CLEAR_MISSION_AUDIO 1

		LOAD_MISSION_AUDIO 1 garage1_audio[index_dialogue_garage1]
		play_audio_flag_garage1 = 1
	ENDIF

	IF play_audio_flag_garage1 = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			IF NOT IS_CHAR_DEAD char_who_is_talking[index_dialogue_garage1]
				START_CHAR_FACIAL_TALK char_who_is_talking[index_dialogue_garage1] 10000
			ENDIF
			
		    
			PLAY_MISSION_AUDIO 1
			PRINT_NOW $garage1_text[index_dialogue_garage1] 10000 1

			// adding blips during conversations//////////////////////
			
			
			////////////////////////////////////////////////////////////

			play_audio_flag_garage1 = 2
		ENDIF
	ENDIF



	IF play_audio_flag_garage1 = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
		   //	temp_int = c3_counter - 1

			IF NOT IS_CHAR_DEAD char_who_is_talking[index_dialogue_garage1]
				STOP_CHAR_FACIAL_TALK char_who_is_talking[index_dialogue_garage1]
			ENDIF
			

			TIMERA = 0
			play_audio_flag_garage1 = 0
			CLEAR_THIS_PRINT $garage1_text[index_dialogue_garage1]
			index_dialogue_garage1++
			flag_cutscene_garage1++
		ENDIF
	ENDIF


RETURN


cutscene_audio:
	LOAD_MISSION_AUDIO 1 garage1_audio[index_dialogue_garage1]
	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		WAIT 0
	ENDWHILE  

	PRINT_NOW $garage1_text[index_dialogue_garage1] 10000 1

	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT $garage1_text[index_dialogue_garage1]
	index_dialogue_garage1++
	flag_cutscene_garage1++
RETURN





  



// *****************************
// MISSION PASSED               
// *****************************

mission_garag1_passed:





	//flag_pp1_mission1_passed = 1
 //	PRINT_WITH_NUMBER_BIG M_PASS 100 5000 1
 //	ADD_SCORE player1 100
   //	PRINT_BIG M_PASS 5000 1
   	PRINT_WITH_NUMBER_BIG( M_PASSD ) 0 5000 1 //"Mission Passed!"

	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1

  //	REGISTER_MISSION_PASSED ( GARAGE1 )

  	REGISTER_MISSION_PASSED ( GAR_1 )

	PLAYER_MADE_PROGRESS 1
	SET_INT_STAT PASSED_GARAGE1 1

	



   /*	START_NEW_SCRIPT steal_mission_loop
	REMOVE_BLIP steal_contact_blip
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT stealX stealY stealZ steal_blip_icon steal_contact_blip
						   */

	START_NEW_SCRIPT scrash_mission_loop
	//REMOVE_BLIP scrash_contact_blip
	//ADD_SPRITE_BLIP_FOR_CONTACT_POINT scrashX scrashY scrashZ crash_blip_icon scrash_contact_blip


	flag_garage_mission_counter ++

	//REMOVE_BLIP garage_contact_blip

 /*	START_NEW_SCRIPT trace_mission_loop
	REMOVE_BLIP dschool_contact_blip 
	ADD_SPRITE_BLIP_FOR_CONTACT_POINT -2031.2 -118.0 34.3 RADAR_SPRITE_SCHOOL dschool_contact_blip
	CHANGE_BLIP_DISPLAY dschool_contact_blip BLIP_ONLY            

		  */



 


RETURN
		
// *****************************
// mission failed
// *****************************

mission_garag1_failed:
	
	PRINT_BIG M_FAIL 5000 1
RETURN

// *****************************
// mission cleanup
// *****************************

mission_cleanup_garag1:
	CAMERA_RESET_NEW_SCRIPTABLES
	DISABLE_ALL_ENTRY_EXITS FALSE

	IF IS_PLAYER_PLAYING player1
   //		SHUT_CHAR_UP scplayer TRUE
		SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	ENDIF
	SWITCH_RANDOM_TRAINS ON
 /*	IF NOT IS_CHAR_DEAD scplayer
	//	IF flag_garage_doors = 0
	   //	IF flag_mission_garag1_failed = 1
			
			IF IS_CHAR_IN_AREA_3D scplayer -2056.86 150.93 23.16 -2039.3 182.7 32.87 FALSE
				SET_PLAYER_CONTROL player1 OFF

				DO_FADE 1000 FADE_OUT


				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -2028.0 177.0 28.2
					
				
				ENDIF 
				
				SET_CHAR_COORDINATES scplayer -2028.0 177.0 28.2
				SET_CHAR_HEADING scplayer 267.0
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER

				WAIT 0
				DO_FADE 1000 FADE_IN

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE
				SET_PLAYER_CONTROL player1 ON


			ENDIF
	   //	ENDIF
	
	ENDIF  */

	CLEAR_HELP
	index_garag1 = 0
	WHILE index_garag1 < 5
		REMOVE_BLIP blip_mech_garag1[index_garag1]
		index_garag1++
	ENDWHILE

	REMOVE_ANIMATION PARK
	REMOVE_ANIMATION CAR
	REMOVE_ANIMATION CRIB
	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION CAR_CHAT
	REMOVE_ANIMATION smoking


 //	REMOVE_BLIP blip_hub_garag1
	REMOVE_BLIP blip_players_car_garag1
	REMOVE_BLIP blip_hospital 
	REMOVE_BLIP blip_police

	IF NOT IS_CAR_DEAD car_player_garag1
		SET_CAR_HEALTH car_player_garag1 600
	ENDIF




	MARK_CAR_AS_NO_LONGER_NEEDED car_player_garag1 
	MARK_CAR_AS_NO_LONGER_NEEDED car_mech2_garag1
	MARK_CAR_AS_NO_LONGER_NEEDED car_mech1_garag1 


	MARK_CAR_AS_NO_LONGER_NEEDED car_mech2_garag1
	DELETE_CAR rc_heli_garage1

 //	MARK_CHAR_AS_NO_LONGER_NEEDED char_truth_garag1
//	MARK_CHAR_AS_NO_LONGER_NEEDED char_mech_garag1[0]
 //	MARK_CHAR_AS_NO_LONGER_NEEDED char_mech_garag1[1]
 //	MARK_CHAR_AS_NO_LONGER_NEEDED char_mech_garag1[2]
 //	MARK_CHAR_AS_NO_LONGER_NEEDED char_mech_garag1[3]


	REMOVE_CHAR_ELEGANTLY char_truth_garag1
	REMOVE_CHAR_ELEGANTLY char_mech_garag1[0]
	REMOVE_CHAR_ELEGANTLY char_mech_garag1[1]
	REMOVE_CHAR_ELEGANTLY char_mech_garag1[2]
	REMOVE_CHAR_ELEGANTLY char_mech_garag1[3]
	REMOVE_CHAR_ELEGANTLY pony_driver_garage1



//	SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
 //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0




	DELETE_OBJECT object_zeros_bench


   MARK_MODEL_AS_NO_LONGER_NEEDED bomb
   MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL
	MARK_MODEL_AS_NO_LONGER_NEEDED emperor
	MARK_MODEL_AS_NO_LONGER_NEEDED HOTDOG
	MARK_MODEL_AS_NO_LONGER_NEEDED pony
	MARK_MODEL_AS_NO_LONGER_NEEDED rcraider
	MARK_MODEL_AS_NO_LONGER_NEEDED towtruck

   	MARK_MODEL_AS_NO_LONGER_NEEDED HOTDOG
	MARK_MODEL_AS_NO_LONGER_NEEDED cigar


	DELETE_OBJECT dwaynes_blunt

	SET_SCRIPT_LIMIT_TO_GANG_SIZE 99


	RELEASE_WEATHER

	UNLOAD_SPECIAL_CHARACTER 1 // unloading zero
	UNLOAD_SPECIAL_CHARACTER 2 // unloading zero

	UNLOAD_SPECIAL_CHARACTER 3 // unloading zero
	UNLOAD_SPECIAL_CHARACTER 4 // unloading zero


	GET_GAME_TIMER timer_mobile_start

   	IF IS_GARAGE_OPEN hbgdsfs
		CLOSE_GARAGE hbgdsfs
	ENDIF 

   /*	IF LOCATE_CHAR_ANY_MEANS_3D scplayer 


	ENDIF  */

	flag_go_to_mechanic1_garag1 = 0
	flag_go_to_mechanic2_garag1 = 0
	flag_go_to_mechanic3_garag1	= 0
	flag_go_to_mechanic4_garag1 = 0
	flag_go_to_hub_garag1 = 0
	flag_go_to_hospital_garag1 = 0
	flag_go_to_police_station_garag1 = 0



	flag_cutscene_garage1 = 0

	flag_lod_close_to_desstination = 0

	flag_text_garage1 = 0

	flag_garage_doors = 0


	flag_mission_garag1_passed = 0
	flag_mission_garag1_failed = 0
	flag_player_out_of_car_garag1 = 0


	flag_loose_mech3_garag1 = 0
	flag_is_mech3_off_bike_garag1 = 0
	flag_is_zero_on_bike = 0

	flag_is_truth_in_car = 0
	flag_is_mechanic2_in_car = 0
	flag_is_mechanic1_in_car = 0

	flag_help_text_garage1 = 0

	STOP_FX_SYSTEM exhale_smoke_effect



	flag_player_on_mission = 0




	MISSION_HAS_FINISHED
RETURN


}	

   






			    