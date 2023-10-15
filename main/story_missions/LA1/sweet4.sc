MISSION_START
// ------------------------------------------------------------------------------------------------
// Sweet Mission 4: Ghetto Drive By

{

SCRIPT_NAME sweet4

// Begin...
GOSUB mission_start_sweet4

	IF HAS_DEATHARREST_BEEN_EXECUTED
		GOSUB mission_failed_sweet4
	ENDIF

GOSUB mission_cleanup_sweet4

MISSION_END
// ------------------------------------------------------------------------------------------------
// Variables
// ---- Flags
	VAR_INT sw4_stage sw4_hood_acc sw4_ammo sw4_hood_dead[18] sw4_spray_marker
	VAR_INT  sw4_blipped sw4_cut sw4_attack[4] sw4_hood_create[4]
	LVAR_INT sw4_group_active sw4_group_dead[4] sw4_loop	sw4_side sw4_anim
	LVAR_INT sw4_create sw4_help sw4_stopped_text sw4_stopped sw4_in_car
	LVAR_INT sw4_decision sw4_on_fire
	LVAR_INT sw4_wanted_flag	sw4_skip_flag sw4_sequence_flag
	LVAR_INT sw4_cut_text sw4_max_wanted
// ---- Blips
	LVAR_FLOAT sw4_hood_x sw4_hood_y sw4_hood_z
	LVAR_INT sw4_hood_blip sw4_end_blip

// ---- Timers / Counters
	LVAR_INT sw4_timer_start[6] sw4_timer_end[6] sw4_timer_diff[6] sw4_dead_count
	LVAR_INT sw4_timer_remaining_secs sw4_timer_remaining
	LVAR_INT sw4_blip_timer_diff sw4_blip_timer_end sw4_blip_timer_start sw4_blip_counter
	VAR_INT sw4_text_timer_start sw4_text_timer_end sw4_text_timer_diff sw4_text_timer_flag
	LVAR_INT sw4_count
// ---- Coords
	LVAR_FLOAT sw4_group_loc
	LVAR_FLOAT sw4_gosub_x sw4_gosub_y sw4_gosub_z
	LVAR_FLOAT sw4_end_x sw4_end_y sw4_end_z
// ---- Sequences									 
	LVAR_INT sw4_flee_exp_car sw4_destroy_lead_car sw4_move sw4_flee_car sw4_drive_away sw4_smoke_enters_car
	LVAR_INT sw4_hood_react[16]	sw4_sweet_end_cut sw4_smoke_end_cut sw4_player_shake sw4_ryder_end_cut
	LVAR_INT sw4_hood_anim
// ---- Cameras
	LVAR_FLOAT sw4_cam_x[10] sw4_cam_y[10] sw4_cam_z[10]
// ---- Objects
	LVAR_INT sw4_gang_strength[40]

// ---- Entities
	LVAR_INT sw4_player_car_arrive sw4_hood_grp[4] sw4_gosub_char sw4_closest_ped
	LVAR_INT sw4_player_group sw4_passenger sw4_forty 
	VAR_INT sw4_health_display sw4_health
	LVAR_FLOAT sw4_closest_ped_dist sw4_gosub_dist sw4_anim_time

// ---- Dialogue
	LVAR_TEXT_LABEL sw4_text[62]
	LVAR_INT sw4_audio_char
	LVAR_INT sw4_audio[62] sw4_counter sw4_audio_playing sw4_audio_slot	sw4_alt_slot sw4_ahead_counter

// -------- Characters
	sw4_load_all:
		REQUEST_MODEL GREENWOO
		REQUEST_MODEL TEC9
		REQUEST_MODEL COLT45
		LOAD_SPECIAL_CHARACTER 10 smoke
		LOAD_SPECIAL_CHARACTER 2 ryder2
		LOAD_SPECIAL_CHARACTER 3 sweet

		LOAD_ALL_MODELS_NOW

		WHILE NOT HAS_MODEL_LOADED GREENWOO
		OR NOT HAS_MODEL_LOADED TEC9
		OR NOT HAS_MODEL_LOADED COLT45
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 10
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
		OR NOT HAS_SPECIAL_CHARACTER_LOADED 3
        WAIT 0
		ENDWHILE
	RETURN
// ---- Smoke  
	sw4_smoke_create:
 		LVAR_INT sw4_smoke sw4_smoke_blip sw4_smoke_status
		LVAR_FLOAT sw4_smoke_x sw4_smoke_y sw4_smoke_z sw4_smoke_h
		sw4_smoke_x = 2510.76 
		sw4_smoke_y = -1665.82
		sw4_smoke_z = 12.3835 
		sw4_smoke_h = 213.1638
		CLEAR_AREA sw4_smoke_x sw4_smoke_y sw4_smoke_z 1.0 TRUE 
		CREATE_CHAR PEDTYPE_CIVMALE SPECIAL10 sw4_smoke_x sw4_smoke_y sw4_smoke_z sw4_smoke
		SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sw4_smoke FALSE
		GIVE_WEAPON_TO_CHAR sw4_smoke WEAPONTYPE_TEC9 sw4_ammo
		SET_CURRENT_CHAR_WEAPON sw4_smoke WEAPONTYPE_TEC9
		SET_CHAR_HEADING sw4_smoke sw4_smoke_h
		SET_CHAR_ACCURACY sw4_smoke 90
		SET_ANIM_GROUP_FOR_CHAR sw4_smoke fatman
		SET_CHAR_PROOFS sw4_smoke TRUE FALSE FALSE FALSE FALSE
	RETURN
// --------	
// ---- Ryder	
	sw4_ryder_create:	
		LVAR_INT sw4_ryder sw4_ryder_blip sw4_ryder_status
		LVAR_FLOAT sw4_ryder_x sw4_ryder_y sw4_ryder_z sw4_ryder_h
		sw4_ryder_x = 2509.39 
		sw4_ryder_y = -1672.07
		sw4_ryder_z = 12.3835 
		sw4_ryder_h = 59.5920
		CLEAR_AREA sw4_ryder_x sw4_ryder_y sw4_ryder_z 1.0 TRUE 
		CREATE_CHAR PEDTYPE_CIVMALE SPECIAL02 sw4_ryder_x sw4_ryder_y sw4_ryder_z sw4_ryder
		SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sw4_ryder FALSE
		GIVE_WEAPON_TO_CHAR sw4_ryder WEAPONTYPE_TEC9 sw4_ammo
		SET_CURRENT_CHAR_WEAPON sw4_ryder WEAPONTYPE_TEC9
		SET_CHAR_HEADING sw4_ryder sw4_ryder_h
		SET_CHAR_ACCURACY sw4_ryder 90
		SET_ANIM_GROUP_FOR_CHAR sw4_ryder gang1
		SET_CHAR_PROOFS sw4_ryder TRUE FALSE FALSE FALSE FALSE
	RETURN
	sw4_ryder_delete:
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_ryder
	RETURN
// --------	
// ---- Sweet
	sw4_sweet_create:	
		LVAR_INT sw4_sweet sw4_sweet_blip sw4_sweet_status
		LVAR_FLOAT sw4_sweet_x sw4_sweet_y sw4_sweet_z sw4_sweet_h
		sw4_sweet_x = 2514.29 
		sw4_sweet_y = -1670.46
		sw4_sweet_z = 12.3835 
		sw4_sweet_h = 41.1276
		CLEAR_AREA sw4_sweet_x sw4_sweet_y sw4_sweet_z 1.0 TRUE 
		CREATE_CHAR PEDTYPE_CIVMALE SPECIAL03 sw4_sweet_x sw4_sweet_y sw4_sweet_z sw4_sweet
		SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR sw4_sweet FALSE
		GIVE_WEAPON_TO_CHAR sw4_sweet WEAPONTYPE_TEC9 sw4_ammo
		SET_CURRENT_CHAR_WEAPON sw4_sweet WEAPONTYPE_TEC9
		SET_CHAR_HEADING sw4_sweet sw4_sweet_h
		SET_CHAR_ACCURACY sw4_sweet 90
		SET_ANIM_GROUP_FOR_CHAR sw4_sweet gang2
		SET_CHAR_PROOFS sw4_sweet TRUE FALSE FALSE FALSE FALSE
	RETURN
// --------	
// ---- Flat Hoods
	sw4_flat_hood_create:
		LVAR_INT sw4_flat_hood[18] sw4_flat_hood_blip[18]
		LVAR_FLOAT sw4_flat_hood_x[18] sw4_flat_hood_y[18] sw4_flat_hood_z[18] sw4_flat_hood_h[18]
		LVAR_INT sw4_hood_car[4] 
		LVAR_FLOAT sw4_hood_car_x[4] sw4_hood_car_y[4] sw4_hood_car_z[4] sw4_hood_car_h[4]
		REQUEST_MODEL ballas1
		REQUEST_MODEL ballas2
		REQUEST_MODEL COLT45
		REQUEST_MODEL VOODOO
		REQUEST_MODEL SPRAYCAN
		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED ballas1
		OR NOT HAS_MODEL_LOADED ballas2
		OR NOT HAS_MODEL_LOADED COLT45
		OR NOT HAS_MODEL_LOADED VOODOO
		OR NOT HAS_MODEL_LOADED SPRAYCAN

		WAIT 0
		ENDWHILE
		REQUEST_ANIMATION GANGS
		REQUEST_ANIMATION GHANDS
		REQUEST_ANIMATION GHETTO_DB
		WHILE NOT HAS_ANIMATION_LOADED GANGS
		OR NOT HAS_ANIMATION_LOADED GHANDS
		OR NOT HAS_ANIMATION_LOADED GHETTO_DB
		WAIT 0
		ENDWHILE
		sw4_create = 1
	RETURN
	ADD_BLIP_FOR_COORD 2075.55 -1831.09 12.21 sw4_spray_marker
	CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[14] sw4_flat_hood_y[14] sw4_flat_hood_z[14] sw4_closest_ped
	CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[14] sw4_flat_hood_y[14] sw4_flat_hood_z[14] sw4_audio_char
	ADD_BLIP_FOR_CHAR sw4_smoke	sw4_smoke_blip
	ADD_BLIP_FOR_CHAR sw4_sweet	sw4_sweet_blip
	ADD_BLIP_FOR_CHAR sw4_ryder	sw4_ryder_blip
// ---- Group 1
	sw4_flat_hood_create1:
		sw4_hood_car_x[0] =	2077.18
		sw4_hood_car_y[0] =	-1362.05
		sw4_hood_car_z[0] =	23.65
		sw4_hood_car_h[0] =	180.0

		sw4_flat_hood_x[0] = 2079.42 
		sw4_flat_hood_y[0] = -1364.08
		sw4_flat_hood_z[0] = 23.04 
		sw4_flat_hood_h[0] = 314.1323

		CLEAR_AREA sw4_hood_car_x[0] sw4_hood_car_y[0] sw4_hood_car_z[0] 0.5 TRUE
		CREATE_CAR VOODOO sw4_hood_car_x[0] sw4_hood_car_y[0] sw4_hood_car_z[0] sw4_hood_car[0]
		CREATE_CHAR_INSIDE_CAR sw4_hood_car[0] PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood[0]
		SET_CAR_HEADING sw4_hood_car[0]	sw4_hood_car_h[0]
		SUPPRESS_CAR_MODEL VOODOO
		OPEN_CAR_DOOR sw4_hood_car[0] FRONT_LEFT_DOOR
		//CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[0] sw4_flat_hood_y[0] sw4_flat_hood_z[0] sw4_flat_hood[0]
		//SET_CHAR_HEADING sw4_flat_hood[0] sw4_flat_hood_h[0]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[0] WEAPONTYPE_TEC9 sw4_ammo
		SET_CHAR_ACCURACY sw4_flat_hood[0] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR sw4_flat_hood[0] sw4_flat_hood_blip[0]
		ENDIF
		
		sw4_flat_hood_x[1] = 2075.95
		sw4_flat_hood_y[1] = -1365.74
		sw4_flat_hood_z[1] = 24.02
		sw4_flat_hood_h[1] = 315.85 
		CLEAR_AREA sw4_flat_hood_x[1] sw4_flat_hood_y[1] sw4_flat_hood_z[1] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[1] sw4_flat_hood_y[1] sw4_flat_hood_z[1] sw4_flat_hood[1]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[1] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[1] sw4_flat_hood_h[1]
		SET_CHAR_ACCURACY sw4_flat_hood[1] sw4_hood_acc
		
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR sw4_flat_hood[1] sw4_flat_hood_blip[1]
		ENDIF

		sw4_flat_hood_x[2] = 2079.87
		sw4_flat_hood_y[2] = -1361.88
		sw4_flat_hood_z[2] = 24.02
		sw4_flat_hood_h[2] = 54.15 
		CLEAR_AREA sw4_flat_hood_x[2] sw4_flat_hood_y[2] sw4_flat_hood_z[2] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[2] sw4_flat_hood_y[2] sw4_flat_hood_z[2] sw4_flat_hood[2]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[2] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[2] sw4_flat_hood_h[2]
		SET_CHAR_ACCURACY sw4_flat_hood[2] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[2] sw4_flat_hood_blip[2]
		ENDIF

		sw4_flat_hood_x[3] = 2079.34
		sw4_flat_hood_y[3] = -1360.02
		sw4_flat_hood_z[3] = 24.02
		sw4_flat_hood_h[3] = 214.36 
		CLEAR_AREA sw4_flat_hood_x[3] sw4_flat_hood_y[3] sw4_flat_hood_z[3] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[3] sw4_flat_hood_y[3] sw4_flat_hood_z[3] sw4_flat_hood[3]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[3] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[3] sw4_flat_hood_h[3]
		SET_CHAR_ACCURACY sw4_flat_hood[3] sw4_hood_acc
		

		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[3] sw4_flat_hood_blip[3]
		ENDIF
	RETURN
// ---- Group 2
	sw4_flat_hood_create2:
		sw4_hood_car_x[1] =	2101.57
		sw4_hood_car_y[1] =	-1287.65
		sw4_hood_car_z[1] =	24.17
		sw4_hood_car_h[1] =	5.0
		
		sw4_flat_hood_x[4] = 2101.57
		sw4_flat_hood_y[4] = -1287.65 
		sw4_flat_hood_z[4] = 24.17
		sw4_flat_hood_h[4] = 5.0
		
		CLEAR_AREA sw4_flat_hood_x[4] sw4_flat_hood_y[4] sw4_flat_hood_z[4] 0.5 TRUE
		CREATE_CAR VOODOO sw4_hood_car_x[1] sw4_hood_car_y[1] sw4_hood_car_z[1] sw4_hood_car[1]
		CREATE_CHAR_INSIDE_CAR sw4_hood_car[1] PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood[4]
		SET_CAR_HEADING sw4_hood_car[1]	sw4_hood_car_h[1]
		POP_CAR_BOOT sw4_hood_car[1] 
		//CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[4] sw4_flat_hood_y[4] sw4_flat_hood_z[4] sw4_flat_hood[4]
		//SET_CHAR_HEADING sw4_flat_hood[4] sw4_flat_hood_h[4]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[4] WEAPONTYPE_TEC9 sw4_ammo
		SET_CHAR_ACCURACY sw4_flat_hood[4] sw4_hood_acc
		
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[4] sw4_flat_hood_blip[4]
		ENDIF

		sw4_flat_hood_x[5] = 2101.58
		sw4_flat_hood_y[5] = -1291.78
		sw4_flat_hood_z[5] = 24.02
		sw4_flat_hood_h[5] = 182.8706 
		CLEAR_AREA sw4_flat_hood_x[5] sw4_flat_hood_y[5] sw4_flat_hood_z[5] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[5] sw4_flat_hood_y[5] sw4_flat_hood_z[5] sw4_flat_hood[5]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[5] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[5] sw4_flat_hood_h[5]
		SET_CHAR_ACCURACY sw4_flat_hood[5] sw4_hood_acc
		
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[5] sw4_flat_hood_blip[5]
		ENDIF
		
		sw4_flat_hood_x[6] = 2099.30 
		sw4_flat_hood_y[6] = -1291.12 
		sw4_flat_hood_z[6] = 24.02
		sw4_flat_hood_h[6] = 105.53 
		CLEAR_AREA sw4_flat_hood_x[6] sw4_flat_hood_y[6] sw4_flat_hood_z[6] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[6] sw4_flat_hood_y[6] sw4_flat_hood_z[6] sw4_flat_hood[6]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[6] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[6] sw4_flat_hood_h[6]
		SET_CHAR_ACCURACY sw4_flat_hood[6] sw4_hood_acc
		
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[6] sw4_flat_hood_blip[6]
		ENDIF
 
		sw4_flat_hood_x[7] = 2093.84 
		sw4_flat_hood_y[7] = -1290.07 
		sw4_flat_hood_z[7] = 23.15
		sw4_flat_hood_h[7] = 58.035 
		CLEAR_AREA sw4_flat_hood_x[7] sw4_flat_hood_y[7] sw4_flat_hood_z[7] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[7] sw4_flat_hood_y[7] sw4_flat_hood_z[7] sw4_flat_hood[7]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[7] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[7] sw4_flat_hood_h[7]
		SET_CHAR_ACCURACY sw4_flat_hood[7] sw4_hood_acc

		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[7] sw4_flat_hood_blip[7]
		ENDIF
	RETURN

// ---- Group 3
	sw4_flat_hood_create3:
		sw4_flat_hood_x[8] = 2161.07  
		sw4_flat_hood_y[8] = -1265.92 
		sw4_flat_hood_z[8] = 23.02  
		sw4_flat_hood_h[8] = 257.59 
		CLEAR_AREA sw4_flat_hood_x[8] sw4_flat_hood_y[8] sw4_flat_hood_z[8] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[8] sw4_flat_hood_y[8] sw4_flat_hood_z[8] sw4_flat_hood[8]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[8] WEAPONTYPE_TEC9 sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[8] sw4_flat_hood_h[8]
		SET_CHAR_ACCURACY sw4_flat_hood[8] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[8] sw4_flat_hood_blip[8]
		ENDIF

		sw4_flat_hood_x[9] = 2162.18  
		sw4_flat_hood_y[9] = -1264.69 
		sw4_flat_hood_z[9] = 23.03  
		sw4_flat_hood_h[9] = 238.46 
		CLEAR_AREA sw4_flat_hood_x[9] sw4_flat_hood_y[9] sw4_flat_hood_z[9] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[9] sw4_flat_hood_y[9] sw4_flat_hood_z[9] sw4_flat_hood[9]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[9] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[9] sw4_flat_hood_h[9]
		SET_CHAR_ACCURACY sw4_flat_hood[9] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[9] sw4_flat_hood_blip[9] 
		ENDIF

		sw4_flat_hood_x[10] = 2162.97  
		sw4_flat_hood_y[10] = -1261.92 
		sw4_flat_hood_z[10] = 23.02  
		sw4_flat_hood_h[10] = 45.33 
		CLEAR_AREA sw4_flat_hood_x[10] sw4_flat_hood_y[10] sw4_flat_hood_z[10] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[10] sw4_flat_hood_y[10] sw4_flat_hood_z[10] sw4_flat_hood[10]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[10] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[10] sw4_flat_hood_h[10]
		SET_CHAR_ACCURACY sw4_flat_hood[10] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[10] sw4_flat_hood_blip[10]
		ENDIF

		sw4_flat_hood_x[11] = 2160.12  
		sw4_flat_hood_y[11] = -1262.98 
		sw4_flat_hood_z[11] = 23.03  
		sw4_flat_hood_h[11] = 18.46 
		CLEAR_AREA sw4_flat_hood_x[11] sw4_flat_hood_y[11] sw4_flat_hood_z[11] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[11] sw4_flat_hood_y[11] sw4_flat_hood_z[11] sw4_flat_hood[11]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[11] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[11] sw4_flat_hood_h[11]
		SET_CHAR_ACCURACY sw4_flat_hood[11] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[11] sw4_flat_hood_blip[11]
		ENDIF
	RETURN
// ---- Group 4
	sw4_flat_hood_create4:

	   	sw4_flat_hood_x[12] = 1983.80  
		sw4_flat_hood_y[12] = -1235.98 
		sw4_flat_hood_z[12] = 20.17  
		sw4_flat_hood_h[12] = 318.72 
		CLEAR_AREA sw4_flat_hood_x[12] sw4_flat_hood_y[12] sw4_flat_hood_z[12] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[12] sw4_flat_hood_y[12] sw4_flat_hood_z[12] sw4_flat_hood[12]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[12] WEAPONTYPE_TEC9 sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[12] sw4_flat_hood_h[12]
		SET_CHAR_ACCURACY sw4_flat_hood[12] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[12] sw4_flat_hood_blip[12]
		ENDIF
	   						   
		sw4_flat_hood_x[13] = 1981.30  
		sw4_flat_hood_y[13] = -1233.94 
		sw4_flat_hood_z[13] = 20.04  
		sw4_flat_hood_h[13] = 254.99
		CLEAR_AREA sw4_flat_hood_x[13] sw4_flat_hood_y[13] sw4_flat_hood_z[13] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[13] sw4_flat_hood_y[13] sw4_flat_hood_z[13] sw4_flat_hood[13]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[13] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[13] sw4_flat_hood_h[13]
		SET_CHAR_ACCURACY sw4_flat_hood[13] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[13] sw4_flat_hood_blip[13]
		ENDIF
									  
		sw4_flat_hood_x[14] = 1986.67   
		sw4_flat_hood_y[14] = -1239.76  
		sw4_flat_hood_z[14] = 19.17  
		sw4_flat_hood_h[14] = 98.28
		CLEAR_AREA sw4_flat_hood_x[14] sw4_flat_hood_y[14] sw4_flat_hood_z[14] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas1 sw4_flat_hood_x[14] sw4_flat_hood_y[14] sw4_flat_hood_z[14] sw4_flat_hood[14]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[14] WEAPONTYPE_SPRAYCAN sw4_ammo
		TASK_STAY_IN_SAME_PLACE sw4_flat_hood[14] TRUE
		SET_CHAR_HEADING sw4_flat_hood[14] sw4_flat_hood_h[14]
		SET_CHAR_ACCURACY sw4_flat_hood[14] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[14] sw4_flat_hood_blip[14]
		ENDIF

		sw4_flat_hood_x[15] = 1986.54  
		sw4_flat_hood_y[15] = -1233.03 
		sw4_flat_hood_z[15] = 20.07  
		sw4_flat_hood_h[15] = 143.42 
		CLEAR_AREA sw4_flat_hood_x[15] sw4_flat_hood_y[15] sw4_flat_hood_z[15] 0.5 TRUE
		CREATE_CHAR PEDTYPE_GANG_FLAT ballas2 sw4_flat_hood_x[15] sw4_flat_hood_y[15] sw4_flat_hood_z[15] sw4_flat_hood[15]
		GIVE_WEAPON_TO_CHAR sw4_flat_hood[15] WEAPONTYPE_PISTOL sw4_ammo
		SET_CHAR_HEADING sw4_flat_hood[15] sw4_flat_hood_h[15]
		SET_CHAR_ACCURACY sw4_flat_hood[15] sw4_hood_acc
		IF sw4_blipped = 0
			ADD_BLIP_FOR_CHAR  sw4_flat_hood[15] sw4_flat_hood_blip[15]
		ENDIF
	RETURN
	
	sw4_flat_hood_delete:
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[0]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[1]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[2]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[3]

		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[4]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[5]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[6]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[7]

		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[8]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[9]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[10] 
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[11]

		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[12]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[13]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[14]
		MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[15]
	RETURN

// ------------------------------------------------------------------------------------------------
// -------- Vehicles
// ---- Player Car
	sw4_player_car_create:
		LVAR_INT sw4_player_car sw4_player_car_blip	
		LVAR_FLOAT sw4_player_car_x sw4_player_car_y sw4_player_car_z sw4_player_car_h
		
		sw4_player_car_x =	2508.16 
		sw4_player_car_y = -1666.47 
		sw4_player_car_z = 13.0
		sw4_player_car_h = 16.0
		SWITCH_CAR_GENERATOR gen_car7 0
		CUSTOM_PLATE_FOR_NEXT_CAR GREENWOO GROVE4L_
		CLEAR_AREA sw4_player_car_x sw4_player_car_y sw4_player_car_z 6.0 TRUE
		CREATE_CAR GREENWOO sw4_player_car_x sw4_player_car_y sw4_player_car_z sw4_player_car
		CHANGE_CAR_COLOUR sw4_player_car 59 34
		SET_CAR_HEADING sw4_player_car sw4_player_car_h
		ADD_BLIP_FOR_CAR sw4_player_car sw4_player_car_blip
		SET_BLIP_AS_FRIENDLY sw4_player_car_blip TRUE
		//SET_CAR_PROOFS sw4_player_car TRUE TRUE TRUE TRUE TRUE
		SET_CAN_BURST_CAR_TYRES sw4_player_car FALSE
		SET_CAR_HEALTH sw4_player_car 1450
		ALTER_WANTED_LEVEL player1 0
   	RETURN
	sw4_player_car_delete:
		IF NOT IS_CAR_DEAD sw4_player_car
			IF NOT IS_CAR_ON_SCREEN sw4_player_car
				DELETE_CAR sw4_player_car
			ENDIF
		ENDIF
		MARK_MODEL_AS_NO_LONGER_NEEDED GREENWOO
	RETURN
	
// --------	Set Groups
	sw4_hood_group1:
//		IF NOT IS_CHAR_DEAD sw4_flat_hood[0]
//		AND NOT IS_CHAR_DEAD sw4_flat_hood[1]
//		AND NOT IS_CHAR_DEAD sw4_flat_hood[2]
//		AND NOT IS_CHAR_DEAD sw4_flat_hood[3]
//			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR sw4_hood_grp[0]
//			SET_GROUP_LEADER sw4_hood_grp[0] sw4_flat_hood[0]
//			SET_GROUP_MEMBER sw4_hood_grp[0] sw4_flat_hood[1]
//			SET_GROUP_MEMBER sw4_hood_grp[0] sw4_flat_hood[2]
//			SET_GROUP_MEMBER sw4_hood_grp[0] sw4_flat_hood[3]
//		ENDIF
	RETURN
	sw4_hood_group2:
//		IF NOT IS_CHAR_DEAD sw4_flat_hood[4]
//		AND NOT IS_CHAR_DEAD sw4_flat_hood[5]
//		AND NOT IS_CHAR_DEAD sw4_flat_hood[6]
//		AND NOT IS_CHAR_DEAD sw4_flat_hood[7]
//			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_SIT_IN_LEADER_CAR sw4_hood_grp[1]
//			SET_GROUP_LEADER sw4_hood_grp[1] sw4_flat_hood[4]
//			SET_GROUP_MEMBER sw4_hood_grp[1] sw4_flat_hood[5]
//			SET_GROUP_MEMBER sw4_hood_grp[1] sw4_flat_hood[6]
//			SET_GROUP_MEMBER sw4_hood_grp[1] sw4_flat_hood[7]
//		ENDIF
	RETURN
   	sw4_hood_group3:
		IF NOT IS_CHAR_DEAD sw4_flat_hood[8]
		AND NOT IS_CHAR_DEAD sw4_flat_hood[9]
		AND NOT IS_CHAR_DEAD sw4_flat_hood[10]
		AND NOT IS_CHAR_DEAD sw4_flat_hood[11]
			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS sw4_hood_grp[2]
			SET_GROUP_LEADER sw4_hood_grp[2] sw4_flat_hood[8]
			SET_GROUP_MEMBER sw4_hood_grp[2] sw4_flat_hood[9]
			SET_GROUP_MEMBER sw4_hood_grp[2] sw4_flat_hood[10]
			SET_GROUP_MEMBER sw4_hood_grp[2] sw4_flat_hood[11]
		ENDIF
	RETURN
	sw4_hood_group4:
		IF NOT IS_CHAR_DEAD sw4_flat_hood[12]
		AND NOT IS_CHAR_DEAD sw4_flat_hood[13]
		AND NOT IS_CHAR_DEAD sw4_flat_hood[14]
		AND NOT IS_CHAR_DEAD sw4_flat_hood[15]
			CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS sw4_hood_grp[3]
			SET_GROUP_LEADER sw4_hood_grp[3] sw4_flat_hood[12]
			SET_GROUP_MEMBER sw4_hood_grp[3] sw4_flat_hood[13]
			SET_GROUP_MEMBER sw4_hood_grp[3] sw4_flat_hood[14]
			SET_GROUP_MEMBER sw4_hood_grp[3] sw4_flat_hood[15]
		ENDIF
   RETURN

// ---- Sequences

sw4_hood_react_group1:
	IF NOT IS_CAR_DEAD sw4_hood_car[0]
	AND NOT IS_CAR_DEAD sw4_player_car
		OPEN_SEQUENCE_TASK sw4_hood_react[0] // First car, driver gets out and shoots at the player's car...
			TASK_LEAVE_CAR -1 sw4_hood_car[0]
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[0]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD sw4_player_car
		OPEN_SEQUENCE_TASK sw4_hood_react[1] // First car, goon walks around car while shooting at the player, then shoots some more...
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2075.15	-1363.85 23.85 PEDMOVE_RUN 0.5 5.0 scplayer
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[1]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[2] // First car, goon heads for the player's car while shooting...
			TASK_DIVE_AND_GET_UP -1 1.0 1.0 500
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[2]
	ENDIF
		
	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_anim // First car, goon heads for the player's car while shooting...
			TASK_PLAY_ANIM -1 prtial_gngtlkG GANGS 4.0 FALSE FALSE FALSE FALSE 0
			TASK_PLAY_ANIM -1 prtial_gngtlkH GANGS 4.0 FALSE FALSE FALSE FALSE 0	
		CLOSE_SEQUENCE_TASK sw4_hood_anim
	ENDIF	

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[3] // First car, goon makes a break for it...
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[3]		
	ENDIF
RETURN

sw4_hood_react_group2:
	IF NOT IS_CAR_DEAD sw4_hood_car[1]
	AND NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[4] // Second car, goon gets out of car heads for player while shooting...
			TASK_LEAVE_CAR -1 sw4_hood_car[1]
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 sw4_player_car_x sw4_player_car_y sw4_player_car_z PEDMOVE_WALK 1.0 5.0 scplayer
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[4]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[5] // Second car, goon makes a break for it...
			TASK_WEAPON_ROLL -1	TRUE
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[5]		
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[6] // Second car, goon moves to corner while shooting, then flees...
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2080.66 -1292.61 24.02 PEDMOVE_RUN 0.5 5.0 scplayer
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[6]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[7] // Second car, goon sidesteps behind car while shooting...
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2132.0 -1307.0 24.0 PEDMOVE_RUN 1.0 5.0 scplayer
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK	sw4_hood_react[7]
	ENDIF	
RETURN

sw4_hood_react_group3:
	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[8] // Second car, goon gets out of car heads for player while shooting...
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2163.0 -1262.0 24.0 PEDMOVE_RUN 1.0 5.0 scplayer
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2080.0 -1261.0 24.0 PEDMOVE_RUN 1.0 5.0 scplayer
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[8]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[9] // Second car, goon makes a break for it...
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[9]		
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD sw4_player_car
		OPEN_SEQUENCE_TASK sw4_hood_react[10] // Second car, goon moves to corner while shooting, then flees...
			TASK_STAY_IN_SAME_PLACE -1 TRUE
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[10]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[11] // Second car, goon sidesteps behind car while shooting...
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2163.0 -1262.0 24.0 PEDMOVE_RUN 1.0 5.0 scplayer
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 2080.0 -1261.0 24.0 PEDMOVE_RUN 1.0 5.0 scplayer
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK	sw4_hood_react[11]
	ENDIF	
RETURN

sw4_hood_react_group4:
	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[12] // Second car, goon gets out of car heads for player while shooting...
			TASK_GO_TO_COORD_WHILE_SHOOTING -1 sw4_player_car_x sw4_player_car_y sw4_player_car_z PEDMOVE_WALK 1.0 5.0 scplayer
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[12]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CAR_DEAD sw4_player_car
		OPEN_SEQUENCE_TASK sw4_hood_react[13] // Second car, goon makes a break for it...
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK sw4_hood_react[13]		
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[14] // Second car, goon moves to corner while shooting, then flees...
			TASK_SHOOT_AT_COORD -1 1984.21 -1239.04 20.50 6000
			TASK_FLEE_CHAR -1 scplayer 50.0 -1
		CLOSE_SEQUENCE_TASK sw4_hood_react[14]
	ENDIF

	IF NOT IS_CHAR_DEAD scplayer
		OPEN_SEQUENCE_TASK sw4_hood_react[15] // Second car, goon sidesteps behind car while shooting...
			TASK_DESTROY_CAR -1 sw4_player_car
		CLOSE_SEQUENCE_TASK	sw4_hood_react[15]
	ENDIF	
RETURN


// ------------------------------------------------------------------------------------------------
// Start Mission
mission_start_sweet4:
	REGISTER_MISSION_GIVEN
	flag_player_on_mission = 1
	WAIT 0
// ------------------------------------------------------------------------------------------------
// Initialize Variables
// ---- Flags
	sw4_stage = 0
	sw4_help = 0
	sw4_hood_acc = 30
	sw4_ammo = 30000
	sw4_blipped = 0
	sw4_cut = 0
	sw4_wanted_flag = 0
	sw4_skip_flag = 0
	sw4_sequence_flag = 0 
	sw4_cut_text = 0
	sw4_on_fire = 0
	sw4_smoke_status = 0
	sw4_ryder_status = 0
	sw4_sweet_status = 0

	sw4_attack[0] = 0
	sw4_attack[1] = 0
	sw4_attack[2] = 0
	sw4_attack[3] = 0

	sw4_hood_create[0] = 0
	sw4_hood_create[1] = 0
	sw4_hood_create[2] = 0
	sw4_hood_create[3] = 0

	sw4_dead_count = 0

	sw4_hood_dead[0] = 0	
	sw4_hood_dead[1] = 0	
	sw4_hood_dead[2] = 0	
	sw4_hood_dead[3] = 0	
	sw4_hood_dead[4] = 0	
	sw4_hood_dead[5] = 0	
	sw4_hood_dead[6] = 0	
	sw4_hood_dead[7] = 0	
	sw4_hood_dead[8] = 0	
	sw4_hood_dead[9] = 0	
	sw4_hood_dead[10] = 0	
	sw4_hood_dead[11] = 0	
	sw4_hood_dead[12] = 0	
	sw4_hood_dead[13] = 0	
	sw4_hood_dead[14] = 0	
   	sw4_hood_dead[15] = 0	

	sw4_group_dead[0] = 0
	sw4_group_dead[1] = 0
	sw4_group_dead[2] = 0
	sw4_group_dead[3] = 0
	
	sw4_group_active = 0

	sw4_create = 0

	sw4_count = 0
	
// ---- Sequences
	sw4_move = 0 

	sw4_hood_react[0] = 1
	sw4_hood_react[1] = 2
	sw4_hood_react[2] = 3
	sw4_hood_react[3] = 4

//	sw4_hood_react[4] = 1
//	sw4_hood_react[5] = 2
//	sw4_hood_react[6] = 3
//	sw4_hood_react[7] = 4
//
//	sw4_hood_react[8] = 1
//	sw4_hood_react[9] = 2
//	sw4_hood_react[10] = 3
//	sw4_hood_react[11] = 4
//
//	sw4_hood_react[12] = 1
//	sw4_hood_react[13] = 2
//	sw4_hood_react[14] = 3
//	sw4_hood_react[15] = 4

	sw4_ryder_end_cut = 5
	sw4_player_shake = 6
	sw4_drive_away = 7
	sw4_smoke_enters_car = 8
	sw4_hood_anim = 9

// ---- Counter Var
	sw4_dead_count = 0
	
   
// ---- Coords
	sw4_group_loc = 25.0

// ---- Hoods
	sw4_flat_hood_x[0] = 2227.72 
	sw4_flat_hood_y[0] = -1315.79
	sw4_flat_hood_z[0] = 24.04 

	sw4_flat_hood_x[5] = 2099.80 
	sw4_flat_hood_y[5] = -1288.37
	sw4_flat_hood_z[5] = 24.35 

	sw4_flat_hood_x[10] = 2184.97  
	sw4_flat_hood_y[10] = -1230.92 
	sw4_flat_hood_z[10] = 24.02
	
	sw4_flat_hood_x[15] = 2157.77  
	sw4_flat_hood_y[15] = -1345.03 
	sw4_flat_hood_z[15] = 24.04  
	  
	sw4_end_x =	2507.0	
	sw4_end_y = -1671.0
	sw4_end_z = 12.0
	   
// ---- Camera Coords
	sw4_cam_x[0] = 2500.026  
	sw4_cam_y[0] = -1661.180  
	sw4_cam_z[0] = 17.763

	sw4_cam_x[1] = 2194.35 
	sw4_cam_y[1] = -1380.42 
	sw4_cam_z[1] = 24.34

	sw4_cam_x[2] = 2082.42 
	sw4_cam_y[2] = -1362.58 
	sw4_cam_z[2] = 26.81

	sw4_cam_x[3] = 2078.99 
	sw4_cam_y[3] = -1366.22 
	sw4_cam_z[3] = 25.09

	sw4_cam_x[4] = 2152.0 
	sw4_cam_y[4] = -1339.0 
	sw4_cam_z[4] = 30.0

	sw4_cam_x[5] = 2502.16 
	sw4_cam_y[5] = -1661.95 
	sw4_cam_z[5] = 15.76

	sw4_cam_x[6] = 2509.18
	sw4_cam_y[6] = -1662.81
	sw4_cam_z[6] = 14.07

	sw4_cam_x[7] = 2510.63
	sw4_cam_y[7] = -1665.81
	sw4_cam_z[7] = 13.83

	sw4_cam_x[8] = 2511.89
	sw4_cam_y[8] = -1668.39
	sw4_cam_z[8] = 14.26

	sw4_cam_x[9] = 0.0
	sw4_cam_y[9] = 0.0
	sw4_cam_z[9] = 0.0

// ---- Flat Hood Blip
	sw4_hood_x = 2132.2388 
	sw4_hood_y = -1379.6517
	sw4_hood_z = 22.8281
				 
	sw4_flat_hood_x[4] = 2101.57
	sw4_flat_hood_y[4] = -1287.65 
	sw4_flat_hood_z[4] = 24.17
	sw4_flat_hood_h[4] = 5.0

	sw4_flat_hood_x[8] = 2161.07  
	sw4_flat_hood_y[8] = -1265.92 
	sw4_flat_hood_z[8] = 23.02  
	sw4_flat_hood_h[8] = 257.59

	sw4_flat_hood_x[12] = 1983.80  
	sw4_flat_hood_y[12] = -1235.98 
	sw4_flat_hood_z[12] = 20.17  
	sw4_flat_hood_h[12] = 318.72

// ---- Dialogue Flags
	sw4_audio_slot = 1
	sw4_alt_slot = 2
	sw4_counter = 0
	sw4_ahead_counter = 1
	sw4_audio_playing = 0

// ---- Dialogue Text
	$sw4_text[1] = SWE4_AA // Where we going, homie?
	$sw4_text[2] = SWE4_AB // Rollin' Heights Ballas country.
	$sw4_text[3] = SWE4_AC // Front Yard Ballas country.
	$sw4_text[4] = SWE4_AD // Kilo Trays country. 
	$sw4_text[5] = SWE4_AE // Do us a little drive by?
	$sw4_text[6] = SWE4_AF // For real. You down, Carl?
	$sw4_text[7] = SWE4_AG // I ain't packing any heat.
	$sw4_text[8] = SWE4_AH // You ain't cold enough for that shit yet, CJ.
	$sw4_text[9] = SWE4_AI // No, you're our chauffeur for this little gig!
	$sw4_text[10] = SWE4_AJ // Gee, thanks.
	$sw4_text[11] = SWE4_AK // Just don't drive like a fool.

	$sw4_text[12] = SWE4_BA // Alright, Ballas turf, you dogs ready?
	$sw4_text[13] = SWE4_BB // Sure, dude, I'm ready.
	$sw4_text[14] = SWE4_BC // Carl, Just concentrate on the driving and we'll take care of the shooting.
	$sw4_text[15] = SWE4_BD // Listen to the man. Try not to park us up a tree or nothin'.
	$sw4_text[16] = SWE4_BE // Yeah if the car stops, we're dead meat.

	$sw4_text[17] = SWE4_CA // What did I say? CJ drives like a blind motherfucker!
	$sw4_text[18] = SWE4_CB // Never buy a new car, CJ, never!
	$sw4_text[19] = SWE4_CC // He's crashing on purpose, I swear it!

	$sw4_text[20] = SWE4_DA // Holy shit we're sitting ducks!
	$sw4_text[21] = SWE4_DB // Oh man, oh man, move! MOVE!
	$sw4_text[22] = SWE4_DC // What you doing, CJ?!
	$sw4_text[23] = SWE4_DD // Get us moving!
	$sw4_text[24] = SWE4_DE // He's trying to get us killed!
	$sw4_text[25] = SWE4_DF // Move it, CJ, move it!

	$sw4_text[26] = SWE4_EA // I told you he was a buster!
	$sw4_text[27] = SWE4_EB // Don't run out on me again, Carl!
	$sw4_text[28] = SWE4_EC // Carl's quitting on us!

	$sw4_text[29] = SWE4_FA // He's ditched us!
	$sw4_text[30] = SWE4_FB // Damn you, Carl! I'm on it!
	$sw4_text[31] = SWE4_FC // I'll get us out of here!
	$sw4_text[32] = SWE4_FD // Motherfucker! I've got the wheel! 

	$sw4_text[33] = SWE4_GA // Go Carl, GO!!
	$sw4_text[34] = SWE4_GB // These wheels are hot, every cop in South Central will be looking for this car!

	$sw4_text[35] = SWE4_HA // Alright! Let's get back to the Grove.
	$sw4_text[36] = SWE4_HB // Yeah, what you waiting for, Carl?

	$sw4_text[37] = SWE4_KA // Holy fuck, Grove is back, man, Grove is back!
	$sw4_text[38] = SWE4_KB // Righteous, dude, they was totally unprepared for us!
	$sw4_text[39] = SWE4_KC // I'm amazed you didn't get us killed, CJ.
	$sw4_text[40] = SWE4_KD // Yo, check it, am I dead?
	$sw4_text[41] = SWE4_KF // Hey, Carl, ignore that motherfucker, you did good today.
	$sw4_text[42] = SWE4_KG // You're down with the Grove and those Ballas know it,
	$sw4_text[43] = SWE4_KH // so watch yourself from now on.
	$sw4_text[44] = SWE4_KE // I'm cool, dude. Look, I'll see you cats later.
	$sw4_text[45] = SWE4_KI // Here, go get yourself a beer and some colours.

	$sw4_text[46] = SWE4_LA // We iced them! CJ, let's hunt us down some more!
	$sw4_text[47] = SWE4_LB // They was too easy, let's find us some more Ballas fools!
	$sw4_text[48] = SWE4_LC // What you waiting for, CJ? Find us some more Ballas fools to cap!

	$sw4_text[49] = SWE4_MA // Some of those fools are still standing, we gotta make another pass!
	$sw4_text[50] = SWE4_MB // We didn't get all those motherfuckers!
	$sw4_text[51] = SWE4_MC // CJ, let's finish them!
	$sw4_text[52] = SWE4_MD // Where you going, CJ, there's still some Ballas needing iced!

	$sw4_text[53] = SWE4_NA // CJ, you're a liability!
	$sw4_text[54] = SWE4_NB // The cops are onto us again!
	$sw4_text[55] = SWE4_NC // CJ, you trying to get us busted?

	$sw4_text[56] = SWE4_OA // C'mon CJ, we gotta lose all this attention!
	$sw4_text[57] = SWE4_OB // CJ, get us to a spray shop!
	$sw4_text[58] = SWE4_OC // Not again! C'mon, CJ, get us someplace safe!

	$sw4_text[59] = SWE4_PA // Now, what was we saying?
	$sw4_text[60] = SWE4_PB // I forgot what we was rapping on about!
	$sw4_text[61] = SWE4_PC // Ok, we're cool. Now what was I sayin'?

// ---- Dialogue Audio
	$sw4_audio[1] = SOUND_SWE4_AA // Where we going, homie?
	$sw4_audio[2] = SOUND_SWE4_AB // Rollin' Heights Ballas country.
	$sw4_audio[3] = SOUND_SWE4_AC // Front Yard Ballas country.
	$sw4_audio[4] = SOUND_SWE4_AD // Kilo Trays country. 
	$sw4_audio[5] = SOUND_SWE4_AE // Do us a little drive by?
	$sw4_audio[6] = SOUND_SWE4_AF // For real. You down, Carl?
	$sw4_audio[7] = SOUND_SWE4_AG // I ain't packing any heat.
	$sw4_audio[8] = SOUND_SWE4_AH // You ain't cold enough for that shit yet, CJ.
	$sw4_audio[9] = SOUND_SWE4_AI // No, you're our chauffeur for this little gig!
	$sw4_audio[10] = SOUND_SWE4_AJ // Gee, thanks.
	$sw4_audio[11] = SOUND_SWE4_AK // Just don't drive like a fool.

	$sw4_audio[12] = SOUND_SWE4_BA // Alright, Ballas turf, you dogs ready?
	$sw4_audio[13] = SOUND_SWE4_BB // Sure, dude, I'm ready.
	$sw4_audio[14] = SOUND_SWE4_BC // Carl, Just concentrate on the driving and we'll take care of the shooting.
	$sw4_audio[15] = SOUND_SWE4_BD // Listen to the man. Try not to park us up a tree or nothin'.
	$sw4_audio[16] = SOUND_SWE4_BE // Yeah if the car stops, we're dead meat.

	$sw4_audio[17] = SOUND_SWE4_CA // What did I say? CJ drives like a blind motherfucker!
	$sw4_audio[18] = SOUND_SWE4_CB // Never buy a new car, CJ, never!
	$sw4_audio[19] = SOUND_SWE4_CC // He's crashing on purpose, I swear it!

	$sw4_audio[20] = SOUND_SWE4_DA // Holy shit we're sitting ducks!
	$sw4_audio[21] = SOUND_SWE4_DB // Oh man, oh man, move! MOVE!
	$sw4_audio[22] = SOUND_SWE4_DC // What you doing, CJ?!
	$sw4_audio[23] = SOUND_SWE4_DD // Get us moving!
	$sw4_audio[24] = SOUND_SWE4_DE // He's trying to get us killed!
	$sw4_audio[25] = SOUND_SWE4_DF // Move it, CJ, move it!

	$sw4_audio[26] = SOUND_SWE4_EA // I told you he was a buster!
	$sw4_audio[27] = SOUND_SWE4_EB // Don't run out on me again, Carl!
	$sw4_audio[28] = SOUND_SWE4_EC // Carl's quitting on us!

	$sw4_audio[29] = SOUND_SWE4_FA // He's ditched us!
	$sw4_audio[30] = SOUND_SWE4_FB // Damn you, Carl! I'm on it!
	$sw4_audio[31] = SOUND_SWE4_FC // I'll get us out of here!
	$sw4_audio[32] = SOUND_SWE4_FD // Motherfucker! I've got the wheel! 

	$sw4_audio[33] = SOUND_SWE4_GA // Go Carl, GO!!
	$sw4_audio[34] = SOUND_SWE4_GB // These wheels are hot, every cop in South Central will be looking for this car!

	$sw4_audio[35] = SOUND_SWE4_HA // Alright! Let's get back to the Grove.
	$sw4_audio[36] = SOUND_SWE4_HB // Yeah, what you waiting for, Carl?

	$sw4_audio[37] = SOUND_SWE4_KA // Holy fuck, Grove is back, man, Grove is back!
	$sw4_audio[38] = SOUND_SWE4_KB // Righteous, dude, they was totally unprepared for us!
	$sw4_audio[39] = SOUND_SWE4_KC // I'm amazed you didn't get us killed, CJ.
	$sw4_audio[40] = SOUND_SWE4_KD // Yo, check it, am I dead?
	$sw4_audio[41] = SOUND_SWE4_KF // Hey, Carl, ignore that motherfucker, you did good today.
	$sw4_audio[42] = SOUND_SWE4_KG // You're down with the Grove and those Ballas know it,
	$sw4_audio[43] = SOUND_SWE4_KH // so watch yourself from now on.
	$sw4_audio[44] = SOUND_SWE4_KE // I'm cool, dude. Look, I'll see you cats later.
	$sw4_audio[45] = SOUND_SWE4_KI // Here, go get yourself a beer and some colours.

	$sw4_audio[46] = SOUND_SWE4_LA // We iced them! CJ, let's hunt us down some more!
	$sw4_audio[47] = SOUND_SWE4_LB // They was too easy, let's find us some more Ballas fools!
	$sw4_audio[48] = SOUND_SWE4_LC // What you waiting for, CJ? Find us some more Ballas fools to cap!

	$sw4_audio[49] = SOUND_SWE4_MA // Some of those fools are still standing, we gotta make another pass!
	$sw4_audio[50] = SOUND_SWE4_MB // We didn't get all those motherfuckers!
	$sw4_audio[51] = SOUND_SWE4_MC // CJ, let's finish them!
	$sw4_audio[52] = SOUND_SWE4_MD // Where you going, CJ, there's still some Ballas needing iced!

	$sw4_audio[53] = SOUND_SWE4_NA // CJ, you're a liability!
	$sw4_audio[54] = SOUND_SWE4_NB // The cops are onto us again!
	$sw4_audio[55] = SOUND_SWE4_NC // CJ, you trying to get us busted?

	$sw4_audio[56] = SOUND_SWE4_OA // C'mon CJ, we gotta lose all this attention!
	$sw4_audio[57] = SOUND_SWE4_OB // CJ, get us to a spray shop!
	$sw4_audio[58] = SOUND_SWE4_OC // Not again! C'mon, CJ, get us someplace safe!

	$sw4_audio[59] = SOUND_SWE4_PA // Now, what was we saying?
	$sw4_audio[60] = SOUND_SWE4_PB // I forgot what we was rapping on about!
	$sw4_audio[61] = SOUND_SWE4_PC // Ok, we're cool. Now what was I sayin'?

// ------------------------------------------------------------------------------------------------
// Request Models

// ------------------------------------------------------------------------------------------------
// Load Text
CLEAR_THIS_PRINT M_FAIL
LOAD_MISSION_TEXT SWEET4
//CLEAR_PRINTS

// Cutscene
CLEAR_AREA 2518.0 -1673.0 14.0 50.0 FALSE


//SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE 2520.0 -1679.0 15.0 10.0 GEN_DOOREXT03 FALSE
//SET_PED_DENSITY_MULTIPLIER 0.0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
            WAIT 0
ENDWHILE

LOAD_CUTSCENE SWEET4A

DO_FADE 0 FADE_OUT
 
WHILE NOT HAS_CUTSCENE_LOADED
            WAIT 0
ENDWHILE

DO_FADE 0 FADE_OUT
 
START_CUTSCENE

DO_FADE 0 FADE_OUT

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
            WAIT 0
ENDWHILE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE



//SET_VISIBILITY_OF_CLOSEST_OBJECT_OF_TYPE 2520.0 -1679.0 15.0 10.0 GEN_DOOREXT03 TRUE

SET_MUSIC_DOES_FADE FALSE
SET_PLAYER_CONTROL player1 ON
SWITCH_WIDESCREEN OFF 
SET_RADIO_CHANNEL RS_NEW_JACK_SWING

// ------------------------------------------------------------------------------------------------
// Entity GoSubs

// Set gang strength

SET_CREATE_RANDOM_GANG_MEMBERS FALSE

//GET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT sw4_gang_strength[0]
//SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT 0 
//
//GET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT sw4_gang_strength[1]
//SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT sw4_gang_strength[2]
//SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT sw4_gang_strength[3]
//SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT sw4_gang_strength[4]
//SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT sw4_gang_strength[5]
//SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH JEF1a GANG_FLAT sw4_gang_strength[6]
//SET_ZONE_GANG_STRENGTH JEF1a GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH JEF1b GANG_FLAT sw4_gang_strength[7]
//SET_ZONE_GANG_STRENGTH JEF1b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH JEF2  GANG_FLAT sw4_gang_strength[8]
//SET_ZONE_GANG_STRENGTH JEF2  GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH JEF3b GANG_FLAT sw4_gang_strength[9]
//SET_ZONE_GANG_STRENGTH JEF3b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH JEF3c GANG_FLAT sw4_gang_strength[10]
//SET_ZONE_GANG_STRENGTH JEF3c GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT sw4_gang_strength[11]
//SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT sw4_gang_strength[12]
//SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT sw4_gang_strength[13]
//SET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT sw4_gang_strength[14]
//SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT sw4_gang_strength[15]
//SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT sw4_gang_strength[16]
//SET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT sw4_gang_strength[17]
//SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH GLN2a GANG_FLAT sw4_gang_strength[18]
//SET_ZONE_GANG_STRENGTH GLN2a GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH GLN2b GANG_FLAT sw4_gang_strength[19]
//SET_ZONE_GANG_STRENGTH GLN2b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH LIND1a GANG_FLAT sw4_gang_strength[20]
//SET_ZONE_GANG_STRENGTH LIND1a GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH LIND1b GANG_FLAT sw4_gang_strength[21]
//SET_ZONE_GANG_STRENGTH LIND1b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH LIND2a GANG_FLAT sw4_gang_strength[22]
//SET_ZONE_GANG_STRENGTH LIND2a GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH LIND2b GANG_FLAT sw4_gang_strength[23]
//SET_ZONE_GANG_STRENGTH LIND2b GANG_FLAT 0
//
//GET_ZONE_GANG_STRENGTH LIND3 GANG_FLAT sw4_gang_strength[24]
//SET_ZONE_GANG_STRENGTH LIND3 GANG_FLAT 0






GOSUB sw4_load_all
GOSUB sw4_smoke_create
GOSUB sw4_ryder_create
GOSUB sw4_sweet_create

GOSUB sw4_player_car_create

// ---- Set Groups
IF NOT IS_CHAR_DEAD sw4_smoke
AND NOT IS_CHAR_DEAD sw4_ryder
AND NOT IS_CHAR_DEAD sw4_sweet
AND NOT IS_CAR_DEAD sw4_player_car
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	OPEN_SEQUENCE_TASK sw4_smoke_enters_car
		TASK_STAND_STILL -1 1000
		TASK_ENTER_CAR_AS_PASSENGER -1 sw4_player_car -1 0
	CLOSE_SEQUENCE_TASK sw4_smoke_enters_car
	PERFORM_SEQUENCE_TASK sw4_smoke sw4_smoke_enters_car
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER sw4_ryder sw4_player_car -1 1
	SET_NEXT_DESIRED_MOVE_STATE PEDMOVE_WALK
	TASK_ENTER_CAR_AS_PASSENGER sw4_sweet sw4_player_car -1 2
	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY sw4_decision
	SET_CHAR_DECISION_MAKER sw4_smoke sw4_decision
	SET_CHAR_DECISION_MAKER sw4_ryder sw4_decision
	SET_CHAR_DECISION_MAKER sw4_sweet sw4_decision
	SET_CHAR_CANT_BE_DRAGGED_OUT scplayer TRUE
	SET_CHAR_CANT_BE_DRAGGED_OUT sw4_smoke TRUE
	SET_CHAR_CANT_BE_DRAGGED_OUT sw4_ryder TRUE
	SET_CHAR_CANT_BE_DRAGGED_OUT sw4_sweet TRUE
	GET_GAME_TIMER sw4_text_timer_start 
	sw4_text_timer_flag = -1
ENDIF

GET_MAX_WANTED_LEVEL sw4_max_wanted
SET_MAX_WANTED_LEVEL 0
SET_CREATE_RANDOM_COPS FALSE
SWITCH_COPS_ON_BIKES OFF


//VIEW_INTEGER_VARIABLE sw4_stage sw4_stage
//VIEW_INTEGER_VARIABLE sw4_text_timer_flag sw4_text_timer_flag
//VIEW_INTEGER_VARIABLE sw4_blipped sw4_blipped


// ------------------------------------------------------------------------------------------------
// Task Sequences

// ------------------------------------------------------------------------------------------------
// Start Blip
SET_CAMERA_BEHIND_PLAYER

SET_WANTED_MULTIPLIER 0.7
SET_PLAYER_GROUP_RECRUITMENT player1 FALSE
IF NOT IS_CHAR_DEAD scplayer
	SET_CHAR_COORDINATES scplayer 2516.92 -1679.45 13.42
	SET_CHAR_HEADING scplayer 27.25
ENDIF
GOSUB sw4_flat_hood_create

PRINT_NOW ( SWE4_00 ) 5000 1 // Get in the car

GET_GAME_TIMER sw4_timer_start[2]
					sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]

