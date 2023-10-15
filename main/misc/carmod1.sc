MISSION_START

{

VAR_INT mod_flag control_flag_mod // these are used to say what point the script is at.

// Car colour and paintjobs
CONST_INT MOD_GARAGE_PAINTJOB 100
CONST_INT MOD_GARAGE_CAR_COLOUR 101

VAR_INT number_of_colours
number_of_colours = 0

VAR_INT stored_paintjob_number paintjob_number paintjob_number_menu
stored_paintjob_number = 0
paintjob_number = 0
paintjob_number_menu = 0

// cameras for noraml car and street racers
CONST_FLOAT cam_general_true_modX 611.2935  // used for stereo's, car colours and paintjobs      
CONST_FLOAT cam_general_true_modY -121.2534
CONST_FLOAT cam_general_true_modZ 999.0341

// Lowrider normal cam
CONST_FLOAT camLR_general_true_modX 611.9196 // used for stereo's, car colours and paintjobs      
CONST_FLOAT camLR_general_true_modY -71.9830
CONST_FLOAT camLR_general_true_modZ 998.9867

// normal car
CONST_FLOAT camNC_general_true_modX 612.7667   
CONST_FLOAT camNC_general_true_modY	0.5696
CONST_FLOAT	camNC_general_true_modZ	1002.1138

VAR_FLOAT cam_general_modX cam_general_modY cam_general_modZ
cam_general_modX = 0.0
cam_general_modY = 0.0
cam_general_modZ = 0.0

CONST_FLOAT cam_general_look_at_true_modX 612.0599
CONST_FLOAT cam_general_look_at_true_modY -121.7974
CONST_FLOAT cam_general_look_at_true_modZ 998.6927

// Lowrider version
CONST_FLOAT camLR_general_look_at_true_modX 612.8176   
CONST_FLOAT camLR_general_look_at_true_modY -72.3798
CONST_FLOAT camLR_general_look_at_true_modZ 998.7969

// normal car
CONST_FLOAT camNC_general_look_at_true_modX 613.6664  
CONST_FLOAT	camNC_general_look_at_true_modY	0.1645
CONST_FLOAT	camNC_general_look_at_true_modZ	1001.9515

VAR_FLOAT cam_general_look_at_modX cam_general_look_at_modY cam_general_look_at_modZ
cam_general_look_at_modX = 0.0
cam_general_look_at_modY = 0.0
cam_general_look_at_modZ = 0.0  

// Wheel cam
CONST_FLOAT wheel_cam_true_modX 612.5510
CONST_FLOAT wheel_cam_true_modY	-126.8767
CONST_FLOAT wheel_cam_true_modZ	997.6746

// LowRider One
CONST_FLOAT wheel_camLR_true_modX 613.5045      
CONST_FLOAT wheel_camLR_true_modY -77.6100
CONST_FLOAT wheel_camLR_true_modZ 997.6773

// normal car
CONST_FLOAT wheel_camNC_true_modX 614.4382      
CONST_FLOAT wheel_camNC_true_modY -4.5756
CONST_FLOAT wheel_camNC_true_modZ 1000.6324

VAR_FLOAT wheel_cam_modX wheel_cam_modY wheel_cam_modZ
wheel_cam_modX = 0.0
wheel_cam_modY = 0.0
wheel_cam_modZ = 0.0

CONST_FLOAT wheel_cam_true_look_at_modX 613.1689
CONST_FLOAT wheel_cam_true_look_at_modY -126.0916
CONST_FLOAT wheel_cam_true_look_at_modZ	997.6324

// Lowrider one
CONST_FLOAT wheel_camLR_true_look_at_modX 614.0041
CONST_FLOAT wheel_camLR_true_look_at_modY -76.7527
CONST_FLOAT wheel_camLR_true_look_at_modZ 997.8019

// normal car
CONST_FLOAT wheel_camNC_true_look_at_modX 614.9804   
CONST_FLOAT wheel_camNC_true_look_at_modY -3.7381
CONST_FLOAT wheel_camNC_true_look_at_modZ 1000.5651 

VAR_FLOAT wheel_cam_look_at_modX wheel_cam_look_at_modY wheel_cam_look_at_modZ
wheel_cam_look_at_modX = 0.0
wheel_cam_look_at_modY = 0.0
wheel_cam_look_at_modZ = 0.0 

// Spoiler cams
CONST_FLOAT spoiler_cam_true_modX 615.7711     
CONST_FLOAT spoiler_cam_true_modY -126.8826
CONST_FLOAT spoiler_cam_true_modZ 998.7869

// Lowrider 
CONST_FLOAT spoiler_camLR_true_modX 617.6616     
CONST_FLOAT spoiler_camLR_true_modY -77.5803
CONST_FLOAT spoiler_camLR_true_modZ 998.5854

// normal car
CONST_FLOAT spoiler_camNC_true_modX 618.1872 // 617.8716     
CONST_FLOAT spoiler_camNC_true_modY -5.3884 //	-4.9760
CONST_FLOAT spoiler_camNC_true_modZ 1002.4232 //1001.4346

VAR_FLOAT spoiler_cam_modX spoiler_cam_modY spoiler_cam_modZ
spoiler_cam_modX = 0.0
spoiler_cam_modY = 0.0
spoiler_cam_modZ = 0.0

CONST_FLOAT spoiler_cam_true_look_at_modX 616.1115  
CONST_FLOAT spoiler_cam_true_look_at_modY -125.9810
CONST_FLOAT spoiler_cam_true_look_at_modZ 998.5200

// Lowrider
CONST_FLOAT spoiler_camLR_true_look_at_modX 618.0117    
CONST_FLOAT spoiler_camLR_true_look_at_modY	-76.6793
CONST_FLOAT spoiler_camLR_true_look_at_modZ 998.3290

// normal car
CONST_FLOAT spoiler_camNC_true_look_at_modX 618.4805 // 618.1115      
CONST_FLOAT spoiler_camNC_true_look_at_modY -4.4899 //	-4.0173
CONST_FLOAT spoiler_camNC_true_look_at_modZ 1002.0967 //	1001.2820

VAR_FLOAT spoiler_cam_look_at_modX spoiler_cam_look_at_modY spoiler_cam_look_at_modZ
spoiler_cam_look_at_modX = 0.0
spoiler_cam_look_at_modY = 0.0
spoiler_cam_look_at_modZ = 0.0

// Wings camera
CONST_FLOAT wing_cam_true_modX 612.5510          
CONST_FLOAT wing_cam_true_modY -126.8767
CONST_FLOAT wing_cam_true_modZ 997.6746

// Lowrider
CONST_FLOAT wing_camLR_true_modX 616.0645           
CONST_FLOAT wing_camLR_true_modY -77.4652
CONST_FLOAT wing_camLR_true_modZ 998.1566

// normal car
CONST_FLOAT wing_camNC_true_modX 615.6667            
CONST_FLOAT wing_camNC_true_modY -4.5419
CONST_FLOAT wing_camNC_true_modZ 1000.2697

VAR_FLOAT wing_cam_modX wing_cam_modY wing_cam_modZ
wing_cam_modX = 0.0
wing_cam_modY = 0.0
wing_cam_modZ = 0.0

CONST_FLOAT wing_cam_true_look_at_modX 613.1689
CONST_FLOAT wing_cam_true_look_at_modY -126.0916
CONST_FLOAT wing_cam_true_look_at_modZ 997.6324

// Lowrider
CONST_FLOAT wing_camLR_true_look_at_modX 616.6523  
CONST_FLOAT wing_camLR_true_look_at_modY -76.6904
CONST_FLOAT wing_camLR_true_look_at_modZ 997.9242

// normal car
CONST_FLOAT wing_camNC_true_look_at_modX 616.2391     
CONST_FLOAT wing_camNC_true_look_at_modY -3.7425
CONST_FLOAT wing_camNC_true_look_at_modZ 1000.4523

VAR_FLOAT wing_cam_look_at_modX wing_cam_look_at_modY wing_cam_look_at_modZ
wing_cam_look_at_modX = 0.0
wing_cam_look_at_modY = 0.0
wing_cam_look_at_modZ = 0.0

// roof camera
CONST_FLOAT roof_cam_true_modX 611.6899     
CONST_FLOAT roof_cam_true_modY -124.1341
CONST_FLOAT roof_cam_true_modZ 998.9570

// Lowrider
CONST_FLOAT roof_camLR_true_modX 615.7546      
CONST_FLOAT roof_camLR_true_modY -77.9771
CONST_FLOAT roof_camLR_true_modZ 999.3472

// normal car
CONST_FLOAT roof_camNC_true_modX 613.6697         
CONST_FLOAT roof_camNC_true_modY -1.4788
CONST_FLOAT roof_camNC_true_modZ 1002.2325 

VAR_FLOAT roof_cam_modX roof_cam_modY roof_cam_modZ
roof_cam_modX = 0.0
roof_cam_modY = 0.0 
roof_cam_modZ = 0.0

CONST_FLOAT roof_cam_true_look_at_modX 612.6635 
CONST_FLOAT roof_cam_true_look_at_modY -124.0004
CONST_FLOAT roof_cam_true_look_at_modZ 998.7720

// Lowrider
CONST_FLOAT roof_camLR_true_look_at_modX 616.0991  
CONST_FLOAT roof_camLR_true_look_at_modY -77.1049 
CONST_FLOAT roof_camLR_true_look_at_modZ 998.9999

// normal car
CONST_FLOAT roof_camNC_true_look_at_modX 614.6470   
CONST_FLOAT roof_camNC_true_look_at_modY -1.5313
CONST_FLOAT roof_camNC_true_look_at_modZ 1002.0273

VAR_FLOAT roof_cam_look_at_modX roof_cam_look_at_modY roof_cam_look_at_modZ
roof_cam_look_at_modX = 0.0
roof_cam_look_at_modY = 0.0 
roof_cam_look_at_modZ = 0.0

// bonnet camera
CONST_FLOAT bonnet_cam_true_modX 610.8686     
CONST_FLOAT bonnet_cam_true_modY -123.9456
CONST_FLOAT bonnet_cam_true_modZ 998.8293

// Lowrider
CONST_FLOAT bonnet_camLR_true_modX 612.3392      
CONST_FLOAT bonnet_camLR_true_modY -74.6454
CONST_FLOAT bonnet_camLR_true_modZ 998.7184

// normal car
CONST_FLOAT bonnet_camNC_true_modX 613.5347            
CONST_FLOAT bonnet_camNC_true_modY -1.7916
CONST_FLOAT bonnet_camNC_true_modZ 1002.5261

VAR_FLOAT bonnet_cam_modX bonnet_cam_modY bonnet_cam_modZ
bonnet_cam_modX = 0.0
bonnet_cam_modY = 0.0
bonnet_cam_modZ = 0.0

CONST_FLOAT bonnet_cam_true_look_at_modX 611.8196 
CONST_FLOAT bonnet_cam_true_look_at_modY -123.8469
CONST_FLOAT bonnet_cam_true_look_at_modZ 998.5366

// Lowrider
CONST_FLOAT bonnet_camLR_true_look_at_modX 613.3315    
CONST_FLOAT bonnet_camLR_true_look_at_modY -74.5934
CONST_FLOAT bonnet_camLR_true_look_at_modZ 998.6063

// normal car
CONST_FLOAT bonnet_camNC_true_look_at_modX 614.4059     
CONST_FLOAT bonnet_camNC_true_look_at_modY -1.7952
CONST_FLOAT bonnet_camNC_true_look_at_modZ 1002.0352

VAR_FLOAT bonnet_cam_look_at_modX bonnet_cam_look_at_modY bonnet_cam_look_at_modZ
bonnet_cam_look_at_modX = 0.0
bonnet_cam_look_at_modY = 0.0 
bonnet_cam_look_at_modZ = 0.0

// exhaust camera
CONST_FLOAT exhaust_cam_true_modX 618.9148          
CONST_FLOAT exhaust_cam_true_modY -125.9082
CONST_FLOAT exhaust_cam_true_modZ 997.2668

// Lowrider
CONST_FLOAT exhaust_camLR_true_modX 620.7762
CONST_FLOAT exhaust_camLR_true_modY -76.6076
CONST_FLOAT exhaust_camLR_true_modZ 997.2366

// normal car
CONST_FLOAT exhaust_camNC_true_modX 621.9104 // 621.4796    
CONST_FLOAT exhaust_camNC_true_modY -3.6269 //	-3.4952
CONST_FLOAT exhaust_camNC_true_modZ 1000.4076 // 1000.3300

VAR_FLOAT exhaust_cam_modX exhaust_cam_modY exhaust_cam_modZ 
exhaust_cam_modX = 0.0      
exhaust_cam_modY = 0.0
exhaust_cam_modZ = 0.0 

CONST_FLOAT exhaust_look_at_true_modX 618.0776
CONST_FLOAT exhaust_look_at_true_modY -125.3645
CONST_FLOAT exhaust_look_at_true_modZ 997.3261

// Lowrider
CONST_FLOAT exhaust_look_atLR_true_modX 620.0510   
CONST_FLOAT exhaust_look_atLR_true_modY	-75.9492
CONST_FLOAT exhaust_look_atLR_true_modZ 997.4383

// normal car
CONST_FLOAT exhaust_look_atNC_true_modX 621.0475 // 620.5905      
CONST_FLOAT exhaust_look_atNC_true_modY -3.1303 //	-3.0499
CONST_FLOAT exhaust_look_atNC_true_modZ 1000.3142 // 1000.4355 

VAR_FLOAT exhaust_look_at_cam_modX exhaust_look_at_cam_modY exhaust_look_at_cam_modZ 
exhaust_look_at_cam_modX = 0.0      
exhaust_look_at_cam_modY = 0.0
exhaust_look_at_cam_modZ = 0.0

// front fender
CONST_FLOAT front_fender_cam_true_modX 611.3840     
CONST_FLOAT front_fender_cam_true_modY -122.4332
CONST_FLOAT front_fender_cam_true_modZ 997.4291

// Lowrider
CONST_FLOAT front_fender_camLR_true_modX 612.1144        
CONST_FLOAT front_fender_camLR_true_modY -74.4962
CONST_FLOAT front_fender_camLR_true_modZ 997.7816

// normal car
CONST_FLOAT front_fender_camNC_true_modX 611.6121  // 613.4149           
CONST_FLOAT front_fender_camNC_true_modY -1.4851 // -1.9023
CONST_FLOAT front_fender_camNC_true_modZ 1001.7769 // 1000.5794

VAR_FLOAT front_fender_cam_modX front_fender_cam_modY front_fender_cam_modZ 
front_fender_cam_modX = 0.0      
front_fender_cam_modY = 0.0
front_fender_cam_modZ = 0.0 

CONST_FLOAT front_fender_look_at_true_modX 612.2263  
CONST_FLOAT front_fender_look_at_true_modY -122.9720
CONST_FLOAT front_fender_look_at_true_modZ 997.4357

// lowrider
CONST_FLOAT front_fender_look_atLR_true_modX 613.1110     
CONST_FLOAT front_fender_look_atLR_true_modY -74.4536
CONST_FLOAT front_fender_look_atLR_true_modZ 997.7114

// normal car
CONST_FLOAT front_fender_look_atNC_true_modX 612.6027 //  614.4062       
CONST_FLOAT front_fender_look_atNC_true_modY -1.5070 // -1.8187
CONST_FLOAT front_fender_look_atNC_true_modZ 1001.6419 // 1000.6804

VAR_FLOAT front_fender_look_at_cam_modX front_fender_look_at_cam_modY front_fender_look_at_cam_modZ 
front_fender_look_at_cam_modX = 0.0      
front_fender_look_at_cam_modY = 0.0
front_fender_look_at_cam_modZ = 0.0

// rear fender
CONST_FLOAT rear_fender_cam_true_modX 618.9148
CONST_FLOAT rear_fender_cam_true_modY -125.9082
CONST_FLOAT rear_fender_cam_true_modZ 997.2668

// lowrider
CONST_FLOAT rear_fender_camLR_true_modX 620.7762 
CONST_FLOAT rear_fender_camLR_true_modY -76.6076
CONST_FLOAT rear_fender_camLR_true_modZ 997.2366

// normal car
CONST_FLOAT rear_fender_camNC_true_modX 621.4796    
CONST_FLOAT rear_fender_camNC_true_modY	-3.4952
CONST_FLOAT rear_fender_camNC_true_modZ 1000.3300

VAR_FLOAT rear_fender_cam_modX rear_fender_cam_modY rear_fender_cam_modZ 
rear_fender_cam_modX = 0.0      
rear_fender_cam_modY = 0.0
rear_fender_cam_modZ = 0.0 

CONST_FLOAT rear_fender_look_at_true_modX 618.0776
CONST_FLOAT rear_fender_look_at_true_modY -125.3645
CONST_FLOAT rear_fender_look_at_true_modZ 997.3261

// lowrider
CONST_FLOAT rear_fender_look_atLR_true_modX 620.0510
CONST_FLOAT rear_fender_look_atLR_true_modY -75.9492 
CONST_FLOAT rear_fender_look_atLR_true_modZ	997.4383

// normal car
CONST_FLOAT rear_fender_look_atNC_true_modX 620.5905      
CONST_FLOAT rear_fender_look_atNC_true_modY	-3.0499
CONST_FLOAT rear_fender_look_atNC_true_modZ 1000.4355

VAR_FLOAT rear_fender_look_at_cam_modX rear_fender_look_at_cam_modY rear_fender_look_at_cam_modZ 
rear_fender_look_at_cam_modX = 0.0      
rear_fender_look_at_cam_modY = 0.0
rear_fender_look_at_cam_modZ = 0.0

// lights camera
CONST_FLOAT lights_cam_trueX 611.1373        
CONST_FLOAT lights_cam_trueY -124.2795
CONST_FLOAT lights_cam_trueZ 998.1324

// lowrider
CONST_FLOAT lights_camLR_trueX 612.1144            
CONST_FLOAT lights_camLR_trueY -74.4962
CONST_FLOAT lights_camLR_trueZ 997.7816

// normal car
CONST_FLOAT lights_camNC_trueX 612.6115 // 613.4149           
CONST_FLOAT lights_camNC_trueY -1.7655 // -1.9023
CONST_FLOAT lights_camNC_trueZ 1001.2997 // 1000.5794

VAR_FLOAT lights_cam_modX lights_cam_modY lights_cam_modZ
lights_cam_modX = 0.0
lights_cam_modY = 0.0
lights_cam_modZ = 0.0

CONST_FLOAT lights_look_at_cam_true_modX 612.1359
CONST_FLOAT lights_look_at_cam_true_modY -124.2299
CONST_FLOAT lights_look_at_cam_true_modZ 998.1481

// lowrider
CONST_FLOAT lights_look_atLR_cam_true_modX 613.1110   
CONST_FLOAT lights_look_atLR_cam_true_modY -74.4536
CONST_FLOAT lights_look_atLR_cam_true_modZ 997.7114

// normal car
CONST_FLOAT lights_look_atNC_cam_true_modX 613.5665 // 614.4062       
CONST_FLOAT lights_look_atNC_cam_true_modY -1.6304 // -1.8187
CONST_FLOAT lights_look_atNC_cam_true_modZ 1001.0358 // 1000.6804 

VAR_FLOAT lights_look_at_cam_modX lights_look_at_cam_modY lights_look_at_cam_modZ
lights_look_at_cam_modX = 0.0 
lights_look_at_cam_modY = 0.0 
lights_look_at_cam_modZ = 0.0

// front bullbar
CONST_FLOAT front_bullbar_cam_true_modX 610.8206
CONST_FLOAT front_bullbar_cam_true_modY -126.0154
CONST_FLOAT front_bullbar_cam_true_modZ 997.3317

// lowrider
CONST_FLOAT front_bullbar_camLR_true_modX 612.1144  
CONST_FLOAT front_bullbar_camLR_true_modY -74.4962
CONST_FLOAT front_bullbar_camLR_true_modZ 997.7816

// normal car
CONST_FLOAT front_bullbar_camNC_true_modX 611.6121           
CONST_FLOAT front_bullbar_camNC_true_modY -1.4851
CONST_FLOAT front_bullbar_camNC_true_modZ 1001.7769

VAR_FLOAT front_bullbar_cam_modX front_bullbar_cam_modY front_bullbar_cam_modZ 
front_bullbar_cam_modX = 0.0      
front_bullbar_cam_modY = 0.0
front_bullbar_cam_modZ = 0.0

CONST_FLOAT front_bullbar_look_at_true_modX 611.6263  
CONST_FLOAT front_bullbar_look_at_true_modY -125.4232
CONST_FLOAT front_bullbar_look_at_true_modZ 997.3185

// lowrider
CONST_FLOAT front_bullbar_look_atLR_true_modX 613.1110    
CONST_FLOAT front_bullbar_look_atLR_true_modY -74.4536
CONST_FLOAT front_bullbar_look_atLR_true_modZ 997.7114

// normal car
CONST_FLOAT front_bullbar_look_atNC_true_modX 612.6027       
CONST_FLOAT front_bullbar_look_atNC_true_modY -1.5070
CONST_FLOAT front_bullbar_look_atNC_true_modZ 1001.6419

VAR_FLOAT front_bullbar_look_at_cam_modX front_bullbar_look_at_cam_modY front_bullbar_look_at_cam_modZ 
front_bullbar_look_at_cam_modX = 0.0      
front_bullbar_look_at_cam_modY = 0.0
front_bullbar_look_at_cam_modZ = 0.0

// rear bullbar
CONST_FLOAT rear_bullbar_cam_true_modX 619.0011
CONST_FLOAT rear_bullbar_cam_true_modY -126.1950
CONST_FLOAT rear_bullbar_cam_true_modZ 997.4440

// lowrider
CONST_FLOAT rear_bullbar_camLR_true_modX 620.8461   
CONST_FLOAT rear_bullbar_camLR_true_modY -76.8773
CONST_FLOAT rear_bullbar_camLR_true_modZ 997.3512

// normal car
CONST_FLOAT rear_bullbar_camNC_true_modX 621.4796    
CONST_FLOAT rear_bullbar_camNC_true_modY -3.4952
CONST_FLOAT rear_bullbar_camNC_true_modZ 1000.3300

VAR_FLOAT rear_bullbar_cam_modX rear_bullbar_cam_modY rear_bullbar_cam_modZ 
rear_bullbar_cam_modX = 0.0      
rear_bullbar_cam_modY = 0.0
rear_bullbar_cam_modZ = 0.0 

CONST_FLOAT rear_bullbar_look_at_true_modX 618.3423
CONST_FLOAT rear_bullbar_look_at_true_modY -125.4429
CONST_FLOAT rear_bullbar_look_at_true_modZ 997.4610

// lowrider
CONST_FLOAT rear_bullbar_look_atLR_true_modX 620.2133  
CONST_FLOAT rear_bullbar_look_atLR_true_modY -76.1111
CONST_FLOAT rear_bullbar_look_atLR_true_modZ 997.4626

// normal car
CONST_FLOAT rear_bullbar_look_atNC_true_modX 620.5905      
CONST_FLOAT rear_bullbar_look_atNC_true_modY -3.0499
CONST_FLOAT rear_bullbar_look_atNC_true_modZ 1000.4355

VAR_FLOAT rear_bullbar_look_at_cam_modX rear_bullbar_look_at_cam_modY rear_bullbar_look_at_cam_modZ 
rear_bullbar_look_at_cam_modX = 0.0      
rear_bullbar_look_at_cam_modY = 0.0
rear_bullbar_look_at_cam_modZ = 0.0 

// nitro camera
CONST_FLOAT nitro_cam_true_modX 617.8134           
CONST_FLOAT nitro_cam_true_modY -126.8104
CONST_FLOAT nitro_cam_true_modZ  999.6476

// lowrider
CONST_FLOAT nitro_camLR_true_modX 621.0414              
CONST_FLOAT nitro_camLR_true_modY -74.9455
CONST_FLOAT nitro_camLR_true_modZ 998.9329

// normal car
CONST_FLOAT nitro_camNC_true_modX 622.5965 // 621.0519                 
CONST_FLOAT nitro_camNC_true_modY -2.0140 // -1.9668
CONST_FLOAT nitro_camNC_true_modZ 1003.0187 // 1002.6318

VAR_FLOAT nitro_cam_modX nitro_cam_modY nitro_cam_modZ
nitro_cam_modX = 0.0
nitro_cam_modY = 0.0
nitro_cam_modZ = 0.0

CONST_FLOAT nitro_look_at_true_modX 617.3374      
CONST_FLOAT nitro_look_at_true_modY -126.1012
CONST_FLOAT nitro_look_at_true_modZ 999.1275

// lowrider
CONST_FLOAT nitro_look_atLR_true_modX 620.0759  
CONST_FLOAT nitro_look_atLR_true_modY -74.9535
CONST_FLOAT nitro_look_atLR_true_modZ 998.6727

// normal car
CONST_FLOAT nitro_look_atNC_true_modX 621.7657 // 620.1599    
CONST_FLOAT nitro_look_atNC_true_modY -2.0174 // -1.9631
CONST_FLOAT nitro_look_atNC_true_modZ 1002.4622 // 1002.1797


VAR_FLOAT nitro_look_at_modX nitro_look_at_modY nitro_look_at_modZ 
nitro_look_at_modX = 0.0
nitro_look_at_modY = 0.0 
nitro_look_at_modZ = 0.0

// custom paint camera
CONST_FLOAT custom_paint_cam_true_modX 611.2935     
CONST_FLOAT custom_paint_cam_true_modY -121.2534
CONST_FLOAT custom_paint_cam_true_modZ 999.0341

// lowrider
CONST_FLOAT custom_paint_camLR_true_modX 612.2584        
CONST_FLOAT custom_paint_camLR_true_modY -72.1851
CONST_FLOAT custom_paint_camLR_true_modZ 998.8818

// normal car
CONST_FLOAT custom_paint_camNC_true_modX 612.7667   
CONST_FLOAT custom_paint_camNC_true_modY 0.5696
CONST_FLOAT	custom_paint_camNC_true_modZ 1002.1138

VAR_FLOAT custom_paint_cam_modX custom_paint_cam_modY custom_paint_cam_modZ
custom_paint_cam_modX = 0.0 
custom_paint_cam_modY = 0.0 
custom_paint_cam_modZ = 0.0

CONST_FLOAT custom_paint_cam_look_at_true_modX 612.0599
CONST_FLOAT custom_paint_cam_look_at_true_modY -121.7974
CONST_FLOAT custom_paint_cam_look_at_true_modZ 998.6927

// lowrider
CONST_FLOAT custom_paint_camLR_look_at_true_modX 613.1286    
CONST_FLOAT custom_paint_camLR_look_at_true_modY -72.6701
CONST_FLOAT custom_paint_camLR_look_at_true_modZ 998.7961

// normal car
CONST_FLOAT custom_paint_camNC_look_at_true_modX 613.6664  
CONST_FLOAT	custom_paint_camNC_look_at_true_modY 0.1645
CONST_FLOAT	custom_paint_camNC_look_at_true_modZ 1001.9515

VAR_FLOAT custom_paint_cam_look_at_modX custom_paint_cam_look_at_modY custom_paint_cam_look_at_modZ
custom_paint_cam_look_at_modX = 0.0
custom_paint_cam_look_at_modY = 0.0
custom_paint_cam_look_at_modZ = 0.0

// misc camera 
CONST_FLOAT misc_cam_true_modX 611.2935     
CONST_FLOAT misc_cam_true_modY -121.2534
CONST_FLOAT misc_cam_true_modZ 999.0341

// lowrider
CONST_FLOAT misc_camLR_true_modX 615.7372        
CONST_FLOAT misc_camLR_true_modY -77.2556
CONST_FLOAT misc_camLR_true_modZ 998.6337

// normal car
CONST_FLOAT misc_camNC_true_modX 613.5844       
CONST_FLOAT misc_camNC_true_modY -0.7127
CONST_FLOAT misc_camNC_true_modZ 1002.0314

VAR_FLOAT misc_cam_modX misc_cam_modY misc_cam_modZ // used for hydralics for the lowrider
misc_cam_modX = 0.0
misc_cam_modY = 0.0 
misc_cam_modZ = 0.0

CONST_FLOAT misc_cam_true_look_at_modX 612.0599
CONST_FLOAT misc_cam_true_look_at_modY -121.7974
CONST_FLOAT misc_cam_true_look_at_modZ 998.6927

// lowrider
CONST_FLOAT misc_camLR_true_look_at_modX 616.2341   
CONST_FLOAT misc_camLR_true_look_at_modY -76.4031
CONST_FLOAT misc_camLR_true_look_at_modZ 998.4717

// normal car
CONST_FLOAT misc_camNC_true_look_at_modX 614.5616     
CONST_FLOAT misc_camNC_true_look_at_modY -0.8400
CONST_FLOAT misc_camNC_true_look_at_modZ 1001.8622

VAR_FLOAT misc_cam_look_at_modX misc_cam_look_at_modY misc_cam_look_at_modZ
misc_cam_look_at_modX = 0.0
misc_cam_look_at_modY = 0.0
misc_cam_look_at_modZ = 0.0

// Offset for garage
VAR_FLOAT mod_garage_offsetX mod_garage_offsetY mod_garage_offsetZ
mod_garage_offsetX = 0.0
mod_garage_offsetY = 0.0
mod_garage_offsetZ = 0.0

// camera in script the each seperate camera is loaded into
VAR_FLOAT mod_swap_camX mod_swap_camY mod_swap_camZ
mod_swap_camX = 0.0
mod_swap_camY = 0.0
mod_swap_camZ = 0.0

VAR_FLOAT	mod_swap_look_at_camX mod_swap_look_at_camY mod_swap_look_at_camZ
mod_swap_look_at_camX = 0.0 
mod_swap_look_at_camY = 0.0
mod_swap_look_at_camZ =	0.0

mod_flag = 0

VAR_INT no_of_mods_for_vehicle
no_of_mods_for_vehicle = 0
			

VAR_INT new_mod_index_wil
VAR_INT upgradetype_mod
VAR_INT wing_mod_counter roof_mod_counter exhaust_mod_counter nitro_mod_counter spolier_mod_counter bonnet_mod_counter bonnetLR_mod_counter wheel_mod_counter

wheel_mod_counter = 0
wing_mod_counter = 0 
roof_mod_counter = 0 
exhaust_mod_counter = 0 
nitro_mod_counter = 0 
spolier_mod_counter = 0 
bonnet_mod_counter = 0
bonnetLR_mod_counter = 0

VAR_INT front_bumper_mod_counter rear_bumper_mod_counter lights_mod_counter front_bullbar_mod_counter rear_bullbar_mod_counter misc_mod_counter
front_bumper_mod_counter = 0 
rear_bumper_mod_counter = 0 
lights_mod_counter = 0 
front_bullbar_mod_counter = 0 
rear_bullbar_mod_counter = 0
misc_mod_counter = 0

VAR_INT paintjob_mod_counter stereo_mod_counter hydraulics_mod_counter
paintjob_mod_counter = 0
stereo_mod_counter = 0
hydraulics_mod_counter = 0

CONST_INT MAX_MOD_PER_SECTION 12

VAR_INT wing_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT roof_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT exhaust_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT nitro_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT spolier_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT bonnet_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT bonnetLR_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT front_bumper_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT rear_bumper_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT lights_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT front_bullbar_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT rear_bullbar_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT misc_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT wheel_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT stereo_mod_model_index[MAX_MOD_PER_SECTION]
VAR_INT hydraulics_mod_model_index[MAX_MOD_PER_SECTION]

VAR_INT upgradetype[MAX_MOD_PER_SECTION]

// menu stuff
VAR_INT upgrade_meun1_selected upgrade_meun2_selected
VAR_INT mod_counter
VAR_INT number_of_mods_in_section // used for the price and hilight stuff
upgrade_meun1_selected = 0
upgrade_meun2_selected = 0
mod_counter = 0

VAR_INT mod_model_index

// flags to say can you buy, can you mod, is it already on car
VAR_INT flag_cant_mod flag_car_same_colour
flag_car_same_colour = 0
flag_cant_mod = 0
flag_bought_item_already_shops = 0
flag_no_money_shops = 0

VAR_INT unk10416 unk10417
unk10416 = 0
unk10417 = 0

// stored car stuff to return car to normal
VAR_INT just_replaced_car_mod original_car_mod 

VAR_INT stored_car_colour1 stored_car_colour2

VAR_INT third_menu_shops third_menu_drawn_shops
third_menu_drawn_shops = 0

VAR_INT colour_menu_shops colour_menu_drawn_shops
colour_menu_drawn_shops = 0
main_menu_drawn_shops = 0
sub_menu_drawn_shops = 0

CONST_INT RESPRAY_COST_MOD_SHOP 150 // sets the cost of a respray in the mod shop to $500
CONST_INT PAINTJOB_COST_MODSHOP 500

VAR_FLOAT car_locate_modX car_locate_modY car_locate_modZ
car_locate_modX = 0.0
car_locate_modY = 0.0
car_locate_modZ = 0.0

VAR_INT stored_mod_garage_car

// forth menu stuff
VAR_TEXT_LABEL $forth_menu_item1 $forth_menu_item2

VAR_INT forth_menu_shops

VAR_INT forth_menu_drawn_shops upgrade_meun4_selected
forth_menu_drawn_shops = 0
upgrade_meun4_selected = 0

VAR_INT selected_car_colour_mods upgrade_colour_selected
selected_car_colour_mods = 0
upgrade_colour_selected = 0

VAR_INT car_colour1_change_mods car_colour2_change_mods
car_colour1_change_mods = 0
car_colour2_change_mods = 0

// new menu stuff for car colours
VAR_INT flag_no_of_car_colours
flag_no_of_car_colours = 0

VAR_INT car_name_mod
car_name_mod = 0

VAR_INT shop_counter number_of_paintjobs
shop_counter = 0 
number_of_paintjobs = 0

// camera stuf to tell me if the car is a lowrider or not
VAR_INT flag_car_lowrider_carmods
flag_car_lowrider_carmods = 0

// car lock status
VAR_INT car_lock_state_mod_garage
car_lock_state_mod_garage = 0

// new car check before switching on the entryexit
VAR_INT car_mod_number[16] can_car_have_mod[16]
		
VAR_INT can_car_be_modded_counter
can_car_be_modded_counter = 0

VAR_INT car_name_mod_garage

VAR_INT flag_car_normal_carmod flag_car_lowrider_carmod flag_car_racer_carmod
flag_car_normal_carmod = 0
flag_car_lowrider_carmod = 0 
flag_car_racer_carmod = 0

VAR_INT car_model_carmod1
car_model_carmod1 = 0							

SET_DEATHARREST_STATE OFF

SCRIPT_NAME	CARMOD

shop_mod_shop1_inner:

	WAIT 0

	IF IS_PLAYER_PLAYING player1

		IF disable_mod_garage = 0
         
		 	IF mod_flag > 0
				
				IF HAS_LANGUAGE_CHANGED
					CLEAR_HELP			
					DELETE_MENU main_menu_shops
					DELETE_MENU sub_menu_shops
					DELETE_MENU colour_menu_shops
					DELETE_MENU third_menu_shops
					DELETE_MENU forth_menu_shops
					main_menu_drawn_shops = 0
					sub_menu_drawn_shops = 0
					colour_menu_drawn_shops = 0
					third_menu_drawn_shops = 0
					forth_menu_drawn_shops = 0
					GET_CURRENT_LANGUAGE current_Language
				ENDIF
				
			ENDIF 
		 
			IF IS_CHAR_IN_ANY_CAR scplayer
				STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_mod_garage_car

				IF mod_flag = 0

					// LA Normal Garage
					IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer LS_NR_GARAGEX LS_NR_GARAGEY LS_NR_GARAGEZ 4.0 4.0 2.0 TRUE // -2720.5950 217.1895 3.4922 4.0 4.0 4.0 TRUE
					OR LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer SF_NR_GARAGEX SF_NR_GARAGEY SF_NR_GARAGEZ 4.0 4.0 2.0 TRUE
					OR LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer LV_NR_GARAGEX LV_NR_GARAGEY LV_NR_GARAGEZ 4.0 4.0 2.0 TRUE

						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_mod_garage_car
		
						IF IS_CHAR_SITTING_IN_CAR scplayer stored_mod_garage_car

							IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
							AND NOT IS_CHAR_IN_ANY_BOAT scplayer
							AND NOT IS_BIG_VEHICLE stored_mod_garage_car
							AND NOT IS_EMERGENCY_SERVICES_VEHICLE stored_mod_garage_car

								GOSUB can_car_be_modded_carmod1

								IF NOT can_car_be_modded_counter = 0
									 
								   	IF NOT IS_CAR_STREET_RACER stored_mod_garage_car
									AND NOT IS_CAR_LOW_RIDER stored_mod_garage_car

										IF control_flag_mod = 0
										
											SET_PLAYER_CONTROL player1 OFF
											SET_MINIGAME_IN_PROGRESS TRUE
											 
											SET_RADIO_TO_PLAYERS_FAVOURITE_STATION
																			
											GET_CAR_DOOR_LOCK_STATUS stored_mod_garage_car car_lock_state_mod_garage

											LOCK_CAR_DOORS stored_mod_garage_car CARLOCK_LOCKED_PLAYER_INSIDE

											DO_FADE 1000 FADE_OUT

											control_flag_mod = 1

										ENDIF
										
										IF control_flag_mod = 1
										
											IF NOT GET_FADING_STATUS
												SET_AREA_NAME CARMOD1
												control_flag_mod = 2
											ENDIF
											
										ENDIF
									
										IF control_flag_mod = 2
										
											IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer LS_NR_GARAGEX LS_NR_GARAGEY LS_NR_GARAGEZ 4.0 4.0 2.0 FALSE
												car_locate_modX = LS_NR_GARAGEX // used for the cars postion in the wait to leave bit at the end  
												car_locate_modY = LS_NR_GARAGEY  
												car_locate_modZ = LS_NR_GARAGEZ
											ELSE
												
												IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer SF_NR_GARAGEX SF_NR_GARAGEY SF_NR_GARAGEZ 4.0 4.0 2.0 FALSE
													car_locate_modX = SF_NR_GARAGEX // used for the cars postion in the wait to leave bit at the end  
													car_locate_modY = SF_NR_GARAGEY  
													car_locate_modZ = SF_NR_GARAGEZ
												ELSE

													IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer LV_NR_GARAGEX LV_NR_GARAGEY LV_NR_GARAGEZ 4.0 4.0 2.0 FALSE
														car_locate_modX = LV_NR_GARAGEX // used for the cars postion in the wait to leave bit at the end  
														car_locate_modY = LV_NR_GARAGEY  
														car_locate_modZ = LV_NR_GARAGEZ
													ENDIF

												ENDIF

											ENDIF

											mod_garage_offsetX = 0.0 // 3.847   // normal garage offset
											mod_garage_offsetY = 0.0 // 121.003
											mod_garage_offsetZ = 0.0 // 3.947


											SET_AREA_VISIBLE 1
											SET_CHAR_AREA_VISIBLE scplayer 1
											SET_VEHICLE_AREA_VISIBLE stored_mod_garage_car 1

											LOAD_SHOP carmod1
											LOAD_PRICES CarMods

											REQUEST_COLLISION 617.536 -1.99
											LOAD_SCENE 617.536 -1.99 999.98  
											SET_CAR_COORDINATES stored_mod_garage_car 617.536 -1.99 999.98
											SET_CAR_HEADING stored_mod_garage_car 90.0

											flag_car_normal_carmod = 1			   

																												
											flag_car_lowrider_carmods = 0
																		
											flag_cant_mod = 0
											control_flag_mod = 0 

											mod_flag = 1

										ENDIF

									ELSE

										IF flag_cant_mod = 0
											PRINT_NOW (MODNO) 5000 1 //"U cant mod this!"
											flag_cant_mod = 1
											mod_flag = 0
										ENDIF

							   		ENDIF
									 									
							  	ELSE

									IF flag_cant_mod = 0
										PRINT_NOW (MODNO2) 5000 1 //"U cant mod this!"
										flag_cant_mod = 1
										mod_flag = 0
									ENDIF

							   	ENDIF

							ELSE

								IF flag_cant_mod = 0
									PRINT_NOW (MODNO2) 5000 1 //"U cant mod this!"
									flag_cant_mod = 1
									mod_flag = 0
								ENDIF	
						
							ENDIF

						ELSE
							SWITCH_ENTRY_EXIT carmod1 FALSE
							flag_cant_mod = 0
						ENDIF

					ELSE
						SWITCH_ENTRY_EXIT carmod1 FALSE
						flag_cant_mod = 0	
					ENDIF // END OF LA NORMAL GARAGE
					
					// LA LOWRIDER GARAGE UNDER BRIDGE
					IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer LS_LR_GARAGEX LS_LR_GARAGEY LS_LR_GARAGEZ 4.0 4.0 4.0 TRUE // -2720.5950 217.1895 3.4922 4.0 4.0 4.0 TRUE

						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_mod_garage_car

						IF IS_CHAR_SITTING_IN_CAR scplayer stored_mod_garage_car

							IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
							AND NOT IS_CHAR_IN_ANY_BOAT scplayer
							AND NOT IS_BIG_VEHICLE stored_mod_garage_car
							AND NOT IS_EMERGENCY_SERVICES_VEHICLE stored_mod_garage_car

								GOSUB can_car_be_modded_carmod1

								IF NOT can_car_be_modded_counter = 0

							   		IF IS_CAR_LOW_RIDER stored_mod_garage_car

										IF control_flag_mod = 0

											SET_PLAYER_CONTROL player1 OFF
											SET_MINIGAME_IN_PROGRESS TRUE
											
											SET_RADIO_TO_PLAYERS_FAVOURITE_STATION										
										   
											GET_CAR_DOOR_LOCK_STATUS stored_mod_garage_car car_lock_state_mod_garage

											LOCK_CAR_DOORS stored_mod_garage_car CARLOCK_LOCKED_PLAYER_INSIDE

											GET_CAR_MODEL stored_mod_garage_car car_name_mod_garage

											DO_FADE 1000 FADE_OUT

											control_flag_mod = 1

										ENDIF

										IF control_flag_mod = 1
										
											IF NOT GET_FADING_STATUS
												SET_AREA_NAME CARMOD2
												control_flag_mod = 2
											ENDIF
											
										ENDIF
										
										IF control_flag_mod = 2  

											flag_car_lowrider_carmods = 1

											car_locate_modX = LS_LR_GARAGEX // used for the cars postion in the wait to leave bit at the end  
											car_locate_modY = LS_LR_GARAGEY  
											car_locate_modZ = LS_LR_GARAGEZ

											SET_AREA_VISIBLE 2
											SET_CHAR_AREA_VISIBLE scplayer 2
											SET_VEHICLE_AREA_VISIBLE stored_mod_garage_car 2

											LOAD_SHOP carmod2
											LOAD_PRICES CarMods

											REQUEST_COLLISION 616.783 -74.815
											LOAD_SCENE 616.783 -74.815 997.014 
											SET_CAR_COORDINATES stored_mod_garage_car 616.783 -74.815 997.014
											SET_CAR_HEADING stored_mod_garage_car 90.0

											flag_car_lowrider_carmod = 1 			

											mod_garage_offsetX = 0.0
											mod_garage_offsetY = 0.0
											mod_garage_offsetZ = 0.0
																	
											flag_cant_mod = 0
											control_flag_mod = 0 

											mod_flag = 1

										ENDIF

									ELSE

										IF flag_cant_mod = 0
											PRINT_NOW (MODNO) 5000 1 //"U cant mod this!"
											flag_cant_mod = 1
											mod_flag = 0
										ENDIF

									ENDIF
									 									
							  	ELSE

									IF flag_cant_mod = 0
										PRINT_NOW (MODNO2) 5000 1 //"U cant mod this!"
										flag_cant_mod = 1
										mod_flag = 0
									ENDIF

							   	ENDIF

							ELSE

								IF flag_cant_mod = 0
									PRINT_NOW (MODNO2) 5000 1 //"U cant mod this!"
									mod_flag = 0
								ENDIF	
						
							ENDIF

						ELSE
							SWITCH_ENTRY_EXIT carmod2 FALSE
							flag_cant_mod = 0
						ENDIF

					ELSE
						SWITCH_ENTRY_EXIT carmod2 FALSE
						flag_cant_mod = 0	
					ENDIF // END OF LA LOWRIDER GARAGE
				
					// SAN FRAN STREET RACER
					IF LOCATE_STOPPED_CHAR_IN_CAR_3D scplayer SF_SR_GARAGEX SF_SR_GARAGEY SF_SR_GARAGEZ 4.0 4.0 1.0 TRUE // -2720.5950 217.1895 3.4922 4.0 4.0 4.0 TRUE

						STORE_CAR_CHAR_IS_IN_NO_SAVE scplayer stored_mod_garage_car

						IF IS_CHAR_SITTING_IN_CAR scplayer stored_mod_garage_car

							IF NOT IS_CHAR_IN_FLYING_VEHICLE scplayer
							AND NOT IS_CHAR_IN_ANY_BOAT scplayer
							AND NOT IS_BIG_VEHICLE stored_mod_garage_car
							AND NOT IS_EMERGENCY_SERVICES_VEHICLE stored_mod_garage_car

								GOSUB can_car_be_modded_carmod1

								IF NOT can_car_be_modded_counter = 0

							   		IF IS_CAR_STREET_RACER stored_mod_garage_car

										IF control_flag_mod = 0

											SET_PLAYER_CONTROL player1 OFF
											SET_MINIGAME_IN_PROGRESS TRUE

											

											SET_RADIO_TO_PLAYERS_FAVOURITE_STATION
																											
											GET_CAR_DOOR_LOCK_STATUS stored_mod_garage_car car_lock_state_mod_garage 

											LOCK_CAR_DOORS stored_mod_garage_car CARLOCK_LOCKED_PLAYER_INSIDE
											  																
											DO_FADE 1000 FADE_OUT

											control_flag_mod = 1

										ENDIF

										IF control_flag_mod = 1
										
											IF NOT GET_FADING_STATUS
												SET_AREA_NAME CARMOD3
												control_flag_mod = 2
											ENDIF
											
										ENDIF

										IF control_flag_mod = 2

											mod_garage_offsetX = 0.0  // street racer offset
											mod_garage_offsetY = 0.0
											mod_garage_offsetZ = 0.0

											car_locate_modX = SF_SR_GARAGEX // used for the cars postion in the wait to leave bit at the end  
											car_locate_modY = SF_SR_GARAGEY  
											car_locate_modZ = SF_SR_GARAGEZ
											 							
											SET_AREA_VISIBLE 3
											SET_CHAR_AREA_VISIBLE scplayer 3
											SET_VEHICLE_AREA_VISIBLE stored_mod_garage_car 3

											flag_car_lowrider_carmods = 0
											flag_car_racer_carmod = 1					

											LOAD_SHOP carmod3
											LOAD_PRICES CarMods

											REQUEST_COLLISION 615.286 -124.239
											LOAD_SCENE 615.286 -124.239 996.995 
											SET_CAR_COORDINATES stored_mod_garage_car 615.286 -124.239 996.995
											SET_CAR_HEADING stored_mod_garage_car 90.0
											
											flag_cant_mod = 0
											control_flag_mod = 0 

											mod_flag = 1

										ENDIF

									ELSE

										IF flag_cant_mod = 0
											PRINT_NOW (MODNO) 5000 1 //"U cant mod this!"
											flag_cant_mod = 1
											mod_flag = 0
										ENDIF

									ENDIF
									 									
							  	ELSE

									IF flag_cant_mod = 0
										PRINT_NOW (MODNO2) 5000 1 //"U cant mod this!"
										flag_cant_mod = 1
										mod_flag = 0
									ENDIF

							   	ENDIF

							ELSE

								IF flag_cant_mod = 0
									PRINT_NOW (MODNO2) 5000 1 //"U cant mod this!"
									flag_cant_mod = 1
									mod_flag = 0
								ENDIF	
						
							ENDIF

						ELSE
							SWITCH_ENTRY_EXIT carmod3 FALSE
							flag_cant_mod = 0
						ENDIF

					ELSE
						SWITCH_ENTRY_EXIT carmod3 FALSE
						flag_cant_mod = 0	
					ENDIF // END OF STREET RACER GARAGE

				ENDIF	// END OF MOD_FLAG = 0

				// WAITING FOR THE SHOP TO LOAD
				IF mod_flag = 1

	//				SET_MINIGAME_IN_PROGRESS TRUE

					GET_LOADED_SHOP $shop_name

					SET_EXTRA_COLOURS 4 FALSE

					IF IS_CAR_STREET_RACER stored_mod_garage_car

						cam_general_modX = cam_general_true_modX + mod_garage_offsetX 
						cam_general_modY = cam_general_true_modY + mod_garage_offsetY
						cam_general_modZ = cam_general_true_modZ + mod_garage_offsetZ

						cam_general_look_at_modX = cam_general_look_at_true_modX + mod_garage_offsetX
						cam_general_look_at_modY = cam_general_look_at_true_modY + mod_garage_offsetY
						cam_general_look_at_modZ = cam_general_look_at_true_modZ + mod_garage_offsetZ

						wheel_cam_modX = wheel_cam_true_modX + mod_garage_offsetX
						wheel_cam_modY = wheel_cam_true_modY + mod_garage_offsetY
						wheel_cam_modZ = wheel_cam_true_modZ + mod_garage_offsetZ

						wheel_cam_look_at_modX = wheel_cam_true_look_at_modX + mod_garage_offsetX
						wheel_cam_look_at_modY = wheel_cam_true_look_at_modY + mod_garage_offsetY
						wheel_cam_look_at_modZ = wheel_cam_true_look_at_modZ + mod_garage_offsetZ
						
						spoiler_cam_modX = spoiler_cam_true_modX + mod_garage_offsetX
						spoiler_cam_modY = spoiler_cam_true_modY + mod_garage_offsetY
						spoiler_cam_modZ = spoiler_cam_true_modZ + mod_garage_offsetZ
										
						spoiler_cam_look_at_modX = spoiler_cam_true_look_at_modX + mod_garage_offsetX
						spoiler_cam_look_at_modY = spoiler_cam_true_look_at_modY + mod_garage_offsetY
						spoiler_cam_look_at_modZ = spoiler_cam_true_look_at_modZ + mod_garage_offsetZ

						wing_cam_modX = wing_cam_true_modX + mod_garage_offsetX
						wing_cam_modY = wing_cam_true_modY + mod_garage_offsetY
						wing_cam_modZ = wing_cam_true_modZ + mod_garage_offsetZ
						
						wing_cam_look_at_modX = wing_cam_true_look_at_modX + mod_garage_offsetX
						wing_cam_look_at_modY = wing_cam_true_look_at_modY + mod_garage_offsetY
						wing_cam_look_at_modZ = wing_cam_true_look_at_modZ + mod_garage_offsetZ

						roof_cam_modX = roof_cam_true_modX + mod_garage_offsetX
						roof_cam_modY = roof_cam_true_modY + mod_garage_offsetY
						roof_cam_modZ = roof_cam_true_modZ + mod_garage_offsetZ
						
						roof_cam_look_at_modX = roof_cam_true_look_at_modX + mod_garage_offsetX
						roof_cam_look_at_modY = roof_cam_true_look_at_modY + mod_garage_offsetY
						roof_cam_look_at_modZ = roof_cam_true_look_at_modZ + mod_garage_offsetZ

						bonnet_cam_modX = bonnet_cam_true_modX + mod_garage_offsetX
						bonnet_cam_modY = bonnet_cam_true_modY + mod_garage_offsetY
						bonnet_cam_modZ = bonnet_cam_true_modZ + mod_garage_offsetZ

						bonnet_cam_look_at_modX = bonnet_cam_true_look_at_modX + mod_garage_offsetX
						bonnet_cam_look_at_modY = bonnet_cam_true_look_at_modY + mod_garage_offsetY
						bonnet_cam_look_at_modZ = bonnet_cam_true_look_at_modZ + mod_garage_offsetZ

						exhaust_cam_modX = exhaust_cam_true_modX + mod_garage_offsetX 
						exhaust_cam_modY = exhaust_cam_true_modY + mod_garage_offsetY
						exhaust_cam_modZ = exhaust_cam_true_modZ + mod_garage_offsetZ

						exhaust_look_at_cam_modX = exhaust_look_at_true_modX + mod_garage_offsetX
						exhaust_look_at_cam_modY = exhaust_look_at_true_modY + mod_garage_offsetY
						exhaust_look_at_cam_modZ = exhaust_look_at_true_modZ + mod_garage_offsetZ

						front_fender_cam_modX = front_fender_cam_true_modX + mod_garage_offsetX
						front_fender_cam_modY = front_fender_cam_true_modY + mod_garage_offsetY
						front_fender_cam_modZ = front_fender_cam_true_modZ + mod_garage_offsetZ

						front_fender_look_at_cam_modX = front_fender_look_at_true_modX + mod_garage_offsetX
						front_fender_look_at_cam_modY = front_fender_look_at_true_modY + mod_garage_offsetY
						front_fender_look_at_cam_modZ = front_fender_look_at_true_modZ + mod_garage_offsetZ
						
						rear_fender_cam_modX = rear_fender_cam_true_modX + mod_garage_offsetX
						rear_fender_cam_modY = rear_fender_cam_true_modY + mod_garage_offsetY
						rear_fender_cam_modZ = rear_fender_cam_true_modZ + mod_garage_offsetZ 

						rear_fender_look_at_cam_modX = rear_fender_look_at_true_modX + mod_garage_offsetX
						rear_fender_look_at_cam_modY = rear_fender_look_at_true_modY + mod_garage_offsetY
						rear_fender_look_at_cam_modZ = rear_fender_look_at_true_modZ + mod_garage_offsetZ 

						lights_cam_modX = lights_cam_trueX + mod_garage_offsetX
						lights_cam_modY = lights_cam_trueY + mod_garage_offsetY  
						lights_cam_modZ = lights_cam_trueZ + mod_garage_offsetZ

						lights_look_at_cam_modX = lights_look_at_cam_true_modX + mod_garage_offsetX 
						lights_look_at_cam_modY = lights_look_at_cam_true_modY + mod_garage_offsetY
						lights_look_at_cam_modZ = lights_look_at_cam_true_modZ + mod_garage_offsetZ

						front_bullbar_cam_modX = front_bullbar_cam_true_modX + mod_garage_offsetX
						front_bullbar_cam_modY = front_bullbar_cam_true_modY + mod_garage_offsetY
						front_bullbar_cam_modZ = front_bullbar_cam_true_modZ + mod_garage_offsetZ 

						front_bullbar_look_at_cam_modX = front_bullbar_look_at_true_modX + mod_garage_offsetX
						front_bullbar_look_at_cam_modY = front_bullbar_look_at_true_modY + mod_garage_offsetY
						front_bullbar_look_at_cam_modZ = front_bullbar_look_at_true_modZ + mod_garage_offsetZ 

						rear_bullbar_cam_modX = rear_bullbar_cam_true_modX + mod_garage_offsetX
						rear_bullbar_cam_modY = rear_bullbar_cam_true_modY + mod_garage_offsetY
						rear_bullbar_cam_modZ = rear_bullbar_cam_true_modZ + mod_garage_offsetZ 
						
						rear_bullbar_look_at_cam_modX = rear_bullbar_look_at_true_modX + mod_garage_offsetX
						rear_bullbar_look_at_cam_modY = rear_bullbar_look_at_true_modY + mod_garage_offsetY
						rear_bullbar_look_at_cam_modZ = rear_bullbar_look_at_true_modZ + mod_garage_offsetZ

						nitro_cam_modX = nitro_cam_true_modX + mod_garage_offsetX 
						nitro_cam_modY = nitro_cam_true_modY + mod_garage_offsetY
						nitro_cam_modZ = nitro_cam_true_modZ + mod_garage_offsetZ

						nitro_look_at_modX = nitro_look_at_true_modX + mod_garage_offsetX
						nitro_look_at_modY = nitro_look_at_true_modY + mod_garage_offsetY
						nitro_look_at_modZ = nitro_look_at_true_modZ + mod_garage_offsetZ

						custom_paint_cam_modX = custom_paint_cam_true_modX + mod_garage_offsetX 
						custom_paint_cam_modY = custom_paint_cam_true_modY + mod_garage_offsetY
						custom_paint_cam_modZ = custom_paint_cam_true_modZ + mod_garage_offsetZ

						custom_paint_cam_look_at_modX = custom_paint_cam_look_at_true_modX + mod_garage_offsetX 
						custom_paint_cam_look_at_modY = custom_paint_cam_look_at_true_modY + mod_garage_offsetY
						custom_paint_cam_look_at_modZ = custom_paint_cam_look_at_true_modZ + mod_garage_offsetZ
						
						misc_cam_modX = misc_cam_true_modX + mod_garage_offsetX
						misc_cam_modY = misc_cam_true_modY + mod_garage_offsetY
						misc_cam_modZ = misc_cam_true_modZ + mod_garage_offsetZ

						misc_cam_look_at_modX = misc_cam_true_look_at_modX + mod_garage_offsetX
						misc_cam_look_at_modY = misc_cam_true_look_at_modY + mod_garage_offsetY
						misc_cam_look_at_modZ = misc_cam_true_look_at_modZ + mod_garage_offsetZ

					ELSE

						IF IS_CAR_LOW_RIDER stored_mod_garage_car 

							cam_general_modX = camLR_general_true_modX + mod_garage_offsetX 
							cam_general_modY = camLR_general_true_modY + mod_garage_offsetY
							cam_general_modZ = camLR_general_true_modZ + mod_garage_offsetZ

							cam_general_look_at_modX = camLR_general_look_at_true_modX + mod_garage_offsetX
							cam_general_look_at_modY = camLR_general_look_at_true_modY + mod_garage_offsetY
							cam_general_look_at_modZ = camLR_general_look_at_true_modZ + mod_garage_offsetZ

							wheel_cam_modX = wheel_camLR_true_modX + mod_garage_offsetX
							wheel_cam_modY = wheel_camLR_true_modY + mod_garage_offsetY
							wheel_cam_modZ = wheel_camLR_true_modZ + mod_garage_offsetZ

							wheel_cam_look_at_modX = wheel_camLR_true_look_at_modX + mod_garage_offsetX
							wheel_cam_look_at_modY = wheel_camLR_true_look_at_modY + mod_garage_offsetY
							wheel_cam_look_at_modZ = wheel_camLR_true_look_at_modZ + mod_garage_offsetZ
							
							spoiler_cam_modX = spoiler_camLR_true_modX + mod_garage_offsetX
							spoiler_cam_modY = spoiler_camLR_true_modY + mod_garage_offsetY
							spoiler_cam_modZ = spoiler_camLR_true_modZ + mod_garage_offsetZ
											
							spoiler_cam_look_at_modX = spoiler_camLR_true_look_at_modX + mod_garage_offsetX
							spoiler_cam_look_at_modY = spoiler_camLR_true_look_at_modY + mod_garage_offsetY
							spoiler_cam_look_at_modZ = spoiler_camLR_true_look_at_modZ + mod_garage_offsetZ

							wing_cam_modX = wing_camLR_true_modX + mod_garage_offsetX
							wing_cam_modY = wing_camLR_true_modY + mod_garage_offsetY
							wing_cam_modZ = wing_camLR_true_modZ + mod_garage_offsetZ
							
							wing_cam_look_at_modX = wing_camLR_true_look_at_modX + mod_garage_offsetX
							wing_cam_look_at_modY = wing_camLR_true_look_at_modY + mod_garage_offsetY
							wing_cam_look_at_modZ = wing_camLR_true_look_at_modZ + mod_garage_offsetZ

							roof_cam_modX = roof_camLR_true_modX + mod_garage_offsetX
							roof_cam_modY = roof_camLR_true_modY + mod_garage_offsetY
							roof_cam_modZ = roof_camLR_true_modZ + mod_garage_offsetZ
							
							roof_cam_look_at_modX = roof_camLR_true_look_at_modX + mod_garage_offsetX
							roof_cam_look_at_modY = roof_camLR_true_look_at_modY + mod_garage_offsetY
							roof_cam_look_at_modZ = roof_camLR_true_look_at_modZ + mod_garage_offsetZ

							bonnet_cam_modX = bonnet_camLR_true_modX + mod_garage_offsetX
							bonnet_cam_modY = bonnet_camLR_true_modY + mod_garage_offsetY
							bonnet_cam_modZ = bonnet_camLR_true_modZ + mod_garage_offsetZ

							bonnet_cam_look_at_modX = bonnet_camLR_true_look_at_modX + mod_garage_offsetX
							bonnet_cam_look_at_modY = bonnet_camLR_true_look_at_modY + mod_garage_offsetY
							bonnet_cam_look_at_modZ = bonnet_camLR_true_look_at_modZ + mod_garage_offsetZ

							exhaust_cam_modX = exhaust_camLR_true_modX + mod_garage_offsetX 
							exhaust_cam_modY = exhaust_camLR_true_modY + mod_garage_offsetY
							exhaust_cam_modZ = exhaust_camLR_true_modZ + mod_garage_offsetZ

							exhaust_look_at_cam_modX = exhaust_look_atLR_true_modX + mod_garage_offsetX
							exhaust_look_at_cam_modY = exhaust_look_atLR_true_modY + mod_garage_offsetY
							exhaust_look_at_cam_modZ = exhaust_look_atLR_true_modZ + mod_garage_offsetZ

							front_fender_cam_modX = front_fender_camLR_true_modX + mod_garage_offsetX
							front_fender_cam_modY = front_fender_camLR_true_modY + mod_garage_offsetY
							front_fender_cam_modZ = front_fender_camLR_true_modZ + mod_garage_offsetZ

							front_fender_look_at_cam_modX = front_fender_look_atLR_true_modX + mod_garage_offsetX
							front_fender_look_at_cam_modY = front_fender_look_atLR_true_modY + mod_garage_offsetY
							front_fender_look_at_cam_modZ = front_fender_look_atLR_true_modZ + mod_garage_offsetZ
							
							rear_fender_cam_modX = rear_fender_camLR_true_modX + mod_garage_offsetX
							rear_fender_cam_modY = rear_fender_camLR_true_modY + mod_garage_offsetY
							rear_fender_cam_modZ = rear_fender_camLR_true_modZ + mod_garage_offsetZ 

							rear_fender_look_at_cam_modX = rear_fender_look_atLR_true_modX + mod_garage_offsetX
							rear_fender_look_at_cam_modY = rear_fender_look_atLR_true_modY + mod_garage_offsetY
							rear_fender_look_at_cam_modZ = rear_fender_look_atLR_true_modZ + mod_garage_offsetZ 

							lights_cam_modX = lights_camLR_trueX + mod_garage_offsetX
							lights_cam_modY = lights_camLR_trueY + mod_garage_offsetY  
							lights_cam_modZ = lights_camLR_trueZ + mod_garage_offsetZ

							lights_look_at_cam_modX = lights_look_atLR_cam_true_modX + mod_garage_offsetX 
							lights_look_at_cam_modY = lights_look_atLR_cam_true_modY + mod_garage_offsetY
							lights_look_at_cam_modZ = lights_look_atLR_cam_true_modZ + mod_garage_offsetZ

							front_bullbar_cam_modX = front_bullbar_camLR_true_modX + mod_garage_offsetX
							front_bullbar_cam_modY = front_bullbar_camLR_true_modY + mod_garage_offsetY
							front_bullbar_cam_modZ = front_bullbar_camLR_true_modZ + mod_garage_offsetZ 

							front_bullbar_look_at_cam_modX = front_bullbar_look_atLR_true_modX + mod_garage_offsetX
							front_bullbar_look_at_cam_modY = front_bullbar_look_atLR_true_modY + mod_garage_offsetY
							front_bullbar_look_at_cam_modZ = front_bullbar_look_atLR_true_modZ + mod_garage_offsetZ 

							rear_bullbar_cam_modX = rear_bullbar_camLR_true_modX + mod_garage_offsetX
							rear_bullbar_cam_modY = rear_bullbar_camLR_true_modY + mod_garage_offsetY
							rear_bullbar_cam_modZ = rear_bullbar_camLR_true_modZ + mod_garage_offsetZ 
							
							rear_bullbar_look_at_cam_modX = rear_bullbar_look_atLR_true_modX + mod_garage_offsetX
							rear_bullbar_look_at_cam_modY = rear_bullbar_look_atLR_true_modY + mod_garage_offsetY
							rear_bullbar_look_at_cam_modZ = rear_bullbar_look_atLR_true_modZ + mod_garage_offsetZ

							nitro_cam_modX = nitro_camLR_true_modX + mod_garage_offsetX 
							nitro_cam_modY = nitro_camLR_true_modY + mod_garage_offsetY
							nitro_cam_modZ = nitro_camLR_true_modZ + mod_garage_offsetZ

							nitro_look_at_modX = nitro_look_atLR_true_modX + mod_garage_offsetX
							nitro_look_at_modY = nitro_look_atLR_true_modY + mod_garage_offsetY
							nitro_look_at_modZ = nitro_look_atLR_true_modZ + mod_garage_offsetZ

							custom_paint_cam_modX = custom_paint_camLR_true_modX + mod_garage_offsetX 
							custom_paint_cam_modY = custom_paint_camLR_true_modY + mod_garage_offsetY
							custom_paint_cam_modZ = custom_paint_camLR_true_modZ + mod_garage_offsetZ

							custom_paint_cam_look_at_modX = custom_paint_camLR_look_at_true_modX + mod_garage_offsetX 
							custom_paint_cam_look_at_modY = custom_paint_camLR_look_at_true_modY + mod_garage_offsetY
							custom_paint_cam_look_at_modZ = custom_paint_camLR_look_at_true_modZ + mod_garage_offsetZ
					
							IF car_name_mod_garage = REMINGTN // remmington misc is for the front of the car
							
								misc_cam_modX = front_fender_camLR_true_modX  + mod_garage_offsetX 
								misc_cam_modY = front_fender_camLR_true_modY + mod_garage_offsetY
								misc_cam_modZ = front_fender_camLR_true_modZ + mod_garage_offsetZ

								misc_cam_look_at_modX = front_fender_look_atLR_true_modX + mod_garage_offsetX
								misc_cam_look_at_modY = front_fender_look_atLR_true_modY + mod_garage_offsetY
								misc_cam_look_at_modZ = front_fender_look_atLR_true_modZ + mod_garage_offsetZ

							ELSE

								misc_cam_modX = misc_camLR_true_modX + mod_garage_offsetX
								misc_cam_modY = misc_camLR_true_modY + mod_garage_offsetY
								misc_cam_modZ = misc_camLR_true_modZ + mod_garage_offsetZ

								misc_cam_look_at_modX = misc_camLR_true_look_at_modX + mod_garage_offsetX
								misc_cam_look_at_modY = misc_camLR_true_look_at_modY + mod_garage_offsetY
								misc_cam_look_at_modZ = misc_camLR_true_look_at_modZ + mod_garage_offsetZ

							ENDIF

						ELSE
						
							cam_general_modX = camNC_general_true_modX + mod_garage_offsetX 
							cam_general_modY = camNC_general_true_modY + mod_garage_offsetY
							cam_general_modZ = camNC_general_true_modZ + mod_garage_offsetZ

							cam_general_look_at_modX = camNC_general_look_at_true_modX + mod_garage_offsetX
							cam_general_look_at_modY = camNC_general_look_at_true_modY + mod_garage_offsetY
							cam_general_look_at_modZ = camNC_general_look_at_true_modZ + mod_garage_offsetZ

							wheel_cam_modX = wheel_camNC_true_modX + mod_garage_offsetX
							wheel_cam_modY = wheel_camNC_true_modY + mod_garage_offsetY
							wheel_cam_modZ = wheel_camNC_true_modZ + mod_garage_offsetZ

							wheel_cam_look_at_modX = wheel_camNC_true_look_at_modX + mod_garage_offsetX
							wheel_cam_look_at_modY = wheel_camNC_true_look_at_modY + mod_garage_offsetY
							wheel_cam_look_at_modZ = wheel_camNC_true_look_at_modZ + mod_garage_offsetZ
							
							spoiler_cam_modX = spoiler_camNC_true_modX + mod_garage_offsetX
							spoiler_cam_modY = spoiler_camNC_true_modY + mod_garage_offsetY
							spoiler_cam_modZ = spoiler_camNC_true_modZ + mod_garage_offsetZ
											
							spoiler_cam_look_at_modX = spoiler_camNC_true_look_at_modX + mod_garage_offsetX
							spoiler_cam_look_at_modY = spoiler_camNC_true_look_at_modY + mod_garage_offsetY
							spoiler_cam_look_at_modZ = spoiler_camNC_true_look_at_modZ + mod_garage_offsetZ

							wing_cam_modX = wing_camNC_true_modX + mod_garage_offsetX
							wing_cam_modY = wing_camNC_true_modY + mod_garage_offsetY
							wing_cam_modZ = wing_camNC_true_modZ + mod_garage_offsetZ
							
							wing_cam_look_at_modX = wing_camNC_true_look_at_modX + mod_garage_offsetX
							wing_cam_look_at_modY = wing_camNC_true_look_at_modY + mod_garage_offsetY
							wing_cam_look_at_modZ = wing_camNC_true_look_at_modZ + mod_garage_offsetZ

							roof_cam_modX = roof_camNC_true_modX + mod_garage_offsetX
							roof_cam_modY = roof_camNC_true_modY + mod_garage_offsetY
							roof_cam_modZ = roof_camNC_true_modZ + mod_garage_offsetZ
						
							roof_cam_look_at_modX = roof_camNC_true_look_at_modX + mod_garage_offsetX
							roof_cam_look_at_modY = roof_camNC_true_look_at_modY + mod_garage_offsetY
							roof_cam_look_at_modZ = roof_camNC_true_look_at_modZ + mod_garage_offsetZ

							bonnet_cam_modX = bonnet_camNC_true_modX + mod_garage_offsetX
							bonnet_cam_modY = bonnet_camNC_true_modY + mod_garage_offsetY
							bonnet_cam_modZ = bonnet_camNC_true_modZ + mod_garage_offsetZ

							bonnet_cam_look_at_modX = bonnet_camNC_true_look_at_modX + mod_garage_offsetX
							bonnet_cam_look_at_modY = bonnet_camNC_true_look_at_modY + mod_garage_offsetY
							bonnet_cam_look_at_modZ = bonnet_camNC_true_look_at_modZ + mod_garage_offsetZ

							exhaust_cam_modX = exhaust_camNC_true_modX + mod_garage_offsetX 
							exhaust_cam_modY = exhaust_camNC_true_modY + mod_garage_offsetY
							exhaust_cam_modZ = exhaust_camNC_true_modZ + mod_garage_offsetZ

							exhaust_look_at_cam_modX = exhaust_look_atNC_true_modX + mod_garage_offsetX
							exhaust_look_at_cam_modY = exhaust_look_atNC_true_modY + mod_garage_offsetY
							exhaust_look_at_cam_modZ = exhaust_look_atNC_true_modZ + mod_garage_offsetZ

							front_fender_cam_modX = front_fender_camNC_true_modX + mod_garage_offsetX
							front_fender_cam_modY = front_fender_camNC_true_modY + mod_garage_offsetY
							front_fender_cam_modZ = front_fender_camNC_true_modZ + mod_garage_offsetZ

							front_fender_look_at_cam_modX = front_fender_look_atNC_true_modX + mod_garage_offsetX
							front_fender_look_at_cam_modY = front_fender_look_atNC_true_modY + mod_garage_offsetY
							front_fender_look_at_cam_modZ = front_fender_look_atNC_true_modZ + mod_garage_offsetZ
							
							rear_fender_cam_modX = rear_fender_camNC_true_modX + mod_garage_offsetX
							rear_fender_cam_modY = rear_fender_camNC_true_modY + mod_garage_offsetY
							rear_fender_cam_modZ = rear_fender_camNC_true_modZ + mod_garage_offsetZ 

							rear_fender_look_at_cam_modX = rear_fender_look_atNC_true_modX + mod_garage_offsetX
							rear_fender_look_at_cam_modY = rear_fender_look_atNC_true_modY + mod_garage_offsetY
							rear_fender_look_at_cam_modZ = rear_fender_look_atNC_true_modZ + mod_garage_offsetZ 

							lights_cam_modX = lights_camNC_trueX + mod_garage_offsetX
							lights_cam_modY = lights_camNC_trueY + mod_garage_offsetY  
							lights_cam_modZ = lights_camNC_trueZ + mod_garage_offsetZ

							lights_look_at_cam_modX = lights_look_atNC_cam_true_modX + mod_garage_offsetX 
							lights_look_at_cam_modY = lights_look_atNC_cam_true_modY + mod_garage_offsetY
							lights_look_at_cam_modZ = lights_look_atNC_cam_true_modZ + mod_garage_offsetZ

							front_bullbar_cam_modX = front_bullbar_camNC_true_modX + mod_garage_offsetX
							front_bullbar_cam_modY = front_bullbar_camNC_true_modY + mod_garage_offsetY
							front_bullbar_cam_modZ = front_bullbar_camNC_true_modZ + mod_garage_offsetZ 

							front_bullbar_look_at_cam_modX = front_bullbar_look_atNC_true_modX + mod_garage_offsetX
							front_bullbar_look_at_cam_modY = front_bullbar_look_atNC_true_modY + mod_garage_offsetY
							front_bullbar_look_at_cam_modZ = front_bullbar_look_atNC_true_modZ + mod_garage_offsetZ 

							rear_bullbar_cam_modX = rear_bullbar_camNC_true_modX + mod_garage_offsetX
							rear_bullbar_cam_modY = rear_bullbar_camNC_true_modY + mod_garage_offsetY
							rear_bullbar_cam_modZ = rear_bullbar_camNC_true_modZ + mod_garage_offsetZ 
							
							rear_bullbar_look_at_cam_modX = rear_bullbar_look_atNC_true_modX + mod_garage_offsetX
							rear_bullbar_look_at_cam_modY = rear_bullbar_look_atNC_true_modY + mod_garage_offsetY
							rear_bullbar_look_at_cam_modZ = rear_bullbar_look_atNC_true_modZ + mod_garage_offsetZ

							nitro_cam_modX = nitro_camNC_true_modX + mod_garage_offsetX 
							nitro_cam_modY = nitro_camNC_true_modY + mod_garage_offsetY
							nitro_cam_modZ = nitro_camNC_true_modZ + mod_garage_offsetZ

							nitro_look_at_modX = nitro_look_atNC_true_modX + mod_garage_offsetX
							nitro_look_at_modY = nitro_look_atNC_true_modY + mod_garage_offsetY
							nitro_look_at_modZ = nitro_look_atNC_true_modZ + mod_garage_offsetZ

							custom_paint_cam_modX = custom_paint_camNC_true_modX + mod_garage_offsetX 
							custom_paint_cam_modY = custom_paint_camNC_true_modY + mod_garage_offsetY
							custom_paint_cam_modZ = custom_paint_camNC_true_modZ + mod_garage_offsetZ

							custom_paint_cam_look_at_modX = custom_paint_camNC_look_at_true_modX + mod_garage_offsetX 
							custom_paint_cam_look_at_modY = custom_paint_camNC_look_at_true_modY + mod_garage_offsetY
							custom_paint_cam_look_at_modZ = custom_paint_camNC_look_at_true_modZ + mod_garage_offsetZ
					
							misc_cam_modX = misc_camNC_true_modX + mod_garage_offsetX
							misc_cam_modY = misc_camNC_true_modY + mod_garage_offsetY
							misc_cam_modZ = misc_camNC_true_modZ + mod_garage_offsetZ

							misc_cam_look_at_modX = misc_camNC_true_look_at_modX + mod_garage_offsetX
							misc_cam_look_at_modY = misc_camNC_true_look_at_modY + mod_garage_offsetY
							misc_cam_look_at_modZ = misc_camNC_true_look_at_modZ + mod_garage_offsetZ

						
						ENDIF
						
					ENDIF			
					   
					TIMERA = 0
					DISPLAY_RADAR FALSE
					SHOW_UPDATE_STATS FALSE
					SET_FIXED_CAMERA_POSITION cam_general_modX cam_general_modY cam_general_modZ 0.0 0.0 0.0
					POINT_CAMERA_AT_POINT cam_general_look_at_modX cam_general_look_at_modY cam_general_look_at_modZ JUMP_CUT
					control_flag_mod = 0
					mod_flag = 2			

				ENDIF
							   
				// SETS UP CAMERA AND SETS PLAYER CONTROL
				IF mod_flag = 2

					IF control_flag_mod = 0 

						SET_CAR_DENSITY_MULTIPLIER 0.0
                        GET_NUMBER_OF_ITEMS_IN_SHOP item_no_shops 
                        GET_CAR_COLOURS stored_mod_garage_car stored_car_colour1 stored_car_colour2
						GET_CURRENT_VEHICLE_PAINTJOB stored_mod_garage_car stored_paintjob_number
                        GET_CAR_MODEL stored_mod_garage_car car_model_carmod1
                        RESET_VEHICLE_HYDRAULICS stored_mod_garage_car
						STORE_CAR_MOD_STATE
						control_flag_mod = 1
					ENDIF

					IF control_flag_mod = 1 // Added to stop bug where player gets control too early
					
						IF TIMERA > 2000
							DO_FADE 1000 FADE_IN 
							control_flag_mod = 2
						ENDIF

					ENDIF

					IF control_flag_mod = 2

						IF NOT GET_FADING_STATUS
							SET_PLAYER_IS_IN_STADIUM TRUE
							control_flag_mod = 0
							mod_flag = 3
						ENDIF

					ENDIF
							
										 
				ENDIF // end mod flag = 2
				 
				// MENU1 IS DRAWN AND PLAYER CAN CHOOSE FROM IT
				IF mod_flag = 3
					
					IF control_flag_mod = 0

						IF main_menu_drawn_shops = 0
							GOSUB fill_in_text_menu1_mod
							GOSUB draw_main_menu_carmod 					
						ENDIF
						
						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

							car_colour1_change_mods = 0
							car_colour2_change_mods = 0

							GET_MENU_ITEM_SELECTED main_menu_shops upgrade_meun1_selected

							IF upgrade_meun1_selected < 0
								upgrade_meun1_selected = 0
							ENDIF

							IF NOT upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR							

								IF NOT upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB 
									GET_CURRENT_CAR_MOD stored_mod_garage_car upgradetype[upgrade_meun1_selected] original_car_mod
								ELSE
									GET_CURRENT_VEHICLE_PAINTJOB stored_mod_garage_car stored_paintjob_number 
								ENDIF

								GOSUB fill_in_text_menu2_mods

							ELSE

								mod_swap_camX = cam_general_modX
								mod_swap_camY = cam_general_modY
								mod_swap_camZ = cam_general_modZ 

								mod_swap_look_at_camX = cam_general_look_at_modX
								mod_swap_look_at_camY = cam_general_look_at_modY
								mod_swap_look_at_camZ = cam_general_look_at_modZ
  
								item_price[mod_counter] = RESPRAY_COST_MOD_SHOP

								GET_CAR_MODEL stored_mod_garage_car car_name_mod

								GOSUB find_how_many_car_colours_mod

						  	ENDIF

							control_flag_mod = 5

						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							control_flag_mod = 1
						ENDIF
					
					ENDIF

					// PLAYER HAS PRESSED TO QUIT OUT OF THE WHOLE MENU
					IF control_flag_mod = 1
					
						IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

							IF main_menu_drawn_shops = 1
								DELETE_MENU main_menu_shops
								CLEAR_HELP
								main_menu_drawn_shops = 0
							ENDIF

							SET_FADING_COLOUR 0 0 0
							DO_FADE 1000 FADE_OUT					
																			
							control_flag_mod = 2						
						ENDIF
						
					ENDIF

					IF control_flag_mod = 2

						IF NOT GET_FADING_STATUS

							SET_AREA_VISIBLE 0
							SET_CHAR_AREA_VISIBLE scplayer 0
							SET_VEHICLE_AREA_VISIBLE stored_mod_garage_car 0

							CLEAR_EXTRA_COLOURS FALSE

							RESTORE_CAR_MOD_STATE
							flag_car_lowrider_carmods = 0
							REQUEST_COLLISION car_locate_modX car_locate_modY
							LOAD_SCENE car_locate_modX car_locate_modY car_locate_modZ 
							SET_CAR_COORDINATES stored_mod_garage_car car_locate_modX car_locate_modY car_locate_modZ

							IF flag_car_normal_carmod = 1
								SET_CAR_HEADING stored_mod_garage_car 0.0
								SET_CAMERA_BEHIND_PLAYER
							ENDIF

							IF flag_car_lowrider_carmod = 1 
								SET_CAR_HEADING stored_mod_garage_car 180.0
								SET_CAMERA_BEHIND_PLAYER
							ENDIF

							IF flag_car_racer_carmod = 1
								SET_CAR_HEADING stored_mod_garage_car 90.0
								SET_CAMERA_BEHIND_PLAYER
							ENDIF

							MANAGE_ALL_POPULATION

							RESTORE_CAMERA_JUMPCUT
							
							CLEAR_LOADED_SHOP

							SET_FADING_COLOUR 0 0 0
							DO_FADE 1000 FADE_IN
							control_flag_mod = 3
							
						ENDIF
						
					ENDIF	

					IF control_flag_mod = 3

						IF NOT GET_FADING_STATUS

							SET_PLAYER_CONTROL player1 ON
							SET_MINIGAME_IN_PROGRESS FALSE
							LOCK_CAR_DOORS stored_mod_garage_car car_lock_state_mod_garage					   	 
							DISPLAY_RADAR TRUE
							SET_PLAYER_IS_IN_STADIUM FALSE

							control_flag_mod = 4

						ENDIF
						
					ENDIF 

					IF control_flag_mod = 4
			
						IF NOT LOCATE_CHAR_IN_CAR_3D scplayer car_locate_modX car_locate_modY car_locate_modZ 4.0 4.0 4.0 FALSE

							SET_CAR_DENSITY_MULTIPLIER 1.0

							flag_cant_mod = 0
							mod_flag = 0
							control_flag_mod = 0
							flag_car_normal_carmod = 0
							flag_car_lowrider_carmod = 0 
							flag_car_racer_carmod = 0

							GOSUB mod_cleanup_big

						ENDIF
						

					ENDIF
				
					IF control_flag_mod = 5
					
						IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

							IF main_menu_drawn_shops = 1
								DELETE_MENU main_menu_shops
								CLEAR_HELP
								main_menu_drawn_shops = 0
							ENDIF

							SET_FIXED_CAMERA_POSITION mod_swap_camX mod_swap_camY mod_swap_camZ 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT mod_swap_look_at_camX mod_swap_look_at_camY mod_swap_look_at_camZ JUMP_CUT 
							
							mod_flag = 4
							control_flag_mod = 0
						ENDIF

					ENDIF

				ENDIF // end mod_flag = 2

				// player is in menu two to select items
				IF mod_flag = 4

					IF IS_CAR_DEAD stored_mod_garage_car
						GOSUB mod_cleanup_big 
						GOTO shop_mod_shop1_inner
					ENDIF

				  	IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR

						IF forth_menu_drawn_shops = 0
							GOSUB draw_menu4_mod_shop
						ENDIF

				  	ELSE
					
						IF sub_menu_drawn_shops = 0
							GOSUB draw_sub_menu_mod 
						ENDIF

				 	ENDIF

					IF control_flag_mod = 0

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							flag_no_money_shops = 0
							flag_bought_item_already_shops = 0
							flag_car_same_colour = 0
							control_flag_mod = 1
						ENDIF

						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

							IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR

								GET_MENU_ITEM_SELECTED forth_menu_shops upgrade_meun4_selected

								IF upgrade_meun4_selected < 0
									upgrade_meun4_selected = 0
								ENDIF

								IF upgrade_meun4_selected = 0
									car_colour1_change_mods = 1	
								ELSE
									car_colour2_change_mods = 1
								ENDIF 
								
							ELSE
							
								GET_MENU_ITEM_SELECTED sub_menu_shops upgrade_meun2_selected

								IF upgrade_meun2_selected < 0
									upgrade_meun2_selected = 0
								ENDIF

							  	IF NOT upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB
								AND NOT upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR 
						   			mod_model_index = item_model_index[upgrade_meun2_selected]
						   			REQUEST_VEHICLE_MOD mod_model_index
						   	   	ENDIF
						   	   	
						   	ENDIF 

							flag_no_money_shops = 0
							flag_bought_item_already_shops = 0
							flag_car_same_colour = 0

							control_flag_mod = 0
							mod_flag = 5 

						ENDIF 
						
					ENDIF

					// QUIT FROM MENU 2 BACK TO MENU 1
					IF control_flag_mod = 1
					
						IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

							IF sub_menu_drawn_shops = 1
								DELETE_MENU sub_menu_shops
								CLEAR_HELP
								sub_menu_drawn_shops = 0
							ENDIF
							
							IF colour_menu_drawn_shops = 1
								DELETE_MENU colour_menu_shops
								CLEAR_HELP
								colour_menu_drawn_shops = 0
							ENDIF
							
							IF forth_menu_drawn_shops = 1
								DELETE_MENU forth_menu_shops
								CLEAR_HELP
								forth_menu_drawn_shops = 0
							ENDIF
						
							IF main_menu_drawn_shops = 0
								GOSUB fill_in_text_menu1_mod
								GOSUB draw_main_menu_carmod 					
							ENDIF

							SET_FIXED_CAMERA_POSITION cam_general_modX cam_general_modY cam_general_modZ 0.0 0.0 0.0
							POINT_CAMERA_AT_POINT cam_general_look_at_modX cam_general_look_at_modY cam_general_look_at_modZ JUMP_CUT
						
							flag_cant_mod = 0
							control_flag_mod = 0
							mod_flag = 3
						ENDIF
						
					ENDIF
															
				ENDIF // end mod_flag 4 
			   	
													
				IF mod_flag = 5

					IF IS_CAR_DEAD stored_mod_garage_car
						GOSUB mod_cleanup_big 
						GOTO shop_mod_shop1_inner
					ENDIF

					IF control_flag_mod = 0

						IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

							IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR  // Tells me either colour1 or colour2
								control_flag_mod = 1
							ELSE

							  	IF NOT upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB
								 
									IF HAS_VEHICLE_MOD_LOADED mod_model_index								 
										control_flag_mod = 1
									ENDIF
								ELSE
									control_flag_mod = 1
							   	ENDIF

							ENDIF

							IF control_flag_mod = 1
							
								IF sub_menu_drawn_shops = 1
									DELETE_MENU sub_menu_shops
									CLEAR_HELP
									sub_menu_drawn_shops = 0
								ENDIF 

							ENDIF

						ENDIF

					ENDIF

					// draws the buy menu
					IF control_flag_mod = 1
						 
					   	IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR

							IF forth_menu_drawn_shops = 1
								DELETE_MENU forth_menu_shops
								CLEAR_HELP
								forth_menu_drawn_shops = 0
							ENDIF
					   	
							IF colour_menu_drawn_shops = 0
					   			GOSUB draw_colour_menu
							ENDIF

						ELSE
							
							IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB

							 	paintjob_number = upgrade_meun2_selected 
                                paintjob_number_menu = upgrade_meun2_selected + 1
								GIVE_VEHICLE_PAINTJOB stored_mod_garage_car paintjob_number	

					  		ELSE
						 
								ADD_VEHICLE_MOD stored_mod_garage_car mod_model_index just_replaced_car_mod
						   		MARK_VEHICLE_MOD_AS_NO_LONGER_NEEDED mod_model_index
																
							ENDIF

							GOSUB draw_third_menu_shops
						ENDIF

						control_flag_mod = 2
												
					ENDIF  

					IF control_flag_mod = 2

						IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR
							
							GET_MENU_ITEM_SELECTED colour_menu_shops upgrade_colour_selected
                            

							IF upgrade_colour_selected < 0
								upgrade_colour_selected = 0
							ENDIF
							
							IF car_colour1_change_mods = 1
							
								IF car_model_carmod1 = 542
								OR car_model_carmod1 = 549
									CHANGE_CAR_COLOUR_FROM_MENU colour_menu_shops stored_mod_garage_car 1 upgrade_colour_selected
									CHANGE_CAR_COLOUR_FROM_MENU colour_menu_shops stored_mod_garage_car 2 upgrade_colour_selected
								ELSE
									CHANGE_CAR_COLOUR_FROM_MENU colour_menu_shops stored_mod_garage_car 1 upgrade_colour_selected
								ENDIF

							ELSE
								CHANGE_CAR_COLOUR_FROM_MENU colour_menu_shops stored_mod_garage_car 2 upgrade_colour_selected
							ENDIF

							IF colour_menu_drawn_shops = 0
					   			GOSUB draw_colour_menu
							ENDIF
							
						ELSE				
							
							IF third_menu_drawn_shops = 0
								GOSUB draw_third_menu_shops 
							ENDIF

						ENDIF 

						IF IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL
							control_flag_mod = 3
						ENDIF
						
						IF IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT
							
							CLEAR_THIS_PRINT (CLTHNO2)
							CLEAR_THIS_PRINT (COLORNO)
							CLEAR_THIS_PRINT (SHOPNO)

							flag_bought_item_already_shops = 0
							flag_car_same_colour = 0
							flag_no_money_shops = 0

							control_flag_mod = 4	
						ENDIF	
						 
					ENDIF

					IF control_flag_mod = 3
					
						IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_CANCEL

							IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR 
								
								IF stored_paintjob_number > -1
								   	CHANGE_CAR_COLOUR stored_mod_garage_car stored_car_colour1 stored_car_colour2
									GIVE_VEHICLE_PAINTJOB stored_mod_garage_car stored_paintjob_number
								ELSE
									CHANGE_CAR_COLOUR stored_mod_garage_car stored_car_colour1 stored_car_colour2
								ENDIF

							ELSE
							
								IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB

									IF stored_paintjob_number < 0
										GIVE_VEHICLE_PAINTJOB stored_mod_garage_car -1  // 0
										CHANGE_CAR_COLOUR stored_mod_garage_car stored_car_colour1 stored_car_colour2
									ELSE									 
										GIVE_VEHICLE_PAINTJOB stored_mod_garage_car stored_paintjob_number
									ENDIF

								ELSE
									RESTORE_CAR_MOD_STATE
							 	ENDIF

								IF third_menu_drawn_shops = 1
									DELETE_MENU third_menu_shops
									CLEAR_HELP
									third_menu_drawn_shops = 0
								ENDIF

							ENDIF

							IF colour_menu_drawn_shops = 1 
								DELETE_MENU colour_menu_shops
								CLEAR_HELP
								colour_menu_drawn_shops = 0
							ENDIF

							IF forth_menu_drawn_shops = 1
								DELETE_MENU forth_menu_shops
								CLEAR_HELP
								forth_menu_drawn_shops = 0
							ENDIF

							IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR

								IF forth_menu_drawn_shops = 0
									GOSUB draw_menu4_mod_shop
								ENDIF
								
							ELSE 

								IF sub_menu_drawn_shops = 0
									GOSUB fill_in_text_menu2_mods
									GOSUB draw_sub_menu_mod 
								ENDIF

							ENDIF

							car_colour1_change_mods = 0
							car_colour2_change_mods = 0
														
							control_flag_mod = 0 
							mod_flag = 4
						
						ENDIF
					
					ENDIF

					IF control_flag_mod = 4

						IF NOT IS_BUTTON_PRESSED PAD1 BUTTON_ACCEPT

							STORE_SCORE player1 players_money

							IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_CAR_COLOUR

								IF players_money >= RESPRAY_COST_MOD_SHOP

									// Exterior color changes.

									IF car_colour1_change_mods = 1
									    
										//IF NOT stored_car_colour1 = upgrade_colour_selected
											
											ADD_SCORE player1 -150
											INCREMENT_FLOAT_STAT CAR_MOD_BUDGET 150.0
											CLEAR_WANTED_LEVEL_IN_GARAGE
											SET_VEHICLE_DIRT_LEVEL stored_mod_garage_car 0.0 							   	 
											
											GET_CAR_COLOURS	stored_mod_garage_car stored_car_colour1 stored_car_colour2
											
											REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_BUY_CAR_RESPRAY
											FIX_CAR stored_mod_garage_car
											STORE_CAR_MOD_STATE
																	
											control_flag_mod = 0
											mod_flag = 3

											IF sub_menu_drawn_shops = 1
												DELETE_MENU sub_menu_shops
												CLEAR_HELP
												sub_menu_drawn_shops = 0
											ENDIF

											IF colour_menu_drawn_shops = 1
												DELETE_MENU colour_menu_shops
												CLEAR_HELP
												colour_menu_drawn_shops = 0
											ENDIF

											IF third_menu_drawn_shops = 1
												DELETE_MENU third_menu_shops
												CLEAR_HELP
												third_menu_drawn_shops = 0
											ENDIF

											IF forth_menu_drawn_shops = 1
												DELETE_MENU forth_menu_shops
												CLEAR_HELP
												forth_menu_drawn_shops = 0
											ENDIF

											IF main_menu_drawn_shops = 0
												GOSUB fill_in_text_menu1_mod
												GOSUB draw_main_menu_carmod 					
											ENDIF

											SET_FIXED_CAMERA_POSITION cam_general_modX cam_general_modY cam_general_modZ 0.0 0.0 0.0
											POINT_CAMERA_AT_POINT cam_general_look_at_modX cam_general_look_at_modY cam_general_look_at_modZ JUMP_CUT

										//ELSE

										//	IF flag_car_same_colour = 0 
								 		//		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
										//		PRINT_NOW (COLORNO) 3000 1 //"You're vehicle is already this colour."
										//		control_flag_mod = 2
										//		flag_car_same_colour = 1
										//	ENDIF	

										//ENDIF

									// Interior color changes.

									ELSE
										
										//IF NOT stored_car_colour2 = upgrade_colour_selected

											ADD_SCORE player1 -150
											INCREMENT_FLOAT_STAT CAR_MOD_BUDGET 150.0
											CLEAR_WANTED_LEVEL_IN_GARAGE
											SET_VEHICLE_DIRT_LEVEL stored_mod_garage_car 0.0							   	 
										   	GET_CAR_COLOURS	stored_mod_garage_car stored_car_colour1 stored_car_colour2
											REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_BUY_CAR_RESPRAY
											FIX_CAR stored_mod_garage_car
											STORE_CAR_MOD_STATE
											
											control_flag_mod = 0
											mod_flag = 3

											IF sub_menu_drawn_shops = 1
												DELETE_MENU sub_menu_shops
												CLEAR_HELP
												sub_menu_drawn_shops = 0
											ENDIF

											IF colour_menu_drawn_shops = 1
												DELETE_MENU colour_menu_shops
												CLEAR_HELP
												colour_menu_drawn_shops = 0
											ENDIF

											IF third_menu_drawn_shops = 1
												DELETE_MENU third_menu_shops
												CLEAR_HELP
												third_menu_drawn_shops = 0
											ENDIF

											IF forth_menu_drawn_shops = 1
												DELETE_MENU forth_menu_shops
												CLEAR_HELP
												forth_menu_drawn_shops = 0
											ENDIF

											IF main_menu_drawn_shops = 0
												GOSUB fill_in_text_menu1_mod
												GOSUB draw_main_menu_carmod 					
											ENDIF

											SET_FIXED_CAMERA_POSITION cam_general_modX cam_general_modY cam_general_modZ 0.0 0.0 0.0
											POINT_CAMERA_AT_POINT cam_general_look_at_modX cam_general_look_at_modY cam_general_look_at_modZ JUMP_CUT

										//ELSE

										//	IF flag_car_same_colour = 0
								 		//		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
										//		PRINT_NOW (COLORNO) 3000 1 //"You're vehicle is already this colour."
										//		control_flag_mod = 2
										//		flag_car_same_colour = 1
										//	ENDIF

										//ENDIF

									ENDIF

								ELSE

									IF flag_no_money_shops = 0 
								 		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
										PRINT_NOW (SHOPNO) 3000 1 //"You don't have enough money to buy this."
										control_flag_mod = 2
										flag_no_money_shops = 1
									ENDIF
					
								ENDIF
																
							ELSE
						
								IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB

									IF players_money >= PAINTJOB_COST_MODSHOP 
										
										IF paintjob_number = stored_paintjob_number

											IF flag_bought_item_already_shops = 0
										 		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
												PRINT_NOW (CLTHNO2) 3000 1 //"You have already bought this item!"
												control_flag_mod = 2
												flag_bought_item_already_shops = 1
											ENDIF

										ELSE
											ADD_SCORE player1 -500
											INCREMENT_FLOAT_STAT CAR_MOD_BUDGET 500.0
											CLEAR_WANTED_LEVEL_IN_GARAGE
											SET_VEHICLE_DIRT_LEVEL stored_mod_garage_car 0.0
											FIX_CAR stored_mod_garage_car
											STORE_CAR_MOD_STATE
																						  
											stored_paintjob_number = paintjob_number
											GET_CAR_COLOURS stored_mod_garage_car stored_car_colour1 stored_car_colour2 // needed so paintjob colour is not changed after you buy it 
											REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_BUY_CAR_RESPRAY

											control_flag_mod = 0
											mod_flag = 3

											IF sub_menu_drawn_shops = 1
												DELETE_MENU sub_menu_shops
												CLEAR_HELP
												sub_menu_drawn_shops = 0
											ENDIF

											IF colour_menu_drawn_shops = 1
												DELETE_MENU colour_menu_shops
												CLEAR_HELP
												colour_menu_drawn_shops = 0
											ENDIF

											IF third_menu_drawn_shops = 1
												DELETE_MENU third_menu_shops
												CLEAR_HELP
												third_menu_drawn_shops = 0
											ENDIF

											IF forth_menu_drawn_shops = 1
												DELETE_MENU forth_menu_shops
												CLEAR_HELP
												forth_menu_drawn_shops = 0
											ENDIF

											IF main_menu_drawn_shops = 0
												GOSUB fill_in_text_menu1_mod
												GOSUB draw_main_menu_carmod 					
											ENDIF

											SET_FIXED_CAMERA_POSITION cam_general_modX cam_general_modY cam_general_modZ 0.0 0.0 0.0
											POINT_CAMERA_AT_POINT cam_general_look_at_modX cam_general_look_at_modY cam_general_look_at_modZ JUMP_CUT
	 
										ENDIF

									ELSE

										IF flag_no_money_shops = 0 
									 		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
											PRINT_NOW (SHOPNO) 3000 1 //"You don't have enough money to buy this."
											control_flag_mod = 2
											flag_no_money_shops = 1
										ENDIF

									ENDIF
								
								ELSE						 

									IF players_money >= item_price[upgrade_meun2_selected]	 							
											
										IF NOT original_car_mod = mod_model_index
											REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_BUY_CAR_MOD
											BUY_ITEM mod_model_index
											SET_VEHICLE_DIRT_LEVEL stored_mod_garage_car 0.0
											STORE_CAR_MOD_STATE
											original_car_mod = mod_model_index

											IF flag_1st_time_nitro_shop = 0

												IF $item_text_label = BMBSM
												OR $item_text_label = BMBTN
												OR $item_text_label = BMBLN
													flag_1st_time_nitro_shop = 1
												ENDIF
												
											ENDIF 
											
											control_flag_mod = 0
											mod_flag = 3

											IF sub_menu_drawn_shops = 1
												DELETE_MENU sub_menu_shops
												CLEAR_HELP
												sub_menu_drawn_shops = 0
											ENDIF

											IF colour_menu_drawn_shops = 1
												DELETE_MENU colour_menu_shops
												CLEAR_HELP
												colour_menu_drawn_shops = 0
											ENDIF

											IF third_menu_drawn_shops = 1
												DELETE_MENU third_menu_shops
												CLEAR_HELP
												third_menu_drawn_shops = 0
											ENDIF

											IF forth_menu_drawn_shops = 1
												DELETE_MENU forth_menu_shops
												CLEAR_HELP
												forth_menu_drawn_shops = 0
											ENDIF

											IF main_menu_drawn_shops = 0
												GOSUB fill_in_text_menu1_mod
												GOSUB draw_main_menu_carmod 					
											ENDIF

											SET_FIXED_CAMERA_POSITION cam_general_modX cam_general_modY cam_general_modZ 0.0 0.0 0.0
											POINT_CAMERA_AT_POINT cam_general_look_at_modX cam_general_look_at_modY cam_general_look_at_modZ JUMP_CUT
											 									   
										ELSE

											IF flag_bought_item_already_shops = 0
									 			REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
												PRINT_NOW (CLTHNO2) 3000 1 //"You have already bought this item!"
												control_flag_mod = 2
												flag_bought_item_already_shops = 1
											ENDIF
										   		
										ENDIF

									ELSE
										
										IF flag_no_money_shops = 0
									 		REPORT_MISSION_AUDIO_EVENT_AT_POSITION player_x player_y player_z SOUND_SHOP_BUY_DENIED
											PRINT_NOW (SHOPNO) 3000 1 //"You don't have enough money to buy this."
											control_flag_mod = 2
											flag_no_money_shops = 1
										ENDIF										

									ENDIF

								ENDIF

							ENDIF
							
						ENDIF

					ENDIF // end button pressed
				
				ENDIF // end mod_flag = 4

			ENDIF // IS CHAR IN ANY CAR

		ENDIF // racertour flag = 0

	ELSE
		GOSUB mod_cleanup_big
	ENDIF // PLAYER PLAYING				
 					 
GOTO shop_mod_shop1_inner


mod_cleanup_big:

	IF main_menu_drawn_shops = 1
		DELETE_MENU main_menu_shops
		CLEAR_HELP
		main_menu_drawn_shops = 0
	ENDIF

	IF sub_menu_drawn_shops = 1
		DELETE_MENU sub_menu_shops
		CLEAR_HELP
		sub_menu_drawn_shops = 0
	ENDIF

	IF colour_menu_drawn_shops = 1
		DELETE_MENU colour_menu_shops
		CLEAR_HELP
		colour_menu_drawn_shops = 0
	ENDIF

	IF third_menu_drawn_shops = 1
		DELETE_MENU third_menu_shops
		CLEAR_HELP
		third_menu_drawn_shops = 0
	ENDIF

	IF forth_menu_drawn_shops = 1
		DELETE_MENU forth_menu_shops
		CLEAR_HELP
		forth_menu_drawn_shops = 0
	ENDIF

	IF mod_flag >= 1
		CLEAR_HELP
	ENDIF 
	
	car_colour1_change_mods = 0
	car_colour2_change_mods = 0 

	flag_cant_mod = 0
	flag_bought_item_already_shops = 0
	flag_no_money_shops = 0
	flag_car_same_colour = 0
	flag_car_normal_carmod = 0
	flag_car_lowrider_carmod = 0 
	flag_car_racer_carmod = 0

	mod_flag = 0
	control_flag_mod = 0

	SET_PLAYER_IS_IN_STADIUM FALSE

	SHOW_UPDATE_STATS TRUE

	SET_MINIGAME_IN_PROGRESS FALSE

	IF flag_1st_time_nitro_shop = 1 
		PRINT_HELP ( NITROH1 )
		flag_1st_time_nitro_shop = 2
	ENDIF
	
	TERMINATE_THIS_SCRIPT

RETURN


// draws the menu to say what mods the car can have
draw_main_menu_carmod:								  
				
	IF main_menu_drawn_shops = 0
		
		PRINT_HELP_FOREVER MODH1 
		
		// Create and populate the main menu.
		IF IS_XBOX_VERSION
			CREATE_MENU UPGRADE 29.0 155.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
		ELSE
			IF current_Language = LANGUAGE_ENGLISH
				CREATE_MENU UPGRADE 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
			ELSE
				IF current_Language = LANGUAGE_FRENCH
					CREATE_MENU UPGRADE 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
				ELSE
					IF current_Language = LANGUAGE_GERMAN
						CREATE_MENU UPGRADE 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
					ELSE
						IF current_Language = LANGUAGE_ITALIAN
							CREATE_MENU UPGRADE 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
						ELSE
							IF current_Language = LANGUAGE_SPANISH
								CREATE_MENU UPGRADE 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT main_menu_shops
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		SET_MENU_COLUMN_ORIENTATION main_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN main_menu_shops 0 DUMMY $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11] 
		
		main_menu_drawn_shops = 1
	ENDIF

