@echo off
echo.
echo [��Ϣ] ����Web���̡�
echo.

cd %~dp0
cd ../target
chdir

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% itsm-1.0.jar

cd bin
pause