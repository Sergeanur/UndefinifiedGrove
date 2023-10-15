MISSION_START

{

// Variables
VAR_INT shop_keep_ammunation
VAR_INT weapon_price
VAR_INT num_of_weapons

VAR_INT weapon_obj
VAR_INT created_this_weapon

VAR_TEXT_LABEL $weapon_name

VAR_TEXT_LABEL $stored_weapon_shop $section_selected_ammu

VAR_INT what_slot weapon_ammu model_index_ammu

// used to put animation names into
VAR_TEXT_LABEL16 $anim_name_lift_ammu $anim_name_lift_end_ammu $anim_name_return_start_ammu $anim_name_return_ammu 

VAR_INT shop_alarm_ammu
VAR_INT shop_item_ammu

// player weapon stuff to compare
VAR_INT player_weapontype
VAR_INT player_ammo
VAR_INT player_weapon_model

VAR_FLOAT weapon_offX weapon_offY weapon_offZ

VAR_INT temp_slot // Used to add one on to what_slot as G's code for GET_WEAPON_ITEM_IN_SLOT reads from 1 not 0

//VAR_INT weapon_obj2

VAR_INT flag_ammu

VAR_INT flag_attacked_ammu_keeper

VAR_FLOAT return_animation_time_ammunation shop_keep_heading_ammunation player_heading_ammunation

VAR_INT shop_progress_ammunation

// used to place weapon on counter when it is released from shop keeper hand (if I don't do this if falls through world)
VAR_FLOAT weapon_on_counterX_shops weapon_on_counterY_shops weapon_on_counterZ_shops

weapon_on_counterX_shops = 0.0
weapon_on_counterY_shops = 0.0 
weapon_on_counterZ_shops = 0.0 

// player stand
CONST_FLOAT ammu_player_trueX 296.506
CONST_FLOAT ammu_player_trueY -38.168
CONST_FLOAT ammu_player_trueZ 1000.547

VAR_FLOAT ammu_offX ammu_offY ammu_offZ
ammu_offX = 0.0
ammu_offY = 0.0 
ammu_offZ = 0.0

// shop keep stands
CONST_FLOAT ammu_keep_trueX 296.506
CONST_FLOAT ammu_keep_trueY	-40.350 // -40.300 OLD VALUE
CONST_FLOAT ammu_keep_trueZ 1000.540 // 1000.547

// Camera stuff
CONST_FLOAT cam_ammu_off_trueX 296.585
CONST_FLOAT cam_ammu_off_trueY -38.345
CONST_FLOAT cam_ammu_off_trueZ 1002.236

cam_offx = 0.0
cam_offy = 0.0
cam_offz = 0.0

CONST_FLOAT camera_ammu_point_trueX 296.501
CONST_FLOAT camera_ammu_point_trueY -39.298
CONST_FLOAT camera_ammu_point_trueZ 1001.943

ammu_camera_pointX = 0.0
ammu_camera_pointY = 0.0
ammu_camera_pointZ = 0.0

return_animation_time_ammunation = 0.0

shop_keep_heading_ammunation = 0.0

cost_menu_drawn_shops = 0

ammuX = 0.0
ammuY = 0.0
ammuZ = 0.0

flag_attacked_ammu_keeper = 0

flag_ammu = 0

weapon_offX = 0.0
weapon_offY = 0.0
weapon_offZ = 0.0

player_weapontype = 0
player_ammo = 0
player_weapon_model = 0

cost_menu_drawn_shops = 0
bought_menu_drawn_shops = 0
flag_bought_item_already_shops = 0
flag_no_money_shops = 0

VAR_FLOAT ammu_camera_pointX ammu_camera_pointY ammu_camera_pointZ

VAR_INT player_armour
VAR_INT player_max_armour
player_max_armour = 0
player_armour = 0

VAR_INT flag_buy_ammo_flag
flag_buy_ammo_flag = 0

// new menu stuff for weapons
VAR_INT weapon_count_ammu no_of_pistols no_of_shotguns no_of_microSMG no_of_SMG no_of_assault no_of_thrown no_of_rifle
VAR_INT no_of_armour no_of_heavy pistol_name_menu shotgun_name_menu microSMG_name_menu SMG_name_menu assault_name_menu
VAR_INT rifle_name_menu thrown_name_menu armour_name_menu heavy_name_menu no_of_different_type_weapons
no_of_different_type_weapons = 0
weapon_count_ammu = 0
temp_var_shops = 0 
no_of_pistols = 0
no_of_shotguns = 0
no_of_microSMG = 0
no_of_SMG = 0
no_of_assault = 0
no_of_thrown = 0
no_of_rifle = 0
no_of_armour = 0
no_of_heavy = 0
pistol_name_menu = 0
shotgun_name_menu = 0
microSMG_name_menu = 0
SMG_name_menu = 0
assault_name_menu = 0
rifle_name_menu = 0
thrown_name_menu = 0
armour_name_menu = 0
heavy_name_menu = 0


VAR_INT pistol_array[3] shotgun_array[3] microSMG_array[2] SMG_array[1] assault_array[2] rifle_array[2]
VAR_INT thrown_array[2] armour_array[1] second_menu_weapon[3]

VAR_INT temp_var_ammu main_menu_selection_ammu sub_menu_selection_ammu
temp_var_ammu = 0
main_menu_selection_ammu = 0
sub_menu_selection_ammu = 0 

// Stuff to fool the compiler
GOTO after_ammu_setup

ADD_CONTINUOUS_SOUND ammuX ammuY ammuZ SOUND_BANK_ALARM_LOOP shop_alarm_ammu
CREATE_OBJECT model_index_ammu 1659.635 -1576.514 15.585 shop_item_ammu

CREATE_OBJECT model_index_ammu weapon_offX weapon_offY weapon_offZ weapon_obj
 
after_ammu_setup:

shopkeeper_model_shops = WMYAMMO

REQUEST_MODEL shopkeeper_model_shops
				
REQUEST_MODEL COLT45
REQUEST_MODEL TEC9
REQUEST_MODEL MICRO_UZI
REQUEST_MODEL CHROMEGUN
REQUEST_MODEL GRENADE
REQUEST_MODEL ARMOUR
REQUEST_MODEL MP5LNG
REQUEST_MODEL SAWNOFF
REQUEST_MODEL SILENCED
REQUEST_MODEL SATCHEL
REQUEST_MODEL BOMB
REQUEST_MODEL CUNTGUN
REQUEST_MODEL SNIPER
REQUEST_MODEL AK47
REQUEST_MODEL M4
REQUEST_MODEL DESERT_EAGLE
REQUEST_MODEL SHOTGSPA
										
LOAD_ALL_MODELS_NOW

SET_DEATHARREST_STATE OFF

// AMMUNATION **************************************************************************

	SCRIPT_NAME	AMUNAT
		
shop_ammunation_inner:

	WAIT 0
	
	IF IS_PLAYER_PLAYING player1

		IF NOT IS_STRING_EMPTY $shop_name

			IF flag_ammu > 0
		
				IF HAS_LANGUAGE_CHANGED
					CLEAR_HELP
					DELETE_MENU main_menu_shops
					DELETE_MENU sub_menu_shops
					DELETE_MENU bought_menu_shops
					DELETE_MENU cost_menu_shops
					main_menu_drawn_shops = 0
					sub_menu_drawn_shops = 0 
					cost_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0
					GET_CURRENT_LANGUAGE current_Language
				ENDIF
				
			ENDIF  
		
			IF flag_ammu = 0
										
				IF $shop_name = AMMUN1					
									
					ammu_offX = 0.0
					ammu_offY = 0.0 
					ammu_offZ = 0.0

					player_heading_ammunation = 180.0
					shop_keep_heading_ammunation = 0.0
													  
					blob_flag_shop = 1
												   	
					$stored_weapon_shop = $shop_name 

					shop_progress_ammunation = 0
					flag_ammu = 1

				ENDIF

				IF $shop_name = AMMUN2					
																							
					ammu_offX = -0.765
					ammu_offY = -42.311 
					ammu_offZ = -0.013

					player_heading_ammunation = 180.0
					shop_keep_heading_ammunation = 0.0
													  
					blob_flag_shop = 1
												   	
					$stored_weapon_shop = $shop_name 

					shop_progress_ammunation = 0
					flag_ammu = 1

				ENDIF

				IF $shop_name = AMMUN3					
																							
					ammu_offX =-6.264
					ammu_offY = -71.34
					ammu_offZ = -0.002

					player_heading_ammunation = 180.0
					shop_keep_heading_ammunation = 0.0
													  
					blob_flag_shop = 1
												   	
					$stored_weapon_shop = $shop_name 
					shop_progress_ammunation = 0
					flag_ammu = 1

				ENDIF

				IF $shop_name = AMMUN4					
																							
					ammu_offX = 11.642
					ammu_offY = -102.936 
					ammu_offZ = -1.929

					player_heading_ammunation = 180.0
					shop_keep_heading_ammunation = 0.0
													  
					blob_flag_shop = 1
												   	
					$stored_weapon_shop = $shop_name 

					flag_ammu = 1

				ENDIF

				IF $shop_name = AMMUN5																				

					ammu_offX = 16.285
					ammu_offY = -127.781 
					ammu_offZ = -1.929

					player_heading_ammunation = 180.0
					shop_keep_heading_ammunation = 0.0
													  
					blob_flag_shop = 1
												   	
					$stored_weapon_shop = $shop_name 

					flag_ammu = 1

				ENDIF

			ENDIF // flag = 0
			
			IF flag_ammu = 1					   

				USE_TEXT_COMMANDS TRUE

				SHOW_UPDATE_STATS FALSE

//				SET_PLAYER_IS_IN_STADIUM TRUE
														
				// player
				keep_offX = ammu_player_trueX + ammu_offX 
				keep_offY = ammu_player_trueY + ammu_offY 
				keep_offZ = ammu_player_trueZ + ammu_offZ
				
				// shop keeper
				ammuX = ammu_keep_trueX + ammu_offX   
				ammuY = ammu_keep_trueY + ammu_offY  
				ammuZ = ammu_keep_trueZ + ammu_offZ
				
				// camera stuff
				cam_offx = cam_ammu_off_trueX + ammu_offX   
				cam_offy = cam_ammu_off_trueY + ammu_offY 
				cam_offz = cam_ammu_off_trueZ + ammu_offZ

				ammu_camera_pointX = camera_ammu_point_trueX + ammu_offX  
				ammu_camera_pointY = camera_ammu_point_trueY + ammu_offY 
				ammu_camera_pointZ = camera_ammu_point_trueZ + ammu_offZ 
				
				CREATE_CHAR PEDTYPE_CIVMALE shopkeeper_model_shops ammuX ammuY ammuZ shop_keep_ammunation
				SET_CHAR_HEADING shop_keep_ammunation shop_keep_heading_ammunation 
				SET_CHAR_ONLY_DAMAGED_BY_PLAYER shop_keep_ammunation TRUE
				SET_CHAR_DECISION_MAKER shop_keep_ammunation DM_PED_EMPTY

				IF flag_casino_mission_counter > 2 // SPAZ OPEN all weapons open
					GET_NUMBER_OF_ITEMS_IN_SHOP num_of_weapons
					
					IF flag_16weapons_open = 0
						PRINT_HELP (NEWWEAP) // "A new weapon is now available"
						flag_16weapons_open = 1
					ENDIF
					
				ELSE

					IF flag_desert_mission_counter > 7 // DESERT EAGLE OPEN
						num_of_weapons = 15

						IF flag_15weapons_open = 0
							PRINT_HELP (NEWWEAP) // "A new weapon is now available"
							flag_15weapons_open = 1
						ENDIF

					ELSE

						IF flag_Synd_mission_counter > 9 // M4 OPEN
							num_of_weapons = 14

							IF flag_14weapons_open = 0
								PRINT_HELP (NEWWEAP) // "A new weapon is now available"
								flag_14weapons_open = 1
							ENDIF

						ELSE
							
							IF flag_wuzi_mission_counter > 0 // AK OPEN
								num_of_weapons = 13

								IF flag_13weapons_open = 0
									PRINT_HELP (NEWWEAP) // "A new weapon is now available"
									flag_13weapons_open = 1
								ENDIF

							ELSE
															
								IF flag_Synd_mission_counter > 7 // SNIPER RIFLE OPEN
									num_of_weapons = 12

									IF flag_12weapons_open = 0
										PRINT_HELP (NEWWEAP) // "A new weapon is now available"
										flag_12weapons_open = 1
									ENDIF

								ELSE

									IF flag_truth_mission_counter > 0 // COUNTRY RIFLE OPEN
										num_of_weapons = 11

										IF flag_11weapons_open = 0
											PRINT_HELP (NEWWEAP) // "A new weapon is now available"
											flag_11weapons_open = 1
										ENDIF

									ELSE

										IF flag_cat_mission4_passed = 1 // STACHEL CHARGE OPEN
											num_of_weapons = 10

											IF flag_10weapons_open = 0
												PRINT_HELP (NEWWEAP) // "A new weapon is now available"
												flag_10weapons_open = 1
											ENDIF

										ELSE

											IF flag_strap_mission_counter > 4 // SILENCED PISTOL OPEN
												num_of_weapons = 9

												IF flag_9weapons_open = 0
													PRINT_HELP (NEWWEAP) // "A new weapon is now available"
													flag_9weapons_open = 1
												ENDIF

											ELSE

												IF flag_smoke_mission_counter > 3  // SAWN OFF SHOTGUN OPEN
													num_of_weapons = 8

													IF flag_8weapons_open = 0
														PRINT_HELP (NEWWEAP) // "A new weapon is now available"
														flag_8weapons_open = 1
													ENDIF

												ELSE

													IF flag_ryder_mission_counter > 2 // MP5 OPEN
														num_of_weapons = 7

														IF flag_7weapons_open = 0
															PRINT_HELP (NEWWEAP) // "A new weapon is now available"
															flag_7weapons_open = 1
														ENDIF

													ELSE

														IF flag_la1fin1_mission_counter < 2 // 
															num_of_weapons = 6

															IF flag_6weapons_open = 0
																PRINT_HELP (NEWWEAP) // "A new weapon is now available"
																flag_6weapons_open = 1
															ENDIF

														ENDIF
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF			
				
				flag_ammu = 2

			ENDIF 		 
			
			IF flag_ammu = 2

				GET_LOADED_SHOP $shop_name
										 			
				IF NOT $shop_name = $stored_weapon_shop
					GOSUB ammunation_cleanup_big 
				ENDIF
				
				IF NOT IS_CHAR_DEAD shop_keep_ammunation
					
					IF flag_attacked_ammu_keeper = 0

						IF shop_progress_ammunation = 0
							TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation SHP_Tray_pose WEAPONS 1000.0 TRUE FALSE FALSE FALSE -1
							return_animation_time_ammunation = 0.0	
							shop_progress_ammunation = 1
						ENDIF
						 												
						IF flag_shooting_range_mission = 0

							IF IS_CHAR_SHOOTING scplayer
							OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_ammunation
							OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_ammunation WEAPONTYPE_ANYWEAPON
								SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_SHOP_CLOSED FALSE FALSE TRUE sample_name_shops
								TASK_STAND_STILL shop_keep_ammunation 5
								SET_CHAR_WEAPON_SKILL shop_keep_ammunation WEAPONSKILL_PRO
								GIVE_WEAPON_TO_CHAR shop_keep_ammunation WEAPONTYPE_PISTOL 30000 // set to infinate ammo
								SET_CHAR_ACCURACY shop_keep_ammunation 80
								TASK_STAY_IN_SAME_PLACE shop_keep_ammunation TRUE
								TASK_KILL_CHAR_ON_FOOT shop_keep_ammunation scplayer
								blob_flag_shop = 0 
								flag_attacked_ammu_keeper = 1
							ELSE

								blob_flag_shop = 1
								IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop

									IF CAN_PLAYER_START_MISSION player1

										shop_progress_ammunation = 0
										flag_ammu = 3

									ENDIF

								ENDIF

							ENDIF
							
						ELSE					

							IF IS_PLAYER_TARGETTING_CHAR player1 shop_keep_ammunation
							OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_ammunation WEAPONTYPE_ANYWEAPON
								SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_SHOP_CLOSED FALSE FALSE TRUE sample_name_shops
								TASK_STAND_STILL shop_keep_ammunation 5
								SET_CHAR_WEAPON_SKILL shop_keep_ammunation WEAPONSKILL_PRO
								GIVE_WEAPON_TO_CHAR shop_keep_ammunation WEAPONTYPE_PISTOL 30000 // set to infinate ammo
								SET_CHAR_ACCURACY shop_keep_ammunation 80
								TASK_STAY_IN_SAME_PLACE shop_keep_ammunation TRUE
								TASK_KILL_CHAR_ON_FOOT shop_keep_ammunation scplayer
								blob_flag_shop = 0 
								flag_attacked_ammu_keeper = 1
							ELSE

								blob_flag_shop = 1
								IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop
									shop_progress_ammunation = 0
									flag_ammu = 3
								ENDIF

							ENDIF

						ENDIF
																						 
					ELSE

						GOSUB ammunation_cleanup_small
						
						GET_LOADED_SHOP $shop_name
										 			
						IF NOT $shop_name = $stored_weapon_shop
							GOSUB ammunation_cleanup_big
						ENDIF

						GOTO shop_ammunation_inner

					ENDIF

				ELSE

					GOSUB ammunation_cleanup_small
						
					GET_LOADED_SHOP $shop_name
										 			
					IF NOT $shop_name = $stored_weapon_shop
						GOSUB ammunation_cleanup_big
					ENDIF

					GOTO shop_ammunation_inner

				ENDIF

			ENDIF
			
			IF flag_ammu = 3
				SET_PLAYER_CONTROL player1 OFF
				SET_MINIGAME_IN_PROGRESS TRUE
				SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_AREA player_x player_y player_z 1.0 TRUE
				CLEAR_THIS_PRINT (SHOPNO)
				TIMERA = 0
				flag_ammu = 4
			ENDIF
					
			IF flag_ammu = 4

				IF IS_CHAR_DEAD shop_keep_ammunation

					GOSUB ammunation_cleanup_small
				
					GET_LOADED_SHOP $shop_name
										 			
					IF NOT $shop_name = $stored_weapon_shop
						GOSUB ammunation_cleanup_big
					ENDIF

					GOTO shop_ammunation_inner

				ENDIF

				IF shop_progress_ammunation = 0

					IF TIMERA >= 300
					
						SET_CHAR_COORDINATES_DONT_WARP_GANG scplayer keep_offX keep_offY keep_offZ 
						SET_CHAR_HEADING scplayer player_heading_ammunation
											 
						GET_CHAR_COORDINATES shop_keep_ammunation temp_shopX temp_shopY temp_shopZ
								
						IF NOT temp_shopX = ammuX
						OR NOT temp_shopY = ammuY 
						OR NOT temp_shopZ = ammuZ
							SET_CHAR_COORDINATES shop_keep_ammunation ammuX ammuY ammuZ
							SET_CHAR_HEADING shop_keep_ammunation shop_keep_heading_ammunation
						ENDIF 
								  
						
//						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation SHP_Tray_In WEAPONS 4.0 FALSE FALSE FALSE TRUE -1
//						return_animation_time_ammunation = 0.0

//						IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation SHP_Tray_In
//							GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation SHP_Tray_In return_animation_time_ammunation
//						ENDIF
											
						SET_FIXED_CAMERA_POSITION cam_offx cam_offy cam_offz 0.0 0.0 0.0
						POINT_CAMERA_AT_POINT ammu_camera_pointX ammu_camera_pointY ammu_camera_pointZ JUMP_CUT

						SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_WHAT_WANT FALSE FALSE TRUE sample_name_shops

						IF flag_on_doberman_mission = 1
							flag_bought_gun_for_doberman = 1
						ENDIF 
												
						IF IS_PLAYER_PLAYING player1
							GET_CHAR_COORDINATES scplayer player_x player_y player_z
						ELSE
							GOSUB ammunation_cleanup_big
							GOTO shop_ammunation_inner
						ENDIF

						DISPLAY_RADAR FALSE

						flag_ammu = 5
						shop_progress_ammunation = 0

					ENDIF

				ENDIF

			ENDIF // flag_ammu = 4

			// ******************** SHOPKEEPER IN READY POSE PLAYER CAN NOW SHOP ******************
			IF flag_ammu = 5

				IF IS_CHAR_DEAD shop_keep_ammunation

					GOSUB ammunation_cleanup_small
				
					GET_LOADED_SHOP $shop_name
										 			
					IF NOT $shop_name = $stored_weapon_shop
						GOSUB ammunation_cleanup_big
					ENDIF

					GOTO shop_ammunation_inner

				ENDIF

				IF shop_progress_ammunation < 1

					IF main_menu_drawn_shops = 0
						GOSUB fill_main_menu_ammu
						GOSUB draw_main_menu_ammu
					ENDIF

				ENDIF
																
				IF shop_progress_ammunation = 0				
			
					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						shop_progress_ammunation = 3						
					ENDIF
					
					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						shop_progress_ammunation = 1
					ENDIF 

				ENDIF

				IF shop_progress_ammunation = 1
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

						IF main_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU main_menu_shops
							main_menu_drawn_shops = 0
						ENDIF

						IF sub_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU sub_menu_shops
							sub_menu_drawn_shops = 0
						ENDIF

						IF cost_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU cost_menu_shops
							cost_menu_drawn_shops = 0
						ENDIF

						IF bought_menu_drawn_shops = 1
							DELETE_MENU bought_menu_shops
							bought_menu_drawn_shops = 0
						ENDIF
						
						CLEAR_THIS_PRINT (AMMUA)
						CLEAR_THIS_PRINT (SHOPNO)
						CLEAR_THIS_PRINT (AMMUC)
						CLEAR_THIS_PRINT (AMMUD)
							
						SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_THANKS_FOR_CUSTOM FALSE FALSE TRUE sample_name_shops

						RESTORE_CAMERA_JUMPCUT
						SET_PLAYER_CONTROL player1 ON
						SET_MINIGAME_IN_PROGRESS FALSE

						DISPLAY_RADAR TRUE
						
						shop_progress_ammunation = 2
						
					ENDIF
					
				ENDIF
				
				IF shop_progress_ammunation = 2
					
					IF flag_attacked_ammu_keeper = 0

						IF IS_CHAR_SHOOTING scplayer
						OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_ammunation
						OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_ammunation WEAPONTYPE_ANYWEAPON
							SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_SHOP_CLOSED FALSE FALSE TRUE sample_name_shops
							TASK_STAND_STILL shop_keep_ammunation 5
							SET_CHAR_WEAPON_SKILL shop_keep_ammunation WEAPONSKILL_PRO
							GIVE_WEAPON_TO_CHAR shop_keep_ammunation WEAPONTYPE_PISTOL 30000 // set to infinate ammo
							SET_CHAR_ACCURACY shop_keep_ammunation 80
							TASK_STAY_IN_SAME_PLACE shop_keep_ammunation TRUE
							TASK_KILL_CHAR_ON_FOOT shop_keep_ammunation scplayer 
							flag_attacked_ammu_keeper = 1
						ENDIF

					ENDIF

					IF NOT LOCATE_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ FALSE
					 
						main_menu_drawn_shops = 0
						sub_menu_drawn_shops = 0
						created_this_weapon = 0
						cost_menu_drawn_shops = 0
						bought_menu_drawn_shops = 0
						flag_ammu = 2
						shop_progress_ammunation = 0
					ENDIF
	
				ENDIF
 				
				IF shop_progress_ammunation = 3

					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

					    GET_MENU_ITEM_ACCEPTED main_menu_shops main_menu_selection_ammu
					    
						$section_selected_ammu = $item_text_label[main_menu_selection_ammu]
						
						IF main_menu_selection_ammu < 0
							main_menu_selection_ammu = 0
						ENDIF 

						IF main_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU main_menu_shops
							main_menu_drawn_shops = 0
						ENDIF

						IF sub_menu_drawn_shops = 0
							GOSUB draw_second_menu_shops
						ENDIF

						shop_progress_ammunation = 0
						flag_ammu = 6

					ENDIF

				ENDIF

			ENDIF // END FLAG AMMU = 5

			IF flag_ammu = 6

				IF IS_CHAR_DEAD shop_keep_ammunation

					GOSUB ammunation_cleanup_small
				
					GET_LOADED_SHOP $shop_name
										 			
					IF NOT $shop_name = $stored_weapon_shop
						GOSUB ammunation_cleanup_big
					ENDIF

					GOTO shop_ammunation_inner

				ENDIF

				IF shop_progress_ammunation < 1

					IF sub_menu_drawn_shops = 0
						GOSUB draw_second_menu_shops
					ENDIF

				ENDIF

				IF shop_progress_ammunation = 0
				
					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT						 
						shop_progress_ammunation = 2		
					ENDIF

					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						shop_progress_ammunation = 1	
					ENDIF
				ENDIF
				
				IF shop_progress_ammunation = 1
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

						IF sub_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU sub_menu_shops
							sub_menu_drawn_shops = 0
						ENDIF
						
						IF main_menu_drawn_shops = 0
							GOSUB fill_main_menu_ammu
							GOSUB draw_main_menu_ammu
						ENDIF

						shop_progress_ammunation = 0
						flag_ammu = 5
												
					ENDIF
					
				ENDIF
				
				IF shop_progress_ammunation = 2
				    
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

					    GET_MENU_ITEM_ACCEPTED sub_menu_shops sub_menu_selection_ammu
					   
						IF sub_menu_selection_ammu < 0
							sub_menu_selection_ammu = 0
						ENDIF
						
						weapon_ammu = second_menu_weapon[sub_menu_selection_ammu]
						 
						GET_NAME_OF_ITEM weapon_ammu $weapon_name
						GET_PRICE_OF_ITEM weapon_ammu weapon_price

						IF NOT weapon_ammu = WEAPONTYPE_ARMOUR
							GET_WEAPONTYPE_SLOT weapon_ammu what_slot
							GET_WEAPONTYPE_MODEL weapon_ammu model_index_ammu
						ELSE
							model_index_ammu = ARMOUR
						ENDIF										
						
						IF sub_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU sub_menu_shops
							sub_menu_drawn_shops = 0
						ENDIF

						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation SHP_G_lift_in WEAPONS 1000.0 FALSE FALSE FALSE TRUE -1
						return_animation_time_ammunation = 0.0

						IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation SHP_G_lift_in
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation SHP_G_lift_in return_animation_time_ammunation
						ENDIF
					
						shop_progress_ammunation = 3

					ENDIF
															
				ENDIF
				
				IF shop_progress_ammunation = 3

					IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation SHP_G_lift_in
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation SHP_G_lift_in return_animation_time_ammunation
					ENDIF

					IF return_animation_time_ammunation = 1.0
						shop_progress_ammunation = 4
					ENDIF

				ENDIF

				IF shop_progress_ammunation = 4
																												
					CREATE_OBJECT model_index_ammu ammuX ammuY ammuZ weapon_obj
						 					 
					GOSUB find_what_anims_to_load_ammu
					GOSUB put_away_anim_ammu

					TASK_PICK_UP_OBJECT shop_keep_ammunation weapon_obj 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
					FREEZE_OBJECT_POSITION weapon_obj FALSE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation $anim_name_lift_ammu WEAPONS 1000.0 FALSE FALSE FALSE TRUE -1
					return_animation_time_ammunation = 0.0
					
					IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_lift_ammu
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_lift_ammu return_animation_time_ammunation
					ENDIF

					shop_progress_ammunation = 5

				ENDIF

				IF shop_progress_ammunation = 5

					IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_lift_ammu
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_lift_ammu return_animation_time_ammunation
					ENDIF

					IF return_animation_time_ammunation = 1.0
						DROP_OBJECT shop_keep_ammunation FALSE
						REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_AMMUNATION_GUN_COLLISION
					   	SET_OBJECT_COORDINATES weapon_obj weapon_on_counterX_shops weapon_on_counterY_shops weapon_on_counterZ_shops
						FREEZE_OBJECT_POSITION weapon_obj TRUE
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation $anim_name_lift_end_ammu WEAPONS 1000.0 FALSE FALSE FALSE TRUE -1
						return_animation_time_ammunation = 0.0
						
						IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_lift_end_ammu
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_lift_end_ammu return_animation_time_ammunation
						ENDIF

						shop_progress_ammunation = 6
					ENDIF
					 
				ENDIF

				IF shop_progress_ammunation = 6
					
					IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_lift_end_ammu
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_lift_end_ammu return_animation_time_ammunation
					ENDIF

					IF return_animation_time_ammunation = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation SHP_tray_pose WEAPONS 1000.0 TRUE FALSE FALSE FALSE -1
						return_animation_time_ammunation = 0.0

						IF cost_menu_drawn_shops = 0
							GOSUB print_ammunation_oncreen_text
						ENDIF
						
						IF NOT weapon_ammu = WEAPONTYPE_ARMOUR
							temp_slot = what_slot + 1
			 				GET_CHAR_WEAPON_IN_SLOT scplayer temp_slot player_weapontype player_ammo player_weapon_model // player_weapon_slot
			   			
							IF NOT player_weapontype = weapon_ammu 
							
					 			IF player_ammo > 0
									PRINT_NOW (AMMUA) 5000 1 //"Buying this weapon will replace your current one."
					 			ENDIF

							ENDIF

						ENDIF	
						
						flag_ammu = 7
						shop_progress_ammunation = 0
					ENDIF
					
				ENDIF

			ENDIF

			// WAITING FOR PLAYER TO BUY OR GO BACK TO WEAPON SELECT MENU
			IF flag_ammu = 7

				IF IS_CHAR_DEAD shop_keep_ammunation

					GOSUB ammunation_cleanup_small
				
					GET_LOADED_SHOP $shop_name
										 			
					IF NOT $shop_name = $stored_weapon_shop
						GOSUB ammunation_cleanup_big
					ENDIF

					GOTO shop_ammunation_inner

				ENDIF

				IF shop_progress_ammunation < 1

					IF cost_menu_drawn_shops = 0
						GOSUB print_ammunation_oncreen_text
					ENDIF

				ENDIF

				IF shop_progress_ammunation = 0

					IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
						shop_progress_ammunation = 5
					ENDIF
					
					IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						shop_progress_ammunation = 1
					ENDIF
										
				ENDIF
				
				// PLAYER DECIDED TO GO BACK TO WEAPON TYPE MENU
				IF shop_progress_ammunation = 1
				
					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
						
						IF cost_menu_drawn_shops = 1
							CLEAR_HELP
							DELETE_MENU cost_menu_shops
							cost_menu_drawn_shops = 0
						ENDIF

						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation $anim_name_return_start_ammu WEAPONS 1000.0 FALSE FALSE FALSE TRUE -1	
						return_animation_time_ammunation = 0.0
						
						IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_return_start_ammu
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_return_start_ammu return_animation_time_ammunation
						ENDIF

						shop_progress_ammunation = 2					
					ENDIF

				ENDIF
				
				IF shop_progress_ammunation = 2

					IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_return_start_ammu
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_return_start_ammu return_animation_time_ammunation
					ENDIF

					IF return_animation_time_ammunation = 1.0
						TASK_PICK_UP_OBJECT shop_keep_ammunation weapon_obj 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE
						FREEZE_OBJECT_POSITION weapon_obj FALSE
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation $anim_name_return_ammu WEAPONS 1000.0 FALSE FALSE FALSE TRUE -1	
						return_animation_time_ammunation = 0.0

						IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_return_ammu
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_return_ammu return_animation_time_ammunation
						ENDIF

						shop_progress_ammunation = 3
					ENDIF
					  
				ENDIF

				IF shop_progress_ammunation = 3

					IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation $anim_name_return_ammu
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation $anim_name_return_ammu return_animation_time_ammunation
					ENDIF

					IF return_animation_time_ammunation = 1.0
						DROP_OBJECT shop_keep_ammunation FALSE
						SET_OBJECT_COORDINATES weapon_obj weapon_on_counterX_shops weapon_on_counterY_shops weapon_on_counterZ_shops
						FREEZE_OBJECT_POSITION weapon_obj TRUE
						GOSUB delete_all_weapons

						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation shp_G_lift_out WEAPONS 1000.0 FALSE FALSE FALSE TRUE -1	
						return_animation_time_ammunation = 0.0

						IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation shp_G_lift_out
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation shp_G_lift_out return_animation_time_ammunation
						ENDIF

						shop_progress_ammunation = 4
					ENDIF

				ENDIF

				IF shop_progress_ammunation = 4

					IF IS_CHAR_PLAYING_ANIM shop_keep_ammunation shp_G_lift_out
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_ammunation shp_G_lift_out return_animation_time_ammunation
					ENDIF

					IF return_animation_time_ammunation = 1.0
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_ammunation SHP_Tray_pose WEAPONS 1000.0 TRUE FALSE FALSE FALSE -1	
						return_animation_time_ammunation = 0.0
						shop_progress_ammunation = 0
						flag_ammu = 6
					ENDIF
					
				ENDIF

				IF shop_progress_ammunation = 5

					IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
											
						CLEAR_THIS_PRINT (AMMUA)
						CLEAR_THIS_PRINT (SHOPNO)
						CLEAR_THIS_PRINT (AMMUC)
						CLEAR_THIS_PRINT (AMMUD)
						flag_no_money_shops = 0
						flag_buy_ammo_flag = 0
													
						STORE_SCORE player1 players_money
																	
						IF players_money >= weapon_price
					
							IF NOT weapon_ammu = WEAPONTYPE_ARMOUR
							 						
			 					GET_AMMO_IN_CHAR_WEAPON scplayer weapon_ammu player_ammo
																 						
								IF player_ammo < 9999							   
									BUY_ITEM weapon_ammu
									SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_GIVE_PRODUCT FALSE FALSE TRUE sample_name_shops
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_AMMUNATION_BUY_WEAPON
									 										 			
									shop_progress_ammunation = 6
									TIMERB = 0
																							
								ELSE
	
									IF flag_buy_ammo_flag = 0 
								 		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_AMMUNATION_BUY_WEAPON_DENIED
									 	
										PRINT_NOW (AMMUC) 5000 1 //"You cannot buy any more ammunition for this weapon."
										SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_BOUGHT_ENOUGH FALSE FALSE TRUE sample_name_shops
										shop_progress_ammunation = 0
										flag_buy_ammo_flag = 1
									ENDIF
	
								ENDIF
	
							ELSE

								GET_CHAR_ARMOUR scplayer player_armour
								GET_PLAYER_MAX_ARMOUR player1 player_max_armour
	
								IF player_armour < player_max_armour
									SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_GIVE_PRODUCT FALSE FALSE TRUE sample_name_shops 		
									BUY_ITEM weapon_ammu
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_AMMUNATION_BUY_WEAPON
									shop_progress_ammunation = 6
									TIMERB = 0
	
								ELSE

									IF flag_buy_ammo_flag = 0
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_AMMUNATION_BUY_WEAPON_DENIED
									 	SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_BOUGHT_ENOUGH FALSE FALSE TRUE sample_name_shops	
										PRINT_NOW (AMMUD) 5000 1 //"You cannot buy any more armour."
										shop_progress_ammunation = 0
										flag_buy_ammo_flag = 1
									ENDIF

								ENDIF 

							ENDIF
						
						ELSE
							
							IF flag_no_money_shops = 0
							 	REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_AMMUNATION_BUY_WEAPON_DENIED
								SET_CHAR_SAY_CONTEXT_IMPORTANT shop_keep_ammunation CONTEXT_GLOBAL_NO_MONEY FALSE FALSE TRUE sample_name_shops
								PRINT_NOW (SHOPNO) 5000 1 //"You don't have enough money to buy this."
								shop_progress_ammunation = 0
								flag_no_money_shops = 1
							ENDIF

						ENDIF
										
					ENDIF

				ENDIF // END SHOP PROGRESS = 5
				
				// Printing up the bought text
				IF shop_progress_ammunation = 6

					IF cost_menu_drawn_shops = 1
						DELETE_MENU cost_menu_shops
						CLEAR_HELP
						cost_menu_drawn_shops = 0
					ENDIF
					
					IF bought_menu_drawn_shops = 0
						IF IS_XBOX_VERSION
							CREATE_MENU AMMUN 29.0 48.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops
						ELSE
							CREATE_MENU AMMUN 29.0 20.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops
						ENDIF
						SET_MENU_COLUMN_ORIENTATION bought_menu_shops 0 FO_LEFT 
						SET_MENU_COLUMN bought_menu_shops 0 WEAPON BOUGHT DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
						SET_MENU_COLUMN_ORIENTATION bought_menu_shops 1 FO_CENTRE 
						SET_MENU_COLUMN bought_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
						SET_MENU_ITEM_WITH_NUMBER bought_menu_shops 1 0 DOLLAR weapon_price 
						SET_MENU_COLUMN_WIDTH bought_menu_shops 0 140 
						SET_MENU_COLUMN_WIDTH bought_menu_shops 1 46
						bought_menu_drawn_shops = 1
					ENDIF

					TIMERB = 0

					shop_progress_ammunation = 7

				ENDIF
				
				IF shop_progress_ammunation = 7					 
				
					IF TIMERB >= 1000

						IF bought_menu_drawn_shops = 1
							DELETE_MENU bought_menu_shops
							bought_menu_drawn_shops = 0
						ENDIF

						shop_progress_ammunation = 0

						IF shop_progress_ammunation < 1
						
							IF cost_menu_drawn_shops = 0 
								GOSUB print_ammunation_oncreen_text
							ENDIF

						ENDIF
						
					ENDIF
					
				ENDIF // END SHOP PROGRESS = 7  		
											
			ENDIF
					
		ELSE
			GOSUB ammunation_cleanup_big
		ENDIF // string empty	 					I
									
	ELSE
		GOSUB ammunation_cleanup_big
	ENDIF // player playing

GOTO shop_ammunation_inner

 						
// prints the text to the screen
print_ammunation_oncreen_text:
	IF cost_menu_drawn_shops = 0
		PRINT_HELP_FOREVER (AMMU_H3)  
		
		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU AMMUN 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU AMMUN 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU AMMUN 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU AMMUN 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU AMMUN 29.0 95.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 0 FO_LEFT 
		SET_MENU_COLUMN cost_menu_shops 0 WEAPON $weapon_name DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 1 FO_CENTRE 
		SET_MENU_COLUMN cost_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_ITEM_WITH_NUMBER cost_menu_shops 1 0 DOLLAR weapon_price 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 1 46 

		cost_menu_drawn_shops = 1
	ENDIF
RETURN

// small cleanup
ammunation_cleanup_small:

	IF main_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU main_menu_shops
		main_menu_drawn_shops = 0
	ENDIF

	IF sub_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU sub_menu_shops
		sub_menu_drawn_shops = 0
	ENDIF

	IF cost_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU cost_menu_shops
		cost_menu_drawn_shops = 0
	ENDIF

	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops
		bought_menu_drawn_shops = 0
	ENDIF

	IF flag_ammu >= 1
		CLEAR_HELP	
	ENDIF

	CLEAR_THIS_PRINT (AMMUA)
	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (AMMUC)
	CLEAR_THIS_PRINT AMMUD)

	main_menu_drawn_shops = 0
	sub_menu_drawn_shops = 0
	created_this_weapon = 0
	shop_progress_ammunation = 0
	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0

	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0
	flag_buy_ammo_flag = 0

	flag_ammu = 2		
	