RETURN

// draws the sub menu
draw_sub_menu_mod:

	IF sub_menu_drawn_shops = 0
		
		PRINT_HELP_FOREVER MODH3 

        // Create and populate the secondary menu.
		
		IF IS_XBOX_VERSION
			CREATE_MENU UPGRADE 29.0 155.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
		ELSE
			IF current_Language = LANGUAGE_ENGLISH
				CREATE_MENU UPGRADE 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
			ELSE
				IF current_Language = LANGUAGE_FRENCH
					CREATE_MENU UPGRADE 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
				ELSE
					IF current_Language = LANGUAGE_GERMAN
						CREATE_MENU UPGRADE 29.0 165.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
					ELSE
						IF current_Language = LANGUAGE_ITALIAN
							CREATE_MENU UPGRADE 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
						ELSE
							IF current_Language = LANGUAGE_SPANISH
								CREATE_MENU UPGRADE 29.0 145.0 186.0 1 TRUE TRUE FO_LEFT sub_menu_shops
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		SET_MENU_COLUMN_ORIENTATION sub_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN sub_menu_shops 0 DUMMY $item_text_label[0] $item_text_label[1] $item_text_label[2] $item_text_label[3] $item_text_label[4] $item_text_label[5] $item_text_label[6] $item_text_label[7] $item_text_label[8] $item_text_label[9] $item_text_label[10] $item_text_label[11] 
		
		IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB
		    temp_var_shops = 0

			WHILE temp_var_shops < number_of_paintjobs
				paintjob_number_menu = temp_var_shops + 1
				SET_MENU_ITEM_WITH_NUMBER sub_menu_shops 0 temp_var_shops PJOB paintjob_number_menu
				++temp_var_shops
			ENDWHILE

		    temp_var_shops = 0

			WHILE temp_var_shops < number_of_paintjobs
				ACTIVATE_MENU_ITEM sub_menu_shops temp_var_shops item_hilight_shops[temp_var_shops]
				++temp_var_shops
			ENDWHILE
		ELSE		   
		    temp_var_shops = 0

			WHILE temp_var_shops < number_of_mods_in_section
				ACTIVATE_MENU_ITEM sub_menu_shops temp_var_shops item_hilight_shops[temp_var_shops]
				++temp_var_shops
			ENDWHILE
		ENDIF
						
		sub_menu_drawn_shops = 1
	ENDIF
   
