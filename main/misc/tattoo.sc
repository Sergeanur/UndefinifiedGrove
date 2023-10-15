MISSION_START

{

// new proper menus
VAR_FLOAT shop_keep_tattooX shop_keep_tattooY shop_keep_tattooZ shop_keep_heading_tattoo 

VAR_TEXT_LABEL $tattoo_name // Used to display the name of the garment

VAR_INT shop_keep_tattoo

VAR_INT tattoo_price
											   
VAR_TEXT_LABEL $stored_tattoo_shop

VAR_INT shopkeeper_attacked_tattoo

VAR_INT player_in_view_mode_tattoo

// used to create the changing room door to animate
VAR_INT tattoo_needle

VAR_INT flag_tattoo

VAR_FLOAT tattoo_needleX tattoo_needleY tattoo_needleZ tattoo_needle_heading

VAR_FLOAT return_animation_time_tattoo object_animation_time_tattoo shop_keep_animation_time_tattoo tattoo_heading

VAR_FLOAT cam_point_tattooX cam_point_tattooY cam_point_tattooZ

VAR_INT shop_progress_tattoo // used to say what stage you are in in section 4 onwards

VAR_INT tex_tattoo model_tattoo tex_tattoo2 model_tattoo2 // used to store player clothes so can make him have torso for tattoo

// VAR_INT clothesId_tattoo

// Animation stuff
VAR_TEXT_LABEL16 $name_of_player_in_anim_tattoo $name_of_shop_in_anim_tattoo $name_of_object_in_anim_tattoo
VAR_TEXT_LABEL16 $name_of_player_pose_anim_tattoo $name_of_shop_pose_anim_tattoo $name_of_object_pose_anim_tattoo
VAR_TEXT_LABEL16 $name_of_player_out_anim_tattoo $name_of_shop_out_anim_tattoo $name_of_object_out_anim_tattoo

VAR_INT stored_item_picked_shops // used to check what player was doing

VAR_INT number_of_tats_in_shop number_of_tattoos_in_area




number_of_tats_in_shop = 0
number_of_tattoos_in_area = 0
area_looked_for_shops = 0
price_counter = 0
item_no_shops = 0
temp_var_shops = 0

shop_progress_tattoo = 0

shop_keep_tattooX = 0.0
shop_keep_tattooY = 0.0
shop_keep_tattooZ = 0.0
shop_keep_heading_tattoo = 0.0

tattoo_heading = 0.0

return_animation_time_tattoo = 0.0
shop_keep_animation_time_tattoo = 0.0
object_animation_time_tattoo = 0.0

tattoo_needleX = 0.0
tattoo_needleY = 0.0
tattoo_needleZ = 0.0
tattoo_needle_heading = 0.0

flag_tattoo = 0

player_in_view_mode_tattoo = 0

shopkeeper_attacked_tattoo = 0

main_menu_drawn_shops = 0
sub_menu_drawn_shops = 0
stored_item_picked_shops = 0
shop_main_item_picked_shops = 0
shop_sub_item_picked_shops = 0

cost_menu_drawn_shops = 0
bought_menu_drawn_shops = 0

flag_bought_item_already_shops = 0
flag_no_money_shops = 0

VAR_INT menu_two_item_hilighted previous_menu_two_item_hilighted

menu_two_item_hilighted = 0 
previous_menu_two_item_hilighted = 500

VAR_INT temp_var_shops2
temp_var_shops2 = 0

VAR_INT number_of_tats_player_has
number_of_tats_player_has = 0

VAR_INT player_has_tattoo_in_area[9]

VAR_INT flag_in_remove_tattoo_section
flag_in_remove_tattoo_section = 0

VAR_INT remove_tats_menu_drawn remove_menu_tattoo
remove_tats_menu_drawn = 0

VAR_INT menu_item_no_tats
menu_item_no_tats = 0

VAR_INT remove_menu_item_selected
remove_menu_item_selected = 0

CONST_INT TATTOO_REMOVAL_PRICE 400

// Offset stuff for the shops
CONST_FLOAT keep_true_offX -203.318 // new as player is not infront of desk anymore
CONST_FLOAT	keep_true_offY -7.062
CONST_FLOAT	keep_true_offZ 1001.28

keep_offX = 0.0
keep_offY = 0.0
keep_offZ = 0.0

CONST_FLOAT shop_keep_true_tattooX -201.668
CONST_FLOAT	shop_keep_true_tattooY -6.217
CONST_FLOAT	shop_keep_true_tattooZ 1001.28

shop_keep_tattooX = 0.0
shop_keep_tattooY = 0.0
shop_keep_tattooZ = 0.0

CONST_FLOAT cam_true_offx -203.2045   // -202.8717 // -202.9453   
CONST_FLOAT	cam_true_offy -5.3316  // -5.9529 // -5.9943
CONST_FLOAT	cam_true_offz 1002.4316  // 1002.2184 // 1002.1299

cam_offx = 0.0   
cam_offy = 0.0
cam_offz = 0.0

CONST_FLOAT	cam_point_true_tattooX -202.2993     // -201.8831 // -201.9579
CONST_FLOAT	cam_point_true_tattooY -5.7376   // -5.9515 // -5.9670
CONST_FLOAT	cam_point_true_tattooZ 1002.3063 // 1002.3689 // 1002.2856

cam_point_tattooX = 0.0
cam_point_tattooY = 0.0
cam_point_tattooZ = 0.0

VAR_FLOAT tattoo_offsetX tattoo_offsetY tattoo_offsetZ
tattoo_offsetX = 0.0
tattoo_offsetY = 0.0 
tattoo_offsetZ = 0.0

// requests the models
shopkeeper_model_shops = BMYTATT

REQUEST_MODEL TATTOO_KIT

LOAD_TEXTURE_DICTIONARY LD_TATT

REQUEST_MODEL shopkeeper_model_shops

LOAD_MISSION_AUDIO 4 SOUND_TATTOO

LOAD_ALL_MODELS_NOW
// end of requesting models


SET_DEATHARREST_STATE OFF
 
SCRIPT_NAME	TATTO

shop_tattoo_inner:
    
	WAIT 0
	
	IF IS_PLAYER_PLAYING player1

		IF NOT IS_STRING_EMPTY $shop_name

			IF flag_tattoo > 0

				IF HAS_LANGUAGE_CHANGED
					CLEAR_HELP
					DELETE_MENU bought_menu_shops
					DELETE_MENU sub_menu_shops
					DELETE_MENU main_menu_shops
					DELETE_MENU remove_menu_tattoo
					main_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0
					sub_menu_drawn_shops = 0
					remove_tats_menu_drawn = 0
					GET_CURRENT_LANGUAGE current_Language
				ENDIF
			ENDIF
		
			IF flag_tattoo = 0

				IF $shop_name = TATTO2 // Tattooists

					tattoo_offsetX = 0.0
					tattoo_offsetY = 0.0 
					tattoo_offsetZ = 0.0
				
					tattoo_heading = 125.0				
					shop_keep_heading_tattoo = 155.0

					$stored_tattoo_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_tats_in_shop
										
					flag_tattoo = 1
				ENDIF

				IF $shop_name = TATTOO

					tattoo_offsetX = 0.0
					tattoo_offsetY = -18.266 // 279 
					tattoo_offsetZ = 0.0
				
					tattoo_heading = 125.0				
					shop_keep_heading_tattoo = 155.0

					$stored_tattoo_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_tats_in_shop
										
					flag_tattoo = 1
				ENDIF

				IF $shop_name = TATTO3
				 
					tattoo_offsetX = 0.0
					tattoo_offsetY = -35.32 // 333 
					tattoo_offsetZ = 0.0
				
					tattoo_heading = 125.0				
					shop_keep_heading_tattoo = 155.0

					$stored_tattoo_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP number_of_tats_in_shop
										
					flag_tattoo = 1

				ENDIF 
				
			ENDIF // flag_tattoo  = 0

			IF flag_tattoo = 1

				IF shop_progress_tattoo = 0
					
					keep_offX = keep_true_offX + tattoo_offsetX
					keep_offY = keep_true_offY + tattoo_offsetY
					keep_offZ = keep_true_offZ + tattoo_offsetZ
					
					shop_keep_tattooX = shop_keep_true_tattooX + tattoo_offsetX
					shop_keep_tattooY = shop_keep_true_tattooY + tattoo_offsetY
					shop_keep_tattooZ = shop_keep_true_tattooZ + tattoo_offsetZ
					
					cam_offx = cam_true_offx + tattoo_offsetX   
					cam_offy = cam_true_offy + tattoo_offsetY
					cam_offz = cam_true_offz + tattoo_offsetZ
										
					cam_point_tattooX = cam_point_true_tattooX + tattoo_offsetX
					cam_point_tattooY = cam_point_true_tattooY + tattoo_offsetY
					cam_point_tattooZ = cam_point_true_tattooZ + tattoo_offsetZ
					
					// needle object
					tattoo_needleX = shop_keep_tattooX 
					tattoo_needleY = shop_keep_tattooY 
					tattoo_needleZ = shop_keep_tattooZ + 1.0
					tattoo_needle_heading = shop_keep_heading_tattoo
 					
					USE_TEXT_COMMANDS TRUE

					SHOW_UPDATE_STATS FALSE
									
				  	CREATE_CHAR PEDTYPE_CIVMALE shopkeeper_model_shops shop_keep_tattooX shop_keep_tattooY shop_keep_tattooZ shop_keep_tattoo
					SET_CHAR_ONLY_DAMAGED_BY_PLAYER shop_keep_tattoo TRUE
					SET_CHAR_DECISION_MAKER shop_keep_tattoo DM_PED_EMPTY
					SET_CHAR_HEADING shop_keep_tattoo shop_keep_heading_tattoo	
					 
					SET_CHAR_USES_COLLISION_CLOSEST_OBJECT_OF_TYPE shop_keep_tattooX shop_keep_tattooY shop_keep_tattooZ 1.5 cj_barstool FALSE shop_keep_tattoo	 				
	 									
					shopkeeper_attacked_tattoo = 0

					CREATE_OBJECT_NO_OFFSET TATTOO_KIT tattoo_needleX tattoo_needleY tattoo_needleZ tattoo_needle
				   	SET_OBJECT_HEADING tattoo_needle tattoo_needle_heading
				   	ATTACH_CHAR_TO_OBJECT shop_keep_tattoo tattoo_needle 0.0 0.0 0.0 0 180.0 WEAPONTYPE_UNARMED 
				   	
					blob_flag_shop = 1

					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_idle_loop_t TATTOOS 4.0 TRUE FALSE FALSE FALSE -1
					shop_keep_animation_time_tattoo = 0.0

					TIMERA = 0
					SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_TAKE_A_SEAT sample_name_shops
					shop_progress_tattoo = 1

				ENDIF

				IF shop_progress_tattoo = 1

					IF TIMERA >= 300
						 					  			
						IF DOES_OBJECT_EXIST tattoo_needle
							PLAY_OBJECT_ANIM tattoo_needle tat_idle_loop_o TATTOOS 4.0 TRUE FALSE
							object_animation_time_tattoo = 0.0
						ENDIF

						shop_progress_tattoo = 0
						flag_tattoo = 2
						
					ENDIF
					
				ENDIF 
				 				
			ENDIF // flag_tattoo = 1										

			IF flag_tattoo = 2
			
				GET_LOADED_SHOP $shop_name
															
				IF NOT $shop_name = $stored_tattoo_shop
					GOSUB tattoo_cleanup_big
				ENDIF

				IF NOT IS_CHAR_DEAD shop_keep_tattoo

					IF shopkeeper_attacked_tattoo = 0

						IF IS_CHAR_SHOOTING scplayer
						OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_tattoo
						OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_tattoo WEAPONTYPE_ANYWEAPON

							SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shops

						  	IF DOES_OBJECT_EXIST tattoo_needle
						  		PLAY_OBJECT_ANIM tattoo_needle tat_drop_o TATTOOS 1000.0 FALSE TRUE
								object_animation_time_tattoo = 0.0
						  	ENDIF 

							TASK_HANDS_UP shop_keep_tattoo -2
							shopkeeper_attacked_tattoo = 1
						ELSE
							
							blob_flag_shop = 1						 						   										
							IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop

								IF CAN_PLAYER_START_MISSION player1
									flag_tattoo = 3
								ENDIF

							ENDIF

						ENDIF

					ELSE

						GOSUB tattoo_cleanup_small
					
						GET_LOADED_SHOP $shop_name
										
						IF NOT $shop_name = $stored_tattoo_shop
							GOSUB tattoo_cleanup_big
					 	ENDIF
					 	
					 	GOTO shop_tattoo_inner

					ENDIF

				ELSE

					GOSUB tattoo_cleanup_small
					
					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_tattoo_shop
						GOSUB tattoo_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_tattoo_inner

				ENDIF

			ENDIF // flag_tattoo = 2

			IF flag_tattoo = 3

				IF  IS_PLAYER_PLAYING player1
					SET_PLAYER_CONTROL player1 OFF
					SET_MINIGAME_IN_PROGRESS TRUE
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
					GET_CHAR_COORDINATES scplayer player_x player_y player_z
					CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					CLEAR_AREA player_x player_y player_z 1.0 TRUE
					DISPLAY_RADAR FALSE
					TIMERA = 0
					shop_progress_tattoo = 0
					flag_tattoo = 4
				ELSE
					GOSUB tattoo_cleanup_big
					GOTO shop_tattoo_inner
				ENDIF 

			ENDIF // flag_tattoo = 3

			// ***************** SETTING UP CAMERA AND CHECK PLAYER POSITION *********************
			IF flag_tattoo = 4

				IF IS_CHAR_DEAD shop_keep_tattoo			

			 		GOSUB tattoo_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_tattoo_shop
						GOSUB tattoo_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_tattoo_inner
				 	 
				ENDIF // end of shop keep dead


				IF shop_progress_tattoo = 0
 				
					IF TIMERA >= 300																		
						 
						SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
														   
						SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer keep_offX keep_offY keep_offZ 
						SET_CHAR_HEADING scplayer tattoo_heading
						GET_CHAR_COORDINATES scplayer player_x player_y player_z

						GET_CHAR_COORDINATES shop_keep_tattoo temp_shopX temp_shopY temp_shopZ
									
						IF NOT temp_shopX = shop_keep_tattooX 
						OR NOT temp_shopY = shop_keep_tattooY 
						OR NOT temp_shopZ = shop_keep_tattooZ
							SET_CHAR_COORDINATES shop_keep_tattoo shop_keep_tattooX shop_keep_tattooY shop_keep_tattooZ
							SET_CHAR_HEADING shop_keep_tattoo shop_keep_heading_tattoo
						ENDIF
						
						SET_FIXED_CAMERA_POSITION cam_offx cam_offy cam_offz 0.0 0.0 0.0 			
						POINT_CAMERA_AT_POINT cam_point_tattooX cam_point_tattooY cam_point_tattooZ JUMP_CUT 							  																																																							

						// stores the players clothes and gives him the torso
						STORE_CLOTHES_STATE
						GET_CLOTHES_ITEM player1 0 tex_tattoo model_tattoo

						GIVE_PLAYER_CLOTHES player1 0 0 17 // removes any special uniforms
						GIVE_PLAYER_CLOTHES player1 0 0 0 // gives player naked torso
						BUILD_PLAYER_MODEL player1
						STORE_CLOTHES_STATE 
						   	   							
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_sit_in_p TATTOOS 4.0 FALSE FALSE FALSE TRUE -1
	  					return_animation_time_tattoo = 0.0

						IF IS_CHAR_PLAYING_ANIM scplayer tat_sit_in_p
							GET_CHAR_ANIM_CURRENT_TIME scplayer tat_sit_in_p return_animation_time_tattoo
						ENDIF

					   	IF DOES_OBJECT_EXIST tattoo_needle
					  		PLAY_OBJECT_ANIM tattoo_needle tat_sit_in_o TATTOOS 4.0 FALSE TRUE
							object_animation_time_tattoo = 0.0
					  	ENDIF

						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_sit_in_t TATTOOS 4.0 FALSE FALSE FALSE TRUE -1
						shop_keep_animation_time_tattoo = 0.0

						shop_progress_tattoo = 1

					ENDIF

				ENDIF
			   	
			   	//	Waiting for walking into changing room anim to finish				
				IF shop_progress_tattoo = 1
				
					IF IS_CHAR_PLAYING_ANIM scplayer tat_sit_in_p
						GET_CHAR_ANIM_CURRENT_TIME scplayer tat_sit_in_p return_animation_time_tattoo
					ENDIF	
					 				
				    IF return_animation_time_tattoo = 1.0
				    
				    	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_sit_loop_p TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
		  				return_animation_time_tattoo = 0.0

						IF DOES_OBJECT_EXIST tattoo_needle
							PLAY_OBJECT_ANIM tattoo_needle tat_sit_loop_o TATTOOS 1000.0 TRUE FALSE
							object_animation_time_tattoo = 0.0
						ENDIF

						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_sit_loop_t TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
						shop_keep_animation_time_tattoo = 0.0
						
						SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_WHAT_WANT sample_name_shops
										    						   	  																	   									
						shop_progress_tattoo = 0
						flag_tattoo = 5
					ENDIF
					
				ENDIF

			ENDIF // flag_tattoo = 4
			
			// ************************************* FIRST MENU ***********************************
			// player to select the area the clothes are to go onto					
			IF flag_tattoo = 5		

				IF IS_CHAR_DEAD shop_keep_tattoo			

			 		GOSUB tattoo_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_tattoo_shop
						GOSUB tattoo_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_tattoo_inner
				 	 
				ENDIF // end of shop keep dead
				
				IF shop_progress_tattoo < 2
					GOSUB find_if_player_got_tats
			   		GOSUB print_tattoo_menu_oncreen_text
				ENDIF
							   									
				blob_flag_shop = 0

				IF shop_progress_tattoo = 0
				    
					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						shop_progress_tattoo = 1
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						shop_progress_tattoo = 4
					ENDIF

				ENDIF								
				
				// ***** PLAYER HAS PRESSED CROSS IN FIRST MENU TO SELECT POSITION OF TATTOO ******
				IF shop_progress_tattoo = 1
					
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

						GET_MENU_ITEM_ACCEPTED main_menu_shops shop_main_item_picked_shops
					    
						IF shop_main_item_picked_shops < 0
							shop_main_item_picked_shops = 0
						ENDIF
						
						IF shop_main_item_picked_shops = 9

							IF main_menu_drawn_shops = 1
								DELETE_MENU main_menu_shops
								CLEAR_HELP
								main_menu_drawn_shops = 0
							ENDIF 

							IF remove_tats_menu_drawn = 0
								GOSUB draw_remove_tats_menu
							ENDIF

							shop_progress_tattoo = 0
							flag_tattoo = 8

						ELSE
						 	GOSUB check_which_area_to_buy
							GOSUB find_which_tattoo_shop_load

							flag_in_remove_tattoo_section = 0

							GOSUB back_anim_start_tattoo
					   											   
						ENDIF

					ENDIF

				ENDIF

				IF shop_progress_tattoo = 2

					flag_in_remove_tattoo_section = 0

					GOSUB back_anim_playing_tattoo
										
				ENDIF

				IF shop_progress_tattoo = 3

				   	stored_item_picked_shops = shop_main_item_picked_shops
				
					IF main_menu_drawn_shops = 1
						CLEAR_HELP
						DELETE_MENU main_menu_shops
						main_menu_drawn_shops = 0
					ENDIF

					previous_menu_two_item_hilighted = 500

					flag_tattoo = 6
					shop_progress_tattoo = 0
 					
				ENDIF

				// ******************** PLAYER HAS PRESSED TRIANGLE IN FIRST MENU *****************
				
				IF shop_progress_tattoo = 4
					
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						
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

						shop_progress_tattoo = 5
					ENDIF
				ENDIF

				IF shop_progress_tattoo = 5
				
					flag_in_remove_tattoo_section = 0
					
					IF IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_loop_p
						
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_back_sit_out_p TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1
						return_animation_time_tattoo = 0.0

						shop_progress_tattoo = 6	  
						
					ELSE
						 
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_sit_out_p TATTOOS 1000.0 FALSE FALSE FALSE FALSE -1
						return_animation_time_tattoo = 0.0

						IF DOES_OBJECT_EXIST tattoo_needle
							PLAY_OBJECT_ANIM tattoo_needle tat_sit_out_o TATTOOS 1000.0 FALSE TRUE
							object_animation_time_tattoo = 0.0
						ENDIF
							
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_sit_out_t TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1	
						shop_keep_animation_time_tattoo = 0.0

						shop_progress_tattoo = 7

					ENDIF
		   				
    	   		ENDIF

				IF shop_progress_tattoo = 6
				
					IF IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_out_p
						GET_CHAR_ANIM_CURRENT_TIME scplayer tat_back_sit_out_p return_animation_time_tattoo
					ENDIF

					IF return_animation_time_tattoo = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_sit_out_p TATTOOS 1000.0 FALSE FALSE FALSE FALSE -1
						return_animation_time_tattoo = 0.0

						IF DOES_OBJECT_EXIST tattoo_needle
							PLAY_OBJECT_ANIM tattoo_needle tat_sit_out_o TATTOOS 1000.0 FALSE TRUE
							object_animation_time_tattoo = 0.0
						ENDIF
							
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_sit_out_t TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1
						SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_THANKS_FOR_CUSTOM sample_name_shops
						shop_keep_animation_time_tattoo = 0.0

	 					shop_progress_tattoo = 7
  					ENDIF

				ENDIF		   

				// ******************* WAITING FOR PLAYER TO GET OUT OF CHAIR *********************
				IF shop_progress_tattoo = 7

					IF IS_CHAR_PLAYING_ANIM scplayer tat_sit_out_p
						GET_CHAR_ANIM_CURRENT_TIME scplayer tat_sit_out_p return_animation_time_tattoo
					ENDIF

					IF IS_CHAR_PLAYING_ANIM shop_keep_tattoo tat_sit_out_t
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_tattoo tat_sit_out_t shop_keep_animation_time_tattoo
					ENDIF

					IF shop_keep_animation_time_tattoo = 1.0 			
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_idle_loop_t TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
						shop_keep_animation_time_tattoo = 0.0		    				
											
						IF DOES_OBJECT_EXIST tattoo_needle
 							PLAY_OBJECT_ANIM tattoo_needle tat_idle_loop_o TATTOOS 1000.0 TRUE FALSE
							object_animation_time_tattoo = 0.0
						ENDIF
					   	 
					ENDIF

					IF return_animation_time_tattoo = 1.0
						main_menu_drawn_shops = 0
						sub_menu_drawn_shops = 0
						shop_progress_tattoo = 8
					ENDIF

				ENDIF

				// * FINISHED QUITTING OUT OF FIRST MENU - HAVE TO WAIT FOR PLAYER TO LEAVE AREA *
				IF shop_progress_tattoo = 8
				
				  	GIVE_PLAYER_CLOTHES player1 tex_tattoo model_tattoo 0
				   	BUILD_PLAYER_MODEL player1
					STORE_CLOTHES_STATE
					
					CLEAR_THIS_PRINT (SHOPNO)
					CLEAR_THIS_PRINT (CLTHNO2) 
					
					SET_PLAYER_CONTROL player1 ON
					SET_MINIGAME_IN_PROGRESS FALSE
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT

					DISPLAY_RADAR TRUE

					IF IS_CHAR_PLAYING_ANIM shop_keep_tattoo tat_sit_out_t
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_tattoo tat_sit_out_t shop_keep_animation_time_tattoo
					ENDIF

					IF shop_keep_animation_time_tattoo = 1.0 			
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_idle_loop_t TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
						shop_keep_animation_time_tattoo = 0.0		    				
											
						IF DOES_OBJECT_EXIST tattoo_needle
 							PLAY_OBJECT_ANIM tattoo_needle tat_idle_loop_o TATTOOS 1000.0 TRUE FALSE
							object_animation_time_tattoo = 0.0
						ENDIF
					   	 
					ENDIF

					flag_tattoo = 7
				ENDIF

			ENDIF // flag_tattoo = 5
			
			// ********************************	SECOND MENU ***********************************
			// ************ Player selects what to view and buy *******************************
			IF flag_tattoo = 6

				IF IS_CHAR_DEAD shop_keep_tattoo			

			 		GOSUB tattoo_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_tattoo_shop
						GOSUB tattoo_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_tattoo_inner
				 	 
				ENDIF // end of shop keep dead

				blob_flag_shop = 0

				// stops the menu text darwing after player has pressed to view item
				IF shop_progress_tattoo < 1
					GOSUB print_tattoo_oncreen_text2
				ENDIF
																																		
				IF shop_progress_tattoo = 0

					GET_MENU_ITEM_SELECTED sub_menu_shops menu_two_item_hilighted
					
					IF menu_two_item_hilighted < 0
						menu_two_item_hilighted = 0
					ENDIF 

					IF NOT previous_menu_two_item_hilighted = menu_two_item_hilighted 
						GOSUB choose_sprite_tattoo 
						previous_menu_two_item_hilighted = menu_two_item_hilighted
					ENDIF				

					GOSUB draw_sprite_tattoo

					// Buy Item
				   	
					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						flag_no_money_shops = 0
						flag_bought_item_already_shops = 0
						bought_menu_drawn_shops = 0
						GOSUB draw_sprite_tattoo
						shop_progress_tattoo = 1
					ENDIF
					 				   
					// quit to menu 1						
					
					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						GOSUB fill_arrays_for_tattoo_info // need to recalculate the grey out stuff

						flag_no_money_shops = 0
						flag_bought_item_already_shops = 0
						bought_menu_drawn_shops = 0
						shop_progress_tattoo = 8
					ENDIF

					
				ENDIF
				
				// **************************** QUIT TO MENU 1 ************************************
				//	Player has pressed triangle in second menu							
				IF shop_progress_tattoo = 8	
						
				   	IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

						IF sub_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU sub_menu_shops
							sub_menu_drawn_shops = 0
						ENDIF

						GOSUB find_if_player_got_tats
						GOSUB print_tattoo_menu_oncreen_text 

					  	shop_progress_tattoo = 0
						flag_tattoo = 5

				   	ENDIF // triangle pressed

				ENDIF

				// ********************************** BUY ITEM ************************************
				// ************************** PLAYER HAS PRESSED CROSS TO BUY ITEM ****************		
				IF shop_progress_tattoo = 1
					GOSUB draw_sprite_tattoo
						
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

						GOSUB draw_sprite_tattoo

						GET_MENU_ITEM_ACCEPTED sub_menu_shops shop_sub_item_picked_shops
						
						IF shop_sub_item_picked_shops < 0
							shop_sub_item_picked_shops = 0
						ENDIF				

						model_index = item_model_index[shop_sub_item_picked_shops] 						
						tattoo_price = item_price[shop_sub_item_picked_shops]   
						// $tattoo_name = $item_text_label[shop_sub_item_picked_shops]
						 					   
						STORE_SCORE player1 players_money
   
						shop_progress_tattoo = 2

					ENDIF

				ENDIF

				// checks players money
				IF shop_progress_tattoo = 2

					GOSUB draw_sprite_tattoo
													
					IF players_money >= tattoo_price

						GOSUB draw_sprite_tattoo

						IF NOT player_item_texture_shops = model_index

							IF sub_menu_drawn_shops = 1
								DELETE_MENU sub_menu_shops
								CLEAR_HELP
								sub_menu_drawn_shops = 0
							ENDIF	
										
							SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_GIVE_PRODUCT sample_name_shops
							BUY_ITEM model_index

							REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY
							
							IF sub_menu_drawn_shops = 1
								DELETE_MENU sub_menu_shops
								CLEAR_HELP
								sub_menu_drawn_shops = 0
							ENDIF

							IF bought_menu_drawn_shops = 0
								IF IS_XBOX_VERSION
									CREATE_MENU TATTO 29.0 50.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops
								ELSE
									CREATE_MENU TATTO 29.0 25.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops
								ENDIF
								SET_MENU_COLUMN_ORIENTATION bought_menu_shops 0 FO_LEFT 
								SET_MENU_COLUMN bought_menu_shops 0 TATTO BOUGHT DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
								SET_MENU_COLUMN_ORIENTATION bought_menu_shops 1 FO_RIGHT 
								SET_MENU_COLUMN bought_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
								SET_MENU_ITEM_WITH_NUMBER bought_menu_shops 1 0 DOLLAR tattoo_price 
								SET_MENU_COLUMN_WIDTH bought_menu_shops 0 140 
								SET_MENU_COLUMN_WIDTH bought_menu_shops 1 46
								bought_menu_drawn_shops = 1
							ENDIF
							
							flag_in_remove_tattoo_section = 0
							GOSUB start_move_in_anim_tattoo
							
							shop_progress_tattoo = 3

						ELSE

							IF flag_bought_item_already_shops = 0
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
								PRINT_NOW (CLTHNO2) 5000 1 //"You have already bought this item!"
								shop_progress_tattoo = 0
								flag_bought_item_already_shops = 1
							ENDIF
						ENDIF

					ELSE

						IF flag_no_money_shops = 0
					 		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED			
							SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_NO_MONEY sample_name_shops
							PRINT_NOW (SHOPNO) 5000 1 //"You don't have enough money to buy this."
							shop_progress_tattoo = 0
							flag_no_money_shops = 1
						ENDIF

					ENDIF

				ENDIF

				// waiting for in anim to finish and play loop
				IF shop_progress_tattoo = 3
					flag_in_remove_tattoo_section = 0
					GOSUB play_move_in_anims_tattoo
				ENDIF

				IF shop_progress_tattoo = 4
					flag_in_remove_tattoo_section = 0
					GOSUB start_out_anims_tattoo	
					shop_progress_tattoo = 5
				ENDIF
				
				IF shop_progress_tattoo = 5
					flag_in_remove_tattoo_section = 0
					GOSUB finish_out_anims_tattoo					 					
				ENDIF

				IF shop_progress_tattoo = 6
				
					IF bought_menu_drawn_shops = 1
						DELETE_MENU bought_menu_shops
						bought_menu_drawn_shops = 0
					ENDIF


					shop_progress_tattoo = 0
					flag_tattoo = 5

					IF shop_progress_tattoo < 2
						GOSUB find_if_player_got_tats
				   		GOSUB print_tattoo_menu_oncreen_text
					ENDIF
					 				 
				ENDIF
					
			ENDIF // flag tattoo 6

						
				// ******************* PLAYER HAS PRESSED TRIANGLE TO QUIT TO MENU 2 **************
				
			IF flag_tattoo = 7

				IF IS_CHAR_DEAD shop_keep_tattoo			

			 		GOSUB tattoo_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_tattoo_shop
						GOSUB tattoo_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_tattoo_inner
				 	 
				ENDIF // end of shop keep dead


				IF shopkeeper_attacked_tattoo = 0

					IF IS_CHAR_SHOOTING scplayer
					OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_tattoo
					OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_tattoo WEAPONTYPE_ANYWEAPON

						SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shops
						
						IF DOES_OBJECT_EXIST tattoo_needle
						  	PLAY_OBJECT_ANIM tattoo_needle tat_drop_o TATTOOS 1000.0 FALSE TRUE
							object_animation_time_tattoo = 0.0
						ENDIF
						
						TASK_HANDS_UP shop_keep_tattoo -2
						shopkeeper_attacked_tattoo = 1
					ENDIF

				ENDIF

				IF IS_CHAR_PLAYING_ANIM shop_keep_tattoo tat_sit_out_t
					GET_CHAR_ANIM_CURRENT_TIME shop_keep_tattoo tat_sit_out_t shop_keep_animation_time_tattoo
				ENDIF

				IF shop_keep_animation_time_tattoo = 1.0 			
					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_idle_loop_t TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
					shop_keep_animation_time_tattoo = 0.0		    				
										
					IF DOES_OBJECT_EXIST tattoo_needle
							PLAY_OBJECT_ANIM tattoo_needle tat_idle_loop_o TATTOOS 1000.0 TRUE FALSE
						object_animation_time_tattoo = 0.0
					ENDIF
				   	 
				ENDIF

				IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop					
					flag_tattoo = 2
					shop_progress_tattoo = 0
					main_menu_drawn_shops = 0
					sub_menu_drawn_shops = 0
					stored_item_picked_shops = 0
					shop_main_item_picked_shops = 0
					shop_sub_item_picked_shops = 0
					remove_menu_item_selected = 0
					   
					cost_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0

					flag_bought_item_already_shops = 0
					flag_no_money_shops = 0

					CLEAR_THIS_PRINT (SHOPNO)
					CLEAR_THIS_PRINT (CLTHNO2)
					 
					blob_flag_shop = 1
				ENDIF

			ENDIF // flag_tattoo = 7

			IF flag_tattoo = 8

				IF IS_CHAR_DEAD shop_keep_tattoo			

			 		GOSUB tattoo_cleanup_small

					GET_LOADED_SHOP $shop_name
									
					IF NOT $shop_name = $stored_tattoo_shop
						GOSUB tattoo_cleanup_big
				 	ENDIF
				 	
				 	GOTO shop_tattoo_inner
				 	 
				ENDIF // end of shop keep dead

				IF shop_progress_tattoo < 1
	                IF remove_tats_menu_drawn = 0
						GOSUB draw_remove_tats_menu
					ENDIF
				ENDIF
	
				IF shop_progress_tattoo = 0
				    
					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						flag_no_money_shops = 0
						shop_progress_tattoo = 1
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT	
						flag_no_money_shops = 0
						shop_progress_tattoo = 2
					ENDIF
									
				ENDIF
				
				IF shop_progress_tattoo = 1
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

						IF remove_tats_menu_drawn = 1
							DELETE_MENU remove_menu_tattoo
							CLEAR_HELP
							remove_tats_menu_drawn = 0
						ENDIF

						IF main_menu_drawn_shops = 0
							GOSUB find_if_player_got_tats
							GOSUB print_tattoo_menu_oncreen_text 
						ENDIF

						flag_no_money_shops = 0

					  	shop_progress_tattoo = 0
						flag_tattoo = 5
						
					ENDIF
					
				ENDIF
				
				IF shop_progress_tattoo = 2

					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

						STORE_SCORE player1 players_money

						IF players_money >= TATTOO_REMOVAL_PRICE 
							
							GET_MENU_ITEM_ACCEPTED remove_menu_tattoo remove_menu_item_selected
 
							IF remove_menu_item_selected < 0
								remove_menu_item_selected = 0
							ENDIF

							IF remove_tats_menu_drawn = 1
								DELETE_MENU remove_menu_tattoo
								CLEAR_HELP
								remove_tats_menu_drawn = 0
							ENDIF	

							area_to_look_at_shops = player_has_tattoo_in_area[remove_menu_item_selected] 

							GOSUB find_which_tattoo_shop_load

							ADD_SCORE player1 -400
							SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_REMOVE_TATTOO sample_name_shops
							REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY

							shop_progress_tattoo = 3
							
						ELSE

							IF flag_no_money_shops = 0
						 		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED			
								SET_CHAR_SAY_CONTEXT shop_keep_tattoo CONTEXT_GLOBAL_NO_MONEY sample_name_shops
								PRINT_NOW (SHOPNO) 5000 1 //"You don't have enough money to buy this."
								shop_progress_tattoo = 0
								flag_no_money_shops = 1
							ENDIF
						
						ENDIF 
						
					ENDIF
									
				ENDIF // progress 2
				
				// starts the proper positioning animation
				IF shop_progress_tattoo = 3 
					flag_in_remove_tattoo_section = 1
					GOSUB back_anim_start_tattoo
				
				ENDIF

				// continuation of positioning animation
				IF shop_progress_tattoo = 4
					flag_in_remove_tattoo_section = 1
					GOSUB back_anim_playing_tattoo
									
				ENDIF
				
				IF shop_progress_tattoo = 5
					GIVE_PLAYER_CLOTHES player1 0 0 area_to_look_at_shops
					flag_in_remove_tattoo_section = 1
					GOSUB start_move_in_anim_tattoo
					shop_progress_tattoo = 6
				ENDIF
				
				IF shop_progress_tattoo = 6
					flag_in_remove_tattoo_section = 1
					GOSUB play_move_in_anims_tattoo
				ENDIF 

				IF shop_progress_tattoo = 7
					flag_in_remove_tattoo_section = 1
					GOSUB start_out_anims_tattoo
					shop_progress_tattoo = 8
				ENDIF

				IF shop_progress_tattoo = 8
					flag_in_remove_tattoo_section = 1
					GOSUB finish_out_anims_tattoo
				ENDIF
				
				IF shop_progress_tattoo = 9

					shop_progress_tattoo = 0
					flag_tattoo = 5

					IF shop_progress_tattoo < 2
						GOSUB find_if_player_got_tats
				   		GOSUB print_tattoo_menu_oncreen_text
					ENDIF
				ENDIF

			ENDIF 
			
		ELSE	
			GOSUB tattoo_cleanup_big
		ENDIF // string empty
		
	ELSE
		GOSUB tattoo_cleanup_big
	ENDIF // player playing							
								
GOTO shop_tattoo_inner   		 


tattoo_cleanup_small:

	IF flag_tattoo >= 1
		CLEAR_HELP
	ENDIF

	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (CLTHNO2)

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

	IF remove_tats_menu_drawn = 1
		DELETE_MENU remove_menu_tattoo
		CLEAR_HELP
		remove_tats_menu_drawn = 0
	ENDIF
	
	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops 
		bought_menu_drawn_shops = 0
	ENDIF	

	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0

	main_menu_drawn_shops = 0
	sub_menu_drawn_shops = 0
	stored_item_picked_shops = 0
	shop_main_item_picked_shops = 0
	shop_sub_item_picked_shops = 0
	remove_menu_item_selected = 0

	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0
	
	flag_tattoo = 2
	shop_progress_tattoo = 0
  
RETURN

tattoo_cleanup_big:

	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (CLTHNO2)
	   
	blob_flag_shop = 1
	shopkeeper_attacked_tattoo = 0
	flag_tattoo = 0
	shop_progress_tattoo = 0

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

	IF remove_tats_menu_drawn = 1
		DELETE_MENU remove_menu_tattoo
		CLEAR_HELP
		remove_tats_menu_drawn = 0
	ENDIF
	
	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops 
		bought_menu_drawn_shops = 0
	ENDIF	   

	main_menu_drawn_shops = 0
	sub_menu_drawn_shops = 0
	stored_item_picked_shops = 0
	shop_main_item_picked_shops = 0
	shop_sub_item_picked_shops = 0
	remove_menu_item_selected = 0

	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0

	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0

	IF NOT IS_CHAR_DEAD shop_keep_tattoo 
		DETACH_CHAR_FROM_CAR shop_keep_tattoo
	ENDIF

	DELETE_OBJECT tattoo_needle

   	MARK_MODEL_AS_NO_LONGER_NEEDED TATTOO_KIT 

	DELETE_CHAR shop_keep_tattoo

	MARK_MODEL_AS_NO_LONGER_NEEDED shopkeeper_model_shops

	REMOVE_TEXTURE_DICTIONARY

	USE_TEXT_COMMANDS FALSE

	DISPLAY_RADAR TRUE

	SHOW_UPDATE_STATS TRUE

	SET_MINIGAME_IN_PROGRESS FALSE

//	SET_PLAYER_IS_IN_STADIUM FALSE

	CLEAR_MISSION_AUDIO 4

	TERMINATE_THIS_SCRIPT

RETURN


check_which_area_to_buy:

	IF shop_main_item_picked_shops = 0 // left upper arm
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS1
	ENDIF

	IF shop_main_item_picked_shops = 1 // left lower arm
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS2
	ENDIF

	IF shop_main_item_picked_shops = 2 // right upper arm
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS3
	ENDIF

	IF shop_main_item_picked_shops = 3 // right lower arm
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS4
	ENDIF

	IF shop_main_item_picked_shops = 4 // Back
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS5
	ENDIF

	IF shop_main_item_picked_shops = 5 // Left Chest
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS6
	ENDIF

	IF shop_main_item_picked_shops = 6 // Right chest
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS7
	ENDIF

	IF shop_main_item_picked_shops = 7 // Stomach
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS8
	ENDIF

	IF shop_main_item_picked_shops = 8 // Lower Back
		area_to_look_at_shops = CLOTHES_TEX_TATTOOS9
	ENDIF

RETURN

// used to set the correct items into slots for the shop
find_which_tattoo_shop_load:
	 
	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS1 // left upper arm 
		
		area_looked_for_shops = 4
		
		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info	
		
	  	$name_of_player_in_anim_tattoo = tat_armL_in_p
		$name_of_shop_in_anim_tattoo = tat_armL_in_t 
		$name_of_object_in_anim_tattoo = tat_armL_in_o

		$name_of_player_pose_anim_tattoo = tat_armL_pose_p
		$name_of_shop_pose_anim_tattoo = tat_armL_pose_t 
		$name_of_object_pose_anim_tattoo = tat_armL_pose_o

		$name_of_player_out_anim_tattoo = tat_armL_out_p
		$name_of_shop_out_anim_tattoo = tat_armL_out_t 
		$name_of_object_out_anim_tattoo = tat_armL_out_o
				
	ENDIF

	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS2 // left lower arm 

		area_looked_for_shops = 5
 
		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops
		
		GOSUB fill_arrays_for_tattoo_info

		$name_of_player_in_anim_tattoo = tat_armL_in_p
		$name_of_shop_in_anim_tattoo = tat_armL_in_t 
		$name_of_object_in_anim_tattoo = tat_armL_in_o

		$name_of_player_pose_anim_tattoo = tat_armL_pose_p
		$name_of_shop_pose_anim_tattoo = tat_armL_pose_t 
		$name_of_object_pose_anim_tattoo = tat_armL_pose_o

		$name_of_player_out_anim_tattoo = tat_armL_out_p
		$name_of_shop_out_anim_tattoo = tat_armL_out_t 
		$name_of_object_out_anim_tattoo = tat_armL_out_o

		
	ENDIF
	
	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS3 // right upper arm

		area_looked_for_shops = 6
		
		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info
			   
		$name_of_player_in_anim_tattoo = tat_armR_in_p
		$name_of_shop_in_anim_tattoo = tat_armR_in_t 
		$name_of_object_in_anim_tattoo = tat_armR_in_o

		$name_of_player_pose_anim_tattoo = tat_armR_pose_p
		$name_of_shop_pose_anim_tattoo = tat_armR_pose_t 
		$name_of_object_pose_anim_tattoo = tat_armR_pose_o

		$name_of_player_out_anim_tattoo = tat_armR_out_p
		$name_of_shop_out_anim_tattoo = tat_armR_out_t 
		$name_of_object_out_anim_tattoo = tat_armR_out_o

	ENDIF 
	
	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS4 // right lower arm

		area_looked_for_shops = 7	

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info 
	   
		$name_of_player_in_anim_tattoo = tat_armR_in_p
		$name_of_shop_in_anim_tattoo = tat_armR_in_t 
		$name_of_object_in_anim_tattoo = tat_armR_in_o

		$name_of_player_pose_anim_tattoo = tat_armR_pose_p
		$name_of_shop_pose_anim_tattoo = tat_armR_pose_t 
		$name_of_object_pose_anim_tattoo = tat_armR_pose_o

		$name_of_player_out_anim_tattoo = tat_armR_out_p
		$name_of_shop_out_anim_tattoo = tat_armR_out_t 
		$name_of_object_out_anim_tattoo = tat_armR_out_o 

	ENDIF 	


	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS5 // Back

		area_looked_for_shops = 8
		

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info	   
		$name_of_player_in_anim_tattoo = tat_back_in_p
		$name_of_shop_in_anim_tattoo = tat_back_in_t 
		$name_of_object_in_anim_tattoo = tat_back_in_o

		$name_of_player_pose_anim_tattoo = tat_back_pose_p
		$name_of_shop_pose_anim_tattoo = tat_back_pose_t 
		$name_of_object_pose_anim_tattoo = tat_back_pose_o

		$name_of_player_out_anim_tattoo = tat_back_out_p
		$name_of_shop_out_anim_tattoo = tat_back_out_t 
		$name_of_object_out_anim_tattoo = tat_back_out_o 

	ENDIF 	

	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS6 // Left Chest

		area_looked_for_shops = 9

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info
			   
		$name_of_player_in_anim_tattoo = tat_che_in_p
		$name_of_shop_in_anim_tattoo = tat_che_in_t 
		$name_of_object_in_anim_tattoo = tat_che_in_o

		$name_of_player_pose_anim_tattoo = tat_che_pose_p
		$name_of_shop_pose_anim_tattoo = tat_che_pose_t 
		$name_of_object_pose_anim_tattoo = tat_che_pose_o

		$name_of_player_out_anim_tattoo = tat_che_out_p
		$name_of_shop_out_anim_tattoo = tat_che_out_t 
		$name_of_object_out_anim_tattoo = tat_che_out_o 

	ENDIF 	


	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS7 // Right chest

		area_looked_for_shops = 10

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info	   
		$name_of_player_in_anim_tattoo = tat_che_in_p
		$name_of_shop_in_anim_tattoo = tat_che_in_t 
		$name_of_object_in_anim_tattoo = tat_che_in_o

		$name_of_player_pose_anim_tattoo = tat_che_pose_p
		$name_of_shop_pose_anim_tattoo = tat_che_pose_t 
		$name_of_object_pose_anim_tattoo = tat_che_pose_o

		$name_of_player_out_anim_tattoo = tat_che_out_p
		$name_of_shop_out_anim_tattoo = tat_che_out_t 
		$name_of_object_out_anim_tattoo = tat_che_out_o
		

	ENDIF 	
	
	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS8 // Stomach
	   
		area_looked_for_shops = 11
		 
		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info
		
		$name_of_player_in_anim_tattoo = tat_che_in_p
		$name_of_shop_in_anim_tattoo = tat_bel_in_t 
		$name_of_object_in_anim_tattoo = tat_bel_in_o

		$name_of_player_pose_anim_tattoo = tat_che_pose_p
		$name_of_shop_pose_anim_tattoo = tat_bel_pose_t 
		$name_of_object_pose_anim_tattoo = tat_bel_pose_o

		$name_of_player_out_anim_tattoo = tat_che_out_p
		$name_of_shop_out_anim_tattoo = tat_bel_out_t 
		$name_of_object_out_anim_tattoo = tat_bel_out_o   
		 		  

	ENDIF
	
	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS9 // Lower Back
	   
		area_looked_for_shops = 12

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		GOSUB fill_arrays_for_tattoo_info	   
		$name_of_player_in_anim_tattoo = tat_back_in_p
		$name_of_shop_in_anim_tattoo = tat_back_in_t 
		$name_of_object_in_anim_tattoo = tat_back_in_o

		$name_of_player_pose_anim_tattoo = tat_back_pose_p
		$name_of_shop_pose_anim_tattoo = tat_back_pose_t 
		$name_of_object_pose_anim_tattoo = tat_back_pose_o

		$name_of_player_out_anim_tattoo = tat_back_out_p
		$name_of_shop_out_anim_tattoo = tat_back_out_t 
		$name_of_object_out_anim_tattoo = tat_back_out_o   	  

	ENDIF  	
		
RETURN

// fills in the arrays
fill_arrays_for_tattoo_info:

    item_no_shops = 0
    number_of_tattoos_in_area = 0

    WHILE item_no_shops < number_of_tats_in_shop

	    GET_ITEM_IN_SHOP item_no_shops model_index
	    GET_SHOPPING_EXTRA_INFO model_index 0 modelid_shops
        
		IF modelid_shops = area_looked_for_shops
		    item_model_index[number_of_tattoos_in_area] = model_index
		
		    GET_NAME_OF_ITEM model_index $tattoo_name
		    GET_PRICE_OF_ITEM model_index tattoo_price
		
		    item_price[number_of_tattoos_in_area] = tattoo_price  
		    $item_text_label[number_of_tattoos_in_area] = $tattoo_name

		    IF player_item_texture_shops = model_index
			    item_hilight_shops[number_of_tattoos_in_area] = FALSE
		    ELSE
			    item_hilight_shops[number_of_tattoos_in_area] = TRUE
		    ENDIF
		
		    ++number_of_tattoos_in_area 
	    ENDIF
	
	++item_no_shops
    ENDWHILE
 	
	temp_var_shops = number_of_tattoos_in_area
	
	WHILE temp_var_shops < 12
		item_model_index[temp_var_shops] = -1
		item_price[temp_var_shops] = 0
		$item_text_label[temp_var_shops] = DUMMY
		++temp_var_shops
    ENDWHILE

RETURN


// displays the main menu text
print_tattoo_menu_oncreen_text:

 	IF main_menu_drawn_shops = 0

		// Create and populate the main menu with the list of body parts.
		
		PRINT_HELP_FOREVER TATTA

		$ITEM1 = LARMTP
		$ITEM2 = LARMLW
		$ITEM3 = RARMTP
		$ITEM4 = RARMLW
		$ITEM5 = BACK
		$ITEM6 = LCHEST
		$ITEM7 = RCHEST
		$ITEM8 = BELLY 
		$ITEM9 = LBACK 

		IF number_of_tats_player_has > 0
			$ITEM10 = TATREM 
		ELSE
			$ITEM10 = DUMMY 
		ENDIF

		$ITEM11 = DUMMY 
		$ITEM12 = DUMMY 
		
		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU TATTO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU TATTO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU TATTO 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU TATTO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU TATTO 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION main_menu_shops 1 FO_LEFT
		SET_MENU_COLUMN main_menu_shops 0 DUMMY $ITEM1 $ITEM2 $ITEM3 $ITEM4 $ITEM5 $ITEM6 $ITEM7 $ITEM8 $ITEM9 $ITEM10 $ITEM11 $ITEM12 

		main_menu_drawn_shops = 1
	ENDIF

RETURN

// Second menu for showing all the items you can buy
print_tattoo_oncreen_text2:

	IF sub_menu_drawn_shops = 0

		// Create and populate the inner menu with available tattoos.
		
		PRINT_HELP_FOREVER TATTB

		IF IS_XBOX_VERSION
			CREATE_MENU TATTO 29.0 155.0 93.0 2 TRUE TRUE FO_LEFT sub_menu_shops
		ELSE
			IF current_Language = LANGUAGE_ENGLISH
				CREATE_MENU TATTO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT sub_menu_shops
			ELSE
				IF current_Language = LANGUAGE_FRENCH
					CREATE_MENU TATTO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT sub_menu_shops
				ELSE
					IF current_Language = LANGUAGE_GERMAN
						CREATE_MENU TATTO 29.0 165.0 93.0 2 TRUE TRUE FO_LEFT sub_menu_shops
					ELSE
						IF current_Language = LANGUAGE_ITALIAN
							CREATE_MENU TATTO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT sub_menu_shops
						ELSE
							IF current_Language = LANGUAGE_SPANISH
								CREATE_MENU TATTO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT sub_menu_shops
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		

		SET_MENU_COLUMN_ORIENTATION sub_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN sub_menu_shops 0 TATTO $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11]
		SET_MENU_COLUMN_ORIENTATION sub_menu_shops 1 FO_RIGHT
		SET_MENU_COLUMN sub_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		
		price_counter = 0
		WHILE price_counter < number_of_tattoos_in_area
			SET_MENU_ITEM_WITH_NUMBER sub_menu_shops 1 price_counter DOLLAR item_price[price_counter]
			++price_counter
		ENDWHILE

		temp_var_shops = 0
		WHILE temp_var_shops < number_of_tattoos_in_area
			ACTIVATE_MENU_ITEM sub_menu_shops temp_var_shops item_hilight_shops[temp_var_shops]
			++temp_var_shops
		ENDWHILE

		SET_MENU_COLUMN_WIDTH sub_menu_shops 0 140
		SET_MENU_COLUMN_WIDTH sub_menu_shops 1 46

		sub_menu_drawn_shops = 1
	ENDIF


