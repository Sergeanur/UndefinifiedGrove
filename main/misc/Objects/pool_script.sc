MISSION_START



{///////////////////////////////////////////////////////////////////////////////
pool_script: 	////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME POOL

LVAR_INT this_table
LVAR_INT this_opponent
LVAR_INT this_cue
LVAR_INT flag
LVAR_INT pool_help
LVAR_INT temp_int
LVAR_INT temp_seq
LVAR_INT pool_guy_model

//LVAR_INT this_script_id

// globals
//VAR_INT pool_table_tracker

VAR_INT active_pool_table
VAR_INT active_pool_opponent
VAR_INT active_initial_cue

VAR_INT pool_min_bet
VAR_INT pool_max_bet

	
VAR_INT active_pool_script

//VAR_INT kill_pool_script

//GENERATE_RANDOM_INT_IN_RANGE 1 320000 this_script_id

// decide whether to allow this script to continue or not
IF active_pool_script = 0
	IF flag_player_on_mission = 0
		active_pool_script = 1 // this_script_id
		active_pool_table = 0
		active_pool_opponent = 0
	ELSE
		TERMINATE_THIS_SCRIPT
	ENDIF
ELSE
	// terminate script
	TERMINATE_THIS_SCRIPT
ENDIF


// ambient stuff - before game is started
flag = 0
pool_script_loop1:
WAIT 0

	// fake creates
	IF flag = -1
		CREATE_OBJECT K_POOLTABLESM 0.0 0.0 0.0 this_table
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 this_opponent
		CREATE_OBJECT K_POOLTABLESM 0.0 0.0 0.0 this_cue
		CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 active_pool_opponent
		CREATE_OBJECT K_POOLTABLESM 0.0 0.0 0.0 active_pool_table	
		CREATE_OBJECT K_POOLTABLESM 0.0 0.0 0.0 active_initial_cue  
	ENDIF


	// request stuff
	IF flag = 0
		IF IS_PLAYER_PLAYING player1
			IF CAN_PLAYER_START_MISSION player1
		        IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
					GENERATE_RANDOM_INT_IN_RANGE 0 2 temp_int
					IF temp_int = 0
						pool_guy_model = BMYPOL1
					ELSE
						pool_guy_model = BMYPOL2
					ENDIF
					

					REQUEST_MODEL pool_guy_model
					REQUEST_ANIMATION POOL
					REQUEST_MODEL POOLCUE
					WHILE NOT HAS_MODEL_LOADED pool_guy_model 
					   OR NOT HAS_ANIMATION_LOADED POOL
					   OR NOT HAS_MODEL_LOADED POOLCUE
						WAIT 0
					ENDWHILE
					flag++
				ELSE
					GOSUB cleanup_pool_script
				ENDIF
			ELSE
				GOSUB cleanup_pool_script
			ENDIF
		ELSE
			GOSUB cleanup_pool_script
		ENDIF
	ENDIF	

	// create stuff
	pool_script_restart:
	IF flag = 1
		// cleanup if player is on a date
		IF IS_PLAYER_PLAYING player1
			IF CAN_PLAYER_START_MISSION player1
		        IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
					IF DOES_OBJECT_EXIST this_table
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS this_table 0.0219 -1.1236 0.5363 x y z
						GET_GROUND_Z_FOR_3D_COORD x y z z
						IF NOT DOES_CHAR_EXIST this_opponent
							IF NOT DOES_CHAR_EXIST active_pool_opponent
								CREATE_CHAR PEDTYPE_CIVMALE	pool_guy_model x y z this_opponent
								SET_CHAR_DECISION_MAKER this_opponent iGlobalPedPanicDM
								MARK_MODEL_AS_NO_LONGER_NEEDED pool_guy_model
								GET_OBJECT_HEADING this_table heading
								SET_CHAR_HEADING this_opponent heading
							ELSE
								IF NOT IS_CHAR_DEAD active_pool_opponent
									this_opponent = active_pool_opponent
									SET_CHAR_COORDINATES this_opponent x y z
									GET_OBJECT_HEADING this_table heading
									SET_CHAR_HEADING this_opponent heading
								ELSE
									CREATE_CHAR PEDTYPE_CIVMALE	pool_guy_model x y z this_opponent
									SET_CHAR_DECISION_MAKER this_opponent iGlobalPedPanicDM
									MARK_MODEL_AS_NO_LONGER_NEEDED pool_guy_model
									GET_OBJECT_HEADING this_table heading
									SET_CHAR_HEADING this_opponent heading
								ENDIF
							ENDIF
						ELSE
							IF NOT IS_CHAR_DEAD this_opponent
								SET_CHAR_COORDINATES this_opponent x y z
								GET_OBJECT_HEADING this_table heading
								SET_CHAR_HEADING this_opponent heading
							ENDIF
						ENDIF
						IF NOT DOES_OBJECT_EXIST this_cue
							CREATE_OBJECT K_POOLQ 0.0 0.0 0.0 this_cue
						ENDIF
						IF NOT IS_CHAR_DEAD this_opponent
							IF NOT IS_CHAR_HOLDING_OBJECT this_opponent this_cue
								TASK_PICK_UP_OBJECT this_opponent this_cue 0.0 0.0 0.0 PED_HANDR HOLD_ORIENTATE_BONE_FULL NULL NULL TRUE	
							ENDIF
						ENDIF
						OPEN_SEQUENCE_TASK temp_seq
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_ChalkCue POOL 4.0 FALSE FALSE FALSE TRUE -1
							IF IS_PLAYER_PLAYING player1
								TASK_LOOK_AT_CHAR -1 scplayer 9000
							ENDIF
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 POOL_Idle_Stance POOL 4.0 FALSE TRUE TRUE FALSE 10000
							SET_SEQUENCE_TO_REPEAT temp_seq 1
						CLOSE_SEQUENCE_TASK temp_seq
						IF NOT IS_CHAR_DEAD this_opponent
							PERFORM_SEQUENCE_TASK this_opponent temp_seq
						ENDIF
						CLEAR_SEQUENCE_TASK temp_seq
						flag++
					ELSE
						//WRITE_DEBUG cleanup_pool1
					   GOSUB cleanup_pool_script	
					ENDIF
				ELSE
					//WRITE_DEBUG cleanup_pool2
					GOSUB cleanup_pool_script
				ENDIF
			ELSE
				//WRITE_DEBUG cleanup_pool3
				GOSUB cleanup_pool_script
			ENDIF
		ELSE
		   	//WRITE_DEBUG cleanup_pool4
			GOSUB cleanup_pool_script
		ENDIF
	ENDIF

	// process behaviour
	IF flag = 2
		IF flag_player_on_mission = 0
			IF IS_PLAYER_PLAYING player1
				IF iSetCasinoPanic = 0 	 
					IF DOES_OBJECT_EXIST this_table
						IF NOT IS_CHAR_DEAD	this_opponent
							
							IF HAS_CHAR_BEEN_DAMAGED_BY_CHAR this_opponent scplayer
								CLEAR_CHAR_TASKS this_opponent
								DROP_OBJECT this_opponent FALSE
								DELETE_OBJECT this_cue 
								GIVE_WEAPON_TO_CHAR this_opponent WEAPONTYPE_POOL_CUE 99999
								TASK_KILL_CHAR_ON_FOOT this_opponent scplayer
								CLEAR_HELP
								pool_help = 0
								GOTO safe_pool_cleanup
							ELSE
								IF LOCATE_CHAR_ON_FOOT_CHAR_3D this_opponent scplayer 1.5 1.5 1.5 FALSE
								
									IF IS_BUTTON_PRESSED PAD1 TRIANGLE
										
										STORE_SCORE player1 temp_int
										IF temp_int < 50 //pool_min_bet
											PRINT_NOW NOCASH 3000 1 // not enough money
										ELSE	
											IF pool_help = 1
												CLEAR_HELP
												pool_help = flag_player_on_mission
											ENDIF
											active_pool_table	 = this_table
											active_pool_opponent = this_opponent
											active_initial_cue   = this_cue
											load_and_launch_mission_if_poss = POOL_SCRIPT
											flag++

										ENDIF
									ELSE
										IF pool_help = 0
											PRINT_HELP_FOREVER POOL_A  
											pool_help = 1
										ENDIF
									ENDIF	

								ELSE						
									IF pool_help = 1
										CLEAR_HELP
										pool_help = 0
									ENDIF
								ENDIF		
							ENDIF
						ELSE
							GOTO safe_pool_cleanup	
						ENDIF
					ELSE
						GOTO safe_pool_cleanup
					ENDIF
				ELSE
					IF NOT IS_CHAR_DEAD this_opponent
						CLEAR_CHAR_TASKS this_opponent
					ENDIF
					GOTO safe_pool_cleanup
				ENDIF
			ELSE
				GOTO safe_pool_cleanup
			ENDIF
		ELSE
			IF NOT IS_CHAR_DEAD this_opponent
				CLEAR_CHAR_TASKS this_opponent
			ENDIF
			GOTO safe_pool_cleanup 
		ENDIF
	ENDIF
	

	// wait for pool script to start
	IF flag = 3
		IF flag_player_on_mission = 1
			//WRITE_DEBUG pool_started
			flag++
		ENDIF
	ENDIF

	// wait for pool script to finish
	IF flag = 4
		IF flag_player_on_mission = 0
			IF IS_PLAYER_PLAYING player1
				IF CAN_PLAYER_START_MISSION player1
		       	 	IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
						IF DOES_OBJECT_EXIST this_table
							IF NOT IS_CHAR_DEAD	this_opponent
								//WRITE_DEBUG pool_script_restarted
								flag = 1
								GOTO pool_script_restart
								

							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


	// cleanup
	IF IS_PLAYER_PLAYING player1
		IF DOES_OBJECT_EXIST this_table
			IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer this_table 80.0 80.0 FALSE
				GOTO safe_pool_cleanup
			ENDIF
		ELSE
			GOTO safe_pool_cleanup
		ENDIF
	ELSE
		GOTO safe_pool_cleanup
	ENDIF

GOTO pool_script_loop1



///////////////////   safe cleanup -  waits until the player has left area /////////////////
safe_pool_cleanup:
	
	IF IS_PLAYER_PLAYING player1
		IF DOES_OBJECT_EXIST this_table
			IF NOT LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer this_table 80.0 80.0 FALSE
				GOSUB cleanup_pool_script
			ENDIF
		ELSE
			GOSUB cleanup_pool_script
		ENDIF	
	ELSE
		GOSUB cleanup_pool_script
	ENDIF

WAIT 0
GOTO safe_pool_cleanup
///////////////////////////////////////////////////////////////////////////////////////////



cleanup_pool_script:
	
	//WRITE_DEBUG cleanup_top
	IF pool_help = 1
		CLEAR_HELP
	ENDIF
	CLEAR_THIS_PRINT NOCASH

	MARK_CHAR_AS_NO_LONGER_NEEDED this_opponent
	DELETE_OBJECT this_cue

	// remove loaded stuff
	MARK_MODEL_AS_NO_LONGER_NEEDED pool_guy_model
	REMOVE_ANIMATION POOL
	MARK_MODEL_AS_NO_LONGER_NEEDED POOLCUE

	//kill_pool_script = 0
	active_pool_script = 0
	active_pool_table = 0
	active_pool_opponent = 0
	TERMINATE_THIS_SCRIPT
RETURN

}

MISSION_END
