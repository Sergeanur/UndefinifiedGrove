MISSION_START
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// **********************************car gen*********************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

 
// Variables for mission

VAR_INT beach_first_floor_cars_exist beach_second_floor_cars_exist beach_third_floor_cars_exist beach_need_to_clear_area_flag 
VAR_INT	mall_first_floor_cars_exist mall_second_floor_cars_exist mall_third_floor_cars_exist mall_fourth_floor_cars_exist mall_fifth_floor_cars_exist mall_need_to_clear_area_flag 
// ****************************************Mission Start************************************


SCRIPT_NAME cargen
SET_DEATHARREST_STATE OFF
{
car_park_inner:

	WAIT 500

	//car park next to big mall

	IF IS_PLAYER_PLAYING player1
		IF IS_PLAYER_IN_ZONE player1 BEACH3
			IF IS_PLAYER_IN_AREA_3D player1 369.7 1207.3 24.5 372.2 1212.8 28.5 FALSE
				mall_need_to_clear_area_flag = 4
			ENDIF
			IF IS_PLAYER_IN_AREA_3D	player1 301.9 1152.3 16.1 342.4 1259.4 22.0 FALSE// 1ST FLOOR LOCATE
				IF mall_first_floor_cars_exist = 0
					GOSUB mall_first_floor_of_cars
					mall_first_floor_cars_exist = 1
				ENDIF
				IF mall_second_floor_cars_exist = 0
					GOSUB mall_second_floor_of_cars
					mall_second_floor_cars_exist = 1
				ENDIF
				IF mall_third_floor_cars_exist = 1
					CLEAR_AREA_OF_CARS 342.4 1259.1 21.5 302.5 1153.3 26.0 // 3RD FLOOR CLEAR
					mall_third_floor_cars_exist = 0
				ENDIF
				IF mall_fourth_floor_cars_exist = 1
					CLEAR_AREA_OF_CARS 342.4 1153.3 23.8 367.2 1259.1 28.9 // 4th FLOOR CLEAR
					mall_fourth_floor_cars_exist = 0
				ENDIF
				IF mall_fifth_floor_cars_exist = 1
					CLEAR_AREA_OF_CARS 302.1 1151.5 32.0 342.7 1259.4 26.5// 5th FLOOR CLEAR
					mall_fifth_floor_cars_exist = 0
				ENDIF
				mall_need_to_clear_area_flag = 1
			ENDIF
			
			IF mall_need_to_clear_area_flag > 0 
				IF IS_PLAYER_IN_AREA_3D	player1 341.3 1258.9 19.1 367.2 1152.6 23.8 FALSE// 2nd FLOOR LOCATE
					IF mall_first_floor_cars_exist = 0
						GOSUB mall_first_floor_of_cars
						mall_first_floor_cars_exist = 1
					ENDIF
					IF mall_second_floor_cars_exist = 0
						GOSUB mall_second_floor_of_cars
						mall_second_floor_cars_exist = 1
					ENDIF
					IF mall_third_floor_cars_exist = 0
						GOSUB mall_third_floor_of_cars
						mall_third_floor_cars_exist = 1
					ENDIF
					IF mall_fourth_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 342.4 1153.3 23.8 367.2 1259.1 28.9 
						mall_fourth_floor_cars_exist = 0
					ENDIF
					IF mall_fifth_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 302.1 1151.5 32.0 342.7 1259.4 26.5
						mall_fifth_floor_cars_exist = 0
					ENDIF
					mall_need_to_clear_area_flag = 2
				ENDIF
			ENDIF
			
			IF mall_need_to_clear_area_flag > 1 
				IF IS_PLAYER_IN_AREA_3D	player1 342.4 1259.1 21.5 302.5 1153.3 26.0 FALSE// 3rd FLOOR LOCATE
					IF mall_first_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 301.9 1152.3 16.1 342.4 1259.4 22.0
						mall_first_floor_cars_exist = 0
					ENDIF
					IF mall_second_floor_cars_exist = 0
						GOSUB mall_second_floor_of_cars
						mall_second_floor_cars_exist = 1
					ENDIF
					IF mall_third_floor_cars_exist = 0
						GOSUB mall_third_floor_of_cars
						mall_third_floor_cars_exist = 1
					ENDIF
					IF mall_fourth_floor_cars_exist = 0
						GOSUB mall_fourth_floor_of_cars
						mall_fourth_floor_cars_exist = 1
					ENDIF
					IF mall_fifth_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 302.1 1151.5 32.0 342.7 1259.4 26.5
						mall_fifth_floor_cars_exist = 0
					ENDIF
					mall_need_to_clear_area_flag = 3
				ENDIF
			ENDIF

			IF mall_need_to_clear_area_flag > 2 
				IF IS_PLAYER_IN_AREA_3D	player1 342.4 1153.3 23.8   372.3 1259.1 28.9 FALSE// 4th FLOOR LOCATE
					IF mall_first_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 301.9 1152.3 16.1 342.4 1259.4 22.0
						mall_first_floor_cars_exist = 0
					ENDIF
					IF mall_second_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 341.3 1258.9 19.1 367.2 1152.6 23.8
						mall_second_floor_cars_exist = 0
					ENDIF
					IF mall_third_floor_cars_exist = 0
						GOSUB mall_third_floor_of_cars
						mall_third_floor_cars_exist = 1
					ENDIF
					IF mall_fourth_floor_cars_exist = 0
						GOSUB mall_fourth_floor_of_cars
						mall_fourth_floor_cars_exist = 1
					ENDIF
					IF mall_fifth_floor_cars_exist = 0
						GOSUB mall_fifth_floor_of_cars
						mall_fifth_floor_cars_exist = 1
					ENDIF
					mall_need_to_clear_area_flag = 4
				ENDIF
			ENDIF

			IF mall_need_to_clear_area_flag > 3 
				IF IS_PLAYER_IN_AREA_3D	player1 302.1 1151.5 32.0 342.7 1259.4 26.5  FALSE// 5th FLOOR LOCATE
					IF mall_first_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 301.9 1152.3 16.1 342.4 1259.4 22.0
						mall_first_floor_cars_exist = 0
					ENDIF
					IF mall_second_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 341.3 1258.9 19.1 367.2 1152.6 23.8
						mall_second_floor_cars_exist = 0
					ENDIF
					IF mall_third_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 342.4 1259.1 21.5 302.5 1153.3 26.0
						mall_third_floor_cars_exist = 0
					ENDIF
					IF mall_fourth_floor_cars_exist = 0
						GOSUB mall_fourth_floor_of_cars
						mall_fourth_floor_cars_exist = 1
					ENDIF
					IF mall_fifth_floor_cars_exist = 0
						GOSUB mall_fifth_floor_of_cars
						mall_fifth_floor_cars_exist = 1
					ENDIF
					mall_need_to_clear_area_flag = 5
				ENDIF
			ENDIF

			IF mall_need_to_clear_area_flag > 0
				IF NOT IS_PLAYER_IN_AREA_3D player1 301.9 1152.3 16.1  372.3 1258.4 32.6 FALSE // ENTIRE CARPARK LOCATE
					CLEAR_AREA_OF_CARS 301.9 1152.3 16.1 342.4 1259.4 22.0 // 1st FLOOR CLEAR
					CLEAR_AREA_OF_CARS 341.3 1258.9 19.1 367.2 1152.6 23.8 // 2ND FLOOR CLEAR
					CLEAR_AREA_OF_CARS 342.4 1259.1 21.5 302.5 1153.3 26.0 // 3RD FLOOR CLEAR
					CLEAR_AREA_OF_CARS 342.4 1153.3 23.8 367.2 1259.1 28.9 //4th floor clear
					CLEAR_AREA_OF_CARS 302.1 1151.5 32.0 342.7 1259.4 26.5 //fifth floor clear
					mall_first_floor_cars_exist = 0
					mall_second_floor_cars_exist = 0
					mall_third_floor_cars_exist  = 0
					mall_fourth_floor_cars_exist = 0
					mall_fifth_floor_cars_exist  = 0
					mall_need_to_clear_area_flag = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF



	//car park behind in ocean beach

	IF IS_PLAYER_PLAYING player1
		IF IS_PLAYER_IN_ZONE player1 BEACH1
			IF IS_PLAYER_IN_AREA_3D	player1 104.9 -1143.3 23.6 158.3 -1239.3 2.1 FALSE// 1ST FLOOR LOCATE
				IF beach_first_floor_cars_exist = 0
					GOSUB beach_first_floor_of_cars
					beach_first_floor_cars_exist = 1
				ENDIF
				IF beach_second_floor_cars_exist = 0
					GOSUB beach_second_floor_of_cars
					beach_second_floor_cars_exist = 1
				ENDIF
				IF beach_third_floor_cars_exist = 1
					CLEAR_AREA_OF_CARS 158.3 -1236.6 29.9 104.5 -1142.1 36.8 // 3RD FLOOR CLEAR
					beach_third_floor_cars_exist = 0
				ENDIF
				beach_need_to_clear_area_flag = 1
			ENDIF
			IF beach_need_to_clear_area_flag > 0 
				IF IS_PLAYER_IN_AREA_3D	player1 104.9 -1143.3 23.6 158.3 -1236.6 29.9 FALSE// 2nd FLOOR LOCATE
					IF beach_first_floor_cars_exist = 0
						GOSUB beach_first_floor_of_cars
						beach_first_floor_cars_exist = 1
					ENDIF
					IF beach_second_floor_cars_exist = 0
						GOSUB beach_second_floor_of_cars
						beach_second_floor_cars_exist = 1
					ENDIF
					IF beach_third_floor_cars_exist = 0
						GOSUB beach_third_floor_of_cars
						beach_third_floor_cars_exist = 1
					ENDIF
					beach_need_to_clear_area_flag = 2
				ENDIF
			ENDIF
			IF beach_need_to_clear_area_flag > 1 
				IF IS_PLAYER_IN_AREA_3D	player1 158.3 -1236.6 29.9 104.5 -1142.1 36.8 FALSE// 3rd FLOOR LOCATE
					IF beach_first_floor_cars_exist = 1
						CLEAR_AREA_OF_CARS 104.9 -1143.3 23.6 158.3 -1239.3 2.1
						beach_first_floor_cars_exist = 0
					ENDIF
					IF beach_second_floor_cars_exist = 0
						GOSUB beach_second_floor_of_cars
						beach_second_floor_cars_exist = 1
					ENDIF
					IF beach_third_floor_cars_exist = 0
						GOSUB beach_third_floor_of_cars
						beach_third_floor_cars_exist = 1
					ENDIF
					beach_need_to_clear_area_flag = 3
				ENDIF
			ENDIF
			IF beach_need_to_clear_area_flag > 0
				IF NOT IS_PLAYER_IN_AREA_3D player1 158.3 -1239.3 2.1 104.5 -1142.1 36.8 0 // ENTIRE CARPARK LOCATE
					CLEAR_AREA_OF_CARS 104.9 -1143.3 23.6 158.3 -1239.3 2.1 // 1st FLOOR CLEAR
					CLEAR_AREA_OF_CARS 104.9 -1143.3 23.6 158.3 -1236.6 29.9 // 2ND FLOOR CLEAR
					CLEAR_AREA_OF_CARS 158.3 -1236.6 29.9 104.5 -1142.1 36.8 // 3RD FLOOR CLEAR
					beach_first_floor_cars_exist = 0
					beach_second_floor_cars_exist = 0
					beach_third_floor_cars_exist  = 0
					beach_need_to_clear_area_flag = 0
				ENDIF
			ENDIF
		ENDIF
	ENDIF

GOTO car_park_inner
MISSION_END
}