RETURN

choose_sprite_tattoo:
	LOAD_SPRITE 1 $item_text_label[menu_two_item_hilighted]
RETURN


find_if_player_got_tats:

	temp_var_shops = 0
	number_of_tats_player_has = 0

	WHILE temp_var_shops < 9

		temp_var_shops2 = CLOTHES_TEX_TATTOOS1 + temp_var_shops 

		GET_CLOTHES_ITEM player1 temp_var_shops2 tex_tattoo2 model_tattoo2

		IF NOT tex_tattoo2 = 0
			++ number_of_tats_player_has
		ENDIF 
		
	    ++temp_var_shops

	ENDWHILE

RETURN


draw_remove_tats_menu:
   
	IF remove_tats_menu_drawn = 0

		menu_item_no_tats = 0
		temp_var_shops = 0

		WHILE temp_var_shops < 9

			temp_var_shops2 = CLOTHES_TEX_TATTOOS1 + temp_var_shops 

			GET_CLOTHES_ITEM player1 temp_var_shops2 tex_tattoo2 model_tattoo2

			IF NOT tex_tattoo2 = 0
				player_has_tattoo_in_area[menu_item_no_tats] = temp_var_shops2

				SWITCH temp_var_shops2
				CASE 4
					$item_text_label[menu_item_no_tats] = LARMTP
					BREAK
				CASE 5
					$item_text_label[menu_item_no_tats] = LARMLW
					BREAK
				CASE 6
					$item_text_label[menu_item_no_tats] = RARMTP
					BREAK
				CASE 7
					$item_text_label[menu_item_no_tats] = RARMLW
					BREAK
				CASE 8
					$item_text_label[menu_item_no_tats] = BACK
					BREAK
				CASE 9
					$item_text_label[menu_item_no_tats] = LCHEST
					BREAK
				CASE 10
					$item_text_label[menu_item_no_tats] = RCHEST
					BREAK
				CASE 11
					$item_text_label[menu_item_no_tats] = BELLY
					BREAK
				CASE 12
					$item_text_label[menu_item_no_tats] = LBACK
					BREAK
				ENDSWITCH

                ++menu_item_no_tats
			ENDIF 
				
			++temp_var_shops

		ENDWHILE

		temp_var_shops = menu_item_no_tats

		WHILE temp_var_shops < 12
			$item_text_label[temp_var_shops] = DUMMY
			++temp_var_shops
		ENDWHILE

		PRINT_HELP_FOREVER TATTA

		// Create the 'remove tattoo' menu.

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU TATTO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT remove_menu_tattoo
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU TATTO 29.0 165.0 93.0 2 TRUE TRUE FO_LEFT remove_menu_tattoo
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU TATTO 29.0 165.0 93.0 2 TRUE TRUE FO_LEFT remove_menu_tattoo
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU TATTO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT remove_menu_tattoo
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU TATTO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT remove_menu_tattoo
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION remove_menu_tattoo 0 FO_LEFT
		SET_MENU_COLUMN remove_menu_tattoo 0 TATTO $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11]
		SET_MENU_COLUMN_ORIENTATION remove_menu_tattoo 1 FO_RIGHT
		SET_MENU_COLUMN remove_menu_tattoo 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		
		
		price_counter = 0
		WHILE price_counter < menu_item_no_tats
			SET_MENU_ITEM_WITH_NUMBER sub_menu_shops 1 price_counter DOLLAR TATTOO_REMOVAL_PRICE
			++price_counter
		ENDWHILE

		SET_MENU_COLUMN_WIDTH remove_menu_tattoo 0 140
		SET_MENU_COLUMN_WIDTH remove_menu_tattoo 1 46
				  			
		remove_tats_menu_drawn = 1
	ENDIF