RETURN

// draws the special colour menu with a grid
draw_colour_menu:
	IF colour_menu_drawn_shops = 0
		
		PRINT_HELP_FOREVER MODH6 

		IF current_Language = LANGUAGE_ENGLISH
			CREATE_MENU_GRID CARM1 29.0 145.0 25.7 8 TRUE TRUE FO_LEFT colour_menu_shops
		ELSE
			IF current_Language = LANGUAGE_FRENCH
				CREATE_MENU_GRID CARM1 29.0 145.0 25.7 8 TRUE TRUE FO_LEFT colour_menu_shops
			ELSE
				IF current_Language = LANGUAGE_GERMAN
					CREATE_MENU_GRID CARM1 29.0 155.0 25.7 8 TRUE TRUE FO_LEFT colour_menu_shops
				ELSE
					IF current_Language = LANGUAGE_ITALIAN
						CREATE_MENU_GRID CARM1 29.0 145.0 25.7 8 TRUE TRUE FO_LEFT colour_menu_shops
					ELSE
						IF current_Language = LANGUAGE_SPANISH
							CREATE_MENU_GRID CARM1 29.0 145.0 25.7 8 TRUE TRUE FO_LEFT colour_menu_shops
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		colour_menu_drawn_shops = 1
	ENDIF
