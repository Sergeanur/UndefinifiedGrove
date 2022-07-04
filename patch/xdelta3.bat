echo off
ren sc.exe _sc.exe
xdelta3 -d -s _sc.exe sc.patch sc.exe
del _sc.exe