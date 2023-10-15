MISSION_START
// ****************************************************************************************
// ***																					***
// *** Date: 23/07/2004 	Time: 10:27:07	   Author:  Chris Rothwell 					***
// ***																					***
// *** Mission: Catalina - Global Script 												***
// ***																					***
// ****************************************************************************************

GOSUB mission_cat_start

IF HAS_DEATHARREST_BEEN_EXECUTED 
	GOSUB mission_cat_failed
ENDIF

GOSUB mission_cat_cleanup

MISSION_END
 
// ************************************ MISSION START *************************************
{
mission_cat_start:

flag_player_on_mission = 1

REGISTER_MISSION_GIVEN
SCRIPT_NAME catalin

WAIT 0

LOAD_MISSION_TEXT CAT

LVAR_INT mission_blip mission_flag mission_timer temp_int sequence_task players_car	a b c
LVAR_FLOAT h x2 y2 z2 x3 y3 z3 temp_float speed

lvar_int cat_convo_lines[3]
cat_convo_lines[0] = 0
cat_convo_lines[1] = 0
cat_convo_lines[2] = 0

lvar_int carl
carl = scplayer

MAKE_PLAYER_GANG_DISAPPEAR

if cat_counter = 0
	SET_AREA_VISIBLE 1

	LOAD_CUTSCENE CAT_1	//at the bar guys hold catalina up

	WHILE NOT HAS_CUTSCENE_LOADED
		WAIT 0
	ENDWHILE
	 
	START_CUTSCENE

	DO_FADE 1000 FADE_IN
	  
	WHILE NOT HAS_CUTSCENE_FINISHED
		WAIT 0
	ENDWHILE
	 
	CLEAR_CUTSCENE
	
	SET_PLAYER_CONTROL player1 OFF

	DO_FADE 0 FADE_OUT

	WHILE GET_FADING_STATUS
		WAIT 0
	ENDWHILE

	SET_AREA_VISIBLE 0

	LOAD_SPECIAL_CHARACTER 5 cat 
	while NOT HAS_SPECIAL_CHARACTER_LOADED 5
		wait 0
		LOAD_SPECIAL_CHARACTER 5 cat 
	endwhile
	
	CLEAR_AREA 681.1634 -478.1859 15.3281 0.5 0
	LOAD_SCENE 681.1634 -478.1859 15.3281
	SET_CHAR_COORDINATES scplayer 681.1634 -478.1859 15.3281 
	SET_CHAR_HEADING scplayer 177.0008
	SET_CAMERA_BEHIND_PLAYER
	CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 682.8292 -477.8332 15.3359 catalina
	set_char_heading catalina 149.5317

	//Outside the diner			
	lvar_text_label $cat_convo1[20]
	$cat_convo1[0] = &CAT_AA	//CATALINA:	Where's your car?
	$cat_convo1[1] = &CAT_AB	//CARL:		Where's yours?
	$cat_convo1[2] = &CAT_AC	//CATALINA:	Ladies don't drive themselves, that's what men are for!
	$cat_convo1[3] = &CAT_AD	//CARL:		Oh my goodness!  Thanks Cesar. Appreciate this, homie!
	lvar_int cat_convo1_sound[20]
	cat_convo1_sound[0] = SOUND_CAT_AA
	cat_convo1_sound[1] = SOUND_CAT_AB
	cat_convo1_sound[2] = SOUND_CAT_AC
	cat_convo1_sound[3] = SOUND_CAT_AD
	lvar_int cat_convo1_ped[20]
	cat_convo1_ped[0] = CATALINA
	cat_convo1_ped[1] = CARL		
	cat_convo1_ped[2] = CATALINA
	cat_convo1_ped[3] = CARL		
	cat_convo_lines[0] = 4

	//In a car - INSTRUCTION			
	lvar_text_label $cat_convo2[12]
	$cat_convo2[0]  = &CAT_BA	//CARL:		So, what's your name? Where we going?
	$cat_convo2[1]  = &CAT_BB	//CATALINA:	My name is Catalina and we gonna take this county for every stinking cent!
	$cat_convo2[2]  = &CAT_BC	//CARL:		Ok, good plan I guess.
	$cat_convo2[3]  = &CAT_BD	//CATALINA:	You're damn straight it's a good plan!
	$cat_convo2[4]  = &CAT_BE	//CATALINA: I've cased four soft targets;
	$cat_convo2[5]  = &CAT_BH	//CATALINA:	A liquor store in Blueberry.
	$cat_convo2[6]  = &CAT_BG	//CATALINA:	A bank in Palomino Creek.
	$cat_convo2[7]  = &CAT_BF	//CATALINA:	A gas station in Dillimore.
	$cat_convo2[8]  = &CAT_BI	//CATALINA:	And a betting shop in Montgomery.
	$cat_convo2[9]  = &CAT_BJ	//CARL:		Hold up. Which one first?
	$cat_convo2[10] = &CAT_BK	//CATALINA:	You're the driver, you dumb pig, you choose!
	lvar_int cat_convo2_sound[12]
	cat_convo2_sound[0]  = SOUND_CAT_BA	
	cat_convo2_sound[1]  = SOUND_CAT_BB	
	cat_convo2_sound[2]  = SOUND_CAT_BC	
	cat_convo2_sound[3]  = SOUND_CAT_BD	
	cat_convo2_sound[4]  = SOUND_CAT_BE	
	cat_convo2_sound[5]  = SOUND_CAT_BH	
	cat_convo2_sound[6]  = SOUND_CAT_BG	
	cat_convo2_sound[7]  = SOUND_CAT_BF	
	cat_convo2_sound[8]  = SOUND_CAT_BI	
	cat_convo2_sound[9]  = SOUND_CAT_BJ	
	cat_convo2_sound[10] = SOUND_CAT_BK
	lvar_int cat_convo2_ped[12]
	cat_convo2_ped[0] =  CARL		
	cat_convo2_ped[1] =  CATALINA	
	cat_convo2_ped[2] =  CARL		
	cat_convo2_ped[3] =  CATALINA	
	cat_convo2_ped[4] =  CATALINA
	cat_convo2_ped[5] =  CATALINA	
	cat_convo2_ped[6] =  CATALINA
	cat_convo2_ped[7] =  CATALINA	
	cat_convo2_ped[8] =  CATALINA
	cat_convo2_ped[9] =  CARL			
	cat_convo2_ped[10] = CATALINA
	cat_convo_lines[1] = 11

	//Carl & Catalina in car on way to FIRST job - BANTER/INSTRUCTION			
	lvar_text_label $cat_convo3[12]
	$cat_convo3[0]  = &CAT_CA	//CARL:		You pretty highly strung, huh, lady.
	$cat_convo3[1]  = &CAT_CB	//CATALINA:	Yeah? And what are you? 
	$cat_convo3[2]  = &CAT_CC	//CATALINA:	Some laid back gang banger dude?
	$cat_convo3[3]  = &CAT_CD	//CARL:		I keep myself to myself. That's my style.
	$cat_convo3[4]  = &CAT_CE	//CATALINA:	Cesar says you got a brother in jail, 
	$cat_convo3[5]  = &CAT_CF	//CATALINA:	another brother dead, a mother just killed,
	$cat_convo3[6]  = &CAT_CG	//CATALINA:	and you got a bent cop on your case.
	$cat_convo3[7]  = &CAT_CH	//CARL:		I ain't listening to you.
	$cat_convo3[8]  = &CAT_CI	//CATALINA:	You think that is keeping yourself to yourself, eh amigo?
	$cat_convo3[9]  = &CAT_CJ	//CATALINA:	Real cool? You just an idiota!
	$cat_convo3[10] = &CAT_CK	//CARL:		And you're real charming. A proper lady.
	lvar_int cat_convo3_sound[12]
	cat_convo3_sound[0]  = SOUND_CAT_CA	
	cat_convo3_sound[1]  = SOUND_CAT_CB	
	cat_convo3_sound[2]  = SOUND_CAT_CC	
	cat_convo3_sound[3]  = SOUND_CAT_CD	
	cat_convo3_sound[4]  = SOUND_CAT_CE	
	cat_convo3_sound[5]  = SOUND_CAT_CF	
	cat_convo3_sound[6]  = SOUND_CAT_CG	
	cat_convo3_sound[7]  = SOUND_CAT_CH	
	cat_convo3_sound[8]  = SOUND_CAT_CI	
	cat_convo3_sound[9]  = SOUND_CAT_CJ	
	cat_convo3_sound[10] = SOUND_CAT_CK
	lvar_int cat_convo3_ped[12]
	cat_convo3_ped[0] =  CARL		
	cat_convo3_ped[1] =  CATALINA	
	cat_convo3_ped[2] =  CATALINA
	cat_convo3_ped[3] =  CARL			
	cat_convo3_ped[4] =  CATALINA
	cat_convo3_ped[5] =  CATALINA	
	cat_convo3_ped[6] =  CATALINA
	cat_convo3_ped[7] =  CARL			
	cat_convo3_ped[8] =  CATALINA
	cat_convo3_ped[9] =  CATALINA	
	cat_convo3_ped[10] = CARL		
	cat_convo_lines[2] = 11
else
	FORCE_WEATHER_NOW WEATHER_EXTRASUNNY_COUNTRYSIDE 
	if cat_counter = 1
		LOAD_CUTSCENE CAT_2	//catalina sneaks up on player from behind

		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
		 
		CLEAR_AREA 868.5210 -30.0862 63.1875 5.8 0
		//SET_ALL_CARS_IN_AREA_VISIBLE 868.5210 -30.0862 63.1875 5.8 5.8 5.8 FALSE
		START_CUTSCENE

		DO_FADE 1000 FADE_IN
		  
		WHILE NOT HAS_CUTSCENE_FINISHED
			WAIT 0
		ENDWHILE
		
		CLEAR_CUTSCENE

		SET_PLAYER_CONTROL player1 OFF

		DO_FADE 0 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_AREA_VISIBLE 0
		SET_INT_STAT STARTED_CAT2 1
		
		LOAD_SPECIAL_CHARACTER 5 cat 
		while NOT HAS_SPECIAL_CHARACTER_LOADED 5
			wait 0
			LOAD_SPECIAL_CHARACTER 5 cat 
		endwhile
		CLEAR_AREA 868.4825 -29.6753 62.1875 0.5 0
		LOAD_SCENE 868.4825 -29.6753 62.1875
		SET_CHAR_COORDINATES scplayer 868.4825 -29.6753 62.1875 
		SET_CHAR_HEADING scplayer 157.9281
		SET_CAMERA_BEHIND_PLAYER
		CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 867.7455 -29.0865 62.1875 catalina
		set_char_heading catalina 225.9654
		
		//Carl & Catalina in car on way to second job - INSTRUCTION			
		$cat_convo1[0]  = &CAT_FA	//CARL:		Hey, what we got left?
		cat_convo1_sound[0]  = SOUND_CAT_FA	
		cat_convo1_ped[0] =  CARL		
		$cat_convo1[1]  = &CAT_FB	//CATALINA:	Are you stupid? You forget already?
		cat_convo1_sound[1]  = SOUND_CAT_FB	
		cat_convo1_ped[1] =  CATALINA
		
		temp_int = 2
		if flag_cat_mission3_passed = 0
			$cat_convo1[temp_int]  = &CAT_FC	//CATALINA:	The gas station in Dillimore.
			cat_convo1_sound[temp_int]  = SOUND_CAT_FC	
			cat_convo1_ped[temp_int] =  CATALINA
			++ temp_int
		endif
		if flag_cat_mission2_passed = 0
			$cat_convo1[temp_int]  = &CAT_FD	//CATALINA:	The bank in Palomino Creek.
			cat_convo1_sound[temp_int]  = SOUND_CAT_FD	
			cat_convo1_ped[temp_int] =  CATALINA
			++ temp_int
		endif
		if flag_cat_mission1_passed = 0
			if flag_cat_mission4_passed = 0
				$cat_convo1[temp_int]  = &CAT_FE	//CATALINA:	The liquor store in Blueberry.
				cat_convo1_sound[temp_int]  = SOUND_CAT_FE	
				cat_convo1_ped[temp_int] =  CATALINA
			else	
				$cat_convo1[temp_int]  = &CAT_FG	//CATALINA:	Or the liquor store in Blueberry.
				cat_convo1_sound[temp_int]  = SOUND_CAT_FG	
				cat_convo1_ped[temp_int] =  CATALINA
			endif
			++ temp_int
		endif
		if flag_cat_mission4_passed = 0
			$cat_convo1[temp_int]  = &CAT_FH	//CATALINA:	Or the betting shop in Montgomery.
			cat_convo1_sound[temp_int]  = SOUND_CAT_FH	
			cat_convo1_ped[temp_int] =  CATALINA
			++ temp_int
		endif
		$cat_convo1[temp_int]  = &CAT_FI	//CARL:		Ok, but this time we're gonna do it real chill, 
		cat_convo1_sound[temp_int]  = SOUND_CAT_FI	
		cat_convo1_ped[temp_int] =  CARL		
		++ temp_int
		$cat_convo1[temp_int]  = &CAT_FJ	//CARL:		no crazy psycho shit, baby.
		cat_convo1_sound[temp_int]  = SOUND_CAT_FJ	
		cat_convo1_ped[temp_int] =  CARL		
		++ temp_int
		$cat_convo1[temp_int] = &CAT_FK	//CATALINA:	Speak for yourself, soft boy.
		cat_convo1_sound[temp_int] = SOUND_CAT_FK
		cat_convo1_ped[temp_int] = CATALINA
		++ temp_int
		cat_convo_lines[0] = temp_int


		$cat_convo2[0]  = &CAT_FL	//CATALINA:	Today I feel like killing all the men I meet!
		$cat_convo2[1]  = &CAT_FM	//CARL:		Ah baby, c'mon, what about me?
		$cat_convo2[2]  = &CAT_FN	//CATALINA:	Don't worry, I make an exception for some of the men in my life.
		$cat_convo2[3]  = &CAT_FO	//CATALINA:	Now drive faster - YEEAAARGHH!
		cat_convo2_sound[0]  = SOUND_CAT_FL	
		cat_convo2_sound[1]  = SOUND_CAT_FM	
		cat_convo2_sound[2]  = SOUND_CAT_FN	
		cat_convo2_sound[3]  = SOUND_CAT_FO	
		cat_convo2_ped[0] =  CATALINA
		cat_convo2_ped[1] =  CARL
		cat_convo2_ped[2] =  CATALINA
		cat_convo2_ped[3] =  CATALINA	
		cat_convo_lines[1] = 4
	endif

	if cat_counter = 2
		LOAD_CUTSCENE CAT_3	//catalina whips carl

		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
		 
		CLEAR_AREA 868.5210 -30.0862 63.1875 5.8 0
		START_CUTSCENE

		DO_FADE 1000 FADE_IN
		  
		WHILE NOT HAS_CUTSCENE_FINISHED
			WAIT 0
		ENDWHILE
		
		CLEAR_CUTSCENE

		SET_PLAYER_CONTROL player1 OFF

		DO_FADE 0 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_AREA_VISIBLE 0
		
		LOAD_SPECIAL_CHARACTER 5 cat 
		while NOT HAS_SPECIAL_CHARACTER_LOADED 5
			wait 0
			LOAD_SPECIAL_CHARACTER 5 cat 
		endwhile
		CLEAR_AREA 868.4825 -29.6753 62.1875 0.5 0
		LOAD_SCENE 868.4825 -29.6753 62.1875
		SET_CHAR_COORDINATES scplayer 868.4825 -29.6753 62.1875 
		SET_CHAR_HEADING scplayer 157.9281
		SET_CAMERA_BEHIND_PLAYER
		CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 867.7455 -29.0865 62.1875 catalina
		set_char_heading catalina 225.9654
		
		//Carl & Catalina in car on way to third job - BANTER/INSTRUCTION			
		$cat_convo1[0]  = &CAT_KA	//CATALINA:	How was it?
		$cat_convo1[1]  = &CAT_KB	//CARL:		Different
		$cat_convo1[2]  = &CAT_KC	//CATALINA:	I knew you would like it!
		$cat_convo1[3]  = &CAT_KD	//CARL:		Look, baby, I thought we was going to make some serious paper.
		$cat_convo1[4]  = &CAT_KE	//CATALINA:	I'm starting to get really bored of you.
		$cat_convo1[5]  = &CAT_KF	//CARL:		I just need the money.
		$cat_convo1[6]  = &CAT_KG	//CATALINA:	And I'm just a cheap fuck? 
		$cat_convo1[7]  = &CAT_KH	//CATALINA:	A whore you don't even pay?
		$cat_convo1[8]  = &CAT_KI	//CARL:		No, I didn't say that.
		$cat_convo1[9]  = &CAT_KJ	//CATALINA:	Carl, I say I'm in love with you, and you act like I'm an idiot. 
		$cat_convo1[10] = &CAT_KK	//CATALINA:	I see the way you look at other women. 
		$cat_convo1[11] = &CAT_KL	//CATALINA:	I know your kind. Carl. I am serious. 
		$cat_convo1[12] = &CAT_KM	//CATALINA:	I will kill you if you ever mess around. 
		$cat_convo1[13] = &CAT_KN	//CATALINA:	I will castrate you first. 
		$cat_convo1[14] = &CAT_KO	//CATALINA:	Then I will make you eat them.
		$cat_convo1[15] = &CAT_KP	//CARL:		Enough! I need some fucking money!
		$cat_convo1[16] = &CAT_KQ	//CATALINA:	Carl, you are really boring me now.
		$cat_convo1[17] = &CAT_KR	//CARL:		Please, sweet hear. I've got some real, real deep shit.
		$cat_convo1[18] = &CAT_KS	//CATALINA:	Okay. Maybe today we hit it big!
		cat_convo1_sound[0]  = SOUND_CAT_KA	
		cat_convo1_sound[1]  = SOUND_CAT_KB	
		cat_convo1_sound[2]  = SOUND_CAT_KC	
		cat_convo1_sound[3]  = SOUND_CAT_KD	
		cat_convo1_sound[4]  = SOUND_CAT_KE	
		cat_convo1_sound[5]  = SOUND_CAT_KF	
		cat_convo1_sound[6]  = SOUND_CAT_KG	
		cat_convo1_sound[7]  = SOUND_CAT_KH	
		cat_convo1_sound[8]  = SOUND_CAT_KI	
		cat_convo1_sound[9]  = SOUND_CAT_KJ	
		cat_convo1_sound[10] = SOUND_CAT_KK
		cat_convo1_sound[11] = SOUND_CAT_KL	
		cat_convo1_sound[12] = SOUND_CAT_KM	
		cat_convo1_sound[13] = SOUND_CAT_KN	
		cat_convo1_sound[14] = SOUND_CAT_KO	
		cat_convo1_sound[15] = SOUND_CAT_KP	
		cat_convo1_sound[16] = SOUND_CAT_KQ	
		cat_convo1_sound[17] = SOUND_CAT_KR	
		cat_convo1_sound[18] = SOUND_CAT_KS	
		cat_convo1_ped[0]  = CATALINA
		cat_convo1_ped[1]  = CARL		
		cat_convo1_ped[2]  = CATALINA
		cat_convo1_ped[3]  = CARL		
		cat_convo1_ped[4]  = CATALINA
		cat_convo1_ped[5]  = CARL		
		cat_convo1_ped[6]  = CATALINA
		cat_convo1_ped[7]  = CATALINA
		cat_convo1_ped[8]  = CARL		
		cat_convo1_ped[9]  = CATALINA
		cat_convo1_ped[10] = CATALINA
		cat_convo1_ped[11] = CATALINA
		cat_convo1_ped[12] = CATALINA
		cat_convo1_ped[13] = CATALINA
		cat_convo1_ped[14] = CATALINA
		cat_convo1_ped[15] = CARL		
		cat_convo1_ped[16] = CATALINA
		cat_convo1_ped[17] = CARL		
		cat_convo1_ped[18] = CATALINA
		cat_convo_lines[0] = 19
	endif

	if cat_counter = 3
		LOAD_CUTSCENE CAT_4	//catalina has a go at carl - bursts out of the door

		WHILE NOT HAS_CUTSCENE_LOADED
			WAIT 0
		ENDWHILE
		 
		CLEAR_AREA 868.5210 -30.0862 63.1875 5.8 0
		//SET_ALL_CARS_IN_AREA_VISIBLE 868.5210 -30.0862 63.1875 5.8 5.8 5.8 FALSE
		START_CUTSCENE

		DO_FADE 1000 FADE_IN
		  
		WHILE NOT HAS_CUTSCENE_FINISHED
			WAIT 0
		ENDWHILE

		CLEAR_CUTSCENE

		SET_PLAYER_CONTROL player1 OFF

		DO_FADE 0 FADE_OUT

		WHILE GET_FADING_STATUS
			WAIT 0
		ENDWHILE

		SET_AREA_VISIBLE 0
		
		LOAD_SPECIAL_CHARACTER 5 cat 
		while NOT HAS_SPECIAL_CHARACTER_LOADED 5
			wait 0
			LOAD_SPECIAL_CHARACTER 5 cat 
		endwhile
		CLEAR_AREA 868.4825 -29.6753 62.1875 0.5 0
		LOAD_SCENE 868.4825 -29.6753 62.1875
		SET_CHAR_COORDINATES scplayer 868.4825 -29.6753 62.1875 
		SET_CHAR_HEADING scplayer 157.9281
		SET_CAMERA_BEHIND_PLAYER
		CREATE_CHAR	PEDTYPE_SPECIAL SPECIAL05 867.7455 -29.0865 62.1875 catalina
		set_char_heading catalina 225.9654
		
		//Carl & Catalina in car on way to job discussing how bank job went - BANTER/INSTRUCTION			
		$cat_convo1[0] = &CAT4_LA	//CATALINA:	I feel good today, like a woman reborn!
		$cat_convo1[1] = &CAT4_LB	//CARL:		Cool, maybe you won't go berserk, eh?
		$cat_convo1[2] = &CAT4_LC	//CATALINA:	Oh, I go berserk, but not until I really pissed!
		$cat_convo1[3] = &CAT4_LD	//CARL:		Oh, well, that's a relief to hear!
		$cat_convo1[4] = &CAT4_LE	//CATALINA:	Perhaps this time no cowboy motherfuckers will get in our way!
		$cat_convo1[5] = &CAT4_LF	//CARL:		Amen to that!
		cat_convo1_sound[0]  = SOUND_CAT4_LA	
		cat_convo1_sound[1]  = SOUND_CAT4_LB	
		cat_convo1_sound[2]  = SOUND_CAT4_LC	
		cat_convo1_sound[3]  = SOUND_CAT4_LD	
		cat_convo1_sound[4]  = SOUND_CAT4_LE	
		cat_convo1_sound[5]  = SOUND_CAT4_LF	
		cat_convo1_ped[0]  = CATALINA
		cat_convo1_ped[1]  = CARL		
		cat_convo1_ped[2]  = CATALINA
		cat_convo1_ped[3]  = CARL		
		cat_convo1_ped[4]  = CATALINA
		cat_convo1_ped[5]  = CARL		
		cat_convo_lines[0] = 6

		//Carl & Catalina in car on way to FORTH job - BANTER/INSTRUCTION			
		$cat_convo2[0] = &CAT_NA	//CATALINA:	Come on, drive!
		$cat_convo2[1] = &CAT_NB	//CARL:		What's wrong?
		$cat_convo2[2] = &CAT_NC	//CATALINA:	Nothing. I Just hate men.
		$cat_convo2[3] = &CAT_ND	//CARL:		Well give me a break.
		$cat_convo2[4] = &CAT_NE	//CATALINA:	Here's your break. 
		$cat_convo2[5] = &CAT_NF	//CATALINA:	Your break is you're not on my barbecue being eaten.
		$cat_convo2[6] = &CAT_NG	//CARL:		Well, that's one way of looking at shit, I suppose.
		$cat_convo2[7] = &CAT_NH	//CATALINA:	Now, try to be a man this time.
		$cat_convo2[8] = &CAT_KT	//CATALINA:	Drive, lover!
		cat_convo2_sound[0]  = SOUND_CAT_NA
		cat_convo2_sound[1]  = SOUND_CAT_NB
		cat_convo2_sound[2]  = SOUND_CAT_NC
		cat_convo2_sound[3]  = SOUND_CAT_ND
		cat_convo2_sound[4]  = SOUND_CAT_NE
		cat_convo2_sound[5]  = SOUND_CAT_NF
		cat_convo2_sound[6]  = SOUND_CAT_NG
		cat_convo2_sound[7]  = SOUND_CAT_NH
		cat_convo2_sound[8]  = SOUND_CAT_KT
		cat_convo2_ped[0]  = CATALINA
		cat_convo2_ped[1]  = CARL		
		cat_convo2_ped[2]  = CATALINA
		cat_convo2_ped[3]  = CARL		
		cat_convo2_ped[4]  = CATALINA
		cat_convo2_ped[5]  = CATALINA
		cat_convo2_ped[6]  = CARL		
		cat_convo2_ped[7]  = CATALINA
		cat_convo2_ped[8]  = CATALINA
		cat_convo_lines[1] = 9
	endif
endif
  
SET_ANIM_GROUP_FOR_CHAR catalina WOMAN
//DONT_REMOVE_CHAR catalina
SET_CHAR_ONLY_DAMAGED_BY_PLAYER catalina TRUE
SET_CHAR_NEVER_TARGETTED catalina TRUE
SET_CHAR_RELATIONSHIP catalina ACQUAINTANCE_TYPE_PED_RESPECT PEDTYPE_PLAYER1
SET_CHAR_HEALTH catalina 100
SET_CHAR_SUFFERS_CRITICAL_HITS catalina FALSE
//set_char_drowns_in_water catalina true
//ENABLE_ENTRY_EXIT_PLAYER_GROUP_WARPING 1291.0 271.0 10.0 TRUE // (added by KEvin) so catalina follows player inside the OTB

IF NOT IS_GROUP_MEMBER catalina players_group  
	MAKE_ROOM_IN_PLAYER_GANG_FOR_MISSION_PEDS 1
	SET_GROUP_MEMBER players_group catalina
	SET_PLAYER_GROUP_TO_FOLLOW_ALWAYS player1 TRUE
	LISTEN_TO_PLAYER_GROUP_COMMANDS catalina FALSE
ENDIF

release_weather
MAKE_PLAYER_GANG_REAPPEAR
DO_FADE 1000 FADE_IN

SET_PLAYER_CONTROL player1 on

WHILE GET_FADING_STATUS
	WAIT 0
ENDWHILE

if cat_liquor_banter = 1
	cat_liquor_banter = 0
endif
if cat_otb_banter = 1
	cat_otb_banter = 0
endif

if flag_cat_mission1_passed = 1
and cat_liquor_banter = 0
	//Carl & Catalina in car on way to job discussing how Liquor store job went - BANTER			
	$cat_convo3[0]  = &CAT1_IA	//CATALINA:	I hope you drive better this time around!
	$cat_convo3[1]  = &CAT1_IB	//CARL:		You ever driven a quad? It ain't easy.
	$cat_convo3[2]  = &CAT1_IC	//CARL:		Specially when you got a screaming bitch on the back, 
	$cat_convo3[3]  = &CAT1_IE	//CATALINA:	I like it when you angry at me, Carl.
	$cat_convo3[4]  = &CAT1_IF	//CATALINA:	I will try to anger you more often.
	$cat_convo3[5]  = &CAT1_IG	//CARL:		Now that I'm looking forward to
	cat_convo3_sound[0]  = SOUND_CAT1_IA	
	cat_convo3_sound[1]  = SOUND_CAT1_IB	
	cat_convo3_sound[2]  = SOUND_CAT1_IC	
	cat_convo3_sound[3]  = SOUND_CAT1_IE	
	cat_convo3_sound[4]  = SOUND_CAT1_IF	
	cat_convo3_sound[5]  = SOUND_CAT1_IG	
	cat_convo3_ped[0]  = CATALINA	
	cat_convo3_ped[1]  = CARL			
	cat_convo3_ped[2]  = CARL			
	cat_convo3_ped[3]  = CATALINA	
	cat_convo3_ped[4]  = CATALINA	
	cat_convo3_ped[5]  = CARL			
	cat_convo_lines[2] = 6
	cat_liquor_banter = 1
else
	if flag_cat_mission4_passed = 1
	and cat_otb_banter = 0
		//Carl & Catalina in car on way to job discussing how OTB job went - BANTER/INSTRUCTION			
		$cat_convo3[0]  = &CAT2_HA	//CATALINA:	Carl, you have to be fast and totally ruthless!
		$cat_convo3[1]  = &CAT2_HB	//CATALINA:	No fucking about like in the betting shop!
		$cat_convo3[2]  = &CAT2_HC	//CARL:		What? If you hadn't started bustin', things would have been sweet!
		$cat_convo3[3]  = &CAT2_HE	//CATALINA:	They had to die because YOU were slow and stupid, 
		$cat_convo3[4]  = &CAT2_HF	//CATALINA:	like a big fat brat that eats chocolates while his 
		$cat_convo3[5]  = &CAT2_HG	//CATALINA:	father gives nothing to his step daughter but stale bread!
		$cat_convo3[6]  = &CAT2_HH	//CARL:		Wh-what is you talking about?
		$cat_convo3[7]  = &CAT2_HI	//CATALINA:	Enough! I am not speaking to you any more!
		cat_convo3_sound[0]  = SOUND_CAT2_HA	
		cat_convo3_sound[1]  = SOUND_CAT2_HB	
		cat_convo3_sound[2]  = SOUND_CAT2_HC	
		cat_convo3_sound[3]  = SOUND_CAT2_HE	
		cat_convo3_sound[4]  = SOUND_CAT2_HF	
		cat_convo3_sound[5]  = SOUND_CAT2_HG	
		cat_convo3_sound[6]  = SOUND_CAT2_HH	
		cat_convo3_sound[7]  = SOUND_CAT2_HI	
		cat_convo3_ped[0]  = CATALINA
		cat_convo3_ped[1]  = CATALINA
		cat_convo3_ped[2]  = CARL		
		cat_convo3_ped[3]  = CATALINA
		cat_convo3_ped[4]  = CATALINA
		cat_convo3_ped[5]  = CATALINA
		cat_convo3_ped[6]  = CARL		
		cat_convo3_ped[7]  = CATALINA
		cat_convo_lines[2] = 8
		cat_otb_banter = 1
	endif
endif

lvar_int convo_counter
convo_counter = 0

lvar_int convo_flag
convo_flag = 0

lvar_int convo_timer
convo_timer = 0

lvar_int audio_slot
audio_slot = 1

lvar_int blip_counter
blip_counter = 0

lvar_int cat_in_group_flag
cat_in_group_flag = 1

lvar_int blip_on_cat_mission1 blip_on_cat_mission2 blip_on_cat_mission3 blip_on_cat_mission4
blip_on_cat_mission1 = 0
blip_on_cat_mission2 = 0
blip_on_cat_mission3 = 0
blip_on_cat_mission4 = 0

if cat_counter > 0
	lvar_int cat_mission1_blip cat_mission2_blip cat_mission3_blip cat_mission4_blip
	IF flag_cat_mission1_passed = 0
		ADD_SPRITE_BLIP_FOR_COORD catX[1] catY[1] catZ[1] RADAR_SPRITE_CASH cat_mission1_blip  //CAT1.SC - LIQUOR - Neil
		blip_on_cat_mission1 = 1
	ENDIF
	IF flag_cat_mission2_passed = 0
		ADD_SPRITE_BLIP_FOR_COORD catX[2] catY[2] catZ[2] RADAR_SPRITE_CASH cat_mission2_blip  //CAT2.SC - BANK - ChrisR
		blip_on_cat_mission2 = 1
	ENDIF
	IF flag_cat_mission3_passed = 0
		ADD_SPRITE_BLIP_FOR_COORD catX[3] catY[3] catZ[3] RADAR_SPRITE_CASH cat_mission3_blip  //CAT3.SC - GAS STATION - ChrisM
		blip_on_cat_mission3 = 1
	ENDIF
	IF flag_cat_mission4_passed = 0
		ADD_SPRITE_BLIP_FOR_COORD catX[4] catY[4] catZ[4] RADAR_SPRITE_CASH cat_mission4_blip  //CAT4.SC - OTB - Kev
		blip_on_cat_mission4 = 1
	ENDIF
endif

lvar_int created_trucks
created_trucks = 0


// ************************************* MISSION LOOP *************************************
mission_cat_loop:

WAIT 0

get_game_timer game_timer

IF NOT IS_CHAR_DEAD	catalina
	IF IS_GROUP_MEMBER catalina players_group

		if locate_char_any_means_char_2d scplayer catalina 20.0 20.0 0
			if convo_flag < 3
				if cat_convo_lines[convo_flag] = 0
					++ convo_flag
					goto skip_dialogue_playing
				endif
				if convo_flag = 1
					if not is_char_in_any_car catalina
						if cat_counter = 1
						or cat_counter = 3
							goto skip_dialogue_playing
						endif
					else
						if convo_counter = 0
							convo_timer = game_timer - 10
						endif
					endif
				endif
				if convo_timer < game_timer
					if audio_line_is_active = 0
						if convo_flag = 0
							$audio_string    = $cat_convo1[convo_counter]	
							audio_sound_file = cat_convo1_sound[convo_counter]
							START_NEW_SCRIPT audio_line cat_convo1_ped[convo_counter] 0 1 audio_slot convo_counter
							if cat_convo1_ped[convo_counter] = catalina
								task_look_at_char catalina carl	3000
							endif
						endif
						if convo_flag = 1
							$audio_string    = $cat_convo2[convo_counter]	
							audio_sound_file = cat_convo2_sound[convo_counter]
							START_NEW_SCRIPT audio_line cat_convo2_ped[convo_counter] 0 1 audio_slot convo_counter
							if cat_convo2_ped[convo_counter] = catalina
								task_look_at_char catalina carl	3000
							endif
						endif
						if convo_flag = 2
							$audio_string    = $cat_convo3[convo_counter]	
							audio_sound_file = cat_convo3_sound[convo_counter]
							START_NEW_SCRIPT audio_line cat_convo3_ped[convo_counter] 0 1 audio_slot convo_counter
							if cat_convo3_ped[convo_counter] = catalina
								task_look_at_char catalina carl	3000
							endif
						endif
						if audio_slot = 1
							audio_slot = 2
						else
							audio_slot = 1
						endif
						convo_timer = game_timer + 500
						++ convo_counter
						if convo_counter < cat_convo_lines[convo_flag]
							CLEAR_MISSION_AUDIO audio_slot
							if convo_flag = 0
								LOAD_MISSION_AUDIO audio_slot cat_convo1_sound[convo_counter]
							endif
							if convo_flag = 1
								LOAD_MISSION_AUDIO audio_slot cat_convo2_sound[convo_counter]
							endif
							if convo_flag = 2
								LOAD_MISSION_AUDIO audio_slot cat_convo3_sound[convo_counter]
							endif
						else
							convo_counter = 0
							convo_timer = game_timer + 10000
							++ convo_flag
						endif
					endif
				endif
			endif
		endif
		skip_dialogue_playing:
		if cat_counter = 0
			if convo_flag = 1
				if blip_counter = 0
					IF convo_counter > 6//[5] CAT_BH	//CATALINA:	A liquor store in Blueberry.
						ADD_SPRITE_BLIP_FOR_COORD catX[1] catY[1] catZ[1] RADAR_SPRITE_CASH cat_mission1_blip  //CAT1.SC - LIQUOR - Neil
						blip_on_cat_mission1 = 1
						++ blip_counter
					ENDIF
				endif
				if blip_counter = 1
					IF convo_counter > 7//[6] CAT_BG	//CATALINA:	A bank in Palomino Creek.
						ADD_SPRITE_BLIP_FOR_COORD catX[2] catY[2] catZ[2] RADAR_SPRITE_CASH cat_mission2_blip  //CAT2.SC - BANK - ChrisR
						blip_on_cat_mission2 = 1
						++ blip_counter
					ENDIF
				endif
				if blip_counter = 2
					IF convo_counter > 8//[7] CAT_BF	//CATALINA:	A gas station in Dillimore.
						ADD_SPRITE_BLIP_FOR_COORD catX[3] catY[3] catZ[3] RADAR_SPRITE_CASH cat_mission3_blip  //CAT3.SC - GAS STATION - ChrisM
						blip_on_cat_mission3 = 1
						++ blip_counter
					ENDIF
				endif
				if blip_counter = 3
					IF convo_counter > 9//[8] CAT_BI	//CATALINA:	And a betting shop in Montgomery.
						ADD_SPRITE_BLIP_FOR_COORD catX[4] catY[4] catZ[4] RADAR_SPRITE_CASH cat_mission4_blip  //CAT4.SC - OTB - Kev
						blip_on_cat_mission4 = 1
						++ blip_counter
					ENDIF
				endif
			endif
		endif
	
		IF IS_PS2_KEYBOARD_KEY_PRESSED PS2_KEY_S //IS_BUTTON_PRESSED PAD1 RIGHTSHOCK//DEBUG!!!! WARP
			IF flag_cat_mission_counter = 0
				CLEAR_AREA catX[1] catY[1] catZ[1] 0.5 0
				SET_CHAR_COORDINATES scplayer catX[1] catY[1] catZ[1] 
				x = catX[1] - 1.0
				SET_CHAR_COORDINATES catalina x catY[1] catZ[1] 
				LOAD_SCENE catX[1] catY[1] catZ[1]
			ENDIF
			IF flag_cat_mission_counter = 1
				CLEAR_AREA catX[2] catY[2] catZ[2] 0.5 0
				SET_CHAR_COORDINATES scplayer catX[2] catY[2] catZ[2] 
				x =  catX[2] - 1.0
				SET_CHAR_COORDINATES catalina x catY[2] catZ[2] 
				LOAD_SCENE catX[2] catY[2] catZ[2]
			ENDIF
			IF flag_cat_mission_counter = 2
				CLEAR_AREA catX[3] catY[3] catZ[3] 0.5 0
				SET_CHAR_COORDINATES scplayer catX[3] catY[3] catZ[3] 
				x =  catX[3] - 1.0
				SET_CHAR_COORDINATES catalina x catY[3] catZ[3] 
				LOAD_SCENE catX[3] catY[3] catZ[3]
			ENDIF
			IF flag_cat_mission_counter = 3
				CLEAR_AREA catX[4] catY[4] catZ[4] 0.5 0
				SET_CHAR_COORDINATES scplayer catX[4] catY[4] catZ[4] 
				x =  catX[4] - 1.0
				SET_CHAR_COORDINATES catalina x catY[4] catZ[4] 
				LOAD_SCENE catX[4] catY[4] catZ[4]
			ENDIF
		ENDIF

		IF flag_cat_mission1_passed = 0
			if blip_on_cat_mission1 = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer catX[1] catY[1] catZ[1] 3.0 3.0 3.0 1
					SET_PLAYER_CONTROL player1 OFF
					PRINT_BIG CAT_1 1000 2 //"Catalina mission 1" //NEIL //Rob liquor store
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					load_and_launch_catalina_mission = 0
					RETURN
				ENDIF	
			ENDIF
		ENDIF
		IF flag_cat_mission2_passed = 0
			if blip_on_cat_mission2 = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer catX[2] catY[2] catZ[2] 3.0 3.0 3.0 1
					SET_PLAYER_CONTROL player1 OFF
					PRINT_BIG CAT_2 1000 2 //"Catalina mission 2" //Kev B //Rob bank
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					load_and_launch_catalina_mission = 1
					RETURN
				ENDIF
			ENDIF	
		ENDIF
		IF flag_cat_mission3_passed = 0
			if blip_on_cat_mission3 = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer catX[3] catY[3] catZ[3] 3.0 3.0 3.0 1
					SET_PLAYER_CONTROL player1 OFF
					PRINT_BIG CAT_3 1000 2 //"Catalina mission 3" //CHRIS M //Rob gas station
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					load_and_launch_catalina_mission = 2
					RETURN
				ENDIF	
			ENDIF
		ENDIF

		IF flag_cat_mission4_passed = 0
			if blip_on_cat_mission4 = 1
				IF LOCATE_CHAR_ANY_MEANS_3D scplayer catX[4] catY[4] catZ[4] 3.0 3.0 3.0 1
					SET_PLAYER_CONTROL player1 OFF
					PRINT_BIG CAT_4 1000 2 //"Catalina mission 4" //KEVIN W //Rob OTB
					DO_FADE 500 FADE_OUT
					WHILE GET_FADING_STATUS
						WAIT 0
					ENDWHILE
					load_and_launch_catalina_mission = 3
					RETURN
				ENDIF	
			ENDIF	
		ENDIF						
	else
		if cat_in_group_flag = 1
			print_now catleft 5000 1//~s~You left ~b~Catalina~s~ behind. Go back and pick her up.
			remove_blip	cat_mission1_blip
			remove_blip	cat_mission2_blip
			remove_blip	cat_mission3_blip
			remove_blip	cat_mission4_blip
			add_blip_for_char catalina mission_blip
			set_blip_as_friendly mission_blip true
			++ cat_in_group_flag
		endif
		if cat_in_group_flag = 2
			if locate_char_any_means_char_2d scplayer catalina 10.0 10.0 0
				remove_blip	mission_blip
				IF NOT IS_GROUP_MEMBER catalina players_group  
					SET_GROUP_MEMBER players_group catalina
				ENDIF
				if blip_on_cat_mission1 = 1
					ADD_SPRITE_BLIP_FOR_COORD catX[1] catY[1] catZ[1] RADAR_SPRITE_CASH cat_mission1_blip  //CAT1.SC - LIQUOR - Neil
				endif
				if blip_on_cat_mission2 = 1
					ADD_SPRITE_BLIP_FOR_COORD catX[2] catY[2] catZ[2] RADAR_SPRITE_CASH cat_mission2_blip  //CAT2.SC - BANK - ChrisR
				endif
				if blip_on_cat_mission3 = 1
					ADD_SPRITE_BLIP_FOR_COORD catX[3] catY[3] catZ[3] RADAR_SPRITE_CASH cat_mission3_blip  //CAT3.SC - GAS STATION - ChrisM
				endif
				if blip_on_cat_mission4 = 1
					ADD_SPRITE_BLIP_FOR_COORD catX[4] catY[4] catZ[4] RADAR_SPRITE_CASH cat_mission4_blip  //CAT4.SC - OTB - Kev
				endif
				cat_in_group_flag = 1
			endif
		endif
	ENDIF
ELSE
	print_now catdead 5000 1//~r~Catalina is dead!
	goto mission_cat_failed
ENDIF

if locate_char_any_means_2d scplayer 660.0 -585.0 200.0 200.0 0
	if created_trucks = 0
		request_model PETRO
		request_model PETROTR
		if has_model_loaded PETRO
		and has_model_loaded PETROTR
			LVAR_INT c3_truck c3_trailer
			CREATE_CAR PETRO 657.49 -585.13 16.36 c3_truck
			SET_CAR_HEADING c3_truck 170.66
			SET_CAN_BURST_CAR_TYRES c3_truck FALSE
			SET_CAR_PROOFS c3_truck TRUE FALSE FALSE FALSE FALSE
			SET_LOAD_COLLISION_FOR_CAR_FLAG c3_truck FALSE
			lock_car_doors c3_truck carlock_locked

			CREATE_CAR PETROTR 667.49 -583.13 16.36 c3_trailer
			SET_CAR_HEADING c3_trailer 184.66
			SET_CAR_HEALTH c3_trailer 1250
			SET_CAR_PROOFS c3_trailer TRUE TRUE TRUE TRUE TRUE
			SET_CAN_BURST_CAR_TYRES c3_trailer FALSE
			SET_LOAD_COLLISION_FOR_CAR_FLAG c3_trailer FALSE
			lock_car_doors c3_trailer carlock_locked
			created_trucks = 1
		endif
	endif
else
	if created_trucks = 1
		delete_car c3_truck
		delete_car c3_trailer
		mark_model_as_no_longer_needed PETRO
		mark_model_as_no_longer_needed PETROTR
		created_trucks = 0
	endif
endif


GOTO mission_cat_loop


	
// ************************************ MISSION FAILED ************************************
mission_cat_failed:
PRINT_BIG M_FAIL 5000 1
remove_char_elegantly catalina
RETURN

   


// *********************************** MISSION CLEANUP ************************************
mission_cat_cleanup:

REMOVE_BLIP mission_blip

REMOVE_BLIP cat_mission1_blip
REMOVE_BLIP cat_mission2_blip
REMOVE_BLIP cat_mission3_blip
REMOVE_BLIP cat_mission4_blip

UNLOAD_SPECIAL_CHARACTER 5
mark_model_as_no_longer_needed PETRO
mark_model_as_no_longer_needed PETROTR

flag_player_on_mission = 0

MISSION_HAS_FINISHED
if load_and_launch_catalina_mission > -1
	do_fade 0 fade_out
endif
RETURN
}		













