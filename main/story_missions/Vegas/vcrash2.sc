MISSION_START
// ****************************************************************************************
// ***																					***
// *** Date: 14/06/2004 	Time: 10:44:44	   Author:  Chris Rothwell 					***
// ***																					***
// *** Mission: Vegas Crash Mission - Kill Pulaski										***
// ***																					***
// *** Brief: Pulaski escapes in a car, the player has to hunt him down and kill him.	***
// *** If the player takes too long, then Pulaski will escape.							***
// ***																					***
// ****************************************************************************************

GOSUB mission_vcr2_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_vcr2_failed
ENDIF

GOSUB mission_vcr2_cleanup

MISSION_END
 
// ************************************ MISSION START *************************************
{
mission_vcr2_start:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME vcr2

WAIT 0

LVAR_INT mission_blip mission_timer temp_int sequence_task
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 temp_float speed

lvar_int mission_flag
//view_integer_variable mission_flag mission_flag

LOAD_MISSION_TEXT VCR2

MAKE_PLAYER_GANG_DISAPPEAR



LOAD_CUTSCENE CRASV2A

WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
clear_area -359.5421 2225.5249 42.4562 30.9000 0
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


if not WAS_CUTSCENE_SKIPPED

LOAD_CUTSCENE CRASV2B

WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_DESERT
clear_area -359.5421 2225.5249 42.4562 30.9000 0
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

endif

load_special_character 1 pulaski
request_model bandito
request_model buffalo
request_model desert_eagle
load_all_models_now
while not has_model_loaded bandito
or not has_model_loaded buffalo
or not has_model_loaded desert_eagle
or not has_special_character_loaded 1
	wait 0
endwhile

lvar_text_label	$pulaski_chat[20]
$pulaski_chat[0] =  VCR2_AA	//CARL:		Hey Eddie!  You know Tenpenny gonna rub you out too!
$pulaski_chat[1] =  VCR2_AB	//PULASKI:	Carl, you've got it all wrong, pal.
$pulaski_chat[2] =  VCR2_AC	//PULASKI:	Tenpenny was the one saving your ass.
$pulaski_chat[3] =  VCR2_AD	//PULASKI:	Everytime I wanted to get rid of you, he'd find some excuse to keep you alive.
$pulaski_chat[4] =  VCR2_AE	//PULASKI:	Well, now it's up to me, and I ain't such a fucking soft touch!
				    
$pulaski_chat[5] =  VCR2_BA	//PULASKI:	So, how's your sister?
$pulaski_chat[6] =  VCR2_BB	//PULASKI:	I always had a thing for her.
$pulaski_chat[7] =  VCR2_BC	//PULASKI:	Tenpenny and I got some great shots of her and her varrio boyfriend 
//$pulaski_chat[8] =  VCR2_BD	//PULASKI:	going at it like two horny chihuahuas.
$pulaski_chat[8] =  VCR2_BE	//PULASKI:	Man, does she put out like a pro!
$pulaski_chat[9] = VCR2_BF	//CARL:		Screw you Pulaski!
$pulaski_chat[10] = VCR2_BG	//CARL:		I know what you're trying to do, and you ain't gonna crawl inside my head.
$pulaski_chat[11] = VCR2_BH //PULASKI:	I ain't kidding!
$pulaski_chat[12] = VCR2_BJ	//PULASKI:	The best stake-out I ever done.
			  
$pulaski_chat[13] = VCR2_CA	//PULASKI:	Carl, you should have seen your moms before she was buried.
$pulaski_chat[14] = VCR2_CB	//PULASKI:	She was a real mess - most her face was hanging off!
$pulaski_chat[15] = VCR2_CC	//CARL:		Don't fucking talk about my Moms!
$pulaski_chat[16] = VCR2_CD	//PULASKI:	The boys fooled around with her at the scene
$pulaski_chat[17] = VCR2_CE	//PULASKI:	You know, touching her, joking around and shit.
$pulaski_chat[18] = VCR2_CF	//CARL:		Fuck you, Pulaski, FUCK YOU!
$pulaski_chat[19] = VCR2_CG	//PULASKI:	HEH heh heh!

lvar_int pulaski_chat_sound[21]
pulaski_chat_sound[0] =  SOUND_VCR2_AA	//CARL:		Hey Eddie!  You know Tenpenny gonna rub you out too!
pulaski_chat_sound[1] =  SOUND_VCR2_AB	//PULASKI:	Carl, you've got it all wrong, pal.
pulaski_chat_sound[2] =  SOUND_VCR2_AC	//PULASKI:	Tenpenny was the one saving your ass.
pulaski_chat_sound[3] =  SOUND_VCR2_AD	//PULASKI:	Everytime I wanted to get rid of you, he'd find some excuse to keep you alive.
pulaski_chat_sound[4] =  SOUND_VCR2_AE	//PULASKI:	Well, now it's up to me, and I ain't such a fucking soft touch!
				    
pulaski_chat_sound[5] =  SOUND_VCR2_BA	//PULASKI:	So, how's your sister?
pulaski_chat_sound[6] =  SOUND_VCR2_BB	//PULASKI:	I always had a thing for her.
pulaski_chat_sound[7] =  SOUND_VCR2_BC	//PULASKI:	Tenpenny and I got some great shots of her and her varrio boyfriend 
//pulaski_chat_sound[8] =  SOUND_VCR2_BD	//PULASKI:	going at it like two horny chihuahuas.
pulaski_chat_sound[8] =  SOUND_VCR2_BE	//PULASKI:	Man, does she put out like a pro!
pulaski_chat_sound[9] = SOUND_VCR2_BF	//CARL:		Screw you Pulaski!
pulaski_chat_sound[10] = SOUND_VCR2_BG	//CARL:		I know what you're trying to do, and you ain't gonna crawl inside my head.
pulaski_chat_sound[11] = SOUND_VCR2_BH	//PULASKI:	I ain't kidding!
pulaski_chat_sound[12] = SOUND_VCR2_BJ	//PULASKI:	The best stake-out I ever done.

pulaski_chat_sound[13] = SOUND_VCR2_CA	//PULASKI:	Carl, you should have seen your moms before she was buried.
pulaski_chat_sound[14] = SOUND_VCR2_CB	//PULASKI:	She was a real mess - most her face was hanging off!
pulaski_chat_sound[15] = SOUND_VCR2_CC	//CARL:		Don't fucking talk about my Moms!
pulaski_chat_sound[16] = SOUND_VCR2_CD	//PULASKI:	The boys fooled around with her at the scene
pulaski_chat_sound[17] = SOUND_VCR2_CE	//PULASKI:	You know, touching her, joking around and shit.
pulaski_chat_sound[18] = SOUND_VCR2_CF	//CARL:		Fuck you, Pulaski, FUCK YOU!
pulaski_chat_sound[19] = SOUND_VCR2_CG	//PULASKI:	HEH heh heh!

lvar_int convo_counter audio_slot convo_timer
convo_counter = 0
audio_slot = 1
get_game_timer game_timer
convo_timer = game_timer + 6000

lvar_int pulaski_car
CUSTOM_PLATE_FOR_NEXT_CAR buffalo PULASKI
create_car buffalo -371.0292 2209.8196 41.4921  pulaski_car//-365.6242 2215.0977 41.4989 pulaski_car
set_car_heading pulaski_car 159.2268//78.3353
change_car_colour pulaski_car CARCOLOUR_LIGHTBLUEGREY CARCOLOUR_BLACK
SET_PETROL_TANK_WEAKPOINT pulaski_car FALSE
set_car_only_damaged_by_player pulaski_car true
set_car_can_go_against_traffic pulaski_car TRUE
set_car_proofs pulaski_car TRUE TRUE TRUE TRUE TRUE
set_car_health pulaski_car 2250

lvar_int pulaski
create_char pedtype_mission1 special01 -335.4141 2218.9417 41.4921 pulaski
SET_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE pedtype_mission1
set_char_heading pulaski 104.1080
set_char_only_damaged_by_player	pulaski true
set_char_max_health	pulaski 500
set_char_health	pulaski 500
set_char_suffers_critical_hits pulaski false
SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY pulaski TRUE
//SET_CHAR_SHOOT_RATE pulaski 65
set_char_accuracy pulaski 80
SET_CHAR_PROOFS pulaski true True True false false
open_sequence_task sequence_task
	task_go_straight_to_coord -1 -346.4254 2216.4275 41.4921 pedmove_run -2
	task_enter_car_as_driver -1 pulaski_car -2
	task_car_drive_to_coord -1 pulaski_car -472.7780 1958.1945 83.5515 40.0 0 true 2
close_sequence_task	sequence_task
perform_sequence_task pulaski sequence_task
clear_sequence_task	sequence_task

set_player_control player1 off
hide_char_weapon_for_scripted_cutscene scplayer true
set_everyone_ignore_player player1 true

wait 0
switch_widescreen on
set_all_cars_can_be_damaged false
set_fixed_camera_position -341.3325 2213.1096 42.8370 0.0 0.0 0.0
point_camera_at_point -340.7180 2213.8984 42.8258 jump_cut

//load_scene -336.5103 2216.6953 42.6843
LOAD_SCENE_IN_DIRECTION	-341.3325 2213.1096 42.8370 327.4725

clear_char_tasks_immediately scplayer
clear_area -334.1724 2214.6199 40.4911 30.0 0
load_scene -334.1724 2214.6199 40.4911
set_char_coordinates scplayer -334.1724 2214.6199 40.4911
set_char_heading scplayer 232.0820
set_camera_behind_player

lvar_int players_bandito
create_car bandito -342.2552 2221.3904 41.4911 players_bandito
set_car_heading players_bandito 168.7771

do_fade 500 fade_in
get_game_timer game_timer
mission_timer = game_timer + 1500

mission_flag = 0

lvar_int done_this_bit
done_this_bit = 0

release_weather

// ************************************* MISSION LOOP *************************************
mission_vcr2_loop:

WAIT 0

get_game_timer game_timer

if is_ps2_keyboard_key_pressed ps2_key_s
	goto mission_vcr2_passed  
endif

if is_char_dead	pulaski
	if mission_flag < 9
		mission_timer = game_timer + 2000
		mission_flag = 9
	endif
endif

if mission_flag = 0
	if mission_timer < game_timer
		task_go_straight_to_coord scplayer -337.9653 2218.5942 41.4911 pedmove_run 2000
//		set_fixed_camera_position -331.9285 2217.6946 43.3619 0.0 0.0 0.0
//		point_camera_at_point -332.8746 2217.9761 43.2020 jump_cut
//		open_sequence_task sequence_task
//			task_achieve_heading -1 100.0
//			task_look_at_char -1 pulaski 1000
//			task_shake_fist -1
//		close_sequence_task	sequence_task
//		perform_sequence_task scplayer sequence_task
//		clear_sequence_task	sequence_task
		mission_timer = game_timer + 1500
		++ mission_flag
	endif
endif

if mission_flag = 1
	if mission_timer < game_timer
		set_char_coordinates scplayer -337.9653 2218.5942 41.4911
		set_char_heading scplayer 90.0
		set_char_coordinates pulaski -359.3949 2213.4043 41.4911
		set_char_heading pulaski 90.0000
		set_player_control player1 on
		hide_char_weapon_for_scripted_cutscene scplayer false
		set_everyone_ignore_player player1 false
		switch_widescreen off
		MAKE_PLAYER_GANG_REAPPEAR
		set_all_cars_can_be_damaged true
		set_camera_behind_player
		restore_camera_jumpcut
		add_blip_for_char pulaski mission_blip
		print_now VCR2__1 15000 1//Kill pulaski!
		mission_timer = game_timer + 26000
		++ mission_flag
	endif
endif

if mission_flag = 2
	if mission_timer < game_timer
		TASK_CAR_DRIVE_WANDER pulaski -1 40.0 2
		give_weapon_to_char	pulaski weapontype_desert_eagle 9999
		SET_CHAR_RELATIONSHIP pulaski ACQUAINTANCE_TYPE_PED_HATE pedtype_player1
		SET_CHAR_PROOFS pulaski false True True false false
		if done_this_bit = 0
			if not is_car_dead pulaski_car
				set_car_proofs pulaski_car FALSE FALSE false FALSE FALSE
				ADD_STUCK_CAR_CHECK_WITH_WARP pulaski_car 4.0 4000 true true false -1
			endif
			done_this_bit = 1
		endif
		mission_timer = game_timer + 240000
		++ mission_flag
	endif
endif

if mission_flag = 3
	if locate_char_any_means_2d pulaski 184.0797 1715.9486 40.6000 1108.6677 0	//getting close to vegas
		get_script_task_status pulaski	TASK_CAR_DRIVE_TO_COORD task_status
		if task_status = finished_task
			task_car_drive_to_coord pulaski -1 -2466.3091 2244.3284 3.8078 40.0 0 true 2
			mission_timer = game_timer + 10000
			mission_flag = 2
		endif
	endif
	if locate_char_any_means_2d pulaski -2747.4822 2334.3013 36.3400 5.8600 0 //about to enter san fran
		get_script_task_status pulaski	TASK_CAR_DRIVE_TO_COORD task_status
		if task_status = finished_task
			task_car_drive_to_coord pulaski -1 -1446.2249 2670.9915 54.9682 40.0 0 true 2
			mission_timer = game_timer + 10000
			mission_flag = 2
		endif
	endif
	if locate_char_any_means_char_2d scplayer pulaski 200.0 200.0 0
		lvar_int getaway_timer
		getaway_timer = game_timer + 8000
		if not does_blip_exist mission_blip
			TASK_CAR_DRIVE_WANDER pulaski -1 50.0 2
			add_blip_for_char pulaski mission_blip
		endif
	else
		if does_blip_exist mission_blip
			TASK_CAR_DRIVE_WANDER pulaski -1 28.0 2
			print_now VCR2__2 5000 1 //pulaski is getting away!
			remove_blip mission_blip
		endif
		if getaway_timer < game_timer
			print_now VCR2__3 5000 1 //pulaski got away!
			goto mission_vcr2_failed
		endif
	endif

	if convo_counter < 20
		if locate_char_any_means_char_2d scplayer pulaski 30.0 30.0 0
			if convo_timer < game_timer
				if audio_line_is_active = 0
					$audio_string    = $pulaski_chat[convo_counter]	
					audio_sound_file = pulaski_chat_sound[convo_counter]
					START_NEW_SCRIPT audio_line -1 0 1 audio_slot convo_counter
					if audio_slot = 1
						audio_slot = 2
					else
						audio_slot = 1
					endif
					convo_timer = game_timer + 500
					++ convo_counter
					if convo_counter < 20
						CLEAR_MISSION_AUDIO audio_slot
						LOAD_MISSION_AUDIO audio_slot pulaski_chat_sound[convo_counter]
					endif
				endif
			endif
		endif
	endif
	if not is_char_in_any_car pulaski
		if locate_char_any_means_char_2d scplayer pulaski 20.0 20.0 0
			get_script_task_status pulaski task_kill_char_on_foot_timed task_status
			if task_status = finished_task
				task_kill_char_on_foot_timed pulaski scplayer 4000
			endif
		endif
	endif
endif

if mission_flag = 9
	if mission_timer < game_timer
		if is_char_in_water pulaski
			goto mission_vcr2_passed
		else
			do_fade 500 fade_out
			++ mission_flag
		endif
		////Pulaski is dying, Player stands over him - SCRIPTED CUT		
		//[VCR2_DA]	//CARL:		Not feeling so fucking full of yourself now, huh?
		//[VCR2_DB]	//PULASKI:	Yeah, well (cough) thems the breaks... fuck...
		//[VCR2_DC]	//CARL:		Any last requests?
		//[VCR2_DD]	//PULASKI:	Yeah, can I (cough) can I rape your sister?
		//[VCR2_DE]	//CARL:		You a asshole to the end.  Punk motherfucker!
		//goto mission_vcr2_passed
	endif
endif

if mission_flag = 10
	request_animation swat
	if not get_fading_status
		clear_char_tasks_immediately scplayer
		delete_car pulaski_car
		GET_DEAD_CHAR_COORDINATES pulaski x y z
		delete_char pulaski
		get_closest_car_node x y z x y z
		//x = 0.0
		//y = 0.0
		//z = 2.1172
		clear_area x y z 100.0 1
		CUSTOM_PLATE_FOR_NEXT_CAR buffalo PULASKI
		create_car buffalo x y z pulaski_car
		change_car_colour pulaski_car CARCOLOUR_LIGHTBLUEGREY CARCOLOUR_BLACK
		set_car_density_multiplier 0.0
		set_ped_density_multiplier 0.0
		mission_timer = game_timer + 100
		++ mission_flag
	endif
endif
			
if mission_flag = 11
	if mission_timer < game_timer
		if has_animation_loaded swat
			if is_car_dead pulaski_car
				do_fade 500 fade_in
				goto mission_vcr2_passed
			endif
			get_offset_from_car_in_world_coords pulaski_car 1.6	-1.0 0.0 x y z
			get_ground_z_for_3d_coord x y z z
			lvar_int dead_pulaski
			create_char pedtype_mission1 special01 x y z dead_pulaski
			get_car_heading	pulaski_car heading
			heading += -90.0
			set_char_heading dead_pulaski heading
			set_char_bleeding dead_pulaski true
			TASK_PLAY_ANIM_NON_INTERRUPTABLE dead_pulaski gnstwall_injurd SWAT 9999.0 TRUE FALSE FALSE FALSE -1
			get_offset_from_car_in_world_coords pulaski_car 3.0	-1.0 0.0 x y z
			get_ground_z_for_3d_coord x y z z
			clear_char_tasks_immediately scplayer
			set_char_coordinates scplayer x y z
			get_car_heading	pulaski_car heading
			heading += 90.0
			set_char_heading scplayer heading

			get_offset_from_car_in_world_coords pulaski_car 3.3455 -1.6234 1.1443 x y z
			set_fixed_camera_position x y z 0.0 0.0 0.0
			get_offset_from_car_in_world_coords pulaski_car 2.5461 -1.3539 0.6073 x y z
			point_camera_at_char dead_pulaski fixed jump_cut
			
			SET_PLAYER_CONTROL player1 OFF
			HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
			SET_EVERYONE_IGNORE_PLAYER player1 TRUE
			SWITCH_WIDESCREEN ON
			SET_ALL_CARS_CAN_BE_DAMAGED FALSE
			
			get_offset_from_car_in_world_coords pulaski_car 1.2837 -0.8452 -0.2859 x y z
			TASK_LOOK_AT_COORD scplayer x y z 20000
			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_VCR2_DA
			do_fade 500 fade_in

			if not is_car_dead players_bandito
				get_car_coordinates pulaski_car x y z
				if locate_car_2d players_bandito x y 6.0 6.0 0
					delete_car players_bandito
				endif
			else
				delete_car players_bandito
			endif

			mission_timer = game_timer + 400
			++ mission_flag
		endif
	endif
endif

if mission_flag = 12
	if not get_fading_status
		if audio_line_is_active = 0
			$audio_string    = VCR2_DA	
			audio_sound_file = SOUND_VCR2_DA
			START_NEW_SCRIPT audio_line scplayer 0 1 1 1
			CLEAR_MISSION_AUDIO 2
			LOAD_MISSION_AUDIO 2 SOUND_VCR2_DB
			if not is_car_dead pulaski_car
				get_offset_from_car_in_world_coords pulaski_car 1.2837 -0.8452 -0.2859 x y z
				TASK_LOOK_AT_COORD scplayer x y z 20000
			endif
			mission_timer = game_timer + 500
			++ mission_flag
		endif
	endif
endif

if mission_flag = 13
	if mission_timer < game_timer
		if audio_line_is_active = 0
			$audio_string    = VCR2_DB	
			audio_sound_file = SOUND_VCR2_DB
			START_NEW_SCRIPT audio_line dead_pulaski 0 1 2 1
			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_VCR2_DC
			if not is_car_dead pulaski_car
				get_offset_from_car_in_world_coords pulaski_car 1.2837 -0.8452 -0.2859 x y z
				TASK_LOOK_AT_COORD scplayer x y z 20000
			endif
			mission_timer = game_timer + 500
			++ mission_flag
		endif
	endif
endif
if mission_flag = 14
	if mission_timer < game_timer
		if audio_line_is_active = 0
			$audio_string    = VCR2_DC	
			audio_sound_file = SOUND_VCR2_DC
			START_NEW_SCRIPT audio_line scplayer 0 1 1 1
			CLEAR_MISSION_AUDIO 2
			LOAD_MISSION_AUDIO 2 SOUND_VCR2_DD
			if not is_car_dead pulaski_car
				get_offset_from_car_in_world_coords pulaski_car 1.2837 -0.8452 -0.2859 x y z
				TASK_LOOK_AT_COORD scplayer x y z 20000
			endif
			mission_timer = game_timer + 500
			++ mission_flag
		endif
	endif
endif
if mission_flag = 15
	if mission_timer < game_timer
		if audio_line_is_active = 0
			$audio_string    = VCR2_DD	
			audio_sound_file = SOUND_VCR2_DD
			START_NEW_SCRIPT audio_line dead_pulaski 0 1 2 1
			CLEAR_MISSION_AUDIO 1
			LOAD_MISSION_AUDIO 1 SOUND_VCR2_DE
			if not is_car_dead pulaski_car
				get_offset_from_car_in_world_coords pulaski_car 1.2837 -0.8452 -0.2859 x y z
				TASK_LOOK_AT_COORD scplayer x y z 20000
			endif
			mission_timer = game_timer + 500
			++ mission_flag
		endif
	endif
endif
if mission_flag = 16
	if mission_timer < game_timer
		if audio_line_is_active = 0
			$audio_string    = VCR2_DE	
			audio_sound_file = SOUND_VCR2_DE
			START_NEW_SCRIPT audio_line scplayer 0 1 1 1
			
			set_near_clip 0.1
			if not is_car_dead pulaski_car
				get_offset_from_car_in_world_coords pulaski_car 1.2837 -0.8452 -0.2859 x y z //cut looking at player from pulaski's perspective
				TASK_LOOK_AT_COORD scplayer x y z 20000
				set_fixed_camera_position x y z 0.0 0.0 0.0
				get_offset_from_car_in_world_coords pulaski_car 2.1414 -0.9080 0.2244 x y z
				point_camera_at_point x y z	jump_cut
			endif
			
			mission_timer = game_timer + 500
			++ mission_flag
		endif
	endif
endif
if mission_flag = 17
	if mission_timer < game_timer
		if audio_line_is_active = 0
			//SET_PLAYER_CONTROL player1 ON
			TASK_PLAY_ANIM scplayer FightA_G PED 4.0 FALSE FALSE FALSE FALSE -1
			++ mission_flag
		endif
	endif
endif

if mission_flag = 18
	IF IS_CHAR_PLAYING_ANIM scplayer FightA_G
		lvar_float Retured_anim_time
		GET_CHAR_ANIM_CURRENT_TIME scplayer FightA_G Retured_anim_time
		if Retured_anim_time > 0.351
			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 0.0 0.0 0.0 SOUND_STAMP_PED
			SET_MUSIC_DOES_FADE FALSE
			DO_FADE 0 FADE_OUT
			if not is_char_dead	dead_pulaski
				task_die dead_pulaski
			endif
			mission_timer = game_timer + 1000
			++ mission_flag
		endif
	ENDIF
endif
if mission_flag = 19
	if mission_timer < game_timer
		DO_FADE 500 FADE_IN
		
		set_car_density_multiplier 1.0
		set_ped_density_multiplier 1.0
		
		SET_PLAYER_CONTROL player1 ON
		HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
		SET_EVERYONE_IGNORE_PLAYER player1 FALSE
		SWITCH_WIDESCREEN OFF
		//SET_ALL_CARS_CAN_BE_DAMAGED TRUE
		SET_CAMERA_BEHIND_PLAYER
		RESTORE_CAMERA_JUMPCUT

		goto mission_vcr2_passed
	endif
endif

GOTO mission_vcr2_loop


	
// ************************************ MISSION FAILED ************************************
mission_vcr2_failed:
PRINT_BIG M_FAIL 5000 1
RETURN

   

// ************************************ MISSION PASSED ************************************
mission_vcr2_passed:

PRINT_BIG M_PASSD 5000 1 //"Mission Passed!"
ADD_SCORE player1 0
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1
REGISTER_MISSION_PASSED VCRASH2
PLAYER_MADE_PROGRESS 1
REMOVE_BLIP vcrash_contact_blip
SET_INT_STAT PASSED_VCRASH2 1
flag_vcrash_mission_counter ++
RETURN
		


// ************************************ MISSION CLEANUP ***********************************
mission_vcr2_cleanup:

REMOVE_BLIP mission_blip
GET_GAME_TIMER timer_mobile_start

unload_special_character 1
mark_model_as_no_longer_needed bandito
mark_model_as_no_longer_needed buffalo
mark_model_as_no_longer_needed desert_eagle
SET_MUSIC_DOES_FADE TRUE

if is_player_playing player1
	CLEAR_CHAR_RELATIONSHIP scplayer ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
endif

remove_animation swat

flag_player_on_mission = 0

MISSION_HAS_FINISHED
RETURN
}		



		