RETURN


// tells the first menu what to have it it
fill_in_text_menu1_mod:

no_of_mods_for_vehicle = 0
wing_mod_counter = 0 
roof_mod_counter = 0 
exhaust_mod_counter = 0 
nitro_mod_counter = 0 
spolier_mod_counter = 0 
bonnet_mod_counter = 0
bonnetLR_mod_counter = 0
front_bumper_mod_counter = 0 
rear_bumper_mod_counter = 0 
lights_mod_counter = 0 
front_bullbar_mod_counter = 0 
rear_bullbar_mod_counter = 0
misc_mod_counter = 0
wheel_mod_counter = 0
number_of_paintjobs = 0
number_of_colours = 0
hydraulics_mod_counter = 0
stereo_mod_counter = 0
shop_counter = 0

// gets the number of paintjobs
GET_NUM_AVAILABLE_PAINTJOBS stored_mod_garage_car number_of_paintjobs

IF NOT number_of_paintjobs = 0
	$item_text_label[no_of_mods_for_vehicle] = CARM11
	upgradetype[no_of_mods_for_vehicle] = MOD_GARAGE_PAINTJOB // Means paintjobs
	++ no_of_mods_for_vehicle 
ENDIF

GET_NUM_CAR_COLOURS stored_mod_garage_car number_of_colours