// ------------------------------------------------------------------------------------------------

SET_FADING_COLOUR 0 0 0
DO_FADE 500 FADE_IN
// Main Loop
sw4_main_loop:

WAIT 0

// ---- Load & Play Dialogue...
IF NOT sw4_counter = 0
	IF sw4_audio_playing = 0
		IF HAS_MISSION_AUDIO_LOADED sw4_alt_slot
			CLEAR_MISSION_AUDIO sw4_alt_slot
		ENDIF
		sw4_audio_playing = 1
	ENDIF

	IF sw4_audio_playing = 1
		LOAD_MISSION_AUDIO sw4_audio_slot sw4_audio[sw4_counter]
		GOSUB sw4_dialogue_pos
//		ATTACH_MISSION_AUDIO_TO_PED sw4_audio_slot sw4_audio_char
		sw4_audio_playing = 2
	ENDIF

	IF sw4_audio_playing = 2
	 	IF HAS_MISSION_AUDIO_LOADED sw4_audio_slot
			IF NOT sw4_audio_char = 0
				IF NOT IS_CHAR_DEAD	sw4_audio_char
					START_CHAR_FACIAL_TALK sw4_audio_char 10000
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sw4_audio_char TRUE
					//ATTACH_MISSION_AUDIO_TO_CHAR c3_audio_slot c3_audio_char
				ENDIF
			ENDIF
			PLAY_MISSION_AUDIO sw4_audio_slot
			PRINT_NOW $sw4_text[sw4_counter] 10000 1
			sw4_audio_playing = 3
		ENDIF
	ENDIF

	IF sw4_audio_playing = 3
		IF HAS_MISSION_AUDIO_FINISHED sw4_audio_slot
			CLEAR_THIS_PRINT $sw4_text[sw4_counter]
			IF NOT sw4_audio_char = 0
				IF NOT IS_CHAR_DEAD	sw4_audio_char
					STOP_CHAR_FACIAL_TALK sw4_audio_char
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH sw4_audio_char FALSE
				ENDIF
			ENDIF
			IF sw4_audio_slot = 1
				sw4_audio_slot = 2
				sw4_alt_slot = 1
			ELSE
				sw4_audio_slot = 1
				sw4_alt_slot = 2
			ENDIF
			sw4_counter = 0
			sw4_audio_playing = 0
			IF sw4_text_timer_flag = 2
				sw4_text_timer_flag = 6
			ELSE
				sw4_text_timer_flag++
			ENDIF
		ELSE
			IF NOT HAS_MISSION_AUDIO_LOADED sw4_alt_slot
				IF sw4_counter < 60
					sw4_ahead_counter = sw4_counter + 1
					LOAD_MISSION_AUDIO sw4_alt_slot sw4_audio[sw4_ahead_counter]
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF  

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_passed_sweet4
ENDIF


