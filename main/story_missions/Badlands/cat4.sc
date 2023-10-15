MISSION_START

// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// *************************************** CAT 4 *******************************************
// ********************************* Mission Description ***********************************
// *****************************************************************************************
// ****************************************************************************************
// *****************************************************************************************

SCRIPT_NAME CAT4

// Mission start stuff

GOSUB mission_start_cat4

IF HAS_DEATHARREST_BEEN_EXECUTED
	GOSUB mission_failed_cat4
ENDIF

GOSUB mission_cleanup_cat4

MISSION_END

{ 
// Variables for mission

// Cars
LVAR_INT car_police1_cat4 car_police2_cat4 car_police3_cat4


LVAR_INT getaway_car_car4

LVAR_INT player_in_otb_flag_cat4_local_var

LVAR_INT object_door_cat4

LVAR_INT small_safe_cat4

LVAR_INT players_car_cat4
LVAR_INT players_car_model_cat4

 

// Chars

LVAR_INT char_pol1_cat4 char_pol2_cat4 char_pol3_cat4
LVAR_INT char_random_ped_cat4[6]

LVAR_INT index_random_char

LVAR_INT index_cat4


// DECISION MAKERS

LVAR_INT dm_cops_cat4
LVAR_INT dm_catalina_cat4
//LVAR_INT dm_owner_of_cat_cat4
// seq
LVAR_INT seq_cat_stealing_car



// objects
LVAR_INT object_cutscene_satchel_cat4

LVAR_INT players_ammo_cat4 players_ammo_2_cat4 players_ammo_3_cat4


//Coords
LVAR_FLOAT coord_otb_x_cat4 coord_otb_y_cat4 coord_otb_z_cat4

LVAR_FLOAT coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4
LVAR_FLOAT coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z

LVAR_FLOAT coord_random_char_x[6] coord_random_char_y[6] coord_random_char_z[6]



// flags

LVAR_INT flag_holdup_cat4

VAR_INT flag_has_safe_been_robbed_cat4

LVAR_INT flag_getaway_cat4
LVAR_INT flag_mission_passed_cat4 
LVAR_INT flag_mission_failed_cat4 

LVAR_INT flag_police_arrive 

LVAR_INT flag_are_cops_pissed	
 
LVAR_INT flag_text_otb flag_text_cat_police_killing_cat4
LVAR_INT flag_robbery_cutscene_otb
LVAR_INT flag_cutscene1_cat4
LVAR_INT flag_create_car_cat_enters
LVAR_INT flag_cat_shoot_cops

LVAR_INT flag_help_text_spary_cat4
LVAR_INT flag_has_catalina_chocolate_rant_been_done

LVAR_INT cat_coment_counter 

LVAR_INT flag_has_otb_door_been_open
LVAR_INT flag_display_help_cat4 

LVAR_INT flag_set_cat_as_group_member

LVAR_INT flag_dont_make_cat_leave_car

LVAR_INT play_audio_flag_cat4
LVAR_INT help_text_cat4
LVAR_INT set_audio_to_be_played

LVAR_INT number_of_conversation_samples_cat4

LVAR_INT max_number_in_players_group






// blips
LVAR_INT blip_otb_cat4
LVAR_INT blip_cat_cat4
LVAR_INT blip_getaway_car
LVAR_INT blip_hiding_spot
LVAR_INT blip_paint_and_spray_cat4



// VARIABLES FOR HOLDUP
LVAR_INT flag_has_cat_got_the_loot_cat4

VAR_INT game_timer1_cat4 game_timer2_cat4


// text and audio

LVAR_TEXT_LABEL $cat4_text[52]

LVAR_INT cat4_audio[52]
LVAR_INT index_dialogue_cat4

LVAR_INT flag_safe_explosion_delay

LVAR_INT dm_players_group_cat4








// FX SYSTEM





// **************************************** Mission Start **********************************


mission_start_cat4:





//REGISTER_MISSION_GIVEN

flag_player_on_mission = 1



SWITCH_ENTRY_EXIT genOTB FALSE

IF IS_PLAYER_PLAYING Player1
	SET_PLAYER_CONTROL player1 OFF
ENDIF

// ****************************************START OF CUTSCENE********************************
//SET_PLAYER_CONTROL Player1 OFF



// ****************************************END OF CUTSCENE**********************************

// fades the screen in 

SET_FADING_COLOUR 0 0 0

DO_FADE 1000 FADE_OUT
SWITCH_WIDESCREEN ON

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

IF IS_PLAYER_PLAYING player1
	SET_CHAR_HEADING scplayer 64.0
	SET_CAMERA_BEHIND_PLAYER
ENDIF



FIND_MAX_NUMBER_OF_GROUP_MEMBERS max_number_in_players_group
max_number_in_players_group -= 1
IF max_number_in_players_group <= 0
	SET_SCRIPT_LIMIT_TO_GANG_SIZE 1
ELSE
	SET_SCRIPT_LIMIT_TO_GANG_SIZE max_number_in_players_group
//GET_INT_STAT CYCLE_SKILL stat_read_skill_temp
ENDIF
MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1




// request models






REQUEST_MODEL DESERT_EAGLE
REQUEST_MODEL BOMB
REQUEST_MODEL SATCHEL 

REQUEST_MODEL rider1_door

REQUEST_MODEL kev_safe
REQUEST_MODEL man_safenew

REQUEST_ANIMATION misc


WHILE NOT HAS_MODEL_LOADED SATCHEL
	OR NOT HAS_MODEL_LOADED BOMB
	WAIT 0
ENDWHILE






			 





//LOAD_MISSION_TEXT CAT2
LOAD_MISSION_TEXT CAT

//RESTORE_CAMERA_JUMPCUT






//WAIT 1500



// **************************************** Initialising variables  **********************************

// Main Loop Flags

play_audio_flag_cat4 = 1

flag_holdup_cat4 = 1
flag_getaway_cat4 = 0
flag_mission_passed_cat4 = 0
flag_mission_failed_cat4 = 0
flag_robbery_cutscene_otb = 0
flag_has_safe_been_robbed_cat4 = 0			// resets otb safe
flag_cat_shoot_cops = 0
flag_otb_robbing_peds_panic = 0
flag_police_arrive = 0
flag_create_car_cat_enters = 0
flag_help_text_spary_cat4 = 0

flag_safe_explosion_delay = 0

flag_display_help_cat4 = 0

set_audio_to_be_played = 0

play_audio_flag_cat4 = 0

cat_coment_counter = 0

help_text_cat4 = 0

flag_dont_make_cat_leave_car = 1

flag_text_otb = 0
flag_text_cat_police_killing_cat4 = 0

flag_has_otb_door_been_open = 0

//player_in_otb_flag = 0
player_in_otb_flag_cat4_local_var = 0
flag_cutscene1_cat4 = 0

flag_are_cops_pissed  = 0	

flag_has_catalina_chocolate_rant_been_done = 0


flag_has_cat_got_the_loot_cat4 = 0
counter_robbery_cat4 = 0

index_dialogue_cat4 = 0

flag_set_cat_as_group_member = 0





coord_otb_x_cat4 = 1289.8507 
coord_otb_y_cat4 = 269.8778 
coord_otb_z_cat4 = 19.5888

coord_otb_clerk_x_cat4 = 820.2197  
coord_otb_clerk_y_cat4 = 4.2019 
coord_otb_clerk_z_cat4 = 1004.6614


coord_spray_shop_x = 719.17 
coord_spray_shop_y = -457.68
coord_spray_shop_z = 16.92



coord_random_char_x[0] = 822.4665  
coord_random_char_y[0] = 1.7837 
coord_random_char_z[0] = 1003.1

coord_random_char_x[1] = 822.0091 
coord_random_char_y[1] = 6.0787 
coord_random_char_z[1] = 1003.17

coord_random_char_x[2] = 832.8624
coord_random_char_y[2] = 0.7513 
coord_random_char_z[2] = 1003.18

coord_random_char_x[3] = 830.9443 
coord_random_char_y[3] = 0.3996 
coord_random_char_z[3] = 1003.17

coord_random_char_x[4] = 827.8390 
coord_random_char_y[4] = -0.2443 
coord_random_char_z[4] = 1003.1

coord_random_char_x[5] = 829.2125 
coord_random_char_y[5] = 2.0354 
coord_random_char_z[5] = 1003.17



// AUDIO AND TEXT

	// conversation in the car after cat and carl lose the police

  /*	
  	$cat4_text[0] = &CAT2_HA  //CATALINA	Carl, you have to be fast and totally ruthless!
	$cat4_text[1] = &CAT2_HB  //CATALINA	No fucking about like in the betting shop!
	$cat4_text[2] = &CAT2_HC	//carl What? If you hadn’t started blasting, 
	$cat4_text[3] = &CAT2_HE  //CATALINA	They had to die because YOU were slow and stupid, 
	$cat4_text[4] = &CAT2_HF  //CATALINA	like a big fat brat that eats chocolates while his 
	$cat4_text[5] = &CAT2_HG  //CATALINA	father gives nothing to his step daughter but stale bread!
	$cat4_text[6] = &CAT2_HH  //carl	What? Where’d that come from?
	$cat4_text[7] = &CAT2_HI  //CATALINA	Enough! I am not speaking to you any more!


	cat4_audio[0] = _CAT2_HA  //CATALINA	Carl, you have to be fast and totally ruthless!
	cat4_audio[1] = SOUND_CAT2_HB  //CATALINA	No fucking about like in the betting shop!
	cat4_audio[2] = SOUND_CAT2_HC	//carl What? If you hadn’t started blasting,  things would have been sweet!
	cat4_audio[3] = SOUND_CAT2_HE  //CATALINA	They had to die because YOU were slow and stupid, 
	cat4_audio[4] = SOUND_CAT2_HF  //CATALINA	like a big fat brat that eats chocolates while his 
	cat4_audio[5] = SOUND_CAT2_HG  //CATALINA	father gives nothing to his step daughter but stale bread!
	cat4_audio[6] = SOUND_CAT2_HH  //carl	What? Where’d that come from?
	cat4_audio[7] = SOUND_CAT2_HI  //CATALINA	Enough! I am not speaking to you any more!
 */
 
 
 
IF cat_getaway_dialogue = 0
	IF cat_counter = 0
		$cat4_text[0] = &CAT_DA				// I have a hideout on Fern Ridge.
		$cat4_text[1] = &CAT_DE				//Now drive!
   		$cat4_text[2] = &CAT_DF				//You’re fucking psycho!
		$cat4_text[3] = &CAT_DG				//All you little men are scared of strong women!
		$cat4_text[4] = &CAT_DH				//If we’re passionate you say we crazy.
		$cat4_text[5] = &CAT_DI				//If we’re upset you say we hysterical.
		$cat4_text[6] = &CAT_DJ				//We sleep with men, we’re sluts.
		$cat4_text[7] = &CAT_DK				//If we don’t put out we’re frigid bitches.
		$cat4_text[8] = &CAT_DL				// Who you calling little men? You went berserk back there!
		$cat4_text[9] = &CAT_DM				// That? That was just another day at the office!
		$cat4_text[10] = &CAT_DN				// You can’t stand the heat, go put your tiny balls in the freezer!
		$cat4_text[11] = &CAT_DO				// Tiny balls? Now just wait a minute-
		$cat4_text[12] = &CAT_DP				//Enough! Just shut up and drive, I’m counting the fucking money!
			   

		cat4_audio[0] = SOUND_CAT_DA				// I have a hideout on Fern Ridge.
		cat4_audio[1] = SOUND_CAT_DE				//Now drive!
 		cat4_audio[2] = SOUND_CAT_DF				//You’re fucking psycho!
		cat4_audio[3] = SOUND_CAT_DG				//All you little men are scared of strong women!
		cat4_audio[4] = SOUND_CAT_DH				//If we’re passionate you say we crazy.
		cat4_audio[5] = SOUND_CAT_DI				//If we’re upset you say we hysterical.
		cat4_audio[6]  = SOUND_CAT_DJ				//We sleep with men, we’re sluts.
		cat4_audio[7]  = SOUND_CAT_DK				//If we don’t put out we’re frigid bitches.
		cat4_audio[8]  = SOUND_CAT_DL				// Who you calling little men? You went berserk back there!
		cat4_audio[9] = SOUND_CAT_DM				// That? That was just another day at the office!
		cat4_audio[10] = SOUND_CAT_DN				// You can’t stand the heat, go put your tiny balls in the freezer!
		cat4_audio[11] = SOUND_CAT_DO				// Tiny balls? Now just wait a minute-
		cat4_audio[12] = SOUND_CAT_DP				//Enough! Just shut up and drive, I’m counting the fucking money!
						 
		number_of_conversation_samples_cat4 = 16	// Have to add 4 samples that play prior 
	ELSE

   		$cat4_text[0] = &CAT_DF				//You’re fucking psycho!
		$cat4_text[1] = &CAT_DG				//All you little men are scared of strong women!
		$cat4_text[2] = &CAT_DH				//If we’re passionate you say we crazy.
		$cat4_text[3] = &CAT_DI				//If we’re upset you say we hysterical.
		$cat4_text[4] = &CAT_DJ				//We sleep with men, we’re sluts.
		$cat4_text[5] = &CAT_DK				//If we don’t put out we’re frigid bitches.
		$cat4_text[6] = &CAT_DL				// Who you calling little men? You went berserk back there!
		$cat4_text[7] = &CAT_DM				// That? That was just another day at the office!
		$cat4_text[8]  = &CAT_DN				// You can’t stand the heat, go put your tiny balls in the freezer!
		$cat4_text[9]  = &CAT_DO				// Tiny balls? Now just wait a minute-
		$cat4_text[10] = &CAT_DP				//Enough! Just shut up and drive, I’m counting the fucking money!
			   

 		cat4_audio[0] = SOUND_CAT_DF				//You’re fucking psycho!
		cat4_audio[1] = SOUND_CAT_DG				//All you little men are scared of strong women!
		cat4_audio[2] = SOUND_CAT_DH				//If we’re passionate you say we crazy.
		cat4_audio[3] = SOUND_CAT_DI				//If we’re upset you say we hysterical.
		cat4_audio[4]  = SOUND_CAT_DJ				//We sleep with men, we’re sluts.
		cat4_audio[5]  = SOUND_CAT_DK				//If we don’t put out we’re frigid bitches.
		cat4_audio[6]  = SOUND_CAT_DL				// Who you calling little men? You went berserk back there!
		cat4_audio[7] = SOUND_CAT_DM				// That? That was just another day at the office!
		cat4_audio[8] = SOUND_CAT_DN				// You can’t stand the heat, go put your tiny balls in the freezer!
		cat4_audio[9] = SOUND_CAT_DO				// Tiny balls? Now just wait a minute-
		cat4_audio[10] = SOUND_CAT_DP				//Enough! Just shut up and drive, I’m counting the fucking money!
						 
		number_of_conversation_samples_cat4 = 14	// Have to add 4 samples that play prior 
	ENDIF
ENDIF 

 

IF cat_getaway_dialogue = 1

	$cat4_text[0] = &CAT_GA					// Take me home, Carl.
	$cat4_text[1] = &CAT_GB					// Okay. Look, but we gotta talk about something.
	$cat4_text[2] = &CAT_GC					//What? What do I have to say to you?
	$cat4_text[3] = &CAT_GD					//You’re a great girl, and all, but you gotta calm down.
	$cat4_text[4] = &CAT_GE					// I know some cold blooded cats wouldn’t act like you.
	$cat4_text[5] = &CAT_GF					//Oh, you get given a lioness and you want a pussy cat? Wimp!
	$cat4_text[6] = &CAT_GG					//No, I just want…
	$cat4_text[7] = &CAT_GH					//You know why I act like this.
	$cat4_text[8] = &CAT_GI					//I ain't got a fuckin' clue.
	$cat4_text[9] = &CAT_GJ					//I’m in love, Carl. 
	$cat4_text[10] = &CAT_GK					// A woman’s heart is a tempestuous place, 
	$cat4_text[11] = &CAT_GL					//and you will break my heart. 
	$cat4_text[12] = &CAT_GM					// Sometimes I want to kill us both!
	$cat4_text[13] = &CAT_GN					//Please, don’t do that. Just relax a little.

	cat4_audio[0] = SOUND_CAT_GA			// Take me home, Carl.		
	cat4_audio[1] = SOUND_CAT_GB			// Okay. Look, but we gotta talk about something.
	cat4_audio[2] = SOUND_CAT_GC			//What? What do I have to say to you?
	cat4_audio[3] = SOUND_CAT_GD			//You’re a great girl, and all, but you gotta calm down.
	cat4_audio[4] = SOUND_CAT_GE			// I know some cold blooded cats wouldn’t act like you.
	cat4_audio[5] = SOUND_CAT_GF			//Oh, you get given a lioness and you want a pussy cat? Wimp!
	cat4_audio[6] = SOUND_CAT_GG			//No, I just want…
	cat4_audio[7] = SOUND_CAT_GH			//You know why I act like this.
	cat4_audio[8]  = SOUND_CAT_GI				//I ain't got a fuckin' clue.
	cat4_audio[9]  = SOUND_CAT_GJ				//I’m in love, Carl. 
	cat4_audio[10] = SOUND_CAT_GK				// A woman’s heart is a tempestuous place, 
	cat4_audio[11] = SOUND_CAT_GL				//and you will break my heart. 
	cat4_audio[12] = SOUND_CAT_GM				// Sometimes I want to kill us both!
	cat4_audio[13] = SOUND_CAT_GN				//Please, don’t do that. Just relax a little.

	number_of_conversation_samples_cat4 = 17	// Have to add 4 samples that play prior

ENDIF 

 
 

IF cat_getaway_dialogue = 2

	$cat4_text[0] = &CAT_OA			///Take me home now, big man.
	$cat4_text[1] = &CAT_OB			//How we do?
	$cat4_text[2] = &CAT_OC			//Is that all you care about? Money?
	$cat4_text[3] = &CAT_OD			//No, but I really need the paper--.
	$cat4_text[4] = &CAT_OE			//You revolt me. You make my skin crawl.
	$cat4_text[5] = &CAT_OF			//Well I ain’t crazy about you, either!
	$cat4_text[6] = &CAT_OG			// That’s just it. How little you know. 
	$cat4_text[7] = &CAT_OH			//Don’t talk. Let us enjoy the peace and quiet.

	cat4_audio[0] = SOUND_CAT_OA			///Take me home now, big man.
	cat4_audio[1] = SOUND_CAT_OB			//How we do?
	cat4_audio[2] = SOUND_CAT_OC			//Is that all you care about? Money?
	cat4_audio[3] = SOUND_CAT_OD			//No, but I really need the paper--.
	cat4_audio[4] = SOUND_CAT_OE			//You revolt me. You make my skin crawl.
	cat4_audio[5] = SOUND_CAT_OF			//Well I ain’t crazy about you, either!
	cat4_audio[6] = SOUND_CAT_OG			// That’s just it. How little you know. 
	cat4_audio[7] = SOUND_CAT_OH			//Don’t talk. Let us enjoy the peace and quiet.

	number_of_conversation_samples_cat4 = 11	// Have to add 4 samples that play prior



ENDIF
 
 














index_random_char = 0



ADD_BLIP_FOR_COORD coord_otb_x_cat4 coord_otb_y_cat4 coord_otb_z_cat4 blip_otb_cat4

ADD_BLIP_FOR_COORD coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 blip_hiding_spot 
ADD_BLIP_FOR_COORD coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 blip_paint_and_spray_cat4

IF NOT IS_CHAR_DEAD catalina
	
	ADD_BLIP_FOR_CHAR catalina blip_cat_cat4
	REMOVE_BLIP blip_cat_cat4
ENDIF



REMOVE_BLIP blip_otb_cat4 
REMOVE_BLIP blip_hiding_spot
REMOVE_BLIP blip_paint_and_spray_cat4

REMOVE_BLIP blip_cat_cat4
REMOVE_BLIP spray_shop4





LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_TOUGH dm_cops_cat4

ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 833.0 9.0 10.0 FALSE   //  inside otb
ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 1291.0 271.0 10.0 TRUE	   // outside


// /////////////////////////////////////     Main Loop          ///////////////////////////////////////

main_loop_cat4:
	WAIT 0

	IF flag_holdup_cat4 = 1
		GOSUB holdup_cat4
	ENDIF


	IF flag_getaway_cat4 = 1
		GOSUB getaway_cat4
	ENDIF


	IF flag_mission_passed_cat4 = 1
		GOTO mission_passed_cat4
	ENDIF
	
	IF flag_mission_failed_cat4 = 1
		GOTO mission_failed_cat4
	ENDIF
		
RETURN		

 





// **************************************** MAIN FUNCTIONS  **********************************	  
////////////////////////////////////////////////////////////////////////////////////////////////
streaming_otb_clerk:  
		//   VIEW_INTEGER_VARIABLE flag_dont_make_cat_leave_car flag_dont_make_cat_leave_car



		// check for a wanted level
		IF flag_getaway_cat4 = 1

			IF flag_are_cops_pissed = 0
				IF NOT IS_CHAR_DEAD catalina
					//IF IS_GROUP_MEMBER catalina Players_Group
						IF IS_WANTED_LEVEL_GREATER player1 0
							flag_are_cops_pissed = 1

							IF flag_has_catalina_chocolate_rant_been_done = 1
								flag_text_otb = 1000
	 						ENDIF


							IF NOT DOES_BLIP_EXIST  blip_paint_and_spray_cat4
								ADD_SPRITE_BLIP_FOR_COORD coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z RADAR_SPRITE_SPRAY blip_paint_and_spray_cat4  
							ENDIF

							DISABLE_ALL_ENTRY_EXITS TRUE

							IF flag_dont_make_cat_leave_car = 0
								IF flag_display_text_cat4 = 1
									IF cat_coment_counter > 2
										cat_coment_counter = 0
									ENDIF 

									IF cat_coment_counter = 2
 			  							LOAD_MISSION_AUDIO 1 SOUND_CATX_UA
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

										PRINT_NOW ( CATX_UA ) 10000 1		//	Carl, you are a fucking idiota!	
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
										CLEAR_THIS_PRINT CATX_UA 

										cat_coment_counter++
									ENDIF



									IF cat_coment_counter = 1

 			  							LOAD_MISSION_AUDIO 1 SOUND_CATX_UB
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

										PRINT_NOW ( CATX_UB ) 10000 1		//Idiota, Carl, idiota!
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
										CLEAR_THIS_PRINT CATX_UB 


										cat_coment_counter++
									ENDIF



									IF cat_coment_counter = 0
										LOAD_MISSION_AUDIO 1 SOUND_CATX_UF

										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

										PRINT_NOW ( CATX_UF ) 10000 1		// Carl Johnson, you are shit!	
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
										CLEAR_THIS_PRINT CATX_UF 
										cat_coment_counter++
									ENDIF
								ENDIF


								

								IF flag_dont_make_cat_leave_car = 0
									IF flag_cat_shoot_cops = 0
										IF NOT IS_CHAR_DEAD catalina
											IF NOT LOCATE_CHAR_ANY_MEANS_3D catalina coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z 30.0 30.0 30.0 FALSE
											   //	CLEAR_CHAR_TASKS catalina
												CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP	
												flag_cat_shoot_cops = 1
												SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP

				   						 /*		IF NOT IS_GROUP_MEMBER catalina Players_Group
													SET_GROUP_MEMBER Players_Group catalina 
												ENDIF	 */

											ENDIF
										ENDIF
									ENDIF
								ENDIF
								PRINT CAT4_32 5000 1   // GET TO THE PAINT AND SPRAY

					  		ENDIF				
							 								
							
								
							

							IF DOES_BLIP_EXIST blip_hiding_spot
								REMOVE_BLIP blip_hiding_spot
							ENDIF 

						ENDIF
				   //	ENDIF
				ENDIF

			ELSE

				// corona outside pay and spray
				IF NOT IS_CHAR_DEAD catalina

					IF flag_help_text_spary_cat4 = 0
						IF LOCATE_CHAR_ANY_MEANS_3D catalina 720.5 -466.73 15.72 4.0 4.0 4.0 TRUE
							IF flag_help_text_spary_cat4 = 0


									
								PRINT_NOW CAT4_37 5000 1	//GOD: PARK CAR INT PAY N SPRAY



								IF flag_cat_shoot_cops = 1
									IF NOT IS_CHAR_DEAD catalina
	  									CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP	
										IF IS_GROUP_MEMBER catalina Players_Group
											REMOVE_CHAR_FROM_GROUP catalina
										ENDIF

										CLEAR_CHAR_TASKS catalina
										
			   							IF NOT IS_GROUP_MEMBER catalina Players_Group
											SET_GROUP_MEMBER Players_Group catalina 
											SET_GROUP_FOLLOW_STATUS Players_Group TRUE
										ENDIF 
										SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP

	   

										flag_cat_shoot_cops = 0
									ENDIF
								ENDIF

								flag_help_text_spary_cat4 = 1
							ENDIF
						ENDIF
					ENDIF

					IF flag_help_text_spary_cat4 = 1 
						IF NOT LOCATE_CHAR_ANY_MEANS_3D catalina 720.5 -460.73 15.72 15.0 15.0 15.0 FALSE

							IF flag_help_text_spary_cat4 = 1
								flag_help_text_spary_cat4 = 0
							ENDIF

						ENDIF
					ENDIF

					IF flag_dont_make_cat_leave_car = 0
						IF flag_cat_shoot_cops = 0
							IF IS_WANTED_LEVEL_GREATER player1 0
								IF NOT LOCATE_CHAR_ANY_MEANS_3D catalina coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z 30.0 30.0 30.0 FALSE
									CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP	

									SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP

				   						   /*		IF NOT IS_GROUP_MEMBER catalina Players_Group
													SET_GROUP_MEMBER Players_Group catalina 
												ENDIF
											 */
									flag_cat_shoot_cops = 1
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF
				////////////////////

				IF NOT IS_WANTED_LEVEL_GREATER player1 0
					flag_are_cops_pissed = 0
					GET_GAME_TIMER game_timer2_cat4
 
					
					IF NOT DOES_BLIP_EXIST blip_hiding_spot
						ADD_BLIP_FOR_COORD coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 blip_hiding_spot
						CHANGE_BLIP_COLOUR blip_hiding_spot YELLOW

						DISABLE_ALL_ENTRY_EXITS FALSE
						LOAD_MISSION_AUDIO 1 SOUND_CATX_DE
						WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							WAIT 0
						ENDWHILE  

						PRINT_NOW ( CATX_DE ) 10000 1		//	[]	Get us back to the hideout!
	
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT CATX_DE 

					ENDIF

					IF DOES_BLIP_EXIST blip_paint_and_spray_cat4
						REMOVE_BLIP blip_paint_and_spray_cat4
					ENDIF 
					
					IF flag_cat_shoot_cops = 1
						IF NOT IS_CHAR_DEAD catalina

									CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP	
									IF IS_GROUP_MEMBER catalina Players_Group
										REMOVE_CHAR_FROM_GROUP catalina
									ENDIF

									CLEAR_CHAR_TASKS catalina
									
		   							IF NOT IS_GROUP_MEMBER catalina Players_Group
										SET_GROUP_MEMBER Players_Group catalina 
										SET_GROUP_FOLLOW_STATUS Players_Group TRUE
									ENDIF 
									SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP


				   							/*	IF NOT IS_GROUP_MEMBER catalina Players_Group
													SET_GROUP_MEMBER Players_Group catalina 
												ENDIF	*/

							flag_cat_shoot_cops = 0
						ENDIF
					ENDIF
					TIMERA = 0

				ENDIF
			ENDIF

		ENDIF

	



	// check if player is inside OTB
	IF IS_PLAYER_PLAYING player1
		IF LOCATE_CHAR_ANY_MEANS_3D scplayer coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 50.0 50.0 50.0 FALSE
			IF player_in_otb_flag_cat4_local_var = 0
				CLEAR_THIS_PRINT CAT4_01 	
				
				
					//   CREATE_OBJECT rider1_door 824.4123 10.80 1003.2004 object_door_cat4

				IF flag_has_otb_door_been_open = 0
			   		CREATE_OBJECT rider1_door 824.4123 10.85 1003.2004 object_door_cat4
					SET_OBJECT_HEADING object_door_cat4 175.0
					FREEZE_OBJECT_POSITION object_door_cat4 TRUE
					SET_OBJECT_COLLISION_DAMAGE_EFFECT object_door_cat4 FALSE

				ENDIF

				IF flag_has_otb_door_been_open = 1
				  //	SET_OBJECT_HEADING object_door_cat4 270.0
				ENDIF

			   	IF flag_has_safe_been_robbed_cat4 = 0
			  		CREATE_OBJECT kev_safe 820.5 9.7 1003.2164 small_safe_cat4  // closed safe
			  	ELSE
			  		CREATE_OBJECT man_safenew 820.5 9.7 1003.2164 small_safe_cat4	   // opened safe
			  	  //	MARK_MODEL_AS_NO_LONGER_NEEDED man_safenew
			  	ENDIF	  

				SET_OBJECT_HEADING small_safe_cat4 90.0
 






				IF flag_getaway_cat4 = 1
					IF DOES_BLIP_EXIST blip_hiding_spot
						REMOVE_BLIP blip_hiding_spot  
					ENDIF
				ENDIF

			  //	IF player_in_otb_flag_cat4_local_var = 1
				IF NOT flag_robbery_cutscene_otb = 0

					IF flag_holdup_cat4 = 1
						IF DOES_BLIP_EXIST blip_otb_cat4
							REMOVE_BLIP blip_otb_cat4  
							CLEAR_PRINTS
						ENDIF

						IF NOT IS_CHAR_DEAD catalina
						  //	CLEAR_AREA 823.13 2.2 1003.1797 1.0 TRUE
							CLEAR_CHAR_TASKS catalina

							SET_CHAR_COORDINATES catalina 823.13 2.2 1003.1797
							SET_CHAR_HEADING catalina 107.0
							SET_CHAR_AREA_VISIBLE catalina 3
						 //	SET_CHAR_HAS_USED_ENTRY_EXIT catalina 1291.0 271.0 30.0
							TASK_AIM_GUN_AT_COORD catalina coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 900000
							

							FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION catalina FALSE
						


						  /*	LOAD_MISSION_AUDIO 3 SOUND_CAT2_SECURITY_ALARM
							WHILE NOT HAS_MISSION_AUDIO_LOADED 3
								WAIT 0
							ENDWHILE  
							PLAY_MISSION_AUDIO 3  	*/
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 SOUND_CAT2_SECURITY_ALARM

							  

							LOAD_MISSION_AUDIO 1 SOUND_CATX_JE
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								WAIT 0
							ENDWHILE  

							PRINT_NOW ( CATX_JE ) 10000 1		//	Dont try and dump me CARL!	
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT CATX_JE 				   

						ENDIF
					ENDIF
				ENDIF
			ENDIF
			
			DISPLAY_RADAR OFF
			player_in_otb_flag_cat4_local_var = 1

		ELSE

			IF player_in_otb_flag_cat4_local_var = 1

				IF DOES_OBJECT_EXIST object_door_cat4
					DELETE_OBJECT object_door_cat4
				ENDIF

				IF DOES_OBJECT_EXIST small_safe_cat4
					DELETE_OBJECT small_safe_cat4
				ENDIF


				CLEAR_AREA coord_otb_x_cat4 coord_otb_y_cat4 coord_otb_z_cat4 1000.0 TRUE
		 		IF flag_holdup_cat4 = 1
					IF NOT DOES_BLIP_EXIST blip_otb_cat4
						ADD_BLIP_FOR_COORD coord_otb_x_cat4 coord_otb_y_cat4 coord_otb_z_cat4 blip_otb_cat4	
						SET_COORD_BLIP_APPEARANCE blip_otb_cat4 COORD_BLIP_APPEARANCE_FRIEND

					//  	CLEAR_MISSION_AUDIO 3
						PRINT_NOW CAT4_16 10000 1	  // get back to cat
						CLEAR_HELP

						IF NOT IS_CHAR_DEAD catalina
							FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION catalina TRUE
						ENDIF
				  //		iSetOTBPanic = 0
					   //	CHANGE_BLIP_COLOUR blip_otb_cat4 BLUE 
					ENDIF
				ENDIF	
				DISPLAY_RADAR ON
				player_in_otb_flag_cat4_local_var = 0
			ENDIF



		ENDIF
	ENDIF
	



RETURN

////////////////////////////////////////////////////////////////////////////////////////////





// ***********************************************************************
// ////////////////////////// HOLDUP  ////////////////////////////////////
// ***********************************************************************


//LVAR_INT counter_blaze_cat4

holdup_cat4:



// WAITING FOR PLAYER TO GET INSIDE THE OTB
 
 
	IF IS_CHAR_IN_ANY_CAR scplayer 
		STORE_CAR_CHAR_IS_IN scplayer players_car_cat4
		WARP_CHAR_FROM_CAR_TO_COORD scplayer 1291.5752 269.5648 18.5469
	ELSE
	 	SET_CHAR_COORDINATES scplayer 1291.5752 269.5648 18.5469
	ENDIF



	SET_CHAR_HEADING scplayer 60.0


	CREATE_OBJECT SATCHEL 1288.1412 267.9079 18.5469 object_cutscene_satchel_cat4 
	
	
	 
 
  
	IF NOT IS_CHAR_DEAD catalina

		DELETE_CHAR catalina 
	ENDIF
		

	   /*	IF IS_CHAR_IN_ANY_CAR catalina 
			WARP_CHAR_FROM_CAR_TO_COORD catalina 1288.2772 268.9509 18.5469
			
		ELSE
		   SET_CHAR_COORDINATES catalina 1288.2772 268.9509 18.5469


		ENDIF	*/

	LOAD_SPECIAL_CHARACTER 5 cat 
	WHILE NOT HAS_SPECIAL_CHARACTER_LOADED 5
		WAIT 0
	ENDWHILE
	

	CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 1288.2772 268.9509 18.9469 catalina
	SET_CHAR_GET_OUT_UPSIDE_DOWN_CAR catalina FALSE
	SET_CHAR_HEADING catalina 321.0
	SET_CHAR_ONLY_DAMAGED_BY_PLAYER catalina TRUE
	//	SET_CHAR_DROWNS_IN_WATER catalina TRUE
	SET_CHAR_NEVER_TARGETTED catalina TRUE
	SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH catalina TRUE


	

	IF IS_GROUP_MEMBER catalina Players_Group
		REMOVE_CHAR_FROM_GROUP catalina
	ENDIF


	IF NOT IS_CAR_DEAD players_car_cat4
		SET_CAR_COORDINATES players_car_cat4 1297.0 278.0 18.0
		SET_CAR_HEADING players_car_cat4 150.0
	ENDIF
		

		
		

	LOAD_GROUP_DECISION_MAKER DM_GROUP_MISSION_NORM dm_players_group_cat4 
	SET_GROUP_DECISION_MAKER Players_Group dm_players_group_cat4
	CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE dm_players_group_cat4 EVENT_DAMAGE
	CLEAR_GROUP_DECISION_MAKER_EVENT_RESPONSE dm_players_group_cat4 EVENT_VEHICLE_DAMAGE_WEAPON

		
		

	LOAD_CHAR_DECISION_MAKER DM_PED_MISSION_EMPTY dm_catalina_cat4	

	
	SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
	SET_CHAR_DECISION_MAKER catalina dm_catalina_cat4 																			
	TASK_TOGGLE_PED_THREAT_SCANNER catalina 1 1 1

	CLEAR_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_catalina_cat4 EVENT_ACQUAINTANCE_PED_HATE
 //	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE smoke3_DM EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
	ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_catalina_cat4 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE
	 // ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE dm_catalina_cat4 EVENT_ACQUAINTANCE_PED_HATE TASK_COMPLEX_KILL_PED_ON_FOOT 0.0 100.0 0.0 0.0 FALSE TRUE
	SET_CHAR_SHOOT_RATE catalina 100
	
	IF DOES_OBJECT_EXIST object_cutscene_satchel_cat4  
		TASK_PICK_UP_OBJECT catalina object_cutscene_satchel_cat4 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
	ENDIF


	

	WHILE NOT HAS_ANIMATION_LOADED misc
		WAIT 0
	ENDWHILE

	SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED


  
   	SET_FIXED_CAMERA_POSITION 	1284.6248 257.5731 23.8334  0.0 0.0 0.0 
   	POINT_CAMERA_AT_POINT 		1284.5079 258.5662 23.8240   JUMP_CUT 



   	CAMERA_RESET_NEW_SCRIPTABLES
    CAMERA_PERSIST_TRACK TRUE                   
    CAMERA_PERSIST_POS TRUE                       
    CAMERA_SET_VECTOR_MOVE  1284.6248 257.5731 23.8334 1287.0680 258.5688 22.8720    4500 TRUE    // two sets of coord for cam start point and end point + time for pan
    CAMERA_SET_VECTOR_TRACK 1284.5079 258.5662 23.8240 1287.1801 259.5328 22.6311   4500 TRUE    // two sets of coords for cam aim at start and end point + time for pan
							 
    

	IF NOT IS_CHAR_DEAD catalina
	    SET_CHAR_COORDINATES catalina 1288.2772 268.9509 18.546
	  	SET_CHAR_HEADING catalina 321.0		 
	ENDIF




 


   //	SWITCH_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
   //	SWITCH_PED_ROADS_OFF -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993 
   

SET_CAR_DENSITY_MULTIPLIER 0.0
SET_PED_DENSITY_MULTIPLIER 0.0

 

 

//SET_CAR_DENSITY_MULTIPLIER 1.0
//SET_PED_DENSITY_MULTIPLIER 1.0
 //	LOAD_SCENE 1288.2772 268.9509 25.546

 	LOAD_SCENE_IN_DIRECTION	1284.6248 257.5731 23.8334 	45.0


	CLEAR_AREA 1287.3625 257.5327 23.0934 1000.0 TRUE
	
	DO_FADE 1000 FADE_IN


	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE
	

	TIMERA = 0
	TIMERB = 0

	flag_cutscene1_cat4 = 0
	WHILE NOT flag_cutscene1_cat4 = 10
		WAIT 0

		IF flag_cutscene1_cat4 = 0
			START_CHAR_FACIAL_TALK scplayer 10000

		 	LOAD_MISSION_AUDIO 1 SOUND_CAT2_AB
			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE  

			PRINT_NOW ( CAT2_AB ) 10000 1		//	yOU wanna rob a betting shop?	
			PLAY_MISSION_AUDIO 1
			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
			ENDWHILE
			CLEAR_THIS_PRINT CAT2_AB
			STOP_CHAR_FACIAL_TALK scplayer
			flag_cutscene1_cat4 = 1 

		ENDIF	



		IF flag_cutscene1_cat4 = 1

			IF NOT IS_CHAR_DEAD catalina
			  //	CLEAR_CHAR_TASKS catalina
		//	  	TASK_GOTO_CHAR catalina scplayer -1 0.5

			  // 	TASK_GO_STRAIGHT_TO_COORD catalina 1290.00 269.55 19.1870 PEDMOVE_WALK 5000


				  //	OPEN_SEQUENCE_TASK seq_cat_stealing_car	
					 	TASK_GO_STRAIGHT_TO_COORD catalina 1290.93 269.75 18.5469 PEDMOVE_WALK 3000
						
					   //	TASK_GOTO_CHAR -1 scplayer -1 0.5

						
				//	CLOSE_SEQUENCE_TASK seq_cat_stealing_car
				  //	PERFORM_SEQUENCE_TASK catalina seq_cat_stealing_car 
				  //	CLEAR_SEQUENCE_TASK seq_cat_stealing_car 

			ENDIF
			flag_cutscene1_cat4 = 2



		ENDIF

		IF TIMERB > 3000
			IF flag_cutscene1_cat4 = 2

				
				CAMERA_RESET_NEW_SCRIPTABLES
				SET_FIXED_CAMERA_POSITION 1292.4452 266.8836 19.0157  0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 1291.7562 267.5977 19.1396 JUMP_CUT

				 
				LOAD_MISSION_AUDIO 1 SOUND_CAT2_AC
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE 
				 
				IF NOT IS_CHAR_DEAD catalina
					PRINT_NOW ( CAT2_AC ) 10000 1		//	yOU man enough?
					PLAY_MISSION_AUDIO 1
					START_CHAR_FACIAL_TALK scplayer 10000
				ENDIF



				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE

				IF NOT IS_CHAR_DEAD catalina
					STOP_CHAR_FACIAL_TALK catalina
				ENDIF

				CLEAR_THIS_PRINT CAT2_AC 

				
				flag_cutscene1_cat4 = 3
			ENDIF
		ENDIF


		IF flag_cutscene1_cat4 = 3

				SET_FIXED_CAMERA_POSITION 1300.1182 272.2002 20.6733 0.0 0.0 0.0 
				POINT_CAMERA_AT_POINT 1299.1564 271.9637 20.5352 JUMP_CUT

				IF NOT IS_CHAR_DEAD catalina
				  //	CLEAR_CHAR_TASKS catalina


					TASK_PLAY_ANIM catalina PASS_Rifle_Ped misc 4.0 FALSE FALSE FALSE FALSE -1

				ENDIF


				LOAD_MISSION_AUDIO 1 SOUND_CATX_RB	// carl, here

				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

				IF NOT IS_CHAR_DEAD catalina
					CLEAR_CHAR_TASKS catalina

				ENDIF



				TASK_PLAY_ANIM scplayer PASS_Rifle_Ply misc 4.0 FALSE FALSE FALSE FALSE -1
				IF DOES_OBJECT_EXIST object_cutscene_satchel_cat4
					TASK_PICK_UP_OBJECT scplayer object_cutscene_satchel_cat4 0.0 0.0 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
				ENDIF

				IF NOT IS_CHAR_DEAD catalina
					START_CHAR_FACIAL_TALK catalina 10000
				ENDIF

				PRINT_NOW ( CATX_RB ) 10000 1		// carl, here
				PLAY_MISSION_AUDIO 1

			 //	SET_FIXED_CAMERA_POSITION 1289.3167 268.4893 20.0960  0.0 0.0 0.0 
			 //	POINT_CAMERA_AT_POINT 1290.1132 269.0416 19.8500 JUMP_CUT






				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
				CLEAR_THIS_PRINT CATX_RB 










				IF NOT IS_CHAR_DEAD catalina
					STOP_CHAR_FACIAL_TALK catalina
				ENDIF


			flag_cutscene1_cat4 = 4
			TIMERA = 0
		ENDIF

		IF flag_cutscene1_cat4 = 4

			IF NOT IS_CHAR_DEAD catalina
		   		TASK_GO_STRAIGHT_TO_COORD catalina 1292.81 269.01 18.5469 PEDMOVE_WALK -1
			ENDIF
			flag_cutscene1_cat4 = 5

			LOAD_MISSION_AUDIO 1 SOUND_CAT2_JA	//Satchel charges?!  Where the hell’d you get them?

			WHILE NOT HAS_MISSION_AUDIO_LOADED 1
				WAIT 0
			ENDWHILE  

			PRINT_NOW ( CAT2_JA ) 10000 1		//Satchel charges?!  Where the hell’d you get them?
			PLAY_MISSION_AUDIO 1

			WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
				WAIT 0
			ENDWHILE
			CLEAR_THIS_PRINT CAT2_JA 


		ENDIF

		IF flag_cutscene1_cat4 = 5
			IF TIMERA > 1500
				flag_cutscene1_cat4 = 10
			ENDIF
		ENDIF


			IF TIMERB > 10000
				flag_cutscene1_cat4 = 10
			ENDIF

		IF IS_SKIP_CUTSCENE_BUTTON_PRESSED
			CLEAR_MISSION_AUDIO 1
			flag_cutscene1_cat4 = 10
		ENDIF

	ENDWHILE

	SET_PLAYER_CONTROL player1 ON
	SWITCH_WIDESCREEN OFF
	RESTORE_CAMERA_JUMPCUT
	SET_CAMERA_BEHIND_PLAYER
	IF DOES_OBJECT_EXIST object_cutscene_satchel_cat4
		DELETE_OBJECT object_cutscene_satchel_cat4
	ENDIF
	CAMERA_RESET_NEW_SCRIPTABLES

	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0

	SWITCH_ENTRY_EXIT genOTB TRUE

	IF NOT IS_CHAR_DEAD catalina
		STOP_CHAR_FACIAL_TALK catalina
	ENDIF
	STOP_CHAR_FACIAL_TALK scplayer
				




   //	MARK_CAR_AS_NO_LONGER_NEEDED players_car_cat4

	IF NOT IS_CAR_DEAD players_car_cat4	
	   //	SET_CAR_COORDINATES players_car_cat4 1297.0 278.0 40.0
	   //	SET_CAR_HEADING players_car_cat4 150.0
	   //	FREEZE_CAR_POSITION players_car_cat4 TRUE 
	  //	SET_CAR_VISIBLE players_car_cat4 FALSE
		//SET_CAR_PROOFS players_car_cat4 TRUE TRUE TRUE TRUE TRUE
		SET_CAR_HEALTH players_car_cat4 1000
		SET_CAN_BURST_CAR_TYRES players_car_cat4 FALSE

	ENDIF

	GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE 10

  	IF NOT IS_CHAR_DEAD catalina 
		IF NOT IS_GROUP_MEMBER catalina Players_Group
			SET_GROUP_MEMBER Players_Group catalina
			SET_GROUP_FOLLOW_STATUS Players_Group FALSE
		ENDIF
	ENDIF	


	
	



	ADD_SPRITE_BLIP_FOR_COORD coord_otb_x_cat4 coord_otb_y_cat4 coord_otb_z_cat4 RADAR_SPRITE_CAT_PINK blip_otb_cat4
	PRINT_NOW CAT4_01 10000 1	// get inside the OTB

   //	ADD_BLIP_FOR_COORD coord_otb_x_cat4 coord_otb_y_cat4 coord_otb_z_cat4 blip_otb_cat4
	
 /*	IF set_audio_to_be_played = 0
		index_dialogue_cat4 = 0
		set_audio_to_be_played = 1
	ENDIF

	IF index_dialogue_cat4 = 0
		GOSUB load_and_play_audio_cat4  
	ENDIF	*/

	WHILE NOT HAS_MODEL_LOADED kev_safe
		WAIT 0
	ENDWHILE



	WHILE NOT HAS_MODEL_LOADED man_safenew
		WAIT 0
	ENDWHILE

	WHILE NOT HAS_MODEL_LOADED DESERT_EAGLE
		OR NOT HAS_MODEL_LOADED rider1_door
		WAIT 0
	ENDWHILE


	



	WHILE NOT player_in_otb_flag_cat4_local_var = 1
		WAIT 0

		GOSUB streaming_otb_clerk


		IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 50.0 50.0 50.0 FALSE		 // fix to prevent flash of text appearing
	 		GOSUB check_cat_cat4
		ENDIF

		IF flag_mission_failed_cat4 = 1
			RETURN
		ENDIF

		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S
			flag_holdup_cat4 = 0
			flag_getaway_cat4 = 0
			flag_mission_passed_cat4 = 1 
			RETURN
		ENDIF	

		GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE players_ammo_cat4
		GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_DETONATOR players_ammo_2_cat4



   	 	

		
		IF players_ammo_cat4 <= 0	  // if player runs out of satchels, mission failed
		AND players_ammo_2_cat4 <=0
			IF NOT flag_safe_explosion_delay = 1
				IF NOT IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE 819.55 8.09 1003.4 822.27 10.98 1005.72
	 ///	AND NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_DETONATOR
	  //	AND NOT HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE
		//		 
					flag_holdup_cat4 = 0
					flag_mission_failed_cat4 = 1
					PRINT_NOW ( CAT4_2 ) 10000 1 //  you ran out of satchels!
					RETURN
				ENDIF
			ENDIF
		ENDIF


	  /*	IF flag_set_cat_as_group_member = 0
			IF NOT LOCATE_CHAR_ANY_MEANS_3D scplayer 1288.1781 269.8612 18.5469 15.0 15.0 15.0 FALSE 
				SET_GROUP_FOLLOW_STATUS Players_Group TRUE
			ENDIF
		ENDIF	  */


	ENDWHILE





	
	REMOVE_BLIP blip_otb_cat4

	   //	counter_blaze_cat4 = counter_blaze_cat4 - 1

		IF flag_robbery_cutscene_otb = 0
			IF player_in_otb_flag_cat4_local_var = 1
				REMOVE_ANIMATION misc
	 			CLEAR_SMALL_PRINTS


				// //////////////////Cutscene of player and cat entering the otb. Cat hold gun to clerk
				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				CLEAR_PRINTS		
			 //	SET_GROUP_FOLLOW_STATUS Players_Group TRUE

			//	SET_FIXED_CAMERA_POSITION 833.6932 10.2776 1003.8510 0.0 0.0 0.0
		   //		POINT_CAMERA_AT_CHAR scplayer FIXED JUMP_CUT 

				SET_FIXED_CAMERA_POSITION 833.5528 9.9425 1003.91 0.0 0.0 0.0
				POINT_CAMERA_AT_POINT 832.7419 9.3669 1004.01  JUMP_CUT

				LOAD_MISSION_AUDIO 3 SOUND_BANK_CAT2_BANK

			   // creating the peds that get shot	
				index_cat4 = 0
				WHILE index_cat4 < 6
					CREATE_RANDOM_CHAR coord_random_char_x[index_cat4] coord_random_char_y[index_cat4] coord_random_char_z[index_cat4] char_random_ped_cat4[index_cat4]
					index_cat4++
				ENDWHILE
				DISPLAY_RADAR OFF
				flag_robbery_cutscene_otb = 1

				TIMERA = 0
				TIMERB = 0

				flag_cutscene1_cat4 = 0
				WHILE NOT flag_cutscene1_cat4 = 10
					WAIT 0
				 //CLEAR_AREA coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 1000.0 TRUE

					IF	flag_cutscene1_cat4 >= 2 
						IF HAS_MISSION_AUDIO_FINISHED 3
							PLAY_MISSION_AUDIO 3
						ENDIF
					ENDIF

					
					IF flag_cutscene1_cat4 = 0
						
						SET_CHAR_COORDINATES scplayer 832.4548 7.0794 1003.1870 
						TASK_GO_STRAIGHT_TO_COORD scplayer 828.2866 8.2237 1003.1870 PEDMOVE_WALK 5000
						CLEAR_AREA 828.2866 8.2237 1003.1870 2.0 TRUE





												  
						IF NOT IS_CHAR_DEAD catalina

						  	SET_CHAR_PROOFS catalina FALSE FALSE TRUE FALSE FALSE

							SET_CHAR_ACCURACY catalina 90
						   	CLEAR_CHAR_TASKS catalina
							SET_CHAR_AREA_VISIBLE catalina 3



						   
						//	ADD_GROUP_DECISION_MAKER_EVENT_RESPONSE dm_catalina_cat4 EVENT_ACQUAINTANCE_PED_HATE TASK_SIMPLE_GANG_DRIVEBY 0.0 100.0 0.0 0.0 TRUE FALSE



						//	IF NOT IS_CHAR_DEAD char_otb_clerk_cat4 

						   	IF IS_GROUP_MEMBER catalina Players_Group
								REMOVE_CHAR_FROM_GROUP catalina
							ENDIF	

							IF IS_CHAR_IN_ANY_CAR catalina
								WARP_CHAR_FROM_CAR_TO_COORD catalina 833.5104 4.3569 1003.5870 
							ELSE
								SET_CHAR_COORDINATES catalina 833.5104 4.3569 1003.1870  
							ENDIF

							SET_CHAR_HAS_USED_ENTRY_EXIT catalina 1290.0 270.0 10.0
 	
							 
							SET_CHAR_HEADING catalina 107.0
							GIVE_WEAPON_TO_CHAR catalina WEAPONTYPE_DESERT_EAGLE 10000 
							TASK_GO_STRAIGHT_TO_COORD catalina 824.3764 3.4945 1003.1797  PEDMOVE_RUN -1
							CLEAR_AREA 824.3764 3.4945 1003.1797 2.0 TRUE

						ENDIF

						flag_cutscene1_cat4 = 1
					ENDIF

					IF flag_cutscene1_cat4 = 1					 
						IF TIMERA > 2000								
							IF NOT IS_CHAR_DEAD catalina
							   	CLEAR_CHAR_TASKS catalina
								OPEN_SEQUENCE_TASK seq_cat_stealing_car	
									TASK_GO_STRAIGHT_TO_COORD -1 824.3764 3.4945 1003.1797 PEDMOVE_RUN 3000
									TASK_AIM_GUN_AT_COORD -1 coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 5000
								CLOSE_SEQUENCE_TASK seq_cat_stealing_car
								PERFORM_SEQUENCE_TASK catalina seq_cat_stealing_car 
								CLEAR_SEQUENCE_TASK seq_cat_stealing_car 

								iSetOTBPanic = 1

							 	LOAD_MISSION_AUDIO 1 SOUND_CAT2_DA

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE  

								PRINT_NOW ( CAT2_DA ) 10000 1		// Open the backroom door or I blow your fucking face off!
								PLAY_MISSION_AUDIO 1

								IF NOT IS_CHAR_DEAD catalina
									START_CHAR_FACIAL_TALK catalina	10000
								ENDIF

								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									WAIT 0
								ENDWHILE
								CLEAR_THIS_PRINT CAT2_DA 

								IF NOT IS_CHAR_DEAD catalina
									STOP_CHAR_FACIAL_TALK catalina
								ENDIF


								
					
		   
								SET_FIXED_CAMERA_POSITION 829.9543 8.6043 1004.8922 0.0 0.0 0.0 
								POINT_CAMERA_AT_POINT 829.2075 7.9448 1004.8064  JUMP_CUT 

								TASK_LOOK_AT_COORD scplayer 824.17 3.95 1007.0 3000

								IF NOT IS_CHAR_DEAD catalina
									CLEAR_CHAR_TASKS catalina
								   	SET_CHAR_COORDINATES catalina 824.3764 3.4945 1003.1797
								   	SET_CHAR_HEADING catalina 107.0
									TASK_AIM_GUN_AT_COORD catalina coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 5000
								ENDIF

								index_cat4 = 0
								WHILE index_cat4 < 6
									IF NOT IS_CHAR_DEAD char_random_ped_cat4[index_cat4]
										IF index_cat4 = 0
										OR index_cat4 = 3
										OR index_cat4 = 5
											TASK_HANDS_UP char_random_ped_cat4[index_cat4] 900000
										ELSE
											TASK_DUCK char_random_ped_cat4[index_cat4] 900000
										ENDIF
									ENDIF	
									index_cat4++
								ENDWHILE



								LOAD_MISSION_AUDIO 1 SOUND_CAT2_DB

								WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									WAIT 0
								ENDWHILE  

								PRINT_NOW ( CAT2_DB ) 10000 1		// Leave the panic button or I'll kill your children too!
								PLAY_MISSION_AUDIO 1

								IF NOT IS_CHAR_DEAD catalina
									START_CHAR_FACIAL_TALK catalina 10000
								ENDIF

								WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									WAIT 0
								ENDWHILE
								CLEAR_THIS_PRINT CAT2_DB

								IF NOT IS_CHAR_DEAD catalina
									STOP_CHAR_FACIAL_TALK catalina
								ENDIF





							 //	iSetOTBPanic = 1

								
								TIMERA = 0	
							ENDIF		   
							flag_cutscene1_cat4 = 2
						ENDIF
					ENDIF

					IF flag_cutscene1_cat4 = 2
						IF TIMERA > 500


			  				IF NOT IS_CHAR_DEAD catalina		 
							   	CLEAR_CHAR_TASKS catalina
							  	TASK_STAND_STILL catalina 900000
						   //		TASK_AIM_GUN_AT_COORD catalina coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 900000
							 	SET_FIXED_CAMERA_POSITION 821.8057 3.2657 1005.0901  0.0 0.0 0.0 
								POINT_CAMERA_AT_POINT 822.7056 3.6678 1004.921  JUMP_CUT

							 
							// 	TASK_LOOK_ABOUT catalina 3000
							//	TASK_LOOK_ABOUT scplayer 3000
						   //	TASK_LOOK_AT_COORD scplayer LookAtX LookAtY LookAtZ LookTime
						   //	TASK_LOOK_AT_COORD scplayer LookAtX LookAtY LookAtZ LookTime
							//	IF IS_PLAYER_PLAYING player1
									TASK_LOOK_AT_COORD scplayer 824.17 3.95 1007.0 3000
							  //	ENDIF


								TASK_LOOK_AT_COORD CATALINA 824.17 3.95 1007.0 2000

								TASK_AIM_GUN_AT_COORD catalina 819.55 5.55 1005.59 5000

					  //			REPORT_MISSION_AUDIO_EVENT_AT_POSITION 824.17 3.95 1007.0 SOUND_SECURITY_ALARM


							  	REPORT_MISSION_AUDIO_EVENT_AT_POSITION coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 SOUND_CAT2_SECURITY_ALARM
								PRINT_NOW CAT4_3 5000 1


							  /*	LOAD_MISSION_AUDIO 3 SOUND_CAT2_SECURITY_ALARM
								WHILE NOT HAS_MISSION_AUDIO_LOADED 3
									WAIT 0
								ENDWHILE  
								PLAY_MISSION_AUDIO 3 	*/
								

							 	// ALARM GOES OFF HERE

								// SOUND_BANK_ALARM_LOOP
							    
									
								TIMERA = 0
			  				ENDIF 
							flag_cutscene1_cat4 = 3
						ENDIF
					ENDIF

					IF flag_cutscene1_cat4 = 3
						IF TIMERA > 2000
							IF NOT IS_CHAR_DEAD catalina
								TASK_AIM_GUN_AT_COORD catalina coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 5000
							ENDIF
							CLEAR_THIS_PRINT CAT4_3

							LOAD_MISSION_AUDIO 1 SOUND_CAT2_FA
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								WAIT 0
							ENDWHILE  

							PRINT_NOW ( CAT2_FA ) 10000 1		// I warned you, you stupid bitch!								
							
							IF NOT IS_CHAR_DEAD catalina
								START_CHAR_FACIAL_TALK catalina 10000
							ENDIF

							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT CAT2_FA 

							IF NOT IS_CHAR_DEAD catalina
								STOP_CHAR_FACIAL_TALK catalina
							ENDIF


							IF IS_PLAYER_PLAYING player1
								TASK_ACHIEVE_HEADING scplayer 112.0
							ENDIF
							
							flag_otb_robbing_peds_panic = 1
							flag_cutscene1_cat4 = 4
							TIMERA = 0
						ENDIF
					ENDIF

					IF flag_cutscene1_cat4 = 4
						IF TIMERA > 1000

							IF NOT IS_CHAR_DEAD catalina
								CLEAR_CHAR_TASKS catalina

								SET_FIXED_CAMERA_POSITION 829.9543 8.6043 1004.8922 0.0 0.0 0.0 
								POINT_CAMERA_AT_POINT 829.2075 7.9448 1004.8064  JUMP_CUT 

	  
								IF NOT IS_CHAR_DEAD catalina
									IF NOT IS_CHAR_DEAD char_random_ped_cat4[index_random_char]
										TASK_KILL_CHAR_ON_FOOT catalina char_random_ped_cat4[index_random_char]
									ENDIF
								ENDIF



								 LOAD_MISSION_AUDIO 1 SOUND_CAT2_FB

								  

								 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								 	WAIT 0
								 ENDWHILE  

							   	 PRINT ( CAT2_FB ) 10000 1		// Stupid fucking bitch!
								 PLAY_MISSION_AUDIO 1
								 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								 	WAIT 0
								 ENDWHILE
							   	 CLEAR_THIS_PRINT CAT2_FB 

								 




							ENDIF

							flag_cutscene1_cat4 = 5
						ENDIF
					ENDIF




					IF TIMERB > 17000
						flag_cutscene1_cat4 = 10
					ENDIF

				ENDWHILE

			  	//GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_DETONATOR 1

		  	  	
				IF IS_PLAYER_PLAYING player1	
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					RESTORE_CAMERA_JUMPCUT
				   	SET_CAMERA_BEHIND_PLAYER
		
					IF NOT IS_CHAR_DEAD catalina 
						TASK_AIM_GUN_AT_COORD catalina coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 900000
					ENDIF
					GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE players_ammo_2_cat4
					GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE players_ammo_3_cat4


				   	flag_robbery_cutscene_otb = 1
				   	flag_otb_robbing_peds_panic = 1
				   	TIMERA = 0
				   	TIMERB = 0
					GET_GAME_TIMER game_timer2_cat4
				ENDIF
				
			ENDIF
			
		ENDIF
	// END CUTSCENE



	 


	
	TIMERB= 0

	WHILE NOT flag_has_cat_got_the_loot_cat4 = 1 
		WAIT 0

	  //	IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE
		GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE players_ammo_cat4


   	 	GET_AMMO_IN_CHAR_WEAPON scplayer WEAPONTYPE_DETONATOR players_ammo_2_cat4


		IF players_ammo_cat4 <= 0	  // if player runs out of satchels, mission failed
		AND players_ammo_2_cat4 <=0
			IF NOT flag_safe_explosion_delay = 1
				IF NOT IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE 819.55 8.09 1003.4 822.27 10.98 1005.72
					flag_holdup_cat4 = 0
					flag_mission_failed_cat4 = 1
					PRINT_NOW ( CAT4_2 ) 5000 1 //  you ran out of satchels!
					RETURN	
				ENDIF
			ENDIF		
		ENDIF
						





		IF IS_CHAR_DEAD catalina
			flag_holdup_cat4 = 0
			flag_mission_failed_cat4 = 1
			
			PRINT_NOW ( CAT4_15 ) 5000 1 //  GOD: CATS DEAD!
 
			RETURN
		ENDIF

		IF help_text_cat4 = 0
			PRINT_HELP CAT4_43  
			help_text_cat4 = 1	// GOD plant satchel
			TIMERB = 0
		ENDIF							 

		IF help_text_cat4 = 1
		   	IF TIMERB > 6000
				PRINT_NOW CAT4_42 6000 1
	
				help_text_cat4 = 2	   //HELP  press o to throw
		 		TIMERB = 0
			ENDIF
			
		ENDIF	



		
		IF help_text_cat4 <= 2
		  IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE
		   	IF players_ammo_cat4 < players_ammo_3_cat4
			  		CLEAR_HELP

					

					help_text_cat4 = 3	   // HELP get a safe distance away
					TIMERB = 0
				ENDIF

		   	ENDIF
		ENDIF


		IF help_text_cat4 = 3
			IF TIMERB > 1500	

				PRINT_NOW CAT4_44 6000 1
				PRINT_HELP CAT4_45  
				help_text_cat4 = 4
				TIMERB = 0
			ENDIF
		ENDIF
		
		IF help_text_cat4 <= 4
	   		IF DOES_OBJECT_EXIST object_door_cat4
			   //	IF HAS_OBJECT_BEEN_DAMAGED object_door_cat4
		  		IF HAS_OBJECT_OF_TYPE_BEEN_SMASHED 824.4123 10.80 1003.2004 10.0 rider1_door	
			   		IF TIMERB > 500
						CLEAR_SMALL_PRINTS
						CLEAR_HELP
						
						help_text_cat4 = 5	   //HELP  press cicle to detonate
						TIMERB = 0
					ENDIF
				ENDIF
			ENDIF
		ENDIF	
		
		IF help_text_cat4 = 5

		        CLEAR_HELP

				LOAD_MISSION_AUDIO 1 SOUND_CAT2_DC
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  
				
				PRINT ( CAT2_DC ) 10000 1		// CAT carl, get in there
				PLAY_MISSION_AUDIO 1

				IF NOT IS_CHAR_DEAD catalina
					START_CHAR_FACIAL_TALK catalina	10000
				ENDIF

				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
				CLEAR_THIS_PRINT CAT2_DC

				IF NOT IS_CHAR_DEAD catalina
					STOP_CHAR_FACIAL_TALK catalina
				ENDIF

				PRINT_NOW CAT4_46 10000 1 //use satchel to open safe

				help_text_cat4 = 6	  
				TIMERB = 0
	   //		ENDIF
		ENDIF	
		
		
		
					 

		
		IF player_in_otb_flag_cat4_local_var = 1

		  	GET_GAME_TIMER game_timer1_cat4
			game_timer1_cat4 -= game_timer2_cat4

			IF flag_text_otb = 0
				flag_text_otb = 1
				GET_GAME_TIMER game_timer2_cat4
			ENDIF

		  	GET_GAME_TIMER game_timer1_cat4
			game_timer1_cat4 -= game_timer2_cat4


			IF flag_text_otb = 1
				 	flag_text_otb = 2


					  
					 LOAD_MISSION_AUDIO 1 SOUND_CAT2_FC

					 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					 	WAIT 0
					 ENDWHILE  

				   //	PRINT CAT2_FC 10000 1 // cat starts killing people  "now i kill you"
					PLAY_MISSION_AUDIO 1
					 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					 	WAIT 0
					 ENDWHILE
				   //	 CLEAR_THIS_PRINT CAT2_FC 






					IF NOT IS_CHAR_DEAD catalina
						CLEAR_CHAR_TASKS catalina
					  //	GET_RANDOM_CHAR_IN_SPHERE 826.9793 1.7226 1003.1797 30.0 TRUE TRUE TRUE char_random_ped_cat4[index_random_char]
				   //		MARK_CHAR_AS_NO_LONGER_NEEDED char_random_ped_cat4[index_random_char]
							
						IF NOT IS_CHAR_DEAD char_random_ped_cat4[index_random_char]
							TASK_KILL_CHAR_ON_FOOT catalina char_random_ped_cat4[index_random_char]
						ENDIF
					 //	index_random_char++
												//SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_MISSION1
					ENDIF
				   //	TIMERB = 0
				   GET_GAME_TIMER game_timer2_cat4
			   //	ENDIF
			ENDIF

		  	GET_GAME_TIMER game_timer1_cat4
			game_timer1_cat4 -= game_timer2_cat4




			IF flag_text_otb = 2
			   //	IF game_timer1_cat4 > 5000
				flag_text_otb = 3

				//	LOAD_MISSION_AUDIO 1 SOUND_CATX_TU // you’re all dead, assholes!

				LOAD_MISSION_AUDIO 1 SOUND_CATX_TJ // eat my shit
				WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					WAIT 0
				ENDWHILE  

					 //	PRINT_NOW ( CATX_TU ) 10000 1		
							 							
				PLAY_MISSION_AUDIO 1
				WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					WAIT 0
				ENDWHILE
						  //		CLEAR_THIS_PRINT CATX_TU 


				GET_GAME_TIMER game_timer2_cat4
			 //	ENDIF
			   //	TIMERB = 0
				
			ENDIF

		  	GET_GAME_TIMER game_timer1_cat4
			game_timer1_cat4 -= game_timer2_cat4


			IF flag_text_otb = 3
				IF game_timer1_cat4 > 5000
				 	flag_text_otb = 4


					 LOAD_MISSION_AUDIO 1 SOUND_CAT2_EB

					  

					 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					 	WAIT 0
					 ENDWHILE  

				  //	 PRINT ( CAT2_EB ) 10000 1		// Stop playing with your balls and hurry up!
					 PLAY_MISSION_AUDIO 1
					 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					 	WAIT 0
					 ENDWHILE
				   //	 CLEAR_THIS_PRINT CAT2_EB 

					GET_GAME_TIMER game_timer2_cat4
				ENDIF
			   //TIMERB = 0
			ENDIF

		  	GET_GAME_TIMER game_timer1_cat4
			game_timer1_cat4 -= game_timer2_cat4


			IF flag_text_otb = 4
				IF game_timer1_cat4 > 10000
				 	flag_text_otb = 5

					 LOAD_MISSION_AUDIO 1 SOUND_CAT2_EA

					  

					 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
					 	WAIT 0
					 ENDWHILE  

				   //	 PRINT ( CAT2_EA ) 10000 1		// MOVE IT!
					 PLAY_MISSION_AUDIO 1
					 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
					 	WAIT 0
					 ENDWHILE
				   //	 CLEAR_THIS_PRINT CAT2_EA 
					GET_GAME_TIMER game_timer2_cat4
				ENDIF
			  //	TIMERB = 0
			ENDIF

		  	GET_GAME_TIMER game_timer1_cat4
			game_timer1_cat4 -= game_timer2_cat4


			IF flag_text_otb = 5
				IF game_timer1_cat4 > 10000
				 	flag_text_otb = 6
					GET_GAME_TIMER game_timer2_cat4
				ENDIF
			 //	TIMERB = 0

			ENDIF

		  	GET_GAME_TIMER game_timer1_cat4
			game_timer1_cat4 -= game_timer2_cat4


			// cat goes mental and starts to shoot everyone
			IF flag_text_otb > 1
				IF index_random_char < 2
					IF IS_CHAR_DEAD char_random_ped_cat4[index_random_char]
					   	MARK_CHAR_AS_NO_LONGER_NEEDED char_random_ped_cat4[index_random_char]

						IF NOT IS_CHAR_DEAD catalina
							IF NOT IS_CHAR_SHOOTING catalina
								index_random_char++

								IF index_random_char < 2
									CLEAR_CHAR_TASKS catalina
									IF NOT IS_CHAR_DEAD char_random_ped_cat4[index_random_char]
										TASK_KILL_CHAR_ON_FOOT catalina char_random_ped_cat4[index_random_char]
									ENDIF

									// cats comments
									IF index_random_char = 2

 			  							LOAD_MISSION_AUDIO 1 SOUND_CATX_TD
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

									 //	PRINT_NOW ( CATX_TD ) 10000 1		//	BLEED STUPID MOTHER FUCKER	
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
									//	CLEAR_THIS_PRINT CATX_TD 
									ENDIF

									// cats comments
									IF index_random_char = 4
    			  						LOAD_MISSION_AUDIO 1 SOUND_CATX_TJ
										WHILE NOT HAS_MISSION_AUDIO_LOADED 1
											WAIT 0
										ENDWHILE  

										//PRINT_NOW ( CATX_TJ ) 10000 1		//	EAT MY SHIT	
										PLAY_MISSION_AUDIO 1
										WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
											WAIT 0
										ENDWHILE
									  //	CLEAR_THIS_PRINT CATX_TJ 
									ENDIF

									// cats comments
									IF index_random_char = 5
    			  					   	LOAD_MISSION_AUDIO 1 SOUND_CATX_TQ
									  //	WHILE NOT HAS_MISSION_AUDIO_LOADED 1
									   	   //	WAIT 0
									   //	ENDWHILE  

									   	PRINT_NOW ( CATX_TQ ) 100 1		//	who wants me now?
									   //	PLAY_MISSION_AUDIO 1
									//	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
									   //	WAIT 0
									  //	ENDWHILE
									 //	CLEAR_THIS_PRINT CATX_TQ 
									ENDIF

										
									

									
								ELSE
									CLEAR_CHAR_TASKS catalina
									TASK_AIM_GUN_AT_COORD catalina coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 900000

								ENDIF
							   //	index_random_char++	

							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF


		
		ENDIF


	 //	ENDIF

		GOSUB streaming_otb_clerk


			IF IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE 820.3084 -1.6925 1003.2775 828.8 4.7199 1008.0789 
				IF NOT IS_CHAR_DEAD catalina
					SET_CHAR_HEALTH catalina 0
				ENDIF
		   	ENDIF


		IF flag_has_otb_door_been_open = 0
			// kill catalina if satchel is too close to her

			IF IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE 823.89 8.99 1000.4 826.18 11.98 1009.72
				SET_OBJECT_COLLISION_DAMAGE_EFFECT object_door_cat4 TRUE

				FREEZE_OBJECT_POSITION object_door_cat4 FALSE
				BREAK_OBJECT object_door_cat4 TRUE
				SET_OBJECT_HEALTH object_door_cat4 0 
				  	 //	IF HAS_OBJECT_OF_TYPE_BEEN_SMASHED 824.4123 10.80 1003.2004 10.0 rider1_door	
					 //	ROTATE_OBJECT object_door_cat4 270.0 3.0 FALSE
				flag_has_otb_door_been_open = 1
				ADD_EXPLOSION 820.5 9.7 1003.2164 EXPLOSION_GRENADE

			ENDIF




	   		IF DOES_OBJECT_EXIST object_door_cat4
			   //	IF HAS_OBJECT_BEEN_DAMAGED object_door_cat4
			   IF IS_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_REMOTE_SATCHEL_CHARGE	
					IF IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE 823.89 8.99 1000.4 826.18 11.98 1009.72
						SET_OBJECT_COLLISION_DAMAGE_EFFECT object_door_cat4 TRUE

						FREEZE_OBJECT_POSITION object_door_cat4 FALSE
						BREAK_OBJECT object_door_cat4 TRUE
						SET_OBJECT_HEALTH object_door_cat4 0 
				  	 //	IF HAS_OBJECT_OF_TYPE_BEEN_SMASHED 824.4123 10.80 1003.2004 10.0 rider1_door	
					 //	ROTATE_OBJECT object_door_cat4 270.0 3.0 FALSE
						flag_has_otb_door_been_open = 1
						ADD_EXPLOSION 820.5 9.7 1003.2164 EXPLOSION_GRENADE

					ENDIF
				ENDIF
		 	ENDIF
		ENDIF




		IF flag_has_otb_door_been_open = 1
			IF IS_EXPLOSION_IN_AREA EXPLOSION_GRENADE 819.55 8.09 1003.4 822.27 10.98 1005.72
				CLEAR_HELP
				CLEAR_PRINTS
				flag_has_safe_been_robbed_cat4 = 1
				MARK_MODEL_AS_NO_LONGER_NEEDED rider1_door
				SWITCH_ENTRY_EXIT genOTB FALSE

				IF flag_safe_explosion_delay =0
					flag_safe_explosion_delay = 1
					TIMERA= 0
				ENDIF


	    		DELETE_OBJECT small_safe_cat4

				CREATE_OBJECT man_safenew 820.5 9.7 1003.2164 small_safe_cat4	
				SET_OBJECT_HEADING small_safe_cat4 90.0 

				IF NOT IS_CHAR_DEAD catalina
					FREEZE_CHAR_POSITION_AND_DONT_LOAD_COLLISION catalina FALSE
				ENDIF


			ENDIF
		ENDIF


		IF flag_safe_explosion_delay = 1
			IF TIMERA > 500
				ADD_EXPLOSION 820.5 9.7 1003.2164 EXPLOSION_GRENADE

				index_cat4 = 4
				WHILE index_cat4 < 6
					MARK_CHAR_AS_NO_LONGER_NEEDED char_random_ped_cat4[index_cat4]
					index_cat4++
				ENDWHILE

				IF DOES_OBJECT_EXIST object_door_cat4
					DELETE_OBJECT object_door_cat4
				ENDIF

				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
			 //	SWITCH_PED_ROADS_OFF 816.5276 4.4624 1001.8299 847.5276 22.4624 1009.82993 

			  //	SET_CAR_DENSITY_MULTIPLIER 0.0
				SET_PED_DENSITY_MULTIPLIER 0.0

 

 



				SET_FIXED_CAMERA_POSITION 823.9662 11.5153 1005.8105 0.0 0.0 0.0 //establishing shot
				POINT_CAMERA_AT_POINT 823.2487 11.0891 1005.2598  JUMP_CUT 


				SET_CHAR_COORDINATES scplayer 824.8268 10.0672 1003.1870
				SET_CHAR_HEADING scplayer 100.0
			 	SET_CHAR_AREA_VISIBLE scplayer 3
			  	SET_AREA_VISIBLE 3

				
			  	IF NOT IS_CHAR_DEAD catalina 
					CLEAR_CHAR_TASKS catalina 
					SET_CHAR_COORDINATES catalina 827.95 9.21 1003.13
					SET_CHAR_HEADING catalina 93.0
				  //	TASK_CHAT_WITH_CHAR catalina scplayer TRUE FALSE
				  //	TASK_DUCK catalina 4000
				  //	IF flag_cat_shoot_cops = 1
						SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP
						CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP	
						flag_cat_shoot_cops = 0
				   //	ENDIF
				ENDIF	 

 

				SET_PLAYER_CONTROL player1 OFF
				SWITCH_WIDESCREEN ON
				MAKE_PLAYER_GANG_DISAPPEAR
				CLEAR_AREA 824.8268 10.0672 1003.1870 1000.0 TRUE


				REQUEST_ANIMATION bomber
				WHILE NOT HAS_ANIMATION_LOADED bomber
					WAIT 0
				ENDWHILE

				TASK_DUCK scplayer 3000

				TIMERA = 0
				TIMERB = 0

				flag_cutscene1_cat4 = 0
				WHILE NOT flag_cutscene1_cat4 = 10
					WAIT 0
				 //CLEAR_AREA coord_otb_clerk_x_cat4 coord_otb_clerk_y_cat4 coord_otb_clerk_z_cat4 1000.0 TRUE




					IF flag_cutscene1_cat4 = 0
						IF TIMERA > 2000
						  //	SET_FIXED_CAMERA_POSITION 819.6043 10.3457 1004.4273  0.0 0.0 0.0 //establishing shot
						//	POINT_CAMERA_AT_POINT 820.5945 10.2527 1004.3235   JUMP_CUT
							flag_cutscene1_cat4 = 1 
						ENDIF
					ENDIF



					IF flag_cutscene1_cat4 = 1
						IF TIMERA > 2000

					  //		SET_FIXED_CAMERA_POSITION 823.9662 11.5153 1005.8105 0.0 0.0 0.0 //establishing shot
						 //	POINT_CAMERA_AT_POINT 823.2487 11.0891 1005.2598  JUMP_CUT 




							SET_CHAR_COORDINATES scplayer 824.8268 10.0672 1003.1870
							SET_CHAR_HEADING scplayer 100.0
						 	SET_CHAR_AREA_VISIBLE scplayer 3
						  	SET_AREA_VISIBLE 3


						
							flag_cutscene1_cat4 = 2
							TASK_GO_STRAIGHT_TO_COORD scplayer 821.3832 9.7296 1003.1951  PEDMOVE_WALK -1





	  
					  //		CREATE_CAR BOBCAT 1297.0 278.0 18.0 getaway_car_car4 
						 /*	SET_CAR_HEADING getaway_car_car4 150.0 
							SET_CAR_HEALTH getaway_car_car4 800 
							SET_CAR_PROOFS getaway_car_car4 TRUE TRUE TRUE TRUE FALSE
							FREEZE_CAR_POSITION getaway_car_car4 TRUE 
							SET_CAN_BURST_CAR_TYRES getaway_car_car4 FALSE	*/

							IF NOT IS_CAR_DEAD players_car_cat4	

								FREEZE_CAR_POSITION players_car_cat4 TRUE 
								SET_CAR_VISIBLE players_car_cat4 TRUE
								SET_CAR_PROOFS players_car_cat4 FALSE FALSE FALSE FALSE FALSE
								SET_CAR_HEALTH players_car_cat4 1500
								SET_CAR_COORDINATES players_car_cat4 1297.0 278.0 18.0
								SET_CAR_HEADING players_car_cat4 150.0
							ENDIF

						

							REQUEST_MODEL copcarru
							REQUEST_MODEL csher
							REQUEST_MODEL colt45

							WHILE NOT HAS_MODEL_LOADED copcarru
							OR NOT HAS_MODEL_LOADED csher
							OR NOT HAS_MODEL_LOADED	colt45
								WAIT 0
							ENDWHILE


							CREATE_CAR copcarru 1296.0380 251.8959 18.4002 car_police1_cat4 
							CREATE_CAR copcarru 1301.3264 265.0127 18.4007 car_police2_cat4
							CREATE_CAR copcarru 1308.3976 278.7967 18.546  car_police3_cat4
						
							SET_CAR_HEADING car_police1_cat4 11.7173  
							SET_CAR_HEADING	car_police2_cat4 70.3422 
							SET_CAR_HEADING	car_police3_cat4 119.5617 


							CREATE_CHAR_INSIDE_CAR car_police1_cat4 PEDTYPE_COP csher char_pol1_cat4 
							GIVE_WEAPON_TO_CHAR char_pol1_cat4 WEAPONTYPE_PISTOL 1000  


							CREATE_CHAR_INSIDE_CAR car_police2_cat4 PEDTYPE_COP csher char_pol2_cat4 
							GIVE_WEAPON_TO_CHAR char_pol2_cat4 WEAPONTYPE_PISTOL 1000  

							CREATE_CHAR_INSIDE_CAR car_police3_cat4 PEDTYPE_COP csher char_pol3_cat4 
							GIVE_WEAPON_TO_CHAR char_pol3_cat4 WEAPONTYPE_PISTOL 1000  

							TIMERA =  0
						ENDIF
						
	
					ENDIF	
					
					IF flag_cutscene1_cat4 = 2
						IF TIMERA > 2000

							SET_FIXED_CAMERA_POSITION 	819.5102 10.5367 1004.4390  0.0 0.0 0.0 
							POINT_CAMERA_AT_POINT 		820.4842 10.3240 1004.3616   JUMP_CUT


							OPEN_SEQUENCE_TASK seq_cat_stealing_car	
																//TASK_DUCK -1 3000
								TASK_PLAY_ANIM -1 BOM_Plant_In BOMBER 4.0 FALSE FALSE FALSE FALSE 0
								TASK_PLAY_ANIM -1 BOM_Plant_Loop BOMBER 4.0 FALSE FALSE FALSE FALSE 5000

							   
							CLOSE_SEQUENCE_TASK seq_cat_stealing_car
							PERFORM_SEQUENCE_TASK scplayer seq_cat_stealing_car 
							CLEAR_SEQUENCE_TASK seq_cat_stealing_car 



							IF NOT IS_CHAR_DEAD scplayer
								START_CHAR_FACIAL_TALK scplayer	100000
							ENDIF

							LOAD_MISSION_AUDIO 1 SOUND_CAT2_GA
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								WAIT 0
							ENDWHILE  

							PRINT_NOW ( CAT2_GA ) 10000 1		// player, ok , i got it
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT CAT2_GA
							IF NOT IS_CHAR_DEAD scplayer
								STOP_CHAR_FACIAL_TALK scplayer
							ENDIF










	 						LOAD_MISSION_AUDIO 1 SOUND_CAT2_GB

							 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							 ENDWHILE  


							IF NOT IS_CHAR_DEAD catalina
								START_CHAR_FACIAL_TALK catalina	10000
							ENDIF

							PRINT_NOW CAT2_GB 10000 1 // cat	About fucking time, you’re a fucking sloth!
							PLAY_MISSION_AUDIO 1
							 WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							 	WAIT 0
							 ENDWHILE
							 CLEAR_THIS_PRINT CAT2_GB

							IF NOT IS_CHAR_DEAD catalina
								STOP_CHAR_FACIAL_TALK catalina
							ENDIF




							



 
							TIMERA = 0
							flag_cutscene1_cat4 = 3
						ENDIF
					ENDIF


					IF flag_cutscene1_cat4 = 3
						IF TIMERA > 1000

							
							SET_FIXED_CAMERA_POSITION 	832.2155 11.3956 1003.3882 0.0 0.0 0.0 
							POINT_CAMERA_AT_POINT 		831.3511 10.9217 1003.5567   JUMP_CUT

	 						LOAD_MISSION_AUDIO 1 SOUND_CAT2_GC
  							 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							 ENDWHILE  

							OPEN_SEQUENCE_TASK seq_cat_stealing_car	
								TASK_ACHIEVE_HEADING -1 10.0
	   							TASK_GO_STRAIGHT_TO_COORD -1 833.0 10.0 1003.0 PEDMOVE_RUN -1

							CLOSE_SEQUENCE_TASK seq_cat_stealing_car
							PERFORM_SEQUENCE_TASK scplayer seq_cat_stealing_car 
							CLEAR_SEQUENCE_TASK seq_cat_stealing_car 


							IF NOT IS_CHAR_DEAD catalina
					   			CLEAR_CHAR_TASKS catalina 	 
								TASK_GO_STRAIGHT_TO_COORD catalina 834.0 7.0 1003.0 PEDMOVE_RUN -1
							ENDIF





							IF NOT IS_CHAR_DEAD scplayer
								START_CHAR_FACIAL_TALK scplayer 10000
							ENDIF


							PRINT_NOW CAT2_GC 10000 1 // lets go
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE


							IF NOT IS_CHAR_DEAD scplayer
								STOP_CHAR_FACIAL_TALK scplayer
							ENDIF


							CLEAR_THIS_PRINT CAT2_GC






							
							 
							TIMERA = 0
							flag_cutscene1_cat4 = 4
						ENDIF
					ENDIF




					IF flag_cutscene1_cat4 = 4
					//	IF TIMERA > 1000
							DO_FADE 1000 FADE_OUT

							WHILE GET_FADING_STATUS
								WAIT 0
							ENDWHILE
							MAKE_PLAYER_GANG_REAPPEAR

							
							 
							TIMERA = 0
							flag_cutscene1_cat4 = 5
					//	ENDIF
					ENDIF


					

					IF TIMERB > 14800
						flag_cutscene1_cat4 = 10
					ENDIF


				ENDWHILE

				SET_AREA_VISIBLE 0
			

		  		SET_CHAR_COORDINATES scplayer 1292.7206 271.0581 18.5469
				SET_CHAR_HAS_USED_ENTRY_EXIT scplayer 833.0 9.0 10.0
				LOAD_SCENE 1292.7206 271.0581 18.5469

				SET_CHAR_HEADING scplayer 333.0	
			
				IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_DETONATOR
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_DETONATOR 
				ENDIF



			   
				IF NOT IS_CHAR_DEAD catalina
					   //	TASK_WARP_CHAR_INTO_CAR_AS_PASSENGER catalina players_car_cat4 0
						
					SET_CHAR_COORDINATES catalina 1292.6598 275.2182 18.5547 
					SET_CHAR_HEADING catalina 340.1134
					SET_CHAR_CANT_BE_DRAGGED_OUT catalina TRUE
					SET_CHAR_STAY_IN_CAR_WHEN_JACKED catalina TRUE
					SET_CHAR_PROOFS catalina FALSE FALSE FALSE FALSE FALSE
					SET_CHAR_HAS_USED_ENTRY_EXIT catalina 833.0 9.0 10.0
					SET_CHAR_AREA_VISIBLE catalina 0
		   			
				ENDIF


				CLEAR_AREA 1292.7206 271.0581 18.5469 1000.0 TRUE

				DO_FADE 1000 FADE_IN


				IF NOT IS_CAR_DEAD players_car_cat4
				  	IF IS_CAR_PASSENGER_SEAT_FREE players_car_cat4 0 	
						IF NOT IS_CHAR_DEAD catalina
						   //	TASK_WARP_CHAR_INTO_CAR_AS_PASSENGER catalina players_car_cat4 0
							
							TASK_ENTER_CAR_AS_PASSENGER catalina players_car_cat4 -1 0

						   	IF IS_GROUP_MEMBER catalina Players_Group
								SET_GROUP_FOLLOW_STATUS Players_Group FALSE
							ENDIF		 
							flag_dont_make_cat_leave_car = 1
		   				ENDIF

					ELSE

						IF NOT IS_GROUP_MEMBER catalina Players_Group
							SET_GROUP_MEMBER Players_Group catalina
							SET_GROUP_FOLLOW_STATUS Players_Group TRUE

						ENDIF	
						flag_dont_make_cat_leave_car = 0

					ENDIF

				ELSE

					IF NOT IS_GROUP_MEMBER catalina Players_Group
						SET_GROUP_MEMBER Players_Group catalina
						SET_GROUP_FOLLOW_STATUS Players_Group TRUE

					ENDIF	


					
				ENDIF



				//flag_are_cops_pissed = 1
				SET_PLAYER_CONTROL player1 ON
				SWITCH_WIDESCREEN OFF
				RESTORE_CAMERA_JUMPCUT
				SET_CAMERA_BEHIND_PLAYER
		   
				REMOVE_ANIMATION bomber 
				DISPLAY_RADAR ON
				DELETE_OBJECT object_door_cat4
				DELETE_OBJECT small_safe_cat4
			
				SET_CAR_DENSITY_MULTIPLIER 1.0
				SET_PED_DENSITY_MULTIPLIER 1.0

				MARK_MODEL_AS_NO_LONGER_NEEDED man_safenew
				MARK_MODEL_AS_NO_LONGER_NEEDED kev_safe

				SET_RADIO_CHANNEL RS_COUNTRY


				flag_has_cat_got_the_loot_cat4 = 1
				REMOVE_BLIP blip_otb_cat4
				flag_holdup_cat4 = 0
				flag_getaway_cat4 = 1
				//RETURN
			ENDIF
		ENDIF 



	ENDWHILE



  

	



		// Next step
		flag_holdup_cat4 = 0
		flag_getaway_cat4 = 1
		

RETURN



// ***********************************************************************
// ////////////////////////// GETAWAY ////////////////////////////////////
// ***********************************************************************

LVAR_FLOAT coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4

LVAR_INT temp_cop_cat4
LVAR_INT flag_display_text_cat4

getaway_cat4:



 

	 

					   
	flag_display_text_cat4 = 0											   
	coord_hiding_spot_x_cat4 = 872.9051 
	coord_hiding_spot_y_cat4 = -30.3161 
	coord_hiding_spot_z_cat4 = 62.1893 


  //	SWITCH_ROADS_OFF 1140.9072 213.5046 1.7728 1585.8451 382.9843 43.8501


	SET_CAR_DENSITY_MULTIPLIER 0.0
//SET_PED_DENSITY_MULTIPLIER 0.0


    GOSUB streaming_otb_clerk



	WHILE NOT flag_mission_passed_cat4 = 1
		
		// Cat checks
		WAIT 0

		GOSUB streaming_otb_clerk

		// catalina getting into car

		IF flag_create_car_cat_enters = 0
			IF player_in_otb_flag_cat4_local_var = 0 // if player is outside of OTBI

				ALTER_WANTED_LEVEL player1 4
	
	
				flag_text_otb = 4
				
				DISPLAY_RADAR ON
				SET_CHAR_HEADING scplayer 333.0
				SET_CAMERA_BEHIND_PLAYER 
				IF NOT IS_CHAR_DEAD catalina
					
				  //	TASK_GO_STRAIGHT_TO_COORD catalina 1298.40 275.65  19.5 PEDMOVE_RUN 
				  	SET_CHAR_ACCURACY catalina 100
					IF NOT IS_CAR_DEAD players_car_cat4
						GET_CAR_MODEL players_car_cat4 players_car_model_cat4

						IF IS_CAR_PASSENGER_SEAT_FREE players_car_cat4 0
						AND IS_THIS_MODEL_A_CAR players_car_model_cat4
						//	TASK_ENTER_CAR_AS_PASSENGER catalina players_car_cat4 -1 0

							SET_CHAR_CANT_BE_DRAGGED_OUT catalina TRUE
							SET_CHAR_STAY_IN_CAR_WHEN_JACKED catalina TRUE
		 
		 				   	ADD_BLIP_FOR_CAR players_car_cat4 blip_getaway_car
						   	SET_BLIP_AS_FRIENDLY blip_getaway_car TRUE

		 					LOAD_MISSION_AUDIO 1 SOUND_CATX_BF
	  						 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							 ENDWHILE  

							PRINT_NOW CATX_BF 10000 1 // cat	get in the car
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT CATX_BF

						ELSE

		 					LOAD_MISSION_AUDIO 1 SOUND_CATX_NA
	  						 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
							 	WAIT 0
							 ENDWHILE  

							PRINT_NOW CATX_NA 10000 1 // Carl, get us some fucking wheels!
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT CATX_NA
	  

						ENDIF
					ELSE

	 					LOAD_MISSION_AUDIO 1 SOUND_CATX_NA
  						 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
						 	WAIT 0
						 ENDWHILE  

						PRINT_NOW CATX_NA 10000 1 // Carl, get us some fucking wheels!
						PLAY_MISSION_AUDIO 1
						WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
							WAIT 0
						ENDWHILE
						CLEAR_THIS_PRINT CATX_NA
	  



	  				ENDIF
				ENDIF


				IF NOT IS_CHAR_DEAD char_pol1_cat4 
					IF NOT IS_CAR_DEAD car_police1_cat4
						OPEN_SEQUENCE_TASK seq_cat_stealing_car	
							TASK_LEAVE_CAR -1 car_police1_cat4
							TASK_AIM_GUN_AT_CHAR -1 scplayer 20000
						  	TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK seq_cat_stealing_car
						PERFORM_SEQUENCE_TASK char_pol1_cat4 seq_cat_stealing_car 
						CLEAR_SEQUENCE_TASK seq_cat_stealing_car 
						SET_CHAR_DECISION_MAKER char_pol1_cat4 dm_cops_cat4
					ENDIF

				ENDIF 

				IF NOT IS_CHAR_DEAD char_pol2_cat4 
					IF NOT IS_CAR_DEAD car_police2_cat4

						OPEN_SEQUENCE_TASK seq_cat_stealing_car	
							TASK_LEAVE_CAR -1 car_police2_cat4
							 TASK_AIM_GUN_AT_CHAR -1 scplayer 20000

						  	TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK seq_cat_stealing_car
						PERFORM_SEQUENCE_TASK char_pol2_cat4 seq_cat_stealing_car 
						CLEAR_SEQUENCE_TASK seq_cat_stealing_car 
						SET_CHAR_DECISION_MAKER char_pol2_cat4 dm_cops_cat4
					ENDIF

				ENDIF 


				IF NOT IS_CHAR_DEAD char_pol3_cat4 
					IF NOT IS_CAR_DEAD car_police3_cat4

						OPEN_SEQUENCE_TASK seq_cat_stealing_car	
							TASK_LEAVE_CAR -1 car_police3_cat4
							TASK_AIM_GUN_AT_CHAR -1 scplayer 20000

						   	TASK_KILL_CHAR_ON_FOOT -1 scplayer
						CLOSE_SEQUENCE_TASK seq_cat_stealing_car
						PERFORM_SEQUENCE_TASK char_pol3_cat4 seq_cat_stealing_car 
						CLEAR_SEQUENCE_TASK seq_cat_stealing_car 
						SET_CHAR_DECISION_MAKER char_pol3_cat4 dm_cops_cat4
					ENDIF


				ENDIF 





				


			   flag_create_car_cat_enters = 1

			ENDIF
		ENDIF
		
		IF NOT IS_CHAR_DEAD catalina 
				IF flag_create_car_cat_enters = 1

						MARK_CAR_AS_NO_LONGER_NEEDED car_police1_cat4
						MARK_CAR_AS_NO_LONGER_NEEDED car_police2_cat4
						MARK_CAR_AS_NO_LONGER_NEEDED car_police3_cat4
						MARK_CHAR_AS_NO_LONGER_NEEDED char_pol1_cat4
						MARK_CHAR_AS_NO_LONGER_NEEDED char_pol2_cat4
						MARK_CHAR_AS_NO_LONGER_NEEDED char_pol3_cat4



						MARK_CAR_AS_NO_LONGER_NEEDED players_car_cat4
						flag_create_car_cat_enters = 2
					   	IF DOES_BLIP_EXIST blip_hiding_spot
							REMOVE_BLIP blip_hiding_spot
						ENDIF

						IF DOES_BLIP_EXIST blip_paint_and_spray_cat4
							REMOVE_BLIP blip_paint_and_spray_cat4
						ENDIF
						//SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993
						
						
												
						SET_CAR_DENSITY_MULTIPLIER 1.0
						SET_PED_DENSITY_MULTIPLIER 1.0
							

					


						flag_create_car_cat_enters = 2

			   //	ENDIF
			ENDIF

			IF flag_create_car_cat_enters = 2
	
					IF IS_CHAR_IN_ANY_CAR scplayer 
				//	AND IS_CHAR_IN_ANY_CAR catalina
						flag_create_car_cat_enters = 3
						flag_display_text_cat4 = 1
						

				/*		CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP	
						flag_cat_shoot_cops = 1
						SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
																	*/
					   //	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE

						IF DOES_BLIP_EXIST blip_getaway_car
							REMOVE_BLIP blip_getaway_car
						ENDIF

						IF NOT IS_GROUP_MEMBER catalina Players_Group
							SET_GROUP_MEMBER Players_Group catalina
							SET_GROUP_FOLLOW_STATUS Players_Group TRUE
						ENDIF
					   //	CLEAR_CHAR_TASKS catalina
						
						IF NOT IS_CAR_DEAD players_car_cat4 
							FREEZE_CAR_POSITION players_car_cat4 FALSE
							SET_CAR_PROOFS players_car_cat4 FALSE FALSE FALSE FALSE FALSE
							
						ENDIF
	

						IF flag_are_cops_pissed  = 0	
							IF NOT DOES_BLIP_EXIST blip_hiding_spot

								ADD_BLIP_FOR_COORD coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 blip_hiding_spot
								CHANGE_BLIP_COLOUR blip_hiding_spot YELLOW

								DISABLE_ALL_ENTRY_EXITS FALSE

							ENDIF
						ENDIF

						IF flag_are_cops_pissed  = 1
							IF NOT IS_CHAR_DEAD catalina
								IF IS_GROUP_MEMBER catalina Players_Group	
									IF NOT DOES_BLIP_EXIST blip_paint_and_spray_cat4
									 // ADD_SPRITE_BLIP_FOR_CONTACT_POINT coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z RADAR_SPRITE_SPRAY blip_paint_and_spray_cat4
									//	ADD_BLIP_FOR_COORD coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z blip_paint_and_spray_cat4
										ADD_SPRITE_BLIP_FOR_COORD coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z RADAR_SPRITE_SPRAY blip_paint_and_spray_cat4 
										PRINT_NOW CAT4_32 5000 1   // GET TO THE PAINT AND SPRAY
										DISABLE_ALL_ENTRY_EXITS TRUE
									ENDIF
								ENDIF
							ENDIF
						ENDIF

					ENDIF
				
			ENDIF


		   	IF flag_create_car_cat_enters = 3
	  			GOSUB check_cat_cat4
				IF flag_mission_failed_cat4 = 1
					RETURN
				ENDIF
				IF NOT IS_CHAR_DEAD catalina
					IF IS_CHAR_IN_ANY_CAR catalina

						CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP	
						flag_cat_shoot_cops = 1
						SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP
											
						flag_dont_make_cat_leave_car = 0
						flag_create_car_cat_enters = 4
					ENDIF 
				ENDIF 
			ENDIF 


		   	IF flag_create_car_cat_enters = 4
	  			GOSUB check_cat_cat4
				IF flag_mission_failed_cat4 = 1
					RETURN
				ENDIF
			ENDIF 

		ELSE

			
			flag_getaway_cat4 = 0
			flag_mission_failed_cat4 = 1				  
			PRINT_NOW ( CAT4_15 ) 5000 1 //  CATS DEAD!
			RETURN
		
		ENDIF


	   	/////////////////////////////////////////////
		// checking if player is back at the hideout
		//////////////////////////////////////////////

//CAT2_HA
		IF flag_are_cops_pissed = 0
			IF LOCATE_CHAR_ANY_MEANS_3D scplayer coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 4.0 4.0 4.0 TRUE
			
			ENDIF

		IF IS_CHAR_DEAD catalina

			
			flag_getaway_cat4 = 0
			flag_mission_failed_cat4 = 1
			flag_mission_passed_cat4 = 0				  
			PRINT_NOW ( CAT4_15 ) 5000 1 //  CATS DEAD!
			RETURN
		ENDIF



		   IF NOT IS_CHAR_DEAD catalina 
		   	IF IS_CHAR_IN_ANY_CAR  scplayer
		   		IF IS_CHAR_IN_ANY_CAR catalina

		   
		   			//////////////////////////////
		   			/// CATS rant
		   			///////////////////////////////

		   		 /*	IF set_audio_to_be_played = 0
		   				index_dialogue_cat4 = 0
		   				set_audio_to_be_played = 1
		   			ENDIF	 */

		   			IF flag_text_otb >= 4
		   			AND flag_text_otb <= number_of_conversation_samples_cat4
		   				IF flag_text_otb = 4
							SHUT_PLAYER_UP player1 TRUE
		   					IF TIMERA > 10000
		   						GOSUB load_and_play_audio_cat4
		   					ENDIF
		   				ELSE
		   					GOSUB load_and_play_audio_cat4
		   				ENDIF  
		   			

		   			ENDIF	


		   		ENDIF
		   		//////////////////////////////
		   		/// END CATS RANT
		   		///////////////////////////////
		   	ENDIF
		   ENDIF



			IF NOT IS_CHAR_DEAD catalina 
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 4.0 4.0 4.0 FALSE 
				AND LOCATE_CHAR_ANY_MEANS_3D catalina coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 10.0 10.0 10.0 FALSE	
				//   	IF temp_cop_cat4 = -1
					flag_mission_passed_cat4 = 1
					flag_mission_failed_cat4 = 0


					IF LOCATE_CHAR_IN_CAR_3D scplayer coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 10.0 10.0 10.0 TRUE
						SET_PLAYER_CONTROL player1 OFF
						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer car

						TASK_CAR_TEMP_ACTION scplayer car TEMPACT_HANDBRAKESTRAIGHT 2000000

					ENDIF




					// cutscene of cat leaving the group

					SET_PLAYER_CONTROL player1 OFF
					SWITCH_WIDESCREEN ON

					TIMERA = 0
					TIMERB = 0

					flag_cutscene1_cat4 = 0
					WHILE NOT flag_cutscene1_cat4 = 10
						WAIT 0					   

						// [CAT2_CA]	You impress me, Carl Johnson!
						// prints to use [CATX_RC] "Here's you cut, big man"



						CLEAR_AREA 866.7373 -24.0333 64.9955 1000.0 TRUE 
						IF flag_cutscene1_cat4 = 0
							flag_cutscene1_cat4 = 1
							SET_FIXED_CAMERA_POSITION 866.3945 -23.1135 65.1866 0.0 0.0 0.0 
							POINT_CAMERA_AT_POINT 866.7373 -24.0333 64.9955  JUMP_CUT
							CLEAR_MISSION_AUDIO 1

							LOAD_MISSION_AUDIO 1 SOUND_CAT2_CA
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								WAIT 0
							ENDWHILE  

							PRINT_NOW ( CAT2_CA ) 10000 1		// You impress me, Carl Johnson!
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT CAT2_CA 

						ENDIF

						IF flag_cutscene1_cat4 = 1
							flag_cutscene1_cat4 = 2

							LOAD_MISSION_AUDIO 1 SOUND_CATX_RC
							WHILE NOT HAS_MISSION_AUDIO_LOADED 1
								WAIT 0
							ENDWHILE  

							PRINT_NOW ( CATX_RC ) 10000 1		// heres your cut.
							PLAY_MISSION_AUDIO 1
							WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
								WAIT 0
							ENDWHILE
							CLEAR_THIS_PRINT CATX_RC   

						ENDIF

						IF flag_cutscene1_cat4 = 2
							flag_cutscene1_cat4 = 3
							TIMERA = 0
							IF NOT IS_CHAR_DEAD catalina
							   	IF IS_GROUP_MEMBER catalina Players_Group
									REMOVE_CHAR_FROM_GROUP catalina
								ENDIF	

								IF IS_CHAR_IN_ANY_CAR catalina
									TASK_LEAVE_ANY_CAR catalina
								ENDIF
							ENDIF
						ENDIF

						IF flag_cutscene1_cat4 = 3
							IF TIMERA < 1500
								flag_cutscene1_cat4 = 4

								IF NOT IS_CHAR_DEAD catalina
		   							TASK_GO_STRAIGHT_TO_COORD catalina 869.84 -26.24 63.1797 PEDMOVE_WALK -1
								ENDIF
							ENDIF
 
						ENDIF








						IF TIMERB > 7000
							flag_cutscene1_cat4 = 10
						ENDIF

					ENDWHILE
					DELETE_CHAR catalina
					SET_PLAYER_CONTROL player1 ON
					SWITCH_WIDESCREEN OFF
					RESTORE_CAMERA_JUMPCUT
					SET_CAMERA_BEHIND_PLAYER

				// end cutscene


				ENDIF	 


		   
			ENDIF




		ENDIF







	ENDWHILE


	REMOVE_BLIP blip_hiding_spot
	flag_mission_passed_cat4 = 1	
	flag_getaway_cat4 = 0

RETURN





//////////////////////////////////////////////
// GOSUB FUNCTIONS
//////////////////////////////////////////////





	

//////////////////////////////////////////
// Check if cat is stilll in players group
//////////////////////////////////////////
	check_cat_cat4:
		IF NOT IS_CHAR_DEAD catalina
			// IF player leaves cat behind	
		  /* 	IF IS_CHAR_PLAYING_ANIM catalina woman_run
				SET_CHAR_ANIM_SPEED catalina woman_run 10.0
			ENDIF	 */	
			
			
			
			// comments when cat is shooting cops
			
			IF flag_are_cops_pissed  = 1
				IF IS_CHAR_SHOOTING catalina
				   //	IF TIMERB > 10000
					
				   	
 					GOSUB cat_talks_nasty_to_cops_cat4
				

				ENDIF
			ENDIF
					
			
			 
		
		   //	IF player_in_otb_flag_cat4_local_var = 1
			IF NOT IS_GROUP_MEMBER catalina Players_Group
		        PRINT_NOW CAT4_16 4000 1 //"WHERE ARE U GOING?"


				IF flag_getaway_cat4 = 1
					IF DOES_BLIP_EXIST blip_paint_and_spray_cat4
						REMOVE_BLIP blip_paint_and_spray_cat4
					ENDIF

					IF DOES_BLIP_EXIST blip_hiding_spot
						REMOVE_BLIP blip_hiding_spot
					ENDIF
				ENDIF

				IF NOT DOES_BLIP_EXIST blip_cat_cat4
					IF NOT IS_CHAR_DEAD catalina
		        		ADD_BLIP_FOR_CHAR catalina blip_cat_cat4
  						SET_BLIP_AS_FRIENDLY blip_cat_cat4 TRUE
					ENDIF
				ENDIF

				WHILE NOT IS_GROUP_MEMBER catalina Players_Group
					WAIT 0


					IF NOT IS_CHAR_DEAD catalina 
						IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer catalina 200.0 200.0 200.0 FALSE
							flag_holdup_cat4 = 0
							flag_getaway_cat4 = 0
							flag_mission_failed_cat4 = 1
							PRINT_NOW ( CAT4_14 ) 5000 1 //  you  abandoned cat
							RETURN


						ENDIF
						

						IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer catalina 8.0 8.0 8.0 FALSE
						    SET_GROUP_MEMBER Players_Group catalina
  							 SET_GROUP_FOLLOW_STATUS Players_Group TRUE
							REMOVE_BLIP blip_cat_cat4
						
							IF flag_getaway_cat4 = 1
								IF flag_are_cops_pissed  = 0	
									IF NOT DOES_BLIP_EXIST blip_hiding_spot
										ADD_BLIP_FOR_COORD coord_hiding_spot_x_cat4 coord_hiding_spot_y_cat4 coord_hiding_spot_z_cat4 blip_hiding_spot
										CHANGE_BLIP_COLOUR blip_hiding_spot YELLOW
										PRINT_NOW ( CAT4_13 ) 10000 1		// Get back to the hideout
										DISABLE_ALL_ENTRY_EXITS FALSE
									ENDIF
								ENDIF

								IF flag_are_cops_pissed  = 1
									IF NOT DOES_BLIP_EXIST blip_paint_and_spray_cat4
										ADD_SPRITE_BLIP_FOR_COORD coord_spray_shop_x coord_spray_shop_y coord_spray_shop_z RADAR_SPRITE_SPRAY blip_paint_and_spray_cat4  
										PRINT_NOW CAT4_32 5000 1   // GET TO THE PAINT AND SPRAY
										DISABLE_ALL_ENTRY_EXITS TRUE
									ENDIF
								ENDIF
							ENDIF
						ENDIF



					ELSE
						REMOVE_BLIP blip_otb_cat4
						

						flag_holdup_cat4 = 0
						flag_getaway_cat4 = 0

						flag_mission_failed_cat4 = 1				  
						PRINT_NOW ( CAT4_15 ) 5000 1 //  CATS DEAD!
						RETURN
					ENDIF
				ENDWHILE
		    ENDIF
		ELSE
			REMOVE_BLIP blip_otb_cat4
			
			flag_holdup_cat4 = 0
			flag_getaway_cat4 = 0

			flag_mission_failed_cat4 = 1				  
			PRINT_NOW ( CAT4_15 ) 5000 1 //  CATS DEAD!
			RETURN
		ENDIF

	RETURN



// *********************************** audio******************************		 
load_and_play_audio_cat4:

	IF play_audio_flag_cat4 = 0
		IF NOT IS_CHAR_DEAD catalina
			SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH catalina TRUE
		ENDIF
		CLEAR_MISSION_AUDIO 1
		LOAD_MISSION_AUDIO 1 cat4_audio[index_dialogue_cat4]
		play_audio_flag_cat4 = 1
	ENDIF

	IF play_audio_flag_cat4 = 1
		IF HAS_MISSION_AUDIO_LOADED 1
			PLAY_MISSION_AUDIO 1
			PRINT_NOW $cat4_text[index_dialogue_cat4] 10000 1
			play_audio_flag_cat4 = 2
		ENDIF
	ENDIF



	IF play_audio_flag_cat4 = 2
		IF HAS_MISSION_AUDIO_FINISHED 1
		   //	temp_int = c3_counter - 1
			play_audio_flag_cat4 = 0
			CLEAR_THIS_PRINT $cat4_text[index_dialogue_cat4]

			IF NOT IS_CHAR_DEAD catalina
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH catalina FALSE
			ENDIF

			index_dialogue_cat4++
			flag_text_otb++
		ENDIF
	ENDIF



RETURN


cat_talks_nasty_to_cops_cat4:

IF flag_text_cat_police_killing_cat4 = 0
	
	flag_text_cat_police_killing_cat4 = 1


	LOAD_MISSION_AUDIO 1 SOUND_CATX_SA
	 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	 	WAIT 0
	 ENDWHILE  

	PRINT_NOW CATX_SA 10000 1 
	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT CATX_SA
	RETURN

ENDIF


IF flag_text_cat_police_killing_cat4 = 1

	LOAD_MISSION_AUDIO 1 SOUND_CATX_SB
	 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	 	WAIT 0
	 ENDWHILE  

	PRINT_NOW CATX_SB 10000 1 
	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT CATX_SB


	flag_text_cat_police_killing_cat4 = 2
	RETURN
ENDIF


IF flag_text_cat_police_killing_cat4 = 2

	LOAD_MISSION_AUDIO 1 SOUND_CATX_SC
	 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
	 	WAIT 0
	 ENDWHILE  

	PRINT_NOW CATX_SC 10000 1 
	PLAY_MISSION_AUDIO 1
	WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
		WAIT 0
	ENDWHILE
	CLEAR_THIS_PRINT CATX_SC


 	flag_text_cat_police_killing_cat4 = 3
	RETURN
ENDIF


IF flag_text_cat_police_killing_cat4 = 3
	 	flag_text_cat_police_killing_cat4 = 4
		LOAD_MISSION_AUDIO 1 SOUND_CATX_SD
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_SD 10000 1 
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_SD
		RETURN

	
ENDIF


IF flag_text_cat_police_killing_cat4 = 4
	 	flag_text_cat_police_killing_cat4 = 5
		LOAD_MISSION_AUDIO 1 SOUND_CATX_SE
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_SE 10000 1 
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_SE
		RETURN
ENDIF


IF flag_text_cat_police_killing_cat4 = 5


	 	flag_text_cat_police_killing_cat4 = 6

		LOAD_MISSION_AUDIO 1 SOUND_CATX_SF
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_SF 10000 1 // cat	get in the car
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_SF
		RETURN
ENDIF


IF flag_text_cat_police_killing_cat4 = 6
	 	flag_text_cat_police_killing_cat4 = 7

		LOAD_MISSION_AUDIO 1 SOUND_CATX_SG
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_SG 10000 1 // cat	get in the car
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_SG
		RETURN
ENDIF


IF flag_text_cat_police_killing_cat4 = 7

	 	flag_text_cat_police_killing_cat4 = 8
		LOAD_MISSION_AUDIO 1 SOUND_CATX_SH
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_SH 10000 1 // cat	get in the car
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_SH
		RETURN
ENDIF

IF flag_text_cat_police_killing_cat4 = 8

	 	flag_text_cat_police_killing_cat4 = 9
		LOAD_MISSION_AUDIO 1 SOUND_CATX_TD
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_TD 10000 1 // cat	get in the car
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_TD
		RETURN
ENDIF

IF flag_text_cat_police_killing_cat4 = 9

	 	flag_text_cat_police_killing_cat4 = 10
		LOAD_MISSION_AUDIO 1 SOUND_CATX_TJ
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_TJ 10000 1 // cat	get in the car
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_TJ
		RETURN
ENDIF

IF flag_text_cat_police_killing_cat4 = 10

	 	flag_text_cat_police_killing_cat4 = 0
		LOAD_MISSION_AUDIO 1 SOUND_CATX_TQ
		 WHILE NOT HAS_MISSION_AUDIO_LOADED 1
		 	WAIT 0
		 ENDWHILE  

		PRINT_NOW CATX_TQ 10000 1 // cat	get in the car
		PLAY_MISSION_AUDIO 1
		WHILE NOT HAS_MISSION_AUDIO_FINISHED 1
			WAIT 0
		ENDWHILE
		CLEAR_THIS_PRINT CATX_TQ
		RETURN
ENDIF





				



// **************************************** Mission cat4 failed ***********************

mission_failed_cat4:
	PRINT_BIG ( M_FAIL ) 5000 1 //"Mission Failed"
	// this happens in the cleanup function, why here? It looks odd
	DELETE_CHAR catalina
   //	SET_OBJECT_HEADING object_door_cat4 175.0
  	flag_has_otb_door_been_open = 0

RETURN

   

// **************************************** mission cat4 passed ************************

mission_passed_cat4:
	
	//REGISTER_MISSION_PASSED ( CAT_1 ) //Used in the stats 
	PRINT_WITH_NUMBER_BIG ( M_PASS ) 2000 5000 1 //"Mission Passed!"
	ADD_SCORE player1 2000
	CLEAR_WANTED_LEVEL player1
	PLAY_MISSION_PASSED_TUNE 1
	cat_counter ++ //Used to terminate this mission loop in the main script. These varibles will be set up in the main.sc
	//REMOVE_BLIP cat_contact_blip
	++ cat_getaway_dialogue
	REGISTER_MISSION_PASSED ( CAT_4 )
	PLAYER_MADE_PROGRESS 1 

	 
   //	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME CAT

   	IF cat_liquor_banter = 1
		cat_liquor_banter = 2
	ENDIF	
	  

	flag_cat_mission4_passed = 1 

RETURN
		


// ********************************** mission cleanup ***********************************

mission_cleanup_cat4:
 //	DO_FADE 0 FADE_IN
	CLEAR_HELP
	flag_player_on_mission = 0
	DISPLAY_RADAR ON
	DISABLE_ALL_ENTRY_EXITS FALSE
	 SWITCH_ENTRY_EXIT genOTB TRUE
	IF IS_PLAYER_PLAYING player1 
	 	SHUT_PLAYER_UP player1 FALSE
	ENDIF
	IF NOT IS_CHAR_DEAD catalina 
   		SET_CHAR_PROOFS catalina FALSE FALSE FALSE FALSE FALSE
	ENDIF

	IF NOT IS_CAR_DEAD players_car_cat4 
		FREEZE_CAR_POSITION players_car_cat4 FALSE
		SET_CAR_PROOFS players_car_cat4 FALSE FALSE FALSE FALSE FALSE
		SET_CAN_BURST_CAR_TYRES players_car_cat4 TRUE
	ENDIF						  

	REMOVE_CHAR_ELEGANTLY catalina


	index_cat4 = 4
	WHILE index_cat4 < 6
		MARK_CHAR_AS_NO_LONGER_NEEDED char_random_ped_cat4[index_cat4]
		index_cat4++
	ENDWHILE

	REMOVE_ANIMATION misc
	MARK_MODEL_AS_NO_LONGER_NEEDED csher						   
	MARK_MODEL_AS_NO_LONGER_NEEDED firetruk
	MARK_MODEL_AS_NO_LONGER_NEEDED copcarla
	MARK_MODEL_AS_NO_LONGER_NEEDED ambulan
	MARK_MODEL_AS_NO_LONGER_NEEDED lafd1
	MARK_MODEL_AS_NO_LONGER_NEEDED roadworkbarrier1
	MARK_MODEL_AS_NO_LONGER_NEEDED colt45
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
   //	MARK_MODEL_AS_NO_LONGER_NEEDED BOBCAT

	MARK_MODEL_AS_NO_LONGER_NEEDED copcarru
	MARK_MODEL_AS_NO_LONGER_NEEDED DESERT_EAGLE
   	MARK_MODEL_AS_NO_LONGER_NEEDED BOMB
  	MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL


	MARK_CAR_AS_NO_LONGER_NEEDED car_police1_cat4
	MARK_CAR_AS_NO_LONGER_NEEDED car_police2_cat4
	MARK_CAR_AS_NO_LONGER_NEEDED car_police3_cat4
	MARK_CAR_AS_NO_LONGER_NEEDED players_car_cat4

	MARK_CHAR_AS_NO_LONGER_NEEDED char_pol1_cat4
	MARK_CHAR_AS_NO_LONGER_NEEDED char_pol2_cat4
	MARK_CHAR_AS_NO_LONGER_NEEDED char_pol3_cat4


	MARK_OBJECT_AS_NO_LONGER_NEEDED  object_door_cat4 
	MARK_OBJECT_AS_NO_LONGER_NEEDED small_safe_cat4
	MARK_MODEL_AS_NO_LONGER_NEEDED rider1_door
	MARK_MODEL_AS_NO_LONGER_NEEDED KEV_SAFE
	MARK_MODEL_AS_NO_LONGER_NEEDED man_safenew
	MARK_CAR_AS_NO_LONGER_NEEDED players_car_cat4

  // 	CLEAR_MISSION_AUDIO 3






   
   	iSetOTBPanic = 0




	IF NOT IS_CHAR_DEAD catalina
		CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_COP	
		CLEAR_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_LIKE PEDTYPE_COP	

	ENDIF


  	REMOVE_ANIMATION bomber 
	flag_otb_robbing_peds_panic = 0

	GET_GAME_TIMER timer_mobile_start


	REMOVE_BLIP blip_otb_cat4
	REMOVE_BLIP blip_cat_cat4
	REMOVE_BLIP blip_getaway_car
	REMOVE_BLIP blip_hiding_spot
	REMOVE_BLIP blip_paint_and_spray_cat4


	ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 720.016 -454.625 15.328 RADAR_SPRITE_SPRAY spray_shop4 //Badlands Countryeast

   //	CLEAR_ONSCREEN_COUNTER counter_sprunk_detonate_cat4 

 //	SWITCH_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993	
   //	SWITCH_PED_ROADS_BACK_TO_ORIGINAL -5000.5276 -5000.4624 -1000.8299 5000.5276 5000.4624 1000.82993


	SET_CAR_DENSITY_MULTIPLIER 1.0
	SET_PED_DENSITY_MULTIPLIER 1.0








	REMOVE_DECISION_MAKER dm_cops_cat4
	REMOVE_DECISION_MAKER dm_catalina_cat4



	REMOVE_ALL_SCRIPT_FIRES
	//SWITCH_ROADS_BACK_TO_ORIGINAL 1140.9072 213.5046 1.7728 1585.8451 382.9843 43.8501


	MISSION_HAS_FINISHED	
	SET_EVERYONE_IGNORE_PLAYER Player1 OFF

  //	ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 833.0 9.0 10.0 FALSE   //  inside otb
  //	ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 1291.0 271.0 10.0 FALSE	   // outside



	SET_GROUP_SEPARATION_RANGE Players_Group 30.0

	// **************************************** Initialising variables  **********************************
	flag_holdup_cat4 = 1
	flag_getaway_cat4 = 0
	flag_mission_passed_cat4 = 0
	flag_mission_failed_cat4 = 0
	flag_robbery_cutscene_otb = 0
	flag_has_safe_been_robbed_cat4 = 0			// resets otb safe
	flag_cat_shoot_cops = 0
	flag_otb_robbing_peds_panic = 0
	flag_police_arrive = 0
	flag_create_car_cat_enters = 0
	flag_help_text_spary_cat4 = 0

	flag_display_help_cat4 = 0

	cat_coment_counter = 0


	flag_text_otb = 0
	flag_text_cat_police_killing_cat4 = 0

	flag_has_otb_door_been_open = 0

	player_in_otb_flag_cat4_local_var = 0
	flag_cutscene1_cat4 = 0

	flag_are_cops_pissed  = 0	

	flag_has_catalina_chocolate_rant_been_done = 0


	flag_has_cat_got_the_loot_cat4 = 0
	counter_robbery_cat4 = 0





RETURN







}