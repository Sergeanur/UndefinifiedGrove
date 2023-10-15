MISSION_START

//--- Dance
CONST_INT SPRITE_CROSS				1
CONST_INT SPRITE_SQUARE 			2
CONST_INT SPRITE_TRIANGLE 			3
CONST_INT SPRITE_CIRCLE 			4

//--- Lowrider
CONST_INT SPRITE_STKLEFT			9	//not used 
CONST_INT SPRITE_STKRGHT			10	//not used

CONST_INT SPRITE_STKUR				11 
CONST_INT SPRITE_STKDL				12 
CONST_INT SPRITE_STKUP				13
CONST_INT SPRITE_STKDWN				14

CONST_INT SPRITE_STKUL				15
CONST_INT SPRITE_STKDR				16

//--- Hit zones
CONST_INT SPRITE_CRING				17

//--- FXs
CONST_INT SPRITE_CHIT				18

//--- Rendering Hit FX	
CONST_INT DB_HIT_DURATION			160 //(in milliseconds)
CONST_INT DB_HIT_STEP_MULTI_FAST 	8
CONST_INT DB_HIT_STEP_MULTI_SLOW 	4

CONST_INT BD_HIT_STANDARD			1
CONST_INT BD_HIT_GOOD				2
CONST_INT BD_HIT_PERFECT			3

MISSION_END

// GLOBAL VARIABLES --- 

// parameters - don't need to be set cause there's some default values, but can if you want.
VAR_INT bd_future_time_shown
VAR_INT bd_past_time_shown
VAR_FLOAT bd_x bd_y 
VAR_FLOAT bd_width
VAR_FLOAT bd_sprite_height bd_sprite_width

//VAR_INT fDownTimer

VAR_INT BD_RenderHit // Incoming request to render a hit around the button. 



// optional flags
VAR_INT bd_terminate_script		// set to 1 to terminate the script

VAR_INT debug_show_beat_position

// debug stuff for matt
VAR_INT time_of_first_beat
VAR_INT time_since_first_beat
VAR_INT beat_display_script_started


