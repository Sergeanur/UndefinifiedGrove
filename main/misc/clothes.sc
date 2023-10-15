MISSION_START

{

VAR_TEXT_LABEL $clothes_name // Used to display the name of the garment

VAR_INT shop_keep_clothes

VAR_INT clothes_price
											    
VAR_INT number_of_items_clothes

VAR_TEXT_LABEL $stored_clothes_shop

VAR_INT shopkeeper_attacked_clothes

VAR_INT player_in_view_mode

VAR_INT flag_player_use_changing_room

// used to create the changing room door to animate
VAR_INT changing_room_door

VAR_INT flag_clothes

VAR_FLOAT return_animation_time_clothes object_animation_time_clothes

VAR_INT flag_player_in_changing_room

VAR_TEXT_LABEL16 $name_of_anim_clothes

VAR_INT shop_progress_clothes // used to say what stage you are in in section 4 onwards

// menu stuff
VAR_INT number_of_clothes_in_shop number_of_clothes_in_area

number_of_clothes_in_shop = 0
number_of_clothes_in_area = 0
area_looked_for_shops = 0
price_counter = 0
item_no_shops = 0
temp_var_shops = 0

// NEW MEMU STUFF
main_menu_drawn_shops = 0
sub_menu_drawn_shops = 0

shop_progress_clothes = 0

// new stuff for script to work with all 6 shops
CONST_FLOAT change_door_trueX 213.874
CONST_FLOAT change_door_trueY -39.811
CONST_FLOAT change_door_trueZ 1002.2

VAR_FLOAT change_doorX change_doorY change_doorZ
change_doorX = 0.0
change_doorY = 0.0
change_doorZ = 0.0
change_room_door_heading = 0.0

// used for the offsets for all clothes shops
VAR_FLOAT clothes_offX clothes_offY clothes_offZ change_room_door_heading 
clothes_offX = 0.0
clothes_offY = 0.0
clothes_offZ = 0.0
change_room_door_heading = 0.0

// area for player to stand
CONST_FLOAT clothes_locate_trueX 214.622
CONST_FLOAT clothes_locate_trueY -40.652
CONST_FLOAT clothes_locate_trueZ 1001.033

VAR_FLOAT clothes_locateX clothes_locateY clothes_locateZ clothes_heading
clothes_locateX = 0.0
clothes_locateY = 0.0
clothes_locateZ = 0.0
clothes_heading = 0.0

// camera stuff
CONST_FLOAT clothes_cam_trueX 212.8715                         
CONST_FLOAT clothes_cam_trueY -42.8156
CONST_FLOAT clothes_cam_trueZ 1002.1159

VAR_FLOAT clothes_camX clothes_camY clothes_camZ
clothes_camX = 0.0
clothes_camY = 0.0
clothes_camZ = 0.0

CONST_FLOAT clothes_cam_point_trueX 213.4791  
CONST_FLOAT clothes_cam_point_trueY -42.0238 
CONST_FLOAT clothes_cam_point_trueZ 1002.0531

VAR_FLOAT clothes_cam_pointX clothes_cam_pointY clothes_cam_pointZ
clothes_cam_pointX = 0.0 
clothes_cam_pointY = 0.0 
clothes_cam_pointZ = 0.0

// hat, chain, specs
CONST_FLOAT clothes_hat_cam_trueX 214.5148     
CONST_FLOAT clothes_hat_cam_trueY -41.6106
CONST_FLOAT clothes_hat_cam_trueZ 1002.4937

VAR_FLOAT clothes_hat_camX clothes_hat_camY clothes_hat_camZ
clothes_hat_camX = 0.0
clothes_hat_camY = 0.0
clothes_hat_camZ = 0.0

CONST_FLOAT clothes_hat_cam_point_trueX 214.5367    
CONST_FLOAT clothes_hat_cam_point_trueY -40.6175 
CONST_FLOAT clothes_hat_cam_point_trueZ 1002.6089

VAR_FLOAT clothes_hat_cam_pointX clothes_hat_cam_pointY clothes_hat_cam_pointZ
clothes_hat_cam_pointX = 0.0 
clothes_hat_cam_pointY = 0.0 
clothes_hat_cam_pointZ = 0.0

// Shop keeper
CONST_FLOAT shop_keep_cloth_trueX 203.528
CONST_FLOAT shop_keep_cloth_trueY -41.644
CONST_FLOAT shop_keep_cloth_trueZ 1000.852

VAR_FLOAT shop_keep_clothX shop_keep_clothY shop_keep_clothZ shop_keep_heading_clothes
shop_keep_clothX = 0.0
shop_keep_clothY = 0.0
shop_keep_clothZ = 0.0
shop_keep_heading_clothes = 0.0

VAR_INT shop_door_model_clothes


number_of_items_clothes = 0

flag_player_in_changing_room = 0

clothes_heading = 0.0

clothes_cam_pointX = 0.0
clothes_cam_pointY = 0.0
clothes_cam_pointZ = 0.0

return_animation_time_clothes = 0.0

object_animation_time_clothes = 0.0


flag_clothes = 0

flag_player_use_changing_room = 0  

player_in_view_mode = 0

shopkeeper_attacked_clothes = 0

flag_bought_item_already_shops = 0
flag_no_money_shops = 0

main_menu_drawn_shops = 0
sub_menu_drawn_shops = 0
cost_menu_drawn_shops = 0
bought_menu_drawn_shops = 0

flag_bought_item_already_shops = 0
flag_no_money_shops = 0
flag_player_owned_item_shops = 0

area_to_look_at_shops = 0

VAR_INT player_in_changeroom_clothes
player_in_changeroom_clothes = 0

// tells script which bit of menu to go to
VAR_INT flag_bought_something_clothes
flag_bought_something_clothes = 0

// hashkey to change clothes prices
VAR_INT hash_key_clothes
hash_key_clothes = 0

// requesting models

IF $shop_name = LACS1 // sub URBAN
	shopkeeper_model_shops = WFYCLOT
	shop_door_model_clothes = CJ_SUBURB_DOOR_2
ENDIF

IF $shop_name = CSchp // BINCO
	shopkeeper_model_shops = WFYCLOT
	shop_door_model_clothes = CJ_BINCO_DOOR
ENDIF

IF $shop_name = CSsprt // PRO-LAPS
	shopkeeper_model_shops = WMYCLOT
	shop_door_model_clothes = CJ_PRO_DOOR_01
ENDIF

IF $shop_name = clothgp // ZIP
	shopkeeper_model_shops = WFYCLOT
	shop_door_model_clothes = CJ_GAP_DOOR_
ENDIF

IF $shop_name = Csdesgn // VICTIM
	shopkeeper_model_shops = WFYCLOT
	shop_door_model_clothes = CJ_VICTIM_DOOR 
ENDIF

IF $shop_name = Csexl // DIDIES SACHS
	shopkeeper_model_shops = WMYCLOT
	shop_door_model_clothes = CJ_DS_DOOR
ENDIF

REQUEST_MODEL shop_door_model_clothes

REQUEST_MODEL shopkeeper_model_shops

LOAD_MISSION_AUDIO 4 SOUND_DRESSING 

LOAD_ALL_MODELS_NOW
// end of requesting models 

SET_DEATHARREST_STATE OFF

SCRIPT_NAME	CLOTH

shop_clothes_inner:

	WAIT 0
	
	IF IS_PLAYER_PLAYING player1

		IF NOT IS_STRING_EMPTY $shop_name

			IF flag_clothes > 0

				SHUT_CHAR_UP scplayer TRUE

				IF HAS_LANGUAGE_CHANGED
					CLEAR_HELP
					DELETE_MENU main_menu_shops
					DELETE_MENU sub_menu_shops
					DELETE_MENU bought_menu_shops
					DELETE_MENU cost_menu_shops
					main_menu_drawn_shops = 0
					sub_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0
					cost_menu_drawn_shops = 0
					GET_CURRENT_LANGUAGE current_Language
				ENDIF

			ENDIF
			
			IF flag_clothes = 0

				IF $shop_name = LACS1 // sub URBAN

					clothes_offX = 0.0
					clothes_offY = 0.0
					clothes_offZ = 0.0

					shop_keep_clothX = 203.528
					shop_keep_clothY = -41.644
					shop_keep_clothZ = 1000.852
				
					shop_keep_heading_clothes = 180.0				
					clothes_heading = 90.0 
					change_room_door_heading = 0.0 
					
					$stored_clothes_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_clothes_in_shop
					
					flag_clothes = 1
									  
				ENDIF				
					
				IF $shop_name = CSchp // BINCO

					clothes_offX = 2.85
					clothes_offY = -57.534
					clothes_offZ = 3.26

					shop_keep_clothX = 208.806
					shop_keep_clothY = -98.713
					shop_keep_clothZ = 1004.297
									
					shop_keep_heading_clothes = 180.0				
					clothes_heading = 90.0 
					change_room_door_heading = 0.0 
					
					$stored_clothes_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_clothes_in_shop
					
					flag_clothes = 1
														  
				ENDIF

				IF $shop_name = CSsprt // PRO-LAPS

					clothes_offX = -12.653
					clothes_offY = -90.758
					clothes_offZ = 1.477

					shop_keep_clothX = 207.018
					shop_keep_clothY = -127.782
					shop_keep_clothZ = 1002.555
				
					shop_keep_heading_clothes = 180.0				
					clothes_heading = 90.0 
					change_room_door_heading = 0.0 
					
					$stored_clothes_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_clothes_in_shop
					
					flag_clothes = 1
														  
				ENDIF

				IF $shop_name = clothgp // ZIP

					clothes_offX = -33.204
					clothes_offY = -46.245
					clothes_offZ = 0.0

					shop_keep_clothX = 161.560
					shop_keep_clothY = -81.369
					shop_keep_clothZ = 1000.859			
				
					shop_keep_heading_clothes = 180.0				
					clothes_heading = 90.0 
					change_room_door_heading = 0.0 
					
					$stored_clothes_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_clothes_in_shop
					
					flag_clothes = 1
														  
				ENDIF

				IF $shop_name = Csdesgn // VICTIM

					IF flag_mansion_mission_counter > 3
						GET_HASH_KEY tshirtlocgrey hash_key_clothes
						ADD_PRICE_MODIFIER hash_key_clothes 15

						GET_HASH_KEY baskballloc hash_key_clothes
						ADD_PRICE_MODIFIER hash_key_clothes 20

						GET_HASH_KEY tshirtmaddgrey hash_key_clothes
						ADD_PRICE_MODIFIER hash_key_clothes 300

						GET_HASH_KEY tshirtmaddgrn hash_key_clothes
						ADD_PRICE_MODIFIER hash_key_clothes 300
					ENDIF

					clothes_offX = -5.806
					clothes_offY = 36.733
					clothes_offZ = -0.815

					shop_keep_clothX = 204.836
					shop_keep_clothY = -7.328
					shop_keep_clothZ = 1000.258
					shop_keep_heading_clothes = 270.0
									
					clothes_heading = 90.0 
					change_room_door_heading = 0.0 
					
					$stored_clothes_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_clothes_in_shop
					
					flag_clothes = 1
														  
				ENDIF

				IF $shop_name = Csexl // DIDIES SACHS

					clothes_offX = 1.2
					clothes_offY = -114.524
					clothes_offZ = -1.51

					shop_keep_clothX = 204.366
					shop_keep_clothY = -157.789
					shop_keep_clothZ = 999.563
				
					shop_keep_heading_clothes = 180.0				
					clothes_heading = 90.0 
					change_room_door_heading = 0.0 
					
					$stored_clothes_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_clothes_in_shop
					
					flag_clothes = 1
														  
				ENDIF								

			ENDIF // flag_clothes  = 0

			IF flag_clothes = 1
			
				USE_TEXT_COMMANDS TRUE

				SHOW_UPDATE_STATS FALSE

				clothes_camX = clothes_cam_trueX + clothes_offX   
				clothes_camY = clothes_cam_trueY + clothes_offY 
				clothes_camZ = clothes_cam_trueZ + clothes_offZ 

				clothes_cam_pointX = clothes_cam_point_trueX + clothes_offX 
				clothes_cam_pointY = clothes_cam_point_trueY + clothes_offY 
				clothes_cam_pointZ = clothes_cam_point_trueZ + clothes_offZ
				
				clothes_hat_camX = clothes_hat_cam_trueX + clothes_offX     
 				clothes_hat_camY = clothes_hat_cam_trueY + clothes_offY
 				clothes_hat_camZ = clothes_hat_cam_trueZ + clothes_offZ

				clothes_hat_cam_pointX = clothes_hat_cam_point_trueX + clothes_offX 
 				clothes_hat_cam_pointY = clothes_hat_cam_point_trueY + clothes_offY
				clothes_hat_cam_pointZ = clothes_hat_cam_point_trueZ + clothes_offZ 

				clothes_locateX = clothes_locate_trueX + clothes_offX
				clothes_locateY = clothes_locate_trueY + clothes_offY 
				clothes_locateZ = clothes_locate_trueZ + clothes_offZ

				change_doorX = change_door_trueX + clothes_offX 
				change_doorY = change_door_trueY + clothes_offY
				change_doorZ = change_door_trueZ + clothes_offZ
												
			  	CREATE_CHAR PEDTYPE_CIVMALE shopkeeper_model_shops shop_keep_clothX shop_keep_clothY shop_keep_clothZ shop_keep_clothes
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER shop_keep_clothes TRUE
				SET_CHAR_DECISION_MAKER shop_keep_clothes DM_PED_EMPTY
				SET_CHAR_HEADING shop_keep_clothes shop_keep_heading_clothes
				shopkeeper_attacked_clothes = 0

				CREATE_OBJECT_NO_OFFSET shop_door_model_clothes change_doorX change_doorY change_doorZ changing_room_door
				SET_OBJECT_HEADING changing_room_door change_room_door_heading

				SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_WELCOME_TO_SHOP sample_name_shops
				
				blob_flag_shop = 1

				flag_clothes = 2
				
			ENDIF // flag_clothes = 1										

			IF flag_clothes = 2
			
				GET_LOADED_SHOP $shop_name
															
				IF NOT $shop_name = $stored_clothes_shop
					GOSUB clothes_cleanup_big
				ENDIF

				IF NOT IS_CHAR_DEAD shop_keep_clothes

					IF shopkeeper_attacked_clothes = 0

						IF IS_CHAR_SHOOTING scplayer
						OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_clothes
						OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_clothes WEAPONTYPE_ANYWEAPON
						OR iSetClothesPanic = 1
							SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shops
							TASK_HANDS_UP shop_keep_clothes -2
							shopkeeper_attacked_clothes = 1
						ELSE

							blob_flag_shop = 1
							IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer clothes_locateX clothes_locateY clothes_locateZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop

								IF CAN_PLAYER_START_MISSION player1
									flag_clothes = 3
								ENDIF

							ENDIF

						ENDIF

						IF flag_player_use_changing_room = 0
							
							IF LOCATE_CHAR_ON_FOOT_CHAR_3D scplayer shop_keep_clothes 8.0 8.0 3.0 FALSE
								SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_SHOW_CHANGINGROOM sample_name_shops
								flag_player_use_changing_room = 1
							ELSE
								flag_player_use_changing_room = 0
							ENDIF 

						ENDIF // player use changing room
													 						   										
					ELSE

						GOSUB clothes_cleanup_small
					
						GET_LOADED_SHOP $shop_name
										
						IF NOT $shop_name = $stored_clothes_shop
							GOSUB clothes_cleanup_big
					 	ENDIF
					 	
					 	GOTO shop_clothes_inner

					ENDIF

				ELSE

					GOSUB clothes_cleanup_small
					
					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_clothes_shop
						GOSUB clothes_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_clothes_inner

				ENDIF

			ENDIF // flag_clothes = 2

			IF flag_clothes = 3

				IF  IS_PLAYER_PLAYING player1
					SET_PLAYER_CONTROL player1 OFF
					SET_MINIGAME_IN_PROGRESS TRUE
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
					GET_CHAR_COORDINATES scplayer player_x player_y player_z
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_AREA player_x player_y player_z 1.0 TRUE
					DISPLAY_RADAR FALSE
					TIMERA = 0
					shop_progress_clothes = 0
					flag_clothes = 4
				ELSE
					GOSUB clothes_cleanup_big
					GOTO shop_clothes_inner
				ENDIF 

			ENDIF // flag_clothes = 3

			// ***************** SETTING UP CAMERA AND CHECK PLAYER POSITION *********************
			IF flag_clothes = 4

				IF IS_CHAR_DEAD shop_keep_clothes			

			 		GOSUB clothes_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_clothes_shop
						GOSUB clothes_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_clothes_inner
				 	 
				ENDIF // end of shop keep dead


				IF shop_progress_clothes = 0 
 				
					IF TIMERA >= 300																		
						 
						SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
						SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer clothes_locateX clothes_locateY clothes_locateZ 
						SET_CHAR_HEADING scplayer clothes_heading
						GET_CHAR_COORDINATES scplayer player_x player_y player_z

						GET_CHAR_COORDINATES shop_keep_clothes temp_shopX temp_shopY temp_shopZ
									
						IF NOT temp_shopX = shop_keep_clothX 
						OR NOT temp_shopY = shop_keep_clothY 
						OR NOT temp_shopZ = shop_keep_clothZ
							SET_CHAR_COORDINATES shop_keep_clothes shop_keep_clothX shop_keep_clothY shop_keep_clothZ
							SET_CHAR_HEADING shop_keep_clothes shop_keep_heading_clothes
						ENDIF 
							  																																																							
						SET_FIXED_CAMERA_POSITION clothes_camX clothes_camY clothes_camZ 0.0 0.0 0.0 			
						POINT_CAMERA_AT_POINT clothes_cam_pointX clothes_cam_pointY clothes_cam_pointZ JUMP_CUT

						STORE_CLOTHES_STATE
						   	   							
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_In CLOTHES 4.0 FALSE FALSE FALSE TRUE -1
	  					return_animation_time_clothes = 0.0

						IF IS_CHAR_PLAYING_ANIM scplayer CLO_In
							GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_In return_animation_time_clothes
						ENDIF

						IF DOES_OBJECT_EXIST changing_room_door
							PLAY_OBJECT_ANIM changing_room_door CLO_Pose_Out_O CLOTHES 4.0 FALSE TRUE
						ENDIF

						shop_progress_clothes = 1

					ENDIF

				ENDIF
			   	
			   	//	Waiting for walking into changing room anim to finish				
				IF shop_progress_clothes = 1	

					IF IS_CHAR_PLAYING_ANIM scplayer CLO_In
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_In return_animation_time_clothes
					ENDIF
				
				    IF return_animation_time_clothes = 1.0						   	  																	   									
						shop_progress_clothes = 0
						flag_clothes = 5
					ENDIF
					
				ENDIF

			ENDIF // flag_clothes = 4
			
			// ************************************* FIRST MENU ***********************************
			// player to select view and buy clothes					
			IF flag_clothes = 5		

				IF IS_CHAR_DEAD shop_keep_clothes			

			 		GOSUB clothes_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_clothes_shop
						GOSUB clothes_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_clothes_inner
				 	 
				ENDIF // end of shop keep dead

				IF main_menu_drawn_shops = 0

					IF shop_progress_clothes < 1
						GOSUB print_cloth_menu_oncreen_text
					ENDIF

				ENDIF

				blob_flag_shop = 0

				IF shop_progress_clothes = 0

				    IF IS_BUTTON_PRESSED PAD1 CROSS
						shop_progress_clothes = 1
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 TRIANGLE
						shop_progress_clothes = 2
					ENDIF

				ENDIF				
				
				// ******* PLAYER HAS PRESSED CROSS IN FIRST MENU TO SELECT TORSO LEGS ETC ********
				IF shop_progress_clothes = 1	
					
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS

						GET_MENU_ITEM_ACCEPTED main_menu_shops shop_main_item_picked_shops

						IF shop_main_item_picked_shops < 0
							shop_main_item_picked_shops = 0
						ENDIF 

						GOSUB find_which_clothes_shop_load

						flag_clothes = 6
												
						IF main_menu_drawn_shops = 1
							DELETE_MENU main_menu_shops
							CLEAR_HELP
							main_menu_drawn_shops = 0
						ENDIF

						shop_progress_clothes = 0

					ENDIF
				ENDIF

				// ******************** PLAYER HAS PRESSED TRIANGLE IN FIRST MENU *****************
				IF shop_progress_clothes = 2
					
					IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE

						IF main_menu_drawn_shops = 1
							DELETE_MENU main_menu_shops
							CLEAR_HELP
							main_menu_drawn_shops = 0
						ENDIF

						IF sub_menu_drawn_shops = 1
							DELETE_MENU sub_menu_shops
							CLEAR_HELP
							sub_menu_drawn_shops = 0
						ENDIF

						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Out CLOTHES 1000.0 FALSE FALSE FALSE FALSE -1
						return_animation_time_clothes = 0.0

						IF DOES_OBJECT_EXIST changing_room_door
							PLAY_OBJECT_ANIM changing_room_door CLO_Pose_In_O CLOTHES 1000.0 FALSE TRUE
						ENDIF

						shop_progress_clothes = 5

					ENDIF
				ENDIF

				// ******************* WAITING FOR WALK AWAY ANIM TO FINISH ***********************
				IF shop_progress_clothes = 5

					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Out
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Out return_animation_time_clothes
					ENDIF

					// Player walking out of changing room
					IF return_animation_time_clothes = 1.0
						shop_progress_clothes = 6
					ENDIF
				ENDIF

				// * FINISHED QUITTING OUT OF FIRST MENU - HAVE TO WAIT FOR PLAYER TO LEAVE AREA *
				IF shop_progress_clothes = 6	
					SET_PLAYER_CONTROL player1 ON
					SET_MINIGAME_IN_PROGRESS FALSE
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT

					DISPLAY_RADAR TRUE

					SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_THANKS_FOR_CUSTOM sample_name_shops

					player_in_changeroom_clothes = 0

					main_menu_drawn_shops = 0
					sub_menu_drawn_shops = 0

					flag_clothes = 8
				ENDIF

			ENDIF // flag_clothes = 5
			
			// ********************************	SECOND MENU ***********************************
			// ************ Player selects what to view and buy *******************************
			IF flag_clothes = 6

				IF IS_CHAR_DEAD shop_keep_clothes			

			 		GOSUB clothes_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_clothes_shop
						GOSUB clothes_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_clothes_inner
				 	 
				ENDIF // end of shop keep dead


				// stops the menu text darwing after player has pressed to view item

				IF sub_menu_drawn_shops = 0
				 
					IF shop_progress_clothes < 2
						GOSUB print_clothes_oncreen_text2
					ENDIF

				ENDIF

				blob_flag_shop = 0
																																		
				IF shop_progress_clothes = 0

					// quit to menu 1						
					
					IF IS_BUTTON_PRESSED PAD1 TRIANGLE
						shop_progress_clothes = 1
					ENDIF


					// View Item
				   	
					IF IS_BUTTON_PRESSED PAD1 CROSS
					
						player_in_changeroom_clothes = 0

						GET_MENU_ITEM_SELECTED sub_menu_shops shop_sub_item_picked_shops

						IF shop_sub_item_picked_shops < 0
							shop_sub_item_picked_shops = 0
						ENDIF

						model_index = item_model_index[shop_sub_item_picked_shops] 						
						clothes_price = item_price[shop_sub_item_picked_shops]
						modelid_shops = item_modelid[shop_sub_item_picked_shops]
		 				component_shops = item_component[shop_sub_item_picked_shops]   
					   	$clothes_name = $item_text_label[shop_sub_item_picked_shops]

						IF component_shops = CLOTHES_TEX_NECKLACE
						OR component_shops = CLOTHES_TEX_WATCH
						OR component_shops = CLOTHES_TEX_GLASSES
						OR component_shops = CLOTHES_TEX_HAT
							SET_FIXED_CAMERA_POSITION clothes_hat_camX clothes_hat_camY clothes_hat_camZ 0.0 0.0 0.0 			
							POINT_CAMERA_AT_POINT clothes_hat_cam_pointX clothes_hat_cam_pointY clothes_hat_cam_pointZ JUMP_CUT
						ELSE

							IF area_to_look_at_shops = CLOTHES_TEX_SHIRT
						   	OR area_to_look_at_shops = CLOTHES_TEX_TROUSERS   
						   		PLAY_MISSION_AUDIO 4
						   	ENDIF

							SET_FIXED_CAMERA_POSITION clothes_camX clothes_camY clothes_camZ 0.0 0.0 0.0 			
							POINT_CAMERA_AT_POINT clothes_cam_pointX clothes_cam_pointY clothes_cam_pointZ JUMP_CUT
						ENDIF
					   												 
						
						IF sub_menu_drawn_shops = 1
							DELETE_MENU sub_menu_shops
							CLEAR_HELP
							sub_menu_drawn_shops = 0
						ENDIF

						shop_progress_clothes = 2
					ENDIF

				ENDIF
				
				// **************************** QUIT TO MENU 1 ************************************
				//	Player has pressed triangle in second menu							
				IF shop_progress_clothes = 1	
					
					IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE

						shop_progress_clothes = 0
						flag_clothes = 5

						IF sub_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU sub_menu_shops
							sub_menu_drawn_shops = 0
						ENDIF

						IF main_menu_drawn_shops = 0
							GOSUB print_cloth_menu_oncreen_text 
						ENDIF		

					ENDIF	

				ENDIF

				// ********************************** VIEW ITEM ***********************************
				//	Player has pressed square in second menu
				
				IF shop_progress_clothes = 2
	                IF HAS_MISSION_AUDIO_FINISHED 4
						IF NOT IS_BUTTON_PRESSED PAD1 CROSS
							shop_progress_clothes = 3
						ENDIF
					ENDIF
				ENDIF

				// ********	START THE PLAYER WALKING OUT OF THE CHANGING ROOM WITH NEW CLOTHES ****	
				IF shop_progress_clothes = 3

					IF area_to_look_at_shops = CLOTHES_TEX_SHIRT
					OR area_to_look_at_shops = CLOTHES_TEX_TROUSERS
					OR area_to_look_at_shops = CLOTHES_TEX_SHOES
						GIVE_PLAYER_CLOTHES player1 0 0 17
						STORE_CLOTHES_STATE
					ENDIF					
																					
		 			GIVE_PLAYER_CLOTHES player1 model_index modelid_shops component_shops
		   			BUILD_PLAYER_MODEL player1

					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_In CLOTHES 1000.0 FALSE FALSE FALSE TRUE -1
					return_animation_time_clothes = 0.0

					IF DOES_OBJECT_EXIST changing_room_door
						PLAY_OBJECT_ANIM changing_room_door CLO_Pose_In_O CLOTHES 1000.0 FALSE TRUE
					ENDIF										   					


					shop_progress_clothes = 4
				ENDIF

				// ****** WAITING FOR ANIM OF PLAYER WALKING OUT OF CHANGING ROOM TO FINISH *******
				IF shop_progress_clothes = 4	

					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Pose_In
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Pose_In return_animation_time_clothes
					ENDIF
	   
					// ********* PLAYER WALKING OUT OF CHANGING ROOM TO VIEW HIS PURCHASES ********
					IF return_animation_time_clothes = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $name_of_anim_clothes CLOTHES 1000.0 FALSE FALSE FALSE TRUE -1 // CLO_Pose_Torso
						return_animation_time_clothes = 0.0						
						shop_progress_clothes = 5
					ENDIF

				ENDIF

				// ******************* WAITING FOR CLOTHES-SPECIFIC ANIM TO FINISH *****************
				IF shop_progress_clothes = 5

   					IF IS_CHAR_PLAYING_ANIM scplayer $name_of_anim_clothes
						GET_CHAR_ANIM_CURRENT_TIME scplayer $name_of_anim_clothes return_animation_time_clothes
					ENDIF

					// ****************** PLAYER CHECKS OUT WHAT U HAVE CHOSEN ********************
					IF return_animation_time_clothes = 1.0
						 
						GOSUB display_price_name_text_clothes
						
						// player in pose loop
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Loop CLOTHES 1000.0 TRUE FALSE FALSE FALSE -1
						return_animation_time_clothes = 0.0
						flag_clothes = 7
						shop_progress_clothes = 0
					ENDIF
										
				ENDIF
					 	
			ENDIF	//	IF flag_clothes = 6

			// ******************** DISPLAY PRICE OF CURRENT CLOTHES SELECTION ********************
			IF flag_clothes = 7

				IF IS_CHAR_DEAD shop_keep_clothes			

			 		GOSUB clothes_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_clothes_shop
						GOSUB clothes_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_clothes_inner
				 	 
				ENDIF // end of shop keep dead

				blob_flag_shop = 0

				IF shop_progress_clothes = 0

					IF IS_BUTTON_PRESSED PAD1 CROSS
						CLEAR_THIS_PRINT (SHOPNO)
						CLEAR_THIS_PRINT (CLTHNO2)
						CLEAR_THIS_PRINT (CLTHNO1)
						flag_bought_item_already_shops = 0		
						flag_no_money_shops = 0
						flag_player_owned_item_shops = 0
						flag_bought_something_clothes = 0
						shop_progress_clothes = 1
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 TRIANGLE
						
						CLEAR_THIS_PRINT (SHOPNO)
						CLEAR_THIS_PRINT (CLTHNO2)
						CLEAR_THIS_PRINT (CLTHNO1)
						flag_bought_item_already_shops = 0
						flag_no_money_shops = 0
						flag_player_owned_item_shops = 0
						flag_bought_something_clothes = 0

						GOSUB fill_arrays_for_clothes_info // need to recalulate the menu's again for hilight

						IF cost_menu_drawn_shops = 1
							DELETE_MENU cost_menu_shops
							CLEAR_HELP
							cost_menu_drawn_shops = 0
						ENDIF
						
						IF player_in_changeroom_clothes = 0
						
							shop_progress_clothes = 3

						ELSE

							IF bought_menu_drawn_shops = 1
								DELETE_MENU bought_menu_shops
								CLEAR_HELP
								bought_menu_drawn_shops = 0
							ENDIF
						
							shop_progress_clothes = 0
							flag_clothes = 6
						ENDIF

					ENDIF


				ENDIF

				IF shop_progress_clothes < 2 
					GOSUB display_price_name_text_clothes
				ENDIF
				
				// ************************** PLAYER HAS PRESSED CROSS TO BUY ITEM ****************		
				IF shop_progress_clothes = 1		
					
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS				

						STORE_SCORE player1 players_money
													
						IF players_money >= clothes_price

							IF NOT player_item_texture_shops = model_index 

								IF cost_menu_drawn_shops = 1
									DELETE_MENU cost_menu_shops
									CLEAR_HELP
									cost_menu_drawn_shops = 0
								ENDIF	

								BUY_ITEM model_index
								flag_bought_something_clothes = 1
								SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_GIVE_PRODUCT sample_name_shops
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY

								GOSUB bought_text_clothes

								GOSUB savehouse_clothes_check 
								 
								STORE_CLOTHES_STATE
								BUILD_PLAYER_MODEL player1

								GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops
								
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Buy CLOTHES 4.0 FALSE FALSE FALSE TRUE -1
								return_animation_time_clothes = 0.0						

								shop_progress_clothes = 2

							ELSE
									
								IF flag_bought_item_already_shops = 0
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
									SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_BOUGHT_ENOUGH sample_name_shops
									PRINT_NOW (CLTHNO2) 5000 1 //"You have already bought this item!"
									shop_progress_clothes = 0
									flag_bought_item_already_shops = 1
								ENDIF

							ENDIF

						ELSE

							IF flag_no_money_shops = 0
						  		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
								SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_NO_MONEY sample_name_shops
								PRINT_NOW (SHOPNO) 5000 1 //"You don't have enough money to buy this."
								shop_progress_clothes = 0
								flag_no_money_shops = 1
							ENDIF

						ENDIF

					ENDIF
				ENDIF

				// ******************** WAITING FOR BUY ANIMATION TO FINISH ***********************
				IF shop_progress_clothes = 2	
					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Buy
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Buy return_animation_time_clothes
					ENDIF

					IF return_animation_time_clothes = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Loop CLOTHES 1000.0 TRUE FALSE FALSE FALSE -1
						return_animation_time_clothes = 0.0
						shop_progress_clothes = 3
					ENDIF
				ENDIF

				// ******************* PLAYER HAS PRESSED TRIANGLE TO QUIT TO MENU 2 **************
				IF shop_progress_clothes = 3		

					// player in view mode then decides to change clothes

					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer CLO_Pose_Out CLOTHES 4.0 FALSE FALSE FALSE TRUE -1
					return_animation_time_clothes = 0.0						

					IF DOES_OBJECT_EXIST changing_room_door
						PLAY_OBJECT_ANIM changing_room_door CLO_Pose_Out_O CLOTHES 1000.0 FALSE TRUE
					ENDIF

					shop_progress_clothes = 4
				ENDIF

				IF shop_progress_clothes = 4	//	Waiting for anim of walking back into changing room to finish

   					IF IS_CHAR_PLAYING_ANIM scplayer CLO_Pose_Out
						GET_CHAR_ANIM_CURRENT_TIME scplayer CLO_Pose_Out return_animation_time_clothes
					ENDIF

					// player walks back into changing room
					IF return_animation_time_clothes = 1.0
						RESTORE_CLOTHES_STATE
						BUILD_PLAYER_MODEL player1
						STORE_CLOTHES_STATE

						IF bought_menu_drawn_shops = 1
							DELETE_MENU bought_menu_shops
							CLEAR_HELP
							bought_menu_drawn_shops = 0
						ENDIF

						IF sub_menu_drawn_shops = 1
							DELETE_MENU sub_menu_shops
							CLEAR_HELP
							sub_menu_drawn_shops = 0
						ENDIF

						IF cost_menu_drawn_shops = 1
							DELETE_MENU cost_menu_shops
							CLEAR_HELP
							cost_menu_drawn_shops = 0
						ENDIF

						player_in_changeroom_clothes = 1

						IF flag_bought_something_clothes = 1
							shop_progress_clothes = 0
							flag_clothes = 5

							IF main_menu_drawn_shops = 0

								IF shop_progress_clothes < 1
									GOSUB print_cloth_menu_oncreen_text
								ENDIF

							ENDIF

						ELSE
							shop_progress_clothes = 0
							flag_clothes = 6

							IF sub_menu_drawn_shops = 0
								GOSUB fill_arrays_for_clothes_info // need to recalulate the menu's again for hilight
								GOSUB print_clothes_oncreen_text2
							ENDIF
							
						ENDIF
						
					ENDIF

				ENDIF

			ENDIF	// flag_clothes = 7

			IF flag_clothes = 8

				IF IS_CHAR_DEAD shop_keep_clothes			

			 		GOSUB clothes_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_clothes_shop
						GOSUB clothes_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_clothes_inner
				 	 
				ENDIF // end of shop keep dead


				IF shopkeeper_attacked_clothes = 0

					IF IS_CHAR_SHOOTING scplayer
					OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_clothes
					OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_clothes WEAPONTYPE_ANYWEAPON
					OR iSetClothesPanic = 1
						SET_CHAR_SAY_CONTEXT shop_keep_clothes CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shops
						TASK_HANDS_UP shop_keep_clothes -2
						shopkeeper_attacked_clothes = 1
					ENDIF

				ENDIF

				IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer clothes_locateX clothes_locateY clothes_locateZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop

					main_menu_drawn_shops = 0
					sub_menu_drawn_shops = 0
					cost_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0

					shop_main_item_picked_shops = 0						  
					shop_sub_item_picked_shops = 0
																				
					flag_clothes = 2
					shop_progress_clothes = 0
					flag_bought_item_already_shops = 0
					flag_no_money_shops = 0
					flag_bought_something_clothes = 0

					blob_flag_shop = 1
					
				ENDIF

			ENDIF // flag_clothes = 8
			
		ELSE	
			GOSUB clothes_cleanup_big
		ENDIF // string empty
		
	ELSE
		GOSUB clothes_cleanup_big
	ENDIF // player playing							
								
GOTO shop_clothes_inner   		 


clothes_cleanup_small:

	IF flag_clothes >= 1
		CLEAR_HELP	
	ENDIF

	IF main_menu_drawn_shops = 1
		DELETE_MENU main_menu_shops
		CLEAR_HELP
		main_menu_drawn_shops = 0
	ENDIF

	IF sub_menu_drawn_shops = 1
		DELETE_MENU sub_menu_shops
		CLEAR_HELP
		sub_menu_drawn_shops = 0
	ENDIF

	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops
		CLEAR_HELP
		bought_menu_drawn_shops = 0
	ENDIF

	IF cost_menu_drawn_shops = 1
		DELETE_MENU cost_menu_shops
		CLEAR_HELP
		cost_menu_drawn_shops = 0
	ENDIF
	
	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (CLTHNO2)
	CLEAR_THIS_PRINT (CLTHNO1)
	
	main_menu_drawn_shops = 0
	sub_menu_drawn_shops = 0
	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0

	shop_main_item_picked_shops = 0
	shop_sub_item_picked_shops = 0

	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0

	flag_player_owned_item_shops = 0

	shop_progress_clothes = 0

	player_in_changeroom_clothes = 0
	flag_bought_something_clothes = 0

	flag_clothes = 2
   
RETURN

clothes_cleanup_big:

	IF main_menu_drawn_shops = 1
		DELETE_MENU main_menu_shops
		CLEAR_HELP
		main_menu_drawn_shops = 0
	ENDIF

	IF sub_menu_drawn_shops = 1
		DELETE_MENU sub_menu_shops
		CLEAR_HELP
		sub_menu_drawn_shops = 0
	ENDIF

	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops
		CLEAR_HELP
		bought_menu_drawn_shops = 0
	ENDIF

	IF cost_menu_drawn_shops = 1
		DELETE_MENU cost_menu_shops
		CLEAR_HELP
		cost_menu_drawn_shops = 0
	ENDIF


	IF flag_clothes >= 1
		CLEAR_HELP
	ENDIF

	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (CLTHNO2)
	CLEAR_THIS_PRINT (CLTHNO1)

	player_in_changeroom_clothes = 0

	main_menu_drawn_shops = 0
	sub_menu_drawn_shops = 0
	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0

	shop_main_item_picked_shops = 0
	shop_sub_item_picked_shops = 0
	   
	blob_flag_shop = 1

	flag_player_use_changing_room = 0

	shopkeeper_attacked_clothes = 0

	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0
	flag_player_owned_item_shops = 0
	flag_bought_something_clothes = 0
	
	shop_progress_clothes = 0
	flag_clothes = 0

   	DELETE_OBJECT changing_room_door

	MARK_MODEL_AS_NO_LONGER_NEEDED shop_door_model_clothes 

	DELETE_CHAR shop_keep_clothes

	MARK_MODEL_AS_NO_LONGER_NEEDED shopkeeper_model_shops

	USE_TEXT_COMMANDS FALSE

	DISPLAY_RADAR TRUE

	SHOW_UPDATE_STATS TRUE

	SET_MINIGAME_IN_PROGRESS FALSE

	CLEAR_MISSION_AUDIO 4

	IF IS_PLAYER_PLAYING player1
		SHUT_CHAR_UP scplayer FALSE
	ENDIF

//	SET_PLAYER_IS_IN_STADIUM FALSE

	TERMINATE_THIS_SCRIPT
	
RETURN

// used to set the correct items into slots for the shop
find_which_clothes_shop_load:

	IF shop_main_item_picked_shops = 0 // Torso
	
		area_looked_for_shops = 0

		area_to_look_at_shops = CLOTHES_TEX_SHIRT

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_clothes_info

		 
		$name_of_anim_clothes = CLO_Pose_Torso

	ENDIF

	IF shop_main_item_picked_shops = 1 // Legs

		area_looked_for_shops = 2

		area_to_look_at_shops = CLOTHES_TEX_TROUSERS

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_clothes_info

		$name_of_anim_clothes = CLO_Pose_Legs
		
	ENDIF
	
	IF shop_main_item_picked_shops = 2 // Feet

		area_looked_for_shops = 3

		area_to_look_at_shops =	CLOTHES_TEX_SHOES

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_clothes_info


		$name_of_anim_clothes = CLO_Pose_Shoes 

	ENDIF
	
	IF shop_main_item_picked_shops = 3 // CHAINS

		area_looked_for_shops = 13

		area_to_look_at_shops = CLOTHES_TEX_NECKLACE

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_clothes_info
		 
		$name_of_anim_clothes = CLO_Pose_Torso 

	ENDIF
	
	IF shop_main_item_picked_shops = 4 // WATCHES

		area_looked_for_shops = 14

		area_to_look_at_shops = CLOTHES_TEX_WATCH

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_clothes_info
		 
		$name_of_anim_clothes = CLO_Pose_Watch 

	ENDIF
	
	IF shop_main_item_picked_shops = 5 // SHADES

		area_looked_for_shops = 15

		area_to_look_at_shops = CLOTHES_TEX_GLASSES

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_clothes_info
		 
		$name_of_anim_clothes = CLO_Pose_Hat 

	ENDIF
	
	IF shop_main_item_picked_shops = 6 // HATS

		area_looked_for_shops = 16

		area_to_look_at_shops = CLOTHES_TEX_HAT

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops
		
		GOSUB fill_arrays_for_clothes_info
		 
		$name_of_anim_clothes = CLO_Pose_Hat 

	ENDIF 	

RETURN

// fills in the arrays
fill_arrays_for_clothes_info:

item_no_shops = 0
number_of_clothes_in_area = 0

WHILE item_no_shops < number_of_clothes_in_shop

	GET_ITEM_IN_SHOP item_no_shops model_index
	GET_SHOPPING_EXTRA_INFO model_index 0 modelid_shops
	GET_SHOPPING_EXTRA_INFO model_index 1 component_shops

	IF component_shops = area_looked_for_shops 
		item_model_index[number_of_clothes_in_area] = model_index
		
		GET_NAME_OF_ITEM model_index $clothes_name
		GET_PRICE_OF_ITEM model_index clothes_price
		
		item_price[number_of_clothes_in_area] = clothes_price  
		$item_text_label[number_of_clothes_in_area] = $clothes_name
		item_modelid[number_of_clothes_in_area] = modelid_shops  
		item_component[number_of_clothes_in_area] = component_shops

		IF player_item_texture_shops = model_index
			item_hilight_shops[number_of_clothes_in_area] = 0
		ELSE

			IF HAS_PLAYER_BOUGHT_ITEM model_index
				item_hilight_shops[number_of_clothes_in_area] = 1
			ELSE	 
				item_hilight_shops[number_of_clothes_in_area] = 2
			ENDIF

		ENDIF 
						
		++ number_of_clothes_in_area

	ENDIF
	
	++ item_no_shops
			
ENDWHILE

temp_var_shops = number_of_clothes_in_area 

WHILE temp_var_shops < max_number_allowed_in_menu_shops

	item_model_index[temp_var_shops] = -1
		
	item_price[temp_var_shops] = 0
	  
	$item_text_label[temp_var_shops] = DUMMY
		
	++ temp_var_shops 

ENDWHILE 
 	
RETURN

// displays the menu text
print_cloth_menu_oncreen_text:

 	IF main_menu_drawn_shops = 0
	    
		PRINT_HELP_FOREVER CLOTHA

		$ITEM1 = TORSO
		$ITEM2 = LEGS
		$ITEM3 = FEET
		$ITEM4 = CHAINS
		$ITEM5 = WATCHES
		$ITEM6 = SHADES
		$ITEM7 = HATS
		$ITEM8 = DUMMY
		$ITEM9 = DUMMY
		$ITEM10 = DUMMY
		$ITEM11 = DUMMY
		$ITEM12 = DUMMY
		
		// Create and populate the main menu with the list of body parts.

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		SET_MENU_COLUMN_ORIENTATION main_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN main_menu_shops 0 DUMMY $ITEM1 $ITEM2 $ITEM3 $ITEM4 $ITEM5 $ITEM6 $ITEM7 $ITEM8 $ITEM9 $ITEM10 $ITEM11 $ITEM12 
		
		main_menu_drawn_shops = 1
	ENDIF

RETURN


// Second menu for showing all the items you can buy
print_clothes_oncreen_text2:

	IF sub_menu_drawn_shops = 0
	    
		PRINT_HELP_FOREVER CLOTHB
		
		// Create and populate the inner menu with available clothes.

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU CLOTCHO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU CLOTCHO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		SET_MENU_COLUMN_ORIENTATION sub_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN sub_menu_shops 0 DUMMY $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11] 
		
		temp_var_shops = 0
		WHILE temp_var_shops < number_of_clothes_in_area

			IF item_hilight_shops[temp_var_shops] = 0
				HIGHLIGHT_MENU_ITEM sub_menu_shops temp_var_shops FALSE
				ACTIVATE_MENU_ITEM sub_menu_shops temp_var_shops FALSE
			ELSE
				IF item_hilight_shops[temp_var_shops] = 1
					HIGHLIGHT_MENU_ITEM sub_menu_shops temp_var_shops TRUE
				ELSE
					HIGHLIGHT_MENU_ITEM sub_menu_shops temp_var_shops FALSE
					ACTIVATE_MENU_ITEM sub_menu_shops temp_var_shops TRUE
				ENDIF
			ENDIF
			++temp_var_shops
		ENDWHILE

		sub_menu_drawn_shops = 1
	ENDIF

