MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// ************************************* Mansion 3 *****************************************
// *********************************** Pick Up Sweet ***************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
SCRIPT_NAME MANSIO3

// Mission start stuff

GOSUB mission_start_mansion3

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_mansion3_failed			   						 
ENDIF

GOSUB mission_cleanup_mansion3

MISSION_END
 
// Variables for mission

VAR_INT has_seen_this_cutscene

{
			   
// NPC's
LVAR_INT m3_dealer_blip[6] m3_dealer[6]	m3_cracker[6]
LVAR_INT dummy_dealer	//	this ped is just used to stop dealer.sc being streamed out

LVAR_FLOAT sw_X sw_Y sw_Z  

// Blips
LVAR_INT m3_grove_blip m3_bus_blip m3_sweet_blip m3_display_txt 

// Vehicles
LVAR_INT m3_ply_car m3_cut_car passengers_cut_car m3_stolen_car

// Mission
LVAR_INT m3_mission_status m3_has_been_passed

LVAR_INT flying_drugs 

LVAR_FLOAT m3_drug_X m3_drug_Y m3_drug_Z

LVAR_INT m3_once_only m3_bus_sign1

LVAR_FLOAT m3_ply_heading

LVAR_INT m3_dealers_killed

LVAR_INT m3_hood_taken

LVAR_INT m3_gan1 m3_gan2 

LVAR_INT m3_help_take_hood

LVAR_INT m3_switch

LVAR_INT m3_hood_blip

LVAR_INT v m3_taken_txt

LVAR_INT m3_playing

LVAR_INT m3_audio 

LVAR_INT passengers_in_cut_car

LVAR_INT m3_can_trigger_a_war

LVAR_TEXT_LABEL m3_print

VAR_INT m3_in_the_zone m3_sweets_health

// **************************************** Mission Start **********************************

mission_start_mansion3:

LOAD_MISSION_TEXT MAN_3

SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION TRUE
					  
SET_FADING_COLOUR 0 0 0

REGISTER_MISSION_GIVEN

flag_player_on_mission = 1

m3_playing = 2

WAIT 0

LOAD_SPECIAL_CHARACTER 1 sweet

//intro cut scene
SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

FORCE_WEATHER_NOW WEATHER_SUNNY_LA

SET_AREA_VISIBLE 5

LOAD_CUTSCENE BHILL3a
 
WHILE NOT HAS_CUTSCENE_LOADED
	WAIT 0
ENDWHILE
 
START_CUTSCENE

SET_FADING_COLOUR 0 0 0
DO_FADE 1000 FADE_IN
  
WHILE NOT HAS_CUTSCENE_FINISHED
	WAIT 0
ENDWHILE

SET_FADING_COLOUR 0 0 0
DO_FADE 0 FADE_OUT

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE
 
CLEAR_CUTSCENE

SET_AREA_VISIBLE 0

RELEASE_WEATHER

// *****************************************************************************************
// *																					   *
// *                                	  Requests   				   					   *
// *																					   *
// *****************************************************************************************
		
REQUEST_MODEL vincent
REQUEST_MODEL bmydrug
REQUEST_MODEL FAM2
REQUEST_MODEL ak47
REQUEST_MODEL WMYDRUG

REQUEST_ANIMATION GANGS
REQUEST_ANIMATION CRACK

REQUEST_CAR_RECORDING 488

STREAM_SCRIPT Dealer.sc

LOAD_ALL_MODELS_NOW 

WHILE NOT HAS_MODEL_LOADED vincent
OR NOT HAS_SPECIAL_CHARACTER_LOADED 1
OR NOT HAS_MODEL_LOADED FAM2

	WAIT 0

ENDWHILE

WHILE NOT HAS_ANIMATION_LOADED GANGS
OR NOT HAS_ANIMATION_LOADED CRACK
OR NOT HAS_MODEL_LOADED bmydrug

	WAIT 0

ENDWHILE

WHILE NOT HAS_STREAMED_SCRIPT_LOADED Dealer.sc
OR NOT HAS_CAR_RECORDING_BEEN_LOADED 488
OR NOT HAS_MODEL_LOADED ak47
OR NOT HAS_MODEL_LOADED WMYDRUG
	
	WAIT 0
	
ENDWHILE

REQUEST_COLLISION 1251.5413 -808.7633 

// Gang stuff it'll work fine **************************************************************

SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 0

SET_ZONE_GANG_STRENGTH GAN1 GANG_FLAT 40

CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

// *****************************************************************************************

CREATE_CHAR PEDTYPE_CIVMALE bmydrug 2488.6169 -1671.5364 -10.0 dummy_dealer // This ped is just used to stop dealer.sc being streamed out
START_NEW_STREAMED_SCRIPT Dealer.sc dummy_dealer

// ****************************************START OF CUTSCENE********************************

ADD_BLIP_FOR_COORD 2514.4614 -1674.1044 12.6425 m3_grove_blip
				    
ADD_BLIP_FOR_COORD 1521.2521 -1673.3357 12.5607 m3_bus_blip

CHANGE_BLIP_DISPLAY m3_grove_blip NEITHER

CHANGE_BLIP_DISPLAY m3_bus_blip BOTH

CLEAR_AREA 1245.0488 -811.1318 83.1477 10.0 TRUE

CREATE_CAR vincent 1245.0488 -811.1318 83.1477 m3_ply_car

CHANGE_CAR_COLOUR m3_ply_car CARCOLOUR_BLACK CARCOLOUR_BLACK

SET_CAR_HEADING m3_ply_car 181.4888 

SET_CAR_TRACTION m3_ply_car 2.0

SET_CHAR_COORDINATES scplayer 1251.5413 -808.7633 83.1484 

SET_CHAR_HEADING scplayer 109.0044   
 
ADD_BLIP_FOR_COORD 2485.5271 -1668.5282 12.3438 m3_hood_blip

CHANGE_BLIP_DISPLAY m3_hood_blip NEITHER

SET_CAMERA_BEHIND_PLAYER

RESTORE_CAMERA_JUMPCUT

LOAD_SCENE_IN_DIRECTION 1251.5413 -808.7633 83.1484 109.0044  

GOSUB mansion3_fade_in	 

PRINT_NOW ( MAN3_01 ) 7000 1 // ~s~Pick up Sweet from the ~y~Precinct~s~.

WHILE NOT IS_CHAR_DEAD scplayer

	WAIT 0

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_S
		
		GOTO mission_mansion3_passed 
	
	ENDIF

	GOSUB man3_keys

	GOSUB m3_play_sample

	// ****************************** Going to bus stop to get sweet ***********************

	IF m3_mission_status = 0

		IF IS_CHAR_IN_ANY_CAR scplayer
													
			STORE_CAR_CHAR_IS_IN scplayer m3_cut_car

			GET_MAXIMUM_NUMBER_OF_PASSENGERS m3_cut_car passengers_cut_car

			GET_NUMBER_OF_PASSENGERS m3_cut_car passengers_in_cut_car

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer 1521.1943 -1671.9128 12.5625 3.0 3.0 3.0 TRUE

			IF passengers_cut_car = 0

				PRINT_NOW ( MAN3_19 ) 3000 1 // ~s~Get a bigger vehicle!

			ENDIF

			IF passengers_in_cut_car = passengers_cut_car

				IF IS_CHAR_IN_ANY_HELI scplayer
				OR IS_CHAR_IN_ANY_PLANE scplayer
				OR IS_CHAR_IN_FLYING_VEHICLE scplayer
				    
					PRINT_NOW ( MAN3_69 ) 3000 1  // The vehicle is too full, there's no room for Sweet!

				ELSE
					PRINT_NOW ( MAN3_34 ) 3000 1  // You need a vehicle to pick up Sweet.
				ENDIF

			ENDIF

			// Player has arrived at the bus stop ********************************************

			IF passengers_cut_car > 0
			AND NOT passengers_in_cut_car = passengers_cut_car

				IF IS_CHAR_IN_ANY_CAR scplayer

					SET_PLAYER_CONTROL player1 OFF

					IF IS_CHAR_IN_ANY_CAR scplayer
						STORE_CAR_CHAR_IS_IN scplayer car
						TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
					ENDIF

					//CLEAR_ONSCREEN_TIMER m3_bus_clock

					SET_PLAYER_CONTROL player1 FALSE
						 
					//outside LA police station

					SET_FADING_COLOUR 0 0 0

					DO_FADE 1000 FADE_OUT

					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE

					MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1

					IF NOT IS_CAR_DEAD m3_ply_car
						IF LOCATE_CAR_3D m3_ply_car 1542.7299 -1675.6971 12.5551 15.0 15.0 15.0 FALSE 

							DELETE_CAR m3_ply_car
											
						ENDIF
					ENDIF

					CLEAR_AREA 1542.7299 -1675.6971 12.5551 60.0 TRUE 

					LOAD_CUTSCENE BHILL3b
					 
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

					IF IS_CHAR_IN_ANY_CAR scplayer

						IF NOT IS_CAR_DEAD car
					   
					 		SET_CAR_COORDINATES car 1521.1943 -1671.9128 12.5625 

						ENDIF
												
					ENDIF

					IF NOT IS_CAR_DEAD car

						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

						CREATE_CHAR_AS_PASSENGER car PEDTYPE_MISSION2 SPECIAL01 0 sweet

						SET_CHAR_HEALTH sweet 700
						
						SET_CHAR_MAX_HEALTH sweet 700

						SET_ANIM_GROUP_FOR_CHAR sweet gang2

						SET_CHAR_NEVER_TARGETTED sweet TRUE

						SET_CHAR_SUFFERS_CRITICAL_HITS sweet FALSE

						SET_CHAR_ACCURACY sweet 90 

					ENDIF
										
					IF NOT IS_CHAR_DEAD sweet
					AND NOT IS_CAR_DEAD car
					AND NOT IS_CHAR_DEAD scplayer

						IF NOT IS_CHAR_IN_CAR scplayer car

							WARP_CHAR_INTO_CAR scplayer car 

						ENDIF

						ADD_BLIP_FOR_CHAR sweet m3_sweet_blip

						SET_BLIP_AS_FRIENDLY m3_sweet_blip TRUE

						CHANGE_BLIP_DISPLAY m3_sweet_blip NEITHER

					ENDIF

					TIMERB = 0

					CHANGE_BLIP_DISPLAY m3_grove_blip BOTH

					CHANGE_BLIP_DISPLAY m3_bus_blip NEITHER

					IF NOT IS_CHAR_DEAD sweet

						MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

						SET_GROUP_MEMBER Players_group sweet

					ENDIF

					SET_CAMERA_BEHIND_PLAYER

					RESTORE_CAMERA_JUMPCUT

					SET_PLAYER_CONTROL player1 ON

					GET_CHAR_HEADING scplayer m3_ply_heading

					IF NOT IS_CAR_DEAD car
						IF m3_ply_heading > 90.0
						AND m3_ply_heading < 270.0

							LOAD_SCENE_IN_DIRECTION 1521.1943 -1671.9128 12.5625 180.0000

							SET_CAR_HEADING car 180.0000

						ELSE

							LOAD_SCENE_IN_DIRECTION 1521.1943 -1671.9128 12.5625 0.0000

							SET_CAR_HEADING car 0.0000

						ENDIF 	
					ENDIF

					GOSUB mansion3_fade_in

					m3_display_txt = 1

					m3_mission_status = 1  

				ELSE

					PRINT_NOW ( MAN3_08 ) 4000 1 // You need a car to pick up sweet

				ENDIF

			ENDIF

		ENDIF

	ENDIF // mission_status = 0

	IF NOT m3_display_txt = 0

		IF TIMERB > 1000 
		AND m3_display_txt = 1

			IF NOT IS_CHAR_DEAD ryder

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder TRUE

			ENDIF

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer TRUE

			ENDIF

			$m3_print = &MAN3_AA	// Shit is all fucked up now, dude.			
			m3_audio = SOUND_MAN3_AA
			GOSUB m3_load_sample

			m3_display_txt = 2

		ENDIF
		IF m3_playing = 2 
		AND m3_display_txt = 2

			$m3_print = &MAN3_AB	// Yeah.
			m3_audio = SOUND_MAN3_AB
			GOSUB m3_load_sample

			m3_display_txt = 3

		ENDIF
		IF m3_playing = 2  
		AND m3_display_txt = 3

			$m3_print = &MAN3_AC	// What you want, it ain’t round here no more.
			m3_audio = SOUND_MAN3_AC
			GOSUB m3_load_sample

			m3_display_txt = 4

		ENDIF
		IF m3_playing = 2  
		AND m3_display_txt = 4

			$m3_print = &MAN3_AD	// Just take me to mom’s house.
			m3_audio = SOUND_MAN3_AD
			GOSUB m3_load_sample

			m3_display_txt = 5

		ENDIF
		IF m3_playing = 2  
		AND m3_display_txt = 5
							
			PRINT_NOW ( MAN3_05 ) 7000 1 // ~s~Drive Sweet to ~y~Grove Street

			m3_display_txt = 0

			IF NOT IS_CHAR_DEAD ryder

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH ryder FALSE

			ENDIF

			IF NOT IS_CHAR_DEAD scplayer

				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH scplayer FALSE

			ENDIF

		ENDIF

	ENDIF

	// ******************************************************************************************************
	// *																									*
	// *                                        DRIVING TO GROVE	 										*
	// *																									*
	// ******************************************************************************************************

	IF m3_mission_status = 1
	OR m3_mission_status = 2

		IF IS_CHAR_DEAD sweet

			PRINT_NOW ( MAN3_11 ) 4000 1 // ~r~You let Sweet get killed

			GOTO mission_mansion3_failed

		ENDIF

		IF NOT IS_CHAR_DEAD sweet

			GET_CHAR_COORDINATES sweet sw_X sw_Y sw_Z

		ENDIF

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer sw_X sw_Y sw_Z 30.0 30.0 30.0 FALSE

			CHANGE_BLIP_DISPLAY m3_sweet_blip NEITHER			
			CHANGE_BLIP_DISPLAY m3_grove_blip BOTH

			IF NOT IS_GROUP_MEMBER sweet Players_group

				MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

				SET_GROUP_MEMBER Players_group sweet
				
			ENDIF

			m3_mission_status = 1

		ELSE 

			PRINT_NOW ( MAN3_03 ) 4000 1 // You lost sweet, go back and get him

			CHANGE_BLIP_DISPLAY m3_sweet_blip BOTH
			CHANGE_BLIP_DISPLAY m3_grove_blip NEITHER
			
			IF IS_GROUP_MEMBER sweet Players_group

				REMOVE_CHAR_FROM_GROUP sweet
				
			ENDIF

			m3_mission_status = 2

		ENDIF

		IF m3_mission_status = 1

 			IF LOCATE_CHAR_ANY_MEANS_3D scplayer 2514.4614 -1674.1044 12.6425 4.0 4.0 4.0 TRUE

				SET_PLAYER_CONTROL player1 OFF
				IF IS_CHAR_IN_ANY_CAR scplayer
					STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car
					TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000
				ENDIF

				stick_me_in_here_instead:

				SET_FADING_COLOUR 0 0 0

				DO_FADE 1000 FADE_OUT

				WHILE GET_FADING_STATUS
					WAIT 0
				ENDWHILE

				MAKE_PLAYER_SAFE_FOR_CUTSCENE Player1

				IF NOT IS_CAR_DEAD m3_ply_car
					IF LOCATE_CAR_3D m3_ply_car 2492.8887 -1666.5640 12.3438 15.0 15.0 15.0 FALSE 

						CLEAR_AREA 2482.9939 -1653.6089 12.4610 10.0 TRUE 

						SET_CAR_COORDINATES m3_ply_car 2482.9939 -1653.6089 12.4610 
						
						SET_CAR_HEADING m3_ply_car 273.1021 
			
					ENDIF
				ENDIF

				CLEAR_AREA 2492.8887 -1666.5640 12.3438 60.0 TRUE

				LOAD_CUTSCENE BHILL3c
				 
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
				
				LVAR_INT m3_deal_seq m3_buy_seq

				OPEN_SEQUENCE_TASK m3_deal_seq

					TASK_PLAY_ANIM -1 IDLE_chat PED 1.0 TRUE FALSE FALSE FALSE -1						

					SET_SEQUENCE_TO_REPEAT m3_deal_seq 1

				CLOSE_SEQUENCE_TASK m3_deal_seq

				OPEN_SEQUENCE_TASK m3_buy_seq

					TASK_PAUSE -1 1000
						
					SET_SEQUENCE_TO_REPEAT m3_buy_seq 1

				CLOSE_SEQUENCE_TASK m3_buy_seq

				CHANGE_BLIP_DISPLAY m3_grove_blip NEITHER
				
				m3_mission_status = 3

				CREATE_CHAR PEDTYPE_CIVMALE bmydrug 2429.1899 -1677.5370 12.7231 m3_dealer[0]				

				SET_CHAR_HEADING m3_dealer[0] 306.1672 

				PERFORM_SEQUENCE_TASK m3_dealer[0] m3_deal_seq
						
				ADD_BLIP_FOR_CHAR m3_dealer[0] m3_dealer_blip[0]

				CREATE_CHAR PEDTYPE_CIVMALE FAM2 2430.5864 -1677.0746 12.7135 m3_cracker[1]				

				SET_CHAR_HEADING m3_cracker[1] 80.5280 

				PERFORM_SEQUENCE_TASK m3_cracker[1] m3_buy_seq

				CREATE_CHAR PEDTYPE_CIVMALE FAM2 2513.3638 -1649.8652 13.5588 m3_cracker[2]				

				SET_CHAR_HEADING m3_cracker[2] 140.4164  

				TASK_PLAY_ANIM m3_cracker[2] crckidle1 CRACK 1.0 TRUE FALSE FALSE FALSE -1

				APPLY_BRAKES_TO_PLAYERS_CAR player1 OFF

				GOSUB mansion3_set_camera
				
				LOAD_SCENE_IN_DIRECTION 2424.1841 -1679.5802 12.7653 322.7617 
														    
				SET_FIXED_CAMERA_POSITION 2427.2271 -1681.1195 13.8910 0.0 0.0 0.0 // Bike from front
				POINT_CAMERA_AT_POINT 2427.8079 -1680.3058 13.8707 JUMP_CUT

				PRINT_NOW ( MAN3_20 ) 5000 1 // Kill any crack dealers in your hood to keep your gang healthy

				GOSUB mansion3_fade_in

				WAIT 1000

				TIMERB = 0
				WHILE TIMERB < 4000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					AND has_seen_this_cutscene = 1
						GOTO m3_skip_the_cut_2
					ENDIF
				ENDWHILE 

				SET_FIXED_CAMERA_POSITION 2507.9839 -1652.1989 15.6974 0.0 0.0 0.0 // Bike from front
				POINT_CAMERA_AT_POINT 2508.8923 -1651.9133 15.3924 JUMP_CUT

				PRINT_NOW ( MAN3_21 ) 5000 1 // If a gang member is fucked on crack you won't be able to use him

				TIMERB = 0
				WHILE TIMERB < 5000
					WAIT 0
					IF IS_BUTTON_PRESSED PAD1 CROSS
					AND has_seen_this_cutscene = 1
						GOTO m3_skip_the_cut_2
					ENDIF
				ENDWHILE 

				m3_skip_the_cut_2:

				has_seen_this_cutscene = 1

				m3_can_trigger_a_war = 1
				
				CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION TRUE

				IF NOT IS_CHAR_DEAD scplayer

					IF IS_CHAR_IN_ANY_CAR scplayer

						WARP_CHAR_FROM_CAR_TO_COORD scplayer 2506.0593 -1671.9681 12.3790

					ELSE

						SET_CHAR_COORDINATES scplayer 2506.0593 -1671.9681 12.3790

					ENDIF

					SET_CAMERA_BEHIND_PLAYER

					SET_CHAR_HEADING scplayer 62.4295
				
				ENDIF

				IF NOT IS_CHAR_DEAD sweet

					IF IS_CHAR_IN_ANY_CAR sweet

						WARP_CHAR_FROM_CAR_TO_COORD sweet 2506.5671 -1670.2120 12.3822

					ELSE

						SET_CHAR_COORDINATES sweet 2506.5671 -1670.2120 12.3822

					ENDIF

					GIVE_WEAPON_TO_CHAR sweet WEAPONTYPE_AK47 300
					 
					SET_CURRENT_CHAR_WEAPON sweet WEAPONTYPE_AK47

					SET_CHAR_HEADING sweet 113.0677

				ENDIF

				IF NOT IS_GROUP_MEMBER sweet Players_group

					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

					SET_GROUP_MEMBER Players_group sweet
					
				ENDIF

				SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 0

				SET_ZONE_GANG_STRENGTH GAN1 GANG_FLAT 40

				SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 0

				SET_ZONE_GANG_STRENGTH GAN2 GANG_FLAT 40

				DELETE_CHAR m3_cracker[1]

				m3_display_txt = 0

				GOSUB mansion3_restore_camera
				
				IF NOT IS_CHAR_DEAD sweet
				 
					SET_CHAR_HEALTH sweet 700

					SET_CHAR_MAX_HEALTH sweet 700

					GET_CHAR_HEALTH sweet m3_sweets_health

				ENDIF
								
				m3_sweets_health = m3_sweets_health / 7

				DISPLAY_ONSCREEN_COUNTER_WITH_STRING m3_sweets_health COUNTER_DISPLAY_BAR ( MAN3_32 )  // Sweet

			 //	GOTO mission_mansion3_passed

			ENDIF  

		ENDIF

	ENDIF // mission_status = 1 or 2

	// ***********************************************************************************************
	// *																							 *
	// *							 	   Spawn the dealers   	 	 								 *
	// *																							 *
	// ***********************************************************************************************

	IF m3_mission_status = 3

		PRINT_NOW ( MAN3_22 ) 7000 1 // ~s~Clean the hood of any ~r~dealers~s~!	

		CREATE_CHAR PEDTYPE_DEALER WMYDRUG 2482.0872 -1696.1189 12.5267 m3_dealer[1]

		SET_CHAR_HEADING m3_dealer[1] 0.0950   
		
		ADD_BLIP_FOR_CHAR m3_dealer[1] m3_dealer_blip[1]

		CREATE_CHAR PEDTYPE_DEALER bmydrug 2451.6807 -1644.0833 12.4570 m3_dealer[2]

		SET_CHAR_HEADING m3_dealer[2] 186.2393   
		
		ADD_BLIP_FOR_CHAR m3_dealer[2] m3_dealer_blip[2]

		CREATE_CHAR PEDTYPE_DEALER WMYDRUG 2415.7903 -1631.1433 12.5114 m3_dealer[3]

		SET_CHAR_HEADING m3_dealer[3] 287.5167   
		
		ADD_BLIP_FOR_CHAR m3_dealer[3] m3_dealer_blip[3] 

		CREATE_CHAR PEDTYPE_DEALER bmydrug 2364.3608 -1666.7748 12.5469 m3_dealer[4]

		SET_CHAR_HEADING m3_dealer[4] 343.1245   
		
		ADD_BLIP_FOR_CHAR m3_dealer[4] m3_dealer_blip[4] 

		CREATE_CHAR PEDTYPE_DEALER bmydrug 2370.3333 -1637.4197 12.4770 m3_dealer[5]

		SET_CHAR_HEADING m3_dealer[5] 140.6299   
		
		ADD_BLIP_FOR_CHAR m3_dealer[5] m3_dealer_blip[5] 

		START_NEW_STREAMED_SCRIPT Dealer.sc m3_dealer[0]
		
		START_NEW_STREAMED_SCRIPT Dealer.sc m3_dealer[1]
		
		START_NEW_STREAMED_SCRIPT Dealer.sc m3_dealer[2]
		
		START_NEW_STREAMED_SCRIPT Dealer.sc m3_dealer[3]
		
		START_NEW_STREAMED_SCRIPT Dealer.sc m3_dealer[4]
		
		START_NEW_STREAMED_SCRIPT Dealer.sc m3_dealer[5]

		DELETE_CHAR dummy_dealer //	This ped is just used to stop dealer.sc being streamed out

		m3_mission_status = 4

	ENDIF

	IF m3_mission_status = 4

		IF NOT IS_CHAR_DEAD sweet

			GET_CHAR_HEALTH sweet m3_sweets_health

			m3_sweets_health = m3_sweets_health / 7

		ENDIF

		GET_ZONE_GANG_STRENGTH GAN1 GANG_FLAT m3_gan1

		IF m3_gan1 = 0

			IF m3_hood_taken = 0

				m3_hood_taken = 1

				IF m3_dealers_killed = 0

					GOSUB mansion3_set_camera

					IF NOT IS_CHAR_DEAD scplayer

						CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

					ENDIF

					IF NOT IS_CHAR_DEAD sweet

						CLEAR_CHAR_TASKS_IMMEDIATELY sweet

						TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

					ENDIF

					PRINT_NOW ( MAN3_33 ) 4000 1 // The Hood is yours!
					
					WAIT 4000

					SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 40

					SET_ZONE_GANG_STRENGTH GAN1 GANG_FLAT 0

					SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER1

					SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_GROVE PEDTYPE_GANG_FLAT 

					GOSUB mansion3_restore_camera

					TIMERB = 0

				ELSE

					GOTO m3_end_cutscene

				ENDIF

			ENDIF

		ENDIF

		IF m3_hood_taken = 1
		AND TIMERB > 4000
		AND m3_taken_txt = 0
		
			PRINT_NOW ( MAN3_29 ) 10000 1 // Now kill the dealers!
		
			m3_taken_txt = 1

		ENDIF

		// ***********************************************************************************************
		// *																							 *
		// *						   Make sure player activates correct zone							 *
		// *																							 *
		// ***********************************************************************************************
			 
	  	IF IS_PLAYER_IN_INFO_ZONE player1 GAN1
		AND m3_hood_taken = 0
		AND m3_can_trigger_a_war = 1

			IF m3_switch = 0
			AND NOT IS_GANG_WAR_GOING_ON

				CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION TRUE

				SET_GANG_WARS_ACTIVE TRUE

				IF DOES_BLIP_EXIST m3_hood_blip

					REMOVE_BLIP m3_hood_blip

				ENDIF

				m3_switch = 1

			ENDIF

			m3_in_the_zone = 1

		ENDIF

		IF NOT IS_PLAYER_IN_INFO_ZONE player1 GAN1
		AND m3_hood_taken = 0 
		AND m3_can_trigger_a_war = 1

			IF m3_switch = 1
			AND NOT IS_GANG_WAR_GOING_ON

				CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

				SET_GANG_WARS_ACTIVE FALSE

				PRINT_NOW ( MAN3_31 ) 6000 1 // ~s~Go back and take the hood at ~y~Grove Street~s~.

				IF NOT DOES_BLIP_EXIST m3_hood_blip

					ADD_BLIP_FOR_COORD 2485.5271 -1668.5282 12.3438 m3_hood_blip

				ENDIF

				m3_switch = 0

			ENDIF

			m3_in_the_zone = 0

		ENDIF

		IF IS_CHAR_DEAD sweet

			PRINT_NOW ( MAN3_11 ) 4000 1 // ~r~You let Sweet get killed

			GOTO mission_mansion3_failed

		ENDIF

		IF NOT IS_CHAR_DEAD sweet

			GET_CHAR_COORDINATES sweet sw_X sw_Y sw_Z

		ENDIF

		// ***********************************************************************************************
		// *																							 *
		// *							 	 	 Left Sweet behind 					   	 	 			 *
		// *																							 *
		// ***********************************************************************************************

		IF LOCATE_CHAR_ANY_MEANS_3D scplayer sw_X sw_Y sw_Z 35.0 35.0 35.0 FALSE
		
			IF m3_once_only = 1
					
				CHANGE_BLIP_DISPLAY m3_sweet_blip NEITHER			

				CHANGE_BLIP_DISPLAY m3_dealer_blip[0] BOTH
				CHANGE_BLIP_DISPLAY m3_dealer_blip[1] BOTH
				CHANGE_BLIP_DISPLAY m3_dealer_blip[2] BOTH
				CHANGE_BLIP_DISPLAY m3_dealer_blip[3] BOTH
				CHANGE_BLIP_DISPLAY m3_dealer_blip[4] BOTH
				CHANGE_BLIP_DISPLAY m3_dealer_blip[5] BOTH

				IF NOT IS_CHAR_DEAD	sweet

					MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1

					SET_GROUP_MEMBER Players_group sweet

				ENDIF

				m3_once_only = 0

			ENDIF

		ELSE
		 
			IF m3_once_only = 0

				PRINT_NOW ( MAN3_03 ) 10000 1 // You lost sweet, go back and get him

				CHANGE_BLIP_DISPLAY m3_sweet_blip BOTH

				CHANGE_BLIP_DISPLAY m3_dealer_blip[0] NEITHER
				CHANGE_BLIP_DISPLAY m3_dealer_blip[1] NEITHER
				CHANGE_BLIP_DISPLAY m3_dealer_blip[2] NEITHER
				CHANGE_BLIP_DISPLAY m3_dealer_blip[3] NEITHER
				CHANGE_BLIP_DISPLAY m3_dealer_blip[4] NEITHER
				CHANGE_BLIP_DISPLAY m3_dealer_blip[5] NEITHER

				IF NOT IS_CHAR_DEAD	sweet	  

					REMOVE_CHAR_FROM_GROUP sweet	 

				ENDIF		 

				m3_once_only = 1  

			ENDIF  

		ENDIF

		// ***********************************************************************************************
		// *																							 *
		// *							    Dealer dies remove his blip 	  				 			 *
		// *																							 *
		// ***********************************************************************************************

		IF IS_CHAR_DEAD	m3_dealer[0]
		AND DOES_BLIP_EXIST m3_dealer_blip[0]

			REMOVE_BLIP m3_dealer_blip[0]

		ENDIF

		IF IS_CHAR_DEAD	m3_dealer[1]
		AND DOES_BLIP_EXIST m3_dealer_blip[1]

			REMOVE_BLIP m3_dealer_blip[1]

		ENDIF

		IF IS_CHAR_DEAD	m3_dealer[2]
		AND DOES_BLIP_EXIST m3_dealer_blip[2]

			REMOVE_BLIP m3_dealer_blip[2]

		ENDIF

		IF IS_CHAR_DEAD	m3_dealer[3]
		AND DOES_BLIP_EXIST m3_dealer_blip[3]

			REMOVE_BLIP m3_dealer_blip[3]

		ENDIF

		IF IS_CHAR_DEAD	m3_dealer[4]
		AND DOES_BLIP_EXIST m3_dealer_blip[4]

			REMOVE_BLIP m3_dealer_blip[4]

		ENDIF

		IF IS_CHAR_DEAD	m3_dealer[5]
		AND DOES_BLIP_EXIST m3_dealer_blip[5]

			REMOVE_BLIP m3_dealer_blip[5]
									    
		ENDIF

		IF m3_help_take_hood = 1
		AND TIMERA > 4000

			PRINT_HELP ( MAN3_30 ) // ~s~Shoot some Ballas to start a gang war!

			m3_help_take_hood = 2

		ENDIF

		// ***********************************************************************************************
		// *																							 *
		// *							     Player kills all the dealers					 			 *
		// *																							 *
		// ***********************************************************************************************

		IF IS_CHAR_DEAD m3_dealer[0]
		AND IS_CHAR_DEAD m3_dealer[1]
		AND IS_CHAR_DEAD m3_dealer[2]
		AND IS_CHAR_DEAD m3_dealer[3]
		AND IS_CHAR_DEAD m3_dealer[4]
		AND IS_CHAR_DEAD m3_dealer[5]

			IF m3_dealers_killed = 0

				m3_dealers_killed = 1

				IF m3_hood_taken = 0

					PRINT_NOW ( MAN3_28 ) 10000 1 // Now take back the hood!

					TIMERA = 0

					m3_help_take_hood = 1

				ELSE

					GOTO m3_end_cutscene

				ENDIF

			ENDIF

		ENDIF

	ENDIF

ENDWHILE

GOTO mission_mansion3_failed

// ***********************************************************************************************
// *																							 *
// *							 		The Last Cutscene	 									 *
// *																							 *
// ***********************************************************************************************

m3_end_cutscene:

	GOSUB mansion3_fade_out
	GOSUB mansion3_set_camera
	 
	IF NOT IS_CHAR_DEAD scplayer
	AND NOT IS_CHAR_DEAD sweet

		REMOVE_CHAR_FROM_GROUP sweet

		CLEAR_AREA 2512.7610 -1672.5204 12.5034 20.0 TRUE 
							 
		DELETE_CAR m3_ply_car

		IF IS_CHAR_IN_ANY_CAR scplayer 

			WARP_CHAR_FROM_CAR_TO_COORD scplayer 2512.7610 -1672.5204 12.5034

			WARP_CHAR_FROM_CAR_TO_COORD sweet 2514.7876 -1673.9382 12.6861

		ELSE

			SET_CHAR_COORDINATES scplayer 2512.7610 -1672.5204 12.5034

			SET_CHAR_COORDINATES sweet 2514.7876 -1673.9382 12.6861

		ENDIF
					 
		DELETE_CAR m3_ply_car

		SET_CHAR_HEADING scplayer 240.2609 

		TASK_TURN_CHAR_TO_FACE_CHAR scplayer sweet

		SET_CHAR_HEADING sweet 73.5014  

		TASK_TURN_CHAR_TO_FACE_CHAR sweet scplayer

		TASK_TOGGLE_PED_THREAT_SCANNER sweet FALSE FALSE FALSE
		
		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		CLEAR_CHAR_TASKS_IMMEDIATELY sweet

		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED

		SET_CURRENT_CHAR_WEAPON sweet WEAPONTYPE_UNARMED

	ENDIF

	LOAD_SCENE 2512.7610 -1672.5204 12.5034

	LOAD_SCENE_IN_DIRECTION 2512.7610 -1672.5204 12.5034 240.2609 

	SET_FIXED_CAMERA_POSITION 2509.7981 -1672.1747 13.4192 0.0 0.0 0.0 // Player enters car
	POINT_CAMERA_AT_POINT 2510.7522 -1672.4573 13.5178 JUMP_CUT

	GOSUB mansion3_fade_in
	 
	IF NOT IS_CHAR_DEAD scplayer

		TASK_PLAY_ANIM scplayer PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

		WAIT 500

	ENDIF

	IF NOT IS_CHAR_DEAD sweet

		TASK_PLAY_ANIM sweet PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

		WAIT 500

	ENDIF

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_MAN3_BA // Alright, lets get out of here. Go see Kendl.

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( MAN3_BA ) 4000 1 // Alright, lets get out of here. Go see Kendl.

	TIMERB = 0
	WHILE TIMERB < 4000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO m3_skip_the_cut_10
		ENDIF
	ENDWHILE

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_MAN3_BB // Kendl can come see me, at  her home.

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( MAN3_BB ) 3000 1 // Kendl can come see me, at  her home.

	IF NOT IS_CHAR_DEAD sweet

		CLEAR_CHAR_TASKS_IMMEDIATELY sweet

		TASK_PLAY_ANIM sweet IDLE_chat PED 4.0 FALSE FALSE FALSE FALSE -1

	ENDIF

	TIMERB = 0
	WHILE TIMERB < 3000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO m3_skip_the_cut_10
		ENDIF
	ENDWHILE

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_MAN3_BC // There’s nothing here no more.

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( MAN3_BC ) 3000 1 // There’s nothing here no more.

	IF NOT IS_CHAR_DEAD scplayer

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		TASK_PLAY_ANIM scplayer IDLE_chat PED 4.0 FALSE FALSE FALSE FALSE -1

	ENDIF

	TIMERB = 0
	WHILE TIMERB < 3000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO m3_skip_the_cut_10
		ENDIF
	ENDWHILE

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_MAN3_BD // Rome wasn’t built in a day.

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( MAN3_BD ) 3000 1 // Rome wasn’t built in a day.

	TIMERB = 0
	WHILE TIMERB < 3000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO m3_skip_the_cut_10
		ENDIF
	ENDWHILE

	IF NOT IS_CHAR_DEAD scplayer

		CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

		TASK_GO_TO_COORD_ANY_MEANS scplayer 2507.7935 -1668.9266 12.3879 PEDMOVE_WALK -1

	ENDIF

	CLEAR_MISSION_AUDIO 1

 	LOAD_MISSION_AUDIO 1 SOUND_MAN3_BE // My brother is a pain in the ass

	WHILE NOT HAS_MISSION_AUDIO_LOADED 1	 
		WAIT 0
	ENDWHILE

	PLAY_MISSION_AUDIO 1

	PRINT_NOW ( MAN3_BE ) 2000 1 // My brother is a pain in the ass

	TIMERB = 0
	WHILE TIMERB < 2000
		WAIT 0
		IF IS_BUTTON_PRESSED PAD1 CROSS
			GOTO m3_skip_the_cut_10
		ENDIF
	ENDWHILE

	m3_skip_the_cut_10:

	GOSUB mansion3_restore_camera

	SET_CAMERA_BEHIND_PLAYER

	DELETE_CHAR sweet

GOTO mission_mansion3_passed


// ***********************************************************************************************
// *																							 *
// *							 	      Mission Failed   		 								 *
// *																							 *
// ***********************************************************************************************


mission_mansion3_failed:

	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"

RETURN

// ***********************************************************************************************
// *																							 *
// *							 	      Mission Passed  	 	 								 *
// *																							 *
// ***********************************************************************************************
  
mission_mansion3_passed:

	CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION TRUE

	SET_GANG_WARS_ACTIVE TRUE

	SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 40
	SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 40

	SET_ZONE_GANG_STRENGTH GAN1 GANG_FLAT 0
	SET_ZONE_GANG_STRENGTH GAN2 GANG_FLAT 0

	START_NEW_SCRIPT grove_mission_loop

	REMOVE_BLIP grove_contact_blip

	ADD_SPRITE_BLIP_FOR_CONTACT_POINT introX introY introZ grove_blip_icon grove_contact_blip

	flag_mansion_mission_counter ++

	PLAYER_MADE_PROGRESS 1 

	REGISTER_MISSION_PASSED ( MAN_3 ) //Used in the stats 

	PRINT_WITH_NUMBER_BIG ( M_PASSR ) 40 5000 1 //"Mission Passed!"

	AWARD_PLAYER_MISSION_RESPECT 40 //amount of respect

	CREATE_PROTECTION_PICKUP 2502.10 -1686.38 13.0 10000 territory_cash territory_pickup

	START_NEW_SCRIPT territory_cash_loop

	terminted_territory_pickup = 0

	PLAY_MISSION_PASSED_TUNE 1

	CLEAR_WANTED_LEVEL player1

	SET_INT_STAT PASSED_MANSION3 1

	m3_has_been_passed = 1

RETURN

// ***********************************************************************************************
// *																							 *
// *							 	     Clean-up Mission 	 	 								 *
// *																							 *
// ***********************************************************************************************

mission_cleanup_mansion3:

	IF m3_has_been_passed = 0

		CAN_TRIGGER_GANG_WAR_WHEN_ON_A_MISSION FALSE

		SET_GANG_WARS_ACTIVE FALSE

		SET_ZONE_GANG_STRENGTH GAN1 GANG_GROVE 0
		SET_ZONE_GANG_STRENGTH GAN2 GANG_GROVE 0

		SET_ZONE_GANG_STRENGTH GAN1 GANG_FLAT 40
		SET_ZONE_GANG_STRENGTH GAN2 GANG_FLAT 40

	ENDIF

	CLEAR_ONSCREEN_COUNTER m3_sweets_health

	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_GANG_GROVE PEDTYPE_PLAYER1
	SET_RELATIONSHIP ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_GANG_GROVE PEDTYPE_GANG_FLAT 

	flag_player_on_mission = 0

	GET_GAME_TIMER timer_mobile_start //Used to reset the mobile phone timer so it doesn't ring immediately after the mission

	GET_GAME_TIMER gf_game_timer

	MARK_MODEL_AS_NO_LONGER_NEEDED vincent
	MARK_MODEL_AS_NO_LONGER_NEEDED coach
	MARK_MODEL_AS_NO_LONGER_NEEDED bmydrug
	MARK_MODEL_AS_NO_LONGER_NEEDED FAM2
	MARK_MODEL_AS_NO_LONGER_NEEDED ak47
	MARK_MODEL_AS_NO_LONGER_NEEDED WMYDRUG 

	MARK_STREAMED_SCRIPT_AS_NO_LONGER_NEEDED Dealer.sc

	UNLOAD_SPECIAL_CHARACTER 1

	REMOVE_ANIMATION GANGS
	REMOVE_ANIMATION CRACK

	IF NOT IS_CHAR_DEAD sweet

		REMOVE_CHAR_ELEGANTLY sweet

	ENDIF

	IF DOES_BLIP_EXIST m3_hood_blip

		REMOVE_BLIP m3_hood_blip

	ENDIF

	IF DOES_BLIP_EXIST m3_dealer_blip[0]

		REMOVE_BLIP m3_dealer_blip[0]

	ENDIF

	IF DOES_BLIP_EXIST m3_dealer_blip[1]

		REMOVE_BLIP m3_dealer_blip[1]

	ENDIF

	IF DOES_BLIP_EXIST m3_bus_blip

		REMOVE_BLIP m3_bus_blip

	ENDIF
	IF DOES_BLIP_EXIST m3_grove_blip

		REMOVE_BLIP m3_grove_blip

	ENDIF
	IF DOES_BLIP_EXIST m3_sweet_blip

		REMOVE_BLIP m3_sweet_blip

	ENDIF

	RESTORE_CAMERA_JUMPCUT

	SET_CAMERA_BEHIND_PLAYER	

	MISSION_HAS_FINISHED

RETURN

mansion3_set_camera:

	SWITCH_WIDESCREEN ON
	SET_PLAYER_CONTROL player1 OFF

RETURN

mansion3_restore_camera:

	CLEAR_PRINTS

	SET_CAMERA_BEHIND_PLAYER
	SWITCH_WIDESCREEN OFF
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT
 
RETURN

mansion3_fade_out:

	DO_FADE 500 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

mansion3_fade_in:
	DO_FADE 500 FADE_IN

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

RETURN

// ***********************************************************************************************
// *																							 *
// *							 	          Keyboard     	 	 								 *
// *																							 *
// ***********************************************************************************************

man3_keys:

LVAR_INT m3_dummy_car

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_W

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES scplayer 1522.0896 -1662.0824 12.5469 
			
			SET_CHAR_HEADING scplayer 175.5419 

			SET_CAMERA_BEHIND_PLAYER

		ENDIF
 
 	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_D

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_F

		IF NOT IS_CHAR_DEAD scplayer

			SET_CHAR_COORDINATES scplayer 2503.6875 -1671.4528 12.3707 
			
			SET_CHAR_HEADING scplayer 255.1807 

			SET_CAMERA_BEHIND_PLAYER

		ENDIF

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_K

		GOSUB mansion3_set_camera

		IF NOT IS_CHAR_DEAD scplayer

			CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

		ENDIF

		IF NOT IS_CHAR_DEAD sweet

			CLEAR_CHAR_TASKS_IMMEDIATELY sweet

			TASK_PLAY_ANIM_NON_INTERRUPTABLE sweet PRTIAL_HNDSHK_01 GANGS 4.0 FALSE FALSE FALSE FALSE -1

		ENDIF

		PRINT_NOW ( MAN3_33 ) 4000 1 // The Hood is yours!
		
		WAIT 4000

		GOSUB mansion3_restore_camera

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_H

		GOTO m3_end_cutscene

	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_Z
	
		IF NOT IS_CAR_DEAD m3_cut_car
			
			CREATE_CHAR_AS_PASSENGER m3_cut_car PEDTYPE_CIVMALE FAM2 0 m3_cracker[0]
						
			CREATE_CHAR_AS_PASSENGER m3_cut_car PEDTYPE_CIVMALE FAM2 1 m3_cracker[1]
			
			CREATE_CHAR_AS_PASSENGER m3_cut_car PEDTYPE_CIVMALE FAM2 2 m3_cracker[2]

		ENDIF	
				
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_M

	ENDIF

RETURN

m3_load_sample:

	CLEAR_MISSION_AUDIO 1

	LOAD_MISSION_AUDIO 1 m3_audio

	m3_playing = 0

RETURN

m3_play_sample:

	IF HAS_MISSION_AUDIO_LOADED 1
	AND m3_playing = 0

		PLAY_MISSION_AUDIO 1

		PRINT_NOW ( $m3_print ) 10000 1  

		m3_playing = 1

	ENDIF

	IF HAS_MISSION_AUDIO_FINISHED 1
	AND m3_playing = 1

		CLEAR_MISSION_AUDIO 1	

		CLEAR_THIS_PRINT $m3_print

		m3_playing = 2

	ENDIF
	
RETURN

}

/*


{---------------------------------------MANSION3--------------------------------------------}

[MAN3_01:MAN_3]
~s~Pick up Sweet from the ~y~Precinct~s~.

[MAN3_02:MAN_3]
~r~You missed the bus.

[MAN3_03:MAN_3]
~s~You lost ~b~Sweet~s~, go back and get him.

[MAN3_05:MAN_3]
~s~Drive Sweet to ~y~Grove Street~s~.

[MAN3_08:MAN_3]
You need a car to pick up sweet.

[MAN3_11:MAN_3]
~r~You let Sweet get killed.

[MAN3_18:MAN_3]
Sweet's out of jail, he's going to need a ride.

[MAN3_19:MAN_3]
~s~Get a bigger vehicle!

[MAN3_20:MAN_3]
Kill any crack dealers in your hood to keep your gang healthy.

[MAN3_21:MAN_3]
If a gang member is fucked on crack you won't be able to use him.

[MAN3_22:MAN_3]
~s~Clean the hood of any ~r~Dealers~s~!

[MAN3_27:MAN_3]
Time Left :



 

*/ 