RETURN

// checks and plays the back anims
back_anim_start_tattoo:

	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS5
	OR area_to_look_at_shops = CLOTHES_TEX_TATTOOS9

		IF NOT IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_loop_p
	   
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_back_sit_in_p TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1
			return_animation_time_tattoo = 0.0

			IF IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_in_p
				GET_CHAR_ANIM_CURRENT_TIME scplayer tat_back_sit_in_p return_animation_time_tattoo
			ENDIF
			
			IF flag_in_remove_tattoo_section = 1 
				shop_progress_tattoo = 4	
			ELSE
				shop_progress_tattoo = 2
			ENDIF

		ELSE
			
			IF flag_in_remove_tattoo_section = 1
				shop_progress_tattoo = 5
			ELSE
				shop_progress_tattoo = 3
			ENDIF

		ENDIF
							
	ELSE

		IF IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_loop_p
									
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_back_sit_out_p TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1
			return_animation_time_tattoo = 0.0

			IF IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_out_p
				GET_CHAR_ANIM_CURRENT_TIME scplayer tat_back_sit_out_p return_animation_time_tattoo
			ENDIF

			IF flag_in_remove_tattoo_section = 1 
				shop_progress_tattoo = 4	
			ELSE
				shop_progress_tattoo = 2
			ENDIF

		ELSE
							 						   
			IF flag_in_remove_tattoo_section = 1
				shop_progress_tattoo = 5
			ELSE
				shop_progress_tattoo = 3
			ENDIF
				
		ENDIF

	ENDIF
	
