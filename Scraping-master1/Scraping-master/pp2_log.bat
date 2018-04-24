@echo off   
set input=%1%  
set input2=%2%  
  
::传入普通参数  
echo 您输入了参数:%input%   
start cmd  /c "D:\A_GIT_MANTI\Scraping-master1\Scraping-master\jd_findone_log.py %input%"  