// If anyone dies...
IF sw4_stage < 8
OR sw4_stage = 666
	IF IS_CHAR_DEAD	sw4_smoke
	OR IS_CHAR_DEAD sw4_ryder
	OR IS_CHAR_DEAD	sw4_sweet
		PRINT_NOW ( SWE4_04 ) 5000 1 // Your homies didn't make it.
		GOTO mission_failed_sweet4
	ENDIF
ENDIF

IF IS_CAR_DEAD sw4_player_car
	PRINT_NOW ( SWE4_04 ) 5000 1 // Your homies didn't make it.
	GOTO mission_failed_sweet4
ENDIF

//IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
//	GOSUB sw4_flat_hood_create
//	//sw4_text_timer_flag = 18
//	sw4_stage = 7
//	sw4_help = 4
//ENDIF

//IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_SPACE
//	IF NOT IS_CHAR_DEAD scplayer
//	AND NOT IS_CHAR_DEAD sw4_smoke
//	AND NOT	IS_CHAR_DEAD sw4_ryder
//	AND NOT	IS_CHAR_DEAD sw4_sweet
//		IF NOT IS_CAR_DEAD sw4_player_car
//		   	IF IS_CHAR_IN_CAR scplayer sw4_player_car
//			AND	IS_CHAR_IN_CAR sw4_smoke sw4_player_car
//			AND	IS_CHAR_IN_CAR sw4_ryder sw4_player_car
//			AND	IS_CHAR_IN_CAR sw4_sweet sw4_player_car
//				SET_CAR_COORDINATES sw4_player_car sw4_hood_x sw4_hood_y sw4_hood_z
//				sw4_stage = 3
//				sw4_text_timer_flag = 14
//			ENDIF
//		ENDIF
//	ENDIF
//ENDIF