IF NOT number_of_colours = 0
	$item_text_label[no_of_mods_for_vehicle] = CARM1
	upgradetype[no_of_mods_for_vehicle] = MOD_GARAGE_CAR_COLOUR // Means paintjobs
	++ no_of_mods_for_vehicle 
ENDIF

// checks the rest of the mods
WHILE shop_counter < item_no_shops

	GET_ITEM_IN_SHOP shop_counter new_mod_index_wil
			
   	GET_VEHICLE_MOD_TYPE new_mod_index_wil upgradetype_mod

	IF upgradetype_mod = VEHICLE_REPLACEMENT_WHEEL

		IF wheel_mod_counter = 0 
			$item_text_label[no_of_mods_for_vehicle] = CARM12
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_REPLACEMENT_WHEEL
			++ no_of_mods_for_vehicle
		ENDIF
	
		wheel_mod_model_index[wheel_mod_counter] = new_mod_index_wil 

		++ wheel_mod_counter
		  
	ENDIF
		
	IF upgradetype_mod = VEHICLE_UPGRADE_WING

		IF wing_mod_counter = 0 
			$item_text_label[no_of_mods_for_vehicle] = CARM4
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_WING
			++ no_of_mods_for_vehicle
		ENDIF
	
		wing_mod_model_index[wing_mod_counter] = new_mod_index_wil 

		++ wing_mod_counter
		  
	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_ROOF

		IF roof_mod_counter = 0
			$item_text_label[no_of_mods_for_vehicle] = CARM10
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_ROOF
			++ no_of_mods_for_vehicle
		ENDIF

		roof_mod_model_index[roof_mod_counter] = new_mod_index_wil 
		 
		++ roof_mod_counter 
	  		     
	ENDIF

	IF upgradetype_mod = VEHICLE_REPLACEMENT_EXHAUST
	
		IF exhaust_mod_counter = 0 
			$item_text_label[no_of_mods_for_vehicle] = CARM5
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_REPLACEMENT_EXHAUST
			++ no_of_mods_for_vehicle  
		ENDIF

		exhaust_mod_model_index[exhaust_mod_counter] = new_mod_index_wil

		++ exhaust_mod_counter

	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_NITRO
	
		IF nitro_mod_counter = 0
			$item_text_label[no_of_mods_for_vehicle] = CARM6
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_NITRO
			++ no_of_mods_for_vehicle  
		ENDIF

		nitro_mod_model_index[nitro_mod_counter] = new_mod_index_wil 

		++ nitro_mod_counter

	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_SPOILER
	
		IF spolier_mod_counter = 0
			$item_text_label[no_of_mods_for_vehicle] = CARM2
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_SPOILER
			++ no_of_mods_for_vehicle
		ENDIF

		spolier_mod_model_index[spolier_mod_counter] = new_mod_index_wil 
	 
		++ spolier_mod_counter 
		  
	ENDIF
  
	IF upgradetype_mod = VEHICLE_UPGRADE_BONNET
	
		IF bonnet_mod_counter = 0			 	 
			$item_text_label[no_of_mods_for_vehicle] = CARM3
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_BONNET  
			++ no_of_mods_for_vehicle
		ENDIF

		bonnet_mod_model_index[bonnet_mod_counter] = new_mod_index_wil 

		++ bonnet_mod_counter 

	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_BONNET_LEFT_RIGHT
	
		IF bonnetLR_mod_counter = 0			 	 
			$item_text_label[no_of_mods_for_vehicle] = CARM17
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_BONNET_LEFT_RIGHT  
			++ no_of_mods_for_vehicle
		ENDIF

		bonnetLR_mod_model_index[bonnetLR_mod_counter] = new_mod_index_wil 

		++ bonnetLR_mod_counter 

	ENDIF

	IF upgradetype_mod = VEHICLE_REPLACEMENT_FRONT_BUMPER
	
		IF front_bumper_mod_counter = 0
	   		$item_text_label[no_of_mods_for_vehicle] = CARM7
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_REPLACEMENT_FRONT_BUMPER
			++ no_of_mods_for_vehicle
		ENDIF

		front_bumper_mod_model_index[front_bumper_mod_counter] = new_mod_index_wil

		++ front_bumper_mod_counter
			
	ENDIF
	
	IF upgradetype_mod = VEHICLE_REPLACEMENT_REAR_BUMPER
	
		IF rear_bumper_mod_counter = 0
			$item_text_label[no_of_mods_for_vehicle] = CARM8
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_REPLACEMENT_REAR_BUMPER
			++ no_of_mods_for_vehicle
		ENDIF

		rear_bumper_mod_model_index[rear_bumper_mod_counter] = new_mod_index_wil 
		
		++ rear_bumper_mod_counter
		  
	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_FRONT_LIGHTS
	
		IF lights_mod_counter = 0
	 		$item_text_label[no_of_mods_for_vehicle] = CARM9
	 		upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_FRONT_LIGHTS  
			++ no_of_mods_for_vehicle
		ENDIF

		lights_mod_model_index[lights_mod_counter] = new_mod_index_wil 

		++ lights_mod_counter

	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_FRONT_BULLBAR
	
		IF front_bullbar_mod_counter = 0
	 		$item_text_label[no_of_mods_for_vehicle] = CARM13
	 		upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_FRONT_BULLBAR  
			++ no_of_mods_for_vehicle
		ENDIF

		front_bullbar_mod_model_index[front_bullbar_mod_counter] = new_mod_index_wil 

		++ front_bullbar_mod_counter 

	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_REAR_BULLBAR
	
		IF rear_bullbar_mod_counter = 0 
			$item_text_label[no_of_mods_for_vehicle] = CARM14
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_REAR_BULLBAR
			++ no_of_mods_for_vehicle
		ENDIF

		rear_bullbar_mod_model_index[rear_bullbar_mod_counter] = new_mod_index_wil 
		
		++ rear_bullbar_mod_counter
		  
	ENDIF

	IF upgradetype_mod = VEHICLE_REPLACEMENT_MISC
	
		IF misc_mod_counter = 0 
			$item_text_label[no_of_mods_for_vehicle] = CARM15
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_REPLACEMENT_MISC  
			++ no_of_mods_for_vehicle
		ENDIF

		misc_mod_model_index[misc_mod_counter] = new_mod_index_wil 

		++ misc_mod_counter

	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_HYDRAULICS
	
		IF hydraulics_mod_counter = 0 
			$item_text_label[no_of_mods_for_vehicle] = CARM18
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_HYDRAULICS  
			++ no_of_mods_for_vehicle
		ENDIF

		hydraulics_mod_model_index[hydraulics_mod_counter] = new_mod_index_wil 

		++ hydraulics_mod_counter

	ENDIF

	IF upgradetype_mod = VEHICLE_UPGRADE_STEREO
	
		IF stereo_mod_counter = 0 
			$item_text_label[no_of_mods_for_vehicle] = CARM16
			upgradetype[no_of_mods_for_vehicle] = VEHICLE_UPGRADE_STEREO  
			++ no_of_mods_for_vehicle
		ENDIF

		stereo_mod_model_index[stereo_mod_counter] = new_mod_index_wil 

		++ stereo_mod_counter

	ENDIF

	++ shop_counter

