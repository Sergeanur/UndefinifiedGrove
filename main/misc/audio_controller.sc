MISSION_START
MISSION_END


// GLOBAL VARIABLES

// arrays
VAR_INT SLOT_play_status[2] SLOT_priority[2] SLOT_ped[2]
VAR_FLOAT SLOT_X[2] SLOT_Y[2] SLOT_Z[2]

VAR_INT SLOT_sample[2] load_sample 
VAR_TEXT_LABEL SLOT_text[2] load_text

// general variables used by most of the below scripts
VAR_INT actual_SLOT[2] SLOT_status[2]
VAR_INT current_slot preload_slot


/*
	###########################################################################
	##
	##	MAIN AUDIO CONTROLLER
	##
	###########################################################################
*/

{


LVAR_INT audio_counter other_slot 
 
start_audio_controller:
SCRIPT_NAME A_CONT

	// used as an increment on the array pointer as the audio slots are 1&2 
	actual_SLOT[0] = 1
	actual_SLOT[1] = 2

	SLOT_status[0] = -3
	SLOT_status[1] = -3
	SLOT_priority[0] = 0
	SLOT_priority[1] = 0
	SLOT_play_status[0] = 0
	SLOT_play_status[0]	= 0

	current_slot = 0
	preload_slot = 1

	SLOT_ped[0] = scplayer
	SLOT_ped[1] = scplayer

audio_controller:

	WAIT 0

	audio_counter = 0
	other_slot = 1

	IF SLOT_status[0] >= -2
	OR SLOT_status[1] >= -2 
	
		WHILE audio_counter < 2

			SWITCH SLOT_status[audio_counter] 

				CASE -1 // small timer to allow variables to be set before script checks them					
					SLOT_priority[audio_counter] = 0
					SLOT_status[audio_counter] = -3
				BREAK

				// CHECK TO SEE IF AUDIO SLOT HAS LOADED 
				CASE 0
					IF NOT HAS_MISSION_AUDIO_LOADED actual_SLOT[audio_counter] 
						BREAK
					ENDIF		


					// ###############################
					// ##
					// ## PLAY AUDIO OR MAKE IT WAIT
					// ##
					// ###############################

					// if the slot just loaded is the preload slot check if it can play now or has to wait
					IF audio_counter = preload_slot 	
						// if preloaded slot has higher priority, clear current slot and play this one 
						IF SLOT_priority[audio_counter] > SLOT_priority[other_slot]
							
							// swap slots
							current_slot = audio_counter
							preload_slot = other_slot

							// clear old current slot
							CLEAR_MISSION_AUDIO actual_SLOT[preload_slot]
							CLEAR_THIS_PRINT $SLOT_text[preload_slot]

							// if the old current slot was to be played by a ped enable his speech unless he/she is going to say the new audio
							IF NOT SLOT_play_status[preload_slot] = 0
								IF NOT IS_CHAR_DEAD SLOT_ped[preload_slot]
									STOP_CHAR_FACIAL_TALK SLOT_ped[preload_slot]
									IF NOT SLOT_ped[preload_slot] = SLOT_ped[current_slot]
										SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH SLOT_ped[preload_slot] FALSE 
										//ENABLE_CHAR_SPEECH SLOT_ped[preload_slot]
  									ENDIF
								ENDIF 
							ENDIF 
							
							SLOT_status[preload_slot] = -1
						ELSE
							// wait for current sample to finish before playing this one
							SLOT_status[audio_counter] = 1
							BREAK 
						ENDIF
					ENDIF

					// PLAY AUDIO IN CURRENT SLOT
					// attach the sample to a char or coord if it needs to be
					IF NOT SLOT_play_status[audio_counter] = 0 // char 
						IF NOT IS_CHAR_DEAD SLOT_ped[audio_counter]
							START_CHAR_FACIAL_TALK SLOT_ped[audio_counter] 999999
							IF SLOT_play_status[audio_counter] = 1 // attach to the char
								ATTACH_MISSION_AUDIO_TO_CHAR actual_SLOT[audio_counter] SLOT_ped[audio_counter]
							ENDIF
						ENDIF 
					ENDIF

					PLAY_MISSION_AUDIO actual_SLOT[audio_counter]
					PRINT_NOW $SLOT_text[audio_counter] 10000 1
					SLOT_status[audio_counter] = 2

				BREAK

				// CHECK FOR OTHER SLOT TO FINISH SO THIS ONE CAN LOAD
				CASE 1
					IF current_slot = audio_counter
						
						// attach the sample to a char or coord if it needs to be
						IF NOT SLOT_play_status[audio_counter] = 0 // char 
							IF NOT IS_CHAR_DEAD SLOT_ped[audio_counter]
								START_CHAR_FACIAL_TALK SLOT_ped[audio_counter] 999999
								IF SLOT_play_status[audio_counter] = 1 // attach to the char
									ATTACH_MISSION_AUDIO_TO_CHAR actual_SLOT[audio_counter] SLOT_ped[audio_counter]
								ENDIF
							ENDIF 
						ENDIF
						PLAY_MISSION_AUDIO actual_SLOT[audio_counter]
						PRINT_NOW $SLOT_text[audio_counter] 10000 1
						SLOT_status[audio_counter] = 2

					ENDIF  
				BREAK

				// CHECK FOR SAMPLE FINISHING
				CASE 2
					IF HAS_MISSION_AUDIO_FINISHED actual_SLOT[audio_counter]
						// this slot now becomes preload slot
						IF current_slot = audio_counter
							preload_slot = audio_counter
							current_slot = other_slot
						ENDIF
					ELSE
						// check if slot was attached to a ped or is playing frontend pretending to be a ped 
						IF NOT SLOT_play_status[audio_counter] = 0 // char 
							IF NOT IS_CHAR_DEAD SLOT_ped[audio_counter]
								BREAK	
							ENDIF 
						ENDIF
						BREAK
					ENDIF 	
				
				// USED TO CLEAR SLOTS (IF PLAYER GETS OUT OF A CAR DURING CONVERSATION ETC)
				CASE -2
					CLEAR_MISSION_AUDIO actual_SLOT[audio_counter]
					CLEAR_THIS_PRINT $SLOT_text[audio_counter]
					
					IF NOT SLOT_play_status[audio_counter] = 0
						IF NOT IS_CHAR_DEAD SLOT_ped[audio_counter]
							STOP_CHAR_FACIAL_TALK SLOT_ped[audio_counter]
							IF NOT SLOT_ped[audio_counter] = SLOT_ped[other_slot]
								SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH SLOT_ped[audio_counter] FALSE
								//ENABLE_CHAR_SPEECH SLOT_ped[audio_counter]
							ENDIF
						ENDIF 
					ENDIF

					SLOT_status[audio_counter] = -1	
				BREAK

			ENDSWITCH

			audio_counter ++
			other_slot --

		ENDWHILE
	ELSE
		SLOT_status[0] = -4
		SLOT_status[1] = -4	
	ENDIF

GOTO audio_controller
}


