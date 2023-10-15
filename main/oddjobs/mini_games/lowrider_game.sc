MISSION_START
MISSION_END

// GLOBALS
VAR_INT lowrider_game_is_active
VAR_INT lowrider_pscore
VAR_INT lowrider_oscore
VAR_INT lowrider_level
VAR_INT lowrider_last_level
VAR_INT lowrider_opposition_skill


VAR_FLOAT wheel_fl wheel_bl	wheel_fr wheel_br

CONST_INT LOWRIDER_PERFECT_SCORE				50
CONST_INT LOWRIDER_GOOD_SCORE					20
CONST_INT LOWRIDER_OK_SCORE						10
CONST_INT LOWRIDER_BAD_SCORE					0
CONST_INT LOWRIDER_VBAD_SCORE					0

CONST_INT LOWRIDER_PERFECT_TIME					35  
CONST_INT LOWRIDER_PERFECT_TIME_NEG				-35 
CONST_INT LOWRIDER_GOOD_TIME					50  
CONST_INT LOWRIDER_GOOD_TIME_NEG				-50 
CONST_INT LOWRIDER_OK_TIME						100  
CONST_INT LOWRIDER_OK_TIME_NEG					-100 
CONST_INT LOWRIDER_BAD_TIME	 					200  
CONST_INT LOWRIDER_BAD_TIME_NEG					-200 
												
CONST_INT LOWRIDER_BEAT_PERFECT					0
CONST_INT LOWRIDER_BEAT_GOOD					1
CONST_INT LOWRIDER_BEAT_ALRIGHT					2
CONST_INT LOWRIDER_BEAT_PAST					3
CONST_INT LOWRIDER_BEAT_FUTURE					4
CONST_INT LOWRIDER_BEAT_WRONG_BUTTON			5
CONST_INT LOWRIDER_BEAT_MISTIMED_BUTTON			6
												
CONST_INT LOWRIDER_SCORE_LIMIT					999999			
												
CONST_INT LOWRIDER_OVERALL_BAD					0
CONST_INT LOWRIDER_OVERALL_GOOD					1
CONST_INT LOWRIDER_OVERALL_PERFECT				2
												
CONST_INT LOWRIDER_SCORE_PERFECT_MULTIPLER 		10
					
CONST_INT SPRITE_PERFECT						54
CONST_INT SPRITE_BAD							55
CONST_INT SPRITE_GOOD							56

CONST_INT LOWRIDER_SCORE_TOTAL_BEATS_TO_REPORT	4


VAR_INT lowr_perfect_beat_counter
VAR_INT lowr_good_beat_counter
VAR_INT lowr_bad_beat_counter
VAR_INT lowr_overall_state