////Misc
//
////start of mission
//CATALINA:	CAT1_BC	Let's go, yeehaa!
//CATALINA:	CAT1_BA	Carl, you drive, I'll shoot!
//CARL:		CAT2_GC	Whatever, lets roll!
//
////player crashes
//CATALINA:	CAT1_CC	You're driving like an old lady!
//CATALINA:	CAT3_EG	I thought you said you could drive!
//CATALINA:	BCS5_BA	Hey, Carl, you drive like a drunken camel!
//
////player gets wanted level
//CATALINA:	CAT1_EC	Your pissing around has brought the cops!
//CATALINA:	CAT4_ED	Idiota!
//
////player leaves catalina behind
//CATALINA:	CAT1_GA	Don't leave me behind, Carl Johnson!
//CATALINA:	CAT1_GB	You leave me, I CASTRATE YOU!
//CATALINA:	CAT1_GC	Get back here, you useless bastard!
//CATALINA:	CAT1_GD	Carl Johnson, don't you leave me!
//
//	CATALINA:	CAT3_EF	Where have you been?
//
////catalina impressed
//CATALINA:	CAT2_CA	You impress me, Carl Johnson!
//CATALINA:	CAT2_CC	I think I like you!
//CATALINA:	BCS5_AE	I have never felt so satisfied!
//CATALINA:	BCS5_BE	At last I have found a man to satisfy me!
//
////player taking too long to get to a job
//CATALINA:	CAT4_KB	You slow bastard!
//CATALINA:	CAT4_KC	Are you afraid of speed, Carl?
//CATALINA:	CAT4_KD	Stop getting lost!
//CATALINA:	CAT4_KE	You're too laid back, you lazy slob!
//CATALINA:	CAT4_KG	More speed, less idiota!
//CATALINA:	CAT4_KA	What's keeping you?
//CATALINA:	CAT2_EA	Move it!
//CATALINA:	CAT2_EC	We haven't got all fucking day!
//CATALINA:	CAT3_EB	What is keeping you?
//CATALINA:	CAT3_EC	C'mon, Carl, move it!
//CATALINA:	CAT_KQ		Carl, you are really boring me now.
//CATALINA:	CAT_NA		Come on, drive!
//
//	CARL:		CAT3_BB	I'm on it, I got it!
//	CARL:		CAT4_CB	Yes'm, Miss Catalina, Miss.
//	CARL:		CAT_CH	I ain't listening to you.
//
//CATALINA:	CAT4_KF	Pick it up a little, you retarded asshole!
//	CARL:		CAT4_DE	Don't push me!
//
//CATALINA:	CAT2_EB	Stop playing with your balls and hurry up!
//
//CATALINA:	CAT2_HI	Enough! I am not speaking to you any more!
//
//
////player attacks catalina
//CATALINA:	CAT2_FC	Now I kill you!
//
//
////player getting close to a location
//CATALINA:	CAT3_ED	Not far now!
//CATALINA:	CAT3_HB	That's the place, up ahead!