// Get in the car...
IF sw4_stage = 0
	IF NOT IS_CAR_DEAD sw4_player_car
		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CHAR_DEAD sw4_smoke
		AND NOT	IS_CHAR_DEAD sw4_ryder
		AND NOT	IS_CHAR_DEAD sw4_sweet
			IF IS_CHAR_IN_CAR scplayer sw4_player_car
			AND IS_CHAR_IN_CAR sw4_smoke sw4_player_car
			AND IS_CHAR_IN_CAR sw4_ryder sw4_player_car
			AND IS_CHAR_IN_CAR sw4_sweet sw4_player_car
				CLEAR_PRINTS
				CLEAR_LOOK_AT sw4_smoke
				REMOVE_BLIP sw4_player_car_blip
				REMOVE_BLIP sw4_smoke_blip
				REMOVE_BLIP sw4_ryder_blip
				REMOVE_BLIP sw4_sweet_blip
				GET_GAME_TIMER sw4_text_timer_start
				sw4_blipped = 0
				sw4_stage = 1
			ENDIF
			IF IS_CHAR_IN_CAR scplayer sw4_player_car
				IF sw4_smoke_status = 0
					IF NOT IS_CHAR_IN_CAR sw4_smoke	sw4_player_car
						REMOVE_BLIP sw4_player_car_blip
						ADD_BLIP_FOR_CHAR sw4_smoke	sw4_smoke_blip
						SET_BLIP_AS_FRIENDLY sw4_smoke_blip TRUE
						sw4_smoke_status = 1				 
					ENDIF
				ENDIF
				IF sw4_smoke_status = 1
					IF NOT IS_CHAR_IN_CAR sw4_smoke	sw4_player_car
						IF NOT LOCATE_CHAR_ON_FOOT_CAR_2D sw4_smoke sw4_player_car 8.0 8.0 FALSE
							PRINT_NOW ( SWE4_09 ) 100 1 // Pick up Smoke.
						ELSE
							TASK_ENTER_CAR_AS_PASSENGER sw4_smoke sw4_player_car -1 0
							GET_GAME_TIMER sw4_timer_start[2]
							sw4_smoke_status = 2
						ENDIF
					ENDIF
				ENDIF
				IF sw4_smoke_status = 2	
					GET_GAME_TIMER sw4_timer_end[2]
					sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]
					IF sw4_timer_diff[2] > 1000
						//PRINT_NOW ( SWE4_09 ) 1000 1 // Pick up Smoke.
						IF sw4_timer_diff[2] > 3000
							sw4_smoke_status = 1
						ENDIF
					ENDIF
				ENDIF
				IF sw4_ryder_status = 0
					IF NOT IS_CHAR_IN_CAR sw4_ryder	sw4_player_car
						REMOVE_BLIP sw4_player_car_blip
						ADD_BLIP_FOR_CHAR sw4_ryder	sw4_ryder_blip
						SET_BLIP_AS_FRIENDLY sw4_ryder_blip TRUE
						sw4_ryder_status = 1				 
					ENDIF
				ENDIF
				IF sw4_ryder_status = 1
					IF NOT IS_CHAR_IN_CAR sw4_ryder	sw4_player_car
						IF NOT LOCATE_CHAR_ON_FOOT_CAR_2D sw4_ryder sw4_player_car 8.0 8.0 FALSE
							PRINT_NOW ( SWE4_10 ) 100 1 // Pick up ryder.
						ELSE
							TASK_ENTER_CAR_AS_PASSENGER sw4_ryder sw4_player_car -1 1
							GET_GAME_TIMER sw4_timer_start[2]
							sw4_ryder_status = 2
						ENDIF
					ENDIF
				ENDIF
				IF sw4_ryder_status = 2	
					GET_GAME_TIMER sw4_timer_end[2]
					sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]
					IF sw4_timer_diff[2] > 1000
						//PRINT_NOW ( SWE4_10 ) 1000 1 // Pick up ryder.
						IF sw4_timer_diff[2] > 3000
							sw4_ryder_status = 1
						ENDIF
					ENDIF
				ENDIF
				IF sw4_sweet_status = 0
					IF NOT IS_CHAR_IN_CAR sw4_sweet	sw4_player_car
						REMOVE_BLIP sw4_player_car_blip
						ADD_BLIP_FOR_CHAR sw4_sweet	sw4_sweet_blip
						SET_BLIP_AS_FRIENDLY sw4_sweet_blip TRUE
						sw4_sweet_status = 1				 
					ENDIF
				ENDIF
				IF sw4_sweet_status = 1
					IF NOT IS_CHAR_IN_CAR sw4_sweet	sw4_player_car
						IF NOT LOCATE_CHAR_ON_FOOT_CAR_2D sw4_sweet sw4_player_car 8.0 8.0 FALSE
							PRINT_NOW ( SWE4_11 ) 100 1 // Pick up sweet.
						ELSE
							TASK_ENTER_CAR_AS_PASSENGER sw4_sweet sw4_player_car -1 2
							GET_GAME_TIMER sw4_timer_start[2]
							sw4_sweet_status = 2
						ENDIF
					ENDIF
				ENDIF
				IF sw4_sweet_status = 2	
					GET_GAME_TIMER sw4_timer_end[2]
					sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]
					IF sw4_timer_diff[2] > 1000
						//PRINT_NOW ( SWE4_11 ) 1000 1 // Pick up sweet
						IF sw4_timer_diff[2] > 3000
							sw4_sweet_status = 1
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF

// If you're ready we can begin...
IF NOT IS_CHAR_DEAD scplayer
AND NOT IS_CHAR_DEAD sw4_smoke
AND NOT	IS_CHAR_DEAD sw4_ryder
AND NOT	IS_CHAR_DEAD sw4_sweet
	IF NOT IS_CAR_DEAD sw4_player_car
	   	IF IS_CHAR_IN_CAR scplayer sw4_player_car
		AND	IS_CHAR_IN_CAR sw4_smoke sw4_player_car
		AND	IS_CHAR_IN_CAR sw4_ryder sw4_player_car
		AND	IS_CHAR_IN_CAR sw4_sweet sw4_player_car
			IF sw4_stage = 1
				
				
				sw4_stage = 2
		   	ENDIF
		   	IF sw4_stage = 2
				sw4_cut = 0
				SET_PED_DENSITY_MULTIPLIER 0.0
				ADD_BLIP_FOR_COORD sw4_hood_x sw4_hood_y sw4_hood_z sw4_hood_blip 
				GET_GAME_TIMER sw4_timer_start[2]
				sw4_stage = 3
			ENDIF
			IF sw4_stage = 3
				IF sw4_help = 0
					GET_GAME_TIMER sw4_timer_end[2]
					sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]
					IF sw4_timer_diff[2] > 6000
						GET_GAME_TIMER sw4_timer_start[2]
						sw4_help = 1
					ENDIF
				ENDIF 
				IF sw4_help = 1
					GET_GAME_TIMER sw4_timer_end[2]
					sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]
					IF sw4_timer_diff[2] > 5000
						GET_GAME_TIMER sw4_timer_start[2]
						sw4_help = 2
					ENDIF
				ENDIF
			ENDIF
		   	IF sw4_stage = 3
				IF sw4_hood_create[1] = 0
				AND sw4_group_active = 0
					IF NOT IS_CAR_DEAD sw4_player_car
						IF LOCATE_CHAR_IN_CAR_3D scplayer sw4_hood_x sw4_hood_y sw4_hood_z 150.0 150.0 150.0 FALSE
							IF sw4_create = 0
								SET_PED_DENSITY_MULTIPLIER 0.0
								
								//sw4_create = 1
							ENDIF
						ENDIF
	 					IF LOCATE_CHAR_IN_CAR_3D scplayer sw4_hood_x sw4_hood_y sw4_hood_z 4.0 4.0 4.0 TRUE
							IF IS_CHAR_SITTING_IN_CAR scplayer sw4_player_car
								SET_PLAYER_CONTROL player1 OFF
								sw4_text_timer_flag = 9
								REMOVE_BLIP sw4_hood_blip
								SET_PLAYER_CONTROL player1 OFF
								SWITCH_WIDESCREEN ON
								DO_FADE 300 FADE_OUT
								GET_GAME_TIMER sw4_text_timer_start
								TASK_PLAY_ANIM scplayer GDB_Car_PLY GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 0
								SET_CURRENT_CHAR_WEAPON sw4_smoke WEAPONTYPE_TEC9
								TASK_PLAY_ANIM sw4_smoke GDB_Car_SMO GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 0
								SET_CURRENT_CHAR_WEAPON sw4_sweet WEAPONTYPE_TEC9
								TASK_PLAY_ANIM sw4_sweet GDB_Car_SWE GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 0
								SET_CURRENT_CHAR_WEAPON sw4_ryder WEAPONTYPE_TEC9
								TASK_PLAY_ANIM sw4_ryder GDB_Car_RYD GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 0
								SET_CAR_CAN_BE_VISIBLY_DAMAGED sw4_player_car FALSE
								CREATE_OBJECT kb_beer 2440.58 -1979.89 14.2 sw4_forty
								GOSUB sw4_flat_hood_create1
								
								GOSUB sw4_hood_group1
								GOSUB sw4_hood_react_group1
								IF NOT IS_CHAR_DEAD sw4_flat_hood[3]
									HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE sw4_flat_hood[3] TRUE
									TASK_PICK_UP_OBJECT sw4_flat_hood[3] sw4_forty 0.1 0.1 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL -1
								ENDIF
								REQUEST_MODEL laeroad30
								WHILE GET_FADING_STATUS
								WAIT 0
								ENDWHILE
								WHILE NOT HAS_MODEL_LOADED laeroad30
	   							WAIT 0
								ENDWHILE
								IF NOT IS_CAR_DEAD sw4_player_car
									POP_CAR_PANEL sw4_player_car WINDSCREEN_PANEL TRUE
								ENDIF
								
								sw4_stage = 4
								sw4_group_active = 1
								sw4_hood_create[0] = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
 	  	ENDIF
	ENDIF
ENDIF

// Fit a cutscene in here...
IF sw4_stage = 4
AND sw4_cut = 0
	IF NOT IS_CHAR_DEAD sw4_ryder
	AND NOT IS_CHAR_DEAD sw4_smoke
		IF NOT IS_CAR_DEAD sw4_player_car
		AND NOT IS_CHAR_DEAD sw4_sweet
			GET_GAME_TIMER sw4_timer_start[0]
			GET_GAME_TIMER sw4_text_timer_start
			sw4_cut = 1
		ENDIF
	ENDIF
ENDIF
IF sw4_stage = 4
	IF sw4_cut > 0
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 2000
			IF IS_BUTTON_PRESSED PAD1 CROSS
				CLEAR_PRINTS
				sw4_text_timer_flag = 14
				sw4_skip_flag = 1
				sw4_cut = 4
		 	ENDIF
		ENDIF
	ENDIF
	IF sw4_cut = 1
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 300
			IF sw4_hood_create[0] = 1
				IF NOT IS_CHAR_DEAD sw4_ryder
					SET_NEAR_CLIP 0.1
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS sw4_player_car -0.337 1.566 0.657 sw4_cam_x[1] sw4_cam_y[1] sw4_cam_z[1]
					GET_OFFSET_FROM_CAR_IN_WORLD_COORDS sw4_player_car 0.038 -1.033 0.088 sw4_cam_x[3] sw4_cam_y[3] sw4_cam_z[3]
					SET_FIXED_CAMERA_POSITION sw4_cam_x[1] sw4_cam_y[1] sw4_cam_z[1] 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT sw4_cam_x[3] sw4_cam_y[3] sw4_cam_z[3] JUMP_CUT
					//POINT_CAMERA_AT_CHAR sw4_ryder FIXED JUMP_CUT
					GET_GAME_TIMER sw4_timer_start[0]
					SET_FADING_COLOUR 0 0 0
					DO_FADE 300 FADE_IN
					WHILE GET_FADING_STATUS
	            		WAIT 0
					ENDWHILE
					sw4_text_timer_flag = 9
					sw4_cut = 2
				ENDIF
			ENDIF
	   	ENDIF
	ENDIF
	IF sw4_cut = 2											 
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 10700
			IF sw4_hood_create[0] = 1
				SET_FADING_COLOUR 0 0 0
				DO_FADE 300 FADE_OUT
				WHILE GET_FADING_STATUS
            		WAIT 0
				ENDWHILE
				//LOAD_SCENE sw4_cam_x[2] sw4_cam_y[2] sw4_cam_z[2]
				//GET_GAME_TIMER sw4_timer_start[0]
				sw4_cut = 31
			ENDIF
	   	ENDIF
	ENDIF
	IF sw4_cut = 31
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 11000
			SET_NEAR_CLIP 0.1
			SET_FADING_COLOUR 0 0 0
			SET_FIXED_CAMERA_POSITION 2098.1392 -1385.8824 30.5735 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT 2098.9521 -1385.3640 30.3086 JUMP_CUT
			DO_FADE 300 FADE_IN
			WHILE GET_FADING_STATUS
        		WAIT 0
			ENDWHILE
			sw4_cut = 3
		ENDIF
	ENDIF
	IF sw4_cut = 3
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 13000
			IF sw4_hood_create[0] = 1
				IF NOT IS_CAR_DEAD sw4_hood_car[0]
					//LOAD_SCENE sw4_cam_x[2] sw4_cam_y[2] sw4_cam_z[2]
					CAMERA_RESET_NEW_SCRIPTABLES
		            CAMERA_PERSIST_TRACK TRUE                   
		            CAMERA_PERSIST_POS TRUE                       
		            CAMERA_SET_VECTOR_MOVE 2098.1392 -1385.8824 30.5735 2075.7866 -1369.6572 23.6289 3500 TRUE
		            CAMERA_SET_VECTOR_TRACK 2098.9521 -1385.3640 30.3086 2076.3159 -1368.8141 23.7227 3500 TRUE
					IF NOT IS_CHAR_DEAD	sw4_flat_hood[2]
						TASK_PLAY_ANIM sw4_flat_hood[2] gsign1 GHANDS 4.0 FALSE FALSE FALSE FALSE 0
					ENDIF
					IF NOT IS_CHAR_DEAD	sw4_flat_hood[1]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[1] sw4_hood_anim
					ENDIF
					sw4_cut = 4
				ENDIF
			ENDIF
	   	ENDIF
	ENDIF
	IF sw4_cut = 4
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 20000
		OR sw4_skip_flag = 1
			SET_FADING_COLOUR 0 0 0
			DO_FADE 300 FADE_OUT
			sw4_cut = 6
	   	ENDIF
	ENDIF

	IF sw4_cut = 6
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 21000
		OR sw4_skip_flag = 1
			CLEAR_PRINTS
			sw4_text_timer_flag = 14
			//DELETE_OBJECT sw4_forty
			MARK_MODEL_AS_NO_LONGER_NEEDED laeroad30
			CAMERA_RESET_NEW_SCRIPTABLES
			RESTORE_CAMERA_JUMPCUT
			SET_CAMERA_BEHIND_PLAYER
			SET_PLAYER_CONTROL player1 ON
			SWITCH_WIDESCREEN OFF
			SET_FADING_COLOUR 0 0 0
			DO_FADE 300 FADE_IN
			REMOVE_ANIMATION GANGS
			REMOVE_ANIMATION GHANDS
			//PRINT_NOW ( SWE4_02 ) 5000 1 // Shoot up the neighborhood.
			
			GET_GAME_TIMER sw4_timer_start[3]
			GET_GAME_TIMER sw4_timer_start[4]
			GET_GAME_TIMER sw4_timer_start[5]
						
			PRINT_HELP SWE4_13  
			
			FIX_CAR_PANEL sw4_player_car WINDSCREEN_PANEL
			CLEAR_AREA sw4_hood_x sw4_hood_y sw4_hood_z 4.0 TRUE
			SET_CAR_CAN_BE_VISIBLY_DAMAGED sw4_player_car TRUE
			IF NOT IS_CAR_DEAD sw4_player_car
				GET_CAR_HEALTH sw4_player_car sw4_health
				IF sw4_health > 250
					sw4_health -= 250
				ENDIF
				sw4_health_display = sw4_health / 10
			ENDIF
			sw4_blipped = 1
			sw4_skip_flag = 0
			sw4_stage = 5
	   	ENDIF
	ENDIF
