MISSION_START




///////////////////////////
//// HOMLESS GUY BRAIN ////
///////////////////////////




homeless_ambience_brain:

{


    SCRIPT_NAME HMLES	

    LVAR_INT homeless_ped
	VAR_INT homeless_brain_flag
	VAR_INT homeless_number
	LVAR_INT homeless_car
	LVAR_INT homeless_player_in_car
	LVAR_INT homeless_seq
	LVAR_INT homeless_timer_start homeless_timer_current homeless_time_difference
	VAR_INT homeless_waiting_flag
	LVAR_FLOAT homeless_x homeless_y homeless_z
	LVAR_FLOAT homeless_temp_float	
	LVAR_INT homeless_temp_int
	LVAR_INT homeless_terminate
	LVAR_INT homeless_leader homeless_group_size
	LVAR_INT homeless_vomit
	LVAR_INT homeless_task_status home_dummy 
	LVAR_INT homeless_blip
	LVAR_INT homeless_booze

    homeless_brain_flag 	= 0
	homeless_waiting_flag 	= 0
	homeless_number 		= 0
	homeless_player_in_car 	= 0
	homeless_terminate 		= 0
	homeless_leader 		= 0
	homeless_group_size 	= 0
	homeless_temp_int		= 0
	//PRINT_NOW (HMLS_03) 3000 1 //I saw it first it's mine!

    IF homeless_brain_flag = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 homeless_ped
    	//ADD_BLIP_FOR_CHAR homeless_ped homeless_blip
    ENDIF

	
	
	
	GENERATE_RANDOM_INT_IN_RANGE 0 4 homeless_number
	homeless_number = 3	
	
	IF NOT fire_people_created = 0
	//OR flag_player_on_mission = 1
		homeless_terminate = 1		
		TERMINATE_THIS_SCRIPT
	ELSE
		IF DOES_CHAR_EXIST homeless_ped
			IF NOT IS_CHAR_DEAD homeless_ped
				//REMOVE_BLIP homeless_blip
				//ADD_BLIP_FOR_CHAR homeless_ped homeless_blip
				//SET_BLIP_AS_FRIENDLY homeless_blip TRUE
				CLEAR_CHAR_TASKS_IMMEDIATELY homeless_ped
				IF homeless_number = 2
				SET_ANIM_GROUP_FOR_CHAR homeless_ped drunkman  
				ENDIF
			ENDIF
		ENDIF
		fire_people_created = 1
	ENDIF
	
				     
	// poor anim group may be used for this script
	//VIEW_INTEGER_VARIABLE  homeless_number homeless_number
	//VIEW_INTEGER_VARIABLE  homeless_waiting_flag homeless_waiting_flag
	//VIEW_INTEGER_VARIABLE  homeless_brain_flag homeless_brain_flag

homeless_ambience_brain_loop:

    WAIT 0               
	
	IF DOES_CHAR_EXIST homeless_ped
	AND homeless_terminate = 0
	
		
		IF IS_PLAYER_PLAYING player1
       	AND NOT IS_CHAR_DEAD homeless_ped 
			
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer homeless_car
				homeless_player_in_car = 1
			ELSE
				homeless_player_in_car = 0
			ENDIF
			
			/* ************ GIVE BUM INITIAL TASKS **************** */
			
			IF homeless_brain_flag = 0																				
				
				////////////////////////////////////////////////////////////////
				//// HAVE A BUM THAT FOLLOWS PLAYER AROUND ASKING FOR MONEY ////
				////////////////////////////////////////////////////////////////
				
				IF homeless_number = 0
					IF LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer homeless_ped 10.0 10.0 3.0 FALSE
						IF HAS_CHAR_SPOTTED_CHAR homeless_ped scplayer																											//
							
							SET_CHAR_SAY_CONTEXT homeless_ped CONTEXT_GLOBAL_BUM_LATCH  home_dummy
							//OPEN_SEQUENCE_TASK homeless_seq
							//TASK_GOTO_CHAR -1 scplayer 5000 1.5
							IF homeless_player_in_car = 0
							TASK_WALK_ALONGSIDE_CHAR homeless_ped scplayer							
							ENDIF
							//CLOSE_SEQUENCE_TASK homeless_seq
							//PERFORM_SEQUENCE_TASK homeless_ped homeless_seq
							//CLEAR_SEQUENCE_TASK	homeless_seq							
							homeless_brain_flag = 1						
						ENDIF
					ENDIF
				ENDIF				
								
				///////////////////////////////////////////////////////////
				//// HAVE A BUM SITTING IN THE STREET ASKING FOR BOOZE ////
				///////////////////////////////////////////////////////////
				
				IF homeless_number = 1
					IF homeless_waiting_flag = 0
						REQUEST_ANIMATION SUNBATHE
						homeless_waiting_flag = 1
					ENDIF
					
					IF homeless_waiting_flag = 1
						IF HAS_ANIMATION_LOADED SUNBATHE
							homeless_waiting_flag = 2
						ELSE
						
						ENDIF
					ENDIF
					
					IF homeless_waiting_flag = 2								
						IF HAS_ANIMATION_LOADED SUNBATHE
						GET_CHAR_COORDINATES homeless_ped homeless_x homeless_y homeless_z
						
						OPEN_SEQUENCE_TASK homeless_seq
						//TASK_PLAY_ANIM	 -1  SBATHE_F_LieB2Sit SUNBATHE 1000.0 FALSE FALSE FALSE FALSE -1 
						TASK_PLAY_ANIM	 -1  ParkSit_M_In SUNBATHE 4.0 FALSE FALSE FALSE FALSE -1 
						TASK_PLAY_ANIM	 -1  ParkSit_M_IdleB SUNBATHE 4.0 FALSE FALSE FALSE FALSE -1 
						TASK_PLAY_ANIM	 -1  ParkSit_M_IdleA SUNBATHE 4.0 FALSE FALSE FALSE FALSE -1 
						TASK_PLAY_ANIM	 -1  ParkSit_M_IdleC SUNBATHE 4.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM	 -1  ParkSit_M_IdleB SUNBATHE 4.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM	 -1  ParkSit_M_IdleC SUNBATHE 4.0 FALSE FALSE FALSE FALSE -1
						TASK_PLAY_ANIM	 -1  ParkSit_M_Out SUNBATHE 4.0 FALSE FALSE FALSE FALSE -1  
						TASK_WANDER_STANDARD -1
						//SET_SEQUENCE_TO_REPEAT homeless_seq 1 
						CLOSE_SEQUENCE_TASK homeless_seq
						PERFORM_SEQUENCE_TASK homeless_ped homeless_seq
						CLEAR_SEQUENCE_TASK	homeless_seq
						homeless_temp_int = 0
						homeless_brain_flag = 1
						homeless_waiting_flag = 3
						ELSE 
						REQUEST_ANIMATION SUNBATHE
						ENDIF
					ENDIF					
				ENDIF

				/////////////////////////////////////////////
				/// HAVE DRUNK BUM STAGGER ABOUT AND SPEW ///
				/////////////////////////////////////////////
				
				
				IF homeless_number = 2
					//SET_ANIM_GROUP_FOR_CHAR homeless_ped drunkman
					IF homeless_waiting_flag = 0
						REQUEST_ANIMATION FOOD
						REQUEST_ANIMATION VENDING
						REQUEST_MODEL CJ_JUICE_CAN
						homeless_waiting_flag = 1
					ENDIF
					
					IF homeless_waiting_flag = 1
						IF HAS_ANIMATION_LOADED FOOD
						AND HAS_ANIMATION_LOADED VENDING
						AND HAS_MODEL_LOADED CJ_JUICE_CAN	
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer  0.0 1.3 0.0  homeless_x homeless_y homeless_z
							CREATE_OBJECT CJ_JUICE_CAN homeless_x homeless_y homeless_z homeless_booze
							homeless_waiting_flag = 2
						ENDIF
					ENDIF
					
					IF homeless_waiting_flag = 2
						IF HAS_ANIMATION_LOADED VENDING
							IF HAS_ANIMATION_LOADED FOOD
								
								//SET_ANIM_GROUP_FOR_CHAR homeless_ped drunkman
								GET_CHAR_COORDINATES homeless_ped homeless_x homeless_y homeless_z 
								OPEN_SEQUENCE_TASK homeless_seq																		
								
								//TASK_PLAY_ANIM	 -1	 WALK_drunk	  	PED 	1004.0 FALSE TRUE  TRUE  FALSE -1 // latest addition
								TASK_PICK_UP_OBJECT -1 homeless_booze 0.1 0.1 0.0 PED_HANDL HOLD_ORIENTATE_BONE_FULL NULL NULL -1
								TASK_PLAY_ANIM	 -1  VEND_Drink2_P 	VENDING 1004.0 FALSE FALSE FALSE FALSE -1 // latest addition
								TASK_PLAY_ANIM	 -1	 WALK_drunk	  	PED 	1004.0 FALSE TRUE  TRUE  FALSE -1 // latest addition
								TASK_PLAY_ANIM	 -1  VEND_Drink_P 	VENDING 1004.0 FALSE FALSE FALSE FALSE -1 // latest addition
								TASK_PLAY_ANIM	 -1	 WALK_drunk	  	PED 	1004.0 FALSE TRUE  TRUE  FALSE -1 // latest addition
								TASK_PLAY_ANIM	 -1  VEND_Drink2_P 	VENDING 1004.0 FALSE FALSE FALSE FALSE -1 // latest addition
								TASK_PLAY_ANIM	 -1  EAT_Vomit_P 	FOOD  	1004.0 FALSE FALSE FALSE FALSE -1 // latest addition							
								TASK_WANDER_STANDARD -1
								CLOSE_SEQUENCE_TASK homeless_seq
								PERFORM_SEQUENCE_TASK homeless_ped homeless_seq
								CLEAR_SEQUENCE_TASK	homeless_seq
								homeless_brain_flag = 1
								homeless_waiting_flag = 3
							ELSE
								REQUEST_ANIMATION FOOD
							ENDIF
						ELSE
							REQUEST_ANIMATION VENDING						
						ENDIF
					ENDIF					
				ENDIF				
								
			
				//////////////////////////////////////////////////
				/// HAVE TRAMP (IF MALE) PISSING IN THE STREET ///
				//////////////////////////////////////////////////
			
				IF homeless_number = 3
					//SET_ANIM_GROUP_FOR_CHAR homeless_ped drunkman
					IF homeless_waiting_flag = 0
						REQUEST_ANIMATION PAULNMAC
						
						homeless_waiting_flag = 1
					ENDIF
					
					IF homeless_waiting_flag = 1
						IF HAS_ANIMATION_LOADED PAULNMAC						
							homeless_waiting_flag = 2
						ENDIF
					ENDIF
					
					IF homeless_waiting_flag = 2
						IF HAS_ANIMATION_LOADED PAULNMAC							
							//SET_ANIM_GROUP_FOR_CHAR homeless_ped drunkman
							GET_CHAR_COORDINATES homeless_ped homeless_x homeless_y homeless_z 
							OPEN_SEQUENCE_TASK homeless_seq																		
							TASK_PLAY_ANIM -1 piss_in PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // paul idle
			  			    TASK_PLAY_ANIM -1 piss_loop PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // paul idle
			 			    TASK_PLAY_ANIM -1 piss_out PAULNMAC 4.0 FALSE FALSE FALSE FALSE -1 // paul idle							
							TASK_WANDER_STANDARD -1
							CLOSE_SEQUENCE_TASK homeless_seq
							PERFORM_SEQUENCE_TASK homeless_ped homeless_seq
							CLEAR_SEQUENCE_TASK	homeless_seq
							homeless_brain_flag = 1
							homeless_waiting_flag = 3							
						ELSE
							REQUEST_ANIMATION PAULNMAC						
						ENDIF
					ENDIF					
				ENDIF
			
			
			
			ENDIF



			


			
			/* ******** AFTER INITIAL TASKS HAVE BEEN GIVEN ********** */
			

			IF homeless_brain_flag = 1
				
				////////////////////////////////////////////////////////////////
				//// HAVE A BUM THAT FOLLOWS PLAYER AROUND ASKING FOR MONEY ////
				////////////////////////////////////////////////////////////////
				IF homeless_number = 0
					SET_CHAR_SAY_CONTEXT homeless_ped CONTEXT_GLOBAL_SOLICIT  home_dummy						
				ENDIF
				///////////////////////////////////////////////////////////
				//// HAVE A BUM SITTING IN THE STREET ASKING FOR BOOZE ////
				///////////////////////////////////////////////////////////	
				IF homeless_number = 1						
					SET_CHAR_SAY_CONTEXT homeless_ped CONTEXT_GLOBAL_SOLICIT  home_dummy
				ENDIF
				/////////////////////////////////////////////
				/// HAVE DRUNK BUM STAGGER ABOUT AND SPEW ///
				/////////////////////////////////////////////		
				IF homeless_number = 2								
							
				ENDIF
				//////////////////////////////////////////////////
				/// HAVE TRAMP (IF MALE) PISSING IN THE STREET ///
				//////////////////////////////////////////////////	
				IF homeless_number = 3								
							
				ENDIF
																		
				GET_GAME_TIMER homeless_timer_start
				homeless_brain_flag = 2
				
			ENDIF


			
			//////////////////////////////////
			//////////////////////////////////
			//////////////////////////////////
			
			
			
			IF homeless_brain_flag = 2
				////////////////////////////////////////////////////////////////
				//// HAVE A BUM THAT FOLLOWS PLAYER AROUND ASKING FOR MONEY ////
				////////////////////////////////////////////////////////////////
				
				IF homeless_number = 0
					IF NOT IS_MESSAGE_BEING_DISPLAYED
						GET_GAME_TIMER homeless_timer_current
						homeless_time_difference = homeless_timer_current - homeless_timer_start  
						IF homeless_time_difference > 8000
						AND homeless_time_difference < 10000
							//PRINT (HMLS_02) 2000 1 //C'mon you must have something you can give me?
							SET_CHAR_SAY_CONTEXT homeless_ped CONTEXT_GLOBAL_SOLICIT_UNREASONABLE  home_dummy
							TASK_KILL_CHAR_ON_FOOT homeless_ped scplayer
							fire_people_created = 0
							homeless_terminate = 1
						ELSE
							SET_CHAR_SAY_CONTEXT homeless_ped CONTEXT_GLOBAL_SOLICIT  home_dummy						
						ENDIF
					ENDIF
				ENDIF
				
				///////////////////////////////////////////////////////////
				//// HAVE A BUM SITTING IN THE STREET ASKING FOR BOOZE ////
				///////////////////////////////////////////////////////////
				
				IF homeless_number = 1
				   	GET_SCRIPT_TASK_STATUS homeless_ped PERFORM_SEQUENCE_TASK  homeless_task_status
					IF  homeless_task_status  = PERFORMING_TASK
				   		GET_SEQUENCE_PROGRESS  homeless_ped homeless_task_status
					   	IF homeless_task_status > 6
							IF homeless_temp_int = 0
						   	   homeless_temp_int = 1
						   	   GET_GAME_TIMER homeless_timer_start
						   	ELSE
							   	GET_GAME_TIMER homeless_timer_current
							   	homeless_time_difference = homeless_timer_current - homeless_timer_start
							   	IF	homeless_time_difference > 15000
							   		homeless_brain_flag = 0
							   		homeless_waiting_flag = 0
								ENDIF
							ENDIF
						ENDIF
					ENDIF

				ENDIF

				/////////////////////////////////////////////
				/// HAVE DRUNK BUM STAGGER ABOUT AND SPEW ///
				/////////////////////////////////////////////

				IF homeless_number = 2
					GET_SCRIPT_TASK_STATUS homeless_ped PERFORM_SEQUENCE_TASK  homeless_task_status
					IF  homeless_task_status  = PERFORMING_TASK										
						IF IS_CHAR_PLAYING_ANIM homeless_ped Eat_Vomit_P
							
							GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS homeless_ped 0.355 -0.116 0.048 homeless_x homeless_y homeless_z							
							GET_CHAR_ANIM_CURRENT_TIME homeless_ped  Eat_Vomit_P homeless_temp_float
							CREATE_FX_SYSTEM puke homeless_x homeless_y homeless_z TRUE homeless_vomit
							homeless_temp_float = 0.0
							homeless_temp_int = 0
							homeless_brain_flag = 3	
						ENDIF

					ENDIF
				ENDIF

				//////////////////////////////////////////////////
				/// HAVE TRAMP (IF MALE) PISSING IN THE STREET ///
				//////////////////////////////////////////////////

				IF homeless_number = 3
					GET_SCRIPT_TASK_STATUS homeless_ped PERFORM_SEQUENCE_TASK  homeless_task_status
					IF  homeless_task_status  = PERFORMING_TASK
						IF IS_CHAR_PLAYING_ANIM homeless_ped piss_in																	
							//GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS homeless_ped -0.0800  0.0100  0.0100 homeless_x homeless_y homeless_z					
							GET_CHAR_ANIM_CURRENT_TIME homeless_ped  piss_in homeless_temp_float						
							
							
							CREATE_FX_SYSTEM_ON_CHAR_WITH_DIRECTION PETROLCAN homeless_ped 0.0000  0.5800   -0.0800  0.000  0.0100  0.0000  TRUE homeless_vomit
							//CREATE_FX_SYSTEM_ON_CHAR_WITH_DIRECTION PETROLCAN homeless_ped -0.0800  0.1500  0.0400  0.0100  0.0000  -0.0000  TRUE homeless_vomit
							//ATTACH_FX_SYSTEM_TO_CHAR_BONE homeless_vomit homeless_ped BONE_PELVIS
							
							homeless_temp_float = 0.0						
							homeless_temp_int = 0
							homeless_brain_flag = 3	
						ENDIF
					ENDIF
				ENDIF

			ENDIF

			//////////////////////////////////
			//////////////////////////////////
			//////////////////////////////////
			
			
			IF homeless_brain_flag = 3
				
				////////////////////////////////////////////////////////////////
				//// HAVE A BUM THAT FOLLOWS PLAYER AROUND ASKING FOR MONEY ////
				////////////////////////////////////////////////////////////////
				
				IF homeless_number = 0
					
				ENDIF
				
				///////////////////////////////////////////////////////////
				//// HAVE A BUM SITTING IN THE STREET ASKING FOR BOOZE ////
				///////////////////////////////////////////////////////////
				
				IF homeless_number = 1
				
				ENDIF

				
				/////////////////////////////////////////////
				/// HAVE DRUNK BUM STAGGER ABOUT AND SPEW ///
				/////////////////////////////////////////////
				
				IF homeless_number = 2
					IF NOT homeless_temp_float = 1.0 
						
						IF NOT IS_CHAR_DEAD homeless_ped
							IF IS_CHAR_PLAYING_ANIM homeless_ped Eat_Vomit_P
								GET_CHAR_ANIM_CURRENT_TIME homeless_ped Eat_Vomit_P homeless_temp_float
							ENDIF
						ELSE
							homeless_temp_float = 1.0	 
						ENDIF
						
						IF homeless_temp_int = 0
							IF homeless_temp_float >= 0.50
								IF NOT homeless_vomit = -1								
									PLAY_FX_SYSTEM homeless_vomit
								ENDIF
								homeless_temp_int = 1
							ENDIF								
						ENDIF	 
													
					ELSE
						STOP_FX_SYSTEM homeless_vomit
						KILL_FX_SYSTEM homeless_vomit
						fire_people_created = 0
						homeless_terminate = 1
					ENDIF
					
				ENDIF

				//////////////////////////////////////////////////
				/// HAVE TRAMP (IF MALE) PISSING IN THE STREET ///
				//////////////////////////////////////////////////

				IF homeless_number = 3
					
					IF 	homeless_temp_int < 2
						GET_SCRIPT_TASK_STATUS homeless_ped PERFORM_SEQUENCE_TASK  homeless_task_status
						IF homeless_task_status = FINISHED_TASK
							IF homeless_temp_int = 1
								STOP_FX_SYSTEM homeless_vomit
								KILL_FX_SYSTEM homeless_vomit
								homeless_temp_int = 2
							ENDIF
							fire_people_created = 0
							homeless_terminate = 1
						ELSE
							IF homeless_task_status = PERFORMING_TASK
								GET_SEQUENCE_PROGRESS  homeless_ped homeless_task_status
						   		IF homeless_task_status > 2
									STOP_FX_SYSTEM homeless_vomit
									KILL_FX_SYSTEM homeless_vomit
									homeless_temp_int = 2
								ENDIF
							ELSE
								STOP_FX_SYSTEM homeless_vomit
								KILL_FX_SYSTEM homeless_vomit
								homeless_temp_int = 2
							ENDIF
						ENDIF
					ENDIF
					
					
					
					
					
					
				 	IF homeless_temp_int = 0 						
						IF NOT IS_CHAR_DEAD homeless_ped
							IF IS_CHAR_PLAYING_ANIM homeless_ped piss_in
								GET_CHAR_ANIM_CURRENT_TIME homeless_ped piss_in homeless_temp_float
							ENDIF
						ELSE
							homeless_temp_float = 1.0	 
						ENDIF

						IF homeless_temp_int = 0
							IF homeless_temp_float >= 0.6
								IF NOT homeless_vomit = -1								
									PLAY_FX_SYSTEM homeless_vomit
								ENDIF
								
								homeless_temp_int = 1
							ENDIF 
						ENDIF	 
					ELSE													  								
						IF homeless_temp_int = 1
							IF IS_CHAR_PLAYING_ANIM homeless_ped piss_out
								GET_CHAR_ANIM_CURRENT_TIME homeless_ped piss_out homeless_temp_float																		
								IF homeless_temp_float >= 0.42
									STOP_FX_SYSTEM homeless_vomit
									KILL_FX_SYSTEM homeless_vomit																						
									homeless_temp_int = 2
								ENDIF														
							ENDIF
						ENDIF
					ENDIF
																	
				ENDIF


			ENDIF // homeless brain flag   = 3

		   /////////////////////////////////////////////////////////////
		   /// PUT A CHECK AND IF PLAYER GOES OUT OF RANGE TERMINATE ///
		   /////////////////////////////////////////////////////////////

		   IF NOT LOCATE_CHAR_ANY_MEANS_CHAR_3D scplayer homeless_ped 30.0 30.0 20.0 FALSE
				fire_people_created = 0
				homeless_terminate = 1	
		   ENDIF
		
		ENDIF
				
	
	ELSE
		fire_people_created = 0
		homeless_terminate = 1
    ENDIF

	 IF IS_CHAR_DEAD homeless_ped
		homeless_terminate = 1
		fire_people_created = 0
	 ENDIF

	 IF homeless_terminate = 1
			
		
		IF DOES_CHAR_EXIST homeless_ped
			IF NOT IS_CHAR_DEAD homeless_ped
				TASK_WANDER_STANDARD homeless_ped
			ENDIF
		ENDIF
		
		STOP_FX_SYSTEM homeless_vomit
		KILL_FX_SYSTEM homeless_vomit
		
		MARK_CHAR_AS_NO_LONGER_NEEDED homeless_ped
		MARK_MODEL_AS_NO_LONGER_NEEDED CJ_JUICE_CAN 
		MARK_OBJECT_AS_NO_LONGER_NEEDED homeless_booze
		REMOVE_ANIMATION FOOD
		REMOVE_ANIMATION VENDING
		REMOVE_ANIMATION SUNBATHE
		REMOVE_ANIMATION PAULNMAC
		TERMINATE_THIS_SCRIPT
//		REMOVE_BLIP  homeless_blip	 
//	 	CLEAR_ALL_VIEW_VARIABLES
	 ENDIF
	

/*
PRINT (HMLS_01) 2000 1 //Hey buddy, spare a dollar?
PRINT (HMLS_02) 2000 1 //C'mon you must have something you can give me?
PRINT (HMLS_03) 2000 1 //I saw it first it's mine!
PRINT (HMLS_04) 2000 1 //Hey man, got anything to drink?

*/

GOTO homeless_ambience_brain_loop

}



MISSION_END
