MISSION_START
MISSION_END
{
bball_challenge_script:
SCRIPT_NAME BBCHAL


// parameters passed in
LVAR_INT bbhoop

// blips
LVAR_INT location_blip

// floats
LVAR_FLOAT x2 y2 z2
LVAR_FLOAT x3 y3 z3
LVAR_FLOAT vec_x vec_y vec_z
LVAR_FLOAT vec2_x vec2_y vec2_z
LVAR_FLOAT location_x location_y location_z
LVAR_FLOAT temp_float temp_float2

// ints
LVAR_INT m_stage
LVAR_INT m_goals
LVAR_INT help_flag
LVAR_INT temp_int temp_int2 temp_int3
LVAR_INT next_time_to_award
LVAR_INT new_high_score
//LVAR_INT round
LVAR_INT missed_flag
LVAR_INT missed_flag2
LVAR_INT time_to_add_on

VAR_INT bball_r3_is_pressed

m_stage = 0
m_goals = 0
help_flag = 0
bball_challenge_timer = 60500
bball_challenge_score = 0
new_high_score = 0
//round = 0
missed_flag	 = 0
missed_flag2 = 0
time_to_add_on = 3000

new_marker_distance = 4.0

TIMERA = 0
TIMERB = 0

bball_challenge_active = 1

// fake create
IF m_stage = -1
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 bbhoop
ENDIF

bball_challenge_loop:
WAIT 0

	SWITCH m_stage
		CASE 0
			GOSUB bbchal_m_stage_0	
		BREAK
		CASE 1
			GOSUB bbchal_m_stage_1
		BREAK
	ENDSWITCH

	IF bball_active = 0 
	AND bball_throw_active = 0
		GOSUB bbchal_cleanup
	ENDIF

	GOSUB bbchal_display_help

	IF bball_is_active = 0
		GOSUB bbchal_cleanup
	ENDIF

	IF bball_r3_is_pressed = 0
		IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			bball_r3_is_pressed = 1
			GOSUB bbchal_cleanup
		ENDIF
	ELSE
		IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
			bball_r3_is_pressed = 0
		ENDIF		
	ENDIF

GOTO bball_challenge_loop

bbchal_display_help:
	// print help

	SWITCH help_flag

		CASE 0
			IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			AND NOT IS_MESSAGE_BEING_DISPLAYED
				PRINT_HELP BB_11  // Shoot as many hoops as possible before the time expires.
				help_flag++
			ENDIF	
		BREAK
		CASE 1
			IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			AND NOT IS_MESSAGE_BEING_DISPLAYED
				PRINT_HELP BB_12   // Extra time will be awarded for each shot made
				help_flag++
			ENDIF
		BREAK
		CASE 2
			IF NOT IS_HELP_MESSAGE_BEING_DISPLAYED
			AND NOT IS_MESSAGE_BEING_DISPLAYED
				PRINT_HELP BB_10   // Shoot hoops from the ~y~marker~w~ on the court.
				help_flag++
			ENDIF
		BREAK

	ENDSWITCH

RETURN

bbchal_m_stage_0:

	// print instructions
	IF m_goals = 0
		PRINT_NOW BB_13 5000 1
		TIMERA = 0
		m_goals++
	ENDIF

	IF m_goals = 1
		//IF TIMERA > 5000
			m_goals++
		//ENDIF
	ENDIF
	
	// create onscreen timers and text
	IF m_goals = 2
		//IF help_flag > 2
			DISPLAY_ONSCREEN_TIMER_WITH_STRING bball_challenge_timer TIMER_DOWN BB_19 // time
			DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING bball_challenge_score COUNTER_DISPLAY_NUMBER 1 BB_18 // score
			DISPLAY_NTH_ONSCREEN_COUNTER_WITH_STRING bball_challenge_high_score COUNTER_DISPLAY_NUMBER 2 BB_14 // high score
			GOSUB bbchal_get_new_marker
			ADD_BLIP_FOR_COORD location_x location_y location_z location_blip
			m_goals++
		//ENDIF
	ENDIF

	// wait for player to shoot from first marker
	IF m_goals = 3
		IF IS_PLAYER_PLAYING player1
			IF LOCATE_CHAR_ON_FOOT_3D scplayer location_x location_y location_z 1.5 1.5 2.0 TRUE
				IF bball_throw_active = 1
					IF DOES_BLIP_EXIST location_blip
						REMOVE_BLIP location_blip
					ENDIF		
					m_goals = 1
					m_stage++
				ELSE
					bball_challenge_timer = 60500	
				ENDIF
			ELSE
				bball_challenge_timer = 60500		
			ENDIF
		ENDIF
	ENDIF

RETURN

bbchal_m_stage_1:

	//WRITE_DEBUG_WITH_INT m_goals m_goals
	//WRITE_DEBUG_WITH_INT m_stage m_stage

	//WRITE_DEBUG_WITH_INT points_scored points_scored
	IF m_goals = 0
		// see if shot went in
		IF NOT points_scored = 0
			IF points_scored > 0
						bball_challenge_score += points_scored
						bball_challenge_timer += next_time_to_award

						new_marker_distance += 0.5
						GOSUB bbchal_get_new_marker	
						IF DOES_BLIP_EXIST location_blip
							REMOVE_BLIP location_blip
						ENDIF
						ADD_BLIP_FOR_COORD location_x location_y location_z location_blip
						//points_scored = 0
						m_goals++

					ELSE
				// missed
				IF DOES_BLIP_EXIST location_blip
					REMOVE_BLIP location_blip
				ENDIF
				ADD_BLIP_FOR_COORD location_x location_y location_z location_blip
				IF missed_flag = 0
					PRINT_NOW BB_20	5000 1
	   				missed_flag++
				ENDIF
				//points_scored = 0
				m_goals++
			ENDIF
		ENDIF
	ENDIF

	// wait for player to take shot from location
	IF m_goals = 1
		IF IS_PLAYER_PLAYING player1
			LOCATE_CHAR_ON_FOOT_3D scplayer location_x location_y location_z 1.5 1.5 2.0 TRUE
		ENDIF
		IF bball_throw_active = 1
			IF IS_PLAYER_PLAYING player1
				IF LOCATE_CHAR_ON_FOOT_3D scplayer location_x location_y location_z 1.5 1.5 2.0 TRUE
					IF DOES_BLIP_EXIST location_blip
						REMOVE_BLIP location_blip
					ENDIF
					//points_scored = 0
					m_goals = 0
					bball_throw_active = 0
					//WRITE_DEBUG true1
				ELSE
					IF missed_flag2 = 0
						PRINT_NOW BB_16	5000 1 // take the shot from the marker
						missed_flag2++
					ENDIF
					bball_throw_active = 0
					//WRITE_DEBUG false1
				ENDIF
			ENDIF
		ELSE
			missed_flag2 = 0	
		ENDIF
	ENDIF

//	// wait for player to slam dunk
//	IF m_goals = 2
//		IF IS_PLAYER_PLAYING player1
//			LOCATE_CHAR_ON_FOOT_3D scplayer location_x location_y location_z 1.5 1.5 2.0 TRUE
//		ENDIF
//		IF bball_throw_active = 1
//			IF IS_PLAYER_PLAYING player1	
//				IF LOCATE_CHAR_ON_FOOT_3D scplayer location_x location_y location_z 1.5 1.5 2.0 TRUE
//					IF DOES_BLIP_EXIST location_blip
//						REMOVE_BLIP location_blip
//					ENDIF
//					m_goals = 0
//				ELSE
//					bball_throw_active = 0
//				ENDIF
//			ENDIF
//		ENDIF
//	ENDIF

	// finishing
	IF m_goals = 3
		IF TIMERA > 5000
			GOSUB bbchal_cleanup
		ENDIF
	ENDIF

	// check for new high score
	IF new_high_score = 0
		IF bball_challenge_score > bball_challenge_high_score
			bball_challenge_high_score = bball_challenge_score
			//PRINT_NOW BB_15 5000 1 // new high score!!
			new_high_score++
		ENDIF
	ELSE
		IF bball_challenge_score > bball_challenge_high_score
			bball_challenge_high_score = bball_challenge_score
		ENDIF
	ENDIF

	// wait for time to expire
	IF bball_challenge_timer <= 0
		IF m_goals < 3
			PRINT_NOW BB_17	5000 1 // time has expired 
			TIMERA = 0
		ENDIF
		m_goals = 3
	ENDIF


RETURN


LVAR_FLOAT new_marker_distance
bbchal_get_random_marker:
	get_random_marker_start:

	IF DOES_OBJECT_EXIST bbhoop

		GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 2 x2 y2 z2
		GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 3 x3 y3 z3
		GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x y z
		
		 

		// bring court in a bit
		SWITCH hoop_direction
			CASE 1
				x2 += -1.5
				y2 += 1.5
				x3 += 1.5
				y3 += -1.5	 		
			BREAK
			CASE 2
				x2 += -1.5
				y2 += -1.5
				x3 += 1.5
				y3 += 1.5
			BREAK
			CASE 3
				x2 += 1.5
				y2 += -1.5
				x3 += -1.5
				y3 += 1.5
			BREAK
			CASE 4
				x2 += 1.5
				y2 += 1.5
				x3 += -1.5
				y3 += -1.5
			BREAK
		ENDSWITCH
	

		z2 += 5.0
		GET_GROUND_Z_FOR_3D_COORD x2 y2 z2 location_z

		// get random point
		IF x2 < x3
			GENERATE_RANDOM_FLOAT_IN_RANGE x2 x3 location_x
		ELSE
			GENERATE_RANDOM_FLOAT_IN_RANGE x3 x2 location_x
		ENDIF
		IF y2 < y3
			GENERATE_RANDOM_FLOAT_IN_RANGE y2 y3 location_y
		ELSE
			GENERATE_RANDOM_FLOAT_IN_RANGE y2 y3 location_y
		ENDIF

		vec_x = location_x - x
		vec_y = location_y - y

		GET_DISTANCE_BETWEEN_COORDS_2D x y location_x location_y temp_float

		vec_x /= temp_float
		vec_y /= temp_float

		vec_x *= new_marker_distance
		vec_y *= new_marker_distance

		
		location_x = x + vec_x
		location_y = y + vec_y

		// check location isn't outside court
		IF x2 < x3
			IF location_x < x2
				location_x = x2
			ENDIF
			IF location_x > x3
				location_x = x3
			ENDIF
		ELSE
			IF location_x > x2
				location_x = x2
			ENDIF
			IF location_x < x3
				location_x = x3
			ENDIF
		ENDIF 
		IF y2 < y3
			IF location_y < y2
				location_y = y2
			ENDIF
			IF location_y > y3
				location_y = y3
			ENDIF
		ELSE
			IF location_y > y2
				location_y = y2
			ENDIF
			IF location_y < y3
				location_y = y3
			ENDIF
		ENDIF 

	ENDIF

RETURN

bbchal_get_new_marker:

	GOSUB bbchal_get_random_marker

	IF DOES_OBJECT_EXIST bbhoop
		GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x y z
	ENDIF

	GET_DISTANCE_BETWEEN_COORDS_2D location_x location_y x y distance
	
	// store how much time this will be worth 1 sec per meter
	next_time_to_award =# distance
	next_time_to_award *= time_to_add_on

	temp_float =# time_to_add_on
	temp_float *= 0.1
	temp_int =# temp_float
	temp_int *= -1
	time_to_add_on += temp_int

	IF time_to_add_on < 500
		time_to_add_on = 500
	ENDIF

RETURN

bbchal_cleanup:
CLEAR_PRINTS

IF NOT new_high_score = 0
	//PRINT_BIG BB_15 5000 1 // new high score
	//PRINT_NOW BB_15 5000 1 // new high score!!
	//~y~NEW HIGH SCORE!!~n~~1~	 
	PRINT_WITH_NUMBER_BIG BB_15 bball_challenge_high_score 5000 1
	REGISTER_INT_STAT HIGHEST_SCORE_IN_BASKETBALL_CHALLENGE bball_challenge_high_score
	PLAY_MISSION_PASSED_TUNE 1
ENDIF

IF DOES_BLIP_EXIST location_blip
	REMOVE_BLIP location_blip
ENDIF
CLEAR_ONSCREEN_COUNTER bball_challenge_score 
CLEAR_ONSCREEN_COUNTER bball_challenge_high_score
CLEAR_ONSCREEN_TIMER bball_challenge_timer
bball_challenge_active = 0
TERMINATE_THIS_SCRIPT
RETURN

}

//	[BB_09]
//	Press the ~h~R3 ~w~button to start the challenge mode.
//	[BB_10]
//	Shoot hoops from the ~y~marker~w~ on the court.
//	[BB_11]
//	Shoot as many hoops as possible before the time expires.
//	[BB_12]
//	Extra time will be awarded for each shot made.
//	[BB_13]
//	~s~Challenge mode started!
//	[BB_14]
//	High Score
//	[BB_15]
//	~s~NEW HIGH SCORE!!
//	[BB_16]
//	~s~Shoot from the ~y~marker~s~. 
//	[BB_17]
//	~r~Time Expired!
//	[BB_18]
//	Score
//	[BB_19]
//	Time
