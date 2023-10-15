MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Casino 10 *****************************************
// *********************************** Marco Forelli   *************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME casin10

// Mission start stuff

GOSUB mission_start_casino10

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_casino10_failed
ENDIF

GOSUB mission_cleanup_casino10

MISSION_END

{ 
// Variables for mission

// general stuff
LVAR_INT airport_blip_casino10 airport_blip2_casino10

LVAR_INT flag_played_cut_casino10 // used to tell if the player has skipped at cutscene or not

LVAR_INT player_leap_casino10

LVAR_INT roll_left_casino10 // roll left task
LVAR_INT roll_right_casino10 // roll right task

// Forelli
LVAR_INT forelli_casino10 blip_forelli_casino10 flag_forelli_dead_casino10 forelli_run_count_casino10

LVAR_INT time_forelli_casino10

LVAR_INT forelli_taunts_casino10 // used for forelli to shout abuse at the player

LVAR_INT forelli_health_casino10

// Forelli bodyguards
LVAR_INT bodyguard1_casino10 flag_bodyguard1_dead_casino10 bodyguard1_kill_player_casino10 

LVAR_INT bodyguard2_casino10 flag_bodyguard2_dead_casino10 bodyguard2_kill_player_casino10  

//Cannon fodder goons

LVAR_INT goon1_casino10 flag_goon1_dead_casino10 goon1_attack_player_casino10 

LVAR_INT goon2_casino10 flag_goon2_dead_casino10 goon2_attack_player_casino10

LVAR_INT goon3_casino10 flag_goon3_dead_casino10 goon3_kill_player_casino10 

LVAR_INT goon4_casino10 flag_goon4_dead_casino10 goon4_kill_player_casino10
 
LVAR_INT goon5_casino10 flag_goon5_dead_casino10 goon5_task_casino10 goon5_kill_player_casino10

LVAR_INT goon6_casino10 flag_goon6_dead_casino10 goon6_task_casino10 goon6_kill_player_casino10 

// Goons on the top floor
LVAR_INT top_goon1_casino10 top_goon1_dead_casino10	top_goon1_task2_casino10 top_goon1_kill_player_casino10

LVAR_INT top_goon2_casino10 top_goon2_dead_casino10 top_goon2_task2_casino10 top_goon2_kill_player_casino10

LVAR_INT top_goon3_casino10 top_goon3_dead_casino10 flag_animate_top_goon3_casino10

LVAR_INT top_goon4_casino10 top_goon4_dead_casino10 flag_animate_top_goon4_casino10

// corridor guards
LVAR_INT corridor_goon1_casino10 corridor_goon1_dead_casino10 corridor_goon1_kill_player_casino10 

LVAR_INT corridor_goon2_casino10 corridor_goon2_dead_casino10 corridor_goon2_kill_player_casino10

LVAR_INT corridor_goon3_casino10 corridor_goon3_dead_casino10 corridor_goon3_kill_player_casino10

// Sequence stuff
LVAR_INT peekright_casino10

// kill player tasks
LVAR_INT task_kill_player_casino10 task_kill2_player_casino10

// Used to test if animations have finished
LVAR_FLOAT return_animation_time_casino10

LVAR_INT enemy_actor_casino10 // used for different guys in the gosub for peeking

LVAR_INT enemy_actor1_casino10 enemy_actor2_casino10 // used for the kill player commands

LVAR_INT enemy_actor3_casino10 // used for the roll left task

LVAR_INT enemy_actor4_casino10 // used for the roll right task

// decision makers
LVAR_INT tough_dm_casino10 dm_duck_casino10

// guys for the initial cutscene
LVAR_INT macca_casino10 paul_casino10 rosenberg_casino10 empty_dm_casino10 car_casino10

LVAR_INT jet_casino10 jet_blip_casino10

LVAR_INT flag_watched_1stcut_casino10

// Plane cutscene skip stuff
LVAR_INT watched_plane_cutscene_casino10

// flag to say player has entered the bistro
LVAR_INT player_entered_bistro_casino10 player_downstairs_casino10

// Cleanup stuff for the end section
LVAR_INT flag_mid_cleanup_done_casino10 end_guys_created_casino10

// end of level guys
LVAR_INT end_goon1_casino10 end_goon2_casino10 end_goon3_casino10 car2_casino10

// Forelli's sequence
LVAR_INT forelli_end_casino10

// cutscene cars
LVAR_INT stored_car_casino10

// liberty City blips
LVAR_INT liberty_blip_casino10

// end cutscene stuff
LVAR_INT flag_watched_end1_casino10 flag_watched_end2_casino10 had_plane_message_casino10

// plane landing at the end
LVAR_INT landing_blip_casino10

// Mobile phone and audio stuff
LVAR_INT casino10_index casino10_audio_is_playing casino10_cutscene_flag casino10_chat_switch casino10_audio_chat[17]

VAR_TEXT_LABEL $casino10_chat[17]

// counter for the dead gang members
LVAR_INT counter_dead_casino10

LVAR_INT end_goon1_casino10_dead end_goon1_kill_player_casino10 end_goon2_casino10_dead end_goon2_kill_player_casino10
LVAR_INT end_goon3_casino10_dead end_goon3_kill_player_casino10

// help message for the cinematic camera
LVAR_INT flag_had_camera_message_casino10

// NEW CUTSCENE FOR LIBERTY
LVAR_INT taxi_driver_casino10 cutscene_car_casino10 player_liberty_sequence
LVAR_INT cutscene_car2_casino10 car2_driver_casino10

LVAR_INT dummy1_casino10 dummy2_casino10 dummy3_casino10 dummy4_casino10 dummy5_casino10
LVAR_INT dummy6_casino10 dummy7_casino10 dummy8_casino10 dummy9_casino10 dummy10_casino10

// *************************************** MISSION DIALOGUE ***************************************

casino10_chat_switch:

SWITCH casino10_chat_switch		   

	CONST_INT casino10_CHAT1 0
	CONST_INT casino10_CHAT2 1
	
	CASE casino10_CHAT1

		$casino10_chat[0] = &CAS11BA	//This is private flight CJ101 on approach to Liberty, over.
		$casino10_chat[1] = &CAS11BB	//Flight CJ101, we have you on our scope.
		$casino10_chat[2] = &CAS11BC	//Stay on current heading and await final approach instructions...
		$casino10_chat[3] = &CAS11CA	//Welcome to Liberty City, CJ101...

		casino10_audio_chat[0] = SOUND_CAS11BA	//This is private flight CJ101 on approach to Liberty, over.
		casino10_audio_chat[1] = SOUND_CAS11BB	//Flight CJ101, we have you on our scope.
		casino10_audio_chat[2] = SOUND_CAS11BC	//Stay on current heading and await final approach instructions...
		casino10_audio_chat[3] = SOUND_CAS11CA	//Welcome to Liberty City, CJ101...

		cell_index_end = 3
	BREAK

	CASE casino10_CHAT2

		$casino10_chat[0] = &CAS11GA	//This is flight CJ101 out fo Liberty City,
		$casino10_chat[1] = &CAS11GB	//requesting final approach to Las Venturas International, over.
		$casino10_chat[2] = &CAS11GC	//Flight CJ101, you are clear for laanding on runway 3.
	   
		casino10_audio_chat[0] = SOUND_CAS11GA	//This is flight CJ101 out fo Liberty City,
		casino10_audio_chat[1] = SOUND_CAS11GB	//requesting final approach to Las Venturas International, over.
		casino10_audio_chat[2] = SOUND_CAS11GC	//Flight CJ101, you are clear for laanding on runway 3.
	   
		cell_index_end = 2
	BREAK

ENDSWITCH

RETURN

// ************************************** END OF MISSION DIALOGUE *********************************

 
// **************************************** Mission Start **********************************

mission_start_casino10:

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

// general stuff
flag_played_cut_casino10 = 0

forelli_run_count_casino10 = 0

flag_forelli_dead_casino10 = 0

time_forelli_casino10 = 0

forelli_taunts_casino10 = 0

forelli_health_casino10 = 1000

// bodyguards1
flag_bodyguard1_dead_casino10 = 0

bodyguard1_kill_player_casino10 = 0

// bodyguard2
flag_bodyguard2_dead_casino10 = 0

bodyguard2_kill_player_casino10 = 0

// goon1
flag_goon1_dead_casino10 = 0

goon1_attack_player_casino10 = 0

// goon2
flag_goon2_dead_casino10 = 0

goon2_attack_player_casino10 = 0

// goon3
flag_goon3_dead_casino10 = 0
goon3_kill_player_casino10 = 0

// goon4
flag_goon4_dead_casino10 = 0
goon4_kill_player_casino10 = 0

// goon5
flag_goon5_dead_casino10 = 0
goon5_kill_player_casino10 = 0

// goon6
flag_goon6_dead_casino10 = 0

goon6_kill_player_casino10 = 0

return_animation_time_casino10 = 0.0

flag_animate_top_goon3_casino10 = 0

flag_animate_top_goon3_casino10 = 0

// corridor guards
corridor_goon1_dead_casino10 = 0

corridor_goon1_kill_player_casino10 = 0

// corridor guard2
corridor_goon2_dead_casino10 = 0

corridor_goon2_kill_player_casino10 = 0

// corridor guard3
corridor_goon3_dead_casino10 = 0

corridor_goon3_kill_player_casino10 = 0

// 1st cutscene
flag_watched_1stcut_casino10 = 0

// plane taking off cutscene
watched_plane_cutscene_casino10 = 0

// player has entered the bistro
player_entered_bistro_casino10 = 0

player_downstairs_casino10 = 0

// end section stuff
flag_mid_cleanup_done_casino10 = 0

end_guys_created_casino10 = 0

flag_watched_end1_casino10 = 0

flag_watched_end2_casino10 = 0

had_plane_message_casino10 = 0

// top goon1
top_goon1_kill_player_casino10 = 0
top_goon1_dead_casino10 = 0

// top goons kill player (top goon1 is the guy player kills)
top_goon2_kill_player_casino10 = 0
top_goon2_dead_casino10 = 0
top_goon3_dead_casino10 = 0
top_goon4_dead_casino10 = 0

// counter for dead guards
counter_dead_casino10 = 0

// new end guards dead checks
end_goon1_casino10_dead = 0
end_goon2_casino10_dead = 0
end_goon3_casino10_dead = 0
end_goon1_kill_player_casino10 = 0
end_goon2_kill_player_casino10 = 0
end_goon3_kill_player_casino10 = 0

// help message for cinematic camera
flag_had_camera_message_casino10 = 0

LOAD_MISSION_TEXT CASIN10

WAIT 0

CLEAR_AREA 2163.589 1682.247 9.860 1.0 TRUE 
SET_CHAR_COORDINATES scplayer 2163.589 1682.247 9.860
SET_CHAR_HEADING scplayer 80.0
SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

// ****************************************START OF CUTSCENE********************************

// fades the screen in

iTerminateAllAmbience = 1 

SET_AREA_VISIBLE 2


LOAD_CUTSCENE CAS_11a
 
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

iTerminateAllAmbience = 0

// ****************************************END OF CUTSCENE**********************************

SET_WANTED_MULTIPLIER 0.5

SET_PLAYER_CONTROL player1 OFF
SET_POLICE_IGNORE_PLAYER player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 ON

SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2233.5 1712.8 10.0 //MAFIA CASINO

WAIT 1000

REQUEST_MODEL SHAMAL
REQUEST_MODEL SENTINEL

LOAD_SPECIAL_CHARACTER 1 MACCER
LOAD_SPECIAL_CHARACTER 2 PAUL
LOAD_SPECIAL_CHARACTER 3 ROSE 

LOAD_MISSION_AUDIO 1 SOUND_CAS11AA // Ok, you guys get out of Las Venturas fast!
LOAD_MISSION_AUDIO 2 SOUND_CAS11AB // I'll be in touch.

// Sequences
// stuff here to make the compiler not complain
GOTO fool_compiler
OPEN_SEQUENCE_TASK peekright_casino10
		TASK_STAY_IN_SAME_PLACE -1 FALSE
CLOSE_SEQUENCE_TASK peekright_casino10

OPEN_SEQUENCE_TASK task_kill_player_casino10
		TASK_STAY_IN_SAME_PLACE -1 FALSE
CLOSE_SEQUENCE_TASK task_kill_player_casino10

OPEN_SEQUENCE_TASK task_kill2_player_casino10
		TASK_STAY_IN_SAME_PLACE -1 FALSE
CLOSE_SEQUENCE_TASK task_kill2_player_casino10

OPEN_SEQUENCE_TASK roll_left_casino10
		TASK_STAY_IN_SAME_PLACE -1 FALSE
CLOSE_SEQUENCE_TASK roll_left_casino10

OPEN_SEQUENCE_TASK roll_right_casino10
		TASK_STAY_IN_SAME_PLACE -1 FALSE
CLOSE_SEQUENCE_TASK roll_right_casino10

CREATE_CAR SENTINEL 0.0 0.0 0.0 car2_casino10
DELETE_CAR car2_casino10

ADD_BLIP_FOR_COORD 0.0 0.0 0.0 jet_blip_casino10
REMOVE_BLIP jet_blip_casino10

fool_compiler:

// decision makers
LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH tough_dm_casino10

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm_duck_casino10
ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_duck_casino10 EVENT_SHOT_FIRED_WHIZZED_BY TASK_SIMPLE_DUCK_WHILE_SHOTS_WHIZZING 0.0 100.0 100.0 100.0 FALSE TRUE

LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY empty_dm_casino10

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED SHAMAL
OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_SPECIAL_CHARACTER_LOADED 2
OR NOT HAS_MODEL_LOADED SENTINEL
OR NOT HAS_MISSION_AUDIO_LOADED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2

	WAIT 0

ENDWHILE

WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 3

	WAIT 0

ENDWHILE

// ****** creates the character for the cutscene outside casino where Rosenberg Paul and Macca leg it ******

CLEAR_AREA 2163.589 1682.247 9.860 1.0 TRUE 
SET_CHAR_COORDINATES scplayer 2163.589 1682.247 9.860
SET_CHAR_HEADING scplayer 80.0
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE
LOAD_SCENE 2163.589 1682.247 9.860 

SWITCH_ROADS_OFF 2095.350 1734.179 8.0 2337.495 1524.991 15.0
SWITCH_PED_ROADS_OFF 2095.350 1734.179 8.0 2337.495 1524.991 15.0
 
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 2162.213 1682.752 9.860 macca_casino10 
SET_CHAR_DECISION_MAKER macca_casino10 empty_dm_casino10
SET_CHAR_HEADING macca_casino10 280.0
SET_CHAR_ONLY_DAMAGED_BY_PLAYER macca_casino10 TRUE
TASK_TURN_CHAR_TO_FACE_CHAR macca_casino10 scplayer
SET_CHAR_PROOFS macca_casino10 TRUE TRUE TRUE TRUE TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH macca_casino10 TRUE 

CLEAR_AREA 2160.582 1682.103 9.735 4.0 TRUE 
CREATE_CAR SENTINEL 2160.582 1682.103 9.735 car_casino10
SET_CAR_HEADING car_casino10 0.0
SET_CAR_ONLY_DAMAGED_BY_PLAYER car_casino10 TRUE
SET_CAR_PROOFS car_casino10 TRUE TRUE TRUE TRUE TRUE

CREATE_CHAR_AS_PASSENGER car_casino10 PEDTYPE_MISSION1 SPECIAL03 1 rosenberg_casino10 
SET_CHAR_DECISION_MAKER rosenberg_casino10 empty_dm_casino10
SET_CHAR_ONLY_DAMAGED_BY_PLAYER rosenberg_casino10 TRUE
SET_CHAR_PROOFS rosenberg_casino10 TRUE TRUE TRUE TRUE TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH rosenberg_casino10 TRUE

CREATE_CHAR_INSIDE_CAR car_casino10 PEDTYPE_MISSION1 SPECIAL02 paul_casino10
SET_CHAR_DECISION_MAKER paul_casino10 empty_dm_casino10
SET_CHAR_ONLY_DAMAGED_BY_PLAYER paul_casino10 TRUE
SET_CHAR_PROOFS paul_casino10 TRUE TRUE TRUE TRUE TRUE
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH paul_casino10 TRUE

CAR_SET_IDLE car_casino10


CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE                       
CAMERA_SET_VECTOR_MOVE 2165.2280 1680.2563 9.9643 2164.9746 1684.1086 11.4927 14500 TRUE
CAMERA_SET_VECTOR_TRACK 2162.274 1682.247 11.860 2160.582 1682.103 11.0 14500 TRUE	 

SET_FADING_COLOUR 0 0 0

SWITCH_WIDESCREEN ON

MAKE_PLAYER_GANG_DISAPPEAR

DO_FADE 2000 FADE_IN

CLEAR_AREA 2162.274 1682.247 9.860 120.0 TRUE

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

// cutscene of the player telling Paul, Macca and Rosenberg to go elsewhere and he will meet them later
SKIP_CUTSCENE_START

IF IS_PLAYER_PLAYING player1
	PLAY_MISSION_AUDIO 1 
	PRINT_NOW (CAS11AA) 10000 1 //"Ok, you guys get out of Las Venturas fast!"
	START_CHAR_FACIAL_TALK scplayer 999999
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  scplayer IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
ELSE
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_THIS_PRINT (CAS11AA) 
ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1

	WAIT 0

	IF IS_CHAR_DEAD macca_casino10
		PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
		GOTO mission_casino10_failed
	ELSE
		CLEAR_CHAR_TASKS macca_casino10 
	ENDIF

	IF IS_CHAR_DEAD paul_casino10
		PRINT_NOW (CM10_12) 8000 1 //"Paul is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD rosenberg_casino10
		PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CAR_DEAD car_casino10
		PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF NOT IS_PLAYER_PLAYING player1
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (CAS11AA) 
	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_THIS_PRINT (CAS11AA)
STOP_CHAR_FACIAL_TALK scplayer

LOAD_MISSION_AUDIO 1 SOUND_CAS11AC  //What about your backup, ol’lad, will you be ok without us?
	   
PLAY_MISSION_AUDIO 2
PRINT_NOW (CAS11AB) 10000 1 //"I'll be in touch."
START_CHAR_FACIAL_TALK scplayer 999999

WHILE NOT HAS_MISSION_AUDIO_FINISHED 2
OR NOT HAS_MISSION_AUDIO_LOADED 1

	WAIT 0

	IF IS_CHAR_DEAD macca_casino10
		PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
		GOTO mission_casino10_failed
	ELSE
		CLEAR_CHAR_TASKS macca_casino10 
	ENDIF

	IF IS_CHAR_DEAD paul_casino10
		PRINT_NOW (CM10_12) 8000 1 //"Paul is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD rosenberg_casino10
		PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CAR_DEAD car_casino10
		PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF NOT IS_PLAYER_PLAYING player1
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (CAS11AB) 
	ENDIF

ENDWHILE

CLEAR_CHAR_TASKS scplayer

CLEAR_MISSION_AUDIO 2
CLEAR_THIS_PRINT (CAS11AB)
STOP_CHAR_FACIAL_TALK scplayer

LOAD_MISSION_AUDIO 2 SOUND_CAS11AD //Of course he will, you fucking moron, come on!

IF NOT IS_CHAR_DEAD macca_casino10 
	PLAY_MISSION_AUDIO 1
	PRINT_NOW (CAS11AC) 10000 1 //"What about your backup, ol’lad, will you be ok without us?"
	START_CHAR_FACIAL_TALK macca_casino10 999999
	TASK_PLAY_ANIM_NON_INTERRUPTABLE  macca_casino10 IDLE_CHAT PED 4.0 TRUE FALSE FALSE FALSE -1
ELSE
	PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
	GOTO mission_casino10_failed
ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2

	WAIT 0

	IF IS_CHAR_DEAD macca_casino10
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (CAS11AC)
		PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD paul_casino10
		PRINT_NOW (CM10_12) 8000 1 //"Paul is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD rosenberg_casino10
		PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CAR_DEAD car_casino10
		PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
		GOTO mission_casino10_failed 
	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 1
CLEAR_THIS_PRINT (CAS11AC)

IF NOT IS_CHAR_DEAD macca_casino10
	STOP_CHAR_FACIAL_TALK macca_casino10 
	CLEAR_CHAR_TASKS macca_casino10
ELSE
	PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
	GOTO mission_casino10_failed
ENDIF

IF NOT IS_CHAR_DEAD rosenberg_casino10
	TASK_LOOK_AT_CHAR rosenberg_casino10 scplayer -2 
	PLAY_MISSION_AUDIO 2
	PRINT_NOW (CAS11AD) 10000 1 //"Of course he will, you fucking moron, come on!"
	START_CHAR_FACIAL_TALK rosenberg_casino10 999999
ELSE
	PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_THIS_PRINT  (CAS11AD)
	GOTO mission_casino10_failed
ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 2

	WAIT 0

	IF IS_CHAR_DEAD macca_casino10
		PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD paul_casino10
		PRINT_NOW (CM10_12) 8000 1 //"Paul is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD rosenberg_casino10
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_THIS_PRINT (CAS11AC)
		PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CAR_DEAD car_casino10
		PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
		GOTO mission_casino10_failed 
	ENDIF

ENDWHILE

CLEAR_MISSION_AUDIO 2
CLEAR_THIS_PRINT (CAS11AD)

IF NOT IS_CHAR_DEAD rosenberg_casino10 
	STOP_CHAR_FACIAL_TALK rosenberg_casino10
	CLEAR_LOOK_AT rosenberg_casino10
ELSE
	PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
	GOTO mission_casino10_failed 
ENDIF

IF NOT IS_CAR_DEAD car_casino10

	IF NOT IS_CHAR_DEAD macca_casino10
		 TASK_ENTER_CAR_AS_PASSENGER macca_casino10 car_casino10 10000 0
	ELSE
		PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD macca_casino10
		PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
		GOTO mission_casino10_failed 
	ENDIF
	
	IF IS_CHAR_DEAD rosenberg_casino10  
		PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD paul_casino10
		PRINT_NOW (CM10_12) 8000 1 //"Paul is dead!"
		GOTO mission_casino10_failed	
	ENDIF

ELSE
	PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
	GOTO mission_casino10_failed
ENDIF

WHILE NOT IS_CHAR_IN_CAR macca_casino10 car_casino10
OR NOT IS_CHAR_IN_CAR paul_casino10 car_casino10
OR NOT IS_CHAR_IN_CAR rosenberg_casino10 car_casino10

	WAIT 0

	IF IS_CHAR_DEAD macca_casino10
		PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD paul_casino10
		PRINT_NOW (CM10_12) 8000 1 //"Paul is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CHAR_DEAD rosenberg_casino10
		PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
		GOTO mission_casino10_failed 
	ENDIF

	IF IS_CAR_DEAD car_casino10
		PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
		GOTO mission_casino10_failed 
	ENDIF
	
ENDWHILE

IF NOT IS_CAR_DEAD car_casino10
	CAR_GOTO_COORDINATES car_casino10 2148.048 1703.466 9.727
	SET_CAR_DRIVING_STYLE car_casino10 2
	SET_CAR_CRUISE_SPEED car_casino10 30.0
ELSE
	PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
	GOTO mission_casino10_failed 
ENDIF

WAIT 2000

DELETE_CHAR macca_casino10
DELETE_CHAR paul_casino10
DELETE_CHAR rosenberg_casino10
DELETE_CAR car_casino10

SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL  
 
SWITCH_WIDESCREEN OFF

MAKE_PLAYER_GANG_REAPPEAR

SET_PLAYER_CONTROL player1 ON
SET_CAMERA_BEHIND_PLAYER
CAMERA_RESET_NEW_SCRIPTABLES
RESTORE_CAMERA_JUMPCUT
SET_EVERYONE_IGNORE_PLAYER player1 OFF
SET_POLICE_IGNORE_PLAYER player1 OFF

 UNLOAD_SPECIAL_CHARACTER 1
 UNLOAD_SPECIAL_CHARACTER 2
 UNLOAD_SPECIAL_CHARACTER 3

SWITCH_ROADS_BACK_TO_ORIGINAL 2095.350 1734.179 8.0 2337.495 1524.991 15.0
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2095.350 1734.179 8.0 2337.495 1524.991 15.0

flag_watched_1stcut_casino10 = 1
SKIP_CUTSCENE_END

IF flag_watched_1stcut_casino10 = 0

	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_PRINTS

	SET_FADING_COLOUR 0 0 0

	DO_FADE 1000 FADE_OUT

	WHILE GET_FADING_STATUS

		WAIT 0

//		IF IS_CHAR_DEAD macca_casino10
//			PRINT_NOW (CM10_11) 8000 1 //"Macca is dead!"
//			GOTO mission_casino10_failed 
//		ENDIF
//
//		IF IS_CHAR_DEAD paul_casino10
//			PRINT_NOW (CM10_12) 8000 1 //"Paul is dead!"
//			GOTO mission_casino10_failed 
//		ENDIF
//
//		IF IS_CHAR_DEAD rosenberg_casino10
//			PRINT_NOW (CM10_13) 8000 1 //"Rosenberg is dead!"
//			GOTO mission_casino10_failed 
//		ENDIF
//
//		IF IS_CAR_DEAD car_casino10
//			PRINT_NOW (CM10_14) 8000 1 //"The car is dead!"
//			GOTO mission_casino10_failed 
//		ENDIF
		
	ENDWHILE

	MAKE_PLAYER_GANG_REAPPEAR

	CLEAR_AREA 2163.589 1682.247 9.860 1.0 TRUE 
	SET_CHAR_COORDINATES scplayer 2163.589 1682.247 9.860
	SET_CHAR_HEADING scplayer 80.0

	DELETE_CHAR macca_casino10
	DELETE_CHAR paul_casino10
	DELETE_CHAR rosenberg_casino10
	DELETE_CAR car_casino10

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL

	UNLOAD_SPECIAL_CHARACTER 1
	UNLOAD_SPECIAL_CHARACTER 2
	UNLOAD_SPECIAL_CHARACTER 3

	SWITCH_WIDESCREEN OFF
	CAMERA_RESET_NEW_SCRIPTABLES
	SET_CAMERA_BEHIND_PLAYER
	RESTORE_CAMERA_JUMPCUT

	SET_FADING_COLOUR 0 0 0

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

	SWITCH_ROADS_BACK_TO_ORIGINAL 2095.350 1734.179 8.0 2337.495 1524.991 15.0
	SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2095.350 1734.179 8.0 2337.495 1524.991 15.0

	DO_FADE 1000 FADE_IN

	WHILE GET_FADING_STATUS

		WAIT 0
		
	ENDWHILE 	  
 
	SET_PLAYER_CONTROL player1 ON
	SET_EVERYONE_IGNORE_PLAYER player1 OFF
	SET_POLICE_IGNORE_PLAYER player1 OFF

ENDIF

// end of cutscene player is told to get to the airport
PRINT_NOW (CM10_1) 10000 1 //"Get to the Vegas airport, we have a chartered jet waiting to take you to Liberty."
ADD_BLIP_FOR_COORD 1707.828 1606.639 9.055 airport_blip2_casino10
CLEAR_AREA 1707.828 1606.639 9.055 2.0 FALSE 

// waiting for the player to get to the airport
WHILE NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 1707.828 1606.639 9.055 4.0 4.0 3.0 FALSE

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
    	GOTO mission_casino10_passed  
	ENDIF

ENDWHILE

REMOVE_BLIP airport_blip2_casino10
 		 
PRINT_NOW (CM10_10) 10000 1 //"Make your way to the aircraft"

// creates the jet
CLEAR_AREA 1479.714 1758.306 12.098 2.0 TRUE 
CREATE_CAR SHAMAL 1479.714 1758.306 12.098 jet_casino10
SET_CAR_ONLY_DAMAGED_BY_PLAYER jet_casino10 TRUE
SET_CAR_HEADING jet_casino10 180.0
ADD_BLIP_FOR_CAR jet_casino10 airport_blip_casino10
SET_BLIP_AS_FRIENDLY airport_blip_casino10 TRUE 

WHILE NOT IS_CHAR_IN_CAR scplayer jet_casino10

	WAIT 0

	IF IS_CAR_DEAD jet_casino10
		PRINT_NOW (CM10_17) 8000 1 //"You have destroyed the jet!"
		REMOVE_BLIP airport_blip_casino10
		GOTO mission_casino10_failed
	ENDIF
	
ENDWHILE

IF NOT IS_CAR_DEAD jet_casino10
	SET_CAR_ONLY_DAMAGED_BY_PLAYER jet_casino10 FALSE
ELSE
	PRINT_NOW (CM10_17) 8000 1 //"You have destroyed the jet!"
	REMOVE_BLIP airport_blip_casino10
	GOTO mission_casino10_failed
ENDIF

REMOVE_BLIP airport_blip_casino10

PRINT_NOW (CM10_18) 8000 1 //"Fly to Liberty City"
ADD_BLIP_FOR_COORD 7500.0 2478.34 200.0 liberty_blip_casino10

TIMERA = 0

WHILE NOT LOCATE_CHAR_IN_CAR_3D scplayer 7500.0 2478.34 200.0 100.0 100.0 200.0 FALSE 
OR NOT IS_CHAR_SITTING_IN_CAR scplayer jet_casino10

	WAIT 0

	IF IS_CAR_DEAD jet_casino10
		PRINT_NOW (CM10_17) 8000 1 //"You have destroyed the jet!"
		GOTO mission_casino10_failed
	ELSE

		IF IS_CHAR_IN_CAR scplayer jet_casino10

			IF flag_had_camera_message_casino10 = 0

				IF TIMERA > 40000
					
					PRINT_HELP CM10_99  

					flag_had_camera_message_casino10 = 1
				ENDIF

			ENDIF
				
					   
			IF had_plane_message_casino10 = 1  
				PRINT_NOW (CM10_18) 8000 1 //"Fly to Liberty City"
				ADD_BLIP_FOR_COORD 7500.0 2478.34 200.0 liberty_blip_casino10
				REMOVE_BLIP airport_blip_casino10 
				blob_flag = 1
				had_plane_message_casino10 = 0
			ENDIF
	
		ELSE

			IF had_plane_message_casino10 = 0
				REMOVE_BLIP liberty_blip_casino10
				ADD_BLIP_FOR_CAR jet_casino10 airport_blip_casino10
				SET_BLIP_AS_FRIENDLY airport_blip_casino10 TRUE
				PRINT_NOW (IN_VEH) 5000 1 //"Get back in the helicopter!"
				blob_flag = 0 
				had_plane_message_casino10 = 1
			ENDIF 

		ENDIF 

	ENDIF
	
ENDWHILE

REMOVE_BLIP liberty_blip_casino10 

SET_FADING_COLOUR 0 0 0

DO_FADE 500 FADE_OUT

WHILE GET_FADING_STATUS 

	WAIT 0

//	IF IS_CAR_DEAD jet_casino10
//		PRINT_NOW (CM10_17) 8000 1 //"You have destroyed the jet!"
//		GOTO mission_casino10_failed
//	ENDIF

ENDWHILE

SET_AREA_NAME MARKS

MAKE_PLAYER_GANG_DISAPPEAR

SET_PLAYER_CONTROL player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 ON
SET_POLICE_IGNORE_PLAYER player1 ON

// **************************** loads and creates all the characters for the St. Marks Bistro *******************

CLEAR_WANTED_LEVEL player1
SET_MAX_WANTED_LEVEL 0

SET_RESPAWN_POINT_FOR_DURATION_OF_MISSION 1578.4460 1770.6816 9.8358

FORCE_WEATHER_NOW WEATHER_SUNNY_SF

REQUEST_MODEL VMAFF1 	 
REQUEST_MODEL COLT45
REQUEST_MODEL TAXI
REQUEST_MODEL SENTINEL
REQUEST_CAR_RECORDING 602 // player taxi
REQUEST_CAR_RECORDING 603 // mafia sentinel
REQUEST_MODEL BMYBU
REQUEST_MODEL WFYBU

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED VMAFF1
OR NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_MODEL_LOADED TAXI
OR NOT HAS_MODEL_LOADED SENTINEL
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 602
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 603

 	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED BMYBU
OR NOT HAS_MODEL_LOADED WFYBU

	WAIT 0

ENDWHILE
 
SET_AREA_VISIBLE 1 // inside of bistro and surrounding land

SET_CHAR_AREA_VISIBLE scplayer 1

REQUEST_COLLISION -811.9990 457.0270
LOAD_SCENE_IN_DIRECTION -811.9990 457.0270 1356.6222 82.0 // Make St marks load in.

//SWITCH_PED_ROADS_ON -908.229 445.396 1342.0 -772.180 473.524 1370.0 // St. Marks Bistro

LOAD_ALL_MODELS_NOW

IF IS_CHAR_IN_ANY_CAR scplayer
	SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
	WARP_CHAR_FROM_CAR_TO_COORD scplayer -813.833 459.110 998.989
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer 
ELSE
	SET_CHAR_PROOFS scplayer TRUE TRUE TRUE TRUE TRUE
	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	SET_CHAR_COORDINATES scplayer -813.833 459.110 998.989
ENDIF

SET_CAMERA_BEHIND_PLAYER
SET_POLICE_IGNORE_PLAYER player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 ON

DELETE_CAR jet_casino10
MARK_MODEL_AS_NO_LONGER_NEEDED SHAMAL

//// checks to see if the player has a gun and if not gives him one then aims it at the mafia goons. 
//IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
//	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL 60
//	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL
//ELSE
//	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL
//ENDIF

CREATE_CAR TAXI -811.9990 457.0270 1356.6222 cutscene_car_casino10
SET_CAR_HEADING cutscene_car_casino10 270.0 
SET_VEHICLE_AREA_VISIBLE cutscene_car_casino10 1
SET_CAR_ONLY_DAMAGED_BY_PLAYER cutscene_car_casino10 TRUE
CREATE_CHAR_INSIDE_CAR cutscene_car_casino10 PEDTYPE_CIVMALE VMAFF1 taxi_driver_casino10
SET_CHAR_AREA_VISIBLE taxi_driver_casino10 1
CAR_SET_IDLE cutscene_car_casino10 
WARP_CHAR_INTO_CAR_AS_PASSENGER scplayer cutscene_car_casino10 1

CREATE_CAR SENTINEL -729.2895 514.4813 1370.9688 cutscene_car2_casino10
SET_CAR_HEADING cutscene_car2_casino10 161.5196
SET_VEHICLE_AREA_VISIBLE cutscene_car2_casino10 1
SET_CAR_ONLY_DAMAGED_BY_PLAYER cutscene_car2_casino10 TRUE
CREATE_CHAR_INSIDE_CAR cutscene_car2_casino10 PEDTYPE_CIVMALE VMAFF1 car2_driver_casino10
SET_CHAR_AREA_VISIBLE car2_driver_casino10 1
CAR_SET_IDLE cutscene_car2_casino10

WHILE NOT IS_CAR_WAITING_FOR_WORLD_COLLISION cutscene_car_casino10
	
	WAIT 0

	IF IS_CAR_DEAD cutscene_car_casino10

	ENDIF

	IF IS_CAR_DEAD cutscene_car2_casino10
		
	ENDIF
	 
ENDWHILE

// creates dummy peds walking about
CREATE_CHAR PEDTYPE_CIVMALE BMYBU -864.653 451.569 1348.584 dummy1_casino10
TASK_WANDER_STANDARD dummy1_casino10 

CREATE_CHAR PEDTYPE_CIVFEMALE WFYBU -884.251 466.693 1346.577 dummy2_casino10
TASK_WANDER_STANDARD dummy2_casino10

CREATE_CHAR PEDTYPE_CIVMALE BMYBU -857.588 467.419 1349.290 dummy3_casino10
TASK_WANDER_STANDARD dummy3_casino10

CREATE_CHAR PEDTYPE_CIVFEMALE WFYBU -814.460 466.873 1356.137 dummy4_casino10
TASK_WANDER_STANDARD dummy4_casino10

CREATE_CHAR PEDTYPE_CIVMALE BMYBU -781.615 468.628 1363.266 dummy5_casino10 
TASK_WANDER_STANDARD dummy5_casino10

CREATE_CHAR PEDTYPE_CIVFEMALE WFYBU -737.431 503.600 1370.968 dummy6_casino10
TASK_WANDER_STANDARD dummy6_casino10

CREATE_CHAR PEDTYPE_CIVMALE BMYBU -738.769 476.826 1370.306 dummy7_casino10 
TASK_WANDER_STANDARD dummy7_casino10

CREATE_CHAR PEDTYPE_CIVFEMALE WFYBU -771.993 456.237 1364.445 dummy8_casino10
TASK_WANDER_STANDARD dummy8_casino10

CREATE_CHAR PEDTYPE_CIVMALE BMYBU -799.539 453.030 1359.074 dummy9_casino10 
TASK_WANDER_STANDARD dummy9_casino10

CREATE_CHAR PEDTYPE_CIVFEMALE WFYBU -753.076 484.357 1369.885 dummy10_casino10 
TASK_WANDER_STANDARD dummy10_casino10

SET_FIXED_CAMERA_POSITION -751.2452 449.8578 1370.5004 0.0 0.0 0.0
POINT_CAMERA_AT_POINT -752.1519 450.2614 1370.3782 JUMP_CUT

IF NOT IS_CAR_DEAD cutscene_car_casino10
	START_PLAYBACK_RECORDED_CAR cutscene_car_casino10 602
	SET_PLAYBACK_SPEED cutscene_car_casino10 1.1
ENDIF

IF NOT IS_CAR_DEAD cutscene_car2_casino10
	START_PLAYBACK_RECORDED_CAR cutscene_car2_casino10 603
	SET_PLAYBACK_SPEED cutscene_car2_casino10 1.1
ENDIF

WAIT 800

IF IS_CAR_DEAD cutscene_car_casino10
	STOP_PLAYBACK_RECORDED_CAR cutscene_car_casino10
ENDIF

IF IS_CAR_DEAD cutscene_car2_casino10
	STOP_PLAYBACK_RECORDED_CAR cutscene_car2_casino10
ENDIF

SET_FADING_COLOUR 0 0 0

SWITCH_WIDESCREEN ON

DO_FADE 2000 FADE_IN

CAMERA_RESET_NEW_SCRIPTABLES
CAMERA_PERSIST_TRACK TRUE                   
CAMERA_PERSIST_POS TRUE
CAMERA_SET_VECTOR_MOVE -751.2452 449.8578 1370.5004 -730.0718 483.3791 1373.4719 8600 TRUE
CAMERA_SET_VECTOR_TRACK -752.1519 450.2614 1370.3782 -730.9146 483.9166 1373.4963 8600 TRUE
                       
WHILE GET_FADING_STATUS

	WAIT 0
	
ENDWHILE

SET_PLAYER_IS_IN_STADIUM TRUE
HIDE_ALL_FRONTEND_BLIPS TRUE

IF IS_CAR_DEAD cutscene_car_casino10
	STOP_PLAYBACK_RECORDED_CAR cutscene_car_casino10
ENDIF

IF IS_CAR_DEAD cutscene_car2_casino10
	STOP_PLAYBACK_RECORDED_CAR cutscene_car2_casino10
ENDIF

SKIP_CUTSCENE_START

WHILE IS_PLAYBACK_GOING_ON_FOR_CAR cutscene_car_casino10 

	WAIT 0

	IF IS_CAR_DEAD cutscene_car_casino10
		STOP_PLAYBACK_RECORDED_CAR cutscene_car_casino10
	ENDIF

	IF IS_CAR_DEAD cutscene_car2_casino10
		STOP_PLAYBACK_RECORDED_CAR cutscene_car2_casino10
	ENDIF
	
ENDWHILE

OPEN_SEQUENCE_TASK player_liberty_sequence
		
	IF NOT IS_CAR_DEAD cutscene_car_casino10 
		TASK_LEAVE_CAR -1 cutscene_car_casino10
	ENDIF

	TASK_GO_STRAIGHT_TO_COORD -1 -760.2046 492.1819 1370.6766 PEDMOVE_WALK 10000

CLOSE_SEQUENCE_TASK player_liberty_sequence

PERFORM_SEQUENCE_TASK scplayer player_liberty_sequence
CLEAR_SEQUENCE_TASK player_liberty_sequence  

TIMERA = 0

WHILE TIMERA < 3000

	WAIT 0

	IF IS_CAR_DEAD cutscene_car_casino10

	ENDIF

	IF IS_CAR_DEAD cutscene_car2_casino10

	ENDIF
	 
ENDWHILE

SKIP_CUTSCENE_END

SET_FADING_COLOUR 0 0 0  

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

//SWITCH_PED_ROADS_OFF -908.229 445.396 1342.0 -772.180 473.524 1370.0 // St. Marks Bistro

SET_WEATHER_TO_APPROPRIATE_TYPE_NOW

CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
CLEAR_SEQUENCE_TASK player_liberty_sequence

IF NOT IS_CAR_DEAD cutscene_car_casino10
	STOP_PLAYBACK_RECORDED_CAR cutscene_car_casino10

	IF IS_CHAR_IN_CAR scplayer cutscene_car_casino10 
		WARP_CHAR_FROM_CAR_TO_COORD scplayer -760.2046 492.1819 1370.6766

		WHILE IS_CHAR_IN_ANY_CAR scplayer

			WAIT 0

			IF IS_CAR_DEAD cutscene_car_casino10
			
			ENDIF
			
			IF IS_CAR_DEAD cutscene_car2_casino10
			
			ENDIF
			
			IF IS_CHAR_DEAD taxi_driver_casino10
			
			ENDIF
			
			IF IS_CHAR_DEAD car2_driver_casino10 
			
			ENDIF   

		ENDWHILE

	ENDIF

ENDIF

IF NOT IS_CAR_DEAD cutscene_car2_casino10
	STOP_PLAYBACK_RECORDED_CAR cutscene_car2_casino10
ENDIF

// checks to see if the player has a gun and if not gives him one then aims it at the mafia goons. 
IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PISTOL
	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PISTOL 60
	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL
ELSE
	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PISTOL
ENDIF

REMOVE_CAR_RECORDING 602
REMOVE_CAR_RECORDING 603 

CAMERA_RESET_NEW_SCRIPTABLES

DELETE_CAR cutscene_car_casino10
DELETE_CHAR taxi_driver_casino10
DELETE_CAR cutscene_car2_casino10
DELETE_CHAR car2_driver_casino10

DELETE_CHAR dummy1_casino10
DELETE_CHAR dummy2_casino10
DELETE_CHAR dummy3_casino10
DELETE_CHAR dummy4_casino10
DELETE_CHAR dummy5_casino10
DELETE_CHAR dummy6_casino10
DELETE_CHAR dummy7_casino10
DELETE_CHAR dummy8_casino10
DELETE_CHAR dummy9_casino10
DELETE_CHAR dummy10_casino10 

MARK_MODEL_AS_NO_LONGER_NEEDED BMYBU
MARK_MODEL_AS_NO_LONGER_NEEDED WFYBU
MARK_MODEL_AS_NO_LONGER_NEEDED TAXI

REQUEST_MODEL VMAFF1 	 
REQUEST_MODEL VMAFF2
REQUEST_MODEL VMAFF3	  
REQUEST_MODEL CHROMEGUN
REQUEST_MODEL MICRO_UZI
REQUEST_MODEL COLT45
REQUEST_ANIMATION SWAT
REQUEST_MODEL SENTINEL

LOAD_SPECIAL_CHARACTER 1 FORELLI

LOAD_MISSION_AUDIO 1 SOUND_CAS11DA 
LOAD_MISSION_AUDIO 2 SOUND_CAS11DB

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED VMAFF1
OR NOT HAS_MODEL_LOADED VMAFF2
OR NOT HAS_ANIMATION_LOADED SWAT
OR NOT HAS_MODEL_LOADED CHROMEGUN
OR NOT HAS_MODEL_LOADED MICRO_UZI
OR NOT HAS_SPECIAL_CHARACTER_LOADED 1

 	WAIT 0

ENDWHILE

WHILE NOT HAS_MODEL_LOADED COLT45
OR NOT HAS_MODEL_LOADED VMAFF3
OR NOT HAS_MODEL_LOADED SENTINEL
OR NOT HAS_MISSION_AUDIO_LOADED 1
OR NOT HAS_MISSION_AUDIO_LOADED 2
	 
	WAIT 0

ENDWHILE

// Marco Forelli
CREATE_CHAR PEDTYPE_MISSION1 SPECIAL01 -783.620 505.055 1370.788 forelli_casino10 // -791.560 491.675 370.782
SET_CHAR_ALLOWED_TO_DUCK forelli_casino10 FALSE
GIVE_WEAPON_TO_CHAR forelli_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_WEAPON_SKILL forelli_casino10 WEAPONSKILL_PRO
SET_CHAR_USES_UPPERBODY_DAMAGE_ANIMS_ONLY forelli_casino10 TRUE
SET_CHAR_HEADING forelli_casino10 348.440
ADD_BLIP_FOR_CHAR forelli_casino10 blip_forelli_casino10
SET_CHAR_DECISION_MAKER forelli_casino10 empty_dm_casino10
SET_CHAR_SUFFERS_CRITICAL_HITS forelli_casino10 FALSE
SET_CHAR_MAX_HEALTH forelli_casino10 1000
SET_CHAR_HEALTH forelli_casino10 1000
SET_CHAR_ACCURACY forelli_casino10 80
SET_CHAR_SHOOT_RATE forelli_casino10 50

// BodyGuards
CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -783.223 497.549 1370.780 bodyguard1_casino10  // behind the bar
SET_CHAR_ALLOWED_TO_DUCK bodyguard1_casino10 FALSE
GIVE_WEAPON_TO_CHAR bodyguard1_casino10 WEAPONTYPE_SHOTGUN 30000 // Set to infinate ammo
SET_CHAR_HEADING bodyguard1_casino10 348.440
SET_CHAR_DECISION_MAKER bodyguard1_casino10 dm_duck_casino10
SET_CHAR_ACCURACY bodyguard1_casino10 65
SET_CHAR_SHOOT_RATE bodyguard1_casino10 40

CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 -788.965 497.522 1370.780 bodyguard2_casino10 // behind the bar
SET_CHAR_ALLOWED_TO_DUCK bodyguard2_casino10 FALSE
GIVE_WEAPON_TO_CHAR bodyguard2_casino10 WEAPONTYPE_SHOTGUN 30000 // Set to infinate ammo
SET_CHAR_HEADING bodyguard2_casino10 348.440
SET_CHAR_DECISION_MAKER bodyguard2_casino10 dm_duck_casino10
SET_CHAR_ACCURACY bodyguard2_casino10 65
SET_CHAR_SHOOT_RATE bodyguard2_casino10 45

CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 -791.034 500.973 1370.780 goon1_casino10
SET_CHAR_ALLOWED_TO_DUCK goon1_casino10 FALSE
GIVE_WEAPON_TO_CHAR goon1_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING goon1_casino10 5.1
SET_CHAR_DECISION_MAKER goon1_casino10 tough_dm_casino10
SET_CHAR_ACCURACY goon1_casino10 70
SET_CHAR_SHOOT_RATE goon1_casino10 50

CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -780.395 500.947 1370.780 goon2_casino10
SET_CHAR_ALLOWED_TO_DUCK goon2_casino10 FALSE
GIVE_WEAPON_TO_CHAR goon2_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING goon2_casino10 5.1
SET_CHAR_DECISION_MAKER goon2_casino10 tough_dm_casino10
SET_CHAR_ACCURACY goon2_casino10 40
SET_CHAR_SHOOT_RATE goon2_casino10 60 

// Two goons who weapon crouch up the stairs
CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 -794.945 507.819 1370.788 goon3_casino10
SET_CHAR_ALLOWED_TO_DUCK goon3_casino10 FALSE
GIVE_WEAPON_TO_CHAR goon3_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING goon3_casino10	174.837
SET_CHAR_DECISION_MAKER goon3_casino10 tough_dm_casino10
SET_CHAR_ACCURACY goon3_casino10 70
SET_CHAR_SHOOT_RATE goon3_casino10 45

CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 -793.000 506.434 1370.788 goon4_casino10
SET_CHAR_ALLOWED_TO_DUCK goon4_casino10 FALSE
GIVE_WEAPON_TO_CHAR goon4_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING goon4_casino10 113.867
SET_CHAR_DECISION_MAKER goon4_casino10 tough_dm_casino10
SET_CHAR_ACCURACY goon4_casino10 65
SET_CHAR_SHOOT_RATE goon4_casino10 40

// two guys who run and take cover by the tables
CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 -785.853 508.372 1370.774 goon5_casino10  // runs to a table on the ground floor -781.255 509.456 1370.780
SET_CHAR_ALLOWED_TO_DUCK goon5_casino10 FALSE
GIVE_WEAPON_TO_CHAR goon5_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING goon5_casino10 212.0
SET_CHAR_DECISION_MAKER goon5_casino10 tough_dm_casino10
SET_CHAR_ACCURACY goon5_casino10 65
SET_CHAR_SHOOT_RATE goon5_casino10 40

CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -792.191 508.908 1370.788 goon6_casino10 // runs to a table on the ground floor
SET_CHAR_ALLOWED_TO_DUCK goon6_casino10 FALSE
GIVE_WEAPON_TO_CHAR goon6_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING goon6_casino10 188.0
SET_CHAR_DECISION_MAKER goon6_casino10 tough_dm_casino10
SET_CHAR_ACCURACY goon6_casino10 70
SET_CHAR_SHOOT_RATE goon6_casino10 40

// **********************************Top Floor Guards
// Two guards chatting on the toplevel
CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 -783.975 498.564 1375.195 top_goon1_casino10 // guy who player shoots
SET_CHAR_ALLOWED_TO_DUCK top_goon1_casino10 FALSE
GIVE_WEAPON_TO_CHAR top_goon1_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING top_goon1_casino10	172.352
SET_CHAR_DECISION_MAKER top_goon1_casino10 tough_dm_casino10
SET_CHAR_ACCURACY top_goon1_casino10 65
SET_CHAR_SHOOT_RATE top_goon1_casino10 40

CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -792.542 494.229 1375.235 top_goon2_casino10 // leaning on far wall smoking
SET_CHAR_ALLOWED_TO_DUCK top_goon2_casino10 FALSE
GIVE_WEAPON_TO_CHAR top_goon2_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING top_goon2_casino10 279.542
SET_CHAR_DECISION_MAKER top_goon2_casino10 tough_dm_casino10
SET_CHAR_ACCURACY top_goon2_casino10 70
SET_CHAR_SHOOT_RATE top_goon2_casino10 50

// peeks out and shoots
CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 -786.142 491.815 1375.235 top_goon3_casino10 // guy who will peek out and shoot
SET_CHAR_ALLOWED_TO_DUCK top_goon3_casino10 FALSE
GIVE_WEAPON_TO_CHAR top_goon3_casino10 WEAPONTYPE_SHOTGUN 30000 // Set to infinate ammo
SET_CHAR_HEADING top_goon3_casino10 270.0
SET_CHAR_DECISION_MAKER top_goon3_casino10 empty_dm_casino10
SET_CHAR_ACCURACY top_goon3_casino10 75
SET_CHAR_SHOOT_RATE top_goon3_casino10 40

CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -793.700 494.999 1375.243 top_goon4_casino10 // guy who will peek out and shoot
SET_CHAR_ALLOWED_TO_DUCK top_goon4_casino10 FALSE
GIVE_WEAPON_TO_CHAR top_goon4_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING top_goon4_casino10 270.0
SET_CHAR_DECISION_MAKER top_goon4_casino10 empty_dm_casino10
SET_CHAR_ACCURACY top_goon4_casino10 65
SET_CHAR_SHOOT_RATE top_goon4_casino10 40

// corridor guards
CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -781.141 494.625 1367.571 corridor_goon1_casino10 // guy who will peek out and shoot
SET_CHAR_ALLOWED_TO_DUCK corridor_goon1_casino10 FALSE
GIVE_WEAPON_TO_CHAR corridor_goon1_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING corridor_goon1_casino10 90.0
SET_CHAR_DECISION_MAKER corridor_goon1_casino10 tough_dm_casino10
SET_CHAR_ACCURACY corridor_goon1_casino10 65
SET_CHAR_SHOOT_RATE corridor_goon1_casino10 40

CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 -786.705 501.671 1366.414 corridor_goon2_casino10 // guy who will peek out and shoot
SET_CHAR_ALLOWED_TO_DUCK corridor_goon2_casino10 FALSE
GIVE_WEAPON_TO_CHAR corridor_goon2_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING corridor_goon2_casino10 270.0
SET_CHAR_DECISION_MAKER corridor_goon2_casino10 tough_dm_casino10
SET_CHAR_ACCURACY corridor_goon2_casino10 65
SET_CHAR_SHOOT_RATE corridor_goon2_casino10 40

CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 -790.646 505.521 1366.414 corridor_goon3_casino10 // guy who crouches behind the stove in kitchen
SET_CHAR_ALLOWED_TO_DUCK corridor_goon3_casino10 FALSE
GIVE_WEAPON_TO_CHAR corridor_goon3_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING corridor_goon3_casino10 231.0
SET_CHAR_DECISION_MAKER corridor_goon3_casino10 dm_duck_casino10
SET_CHAR_ACCURACY corridor_goon3_casino10 65
SET_CHAR_SHOOT_RATE corridor_goon3_casino10 50

// stuff to full compiler with
GOTO fool_complier_casino10

CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 -816.005 493.329 1358.293 end_goon1_casino10
DELETE_CHAR end_goon1_casino10

CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -821.268 508.167 1358.273 end_goon2_casino10
DELETE_CHAR end_goon2_casino10

CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 -828.770 510.594 1357.307 end_goon3_casino10
DELETE_CHAR end_goon3_casino10

fool_complier_casino10: 

// player is standing in the bistro
CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
SET_CHAR_COORDINATES scplayer -783.104 490.814 1375.235
SET_CHAR_HEADING scplayer 8.832
LOAD_SCENE_IN_DIRECTION -783.104 490.814 1375.235 8.832

SET_FIXED_CAMERA_POSITION -787.9748 493.9206 1375.7960 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -787.0560 494.3102 1375.8591 JUMP_CUT

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_IN

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

player_entered_bistro_casino10 = 1

SKIP_CUTSCENE_START

// player walks into the room and looks at the guards
TASK_GO_STRAIGHT_TO_COORD scplayer -783.153 495.215 1375.227 PEDMOVE_WALK -1

IF NOT IS_CHAR_DEAD top_goon1_casino10
	TASK_LOOK_AT_CHAR scplayer top_goon1_casino10 -1
ELSE

	IF top_goon1_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ENDIF

ENDIF
   
IF NOT IS_CHAR_DEAD top_goon2_casino10
	TASK_LOOK_AT_CHAR top_goon2_casino10 scplayer -1
ELSE

	IF top_goon2_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon2_dead_casino10 = 1
	ENDIF

ENDIF

OPEN_SEQUENCE_TASK top_goon1_task2_casino10 // right
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 -783.336 496.837 1375.235 PEDMOVE_WALK -1
	TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer 
	TASK_LOOK_AT_CHAR -1 scplayer -1
	TASK_AIM_GUN_AT_CHAR -1 scplayer 15000 
CLOSE_SEQUENCE_TASK top_goon1_task2_casino10

IF NOT IS_CHAR_DEAD top_goon1_casino10
	PERFORM_SEQUENCE_TASK top_goon1_casino10 top_goon1_task2_casino10
	CLEAR_SEQUENCE_TASK top_goon1_task2_casino10
ELSE

	IF top_goon1_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ENDIF

ENDIF

IF NOT IS_CHAR_DEAD top_goon1_casino10 
	PLAY_MISSION_AUDIO 1
	PRINT_NOW (CAS11DA) 10000 1 //Private function, invitation only!
	START_CHAR_FACIAL_TALK top_goon1_casino10 999999
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH top_goon1_casino10 TRUE
ELSE
	CLEAR_MISSION_AUDIO 1

	IF top_goon1_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ENDIF

ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 1

	WAIT 0

	GOSUB baddie_death_checks_casino10

	IF top_goon1_dead_casino10 = 0 
		
		IF IS_CHAR_DEAD top_goon1_casino10 
			CLEAR_MISSION_AUDIO 1
			++ counter_dead_casino10
			top_goon1_dead_casino10 = 1
		ENDIF

	ENDIF

ENDWHILE

CLEAR_THIS_PRINT (CAS11DA)

IF NOT IS_CHAR_DEAD top_goon1_casino10
	STOP_CHAR_FACIAL_TALK top_goon1_casino10
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH top_goon1_casino10 FALSE
	TASK_STAY_IN_SAME_PLACE scplayer TRUE
 
ELSE

	IF top_goon1_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ENDIF
	
ENDIF

PLAY_MISSION_AUDIO 2
PRINT_NOW (CAS11DB) 4000 1 //Oh yeah? Well Mr. Leone says otherwise!"
START_CHAR_FACIAL_TALK scplayer 999999
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

// player jumps over the rail
SET_FIXED_CAMERA_POSITION -785.9136 497.3071 1375.5786 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT -785.0042 496.9065 1375.6900 JUMP_CUT

IF NOT IS_CHAR_DEAD top_goon1_casino10
	TASK_TOGGLE_DUCK top_goon1_casino10 TRUE 
	SET_CHAR_ACCURACY top_goon1_casino10 5
  	TASK_SHOOT_AT_COORD top_goon1_casino10 -781.079 495.708 1376.195 10000
  	SET_CHAR_SHOOT_RATE top_goon1_casino10 80 
ELSE

	IF top_goon1_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ENDIF

ENDIF

OPEN_SEQUENCE_TASK player_leap_casino10	
	TASK_ACHIEVE_HEADING -1 270.0
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 JMP_Wall1m_180 SWAT 4.0 FALSE FALSE FALSE TRUE -1
	return_animation_time_casino10 = 0.0
CLOSE_SEQUENCE_TASK player_leap_casino10

PERFORM_SEQUENCE_TASK scplayer player_leap_casino10																		  	

IF IS_CHAR_PLAYING_ANIM scplayer JMP_Wall1m_180 				
	GET_CHAR_ANIM_CURRENT_TIME scplayer JMP_Wall1m_180 return_animation_time_casino10
ENDIF

WHILE NOT return_animation_time_casino10 = 1.0

	WAIT 0
	 
	IF IS_CHAR_PLAYING_ANIM scplayer JMP_Wall1m_180 				
		GET_CHAR_ANIM_CURRENT_TIME scplayer JMP_Wall1m_180 return_animation_time_casino10
	ENDIF
	
	GOSUB baddie_death_checks_casino10   	 
	
ENDWHILE
 
// two guards on bottom level run to the tables to take cover
OPEN_SEQUENCE_TASK goon5_task_casino10
	TASK_TOGGLE_DUCK -1 TRUE
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 -781.563 505.346 1370.782 PEDMOVE_RUN -1 
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK goon5_task_casino10

IF NOT IS_CHAR_DEAD goon5_casino10
	PERFORM_SEQUENCE_TASK goon5_casino10 goon5_task_casino10
	CLEAR_SEQUENCE_TASK goon5_task_casino10
ELSE

	IF flag_goon5_dead_casino10 = 0 
		++ counter_dead_casino10
		flag_goon5_dead_casino10 = 1
	ENDIF

ENDIF

OPEN_SEQUENCE_TASK goon6_task_casino10
	TASK_TOGGLE_DUCK -1 TRUE
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 -786.511 505.073 1370.782 PEDMOVE_RUN -1 
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer	
CLOSE_SEQUENCE_TASK goon6_task_casino10

IF NOT IS_CHAR_DEAD goon6_casino10
	PERFORM_SEQUENCE_TASK goon6_casino10 goon6_task_casino10
	CLEAR_SEQUENCE_TASK goon6_task_casino10
ELSE

	IF flag_goon6_dead_casino10 = 0 
		++ counter_dead_casino10
		flag_goon6_dead_casino10 = 1
	ENDIF

ENDIF

WHILE NOT HAS_MISSION_AUDIO_FINISHED 2

	WAIT 0

	GOSUB baddie_death_checks_casino10

	IF IS_CHAR_DEAD scplayer 
		CLEAR_MISSION_AUDIO 2
   	ENDIF

ENDWHILE

CLEAR_THIS_PRINT (CAS11DB)
STOP_CHAR_FACIAL_TALK scplayer
SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

// guard is shot and falls over rail, guard2 runs for his position
OPEN_SEQUENCE_TASK top_goon2_task2_casino10 // right
	TASK_TOGGLE_DUCK -1 TRUE
	TASK_STAY_IN_SAME_PLACE -1 TRUE 
	TASK_KILL_CHAR_ON_FOOT  -1 scplayer
CLOSE_SEQUENCE_TASK top_goon2_task2_casino10

IF NOT IS_CHAR_DEAD top_goon2_casino10
	PERFORM_SEQUENCE_TASK top_goon2_casino10 top_goon2_task2_casino10
	CLEAR_SEQUENCE_TASK top_goon2_task2_casino10
ELSE

	IF top_goon2_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon2_dead_casino10 = 1
	ENDIF

ENDIF

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2

SWITCH_WIDESCREEN OFF

CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
SET_CHAR_COORDINATES scplayer -780.806 495.717 1375.0
SET_CHAR_HEADING scplayer 100.0
TASK_TOGGLE_DUCK scplayer TRUE
TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer WEAPON_CROUCH PED 1000.0 FALSE FALSE FALSE FALSE -1
RESTORE_CAMERA_JUMPCUT

CLEAR_SEQUENCE_TASK player_leap_casino10

flag_played_cut_casino10 = 1

SKIP_CUTSCENE_END

IF flag_played_cut_casino10 = 0

	CLEAR_PRINTS
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2

	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

	OPEN_SEQUENCE_TASK goon5_task_casino10
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 -781.563 505.346 1370.782 PEDMOVE_RUN -1 
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 scplayer	
	CLOSE_SEQUENCE_TASK goon5_task_casino10

	IF NOT IS_CHAR_DEAD goon5_casino10
		PERFORM_SEQUENCE_TASK goon5_casino10 goon5_task_casino10
		CLEAR_SEQUENCE_TASK goon5_task_casino10
	ELSE
		
		IF flag_goon5_dead_casino10 = 0 
			++ counter_dead_casino10
			flag_goon5_dead_casino10 = 1
		ENDIF
		
	ENDIF

	OPEN_SEQUENCE_TASK goon6_task_casino10
		TASK_TOGGLE_DUCK -1 TRUE
		TASK_FOLLOW_PATH_NODES_TO_COORD -1 -786.511 505.073 1370.782 PEDMOVE_RUN -1 
		TASK_STAY_IN_SAME_PLACE -1 TRUE
		TASK_KILL_CHAR_ON_FOOT -1 scplayer	
	CLOSE_SEQUENCE_TASK goon6_task_casino10

	IF NOT IS_CHAR_DEAD goon6_casino10
		PERFORM_SEQUENCE_TASK goon6_casino10 goon6_task_casino10
		CLEAR_SEQUENCE_TASK goon6_task_casino10
	ELSE

		IF flag_goon6_dead_casino10 = 0 
			++ counter_dead_casino10
			flag_goon6_dead_casino10 = 1
		ENDIF

	ENDIF

	CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
	SET_CHAR_COORDINATES scplayer -780.806 495.717 1375.0
	SET_CHAR_HEADING scplayer 100.0

//	TASK_TOGGLE_DUCK scplayer TRUE
//	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer WEAPON_CROUCH PED 1000.0 FALSE FALSE FALSE FALSE -1
 
	RESTORE_CAMERA_JUMPCUT
ENDIF

SWITCH_WIDESCREEN OFF
SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT
SET_PLAYER_CONTROL player1 ON
SET_POLICE_IGNORE_PLAYER player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 OFF
SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE

IF NOT IS_CHAR_DEAD top_goon1_casino10
	SET_CHAR_ACCURACY top_goon1_casino10 40
	
	IF top_goon1_kill_player_casino10 = 0
		SET_CHAR_SHOOT_RATE top_goon1_casino10 40
		TASK_STAY_IN_SAME_PLACE top_goon1_casino10 FALSE 
		TASK_KILL_CHAR_ON_FOOT top_goon1_casino10 scplayer
		top_goon1_kill_player_casino10 = 1 
	ENDIF

ELSE

	IF top_goon1_dead_casino10 = 0 
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ENDIF

ENDIF

MAKE_PLAYER_GANG_REAPPEAR

// Checks to see where the player is before ordering guys to attack
IF player_entered_bistro_casino10 = 1

	IF IS_CHAR_IN_AREA_3D scplayer -778.0 486.0 1370.0 -797.0 500.0 1380.0 FALSE // top area
				
		IF NOT IS_CHAR_DEAD goon3_casino10 // runs up the stairs in weapon crouch

			IF goon3_kill_player_casino10 = 0
				enemy_actor1_casino10 = goon3_casino10 
				GOSUB kill_player1_task
				goon3_kill_player_casino10 = 1
			ENDIF

		ELSE

			IF flag_goon3_dead_casino10 = 0 
				++ counter_dead_casino10
				flag_goon3_dead_casino10 = 1
			ENDIF
			 
		ENDIF

		IF NOT IS_CHAR_DEAD goon4_casino10 // runs up the stairs in weapon crouch

			IF goon4_kill_player_casino10 = 0
				enemy_actor2_casino10 = goon4_casino10 
				GOSUB kill_player2_task
				goon4_kill_player_casino10 = 1
			ENDIF

		ELSE

			IF flag_goon4_dead_casino10 = 0 
				++ counter_dead_casino10
				flag_goon4_dead_casino10 = 1
			ENDIF
			 
		ENDIF

	ELSE

		IF NOT IS_CHAR_DEAD goon3_casino10 // runs up the stairs in weapon crouch

			IF goon3_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS goon3_casino10
				TASK_STAY_IN_SAME_PLACE goon3_casino10 FALSE
				TASK_KILL_CHAR_ON_FOOT goon3_casino10 scplayer
				goon3_kill_player_casino10 = 1
			ENDIF

		ELSE

			IF flag_goon3_dead_casino10 = 0 
				++ counter_dead_casino10
				flag_goon3_dead_casino10 = 1
			ENDIF

		ENDIF

		IF NOT IS_CHAR_DEAD goon4_casino10 // runs up the stairs in weapon crouch

			IF goon4_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS goon4_casino10
				TASK_STAY_IN_SAME_PLACE goon4_casino10 FALSE
				TASK_KILL_CHAR_ON_FOOT goon4_casino10 scplayer
				goon4_kill_player_casino10 = 1
			ENDIF

		ELSE

			IF flag_goon4_dead_casino10 = 0 
				++ counter_dead_casino10
				flag_goon4_dead_casino10 = 1
			ENDIF

		ENDIF

	ENDIF

ENDIF

PRINT_NOW (CM10_2) 10000 1 //"Kill Forelli and his bodyguards."

WHILE NOT counter_dead_casino10 = 19

	WAIT 0

	IF flag_mid_cleanup_done_casino10 = 0

		IF IS_CHAR_IN_AREA_3D scplayer -777.097 501.709 1365.0 -784.992 497.660 1370.0 FALSE
			GOSUB mid_mission_cleanup_casino10

			IF end_guys_created_casino10 = 0
				GOSUB create_end_guys_casino10 
			ENDIF

		ENDIF

	ENDIF

	IF end_guys_created_casino10 = 1
		GOSUB end_guys_death_check_casino10
	ENDIF

	GOSUB baddie_death_checks_casino10

// sets up these guys to attack player at the top of the stairs
	
	IF goon1_attack_player_casino10 = 0
			
		IF IS_CHAR_IN_AREA_3D scplayer -778.0 486.0 1370.0 -797.0 500.0 1380.0 FALSE // top area

			IF flag_goon3_dead_casino10 = 1
			
				IF NOT IS_CHAR_DEAD goon1_casino10
					enemy_actor1_casino10 = goon1_casino10 
					GOSUB kill_player1_task
					goon1_attack_player_casino10 = 1
				ELSE

					IF flag_goon1_dead_casino10 = 0
						++ counter_dead_casino10
						flag_goon1_dead_casino10 = 1
					ENDIF
					 
				ENDIF

			ENDIF

		ELSE

			IF NOT IS_CHAR_DEAD goon1_casino10
				CLEAR_CHAR_TASKS goon1_casino10
				TASK_STAY_IN_SAME_PLACE goon1_casino10 FALSE
				TASK_KILL_CHAR_ON_FOOT goon1_casino10 scplayer
				goon1_attack_player_casino10 = 2
			ELSE

				IF flag_goon1_dead_casino10 = 0
					++ counter_dead_casino10
					flag_goon1_dead_casino10 = 1
				ENDIF
				 
			ENDIF

		ENDIF

	ENDIF

	IF goon2_attack_player_casino10 = 0

		IF IS_CHAR_IN_AREA_3D scplayer -778.0 486.0 1370.0 -797.0 500.0 1380.0 FALSE // top area

			IF flag_goon4_dead_casino10 = 1

				IF NOT IS_CHAR_DEAD goon2_casino10
					enemy_actor2_casino10 = goon2_casino10 
					GOSUB kill_player2_task
					goon2_attack_player_casino10 = 1
				ELSE

					IF flag_goon2_dead_casino10 = 0
						++ counter_dead_casino10
						flag_goon2_dead_casino10 = 1
					ENDIF
					 
				ENDIF

			ENDIF

		ELSE

			IF NOT IS_CHAR_DEAD goon2_casino10
				CLEAR_CHAR_TASKS goon2_casino10
				TASK_STAY_IN_SAME_PLACE goon2_casino10 FALSE
				TASK_KILL_CHAR_ON_FOOT goon2_casino10 scplayer
				goon2_attack_player_casino10 = 2
			ELSE

				IF flag_goon2_dead_casino10 = 0
					++ counter_dead_casino10
					flag_goon2_dead_casino10 = 1
				ENDIF
				 
			ENDIF

		ENDIF

	ENDIF

	IF corridor_goon1_kill_player_casino10 = 0

		IF NOT IS_CHAR_DEAD corridor_goon1_casino10

			IF IS_CHAR_IN_AREA_3D scplayer -792.344 489.644 1370.782 -783.436 493.662 1366.0 FALSE // 369.782
				enemy_actor3_casino10 = corridor_goon1_casino10 
				GOSUB roll_left_task_casino10
				corridor_goon1_kill_player_casino10 = 1 
			ENDIF

		ELSE
			
			IF corridor_goon1_dead_casino10 = 0
				++ counter_dead_casino10
				corridor_goon1_dead_casino10 = 1
			ENDIF

		ENDIF
	   	
	ENDIF

	IF corridor_goon2_kill_player_casino10 = 0

		IF NOT IS_CHAR_DEAD corridor_goon2_casino10

			IF IS_CHAR_IN_AREA_3D scplayer -777.097 501.709 1365.0 -784.992 497.660 1370.0 FALSE
				enemy_actor4_casino10 = corridor_goon2_casino10 
				GOSUB roll_right_task_casino10
				corridor_goon2_kill_player_casino10 = 1 
			ENDIF

		ELSE

			IF corridor_goon2_dead_casino10 = 0
				++ counter_dead_casino10
				corridor_goon2_dead_casino10 = 1
			ENDIF

		ENDIF
	   	
	ENDIF

	IF corridor_goon3_kill_player_casino10 = 0

		IF NOT IS_CHAR_DEAD corridor_goon3_casino10

			IF IS_CHAR_IN_AREA_3D scplayer -777.097 501.709 1365.0 -784.992 497.660 1370.0 FALSE
				CLEAR_CHAR_TASKS corridor_goon3_casino10
				TASK_STAY_IN_SAME_PLACE corridor_goon3_casino10 TRUE  
				TASK_KILL_CHAR_ON_FOOT corridor_goon3_casino10 scplayer
				corridor_goon3_kill_player_casino10 = 1 
			ENDIF

		ELSE

			IF corridor_goon3_dead_casino10 = 0
				++ counter_dead_casino10
				corridor_goon3_dead_casino10 = 1
			ENDIF

		ENDIF
	   	
	ENDIF 

// forelli runs
	IF IS_PLAYER_PLAYING player1
		GET_CHAR_COORDINATES scplayer player_x player_y player_z
	ENDIF

	IF flag_forelli_dead_casino10 = 0

		IF IS_CHAR_DEAD forelli_casino10
			REMOVE_BLIP blip_forelli_casino10 
			++ counter_dead_casino10 
			flag_forelli_dead_casino10 = 1
		ELSE

			GET_CHAR_HEALTH forelli_casino10 forelli_health_casino10

			IF forelli_run_count_casino10 = 0
				//SET_CHAR_NEVER_TARGETTED forelli_casino10 TRUE
				CLEAR_CHAR_RELATIONSHIP forelli_casino10 ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				CLEAR_CHAR_TASKS_IMMEDIATELY forelli_casino10
				TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -781.090 497.790 1370.782 PEDMOVE_RUN 10000
				forelli_run_count_casino10 = 1
			ENDIF

			IF forelli_run_count_casino10 = 1
			
			 	IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -781.090 497.790 1370.782 0.5 0.5 2.0 FALSE
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -791.749 491.447 1370.782 PEDMOVE_RUN 10000
					forelli_run_count_casino10 = 2
				ENDIF
				
			ENDIF
			
			IF forelli_run_count_casino10 = 2

				IF IS_CHAR_IN_AREA_ON_FOOT_3D scplayer -777.827 510.0 1369.0 -797.0 494.0 1373.0 FALSE
					player_downstairs_casino10 = 1	
				ENDIF
							
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -791.749 491.447 1370.782 0.5 0.5 2.0 FALSE			  	 
					
					IF time_forelli_casino10 = 0
						SET_CHAR_HEADING forelli_casino10 0.0
						TASK_STAY_IN_SAME_PLACE forelli_casino10 TRUE
						TASK_KILL_CHAR_ON_FOOT forelli_casino10 scplayer
						TIMERB = 0
						time_forelli_casino10 = 1
					ENDIF

					IF player_downstairs_casino10 = 1

						IF time_forelli_casino10 = 1

							IF TIMERB >= 6000
							OR forelli_health_casino10 <= 800
								CLEAR_CHAR_TASKS forelli_casino10
								TASK_STAY_IN_SAME_PLACE forelli_casino10 FALSE  
								TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -779.844 492.670 1367.616 PEDMOVE_RUN 10000
								forelli_run_count_casino10 = 3
							ENDIF					

						ENDIF

					ENDIF

				ENDIF

			ENDIF
						
			IF forelli_run_count_casino10 = 3
			
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -779.844 492.670 1367.616 0.5 0.5 2.0 FALSE
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -779.844 499.811 1367.571 PEDMOVE_RUN 10000				
					forelli_run_count_casino10 = 4
				ENDIF
				
			ENDIF
			
			IF forelli_run_count_casino10 = 4

				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -779.844 499.811 1367.571 0.75 0.75 2.0 FALSE

					IF forelli_health_casino10 > 99
						SET_CHAR_HEALTH forelli_casino10 100
					ENDIF
					
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -787.237 499.939 1366.414 PEDMOVE_RUN 10000
					forelli_run_count_casino10 = 5
				ENDIF
								
			ENDIF
			
			IF forelli_run_count_casino10 = 5
			
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -787.237 499.939 1366.414 0.5 0.5 2.0 FALSE 	
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -787.543 508.034 1366.414 PEDMOVE_RUN 10000
					forelli_run_count_casino10 = 6
				ENDIF
				
			ENDIF
			
			IF forelli_run_count_casino10 = 6
			
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -787.543 508.034 1366.414 0.5 0.5 2.0 FALSE
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -791.552 507.859 1366.414 PEDMOVE_RUN 10000	 	
					forelli_run_count_casino10 = 7
				ENDIF
				
			ENDIF
			
			IF forelli_run_count_casino10 = 7
				
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -791.552 507.859 1366.414 0.5 0.5 2.0 FALSE
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -791.045 498.883 1366.414 PEDMOVE_RUN 10000	
					forelli_run_count_casino10 = 8
				ENDIF
				
				
			ENDIF
			
			IF forelli_run_count_casino10 = 8
			
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -791.045 498.883 1366.414 0.5 0.5 2.0 FALSE
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -791.810 492.473 1366.414 PEDMOVE_RUN 10000
					forelli_run_count_casino10 = 9
				ENDIF	
												
			ENDIF
			
			IF forelli_run_count_casino10 = 9
			
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -791.810 492.473 1366.414 0.5 0.5 2.0 FALSE 	
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -798.194 492.462 1366.274 PEDMOVE_RUN 10000
					forelli_run_count_casino10 = 10
				ENDIF
				
			ENDIF

			IF forelli_run_count_casino10 = 10
				
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -798.194 492.462 1366.274 0.5 0.5 2.0 FALSE // top of back stairs 	
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -799.081 503.710 1361.913 PEDMOVE_RUN 10000
					forelli_run_count_casino10 = 11
				ENDIF
				
			ENDIF
			
			IF forelli_run_count_casino10 = 11
				
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -799.081 503.710 1361.913 0.5 0.5 2.0 FALSE // bottom of stairs 	
					TASK_FOLLOW_PATH_NODES_TO_COORD forelli_casino10 -821.412 504.901 1357.35 PEDMOVE_RUN 10000
					forelli_run_count_casino10 = 12
				ENDIF
				
			ENDIF
			
			IF forelli_run_count_casino10 = 12
				
				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -821.412 504.901 1357.358 0.5 0.5 2.0 FALSE // middle of the yard
				
					OPEN_SEQUENCE_TASK forelli_end_casino10
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -830.889 501.107 1357.374 PEDMOVE_RUN -1
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 -830.711 498.369 1357.385 PEDMOVE_RUN -1
						TASK_ACHIEVE_HEADING -1 260.984
					CLOSE_SEQUENCE_TASK forelli_end_casino10 
					
					IF NOT IS_CHAR_DEAD forelli_casino10
						PERFORM_SEQUENCE_TASK forelli_casino10 forelli_end_casino10
						CLEAR_SEQUENCE_TASK forelli_end_casino10 
						forelli_run_count_casino10 = 13
					ELSE
						REMOVE_BLIP blip_forelli_casino10
						++ counter_dead_casino10
						flag_forelli_dead_casino10 = 1
					ENDIF

				ENDIF
				
			ENDIF
			
			IF forelli_run_count_casino10 = 13

				IF LOCATE_CHAR_ON_FOOT_3D forelli_casino10 -830.711 498.369 1357.385 0.5 0.5 2.0 FALSE 

					IF NOT IS_CHAR_DEAD forelli_casino10
						TASK_STAY_IN_SAME_PLACE forelli_casino10 TRUE
						TASK_TOGGLE_DUCK forelli_casino10 TRUE
						TASK_KILL_CHAR_ON_FOOT forelli_casino10 scplayer

//						IF forelli_health_casino10 > 99
//							SET_CHAR_HEALTH forelli_casino10 50
//						ENDIF

	 					forelli_run_count_casino10 = 14
					ELSE
						REMOVE_BLIP blip_forelli_casino10
						++ counter_dead_casino10
						flag_forelli_dead_casino10 = 1
					ENDIF

				ENDIF
			
			ENDIF 
			 			
		ENDIF
		
	ENDIF
			
ENDWHILE

WAIT 3000

SET_PLAYER_CONTROL player1 OFF
SET_EVERYONE_IGNORE_PLAYER player1 ON
SET_POLICE_IGNORE_PLAYER player1 ON
//SWITCH_WIDESCREEN ON

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

MAKE_PLAYER_GANG_DISAPPEAR

IF flag_mid_cleanup_done_casino10 = 0
	GOSUB mid_mission_cleanup_casino10
ENDIF

DELETE_CHAR corridor_goon1_casino10
DELETE_CHAR corridor_goon2_casino10
DELETE_CHAR corridor_goon3_casino10

DELETE_CHAR end_goon1_casino10
DELETE_CHAR end_goon2_casino10
DELETE_CHAR end_goon3_casino10

DELETE_CAR car2_casino10
MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED TAXI
MARK_MODEL_AS_NO_LONGER_NEEDED BMYBU
MARK_MODEL_AS_NO_LONGER_NEEDED WFYBU

REMOVE_ANIMATION SWAT

REMOVE_CHAR_ELEGANTLY forelli_casino10

UNLOAD_SPECIAL_CHARACTER 1

// need to request all of the models at the airport to get streaming working
REQUEST_MODEL SHAMAL

REQUEST_CAR_RECORDING 601

LOAD_ALL_MODELS_NOW

WHILE NOT HAS_MODEL_LOADED SHAMAL
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 601 

	WAIT 0

ENDWHILE

SET_AREA_VISIBLE 0 // normal world
SET_PLAYER_IS_IN_STADIUM FALSE
SET_CHAR_AREA_VISIBLE scplayer 0

CLEAR_HELP
CLEAR_PRINTS

CREATE_CAR SHAMAL 7500.0 2478.34 200.0 jet_casino10 
PLANE_STARTS_IN_AIR jet_casino10
SET_CAR_ONLY_DAMAGED_BY_PLAYER jet_casino10 TRUE
SET_CAR_PROOFS jet_casino10 TRUE TRUE TRUE TRUE TRUE
SET_CAR_HEADING jet_casino10 110.0 // used for plane if player has to fly back himself
SET_PLANE_UNDERCARRIAGE_UP jet_casino10 TRUE
WARP_CHAR_INTO_CAR scplayer jet_casino10

START_PLAYBACK_RECORDED_CAR jet_casino10 601

SET_FIXED_CAMERA_POSITION 8867.6367 1581.8903 193.1880 0.0 0.0 0.0 
POINT_CAMERA_AT_POINT 8867.6055 1582.8831 193.0727 JUMP_CUT

SWITCH_WIDESCREEN ON 

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

SKIP_CUTSCENE_START

TIMERA = 0

WHILE TIMERA < 2800

	WAIT 0

	IF IS_CAR_DEAD jet_casino10
		PRINT_NOW (CM10_17) 8000 1 //"You have destroyed the jet!"
		GOTO mission_casino10_failed
	ENDIF

ENDWHILE

SKIP_CUTSCENE_END

SET_FADING_COLOUR  0 0 0
DO_FADE 500 FADE_OUT

WHILE GET_FADING_STATUS 

	WAIT 0
	
ENDWHILE

IF NOT IS_CAR_DEAD jet_casino10

	IF IS_PLAYBACK_GOING_ON_FOR_CAR jet_casino10 
		STOP_PLAYBACK_RECORDED_CAR jet_casino10
	ENDIF

ENDIF

REMOVE_CAR_RECORDING 601

// this stuff is used if player is to fly the jet back
IF NOT IS_CAR_DEAD jet_casino10
	SET_CAR_COORDINATES jet_casino10 7500.0 2478.34 200.0
	FREEZE_CAR_POSITION jet_casino10 TRUE  
	PLANE_STARTS_IN_AIR jet_casino10
	SET_CAR_ONLY_DAMAGED_BY_PLAYER jet_casino10 FALSE
	SET_CAR_PROOFS jet_casino10 FALSE FALSE FALSE FALSE FALSE
	SET_CAR_HEADING jet_casino10 110.0 // used for plane if player has to fly back himself
	SET_PLANE_UNDERCARRIAGE_UP jet_casino10 TRUE
ENDIF

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

MAKE_PLAYER_GANG_REAPPEAR

SET_FADING_COLOUR 0 0 0

HIDE_ALL_FRONTEND_BLIPS FALSE

SET_MAX_WANTED_LEVEL 6

DO_FADE 500 FADE_IN

SWITCH_WIDESCREEN OFF // used for player flying back himself

WHILE GET_FADING_STATUS

	WAIT 0

ENDWHILE

SET_PLAYER_CONTROL player1 ON
SET_EVERYONE_IGNORE_PLAYER player1 OFF
SET_POLICE_IGNORE_PLAYER player1 OFF

IF NOT IS_CAR_DEAD jet_casino10
	FREEZE_CAR_POSITION jet_casino10 FALSE
	SET_CAR_FORWARD_SPEED jet_casino10 30.0
ELSE
	PRINT_NOW (CM10_17) 8000 1 //"You have destroyed the jet!"
	GOTO mission_casino10_failed
ENDIF

PRINT_NOW (CM10_19) 8000 1 //"Fly back to San Fierro!"
ADD_BLIP_FOR_COORD 1477.225 1640.364 9.860 landing_blip_casino10

SET_ALWAYS_DRAW_3D_MARKERS TRUE

casino10_index = 0
casino10_audio_is_playing = 0
casino10_cutscene_flag = 0
casino10_chat_switch = casino10_CHAT2
GOSUB casino10_chat_switch

had_plane_message_casino10 = 0                                             

WHILE NOT IS_CAR_STOPPED_IN_AREA_3D jet_casino10 1636.3195 1144.4778 7.0 1261.4111 1780.6724 14.0 FALSE  //LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer 1477.225 1640.364 9.860 10.0 10.0 10.0 TRUE 
OR NOT IS_CHAR_IN_AREA_3D scplayer 1636.3195 1144.4778 7.0 1261.4111 1780.6724 14.0 FALSE 
//OR NOT IS_CHAR_SITTING_IN_CAR scplayer jet_casino10

	WAIT 0

	IF IS_CAR_DEAD jet_casino10
		CLEAR_MISSION_AUDIO 1
		CLEAR_MISSION_AUDIO 2
		CLEAR_PRINTS
		PRINT_NOW (CM10_17) 8000 1 //"You have destroyed the jet!"
		GOTO mission_casino10_failed
	ELSE

		IF IS_CHAR_IN_CAR scplayer jet_casino10

			IF LOCATE_CHAR_IN_CAR_3D scplayer 1477.225 1640.364 9.860 350.0 350.0 500.0 FALSE
				GOSUB load_and_play_audio_casino10
			ENDIF

			IF NOT IS_CAR_STOPPED_IN_AREA_3D jet_casino10 1636.3195 1144.4778 7.0 1261.4111 1780.6724 14.0 FALSE
					   
				IF had_plane_message_casino10 = 1  
					PRINT_NOW (CM10_19) 8000 1 //"Fly back to San Fierro!"
					ADD_BLIP_FOR_COORD 1477.225 1640.364 9.860 landing_blip_casino10
					REMOVE_BLIP jet_blip_casino10 
					blob_flag = 1
					had_plane_message_casino10 = 0
				ENDIF

			ENDIF
	
		ELSE

			CLEAR_MISSION_AUDIO 1
			CLEAR_MISSION_AUDIO 2

			IF NOT IS_CAR_STOPPED_IN_AREA_3D jet_casino10 1636.3195 1144.4778 7.0 1261.4111 1780.6724 14.0 FALSE
			
				IF had_plane_message_casino10 = 0
					REMOVE_BLIP landing_blip_casino10
					ADD_BLIP_FOR_CAR jet_casino10 jet_blip_casino10
					SET_BLIP_AS_FRIENDLY airport_blip_casino10 TRUE
					PRINT_NOW (IN_VEH) 5000 1 //"Get back in the helicopter!"
					blob_flag = 0 
					had_plane_message_casino10 = 1
				ENDIF
				
			ENDIF 

		ENDIF
				
	ENDIF

ENDWHILE

REMOVE_BLIP landing_blip_casino10
REMOVE_BLIP jet_blip_casino10

SET_ALWAYS_DRAW_3D_MARKERS FALSE

CLEAR_MISSION_AUDIO 1
CLEAR_MISSION_AUDIO 2
CLEAR_PRINTS
 
GOTO mission_casino10_passed

 // **************************************** Mission casino10 failed ***********************

mission_casino10_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
 	   	   
IF IS_CHAR_IN_WATER scplayer
OR IS_CAR_IN_WATER jet_casino10   
	SET_UP_SKIP_AFTER_MISSION 2149.609 1681.117 9.820 90.0
ENDIF

IF IS_CHAR_IN_AIR scplayer
	
	IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
		
		IF NOT IS_PLAYER_USING_JETPACK player1

			IF NOT player_fall_state > 0
				SET_UP_SKIP_AFTER_MISSION 2149.609 1681.117 9.820 90.0
			ENDIF
				
		ENDIF
			
	ENDIF
		
ENDIF

RETURN

// **************************************** mission casino10 passed ************************

mission_casino10_passed:

flag_casino_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
REGISTER_MISSION_PASSED (CASIN10) //Used in the stats
PLAYER_MADE_PROGRESS 1 
PRINT_WITH_NUMBER_BIG (M_PASSS) 20000 5000 1 //"Mission Passed!"
ADD_SCORE player1 20000
AWARD_PLAYER_MISSION_RESPECT 35 //amount of respect
CLEAR_WANTED_LEVEL player1
PLAY_MISSION_PASSED_TUNE 1

REMOVE_BLIP heist_contact_blip

SET_INT_STAT CITIES_PASSED 3
START_NEW_SCRIPT mansion_mission_loop
REMOVE_BLIP casino_contact_blip
ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
SET_BLIP_ENTRY_EXIT casino_contact_blip 2026.6028 1007.7353 20.0

TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOBLA2
START_NEW_SCRIPT cell_phone_LA2

SET_INT_STAT PASSED_CASINO10 1

RETURN	

// ********************************** mission cleanup ***********************************
mission_cleanup_casino10:

SET_MAX_WANTED_LEVEL 6

//SWITCH_PED_ROADS_OFF -908.229 445.396 1342.0 -772.180 473.524 1370.0 // St. Marks Bistro

SET_WANTED_MULTIPLIER 1.0

REMOVE_CHAR_ELEGANTLY forelli_casino10

flag_player_on_mission = 0
GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission

SWITCH_ROADS_BACK_TO_ORIGINAL 2095.350 1734.179 8.0 2337.495 1524.991 15.0 // outside casino
SWITCH_PED_ROADS_BACK_TO_ORIGINAL 2095.350 1734.179 8.0 2337.495 1524.991 15.0 // outside casino 
 
CLEAR_SEQUENCE_TASK top_goon1_task2_casino10
CLEAR_SEQUENCE_TASK top_goon2_task2_casino10

CLEAR_SEQUENCE_TASK task_kill_player_casino10
CLEAR_SEQUENCE_TASK task_kill2_player_casino10

CLEAR_SEQUENCE_TASK peekright_casino10

CLEAR_SEQUENCE_TASK player_liberty_sequence

CLEAR_SEQUENCE_TASK goon5_task_casino10
CLEAR_SEQUENCE_TASK goon6_task_casino10

CLEAR_SEQUENCE_TASK roll_left_casino10
CLEAR_SEQUENCE_TASK roll_right_casino10

CLEAR_SEQUENCE_TASK forelli_end_casino10
 
REMOVE_BLIP blip_forelli_casino10
REMOVE_BLIP airport_blip_casino10
REMOVE_BLIP airport_blip2_casino10
REMOVE_BLIP liberty_blip_casino10
REMOVE_BLIP jet_blip_casino10
REMOVE_BLIP landing_blip_casino10
 
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF1
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF2
MARK_MODEL_AS_NO_LONGER_NEEDED VMAFF3
MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED SHAMAL
MARK_MODEL_AS_NO_LONGER_NEEDED SENTINEL
MARK_MODEL_AS_NO_LONGER_NEEDED TAXI
MARK_MODEL_AS_NO_LONGER_NEEDED BMYBU
MARK_MODEL_AS_NO_LONGER_NEEDED WFYBU

 UNLOAD_SPECIAL_CHARACTER 1
 UNLOAD_SPECIAL_CHARACTER 2
 UNLOAD_SPECIAL_CHARACTER 3

REMOVE_DECISION_MAKER tough_dm_casino10
REMOVE_DECISION_MAKER empty_dm_casino10

REMOVE_CAR_RECORDING 601
REMOVE_CAR_RECORDING 602
REMOVE_CAR_RECORDING 603

REMOVE_ANIMATION SWAT

SET_ALWAYS_DRAW_3D_MARKERS FALSE

SET_PLAYER_IS_IN_STADIUM FALSE

MISSION_HAS_FINISHED
 
RETURN

// Checks to see who has been killed
baddie_death_checks_casino10:

IF flag_forelli_dead_casino10 = 0

	IF IS_CHAR_DEAD forelli_casino10
		REMOVE_BLIP blip_forelli_casino10
		++ counter_dead_casino10 
		flag_forelli_dead_casino10 = 1
	ENDIF

ENDIF

IF flag_bodyguard1_dead_casino10 = 0

	IF IS_CHAR_DEAD bodyguard1_casino10
		++ counter_dead_casino10 
		flag_bodyguard1_dead_casino10 = 1
	ELSE

		IF player_entered_bistro_casino10 = 1

			IF flag_forelli_dead_casino10 = 0

				IF bodyguard1_kill_player_casino10 = 0
				
					IF NOT IS_CHAR_IN_AREA_3D scplayer -796.172 488.580 1370.0 -777.902 498.668 1378.235 FALSE // upstairs level
						CLEAR_CHAR_TASKS bodyguard1_casino10 
						TASK_STAY_IN_SAME_PLACE bodyguard1_casino10 TRUE 
						TASK_KILL_CHAR_ON_FOOT bodyguard1_casino10 scplayer
						bodyguard1_kill_player_casino10 = 1
					ENDIF
				
				ENDIF

			ELSE

				IF bodyguard1_kill_player_casino10 = 1
					TASK_STAY_IN_SAME_PLACE bodyguard1_casino10 FALSE 
					bodyguard1_kill_player_casino10 = 2
				ELSE
					
					IF NOT bodyguard1_kill_player_casino10 = 2
						CLEAR_CHAR_TASKS bodyguard1_casino10
						TASK_STAY_IN_SAME_PLACE bodyguard1_casino10 FALSE 
						TASK_KILL_CHAR_ON_FOOT bodyguard1_casino10 scplayer
						bodyguard1_kill_player_casino10 = 2
					ENDIF

				ENDIF

			ENDIF
			
		ENDIF	 

	ENDIF

ENDIF

IF flag_bodyguard2_dead_casino10 = 0

	IF IS_CHAR_DEAD bodyguard2_casino10
		++ counter_dead_casino10  
		flag_bodyguard2_dead_casino10 = 1
	ELSE

		IF player_entered_bistro_casino10 = 1

			IF flag_forelli_dead_casino10 = 0

				IF bodyguard2_kill_player_casino10 = 0
			
					IF NOT IS_CHAR_IN_AREA_3D scplayer -796.172 488.580 1370.0 -777.902 498.668 1378.235 FALSE // upstairs level
						CLEAR_CHAR_TASKS bodyguard2_casino10
						TASK_STAY_IN_SAME_PLACE bodyguard2_casino10 TRUE 
						TASK_KILL_CHAR_ON_FOOT bodyguard2_casino10 scplayer
						bodyguard2_kill_player_casino10 = 1
					ENDIF

				ENDIF

			ELSE

				IF bodyguard2_kill_player_casino10 = 1
					TASK_STAY_IN_SAME_PLACE bodyguard2_casino10 FALSE 
					bodyguard2_kill_player_casino10 = 2
				ELSE

					IF NOT bodyguard2_kill_player_casino10 = 2 
						CLEAR_CHAR_TASKS bodyguard2_casino10
						TASK_STAY_IN_SAME_PLACE bodyguard2_casino10 FALSE 
						TASK_KILL_CHAR_ON_FOOT bodyguard2_casino10 scplayer
						bodyguard2_kill_player_casino10 = 2
					ENDIF

				ENDIF

			ENDIF
		
		ENDIF


	ENDIF

ENDIF

IF flag_goon1_dead_casino10 = 0

	IF IS_CHAR_DEAD goon1_casino10
		++ counter_dead_casino10  
		flag_goon1_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF goon1_attack_player_casino10 < 2
				TASK_STAY_IN_SAME_PLACE goon1_casino10 FALSE
				goon1_attack_player_casino10 = 2
			ENDIF

		ENDIF

	ENDIF

ENDIF

IF flag_goon2_dead_casino10 = 0

	IF IS_CHAR_DEAD goon2_casino10
		++ counter_dead_casino10  
		flag_goon2_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF goon2_attack_player_casino10 < 2
				TASK_STAY_IN_SAME_PLACE goon2_casino10 FALSE
				goon2_attack_player_casino10 = 2
			ENDIF

		ENDIF

	ENDIF

ENDIF

IF flag_goon3_dead_casino10 = 0

	IF IS_CHAR_DEAD goon3_casino10
		++ counter_dead_casino10  
		flag_goon3_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF goon3_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS goon3_casino10
				TASK_STAY_IN_SAME_PLACE goon3_casino10 FALSE 
				TASK_KILL_CHAR_ON_FOOT goon3_casino10 scplayer
				goon3_kill_player_casino10 = 1
			ELSE
				
				IF goon3_kill_player_casino10 < 2
					TASK_STAY_IN_SAME_PLACE goon3_casino10 FALSE
					goon3_kill_player_casino10 = 2
				ENDIF		

			ENDIF

		ENDIF

	ENDIF


ENDIF

IF flag_goon4_dead_casino10 = 0

	IF IS_CHAR_DEAD goon4_casino10
		++ counter_dead_casino10  
		flag_goon4_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF goon4_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS goon4_casino10
				TASK_STAY_IN_SAME_PLACE goon4_casino10 FALSE
				TASK_KILL_CHAR_ON_FOOT goon4_casino10 scplayer
				goon4_kill_player_casino10 = 1
			ELSE

				IF goon4_kill_player_casino10 < 2
					TASK_STAY_IN_SAME_PLACE goon4_casino10 FALSE
					goon4_kill_player_casino10 = 2
				ENDIF

			ENDIF

		ENDIF

	ENDIF


ENDIF

IF flag_goon5_dead_casino10 = 0

	IF IS_CHAR_DEAD goon5_casino10
		++ counter_dead_casino10  
		flag_goon5_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF goon5_kill_player_casino10 = 0
				TASK_STAY_IN_SAME_PLACE goon5_casino10 FALSE
				goon5_kill_player_casino10 = 1
			ENDIF

		ENDIF

	ENDIF


ENDIF

IF flag_goon6_dead_casino10 = 0

	IF IS_CHAR_DEAD goon6_casino10
		++ counter_dead_casino10  
		flag_goon6_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF goon6_kill_player_casino10 = 0
				TASK_STAY_IN_SAME_PLACE goon6_casino10 FALSE
				goon6_kill_player_casino10 = 1
			ENDIF

		ENDIF

	ENDIF
	
ENDIF

IF top_goon1_dead_casino10 = 0

	IF IS_CHAR_DEAD top_goon1_casino10
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ELSE
		
		IF flag_forelli_dead_casino10 = 1

			IF top_goon1_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS top_goon1_casino10
				TASK_STAY_IN_SAME_PLACE top_goon1_casino10 FALSE 
				TASK_KILL_CHAR_ON_FOOT top_goon1_casino10 scplayer
				top_goon1_kill_player_casino10 = 1
			ENDIF

		ENDIF

	ENDIF

ENDIF

IF top_goon2_dead_casino10 = 0

	IF IS_CHAR_DEAD top_goon2_casino10
		++ counter_dead_casino10
		top_goon2_dead_casino10 = 1
	ELSE

		IF player_entered_bistro_casino10 = 1

			IF flag_forelli_dead_casino10 = 0

				IF top_goon2_kill_player_casino10 = 0

					IF NOT IS_CHAR_IN_AREA_3D scplayer -778.0 486.0 1370.0 -797.0 500.0 1380.0 FALSE // top area
						CLEAR_CHAR_TASKS top_goon2_casino10
						TASK_STAY_IN_SAME_PLACE top_goon2_casino10 FALSE
						TASK_KILL_CHAR_ON_FOOT top_goon2_casino10 scplayer
						top_goon2_kill_player_casino10 = 1
					ENDIF

				ENDIF

			ELSE
				
				IF top_goon2_kill_player_casino10 = 0
					CLEAR_CHAR_TASKS top_goon2_casino10 
					TASK_STAY_IN_SAME_PLACE top_goon2_casino10 FALSE
					TASK_KILL_CHAR_ON_FOOT top_goon2_casino10 scplayer
					top_goon2_kill_player_casino10 = 1
				ENDIF

			ENDIF

		ENDIF

	ENDIF

ENDIF

IF top_goon3_dead_casino10 = 0

	IF IS_CHAR_DEAD top_goon3_casino10
		++ counter_dead_casino10
		top_goon3_dead_casino10 = 1
	ELSE

		IF player_entered_bistro_casino10 = 1

			IF flag_forelli_dead_casino10 = 0

				IF IS_CHAR_IN_AREA_3D scplayer -781.646 498.659 1374.0 -780.146 487.315 1378.0 FALSE

					IF flag_animate_top_goon3_casino10 = 0
						enemy_actor_casino10 = top_goon3_casino10
						GOSUB peek_sequence_casino10  
						flag_animate_top_goon3_casino10 = 1	
					ENDIF
					
					IF flag_animate_top_goon3_casino10 = 1
						
						IF HAS_CHAR_BEEN_DAMAGED_BY_WEAPON top_goon3_casino10 WEAPONTYPE_ANYWEAPON 
							
							IF flag_animate_top_goon3_casino10 = 1
								CLEAR_CHAR_TASKS top_goon3_casino10 
								TASK_STAY_IN_SAME_PLACE top_goon3_casino10 FALSE
								TASK_KILL_CHAR_ON_FOOT top_goon3_casino10 scplayer
								flag_animate_top_goon3_casino10 = 2
							ENDIF
							
						ENDIF
						
					ENDIF			

				ELSE

					IF flag_animate_top_goon3_casino10 = 1
						CLEAR_CHAR_TASKS top_goon3_casino10
						TASK_STAY_IN_SAME_PLACE top_goon3_casino10 FALSE
						TASK_KILL_CHAR_ON_FOOT top_goon3_casino10 scplayer
						flag_animate_top_goon3_casino10 = 2
					ENDIF
					 

				ENDIF

			ELSE

				IF NOT flag_animate_top_goon3_casino10 = 2
					CLEAR_CHAR_TASKS top_goon3_casino10
					TASK_STAY_IN_SAME_PLACE top_goon3_casino10 FALSE
					TASK_KILL_CHAR_ON_FOOT top_goon3_casino10 scplayer
					flag_animate_top_goon3_casino10 = 2
				ENDIF

			ENDIF

		ENDIF

	ENDIF

ENDIF

IF top_goon4_dead_casino10 = 0

	IF IS_CHAR_DEAD top_goon4_casino10
		++ counter_dead_casino10
		top_goon4_dead_casino10 = 1
	ELSE
	   
		IF player_entered_bistro_casino10 = 1

			IF flag_forelli_dead_casino10 = 0

				IF IS_CHAR_IN_AREA_3D scplayer -781.646 498.659 1374.0 -780.146 487.315 1378.0 FALSE
					  
					IF flag_animate_top_goon4_casino10 = 0
						TIMERA = 0
						flag_animate_top_goon4_casino10 = 1
					ENDIF

					IF flag_animate_top_goon4_casino10 = 1

						IF TIMERA >= 800
							enemy_actor_casino10 = top_goon4_casino10
							GOSUB peek_sequence_casino10
							flag_animate_top_goon4_casino10 = 2
						ENDIF

					ENDIF

					IF flag_animate_top_goon4_casino10 = 1
						
						IF HAS_CHAR_BEEN_DAMAGED_BY_WEAPON top_goon4_casino10 WEAPONTYPE_ANYWEAPON 
							
							IF flag_animate_top_goon4_casino10 < 3
								CLEAR_CHAR_TASKS top_goon4_casino10
								TASK_STAY_IN_SAME_PLACE top_goon4_casino10 FALSE
								TASK_KILL_CHAR_ON_FOOT top_goon4_casino10 scplayer
								flag_animate_top_goon4_casino10 = 3
							ENDIF
							
						ENDIF
						
					ENDIF

				ELSE

					IF flag_animate_top_goon4_casino10 = 2
						CLEAR_CHAR_TASKS top_goon4_casino10
						TASK_STAY_IN_SAME_PLACE top_goon4_casino10 FALSE
						TASK_KILL_CHAR_ON_FOOT top_goon4_casino10 scplayer
						flag_animate_top_goon4_casino10 = 3
					ENDIF
					 

				ENDIF

			ELSE

				IF flag_animate_top_goon4_casino10 < 3
					CLEAR_CHAR_TASKS top_goon4_casino10
					TASK_STAY_IN_SAME_PLACE top_goon4_casino10 FALSE
					TASK_KILL_CHAR_ON_FOOT top_goon4_casino10 scplayer
					flag_animate_top_goon4_casino10 = 3
				ENDIF
				
			ENDIF

		ENDIF

	ENDIF

ENDIF

IF corridor_goon1_dead_casino10 = 0

	IF IS_CHAR_DEAD corridor_goon1_casino10
		++ counter_dead_casino10
		corridor_goon1_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF corridor_goon1_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS corridor_goon1_casino10
				TASK_STAY_IN_SAME_PLACE corridor_goon1_casino10 FALSE 
				TASK_KILL_CHAR_ON_FOOT corridor_goon1_casino10 scplayer
				corridor_goon1_kill_player_casino10 = 1
			ELSE
				
				IF corridor_goon1_kill_player_casino10 < 2 
					TASK_STAY_IN_SAME_PLACE corridor_goon1_casino10 FALSE
					corridor_goon1_kill_player_casino10 = 2
				ENDIF

			ENDIF

		ENDIF

	ENDIF

ENDIF

IF corridor_goon2_dead_casino10 = 0

	IF IS_CHAR_DEAD corridor_goon2_casino10
		++ counter_dead_casino10
		corridor_goon2_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF corridor_goon2_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS corridor_goon2_casino10
				TASK_STAY_IN_SAME_PLACE corridor_goon2_casino10 FALSE 
				TASK_KILL_CHAR_ON_FOOT corridor_goon2_casino10 scplayer
				corridor_goon2_kill_player_casino10 = 1
			ELSE

				IF corridor_goon2_kill_player_casino10 < 2
					TASK_STAY_IN_SAME_PLACE corridor_goon2_casino10 FALSE 
					corridor_goon2_kill_player_casino10 = 2
				ENDIF
				
			ENDIF	 

		ENDIF

	ENDIF

ENDIF

IF corridor_goon3_dead_casino10 = 0

	IF IS_CHAR_DEAD corridor_goon3_casino10
		++ counter_dead_casino10
		corridor_goon3_dead_casino10 = 1
	ELSE

		IF flag_forelli_dead_casino10 = 1

			IF corridor_goon3_kill_player_casino10 = 0
				CLEAR_CHAR_TASKS corridor_goon3_casino10
				TASK_STAY_IN_SAME_PLACE corridor_goon3_casino10 FALSE 
				TASK_KILL_CHAR_ON_FOOT corridor_goon3_casino10 scplayer
				corridor_goon3_kill_player_casino10 = 1
			ELSE

				IF corridor_goon3_kill_player_casino10 < 2
					TASK_STAY_IN_SAME_PLACE corridor_goon3_casino10 FALSE 
					corridor_goon3_kill_player_casino10 = 2
				ENDIF

			ENDIF

		ENDIF

	ENDIF

ENDIF

RETURN

// tells the guards to peek
peek_sequence_casino10:
OPEN_SEQUENCE_TASK peekright_casino10
	TASK_PLAY_ANIM -1 swt_wllpk_R SWAT 8.0 FALSE FALSE FALSE FALSE -1
	TASK_PLAY_ANIM -1 swt_wllshoot_in_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT_TIMED -1 scplayer 1500
	TASK_STAY_IN_SAME_PLACE -1 FALSE
	TASK_PLAY_ANIM -1 swt_wllshoot_out_R SWAT 8.0 FALSE TRUE TRUE FALSE -1
	TASK_PLAY_ANIM -1 swt_wllpk_R_back SWAT 8.0 FALSE FALSE FALSE FALSE -1
	SET_SEQUENCE_TO_REPEAT peekright_casino10 1
CLOSE_SEQUENCE_TASK peekright_casino10

PERFORM_SEQUENCE_TASK enemy_actor_casino10 peekright_casino10
CLEAR_SEQUENCE_TASK peekright_casino10

RETURN

// kill player tasks
kill_player1_task:
OPEN_SEQUENCE_TASK task_kill_player_casino10
	TASK_TOGGLE_DUCK -1 TRUE                   
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 -788.611 497.731 1375.227 PEDMOVE_RUN -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK task_kill_player_casino10

IF NOT IS_CHAR_DEAD enemy_actor1_casino10
	PERFORM_SEQUENCE_TASK enemy_actor1_casino10 task_kill_player_casino10
ENDIF

CLEAR_SEQUENCE_TASK task_kill_player_casino10

RETURN

kill_player2_task:
OPEN_SEQUENCE_TASK task_kill2_player_casino10
	TASK_TOGGLE_DUCK -1 TRUE                   
	TASK_FOLLOW_PATH_NODES_TO_COORD -1 -789.542 495.862 1376.235 PEDMOVE_RUN -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK task_kill2_player_casino10


IF NOT IS_CHAR_DEAD enemy_actor2_casino10
	PERFORM_SEQUENCE_TASK enemy_actor2_casino10 task_kill2_player_casino10
ENDIF

CLEAR_SEQUENCE_TASK task_kill2_player_casino10

RETURN

//  roll out for enemies
roll_left_task_casino10:
OPEN_SEQUENCE_TASK roll_left_casino10
	TASK_PLAY_ANIM -1 Crouch_Roll_L PED 8.0 FALSE TRUE TRUE FALSE -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_TOGGLE_DUCK -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK roll_left_casino10

IF NOT IS_CHAR_DEAD enemy_actor3_casino10 
	PERFORM_SEQUENCE_TASK enemy_actor3_casino10 roll_left_casino10
ENDIF

CLEAR_SEQUENCE_TASK roll_left_casino10
RETURN

//  roll out for enemies
roll_right_task_casino10:
OPEN_SEQUENCE_TASK roll_right_casino10
	TASK_PLAY_ANIM -1 Crouch_Roll_R PED 8.0 FALSE TRUE TRUE FALSE -1
	TASK_STAY_IN_SAME_PLACE -1 TRUE
	TASK_TOGGLE_DUCK -1 TRUE
	TASK_KILL_CHAR_ON_FOOT -1 scplayer
CLOSE_SEQUENCE_TASK roll_right_casino10

IF NOT IS_CHAR_DEAD enemy_actor4_casino10 
	PERFORM_SEQUENCE_TASK enemy_actor4_casino10 roll_right_casino10
ENDIF

CLEAR_SEQUENCE_TASK roll_right_casino10
RETURN

// cleans up the guards so I can put in more guards for the ending
mid_mission_cleanup_casino10:

IF flag_bodyguard1_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD bodyguard1_casino10
		DELETE_CHAR bodyguard1_casino10
		++ counter_dead_casino10
		flag_bodyguard1_dead_casino10 = 1
	ENDIF

ENDIF

IF flag_bodyguard2_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD bodyguard2_casino10
		DELETE_CHAR bodyguard2_casino10
		++ counter_dead_casino10
		flag_bodyguard2_dead_casino10 = 1
	ENDIF

ENDIF

IF flag_goon1_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD goon1_casino10
		DELETE_CHAR goon1_casino10
		++ counter_dead_casino10
		flag_goon1_dead_casino10 = 1
	ENDIF

ENDIF

IF flag_goon2_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD goon2_casino10
		DELETE_CHAR goon2_casino10
		++ counter_dead_casino10
		flag_goon2_dead_casino10 = 1
	ENDIF

ENDIF

IF flag_goon3_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD goon3_casino10
		DELETE_CHAR goon3_casino10
		++ counter_dead_casino10
		flag_goon3_dead_casino10 = 1
	ENDIF

ENDIF

IF flag_goon4_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD goon4_casino10
		DELETE_CHAR goon4_casino10
		++ counter_dead_casino10
		flag_goon4_dead_casino10 = 1
	ENDIF

ENDIF

IF flag_goon5_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD goon5_casino10
		DELETE_CHAR goon5_casino10
		++ counter_dead_casino10
		flag_goon5_dead_casino10 = 1
	ENDIF
	
ENDIF

IF flag_goon6_dead_casino10 = 0   

	IF NOT IS_CHAR_DEAD goon6_casino10
		DELETE_CHAR goon6_casino10
		++ counter_dead_casino10
		flag_goon6_dead_casino10 = 1
	ENDIF

ENDIF

IF top_goon1_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD top_goon1_casino10
		DELETE_CHAR top_goon1_casino10
		++ counter_dead_casino10
		top_goon1_dead_casino10 = 1
	ENDIF

ENDIF

IF top_goon2_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD top_goon2_casino10
		DELETE_CHAR top_goon2_casino10
		++ counter_dead_casino10
		top_goon2_dead_casino10 = 1
	ENDIF

ENDIF

IF top_goon3_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD top_goon3_casino10
		DELETE_CHAR top_goon3_casino10
		++ counter_dead_casino10
		top_goon3_dead_casino10 = 0
	ENDIF

ENDIF

IF top_goon4_dead_casino10 = 0

	IF NOT IS_CHAR_DEAD top_goon4_casino10
		DELETE_CHAR top_goon4_casino10
		++ counter_dead_casino10
		top_goon4_dead_casino10 = 1
	ENDIF

ENDIF

flag_mid_cleanup_done_casino10 = 1

RETURN

create_end_guys_casino10:

CUSTOM_PLATE_FOR_NEXT_CAR SENTINEL &_4GEDIT_
CREATE_CAR SENTINEL -831.107 503.678 1357.348 car2_casino10
CHANGE_CAR_COLOUR car2_casino10 24 24
SET_CAR_HEADING car2_casino10 292.606
LOCK_CAR_DOORS car2_casino10 CARLOCK_LOCKED
SET_VEHICLE_AREA_VISIBLE car2_casino10 1

CREATE_CHAR PEDTYPE_MISSION1 VMAFF3 -816.005 493.329 1358.293 end_goon1_casino10
SET_CHAR_ALLOWED_TO_DUCK end_goon1_casino10 FALSE
GIVE_WEAPON_TO_CHAR end_goon1_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING end_goon1_casino10 302.139
SET_CHAR_DECISION_MAKER end_goon1_casino10 dm_duck_casino10
TASK_STAY_IN_SAME_PLACE end_goon1_casino10 TRUE
TASK_KILL_CHAR_ON_FOOT end_goon1_casino10 scplayer
SET_CHAR_ACCURACY end_goon1_casino10 80
SET_CHAR_SHOOT_RATE end_goon1_casino10 50

CREATE_CHAR PEDTYPE_MISSION1 VMAFF1 -821.268 508.167 1358.273 end_goon2_casino10
SET_CHAR_ALLOWED_TO_DUCK end_goon2_casino10 FALSE
GIVE_WEAPON_TO_CHAR end_goon2_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING end_goon2_casino10 234.012
SET_CHAR_DECISION_MAKER end_goon2_casino10 dm_duck_casino10
TASK_TOGGLE_DUCK end_goon2_casino10 TRUE 
TASK_STAY_IN_SAME_PLACE end_goon2_casino10 TRUE
TASK_KILL_CHAR_ON_FOOT end_goon2_casino10 scplayer
SET_CHAR_ACCURACY end_goon2_casino10 50
SET_CHAR_SHOOT_RATE end_goon2_casino10 80

CREATE_CHAR PEDTYPE_MISSION1 VMAFF2 -828.770 510.594 1357.307 end_goon3_casino10
SET_CHAR_ALLOWED_TO_DUCK end_goon3_casino10 FALSE
GIVE_WEAPON_TO_CHAR end_goon3_casino10 WEAPONTYPE_MICRO_UZI 30000 // Set to infinate ammo
SET_CHAR_HEADING end_goon3_casino10 241.767
SET_CHAR_DECISION_MAKER end_goon3_casino10 dm_duck_casino10
TASK_TOGGLE_DUCK end_goon3_casino10 TRUE 
TASK_STAY_IN_SAME_PLACE end_goon3_casino10 TRUE
TASK_KILL_CHAR_ON_FOOT end_goon3_casino10 scplayer
SET_CHAR_ACCURACY end_goon3_casino10 75
SET_CHAR_SHOOT_RATE end_goon3_casino10 50  

end_guys_created_casino10 = 1

RETURN

// mission audio
load_and_play_audio_casino10:

	IF casino10_audio_is_playing = 0
	OR casino10_audio_is_playing = 1
		IF casino10_index <= cell_index_end
			GOSUB play_casino10_audio
		ENDIF
	ENDIF

	IF casino10_audio_is_playing = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
			casino10_audio_is_playing = 0
			casino10_index ++
			casino10_cutscene_flag = 0
			CLEAR_PRINTS	
		ENDIF
	ENDIF

RETURN

play_casino10_audio:

	IF casino10_audio_is_playing = 0
		LOAD_MISSION_AUDIO 1 casino10_audio_chat[casino10_index]
		casino10_audio_is_playing = 1
	ENDIF
	IF casino10_audio_is_playing = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PRINT_NOW ( $casino10_chat[casino10_index] ) 4000 1 //Dummy message"
			PLAY_MISSION_AUDIO 1
			casino10_audio_is_playing = 2
		ENDIF
	ENDIF	
	
RETURN

end_guys_death_check_casino10:

	IF end_goon1_casino10_dead = 0

		IF IS_CHAR_DEAD end_goon1_casino10
			++ counter_dead_casino10
			end_goon1_casino10_dead = 1
		ELSE

			IF flag_forelli_dead_casino10 = 1
			
				IF end_goon1_kill_player_casino10 = 0
					SET_CHAR_STAY_IN_SAME_PLACE end_goon1_casino10 FALSE
					end_goon1_kill_player_casino10 = 1
				ENDIF
				
			ENDIF  

		ENDIF

	ENDIF
	
	IF end_goon2_casino10_dead = 0

		IF IS_CHAR_DEAD end_goon2_casino10
			++ counter_dead_casino10
			end_goon2_casino10_dead = 1
		ELSE
			
			IF flag_forelli_dead_casino10 = 1
			
				IF end_goon2_kill_player_casino10 = 0
					SET_CHAR_STAY_IN_SAME_PLACE end_goon2_casino10 FALSE
					end_goon2_kill_player_casino10 = 1
				ENDIF
				
			ENDIF  

		ENDIF

	ENDIF
	
	IF end_goon3_casino10_dead = 0

		IF IS_CHAR_DEAD end_goon3_casino10
			++ counter_dead_casino10
			end_goon3_casino10_dead = 1
		ELSE

			IF flag_forelli_dead_casino10 = 1
			
				IF end_goon3_kill_player_casino10 = 0
					SET_CHAR_STAY_IN_SAME_PLACE end_goon3_casino10 FALSE
					end_goon3_kill_player_casino10 = 1
				ENDIF
				
			ENDIF  

		ENDIF

	ENDIF 
	 
RETURN


			    
}