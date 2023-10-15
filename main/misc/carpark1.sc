MISSION_START
{

SCRIPT_NAME carprk1
SET_DEATHARREST_STATE OFF

lvar_int flag_created_cars_level1
lvar_int flag_created_cars_level2
lvar_int flag_created_cars_level3
lvar_int flag_created_cars_level4
lvar_int flag_created_cars_level5
lvar_int flag_created_cars_level6
lvar_int flag_created_cars_level7
lvar_int flag_created_cars_level8
lvar_int flag_created_cars_level9
lvar_int flag_created_cars_level10
flag_created_cars_level1  =	0
flag_created_cars_level2  =	0
flag_created_cars_level3  =	0
flag_created_cars_level4  =	0
flag_created_cars_level5  =	0
flag_created_cars_level6  =	0
flag_created_cars_level7  =	0
flag_created_cars_level8  =	0
flag_created_cars_level9  =	0
flag_created_cars_level10 =	0

carpark1_loop:

	WAIT 0

	if is_player_playing player1

		//2052.4316 2366.1516 9.8203 2117.2830 2441.0017 52.9123//entire carpark locate
		if is_char_in_area_3d scplayer 2037.5684 2366.1516 9.8203 2119.2830 2472.4177 14.1172 0//level 1
			if flag_created_cars_level1 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2077.8723 2398.6567 9.8203 89.6383 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.0994 2414.1765 9.8203 90.4575 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.8589 2416.4395 9.8203 270.7819 
				flag_created_cars_level1 = 1
			endif
		else
			if flag_created_cars_level1 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516 9.8203 2117.2830 2441.0017 14.1172
				flag_created_cars_level1 = 0
			endif
		endif

		if is_char_in_area_3d scplayer 2052.4316 2366.1516	13.1172 2119.2830 2441.0017 18.4219 0//level 2
			if flag_created_cars_level2 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.5369 2412.2998 14.1172 89.9851 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2095.3208 2398.5591 14.1172 90.1384 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.0713 2405.5254 14.1172 267.6596 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.1873 2420.8875 14.1172 90.4995 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.3599 2410.1389 14.1172 270.9704 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.1682 2396.7781 14.1172 98.3338 
				flag_created_cars_level2 = 1
			endif
		else
			if flag_created_cars_level2 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	14.1172 2117.2830 2441.0017 18.4219
				flag_created_cars_level2 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516	17.4219 2119.2830 2441.0017 22.7188 0//level 3
			if flag_created_cars_level3 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.5171 2405.8865 18.4219 92.3912 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2095.6877 2396.1953 18.4219 270.3566 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2086.9995 2416.4419 18.4219 89.9821 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2086.9253 2402.8958 18.4219 270.2662 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.0708 2409.6509 18.4219 89.1894 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.1462 2416.4998 18.4219 270.7297 
				flag_created_cars_level3 = 1
			endif
		else
			if flag_created_cars_level3 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	18.4219 2117.2830 2441.0017 22.7188
				flag_created_cars_level3 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516	21.7188 2119.2830 2441.0017 27.0234 0//level 4
			if flag_created_cars_level4 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.4993 2411.8516 22.7188 90.7897 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.4832 2407.6182 22.7188 91.9516 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.2422 2400.8540 22.7188 269.9636 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.3127 2412.0769 22.7188 267.6149 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.1379 2396.2512 22.7188 92.2442 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.1792 2392.0215 22.7188 87.7661 
				flag_created_cars_level4 = 1
			endif
		else
			if flag_created_cars_level4 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	22.7188 2117.2830 2441.0017 27.0234
				flag_created_cars_level4 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516 24.7815 2119.2830 2441.0017 31.3203 0//level 5
			if flag_created_cars_level5 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.6606 2416.4219 27.0234 268.6120 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2077.8723 2398.6567 27.0234 89.6383 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.1948 2394.3635 27.0234 271.5008 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.0994 2414.1765 27.0234 90.4575 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2095.8179 2398.6858 27.0234 268.2181 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.8589 2416.4395 27.0234 270.7819 
				flag_created_cars_level5 = 1
			endif
		else
			if flag_created_cars_level5 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	27.0234 2117.2830 2441.0017 31.3203
				flag_created_cars_level5 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516	30.3203 2119.2830 2441.0017 35.6172 0//level 6
			if flag_created_cars_level6 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.5369 2412.2998 31.3203 89.9851 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2095.3208 2398.5591 31.3203 90.1384 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.0713 2405.5254 31.3203 267.6596 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.1873 2420.8875 31.3203 90.4995 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.3599 2410.1389 31.3203 270.9704 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.1682 2396.7781 31.3203 98.3338 
				flag_created_cars_level6 = 1
			endif
		else
			if flag_created_cars_level6 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	31.3203 2117.2830 2441.0017 35.6172
				flag_created_cars_level6 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516	34.6172 2119.2830 2441.0017 39.9219 0//level 7
			if flag_created_cars_level7 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.5171 2405.8865 35.6172 92.3912 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2095.6877 2396.1953 35.6172 270.3566 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2086.9995 2416.4419 35.6172 89.9821 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2086.9253 2402.8958 35.6172 270.2662 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.0708 2409.6509 35.6172 89.1894 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.1462 2416.4998 35.6172 270.7297 
				flag_created_cars_level7 = 1
			endif
		else
			if flag_created_cars_level7 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	35.6172 2117.2830 2441.0017 39.9219
				flag_created_cars_level7 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516	38.9219 2119.2830 2441.0017 44.2188 0//level 8
			if flag_created_cars_level8 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.4993 2411.8516 39.9219 90.7897 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.4832 2407.6182 39.9219 91.9516 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.2422 2400.8540 39.9219 269.9636 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.3127 2412.0769 39.9219 267.6149 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.1379 2396.2512 39.9219 92.2442 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.1792 2392.0215 39.9219 87.7661 
				flag_created_cars_level8 = 1
			endif
		else
			if flag_created_cars_level8 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	39.9219 2117.2830 2441.0017 44.2188
				flag_created_cars_level8 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516 43.2188 2119.2830 2441.0017 48.5234 0//level 9
			if flag_created_cars_level9 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.6606 2416.4219 44.2188 268.6120 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2077.8723 2398.6567 44.2188 89.6383 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.1948 2394.3635 44.2188 271.5008 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.0994 2414.1765 44.2188 90.4575 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2095.8179 2398.6858 44.2188 268.2181 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.8589 2416.4395 44.2188 270.7819 
				flag_created_cars_level9 = 1
			endif
		else
			if flag_created_cars_level9 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	44.2188 2117.2830 2441.0017 48.5234
				flag_created_cars_level9 = 0
			endif
		endif
		
		if is_char_in_area_3d scplayer 2052.4316 2366.1516	44.5234 2119.2830 2441.0017 55.9123 0//level 10
			if flag_created_cars_level10 = 0
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2104.5369 2412.2998 48.5234 89.9851 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2095.3208 2398.5591 48.5234 90.1384 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.0713 2405.5254 48.5234 267.6596 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2087.1873 2420.8875 48.5234 90.4995 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2078.3599 2410.1389 48.5234 270.9704 
				CREATE_RANDOM_CAR_FOR_CAR_PARK 2069.1682 2396.7781 48.5234 98.3338 
				flag_created_cars_level10 = 1
			endif
		else
			if flag_created_cars_level10 = 1
				CLEAR_AREA_OF_CARS 2052.4316 2366.1516	48.5234 2117.2830 2441.0017 52.9123
				flag_created_cars_level10 = 0
			endif
		endif
		
		if not is_char_in_area_2d scplayer 2037.5359 2365.3726 2119.9 2483.9216 0
			terminate_this_script
		endif
		
	endif

GOTO carpark1_loop
MISSION_END
}