RETURN

// big cleanup
ammunation_cleanup_big:

	IF main_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU main_menu_shops
		main_menu_drawn_shops = 0
	ENDIF

	IF sub_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU sub_menu_shops
		sub_menu_drawn_shops = 0
	ENDIF

	IF cost_menu_drawn_shops = 1
		CLEAR_HELP
		DELETE_MENU cost_menu_shops
		cost_menu_drawn_shops = 0
	ENDIF

	IF bought_menu_drawn_shops = 1
		DELETE_MENU bought_menu_shops
		bought_menu_drawn_shops = 0
	ENDIF

	IF flag_ammu >= 1
		CLEAR_HELP
	ENDIF

	CLEAR_THIS_PRINT (AMMUA)
	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (AMMUC)
	CLEAR_THIS_PRINT (AMMUD)
	
	main_menu_drawn_shops = 0
	sub_menu_drawn_shops = 0	
	
   	flag_attacked_ammu_keeper = 0
   	created_this_weapon = 0
	shop_progress_ammunation = 0
	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0

	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0
	flag_buy_ammo_flag = 0

	flag_ammu = 0
		
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED SILENCED
	MARK_MODEL_AS_NO_LONGER_NEEDED DESERT_EAGLE
	MARK_MODEL_AS_NO_LONGER_NEEDED CHROMEGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED SAWNOFF
	MARK_MODEL_AS_NO_LONGER_NEEDED SHOTGSPA
	MARK_MODEL_AS_NO_LONGER_NEEDED GRENADE
	MARK_MODEL_AS_NO_LONGER_NEEDED MICRO_UZI
	MARK_MODEL_AS_NO_LONGER_NEEDED MP5LNG
	MARK_MODEL_AS_NO_LONGER_NEEDED TEC9
	MARK_MODEL_AS_NO_LONGER_NEEDED AK47
	MARK_MODEL_AS_NO_LONGER_NEEDED M4
	MARK_MODEL_AS_NO_LONGER_NEEDED CUNTGUN
	MARK_MODEL_AS_NO_LONGER_NEEDED SNIPER
	MARK_MODEL_AS_NO_LONGER_NEEDED ARMOUR
	MARK_MODEL_AS_NO_LONGER_NEEDED SATCHEL
	MARK_MODEL_AS_NO_LONGER_NEEDED BOMB
 		 
	USE_TEXT_COMMANDS FALSE

	MARK_MODEL_AS_NO_LONGER_NEEDED shopkeeper_model_shops 

	DELETE_CHAR shop_keep_ammunation

	SHOW_UPDATE_STATS TRUE

	DISPLAY_RADAR TRUE

	SET_MINIGAME_IN_PROGRESS FALSE

