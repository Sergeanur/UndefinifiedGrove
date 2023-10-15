MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Heist 2 *******************************************
// ******************************* The Plan/Get girlfriend  ********************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME HEIST2



// Mission start stuff

GOSUB mission_start_heist2

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_heist2_failed
ENDIF

GOSUB mission_cleanup_heist2

MISSION_END

{ 
// Variables for mission

/////////////////////////////////////// Variables for mission\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

LVAR_INT hei2_number_of_charges_placed
LVAR_INT hei2_locate_visisble 
LVAR_INT hei2_counter
LVAR_INT hei2_counter2
LVAR_INT hei2_dam_sec_decision
LVAR_INT hei2_task_status
LVAR_INT hei2_water_splash
LVAR_INT hei2_temp_int
LVAR_INT hei2_last_audio


LVAR_FLOAT hei2_player_x  hei2_player_y hei2_player_z
LVAR_FLOAT hei2_anim_time 
VAR_FLOAT hei2_height

LVAR_FLOAT hei2_security_start_x[6]
LVAR_FLOAT hei2_security_start_y[6]
LVAR_FLOAT hei2_security_start_z[6]
LVAR_FLOAT hei2_security_start_heading[6]
LVAR_FLOAT hei2_security_end_x[6]
LVAR_FLOAT hei2_security_end_y[6]
LVAR_FLOAT hei2_security_end_z[6]



LVAR_FLOAT hei2_generator_x[5]
LVAR_FLOAT hei2_generator_y[5]
LVAR_FLOAT hei2_generator_z[5]


//LVAR_FLOAT hei2_camera_offset_x	hei2_camera_offset_y  hei2_camera_offset_z
//LVAR_FLOAT hei2_camera_point_x	hei2_camera_point_y  hei2_camera_point_z


////////////////// PICK-UP ////////////////////////
LVAR_INT hei2_knife_pickup


////////////////// FLAGS \\\\\\\\\\\\\\\\\\\\\\\\\\\
LVAR_INT hei2_player_spotted_flag
LVAR_INT hei2_safety_flag
LVAR_INT hei2_mission_progression_flag
LVAR_INT hei2_charge_on_generator_flag[5]
LVAR_INT hei2_security_blip_visible_flag[6]
LVAR_INT hei2_dive_scene_played_flag 
LVAR_INT hei2_player_location_int
LVAR_INT hei2_help_displayed
LVAR_INT hei2_near_generator
LVAR_INT hei2_para_progression hei2_in_water          
LVAR_INT hei2_camera_flag
LVAR_INT hei2_text_patrol_flag[6]
LVAR_INT hie2_out_of_plane_blip_added
LVAR_INT hei2_player_spotted_before
LVAR_INT hei2_player_duck
//LVAR_INT hei2_gate_state
LVAR_INT hei2_clothes_changed
LVAR_INT hei2_plane_blip_swap
LVAR_INT hei2_plane_doors_locked  hei2_player_has_jumped hei2_landed_flag              

///////////////// BLIPS  \\\\\\\\\\\\\\\\\\\\\\\\\\\
LVAR_INT hei2_generator_blip[5]
LVAR_INT hei2_generator_room_door_blip
LVAR_INT hei2_security_blip[6]
LVAR_INT hei2_plane_blip hei2_checkpoint
LVAR_INT hei2_quay_guard_blip[2]
LVAR_INT hei2_knife_blip


///////////////// PEOPLE \\\\\\\\\\\\\\\\\\\\\\\\\\

LVAR_INT hei2_security_ped[6]
LVAR_INT hei2_quay_guard[2]
LVAR_INT hei2_temp_ped[2]


///////////////// GROUPS \\\\\\\\\\\\\\\\\\\\\\\\\\\\

///////////////// VEHICLES \\\\\\\\\\\\\\\\\\\\\\\\\\

LVAR_INT hei2_player_boat
LVAR_INT hei2_jump_plane

///////////////// OBJECTS \\\\\\\\\\\\\\\\\\\\\\\
LVAR_INT hei2_bomb_obj[5]
LVAR_INT hei2_quay_object[8]

////////////////// SEQUENCES \\\\\\\\\\\\\\\\\\\\\\\\\
LVAR_INT hei2_plant_bomb_seq
LVAR_INT hei2_dam_dive_seq
LVAR_INT hei2_sequence



LVAR_INT hei2_can_be_spotted   
LVAR_INT hei2_timer_start hei2_timer_current hei2_timer_elapsed
LVAR_INT hei2_fireball hei2_fireball2


///// AUDIO CRAP
VAR_TEXT_LABEL hei2_text[18]
LVAR_INT hei2_audio[18]
LVAR_INT hei2_audio_counter
LVAR_INT hei2_audio_slot1 hei2_audio_slot2 hei2_audio_playing
LVAR_INT hei2_text_loop_done
LVAR_INT hei2_mobile
LVAR_INT hei2_text_timer_diff 
LVAR_INT hei2_text_timer_end 
LVAR_INT hei2_text_timer_start
LVAR_INT hei2_ahead_counter


LVAR_INT hei2_dummy
LVAR_INT hei2_sfx[2]
LVAR_INT hei2_sfx_counter 
LVAR_INT hei2_sfx_playing
LVAR_INT hei2_sfx_played

// ****************************************START OF CUTSCENE********************************


// ****************************************END OF CUTSCENE**********************************






// **************************************** Mission Start **********************************

mission_start_heist2:

LOAD_MISSION_TEXT HEIST2

REGISTER_MISSION_GIVEN
SET_FADING_COLOUR 0 0 0

flag_player_on_mission = 1

DO_FADE 2000 FADE_OUT
WHILE GET_FADING_STATUS
    WAIT 0
ENDWHILE 

SET_AREA_VISIBLE 10
FORCE_WEATHER WEATHER_SUNNY_VEGAS


LOAD_CUTSCENE HEIST4a
 
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
LOAD_SCENE 2030.1027 1020.7899 9.8203
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
CLEAR_CUTSCENE


RELEASE_WEATHER
SET_AREA_VISIBLE 0

WAIT 0

REQUEST_COLLISION 2030.1027 1020.7899
LOAD_SCENE 2030.1027 1020.7899 9.8203
SET_CHAR_COORDINATES scplayer 2030.1027 1020.7899 9.8130 
SET_CHAR_HEADING scplayer 277.5123
SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 2016.4222 1017.1024 10.0 //TRIAD CASINO

 

SET_CAMERA_BEHIND_PLAYER
RESTORE_CAMERA_JUMPCUT

ADD_BLIP_FOR_COORD 1717.0166 1602.4974 9.3368 hei2_plane_blip

//ADD_BLIP_FOR_COORD 1427.87  1258.75  11.35  hei2_plane_blip	
SET_BLIP_AS_FRIENDLY hei2_plane_blip TRUE

REQUEST_ANIMATION BOMBER
WHILE NOT HAS_ANIMATION_LOADED BOMBER
	WAIT 0
ENDWHILE

SET_FADING_COLOUR 0 0 0



SWITCH_WIDESCREEN OFF

//STORE_CLOTHES_STATE
//hei2_clothes_changed = 1
//GIVE_PLAYER_CLOTHES_OUTSIDE_SHOP Player1  balaclava  balaclava CLOTHES_TEX_EXTRA1												 
//BUILD_PLAYER_MODEL player1


LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_STEAL  hei2_dam_sec_decision // new ai stuff

//WAIT 1000
LOAD_SCENE 2030.1027 1020.7899 9.8203
DO_FADE 1500 FADE_IN 
WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

SET_PLAYER_CONTROL Player1 ON
 



//LOAD_CHAR_DECISION_MAKER david/dam_sec hei2_dam_sec_decision
//CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE hei2_dam_sec_decision EVENT_POTENTIAL_WALK_INTO_PED  //was TASK_COMPLEX_AVOID_OTHER_PED_WHILE_WANDERING
PRINT (HEI2_90) 10000 1 // Go to the airport you need a plane to parachute onto the dam quay.
/////////////////////////// set the initial flag status	   \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 hei2_player_spotted_flag          = 0 
 hei2_mission_progression_flag     = 0
 hei2_safety_flag				   = 0  
 hei2_dive_scene_played_flag       = 0

 hei2_help_displayed			   = 0
 hei2_near_generator			   = 0
 hei2_in_water					   = 0
 hie2_out_of_plane_blip_added	   = 0
 hei2_camera_flag				   = 0
 hei2_player_spotted_before		   = 0
// hei2_clothes_changed 			   = 0
 hei2_plane_doors_locked		   = 0
 hei2_player_has_jumped			   = 0
 hei2_plane_blip_swap			   = 0
 hei2_landed_flag				   = 0				 		


 hei2_para_progression	= 0


 
 
 hei2_counter = 0
 WHILE hei2_counter < 6  
 hei2_security_blip_visible_flag[hei2_counter]= 0
 IF hei2_counter < 5	
	 hei2_charge_on_generator_flag[hei2_counter]  = 0	 
 ENDIF
 hei2_counter++
 ENDWHILE

///////////////////////////  set the initial valus of other variables   \\\\\\\\\\\\\\\
 hei2_number_of_charges_placed     = 0
 hei2_player_location_int		   = 0		
 hei2_locate_visisble			   = TRUE
 hei2_counter					   = 0
 hei2_counter2					   = 0	
 
 

//////security guards start tehn end points  
    


//////////////////////////// set the guards starting locations \\\\\\\\\\\\\\\\\ 
 hei2_security_start_x[0]			   = -955.7253	
 hei2_security_start_y[0]			   = 1920.6371	
 hei2_security_start_z[0]			   = 8.0078
 hei2_security_start_heading[0]		   = 294.0869		

 hei2_security_start_x[1]			   = -959.8978	
 hei2_security_start_y[1]			   = 1861.8835	
 hei2_security_start_z[1]			   = 8.0078
 hei2_security_start_heading[1]		   = 87.9045
 
 hei2_security_start_x[2]			   = -945.8220	
 hei2_security_start_y[2]			   = 1868.0298	
 hei2_security_start_z[2]			   = 4.0051
 hei2_security_start_heading[2]		   = 177.4150
 
 hei2_security_start_x[3]			   = -945.5732	
 hei2_security_start_y[3]			   = 1911.2555	
 hei2_security_start_z[3]			   = 4.0000
 hei2_security_start_heading[3]		   = 183.0188
 
 hei2_security_start_x[4]			   = -959.6175	
 hei2_security_start_y[4]			   = 1858.1558	
 hei2_security_start_z[4]			   = 8.0078
 hei2_security_start_heading[4]		   = 180.4759

 hei2_security_start_x[5]	   	       = -959.7453	
 hei2_security_start_y[5]	   	       = 1914.6742	
 hei2_security_start_z[5]	   	   	   = 8.0078
 hei2_security_start_heading[5]		   = 185.4313

/////////////////////////// set the guards end location \\\\\\\\\\\\\\\\\\\\\\\\\
 hei2_security_end_x[0]			   = -955.7253	
 hei2_security_end_y[0]			   = 1920.6371	
 hei2_security_end_z[0]			   = 8.0078
		

 hei2_security_end_x[1]			   = -959.8978	
 hei2_security_end_y[1]			   = 1861.8835	
 hei2_security_end_z[1]			   = 8.0078
             
                

 hei2_security_end_x[2]			       = -943.7589	
 hei2_security_end_y[2]			       = 1888.9435	
 hei2_security_end_z[2]			       = 4.0051

 
 hei2_security_end_x[3]			       = -943.0214	
 hei2_security_end_y[3]			       = 1932.3524	
 hei2_security_end_z[3]			       = 4.0000

 
 hei2_security_end_x[4]			       = -959.4366	
 hei2_security_end_y[4]			       = 1900.0217	
 hei2_security_end_z[4]			   	   = 8.0078


 hei2_security_end_x[5]			       = -959.4960	
 hei2_security_end_y[5]			       = 1936.9250	
 hei2_security_end_z[5]			   	   = 8.0061
 


 //////////////////////////// set the location of the generators \\\\\\\\\\\\\\\\\\\\
 hei2_generator_x[0] 					= -952.56
 hei2_generator_y[0] 					= 1943.52
 hei2_generator_z[0] 					= 8.0

 hei2_generator_x[1] 					= -952.56
 hei2_generator_y[1] 					= 1922.07
 hei2_generator_z[1] 					= 8.0

 hei2_generator_x[2] 					= -952.56 
 hei2_generator_y[2] 					= 1900.44
 hei2_generator_z[2] 					= 8.0

 hei2_generator_x[3] 					= -952.56
 hei2_generator_y[3] 					= 1878.16
 hei2_generator_z[3] 					= 8.0

 hei2_generator_x[4] 					= -952.56
 hei2_generator_y[4] 					= 1857.70
 hei2_generator_z[4] 					= 8.0
 

// hei2_gate_state = iFlagOpenAirportGates
hei2_can_be_spotted = 0



/////// AUDIO CRAP ///////////////////
hei2_audio_counter	= 0
hei2_audio_slot1 	   = 1
hei2_audio_slot2 	   = 2
hei2_audio_playing	   = 0
hei2_text_loop_done	   = 0
hei2_mobile			   = 0
hei2_text_timer_diff   = 0
hei2_text_timer_end    = 0
hei2_text_timer_start  = 0
hei2_ahead_counter	   = 0
hei2_last_audio		   = 0


hei2_sfx[1]			  = SOUND_HATCH_LOCK
hei2_sfx_counter 	  = 0
hei2_sfx_playing	  = 0
hei2_sfx_played 	  = 0

$hei2_text[1] = HE2_AA //Hey, I found him, he's over here!
$hei2_text[2] = HE2_AB//He's in there somewhere, start looking!
$hei2_text[3] = HE2_AC//The cops are on the way, this thrill seeker ain't gonna escape!
$hei2_text[4] = HE2_AD//We got a light saying there's been a break in!
$hei2_text[5] = HE2_AE//That's the guy, arrest him!
$hei2_text[6] = HE2_AF//We need more men in here!
$hei2_text[7] = HE2_AG//Stop him, for chrisakes!
$hei2_text[8] = HE2_AH//Always get the nut jobs on my shift!
$hei2_text[9] = HE2_AJ//Must be seeing things. I thought that coffee tasted a bit odd!
$hei2_text[10] = HE2_AK//Finally, some action around here!
$hei2_text[11] = HE2_AL//Where the hell did he go?
$hei2_text[12] = HE2_BA//Any sign of him yet?
$hei2_text[13] = HE2_BB//Not yet, but we've locked the quay entrance and the cops are on the way
$hei2_text[14] = HE2_CA//What the... somebody'e locked the hatch!
$hei2_text[15] = HE2_CB//Shit, the hatch locked behind me!
$hei2_text[16] = HE2_DA //Shit, must have taken a wrong turn someplace!
$hei2_text[17] = HE2_DB  //We've got him trapped, there's no way off that ledge!

hei2_audio[1] = SOUND_HE2_AA //Hey, I found him, he's over here!
hei2_audio[2] = SOUND_HE2_AB//He's in there somewhere, start looking!
hei2_audio[3] = SOUND_HE2_AC//The cops are on the way, this thrill seeker ain't gonna escape!
hei2_audio[4] = SOUND_HE2_AD//We got a light saying there's been a break in!
hei2_audio[5] = SOUND_HE2_AE//That's the guy, arrest him!
hei2_audio[6] = SOUND_HE2_AF//We need more men in here!
hei2_audio[7] = SOUND_HE2_AG//Stop him, for chrisakes!
hei2_audio[8] = SOUND_HE2_AH//Always get the nut jobs on my shift!
hei2_audio[9] = SOUND_HE2_AJ//Must be seeing things. I thought that coffee tasted a bit odd!
hei2_audio[10] = SOUND_HE2_AK//Finally, some action around here!
hei2_audio[11] = SOUND_HE2_AL//Where the hell did he go?
hei2_audio[12] = SOUND_HE2_BA//Any sign of him yet?
hei2_audio[13] = SOUND_HE2_BB//Not yet, but we've locked the quay entrance and the cops are on the way
hei2_audio[14] = SOUND_HE2_CA//What the... somebody'e locked the hatch!
hei2_audio[15] = SOUND_HE2_CB//Shit, the hatch locked behind me!

hei2_audio[16] = SOUND_HE2_DA //Shit, must have taken a wrong turn someplace!
hei2_audio[17] = SOUND_HE2_DB //We've got him trapped, there's no way off that ledge!




//VIEW_FLOAT_VARIABLE hei2_height hei2_height
//VIEW_INTEGER_VARIABLE hei2_plane_doors_locked hei2_plane_doors_locked
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION1 PEDTYPE_MISSION1
SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_MISSION2 PEDTYPE_MISSION2

SWITCH_ENTRY_EXIT damin FALSE
SWITCH_ENTRY_EXIT damout FALSE
	 	
// ****************************************START OF CUTSCENE********************************

// fades the screen in 

/*
LOAD_CUTSCENE casino_1
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE

DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
*/
// ****************************************END OF CUTSCENE**********************************


WAIT 1000

// ************************************** MAIN LOOP **********************************
 
 
heist2_main_loop:

WAIT 0
/////////////////////////////
/// debug key board stuff ///
/////////////////////////////

//GET_CHAR_LIGHTING scplayer hei2_light_f
//VIEW_FLOAT_VARIABLE hei2_light_f light_level

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_1
  SET_CHAR_COORDINATES scplayer -585.0 2026.0 60.0 // the dam
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_2
  SET_CHAR_COORDINATES scplayer 1423.6571 1643.1824 9.8359 // the airport
  //GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_UNARMED 1
  //					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_3  
  SET_CHAR_COORDINATES scplayer -828.64 1971.644 6.0 // the door to generator room   
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_4
SET_CHAR_COORDINATES scplayer -693.98 1806.81 800.0
ENDIF




IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_RETURN
	hei2_dive_scene_played_flag       = 0		
	hei2_camera_flag = 0	
	GOSUB heist2_dive_scene
	
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_6
   hei2_mission_progression_flag = 6
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_7
   hei2_para_progression = 6
ENDIF

IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
	GOTO mission_heist2_passed
ENDIF




///////////////////////////////////
/// Set up for start of Mission ///
/////////////////////////////////// 
 
 IF hei2_mission_progression_flag = 99999
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 hei2_quay_guard_blip[0]
	ADD_BLIP_FOR_COORD 0.0 0.0 0.0 hei2_quay_guard_blip[1]
 ENDIF
 


 IF hei2_mission_progression_flag = 0
	PRINT (HEI2_90) 7000 1 // Go to the airport you need a plane to parachute onto the dam quay.			
	hei2_mission_progression_flag = 1
	hei2_para_progression = 1   
 ENDIF



//////////////////////////////////
/// PARACHUTE CODE GOES HERE ? ///
//////////////////////////////////
IF hei2_para_progression < 12
	
	

	IF hei2_para_progression < 4
		///////////////////////////////
		///////////////////////////////
		///////////////////////////////
		

		IF hei2_para_progression = 1								
			IF LOCATE_CHAR_ANY_MEANS_2D scplayer 1477.4014 1677.3990 250.0 250.0  FALSE // plane
			OR LOCATE_CHAR_ANY_MEANS_2D scplayer 1717.0166 1602.4974 20.0 20.0 FALSE
			OR LOCATE_CHAR_ANY_MEANS_3D SCPLAYER 1477.1776  1639.0835  10.9203  79.4990  143.0963  4.9000  FALSE
				REQUEST_MODEL NEVADA			
				WHILE NOT HAS_MODEL_LOADED NEVADA			
				WAIT 0
				ENDWHILE
				SET_AREA51_SAM_SITE OFF							 
				CREATE_CAR NEVADA 1477.4014 1677.3990 9.8425 hei2_jump_plane									
				SET_CAR_HEADING hei2_jump_plane 180.049						
				
				hei2_para_progression = 2						
			ENDIF
		ENDIF
		
		///////////////////////////////
		///////////////////////////////
		///////////////////////////////
		IF hei2_plane_blip_swap = 0
			IF LOCATE_CHAR_ANY_MEANS_3D SCPLAYER 1477.1776  1639.0835  10.9203  79.4990  143.0963  4.9000  FALSE
			OR LOCATE_CHAR_ANY_MEANS_2D scplayer 1717.0166 1602.4974 20.0 20.0 FALSE
			   	IF NOT IS_CAR_DEAD hei2_jump_plane
				   	REMOVE_BLIP hei2_plane_blip
				   	ADD_BLIP_FOR_CAR hei2_jump_plane hei2_plane_blip
				   	SET_BLIP_AS_FRIENDLY hei2_plane_blip TRUE
					CLEAR_PRINTS 
					PRINT (HEI2_57) 7000 1 //~s~Get in the plane on the end of the runway.
					hei2_plane_blip_swap = 1
				ENDIF
			ENDIF
		ENDIF

		///////////////////////////////
		///////////////////////////////
		///////////////////////////////
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1717.0166 1602.4974 9.3368 10.0 10.0 5.0  FALSE
			//IF NOT iFlagOpenAirportGates = 1
			//iFlagOpenAirportGates = 1
			//ENDIF
		ENDIF
		
		///////////////////////////////
		///////////////////////////////
		///////////////////////////////
		
		IF hei2_para_progression > 1
			IF hei2_para_progression < 4
				IF IS_CAR_DEAD hei2_jump_plane
					CLEAR_PRINTS
					PRINT (HEI2_25) 5000 1 //~r~ The plane has been destroyed!
					GOTO mission_heist2_failed
				ENDIF
			ENDIF
		ENDIF

		///////////////////////////////
		///////////////////////////////
		///////////////////////////////

		IF hei2_para_progression = 2
			IF NOT IS_CAR_DEAD hei2_jump_plane
				IF IS_CHAR_SITTING_IN_CAR scplayer hei2_jump_plane 
			   		
			   		LOCK_CAR_DOORS hei2_jump_plane CARLOCK_LOCKED_PLAYER_INSIDE
			   		hei2_plane_doors_locked = 1
			   		//LOCK_CAR_DOORS hei2_jump_plane CARLOCK_UNLOCKED
			   		SET_CAMERA_BEHIND_PLAYER
			   		CLEAR_PRINTS
			   		PRINT (HEI2_26) 10000 1 //~s~Fly the aircraft to the jump point over the dam.			   		
			   		REMOVE_BLIP hei2_plane_blip		   				   		    		    		   		   		   
			   		ADD_BLIP_FOR_COORD -592.42 1555.88 750.0 hei2_plane_blip
			   		CREATE_CHECKPOINT CHECKPOINT_TORUS -592.42 1555.88 750.0 -592.42 1555.88 750.0  25.0 hei2_checkpoint	  		   				   				   		
			   		//REMOVE_ALL_CHAR_WEAPONS scplayer
					
					//SET_PLAYER_CYCLE_WEAPON_BUTTON PLAYER1 FALSE
											   		
			   		
					//GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_UNARMED 1
					//SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
			   		
			   		REQUEST_MODEL gun_para
					WHILE NOT HAS_MODEL_LOADED gun_para
						REQUEST_MODEL gun_para
					WAIT 0 
					ENDWHILE
					
					

					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PARACHUTE 1
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE  
					Para_pickup_flag = 0				
			   		hei2_para_progression = 3
				ENDIF
			ENDIF
		ENDIF


	ELSE		
		//IF NOT iFlagOpenAirportGates = hei2_gate_state		
			//iFlagOpenAirportGates = hei2_gate_state
		//ENDIF		
	ENDIF


	 				  



	IF hei2_para_progression = 3								
				
		
		IF NOT IS_CAR_DEAD hei2_jump_plane				   		   			   			    													
			
			
			
			IF IS_CHAR_SITTING_IN_CAR scplayer hei2_jump_plane
			OR hei2_player_has_jumped = 1																												
								
				
				/// add a check here to see if the player is in air or on ground ///
				// on ground unlock the plane? 									 ///
				
				
				
				
				
				
				IF hie2_out_of_plane_blip_added = 1
					REMOVE_BLIP hei2_plane_blip
					ADD_BLIP_FOR_COORD -592.42 1555.88 750.0 hei2_plane_blip
					CREATE_CHECKPOINT CHECKPOINT_TORUS -592.42 1555.88 750.0 -592.42 1555.88 750.0  25.0 hei2_checkpoint
					//REMOVE_ALL_CHAR_WEAPONS scplayer
					
					GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_PARACHUTE 1
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_PARACHUTE
					hie2_out_of_plane_blip_added = 0
				ENDIF

																	   				   	
					IF LOCATE_CHAR_IN_CAR_3D scplayer -592.42 1555.88 750.0 50.0 50.0 25.0 FALSE							
						
						IF hei2_plane_doors_locked = 1																																			
							LOCK_CAR_DOORS hei2_jump_plane CARLOCK_UNLOCKED
							CLEAR_HELP
							PRINT_HELP_FOREVER (HEI2_50)//:HEIST2]	~s~Press the ~t~ button to jump from the plane.
							CLEAR_PRINTS
							PRINT (hei2_15) 10000 1 // ~s~ GO!
							hei2_plane_doors_locked = 0
						ENDIF
						
						
						IF hei2_player_has_jumped = 1
							CLEAR_HELP
							CLEAR_PRINTS
							SWITCH_WIDESCREEN ON
							SET_PLAYER_CONTROL Player1 OFF
							CLEAR_PRINTS
							DELETE_CHECKPOINT hei2_checkpoint
							
							
							
														
							///////////////////////////////////////////////////////////////
							/// aparently need a cut-scene here, no ideas as yet though ///
							///////////////////////////////////////////////////////////////							
						    
						    FREEZE_CAR_POSITION hei2_jump_plane TRUE
						    SET_CAR_COORDINATES hei2_jump_plane -592.42 1555.88 750.0
						    SET_CAR_HEADING hei2_jump_plane 180.0
						    SET_HELI_BLADES_FULL_SPEED 	hei2_jump_plane
						    //SET_FIXED_CAMERA_POSITION  -604.7717 1543.1805 763.1285  0.0 0.0 0.0
							//POINT_CAMERA_AT_POINT  -604.2688 1543.8140 762.5404 JUMP_CUT        	        																						 							

							TIMERA = 0														
							para_control_off = 1														
							////////////////////
							/// PLANE CRASH	 ///
							////////////////////							
								
							REQUEST_CAR_RECORDING 805							
																												
							//WARP_CHAR_FROM_CAR_TO_COORD scplayer -594.99 1551.64 750.0
							//SET_CHAR_HEADING  scplayer  45.0
							
							//RESTORE_CAMERA
							TIMERA = 0
							hei2_safety_flag = 0
							
							
							WHILE hei2_safety_flag = 0
							IF HAS_CAR_RECORDING_BEEN_LOADED 805
							AND TIMERA > 3000
								hei2_safety_flag = 1
								REQUEST_CAR_RECORDING 805
							ENDIF

							WAIT 0 
							ENDWHILE

							IF NOT IS_CAR_DEAD hei2_jump_plane
								START_PLAYBACK_RECORDED_CAR hei2_jump_plane 805
								SET_FIXED_CAMERA_POSITION -590.9276 1546.7495 735.8972  0.0 0.0 0.0		 
								POINT_CAMERA_AT_POINT -591.0876 1546.2274 735.0595 JUMP_CUT								
								SET_CHAR_VISIBLE scplayer FALSE	
								GET_GAME_TIMER hei2_timer_start
							ENDIF 
										
									
							IF NOT IS_CAR_DEAD hei2_jump_plane
									FREEZE_CAR_POSITION hei2_jump_plane FALSE
							ENDIF
								
								SKIP_CUTSCENE_START

								para_freefall_vz = 0.0  
								hei2_safety_flag = 0
								hei2_temp_int = 0
								WHILE hei2_safety_flag = 0
									para_freefall_vz = 0.0
								IF NOT IS_CAR_DEAD hei2_jump_plane
									IF NOT IS_PLAYBACK_GOING_ON_FOR_CAR hei2_jump_plane
										hei2_safety_flag = 1
									ELSE
										GET_GAME_TIMER  hei2_timer_current 
										hei2_timer_elapsed  = hei2_timer_current - hei2_timer_start
										IF hei2_temp_int = 0
											IF hei2_timer_elapsed > 6000												   
											   DO_FADE 1000 FADE_OUT
											   WHILE GET_FADING_STATUS
													WAIT 0
											   ENDWHILE
											   
											   REQUEST_MODEL SW_BIT_09
											   WHILE NOT HAS_MODEL_LOADED SW_BIT_09
											   WAIT 0 
											   ENDWHILE
											   
											   SET_FIXED_CAMERA_POSITION -781.9676 1163.64 28.421 0.0 0.0 0.0
											   POINT_CAMERA_AT_POINT -781.3640 1164.0773 29.0876 JUMP_CUT											   	

											   LOAD_SCENE	-781.3640 1164.0773 29.0876												   
											   REQUEST_COLLISION -781.3640 1164.0773 
											   IF NOT IS_CAR_DEAD hei2_jump_plane
											   		SKIP_IN_PLAYBACK_RECORDED_CAR hei2_jump_plane 80.0
											   		SET_HELI_BLADES_FULL_SPEED 	hei2_jump_plane
											   ENDIF
											   //WAIT 500
											   LOAD_SCENE_IN_DIRECTION	-781.3640 1164.0773 29.0876	270.0
											   DO_FADE 1000 FADE_IN
											   WHILE GET_FADING_STATUS
													WAIT 0
											   ENDWHILE	
												hei2_temp_int = 1
											ENDIF
										ENDIF
									
									ENDIF
								ENDIF
							WAIT 0 
							ENDWHILE
							
							IF NOT IS_CAR_DEAD hei2_jump_plane
								SET_HELI_BLADES_FULL_SPEED 	hei2_jump_plane
							ENDIF
							CREATE_FX_SYSTEM smoke50lit -761.2061 1174.6165 39.0387 TRUE hei2_fireball
							CREATE_FX_SYSTEM explosion_small -761.2061 1174.6165 38.5387 TRUE hei2_fireball2
							IF hei2_fireball = -1
								//BREAKPOINT failed_to_create_fx_system_water_splash
							ELSE
								WAIT 300
								PLAY_FX_SYSTEM hei2_fireball2
								PLAY_FX_SYSTEM hei2_fireball		
							ENDIF
							ADD_EXPLOSION -761.2062 1175.6165 39.5387 EXPLOSION_MOLOTOV
							ADD_EXPLOSION -761.2060 1173.6165 39.5387 EXPLOSION_MOLOTOV
							ADD_EXPLOSION -761.2061 1174.6165 39.5387 EXPLOSION_MOLOTOV
							
							
							//ADD_EXPLOSION -761.2062 1175.6165 39.5387 EXPLOSION_ROCKET
							//ADD_EXPLOSION -761.2060 1173.6165 39.5387 EXPLOSION_GRENADE
							//ADD_EXPLOSION -761.2061 1174.6165 39.5387 EXPLOSION_HELI
							
							
							IF NOT IS_CAR_DEAD hei2_jump_plane
							EXPLODE_CAR hei2_jump_plane
							ENDIF	
							  
							
							
							//////////////////
							//////////////////	
							TIMERA = 0
							WHILE TIMERA < 2000
								
								IF TIMERA < 500
									ADD_EXPLOSION -761.2062 1175.6165 39.5387 EXPLOSION_ROCKET
								ELSE
									IF TIMERA < 1000
									ELSE
										IF TIMERA < 1500
											ADD_EXPLOSION -761.2060 1173.6165 39.5387 EXPLOSION_GRENADE
										ELSE
											ADD_EXPLOSION -761.2061 1174.6165 39.5387 EXPLOSION_HELI
										ENDIF
									ENDIF
								ENDIF
																		


								IF IS_CHAR_DEAD scplayer
								OR IS_CAR_DEAD hei2_jump_plane
									TIMERA = 2000
								ENDIF
							WAIT 0 
							ENDWHILE
							
							SKIP_CUTSCENE_END
							
							MARK_MODEL_AS_NO_LONGER_NEEDED SW_BIT_09

							DO_FADE 500 FADE_IN
						    WHILE GET_FADING_STATUS
								WAIT 0
						    ENDWHILE

							REMOVE_CAR_RECORDING 805
							KILL_FX_SYSTEM hei2_fireball
							KILL_FX_SYSTEM hei2_fireball2							
							//DELETE_CAR hei2_jump_plane
							SET_CHAR_VISIBLE scplayer TRUE
							SWITCH_WIDESCREEN OFF
							SET_PLAYER_CONTROL Player1 ON
							
							para_control_off = 0
							para_freefall_vz = -30.0
							
							RESTORE_CAMERA_JUMPCUT
							IF NOT IS_CAR_DEAD hei2_jump_plane
								SET_CAR_COORDINATES hei2_jump_plane  -761.2061 1174.6165 39.5387
							ENDIF
							MARK_CAR_AS_NO_LONGER_NEEDED hei2_jump_plane								
							hei2_para_progression = 4
						ENDIF																												
					ELSE
						CLEAR_HELP
						CLEAR_THIS_PRINT hei2_15

						GET_CHAR_HEIGHT_ABOVE_GROUND  scplayer hei2_height
						IF hei2_height < 5.0	
							IF hei2_plane_doors_locked = 1
								LOCK_CAR_DOORS hei2_jump_plane CARLOCK_UNLOCKED								
								hei2_plane_doors_locked = 0
							ENDIF
						ELSE
							IF hei2_plane_doors_locked = 0	
								LOCK_CAR_DOORS hei2_jump_plane CARLOCK_LOCKED_PLAYER_INSIDE				   				
				   				hei2_plane_doors_locked = 1
							ENDIF
						ENDIF
									
						IF LOCATE_CHAR_IN_CAR_3D scplayer -592.42 1555.88 750.0 400.0 400.0 300.0 FALSE
							PRINT_HELP_FOREVER HEI2_88
							CLEAR_PRINTS
						ENDIF
					ENDIF
																			
			
			ELSE // player has left the vehicle
												
				IF hei2_player_has_jumped = 0 
					IF LOCATE_CHAR_ANY_MEANS_3D scplayer -592.42 1555.88 750.0 100.0 100.0 100.0 FALSE 
					   hei2_player_has_jumped = 1
					ELSE
					   CLEAR_PRINTS				
						PRINT (HEI2_28) 1000 1 //~s~Get back in the plane!
						IF hie2_out_of_plane_blip_added = 0				
							REMOVE_BLIP hei2_plane_blip
							DELETE_CHECKPOINT hei2_checkpoint
							ADD_BLIP_FOR_CAR hei2_jump_plane hei2_plane_blip						
							SET_BLIP_AS_FRIENDLY hei2_plane_blip TRUE
							hie2_out_of_plane_blip_added = 1
						ENDIF
						
						IF hei2_plane_doors_locked = 1
							LOCK_CAR_DOORS hei2_jump_plane CARLOCK_UNLOCKED
							hei2_plane_doors_locked = 0
						ENDIF
					ENDIF
				ENDIF 																									
			ENDIF																
		ENDIF
	ENDIF


	/////////////////////////
	/////////////////////////



	
					   																		
		

	IF hei2_para_progression = 4 // jumped out of plane
	   	  	 
	   CREATE_CHECKPOINT CHECKPOINT_EMPTYTUBE -811.07 1816.08 7.0 -811.07 1816.08 200.0  6.0 hei2_checkpoint	  
	   REMOVE_BLIP hei2_plane_blip
	   ADD_BLIP_FOR_COORD  -811.07 1816.08 7.0 hei2_plane_blip
	   MARK_CAR_AS_NO_LONGER_NEEDED	  hei2_jump_plane
	   MARK_MODEL_AS_NO_LONGER_NEEDED NEVADA	  	    	  				  	  			 	    	    	  				  	  	  	  
	   REQUEST_MODEL smashboxpile
		REQUEST_MODEL k_cargo1
		REQUEST_MODEL KMB_CONTAINER_RED
		REQUEST_MODEL k_cargo4
		REQUEST_MODEL WMYSGRD
		REQUEST_MODEL NITESTICK 

		LOAD_ALL_MODELS_NOW
		WHILE NOT HAS_MODEL_LOADED k_cargo4
		OR NOT HAS_MODEL_LOADED	smashboxpile
		OR NOT HAS_MODEL_LOADED	k_cargo1
		OR NOT HAS_MODEL_LOADED	KMB_CONTAINER_RED
		OR NOT HAS_MODEL_LOADED  WMYSGRD
		OR NOT HAS_MODEL_LOADED NITESTICK
		WAIT 0 
		ENDWHILE

		CREATE_OBJECT_NO_OFFSET smashboxpile -829.8794 1957.8267 6.4364  hei2_quay_object[0] 
		SET_OBJECT_HEADING hei2_quay_object[0] 7.1004  
		CREATE_OBJECT_NO_OFFSET smashboxpile -824.9033 1958.4427 6.5048 hei2_quay_object[1] 
		SET_OBJECT_HEADING hei2_quay_object[1] 9.1604  
		CREATE_OBJECT_NO_OFFSET  k_cargo1 -828.3333 1952.5269 5.9651 hei2_quay_object[2] 
		SET_OBJECT_HEADING hei2_quay_object[2] 102.0394  
		CREATE_OBJECT_NO_OFFSET k_cargo1 -825.9227 1953.1711 5.9651 hei2_quay_object[3] 
		SET_OBJECT_HEADING hei2_quay_object[3] 100.9489  
		CREATE_OBJECT_NO_OFFSET  k_cargo4 -827.6044 1958.2758 5.9873 hei2_quay_object[4] 
		SET_OBJECT_HEADING hei2_quay_object[4] 101.9089  
		CREATE_OBJECT_NO_OFFSET  KMB_CONTAINER_RED -820.3694 1933.6655 7.3952 hei2_quay_object[5] 		    		  
		SET_OBJECT_HEADING hei2_quay_object[5] 11.8154  
		CREATE_OBJECT_NO_OFFSET  KMB_CONTAINER_RED -822.3879 1945.5193 7.4836 hei2_quay_object[6] // gap against wall needs fixed!!!
		SET_OBJECT_HEADING hei2_quay_object[6] 11.8154  
		//CREATE_OBJECT_NO_OFFSET  k_cargo1 -822.6232 1940.5591 6.0106 hei2_quay_object[7] 
		//SET_OBJECT_HEADING hei2_quay_object[7] 9.0207 


		CREATE_CHAR PEDTYPE_MISSION1 WMYSGRD -826.3729 1946.9884 6.0692 hei2_quay_guard[0] 				
		GIVE_WEAPON_TO_CHAR hei2_quay_guard[0] WEAPONTYPE_NIGHTSTICK 3000
		SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hei2_quay_guard[0] FALSE
		SET_CHAR_HEADING hei2_quay_guard[0] 0.0000 
		SET_SENSE_RANGE hei2_quay_guard[0] 10.0

		CREATE_CHAR PEDTYPE_MISSION1 WMYSGRD -823.3655 1956.2758 6.0400  hei2_quay_guard[1]		
		GIVE_WEAPON_TO_CHAR hei2_quay_guard[1] WEAPONTYPE_NIGHTSTICK 3000
		SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hei2_quay_guard[1] FALSE
		SET_CHAR_HEADING hei2_quay_guard[1] 301.2606
		SET_SENSE_RANGE hei2_quay_guard[1] 10.0
	 	
	 	SET_CHAR_DECISION_MAKER hei2_quay_guard[0] hei2_dam_sec_decision
		SET_CHAR_DECISION_MAKER hei2_quay_guard[1] hei2_dam_sec_decision		 					
		
		FLUSH_PATROL_ROUTE 

		 
		EXTEND_PATROL_ROUTE -825.4943 1938.9036 6.0000 NONE NONE		
		EXTEND_PATROL_ROUTE -823.3667 1933.5399 6.0000 ROADCROSS PED 		
		EXTEND_PATROL_ROUTE -825.4943 1938.9036 6.0000 NONE NONE
		EXTEND_PATROL_ROUTE -826.9571 1947.5686 6.0000 ROADCROSS PED
		TASK_FOLLOW_PATROL_ROUTE hei2_quay_guard[0] PEDMOVE_WALK FOLLOW_ROUTE_BACKFORWARD
		
		FLUSH_PATROL_ROUTE
		EXTEND_PATROL_ROUTE -828.9601 1955.3660 6.0000 ROADCROSS PED
		EXTEND_PATROL_ROUTE -823.2458 1957.0160 6.0070  ROADCROSS PED 		 		
		TASK_FOLLOW_PATROL_ROUTE hei2_quay_guard[1] PEDMOVE_WALK FOLLOW_ROUTE_BACKFORWARD
				
	 	
	 	


	 hei2_para_progression = 5
	
	ENDIF

/*


PRINT (HEI2_33) 2000 1 //~s~When at the checkpoint press the ~t~ button to jump from the plane


*/


	IF hei2_para_progression > 4
	AND hei2_para_progression < 12
		
		
		IF NOT IS_CAR_DEAD hei2_jump_plane
			IF NOT IS_CAR_ON_SCREEN hei2_jump_plane
			MARK_CAR_AS_NO_LONGER_NEEDED hei2_jump_plane
			MARK_MODEL_AS_NO_LONGER_NEEDED NEVADA
		 	DELETE_CAR hei2_jump_plane
			ENDIF
		ENDIF
		

		IF NOT hei2_audio_counter = 0
			IF hei2_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot2
					CLEAR_MISSION_AUDIO hei2_audio_slot2
				ENDIF
				hei2_audio_playing = 1
			ENDIF

			IF hei2_audio_playing = 1
				LOAD_MISSION_AUDIO hei2_audio_slot1 hei2_audio[hei2_audio_counter]
				hei2_audio_playing = 2
			ENDIF

			IF hei2_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot1
					PLAY_MISSION_AUDIO hei2_audio_slot1
					PRINT_NOW $hei2_text[hei2_audio_counter] 10000 1
					hei2_audio_playing = 3
				ENDIF
			ENDIF

			IF hei2_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED hei2_audio_slot1
					CLEAR_THIS_PRINT $hei2_text[hei2_audio_counter]
					IF hei2_audio_slot1 = 1
						hei2_audio_slot1 = 2
						hei2_audio_slot2 = 1
					ELSE
						hei2_audio_slot1 = 1
						hei2_audio_slot2 = 2
					ENDIF
					hei2_audio_counter = 0
					hei2_audio_playing = 0				
				ENDIF
			ENDIF
		ENDIF
		
		hei2_counter = 0
		WHILE ( hei2_counter < 2 )
		 	
		 	/////////////////////////////////////////
			/////////////////////////////////////////
		 	
		 	

			IF NOT IS_CHAR_DEAD	hei2_quay_guard[hei2_counter]	//INVESTIGATE
				IF hei2_text_patrol_flag[hei2_counter] = 0
					IF IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_SOUND_QUIET
	            	OR IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT 
						IF NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE				            				
							IF hei2_audio_playing = 0
								GENERATE_RANDOM_INT_IN_RANGE 0 4 hei2_temp_int
								IF hei2_temp_int = 0
									hei2_audio_counter = 1	
								ELSE
									IF hei2_temp_int = 1
										hei2_audio_counter = 8
									ELSE
										hei2_audio_counter = 12
									ENDIF
								ENDIF								
							ENDIF								            				            
				            hei2_text_patrol_flag[hei2_counter] = 1
				        	SET_CHAR_SAY_CONTEXT hei2_quay_guard[hei2_counter] CONTEXT_GLOBAL_STEALTH_ALERT_GENERIC hei2_dummy
				        ENDIF
					ENDIF
				ENDIF
				IF hei2_text_patrol_flag[hei2_counter] = 1 
					IF NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_SOUND_QUIET
			        AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
			        AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_SHOT_FIRED
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_DAMAGE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_GUN_AIMED_AT
				        IF hei2_audio_playing = 0
							GENERATE_RANDOM_INT_IN_RANGE 0 3 hei2_temp_int
							IF hei2_temp_int = 0
								hei2_audio_counter = 6	
							ELSE
								IF hei2_temp_int = 1
									hei2_audio_counter = 3
								ELSE
									hei2_audio_counter = 2
								ENDIF
							ENDIF								
						ENDIF				        				        
				        SET_CHAR_SAY_CONTEXT hei2_quay_guard[hei2_counter] CONTEXT_GLOBAL_ARREST	 hei2_dummy
				    	hei2_text_patrol_flag[hei2_counter] = 0
					ENDIF
				ENDIF
				IF hei2_text_patrol_flag[hei2_counter] = 0 
				OR hei2_text_patrol_flag[hei2_counter] = 1
					IF IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE
					OR IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_SHOT_FIRED
					OR IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_DAMAGE
					OR IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_GUN_AIMED_AT
			            IF hei2_audio_playing = 0
							GENERATE_RANDOM_INT_IN_RANGE 0 4 hei2_temp_int
							IF hei2_temp_int = 0
								hei2_audio_counter = 1	
							ELSE
								IF hei2_temp_int = 5
									hei2_audio_counter = 8
								ELSE
									IF hei2_temp_int = 2
										hei2_audio_counter = 7
									ELSE
										hei2_audio_counter = 10
									ENDIF
								ENDIF
							ENDIF																									
						ENDIF
			            
			            IF hei2_player_spotted_flag = 0
							IF NOT IS_CHAR_DEAD	hei2_quay_guard[0]
								TASK_KILL_CHAR_ON_FOOT hei2_quay_guard[0] scplayer
							ENDIF  
							IF NOT IS_CHAR_DEAD	hei2_quay_guard[1]
								TASK_KILL_CHAR_ON_FOOT hei2_quay_guard[1] scplayer
							ENDIF
							hei2_player_spotted_flag = 1						
						ENDIF
			            SET_CHAR_SAY_CONTEXT hei2_quay_guard[hei2_counter] CONTEXT_GLOBAL_STEALTH_DEF_SIGHTING hei2_dummy
			            //PRINT_NOW (HE2_AG) 6000 1 // SECURITY GUARD: Lock the door, don't let him get inside!						
						hei2_text_patrol_flag[hei2_counter] = 2
			        ENDIF
				ENDIF
				IF hei2_text_patrol_flag[hei2_counter] = 2  //RETURN FROM KILL PLAYER
					IF NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_SOUND_QUIET
			        AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_SHOT_FIRED
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_DAMAGE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_quay_guard[hei2_counter] EVENT_GUN_AIMED_AT
				        IF hei2_audio_playing = 0
							GENERATE_RANDOM_INT_IN_RANGE 0 2 hei2_temp_int
							IF hei2_temp_int = 0
								hei2_audio_counter = 11	
							ELSE					
								hei2_audio_counter = 9									
							ENDIF																	
						ENDIF
				        //PRINT_NOW (HE2_AB) 5000 1 // SECURITY GUARD: We know he's in here somewhere, start looking!
				    	SET_CHAR_SAY_CONTEXT hei2_quay_guard[hei2_counter] CONTEXT_GLOBAL_STEALTH_NOTHING_THERE hei2_dummy
				    	hei2_text_patrol_flag[hei2_counter] = 0
					ENDIF
				ENDIF

				
			
			ELSE
				IF hei2_security_blip_visible_flag[hei2_counter] = 1
					REMOVE_BLIP hei2_quay_guard_blip[hei2_counter]	
					
					hei2_security_blip_visible_flag[hei2_counter] = 0
				ENDIF
			
			ENDIF

				/////////////////////////////////
				/////////////////////////////////
		   					 		 		 



		 hei2_counter++
		 ENDWHILE
		 
		 IF hei2_audio_playing = 0
			 IF NOT hei2_last_audio =  hei2_audio_counter			
				hei2_last_audio =  hei2_audio_counter
			 ELSE
			    
			    hei2_audio_counter = 0	
			 ENDIF
		 ENDIF
		 
	ENDIF






 


	IF hei2_para_progression = 5	 						
		
		IF  IS_CHAR_PLAYING_ANIM scplayer  PARA_open
		OR  IS_CHAR_PLAYING_ANIM scplayer  PARA_float
			CLEAR_HELP
		ENDIF

		IF player_landed = 2 
		OR LOCATE_CHAR_ANY_MEANS_3D scplayer -811.07 1816.08 7.0 6.0 6.0 3.0 FALSE
			 SET_PLAYER_CYCLE_WEAPON_BUTTON PLAYER1 FALSE											   					   		
			 GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_UNARMED 1
			 SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

			 
			 DELETE_CHECKPOINT hei2_checkpoint	  
			 REMOVE_BLIP hei2_plane_blip
			 
			 CLEAR_PRINTS
			 CLEAR_HELP	
			 SET_PLAYER_CONTROL player1 OFF
			 DO_FADE 1500 FADE_OUT
			 WHILE GET_FADING_STATUS
			 WAIT 0
			 ENDWHILE

			 IF IS_CHAR_IN_ANY_CAR scplayer
				TASK_LEAVE_ANY_CAR scplayer
			 ENDIF

			 MARK_MODEL_AS_NO_LONGER_NEEDED gun_para
			 //warp if too close to teh guards
			 IF LOCATE_CHAR_ANY_MEANS_3D SCPLAYER -828.4104  1945.7595  7.0400  13.1000  19.2998  4.5000  FALSE
			 	SET_CHAR_COORDINATES scplayer -820.3474 1939.7275 6.0000 
				SET_CHAR_HEADING scplayer 295.9757
			 ENDIF
			 
			 SWITCH_WIDESCREEN ON			 
			 TASK_TURN_CHAR_TO_FACE_COORD scplayer  -830.94  1983.23  9.0 
			 TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME PARACH
			 player_landed = 2
			 CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
			 RESTORE_CAMERA_JUMPCUT
			 SET_RADAR_ZOOM 90	 		 
			 REQUEST_MODEL KNIFECUR
			 WHILE NOT HAS_MODEL_LOADED KNIFECUR
			 WAIT 0 
			 ENDWHILE			 
			 CREATE_PICKUP KNIFECUR PICKUP_ONCE -819.0 1929.0 7.0  hei2_knife_pickup				  
			 
			 ADD_BLIP_FOR_PICKUP hei2_knife_pickup hei2_knife_blip
			 CHANGE_BLIP_DISPLAY hei2_knife_blip BLIP_ONLY
			 

			 
			//////////////////////////////////////////////////
			//////////////////////////////////////////////////
			//////////////////////////////////////////////////
			DELETE_CHAR	hei2_quay_guard[0]
			MARK_CHAR_AS_NO_LONGER_NEEDED hei2_quay_guard[0]
			DELETE_CHAR	hei2_quay_guard[1]
			MARK_CHAR_AS_NO_LONGER_NEEDED hei2_quay_guard[1]

			CREATE_CHAR PEDTYPE_MISSION1 WMYSGRD -826.3729 1946.9884 6.0692 hei2_quay_guard[0] 				
			GIVE_WEAPON_TO_CHAR hei2_quay_guard[0] WEAPONTYPE_NIGHTSTICK 3000
			SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hei2_quay_guard[0] FALSE
			SET_CHAR_HEADING hei2_quay_guard[0] 0.0000 
			SET_SENSE_RANGE hei2_quay_guard[0] 10.0

			CREATE_CHAR PEDTYPE_MISSION1 WMYSGRD -823.3655 1956.2758 6.0400  hei2_quay_guard[1]		
			GIVE_WEAPON_TO_CHAR hei2_quay_guard[1] WEAPONTYPE_NIGHTSTICK 3000
			SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hei2_quay_guard[1] FALSE
			SET_CHAR_HEADING hei2_quay_guard[1] 301.2606
			SET_SENSE_RANGE hei2_quay_guard[1] 10.0
		 	SET_CHAR_DECISION_MAKER hei2_quay_guard[0] hei2_dam_sec_decision
			SET_CHAR_DECISION_MAKER hei2_quay_guard[1] hei2_dam_sec_decision		 					
			
			FLUSH_PATROL_ROUTE 

			//EXTEND_PATROL_ROUTE -825.4943 1938.9036 6.0000 NONE NONE		
			EXTEND_PATROL_ROUTE -823.3667 1933.5399 6.0000 ROADCROSS PED 		
			EXTEND_PATROL_ROUTE -825.4943 1938.9036 6.0000 NONE NONE
			EXTEND_PATROL_ROUTE -826.9571 1947.5686 6.0000 ROADCROSS PED
			TASK_FOLLOW_PATROL_ROUTE hei2_quay_guard[0] PEDMOVE_WALK FOLLOW_ROUTE_BACKFORWARD
			
			FLUSH_PATROL_ROUTE
			EXTEND_PATROL_ROUTE -828.9601 1955.3660 6.0000 ROADCROSS PED
			EXTEND_PATROL_ROUTE -823.2458 1957.0160 6.0070  ROADCROSS PED 		 		
			TASK_FOLLOW_PATROL_ROUTE hei2_quay_guard[1] PEDMOVE_WALK FOLLOW_ROUTE_BACKFORWARD
			
			//////////////////////////////////////////////////
			//////////////////////////////////////////////////
			//////////////////////////////////////////////////
			
			 
			 // long shot?
			 SET_FIXED_CAMERA_POSITION -794.19 1856.16 8.89 0.0 0.0 0.0
			 POINT_CAMERA_AT_POINT -833.48 1914.18 3.2	JUMP_CUT
			 			 			 
			 DO_FADE 1500 FADE_IN
			 WHILE GET_FADING_STATUS
			 WAIT 0
			 ENDWHILE
			 
			 PRINT (HEI2_52) 6000 1 // ~s~Make your way to the end of the dam quay.			 			 
			 SKIP_CUTSCENE_START
			 WHILE IS_MESSAGE_BEING_DISPLAYED 
			 WAIT 0 
			 ENDWHILE
			 
			 
			  /// guard nearest the door
			 SET_FIXED_CAMERA_POSITION -821.12 1953.34 7.52 0.0 0.0 0.0
			 POINT_CAMERA_AT_POINT -842.66 1973.89 3.84	JUMP_CUT
			 
			 
			 PRINT (HEI2_52) 6000 1 // ~s~Make your way to the end of the dam quay.			 			 			 
			 WHILE IS_MESSAGE_BEING_DISPLAYED 
			 WAIT 0 
			 ENDWHILE
			 
			 
			 /// other guard
			 SET_FIXED_CAMERA_POSITION -828.18 1949.82 6.79 0.0 0.0 0.0
			 POINT_CAMERA_AT_POINT -807.10 1918.51 8.91	JUMP_CUT
			 
			 
			 PRINT (HEI2_53) 6000 1 // ~s~Avoid getting spotted by the guards patroling in the area
			 WHILE IS_MESSAGE_BEING_DISPLAYED 
			 WAIT 0 
			 ENDWHILE
				
			 
			 PRINT (HEI2_46) 6000 1 // ~s~There's a knife stashed near one of the containers which you may find useful.
			 // cut-scene showing guards patroling
			 SET_FIXED_CAMERA_POSITION -823.48 1924.82 6.71 0.0 0.0 0.0
			 POINT_CAMERA_AT_POINT -808.71 1959.51 9.43 JUMP_CUT			 
			 WHILE IS_MESSAGE_BEING_DISPLAYED 
			 WAIT 0 
			 ENDWHILE			 
			 
			 SKIP_CUTSCENE_END
			 CLEAR_PRINTS

		 	 IF NOT IS_CHAR_DEAD hei2_quay_guard[0]
			  	ADD_BLIP_FOR_CHAR hei2_quay_guard[0] hei2_quay_guard_blip[0]			 
			  	CHANGE_BLIP_DISPLAY hei2_quay_guard_blip[0] BLIP_ONLY
			  	hei2_security_blip_visible_flag[0] = 1
			 ENDIF
			 IF NOT IS_CHAR_DEAD hei2_quay_guard[1]
			  	ADD_BLIP_FOR_CHAR hei2_quay_guard[1] hei2_quay_guard_blip[1]
			  	CHANGE_BLIP_DISPLAY hei2_quay_guard_blip[1] BLIP_ONLY
			  	hei2_security_blip_visible_flag[1] = 1
			 ENDIF

			 SWITCH_WIDESCREEN OFF
			 PRINT (HEI2_60) 5000 1 //~s~Go and pickup the ~g~knife~s~.
			 
			 			 
			 RESTORE_CAMERA_JUMPCUT
			 SET_PLAYER_CONTROL player1 ON
			 
			 
			 
			 
			 IF NOT IS_CHAR_DEAD hei2_quay_guard[0]
				 SET_CHAR_RELATIONSHIP hei2_quay_guard[0] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			 	 SET_INFORM_RESPECTED_FRIENDS hei2_quay_guard[0] 30.0 10		   
			 ENDIF
			 IF NOT IS_CHAR_DEAD hei2_quay_guard[1]
			 	SET_CHAR_RELATIONSHIP hei2_quay_guard[1] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
			 	SET_INFORM_RESPECTED_FRIENDS hei2_quay_guard[1] 30.0 10
			 ENDIF

			 PRINT_HELP HEI2_89 //Remember to crouch to make less noise.

			 hei2_para_progression = 7
		ELSE
			IF NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PARACHUTE
				IF hei2_landed_flag = 0
				SET_PLAYER_CYCLE_WEAPON_BUTTON PLAYER1 FALSE											   					   		
				 GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_UNARMED 1
				 SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				hei2_landed_flag = 1
				ENDIF

			ENDIF

		ENDIF
	ENDIF
							
	

	/////////////////////////////////////////////
	/// FORCE THE PLAYER TO PICK UP THE KNIFE ///
	/////////////////////////////////////////////

	IF hei2_para_progression = 7
		
		IF IS_CHAR_IN_WATER scplayer											
			IF hei2_in_water = 0
				CLEAR_PRINTS
				PRINT (HEI2_40) 7000 1 //~s~Get back on the dam quay.
				REMOVE_BLIP hei2_plane_blip
				ADD_BLIP_FOR_COORD -799.5823 1813.1560 2.0061 hei2_plane_blip
				SET_RADAR_ZOOM 0				
				hei2_in_water = 1			
			ENDIF								

		ELSE
			
			IF hei2_in_water = 1
				REMOVE_BLIP hei2_plane_blip
				hei2_in_water = 0
				CLEAR_PRINTS				
				SET_RADAR_ZOOM 90				
				PRINT (HEI2_60) 5000 1 //~s~Go and pickup the ~g~knife~s~.
			ENDIF
		ENDIF
		
		
		IF HAS_PICKUP_BEEN_COLLECTED hei2_knife_pickup
			CLEAR_PRINTS
			PRINT (HEI2_34) 7000 1// ~s~Get past the guards and find the enterance to the generator room
			REMOVE_BLIP hei2_plane_blip 
			ADD_BLIP_FOR_COORD  -830.94  1983.23  9.0 hei2_plane_blip
			hei2_para_progression = 8
		ENDIF

	ENDIF

	////////////////////////////////////////////////
	/// PLAYER HAS TO MAKE THEIR WAY TO ENTRANCE ///
	////////////////////////////////////////////////

	
	IF hei2_para_progression = 8
	
		IF IS_CHAR_DEAD	hei2_quay_guard[0]
			REMOVE_BLIP hei2_quay_guard_blip[0] 	
		ENDIF
		IF IS_CHAR_DEAD	hei2_quay_guard[1]
			REMOVE_BLIP hei2_quay_guard_blip[1] 	
		ENDIF
				
		IF IS_CHAR_IN_WATER scplayer											
			IF hei2_in_water = 0
				CLEAR_PRINTS
				REMOVE_BLIP hei2_plane_blip
				PRINT (HEI2_40) 7000 1 //~s~Get back on the dam quay.				
				ADD_BLIP_FOR_COORD -799.5823 1813.1560 2.0061 hei2_plane_blip
				SET_RADAR_ZOOM 0				
				hei2_in_water = 1			
			ENDIF								

		ELSE
			
			IF hei2_in_water = 1
				hei2_in_water = 0
				CLEAR_PRINTS				
				REMOVE_BLIP hei2_plane_blip
				ADD_BLIP_FOR_COORD  -830.94  1983.23  9.0 hei2_plane_blip
				SET_RADAR_ZOOM 90				
				PRINT (HEI2_34) 7000 1// ~s~Get past the guards and find the enterance to the generator room
			ENDIF

														
				
			
			
			IF LOCATE_CHAR_ON_FOOT_3D scplayer  -830.2665 1982.1241 8.2500    1.2 1.2 3.0 TRUE //quay enterance		
			   
				CLEAR_PRINTS
				SET_PLAYER_CONTROL player1 OFF
				DO_FADE 1000 FADE_OUT
				WHILE GET_FADING_STATUS
				WAIT 0
				ENDWHILE
				
				SET_CHAR_COORDINATES scplayer -942.0  1847.0  4.0
				SET_AREA_VISIBLE 17
				LOAD_SCENE -942.0  1847.0  4.0
				REQUEST_COLLISION -942.0  1847.0  
				SET_EXTRA_COLOURS 1 FALSE
				CLEAR_PRINTS
				REMOVE_BLIP hei2_plane_blip
				
				MARK_CHAR_AS_NO_LONGER_NEEDED hei2_quay_guard[0]
				MARK_CHAR_AS_NO_LONGER_NEEDED hei2_quay_guard[1]
				
				MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[0]
				MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[1]
				MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[2]
				MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[3]
				MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[4]
				MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[5]
				MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[6]
				//MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[7]

				REMOVE_BLIP hei2_quay_guard_blip[0]
				REMOVE_BLIP hei2_quay_guard_blip[1]

				MARK_MODEL_AS_NO_LONGER_NEEDED	smashboxpile
				MARK_MODEL_AS_NO_LONGER_NEEDED	k_cargo1
				MARK_MODEL_AS_NO_LONGER_NEEDED	KMB_CONTAINER_RED
				MARK_MODEL_AS_NO_LONGER_NEEDED	k_cargo4
				MARK_MODEL_AS_NO_LONGER_NEEDED	KNIFECUR
				REMOVE_PICKUP hei2_knife_pickup
				REMOVE_BLIP hei2_knife_blip
				SET_PED_DENSITY_MULTIPLIER 0.0
				SET_CAR_DENSITY_MULTIPLIER 0.0 // normally 1.0													
				
				
				hei2_mission_progression_flag = 2
				hei2_para_progression = 12
			ENDIF
						

		ENDIF
	ENDIF

ENDIF // IF hei2_para_progression < 12






//////////////////////////////////////////////////////////////////
/// Create the security guards and assign them a task sequence ///
//////////////////////////////////////////////////////////////////

IF hei2_para_progression > 11

	IF hei2_mission_progression_flag = 2																	 			
	
			REQUEST_MODEL SATCHEL  //DYNAMITE
			REQUEST_MODEL WMYMECH
			REQUEST_MODEL COLT45
			REQUEST_MODEL CELLPHONE
			WHILE NOT HAS_MODEL_LOADED SATCHEL//DYNAMITE
			OR NOT HAS_MODEL_LOADED	WMYMECH
			OR NOT HAS_MODEL_LOADED	COLT45
			OR NOT HAS_MODEL_LOADED CELLPHONE
			WAIT 0 
			ENDWHILE 
			CREATE_PICKUP KNIFECUR PICKUP_ON_STREET_SLOW -938.3901 1901.6489 4.3000 hei2_knife_pickup

		   CREATE_CHAR PEDTYPE_MISSION2 WMYSGRD -960.0 1927.0 8.04 hei2_temp_ped[1]									
		   //SET_CHAR_HEADING hei2_temp_ped[0] 232.0
		   //CREATE_CHAR PEDTYPE_MISSION2 WMYSGRD -958.0 1926.0 8.04 hei2_temp_ped[1]									
		   SET_CHAR_HEADING hei2_temp_ped[1] 81.0
		   
		   TASK_USE_MOBILE_PHONE hei2_temp_ped[1] TRUE
		   //TASK_CHAT_WITH_CHAR hei2_temp_ped[0] hei2_temp_ped[1] TRUE FALSE		   		   
		   //TASK_CHAT_WITH_CHAR hei2_temp_ped[1] hei2_temp_ped[0] FALSE FALSE
			
			hei2_security_blip_visible_flag[0] = 0
			hei2_security_blip_visible_flag[1] = 0
			hei2_player_spotted_flag = 0
			hei2_counter = 0
			WHILE hei2_counter < 6
			   
					
					
																			
					IF hei2_counter > 1	// guards 3+						
						CREATE_CHAR PEDTYPE_MISSION2 WMYSGRD hei2_security_start_x[hei2_counter] hei2_security_start_y[hei2_counter] hei2_security_start_z[hei2_counter] hei2_security_ped[hei2_counter]									
					
						SET_FOLLOW_NODE_THRESHOLD_DISTANCE hei2_security_ped[hei2_counter] 1.0
						SET_SENSE_RANGE hei2_security_ped[hei2_counter] 10.0
						SET_CHAR_ACCURACY hei2_security_ped[hei2_counter] 85
						SET_INFORM_RESPECTED_FRIENDS hei2_security_ped[hei2_counter] 20.0 1		   			
			   			
			   			IF hei2_counter = 2
						OR hei2_counter = 4
			   				GIVE_WEAPON_TO_CHAR hei2_security_ped[hei2_counter] WEAPONTYPE_NIGHTSTICK 3000
			   				SET_CHAR_SHOOT_RATE hei2_security_ped[hei2_counter] 70
			   				SET_CHAR_WEAPON_SKILL hei2_security_ped[hei2_counter] WEAPONSKILL_PRO
			   			ELSE
							GIVE_WEAPON_TO_CHAR hei2_security_ped[hei2_counter] WEAPONTYPE_PISTOL 3000						
							SET_CHAR_SHOOT_RATE hei2_security_ped[hei2_counter] 60
			   				SET_CHAR_WEAPON_SKILL hei2_security_ped[hei2_counter] WEAPONSKILL_STD
						ENDIF
						
						SET_CHAR_DROPS_WEAPONS_WHEN_DEAD hei2_security_ped[hei2_counter] FALSE			   			
			   			SET_CHAR_HEADING hei2_security_ped[hei2_counter] hei2_security_start_heading[hei2_counter]															
						ADD_BLIP_FOR_CHAR hei2_security_ped[hei2_counter] hei2_security_blip[hei2_counter]
						CHANGE_BLIP_DISPLAY hei2_security_blip[hei2_counter] BLIP_ONLY
						hei2_security_blip_visible_flag[hei2_counter]= 1
						hei2_text_patrol_flag[hei2_counter] = 0

						FLUSH_PATROL_ROUTE
						EXTEND_PATROL_ROUTE hei2_security_end_x[hei2_counter] hei2_security_end_y[hei2_counter] hei2_security_end_z[hei2_counter] ROADCROSS PED 
						EXTEND_PATROL_ROUTE hei2_security_start_x[hei2_counter] hei2_security_start_y[hei2_counter] hei2_security_start_z[hei2_counter] ROADCROSS PED 

						TASK_FOLLOW_PATROL_ROUTE hei2_security_ped[hei2_counter] PEDMOVE_WALK FOLLOW_ROUTE_BACKFORWARD
					ELSE														 
						CREATE_CHAR PEDTYPE_MISSION2 WMYMECH hei2_security_start_x[hei2_counter] hei2_security_start_y[hei2_counter] hei2_security_start_z[hei2_counter] hei2_security_ped[hei2_counter]									
					
						SET_FOLLOW_NODE_THRESHOLD_DISTANCE hei2_security_ped[hei2_counter] 1.0
						SET_SENSE_RANGE hei2_security_ped[hei2_counter] 10.0
						SET_CHAR_ACCURACY hei2_security_ped[hei2_counter] 85
						SET_INFORM_RESPECTED_FRIENDS hei2_security_ped[hei2_counter] 20.0 1		   						   			
			   			SET_CHAR_HEADING hei2_security_ped[hei2_counter] hei2_security_start_heading[hei2_counter]									
						
						IF hei2_counter = 0
							TASK_DUCK hei2_security_ped[hei2_counter] -2
						ENDIF
						ADD_BLIP_FOR_CHAR hei2_security_ped[hei2_counter] hei2_security_blip[hei2_counter]
						CHANGE_BLIP_DISPLAY hei2_security_blip[hei2_counter] BLIP_ONLY
						hei2_security_blip_visible_flag[hei2_counter]= 1
						hei2_text_patrol_flag[hei2_counter] = 0
						
						OPEN_SEQUENCE_TASK	hei2_sequence												
						TASK_FOLLOW_PATH_NODES_TO_COORD -1 hei2_security_start_x[hei2_counter] hei2_security_start_y[hei2_counter] hei2_security_start_z[hei2_counter] PEDMOVE_WALK -2
						TASK_ACHIEVE_HEADING -1  hei2_security_start_heading[hei2_counter]
						TASK_DUCK -1 5000
						TASK_SCRATCH_HEAD -1
						TASK_PLAY_ANIM -1 FUCKU PED 4.0 FALSE FALSE FALSE FALSE -1 						
						TASK_USE_ATM -1						
						TASK_SCRATCH_HEAD -1						
						SET_SEQUENCE_TO_REPEAT hei2_sequence 1
						CLOSE_SEQUENCE_TASK hei2_sequence
						
						PERFORM_SEQUENCE_TASK hei2_security_ped[hei2_counter] hei2_sequence
						CLEAR_SEQUENCE_TASK hei2_sequence
						
						
						//FLUSH_PATROL_ROUTE
						//EXTEND_PATROL_ROUTE hei2_security_start_x[hei2_counter] hei2_security_start_y[hei2_counter] hei2_security_start_z[hei2_counter] FUCKU PED 										
						//TASK_FOLLOW_PATROL_ROUTE hei2_security_ped[hei2_counter] PEDMOVE_WALK FOLLOW_ROUTE_BACKFORWARD
					ENDIF		 											   					   					    					   					   														
				 
			hei2_counter++
			ENDWHILE
						
					
	   	hei2_mission_progression_flag = 3				
		

	ENDIF





	//////////////////////////////////////////////////////////////////////////// 
	/// Once player is at the dam enterance add the blips for the generators ///
	////////////////////////////////////////////////////////////////////////////
	 																		

	  IF hei2_mission_progression_flag = 3
	   	
	   	
	   	hei2_player_location_int = 1
		IF LOCATE_CHAR_ON_FOOT_3D scplayer -942.0  1847.0  5.0 2.0 2.0 2.0 FALSE // needs to be changed
		OR LOCATE_CHAR_ON_FOOT_3D scplayer -960.0  1952.0  9.0 2.0 2.0 2.0 FALSE// interior of the dam
		
		SET_FIXED_CAMERA_POSITION -940.3428 1845.9495 5.4312  0.0 0.0 0.0 // wide shot
		POINT_CAMERA_AT_POINT -940.8073 1846.8195 5.5962 JUMP_CUT  // wide shot
		
		
		
		SWITCH_WIDESCREEN ON
		CLEAR_PRINTS
		CLEAR_HELP
		DO_FADE 1500 FADE_IN
		WHILE GET_FADING_STATUS
		WAIT 0
		ENDWHILE
		//////////////////////
		/// cut-scene crap ///
		//////////////////////
		
		PRINT (HEI2_20) 6000 1 //~s~ This is all about stealth, if you get spotted or make too much noise the whole building will be alerted.
		SKIP_CUTSCENE_START
		WHILE IS_MESSAGE_BEING_DISPLAYED
		WAIT 0
		ENDWHILE
		
		SET_FIXED_CAMERA_POSITION -959.8510 1925.8219 8.6787  0.0 0.0 0.0 // generator shot 1
		POINT_CAMERA_AT_POINT -959.1008 1925.2178 8.9476 JUMP_CUT  // generator shot 1
		
		IF NOT IS_CHAR_DEAD hei2_security_ped[5]
			FREEZE_CHAR_POSITION hei2_security_ped[5] TRUE
		ENDIF
		
		PRINT HEI2_2 6000 1  
		WHILE IS_MESSAGE_BEING_DISPLAYED
		WAIT 0
		ENDWHILE

		SET_FIXED_CAMERA_POSITION -960.33 1925.24 9.12 0.0 0.0 0.0 // guards talking		
		POINT_CAMERA_AT_POINT -938.79 1959.11 15.19 JUMP_CUT  //
		
		hei2_audio_counter	= 0
hei2_audio_slot1 	   = 1
hei2_audio_slot2 	   = 2
hei2_audio_playing	   = 0
hei2_text_loop_done	   = 0
hei2_mobile			   = 0
hei2_text_timer_diff   = 0
hei2_text_timer_end    = 0
hei2_text_timer_start  = 0
hei2_ahead_counter	   = 0
		
		hei2_text_loop1:
		WAIT 0 

			IF hei2_text_loop_done	   = 0
			
			
				IF NOT hei2_audio_counter = 0
					IF hei2_audio_playing = 0
						IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot2
							CLEAR_MISSION_AUDIO hei2_audio_slot2
						ENDIF
						hei2_audio_playing = 1
					ENDIF

					IF hei2_audio_playing = 1
						LOAD_MISSION_AUDIO hei2_audio_slot1 hei2_audio[hei2_audio_counter]
						hei2_audio_playing = 2
					ENDIF

					IF hei2_audio_playing = 2
					 	IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot1
							PLAY_MISSION_AUDIO hei2_audio_slot1
							PRINT_NOW $hei2_text[hei2_audio_counter] 10000 1
							hei2_audio_playing = 3
						ENDIF
					ENDIF

					IF hei2_audio_playing = 3
						IF HAS_MISSION_AUDIO_FINISHED hei2_audio_slot1
							CLEAR_THIS_PRINT $hei2_text[hei2_audio_counter]
							IF hei2_audio_slot1 = 1
								hei2_audio_slot1 = 2
								hei2_audio_slot2 = 1
							ELSE
								hei2_audio_slot1 = 1
								hei2_audio_slot2 = 2
							ENDIF
							hei2_audio_counter = 0
							hei2_audio_playing = 0				
						ELSE
							IF NOT HAS_MISSION_AUDIO_LOADED hei2_audio_slot2
								IF hei2_audio_counter < 8
									hei2_ahead_counter = hei2_audio_counter + 1
									LOAD_MISSION_AUDIO hei2_audio_slot2 hei2_audio[hei2_ahead_counter]
								ENDIF
							ENDIF						
						ENDIF
					ENDIF
				ENDIF
				
		
		
			SWITCH hei2_mobile
				
				CASE 0
				IF hei2_audio_playing = 0
					IF NOT IS_CHAR_DEAD hei2_temp_ped[1]
					 	STOP_CHAR_FACIAL_TALK hei2_temp_ped[1]
					 	START_CHAR_FACIAL_TALK hei2_temp_ped[1]	 4000									
					ENDIF
					hei2_audio_counter = 12	// CESAR: CJ.
					hei2_mobile = 1
					GET_GAME_TIMER hei2_text_timer_start
				ENDIF
				BREAK

				CASE 1
				GET_GAME_TIMER hei2_text_timer_end
					hei2_text_timer_diff = hei2_text_timer_end - hei2_text_timer_start
					IF hei2_text_timer_diff > 1000
						IF hei2_audio_playing = 0
							IF NOT IS_CHAR_DEAD hei2_temp_ped[1]
							 	STOP_CHAR_FACIAL_TALK hei2_temp_ped[1]							 	
							ENDIF
							hei2_audio_counter = 13	// CESAR: CJ.
							hei2_mobile = 2
							GET_GAME_TIMER hei2_text_timer_start
						ENDIF
					ENDIF
				BREAK

				DEFAULT
					GET_GAME_TIMER hei2_text_timer_end
					hei2_text_timer_diff = hei2_text_timer_end - hei2_text_timer_start
					IF hei2_text_timer_diff > 1000
						IF hei2_audio_playing = 0
						   hei2_text_loop_done = 1	
						ENDIF
					ENDIF
					BREAK
			ENDSWITCH
		
		GOTO hei2_text_loop1
		ENDIF
		
		SKIP_CUTSCENE_END
		
		IF NOT IS_CHAR_DEAD hei2_security_ped[5]
			FREEZE_CHAR_POSITION hei2_security_ped[5] FALSE
		ENDIF
		
		CLEAR_PRINTS 
		PRINT HEI2_2 4000 1  

		RESTORE_CAMERA_JUMPCUT
		
		IF NOT IS_CHAR_DEAD hei2_temp_ped[1]
			GET_SCRIPT_TASK_STATUS hei2_temp_ped[1] TASK_USE_MOBILE_PHONE hei2_task_status
			IF NOT hei2_task_status = FINISHED_TASK
				TASK_USE_MOBILE_PHONE hei2_temp_ped[1] FALSE
			ENDIF
			MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
		ENDIF
		
		//DELETE_CHAR hei2_temp_ped[0]
		DELETE_CHAR hei2_temp_ped[1]
		//MARK_CHAR_AS_NO_LONGER_NEEDED hei2_temp_ped[0]
		MARK_CHAR_AS_NO_LONGER_NEEDED hei2_temp_ped[1]

		SET_RADAR_ZOOM 90	
		CLEAR_PRINTS	
		SET_PLAYER_CONTROL player1 ON
		SWITCH_WIDESCREEN OFF

		//////////////////////////////////////////////////////////////////////////
		   hei2_counter2 = 0
		   WHILE hei2_counter2 < 6
			   IF hei2_counter2 < 5
				   ADD_BLIP_FOR_COORD hei2_generator_x[hei2_counter2] hei2_generator_y[hei2_counter2] hei2_generator_z[hei2_counter2]  hei2_generator_blip[hei2_counter2]				   
			   ENDIF
			   IF NOT IS_CHAR_DEAD hei2_security_ped[hei2_counter2]
				   SET_CHAR_RELATIONSHIP hei2_security_ped[hei2_counter2] ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1				   
				   SET_CHAR_DECISION_MAKER hei2_security_ped[hei2_counter2] hei2_dam_sec_decision		   
			   	   
			   	   SET_INFORM_RESPECTED_FRIENDS hei2_security_ped[hei2_counter2] 40.0 10
			   ENDIF
			   hei2_counter2++		   
		   ENDWHILE
		   	   
		   
		   
		   hei2_mission_progression_flag = 4	
		ENDIF
	 ENDIF
	



	/////////////////////////////////////
	/// check on status of the guards ///
	/////////////////////////////////////

	IF hei2_mission_progression_flag < 6

		IF	hei2_player_location_int = 1
			hei2_counter = 0
			WHILE hei2_counter < 6																					
			
			
			IF IS_CHAR_DEAD hei2_security_ped[hei2_counter]
				IF hei2_security_blip_visible_flag[hei2_counter]= 1
					REMOVE_BLIP hei2_security_blip[hei2_counter]						
					hei2_security_blip_visible_flag[hei2_counter]= 0
				ENDIF						
			ENDIF
			hei2_counter++
			ENDWHILE
		ENDIF

	ELSE
	   IF hei2_mission_progression_flag = 6
	   	  
	   	  hei2_counter = 0
		  WHILE hei2_counter < 6
	   	  IF hei2_security_blip_visible_flag[hei2_counter]= 1
			REMOVE_BLIP hei2_security_blip[hei2_counter]						
			hei2_security_blip_visible_flag[hei2_counter]= 0
		  ENDIF
		  hei2_counter++
		  ENDWHILE	 
	   ENDIF
	ENDIF
	

	////////////////////////////////////
	/// set up checks for the guards ///
	////////////////////////////////////



	IF hei2_mission_progression_flag > 2	
	AND hei2_can_be_spotted = 0 	
			 		 			 		 		 		 		 		 		 
		 
		 IF NOT IS_CAR_DEAD hei2_jump_plane
			IF NOT IS_CAR_ON_SCREEN hei2_jump_plane
			MARK_CAR_AS_NO_LONGER_NEEDED hei2_jump_plane
			MARK_MODEL_AS_NO_LONGER_NEEDED NEVADA
		 	DELETE_CAR hei2_jump_plane
			ENDIF
		ENDIF
		

		IF NOT hei2_audio_counter = 0
			IF hei2_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot2
					CLEAR_MISSION_AUDIO hei2_audio_slot2
				ENDIF
				hei2_audio_playing = 1
			ENDIF

			IF hei2_audio_playing = 1
				LOAD_MISSION_AUDIO hei2_audio_slot1 hei2_audio[hei2_audio_counter]
				hei2_audio_playing = 2
			ENDIF

			IF hei2_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot1
					PLAY_MISSION_AUDIO hei2_audio_slot1
					PRINT_NOW $hei2_text[hei2_audio_counter] 10000 1
					hei2_audio_playing = 3
				ENDIF
			ENDIF

			IF hei2_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED hei2_audio_slot1
					CLEAR_THIS_PRINT $hei2_text[hei2_audio_counter]
					IF hei2_audio_slot1 = 1
						hei2_audio_slot1 = 2
						hei2_audio_slot2 = 1
					ELSE
						hei2_audio_slot1 = 1
						hei2_audio_slot2 = 2
					ENDIF
					hei2_audio_counter = 0
					hei2_audio_playing = 0				
				ENDIF
			ENDIF
		ENDIF
		 
		 
		 
		 
		 hei2_counter = 0
		 
		 WHILE ( hei2_counter < 6 )
		 			 						


		 //////////////////////////////////////
		 /////////////////////////////////////
		 ////////////////////////////////////
		 
		 // ************************************MGS PATROL TEXT*************************************************
			IF NOT IS_CHAR_DEAD	hei2_security_ped[hei2_counter]	//INVESTIGATE
				IF hei2_text_patrol_flag[hei2_counter] = 0
					IF IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_SOUND_QUIET
	            	OR IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT 
						IF NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE
				            IF hei2_audio_playing = 0
								IF hei2_counter > 1
									GENERATE_RANDOM_INT_IN_RANGE 0 3 hei2_temp_int
									IF hei2_temp_int = 0
										hei2_audio_counter = 6	
									ELSE
										IF hei2_temp_int = 1
											hei2_audio_counter = 3
										ELSE
											hei2_audio_counter = 2
										ENDIF
									ENDIF
								ENDIF																	
							ENDIF
				            //PRINT_NOW (HE2_AC) 5000 1//SECURITY GUARD:	Why did this have to happen on my shift?
				            SET_CHAR_SAY_CONTEXT_IMPORTANT hei2_security_ped[hei2_counter] CONTEXT_GLOBAL_STEALTH_ALERT_GENERIC TRUE		FALSE	FALSE hei2_dummy
				            hei2_text_patrol_flag[hei2_counter] = 1
				        ENDIF
					ENDIF
				ENDIF
				IF hei2_text_patrol_flag[hei2_counter] = 1 //RETURN FROM INVESTIGATE
					IF NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_SOUND_QUIET
			        AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
			        AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_SHOT_FIRED
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_DAMAGE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_GUN_AIMED_AT
				        IF hei2_audio_playing = 0
							IF hei2_counter > 1
								GENERATE_RANDOM_INT_IN_RANGE 0 2 hei2_temp_int
								IF hei2_temp_int = 0
									hei2_audio_counter = 11	
								ELSE					
									hei2_audio_counter = 9									
								ENDIF
							ENDIF																	
						ENDIF
				        //PRINT_NOW (HE2_AL) 5000 1 //SECURITY GUARD: Where the hell did he go?
				    	SET_CHAR_SAY_CONTEXT_IMPORTANT hei2_security_ped[hei2_counter] CONTEXT_GLOBAL_ARREST  TRUE		FALSE	FALSE	 hei2_dummy
				    	hei2_text_patrol_flag[hei2_counter] = 0
					ENDIF
				ENDIF
				IF hei2_text_patrol_flag[hei2_counter] = 0 //KILL PLAYER
				OR hei2_text_patrol_flag[hei2_counter] = 1
					IF IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE
					OR IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_SHOT_FIRED
					OR IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_DAMAGE
					OR IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_GUN_AIMED_AT
			            IF hei2_audio_playing = 0
							IF hei2_counter > 1
								GENERATE_RANDOM_INT_IN_RANGE 0 4 hei2_temp_int
								IF hei2_temp_int = 0
									hei2_audio_counter = 1	
								ELSE
									IF hei2_temp_int = 1
										hei2_audio_counter = 8
									ELSE
										IF hei2_temp_int = 2
											hei2_audio_counter = 4
										ELSE
											hei2_audio_counter = 12
										ENDIF
									ENDIF
								ENDIF	
							ENDIF																
						ENDIF
			            //PRINT_NOW (HE2_AE) 5000 1 //SECURITY GUARD: I Found him, he's over here!
						SET_CHAR_SAY_CONTEXT_IMPORTANT hei2_security_ped[hei2_counter] CONTEXT_GLOBAL_STEALTH_DEF_SIGHTING TRUE		FALSE	FALSE hei2_dummy
						hei2_player_spotted_flag = 1
						hei2_text_patrol_flag[hei2_counter] = 2
			        ENDIF
				ENDIF
				IF hei2_text_patrol_flag[hei2_counter] = 2  //RETURN FROM KILL PLAYER
					IF NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_SOUND_QUIET
			        AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE_BADLY_LIT
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_ACQUAINTANCE_PED_HATE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_SHOT_FIRED
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_DAMAGE
					AND NOT IS_CHAR_RESPONDING_TO_EVENT hei2_security_ped[hei2_counter] EVENT_GUN_AIMED_AT
				        IF hei2_audio_playing = 0
							IF hei2_counter > 1
								GENERATE_RANDOM_INT_IN_RANGE 0 4 hei2_temp_int
								IF hei2_temp_int = 0
									hei2_audio_counter = 1	
								ELSE
									IF hei2_temp_int = 5
										hei2_audio_counter = 8
									ELSE
										IF hei2_temp_int = 2
											hei2_audio_counter = 7
										ELSE
											hei2_audio_counter = 10
										ENDIF
									ENDIF
								ENDIF
							ENDIF																	
						ENDIF
				        //PRINT_NOW (HE2_AB) 5000 1 // SECURITY GUARD: We know he's in here somewhere, start looking!
				    	SET_CHAR_SAY_CONTEXT_IMPORTANT  hei2_security_ped[hei2_counter] CONTEXT_GLOBAL_STEALTH_NOTHING_THERE TRUE		FALSE	FALSE hei2_dummy
				    	hei2_text_patrol_flag[hei2_counter] = 0
					ENDIF
				ENDIF


		 /////////////////////////////////////
		 /////////////////////////////////////
		 /////////////////////////////////////
			 				 				 				 				 	
		 	ENDIF		 			 		 		 		 		 
		 
		 	
		 		 		 
		 hei2_counter++
		 ENDWHILE			 
	
		 		 		
		 		

		 IF hei2_player_spotted_before = 0
		 	IF hei2_player_spotted_flag = 1
			 
			 
			 IF hei2_audio_playing = 0
				hei2_player_spotted_before = 1
				hei2_audio_counter = 4																	
			 ENDIF
			 //CLEAR_PRINTS
			 //PRINT (HE2_AD) 4000 1 // "You're not supposed to be in here!"	
			 //PRINT (HE2_AF) 4000 1 //)SECURITY GUARD: We need more guys in here!
			 
			 ENDIF
		 ENDIF
		 /////////////////////
		 /// STEALTH CHECK ///
		 /////////////////////
		 	IF hei2_audio_playing = 0
			 	IF NOT hei2_last_audio =  hei2_audio_counter
					hei2_last_audio =  hei2_audio_counter
				ELSE
					hei2_audio_counter = 0	
				ENDIF
			ENDIF				   			   			       							
	
	ENDIF




	////////////////////////////////////////////////////////////////////////////////////////////////////
	/// Check to see if player is in front of a generator, if he is remove blip and increase counter ///
	////////////////////////////////////////////////////////////////////////////////////////////////////

	 IF hei2_mission_progression_flag = 4
	 	IF  hei2_number_of_charges_placed < 5 	
		          
	 		////////////////////////////////////////////////
	 		/// check if generator has had charge placed ///
			////////////////////////////////////////////////
			hei2_near_generator = 0
			hei2_counter2 = 0
			WHILE hei2_counter2 < 5
			IF hei2_charge_on_generator_flag[hei2_counter2] = 0
	 			IF LOCATE_CHAR_ON_FOOT_3D scplayer hei2_generator_x[hei2_counter2] hei2_generator_y[hei2_counter2] hei2_generator_z[hei2_counter2] 4.0 4.0 3.0  FALSE 
		 	       hei2_near_generator = 1
		 	       IF hei2_help_displayed = 0
					   hei2_help_displayed = 1
					   PRINT_HELP HEI2_6  
				   ENDIF
		 	       IF IS_BUTTON_PRESSED PAD1 TRIANGLE	// button to press to set the charge

			 	       CLEAR_HELP
					   hei2_help_displayed = 0
			 	       hei2_number_of_charges_placed++
					   REMOVE_BLIP hei2_generator_blip[hei2_counter2]					   
					   IF  IS_CHAR_DUCKING scplayer
							hei2_player_duck = 1
					   ELSE
							hei2_player_duck = 0
					   ENDIF
					   SET_PLAYER_CONTROL player1 OFF					   
					   OPEN_SEQUENCE_TASK hei2_plant_bomb_seq					   					   
					   IF hei2_player_duck = 0
					   		TASK_TURN_CHAR_TO_FACE_COORD -1 hei2_generator_x[hei2_counter2] hei2_generator_y[hei2_counter2] hei2_generator_z[hei2_counter2]				   
					   		TASK_PLAY_ANIM -1 BOM_plant_In BOMBER 4.0 0 0 0 0 -1	  // player goes into crouch
					   ELSE
							TASK_PLAY_ANIM -1 BOM_Plant_Crouch_In BOMBER 4.0 0 0 0 0 -1	  // player goes into crouch
					   ENDIF
					   TASK_PLAY_ANIM -1 BOM_plant_Loop BOMBER 4.0 0 0 0 0 -1	  // player goes into crouch
					   TASK_PLAY_ANIM -1 BOM_plant_Loop BOMBER 4.0 0 0 0 0 	-1     // player arming bomb      				   
					   IF  hei2_player_duck = 1
					   		TASK_PLAY_ANIM -1 BOM_Plant_Crouch_Out BOMBER 4.0 0 0 0 0 -1	  // player goes into crouch
					   		TASK_TOGGLE_DUCK -1 TRUE
					   ELSE
							TASK_PLAY_ANIM -1 BOM_plant_2Idle BOMBER 4.0 0 0 0 0 -1	 // player stands up
					   ENDIF
					   CLOSE_SEQUENCE_TASK hei2_plant_bomb_seq
					   PERFORM_SEQUENCE_TASK scplayer hei2_plant_bomb_seq
					   CLEAR_SEQUENCE_TASK hei2_plant_bomb_seq

					  

 


 
					   TIMERA = 0
					   hei2_safety_flag = 0
					   WHILE hei2_safety_flag = 0
					   	IF hei2_player_duck = 0
						   	IF IS_CHAR_PLAYING_ANIM scplayer BOM_plant_2Idle
						   	OR TIMERA > 6000
						   	   GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.5 -1.0 hei2_generator_x[hei2_counter2] hei2_generator_y[hei2_counter2] hei2_generator_z[hei2_counter2]
						   	   CREATE_OBJECT SATCHEL hei2_generator_x[hei2_counter2] hei2_generator_y[hei2_counter2] hei2_generator_z[hei2_counter2] hei2_bomb_obj[hei2_counter2]
						   	   SET_OBJECT_HEADING hei2_bomb_obj[hei2_counter2] 100.0
							   //SET_OBJECT_COORDINATES ObjectID X Y Z
							   //SET_OBJECT_ROTATION hei2_bomb_obj[hei2_counter2] XRot YRot ZRot 

						   	   hei2_safety_flag = 1
							ENDIF
						ELSE
							GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK hei2_task_status
							IF hei2_task_status = FINISHED_TASK
							OR TIMERA > 6000
								GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.5 -1.0 hei2_generator_x[hei2_counter2] hei2_generator_y[hei2_counter2] hei2_generator_z[hei2_counter2]
						   	   	CREATE_OBJECT SATCHEL hei2_generator_x[hei2_counter2] hei2_generator_y[hei2_counter2] hei2_generator_z[hei2_counter2] hei2_bomb_obj[hei2_counter2]
						   	   	SET_OBJECT_HEADING hei2_bomb_obj[hei2_counter2] 100.0
								hei2_safety_flag = 1
							ENDIF
						ENDIF
					   
					   WAIT 0
					   ENDWHILE
					   PRINT_NOW (HEI2_58) 4000 1// ~s~Charge placed.
					   SET_PLAYER_CONTROL player1 ON
					   hei2_charge_on_generator_flag[hei2_counter2] = 1
				   ENDIF
				
				
				ENDIF
			ENDIF
			hei2_counter2++
			ENDWHILE

			IF hei2_near_generator = 0
				CLEAR_HELP
				hei2_help_displayed = 0
			ENDIF


			////////////////////////////////////////////////////
			////////////////////////////////////////////////////

	 	ENDIF // IF  hei2_number_of_charges_placed < 5  

		 IF hei2_number_of_charges_placed = 5
	 	 CLEAR_PRINTS
		 ADD_BLIP_FOR_COORD -959.5947 1953.1221 8.0078   hei2_generator_room_door_blip
		 PRINT ( HEI2_5 ) 10000 1 ////////////  All the generator have been rigged to blow, get out of there !
		 
		 hei2_mission_progression_flag = 5
		 ENDIF

	 	
	 ENDIF // IF mission progression





	////////////////////////////////////////////////////////////////////////////////////////
	/// Check to see if player is at the exit of the generator room and pass the mission ///
	////////////////////////////////////////////////////////////////////////////////////////

	IF hei2_mission_progression_flag = 5
		IF LOCATE_CHAR_ON_FOOT_3D scplayer -959.5947 1953.1221 8.0078 2.0 2.0 2.0  hei2_locate_visisble
		   //warp player to tower ledge
		   SET_PLAYER_CONTROL player1 OFF
		   SWITCH_WIDESCREEN ON
		   DO_FADE 1500 FADE_OUT
		   WHILE GET_FADING_STATUS
		   WAIT 0
		   ENDWHILE

		   hei2_can_be_spotted = 1
		   hei2_locate_visisble = FALSE
		   REMOVE_BLIP hei2_generator_room_door_blip		   
		   REMOVE_PICKUP hei2_knife_pickup
		   SET_PED_DENSITY_MULTIPLIER 1.0
			SET_CAR_DENSITY_MULTIPLIER 1.0
		    
		    DELETE_OBJECT hei2_bomb_obj[0]
			DELETE_OBJECT hei2_bomb_obj[1]
			DELETE_OBJECT hei2_bomb_obj[2]
			DELETE_OBJECT hei2_bomb_obj[3]
			DELETE_OBJECT hei2_bomb_obj[4]

			DELETE_CHAR hei2_security_ped[0]
		    DELETE_CHAR hei2_security_ped[1]
			DELETE_CHAR hei2_security_ped[2]
			DELETE_CHAR hei2_security_ped[3]
			DELETE_CHAR hei2_security_ped[4]
			DELETE_CHAR hei2_security_ped[5]

		    MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[0]
			MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[1]
			MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[2]
			MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[3]
			MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[4]

		   MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[0]
		   MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[1]
		   MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[2]
		   MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[3]
		   MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[4]
		   MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[5]

		   MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL//DYNAMITE

		   REQUEST_MODEL REEFER	
		   
		   
		   WHILE NOT HAS_MODEL_LOADED REEFER		  		   		   		   
		   WAIT 0 
		   ENDWHILE
				   
			CREATE_CAR REEFER -638.0 1955.0 1.0 hei2_player_boat
			SET_CAR_HEADING hei2_player_boat 180.0									
			GOSUB heist2_dive_scene
			CLEAR_PRINTS 
			GOTO mission_heist2_passed			
		ELSE
			hei2_locate_visisble = TRUE
		ENDIF
	ENDIF
		
		
	

							
	

	//////////////////////
	/// END OF MISSION ///
	//////////////////////

	


ENDIF // IF hei2_para_progression > 11


GOTO heist2_main_loop






///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////// labeled functions  //////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

   




///////////////////////////////
/// PLAY THE DIVE CUT-SCENE ///
///////////////////////////////
heist2_dive_scene:
IF hei2_dive_scene_played_flag       = 0
	
	LVAR_INT hei2_cop_car1 hei2_cop_car2 hei2_cop_car3 hei2_cop_car4
	

	CLEAR_PRINTS
	SET_PLAYER_CONTROL player1 OFF
	SWITCH_WIDESCREEN ON
	
	
	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_PARACHUTE
		REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_PARACHUTE
	ENDIF






	REQUEST_ANIMATION DAM_JUMP
	REQUEST_ANIMATION BD_FIRE
	REQUEST_ANIMATION BOMBER
	REQUEST_MODEL ENFORCER
	REQUEST_MODEL 	  599 // POL_RANGER



	SET_AREA_VISIBLE 0
	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer TRUE
	WHILE NOT HAS_ANIMATION_LOADED DAM_JUMP
	OR NOT HAS_ANIMATION_LOADED BD_FIRE
	OR NOT HAS_ANIMATION_LOADED BOMBER
	OR NOT HAS_MODEL_LOADED  599
	OR NOT HAS_MODEL_LOADED ENFORCER
	WAIT 0
	ENDWHILE
	CLEAR_EXTRA_COLOURS FALSE
	
	
	SET_CHAR_COORDINATES scplayer -596.3235 2015.6079 75.9845 
	//TASK_ACHIEVE_HEADING -1 330.0
	SET_CHAR_HEADING scplayer 330.0
	
	LOAD_SCENE -594.9886 2018.6387 76.8829
	REQUEST_COLLISION -594.9886 2018.6387
	//////// door locking scene
	SET_FIXED_CAMERA_POSITION -595.73 2001.92 78.66 0.0 0.0 0.0 // wide shot
	POINT_CAMERA_AT_POINT -601.02 2033.63 73.91 JUMP_CUT  // wide shot
	
	SET_PED_DENSITY_MULTIPLIER 0.0
	SET_CAR_DENSITY_MULTIPLIER 0.0
	CLEAR_AREA -683.0 2053.0  59.1719 50.0 TRUE
	CLEAR_AREA -488.0 2001.0  59.4028 50.0 TRUE


	CREATE_CAR 599 -683.0 2053.0  59.1719 hei2_cop_car1
	SET_CAR_HEADING hei2_cop_car1 268.7848
	SET_CAR_CAN_GO_AGAINST_TRAFFIC hei2_cop_car1 TRUE
	SWITCH_CAR_SIREN hei2_cop_car1 ON

	CREATE_CAR 599 -486.0 2006.0  59.4028  hei2_cop_car2
	SET_CAR_HEADING hei2_cop_car2 130.7071
	SET_CAR_CAN_GO_AGAINST_TRAFFIC hei2_cop_car2 TRUE
	SWITCH_CAR_SIREN hei2_cop_car2 ON

	CREATE_CAR ENFORCER -694.0 2055.0  59.1719  hei2_cop_car3
	SET_CAR_HEADING hei2_cop_car3 268.7848
	SET_CAR_CAN_GO_AGAINST_TRAFFIC hei2_cop_car3 TRUE
	SWITCH_CAR_SIREN hei2_cop_car3 ON

	CREATE_CAR ENFORCER -491.0 1997.0  59.4028  hei2_cop_car4
	SET_CAR_HEADING hei2_cop_car4 126.7071
	SET_CAR_CAN_GO_AGAINST_TRAFFIC hei2_cop_car4 TRUE
	SWITCH_CAR_SIREN hei2_cop_car4 ON


	ALTER_WANTED_LEVEL_NO_DROP player1 2
	
	
	DO_FADE 1500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	TIMERB = 0										
	
	CLEAR_CHAR_TASKS scplayer
	SKIP_CUTSCENE_START						
			hei2_audio_counter	= 0
			hei2_audio_slot1 	   = 1
			hei2_audio_slot2 	   = 2
			hei2_audio_playing	   = 0
			hei2_text_loop_done	   = 0
			hei2_mobile			   = 0
			hei2_text_timer_diff   = 0
			hei2_text_timer_end    = 0
			hei2_text_timer_start  = 0
			hei2_ahead_counter	   = 0
			
			hei2_sfx_counter = 0
			hei2_sfx_played = 0
			hei2_sfx_playing = 0

	hei2_text_loop2:
	WAIT 0 
	
		IF NOT hei2_audio_counter = 0
			IF hei2_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot2
					CLEAR_MISSION_AUDIO hei2_audio_slot2
				ENDIF
				hei2_audio_playing = 1
			ENDIF

			IF hei2_audio_playing = 1
				LOAD_MISSION_AUDIO hei2_audio_slot1 hei2_audio[hei2_audio_counter]
				hei2_audio_playing = 2
			ENDIF

			IF hei2_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot1
					PLAY_MISSION_AUDIO hei2_audio_slot1
					PRINT_NOW $hei2_text[hei2_audio_counter] 10000 1
					hei2_audio_playing = 3
				ENDIF
			ENDIF

			IF hei2_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED hei2_audio_slot1
					CLEAR_THIS_PRINT $hei2_text[hei2_audio_counter]
					IF hei2_audio_slot1 = 1
						hei2_audio_slot1 = 2
						hei2_audio_slot2 = 1
					ELSE
						hei2_audio_slot1 = 1
						hei2_audio_slot2 = 2
					ENDIF
					hei2_audio_counter = 0
					hei2_audio_playing = 0				
				ENDIF
			ENDIF
		ENDIF



		IF NOT hei2_sfx_counter = 0
			IF hei2_sfx_playing = 0
				IF HAS_MISSION_AUDIO_LOADED 3
					CLEAR_MISSION_AUDIO 3
				ENDIF
				hei2_sfx_playing = 1
			ENDIF

			IF hei2_sfx_playing = 1
				LOAD_MISSION_AUDIO 3 hei2_sfx[hei2_sfx_counter]	 // audio fx to be used
				hei2_sfx_playing = 2
			ENDIF

			IF hei2_sfx_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED 3
					PLAY_MISSION_AUDIO 3				
					hei2_sfx_playing = 3
				ENDIF
			ENDIF

			IF hei2_sfx_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED 3								
					hei2_sfx_counter = 0
					hei2_sfx_playing = 0			
				ENDIF
			ENDIF
		ENDIF






		SWITCH hei2_mobile
			CASE 0
				IF hei2_audio_playing = 0
					
				 	STOP_CHAR_FACIAL_TALK scplayer
				 	START_CHAR_FACIAL_TALK scplayer	 4000									
					hei2_audio_counter = 16// HE2_DA //Shit, must have taken a wrong turn someplace!						
					TASK_SCRATCH_HEAD scplayer
					hei2_mobile = 1
					GET_GAME_TIMER hei2_text_timer_start
					//hei2_sfx_counter = 1
				ENDIF
				BREAK
			CASE 1
				GET_GAME_TIMER hei2_text_timer_end
				hei2_text_timer_diff = hei2_text_timer_end - hei2_text_timer_start
				IF hei2_text_timer_diff > 2000
					IF hei2_audio_playing = 0
						IF hei2_sfx_played = 1
							IF hei2_sfx_playing = 0												
								STOP_CHAR_FACIAL_TALK scplayer
					 			START_CHAR_FACIAL_TALK scplayer	 4000
								hei2_audio_counter = 14// = SOUND_HE2_CB//Shit, the hatch locked behind me!					
								hei2_mobile = 2
								GET_GAME_TIMER hei2_text_timer_start
								SET_FIXED_CAMERA_POSITION -596.53 2013.11 76.53  0.0 0.0 0.0 // wide shot	
								POINT_CAMERA_AT_POINT -598.08 2045.39 79.93 JUMP_CUT  // wide shot
								CLEAR_CHAR_TASKS scplayer
								OPEN_SEQUENCE_TASK hei2_dam_dive_seq			
								TASK_PLAY_ANIM -1 BOM_plant_In BOMBER 4.0 0 0 0 0 -1	  // player goes into crouch
								TASK_PLAY_ANIM -1 BOM_plant_Loop BOMBER 4.0 0 0 0 0 -1	  // player goes into crouch	
								TASK_PLAY_ANIM -1 BOM_plant_2Idle BOMBER 4.0 0 0 0 0 -1	 // player stands up					
								CLOSE_SEQUENCE_TASK hei2_dam_dive_seq
								PERFORM_SEQUENCE_TASK scplayer hei2_dam_dive_seq
								CLEAR_SEQUENCE_TASK hei2_dam_dive_seq
							ENDIF
						ELSE
							IF hei2_sfx_played = 0
								hei2_sfx_counter = 1
								hei2_sfx_played = 1
							ENDIF
						ENDIF
					ENDIF
				ENDIF	
				BREAK
															
			DEFAULT
				GET_GAME_TIMER hei2_text_timer_end
				hei2_text_timer_diff = hei2_text_timer_end - hei2_text_timer_start
				IF hei2_text_timer_diff > 1000
					IF hei2_audio_playing = 0
					   STOP_CHAR_FACIAL_TALK scplayer
					   START_CHAR_FACIAL_TALK scplayer	 4000
					   hei2_text_loop_done = 1	
					ENDIF
				ENDIF
				BREAK
		ENDSWITCH   

				
		
		   										
		
	IF hei2_text_loop_done = 0		
		GOTO hei2_text_loop2
	ENDIF
		
	
	
	hei2_safety_flag = 0
	TIMERA = 0
	WHILE hei2_safety_flag = 0 
	 	GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  hei2_task_status
		IF  hei2_task_status  = FINISHED_TASK
			hei2_safety_flag  = 1
		ENDIF
	WAIT 0 
	ENDWHILE




	//PRINT (HE2_DB) 5000 1 //SECURITY GUARD: We've got him trapped, there's no way off of that ledge"
	// counter  = 17 //HE2_DB  //We've got him trapped, there's no way off that ledge!
	

	CLEAR_CHAR_TASKS scplayer
	CLEAR_LOOK_AT scplayer
	OPEN_SEQUENCE_TASK hei2_dam_dive_seq				
	TASK_GO_TO_COORD_ANY_MEANS -1 -598.5344 2017.5299 75.9845 PEDMOVE_WALK -1
	TASK_ACHIEVE_HEADING -1 70.0
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BD_Panic_02 BD_FIRE 4.0 FALSE FALSE FALSE FALSE -1			
	CLOSE_SEQUENCE_TASK hei2_dam_dive_seq
	PERFORM_SEQUENCE_TASK scplayer hei2_dam_dive_seq
	CLEAR_SEQUENCE_TASK hei2_dam_dive_seq
	 
	//SET_FIXED_CAMERA_POSITION -598.30 2007.47 77.31  0.0 0.0 0.0 // wide shot	
	//POINT_CAMERA_AT_POINT -602.87 2020.21 75.95 JUMP_CUT  // wide shot

	hei2_audio_counter	= 17
	hei2_audio_slot1 	   = 1
	hei2_audio_slot2 	   = 2
	hei2_audio_playing	   = 0
	hei2_text_loop_done	   = 0
	hei2_mobile			   = 0
	hei2_text_timer_diff   = 0
	hei2_text_timer_end    = 0
	hei2_text_timer_start  = 0
	hei2_ahead_counter	   = 0

	hei2_safety_flag = 0
	TIMERA = 0
	WHILE hei2_safety_flag = 0 
	 	
	 	
	 	IF NOT hei2_audio_counter = 0
			IF hei2_audio_playing = 0
				IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot2
					CLEAR_MISSION_AUDIO hei2_audio_slot2
				ENDIF
				hei2_audio_playing = 1
			ENDIF

			IF hei2_audio_playing = 1
				LOAD_MISSION_AUDIO hei2_audio_slot1 hei2_audio[hei2_audio_counter]
				hei2_audio_playing = 2
			ENDIF

			IF hei2_audio_playing = 2
			 	IF HAS_MISSION_AUDIO_LOADED hei2_audio_slot1
					PLAY_MISSION_AUDIO hei2_audio_slot1
					PRINT_NOW $hei2_text[hei2_audio_counter] 10000 1
					hei2_audio_playing = 3
				ENDIF
			ENDIF

			IF hei2_audio_playing = 3
				IF HAS_MISSION_AUDIO_FINISHED hei2_audio_slot1
					CLEAR_THIS_PRINT $hei2_text[hei2_audio_counter]
					IF hei2_audio_slot1 = 1
						hei2_audio_slot1 = 2
						hei2_audio_slot2 = 1
					ELSE
						hei2_audio_slot1 = 1
						hei2_audio_slot2 = 2
					ENDIF
					hei2_audio_counter = 0
					hei2_audio_playing = 0				
				ENDIF
			ENDIF
		ENDIF
	 	
	 	
	 	
	 	
	 	
	 	GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  hei2_task_status
		IF  hei2_task_status  = FINISHED_TASK			
			hei2_safety_flag  = 1
		ELSE
			IF 	hei2_task_status  = PERFORMING_TASK
				GET_SEQUENCE_PROGRESS scplayer hei2_task_status
				IF hei2_task_status = 1
					IF NOT IS_CAR_DEAD hei2_cop_car1
						SET_CAR_FORWARD_SPEED hei2_cop_car1 20.0
						SET_CAR_CRUISE_SPEED hei2_cop_car1 40.0
						CAR_GOTO_COORDINATES hei2_cop_car1 -591.4136 2024.8634 59.1719 
					ENDIF

					IF NOT IS_CAR_DEAD hei2_cop_car3
						SET_CAR_FORWARD_SPEED hei2_cop_car3 20.0
						SET_CAR_CRUISE_SPEED hei2_cop_car3 40.0
						CAR_GOTO_COORDINATES hei2_cop_car3 -591.4136 2024.8634 59.1719 
					ENDIF					
					SET_FIXED_CAMERA_POSITION -597.14 2017.04 78.03  0.0 0.0 0.0 // wide shot	
					POINT_CAMERA_AT_POINT -637.05 2036.76 64.61 JUMP_CUT  // wide shot
				ENDIF								
																	   				
			ENDIF	
		ENDIF
	WAIT 0 
	ENDWHILE
	
	SET_CHAR_COORDINATES scplayer -598.5344 2017.5299 75.9845
	SET_CHAR_HEADING scplayer 70.0
	SET_FIXED_CAMERA_POSITION -601.69 2017.41 78.85  0.0 0.0 0.0 // wide shot	
	POINT_CAMERA_AT_POINT -576.19 2014.02 68.86 JUMP_CUT  // wide shot
	
	OPEN_SEQUENCE_TASK hei2_dam_dive_seq
	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BD_Panic_04 BD_FIRE 4.0 FALSE FALSE FALSE FALSE -1	
	TASK_GO_TO_COORD_ANY_MEANS -1 -596.3235 2015.6079 75.9845 PEDMOVE_WALK -1
	TASK_ACHIEVE_HEADING -1 148.5634		
	CLOSE_SEQUENCE_TASK hei2_dam_dive_seq
	PERFORM_SEQUENCE_TASK scplayer hei2_dam_dive_seq
	CLEAR_SEQUENCE_TASK hei2_dam_dive_seq

	hei2_safety_flag = 0

	TIMERA = 0
	WHILE hei2_safety_flag = 0 
	 	GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  hei2_task_status
		IF  hei2_task_status  = FINISHED_TASK			
			hei2_safety_flag  = 1
		ELSE
			IF 	hei2_task_status  = PERFORMING_TASK
				GET_SEQUENCE_PROGRESS scplayer hei2_task_status				
				
				IF hei2_task_status = 0					

					IF NOT IS_CAR_DEAD hei2_cop_car2
						SET_CAR_FORWARD_SPEED hei2_cop_car2 20.0
						SET_CAR_CRUISE_SPEED hei2_cop_car2 40.0
						CAR_GOTO_COORDINATES hei2_cop_car2 -587.4136 2036.8634 59.1719
					ENDIF

					IF NOT IS_CAR_DEAD hei2_cop_car4
						SET_CAR_FORWARD_SPEED hei2_cop_car4 20.0
						SET_CAR_CRUISE_SPEED hei2_cop_car4 40.0
						CAR_GOTO_COORDINATES hei2_cop_car4 -587.4136 2036.8634 59.1719
					ENDIF

					
				ENDIF
																	   				
			ENDIF	
		ENDIF
	WAIT 0 
	ENDWHILE


   

	/////// jump scene //////
	
		
	SET_FIXED_CAMERA_POSITION -596.51 2000.5 79.70  0.0 0.0 0.0 // wide shot
	POINT_CAMERA_AT_POINT -600.79 2009.02 75.31 JUMP_CUT  // wide shot
		
	 
	CLEAR_LOOK_AT scplayer
	CLEAR_CHAR_TASKS  scplayer
	SET_CHAR_COORDINATES scplayer -596.3235 2015.6079 75.9845
	SET_CHAR_HEADING scplayer 148.5634
		

	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer DAM_Launch DAM_JUMP 4.0 FALSE FALSE FALSE FALSE -1
	
	
	hei2_safety_flag = 0	
	WHILE hei2_safety_flag = 0
		GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM_NON_INTERRUPTABLE  hei2_task_status
		IF hei2_task_status  = FINISHED_TASK
			SET_CHAR_COORDINATES scplayer -613.74 1980.77 52.13
			WAIT 200
			hei2_safety_flag = 1
		ENDIF
	WAIT 0
	ENDWHILE

				
	//// long dive scene //
	//SET_FIXED_CAMERA_POSITION -613.55 1961.16 46.40 0.0 0.0 0.0 // wide shot 						
	//POINT_CAMERA_AT_POINT -614.39 1980.08 44.86 JUMP_CUT

	SET_FIXED_CAMERA_POSITION -607.52 1963.50 41.26 0.0 0.0 0.0 // wide shot 						
	//POINT_CAMERA_AT_POINT  -613.74 1980.77 52.13 JUMP_CUT
	POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT

	DELETE_CAR hei2_cop_car1
	DELETE_CAR hei2_cop_car2
	DELETE_CAR hei2_cop_car3
	DELETE_CAR hei2_cop_car4

	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer DAM_Dive_Loop DAM_JUMP 1000.0 TRUE FALSE FALSE FALSE -1
	
	
	hei2_safety_flag = 0
	WHILE hei2_safety_flag = 0
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer -613.74 1980.77 10.824 50.0 50.0 1.0 FALSE
					   	   	       
			//SET_CHAR_COORDINATES scplayer  -613.93 2032.1 59.4	// top of dam	
			
			hei2_safety_flag = 1
		ENDIF
	WAIT 0
	ENDWHILE

	SET_FIXED_CAMERA_POSITION -620.95 1970.46 4.58 0.0 0.0 0.0 // wide shot			   			   			  			   
	POINT_CAMERA_AT_POINT -610.92 1984.83 -2.79 JUMP_CUT

	//SET_CHAR_COORDINATES scplayer  -613.74 1980.77 -0.8	   												

	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer DAM_Land DAM_JUMP 4.0 FALSE TRUE TRUE FALSE -1			   			   			   			   				   				   			   
	//TASK_PLAY_ANIM_WITH_FLAGS scplayer DAM_Land DAM_JUMP 4.0 FALSE FALSE FALSE FALSE -1 FALSE TRUE	
	hei2_safety_flag = 0
	hei2_camera_flag = 0	
	
	

	WHILE hei2_safety_flag = 0			   				   				   			   			
		GET_SCRIPT_TASK_STATUS scplayer TASK_PLAY_ANIM_NON_INTERRUPTABLE  hei2_task_status
		IF  hei2_task_status  = FINISHED_TASK												
			hei2_safety_flag = 1							 
	    ELSE
			IF hei2_camera_flag = 0
				IF IS_CHAR_PLAYING_ANIM scplayer DAM_Land
					GET_CHAR_ANIM_CURRENT_TIME scplayer DAM_Land hei2_anim_time
					IF hei2_anim_time > 0.115
					   CREATE_FX_SYSTEM water_splash -613.74 1980.77 -0.8 TRUE hei2_water_splash
						IF hei2_water_splash = -1
							//BREAKPOINT failed_to_create_fx_system_water_splash
						ELSE
							PLAY_AND_KILL_FX_SYSTEM hei2_water_splash
							hei2_camera_flag = 1
						ENDIF
					ENDIF					
				ENDIF
			ENDIF
		ENDIF
		WAIT 0 
	ENDWHILE


	SKIP_CUTSCENE_END

	CLEAR_MISSION_AUDIO hei2_audio_slot1
	CLEAR_MISSION_AUDIO hei2_audio_slot2

	CLEAR_PRINTS
	
	DO_FADE 1000 FADE_OUT
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	
	//RESTORE_CLOTHES_STATE
	//BUILD_PLAYER_MODEL player1
	SET_CHAR_COORDINATES_NO_OFFSET scplayer -613.74 1980.77 -0.5 
	SET_CHAR_HEADING scplayer 148.5634

	REMOVE_ANIMATION DAM_JUMP
	REMOVE_ANIMATION BOMBER
	REMOVE_ANIMATION BD_FIRE
	MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car1
	MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car2
	MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car3 
	MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car4
	MARK_MODEL_AS_NO_LONGER_NEEDED 599
	MARK_MODEL_AS_NO_LONGER_NEEDED ENFORCER
	SET_PED_DENSITY_MULTIPLIER 1.0
	SET_CAR_DENSITY_MULTIPLIER 1.0
	RESTORE_CAMERA_JUMPCUT
	
	HIDE_CHAR_WEAPON_FOR_SCRIPTED_CUTSCENE scplayer FALSE
	SWITCH_WIDESCREEN OFF
	
	DO_FADE 1500 FADE_IN
	WHILE GET_FADING_STATUS
	WAIT 0
	ENDWHILE
	
	SET_PLAYER_CONTROL player1 ON
	
	
	hei2_dive_scene_played_flag      = 1
ENDIF
RETURN


///////////////////
/// TEXT LABELS ///
///////////////////
/*
PRINT (hei2_1) 2000 1 //~s~Find the enterance to the generator room
PRINT (hei2_2) 2000 1 
PRINT (hei2_3) 2000 1 //~s~Be careful though, it will all be for nothing if you get spotted doing it.
PRINT (hei2_4) 2000 1 //~r~You've been spotted ! you'll have to come back and try again later
PRINT (hei2_5) 2000 1 //~s~All the generators have been rigged, you need to get out of there without getting caught.
PRINT (hei2_6) 2000 1 
PRINT (hei2_7) 2000 1 //SECURITY GUARD: I Found him, he's over here!
PRINT (hei2_8) 2000 1 //"Thats the guy, arrest him!"
PRINT (hei2_9) 2000 1 //~s~ You must not get caught, find a way to lose the cops.
PRINT (HEI2_90) 2000 1 //~s~ Go to the airport you need a plane to parachute onto the dam quay. 
PRINT (hei2_11) 2000 1 // ~o~ = Open Chute
PRINT (hei2_12) 2000 1 // ~s~Land on the end of the dam quay
PRINT (hei2_13) 2000 1 // ~t~ = Jump From Plane
PRINT (hei2_14) 2000 1 // ~s~ We're over the dam, get ready to jump!
PRINT (hei2_15) 2000 1 // ~s~ GO!
PRINT (HEI2_16) 2000 1 // SECURITY GUARD: We know he's in here somewhere, start looking!
PRINT (HEI2_17) 2000 1 // SECURITY GUARD: The cops are on the way, this thrill seeker aint gonna escape 
PRINT (HEI2_18) 2000 1 // SECURITY GUARD: There's a report of an unauthorised person breaking into the building.
PRINT (HEI2_20) 2000 1 //~s~ This is all about stealth, if you get spotted or make too much noise the whole building will have been alerted.
[HEI2_21) 4000 1 ~s~ You missed the jump point, you'll have to go round again!
[HEI2_22) 4000 1 Hold the L1 button turn left. ~n~ Hold the R1 button to turn right.
[HEI2_23) 4000 1 Holding both the L1 and R1 buttons will increase your speed of decent.
[HEI2_24) 4000 1 Stealth
PRINT (HEI2_25) 2000 1 //~r~ The plane has been destroyed!


PRINT (HEI2_43) 4000 1// "You're too low, we need to gain some height to make the jump."
PRINT (HEI2_44) 4000 1 //"You're too high, we need to lose some height to make the jump."
PRINT (HEI2_45) 4000 1// "Be careful, if you take the direct route it will take us over the SAM sites."
PRINT (HEI2_46) 4000 1 //~s~There's a knife stashed near one of the containers which you may find useful.

PRINT (HEI2_48) 4000 1 //~s~Jack that boat and get the hell out of there. 
PRINT (HEI2_49) 4000 1 //"CLICK"
PRINT (HEI2_50) 4000 1 //"What the..  the door's been locked!"
PRINT (HEI2_47) 4000 1 //SECURITY GUARD: We've got him trapped, there's no way off of that ledge"

DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING hei2_stealth COUNTER_DISPLAY_BAR 1 HEI2_24

/////////////////////////////////////////
///  possible audio from the guards   ///
/////////////////////////////////////////
" You're not supposed to be here "
" Get him away from the generators"
" He's planting bombs on the generators, stop him !"
" Shoot the intruder /tresspasser !"

*/

 // **************************************** Mission heist2 failed ***********************



mission_heist2_failed:
PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
RETURN

   





// **************************************** mission heist2 passed ************************

mission_heist2_passed:

flag_heist_mission_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
REGISTER_MISSION_PASSED ( HEIST_2 ) //Used in the stats 
PLAYER_MADE_PROGRESS 1
//PRINT_WITH_NUMBER_BIG ( M_PASS ) 100 5000 1 //"Mission Passed!"
//ADD_SCORE player1 100
PRINT_WITH_NUMBER_BIG ( M_PASSR ) 5 5000 1 //"Mission Passed!"
PLAY_MISSION_PASSED_TUNE 1
AWARD_PLAYER_MISSION_RESPECT 5//amount of respect

//CLEAR_WANTED_LEVEL player1
//CLEAR_WANTED_LEVEL player1
RETURN
		



//nt_roadblockCI
// RANGER
// COASTGRD
// DINGHY





// ********************************** mission cleanup ***********************************

mission_cleanup_heist2:

/////////////////////////    REMOVE_BLIPS

Para_pickup_flag = 1
GET_GAME_TIMER timer_mobile_start

DISPLAY_RADAR TRUE

RELEASE_WEATHER
SET_RADAR_ZOOM  0
SWITCH_ENTRY_EXIT damin TRUE
SWITCH_ENTRY_EXIT damout TRUE


REMOVE_CAR_RECORDING 805


IF hei2_clothes_changed = 1
	IF IS_PLAYER_PLAYING PLAYER1
		SET_PLAYER_CYCLE_WEAPON_BUTTON PLAYER1 TRUE
	ENDIF
ENDIF


//SET_RADAR_ZOOM 1
SET_PED_DENSITY_MULTIPLIER 1.0
SET_CAR_DENSITY_MULTIPLIER 1.0 // normally 1.0
SET_AREA51_SAM_SITE ON


REMOVE_BLIP hei2_generator_blip[0]
REMOVE_BLIP hei2_generator_blip[1]
REMOVE_BLIP hei2_generator_blip[2]
REMOVE_BLIP hei2_generator_blip[3]
REMOVE_BLIP hei2_generator_blip[4]

REMOVE_BLIP hei2_quay_guard_blip[0]
REMOVE_BLIP hei2_quay_guard_blip[1]


REMOVE_BLIP hei2_generator_room_door_blip
REMOVE_BLIP hei2_plane_blip
REMOVE_BLIP hei2_knife_blip

hei2_counter = 0
WHILE hei2_counter < 6	   	  
REMOVE_BLIP hei2_security_blip[hei2_counter]									
hei2_counter++
ENDWHILE

DELETE_CHECKPOINT hei2_checkpoint


//////////////////////// REMOVE_GROUP

 
 
/////////////////////// CLEAR_ONSCREEN_COUNTER

//CLEAR_ONSCREEN_COUNTER hei2_stealth

//////////////////////  CLEAR_ONSCREEN_TIMER


/////////////////////  MARK MODELs AS NO LONGER NEEDED


MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[0]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[1]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[2]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[3]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_bomb_obj[4]

				
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[0]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[1]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[2]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[3]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[4]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[5]
MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[6]
//MARK_OBJECT_AS_NO_LONGER_NEEDED hei2_quay_object[7]		

MARK_CHAR_AS_NO_LONGER_NEEDED hei2_quay_guard[0]
MARK_CHAR_AS_NO_LONGER_NEEDED hei2_quay_guard[1]

MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[0]
MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[1]
MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[2]
MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[3]
MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[4]
MARK_CHAR_AS_NO_LONGER_NEEDED hei2_security_ped[5]

MARK_CHAR_AS_NO_LONGER_NEEDED hei2_temp_ped[0]
MARK_CHAR_AS_NO_LONGER_NEEDED hei2_temp_ped[0]



//MARK_CHAR_AS_NO_LONGER_NEEDED hei2_fisherman_ped


MARK_CAR_AS_NO_LONGER_NEEDED hei2_player_boat
MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car1
MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car2
MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car3 
MARK_CAR_AS_NO_LONGER_NEEDED hei2_cop_car4

MARK_MODEL_AS_NO_LONGER_NEEDED  ENFORCER
MARK_MODEL_AS_NO_LONGER_NEEDED  599
MARK_MODEL_AS_NO_LONGER_NEEDED	SMASHBOXPILE
MARK_MODEL_AS_NO_LONGER_NEEDED	K_CARGO1
MARK_MODEL_AS_NO_LONGER_NEEDED	KMB_CONTAINER_RED
MARK_MODEL_AS_NO_LONGER_NEEDED	K_CARGO4
MARK_MODEL_AS_NO_LONGER_NEEDED gun_para



MARK_MODEL_AS_NO_LONGER_NEEDED WMYSGRD
MARK_MODEL_AS_NO_LONGER_NEEDED WMYMECH
MARK_MODEL_AS_NO_LONGER_NEEDED NITESTICK
MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
MARK_MODEL_AS_NO_LONGER_NEEDED KNIFECUR

MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL//DYNAMITE
MARK_MODEL_AS_NO_LONGER_NEEDED REEFER
//MARK_MODEL_AS_NO_LONGER_NEEDED WMYTEX1
MARK_MODEL_AS_NO_LONGER_NEEDED	KNIFECUR





LOCK_CAR_DOORS hei2_jump_plane CARLOCK_UNLOCKED
					
MARK_CAR_AS_NO_LONGER_NEEDED   hei2_jump_plane
MARK_MODEL_AS_NO_LONGER_NEEDED NEVADA
MARK_MODEL_AS_NO_LONGER_NEEDED SW_BIT_09

REMOVE_PICKUP hei2_knife_pickup

REMOVE_ANIMATION BOMBER
REMOVE_ANIMATION DAM_JUMP
REMOVE_ANIMATION BD_FIRE
REMOVE_DECISION_MAKER hei2_dam_sec_decision


/////////////////////////////////////
//CLEAR_ALL_VIEW_VARIABLES



flag_player_on_mission = 0
GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission
MISSION_HAS_FINISHED
//UNLOAD_SPECIAL_CHARACTER 1
RETURN

 
}




							  