{///////////////////////////////////////////////////////////////////////////////
beat_display:///////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
SCRIPT_NAME BDISPLY
IF beat_display_script_started = 0
	beat_display_script_started = 1
	bd_terminate_script = 0
ENDIF

LVAR_INT temp_int iTemp	iTemp2
LVAR_INT temp_int2 
LVAR_INT temp_int3
LVAR_FLOAT temp_float 
LVAR_FLOAT temp_float2 
LVAR_FLOAT temp_float3
LVAR_INT time_to_this_beat time_to_this_beat_2
LVAR_INT this_beat_type this_beat_type_2
LVAR_INT this_beat_closestnumber this_beat_closestnumber_2 this_beat_closestnumber_stored
LVAR_INT this_time 
LVAR_INT last_time 
LVAR_INT time_elapsed
LVAR_INT sprite_num
LVAR_FLOAT fHitZone_sprite_height fHitZone_sprite_width fHitFX_sprite_width fHitFX_sprite_height
LVAR_INT iHit_Rendering iFXCurrentAlpha iFX_BeatToRender iFXDesiredAlpha iFXHitDuration 

USE_TEXT_COMMANDS TRUE
LOAD_TEXTURE_DICTIONARY ld_beat

LOAD_SPRITE SPRITE_CROSS cross
LOAD_SPRITE SPRITE_SQUARE square
LOAD_SPRITE SPRITE_TRIANGLE triang
LOAD_SPRITE SPRITE_CIRCLE circle

//The Notes
LOAD_SPRITE SPRITE_STKUP up 
LOAD_SPRITE SPRITE_STKDWN down
LOAD_SPRITE SPRITE_STKRGHT right 
LOAD_SPRITE SPRITE_STKLEFT left

LOAD_SPRITE SPRITE_STKUR upr 
LOAD_SPRITE SPRITE_STKDR downr
LOAD_SPRITE SPRITE_STKDL downl 
LOAD_SPRITE SPRITE_STKUL upl

//--- Hit zone 
LOAD_SPRITE SPRITE_CRING cring

//--- Hit FX
LOAD_SPRITE SPRITE_CHIT chit

//fDownTimer = 0

BD_FUTURE_TIME_SHOWN = 4500 
BD_PAST_TIME_SHOWN = 800 
BD_X = 265.0000 
BD_Y = 390.0000 
BD_WIDTH = 355.0000

BD_SPRITE_HEIGHT = 32.0 
BD_SPRITE_WIDTH = 32.0 

fHitZone_sprite_height = 64.0
fHitZone_sprite_width = 64.0

fHitFX_sprite_height = 64.0
fHitFX_sprite_width = 64.0

GET_GAME_TIMER last_time

WAIT 1000

beat_display_loop:
WAIT 0

	//--- If the player has paused the game, clear the FX rendering flags
	IF HAS_GAME_JUST_RETURNED_FROM_FRONTEND 
		BD_RenderHit = 0
		iHit_Rendering = 0
	ENDIF

	GET_BEAT_TRACK_STATUS	temp_int
	IF temp_int = CUTSCENE_TRACK_PLAYING 
		
		GET_GAME_TIMER this_time
		time_elapsed = this_time - last_time
		last_time = this_time

		//IF fDownTimer > 0
		//	fDownTimer -= time_elapsed
		//ENDIf

		// draw line for present time
		temp_int = bd_past_time_shown + bd_future_time_shown // total time shown
		temp_float =# temp_int
		temp_float2 =# bd_past_time_shown
		temp_float2 /= temp_float
		temp_float2 *= bd_width
		x = bd_x + temp_float2
		y = BD_Y

		//--- Draw the hit zone
		
		SET_SPRITES_DRAW_BEFORE_FADE TRUE
		DRAW_SPRITE SPRITE_CRING x y fHitZone_sprite_width fHitZone_sprite_height 128 128 128 255

		//////////////////////////////////////////////

		// used for turning off past beats
		GET_GAME_TIMER time_since_first_beat
		time_since_first_beat -= time_of_first_beat

		// draw future beats
		temp_int = 1
		WHILE temp_int < 11
			 
			GET_BEAT_PROXIMITY temp_int time_to_this_beat this_beat_type this_beat_closestnumber

			// draw this fucker
			IF NOT this_beat_type = 0
				
				IF time_to_this_beat < bd_future_time_shown

					// map sprite to beat
					GOSUB BD_MapButtonToBeat

					//WRITE_DEBUG_WITH_INT sprite_num sprite_num

					// where to draw //

					// total time shown
					temp_int2 = bd_past_time_shown + bd_future_time_shown 

					// time to beat //
					temp_int3 = time_to_this_beat
					temp_int3 += bd_past_time_shown
					temp_float =# temp_int3
					temp_float2 =# temp_int2
					
					// time to beat / total time
					temp_float /= temp_float2 
					temp_float *= bd_width

					x = bd_x + temp_float
					y = bd_y

					// figure out alpha
					temp_float =# time_to_this_beat
					temp_float2 =# bd_future_time_shown
					temp_float /= temp_float2
					temp_float *= -1.0
					temp_float += 1.0
					
					IF time_to_this_beat < 0
						temp_float = 1.0
					ENDIF
					
					temp_float *= 255.0
					temp_int3 =# temp_float

					IF NOT sprite_num = 0

						//--- Render FX						  
						GOSUB BD_RenderSpriteFX

						//--- Draw button sprite on top of FX
						
						SET_SPRITES_DRAW_BEFORE_FADE TRUE
						DRAW_SPRITE sprite_num x y bd_sprite_width bd_sprite_height 128 128 128 temp_int3	

					ENDIF
				ENDIF
			ENDIF
		temp_int++
		ENDWHILE

   //-------------------------------------------------------------------------------------------------------

		IF debug_show_beat_position = 0 // used for turning off past beats
			// draw past beats
			temp_int = -1
			WHILE temp_int > -11
				
				GET_BEAT_PROXIMITY temp_int time_to_this_beat this_beat_type this_beat_closestnumber

				time_to_this_beat *= -1

				// draw this fucker
				IF NOT this_beat_type = 0
					
					IF time_to_this_beat < bd_past_time_shown
						

						// map sprite to beat
						GOSUB BD_MapButtonToBeat

						// where to draw
						temp_int3 = time_to_this_beat
						temp_int3 *= -1
						temp_int3 += bd_past_time_shown
						
						temp_int2 = bd_past_time_shown + bd_future_time_shown // total time shown
						
						temp_float =# temp_int3
						temp_float2 =# temp_int2

						temp_float /= temp_float2 // how far along the line this needs to get drawn
						temp_float *= bd_width
						
						x = bd_x + temp_float
						y = bd_y

						// figure out alpha
						temp_float =# temp_int3
						temp_float2 =# bd_past_time_shown
						temp_float /= temp_float2
						IF time_to_this_beat < 0
							temp_float = 1.0
						ENDIF

						temp_float *= 255.0
						temp_int3 =# temp_float

						IF NOT sprite_num = 0
							GOSUB BD_RenderSpriteFX
							SET_SPRITES_DRAW_BEFORE_FADE TRUE
							DRAW_SPRITE sprite_num x y bd_sprite_width bd_sprite_height 128 128 128 temp_int3
						ENDIF

					ENDIF
				ENDIF
		    temp_int--
			ENDWHILE
		ENDIF	


	ELSE	   
		//DRAW_SPRITE SPRITE_STKDL backgroundX backgroundY 666.0 420.0 0 0 0 255
		// if track is not TRACK_PLAYING
		GOTO cleanup_beat_display  
	ENDIF


IF bd_terminate_script = 0
	GOTO beat_display_loop
ELSE
	GOTO cleanup_beat_display
ENDIF

cleanup_beat_display:
bd_terminate_script = 0
beat_display_script_started = 0
USE_TEXT_COMMANDS FALSE
REMOVE_TEXTURE_DICTIONARY
TERMINATE_THIS_SCRIPT


// SUBROUTINES------------------------------------------------------

/********************************************
			MAP BUTTON TO BEAT
********************************************/
BD_MapButtonToBeat:

	sprite_num = 0

	IF this_beat_type = SPRITE_CROSS
		sprite_num = SPRITE_CROSS
	ENDIF
	IF this_beat_type = SPRITE_SQUARE
		sprite_num = SPRITE_SQUARE
	ENDIF
	IF this_beat_type = SPRITE_TRIANGLE
		sprite_num = SPRITE_TRIANGLE
	ENDIF
	IF this_beat_type = SPRITE_CIRCLE
		sprite_num = SPRITE_CIRCLE
	ENDIF

	// analogue directions
	IF this_beat_type = SPRITE_STKLEFT  // left	
		sprite_num = SPRITE_STKLEFT													
	ENDIF																
	IF this_beat_type = SPRITE_STKRGHT // right	
		sprite_num = SPRITE_STKRGHT													
	ENDIF																
	IF this_beat_type = SPRITE_STKUR // up & right								
		sprite_num = SPRITE_STKUR													
	ENDIF
	IF this_beat_type = SPRITE_STKDL // down & left
		sprite_num = SPRITE_STKDL
	ENDIF
	IF this_beat_type = SPRITE_STKUP // up
		sprite_num = SPRITE_STKUP
	ENDIF
	IF this_beat_type = SPRITE_STKDWN // down
		sprite_num = SPRITE_STKDWN
	ENDIF
	IF this_beat_type = SPRITE_STKUL // up & left
		sprite_num = SPRITE_STKUL
	ENDIF
	IF this_beat_type = SPRITE_STKDR // down & right
		sprite_num = SPRITE_STKDR
	ENDIF

RETURN

/********************************************
			RENDER SPRITE FX
********************************************/
BD_RenderSpriteFX:	

	IF BD_RenderHit > 0
	AND iHit_Rendering = 0		
		GET_BEAT_PROXIMITY 0 iTemp iTemp iFX_BeatToRender
		++iFX_BeatToRender
		iHit_Rendering = 1
	ENDIF

	this_beat_closestnumber_2 = this_beat_closestnumber
	this_beat_closestnumber_2 += temp_int

	IF temp_int < 0
		time_to_this_beat *= -1	
		++this_beat_closestnumber_2
	ENDIF
	//--- FX rendering
	IF this_beat_closestnumber_2 = iFX_BeatToRender
		IF time_to_this_beat < DANCE_BOUNDS_NO_BEAT
		AND time_to_this_beat > -400							
			GOSUB BD_RenderHitFX 
		ELSE
			iHit_Rendering = 0
			BD_RenderHit = 0			 		 		 
		ENDIF
	ELSE		
		BD_RenderHit = 0			 		 		 
	ENDIF

RETURN
/********************************************
			RENDER HIT FX
********************************************/

BD_RenderHitFX:

SWITCH iHit_Rendering

	CASE 1

		IF BD_RenderHit = BD_HIT_STANDARD
			iFXDesiredAlpha = 90
			iFXHitDuration = 100
		ELSE
			IF BD_RenderHit = BD_HIT_GOOD
				iFXDesiredAlpha = 190
				iFXHitDuration = 80
			ELSE
				IF BD_RenderHit = BD_HIT_PERFECT
					iFXDesiredAlpha = 255
					iFXHitDuration = 25
				ENDIF
			ENDIF
		ENDIF
		
		iFXCurrentAlpha = iFXDesiredAlpha

		TIMERB = 0		

		SET_SPRITES_DRAW_BEFORE_FADE TRUE

		DRAW_SPRITE SPRITE_CHIT x y fHitFX_sprite_width fHitFX_sprite_height 128 128 128 iFXCurrentAlpha
		
		++iHit_Rendering
	BREAK

	CASE 2
		IF iFXHitDuration > TIMERB
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE SPRITE_CHIT x y fHitFX_sprite_width fHitFX_sprite_height 128 128 128 iFXCurrentAlpha
		ELSE
			SET_SPRITES_DRAW_BEFORE_FADE TRUE
			DRAW_SPRITE SPRITE_CHIT x y fHitFX_sprite_width fHitFX_sprite_height 128 128 128 iFXCurrentAlpha
			++iHit_Rendering
		ENDIF
	BREAK

	CASE 3
		//--- compute alpha
		iTemp = iFXDesiredAlpha / time_elapsed 
		iTemp *= DB_HIT_STEP_MULTI_SLOW
		//--- Fade out
		iFXCurrentAlpha -= iTemp
		IF iFXCurrentAlpha <= 0
			iFXCurrentAlpha = 0
			//--- FX finished
			iHit_Rendering = 0
			BD_RenderHit = 0
		ENDIF

		SET_SPRITES_DRAW_BEFORE_FADE TRUE

		DRAW_SPRITE SPRITE_CHIT x y fHitFX_sprite_width fHitFX_sprite_height 128 128 128 iFXCurrentAlpha
	BREAK

ENDSWITCH
RETURN
}