//	SET_PLAYER_IS_IN_STADIUM FALSE

	IF trigger_new_range_level = 1
    	PRINT_HELP ANR_56  // A new weapon challenge is available.
        trigger_new_range_level = 0
	ENDIF

	TERMINATE_THIS_SCRIPT
	
RETURN

// deletes the gun I created from the menu
delete_all_weapons:

	DELETE_OBJECT weapon_obj
		
RETURN

// finds what animations to use
find_what_anims_to_load_ammu:

IF model_index_ammu = CHROMEGUN
OR model_index_ammu = SHOTGSPA
OR model_index_ammu = AK47
OR model_index_ammu = M4
OR model_index_ammu = SNIPER
OR model_index_ammu = CUNTGUN

	$anim_name_lift_ammu = shp_2h_lift
	$anim_name_lift_end_ammu = shp_2h_lift_end

	weapon_on_counterX_shops = ammuX + 0.231
	weapon_on_counterY_shops = ammuY + 0.636 
	weapon_on_counterZ_shops = ammuZ + 1.053

ENDIF

IF model_index_ammu = SAWNOFF

	$anim_name_lift_ammu = shp_2h_lift
	$anim_name_lift_end_ammu = shp_2h_lift_end

	weapon_on_counterX_shops = ammuX + 0.231
	weapon_on_counterY_shops = ammuY + 0.636 
	weapon_on_counterZ_shops = ammuZ + 1.053