//mall car park cars
mall_first_floor_of_cars:////////////////////////////////////////////// // 1st FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 310.2 1234.8 16.2 90.2
	CREATE_RANDOM_CAR_FOR_CAR_PARK 336.4 1250.7 16.2 271.9
	CREATE_RANDOM_CAR_FOR_CAR_PARK 307.6 1160.9 16.2 92.8
	CREATE_RANDOM_CAR_FOR_CAR_PARK 336.4 1157.7 16.2 270.1
	CREATE_RANDOM_CAR_FOR_CAR_PARK 307.4 1253.6 16.2 89.4
RETURN/////////////////////////////////////////////////////////////

mall_second_floor_of_cars:////////////////////////////////////////////// // 2ND FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 347.5 1185.5 19.1 95.2
	CREATE_RANDOM_CAR_FOR_CAR_PARK 361.4 1202.9 19.1 273.7 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 361.3 1157.8 19.1 267.7
	CREATE_RANDOM_CAR_FOR_CAR_PARK 347.6 1228.8 19.0 92.5 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 361.4 1255.5 19.1 269.5
	CREATE_RANDOM_CAR_FOR_CAR_PARK 362.4 1228.7 19.1 264.6
RETURN/////////////////////////////////////////////////////////////

mall_third_floor_of_cars:////////////////////////////////////////////// // 3rd FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 308.4 1225.8 21.5 87.4
	CREATE_RANDOM_CAR_FOR_CAR_PARK 335.8 1207.9 21.5 271.2
	CREATE_RANDOM_CAR_FOR_CAR_PARK 335.8 1213.6 21.5 270.6
	CREATE_RANDOM_CAR_FOR_CAR_PARK 308.4 1196.2 21.5 90.0 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 307.2 1168.4 21.5 89.6 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 336.2 1182.3 21.5 270.3  
