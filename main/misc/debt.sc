MISSION_START
// *****************************************************************************************
// ***																					 ***
// *** Date: 27/07/2004 	Time: 18:18:23	   Author:  Chris Rothwell 					 ***
// ***																					 ***
// *** Vegas Debt Collectors			 												 ***
// ***																					 ***
// *****************************************************************************************

{
debt_collectors:

SCRIPT_NAME DEBT

lVAR_INT flag
flag = 0

lvar_int debt_level
debt_level = 0

lvar_int vincent_requested vwmycr_requested vbmycr_requested vwmycd_requested hmycr_requested micro_uzi_requested ak47_requested 
vincent_requested = 0
vwmycr_requested = 0
vbmycr_requested = 0
vwmycd_requested = 0
hmycr_requested = 0
micro_uzi_requested = 0
ak47_requested = 0

GET_GAME_TIMER game_timer
lvar_int respawn_timer
respawn_timer = game_timer + 50//120000

lvar_int car_mission_timer
car_mission_timer = game_timer - 10

debt_collectors_loop:

WAIT 250

GET_GAME_TIMER game_timer

if flag = 0
	if flag_player_on_mission = 0
		if respawn_timer < game_timer
			if IS_PLAYER_PLAYING player1
				lvar_int temp_int
				GET_AREA_VISIBLE temp_int
				if temp_int = 0
					if not IS_WANTED_LEVEL_GREATER player1 1
						if not is_char_in_flying_vehicle scplayer
							if im_players_city = LEVEL_LASVEGAS//is_char_in_area_2d scplayer 895.3812 598.1167 2917.1340 2830.4780 0 //is player1 in vegas

								STORE_SCORE player1 temp_int
								if temp_int > -1
								endif
								if temp_int < -5000
									debt_level = 1
								endif
								if temp_int < -50000
									debt_level = 2
								endif
								if temp_int < -500000
									debt_level = 3
								endif
								++ flag
							else
								if respawn_timer < game_timer
									respawn_timer = game_timer + 120000
								endif
							endif
						else
							if respawn_timer < game_timer
								respawn_timer = game_timer + 60000
							endif
						endif
					else
						if respawn_timer < game_timer
							respawn_timer = game_timer + 60000
						endif
					endif
				else
					if respawn_timer < game_timer
						respawn_timer = game_timer + 60000
					endif
				endif
			else
				if respawn_timer < game_timer
					respawn_timer = game_timer + 120000
				endif
			endif
		endif
	else
		if respawn_timer < game_timer
			respawn_timer = game_timer + 120000
		endif
	endif
endif

if flag = 1
	get_gen_coords:
	get_game_timer game_timer
	if is_player_playing player1
		GET_CHAR_COORDINATES scplayer player_x player_y z
		x = player_x - 200.0
		player_x += 200.0
		GENERATE_RANDOM_FLOAT_IN_RANGE x player_x x
		y = player_y - 200.0
		player_y += 200.0
		GENERATE_RANDOM_FLOAT_IN_RANGE y player_y y
					
		GET_CLOSEST_CAR_NODE_WITH_HEADING x y z x y z heading

		GET_CHAR_COORDINATES scplayer player_x player_y player_z
		GET_DISTANCE_BETWEEN_COORDS_2D player_x player_y x y distance

		IF z < 1.0
			WAIT 0
			GOTO get_gen_coords
		ENDIF

		IF distance < 50.0
			WAIT 0
			GOTO get_gen_coords
		ENDIF

		if is_point_on_screen x y z 4.0
			WAIT 0
			GOTO get_gen_coords
		endif
		++ flag
	else
		gosub cleaup_debt_collectors
		respawn_timer = game_timer + 120000
		flag = 0
	endif
endif
			
if flag = 2
	if has_model_loaded	VINCENT
	and has_model_loaded VWMYCR
	and has_model_loaded VBMYCR
	and has_model_loaded VWMYCD
	and has_model_loaded HMYCR
	and has_model_loaded micro_uzi
		if has_model_loaded	ak47
			lvar_int collector_car
			CREATE_CAR VINCENT x y z collector_car
			SET_CAR_HEADING collector_car heading
			change_car_colour collector_car 75 0
			SET_CAR_HEALTH collector_car 1500
			SET_CAR_DRIVING_STYLE collector_car 2
			ADD_STUCK_CAR_CHECK_WITH_WARP collector_car 4.0 4000 true true true -1
			if vincent_requested = 1
				mark_model_as_no_longer_needed VINCENT
				vincent_requested = 0
			endif

			lvar_int debt_collector[4]
			CREATE_CHAR_INSIDE_CAR collector_car PEDTYPE_MISSION1 VWMYCR debt_collector[0]
			//lvar_int tempy_tempy_blip[4]
			//add_blip_for_char debt_collector[0]	tempy_tempy_blip[0]
			if vwmycr_requested = 1
				mark_model_as_no_longer_needed VWMYCR
				vwmycr_requested = 0
			endif

			if debt_level > 0
				lvar_int collector_group
				CREATE_GROUP DEFAULT_TASK_ALLOCATOR_FOLLOW_ANY_MEANS collector_group
				SET_GROUP_LEADER collector_group debt_collector[0]
				
				lvar_int collector_group_decisions
				COPY_group_DECISION_MAKER -1 collector_group_decisions
				CLEAR_group_DECISION_MAKER_EVENT_RESPONSE collector_group_decisions EVENT_VEHICLE_DAMAGE_WEAPON
				
				SET_GROUP_DECISION_MAKER collector_group collector_group_decisions

				CREATE_CHAR_AS_PASSENGER collector_car PEDTYPE_MISSION1 VBMYCR 0 debt_collector[1]
				//add_blip_for_char debt_collector[1]	tempy_tempy_blip[1]
				if vbmycr_requested = 1
					mark_model_as_no_longer_needed VBMYCR
					vbmycr_requested = 0
				endif
				if debt_level > 1
					CREATE_CHAR_AS_PASSENGER collector_car PEDTYPE_MISSION1 VWMYCD 1 debt_collector[2]
					//add_blip_for_char debt_collector[2]	tempy_tempy_blip[2]
					if vwmycd_requested = 1
						mark_model_as_no_longer_needed VWMYCD
						vwmycd_requested = 0
					endif
				endif
				if debt_level > 2
					CREATE_CHAR_AS_PASSENGER collector_car PEDTYPE_MISSION1 HMYCR 2 debt_collector[3]
					//add_blip_for_char debt_collector[3]	tempy_tempy_blip[3]
					if hmycr_requested = 1
						mark_model_as_no_longer_needed HMYCR
						hmycr_requested = 0
					endif
				endif
			endif

			lvar_int collector_decisions
			COPY_CHAR_DECISION_MAKER -1 collector_decisions
			CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE collector_decisions EVENT_VEHICLE_DAMAGE_WEAPON

			an = 0
			while an < 4
				if not is_char_dead	debt_collector[an]
					SET_CHAR_DECISION_MAKER debt_collector[an] collector_decisions
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER debt_collector[an] TRUE
					SET_CHAR_RELATIONSHIP debt_collector[an] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
					set_char_money debt_collector[an] 10000
					set_char_keep_task debt_collector[an] true
					IF an = 0
					or an = 2
						GIVE_WEAPON_TO_CHAR debt_collector[an] WEAPONTYPE_MICRO_UZI 9999
					ENDIF

					IF an = 1
					or an = 3
						GIVE_WEAPON_TO_CHAR debt_collector[an] WEAPONTYPE_AK47 9999
					ENDIF
				endif
				++ an
			endwhile
			if micro_uzi_requested = 1
				mark_model_as_no_longer_needed micro_uzi
				micro_uzi_requested = 0
			endif
			if ak47_requested = 1
				mark_model_as_no_longer_needed ak47
				ak47_requested = 0
			endif
			++ flag
		else
			if not has_model_loaded	ak47
				request_model ak47
				ak47_requested = 1
			endif
		endif
	else
		if not has_model_loaded	VINCENT
			request_model VINCENT
			vincent_requested = 1
		endif
		if not has_model_loaded VWMYCR
			request_model VWMYCR
			vwmycr_requested = 1
		endif
		if not has_model_loaded VBMYCR
			request_model VBMYCR
			vbmycr_requested = 1
		endif
		if not has_model_loaded VWMYCD
			request_model VWMYCD
			vwmycd_requested = 1
		endif
		if not has_model_loaded HMYCR
			request_model HMYCR
			hmycr_requested = 1
		endif
		if not has_model_loaded micro_uzi
			request_model micro_uzi
			micro_uzi_requested = 1
		endif
		if not has_model_loaded	ak47
			request_model ak47
			ak47_requested = 1
		endif
	endif
endif

if flag = 3
	if flag_player_on_mission = 0
		if IS_PLAYER_PLAYING player1
			if not is_score_greater player1 -1
				GET_AREA_VISIBLE temp_int
				if temp_int = 0
					if not is_char_dead	debt_collector[0]
						if locate_char_any_means_char_2d scplayer debt_collector[0] 500.0 500.0 0
							IF IS_CHAR_SITTING_IN_ANY_CAR debt_collector[0]
								if not is_car_dead collector_car
									if car_mission_timer < game_timer
										if is_char_in_any_car scplayer
											generate_random_int_in_range 0 4 temp_int
											store_car_char_is_in_no_save scplayer car
											if temp_int = 0
												TASK_CAR_MISSION debt_collector[0] collector_car car MISSION_RAMCAR_FARAWAY 100.0 2
											endif
											if temp_int = 1
												TASK_CAR_MISSION debt_collector[0] collector_car car MISSION_BLOCKCAR_HANDBRAKESTOP 100.0 2
											endif
											if temp_int = 2
												TASK_CAR_MISSION debt_collector[0] collector_car car MISSION_ESCORT_LEFT 100.0 2
											endif
											if temp_int = 3
												TASK_CAR_MISSION debt_collector[0] collector_car car MISSION_ESCORT_RIGHT 100.0 2
											endif
										else
											TASK_CAR_MISSION debt_collector[0] collector_car -1 MISSION_RAMPLAYER_FARAWAY 100.0 2
										endif
										car_mission_timer = game_timer + 8000
									endif
										
									get_char_speed scplayer	distance
									if is_char_on_foot scplayer
									or distance < 5.0
										if locate_char_any_means_char_2d debt_collector[0] scplayer 15.0 15.0 0
											get_script_task_status debt_collector[0] PERFORM_SEQUENCE_TASK task_status
											if task_status = finished_task
												lvar_int sequence_task
												OPEN_SEQUENCE_TASK sequence_task
													generate_random_int_in_range 0 2 temp_int
													if temp_int = 0
														TASK_CAR_TEMP_ACTION -1 collector_car TEMPACT_TURNLEFT 200
														TASK_CAR_TEMP_ACTION -1 collector_car TEMPACT_HANDBRAKETURNLEFT 1000
													else
														TASK_CAR_TEMP_ACTION -1 collector_car TEMPACT_TURNRIGHT 200
														TASK_CAR_TEMP_ACTION -1 collector_car TEMPACT_HANDBRAKETURNRIGHT 1000
													endif
													TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 5000
												CLOSE_SEQUENCE_TASK sequence_task
												PERFORM_SEQUENCE_TASK debt_collector[0] sequence_task
												CLEAR_SEQUENCE_TASK sequence_task
											endif
										endif
									endif
									if is_car_stuck	collector_car
									and distance < 15.0
										if locate_char_any_means_car_2d scplayer collector_car 30.0 30.0 0
											if is_car_on_screen collector_car
												get_script_task_status debt_collector[0] TASK_KILL_CHAR_ON_FOOT_TIMED task_status
												if task_status = finished_task
													TASK_KILL_CHAR_ON_FOOT_TIMED debt_collector[0] scplayer 5000
												endif
											endif
										endif
									endif
								else
									mark_car_as_no_longer_needed collector_car
								endif
							else
								if locate_char_any_means_char_2d debt_collector[0] scplayer 30.0 30.0 0
									get_script_task_status debt_collector[0] TASK_KILL_CHAR_ON_FOOT_TIMED task_status
									if task_status = finished_task
										TASK_KILL_CHAR_ON_FOOT_TIMED debt_collector[0] scplayer 5000
									endif
								else
									if not is_car_dead collector_car
										get_script_task_status debt_collector[0] perform_sequence_task task_status
										if task_status = finished_task
											open_sequence_task sequence_task
												TASK_GOTO_CAR -1 collector_car 99999 5.0
												TASK_enter_car_as_driver -1 collector_car -2
											close_sequence_task	sequence_task
											perform_sequence_task debt_collector[0]	sequence_task
											clear_sequence_task	sequence_task
										endif
									else
										mark_car_as_no_longer_needed collector_car
										if not locate_char_any_means_char_2d scplayer debt_collector[0] 60.0 60.0 0
											IF NOT IS_CAR_ON_SCREEN collector_car
												get_char_coordinates debt_collector[0] x y z
												GET_CLOSEST_CAR_NODE_WITH_HEADING x y z x y z heading
												IF NOT IS_POINT_OBSCURED_BY_A_MISSION_ENTITY x y z 4.0 4.0 4.0
													IF NOT IS_POINT_ON_SCREEN x y z 4.0
														CREATE_CAR VINCENT x y z collector_car
														SET_CAR_HEADING collector_car heading
														change_car_colour collector_car 75 0
														SET_CAR_HEALTH collector_car 1500
														SET_CAR_DRIVING_STYLE collector_car 2
														ADD_STUCK_CAR_CHECK_WITH_WARP collector_car 4.0 4000 true true true -1
													endif
												endif
											endif
										else
											get_script_task_status debt_collector[0] TASK_KILL_CHAR_ON_FOOT_TIMED task_status
											if task_status = finished_task
												TASK_KILL_CHAR_ON_FOOT_TIMED debt_collector[0] scplayer 5000
											endif
										endif
									endif
								endif
							endif
						else
							gosub cleaup_debt_collectors
							respawn_timer = game_timer + 60000
							flag = 0
						endif
					else
						MARK_CHAR_AS_NO_LONGER_NEEDED debt_collector[0]
						an = 1
						while an < 4
							IF NOT IS_CHAR_DEAD	debt_collector[an]
								if does_group_exist	collector_group
									IF IS_GROUP_MEMBER debt_collector[an] collector_group
										IF IS_GROUP_LEADER debt_collector[an] collector_group
											debt_collector[0] = debt_collector[an]
											debt_collector[an] = -1
										ENDIF
									ENDIF
								endif
							ELSE
								//remove_blip	tempy_tempy_blip[an]
								MARK_CHAR_AS_NO_LONGER_NEEDED debt_collector[an]
							ENDIF
							++ an
						endwhile
					endif
					an = 1
					while an < 4
						if not is_char_dead debt_collector[0]
							IF NOT IS_CHAR_DEAD	debt_collector[an]
								if does_group_exist	collector_group
									IF NOT IS_GROUP_MEMBER debt_collector[an] collector_group
										IF LOCATE_CHAR_ANY_MEANS_CHAR_2D debt_collector[an] debt_collector[0] 30.0 30.0 0
											SET_GROUP_MEMBER collector_group debt_collector[an]
										ELSE
											GET_SCRIPT_TASK_STATUS debt_collector[an] TASK_KILL_CHAR_ON_FOOT task_status
											IF task_status = FINISHED_TASK
												mark_char_as_no_longer_needed debt_collector[an]
												TASK_KILL_CHAR_ON_FOOT_TIMED debt_collector[an] scplayer 5000
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ELSE
								//remove_blip	tempy_tempy_blip[an]
								MARK_CHAR_AS_NO_LONGER_NEEDED debt_collector[an]
							ENDIF
						ENDIF
						++ an
					endwhile
				else
					gosub cleaup_debt_collectors
					respawn_timer = game_timer + 60000
					flag = 0
				endif
			else
				gosub cleaup_debt_collectors
				flag_mob_random[10] = 0
				TERMINATE_THIS_SCRIPT
			endif
		else
			gosub cleaup_debt_collectors
			respawn_timer = game_timer + 120000
			flag = 0
		endif
	else
		gosub cleaup_debt_collectors
		respawn_timer = game_timer + 120000
		flag = 0
	endif

	if is_char_dead	debt_collector[0]
	and	is_char_dead debt_collector[1]
	and	is_char_dead debt_collector[2]
	and	is_char_dead debt_collector[3]
		gosub cleaup_debt_collectors
		respawn_timer = game_timer + 120000
		flag = 0
	endif
endif

GOTO debt_collectors_loop

cleaup_debt_collectors:
//	remove_blip tempy_tempy_blip[0]
//	remove_blip tempy_tempy_blip[1]
//	remove_blip tempy_tempy_blip[2]
//	remove_blip tempy_tempy_blip[3]
	mark_char_as_no_longer_needed debt_collector[0]
	mark_char_as_no_longer_needed debt_collector[1]
	mark_char_as_no_longer_needed debt_collector[2]
	mark_char_as_no_longer_needed debt_collector[3]
	mark_car_as_no_longer_needed collector_car
	remove_decision_maker collector_decisions
	remove_decision_maker collector_group_decisions
	remove_group collector_group
	if vincent_requested = 1
		mark_model_as_no_longer_needed VINCENT
		vincent_requested = 0
	endif
	if vwmycr_requested = 1
		mark_model_as_no_longer_needed VWMYCR
		vwmycr_requested = 0
	endif
	if vbmycr_requested = 1
		mark_model_as_no_longer_needed VBMYCR
		vbmycr_requested = 0
	endif
	if vwmycd_requested = 1
		mark_model_as_no_longer_needed VWMYCD
		vwmycd_requested = 0
	endif
	if hmycr_requested = 1
		mark_model_as_no_longer_needed HMYCR
		hmycr_requested = 0
	endif
	if micro_uzi_requested = 1
		mark_model_as_no_longer_needed micro_uzi
		micro_uzi_requested = 0
	endif
	if ak47_requested = 1
		mark_model_as_no_longer_needed ak47
		ak47_requested = 0
	endif
return

MISSION_END
}