ENDIF

IF model_index_ammu = COLT45
OR model_index_ammu = SILENCED
OR model_index_ammu = DESERT_EAGLE
OR model_index_ammu = GRENADE
OR model_index_ammu = MICRO_UZI
OR model_index_ammu = MP5LNG

	$anim_name_lift_ammu = shp_1h_lift
	$anim_name_lift_end_ammu = shp_1h_lift_end

	weapon_on_counterX_shops = ammuX + 0.108
	weapon_on_counterY_shops = ammuY + 0.654 
	weapon_on_counterZ_shops = ammuZ + 1.053

ENDIF

IF model_index_ammu = SATCHEL
OR model_index_ammu = TEC9

	$anim_name_lift_ammu = shp_1h_lift
	$anim_name_lift_end_ammu = shp_1h_lift_end

	weapon_on_counterX_shops = ammuX + 0.108
	weapon_on_counterY_shops = ammuY + 0.654 
	weapon_on_counterZ_shops = ammuZ + 1.053

ENDIF

IF model_index_ammu = ARMOUR

	$anim_name_lift_ammu = shp_ar_lift
	$anim_name_lift_end_ammu = shp_ar_lift_end

	weapon_on_counterX_shops = ammuX + 0.175
	weapon_on_counterY_shops = ammuY + 0.676 
	weapon_on_counterZ_shops = ammuZ + 1.500 // 1.483