RETURN/////////////////////////////////////////////////////////////

mall_fourth_floor_of_cars:////////////////////////////////////////////// // 4th FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 348.3 1190.1 23.8 91.0
	CREATE_RANDOM_CAR_FOR_CAR_PARK 348.0 1203.5 23.8 87.5
	CREATE_RANDOM_CAR_FOR_CAR_PARK 348.2 1208.5 23.8 90.2
	CREATE_RANDOM_CAR_FOR_CAR_PARK 347.1 1227.1 23.8 89.8 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 348.2 1253.7 23.8 92.5 
RETURN/////////////////////////////////////////////////////////////

mall_fifth_floor_of_cars:////////////////////////////////////////////// // 5th FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 336.8 1158.1 26.3 269.4
	CREATE_RANDOM_CAR_FOR_CAR_PARK 335.0 1186.2 26.3 268.7
	CREATE_RANDOM_CAR_FOR_CAR_PARK 336.3 1190.7 26.3 271.2
	CREATE_RANDOM_CAR_FOR_CAR_PARK 337.0 1227.0 26.2 269.5 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 337.1 1221.0 26.2 267.9 
RETURN/////////////////////////////////////////////////////////////



//beach car park cars
beach_first_floor_of_cars:////////////////////////////////////////////// // 1st FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 109.8 -1151.2 16.5 1.6 	  
	CREATE_RANDOM_CAR_FOR_CAR_PARK 129.7 -1150.8 16.5 0.4 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 143.6 -1153.0 16.5 358.9 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 123.0 -1180.8 16.5 1.3 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 128.0 -1180.8 16.5 1.3 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 133.0 -1180.8 16.5 1.3
RETURN/////////////////////////////////////////////////////////////

beach_second_floor_of_cars:////////////////////////////////////////////// // 2ND FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 150.5 -1192.9 23.2 265.1 	  
	CREATE_RANDOM_CAR_FOR_CAR_PARK 141.6 -1228.8 23.2 181.3 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 127.1 -1228.7 23.2 178.6 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 117.9 -1152.3 23.2 0.1 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 141.8 -1151.1 23.2 0.0 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 129.2 -1179.3 23.2 0.0
RETURN/////////////////////////////////////////////////////////////

beach_third_floor_of_cars:////////////////////////////////////////////// // 3rd FLOOR CARS
	CREATE_RANDOM_CAR_FOR_CAR_PARK 133.0 -1199.6 30.0 182.6 	  
	CREATE_RANDOM_CAR_FOR_CAR_PARK 123.7 -1199.7 30.0 180.4 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 133.0 -1167.9 30.0 182.6
	CREATE_RANDOM_CAR_FOR_CAR_PARK 146.3 -1149.3 30.1 273.0 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 143.0 -1230.0 30.1 181.5 
	CREATE_RANDOM_CAR_FOR_CAR_PARK 135.3 -1217.7 30.1 1.5 
RETURN/////////////////////////////////////////////////////////////



