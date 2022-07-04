MISSION_START






/////////////////////////
/// PROSTITUTE BRAIN ////
/////////////////////////

prostitute_brain:
{

	SCRIPT_NAME PROST

	LVAR_INT pros_ped
	LVAR_INT pros_punter_ped
	LVAR_INT pros_brain_flag 
	LVAR_INT pros_car_type
	LVAR_INT pros_found_punter
	LVAR_INT pros_punter_in_car
	LVAR_INT pros_punters_car
	LVAR_INT pros_seq punter_seq 
	LVAR_INT pros_after_player
	LVAR_INT pros_terminate
	LVAR_INT pros_task_assigned
	LVAR_INT pros_task_status	
	LVAR_INT pros_animation_loaded	
	LVAR_INT pros_following_punter
	LVAR_INT pros_leader
	LVAR_INT pros_player_propositioned
	LVAR_INT pros_cost
	LVAR_INT pros_pad_shaking
	LVAR_INT pros_time_elapsed pros_timer_current pros_timer_start
	LVAR_FLOAT pros_speed
	LVAR_FLOAT pros_x pros_y pros_z
	LVAR_FLOAT pros_light_level
	LVAR_INT pros_good_to_go

	LVAR_INT pros_blip pros_dummy
	LVAR_FLOAT pros_cost_f


	pros_brain_flag 		= 0 
	pros_found_punter 		= 0
	pros_punter_in_car 		= 0
	pros_after_player 		= 0 // easier to test on player
	pros_terminate 			= 0
	pros_task_assigned 		= 0
	pros_animation_loaded 	= 0
	pros_following_punter	= 0	
	pros_pad_shaking		= 0
	pros_light_level  		= 0.0
	pros_player_propositioned = 0	
	pros_good_to_go			= 0
	pros_cost_f = 0.0


	IF pros_brain_flag = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 pros_ped
		CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 pros_punter_ped
       	//ADD_BLIP_FOR_CHAR pros_ped pros_blip
    ENDIF

	

	IF NOT pros_active = 0
	OR flag_player_on_mission = 1
	    IS_CHAR_IN_WATER scplayer
			pros_terminate = 1		
		TERMINATE_THIS_SCRIPT
	ELSE
		IF DOES_CHAR_EXIST pros_ped
			IF NOT IS_CHAR_DEAD pros_ped
				//REMOVE_BLIP pros_blip
				//ADD_BLIP_FOR_CHAR pros_ped pros_blip
				//SET_BLIP_AS_FRIENDLY pros_blip TRUE
			ENDIF
		ENDIF
		   
		pros_terminate = 1		
		TERMINATE_THIS_SCRIPT
		pros_active = 1 // only have one active at a time	
	ENDIF

	IF IS_PLAYER_PLAYING Player1
		IF IS_CHAR_IN_WATER scplayer
			pros_terminate = 1		
		 	TERMINATE_THIS_SCRIPT
		ENDIF
	ENDIF


prostitute_brain_loop:

//	VIEW_INTEGER_VARIABLE pros_active pros_active
	//VIEW_INTEGER_VARIABLE pros_terminate pros_terminate
	//VIEW_INTEGER_VARIABLE flag_player_on_mission flag_player_on_mission
	//VIEW_INTEGER_VARIABLE pros_found_punter pros_found_punter
	WAIT 0
	/*
	VIEW_INTEGER_VARIABLE pros_brain_flag 		brain_flag
	VIEW_INTEGER_VARIABLE pros_punter_in_car 	in_car
	VIEW_INTEGER_VARIABLE pros_found_punter 	found_punter
	VIEW_INTEGER_VARIABLE pros_after_player 	after_player
	VIEW_INTEGER_VARIABLE pros_task_assigned 	task_assigned
	VIEW_INTEGER_VARIABLE pros_task_status 		task_status
	VIEW_FLOAT_VARIABLE pros_light_level    	light_level
	 */
	IF DOES_CHAR_EXIST pros_ped
	AND pros_terminate = 0
		IF NOT IS_CHAR_DEAD pros_ped
		AND IS_PLAYER_PLAYING Player1
			////////////////////////////////
			//// INVOLVING COMPUTER PED ////
			////////////////////////////////			
			//IF  flag_player_on_mission = 1
			//	CLEAR_HELP
			//	pros_active = 0
			//	pros_terminate = 1
			//ENDIF
			

			IF pros_found_punter = 1						
				IF pros_after_player = 1
					IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D pros_ped scplayer 15.5 15.5 4.0 FALSE
					OR flag_player_on_mission = 1
						CLEAR_HELP
						pros_active = 0
						pros_terminate = 1
					ENDIF
				ENDIF
			ENDIF

			IF pros_found_punter = 0
				IF pros_player_propositioned = 0
							
					IF NOT IS_CHAR_DEAD pros_ped
					AND NOT IS_CHAR_DEAD scplayer							
						
						
						
						// REMOVING THIS SECTION FORCES THE PROSTITUTE TO USE THE PLAYER  
						
						GET_CHAR_COORDINATES pros_ped pros_x pros_y pros_z	
						//GET_RANDOM_CHAR_IN_SPHERE pros_x pros_y pros_z 5.0 TRUE TRUE FALSE pros_punter_ped
						//MARK_CHAR_AS_NO_LONGER_NEEDED pros_punter_ped
						/*
						IF pros_player_propositioned = 0
							IF NOT pros_punter_ped = -1
								IF IS_CHAR_MALE	pros_punter_ped			
									//ADD_BLIP_FOR_CHAR pros_punter_ped pros_punter_blip
									pros_found_punter = 1
									TASK_STAND_STILL pros_punter_ped -1
									IF IS_CHAR_IN_ANY_CAR pros_punter_ped							    
										STORE_CAR_CHAR_IS_IN_NO_SAVE pros_punter_ped pros_punters_car
										pros_punter_in_car = 1
									ENDIF
								ENDIF
							ENDIF
						ENDIF
						*/
						
						
						

					
					
					
						IF flag_player_on_mission = 0
						AND NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS // player is not on a date

 												
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D pros_ped scplayer 7.5 7.5 4.0 FALSE
								//// add bit where prostitue asks player if he's interested ////
								pros_good_to_go  = 0 	
								


								/// bus's
								


								
								IF IS_CHAR_IN_ANY_CAR scplayer
									IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
										STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer pros_punters_car									
										GET_CAR_MODEL pros_punters_car pros_car_type
										IF NOT pros_car_type =  PIZZABOY 
											IF NOT pros_car_type =  PCJ600 
												IF NOT pros_car_type = FCR900
													IF NOT pros_car_type =  FAGGIO	
														IF NOT pros_car_type =  FREEWAY 
															IF NOT pros_car_type =  SANCHEZ	
																IF NOT pros_car_type =  BMX  
																	IF NOT pros_car_type =  MTBIKE 
																		IF NOT pros_car_type =  NRG500 									
																			IF NOT pros_car_type =  BF400 
																				IF NOT pros_car_type =  WAYFARER
																					IF NOT pros_car_type =  BIKE 
																						IF NOT pros_car_type = QUAD
																							IF NOT pros_car_type = COPBIKE																								
																								IF NOT pros_car_type = BUS
																									IF NOT pros_car_type = COACH
																										IF IS_CAR_PASSENGER_SEAT_FREE pros_punters_car 0
																											IF NOT IS_WANTED_LEVEL_GREATER player1 0
																												pros_good_to_go  = 1
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
															ENDIF
														ENDIF
													ENDIF
												ENDIF
											ENDIF
										ENDIF
									ENDIF
								ELSE
									IF NOT IS_WANTED_LEVEL_GREATER player1 0
										pros_good_to_go  = 1
									ENDIF
								ENDIF


								
								
								IF pros_good_to_go = 1		
									GET_CHAR_SPEED scplayer pros_speed
									IF pros_speed	< 5.0								 								 
										
										/// put checks in for player car and other factors (like girlfriends)
										 
										
										IF pros_player_propositioned = 0  
											//pros_found_punter = 1
											IF NOT IS_CHAR_IN_ANY_CAR scplayer												
												OPEN_SEQUENCE_TASK pros_seq 													
												TASK_GOTO_CHAR -1 scplayer  -1 1.1
												TASK_TURN_CHAR_TO_FACE_CHAR	-1 scplayer
												TASK_LOOK_AT_CHAR -1 scplayer 4000
												TASK_STAND_STILL -1 20000							   									    										
												CLOSE_SEQUENCE_TASK pros_seq
												PERFORM_SEQUENCE_TASK pros_ped pros_seq
												CLEAR_SEQUENCE_TASK pros_seq 
											ELSE
												IF NOT IS_CAR_DEAD pros_punters_car
													OPEN_SEQUENCE_TASK pros_seq 									   																										
													TASK_GOTO_CAR -1 pros_punters_car  -1 2.5
													TASK_TURN_CHAR_TO_FACE_CHAR	-1 scplayer
													TASK_LOOK_AT_CHAR -1 scplayer 4000
													TASK_STAND_STILL -1 20000							   									    										
													CLOSE_SEQUENCE_TASK pros_seq
													PERFORM_SEQUENCE_TASK pros_ped pros_seq
													CLEAR_SEQUENCE_TASK pros_seq
												ENDIF
											ENDIF
											GET_INT_STAT SEX_APPEAL	pros_cost
											
											
											IF pros_cost < 200
												pros_cost = 50
											ELSE 
												IF pros_cost < 400
													pros_cost = 40	
												ELSE
													IF pros_cost < 600
														pros_cost = 30
													ELSE
														IF pros_cost < 800
															pros_cost =	20
														ELSE
															pros_cost =	10
														ENDIF
													ENDIF
												ENDIF
											ENDIF
											
											SET_CHAR_SAY_CONTEXT pros_ped CONTEXT_GLOBAL_SOLICIT  pros_dummy
											PRINT_WITH_NUMBER_NOW (PROS_07) pros_cost 5000 1  //It's gonns cost you $~1~, interested?
											
											PRINT_HELP_FOREVER TALK_1  
											
											/// if player says yes determine parice depending on sex appeal etc? ///
											pros_player_propositioned= 1								
										ENDIF
									ENDIF
								ENDIF
							ELSE
								IF pros_player_propositioned= 1
								   CLEAR_HELP
								   pros_player_propositioned= 0
								ENDIF						
							ENDIF
						ENDIF
				   	ENDIF																											
				ELSE //pros_player_propositioned = 0														
					IF NOT IS_CHAR_DEAD pros_ped
					AND NOT IS_CHAR_DEAD scplayer

						IF flag_player_on_mission = 0												
							IF LOCATE_CHAR_ANY_MEANS_CHAR_3D pros_ped scplayer 7.5 7.5 4.0 FALSE

								IF IS_PLAYER_PLAYING player1 
									STORE_SCORE player1 players_money
								ENDIF

								
								IF IS_BUTTON_PRESSED PAD1 DPADRIGHT							
									CLEAR_HELP																																
									pros_after_player = 1									
									pros_found_punter = 1																		
									IF NOT IS_CHAR_DEAD scplayer
										IF IS_CHAR_IN_ANY_CAR scplayer	
										 	STORE_CAR_CHAR_IS_IN_NO_SAVE  scplayer pros_punters_car					  	
										  	TASK_TOGGLE_PED_THREAT_SCANNER pros_ped FALSE FALSE FALSE
										  	pros_punter_in_car = 1
										ELSE
											pros_punter_in_car = 0
										ENDIF
									ENDIF
								ELSE
									IF IS_BUTTON_PRESSED PAD1 DPADLEFT											
										CLEAR_HELP
										pros_active = 0
										pros_terminate = 1
									ENDIF
								ENDIF								
							ELSE
								IF pros_player_propositioned= 1
								   CLEAR_HELP
								   //pros_active = 0
								   //pros_terminate = 1 // finished script
								   pros_player_propositioned= 0
								ENDIF						
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			
			
			
			
			IF pros_found_punter = 1						
				IF pros_after_player = 0
					IF NOT IS_CHAR_DEAD pros_punter_ped					
						IF pros_brain_flag = 0				
							IF pros_punter_in_car = 1														   		 
								IF IS_CHAR_SITTING_IN_ANY_CAR pros_punter_ped 										
									STORE_CAR_CHAR_IS_IN_NO_SAVE pros_punter_ped pros_punters_car
									IF IS_CAR_STOPPED pros_punters_car
										IF IS_CAR_PASSENGER_SEAT_FREE pros_punters_car 0										
											IF LOCATE_CHAR_ON_FOOT_CAR_3D pros_ped pros_punters_car 10.0 10.0 3.0 FALSE
												// get in car
												pros_brain_flag = 2 
											ENDIF
										ENDIF
									ELSE
									// stop the car
									ENDIF
								ENDIF														
							ELSE //pros punter in car
								// have prositute approach punter
								pros_brain_flag = 1	
							ENDIF				
						ELSE //pros_brain_flag = 0
							
							 IF pros_animation_loaded = 0
								REQUEST_ANIMATION BLOWJOBZ
							 	 pros_animation_loaded = 1
							 ENDIF
							 IF  pros_animation_loaded = 1
								IF HAS_ANIMATION_LOADED BLOWJOBZ
									 pros_animation_loaded = 2
								ENDIF
							 ENDIF
							
							IF  pros_animation_loaded = 2
								IF pros_brain_flag = 1						
									
									IF IS_CHAR_IN_ANY_CAR pros_punter_ped
										STORE_CAR_CHAR_IS_IN_NO_SAVE pros_punter_ped pros_punters_car
									ENDIF
									
									IF pros_task_assigned = 0																			
										
										OPEN_SEQUENCE_TASK pros_seq 									   
										TASK_GOTO_CHAR -1 pros_punter_ped  -1 1.1	
										TASK_TURN_CHAR_TO_FACE_CHAR	-1 pros_punter_ped
										TASK_LOOK_AT_CHAR -1 pros_punter_ped 4000
										TASK_STAND_STILL -1 20000							   									    										
										CLOSE_SEQUENCE_TASK pros_seq
										PERFORM_SEQUENCE_TASK pros_ped pros_seq
										CLEAR_SEQUENCE_TASK pros_seq									   
										pros_task_assigned =1																		
									ENDIF

									IF pros_task_assigned = 1								     
										GET_SCRIPT_TASK_STATUS pros_ped PERFORM_SEQUENCE_TASK  pros_task_status
										IF  pros_task_status  = PERFORMING_TASK	
											GET_SEQUENCE_PROGRESS pros_ped pros_task_status
											IF pros_task_status > 0 // after look at char task										   																							
												CLEAR_CHAR_TASKS_IMMEDIATELY pros_punter_ped
												OPEN_SEQUENCE_TASK punter_seq											
												TASK_GOTO_CHAR -1 pros_ped -1 1.1
												TASK_TURN_CHAR_TO_FACE_CHAR	-1 pros_ped
												TASK_LOOK_AT_CHAR -1 pros_ped 4000
												TASK_STAND_STILL -1 2000												
												CLOSE_SEQUENCE_TASK	punter_seq
												PERFORM_SEQUENCE_TASK pros_punter_ped punter_seq
												CLEAR_SEQUENCE_TASK punter_seq																							
												REQUEST_ANIMATION INT_SHOP
												pros_task_assigned = 2
											ENDIF
										ENDIF
									ENDIF


										IF pros_task_assigned = 2
											IF HAS_ANIMATION_LOADED INT_SHOP
												GET_SCRIPT_TASK_STATUS pros_punter_ped PERFORM_SEQUENCE_TASK  pros_task_status
												IF  pros_task_status  = FINISHED_TASK												
													OPEN_SEQUENCE_TASK punter_seq
													TASK_TURN_CHAR_TO_FACE_CHAR	-1 pros_punter_ped
													TASK_STAND_STILL -1 20000
													CLOSE_SEQUENCE_TASK punter_seq
													PERFORM_SEQUENCE_TASK pros_ped punter_seq
													CLEAR_SEQUENCE_TASK punter_seq

													OPEN_SEQUENCE_TASK punter_seq
													TASK_TURN_CHAR_TO_FACE_CHAR	-1 pros_ped
													IF HAS_ANIMATION_LOADED INT_SHOP
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 shop_pay INT_SHOP 1000.0 FALSE FALSE FALSE FALSE -1
													ENDIF
													CLOSE_SEQUENCE_TASK	punter_seq
													PERFORM_SEQUENCE_TASK pros_punter_ped punter_seq
													CLEAR_SEQUENCE_TASK punter_seq
													REMOVE_ANIMATION INT_SHOP
													pros_task_assigned = 3
												ENDIF
											ELSE
												REQUEST_ANIMATION INT_SHOP
											ENDIF
										ENDIF
										
										
									IF pros_task_assigned = 3								     
										GET_SCRIPT_TASK_STATUS pros_punter_ped PERFORM_SEQUENCE_TASK  pros_task_status
										IF  pros_task_status  = FINISHED_TASK
											IF HAS_ANIMATION_LOADED	BLOWJOBZ
											OPEN_SEQUENCE_TASK pros_seq 									   																	   									    
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Start_W BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1										
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_End_W   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1							   									   
											TASK_WANDER_STANDARD -1
											CLOSE_SEQUENCE_TASK pros_seq
											PERFORM_SEQUENCE_TASK pros_ped pros_seq
											CLEAR_SEQUENCE_TASK pros_seq

											OPEN_SEQUENCE_TASK punter_seq
										   	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Start_P BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_End_P   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
											CLOSE_SEQUENCE_TASK	punter_seq
											PERFORM_SEQUENCE_TASK pros_punter_ped punter_seq
											CLEAR_SEQUENCE_TASK punter_seq
											pros_task_assigned = 4
											ELSE
												REQUEST_ANIMATION BLOWJOBZ
											ENDIF											
										ENDIF
									ENDIF

									IF pros_task_assigned = 4								     
										GET_SCRIPT_TASK_STATUS pros_punter_ped PERFORM_SEQUENCE_TASK  pros_task_status
										IF  pros_task_status  = FINISHED_TASK											
											REMOVE_ANIMATION BLOWJOBZ
											pros_active = 0
											pros_terminate = 1 // finished script
										ENDIF
									ENDIF


								ELSE //pros brain = 1  PLAYER IN CAR
									IF pros_brain_flag = 2
										IF NOT IS_CAR_DEAD pros_punters_car
											IF pros_task_assigned = 0
												IF IS_PLAYER_PLAYING player1
												SET_PLAYER_CONTROL player1 OFF
												
												OPEN_SEQUENCE_TASK pros_seq 									   
												TASK_GOTO_CAR -1 pros_punters_car -1	2.5
												TASK_LOOK_AT_CHAR -1 pros_punter_ped 4000
												TASK_STAND_STILL -1 2000
												TASK_ENTER_CAR_AS_PASSENGER -1 pros_punters_car -1 0									    												
												CLOSE_SEQUENCE_TASK pros_seq
												PERFORM_SEQUENCE_TASK pros_ped pros_seq
												CLEAR_SEQUENCE_TASK pros_seq					   							
												pros_task_assigned = 1								
												ENDIF
											ENDIF

											
												
												
											IF pros_task_assigned = 1								     
												GET_SCRIPT_TASK_STATUS pros_ped PERFORM_SEQUENCE_TASK  pros_task_status
												IF  pros_task_status  = FINISHED_TASK	
													IF HAS_ANIMATION_LOADED BLOWJOBZ
													OPEN_SEQUENCE_TASK pros_seq 									   																					    
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Start_W BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_End_W   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1													
													CLOSE_SEQUENCE_TASK pros_seq
													PERFORM_SEQUENCE_TASK pros_ped pros_seq
													CLEAR_SEQUENCE_TASK pros_seq

													
													OPEN_SEQUENCE_TASK punter_seq
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Start_P BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_End_P   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
													CLOSE_SEQUENCE_TASK	punter_seq
													PERFORM_SEQUENCE_TASK pros_punter_ped punter_seq
													CLEAR_SEQUENCE_TASK punter_seq

													pros_task_assigned = 2
													ELSE 
													REQUEST_ANIMATION BLOWJOBZ
													ENDIF
												ENDIF
											ENDIF

																						
											IF pros_task_assigned = 2								     											   
											   	GET_SCRIPT_TASK_STATUS pros_punter_ped PERFORM_SEQUENCE_TASK  pros_task_status
												IF  pros_task_status  = FINISHED_TASK													
													OPEN_SEQUENCE_TASK pros_seq   													
													TASK_LEAVE_ANY_CAR -1									   
													TASK_WANDER_STANDARD -1
													CLOSE_SEQUENCE_TASK pros_seq
													PERFORM_SEQUENCE_TASK pros_ped pros_seq
													CLEAR_SEQUENCE_TASK pros_seq																																					
													REMOVE_ANIMATION BLOWJOBZ
													pros_active = 0
													pros_terminate = 1 // finished script
												ENDIF
											ENDIF

										ENDIF
									ENDIF //pros_brain_flag = 2
								ENDIF // pros_brain = 1
							ENDIF //  pros_animation_loaded = 2
							
						ENDIF // pros_brain = 0					

					ENDIF //IF NOT IS_CHAR_DEAD pros_punter_ped
				ENDIF /// after player
					
					
					//////////////////////////////////////////////////////////
					//// players interaction with prostitute goes in here ////
					//////////////////////////////////////////////////////////

				IF  pros_after_player = 1
					/////////////////////////////////////////////////////////////
					///  NEED TO ADD A CHECK FOR PLAYER GETTING INTO CAR HERE ///
					IF NOT IS_CHAR_DEAD scplayer
						IF IS_CHAR_IN_WATER scplayer						
							pros_terminate = 1
						ENDIF
					ENDIF

					IF NOT IS_CHAR_DEAD scplayer
						IF IS_WANTED_LEVEL_GREATER player1 0										
						   CLEAR_HELP
						   pros_active = 0
						   pros_terminate = 1 // finished script
						  
						   PRINT_NOW (PROS_09) 4000 1 //Stop wasteing my time!
						ENDIF
						pros_good_to_go  = 0
						IF IS_CHAR_IN_ANY_CAR scplayer
							STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer pros_punters_car
							IF pros_brain_flag < 3
								IF IS_CAR_STOPPED pros_punters_car																			
									IF LOCATE_CHAR_ANY_MEANS_CAR_3D pros_ped pros_punters_car 10.0 10.0 3.0 FALSE											
										IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
											GET_CAR_MODEL pros_punters_car pros_car_type
											IF NOT pros_car_type =  PIZZABOY 
												IF NOT pros_car_type =  PCJ600 
													IF NOT pros_car_type =  FAGGIO	
														IF NOT pros_car_type =  FREEWAY 
															IF NOT pros_car_type = FCR900
																IF NOT pros_car_type =  SANCHEZ	
																	IF NOT pros_car_type =  BMX  
																		IF NOT pros_car_type =  MTBIKE 
																			IF NOT pros_car_type =  NRG500 									
																				IF NOT pros_car_type =  BF400 
																					IF NOT pros_car_type =  WAYFARER
																						IF NOT pros_car_type =  BIKE
																							IF NOT pros_car_type = QUAD 																						
																								IF NOT pros_car_type = COPBIKE																								
																									IF NOT pros_car_type = BUS
																										IF NOT pros_car_type = COACH
																											IF IS_CAR_PASSENGER_SEAT_FREE pros_punters_car 0																		
																												IF NOT IS_WANTED_LEVEL_GREATER player1 0
																													pros_good_to_go  = 1																								
																												ENDIF
																											ELSE
																												IF IS_CHAR_IN_CAR  pros_ped	pros_punters_car
																												   	IF NOT IS_WANTED_LEVEL_GREATER player1 0
																														pros_good_to_go  = 1																								
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
							
							IF pros_good_to_go  = 1
							   IF NOT pros_brain_flag = 2
								pros_task_assigned = 0
								ENDIF
							   pros_brain_flag = 2
							ELSE
							   CLEAR_HELP
							   pros_active = 0
							   PRINT_NOW (PROS_09) 4000 1 //Stop wasteing my time!
							   pros_terminate = 1 // finished script
							ENDIF										
						ENDIF
					ENDIF


					





					
					/////////////////////////////////////////////////
					IF NOT IS_CHAR_DEAD scplayer
						IF NOT flag_player_on_mission = 1 // don't want to interfere with mission stuff
							IF pros_brain_flag = 0				
								IF pros_punter_in_car = 1														   		 
									IF IS_CHAR_SITTING_IN_ANY_CAR scplayer 										
										STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer pros_punters_car
										IF IS_CAR_STOPPED pros_punters_car
											IF IS_CAR_PASSENGER_SEAT_FREE pros_punters_car 0										
												IF LOCATE_CHAR_ON_FOOT_CAR_3D pros_ped pros_punters_car 10.0 10.0 3.0 FALSE
													// get in car
													pros_brain_flag = 2 
												ENDIF
											ENDIF
										ENDIF
									ENDIF														
								ELSE //pros punter in car
									// have prositute approach punter
									GET_CHAR_LIGHTING scplayer pros_light_level
									IF NOT pros_light_level  < 0.2
										CLEAR_PRINTS
										PRINT (PROS_01) 4000 1 //We need to find somewhere more secluded.
									ENDIF
									pros_brain_flag = 1	
								ENDIF				
							ELSE //pros_brain_flag = 0
								
								 IF pros_animation_loaded = 0
									REQUEST_ANIMATION BLOWJOBZ
								 	 pros_animation_loaded = 1
								 ENDIF
								 IF  pros_animation_loaded = 1
									IF HAS_ANIMATION_LOADED BLOWJOBZ
										 pros_animation_loaded = 2
									ENDIF
								 ENDIF
								
								 IF  pros_animation_loaded = 2
									
									
									//////////////////////
									///	Player on Foot ///
									//////////////////////									
									
									IF pros_brain_flag = 1						
										 //GET_CHAR_LIGHTING CharID ReturnLightingFloat
										// The default lighting value is 1.0f. A value between 0.0f and 1.0f means darker. A value greater than 1.0f means brighter									
										/// have it so that transaction only takes place in dark area										
											IF pros_task_assigned = 0
												GET_CHAR_LIGHTING scplayer pros_light_level
												IF pros_light_level  < 0.2
													pros_task_assigned = 1
													IF NOT IS_MESSAGE_BEING_DISPLAYED
														PRINT (PROS_02) 4000 1 //This seems an ok place.
													ELSE
														IF pros_following_punter = 1
															CLEAR_PRINTS
															PRINT (PROS_02) 4000 1 //This seems an ok place.
														ENDIF	
													ENDIF
												ELSE
													IF pros_following_punter = 0
														// follow player
														//TASK_FOLLOW_FOOTSTEPS pros_ped scplayer																					
														TASK_WALK_ALONGSIDE_CHAR pros_ped scplayer
														pros_following_punter = 1
													ENDIF

													//IF NOT IS_MESSAGE_BEING_DISPLAYED
													//	PRINT (PROS_01) 4000 1 //We need to find somewhere more secluded.
													//ENDIF
												ENDIF
											
											
											ENDIF										
										// let transaction take place										
										
										
										
										
										IF pros_task_assigned = 1										
											IF IS_PLAYER_PLAYING player1
												SET_PLAYER_CONTROL player1 OFF
												SWITCH_WIDESCREEN ON
												
												// start timer
												GET_GAME_TIMER pros_timer_start

												IF IS_CHAR_IN_ANY_CAR scplayer
													TASK_LEAVE_ANY_CAR scplayer
												ENDIF
												
												GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer  0.0 1.3 0.0  pros_x pros_y pros_z

												OPEN_SEQUENCE_TASK pros_seq 									   
												IF IS_CHAR_IN_ANY_CAR pros_ped
													TASK_LEAVE_ANY_CAR -1
												ENDIF
												//TASK_GOTO_CHAR -1 scplayer  -1 1.5												
												TASK_GO_TO_COORD_ANY_MEANS -1 pros_x pros_y pros_z PEDMOVE_WALK -1
												TASK_TURN_CHAR_TO_FACE_CHAR -1 scplayer
												TASK_LOOK_AT_CHAR -1 scplayer 4000
												TASK_STAND_STILL -1 2000							   									    										
												CLOSE_SEQUENCE_TASK pros_seq
												PERFORM_SEQUENCE_TASK pros_ped pros_seq
												CLEAR_SEQUENCE_TASK pros_seq									   											
												pros_task_assigned =2
											ENDIF																				
										ENDIF

										IF pros_task_assigned = 2								     
											GET_SCRIPT_TASK_STATUS pros_ped PERFORM_SEQUENCE_TASK  pros_task_status
											IF  pros_task_status  = PERFORMING_TASK	
												
												
												GET_SEQUENCE_PROGRESS pros_ped pros_task_status
												IF pros_task_status > 0 // after look at char task										   																							
													//IF NOT IS_MESSAGE_BEING_DISPLAYED
													//	PRINT (PROS_04) 4000 1 //Het baby, wanna party?
													//ENDIF
													OPEN_SEQUENCE_TASK punter_seq
													//TASK_GOTO_CHAR -1 pros_ped -1 1.1
													TASK_TURN_CHAR_TO_FACE_CHAR -1 pros_ped
													TASK_LOOK_AT_CHAR -1 pros_ped 4000													
													TASK_STAND_STILL -1 1000																																						
													CLOSE_SEQUENCE_TASK	punter_seq
													PERFORM_SEQUENCE_TASK scplayer punter_seq
													CLEAR_SEQUENCE_TASK punter_seq																							
													REQUEST_ANIMATION INT_SHOP
													pros_task_assigned = 3
												ENDIF
											ENDIF
										ENDIF

										
										IF pros_task_assigned = 3
											IF HAS_ANIMATION_LOADED INT_SHOP
												GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  pros_task_status
												IF  pros_task_status  = FINISHED_TASK												
													CLEAR_PRINTS
													PRINT_NOW (PROS_05) 4000 1 //Money first, then we'll party.
													IF IS_PLAYER_PLAYING player1 
														STORE_SCORE player1 players_money
													ENDIF
													
													OPEN_SEQUENCE_TASK punter_seq
													TASK_TURN_CHAR_TO_FACE_CHAR	-1 scplayer
													TASK_STAND_STILL -1 20000
													CLOSE_SEQUENCE_TASK punter_seq
													PERFORM_SEQUENCE_TASK pros_ped punter_seq
													CLEAR_SEQUENCE_TASK punter_seq

													OPEN_SEQUENCE_TASK punter_seq
													TASK_TURN_CHAR_TO_FACE_CHAR	-1 pros_ped													
													IF players_money >= pros_cost																							
														IF HAS_ANIMATION_LOADED INT_SHOP
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 shop_pay INT_SHOP 1000.0 FALSE FALSE FALSE FALSE -1
														ENDIF
														SET_CHAR_MONEY pros_ped pros_cost
														pros_cost *= -1
														ADD_SCORE player1 pros_cost																													
														pros_cost_f	=# pros_cost
														pros_cost_f *= -1.0
														INCREMENT_FLOAT_STAT PROSTITUTE_BUDGET pros_cost_f
													ELSE																					
														// prints message about not having enougn cash
														PRINT_NOW (PROS_09) 4000 1 //Stop wasteing my time!
														pros_active = 0
														pros_terminate = 1																													
													ENDIF																										
													CLOSE_SEQUENCE_TASK	punter_seq
													PERFORM_SEQUENCE_TASK scplayer punter_seq
													CLEAR_SEQUENCE_TASK punter_seq
													REMOVE_ANIMATION INT_SHOP
													pros_task_assigned = 4	 
													
												ENDIF
											ELSE
												REQUEST_ANIMATION INT_SHOP
											ENDIF
										ENDIF	
																
											
										IF pros_task_assigned = 4								     
											GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  pros_task_status
											IF  pros_task_status  = FINISHED_TASK
												IF HAS_ANIMATION_LOADED BLOWJOBZ
												OPEN_SEQUENCE_TASK pros_seq 									   																	   									    
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Start_W BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1										
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_End_W   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1							   									   
												TASK_WANDER_STANDARD -1
												CLOSE_SEQUENCE_TASK pros_seq
												PERFORM_SEQUENCE_TASK pros_ped pros_seq
												CLEAR_SEQUENCE_TASK pros_seq

												OPEN_SEQUENCE_TASK punter_seq
											   	TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Start_P BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Stand_End_P   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
												CLOSE_SEQUENCE_TASK	punter_seq
												PERFORM_SEQUENCE_TASK scplayer punter_seq
												CLEAR_SEQUENCE_TASK punter_seq
												pros_task_assigned = 5
												ELSE
													REQUEST_ANIMATION BLOWJOBZ
												ENDIF											
											ENDIF
										ENDIF

										IF pros_task_assigned = 5								     
											GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  pros_task_status
											IF  pros_task_status  = FINISHED_TASK	
												IF IS_PLAYER_PLAYING player1
												SET_PLAYER_CONTROL player1 ON
												ENDIF
												IF pros_after_player = 1
													SWITCH_WIDESCREEN OFF
												ENDIF
												REMOVE_ANIMATION BLOWJOBZ
												pros_active = 0
												pros_terminate = 1 // finished script
												INCREMENT_INT_STAT TIMES_VISITED_PROSTITUTE 1
											ELSE
												IF pros_task_status = PERFORMING_TASK
													GET_SEQUENCE_PROGRESS scplayer pros_task_status
													IF pros_task_status =  6 //end of bj
													   IF pros_pad_shaking = 0
													   		SHAKE_PAD PAD1 2800 250
															pros_pad_shaking = 1
														ENDIF
														SET_CHAR_SAY_CONTEXT pros_ped CONTEXT_GLOBAL_GIVING_HEAD  pros_dummy
													ENDIF
													IF pros_task_status = 1																											
													ENDIF
												ENDIF
											ENDIF
										ENDIF
										
										IF pros_task_assigned > 1
										   GET_GAME_TIMER pros_timer_current
										   pros_time_elapsed = pros_timer_current - pros_timer_start
										   IF pros_time_elapsed > 20000
										   OR IS_BUTTON_PRESSED PAD1 CROSS
											   SET_PLAYER_CONTROL player1 ON
											   SWITCH_WIDESCREEN OFF
											   CLEAR_CHAR_TASKS scplayer
											   CLEAR_CHAR_TASKS pros_ped
											   pros_terminate = 1
											   pros_active = 0
										   ENDIF
										
										ENDIF
																	
									ENDIF //pros brain = 1  
									
									/////////////////////
									/// PLAYER IN CAR ///
									/////////////////////
									
									
									IF pros_brain_flag = 2
										
										
										IF LOCATE_CHAR_ANY_MEANS_CHAR_3D pros_ped scplayer 10.5 10.5 4.0 FALSE
										
											IF IS_CHAR_IN_ANY_CAR scplayer
												STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer pros_punters_car
											ELSE
												pros_active = 0
												pros_terminate = 1 // finished script
											ENDIF																				




											IF NOT IS_CAR_DEAD pros_punters_car
												
												
												IF pros_task_assigned = 2								     
													GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK  pros_task_status
													IF  pros_task_status  = FINISHED_TASK	
														
														OPEN_SEQUENCE_TASK pros_seq   													
														TASK_LEAVE_ANY_CAR -1									   
														TASK_WANDER_STANDARD -1
														CLOSE_SEQUENCE_TASK pros_seq
														PERFORM_SEQUENCE_TASK pros_ped pros_seq
														CLEAR_SEQUENCE_TASK pros_seq
														
														
														IF pros_after_player = 1
															SWITCH_WIDESCREEN OFF
															IF IS_PLAYER_PLAYING Player1
															SET_PLAYER_CONTROL player1 ON
															ENDIF	
														ENDIF														
														REMOVE_ANIMATION BLOWJOBZ
														pros_task_assigned = 4
														pros_active = 0
														pros_terminate = 1 // finished script
														INCREMENT_INT_STAT TIMES_VISITED_PROSTITUTE 1														
													ELSE
														IF pros_task_status = PERFORMING_TASK
															GET_SEQUENCE_PROGRESS scplayer pros_task_status
															IF pros_task_status =  7 //end of bj
															   IF pros_pad_shaking = 0
															   		SHAKE_PAD PAD1 2800 250
																	pros_pad_shaking = 1
																ENDIF
															ENDIF
														ENDIF
													ENDIF
												ENDIF
												
												IF pros_task_assigned = 1								     																										
													GET_SCRIPT_TASK_STATUS pros_ped PERFORM_SEQUENCE_TASK  pros_task_status
													IF  pros_task_status  = FINISHED_TASK
													//OR IS_CHAR_SITTING_IN_ANY_CAR 	pros_ped
														IF HAS_ANIMATION_LOADED BLOWJOBZ
														
														SWITCH_WIDESCREEN ON 
														IF IS_PLAYER_PLAYING Player1
															SET_PLAYER_CONTROL Player1 OFF
														ENDIF
														OPEN_SEQUENCE_TASK pros_seq 									   																					    
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Start_W BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_W  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_End_W   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1													
														CLOSE_SEQUENCE_TASK pros_seq
														PERFORM_SEQUENCE_TASK pros_ped pros_seq
														CLEAR_SEQUENCE_TASK pros_seq

														
														OPEN_SEQUENCE_TASK punter_seq
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Start_P BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_Loop_P  BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BJ_Car_End_P   BLOWJOBZ 1000.0 FALSE FALSE FALSE FALSE -1
														CLOSE_SEQUENCE_TASK	punter_seq
														PERFORM_SEQUENCE_TASK scplayer punter_seq
														CLEAR_SEQUENCE_TASK punter_seq

														pros_task_assigned = 2
														ELSE
															REQUEST_ANIMATION BLOWJOBZ
														ENDIF
													ENDIF
												ENDIF
												
												
												IF pros_task_assigned = 0
													//ADD_BLIP_FOR_CAR pros_punters_car pros_punter_blip
													//SET_PLAYER_CONTROL player1 OFF												
													///////////// this doesn't work (dont know why it just doesn't)
													/* ped wont perform any task assigned here */
													
													IF NOT IS_MESSAGE_BEING_DISPLAYED
														//PRINT (PROS_03) 4000 1 //Nice car
														
														PRINT (PROS_06) 4000 1 //You've got money right ?
													ENDIF
													OPEN_SEQUENCE_TASK pros_seq 									   																								
													TASK_GOTO_CHAR -1 scplayer -1	3.0
													TASK_LOOK_AT_CHAR -1 scplayer 4000																									
													IF players_money >= pros_cost																							
														TASK_ENTER_CAR_AS_PASSENGER -1 pros_punters_car -1 0
														SET_CHAR_MONEY pros_ped pros_cost
														pros_cost *= -1
														ADD_SCORE player1 pros_cost																													
														pros_cost_f	=# pros_cost
														pros_cost_f *= -1.0
														INCREMENT_FLOAT_STAT PROSTITUTE_BUDGET pros_cost_f
													ELSE																					
														PRINT_NOW (PROS_09) 4000 1 //Stop wasteing my time!
														// prints message about not having enougn cash
														pros_active = 0
														pros_terminate = 1															
													ENDIF																										
													CLOSE_SEQUENCE_TASK pros_seq
													PERFORM_SEQUENCE_TASK pros_ped pros_seq
													CLEAR_SEQUENCE_TASK pros_seq
													
													//////////////////////////////////////////////////////////////
													//WARP_CHAR_INTO_CAR_AS_PASSENGER pros_ped pros_punters_car 0
													pros_task_assigned = 1																				
												ENDIF
											ELSE
												pros_active = 0
												pros_terminate = 1 // finished script
											ENDIF // LOCATE_CHAR_ANY_MEANS_3D																																													
																			
											
											

										ENDIF
									ENDIF //pros_brain_flag = 2
									 
								ENDIF //  pros_animation_loaded = 2
								
							ENDIF // pros_brain = 0

						ELSE // flag_player_on_mission = 1
							pros_active = 0
							pros_terminate = 1
						ENDIF // flag_player_on_mission = 1

					ENDIF //IF NOT IS_CHAR_DEAD scplayer
				ENDIF // pros_after_player = 1
			ENDIF
		

			///// if player too far anway from the prostitute clear it ? ///
			/*
			IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_2D pros_ped scplayer 50.0 50.0 FALSE
				pros_terminate = 1
				pros_active = 0
			ENDIF
			*/




		ELSE  //IS_CHAR_DEAD pros_ped
			pros_active = 0
			pros_terminate = 1
			pros_task_assigned = 5
			IF NOT IS_CHAR_DEAD scplayer
				IF pros_after_player = 1
					SWITCH_WIDESCREEN OFF			
					IF IS_PLAYER_PLAYING player1
					SET_PLAYER_CONTROL player1 ON
					ENDIF
				ENDIF
			ENDIF
		ENDIF //IS_CHAR_DEAD pros_ped



	ELSE //DOES_CHAR_EXIST
		IF NOT IS_CHAR_DEAD scplayer
			IF pros_after_player = 1
				SWITCH_WIDESCREEN OFF			
				IF IS_PLAYER_PLAYING player1
				SET_PLAYER_CONTROL player1 ON
				ENDIF
			ENDIF
		ENDIF
		
		
		
		//IF pros_terminate = 0
		//	pros_active = 0
	    //ENDIF
		
		
		IF pros_player_propositioned= 1
			CLEAR_HELP
	    ENDIF

		IF DOES_CHAR_EXIST pros_ped
			IF NOT IS_CHAR_DEAD pros_ped
				TASK_WANDER_STANDARD pros_ped
			ENDIF
		ENDIF

			
		MARK_CHAR_AS_NO_LONGER_NEEDED pros_ped
		//MARK_CHAR_AS_NO_LONGER_NEEDED pros_punter_ped
	    //MARK_CAR_AS_NO_LONGER_NEEDED  pros_punters_car
		REMOVE_ANIMATION BLOWJOBZ
		REMOVE_ANIMATION INT_SHOP
		//REMOVE_BLIP pros_punter_blip
		//REMOVE_BLIP pros_blip		
		CLEAR_THIS_PRINT PROS_01
		CLEAR_THIS_PRINT PROS_02
		CLEAR_THIS_PRINT PROS_03
		CLEAR_THIS_PRINT PROS_04
		CLEAR_THIS_PRINT PROS_05
		CLEAR_THIS_PRINT PROS_06
		CLEAR_THIS_PRINT PROS_07
		//CLEAR_ALL_VIEW_VARIABLES
		pros_active = 0
		TERMINATE_THIS_SCRIPT				 
	ENDIF


GOTO prostitute_brain_loop


/*
PRINT (PROS_01) 4000 1 //We need to find somewhere more secluded.
PRINT (PROS_02) 4000 1 //This seems an ok place.
PRINT (PROS_03) 4000 1 //Nice car
PRINT (PROS_04) 4000 1 //Het baby, wanna party?
PRINT (PROS_05) 4000 1 //Money first, then we'll party.
PRINT (PROS_06) 4000 1 //You've got money right ?
PRINT (PROS_09) 4000 1 //Stop wasteing my time!



THINGS STILL TO DO


SET_CHAR_HEALTH
// need to add the player health increase from being with a prostitute


 

SET_PLAYER_HOOKER PlayerID CharID
// use the above command to trigger the bouncy car animation, char is the prostitute ped, both must be in the same car
// can maybe use this as well as the in car blowjob stuff





*/




}



MISSION_END