ENDIF   

RETURN

// anim of shop guy taking away anim
put_away_anim_ammu:

IF model_index_ammu = CHROMEGUN
OR model_index_ammu = SHOTGSPA
OR model_index_ammu = AK47
OR model_index_ammu = M4
OR model_index_ammu = SNIPER
OR model_index_ammu = CUNTGUN

	$anim_name_return_start_ammu = shp_2h_ret_s
	$anim_name_return_ammu = shp_2h_ret 

ENDIF

IF model_index_ammu = SAWNOFF

	$anim_name_return_start_ammu = shp_2h_ret_s
	$anim_name_return_ammu = shp_2h_ret 

ENDIF


IF model_index_ammu = COLT45
OR model_index_ammu = SILENCED
OR model_index_ammu = DESERT_EAGLE
OR model_index_ammu = GRENADE
OR model_index_ammu = MICRO_UZI
OR model_index_ammu = MP5LNG

	$anim_name_return_start_ammu = shp_1h_ret_s
	$anim_name_return_ammu = shp_1h_ret

ENDIF

IF model_index_ammu = SATCHEL
OR model_index_ammu = TEC9

	$anim_name_return_start_ammu = shp_1h_ret_s
	$anim_name_return_ammu = shp_1h_ret

ENDIF

IF model_index_ammu = ARMOUR

	$anim_name_return_start_ammu = shp_ar_ret_s
	$anim_name_return_ammu = shp_ar_ret
	
