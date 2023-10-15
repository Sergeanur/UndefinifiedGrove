MISSION_START

{

// new proper menus

VAR_TEXT_LABEL $wardrobe_name // Used to display the name of the garment
											    
VAR_INT number_of_items_wardrobe

//VAR_TEXT_LABEL $stored_wardrobe_shop

VAR_INT player_in_view_mode_wardrobe

VAR_INT flag_player_use_changing_room_wardrobe

// used to create the changing room door to animate
VAR_INT changing_room_door_wardrobe

VAR_INT flag_wardrobe

VAR_FLOAT return_animation_time_wardrobe object_animation_time_wardrobe

VAR_INT flag_player_in_changing_room_wardrobe

VAR_TEXT_LABEL16 $name_of_anim_wardrobe[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]

VAR_TEXT_LABEL16 $current_name_of_anim_wardrobe

VAR_INT shop_progress_wardrobe // used to say what stage you are in in section 4 onwards
shop_progress_wardrobe = 0

VAR_INT shop_door_model_wardrobe


// menu stuff
// NEW MEMU STUFF
VAR_INT main_menu_drawn_wardrobe main_menu_wardrobe shop_main_item_picked_wardrobe
shop_main_item_picked_wardrobe = 0
main_menu_drawn_wardrobe = 0

VAR_INT number_of_wardrobe_in_shop number_of_wardrobe_in_area

number_of_wardrobe_in_shop = 0
number_of_wardrobe_in_area = 0
price_counter = 0
item_no_shops = 0
temp_var_shops = 0

// new stuff for script to work with all 6 shops
CONST_FLOAT wardrobe_door_trueX 213.874
CONST_FLOAT wardrobe_door_trueY -39.811
CONST_FLOAT wardrobe_door_trueZ 1002.2

wardrobe_door_heading = 0.0

// used for the offsets for all wardrobe shops
VAR_FLOAT wardrobe_offX wardrobe_offY wardrobe_offZ wardrobe_door_heading 
wardrobe_offX = 0.0
wardrobe_offY = 0.0
wardrobe_offZ = 0.0
wardrobe_door_heading = 0.0

// area for player to stand
CONST_FLOAT wardrobe_locate_trueX 214.622
CONST_FLOAT wardrobe_locate_trueY -40.652
CONST_FLOAT wardrobe_locate_trueZ 1001.033

VAR_FLOAT wardrobe_locateX wardrobe_locateY wardrobe_locateZ wardrobe_heading
wardrobe_locateX = 0.0
wardrobe_locateY = 0.0
wardrobe_locateZ = 0.0
wardrobe_heading = 0.0

// camera stuff
CONST_FLOAT wardrobe_cam_trueX 212.8715
CONST_FLOAT wardrobe_cam_trueY -42.8156
CONST_FLOAT wardrobe_cam_trueZ 1002.1159

VAR_FLOAT wardrobe_camX wardrobe_camY wardrobe_camZ
wardrobe_camX = 0.0
wardrobe_camY = 0.0
wardrobe_camZ = 0.0

CONST_FLOAT wardrobe_cam_point_trueX 213.4791  
CONST_FLOAT wardrobe_cam_point_trueY -42.0238
CONST_FLOAT wardrobe_cam_point_trueZ 1002.0531

VAR_FLOAT wardrobe_cam_pointX wardrobe_cam_pointY wardrobe_cam_pointZ
wardrobe_cam_pointX = 0.0 
wardrobe_cam_pointY = 0.0 
wardrobe_cam_pointZ = 0.0

// hat, chain, specs
CONST_FLOAT wardrobe_hat_cam_trueX 214.5148    
CONST_FLOAT wardrobe_hat_cam_trueY -41.6106
CONST_FLOAT wardrobe_hat_cam_trueZ 1002.4937

VAR_FLOAT wardrobe_hat_camX wardrobe_hat_camY wardrobe_hat_camZ
wardrobe_hat_camX = 0.0
wardrobe_hat_camY = 0.0
wardrobe_hat_camZ = 0.0

CONST_FLOAT wardrobe_hat_cam_point_trueX 214.5367      
CONST_FLOAT wardrobe_hat_cam_point_trueY -40.6175
CONST_FLOAT wardrobe_hat_cam_point_trueZ 1002.6089

VAR_FLOAT wardrobe_hat_cam_pointX wardrobe_hat_cam_pointY wardrobe_hat_cam_pointZ
wardrobe_hat_cam_pointX = 0.0 
wardrobe_hat_cam_pointY = 0.0 
wardrobe_hat_cam_pointZ = 0.0

number_of_items_wardrobe = 0

flag_player_in_changing_room_wardrobe = 0

wardrobe_heading = 0.0

wardrobe_cam_pointX = 0.0
wardrobe_cam_pointY = 0.0
wardrobe_cam_pointZ = 0.0

return_animation_time_wardrobe = 0.0
object_animation_time_wardrobe = 0.0

flag_wardrobe = 0
flag_player_use_changing_room_wardrobe = 0  
player_in_view_mode_wardrobe = 0

main_menu_drawn_wardrobe = 0
cost_menu_drawn_shops = 0
bought_menu_drawn_shops = 0

flag_bought_item_already_shops = 0
flag_player_owned_item_shops = 0

area_to_look_at_shops = 0

VAR_INT visible_area_wardrobe

VAR_INT row_number_wardrobe

VAR_TEXT_LABEL $menu_item[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS] $shop_section[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]

VAR_INT number_of_torso_wardrobe number_of_legs_wardrobe number_of_feet_wardrobe number_of_chains_wardrobe
VAR_INT number_of_watches_wardrobe number_of_glasses_wardrobe number_of_hats_wardrobe number_of_special_wardrobe

number_of_torso_wardrobe = 0 
number_of_legs_wardrobe = 0 
number_of_feet_wardrobe = 0 
number_of_chains_wardrobe = 0
number_of_watches_wardrobe = 0
number_of_glasses_wardrobe = 0
number_of_hats_wardrobe = 0
number_of_special_wardrobe = 0

VAR_INT body_part_wardrobe[MAX_NUMBER_ALLOWED_IN_MENU_SHOPS]

// second menu stuff
VAR_INT second_menu_drawn_wardrobe second_menu_wardrobe second_menu_item_picked_wardrobe
second_menu_item_picked_wardrobe = 0
second_menu_drawn_wardrobe = 0

// Third menu stuff
VAR_INT number_bought_items_in_area
number_bought_items_in_area = 0

VAR_INT third_menu_wardrobe third_menu_drawn_wardrobe third_menu_item_picked_wardrobe
third_menu_drawn_wardrobe = 0
third_menu_item_picked_wardrobe = 0

// used for menu stuff
VAR_INT flag_got_special_clothes_wardrobe
flag_got_special_clothes_wardrobe = 0

VAR_INT flag_remove_menu_drawn_wardrobe remove_menu_wardrobe
flag_remove_menu_drawn_wardrobe = 0

VAR_INT draw_remove_option_wardrobe
draw_remove_option_wardrobe = 0

VAR_INT player_in_changeroom_wardrobe
player_in_changeroom_wardrobe = 0

VAR_INT player_using_wardrobe
player_using_wardrobe = 0

VAR_INT flag_bought_something_wardrobe
flag_bought_something_wardrobe = 0

// requesting models
shop_door_model_wardrobe = CJ_BINCO_DOOR

REQUEST_MODEL shop_door_model_wardrobe

REQUEST_ANIMATION CLOTHES

LOAD_MISSION_AUDIO 4 SOUND_CLOTHES_DRESSING_WARDROBE 
	
LOAD_ALL_MODELS_NOW
// end of requesting models


SET_DEATHARREST_STATE OFF

SCRIPT_NAME	warrobe

shop_wardrobe_inner:

	WAIT 0
	
	IF IS_PLAYER_PLAYING player1

	GET_AREA_VISIBLE visible_area_wardrobe

		IF visible_area_wardrobe = 14

			IF flag_wardrobe > 0

				IF HAS_LANGUAGE_CHANGED
					CLEAR_HELP
					DELETE_MENU main_menu_wardrobe
					DELETE_MENU second_menu_wardrobe
					DELETE_MENU third_menu_wardrobe
					DELETE_MENU remove_menu_wardrobe
					DELETE_MENU cost_menu_shops
					DELETE_MENU bought_menu_shops
					main_menu_drawn_wardrobe = 0
					second_menu_drawn_wardrobe = 0
					third_menu_drawn_wardrobe = 0
					flag_remove_menu_drawn_wardrobe = 0    
					cost_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0
					GET_CURRENT_LANGUAGE current_Language
				ENDIF

			ENDIF
	
			IF flag_wardrobe = 0

				player_using_wardrobe = 1 

				wardrobe_offX = 43.529
				wardrobe_offY = 0.694
				wardrobe_offZ = 0.012
									
				wardrobe_heading = 90.0 
				wardrobe_door_heading = 0.0 
					
//				$stored_wardrobe_shop = $shop_name 
					
				flag_wardrobe = 1											

			ENDIF // flag_wardrobe  = 0

			IF flag_wardrobe = 1
				
				IF shop_progress_wardrobe = 0 		

					USE_TEXT_COMMANDS TRUE

					SHOW_UPDATE_STATS FALSE

					wardrobe_camX = wardrobe_cam_trueX + wardrobe_offX     
					wardrobe_camY =	wardrobe_cam_trueY + wardrobe_offY
					wardrobe_camZ = wardrobe_cam_trueZ + wardrobe_offZ 

					wardrobe_cam_pointX = wardrobe_cam_point_trueX + wardrobe_offX
					wardrobe_cam_pointY = wardrobe_cam_point_trueY + wardrobe_offY 
					wardrobe_cam_pointZ = wardrobe_cam_point_trueZ + wardrobe_offZ

					wardrobe_hat_camX = wardrobe_hat_cam_trueX + wardrobe_offX 
 					wardrobe_hat_camY = wardrobe_hat_cam_trueY + wardrobe_offY
					wardrobe_hat_camZ = wardrobe_hat_cam_trueZ + wardrobe_offZ 
										
					wardrobe_hat_cam_pointX = wardrobe_hat_cam_point_trueX + wardrobe_offX 
 					wardrobe_hat_cam_pointY = wardrobe_hat_cam_point_trueY + wardrobe_offY
					wardrobe_hat_cam_pointZ = wardrobe_hat_cam_point_trueZ + wardrobe_offZ 

					wardrobe_locateX = wardrobe_locate_trueX + wardrobe_offX
					wardrobe_locateY = wardrobe_locate_trueY + wardrobe_offY 
					wardrobe_locateZ = wardrobe_locate_trueZ + wardrobe_offZ

					change_doorX = wardrobe_door_trueX + wardrobe_offX 
					change_doorY = wardrobe_door_trueY + wardrobe_offY
					change_doorZ = wardrobe_door_trueZ + wardrobe_offZ
													
					CREATE_OBJECT_NO_OFFSET shop_door_model_wardrobe change_doorX change_doorY change_doorZ changing_room_door_wardrobe
					SET_OBJECT_HEADING changing_room_door_wardrobe wardrobe_door_heading 

					blob_flag_shop = 1

					shop_progress_wardrobe = 1

				ENDIF

				IF shop_progress_wardrobe = 1

					IF HAS_ANIMATION_LOADED CLOTHES
						shop_progress_wardrobe = 0
						flag_wardrobe = 2
					ENDIF

				ENDIF
				
			ENDIF // flag_wardrobe = 1										

			IF flag_wardrobe = 2
									
				IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer wardrobe_locateX wardrobe_locateY wardrobe_locateZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop

					IF CAN_PLAYER_START_MISSION player1
						flag_wardrobe = 3
					ENDIF

				ENDIF 
				
			ENDIF // flag_wardrobe = 2

			IF flag_wardrobe = 3

				IF  IS_PLAYER_PLAYING player1
					SET_PLAYER_CONTROL player1 OFF
					SET_MINIGAME_IN_PROGRESS TRUE
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
					GET_CHAR_COORDINATES scplayer player_x player_y player_z
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_AREA player_x player_y player_z 1.0 TRUE
					DISPLAY_RADAR FALSE
					TIMERA = 0
					shop_progress_wardrobe = 0
					flag_wardrobe = 4
				ELSE
					GOSUB wardrobe_cleanup_big
					GOTO shop_wardrobe_inner
				ENDIF 

			ENDIF // flag_wardrobe = 3

			// ***************** SETTING UP CAMERA AND CHECK PLAYER POSITION *********************
			IF flag_wardrobe = 4

				IF shop_progress_wardrobe = 0 
 				
					IF TIMERA >= 300																		
						 
						SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
														   
						SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer wardrobe_locateX wardrobe_locateY wardrobe_locateZ 
						SET_CHAR_HEADING scplayer wardrobe_heading
						GET_CHAR_COORDINATES scplayer player_x player_y player_z
							  																																																							
						SET_FIXED_CAMERA_POSITION wardrobe_camX wardrobe_camY wardrobe_camZ 0.0 0.0 0.0 			
						POINT_CAMERA_AT_POINT wardrobe_cam_pointX wardrobe_cam_pointY wardrobe_cam_pointZ JUMP_CUT
						   	   							
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_In CLOTHES 4.0 FALSE FALSE FALSE TRUE -1
	  					return_animation_time_wardrobe = 0.0

						IF IS_CHAR_PLAYING_ANIM scplayer CLO_In
							GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_In return_animation_time_wardrobe
						ENDIF

						IF DOES_OBJECT_EXIST changing_room_door_wardrobe
							PLAY_OBJECT_ANIM changing_room_door_wardrobe CLO_Pose_Out_O CLOTHES 4.0 FALSE TRUE
						ENDIF

						shop_progress_wardrobe = 1

					ENDIF

				ENDIF
				   	
			   	//	Waiting for walking into changing room anim to finish				
				IF shop_progress_wardrobe = 1	

					IF IS_CHAR_PLAYING_ANIM scplayer CLO_In
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_In return_animation_time_wardrobe
					ENDIF
				
				    IF return_animation_time_wardrobe = 1.0
				    	STORE_CLOTHES_STATE						   	  																	   									
						shop_progress_wardrobe = 0
						flag_wardrobe = 5
					ENDIF
					
				ENDIF

			ENDIF // flag_wardrobe = 4
			
			// ************************************* FIRST MENU ***********************************
			// player to select view and buy wardrobe					
			IF flag_wardrobe = 5		

				IF main_menu_drawn_wardrobe = 0

					IF shop_progress_wardrobe < 1
					  GOSUB wardrobe_main_menu
					ENDIF

				ENDIF
				
				blob_flag_shop = 0

				IF shop_progress_wardrobe = 0
					
					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						shop_progress_wardrobe = 1
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						shop_progress_wardrobe = 2
					ENDIF

				ENDIF				
					
				// ******* PLAYER HAS PRESSED CROSS IN FIRST MENU TO SELECT SHOP ***********
				IF shop_progress_wardrobe = 1
					
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						
						GET_MENU_ITEM_ACCEPTED main_menu_wardrobe shop_main_item_picked_wardrobe
					    
						IF shop_main_item_picked_wardrobe < 0
							shop_main_item_picked_wardrobe = 0
						ENDIF

						IF main_menu_drawn_wardrobe = 1
							DELETE_MENU main_menu_wardrobe
							CLEAR_HELP
							main_menu_drawn_wardrobe = 0
						ENDIF
												
						IF $shop_section[shop_main_item_picked_wardrobe] = remove
						OR $shop_section[shop_main_item_picked_wardrobe] = uniform

							$current_name_of_anim_wardrobe = CLO_Pose_Torso

							shop_progress_wardrobe = 0
							flag_wardrobe = 7

						ELSE
					
							GOSUB fill_in_second_menu_wardrobe
												
							IF second_menu_drawn_wardrobe = 0  
								GOSUB draw_second_menu_wardrobe
							ENDIF

							shop_progress_wardrobe = 0

							flag_wardrobe = 6
							
						ENDIF				 
					ENDIF
				ENDIF

			// ******************** PLAYER HAS PRESSED TRIANGLE IN FIRST MENU *****************
				IF shop_progress_wardrobe = 2
					
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

						IF main_menu_drawn_wardrobe = 1
							DELETE_MENU main_menu_wardrobe
							CLEAR_HELP
							main_menu_drawn_wardrobe = 0
						ENDIF

						IF second_menu_drawn_wardrobe = 1
							DELETE_MENU second_menu_wardrobe
							CLEAR_HELP
							second_menu_drawn_wardrobe = 0
						ENDIF
						 
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Out CLOTHES 1000.0 FALSE FALSE FALSE FALSE -1
						return_animation_time_wardrobe = 0.0

						IF DOES_OBJECT_EXIST changing_room_door_wardrobe
							PLAY_OBJECT_ANIM changing_room_door_wardrobe CLO_Pose_In_O CLOTHES 1000.0 FALSE TRUE
						ENDIF

						shop_progress_wardrobe = 5
		   				
    	   			ENDIF
					   
				ENDIF

				// ******************* WAITING FOR WALK AWAY ANIM TO FINISH ***********************
				IF shop_progress_wardrobe = 5

					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Out
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Out return_animation_time_wardrobe
					ENDIF

					// Player walking out of changing room
					IF return_animation_time_wardrobe = 1.0
						shop_progress_wardrobe = 6
					ENDIF
				ENDIF

				// * FINISHED QUITTING OUT OF FIRST MENU - HAVE TO WAIT FOR PLAYER TO LEAVE AREA *
				IF shop_progress_wardrobe = 6	
					SET_PLAYER_CONTROL player1 ON
					SET_MINIGAME_IN_PROGRESS FALSE
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT

					DISPLAY_RADAR TRUE

					player_in_changeroom_wardrobe = 0

					main_menu_drawn_wardrobe = 0
					second_menu_drawn_wardrobe = 0
					third_menu_drawn_wardrobe = 0

					flag_wardrobe = 11
				ENDIF

			ENDIF // flag_wardrobe = 5
			
			// ********************************	SECOND MENU ***********************************
			// ************ Player chooses the area to look at ********************************
			IF flag_wardrobe = 6

				IF second_menu_drawn_wardrobe = 0
					GOSUB fill_in_second_menu_wardrobe  
					GOSUB draw_second_menu_wardrobe
				ENDIF

				blob_flag_shop = 0
																																		
				IF shop_progress_wardrobe = 0

					// quit to menu 1						
					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						shop_progress_wardrobe = 1
					ENDIF

					// choose area
				   	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

						GET_MENU_ITEM_SELECTED second_menu_wardrobe second_menu_item_picked_wardrobe
					    
						IF second_menu_item_picked_wardrobe < 0
							second_menu_item_picked_wardrobe = 0
						ENDIF					
						
						$current_name_of_anim_wardrobe = $name_of_anim_wardrobe[second_menu_item_picked_wardrobe]
						GOSUB fill_third_menu_wardrobe

						shop_progress_wardrobe = 2
					ENDIF

				ENDIF
				
				// **************************** QUIT TO MENU 1 ************************************
				//	Player has pressed triangle in second menu							
				IF shop_progress_wardrobe = 1	
						
				   	IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
					  	shop_progress_wardrobe = 0
						flag_wardrobe = 5

						IF second_menu_drawn_wardrobe = 1
							CLEAR_HELP
							DELETE_MENU second_menu_wardrobe
							second_menu_drawn_wardrobe = 0
						ENDIF

						IF main_menu_drawn_wardrobe = 0
							GOSUB wardrobe_main_menu 
						ENDIF		

				   	ENDIF // triangle pressed

				ENDIF

				// **************** PLAYER IS NOW READY TO CHOOSE ITEM TO TRY ON *******************

				IF shop_progress_wardrobe = 2

					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						IF second_menu_drawn_wardrobe = 1
							CLEAR_HELP
							DELETE_MENU second_menu_wardrobe
							second_menu_drawn_wardrobe = 0
						ENDIF

						IF third_menu_drawn_wardrobe = 0
							GOSUB draw_third_menu_wardrobe
						ENDIF

						shop_progress_wardrobe = 0											 
						flag_wardrobe = 7
					ENDIF

				ENDIF

			ENDIF // end flag_wardrobe = 6

				// ************ PLAYER IN MENU 3 TO CHOOSE WHICH ITEM TO TRY ON *******************
			IF flag_wardrobe = 7

				IF $shop_section[shop_main_item_picked_wardrobe] = remove

					IF flag_remove_menu_drawn_wardrobe = 0  
						GOSUB draw_remove_menu_wardrobe
						 
						IF row_number_wardrobe = 0

							IF flag_remove_menu_drawn_wardrobe = 1
								DELETE_MENU remove_menu_wardrobe
								CLEAR_HELP 
								flag_remove_menu_drawn_wardrobe = 0
							ENDIF

							shop_progress_wardrobe = 0
							flag_wardrobe = 5

							GOTO shop_wardrobe_inner

						ENDIF

					ENDIF
				
				ELSE 

					IF $shop_section[shop_main_item_picked_wardrobe] = uniform

						IF third_menu_drawn_wardrobe = 0 
							GOSUB fill_in_special_menu_wardrobe
							GOSUB draw_third_menu_wardrobe
						ENDIF

					ELSE

						IF third_menu_drawn_wardrobe = 0 
							GOSUB fill_third_menu_wardrobe 
							GOSUB draw_third_menu_wardrobe
						ENDIF

					ENDIF

				ENDIF

				IF shop_progress_wardrobe = 0

					// quit to menu 2						
					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						shop_progress_wardrobe = 1
					ENDIF

					// choose area
				   	IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

						GET_MENU_ITEM_SELECTED third_menu_wardrobe third_menu_item_picked_wardrobe
					    
						IF third_menu_item_picked_wardrobe < 0
							third_menu_item_picked_wardrobe = 0
						ENDIF
						 							
						shop_progress_wardrobe = 2
					ENDIF

				ENDIF

				IF shop_progress_wardrobe = 1
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						IF third_menu_drawn_wardrobe = 1
							DELETE_MENU third_menu_wardrobe
							CLEAR_HELP
							third_menu_drawn_wardrobe = 0
						ENDIF
						
						IF flag_remove_menu_drawn_wardrobe = 1
							DELETE_MENU remove_menu_wardrobe
							CLEAR_HELP 
							flag_remove_menu_drawn_wardrobe = 0
						ENDIF 

						IF $shop_section[shop_main_item_picked_wardrobe] = uniform
						OR $shop_section[shop_main_item_picked_wardrobe] = remove

							IF main_menu_drawn_wardrobe = 0
								GOSUB wardrobe_main_menu 
							ENDIF	

							flag_wardrobe = 5
							shop_progress_wardrobe = 0

						ELSE
						
							IF second_menu_drawn_wardrobe = 0
								GOSUB fill_in_second_menu_wardrobe 
								GOSUB draw_second_menu_wardrobe
							ENDIF

							shop_progress_wardrobe = 0
							flag_wardrobe = 6
						ENDIF
						
					ENDIF
					
				ENDIF
				
				IF shop_progress_wardrobe = 2
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

						IF third_menu_drawn_wardrobe = 1
							DELETE_MENU third_menu_wardrobe
							CLEAR_HELP
							third_menu_drawn_wardrobe = 0
						ENDIF
						
						IF flag_remove_menu_drawn_wardrobe = 1
							DELETE_MENU remove_menu_wardrobe
							CLEAR_HELP 
							flag_remove_menu_drawn_wardrobe = 0
						ENDIF 

						player_in_changeroom_wardrobe = 0
					
						shop_progress_wardrobe = 0
						flag_wardrobe = 8
					
					ENDIF
					
				ENDIF  
									
			ENDIF // flag_wardrobe = 7
			
			// **************** PLAYER HAS SELECTED AT ITEM TO VIEW ******************************* 		
			IF flag_wardrobe = 8
			  				
				IF shop_progress_wardrobe = 0

					IF $shop_section[shop_main_item_picked_wardrobe] = remove
					
						model_index = 0 						
						modelid_shops = 0
				 		component_shops = item_component[third_menu_item_picked_wardrobe]   
						$wardrobe_name = $menu_item[third_menu_item_picked_wardrobe]

						GET_CLOTHES_ITEM player1 component_shops player_item_texture_shops player_item_model_shops
					
					ELSE							 

						model_index = item_model_index[third_menu_item_picked_wardrobe] 						
						modelid_shops = item_modelid[third_menu_item_picked_wardrobe]
				 		component_shops = area_to_look_at_shops // item_component[third_menu_item_picked_wardrobe]   
						$wardrobe_name = $menu_item[third_menu_item_picked_wardrobe]
						
					ENDIF

//					component_shops

					IF component_shops = CLOTHES_TEX_NECKLACE
					OR component_shops = CLOTHES_TEX_WATCH
					OR component_shops = CLOTHES_TEX_GLASSES
					OR component_shops = CLOTHES_TEX_HAT
						SET_FIXED_CAMERA_POSITION wardrobe_hat_camX wardrobe_hat_camY wardrobe_hat_camZ 0.0 0.0 0.0 			
						POINT_CAMERA_AT_POINT wardrobe_hat_cam_pointX wardrobe_hat_cam_pointY wardrobe_hat_cam_pointZ JUMP_CUT
					ELSE
						SET_FIXED_CAMERA_POSITION wardrobe_camX wardrobe_camY wardrobe_camZ 0.0 0.0 0.0 			
						POINT_CAMERA_AT_POINT wardrobe_cam_pointX wardrobe_cam_pointY wardrobe_cam_pointZ JUMP_CUT

						IF NOT player_item_texture_shops = model_index
							PLAY_MISSION_AUDIO 4
						ENDIF

					ENDIF
					
					IF component_shops = CLOTHES_TEX_SHIRT
					OR component_shops = CLOTHES_TEX_TROUSERS
					OR component_shops = CLOTHES_TEX_SHOES
						GIVE_PLAYER_CLOTHES player1 0 0 17
						STORE_CLOTHES_STATE
					ENDIF							
																					
		 			GIVE_PLAYER_CLOTHES player1 model_index modelid_shops component_shops
					
		   			BUILD_PLAYER_MODEL player1

					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_In CLOTHES 1000.0 FALSE FALSE FALSE TRUE -1
					return_animation_time_wardrobe = 0.0

					IF DOES_OBJECT_EXIST changing_room_door_wardrobe
						PLAY_OBJECT_ANIM changing_room_door_wardrobe CLO_Pose_In_O CLOTHES 1000.0 FALSE TRUE
					ENDIF	
				
					shop_progress_wardrobe = 1
				
				ENDIF
				
				IF shop_progress_wardrobe = 1

					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Pose_In
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Pose_In return_animation_time_wardrobe
					ENDIF
	   
					// ********* PLAYER WALKING OUT OF CHANGING ROOM TO VIEW HIS PURCHASES ********
					IF return_animation_time_wardrobe = 1.0

						IF NOT $shop_section[shop_main_item_picked_wardrobe] = remove 
							TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $current_name_of_anim_wardrobe CLOTHES 1000.0 FALSE FALSE FALSE TRUE -1 // CLO_Pose_Torso
							return_animation_time_wardrobe = 0.0						
							shop_progress_wardrobe = 2
						ELSE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Loop CLOTHES 1000.0 TRUE FALSE FALSE FALSE -1
							return_animation_time_wardrobe = 0.0
							flag_wardrobe = 9
							shop_progress_wardrobe = 0
						ENDIF

					ENDIF

				ENDIF
				
				IF shop_progress_wardrobe = 2
				 
					IF IS_CHAR_PLAYING_ANIM scplayer $current_name_of_anim_wardrobe
						GET_CHAR_ANIM_CURRENT_TIME scplayer $current_name_of_anim_wardrobe return_animation_time_wardrobe
					ENDIF

					IF return_animation_time_wardrobe = 1.0

						GOSUB display_price_name_text_wardrobe
						
						// player in pose loop
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Loop CLOTHES 1000.0 TRUE FALSE FALSE FALSE -1
						return_animation_time_wardrobe = 0.0
						flag_wardrobe = 9
						shop_progress_wardrobe = 0
					ENDIF

				ENDIF
    						
			ENDIF // flag wardrobe = 8

			// ******************** DISPLAY PRICE OF CURRENT CLOTHES SELECTION ********************
			IF flag_wardrobe = 9

				blob_flag_shop = 0
				
				IF shop_progress_wardrobe = 0

					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						CLEAR_THIS_PRINT (WARDNO)
						CLEAR_THIS_PRINT (WARDNO2)
						flag_bought_item_already_shops = 0		
						flag_player_owned_item_shops = 0
						flag_bought_something_wardrobe = 0
						shop_progress_wardrobe = 1
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						CLEAR_THIS_PRINT (WARDNO)
						CLEAR_THIS_PRINT (WARDNO2)
						flag_bought_item_already_shops = 0
						flag_player_owned_item_shops = 0
						flag_bought_something_wardrobe = 0

//						GOSUB fill_arrays_for_wardrobe_info // need to recalulate the menu's again for hilight

						IF cost_menu_drawn_shops = 1
							DELETE_MENU cost_menu_shops
							CLEAR_HELP
							cost_menu_drawn_shops = 0
						ENDIF

						IF player_in_changeroom_wardrobe = 0
						
							shop_progress_wardrobe = 3

						ELSE

							IF bought_menu_drawn_shops = 1
								DELETE_MENU bought_menu_shops
								bought_menu_drawn_shops = 0
							ENDIF
						
							shop_progress_wardrobe = 0
							flag_wardrobe = 7
						ENDIF
					ENDIF
				ENDIF

				IF shop_progress_wardrobe < 2 
					GOSUB display_price_name_text_wardrobe
				ENDIF
					
				// ************************** PLAYER HAS PRESSED CROSS TO BUY ITEM ****************		
				IF shop_progress_wardrobe = 1
						
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						IF $shop_section[shop_main_item_picked_wardrobe] = remove
								
							IF NOT player_item_texture_shops = 0

								IF cost_menu_drawn_shops = 1
									DELETE_MENU cost_menu_shops
									CLEAR_HELP
									cost_menu_drawn_shops = 0
								ENDIF	

								REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY
										
								GOSUB bought_text_wardrobe

								flag_bought_something_wardrobe = 1
								STORE_CLOTHES_STATE
								BUILD_PLAYER_MODEL player1

								GET_CLOTHES_ITEM player1 component_shops player_item_texture_shops player_item_model_shops

								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Buy CLOTHES 4.0 FALSE FALSE FALSE TRUE -1
								return_animation_time_wardrobe = 0.0						

								shop_progress_wardrobe = 2
															
							ELSE

								IF flag_bought_item_already_shops = 0
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
									PRINT_NOW (WARDNO2) 5000 1 //"You have already bought this item!"
									shop_progress_wardrobe = 0
									flag_bought_item_already_shops = 1
								ENDIF
							
							
							ENDIF 
						
						ELSE				
						   
							IF NOT player_item_texture_shops = model_index 

								IF cost_menu_drawn_shops = 1
									DELETE_MENU cost_menu_shops
									CLEAR_HELP
									cost_menu_drawn_shops = 0
								ENDIF
								
								ADD_PRICE_MODIFIER model_index 0
															 
								BUY_ITEM model_index
								flag_bought_something_wardrobe = 1
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY

								REMOVE_PRICE_MODIFIER model_index // $wardrobe_name
										
								GOSUB bought_text_wardrobe

								STORE_CLOTHES_STATE
								BUILD_PLAYER_MODEL player1

								GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops
								
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Buy CLOTHES 4.0 FALSE FALSE FALSE TRUE -1
								return_animation_time_wardrobe = 0.0						

								shop_progress_wardrobe = 2

							ELSE
										
								IF flag_bought_item_already_shops = 0
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
									PRINT_NOW (WARDNO) 5000 1 //"You have already bought this item!"
									shop_progress_wardrobe = 0
									flag_bought_item_already_shops = 1
								ENDIF

							ENDIF

						ENDIF

					ENDIF

				ENDIF

				// ******************** WAITING FOR BUY ANIMATION TO FINISH ***********************
				IF shop_progress_wardrobe = 2	
					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Buy
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Buy return_animation_time_wardrobe
					ENDIF
					
					IF return_animation_time_wardrobe = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Loop CLOTHES 1000.0 TRUE FALSE FALSE FALSE -1
						return_animation_time_wardrobe = 0.0
						shop_progress_wardrobe = 3																						
					ENDIF

				ENDIF

				// ******************* PLAYER HAS PRESSED TRIANGLE TO QUIT TO MENU 2 **************
				IF shop_progress_wardrobe = 3		

					// player in view mode then decides to change clothes

					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Out CLOTHES 4.0 FALSE FALSE FALSE TRUE -1
					return_animation_time_wardrobe = 0.0						

					IF DOES_OBJECT_EXIST changing_room_door_wardrobe
						PLAY_OBJECT_ANIM changing_room_door_wardrobe CLO_Pose_Out_O CLOTHES 1000.0 FALSE TRUE
					ENDIF

					shop_progress_wardrobe = 4
					
				ENDIF

				IF shop_progress_wardrobe = 4	//	Waiting for anim of walking back into changing room to finish

   					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Pose_Out
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Pose_Out return_animation_time_wardrobe
					ENDIF

					// player walks back into changing room
					IF return_animation_time_wardrobe = 1.0																				
						RESTORE_CLOTHES_STATE
						BUILD_PLAYER_MODEL player1
						STORE_CLOTHES_STATE
						
						IF cost_menu_drawn_shops = 1
							DELETE_MENU cost_menu_shops
							CLEAR_HELP
							cost_menu_drawn_shops = 0
						ENDIF
						
						IF bought_menu_drawn_shops = 1
							DELETE_MENU bought_menu_shops
							bought_menu_drawn_shops = 0
						ENDIF									
											
						player_in_changeroom_wardrobe = 1
											  
						IF flag_bought_something_wardrobe = 1
							shop_progress_wardrobe = 0
							flag_wardrobe = 5

							IF main_menu_drawn_wardrobe = 0

								IF shop_progress_wardrobe < 1
								  GOSUB wardrobe_main_menu
								ENDIF

							ENDIF

						ELSE
							
							IF $shop_section[shop_main_item_picked_wardrobe] = remove

								IF flag_remove_menu_drawn_wardrobe = 0  
									GOSUB draw_remove_menu_wardrobe
	
									IF row_number_wardrobe = 0
	
										IF flag_remove_menu_drawn_wardrobe = 1
											DELETE_MENU remove_menu_wardrobe
											CLEAR_HELP 
											flag_remove_menu_drawn_wardrobe = 0
										ENDIF
	
										shop_progress_wardrobe = 0
										flag_wardrobe = 5
	
										IF main_menu_drawn_wardrobe = 0
	
											IF shop_progress_wardrobe < 1
											  GOSUB wardrobe_main_menu
											ENDIF
	
										ENDIF
	
										GOTO shop_wardrobe_inner
	
									ELSE

										shop_progress_wardrobe = 0
										flag_wardrobe = 7

									ENDIF
								
								ENDIF
							
							ELSE 

								IF $shop_section[shop_main_item_picked_wardrobe] = uniform
	
									IF third_menu_drawn_wardrobe = 0 
										GOSUB fill_in_special_menu_wardrobe
										GOSUB draw_third_menu_wardrobe
									ENDIF
	
									shop_progress_wardrobe = 0
									flag_wardrobe = 7
	
								ELSE
	
									IF third_menu_drawn_wardrobe = 0 
										GOSUB fill_third_menu_wardrobe 
										GOSUB draw_third_menu_wardrobe
									ENDIF
	
									shop_progress_wardrobe = 0
									flag_wardrobe = 7
	
								ENDIF
	
							ENDIF
							
						ENDIF
																	   
					ENDIF

				ENDIF

			ENDIF	// flag_wardrobe = 7

			IF flag_wardrobe = 11

				IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer wardrobe_locateX wardrobe_locateY wardrobe_locateZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop

					main_menu_drawn_wardrobe = 0
					second_menu_drawn_wardrobe = 0
					third_menu_drawn_wardrobe = 0
					cost_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0
					flag_remove_menu_drawn_wardrobe = 0
					shop_main_item_picked_wardrobe = 0
					shop_sub_item_picked_shops = 0
					flag_bought_something_wardrobe = 0
																				
					flag_wardrobe = 2
					shop_progress_wardrobe = 0
					flag_bought_item_already_shops = 0

					blob_flag_shop = 1
						
				ENDIF

			ENDIF // flag_wardrobe = 11

		ELSE
			GOSUB wardrobe_cleanup_big		
		ENDIF // visible area = 13	
		
	ELSE
		GOSUB wardrobe_cleanup_big
	ENDIF // player playing							
								
GOTO shop_wardrobe_inner   		 


wardrobe_cleanup_small:

	IF flag_wardrobe >= 1
		CLEAR_HELP	
	ENDIF

	IF main_menu_drawn_wardrobe = 1
		DELETE_MENU main_menu_wardrobe
		CLEAR_HELP
		main_menu_drawn_wardrobe = 0
	ENDIF

	IF second_menu_drawn_wardrobe = 1
		DELETE_MENU second_menu_wardrobe
		CLEAR_HELP
		second_menu_drawn_wardrobe = 0
	ENDIF

	IF third_menu_drawn_wardrobe = 1
		DELETE_MENU third_menu_wardrobe
		CLEAR_HELP
		third_menu_drawn_wardrobe = 0
	ENDIF

	IF flag_remove_menu_drawn_wardrobe = 1
		DELETE_MENU remove_menu_wardrobe
		CLEAR_HELP 
		flag_remove_menu_drawn_wardrobe = 0
	ENDIF

	IF cost_menu_drawn_shops = 1
		DELETE_MENU cost_menu_shops
		CLEAR_HELP 
		cost_menu_drawn_shops = 0
	ENDIF

	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops
		CLEAR_HELP 
		bought_menu_drawn_shops = 0
	ENDIF

	CLEAR_THIS_PRINT (WARDNO)
	CLEAR_THIS_PRINT (WARDNO2)
	
	main_menu_drawn_wardrobe = 0
	second_menu_drawn_wardrobe = 0
	third_menu_drawn_wardrobe = 0
	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0
	flag_remove_menu_drawn_wardrobe = 0
	shop_main_item_picked_wardrobe = 0
	shop_sub_item_picked_shops = 0

	flag_bought_item_already_shops = 0
	flag_player_owned_item_shops = 0
	flag_bought_something_wardrobe = 0

	shop_progress_wardrobe = 0

	player_in_changeroom_wardrobe = 0

	flag_wardrobe = 2
   
RETURN

wardrobe_cleanup_big:

	IF main_menu_drawn_wardrobe = 1
		DELETE_MENU main_menu_wardrobe
		CLEAR_HELP
		main_menu_drawn_wardrobe = 0
	ENDIF

	IF second_menu_drawn_wardrobe = 1
		DELETE_MENU second_menu_wardrobe
		CLEAR_HELP
		second_menu_drawn_wardrobe = 0
	ENDIF

	IF third_menu_drawn_wardrobe = 1
		DELETE_MENU third_menu_wardrobe
		CLEAR_HELP
		third_menu_drawn_wardrobe = 0
	ENDIF

	IF flag_remove_menu_drawn_wardrobe = 1
		DELETE_MENU remove_menu_wardrobe
		CLEAR_HELP 
		flag_remove_menu_drawn_wardrobe = 0
	ENDIF

	IF cost_menu_drawn_shops = 1
		DELETE_MENU cost_menu_shops
		CLEAR_HELP 
		cost_menu_drawn_shops = 0
	ENDIF

	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops
		CLEAR_HELP 
		bought_menu_drawn_shops = 0
	ENDIF

	IF flag_wardrobe >= 1
		CLEAR_HELP
	ENDIF

	CLEAR_THIS_PRINT (WARDNO)
	CLEAR_THIS_PRINT (WARDNO2)

	main_menu_drawn_wardrobe = 0
	second_menu_drawn_wardrobe = 0
	third_menu_drawn_wardrobe = 0
	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0
	flag_remove_menu_drawn_wardrobe = 0				
	shop_main_item_picked_wardrobe = 0
	shop_sub_item_picked_shops = 0
	flag_bought_something_wardrobe = 0
	   
	blob_flag_shop = 1

	flag_player_use_changing_room_wardrobe = 0

	flag_bought_item_already_shops = 0
	flag_player_owned_item_shops = 0
	
	shop_progress_wardrobe = 0
	flag_wardrobe = 0

	player_in_changeroom_wardrobe = 0

   	DELETE_OBJECT changing_room_door_wardrobe

	MARK_MODEL_AS_NO_LONGER_NEEDED shop_door_model_wardrobe
	
	REMOVE_ANIMATION CLOTHES
	
	CLEAR_LOADED_SHOP 

	USE_TEXT_COMMANDS FALSE

	DISPLAY_RADAR TRUE

	SHOW_UPDATE_STATS TRUE

	SET_MINIGAME_IN_PROGRESS FALSE

	player_using_wardrobe = 0

	CLEAR_MISSION_AUDIO 4

//	SET_PLAYER_IS_IN_STADIUM FALSE

	TERMINATE_THIS_SCRIPT
	
RETURN

// FINDS WHICH SHOP TO LOAD
fill_in_second_menu_wardrobe:

	number_of_torso_wardrobe = 0
	number_of_legs_wardrobe = 0
	number_of_feet_wardrobe = 0
	number_of_chains_wardrobe = 0
	number_of_watches_wardrobe = 0
	number_of_glasses_wardrobe = 0
	number_of_hats_wardrobe = 0

	LOAD_SHOP $shop_section[shop_main_item_picked_wardrobe] 
	LOAD_PRICES CLOTHES

	GET_NUMBER_OF_ITEMS_IN_SHOP number_of_wardrobe_in_shop

	temp_var_shops = 0
	row_number_wardrobe = 0
	
	WHILE temp_var_shops < number_of_wardrobe_in_shop

		GET_ITEM_IN_SHOP temp_var_shops model_index
		GET_SHOPPING_EXTRA_INFO model_index 1 component_shops 
	
		IF HAS_PLAYER_BOUGHT_ITEM model_index

			SWITCH component_shops
			
				CASE CLOTHES_TEX_SHIRT 

					IF number_of_torso_wardrobe = 0
						$menu_item[row_number_wardrobe] = TORSO
						body_part_wardrobe[row_number_wardrobe] = CLOTHES_TEX_SHIRT 
						$name_of_anim_wardrobe[row_number_wardrobe] = CLO_Pose_Torso
						++ row_number_wardrobe 								
						number_of_torso_wardrobe = 1 
					ENDIF

				BREAK

				CASE CLOTHES_TEX_TROUSERS 

					IF number_of_legs_wardrobe = 0
						$menu_item[row_number_wardrobe] = LEGS
						body_part_wardrobe[row_number_wardrobe] = CLOTHES_TEX_TROUSERS
						$name_of_anim_wardrobe[row_number_wardrobe] = CLO_Pose_Legs
						++ row_number_wardrobe 								
						number_of_legs_wardrobe = 1 
					ENDIF

				BREAK

				CASE CLOTHES_TEX_SHOES 

					IF number_of_feet_wardrobe = 0
						$menu_item[row_number_wardrobe] = FEET
						body_part_wardrobe[row_number_wardrobe] = CLOTHES_TEX_SHOES
						$name_of_anim_wardrobe[row_number_wardrobe] = CLO_Pose_Shoes
						++ row_number_wardrobe 								
						number_of_feet_wardrobe = 1 
					ENDIF

				BREAK

				CASE CLOTHES_TEX_NECKLACE 

					IF number_of_chains_wardrobe = 0
						$menu_item[row_number_wardrobe] = CHAINS
						body_part_wardrobe[row_number_wardrobe] = CLOTHES_TEX_NECKLACE
						$name_of_anim_wardrobe[row_number_wardrobe] = CLO_Pose_Torso
						++ row_number_wardrobe 								
						number_of_chains_wardrobe = 1 
					ENDIF

				BREAK

				CASE CLOTHES_TEX_WATCH 

					IF number_of_watches_wardrobe = 0
						$menu_item[row_number_wardrobe] = WATCHES
						body_part_wardrobe[row_number_wardrobe] = CLOTHES_TEX_WATCH
						$name_of_anim_wardrobe[row_number_wardrobe] = CLO_Pose_Watch
						++ row_number_wardrobe 								
						number_of_watches_wardrobe = 1 
					ENDIF

				BREAK

				CASE CLOTHES_TEX_GLASSES 

					IF number_of_glasses_wardrobe = 0
						$menu_item[row_number_wardrobe] = SHADES
						body_part_wardrobe[row_number_wardrobe] = CLOTHES_TEX_GLASSES
						$name_of_anim_wardrobe[row_number_wardrobe] = CLO_Pose_Hat
						++ row_number_wardrobe 								
						number_of_glasses_wardrobe = 1 
					ENDIF

				BREAK

				CASE CLOTHES_TEX_HAT 

					IF number_of_hats_wardrobe = 0
						$menu_item[row_number_wardrobe] = HATS
						body_part_wardrobe[row_number_wardrobe] = CLOTHES_TEX_HAT
						$name_of_anim_wardrobe[row_number_wardrobe] = CLO_Pose_Hat
						++ row_number_wardrobe 								
						number_of_hats_wardrobe = 1 
					ENDIF

				BREAK

			ENDSWITCH
		   			
		ENDIF
	
		++ temp_var_shops
		
	ENDWHILE

	temp_var_shops = row_number_wardrobe
	
	WHILE temp_var_shops < MAX_NUMBER_ALLOWED_IN_MENU_SHOPS
		  
		$menu_item[temp_var_shops] = DUMMY
			
		++ temp_var_shops 

	ENDWHILE 	
	
RETURN

draw_second_menu_wardrobe:

	IF second_menu_drawn_wardrobe = 0

		PRINT_HELP_FOREVER (WARDH2)

		// Create and populate the second menu.

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT second_menu_wardrobe
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT second_menu_wardrobe
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT second_menu_wardrobe
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT second_menu_wardrobe
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT second_menu_wardrobe
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		SET_MENU_COLUMN_ORIENTATION second_menu_wardrobe 0 FO_LEFT
		SET_MENU_COLUMN second_menu_wardrobe 0 DUMMY $menu_item[0] $menu_item[1] $menu_item[2] $menu_item[3] $menu_item[4] $menu_item[5] $menu_item[6] $menu_item[7] $menu_item[8] $menu_item[9] $menu_item[10] $menu_item[11]

		second_menu_drawn_wardrobe = 1
	ENDIF

RETURN


fill_third_menu_wardrobe:
 
	temp_var_shops = 0
	row_number_wardrobe = 0
	number_bought_items_in_area = 0

	GET_CLOTHES_ITEM player1 body_part_wardrobe[second_menu_item_picked_wardrobe] player_item_texture_shops player_item_model_shops

	GET_NUMBER_OF_ITEMS_IN_SHOP number_of_wardrobe_in_shop
	
	WHILE temp_var_shops < number_of_wardrobe_in_shop

		GET_ITEM_IN_SHOP temp_var_shops model_index
		GET_SHOPPING_EXTRA_INFO model_index 0 modelid_shops
		GET_SHOPPING_EXTRA_INFO model_index 1 component_shops 
	
		IF HAS_PLAYER_BOUGHT_ITEM model_index

			IF component_shops = body_part_wardrobe[second_menu_item_picked_wardrobe]
				 
				item_model_index[number_bought_items_in_area] = model_index
				
				GET_NAME_OF_ITEM model_index $wardrobe_name
						  
				$menu_item[number_bought_items_in_area] = $wardrobe_name
				item_modelid[number_bought_items_in_area] = modelid_shops  
//				item_component[number_bought_items_in_area] = component_shops
				area_to_look_at_shops = component_shops 

				IF player_item_texture_shops = model_index
					item_hilight_shops[number_bought_items_in_area] = FALSE
				ELSE
					item_hilight_shops[number_bought_items_in_area] = TRUE
				ENDIF

				++ number_bought_items_in_area

			ENDIF 			  
											   			
		ENDIF
	
		++ temp_var_shops
		
	ENDWHILE

	temp_var_shops = number_bought_items_in_area
	
	WHILE temp_var_shops < MAX_NUMBER_ALLOWED_IN_MENU_SHOPS
		  
		$menu_item[temp_var_shops] = DUMMY
			
		++ temp_var_shops 

	ENDWHILE 	

RETURN

draw_third_menu_wardrobe:

	IF third_menu_drawn_wardrobe = 0

		PRINT_HELP_FOREVER (WARDH3)
		
		// Create and populate the third menu.

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT third_menu_wardrobe
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT third_menu_wardrobe
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT third_menu_wardrobe
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT third_menu_wardrobe
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT third_menu_wardrobe
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		SET_MENU_COLUMN_ORIENTATION third_menu_wardrobe 0 FO_LEFT
		SET_MENU_COLUMN third_menu_wardrobe 0 DUMMY $menu_item[0] $menu_item[1] $menu_item[2] $menu_item[3] $menu_item[4] $menu_item[5] $menu_item[6] $menu_item[7] $menu_item[8] $menu_item[9] $menu_item[10] $menu_item[11]

		temp_var_shops = 0
		WHILE temp_var_shops < number_bought_items_in_area
			ACTIVATE_MENU_ITEM third_menu_wardrobe temp_var_shops item_hilight_shops[temp_var_shops]
			++temp_var_shops
		ENDWHILE


		third_menu_drawn_wardrobe = 1
	ENDIF

RETURN

wardrobe_main_menu:

IF main_menu_drawn_wardrobe = 0

	GOSUB draw_remove_menu_wardrobe
	
	IF flag_remove_menu_drawn_wardrobe = 1
		DELETE_MENU remove_menu_wardrobe
		CLEAR_HELP 
		flag_remove_menu_drawn_wardrobe = 0
	ENDIF 

	IF row_number_wardrobe = 0
		draw_remove_option_wardrobe = 0
	ELSE
		draw_remove_option_wardrobe = 1
	ENDIF

	row_number_wardrobe = 0
	

	IF current_Language = LANGUAGE_ENGLISH
		CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_wardrobe
	ELSE
		IF current_Language = LANGUAGE_FRENCH
			CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_wardrobe
		ELSE
			IF current_Language = LANGUAGE_GERMAN
				CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_wardrobe
			ELSE
				IF current_Language = LANGUAGE_ITALIAN
					CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_wardrobe
				ELSE
					IF current_Language = LANGUAGE_SPANISH
						CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_wardrobe
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF

	SET_MENU_COLUMN_ORIENTATION main_menu_wardrobe 0 FO_LEFT

	IF flag_bought_from_binco = 1
		$menu_item[row_number_wardrobe] = SHOP1
		$shop_section[row_number_wardrobe] = cschp
		++row_number_wardrobe 
	ENDIF

	IF flag_bought_from_prolapse = 1
		$menu_item[row_number_wardrobe] = SHOP2
		$shop_section[row_number_wardrobe] = cssprt
		++row_number_wardrobe 
	ENDIF

	IF flag_bought_from_suburban = 1
		$menu_item[row_number_wardrobe] = SHOP3
		$shop_section[row_number_wardrobe] = lacs1
		++row_number_wardrobe 
	ENDIF
	
	IF flag_bought_from_zip = 1
		$menu_item[row_number_wardrobe] = SHOP4
		$shop_section[row_number_wardrobe] = clothgp
		++row_number_wardrobe 
	ENDIF
	
	IF flag_bought_from_victim = 1
	    $menu_item[row_number_wardrobe] = SHOP5
		$shop_section[row_number_wardrobe] = csdesgn
		++row_number_wardrobe 
	ENDIF
	
	IF flag_bought_from_didiessachs = 1
     	$menu_item[row_number_wardrobe] = SHOP6
		$shop_section[row_number_wardrobe] = csexl
		++row_number_wardrobe 
	ENDIF

	flag_got_special_clothes_wardrobe = 0

	IF flag_player_got_gimp_suit = 1
	OR flag_player_got_valet_uniform = 1 
 	OR flag_player_got_casino_uniform = 1
	OR flag_got_mechanic_clothes = 1
	OR flag_player_got_police_uniform = 1
	OR flag_player_got_country_clothes = 1
		flag_got_special_clothes_wardrobe = 1	
	ENDIF

	IF flag_got_medic_clothes = 1
	OR flag_got_pimp_clothes = 1
		flag_got_special_clothes_wardrobe = 1
	ENDIF

	IF flag_got_special_clothes_wardrobe = 1
	    $menu_item[row_number_wardrobe] = SHOP7
		$shop_section[row_number_wardrobe] = uniform
        ++row_number_wardrobe
	ENDIF

	IF draw_remove_option_wardrobe = 1
		$menu_item[row_number_wardrobe] = REMCLT
		$shop_section[row_number_wardrobe] = remove
		++row_number_wardrobe
	ENDIF

	temp_var_shops = row_number_wardrobe
	
	WHILE temp_var_shops < MAX_NUMBER_ALLOWED_IN_MENU_SHOPS
		$menu_item[temp_var_shops] = DUMMY
		++ temp_var_shops 
	ENDWHILE

	PRINT_HELP_FOREVER WARDH1
	SET_MENU_COLUMN main_menu_wardrobe 0 DUMMY $menu_item[0] $menu_item[1] $menu_item[2] $menu_item[3] $menu_item[4] $menu_item[5] $menu_item[6] $menu_item[7] $menu_item[8] $menu_item[9] $menu_item[10] $menu_item[11]

	main_menu_drawn_wardrobe = 1
ENDIF

RETURN
 
// displays the text for the cost and name of item
display_price_name_text_wardrobe:

	IF cost_menu_drawn_shops = 0
	    
		IF $shop_section[shop_main_item_picked_wardrobe] = remove 
			PRINT_HELP_FOREVER REMOH2  
		ELSE
			PRINT_HELP_FOREVER WARDH4  
		ENDIF

		CREATE_MENU CLOTCHO 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 0 FO_LEFT 
		SET_MENU_COLUMN cost_menu_shops 0 DUMMY $wardrobe_name DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN_WIDTH cost_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 1 46 

		cost_menu_drawn_shops = 1
	ENDIF

RETURN


bought_text_wardrobe:
	IF bought_menu_drawn_shops = 0
		IF IS_XBOX_VERSION
			CREATE_MENU CLOTCHO 29.0 48.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops
		ELSE
			CREATE_MENU CLOTCHO 29.0 25.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops
		ENDIF
		SET_MENU_COLUMN_ORIENTATION bought_menu_shops 0 FO_LEFT 
		SET_MENU_COLUMN bought_menu_shops 0 DUMMY CHANGED DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN_WIDTH bought_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH bought_menu_shops 1 46 
		bought_menu_drawn_shops = 1
	ENDIF
RETURN

fill_in_special_menu_wardrobe:

	LOAD_SHOP uniform 
	LOAD_PRICES CLOTHES

	GET_NUMBER_OF_ITEMS_IN_SHOP number_of_wardrobe_in_shop


	temp_var_shops = 0
	row_number_wardrobe = 0
	number_bought_items_in_area = 0
	
	WHILE temp_var_shops < number_of_wardrobe_in_shop

		GET_ITEM_IN_SHOP temp_var_shops model_index
		GET_SHOPPING_EXTRA_INFO model_index 0 modelid_shops
		GET_SHOPPING_EXTRA_INFO model_index 1 component_shops 
					 
		item_model_index[number_bought_items_in_area] = model_index
		
		GET_NAME_OF_ITEM model_index $wardrobe_name
				  
		$menu_item[number_bought_items_in_area] = $wardrobe_name
		item_modelid[number_bought_items_in_area] = modelid_shops  
//		item_component[number_bought_items_in_area] = component_shops
		area_to_look_at_shops = CLOTHES_TEX_EXTRA1 

		GET_CLOTHES_ITEM player1 CLOTHES_TEX_EXTRA1 player_item_texture_shops player_item_model_shops
		
		IF player_item_texture_shops = model_index
			item_hilight_shops[number_bought_items_in_area] = FALSE
		ELSE
			item_hilight_shops[number_bought_items_in_area] = TRUE
		ENDIF			

		IF flag_player_got_gimp_suit = 1
		AND $wardrobe_name = GIMP 
			++ number_bought_items_in_area
		ENDIF
						
		IF flag_player_got_valet_uniform = 1
		AND $wardrobe_name = VALETU 
			++ number_bought_items_in_area
		ENDIF

		IF flag_player_got_country_clothes = 1
		AND $wardrobe_name = COUNTRY
			++ number_bought_items_in_area
		ENDIF
			  
		IF flag_player_got_casino_uniform = 1
		AND $wardrobe_name = CROUP
			++ number_bought_items_in_area
		ENDIF
							  
		IF flag_player_got_police_uniform = 1
		AND $wardrobe_name = POLICE
			++ number_bought_items_in_area
		ENDIF
				 
		IF flag_got_medic_clothes = 1
		AND $wardrobe_name = PAMEDIC
			++ number_bought_items_in_area
		ENDIF
		
		IF flag_got_pimp_clothes = 1
		AND $wardrobe_name = PIMPSUT
			++ number_bought_items_in_area
		ENDIF

		IF flag_got_mechanic_clothes = 1
		AND $wardrobe_name = RDRIVER
			++ number_bought_items_in_area
		ENDIF
		 
		$current_name_of_anim_wardrobe = CLO_Pose_Torso

 	
		++ temp_var_shops
		
	ENDWHILE

	temp_var_shops = number_bought_items_in_area
	
	WHILE temp_var_shops < MAX_NUMBER_ALLOWED_IN_MENU_SHOPS
		$menu_item[temp_var_shops] = DUMMY
		++ temp_var_shops 
	ENDWHILE

RETURN

draw_remove_menu_wardrobe:

	temp_var_shops = 0
	row_number_wardrobe = 0

	IF flag_remove_menu_drawn_wardrobe = 0
	
		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT remove_menu_wardrobe
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT remove_menu_wardrobe
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT remove_menu_wardrobe
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT remove_menu_wardrobe
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT remove_menu_wardrobe
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION remove_menu_wardrobe 0 FO_LEFT

		GET_CLOTHES_ITEM player1 CLOTHES_TEX_SHIRT player_item_texture_shops player_item_model_shops

		IF NOT player_item_texture_shops = 0
        	$menu_item[row_number_wardrobe] = TORSO
			item_component[row_number_wardrobe] = CLOTHES_TEX_SHIRT
			++row_number_wardrobe
		ENDIF
		
		GET_CLOTHES_ITEM player1 CLOTHES_TEX_TROUSERS player_item_texture_shops player_item_model_shops
		
		IF NOT player_item_texture_shops = 0
        	$menu_item[row_number_wardrobe] = LEGS
			item_component[row_number_wardrobe] = CLOTHES_TEX_TROUSERS
			++row_number_wardrobe
		ENDIF 

		GET_CLOTHES_ITEM player1 CLOTHES_TEX_SHOES player_item_texture_shops player_item_model_shops
		
		IF NOT player_item_texture_shops = 0
	        $menu_item[row_number_wardrobe] = FEET
			item_component[row_number_wardrobe] = CLOTHES_TEX_SHOES
			++row_number_wardrobe
		ENDIF
		
		GET_CLOTHES_ITEM player1 CLOTHES_TEX_NECKLACE player_item_texture_shops player_item_model_shops
		
		IF NOT player_item_texture_shops = 0
        	$menu_item[row_number_wardrobe] = CHAINS
			item_component[row_number_wardrobe] = CLOTHES_TEX_NECKLACE
			++row_number_wardrobe
		ENDIF

		GET_CLOTHES_ITEM player1 CLOTHES_TEX_WATCH player_item_texture_shops player_item_model_shops
		
		IF NOT player_item_texture_shops = 0
			$menu_item[row_number_wardrobe] = WATCHES
			item_component[row_number_wardrobe] = CLOTHES_TEX_WATCH
			++row_number_wardrobe
		ENDIF

		GET_CLOTHES_ITEM player1 CLOTHES_TEX_GLASSES player_item_texture_shops player_item_model_shops
		
		IF NOT player_item_texture_shops = 0
			$menu_item[row_number_wardrobe] = SHADES
			item_component[row_number_wardrobe] = CLOTHES_TEX_GLASSES
			++row_number_wardrobe
		ENDIF
		
		GET_CLOTHES_ITEM player1 CLOTHES_TEX_HAT player_item_texture_shops player_item_model_shops
		
		IF NOT player_item_texture_shops = 0
			$menu_item[row_number_wardrobe] = HATS
			item_component[row_number_wardrobe] = CLOTHES_TEX_HAT
			++row_number_wardrobe
		ENDIF
		
		GET_CLOTHES_ITEM player1 CLOTHES_TEX_EXTRA1 player_item_texture_shops player_item_model_shops
		
		IF NOT player_item_texture_shops = 0
			$menu_item[row_number_wardrobe] = SHOP7
			item_component[row_number_wardrobe] = CLOTHES_TEX_EXTRA1
			++row_number_wardrobe
		ENDIF
		  
		temp_var_shops = row_number_wardrobe
	
		WHILE temp_var_shops < MAX_NUMBER_ALLOWED_IN_MENU_SHOPS
			$menu_item[temp_var_shops] = DUMMY
			++temp_var_shops 
		ENDWHILE

		PRINT_HELP_FOREVER (REMOH1)
		SET_MENU_COLUMN remove_menu_wardrobe 0 DUMMY $menu_item[0] $menu_item[1] $menu_item[2] $menu_item[3] $menu_item[4] $menu_item[5] $menu_item[6] $menu_item[7] $menu_item[8] $menu_item[9] $menu_item[10] $menu_item[11]
					 		
		flag_remove_menu_drawn_wardrobe = 1

	ENDIF
		
RETURN

MISSION_END

}
