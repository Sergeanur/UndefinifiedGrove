MISSION_START
// ------------------------------------------------------------------------------------------------
// Catalina Mission 3: Gas Station Robbery
{

SCRIPT_NAME cat3

// Begin...
GOSUB mission_start_cat3

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_cat3
	ENDIF

GOSUB mission_cleanup_cat3

MISSION_END
// ------------------------------------------------------------------------------------------------
// Variables
// ---- Mission Pass Score
	LVAR_INT c3_pass 
// ---- Flags
	LVAR_INT c3_stage c3_in_car c3_cut
	LVAR_INT c3_help_text_flag
	LVAR_INT c3_cut_delay c3_end_brake

// ---- Blips
	LVAR_INT c3_gas_loc_blip 
	
// ---- Timers / Counters
	LVAR_INT c3_timer_diff c3_timer_end c3_timer_start 

// ---- Sequences
	LVAR_INT c3_clerk_cut_seq_1	c3_cat_seq_1
	LVAR_INT c3_task_status
	LVAR_INT c3_end_cut_1

// ---- Groups
	LVAR_INT c3_grouped
	 
// ---- Coords
	LVAR_FLOAT c3_gas_loc_x c3_gas_loc_y	c3_gas_loc_z
	LVAR_FLOAT c3_cat_gas_x c3_cat_gas_y c3_cat_gas_z

// ----  Cutscene Check
	LVAR_FLOAT c3_min_x c3_min_y c3_min_z c3_max_x c3_max_y c3_max_z 
 	LVAR_FLOAT c3_offset_x c3_offset_y c3_offset_z

// ---- Cameras
	LVAR_FLOAT c3_cam_x[10] c3_cam_y[10] c3_cam_z[10]
	LVAR_FLOAT c3_cam_look_x[10] c3_cam_look_y[10] c3_cam_look_z[10] 

// ---- Dialogue 
	LVAR_INT c3_random_dialogue c3_random_dialogue_last[2] c3_dialogue_playing
	LVAR_INT c3_text_timer_diff c3_text_timer_end c3_text_timer_start

	LVAR_TEXT_LABEL c3_text[56]
	LVAR_INT c3_audio_char
	LVAR_INT c3_audio[56] c3_counter c3_audio_playing c3_audio_slot	c3_alt_slot c3_ahead_counter
// ---- Misc
	VAR_INT c3_trailer_health_display c3_rand_escort
	LVAR_INT c3_trailer_health c3_trailer_health_old //c3_trailer_health

// ---- Entities
	LVAR_INT c3_player_car
// ------------------------------------------------------------------------------------------------
// -------- Create Characters
	c3_load_all:
		REQUEST_MODEL BMYPOL2
		REQUEST_MODEL WMOST
		
		REQUEST_MODEL PETRO
		REQUEST_MODEL PETROTR
		REQUEST_MODEL SABRE

		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_MODEL_LOADED BMYPOL2
		OR NOT HAS_MODEL_LOADED WMOST
	   	OR NOT HAS_MODEL_LOADED PETRO
		OR NOT HAS_MODEL_LOADED PETROTR
		OR NOT HAS_MODEL_LOADED SABRE
		WAIT 0
		ENDWHILE
	RETURN
// ------------ Catalina
	c3_cat_create:
	   	LVAR_INT catalina_blip
		REQUEST_ANIMATION MISC
		REQUEST_MODEL MICRO_UZI
		REQUEST_MODEL CHROMEGUN
		LOAD_MISSION_AUDIO 3 SOUND_TRAILER_HOOKUP
		LOAD_SPECIAL_CHARACTER 1 cat
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_ANIMATION_LOADED MISC
		OR NOT HAS_MODEL_LOADED MICRO_UZI
		OR NOT HAS_MODEL_LOADED CHROMEGUN
		OR NOT HAS_MISSION_AUDIO_LOADED	3
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
		WAIT 0
		ENDWHILE
		IF NOT DOES_CHAR_EXIST catalina
			CREATE_CHAR PEDTYPE_CIVFEMALE SPECIAL01 c3_cat_gas_x c3_cat_gas_y c3_cat_gas_z catalina
		ELSE
			DELETE_CHAR	catalina
			CREATE_CHAR PEDTYPE_CIVFEMALE SPECIAL01 c3_cat_gas_x c3_cat_gas_y c3_cat_gas_z catalina
		ENDIF
		IF NOT IS_CHAR_DEAD catalina
			GIVE_WEAPON_TO_CHAR catalina WEAPONTYPE_SHOTGUN 30000
		ENDIF
		//SET_CHAR_DROWNS_IN_WATER catalina TRUE
	RETURN

	ADD_BLIP_FOR_CHAR catalina catalina_blip
	ADD_BLIP_FOR_COORD c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z c3_gas_loc_blip

	c3_cat_delete:
		MARK_MODEL_AS_NO_LONGER_NEEDED desert_eagle
	RETURN
// ------------ Trucker
// ------------ Clerk
   	c3_clerk_create:
		LVAR_INT c3_clerk[2] c3_clerk_status
		LVAR_FLOAT c3_clerk_x[2] c3_clerk_y[2] c3_clerk_z[2] c3_clerk_h[2] c3_clerk_cruise

		c3_clerk_x[0] = 664.21 
		c3_clerk_y[0] = -569.54
		c3_clerk_z[0] = 15.37 
		c3_clerk_h[0] = 165.23
		CLEAR_AREA c3_clerk_x[0] c3_clerk_y[0] c3_clerk_z[0] 3.0 TRUE 
		CREATE_CHAR PEDTYPE_CIVMALE BMYPOL2 c3_clerk_x[0] c3_clerk_y[0] c3_clerk_z[0] c3_clerk[0]
		GIVE_WEAPON_TO_CHAR c3_clerk[0] WEAPONTYPE_MICRO_UZI 30000
		SET_CHAR_HEADING c3_clerk[0] c3_clerk_h[0]
		SET_CHAR_HEALTH c3_clerk[0] 2000
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE c3_clerk[0] FALSE
		SET_CHAR_PROOFS c3_clerk[0] FALSE TRUE TRUE FALSE TRUE

		c3_clerk_x[1] = 664.9634 
		c3_clerk_y[1] = -566.7849
		c3_clerk_z[1] = 15.3359 
		c3_clerk_h[1] = 139.1057 
		CLEAR_AREA c3_clerk_x[1] c3_clerk_y[1] c3_clerk_z[1] 3.0 TRUE 
		CREATE_CHAR PEDTYPE_CIVMALE WMOST c3_clerk_x[1] c3_clerk_y[1] c3_clerk_z[1] c3_clerk[1]
		SET_CHAR_HEADING c3_clerk[1] c3_clerk_h[1]
		SET_CHAR_HEALTH c3_clerk[1] 2000
		SET_CHAR_CAN_BE_SHOT_IN_VEHICLE c3_clerk[1] FALSE
		SET_CHAR_PROOFS c3_clerk[1] FALSE TRUE TRUE FALSE TRUE
	RETURN
	c3_clerk_delete:
		MARK_MODEL_AS_NO_LONGER_NEEDED BMYPOL2
	RETURN
// ---- Catalina's Friend
	LVAR_INT c3_buyer
	LVAR_FLOAT c3_buyer_x c3_buyer_y c3_buyer_z	c3_buyer_h
	c3_buyer_create:
		REQUEST_MODEL BMOST
		WHILE NOT HAS_MODEL_LOADED BMOST
		WAIT 0
		ENDWHILE
		c3_buyer_x = -78.4672 
		c3_buyer_y = -1134.7391
		c3_buyer_z = 0.0733 	
		c3_buyer_h = 326.2953
		CLEAR_AREA c3_buyer_x c3_buyer_y c3_buyer_z 3.0 TRUE 
		CREATE_CHAR PEDTYPE_CIVMALE BMOST c3_buyer_x c3_buyer_y c3_buyer_z c3_buyer
		SET_CHAR_HEADING c3_buyer c3_buyer_h
	RETURN
		CREATE_CHAR PEDTYPE_CIVMALE BMOST c3_buyer_x c3_buyer_y c3_buyer_z c3_audio_char
// ------------------------------------------------------------------------------------------------
// -------- Vehicles
// ------------ Truck
	c3_truck_create:
		LVAR_INT c3_truck c3_truck_blip 
		LVAR_FLOAT c3_truck_x c3_truck_y c3_truck_z c3_truck_h c3_truck_speed
		c3_truck_x = 657.49
		c3_truck_y = -585.13
		c3_truck_z = 16.36
		c3_truck_h = 170.66
		CLEAR_AREA c3_truck_x c3_truck_y c3_truck_z 15.0 TRUE
		CREATE_CAR PETRO c3_truck_x c3_truck_y c3_truck_z c3_truck
		SET_CAR_HEADING c3_truck c3_truck_h
		SET_CAN_BURST_CAR_TYRES c3_truck FALSE
		SET_CAR_PROOFS c3_truck TRUE FALSE FALSE FALSE FALSE
		SET_LOAD_COLLISION_FOR_CAR_FLAG c3_truck FALSE
   	RETURN

	CREATE_CAR LINERUN c3_truck_x c3_truck_y c3_truck_z c3_player_car

// ------------ Escape Car
	c3_trailer_create:
		LVAR_INT c3_trailer c3_trailer_blip
		LVAR_FLOAT c3_trailer_x c3_trailer_y c3_trailer_z c3_trailer_h
		c3_trailer_x = 667.49
		c3_trailer_y = -583.13
		c3_trailer_z = 16.36
		c3_trailer_h = 184.66
		CLEAR_AREA c3_trailer_x c3_trailer_y c3_trailer_z 15.0 TRUE
		CREATE_CAR PETROTR c3_trailer_x c3_trailer_y c3_trailer_z c3_trailer
		SET_CAR_HEADING c3_trailer c3_trailer_h
		SET_CAR_HEALTH c3_trailer 1250
		SET_CAR_PROOFS c3_trailer TRUE TRUE TRUE TRUE TRUE
		SET_CAN_BURST_CAR_TYRES c3_trailer FALSE
		SET_LOAD_COLLISION_FOR_CAR_FLAG c3_trailer FALSE
	RETURN

	ADD_BLIP_FOR_CAR c3_trailer c3_trailer_blip

// ------------ Chase Car
	c3_chase_create:
		LVAR_INT c3_chase c3_chase_blip
		LVAR_FLOAT c3_chase_x c3_chase_y c3_chase_z c3_chase_h
		c3_chase_x = 667.54
		c3_chase_y = -545.97
		c3_chase_z = 15.09
		c3_chase_h = 96.19
		CLEAR_AREA c3_chase_x c3_chase_y c3_chase_z 3.0 TRUE
		CREATE_CAR SABRE c3_chase_x c3_chase_y c3_chase_z c3_chase
		SET_CAR_HEADING c3_chase c3_chase_h
		SET_CAR_CRUISE_SPEED c3_chase 50.0
		SET_CAR_PROOFS c3_chase TRUE TRUE TRUE TRUE TRUE
		LOCK_CAR_DOORS c3_chase CARLOCK_LOCKED
		SET_CAN_BURST_CAR_TYRES c3_chase FALSE
		SET_LOAD_COLLISION_FOR_CAR_FLAG c3_chase FALSE
	RETURN
	c3_exp_car_create:
		LVAR_INT c3_exp_car c3_exp_car_blip
		LVAR_FLOAT c3_exp_car_x c3_exp_car_y c3_exp_car_z c3_exp_car_h
		REQUEST_MODEL SADLER
		WHILE NOT HAS_MODEL_LOADED SADLER
		WAIT 0
		ENDWHILE
		c3_exp_car_x = -87.9239 
		c3_exp_car_y = -1160.5974
		c3_exp_car_z = 1.1091 
		c3_exp_car_h = 170.4884 
		CLEAR_AREA c3_exp_car_x c3_exp_car_y c3_exp_car_z 3.0 TRUE
		CREATE_CAR SADLER c3_exp_car_x c3_exp_car_y c3_exp_car_z c3_exp_car
		SET_CAR_HEADING c3_exp_car c3_exp_car_h
		SET_CAR_PROOFS c3_exp_car TRUE TRUE TRUE TRUE TRUE
	RETURN
// ---- Catalina's Bike
	c3_cat_bike_create:
		LVAR_INT c3_cat_bike
		LVAR_FLOAT c3_cat_bike_x c3_cat_bike_y c3_cat_bike_z c3_cat_bike_h
		REQUEST_MODEL SANCHEZ
		REQUEST_CAR_RECORDING 189
		WHILE NOT HAS_MODEL_LOADED SANCHEZ
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 189
		WAIT 0
		ENDWHILE
		c3_cat_bike_x =	-88.1347 
		c3_cat_bike_y =	-1127.3966
		c3_cat_bike_z =	0.0847 
		c3_cat_bike_h =	348.4666
		CLEAR_AREA c3_cat_bike_x c3_cat_bike_y c3_cat_bike_z 3.0 TRUE
		CREATE_CAR SANCHEZ c3_cat_bike_x c3_cat_bike_y c3_cat_bike_z c3_cat_bike
		SET_CAR_HEADING c3_cat_bike c3_cat_bike_h
		IF NOT IS_CHAR_DEAD catalina
			WARP_CHAR_INTO_CAR catalina c3_cat_bike
			TASK_LOOK_AT_CHAR catalina scplayer -1
			SET_CHAR_COORDINATES scplayer -86.3344 -1127.3159 0.0781 
			SET_CHAR_HEADING scplayer 6.8740
			TASK_LOOK_AT_CHAR scplayer catalina -1
		ENDIF
	RETURN

// ------------------------------------------------------------------------------------------------
// Start Mission
mission_start_cat3:
	//REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1
	WAIT 0

	LOAD_MISSION_TEXT CAT
// ------------------------------------------------------------------------------------------------
// Initialize Variables
// ---- Mission Pass Score
	c3_pass = 5000
// ---- Flags
	c3_stage = 0
	c3_in_car = 1
	c3_cut = 0	
	c3_help_text_flag = 0
	c3_cut_delay = 0
	c3_trailer_health_old = 1500
	c3_end_brake = 0

	c3_counter = 0
	c3_ahead_counter = 1

	c3_audio_slot = 1
	c3_alt_slot = 2

	c3_audio_playing = 0

// ---- Dialogue Text
	$c3_text[1] = CAT3_AA // CATALINA: Hand over the takings, or I blow your fucking balls off!
	$c3_text[2] = CAT3_AB // CLERK 1: This here’s bulletproof glass!
	$c3_text[3] = CAT3_AC // CLERK 1: So you can just fuck off, bitch, before I call the sheriff!
	$c3_text[4] = CAT3_AD // CLERK 2: What you doing, man, just give her the cash!
	$c3_text[5] = CAT3_AE // CATALINA: Suit yourself, macaron.
	$c3_text[6] = CAT3_AF // CATALINA: Change of plan, Carl, we’re taking the tanker!
	$c3_text[7] = CAT3_AG // CLERK 1: Hey! What you doing?

	$c3_text[8] = CAT3_BA // CATALINA: Move it, you lazy bastard, get that tanker hooked up!
	$c3_text[9] = CAT3_BB // CARL: Yo, I’m on it, I’m on it!
	$c3_text[10] = CAT3_BC // CLERK 1: You won’t get away with this!
	$c3_text[11] = CAT3_BD // CATALINA: Shut your spotty little mouth!

	$c3_text[12] = CAT3_CA // CATALINA: Drive! I know a guy who’ll pay for this rig and its cargo!
	$c3_text[13] = CAT3_CB // CLERK 1: I ain’t losing another crappy job because of some crazy bitch!
	$c3_text[14] = CAT3_CC // CLERK 1: C’mon, Derek, we’re going to stop those bastards!
	$c3_text[15] = CAT3_CD // CLERK 2: But I don’t wanna!

	$c3_text[16] = CAT3_DA // CATALINA: Follow me!
	$c3_text[17] = CAT3_DB // CLERK 1: Oh no you don’t, bitch!
	$c3_text[18] = CAT3_DC // CLERK 1: C’mon, Derek, we’re going to be local heroes!
	$c3_text[19] = CAT3_DD // CLERK 2: I’d be happier staying here and stacking shelves!

	$c3_text[20] = CAT3_EA // CATALINA: Follow me!
	$c3_text[21] = CAT3_EB // CATALINA: What is keeping you?
	$c3_text[22] = CAT3_EC // CATALINA: C’mon, Carl, move it!

	$c3_text[23] = CAT3_ED // CATALINA: Not far now!
	$c3_text[24] = CAT3_EE // CATALINA: This way!
	$c3_text[25] = CAT3_EF // CATALINA: Where have you been?
	$c3_text[26] = CAT3_EG // CATALINA: I thought you said you could drive!

	$c3_text[27] = CAT3_FA // CLERK 1: Keep her steady so I can get a good shot!
	$c3_text[28] = CAT3_FB // CLERK 1: Pull alongside!
	$c3_text[29] = CAT3_FC // CLERK 1: Keep up with them!
	$c3_text[30] = CAT3_FD // CLERK 1: Get ahead of them!
	$c3_text[31] = CAT3_FE // CLERK 1: Block their route!
	$c3_text[32] = CAT3_FF // CLERK 1: Steady... steady...
	
	$c3_text[33] = CAT3_GA // CLERK 2: It ain't worth it!
	$c3_text[34] = CAT3_GB // CLERK 2: I'm scared!
	$c3_text[35] = CAT3_GC // CLERK 2: This ain't my fight!
	$c3_text[36] = CAT3_GD // CLERK 2: We'll get ourselves killed!
	$c3_text[37] = CAT3_GE // CLERK 2: I don't wanna do this!
	$c3_text[38] = CAT3_GF // CLERK 2: Oh my god, I'm gonnna die!
	$c3_text[39] = CAT3_GG // CLERK 2: That rig will crush us!

	$c3_text[40] = CAT3_HA // CATALINA: Pull up in that depot up ahead!
	$c3_text[41] = CAT3_HB // CATALINA: That's the place, up ahead!
	$c3_text[42] = CAT3_HC // CATALINA: Yeehaa, that's the place, go, Carl, go!

	$c3_text[43] = CAT3_JA // CATALINA: Hello, Mr. Whittaker!
	$c3_text[44] = CAT3_JB // MR WHITTAKER: Catalina! What have you brought me today?
	$c3_text[45] = CAT3_JC // CATALINA: A rig and tanker, full to the brim with premium gas!
	$c3_text[46] = CAT3_JD // MR WHITTAKER: Never seen it, never saw you, never gave you this wad of cash!
	$c3_text[47] = CAT3_JE // CATALINA: Nice not doing business!
	$c3_text[48] = CAT3_JF // MR WHITTAKER: Likewise. Now get out of here, before the cops come snooping.
	$c3_text[49] = CAT3_JG // MR WHITTAKER: You ever get hold of another rig, just drop it in, 
	$c3_text[50] = CAT3_JH // MR WHITTAKER: I'll always give a good price!
	$c3_text[51] = CAT3_JJ // MR WHITTAKER: You ever want to run some freight for me just drop in.
	$c3_text[52] = CAT3_JK // MR WHITTAKER: I've always got shit needs moving!

	$c3_text[53] = CATX_UJ 
	$c3_text[54] = CATX_UK 

	$c3_text[55] = CAT_PF


// ---- Dialogue Audio
	c3_audio[1] = SOUND_CAT3_AA // CATALINA: Hand over the takings, or I blow your fucking balls off!
	c3_audio[2] = SOUND_CAT3_AB // CLERK 1: This here’s bulletproof glass!
	c3_audio[3] = SOUND_CAT3_AC // CLERK 1: So you can just fuck off, bitch, before I call the sheriff!
	c3_audio[4] = SOUND_CAT3_AD // CLERK 2: What you doing, man, just give her the cash!
	c3_audio[5] = SOUND_CAT3_AE // CATALINA: Suit yourself, macaron.
	c3_audio[6] = SOUND_CAT3_AF // CATALINA: Change of plan, Carl, we’re taking the tanker!
	c3_audio[7] = SOUND_CAT3_AG // CLERK 1: Hey! What you doing?

	c3_audio[8] = SOUND_CAT3_BA // CATALINA: Move it, you lazy bastard, get that tanker hooked up!
	c3_audio[9] = SOUND_CAT3_BB // CARL: Yo, I’m on it, I’m on it!
	c3_audio[10] = SOUND_CAT3_BC // CLERK 1: You won’t get away with this!
	c3_audio[11] = SOUND_CAT3_BD // CATALINA: Shut your spotty little mouth!

	c3_audio[12] = SOUND_CAT3_CA // CATALINA: Drive! I know a guy who’ll pay for this rig and its cargo!
	c3_audio[13] = SOUND_CAT3_CB // CLERK 1: I ain’t losing another crappy job because of some crazy bitch!
	c3_audio[14] = SOUND_CAT3_CC // CLERK 1: C’mon, Derek, we’re going to stop those bastards!
	c3_audio[15] = SOUND_CAT3_CD // CLERK 2: But I don’t wanna!

	c3_audio[16] = SOUND_CAT3_DA // CATALINA: Follow me!
	c3_audio[17] = SOUND_CAT3_DB // CLERK 1: Oh no you don’t, bitch!
	c3_audio[18] = SOUND_CAT3_DC // CLERK 1: C’mon, Derek, we’re going to be local heroes!
	c3_audio[19] = SOUND_CAT3_DD // CLERK 2: I’d be happier staying here and stacking shelves!

	c3_audio[20] = SOUND_CAT3_EA // CATALINA: Follow me!
	c3_audio[21] = SOUND_CAT3_EB // CATALINA: What is keeping you?
	c3_audio[22] = SOUND_CAT3_EC // CATALINA: C’mon, Carl, move it!

	c3_audio[23] = SOUND_CAT3_ED // CATALINA: Not far now!
	c3_audio[24] = SOUND_CAT3_EE // CATALINA: This way!
	c3_audio[25] = SOUND_CAT3_EF // CATALINA: Where have you been?
	c3_audio[26] = SOUND_CAT3_EG // CATALINA: I thought you said you could drive!

	c3_audio[27] = SOUND_CAT3_FA // Keep her steady so I can get a good shot!
	c3_audio[28] = SOUND_CAT3_FB // Pull alongside!
	c3_audio[29] = SOUND_CAT3_FC // Keep up with them!
	c3_audio[30] = SOUND_CAT3_FD // Get ahead of them!
	c3_audio[31] = SOUND_CAT3_FE // Block their route!
	c3_audio[32] = SOUND_CAT3_FF // Steady... steady...
	
	c3_audio[33] = SOUND_CAT3_GA // It ain't worth it!
	c3_audio[34] = SOUND_CAT3_GB // I'm scared!
	c3_audio[35] = SOUND_CAT3_GC // This ain't my fight!
	c3_audio[36] = SOUND_CAT3_GD // We'll get ourselves killed!
	c3_audio[37] = SOUND_CAT3_GE // I don't wanna do this!
	c3_audio[38] = SOUND_CAT3_GF // Oh my god, I'm gonnna die!
	c3_audio[39] = SOUND_CAT3_GG // That rig will crush us!

	c3_audio[40] = SOUND_CAT3_HA // Pull up in that depot up ahead!
	c3_audio[41] = SOUND_CAT3_HB // That's the place, up ahead!
	c3_audio[42] = SOUND_CAT3_HC // Yeehaa, that's the place, go, Carl, go!

	c3_audio[43] = SOUND_CAT3_JA // Hello, Mr. Whittaker!
	c3_audio[44] = SOUND_CAT3_JB // Catalina! What have you brought me today?
	c3_audio[45] = SOUND_CAT3_JC // A rig and tanker, full to the brim with premium gas!
	c3_audio[46] = SOUND_CAT3_JD // Never seen it, never saw you, never gave you this wad of cash!
	c3_audio[47] = SOUND_CAT3_JE // Nice not doing business!
	c3_audio[48] = SOUND_CAT3_JF // Likewise. Now get out of here, before the cops come snooping.
	c3_audio[49] = SOUND_CAT3_JG // You ever get hold of another rig, just drop it in, 
	c3_audio[50] = SOUND_CAT3_JH // I'll always give a good price!
	c3_audio[51] = SOUND_CAT3_JJ // You ever want to run some freight for me just drop in.
	c3_audio[52] = SOUND_CAT3_JK // I've always got shit needs moving!

	c3_audio[53] = SOUND_CATX_UJ 
	c3_audio[54] = SOUND_CATX_UK 

	c3_audio[55] = SOUND_CAT_PF

// ---- Coords
	c3_gas_loc_x = -43.7625 
	c3_gas_loc_y = -1141.8047
	c3_gas_loc_z = 0.0781 
	
	c3_cat_gas_x = 660.3846 
	c3_cat_gas_y = -571.9213
	c3_cat_gas_z = 15.3465 
	   

// ---- Camera Coords
	c3_cam_x[0] = 	   659.5142 
	c3_cam_y[0] = 	   -571.6500
	c3_cam_z[0]	= 	   17.0104 

	c3_cam_look_x[0] = 660.4576 
	c3_cam_look_y[0] = -571.3304
	c3_cam_look_z[0] = 16.9229 
	
	c3_cam_x[1] =	661.7569 
	c3_cam_y[1] =	-570.6351
	c3_cam_z[1]	=	17.1184 

	c3_cam_look_x[1] =	662.4741 
	c3_cam_look_y[1] =	-569.9394
	c3_cam_look_z[1] =	17.0783

	c3_cam_x[2] =		658.7903 
	c3_cam_y[2] =		-568.8011
	c3_cam_z[2]	=		17.1657 

	c3_cam_look_x[2] =	659.5721 
	c3_cam_look_y[2] =	-569.3959
	c3_cam_look_z[2] =	16.9783 

	c3_cam_x[3] =		659.4771 
	c3_cam_y[3] =		-568.5789
	c3_cam_z[3]	=		17.0879 

	c3_cam_look_x[3] =	660.1776 
	c3_cam_look_y[3] =	-569.2756
	c3_cam_look_z[3] =	16.9335 

	c3_cam_x[4] =		655.2368 
	c3_cam_y[4] =		-601.8433 
	c3_cam_z[4]	=		16.4703 

	c3_cam_look_x[4] =	654.2847 
	c3_cam_look_y[4] =	-601.6681 
	c3_cam_look_z[4] =	16.2199 
	
	c3_cam_x[5] =		-73.4246 
	c3_cam_y[5] =		-1132.7161
	c3_cam_z[5]	=		1.4933 

	c3_cam_look_x[5] =	-74.3653 
	c3_cam_look_y[5] =	-1133.0492
	c3_cam_look_z[5] =	1.5579
	
	c3_cam_x[6] =		-80.0272 
	c3_cam_y[6] =		-1133.9542
	c3_cam_z[6]	=		1.2517 

	c3_cam_look_x[6] =	-79.0308 
	c3_cam_look_y[6] =	-1133.9556
	c3_cam_look_z[6] =	1.3367
	
	c3_cam_x[7] =		-76.5131 
	c3_cam_y[7] =		-1135.4342
	c3_cam_z[7]	=		1.6855 

	c3_cam_look_x[7] =	-75.8151 
	c3_cam_look_y[7] =	-1134.7186
	c3_cam_look_z[7] =	1.7110

	c3_clerk_cruise = 35.0

// ------------------------------------------------------------------------------------------------
// Entity GoSubs / Create
	
	GOSUB c3_cat_create
	GOSUB c3_load_all
	GOSUB c3_clerk_create
	GOSUB c3_truck_create
	GOSUB c3_trailer_create
	GOSUB c3_chase_create

	SET_PLAYER_GROUP_RECRUITMENT player1 FALSE

	LOAD_SCENE_IN_DIRECTION c3_cat_gas_x c3_cat_gas_y c3_cat_gas_z 295.0

// ------------------------------------------------------------------------------------------------
// ---- Groups
// ------------------------------------------------------------------------------------------------
// Task Sequences
// ------------------------------------------------------------------------------------------------
// Starting blip...
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON

	IF IS_CHAR_IN_ANY_CAR scplayer
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 659.6722 -569.2720 15.3465
	ELSE
		SET_CHAR_COORDINATES scplayer 659.6722 -569.2720 15.3465
	ENDIF

	IF NOT IS_CHAR_DEAD catalina
	AND NOT IS_CHAR_DEAD c3_clerk[0]
		IF IS_CHAR_IN_ANY_CAR catalina
			WARP_CHAR_FROM_CAR_TO_COORD catalina c3_cat_gas_x c3_cat_gas_y c3_cat_gas_z
		ELSE
			SET_CHAR_COORDINATES catalina c3_cat_gas_x c3_cat_gas_y c3_cat_gas_z
		ENDIF
				TASK_TURN_CHAR_TO_FACE_CHAR scplayer c3_clerk[0]
		TASK_TURN_CHAR_TO_FACE_CHAR c3_clerk[0] catalina
		SET_CHAR_HEADING scplayer 177.1965
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE c3_clerk[0] TRUE
				SET_FIXED_CAMERA_POSITION c3_cam_x[0] c3_cam_y[0] c3_cam_z[0] 0.0 0.0 0.0
		POINT_CAMERA_AT_POINT c3_cam_look_x[0] c3_cam_look_y[0] c3_cam_look_z[0] JUMP_CUT
		GET_GAME_TIMER c3_timer_start
		REMOVE_CHAR_FROM_GROUP catalina
		c3_cut = 1
		TASK_AIM_GUN_AT_CHAR catalina c3_clerk[0] 20000
		TASK_STAY_IN_SAME_PLACE catalina TRUE
		TASK_STAY_IN_SAME_PLACE c3_clerk[0] TRUE
		c3_counter = 1 //CATALINA: Hand over the takings, or I blow your fucking balls off!
		//GOSUB c3_play_dialogue
		//PRINT_NOW ( CAT3_AA ) 5000 1 //CATALINA: Hand over the takings, or I blow your fucking balls off!
	ENDIF
	SET_FADING_COLOUR 0 0 0
	SET_FADING_COLOUR 0 0 0
	DO_FADE 500 FADE_IN
	DO_FADE 500 FADE_IN

	SET_CAR_DENSITY_MULTIPLIER 0.5


 
// ------------------------------------------------------------------------------------------------
// Main Loop
c3_main_loop:

WAIT 0

// ---- Load & Play Dialogue...
IF NOT c3_counter = 0
	IF c3_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED c3_alt_slot
			CLEAR_MISSION_AUDIO c3_alt_slot
		ENDIF
		c3_audio_playing = 1
	ENDIF

	IF c3_audio_playing = 1
		LOAD_MISSION_AUDIO c3_audio_slot c3_audio[c3_counter]
		GOSUB c3_dialogue_pos
		
		c3_audio_playing = 2
	ENDIF

	IF c3_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED c3_audio_slot
			IF NOT c3_audio_char = 0
				IF NOT IS_CHAR_DEAD	c3_audio_char
					START_CHAR_FACIAL_TALK c3_audio_char 10000
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH c3_audio_char TRUE
					//ATTACH_MISSION_AUDIO_TO_CHAR c3_audio_slot c3_audio_char
				ENDIF
			ENDIF
			PLAY_MISSION_AUDIO c3_audio_slot
			PRINT_NOW $c3_text[c3_counter] 10000 1
			c3_audio_playing = 3
		ENDIF
	ENDIF

	IF c3_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED c3_audio_slot
			CLEAR_THIS_PRINT $c3_text[c3_counter]
			IF NOT c3_audio_char = 0
				IF NOT IS_CHAR_DEAD	c3_audio_char
					STOP_CHAR_FACIAL_TALK c3_audio_char
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH c3_audio_char FALSE
				ENDIF
			ENDIF
			IF c3_audio_slot = 1
				c3_audio_slot = 2
				c3_alt_slot = 1
			ELSE
				c3_audio_slot = 1
				c3_alt_slot = 2
			ENDIF
			c3_counter = 0
			c3_audio_playing = 0
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED c3_alt_slot
				IF c3_counter < 60
					c3_ahead_counter = c3_counter + 1
					LOAD_MISSION_AUDIO c3_alt_slot c3_audio[c3_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// -----------------

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_passed_cat3
ENDIF

IF c3_stage = 0
	IF c3_cut < 6
		IF IS_BUTTON_PRESSED PAD1 CROSS
			c3_timer_start -= 10000 
			//CLEAR_PRINTS
			c3_cut = 7
		ENDIF
	ENDIF
	IF c3_cut = 1
		GET_GAME_TIMER c3_timer_end
		c3_timer_diff = c3_timer_end - c3_timer_start
		IF c3_timer_diff > 2000
			IF c3_audio_playing = 0
				IF NOT IS_CHAR_DEAD catalina
				AND NOT IS_CHAR_DEAD c3_clerk[0]
					OPEN_SEQUENCE_TASK c3_clerk_cut_seq_1
						TASK_FOLLOW_PATH_NODES_TO_COORD  -1 662.8280 -570.1751 15.3359 PEDMOVE_WALK -2
						TASK_LOOK_AT_CHAR -1 catalina 30000
						TASK_TURN_CHAR_TO_FACE_CHAR -1 catalina
					CLOSE_SEQUENCE_TASK c3_clerk_cut_seq_1
					PERFORM_SEQUENCE_TASK c3_clerk[0] c3_clerk_cut_seq_1
					GET_GAME_TIMER c3_timer_start
					//PRINT_NOW ( CAT3_AB ) 5000 1	
					c3_counter = 2	// CLERK 1: This here’s bulletproof glass!
					c3_cut = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF c3_cut = 2
		IF NOT IS_CHAR_DEAD c3_clerk[0] 
			IF NOT IS_CHAR_PLAYING_ANIM c3_clerk[0] bng_wndw_02
				GET_SCRIPT_TASK_STATUS c3_clerk[0] PERFORM_SEQUENCE_TASK c3_clerk_status
				IF c3_clerk_status = FINISHED_TASK
					TASK_PLAY_ANIM c3_clerk[0] bng_wndw_02 MISC 4.0 FALSE FALSE FALSE FALSE -1 
				ENDIF
			ENDIF
		ENDIF
		GET_GAME_TIMER c3_timer_end
		c3_timer_diff = c3_timer_end - c3_timer_start
		IF c3_timer_diff > 2000
			IF c3_audio_playing = 0
				IF NOT IS_CHAR_DEAD catalina
				AND NOT IS_CHAR_DEAD c3_clerk[0]
					SET_FIXED_CAMERA_POSITION c3_cam_x[1] c3_cam_y[1] c3_cam_z[1] 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT c3_cam_look_x[1] c3_cam_look_y[1] c3_cam_look_z[1] JUMP_CUT
					GET_GAME_TIMER c3_timer_start
					c3_counter = 3	// CLERK 1: So you can just fuck off, bitch, before I call the sheriff!
					//PRINT_NOW ( CAT3_AC ) 5000 1 // CLERK 1: So you can just fuck off, bitch, before I call the sheriff!
					c3_cut = 3
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF c3_cut = 3
		GET_GAME_TIMER c3_timer_end
		c3_timer_diff = c3_timer_end - c3_timer_start
		IF c3_timer_diff > 2000
			IF c3_audio_playing = 0
				IF NOT IS_CHAR_DEAD catalina
				AND NOT IS_CHAR_DEAD c3_clerk[0]
				AND NOT IS_CHAR_DEAD c3_clerk[1]
					TASK_SHAKE_FIST c3_clerk[1]
					//TASK_COWER c3_clerk[1]
					GET_GAME_TIMER c3_timer_start
					c3_counter = 4	//CLERK 2: What you doing, man, just give her the cash!
					//PRINT_NOW ( CAT3_AD ) 5000 1 //CLERK 2: What you doing, man, just give her the cash!
					c3_cut = 4
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF c3_cut = 4
		GET_GAME_TIMER c3_timer_end
		c3_timer_diff = c3_timer_end - c3_timer_start
		IF c3_timer_diff > 2000
			IF c3_audio_playing = 0
				IF NOT IS_CAR_DEAD c3_truck
					SET_FIXED_CAMERA_POSITION c3_cam_x[2] c3_cam_y[2] c3_cam_z[2] 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT c3_cam_look_x[2] c3_cam_look_y[2] c3_cam_look_z[2] JUMP_CUT
					GET_GAME_TIMER c3_timer_start
					c3_counter = 5	//CATALINA: Suit yourself, macaron.
					//PRINT_NOW ( CAT3_AE ) 3000 1	//CATALINA: Suit yourself, macaron.
					c3_cut = 5
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF c3_cut = 5
		GET_GAME_TIMER c3_timer_end
		c3_timer_diff = c3_timer_end - c3_timer_start
		IF c3_timer_diff > 2000
			IF c3_audio_playing = 0
				IF NOT IS_CAR_DEAD c3_truck
				AND NOT IS_CHAR_DEAD catalina
				AND NOT IS_CHAR_DEAD scplayer
					POINT_CAMERA_AT_CAR c3_truck FIXED JUMP_CUT
					GET_GAME_TIMER c3_timer_start
					TASK_LOOK_AT_CHAR scplayer catalina	 8000
					//PRINT_NOW ( CAT3_AF ) 5000 1	//CATALINA: Change of plan, Carl, we’re taking the tanker!
					OPEN_SEQUENCE_TASK c3_cat_seq_1
						TASK_LOOK_AT_CHAR -1 scplayer 1000
						TASK_GO_STRAIGHT_TO_COORD -1 657.7125 -576.2346 15.3359 PEDMOVE_RUN -1
						TASK_GO_STRAIGHT_TO_COORD -1 654.6555 -585.0117 15.3359 PEDMOVE_RUN -1
						TASK_ENTER_CAR_AS_PASSENGER -1 c3_truck 30000 0	
					CLOSE_SEQUENCE_TASK c3_cat_seq_1
					PERFORM_SEQUENCE_TASK catalina c3_cat_seq_1
					c3_counter = 6 //CATALINA: Change of plan, Carl, we’re taking the tanker!
					c3_cut = 6
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF c3_cut = 6
		GET_GAME_TIMER c3_timer_end
		c3_timer_diff = c3_timer_end - c3_timer_start
		IF c3_timer_diff > 2000
			IF c3_audio_playing = 0
				IF NOT IS_CHAR_DEAD catalina
				AND NOT IS_CHAR_DEAD c3_clerk[0]
					SET_FIXED_CAMERA_POSITION c3_cam_x[3] c3_cam_y[3] c3_cam_z[3] 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT c3_cam_look_x[3] c3_cam_look_y[3] c3_cam_look_z[3] JUMP_CUT
					TASK_FOLLOW_PATH_NODES_TO_COORD scplayer 659.8791 -573.4293 15.3465 PEDMOVE_WALK -2
					//PRINT ( CAT3_AG ) 5000 1  //CLERK 1: Hey! What you doing?
					c3_counter = 7 //CLERK 1: Hey! What you doing?
					GET_GAME_TIMER c3_timer_start
					//TASK_SHAKE_FIST c3_clerk[0]
					c3_cut = 7
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF c3_cut = 7
		GET_GAME_TIMER c3_timer_end
		c3_timer_diff = c3_timer_end - c3_timer_start
		IF c3_timer_diff > 2500
			IF c3_audio_playing = 0
				IF NOT IS_CHAR_DEAD catalina
				AND NOT IS_CHAR_DEAD c3_clerk[0]
				AND NOT IS_CAR_DEAD c3_truck
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					IF NOT IS_CAR_DEAD c3_truck
						ADD_BLIP_FOR_CAR c3_truck c3_truck_blip
						SET_BLIP_AS_FRIENDLY c3_truck_blip TRUE
						IF NOT IS_CHAR_DEAD catalina
							IF NOT IS_CHAR_IN_CAR catalina c3_truck
								TASK_ENTER_CAR_AS_PASSENGER catalina c3_truck 30000 0
							ENDIF
						ENDIF
						IF NOT IS_CAR_DEAD c3_trailer
							FREEZE_CAR_POSITION c3_trailer TRUE
						ENDIF
					ENDIF
					REMOVE_ANIMATION MISC
					CLEAR_AREA catX[3] catY[3] catZ[3] 200.0 TRUE
					SET_CHAR_HEADING scplayer c3_truck_h
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					SET_FADING_COLOUR 0 0 0
					DO_FADE 500 FADE_IN
					DO_FADE 500 FADE_IN
					DO_FADE 500 FADE_IN
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					c3_stage = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF c3_stage = 1
	IF NOT IS_CAR_DEAD c3_truck
	AND NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD c3_trailer
		PRINT_NOW ( CAT3_02 ) 5000 1
		IF IS_CHAR_IN_CAR scplayer c3_truck
			REMOVE_BLIP c3_truck_blip
			PRINT_HELP ( CAT3_03 )
			REQUEST_ANIMATION CAR_CHAT

			WHILE NOT HAS_ANIMATION_LOADED CAR_CHAT
				WAIT 0
			ENDWHILE

			c3_stage = 2
		ENDIF
	ENDIF
ENDIF
	
IF c3_stage = 2
	IF NOT IS_CAR_DEAD c3_truck
	AND NOT IS_CAR_DEAD	c3_trailer
	AND NOT IS_CHAR_DEAD catalina
		IF LOCATE_CHAR_IN_CAR_CAR_3D scplayer c3_trailer 15.0 15.0 15.0 FALSE
			IF c3_help_text_flag = 0
				
				PRINT_HELP CAT3_12  
				
				GET_GAME_TIMER c3_timer_start
				c3_help_text_flag = 1
			ENDIF
		ENDIF
		IF c3_help_text_flag = 1
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 8000
				PRINT_HELP ( CAT3_13 )
				c3_help_text_flag = 2
			ENDIF
		ENDIF
		IF c3_cut_delay = 0
			IF IS_TRAILER_ATTACHED_TO_CAB c3_trailer c3_truck
				IF NOT IS_CAR_DEAD c3_trailer
					FREEZE_CAR_POSITION c3_trailer FALSE
				ENDIF
				//PRINT_NOW ( CAT3_14 ) 5000 1 // You're hooked up!
				PLAY_MISSION_AUDIO 3 //SOUND_TRAILER_HOOKUP
				GET_GAME_TIMER c3_timer_start
				REMOVE_BLIP c3_trailer_blip
				CLEAR_HELP
				c3_cut_delay = 1
			ENDIF
		ENDIF
		IF c3_cut_delay = 1
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 1500
				
					CLEAR_PRINTS
					LOCK_CAR_DOORS c3_truck CARLOCK_LOCKED_PLAYER_INSIDE
									
					DO_FADE 150 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					SWITCH_WIDESCREEN ON

					SET_PLAYER_CONTROL player1 OFF
					CLEAR_PRINTS
					IF HAS_MISSION_AUDIO_FINISHED 3
					CLEAR_MISSION_AUDIO 3
					ENDIF
					
					IF NOT IS_CAR_DEAD c3_truck
						LOCK_CAR_DOORS c3_truck CARLOCK_UNLOCKED
					ENDIF
				//IF c3_grouped = 0
					IF NOT IS_CHAR_DEAD catalina
					AND NOT IS_CAR_DEAD c3_truck 
						IF NOT IS_CHAR_IN_CAR catalina c3_truck
//							CLEAR_CHAR_TASKS_IMMEDIATELY catalina
//							//WARP_CHAR_INTO_CAR_AS_PASSENGER catalina c3_truck 0
//							IF NOT IS_GROUP_MEMBER catalina players_group
//								SET_GROUP_MEMBER players_group catalina 
//								REMOVE_BLIP catalina_blip
//								REMOVE_BLIP c3_gas_loc_blip
//								ADD_BLIP_FOR_COORD c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z c3_gas_loc_blip
//								CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
//								DISPLAY_ONSCREEN_COUNTER_WITH_STRING c3_trailer_health_display COUNTER_DISPLAY_BAR CAT3_11
//								//SET_ONSCREEN_COUNTER_COLOUR c3_trailer_health_display HUD_COLOUR_RED
//								WARP_CHAR_INTO_CAR_AS_PASSENGER catalina c3_truck 0
//								//c3_grouped = 1
//							ELSE
								REMOVE_CHAR_FROM_GROUP catalina
								REMOVE_BLIP catalina_blip
								REMOVE_BLIP c3_gas_loc_blip
								ADD_BLIP_FOR_COORD c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z c3_gas_loc_blip
								CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
								DISPLAY_ONSCREEN_COUNTER_WITH_STRING c3_trailer_health_display COUNTER_DISPLAY_BAR CAT3_11
								WARP_CHAR_INTO_CAR_AS_PASSENGER catalina c3_truck 0
								//SET_ONSCREEN_COUNTER_COLOUR c3_trailer_health_display HUD_COLOUR_RED
								//c3_grouped = 1
							//ENDIF
						ELSE
							REMOVE_BLIP c3_gas_loc_blip
							ADD_BLIP_FOR_COORD c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z c3_gas_loc_blip
							CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
							DISPLAY_ONSCREEN_COUNTER_WITH_STRING c3_trailer_health_display COUNTER_DISPLAY_BAR CAT3_11
							//SET_ONSCREEN_COUNTER_COLOUR c3_trailer_health_display HUD_COLOUR_RED
						ENDIF
					ENDIF
				
				
				
				 
				IF NOT IS_CAR_DEAD c3_trailer
					SET_CAR_PROOFS c3_trailer FALSE FALSE FALSE FALSE FALSE
				ENDIF
				REQUEST_CAR_RECORDING 188
				WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 188
					WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD c3_chase
				AND NOT IS_CHAR_DEAD c3_clerk[0]
				AND NOT IS_CHAR_DEAD c3_clerk[1]
				AND NOT IS_CHAR_DEAD catalina
				AND NOT IS_CAR_DEAD c3_truck
//					IF NOT IS_CHAR_IN_CAR catalina c3_truck
//						WARP_CHAR_INTO_CAR_AS_PASSENGER catalina c3_truck 0
// 					ENDIF
//					IF IS_GROUP_MEMBER catalina players_group
//						REMOVE_CHAR_FROM_GROUP catalina
//					ENDIF
					CLEAR_AREA 658.6589 -567.5756 15.3465 25.0 TRUE
					SET_CAR_COORDINATES	c3_chase 658.6589 -567.5756 15.3465
					SET_CAR_HEADING c3_chase 180.0
					WARP_CHAR_INTO_CAR c3_clerk[1] c3_chase
					WARP_CHAR_INTO_CAR_AS_PASSENGER c3_clerk[0] c3_chase 0
					SET_FIXED_CAMERA_POSITION 651.9285 -575.1257 16.2766 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT 652.3163 -576.0270 16.4698 JUMP_CUT
					GET_GAME_TIMER c3_timer_start
					c3_cut = 1
					c3_stage = 3
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF c3_stage = 3
	IF NOT IS_CHAR_DEAD c3_clerk[0]
	AND NOT IS_CHAR_DEAD c3_clerk[1]
	AND NOT IS_CAR_DEAD c3_chase
	AND NOT IS_CAR_DEAD c3_truck
		IF c3_cut < 4
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 1000
				IF IS_BUTTON_PRESSED PAD1 CROSS
					c3_timer_start -= 5000
					c3_cut = 4
				ENDIF
			ENDIF	
		ENDIF
		IF c3_cut = 1
			IF NOT IS_CAR_DEAD c3_chase
				DO_FADE 150 FADE_IN
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD c3_chase
					START_PLAYBACK_RECORDED_CAR c3_chase 188
				ENDIF
				//PRINT_NOW ( CAT3_CB ) 5000 1 //	CLERK 1: I ain’t losing another crappy job because of some crazy bitch!
				IF c3_audio_playing = 0
					c3_counter = 13 // CLERK 1: I ain’t losing another crappy job because of some crazy bitch!
				ENDIF
				GET_GAME_TIMER c3_timer_start
				c3_cut = 2	
			ENDIF
	   	ENDIF
		IF c3_cut = 2
			IF NOT IS_CAR_DEAD c3_chase
				IF IS_PLAYBACK_GOING_ON_FOR_CAR c3_chase
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS c3_chase 0.0 5.5 0.0 c3_offset_x c3_offset_y c3_offset_z
					c3_min_x = c3_offset_x - 0.25
					c3_min_y = c3_offset_y - 0.25
					c3_min_z = c3_offset_z - 0.25
					c3_max_x = c3_offset_x + 0.25
					c3_max_y = c3_offset_y + 0.25
					c3_max_z = c3_offset_z + 0.25
					IF IS_AREA_OCCUPIED c3_min_x c3_min_y c3_min_z c3_max_x c3_max_y c3_max_z FALSE TRUE FALSE FALSE FALSE
						STOP_PLAYBACK_RECORDED_CAR c3_chase
					ENDIF
				ENDIF
			ENDIF
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					IF NOT IS_CHAR_DEAD c3_clerk[0]
						TASK_PLAY_ANIM c3_clerk[0] CAR_Sc3_BR CAR_CHAT 4.0 FALSE FALSE FALSE FALSE -1 //for the passenger speaking
					ENDIF
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS c3_chase 0.9 1.0 0.5 c3_cam_x[4] c3_cam_y[4] c3_cam_z[4]
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS c3_chase 0.15 0.0 0.3 c3_cam_look_x[4] c3_cam_look_y[4] c3_cam_look_z[4]
					SET_FIXED_CAMERA_POSITION c3_cam_x[4] c3_cam_y[4] c3_cam_z[4] 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT c3_cam_look_x[4] c3_cam_look_y[4] c3_cam_look_z[4] JUMP_CUT
					SET_NEAR_CLIP 0.1
					// PRINT_NOW ( CAT3_CC ) 3500 1 //	CLERK 1: C’mon, Derek, we’re going to stop those bastards!
					c3_counter = 14 // CLERK 1: C’mon, Derek, we’re going to stop those bastards!
					GET_GAME_TIMER c3_timer_start
					c3_cut = 3
				ENDIF		
			ENDIF
	   	ENDIF
		IF c3_cut = 3
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					c3_counter = 15 // CLERK 2: But I don’t wanna!
					//PRINT_NOW ( CAT3_CD ) 3500 1 // CLERK 2: But I don’t wanna!
					TASK_PLAY_ANIM c3_clerk[1] CAR_Sc4_BL CAR_CHAT 2.0 TRUE FALSE FALSE FALSE 3500 // for the driver speaking
					GET_GAME_TIMER c3_timer_start
					c3_cut = 4	
				ENDIF	
			ENDIF
	   	ENDIF
		IF c3_cut = 4
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_audio_playing = 0
			AND c3_counter = 0
				CLEAR_PRINTS
				DO_FADE 150 FADE_IN
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				RESTORE_CAMERA_JUMPCUT
				IF NOT IS_CAR_DEAD c3_chase
				AND NOT IS_CHAR_DEAD c3_clerk[0]
					IF IS_CHAR_IN_CAR c3_clerk[0] c3_chase
						IF NOT IS_CAR_DEAD c3_trailer
							FREEZE_CAR_POSITION c3_trailer FALSE
						ENDIF
						SET_CAR_PROOFS c3_chase FALSE FALSE FALSE FALSE FALSE
						REMOVE_ANIMATION CAR_CHAT
						ADD_STUCK_CAR_CHECK_WITH_WARP c3_chase 1.0 2000 TRUE TRUE TRUE 2
						ADD_BLIP_FOR_CAR_OLD c3_chase RED MARKER_ONLY c3_chase_blip
						c3_dialogue_playing = 4
						GET_GAME_TIMER c3_text_timer_start
						IF c3_audio_playing = 0
							c3_counter = 12 // CATALINA: Drive! I know a guy who’ll pay for this rig and its cargo
						ENDIF

						//PRINT_NOW ( CAT3_CA ) 5000 1 //	CATALINA: Drive! I know a guy who’ll pay for this rig and its cargo
						c3_stage = 4

					ENDIF
				ENDIF
			ENDIF
	   	ENDIF
	ENDIF	
ENDIF

IF c3_stage = 4
	IF NOT IS_CAR_DEAD c3_truck
	AND NOT IS_CAR_DEAD c3_trailer
	AND NOT IS_CHAR_DEAD catalina
		IF c3_audio_playing = 0
			IF c3_counter = 0
				IF c3_cut = 4
					PRINT ( CAT3_05 ) 5000 1 // Don't jacknife
					c3_cut = -4
				ENDIF
			ENDIF
		ENDIF

		GET_CAR_HEALTH c3_trailer c3_trailer_health_display
		IF c3_trailer_health_display > 250
			c3_trailer_health_display -= 250
			c3_trailer_health_display /= 100
			c3_trailer_health_display *= 10
		ELSE
			CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
		ENDIF

		// Dialogue
		IF c3_in_car = 1
			IF NOT IS_CAR_DEAD c3_chase
			AND NOT IS_CHAR_DEAD c3_clerk[0]
			AND NOT IS_CHAR_DEAD c3_clerk[1]
				IF c3_dialogue_playing	= 0
					GENERATE_RANDOM_INT_IN_RANGE 0 6 c3_random_dialogue
					GET_GAME_TIMER c3_text_timer_start
					c3_dialogue_playing = 1
					IF c3_random_dialogue = c3_random_dialogue_last[0]
						IF c3_random_dialogue < 5 
							c3_random_dialogue ++
						ELSE
							IF c3_random_dialogue > 0
								c3_random_dialogue --
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF c3_dialogue_playing = 1
					IF c3_random_dialogue = 0
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 27 //CLERK 1: Keep her steady so I can get a good shot!
							IF IS_CHAR_IN_CAR scplayer c3_truck 
								SET_CHAR_ACCURACY c3_clerk[0] 20
								TASK_DRIVE_BY c3_clerk[0] catalina -1 0.0 -5.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN TRUE 50
								SET_CURRENT_CHAR_WEAPON c3_clerk[0] WEAPONTYPE_MICRO_UZI
								TASK_DRIVE_BY catalina c3_clerk[0] -1 0.0 0.0 0.0 30.0 DRIVEBY_FIXED_RHS TRUE 90
								SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_SHOTGUN
							ELSE
								SET_CHAR_ACCURACY c3_clerk[0] 50
								TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 15
							ENDIF
							c3_random_dialogue_last[0] = c3_random_dialogue
							GET_GAME_TIMER c3_text_timer_start
							c3_dialogue_playing = 2								
						ENDIF
					ENDIF
					IF c3_random_dialogue = 1
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 28 // CLERK 1: Pull alongside!
							IF IS_CHAR_IN_CAR scplayer c3_truck
								SET_CHAR_ACCURACY c3_clerk[0] 20
								TASK_DRIVE_BY c3_clerk[0] catalina -1 0.0 -5.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN TRUE 50
								SET_CURRENT_CHAR_WEAPON c3_clerk[0] WEAPONTYPE_MICRO_UZI
								TASK_DRIVE_BY catalina c3_clerk[0] -1 0.0 0.0 0.0 30.0 DRIVEBY_FIXED_RHS TRUE 90
								SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_SHOTGUN
							ELSE
								SET_CHAR_ACCURACY c3_clerk[0] 50
								TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 15
							ENDIF
							c3_random_dialogue_last[0] = c3_random_dialogue
							GET_GAME_TIMER c3_text_timer_start
							c3_dialogue_playing = 2
						ENDIF 
					ENDIF
					IF c3_random_dialogue = 2
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 29 // CLERK 1: Keep up with them!
							IF IS_CHAR_IN_CAR scplayer c3_truck
								SET_CHAR_ACCURACY c3_clerk[0] 20
								TASK_DRIVE_BY c3_clerk[0] catalina -1 0.0 -5.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN TRUE 50
								SET_CURRENT_CHAR_WEAPON c3_clerk[0] WEAPONTYPE_MICRO_UZI
								TASK_DRIVE_BY catalina c3_clerk[0] -1 0.0 0.0 0.0 30.0 DRIVEBY_FIXED_RHS TRUE 90
								SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_SHOTGUN
							ELSE
								SET_CHAR_ACCURACY c3_clerk[0] 50
								TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 15
							ENDIF
							c3_random_dialogue_last[0] = c3_random_dialogue
							GET_GAME_TIMER c3_text_timer_start
							c3_dialogue_playing = 2
						ENDIF 
					ENDIF
					IF c3_random_dialogue = 3
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 30 //CLERK 1: Get ahead of them!
							IF IS_CHAR_IN_CAR scplayer c3_truck
								SET_CHAR_ACCURACY c3_clerk[0] 20
								TASK_DRIVE_BY c3_clerk[0] catalina -1 0.0 -5.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN TRUE 50
								SET_CURRENT_CHAR_WEAPON c3_clerk[0] WEAPONTYPE_MICRO_UZI
								TASK_DRIVE_BY catalina c3_clerk[0] -1 0.0 0.0 0.0 30.0 DRIVEBY_FIXED_RHS TRUE 90
								SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_SHOTGUN
							ELSE
								SET_CHAR_ACCURACY c3_clerk[0] 50
								TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 15
							ENDIF
							c3_random_dialogue_last[0] = c3_random_dialogue
							GET_GAME_TIMER c3_text_timer_start
							c3_dialogue_playing = 2								
						ENDIF
					ENDIF
					IF c3_random_dialogue = 4
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 31 //CLERK 1: Block their route!
							IF IS_CHAR_IN_CAR scplayer c3_truck
								SET_CHAR_ACCURACY c3_clerk[0] 20
								TASK_DRIVE_BY c3_clerk[0] catalina -1 0.0 -5.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN TRUE 50
								SET_CURRENT_CHAR_WEAPON c3_clerk[0] WEAPONTYPE_MICRO_UZI
								TASK_DRIVE_BY catalina c3_clerk[0] -1 0.0 0.0 0.0 30.0 DRIVEBY_FIXED_RHS TRUE 90
								SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_SHOTGUN
							ELSE
								SET_CHAR_ACCURACY c3_clerk[0] 50
								TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 15
							ENDIF
							c3_random_dialogue_last[0] = c3_random_dialogue
							c3_dialogue_playing = 2								
						ENDIF
					ENDIF
					IF c3_random_dialogue = 5
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 32 // CLERK 1: Steady… steady…
							IF IS_CHAR_IN_CAR scplayer c3_truck
								SET_CHAR_ACCURACY c3_clerk[0] 20
								TASK_DRIVE_BY c3_clerk[0] catalina -1 0.0 -5.0 0.0 30.0 DRIVEBY_AI_ALL_DIRN TRUE 50
								SET_CURRENT_CHAR_WEAPON c3_clerk[0] WEAPONTYPE_MICRO_UZI
								TASK_DRIVE_BY catalina c3_clerk[0] -1 0.0 0.0 0.0 30.0 DRIVEBY_FIXED_RHS TRUE 90
								SET_CURRENT_CHAR_WEAPON catalina WEAPONTYPE_SHOTGUN
							ELSE
								SET_CHAR_ACCURACY c3_clerk[0] 50
								TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 15
							ENDIF
							c3_random_dialogue_last[0] = c3_random_dialogue
							GET_GAME_TIMER c3_text_timer_start
							c3_dialogue_playing = 2							
						ENDIF
					ENDIF
				ENDIF
				// Clerk...
				IF c3_dialogue_playing	= 2
					GENERATE_RANDOM_INT_IN_RANGE 6 13 c3_random_dialogue
					GET_GAME_TIMER c3_text_timer_start
					c3_dialogue_playing = 3
					IF c3_random_dialogue = c3_random_dialogue_last[1]
						IF c3_random_dialogue < 12 
							c3_random_dialogue ++
						ELSE
							IF c3_random_dialogue > 6
								c3_random_dialogue --
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				IF c3_dialogue_playing = 3
					IF c3_random_dialogue = 6
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 33	//CLERK 2: It ain’t worth it!
							c3_dialogue_playing = 4
							c3_random_dialogue_last[1] = c3_random_dialogue
						ENDIF
					ENDIF
					IF c3_random_dialogue = 7
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 34	//CLERK 2: I’m scared, man!
							c3_dialogue_playing = 4
							c3_random_dialogue_last[1] = c3_random_dialogue
						ENDIF
					ENDIF
					IF c3_random_dialogue = 8
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 35	//CLERK 2: This ain’t my fight!
							c3_dialogue_playing = 4
							c3_random_dialogue_last[1] = c3_random_dialogue
						ENDIF
					ENDIF
					IF c3_random_dialogue = 9
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 36	//CLERK 2: We’ll get ourselves killed!
							c3_dialogue_playing = 4
							c3_random_dialogue_last[1] = c3_random_dialogue
						ENDIF
					ENDIF
					IF c3_random_dialogue = 10
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 37	//CLERK 2: I don’t wanna do this!
							c3_dialogue_playing = 4
							c3_random_dialogue_last[1] = c3_random_dialogue
						ENDIF
					ENDIF
					IF c3_random_dialogue = 11
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 38	//CLERK 2: Oh my god, I’m gonnna die!
							c3_dialogue_playing = 4
							c3_random_dialogue_last[1] = c3_random_dialogue
						ENDIF
					ENDIF
					IF c3_random_dialogue = 12
						IF c3_audio_playing = 0
						AND c3_counter = 0
							c3_counter = 39	 //CLERK 2: That rig will crush us!
							c3_dialogue_playing = 4
							c3_random_dialogue_last[1] = c3_random_dialogue
						ENDIF
					ENDIF
				ENDIF
				IF c3_dialogue_playing = 4
					GET_GAME_TIMER c3_text_timer_end 
					c3_text_timer_diff = c3_text_timer_end - c3_text_timer_start
					IF c3_text_timer_diff > 12000
						c3_dialogue_playing = 5
					ENDIF
				ENDIF
			ENDIF
		ELSE
			IF NOT IS_CAR_DEAD c3_chase
			AND NOT	IS_CHAR_DEAD c3_clerk[0]
				GET_SCRIPT_TASK_STATUS c3_clerk[0] TASK_DRIVE_BY c3_clerk_status
				IF c3_clerk_status = FINISHED_TASK
					SET_CHAR_ACCURACY c3_clerk[0] 50
					TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 60
				ENDIF
			ENDIF
		ENDIF

		IF IS_CAR_DEAD c3_chase
			IF NOT IS_CHAR_DEAD c3_clerk[0]
				IF NOT IS_CHAR_ON_SCREEN c3_clerk[0]
					REMOVE_CHAR_ELEGANTLY c3_clerk[0]
				ENDIF
			ENDIF
			IF NOT IS_CHAR_DEAD c3_clerk[1]
				IF NOT IS_CHAR_ON_SCREEN c3_clerk[1]
					REMOVE_CHAR_ELEGANTLY c3_clerk[1]
				ENDIF
			ENDIF
			REMOVE_BLIP c3_chase_blip
		ENDIF
		//SET_CAR_HEALTH c3_chase 1000

		// Speed Up & Slow Down...
		IF NOT IS_CHAR_DEAD	c3_clerk[0]
		AND NOT IS_CAR_DEAD c3_chase
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer c3_clerk[0] 15.0 15.0 15.0 FALSE
				IF c3_clerk_cruise < 60.0
					c3_clerk_cruise += 0.2
				ENDIF
			ELSE
				IF c3_clerk_cruise > 40.0
					c3_clerk_cruise -= 0.1
				ENDIF
	 		ENDIF

			// Catch up...
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z 120.0 120.0 120.0 FALSE
				IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer c3_clerk[0] 100.0 100.0 100.0 FALSE
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS c3_trailer 0.0 -40.0 0.0 c3_offset_x c3_offset_y c3_offset_z
					//GET_CAR_COORDINATES	c3_trailer c3_trailer_x c3_trailer_y c3_trailer_z
					GET_NTH_CLOSEST_CAR_NODE_WITH_HEADING c3_offset_x c3_offset_y c3_offset_z 1 c3_chase_x c3_chase_y c3_chase_z c3_chase_h
		 			IF NOT IS_POINT_ON_SCREEN c3_chase_x c3_chase_y c3_chase_z 15.0
						
						SET_CAR_COORDINATES c3_chase c3_chase_x c3_chase_y c3_chase_z
						SET_CAR_HEADING c3_chase c3_chase_h
					ENDIF
				ELSE
					IF c3_dialogue_playing = 5
						c3_dialogue_playing = 0
					ENDIF
				ENDIF 
			ENDIF
		ENDIF

 		// Update gas display...	
		GET_CAR_HEALTH c3_trailer c3_trailer_health
		
		// Create oil patch on trailer damage...
//		IF c3_trailer_health < c3_trailer_health_old
//			c3_trailer_health_old = c3_trailer_health
//			GET_CAR_COORDINATES	c3_trailer c3_trailer_x c3_trailer_y c3_trailer_z
//			GET_GROUND_Z_FOR_3D_COORD c3_trailer_x c3_trailer_y c3_trailer_z c3_trailer_z
//			CREATE_OIL_PUDDLE c3_trailer_x c3_trailer_y c3_trailer_z
//	   	ENDIF

		// Attack the Trailer...
		IF c3_end_brake = 0
			IF NOT IS_CHAR_DEAD c3_clerk[0]
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z 120.0 120.0 120.0 FALSE
					IF NOT IS_CHAR_DEAD c3_clerk[1]
						IF NOT IS_CAR_DEAD c3_trailer
							IF NOT IS_CAR_DEAD c3_chase
								GET_SCRIPT_TASK_STATUS c3_clerk[1] TASK_CAR_MISSION c3_task_status
								IF c3_task_status = FINISHED_TASK
									TASK_CAR_MISSION c3_clerk[1] c3_chase c3_truck MISSION_BLOCKCAR_FARAWAY c3_clerk_cruise DRIVINGMODE_AVOIDCARS
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ELSE
					IF NOT IS_CAR_DEAD c3_chase
						IF NOT IS_CHAR_DEAD c3_clerk[1]
							TASK_CAR_TEMP_ACTION c3_clerk[1] c3_chase TEMPACT_HANDBRAKESTRAIGHT 2000
							GET_GAME_TIMER c3_timer_start
							IF DOES_CAR_HAVE_STUCK_CAR_CHECK c3_chase
								REMOVE_STUCK_CAR_CHECK c3_chase	
							ENDIF
							c3_end_brake = 1
						ENDIF
					ENDIF
				ENDIF
				
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer c3_clerk[0] 30.0 30.0 30.0 FALSE
					IF NOT IS_CHAR_DEAD c3_clerk[1]
						IF NOT IS_CAR_DEAD c3_trailer
							IF NOT IS_CAR_DEAD c3_chase
								GENERATE_RANDOM_INT_IN_RANGE 0 4 c3_rand_escort
		//						GET_SCRIPT_TASK_STATUS c3_clerk[1] TASK_CAR_MISSION c3_task_status
		//						IF c3_task_status = FINISHED_TASK
								c3_timer_diff = c3_timer_end - c3_timer_start
								IF c3_timer_diff = 5000
									IF c3_rand_escort = 0
										TASK_CAR_MISSION c3_clerk[1] c3_chase c3_truck MISSION_ESCORT_LEFT c3_clerk_cruise DRIVINGMODE_AVOIDCARS
										GET_GAME_TIMER c3_timer_start
									ENDIF
									IF c3_rand_escort = 1
										TASK_CAR_MISSION c3_clerk[1] c3_chase c3_truck MISSION_ESCORT_RIGHT c3_clerk_cruise DRIVINGMODE_AVOIDCARS
										GET_GAME_TIMER c3_timer_start
									ENDIF
									IF c3_rand_escort = 2
										TASK_CAR_MISSION c3_clerk[1] c3_chase c3_truck MISSION_ESCORT_REAR c3_clerk_cruise DRIVINGMODE_AVOIDCARS
										GET_GAME_TIMER c3_timer_start
									ENDIF
									IF c3_rand_escort = 3
										TASK_CAR_MISSION c3_clerk[1] c3_chase c3_truck MISSION_ESCORT_FRONT c3_clerk_cruise DRIVINGMODE_AVOIDCARS
										GET_GAME_TIMER c3_timer_start
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CAR_DEAD c3_truck
		AND NOT IS_CHAR_DEAD c3_clerk[0]
			GET_CAR_SPEED c3_truck c3_truck_speed
			IF c3_truck_speed < 3.0
				GET_SCRIPT_TASK_STATUS c3_clerk[0] TASK_DRIVE_BY c3_task_status
				IF c3_task_status = FINISHED_TASK
					SET_CHAR_ACCURACY c3_clerk[0] 40
					TASK_DRIVE_BY c3_clerk[0] catalina -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 100
					SET_CURRENT_CHAR_WEAPON c3_clerk[0] WEAPONTYPE_MICRO_UZI
				ENDIF
			ENDIF
		ENDIF

		// Fail if Trailer is detached...
		IF NOT IS_TRAILER_ATTACHED_TO_CAB c3_trailer c3_truck
			IF IS_CHAR_IN_CAR catalina c3_truck
				IF NOT IS_CAR_UPSIDEDOWN c3_truck
					DO_FADE 50 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					IF NOT IS_CAR_DEAD c3_truck
						SET_PLAYER_CONTROL player1 OFF
						SWITCH_WIDESCREEN ON
						SET_CAR_TEMP_ACTION c3_truck TEMPACT_HANDBRAKESTRAIGHT 2000
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS c3_truck 2.5 4.0 0.0 c3_cam_x[8] c3_cam_y[8] c3_cam_z[8]
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS c3_truck 2.2 3.0 0.0 c3_cam_look_x[8] c3_cam_look_y[8] c3_cam_look_z[8]
						SET_FIXED_CAMERA_POSITION c3_cam_x[8] c3_cam_y[8] c3_cam_z[8] 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT c3_cam_look_x[8] c3_cam_look_y[8] c3_cam_look_z[8] JUMP_CUT
						IF NOT IS_CAR_DEAD c3_chase
							IF NOT IS_CAR_ON_SCREEN c3_chase
								DELETE_CAR c3_chase
							ENDIF
						ENDIF
						GET_GAME_TIMER c3_timer_start
						c3_cut = 0
						GOTO c3_cat_fail_cut
			  		ENDIF
				ELSE
					DO_FADE 300 FADE_IN
					CLEAR_PRINTS
					PRINT_NOW ( CAT3_08 ) 5000 1
					GOTO mission_failed_cat3
				ENDIF
			ELSE
				PRINT_NOW ( CAT3_08 ) 5000 1
				GOTO mission_failed_cat3
			ENDIF
		ENDIF

		// Check that Player has made it intact to the destination...
		IF IS_CHAR_IN_CAR catalina c3_truck
		AND IS_CHAR_IN_CAR scplayer c3_truck
			IF LOCATE_CAR_3D c3_truck c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z 5.0 5.0 5.0 TRUE
			OR LOCATE_CAR_3D c3_trailer c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z 5.0 5.0 5.0 FALSE
				SET_PLAYER_CONTROL player1 OFF
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				GOSUB c3_exp_car_create
//				IF IS_CAR_DEAD c3_chase
//					IF NOT IS
//					DELETE_CHAR c3_clerk[0]
//					DELETE_CHAR c3_clerk[1]
//				ENDIF
				LOAD_SCENE -76.1314 -1131.4170 0.0782
				SWITCH_WIDESCREEN ON
				REMOVE_BLIP c3_gas_loc_blip
				CLEAR_PRINTS
				REQUEST_CAR_RECORDING 199
				WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 199
					WAIT 0
				ENDWHILE
				SET_FIXED_CAMERA_POSITION -81.2678 -1141.7351 3.8990 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -81.7168 -1140.8417 3.8854 JUMP_CUT
				IF NOT IS_CHAR_DEAD catalina
				AND NOT IS_CHAR_DEAD scplayer
				AND NOT IS_CAR_DEAD c3_truck
					FREEZE_CAR_POSITION c3_truck TRUE
					LOCK_CAR_DOORS c3_truck CARLOCK_LOCKOUT_PLAYER_ONLY
					WARP_CHAR_FROM_CAR_TO_COORD catalina -71.5991 -1134.9524 0.0781 
					SET_CHAR_HEADING catalina 63.1513
					WARP_CHAR_FROM_CAR_TO_COORD scplayer -74.2582 -1130.5261 0.0781  
					SET_CHAR_HEADING scplayer 135.9294
				ENDIF
				GOSUB c3_buyer_create
				CLEAR_PRINTS
				GET_GAME_TIMER c3_timer_start
				
				c3_cut = -4
				c3_stage = 5
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
	SET_PLAYER_CONTROL player1 OFF
	DO_FADE 500 FADE_OUT
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	GOSUB c3_exp_car_create
	LOAD_SCENE -76.1314 -1131.4170 0.0782
	SWITCH_WIDESCREEN ON
	REMOVE_BLIP c3_gas_loc_blip
	CLEAR_PRINTS
	REQUEST_CAR_RECORDING 199
	WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 199
		WAIT 0
	ENDWHILE
	SET_FIXED_CAMERA_POSITION -81.2678 -1141.7351 3.8990 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -81.7168 -1140.8417 3.8854 JUMP_CUT

	IF NOT IS_CHAR_DEAD catalina
	AND NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD c3_truck
		SET_CAR_COORDINATES  c3_truck c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z
		WARP_CHAR_FROM_CAR_TO_COORD catalina -71.5991 -1134.9524 0.0781 
		SET_CHAR_HEADING catalina 63.1513
		WARP_CHAR_FROM_CAR_TO_COORD scplayer -74.2582 -1130.5261 0.0781  
		SET_CHAR_HEADING scplayer 135.9294
		
	ENDIF
	GOSUB c3_buyer_create
	CLEAR_HELP
	CLEAR_PRINTS
	GET_GAME_TIMER c3_timer_start
	c3_cut = -4
	c3_stage = 5
ENDIF

// End Cutscene
IF c3_stage = 5
	IF NOT IS_CHAR_DEAD catalina
	AND NOT IS_CHAR_DEAD c3_buyer
	AND NOT IS_CAR_DEAD c3_truck
		IF IS_GROUP_MEMBER catalina players_group
			REMOVE_CHAR_FROM_GROUP catalina
		ENDIF
		IF c3_cut = -4
			IF NOT IS_CAR_DEAD c3_chase
				SET_CAR_PROOFS c3_chase FALSE FALSE FALSE FALSE FALSE
				START_PLAYBACK_RECORDED_CAR c3_chase 199
				GET_GAME_TIMER c3_timer_start
				DO_FADE 250 FADE_IN
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				c3_cut = -3
			ELSE
				c3_cut = -1 // Skip this section if clerks are dead.
			ENDIF
		ENDIF
		IF c3_cut = -3
			IF NOT IS_CAR_DEAD c3_chase
				GET_GAME_TIMER c3_timer_end
				c3_timer_diff = c3_timer_end - c3_timer_start
				IF c3_timer_diff > 2000
					SET_FIXED_CAMERA_POSITION -107.3115 -1128.8082 2.6543 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -106.7880 -1129.6519 2.7731 JUMP_CUT
					SET_CAR_HEALTH c3_chase 5
					c3_cut = -21
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = -21
			IF NOT IS_CAR_DEAD c3_chase
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR c3_chase
					SET_FIXED_CAMERA_POSITION -108.8537 -1168.3424 3.0287 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -108.0611 -1167.7360 3.0908 JUMP_CUT
					ADD_EXPLOSION -92.24 -1161.46 2.5 EXPLOSION_CAR //1.0
					EXPLODE_CAR_IN_CUTSCENE c3_chase
					GET_GAME_TIMER c3_timer_start
					c3_cut = -22
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = -22
			IF c3_timer_diff > 1000
				IF NOT IS_CAR_DEAD c3_exp_car
					SET_CAR_PROOFS c3_exp_car FALSE FALSE FALSE FALSE FALSE
					EXPLODE_CAR_IN_CUTSCENE c3_exp_car
					c3_cut = -2
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = -2
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 3000
			   	DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				c3_cut = -1
			ENDIF
		ENDIF

		IF c3_cut = -1
			SET_FIXED_CAMERA_POSITION c3_cam_x[5] c3_cam_y[5] c3_cam_z[5] 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT c3_cam_look_x[5] c3_cam_look_y[5] c3_cam_look_z[5] JUMP_CUT
			IF NOT IS_CHAR_DEAD	c3_clerk[0]
			AND	NOT IS_CHAR_DEAD c3_clerk[1]
			AND NOT IS_CAR_DEAD c3_Chase
				DELETE_CHAR	c3_clerk[0]
				DELETE_CHAR c3_clerk[1]
				DELETE_CAR c3_chase
			ENDIF
			//GOSUB c3_buyer_create
			LOAD_SCENE_IN_DIRECTION	c3_cam_x[5] c3_cam_y[5] c3_cam_z[5] 100.0
			CLEAR_PRINTS
			GET_GAME_TIMER c3_timer_start
			DO_FADE 500 FADE_IN
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			c3_cut = 0
		ENDIF
		IF c3_cut = 0
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 300
				IF c3_audio_playing = 0
					IF NOT IS_CHAR_DEAD catalina
					AND NOT IS_CHAR_DEAD c3_buyer
						OPEN_SEQUENCE_TASK c3_end_cut_1
							TASK_TURN_CHAR_TO_FACE_CHAR -1 c3_buyer
							TASK_GO_STRAIGHT_TO_COORD -1 -74.8954 -1133.7789 0.0781 PEDMOVE_WALK -1
							TASK_LOOK_AT_CHAR -1 c3_buyer -2
						CLOSE_SEQUENCE_TASK c3_end_cut_1
						PERFORM_SEQUENCE_TASK catalina c3_end_cut_1
						//PRINT_NOW ( CAT3_JA ) 3000 1 // CATALINA: Hello, Mr. Whittaker!
						c3_counter = 43	// CATALINA: Hello, Mr. Whittaker!
						GET_GAME_TIMER c3_timer_start
						c3_cut = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 1
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					DO_FADE 50 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE

					IF NOT IS_CHAR_DEAD c3_buyer
					AND NOT IS_CHAR_DEAD catalina
						TASK_LOOK_AT_CHAR c3_buyer catalina -2
						TASK_SCRATCH_HEAD c3_buyer
					ENDIF
					c3_counter = 44	// MR. WHITTAKER: Catalina! What have you brought me today?
					//PRINT_NOW ( CAT3_JB ) 4500 1 // MR. WHITTAKER: Catalina! What have you brought me today?
					GET_GAME_TIMER c3_timer_start
					LOAD_SCENE_IN_DIRECTION	c3_cam_x[6] c3_cam_y[6] c3_cam_z[6] 270.0
					SET_FIXED_CAMERA_POSITION c3_cam_x[6] c3_cam_y[6] c3_cam_z[6] 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT c3_cam_look_x[6] c3_cam_look_y[6] c3_cam_look_z[6] JUMP_CUT
					DO_FADE 50 FADE_IN
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					c3_cut = 2
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 2
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					IF NOT IS_CAR_DEAD c3_truck
					AND NOT IS_CHAR_DEAD c3_buyer
					AND NOT IS_CHAR_DEAD catalina
						CLEAR_LOOK_AT c3_buyer
						TASK_TURN_CHAR_TO_FACE_COORD catalina c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z
						TASK_TURN_CHAR_TO_FACE_COORD c3_buyer c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z
						TASK_LOOK_AT_VEHICLE catalina c3_truck -2
						TASK_LOOK_AT_VEHICLE c3_buyer c3_truck -2
						TASK_GO_STRAIGHT_TO_COORD c3_buyer -75.1023 -1134.8713 0.0781 PEDMOVE_WALK -1
						c3_counter = 45	// CATALINA: A rig and tanker, full to the brim with premium gas!
						//PRINT_NOW ( CAT3_JC ) 5000 1 // CATALINA: A rig and tanker, full to the brim with premium gas!
						GET_GAME_TIMER c3_timer_start
						c3_cut = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 3
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					IF NOT IS_CHAR_DEAD c3_buyer
						SET_FIXED_CAMERA_POSITION c3_cam_x[7] c3_cam_y[7] c3_cam_z[7] 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT c3_cam_look_x[7] c3_cam_look_y[7] c3_cam_look_z[7] JUMP_CUT
						TASK_TURN_CHAR_TO_FACE_CHAR c3_buyer catalina
						TASK_LOOK_AT_CHAR c3_buyer catalina -2
						TASK_SCRATCH_HEAD c3_buyer
						TASK_TURN_CHAR_TO_FACE_CHAR catalina c3_buyer
						TASK_LOOK_AT_CHAR catalina c3_buyer -2
					ENDIF
					c3_counter = 46	// MR. WHITTAKER: Never seen it, never saw you, never gave you this wad of cash!
					//PRINT_NOW ( CAT3_JD ) 6000 1 // MR. WHITTAKER: Never seen it, never saw you, never gave you this wad of cash!
					GET_GAME_TIMER c3_timer_start
					c3_cut = 4
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 4
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					c3_counter = 47	// CATALINA: Nice not doing business!
					//PRINT_NOW ( CAT3_JE ) 4000 1 // CATALINA: Nice not doing business!
					GET_GAME_TIMER c3_timer_start
					c3_cut = 5
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 5
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					IF NOT IS_CHAR_DEAD catalina
						CLEAR_LOOK_AT catalina
						TASK_GO_STRAIGHT_TO_COORD catalina -83.1314 -1129.4170 0.0782 PEDMOVE_WALK -1
						c3_counter = 48	// MR. WHITTAKER: Likewise. Now get out of here, before the cops come snooping.
						//PRINT_NOW ( CAT3_JF ) 6000 1 // MR. WHITTAKER: Likewise. Now get out of here, before the cops come snooping.
						GET_GAME_TIMER c3_timer_start
						c3_cut = 6
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 6
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					IF NOT IS_CHAR_DEAD c3_buyer
						CLEAR_LOOK_AT c3_buyer
						TASK_TURN_CHAR_TO_FACE_CHAR c3_buyer scplayer
						WAIT 100
						TASK_GO_STRAIGHT_TO_COORD scplayer -84.1314 -1128.4170 0.0782 PEDMOVE_WALK -1
						c3_counter = 51	// MR. WHITTAKER: You ever get hold of another rig, just drop it in –
						//PRINT_NOW ( CAT3_JG ) 4000 1 // MR. WHITTAKER: You ever get hold of another rig, just drop it in – 
						GET_GAME_TIMER c3_timer_start
						c3_cut = 7
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 7
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 2000
				IF c3_audio_playing = 0
					c3_counter = 52	// MR. WHITTAKER: I’ll always give a good price!
					//PRINT_NOW ( CAT3_JH ) 3000 1 // MR. WHITTAKER: I’ll always give a good price!
					GET_GAME_TIMER c3_timer_start
					c3_cut = 8
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 8
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 3000
				DO_FADE 100 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				GET_GAME_TIMER c3_timer_start
				IF NOT IS_CHAR_DEAD catalina
					IF IS_GROUP_MEMBER catalina players_group
						REMOVE_CHAR_FROM_GROUP catalina
					ENDIF
				ENDIF
				GOSUB c3_cat_bike_create
				CLEAR_CHAR_TASKS scplayer
				// 
				SET_FIXED_CAMERA_POSITION -86.1477 -1130.4454 0.9157 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -86.5058 -1129.5203 1.0417 JUMP_CUT
//				SET_PLAYER_CONTROL player1 ON
//				SWITCH_WIDESCREEN OFF
//				RESTORE_CAMERA_JUMPCUT
//				SET_CAMERA_BEHIND_PLAYER
				//GOTO mission_passed_cat3
				c3_cut = 9
			ENDIF
		ENDIF
		IF c3_cut = 9
			DO_FADE 100 FADE_IN
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			IF c3_audio_playing = 0
				c3_counter = 55
				c3_cut = 10
			ENDIF
		ENDIF
		IF c3_cut = 10
			IF c3_counter = 0
				IF NOT IS_CAR_DEAD c3_cat_bike
				AND NOT IS_CHAR_DEAD catalina
					IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR c3_cat_bike
						CLEAR_LOOK_AT catalina
						START_PLAYBACK_RECORDED_CAR c3_cat_bike 189
						c3_cut = 11
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF c3_cut = 11
			IF NOT IS_CAR_DEAD c3_cat_bike
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR c3_cat_bike
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					CLEAR_CHAR_TASKS scplayer
					DELETE_CHAR catalina
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					GOTO mission_passed_cat3
				ENDIF
			ENDIF
		ENDIF

			

		IF c3_cut > 0
			IF IS_BUTTON_PRESSED PAD1 CROSS 
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				IF NOT IS_CHAR_DEAD catalina
					IF NOT IS_GROUP_MEMBER catalina players_group
						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
						SET_GROUP_MEMBER players_group catalina
					ENDIF
				ENDIF
				CLEAR_CHAR_TASKS scplayer
				DELETE_CHAR catalina

				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				GOTO mission_passed_cat3
			ENDIF
		ENDIF
	ENDIF
ENDIF

// ---- Checks
IF c3_stage > 0
AND c3_stage < 5
	GOSUB c3_player_group_check
ENDIF

IF c3_stage > 1
AND c3_stage < 5
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD c3_truck
		IF c3_in_car = 0
			IF c3_grouped = 1
				IF NOT c3_stage = 1
				AND NOT	c3_stage = 3
					PRINT_NOW ( CAT3_10 ) 100 1
				ENDIF
				IF IS_CHAR_IN_CAR scplayer c3_truck
					IF NOT c3_stage = 1
					AND NOT	c3_stage = 3
						CLEAR_PRINTS
					ENDIF
					REMOVE_BLIP c3_truck_blip
					IF c3_stage = 2
						IF NOT IS_CAR_DEAD c3_trailer
							ADD_BLIP_FOR_CAR c3_trailer c3_trailer_blip
							SET_BLIP_AS_FRIENDLY c3_trailer_blip TRUE
						ENDIF
					ENDIF
					IF c3_stage = 3
					OR c3_stage = 4
						REMOVE_BLIP c3_gas_loc_blip
						ADD_BLIP_FOR_COORD c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z c3_gas_loc_blip
						SET_BLIP_AS_FRIENDLY c3_gas_loc_blip TRUE
						CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
						DISPLAY_ONSCREEN_COUNTER_WITH_STRING c3_trailer_health_display COUNTER_DISPLAY_BAR CAT3_11
						//SET_ONSCREEN_COUNTER_COLOUR c3_trailer_health_display HUD_COLOUR_RED
					ENDIF
					c3_in_car = 1
				ENDIF
			ENDIF
		ENDIF
		IF c3_in_car = 1
			IF NOT IS_CHAR_IN_CAR scplayer c3_truck
				IF c3_grouped = 1
					ADD_BLIP_FOR_CAR c3_truck c3_truck_blip
					SET_BLIP_AS_FRIENDLY c3_truck_blip TRUE
				ENDIF
				IF c3_stage = 2
					REMOVE_BLIP c3_trailer_blip
				ENDIF
				IF c3_stage = 3
				OR c3_stage = 4
					REMOVE_BLIP c3_gas_loc_blip
					CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
				ENDIF
				IF c3_stage = 4
					IF NOT IS_CHAR_DEAD	c3_clerk[0]
					 	SET_CHAR_ACCURACY c3_clerk[0] 50
						TASK_DRIVE_BY c3_clerk[0] scplayer -1 0.0 0.0 0.0 150.0 DRIVEBY_AI_ALL_DIRN TRUE 60
					ENDIF
				ENDIF
				c3_in_car = 0
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF IS_CHAR_DEAD catalina
OR IS_CHAR_IN_WATER catalina
	CLEAR_PRINTS
	PRINT_NOW ( CAT3_06 ) 10000 1 // Catalina didn't make it.
	GOTO mission_failed_cat3
ENDIF

IF c3_stage < 5
	IF IS_CAR_DEAD c3_trailer
		CLEAR_PRINTS
		PRINT_NOW ( CAT3_07 ) 5000 1
		GOTO mission_failed_cat3
	ENDIF
ENDIF
IF c3_stage < 5
	IF IS_CAR_DEAD c3_truck
		CLEAR_PRINTS
		PRINT_NOW ( CAT3_09 ) 5000 1
		GOTO mission_failed_cat3
	ENDIF
ENDIF

GOTO c3_main_loop 
// ------------------------------------------------------------------------------------------------
// Tanker disconnected cutscene...
c3_cat_fail_cut:

WAIT 0
// ---- Load & Play Dialogue...
IF NOT c3_counter = 0
	IF c3_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED c3_alt_slot
			CLEAR_MISSION_AUDIO c3_alt_slot
		ENDIF
		c3_audio_playing = 1
	ENDIF

	IF c3_audio_playing = 1
		LOAD_MISSION_AUDIO c3_audio_slot c3_audio[c3_counter]
		GOSUB c3_dialogue_pos
		
		c3_audio_playing = 2
	ENDIF

	IF c3_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED c3_audio_slot
			IF NOT c3_audio_char = 0
				IF NOT IS_CHAR_DEAD	c3_audio_char
					START_CHAR_FACIAL_TALK c3_audio_char 10000
					//ATTACH_MISSION_AUDIO_TO_CHAR c3_audio_slot c3_audio_char
				ENDIF
			ENDIF
			PLAY_MISSION_AUDIO c3_audio_slot
			PRINT_NOW $c3_text[c3_counter] 10000 1
			c3_audio_playing = 3
		ENDIF
	ENDIF

	IF c3_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED c3_audio_slot
			CLEAR_THIS_PRINT $c3_text[c3_counter]
			IF NOT c3_audio_char = 0
				IF NOT IS_CHAR_DEAD	c3_audio_char
					STOP_CHAR_FACIAL_TALK c3_audio_char
				ENDIF
			ENDIF
			IF c3_audio_slot = 1
				c3_audio_slot = 2
				c3_alt_slot = 1
			ELSE
				c3_audio_slot = 1
				c3_alt_slot = 2
			ENDIF
			c3_counter = 0
			c3_audio_playing = 0
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED c3_alt_slot
				IF c3_counter < 54
					c3_ahead_counter = c3_counter + 1
					LOAD_MISSION_AUDIO c3_alt_slot c3_audio[c3_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


	IF c3_cut = 0
		DO_FADE 50 FADE_IN
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
		c3_counter = 53
		//PRINT_NOW ( CATX_UJ ) 5000 1 // Carl Johnson, you are shit!
		GET_GAME_TIMER c3_timer_start
		IF NOT IS_CAR_DEAD c3_truck
		AND NOT IS_CHAR_DEAD catalina
			ATTACH_CAMERA_TO_VEHICLE c3_truck 1.0 8.0 1.5 0.0 0.0 1.0 0.0 JUMP_CUT
			CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
			c3_cut = 1
		ENDIF
	ENDIF
	IF c3_cut = 1
		IF NOT IS_CAR_DEAD c3_truck
		AND NOT IS_CHAR_DEAD catalina
		//IF NOT IS_CHAR_IN_CAR catalina c3_truck
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 1000
				IF c3_audio_playing = 0
					c3_counter = 54
					//PRINT_NOW ( CATX_UK ) 5000 1 // I cannot love a stupid man!
					ATTACH_CAMERA_TO_VEHICLE c3_truck 1.0 8.0 1.5 0.0 0.0 1.0 0.0 JUMP_CUT
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS c3_truck 4.5 10.0 0.0 c3_cam_x[8] c3_cam_y[8] c3_cam_z[8]
					TASK_LEAVE_CAR catalina	c3_truck
					GET_GAME_TIMER c3_timer_start
					c3_cut = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF c3_cut = 2
		IF c3_audio_playing = 0
			GET_GAME_TIMER c3_timer_end
			c3_timer_diff = c3_timer_end - c3_timer_start
			IF c3_timer_diff > 1000
				IF NOT IS_CHAR_DEAD catalina
					IF NOT IS_CAR_DEAD c3_truck
						IF NOT IS_CHAR_IN_CAR catalina c3_truck
							TASK_GO_STRAIGHT_TO_COORD catalina c3_cam_x[8] c3_cam_y[8] c3_cam_z[8] PEDMOVE_WALK -1
							IF c3_audio_playing = 0
								DO_FADE 50 FADE_OUT
								WHILE GET_FADING_STATUS
								WAIT 0
								ENDWHILE
								IF NOT IS_CHAR_DEAD catalina
									IF IS_GROUP_MEMBER catalina players_group
										REMOVE_CHAR_FROM_GROUP catalina
										DELETE_CHAR catalina 
										CLEAR_PRINTS
										c3_cut = 3
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	IF c3_cut = 3
		SWITCH_WIDESCREEN OFF
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		DO_FADE 50 FADE_IN
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE

		PRINT_NOW ( CAT3_08 ) 5000 1
		SET_PLAYER_CONTROL player1 ON
		GOTO mission_failed_cat3
	ENDIF


//-88.1347 -1127.3966 0.0847 348.4666 
//-86.1477 -1130.4454 0.9157 -86.5058 -1129.5203 1.0417 


GOTO c3_cat_fail_cut
// ------------------------------------------------------------------------------------------------
// Mission Failed
mission_failed_cat3:
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN
// ------------------------------------------------------------------------------------------------
// Mission Passed
mission_passed_cat3:

cat_counter ++

IF NOT IS_CAR_DEAD c3_truck
AND NOT IS_CAR_DEAD c3_trailer
	FREEZE_CAR_POSITION c3_truck FALSE
	DELETE_CAR c3_truck
	DELETE_CAR c3_trailer
ENDIF
START_NEW_SCRIPT trucking_loop
//IF cat_counter = 2
//AND bcesar_tiggered = 0
//	START_NEW_SCRIPT bcesar_mission_loop
//	REMOVE_BLIP bcesar_contact_blip
//	ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcesarX bcesarY bcesarZ cesar_blip_icon bcesar_contact_blip
//	bcesar_tiggered = 1
//ENDIF
IF cat_otb_banter = 1
	cat_otb_banter = 2
ENDIF

IF cat_liquor_banter = 1
cat_liquor_banter = 2
ENDIF


REMOVE_BLIP tucking_contact_blip
ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT truckX truckY truckZ RADAR_SPRITE_TRUCK tucking_contact_blip
PLAY_MISSION_PASSED_TUNE 1	
	REGISTER_MISSION_PASSED ( CAT_3 )
	PLAYER_MADE_PROGRESS 1
	PRINT_WITH_NUMBER_BIG ( M_PASS ) c3_pass 5000 1 //"Mission Passed!"
	ADD_SCORE player1 c3_pass
	CLEAR_WANTED_LEVEL player1
	flag_cat_mission3_passed = 1
RETURN
// ------------------------------------------------------------------------------------------------
// Mission Cleanup
mission_cleanup_cat3:
// ---- Blips
	REMOVE_BLIP c3_truck_blip
	REMOVE_BLIP c3_trailer_blip
	REMOVE_BLIP c3_chase_blip
	REMOVE_BLIP c3_gas_loc_blip
	REMOVE_BLIP catalina_blip

	CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
	CLEAR_MISSION_AUDIO 3
// ---- Models
	DELETE_CHAR c3_buyer
	DELETE_CAR c3_cat_bike
	//DELETE_CHAR catalina
	MARK_MODEL_AS_NO_LONGER_NEEDED HFYST
	MARK_MODEL_AS_NO_LONGER_NEEDED WMOST
	MARK_MODEL_AS_NO_LONGER_NEEDED BMYPOL2
	MARK_MODEL_AS_NO_LONGER_NEEDED PETRO
	MARK_MODEL_AS_NO_LONGER_NEEDED PETROTR
	MARK_MODEL_AS_NO_LONGER_NEEDED SABRE
	MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
	MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED BMOST
	MARK_MODEL_AS_NO_LONGER_NEEDED SADLER
	MARK_MODEL_AS_NO_LONGER_NEEDED SANCHEZ
	IF NOT IS_CHAR_DEAD catalina
		REMOVE_CHAR_ELEGANTLY catalina
	ENDIF
	UNLOAD_SPECIAL_CHARACTER 1
	REMOVE_ANIMATION CAR_CHAT
	REMOVE_ANIMATION MISC

	REMOVE_CAR_RECORDING 188
	REMOVE_CAR_RECORDING 189
	REMOVE_CAR_RECORDING 199

// ----	Clear Script Stuff
	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start
	DO_FADE 500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	MISSION_HAS_FINISHED
RETURN
// ------------------------------------------------------------------------------------------------
// ---- Sub-routines

c3_dialogue_pos:
	
	IF c3_counter = 1 // CATALINA: Hand over the takings, or I blow your fucking balls off!
	OR c3_counter = 5 // CATALINA: Suit yourself, macaron.
	OR c3_counter = 6 // CATALINA: Change of plan, Carl, we’re taking the tanker!
	OR c3_counter = 8 // CATALINA: Move it, you lazy bastard, get that tanker hooked up!
	OR c3_counter = 11 // CATALINA: Shut your spotty little mouth!
	OR c3_counter = 12 // CATALINA: Drive! I know a guy who’ll pay for this rig and its cargo!
		c3_audio_char = catalina
	ENDIF

	IF c3_counter = 16 // CATALINA: Follow me!
	OR c3_counter = 20 // CATALINA: Follow me!
	OR c3_counter = 21 // CATALINA: What is keeping you?
	OR c3_counter = 22 // CATALINA: C’mon, Carl, move it!
	OR c3_counter = 23 // CATALINA: Not far now!
	OR c3_counter = 24 // CATALINA: This way!
		c3_audio_char = catalina
	ENDIF

	IF c3_counter = 25 // CATALINA: Where have you been?
	OR c3_counter = 26 // CATALINA: I thought you said you could drive!
	OR c3_counter = 40 // CATALINA: Pull up in that depot up ahead!
	OR c3_counter = 41 // CATALINA: That's the place, up ahead!
	OR c3_counter = 42 // CATALINA: Yeehaa, that's the place, go, Carl, go!
	OR c3_counter = 43 // CATALINA: Hello, Mr. Whittaker!
		c3_audio_char = catalina
	ENDIF

	IF c3_counter = 45 // CATALINA: A rig and tanker, full to the brim with premium gas!
	OR c3_counter = 47 // CATALINA: Nice not doing business!
		c3_audio_char = catalina
	ENDIF
	
	IF c3_counter = 2  // CLERK 1: This here’s bulletproof glass!
	OR c3_counter = 3  // CLERK 1: So you can just fuck off, bitch, before I call the sheriff!
	OR c3_counter = 7  // CLERK 1: Hey! What you doing?
	OR c3_counter = 10  // CLERK 1: You won’t get away with this!
	OR c3_counter = 13  // CLERK 1: I ain’t losing another crappy job because of some crazy bitch!
	OR c3_counter = 14  // CLERK 1: C’mon, Derek, we’re going to stop those bastards!
		c3_audio_char = c3_clerk[0]
	ENDIF

	IF c3_counter = 17 // CLERK 1: Oh no you don’t, bitch!
	OR c3_counter = 18 // CLERK 1: C’mon, Derek, we’re going to be local heroes!
		c3_audio_char = c3_clerk[0]
	ENDIF

	IF c3_counter = 27 // CLERK 1: Keep her steady so I can get a good shot!
	OR c3_counter = 28 // CLERK 1: Pull alongside!
	OR c3_counter = 29 // CLERK 1: Keep up with them!
	OR c3_counter = 30 // CLERK 1: Get ahead of them!
		c3_audio_char = 0
	ENDIF	

	IF c3_counter = 31 // CLERK 1: Block their route!
	OR c3_counter = 32 // CLERK 1: Steady... steady...
	OR c3_counter = 15 // CLERK 2: But I don’t wanna!
		c3_audio_char = 0
	ENDIF

	IF c3_counter = 4 // CLERK 2: What you doing, man, just give her the cash!
	OR c3_counter = 19 // CLERK 2: I’d be happier staying here and stacking shelves!
		c3_audio_char = c3_clerk[1]
	ENDIF

	IF c3_counter = 33 // CLERK 2: It ain't worth it!
	OR c3_counter = 34 // CLERK 2: I'm scared!
	OR c3_counter = 35 // CLERK 2: This ain't my fight!
		c3_audio_char = 0
	ENDIF

	IF c3_counter = 36 // CLERK 2: We'll get ourselves killed!
	OR c3_counter = 37 // CLERK 2: I don't wanna do this!
	OR c3_counter = 38 // CLERK 2: Oh my god, I'm gonnna die!
	OR c3_counter = 39 // CLERK 2: That rig will crush us!
		c3_audio_char = 0
	ENDIF
	
	IF c3_counter = 44 // MR WHITTAKER: Catalina! What have you brought me today?
	OR c3_counter = 46 // MR WHITTAKER: Never seen it, never saw you, never gave you this wad of cash!
	OR c3_counter = 48 // MR WHITTAKER: Likewise. Now get out of here, before the cops come snooping.
	OR c3_counter = 49 // MR WHITTAKER: You ever get hold of another rig, just drop it in, 
	OR c3_counter = 50 // MR WHITTAKER: I'll always give a good price!
	OR c3_counter = 51 // MR WHITTAKER: You ever want to run some freight for me just drop in.
		c3_audio_char = c3_buyer
	ENDIF

	IF c3_counter = 52 // MR WHITTAKER: I've always got shit needs moving!
		c3_audio_char = c3_buyer
	ENDIF

	IF c3_counter = 9 // CARL: Yo, I’m on it, I’m on it!
		c3_audio_char = scplayer
	ENDIF
RETURN

c3_player_car_check:
// Get Player's car...
	IF NOT IS_CHAR_DEAD scplayer
		IF c3_in_car = 0
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN scplayer c3_player_car
				c3_in_car = 1
			ENDIF
		ENDIF
		IF c3_in_car = 1
			IF NOT IS_CHAR_IN_ANY_CAR scplayer
				c3_in_car = 0
			ENDIF
		ENDIF
	ENDIF
RETURN

c3_player_group_check:
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD catalina
		IF c3_grouped = 0
			IF c3_stage > 1
			AND c3_stage < 5
				IF NOT IS_GROUP_MEMBER catalina players_group
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer catalina 8.0 8.0 8.0 FALSE
						IF c3_stage = 2
							IF c3_in_car = 1
								IF NOT IS_CAR_DEAD c3_trailer
									REMOVE_BLIP	c3_trailer_blip
									ADD_BLIP_FOR_CAR c3_trailer c3_trailer_blip
									SET_BLIP_AS_FRIENDLY c3_trailer_blip TRUE
								ENDIF
							ENDIF
							IF c3_in_car = 0
								IF NOT IS_CAR_DEAD c3_truck
									REMOVE_BLIP c3_truck_blip
									ADD_BLIP_FOR_CAR c3_truck c3_truck_blip
									SET_BLIP_AS_FRIENDLY c3_truck_blip TRUE
								ENDIF
							ENDIF
						ENDIF
						IF c3_stage = 4
							IF c3_in_car = 1
								c3_dialogue_playing = 4
								REMOVE_BLIP c3_gas_loc_blip
								ADD_BLIP_FOR_COORD c3_gas_loc_x c3_gas_loc_y c3_gas_loc_z c3_gas_loc_blip
								CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
								DISPLAY_ONSCREEN_COUNTER_WITH_STRING c3_trailer_health_display COUNTER_DISPLAY_BAR CAT3_11
								//SET_ONSCREEN_COUNTER_COLOUR c3_trailer_health_display HUD_COLOUR_RED
							ENDIF
							IF c3_in_car = 0
								IF NOT IS_CAR_DEAD c3_truck
									c3_dialogue_playing = 4
									REMOVE_BLIP c3_truck_blip
									ADD_BLIP_FOR_CAR c3_truck c3_truck_blip
									SET_BLIP_AS_FRIENDLY c3_truck_blip TRUE
								ENDIF
							ENDIF
						ENDIF
						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
						SET_GROUP_MEMBER players_group catalina 
						REMOVE_BLIP catalina_blip
						IF NOT c3_stage = 1
						AND NOT c3_stage = 3
							CLEAR_PRINTS
						ENDIF
						c3_grouped = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF c3_grouped = 1
			IF NOT IS_GROUP_MEMBER catalina players_group
				IF c3_stage = 2
					REMOVE_BLIP c3_trailer_blip
					IF c3_in_car = 0
						REMOVE_BLIP c3_truck_blip
					ENDIF
				ENDIF
				IF c3_stage = 4
					CLEAR_ONSCREEN_COUNTER c3_trailer_health_display
					REMOVE_BLIP	c3_gas_loc_blip
					c3_dialogue_playing = 10
					IF c3_in_car = 0
						REMOVE_BLIP c3_truck_blip
					ENDIF
				ENDIF
				ADD_BLIP_FOR_CHAR catalina catalina_blip
				SET_BLIP_AS_FRIENDLY catalina_blip TRUE
				IF NOT c3_stage = 3
					PRINT_NOW ( CAT3_01 ) 5000 1
				ENDIF
				c3_grouped = 0
			ENDIF
		ENDIF
	ENDIF			
RETURN


}

/*

[SOUND_CAT3_01:CAT3]
~s~Go and pick up ~b~Catalina~s~.

[SOUND_CAT3_02:CAT3]
~s~Get into the ~b~rig~s~!

[SOUND_CAT3_03:CAT3]
Position the cab in front of the trailer. 

[SOUND_CAT3_04:CAT3]
Look behind by pressing L2 and R2 together, or use the right thumbstick to manoeuvre the camera for a better view. To attach to a trailer, slowly back the cab into the front of the trailer.

[SOUND_CAT3_05:CAT3]
~s~Drive the rig out to Catalina's ~y~buyer~s~. Be careful not to disconnect the trailer.

[SOUND_CAT3_06:CAT3]
~r~Catalina didn't make it.

[SOUND_CAT3_07:CAT3]
~r~The tanker was destroyed!

[SOUND_CAT3_08:CAT3]
~r~The tanker disconnected!

[SOUND_CAT3_09:CAT3]
~r~The rig was destroyed!

[SOUND_CAT3_10:CAT3]
~s~Get back in the ~b~cab~s~!

[SOUND_CAT3_11:CAT3]
TANKER~N~HEALTH~N~


[SOUND_CAT3_AA:CAT3]	
Hand over the takings, or I blow your fucking balls off!
		
[SOUND_CAT3_AB:CAT3]	
This here's bulletproof glass!

[SOUND_CAT3_AC:CAT3]	
So you can just fuck off, bitch, before I call the sheriff!

[SOUND_CAT3_AD:CAT3]	
What you doing, man, just give her the cash!

[SOUND_CAT3_AE:CAT3]	
Suit yourself, maricon.

[SOUND_CAT3_AF:CAT3]	
Change of plan, Carl, we're taking the tanker!
	
[SOUND_CAT3_AG:CAT3]	
Hey! What you doing?
	
[SOUND_CAT3_CA:CAT3]	
Drive! I know a guy who'll pay for this rig and its cargo!
	
[SOUND_CAT3_CB:CAT3]	
I ain't losing another crappy job because of some crazy bitch!

[SOUND_CAT3_CC:CAT3]	
C'mon, Derek, we're going to stop those bastards!
	
[SOUND_CAT3_CD:CAT3]	
But I don't wanna!
	
[SOUND_CAT3_FA:CAT3]	
Keep her steady so I can get a good shot!

[SOUND_CAT3_FB:CAT3]	
Pull alongside!

[SOUND_CAT3_FC:CAT3]	
Keep up with them!

[SOUND_CAT3_FD:CAT3]	
Get ahead of them!

[SOUND_CAT3_FE:CAT3]	
Block their route!

[SOUND_CAT3_FF:CAT3]	
Steady... steady...
	
[SOUND_CAT3_GA:CAT3]	
It ain't worth it!

[SOUND_CAT3_GB:CAT3]	
I'm scared, man!

[SOUND_CAT3_GC:CAT3]	
This ain't my fight!

[SOUND_CAT3_GD:CAT3]	
We'll get ourselves killed!

[SOUND_CAT3_GE:CAT3]	
I don't wanna do this!

[SOUND_CAT3_GF:CAT3]	
Oh my god, I'm gonnna die!

[SOUND_CAT3_GG:CAT3]	
That rig will crush us!
	
[SOUND_CAT3_JA:CAT3]	
Hello, Mr. Whittaker!
	
[SOUND_CAT3_JB:CAT3]	
Catalina! What have you brought me today?

[SOUND_CAT3_JC:CAT3]	
A rig and tanker, full to the brim with premium gas!

[SOUND_CAT3_JD:CAT3]	
Never seen it, never saw you, never gave you this wad of cash!
	
[SOUND_CAT3_JE:CAT3]	
Nice not doing business!

[SOUND_CAT3_JF:CAT3]	
Likewise. Now get out of here, before the cops come snooping.

[SOUND_CAT3_JG:CAT3]	
You ever want to run some freight for me just drop in 

[SOUND_CAT3_JH:CAT3]	
I've always got shit needs moving!

[SOUND_CAT3_XA:CAT3]
Carl Johnson, you are shit!

[SOUND_CAT3_XB:CAT3]
I cannot love a stupid man!

{Carl & Catalina hold up gas station} – SCRIPTED CUT			
		
[SOUND_CAT3_AA]	
CATALINA: Hand over the takings, or I blow your fucking balls off!
		
[SOUND_CAT3_AB]	
CLERK 1: This here’s bulletproof glass!

[SOUND_CAT3_AC]	
CLERK 1: So you can just fuck off, bitch, before I call the sheriff!

[SOUND_CAT3_AD]	
CLERK 2: What you doing, man, just give her the cash!

[SOUND_CAT3_AE]	
CATALINA: Suit yourself, macaron.

[SOUND_CAT3_AF]	
CATALINA: Change of plan, Carl, we’re taking the tanker!
	
[SOUND_CAT3_AG]	
CLERK 1: Hey! What you doing?
	
{Player has to hook-up tanker – Cashier shouts threats} – INSTRUCTIONS			
[SOUND_CAT3_BA]	
CATALINA: Move it, you lazy bastard, get that tanker hooked up!
	
[SOUND_CAT3_BB]	
CARL: Yo, I’m on it, I’m on it!
	
[SOUND_CAT3_BC]	
CASHIER 1: You won’t get away with this!
	
[SOUND_CAT3_BD]	
CATALINA: Shut your spotty little mouth!
	
{Player has hooked up Tanker – Catalina gets in the cab} – INSTRUCTIONS	
		
[SOUND_CAT3_CA]	
CATALINA: Drive! I know a guy who’ll pay for this rig and its cargo!
	
[SOUND_CAT3_CB]	
CLERK 1: I ain’t losing another crappy job because of some crazy bitch!

[SOUND_CAT3_CC]	
CLERK 1: C’mon, Derek, we’re going to stop those bastards!
	
[SOUND_CAT3_CD]	
CLERK 2: But I don’t wanna!
	
{Player has hooked up Tanker – Catalina grabs a bike to lead the way} – INSTRUCTIONS			
	
	
{Catalina waiting for Player on bike – ‘hurry up/this way’} – variations
			
	
[SOUND_CAT3_EA]	
CATALINA: Follow me!

[SOUND_CAT3_EB]	
CATALINA: What is keeping you?

[SOUND_CAT3_EC]	
CATALINA: C’mon, Carl, move it!

[SOUND_CAT3_ED]	
CATALINA: Not far now!

[SOUND_CAT3_EE]	
CATALINA: This way!

[SOUND_CAT3_EF]	
CATALINA: Where have you been?

[SOUND_CAT3_EG]	
CATALINA: I thought you said you could drive!
	
{Clerk 1 & 2 give chase in a car.} – variations	
		
	
[SOUND_CAT3_FA]	
CLERK 1: Keep her steady so I can get a good shot!

[SOUND_CAT3_FB]	
CLERK 1: Pull alongside!

[SOUND_CAT3_FC]	
CLERK 1: Keep up with them!

[SOUND_CAT3_FD]	
CLERK 1: Get ahead of them!

[SOUND_CAT3_FE]	
CLERK 1: Block their route!

[SOUND_CAT3_FF]	
CLERK 1: Steady… steady…
	
	
[SOUND_CAT3_GA]	
CLERK 2: It ain’t worth it!

[SOUND_CAT3_GB]	
CLERK 2: I’m scared, man!

[SOUND_CAT3_GC]	
CLERK 2: This ain’t my fight!

[SOUND_CAT3_GD]	
CLERK 2: We’ll get ourselves killed!

[SOUND_CAT3_GE]	
CLERK 2: I don’t wanna do this!

[SOUND_CAT3_GF]	
CLERK 2: Oh my god, I’m gonnna die!

[SOUND_CAT3_GG]	
CLERK 2: That rig will crush us!
	
{Player is approaching drop-off location} – INSTRUCTIONS - variations
	
[SOUND_CAT3_HA]	
CATALINA: Pull up in that depot up ahead!

[SOUND_CAT3_HB]	
CATALINA: That’s the place, up ahead!

[SOUND_CAT3_HC]	
CATALINA: Yeehaa, that’s the place – go, Carl, go!
	
{Rig is accepted by depot owner} – SCRIPTED CUT	
	
[SOUND_CAT3_JA]	
CATALINA: Hello, Mr. Whittaker!
	
[SOUND_CAT3_JB]	
MR. WHITTAKER: Catalina! What have you brought me today?

[SOUND_CAT3_JC]	
CATALINA: A rig and tanker, full to the brim with premium gas!

[SOUND_CAT3_JD]	
MR. WHITTAKER: Never seen it, never saw you, never gave you this wad of cash!
	
[SOUND_CAT3_JE]	
CATALINA: Nice not doing business!

[SOUND_CAT3_JF]	
MR. WHITTAKER: Likewise. Now get out of here, before the cops come snooping.

[SOUND_CAT3_JG]	
MR. WHITTAKER: You ever get hold of another rig, just drop it in – 

[SOUND_CAT3_JH]	
MR. WHITTAKER: I’ll always give a good price!




[C3_0:CAT3]
~s~Go and pick up Catalina

[C3_1:CAT3]
Catalina: Go and get a car!

[C3_2:CAT3]
Catalina: About time you showed up.

[C3_3:CAT3]
Catalina: There's a gas station I know with a safe in the back room.

[C3_4:CAT3]
Catalina: We can rob it if we get the clerk out of the store.

[C3_4A:CAT3]
Catalina: He only leaves the store for truckers. And I know where we can find a truck.

[C3_5:CAT3]
Catalina: This will do nicely.

[C3_6:CAT3]
~s~Wait for Catalina.

[C3_7:CAT3]
Catalina: We have business to take care of, let's go.

[C3_8:CAT3]
Catalina: I need you to distract the clerk while I rob the place.

[C3_9:CAT3]
Catalina: Be careful, he's got an itchy trigger finger.

[C3_10:CAT3]
~s~Distract the clerk, don't let Catalina get caught.

[C3_11:CAT3]
CATALINA: Give me all the money in the till or I shoot.

[C3_12:CAT3]
CLERK: Not a chance! And this glass is bulletproof. How you like them apples? 

[C3_13:CAT3]
CATALINA: Ok, keep the cash, there's been a change of plan.

[C3_14:CAT3]
CLERK: You give up pretty easy, amateur!

[C3_15:CAT3]
CATALINA: Carl! Hook up that rig and see how badly this punk wants to keep his gas.

[C3_15A:CAT3]
CLERK: Damn you! I want my gas back!

[C3_15B:CAT3]
CLERK: If I can't have it, no-one can!

[C3_15C:CAT3]
~s~Get into the rig!

[C3_15H:CAT3]
To connect to the tanker line up the rig directly in front of it.

[C3_15I:CAT3]
Reverse the rig until the connection is made.

[C3_16:CAT3]
CATALINA: I know a guy who'll buy the whole rig.

[C3_17:CAT3]
~s~Drive the rig out to Catalina's buyer. Be careful not to jacknife it.

[C3_18:CAT3]
~s~Catalina didn't make it.

[C3_19:CAT3]
~s~Catalina has all the cash. Head back and pick her up.

[C3_20:CAT3]
~s~Head back to the hideout!

[C3_21:CAT3]
~s~The tanker was destroyed!

[C3_22:CAT3]
~s~The tanker disconnected!

[C3_23:CAT3]
~s~The rig was destroyed!

[C3_24:CAT3]
~s~Get back in the cab!

[C3_H:CAT3]
GAS:

*/