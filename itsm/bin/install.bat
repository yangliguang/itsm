@echo off
echo.
echo [��Ϣ] ��������·����
echo.

%~d0
cd %~dp0

cd ..
call mvn clean

echo.
echo [��Ϣ] ���Web���̣�����war/jar���ļ���
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package -Dmaven.test.skip=true

echo.
echo [��Ϣ] ����Web���̡�
echo.

chcp 65001

cd %~dp0
cd ../target
chdir

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% itsm-1.0.jar --spring.profiles.active=dev

cd bin
pause