RETURN

// waiting for the back anims to finish
back_anim_playing_tattoo:

	IF area_to_look_at_shops = CLOTHES_TEX_TATTOOS5
	OR area_to_look_at_shops = CLOTHES_TEX_TATTOOS9


		IF IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_in_p
			GET_CHAR_ANIM_CURRENT_TIME scplayer tat_back_sit_in_p return_animation_time_tattoo
		ENDIF

		IF return_animation_time_tattoo = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_back_sit_loop_p TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
			return_animation_time_tattoo = 0.0

			IF flag_in_remove_tattoo_section = 1
				shop_progress_tattoo = 5
			ELSE
				shop_progress_tattoo = 3
			ENDIF

		ENDIF

	
	ELSE
		
		IF IS_CHAR_PLAYING_ANIM scplayer tat_back_sit_out_p
			GET_CHAR_ANIM_CURRENT_TIME scplayer tat_back_sit_out_p return_animation_time_tattoo
		ENDIF

		IF return_animation_time_tattoo = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_sit_loop_p TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
			return_animation_time_tattoo = 0.0

			IF flag_in_remove_tattoo_section = 1
				shop_progress_tattoo = 5
			ELSE
				shop_progress_tattoo = 3
			ENDIF

		ENDIF
	   													
	ENDIF
	