{
lowrider_game:
SCRIPT_NAME LOWGAME

// input parameters
LVAR_INT pcar
LVAR_INT ocar
LVAR_INT bounce_girl

// workings
LVAR_FLOAT pcar_x pcar_y pcar_z
LVAR_FLOAT ocar_x ocar_y ocar_z
LVAR_FLOAT vec_x vec_y vec_z
LVAR_FLOAT force_multiplier
LVAR_INT flag
LVAR_INT temp_seq
LVAR_INT lowrider_sequence
LVAR_INT car_got_roof
LVAR_INT temp_int
LVAR_INT player_stick_position
LVAR_INT last_stick_position
LVAR_INT beat_time beat_type beat_num
LVAR_INT beat_fuckup
LVAR_INT cumulative_score
LVAR_INT last_scored_beat
LVAR_FLOAT temp_float
LVAR_INT last_opp_scored_beat

LVAR_INT stored_beat

LVAR_INT last_print

LVAR_INT consecutive_goods
LVAR_INT consecutive_bads

// set initial flags
force_multiplier = 0.01
lowrider_level = 0
flag = 0
car_got_roof = 0
lowrider_pscore	= 0
lowrider_oscore	= 0
player_stick_position = 0
beat_fuckup = 0
cumulative_score = 0
last_scored_beat = -1
last_opp_scored_beat = 0
last_stick_position = 0


// fake creates
IF flag = -1
	CREATE_CAR PONY 0.0 0.0 0.0 pcar
	CREATE_CAR PONY 0.0 0.0 0.0 ocar
	CREATE_CHAR PEDTYPE_CIVMALE MALE01 0.0 0.0 0.0 bounce_girl
	WAIT 0
	WAIT 0
	WAIT 0
ENDIF

// check input is valid
IF DOES_VEHICLE_EXIST pcar
	IF IS_CAR_DEAD pcar
		GOTO terminate_lowrider_game
	ENDIF
ELSE
	GOTO terminate_lowrider_game
ENDIF
IF DOES_VEHICLE_EXIST ocar
	IF IS_CAR_DEAD ocar
		GOTO terminate_lowrider_game
	ENDIF
ELSE
	GOTO terminate_lowrider_game
ENDIF

IF lowrider_opposition_skill < 1
	lowrider_opposition_skill = 1
ELSE
	IF lowrider_opposition_skill > 5
		lowrider_opposition_skill = 5
	ENDIF
ENDIF

// get initial values
IF NOT IS_CAR_DEAD pcar
	GET_CAR_COORDINATES pcar pcar_x pcar_y pcar_z
ENDIF
IF NOT IS_CAR_DEAD ocar
	GET_CAR_COORDINATES ocar ocar_x ocar_y ocar_z
ENDIF


lowrider_game_is_active = 1
lowrider_game_loop:
WAIT 0

	// this is to fix the bug if player dies from starvation while on mini game
	IF flag < 5
		IF NOT DOES_VEHICLE_EXIST pcar
			bd_terminate_script = 1
			flag = 5
		ENDIF
	ENDIF

	SWITCH flag

		// initialise
		CASE 0

			SET_CAR_DENSITY_MULTIPLIER 0.0
			SET_PED_DENSITY_MULTIPLIER 0.0
			CLEAR_AREA pcar_x pcar_y pcar_z 200.0 TRUE

			// load anims
			REQUEST_ANIMATION LOWRIDER
			WHILE NOT HAS_ANIMATION_LOADED LOWRIDER
				WAIT 0
			ENDWHILE

			// setup girl in car
			IF NOT IS_CAR_DEAD pcar

				LOCK_CAR_DOORS pcar CARLOCK_LOCKED_PLAYER_INSIDE
				APPLY_BRAKES_TO_PLAYERS_CAR player1 ON
				SET_CAR_PROOFS pcar TRUE TRUE TRUE TRUE TRUE

				// default offsets
				x = 0.6200 
				y = 0.3100 
				z = -0.1670
							    
			ENDIF
			// give girl initial sequence
			OPEN_SEQUENCE_TASK lowrider_sequence
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idleloop  LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idleloop  LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idleloop  LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idleloop  LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hurry LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hair  LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idleloop  LOWRIDER 4.0 FALSE FALSE FALSE FALSE -1
				SET_SEQUENCE_TO_REPEAT lowrider_sequence 1
			CLOSE_SEQUENCE_TASK lowrider_sequence
			IF NOT IS_CHAR_DEAD bounce_girl
				IF IS_CHAR_ATTACHED_TO_ANY_CAR	bounce_girl
					PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
				ENDIF
			ENDIF

			// check if car has roof
			car_got_roof = 0
			IF NOT IS_CAR_DEAD pcar
				IF IS_CAR_MODEL pcar REMINGTN
				OR IS_CAR_MODEL pcar SLAMVAN
				OR IS_CAR_MODEL pcar TORNADO
					car_got_roof = 1
				ELSE
					IF IS_CAR_MODEL pcar BLADE
					OR IS_CAR_MODEL pcar BROADWAY
					OR IS_CAR_MODEL pcar SAVANNA
						GET_CURRENT_CAR_MOD pcar VEHICLE_UPGRADE_ROOF temp_int
						IF NOT temp_int = -1
							car_got_roof = 1
						ENDIF
					ELSE
						car_got_roof = 1
					ENDIF
				ENDIF	

			ENDIF
			
			IF NOT IS_CHAR_DEAD bounce_girl
				IF NOT IS_CHAR_ATTACHED_TO_ANY_CAR	bounce_girl
					car_got_roof = 1
				ENDIF
			ENDIF	
			
			// initialise all the scores
			lowr_perfect_beat_counter = 0
			lowr_good_beat_counter	  = 0
			lowr_bad_beat_counter	  = 0
			lowrider_pscore			  = 0
			lowr_overall_state		  = 0
			lowrider_oscore			  = 0

			flag++
		BREAK

		// wait to make sure track is playing
		CASE 1
			GET_BEAT_TRACK_STATUS	temp_int
			IF temp_int = CUTSCENE_TRACK_PLAYING  
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING lowrider_pscore COUNTER_DISPLAY_NUMBER 1 LOWR1
				DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING lowrider_oscore COUNTER_DISPLAY_NUMBER 2 LOWR2	
				flag++
				TIMERA = 0
			ENDIF
		BREAK

		// game is playing - update scores and bounce girl
		CASE 2
			GET_BEAT_TRACK_STATUS	temp_int
			IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_SPACE
				TIMERA = 90000
			ENDIF

			GET_BEAT_PROXIMITY -1 beat_time beat_type beat_num


			IF NOT beat_type = 33
				FORCE_BIG_MESSAGE_AND_COUNTER  TRUE

/// -------------   FIGURE HOW GOOD THIS ATTEMPT WAS -----------------------------------
				GOSUB LOWR_get_stick_position
				GET_BEAT_PROXIMITY 0 beat_time beat_type beat_num
				//WRITE_DEBUG_WITH_INT beat_num beat_num
				 
// scoring system ---------------------------------------

				IF NOT beat_num = last_scored_beat

					stored_beat = -1

					// if player has moved stick
					IF NOT player_stick_position = 0
					
						// player hits beat spot on (zone 1)
						IF beat_time > LOWRIDER_PERFECT_TIME_NEG 
						AND beat_time < LOWRIDER_PERFECT_TIME
							
							// player has hit the right beat
							IF player_stick_position = beat_type
								stored_beat = LOWRIDER_BEAT_PERFECT
							ELSE
								// player hits wrong beat
								beat_fuckup = 1	
								stored_beat = LOWRIDER_BEAT_WRONG_BUTTON
							ENDIF	
						
						ELSE
							// player hits beat medium zone (zone 2)
							IF beat_time > LOWRIDER_GOOD_TIME_NEG 
							AND beat_time < LOWRIDER_GOOD_TIME
								
								// player has hit the right beat
								IF player_stick_position = beat_type
									stored_beat = LOWRIDER_BEAT_GOOD
								ELSE
									// player hits wrong beat
									beat_fuckup = 1	
									stored_beat = LOWRIDER_BEAT_WRONG_BUTTON
								ENDIF	
							
							ELSE
								// player hits beat in easy zone (zone 3)
								IF beat_time > LOWRIDER_OK_TIME_NEG
								AND beat_time < LOWRIDER_OK_TIME
									
									// player has hit the right beat
									IF player_stick_position = beat_type
										stored_beat = LOWRIDER_BEAT_ALRIGHT
									ELSE
										// player hits wrong beat
										beat_fuckup = 1	
										stored_beat = LOWRIDER_BEAT_WRONG_BUTTON
									ENDIF	
								
								ELSE
									// player has missed beat
									IF beat_time > LOWRIDER_BAD_TIME_NEG
									OR beat_time < LOWRIDER_BAD_TIME
										IF player_stick_position = beat_type
											beat_fuckup = 1	
											IF beat_time < 0
												stored_beat = LOWRIDER_BEAT_PAST
											ELSE
												stored_beat = LOWRIDER_BEAT_FUTURE
											ENDIF
										ELSE
											beat_fuckup = 1
											stored_beat = LOWRIDER_BEAT_WRONG_BUTTON	
										ENDIF
									ENDIF		
								ENDIF		
							ENDIF	
						ENDIF

						BD_RenderHit = 1

						IF stored_beat = LOWRIDER_BEAT_PERFECT
							BD_RenderHit = BD_HIT_PERFECT
							consecutive_goods++
						ELSE
							IF stored_beat = LOWRIDER_BEAT_GOOD
								BD_RenderHit = BD_HIT_GOOD
								consecutive_goods++
							ELSE
								BD_RenderHit = BD_HIT_STANDARD	
								consecutive_bads++
							ENDIF
						ENDIF

					ELSE
						IF beat_time < LOWRIDER_BAD_TIME_NEG
							beat_fuckup = 1
							stored_beat = LOWRIDER_BEAT_PAST
							consecutive_bads++
						ENDIF
					ENDIF


					IF NOT stored_beat = -1
						GOSUB LOWR_Update_Overall_Report
						GOSUB LOWR_update_score_and_stats
						GOSUB LOWR_Print_Input_Feedback
						last_scored_beat = beat_num
					ENDIF

				ENDIF

				// make sure players score can't fall below zero
				IF lowrider_pscore < 0
					lowrider_pscore = 0
				ENDIF
				
				// make sure cars don't roll over --------------------------
				IF NOT IS_CAR_DEAD pcar
					IF NOT LOCATE_CAR_2D pcar pcar_x pcar_y 3.0 3.0 FALSE
						GET_CAR_COORDINATES pcar x y z
						vec_x = pcar_x - x
						vec_y =	pcar_y - y
						vec_x *= force_multiplier
						vec_y *= force_multiplier
						APPLY_FORCE_TO_CAR pcar vec_x vec_y 0.0 0.0 0.0 0.0
					ENDIF
				ENDIF
				IF NOT IS_CAR_DEAD ocar
					IF NOT LOCATE_CAR_2D ocar ocar_x ocar_y 3.0 3.0 FALSE
						GET_CAR_COORDINATES ocar x y z
						vec_x = ocar_x - x
						vec_y =	ocar_y - y
						vec_x *= force_multiplier
						vec_y *= force_multiplier
						APPLY_FORCE_TO_CAR ocar vec_x vec_y 0.0 0.0 0.0 0.0
					ENDIF
				ENDIF

				// make opposition car bounce to the music -------------------
				IF beat_time < 100
				AND beat_time > -100  
					IF beat_type = 9
						wheel_fl = 1.0			 
						wheel_bl = 1.0
						wheel_fr = 0.0
						wheel_br = 0.0
					ENDIF
					IF beat_type = 10
						wheel_fl = 0.0
						wheel_bl = 0.0
						wheel_fr = 1.0
						wheel_br = 1.0
					ENDIF 
					IF beat_type = 11
						wheel_fl = 0.0
						wheel_bl = 0.0
						wheel_fr = 1.0
						wheel_br = 0.0
					ENDIF
					IF beat_type = 12
						wheel_fl = 0.0
						wheel_bl = 1.0
						wheel_fr = 0.0
						wheel_br = 0.0
					ENDIF
					IF beat_type = 13
						wheel_fl = 1.0
						wheel_bl = 0.0
						wheel_fr = 1.0
						wheel_br = 0.0
					ENDIF
					IF beat_type = 14
						wheel_fl = 0.0
						wheel_bl = 1.0
						wheel_fr = 0.0
						wheel_br = 1.0
					ENDIF
					IF beat_type = 15
						wheel_fl = 1.0
						wheel_bl = 0.0
						wheel_fr = 0.0
						wheel_br = 0.0
					ENDIF
					IF beat_type = 16
						wheel_fl = 0.0
						wheel_bl = 0.0
						wheel_fr = 0.0
						wheel_br = 1.0
					ENDIF

					// give opposition score depending on their skill
					IF NOT beat_num = last_opp_scored_beat
						GOSUB update_lowrider_opposition_score
						last_opp_scored_beat = beat_num
					ENDIF
				
				ELSE
					wheel_fl = 0.0
					wheel_bl = 0.0
					wheel_fr = 0.0
					wheel_br = 0.0
				ENDIF
				
				// control ocar hydraulics
				IF NOT IS_CAR_DEAD ocar
					IF DOES_CAR_HAVE_HYDRAULICS ocar
						CONTROL_CAR_HYDRAULICS ocar wheel_fl wheel_bl wheel_fr wheel_br
					ENDIF
				ENDIF


				// BOUNCING GIRL ===============================================================
				IF car_got_roof = 0
					
					// control the level of the bounce girl --------------------
					// once passed level -1 never go back
					IF lowrider_level = -1
						IF NOT player_stick_position = 0
							lowrider_level = 0
						ENDIF
					ENDIF
					// once passed level 0 never go back
					IF lowrider_level = 0
						IF consecutive_goods > 5
							lowrider_level++
							consecutive_goods = 0	
						ENDIF
					ENDIF

					IF lowrider_level = 1
						IF consecutive_goods > 5
							lowrider_level++
							consecutive_goods = 0
							consecutive_bads = 0
						ENDIF
					ENDIF

					IF lowrider_level = 2
						IF consecutive_goods > 5
							lowrider_level++
							consecutive_goods = 0
							consecutive_bads = 0
						ENDIF
						IF consecutive_bads > 1
							lowrider_level--
							consecutive_bads = 0
						ENDIF
					ENDIF

					IF lowrider_level = 3
						IF consecutive_goods > 5
							lowrider_level++
							consecutive_goods = 0
							consecutive_bads = 0
						ENDIF
						IF consecutive_bads > 1
							lowrider_level--
							consecutive_bads = 0
						ENDIF
					ENDIF

					IF lowrider_level = 4
						IF consecutive_goods > 5
							lowrider_level++
							consecutive_goods = 0
							consecutive_bads = 0
						ENDIF
						IF consecutive_bads > 1
							lowrider_level--
							consecutive_bads = 0
						ENDIF
					ENDIF

					IF lowrider_level = 5
						IF consecutive_bads > 1
							lowrider_level--
							consecutive_goods = 0
							consecutive_bads = 0
						ENDIF 
					ENDIF
					

					// ------------------------- girl anims --------------------------

					// we have changed l
					IF NOT IS_CHAR_DEAD bounce_girl
						IF NOT lowrider_last_level = lowrider_level
						
							IF lowrider_level = -1
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hair  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hurry LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idleloop  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									SET_SEQUENCE_TO_REPEAT lowrider_sequence 1
								CLOSE_SEQUENCE_TASK lowrider_sequence
							ENDIF

							IF lowrider_last_level = -1 
							AND lowrider_level = 0
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idle_to_l0  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l0_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence 
							ENDIF

							IF lowrider_last_level = 0 
							AND lowrider_level = 1
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l0_to_l1  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l1_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence 
							ENDIF

							IF lowrider_last_level = 1
							AND lowrider_level = 2
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l1_to_l2  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l2_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence 
							ENDIF

							IF lowrider_last_level = 2 
							AND lowrider_level = 3
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l2_to_l3  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l3_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence 
							ENDIF

							IF lowrider_last_level = 3 
							AND lowrider_level = 4
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l3_to_l4  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l4_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence 
							ENDIF

							IF lowrider_last_level = 4 
							AND lowrider_level = 5
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l4_to_l5  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l5_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence 
							ENDIF

							IF lowrider_last_level > 1 
							AND lowrider_level = 1
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l345_to_l1  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l1_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence 
							ENDIF

							IF lowrider_last_level = 1
							AND lowrider_level = 0
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l12_to_l0  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l0_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence
							ENDIF

							IF lowrider_last_level = 0
							AND lowrider_level = -1
								CLEAR_SEQUENCE_TASK lowrider_sequence
								OPEN_SEQUENCE_TASK lowrider_sequence
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hair  LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_hurry LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_idleloop  LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
								CLOSE_SEQUENCE_TASK lowrider_sequence	
							ENDIF

							lowrider_last_level = lowrider_level

						ENDIF
			

						IF NOT  last_stick_position = player_stick_position
						AND NOT player_stick_position = 0

							temp_int = 0
							temp_float = 1.0
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_idle_to_l0
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_idle_to_l0 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l12_to_l0
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l12_to_l0 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l345_to_l1
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l345_to_l1 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l0_to_l1
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l0_to_l1 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l1_to_l2
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l1_to_l2 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l2_to_l3
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l2_to_l3 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l3_to_l4
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l3_to_l4 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l4_to_l5
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l4_to_l5 temp_float
							ENDIF
							IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_bdbnce
								GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_bdbnce temp_float
							ENDIF
							IF temp_float < 0.9
								temp_int = 1
							ENDIF
						

							IF temp_int = 0

								IF lowrider_level = 0
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l0_bnce LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK bounce_girl temp_seq
									CLEAR_SEQUENCE_TASK temp_seq 
								ENDIF 
								IF lowrider_level = 1
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l1_bnce LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK bounce_girl temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
								IF lowrider_level = 2
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l2_bnce LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK bounce_girl temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
								IF lowrider_level = 3
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l3_bnce LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK bounce_girl temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
								IF lowrider_level = 4
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l4_bnce LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK bounce_girl temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
								IF lowrider_level = 5
									OPEN_SEQUENCE_TASK temp_seq
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l5_bnce LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
									CLOSE_SEQUENCE_TASK temp_seq
									PERFORM_SEQUENCE_TASK bounce_girl temp_seq
									CLEAR_SEQUENCE_TASK temp_seq
								ENDIF
							ENDIF
							last_stick_position = player_stick_position
						ENDIF


						IF beat_fuckup = 1
							IF player_stick_position = 0
								IF lowrider_level > 0
									IF NOT IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_bdbnce 
										OPEN_SEQUENCE_TASK temp_seq
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_bdbnce LOWRIDER 4.0 FALSE FALSE FALSE TRUE -1
										CLOSE_SEQUENCE_TASK temp_seq
										PERFORM_SEQUENCE_TASK bounce_girl temp_seq
										CLEAR_SEQUENCE_TASK temp_seq
										CLEAR_SEQUENCE_TASK lowrider_sequence
										OPEN_SEQUENCE_TASK lowrider_sequence
											TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 lrgirl_l1_loop LOWRIDER 4.0 TRUE FALSE FALSE FALSE -1
										CLOSE_SEQUENCE_TASK lowrider_sequence
									ENDIF
									lowrider_level = 1
									lowrider_last_level = 1	
								ENDIF 
							ENDIF
							beat_fuckup = 0
						ENDIF


						// if char is not playing certain anims then do the sequence task
						temp_int = 0
						temp_float = 1.0
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_bdbnce
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_bdbnce temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_idle_to_l0
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_idle_to_l0 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l12_to_l0
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l12_to_l0 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l345_to_l1
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l345_to_l1 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l0_bnce
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l0_bnce temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l0_to_l1
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l0_to_l1 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l1_to_l2
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l1_to_l2 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l2_to_l3
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l2_to_l3 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l3_to_l4
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l3_to_l4 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l4_to_l5
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l4_to_l5 temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l1_bnce
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l1_bnce temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l2_bnce
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l2_bnce temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l3_bnce
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l3_bnce temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l4_bnce
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l4_bnce temp_float
						ENDIF
						IF IS_CHAR_PLAYING_ANIM bounce_girl lrgirl_l5_bnce
							GET_CHAR_ANIM_CURRENT_TIME bounce_girl lrgirl_l5_bnce temp_float
						ENDIF
						IF temp_float < 0.9
							temp_int = 1 
						ENDIF

						IF temp_int = 0				
							GET_SCRIPT_TASK_STATUS bounce_girl PERFORM_SEQUENCE_TASK temp_int
							IF temp_int = FINISHED_TASK
								PERFORM_SEQUENCE_TASK bounce_girl lowrider_sequence
							ENDIF
						ENDIF



					ENDIF // is char dead
				ENDIF // car_got_roof 


				// quit out
				
				//SET_PLAYER_CONTROL player1 OFF  // This should be set, but breaks old saves :(

				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
					lowrider_pscore = 0
					flag++
				ENDIF

			ELSE
				// track has finished
				flag++
				TIMERA = 0
			ENDIF	
		BREAK

		// wait for beat display to finish
		CASE 3
			//IF beat_display_script_started = 0
				flag++
			//ENDIF
		BREAK

		// fade out music
		CASE 4
			IF IS_PLAYER_PLAYING player1
				SET_PLAYER_CONTROL player1 OFF
			ENDIF
			DO_FADE 500 FADE_OUT
			WHILE GET_FADING_STATUS
				WAIT 0
			ENDWHILE
			bd_terminate_script = 1
			flag++
		BREAK

		// track has ended
		CASE 5
			GET_BEAT_TRACK_STATUS	temp_int
			IF temp_int = CUTSCENE_TRACK_PLAYING
				STOP_BEAT_TRACK
			ENDIF

			CLEAR_ONSCREEN_COUNTER lowrider_pscore
			CLEAR_ONSCREEN_COUNTER lowrider_oscore
			REMOVE_ANIMATION LOWRIDER
			GOTO terminate_lowrider_game
		BREAK

	ENDSWITCH

GOTO lowrider_game_loop

update_lowrider_opposition_score:

	GENERATE_RANDOM_INT_IN_RANGE 0 10 temp_int

	IF lowrider_opposition_skill = 0
	OR lowrider_opposition_skill = 1

		SWITCH temp_int
			CASE 0
			CASE 1
				lowrider_oscore += LOWRIDER_BAD_SCORE
			BREAK
			CASE 2
			CASE 3	
			CASE 4
			CASE 5
				lowrider_oscore += LOWRIDER_OK_SCORE	
			BREAK
			CASE 6
			CASE 7
			CASE 8
				lowrider_oscore += LOWRIDER_GOOD_SCORE	
			BREAK
			CASE 9
				lowrider_oscore += LOWRIDER_PERFECT_SCORE
			BREAK 
		ENDSWITCH
	
	ELSE

		IF lowrider_opposition_skill = 2
			SWITCH temp_int
				CASE 0
					lowrider_oscore += LOWRIDER_BAD_SCORE
				BREAK
				CASE 1
				CASE 2
				CASE 3	
				CASE 4
					lowrider_oscore += LOWRIDER_OK_SCORE
				BREAK	
				CASE 5
				CASE 6
				CASE 7
					lowrider_oscore += LOWRIDER_GOOD_SCORE
				BREAK	
				CASE 8
				CASE 9
					lowrider_oscore += LOWRIDER_PERFECT_SCORE
				BREAK 
			ENDSWITCH
		ELSE
			IF lowrider_opposition_skill = 3
				SWITCH temp_int
					CASE 0
						lowrider_oscore += LOWRIDER_BAD_SCORE
					BREAK
					CASE 1
					CASE 2
					CASE 3	
						lowrider_oscore += LOWRIDER_OK_SCORE
					BREAK	
					CASE 4
					CASE 5
					CASE 6
						lowrider_oscore += LOWRIDER_GOOD_SCORE	
					BREAK
					CASE 7
					CASE 8
					CASE 9
						lowrider_oscore += LOWRIDER_PERFECT_SCORE
					BREAK 
				ENDSWITCH
			ELSE
				IF lowrider_opposition_skill = 4
					SWITCH temp_int
						CASE 0
						CASE 1
						CASE 2
							lowrider_oscore += LOWRIDER_OK_SCORE
						BREAK
						CASE 3		
						CASE 4
						CASE 5
						CASE 6
							lowrider_oscore += LOWRIDER_GOOD_SCORE	
						BREAK
						CASE 7
						CASE 8
						CASE 9
							lowrider_oscore += LOWRIDER_PERFECT_SCORE
						BREAK
					ENDSWITCH
				ELSE
					IF lowrider_opposition_skill = 5
						SWITCH temp_int
							CASE 0
							CASE 1
								lowrider_oscore += LOWRIDER_OK_SCORE
							BREAK
							CASE 2
							CASE 3	
							CASE 4	
							CASE 5
								lowrider_oscore += LOWRIDER_GOOD_SCORE
							BREAK
							CASE 6
							CASE 7	
							CASE 8
							CASE 9
								lowrider_oscore += LOWRIDER_PERFECT_SCORE
							BREAK 
						ENDSWITCH
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF	

	IF lowrider_oscore < 0
		lowrider_oscore = 0
	ENDIF

RETURN
			  



terminate_lowrider_game:
lowrider_game_is_active = 0
TERMINATE_THIS_SCRIPT


/********************************************
			PRINT INPUT FEEDBACK
********************************************/
LOWR_Print_Input_Feedback:

 SWITCH stored_beat
	CASE LOWRIDER_BEAT_PERFECT
		SHAKE_PAD PAD1 200 255
	BREAK
	CASE LOWRIDER_BEAT_GOOD
		SHAKE_PAD PAD1 200 200
	BREAK
	CASE LOWRIDER_BEAT_ALRIGHT
		SHAKE_PAD PAD1 200 110
	BREAK
	DEFAULT 
		SHAKE_PAD PAD1 200 50
	BREAK
 ENDSWITCH

 SWITCH last_print
		DEFAULT
			IF stored_beat = LOWRIDER_BEAT_PERFECT
				PRINT_BIG DNC_005 1000 5 // Great timing!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_GOOD
				PRINT_BIG DNC_006 1000 5 // Well done!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_ALRIGHT
				PRINT_BIG DNC_007 1000 5  // Alright
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_PAST
				PRINT_BIG DNC_008 1000 5 // Too late!	 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_FUTURE
				PRINT_BIG DNC_009 1000 5 // Too early!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_WRONG_BUTTON
				PRINT_BIG DNC_010 1000 5 // Wrong!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_MISTIMED_BUTTON
				PRINT_BIG DNC_011 1000 5 // Poor timing!
			ENDIF
			last_print = 2
		BREAK

		CASE 2
			IF stored_beat = LOWRIDER_BEAT_PERFECT
				PRINT_BIG DNC_012 1000 5 //Synchronized! 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_GOOD
				PRINT_BIG DNC_013 1000 5 //That's it! 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_ALRIGHT
				PRINT_BIG DNC_014 1000 5  //Not bad! 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_PAST
				PRINT_BIG DNC_015 1000 5 //You missed it! 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_FUTURE
				PRINT_BIG DNC_016 1000 5 //You're fast! 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_WRONG_BUTTON
				PRINT_BIG DNC_017 1000 5 //Not that one! 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_MISTIMED_BUTTON
				PRINT_BIG DNC_018 1000 5 //Try again! 
			ENDIF
			++last_print
		BREAK

		CASE 3
			IF stored_beat = LOWRIDER_BEAT_PERFECT
				PRINT_BIG DNC_019 1000 5 //The master! 
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_GOOD
				PRINT_BIG DNC_020 1000 5 //You got it!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_ALRIGHT
				PRINT_BIG DNC_021 1000 5  //Can do better!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_PAST
				PRINT_BIG DNC_022 1000 5 //You're late!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_FUTURE
				PRINT_BIG DNC_023 1000 5 //Slow down!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_WRONG_BUTTON
				PRINT_BIG DNC_024 1000 5 //Random!
			ENDIF
			IF stored_beat = LOWRIDER_BEAT_MISTIMED_BUTTON
				PRINT_BIG DNC_025 1000 5 //Not on time!
			ENDIF
			++last_print
		BREAK

	ENDSWITCH
 
RETURN


/********************************************
			UPDATE OVERALL REPORT
********************************************/
LOWR_Update_Overall_Report:

	temp_int = lowr_good_beat_counter + lowr_bad_beat_counter 
	temp_int += lowr_perfect_beat_counter

	IF temp_int > 0
		IF temp_int >= LOWRIDER_SCORE_TOTAL_BEATS_TO_REPORT
			IF lowr_perfect_beat_counter = LOWRIDER_SCORE_TOTAL_BEATS_TO_REPORT 
			 	IF lowr_overall_state = LOWRIDER_OVERALL_GOOD
				OR lowr_overall_state = LOWRIDER_OVERALL_PERFECT
					//--- Can enter perfect
					PRINT_BIG DNC_002 50000 7 // PERFECT! //use a big number, the next state will brak it
					lowr_overall_state = LOWRIDER_OVERALL_PERFECT
				ELSE
					PRINT_BIG DNC_004 50000 7 // GOOD! //use a big number, the next state will brak it
					lowr_overall_state = LOWRIDER_OVERALL_GOOD
				ENDIF
			ELSE
				IF lowr_good_beat_counter > lowr_bad_beat_counter
					PRINT_BIG DNC_004 50000 7 // GOOD! //use a big number, the next state will brak it
					lowr_overall_state = LOWRIDER_OVERALL_GOOD
				ELSE
					IF lowr_overall_state = LOWRIDER_OVERALL_PERFECT
						PRINT_BIG DNC_004 50000 7 // GOOD! //use a big number, the next state will brak it
						lowr_overall_state = LOWRIDER_OVERALL_GOOD
					ELSE
						IF lowr_overall_state = LOWRIDER_OVERALL_GOOD 
							PRINT_BIG DNC_003 50000 7 // BAD! //use a big number, the next state will brak it
							lowr_overall_state = LOWRIDER_OVERALL_BAD
						ELSE
							PRINT_BIG DNC_003 50000 7 // BAD! //use a big number, the next state will brak it
							lowr_overall_state = LOWRIDER_OVERALL_BAD
						ENDIF
					ENDIF
				ENDIF
			ENDIF
			//--- Clear the counters 
			lowr_perfect_beat_counter = 0
			lowr_good_beat_counter = 0
			lowr_bad_beat_counter = 0
		ENDIF
	ENDIF

RETURN

LOWR_update_score_and_stats:

	SWITCH stored_beat
		CASE LOWRIDER_BEAT_PERFECT
			IF lowrider_pscore < LOWRIDER_SCORE_LIMIT
				IF lowr_overall_state = LOWRIDER_OVERALL_PERFECT
					temp_int = LOWRIDER_PERFECT_SCORE * LOWRIDER_SCORE_PERFECT_MULTIPLER
					lowrider_pscore += temp_int
				ELSE
					lowrider_pscore += LOWRIDER_PERFECT_SCORE 
				ENDIF
			ENDIF
			lowr_perfect_beat_counter++
		BREAK
		CASE LOWRIDER_BEAT_GOOD
			IF lowrider_pscore < LOWRIDER_SCORE_LIMIT
				IF lowr_overall_state = LOWRIDER_OVERALL_PERFECT
					temp_int = LOWRIDER_GOOD_SCORE * LOWRIDER_SCORE_PERFECT_MULTIPLER
					lowrider_pscore += temp_int
				ELSE
					lowrider_pscore += LOWRIDER_GOOD_SCORE 
				ENDIF
			ENDIF
			lowr_good_beat_counter++
		BREAK
		CASE LOWRIDER_BEAT_ALRIGHT
			IF lowrider_pscore < LOWRIDER_SCORE_LIMIT
				IF lowr_overall_state = LOWRIDER_OVERALL_PERFECT
					temp_int = LOWRIDER_OK_SCORE * LOWRIDER_SCORE_PERFECT_MULTIPLER
					lowrider_pscore += temp_int
				ELSE
					lowrider_pscore += LOWRIDER_OK_SCORE 
				ENDIF
			ENDIF
			lowr_good_beat_counter++
		BREAK
		CASE LOWRIDER_BEAT_PAST
			IF lowrider_pscore > 0
				lowrider_pscore += LOWRIDER_BAD_SCORE 
			ENDIF
			lowr_bad_beat_counter++
		BREAK
		CASE LOWRIDER_BEAT_FUTURE
			IF lowrider_pscore > 0
				lowrider_pscore += LOWRIDER_BAD_SCORE 
			ENDIF
			lowr_bad_beat_counter++
		BREAK
		CASE LOWRIDER_BEAT_WRONG_BUTTON
			IF lowrider_pscore > 0
				lowrider_pscore += LOWRIDER_VBAD_SCORE 
			ENDIF
			lowr_bad_beat_counter++
		BREAK
		CASE LOWRIDER_BEAT_MISTIMED_BUTTON
			IF lowrider_pscore > 0
				lowrider_pscore += LOWRIDER_VBAD_SCORE 
			ENDIF
			lowr_bad_beat_counter++
		BREAK
	ENDSWITCH	

	// make sure score hasn't gone above maximum
	IF lowrider_pscore > LOWRIDER_SCORE_LIMIT
		lowrider_pscore = LOWRIDER_SCORE_LIMIT
	ENDIF

RETURN

LOWR_get_stick_position:

	GET_POSITION_OF_ANALOGUE_STICKS PAD1 lstickx lsticky rstickx rsticky

	temp_float =# rstickx
	vec_x = temp_float

	temp_float =# rsticky
	vec_y = temp_float

	GET_DISTANCE_BETWEEN_COORDS_2D 0.0 0.0 vec_x vec_y temp_float
	
	player_stick_position = 0

	IF temp_float > 64.0

		GET_ANGLE_BETWEEN_2D_VECTORS vec_x vec_y 0.0 -1.0 temp_float
		
		// must be up
		IF temp_float < 15.0 
			player_stick_position = 13 // up
		ELSE
			IF temp_float < 75.0 
				IF rstickx > 0
					player_stick_position = 11 // right and up
				ELSE
					player_stick_position = 15 // left and up
				ENDIF 
			ELSE
				IF temp_float < 105.0 
					IF rstickx > 0
						player_stick_position = 10 // right 
					ELSE
						player_stick_position = 9 // left 
					ENDIF
				ELSE
					IF temp_float < 165.0 
						IF rstickx > 0
							player_stick_position = 16 // right down 
						ELSE
							player_stick_position = 12 // left down
						ENDIF 	
					ELSE
						// must be down	
						player_stick_position = 14 // left down
					ENDIF
				ENDIF
			ENDIF
		ENDIF

	ENDIF

RETURN
}

			