ENDIF

RETURN

// fills in the main ammu menu
fill_main_menu_ammu:

weapon_count_ammu = 0
no_of_different_type_weapons = 0
temp_var_shops = 0
no_of_pistols = 0
no_of_shotguns = 0
no_of_microSMG = 0
no_of_SMG = 0
no_of_assault = 0
no_of_thrown = 0
no_of_rifle = 0
no_of_armour = 0
no_of_heavy = 0
pistol_name_menu = 0
shotgun_name_menu = 0
microSMG_name_menu = 0
SMG_name_menu = 0
assault_name_menu = 0
rifle_name_menu = 0
thrown_name_menu = 0
armour_name_menu = 0
heavy_name_menu = 0

WHILE weapon_count_ammu < num_of_weapons

	GET_ITEM_IN_SHOP weapon_count_ammu weapon_ammu
	
	SWITCH weapon_ammu

		CASE WEAPONTYPE_PISTOL
		CASE WEAPONTYPE_PISTOL_SILENCED
		CASE WEAPONTYPE_DESERT_EAGLE

			pistol_array[no_of_pistols] = weapon_ammu 

			++ no_of_pistols

			IF pistol_name_menu = 0			   
				$item_text_label[no_of_different_type_weapons] = GUN1 // PISTOL
				++ no_of_different_type_weapons
				pistol_name_menu = 1				
			ENDIF
				
		BREAK

		CASE WEAPONTYPE_SHOTGUN
		CASE WEAPONTYPE_SAWNOFF_SHOTGUN
		CASE WEAPONTYPE_SPAS12_SHOTGUN
			
			shotgun_array[no_of_shotguns] = weapon_ammu

			++ no_of_shotguns

			IF shotgun_name_menu = 0
				$item_text_label[no_of_different_type_weapons] = GUN2 // SHOTGUN
				++ no_of_different_type_weapons
				shotgun_name_menu = 1				
			ENDIF
				
		BREAK

		CASE WEAPONTYPE_MICRO_UZI
		CASE WEAPONTYPE_TEC9
			
			microSMG_array[no_of_microSMG] = weapon_ammu

			++no_of_microSMG

			IF microSMG_name_menu = 0
				$item_text_label[no_of_different_type_weapons] = GUN3 // MICROSMG
				++no_of_different_type_weapons
				microSMG_name_menu = 1
			ENDIF
							
		BREAK

		CASE WEAPONTYPE_MP5

			SMG_array[no_of_SMG] = weapon_ammu

			++no_of_SMG

			IF SMG_name_menu = 0	
				$item_text_label[no_of_different_type_weapons] = GUN4 // SMG
				++no_of_different_type_weapons
				SMG_name_menu = 1		
			ENDIF
							
		BREAK

		CASE WEAPONTYPE_AK47
		CASE WEAPONTYPE_M4

			assault_array[no_of_assault] = weapon_ammu

			++no_of_assault

			IF assault_name_menu = 0		
				$item_text_label[no_of_different_type_weapons] = GUN5 // ASSAULT
				++no_of_different_type_weapons
				assault_name_menu = 1
			ENDIF
													
		BREAK

		CASE WEAPONTYPE_SNIPERRIFLE
		CASE WEAPONTYPE_COUNTRYRIFLE

			rifle_array[no_of_rifle] = weapon_ammu

			++no_of_rifle

			IF rifle_name_menu = 0		
				$item_text_label[no_of_different_type_weapons] = GUN6 // RIFLE
				++no_of_different_type_weapons
				rifle_name_menu = 1
			ENDIF
			 													
		BREAK

		CASE WEAPONTYPE_GRENADE
		CASE WEAPONTYPE_REMOTE_SATCHEL_CHARGE

			thrown_array[no_of_thrown] = weapon_ammu

			++no_of_thrown

			IF thrown_name_menu = 0
				$item_text_label[no_of_different_type_weapons] = GUN7 // THROWN
				++ no_of_different_type_weapons
				thrown_name_menu = 1
			ENDIF
			 													
		BREAK

		CASE WEAPONTYPE_ARMOUR

			armour_array[no_of_armour] = weapon_ammu

			++no_of_armour
			 		   
			IF armour_name_menu = 0
				$item_text_label[no_of_different_type_weapons] = GUN8 // ARMOUR
				++ no_of_different_type_weapons
				armour_name_menu = 1		
			ENDIF
						 													
		BREAK

	ENDSWITCH	
 
	++ weapon_count_ammu
	
