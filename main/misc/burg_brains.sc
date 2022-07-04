MISSION_START


 
////////////////////////
//// BURGLARY BRAIN ////
////////////////////////



burglary_brain:

{

    SCRIPT_NAME BURGLAR
	
    LVAR_INT burglary_ped
    LVAR_INT burglary_brain_flag
	LVAR_INT burglary_random_num   burglary_random_num2
	LVAR_INT burglary_task_assigned
	LVAR_INT burglary_terminate	
	//LVAR_INT burglary_decision
	LVAR_INT burglary_counter	 
	LVAR_INT burg_player_spotted		
	LVAR_INT burglary_decision_set
	LVAR_INT burglary_phone_timer_start
	LVAR_INT burglary_phone_timer_current
	LVAR_INT burglary_phone_timer_elapsed
	LVAR_INT burglary_task_status
	LVAR_INT burg_dummy_sample
	LVAR_INT burg_ped_is_gang_member
	LVAR_INT burg_done_gang_check 

    burglary_brain_flag 	= 0
	burglary_terminate 		= 0	
	burglary_task_assigned 	= 0
	burg_player_spotted 	= 0
	burglary_decision_set 	= 0
	burg_ped_is_gang_member = 0
	burg_done_gang_check 	= 0

    IF burglary_brain_flag = 1
        CREATE_CHAR PEDTYPE_CIVFEMALE 0 0.0 0.0 0.0 burglary_ped
    	COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_male_decision
		COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_female_decision 
		COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_gang_decision 
    ENDIF

	
	
	GENERATE_RANDOM_INT_IN_RANGE 0 6 burglary_random_num 	
	
	
	
	IF burglary_random_num = 2
		GENERATE_RANDOM_INT_IN_RANGE 0 6 burglary_random_num2
	ENDIF
	
	
	//burglary_terminate = 1

burglary_brain_loop:

    WAIT 0


IF DOES_CHAR_EXIST burglary_ped
AND burglary_terminate = 0 

	IF IS_PLAYER_PLAYING player1
	AND NOT IS_CHAR_DEAD burglary_ped        				   			

		//////////////////////////////////////////////////
		//// CHECK IF PED IS CREATED AS A GANG MEMBER ////
		//////////////////////////////////////////////////

		IF burg_done_gang_check = 0
			GET_PED_TYPE burglary_ped burg_dummy_sample
					
			burg_ped_is_gang_member = 1
			IF NOT burg_dummy_sample  = PEDTYPE_GANG_FLAT				
				//IF NOT burg_dummy_sample = PEDTYPE_GANG_GROVE
				IF NOT burg_dummy_sample = PEDTYPE_GANG_NMEX
					IF NOT burg_dummy_sample = PEDTYPE_GANG_SFMEX
						IF NOT burg_dummy_sample = PEDTYPE_GANG_VIET
							IF NOT burg_dummy_sample = PEDTYPE_GANG_MAFIA
								IF NOT burg_dummy_sample = PEDTYPE_GANG_TRIAD
									IF NOT burg_dummy_sample = PEDTYPE_GANG_SMEX
										burg_ped_is_gang_member = 0				 
									ELSE
										SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_ABUSE_RIFA TRUE		FALSE	FALSE burg_dummy_sample
									ENDIF
								ELSE
									SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_ABUSE_TRIAD TRUE		FALSE	FALSE burg_dummy_sample
								ENDIF
							ELSE
								SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_ABUSE_MAFIA TRUE		FALSE	FALSE burg_dummy_sample
							ENDIF
						ELSE
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_ABUSE_DA_NANG TRUE		FALSE	FALSE burg_dummy_sample
						ENDIF
					ELSE
						SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_ABUSE_GANG_LSV TRUE		FALSE	FALSE burg_dummy_sample
					ENDIF
				ELSE
					SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_ABUSE_GANG_VLA TRUE		FALSE	FALSE burg_dummy_sample
				ENDIF
				//ENDIF
			ELSE
				SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_ABUSE_GANG_BALLAS TRUE		FALSE	FALSE burg_dummy_sample
			ENDIF
			burg_done_gang_check = 1
		ENDIF




		

		IF NOT IS_CHAR_HOLDING_OBJECT scplayer -1
			IF  NOT burglary_decision_set = 1				
				
				
				///////////////////////////////////////
				//// add decision maker stuff here ////
				///////////////////////////////////////
				IF burg_ped_is_gang_member = 0
					IF IS_CHAR_MALE burglary_ped
						////////////////////////////
						/// MALE EVENT RESPONSES ///
						////////////////////////////
						REMOVE_DECISION_MAKER burglary_male_decision
						COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_male_decision										
						IF NOT burglary_male_decision = -1
							SET_CHAR_DECISION_MAKER burglary_ped burglary_male_decision
						ENDIF
						
						
						
						SET_CHAR_RELATIONSHIP burglary_ped ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1
						// male
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL	 	0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_SIMPLE_HANDS_UP 								0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_GUN_AIMED_AT 				TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 20.0 20.0 TRUE TRUE						
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_GUN_AIMED_AT 				TASK_SIMPLE_HANDS_UP				   				0.0 100.0 40.0 40.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 0.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL		0.0 100.0 0.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_HATE		TASK_SIMPLE_HANDS_UP					   			0.0 100.0 0.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DAMAGE						TASK_SIMPLE_BE_DAMAGED								0.0 100.0 10.0 10.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DAMAGE 					 	TASK_COMPLEX_KILL_PED_ON_FOOT	 					0.0 100.0 20.0 20.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DAMAGE 					 	TASK_SIMPLE_DUCK_FOREVER 		 					0.0 100.0 70.0 70.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DEAD_PED 					TASK_COMPLEX_INVESTIGATE_DEAD_PED 					0.0 100.0 100.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_SIMPLE_DUCK									0.0 100.0 50.0 50.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_COMPLEX_KILL_PED_ON_FOOT 						0.0 100.0 10.0 10.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_COMPLEX_TURN_TO_FACE_ENTITY  					0.0 100.0 10.0 10.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_SIMPLE_DUCK_FOREVER 							0.0 100.0 30.0 30.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SOUND_QUIET				    TASK_COMPLEX_INVESTIGATE_DISTURBANCE				0.0 100.0 0.0 0.0 FALSE TRUE						

					ELSE																															   
						//////////////////////////////
						/// FEMALE EVENT RESPONSES ///
						//////////////////////////////
						REMOVE_DECISION_MAKER burglary_female_decision 
						COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_female_decision 										
						IF NOT burglary_female_decision  = -1
							SET_CHAR_DECISION_MAKER burglary_ped burglary_female_decision 
						ENDIF												
						SET_CHAR_RELATIONSHIP burglary_ped ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1
						// female 																			
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 00.0 0.0 TRUE TRUE										 
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_SIMPLE_HANDS_UP				 0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_GUN_AIMED_AT               	TASK_COMPLEX_REACT_TO_GUN_AIMED_AT   0.0 100.0 50.0 50.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_GUN_AIMED_AT				 	TASK_SIMPLE_HANDS_UP		   		 0.0 100.0 30.0 30.0 TRUE TRUE							 
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_GUN_AIMED_AT				 	TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 10.0 10.0 TRUE TRUE		   		   		    		   
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_SIMPLE_HANDS_UP				 0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 00.0 0.0 TRUE TRUE		  
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DAMAGE					 	TASK_SIMPLE_BE_DAMAGED				 0.0 100.0 20.0 20.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DAMAGE					 	TASK_COMPLEX_KILL_PED_ON_FOOT		 0.0 100.0 60.0 60.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DAMAGE					 	TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 40.0 40.0 TRUE TRUE		   		   		   		   
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_SOUND_QUIET				    TASK_COMPLEX_INVESTIGATE_DISTURBANCE 0.0 100.0 0.0 0.0 FALSE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DEAD_PED 					TASK_COMPLEX_INVESTIGATE_DEAD_PED	 0.0 100.0 40.0 40.0 TRUE TRUE					
					ENDIF
				ELSE
				////////////////////////////
				/// PED IS A GANG MEMBER ///
				////////////////////////////
					REMOVE_DECISION_MAKER burglary_gang_decision 
					COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_gang_decision 										
					IF NOT burglary_gang_decision  = -1
						SET_CHAR_DECISION_MAKER burglary_ped burglary_gang_decision 
					ENDIF
					
					
					SET_CHAR_RELATIONSHIP burglary_ped ACQUAINTANCE_TYPE_PED_DISLIKE PEDTYPE_PLAYER1
					
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL	 	0.0 100.0 00.0 0.0 TRUE TRUE						
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_GUN_AIMED_AT 				TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 20.0 20.0 TRUE TRUE						
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 0.0 0.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL		0.0 100.0 0.0 0.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_DAMAGE						TASK_SIMPLE_BE_DAMAGED								0.0 100.0 10.0 10.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_DAMAGE 					 	TASK_COMPLEX_KILL_PED_ON_FOOT	 					0.0 100.0 20.0 20.0 TRUE TRUE						
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_DEAD_PED 					TASK_COMPLEX_INVESTIGATE_DEAD_PED 					0.0 100.0 100.0 0.0 TRUE TRUE								   		  						
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_SHOT_FIRED				    TASK_COMPLEX_KILL_PED_ON_FOOT 						0.0 100.0 10.0 10.0 TRUE TRUE																
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_SOUND_QUIET				    TASK_COMPLEX_INVESTIGATE_DISTURBANCE				0.0 100.0 0.0 0.0 FALSE TRUE
				ENDIF


				burglary_decision_set = 1
			ENDIF							
		ELSE // player is holding an object
			IF  NOT burglary_decision_set = 2
				

				///////////////////////////////////////
				//// add decision maker stuff here ////
				///////////////////////////////////////
				IF burg_ped_is_gang_member = 0	
					IF IS_CHAR_MALE burglary_ped
						////////////////////////////
						/// MALE EVENT RESPONSES ///
						////////////////////////////
						// male						
						
						REMOVE_DECISION_MAKER burglary_male_decision
						COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_male_decision										
						IF NOT burglary_male_decision = -1
							SET_CHAR_DECISION_MAKER burglary_ped burglary_male_decision
						ENDIF
						
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL		0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_SIMPLE_HANDS_UP 								0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_GUN_AIMED_AT 				TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 20.0 20.0 TRUE TRUE						
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_GUN_AIMED_AT 				TASK_SIMPLE_HANDS_UP				   				0.0 100.0 40.0 40.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL		0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_ACQUAINTANCE_PED_HATE		TASK_SIMPLE_HANDS_UP					   			0.0 100.0 00.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DAMAGE						TASK_SIMPLE_BE_DAMAGED								0.0 100.0 10.0 10.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DAMAGE 					 	TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 30.0 30.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DAMAGE 					 	TASK_SIMPLE_DUCK_FOREVER 							0.0 100.0 60.0 60.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DEAD_PED 					TASK_COMPLEX_INVESTIGATE_DEAD_PED 					0.0 100.0 50.0 0.0 TRUE TRUE								   		  
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_SIMPLE_DUCK									0.0 100.0 50.0 50.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_COMPLEX_KILL_PED_ON_FOOT 						0.0 100.0 20.0 20.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_COMPLEX_TURN_TO_FACE_ENTITY  					0.0 100.0 20.0 20.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SHOT_FIRED				    TASK_SIMPLE_DUCK_FOREVER 							0.0 100.0 10.0 10.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_SOUND_QUIET				    TASK_COMPLEX_INVESTIGATE_DISTURBANCE				0.0 100.0 0.0 0.0 FALSE TRUE

						SET_CHAR_RELATIONSHIP burglary_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_male_decision EVENT_DAMAGE TASK_SIMPLE_BE_DAMAGED 0.0 100.0 100.0 0.0 TRUE TRUE 

					ELSE
						//////////////////////////////
						/// FEMALE EVENT RESPONSES ///
						//////////////////////////////
						REMOVE_DECISION_MAKER burglary_female_decision 
						COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_female_decision 										
						IF NOT burglary_female_decision  = -1
							SET_CHAR_DECISION_MAKER burglary_ped burglary_female_decision 
						ENDIF
						
						
						SET_CHAR_RELATIONSHIP burglary_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
						// female 													

						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 0.0 0.0 TRUE TRUE										 
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_SIMPLE_HANDS_UP				 0.0 100.0 0.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_GUN_AIMED_AT               	TASK_COMPLEX_REACT_TO_GUN_AIMED_AT   0.0 100.0 50.0 50.0 TRUE TRUE						
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_GUN_AIMED_AT				 	TASK_SIMPLE_HANDS_UP		   		 0.0 100.0 20.0 20.0 TRUE TRUE							 
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_GUN_AIMED_AT				 	TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 10.0 10.0 TRUE TRUE		   		   		    		   
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_SIMPLE_HANDS_UP				 0.0 100.0 0.0 0.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 0.0 0.0 TRUE TRUE		  
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DAMAGE					 	TASK_SIMPLE_BE_DAMAGED				 0.0 100.0 30.0 30.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DAMAGE					 	TASK_COMPLEX_KILL_PED_ON_FOOT		 0.0 100.0 10.0 10.0 TRUE TRUE
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DAMAGE					 	TASK_SIMPLE_DUCK_FOREVER			 0.0 100.0 30.0 30.0 TRUE TRUE 
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_DEAD_PED 					TASK_COMPLEX_INVESTIGATE_DEAD_PED	 0.0 100.0 20.0 20.0 TRUE TRUE 
						ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_female_decision  EVENT_SOUND_QUIET				    TASK_COMPLEX_INVESTIGATE_DISTURBANCE 0.0 100.0 0.0 0.0 FALSE TRUE
					ENDIF
				
				
				ELSE
				////////////////////////////
				/// PED IS A GANG MEMBER ///
				////////////////////////////
					REMOVE_DECISION_MAKER burglary_gang_decision 
					COPY_SHARED_CHAR_DECISION_MAKER DM_PED_EMPTY  burglary_gang_decision 										
					IF NOT burglary_gang_decision  = -1
						SET_CHAR_DECISION_MAKER burglary_ped burglary_gang_decision 
					ENDIF
					
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_ACQUAINTANCE_PED_DISLIKE	 	TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL		0.0 100.0 00.0 0.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_GUN_AIMED_AT 				TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 20.0 20.0 TRUE TRUE					
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 00.0 0.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_ACQUAINTANCE_PED_HATE		TASK_COMPLEX_KILL_PED_ON_FOOT_KINDA_STAND_STILL		0.0 100.0 00.0 0.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_DAMAGE						TASK_SIMPLE_BE_DAMAGED								0.0 100.0 10.0 10.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_DAMAGE 					 	TASK_COMPLEX_KILL_PED_ON_FOOT						0.0 100.0 30.0 30.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_DEAD_PED 					TASK_COMPLEX_INVESTIGATE_DEAD_PED 					0.0 100.0 50.0 0.0 TRUE TRUE							   		  
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_SHOT_FIRED				    TASK_COMPLEX_KILL_PED_ON_FOOT 						0.0 100.0 20.0 20.0 TRUE TRUE
					ADD_CHAR_DECISION_MAKER_EVENT_RESPONSE burglary_gang_decision  EVENT_SOUND_QUIET				    TASK_COMPLEX_INVESTIGATE_DISTURBANCE				0.0 100.0 0.0 0.0 FALSE TRUE


					SET_CHAR_RELATIONSHIP burglary_ped ACQUAINTANCE_TYPE_PED_HATE PEDTYPE_PLAYER1
				ENDIF
				
				
				
				//SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_MUGGED	TRUE		FALSE	FALSE burg_dummy_sample
				burglary_decision_set = 2
			ENDIF
		ENDIF // end of if player holding an object




		IF IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_ACQUAINTANCE_PED_HATE
		OR IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_SHOT_FIRED
		OR IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_DAMAGE		
		OR IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_ACQUAINTANCE_PED_DISLIKE
	 	OR burglary_player_stealth = 100
		 	  
			////////////////////////////////////////////////////////////////////
			//// ACTIONS TO BE TAKEN IF THE PLAYER IS ON A BURGLARY MISSION ////
			////////////////////////////////////////////////////////////////////			
			IF burglary_player_spotted = 0
				IF burglary_random_num = 4
				AND burg_ped_is_gang_member = 0										
					
					// phone the police
					IF NOT HAS_MODEL_LOADED CELLPHONE
						REQUEST_MODEL CELLPHONE
					ELSE
						IF NOT IS_CHAR_DEAD burglary_ped
							TASK_USE_MOBILE_PHONE burglary_ped TRUE						
						ENDIF
						SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_JACKED_ON_STREET	 TRUE		FALSE	FALSE burg_dummy_sample
						GET_GAME_TIMER burglary_phone_timer_start
						burg_player_spotted = 1
						burglary_player_spotted = 1
					ENDIF
					
				ELSE
					
					IF burglary_random_num = 2
					// give them a weapon
						IF IS_CURRENT_CHAR_WEAPON burglary_ped WEAPONTYPE_UNARMED
							IF burglary_random_num2	= 1
								IF NOT HAS_MODEL_LOADED BAT
									REQUEST_MODEL BAT
								ELSE
									GIVE_WEAPON_TO_CHAR	burglary_ped WEAPONTYPE_BASEBALLBAT 0 
									SET_CURRENT_CHAR_WEAPON	burglary_ped WEAPONTYPE_BASEBALLBAT
									SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	 CONTEXT_GLOBAL_ATTACK_PLAYER  TRUE		FALSE	FALSE burg_dummy_sample
									GET_SCRIPT_TASK_STATUS burglary_ped	TASK_KILL_CHAR_ON_FOOT burglary_task_status
									IF 	burglary_task_status = FINISHED_TASK
									TASK_KILL_CHAR_ON_FOOT burglary_ped scplayer
									ENDIF
									burg_player_spotted = 1
									burglary_player_spotted = 1
								ENDIF
							ELSE
								IF burglary_random_num2 = 3
									IF NOT HAS_MODEL_LOADED COLT45
										REQUEST_MODEL COLT45
									ELSE
										GIVE_WEAPON_TO_CHAR	burglary_ped WEAPONTYPE_PISTOL 40 
										SET_CURRENT_CHAR_WEAPON	burglary_ped WEAPONTYPE_PISTOL
										SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	 CONTEXT_GLOBAL_ATTACK_PLAYER  TRUE		FALSE	FALSE burg_dummy_sample
										GET_SCRIPT_TASK_STATUS burglary_ped	TASK_KILL_CHAR_ON_FOOT burglary_task_status
										IF 	burglary_task_status = FINISHED_TASK
										TASK_KILL_CHAR_ON_FOOT burglary_ped scplayer
										ENDIF
										burg_player_spotted = 1
										burglary_player_spotted = 1
										SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_PULL_GUN TRUE		FALSE	FALSE burg_dummy_sample
									ENDIF
								ELSE
									IF burglary_random_num2 = 5
										IF NOT HAS_MODEL_LOADED SAWNOFF
											REQUEST_MODEL SAWNOFF
										ELSE
											GIVE_WEAPON_TO_CHAR	burglary_ped WEAPONTYPE_SAWNOFF_SHOTGUN 40 
											SET_CURRENT_CHAR_WEAPON	burglary_ped WEAPONTYPE_SAWNOFF_SHOTGUN
											SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	 CONTEXT_GLOBAL_ATTACK_PLAYER  TRUE		FALSE	FALSE burg_dummy_sample
											GET_SCRIPT_TASK_STATUS burglary_ped	TASK_KILL_CHAR_ON_FOOT burglary_task_status
											IF 	burglary_task_status = FINISHED_TASK
											TASK_KILL_CHAR_ON_FOOT burglary_ped scplayer
											ENDIF
											burg_player_spotted = 1
											burglary_player_spotted = 1
											SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_SHOOT	TRUE		FALSE	FALSE burg_dummy_sample
										ENDIF										 
									ELSE
										SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	 CONTEXT_GLOBAL_FIGHT  TRUE		FALSE	FALSE burg_dummy_sample
										GET_SCRIPT_TASK_STATUS burglary_ped	TASK_KILL_CHAR_ON_FOOT burglary_task_status
										IF 	burglary_task_status = FINISHED_TASK
										TASK_KILL_CHAR_ON_FOOT burglary_ped scplayer
										ENDIF
										burg_player_spotted = 1
										burglary_player_spotted = 1
									ENDIF								
								ENDIF
							ENDIF
						ELSE								
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	 CONTEXT_GLOBAL_FIGHT  TRUE		FALSE	FALSE burg_dummy_sample
							GET_SCRIPT_TASK_STATUS burglary_ped	TASK_KILL_CHAR_ON_FOOT burglary_task_status
							IF 	burglary_task_status = FINISHED_TASK
								TASK_KILL_CHAR_ON_FOOT burglary_ped scplayer
							ENDIF
							
							burg_player_spotted = 1
							burglary_player_spotted = 1
						ENDIF
					ELSE
						burglary_player_spotted = 1
						burg_player_spotted = 1					
					ENDIF
				ENDIF															
			ENDIF															
		ENDIF						   		    		     		    		   		    		   		  		  		  		   		   		   							   
		



		IF burg_player_spotted = 1
		OR burg_player_spotted = 2
			GET_SCRIPT_TASK_STATUS burglary_ped	TASK_KILL_CHAR_ON_FOOT burglary_task_status
			IF 	burglary_task_status = FINISHED_TASK
				IF  NOT IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_ACQUAINTANCE_PED_HATE
				AND NOT IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_SHOT_FIRED
				AND NOT IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_DAMAGE		
				AND NOT IS_CHAR_RESPONDING_TO_EVENT burglary_ped EVENT_ACQUAINTANCE_PED_DISLIKE
					SWITCH burglary_random_num
					
						CASE 0
							GET_SCRIPT_TASK_STATUS burglary_ped	TASK_DUCK burglary_task_status
							IF 	burglary_task_status = FINISHED_TASK
							TASK_DUCK burglary_ped -2
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_DUCK	 TRUE		FALSE	FALSE burg_dummy_sample
							ENDIF
						BREAK

						CASE 1
							GET_SCRIPT_TASK_STATUS burglary_ped	TASK_HANDS_UP burglary_task_status
							IF 	burglary_task_status = FINISHED_TASK
								TASK_HANDS_UP burglary_ped -2
								IF IS_CHAR_HOLDING_OBJECT scplayer -1
									SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_MUGGED	TRUE		FALSE	FALSE burg_dummy_sample
								ELSE
									SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_GUN_RUN TRUE		FALSE	FALSE burg_dummy_sample
								ENDIF
							ENDIF
						BREAK

						CASE 2
							GET_SCRIPT_TASK_STATUS burglary_ped	TASK_KILL_CHAR_ON_FOOT burglary_task_status
							IF 	burglary_task_status = FINISHED_TASK
								TASK_KILL_CHAR_ON_FOOT burglary_ped	scplayer
								SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	 CONTEXT_GLOBAL_ATTACK_PLAYER  TRUE		FALSE	FALSE burg_dummy_sample
							ENDIF
						BREAK
						
						CASE 3
							GET_SCRIPT_TASK_STATUS burglary_ped	TASK_STAND_STILL burglary_task_status
							IF 	burglary_task_status = FINISHED_TASK
							TASK_STAND_STILL  burglary_ped -2
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_GUN_COOL TRUE		FALSE	FALSE burg_dummy_sample
							ENDIF
						BREAK
							
						CASE 4
							IF flag_player_on_burglary_mission = 1																
								IF burg_player_spotted = 1 
									GET_GAME_TIMER burglary_phone_timer_current
									burglary_phone_timer_elapsed = burglary_phone_timer_current -  burglary_phone_timer_start
									IF burglary_phone_timer_elapsed  > 8000
										IF NOT IS_CHAR_DEAD burglary_ped
											GET_SCRIPT_TASK_STATUS burglary_ped TASK_USE_MOBILE_PHONE burglary_task_status
											IF NOT burglary_task_status = FINISHED_TASK
												TASK_USE_MOBILE_PHONE burglary_ped FALSE
											ENDIF
											MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
											burg_player_spotted = 2
											SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_CHASED	 TRUE		FALSE	FALSE burg_dummy_sample
										ENDIF
									ENDIF				
								ELSE
								   GET_SCRIPT_TASK_STATUS burglary_ped	TASK_HANDS_UP burglary_task_status
									IF 	burglary_task_status = FINISHED_TASK
									TASK_HANDS_UP burglary_ped	-2
										IF IS_CHAR_HOLDING_OBJECT scplayer -1
											SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_MUGGED	TRUE		FALSE	FALSE burg_dummy_sample
										ELSE
											SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_GUN_RUN TRUE		FALSE	FALSE burg_dummy_sample
										ENDIF
									ENDIF								
								ENDIF								
							ENDIF
							
							
							
						BREAK

						DEFAULT
							IF IS_CHAR_HOLDING_OBJECT scplayer -1
								SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_MUGGED	TRUE		FALSE	FALSE burg_dummy_sample
							ELSE
								IF IS_CHAR_MALE burglary_ped
									SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_GENERIC_INSULT_MALE TRUE		FALSE	FALSE burg_dummy_sample
								ELSE
									SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_GENERIC_INSULT_MALE TRUE		FALSE	FALSE burg_dummy_sample
								ENDIF
							ENDIF
						BREAK
					ENDSWITCH
				ENDIF
			ENDIF
			
			SWITCH burglary_random_num
			
				CASE 0							
					SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_DUCK	 TRUE		FALSE	FALSE burg_dummy_sample							
				BREAK

				CASE 1							
					IF IS_CHAR_HOLDING_OBJECT scplayer -1
						SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_MUGGED	TRUE		FALSE	FALSE burg_dummy_sample
					ELSE
						SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_GUN_RUN TRUE		FALSE	FALSE burg_dummy_sample
					ENDIF							
				BREAK

				CASE 2
					SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	 CONTEXT_GLOBAL_ATTACK_PLAYER  TRUE		FALSE	FALSE burg_dummy_sample							
				BREAK
				
				CASE 3							
					SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_GUN_COOL TRUE		FALSE	FALSE burg_dummy_sample							
				BREAK
					
				CASE 4							
					IF burg_player_spotted = 1 									
						SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_CHASED	 TRUE		FALSE	FALSE burg_dummy_sample														
					ELSE								   
						IF IS_CHAR_HOLDING_OBJECT scplayer -1
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_MUGGED	TRUE		FALSE	FALSE burg_dummy_sample
						ELSE
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_GUN_RUN TRUE		FALSE	FALSE burg_dummy_sample
						ENDIF																	
					ENDIF																													
				BREAK

				DEFAULT
					IF IS_CHAR_HOLDING_OBJECT scplayer -1
						SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped CONTEXT_GLOBAL_MUGGED	TRUE		FALSE	FALSE burg_dummy_sample
					ELSE
						IF IS_CHAR_MALE burglary_ped
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_GENERIC_INSULT_MALE TRUE		FALSE	FALSE burg_dummy_sample
						ELSE
							SET_CHAR_SAY_CONTEXT_IMPORTANT burglary_ped	CONTEXT_GLOBAL_GENERIC_INSULT_MALE TRUE		FALSE	FALSE burg_dummy_sample
						ENDIF
					ENDIF
				BREAK
			ENDSWITCH						
		
		ENDIF
	ELSE   // is player playing 
		burglary_terminate = 1	        	
	ENDIF				

ELSE  // dopes char exist				
	REMOVE_DECISION_MAKER burglary_male_decision
	REMOVE_DECISION_MAKER burglary_female_decision 
	REMOVE_DECISION_MAKER burglary_gang_decision 

	MARK_CHAR_AS_NO_LONGER_NEEDED burglary_ped										 
	MARK_MODEL_AS_NO_LONGER_NEEDED CELLPHONE
	MARK_MODEL_AS_NO_LONGER_NEEDED BAT
	MARK_MODEL_AS_NO_LONGER_NEEDED COLT45
	MARK_MODEL_AS_NO_LONGER_NEEDED SAWNOFF
	TERMINATE_THIS_SCRIPT			
ENDIF


GOTO burglary_brain_loop

/*

		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_CHASED	 burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_DUCK	 burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_GENERIC_INSULT_MALE  burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_GENERIC_INSULT_FEMALE burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_GUN_RUN burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_GUN_COOL burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_PULL_GUN burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_SHOCKED burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_SHOOT	burg_dummy_sample
		SET_CHAR_SAY_CONTEXT burglary_ped CONTEXT_GLOBAL_SHOOT_GENERIC	burg_dummy_sample



PRINT (BURG_01) 3000 1 //What are you doing in my house?
PRINT (BURG_02) 3000 1 //Please, take what you want just don't hurt me!
PRINT (BURG_03) 3000 1 // Please don't kill me!
PRINT (BURG_04) 3000 1 //Somebody help there's a strange man in my house!
PRINT (BURG_05) 3000 1 //I'm let letting you just walk in here and take my stuff without a fight.
PRINT (BURG_06) 3000 1 //The cops are coming. your going to jail where you belong 
PRINT (BURG_07) 3000 1 //Is that the police?... I'm being robbed send someone over here quick!
PRINT (BURG_08) 3000 1 //Hey! Don't you know its wrong to steal
PRINT (BURG_09) 3000 1 //Put that back right now!
PRINT (BURG_21) 3000 1 //]Who the fuck are you?
PRINT (BURG_10) 3000 1 //]Get the hell out of my house!
PRINT (BURG_11) 3000 1 //]What do you want, why are you here?
PRINT (BURG_12) 3000 1 //]Please, just leave us alone


PRINT (BURG_13) 3000 1 //]Hey where are you going with that?
PRINT (BURG_14) 3000 1 //]We can't afford to buy another one of those.
PRINT (BURG_15) 3000 1 //]Why does this keep happening to me?
PRINT (BURG_16) 3000 1 //]WHat have I done to deserve this


PRINT (BURG_17) 3000 1 //]I'm not taking this!
PRINT (BURG_18) 3000 1 //]Take it easy!
PRINT (BURG_19) 3000 1 //]I'm no threat to you.
PRINT (BURG_20) 3000 1 //]I'm too young to die!




*/





}


MISSION_END