/*
	###################################################################################################################################
	##
	##	LOAD AND PLAY 
	##
	##  START_NEW_SCRIPT audio_load_and_play int:status / int:priority / int:ped (ped not needed for status 0)
	##
	##	status values;
	##	0 = play audio from front end
	##	1 = play audio from ped
	##	2 = play audio frontend but pretend its a ped
	##
	###################################################################################################################################
*/


{
audio_load_and_play: 
SCRIPT_NAME A_ALAP

// variables passed from START_NEW_SCRIPT
LVAR_INT alap_play_status alap_priority alap_ped
//LVAR_FLOAT alap_X alap_Y alap_Z 
	
	// check if the sample to load has a higher or equal priority to the samples in both audio slots
	IF alap_priority >= SLOT_priority[preload_slot]
	
		SLOT_sample[preload_slot] = load_sample
		$SLOT_text[preload_slot] = $load_text

		CLEAR_MISSION_AUDIO actual_SLOT[preload_slot]
		LOAD_MISSION_AUDIO actual_SLOT[preload_slot] SLOT_sample[preload_slot]
		 
		// check if previos preload_slot was for a ped and enable his/her speech if they are not saying  the new sample
		IF NOT SLOT_play_status[preload_slot] = 0
			IF NOT IS_CHAR_DEAD SLOT_ped[preload_slot]
				IF NOT SLOT_ped[preload_slot] = alap_ped
					SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH SLOT_ped[preload_slot] FALSE
					//ENABLE_CHAR_SPEECH SLOT_ped[preload_slot]
				ENDIF
			ENDIF
		ENDIF 
		
		SLOT_status[preload_slot] = 0 // check for loading

		SLOT_play_status[preload_slot] = alap_play_status
		// if new sample is attached to a ped turn off his speech
		IF NOT SLOT_play_status[preload_slot] = 0
			IF SLOT_play_status[preload_slot] = 0 // which it shouldnt do, this is just here to fool the compiler
				CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 alap_ped
			ENDIF

			SLOT_ped[preload_slot] = alap_ped
			IF NOT IS_CHAR_DEAD SLOT_ped[preload_slot] 
				SHUT_CHAR_UP_FOR_SCRIPTED_SPEECH SLOT_ped[preload_slot] TRUE
				//DISABLE_CHAR_SPEECH SLOT_ped[preload_slot] FALSE
			ENDIF
		ENDIF
		
//		// grab the coordinates in case they are used
//		SLOT_X[preload_slot] = alap_X
//		SLOT_Y[preload_slot] = alap_Y
//		SLOT_Z[preload_slot] = alap_Z
		
		SLOT_play_status[preload_slot] = alap_play_status
		SLOT_priority[preload_slot] = alap_priority

	ENDIF  
	
TERMINATE_THIS_SCRIPT
}


/*
	###################################################################################################################################
	##
	##	TERMINATE ALL SCRIPTS 
	##
	##  START_NEW_SCRIPT terminate_audio_controller
	##
	##	use in mission cleanup to remove all audio scripts running for the mission
	##
	###################################################################################################################################
*/


{
terminate_audio_controller:
SCRIPT_NAME A_TERM

	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME A_CONT
	TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME A_ALAP 

TERMINATE_THIS_SCRIPT	
}