ENDIF

// Continue after cutscene...
IF sw4_stage = 5
AND sw4_cut >= 6 // or up
	GET_GAME_TIMER sw4_timer_end[5]
	sw4_timer_diff[5] = sw4_timer_end[5] - sw4_timer_start[5]
	IF sw4_timer_diff[5] > 5000	
		IF sw4_count = 0
			CLEAR_ONSCREEN_COUNTER sw4_health_display
			DISPLAY_ONSCREEN_COUNTER_WITH_STRING sw4_health_display COUNTER_DISPLAY_BAR SWE4_08
			//SET_ONSCREEN_COUNTER_COLOUR sw4_health_display HUD_COLOUR_RED
			
			PRINT_HELP SWE4_15  // This will allow you to see where your enemies are located.
			
			// Add counter and second help box.
			GET_GAME_TIMER sw4_timer_start[3]
			sw4_count = 1
		ENDIF
	ENDIF
	IF sw4_attack[0] = 0
	AND sw4_hood_create[0] = 1
		IF NOT IS_CAR_DEAD sw4_player_car
			IF NOT IS_CHAR_DEAD	sw4_flat_hood[0]
			AND NOT IS_CHAR_DEAD sw4_smoke
				IF LOCATE_CAR_3D sw4_player_car sw4_flat_hood_x[0] sw4_flat_hood_y[0] sw4_flat_hood_z[0] sw4_group_loc sw4_group_loc sw4_group_loc FALSE
				OR IS_CHAR_SHOOTING sw4_smoke
					IF NOT IS_CHAR_DEAD sw4_flat_hood[1]
					AND NOT IS_CHAR_DEAD sw4_flat_hood[2]
					AND NOT IS_CHAR_DEAD sw4_flat_hood[3]
					AND NOT IS_CAR_DEAD sw4_hood_car[0]
						DELETE_OBJECT sw4_forty
						//DROP_OBJECT sw4_flat_hood[3] TRUE
						SET_CURRENT_CHAR_WEAPON sw4_flat_hood[3] WEAPONTYPE_PISTOL
						PERFORM_SEQUENCE_TASK sw4_flat_hood[0] sw4_hood_react[0]
						CLEAR_SEQUENCE_TASK sw4_hood_react[0]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[1] sw4_hood_react[1]
						CLEAR_SEQUENCE_TASK sw4_hood_react[1]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[2] sw4_hood_react[2]
						CLEAR_SEQUENCE_TASK sw4_hood_react[2]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[3] sw4_hood_react[3]
						CLEAR_SEQUENCE_TASK sw4_hood_react[3]
						sw4_attack[0] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_attack[1] = 0
	AND sw4_hood_create[1] = 1
		IF NOT IS_CAR_DEAD sw4_player_car
			IF NOT IS_CHAR_DEAD	sw4_flat_hood[4]
			AND NOT IS_CHAR_DEAD sw4_smoke
				IF LOCATE_CAR_3D sw4_player_car sw4_flat_hood_x[4] sw4_flat_hood_y[4] sw4_flat_hood_z[4] sw4_group_loc sw4_group_loc sw4_group_loc FALSE
				OR IS_CHAR_SHOOTING sw4_smoke
					IF NOT IS_CHAR_DEAD sw4_flat_hood[5]	
					AND NOT IS_CHAR_DEAD sw4_flat_hood[6]
					AND NOT IS_CHAR_DEAD sw4_flat_hood[7]
					AND NOT IS_CAR_DEAD sw4_hood_car[1]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[4] sw4_hood_react[4]
						CLEAR_SEQUENCE_TASK sw4_hood_react[4]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[5] sw4_hood_react[5]
						CLEAR_SEQUENCE_TASK sw4_hood_react[5]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[6] sw4_hood_react[6]
						CLEAR_SEQUENCE_TASK sw4_hood_react[6]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[7] sw4_hood_react[7]
						CLEAR_SEQUENCE_TASK sw4_hood_react[7]
						sw4_attack[1] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_attack[2] = 0
	AND sw4_hood_create[2] = 1
		IF NOT IS_CAR_DEAD sw4_player_car
			IF NOT IS_CHAR_DEAD	sw4_flat_hood[8]
			AND NOT IS_CHAR_DEAD sw4_smoke
				IF LOCATE_CAR_3D sw4_player_car sw4_flat_hood_x[8] sw4_flat_hood_y[8] sw4_flat_hood_z[8] sw4_group_loc sw4_group_loc sw4_group_loc FALSE
				OR IS_CHAR_SHOOTING sw4_smoke
					IF NOT IS_CHAR_DEAD	sw4_flat_hood[9]
					AND NOT IS_CHAR_DEAD sw4_flat_hood[10]
					AND NOT IS_CHAR_DEAD sw4_flat_hood[11]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[8]  sw4_hood_react[8]
						CLEAR_SEQUENCE_TASK sw4_hood_react[8] 
						PERFORM_SEQUENCE_TASK sw4_flat_hood[9]  sw4_hood_react[9] 
						CLEAR_SEQUENCE_TASK sw4_hood_react[9]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[10] sw4_hood_react[10]
						CLEAR_SEQUENCE_TASK sw4_hood_react[10]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[11] sw4_hood_react[11]
						CLEAR_SEQUENCE_TASK sw4_hood_react[11]
						sw4_attack[2] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_attack[3] = 0
	AND sw4_hood_create[3] = 1
		IF NOT IS_CAR_DEAD sw4_player_car
			IF NOT IS_CHAR_DEAD	sw4_flat_hood[12]
			AND NOT IS_CHAR_DEAD sw4_smoke
				IF LOCATE_CAR_3D sw4_player_car sw4_flat_hood_x[12] sw4_flat_hood_y[12] sw4_flat_hood_z[12] sw4_group_loc sw4_group_loc sw4_group_loc FALSE
				OR IS_CHAR_SHOOTING sw4_smoke
					IF NOT IS_CHAR_DEAD	sw4_flat_hood[13]
					AND NOT IS_CHAR_DEAD sw4_flat_hood[14]
					AND NOT IS_CHAR_DEAD sw4_flat_hood[15]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[12] sw4_hood_react[12]
						CLEAR_SEQUENCE_TASK sw4_hood_react[12]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[13] sw4_hood_react[13]
						CLEAR_SEQUENCE_TASK sw4_hood_react[13]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[14] sw4_hood_react[14]
						CLEAR_SEQUENCE_TASK sw4_hood_react[14]
						PERFORM_SEQUENCE_TASK sw4_flat_hood[15] sw4_hood_react[15]
						CLEAR_SEQUENCE_TASK sw4_hood_react[15]
						sw4_attack[3] = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_hood_create[1] = 0
	AND sw4_group_active = 2
	AND sw4_blipped = 0
		IF NOT IS_CAR_DEAD sw4_player_car
			IF NOT IS_POINT_ON_SCREEN sw4_flat_hood_x[4] sw4_flat_hood_y[4] sw4_flat_hood_z[4] 4.0   //We don't care about this pop-in now that the Ballas are spawned in ahead of time
				IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer sw4_flat_hood_x[4] sw4_flat_hood_y[4] sw4_flat_hood_z[4] sw4_group_loc sw4_group_loc sw4_group_loc FALSE 
				   	GOSUB sw4_flat_hood_create2
					GOSUB sw4_hood_group2
					GOSUB sw4_hood_react_group2
					MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
					sw4_hood_create[1] = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_hood_create[2] = 0
	AND sw4_group_active = 3
	AND sw4_blipped = 0
		IF NOT IS_CAR_DEAD sw4_player_car
//			IF NOT IS_POINT_ON_SCREEN sw4_flat_hood_x[8] sw4_flat_hood_y[8] sw4_flat_hood_z[8] 4.0
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer sw4_flat_hood_x[8] sw4_flat_hood_y[8] sw4_flat_hood_z[8] sw4_group_loc sw4_group_loc sw4_group_loc FALSE
				GOSUB sw4_flat_hood_create3
				GOSUB sw4_hood_group3
				GOSUB sw4_hood_react_group3
				sw4_hood_create[2] = 1
			ENDIF
		ENDIF
	ENDIF
	IF sw4_hood_create[3] = 0
	AND sw4_group_active = 4
	AND sw4_blipped = 0
		IF NOT IS_CAR_DEAD sw4_player_car
//			IF NOT IS_POINT_ON_SCREEN sw4_flat_hood_x[12] sw4_flat_hood_y[12] sw4_flat_hood_z[12] 4.0
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer sw4_flat_hood_x[12] sw4_flat_hood_y[12] sw4_flat_hood_z[12] sw4_group_loc sw4_group_loc sw4_group_loc FALSE
  				GOSUB sw4_flat_hood_create4
				GOSUB sw4_hood_group4
				GOSUB sw4_hood_react_group4
				sw4_hood_create[3] = 1
			ENDIF
		ENDIF
	ENDIF
ENDIF


// Count the dead...
IF sw4_stage > 4
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD sw4_smoke
	AND NOT	IS_CHAR_DEAD sw4_ryder
	AND NOT	IS_CHAR_DEAD sw4_sweet
		IF NOT IS_CAR_DEAD sw4_player_car
		   	IF IS_CHAR_IN_CAR scplayer sw4_player_car
			AND	IS_CHAR_IN_CAR sw4_smoke sw4_player_car
			AND	IS_CHAR_IN_CAR sw4_ryder sw4_player_car
			AND	IS_CHAR_IN_CAR sw4_sweet sw4_player_car
				IF NOT IS_CAR_ON_FIRE sw4_player_car
					IF sw4_on_fire = 1
						CLEAR_ONSCREEN_COUNTER sw4_health_display
						DISPLAY_ONSCREEN_COUNTER_WITH_STRING sw4_health_display COUNTER_DISPLAY_BAR SWE4_08
						sw4_on_fire = 0
					ENDIF
					GET_CAR_HEALTH sw4_player_car sw4_health
					sw4_health -= 250
					sw4_health_display = sw4_health / 12
					IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer 2067.0 -1831.0 13.0 5.0 5.0 5.0 FALSE
						IF sw4_health = 750
							SET_CAR_HEALTH sw4_player_car 1450
						ENDIF
					ENDIF 
				ELSE
					IF sw4_on_fire = 0
						CLEAR_ONSCREEN_COUNTER sw4_health_display
						sw4_on_fire = 1
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
   	GET_GAME_TIMER sw4_timer_end[3]
	sw4_timer_diff[3] = sw4_timer_end[3] - sw4_timer_start[3]
	IF sw4_timer_diff[3] > 2000
	OR IS_CHAR_DEAD sw4_closest_ped
		sw4_anim = DRIVEBY_AI_ALL_DIRN //DRIVEBY_START_FROM_RHS
		sw4_gosub_char = sw4_sweet
		GOSUB sw4_shoot_at_closest
		sw4_anim = DRIVEBY_AI_ALL_DIRN //DRIVEBY_START_FROM_LHS
		sw4_gosub_char = sw4_ryder 
		GOSUB sw4_shoot_at_closest
		sw4_anim = DRIVEBY_AI_ALL_DIRN //DRIVEBY_START_FROM_RHS
		sw4_gosub_char = sw4_smoke 
		GOSUB sw4_shoot_at_closest
	ENDIF
	IF sw4_hood_create[0] = 1
		IF sw4_group_active = 1 
			IF sw4_hood_dead[0] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[0]
					REMOVE_BLIP sw4_flat_hood_blip[0]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[0]
					sw4_dead_count++
					sw4_hood_dead[0] = 1
				ENDIF
			ENDIF
		   	IF sw4_hood_dead[1] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[1]
					REMOVE_BLIP sw4_flat_hood_blip[1]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[1]
					sw4_dead_count++
					sw4_hood_dead[1] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[2] = 0
			   	IF IS_CHAR_DEAD sw4_flat_hood[2]
					REMOVE_BLIP sw4_flat_hood_blip[2]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[2]
					sw4_dead_count++
					sw4_hood_dead[2] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[3] = 0
			   	IF IS_CHAR_DEAD sw4_flat_hood[3]
					REMOVE_BLIP sw4_flat_hood_blip[3]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[3]
					sw4_dead_count++
					sw4_hood_dead[3] = 1
				ENDIF
			ENDIF
			IF sw4_group_dead[0] = 0
				IF sw4_hood_dead[0] = 1
				AND sw4_hood_dead[1] = 1
				AND sw4_hood_dead[2] = 1
				AND sw4_hood_dead[3] = 1
					IF sw4_blipped = 0
						IF sw4_audio_playing = 0
						AND sw4_counter = 0
							sw4_counter = 46 // SMOKE: We iced them! CJ, let’s hunt us down some more!
							//PRINT_NOW ( SWE4_LA ) 5000 1 // SMOKE: We iced them! CJ, let’s hunt us down some more!
						ENDIF
					ENDIF
					sw4_group_active = 2
					sw4_group_dead[0] = 1
			   	ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_hood_create[1] = 1
		IF sw4_group_active = 2
			IF sw4_hood_dead[4] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[4]
					REMOVE_BLIP sw4_flat_hood_blip[4]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[4]
					sw4_dead_count++
					sw4_hood_dead[4] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[5] = 0
		   	   	IF IS_CHAR_DEAD sw4_flat_hood[5]
					REMOVE_BLIP sw4_flat_hood_blip[5]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[5]
					sw4_dead_count++
					sw4_hood_dead[5] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[6] = 0
			  	IF IS_CHAR_DEAD sw4_flat_hood[6]
					REMOVE_BLIP sw4_flat_hood_blip[6]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[6]
					sw4_dead_count++
					sw4_hood_dead[6] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[7] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[7]
					REMOVE_BLIP sw4_flat_hood_blip[7]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[7]
					sw4_dead_count++
					sw4_hood_dead[7] = 1
				ENDIF
			ENDIF
			IF sw4_group_dead[1] = 0
				IF sw4_hood_dead[4] = 1
				AND sw4_hood_dead[5] = 1
				AND sw4_hood_dead[6] = 1
				AND sw4_hood_dead[7] = 1
					IF sw4_blipped = 0
						IF sw4_audio_playing = 0
						AND sw4_counter = 0
							sw4_counter = 47 // SWEET: They was too easy, let’s find us some more Ballas fools!
							//PRINT_NOW ( SWE4_LB ) 5000 1 // SWEET: They was too easy, let’s find us some more Ballas fools!
						ENDIF
					ENDIF
					sw4_group_active = 3
					sw4_group_dead[1] = 1
				ENDIF
			ENDIF
		ENDIF
   	ENDIF
	IF sw4_hood_create[2] = 1
		IF sw4_group_active = 3
			IF sw4_hood_dead[8] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[8]
					REMOVE_BLIP sw4_flat_hood_blip[8]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[8]
					sw4_dead_count++
					sw4_hood_dead[8] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[9] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[9]
					REMOVE_BLIP sw4_flat_hood_blip[9]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[9]
					sw4_dead_count++
					sw4_hood_dead[9] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[10] = 0
			   	IF IS_CHAR_DEAD sw4_flat_hood[10]
					REMOVE_BLIP sw4_flat_hood_blip[10]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[10]
					sw4_dead_count++
					sw4_hood_dead[10] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[11] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[11]
					REMOVE_BLIP sw4_flat_hood_blip[11]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[11]
					sw4_dead_count++
					sw4_hood_dead[11] = 1
				ENDIF
			ENDIF
			IF sw4_group_dead[2] = 0
				IF sw4_hood_dead[8] = 1
				AND sw4_hood_dead[9] = 1
				AND sw4_hood_dead[10] = 1
				AND sw4_hood_dead[11] = 1
					IF sw4_blipped = 0
						IF sw4_audio_playing = 0
						AND sw4_counter = 0
							sw4_counter = 48 // RYDER: What you waiting for, CJ? Find us some more Ballas fools to cap!
							//PRINT_NOW ( SWE4_LC ) 6000 1 // RYDER: What you waiting for, CJ? Find us some more Ballas fools to cap!
						ENDIF
					ENDIF
					sw4_group_active = 4
					sw4_group_dead[2] = 1
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_hood_create[3] = 1
		IF sw4_group_active = 4
			IF sw4_hood_dead[12] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[12]
					REMOVE_BLIP sw4_flat_hood_blip[12]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[12]
					sw4_dead_count++
					sw4_hood_dead[12] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[13] = 0
				IF IS_CHAR_DEAD sw4_flat_hood[13]
					REMOVE_BLIP sw4_flat_hood_blip[13]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[13]
					sw4_dead_count++
					sw4_hood_dead[13] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[14] = 0
   				IF IS_CHAR_DEAD sw4_flat_hood[14]
					REMOVE_BLIP sw4_flat_hood_blip[14]
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[14]
					sw4_dead_count++
					sw4_hood_dead[14] = 1
				ENDIF
			ENDIF
			IF sw4_hood_dead[15] = 0
 			   	IF IS_CHAR_DEAD sw4_flat_hood[15]
					REMOVE_BLIP sw4_flat_hood_blip[15] 
					MARK_CHAR_AS_NO_LONGER_NEEDED sw4_flat_hood[15]
					sw4_dead_count++
					sw4_hood_dead[15] = 1
				ENDIF
			ENDIF
			IF sw4_group_dead[3] = 0
				IF sw4_hood_dead[12] = 1
				AND sw4_hood_dead[13] = 1
				AND sw4_hood_dead[14] = 1
				AND sw4_hood_dead[15] = 1
					GET_GAME_TIMER sw4_text_timer_start
					sw4_group_active = 1
					sw4_group_dead[3] = 0
				ENDIF
			ENDIF
	   	ENDIF
	ENDIF
