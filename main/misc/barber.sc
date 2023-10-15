MISSION_START

{

VAR_INT num_of_hairstyles // gets the number of clothes in the shop

VAR_INT unk9728
VAR_INT shop_keep_barbers
VAR_INT hair_price

VAR_INT model_index_barbers haircut_picked_barbers

VAR_INT modelid_barber component_barber  // used to display clothes on the player

// barber moves infrom the side into position
VAR_INT barber_movein_barbers

VAR_FLOAT return_animation_time_barbers
			
// Used to say which hair touchup player will get
VAR_INT random_number_barbers 

VAR_TEXT_LABEL $stored_barbers_shop

VAR_TEXT_LABEL $hairstyle_name

VAR_INT flag_barber

VAR_FLOAT barberX barberY barberZ barber_heading

VAR_INT unk9747

VAR_INT flag_viewed_hair

VAR_INT player_walk_barbers player_task_progress_barber

VAR_FLOAT player_heading_barbers

VAR_TEXT_LABEL16 $name_of_anim_barbers

// Player stand positions
CONST_FLOAT keep_off_true_X 414.3
CONST_FLOAT keep_off_true_Y -19.9
CONST_FLOAT keep_off_true_Z 1000.806

// cameras for all the cuts in the barbers get into chair
CONST_FLOAT getin_chaircamX 412.0009  // 412.7162  // 411.829 // point where all offsets are worked out from
CONST_FLOAT getin_chaircamY -19.8018 // -20.0258 // -22.896
CONST_FLOAT getin_chaircamZ 1001.7647 // 1001.9365 // 1003.053

CONST_FLOAT getin_chaircam_lookatX 412.8502 // 413.4781 // 412.158 // point where all offsets are worked out from
CONST_FLOAT getin_chaircam_lookatY -19.2749 // -19.3876 // -22.004
CONST_FLOAT getin_chaircam_lookatZ 1001.7975 // 1002.0462 // 1002.743

VAR_FLOAT getin_chaircam_offX getin_chaircam_offY getin_chaircam_offZ getin_chaircam_lookat_offX getin_chaircam_lookat_offY getin_chaircam_lookat_offZ

getin_chaircam_offX = 0.0
getin_chaircam_offY = 0.0
getin_chaircam_offZ = 0.0

getin_chaircam_lookat_offX = 0.0
getin_chaircam_lookat_offY = 0.0
getin_chaircam_lookat_offZ = 0.0

// sitting in chair
CONST_FLOAT chaircamX 414.9329   // 415.574   
CONST_FLOAT chaircamY -18.4475 // -18.365
CONST_FLOAT chaircamZ 1002.2863 // 1002.059

CONST_FLOAT chaircam_lookatX 414.0564 // 414.609
CONST_FLOAT chaircam_lookatY -18.7965 // -18.592
CONST_FLOAT chaircam_lookatZ 1001.9548 // 1001.924

VAR_FLOAT chaircam_offX chaircam_offY chaircam_offZ chaircam_lookat_offX chaircam_lookat_offY chaircam_lookat_offZ

chaircam_offX = 0.0
chaircam_offY = 0.0
chaircam_offZ = 0.0

chaircam_lookat_offX = 0.0
chaircam_lookat_offY = 0.0
chaircam_lookat_offZ = 0.0

// Checkout hairstyle
CONST_FLOAT hairstyle_camX 412.6490 // 412.532
CONST_FLOAT hairstyle_camY -19.7078 // -19.810 
CONST_FLOAT hairstyle_camZ 1001.9915 // 1002.464

CONST_FLOAT hairstyle_cam_lookatX 413.3581 // 413.339
CONST_FLOAT hairstyle_cam_lookatY -19.0046 // -19.280
CONST_FLOAT hairstyle_cam_lookatZ 1002.0428 // 1002.205

VAR_FLOAT hairstyle_cam_offX hairstyle_cam_offY hairstyle_cam_offZ hairstyle_cam_lookat_offX hairstyle_cam_lookat_offY hairstyle_cam_lookat_offZ

hairstyle_cam_offX = 0.0  
hairstyle_cam_offY = 0.0 
hairstyle_cam_offZ = 0.0  
					
hairstyle_cam_lookat_offX = 0.0   
hairstyle_cam_lookat_offY = 0.0 
hairstyle_cam_lookat_offZ = 0.0

// Get out of chair
CONST_FLOAT getout_chair_camX 414.5509    // 411.793
CONST_FLOAT getout_chair_camY -20.8773 // -16.224
CONST_FLOAT getout_chair_camZ 1001.4052 // 1003.138

CONST_FLOAT getout_chair_cam_lookatX 414.0572 // 412.304
CONST_FLOAT getout_chair_cam_lookatY -20.0250 // -17.006
CONST_FLOAT getout_chair_cam_lookatZ 1001.5781 // 1002.781

VAR_FLOAT getout_chair_cam_offX getout_chair_cam_offY getout_chair_cam_offZ getout_chair_cam_lookat_offX getout_chair_cam_lookat_offY getout_chair_cam_lookat_offZ 

getout_chair_cam_offX = 0.0 
getout_chair_cam_offY =	0.0
getout_chair_cam_offZ = 0.0

getout_chair_cam_lookat_offX = 0.0
getout_chair_cam_lookat_offY = 0.0
getout_chair_cam_lookat_offZ = 0.0

// Leave Shop
CONST_FLOAT leave_barb_camX 412.5159 // 411.1909 // 411.823
CONST_FLOAT leave_barb_camY -21.7447 // -19.4155 // -17.192
CONST_FLOAT leave_barb_camZ 1001.9464 // 1001.3998 // 1003.253

CONST_FLOAT leave_barb_cam_lookatX 412.9438 // 411.6641 // 411.675
CONST_FLOAT leave_barb_cam_lookatY -20.8411 // -20.2620 // -18.138
CONST_FLOAT leave_barb_cam_lookatZ 1001.9631 // 1001.6437 // 1002.965

VAR_FLOAT leave_barb_cam_offX leave_barb_cam_offY leave_barb_cam_offZ leave_barb_cam_lookat_offX leave_barb_cam_lookat_offY leave_barb_cam_lookat_offZ 

leave_barb_cam_offX = 0.0 
leave_barb_cam_offY = 0.0
leave_barb_cam_offZ = 0.0

leave_barb_cam_lookat_offX = 0.0
leave_barb_cam_lookat_offY = 0.0
leave_barb_cam_lookat_offZ = 0.0

player_heading_barbers = 0.0

player_task_progress_barber = 0

CONST_FLOAT walkX_barbers 411.957
CONST_FLOAT walkY_barbers -19.779
CONST_FLOAT walkZ_barbers 1000.836

CONST_FLOAT walk2X_barbers 411.795
CONST_FLOAT walk2Y_barbers -23.300 // -21.747
CONST_FLOAT walk2Z_barbers 1000.836

VAR_FLOAT walk_offX_barbers walk_offY_barbers walk_offZ_barbers walk2_offX_barbers walk2_offY_barbers walk2_offZ_barbers

walk_offX_barbers = 0.0
walk_offY_barbers = 0.0
walk_offZ_barbers = 0.0

walk2_offX_barbers = 0.0
walk2_offY_barbers = 0.0
walk2_offZ_barbers = 0.0

VAR_FLOAT barber_shop_offsetX barber_shop_offsetY barber_shop_offsetZ

flag_viewed_hair = 0
haircut_picked_barbers = 0
return_animation_time_barbers = 0.0
random_number_barbers = 0
flag_barber = 0

barberX = 0.0
barberY = 0.0
barberZ = 0.0
barber_heading = 0.0

unk9747 = 0
shop_item_price = 0

flag_bought_item_already_shops = 0
flag_no_money_shops = 0

VAR_INT control_flag_barbers
control_flag_barbers= 0

// NEW AUDIO STUFF
VAR_INT player_item_texture_audio_shops player_item_model_audio_shops

VAR_INT item_no_hairstyles number_of_hairstyles_in_area

item_no_hairstyles = 0
number_of_hairstyles_in_area = 0

// new menu stuff

VAR_INT barber_look_shops
barber_look_shops = 0

VAR_INT visible_area_shops

// used to restore the camera at the end walk out
VAR_INT flag_restored_camera_barbers
flag_restored_camera_barbers = 0

VAR_INT cut_hair_flag_barber
cut_hair_flag_barber = 0

// requests models
IF $shop_name = barbers	
	shopkeeper_model_shops = BMOBAR
ENDIF

IF $shop_name = barber2
	shopkeeper_model_shops = WMYBAR
ENDIF

IF $shop_name = barber3					
	shopkeeper_model_shops = BMYBAR
ENDIF	 

REQUEST_MODEL shopkeeper_model_shops

LOAD_MISSION_AUDIO 4 SOUND_HAIRCUT
				 
LOAD_ALL_MODELS_NOW

SET_DEATHARREST_STATE OFF

SCRIPT_NAME	BARB

shop_barbers_inner:
    
	WAIT 0
				
	IF IS_PLAYER_PLAYING player1

		IF NOT IS_STRING_EMPTY $shop_name

			IF flag_barber > 0
				IF HAS_LANGUAGE_CHANGED
					CLEAR_HELP
					DELETE_MENU cost_menu_shops
					DELETE_MENU main_menu_shops
					cost_menu_drawn_shops = 0
					main_menu_drawn_shops = 0
					GET_CURRENT_LANGUAGE current_Language
				ENDIF
			ENDIF
		
			IF flag_barber = 0 
				
				IF $shop_name = barbers	
					
					barber_shop_offsetX = 0.0
					barber_shop_offsetY = 0.0 
					barber_shop_offsetZ = 0.0

					$stored_barbers_shop = $shop_name

					GET_NUMBER_OF_ITEMS_IN_SHOP num_of_hairstyles
					control_flag_barbers = 0
					flag_barber = 1
				ENDIF

				IF $shop_name = barber2

					barber_shop_offsetX = 6.987
					barber_shop_offsetY = -61.401 
					barber_shop_offsetZ = 0.0

					$stored_barbers_shop = $shop_name
					
					GET_NUMBER_OF_ITEMS_IN_SHOP num_of_hairstyles
					control_flag_barbers = 0
					flag_barber = 1
				ENDIF

				IF $shop_name = barber3
										
					barber_shop_offsetX = 0.371
					barber_shop_offsetY = -31.421 
					barber_shop_offsetZ = 0.0

					$stored_barbers_shop = $shop_name
					
					GET_NUMBER_OF_ITEMS_IN_SHOP num_of_hairstyles
					control_flag_barbers = 0
					flag_barber = 1
				ENDIF

			ENDIF // flag_barber = 0
				

			IF flag_barber = 1
			
				USE_TEXT_COMMANDS TRUE

				SHOW_UPDATE_STATS FALSE

				STORE_CLOTHES_STATE  									
				
				// locate for player to stand in
				keep_offX = keep_off_true_X + barber_shop_offsetX
				keep_offY = keep_off_true_Y + barber_shop_offsetY
				keep_offZ = keep_off_true_Z + barber_shop_offsetZ
					
				player_heading_barbers = 90.0
				
				barberX = keep_off_true_X - 0.027 // 0.127
				barberX = barberX + barber_shop_offsetX

				barberY = keep_off_true_Y + 1.898
				barberY = barberY + barber_shop_offsetY
                
				barberZ = 1000.860

				barber_heading = 180.0
				
				// cameras for different shots round the barbers player getting into chair 
												
				getin_chaircam_offX = getin_chaircamX + barber_shop_offsetX
				getin_chaircam_offY = getin_chaircamY + barber_shop_offsetY
				getin_chaircam_offZ = getin_chaircamZ + barber_shop_offsetZ  
				
				getin_chaircam_lookat_offX = getin_chaircam_lookatX + barber_shop_offsetX
				getin_chaircam_lookat_offY = getin_chaircam_lookatY + barber_shop_offsetY
				getin_chaircam_lookat_offZ = getin_chaircam_lookatZ + barber_shop_offsetZ

				// camera for player sitting in chair
				chaircam_offX = chaircamX + barber_shop_offsetX 
				chaircam_offY = chaircamY + barber_shop_offsetY
				chaircam_offZ = chaircamZ + barber_shop_offsetZ

				chaircam_lookat_offX = chaircam_lookatX + barber_shop_offsetX
				chaircam_lookat_offY = chaircam_lookatY + barber_shop_offsetY
				chaircam_lookat_offZ = chaircam_lookatZ + barber_shop_offsetZ
					
				// player checking out new hair in mirror
				hairstyle_cam_offX = hairstyle_camX + barber_shop_offsetX  
				hairstyle_cam_offY = hairstyle_camY + barber_shop_offsetY
				hairstyle_cam_offZ = hairstyle_camZ + barber_shop_offsetZ 
				
				hairstyle_cam_lookat_offX = hairstyle_cam_lookatX + barber_shop_offsetX  
				hairstyle_cam_lookat_offY = hairstyle_cam_lookatY + barber_shop_offsetY
				hairstyle_cam_lookat_offZ = hairstyle_cam_lookatZ + barber_shop_offsetZ

				// get out of chair (quit)
				getout_chair_cam_offX = getout_chair_camX + barber_shop_offsetX
				getout_chair_cam_offY = getout_chair_camY + barber_shop_offsetY
				getout_chair_cam_offZ = getout_chair_camZ + barber_shop_offsetZ

				getout_chair_cam_lookat_offX = getout_chair_cam_lookatX + barber_shop_offsetX
				getout_chair_cam_lookat_offY = getout_chair_cam_lookatY + barber_shop_offsetY
				getout_chair_cam_lookat_offZ = getout_chair_cam_lookatZ + barber_shop_offsetZ

				// leave shop
				leave_barb_cam_offX = leave_barb_camX + barber_shop_offsetX 
				leave_barb_cam_offY = leave_barb_camY + barber_shop_offsetY
				leave_barb_cam_offZ = leave_barb_camZ + barber_shop_offsetZ

				leave_barb_cam_lookat_offX = leave_barb_cam_lookatX + barber_shop_offsetX
				leave_barb_cam_lookat_offY = leave_barb_cam_lookatY + barber_shop_offsetY
				leave_barb_cam_lookat_offZ = leave_barb_cam_lookatZ + barber_shop_offsetZ

				walk_offX_barbers = walkX_barbers + barber_shop_offsetX
				walk_offY_barbers = walkY_barbers + barber_shop_offsetY
				walk_offZ_barbers = walkZ_barbers + barber_shop_offsetZ

				walk2_offX_barbers = walk2X_barbers + barber_shop_offsetX
				walk2_offY_barbers = walk2Y_barbers + barber_shop_offsetY
				walk2_offZ_barbers = walk2Z_barbers + barber_shop_offsetZ

				CREATE_CHAR PEDTYPE_CIVMALE shopkeeper_model_shops barberX barberY barberZ shop_keep_barbers
				SET_CHAR_HEADING shop_keep_barbers barber_heading
				SET_CHAR_DECISION_MAKER shop_keep_barbers DM_PED_EMPTY
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER shop_keep_barbers TRUE

				OPEN_SEQUENCE_TASK barber_movein_barbers
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BRB_in HAIRCUTS 4.0 FALSE FALSE FALSE FALSE -1
					TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BRB_loop HAIRCUTS 4.0 TRUE FALSE FALSE FALSE -1
				CLOSE_SEQUENCE_TASK barber_movein_barbers

			   	OPEN_SEQUENCE_TASK player_walk_barbers
					TASK_GO_STRAIGHT_TO_COORD -1 walk_offX_barbers walk_offY_barbers walk_offZ_barbers PEDMOVE_WALK 8000 //TASK_FOLLOW_PATH_NODES_TO_COORD
					TASK_GO_STRAIGHT_TO_COORD -1 walk2_offX_barbers walk2_offY_barbers walk2_offZ_barbers PEDMOVE_WALK 8000
				   	TASK_ACHIEVE_HEADING -1 180.0 
			   	CLOSE_SEQUENCE_TASK player_walk_barbers

				control_flag_barbers = 0 				
				flag_barber = 2
			ENDIF						
		
			IF flag_barber = 2

				GET_LOADED_SHOP $shop_name
										 			
				IF NOT $shop_name = $stored_barbers_shop
					GOSUB barbers_cleanup_big
				ENDIF 

				IF NOT IS_CHAR_DEAD shop_keep_barbers

					IF flag_attacked_barber = 0
			  
						IF IS_CHAR_SHOOTING scplayer
						OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_barbers
						OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_barbers WEAPONTYPE_ANYWEAPON
							CLEAR_LOOK_AT shop_keep_barbers
							SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shops
							TASK_HANDS_UP shop_keep_barbers -2
							flag_attacked_barber = 1
						ELSE

							IF barber_look_shops = 0
								GET_AREA_VISIBLE visible_area_shops

								IF NOT visible_area_shops = 0 
									TASK_LOOK_AT_CHAR shop_keep_barbers scplayer -2
									SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_TAKE_A_SEAT sample_name_shops
									barber_look_shops = 1
								ENDIF
							ENDIF
					
							blob_flag_shop = 1

							IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop
								IF CAN_PLAYER_START_MISSION player1
									CLEAR_LOOK_AT shop_keep_barbers
									control_flag_barbers = 0
									flag_barber = 3
								ENDIF
							ENDIF
						ENDIF
					
					ELSE
                     	GOSUB barbers_cleanup_small
						
						GET_LOADED_SHOP $shop_name
										 			
						IF NOT $shop_name = $stored_barbers_shop
							GOSUB barbers_cleanup_big
						ENDIF

						GOTO shop_barbers_inner
					ENDIF
				
				ELSE
                   	GOSUB barbers_cleanup_small
						
					GET_LOADED_SHOP $shop_name
									 			
					IF NOT $shop_name = $stored_barbers_shop
						GOSUB barbers_cleanup_big
					ENDIF

					GOTO shop_barbers_inner
				ENDIF

			ENDIF  // IF NOT IS_STRING_EMPTY $shop_name


			IF flag_barber = 3
				SET_PLAYER_CONTROL player1 OFF
				SET_MINIGAME_IN_PROGRESS TRUE
				DISPLAY_RADAR FALSE
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				GET_CHAR_COORDINATES scplayer player_x player_y player_z
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_AREA player_x player_y player_z 1.0 TRUE
				TIMERA = 0
				control_flag_barbers = 0
				flag_barber = 4
			ENDIF
		
			IF flag_barber = 4

				IF IS_CHAR_DEAD shop_keep_barbers

					GOSUB barbers_cleanup_small
					
					GET_LOADED_SHOP $shop_name
									 			
					IF NOT $shop_name = $stored_barbers_shop
						GOSUB barbers_cleanup_big
					ENDIF

					GOTO shop_barbers_inner
				ENDIF
			
				IF TIMERA >= 300
					 					
					GET_CHAR_COORDINATES shop_keep_barbers temp_shopX temp_shopY temp_shopZ
						
					IF NOT temp_shopX = barberX
					OR NOT temp_shopY = barberY 
					OR NOT temp_shopZ = barberZ
						SET_CHAR_COORDINATES shop_keep_barbers barberX barberY barberZ
						SET_CHAR_HEADING shop_keep_barbers barber_heading
					ENDIF 
					
					SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer keep_offX keep_offY keep_offZ 
					SET_CHAR_HEADING scplayer player_heading_barbers

					STORE_CLOTHES_STATE
					GET_CLOTHES_ITEM player1 CLOTHES_TEX_HEAD player_item_texture_shops player_item_model_shops // not sure if this is needed here.			
				   
					SET_FIXED_CAMERA_POSITION getin_chaircam_offX getin_chaircam_offY getin_chaircam_offZ 0.0 0.0 0.0 // camera watching player get into chair
					POINT_CAMERA_AT_POINT getin_chaircam_lookat_offX getin_chaircam_lookat_offY getin_chaircam_lookat_offZ JUMP_CUT
					
					return_animation_time_barbers = 0.0
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BRB_sit_in HAIRCUTS 1000.0 FALSE FALSE FALSE TRUE -1

					STORE_SCORE player1 players_money

					IF flag_changed_hair_intro2 = 0
						IF flag_player_on_menace_mission = 1
							IF players_money < 50							
								ADD_SCORE player1 52 
							ENDIF
						ENDIF
					ENDIF
				   				
					PERFORM_SEQUENCE_TASK shop_keep_barbers barber_movein_barbers  
					control_flag_barbers = 0
	 				flag_barber = 5
				ENDIF
			ENDIF
					
			
			IF flag_barber = 5

				IF IS_CHAR_DEAD shop_keep_barbers

					GOSUB barbers_cleanup_small
					
					GET_LOADED_SHOP $shop_name
									 			
					IF NOT $shop_name = $stored_barbers_shop
						GOSUB barbers_cleanup_big
					ENDIF

					GOTO shop_barbers_inner
				ENDIF

				IF IS_CHAR_PLAYING_ANIM scplayer BRB_sit_in			
					GET_CHAR_ANIM_CURRENT_TIME scplayer BRB_sit_in return_animation_time_barbers
				ENDIF
											  
				IF return_animation_time_barbers = 1.0
				    
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BRB_sit_loop HAIRCUTS 1000.0 TRUE FALSE FALSE TRUE -1

					SET_FIXED_CAMERA_POSITION chaircam_offX chaircam_offY chaircam_offZ 0.0 0.0 0.0 // camera looking at player sitting in chair 
					POINT_CAMERA_AT_POINT chaircam_lookat_offX chaircam_lookat_offY chaircam_lookat_offZ JUMP_CUT
					 					
					SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_WHAT_WANT sample_name_shops
					
					GET_CLOTHES_ITEM player1 CLOTHES_TEX_HEAD player_item_texture_shops player_item_model_shops
					control_flag_barbers = 0
					flag_barber = 6
				ENDIF

			ENDIF

			IF flag_barber = 6

				IF IS_CHAR_DEAD shop_keep_barbers

					GOSUB barbers_cleanup_small
					
					GET_LOADED_SHOP $shop_name
									 			
					IF NOT $shop_name = $stored_barbers_shop
						GOSUB barbers_cleanup_big
					ENDIF

					GOTO shop_barbers_inner
				ENDIF
				
				IF control_flag_barbers < 1 	   
					IF main_menu_drawn_shops = 0
						GOSUB fill_main_menu_barbers
						GOSUB draw_main_menu_barbers
					ENDIF
				ENDIF

				IF control_flag_barbers = 0
				    
					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT  // They want to see the hairdo; move to preview.
						control_flag_barbers = 10	 
					ENDIF
					 
					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL  // Leave shop all together.
						control_flag_barbers = 1
					ENDIF
				ENDIF

				IF control_flag_barbers = 1
				    
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL  // Player is going to leave the shop all together.
					
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

						IF main_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU main_menu_shops
							main_menu_drawn_shops = 0
						ENDIF

						IF flag_viewed_hair = 1
							control_flag_barbers = 2  // Put back the hairdo then leave.
						ELSE 
							control_flag_barbers = 5  // Just leave.
						ENDIF	 
					ENDIF
				ENDIF

				IF control_flag_barbers = 2 		
					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_cut_in HAIRCUTS 4.0 FALSE FALSE FALSE TRUE -1 		
					PLAY_MISSION_AUDIO 4
					return_animation_time_barbers = 0.0
					control_flag_barbers = 3
				ENDIF

				IF control_flag_barbers = 3

					IF IS_CHAR_PLAYING_ANIM shop_keep_barbers BRB_cut_in
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_barbers BRB_cut_in return_animation_time_barbers
					ENDIF 
																		
					IF return_animation_time_barbers = 1.0
					
						IF cut_hair_flag_barber = 0					
							TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_cut HAIRCUTS 4.0 FALSE FALSE FALSE FALSE -1
							
							RESTORE_CLOTHES_STATE			
							BUILD_PLAYER_MODEL player1
							STORE_CLOTHES_STATE
							cut_hair_flag_barber = 1
						ENDIF

						IF cut_hair_flag_barber = 1

							IF HAS_MISSION_AUDIO_FINISHED 4
								TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_cut_out HAIRCUTS 4.0 FALSE FALSE FALSE TRUE -1
								return_animation_time_barbers = 0.0
								control_flag_barbers = 4
								cut_hair_flag_barber = 0
							ENDIF
						ENDIF

					ENDIF
				ENDIF

				IF control_flag_barbers = 4
					
					IF IS_CHAR_PLAYING_ANIM shop_keep_barbers BRB_cut_out 
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_barbers BRB_cut_out return_animation_time_barbers
					ENDIF
																											
					IF return_animation_time_barbers = 1.0								
						control_flag_barbers = 5
					ENDIF
				ENDIF
				
				IF control_flag_barbers = 5 		
				 	
					TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BRB_sit_out HAIRCUTS 1000.0 FALSE FALSE FALSE FALSE -1
					return_animation_time_barbers = 0.0

					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_out HAIRCUTS 1000.0 FALSE FALSE FALSE TRUE -1
					TASK_LOOK_AT_CHAR shop_keep_barbers scplayer -2 
		
					SET_FIXED_CAMERA_POSITION getout_chair_cam_offX getout_chair_cam_offY getout_chair_cam_offZ 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT getout_chair_cam_lookat_offX getout_chair_cam_lookat_offY getout_chair_cam_lookat_offZ JUMP_CUT

					control_flag_barbers = 6
				ENDIF

				IF control_flag_barbers = 6 
		            
					IF IS_CHAR_PLAYING_ANIM scplayer BRB_sit_out
						GET_CHAR_ANIM_CURRENT_TIME scplayer BRB_sit_out return_animation_time_barbers
					ENDIF

					IF return_animation_time_barbers >= 0.8
						PERFORM_SEQUENCE_TASK scplayer player_walk_barbers
						SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_THANKS_FOR_CUSTOM sample_name_shops
						SET_FIXED_CAMERA_POSITION leave_barb_cam_offX leave_barb_cam_offY leave_barb_cam_offZ 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT leave_barb_cam_lookat_offX leave_barb_cam_lookat_offY leave_barb_cam_lookat_offZ JUMP_CUT
						TIMERA = 0  
						control_flag_barbers = 7
					ENDIF

				ENDIF

				IF control_flag_barbers = 7
				    
					IF flag_restored_camera_barbers = 0
						IF TIMERA >= 1500
							SET_CAMERA_BEHIND_PLAYER
							RESTORE_CAMERA_JUMPCUT
							flag_restored_camera_barbers = 1
						ENDIF
					ENDIF

					IF flag_barber > 2
				   		CLEAR_HELP	
				   	ENDIF

					GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK player_task_progress_barber

					IF NOT player_task_progress_barber = FINISHED_TASK
						GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK player_task_progress_barber
					ELSE
						control_flag_barbers = 8
					ENDIF 
				ENDIF
				
				IF control_flag_barbers = 8
				    CLEAR_LOOK_AT shop_keep_barbers 
					SET_CHAR_HEADING scplayer 180.0  
					SET_CAMERA_BEHIND_PLAYER
					RESTORE_CAMERA_JUMPCUT
					SET_PLAYER_CONTROL player1 ON
					SET_MINIGAME_IN_PROGRESS FALSE
					DISPLAY_RADAR TRUE
					flag_restored_camera_barbers = 0
				 	control_flag_barbers = 9
				ENDIF

				IF control_flag_barbers = 9
				    
					IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ TRUE
                       	unk9728 = 0
						unk9747 = 0
						flag_viewed_hair = 0
						flag_bought_item_already_shops = 0
						flag_no_money_shops = 0
						flag_restored_camera_barbers = 0
						control_flag_barbers = 0
						flag_barber = 2	
					ENDIF
					
					IF flag_attacked_barber = 0
						IF IS_CHAR_SHOOTING scplayer
						OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_barbers
						OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_barbers WEAPONTYPE_ANYWEAPON
							SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shops
							TASK_HANDS_UP shop_keep_barbers -2
							flag_attacked_barber = 1
						ENDIF
					ENDIF
				ENDIF
				
				
				// Player wants to preview a haircut.
   		 		
				IF control_flag_barbers = 10
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT  // Player accepts the hairdo. Let's preview it.
					    
						GET_MENU_ITEM_ACCEPTED main_menu_shops haircut_picked_barbers
						
						IF haircut_picked_barbers < 0
							haircut_picked_barbers = 0
						ENDIF

						IF main_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU main_menu_shops
							main_menu_drawn_shops = 0
						ENDIF

						GET_ITEM_IN_SHOP haircut_picked_barbers model_index_barbers
						GET_NAME_OF_ITEM model_index_barbers $hairstyle_name
						GET_PRICE_OF_ITEM model_index_barbers hair_price
						
						GET_SHOPPING_EXTRA_INFO model_index_barbers 0 modelid_barber
						GET_SHOPPING_EXTRA_INFO model_index_barbers 1 component_barber

						shop_item_price = hair_price
						$shop_item_label = $hairstyle_name

						SET_FIXED_CAMERA_POSITION chaircam_offX chaircam_offY chaircam_offZ 0.0 0.0 0.0 // camera for player sitting in chair
						POINT_CAMERA_AT_POINT chaircam_lookat_offX chaircam_lookat_offY chaircam_lookat_offZ JUMP_CUT
 
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_cut_in HAIRCUTS 1000.0 FALSE FALSE FALSE TRUE -1
						
						GET_CLOTHES_ITEM player1 CLOTHES_TEX_HEAD player_item_texture_audio_shops player_item_model_audio_shops		

						IF NOT player_item_texture_audio_shops = model_index_barbers
							PLAY_MISSION_AUDIO 4
						ENDIF

						return_animation_time_barbers = 0.0			
						
						control_flag_barbers = 11 
					ENDIF
				ENDIF

				IF control_flag_barbers = 11
							   		  	
					IF IS_CHAR_PLAYING_ANIM shop_keep_barbers BRB_cut_in 
				   		GET_CHAR_ANIM_CURRENT_TIME shop_keep_barbers BRB_cut_in return_animation_time_barbers
				   	ENDIF
			
					IF return_animation_time_barbers = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_cut HAIRCUTS 1000.0 FALSE FALSE FALSE TRUE -1
						GIVE_PLAYER_CLOTHES player1 0 0 16 // should remove any hat
						GIVE_PLAYER_CLOTHES player1 model_index_barbers modelid_barber component_barber  					
						BUILD_PLAYER_MODEL player1
						flag_viewed_hair = 1
						control_flag_barbers = 12
					ENDIF
				ENDIF
				
				IF control_flag_barbers = 12
					IF HAS_MISSION_AUDIO_FINISHED 4																		
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_cut_out HAIRCUTS 1000.0 FALSE FALSE FALSE TRUE -1
						return_animation_time_barbers = 0.0
						control_flag_barbers = 13
					ENDIF
				ENDIF

				IF control_flag_barbers = 13
				
					IF IS_CHAR_PLAYING_ANIM shop_keep_barbers BRB_cut_out 
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_barbers BRB_cut_out return_animation_time_barbers
					ENDIF

					IF return_animation_time_barbers = 1.0			
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_loop HAIRCUTS 1000.0 TRUE FALSE FALSE FALSE -1
						return_animation_time_barbers = 0.0
						control_flag_barbers = 0
						flag_barber = 7 
					ENDIF
				ENDIF
			ENDIF // end flag barber 6

			IF flag_barber = 7

				IF IS_CHAR_DEAD shop_keep_barbers

					GOSUB barbers_cleanup_small
					
					GET_LOADED_SHOP $shop_name
									 			
					IF NOT $shop_name = $stored_barbers_shop
						GOSUB barbers_cleanup_big
					ENDIF

					GOTO shop_barbers_inner
				ENDIF

				IF cost_menu_drawn_shops = 0
					IF control_flag_barbers < 1
						GOSUB display_text_barber
					ENDIF
				ENDIF

				IF control_flag_barbers = 0

					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT  // Player accepts previewed hairdo. Let's purchase it.
                		CLEAR_THIS_PRINT (SHOPNO)
						CLEAR_THIS_PRINT (BARBNO)
						flag_no_money_shops = 0
						flag_bought_item_already_shops = 0
						control_flag_barbers = 2
					ENDIF
					
				    IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL  // Player rejects previewed hairdo.
						CLEAR_THIS_PRINT (SHOPNO)
						CLEAR_THIS_PRINT (BARBNO)
						control_flag_barbers = 1
					ENDIF
				ENDIF
				
				IF control_flag_barbers = 1
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						
						IF cost_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU cost_menu_shops
							cost_menu_drawn_shops = 0
						ENDIF   
				   
						IF main_menu_drawn_shops = 0
							GOSUB fill_main_menu_barbers
							GOSUB draw_main_menu_barbers
						ENDIF
						
						control_flag_barbers = 0  // This will undo any hairdo preview, and walk out of the shop
						flag_barber = 6
					ENDIF
				ENDIF

				IF control_flag_barbers = 2

					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						 													
						flag_bought_item_already_shops = 0
						flag_no_money_shops = 0
	 
						STORE_SCORE player1 players_money
																
						IF players_money >= hair_price

							IF NOT player_item_texture_shops = model_index_barbers
							    
								IF cost_menu_drawn_shops = 1
									DELETE_MENU cost_menu_shops
									CLEAR_HELP
									cost_menu_drawn_shops = 0
								ENDIF
 								 						   			
								SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_GIVE_PRODUCT sample_name_shops
								
								BUY_ITEM model_index_barbers 
								unk9747 = 1
								flag_viewed_hair = 0

								REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY
													
								STORE_CLOTHES_STATE
								GET_CLOTHES_ITEM player1 CLOTHES_TEX_HEAD player_item_texture_shops player_item_model_shops
							  							
								SET_FIXED_CAMERA_POSITION hairstyle_cam_offX hairstyle_cam_offY hairstyle_cam_offZ 0.0 0.0 0.0 // player titavating after changing hairstyle  
								POINT_CAMERA_AT_POINT hairstyle_cam_lookat_offX hairstyle_cam_lookat_offY hairstyle_cam_lookat_offZ JUMP_CUT

								IF $shop_name = barbers

									IF $hairstyle_name = TASH 
									OR $hairstyle_name = GOATEE
									OR $hairstyle_name = BEARD
									OR $hairstyle_name = AFROT
									OR $hairstyle_name = AFROB
									OR $hairstyle_name = AFROGOT
										$name_of_anim_barbers = BRB_beard_01
									ELSE

										IF random_number_barbers > 1
											random_number_barbers = 0
										ENDIF
													
										IF random_number_barbers < 0
											random_number_barbers = 0
										ENDIF
										
										IF random_number_barbers = 0
											$name_of_anim_barbers = BRB_hair_01
											++ random_number_barbers
										ELSE
											$name_of_anim_barbers = BRB_hair_02
											-- random_number_barbers
										ENDIF
										 
									ENDIF

								ENDIF

								IF $shop_name = barber2
																			  
									IF random_number_barbers > 1
										random_number_barbers = 0
									ENDIF
													
									IF random_number_barbers < 0
										random_number_barbers = 0
									ENDIF
										
									IF random_number_barbers = 0
										$name_of_anim_barbers = BRB_hair_01
										++ random_number_barbers
									ELSE
										$name_of_anim_barbers = BRB_hair_02
										-- random_number_barbers
									ENDIF								

								ENDIF

								IF $shop_name = barber3

									IF $hairstyle_name = BLADBEA 
									OR $hairstyle_name = BALDTSH
									OR $hairstyle_name = BALDGOT
										$name_of_anim_barbers = BRB_beard_01
									ELSE

										IF random_number_barbers > 1
											random_number_barbers = 0
										ENDIF
													
										IF random_number_barbers < 0
											random_number_barbers = 0
										ENDIF
										
										IF random_number_barbers = 0
											$name_of_anim_barbers = BRB_hair_01
											++ random_number_barbers
										ELSE
											$name_of_anim_barbers = BRB_hair_02
											-- random_number_barbers
										ENDIF
										 
									ENDIF

								ENDIF
									
								TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_buy HAIRCUTS 1000.0 FALSE FALSE FALSE TRUE -1								
									
								TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer $name_of_anim_barbers HAIRCUTS 1000.0 FALSE FALSE FALSE TRUE -1
			  					return_animation_time_barbers = 0.0

								control_flag_barbers = 3

							ELSE

								IF flag_bought_item_already_shops = 0
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
									SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_BOUGHT_ENOUGH sample_name_shops							
									PRINT_NOW (BARBNO) 5000 1 //"You have already bought this!"
									control_flag_barbers = 0
									flag_bought_item_already_shops = 1
								ENDIF
							ENDIF

						ELSE
							IF flag_no_money_shops = 0						
								REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
								SET_CHAR_SAY_CONTEXT shop_keep_barbers CONTEXT_GLOBAL_NO_MONEY sample_name_shops													   	
								PRINT_NOW (SHOPNO) 5000 1 //"You don't have enough money to buy this."
								control_flag_barbers = 0
								flag_no_money_shops = 1
							ENDIF

						ENDIF

					ENDIF // end of X button pressed

				ENDIF // IF control_flag_barbers = 2


				// Just purchased haircut. play animations then leave the shop.

				IF control_flag_barbers = 3

					IF IS_CHAR_PLAYING_ANIM scplayer $name_of_anim_barbers
						GET_CHAR_ANIM_CURRENT_TIME scplayer $name_of_anim_barbers return_animation_time_barbers
					ENDIF
												   
					IF return_animation_time_barbers = 1.0

						IF bought_menu_drawn_shops = 1
							DELETE_MENU bought_menu_shops
							CLEAR_HELP
							bought_menu_drawn_shops = 0
						ENDIF

						IF cost_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU cost_menu_shops
							cost_menu_drawn_shops = 0
						ENDIF

						TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer BRB_sit_loop HAIRCUTS 1000.0 TRUE FALSE FALSE TRUE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_barbers BRB_loop HAIRCUTS 1000.0 TRUE FALSE FALSE FALSE -1
						 							   					
						IF flag_changed_hair_intro2 = 0

							IF flag_player_on_mission = 1
								flag_changed_hair_intro2 = 1
							ENDIF

						ENDIF

						SET_FIXED_CAMERA_POSITION chaircam_offX chaircam_offY chaircam_offZ 0.0 0.0 0.0 // camera looking at player sitting in chair 
						POINT_CAMERA_AT_POINT chaircam_lookat_offX chaircam_lookat_offY chaircam_lookat_offZ JUMP_CUT

						IF main_menu_drawn_shops = 0
							GOSUB fill_main_menu_barbers
							GOSUB draw_main_menu_barbers
						ENDIF

						control_flag_barbers = 0
						flag_barber = 6

					ENDIF // end animation

				ENDIF // IF control_flag_barbers = 3
				
			ENDIF // end flag = 7

		ELSE
			GOSUB barbers_cleanup_big
		ENDIF // no shops loaded
				
	ELSE // player playing
		GOSUB barbers_cleanup_big
	ENDIF

GOTO shop_barbers_inner
				

barbers_cleanup_small:

	IF flag_barber > 0
		CLEAR_HELP	
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

	IF main_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU main_menu_shops
		main_menu_drawn_shops = 0
	ENDIF

	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (BARBNO)
	
	flag_barber = 2
	flag_restored_camera_barbers = 0
	unk9728 = 0
	unk9747 = 0
	return_animation_time_barbers = 0.0
	flag_viewed_hair = 0
	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0

	cut_hair_flag_barber = 0
			
RETURN

barbers_cleanup_big:
	
	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (BARBNO)
  
	IF flag_barber > 0
		CLEAR_HELP
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

	IF main_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU main_menu_shops
		main_menu_drawn_shops = 0
	ENDIF
	
	barber_look_shops = 0   
	unk9728 = 0
	flag_attacked_barber = 0
	flag_barber = 0
	unk9747 = 0
	return_animation_time_barbers = 0.0
	flag_viewed_hair = 0
	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0
	flag_restored_camera_barbers = 0

	cut_hair_flag_barber = 0
		
	CLEAR_SEQUENCE_TASK barber_movein_barbers
	CLEAR_SEQUENCE_TASK player_walk_barbers
	
	MARK_MODEL_AS_NO_LONGER_NEEDED shopkeeper_model_shops

	DELETE_CHAR shop_keep_barbers

	DISPLAY_RADAR TRUE

	USE_TEXT_COMMANDS FALSE

	SHOW_UPDATE_STATS TRUE

	SET_MINIGAME_IN_PROGRESS FALSE

	CLEAR_MISSION_AUDIO 4

//	SET_PLAYER_IS_IN_STADIUM FALSE

	TERMINATE_THIS_SCRIPT
	
RETURN


display_text_barber:
	IF cost_menu_drawn_shops = 0
		PRINT_HELP_FOREVER BARB_H2
		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU HAIRCHO 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU HAIRCHO 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU HAIRCHO 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU HAIRCHO 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU HAIRCHO 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 0 FO_LEFT 
		SET_MENU_COLUMN cost_menu_shops 0 HAIRSTY $hairstyle_name DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 1 FO_RIGHT
		SET_MENU_COLUMN cost_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_ITEM_WITH_NUMBER cost_menu_shops 1 0 DOLLAR hair_price
		SET_MENU_COLUMN_WIDTH cost_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 1 46 

		cost_menu_drawn_shops = 1
	ENDIF
RETURN


fill_main_menu_barbers:
    
	// Initialize the items for the menu.

	item_no_hairstyles = 0
	number_of_hairstyles_in_area = 0

	WHILE item_no_hairstyles < num_of_hairstyles

		GET_ITEM_IN_SHOP item_no_hairstyles model_index
		GET_SHOPPING_EXTRA_INFO model_index 0 modelid_shops
		GET_SHOPPING_EXTRA_INFO model_index 1 component_shops

		item_model_index[number_of_hairstyles_in_area] = model_index
		
		GET_NAME_OF_ITEM model_index $hairstyle_name
		GET_PRICE_OF_ITEM model_index hair_price
		
		item_price[number_of_hairstyles_in_area] = hair_price  
		$item_text_label[number_of_hairstyles_in_area] = $hairstyle_name
		item_modelid[number_of_hairstyles_in_area] = modelid_shops  
		item_component[number_of_hairstyles_in_area] = component_shops

		IF player_item_texture_shops = model_index
			item_hilight_shops[number_of_hairstyles_in_area] = FALSE
		ELSE
			item_hilight_shops[number_of_hairstyles_in_area] = TRUE
		ENDIF 
						
		++number_of_hairstyles_in_area
		++item_no_hairstyles
    ENDWHILE

    temp_var_shops = number_of_hairstyles_in_area 

    WHILE temp_var_shops < max_number_allowed_in_menu_shops  // Initialize the empty items.
	    item_model_index[temp_var_shops] = -1
	    item_price[temp_var_shops] = 0
	    $item_text_label[temp_var_shops] = DUMMY
	    ++temp_var_shops 
    ENDWHILE 
 	
RETURN


// Draws The Main Menu for the barber shop.

draw_main_menu_barbers:

	IF main_menu_drawn_shops = 0
    
	    // Create and populate the menu (once at startup).

		PRINT_HELP_FOREVER BARB_H1
		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU HAIRCHO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT main_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU HAIRCHO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT main_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU HAIRCHO 29.0 165.0 93.0 2 TRUE TRUE FO_LEFT main_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU HAIRCHO 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT main_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU HAIRCHO 29.0 165.0 93.0 2 TRUE TRUE FO_LEFT main_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION main_menu_shops 0 FO_LEFT 
		SET_MENU_COLUMN main_menu_shops 0 HAIRSTY $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11] 
		SET_MENU_COLUMN_ORIENTATION main_menu_shops 1 FO_RIGHT 
		SET_MENU_COLUMN main_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		
		temp_var_shops = 0 
		WHILE temp_var_shops < number_of_hairstyles_in_area
			SET_MENU_ITEM_WITH_NUMBER main_menu_shops 1 temp_var_shops DOLLAR item_price[temp_var_shops]
			++temp_var_shops
		ENDWHILE

		temp_var_shops = 0 
		WHILE temp_var_shops < number_of_hairstyles_in_area
			ACTIVATE_MENU_ITEM main_menu_shops temp_var_shops item_hilight_shops[temp_var_shops]
			++temp_var_shops
		ENDWHILE
		
		SET_MENU_COLUMN_WIDTH main_menu_shops 0 140
		SET_MENU_COLUMN_WIDTH main_menu_shops 1 46

		main_menu_drawn_shops = 1
	ENDIF
	
RETURN

MISSION_END

}