ENDWHILE

// fills in any blanks that are left
temp_var_shops = no_of_mods_for_vehicle 

GOSUB fill_in_menu_blanks

RETURN


// gets the names and prices of the items
fill_in_text_menu2_mods:

IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB

	mod_counter = 0

	number_of_mods_in_section = number_of_paintjobs

	WHILE mod_counter < number_of_paintjobs
 
		$item_text_label[mod_counter] = paintjb 
	   	item_price[mod_counter] = PAINTJOB_COST_MODSHOP 

	   	IF stored_paintjob_number = mod_counter
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
		  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = number_of_paintjobs

	GOSUB fill_in_menu_blanks


	mod_swap_camX = custom_paint_cam_modX					 
	mod_swap_camY = custom_paint_cam_modY 
	mod_swap_camZ =	custom_paint_cam_modZ

	mod_swap_look_at_camX = custom_paint_cam_look_at_modX 
	mod_swap_look_at_camY = custom_paint_cam_look_at_modY
	mod_swap_look_at_camZ =	custom_paint_cam_look_at_modZ

ENDIF

IF upgradetype[upgrade_meun1_selected]  = VEHICLE_REPLACEMENT_WHEEL

	mod_counter = 0

	number_of_mods_in_section = wheel_mod_counter

	WHILE mod_counter < wheel_mod_counter
 
		GET_NAME_OF_ITEM wheel_mod_model_index[mod_counter] $item_text_label[mod_counter] 
	   	GET_PRICE_OF_ITEM wheel_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = wheel_mod_model_index[mod_counter]
		
		IF original_car_mod = wheel_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = wheel_mod_counter
	
	GOSUB fill_in_menu_blanks

	mod_swap_camX = wheel_cam_modX						 
	mod_swap_camY = wheel_cam_modY 
	mod_swap_camZ =	wheel_cam_modZ

	mod_swap_look_at_camX = wheel_cam_look_at_modX 
	mod_swap_look_at_camY = wheel_cam_look_at_modY
	mod_swap_look_at_camZ =	wheel_cam_look_at_modZ

	