ENDIF

// If there are enough dead...   ...Show Sprayshop blip...   ...send in the goon cars...   ...add a wanted level...
IF sw4_stage = 5
	IF NOT IS_CAR_DEAD sw4_player_car
		IF sw4_stopped = 0
			IF IS_CAR_STOPPED sw4_player_car
			AND IS_CHAR_IN_CAR scplayer sw4_player_car
				GET_GAME_TIMER sw4_timer_start[0]
				GENERATE_RANDOM_INT_IN_RANGE 0 5 sw4_stopped_text
				sw4_hood_acc = 50
				IF NOT IS_CHAR_DEAD sw4_flat_hood[0]
					SET_CHAR_ACCURACY sw4_flat_hood[0] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[1]
					SET_CHAR_ACCURACY sw4_flat_hood[1] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[2]
					SET_CHAR_ACCURACY sw4_flat_hood[2] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[3]
					SET_CHAR_ACCURACY sw4_flat_hood[3] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[4]
					SET_CHAR_ACCURACY sw4_flat_hood[4] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[5]
					SET_CHAR_ACCURACY sw4_flat_hood[5] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[6]
					SET_CHAR_ACCURACY sw4_flat_hood[6] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[7]
					SET_CHAR_ACCURACY sw4_flat_hood[7] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[8]
					SET_CHAR_ACCURACY sw4_flat_hood[8] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[9]
					SET_CHAR_ACCURACY sw4_flat_hood[9] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[10]
					SET_CHAR_ACCURACY sw4_flat_hood[10] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[11]
					SET_CHAR_ACCURACY sw4_flat_hood[11] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[12]
					SET_CHAR_ACCURACY sw4_flat_hood[12] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[13]
					SET_CHAR_ACCURACY sw4_flat_hood[13] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[14]
					SET_CHAR_ACCURACY sw4_flat_hood[14] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[15]
					SET_CHAR_ACCURACY sw4_flat_hood[15] sw4_hood_acc
				ENDIF
				sw4_stopped = 1
			ENDIF
		ENDIF
		IF sw4_stopped = 1
			GET_GAME_TIMER sw4_timer_end[4]
			sw4_timer_diff[4] = sw4_timer_end[4] - sw4_timer_start[4]
			IF sw4_timer_diff[4] > 3000
				IF IS_CAR_STOPPED sw4_player_car 
					GET_GAME_TIMER sw4_timer_end[0]
					sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
					IF sw4_timer_diff[0] > 250
						IF sw4_stopped_text = 0
							IF sw4_audio_playing = 0
							AND sw4_counter = 0
								sw4_counter = 20 // RYDER: Holy shit we’re sitting ducks!
								//PRINT_NOW ( SWE4_DA ) 3000 1 // RYDER: Holy shit we’re sitting ducks!
								GET_GAME_TIMER sw4_timer_start[0]
								sw4_stopped = 2
							ENDIF
						ENDIF
						IF sw4_stopped_text = 1
							IF sw4_audio_playing = 0
							AND sw4_counter = 0
								sw4_counter = 21 // RYDER: Oh man, oh man, move! MOVE!
								//PRINT_NOW ( SWE4_DB ) 3000 1 // RYDER: Oh man, oh man, move! MOVE!
								GET_GAME_TIMER sw4_timer_start[0]
								sw4_stopped = 2
							ENDIF
						ENDIF
						IF sw4_stopped_text = 2
							IF sw4_audio_playing = 0
							AND sw4_counter = 0
								sw4_counter = 22 // RYDER: What you doing, CJ?!
								//PRINT_NOW ( SWE4_DC ) 3000 1 // RYDER: What you doing, CJ?!
								GET_GAME_TIMER sw4_timer_start[0]
								sw4_stopped = 2
							ENDIF
						ENDIF
						IF sw4_stopped_text = 3
							IF sw4_audio_playing = 0
							AND sw4_counter = 0
								sw4_counter = 23 // RYDER: He’s trying to get us killed!
								//PRINT_NOW ( SWE4_DD ) 3000 1 // RYDER: He’s trying to get us killed!
								GET_GAME_TIMER sw4_timer_start[0]
								sw4_stopped = 2
							ENDIF
						ENDIF
						IF sw4_stopped_text = 4
							IF sw4_audio_playing = 0
							AND sw4_counter = 0
								sw4_counter = 24 // RYDER: He’s trying to get us killed!
								//PRINT_NOW ( SWE4_DE ) 3000 1 // RYDER: He’s trying to get us killed!
								GET_GAME_TIMER sw4_timer_start[0]
								sw4_stopped = 2
							ENDIF
						ENDIF
						IF sw4_stopped_text = 5
							IF sw4_audio_playing = 0
							AND sw4_counter = 0
								sw4_counter = 25 // RYDER: Move it, CJ, move it!
								//PRINT_NOW ( SWE4_DF ) 3000 1 // RYDER: Move it, CJ, move it!
								GET_GAME_TIMER sw4_timer_start[0]
								sw4_stopped = 2
							ENDIF
						ENDIF

					ENDIF
				ELSE
					sw4_stopped = 2
				ENDIF
			ENDIF
		ENDIF
		IF sw4_stopped = 2
			IF NOT IS_CAR_STOPPED sw4_player_car
				GET_GAME_TIMER sw4_timer_end[0]
				sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
				IF sw4_timer_diff[0] > 3100
					sw4_stopped = 0
				ENDIF  
				//CLEAR_PRINTS
				sw4_hood_acc = 30
				IF NOT IS_CHAR_DEAD sw4_flat_hood[0]
					SET_CHAR_ACCURACY sw4_flat_hood[0] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[1]
					SET_CHAR_ACCURACY sw4_flat_hood[1] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[2]
					SET_CHAR_ACCURACY sw4_flat_hood[2] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[3]
					SET_CHAR_ACCURACY sw4_flat_hood[3] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[4]
					SET_CHAR_ACCURACY sw4_flat_hood[4] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[5]
					SET_CHAR_ACCURACY sw4_flat_hood[5] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[6]
					SET_CHAR_ACCURACY sw4_flat_hood[6] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[7]
					SET_CHAR_ACCURACY sw4_flat_hood[7] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[8]
					SET_CHAR_ACCURACY sw4_flat_hood[8] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[9]
					SET_CHAR_ACCURACY sw4_flat_hood[9] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[10]
					SET_CHAR_ACCURACY sw4_flat_hood[10] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[11]
					SET_CHAR_ACCURACY sw4_flat_hood[11] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[12]
					SET_CHAR_ACCURACY sw4_flat_hood[12] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[13]
					SET_CHAR_ACCURACY sw4_flat_hood[13] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[14]
					SET_CHAR_ACCURACY sw4_flat_hood[14] sw4_hood_acc
				ENDIF
				IF NOT IS_CHAR_DEAD sw4_flat_hood[15]
					SET_CHAR_ACCURACY sw4_flat_hood[15] sw4_hood_acc
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_dead_count = 16
		IF sw4_audio_playing = 0
		AND sw4_counter = 0
			sw4_counter = 33 // SWEET: Go Carl, GO!!
			//PRINT ( SWE4_GA ) 5000 1 // SWEET: Go Carl, GO!!
			GET_GAME_TIMER sw4_blip_timer_start
			sw4_dead_count = 17
		ENDIF
	ENDIF
	IF sw4_dead_count = 17
		GET_GAME_TIMER sw4_blip_timer_end
		sw4_blip_timer_diff = sw4_blip_timer_end - sw4_blip_timer_start 
		IF sw4_blip_timer_diff > 3000
			SET_FREE_RESPRAYS ON
			CLEAR_PRINTS
			SET_MAX_WANTED_LEVEL sw4_max_wanted
			IF sw4_wanted_flag = 0
				SET_CREATE_RANDOM_COPS TRUE
				ALTER_WANTED_LEVEL_NO_DROP player1 2
			ENDIF
			LOCK_CAR_DOORS sw4_player_car CARLOCK_UNLOCKED
			CLEAR_ONSCREEN_COUNTER sw4_health_display
			REMOVE_BLIP spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			GET_GAME_TIMER sw4_blip_timer_start
			SET_PED_DENSITY_MULTIPLIER 0.5
			REMOVE_BLIP sw4_flat_hood_blip[0]
			REMOVE_BLIP sw4_flat_hood_blip[1]
			REMOVE_BLIP sw4_flat_hood_blip[2]
			REMOVE_BLIP sw4_flat_hood_blip[3]
			REMOVE_BLIP sw4_flat_hood_blip[4]
			REMOVE_BLIP sw4_flat_hood_blip[5]
			REMOVE_BLIP sw4_flat_hood_blip[6]
			REMOVE_BLIP sw4_flat_hood_blip[7]
			REMOVE_BLIP sw4_flat_hood_blip[8]
			REMOVE_BLIP sw4_flat_hood_blip[9]
			REMOVE_BLIP sw4_flat_hood_blip[10]
			REMOVE_BLIP sw4_flat_hood_blip[11]
			REMOVE_BLIP sw4_flat_hood_blip[12]
			REMOVE_BLIP sw4_flat_hood_blip[13]
			REMOVE_BLIP sw4_flat_hood_blip[14]
			REMOVE_BLIP sw4_flat_hood_blip[15]
			GET_GAME_TIMER sw4_text_timer_start
			sw4_text_timer_flag = 14
			sw4_cut = 0
			sw4_stage = 6
			SET_WANTED_MULTIPLIER 1.0
			sw4_blip_counter = 0
		ENDIF
	ENDIF
ENDIF

// If player clears..
IF sw4_stage = 6
AND sw4_text_timer_flag = 18
	GET_GAME_TIMER sw4_blip_timer_end
	sw4_blip_timer_diff = sw4_blip_timer_end - sw4_blip_timer_start 
	IF sw4_blip_counter = 0
		IF sw4_blip_timer_diff > 500
			PRINT_HELP ( SWE4_07 ) // Marked on Radar as a Spray can
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 1
		IF sw4_blip_timer_diff > 1000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 2
		IF sw4_blip_timer_diff > 1500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 3
		IF sw4_blip_timer_diff > 2000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 4
		IF sw4_blip_timer_diff > 2500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 5
		IF sw4_blip_timer_diff > 3000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 6
		IF sw4_blip_timer_diff > 3500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 7
		IF sw4_blip_timer_diff > 4000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 8
		IF sw4_blip_timer_diff > 4500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 9
		IF sw4_blip_timer_diff > 5000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 10
		IF sw4_blip_timer_diff > 5500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 11
		IF sw4_blip_timer_diff > 6000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 12
		IF sw4_blip_timer_diff > 6500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 13
		IF sw4_blip_timer_diff > 7000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 14
		IF sw4_blip_timer_diff > 7500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 15
		IF sw4_blip_timer_diff > 8000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 16
		IF sw4_blip_timer_diff > 8500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 17
		IF sw4_blip_timer_diff > 9000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 18
		IF sw4_blip_timer_diff > 9500
			REMOVE_BLIP	spray_shop1
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_blip_counter = 19
		IF sw4_blip_timer_diff > 10000
			REMOVE_BLIP	spray_shop1
			ADD_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			REMOVE_BLIP	sw4_spray_marker
			ADD_BLIP_FOR_COORD 2075.55 -1831.09 12.21 sw4_spray_marker
			sw4_blip_counter++
		ENDIF
	ENDIF
	IF sw4_cut = 0
		IF NOT IS_CHAR_DEAD scplayer
			IF LOCATE_CHAR_IN_CAR_3D scplayer 2075.55 -1831.09 12.21 4.0 4.0 4.0 TRUE
				CLEAR_PRINTS
				PRINT_HELP ( SWE4_06 ) // Drive into the garage
				REMOVE_BLIP sw4_spray_marker
				sw4_cut = 1
			ENDIF
		ENDIF
	ENDIF
	IF NOT IS_WANTED_LEVEL_GREATER player1 0
		IF LOCATE_CHAR_IN_CAR_3D scplayer 2075.55 -1831.09 12.21 20.0 20.0 20.0 TRUE
		OR LOCATE_CHAR_IN_CAR_3D scplayer 1024.55 -1039.09 35.21 20.0 20.0 20.0 TRUE
			CLEAR_HELP
			GET_GAME_TIMER sw4_timer_start[0]
			REMOVE_BLIP sw4_spray_marker // this causes it to show back up for a frame?
			sw4_stage = 7 
			sw4_cut = 0
		ELSE
			ALTER_WANTED_LEVEL_NO_DROP player1 2
		ENDIF
	ENDIF
ENDIF

IF sw4_stage = 7
	GET_GAME_TIMER sw4_timer_end[0]
	sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
	IF sw4_timer_diff[0] > 5000
		IF sw4_help = 2
			SET_FREE_RESPRAYS OFF
			PRINT_HELP ( WANT_L )
			ADD_BLIP_FOR_COORD sw4_player_car_x sw4_player_car_y sw4_player_car_z sw4_end_blip
			REMOVE_BLIP spray_shop1
			ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1
			GET_GAME_TIMER sw4_timer_start[0]
			GET_GAME_TIMER sw4_text_timer_start
			sw4_help = 3
		ENDIF
	ENDIF
	IF sw4_help = 3
		GET_GAME_TIMER sw4_timer_end[0]
		sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
		IF sw4_timer_diff[0] > 5000
			IF sw4_wanted_flag = 0
				sw4_text_timer_flag = 18
				GET_GAME_TIMER sw4_text_timer_start
				PRINT_NOW ( SWE4_14 ) 8000 1 // Take your homies back to the hood.
				sw4_wanted_flag = 1
  			ENDIF
			sw4_help = 4
		ENDIF
	ENDIF
	IF sw4_help = 4
		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD sw4_player_car
			IF IS_CHAR_IN_CAR scplayer sw4_player_car
				IF LOCATE_CHAR_IN_CAR_3D scplayer sw4_player_car_x sw4_player_car_y sw4_player_car_z 4.0 4.0 4.0 TRUE
					IF IS_CHAR_SITTING_IN_CAR scplayer sw4_player_car
						SET_PLAYER_CONTROL player1 OFF
						TASK_CAR_TEMP_ACTION scplayer sw4_player_car TEMPACT_HANDBRAKESTRAIGHT 2000000
						
						CLEAR_AREA 2518.0 -1673.0 14.0 50.0 FALSE
						CLEAR_PRINTS
						SET_PED_DENSITY_MULTIPLIER 0.0
						SWITCH_WIDESCREEN ON
						DO_FADE 300 FADE_OUT
						GET_GAME_TIMER sw4_timer_start[0]
						GET_GAME_TIMER sw4_text_timer_start
						TASK_PLAY_ANIM scplayer GDB_Car2_PLY GHETTO_DB 4.0 FALSE FALSE FALSE TRUE 0
						SET_CURRENT_CHAR_WEAPON sw4_smoke WEAPONTYPE_TEC9
						TASK_PLAY_ANIM sw4_smoke GDB_Car2_SMO GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 0
						SET_CURRENT_CHAR_WEAPON sw4_sweet WEAPONTYPE_TEC9
						TASK_PLAY_ANIM sw4_sweet GDB_Car2_SWE GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 0
						HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
						SET_CAR_CAN_BE_VISIBLY_DAMAGED sw4_player_car FALSE
						
						sw4_cut = 0
						sw4_stage = 8
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF
ENDIF