ENDWHILE

temp_var_shops = no_of_different_type_weapons

WHILE temp_var_shops < max_number_allowed_in_menu_shops
	item_model_index[temp_var_shops] = -1
	item_price[temp_var_shops] = 0
	$item_text_label[temp_var_shops] = DUMMY
	++temp_var_shops 
ENDWHILE

RETURN


// Draws The Main Menu for the ammu
draw_main_menu_ammu:

	IF main_menu_drawn_shops = 0
	    
		PRINT_HELP_FOREVER AMMU_H
		
		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU AMMUN 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION main_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN main_menu_shops 0 DUMMY $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11]

		main_menu_drawn_shops = 1

	ENDIF
RETURN

// draws the second menu of the shops
draw_second_menu_shops:

    temp_var_ammu = 0

	IF $section_selected_ammu = GUN1 // PISTOL

		WHILE temp_var_ammu < no_of_pistols
			GET_NAME_OF_ITEM pistol_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = pistol_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	IF $section_selected_ammu = GUN2 // SHOTGUN

		WHILE temp_var_ammu < no_of_shotguns
			GET_NAME_OF_ITEM shotgun_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = shotgun_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	IF $section_selected_ammu = GUN3 // MICROSMG 

		WHILE temp_var_ammu < no_of_microSMG
			GET_NAME_OF_ITEM microSMG_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = microSMG_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	IF $section_selected_ammu = GUN4 // SMG

		WHILE temp_var_ammu < no_of_SMG
			GET_NAME_OF_ITEM SMG_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = SMG_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	IF $section_selected_ammu = GUN5 // PISTOL

		WHILE temp_var_ammu < no_of_assault
			GET_NAME_OF_ITEM assault_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = assault_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	IF $section_selected_ammu = GUN6 // RIFLE

		WHILE temp_var_ammu < no_of_rifle
			GET_NAME_OF_ITEM rifle_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = rifle_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	IF $section_selected_ammu = GUN7 // THROWN

		WHILE temp_var_ammu < no_of_thrown
			GET_NAME_OF_ITEM thrown_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = thrown_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	IF $section_selected_ammu = GUN8 // ARMOUR

		WHILE temp_var_ammu < no_of_armour
			GET_NAME_OF_ITEM armour_array[temp_var_ammu] $weapon_name
			$item_text_label[temp_var_ammu] = $weapon_name
			second_menu_weapon[temp_var_ammu] = armour_array[temp_var_ammu]
			++temp_var_ammu
		ENDWHILE

	ENDIF

	// Fill in excess items.

	temp_var_shops = temp_var_ammu

	WHILE temp_var_shops < max_number_allowed_in_menu_shops
		item_model_index[temp_var_shops] = -1
		item_price[temp_var_shops] = 0
		$item_text_label[temp_var_shops] = DUMMY
		++temp_var_shops 
	ENDWHILE

	IF sub_menu_drawn_shops = 0

	    PRINT_HELP_FOREVER AMMU_H2  

		// Create the sub menu.

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU AMMUN 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU AMMUN 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION sub_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN sub_menu_shops 0 DUMMY $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11]

		sub_menu_drawn_shops = 1

	ENDIF
	
RETURN
 
MISSION_END

}