ENDIF

IF upgradetype[upgrade_meun1_selected]  = VEHICLE_UPGRADE_WING

	mod_counter = 0

	number_of_mods_in_section = wing_mod_counter

	WHILE mod_counter < wing_mod_counter
 
		GET_NAME_OF_ITEM wing_mod_model_index[mod_counter] $item_text_label[mod_counter] 
	   	GET_PRICE_OF_ITEM wing_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = wing_mod_model_index[mod_counter]
		
		IF original_car_mod = wing_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF   
		  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = wing_mod_counter 

	GOSUB fill_in_menu_blanks
	 
	mod_swap_camX = wing_cam_modX						 
	mod_swap_camY = wing_cam_modY 
	mod_swap_camZ =	wing_cam_modZ

	mod_swap_look_at_camX = wing_cam_look_at_modX 
	mod_swap_look_at_camY = wing_cam_look_at_modY
	mod_swap_look_at_camZ =	wing_cam_look_at_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_ROOF 

	mod_counter = 0

	number_of_mods_in_section = roof_mod_counter

	WHILE mod_counter < roof_mod_counter

		GET_NAME_OF_ITEM roof_mod_model_index[mod_counter] $item_text_label[mod_counter] 
	   	GET_PRICE_OF_ITEM roof_mod_model_index[mod_counter] item_price[mod_counter]
	   	item_model_index[mod_counter] = roof_mod_model_index[mod_counter]
	   	
	   	IF original_car_mod = roof_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
			  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = roof_mod_counter 

   GOSUB fill_in_menu_blanks

	mod_swap_camX = roof_cam_modX						 
	mod_swap_camY = roof_cam_modY 
	mod_swap_camZ =	roof_cam_modZ

	mod_swap_look_at_camX = roof_cam_look_at_modX 
	mod_swap_look_at_camY = roof_cam_look_at_modY
	mod_swap_look_at_camZ =	roof_cam_look_at_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_REPLACEMENT_EXHAUST 

	mod_counter = 0

	number_of_mods_in_section = exhaust_mod_counter

	WHILE mod_counter < exhaust_mod_counter

		GET_NAME_OF_ITEM exhaust_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM exhaust_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = exhaust_mod_model_index[mod_counter]
		
		IF original_car_mod = exhaust_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = exhaust_mod_counter 

	GOSUB fill_in_menu_blanks

	mod_swap_camX = exhaust_cam_modX						 
	mod_swap_camY = exhaust_cam_modY 
	mod_swap_camZ =	exhaust_cam_modZ

	mod_swap_look_at_camX = exhaust_look_at_cam_modX 
	mod_swap_look_at_camY = exhaust_look_at_cam_modY
	mod_swap_look_at_camZ =	exhaust_look_at_cam_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_NITRO

	mod_counter = 0

	number_of_mods_in_section = nitro_mod_counter

	WHILE mod_counter < nitro_mod_counter

		GET_NAME_OF_ITEM nitro_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM nitro_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = nitro_mod_model_index[mod_counter]

		IF original_car_mod = nitro_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = nitro_mod_counter 

	GOSUB fill_in_menu_blanks
	 
	mod_swap_camX = nitro_cam_modX						 
	mod_swap_camY = nitro_cam_modY 
	mod_swap_camZ =	nitro_cam_modZ

	mod_swap_look_at_camX = nitro_look_at_modX 
	mod_swap_look_at_camY = nitro_look_at_modY
	mod_swap_look_at_camZ =	nitro_look_at_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_SPOILER 

	mod_counter = 0

	number_of_mods_in_section = spolier_mod_counter

	WHILE mod_counter < spolier_mod_counter

		GET_NAME_OF_ITEM spolier_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM spolier_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = spolier_mod_model_index[mod_counter]
		
		IF original_car_mod = spolier_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = spolier_mod_counter 

	GOSUB fill_in_menu_blanks
	
	mod_swap_camX = spoiler_cam_modX						 
	mod_swap_camY = spoiler_cam_modY 
	mod_swap_camZ =	spoiler_cam_modZ

	mod_swap_look_at_camX = spoiler_cam_look_at_modX 
	mod_swap_look_at_camY = spoiler_cam_look_at_modY
	mod_swap_look_at_camZ =	spoiler_cam_look_at_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_BONNET 

	mod_counter = 0

	number_of_mods_in_section = bonnet_mod_counter

	WHILE mod_counter < bonnet_mod_counter

		GET_NAME_OF_ITEM bonnet_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM bonnet_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = bonnet_mod_model_index[mod_counter]
		
		IF original_car_mod = bonnet_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = bonnet_mod_counter 

	GOSUB fill_in_menu_blanks

	mod_swap_camX =  bonnet_cam_modX											 
	mod_swap_camY =  bonnet_cam_modY
	mod_swap_camZ =	 bonnet_cam_modZ

	mod_swap_look_at_camX = bonnet_cam_look_at_modX  
	mod_swap_look_at_camY = bonnet_cam_look_at_modY
	mod_swap_look_at_camZ =	bonnet_cam_look_at_modZ
		
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_BONNET_LEFT_RIGHT 

	mod_counter = 0

	number_of_mods_in_section = bonnetLR_mod_counter

	WHILE mod_counter < bonnetLR_mod_counter

		GET_NAME_OF_ITEM bonnetLR_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM bonnetLR_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = bonnetLR_mod_model_index[mod_counter]
		
		IF original_car_mod = bonnetLR_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = bonnetLR_mod_counter 

	GOSUB fill_in_menu_blanks

	mod_swap_camX =  bonnet_cam_modX											 
	mod_swap_camY =  bonnet_cam_modY
	mod_swap_camZ =	 bonnet_cam_modZ

	mod_swap_look_at_camX = bonnet_cam_look_at_modX  
	mod_swap_look_at_camY = bonnet_cam_look_at_modY
	mod_swap_look_at_camZ =	bonnet_cam_look_at_modZ
		
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_REPLACEMENT_FRONT_BUMPER 

	mod_counter = 0

	number_of_mods_in_section = front_bumper_mod_counter

	WHILE mod_counter < front_bumper_mod_counter

		GET_NAME_OF_ITEM front_bumper_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM front_bumper_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = front_bumper_mod_model_index[mod_counter]
		
		IF original_car_mod = front_bumper_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = front_bumper_mod_counter 

	GOSUB fill_in_menu_blanks
	 
	mod_swap_camX = front_fender_cam_modX						 
	mod_swap_camY = front_fender_cam_modY 
	mod_swap_camZ = front_fender_cam_modZ	

	mod_swap_look_at_camX = front_fender_look_at_cam_modX  
	mod_swap_look_at_camY = front_fender_look_at_cam_modY
	mod_swap_look_at_camZ =	front_fender_look_at_cam_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_REPLACEMENT_REAR_BUMPER 

	mod_counter = 0

	number_of_mods_in_section = rear_bumper_mod_counter

	WHILE mod_counter < rear_bumper_mod_counter

		GET_NAME_OF_ITEM rear_bumper_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM rear_bumper_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = rear_bumper_mod_model_index[mod_counter]
		
		IF original_car_mod = rear_bumper_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = rear_bumper_mod_counter 

	GOSUB fill_in_menu_blanks
	
	mod_swap_camX = rear_fender_cam_modX 						 
	mod_swap_camY = rear_fender_cam_modY 
	mod_swap_camZ =	rear_fender_cam_modZ

	mod_swap_look_at_camX = rear_fender_look_at_cam_modX 
	mod_swap_look_at_camY = rear_fender_look_at_cam_modY
	mod_swap_look_at_camZ = rear_fender_look_at_cam_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_FRONT_LIGHTS 

	mod_counter = 0

	number_of_mods_in_section = lights_mod_counter

	WHILE mod_counter < lights_mod_counter

		GET_NAME_OF_ITEM lights_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM lights_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = lights_mod_model_index[mod_counter]
		
		IF original_car_mod = lights_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
		 		  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = lights_mod_counter 

	GOSUB fill_in_menu_blanks

	mod_swap_camX = lights_cam_modX						 
	mod_swap_camY = lights_cam_modY 
	mod_swap_camZ =	lights_cam_modZ

	mod_swap_look_at_camX = lights_look_at_cam_modX  
	mod_swap_look_at_camY = lights_look_at_cam_modY
	mod_swap_look_at_camZ =	lights_look_at_cam_modZ
		
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_FRONT_BULLBAR 

	mod_counter = 0

	number_of_mods_in_section = front_bullbar_mod_counter

	WHILE mod_counter < front_bullbar_mod_counter

		GET_NAME_OF_ITEM front_bullbar_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM front_bullbar_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = front_bullbar_mod_model_index[mod_counter]
		
		IF original_car_mod = front_bullbar_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = front_bullbar_mod_counter 

	GOSUB fill_in_menu_blanks

	mod_swap_camX = front_bullbar_cam_modX 						 
	mod_swap_camY = front_bullbar_cam_modY 
	mod_swap_camZ =	front_bullbar_cam_modZ

	mod_swap_look_at_camX = front_bullbar_look_at_cam_modX  
	mod_swap_look_at_camY = front_bullbar_look_at_cam_modY
	mod_swap_look_at_camZ =	front_bullbar_look_at_cam_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_REAR_BULLBAR 

	mod_counter = 0

	number_of_mods_in_section = rear_bullbar_mod_counter

	WHILE mod_counter < rear_bullbar_mod_counter

		GET_NAME_OF_ITEM rear_bullbar_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM rear_bullbar_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = rear_bullbar_mod_model_index[mod_counter]
		
		IF original_car_mod = rear_bullbar_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
				  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = rear_bullbar_mod_counter 

	GOSUB fill_in_menu_blanks	

	mod_swap_camX = rear_bullbar_cam_modX						 
	mod_swap_camY = rear_bullbar_cam_modY 
	mod_swap_camZ = rear_bullbar_cam_modZ	

	mod_swap_look_at_camX = rear_bullbar_look_at_cam_modX  
	mod_swap_look_at_camY = rear_bullbar_look_at_cam_modY
	mod_swap_look_at_camZ =	rear_bullbar_look_at_cam_modZ
		