// End cutscene...  
IF sw4_stage = 8
	IF NOT IS_CAR_DEAD sw4_player_car
		IF sw4_cut = 0
			IF sw4_cut_text = 0
				IF sw4_audio_playing = 0
					IF IS_CAR_STOPPED sw4_player_car
						IF NOT IS_CHAR_DEAD sw4_ryder
							POP_CAR_PANEL sw4_player_car WINDSCREEN_PANEL TRUE
							SET_FADING_COLOUR 0 0 0
							
							OPEN_SEQUENCE_TASK sw4_ryder_end_cut 
								TASK_LEAVE_CAR -1 sw4_player_car
					 			TASK_GO_STRAIGHT_TO_COORD -1 2513.79 -1663.09 14.56 PEDMOVE_WALK -2
							CLOSE_SEQUENCE_TASK sw4_ryder_end_cut
							PERFORM_SEQUENCE_TASK sw4_ryder sw4_ryder_end_cut
						ENDIF
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS sw4_player_car -0.4 1.566 0.657 sw4_cam_x[1] sw4_cam_y[1] sw4_cam_z[1]
						GET_OFFSET_FROM_CAR_IN_WORLD_COORDS sw4_player_car 0.038 -1.033 0.088 sw4_cam_x[3] sw4_cam_y[3] sw4_cam_z[3]
						SET_FIXED_CAMERA_POSITION sw4_cam_x[1] sw4_cam_y[1] sw4_cam_z[1] 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT sw4_cam_x[3] sw4_cam_y[3] sw4_cam_z[3] JUMP_CUT

						DO_FADE 300 FADE_IN 
						WHILE GET_FADING_STATUS
						WAIT 0
						ENDWHILE
						sw4_counter = 42
						//PRINT_NOW ( SWE4_KG ) 5000 1 // SWEET: You’re down with the Grove and those Ballas know it,	
						GET_GAME_TIMER sw4_timer_start[0]
						sw4_cut_text = 1
					ENDIF
				ENDIF
			ENDIF  
			IF sw4_cut_text = 1
				GET_GAME_TIMER sw4_timer_end[0]
				sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
				IF sw4_timer_diff[0] > 1000
					IF sw4_audio_playing = 0
					AND sw4_counter = 0
						sw4_counter = 43
						//PRINT ( SWE4_KH ) 4000 1 // SWEET: so watch yourself from now on.
						GET_GAME_TIMER sw4_timer_start[0]
						sw4_cut_text = 2
						sw4_cut = 3
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF sw4_cut = 3
			GET_GAME_TIMER sw4_timer_end[0]
			sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
			IF sw4_timer_diff[0] > 1000
				REMOVE_CHAR_ELEGANTLY sw4_ryder
				IF sw4_cut_text = 2
					IF sw4_audio_playing = 0
					AND sw4_counter = 0
						sw4_counter = 44
						//PRINT ( SWE4_KE ) 4000 1 // CARL: I'm cool, dude. Look, I'll see you cats later.
						GET_GAME_TIMER sw4_timer_start[0]
						sw4_cut_text = 3
					ENDIF
				ENDIF
			ENDIF
			GET_GAME_TIMER sw4_timer_end[0]
			sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
			IF sw4_cut_text = 3
				IF sw4_timer_diff[0] > 1000
					IF sw4_audio_playing = 0
					AND sw4_counter = 0
						sw4_counter = 45
						//PRINT ( SWE4_KI ) 4000 1 // SMOKE: Here, go get yourself a beer and some colours.
						GET_GAME_TIMER sw4_timer_start[0]
						sw4_cut_text = 4
					ENDIF
				ENDIF
			ENDIF
			GET_GAME_TIMER sw4_timer_end[0]
			sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
			IF NOT IS_CHAR_DEAD sw4_sweet
				IF sw4_timer_diff[0] > 1000
					GET_SCRIPT_TASK_STATUS sw4_sweet TASK_PLAY_ANIM sw4_sweet_status
					IF sw4_sweet_status = FINISHED_TASK
					OR sw4_cut_text = 4
						PERFORM_SEQUENCE_TASK sw4_sweet sw4_ryder_end_cut
						GET_GAME_TIMER sw4_timer_start[0]
						sw4_cut = 4
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF sw4_cut = 4
			IF NOT IS_CHAR_DEAD sw4_smoke
				GET_SCRIPT_TASK_STATUS sw4_smoke TASK_PLAY_ANIM sw4_smoke_status
				IF sw4_smoke_status = FINISHED_TASK
				OR sw4_audio_playing = 0 // ***********************************************  Remove when anims are shortened.
					PERFORM_SEQUENCE_TASK sw4_smoke sw4_ryder_end_cut
					WAIT 100
					PERFORM_SEQUENCE_TASK scplayer sw4_ryder_end_cut
					GET_GAME_TIMER sw4_timer_start[0]
					SET_FADING_COLOUR 0 0 0
					DO_FADE 1500 FADE_OUT
					sw4_cut = 5
				ENDIF
			ENDIF
	   	ENDIF
		IF sw4_cut = 5
			GET_GAME_TIMER sw4_timer_end[0]
			sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
			IF sw4_timer_diff[0] > 1000
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				TASK_PLAY_ANIM scplayer GDB_Car2_PLY GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 1
				IF IS_CHAR_IN_ANY_CAR scplayer
					WARP_CHAR_FROM_CAR_TO_COORD scplayer 2511.0 -1671.0 12.5
				ELSE
					SET_CHAR_COORDINATES scplayer 2511.0 -1671.0 12.5
				ENDIF
				TASK_PLAY_ANIM scplayer GDB_Car2_PLY GHETTO_DB 4.0 FALSE FALSE FALSE FALSE 1
				SET_CHAR_HEADING scplayer 223.0
				REMOVE_CHAR_ELEGANTLY sw4_sweet
				REMOVE_CHAR_ELEGANTLY sw4_smoke
				GET_GAME_TIMER sw4_timer_start[0]
				sw4_cut = 7
			ENDIF
	   	ENDIF
		IF sw4_cut = 7
			GET_GAME_TIMER sw4_timer_end[0]
			sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
			IF sw4_timer_diff[0] > 100
				IF NOT IS_CAR_DEAD sw4_player_car
					DELETE_CHAR sw4_smoke
					DELETE_CHAR sw4_smoke
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					DO_FADE 2000 FADE_IN
					WHILE GET_FADING_STATUS
					WAIT 0
					ENDWHILE
					GOTO mission_passed_sweet4
					sw4_cut = 8
				ENDIF
		   	ENDIF
		ENDIF
	ENDIF
ENDIF

// If player leaves car...
IF sw4_stage > 2
AND sw4_stage < 8
	IF sw4_blipped = 0
   		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD	sw4_player_car
			IF NOT IS_CHAR_IN_CAR scplayer sw4_player_car
			AND IS_CHAR_ON_FOOT	scplayer
				CLOSE_ALL_CAR_DOORS sw4_player_car
				// Drive Away
				IF sw4_sequence_flag = 0
					OPEN_SEQUENCE_TASK sw4_drive_away
						TASK_SHUFFLE_TO_NEXT_CAR_SEAT -1 sw4_player_car
						TASK_CAR_DRIVE_WANDER -1 sw4_player_car 50.0 DRIVINGMODE_AVOIDCARS
					CLOSE_SEQUENCE_TASK sw4_drive_away
					sw4_sequence_flag = 1
				ENDIF
				sw4_in_car = 0
				sw4_timer_remaining = 15000
				ADD_BLIP_FOR_CAR sw4_player_car sw4_player_car_blip
				SET_BLIP_AS_FRIENDLY sw4_player_car_blip TRUE
				IF sw4_stage = 3
					REMOVE_BLIP sw4_hood_blip
				ENDIF
				IF sw4_stage = 5
					CLEAR_ONSCREEN_COUNTER sw4_health_display
					IF sw4_group_active = 1
						IF sw4_hood_dead[0] = 0
							REMOVE_BLIP sw4_flat_hood_blip[0]
						ENDIF
						IF sw4_hood_dead[1] = 0
							REMOVE_BLIP sw4_flat_hood_blip[1]
						ENDIF
						IF sw4_hood_dead[2] = 0
							REMOVE_BLIP sw4_flat_hood_blip[2]
						ENDIF
						IF sw4_hood_dead[3] = 0
							REMOVE_BLIP sw4_flat_hood_blip[3]
						ENDIF
					ENDIF
					IF sw4_group_active = 2
						IF sw4_hood_dead[4] = 0
							REMOVE_BLIP sw4_flat_hood_blip[4]
						ENDIF
						IF sw4_hood_dead[5] = 0
							REMOVE_BLIP sw4_flat_hood_blip[5]
						ENDIF
						IF sw4_hood_dead[6] = 0
							REMOVE_BLIP sw4_flat_hood_blip[6]
						ENDIF
						IF sw4_hood_dead[7] = 0
							REMOVE_BLIP sw4_flat_hood_blip[7]
						ENDIF
					ENDIF
					IF sw4_group_active = 3
						IF sw4_hood_dead[8] = 0
							REMOVE_BLIP sw4_flat_hood_blip[8]
						ENDIF
						IF sw4_hood_dead[9] = 0
							REMOVE_BLIP sw4_flat_hood_blip[9]
						ENDIF
						IF sw4_hood_dead[10] = 0
							REMOVE_BLIP sw4_flat_hood_blip[10]
						ENDIF
						IF sw4_hood_dead[11] = 0
							REMOVE_BLIP sw4_flat_hood_blip[11]
						ENDIF
					ENDIF
					IF sw4_group_active = 4
						IF sw4_hood_dead[12] = 0
							REMOVE_BLIP sw4_flat_hood_blip[12]
						ENDIF
						IF sw4_hood_dead[13] = 0
							REMOVE_BLIP sw4_flat_hood_blip[13]
						ENDIF
						IF sw4_hood_dead[14] = 0
							REMOVE_BLIP sw4_flat_hood_blip[14]
						ENDIF
						IF sw4_hood_dead[15] = 0
							REMOVE_BLIP sw4_flat_hood_blip[15]
						ENDIF
					ENDIF
				ENDIF
				IF sw4_stage = 6
					REMOVE_BLIP sw4_spray_marker
				ENDIF
				IF sw4_stage = 7
					REMOVE_BLIP sw4_end_blip
				ENDIF
				LOCK_CAR_DOORS sw4_player_car CARLOCK_UNLOCKED
				GET_GAME_TIMER sw4_timer_start[2]
				sw4_blipped = 1
			ENDIF
		ENDIF
	ENDIF
	IF	sw4_blipped = 1
		IF sw4_timer_remaining_secs >= 0
			GET_GAME_TIMER sw4_timer_end[2]
			sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]
			sw4_timer_remaining = 10000 - sw4_timer_diff[2]
			sw4_timer_remaining_secs = sw4_timer_remaining / 1000
			IF sw4_in_car = 0
				GET_GAME_TIMER sw4_timer_start[0]
				GENERATE_RANDOM_INT_IN_RANGE 0 2 sw4_stopped_text
				sw4_in_car = 1
			ENDIF
			IF sw4_in_car = 1
				GET_GAME_TIMER sw4_timer_end[0]
				sw4_timer_diff[0] = sw4_timer_end[0] - sw4_timer_start[0]
				IF sw4_timer_diff[0] > 200
					IF sw4_stopped_text = 0
						IF sw4_audio_playing = 0
						AND sw4_counter = 0
							sw4_counter = 26 // RYDER: I told you he was a buster!
							sw4_stopped_text = 5
							//PRINT ( SWE4_EA ) 3000 1 // RYDER: I told you he was a buster!
						ENDIF
					ENDIF
					IF sw4_stopped_text = 1
						IF sw4_audio_playing = 0
						AND sw4_counter = 0
							sw4_counter = 27 // SWEET: Don’t run out on me again, Carl!
							sw4_stopped_text = 5
							//PRINT ( SWE4_EB ) 3000 1 // SWEET: Don’t run out on me again, Carl!
						ENDIF
					ENDIF
					IF sw4_stopped_text = 2
						IF sw4_audio_playing = 0
						AND sw4_counter = 0
							sw4_counter = 28 // SMOKE: Carl’s quitting on us!
							sw4_stopped_text = 5
							//PRINT ( SWE4_EC ) 3000 1 // SMOKE: Carl’s quitting on us!
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		IF sw4_timer_remaining < 100
		AND sw4_timer_remaining > 0
			IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer sw4_player_car 15.0 15.0 15.0 FALSE
				LOCK_CAR_DOORS sw4_player_car CARLOCK_LOCKED
			ENDIF
		ENDIF
		IF sw4_timer_remaining_secs < 0
			sw4_timer_remaining_secs = 0
		ENDIF
		IF sw4_timer_remaining_secs = 0
			IF NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer sw4_player_car 15.0 15.0 15.0 FALSE
				//GET_CHAR_IN_CAR_PASSENGER_SEAT sw4_player_car 0 sw4_passenger
				IF NOT IS_CHAR_DEAD sw4_smoke
					IF sw4_audio_playing = 0
					AND sw4_counter = 0
						LOCK_CAR_DOORS sw4_player_car CARLOCK_LOCKED
						PERFORM_SEQUENCE_TASK sw4_smoke sw4_drive_away
						
						CLEAR_PRINTS
						sw4_counter = 29
						//PRINT_NOW ( SWE4_FA ) 3000 1 // RYDER: He’s ditched us!
						//PRINT ( SWE4_FD ) 3000 1 // SMOKE: Motherfucker! I’ve got the wheel! 
						GET_GAME_TIMER sw4_timer_start[2]
						REMOVE_BLIP sw4_player_car_blip
						sw4_stage = 666
					ENDIF
			   	ENDIF
			ENDIF
		ENDIF
		IF NOT IS_CHAR_DEAD scplayer
		AND NOT IS_CAR_DEAD	sw4_player_car
 			IF IS_CHAR_IN_CAR scplayer sw4_player_car
				sw4_in_car = 0
				REMOVE_BLIP sw4_player_car_blip
				IF sw4_stage = 3
					ADD_BLIP_FOR_COORD sw4_hood_x sw4_hood_y sw4_hood_z sw4_hood_blip
				ENDIF
				IF sw4_stage = 5
					IF sw4_count = 1
						CLEAR_ONSCREEN_COUNTER sw4_health_display
						DISPLAY_ONSCREEN_COUNTER_WITH_STRING sw4_health_display COUNTER_DISPLAY_BAR SWE4_08
						//SET_ONSCREEN_COUNTER_COLOUR sw4_health_display HUD_COLOUR_RED
					ENDIF

					IF NOT IS_CHAR_DEAD sw4_flat_hood[0]
						REMOVE_BLIP sw4_flat_hood_blip[0]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[0] sw4_flat_hood_blip[0]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[1]
						REMOVE_BLIP sw4_flat_hood_blip[1]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[1] sw4_flat_hood_blip[1]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[2]
						REMOVE_BLIP sw4_flat_hood_blip[2]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[2] sw4_flat_hood_blip[2]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[3]  
						REMOVE_BLIP sw4_flat_hood_blip[3]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[3] sw4_flat_hood_blip[3]
					ENDIF
					
					IF NOT IS_CHAR_DEAD sw4_flat_hood[4]
						REMOVE_BLIP sw4_flat_hood_blip[4]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[4] sw4_flat_hood_blip[4]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[5]
						REMOVE_BLIP sw4_flat_hood_blip[5]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[5] sw4_flat_hood_blip[5]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[6]
						REMOVE_BLIP sw4_flat_hood_blip[6]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[6] sw4_flat_hood_blip[6]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[7]
						REMOVE_BLIP sw4_flat_hood_blip[7]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[7] sw4_flat_hood_blip[7]
					ENDIF
					
					IF NOT IS_CHAR_DEAD sw4_flat_hood[8]
						REMOVE_BLIP sw4_flat_hood_blip[8]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[8] sw4_flat_hood_blip[8]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[9]
						REMOVE_BLIP sw4_flat_hood_blip[9]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[9] sw4_flat_hood_blip[9]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[10]
						REMOVE_BLIP sw4_flat_hood_blip[10]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[10] sw4_flat_hood_blip[10]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[11]
						REMOVE_BLIP sw4_flat_hood_blip[11]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[11] sw4_flat_hood_blip[11]
					ENDIF
					
					IF NOT IS_CHAR_DEAD sw4_flat_hood[12]
						REMOVE_BLIP sw4_flat_hood_blip[12]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[12] sw4_flat_hood_blip[12]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[13]
						REMOVE_BLIP sw4_flat_hood_blip[13]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[13] sw4_flat_hood_blip[13]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[14]
						REMOVE_BLIP sw4_flat_hood_blip[14]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[14] sw4_flat_hood_blip[14]
					ENDIF
					IF NOT IS_CHAR_DEAD sw4_flat_hood[15]
						REMOVE_BLIP sw4_flat_hood_blip[15]
						ADD_BLIP_FOR_CHAR sw4_flat_hood[15] sw4_flat_hood_blip[15]
					ENDIF
				ENDIF
				IF sw4_stage = 6
					sw4_text_timer_flag = 18
					GET_GAME_TIMER sw4_blip_timer_start
					sw4_blip_timer_start -= 5000
					REMOVE_BLIP sw4_spray_marker
					ADD_BLIP_FOR_COORD 2075.55 -1831.09 12.21 sw4_spray_marker
				ENDIF
				IF sw4_stage = 7
					REMOVE_BLIP sw4_end_blip
					ADD_BLIP_FOR_COORD sw4_end_x sw4_end_y sw4_end_z sw4_end_blip
				ENDIF
				CLEAR_PRINTS
				sw4_timer_remaining_secs = 1
				GET_GAME_TIMER sw4_text_timer_start
				sw4_blipped = 0
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF sw4_stage = 666
	GET_GAME_TIMER sw4_timer_end[2]
	sw4_timer_diff[2] = sw4_timer_end[2] - sw4_timer_start[2]
	IF sw4_timer_diff[2] > 1000
		IF NOT IS_CHAR_DEAD sw4_smoke 
		AND NOT IS_CAR_DEAD sw4_player_car
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				TASK_CAR_DRIVE_WANDER sw4_smoke sw4_player_car 50.0 DRIVINGMODE_AVOIDCARS
				sw4_counter = 32
				sw4_stage = 667
			ENDIF
		ENDIF
	ENDIF
ENDIF

IF sw4_stage = 667
	IF NOT IS_CAR_DEAD sw4_player_car
		IF NOT IS_CAR_ON_SCREEN sw4_player_car
		OR NOT LOCATE_CHAR_ANY_MEANS_CAR_3D scplayer sw4_player_car 50.0 50.0 50.0 FALSE
			PRINT_NOW ( SWE4_05 ) 5000 1 // You sure you're down for the 'hood?
			GOTO mission_failed_sweet4
		ENDIF
	ENDIF
ENDIF