RETURN

// displays the text for the cost and name of item
display_price_name_text_clothes:
	IF cost_menu_drawn_shops = 0
		PRINT_HELP_FOREVER CLOTHC
		CREATE_MENU CLOTCHO 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN cost_menu_shops 0 CLOTCHO $clothes_name DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 1 FO_RIGHT 
		SET_MENU_COLUMN cost_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_ITEM_WITH_NUMBER cost_menu_shops 1 0 DOLLAR clothes_price 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 1 46 
		cost_menu_drawn_shops = 1
	ENDIF
RETURN


bought_text_clothes:
	IF bought_menu_drawn_shops = 0
		CREATE_MENU CLOTCHO 29.0 25.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops
		SET_MENU_COLUMN_ORIENTATION bought_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN bought_menu_shops 0 CLOTCHO BOUGHT DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN_ORIENTATION bought_menu_shops 1 FO_RIGHT 
		SET_MENU_COLUMN bought_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_ITEM_WITH_NUMBER bought_menu_shops 1 0 DOLLAR clothes_price 
		SET_MENU_COLUMN_WIDTH bought_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH bought_menu_shops 1 46 
		bought_menu_drawn_shops = 1
	ENDIF
RETURN

// checks to see what shop you have purchased from
savehouse_clothes_check:

IF flag_bought_from_binco = 0

	IF $shop_name = cschp
		flag_bought_from_binco = 1
	ENDIF
ENDIF

IF flag_bought_from_prolapse = 0

	IF $shop_name = cssprt
		flag_bought_from_prolapse = 1
	ENDIF
ENDIF

IF flag_bought_from_suburban = 0

	IF $shop_name = lacs1
		flag_bought_from_suburban = 1
	ENDIF
ENDIF

IF flag_bought_from_zip = 0

	IF $shop_name = clothgp
		flag_bought_from_zip = 1
	ENDIF
ENDIF

IF flag_bought_from_victim = 0

	IF $shop_name = csdesgn
		flag_bought_from_victim = 1
	ENDIF
ENDIF

IF flag_bought_from_didiessachs = 0

	IF $shop_name = csexl
		flag_bought_from_didiessachs = 1
	ENDIF
ENDIF

RETURN

MISSION_END

}
