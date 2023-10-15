MISSION_START

SCRIPT_NAME	CELLFON

// MOBILE PHONECALL
VAR_INT	flag_mob_la1[10] flag_mob_LA2[4] flag_mob_cat[10] flag_mob_random[12] flag_mob_vegas[17] flag_mob_sanfran[12] loan_shark_reminder loan_shark_hitmen
VAR_INT ring_a_ding_ding mobile_pause // flag_mobile_dialogue
VAR_INT terminate_cat_calls	call_number 		
VAR_INT flag_mobile_timer flag_player_answered_phone Thekeycard_contact_blip
VAR_INT flag_new_cont players_skipping_the_call	skip_the_mobile_call millies_like_stat
VAR_INT call_delay cell_index_start	cell_index_end audio_slot_mobile
VAR_INT mobile_audio_labels[20]	current_visible_area_cell
VAR_TEXT_LABEL $mobile_print_labels[20]
VAR_FLOAT Returnedfat
   
// SET FLAGS AND VARIABLES
skip_the_mobile_call = 0
terminate_cat_calls = 0
flag_new_cont = 0
flag_mobile_timer = 0
flag_player_answered_phone = 0
call_delay = 20000


SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested

//CATALINA 
CONST_INT CATALINA_MOBILE_CUT2 0
CONST_INT CATALINA_MOBILE_CUT3 1
CONST_INT CATALINA_MOBILE_CUT4 2
CONST_INT CATALINA_MOBILE_CUT5 3
CONST_INT CATALINA_MOBILE_CUT6 4
CONST_INT CATALINA_MOBILE_CUT7 5
//OGLOC
CONST_INT OGLOC_MOBILE_CALL3 6
//CESAR
CONST_INT CESAR_MOBILE_CALL1 7
CONST_INT CESAR_MOBILE_CALL4 8
CONST_INT CRASH_MOBILE_CALL3 9
CONST_INT CESAR_MOBILE_CALL9 10
//KENDAL
CONST_INT KENDAL_MOBILE_CALL1 11
//CRASH
CONST_INT CRASH_MOBILE_CALL1 12
CONST_INT CRASH_MOBILE_CALL2 13
//SMOKE
CONST_INT SMOKE_MOBILE_CALL1 14
//TRUTH
CONST_INT TRUTH_MOBILE_CALL1 15
CONST_INT TRUTH_MOBILE_CALL2 16
CONST_INT TRUTH_MOBILE_CALL3 17
//SWEET
CONST_INT SWEET_MOBILE_CALL1 18
CONST_INT SWEET_MOBILE_CALL2 19
CONST_INT SWEET_MOBILE_CALL3 20
CONST_INT SWEET_MOBILE_CALL4 21
CONST_INT SWEET_MOBILE_CALL5 22
CONST_INT SWEET_MOBILE_CALL6 23
CONST_INT SWEET_MOBILE_CALL7 24
CONST_INT SWEET_MOBILE_CALL8 25
CONST_INT SWEET_MOBILE_CALL9 26
CONST_INT SWEET_MOBILE_CALL10 27
CONST_INT SWEET_MOBILE_CALL11 28
//ZERO
CONST_INT ZERO_MOBILE_CALL1 29
CONST_INT ZERO_MOBILE_CALL2 30
//JETHRO
CONST_INT JETHRO_MOBILE_CALL1 31
CONST_INT JETHRO_MOBILE_CALL2 32
CONST_INT JETHRO_MOBILE_CALL3 33
//WUZI
CONST_INT WUZI_MOBILE_CALL1	34
CONST_INT WUZI_MOBILE_CALL3 35
CONST_INT WUZI_MOBILE_CALL4	36
CONST_INT WUZI_MOBILE_CALL5	37
CONST_INT WUZI_MOBILE_CALL6 38
CONST_INT WUZI_MOBILE_CALL7	39
CONST_INT WUZI_MOBILE_CALL8 40
//TORENO 
CONST_INT TORENO_MOBILE_CALL1 41
CONST_INT TORENO_MOBILE_CALL2 42
CONST_INT TORENO_MOBILE_CALL3 43
CONST_INT TORENO_MOBILE_CALL4 44
CONST_INT TORENO_MOBILE_CALL5 45
CONST_INT TORENO_MOBILE_CALL6 46
//KENT PAUL
CONST_INT KENT_MOBILE_CALL1	47
//ROSENBURG
CONST_INT ROSE_MOBILE_CALL1	48
CONST_INT ROSE_MOBILE_CALL2	49
CONST_INT ROSE_MOBILE_CALL3	50
//SALVATORY
CONST_INT SALV_MOBILE_CALL1	51
CONST_INT SALV_MOBILE_CALL2	52
//MILLIE KEYCARD
CONST_INT MILLIE_KEY_MOBILE_CALL1 53

CONST_INT LOANSHARK_CALL1 54
CONST_INT LOANSHARK_CALL2 55
CONST_INT LOANSHARK_CALL3 56
CONST_INT LOANSHARK_CALL4 57
CONST_INT LOANSHARK_CALL5 58
CONST_INT LOANSHARK_CALL6 59
CONST_INT LOANSHARK_CALL7 60
CONST_INT LOANSHARK_CALL8 61
CONST_INT LOANSHARK_CALL9 62
CONST_INT LOANSHARK_CALL10 63
CONST_INT LOANSHARK_CALL11 64
CONST_INT LOANSHARK_CALL12 65
CONST_INT LOANSHARK_CALL13 66
CONST_INT LOANSHARK_CALL14 67
CONST_INT LOANSHARK_CALL15 68
CONST_INT LOANSHARK_CALL16 69

//GIRLFRIENDS
CONST_INT COOCHIE_MOBILE  	70
CONST_INT COOCHIE_MOBILE2 	71
CONST_INT COOCHIE_MOBILE3 	72
CONST_INT COOCHIE_MOBILE4 	73
CONST_INT COOCHIE_MOBILE5 	74
CONST_INT COOCHIE_MOBILE6 	75
CONST_INT COOCHIE_MOBILE7 	76
CONST_INT COOCHIE_MOBILE8 	77

CONST_INT MICHELLE_MOBILE 	80
CONST_INT MICHELLE_MOBILE2	81
CONST_INT MICHELLE_MOBILE3	82
CONST_INT MICHELLE_MOBILE4	83
CONST_INT MICHELLE_MOBILE5	84
CONST_INT MICHELLE_MOBILE6	85
CONST_INT MICHELLE_MOBILE7	86
CONST_INT MICHELLE_MOBILE8	87

CONST_INT KYLIE_MOBILE	  	90
CONST_INT KYLIE_MOBILE2		91
CONST_INT KYLIE_MOBILE3		92
CONST_INT KYLIE_MOBILE4		93
CONST_INT KYLIE_MOBILE5		94
CONST_INT KYLIE_MOBILE6		95
CONST_INT KYLIE_MOBILE7		96
CONST_INT KYLIE_MOBILE8		97

CONST_INT BARBARA_MOBILE  	100
CONST_INT BARBARA_MOBILE2	101
CONST_INT BARBARA_MOBILE3	102
CONST_INT BARBARA_MOBILE4	103
CONST_INT BARBARA_MOBILE5	104
CONST_INT BARBARA_MOBILE6	105
CONST_INT BARBARA_MOBILE7	106
CONST_INT BARBARA_MOBILE8	107

CONST_INT SUZIE_MOBILE	  	110
CONST_INT SUZIE_MOBILE2		111
CONST_INT SUZIE_MOBILE3		112
CONST_INT SUZIE_MOBILE4		113
CONST_INT SUZIE_MOBILE5		114
CONST_INT SUZIE_MOBILE6		115
CONST_INT SUZIE_MOBILE7		116
CONST_INT SUZIE_MOBILE8		117

CONST_INT MILLIE_MOBILE	  	120
CONST_INT MILLIE_MOBILE2	121
CONST_INT MILLIE_MOBILE3	122
CONST_INT MILLIE_MOBILE4	123
CONST_INT MILLIE_MOBILE5	124
CONST_INT MILLIE_MOBILE6	125
CONST_INT MILLIE_MOBILE7	126
CONST_INT MILLIE_MOBILE8	127

CONST_INT COOCHIE_DUMP	  	130
CONST_INT MICHELLE_DUMP	  	131
CONST_INT KYLIE_DUMP	  	132
CONST_INT BARBARA_DUMP	  	133
CONST_INT SUZIE_DUMP	  	134
CONST_INT MILLIE_DUMP	  	135

mission_start_cell_phone:

{
	
}

MISSION_END