RETURN

// starts player moving in
start_move_in_anim_tattoo:

	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $name_of_player_in_anim_tattoo TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1
	PLAY_MISSION_AUDIO 4
	return_animation_time_tattoo = 0.0

	TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo $name_of_shop_in_anim_tattoo TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1  
	shop_keep_animation_time_tattoo = 0.0

	IF DOES_OBJECT_EXIST tattoo_needle
		PLAY_OBJECT_ANIM tattoo_needle $name_of_object_in_anim_tattoo TATTOOS 1000.0 FALSE TRUE
		object_animation_time_tattoo = 0.0
	ENDIF
	
RETURN

play_move_in_anims_tattoo:

	IF IS_CHAR_PLAYING_ANIM scplayer $name_of_player_in_anim_tattoo
		GET_CHAR_ANIM_CURRENT_TIME scplayer $name_of_player_in_anim_tattoo return_animation_time_tattoo
	ENDIF

	IF IS_CHAR_PLAYING_ANIM shop_keep_tattoo $name_of_shop_in_anim_tattoo
		GET_CHAR_ANIM_CURRENT_TIME shop_keep_tattoo $name_of_shop_in_anim_tattoo return_animation_time_tattoo	
	ENDIF
	
	IF DOES_OBJECT_EXIST tattoo_needle

		IF IS_OBJECT_PLAYING_ANIM tattoo_needle $name_of_object_in_anim_tattoo
			GET_OBJECT_ANIM_CURRENT_TIME tattoo_needle $name_of_object_in_anim_tattoo object_animation_time_tattoo						
		ENDIF
	
	ENDIF 

	IF return_animation_time_tattoo = 1.0
		TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $name_of_player_pose_anim_tattoo TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
		return_animation_time_tattoo = 0.0
   							 
		TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo $name_of_shop_pose_anim_tattoo TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
		shop_keep_animation_time_tattoo = 0.0
   
		IF DOES_OBJECT_EXIST tattoo_needle							
			PLAY_OBJECT_ANIM tattoo_needle $name_of_object_pose_anim_tattoo TATTOOS 1000.0 TRUE FALSE
			object_animation_time_tattoo = 0.0
		ENDIF

   		STORE_CLOTHES_STATE
		BUILD_PLAYER_MODEL player1

		GET_CLOTHES_ITEM player1 area_to_look_at_shops player_item_texture_shops player_item_model_shops

		IF flag_in_remove_tattoo_section = 1
			shop_progress_tattoo = 7	
		ELSE
			GOSUB fill_arrays_for_tattoo_info // need to recalculate the grey out stuff
			shop_progress_tattoo = 4
		ENDIF
						 				   
	ENDIF