// Text Timers
IF sw4_blipped = 0
	IF sw4_stage = 3
		IF sw4_text_timer_flag = -1
			IF sw4_audio_playing = 0
				PRINT ( SWE4_01 ) 100 1 // Drive your homies into Balla territory.
			ENDIF
			GET_GAME_TIMER sw4_text_timer_end 
			sw4_text_timer_diff = sw4_text_timer_end - sw4_text_timer_start
			IF sw4_text_timer_diff > 3000
				CLEAR_PRINTS
				sw4_text_timer_flag++
				GET_GAME_TIMER sw4_text_timer_start
			ENDIF 
		ENDIF
		IF sw4_text_timer_flag = 0
			IF sw4_audio_playing = 0
			AND	sw4_counter = 0
				sw4_counter = 1	 // RYDER: Where we going, homie?
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 1
			IF sw4_audio_playing = 0
			AND	sw4_counter = 0
				sw4_counter = 2	// SWEET: Rollin’ Heights Ballas country.
			ENDIF	
		ENDIF
		IF sw4_text_timer_flag = 2
			IF sw4_audio_playing = 0
			AND	sw4_counter = 0
				sw4_counter = 5	// RYDER: Do us a little drive by?
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 6
			IF sw4_audio_playing = 0
			AND	sw4_counter = 0
				sw4_counter = 9	// RYDER: No, you’re our chauffeur for this little gig!
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 7
			IF sw4_audio_playing = 0
			AND	sw4_counter = 0
				sw4_counter = 10 // CARL: Gee, thanks.
				//PRINT ( SWE4_AJ ) 100 1 // CARL: Gee, thanks.
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 8
			IF sw4_audio_playing = 0
				sw4_counter = 11 // RYDER: Just don’t drive like a fool.
			ENDIF
		ENDIF
	ENDIF
	IF sw4_stage = 4
		IF sw4_text_timer_flag = 9
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 12 // SWEET: Alright – Ballas turf, you dogs ready?
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 10
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 13 // CARL: Sure, dude, I’m ready.
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 11
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 14 // SWEET: Carl, Just concentrate on the driving and we’ll take care of the shooting.
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 12
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 15 // RYDER: Listen to the man. Try not to park us up a tree or nothin’.
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 13
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 16 // SWEET: Yeah if the car stops, we’re dead meat.
				IF NOT IS_CHAR_DEAD	sw4_flat_hood[2]
					TASK_PLAY_ANIM sw4_flat_hood[2] gsign3 GHANDS 4.0 FALSE FALSE FALSE FALSE 0
				ENDIF
				IF NOT IS_CHAR_DEAD	sw4_flat_hood[3]
					//TASK_PLAY_ANIM sw4_flat_hood[3] smkcig_prtl GANGS 4.0 FALSE FALSE FALSE FALSE 0
					TASK_PLAY_ANIM sw4_flat_hood[3] drnkbr_prtl GANGS 4.0 FALSE FALSE FALSE FALSE 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF
	IF sw4_stage = 6
		IF sw4_wanted_flag = 0
			IF sw4_text_timer_flag = 14
				PRINT ( SWE4_12 ) 100 1 // You have a wanted level, cops will chase you.
				GET_GAME_TIMER sw4_text_timer_end 
				sw4_text_timer_diff = sw4_text_timer_end - sw4_text_timer_start
				IF sw4_text_timer_diff > 4000
					sw4_text_timer_flag++
					GET_GAME_TIMER sw4_text_timer_start
				ENDIF 
			
			ENDIF	
			IF sw4_text_timer_flag = 15
				PRINT ( SWE4_03 ) 100 1 // Get to the spray shop, you can lose the police there!
				GET_GAME_TIMER sw4_text_timer_end 
				sw4_text_timer_diff = sw4_text_timer_end - sw4_text_timer_start
				IF sw4_text_timer_diff > 4000
					GET_GAME_TIMER sw4_blip_timer_start
					sw4_text_timer_flag++
					sw4_text_timer_flag++
					sw4_text_timer_flag++
					GET_GAME_TIMER sw4_text_timer_start
				ENDIF 
			ENDIF
		ELSE
			IF sw4_text_timer_flag = 14
				IF sw4_audio_playing = 0
				AND sw4_counter = 0
					sw4_counter = 55 // RYDER: CJ, you trying to get us busted?
				ENDIF
			ENDIF	
			IF sw4_text_timer_flag = 15
				IF sw4_audio_playing = 0
				AND sw4_counter = 0
					sw4_counter = 56 // SWEET: C’mon CJ, we gotta lose all this attention!
				ENDIF
			ENDIF
			IF sw4_text_timer_flag = 16
				PRINT ( SWE4_03 ) 4000 1 // Get to the spray shop, you can lose the police there!
				sw4_text_timer_flag++
				GET_GAME_TIMER sw4_text_timer_start
			ENDIF
		ENDIF
	ENDIF
	IF sw4_stage = 7
	AND sw4_help = 4
		GET_GAME_TIMER sw4_text_timer_end 
		sw4_text_timer_diff = sw4_text_timer_end - sw4_text_timer_start
		IF sw4_text_timer_diff > 6000
			IF sw4_text_timer_flag = 18
				IF sw4_audio_playing = 0
				AND sw4_counter = 0
					sw4_counter = 37 // SWEET: Holy fuck, Grove is back, man, Grove is back!
				ENDIF
			ENDIF
		ENDIF																																  
		IF sw4_text_timer_flag = 19
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 38 // CARL: Righteous, dude, they was totally unprepared for us!
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 20
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 39 // RYDER: I’m amazed you didn’t get us killed, CJ.
			ENDIF
		ENDIF
		IF sw4_text_timer_flag = 21
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 40 // RYDER: Yo, check it, am I dead?
			ENDIF
		ENDIF
	   	IF sw4_text_timer_flag = 22
			IF sw4_audio_playing = 0
			AND sw4_counter = 0
				sw4_counter = 41 // SWEET: Hey, Carl, ignore that motherfucker, you did good today.
			ENDIF
		ENDIF
	ENDIF
ENDIF

		
GOTO sw4_main_loop 
// ------------------------------------------------------------------------------------------------
// Mission Failed
mission_failed_sweet4:

PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

// ------------------------------------------------------------------------------------------------
// Mission Passed
mission_passed_sweet4:
//
//START_NEW_SCRIPT crash_mission_loop
//REMOVE_BLIP crash_contact_blip
//ADD_SPRITE_BLIP_FOR_CONTACT_POINT crashX crashY crashZ crash_blip_icon crash_contact_blip
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_FLAT PEDTYPE_PLAYER1

flag_sweet_mission_counter ++
REGISTER_MISSION_PASSED ( SWEET_4 )
PLAYER_MADE_PROGRESS 1
//PRINT_WITH_NUMBER_BIG ( M_PASS ) 200 5000 1 //"Mission Passed!"
//PRINT_NOW ( SW4_T11 ) 5000 1 // Well done.
ADD_SCORE player1 500
AWARD_PLAYER_MISSION_RESPECT 6
PRINT_WITH_NUMBER_BIG ( M_PASSS ) 500 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 1
START_NEW_SCRIPT ryder_mission_loop
REMOVE_BLIP ryder_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT ryderX ryderY ryderZ ryder_blip_icon ryder_contact_blip

CLEAR_WANTED_LEVEL player1

RETURN

// ------------------------------------------------------------------------------------------------
// Mission Cleanup
mission_cleanup_sweet4:

// ---- Entities
	GOSUB sw4_player_car_delete
	GOSUB sw4_flat_hood_delete

	REMOVE_CHAR_ELEGANTLY sw4_sweet
	REMOVE_CHAR_ELEGANTLY sw4_ryder
	REMOVE_CHAR_ELEGANTLY sw4_smoke

	UNLOAD_SPECIAL_CHARACTER 10
	UNLOAD_SPECIAL_CHARACTER 2
	UNLOAD_SPECIAL_CHARACTER 3

	MARK_MODEL_AS_NO_LONGER_NEEDED ballas1
	MARK_MODEL_AS_NO_LONGER_NEEDED ballas2
	MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED VOODOO
	//MARK_MODEL_AS_NO_LONGER_NEEDED 867
	MARK_MODEL_AS_NO_LONGER_NEEDED SPRAYCAN

	REMOVE_ANIMATION GHANDS
	REMOVE_ANIMATION GANGS
		
// ---- Blips
	REMOVE_BLIP	sw4_player_car_blip
	REMOVE_BLIP sw4_hood_blip
	REMOVE_BLIP sw4_end_blip

	REMOVE_BLIP sw4_flat_hood_blip[0]
	REMOVE_BLIP sw4_flat_hood_blip[1]
	REMOVE_BLIP sw4_flat_hood_blip[2]
	REMOVE_BLIP sw4_flat_hood_blip[3]
	REMOVE_BLIP sw4_flat_hood_blip[4]
	REMOVE_BLIP sw4_flat_hood_blip[5]
	REMOVE_BLIP sw4_flat_hood_blip[6]
	REMOVE_BLIP sw4_flat_hood_blip[7]
	REMOVE_BLIP sw4_flat_hood_blip[8]
	REMOVE_BLIP sw4_flat_hood_blip[9]
	REMOVE_BLIP sw4_flat_hood_blip[10]
	REMOVE_BLIP sw4_flat_hood_blip[11]
	REMOVE_BLIP sw4_flat_hood_blip[12]
	REMOVE_BLIP sw4_flat_hood_blip[13]
	REMOVE_BLIP sw4_flat_hood_blip[14]
	REMOVE_BLIP sw4_flat_hood_blip[15]

	REMOVE_BLIP spray_shop1
	REMOVE_BLIP sw4_spray_marker
	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2067.4 -1831.2 13.5 RADAR_SPRITE_SPRAY spray_shop1

	SET_FREE_RESPRAYS OFF
	SET_WANTED_MULTIPLIER 1.0

//SET_ZONE_GANG_STRENGTH IWD1 GANG_FLAT sw4_gang_strength[0]
//
//SET_ZONE_GANG_STRENGTH IWD2 GANG_FLAT sw4_gang_strength[1]
//
//SET_ZONE_GANG_STRENGTH IWD3a GANG_FLAT sw4_gang_strength[2]
//
//SET_ZONE_GANG_STRENGTH IWD3b GANG_FLAT sw4_gang_strength[3]
//
//SET_ZONE_GANG_STRENGTH IWD4 GANG_FLAT sw4_gang_strength[4]
//
//SET_ZONE_GANG_STRENGTH IWD5 GANG_FLAT sw4_gang_strength[5]
//
//SET_ZONE_GANG_STRENGTH JEF1a GANG_FLAT sw4_gang_strength[6]
//
//SET_ZONE_GANG_STRENGTH JEF1b GANG_FLAT sw4_gang_strength[7]
//
//SET_ZONE_GANG_STRENGTH JEF2  GANG_FLAT sw4_gang_strength[8]
//
//SET_ZONE_GANG_STRENGTH JEF3b GANG_FLAT sw4_gang_strength[9]
//
//SET_ZONE_GANG_STRENGTH JEF3c GANG_FLAT sw4_gang_strength[10]
//
//SET_ZONE_GANG_STRENGTH ELS1a GANG_FLAT sw4_gang_strength[11]
//
//SET_ZONE_GANG_STRENGTH ELS1b GANG_FLAT sw4_gang_strength[12]
//
//SET_ZONE_GANG_STRENGTH ELS2	 GANG_FLAT sw4_gang_strength[13]
//
//SET_ZONE_GANG_STRENGTH ELS3a GANG_FLAT sw4_gang_strength[14]
//
//SET_ZONE_GANG_STRENGTH ELS3b GANG_FLAT sw4_gang_strength[15]
//
//SET_ZONE_GANG_STRENGTH ELS4	 GANG_FLAT sw4_gang_strength[16]
//
//SET_ZONE_GANG_STRENGTH GLN1 GANG_FLAT sw4_gang_strength[17]
//
//SET_ZONE_GANG_STRENGTH GLN2a GANG_FLAT sw4_gang_strength[18]
//
//SET_ZONE_GANG_STRENGTH GLN2b GANG_FLAT sw4_gang_strength[19]
//
//SET_ZONE_GANG_STRENGTH LIND1a GANG_FLAT sw4_gang_strength[20]
//
//SET_ZONE_GANG_STRENGTH LIND1b GANG_FLAT sw4_gang_strength[21]
//
//SET_ZONE_GANG_STRENGTH LIND2a GANG_FLAT sw4_gang_strength[22]
//
//SET_ZONE_GANG_STRENGTH LIND2b GANG_FLAT sw4_gang_strength[23]
//
//SET_ZONE_GANG_STRENGTH LIND3 GANG_FLAT sw4_gang_strength[24]


	
	SET_MAX_WANTED_LEVEL sw4_max_wanted
	SET_CREATE_RANDOM_COPS TRUE
	SWITCH_COPS_ON_BIKES ON

	SWITCH_CAR_GENERATOR gen_car7 101
	DONT_SUPPRESS_CAR_MODEL VOODOO

	
	REMOVE_ANIMATION GHETTO_DB

	CLEAR_ONSCREEN_COUNTER sw4_health_display

	SET_MUSIC_DOES_FADE TRUE
	SET_CREATE_RANDOM_GANG_MEMBERS TRUE
// ---- Models

// ----	Clear Script Stuff
		flag_player_on_mission = 0
		GET_GAME_TIMER timer_mobile_start
		MISSION_HAS_FINISHED
RETURN
// ------------------------------------------------------------------------------------------------

// ---- Subroutines				    

sw4_shoot_at_closest:
	IF NOT IS_CHAR_DEAD sw4_gosub_char
		GET_CHAR_COORDINATES sw4_gosub_char sw4_gosub_x sw4_gosub_y sw4_gosub_z
	ENDIF
	sw4_closest_ped = -1 
	sw4_closest_ped_dist = 99999.9
	sw4_loop = 0
	WHILE sw4_loop < 18
		IF NOT IS_CHAR_DEAD sw4_flat_hood[sw4_loop]
			GET_CHAR_COORDINATES sw4_flat_hood[sw4_loop] sw4_flat_hood_x[sw4_loop] sw4_flat_hood_y[sw4_loop] sw4_flat_hood_z[sw4_loop]
			GET_DISTANCE_BETWEEN_COORDS_2D sw4_gosub_x sw4_gosub_y sw4_flat_hood_x[sw4_loop] sw4_flat_hood_y[sw4_loop] sw4_gosub_dist
			IF sw4_gosub_dist < sw4_closest_ped_dist
				sw4_closest_ped_dist = sw4_gosub_dist
				sw4_closest_ped = sw4_flat_hood[sw4_loop]
			ENDIF
		ENDIF
		sw4_loop++
	ENDWHILE 

	IF sw4_closest_ped >= 0
		IF NOT IS_CHAR_DEAD sw4_closest_ped  
		AND NOT IS_CHAR_DEAD sw4_gosub_char
			TASK_DRIVE_BY sw4_gosub_char sw4_closest_ped -1 0.0 0.0 0.0 40.0 sw4_anim sw4_side 99
			SET_CURRENT_CHAR_WEAPON sw4_gosub_char WEAPONTYPE_TEC9
		ENDIF
	ENDIF

	GET_GAME_TIMER sw4_timer_start[3]
RETURN

sw4_dialogue_pos:
	IF NOT sw4_counter > 11
	AND NOT sw4_counter < 17
		sw4_audio_char = 0
	ENDIF
	IF NOT sw4_counter > 41
	AND NOT sw4_counter < 46
		sw4_audio_char = 0
	ENDIF
	IF sw4_counter = 12
	OR sw4_counter = 14
	OR sw4_counter = 16
	OR sw4_counter = 42
	OR sw4_counter = 43	
		sw4_audio_char = sw4_sweet
	ENDIF
	IF sw4_counter = 13
	OR sw4_counter = 44
		sw4_audio_char = scplayer
	ENDIF
	IF sw4_counter = 15
		sw4_audio_char = sw4_ryder
	ENDIF
	IF sw4_counter = 45
		sw4_audio_char = sw4_smoke
	ENDIF	

RETURN

}

/*

[SWE4_01:SWEET4]
~s~Drive your homies into ~y~Balla territory~s~.

[SWE4_02:SWEET4]
~s~Shoot up the neighborhood.

[SWE4_03:SWEET4]
~s~Get to the spray shop, you can lose the police there!

[SWE4_04:SWEET4] 
~r~Your homies didn't make it.

[SWE4_05:SWEET4] 
~r~You sure you're down for the 'hood?

[SWE4_06:SWEET4]
Drive your vehicle into the spray shop to lose your ~h~wanted level~w~, ~h~repair ~w~and ~h~respray ~w~your vehicle. Cost - ~h~$100~w~. This time it's free.

[SWE4_07:SWEET4]
The spray shop icon is marked on the radar as a spray can.

[SWE4_08:SWEET4]
CAR HEALTH

[SWE4_09:SWEET4]
~s~You've left ~b~Smoke ~s~behind!

[SWE4_10:SWEET4]
~s~You've left ~b~Ryder ~s~behind!

[SWE4_11:SWEET4]
~s~You've left ~b~Sweet ~s~behind!

[SWE4_12:SWEET4]
~s~You have a two star wanted level, the cops will chase you down.

[SWE4_13:SWEET4]
Press ~M~ or ~V~ to look left or right when in a car.

[SWE4_14:SWEET4]
~s~Take your homies back to ~y~the hood~s~.

[SWE4_15:SWEET4]
This will allow you to see where your enemies are located.




[SWE4_AA:SWEET4]
Where we going, homie?

[SWE4_AB:SWEET4]	
Rollin' Heights Ballas country.

[SWE4_AE:SWEET4]	
Do us a little drive by?

[SWE4_AF:SWEET4]	
For real. You down, Carl?

[SWE4_AG:SWEET4]	
I ain't packing any heat.

[SWE4_AH:SWEET4]	
You ain't cold enough for that shit yet, CJ.

[SWE4_AI:SWEET4]	
No, you're our chauffeur for this little gig!

[SWE4_AJ:SWEET4]	
Gee, thanks.

[SWE4_AK:SWEET4]	
Just don't drive like a fool.

[SWE4_BA:SWEET4]	
Alright - Ballas turf, you dogs ready?

[SWE4_BB:SWEET4]	
Sure, dude, I'm ready.

[SWE4_BC:SWEET4]	
Carl, just concentrate on the driving and we'll take care of the shooting.

[SWE4_BD:SWEET4]	
Listen to the man. Try not to park us up a tree or nothin'.

[SWE4_BE:SWEET4]	
Yeah if the car stops, we're dead meat.

[SWE4_DA:SWEET4]	
Holy shit we're sitting ducks!

[SWE4_DB:SWEET4]	
Oh man, oh man, move! MOVE!

[SWE4_DC:SWEET4]	
What you doing, CJ?!

[SWE4_DD:SWEET4]	
Get us moving!

[SWE4_DE:SWEET4]	
He's trying to get us killed!

[SWE4_DF:SWEET4]	
Move it, CJ, move it!

[SWE4_EA:SWEET4]	
I told you he was a buster!

[SWE4_EB:SWEET4]	
Don't run out on me again, Carl!

[SWE4_EC:SWEET4]	
Carl's quitting on us!

[SWE4_FA:SWEET4]	
He's ditched us!
	
[SWE4_FD:SWEET4]	
Motherfucker! I've got the wheel! 

[SWE4_GA:SWEET4]	
Go Carl, GO!!

[SWE4_GB:SWEET4]	
These wheels are hot - every cop in South Central will be looking for this car!

[SWE4_KA:SWEET4]	
Holy fuck, Grove is back, man, Grove is back!

[SWE4_KB:SWEET4]	
Righteous, dude, they was totally unprepared for us!

[SWE4_KC:SWEET4]	
I'm amazed you didn't get us killed, CJ.

[SWE4_KD:SWEET4]	
Yo, check it, am I dead?

[SWE4_KE:SWEET4]	
I'm cool, dude. Look, I'll see you cats later.

[SWE4_KF:SWEET4]	
Hey, Carl, ignore that motherfucker, you did good today.

[SWE4_KG:SWEET4]	
You're down with the Grove and those Ballas know it,

[SWE4_KH:SWEET4]	
so watch yourself from now on.

[SWE4_KI:SWEET4]	
Here, go get yourself a beer and some colors.

[SWE4_LA:SWEET4]	
We iced them! CJ, let's hunt us down some more!

[SWE4_LB:SWEET4]	
They was too easy, let's find us some more Ballas fools!

[SWE4_LC:SWEET4]	
What you waiting for, CJ? Find us some more Ballas fools to cap!
	
[SWE4_NC:SWEET4]	
CJ, you trying to get us busted?

[SWE4_OA:SWEET4]	
C'mon CJ, we gotta lose all this attention!


*/
