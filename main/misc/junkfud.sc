MISSION_START

{

VAR_INT shop_keep_junkfud jfud_keep_created food_price
VAR_INT found_a_food_shop
VAR_INT num_of_food_stuffs						  
VAR_INT ffood_obj
VAR_INT created_this_food

VAR_TEXT_LABEL $food_name
																		 
VAR_INT pish_flag

VAR_INT current_food_item

VAR_TEXT_LABEL $stored_shop

VAR_INT flag_ate_too_much_food

VAR_INT flag_food // used to stop script redoing parts it should not

VAR_INT flag_bought_some_food

VAR_INT vomit_foodshop

VAR_FLOAT vomitX vomitY vomitZ

VAR_FLOAT return_animation_time_food

VAR_FLOAT players_fat

VAR_INT players_health

VAR_FLOAT food_heading // players heading when he enters the shop

VAR_INT flag_vomit_playing_food

VAR_FLOAT junkfudX junkfudY junkfudZ junkfud_heading

// variables for where the shop dude stands
CONST_FLOAT junkfud_trueX 374.0 
CONST_FLOAT	junkfud_trueY -117.141
CONST_FLOAT junkfud_trueZ 1000.539 // used to be 1000.637
CONST_FLOAT junkfud_true_heading 180.0


// offset variables cant be const as they change
VAR_FLOAT junkfud_offsetX junkfud_offsetY junkfud_offsetZ junkfud_heading_offset

junkfud_offsetX = 0.0  
junkfud_offsetY = 0.0
junkfud_offsetZ = 0.0
junkfud_heading_offset = 0.0

// camera that looks at server this is the default camera in PIZA
CONST_FLOAT junkfud_cam_trueX 374.0000 // 373.980
CONST_FLOAT junkfud_cam_trueY -119.1869 // -119.005
CONST_FLOAT junkfud_cam_trueZ 1002.0190 //1002.172

VAR_FLOAT junkfud_cam_offX junkfud_cam_offY junkfud_cam_offZ
junkfud_cam_offX = 0.0
junkfud_cam_offY = 0.0
junkfud_cam_offZ = 0.0

CONST_FLOAT junkfud_cam_point_trueX 373.9532  // 374.064  
CONST_FLOAT junkfud_cam_point_trueY -118.1890 // -118.052
CONST_FLOAT junkfud_cam_point_trueZ 1001.9753 // 1001.879

VAR_FLOAT cam_point_junkfudX cam_point_junkfudY cam_point_junnkfudZ

cam_point_junkfudX = 0.0
cam_point_junkfudY = 0.0
cam_point_junnkfudZ = 0.0

// variables for the food types
VAR_INT foodsmall_junkfud foodmed_junkfud foodlarge_junkfud foodhealthy_junkfud

// vomit camera
CONST_FLOAT vomit_cam_trueX 374.717
CONST_FLOAT vomit_cam_trueY -122.550
CONST_FLOAT vomit_cam_trueZ 1002.572

VAR_FLOAT vomit_camX vomit_camY vomit_camZ
vomit_camX = 0.0
vomit_camY = 0.0
vomit_camZ = 0.0

CONST_FLOAT vomit_cam_point_trueX 374.599  
CONST_FLOAT vomit_cam_point_trueY -121.608
CONST_FLOAT vomit_cam_point_trueZ 1002.256

VAR_FLOAT vomit_cam_point_junkfudX vomit_cam_point_junkfudY vomit_cam_point_junnkfudZ
vomit_cam_point_junkfudX = 0.0
vomit_cam_point_junkfudY = 0.0
vomit_cam_point_junnkfudZ = 0.0

flag_vomit_playing_food = 0

food_heading = 0.0

shop_item_price = 0

vomitX = 0.0
vomitY = 0.0
vomitZ = 0.0

pish_flag = 0

skip_shopping_wait = 0

found_a_food_shop = 0

//counter_meals_bought_food = 0
flag_ate_too_much_food = 0
flag_bought_some_food = 0
flag_attacked_keeper_food = 0
return_animation_time_food = 0.0

blob_flag_shop = 1

cost_menu_drawn_shops = 0
bought_menu_drawn_shops = 0

flag_no_money_shops = 0

VAR_INT sample_name_shops

// requeting models
IF $shop_name = FDpiza
	shopkeeper_model_shops = WMYPIZZ			
	foodsmall_junkfud = pizzalow
	foodmed_junkfud = pizzamed 
	foodlarge_junkfud = pizzahigh
	foodhealthy_junkfud = pizza_healthy
ENDIF

IF $shop_name = FDchick				
	shopkeeper_model_shops = WMYBELL
	foodsmall_junkfud = clucklow
	foodmed_junkfud = cluckmed 
	foodlarge_junkfud = cluckhigh
	foodhealthy_junkfud = cluck_healthy
ENDIF

IF $shop_name = FDburg
	shopkeeper_model_shops = WFYBURG
	foodsmall_junkfud = burgerlow
	foodmed_junkfud = burgermed 
	foodlarge_junkfud = burgerhigh
	foodhealthy_junkfud = burger_healthy
ENDIF

REQUEST_MODEL foodsmall_junkfud 
REQUEST_MODEL foodmed_junkfud
REQUEST_MODEL foodlarge_junkfud
REQUEST_MODEL foodhealthy_junkfud
LOAD_MISSION_AUDIO 4 SOUND_BANK_RESTAURANT

REQUEST_MODEL shopkeeper_model_shops

LOAD_ALL_MODELS_NOW
// end of requesting models
 
SET_DEATHARREST_STATE OFF

IF pish_flag = 1000000
 CREATE_OBJECT model_index tray_offX tray_offY tray_offZ ffood_obj	
ENDIF 

// FOOD LA**************************************************************************

SCRIPT_NAME jfud
		
shop_junkfud_inner:

	WAIT 0
	
	IF IS_PLAYER_PLAYING player1

		IF NOT IS_STRING_EMPTY $shop_name

			IF flag_food > 0
		
				IF HAS_LANGUAGE_CHANGED
					CLEAR_HELP
					DELETE_MENU cost_menu_shops
					DELETE_MENU bought_menu_shops
					cost_menu_drawn_shops = 0
					bought_menu_drawn_shops = 0
					GET_CURRENT_LANGUAGE current_Language				
			  	ENDIF
			  	
			ENDIF   		
		   	
			IF flag_food = 0
				
				// PIZZA SHOP		
				IF $shop_name = FDpiza
										 										
					GET_NUMBER_OF_ITEMS_IN_SHOP num_of_food_stuffs
				   				
					junkfud_offsetX = 0.0
					junkfud_offsetY = 0.0
					junkfud_offsetZ = 0.0
					junkfud_heading_offset = 0.0

					keep_off_dirX = 0.0
					keep_off_dirY = 2.5
					
					$stored_shop = $shop_name

					flag_food = 1

				ENDIF

				// CHICKEN WINGS
				IF $shop_name = FDchick
													 										
					GET_NUMBER_OF_ITEMS_IN_SHOP num_of_food_stuffs
				   				
					junkfud_offsetX = -5.211
					junkfud_offsetY = 112.784
					junkfud_offsetZ = 0.3
					junkfud_heading_offset = 0.0

					keep_off_dirX = 0.0
					keep_off_dirY = 2.5
					 
					$stored_shop = $shop_name
	
					flag_food = 1
				ENDIF
			 
				// BURGER SHOP			
				IF $shop_name = FDburg
										
					GET_NUMBER_OF_ITEMS_IN_SHOP num_of_food_stuffs

					keep_off_dirX = 0.0 
					keep_off_dirY = 2.5
					 				  
					junkfud_offsetX = 1.566
					junkfud_offsetY = 51.419
					junkfud_offsetZ = 0.01
					junkfud_heading_offset = 0.0

					$stored_shop = $shop_name
	
					flag_food = 1
					
				ENDIF							
					
			ENDIF

			IF flag_food = 1
				 				   												
				USE_TEXT_COMMANDS TRUE

				SHOW_UPDATE_STATS FALSE

				junkfudX = junkfud_trueX + junkfud_offsetX
				junkfudY = junkfud_trueY + junkfud_offsetY
				junkfudZ = junkfud_trueZ + junkfud_offsetZ
				junkfud_heading = junkfud_true_heading + junkfud_heading_offset

				junkfud_cam_offX = junkfud_cam_trueX + junkfud_offsetX
				junkfud_cam_offY = junkfud_cam_trueY + junkfud_offsetY
				junkfud_cam_offZ = junkfud_cam_trueZ + junkfud_offsetZ

				cam_point_junkfudX = junkfud_cam_point_trueX + junkfud_offsetX
				cam_point_junkfudY = junkfud_cam_point_trueY + junkfud_offsetY
				cam_point_junnkfudZ = junkfud_cam_point_trueZ + junkfud_offsetZ

				vomit_camX = vomit_cam_trueX + junkfud_offsetX
				vomit_camY = vomit_cam_trueY + junkfud_offsetY
				vomit_camZ = vomit_cam_trueZ + junkfud_offsetZ

				vomit_cam_point_junkfudX = vomit_cam_point_trueX + junkfud_offsetX
				vomit_cam_point_junkfudY = vomit_cam_point_trueY + junkfud_offsetY
				vomit_cam_point_junnkfudZ = vomit_cam_point_trueZ + junkfud_offsetZ
				
				IF jfud_keep_created = 0
				   	CREATE_CHAR PEDTYPE_CIVMALE shopkeeper_model_shops junkfudX junkfudY junkfudZ shop_keep_junkfud
				   	SET_CHAR_HEADING shop_keep_junkfud junkfud_heading 
		   			SET_CHAR_ONLY_DAMAGED_BY_PLAYER shop_keep_junkfud TRUE
					SET_CHAR_DECISION_MAKER shop_keep_junkfud DM_PED_EMPTY
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS shop_keep_junkfud keep_off_dirX keep_off_dirY 0.0 keep_offX keep_offY keep_offZ
					flag_attacked_keeper_food = 0
		   			jfud_keep_created = 1
		   		ENDIF			 

				flag_food = 2
				
			ENDIF
			
			// player in shop
			IF flag_food = 2
			
				GET_LOADED_SHOP $shop_name

				IF NOT $shop_name = $stored_shop
					GOSUB junkfud_cleanup_big
				ENDIF
				  
				IF NOT IS_CHAR_DEAD shop_keep_junkfud

					IF flag_attacked_keeper_food = 0

						IF IS_CHAR_SHOOTING scplayer
						OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_junkfud
						OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_junkfud WEAPONTYPE_ANYWEAPON
						OR iSetPizzaPanic = 1
							SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shop
							TASK_HANDS_UP shop_keep_junkfud -2
							iSetPizzaPanic = 1 
							flag_attacked_keeper_food = 1
						ELSE
												   
							IF total_food_bought_per_day_shops < 11  // 20 

								blob_flag_shop = 1
								IF LOCATE_STOPPED_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ blob_flag_shop

									IF CAN_PLAYER_START_MISSION player1
										SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_WHAT_WANT sample_name_shop
										STORE_SCORE player1 players_money

										IF flag_menace_buyfood < 2

											IF flag_player_on_menace_mission = 1
											
												IF players_money < 2								
													ADD_SCORE player1 2 
												ENDIF

											ENDIF
											
										ENDIF
										
										flag_food = 3
									ENDIF

								ENDIF

							ELSE

								IF flag_ate_too_much_food = 0

									IF IS_CHAR_DEAD shop_keep_junkfud
										SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_BOUGHT_ENOUGH sample_name_shop
										PRINT_NOW (FOOD1) 5000 1 //"You cannot buy any more food at the moment, you will be unwell!
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED 
									ELSE
										GOSUB junkfud_cleanup_small

										GET_LOADED_SHOP $shop_name

									   	IF NOT $shop_name = $stored_shop
											GOSUB junkfud_cleanup_big
										ENDIF

										GOTO shop_junkfud_inner

									ENDIF

									flag_ate_too_much_food = 1
								ENDIF
								
								GET_LOADED_SHOP $shop_name

								IF NOT $shop_name = $stored_shop
									GOSUB junkfud_cleanup_big
								ENDIF 

							ENDIF
													   
						ENDIF

					ELSE

						GOSUB junkfud_cleanup_small
							
						GET_LOADED_SHOP $shop_name

						IF NOT $shop_name = $stored_shop
							GOSUB junkfud_cleanup_big
						ENDIF

						GOTO shop_junkfud_inner

					ENDIF

				ELSE

					GOSUB junkfud_cleanup_small
							
					GET_LOADED_SHOP $shop_name

					IF NOT $shop_name = $stored_shop
						GOSUB junkfud_cleanup_big
					ENDIF

					GOTO shop_junkfud_inner

				ENDIF

			ENDIF

			IF  flag_food = 3							
				SET_PLAYER_CONTROL player1 OFF
				SET_MINIGAME_IN_PROGRESS TRUE
				SET_CHAR_COORDINATES_DONT_WARP_GANG_NO_OFFSET scplayer keep_offX keep_offY keep_offZ  
				GET_CHAR_COORDINATES scplayer player_x player_y player_z
				SET_CHAR_HEADING scplayer food_heading
				GET_CHAR_HEALTH scplayer players_health
				GET_FLOAT_STAT FAT players_fat
				CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
				CLEAR_AREA player_x player_y player_z 1.0 TRUE

				IF NOT IS_CHAR_DEAD shop_keep_junkfud
					GET_CHAR_COORDINATES shop_keep_junkfud temp_shopX temp_shopY temp_shopZ 

					IF NOT temp_shopX = junkfudX
					OR NOT temp_shopY = junkfudY
					OR NOT temp_shopZ = junkfudZ
				   		SET_CHAR_COORDINATES shop_keep_junkfud junkfudX junkfudY junkfudZ
						SET_CHAR_HEADING shop_keep_junkfud junkfud_heading  
					ENDIF

					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS shop_keep_junkfud 0.0 0.8 0.1 tray_offX tray_offY tray_offZ

					IF NOT IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Pose
						TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_In FOOD 4.0 FALSE FALSE FALSE TRUE -1
						return_animation_time_food = 0.0
					ENDIF

					SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_WHAT_WANT sample_name_shops

					IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_In
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_In return_animation_time_food
					ENDIF
				ELSE
					GOSUB junkfud_cleanup_small
							
					GET_LOADED_SHOP $shop_name

					IF NOT $shop_name = $stored_shop
						GOSUB junkfud_cleanup_big
					ENDIF

					GOTO shop_junkfud_inner
				ENDIF

				TIMERA = 0

				WHILE TIMERA >= 600

					WAIT 0

					IF NOT IS_PLAYER_PLAYING player1
						GOSUB junkfud_cleanup_big
						GOTO shop_junkfud_inner  
					ENDIF

					IF IS_CHAR_DEAD shop_keep_junkfud
						GOSUB junkfud_cleanup_small

						GET_LOADED_SHOP $shop_name

					   	IF NOT $shop_name = $stored_shop
							GOSUB junkfud_cleanup_big
						ENDIF

						GOTO shop_junkfud_inner
					ELSE

						IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_In
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_In return_animation_time_food
						ENDIF

					ENDIF

				ENDWHILE
				
				IF IS_PLAYER_PLAYING player1
					SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
									
					SET_FIXED_CAMERA_POSITION junkfud_cam_offX junkfud_cam_offY junkfud_cam_offZ 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT cam_point_junkfudX cam_point_junkfudY cam_point_junnkfudZ JUMP_CUT
				ELSE
					GOSUB junkfud_cleanup_big
					GOTO shop_junkfud_inner
				ENDIF
				
				IF NOT IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Pose 			    

					// into serving position
					WHILE NOT return_animation_time_food = 1.0
						
						WAIT 0
						 												
						IF NOT IS_PLAYER_PLAYING player1
							GOSUB junkfud_cleanup_big
							GOTO shop_junkfud_inner  
						ENDIF

						IF IS_CHAR_DEAD shop_keep_junkfud
							GOSUB junkfud_cleanup_small

							GET_LOADED_SHOP $shop_name

						   	IF NOT $shop_name = $stored_shop
								GOSUB junkfud_cleanup_big
							ENDIF

							GOTO shop_junkfud_inner
						ELSE

							IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_In
								GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_In return_animation_time_food
							ENDIF

						ENDIF
						
					ENDWHILE

				ENDIF

				IF NOT IS_CHAR_DEAD shop_keep_junkfud
				
					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Pose FOOD 4.0 FALSE FALSE FALSE TRUE -1
					return_animation_time_food = 0.0

					IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Pose
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Pose return_animation_time_food
					ENDIF
				ELSE
					GOSUB junkfud_cleanup_small
							
					GET_LOADED_SHOP $shop_name

					IF NOT $shop_name = $stored_shop
						GOSUB junkfud_cleanup_big
					ENDIF

					GOTO shop_junkfud_inner
				ENDIF
				
				// set into serving loop
				WHILE NOT return_animation_time_food = 1.0
				
					WAIT 0

					IF NOT IS_PLAYER_PLAYING player1
						GOSUB junkfud_cleanup_big
						GOTO shop_junkfud_inner  
					ENDIF

					IF IS_CHAR_DEAD shop_keep_junkfud
						GOSUB junkfud_cleanup_small

						GET_LOADED_SHOP $shop_name

					   	IF NOT $shop_name = $stored_shop
							GOSUB junkfud_cleanup_big
						ENDIF

						GOTO shop_junkfud_inner
					ELSE

						IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Pose
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Pose return_animation_time_food
						ENDIF

					ENDIF
					
				ENDWHILE
				
				IF NOT IS_CHAR_DEAD shop_keep_junkfud
				
					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Lift_In FOOD 4.0 FALSE FALSE FALSE TRUE -1
					return_animation_time_food = 0.0

					IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_In
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_In return_animation_time_food
					ENDIF
				ELSE
					GOSUB junkfud_cleanup_small
							
					GET_LOADED_SHOP $shop_name

					IF NOT $shop_name = $stored_shop
						GOSUB junkfud_cleanup_big
					ENDIF

					GOTO shop_junkfud_inner
				ENDIF

				// bending to lift tray
				WHILE NOT return_animation_time_food = 1.0

					WAIT 0
					
					IF NOT IS_PLAYER_PLAYING player1
						GOSUB junkfud_cleanup_big
						GOTO shop_junkfud_inner  
					ENDIF

					IF IS_CHAR_DEAD shop_keep_junkfud
						GOSUB junkfud_cleanup_small

						GET_LOADED_SHOP $shop_name

					   	IF NOT $shop_name = $stored_shop
							GOSUB junkfud_cleanup_big
						ENDIF

						GOTO shop_junkfud_inner
					ELSE

						IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_In
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_In return_animation_time_food
						ENDIF

					ENDIF
				
				ENDWHILE
				
				REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_RESTAURANT_TRAY_COLLISION				
				current_food_item = 0
				created_this_food = 0

				GET_ITEM_IN_SHOP current_food_item model_index
				CREATE_OBJECT model_index tray_offX tray_offY tray_offZ ffood_obj	
			   	
				GET_PRICE_OF_ITEM model_index food_price
				
				GET_NAME_OF_ITEM model_index $food_name

				shop_item_price = food_price
				$shop_item_label = $food_name

				IF NOT IS_CHAR_DEAD shop_keep_junkfud
				  	TASK_PICK_UP_OBJECT shop_keep_junkfud ffood_obj 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE // SHP_Tray_Lift FOOD TRUE
					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Lift FOOD 1000.0 FALSE FALSE FALSE TRUE -1							

					return_animation_time_food = 0.0
					created_this_food = 1

					IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift return_animation_time_food
					ENDIF
				ELSE
					GOSUB junkfud_cleanup_small
							
					GET_LOADED_SHOP $shop_name

					IF NOT $shop_name = $stored_shop
						GOSUB junkfud_cleanup_big
					ENDIF

					GOTO shop_junkfud_inner
				ENDIF

				// Guy lifts tray of food up to counter
				WHILE NOT return_animation_time_food = 1.0

					WAIT 0
					
					IF NOT IS_PLAYER_PLAYING player1
						GOSUB junkfud_cleanup_big
						GOTO shop_junkfud_inner  
					ENDIF

					IF IS_CHAR_DEAD shop_keep_junkfud
						GOSUB junkfud_cleanup_small

						GET_LOADED_SHOP $shop_name

					   	IF NOT $shop_name = $stored_shop
							GOSUB junkfud_cleanup_big
						ENDIF

						GOTO shop_junkfud_inner
					ELSE

						IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift
							GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift return_animation_time_food
						ENDIF

					ENDIF
				
				ENDWHILE

				IF NOT IS_CHAR_DEAD shop_keep_junkfud
					TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Lift_Loop FOOD 1000.0 TRUE FALSE FALSE FALSE -1	
					return_animation_time_food = 0.0

					IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_Loop
						GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_Loop return_animation_time_food
					ENDIF
				ELSE
					GOSUB junkfud_cleanup_small
							
					GET_LOADED_SHOP $shop_name

					IF NOT $shop_name = $stored_shop
						GOSUB junkfud_cleanup_big
					ENDIF

					GOTO shop_junkfud_inner
				ENDIF
				
				flag_food = 4
								
			ENDIF
			
			IF flag_food = 4
			
				IF flag_ate_too_much_food = 0
	 				  
					GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY

					IF LStickX < -100
					OR IS_BUTTON_PRESSED PAD1 DPADLEFT

						IF cost_menu_drawn_shops = 1
							DELETE_MENU cost_menu_shops
							CLEAR_HELP
							cost_menu_drawn_shops = 0
						ENDIF	
						flag_no_money_shops = 0
						current_food_item--
						TIMERA = 0		

				   		WHILE TIMERA < 150

					   		WAIT 0

							IF NOT IS_PLAYER_PLAYING player1
								GOSUB junkfud_cleanup_big
								GOTO shop_junkfud_inner  
							ENDIF

							IF IS_CHAR_DEAD shop_keep_junkfud
								GOSUB junkfud_cleanup_small

								GET_LOADED_SHOP $shop_name

							   	IF NOT $shop_name = $stored_shop
									GOSUB junkfud_cleanup_big
								ENDIF

								GOTO shop_junkfud_inner
							ENDIF

					   	ENDWHILE	

						flag_bought_some_food = 0
						created_this_food = 0
					ENDIF

					IF LStickX > 100
					OR IS_BUTTON_PRESSED PAD1 DPADRIGHT
						
						IF cost_menu_drawn_shops = 1
							DELETE_MENU cost_menu_shops
							CLEAR_HELP
							cost_menu_drawn_shops = 0
						ENDIF						

						flag_no_money_shops = 0
						current_food_item++
						TIMERA = 0

				   		WHILE TIMERA < 150

					   		WAIT 0

							IF NOT IS_PLAYER_PLAYING player1
								GOSUB junkfud_cleanup_big
								GOTO shop_junkfud_inner  
							ENDIF

							IF IS_CHAR_DEAD shop_keep_junkfud
								GOSUB junkfud_cleanup_small

								GET_LOADED_SHOP $shop_name

							   	IF NOT $shop_name = $stored_shop
									GOSUB junkfud_cleanup_big
								ENDIF

								GOTO shop_junkfud_inner
							ENDIF

					   	ENDWHILE

						created_this_food = 0
						flag_bought_some_food = 0
					ENDIF

					IF current_food_item < 0
						current_food_item = num_of_food_stuffs - 1
					ENDIF

					IF current_food_item = num_of_food_stuffs
						current_food_item = 0
					ENDIF

					IF created_this_food = 0

						IF NOT IS_CHAR_DEAD shop_keep_junkfud

							TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Return FOOD 4.0 FALSE FALSE FALSE TRUE -1	
							return_animation_time_food = 0.0
							
							IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Return
								GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Return return_animation_time_food
							ENDIF
						ELSE
							GOSUB junkfud_cleanup_small
									
							GET_LOADED_SHOP $shop_name

							IF NOT $shop_name = $stored_shop
								GOSUB junkfud_cleanup_big
							ENDIF

							GOTO shop_junkfud_inner
						ENDIF

						// shop keeper putting the tray away animation
						WHILE NOT return_animation_time_food = 1.0

							WAIT 0
							
							IF NOT IS_PLAYER_PLAYING player1
								GOSUB junkfud_cleanup_big
								GOTO shop_junkfud_inner  
							ENDIF

							IF IS_CHAR_DEAD shop_keep_junkfud
								GOSUB junkfud_cleanup_small

								GET_LOADED_SHOP $shop_name

							   	IF NOT $shop_name = $stored_shop
									GOSUB junkfud_cleanup_big
								ENDIF

								GOTO shop_junkfud_inner
							ELSE

								IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Return
									GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Return return_animation_time_food
								ENDIF

							ENDIF
						
						ENDWHILE
						
						GOSUB delete_all_food
									
						GET_ITEM_IN_SHOP current_food_item model_index
						CREATE_OBJECT model_index tray_offX tray_offY tray_offZ ffood_obj	
						GET_PRICE_OF_ITEM model_index food_price
						
						GET_NAME_OF_ITEM model_index $food_name

						REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_RESTAURANT_TRAY_COLLISION

						shop_item_price = food_price
						$shop_item_label = $food_name

						IF NOT IS_CHAR_DEAD shop_keep_junkfud
						  	TASK_PICK_UP_OBJECT shop_keep_junkfud ffood_obj 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL FALSE // SHP_Tray_Lift FOOD TRUE
							TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Lift FOOD 1000.0 FALSE FALSE FALSE TRUE -1	
							return_animation_time_food = 0.0
						   
							IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift
								GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift return_animation_time_food
							ENDIF
						ELSE
							GOSUB junkfud_cleanup_small
									
							GET_LOADED_SHOP $shop_name

							IF NOT $shop_name = $stored_shop
								GOSUB junkfud_cleanup_big
							ENDIF

							GOTO shop_junkfud_inner
						ENDIF

						// Guy lifts tray of food up to counter
						WHILE NOT return_animation_time_food = 1.0

							WAIT 0
							
							IF NOT IS_PLAYER_PLAYING player1
								GOSUB junkfud_cleanup_big
								GOTO shop_junkfud_inner  
							ENDIF

							IF IS_CHAR_DEAD shop_keep_junkfud
								GOSUB junkfud_cleanup_small

								GET_LOADED_SHOP $shop_name

							   	IF NOT $shop_name = $stored_shop
									GOSUB junkfud_cleanup_big
								ENDIF

								GOTO shop_junkfud_inner
							ELSE

								IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift
									GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift return_animation_time_food
								ENDIF
							ENDIF
						ENDWHILE

						IF NOT IS_CHAR_DEAD shop_keep_junkfud
							TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Lift_Loop FOOD 1000.0 TRUE FALSE FALSE FALSE -1	
							return_animation_time_food = 0.0

							IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_Loop
								GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_Loop return_animation_time_food
							ENDIF
						ELSE
							GOSUB junkfud_cleanup_small
									
							GET_LOADED_SHOP $shop_name

							IF NOT $shop_name = $stored_shop
								GOSUB junkfud_cleanup_big
							ENDIF

							GOTO shop_junkfud_inner
						ENDIF
												
						created_this_food = 1	 
						
					ENDIF  // created_this_food = 0	 
					

					IF flag_ate_too_much_food = 0
						GOSUB display_text_food
					ENDIF


					// Look for a button press to buy the item.

					IF IS_BUTTON_PRESSED PAD1 CROSS
										    
						WHILE IS_BUTTON_PRESSED PAD1 CROSS
						    
							WAIT 0
                            
							IF NOT IS_PLAYER_PLAYING player1
								GOSUB junkfud_cleanup_big
								GOTO shop_junkfud_inner  
							ENDIF

							IF IS_CHAR_DEAD shop_keep_junkfud
								GOSUB junkfud_cleanup_small
                                GET_LOADED_SHOP $shop_name

								IF NOT $shop_name = $stored_shop
									GOSUB junkfud_cleanup_big
								ENDIF

								GOTO shop_junkfud_inner
							ENDIF

						ENDWHILE

						flag_no_money_shops = 0
						CLEAR_THIS_PRINT (SHOPNO)
						CLEAR_THIS_PRINT (FOOD1)

						IF IS_PLAYER_PLAYING player1 
							STORE_SCORE player1 players_money
						ELSE
							GOSUB junkfud_cleanup_big
							GOTO shop_junkfud_inner
						ENDIF
															
						IF players_money >= food_price
							  							   
							IF total_food_bought_per_day_shops < 11 // 20
								
								BUY_ITEM model_index
								flag_bought_some_food = 1

								IF HAS_MISSION_AUDIO_LOADED 4
                                 	IF HAS_MISSION_AUDIO_FINISHED 4
										REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_RESTAURANT_CJ_EAT
									ENDIF
								ENDIF

								IF flag_store_day_food = 0
						            GET_GAME_TIMER stored_shop_time
						            flag_store_day_food = 1
						        ENDIF

								REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY

								++total_food_bought_per_day_shops
							
								IF NOT IS_CHAR_DEAD shop_keep_junkfud
									SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_GIVE_PRODUCT sample_name_shop
								ELSE

									GOSUB junkfud_cleanup_small

									GET_LOADED_SHOP $shop_name

									IF NOT $shop_name = $stored_shop
										GOSUB junkfud_cleanup_big
									ENDIF

									GOTO shop_junkfud_inner
									
								ENDIF

								TIMERA = 0

								IF cost_menu_drawn_shops = 1
									DELETE_MENU cost_menu_shops
									CLEAR_HELP
									cost_menu_drawn_shops = 0	
								ENDIF

								IF bought_menu_drawn_shops = 0
									CREATE_MENU FOODCHO 29.0 25.0 93.0 2 FALSE TRUE FO_LEFT bought_menu_shops 
									SET_MENU_COLUMN_ORIENTATION bought_menu_shops 0 FO_LEFT 
									SET_MENU_COLUMN bought_menu_shops 0 MEAL BOUGHT DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
									SET_MENU_COLUMN_ORIENTATION bought_menu_shops 1 FO_RIGHT
									SET_MENU_COLUMN bought_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
									SET_MENU_ITEM_WITH_NUMBER bought_menu_shops 1 0 DOLLAR food_price 
									SET_MENU_COLUMN_WIDTH bought_menu_shops 0 140 
									SET_MENU_COLUMN_WIDTH bought_menu_shops 1 46 
									bought_menu_drawn_shops = 1
								ENDIF

								WHILE TIMERA <= 1000

									WAIT 0

									IF NOT IS_PLAYER_PLAYING player1
										GOSUB junkfud_cleanup_big
										GOTO shop_junkfud_inner 
									ENDIF

									IF IS_CHAR_DEAD shop_keep_junkfud
										
										GOSUB junkfud_cleanup_small

										GET_LOADED_SHOP $shop_name

										IF NOT $shop_name = $stored_shop
											GOSUB junkfud_cleanup_big
										ENDIF

										GOTO shop_junkfud_inner
									ENDIF

								ENDWHILE

								IF bought_menu_drawn_shops = 1
									DELETE_MENU bought_menu_shops
									bought_menu_drawn_shops = 0
								ENDIF

								IF cost_menu_drawn_shops = 0
									GOSUB display_text_food 
								ENDIF

								IF flag_menace_buyfood = 1 
									flag_menace_buyfood = 2
								ENDIF

							ELSE

								IF flag_ate_too_much_food = 0

									IF NOT IS_CHAR_DEAD shop_keep_junkfud
										SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_BOUGHT_ENOUGH sample_name_shop
										REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
										PRINT_NOW (FOOD1) 5000 1 //"You cannot buy any more food at the moment, you will be unwell!
									ELSE
										
										GOSUB junkfud_cleanup_small

										GET_LOADED_SHOP $shop_name

										IF NOT $shop_name = $stored_shop
											GOSUB junkfud_cleanup_big
										ENDIF

										GOTO shop_junkfud_inner
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

									// skip stuff for filsh
									IF flag_menace_buyfood = 2
										SET_FADING_COLOUR 0 0 0

										SET_MUSIC_DOES_FADE FALSE

										DO_FADE 0 FADE_OUT

										GOSUB delete_all_food

										DELETE_CHAR shop_keep_junkfud

										flag_menace_buyfood = 3

										cost_menu_drawn_shops = 0
										bought_menu_drawn_shops = 0

										flag_no_money_shops = 0

										created_this_food = 0
										current_food_item = 0
										flag_food = 2

										// stuff sets back player to normal if he is sick
										SET_CHAR_HEALTH scplayer players_health
										SET_FLOAT_STAT FAT players_fat

										cj_vomits_for_menace = 1

										GOTO shop_junkfud_inner 

									ENDIF

									IF total_food_bought_per_day_shops >= 11
									
										GOSUB junkfud_cleanup_small
										GOSUB delete_all_food 

										// stuff sets back player to normal if he is sick

										IF players_health < 10
											players_health = players_health
										ELSE
											players_health = players_health - 10

											IF players_health < 10
												players_health = 10
											ENDIF

										ENDIF

										SET_CHAR_HEALTH scplayer players_health
										SET_FLOAT_STAT FAT players_fat 
											 									 
										SET_FIXED_CAMERA_POSITION vomit_camX vomit_camY vomit_camZ 0.0 0.0 0.0 // 374.717 -122.550 1002.572 
										POINT_CAMERA_AT_POINT vomit_cam_point_junkfudX vomit_cam_point_junkfudY vomit_cam_point_junnkfudZ JUMP_CUT
										
										IF IS_PLAYER_PLAYING player1
											GET_CHAR_COORDINATES scplayer player_X player_Y player_Z 
										   	vomitX = player_X + 0.355
											vomitY = player_Y - 0.116
										   	vomitZ = player_Z - 0.048
											CREATE_FX_SYSTEM puke vomitX vomitY vomitZ TRUE vomit_foodshop
											TASK_PLAY_ANIM_NON_INTERRUPTABLE scplayer Eat_Vomit_P FOOD 4.0 FALSE FALSE FALSE FALSE -1
											return_animation_time_food = 0.0
											SET_CHAR_SAY_CONTEXT scplayer CONTEXT_GLOBAL_PAIN_CJ_PUKE sample_name_shop
																						
										ELSE
											GOSUB junkfud_cleanup_big
											GOTO shop_junkfud_inner 
										ENDIF

										IF NOT IS_CHAR_DEAD shop_keep_junkfud
										   	TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud Eat_Vomit_SK FOOD 1000.0 FALSE FALSE FALSE FALSE -1
											SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_PLAYER_SICK sample_name_shop
										ELSE
											GOSUB junkfud_cleanup_small

											GET_LOADED_SHOP $shop_name

											IF NOT $shop_name = $stored_shop
												GOSUB junkfud_cleanup_big
											ENDIF

											GOTO shop_junkfud_inner
										ENDIF

										IF IS_CHAR_PLAYING_ANIM scplayer Eat_Vomit_P
											GET_CHAR_ANIM_CURRENT_TIME scplayer Eat_Vomit_P return_animation_time_food
										ENDIF

										WHILE NOT return_animation_time_food = 1.0
										
											WAIT 0
																					   
											IF flag_vomit_playing_food = 0

												IF return_animation_time_food >= 0.463
													
													IF HAS_MISSION_AUDIO_LOADED 4
														REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_RESTAURANT_CJ_PUKE
													ENDIF

													flag_vomit_playing_food = 1

												ENDIF

											ENDIF

											IF flag_vomit_playing_food = 1 
												
												IF return_animation_time_food >= 0.52
													PLAY_FX_SYSTEM vomit_foodshop
													flag_vomit_playing_food = 2
												ENDIF
												
											ENDIF	 

											IF IS_PLAYER_PLAYING player1
												GET_CHAR_ANIM_CURRENT_TIME scplayer Eat_Vomit_P return_animation_time_food
											ELSE
												GOSUB junkfud_cleanup_big
												GOTO shop_junkfud_inner 
											ENDIF

											IF NOT IS_CHAR_DEAD shop_keep_junkfud
												
											ELSE
												GOSUB junkfud_cleanup_small

												GET_LOADED_SHOP $shop_name

												IF NOT $shop_name = $stored_shop
													GOSUB junkfud_cleanup_big
												ENDIF

												GOTO shop_junkfud_inner
											ENDIF

										ENDWHILE

										STOP_FX_SYSTEM vomit_foodshop
										KILL_FX_SYSTEM vomit_foodshop

									ELSE

										IF NOT IS_CHAR_DEAD shop_keep_junkfud

											TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Return FOOD 4.0 FALSE FALSE FALSE TRUE -1	
											return_animation_time_food = 0.0
											
											IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Return
												GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Return return_animation_time_food
											ENDIF
										ELSE
											GOSUB junkfud_cleanup_small
													
											GET_LOADED_SHOP $shop_name

											IF NOT $shop_name = $stored_shop
												GOSUB junkfud_cleanup_big
											ENDIF

											GOTO shop_junkfud_inner
										ENDIF

										// shop keeper putting the tray away animation
										WHILE NOT return_animation_time_food = 1.0

											WAIT 0
											
											IF NOT IS_PLAYER_PLAYING player1
												GOSUB junkfud_cleanup_big
												GOTO shop_junkfud_inner  
											ENDIF

											IF IS_CHAR_DEAD shop_keep_junkfud
												GOSUB junkfud_cleanup_small

												GET_LOADED_SHOP $shop_name

											   	IF NOT $shop_name = $stored_shop
													GOSUB junkfud_cleanup_big
												ENDIF

												GOTO shop_junkfud_inner
											ELSE

												IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Return
													GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Return return_animation_time_food
												ENDIF

											ENDIF
										
										ENDWHILE

										GOSUB delete_all_food

										IF NOT IS_CHAR_DEAD shop_keep_junkfud
											TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Lift_Out FOOD 1000.0 FALSE FALSE FALSE TRUE -1	
											return_animation_time_food = 0.0

											IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_Out
												GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_Out return_animation_time_food
											ENDIF
										ELSE
											GOSUB junkfud_cleanup_small
													
											GET_LOADED_SHOP $shop_name

											IF NOT $shop_name = $stored_shop
												GOSUB junkfud_cleanup_big
											ENDIF

											GOTO shop_junkfud_inner
										ENDIF

										// shop keeper putting the tray away animation
										WHILE NOT return_animation_time_food = 1.0

											WAIT 0
											
											IF NOT IS_PLAYER_PLAYING player1
												GOSUB junkfud_cleanup_big
												GOTO shop_junkfud_inner  
											ENDIF

											IF IS_CHAR_DEAD shop_keep_junkfud
												GOSUB junkfud_cleanup_small

												GET_LOADED_SHOP $shop_name

											   	IF NOT $shop_name = $stored_shop
													GOSUB junkfud_cleanup_big
												ENDIF

												GOTO shop_junkfud_inner
											ELSE

												IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_Out
													GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_Out return_animation_time_food
												ENDIF

											ENDIF
										
										ENDWHILE
																			
									ENDIF

									IF NOT IS_CHAR_DEAD shop_keep_junkfud
										TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Pose FOOD 1000.0 FALSE FALSE FALSE TRUE -1	
										return_animation_time_food = 0.0

										IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Pose
											GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Pose return_animation_time_food
										ENDIF
									ELSE
										GOSUB junkfud_cleanup_small
												
										GET_LOADED_SHOP $shop_name

										IF NOT $shop_name = $stored_shop
											GOSUB junkfud_cleanup_big
										ENDIF

										GOTO shop_junkfud_inner
									ENDIF

									// shop keeper goes back into default pose
									WHILE NOT return_animation_time_food = 1.0
									
										WAIT 0

										IF IS_PLAYER_PLAYING player1
											
										ELSE
											GOSUB junkfud_cleanup_big
											GOTO shop_junkfud_inner 
										ENDIF

										IF NOT IS_CHAR_DEAD shop_keep_junkfud
											
											IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Pose
												GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Pose return_animation_time_food
											ENDIF
												
										ELSE
											GOSUB junkfud_cleanup_small

											GET_LOADED_SHOP $shop_name

											IF NOT $shop_name = $stored_shop
												GOSUB junkfud_cleanup_big
											ENDIF

											GOTO shop_junkfud_inner
										ENDIF

									ENDWHILE

									// new stuff to kick player out need a being sick animation.
									SET_PLAYER_CONTROL player1 ON
									SET_MINIGAME_IN_PROGRESS FALSE
									SET_CAMERA_BEHIND_PLAYER
									RESTORE_CAMERA_JUMPCUT
								
									WHILE LOCATE_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ FALSE
										
										WAIT 0

										IF NOT IS_PLAYER_PLAYING player1
											GOSUB junkfud_cleanup_big
											GOTO shop_junkfud_inner  
										ENDIF

										IF IS_CHAR_DEAD shop_keep_junkfud
											GOSUB junkfud_cleanup_small

											GET_LOADED_SHOP $shop_name

											   IF NOT $shop_name = $stored_shop
												GOSUB junkfud_cleanup_big
											ENDIF

										   	GOTO shop_junkfud_inner

										ELSE

											IF flag_attacked_keeper_food = 0
											
												IF IS_CHAR_SHOOTING scplayer
												OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_junkfud
												OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_junkfud WEAPONTYPE_ANYWEAPON
												OR iSetPizzaPanic = 1
													SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shop
													TASK_HANDS_UP shop_keep_junkfud -2
													iSetPizzaPanic = 1
													flag_attacked_keeper_food = 1
												ENDIF

											ENDIF

										ENDIF

									ENDWHILE									

									cost_menu_drawn_shops = 0
									bought_menu_drawn_shops = 0
									created_this_food = 0
									current_food_item = 0
									flag_food = 2
									flag_ate_too_much_food = 1
								ENDIF

							ENDIF
									
						ELSE

							IF flag_no_money_shops = 0
						  		
								IF NOT IS_CHAR_DEAD shop_keep_junkfud
									REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
									SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_NO_MONEY sample_name_shops
									PRINT_NOW (SHOPNO) 5000 1 //"You don't have enough money to buy this."
								ELSE
									GOSUB junkfud_cleanup_small
											
									GET_LOADED_SHOP $shop_name

									IF NOT $shop_name = $stored_shop
										GOSUB junkfud_cleanup_big
									ENDIF

									GOTO shop_junkfud_inner
								ENDIF
																
								flag_no_money_shops = 1
							ENDIF

						ENDIF

					ENDIF // buying button

				ENDIF

				GOSUB quit_out_food

			ENDIF // food flag

		ELSE
			GOSUB junkfud_cleanup_big
		ENDIF // is string empty

	ELSE // player playing
		GOSUB junkfud_cleanup_big
	ENDIF	 
		
GOTO shop_junkfud_inner

				
junkfud_cleanup_small:

	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (FOOD1)

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

	created_this_food = 0
	current_food_item = 0
	flag_bought_some_food = 0
	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0
	flag_food = 2

	flag_no_money_shops = 0

		 
	MARK_MODEL_AS_NO_LONGER_NEEDED foodsmall_junkfud 
	MARK_MODEL_AS_NO_LONGER_NEEDED foodmed_junkfud
	MARK_MODEL_AS_NO_LONGER_NEEDED foodlarge_junkfud
	MARK_MODEL_AS_NO_LONGER_NEEDED foodhealthy_junkfud
				
	IF jfud_keep_created = 1
		CLEAR_HELP	
	ENDIF

RETURN



junkfud_cleanup_big:

	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (FOOD1)

	IF jfud_keep_created = 1
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
		
	jfud_keep_created = 0
	found_a_food_shop = 0
	current_food_item = 0
	created_this_food = 0

	flag_ate_too_much_food = 0
	flag_bought_some_food = 0

	flag_attacked_keeper_food = 0

	flag_vomit_playing_food = 0

	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0

	flag_food = 0
	flag_no_money_shops = 0

	KILL_FX_SYSTEM vomit_foodshop
    DELETE_CHAR shop_keep_junkfud
	
	MARK_MODEL_AS_NO_LONGER_NEEDED shopkeeper_model_shops
	MARK_MODEL_AS_NO_LONGER_NEEDED foodsmall_junkfud 
	MARK_MODEL_AS_NO_LONGER_NEEDED foodmed_junkfud
	MARK_MODEL_AS_NO_LONGER_NEEDED foodlarge_junkfud
	MARK_MODEL_AS_NO_LONGER_NEEDED foodhealthy_junkfud
	
	USE_TEXT_COMMANDS FALSE
	SHOW_UPDATE_STATS TRUE
	SET_MINIGAME_IN_PROGRESS FALSE
	CLEAR_MISSION_AUDIO 4

	TERMINATE_THIS_SCRIPT
		
RETURN


quit_out_food:

IF IS_BUTTON_PRESSED PAD1 TRIANGLE

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

	WHILE IS_BUTTON_PRESSED PAD1 TRIANGLE

		WAIT 0

		IF NOT IS_PLAYER_PLAYING player1
			GOSUB junkfud_cleanup_big
			GOTO shop_junkfud_inner  
		ENDIF

		IF IS_CHAR_DEAD shop_keep_junkfud
			GOSUB junkfud_cleanup_small

			GET_LOADED_SHOP $shop_name

			IF NOT $shop_name = $stored_shop
				GOSUB junkfud_cleanup_big
			ENDIF

		   	GOTO shop_junkfud_inner
		ENDIF

	ENDWHILE

	CLEAR_THIS_PRINT (SHOPNO)
	CLEAR_THIS_PRINT (FOOD1)

	IF flag_menace_buyfood = 2
		
		SET_FADING_COLOUR 0 0 0

		DO_FADE 0 FADE_OUT

		GOSUB delete_all_food

		DELETE_CHAR shop_keep_junkfud

		flag_menace_buyfood = 3
		created_this_food = 0
		current_food_item = 0
		cost_menu_drawn_shops = 0
		bought_menu_drawn_shops = 0
		flag_food = 2
		flag_no_money_shops = 0

		GOTO skip_for_filsh_junkfud 

	ENDIF
	
	blob_flag_shop = 0

	GET_LOADED_SHOP $shop_name

	IF flag_bought_some_food = 1

		IF $shop_name = FDburg 
		   	TASK_PLAY_ANIM scplayer EAT_Burger FOOD 4.0 FALSE FALSE FALSE FALSE -1
		ELSE
			IF $shop_name = FDpiza 
				TASK_PLAY_ANIM scplayer EAT_Pizza FOOD 4.0 FALSE FALSE FALSE FALSE -1
			ELSE
				IF $shop_name = FDchick 
					TASK_PLAY_ANIM scplayer EAT_Chicken FOOD 4.0 FALSE FALSE FALSE FALSE -1

				ENDIF
						
			ENDIF
					
		ENDIF

	ENDIF
	
	IF NOT IS_CHAR_DEAD shop_keep_junkfud

		SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_THANKS_FOR_CUSTOM sample_name_shops

		TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Return FOOD 1000.0 FALSE FALSE FALSE TRUE -1	
		return_animation_time_food = 0.0

		IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Return
			GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Return return_animation_time_food
		ENDIF
	ELSE
		GOSUB junkfud_cleanup_small
				
		GET_LOADED_SHOP $shop_name

		IF NOT $shop_name = $stored_shop
			GOSUB junkfud_cleanup_big
		ENDIF

		GOTO shop_junkfud_inner
	ENDIF

	// ducking back down to get rid of the tray
	WHILE NOT return_animation_time_food = 1.0

		WAIT 0
		
		IF NOT IS_PLAYER_PLAYING player1
			GOSUB junkfud_cleanup_big
			GOTO shop_junkfud_inner  
		ENDIF

		IF IS_CHAR_DEAD shop_keep_junkfud
			GOSUB junkfud_cleanup_small

			GET_LOADED_SHOP $shop_name

		   	IF NOT $shop_name = $stored_shop
				GOSUB junkfud_cleanup_big
			ENDIF

			GOTO shop_junkfud_inner
		ELSE

			IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Return
				GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Return return_animation_time_food
			ENDIF

		ENDIF
	
	ENDWHILE

	// deletes the tray
	GOSUB delete_all_food

	IF NOT IS_CHAR_DEAD shop_keep_junkfud
		TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Lift_Out FOOD 1000.0 FALSE FALSE FALSE TRUE -1	
		return_animation_time_food = 0.0

		IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_Out
			GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_Out return_animation_time_food
		ENDIF
	ELSE
		GOSUB junkfud_cleanup_small
				
		GET_LOADED_SHOP $shop_name

		IF NOT $shop_name = $stored_shop
			GOSUB junkfud_cleanup_big
		ENDIF

		GOTO shop_junkfud_inner
	ENDIF

	// shop keeper putting the tray away animation
	WHILE NOT return_animation_time_food = 1.0

		WAIT 0
		
		IF NOT IS_PLAYER_PLAYING player1
			GOSUB junkfud_cleanup_big
			GOTO shop_junkfud_inner  
		ENDIF

		IF IS_CHAR_DEAD shop_keep_junkfud
			GOSUB junkfud_cleanup_small

			GET_LOADED_SHOP $shop_name

		   	IF NOT $shop_name = $stored_shop
				GOSUB junkfud_cleanup_big
			ENDIF

			GOTO shop_junkfud_inner
		ELSE

			IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Lift_Out
				GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Lift_Out return_animation_time_food
			ENDIF

		ENDIF
	
	ENDWHILE

	IF flag_bought_some_food = 1

		IF HAS_MISSION_AUDIO_LOADED 4

			IF HAS_MISSION_AUDIO_FINISHED 4 
				REPORT_MISSION_AUDIO_EVENT_AT_CHAR scplayer SOUND_RESTAURANT_CJ_EAT
			ENDIF

		ENDIF

	ENDIF

	created_this_food = 0
	current_food_item = 0
	flag_food = 2

	cost_menu_drawn_shops = 0
	bought_menu_drawn_shops = 0
	flag_bought_some_food = 0
	
	
	SET_PLAYER_CONTROL player1 ON
	RESTORE_CAMERA_JUMPCUT						   
	
	IF jfud_keep_created = 1
		CLEAR_HELP	
	ENDIF
		 
	GOSUB delete_all_food

	IF NOT IS_CHAR_DEAD shop_keep_junkfud
		TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Thank FOOD 1000.0 FALSE FALSE FALSE TRUE -1	
		return_animation_time_food = 0.0
		SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_THANKS_FOR_CUSTOM sample_name_shops

		IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Thank
			GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Thank return_animation_time_food
		ENDIF
	ELSE
		GOSUB junkfud_cleanup_small
				
		GET_LOADED_SHOP $shop_name

		IF NOT $shop_name = $stored_shop
			GOSUB junkfud_cleanup_big
		ENDIF

		GOTO shop_junkfud_inner
	ENDIF

	// shop keeper putting the tray away animation
	WHILE NOT return_animation_time_food = 1.0

		WAIT 0
		
		IF NOT IS_PLAYER_PLAYING player1
			GOSUB junkfud_cleanup_big
			GOTO shop_junkfud_inner  
		ENDIF

		IF IS_CHAR_DEAD shop_keep_junkfud
			GOSUB junkfud_cleanup_small

			GET_LOADED_SHOP $shop_name

		   	IF NOT $shop_name = $stored_shop
				GOSUB junkfud_cleanup_big
			ENDIF

			GOTO shop_junkfud_inner
		ELSE

			IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Thank
				GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Thank return_animation_time_food
			ENDIF

		ENDIF
	
	ENDWHILE
	
	IF NOT IS_CHAR_DEAD shop_keep_junkfud
		TASK_PLAY_ANIM_NON_INTERRUPTABLE shop_keep_junkfud SHP_Tray_Pose FOOD 1000.0 FALSE FALSE FALSE TRUE -1
		return_animation_time_food = 0.0

		IF IS_CHAR_PLAYING_ANIM shop_keep_junkfud SHP_Tray_Pose
			GET_CHAR_ANIM_CURRENT_TIME shop_keep_junkfud SHP_Tray_Pose return_animation_time_food
		ENDIF
	ELSE
		GOSUB junkfud_cleanup_small
				
		GET_LOADED_SHOP $shop_name

		IF NOT $shop_name = $stored_shop
			GOSUB junkfud_cleanup_big
		ENDIF

		GOTO shop_junkfud_inner
	ENDIF

	WHILE LOCATE_CHAR_ON_FOOT_3D scplayer keep_offX keep_offY keep_offZ shop_locate_sizeX shop_locate_sizeY shop_locate_sizeZ TRUE

		WAIT 0
				
		IF NOT IS_PLAYER_PLAYING player1
			GOSUB junkfud_cleanup_big
			GOTO shop_junkfud_inner  
		ENDIF

		IF IS_CHAR_DEAD shop_keep_junkfud
			GOSUB junkfud_cleanup_small

			GET_LOADED_SHOP $shop_name

			   IF NOT $shop_name = $stored_shop
				GOSUB junkfud_cleanup_big
			ENDIF

		   	GOTO shop_junkfud_inner

		ELSE

			IF flag_attacked_keeper_food = 0
			
				IF IS_CHAR_SHOOTING scplayer
				OR IS_PLAYER_TARGETTING_CHAR player1 shop_keep_junkfud
				OR HAS_CHAR_BEEN_DAMAGED_BY_WEAPON shop_keep_junkfud WEAPONTYPE_ANYWEAPON
				OR iSetPizzaPanic = 1
					SET_CHAR_SAY_CONTEXT shop_keep_junkfud CONTEXT_GLOBAL_SHOP_CLOSED sample_name_shop
					TASK_HANDS_UP shop_keep_junkfud -2
					iSetPizzaPanic = 1
					flag_attacked_keeper_food = 1
				ENDIF

			ENDIF

		ENDIF

	ENDWHILE
	
	blob_flag_shop = 1				

	skip_for_filsh_junkfud:

ENDIF // press Triangle to quit

RETURN

// displays the text
display_text_food:

	IF cost_menu_drawn_shops = 0
		    
		PRINT_HELP_FOREVER FOOD_H
		
		// Create the menu with all available food items and their prices.

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU FOODCHO 29.0 145.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU FOODCHO 29.0 145.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU FOODCHO 29.0 165.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU FOODCHO 29.0 145.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU FOODCHO 29.0 145.0 93.0 2 FALSE TRUE FO_LEFT cost_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 0 FO_LEFT 
		SET_MENU_COLUMN cost_menu_shops 0 MEAL $shop_item_label DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_COLUMN_ORIENTATION cost_menu_shops 1 FO_RIGHT 
		SET_MENU_COLUMN cost_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_ITEM_WITH_NUMBER cost_menu_shops 1 0 DOLLAR shop_item_price 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH cost_menu_shops 1 46

		cost_menu_drawn_shops = 1
	ENDIF

RETURN


delete_all_food:
	DELETE_OBJECT ffood_obj
RETURN

MISSION_END

}