RETURN

// starting the back out anims
start_out_anims_tattoo:

	TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $name_of_player_out_anim_tattoo TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1
	return_animation_time_tattoo = 0.0

	TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo $name_of_shop_out_anim_tattoo TATTOOS 1000.0 FALSE FALSE FALSE TRUE -1
	shop_keep_animation_time_tattoo = 0.0

	IF DOES_OBJECT_EXIST tattoo_needle							
		PLAY_OBJECT_ANIM tattoo_needle $name_of_object_out_anim_tattoo TATTOOS 1000.0 FALSE TRUE
		object_animation_time_tattoo = 0.0
	ENDIF

RETURN

// finish_out_anims
finish_out_anims_tattoo:
 
	IF IS_CHAR_PLAYING_ANIM scplayer $name_of_player_out_anim_tattoo
		GET_CHAR_ANIM_CURRENT_TIME scplayer $name_of_player_out_anim_tattoo return_animation_time_tattoo
	ENDIF

	IF IS_CHAR_PLAYING_ANIM shop_keep_tattoo $name_of_shop_out_anim_tattoo
		GET_CHAR_ANIM_CURRENT_TIME shop_keep_tattoo $name_of_shop_out_anim_tattoo shop_keep_animation_time_tattoo
	ENDIF

	IF IS_CHAR_PLAYING_ANIM scplayer tat_back_out_p 

		IF return_animation_time_tattoo = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_back_sit_loop_p TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
			return_animation_time_tattoo = 0.0
			
			IF flag_in_remove_tattoo_section = 1
			   shop_progress_tattoo = 9
			ELSE
				shop_progress_tattoo = 6
			ENDIF
			
		ENDIF

		IF shop_keep_animation_time_tattoo = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_sit_loop_t TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
			shop_keep_animation_time_tattoo = 0.0			

			IF DOES_OBJECT_EXIST tattoo_needle							
				PLAY_OBJECT_ANIM tattoo_needle tat_sit_loop_o TATTOOS 1000.0 TRUE FALSE
				object_animation_time_tattoo = 0.0
			ENDIF

		ENDIF
		
	ELSE

		IF return_animation_time_tattoo = 1.0
			TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer tat_sit_loop_p TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
			return_animation_time_tattoo = 0.0

			IF flag_in_remove_tattoo_section = 1
				shop_progress_tattoo = 9
			ELSE
				shop_progress_tattoo = 6
			ENDIF						

		ENDIF

		IF shop_keep_animation_time_tattoo = 1.0 
			TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_tattoo tat_sit_loop_t TATTOOS 1000.0 TRUE FALSE FALSE FALSE -1
			shop_keep_animation_time_tattoo = 0.0

			IF DOES_OBJECT_EXIST tattoo_needle							
				PLAY_OBJECT_ANIM tattoo_needle tat_sit_loop_o TATTOOS 1000.0 TRUE FALSE
				object_animation_time_tattoo = 0.0
			ENDIF			

		ENDIF

	ENDIF

RETURN

draw_sprite_tattoo:

    SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_RECT 280.0 70.0 70.0 70.0 0 0 0 255
	SET_SPRITES_DRAW_BEFORE_FADE TRUE
	DRAW_SPRITE 1 280.0 70.0 64.0 64.0 255 255 255 200 // 54.0

	//DRAW_RECT 580.0 158.0 70.0 70.0 0 0 0 255
	//DRAW_SPRITE 1 580.0 158.0 64.0 64.0 255 255 255 200 // 54.0 

RETURN

MISSION_END

}
