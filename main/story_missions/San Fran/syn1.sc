MISSION_START
// ------------------------------------------------------------------------------------------------
// Syndicate Mission 1: Recon - Photograph Syndicate Members : T-Bone, Jizzy, Toreno, Ryder

{

SCRIPT_NAME syn1

// Begin...
GOSUB mission_start_syn1

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_syn1
	ENDIF

GOSUB mission_cleanup_syn1

MISSION_END
// ------------------------------------------------------------------------------------------------
// Variables
// ---- Flags
	LVAR_INT sy1_stage sy1_cut sy1_temp_flag sy1_fx_flag sy1_skip
	LVAR_INT sy1_mobile	sy1_photo_targets sy1_anim_flag sy1_meet

// ---- Blips

// ---- Timers / Counters
	LVAR_INT syn1_timer
	LVAR_INT sy1_timer_diff sy1_timer_end sy1_timer_start
	LVAR_FLOAT sy1_anim_time
// ---- Coords
	LVAR_FLOAT sy1_park_x sy1_park_y sy1_park_z
	LVAR_INT sy1_park_blip sy1_vantage_blip

// ---- Cameras
	LVAR_FLOAT sy1_cam_x[15] sy1_cam_y[15] sy1_cam_z[15]
	LVAR_FLOAT sy1_cam_look_x[15] sy1_cam_look_y[15] sy1_cam_look_z[15]

	LVAR_FLOAT sy1_fail_cam_x sy1_fail_cam_y sy1_fail_cam_z

	LVAR_FLOAT sy1_fov
	CONST_FLOAT	sy1_ideal_fov 20.0
// ---- Objects

// ---- Dialogue 
	LVAR_INT sy1_dialogue sy1_audio_char
	LVAR_INT sy1_text_timer_diff sy1_text_timer_end sy1_text_timer_start
	LVAR_TEXT_LABEL sy1_text[60]
	LVAR_INT sy1_audio[60] sy1_audio_slot sy1_alt_slot sy1_counter sy1_ahead_counter sy1_audio_playing 
	LVAR_INT sy1_audio_seq
// ---- Photo Stuff
	LVAR_FLOAT sy1_lower_heading sy1_upper_heading

// ---- Sequences
	LVAR_INT sy1_down_stairs	sy1_player_confront	sy1_cesar_enter_heli sy1_dec
	LVAR_INT sy1_ryder_exit_car	sy1_balla_1_meet sy1_jizzy_leave_car
	LVAR_FLOAT sy1_pilot_confront_x[3] sy1_pilot_confront_y[3] sy1_pilot_confront_z[3]

// ---- Offsets
   	LVAR_FLOAT sy1_min_x sy1_min_y sy1_min_z sy1_offset_x sy1_offset_y sy1_offset_z
	LVAR_FLOAT sy1_max_x sy1_max_y sy1_max_z

// ------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------
// -------- Entities
// ---- Player


	LVAR_INT sy1_player_status sy1_player_car sy1_in_car sy1_grouped sy1_smoke_fx[2] sy1_player_car_class
	LVAR_FLOAT sy1_player_x sy1_player_y sy1_player_z sy1_player_h 
// ---- Cesar
	LVAR_INT sy1_cesar sy1_cesar_car sy1_cesar_car_health sy1_cesar_blip sy1_cesar_status sy1_cesar_car_blip
	LVAR_FLOAT sy1_cesar_x sy1_cesar_y sy1_cesar_z
	VAR_FLOAT sy1_cesar_car_x sy1_cesar_car_y sy1_cesar_car_z sy1_cesar_car_h
	LVAR_INT sy1_cesar_stairs

	sy1_cesar_model_load:
		REQUEST_MODEL SAVANNA
		LOAD_SPECIAL_CHARACTER 1 cesar
		REQUEST_ANIMATION BOMBER
		LOAD_ALL_MODELS_NOW										    
		WHILE NOT HAS_MODEL_LOADED SAVANNA
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
		OR NOT HAS_ANIMATION_LOADED BOMBER
        WAIT 0
		ENDWHILE

	RETURN
	ADD_BLIP_FOR_COORD sy1_cesar_car_x sy1_cesar_car_y sy1_cesar_car_z sy1_cesar_blip
	ADD_BLIP_FOR_COORD sy1_park_x sy1_park_y sy1_park_z sy1_park_blip	
	sy1_cesar_car_create:
		sy1_cesar_car_x = -54.0
		sy1_cesar_car_y = -432.0
		sy1_cesar_car_z = 1.0
		sy1_cesar_car_h	= 259.0
		CLEAR_AREA sy1_cesar_car_x sy1_cesar_car_y sy1_cesar_car_z 100.0 TRUE 
		CUSTOM_PLATE_FOR_NEXT_CAR SAVANNA &_LVA4L__
		CREATE_CAR SAVANNA sy1_cesar_car_x sy1_cesar_car_y sy1_cesar_car_z sy1_cesar_car
		CREATE_CHAR PEDTYPE_CIVMALE SPECIAL01 -51.6293 -430.5620 0.5721 sy1_cesar
		//CREATE_CHAR_INSIDE_CAR sy1_cesar_car PEDTYPE_CIVMALE SPECIAL01 sy1_cesar
		SET_CAR_HEADING sy1_cesar_car sy1_cesar_car_h
		SET_CHAR_HEADING sy1_cesar 154.6720
		SET_CHAR_NEVER_TARGETTED sy1_cesar TRUE
		//SET_LOAD_COLLISION_FOR_CAR_FLAG sy1_cesar_car FALSE
		SUPPRESS_CAR_MODEL SAVANNA
		CHANGE_CAR_COLOUR sy1_cesar_car 3 3
		REMOVE_BLIP	sy1_cesar_blip
		ADD_BLIP_FOR_CHAR sy1_cesar sy1_cesar_blip
		SET_BLIP_AS_FRIENDLY sy1_cesar_blip TRUE
		
	RETURN
		CREATE_CHAR_INSIDE_CAR sy1_cesar_car PEDTYPE_CIVMALE SPECIAL01 sy1_audio_char
		ADD_BLIP_FOR_CAR sy1_cesar_car sy1_cesar_car_blip
// Dummy car
	LVAR_INT sy1_dummy_car
	LVAR_FLOAT sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z sy1_dummy_car_h 
	sy1_dummy_car_create:
		REQUEST_MODEL MTBIKE
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED MTBIKE
		WAIT 0
		ENDWHILE
		CREATE_CAR MTBIKE sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z sy1_dummy_car
		SET_CAR_HEADING	sy1_dummy_car sy1_dummy_car_h
		FREEZE_CAR_POSITION sy1_dummy_car TRUE
		SET_CAR_VISIBLE sy1_dummy_car FALSE
		SET_HEADING_FOR_ATTACHED_PLAYER player1 135.0 135.0
		ATTACH_CHAR_TO_BIKE scplayer sy1_dummy_car -0.2 0.5 0.0 FACING_LEFT 110.0 70.0 WEAPONTYPE_CAMERA
		SET_HEADING_FOR_ATTACHED_PLAYER player1 135.0 135.0
		SET_RADIO_CHANNEL RS_OFF
		SET_CAR_ENGINE_ON sy1_dummy_car FALSE
	RETURN
// ---- Ryder and some Ballas
	LVAR_INT sy1_ryder sy1_ryder_car sy1_ryder_blip sy1_balla
	LVAR_INT sy1_ryder_status sy1_ryder_progress sy1_ryder_walk
	LVAR_FLOAT sy1_ryder_x sy1_ryder_y sy1_ryder_z sy1_ryder_h
	LVAR_FLOAT sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z sy1_ryder_car_h

	sy1_ryder_car_create:
		REQUEST_MODEL PICADOR
		REQUEST_CAR_RECORDING 190
		LOAD_SPECIAL_CHARACTER 2 ryder2
		REQUEST_MODEL BALLAS1
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED PICADOR
		OR NOT HAS_MODEL_LOADED BALLAS1
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 190
        WAIT 0
		ENDWHILE

		sy1_ryder_car_x = -2120.3997
		sy1_ryder_car_y = -2448.4578
		sy1_ryder_car_z = 29.4688 
		sy1_ryder_car_h	= 137.4828
		CUSTOM_PLATE_FOR_NEXT_CAR PICADOR &_SHERM__ 

		CLEAR_AREA sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z 5.0 TRUE
		CREATE_CAR PICADOR sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z sy1_ryder_car
		CHANGE_CAR_COLOUR sy1_ryder_car 84 84
		SET_CAR_PROOFS sy1_ryder_car TRUE TRUE TRUE TRUE TRUE
		SET_CAR_HEADING	sy1_ryder_car sy1_ryder_car_h
		CREATE_CHAR_INSIDE_CAR sy1_ryder_car PEDTYPE_CIVMALE SPECIAL02 sy1_ryder
		CREATE_CHAR_AS_PASSENGER sy1_ryder_car PEDTYPE_CIVMALE BALLAS1 0 sy1_balla
		LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY sy1_dec
		SET_CHAR_DECISION_MAKER sy1_ryder sy1_dec
 		
	RETURN

// ---- T-Bone & Van
	LVAR_INT sy1_tbone sy1_tbone_car sy1_tbone_blip 
	LVAR_INT sy1_tbone_status sy1_tbone_progress sy1_tbone_walk
	LVAR_FLOAT sy1_tbone_x sy1_tbone_y sy1_tbone_z sy1_tbone_h
	LVAR_FLOAT sy1_tbone_car_x sy1_tbone_car_y sy1_tbone_car_z sy1_tbone_car_h

	sy1_tbone_car_create:
		REQUEST_MODEL ZR350
		REQUEST_CAR_RECORDING 191
		LOAD_SPECIAL_CHARACTER 3 tbone

		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED ZR350
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 191
        WAIT 0
		ENDWHILE

		sy1_tbone_car_x = -2141.4009
		sy1_tbone_car_y = -2481.5747
		sy1_tbone_car_z = 29.4688 
		sy1_tbone_car_h	= 317.0930
		CLEAR_AREA sy1_tbone_car_x sy1_tbone_car_y sy1_tbone_car_z 5.0 TRUE
		CREATE_CAR ZR350 sy1_tbone_car_x sy1_tbone_car_y sy1_tbone_car_z sy1_tbone_car
		CHANGE_CAR_COLOUR sy1_tbone_car 84 84
		SET_CAR_PROOFS sy1_tbone_car TRUE TRUE TRUE TRUE TRUE
		SET_CAR_HEADING	sy1_tbone_car sy1_tbone_car_h
		CREATE_CHAR_INSIDE_CAR sy1_tbone_car PEDTYPE_CIVMALE SPECIAL03 sy1_tbone
 		START_PLAYBACK_RECORDED_CAR	sy1_tbone_car 191
		SET_PLAYBACK_SPEED sy1_tbone_car 0.6
	RETURN

// ---- Jizzy in his car
	
	LVAR_INT sy1_jizzy sy1_jizzy_car sy1_jizzy_blip sy1_pro sy1_end_car
	LVAR_INT sy1_jizzy_status sy1_jizzy_progress sy1_jizzy_walk
	LVAR_FLOAT sy1_jizzy_x sy1_jizzy_y sy1_jizzy_z sy1_jizzy_h
	LVAR_FLOAT sy1_jizzy_car_x sy1_jizzy_car_y sy1_jizzy_car_z sy1_jizzy_car_h

	sy1_jizzy_car_create:
		REQUEST_MODEL BROADWAY
		REQUEST_MODEL WALTON
		REQUEST_CAR_RECORDING 193
		REQUEST_CAR_RECORDING 194
		LOAD_SPECIAL_CHARACTER 5 jizzy
		REQUEST_MODEL SHFYPRO
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED BROADWAY
		OR NOT HAS_MODEL_LOADED	SHFYPRO
		OR NOT HAS_MODEL_LOADED	WALTON
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 5
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 193
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 194
        WAIT 0
		ENDWHILE

		sy1_jizzy_car_x = -2141.4009
		sy1_jizzy_car_y = -2481.5747
		sy1_jizzy_car_z = 29.4688 
		sy1_jizzy_car_h	= 317.0930
		CLEAR_AREA sy1_jizzy_car_x sy1_jizzy_car_y sy1_jizzy_car_z 5.0 TRUE
		CUSTOM_PLATE_FOR_NEXT_CAR BROADWAY HO_2_HO_
		CREATE_CAR BROADWAY sy1_jizzy_car_x sy1_jizzy_car_y sy1_jizzy_car_z sy1_jizzy_car
		CREATE_CAR WALTON -2235.7273 -2572.8728 30.9296 sy1_end_car
		SET_CAR_PROOFS sy1_jizzy_car TRUE TRUE TRUE TRUE TRUE
		SET_CAR_HEADING	sy1_end_car	44.9335
		CHANGE_CAR_COLOUR sy1_jizzy_car 123 5
		SET_CAR_HEADING	sy1_jizzy_car sy1_jizzy_car_h
		CREATE_CHAR_INSIDE_CAR sy1_jizzy_car PEDTYPE_CIVFEMALE SHFYPRO sy1_pro
		CREATE_CHAR_AS_PASSENGER sy1_jizzy_car PEDTYPE_CIVMALE SPECIAL05 0 sy1_jizzy
 		START_PLAYBACK_RECORDED_CAR	sy1_jizzy_car 193
	RETURN

// ---- Toreno comes out
	LVAR_INT sy1_toreno sy1_toreno_car sy1_toreno_blip 
	LVAR_INT sy1_toreno_status sy1_toreno_progress sy1_toreno_walk
	LVAR_FLOAT sy1_toreno_x sy1_toreno_y sy1_toreno_z sy1_toreno_h
	LVAR_FLOAT sy1_toreno_car_x sy1_toreno_car_y sy1_toreno_car_z sy1_toreno_car_h

	sy1_toreno_car_create:
		REQUEST_MODEL WASHING
		REQUEST_CAR_RECORDING 192
		LOAD_SPECIAL_CHARACTER 4 torino

		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED WASHING
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 4
		OR NOT HAS_CAR_RECORDING_BEEN_LOADED 192
        WAIT 0
		ENDWHILE
		
		sy1_toreno_car_x = -2214.2754
		sy1_toreno_car_y = -2475.1125
		sy1_toreno_car_z = 29.4688 
		sy1_toreno_car_h =  232.2762
		CUSTOM_PLATE_FOR_NEXT_CAR WASHING &_OMEGA__
		CLEAR_AREA sy1_toreno_car_x sy1_toreno_car_y sy1_toreno_car_z 5.0 TRUE
		CREATE_CAR WASHING sy1_toreno_car_x sy1_toreno_car_y sy1_toreno_car_z sy1_toreno_car
		CHANGE_CAR_COLOUR sy1_toreno_car 84 84	  
		SET_CAR_PROOFS sy1_toreno_car TRUE TRUE TRUE TRUE TRUE
		SET_CAR_HEADING	sy1_toreno_car sy1_toreno_car_h
		CREATE_CHAR_INSIDE_CAR sy1_toreno_car PEDTYPE_CIVMALE SPECIAL04 sy1_toreno
 		START_PLAYBACK_RECORDED_CAR	sy1_toreno_car 192
	RETURN

// ------------------------------------------------------------------------------------------------
// -------- Vehicles


// ------------------------------------------------------------------------------------------------
// Start Mission
mission_start_syn1:
	LOAD_MISSION_TEXT SYN1
	REGISTER_MISSION_GIVEN

	flag_player_on_mission = 1
	WAIT 0
// ------------------------------------------------------------------------------------------------
// Initialize Variables
// ---- Flags
	sy1_stage = 0
	sy1_cut = 0
	sy1_temp_flag = 0
	sy1_dialogue = 0
	sy1_in_car = 0

	sy1_fx_flag = 0
	sy1_anim_flag = 0
	sy1_skip = 0
	sy1_grouped = 0
	sy1_meet = 0

	sy1_photo_targets = 0

// ---- Dialogue Flags
	sy1_audio_slot = 1
	sy1_alt_slot = 2
	sy1_counter = 0
	sy1_ahead_counter = 1
	sy1_audio_playing = 0

// ---- Sequences
	sy1_player_confront = 1
// -------- Sequence Coords
	sy1_pilot_confront_x[0] = -2413.0
	sy1_pilot_confront_y[0] = -580.0
	sy1_pilot_confront_z[0]	= 133.0

	sy1_pilot_confront_x[1] = -2410.0
	sy1_pilot_confront_y[1] = -582.0
	sy1_pilot_confront_z[1]	= 133.0

	sy1_pilot_confront_x[2] = -2406.0
	sy1_pilot_confront_y[2] = -584.0
	sy1_pilot_confront_z[2]	= 133.0

	sy1_ryder_car_x = -2141.1526
	sy1_ryder_car_y = -2448.8711
	sy1_ryder_car_z = 29.6250 
	sy1_ryder_car_h	= 107.1964 
// ---- Phot STuff
	sy1_lower_heading =	120.0
	sy1_upper_heading =	240.0
// ---- Counter Var

// ---- Cesar

// ---- Coords
	sy1_player_x = -2407.0
	sy1_player_y = -586.0
	sy1_player_z = 131.0
	sy1_player_h = 4.0

	sy1_park_x = -2163.4893
	sy1_park_y = -2404.1592
	sy1_park_z = 29.6172
	 
	 
	

	sy1_cesar_car_x = -54.0	
	sy1_cesar_car_y = -432.0
	sy1_cesar_car_z = 1.0
	sy1_cesar_car_h	= 259.0

		sy1_dummy_car_x = -2171.1733
		sy1_dummy_car_y = -2422.8206
		sy1_dummy_car_z = 33.2969 
		sy1_dummy_car_h	= 141.2754


// ---- Camera Coords
// -------- Initial Heli Cut
		sy1_cam_x[0] =	-2408.4370
		sy1_cam_y[0] =	-585.0820 
		sy1_cam_z[0] =	133.2022 
		
		sy1_cam_look_x[0] = -2408.3367
		sy1_cam_look_y[0] = -584.0877 
		sy1_cam_look_z[0] = 133.1662 

// -------- Pick Up Cesar Heli Cut
		sy1_cam_x[1] =	-60.0461 
		sy1_cam_y[1] =	-433.1644
		sy1_cam_z[1] =	1.4904 
		
		sy1_cam_look_x[1] = -59.0698 
		sy1_cam_look_y[1] = -432.9484
		sy1_cam_look_z[1] = 1.5005

		sy1_cam_x[2] =	-58.7976 
		sy1_cam_y[2] =	-433.3285
		sy1_cam_z[2] =	3.0796 

		sy1_cam_look_x[2] = -57.8850 
		sy1_cam_look_y[2] = -432.9940
		sy1_cam_look_z[2] = 2.8444

		sy1_cam_x[3] =		  -46.1629 
		sy1_cam_y[3] =		  -427.8859
		sy1_cam_z[3] =		  1.5902 

		sy1_cam_look_x[3] =  -45.8006 
		sy1_cam_look_y[3] =  -428.8180
		sy1_cam_look_z[3] =  1.3937 

		sy1_cam_x[4] =		  -2407.1704
		sy1_cam_y[4] =		  -583.5895 
		sy1_cam_z[4] =		  133.3550 

		sy1_cam_look_x[4] =  -2407.7656
		sy1_cam_look_y[4] =  -582.7864 
		sy1_cam_look_z[4] =  133.3749 

		sy1_cam_x[5] =		 -2407.1704
		sy1_cam_y[5] =		 -583.5895 
		sy1_cam_z[5] =		 133.3550 

		sy1_cam_look_x[5] = -2407.9668
		sy1_cam_look_y[5] = -584.1938 
		sy1_cam_look_z[5] = 133.3749 

		sy1_cam_x[6] =		 -2172.5522
		sy1_cam_y[6] =		 -2427.4873
		sy1_cam_z[6] =		 30.5334 

		sy1_cam_look_x[6] =	 -2171.9856
		sy1_cam_look_y[6] =	 -2428.3042
		sy1_cam_look_z[6] =	 30.6400 

		sy1_cam_x[7] =		 -2139.5693		  
		sy1_cam_y[7] =		 -2452.2385		  
		sy1_cam_z[7] =		 31.2226 		  

		sy1_cam_look_x[7] =	 -2140.2920		  
		sy1_cam_look_y[7] =	 -2451.5486		  
		sy1_cam_look_z[7] =	 31.1829 		  

		sy1_cam_x[8] =		-2175.6719
		sy1_cam_y[8] =		-2437.2302
		sy1_cam_z[8] =		30.7446 

		sy1_cam_look_x[8] =	-2174.6836
		sy1_cam_look_y[8] =	-2437.1333
		sy1_cam_look_z[8] =	30.8619 

		sy1_cam_x[9] =		-2170.4121
		sy1_cam_y[9] =		-2429.4302
		sy1_cam_z[9] =		31.7580 

		sy1_cam_look_x[9] =	-2170.4998
		sy1_cam_look_y[9] =	-2430.4231
		sy1_cam_look_z[9] =	31.8365 

		sy1_cam_x[10] =		 -2246.8511
		sy1_cam_y[10] =		 -2571.0310
		sy1_cam_z[10] =		 32.7468 

		sy1_cam_look_x[10] = -2246.1604
		sy1_cam_look_y[10] = -2570.3110
		sy1_cam_look_z[10] = 32.6781 

		sy1_cam_x[11] =		 -2241.5188
		sy1_cam_y[11] =		 -2568.2810
		sy1_cam_z[11] =		 32.2372 

		sy1_cam_look_x[11] = -2242.2859
		sy1_cam_look_y[11] = -2567.6418
		sy1_cam_look_z[11] = 32.2906 

// ---- Dialogue Text
	$sy1_text[1] = MCES10A // CJ.
	$sy1_text[2] = MCES10B // You got it.
	$sy1_text[3] = MCES10C // My cousin in Los Santos called me. 
	$sy1_text[4] = MCES10D // He gave me a tip about a Ballas car going San Fierro way to score yay.
	$sy1_text[5] = MCES10E // Shit, we gotta find out who's supplyin' those cats.
	$sy1_text[6] = MCES10F // Read your mind, holmes, 
	$sy1_text[7] = MCES10G // I picked them up at the Mulholland Intersection and I'm tailing them now.
	$sy1_text[8] = MCES10H // Ok, I'm coming out to meet you.
	$sy1_text[9] = MCES10J // Better make it fast, holmes, these boys aren't hanging around!
	$sy1_text[10] = CESX_AB // Hop in, holmes!

//	$sy1_text[11] = SYN1_BA // Hey, Cesar, I'm coming your way, where are you?
//	$sy1_text[12] = SYN1_BB // Sitting by the damn road just South of Blueberry!
//	$sy1_text[13] = SYN1_BC // They spotted me and shot out my tyres!
//	$sy1_text[14] = SYN1_BD // Hang on, I'll be there any minute!

	$sy1_text[15] = SYN1_FC // Where to?
	$sy1_text[16] = SYN1_FD // They were headed over Angel Pine way. Follow the road and maybe we can pick them up!

	$sy1_text[17] = SYN1_IA // There it is, holmes!

	$sy1_text[18] = SYN1_ZA // That's our ballas car alright.
	//$sy1_text[19] = SYN1_ZB // Who's that in the back?
	$sy1_text[20] = SYN1_ZC // Ryder, you sherm-head!
	$sy1_text[21] = SYN1_ZD // What's he rolling with Ballas for?
	$sy1_text[22] = SYN1_ZE // This business is bigger than any gang, esse.
	//$sy1_text[23] = SYN1_ZF // Someone's coming out of the diner's back door!
	$sy1_text[24] = SYN1_ZG // Who's this guy, Aztecas? Vagos?
	//$sy1_text[25] = SYN1_ZH // Neither, holmes, he's San Fierro Rifa!
	//$sy1_text[26] = SYN1_ZJ // Ryder's getting out of the car!
	$sy1_text[27] = SYN1_ZK // Ryder, you lap dog!
	$sy1_text[28] = SYN1_ZL // That must be the cash...
	$sy1_text[29] = SYN1_ZM // They're being clever about this, there's no exchange, nothing incriminating.
	$sy1_text[30] = SYN1_ZN // What now? Was that it?
	//$sy1_text[31] = SYN1_ZO // Hey, holmes, check the van!
	$sy1_text[32] = SYN1_ZP // This guy takes himself real serious!
	$sy1_text[33] = SYN1_ZQ // Oh, esse, that's T-Bone Mendez.
	$sy1_text[34] = SYN1_ZR // There's the courier with a fresh load of yay for Ryder and his new friends.
	//$sy1_text[35] = SYN1_ZS // Who are all these guys?
	$sy1_text[36] = SYN1_ZT // More San Fierro Rifa, by the looks of things.
	$sy1_text[37] = SYN1_ZU // They sure keep their security tight!
	//$sy1_text[38] = SYN1_ZV // I didn't scope those guys, did you?
	$sy1_text[39] = SYN1_ZW // No. This is more than just a few thugs pushing product, this is seriously organised!
	$sy1_text[40] = SYN1_ZX // How many of these clowns are there?
	$sy1_text[41] = SYN1_ZY // I know a pimp when I see one!
	$sy1_text[42] = SYN1_ZZ // Who's the gringo?
	$sy1_text[43] = SYN1_YA // I don't like the look of that guy.
	$sy1_text[44] = SYN1_YB // Uh-oh, I think he's clocked us!
	//$sy1_text[45] = SYN1_YC // Well we're not exactly inconspicuous up here.
	//$sy1_text[46] = SYN1_YD // Besides, what's he going to do, huh, throw stones at us?
	$sy1_text[47] = SYN1_YE // Oh fuck! OH FUCK!
	$sy1_text[48] = SYN1_YF // Get us out of here!
	//$sy1_text[49] = SYN1_YG // Pope crap!
	$sy1_text[50] = SYN1_YH // That was some heavy shit!
	$sy1_text[51] = SYN1_YJ // We better split up and get outta here. I'll meet you back at the garage!
	$sy1_text[52] = SYN1_YK // Coolio, dude. We got what we came for

// ---- Dialogue Audio
	sy1_audio[1] = SOUND_MCES10A // CJ.
	sy1_audio[2] = SOUND_MCES10B // You got it.
	sy1_audio[3] = SOUND_MCES10C // My cousin in Los Santos called me. 
	sy1_audio[4] = SOUND_MCES10D // He gave me a tip about a Ballas car going San Fierro way to score yay.
	sy1_audio[5] = SOUND_MCES10E // Damn, we gotta find out who's supplyin' those cats.
	sy1_audio[6] = SOUND_MCES10F // Read your mind, holmes, 
	sy1_audio[7] = SOUND_MCES10G // I picked them up at the Mulholland Intersection and I'm tailing them now.
	sy1_audio[8] = SOUND_MCES10H // Ok, I'm coming out to meet you.
	sy1_audio[9] = SOUND_MCES10J // Better make it fast, holmes, these boys aren't hanging around!
	sy1_audio[10] = SOUND_CESX_AB // Hop in, holmes!

//	sy1_audio[11] = SOUND_SYN1_BA // Hey, Cesar, I'm coming your way, where are you?
//	sy1_audio[12] = SOUND_SYN1_BB // Sitting by the damn road just South of Blueberry!
//	sy1_audio[13] = SOUND_SYN1_BC // They spotted me and shot out my tyres!
//	sy1_audio[14] = SOUND_SYN1_BD // Hang on, I'll be there any minute!

	sy1_audio[15] = SOUND_SYN1_FC // Where to?
	sy1_audio[16] = SOUND_SYN1_FD // They were headed over Angel Pine way. Follow the road and maybe we can pick them up!

	sy1_audio[17] = SOUND_SYN1_IA // There it is, holmes!

	//sy1_audio[18] = SOUND_SYN1_ZA // That's our ballas car alright.
	//sy1_audio[19] = SOUND_SYN1_ZB // Who's that in the back?
	sy1_audio[20] = SOUND_SYN1_ZC // Ryder, you sherm-head!
	sy1_audio[21] = SOUND_SYN1_ZD // What's he rolling with Ballas for?
	sy1_audio[22] = SOUND_SYN1_ZE // This business is bigger than any gang, esse.
	//sy1_audio[23] = SOUND_SYN1_ZF // Someone's coming out of the diner's back door!
	sy1_audio[24] = SOUND_SYN1_ZG // Who's this guy, Aztecas? Vagos?
	//sy1_audio[25] = SOUND_SYN1_ZH // Neither, holmes, he's San Fierro Rifa!
	//sy1_audio[26] = SOUND_SYN1_ZJ // Ryder's getting out of the car!
	sy1_audio[27] = SOUND_SYN1_ZK // Ryder, you lap dog!
	sy1_audio[28] = SOUND_SYN1_ZL // That must be the cash...
	sy1_audio[29] = SOUND_SYN1_ZM // They're being clever about this, there's no exchange, nothing incriminating.
	sy1_audio[30] = SOUND_SYN1_ZN // What now? Was that it?
	//sy1_audio[31] = SOUND_SYN1_ZO // Hey, holmes, check the van!
	sy1_audio[32] = SOUND_SYN1_ZP // This guy takes himself real serious!
	sy1_audio[33] = SOUND_SYN1_ZQ // Oh, esse, that's T-Bone Mendez.
	sy1_audio[34] = SOUND_SYN1_ZR // There's the courier with a fresh load of yay for Ryder and his new friends.
	//sy1_audio[35] = SOUND_SYN1_ZS // Who are all these guys?
	sy1_audio[36] = SOUND_SYN1_ZT // More San Fierro Rifa, by the looks of things.
	sy1_audio[37] = SOUND_SYN1_ZU // They sure keep their security tight!
	//sy1_audio[38] = SOUND_SYN1_ZV // I didn't scope those guys, did you?
	sy1_audio[39] = SOUND_SYN1_ZW // No. This is more than just a few thugs pushing product, this is seriously organised!
	sy1_audio[40] = SOUND_SYN1_ZX // How many of these clowns are there?
	sy1_audio[41] = SOUND_SYN1_ZY // I know a pimp when I see one!
	sy1_audio[42] = SOUND_SYN1_ZZ // Who's the gringo?
	sy1_audio[43] = SOUND_SYN1_YA // I don't like the look of that guy.
	sy1_audio[44] = SOUND_SYN1_YB // Uh-oh, I think he's clocked us!
	//sy1_audio[45] = SOUND_SYN1_YC // Well we're not exactly inconspicuous up here.
	//sy1_audio[46] = SOUND_SYN1_YD // Besides, what's he going to do, huh, throw stones at us?
	sy1_audio[47] = SOUND_SYN1_YE // Oh fuck! OH FUCK!
	sy1_audio[48] = SOUND_SYN1_YF // Get us out of here!
	//sy1_audio[49] = SOUND_SYN1_YG // Pope crap!
	sy1_audio[50] = SOUND_SYN1_YH // That was some heavy shit!
	sy1_audio[51] = SOUND_SYN1_YJ // We better split up and get outta here. I'll meet you back at the garage!
	sy1_audio[52] = SOUND_SYN1_YK // Coolio, dude. We got what we came for



// ------------------------------------------------------------------------------------------------
// Request Models 

// ------------------------------------------------------------------------------------------------
// Entity GoSubs

	IF flag_syn1_passed_1stime = 0
        flag_syn1_passed_1stime = 1
	ENDIF

//VIEW_INTEGER_VARIABLE sy1_cut sy1_cut
// ------------------------------------------------------------------------------------------------
// Task Sequences

// Starting Crap...
//// -------- Pickups
//	DO_FADE 300 FADE_OUT
//	WHILE GET_FADING_STATUS
//	WAIT 0
//	ENDWHILE
	REQUEST_MODEL CELLPHONE
	REQUEST_MODEL CAMERA
	LOAD_ALL_MODELS_NOW
	WHILE NOT HAS_MODEL_LOADED CAMERA
	OR NOT HAS_MODEL_LOADED CELLPHONE
	WAIT 0
	ENDWHILE
	SET_CHAR_COORDINATES scplayer -2030.3121 148.0136 27.8359 
	SET_CHAR_HEADING scplayer 293.7756
	SET_PLAYER_CONTROL player1 OFF
	TASK_USE_MOBILE_PHONE scplayer TRUE
	SWITCH_WIDESCREEN ON
	SET_FIXED_CAMERA_POSITION -2024.4692 136.6788 39.4814 0.0 0.0 0.0
	POINT_CAMERA_AT_POINT -2025.1348 137.4221 39.5473 JUMP_CUT
	SWITCH_CAR_GENERATOR car_gen_pine[0] 0
	SWITCH_CAR_GENERATOR car_gen_pine[1] 0
	SWITCH_CAR_GENERATOR car_gen_pine[2] 0
	GOSUB sy1_cesar_model_load
// Mobile camera
  	DO_FADE 300 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	

	CAMERA_RESET_NEW_SCRIPTABLES
	CAMERA_PERSIST_TRACK TRUE                   
	CAMERA_PERSIST_POS TRUE                       
	CAMERA_SET_VECTOR_MOVE -2024.4692 136.6788 39.4814 -2030.3987 144.5236 29.1601 3500 TRUE
	CAMERA_SET_VECTOR_TRACK -2025.1348 137.4221 39.5473 -2030.7972 145.4357 29.0639 3500 TRUE

	 


// ---- Initial Mobile Call from Cesar...
syn1_mobile_loop:
WAIT 0
// ---- Load & Play Dialogue...
IF NOT sy1_counter = 0
	IF sy1_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED sy1_alt_slot
			CLEAR_MISSION_AUDIO sy1_alt_slot
		ENDIF
		sy1_audio_playing = 1
	ENDIF

	IF sy1_audio_playing = 1
		LOAD_MISSION_AUDIO sy1_audio_slot sy1_audio[sy1_counter]
		GOSUB sy1_dialogue_pos
		
		sy1_audio_playing = 2
	ENDIF

	IF sy1_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED sy1_audio_slot
			IF NOT sy1_audio_char = 0
				IF NOT IS_CHAR_DEAD	sy1_audio_char
					START_CHAR_FACIAL_TALK sy1_audio_char 10000
					//ATTACH_MISSION_AUDIO_TO_CHAR sy1_audio_slot sy1_audio_char
				ENDIF
			ENDIF
			PLAY_MISSION_AUDIO sy1_audio_slot
			PRINT_NOW $sy1_text[sy1_counter] 10000 1
			sy1_audio_playing = 3
		ENDIF
	ENDIF

	IF sy1_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED sy1_audio_slot
			CLEAR_THIS_PRINT $sy1_text[sy1_counter]
			IF NOT sy1_audio_char = 0
				IF NOT IS_CHAR_DEAD	sy1_audio_char
					STOP_CHAR_FACIAL_TALK sy1_audio_char
				ENDIF
			ENDIF
			IF sy1_audio_slot = 1
				sy1_audio_slot = 2
				sy1_alt_slot = 1
			ELSE
				sy1_audio_slot = 1
				sy1_alt_slot = 2
			ENDIF
			sy1_counter = 0
			sy1_audio_playing = 0
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED sy1_alt_slot
				IF sy1_counter < 60
					sy1_ahead_counter = sy1_counter + 1
					LOAD_MISSION_AUDIO sy1_alt_slot sy1_audio[sy1_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

	IF sy1_mobile = 0
		IF sy1_audio_playing = 0
			sy1_counter = 1	// CESAR: CJ.
			sy1_mobile = 1
			GET_GAME_TIMER sy1_text_timer_start
		ENDIF
	ENDIF

	IF sy1_mobile = 1
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				sy1_counter = 2	// CARL: You got it.
				sy1_mobile = 2
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 2
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				sy1_counter = 3	// CESAR: My cousin in Los Santos called me. 
				sy1_mobile = 3
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 3
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				sy1_counter = 4	// He gave me a tip about a Ballas car going San Fierro way to score yay.
				sy1_mobile = 4
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 4
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				CAMERA_RESET_NEW_SCRIPTABLES
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
				SWITCH_WIDESCREEN OFF
				SET_PLAYER_CONTROL player1 ON
				sy1_counter = 5	// CARL: Shit, we gotta find out who's supplyin' those cats.
				sy1_mobile = 5
				GET_GAME_TIMER sy1_text_timer_start

			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 5
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				sy1_counter = 6	// CESAR: Read your mind, holmes,
				sy1_mobile = 6
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 6
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				sy1_counter = 7	// I picked them up at the Mulholland Intersection and I'm tailing them now.
				sy1_mobile = 7
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 7
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				sy1_counter = 8	// CARL: Ok, I'm coming out to meet you.
				sy1_mobile = 8
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 8
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				sy1_counter = 9	// Better make it fast, holmes, these boys aren't hanging around!
				sy1_mobile = 9
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
		ENDIF
	ENDIF

	IF sy1_mobile = 9
	OR sy1_mobile = 10
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF sy1_audio_playing = 0
				SET_PLAYER_CONTROL player1 ON
				GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE sy1_player_status
				IF NOT sy1_player_status = FINISHED_TASK
					TASK_USE_MOBILE_PHONE scplayer FALSE
				ENDIF
				GOTO syn1_main_loop
			ENDIF
		ENDIF
	ENDIF

	IF IS_BUTTON_PRESSED PAD1 TRIANGLE
	OR IS_BUTTON_PRESSED PAD1 CROSS
	OR IS_BUTTON_PRESSED PAD1 CIRCLE
		CAMERA_RESET_NEW_SCRIPTABLES
		RESTORE_CAMERA_JUMPCUT
		SET_CAMERA_BEHIND_PLAYER
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF
		sy1_mobile = 10
	ENDIF
   
GOTO syn1_mobile_loop


// ------------------------------------------------------------------------------------------------
// Main Loop
syn1_main_loop:

WAIT 0

// ---- Debug Skips
IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_passed_syn1  
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
	flag_syn1_skip1 = 1
	flag_syn1_skip2 = 1
ENDIF

IF sy1_stage = 0
	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
		DO_FADE 100 FADE_OUT
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
		SET_CHAR_COORDINATES scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA 30000
		LOAD_SCENE sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
		LOAD_SCENE sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z
		GOSUB sy1_dummy_car_create
		GOSUB sy1_ryder_car_create
		DO_FADE 100 FADE_IN
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
		sy1_stage = 5
		sy1_cut = 1
	ENDIF
ENDIF

IF NOT sy1_counter = 0
	IF sy1_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED sy1_alt_slot
			CLEAR_MISSION_AUDIO sy1_alt_slot
		ENDIF
		sy1_audio_playing = 1
	ENDIF

	IF sy1_audio_playing = 1
		LOAD_MISSION_AUDIO sy1_audio_slot sy1_audio[sy1_counter]
		GOSUB sy1_dialogue_pos
		
		sy1_audio_playing = 2
	ENDIF

	IF sy1_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED sy1_audio_slot
			IF NOT sy1_audio_char = 0
				IF NOT IS_CHAR_DEAD	sy1_audio_char
					START_CHAR_FACIAL_TALK sy1_audio_char 10000
					//ATTACH_MISSION_AUDIO_TO_CHAR sy1_audio_slot sy1_audio_char
				ENDIF
			ENDIF
			PLAY_MISSION_AUDIO sy1_audio_slot
			PRINT_NOW $sy1_text[sy1_counter] 10000 1
			sy1_audio_playing = 3
		ENDIF
	ENDIF

	IF sy1_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED sy1_audio_slot
			CLEAR_THIS_PRINT $sy1_text[sy1_counter]
			IF NOT sy1_audio_char = 0
				IF NOT IS_CHAR_DEAD	sy1_audio_char
					STOP_CHAR_FACIAL_TALK sy1_audio_char
				ENDIF
			ENDIF
			IF sy1_audio_slot = 1
				sy1_audio_slot = 2
				sy1_alt_slot = 1
			ELSE
				sy1_audio_slot = 1
				sy1_alt_slot = 2
			ENDIF
			IF sy1_counter = 10
			AND	sy1_temp_flag = 2
				sy1_counter = 0
				sy1_audio_playing = 0
				sy1_temp_flag = 3
			ELSE
				sy1_counter = 0
				sy1_audio_playing = 0
			ENDIF
			IF sy1_audio_seq = 1
				sy1_audio_seq = 2
			ENDIF
			IF sy1_audio_seq = 0
				sy1_audio_seq = 1
			ENDIF
			IF sy1_stage = 6
				IF sy1_cut = 13
					sy1_cut = 0
				ENDIF
			ENDIF
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED sy1_alt_slot
				IF sy1_counter < 60
					sy1_ahead_counter = sy1_counter + 1
					LOAD_MISSION_AUDIO sy1_alt_slot sy1_audio[sy1_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF sy1_stage = 0
	ADD_BLIP_FOR_COORD sy1_cesar_car_x sy1_cesar_car_y sy1_cesar_car_z sy1_cesar_blip
	SET_COORD_BLIP_APPEARANCE sy1_cesar_blip COORD_BLIP_APPEARANCE_FRIEND
	GET_GAME_TIMER sy1_text_timer_start
	PRINT ( SYN1_02 ) 10000 1 // Go and pick up Cesar.
	
	sy1_dialogue = 11
	sy1_stage = 1
ENDIF


IF sy1_stage = 2
	IF NOT LOCATE_CHAR_IN_CAR_3D scplayer sy1_park_x sy1_park_y sy1_park_z 4.0 4.0 4.0 FALSE
		GOSUB sy1_player_car_check
		GOSUB sy1_player_group_check
	ENDIF
ENDIF

// Go pick up Cesar...
IF sy1_stage = 1
	IF sy1_cut = 0
		IF IS_CHAR_IN_ANY_CAR scplayer
			STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer sy1_player_car
			IF sy1_audio_playing = 0
//				IF sy1_dialogue = 7
//					GET_GAME_TIMER sy1_text_timer_end
//					sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
//					IF sy1_text_timer_diff > 3000
//						//IF flag_syn1_passed_1stime = 1
//						IF flag_syn1_skip1 = 1
//							SET_UP_SKIP -85.5180 -413.1554 0.4297 240.0212
//						ENDIF
//						IF NOT IS_CHAR_ON_ANY_BIKE scplayer
//							GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE sy1_player_status
//							IF sy1_player_status = FINISHED_TASK
//								TASK_USE_MOBILE_PHONE scplayer TRUE
//							ENDIF
//							IF NOT IS_CAR_DEAD sy1_player_car
//								IF IS_CAR_PASSENGER_SEAT_FREE sy1_player_car 1
//									LOCK_CAR_DOORS sy1_player_car CARLOCK_LOCKED
//								ENDIF
//							ENDIF
//							sy1_counter = 11 // CARL: Hey, Cesar, I'm coming your way, where are you?
//							GET_GAME_TIMER sy1_text_timer_start
//							sy1_dialogue = 8
//						ELSE
//							sy1_dialogue = 11
//						ENDIF
//					ENDIF
//				ENDIF
//
//				IF sy1_dialogue = 8
//					GET_GAME_TIMER sy1_text_timer_end
//					sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
//					IF sy1_text_timer_diff > 3000
//						GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE sy1_player_status
//						IF sy1_player_status = FINISHED_TASK
//							TASK_USE_MOBILE_PHONE scplayer TRUE
//						ENDIF
//						IF NOT IS_CHAR_ON_ANY_BIKE scplayer
//							sy1_counter = 12  // CESAR: Sitting by the damn road just South of Blueberry!
//						ENDIF
//						GET_GAME_TIMER sy1_text_timer_start
//						sy1_dialogue = 9
//					ENDIF
//				ENDIF
//				IF sy1_dialogue = 9
//					GET_GAME_TIMER sy1_text_timer_end
//					sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
//					IF sy1_text_timer_diff > 3000
//						GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE sy1_player_status
//						IF sy1_player_status = FINISHED_TASK
//							TASK_USE_MOBILE_PHONE scplayer TRUE
//						ENDIF
//						IF NOT IS_CHAR_ON_ANY_BIKE scplayer
//							sy1_counter = 13 // They spotted me and shot out my tyres.
//						ENDIF
//						GET_GAME_TIMER sy1_text_timer_start
//						sy1_dialogue = 10
//					ENDIF
//				ENDIF
//				IF sy1_dialogue = 10
//					GET_GAME_TIMER sy1_text_timer_end
//					sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
//					IF sy1_text_timer_diff > 3000
//						GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE sy1_player_status
//						IF sy1_player_status = FINISHED_TASK
//							TASK_USE_MOBILE_PHONE scplayer TRUE
//						ENDIF
//						IF NOT IS_CHAR_ON_ANY_BIKE scplayer
//							sy1_counter = 14 // CARL: Hang on, I'll be there any minute!
//						ENDIF
//						GET_GAME_TIMER sy1_text_timer_start
//						sy1_dialogue = 11
//					ENDIF
//				ENDIF
				IF sy1_dialogue = 11
					GET_GAME_TIMER sy1_text_timer_end
					sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
					IF sy1_text_timer_diff > 1
						IF flag_syn1_skip1 = 1
							SET_UP_SKIP -85.5180 -413.1554 0.4297 240.0212
						ENDIF

//						TASK_USE_MOBILE_PHONE scplayer FALSE
//						IF NOT IS_CAR_DEAD sy1_player_car
//							LOCK_CAR_DOORS sy1_player_car CARLOCK_UNLOCKED
//						ENDIF
						IF NOT IS_MESSAGE_BEING_DISPLAYED
							PRINT ( SYN1_02 ) 10000 1 // Go and pick up Cesar.
						ENDIF
						GET_GAME_TIMER sy1_text_timer_start
						sy1_dialogue = 12
						sy1_cut = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_temp_flag = 0
		IF IS_BUTTON_PRESSED PAD1 DPADRIGHT
			TASK_USE_MOBILE_PHONE scplayer FALSE
			IF NOT IS_CAR_DEAD sy1_player_car
				LOCK_CAR_DOORS sy1_player_car CARLOCK_UNLOCKED
			ENDIF

			sy1_dialogue = 12
			sy1_cut = 1
			GOSUB sy1_cesar_car_create
			IF NOT IS_CHAR_DEAD sy1_cesar
				TASK_PLAY_ANIM sy1_cesar BOM_plant_Loop BOMBER 4.0 1 0 0 0 -1
			ENDIF
			sy1_temp_flag = 1
		ENDIF
	ENDIF
	IF sy1_temp_flag = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer sy1_cesar_car_x sy1_cesar_car_y sy1_cesar_car_z 150.0 150.0 150.0 TRUE
			TASK_USE_MOBILE_PHONE scplayer FALSE
			IF NOT IS_CAR_DEAD sy1_player_car
				LOCK_CAR_DOORS sy1_player_car CARLOCK_UNLOCKED
			ENDIF

			GOSUB sy1_cesar_car_create
			IF NOT IS_CHAR_DEAD sy1_cesar
				TASK_PLAY_ANIM sy1_cesar BOM_plant_Loop BOMBER 4.0 1 0 0 0 -1
			ENDIF
			sy1_dialogue = 12
			sy1_temp_flag = 1
		ENDIF
	ENDIF

	IF sy1_temp_flag = 1
		IF NOT IS_CHAR_DEAD sy1_cesar
		AND NOT IS_CAR_DEAD sy1_cesar_car
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer sy1_cesar 30.0 30.0 30.0 FALSE
			AND NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer sy1_cesar 8.0 8.0 8.0 FALSE
				PRINT_NOW ( SYN1_15 ) 100 1 // Get in the car.
				IF sy1_anim_flag = 0
					IF NOT IS_CHAR_DEAD sy1_cesar
						GET_SCRIPT_TASK_STATUS sy1_cesar TASK_PLAY_ANIM sy1_cesar_status
						IF sy1_cesar_status = FINISHED_TASK
							TASK_PLAY_ANIM sy1_cesar BOM_plant_Loop BOMBER 4.0 1 0 0 0 -1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer sy1_cesar 8.0 8.0 8.0 FALSE
				CLEAR_CHAR_TASKS sy1_cesar
				IF sy1_anim_flag = 0
					sy1_anim_flag = 1
				ENDIF
				IF NOT IS_CHAR_IN_ANY_CAR scplayer
					IF NOT IS_GROUP_MEMBER sy1_cesar players_group
						IF flag_syn1_skip1 = 0
							flag_syn1_skip1 = 1
						ENDIF
						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
						SET_GROUP_MEMBER players_group sy1_cesar 
						REMOVE_BLIP sy1_cesar_blip
						//BURST_CAR_TYRE sy1_cesar_car 0
						ADD_BLIP_FOR_CAR sy1_cesar_car sy1_cesar_car_blip
						SET_BLIP_AS_FRIENDLY sy1_cesar_car_blip TRUE
						// Get in the fucking car
						GET_GAME_TIMER sy1_text_timer_start
						sy1_text_timer_start -= 3000
						//sy1_dialogue = 0
						sy1_stage = 2
						sy1_cut = 4
						sy1_meet = 1
						sy1_in_car = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// ---- Cesar dies
IF sy1_stage = 1
	IF sy1_temp_flag = 1
		IF IS_CHAR_DEAD sy1_cesar
			CLEAR_PRINTS
			PRINT ( SYN1_06 ) 5000 1 // Cesar didn't make it.
			GOTO mission_failed_syn1
		ENDIF

		IF IS_CAR_DEAD sy1_cesar_car
			CLEAR_PRINTS
			PRINT ( SYN1_08 ) 5000 1 // You destroyed the car!
			GOTO mission_failed_syn1
		ENDIF
	ENDIF
ENDIF

IF sy1_stage = 2
OR sy1_stage = 3
	IF IS_CHAR_DEAD sy1_cesar
		CLEAR_PRINTS
		PRINT ( SYN1_06 ) 5000 1 // Cesar didn't make it.
		GOTO mission_failed_syn1
	ENDIF
	IF IS_CAR_DEAD sy1_cesar_car
		CLEAR_PRINTS
		PRINT ( SYN1_08 ) 5000 1 // You destroyed the car!
		GOTO mission_failed_syn1
	ENDIF
ENDIF

IF sy1_stage = 2
	IF sy1_in_car = 1
	AND sy1_grouped = 1
	AND sy1_dialogue = 12
		sy1_dialogue = 2
	ELSE
		IF sy1_dialogue > 5
			IF sy1_grouped = 1
				IF sy1_temp_flag = 1
					IF sy1_audio_playing = 0
					AND sy1_counter = 0
						sy1_counter = 10
						sy1_temp_flag = 2
					ENDIF
				ENDIF
			ENDIF
			IF sy1_temp_flag = 1
				IF sy1_grouped = 0
				AND sy1_counter = 0
				AND sy1_audio_playing = 0
					PRINT_NOW ( SYN1_15 ) 100 1 // Get in Cesars car.
					SET_RADIO_CHANNEL RS_CLASSIC_HIP_HOP
				ENDIF
			ENDIF

			IF sy1_temp_flag = 3
				IF sy1_grouped = 1
				AND sy1_counter = 0
				AND sy1_audio_playing = 0
					PRINT_NOW ( SYN1_07 ) 100 1 // Get in the car.
					SET_RADIO_CHANNEL RS_CLASSIC_HIP_HOP
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_CHAR_DEAD sy1_cesar
	AND NOT IS_CAR_DEAD sy1_cesar_car
		IF IS_CHAR_IN_CAR sy1_cesar sy1_cesar_car
			IF sy1_dialogue = 2
				GET_GAME_TIMER sy1_text_timer_end
				sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
				IF sy1_text_timer_diff > 3500
					IF sy1_audio_playing = 0
						sy1_counter = 15 // CARL: Where to?
						GET_GAME_TIMER sy1_text_timer_start
						sy1_dialogue = 3

					ENDIF
				ENDIF
			ENDIF
			IF sy1_dialogue = 3
				GET_GAME_TIMER sy1_text_timer_end
				sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
				IF sy1_text_timer_diff > 1000
					IF sy1_audio_playing = 0
						sy1_counter = 16 // CESAR: They were headed over Angel Pine way. Follow the road and maybe we can pick them up!
						GET_GAME_TIMER sy1_text_timer_start
						sy1_dialogue = 4
					ENDIF
				ENDIF
			ENDIF
			IF sy1_dialogue = 4
				GET_GAME_TIMER sy1_text_timer_end
				sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
				IF sy1_text_timer_diff > 1000
					IF sy1_audio_playing = 0
						PRINT ( SYN1_13 ) 10000 1 // Track the car
						GET_GAME_TIMER sy1_text_timer_start
						sy1_dialogue = 5
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 4
		IF NOT IS_CHAR_DEAD sy1_cesar
		AND NOT IS_CAR_DEAD sy1_cesar_car
			IF IS_CHAR_IN_CAR sy1_cesar sy1_cesar_car
				IF IS_CHAR_IN_CAR scplayer sy1_cesar_car
					IF LOCATE_CHAR_IN_CAR_3D scplayer sy1_park_x sy1_park_y sy1_park_z 4.0 4.0 4.0 TRUE
						IF flag_syn1_skip2 = 0
							flag_syn1_skip2 = 1
						ENDIF
						SET_PLAYER_CONTROL player1 OFF
						GET_GAME_TIMER sy1_text_timer_start
						REMOVE_BLIP sy1_park_blip
						ADD_BLIP_FOR_COORD sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z sy1_vantage_blip
						PRINT_NOW ( SYN1_14 ) 5000 1 // Get on the roof
						sy1_stage = 3
						IF IS_GROUP_MEMBER sy1_cesar players_group
							REMOVE_CHAR_FROM_GROUP sy1_cesar
							OPEN_SEQUENCE_TASK sy1_cesar_stairs
								TASK_LEAVE_CAR -1 sy1_cesar_car
								TASK_GO_STRAIGHT_TO_COORD -1 -2160.5149 -2413.4492 29.6250 PEDMOVE_WALK -2
								TASK_GO_STRAIGHT_TO_COORD -1 -2162.4524 -2414.2295 29.6328 PEDMOVE_WALK -2
								TASK_GO_STRAIGHT_TO_COORD -1 -2165.8843 -2411.8853 31.6250 PEDMOVE_WALK -2
								TASK_GO_STRAIGHT_TO_COORD -1 -2168.9573 -2409.1177 33.6180 PEDMOVE_WALK -2
								TASK_GO_STRAIGHT_TO_COORD -1 -2170.8401 -2414.2397 33.2969 PEDMOVE_WALK -2
								TASK_GO_STRAIGHT_TO_COORD -1 -2167.7407 -2416.9531 33.2969 PEDMOVE_WALK -2
								TASK_GO_STRAIGHT_TO_COORD -1 -2168.3616 -2419.4397 33.2969 PEDMOVE_WALK -2
							CLOSE_SEQUENCE_TASK	sy1_cesar_stairs
							PERFORM_SEQUENCE_TASK sy1_cesar sy1_cesar_stairs
						ENDIF

					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF sy1_stage = 3
	GET_GAME_TIMER sy1_text_timer_end
	sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
	IF sy1_text_timer_diff > 500
		SET_PLAYER_CONTROL player1 ON
	ENDIF
	IF LOCATE_CHAR_ON_FOOT_3D scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z 1.2 1.2 2.0 TRUE
		DO_FADE 100 FADE_OUT
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
		IF NOT IS_CAR_DEAD sy1_cesar_car
			SET_CAR_PROOFS sy1_cesar_car TRUE TRUE TRUE TRUE TRUE
			SET_CAR_HEALTH sy1_cesar_car 1000
			SET_CAR_DENSITY_MULTIPLIER 0.0	
			SET_PED_DENSITY_MULTIPLIER 0.0
			SET_CAR_COORDINATES	sy1_cesar_car -2160.0603 -2409.6306 29.6172
			SET_CAR_HEADING	sy1_cesar_car 191.1948
		ENDIF
		IF NOT IS_CHAR_DEAD sy1_cesar
			SET_CHAR_PROOFS sy1_cesar TRUE TRUE TRUE TRUE TRUE
		ENDIF
		ALTER_WANTED_LEVEL player1 0
		REMOVE_BLIP sy1_vantage_blip
		SET_CHAR_COORDINATES scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
		GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA 30000
		LOAD_SCENE sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
		LOAD_SCENE sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z
		PRINT_HELP_FOREVER ( SYN1_44 ) 
		GOSUB sy1_dummy_car_create
		GOSUB sy1_ryder_car_create
		DO_FADE 100 FADE_IN
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
		sy1_stage = 5
		sy1_cut = 0
		GET_GAME_TIMER sy1_text_timer_start
		PRINT_NOW ( SYN1_12 ) 10000 1 // Get them quick
	ENDIF	
ENDIF

IF sy1_stage = 5
	GET_CAMERA_FOV sy1_fov
	IF sy1_cut = 0
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 8000
			IF NOT IS_CAR_DEAD sy1_ryder_car
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	sy1_ryder_car
					START_PLAYBACK_RECORDED_CAR	sy1_ryder_car 190
				ENDIF
			ENDIF
		ENDIF
		IF sy1_text_timer_diff > 10000
			IF sy1_audio_playing = 0
				sy1_counter = 17
				sy1_cut = 1
			ENDIF
		ENDIF
	ENDIF
// ---- Ryder
	IF sy1_cut = 1
		IF NOT IS_CAR_DEAD sy1_ryder_car
		AND NOT IS_CHAR_DEAD sy1_ryder
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	sy1_ryder_car
				OPEN_SEQUENCE_TASK sy1_ryder_walk
					TASK_LEAVE_CAR -1 sy1_ryder_car
					TASK_GO_STRAIGHT_TO_COORD -1 -2151.4746 -2442.0647 29.6328 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2157.3882 -2441.6162 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2162.4712 -2444.5745 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2167.3086 -2451.8782 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2166.5168 -2456.9656 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2160.4199 -2461.4680 29.6328 PEDMOVE_WALK -2
				CLOSE_SEQUENCE_TASK	sy1_ryder_walk
				PERFORM_SEQUENCE_TASK sy1_ryder sy1_ryder_walk
				sy1_cut = 2
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 2
		IF NOT IS_CAR_DEAD sy1_ryder_car
		AND NOT IS_CHAR_DEAD sy1_ryder
			IF NOT IS_CHAR_IN_CAR sy1_ryder sy1_ryder_car
				ADD_BLIP_FOR_CHAR sy1_ryder sy1_ryder_blip
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_ryder
				ENDIF
				PRINT_NOW ( SYN1_04 ) 5000 1 // Take the photograph
				// Take the photo
				sy1_cut = 3
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 3
		IF NOT IS_CHAR_DEAD sy1_ryder
			GET_SCRIPT_TASK_STATUS sy1_ryder PERFORM_SEQUENCE_TASK sy1_ryder_status
			IF sy1_ryder_status = FINISHED_TASK
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD sy1_dummy_car
					DETACH_CHAR_FROM_CAR scplayer
					DELETE_CAR sy1_dummy_car
					SET_CHAR_COORDINATES scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
					SET_CHAR_HEADING scplayer 210.0
					PRINT_NOW ( SYN1_09 ) 7500 1 // ~r~You didn't get the photo!
					GOTO mission_failed_syn1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 3
		IF sy1_photo_targets = 0
			IF NOT IS_CHAR_DEAD sy1_ryder
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_ryder
					IF sy1_fov < sy1_ideal_fov
						GET_CHAR_HEADING sy1_ryder sy1_ryder_h
						IF sy1_ryder_h > sy1_upper_heading
						OR	sy1_ryder_h < sy1_lower_heading
							IF sy1_audio_playing = 0
								CLEAR_PRINTS
								//PRINT ( SYN1_11 ) 3000 1 // Got him!
								//ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
								REMOVE_BLIP sy1_ryder_blip
								GOSUB sy1_tbone_car_create
								sy1_audio_seq = 0
								sy1_cut = 4
							ENDIF
						ELSE
							PRINT ( SYN1_10 ) 3000 1 // Get a photo of his face!
							IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_ryder
							ENDIF
						ENDIF
					ELSE
						PRINT ( SYN1_05 ) 3000 1 // Zoom in further
						IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_ryder
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut > 3
	AND sy1_cut < 7
		IF sy1_photo_targets = 0
			IF sy1_audio_playing = 0
				IF sy1_audio_seq = 0
					sy1_counter = 20 
				ENDIF
				IF sy1_audio_seq = 1
					sy1_counter = 22
				ENDIF
				IF sy1_audio_seq = 2
					sy1_counter = 27
					sy1_photo_targets = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF

// ---- TBone
	IF sy1_cut = 4
		IF NOT IS_CAR_DEAD sy1_tbone_car
		AND NOT IS_CHAR_DEAD sy1_tbone
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	sy1_tbone_car
				OPEN_SEQUENCE_TASK sy1_tbone_walk
					TASK_LEAVE_CAR -1 sy1_tbone_car
					TASK_GO_STRAIGHT_TO_COORD -1 -2156.6895 -2441.7119 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2161.1423 -2444.1865 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2165.8298 -2449.4763 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2167.1379 -2454.5098 29.6328 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2164.0945 -2458.8633 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2161.3364 -2460.5225 29.6328 PEDMOVE_WALK -2
				CLOSE_SEQUENCE_TASK	sy1_tbone_walk
				PERFORM_SEQUENCE_TASK sy1_tbone sy1_tbone_walk
				sy1_cut = 5
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 5
		IF NOT IS_CAR_DEAD sy1_tbone_car
		AND NOT IS_CHAR_DEAD sy1_tbone
			IF NOT IS_CHAR_IN_CAR sy1_tbone sy1_tbone_car
				ADD_BLIP_FOR_CHAR sy1_tbone sy1_tbone_blip
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_tbone
				ENDIF
				PRINT_NOW ( SYN1_04 ) 5000 1 // Take the photograph
				// Take the photo
				sy1_cut = 6
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 6
		IF NOT IS_CHAR_DEAD sy1_tbone
			GET_SCRIPT_TASK_STATUS sy1_tbone PERFORM_SEQUENCE_TASK sy1_tbone_status
			IF sy1_tbone_status = FINISHED_TASK
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD sy1_dummy_car
					DETACH_CHAR_FROM_CAR scplayer
					DELETE_CAR sy1_dummy_car
					SET_CHAR_COORDINATES scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
					SET_CHAR_HEADING scplayer 210.0
					PRINT_NOW ( SYN1_09 ) 7500 1 // ~r~You didn't get the photo!
					GOTO mission_failed_syn1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 6
		IF sy1_photo_targets = 1
			IF NOT IS_CHAR_DEAD sy1_tbone
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_tbone
					IF sy1_fov < sy1_ideal_fov
						GET_CHAR_HEADING sy1_tbone sy1_tbone_h
						IF sy1_tbone_h > sy1_upper_heading
						OR	sy1_tbone_h < sy1_lower_heading
							IF sy1_audio_playing = 0
								CLEAR_PRINTS
								//PRINT ( SYN1_11 ) 3000 1 // Got him!
								//ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
								REMOVE_BLIP sy1_tbone_blip
								GOSUB sy1_toreno_car_create
								sy1_audio_seq = 0
								sy1_cut = 7
							ENDIF
						ELSE
							PRINT ( SYN1_10 ) 3000 1 // Get a photo of his face!
							IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_tbone
							ENDIF
						ENDIF
					ELSE
						PRINT ( SYN1_05 ) 3000 1 // Zoom in further
						IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_tbone
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut > 6
	AND sy1_cut < 10
		IF sy1_photo_targets = 1
			IF sy1_audio_playing = 0
				IF sy1_audio_seq = 0
					sy1_counter = 32 
				ENDIF
				IF sy1_audio_seq = 1
					sy1_counter = 33
				ENDIF
				IF sy1_audio_seq = 2
					sy1_counter = 30
					sy1_photo_targets = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF

// ---- toreno
	IF sy1_cut = 7
		IF NOT IS_CAR_DEAD sy1_toreno_car
		AND NOT IS_CHAR_DEAD sy1_toreno
			IF IS_PLAYBACK_GOING_ON_FOR_CAR	sy1_toreno_car
				GET_OFFSET_FROM_CAR_IN_WORLD_COORDS sy1_toreno_car 0.0 4.0 0.0 sy1_offset_x sy1_offset_y sy1_offset_z
				sy1_min_x = sy1_offset_x - 1.0
				sy1_min_y = sy1_offset_y - 1.0
				sy1_min_z = sy1_offset_z - 1.0
				sy1_max_x = sy1_offset_x + 1.0
				sy1_max_y = sy1_offset_y + 1.0
				sy1_max_z = sy1_offset_z + 1.0
				IF IS_AREA_OCCUPIED sy1_min_x sy1_min_y sy1_min_z sy1_max_x sy1_max_y sy1_max_z FALSE FALSE TRUE FALSE FALSE
					STOP_PLAYBACK_RECORDED_CAR sy1_toreno_car
				ENDIF
			ENDIF
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	sy1_toreno_car
				
				OPEN_SEQUENCE_TASK sy1_toreno_walk
					TASK_LEAVE_CAR -1 sy1_toreno_car
					TASK_GO_STRAIGHT_TO_COORD -1 -2176.8550 -2453.5398 29.4688 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2172.5171 -2453.8345 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2169.3833 -2456.0796 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2162.8123 -2459.8394 29.6328 PEDMOVE_WALK -2
				CLOSE_SEQUENCE_TASK	sy1_toreno_walk
				PERFORM_SEQUENCE_TASK sy1_toreno sy1_toreno_walk
				sy1_cut = 8
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 8
		IF NOT IS_CAR_DEAD sy1_toreno_car
		AND NOT IS_CHAR_DEAD sy1_toreno
			IF NOT IS_CHAR_IN_CAR sy1_toreno sy1_toreno_car
				ADD_BLIP_FOR_CHAR sy1_toreno sy1_toreno_blip
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_toreno
				ENDIF
				PRINT_NOW ( SYN1_04 ) 5000 1 // Take the photograph
				// Take the photo
				sy1_cut = 9
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 9
		IF NOT IS_CHAR_DEAD sy1_toreno
			GET_SCRIPT_TASK_STATUS sy1_toreno PERFORM_SEQUENCE_TASK sy1_toreno_status
			IF sy1_toreno_status = FINISHED_TASK
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD sy1_dummy_car
					DETACH_CHAR_FROM_CAR scplayer
					DELETE_CAR sy1_dummy_car
					SET_CHAR_COORDINATES scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
					SET_CHAR_HEADING scplayer 210.0
					PRINT_NOW ( SYN1_09 ) 7500 1 // ~r~You didn't get the photo!
					GOTO mission_failed_syn1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 9
		IF sy1_photo_targets = 2
			IF NOT IS_CHAR_DEAD sy1_toreno
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_toreno
					IF sy1_fov < sy1_ideal_fov
						GET_CHAR_HEADING sy1_toreno sy1_toreno_h
						IF sy1_toreno_h > sy1_upper_heading
						OR	sy1_toreno_h < sy1_lower_heading
							IF sy1_audio_playing = 0
								CLEAR_PRINTS
								//PRINT ( SYN1_11 ) 3000 1 // Got him!
								//ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
								REMOVE_BLIP sy1_toreno_blip
								GOSUB sy1_jizzy_car_create
								sy1_audio_seq = 0
								sy1_cut = 10
							ENDIF
						ELSE
							PRINT ( SYN1_10 ) 3000 1 // Get a photo of his face!
							IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_toreno
							ENDIF
						ENDIF
					ELSE
						PRINT ( SYN1_05 ) 3000 1 // Zoom in further
						IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_toreno
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut > 9
	AND sy1_cut < 13
		IF sy1_photo_targets = 2
			IF sy1_audio_playing = 0
				IF sy1_audio_seq = 0
					sy1_counter = 42 
				ENDIF
				IF sy1_audio_seq = 1
					sy1_counter = 43
				ENDIF
				IF sy1_audio_seq = 2
					sy1_counter = 39
					sy1_photo_targets = 3
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	// ---- jizzy
	IF sy1_cut = 10
		IF NOT IS_CAR_DEAD sy1_jizzy_car
		AND NOT IS_CHAR_DEAD sy1_jizzy
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	sy1_jizzy_car
				OPEN_SEQUENCE_TASK sy1_jizzy_walk
					TASK_LEAVE_CAR -1 sy1_jizzy_car
					TASK_GO_STRAIGHT_TO_COORD -1 -2173.3906 -2445.9854 29.4688 PEDMOVE_WALK -2
					TASK_STAND_STILL -1 1500
					TASK_GO_STRAIGHT_TO_COORD -1 -2170.4680 -2449.1865 29.4688 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2167.9741 -2454.9741 29.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2163.5393 -2458.7529 29.6250 PEDMOVE_WALK -2
				CLOSE_SEQUENCE_TASK	sy1_jizzy_walk
				PERFORM_SEQUENCE_TASK sy1_jizzy sy1_jizzy_walk
				sy1_cut = 11
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 11
		IF NOT IS_CAR_DEAD sy1_jizzy_car
		AND NOT IS_CHAR_DEAD sy1_jizzy
			IF NOT IS_CHAR_IN_CAR sy1_jizzy sy1_jizzy_car
				ADD_BLIP_FOR_CHAR sy1_jizzy sy1_jizzy_blip
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_jizzy
				ENDIF
				PRINT_NOW ( SYN1_04 ) 5000 1 // Take the photograph
				// Take the photo
				sy1_cut = 12
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 12
		IF NOT IS_CHAR_DEAD sy1_jizzy
			GET_SCRIPT_TASK_STATUS sy1_jizzy PERFORM_SEQUENCE_TASK sy1_jizzy_status
			IF NOT sy1_jizzy_status = FINISHED_TASK
				GET_SEQUENCE_PROGRESS sy1_jizzy sy1_jizzy_progress
				IF sy1_jizzy_progress = 1
					IF NOT IS_CAR_DEAD sy1_jizzy_car
						IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR	sy1_jizzy_car
							START_PLAYBACK_RECORDED_CAR	sy1_jizzy_car 194
						ENDIF
					ENDIF
				ENDIF 
			ENDIF
			IF sy1_jizzy_status = FINISHED_TASK
				DO_FADE 500 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				IF NOT IS_CAR_DEAD sy1_dummy_car
					DETACH_CHAR_FROM_CAR scplayer
					DELETE_CAR sy1_dummy_car
					SET_CHAR_COORDINATES scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
					SET_CHAR_HEADING scplayer 210.0
					PRINT_NOW ( SYN1_09 ) 7500 1 // ~r~You didn't get the photo!
					GOTO mission_failed_syn1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 12
		IF sy1_photo_targets = 3
			IF NOT IS_CHAR_DEAD sy1_jizzy
				IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_jizzy
					IF sy1_fov < sy1_ideal_fov
						GET_CHAR_HEADING sy1_jizzy sy1_jizzy_h
						IF sy1_jizzy_h > sy1_upper_heading
						OR	sy1_jizzy_h < sy1_lower_heading
							IF sy1_audio_playing = 0
								CLEAR_PRINTS
								//ADD_ONE_OFF_SOUND 0.0 0.0 0.0 SOUND_PART_MISSION_COMPLETE
								REMOVE_BLIP sy1_jizzy_blip
								sy1_audio_seq = 0
								sy1_cut = 13
							ENDIF
						ELSE
							PRINT ( SYN1_10 ) 3000 1 // Get a photo of his face!
							IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_jizzy
							ENDIF
						ENDIF
					ELSE
						PRINT ( SYN1_05 ) 3000 1 // Zoom in further
						IF HAS_CHAR_BEEN_PHOTOGRAPHED sy1_jizzy
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 13
		IF sy1_photo_targets = 3
			IF sy1_audio_playing = 0
				IF sy1_audio_seq = 0
					sy1_counter = 40 
				ENDIF
				IF sy1_audio_seq = 1
					sy1_counter = 41
				ENDIF
				IF sy1_audio_seq = 2
					sy1_counter = 29
					sy1_photo_targets = 4
					sy1_stage = 6
					sy1_cut = -3
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

//-2170.2451 -2410.9556 33.2969  
//-2169.0303 -2409.3274 33.6180  
//-2165.3877 -2411.7463 31.6250  
//-2161.8145 -2414.8108 29.6250 


IF sy1_stage = 6
	IF sy1_cut = -3
		IF sy1_counter = 0
			DO_FADE 150 FADE_OUT
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			CLEAR_HELP
			SET_PLAYER_CONTROL player1 OFF
			SWITCH_WIDESCREEN ON
			IF NOT IS_CHAR_DEAD sy1_cesar
				DETACH_CHAR_FROM_CAR scplayer
				IF NOT IS_CAR_DEAD sy1_dummy_car
					DELETE_CAR sy1_dummy_car
				ENDIF
				SET_FIXED_CAMERA_POSITION -2161.7197 -2411.5823 31.9003  0.0 0.0 0.0
				POINT_CAMERA_AT_POINT -2162.6575 -2411.3965 32.1930 JUMP_CUT
				SET_CHAR_COORDINATES sy1_cesar -2169.0303 -2409.3274 33.6180
				SET_CHAR_HEADING sy1_cesar 232.4401
				SET_CHAR_COORDINATES scplayer -2170.2451 -2410.9556 33.2969
				SET_CHAR_HEADING scplayer 322.3848
				OPEN_SEQUENCE_TASK sy1_down_stairs
					TASK_GO_STRAIGHT_TO_COORD -1 -2169.0303 -2409.3274 33.6180 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2165.3877 -2411.7463 31.6250 PEDMOVE_WALK -2
					TASK_GO_STRAIGHT_TO_COORD -1 -2161.8145 -2414.8108 29.6250 PEDMOVE_RUN -2
				CLOSE_SEQUENCE_TASK sy1_down_stairs
				sy1_cut = -2
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = -2
		IF NOT IS_CHAR_DEAD sy1_cesar
			PERFORM_SEQUENCE_TASK sy1_cesar sy1_down_stairs
			PERFORM_SEQUENCE_TASK scplayer sy1_down_stairs
			DO_FADE 150 FADE_IN
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			GET_GAME_TIMER sy1_text_timer_start
			sy1_cut = -1
		ENDIF
	ENDIF
	IF sy1_cut = -1
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 4500
			sy1_cut = 0
		ENDIF
	ENDIF
	IF sy1_cut = 0
		DO_FADE 250 FADE_OUT
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE	
		
		IF NOT IS_CAR_DEAD sy1_cesar_car
		AND NOT IS_CHAR_DEAD sy1_cesar
			CLEAR_PRINTS
			
			WARP_CHAR_INTO_CAR scplayer sy1_cesar_car
			WARP_CHAR_INTO_CAR_AS_PASSENGER sy1_cesar sy1_cesar_car 0
	//		SET_CHAR_COORDINATES scplayer sy1_dummy_car_x sy1_dummy_car_y sy1_dummy_car_z
	//		SET_CHAR_HEADING scplayer 210.0
	//		GOTO mission_passed_syn1
			//LOAD_SCENE sy1_cam_x[10] sy1_cam_y[10] sy1_cam_z[10]
			MARK_MODEL_AS_NO_LONGER_NEEDED BROADWAY
			UNLOAD_SPECIAL_CHARACTER 4
			UNLOAD_SPECIAL_CHARACTER 5
			
			SET_FIXED_CAMERA_POSITION -2152.2551 -2416.0493 30.2724  0.0 0.0 0.0
			POINT_CAMERA_AT_POINT -2153.1738 -2415.7009 30.4581 JUMP_CUT
			REQUEST_CAR_RECORDING 197
			WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 197
			WAIT 0
			ENDWHILE
			IF NOT IS_CAR_DEAD sy1_cesar_car
				START_PLAYBACK_RECORDED_CAR sy1_cesar_car 197
				GET_GAME_TIMER sy1_text_timer_start
			ENDIF
			DO_FADE 100 FADE_IN
			WHILE GET_FADING_STATUS
			WAIT 0
			ENDWHILE
			IF NOT IS_CHAR_DEAD sy1_cesar
				IF IS_GROUP_MEMBER sy1_cesar players_group
					REMOVE_CHAR_FROM_GROUP sy1_cesar
				ENDIF
			ENDIF
			sy1_cut = 1
			sy1_temp_flag = 1
		ENDIF
	ENDIF
	IF sy1_cut = 1
		IF NOT IS_CAR_DEAD sy1_cesar_car
			GET_GAME_TIMER sy1_text_timer_end
			sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
			IF sy1_temp_flag = 1
				IF sy1_text_timer_diff > 1000
					POINT_CAMERA_AT_POINT -2152.8691 -2416.8384 30.2850 INTERPOLATION
					sy1_temp_flag = 2
				ENDIF
			ENDIF
			IF sy1_temp_flag = 2
				IF sy1_text_timer_diff > 6000
					SET_FIXED_CAMERA_POSITION -2267.2942 -2565.4023 31.7381 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2266.4109 -2564.9900 31.9617 JUMP_CUT
					sy1_temp_flag = 3
				ENDIF
			ENDIF
			IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR sy1_cesar_car
				IF NOT IS_CAR_DEAD sy1_cesar_car
				AND NOT IS_CHAR_DEAD sy1_cesar
					TASK_LEAVE_CAR scplayer sy1_cesar_car
					TASK_LOOK_AT_CHAR sy1_cesar scplayer -2
					CLEAR_AREA sy1_cam_x[10] sy1_cam_y[10] sy1_cam_z[10] 50.0 TRUE
	//				SET_FIXED_CAMERA_POSITION sy1_cam_x[10] sy1_cam_y[10] sy1_cam_z[10] 0.0 0.0 0.0
	//				POINT_CAMERA_AT_POINT sy1_cam_look_x[10] sy1_cam_look_y[10] sy1_cam_look_z[10] JUMP_CUT
					REQUEST_CAR_RECORDING 198
					WHILE NOT HAS_CAR_RECORDING_BEEN_LOADED 198
					WAIT 0
					ENDWHILE
					GET_GAME_TIMER sy1_text_timer_start
					sy1_cut = 2
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 2
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 2000
			IF NOT IS_CHAR_DEAD scplayer
			AND NOT IS_CHAR_DEAD sy1_cesar
			AND NOT IS_CAR_DEAD	sy1_cesar_car
				IF sy1_audio_playing = 0
					TASK_SHUFFLE_TO_NEXT_CAR_SEAT sy1_cesar sy1_cesar_car
					sy1_counter = 50 // That was some heavy shit!
					TASK_LOOK_AT_CHAR scplayer sy1_cesar -2
					TASK_TURN_CHAR_TO_FACE_CHAR	scplayer sy1_cesar
					SET_FIXED_CAMERA_POSITION sy1_cam_x[11] sy1_cam_y[11] sy1_cam_z[11] 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT sy1_cam_look_x[11] sy1_cam_look_y[11] sy1_cam_look_z[11] JUMP_CUT
					GET_GAME_TIMER sy1_text_timer_start
					sy1_cut = 3
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 3
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF NOT IS_CHAR_DEAD sy1_cesar
			AND NOT IS_CAR_DEAD sy1_cesar_car
				IF sy1_audio_playing = 0
					sy1_counter = 51 // We'd better split up and get out of here.
					
					TASK_LOOK_AT_CHAR sy1_cesar scplayer -2
					sy1_cut = 4
					GET_GAME_TIMER sy1_text_timer_start
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 4
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1
			sy1_cut = 5
			GET_GAME_TIMER sy1_text_timer_start
		ENDIF
	ENDIF
	IF sy1_cut = 5
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 1000
			IF NOT IS_CHAR_DEAD sy1_cesar
			AND NOT IS_CAR_DEAD sy1_cesar_car
				IF sy1_audio_playing = 0
					sy1_counter = 52 // Coolio, dude. We got what we came for.
					CLEAR_LOOK_AT scplayer
					CLEAR_LOOK_AT sy1_cesar
					SET_FIXED_CAMERA_POSITION -2243.2078 -2568.7312 32.6032 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT -2243.9453 -2568.0559 32.6050 JUMP_CUT

					 
					START_PLAYBACK_RECORDED_CAR sy1_cesar_car 198

					TASK_GO_STRAIGHT_TO_COORD scplayer -2249.5090 -2562.9563 30.9255 PEDMOVE_WALK -2
					
					sy1_cut = 6
					GET_GAME_TIMER sy1_text_timer_start
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sy1_cut = 6
		GET_GAME_TIMER sy1_text_timer_end
		sy1_text_timer_diff = sy1_text_timer_end - sy1_text_timer_start
		IF sy1_text_timer_diff > 3000
			IF NOT IS_CHAR_DEAD sy1_cesar
			AND NOT IS_CAR_DEAD sy1_cesar_car
				IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR sy1_cesar_car
					CLEAR_CHAR_TASKS scplayer
					DELETE_CAR sy1_cesar_car
					DELETE_CHAR sy1_cesar
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					GOTO mission_passed_syn1
					sy1_cut = 7
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF




GOTO syn1_main_loop 

// ------------------------------------------------------------------------------------------------

// ------------------------------------------------------------------------------------------------


// ------------------------------------------------------------------------------------------------
// Mission Failed
mission_failed_syn1:
	DO_FADE 500 FADE_IN
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Passed
mission_passed_syn1:
DO_FADE 500 FADE_IN
flag_synd_mission_counter ++
REGISTER_MISSION_PASSED ( SYND_1 )
PLAYER_MADE_PROGRESS 1
IF IS_PLAYER_PLAYING player1
	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_CAMERA 24
ENDIF
PLAY_MISSION_PASSED_TUNE 1
//PRINT_BIG ( M_PASSD ) 5000 1 //"Mission Passed!"
////ADD_SCORE player1 200
//AWARD_PLAYER_MISSION_RESPECT 15
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 15 5000 1 //"Mission Passed!"
AWARD_PLAYER_MISSION_RESPECT 15 //amount of respect
CLEAR_WANTED_LEVEL player1
SET_INT_STAT PASSED_SYNDICATE1 1
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Cleanup
mission_cleanup_syn1:
// ---- Entities	+
	IF NOT IS_CHAR_DEAD scplayer
		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
	ENDIF
	IF NOT IS_CHAR_DEAD sy1_cesar
		SET_CHAR_PROOFS sy1_cesar FALSE FALSE FALSE FALSE FALSE
	ENDIF
	CLEAR_HELP
	REMOVE_BLIP sy1_ryder_blip
	REMOVE_BLIP sy1_tbone_blip
	REMOVE_BLIP sy1_toreno_blip
	REMOVE_BLIP sy1_jizzy_blip
	REMOVE_BLIP sy1_park_blip
	REMOVE_BLIP sy1_vantage_blip
	REMOVE_BLIP sy1_cesar_car_blip
	REMOVE_BLIP sy1_cesar_blip

	DELETE_CHAR sy1_ryder
	DELETE_CHAR	sy1_tbone
	DELETE_CHAR	sy1_toreno
	DELETE_CHAR	sy1_jizzy

	DELETE_CAR sy1_ryder_car
	DELETE_CAR sy1_tbone_car
	DELETE_CAR sy1_toreno_car
	DELETE_CAR sy1_jizzy_car
	IF NOT IS_CAR_DEAD sy1_cesar_car
		IF NOT IS_CHAR_IN_CAR scplayer sy1_cesar_car
			DELETE_CAR sy1_cesar_car
		ENDIF
	ENDIF

	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 2
	UNLOAD_SPECIAL_CHARACTER 3
	UNLOAD_SPECIAL_CHARACTER 4
	UNLOAD_SPECIAL_CHARACTER 5

	REMOVE_CHAR_ELEGANTLY sy1_cesar

	REMOVE_ANIMATION BOMBER

	MARK_MODEL_AS_NO_LONGER_NEEDED PICADOR
	MARK_MODEL_AS_NO_LONGER_NEEDED ZR350
	MARK_MODEL_AS_NO_LONGER_NEEDED WASHING
	MARK_MODEL_AS_NO_LONGER_NEEDED BROADWAY
	MARK_MODEL_AS_NO_LONGER_NEEDED SHFYPRO
	MARK_MODEL_AS_NO_LONGER_NEEDED CAMERA
	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
	MARK_MODEL_AS_NO_LONGER_NEEDED SAVANNA
	MARK_MODEL_AS_NO_LONGER_NEEDED MTBIKE
	MARK_MODEL_AS_NO_LONGER_NEEDED BALLAS1
	MARK_MODEL_AS_NO_LONGER_NEEDED WALTON

	SET_CAR_DENSITY_MULTIPLIER 1.0	
	SET_PED_DENSITY_MULTIPLIER 1.0

	SWITCH_CAR_GENERATOR car_gen_pine[0] 101
	SWITCH_CAR_GENERATOR car_gen_pine[1] 101
	SWITCH_CAR_GENERATOR car_gen_pine[2] 101

// ---- Models

// ----	Clear Script Stuff
		
	flag_player_on_mission = 0
	GET_GAME_TIMER timer_mobile_start
	MISSION_HAS_FINISHED
RETURN
// ------------------------------------------------------------------------------------------------
// ---- SubRoutines

sy1_player_car_check:
// Get Player's car...
	IF NOT IS_CHAR_DEAD scplayer
		IF sy1_in_car = 0
			IF sy1_grouped = 1
				IF sy1_meet = 1
					IF sy1_counter = 0
					AND sy1_audio_playing = 0
						PRINT_NOW ( SYN1_07 ) 100 1 // Get in the car.
					ENDIF
				ENDIF
			ENDIF
			IF NOT IS_CAR_DEAD sy1_cesar_car
				IF IS_CHAR_IN_CAR scplayer sy1_cesar_car
					REMOVE_BLIP	sy1_cesar_car_blip
					STORE_CAR_CHAR_IS_IN scplayer sy1_player_car
					// Destination blip
					IF sy1_grouped = 1
						// Destination blip
						ADD_BLIP_FOR_COORD sy1_park_x sy1_park_y sy1_park_z sy1_park_blip
					ENDIF
					IF sy1_dialogue = 5
						PRINT ( SYN1_13 ) 10000 1 // Track the car
					ENDIF
					sy1_in_car = 1
				ENDIF
			ENDIF
		ENDIF
		IF sy1_in_car = 1
			IF NOT IS_CAR_DEAD sy1_cesar_car
			AND NOT IS_CHAR_DEAD sy1_cesar
				IF sy1_grouped = 1
					IF sy1_skip = 0
						IF IS_CHAR_IN_CAR sy1_cesar sy1_cesar_car
							IF flag_syn1_skip2 = 1	
								SET_UP_SKIP_FOR_SPECIFIC_VEHICLE -2201.9124 -2279.0356 29.4688 230.5228	sy1_cesar_car
							ENDIF
							sy1_skip = 1
						ELSE
							CLEAR_SKIP
						ENDIF
					ENDIF
				ELSE
					sy1_skip = 0
					CLEAR_SKIP
				ENDIF

				IF NOT IS_CHAR_IN_CAR scplayer sy1_cesar_car
					IF sy1_grouped = 1
						// Destination blip
						REMOVE_BLIP sy1_park_blip
						REMOVE_BLIP	sy1_cesar_car_blip
						ADD_BLIP_FOR_CAR sy1_cesar_car sy1_cesar_car_blip
						SET_BLIP_AS_FRIENDLY sy1_cesar_car_blip TRUE
					ENDIF
					sy1_in_car = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF
RETURN


sy1_player_group_check:
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD sy1_cesar
		IF sy1_grouped = 0
			IF NOT IS_GROUP_MEMBER sy1_cesar players_group
				IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer sy1_cesar 8.0 8.0 8.0 FALSE
					IF sy1_in_car = 1
						REMOVE_BLIP sy1_park_blip
						ADD_BLIP_FOR_COORD sy1_park_x sy1_park_y sy1_park_z sy1_park_blip
					ELSE
						REMOVE_BLIP sy1_cesar_car_blip
						ADD_BLIP_FOR_CAR sy1_cesar_car sy1_cesar_car_blip
						SET_BLIP_AS_FRIENDLY sy1_cesar_car_blip TRUE
						
					ENDIF
					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
					SET_GROUP_MEMBER players_group sy1_cesar 
					REMOVE_BLIP sy1_cesar_blip
					sy1_meet = 1
		   			sy1_grouped = 1
				ENDIF
			ELSE 
				sy1_grouped = 1
			ENDIF
		ENDIF
		IF sy1_grouped = 1
			IF NOT IS_GROUP_MEMBER sy1_cesar players_group
				IF sy1_in_car = 1
					// Destination blip
					REMOVE_BLIP sy1_park_blip 
				ELSE
					REMOVE_BLIP sy1_cesar_car_blip
				ENDIF
				CLEAR_SKIP
				CLEAR_PRINTS
		 		ADD_BLIP_FOR_CHAR sy1_cesar sy1_cesar_blip
				SET_BLIP_AS_FRIENDLY sy1_cesar_blip TRUE
				IF sy1_meet = 1
					PRINT_NOW ( SYN1_02 ) 10000 1 // ~s~Go and pick up ~y~Cesar~s~.
				ENDIF
			   	sy1_grouped = 0
			ENDIF
		ENDIF
	ENDIF			
RETURN

sy1_dialogue_pos:
	IF sy1_counter = 1 // CJ.
	OR sy1_counter = 3 // My cousin in Los Santos called me. 
	OR sy1_counter = 4 // He gave me a tip about a Ballas car going San Fierro way to score yay.
	OR sy1_counter = 6 // Read your mind, holmes, 
	OR sy1_counter = 7 // I picked them up at the Mulholland Intersection and I'm tailing them now.
	OR sy1_counter = 9 // Better make it fast, holmes, these boys aren't hanging around!
		sy1_audio_char = 0
	ENDIF

	IF sy1_counter = 2 // You got it.
	OR sy1_counter = 5 // Damn, we gotta find out who's supplyin' those cats.
	OR sy1_counter = 8 // Ok, I'm coming out to meet you.
	OR sy1_counter = 10 // I've got an idea - I'll be there in a minute.
		sy1_audio_char = scplayer
	ENDIF

	IF sy1_counter = 11  // Hey, Cesar, I'm coming your way, where are you?
	OR sy1_counter = 14  // Hang on, I'll be there any minute!
		sy1_audio_char = scplayer
	ENDIF

	IF sy1_counter = 12  // Sitting by the damn road just South of Blueberry!
	OR sy1_counter = 13  // They spotted me and shot out my tyres!
		sy1_audio_char = 0
	ENDIF

	IF sy1_counter = 15  // Where to?
		sy1_audio_char = scplayer
	ENDIF

	IF sy1_counter = 16  // They were headed over Angel Pine way. Follow the road and maybe we can pick them up!
		sy1_audio_char = sy1_cesar
	ENDIF

	IF sy1_counter = 17  // There it is, holmes!
	OR sy1_counter = 19 // Who's that in the back?
	OR sy1_counter = 22 // This business is bigger than any gang, esse.
	OR sy1_counter = 23 // Someone's coming out of the diner's back door!
	OR sy1_counter = 25 // Neither, holmes, he's San Fierro Rifa!
	OR sy1_counter = 26 // Ryder's getting out of the car!
		sy1_audio_char = sy1_cesar
	ENDIF

	IF sy1_counter = 18 // That's our ballas car alright.
	OR sy1_counter = 20 // Ryder, you sherm-head!
	OR sy1_counter = 21 // What's he rolling with Ballas for?
	OR sy1_counter = 24 // Who's this guy, Aztecas? Vagos?
	OR sy1_counter = 27 // Ryder, you lap dog!
	OR sy1_counter = 28 // That must be the cash...
		sy1_audio_char = scplayer
	ENDIF

	IF sy1_counter = 29 // They're being clever about this, there's no exchange, nothing incriminating.
	OR sy1_counter = 30 // What now? Was that it?
	OR sy1_counter = 32 // This guy takes himself real serious!
	OR sy1_counter = 34 // There's the courier with a fresh load of yay for Ryder and his new friends.
		sy1_audio_char = scplayer
	ENDIF

	IF sy1_counter = 31 // Hey, holmes, check the van!
	OR sy1_counter = 33 // Oh, esse, that's T-Bone Mendez.
	OR sy1_counter = 35 // Who are all these guys?
		sy1_audio_char = sy1_cesar
	ENDIF
	
	IF sy1_counter = 36 // More San Fierro Rifa, by the looks of things.
	OR sy1_counter = 37 // They sure keep their security tight!
	OR sy1_counter = 38 // I didn't scope those guys, did you?
	OR sy1_counter = 39 // No. This is more than just a few thugs pushing product, this is seriously organised!
	OR sy1_counter = 40 // How many of these clowns are there?
	OR sy1_counter = 41 // I know a pimp when I see one!
		sy1_audio_char = 0
	ENDIF

	IF sy1_counter = 42 // Who's the gringo?
	OR sy1_counter = 43 // I don't like the look of that guy.
	OR sy1_counter = 44 // Uh-oh, I think he's clocked us!
	OR sy1_counter = 45 // Well we're not exactly inconspicuous up here.
	OR sy1_counter = 46 // Besides, what's he going to do, huh, throw stones at us?
		sy1_audio_char = 0
	ENDIF
	 
	IF sy1_counter = 47 // Oh fuck! OH FUCK!
	OR sy1_counter = 48 // Get us out of here!
	OR sy1_counter = 50 // That was some heavy shit!
	OR sy1_counter = 52 // Coolio, dude. We got what we came for
		sy1_audio_char = scplayer
	ENDIF

	IF sy1_counter = 49 // Pope crap!
	OR sy1_counter = 51 // We better split up and get outta here. I'll meet you back at the garage!
		sy1_audio_char = sy1_cesar
	ENDIF
RETURN

//sy1_smoke_a_blunt:
//	IF NOT IS_CHAR_DEAD sy1_ryder
//		IF IS_CHAR_PLAYING_ANIM sy1_ryder m_smk_in
//			GET_CHAR_ANIM_CURRENT_TIME sy1_ryder m_smk_in sy1_anim_time
//			
//			IF sy1_anim_flag = 0
//				IF sy1_anim_time >= 0.32499
//					CREATE_OBJECT cigar_glow sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z sy1_cigar_glow_object
//					ATTACH_OBJECT_TO_OBJECT sy1_cigar_glow_object sy1_cigar_object 0.0 0.0 0.0 0.0 0.0 0.0
//					sy1_anim_flag = 1
//				ENDIF
//			ENDIF
//			IF sy1_anim_flag = 1
//				IF sy1_anim_time >= 0.42999
//				AND	sy1_anim_time < 0.8
//				   	PLAY_FX_SYSTEM sy1_ryder_exhale
//					DELETE_OBJECT sy1_cigar_glow_object
//					sy1_anim_flag = 2
//				ENDIF
//			ENDIF
//			IF sy1_anim_flag = 2
//				IF sy1_anim_time >= 0.8
//				OR sy1_anim_time < 0.42999
//				   	STOP_FX_SYSTEM sy1_ryder_exhale
//					sy1_anim_flag = 0
//				ENDIF
//			ENDIF	
//
//		ELSE
//			STOP_FX_SYSTEM sy1_ryder_exhale
//		ENDIF
//	ENDIF
//
//	IF NOT IS_CHAR_DEAD sy1_ryder
//		IF IS_CHAR_PLAYING_ANIM sy1_ryder m_smk_drag
//			GET_CHAR_ANIM_CURRENT_TIME sy1_ryder m_smk_drag sy1_anim_time
//			
//			IF sy1_anim_flag = 0
//				IF sy1_anim_time >= 0.27273
//					CREATE_OBJECT cigar_glow sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z sy1_cigar_glow_object
//					ATTACH_OBJECT_TO_OBJECT sy1_cigar_glow_object sy1_cigar_object 0.0 0.0 0.0 0.0 0.0 0.0
//					sy1_anim_flag = 1
//				ENDIF
//			ENDIF
//			IF sy1_anim_flag = 1
//				IF sy1_anim_time >= 0.46364
//				AND	sy1_anim_time < 0.8
//				   	PLAY_FX_SYSTEM sy1_ryder_exhale
//					DELETE_OBJECT sy1_cigar_glow_object
//					sy1_anim_flag = 2
//				ENDIF
//			ENDIF
//			IF sy1_anim_flag = 2
//				IF sy1_anim_time >= 0.8
//				OR sy1_anim_time < 0.46364
//				   	STOP_FX_SYSTEM sy1_ryder_exhale
//					sy1_anim_flag = 0
//				ENDIF
//			ENDIF	
//
//		ELSE
//			STOP_FX_SYSTEM sy1_ryder_exhale
//		ENDIF
//	ENDIF
//	
//	IF NOT IS_CHAR_DEAD sy1_ryder
//		IF IS_CHAR_PLAYING_ANIM sy1_ryder m_smk_out
//			GET_CHAR_ANIM_CURRENT_TIME sy1_ryder m_smk_out sy1_anim_time
//			
//			IF sy1_anim_flag = 0
//				IF sy1_anim_time >= 0.27778
//					CREATE_OBJECT cigar_glow sy1_ryder_car_x sy1_ryder_car_y sy1_ryder_car_z sy1_cigar_glow_object
//					ATTACH_OBJECT_TO_OBJECT sy1_cigar_glow_object sy1_cigar_object 0.0 0.0 0.0 0.0 0.0 0.0
//					sy1_anim_flag = 1
//				ENDIF
//			ENDIF
//			IF sy1_anim_flag = 1
//				IF sy1_anim_time >= 0.51111
//				AND	sy1_anim_time < 0.86667
//				   	PLAY_FX_SYSTEM sy1_ryder_exhale
//					DELETE_OBJECT sy1_cigar_glow_object
//					sy1_anim_flag = 2
//				ENDIF
//			ENDIF
//			IF sy1_anim_flag = 2
//				IF sy1_anim_time >= 0.8667
//				OR sy1_anim_time < 0.27778
//				   	STOP_FX_SYSTEM sy1_ryder_exhale
//					DROP_OBJECT sy1_ryder TRUE
//					sy1_anim_flag = 0
//				ENDIF
//			ENDIF	
//
//		ELSE
//			STOP_FX_SYSTEM sy1_ryder_exhale
//		ENDIF
//	ENDIF
//
//RETURN

}
/*
(MOB_40) CESAR calls CARL

[PHO_A_1:SYN1] 
CESAR: CJ.
 
[PHO_A_2:SYN1] 
CARL: You got it.

[PHO_A_3:SYN1] 
CESAR: My cousin in Los Santos called me. He gave me a tip about a Ballas car going San Fierro way to score yay.

[PHO_A_4:SYN1] 
CARL: Damn, we gotta find out who's supplyin' those cats.
 
[PHO_A_5:SYN1] 
CESAR: Read your mind, holmes, I picked them up at the Mulholland Intersection and I'm tailing them now.

[PHO_A_6:SYN1] 
CARL: Ok, I'm coming out to meet you.
 
[PHO_A_7:SYN1] 
CESAR: Better make it fast, holmes, these boys aren't hanging around!
 
[PHO_A_8:SYN1] 
CARL: I've got an idea - I'll be there in a minute.

[PHO_B_1:SYN1]
PILOT: Hey, who are you?

[PHO_B_2:SYN1]
CARL: Eerr, Helmet Inspection Officer?

[PHO_B_3:SYN1]
CARL: Oh, look over there!
 
[PHO_B_4:SYN1]
PILOT: Where?
 
[PHO_B_5:SYN1]
CARL: There!
 
[PHO_B_6:SYN1]
PILOT: Ow! Ooomph! <thud>

[PHO_C_1:SYN1]
CARL: Hey, Cesar, I'm coming your way, where are you?

[PHO_C_2:SYN1]
CESAR: Sitting by the damn road just South of Blueberry!

[PHO_C_3:SYN1]
CESAR: They spotted me and shot out my tyres! 

[PHO_C_4:SYN1]
CARL: Hang on, I'll be there any minute!

[PHO_D_1:SYN1]
PILOT: Ok, sucker, the game's up!

[PHO_D_2:SYN1]
CARL: Is that a gun at the back of my head?

[PHO_D_3:SYN1]
PILOT: No, I'm just fucking pleased to see you!

[PHO_D_4:SYN1]
PILOT: Land this thing!

[PHO_D_5:SYN1]
PILOT: Enjoy the walk, asshole!

[PHO_D_6:SYN1]
CESAR: Shit, I'm outta here!

[PHO_E_1:SYN1]
CESAR: Wow! Holmes! I didn't know you could fly!

[PHO_E_2:SYN1]
CARL: I can't. I'm learning - get in.

[PHO_E_3:SYN1]
CESAR: There's somebody sleeping back here, eh.

[PHO_E_4:SYN1]
CARL: Yeah. Leave him, he should be ok.

[PHO_E_5:SYN1]
CARL: Where to?

[PHO_E_6:SYN1]
CESAR: They were headed over Angel Pine way. Follow the road and maybe we can pick them up!

[PHO_F_1:SYN1]
CESAR: Holmes, this dude's waking up!

[PHO_F_2:SYN1]
PILOT: I've been awake for ages - land this thing!

[PHO_F_3:SYN1]
CESAR: Dude, he's got a gun, we better land.

[PHO_F_4:SYN1]
PILOT: Screw you guys!

[PHO_F_5:SYN1]
CESAR: Shit, I'm outta here!

[PHO_G_1:SYN1]
CESAR: There it is, holmes, parked behind that diner!

[PHO_G_2:SYN1]
CARL: I brought a camera, take that stick and keep her steady!

[PHO_G_3:SYN1]
CESAR: Sweet Mother of Mary!

[PHO_G_4:SYN1]
CESAR: CJ, I can't fly this thing!

[PHO_G_5:SYN1]
CESAR: Hey you! Wake up, I know you're just pretending!

[PHO_G_6:SYN1]
PILOT: Ok, ok! Just don't shoot me!

[PHO_G_7:SYN1]
CARL: Keep her steady!

[PHO_H_1:SYN1]
CARL: That's our Balla car alright.

[PHO_H_2:SYN1]
CESAR: Who's that in the back?

[PHO_H_3:SYN1]
CARL: Ryder, you sherm-head! What's he rolling with Ballas for?

[PHO_H_4:SYN1]
CESAR: This business is bigger than any gang, esse.

[PHO_H_5:SYN1]
CESAR: Someone's coming out of the diner's back door.

[PHO_H_6:SYN1] 
CARL: Who's this guy - Aztecas? Vagos?

[PHO_H_7:SYN1]
CESAR: Neither, holmes, he's San Fierro Rifa.

[PHO_H_8:SYN1]
CESAR: Ryder's getting out of the car.

[PHO_H_9:SYN1]
CARL: Ryder, you lap dog! That must be the cash...

[PHO_H10:SYN1]
CARL: They're being clever about this -~n~there's no exchange, nothing incriminating.

[PHO_H11:SYN1]
CARL: What now? Was that it?

[PHO_H12:SYN1]
CESAR: Hey, holmes, check the van!

[PHO_H13:SYN1]
CARL: This guy takes himself real serious!

[PHO_H14:SYN1] 
CESAR: Oh, esse, that's T-Bone Mendez.

[PHO_H15:SYN1]
CESAR: He did time for running black tar out of Mexico.

[PHO_H16:SYN1]
CESAR: Killed two Aztecas inside - real bad blood with my esse's.

[PHO_H17:SYN1]
CESAR: He's a fucking psycho, man.

[PHO_H18:SYN1] 
CARL: There's the courier with a fresh load of yay for Ryder and his new friends.

[PHO_H19:SYN1]
CARL: Who are all these guys?

[PHO_H20:SYN1]
CARL: More San Fierro Rifa, by the looks of things.

[PHO_H21:SYN1]
CARL: They sure keep their security tight!

[PHO_H22:SYN1]
CESAR: I didn't scope those guys, did you?

[PHO_H23:SYN1] 
CARL: No, this is more than just a few thugs pushing product, ~n~this is seriously organised!

[PHO_H24:SYN1] 
CESAR: How many of these clowns are there?

[PHO_H25:SYN1]
CARL: I know a pimp when I see one!

[PHO_H26:SYN1]
CESAR: Who's the gringo?

[PHO_H27:SYN1]
CARL: I don't like the look of that guy.

[PHO_H28:SYN1]
CARL: Uh-oh, I think he's clocked us!

[PHO_H29:SYN1]
CESAR: Well, we're not exactly inconspicuous up here.

[PHO_H30:SYN1]
CESAR: Besides, what's he going to do, huh, throw stones at us?

[PHO_H31:SYN1]
CARL: Oh fuck! OH FUCK! Get us out of here!

[PHO_I_1:SYN1]
CESAR: Pope crap!

[PHO_I_2:SYN1]
CARL: That was some heavy shit!

[PHO_I_3:SYN1]
CESAR: We better split up and get out of here. I'll meet you back at the garage!

[PHO_I_4:SYN1]
CARL: Coolio, dude. We got what we came for.

[SYN1_01:SYN1]
~s~Go and steal a car.

[SYN1_02:SYN1]
~s~Go and pick up ~y~Cesar~s~.

[SYN1_03:SYN1]
~s~Track down that ~y~Balla car~s~.

[SYN1_04:SYN1]
~s~Take the ~r~photograph~s~.

[SYN1_05:SYN1]
~s~Zoom in further.

[SYN1_06:SYN1]
~r~Cesar didn't make it.




*/