MISSION_START

// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
//
//			MISSION MISSNAM : BASKETBALL
//				     AUTHOR : NEIL
//		       DESICRIPTION : Shoot hoops.
//
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************


// FLOATS

{
bball_script:
SCRIPT_NAME BBALL

// parameters passed in
LVAR_INT bbhoop

// objects
LVAR_INT m_ball 
VAR_INT char_obj

// floats
LVAR_FLOAT x2 y2 z2
LVAR_FLOAT x3 y3 z3
LVAR_FLOAT temp_float temp_float2
LVAR_FLOAT camera_x camera_y camera_z
LVAR_FLOAT look_x look_y look_z

// flags
LVAR_INT m_stage
LVAR_INT p_status p_status_old  // need to be global
LVAR_INT col_loaded_for_ball
LVAR_INT tri_pressed
LVAR_INT cross_pressed
LVAR_INT char_is_attached

LVAR_INT this_hoop_model

//LVAR_INT shoot_time
VAR_FLOAT release_time
VAR_FLOAT anim_release_time

// temps
LVAR_INT temp_int temp_int2
LVAR_INT temp_seq

VAR_INT bball_frame_count

// camera type
VAR_INT bball_camera_type

VAR_INT bball_is_active

VAR_INT bball_stored_weapon
VAR_INT bball_player_has_brassknuckle

//LVAR_INT this_script_id

//GENERATE_RANDOM_INT_IN_RANGE 1 320000 this_script_id

IF NOT bball_active = 0
	TERMINATE_THIS_SCRIPT
ENDIF
		   

// set flags
m_stage				= 0
p_status			= 0
p_status_old		= 0
tri_pressed			= 0
char_is_attached 	= 0
bball_camera_type 	= 0
col_loaded_for_ball = 0
cross_pressed		= 0

// set any global flags

bball_help = 0

// fake creates
IF m_stage = -1
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 bbhoop
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 m_ball
	CREATE_OBJECT WOODENBOX 0.0 0.0 0.0 char_obj
ENDIF


// constants
CONST_INT 	WALK_WITH_BALL			1
CONST_INT 	RUN_WITH_BALL			2
CONST_INT 	STOP_WITH_BALL			3
CONST_INT 	SHOOT					4
CONST_INT	CANCEL_SHOT				5
CONST_INT	FINISH_SHOT				6
CONST_INT	DEFENSIVE_STANCE		7
CONST_INT	BLOCK_JUMP				8
CONST_INT	STEAL					9
CONST_INT	PICKUP_BALL				10
CONST_INT	IDLE_BOUNCE				11
CONST_INT	DO_NOTHING				12
CONST_INT   FINISH_CANCEL_SHOT		13
CONST_INT 	ROLL_ALONG_BACK			14
CONST_INT   SPIN_ON_FINGER			15
CONST_INT   DUNK_START				16
CONST_INT	DUNK_FINISH				17


bball_loop:
		
		// make sure hoop model has loaded
		temp_int = 0

		IF NOT bball_unlocked = 0 // make sure basket ball is unlocked
			IF DOES_OBJECT_EXIST bbhoop
				IF IS_PLAYER_PLAYING player1 
				AND flag_player_on_mission = 0
					IF DOES_OBJECT_HAVE_THIS_MODEL	bbhoop BSKBALL_LAX
						IF HAS_MODEL_LOADED BSKBALL_LAX
							this_hoop_model = BSKBALL_LAX
							temp_int = 1
						ENDIF 
					ELSE
						IF DOES_OBJECT_HAVE_THIS_MODEL	bbhoop BSKBALLHUB_LAX01
							IF HAS_MODEL_LOADED BSKBALLHUB_LAX01
								this_hoop_model = BSKBALLHUB_LAX01 
								temp_int = 1
							ENDIF 
						ELSE
							IF DOES_OBJECT_HAVE_THIS_MODEL	bbhoop VGSXREFBBALLNET
								IF HAS_MODEL_LOADED VGSXREFBBALLNET
									this_hoop_model = VGSXREFBBALLNET 
									temp_int = 1
								ENDIF 
							ELSE
								IF DOES_OBJECT_HAVE_THIS_MODEL	bbhoop VGSXREFBBALLNET2
									IF HAS_MODEL_LOADED VGSXREFBBALLNET2
										this_hoop_model = VGSXREFBBALLNET2 
										temp_int = 1
									ENDIF 
								ENDIF
							ENDIF
						ENDIF
					ENDIF
				ENDIF	
			ENDIF
		ENDIF

		IF temp_int = 1

			// WAIT TO TRIGGER GAME
			SWITCH m_stage

				// when player gets close create m_ball
				CASE 0
					IF bball_active = 0
						IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer bbhoop 50.0 50.0 FALSE								
							IF NOT DOES_OBJECT_EXIST m_ball
								bball_active = 1
								IF HAS_MODEL_LOADED this_hoop_model
									GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 1 x y z
								ENDIF
								
								z += 1.0
								CREATE_OBJECT_NO_OFFSET BBALL_COL x y z m_ball
								col_loaded_for_ball = 1
								SET_OBJECT_DYNAMIC m_ball TRUE
								SET_OBJECT_COLLISION m_ball TRUE
								SET_OBJECT_VELOCITY m_ball 0.0 0.0 -0.1	
								m_stage++
							
							ENDIF
						ENDIF
					ELSE
						GOSUB terminate_bball
					ENDIF
				BREAK


				// wait for player to get near m_ball
				CASE 1
					IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer bbhoop 50.0 50.0 FALSE
						IF DOES_OBJECT_EXIST m_ball
							IF LOCATE_CHAR_ON_FOOT_OBJECT_3D scplayer m_ball 1.0 1.0 2.0 FALSE
								// check m_ball is on court
								IF HAS_MODEL_LOADED this_hoop_model
									GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 2 x2 y2 z2
									GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 3 x3 y3 z3
 									IF IS_OBJECT_IN_AREA_3D m_ball x2 y2 z2 x3 y3 z3 FALSE
										IF LOCATE_CHAR_ON_FOOT_OBJECT_3D scplayer m_ball 0.7 0.7 2.0 FALSE
											GET_OBJECT_COORDINATES m_ball x y z
											x2 = x - 0.71
											y2 = y - 0.71
											z2 = z - 0.71
											x3 = x + 0.71
											y3 = y + 0.71
											z3 = z + 2.0
											IF NOT IS_AREA_OCCUPIED x2 y2 z2 x3 y3 z3 FALSE TRUE FALSE FALSE FALSE
												// check the player is safe to use
												SET_PLAYER_ENTER_CAR_BUTTON player1 FALSE
												PRINT_HELP_FOREVER BB_01 // press triangle to start game
												m_stage++
											ENDIF
										ENDIF
									ELSE
										GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 1 x y z
										z += 1.0
										SET_OBJECT_COORDINATES m_ball x y z 
									ENDIF
								ENDIF
							ENDIF
						ENDIF
					ELSE
						MARK_OBJECT_AS_NO_LONGER_NEEDED m_ball
						m_stage = 0
						bball_active = 0
					ENDIF
				BREAK

				// wait	for player to pickup m_ball
				CASE 2
					IF LOCATE_CHAR_ANY_MEANS_OBJECT_2D scplayer bbhoop 50.0 50.0 FALSE
						IF DOES_OBJECT_EXIST m_ball
							// check m_ball is on court
							IF HAS_MODEL_LOADED this_hoop_model
								GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 2 x2 y2 z2
								GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 3 x3 y3 z3
								IF IS_OBJECT_IN_AREA_3D m_ball x2 y2 z2 x3 y3 z3 FALSE
									IF LOCATE_CHAR_ON_FOOT_OBJECT_3D scplayer m_ball 0.7 0.7 2.0 FALSE
										GET_OBJECT_COORDINATES m_ball x y z
										x2 = x - 0.71
										y2 = y - 0.71
										z2 = z - 0.71
										x3 = x + 0.71
										y3 = y + 0.71
										z3 = z + 2.0
										IF NOT IS_AREA_OCCUPIED x2 y2 z2 x3 y3 z3 FALSE TRUE FALSE FALSE FALSE 
											
											//WRITE_DEBUG_WITH_INT p_status p_status
											
											IF tri_pressed = 0
													
												IF IS_BUTTON_PRESSED PAD1 TRIANGLE
													tri_pressed = 1
													CLEAR_HELP 

													GOSUB bball_init_minigame
													m_stage ++
												ENDIF
											ELSE
												IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
													tri_pressed = 0
												ENDIF
											ENDIF

										ELSE
											SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
											CLEAR_HELP
											m_stage += -1	
										ENDIF
									ELSE
										SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
										CLEAR_HELP
										m_stage += -1 
									ENDIF 
								ELSE
									SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
									CLEAR_HELP
									m_stage += -1
								ENDIF
							ENDIF
						ENDIF
					ELSE	
						SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
						MARK_OBJECT_AS_NO_LONGER_NEEDED m_ball
						bball_active = 0
						m_stage = 0
					ENDIF
				BREAK


				// call basketball minigame
				CASE 3

					// check if player goes off court
					IF HAS_MODEL_LOADED this_hoop_model
						GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 2 x2 y2 z2
						GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 3 x3 y3 z3

						IF NOT IS_CHAR_IN_AREA_3D scplayer x2 y2 z2 x3 y3 z3 FALSE
							IF TIMERB < 6000
							
								IF TIMERB > 0
								AND TIMERB < 1000
									PRINT_HELP_FOREVER 	BB_03
									TIMERB = 1000
								ENDIF
								
								GOSUB bball_minigame

							ELSE
								GOSUB bball_cleanup_minigame	
								m_stage = 1
							ENDIF
						ELSE
							IF TIMERB > 1000
								CLEAR_HELP
							ENDIF

							GOSUB bball_minigame
							TIMERB = 0
						ENDIF

						// if player gets attacked quit out
						IF IS_PLAYER_PLAYING player1
							IF HAS_CHAR_BEEN_DAMAGED_BY_WEAPON scplayer WEAPONTYPE_ANYWEAPON
								GOSUB bball_cleanup_minigame
								m_stage = 1
							ENDIF
						ENDIF



						// quit out of minigame
						IF NOT p_status = PICKUP_BALL
						AND NOT p_status = DUNK_START	
						AND NOT p_status = DUNK_FINISH
						AND NOT p_status = SHOOT	
						
							IF tri_pressed = 0
								
								IF IS_BUTTON_PRESSED PAD1 TRIANGLE
									tri_pressed = 1
									GOSUB bball_cleanup_minigame
									m_stage = 1
								ENDIF
							ELSE
								
								IF NOT IS_BUTTON_PRESSED PAD1 TRIANGLE
									tri_pressed = 0
								ENDIF
							ENDIF

						ENDIF
					ENDIF
				BREAK
			ENDSWITCH

		ELSE
			bball_active = 0
			GOSUB terminate_bball 
		ENDIF
	 

WAIT 0
GOTO bball_loop	

terminate_bball:

	MARK_OBJECT_AS_NO_LONGER_NEEDED m_ball
	IF IS_PLAYER_PLAYING player1
		IF flag_player_on_mission = 0
			SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE
		ENDIF
	ENDIF
	TERMINATE_THIS_SCRIPT

RETURN


bball_edit_anim_time:

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_UP
		anim_release_time += 0.01
	ENDIF
	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_DOWN
		anim_release_time += -0.01
	ENDIF

RETURN


// ******************************************************************************************
//						   update ball colllision
// ******************************************************************************************
bball_update_ball_collision:

	// swap balls between one with collision and one without collision
	IF DOES_OBJECT_EXIST m_ball
		IF IS_OBJECT_ATTACHED m_ball
			IF col_loaded_for_ball = 1
				DETACH_OBJECT m_ball 0.0 0.0 0.0 FALSE
				GET_OBJECT_COORDINATES m_ball x y z
				GET_OBJECT_VELOCITY m_ball x2 y2 z2
				GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
				DELETE_OBJECT m_ball

				CREATE_OBJECT_NO_OFFSET BBALL_INGAME x y z m_ball
				ATTACH_OBJECT_TO_CHAR m_ball scplayer 0.0 0.5 -1.0 0.0 0.0 0.0
				SET_OBJECT_VELOCITY m_ball x2 y2 z2
				GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
				SET_OBJECT_COLLISION m_ball TRUE
				col_loaded_for_ball = 0
				//WRITE_DEBUG ball_attached
			ENDIF	
		ELSE
			IF col_loaded_for_ball = 0


				GET_OBJECT_COORDINATES m_ball x y z
				GET_OBJECT_VELOCITY m_ball x2 y2 z2
				GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
				DELETE_OBJECT m_ball
				CREATE_OBJECT_NO_OFFSET BBALL_COL	x y z m_ball
				SET_OBJECT_DYNAMIC m_ball TRUE
				SET_OBJECT_COLLISION m_ball TRUE
				SET_OBJECT_VELOCITY m_ball x2 y2 z2
				GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
				col_loaded_for_ball = 1
				
				//WRITE_DEBUG ball_detached
			ENDIF
		ENDIF
	ENDIF


RETURN

bball_update_ball_collision_cleanup:

	// swap balls between one with collision and one without collision
	IF DOES_OBJECT_EXIST m_ball
		IF IS_OBJECT_ATTACHED m_ball
			IF col_loaded_for_ball = 1
				DETACH_OBJECT m_ball 0.0 0.0 0.0 FALSE
				GET_OBJECT_COORDINATES m_ball x y z
				GET_OBJECT_VELOCITY m_ball x2 y2 z2
				GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
				DELETE_OBJECT m_ball

				CREATE_OBJECT_NO_OFFSET BBALL_INGAME x y z m_ball
				ATTACH_OBJECT_TO_CHAR m_ball scplayer 0.0 0.5 -1.0 0.0 0.0 0.0
				SET_OBJECT_VELOCITY m_ball x2 y2 z2
				GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
				SET_OBJECT_COLLISION m_ball TRUE
				col_loaded_for_ball = 0
				//WRITE_DEBUG ball_attached
			ENDIF	
		ELSE
			IF col_loaded_for_ball = 0


				//GET_OBJECT_COORDINATES m_ball x y z
				GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 0.5 0.0 x y z

				x2 = x - 0.1
				x3 = x + 0.1
				y2 = y - 0.1
				y3 = y + 0.1
				z2 = z - 0.5
				z3 = z + 1.0 

				IF NOT IS_AREA_OCCUPIED x2 y2 z2 x3 y3 z3 FALSE TRUE FALSE FALSE FALSE
					//WRITE_DEBUG quit1
					GET_OBJECT_VELOCITY m_ball x2 y2 z2
					GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
					DELETE_OBJECT m_ball
					CREATE_OBJECT_NO_OFFSET BBALL_COL	x y z m_ball
					SET_OBJECT_DYNAMIC m_ball TRUE
					SET_OBJECT_COLLISION m_ball TRUE
					SET_OBJECT_VELOCITY m_ball 0.0 0.0 -0.1
					GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
					col_loaded_for_ball = 1
				ELSE
					GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer 0.0 -2.0 -0.3 x y z
					x2 = x - 0.1
					x3 = x + 0.1
					y2 = y - 0.1
					y3 = y + 0.1
					z2 = z - 0.5
					z3 = z + 1.0  
					IF NOT IS_AREA_OCCUPIED x2 y2 z2 x3 y3 z3 FALSE TRUE FALSE FALSE FALSE
						//WRITE_DEBUG quit2
						GET_OBJECT_VELOCITY m_ball x2 y2 z2
						GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
						DELETE_OBJECT m_ball
						CREATE_OBJECT_NO_OFFSET BBALL_COL	x y z m_ball
						SET_OBJECT_DYNAMIC m_ball TRUE
						SET_OBJECT_COLLISION m_ball TRUE
						SET_OBJECT_VELOCITY m_ball 0.0 0.0 -0.1
						GET_OBJECT_ROTATION_VELOCITY m_ball x3 y3 z3
						col_loaded_for_ball = 1
					ELSE
						//WRITE_DEBUG quit3
						DELETE_OBJECT m_ball
					ENDIF
				ENDIF
				
				//WRITE_DEBUG ball_detached
			ENDIF
		ENDIF
	ENDIF

RETURN




// ******************************************************************************************
//						   init minigame
// ******************************************************************************************

bball_init_minigame:
	
	SET_MINIGAME_IN_PROGRESS TRUE
	DISPLAY_NON_MINIGAME_HELP_MESSAGES TRUE
	anim_release_time = 0.4

	SET_PLAYER_ENTER_CAR_BUTTON player1 FALSE

	VAR_FLOAT bball_court_z

	IF IS_PLAYER_PLAYING player1
		
		SET_PLAYER_CONTROL player1 OFF
		SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE
		CLEAR_CHAR_LAST_WEAPON_DAMAGE scplayer

		// weapon stuff ////////////////////////////
		// store current weapon
		GET_CURRENT_CHAR_WEAPON scplayer bball_stored_weapon
		// remove brassknuckle
		IF HAS_CHAR_GOT_WEAPON scplayer WEAPONTYPE_BRASSKNUCKLE
			REMOVE_WEAPON_FROM_CHAR scplayer WEAPONTYPE_BRASSKNUCKLE
			bball_player_has_brassknuckle = 1
			REQUEST_MODEL BRASSKNUCKLE
			WHILE NOT HAS_MODEL_LOADED BRASSKNUCKLE
				WAIT 0
			ENDWHILE
		ELSE
			bball_player_has_brassknuckle = 0
		ENDIF
		SET_CURRENT_CHAR_WEAPON scplayer WEAPONTYPE_UNARMED
		SET_PLAYER_CYCLE_WEAPON_BUTTON player1 FALSE
		/////////////////////////////////////////////

	ENDIF



		
	IF DOES_OBJECT_EXIST m_ball
		FREEZE_OBJECT_POSITION m_ball TRUE
	ENDIF

	REQUEST_ANIMATION BSKTBALL
	REQUEST_MODEL BBALL_INGAME
	REQUEST_MODEL BBALL_COL 
	LOAD_MISSION_AUDIO 4 SOUND_BANK_BASKETBALL
	WHILE NOT HAS_ANIMATION_LOADED BSKTBALL
	   OR NOT HAS_MODEL_LOADED BBALL_INGAME
	   OR NOT HAS_MODEL_LOADED BBALL_COL
	   OR NOT HAS_MISSION_AUDIO_LOADED 4 
		WAIT 0
	ENDWHILE 

	// set game camera
	GET_ACTIVE_CAMERA_COORDINATES camera_x camera_y camera_z
	GET_ACTIVE_CAMERA_POINT_AT look_x look_y look_z

	x = look_x - camera_x
	y = look_y - camera_y
	z = look_z - camera_z

	GET_DISTANCE_BETWEEN_COORDS_3D look_x look_y look_z camera_x camera_y camera_z distance

	x /= distance
	y /= distance
	z /= distance

	x *= 10.0
	y *= 10.0
	z *= 10.0

	look_x = camera_x + x
	look_y = camera_y + y
	look_z = camera_z + z


	TIMERA = 0
	bball_help = 0

	p_status = PICKUP_BALL
	bball_camera_type = 1


	// get court z
	IF IS_PLAYER_PLAYING player1
		GET_CHAR_COORDINATES scplayer x y z
		GET_GROUND_Z_FOR_3D_COORD x y z z
		bball_court_z = z
	ENDIF

	// get hoop direction
	VAR_INT hoop_direction 
	GET_OBJECT_HEADING bbhoop temp_float

	IF temp_float < 10.0 
	OR temp_float > 350.0
		hoop_direction = 1
	ENDIF 
	IF temp_float > 80.0
	AND temp_float < 100.0
		hoop_direction = 2
	ENDIF
	IF temp_float > 170.0
	AND temp_float < 190.0
		hoop_direction = 3
	ENDIF
	IF temp_float > 260.0
	AND temp_float < 280.0
		hoop_direction = 4
	ENDIF
	//VIEW_INTEGER_VARIABLE hoop_direction hoop_direction

	SET_PED_DENSITY_MULTIPLIER 0.2
	SET_CAR_DENSITY_MULTIPLIER 0.2

	bball_is_active = 1

RETURN


// ******************************************************************************************
//						  minigame
// ******************************************************************************************

bball_minigame:

	// frame counter
	bball_frame_count++
	IF bball_frame_count >= 30
		bball_frame_count = 0
	ENDIF
	
	GOSUB bb_update_camera_object
	GOSUB update_player_control
	GOSUB update_bball_tasks
	GOSUB update_object_anims

	IF IS_PS2_KEYBOARD_KEY_JUST_PRESSED PS2_KEY_C
		GOSUB bball_find_camera_position
	ENDIF
	
	// help 
	IF bball_help < 3
		IF bball_help = 0
			IF bball_challenge_active = 0
				PRINT_HELP BB_02  
			ENDIF
			bball_help++
			TIMERA = 0
		ENDIF

		IF bball_help = 1
		AND TIMERA > 10000
			IF bball_challenge_active = 0
				PRINT_HELP BB_07  
			ENDIF
			TIMERA = 0
			bball_help++
		ENDIF

		IF bball_help = 2
		AND TIMERA > 5000
			IF DOES_OBJECT_HAVE_THIS_MODEL	bbhoop BSKBALL_LAX
			OR DOES_OBJECT_HAVE_THIS_MODEL	bbhoop VGSXREFBBALLNET
				IF bball_challenge_active = 0
					PRINT_HELP BB_09 // Press the ~h~R3 ~w~button to start the challenge mode.
				ENDIF
			ENDIF
			TIMERA = 12000
			bball_help++
		ENDIF
	ENDIF

	IF DOES_OBJECT_HAVE_THIS_MODEL	bbhoop BSKBALL_LAX
	OR DOES_OBJECT_HAVE_THIS_MODEL	bbhoop VGSXREFBBALLNET
		IF bball_challenge_active = 0
			IF bball_r3_is_pressed = 0
				
				IF IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					bball_r3_is_pressed = 1
					START_NEW_SCRIPT bball_challenge_script bbhoop
				ENDIF
			ELSE
				IF NOT IS_BUTTON_PRESSED PAD1 RIGHTSHOCK
					bball_r3_is_pressed = 0
				ENDIF		
			ENDIF
		ENDIF
	ENDIF
	

	// check ball doesn't fall below the ground
	IF bball_frame_count = 0
		IF DOES_OBJECT_EXIST m_ball
			IF NOT IS_OBJECT_ATTACHED m_ball
				IF HAS_MODEL_LOADED this_hoop_model
					GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 1 x2 y2 z2
					GET_OBJECT_COORDINATES m_ball x y z
					temp_float = z2 - 0.7
					IF z < temp_float
						SET_OBJECT_COORDINATES m_ball x2 y2 z2
					ENDIF
				ENDIF
			ENDIF
		ENDIF
	ENDIF


//	GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x y z
//	DRAW_CORONA x y z 0.03 CORONATYPE_MOON FLARETYPE_NONE 0 255 0

RETURN

// ******************************************************************************************
//						  minigame - cleanup
// ******************************************************************************************
bball_cleanup_minigame:
	

	IF IS_PLAYER_PLAYING player1

		// weapon stuff ////////////////////////////
		// give smg weapon back to char
		
		// give back brassknuckle
		IF bball_player_has_brassknuckle = 1
			GIVE_WEAPON_TO_CHAR scplayer WEAPONTYPE_BRASSKNUCKLE 1
			bball_player_has_brassknuckle = 0
			MARK_MODEL_AS_NO_LONGER_NEEDED BRASSKNUCKLE
		ENDIF

		// give back current weapon
		SET_CURRENT_CHAR_WEAPON scplayer bball_stored_weapon

		////////////////////////////////////////////

		SET_PLAYER_CYCLE_WEAPON_BUTTON player1 TRUE
		CLEAR_CHAR_TASKS scplayer
		SET_PLAYER_CONTROL player1 ON
		SET_PLAYER_ENTER_CAR_BUTTON player1 TRUE

		IF DOES_OBJECT_EXIST m_ball
			IF IS_OBJECT_ATTACHED m_ball
				DETACH_OBJECT m_ball 0.0 0.0 0.0 TRUE		
				GOSUB bball_update_ball_collision_cleanup
			ENDIF
		ENDIF

		// set ball back to court position
		IF DOES_OBJECT_EXIST m_ball
			IF HAS_MODEL_LOADED this_hoop_model
				GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 1 x y z
				GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 2 x2 y2 z2
				GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 3 x3 y3 z3
			ENDIF
			IF NOT IS_OBJECT_IN_AREA_3D m_ball x2 y2 z2 x3 y3 z3 FALSE
				z += 1.0
				SET_OBJECT_COORDINATES m_ball x y z
			ENDIF
		ENDIF

		IF DOES_OBJECT_EXIST char_obj
			IF char_is_attached = 1
				DETACH_CHAR_FROM_CAR scplayer
				char_is_attached = 0
			ENDIF
			DELETE_OBJECT char_obj
		ENDIF

		RESTORE_CAMERA
		bball_camera_type = 0
		REMOVE_ANIMATION BSKTBALL
		MARK_MODEL_AS_NO_LONGER_NEEDED BBALL_INGAME
		MARK_MODEL_AS_NO_LONGER_NEEDED BBALL_COL 
		CLEAR_MISSION_AUDIO 4

		CLEAR_HELP

		//m_stage = 0
		//bball_active = 0

		bball_is_active = 0

		SET_PED_DENSITY_MULTIPLIER 1.0
		SET_CAR_DENSITY_MULTIPLIER 1.0
		
		SET_MINIGAME_IN_PROGRESS FALSE
		//terminate_script = 1
	ENDIF	
RETURN


// ******************************************************************************************
//						   handles all the basket m_ball ped tasks
// ******************************************************************************************
update_bball_tasks:								
update_bball_tasks_start:

	IF IS_PLAYER_PLAYING player1 
	AND HAS_MODEL_LOADED this_hoop_model
		
		SWITCH p_status

			// offense ----------------------------------------------------------------------------
			CASE ROLL_ALONG_BACK
				VAR_INT roll_along_back_flag
				IF NOT p_status_old = ROLL_ALONG_BACK
					temp_int = 0
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLELOOP
						GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_IDLELOOP temp_float
						IF temp_float > 0.9
							temp_int = 1
							roll_along_back_flag = 0
						ENDIF
					ENDIF
						
					IF temp_int = 1
						//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						OPEN_SEQUENCE_TASK temp_seq
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLE BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK scplayer temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ELSE
						GOTO update_bball_tasks_end
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
					IF temp_int = FINISHED_TASK
						p_status = IDLE_BOUNCE
						GOTO update_bball_tasks_start
					ELSE
						IF roll_along_back_flag = 0
							IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLE
								roll_along_back_flag++
							ENDIF
						ELSE
							IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLELOOP
								p_status = IDLE_BOUNCE
								roll_along_back_flag = 0
								GOTO update_bball_tasks_start
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE SPIN_ON_FINGER
				VAR_INT spin_on_finger_flag
				IF NOT p_status_old = SPIN_ON_FINGER	
					
					temp_int = 0
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLELOOP
						GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_IDLELOOP temp_float
						IF temp_float > 0.9
							spin_on_finger_flag = 0
							temp_int = 1
						ENDIF
					ENDIF

					IF temp_int = 1
						//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						OPEN_SEQUENCE_TASK temp_seq
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLE2 BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK scplayer temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
					ELSE
						GOTO update_bball_tasks_end
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
					IF temp_int = FINISHED_TASK
						p_status = IDLE_BOUNCE
						GOTO update_bball_tasks_start
					ELSE
						IF spin_on_finger_flag = 0
							IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLE2
								spin_on_finger_flag++
							ENDIF
						ELSE
							IF IS_CHAR_PLAYING_ANIM scplayer  BBALL_IDLELOOP
								p_status = IDLE_BOUNCE
								spin_on_finger_flag = 0
								GOTO update_bball_tasks_start
							ENDIF							
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE WALK_WITH_BALL
				// check last state
				IF p_status_old = IDLE_BOUNCE
				OR p_status_old = RUN_WITH_BALL
				OR p_status_old = STOP_WITH_BALL
				OR p_status_old = PICKUP_BALL
					//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					IF NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_pickup
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_L
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_R
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_WALKSTOP_L
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_WALKSTOP_R

						VAR_INT walk_with_ball_flag
						VAR_FLOAT walk_with_ball_start_position
						walk_with_ball_flag = 0

						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_RUN
							GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_RUN walk_with_ball_start_position
						ENDIF

						OPEN_SEQUENCE_TASK temp_seq
							IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLELOOP 
								TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_WALK_START BSKTBALL 8.0 FALSE TRUE TRUE TRUE -1
								TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_WALK BSKTBALL 10000.0 TRUE TRUE TRUE FALSE -1
								walk_with_ball_flag = 1
							ELSE
								TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_WALK BSKTBALL 8.0 TRUE TRUE TRUE FALSE -1
							ENDIF
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK scplayer temp_seq
						CLEAR_SEQUENCE_TASK temp_seq

					ELSE
						GOTO update_bball_tasks_end
					ENDIF
				ELSE
					IF walk_with_ball_flag = 0
						IF IS_CHAR_PLAYING_ANIM	scplayer BBALL_WALK
							GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALK temp_float
							SET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALK walk_with_ball_start_position
							walk_with_ball_flag++										 
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE RUN_WITH_BALL
				// check last state
				IF p_status_old = IDLE_BOUNCE
				OR p_status_old = WALK_WITH_BALL
				OR p_status_old = STOP_WITH_BALL
				OR p_status_old = PICKUP_BALL

					IF p_status_old = IDLE_BOUNCE
						p_status = WALK_WITH_BALL
						GOTO update_bball_tasks_start
					ENDIF


					IF NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_pickup
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_L
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_R
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_WALKSTOP_L
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_WALKSTOP_R 
						
						VAR_INT run_with_ball_flag
						VAR_FLOAT run_with_ball_start_position
						run_with_ball_flag = 0
						
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALK
							GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALK run_with_ball_start_position
						ENDIF

						//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
						OPEN_SEQUENCE_TASK temp_seq
							//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_WALK_START BSKTBALL 8.0 FALSE TRUE TRUE TRUE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_RUN BSKTBALL 8.0 TRUE TRUE TRUE FALSE -1									
						CLOSE_SEQUENCE_TASK temp_seq
						PERFORM_SEQUENCE_TASK scplayer temp_seq
						CLEAR_SEQUENCE_TASK temp_seq

					ELSE
						GOTO update_bball_tasks_end
					ENDIF

				ELSE
					IF run_with_ball_flag = 0
						IF IS_CHAR_PLAYING_ANIM	scplayer BBALL_RUN
							SET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_RUN run_with_ball_start_position
							run_with_ball_flag++
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE STOP_WITH_BALL
				// check last state
				IF NOT p_status_old = STOP_WITH_BALL
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_RUN
						GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_RUN temp_float
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_RUN
							OPEN_SEQUENCE_TASK temp_seq
									IF temp_float < 0.5
										//WRITE_DEBUG_WITH_FLOAT STOP_ANIM_THING temp_float
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_SKIDSTOP_R BSKTBALL 6.0 FALSE TRUE TRUE TRUE -1
									ELSE
										//WRITE_DEBUG_WITH_FLOAT STOP_ANIM_THING2 temp_float
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_SKIDSTOP_L BSKTBALL 6.0 FALSE TRUE TRUE TRUE -1
									ENDIF
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK scplayer temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
						ENDIF
					ENDIF
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALK
						GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALK temp_float
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALK
							OPEN_SEQUENCE_TASK temp_seq
									IF temp_float < 0.5
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_WALKSTOP_R BSKTBALL 6.0 FALSE TRUE TRUE TRUE -1
									ELSE
										TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_WALKSTOP_L BSKTBALL 6.0 FALSE TRUE TRUE TRUE -1
									ENDIF
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK scplayer temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							//WRITE_DEBUG walk_stop3
						ENDIF
					ENDIF
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALK_START
						//IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALK
							OPEN_SEQUENCE_TASK temp_seq
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_WALKSTOP_L BSKTBALL 6.0 FALSE TRUE TRUE TRUE -1
									TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
							CLOSE_SEQUENCE_TASK temp_seq
							PERFORM_SEQUENCE_TASK scplayer temp_seq
							CLEAR_SEQUENCE_TASK temp_seq
							//WRITE_DEBUG walk_stop1
						//ENDIF
					ENDIF
				ELSE
					GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
					IF temp_int = FINISHED_TASK
						p_status = IDLE_BOUNCE
						GOTO update_bball_tasks_end
						//GOTO update_bball_tasks_start
					ELSE
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLELOOP 
							p_status = IDLE_BOUNCE
							GOTO update_bball_tasks_end
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE IDLE_BOUNCE
				IF NOT p_status_old = IDLE_BOUNCE
					//WRITE_DEBUG GOT_HERE
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
				GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
				ENDIF
			BREAK

			CASE SHOOT
				// check last state
				IF NOT p_status_old = SHOOT
					//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					GET_CHAR_COORDINATES scplayer x y z
					GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x2 y2 z2
					x3 = x2 - x
					y3 = y2 - y
					GET_HEADING_FROM_VECTOR_2D x3 y3 heading
					p_desired_heading = heading
					OPEN_SEQUENCE_TASK temp_seq
						//TASK_ACHIEVE_HEADING -1 heading
						GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x y z
						TASK_LOOK_AT_COORD -1 x y z 2000
						IF p_status_old = RUN_WITH_BALL
							IF IS_CHAR_PLAYING_ANIM scplayer BBALL_RUN
								GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_RUN temp_float
							ENDIF
							IF temp_float < 0.5
								TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_SKIDSTOP_L BSKTBALL 6.0 FALSE TRUE TRUE TRUE -1
							ELSE
								TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_SKIDSTOP_R BSKTBALL 6.0 FALSE TRUE TRUE TRUE -1
							ENDIF
						ENDIF
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_Jump_Shot BSKTBALL 10000.0 FALSE FALSE FALSE FALSE -1
						//TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_Jump_End BSKTBALL 10000.0 FALSE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					//SET_CAMERA_BEHIND_PLAYER
				ELSE
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Jump_Shot
						GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Jump_Shot temp_float
						IF temp_float > anim_release_time
							p_status = FINISH_SHOT
							GOTO update_bball_tasks_end
						ENDIF	
					ENDIF	
				ENDIF
			BREAK


			CASE CANCEL_SHOT
				IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Jump_End
					//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_Jump_Cancel BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					p_status = FINISH_CANCEL_SHOT
					GOTO update_bball_tasks_end
				ENDIF
			BREAK

			CASE FINISH_CANCEL_SHOT
				GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK
					p_status = IDLE_BOUNCE
					GOTO update_bball_tasks_end
				ENDIF
			BREAK


			CASE FINISH_SHOT
				IF NOT p_status_old = FINISH_SHOT
					// throw m_ball
					IF IS_OBJECT_ATTACHED m_ball
						DETACH_OBJECT m_ball 0.0 0.0 0.0 FALSE
						GOSUB bball_update_ball_collision 
						GET_OFFSET_FROM_CHAR_IN_WORLD_COORDS scplayer  0.04 0.694 1.75  x y z  // 0.04 0.59 1.583
						SET_OBJECT_COORDINATES m_ball x y z
						//FREEZE_OBJECT_POSITION m_ball TRUE
						GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x2 y2 z2
						
						// save distance of shot
						GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
						bball_shot_dist =# temp_float
						z3 = z
						z3 =# bball_shot_dist
						temp_float2 = temp_float - z3
						temp_float2 *= 10.0
						bball_shot_dist_decimal =# temp_float2

						// get height of arch
						IF temp_float > 20.0
							temp_float = 20.0
						ENDIF
						temp_float /= 20.0
						temp_float *= 5.0
						//temp_float += 1.0
						
						// figure out if player is going to score or miss
						GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float2
						IF temp_float2 > 15.0
							temp_float2 = 15.0
						ENDIF
						release_time *= temp_float2
						GET_OBJECT_COORDINATES m_ball x y z

						// adjust accuracy for muscle stat
						GET_FLOAT_STAT BODY_MUSCLE temp_float2
						temp_float2 /= 1000.0
						temp_float2 += 1.0

						// size of window
						temp_float2 *= 0.15

						//WRITE_DEBUG_WITH_FLOAT release_time_plus_shit release_time
						//WRITE_DEBUG_WITH_FLOAT score_window temp_float2
						IF release_time < temp_float2 
							START_NEW_SCRIPT launch_projectile m_ball x y z	x2 y2 z2 temp_float	0 bbhoop  // score
						ELSE
							START_NEW_SCRIPT launch_projectile m_ball x y z	x2 y2 z2 temp_float	1 bbhoop  // miss
						ENDIF 

					ENDIF
				ELSE
					IF NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_Jump_Shot
						GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
						IF temp_int = FINISHED_TASK
							SET_PLAYER_CONTROL player1 ON
							p_status = DO_NOTHING
							GOTO update_bball_tasks_end
						ENDIF
					ENDIF
				ENDIF
			BREAK

			CASE DUNK_START
				// check last state
				IF NOT p_status_old = DUNK_START

					//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer

					GET_CHAR_COORDINATES scplayer x y z
					//GET_OBJECT_COORDINATES bbhoop x2 y2 z2
					GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x2 y2 z2
					x3 = x - x2
					y3 = y - y2
					GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
					IF temp_float > 0.01
						x3 /= temp_float
						y3 /= temp_float
					ENDIF
					x3 *= 0.01
					y3 *= 0.01
					x = x2 + x3
					y = y2 + y3
					z = z2 - 1.9

					bb_desired_x = x
					bb_desired_y = y
					bb_desired_z = z

					x3 *= -1.0
					y3 *= -1.0
					GET_HEADING_FROM_VECTOR_2D x3 y3 heading
					p_desired_heading = heading
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_Dnk_Lnch BSKTBALL 4.0    FALSE TRUE TRUE TRUE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_Dnk_Gli  BSKTBALL 1000.0 FALSE FALSE FALSE TRUE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_Dnk	   BSKTBALL 1000.0 FALSE FALSE FALSE TRUE -1
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_Dnk_Lnd  BSKTBALL 1000.0 FALSE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq
					//SET_CAMERA_BEHIND_PLAYER
					//WRITE_DEBUG DUNKSTART
				ELSE
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Gli
						IF NOT DOES_OBJECT_EXIST char_obj
							
							GET_CHAR_COORDINATES scplayer x y z
							GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x2 y2 z2

							VAR_FLOAT dunk_slide_distance
							GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 dunk_slide_distance
							//WRITE_DEBUG_WITH_FLOAT dunk_slide_distance dunk_slide_distance 
							dunc_glide_percent = 0.0

							GET_CHAR_HEADING scplayer heading
							CREATE_OBJECT_NO_OFFSET BBALL_INGAME x y z char_obj
							SET_OBJECT_HEADING char_obj heading
							SET_OBJECT_COLLISION char_obj FALSE
//							IF display_debug = 0
								SET_OBJECT_VISIBLE char_obj FALSE
//							ELSE
//								SET_OBJECT_VISIBLE char_obj TRUE
//							ENDIF
							FREEZE_OBJECT_POSITION char_obj TRUE
							ATTACH_CHAR_TO_OBJECT scplayer char_obj 0.0 0.0 0.0 FACING_FORWARD 360.0 WEAPONTYPE_UNARMED
							char_is_attached = 1
							//WRITE_DEBUG DUNK_GLIDE
				
						ENDIF
					ENDIF

					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk
						//WRITE_DEBUG DUNK_GLIDE_FINISHED
						p_status = DUNK_FINISH
						GOTO update_bball_tasks_end
					ENDIF
					

				ENDIF
			BREAK

			CASE DUNK_FINISH
				IF NOT p_status_old = DUNK_FINISH
					IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk
 						IF IS_OBJECT_ATTACHED m_ball
							IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_Dnk_O
								GET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_O temp_float
								IF temp_float > 0.9
								
									DETACH_OBJECT m_ball 0.0 0.0 0.0 FALSE
									GOSUB bball_update_ball_collision
									GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x y z	
									z += -0.5
									SET_OBJECT_COORDINATES m_ball x y z
									FREEZE_OBJECT_POSITION m_ball FALSE			
									SET_OBJECT_COLLISION m_ball TRUE
									SET_OBJECT_DYNAMIC m_ball TRUE
									SET_OBJECT_VELOCITY m_ball 0.0 0.0 -0.1
						
								ELSE
									p_status_old = 0
									GOTO update_bball_tasks_end
								ENDIF
							ELSE
								DETACH_OBJECT m_ball 0.0 0.0 0.0 FALSE
								GOSUB bball_update_ball_collision
								GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x y z	
								z += -0.5
								SET_OBJECT_COORDINATES m_ball x y z
								FREEZE_OBJECT_POSITION m_ball FALSE			
								SET_OBJECT_COLLISION m_ball TRUE
								SET_OBJECT_DYNAMIC m_ball TRUE
								SET_OBJECT_VELOCITY m_ball 0.0 0.0 -0.1
							ENDIF	
						ENDIF
					ELSE
						// update the desired xyz for the landing
						GET_GROUND_Z_FOR_3D_COORD bb_desired_x bb_desired_y bb_desired_z bb_desired_z
					ENDIF
				ELSE
					IF  NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Lnch
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Gli
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk
					AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Lnd 

						GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
						IF temp_int = FINISHED_TASK
							IF DOES_OBJECT_EXIST char_obj
								DETACH_CHAR_FROM_CAR scplayer
								DELETE_OBJECT char_obj
								char_is_attached = 0
							ENDIF
							SET_PLAYER_CONTROL player1 ON
							p_status = DO_NOTHING
							GOTO update_bball_tasks_end
						ENDIF

					ELSE
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Lnd
							// slide dude to finish position
							GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS bbhoop 0.0 1.0 0.0 x y z
							GET_GROUND_Z_FOR_3D_COORD x y z z
							z += 1.0

							bb_desired_x = x
							bb_desired_y = y
							bb_desired_z = z
						ENDIF
					ENDIF
				ENDIF
			BREAK

			

			// defense -----------------------------------------------------------------------------
			CASE DEFENSIVE_STANCE
				// check last state
				IF NOT p_status_old = DEFENSIVE_STANCE
					//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_def_loop BSKTBALL 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq	
				ENDIF
				// do task
				GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK	

					

					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_def_loop BSKTBALL 4.0 TRUE FALSE FALSE FALSE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq	
				ENDIF
			BREAK

			CASE BLOCK_JUMP
				// check last state
				IF NOT p_status_old = BLOCK_JUMP
					//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_def_jump_shot BSKTBALL 4.0 FALSE FALSE FALSE TRUE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq	
				ENDIF
				// do task
				GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
				IF temp_int = FINISHED_TASK	
					OPEN_SEQUENCE_TASK temp_seq
						TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_def_jump_shot BSKTBALL 4.0 FALSE FALSE FALSE TRUE -1
					CLOSE_SEQUENCE_TASK temp_seq
					PERFORM_SEQUENCE_TASK scplayer temp_seq
					CLEAR_SEQUENCE_TASK temp_seq	
				ENDIF 
			BREAK

			CASE STEAL
				
			BREAK

			// neutral
			CASE DO_NOTHING
				// nothing
			BREAK
			
			CASE PICKUP_BALL
				// check last state
				IF NOT p_status_old = PICKUP_BALL

					//CLEAR_CHAR_TASKS_IMMEDIATELY scplayer
					
					//GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
					//IF temp_int = FINISHED_TASK

						SET_PLAYER_CONTROL player1 OFF
						SET_CHAR_PROOFS scplayer FALSE FALSE FALSE FALSE FALSE

						// slide char to position
						GET_CHAR_COORDINATES scplayer x y z
						IF DOES_OBJECT_EXIST m_ball
							GET_OBJECT_COORDINATES m_ball x2 y2 z2
						ENDIF
						x3 = x - x2
						y3 = y - y2

						GET_DISTANCE_BETWEEN_COORDS_2D x y x2 y2 temp_float
						x3 /= temp_float
						y3 /= temp_float

						x3 *= 0.5
						y3 *= 0.5

						x = x2 + x3
						y = y2 + y3

						x3 = x2 - x
						y3 = y2 - y
						GET_HEADING_FROM_VECTOR_2D x3 y3 heading

						OPEN_SEQUENCE_TASK temp_seq	
 							TASK_CHAR_SLIDE_TO_COORD_AND_PLAY_ANIM -1 X Y Z Heading -1.0 BBALL_pickup BSKTBALL 4.0 FALSE FALSE FALSE TRUE -1
							TASK_PLAY_ANIM_NON_INTERRUPTABLE -1 BBALL_IDLELOOP BSKTBALL 10000.0 FALSE FALSE FALSE TRUE -1
						CLOSE_SEQUENCE_TASK temp_seq
						//SET_CHAR_COLLISION scplayer FALSE
						PERFORM_SEQUENCE_TASK scplayer temp_seq
						CLEAR_SEQUENCE_TASK temp_seq
				ELSE
					GET_SCRIPT_TASK_STATUS scplayer PERFORM_SEQUENCE_TASK temp_int
					IF temp_int = FINISHED_TASK
						//SET_CHAR_COLLISION scplayer TRUE
						p_status = IDLE_BOUNCE
						GOTO update_bball_tasks_end
					ELSE
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLELOOP
							//SET_CHAR_COLLISION scplayer TRUE
							p_status = IDLE_BOUNCE
					   		GOTO update_bball_tasks_end
						ENDIF
					ENDIF	
				ENDIF
			BREAK

			DEFAULT

		ENDSWITCH

	ENDIF

	p_status_old = p_status

	update_bball_tasks_end:

RETURN


// ******************************************************************************************
//						   handles all the object anims
// ******************************************************************************************
LVAR_INT bounce_sound_flag
update_object_anims:

	IF IS_PLAYER_PLAYING player1 
		
		// idle bounce
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLELOOP
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_IDLELOOP temp_float

			IF NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_RUN
				IF temp_float > 0.45
					IF NOT bounce_sound_flag = 1
						//WRITE_DEBUG bounce1
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_ball SOUND_BASKETBALL_BOUNCE
						bounce_sound_flag = 1	
					ENDIF
				ELSE
					IF bounce_sound_flag = 1
						bounce_sound_flag = 0	
					ENDIF
				ENDIF
			ENDIF

			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_IDLELOOP_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_IDLELOOP_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_IDLELOOP_O BSKTBALL 1000.0 TRUE FALSE
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_IDLELOOP_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_IDLELOOP_O temp_float
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		// roll across back
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLE
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_IDLE temp_float

			IF temp_float > 0.87671
				IF NOT bounce_sound_flag = 2
					//WRITE_DEBUG bounce2
					REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_ball SOUND_BASKETBALL_BOUNCE
					bounce_sound_flag = 2	
				ENDIF
			ELSE
				IF bounce_sound_flag = 2
					bounce_sound_flag = 0
				ENDIF	
			ENDIF

			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_IDLE_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_IDLE_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_IDLE_O BSKTBALL 1000.0 FALSE TRUE 
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_IDLE_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_IDLE_O temp_float
					ENDIF
				ENDIF	
			ENDIF
		ENDIF

		// spin on finger
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_IDLE2
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_IDLE2 temp_float

			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_IDLE2_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_IDLE2_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_IDLE2_O BSKTBALL 1000.0 FALSE TRUE
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_IDLE2_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_IDLE2_O temp_float
					ENDIF
				ENDIF
			ENDIF	
		ENDIF

		// start of shot
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Jump_Shot
			IF IS_OBJECT_ATTACHED m_ball
				GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Jump_Shot temp_float
				
				IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
					IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_Jump_Shot_O
						//SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Jump_Shot_O temp_float
					ELSE
						IF PLAY_OBJECT_ANIM m_ball BBALL_Jump_Shot_O BSKTBALL 1000.0 FALSE TRUE
							//SET_OBJECT_ANIM_SPEED m_ball BBALL_Jump_Shot_O 0.0
							//SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Jump_Shot_O temp_float
						ENDIF
					ENDIF
				ENDIF
			ENDIF	
		ENDIF

		// shot cancel
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Jump_Cancel
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Jump_Cancel temp_float
			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_Jump_Cancel_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Jump_Cancel_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_Jump_Cancel_O BSKTBALL 1000.0 FALSE TRUE
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_Jump_Cancel_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Jump_Cancel_O temp_float
					ENDIF
				ENDIF	
			ENDIF
		ENDIF

		// pickup m_ball
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_PICKUP
			IF NOT IS_OBJECT_ATTACHED m_ball
				ATTACH_OBJECT_TO_CHAR m_ball scplayer 0.0 0.5 -1.0 0.0 0.0 0.0
				GOSUB bball_update_ball_collision
			ELSE
				GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_PICKUP temp_float

				IF temp_float > 0.836
					IF NOT bounce_sound_flag = 3	
						//WRITE_DEBUG bounce3
						REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_ball SOUND_BASKETBALL_BOUNCE
						bounce_sound_flag = 3	
					ENDIF
				ELSE
					IF bounce_sound_flag = 3
						bounce_sound_flag = 0	 
					ENDIF
				ENDIF

				IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
					IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_PICKUP_O
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_PICKUP_O temp_float
					ELSE
						IF PLAY_OBJECT_ANIM m_ball BBALL_PICKUP_O BSKTBALL 1000.0 FALSE TRUE
							//SET_OBJECT_ANIM_SPEED m_ball BBALL_PICKUP_O 0.0
							SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_PICKUP_O temp_float
						ENDIF
					ENDIF
				ENDIF	
			ENDIF
		ENDIF

		// run
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_RUN
				GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_RUN temp_float

				//WRITE_DEBUG_WITH_FLOAT run_anim_time temp_float

				IF temp_float > 0.222
					IF temp_float < 0.74
						IF NOT bounce_sound_flag = 4
							//WRITE_DEBUG bounce4		
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_ball SOUND_BASKETBALL_BOUNCE
							bounce_sound_flag = 4	
						ENDIF
					ELSE
						IF NOT bounce_sound_flag = 5
							//WRITE_DEBUG bounce5
							REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_ball SOUND_BASKETBALL_BOUNCE
							bounce_sound_flag = 5	
						ENDIF
					ENDIF
				ELSE
					IF bounce_sound_flag = 4
					OR bounce_sound_flag = 5
						bounce_sound_flag = 0
					ENDIF	
				ENDIF

				IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
					IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_RUN_O
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_RUN_O temp_float
					ELSE
						IF PLAY_OBJECT_ANIM m_ball BBALL_RUN_O BSKTBALL 8.0 TRUE FALSE
							//SET_OBJECT_ANIM_SPEED m_ball BBALL_RUN_O 0.0
							SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_RUN_O temp_float
						ENDIF
					ENDIF
				ENDIF
			ENDIF	
		
		// skid stop left 
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_L
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_SKIDSTOP_L temp_float
			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_SKIDSTOP_L_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_SKIDSTOP_L_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_SKIDSTOP_L_O BSKTBALL 6.0 FALSE TRUE
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_SKIDSTOP_L_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_SKIDSTOP_L_O temp_float
					ENDIF
				ENDIF
			ENDIF	
		ENDIF

		// skid stop right 
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_R
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_SKIDSTOP_R temp_float
			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_SKIDSTOP_R_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_SKIDSTOP_R_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_SKIDSTOP_R_O BSKTBALL 6.0 FALSE TRUE
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_SKIDSTOP_R_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_SKIDSTOP_R_O temp_float
					ENDIF
				ENDIF
			ENDIF	
		ENDIF	
		
		// walk stop left 
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALKSTOP_L
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALKSTOP_L temp_float
			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_WALKSTOP_L_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALKSTOP_L_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_WALKSTOP_L_O BSKTBALL 6.0 FALSE TRUE
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALKSTOP_L_O temp_float
					ENDIF
				ENDIF
			ENDIF	
		ENDIF

		// walk stop right 
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALKSTOP_R
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALKSTOP_R temp_float
			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_WALKSTOP_R_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALKSTOP_R_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_WALKSTOP_R_O BSKTBALL 6.0 FALSE TRUE
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALKSTOP_R_O temp_float
					ENDIF
				ENDIF	
			ENDIF
		ENDIF	

		// walk 
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALK
				GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALK temp_float

				IF NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_RUN
					IF temp_float > 0.218
						IF temp_float < 0.718
							IF NOT bounce_sound_flag = 6
								//WRITE_DEBUG bounce6
								REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_ball SOUND_BASKETBALL_BOUNCE
								bounce_sound_flag = 6	
							ENDIF
						ELSE
							IF NOT bounce_sound_flag = 7
								//WRITE_DEBUG bounce7
								REPORT_MISSION_AUDIO_EVENT_AT_OBJECT m_ball SOUND_BASKETBALL_BOUNCE
								bounce_sound_flag = 7	
							ENDIF
						ENDIF
					ELSE
						IF bounce_sound_flag = 6
						OR bounce_sound_flag = 7
							bounce_sound_flag = 0
						ENDIF	
					ENDIF
				ENDIF

				IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
					IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_WALK_O
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALK_O temp_float
					ELSE
						IF PLAY_OBJECT_ANIM m_ball BBALL_WALK_O BSKTBALL 1000.0 TRUE FALSE
							//SET_OBJECT_ANIM_SPEED m_ball BBALL_WALK_O 0.0
							SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALK_O temp_float
						ENDIF
					ENDIF	
				ENDIF
		ENDIF

		// walk start
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_WALK_START
				GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_WALK_START temp_float
				
				IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
					IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_WALK_START_O
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALK_START_O temp_float
					ELSE
						IF PLAY_OBJECT_ANIM m_ball BBALL_WALK_START_O BSKTBALL 8.0 FALSE TRUE
							//SET_OBJECT_ANIM_SPEED m_ball BBALL_WALK_START_O 0.0
							SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_WALK_START_O temp_float
						ENDIF
					ENDIF	
				ENDIF
		ENDIF	
		
		// dunk launch		
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Lnch
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Dnk_Lnch temp_float
			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_Dnk_Lnch_O
					SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_Lnch_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_Dnk_Lnch_O BSKTBALL 4.0 FALSE TRUE
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_Dnk_Lnch_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_Lnch_O temp_float
					ENDIF
				ENDIF	
			ENDIF
		ENDIF

		// dunk glide		
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Gli
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Dnk_Gli temp_float
			
			IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
				IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_Dnk_Gli_O
				   	SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_Gli_O temp_float
				ELSE
					IF PLAY_OBJECT_ANIM m_ball BBALL_Dnk_Gli_O BSKTBALL 1000.0 FALSE TRUE
						//SET_OBJECT_ANIM_SPEED m_ball BBALL_Dnk_Gli_O 0.0
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_Gli_O temp_float
					ENDIF
				ENDIF
			ENDIF	
		ENDIF

		// dunk		
		IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk
			GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Dnk temp_float
			IF temp_float < 0.2
				
				IF DOES_OBJECT_HAVE_THIS_MODEL	m_ball BBALL_INGAME
					IF IS_OBJECT_ATTACHED m_ball
						IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_Dnk_O
							//SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_O temp_float
						ELSE
							IF PLAY_OBJECT_ANIM m_ball BBALL_Dnk_O BSKTBALL 1000.0 FALSE TRUE
								//SET_OBJECT_ANIM_SPEED m_ball BBALL_Dnk_O 0.0
								//SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_O temp_float
							ENDIF
						ENDIF
					ENDIF
				ENDIF	
			ENDIF
		ENDIF

	ENDIF

RETURN

// ******************************************************************************************
//						   update camera position 
// ******************************************************************************************

VAR_FLOAT bb_cam_vec_x bb_cam_vec_y	bb_cam_vec_z

VAR_FLOAT bb_new_camera_x bb_new_camera_y bb_new_camera_z
VAR_FLOAT bb_new_look_x bb_new_look_y bb_new_look_z
bb_update_camera_object:

	// make camera slide

	// get players perpendicular distance from hoop
	IF IS_PLAYER_PLAYING player1		
	AND HAS_MODEL_LOADED this_hoop_model

			// work out camera position 
			GET_CHAR_COORDINATES scplayer x y z	
		 	GET_OBJECT_COORDINATES bbhoop x2 y2 z2
			GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 3 x3 y3 z3
			GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 temp_float temp_float bb_new_camera_z 
			bb_new_camera_z += -1.0
			//VIEW_INTEGER_VARIABLE hoop_direction hoop_direction
			SWITCH hoop_direction
				CASE 1
					bb_new_camera_x = x2 + 0.2
					bb_new_camera_y = y + 5.0
					y3 += 5.0
					IF bb_new_camera_y > y3
						bb_new_camera_y = y3
					ENDIF
					temp_float = y2 + 5.0
					IF bb_new_camera_y < temp_float
						bb_new_camera_y = temp_float
					ENDIF

					// find the look at position
					temp_float = y3 - y2
					temp_float += -5.0

					temp_float2 = y3 - bb_new_camera_y 
					temp_float2 /= temp_float

					temp_float2 *= 0.29
					temp_float2 += 0.34
					
				BREAK
				CASE 2
					bb_new_camera_x = x - 5.0
					bb_new_camera_y = y2 + 0.2
					x3 += -5.0
					IF bb_new_camera_x < x3
						bb_new_camera_x = x3
					ENDIF
					temp_float = x2 - 5.0
					IF bb_new_camera_x > temp_float
						bb_new_camera_x = temp_float
					ENDIF

					// find the look at position
					temp_float = x2 - x3
					temp_float += -5.0

					temp_float2 = bb_new_camera_x - x3  
					temp_float2 /= temp_float

					temp_float2 *= 0.29
					temp_float2 += 0.34
					
				BREAK
				CASE 3

					bb_new_camera_x = x2 - 0.2
					bb_new_camera_y = y - 5.0
					y3 += -5.0
					IF bb_new_camera_y < y3
						bb_new_camera_y = y3
					ENDIF
					temp_float = y2 - 5.0
					IF bb_new_camera_y > temp_float
						bb_new_camera_y = temp_float
					ENDIF

					// find the look at position
					temp_float = y2 - y3
					temp_float += -5.0

					temp_float2 = bb_new_camera_y - y3  
					temp_float2 /= temp_float

					temp_float2 *= 0.29
					temp_float2 += 0.34
		
				BREAK
				CASE 4
					bb_new_camera_x = x + 5.0
					bb_new_camera_y = y2 - 0.2
					x3 += 5.0
					IF bb_new_camera_x > x3
						bb_new_camera_x = x3
					ENDIF
					temp_float = x2 + 5.0
					IF bb_new_camera_x < temp_float
						bb_new_camera_x = temp_float
					ENDIF

					// find the look at position
					temp_float = x3 - x2 
					temp_float += -5.0

					temp_float2 = x3 - bb_new_camera_x    
					temp_float2 /= temp_float

					temp_float2 *= 0.29
					temp_float2 += 0.34
					
				BREAK
			ENDSWITCH
			
			
			// work out camera look at
			bb_cam_vec_x = x2 - x
			bb_cam_vec_y = y2 - y
			bb_cam_vec_z = z2 - z
			bb_cam_vec_x *= temp_float2
			bb_cam_vec_y *= temp_float2
			bb_cam_vec_z *= temp_float2

			bb_new_look_x = x + bb_cam_vec_x
			bb_new_look_y = y + bb_cam_vec_y
			bb_new_look_z = z + bb_cam_vec_z

			
			//	check look at point is on court 
			GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 2 x2 y2 z2
			GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 3 x3 y3 z3
			IF x2 > x3
				IF bb_new_look_x > x2
					bb_new_look_x = x2
				ENDIF
				IF bb_new_look_x < x3
					bb_new_look_x = x3
				ENDIF
			ELSE
				IF bb_new_look_x > x3
					bb_new_look_x = x3
				ENDIF
				IF bb_new_look_x < x2
					bb_new_look_x = x2
				ENDIF
			ENDIF
			IF y2 > y3
				IF bb_new_look_y > y2
					bb_new_look_y = y2
				ENDIF
				IF bb_new_look_y < y3
					bb_new_look_y = y3
				ENDIF
			ELSE
				IF bb_new_look_y > y3
					bb_new_look_y = y3
				ENDIF
				IF bb_new_look_y < y2
					bb_new_look_y = y2
				ENDIF
			ENDIF

			bb_cam_vec_x = bb_new_camera_x - camera_x
			bb_cam_vec_y = bb_new_camera_y - camera_y
			bb_cam_vec_z = bb_new_camera_z - camera_z
			bb_cam_vec_x *= 0.25
			bb_cam_vec_y *= 0.25
			bb_cam_vec_z *= 0.25
			camera_x +=	bb_cam_vec_x
			camera_y +=	bb_cam_vec_y
			camera_z +=	bb_cam_vec_z

			bb_cam_vec_x = bb_new_look_x - look_x
			bb_cam_vec_y = bb_new_look_y - look_y
			bb_cam_vec_z = bb_new_look_z - look_z
			bb_cam_vec_x *= 0.1
			bb_cam_vec_y *= 0.1
			bb_cam_vec_z *= 0.1
			look_x +=	bb_cam_vec_x
			look_y +=	bb_cam_vec_y
			look_z +=	bb_cam_vec_z
			
			SET_FIXED_CAMERA_POSITION camera_x camera_y camera_z 0.0 0.0 0.0
			POINT_CAMERA_AT_POINT look_x look_y look_z JUMP_CUT
		
	ENDIF
			
RETURN


// ******************************************************************************************
//						   update player control 
// ******************************************************************************************
LVAR_FLOAT move_heading
VAR_FLOAT p_desired_heading
LVAR_FLOAT p_actual_heading
LVAR_FLOAT p_heading_diff
VAR_FLOAT bb_desired_x bb_desired_y bb_desired_z
update_player_control:
	
	IF IS_OBJECT_ATTACHED m_ball
	AND HAS_MODEL_LOADED this_hoop_model	

		// do nothing until player has finished picking up m_ball
		temp_int = 1
		IF p_status = PICKUP_BALL
		OR p_status = SHOOT
		OR p_status = FINISH_SHOT
		OR p_status = DUNK_START
		OR p_status = DUNK_FINISH
			temp_int = 0
		ENDIF
		IF p_status = ROLL_ALONG_BACK
		OR p_status = SPIN_ON_FINGER
			temp_int = 0
		ENDIF 

		//WRITE_DEBUG_WITH_INT temp_int temp_int

		IF temp_int = 1

			// dribble	
			IF IS_BUTTON_PRESSED PAD1 LEFTSTICKX
			OR IS_BUTTON_PRESSED PAD1 LEFTSTICKY
				GET_POSITION_OF_ANALOGUE_STICKS PAD1 LStickX LStickY RStickX RStickY
				x =# LStickX
				y =# LStickY
				x /= 128.0
				y /= -128.0
				// get vector magnitude 
				temp_float = x * x
				temp_float2 = y * y
				temp_float += temp_float2
				SQRT temp_float temp_float

				
				// get desired heading
				GET_HEADING_FROM_VECTOR_2D x y p_desired_heading
				
				IF bball_camera_type = 1
					x = look_x - camera_x
					y = look_y - camera_y
				ELSE
					GET_ACTIVE_CAMERA_COORDINATES x2 y2 z2
					GET_ACTIVE_CAMERA_POINT_AT x3 y3 z3
					x = x3 - x2
					y = y3 - y2
					z = z3 - z2  
				ENDIF
				GET_HEADING_FROM_VECTOR_2D x y temp_float2

				p_desired_heading += temp_float2

		 		IF temp_float > 0.3
					IF temp_float < 0.8
						// walk
						p_status = WALK_WITH_BALL
					ELSE
						// run
						p_status = RUN_WITH_BALL
					ENDIF
				ELSE
					IF p_status_old = RUN_WITH_BALL
					OR p_status_old = WALK_WITH_BALL
					OR p_status = RUN_WITH_BALL
					OR p_status = WALK_WITH_BALL
						p_status = STOP_WITH_BALL
						p_status_old = 0
					ENDIF
				ENDIF
			ELSE
				IF p_status_old = RUN_WITH_BALL
				OR p_status_old = WALK_WITH_BALL
				OR p_status = RUN_WITH_BALL
				OR p_status = WALK_WITH_BALL
					p_status = STOP_WITH_BALL
					p_status_old = 0
				ENDIF
			ENDIF		
			
			// shoot
			//WRITE_DEBUG_WITH_INT cross_pressed cross_pressed
			IF cross_pressed = 0
				
				IF IS_BUTTON_PRESSED PAD1 CROSS
				
					IF NOT p_status_old = FINISH_SHOT
						
						// check infront of board
						
						// get vector to hoop from player
						GET_LEVEL_DESIGN_COORDS_FOR_OBJECT bbhoop 0 x y z
						IF IS_PLAYER_PLAYING player1
							GET_CHAR_COORDINATES scplayer x2 y2 z2
						ENDIF
						x3 = x - x2
						y3 = y - y2

						// vector straight out of hoop
						GET_OBJECT_COORDINATES bbhoop x y z
						GET_OFFSET_FROM_OBJECT_IN_WORLD_COORDS bbhoop 0.0 -1.0 0.0 x2 y2 z2
						temp_float = x2 - x
						temp_float2 = y2 - y
						x2 = temp_float
						y2 = temp_float2

						// check angle between 2 vectors is below 90.
						GET_ANGLE_BETWEEN_2D_VECTORS x3 y3 x2 y2 temp_float

						IF temp_float < 90.0

							// check if in right position for a dunk
							temp_int = 0
							IF p_status_old = RUN_WITH_BALL
								// check the player isn't too fat
								GET_FLOAT_STAT FAT temp_float
								IF temp_float < 400.0
									GET_CHAR_COORDINATES scplayer x y z
									GET_OBJECT_COORDINATES bbhoop x2 y2 z2
									GET_DISTANCE_BETWEEN_COORDS_3D x y z x2 y2 z2 temp_float
									IF temp_float < 4.0
									AND temp_float > 1.0
										temp_int = 1
									ENDIF
								ENDIF
							ENDIF

							IF temp_int = 1
								p_status = DUNK_START
							ELSE
								p_status = SHOOT
							ENDIF

							//WRITE_DEBUG StartingShot
							cross_pressed = 1

						ENDIF
					ENDIF
				ELSE
					IF p_status_old = SHOOT
					AND NOT p_status = FINISH_SHOT
						//p_status = CANCEL_SHOT
						cross_pressed = 1
					ENDIF
				ENDIF
			ELSE

			ENDIF 
						
			// tricks 
			
			IF p_status = IDLE_BOUNCE
			AND p_status_old = IDLE_BOUNCE

				IF IS_BUTTON_PRESSED PAD1 SQUARE
					p_status = ROLL_ALONG_BACK
				ENDIF

			ENDIF

			IF p_status = IDLE_BOUNCE
			AND p_status_old = IDLE_BOUNCE

				IF IS_BUTTON_PRESSED PAD1 CIRCLE
					p_status = SPIN_ON_FINGER
				ENDIF

			ENDIF

		ELSE

			IF cross_pressed = 1
			
				IF p_status = SHOOT
				OR p_status = FINISH_SHOT
					
					// figure out if this shot is a hit or miss
					
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
					
						cross_pressed = 0
					
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Jump_Shot
							
							GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Jump_Shot temp_float	
							
							IF temp_float > anim_release_time
								release_time = temp_float - anim_release_time	
							ELSE
								release_time = anim_release_time - temp_float	
							ENDIF  
						ELSE
							release_time = 1.0
						ENDIF
					ELSE
						IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Jump_Shot
							GET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Jump_Shot temp_float	
							temp_float2 = anim_release_time + 0.1
							IF temp_float > temp_float2
								release_time = 1.0
								cross_pressed = 0
							ENDIF
						ELSE
							release_time = 1.0
						ENDIF
					ENDIF

				ELSE
					
					IF NOT IS_BUTTON_PRESSED PAD1 CROSS
						cross_pressed = 0
					ENDIF

				ENDIF
			ENDIF
		ENDIF

	ELSE
		//IF NOT p_status_old = FINISH_SHOT
		IF p_status_old = DO_NOTHING
			IF cross_pressed = 0
				IF DOES_OBJECT_EXIST m_ball
					GET_OBJECT_COORDINATES m_ball x y z
					IF LOCATE_CHAR_ON_FOOT_OBJECT_3D scplayer m_ball 0.7 0.7 2.0 FALSE
						p_status = PICKUP_BALL
					ENDIF
				ENDIF
			ELSE
				IF NOT IS_BUTTON_PRESSED PAD1 CROSS
					cross_pressed = 0
				ENDIF
			ENDIF
		ENDIF	
	ENDIF

	// update heading
	temp_int = 0
	IF p_status = WALK_WITH_BALL
	OR p_status = RUN_WITH_BALL
	OR p_status = BLOCK_JUMP 
	OR p_status = SHOOT
	OR p_status = FINISH_SHOT
		temp_int = 1
	ENDIF
	IF p_status = DUNK_START
	OR p_status = DUNK_FINISH
		temp_int = 1
	ENDIF
	IF temp_int = 1

		IF NOT IS_CHAR_DEAD scplayer
			GET_CHAR_HEADING scplayer p_actual_heading
		ENDIF

		WHILE p_desired_heading > 360.0
			p_desired_heading += -360.0
		ENDWHILE 
		WHILE p_desired_heading < -360.0
			p_desired_heading += 360.0
		ENDWHILE 

	
//		IF p_desired_heading > 180.0
//		AND p_actual_heading < 180.0
//			WRITE_DEBUG_WITH_FLOAT p_desired_heading p_desired_heading
//			WRITE_DEBUG_WITH_FLOAT p_actual_heading p_actual_heading
//		ENDIF

		p_heading_diff = p_desired_heading - p_actual_heading

//		IF p_desired_heading > 180.0
//		AND p_actual_heading < 180.0
//			WRITE_DEBUG_WITH_FLOAT p_heading_diff p_heading_diff
//		ENDIF

		IF p_heading_diff > 180.0
			p_heading_diff += -360.0
		ENDIF
		IF p_heading_diff < -180.0
			p_heading_diff += 360.0
		ENDIF

//		IF p_heading_diff > 90.0
//		OR p_heading_diff < -90.0
//			WRITE_DEBUG_WITH_FLOAT p_heading_diff p_heading_diff
//		ENDIF

		p_heading_diff /= 10.0
		p_actual_heading +=@ p_heading_diff

		IF NOT IS_CHAR_DEAD scplayer
			IF NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_L
			AND NOT IS_CHAR_PLAYING_ANIM scplayer BBALL_SKIDSTOP_R
				SET_CHAR_HEADING scplayer p_actual_heading
			ENDIF
		ENDIF									   
												   
	ENDIF										   
												   
	// update position							   
	IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Gli
	OR IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk 
	OR IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Lnd	  
									  
		//WRITE_DEBUG CHAR_IS_PLAYING_ANIM

		IF DOES_OBJECT_EXIST char_obj
			GET_OBJECT_COORDINATES char_obj x y z
			GET_DISTANCE_BETWEEN_COORDS_3D x y z bb_desired_x bb_desired_y bb_desired_z temp_float

			IF temp_float > 0.07
				x2 = bb_desired_x - x
				y2 = bb_desired_y - y
				z2 = bb_desired_z - z
				x2 /= temp_float
				y2 /= temp_float
				z2 /= temp_float

				IF temp_float > 0.065
					x2 *= 0.065
					y2 *= 0.065
					z2 *= 0.065

					x +=@ x2
					y +=@ y2
					z +=@ z2
					//WRITE_DEBUG UPDATING_CHAR_OBJ
					SET_OBJECT_COORDINATES char_obj x y z

					GET_DISTANCE_BETWEEN_COORDS_3D x y z bb_desired_x bb_desired_y bb_desired_z temp_float
			
					VAR_FLOAT dunc_glide_percent
					dunc_glide_percent = temp_float / dunk_slide_distance
					dunc_glide_percent += -1.0
					dunc_glide_percent *= -1.0

				ELSE
					SET_OBJECT_COORDINATES char_obj bb_desired_x bb_desired_y bb_desired_z
					dunc_glide_percent = 1.0
				ENDIF


			ELSE		
				SET_OBJECT_COORDINATES char_obj bb_desired_x bb_desired_y bb_desired_z
				dunc_glide_percent = 1.0
			ENDIF

			IF IS_CHAR_PLAYING_ANIM scplayer BBALL_Dnk_Gli
				IF dunc_glide_percent >= 0.0
				AND dunc_glide_percent <= 1.0
//					IF display_debug = 1
//						//WRITE_DEBUG_WITH_FLOAT dunc_glide_percent dunc_glide_percent
//					ENDIF
					SET_CHAR_ANIM_CURRENT_TIME scplayer BBALL_Dnk_Gli dunc_glide_percent
					IF IS_OBJECT_PLAYING_ANIM m_ball BBALL_Dnk_Gli_O
						SET_OBJECT_ANIM_CURRENT_TIME m_ball BBALL_Dnk_Gli_O dunc_glide_percent
					ENDIF
				ENDIF
			ENDIF

			//IF dunc_glide_percent <= 1.0
				//WRITE_DEBUG_WITH_FLOAT dunc_glide_percent dunc_glide_percent
			//ENDIF

		ENDIF

	ENDIF

		
RETURN



VAR_FLOAT intrp_dest_x intrp_dest_y intrp_dest_z
VAR_FLOAT intrp_start_x intrp_start_y intrp_start_z
VAR_FLOAT intrp_speed
interpolate_to_coord:

	x = intrp_dest_x - intrp_start_x
	y = intrp_dest_y - intrp_start_y
	z = intrp_dest_z - intrp_start_z

//GET_DISTANCE_BETWEEN_COORDS_3D intrp_start_x intrp_start_y intrp_start_z intrp_dest_x intrp_dest_y intrp_dest_z temp_float

	x *= intrp_speed
	y *= intrp_speed
	z *= intrp_speed

	intrp_start_x += x
	intrp_start_y += y
	intrp_start_z += z
	
RETURN

VAR_FLOAT bb_vec_x bb_vec_y bb_vec_z
VAR_FLOAT bb_vec2_x bb_vec2_y 
//bb_vec2_z
VAR_FLOAT bb_temp_float3
bball_find_camera_position:

	// for camera position
	GET_DEBUG_CAMERA_COORDINATES x y z
	IF DOES_OBJECT_EXIST bbhoop
		GET_OBJECT_COORDINATES bbhoop x2 y2 z2
		GET_OBJECT_HEADING bbhoop heading
	ENDIF
	heading *= -1.0
	COS heading temp_float
	SIN heading temp_float2
	// get coords relative to car 
	bb_vec_x = x - x2
	bb_vec_y = y - y2
	bb_vec_z = z - z2
	// work out new bb_vec_x
	bb_vec2_x = bb_vec_x * temp_float
	bb_temp_float3 = bb_vec_y * temp_float2
	bb_vec2_x -= bb_temp_float3
	// work out new bb_vec_y
	bb_vec2_y = bb_vec_x * temp_float2
	bb_temp_float3 = bb_vec_y * temp_float
	bb_vec2_y += bb_temp_float3
	// save
	SAVE_NEWLINE_TO_DEBUG_FILE 
	SAVE_STRING_TO_DEBUG_FILE "FIXED_CAMERA_POSITION - OFFSET FROM bbhoop = "
	SAVE_FLOAT_TO_DEBUG_FILE bb_vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE bb_vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE bb_vec_z

	// for camera point at
	GET_DEBUG_CAMERA_POINT_AT x y z
	// get coords relative to car 
	bb_vec_x = x - x2
	bb_vec_y = y - y2
	bb_vec_z = z - z2
	// work out new bb_vec_x
	bb_vec2_x = bb_vec_x * temp_float
	bb_temp_float3 = bb_vec_y * temp_float2
	bb_vec2_x -= bb_temp_float3
	// work out new bb_vec_y
	bb_vec2_y = bb_vec_x * temp_float2
	bb_temp_float3 = bb_vec_y * temp_float
	bb_vec2_y += bb_temp_float3
	// save
	SAVE_NEWLINE_TO_DEBUG_FILE 
	SAVE_STRING_TO_DEBUG_FILE "CAMERA_POINT_AT -       OFFSET FROM bbhoop = "
	SAVE_FLOAT_TO_DEBUG_FILE bb_vec2_x
	SAVE_FLOAT_TO_DEBUG_FILE bb_vec2_y
	SAVE_FLOAT_TO_DEBUG_FILE bb_vec_z

RETURN

}

MISSION_END