{

mobile_chat_switch:

IF call_number < COOCHIE_MOBILE
 
	SWITCH call_number

	// ***************************************SWEET*******************************************************************************************
	CASE SWEET_MOBILE_CALL1
		$mobile_print_labels[0] = &MSWE14A	//Whattup, Sweet?
		$mobile_print_labels[1] = &MSWE14B  //Yo, check it, we got a problem.
		$mobile_print_labels[2] = &MSWE14C	//Some punk ass base head fool has been pushing to his Grove brothers!
		$mobile_print_labels[3] = &MSWE14D	//What? Who?
		$mobile_print_labels[4] = &MSWE14E	//Word is he’s been buying his stuff from some Balla over Glen Park.
		$mobile_print_labels[5] = &MSWE14F	//He knows word’s out and now he’s hiding with his new Balla friends.
		$mobile_print_labels[6] = &MSWE14G	//Shit, man, how are we gonna get to him?
		$mobile_print_labels[7] = &MSWE14H	//It’s time for you to announce your return, CJ!
		$mobile_print_labels[8] = &MSWE14J	//Mess up Glen Park and that turncoat coward will lose his nerve and make a break for it!
		$mobile_print_labels[9] = &MSWE14K	//Ok, I’ll come pick you up.
		$mobile_print_labels[10] = &MSWE14L	//Uh-uh, this is your gig, CJ. You need to establish your rep.
		$mobile_print_labels[11] = &MSWE14N	//Get over to AmmuNation and sort yourself with a proper strap. 	
				
		mobile_audio_labels[0] = SOUND_MSWE14A	//Whattup, Sweet?
		mobile_audio_labels[1] = SOUND_MSWE14B	//Yo, check it, we got a problem.
		mobile_audio_labels[2] = SOUND_MSWE14C	//Some punk ass base head fool has been pushing to his Grove brothers!
		mobile_audio_labels[3] = SOUND_MSWE14D	//What? Who?
		mobile_audio_labels[4] = SOUND_MSWE14E	//Word is he’s been buying his stuff from some Balla over Glen Park.
		mobile_audio_labels[5] = SOUND_MSWE14F	//He knows word’s out and now he’s hiding with his new Balla friends.
		mobile_audio_labels[6] = SOUND_MSWE14G	//Shit, man, how are we gonna get to him?
		mobile_audio_labels[7] = SOUND_MSWE14H	//It’s time for you to announce your return, CJ!
		mobile_audio_labels[8] = SOUND_MSWE14J	//Mess up Glen Park and that turncoat coward will lose his nerve and make a break for it.
		mobile_audio_labels[9] = SOUND_MSWE14K	//Ok, I’ll come pick you up.
		mobile_audio_labels[10] = SOUND_MSWE14L	//Uh-uh, this is your gig, CJ. You need to establish your rep.
		mobile_audio_labels[11] = SOUND_MSWE14N	//Get over to AmmuNation and sort yourself with a proper strap.

		cell_index_end = 11
	BREAK

	CASE SWEET_MOBILE_CALL2
		$mobile_print_labels[0] = &MSWE10A	//Carl Johnson, man of the people!
		//$mobile_print_labels[1] = &MSWE10B	//So I understand.
		$mobile_print_labels[1] = &MSWE10D	//Thought I better explain some shit.
		$mobile_print_labels[2] = &MSWE10C	//Sweet? Hey, whattup?
		$mobile_print_labels[3] = &MSWE10E	//Since you been away, shit has changed ‘round here.
		$mobile_print_labels[4] = &MSWE10F	//Grove Street Families ain’t so big and ain’t too happy no more.
		$mobile_print_labels[5] = &MSWE10G	//Seville Boulevard Families and Temple Drive Families got bad blood and split with the Grove.
		$mobile_print_labels[6] = &MSWE10H	//Now we so busy set tripping, Ballas and Vagos have taken over, so watch yo’self out there.
		//$mobile_print_labels[8] = &MSWE10J	//Just coz they wearing greens, don’t mean they gonna be best friends, you down?
		$mobile_print_labels[7] = &MSWE10K	//Yeah, I’m down.
		$mobile_print_labels[8] = &MSWE10L	//Thanks for the heads up.
		$mobile_print_labels[9] = &MSWE10N	//Don’t mention it.

		mobile_audio_labels[0] = SOUND_MSWE10A	//Carl Johnson, man of the people!	
		//mobile_audio_labels[1] = SOUND_MSWE10B	//So I understand.
		mobile_audio_labels[1] = SOUND_MSWE10D	//Thought I better explain some shit.
		mobile_audio_labels[2] = SOUND_MSWE10C	//Sweet? Hey, whattup?
		mobile_audio_labels[3] = SOUND_MSWE10E	//Since you been away, shit has changed ‘round here.
		mobile_audio_labels[4] = SOUND_MSWE10F	//Grove Street Families ain’t so big and ain’t too happy no more.
		mobile_audio_labels[5] = SOUND_MSWE10G	//Seville Boulevard Families and Temple Drive Families got bad blood and split with the Grove.
		mobile_audio_labels[6] = SOUND_MSWE10H	//Now we so busy set tripping, Ballas and Vagos have taken over, so watch yo’self out there.
		//mobile_audio_labels[8] = SOUND_MSWE10J	//Just coz they wearing greens, don’t mean they gonna be best friends, you down?
		mobile_audio_labels[7] = SOUND_MSWE10K	//Yeah, I’m down.
		mobile_audio_labels[8] = SOUND_MSWE10L	//Thanks for the heads up.
		mobile_audio_labels[9] = SOUND_MSWE10N	//Don’t mention it.

		cell_index_end = 9
	BREAK

	CASE SWEET_MOBILE_CALL3
		$mobile_print_labels[0] = &MSWE06A	//Yo?
		$mobile_print_labels[1] = &MSWE06B	//Hey, CJ, it’s Sweet.
		$mobile_print_labels[2] = &MSWE06C	//Whassup?
		$mobile_print_labels[3] = &MSWE06D	//If you don’t respect your body, ain’t nobody going to respect you!
		$mobile_print_labels[4] = &MSWE06E	//You’re too skinny, CJ, you need to pack on some muscle!
		$mobile_print_labels[5] = &MSWE06F	//If I wanted nagging, I’d buy a clockwork wife!
		$mobile_print_labels[6] = &MSWE06G	//Just looking out for you, homie.
		$mobile_print_labels[7] = &MSWE06H	//It’s all show and respect, you lnow?
		$mobile_print_labels[8] = &MSWE06J	//Yeah, I guess.
		$mobile_print_labels[9] = &MSWE06K	//There’s a gym I use a couple of blocks out from the Grove.
		$mobile_print_labels[10] = &MSWE06L	//Go check it out and get yo’self a gangsta’s physique.
		$mobile_print_labels[11] = &MSWE06N	//I’ll scope it out.
		$mobile_print_labels[12] = &MSWE06M	//Later, man.

		mobile_audio_labels[0] = SOUND_MSWE06A	//Yo?
		mobile_audio_labels[1] = SOUND_MSWE06B	//Hey, CJ, it’s Sweet.
		mobile_audio_labels[2] = SOUND_MSWE06C	//Whassup?
		mobile_audio_labels[3] = SOUND_MSWE06D	//If you don’t respect your body, ain’t nobody going to respect you!
		mobile_audio_labels[4] = SOUND_MSWE06E	//You’re too skinny, CJ, you need to pack on some muscle!
		mobile_audio_labels[5] = SOUND_MSWE06F	//If I wanted nagging, I’d buy a clockwork wife!
		mobile_audio_labels[6] = SOUND_MSWE06G	//Just looking out for you, homie.
		mobile_audio_labels[7] = SOUND_MSWE06H	//It’s all show and respect, you lnow?
		mobile_audio_labels[8] = SOUND_MSWE06J	//Yeah, I guess.
		mobile_audio_labels[9] = SOUND_MSWE06K	//There’s a gym I use a couple of blocks out from the Grove.
		mobile_audio_labels[10] = SOUND_MSWE06L	//Go check it out and get yo’self a gangsta’s physique.
		mobile_audio_labels[11] = SOUND_MSWE06N	//I’ll scope it out.
		mobile_audio_labels[12] = SOUND_MSWE06M	//Later, man.


		cell_index_end = 12
	BREAK

	CASE SWEET_MOBILE_CALL4

		$mobile_print_labels[0] = &MSWE05A	//Whassup?
		$mobile_print_labels[1] = &MSWE05B	//Your fat to muscle ratio, that’s what’s up!
		$mobile_print_labels[2] = &MSWE05C	//Sweet? What you on my back for now?
		$mobile_print_labels[3] = &MSWE05D	//I know we’re mourning moms and all,
		$mobile_print_labels[4] = &MSWE05E	//But there’s no need to let yourself go, CJ.
		$mobile_print_labels[5] = &MSWE05F	//So I put a little weight on, but….
		$mobile_print_labels[6] = &MSWE05G	//There’s a gym I go to just up a couple of blocks from the Grove.
		$mobile_print_labels[7] = &MSWE05H	//Go check it out.
		$mobile_print_labels[8] = &MSWE05J	//Ok, I’m gonna see what’s up, man.
		$mobile_print_labels[9] = &MSWE05K	//But with the fat jokes, you’re gonna give me a complex.

		mobile_audio_labels[0] = SOUND_MSWE05A	//Whassup?
		mobile_audio_labels[1] = SOUND_MSWE05B	//Your fat to muscle ratio, that’s what’s up! 
		mobile_audio_labels[2] = SOUND_MSWE05C	//Sweet? What you on my back for now?
		mobile_audio_labels[3] = SOUND_MSWE05D	//I know we’re mourning moms and all,
		mobile_audio_labels[4] = SOUND_MSWE05E	//But there’s no need to let yourself go, CJ.
		mobile_audio_labels[5] = SOUND_MSWE05F	//So I put a little weight on, but….
		mobile_audio_labels[6] = SOUND_MSWE05G	//There’s a gym I go to just up a couple of blocks from the Grove.
		mobile_audio_labels[7] = SOUND_MSWE05H	//Go check it out.
		mobile_audio_labels[8] = SOUND_MSWE05J	//Ok, I’m gonna see what’s up, man.
		mobile_audio_labels[9] = SOUND_MSWE05K	//But with the fat jokes, you’re gonna give me a complex.

		cell_index_end = 9
	BREAK
	
	CASE SWEET_MOBILE_CALL5	//NEED TO ADD THIS*******************************************************************************************
		$mobile_print_labels[0] = &MSWE08A	//Whattup, bro?
		$mobile_print_labels[1] = &MSWE08B	//CJ, I been thinking.
		$mobile_print_labels[2] = &MSWE08C	//The city’s big, but it ain’t that big.
		$mobile_print_labels[3] = &MSWE08D	//Some fools know where Smoke’s hiding.
		$mobile_print_labels[4] = &MSWE08E	//but as long as the Grove Street Families don’t rule the streets, 
		$mobile_print_labels[5] = &MSWE08F	//his money is going to mean more than our rep.
		$mobile_print_labels[6] = &MSWE08G	//What you got in mind?
		$mobile_print_labels[7] = &MSWE08H	//We gotta hit those Ballas and Vagos with everything,
		$mobile_print_labels[8] = &MSWE08J	//Hit every neighborhood they got!
		$mobile_print_labels[9] = &MSWE08K	//If it’s the only way, I’m down for that!
		$mobile_print_labels[10] = &MSWE08L	//I’m gonna see what I can find out on the streets.
		$mobile_print_labels[11] = &MSWE08N	//Big love, bro.
		$mobile_print_labels[12] = &MSWE08M	//Big love, yo.

		mobile_audio_labels[0] = SOUND_MSWE08A	//Whattup, bro?
		mobile_audio_labels[1] = SOUND_MSWE08B	//CJ, I been thinking.
		mobile_audio_labels[2] = SOUND_MSWE08C	//The city’s big, but it ain’t that big.
		mobile_audio_labels[3] = SOUND_MSWE08D	//Some fools know where Smoke’s hiding.
		mobile_audio_labels[4] = SOUND_MSWE08E	//but as long as the Grove Street Families don’t rule the streets,
		mobile_audio_labels[5] = SOUND_MSWE08F	//his money is going to mean more than our rep.
		mobile_audio_labels[6] = SOUND_MSWE08G	//What you got in mind?	
		mobile_audio_labels[7] = SOUND_MSWE08H	//We gotta hit those Ballas and Vagos with everything,
		mobile_audio_labels[8] = SOUND_MSWE08J	//Hit every neighborhood they got!
		mobile_audio_labels[9] = SOUND_MSWE08K	//If it’s the only way, I’m down for that!
		mobile_audio_labels[10] = SOUND_MSWE08L	//I’m gonna see what I can find out on the streets.
		mobile_audio_labels[11] = SOUND_MSWE08N	//Big love, bro.
		mobile_audio_labels[12] = SOUND_MSWE08M	//Big love, yo.

		cell_index_end = 12
	BREAK
	

	CASE SWEET_MOBILE_CALL6
		$mobile_print_labels[0] = &MSWE09A	//Carl, it’s Sweet!
		$mobile_print_labels[1] = &MSWE09B	//Hey wassup, you find Smoke?
		$mobile_print_labels[2] = &MSWE09C	//After we dumped on those Ballas the Vagos started to speak.
		$mobile_print_labels[3] = &MSWE09E	//We’ve got him now!
		$mobile_print_labels[4] = &MSWE09F	//He’s hiding in Los Flores, or East Los Santos.
		$mobile_print_labels[5] = &MSWE09G	//Time to saddle up, CJ!
		$mobile_print_labels[6] = &MSWE09H	//Alright, I’ll get you, and then we’ll roll over there.

		mobile_audio_labels[0] = SOUND_MSWE09A	//Carl, it’s Sweet!
		mobile_audio_labels[1] = SOUND_MSWE09B	//Hey wassup, you find Smoke?
		mobile_audio_labels[2] = SOUND_MSWE09C	//After we dumped on those Ballas the Vagos started to speak.
		mobile_audio_labels[3] = SOUND_MSWE09E	//We’ve got him now!
		mobile_audio_labels[4] = SOUND_MSWE09F	//He’s hiding in Los Flores, or East Los Santos.
		mobile_audio_labels[5] = SOUND_MSWE09G	//Time to saddle up, CJ!
		mobile_audio_labels[6] = SOUND_MSWE09H	//Alright, I’ll get you, and then we’ll roll over there.

		cell_index_end = 6
	BREAK

	CASE SWEET_MOBILE_CALL7
		$mobile_print_labels[0] = &MSWE11A	//Carl, it’s me.
		$mobile_print_labels[1] = &MSWE11B	//Sweet, what’s going on, man?
		$mobile_print_labels[2] = &MSWE11C	//What do you think is going on? I’m in a prison hospital wing, fool!
		$mobile_print_labels[3] = &MSWE11D	//I know, you alright?
		$mobile_print_labels[4] = &MSWE11E	//Not really, no.
		$mobile_print_labels[5] = &MSWE11F	//You gotta do ssomething, dude.
		$mobile_print_labels[6] = &MSWE11G	//I’m trying, man, just gotta make sure Kendl’s safe first.
		$mobile_print_labels[7] = &MSWE11H	//Ok, man, I gotta go...
		$mobile_print_labels[8] = &MSWE11J	//Don’t worry bro, I ain’t gonna leave you in there.
			 
		mobile_audio_labels[0] = SOUND_MSWE11A	//Carl, it’s me.
		mobile_audio_labels[1] = SOUND_MSWE11B	//Sweet, what’s going on, man?
		mobile_audio_labels[2] = SOUND_MSWE11C	//What do you think is going on? I’m in a prison hospital wing, fool!
		mobile_audio_labels[3] = SOUND_MSWE11D	//I know, you alright?
		mobile_audio_labels[4] = SOUND_MSWE11E	//Not really, no.
		mobile_audio_labels[5] = SOUND_MSWE11F	//You gotta do ssomething, dude.
		mobile_audio_labels[6] = SOUND_MSWE11G	//I’m trying, man, just gotta make sure Kendl’s safe first.
		mobile_audio_labels[7] = SOUND_MSWE11H	//Ok, man, I gotta go...
		mobile_audio_labels[8] = SOUND_MSWE11J	//Don’t worry bro, I ain’t gonna leave you in there.

		cell_index_end = 8
	BREAK

	CASE SWEET_MOBILE_CALL8
		$mobile_print_labels[0]  = &MSWE12A	//Carl, it’s your brother.
		$mobile_print_labels[1]  = &MSWE12B	//What’s up, man, you ok?
		$mobile_print_labels[2]  = &MSWE12C	//Not really.
		$mobile_print_labels[3]  = &MSWE12D	//I’m stuck in a cell between two lunatics.
		$mobile_print_labels[4]  = &MSWE12E	//People keep trying to jump me.
		$mobile_print_labels[5]  = &MSWE12F	//This ain’t cool, bro.
		$mobile_print_labels[6]  = &MSWE12G	//You looking after Kendl?
		$mobile_print_labels[7]  = &MSWE12H	//No, she’s looking after me!
		$mobile_print_labels[8]  = &MSWE12J	//Cool
		$mobile_print_labels[9]  = &MSWE12K	//But i’m gonna get you out of there!
		$mobile_print_labels[10] = &MSWE12L	//No you ain’t.
		$mobile_print_labels[11] = &MSWE12N	//Who do you think you are?
		$mobile_print_labels[12] = &MSWE12M	//I’m in for life, man, and I guess I’ve grown used to it now.
		$mobile_print_labels[13] = &MSWE12O	//You ain’t, man, I’m working shit out.
		$mobile_print_labels[14] = &MSWE12P	//Whatever it takes.
		$mobile_print_labels[15] = &MSWE12Q	//Nah, man, I’m through with hoping.
		$mobile_print_labels[16] = &MSWE12R	//Have a nice life, brother.
		$mobile_print_labels[17] = &MSWE12S	//No, wait, I got all kinds of shit going down, just hang in there!
		$mobile_print_labels[18] = &MSWE12T	//Sweet? SWEET?
		$mobile_print_labels[19] = &MSWE12U	//Shit....

		mobile_audio_labels[0]  = SOUND_MSWE12A	//Carl, it’s your brother.
		mobile_audio_labels[1]  = SOUND_MSWE12B	//What’s up, man, you ok?
		mobile_audio_labels[2]  = SOUND_MSWE12C	//Not really.
		mobile_audio_labels[3]  = SOUND_MSWE12D	//I’m stuck in a cell between two lunatics.
		mobile_audio_labels[4]  = SOUND_MSWE12E	//People keep trying to jump me.
		mobile_audio_labels[5]  = SOUND_MSWE12F	//This ain’t cool, bro.
		mobile_audio_labels[6]  = SOUND_MSWE12G	//You looking after Kendl?
		mobile_audio_labels[7]  = SOUND_MSWE12H	//No, she’s looking after me!
		mobile_audio_labels[8]  = SOUND_MSWE12J	//Cool
		mobile_audio_labels[9]  = SOUND_MSWE12K	//But i’m gonna get you out of there!
		mobile_audio_labels[10] = SOUND_MSWE12L	//No you ain’t.
		mobile_audio_labels[11] = SOUND_MSWE12N	//Who do you think you are?
		mobile_audio_labels[12] = SOUND_MSWE12M	//I’m in for life, man, and I guess I’ve grown used to it now.
		mobile_audio_labels[13] = SOUND_MSWE12O	//You ain’t, man, I’m working shit out.
		mobile_audio_labels[14] = SOUND_MSWE12P	//Whatever it takes.
		mobile_audio_labels[15] = SOUND_MSWE12Q	//Nah, man, I’m through with hoping.
		mobile_audio_labels[16] = SOUND_MSWE12R	//Have a nice life, brother.
		mobile_audio_labels[17] = SOUND_MSWE12S	//No, wait, I got all kinds of shit going down, just hang in there!
		mobile_audio_labels[18] = SOUND_MSWE12T	//Sweet? SWEET?
		mobile_audio_labels[19] = SOUND_MSWE12U	//Shit....

		cell_index_end = 19
	BREAK


	/*
	[MSWE02A]	’Sup?
	[MSWE02B]	CJ, Sweet.
	[MSWE02C]	I thought you were on top of that problem.
	[MSWE02D]	Which problem?
	[MSWE02E]	I got problems knee deep!
	[MSWE02F]	Damn yay pushers all over Ganton.
	[MSWE02G]	I thought you were going to put the time in for yo’hood.
	[MSWE02H]	Tss, don’t sweat me, bro.  I’m on it.
	[MSWE02J]	I hope so.
	[MSWE02K]	Lot of homies falling to the rock.
	[MSWE02L]	Like I said, I’m on it.

	[MSWE03A]	Yeah?
	[MSWE03B]	You seen your soldiers recently?
	[MSWE03C]	You seen what the streets are like?
	[MSWE03D]	What you talking about?
	[MSWE03E]	Damn base-heads, that’s what!
	[MSWE03F]	The Grove Street Families needs OG’s,
	[MSWE03G]	Not whacked-out chickenheads!
	[MSWE03H]	They won’t roll when they so high!
	[MSWE03J]	I’ll go see what I can see.
	[MSWE03K]	Well open your eyes this time!

	[MSWE04A]	Wassup?
	[MSWE04B]	What did I tell you about keeping an eye on yo’hood?
	[MSWE04C]	Yeah, my bad.  Shit been hectic, man.
	[MSWE04D]	Grove Streets full of  cluckers, man.
	[MSWE04E]	Ain’t an OG here ain’t high on yay!
	[MSWE04F]	No motherfucker going to bang when they fried!
	[MSWE04G]	I’ll tell you what.  I’m a do a hood patrol, find the pushers, and whack ‘em.
	[MSWE04H]	Damn straight you will!
	*/


	// ***************************************CATALINA*******************************************************************************************

	CASE CATALINA_MOBILE_CUT2
		//CATALINA MOBILE 2						
		$mobile_print_labels[0] =  &MCAT02A //Heeeello!
		$mobile_print_labels[1] =  &MCAT02B //Why you so cheery? You thought I was one of your cheap whores?
		$mobile_print_labels[2] =  &MCAT02C //Catalina, baby, you gotta chill the fuck out and –
		$mobile_print_labels[3] =  &MCAT02D //I’ll chill out when you get here!
		$mobile_print_labels[4] =  &MCAT02E //You better hope I don’t make testicle kebabs when you arrive!
		$mobile_print_labels[5] =  &MCAT02F //Look, this ain’t-
		$mobile_print_labels[6] =  &MCAT02G //No more talk!
		$mobile_print_labels[7] =  &MCAT02H //Get your ass up here, now!

		mobile_audio_labels[0] = SOUND_MCAT02A //Heeeello!
		mobile_audio_labels[1] = SOUND_MCAT02B //Why you so cheery? You thought I was one of your cheap whores?
		mobile_audio_labels[2] = SOUND_MCAT02C //Catalina, baby, you gotta chill the fuck out and –
		mobile_audio_labels[3] = SOUND_MCAT02D //I’ll chill out when you get here!
		mobile_audio_labels[4] = SOUND_MCAT02E //You better hope I don’t make testicle kebabs when you arrive!
		mobile_audio_labels[5] = SOUND_MCAT02F //Look, this ain’t-
		mobile_audio_labels[6] = SOUND_MCAT02G //No more talk!
		mobile_audio_labels[7] = SOUND_MCAT02H //Get your ass up here, now!

		cell_index_end = 7
	BREAK
		
	CASE CATALINA_MOBILE_CUT3
		//CATALINA MOBILE 3						
		$mobile_print_labels[0] = &MCAT03A //PIG!
		$mobile_print_labels[1] = &MCAT03B //Catalina? Is that you?

		mobile_audio_labels[0] = SOUND_MCAT03A //PIG!
		mobile_audio_labels[1] = SOUND_MCAT03B //Catalina? Is that you?

		cell_index_end = 1
	BREAK

	CASE CATALINA_MOBILE_CUT4
		//CATALINA MOBILE 4						
		$mobile_print_labels[0] = &MCAT04A	//Yo.
		$mobile_print_labels[1] = &MCAT04B	//Tiny-balled idiota!
		$mobile_print_labels[2] = &MCAT04C	//Catalina! Yo, I know it’s you. What’s eating you, baby?
		$mobile_print_labels[3] = &MCAT04D	//I don’t love you no more!
		$mobile_print_labels[4] = &MCAT04E	//Well hey, let’s just – <click>
		$mobile_print_labels[5] = &MCAT04F	//Hello?

		mobile_audio_labels[0] = SOUND_MCAT04A	//Yo.
		mobile_audio_labels[1] = SOUND_MCAT04B	//Tiny-balled idiota!
		mobile_audio_labels[2] = SOUND_MCAT04C	//Catalina! Yo, I know it’s you. What’s eating you, baby?
		mobile_audio_labels[3] = SOUND_MCAT04D	//I don’t love you no more!
		mobile_audio_labels[4] = SOUND_MCAT04E	//Well hey, let’s just – <click>
		mobile_audio_labels[5] = SOUND_MCAT04F	//Hello?

		cell_index_end = 5
	BREAK

	CASE CATALINA_MOBILE_CUT5	
		//CATALINA MOBILE 5						
		$mobile_print_labels[0] = &MCAT05B	//I know it’s you, you stinking perro!
		$mobile_print_labels[1] = &MCAT05C	//Look, just say what you gotta say. 
		$mobile_print_labels[2] = &MCAT05D	//I ain’t interested in these stupid games!
		$mobile_print_labels[3] = &MCAT05E	//Stupid games? This is my heart you play with!
		$mobile_print_labels[4] = &MCAT05F	//What? Look, you – <click>
		$mobile_print_labels[5] = &MCAT05G	//Damn! Gotta change my number!

		mobile_audio_labels[0] = SOUND_MCAT05B	//I know it’s you, you stinking perro!
		mobile_audio_labels[1] = SOUND_MCAT05C	//Look, just say what you gotta say. 
		mobile_audio_labels[2] = SOUND_MCAT05D	//I ain’t interested in these stupid games!
		mobile_audio_labels[3] = SOUND_MCAT05E	//Stupid games? This is my heart you play with!
		mobile_audio_labels[4] = SOUND_MCAT05F	//What? Look, you – <click>
		mobile_audio_labels[5] = SOUND_MCAT05G	//Damn! Gotta change my number!

		cell_index_end = 5
	BREAK

	CASE CATALINA_MOBILE_CUT6	
		//CATALINA MOBILE 6						
		$mobile_print_labels[0] = &MCAT06A	//Hello, Claude, baby. 
		$mobile_print_labels[1] = &MCAT06B	//I thought I call to say how much I love you 
		$mobile_print_labels[2] = &MCAT06C	//and how well endowered you are!
		$mobile_print_labels[3] = &MCAT06D	//Yo, Catalina, it’s Carl. 
		$mobile_print_labels[4] = &MCAT06E	//I think you got the wrong number.
		$mobile_print_labels[5] = &MCAT06F	//Oh, Carl!
		$mobile_print_labels[6] = &MCAT06G	//So sorry, it is such an easy mistake to make, 
		$mobile_print_labels[7] = &MCAT06H	//especially when I am so light-headed with love, and lust.
		$mobile_print_labels[8] = &MCAT06J	//Yeah, well I –
		$mobile_print_labels[9] = &MCAT06K	//No time – byeeee! <click>

		mobile_audio_labels[0] = SOUND_MCAT06A	//Hello, Claude, baby. 
		mobile_audio_labels[1] = SOUND_MCAT06B	//I thought I call to say how much I love you 
		mobile_audio_labels[2] = SOUND_MCAT06C	//and how well endowered you are!
		mobile_audio_labels[3] = SOUND_MCAT06D	//Yo, Catalina, it’s Carl. 
		mobile_audio_labels[4] = SOUND_MCAT06E	//I think you got the wrong number.
		mobile_audio_labels[5] = SOUND_MCAT06F	//Oh, Carl!
		mobile_audio_labels[6] = SOUND_MCAT06G	//So sorry, it is such an easy mistake to make, 
		mobile_audio_labels[7] = SOUND_MCAT06H	//especially when I am so light-headed with love, and lust.
		mobile_audio_labels[8] = SOUND_MCAT06J	//Yeah, well I –
		mobile_audio_labels[9] = SOUND_MCAT06K	//No time – byeeee! <click>

		cell_index_end = 9
	BREAK

	CASE CATALINA_MOBILE_CUT7	
		//CATALINA MOBILE 7						
		$mobile_print_labels[0] = &MCAT07A	//Yo.
		$mobile_print_labels[1] = &MCAT07B	//Heavy breathing.
		$mobile_print_labels[2] = &MCAT07C	//Hello?
		$mobile_print_labels[3] = &MCAT07D	//Yes, Claude, faster, harder, DEEPER!
		$mobile_print_labels[4] = &MCAT07E	//Oh, yes, yes, yes, YES!
		$mobile_print_labels[5] = &MCAT07F	//Catalina! You’re sick! Get help!
		$mobile_print_labels[6] = &MCAT07G	//And you, Carl, you are jealous! <click>
			
		mobile_audio_labels[0] = SOUND_MCAT07A	//Yo.
		mobile_audio_labels[1] = SOUND_MCAT07B	//Heavy breathing.
		mobile_audio_labels[2] = SOUND_MCAT07C	//Hello?
		mobile_audio_labels[3] = SOUND_MCAT07D	//Yes, Claude, faster, harder, DEEPER!
		mobile_audio_labels[4] = SOUND_MCAT07E	//Oh, yes, yes, yes, YES!
		mobile_audio_labels[5] = SOUND_MCAT07F	//Catalina! You’re sick! Get help!
		mobile_audio_labels[6] = SOUND_MCAT07G	//And you, Carl, you are jealous! <click>

		cell_index_end = 6
	BREAK

	// ***************************************OG LOC*******************************************************************************************



	CASE OGLOC_MOBILE_CALL3
		//MOB_04 OG LOC CALLS CJ TO PARTY	
		
		$mobile_print_labels[0] = &MLOC03A	//Hey CJ, word up, G!			
		$mobile_print_labels[1] = &MLOC03B	//Hey, Loc.
		$mobile_print_labels[2] = &MLOC03C	//Party is humping, we got a gang of crazy assed bitches,
		$mobile_print_labels[3] = &MLOC03D	//you coming over, homes?
		$mobile_print_labels[4] = &MLOC03E	//I don’t know. I had plans tonight.
		$mobile_print_labels[5] = &MLOC03F	//But we had a big disaster. I won’t be rappin’, my mic’s broken!
		$mobile_print_labels[6] = &MLOC03H	//I’ll be over right away.


		mobile_audio_labels[0] = SOUND_MLOC03A	//Hey CJ, word up, G!
		mobile_audio_labels[1] = SOUND_MLOC03B	//Hey, Loc.
		mobile_audio_labels[2] = SOUND_MLOC03C	//Party is humping, we got a gang of crazy assed bitches,
		mobile_audio_labels[3] = SOUND_MLOC03D	//you coming over, homes?
		mobile_audio_labels[4] = SOUND_MLOC03E	//I don’t know. I had plans tonight.
		mobile_audio_labels[5] = SOUND_MLOC03F	//But we had a big disaster. I won’t be rappin’, my mic’s broken!
		mobile_audio_labels[6] = SOUND_MLOC03H	//I’ll be over right away.

		cell_index_end = 6
	BREAK

	// ***************************************CESAR*******************************************************************************************
	CASE CESAR_MOBILE_CALL1
		$mobile_print_labels[0] = &MCES01A //	Hey, who’s this?
		$mobile_print_labels[1] = &MCES01B //	Ese, holmes, is me, Cesar Vialpando.
		$mobile_print_labels[2] = &MCES01C //	Hey, you seen Kendl?
		$mobile_print_labels[3] = &MCES01D //	Yeah, she around. I’m ringing to say you drive good and you like cars, eh.
		$mobile_print_labels[4] = &MCES01E //	Yeah, I guess, where’s this going?
		$mobile_print_labels[5] = &MCES01F //	You wanna make something, a little money?
		$mobile_print_labels[6] = &MCES01G //	Does the Pope shit in the woods?
		$mobile_print_labels[7] = &MCES01H //	I don’t know, but if you do want to make a little extra, there’s plenty money to be made racing.
		$mobile_print_labels[8] = &MCES01K //	You’re talking illegal street racing, yeah?
		$mobile_print_labels[9] = &MCES01L	//	Ci. No tacky shit, holmes, lowriders.
		$mobile_print_labels[10] = &MCES01M	//	Nice ones. It gotta be nice, or you don’t get in, eh.
		$mobile_print_labels[11] = &MCES01N	//	Ok, I’m in. When and where?
		$mobile_print_labels[12] = &MCES01O	//	Drop by place in El Corona, I’ll take you to the meet, vouch for you. 
		$mobile_print_labels[13] = &MCES01P	//	These guys can be very nervous with new racers, eh.

		mobile_audio_labels[0] = SOUND_MCES01A //	Hey, who’s this?
		mobile_audio_labels[1] = SOUND_MCES01B //	Ese, holmes, is me, Cesar Vialpando.
		mobile_audio_labels[2] = SOUND_MCES01C //	Hey, you seen Kendl?
		mobile_audio_labels[3] = SOUND_MCES01D //	Yeah, she around. I’m ringing to say you drive good and you like cars, eh.
		mobile_audio_labels[4] = SOUND_MCES01E //	Yeah, I guess, where’s this going?
		mobile_audio_labels[5] = SOUND_MCES01F //	You wanna make something, a little money?
		mobile_audio_labels[6] = SOUND_MCES01G //	Does the Pope shit in the woods?
		mobile_audio_labels[7] = SOUND_MCES01H //	I don’t know, but if you do want to make a little extra, there’s plenty money to be made racing.
		mobile_audio_labels[8] = SOUND_MCES01K //	You’re talking illegal street racing, yeah?
		mobile_audio_labels[9] = SOUND_MCES01L	//	Ci. No tacky shit, holmes, lowriders.
		mobile_audio_labels[10] = SOUND_MCES01M	//	Nice ones. It gotta be nice, or you don’t get in, eh.
		mobile_audio_labels[11] = SOUND_MCES01N	//	Ok, I’m in. When and where?
		mobile_audio_labels[12] = SOUND_MCES01O	//	Drop by place in El Corona, I’ll take you to the meet, vouch for you. 
		mobile_audio_labels[13] = SOUND_MCES01P	//	These guys can be very nervous with new racers, eh.

		cell_index_end = 13
	BREAK

	CASE CESAR_MOBILE_CALL4
		$mobile_print_labels[0] = &MCES04A //	Cesar, it’s me.
		$mobile_print_labels[1] = &MCES04B //	Carl. You alright, holmes?
		$mobile_print_labels[2] = &MCES04C //	Your sister been worried. I heard some shit went down.
		$mobile_print_labels[3] = &MCES04D //	Yeah. Los Santos is dangerous right now. 
		$mobile_print_labels[4] = &MCES04E //	I’m out in the middle of… of… I don’t know… 
		$mobile_print_labels[5] = &MCES04F //	of Whetstone. Wherever that is. 
		$mobile_print_labels[6] = &MCES04G //	Don’t know Whetstone too well. 
		$mobile_print_labels[7] = &MCES04H //	I got some family out near there, I think. 
		$mobile_print_labels[8] = &MCES04J //	At least you ain’t in jail. 
		//$mobile_print_labels[9] = &MCES04K //	Shit’s fucked up with your brother, esse. 
		$mobile_print_labels[9] = &MCES04L	//	You be careful and look after Kendl. 
		$mobile_print_labels[10] = &MCES04M	//	Don’t worry about me, man. 
		$mobile_print_labels[11] = &MCES04N	//	Worry about the man who tries to fuck with my woman. 
		$mobile_print_labels[12] = &MCES04O	//	I got some back up coming out to protect you. 
		$mobile_print_labels[13] = &MCES04P	//	My cousin. Really intense, holmes. Trust me. 
		$mobile_print_labels[14] = &MCES04Q	//	Meet them at the diner in Dillimore over in Red County. 
		$mobile_print_labels[15] = &MCES04R	//	You won’t miss them.

		mobile_audio_labels[0] = SOUND_MCES04A //	Cesar, it’s me.
		mobile_audio_labels[1] = SOUND_MCES04B //	Carl. You alright, holmes?
		mobile_audio_labels[2] = SOUND_MCES04C //	Your sister been worried. I heard some shit went down.
		mobile_audio_labels[3] = SOUND_MCES04D //	Yeah. Los Santos is dangerous right now. 
		mobile_audio_labels[4] = SOUND_MCES04E //	I’m out in the middle of… of… I don’t know… 
		mobile_audio_labels[5] = SOUND_MCES04F //	of Whetstone. Wherever that is. 
		mobile_audio_labels[6] = SOUND_MCES04G //	Don’t know Whetstone too well. 
		mobile_audio_labels[7] = SOUND_MCES04H //	I got some family out near there, I think. 
		mobile_audio_labels[8] = SOUND_MCES04J //	At least you ain’t in jail. 
		//mobile_audio_labels[9] = SOUND_MCES04K //	Shit’s fucked up with your brother, esse. 
		mobile_audio_labels[9] = SOUND_MCES04L	//	You be careful and look after Kendl. 
		mobile_audio_labels[10] = SOUND_MCES04M	//	Don’t worry about me, man. 
		mobile_audio_labels[11] = SOUND_MCES04N	//	Worry about the man who tries to fuck with my woman. 
		mobile_audio_labels[12] = SOUND_MCES04O	//	I got some back up coming out to protect you. 
		mobile_audio_labels[13] = SOUND_MCES04P	//	My cousin. Really intense, holmes. Trust me. 
		mobile_audio_labels[14] = SOUND_MCES04Q	//	Meet them at the diner in Dillimore over in Red County. 
		mobile_audio_labels[15] = SOUND_MCES04R	//	You won’t miss them.

		cell_index_end = 15
	BREAK

	CASE CESAR_MOBILE_CALL9
		$mobile_print_labels[0] = &MCES09A	//	Hey, holmes, I been busy!
		$mobile_print_labels[1] = &MCES09B	//	Cesar! Whasup?
		$mobile_print_labels[2] = &MCES09C	//	I can smell nitrous oxide from a mile off, eh. 
		$mobile_print_labels[3] = &MCES09D	//	Racing, my friend. Cars. 
		$mobile_print_labels[4] = &MCES09E	//	Not beautiful cars, but fast, man, fast!
		$mobile_print_labels[5] = &MCES09F	//	What are you talking about?
		$mobile_print_labels[6] = &MCES09G	//	Street racers, from San Fierro. 
		$mobile_print_labels[7] = &MCES09H	//	They meet out here to tear up the black top. 
		//$mobile_print_labels[8] = &MCES09J	//	No chota, no chota choppers. 
		$mobile_print_labels[8] = &MCES09K	//	You wanna make some money?
		$mobile_print_labels[9] = &MCES09L	//	Does the Pope shit in the woods?
		$mobile_print_labels[10] = &MCES09M	//	Why you keep asking me that, holmes? 
		$mobile_print_labels[11] = &MCES09N	//	I told you, I dunno. Where his holiness shits is his business.
		$mobile_print_labels[12] = &MCES09O	//	Just get a fast car and meet me and Kendl just south of Montgomery. 
		$mobile_print_labels[13] = &MCES09P	//	See you, man.

		mobile_audio_labels[0] = SOUND_MCES09A	//	Hey, holmes, I been busy!
		mobile_audio_labels[1] = SOUND_MCES09B	//	Cesar! Whasup?
		mobile_audio_labels[2] = SOUND_MCES09C	//	I can smell nitrous oxide from a mile off, eh. 
		mobile_audio_labels[3] = SOUND_MCES09D	//	Racing, my friend. Cars. 
		mobile_audio_labels[4] = SOUND_MCES09E	//	Not beautiful cars, but fast, man, fast!
		mobile_audio_labels[5] = SOUND_MCES09F	//	What are you talking about?
		mobile_audio_labels[6] = SOUND_MCES09G	//	Street racers, from San Fierro. 
		mobile_audio_labels[7] = SOUND_MCES09H	//	They meet out here to tear up the black top. 
		//mobile_audio_labels[8] = SOUND_MCES09J	//	No chota, no chota choppers. 
		mobile_audio_labels[8] = SOUND_MCES09K	//	You wanna make some money?
		mobile_audio_labels[9] = SOUND_MCES09L	//	Does the Pope shit in the woods?
		mobile_audio_labels[10] = SOUND_MCES09M	//	Why you keep asking me that, holmes? 
		mobile_audio_labels[11] = SOUND_MCES09N	//	I told you, I dunno. Where his holiness shits is his business.
		mobile_audio_labels[12] = SOUND_MCES09O	//	Just get a fast car and meet me and Kendl just south of Montgomery. 
		mobile_audio_labels[13] = SOUND_MCES09P	//	See you, man.

		cell_index_end = 13
	BREAK

	// ***************************************KENDAL*******************************************************************************************
	CASE KENDAL_MOBILE_CALL1
		$mobile_print_labels[0] = &MKND01A	//	Yo.
		$mobile_print_labels[1] = &MKND01B	//	Loser!
		$mobile_print_labels[2] = &MKND01C	//	Yeah, yeah, Kendl.
		$mobile_print_labels[3] = &MKND01D	//	Will you ever grow up?
		$mobile_print_labels[4] = &MKND01E	//	I will when you will.
		$mobile_print_labels[5] = &MKND01F	//	Ok, it’s a deal.
		$mobile_print_labels[6] = &MKND01G	//	Tell Cesar next time I’ll be in a faster car!
		$mobile_print_labels[7] = &MKND01H	//	It won’t help you – LOSER!
		$mobile_print_labels[8] = &MKND01J	//	Goodbye!

		mobile_audio_labels[0] = SOUND_MKND01A	//	Yo.
		mobile_audio_labels[1] = SOUND_MKND01B	//	Loser!
		mobile_audio_labels[2] = SOUND_MKND01C	//	Yeah, yeah, Kendl.
		mobile_audio_labels[3] = SOUND_MKND01D	//	Will you ever grow up?
		mobile_audio_labels[4] = SOUND_MKND01E	//	I will when you will.
		mobile_audio_labels[5] = SOUND_MKND01F	//	Ok, it’s a deal.
		mobile_audio_labels[6] = SOUND_MKND01G	//	Tell Cesar next time I’ll be in a faster car!
		mobile_audio_labels[7] = SOUND_MKND01H	//	It won’t help you – LOSER!
		mobile_audio_labels[8] = SOUND_MKND01J	//	Goodbye!

		cell_index_end = 8
	BREAK



	// ***************************************CRASH*******************************************************************************************

	CASE CRASH_MOBILE_CALL1
		$mobile_print_labels[0] = &MTEN01A	// Wassup?
		$mobile_print_labels[1] = &MTEN01B	// Don’t try and hit me up with that ghetto babble, boy!
		$mobile_print_labels[2] = &MTEN01C	// Officer Tenpenny. How’d you get my number?
		$mobile_print_labels[3] = &MTEN01D	// Ways and means, you piece of shit. You been trying to avoid me?
		$mobile_print_labels[4] = &MTEN01E	// No Sir, just been busy is all.
		$mobile_print_labels[5] = &MTEN01F	// You’re not busy unless you’re doing something for us, you understand?
		$mobile_print_labels[6] = &MTEN01G	// Yeah. Loud and clear.
		$mobile_print_labels[7] = &MTEN01H	// Nice to hear it, Carl. 
		$mobile_print_labels[8] = &MTEN01J	// Call in to the doughnut place in the middle of Market – we need to talk.


		mobile_audio_labels[0] = SOUND_MTEN01A	// Wassup?
		mobile_audio_labels[1] = SOUND_MTEN01B	// Don’t try and hit me up with that ghetto babble, boy!
		mobile_audio_labels[2] = SOUND_MTEN01C	// Officer Tenpenny. How’d you get my number?
		mobile_audio_labels[3] = SOUND_MTEN01D	// Ways and means, you piece of shit. You been trying to avoid me?
		mobile_audio_labels[4] = SOUND_MTEN01E	// No Sir, just been busy is all.
		mobile_audio_labels[5] = SOUND_MTEN01F	// You’re not busy unless you’re doing something for us, you understand?
		mobile_audio_labels[6] = SOUND_MTEN01G	// Yeah. Loud and clear.
		mobile_audio_labels[7] = SOUND_MTEN01H	// Nice to hear it, Carl. 
		mobile_audio_labels[8] = SOUND_MTEN01J	// Call in to the doughnut place in the middle of Market – we need to talk.

		cell_index_end = 8
	BREAK


	CASE CRASH_MOBILE_CALL2
		$mobile_print_labels[0] = &MTEN02A	// Carl! You get that dossier?
		$mobile_print_labels[1] = &MTEN02B	// Yeah I got the damn files. What do you want me to do with them?
		$mobile_print_labels[2] = &MTEN02C	// We need to meet up someplace quiet and take care of things.
		$mobile_print_labels[3] = &MTEN02D	// There’s a ghost town, Las Brujas in the devil’s Castle – you know it?
		$mobile_print_labels[4] = &MTEN02E	// I’ll find it.
		$mobile_print_labels[5] = &MTEN02F	// I know you will, I’ll see you there.


		mobile_audio_labels[0] = SOUND_MTEN02A	// Carl! You get that dossier?
		mobile_audio_labels[1] = SOUND_MTEN02B	// Yeah I got the damn files. What do you want me to do with them?
		mobile_audio_labels[2] = SOUND_MTEN02C	// We need to meet up someplace quiet and take care of things.
		mobile_audio_labels[3] = SOUND_MTEN02D	// There’s a ghost town, Las Brujas in the devil’s Castle – you know it?
		mobile_audio_labels[4] = SOUND_MTEN02E	// I’ll find it.
		mobile_audio_labels[5] = SOUND_MTEN02F	// I know you will, I’ll see you there.

		cell_index_end = 5
	BREAK

	CASE CRASH_MOBILE_CALL3
		 $mobile_print_labels[0] = &MHRZ01A //Carl, it's officer Hernandez
		 $mobile_print_labels[1] = &MHRZ01B //Who?
		 $mobile_print_labels[2] = &MHRZ01C //Officer Hernandez.
		 $mobile_print_labels[3] = &MHRZ01D //I work with Tenpenny and Pulaski.
		 $mobile_print_labels[4] = &MHRZ01E //Oh, the bitch, what the hell you want?
		 $mobile_print_labels[5] = &MHRZ01F //Hey, show me some respect, uh, boy.
		 $mobile_print_labels[6] = &MHRZ01G //Go fuck yourself, you just they bitch!
		 $mobile_print_labels[7] = &MHRZ01H //You watch your tone, boy.
		 $mobile_print_labels[8] = &MHRZ01J //Now listen.  I've got a message from Officer Tenpenny.
		 $mobile_print_labels[9] = &MHRZ01K //Don't try and leave town, that would be a big mistake.
		 $mobile_print_labels[10] = &MHRZ01L //You hear me?
		 $mobile_print_labels[11] = &MHRZ01M //We're watching you.
		 $mobile_print_labels[12] = &MHRZ01N //Whatever you say, bitch.
		 

		 mobile_audio_labels[0] = SOUND_MHRZ01A //Carl, it's officer Hernandez
		 mobile_audio_labels[1] = SOUND_MHRZ01B //Who?
		 mobile_audio_labels[2] = SOUND_MHRZ01C //Officer Hernandez.
		 mobile_audio_labels[3] = SOUND_MHRZ01D //I work with Tenpenny and Pulaski.
		 mobile_audio_labels[4] = SOUND_MHRZ01E //Oh, the bitch, what the hell you want?
		 mobile_audio_labels[5] = SOUND_MHRZ01F //Hey, show me some respect, uh, boy.
		 mobile_audio_labels[6] = SOUND_MHRZ01G //Go fuck yourself, you just they bitch!
		 mobile_audio_labels[7] = SOUND_MHRZ01H //You watch your tone, boy.
		 mobile_audio_labels[8] = SOUND_MHRZ01J //Now listen.  I've got a message from Officer Tenpenny.
		 mobile_audio_labels[9] = SOUND_MHRZ01K //Don't try and leave town, that would be a big mistake.
		 mobile_audio_labels[10] = SOUND_MHRZ01L //You hear me?
		 mobile_audio_labels[11] = SOUND_MHRZ01M //We're watching you.
		 mobile_audio_labels[12] = SOUND_MHRZ01N //Whatever you say, bitch.
	
		cell_index_end = 12
	BREAK

	// ***************************************SMOKE*******************************************************************************************


	CASE SMOKE_MOBILE_CALL1
		$mobile_print_labels[0] = &MSMK01A	//CARl, it’s smoke.
		$mobile_print_labels[1] = &MSMK01B	//Hey smoke
		$mobile_print_labels[2] = &MSMK01C	//Now Carl, I don’t want you to be taking offence at this or nothing,
		$mobile_print_labels[3] = &MSMK01D	//but I got some advice for you.
		$mobile_print_labels[4] = &MSMK01E	//What?
		$mobile_print_labels[5] = &MSMK01F	//The gym my friend. You’re letting yourself go.
		$mobile_print_labels[6] = &MSMK01G	//That’s a bit much, coming from you.
		$mobile_print_labels[7] = &MSMK01H	//Hey Carl, I’m big boned. But I’m still an athlete. 
		$mobile_print_labels[8] = &MSMK01J	//You’re letting yourself go, and to be honest, it breaks my heart.
		$mobile_print_labels[9] = &MSMK01K	//Gimme a break.
		$mobile_print_labels[10] = &MSMK01L	//I’m trying. Trying to help you help yourself, brother. The gym, you hear.
		$mobile_print_labels[11] = &MSMK01M	//Screw you.

		mobile_audio_labels[0] = SOUND_MSMK01A	//CARl, it’s smoke.
		mobile_audio_labels[1] = SOUND_MSMK01B	//Hey smoke
		mobile_audio_labels[2] = SOUND_MSMK01C	//Now Carl, I don’t want you to be taking offence at this or nothing,
		mobile_audio_labels[3] = SOUND_MSMK01D	//but I got some advice for you.
		mobile_audio_labels[4] = SOUND_MSMK01E	//What?
		mobile_audio_labels[5] = SOUND_MSMK01F	//The gym my friend. You’re letting yourself go.
		mobile_audio_labels[6] = SOUND_MSMK01G	//That’s a bit much, coming from you.
		mobile_audio_labels[7] = SOUND_MSMK01H	//Hey Carl, I’m big boned. But I’m still an athlete. 
		mobile_audio_labels[8] = SOUND_MSMK01J	//You’re letting yourself go, and to be honest, it breaks my heart.
		mobile_audio_labels[9] = SOUND_MSMK01K	//Gimme a break.
		mobile_audio_labels[10] = SOUND_MSMK01L	//I’m trying. Trying to help you help yourself, brother. The gym, you hear.
		mobile_audio_labels[11] = SOUND_MSMK01M	//Screw you.

		cell_index_end = 11
	BREAK


	// ***************************************TRUTH*******************************************************************************************

	CASE TRUTH_MOBILE_CALL1
		$mobile_print_labels[0] = &MTRU01A	//Yes.
		$mobile_print_labels[1] = &MTRU01B	//Carl.
		$mobile_print_labels[2] = &MTRU01C	//Who is this?
		$mobile_print_labels[3] = &MTRU01D	//You know me. This is The Truth
		$mobile_print_labels[4] = &MTRU01E	//No, I don’t.
		$mobile_print_labels[5] = &MTRU01F	//Perfection. They said you were a moron.
		$mobile_print_labels[6] = &MTRU01G	//Who?
		$mobile_print_labels[7] = &MTRU01H	//Okay, you can drop the act now, kid.
		$mobile_print_labels[8] = &MTRU01J	//Are you the police?
		$mobile_print_labels[9] = &MTRU01K	//No. We have a mutual friend, and business partner.
		$mobile_print_labels[10] = &MTRU01L	//We do? Who?
		$mobile_print_labels[11] = &MTRU01M	//Yes. Have you killed any cops lately?
		$mobile_print_labels[12] = &MTRU01N	//Oh, god!
		$mobile_print_labels[13] = &MTRU01O	//So, I’ve got a room at a motel in Angel Pine. 
		$mobile_print_labels[14] = &MTRU01P	//Make sure nobody follows you.

		mobile_audio_labels[0] = SOUND_MTRU01A	//Yes.
		mobile_audio_labels[1] = SOUND_MTRU01B	//Carl.
		mobile_audio_labels[2] = SOUND_MTRU01C	//Who is this?
		mobile_audio_labels[3] = SOUND_MTRU01D	//You know me. This is The Truth
		mobile_audio_labels[4] = SOUND_MTRU01E	//No, I don’t.
		mobile_audio_labels[5] = SOUND_MTRU01F	//Perfection. They said you were a moron.
		mobile_audio_labels[6] = SOUND_MTRU01G	//Who?
		mobile_audio_labels[7] = SOUND_MTRU01H	//Okay, you can drop the act now, kid.
		mobile_audio_labels[8] = SOUND_MTRU01J	//Are you the police?
		mobile_audio_labels[9] = SOUND_MTRU01K	//No. We have a mutual friend, and business partner.
		mobile_audio_labels[10] = SOUND_MTRU01L	//We do? Who?
		mobile_audio_labels[11] = SOUND_MTRU01M	//Yes. Have you killed any cops lately?
		mobile_audio_labels[12] = SOUND_MTRU01N	//Oh, god!
		mobile_audio_labels[13] = SOUND_MTRU01O	//So, I’ve got a room at a motel in Angel Pine.
		mobile_audio_labels[14] = SOUND_MTRU01P	//Make sure nobody follows you.

		cell_index_end = 14

	BREAK		
		
	CASE TRUTH_MOBILE_CALL2
		$mobile_print_labels[0] = &MTRU02A	//Whattup?
		$mobile_print_labels[1] = &MTRU02B	//Hey, Carl. Now, I got that little mwah mwah you were after. 
		$mobile_print_labels[2] = &MTRU02C	//But, be careful. People are listening to us. 
		$mobile_print_labels[3] = &MTRU02D	//I got a little green village up in the hills, come and get it. 
		$mobile_print_labels[4] = &MTRU02E	//I don’t know you! I don’t know you! 
		$mobile_print_labels[5] = &MTRU02F	//Prank caller! Prank caller!

		mobile_audio_labels[0] = SOUND_MTRU02A	//Whattup?
		mobile_audio_labels[1] = SOUND_MTRU02B	//Hey, Carl. Now, I got that little mwah mwah you were after. 
		mobile_audio_labels[2] = SOUND_MTRU02C	//But, be careful. People are listening to us. 
		mobile_audio_labels[3] = SOUND_MTRU02D	//I got a little green village up in the hills, come and get it.
		mobile_audio_labels[4] = SOUND_MTRU02E	//I don’t know you! I don’t know you! 
		mobile_audio_labels[5] = SOUND_MTRU02F	//Prank caller! Prank caller!

		cell_index_end = 5

	BREAK

	CASE TRUTH_MOBILE_CALL3
		$mobile_print_labels[0] = &MTRU03A	//Hey
		$mobile_print_labels[1] = &MTRU03B	//Carl. It’s me. The Truth. We got a date with destiny, man
		$mobile_print_labels[2] = &MTRU03C	//In about five minutes.
		$mobile_print_labels[3] = &MTRU03D	//Where are you? 
		$mobile_print_labels[4] = &MTRU03E	//At the old airplane graveyard you’ve been hanging around.

		mobile_audio_labels[0] = SOUND_MTRU03A	//Hey
		mobile_audio_labels[1] = SOUND_MTRU03B	//Carl. It’s me. The Truth. We got a date with destiny, man
		mobile_audio_labels[2] = SOUND_MTRU03C	//In about five minutes.
		mobile_audio_labels[3] = SOUND_MTRU03D	//Where are you?
		mobile_audio_labels[4] = SOUND_MTRU03E	//At the old airplane graveyard you’ve been hanging around.

		cell_index_end = 4

	BREAK

	// ***************************************WUZI*******************************************************************************************

	CASE WUZI_MOBILE_CALL1
		$mobile_print_labels[0] = &MWUZ00A	//Whattup?
		$mobile_print_labels[1] = &MWUZ00B	//Hey Carl, it’s Woozie. 
		$mobile_print_labels[2] = &MWUZ00C	//Hey, if you got some time I’d like for you to come over so we can talk about something.
		$mobile_print_labels[3] = &MWUZ00D	//Yeah, sure. Where can I find you?
		$mobile_print_labels[4] = &MWUZ00E	//I own a little betting shop in China Town.
		$mobile_print_labels[5] = &MWUZ00F	//Just come ‘round and introduce yourself.
		$mobile_print_labels[6] = &MWUZ00G	//My people will be expecting a visit.
		$mobile_print_labels[7] = &MWUZ00H	//It’s a plan, man.
		$mobile_print_labels[8] = &MWUZ00J	//Later, dude.

		mobile_audio_labels[0] = SOUND_MWUZ00A	//Whattup?
		mobile_audio_labels[1] = SOUND_MWUZ00B	//Hey Carl, it’s Woozie. 
		mobile_audio_labels[2] = SOUND_MWUZ00C	//Hey, if you got some time I’d like for you to come over so we can talk about something.
		mobile_audio_labels[3] = SOUND_MWUZ00D	//Yeah, sure. Where can I find you?
		mobile_audio_labels[4] = SOUND_MWUZ00E	//I own a little betting shop in China Town.
		mobile_audio_labels[5] = SOUND_MWUZ00F	//Just come ‘round and introduce yourself.
		mobile_audio_labels[6] = SOUND_MWUZ00G	//My people will be expecting a visit.
		mobile_audio_labels[7] = SOUND_MWUZ00H	//It’s a plan, man.
		mobile_audio_labels[8] = SOUND_MWUZ00J	//Later, dude.

		cell_index_end = 8
	BREAK

	CASE WUZI_MOBILE_CALL3
		$mobile_print_labels[0] = &MWUZ03A	//Hello?
		$mobile_print_labels[1] = &MWUZ03B	//Carl, it’s Woozie!
		$mobile_print_labels[2] = &MWUZ03C	//Hey, Woozie, man, what you been up to, dude?
		$mobile_print_labels[3] = &MWUZ03D	//Come along and see for yourself.
		$mobile_print_labels[4] = &MWUZ03E	//I got a little business proposition for you.
		$mobile_print_labels[5] = &MWUZ03F	//Come over and see the setup, my friend.
		$mobile_print_labels[6] = &MWUZ03G	//Ok, sure, I’d like that!
		$mobile_print_labels[7] = &MWUZ03H	//Like it?  You’re gonna love it!
		$mobile_print_labels[8] = &MWUZ03J	//It’s the Four Dragons Casino in Las Venturas.
		$mobile_print_labels[9] = &MWUZ03K	//I’ll see you soon, yeah?
		$mobile_print_labels[10] = &MWUZ03L	//Sure thing, dude.

		mobile_audio_labels[0] = SOUND_MWUZ03A	//Hello?
		mobile_audio_labels[1] = SOUND_MWUZ03B	//Carl, it’s Woozie!
		mobile_audio_labels[2] = SOUND_MWUZ03C	//Hey, Woozie, man, what you been up to, dude?
		mobile_audio_labels[3] = SOUND_MWUZ03D	//Come along and see for yourself.
		mobile_audio_labels[4] = SOUND_MWUZ03E	//I got a little business proposition for you.
		mobile_audio_labels[5] = SOUND_MWUZ03F	//Come over and see the setup, my friend.
		mobile_audio_labels[6] = SOUND_MWUZ03G	//Ok, sure, I’d like that!
		mobile_audio_labels[7] = SOUND_MWUZ03H	//Like it?  You’re gonna love it!
		mobile_audio_labels[8] = SOUND_MWUZ03J	//It’s the Four Dragons Casino in Las Venturas.
		mobile_audio_labels[9] = SOUND_MWUZ03K	//I’ll see you soon, yeah?
		mobile_audio_labels[10] = SOUND_MWUZ03L	//Sure thing, dude.

		cell_index_end = 10
	BREAK

	CASE WUZI_MOBILE_CALL4		   
		$mobile_print_labels[0] = &MWUZ03B	//Carl, it’s Woozie!
		$mobile_print_labels[1] = &MWUZ05B	//Whattup, dude?
		$mobile_print_labels[2] = &MWUZ05C	//You get that pass key yet?
		$mobile_print_labels[3] = &MWUZ05D	//No, I’m working on it.
		$mobile_print_labels[4] = &MWUZ05E	//What’s the problem, friend?
		$mobile_print_labels[5] = &MWUZ05F	//Just get her in the sack, make her happy and grab the card!
		$mobile_print_labels[6] = &MWUZ05G	//Hey, you can’t rush  the seduction of a lady!
		$mobile_print_labels[7] = &MWUZ05H //(laugh) okay, just gimme a call when you’ve got it.
		$mobile_print_labels[8] = &MWUZ05J	//Will do.

		mobile_audio_labels[0] = SOUND_MWUZ03B	//Carl, it’s Woozie!
		mobile_audio_labels[1] = SOUND_MWUZ05B	//Whattup, dude?
		mobile_audio_labels[2] = SOUND_MWUZ05C	//You get that pass key yet?
		mobile_audio_labels[3] = SOUND_MWUZ05D	//No, I’m working on it.
		mobile_audio_labels[4] = SOUND_MWUZ05E	//What’s the problem, friend?
		mobile_audio_labels[5] = SOUND_MWUZ05F	//Just get her in the sack, make her happy and grab the card!
		mobile_audio_labels[6] = SOUND_MWUZ05G	//Hey, you can’t rush  the seduction of a lady!
		mobile_audio_labels[7] = SOUND_MWUZ05H //(laugh) okay, just gimme a call when you’ve got it.
		mobile_audio_labels[8] = SOUND_MWUZ05J	//Will do.

		cell_index_end = 8
	BREAK

	CASE WUZI_MOBILE_CALL5
		$mobile_print_labels[0] = &MWUZ07A  //Carl, it’s me.
		$mobile_print_labels[1] = &MWUZ07B	//You the man!
		$mobile_print_labels[2] = &MWUZ07C	//Get back to the Four Dragons and we can get on with this thing!
		$mobile_print_labels[3] = &MWUZ07D	//See you in five.

		mobile_audio_labels[0] = SOUND_MWUZ07A //Carl, it’s me.
		mobile_audio_labels[1] = SOUND_MWUZ07B	//You the man!
		mobile_audio_labels[2] = SOUND_MWUZ07C	//Get back to the Four Dragons and we can get on with this thing!
		mobile_audio_labels[3] = SOUND_MWUZ07D	//See you in five.

		cell_index_end = 3
	BREAK

	CASE WUZI_MOBILE_CALL6
		$mobile_print_labels[0] = &MWUZ04A	//Yellow?
		$mobile_print_labels[1] = &MWUZ04B	//Hey, CJ, what’s up with you?
		$mobile_print_labels[2] = &MWUZ04C	//Are we doing this heist or are you going soft on me again.
		$mobile_print_labels[3] = &MWUZ04D	//Yo, check it, those three fools have been shipped out of Venturas, 
		$mobile_print_labels[4] = &MWUZ04E	//Salvatore thinks I’m cool, so we’re on!
		$mobile_print_labels[5] = &MWUZ04F	//I’ll meet you back at the ‘Dragons.
		$mobile_print_labels[6] = &MWUZ04G	//Ok, cool.
		$mobile_print_labels[7] = &MWUZ04H	//Later.   

		mobile_audio_labels[0] = SOUND_MWUZ04A	//Yellow?
		mobile_audio_labels[1] = SOUND_MWUZ04B	//Hey, CJ, what’s up with you?
		mobile_audio_labels[2] = SOUND_MWUZ04C	//Are we doing this heist or are you going soft on me again.
		mobile_audio_labels[3] = SOUND_MWUZ04D	//Yo, check it, those three fools have been shipped out of Venturas,
		mobile_audio_labels[4] = SOUND_MWUZ04E	//Salvatore thinks I’m cool, so we’re on!
		mobile_audio_labels[5] = SOUND_MWUZ04F	//I’ll meet you back at the ‘Dragons.
		mobile_audio_labels[6] = SOUND_MWUZ04G	//Ok, cool.
		mobile_audio_labels[7] = SOUND_MWUZ04H	//Later.   

		cell_index_end = 7
	BREAK


	CASE WUZI_MOBILE_CALL7

		$mobile_print_labels[0] = &MWUZ06A	//Hello?
		$mobile_print_labels[1] = &MWUZ06B	//What’s the hold up with that security pass, CJ?
		$mobile_print_labels[2] = &MWUZ06C	//There’s been a slight setback.
		$mobile_print_labels[3] = &MWUZ06D	//What kind of setback?
		$mobile_print_labels[4] = &MWUZ09E	//Well, more of a romantic fuck up.
		$mobile_print_labels[5] = &MWUZ09F	//It’s Millie, she’s dumped me.
		$mobile_print_labels[6] = &MWUZ06G	//What the fuck? How?
		$mobile_print_labels[7] = &MWUZ06H	//Well, we were hanging out and shit got fucked up.
		$mobile_print_labels[8] = &MWUZ06J	//That’s all I’m sayin’.
		$mobile_print_labels[9] = &MWUZ09H	//I know, I know, how could she dump a catch like me?
		$mobile_print_labels[10] = &MWUZ09J	//There’s no accounting for taste.
		$mobile_print_labels[11] = &MWUZ06K	//Well her pass will probably be in her house!
		$mobile_print_labels[12] = &MWUZ06L	//You gotta break in and get it!
		$mobile_print_labels[13] = &MWUZ06M	//Shit, you’re right.
		$mobile_print_labels[14] = &MWUZ06N	//I’ll call when I’ve got the card.

		mobile_audio_labels[0] = SOUND_MWUZ06A	//Hello?	
		mobile_audio_labels[1] = SOUND_MWUZ06B	//What’s the hold up with that security pass, CJ?
		mobile_audio_labels[2] = SOUND_MWUZ06C	//There’s been a slight setback.
		mobile_audio_labels[3] = SOUND_MWUZ06D	//What kind of setback?
		mobile_audio_labels[4] = SOUND_MWUZ09E	//Well, more of a romantic fuck up.
		mobile_audio_labels[5] = SOUND_MWUZ09F	//It’s Millie, she’s dumped me.
		mobile_audio_labels[6] = SOUND_MWUZ06G	//What the fuck? How?
		mobile_audio_labels[7] = SOUND_MWUZ06H	//Well, we were hanging out and shit got fucked up.
		mobile_audio_labels[8] = SOUND_MWUZ06J	//That’s all I’m sayin’.
		mobile_audio_labels[9] = SOUND_MWUZ09H	//I know, I know, how could she dump a catch like me?
		mobile_audio_labels[10] = SOUND_MWUZ09J	//There’s no accounting for taste.
		mobile_audio_labels[11] = SOUND_MWUZ06K	//Well her pass will probably be in her house!
		mobile_audio_labels[12] = SOUND_MWUZ06L	//You gotta break in and get it!
		mobile_audio_labels[13] = SOUND_MWUZ06M	//Shit, you’re right.
		mobile_audio_labels[14] = SOUND_MWUZ06N	//I’ll call when I’ve got the card.

		cell_index_end = 14
	BREAK

	CASE WUZI_MOBILE_CALL8

		$mobile_print_labels[0] = &MWUZ06A	//Hello?
		$mobile_print_labels[1] = &MWUZ06B	//What’s the hold up with that security pass, CJ?
		$mobile_print_labels[2] = &MWUZ06C	//There’s been a slight setback.
		$mobile_print_labels[3] = &MWUZ06D	//What kind of setback?
		$mobile_print_labels[4] = &MWUZ06E	//Well, more of an unfortunate accident.
		$mobile_print_labels[5] = &MWUZ06F	//It’s Millie, she’s dead.
		$mobile_print_labels[6] = &MWUZ06G	//What the fuck? How?
		$mobile_print_labels[7] = &MWUZ06H	//Well, we were hanging out and shit got fucked up.
		$mobile_print_labels[8] = &MWUZ06J	//That’s all I’m sayin’.
		$mobile_print_labels[9] = &MWUZ06K	//Well her pass will probably be in her house!
		$mobile_print_labels[10] = &MWUZ06L	//You gotta break in and get it!
		$mobile_print_labels[11] = &MWUZ06M	//Shit, you’re right.
		$mobile_print_labels[12] = &MWUZ06N	//I’ll call when I’ve got the card.

		mobile_audio_labels[0] = SOUND_MWUZ06A	//Hello?
		mobile_audio_labels[1] = SOUND_MWUZ06B	//What’s the hold up with that security pass, CJ?
		mobile_audio_labels[2] = SOUND_MWUZ06C	//There’s been a slight setback.
		mobile_audio_labels[3] = SOUND_MWUZ06D	//What kind of setback?
		mobile_audio_labels[4] = SOUND_MWUZ06E	//Well, more of an unfortunate accident.
		mobile_audio_labels[5] = SOUND_MWUZ06F	//It’s Millie, she’s dead.
		mobile_audio_labels[6] = SOUND_MWUZ06G	//What the fuck? How?
		mobile_audio_labels[7] = SOUND_MWUZ06H	//Well, we were hanging out and shit got fucked up.
		mobile_audio_labels[8] = SOUND_MWUZ06J	//That’s all I’m sayin’.
		mobile_audio_labels[9] = SOUND_MWUZ06K	//Well her pass will probably be in her house!
		mobile_audio_labels[10] = SOUND_MWUZ06L	//You gotta break in and get it!
		mobile_audio_labels[11] = SOUND_MWUZ06M	//Shit, you’re right.
		mobile_audio_labels[12] = SOUND_MWUZ06N	//I’ll call when I’ve got the card.

		cell_index_end = 12
	BREAK


	
	// ***************************************TORINO*******************************************************************************************

	CASE TORENO_MOBILE_CALL1

		$mobile_print_labels[0] = &MTOR01A	//Yes?
		$mobile_print_labels[1] = &MTOR01B	//This is a friend of yours. 
		$mobile_print_labels[2] = &MTOR01C	//I’ve got some information relating to your brother. 
		$mobile_print_labels[3] = &MTOR01D	//Come to the my ranch and I’ll explain. 
		$mobile_print_labels[4] = &MTOR01E	//It’s in Tierra Robada, cross the Garver Bridge and head South.
		$mobile_print_labels[5] = &MTOR01F	//Who the fuck is this?
		$mobile_print_labels[6] = &MTOR01G	//I can’t talk right now. Get your ass over here.
		$mobile_print_labels[7] = &MTOR01H	//Moms always told me not to talk to strangers.
		$mobile_print_labels[8] = &MTOR01J	//And look what happened to her!  Now if you want your brother to sleep tonight with his tongue intact, get over here!
		$mobile_print_labels[9] = &MTOR01K	//Now if you want brother to sleep tonight with his tongue intact get your ass over here, now. 
		$mobile_print_labels[10] = &MTOR01L	//Goodbye.

		mobile_audio_labels[0] = SOUND_MTOR01A	//Yes?
		mobile_audio_labels[1] = SOUND_MTOR01B	//This is a friend of yours. 
		mobile_audio_labels[2] = SOUND_MTOR01C	//I’ve got some information relating to your brother. 
		mobile_audio_labels[3] = SOUND_MTOR01D	//Come to the my ranch and I’ll explain. 
		mobile_audio_labels[4] = SOUND_MTOR01E	//It’s in Tierra Robada, cross the Garver Bridge and head South.
		mobile_audio_labels[5] = SOUND_MTOR01F	//Who the fuck is this?
		mobile_audio_labels[6] = SOUND_MTOR01G	//I can’t talk right now. Get your ass over here.
		mobile_audio_labels[7] = SOUND_MTOR01H	//Moms always told me not to talk to strangers.
		mobile_audio_labels[8] = SOUND_MTOR01J	//And look what happened to her!  Now if you want your brother to sleep tonight with his tongue 
		mobile_audio_labels[9] = SOUND_MTOR01K	//Now if you want brother to sleep tonight with his tongue intact get your ass over here, now. 
		mobile_audio_labels[10] = SOUND_MTOR01L	//Goodbye.


		cell_index_end = 10
	BREAK


	CASE TORENO_MOBILE_CALL2

		$mobile_print_labels[0] = &MTOR02A	//Who the fuck is this?
		$mobile_print_labels[1] = &MTOR02B	//Son, get back to the ranch and I’ll explain everything. 
		$mobile_print_labels[2] = &MTOR02C	//And I mean everything.
		$mobile_print_labels[3] = &MTOR02D	//Can’t you just tell me now?
		$mobile_print_labels[4] = &MTOR02E	//I guess not.

		mobile_audio_labels[0] = SOUND_MTOR02A	//Who the fuck is this?
		mobile_audio_labels[1] = SOUND_MTOR02B	//Son, get back to the ranch and I’ll explain everything.
		mobile_audio_labels[2] = SOUND_MTOR02C	//And I mean everything.
		mobile_audio_labels[3] = SOUND_MTOR02D	//Can’t you just tell me now?
		mobile_audio_labels[4] = SOUND_MTOR02E	//I guess not.

		cell_index_end = 4
	BREAK

	CASE TORENO_MOBILE_CALL3

		$mobile_print_labels[0] = &MTOR03A	//Here. Now. Don’t screw around. 
		$mobile_print_labels[1] = &MTOR03B	//Asshole!

		mobile_audio_labels[0] = SOUND_MTOR03A	//Here. Now. Don’t screw around.
		mobile_audio_labels[1] = SOUND_MTOR03B	//Asshole!

		cell_index_end = 1
	BREAK


	CASE TORENO_MOBILE_CALL4

		$mobile_print_labels[0] = &MTOR05A	//Hey, wassup?
		$mobile_print_labels[1] = &MTOR05B	//Hey, how’s the flying coming along?
		$mobile_print_labels[2] = &MTOR05C	//Yeah, well shit man, I was gonna holla at you about that cause, you know, I’m-
		$mobile_print_labels[3] = &MTOR05D	//(laugh) Will you listen to yourself? Admit it, you’re scared.
		$mobile_print_labels[4] = &MTOR05E	//Just a little.
		$mobile_print_labels[5] = &MTOR05F	//Unitl you’ve walked into the middle of Bedouin arms dealer’s camp with nothing but a briefcase full of cash, 
		$mobile_print_labels[6] = &MTOR05G	//you don’t don’t know the meaning of fear, okay?
		$mobile_print_labels[7] = &MTOR05H	//Oh yeah?  Try going to the swap meet in Idlewood sometime.
		$mobile_print_labels[8] = &MTOR05J	//Learn to fly, Carl.

		mobile_audio_labels[0] = SOUND_MTOR05A	//Hey, wassup?
		mobile_audio_labels[1] = SOUND_MTOR05B	//Hey, how’s the flying coming along?
		mobile_audio_labels[2] = SOUND_MTOR05C	//Yeah, well shit man, I was gonna holla at you about that cause, you know, I’m-
		mobile_audio_labels[3] = SOUND_MTOR05D	//(laugh) Will you listen to yourself? Admit it, you’re scared.
		mobile_audio_labels[4] = SOUND_MTOR05E	//Just a little.
		mobile_audio_labels[5] = SOUND_MTOR05F	//Unitl you’ve walked into the middle of Bedouin arms dealer’s camp with nothing but a briefcase full of cash, 
		mobile_audio_labels[6] = SOUND_MTOR05G	//you don’t don’t know the meaning of fear, okay?
		mobile_audio_labels[7] = SOUND_MTOR05H	//Oh yeah?  Try going to the swap meet in Idlewood sometime.
		mobile_audio_labels[8] = SOUND_MTOR05J	//Learn to fly, Carl.

		cell_index_end = 8

	BREAK

	CASE TORENO_MOBILE_CALL5

		$mobile_print_labels[0] = &MTOR06A	//What do you want, Toreno?
		$mobile_print_labels[1] = &MTOR06B	//Well, what do you want, Carl?
		$mobile_print_labels[2] = &MTOR06C	//You want your brother out of jail?
		$mobile_print_labels[3] = &MTOR06D	//Ok, listen, I’m trying man, I really am, I guess I’m not a natural pilot, that’s all.
		$mobile_print_labels[4] = &MTOR06E	//I'm gonna let you in on a little secret. Carl
		$mobile_print_labels[5] = &MTOR06F	//The one difference between those that tried and died 
		$mobile_print_labels[6] = &MTOR06G	//and those that still walk this Earth, is belief in self
		$mobile_print_labels[7] = &MTOR06H	//Nothing is impossible, Carl.
		$mobile_print_labels[8] = &MTOR06J	//Beat your fears, focus your mind and learn to fly.
		$mobile_print_labels[9] = &MTOR06K	//Ok, ok, I’ll-
		$mobile_print_labels[10] = &MTOR06L	//TORENO?
		$mobile_print_labels[11] = &MTOR06M	//Oh shit…

		mobile_audio_labels[0] = SOUND_MTOR06A	//What do you want, Toreno?
		mobile_audio_labels[1] = SOUND_MTOR06B	//Well, what do you want, Carl?
		mobile_audio_labels[2] = SOUND_MTOR06C	//You want your brother out of jail?
		mobile_audio_labels[3] = SOUND_MTOR06D	//Ok, listen, I’m trying man, I really am, I guess I’m not a natural pilot, that’s all.
		mobile_audio_labels[4] = SOUND_MTOR06E	//I'm gonna let you in on a little secret. Carl
		mobile_audio_labels[5] = SOUND_MTOR06F	//The one difference between those that tried and died 
		mobile_audio_labels[6] = SOUND_MTOR06G	//and those that still walk this Earth, is belief in self
		mobile_audio_labels[7] = SOUND_MTOR06H	//Nothing is impossible, Carl.
		mobile_audio_labels[8] = SOUND_MTOR06J	//Beat your fears, focus your mind and learn to fly.
		mobile_audio_labels[9] = SOUND_MTOR06K	//Ok, ok, I’ll-
		mobile_audio_labels[10] = SOUND_MTOR06L	//TORENO?
		mobile_audio_labels[11] = SOUND_MTOR06M	//Oh shit…

		cell_index_end = 11
	BREAK

	CASE TORENO_MOBILE_CALL6

		$mobile_print_labels[0] = &MTOR07A	//Toreno?
		$mobile_print_labels[1] = &MTOR07B	//Carl, Learn to fly.
		$mobile_print_labels[2] = &MTOR07C	//I’m on it, man, I swear.
		$mobile_print_labels[3] = &MTOR07D	//I'm on it man I swear', same old broken record, Carl.  But that's fine…
		$mobile_print_labels[4] = &MTOR07E	//because your brother’s getting a new cell mate tonight.
		$mobile_print_labels[5] = &MTOR07L	//Horse Cock Harry, And I'm sending a present, little wedding present. 
		$mobile_print_labels[6] = &MTOR07M	//Big tub of lube!
		$mobile_print_labels[7] = &MTOR07F	//Shit dude, ok, ok, I swear man, I’m gonna be the best pilot!
		$mobile_print_labels[8] = &MTOR07G	//I'd love to hear you Carl, I can't hear you.  All I can hear is your brother's love cries
		$mobile_print_labels[9] = &MTOR07N	//as eight kilometers of cock finds it's way up his ass.
		$mobile_print_labels[10] = &MTOR07O	//Aooooowww' - that's your brother, ok?  No big problem.
		$mobile_print_labels[11] = &MTOR07H	//Wait! Please, man!
		$mobile_print_labels[12] = &MTOR07J	//that was my last motivational speech, understand? Am I being too spiritual for you, Carl?
		$mobile_print_labels[13] = &MTOR07K	//Ok, man, I get the message!

		mobile_audio_labels[0] = SOUND_MTOR07A	//Toreno?
		mobile_audio_labels[1] = SOUND_MTOR07B	//Carl, Learn to fly.
		mobile_audio_labels[2] = SOUND_MTOR07C	//I’m on it, man, I swear.
		mobile_audio_labels[3] = SOUND_MTOR07D	//I'm on it man I swear', same old broken record, Carl.  But that's fine…
		mobile_audio_labels[4] = SOUND_MTOR07E	//because your brother’s getting a new cell mate tonight.
		mobile_audio_labels[5] = SOUND_MTOR07L	//Horse Cock Harry, And I'm sending a present, little wedding present. 
		mobile_audio_labels[6] = SOUND_MTOR07M	//Big tub of lube!
		mobile_audio_labels[7] = SOUND_MTOR07F	//Shit dude, ok, ok, I swear man, I’m gonna be the best pilot!
		mobile_audio_labels[8] = SOUND_MTOR07G	//I'd love to hear you Carl, I can't hear you.  All I can hear is your brother's love cries
		mobile_audio_labels[9] = SOUND_MTOR07N	//as eight kilometers of cock finds it's way up his ass.
		mobile_audio_labels[10] = SOUND_MTOR07O	//Aooooowww' - that's your brother, ok?  No big problem.
		mobile_audio_labels[11] = SOUND_MTOR07H	//Wait! Please, man!
		mobile_audio_labels[12] = SOUND_MTOR07J	//that was my last motivational speech, understand? Am I being too spiritual for you, Carl?
		mobile_audio_labels[13] = SOUND_MTOR07K	//Ok, man, I get the message!

		cell_index_end = 13
	BREAK


	// ***************************************KENT PAUL*******************************************************************************************

	CASE KENT_MOBILE_CALL1

		$mobile_print_labels[0] = &MKP01A	//Halo
		$mobile_print_labels[1] = &MKP01B	///Hey, Carl, it’s me Paulo.
		$mobile_print_labels[2] = &MKP01C	//Listen, Rosie’s in a tangle 
		$mobile_print_labels[3] = &MKP01D	//and I think you’re the geezer to sort it out for us, ok, sunshine?
		$mobile_print_labels[4] = &MKP01E	//Call around to the office.
		$mobile_print_labels[5] = &MKP01F  //Thanks, man, I appreciate this opportunity.

		mobile_audio_labels[0] = SOUND_MKP01A	//Halo
		mobile_audio_labels[1] = SOUND_MKP01B	///Hey, Carl, it’s me Paulo.
		mobile_audio_labels[2] = SOUND_MKP01C	//Listen, Rosie’s in a tangle 
		mobile_audio_labels[3] = SOUND_MKP01D	//and I think you’re the geezer to sort it out for us, ok, sunshine?
		mobile_audio_labels[4] = SOUND_MKP01E	//Call around to the office.
		mobile_audio_labels[5] = SOUND_MKP01F  //Thanks, man, I appreciate this opportunity.

		cell_index_end = 5
	BREAK


	// ***************************************ROSENBURG*******************************************************************************************

	CASE ROSE_MOBILE_CALL1

		$mobile_print_labels[0] = &MSAL01A	//Hello?
		$mobile_print_labels[1] = &MROS01B	//Carl? It’s me, Ken.
		$mobile_print_labels[2] = &MROS01D	//Listen! The shit’s hit the fucking fan! The Leone family has made 
		//$mobile_print_labels[3] = &MROS01E //their move! Salvatore’s here, 
		$mobile_print_labels[3] = &MROS01F	//now! He’s taken over Caligula’s!
		$mobile_print_labels[4] = &MROS01H	//we’re screwed! It’s war for control of Venturas, man, War! WAR!
		$mobile_print_labels[5] = &MROS01L	//there’s word of some Triad visit or something that should keep him busy. I’m calling from the bathroom, I gotta go, I really gotta go.

		mobile_audio_labels[0] = SOUND_MSAL01A	//Hello?
		mobile_audio_labels[1] = SOUND_MROS01B	//Carl? It’s me, Ken.
		mobile_audio_labels[2] = SOUND_MROS01D	//Listen! The shit’s hit the fucking fan! The Leone family has made
		//mobile_audio_labels[3] = SOUND_MROS01E //their move! Salvatore’s here, 
		mobile_audio_labels[3] = SOUND_MROS01F	//now! He’s taken over Caligula’s!
		mobile_audio_labels[4] = SOUND_MROS01H	//we’re screwed! It’s war for control of Venturas, man, War! WAR!
		mobile_audio_labels[5] = SOUND_MROS01L	//there’s word of some Triad visit or something that should keep him busy. I’m calling from the bathroom, 
		
		cell_index_end = 5
	BREAK

	CASE ROSE_MOBILE_CALL2

		$mobile_print_labels[0] = &MROS02A //He-ello?
		$mobile_print_labels[1] = &MROS02B //You’ve hung us out to dry, I know it!
		$mobile_print_labels[2] = &MROS02C //Rosenberg?
		$mobile_print_labels[3] = &MROS02D //Yeah. Soon to be wearing concrete shoes in a shallow grave in the desert, Rosenberg! 
		$mobile_print_labels[4] = &MROS02E //I’m suprised you remember!
		$mobile_print_labels[5] = &MROS02F //Look, I haven’t forgotten you guys, just hang in there.
		$mobile_print_labels[6] = &MROS02G //Easy for you to say, this Salvatore guy might whack me at any moment.

		mobile_audio_labels[0] = SOUND_MROS02A //He-ello?
		mobile_audio_labels[1] = SOUND_MROS02B //You’ve hung us out to dry, I know it!
		mobile_audio_labels[2] = SOUND_MROS02C //Rosenberg?
		mobile_audio_labels[3] = SOUND_MROS02D //Yeah. Soon to be wearing concrete shoes in a shallow grave in the desert, Rosenberg!
		mobile_audio_labels[4] = SOUND_MROS02E //I’m suprised you remember!
		mobile_audio_labels[5] = SOUND_MROS02F //Look, I haven’t forgotten you guys, just hang in there.
		mobile_audio_labels[6] = SOUND_MROS02G //Easy for you to say, this Salvatore guy might whack me at any moment.


		cell_index_end = 6
	BREAK

	CASE ROSE_MOBILE_CALL3

		$mobile_print_labels[0] = &MROS03A //Hey Ken, how you doin’?
		$mobile_print_labels[1] = &MROS03B //(sniff) Who is this?
		$mobile_print_labels[2] = &MROS03C //It’s Carl, Carl Johnson!
		$mobile_print_labels[3] = &MROS03D //Hey, Carl! Great! (sniff)
		$mobile_print_labels[4] = &MROS03E //Guys, it’s Carl! Ga-great! I'm-- (sniff)  
		$mobile_print_labels[5] = &MROS03F //Fucking great!  Fucking amazing!
		$mobile_print_labels[6] = &MROS03G //Yeah, well, I’ve got a need for an accountant and a sound engineer 
		$mobile_print_labels[7] = &MROS03H //and I thought of you and Paul.
		$mobile_print_labels[8] = &MROS03J //Fucking amazing!
		$mobile_print_labels[9] = &MROS03K //Paul’s great with figures and I’d make a fucking amazing producer! 
		$mobile_print_labels[10] = &MROS03L	//This is (sniff) this fucking great! It's amazing!
		$mobile_print_labels[11] = &MROS03N	//Yeah, yeah, whatever you say man, but look.  Just come over to the mansion, and I’m gonna page you with the address.
		$mobile_print_labels[12] = &MROS03O	//See you soon.
		$mobile_print_labels[13] = &MROS03P //extra	Fucking amazing!

		mobile_audio_labels[0] = SOUND_MROS03A //Hey Ken, how you doin’?
		mobile_audio_labels[1] = SOUND_MROS03B //(sniff) Who is this?
		mobile_audio_labels[2] = SOUND_MROS03C //It’s Carl, Carl Johnson!
		mobile_audio_labels[3] = SOUND_MROS03D //Hey, Carl! Great! (sniff)
		mobile_audio_labels[4] = SOUND_MROS03E //Guys, it’s Carl! Ga-great! I'm-- (sniff)  
		mobile_audio_labels[5] = SOUND_MROS03F //Fucking great!  Fucking amazing!
		mobile_audio_labels[6] = SOUND_MROS03G //Yeah, well, I’ve got a need for an accountant and a sound engineer 
		mobile_audio_labels[7] = SOUND_MROS03H //and I thought of you and Paul.
		mobile_audio_labels[8] = SOUND_MROS03J //Fucking amazing!
		mobile_audio_labels[9] = SOUND_MROS03K //Paul’s great with figures and I’d make a fucking amazing producer! 
		mobile_audio_labels[10] = SOUND_MROS03L	//This is (sniff) this fucking great! It's amazing!
		mobile_audio_labels[11] = SOUND_MROS03N	//Yeah, yeah, whatever you say man, but look.  Just come over to the mansion, and I’m gonna page you with t
		mobile_audio_labels[12] = SOUND_MROS03O	//See you soon.
		mobile_audio_labels[13] = SOUND_MROS03P //extra	Fucking amazing!


		cell_index_end = 13
	BREAK


	// ***************************************SALVATORY*******************************************************************************************

	CASE SALV_MOBILE_CALL1

		$mobile_print_labels[0] = &MSAL01A	//Hello?
		$mobile_print_labels[1] = &MSAL01B	//Hey, Carl my boy!
		$mobile_print_labels[2] = &MSAL01C	//Mr. Leone.
		$mobile_print_labels[3] = &MSAL01D	//Everybody’s talking about the job you did on the St.Mark’s Bistro!
		$mobile_print_labels[4] = &MSAL01E	//Thank you, Mr. Leone.
		$mobile_print_labels[5] = &MSAL01F	//And you, eerr, took care of those three loose ends?
		$mobile_print_labels[6] = &MSAL01G	//Yeah those poor saps ran into a little trouble along the way.
		$mobile_print_labels[7] = &MSAL01H	//You won’t be hearing from Mr. Rosenberg again.
		$mobile_print_labels[8] = &MSAL01J	//Good boy! Good boy!
		$mobile_print_labels[9] = &MSAL01K	//Now listen, you’re going to have to keep a low profile 
		$mobile_print_labels[10] = &MSAL01M	//so let’s keep our distance for a while, eh?
		$mobile_print_labels[11] = &MSAL01N	//I’ll call you.
		$mobile_print_labels[12] = &MSAL01O	//Thank you, Mr. Leone.

		mobile_audio_labels[0] = SOUND_MSAL01A	//Hello?
		mobile_audio_labels[1] = SOUND_MSAL01B	//Hey, Carl my boy!
		mobile_audio_labels[2] = SOUND_MSAL01C	//Mr. Leone.
		mobile_audio_labels[3] = SOUND_MSAL01D	//Everybody’s talking about the job you did on the St.Mark’s Bistro!
		mobile_audio_labels[4] = SOUND_MSAL01E	//Thank you, Mr. Leone.
		mobile_audio_labels[5] = SOUND_MSAL01F	//And you, eerr, took care of those three loose ends?
		mobile_audio_labels[6] = SOUND_MSAL01G	//Yeah those poor saps ran into a little trouble along the way.
		mobile_audio_labels[7] = SOUND_MSAL01H	//You won’t be hearing from Mr. Rosenberg again.
		mobile_audio_labels[8] = SOUND_MSAL01J	//Good boy! Good boy!
		mobile_audio_labels[9] = SOUND_MSAL01K	//Now listen, you’re going to have to keep a low profile 
		mobile_audio_labels[10] = SOUND_MSAL01M	//so let’s keep our distance for a while, eh?
		mobile_audio_labels[11] = SOUND_MSAL01N	//I’ll call you.
		mobile_audio_labels[12] = SOUND_MSAL01O	//Thank you, Mr. Leone.

		cell_index_end = 12
	BREAK


	CASE SALV_MOBILE_CALL2

		$mobile_print_labels[0] = &MSAL02A	//‘Ssup?
		$mobile_print_labels[1] = &MSAL02B	//You two-bit, backstabbing, piece of eggplant shit!
		$mobile_print_labels[2] = &MSAL02C	//Salvatore!
		$mobile_print_labels[3] = &MSAL02D	//Nice to hear from you, too!
		$mobile_print_labels[4] = &MSAL02E	//You’re dead! Your friends are dead! Your family’s dead!
		$mobile_print_labels[5] = &MSAL02F	//I’m gonna fuck you up, and your children and your grandchildren!
		$mobile_print_labels[6] = &MSAL02G	//Well, it’s been nice talking, 
		$mobile_print_labels[7] = &MSAL02H	//but I’ve got some money needs spending on expensive trash, 
		$mobile_print_labels[8] = &MSAL02J	//so if you’ll excuse me.
		$mobile_print_labels[9] = &MSAL02K	//You’re dead! DEAD!

		mobile_audio_labels[0] = SOUND_MSAL02A	//‘Ssup?
		mobile_audio_labels[1] = SOUND_MSAL02B	//You two-bit, backstabbing, piece of eggplant shit!
		mobile_audio_labels[2] = SOUND_MSAL02C	//Salvatore!
		mobile_audio_labels[3] = SOUND_MSAL02D	//Nice to hear from you, too!
		mobile_audio_labels[4] = SOUND_MSAL02E	//You’re dead! Your friends are dead! Your family’s dead!
		mobile_audio_labels[5] = SOUND_MSAL02F	//I’m gonna fuck you up, and your children and your grandchildren!
		mobile_audio_labels[6] = SOUND_MSAL02G	//Well, it’s been nice talking, 
		mobile_audio_labels[7] = SOUND_MSAL02H	//but I’ve got some money needs spending on expensive trash, 
		mobile_audio_labels[8] = SOUND_MSAL02J	//so if you’ll excuse me.
		mobile_audio_labels[9] = SOUND_MSAL02K	//You’re dead! DEAD!


		cell_index_end = 9
	BREAK


	// ***************************************MILLIE KEYCARD*******************************************************************************************

	CASE MILLIE_KEY_MOBILE_CALL1
		$mobile_print_labels[0] = &MMILL2A	//Millie?
		$mobile_print_labels[1] = &MMILL2B	//Hey Carl, just called to say that last night was terrific.
		$mobile_print_labels[2] = &MMILL2C	//Likewise, Miss Perkins, likewise.
		$mobile_print_labels[3] = &MMILL2D	//Listen, I thought about that little scheme you told me about. 
		$mobile_print_labels[4] = &MMILL2E	//Don’t worry, I haven’t told anybody, not even my mom.
		$mobile_print_labels[5] = &MMILL2F	//I’ve left the key card and the door code in my bedroom. 
		//$mobile_print_labels[6] = &MMILL2G	//I’m going out of town for a while, so you can make it look like a burglary.
		$mobile_print_labels[6] = &MMILL2H	//Millie, girl, you will NOT regret this!
		$mobile_print_labels[7] = &MMILL2J	//I know, because I’m getting a cut, right?
		$mobile_print_labels[8] = &MMILL2K	//Sure thing, Millie, sure thing. I’ll see you right.
		$mobile_print_labels[9] = &MMILL2L	//Ok, I'll give you a call sometime.

		mobile_audio_labels[0] = SOUND_MMILL2A	//Millie?
		mobile_audio_labels[1] = SOUND_MMILL2B	//Hey Carl, just called to say that last night was terrific.
		mobile_audio_labels[2] = SOUND_MMILL2C	//Likewise, Miss Perkins, likewise.
		mobile_audio_labels[3] = SOUND_MMILL2D	//Listen, I thought about that little scheme you told me about. 
		mobile_audio_labels[4] = SOUND_MMILL2E	//Don’t worry, I haven’t told anybody, not even my mom.
		mobile_audio_labels[5] = SOUND_MMILL2F	//I’ve left the key card and the door code in my bedroom. 
		//mobile_audio_labels[6] = SOUND_MMILL2G	//I’m going out of town for a while, so you can make it look like a burglary.
		mobile_audio_labels[6] = SOUND_MMILL2H	//Millie, girl, you will NOT regret this!
		mobile_audio_labels[7] = SOUND_MMILL2J	//I know, because I’m getting a cut, right?
		mobile_audio_labels[8] = SOUND_MMILL2K	//Sure thing, Millie, sure thing. I’ll see you right.
		mobile_audio_labels[9] = SOUND_MMILL2L	//Ok, I'll give you a call sometime.

		cell_index_end = 9
	BREAK

	// ***************************************ZERO*******************************************************************************************

	CASE ZERO_MOBILE_CALL1
		$mobile_print_labels[0] = &MZER01A //Yeah, what?
		$mobile_print_labels[1] = &MZER01B //Sorry, Carl, are you busy?
		$mobile_print_labels[2] = &MZER01C //Zero? No, man, whassup?
		$mobile_print_labels[3] = &MZER01D //Disaster! My landlord is selling the shop!
		$mobile_print_labels[4] = &MZER01E //I’ll have nowhere to live and no safe haven from Berkley...
		$mobile_print_labels[5] = &MZER01F //I’m looking into property at the moment.
		$mobile_print_labels[6] = &MZER01G //I’ll swing by and have a look see.

		mobile_audio_labels[0] = SOUND_MZER01A //Yeah, what?
		mobile_audio_labels[1] = SOUND_MZER01B //Sorry, Carl, are you busy?
		mobile_audio_labels[2] = SOUND_MZER01C //Zero? No, man, whassup?
		mobile_audio_labels[3] = SOUND_MZER01D //Disaster! My landlord is selling the shop!
		mobile_audio_labels[4] = SOUND_MZER01E //I’ll have nowhere to live and no safe haven from Berkley...
		mobile_audio_labels[5] = SOUND_MZER01F //I’m looking into property at the moment.
		mobile_audio_labels[6] = SOUND_MZER01G //I’ll swing by and have a look see.

		cell_index_end = 6
	BREAK

	CASE ZERO_MOBILE_CALL2
		$mobile_print_labels[0] = &MZER02A	//Hello.
		$mobile_print_labels[1] = &MZER02B	//Carl! It’s Zero!
		$mobile_print_labels[2] = &MZER02C	//Oh hi, Zee, you weren’t around when I signed the deeds.
		$mobile_print_labels[3] = &MZER02D	//I was on a dangerous reconaisance mission, deep into enemy territory.
		$mobile_print_labels[4] = &MZER02E	//Eerr, right, sure.
		$mobile_print_labels[5] = &MZER02F	//Look, I should pop ‘round some time, have a look at the business, y’know.
		$mobile_print_labels[6] = &MZER02G	//Of course, of course.
		$mobile_print_labels[7] = &MZER02H	//I’ll have to tidy up a bit, this place is such a mess!
		$mobile_print_labels[8] = &MZER02J	//Don’t worry about it.
		$mobile_print_labels[9] = &MZER02K	//I’ll drop in soon.

		mobile_audio_labels[0] = SOUND_MZER02A	//Hello.
		mobile_audio_labels[1] = SOUND_MZER02B	//Carl! It’s Zero!
		mobile_audio_labels[2] = SOUND_MZER02C	//Oh hi, Zee, you weren’t around when I signed the deeds.
		mobile_audio_labels[3] = SOUND_MZER02D	//I was on a dangerous reconaisance mission, deep into enemy territory.
		mobile_audio_labels[4] = SOUND_MZER02E	//Eerr, right, sure.
		mobile_audio_labels[5] = SOUND_MZER02F	//Look, I should pop ‘round some time, have a look at the business, y’know.
		mobile_audio_labels[6] = SOUND_MZER02G	//Of course, of course.
		mobile_audio_labels[7] = SOUND_MZER02H	//I’ll have to tidy up a bit, this place is such a mess!
		mobile_audio_labels[8] = SOUND_MZER02J	//Don’t worry about it.
		mobile_audio_labels[9] = SOUND_MZER02K	//I’ll drop in soon.

		cell_index_end = 9
	BREAK

	// ***************************************JETHRO*******************************************************************************************


	CASE JETHRO_MOBILE_CALL1
		$mobile_print_labels[0] = &MJET_1A	//Yeah, what?
		$mobile_print_labels[1] = &MJET_1B	//Yo, man, it’s Jethro, dude!
		$mobile_print_labels[2] = &MJET_1C	//Yo, Jethro, whattup?
		$mobile_print_labels[3] = &MJET_1D	//Well, I was talking to Cesar and, don’t get me wrong, dude, 
		$mobile_print_labels[4] = &MJET_1E	//I mean, you are one out there dude, when it comes to driving, man,
		$mobile_print_labels[5] = &MJET_1F	//but Cesar, he told us how many cras you get through, dude, 
		$mobile_print_labels[6] = &MJET_1G	//and me and Dwaine were, like, whooaaa!
		$mobile_print_labels[7] = &MJET_1H	//What’s your point, Jethro?
		$mobile_print_labels[8] = &MJET_1J	//No point, man, no point! 
		$mobile_print_labels[9] = &MJET_1K	//Just that there’s an advanced driving school, like, 
		$mobile_print_labels[10] = &MJET_1L	//just up the road from the garage, man, in Doherty.
		$mobile_print_labels[11] = &MJET_1M	//Driving school? What you trying to say, dude?
		$mobile_print_labels[12] = &MJET_1N	//NOTHING, man! Nothing. It was Dwaine’s idea. 
		$mobile_print_labels[13] = &MJET_1O	//I think you’re, like, y’know, cool and shit. 
		$mobile_print_labels[14] = &MJET_1P	//I better go, CJ, I’ll see you later.

		mobile_audio_labels[0] = SOUND_MJET_1A	//Yeah, what?
		mobile_audio_labels[1] = SOUND_MJET_1B	//Yo, man, it’s Jethro, dude!
		mobile_audio_labels[2] = SOUND_MJET_1C	//Yo, Jethro, whattup?
		mobile_audio_labels[3] = SOUND_MJET_1D	//Well, I was talking to Cesar and, don’t get me wrong, dude, 
		mobile_audio_labels[4] = SOUND_MJET_1E	//I mean, you are one out there dude, when it comes to driving, man,
		mobile_audio_labels[5] = SOUND_MJET_1F	//but Cesar, he told us how many cras you get through, dude, 
		mobile_audio_labels[6] = SOUND_MJET_1G	//and me and Dwaine were, like, whooaaa!
		mobile_audio_labels[7] = SOUND_MJET_1H	//What’s your point, Jethro?
		mobile_audio_labels[8] = SOUND_MJET_1J	//No point, man, no point! 
		mobile_audio_labels[9] = SOUND_MJET_1K	//Just that there’s an advanced driving school, like, 
		mobile_audio_labels[10] = SOUND_MJET_1L	//just up the road from the garage, man, in Doherty.
		mobile_audio_labels[11] = SOUND_MJET_1M	//Driving school? What you trying to say, dude?
		mobile_audio_labels[12] = SOUND_MJET_1N	//NOTHING, man! Nothing. It was Dwaine’s idea. 
		mobile_audio_labels[13] = SOUND_MJET_1O	//I think you’re, like, y’know, cool and shit. 
		mobile_audio_labels[14] = SOUND_MJET_1P	//I better go, CJ, I’ll see you later.

		cell_index_end = 14
	BREAK

	CASE JETHRO_MOBILE_CALL2
		$mobile_print_labels[0] = &MJET_2A	//Yo, CJ, it’s, like, Jethro, dude!
		$mobile_print_labels[1] = &MJET_2B	//Hey, Jethro, how’s things?
		$mobile_print_labels[2] = &MJET_2C	//Yo, dude, the garage is coming along nicely, man.
		$mobile_print_labels[3] = &MJET_2D	//But I rang about something else.
		$mobile_print_labels[4] = &MJET_2E	//There’s an unofficial street racing club in San Fierro.
		$mobile_print_labels[5] = &MJET_2F	//Unofficial as in ‘illegal’, right?
		$mobile_print_labels[6] = &MJET_2G	//I don’t know what you’re talking about, dude. 
		$mobile_print_labels[7] = &MJET_2H	//Anyway, they meet up around the Driving School someplace.
		$mobile_print_labels[8] = &MJET_2J	//Thought you might, y’know, like to know.
		$mobile_print_labels[9] = &MJET_2K	//Thanks, Jethro, I’ll give it some consideration.
		$mobile_print_labels[10] = &MJET_2L	//Later, dude.

		mobile_audio_labels[0] = SOUND_MJET_2A	//Yo, CJ, it’s, like, Jethro, dude!
		mobile_audio_labels[1] = SOUND_MJET_2B	//Hey, Jethro, how’s things?
		mobile_audio_labels[2] = SOUND_MJET_2C	//Yo, dude, the garage is coming along nicely, man.
		mobile_audio_labels[3] = SOUND_MJET_2D	//But I rang about something else.
		mobile_audio_labels[4] = SOUND_MJET_2E	//There’s an unofficial street racing club in San Fierro.
		mobile_audio_labels[5] = SOUND_MJET_2F	//Unofficial as in ‘illegal’, right?
		mobile_audio_labels[6] = SOUND_MJET_2G	//I don’t know what you’re talking about, dude. 
		mobile_audio_labels[7] = SOUND_MJET_2H	//Anyway, they meet up around the Driving School someplace.
		mobile_audio_labels[8] = SOUND_MJET_2J	//Thought you might, y’know, like to know.
		mobile_audio_labels[9] = SOUND_MJET_2K	//Thanks, Jethro, I’ll give it some consideration.
		mobile_audio_labels[10] = SOUND_MJET_2L	//Later, dude.

		cell_index_end = 10
	BREAK

	CASE JETHRO_MOBILE_CALL3
		$mobile_print_labels[0] = &MJET_3A	//Hello!
		$mobile_print_labels[1] = &MJET_3B	//Yo, it’s Jethro, man.
		$mobile_print_labels[2] = &MJET_3C	//Me an’ Cesar have used our contacts to, like, get a wish list of cars, 
		$mobile_print_labels[3] = &MJET_3D	//but we’ll need to turn them around real quick, dude.
		$mobile_print_labels[4] = &MJET_3E	//There’s a showroom ‘round the block that’s come up for sale 
		$mobile_print_labels[5] = &MJET_3F	//and I thought it would be a good idea to make things look as legitimate as possible.
		$mobile_print_labels[6] = &MJET_3G	//Dude, that’s a great idea, I’ll look into it.
		$mobile_print_labels[7] = &MJET_3H	//I’ll catch you later.

		mobile_audio_labels[0] = SOUND_MJET_3A	//Hello!
		mobile_audio_labels[1] = SOUND_MJET_3B	//Yo, it’s Jethro, man.
		mobile_audio_labels[2] = SOUND_MJET_3C	//Me an’ Cesar have used our contacts to, like, get a wish list of cars, 
		mobile_audio_labels[3] = SOUND_MJET_3D	//but we’ll need to turn them around real quick, dude.
		mobile_audio_labels[4] = SOUND_MJET_3E	//There’s a showroom ‘round the block that’s come up for sale 
		mobile_audio_labels[5] = SOUND_MJET_3F	//and I thought it would be a good idea to make things look as legitimate as possible.
		mobile_audio_labels[6] = SOUND_MJET_3G	//Dude, that’s a great idea, I’ll look into it.
		mobile_audio_labels[7] = SOUND_MJET_3H	//I’ll catch you later.

		cell_index_end = 7
	BREAK

	CASE LOANSHARK_CALL1
		$mobile_print_labels[0] = &SHRK_1A //Hey,  Mr. Johnson. Just a friendly reminder that you owe me money. 
		$mobile_print_labels[1] = &SHRK_1B //Don't be a stranger.
		
		mobile_audio_labels[0] = SOUND_SHRK_1A //Hey,  Mr. Johnson. Just a friendly reminder that you owe me money.
		mobile_audio_labels[1] = SOUND_SHRK_1B //Don't be a stranger.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL2
		$mobile_print_labels[0] = &SHRK_2A //Hello, Carl Johnson. 
		$mobile_print_labels[1] = &SHRK_2B //You owe me money and I would consider it polite if you paid it back, understand?

		mobile_audio_labels[0] = SOUND_SHRK_2A
		mobile_audio_labels[1] = SOUND_SHRK_2B 

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL3
		$mobile_print_labels[0] = &SHRK_3A //Mr. Carl Johnson. My records show that you owe me money. 
		$mobile_print_labels[1] = &SHRK_3B //Probably just slipped your mind, but I thought I'd just jog your memory.

		mobile_audio_labels[0] = SOUND_SHRK_3A //Mr. Carl Johnson. My records show that you owe me money. 
		mobile_audio_labels[1] = SOUND_SHRK_3B //Probably just slipped your mind, but I thought I'd just jog your memory.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL4
		$mobile_print_labels[0] = &SHRK_4A //Mr. Johnson! I haven't seen you for a while, and you owe me money! 
		$mobile_print_labels[1] = &SHRK_4B //Consider your position and that of your friends and family!

		mobile_audio_labels[0] = SOUND_SHRK_4A //Mr. Johnson! I haven't seen you for a while, and you owe me money!
		mobile_audio_labels[1] = SOUND_SHRK_4B //Consider your position and that of your friends and family!

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL5
		$mobile_print_labels[0] = &SHRK_5A //Carl! I hope you're not spending too much cash. Especially since you've owed me money for so long. 
		$mobile_print_labels[1] = &SHRK_5B //Don't make an enemy of me, Carl.

		mobile_audio_labels[0] = SOUND_SHRK_5A //Carl! I hope you're not spending too much cash. Especially since you've owed me money for so long.
		mobile_audio_labels[1] = SOUND_SHRK_5B //Don't make an enemy of me, Carl.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL6
		$mobile_print_labels[0] = &SHRK_6A //Carl Johnson. I hear you have a sister. 
		$mobile_print_labels[1] = &SHRK_6B //Don't make me explain to her why you owe me so much money!

		mobile_audio_labels[0] = SOUND_SHRK_6A //Carl Johnson. I hear you have a sister. 
		mobile_audio_labels[1] = SOUND_SHRK_6B //Don't make me explain to her why you owe me so much money!

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL7
		$mobile_print_labels[0] = &SHRK_7A //I'm a patient man, Mr. Johnson, but I'm also a businessman, understand? 
		$mobile_print_labels[1] = &SHRK_7B //You owe me money, Carl, think about it.

		mobile_audio_labels[0] = SOUND_SHRK_7A //I'm a patient man, Mr. Johnson, but I'm also a businessman, understand?
		mobile_audio_labels[1] = SOUND_SHRK_7B //You owe me money, Carl, think about it.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL8
		$mobile_print_labels[0] = &SHRK_8A //Mr. Johnson, you owe me money. 
		$mobile_print_labels[1] = &SHRK_8B //A business relationship is built on trust and mutual respect. Think about it.

		mobile_audio_labels[0] = SOUND_SHRK_8A //Mr. Johnson, you owe me money. 
		mobile_audio_labels[1] = SOUND_SHRK_8B //A business relationship is built on trust and mutual respect. Think about it.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL9
		$mobile_print_labels[0] = &SHRK_9A //Mr. Johnson, I hear you're in the neighbourhood! 
		$mobile_print_labels[1] = &SHRK_9B //Some of my associates will be paying you a visit to talk about your debts.

		mobile_audio_labels[0] = SOUND_SHRK_9A //Mr. Johnson, I hear you're in the neighbourhood! 
		mobile_audio_labels[1] = SOUND_SHRK_9B //Some of my associates will be paying you a visit to talk about your debts.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL10
		$mobile_print_labels[0] = &SHRK10A //Carl Johnson. Little bird told me you're in town. 
		$mobile_print_labels[1] = &SHRK10B //You should have listened to me and paid your debts.

		mobile_audio_labels[0] = SOUND_SHRK10A //Carl Johnson. Little bird told me you're in town. 
		mobile_audio_labels[1] = SOUND_SHRK10B //You should have listened to me and paid your debts.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL11
		$mobile_print_labels[0] = &SHRK11A //A man of honour pays his debts, Mr. Johnson. 
		$mobile_print_labels[1] = &SHRK11B //You've left me no choice but to treat you with the same lack of respect you've shown me.

		mobile_audio_labels[0] = SOUND_SHRK11A //A man of honour pays his debts, Mr. Johnson. 
		mobile_audio_labels[1] = SOUND_SHRK11B //You've left me no choice but to treat you with the same lack of respect you've shown me.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL12
		$mobile_print_labels[0] = &SHRK12A //I am not a charity, Mr. Johnson, I'm a businessman. 
		$mobile_print_labels[1] = &SHRK12B //You should have paid up while you had the chance.

		mobile_audio_labels[0] = SOUND_SHRK12A //I am not a charity, Mr. Johnson, I'm a businessman.
		mobile_audio_labels[1] = SOUND_SHRK12B //You should have paid up while you had the chance.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL13
		$mobile_print_labels[0] = &SHRK13A //Mr. Johnson, I want my fucking money! 
		$mobile_print_labels[1] = &SHRK13B //I'm sending someone round to pay you a little courtesy call.

		mobile_audio_labels[0] = SOUND_SHRK13A //Mr. Johnson, I want my fucking money! 
		mobile_audio_labels[1] = SOUND_SHRK13B //I'm sending someone round to pay you a little courtesy call.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL14
		$mobile_print_labels[0] = &SHRK14A //I don't think you're getting the message, Mr. Johnson: I WANT MY MONEY! 
		$mobile_print_labels[1] = &SHRK14B //I'll let you negotiate with my collection department.

		mobile_audio_labels[0] = SOUND_SHRK14A //I don't think you're getting the message, Mr. Johnson: I WANT MY MONEY!
		mobile_audio_labels[1] = SOUND_SHRK14B //I'll let you negotiate with my collection department.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL15
		$mobile_print_labels[0] = &SHRK15A //The time for pleasantries is past, Mr. Johnson. 
		$mobile_print_labels[1] = &SHRK15B //You've given me no choice but to get a little more persuasive about your debt problems.

		mobile_audio_labels[0] = SOUND_SHRK15A //The time for pleasantries is past, Mr. Johnson. 
		mobile_audio_labels[1] = SOUND_SHRK15B //You've given me no choice but to get a little more persuasive about your debt problems.

		cell_index_end = 1
	BREAK

	CASE LOANSHARK_CALL16
		$mobile_print_labels[0] = &SHRK16A //You had your chance to pay me, Mr. Johnson. 
		$mobile_print_labels[1] = &SHRK16B //You've left me no option in this matter - I WANT MY MONEY!

		mobile_audio_labels[0] = SOUND_SHRK16A //You had your chance to pay me, Mr. Johnson. 
		mobile_audio_labels[1] = SOUND_SHRK16B //You've left me no option in this matter - I WANT MY MONEY!

		cell_index_end = 1
	BREAK

	ENDSWITCH

ELSE //call_number IS >= COOCHIE_MOBILE
	
// ***************************************GIRLFRIENDS************************************************************************************
	SWITCH call_number



	CASE COOCHIE_MOBILE
		$mobile_print_labels[0] = &MDEN_2A //Yo, Carl, it’s me, Denise. Whassappening? We gonna hang out or what?
		$mobile_print_labels[1] = &MDEN_2D //Ok sure, baby, I’ll swing by and pick you up!

		mobile_audio_labels[0] = SOUND_MDEN_2A 
		mobile_audio_labels[1] = SOUND_MDEN_2D 

		cell_index_end = 1
	BREAK
	CASE COOCHIE_MOBILE2
		$mobile_print_labels[0] = &MDEN_3A //Whattup Carl? It’s Denise, you down for doing some shit?
		$mobile_print_labels[1] = &MDEN_3B //I hear you, baby! I’ll drop by later.

		mobile_audio_labels[0] = SOUND_MDEN_3A
		mobile_audio_labels[1] = SOUND_MDEN_3B

		cell_index_end = 1
	BREAK
	CASE COOCHIE_MOBILE3
		$mobile_print_labels[0] = &MDEN_4A //Holmes, it’s Denise. I need some attention – don’t make me come and put a cap in yo’ass!
		$mobile_print_labels[1] = &MDEN_4D //I been neglecting my girl! Get ready, I’ll come pick you up.

		mobile_audio_labels[0] = SOUND_MDEN_4A
		mobile_audio_labels[1] = SOUND_MDEN_4D

		cell_index_end = 1
	BREAK
	CASE COOCHIE_MOBILE4
		$mobile_print_labels[0] = &MDEN_5A //Carl, it’s Denise. Where you been, baby, I’m getting lonely!
		$mobile_print_labels[1] = &MDEN_5B //Yo, Denise, baby, I am going to treat you so good. I’ll pick you up later.

		mobile_audio_labels[0] = SOUND_MDEN_5A
		mobile_audio_labels[1] = SOUND_MDEN_5B

		cell_index_end = 1
	BREAK
	CASE COOCHIE_MOBILE5
		$mobile_print_labels[0] = &MDEN_6A //Yo, it’s Denise. You gonna come ‘round and make me feel special?
		$mobile_print_labels[1] = &MDEN_6D //Hey baby, I been busy but I am about to come ‘round and make it up to you, a’ight!

		mobile_audio_labels[0] = SOUND_MDEN_6A
		mobile_audio_labels[1] = SOUND_MDEN_6D

		cell_index_end = 1
	BREAK
	CASE COOCHIE_MOBILE6
		$mobile_print_labels[0] = &MDEN_7D //CJ, its me, Denise! I’m getting real lonely here without you. Maybe I should date one of your friends instead. 
		$mobile_print_labels[1] = &MDEN_4D //I been neglecting my girl! Get ready, I’ll come pick you up.

		mobile_audio_labels[0] = SOUND_MDEN_7D
		mobile_audio_labels[1] = SOUND_MDEN_4D

		cell_index_end = 1
	BREAK
	CASE COOCHIE_MOBILE7
		$mobile_print_labels[0] = &MDEN_8D //Don’t make me feel like shit. Please! Say you’re gonna come. PLEASE, CJ! 
		$mobile_print_labels[1] = &MDEN_6D //Hey baby, I been busy but I am about to come ‘round and make it up to you, a’ight!

		mobile_audio_labels[0] = SOUND_MDEN_8D
		mobile_audio_labels[1] = SOUND_MDEN_6D

		cell_index_end = 1
	BREAK
	CASE COOCHIE_MOBILE8
		$mobile_print_labels[0] = &MDEN_9D //CJ, its Denise! You’re breaking my heart, dog. Don’t treat me like a ho. I’m a homegirl, not a ho. Come on. 
		$mobile_print_labels[1] = &MDEN_5B //Yo, Denise, baby, I am going to treat you so good. I’ll pick you up later.

		mobile_audio_labels[0] = SOUND_MDEN_9D
		mobile_audio_labels[1] = SOUND_MDEN_5B

		cell_index_end = 1
	BREAK
	 
	CASE COOCHIE_DUMP
		//Denise Robinson
		$mobile_print_labels[0] = &MDEN_1A	//Yo.
		$mobile_print_labels[1] = &MDEN_1B	//Chickenhead asshole! 
		$mobile_print_labels[2] = &MDEN_1C	//What? Denise?
		$mobile_print_labels[3] = &MDEN_1D	//Don’t even think about sweet-talking me, you no good crack pusher!
		$mobile_print_labels[4] = &MDEN_1E	//What? What you on about, girl?
		$mobile_print_labels[5] = &MDEN_1F	//I’d heard the rumours about you Grove Street cluckers, about Big Smoke an’all,
		$mobile_print_labels[6] = &MDEN_1G	//but I thought I’ll give that boy a chance.
		$mobile_print_labels[7] = &MDEN_1H	//But all my friends were right.
		$mobile_print_labels[8] = &MDEN_1J	//Only some base-pushing asshole would treat a girl like this!
		$mobile_print_labels[9] = &MDEN_1K	//Now wait a minute!
		$mobile_print_labels[10] = &MDEN_1L	//Can it, Carl. We’re over, finished.

		mobile_audio_labels[0] = SOUND_MDEN_1A	//Yo.
		mobile_audio_labels[1] = SOUND_MDEN_1B	//Chickenhead asshole! 
		mobile_audio_labels[2] = SOUND_MDEN_1C	//What? Denise?
		mobile_audio_labels[3] = SOUND_MDEN_1D	//Don’t even think about sweet-talking me, you no good crack pusher!
		mobile_audio_labels[4] = SOUND_MDEN_1E	//What? What you on about, girl?
		mobile_audio_labels[5] = SOUND_MDEN_1F	//I’d heard the rumours about you Grove Street cluckers, about Big Smoke an’all,
		mobile_audio_labels[6] = SOUND_MDEN_1G	//but I thought I’ll give that boy a chance.
		mobile_audio_labels[7] = SOUND_MDEN_1H	//But all my friends were right.
		mobile_audio_labels[8] = SOUND_MDEN_1J	//Only some base-pushing asshole would treat a girl like this!
		mobile_audio_labels[9] = SOUND_MDEN_1K	//Now wait a minute!
		mobile_audio_labels[10] = SOUND_MDEN_1L	//Can it, Carl. We’re over, finished.	 

		cell_index_end = 10
	BREAK 


	CASE MICHELLE_MOBILE
			
		$mobile_print_labels[0] = &MMICH2A //Yo, Carl, it’s Michelle. You want to come over and talk cam shafts?
		$mobile_print_labels[1] = &MMICH2B //Hey, Michelle! I’ll be right over with my latest wheels!

		mobile_audio_labels[0] = SOUND_MMICH2A
		mobile_audio_labels[1] = SOUND_MMICH2B

		cell_index_end = 1
	BREAK
	CASE MICHELLE_MOBILE2
			
		$mobile_print_labels[0] = &MMICH3A	//CJ, it’s Michelle! I was thinking of going for a ride or something, you fancy?
		$mobile_print_labels[1] = &MMICH3D	//Michelle! Baby, I’m coming over there for a date you will never forget!

		mobile_audio_labels[0] = SOUND_MMICH3A
		mobile_audio_labels[1] = SOUND_MMICH3D	

		cell_index_end = 1
	BREAK
	CASE MICHELLE_MOBILE3
			
		$mobile_print_labels[0] = &MMICH4A	//Hey CJ, it’s lonely old me, Michelle. Just hanging about with nothing to do...
		$mobile_print_labels[1] = &MMICH4B	//Hey, sweet cheeks, get ready, cuz I’m coming over there!

		mobile_audio_labels[0] = SOUND_MMICH4A	//Hey CJ, it’s lonely old me, Michelle. Just hanging about with nothing to do...
		mobile_audio_labels[1] = SOUND_MMICH4B	//Hey, sweet cheeks, get ready, cuz I’m coming over there!

		cell_index_end = 1
	BREAK
	CASE MICHELLE_MOBILE4
			
		$mobile_print_labels[0] = &MMICH5A	//Hey you, it’s me, Michelle! Get over here and put your charm to good use!
		$mobile_print_labels[1] = &MMICH5D	//Hey, baby, I’ve missed you too! I’ll swing by your place soon as I can!

		mobile_audio_labels[0] = SOUND_MMICH5A	//Hey you, it’s me, Michelle! Get over here and put your charm to good use!
		mobile_audio_labels[1] = SOUND_MMICH5D	//Hey, baby, I’ve missed you too! I’ll swing by your place soon as I can!

		cell_index_end = 1
	BREAK
	CASE MICHELLE_MOBILE5
			
		$mobile_print_labels[0] = &MMICH6A   //Hi CJ! What you up to? You wanna go someplace, maybe?
		$mobile_print_labels[1] = &MMICH6D	//My love, my life, my dreams, baby! Stay right where you are, I’ll be right over!

		mobile_audio_labels[0] = SOUND_MMICH6A   //Hi CJ! What you up to? You wanna go someplace, maybe?
		mobile_audio_labels[1] = SOUND_MMICH6D	//My love, my life, my dreams, baby! Stay right where you are, I’ll be right over!

		cell_index_end = 1
	BREAK
	CASE MICHELLE_MOBILE6
			
		$mobile_print_labels[0] = &MMICH7D	//Hey Cj, it’s Michelle. C’mon over and pick me up!
		$mobile_print_labels[1] = &MMICH3D	//Michelle! Baby, I’m coming over there for a date you will never forget!

		mobile_audio_labels[0] = SOUND_MMICH7D	//Hey Cj, it’s Michelle. C’mon over and pick me up!
		mobile_audio_labels[1] = SOUND_MMICH3D	//Michelle! Baby, I’m coming over there for a date you will never forget!

		cell_index_end = 1
	BREAK
	CASE MICHELLE_MOBILE7
			
		$mobile_print_labels[0] = &MMICH8D	//Hey, it’s Michelle, come over CJ, I miss you!
		$mobile_print_labels[1] = &MMICH5D	//Hey, baby, I’ve missed you too! I’ll swing by your place soon as I can!

		mobile_audio_labels[0] = SOUND_MMICH8D	//Hey, it’s Michelle, come over CJ, I miss you!
		mobile_audio_labels[1] = SOUND_MMICH5D	//Hey, baby, I’ve missed you too! I’ll swing by your place soon as I can!

		cell_index_end = 1
	BREAK
	CASE MICHELLE_MOBILE8
			
		$mobile_print_labels[0] = &MMICH9D	//Pick me up, CJ, pick me up!
		$mobile_print_labels[1] = &MMICH4B	//Hey, sweet cheeks, get ready, cuz I’m coming over there!

		mobile_audio_labels[0] = SOUND_MMICH9D	//Pick me up, CJ, pick me up!
		mobile_audio_labels[1] = SOUND_MMICH4B	//Hey, sweet cheeks, get ready, cuz I’m coming over there!

		cell_index_end = 1
	BREAK

	CASE MICHELLE_DUMP
		//Michelle Cannes		
		$mobile_print_labels[0] = &MMICH1A	//Johnson by name, Johnson by nature!
		$mobile_print_labels[1] = &MMICH1B	//You got that right, dick-for-brains! 
		$mobile_print_labels[2] = &MMICH1C	//Hey, Michelle, what’s up, baby?
		$mobile_print_labels[3] = &MMICH1D	//You don’t know? Carl, you are a walking passion killer! 
		$mobile_print_labels[4] = &MMICH1E	//You haven’t one clue what a woman wants or how a lady likes to be treated!
		$mobile_print_labels[5] = &MMICH1F	//C’mon, Michelle, I’m a fool for you, girl!
		$mobile_print_labels[6] = &MMICH1G	//Fool’s the word, alright, pig-headed asshole fool!
		$mobile_print_labels[7] = &MMICH1H	//We’re over, ok? Don’t bother coming ‘round any more!
		$mobile_print_labels[8] = &MMICH1J	//Michelle, baby... Michelle?

		mobile_audio_labels[0] = SOUND_MMICH1A	//Johnson by name, Johnson by nature!
		mobile_audio_labels[1] = SOUND_MMICH1B	//You got that right, dick-for-brains!  
		mobile_audio_labels[2] = SOUND_MMICH1C	//Hey, Michelle, what’s up, baby?
		mobile_audio_labels[3] = SOUND_MMICH1D	//You don’t know? Carl, you are a walking passion killer! 
		mobile_audio_labels[4] = SOUND_MMICH1E	//You haven’t one clue what a woman wants or how a lady likes to be treated!
		mobile_audio_labels[5] = SOUND_MMICH1F	//C’mon, Michelle, I’m a fool for you, girl!
		mobile_audio_labels[6] = SOUND_MMICH1G	//Fool’s the word, alright, pig-headed asshole fool!
		mobile_audio_labels[7] = SOUND_MMICH1H	//We’re over, ok? Don’t bother coming ‘round any more!
		mobile_audio_labels[8] = SOUND_MMICH1J	//Michelle, baby... Michelle?

		cell_index_end = 8
	BREAK 

	CASE KYLIE_MOBILE
			
		$mobile_print_labels[0] = &MHEL_2A	//Carl? Helena. I’m in Flint looking for some fun. You want to meet up?
		$mobile_print_labels[1] = &MHEL_2B	//Hey, Helena! I’ll be over as soon as I can!

		mobile_audio_labels[0] = SOUND_MHEL_2A	//Carl? Helena. I’m in Flint looking for some fun. You want to meet up?
		mobile_audio_labels[1] = SOUND_MHEL_2B	//Hey, Helena! I’ll be over as soon as I can!

		cell_index_end = 1
	BREAK
	CASE KYLIE_MOBILE2
			
		$mobile_print_labels[0] = &MHEL_3A	//Hi Carl! It’s Helena! I’m in desperate need of some R&R!
		$mobile_print_labels[1] = &MHEL_3B	//Helena! Hold tight, baby, I’m coming on over!

		mobile_audio_labels[0] = SOUND_MHEL_3A	//Hi Carl! It’s Helena! I’m in desperate need of some R&R!
		mobile_audio_labels[1] = SOUND_MHEL_3B	//Helena! Hold tight, baby, I’m coming on over!

		cell_index_end = 1
	BREAK
	CASE KYLIE_MOBILE3
			
		$mobile_print_labels[0] = &MHEL_4A	//Oh Carl, it’s Helena! It’s been a hellish week and I need to unwind!
		$mobile_print_labels[1] = &MHEL_4B	//Hello Helena, I’ve missed you, baby! I’ll come over as soon as!

		mobile_audio_labels[0] = SOUND_MHEL_4A	//Oh Carl, it’s Helena! It’s been a hellish week and I need to unwind!
		mobile_audio_labels[1] = SOUND_MHEL_4B	//Hello Helena, I’ve missed you, baby! I’ll come over as soon as!

		cell_index_end = 1
	BREAK
	CASE KYLIE_MOBILE4
			
		$mobile_print_labels[0] = &MHEL_6A	//Carl! I’ve been working sooo hard and I need the attentions of a dashing young man!
		$mobile_print_labels[1] = &MHEL_6B	//Helena, baby, I was just thinking of calling you! I’ll be right over and pick you up!

		mobile_audio_labels[0] = SOUND_MHEL_6A	//Carl! I’ve been working sooo hard and I need the attentions of a dashing young man!
		mobile_audio_labels[1] = SOUND_MHEL_6B	//Helena, baby, I was just thinking of calling you! I’ll be right over and pick you up!

		cell_index_end = 1
	BREAK
	CASE KYLIE_MOBILE5
			
		$mobile_print_labels[0] = &MHEL_5A	//Hello, Carl, it’s Helena! I need a strong young Johnson to show me a good time!
		$mobile_print_labels[1] = &MHEL_5B	//No sweat, baby, I’ll be there before you know it!

		mobile_audio_labels[0] = SOUND_MHEL_5A	//Hello, Carl, it’s Helena! I need a strong young Johnson to show me a good time!
		mobile_audio_labels[1] = SOUND_MHEL_5B	//No sweat, baby, I’ll be there before you know it!

		cell_index_end = 1
	BREAK
	CASE KYLIE_MOBILE6
			
		$mobile_print_labels[0] = &MHELD7D	//Carl, its Helena, come on, we gotta hang out again soon.
		$mobile_print_labels[1] = &MHEL_2B	//Hey, Helena! I’ll be over as soon as I can!

		mobile_audio_labels[0] = SOUND_MHELD7D	//Carl, its Helena, come on, we gotta hang out again soon.
		mobile_audio_labels[1] = SOUND_MHEL_2B	//Hey, Helena! I’ll be over as soon as I can!

		cell_index_end = 1
	BREAK
	CASE KYLIE_MOBILE7

		$mobile_print_labels[0] = &MHELD9D	//Carl, C’est moi, Helena, come on darling, please, don’t be a bastard.
		$mobile_print_labels[1] = &MHEL_4B	//Hello Helena, I’ve missed you, baby! I’ll come over as soon as!

		mobile_audio_labels[0] = SOUND_MHELD9D	//Carl, C’est moi, Helena, come on darling, please, don’t be a bastard.
		mobile_audio_labels[1] = SOUND_MHEL_4B	//Hello Helena, I’ve missed you, baby! I’ll come over as soon as!

		cell_index_end = 1
	BREAK
	CASE KYLIE_MOBILE8
			
		$mobile_print_labels[0] = &MHELD8D	//Carl, please, it’s Helena – we gotta hang out again soon. Party, you know.
		$mobile_print_labels[1] = &MHEL_3B	//Helena! Hold tight, baby, I’m coming on over!

		mobile_audio_labels[0] = SOUND_MHELD8D	//Carl, please, it’s Helena – we gotta hang out again soon. Party, you know.
		mobile_audio_labels[1] = SOUND_MHEL_3B	//Helena! Hold tight, baby, I’m coming on over!

		cell_index_end = 1
	BREAK

	CASE KYLIE_DUMP
		//Helena Wankstein		
		$mobile_print_labels[0] = &MHEL_1A	//Yo, Johnson’s the name and loving’s my game!
		$mobile_print_labels[1] = &MHEL_1B	//You bastard waste of space!
		$mobile_print_labels[2] = &MHEL_1C	//Helena? Whoa, now, I thought you was someone else!
		$mobile_print_labels[3] = &MHEL_1D	//Another ho, or one of your gang banging chums?
		$mobile_print_labels[4] = &MHEL_1E	//Chums? Girl you gotta get out more!
		$mobile_print_labels[5] = &MHEL_1F	//Well if the option is getting out with you or shooting my own leg off, I’m reaching for the gun!
		$mobile_print_labels[6] = &MHEL_1G	//Look, Helena, girl-
		$mobile_print_labels[7] = &MHEL_1H	//Don’t you ‘girl’ me! I’m not one of your ghetto friends! In fact, I’m not your friend at all!
		$mobile_print_labels[8] = &MHEL_1J	//Goodbye, Carl Johnson!
		$mobile_print_labels[9] = &MHEL_1K	//Helena gimme a break! Helena? Shit.

		mobile_audio_labels[0] = SOUND_MHEL_1A	//Yo, Johnson’s the name and loving’s my game!
		mobile_audio_labels[1] = SOUND_MHEL_1B	//You bastard waste of space! 
		mobile_audio_labels[2] = SOUND_MHEL_1C	//Helena? Whoa, now, I thought you was someone else!
		mobile_audio_labels[3] = SOUND_MHEL_1D	//Another ho, or one of your gang banging chums?
		mobile_audio_labels[4] = SOUND_MHEL_1E	//Chums? Girl you gotta get out more!
		mobile_audio_labels[5] = SOUND_MHEL_1F	//Well if the option is getting out with you or shooting my own leg off, I’m reaching for the gun!
		mobile_audio_labels[6] = SOUND_MHEL_1G	//Look, Helena, girl-
		mobile_audio_labels[7] = SOUND_MHEL_1H	//Don’t you ‘girl’ me! I’m not one of your ghetto friends! In fact, I’m not your friend at all!
		mobile_audio_labels[8] = SOUND_MHEL_1J	//Goodbye, Carl Johnson!
		mobile_audio_labels[9] = SOUND_MHEL_1K	//Helena gimme a break! Helena? Shit.

		cell_index_end = 9
	BREAK 

	CASE BARBARA_MOBILE
			
		$mobile_print_labels[0] = &MBARB2A	//Carl? Hi, it’s Barbara. I thought we could hang out some time soon.
		$mobile_print_labels[1] = &MBARB2D	//Ok, Barbara, that sounds good. I’ll swing by later.

		mobile_audio_labels[0] = SOUND_MBARB2A	//Carl? Hi, it’s Barbara. I thought we could hang out some time soon.
		mobile_audio_labels[1] = SOUND_MBARB2D	//Ok, Barbara, that sounds good. I’ll swing by later.

		cell_index_end = 1
	BREAK
	CASE BARBARA_MOBILE2
			
		$mobile_print_labels[0] = &MBARB3A	//Hello, CJ, I hope you’re keeping out of trouble. You fancy going out?
		$mobile_print_labels[1] = &MBARB3B	//Hi Barbara. Ok, I’m down for that – pick you up as soon as I can!

		mobile_audio_labels[0] = SOUND_MBARB3A	//Hello, CJ, I hope you’re keeping out of trouble. You fancy going out?
		mobile_audio_labels[1] = SOUND_MBARB3B	//Hi Barbara. Ok, I’m down for that – pick you up as soon as I can!

		cell_index_end = 1
	BREAK
	CASE BARBARA_MOBILE3
			
		$mobile_print_labels[0] = &MBARB4A	//CJ! Hey, it’s Barbara! Let’s get together, yeah?
		$mobile_print_labels[1] = &MBARB4D	//I’m coming over there and you better have those cuffs ready!

		mobile_audio_labels[0] = SOUND_MBARB4A	//CJ! Hey, it’s Barbara! Let’s get together, yeah?
		mobile_audio_labels[1] = SOUND_MBARB4D	//I’m coming over there and you better have those cuffs ready!

		cell_index_end = 1
	BREAK
	CASE BARBARA_MOBILE4
			
		$mobile_print_labels[0] = &MBARB5A	//Hello, it’s Barabara. Take me out, baby, take me out!
		$mobile_print_labels[1] = &MBARB5D	//Hello Officer Schternvart! Been thinking a lot about you – I’ll see you in five.

		mobile_audio_labels[0] = SOUND_MBARB5A	//Hello, it’s Barabara. Take me out, baby, take me out!
		mobile_audio_labels[1] = SOUND_MBARB5D	//Hello Officer Schternvart! Been thinking a lot about you – I’ll see you in five.

		cell_index_end = 1
	BREAK
	CASE BARBARA_MOBILE5
			
		$mobile_print_labels[0] = &MBARB6A	//Hey, CJ, it’s Barbara. Let’s go out and do something!
		$mobile_print_labels[1] = &MBARB6B	//Hi, Barbara, whattup? Look, I’ll be over to pick you up in, say, ten minutes, ok?

		mobile_audio_labels[0] = SOUND_MBARB6A	//Hey, CJ, it’s Barbara. Let’s go out and do something!
		mobile_audio_labels[1] = SOUND_MBARB6B	//Hi, Barbara, whattup? Look, I’ll be over to pick you up in, say, ten minutes, ok?

		cell_index_end = 1
	BREAK
	CASE BARBARA_MOBILE6
			
		$mobile_print_labels[0] = &MBARB7D	//CJ, it’s Barabara, what you waiting for, come on over!
		$mobile_print_labels[1] = &MBARB5D	//Hello Officer Schternvart! Been thinking a lot about you – I’ll see you in five.

		mobile_audio_labels[0] = SOUND_MBARB7D	//CJ, it’s Barabara, what you waiting for, come on over!
		mobile_audio_labels[1] = SOUND_MBARB5D	//Hello Officer Schternvart! Been thinking a lot about you – I’ll see you in five.

		cell_index_end = 1
	BREAK
	CASE BARBARA_MOBILE7
			
		$mobile_print_labels[0] = &MBARB8D	//Hi, it’s Barbara! Get over here and lets do something!
		$mobile_print_labels[1] = &MBARB3B	//Hi Barbara. Ok, I’m down for that – pick you up as soon as I can!

		mobile_audio_labels[0] = SOUND_MBARB8D	//Hi, it’s Barbara! Get over here and lets do something!
		mobile_audio_labels[1] = SOUND_MBARB3B	//Hi Barbara. Ok, I’m down for that – pick you up as soon as I can!

		cell_index_end = 1
	BREAK
	CASE BARBARA_MOBILE8
			
		$mobile_print_labels[0] = &MBARB9D	//CJ, it’s Babara – get over here and take me out, honey!
		$mobile_print_labels[1] = &MBARB6B	//Hi, Barbara, whattup? Look, I’ll be over to pick you up in, say, ten minutes, ok?

		mobile_audio_labels[0] = SOUND_MBARB9D	//CJ, it’s Babara – get over here and take me out, honey!
		mobile_audio_labels[1] = SOUND_MBARB6B	//Hi, Barbara, whattup? Look, I’ll be over to pick you up in, say, ten minutes, ok?

		cell_index_end = 1
	BREAK

	CASE BARBARA_DUMP
		//Barbara Schternvart		
		$mobile_print_labels[0] = &MBARB1A	//Badass Carl Johnson, one-man crime wave and all-round ghetto star!
		$mobile_print_labels[1] = &MBARB1B	//I knew it! You fucking piece of shit, I knew it! 
		$mobile_print_labels[2] = &MBARB1C	//Who is this?
		$mobile_print_labels[3] = &MBARB1D	//It’s Barbara, you asshole! I thought you were a nice guy! 
		$mobile_print_labels[4] = &MBARB1E	//You said you’d be good for me! 
		$mobile_print_labels[5] = &MBARB1F	//You realise how hard it is for a divorcee to find a man, ANY man?
		$mobile_print_labels[6] = &MBARB1G	//Oh shut up, listen to yourself!
		$mobile_print_labels[7] = &MBARB1H	//What? Listen to MY self? Listen to MY self?
		$mobile_print_labels[8] = &MBARB1J	//Yeah, always going on about how you is damaged goods and shit!
		$mobile_print_labels[9] = &MBARB1K	//You try bringing up a kid and holding down a job on your own! 
		$mobile_print_labels[10] = &MBARB1L	//That bastard tore out my heart! My HEART, Carl!
		$mobile_print_labels[11] = &MBARB1M	//Bitch, you been heartless from the day you popped out. 
		$mobile_print_labels[12] = &MBARB1N	//Bet that poor bastard put up with you for years 
		$mobile_print_labels[13] = &MBARB1O	//before he got the guts, no, THE HELP to escape!
		$mobile_print_labels[14] = &MBARB1P	//You bastard! YOU BASTARD! I have friends in the force! 
		$mobile_print_labels[15] = &MBARB1Q	//They’ll hunt you down! You’re going to jail, Carl, do you hear me, JAIL!
		$mobile_print_labels[16] = &MBARB1R	//Shut the fuck up, bitch!

		mobile_audio_labels[0] = SOUND_MBARB1A	//Badass Carl Johnson, one-man crime wave and all-round ghetto star!
		mobile_audio_labels[1] = SOUND_MBARB1B	//I knew it! You fucking piece of shit, I knew it! 
		mobile_audio_labels[2] = SOUND_MBARB1C	//Who is this?
		mobile_audio_labels[3] = SOUND_MBARB1D	//It’s Barbara, you asshole! I thought you were a nice guy!  
		mobile_audio_labels[4] = SOUND_MBARB1E	//You said you’d be good for me! 
		mobile_audio_labels[5] = SOUND_MBARB1F	//You realise how hard it is for a divorcee to find a man, ANY man?
		mobile_audio_labels[6] = SOUND_MBARB1G	//Oh shut up, listen to yourself!
		mobile_audio_labels[7] = SOUND_MBARB1H	//What? Listen to MY self? Listen to MY self?
		mobile_audio_labels[8] = SOUND_MBARB1J	//Yeah, always going on about how you is damaged goods and shit!
		mobile_audio_labels[9] = SOUND_MBARB1K	//You try bringing up a kid and holding down a job on your own! 
		mobile_audio_labels[10] = SOUND_MBARB1L	//That bastard tore out my heart! My HEART, Carl!
		mobile_audio_labels[11] = SOUND_MBARB1M	//Bitch, you been heartless from the day you popped out. 
		mobile_audio_labels[12] = SOUND_MBARB1N	//Bet that poor bastard put up with you for years 
		mobile_audio_labels[13] = SOUND_MBARB1O	//before he got the guts, no, THE HELP to escape!
		mobile_audio_labels[14] = SOUND_MBARB1P	//You bastard! YOU BASTARD! I have friends in the force! 
		mobile_audio_labels[15] = SOUND_MBARB1Q	//They’ll hunt you down! You’re going to jail, Carl, do you hear me, JAIL!
		mobile_audio_labels[16] = SOUND_MBARB1R	//Shut the fuck up, bitch!

		cell_index_end = 16
	BREAK 

	CASE SUZIE_MOBILE
			
		$mobile_print_labels[0] = &MZAHN2A	//Hey, Carl, it’s Katie. You fancy doing something some time soon, I’m bored?
		$mobile_print_labels[1] = &MZAHN2D	//Katie! I’ll be over to pick you up soon as I’m finished up here.

		mobile_audio_labels[0] = SOUND_MZAHN2A	//Hey, Carl, it’s Katie. You fancy doing something some time soon, I’m bored?
		mobile_audio_labels[1] = SOUND_MZAHN2D	//Katie! I’ll be over to pick you up soon as I’m finished up here.

		cell_index_end = 1
	BREAK
	CASE SUZIE_MOBILE2
			
		$mobile_print_labels[0] = &MZAHN3A	//Carl, it’s Katie, when you going to take me out, huh?
		$mobile_print_labels[1] = &MZAHN3B	//Hey, Katie, I was just thinking about you! I’ll drop by later and we can go out.

		mobile_audio_labels[0] = SOUND_MZAHN3A	//Carl, it’s Katie, when you going to take me out, huh?
		mobile_audio_labels[1] = SOUND_MZAHN3B	//Hey, Katie, I was just thinking about you! I’ll drop by later and we can go out.

		cell_index_end = 1
	BREAK
	CASE SUZIE_MOBILE3
			
		$mobile_print_labels[0] = &MZAHN4A	//Hi, it’s me, Katie! I got a bit of spare time, so I was wondering....
		$mobile_print_labels[1] = &MZAHN4D	//Hey baby! Get ready and I’ll pick you up real soon, ok?

		mobile_audio_labels[0] = SOUND_MZAHN4A	//Hi, it’s me, Katie! I got a bit of spare time, so I was wondering....
		mobile_audio_labels[1] = SOUND_MZAHN4D	//Hey baby! Get ready and I’ll pick you up real soon, ok?

		cell_index_end = 1
	BREAK
	CASE SUZIE_MOBILE4
			
		$mobile_print_labels[0] = &MZAHN5A	//Carl? Katie! Let’s go out!
		$mobile_print_labels[1] = &MZAHN5B	//Katie, I was just about to call you! I’m gonna finish up then swing by and pick you up, ok?

		mobile_audio_labels[0] = SOUND_MZAHN5A	//Carl? Katie! Let’s go out!
		mobile_audio_labels[1] = SOUND_MZAHN5B	//Katie, I was just about to call you! I’m gonna finish up then swing by and pick you up, ok?

		cell_index_end = 1
	BREAK
	CASE SUZIE_MOBILE5
			
		$mobile_print_labels[0] = &MZAHN6A	//Hey babe, it’s Katie. Let’s go out up town or something!
		$mobile_print_labels[1] = &MZAHN6D	//Hey sweet thang! I’m coming over there to take you out – go get ready!

		mobile_audio_labels[0] = SOUND_MZAHN6A	//Hey babe, it’s Katie. Let’s go out up town or something!
		mobile_audio_labels[1] = SOUND_MZAHN6D	//Hey sweet thang! I’m coming over there to take you out – go get ready!

		cell_index_end = 1
	BREAK
	CASE SUZIE_MOBILE6
			
		$mobile_print_labels[0] = &MZAHN7D	//Hey, CJ, it’s Katie, take me out, take me out!
		$mobile_print_labels[1] = &MZAHN2D	//Katie! I’ll be over to pick you up soon as I’m finished up here.

		mobile_audio_labels[0] = SOUND_MZAHN7D	//Hey, CJ, it’s Katie, take me out, take me out!
		mobile_audio_labels[1] = SOUND_MZAHN2D	//Katie! I’ll be over to pick you up soon as I’m finished up here.

		cell_index_end = 1
	BREAK
	CASE SUZIE_MOBILE7
			
		$mobile_print_labels[0] = &MZAHN9D	//Hi, it’s Katie, I really miss you, baby, come pick me up!
		$mobile_print_labels[1] = &MZAHN4D	//Hey baby! Get ready and I’ll pick you up real soon, ok?

		mobile_audio_labels[0] = SOUND_MZAHN9D	//Hi, it’s Katie, I really miss you, baby, come pick me up!
		mobile_audio_labels[1] = SOUND_MZAHN4D	//Hey baby! Get ready and I’ll pick you up real soon, ok?

		cell_index_end = 1
	BREAK
	CASE SUZIE_MOBILE8
			
		$mobile_print_labels[0] = &MZAHN8D	//Hey CJ, it’s Katie, I want to see you, baby!
		$mobile_print_labels[1] = &MZAHN3B	//Hey, Katie, I was just thinking about you! I’ll drop by later and we can go out.

		mobile_audio_labels[0] = SOUND_MZAHN8D	//Hey CJ, it’s Katie, I want to see you, baby!
		mobile_audio_labels[1] = SOUND_MZAHN3B	//Hey, Katie, I was just thinking about you! I’ll drop by later and we can go out.

		cell_index_end = 1
	BREAK

	CASE SUZIE_DUMP	
		//Katie Zhan		
		$mobile_print_labels[0] = &MZAHN1A	//Hello, Carl Johnson, friend to all ladies!
		$mobile_print_labels[1] = &MZAHN1B	//So I’ve heard, you piece of shit! 
		$mobile_print_labels[2] = &MZAHN1C	//Katie? Katie! Hey, doll, I was just about to call you!
		$mobile_print_labels[3] = &MZAHN1D	//You’re always ‘just about to call’ me you cheap bastard!
		$mobile_print_labels[4] = &MZAHN1E	//Look, baby, I know I’ve been a dog, but-
		$mobile_print_labels[5] = &MZAHN1F	//Can it! I’ve given you chance after chance, but I’m not taking any more shit,
		$mobile_print_labels[6] = &MZAHN1G	//I deserve better than you!
		$mobile_print_labels[7] = &MZAHN1H	//Katie, sweet baby, you gotta give me one last chance!
		$mobile_print_labels[8] = &MZAHN1J	//Too late, Carl, you blew it!

		mobile_audio_labels[0] = SOUND_MZAHN1A	//Hello, Carl Johnson, friend to all ladies!
		mobile_audio_labels[1] = SOUND_MZAHN1B	//So I’ve heard, you piece of shit! 
		mobile_audio_labels[2] = SOUND_MZAHN1C	//Katie? Katie! Hey, doll, I was just about to call you!
		mobile_audio_labels[3] = SOUND_MZAHN1D	//You’re always ‘just about to call’ me you cheap bastard!
		mobile_audio_labels[4] = SOUND_MZAHN1E	//Look, baby, I know I’ve been a dog, but-
		mobile_audio_labels[5] = SOUND_MZAHN1F	//Can it! I’ve given you chance after chance, but I’m not taking any more shit,
		mobile_audio_labels[6] = SOUND_MZAHN1G	//I deserve better than you!
		mobile_audio_labels[7] = SOUND_MZAHN1H	//Katie, sweet baby, you gotta give me one last chance!
	 	mobile_audio_labels[8] = SOUND_MZAHN1J	//Too late, Carl, you blew it!

		cell_index_end = 8
	BREAK 

	CASE MILLIE_MOBILE
			
		$mobile_print_labels[0] = &MMILL3A	//Hi, Carl, you want to go out?
		$mobile_print_labels[1] = &MMILL3B	//Oh hi, Millie! I’ll come over your place and pick you up!

		mobile_audio_labels[0] = SOUND_MMILL3A	//Hi, Carl, you want to go out?
		mobile_audio_labels[1] = SOUND_MMILL3B	//Oh hi, Millie! I’ll come over your place and pick you up!

		cell_index_end = 1
	BREAK
	CASE MILLIE_MOBILE2
			
		$mobile_print_labels[0] = &MMILL4A	//Hey, Carl, let’s hit the town or something!
		$mobile_print_labels[1] = &MMILL4D	//Millie, I was just thinking how naughty you must have been – I’ll be over in five!

		mobile_audio_labels[0] = SOUND_MMILL4A	//Hey, Carl, let’s hit the town or something!
		mobile_audio_labels[1] = SOUND_MMILL4D	//Millie, I was just thinking how naughty you must have been – I’ll be over in five!

		cell_index_end = 1
	BREAK
	CASE MILLIE_MOBILE3
			
		$mobile_print_labels[0] = &MMILL5A	//CJ! You forgotten about me?
		$mobile_print_labels[1] = &MMILL5D	//Millie Perkins, the perfect girl! Hold tight, I’ll be right over!

		mobile_audio_labels[0] = SOUND_MMILL5A	//CJ! You forgotten about me?
		mobile_audio_labels[1] = SOUND_MMILL5D	//Millie Perkins, the perfect girl! Hold tight, I’ll be right over!

		cell_index_end = 1
	BREAK
	CASE MILLIE_MOBILE4
			
		$mobile_print_labels[0] = &MMILL6A	//Carl Johnson, have you been a good boy?
		$mobile_print_labels[1] = &MMILL6B	//Well hi, Millie! Don’t move yo’sweet ass, I’m coming right over!

		mobile_audio_labels[0] = SOUND_MMILL6A	//Carl Johnson, have you been a good boy?
		mobile_audio_labels[1] = SOUND_MMILL6B	//Well hi, Millie! Don’t move yo’sweet ass, I’m coming right over!

		cell_index_end = 1
	BREAK
	CASE MILLIE_MOBILE5
			
		$mobile_print_labels[0] = &MMILL7A	//CJ, I think I’ve been a VERY naughty girl!
		$mobile_print_labels[1] = &MMILL7D	//I was just thinking about you, Millie! Be over your place as quick as!

		mobile_audio_labels[0] = SOUND_MMILL7A	//CJ, I think I’ve been a VERY naughty girl!
		mobile_audio_labels[1] = SOUND_MMILL7D	//I was just thinking about you, Millie! Be over your place as quick as!

		cell_index_end = 1
	BREAK
	CASE MILLIE_MOBILE6
			
		$mobile_print_labels[0] = &MMILL8D	//Hey Carl, Let’s go out someplace!
		$mobile_print_labels[1] = &MMILL3B	//Oh hi, Millie! I’ll come over your place and pick you up!

		mobile_audio_labels[0] = SOUND_MMILL8D	//Hey Carl, Let’s go out someplace!
		mobile_audio_labels[1] = SOUND_MMILL3B	//Oh hi, Millie! I’ll come over your place and pick you up!

		cell_index_end = 1
	BREAK
	CASE MILLIE_MOBILE7
			
		$mobile_print_labels[0] = &MMILL9D	//Hey CJ, let’s do something!
		$mobile_print_labels[1] = &MMILL4D	//Millie, I was just thinking how naughty you must have been – I’ll be over in five!

		mobile_audio_labels[0] = SOUND_MMILL9D	//Hey CJ, let’s do something!
		mobile_audio_labels[1] = SOUND_MMILL4D	//Millie, I was just thinking how naughty you must have been – I’ll be over in five!

		cell_index_end = 1
	BREAK
	CASE MILLIE_MOBILE8
			
		$mobile_print_labels[0] = &MMIL10D	//Hey Carl. I knocked off an hour ago – let’s party!
		$mobile_print_labels[1] = &MMILL5D	//Millie Perkins, the perfect girl! Hold tight, I’ll be right over!

		mobile_audio_labels[0] = SOUND_MMIL10D	//Hey Carl. I knocked off an hour ago – let’s party!
		mobile_audio_labels[1] = SOUND_MMILL5D	//Millie Perkins, the perfect girl! Hold tight, I’ll be right over!

		cell_index_end = 1
	BREAK

	CASE MILLIE_DUMP
		//Millie Perkins	
		$mobile_print_labels[0] = &MMILL1A	//This better be good!
		$mobile_print_labels[1] = &MMILL1B	//‘Fraid not, Carl. I don’t want to see you any more. 
		$mobile_print_labels[2] = &MMILL1C	//What? Why? What I do?
		$mobile_print_labels[3] = &MMILL1D	//You treat me like shit. You dress like shit. 
		$mobile_print_labels[4] = &MMILL1E	//You wine and dine like shit.
		$mobile_print_labels[5] = &MMILL1F	//But... but... well... shit...
		$mobile_print_labels[6] = &MMILL1G	//It’s unanimous then, Carl Johnson is shit. See you around, Carl!
		$mobile_print_labels[7] = &MMILL1H	//Do NOT say, “like a doughnut!”
		$mobile_print_labels[8] = &MMILL1J	//Like a big round shit.
		$mobile_print_labels[9] = &MMILL1K	//Shit.


		mobile_audio_labels[0] = SOUND_MMILL1A	//This better be good!
		mobile_audio_labels[1] = SOUND_MMILL1B	//‘Fraid not, Carl. I don’t want to see you any more. 
		mobile_audio_labels[2] = SOUND_MMILL1C	//What? Why? What I do?
		mobile_audio_labels[3] = SOUND_MMILL1D	//You treat me like shit. You dress like shit. 
		mobile_audio_labels[4] = SOUND_MMILL1E	//You wine and dine like shit.
		mobile_audio_labels[5] = SOUND_MMILL1F	//But... but... well... shit...
		mobile_audio_labels[6] = SOUND_MMILL1G	//It’s unanimous then, Carl Johnson is shit. See you around, Carl!
		mobile_audio_labels[7] = SOUND_MMILL1H	//Do NOT say, “like a doughnut!”
		mobile_audio_labels[8] = SOUND_MMILL1J	//Like a big round shit.
		mobile_audio_labels[9] = SOUND_MMILL1K	//Shit.

		cell_index_end = 9
	BREAK 
	ENDSWITCH
ENDIF

RETURN



cell_phone_LA1:


SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested

SCRIPT_NAME MOB_LA1

cell_phone_LA1_inner:
	
	WAIT 90
	// *******************************************************
	IF flag_player_on_mission = 0
	AND flag_mobile_timer = 0
		GET_GAME_TIMER timer_mobile_start
		flag_mobile_timer = 1
	ENDIF

	IF Return_cities_passed > 0
		//IF terminate_LA1_calls = 1
			TERMINATE_THIS_SCRIPT	
		//ENDIF
	ENDIF

	GOSUB check_player_is_safe_for_mobile

	IF IS_PLAYER_PLAYING player1
		IF player_is_completely_safe_for_mobile = 1
			IF timer_mobile_diff > call_delay
	
				IF flag_intro_mission_counter > 0
					IF flag_sweet_mission_counter < 2
						IF funeral_mission_finished = 3
							// SWEET PHONE CALL. TRIGGERS NOTHING*********************************************** 					
							IF flag_mob_la1[5] = 0
								//PRINT_HELP ( ANSWER )
								call_number = SWEET_MOBILE_CALL2
								GOSUB mobile_rings
								IF flag_player_answered_phone = 1									 
									GOSUB mobile_chat_switch
									GOSUB load_and_play_mobile_calls
								ENDIF
								IF flag_player_answered_phone = 1	
									flag_mob_la1[5] = 1
								ENDIF
								GOSUB mobile_message_cleanup
								GOTO cell_phone_LA1_inner
							ENDIF
						ENDIF
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_sweet_mission_counter > 0
					// CRASH PHONE CALL. TRIGGERS NOTHING*********************************************** 					
					IF flag_mob_la1[7] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CRASH_MOBILE_CALL3
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1									 
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_la1[7] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_LA1_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_strap_mission_counter = 4
					GET_TIME_OF_DAY hours minutes
					IF hours >= 20
					OR hours < 6
						// OG LOC PHONE CALL. TRIGGERS HOUSE PARTY*********************************************** 					
						IF flag_mob_la1[0] = 0
							//PRINT_HELP ( ANSWER )
							call_number = OGLOC_MOBILE_CALL3
							GOSUB mobile_rings
							IF flag_player_answered_phone = 1	
								GOSUB mobile_chat_switch
								GOSUB load_and_play_mobile_calls
							ENDIF
							IF flag_player_answered_phone = 1	
								flag_mob_la1[0] = 1
							ENDIF
							GOSUB mobile_message_cleanup
							GOTO cell_phone_LA1_inner
						ENDIF
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_sweet_mission_counter > 2
					// SWEET CALL. TRIGGERS GYM*********************************************** 					
					IF flag_mob_la1[6] = 0
						//PRINT_HELP ( ANSWER )
						GET_FLOAT_STAT FAT Returnedfat
						IF Returnedfat < 450.0
							call_number = SWEET_MOBILE_CALL3
						ENDIF
						IF Returnedfat >= 450.0
							call_number = SWEET_MOBILE_CALL4
						ENDIF
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_la1[6] = 1
							DO_FADE 500 FADE_OUT
						    WHILE GET_FADING_STATUS
						        WAIT 0
						    ENDWHILE 
							
							IF IS_PLAYER_PLAYING player1
								SET_PLAYER_CONTROL player1 OFF
						    	GET_CHAR_COORDINATES scplayer player_X player_Y player_Z
								SET_CHAR_COORDINATES scplayer 2228.8914 -1737.1276 12.3906
							ENDIF
						    CLEAR_PRINTS
						    LOAD_SCENE 2228.5166 -1734.2740 15.7440
							REMOVE_BLIP gym_contact_blip
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2228.0002 -1722.8113 12.5543 RADAR_SPRITE_GYM gym_contact_blip

							SET_FIXED_CAMERA_POSITION 2228.0945 -1735.1787 15.6865 0.0 0.0 0.0 //GYM
							POINT_CAMERA_AT_POINT 2228.5166 -1734.2740 15.7440 JUMP_CUT

							WAIT 500
						    DO_FADE 1000 FADE_IN
							
							PRINT_HELP GYMHELP
							
							WAIT 5000

							PRINT_HELP DUMBELL
							FLASH_HUD_OBJECT HUD_FLASH_RADAR

							WAIT 5000

							FLASH_HUD_OBJECT -1

							DO_FADE 500 FADE_OUT
						    WHILE GET_FADING_STATUS
						        WAIT 0
						    ENDWHILE

							IF IS_PLAYER_PLAYING player1
								player_Z = player_Z - 0.5
								SET_CHAR_COORDINATES scplayer player_X player_Y player_Z
								LOAD_SCENE player_X player_Y player_Z
								RESTORE_CAMERA_JUMPCUT
								SET_PLAYER_CONTROL player1 ON
							ENDIF
							SWITCH_ENTRY_EXIT gym1 TRUE
							SWITCH_ENTRY_EXIT gym2 TRUE
							SWITCH_ENTRY_EXIT gym3 TRUE
							REMOVE_BLIP gym_contact_blip
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT 2228.0002 -1722.8113 12.5543 RADAR_SPRITE_GYM gym_contact_blip
							CHANGE_BLIP_DISPLAY gym_contact_blip BLIP_ONLY
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT -2269.4, -155.6, 35.3 RADAR_SPRITE_GYM gym_blips[0] //GYM2
							CHANGE_BLIP_DISPLAY gym_blips[0] BLIP_ONLY
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT 1968.6, 2292.1, 16.4 RADAR_SPRITE_GYM gym_blips[1] //GYM3
							CHANGE_BLIP_DISPLAY gym_blips[1] BLIP_ONLY

							WAIT 500
							DO_FADE 1000 FADE_IN

						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_LA1_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_strap_mission_counter > 1
					// CRASH TEAM CALL. TRIGGERS CRASH STRAND*********************************************** 					
					IF flag_mob_la1[1] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CRASH_MOBILE_CALL1 
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_la1[1] = 1
							START_NEW_SCRIPT crash_mission_loop
							REMOVE_BLIP crash_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT crashX crashY crashZ crash_blip_icon crash_contact_blip
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_LA1_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_sweet_mission_counter > 6
					// CESAR CALL. TRIGGERS CESAR STRAND*********************************************** 					
					IF flag_mob_la1[2] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CESAR_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_la1[2] = 1
							START_NEW_SCRIPT cesar_mission_loop
							REMOVE_BLIP cesar_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT cesarX cesarY cesarZ cesar_blip_icon cesar_contact_blip
							CHANGE_BLIP_DISPLAY cesar_contact_blip BLIP_ONLY
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_LA1_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_sweet_mission_counter > 6
					IF flag_crash_mission_counter > 0
						// CESAR CALL. TRIGGERS DOBERMAN MISSION*********************************************** 					
						IF flag_mob_la1[4] = 0
							//PRINT_HELP ( ANSWER )
							call_number = SWEET_MOBILE_CALL1
							GOSUB mobile_rings
							IF flag_player_answered_phone = 1	
								GOSUB mobile_chat_switch
								GOSUB load_and_play_mobile_calls
							ENDIF
							IF flag_player_answered_phone = 1	
								flag_mob_la1[4] = 1
								SWITCH_ENTRY_EXIT ammun1 FALSE
								REMOVE_BLIP sweet_contact_blip
					          	ADD_SPRITE_BLIP_FOR_CONTACT_POINT 1365.2507 -1280.1200 12.5469 sweet_blip_icon sweet_contact_blip
							ENDIF
							GOSUB mobile_message_cleanup
							GOTO cell_phone_LA1_inner
						ENDIF
					ENDIF
					// **********************************************************************************************************
				ENDIF

			ENDIF
		ENDIF
	ELSE
		flag_cell_nation = 0
	ENDIF
	
	
GOTO cell_phone_LA1_inner





cell_phone_cat:


SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested

SCRIPT_NAME MOB_CAT

cell_phone_cat_inner:
	
	WAIT 120
	// ********************************************************

	IF flag_player_on_mission = 0
	AND flag_mobile_timer = 0
		GET_GAME_TIMER timer_mobile_start
		flag_mobile_timer = 1
	ENDIF

	IF terminate_cat_calls = 1
		TERMINATE_THIS_SCRIPT	
	ENDIF
	
	GOSUB check_player_is_safe_for_mobile

	IF IS_PLAYER_PLAYING player1
		IF player_is_completely_safe_for_mobile = 1
			IF timer_mobile_diff > call_delay

				IF flag_bcrash_mission_counter > 0
					// CESAR PHONE CALL. TRIGGERS CAT MISSION 1*********************************************** 					
					IF flag_mob_cat[0] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CESAR_MOBILE_CALL4
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_cat[0] = 1
							START_NEW_SCRIPT cat_mission_loop
							REMOVE_BLIP cat_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT catX[5] catY[5] catZ[5] RADAR_SPRITE_MYSTERY cat_contact_blip //TRUCK STOP
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_cat_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_bcrash_mission_counter > 0
					// SWEET PRISION PHONE CALL. TRIGGERS FUCK ALL******************************************************************* 					
					IF flag_mob_cat[3] = 0
						//PRINT_HELP ( ANSWER )
						call_number = SWEET_MOBILE_CALL7
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_cat[3] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_cat_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_truth_mission_counter = 0
				AND cat_counter = 1
					// TRUTH PHONE CALL. TRIGGERS TRUTH MISSION 1*********************************************** 					
					IF flag_mob_cat[1] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TRUTH_MOBILE_CALL1 
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_cat[1] = 1
							START_NEW_SCRIPT truth_mission_loop
							REMOVE_BLIP truth_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT truth2X truth2Y truth2Z truth_blip_icon truth_contact_blip //HOTEL
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_cat_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF cat_counter = 3
				//AND catalina_generation_flag < 2
					// CATALINA PHONE CALLS. TRIGGERS THE FINAL CAT MISSION*********************************************** 					
					IF flag_mob_cat[5] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CATALINA_MOBILE_CUT2
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_cat[5] = 1
							SET_ZONE_GANG_STRENGTH ELCO1 GANG_NMEX 40
	                        SET_ZONE_GANG_STRENGTH ELCO2 GANG_NMEX 40
	                        SET_ZONE_GANG_STRENGTH ELCO1 GANG_SMEX 0
	                        SET_ZONE_GANG_STRENGTH ELCO2 GANG_SMEX 0
	                        SET_ZONE_GANG_STRENGTH ELCO1 GANG_GROVE 0
	                        SET_ZONE_GANG_STRENGTH ELCO2 GANG_GROVE 0

	                        SET_ZONE_GANG_STRENGTH LMEX1a GANG_NMEX 30
	                        SET_ZONE_GANG_STRENGTH LMEX1b GANG_NMEX 30
	                        SET_ZONE_GANG_STRENGTH LMEX1a GANG_SMEX 0
	                        SET_ZONE_GANG_STRENGTH LMEX1b GANG_SMEX 0
	                        SET_ZONE_GANG_STRENGTH LMEX1a GANG_GROVE 0
	                        SET_ZONE_GANG_STRENGTH LMEX1b GANG_GROVE 0
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_cat_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF cat_counter > 1
				AND flag_bcesar_mission_counter = 0
					// CESAR PHONE CALL. TRIGGERS BCESAR RACE1******************************************************************* 					
					IF flag_mob_cat[6] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CESAR_MOBILE_CALL9
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_cat[6] = 1
							START_NEW_SCRIPT bcesar_mission_loop
							REMOVE_BLIP bcesar_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT bcesarX bcesarY bcesarZ cesar_blip_icon bcesar_contact_blip //FARM
							CHANGE_BLIP_DISPLAY bcesar_contact_blip BLIP_ONLY
							PRINT BC4_1 8000 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_cat_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF cat_counter > 3
				AND flag_bcesar_mission_counter > 9
					// TRUTH PHONE CALL. TRIGGERS TRUTH2 MISSION***************************************************************** 					
					IF flag_mob_cat[7] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TRUTH_MOBILE_CALL2 
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_cat[7] = 1
							REMOVE_BLIP	truth_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT truthX truthY truthZ truth_blip_icon truth_contact_blip //TRUTHS FARM
							SET_MAX_WANTED_LEVEL 5
							terminate_cat_calls = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_cat_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF	
					
			ENDIF
		ENDIF
	ELSE
		flag_cell_nation = 0
	ENDIF
	
	
GOTO cell_phone_cat_inner
	


cell_phone_sanfran:

SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested

SCRIPT_NAME MOB_SF

cell_phone_sanfran_inner:
	
	WAIT 150
	// ********************************************************

	IF flag_player_on_mission = 0
	AND flag_mobile_timer = 0
		GET_GAME_TIMER timer_mobile_start
		flag_mobile_timer = 1
	ENDIF

	IF flag_mob_sanfran[8] = 1
		IF flag_mob_sanfran[4] = 1
			IF flag_mob_sanfran[3] = 1
				IF flag_mob_sanfran[1] = 1
					TERMINATE_THIS_SCRIPT
				ENDIF
			ENDIF
		ENDIF	
	ENDIF

	GOSUB check_player_is_safe_for_mobile

	IF IS_PLAYER_PLAYING player1
		IF player_is_completely_safe_for_mobile = 1
			IF timer_mobile_diff > call_delay

				IF flag_Synd_mission_counter > 2
					// WUZI PHONE CALL. TRIGGERS WUZI STRAND***************************************************************** 					
					IF flag_mob_sanfran[6] = 0
						//PRINT_HELP ( ANSWER )
						call_number = WUZI_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[6] = 1
							START_NEW_SCRIPT wuzi_mission_loop
							REMOVE_BLIP wuzi_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT wuziX wuziY wuziZ wuzi_blip_icon wuzi_contact_blip
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_garage_mission_counter > 0
					// ZERO PHONE CALL. TRIGGERS ZERO BUY PROPERTY***************************************************************** 					
					IF flag_mob_sanfran[0] = 0
						//PRINT_HELP ( ANSWER )
						call_number = ZERO_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[0] = 1
							START_NEW_SCRIPT zero_buy_loop
							REMOVE_BLIP	zero_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[1] propertyY[1] propertyZ[1] RADAR_SPRITE_ZERO zero_contact_blip
							CHANGE_BLIP_DISPLAY zero_contact_blip BLIP_ONLY
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_garage_mission_counter > 0
				AND zeros_property_bought = 1
					// ZERO PHONE CALL. TRIGGERS ZERO MISSION***************************************************************** 					
					IF flag_mob_sanfran[1] = 0
						//PRINT_HELP ( ANSWER )
						call_number = ZERO_MOBILE_CALL2
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[1] = 1
							START_NEW_SCRIPT zero_mission_loop
				            REMOVE_BLIP zero_contact_blip
				            ADD_SPRITE_BLIP_FOR_CONTACT_POINT zeroX zeroY zeroZ zero_blip_icon zero_contact_blip
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF
				
				IF flag_garage_mission_counter > 1
					// JETHRO PHONE CALL. TRIGGERS DRIVING SCHOOL***************************************************************** 					
					IF flag_mob_sanfran[2] = 0
						//PRINT_HELP ( ANSWER )
						call_number = JETHRO_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[2] = 1
							START_NEW_SCRIPT trace_mission_loop // TEST STUFF
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT testsX testsY testsZ RADAR_SPRITE_SCHOOL dschool_contact_blip
							//SET_BLIP_ENTRY_EXIT	dschool_contact_blip -2026.4767 -99.8392 10.0
							CHANGE_BLIP_DISPLAY dschool_contact_blip BLIP_ONLY

							START_NEW_SCRIPT boats_school_loop //TEST! FIND A SUITABLE LOCATION TO TRIGGER
							REMOVE_BLIP	boat_school_blip
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT boatsX boatsY boatsZ RADAR_SPRITE_SCHOOL boat_school_blip
							CHANGE_BLIP_DISPLAY boat_school_blip BLIP_ONLY

							START_NEW_SCRIPT bikes_school_loop //TEST! FIND A SUITABLE LOCATION TO TRIGGER
							REMOVE_BLIP	bike_school_blip
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT bikesX bikesY bikesZ RADAR_SPRITE_SCHOOL bike_school_blip
							CHANGE_BLIP_DISPLAY bike_school_blip BLIP_ONLY

						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF
				
				IF driving_test_passed = 1
				AND flag_desert_mission_counter > 3
					// JETHRO PHONE CALL. TRIGGERS RACE TOUR***************************************************************** 					
					IF flag_mob_sanfran[3] = 0
						//PRINT_HELP ( ANSWER )
						call_number = JETHRO_MOBILE_CALL2
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[3] = 1
							REMOVE_BLIP trace_contact_blip[0]
							REMOVE_BLIP trace_contact_blip[1]
							REMOVE_BLIP trace_contact_blip[2]
							REMOVE_BLIP trace_contact_blip[3]
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT traceX[0] traceY[0] traceZ[0] RADAR_SPRITE_FLAG trace_contact_blip[0]
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT traceX[1] traceY[1] traceZ[1] RADAR_SPRITE_FLAG trace_contact_blip[1]
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT traceX[2] traceY[2] traceZ[2] RADAR_SPRITE_FLAG trace_contact_blip[2]
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT traceX[3] traceY[3] traceZ[3] RADAR_SPRITE_FLAG trace_contact_blip[3]
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_mob_sanfran[9] = 1
				AND driving_test_passed = 1
					// JETHRO PHONE CALL. TRIGGERS STEAL PROPERTY BUY***************************************************************** 					
					IF flag_mob_sanfran[4] = 0
						//PRINT_HELP ( ANSWER )
						call_number = JETHRO_MOBILE_CALL3
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[4] = 1
							START_NEW_SCRIPT showroom_buy_loop
							REMOVE_BLIP	showroom_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT propertyX[0] propertyY[0] propertyZ[0] RADAR_SPRITE_CESAR showroom_contact_blip
							CHANGE_BLIP_DISPLAY showroom_contact_blip BLIP_ONLY
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF
			
				IF flag_Synd_mission_counter = 10
					// TORENO PHONE CALL. TRIGGERS TORENO STRAND***************************************************************** 					
					IF flag_mob_sanfran[9] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TORENO_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[9] = 1
							START_NEW_SCRIPT desert_mission_loop
							REMOVE_BLIP save_house_blip[16]
							REMOVE_PICKUP grove_save_pickup[16]
							CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[16] save_pickupY[16] save_pickupZ[16] grove_save_pickup[16] //TORENOS RANCH//remove
							number_of_save_icons = 17
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[16] save_pickupY[16] save_pickupZ[16] RADAR_SPRITE_SAVEHOUSE save_house_blip[16]
							CHANGE_BLIP_DISPLAY save_house_blip[16] BLIP_ONLY
							REMOVE_BLIP desert_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ RADAR_SPRITE_MYSTERY desert_contact_blip
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF
					
				IF flag_desert_mission_counter > 0
					// TORENO PHONE CALL. TRIGGERS DESERT2***************************************************************** 					
					IF flag_mob_sanfran[5] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TORENO_MOBILE_CALL2
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[5] = 1
							REMOVE_BLIP desert_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ RADAR_SPRITE_MYSTERY desert_contact_blip 
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_desert_mission_counter > 1
					// TORENO PHONE CALL. TRIGGERS DESERT3***************************************************************** 					
					IF flag_mob_sanfran[7] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TORENO_MOBILE_CALL3
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[7] = 1
							REMOVE_BLIP desert_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT desertX desertY desertZ desert_blip_icon desert_contact_blip 
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF pilot_test_passed > 0 
					// WUZI PHONE CALL. TRIGGERS CASINO STRAND***************************************************************** 					
					IF flag_mob_sanfran[8] = 0
						//PRINT_HELP ( ANSWER )
						call_number = WUZI_MOBILE_CALL3
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_sanfran[8] = 1
							TERMINATE_ALL_SCRIPTS_WITH_THIS_NAME MOB_VEG
							START_NEW_SCRIPT cell_phone_vegas
							START_NEW_SCRIPT casino_mission_loop
							REMOVE_PICKUP grove_save_pickup[17]
							CREATE_PICKUP PICKUPSAVE PICKUP_ONCE save_pickupX[17] save_pickupY[17] save_pickupZ[17] grove_save_pickup[17] //TRIAD CASINO//remove
							number_of_save_icons = 18
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_CONTACT_POINT save_pickupX[17] save_pickupY[17] save_pickupZ[17] RADAR_SPRITE_SAVEHOUSE save_house_blip[17]
							CHANGE_BLIP_DISPLAY save_house_blip[17] BLIP_ONLY
							REMOVE_BLIP casino_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT casinoX casinoY casinoZ casino_blip_icon casino_contact_blip
							SET_BLIP_ENTRY_EXIT	casino_contact_blip 2026.6028 1007.7353 20.0
							ACTIVATE_GARAGE vEcmod
							ADD_SHORT_RANGE_SPRITE_BLIP_FOR_COORD 2382.2, 1044.0, 9.8 RADAR_SPRITE_MOD_GARAGE mod_garage_blips[2]
							SET_INT_STAT PASSED_CASINO3 1
							START_NEW_SCRIPT little_casino_cut
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_sanfran_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF


			ENDIF
		ENDIF
	ELSE
		flag_cell_nation = 0
	ENDIF
	
	
GOTO cell_phone_sanfran_inner




cell_phone_vegas:

SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested

SCRIPT_NAME MOB_VEG

cell_phone_vegas_inner:
	
	WAIT 180
	// ********************************************************

	IF flag_player_on_mission = 0
	AND flag_mobile_timer = 0
		GET_GAME_TIMER timer_mobile_start
		flag_mobile_timer = 1
	ENDIF

	IF flag_mob_vegas[8] = 1
		IF flag_mob_vegas[4] = 1
			TERMINATE_THIS_SCRIPT	
		ENDIF
	ENDIF
		
	GOSUB check_player_is_safe_for_mobile

	IF IS_PLAYER_PLAYING player1
		IF player_is_completely_safe_for_mobile = 1
			IF timer_mobile_diff > call_delay

				IF flag_desert_mission_counter > 7 
					// WUZI PHONE CALL. TRIGGERS FINAL TRUTH MISSION***************************************************************** 					
					IF flag_mob_vegas[11] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TRUTH_MOBILE_CALL3
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[11] = 1
							REMOVE_BLIP desert2_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT desert2X desert2Y desert2Z desert2_blip_icon desert2_contact_blip
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_vcrash_mission_counter > 0
				AND flag_casino_mission_counter > 7
					// CRASH PHONE CALL. TRIGGERS VCRASH2 MISSION***************************************************************** 					
					IF flag_mob_vegas[0] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CRASH_MOBILE_CALL2
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[0] = 1
							REMOVE_BLIP vcrash_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT -378.7596 2235.8594 41.4288 crash_blip_icon vcrash_contact_blip
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF
				
				IF flag_casino_mission_counter > 3
					// KENT PHONE CALL. TRIGGERS HOSPITAL MISSION***************************************************************** 					
					IF flag_mob_vegas[2] = 0
						//PRINT_HELP ( ANSWER )
						call_number = KENT_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[2] = 1
							REMOVE_BLIP casino_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon casino_contact_blip
							SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614	20.0
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_casino_mission_counter > 6
					// ROSENBURG PHONE CALL. TRIGGERS FREEFALL***************************************************************** 					
					IF flag_mob_vegas[3] = 0
						//PRINT_HELP ( ANSWER )
						call_number = ROSE_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[3] = 1
							REMOVE_BLIP casino_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon casino_contact_blip
							SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614 20.0
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_casino_mission_counter > 7
					IF flag_vcrash_mission_counter > 1
						IF flag_doc_mission_counter > 0
							IF flag_desert_mission_counter > 8
								// ROSENBURG PHONE CALL. TRIGGERS SAIT MARKS***************************************************************** 					
								IF flag_mob_vegas[4] = 0
									//PRINT_HELP ( ANSWER )
									call_number = ROSE_MOBILE_CALL2
									GOSUB mobile_rings
									IF flag_player_answered_phone = 1	
										GOSUB mobile_chat_switch
										GOSUB load_and_play_mobile_calls
									ENDIF
									IF flag_player_answered_phone = 1	
										flag_mob_vegas[4] = 1
										REMOVE_BLIP casino_contact_blip
										ADD_SPRITE_BLIP_FOR_CONTACT_POINT heistX heistY heistZ heist_blip_icon casino_contact_blip
										SET_BLIP_ENTRY_EXIT	casino_contact_blip 2187.2856 1678.4614 20.0
									ENDIF
									GOSUB mobile_message_cleanup
									GOTO cell_phone_vegas_inner
								ENDIF
								// **********************************************************************************************************
							ENDIF
						ENDIF
					ENDIF
				ENDIF

				IF flag_heist_mission_counter > 1
					IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
					//AND iGFLikesPlayer[MILLIE] >= MILLIE_LIKES_PLAYER_REQUIRED_FOR_KEYCARD
						GET_INT_STAT GIRLFRIEND_MILLIE millies_like_stat
						IF millies_like_stat >= MILLIE_LIKES_PLAYER_REQUIRED_FOR_KEYCARD 
							// GOOD PHONE CALL FROM MILLIE. TRIGGERS KEYCARD FOR HEIST***************************************************************** 					
							IF flag_mob_vegas[1] = 0
								//PRINT_HELP ( ANSWER )
								call_number = MILLIE_KEY_MOBILE_CALL1 
								GOSUB mobile_rings
								IF flag_player_answered_phone = 1	
									GOSUB mobile_chat_switch
									GOSUB load_and_play_mobile_calls
								ENDIF
								IF flag_player_answered_phone = 1	
									CREATE_PICKUP KEYCARD PICKUP_ONCE 348.7857 306.1048 998.6557 millies_keycard_pickup
									START_NEW_SCRIPT millies_keycard_loop
									REMOVE_BLIP Thekeycard_contact_blip
									ADD_SPRITE_BLIP_FOR_CONTACT_POINT 2037.3492 2723.9714 9.8281 Theheist_blip_icon Thekeycard_contact_blip
									CHANGE_BLIP_DISPLAY Thekeycard_contact_blip BLIP_ONLY
									PRINT_NOW (GOODKEY) 6000 1 //You can now pick up the keycard 
									flag_mob_vegas[1] = 1
								ENDIF
								GOSUB mobile_message_cleanup
								GOTO cell_phone_vegas_inner
							ENDIF
							// **********************************************************************************************************
						ENDIF
					ENDIF
				ENDIF
				
				IF flag_heist_mission_counter > 1						   
					IF NOT IS_BIT_SET iDateReport DATE_IN_PROGRESS
						IF iGFLikesPlayer[MILLIE] = GF_HATES_PLAYER
						OR iGFLikesPlayer[MILLIE] =	GF_IS_DEAD
							// WUZI CALL. IF MILLIE DUMPED OR DEAD. TRIGGERS KEYCARD FOR HEIST***************************************************************** 					
							IF flag_mob_vegas[1] = 0
								//PRINT_HELP ( ANSWER )
								IF iGFLikesPlayer[MILLIE] =	GF_IS_DEAD
									call_number = WUZI_MOBILE_CALL8 //DEAD
								ENDIF
								IF iGFLikesPlayer[MILLIE] = GF_HATES_PLAYER
									call_number = WUZI_MOBILE_CALL7 //DUMPED
								ENDIF
								GOSUB mobile_rings
								IF flag_player_answered_phone = 1	
									GOSUB mobile_chat_switch
									GOSUB load_and_play_mobile_calls
								ENDIF
								IF flag_player_answered_phone = 1	
									CREATE_PICKUP KEYCARD PICKUP_ONCE 348.7857 306.1048 998.6557 millies_keycard_pickup
									START_NEW_SCRIPT millies_keycard_loop
									REMOVE_BLIP Thekeycard_contact_blip
									ADD_SPRITE_BLIP_FOR_CONTACT_POINT 2037.3492 2723.9714 9.8281 Theheist_blip_icon Thekeycard_contact_blip
									CHANGE_BLIP_DISPLAY Thekeycard_contact_blip BLIP_ONLY	
									PRINT_NOW (BADKEY) 6000 1 //You need to rob her house to get the keycard
									flag_mob_vegas[1] = 1
								ENDIF
								GOSUB mobile_message_cleanup
								GOTO cell_phone_vegas_inner
							ENDIF
							// **********************************************************************************************************
						ENDIF
					ENDIF
				ENDIF

				IF flag_heist_mission_counter > 4
				AND keycard_aquired_from_millie = 0
				AND flag_mob_vegas[6] = 0
					// WUZI PHONE CALL. REMINDER FOR KEYCARD***************************************************************** 					
					IF flag_mob_vegas[5] = 0
						//PRINT_HELP ( ANSWER )
						call_number = WUZI_MOBILE_CALL4
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[5] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_casino_mission_counter > 8
				AND NOT flag_heist_mission_counter = 6
					// SALVATORY PHONE CALL. DONE SAINT MARKS MISSION************************************************************ 					
					IF flag_mob_vegas[7] = 0
						//PRINT_HELP ( ANSWER )
						call_number = SALV_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[7] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_casino_mission_counter > 8
				AND flag_mob_vegas[6] = 0
					// WUZI PHONE CALL. REMINDER FOR HEIST AFTER SAINT MARKS***************************************************** 					
					IF flag_mob_vegas[12] = 0
						//PRINT_HELP ( ANSWER )
						call_number = WUZI_MOBILE_CALL6
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[12] = 1
							IF keycard_aquired_from_millie = 1
								REMOVE_BLIP Theheist_contact_blip
								ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
 					      		SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
								flag_mob_vegas[6] = 1
							ENDIF
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_casino_mission_counter > 8
				AND keycard_aquired_from_millie = 1
					// WUZI PHONE CALL. GOT THE KEYCARD************************************************************************** 					
					IF flag_mob_vegas[6] = 0
						//PRINT_HELP ( ANSWER )
						call_number = WUZI_MOBILE_CALL5
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[6] = 1
							REMOVE_BLIP Theheist_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT TheheistX TheheistY TheheistZ Theheist_blip_icon Theheist_contact_blip
 					      	SET_BLIP_ENTRY_EXIT Theheist_contact_blip 2024.3904 1008.6202 20.0
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_heist_mission_counter > 5
					// SALVATORY PHONE CALL. DONE THE HEIST MISSION************************************************************** 					
					IF flag_mob_vegas[8] = 0
						//PRINT_HELP ( ANSWER )
						call_number = SALV_MOBILE_CALL2
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_vegas[8] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_vegas_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF


			ENDIF
		ENDIF
	ELSE
		flag_cell_nation = 0
	ENDIF
	
	
GOTO cell_phone_vegas_inner



cell_phone_LA2:

SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested

SCRIPT_NAME MOB_LA2

cell_phone_LA2_inner:
	
	WAIT 250
	// ********************************************************

	IF flag_player_on_mission = 0
	AND flag_mobile_timer = 0
		GET_GAME_TIMER timer_mobile_start
		flag_mobile_timer = 1
	ENDIF

	IF flag_mob_LA2[2] = 1
		TERMINATE_THIS_SCRIPT	
	ENDIF
		
	GOSUB check_player_is_safe_for_mobile

	IF IS_PLAYER_PLAYING player1
		IF player_is_completely_safe_for_mobile = 1
			IF timer_mobile_diff > call_delay

				IF flag_mansion_mission_counter > 0
					// ROSENBURG PHONE CALL. OPENS THE HARRIER MISSION***************************************************************** 					
					IF flag_mob_LA2[0] = 0
						//PRINT_HELP ( ANSWER )
						call_number = ROSE_MOBILE_CALL3
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_LA2[0] = 1
							REMOVE_BLIP mansion_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT mansionX mansionY mansionZ mansion_blip_icon mansion_contact_blip
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_LA2_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_casino_mission_counter > 2 //SWEET IN PRISON CALL
				AND flag_mansion_mission_counter < 2
					IF flag_mob_LA2[1] = 0
						//PRINT_HELP ( ANSWER )
						call_number = SWEET_MOBILE_CALL8
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_LA2[1] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_LA2_inner
					ENDIF
				ENDIF

				IF flag_riot_mission_counter > 1 //TRIGGERS GANG WARS
					IF flag_mob_LA2[3] = 0
						//PRINT_HELP ( ANSWER )
						call_number = SWEET_MOBILE_CALL5
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1
							REMOVE_BLIP sweet_contact_blip
							ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip	
							flag_mob_LA2[3] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GET_TERRITORY_UNDER_CONTROL_PERCENTAGE player_territory_owned
						IF player_territory_owned <= 34
							PRINT_NOW MORTURF 12000 1 //Take over gang territories		
						ENDIF
						
						GOTO cell_phone_LA2_inner
					ENDIF
				ENDIF

				IF flag_riot_mission_counter > 1 //TRIGGERS FINALE
					GET_TERRITORY_UNDER_CONTROL_PERCENTAGE player_territory_owned
					IF player_territory_owned > 34
						IF flag_mob_LA2[2] = 0
							//PRINT_HELP ( ANSWER )
							call_number = SWEET_MOBILE_CALL6
							GOSUB mobile_rings
							IF flag_player_answered_phone = 1	
								GOSUB mobile_chat_switch
								GOSUB load_and_play_mobile_calls
							ENDIF
							IF flag_player_answered_phone = 1	
								REMOVE_BLIP sweet_contact_blip
								ADD_SPRITE_BLIP_FOR_CONTACT_POINT sweetX sweetY sweetZ sweet_blip_icon sweet_contact_blip
								flag_mob_LA2[2] = 1
							ENDIF
							GOSUB mobile_message_cleanup
							GOTO cell_phone_LA2_inner
						ENDIF
					ENDIF
				ENDIF

			ENDIF
		ENDIF
	ELSE
		flag_cell_nation = 0
	ENDIF
	
	
GOTO cell_phone_LA2_inner



cell_phone_random:

SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested

SCRIPT_NAME MOB_RAN

cell_phone_random_inner:

	WAIT 1000
	
	IF flag_player_on_mission = 0
	AND flag_mobile_timer = 0
		GET_GAME_TIMER timer_mobile_start
		flag_mobile_timer = 1
	ENDIF
		
	GOSUB check_player_is_safe_for_mobile

	IF IS_PLAYER_PLAYING player1
		IF player_is_completely_safe_for_mobile = 1
			
			IF timer_mobile_diff > 60000

				IF failed_cesar_race = 1
				AND flag_cesar_mission_counter = 0
					// KENDAL CALL. TRIGGERS FUCK ALL*********************************************** 					
					IF flag_mob_random[5] = 0
						//PRINT_HELP ( ANSWER )
						call_number = KENDAL_MOBILE_CALL1
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[5] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF
				
				IF trigger_phonecall_failed = 1
				AND flag_smoke_mission_counter = 1
					GET_FLOAT_STAT FAT Returnedfat
					IF Returnedfat >= 500.0
						// SMOKE CALL. TRIGGERS FUCK ALL*********************************************** 					
						IF flag_mob_random[6] = 0
							//PRINT_HELP ( ANSWER )
							call_number = SMOKE_MOBILE_CALL1
							GOSUB mobile_rings
							IF flag_player_answered_phone = 1	
								GOSUB mobile_chat_switch
								GOSUB load_and_play_mobile_calls
							ENDIF
							IF flag_player_answered_phone = 1	
								flag_mob_random[6] = 1
							ENDIF
							GOSUB mobile_message_cleanup
							GOTO cell_phone_random_inner
						ENDIF
						// **********************************************************************************************************
					ENDIF
				ENDIF

				IF flag_wuzi_mission_counter > 1
					// CATALINA RANDOM PHONE CALLS. TRIGGERS FUCK ALL******************************************************************* 					
					IF flag_mob_random[0] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CATALINA_MOBILE_CUT3
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[0] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_desert_mission_counter > 5
					// CATALINA RANDOM PHONE CALLS. TRIGGERS FUCK ALL*******************************************************************
					IF flag_mob_random[1] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CATALINA_MOBILE_CUT4
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[1] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_casino_mission_counter > 5
					// CATALINA RANDOM PHONE CALLS. TRIGGERS FUCK ALL******************************************************************* 					
					IF flag_mob_random[2] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CATALINA_MOBILE_CUT5
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[2] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF
				
				IF flag_mansion_mission_counter	> 2
					// CATALINA RANDOM PHONE CALLS. TRIGGERS FUCK ALL******************************************************************* 					
					IF flag_mob_random[3] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CATALINA_MOBILE_CUT6
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[3] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF flag_riot_mission_counter > 4
					// CATALINA RANDOM PHONE CALLS. TRIGGERS FUCK ALL******************************************************************* 					
					IF flag_mob_random[4] = 0
						//PRINT_HELP ( ANSWER )
						call_number = CATALINA_MOBILE_CUT7
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[4] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
					// **********************************************************************************************************
				ENDIF

				IF d5_watched_first_cutscene = 1 //TORENO REMINDER
				AND pilot_test_passed = 0
					IF flag_mob_random[7] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TORENO_MOBILE_CALL4
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[7] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
				ENDIF

				IF d5_watched_first_cutscene = 1 //TORENO REMINDER
				AND pilot_test_passed = 0
				AND flag_mob_random[7] = 1
					IF flag_mob_random[8] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TORENO_MOBILE_CALL5
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[8] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
				ENDIF
					
				IF d5_watched_first_cutscene = 1 //TORENO REMINDER
				AND pilot_test_passed = 0
				AND flag_mob_random[8] = 1
					IF flag_mob_random[9] = 0
						//PRINT_HELP ( ANSWER )
						call_number = TORENO_MOBILE_CALL6
						GOSUB mobile_rings
						IF flag_player_answered_phone = 1	
							GOSUB mobile_chat_switch
							GOSUB load_and_play_mobile_calls
						ENDIF
						IF flag_player_answered_phone = 1	
							flag_mob_random[9] = 1
						ENDIF
						GOSUB mobile_message_cleanup
						GOTO cell_phone_random_inner
					ENDIF
				ENDIF

				GET_NUMBER_OF_INSTANCES_OF_STREAMED_SCRIPT debt.sc number_of_instances_of_streamed_script

				IF im_players_city = LEVEL_LASVEGAS
					IF NOT IS_SCORE_GREATER player1 -499
						IF number_of_instances_of_streamed_script = 0
							// LOAD SHARK REMINDER***************************************************************** 					
							IF flag_mob_random[10] = 0
								//PRINT_HELP ( ANSWER )
								IF loan_shark_reminder = 7
									call_number = LOANSHARK_CALL8
									loan_shark_reminder = 0
								ENDIF
								IF loan_shark_reminder = 6
									call_number = LOANSHARK_CALL7 
									loan_shark_reminder = 7
								ENDIF
								IF loan_shark_reminder = 5
									call_number = LOANSHARK_CALL6 
									loan_shark_reminder = 6
								ENDIF
								IF loan_shark_reminder = 4
									call_number = LOANSHARK_CALL5 
									loan_shark_reminder = 5
								ENDIF
								IF loan_shark_reminder = 3
									call_number = LOANSHARK_CALL4 
									loan_shark_reminder = 4
								ENDIF
								IF loan_shark_reminder = 2
									call_number = LOANSHARK_CALL3 
									loan_shark_reminder = 3
								ENDIF
								IF loan_shark_reminder = 1
									call_number = LOANSHARK_CALL2 
									loan_shark_reminder = 2
								ENDIF
								IF loan_shark_reminder = 0
									call_number = LOANSHARK_CALL1 
									loan_shark_reminder = 1
								ENDIF
								GOSUB mobile_rings
								IF flag_player_answered_phone = 1	
									GOSUB mobile_chat_switch
									GOSUB load_and_play_mobile_calls
								ENDIF
								IF flag_player_answered_phone = 1	
									flag_mob_random[10] = 1
									flag_mob_random[11] = 0
									terminate_cat_calls = 1
								ENDIF
								GOSUB mobile_message_cleanup
								GOTO cell_phone_random_inner
							ENDIF
							// **********************************************************************************************************
							
							IF flag_mob_random[10] = 1
								IF flag_mob_random[11] = 0
									//PRINT_HELP ( ANSWER )
									IF loan_shark_hitmen = 7
										call_number = LOANSHARK_CALL16 
										loan_shark_hitmen = 0
									ENDIF
									IF loan_shark_hitmen = 6
										call_number = LOANSHARK_CALL15 
										loan_shark_hitmen = 7
									ENDIF
									IF loan_shark_hitmen = 5
										call_number = LOANSHARK_CALL14 
										loan_shark_hitmen = 6
									ENDIF
									IF loan_shark_hitmen = 4
										call_number = LOANSHARK_CALL13 
										loan_shark_hitmen = 5
									ENDIF
									IF loan_shark_hitmen = 3
										call_number = LOANSHARK_CALL12 
										loan_shark_hitmen = 4
									ENDIF
									IF loan_shark_hitmen = 2
										call_number = LOANSHARK_CALL11 
										loan_shark_hitmen = 3
									ENDIF
									IF loan_shark_hitmen = 1
										call_number = LOANSHARK_CALL10 
										loan_shark_hitmen = 2
									ENDIF
									IF loan_shark_hitmen = 0
										call_number = LOANSHARK_CALL9
										loan_shark_hitmen = 1
									ENDIF
									GOSUB mobile_rings
									IF flag_player_answered_phone = 1	
										GOSUB mobile_chat_switch
										GOSUB load_and_play_mobile_calls
									ENDIF
									IF flag_player_answered_phone = 1	
										flag_mob_random[11] = 1
										terminate_cat_calls = 1
									ENDIF
									GOSUB mobile_message_cleanup
									GOTO cell_phone_random_inner
								ENDIF
							ENDIF
							
							IF flag_mob_random[11] = 1
								STREAM_SCRIPT debt.sc
								IF HAS_STREAMED_SCRIPT_LOADED debt.sc
									START_NEW_STREAMED_SCRIPT debt.sc
								ENDIF
							ENDIF

						ENDIF
					ENDIF
				ENDIF
				
			ENDIF
		ENDIF
   	ELSE
		flag_cell_nation = 0
	ENDIF	
	
GOTO cell_phone_random_inner


// ********************** GIRLFRIENDS ONE SHOT CALL
cell_phone_GF:
	SCRIPT_NAME MOB_GF

LVAR_INT iGFCaller iCallType // parameters: iGFCaller = the girl, iCallType = CALL_DATE or CALL_DUMP
LVAR_INT iCallRandomVariant

SET_DEATHARREST_STATE OFF //stops script being terminated if Player dies/arrested
SET_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
CLEAR_BIT iAgentFlags MOBILE_CALL_ANSWERED

GENERATE_RANDOM_INT_IN_RANGE 0 8 iCallRandomVariant // generate a random variation of the mobile call (0-7)

cell_phone_GF_inner:
	
	WAIT 150
   	
	GOSUB check_player_is_safe_for_mobile

	IF IS_PLAYER_PLAYING player1
		IF player_is_completely_safe_for_mobile = 1
			IF timer_mobile_diff > call_delay
				// COOCHIE PHONE CALL. ************************************************************************************ 					
				IF iGFCaller = COOCHIE
					//PRINT_HELP ( ANSWER )
					IF iCallType = CALL_DATE
						call_number = COOCHIE_MOBILE
						call_number += iCallRandomVariant
						IF call_number > COOCHIE_MOBILE8
							call_number = COOCHIE_MOBILE8
						ENDIF	 
					ELSE
						call_number = COOCHIE_DUMP
					ENDIF						
					GOSUB mobile_rings
					IF flag_player_answered_phone = 1	
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED
						SET_BIT iAgentFlags iGFCaller
						GOSUB mobile_chat_switch
						GOSUB load_and_play_mobile_calls		
					ENDIF
					CLEAR_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
					GOSUB mobile_message_cleanup												
					TERMINATE_THIS_SCRIPT
				ENDIF
				// **********************************************************************************************************

				// MICHELLE PHONE CALL. ************************************************************************************ 					
				IF iGFCaller = MICHELLE
					//PRINT_HELP ( ANSWER )
					IF iCallType = CALL_DATE
						call_number = MICHELLE_MOBILE
						call_number += iCallRandomVariant
						IF call_number > MICHELLE_MOBILE8
							call_number = MICHELLE_MOBILE8
						ENDIF	 
					ELSE
						call_number = MICHELLE_DUMP
					ENDIF						
					GOSUB mobile_rings
					IF flag_player_answered_phone = 1	
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED
						GOSUB mobile_chat_switch
						GOSUB load_and_play_mobile_calls													
					ENDIF
					CLEAR_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
					GOSUB mobile_message_cleanup												
					TERMINATE_THIS_SCRIPT
				ENDIF
				// **********************************************************************************************************

				// KYLIE PHONE CALL. ************************************************************************************ 					
				IF iGFCaller = KYLIE
					//PRINT_HELP ( ANSWER )
					IF iCallType = CALL_DATE
						call_number = KYLIE_MOBILE
						call_number += iCallRandomVariant
						IF call_number > KYLIE_MOBILE8
							call_number = KYLIE_MOBILE8
						ENDIF	 
					ELSE
						call_number = KYLIE_DUMP
					ENDIF						
					GOSUB mobile_rings
					IF flag_player_answered_phone = 1	
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED
						GOSUB mobile_chat_switch
						GOSUB load_and_play_mobile_calls												
					ENDIF
					CLEAR_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING
					GOSUB mobile_message_cleanup				
					TERMINATE_THIS_SCRIPT
				ENDIF
				// **********************************************************************************************************

				// BARBARA PHONE CALL. ************************************************************************************ 					
				IF iGFCaller = BARBARA
					//PRINT_HELP ( ANSWER )
					IF iCallType = CALL_DATE
						call_number = BARBARA_MOBILE
						call_number += iCallRandomVariant
						IF call_number > BARBARA_MOBILE8
							call_number = BARBARA_MOBILE8
						ENDIF	 
					ELSE
						call_number = BARBARA_DUMP
					ENDIF						
					GOSUB mobile_rings
					IF flag_player_answered_phone = 1	
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED
						GOSUB mobile_chat_switch
						GOSUB load_and_play_mobile_calls
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED								
					ENDIF
					CLEAR_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING							
					GOSUB mobile_message_cleanup
					TERMINATE_THIS_SCRIPT
				ENDIF
				// **********************************************************************************************************

				// SUZIE PHONE CALL. ************************************************************************************ 					
				IF iGFCaller = SUZIE
					//PRINT_HELP ( ANSWER )
					IF iCallType = CALL_DATE
						call_number = SUZIE_MOBILE
						call_number += iCallRandomVariant
						IF call_number > SUZIE_MOBILE8
							call_number = SUZIE_MOBILE8
						ENDIF	 
					ELSE
						call_number = SUZIE_DUMP
					ENDIF						
					GOSUB mobile_rings
					IF flag_player_answered_phone = 1
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED	
						GOSUB mobile_chat_switch
						GOSUB load_and_play_mobile_calls
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED								
					ENDIF
					CLEAR_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING							
					GOSUB mobile_message_cleanup
					TERMINATE_THIS_SCRIPT
				ENDIF
				// **********************************************************************************************************
				// MILLIE PHONE CALL. ************************************************************************************ 					
				IF iGFCaller = MILLIE
					//PRINT_HELP ( ANSWER )
					IF iCallType = CALL_DATE
						call_number = MILLIE_MOBILE
						call_number += iCallRandomVariant
						IF call_number > MILLIE_MOBILE8
							call_number = MILLIE_MOBILE8
						ENDIF	 
					ELSE
						call_number = MILLIE_DUMP
					ENDIF						
					GOSUB mobile_rings
					IF flag_player_answered_phone = 1
						SET_BIT iAgentFlags MOBILE_CALL_ANSWERED	
						GOSUB mobile_chat_switch
						GOSUB load_and_play_mobile_calls			
					ENDIF
					CLEAR_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING							
					GOSUB mobile_message_cleanup
					TERMINATE_THIS_SCRIPT
				ENDIF
				// **********************************************************************************************************
			ENDIF
		ELSE
			CLEAR_BIT iAgentFlags MOBILE_CALL_SCRIPT_RUNNING							
			GOSUB mobile_message_cleanup
			TERMINATE_THIS_SCRIPT
		ENDIF
	ELSE
		flag_cell_nation = 0
	ENDIF

	
GOTO cell_phone_GF_inner
// END OF GIRLFRIENDS ONE SHOT CALL *******************************************************


mobile_rings:
	ring_a_ding_ding = 0

	flag_player_on_mission = 1
	flag_cell_nation = 1
	audio_slot_mobile = 1
	LOAD_MISSION_AUDIO 3 SOUND_MOBRING
	players_skipping_the_call = 0
	//--- Print the generic message unless its a girlfriend call (for a frame, to get the sound fx) 
	SWITCH call_number
		CASE COOCHIE_MOBILE
			PRINT_HELP ANSWER0
		BREAK
		CASE MICHELLE_MOBILE 
			PRINT_HELP ANSWER1
		BREAK
		CASE KYLIE_MOBILE	 
			PRINT_HELP ANSWER2
		BREAK
		CASE BARBARA_MOBILE  
			PRINT_HELP ANSWER3
		BREAK
		CASE SUZIE_MOBILE	 
			PRINT_HELP ANSWER4
		BREAK
		CASE MILLIE_MOBILE	 
			PRINT_HELP ANSWER5
		BREAK
		CASE COOCHIE_DUMP	 
			PRINT_HELP ANSWER0
		BREAK
		CASE MICHELLE_DUMP	 
			PRINT_HELP ANSWER1
		BREAK
		CASE KYLIE_DUMP	  
			PRINT_HELP ANSWER2
		BREAK
		CASE BARBARA_DUMP 
			PRINT_HELP ANSWER3
		BREAK
		CASE SUZIE_DUMP	 
			PRINT_HELP ANSWER4
		BREAK
		CASE MILLIE_DUMP 
			PRINT_HELP ANSWER5  
		BREAK
		DEFAULT
			PRINT_HELP ANSWER
		BREAK
	ENDSWITCH

	WHILE ring_a_ding_ding < 7
	AND flag_player_answered_phone < 2

		WAIT 0
		//--- Print (forever this time) the generic message unless its a girlfriend call
		SWITCH call_number
			CASE COOCHIE_MOBILE
			    PRINT_HELP_FOREVER ANSWER0
			BREAK
			CASE MICHELLE_MOBILE 
			    PRINT_HELP_FOREVER ANSWER1
			BREAK
			CASE KYLIE_MOBILE	 
			    PRINT_HELP_FOREVER ANSWER2
			BREAK
			CASE BARBARA_MOBILE  
			    PRINT_HELP_FOREVER ANSWER3
			BREAK
			CASE SUZIE_MOBILE	 
			    PRINT_HELP_FOREVER ANSWER4
			BREAK
			CASE MILLIE_MOBILE	 
			    PRINT_HELP_FOREVER ANSWER5
			BREAK
			CASE COOCHIE_DUMP	 
			    PRINT_HELP_FOREVER ANSWER0
			BREAK
			CASE MICHELLE_DUMP	 
			    PRINT_HELP_FOREVER ANSWER1
			BREAK
			CASE KYLIE_DUMP	  
			    PRINT_HELP_FOREVER ANSWER2
			BREAK
			CASE BARBARA_DUMP 
			    PRINT_HELP_FOREVER ANSWER3
			BREAK
			CASE SUZIE_DUMP	 
				PRINT_HELP_FOREVER ANSWER4
			BREAK
			CASE MILLIE_DUMP 
				PRINT_HELP_FOREVER ANSWER5
			BREAK
			DEFAULT
				PRINT_HELP_FOREVER ANSWER
			BREAK
		ENDSWITCH

		audio_slot_mobile = 3
		GOSUB loading_and_playing_audio
		++ ring_a_ding_ding

		IF flag_player_answered_phone = 1
		AND ring_a_ding_ding > 0
			GOTO imploding_head
		ENDIF
		GOSUB has_audio_finished


		IF flag_player_answered_phone = 1
		AND ring_a_ding_ding > 0
			GOTO imploding_head
		ENDIF

		mobile_pause = 0
		WHILE mobile_pause < 40
			WAIT 0
			GOSUB death_checker
			IF flag_player_answered_phone = 1
				GOTO imploding_head
			ENDIF
			++ mobile_pause
		ENDWHILE
	ENDWHILE
	GET_GAME_TIMER timer_mobile_start
	RETURN

	imploding_head:
	players_skipping_the_call = 1
	GET_GAME_TIMER timer_mobile_start
	
	WHILE IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
		WAIT 0
		IF NOT IS_PLAYER_PLAYING player1
			flag_player_answered_phone = 2
			RETURN
		ENDIF
	ENDWHILE
	
	CLEAR_HELP
	CLEAR_MISSION_AUDIO 1
	IF IS_PLAYER_PLAYING player1
		SET_EVERYONE_IGNORE_PLAYER player1 ON
		SHUT_CHAR_UP scplayer TRUE
		REQUEST_MODEL cellphone
		WHILE NOT HAS_MODEL_LOADED cellphone
			WAIT 0
        ENDWHILE

		GOSUB death_checker

		IF flag_player_answered_phone = 2
            MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
            RETURN
		ENDIF

		TASK_USE_MOBILE_PHONE scplayer TRUE
		timera = 0
		WHILE timera < 2000
			WAIT 0
			IF NOT IS_PLAYER_PLAYING player1
				flag_player_answered_phone = 2
				RETURN
			ENDIF
		ENDWHILE
	ENDIF
RETURN


loading_and_playing_audio:
	WHILE NOT HAS_MISSION_AUDIO_LOADED audio_slot_mobile
		WAIT 0
		IF NOT IS_PLAYER_PLAYING player1
			flag_player_answered_phone = 2
			RETURN
		ELSE
			IF IS_CHAR_IN_WATER scplayer
			OR IS_CHAR_SHOOTING scplayer
			OR NOT IS_CHAR_ON_FOOT scplayer
			OR flag_player_on_mission = 0
			OR NOT main_visible_area = 0
			OR NOT player_fall_state = 0
				flag_player_answered_phone = 2
				CLEAR_HELP
				RETURN
			ENDIF

			
			IF IS_GANG_WAR_FIGHTING_GOING_ON
			OR IS_PLAYER_USING_JETPACK Player1
			OR IS_MINIGAME_IN_PROGRESS
				flag_player_answered_phone = 2
				CLEAR_HELP
				RETURN
			ENDIF
			
			IF flag_player_answered_phone = 0
			AND ring_a_ding_ding > 0
				IF NOT IS_CHAR_SHOOTING scplayer
					IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
					AND CAN_PLAYER_START_MISSION player1
						flag_player_answered_phone = 1
						RETURN
					ENDIF
				ENDIF
			ENDIF
			
			IF players_skipping_the_call = 1
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
					players_skipping_the_call = 2
					RETURN	
				ENDIF
			ENDIF
			IF flag_player_on_mission = 0
				flag_player_answered_phone = 2
				RETURN
			ENDIF
		ENDIF
	ENDWHILE
	IF NOT IS_CHAR_IN_WATER scplayer
		PLAY_MISSION_AUDIO audio_slot_mobile
	ENDIF

RETURN


has_audio_finished:
	WHILE NOT HAS_MISSION_AUDIO_FINISHED audio_slot_mobile
		WAIT 0
		IF NOT IS_PLAYER_PLAYING player1
			flag_player_answered_phone = 2
			RETURN
		ELSE
			IF IS_CHAR_IN_WATER scplayer
			OR IS_CHAR_SHOOTING scplayer
			OR NOT IS_CHAR_ON_FOOT scplayer
			OR flag_player_on_mission = 0
			OR NOT main_visible_area = 0
			OR NOT player_fall_state = 0
				flag_player_answered_phone = 2
				CLEAR_HELP
				RETURN
			ENDIF
			
			IF IS_GANG_WAR_FIGHTING_GOING_ON
			OR IS_PLAYER_USING_JETPACK Player1
			OR IS_MINIGAME_IN_PROGRESS
				flag_player_answered_phone = 2
				CLEAR_HELP
				RETURN
			ENDIF

			IF flag_player_answered_phone = 0
			AND ring_a_ding_ding > 0
				IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
				AND CAN_PLAYER_START_MISSION player1
					flag_player_answered_phone = 1
					RETURN
				ENDIF
			ENDIF
			
			IF players_skipping_the_call = 1
				IF IS_BUTTON_PRESSED PAD1 TRIANGLE
					players_skipping_the_call = 2
					RETURN	
				ENDIF
			ENDIF
			IF flag_player_on_mission = 0
				flag_player_answered_phone = 2
				RETURN
			ENDIF
		ENDIF
	ENDWHILE
RETURN


death_checker:
	IF NOT IS_PLAYER_PLAYING player1
		flag_player_answered_phone = 2
		RETURN
	ELSE
		IF IS_CHAR_IN_WATER scplayer
		OR IS_CHAR_SHOOTING scplayer
		OR NOT IS_CHAR_ON_FOOT scplayer
		OR flag_player_on_mission = 0
		OR NOT main_visible_area = 0
		OR NOT player_fall_state = 0
			flag_player_answered_phone = 2
			CLEAR_HELP
			RETURN
		ENDIF
		
		IF IS_GANG_WAR_FIGHTING_GOING_ON
		OR IS_PLAYER_USING_JETPACK Player1
		OR IS_MINIGAME_IN_PROGRESS
			flag_player_answered_phone = 2
			CLEAR_HELP
			RETURN
		ENDIF

		IF flag_player_answered_phone = 0
		AND ring_a_ding_ding > 0
			IF NOT IS_CHAR_SHOOTING scplayer
				IF IS_BUTTON_PRESSED PAD1 LEFTSHOULDER1
				AND CAN_PLAYER_START_MISSION player1
					flag_player_answered_phone = 1
					RETURN
				ENDIF
			ENDIF
		ENDIF
		
		IF players_skipping_the_call = 1
			IF IS_BUTTON_PRESSED PAD1 TRIANGLE
				players_skipping_the_call = 2
				RETURN	
			ENDIF
		ENDIF
		IF flag_player_on_mission = 0
			flag_player_answered_phone = 2
			RETURN
		ENDIF
	ENDIF
RETURN

mobile_message_cleanup:
	flag_cell_nation = 0
	CLEAR_MISSION_AUDIO 1
	CLEAR_MISSION_AUDIO 2
	CLEAR_MISSION_AUDIO 3
	flag_player_answered_phone = 0
	flag_mobile_timer = 0
	IF IS_PLAYER_PLAYING player1
		//GET_SCRIPT_TASK_STATUS scplayer TASK_USE_MOBILE_PHONE mobile_ReturnStatus
		//IF NOT mobile_ReturnStatus = FINISHED_TASK
			TASK_USE_MOBILE_PHONE scplayer FALSE
		//ENDIF
		SET_PLAYER_CONTROL player1 ON
		SET_EVERYONE_IGNORE_PLAYER player1 OFF
		SHUT_CHAR_UP scplayer FALSE
	ENDIF
	GET_GAME_TIMER timer_mobile_start
	timer_mobile_diff = 0
	GET_GAME_TIMER timer_mobile_now
	timer_mobile_diff = timer_mobile_now - timer_mobile_start
	CLEAR_PRINTS
	IF flag_new_cont = 0
		CLEAR_HELP
	ELSE
		flag_new_cont = 0
	ENDIF
	MARK_MODEL_AS_NO_LONGER_NEEDED cellphone
	flag_player_on_mission = 0
RETURN


load_and_play_mobile_calls:

	cell_index_start  = 0


	loop_cell_phone_audio:


	LOAD_MISSION_AUDIO 1 mobile_audio_labels[cell_index_start]
	
	cell_index_start ++

	IF cell_index_start <= cell_index_end
		LOAD_MISSION_AUDIO 2 mobile_audio_labels[cell_index_start]
	ENDIF
		
	cell_index_start --

	audio_slot_mobile = 1
	GOSUB loading_and_playing_audio
	PRINT_NOW ( $mobile_print_labels[cell_index_start] ) 10000 1 //rhubarb rhubarb
	GOSUB has_audio_finished
	
	IF players_skipping_the_call = 2
		RETURN
	ENDIF

	cell_index_start ++

	IF cell_index_start <= cell_index_end
		audio_slot_mobile = 2
		GOSUB loading_and_playing_audio
		PRINT_NOW ( $mobile_print_labels[cell_index_start] ) 10000 1 //rhubarb rhubarb
		GOSUB has_audio_finished
	ELSE
		cell_index_start --
	ENDIF

	IF players_skipping_the_call = 2
		RETURN
	ENDIF
	
	cell_index_start ++

	IF skip_the_mobile_call = 0
		PRINT_HELP CELSKIP  
		skip_the_mobile_call = 1
	ENDIF

	IF cell_index_start <= cell_index_end 
		GOTO loop_cell_phone_audio
	ENDIF

	CLEAR_PRINTS


RETURN

}

   


		/* OLD FAT CALL
		$mobile_print_labels[0] = &MSWE06A	//Yo?
		$mobile_print_labels[1] = &MSWE06B	//Hey, CJ, it’s Sweet.
		$mobile_print_labels[2] = &MSWE06C	//Whassup?
		$mobile_print_labels[3] = &MSWE06D	//If you don’t respect your body, ain’t nobody going to respect you!
		$mobile_print_labels[4] = &MSWE06O	//You’re too fat, CJ, you need to get some exercise!
		$mobile_print_labels[5] = &MSWE06F	//If I wanted nagging, I’d buy a clockwork wife!
		$mobile_print_labels[6] = &MSWE06G	//Just looking out for you, homie.
		$mobile_print_labels[7] = &MSWE06H	//It’s all show and respect, you lnow?
		$mobile_print_labels[8] = &MSWE06J	//Yeah, I guess.
		$mobile_print_labels[9] = &MSWE06K	//There’s a gym I use a couple of blocks out from the Grove.
		$mobile_print_labels[10] = &MSWE06L	//Go check it out and get yo’self a gangsta’s physique.
		$mobile_print_labels[11] = &MSWE06N	//I’ll scope it out.
		$mobile_print_labels[12] = &MSWE06M	//Later, man.	

		mobile_audio_labels[0] = SOUND_MSWE06A	//Yo?
		mobile_audio_labels[1] = SOUND_MSWE06B	//Hey, CJ, it’s Sweet.
		mobile_audio_labels[2] = SOUND_MSWE06C	//Whassup?
		mobile_audio_labels[3] = SOUND_MSWE06D	//If you don’t respect your body, ain’t nobody going to respect you!
		mobile_audio_labels[4] = SOUND_MSWE06E	//You’re too skinny, CJ, you need to pack on some muscle!
		mobile_audio_labels[5] = SOUND_MSWE06F	//If I wanted nagging, I’d buy a clockwork wife!
		mobile_audio_labels[6] = SOUND_MSWE06G	//Just looking out for you, homie.
		mobile_audio_labels[7] = SOUND_MSWE06H	//It’s all show and respect, you lnow?
		mobile_audio_labels[8] = SOUND_MSWE06J	//Yeah, I guess.
		mobile_audio_labels[9] = SOUND_MSWE06K	//There’s a gym I use a couple of blocks out from the Grove.
		mobile_audio_labels[10] = SOUND_MSWE06L	//Go check it out and get yo’self a gangsta’s physique.
		mobile_audio_labels[11] = SOUND_MSWE06N	//I’ll scope it out.
		mobile_audio_labels[12] = SOUND_MSWE06M	//Later, man.
		*/