ENDIF 

IF upgradetype[upgrade_meun1_selected] = VEHICLE_REPLACEMENT_MISC 

	mod_counter = 0

	number_of_mods_in_section = misc_mod_counter

	WHILE mod_counter < misc_mod_counter

		GET_NAME_OF_ITEM misc_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM misc_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = misc_mod_model_index[mod_counter]
		
		IF original_car_mod = misc_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
		  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = misc_mod_counter // no_of_mods_for_vehicle 

	GOSUB fill_in_menu_blanks	

	mod_swap_camX = misc_cam_modX 						 
	mod_swap_camY = misc_cam_modY 
	mod_swap_camZ =	misc_cam_modZ

	mod_swap_look_at_camX = misc_cam_look_at_modX  
	mod_swap_look_at_camY = misc_cam_look_at_modY
	mod_swap_look_at_camZ =	misc_cam_look_at_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_HYDRAULICS 

	mod_counter = 0

	number_of_mods_in_section = hydraulics_mod_counter

	WHILE mod_counter < hydraulics_mod_counter

		GET_NAME_OF_ITEM hydraulics_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM hydraulics_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = hydraulics_mod_model_index[mod_counter]
		
		IF original_car_mod = hydraulics_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
		  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = hydraulics_mod_counter // no_of_mods_for_vehicle 

	GOSUB fill_in_menu_blanks	

	mod_swap_camX = custom_paint_cam_modX					 
	mod_swap_camY = custom_paint_cam_modY 
	mod_swap_camZ =	custom_paint_cam_modZ

	mod_swap_look_at_camX = custom_paint_cam_look_at_modX 
	mod_swap_look_at_camY = custom_paint_cam_look_at_modY
	mod_swap_look_at_camZ =	custom_paint_cam_look_at_modZ
	
ENDIF

IF upgradetype[upgrade_meun1_selected] = VEHICLE_UPGRADE_STEREO 

	mod_counter = 0

	number_of_mods_in_section = stereo_mod_counter

	WHILE mod_counter < stereo_mod_counter

		GET_NAME_OF_ITEM stereo_mod_model_index[mod_counter] $item_text_label[mod_counter] 
		GET_PRICE_OF_ITEM stereo_mod_model_index[mod_counter] item_price[mod_counter]
		item_model_index[mod_counter] = stereo_mod_model_index[mod_counter]
		
		IF original_car_mod = stereo_mod_model_index[mod_counter]
			item_hilight_shops[mod_counter] = FALSE
		ELSE
			item_hilight_shops[mod_counter] = TRUE
		ENDIF 
		  
		++ mod_counter

	ENDWHILE

	// fills in any blanks that are left
	temp_var_shops = stereo_mod_counter // no_of_mods_for_vehicle 

	GOSUB fill_in_menu_blanks	

	mod_swap_camX = custom_paint_cam_modX					 
	mod_swap_camY = custom_paint_cam_modY 
	mod_swap_camZ =	custom_paint_cam_modZ

	mod_swap_look_at_camX = custom_paint_cam_look_at_modX 
	mod_swap_look_at_camY = custom_paint_cam_look_at_modY
	mod_swap_look_at_camZ =	custom_paint_cam_look_at_modZ
	
ENDIF

RETURN

fill_in_menu_blanks:
	WHILE temp_var_shops < MAX_NUMBER_ALLOWED_IN_MENU_SHOPS
		item_model_index[temp_var_shops] = -1
		item_price[temp_var_shops] = 0
		$item_text_label[temp_var_shops] = DUMMY
		++temp_var_shops 
	ENDWHILE	
RETURN

// draws the menu for car colour areas
draw_menu4_mod_shop:

	IF forth_menu_drawn_shops = 0
		
		// Create and populate the colors menu.
		
		IF flag_no_of_car_colours = 1
		    $forth_menu_item1 = CARCOL1
		    $forth_menu_item2 = DUMMY
		ELSE
		    $forth_menu_item1 = CARCOL1
		    $forth_menu_item2 = CARCOL2
		ENDIF

		PRINT_HELP_FOREVER MODH5

		IF IS_XBOX_VERSION
			CREATE_MENU UPGRADE 29.0 155.0 93.0 2 TRUE TRUE FO_LEFT forth_menu_shops
		ELSE
			IF current_Language = LANGUAGE_ENGLISH
				CREATE_MENU UPGRADE 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT forth_menu_shops
			ELSE
				IF current_Language = LANGUAGE_FRENCH
					CREATE_MENU UPGRADE 29.0 165.0 93.0 2 TRUE TRUE FO_LEFT forth_menu_shops
				ELSE
					IF current_Language = LANGUAGE_GERMAN
						CREATE_MENU UPGRADE 29.0 165.0 93.0 2 TRUE TRUE FO_LEFT forth_menu_shops
					ELSE
						IF current_Language = LANGUAGE_ITALIAN
							CREATE_MENU UPGRADE 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT forth_menu_shops
						ELSE
							IF current_Language = LANGUAGE_SPANISH
								CREATE_MENU UPGRADE 29.0 145.0 93.0 2 TRUE TRUE FO_LEFT forth_menu_shops
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF

		SET_MENU_COLUMN_ORIENTATION forth_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN forth_menu_shops 0 UPGRADE $forth_menu_item1 $forth_menu_item2 DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		
		IF flag_no_of_car_colours = 1 
			SET_MENU_COLUMN_ORIENTATION forth_menu_shops 1 FO_LEFT 
			SET_MENU_COLUMN forth_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
			SET_MENU_ITEM_WITH_NUMBER forth_menu_shops 1 0 DOLLAR 150 
		ELSE
			SET_MENU_COLUMN_ORIENTATION forth_menu_shops 1 FO_LEFT
			SET_MENU_COLUMN forth_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
			SET_MENU_ITEM_WITH_NUMBER forth_menu_shops 1 0 DOLLAR 150 
			SET_MENU_ITEM_WITH_NUMBER forth_menu_shops 1 1 DOLLAR 150 
		ENDIF

		SET_MENU_COLUMN_WIDTH forth_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH forth_menu_shops 1 46 
		
		forth_menu_drawn_shops = 1
	ENDIF
	
RETURN	


// Finds which type carcol1 and/or two a car can have
find_how_many_car_colours_mod:

// cars that use one car colour
	IF car_name_mod = BRAVURA
	OR car_name_mod = BUFFALO
	OR car_name_mod = PEREN
	OR car_name_mod = SENTINEL
	OR car_name_mod = STRETCH
	OR car_name_mod = MANANA
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = INFERNUS
	OR car_name_mod = VOODOO
	OR car_name_mod = PONY
	OR car_name_mod = CHEETAH
	OR car_name_mod = MOONBEAM
	OR car_name_mod = ESPERANT
		flag_no_of_car_colours = 1
	ENDIF

 	IF car_name_mod = WASHING
	OR car_name_mod = PREMIER
	OR car_name_mod = HOTKNIFE
	OR car_name_mod = PREVION
	OR car_name_mod = RUMPO
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = MONSTER
	OR car_name_mod = ADMIRAL
	OR car_name_mod = TURISMO
	OR car_name_mod = CADDY
	OR car_name_mod = SOLAIR
	OR car_name_mod = PCJ600
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = FAGGIO 
	OR car_name_mod = FREEWAY
	OR car_name_mod = SANCHEZ
	OR car_name_mod = HERMES
	OR car_name_mod = SABRE
	OR car_name_mod = ZR350
		flag_no_of_car_colours = 1
	ENDIF
 
 	IF car_name_mod = WALTON
	OR car_name_mod = BMX
	OR car_name_mod = BURRITO
	OR car_name_mod = MESA
	OR car_name_mod = SUPERGT
		flag_no_of_car_colours = 1
	ENDIF
 
	IF car_name_mod = ELEGANT
	OR car_name_mod = MTBIKE
	OR car_name_mod = NEBULA
	OR car_name_mod = BUCCANEE
	OR car_name_mod = FCR900
	OR car_name_mod = FORTUNE
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = CADRONA
	OR car_name_mod = WILLARD
	OR car_name_mod = FORKLIFT
	OR car_name_mod = TRACTOR
	OR car_name_mod = FELTZER
	OR car_name_mod = REMINGTN
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = VORTEX
	OR car_name_mod = VINCENT
	OR car_name_mod = SADLER
	OR car_name_mod = HUSTLER
	OR car_name_mod = INTRUDER
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = PRIMO
	OR car_name_mod = SUNRISE
	OR car_name_mod = MERIT
	OR car_name_mod = UTILITY
	OR car_name_mod = WINDSOR
	OR car_name_mod = MONSTERA
		flag_no_of_car_colours = 1
	ENDIF
		
	
	IF car_name_mod = JESTER
	OR car_name_mod = SULTAN
	OR car_name_mod = STRATUM
   	OR car_name_mod = BIKE
	OR car_name_mod = ELEGY
	OR car_name_mod = URANUS
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = FLASH
	OR car_name_mod = KART
	OR car_name_mod = MOWER
	OR car_name_mod = SWEEPER
	OR car_name_mod = HUNTLEY
	OR car_name_mod = STAFFORD
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = BF400
	OR car_name_mod = NEWSVAN
	OR car_name_mod = EMPEROR
	OR car_name_mod = WAYFARER
	OR car_name_mod = CLUB
	OR car_name_mod = SADLER
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = EUROS
	OR car_name_mod = PICADOR
	OR car_name_mod = ALPHA
	OR car_name_mod = TAXI
	OR car_name_mod = LANDSTAL
	OR car_name_mod = STALLION
		flag_no_of_car_colours = 1
	ENDIF

	IF car_name_mod = SLAMVAN
	OR car_name_mod = CLOVER
	OR car_name_mod = TAMPA
	OR car_name_mod = CABBIE
		flag_no_of_car_colours = 1
	ENDIF	
												   
// cars that use two car colours
	IF car_name_mod = BOBCAT
	OR car_name_mod = MRWHOOP
	OR car_name_mod = BFINJECT
	OR car_name_mod = BANSHEE
	OR car_name_mod = ROMERO
	OR car_name_mod = BLADE
		flag_no_of_car_colours = 2
	ENDIF

	IF car_name_mod = GLENDALE
	OR car_name_mod = OCEANIC
	OR car_name_mod = QUAD
	OR car_name_mod = REGINA
	OR car_name_mod = CAMPER
	OR car_name_mod = RANCHER
		flag_no_of_car_colours = 2
	ENDIF
	
  	IF car_name_mod = VIRGO
	OR car_name_mod = GREENWOO
	OR car_name_mod = HOTRING
	OR car_name_mod = SANDKING
	OR car_name_mod = BLISTAC
	OR car_name_mod = HOTRINA
		flag_no_of_car_colours = 2
	ENDIF

	IF car_name_mod = HOTRINB
	OR car_name_mod = BLOODRA
	OR car_name_mod = MAJESTIC
	OR car_name_mod = NRG500
	OR car_name_mod = TOWTRUCK
	OR car_name_mod = COMET
		flag_no_of_car_colours = 2
	ENDIF

	IF car_name_mod = BULLET
	OR car_name_mod = MAJESTIC
	OR car_name_mod = YOSEMITE
	OR car_name_mod = SAVANNA
		flag_no_of_car_colours = 2
	ENDIF

	IF car_name_mod = DUNERIDE
	OR car_name_mod = BROADWAY
	OR car_name_mod = TORNADO
	OR car_name_mod = TUG
	OR car_name_mod = PHOENIX
		flag_no_of_car_colours = 2
	ENDIF

 	IF car_name_mod = MONSTERB
		flag_no_of_car_colours = 2
	ENDIF

RETURN


can_car_be_modded_carmod1:

 	temp_var_shops = 0
	can_car_be_modded_counter = 0

	WHILE temp_var_shops < 16

		GET_AVAILABLE_VEHICLE_MOD stored_mod_garage_car car_mod_number[temp_var_shops] can_car_have_mod[temp_var_shops]
		
		IF NOT can_car_have_mod[temp_var_shops] = -1 
			++ can_car_be_modded_counter
		ENDIF
		
		++ temp_var_shops

	ENDWHILE	
	
RETURN


// third menu shops

draw_third_menu_shops:
	IF third_menu_drawn_shops = 0
		PRINT_HELP_FOREVER MODH2 
		IF IS_XBOX_VERSION
			CREATE_MENU UPGRADE 29.0 155.0 93.0 2 FALSE TRUE FO_LEFT third_menu_shops
		ELSE
			IF current_Language = LANGUAGE_ENGLISH
				CREATE_MENU UPGRADE 29.0 145.0 93.0 2 FALSE TRUE FO_LEFT third_menu_shops
			ELSE
				IF current_Language = LANGUAGE_FRENCH
					CREATE_MENU UPGRADE 29.0 165.0 93.0 2 FALSE TRUE FO_LEFT third_menu_shops
				ELSE
					IF current_Language = LANGUAGE_GERMAN
						CREATE_MENU UPGRADE 29.0 165.0 93.0 2 FALSE TRUE FO_LEFT third_menu_shops
					ELSE
						IF current_Language = LANGUAGE_ITALIAN
							CREATE_MENU UPGRADE 29.0 145.0 93.0 2 FALSE TRUE FO_LEFT third_menu_shops
						ELSE
							IF current_Language = LANGUAGE_SPANISH
								CREATE_MENU UPGRADE 29.0 145.0 93.0 2 FALSE TRUE FO_LEFT third_menu_shops
							ENDIF
						ENDIF
					ENDIF
				ENDIF
			ENDIF
		ENDIF
		
		SET_MENU_COLUMN_ORIENTATION third_menu_shops 0 FO_LEFT
		SET_MENU_COLUMN third_menu_shops 0 UPGRADE $item_text_label[upgrade_meun2_selected] DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		
		IF upgradetype[upgrade_meun1_selected] = MOD_GARAGE_PAINTJOB
			SET_MENU_ITEM_WITH_NUMBER third_menu_shops 0 0 PJOB paintjob_number_menu
		ENDIF

		SET_MENU_COLUMN_ORIENTATION third_menu_shops 1 FO_RIGHT
		SET_MENU_COLUMN third_menu_shops 1 COST DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY DUMMY
		SET_MENU_ITEM_WITH_NUMBER third_menu_shops 1 0 DOLLAR item_price[upgrade_meun2_selected]
		SET_MENU_COLUMN_WIDTH third_menu_shops 0 140 
		SET_MENU_COLUMN_WIDTH third_menu_shops 1 46 

		third_menu_drawn_shops = 1
	ENDIF
RETURN

MISSION_END

											 
}


