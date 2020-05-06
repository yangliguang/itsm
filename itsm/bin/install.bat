@echo off
echo.
echo [信息] 清理生成路径。
echo.

%~d0
cd %~dp0

cd ..
call mvn clean

echo.
echo [信息] 打包Web工程，生成war/jar包文件。
echo.

%~d0
cd %~dp0

cd ..
call mvn clean package -Dmaven.test.skip=true

echo.
echo [信息] 运行Web工程。
echo.

chcp 65001

cd %~dp0
cd ../target
chdir

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% itsm-1.0.jar --spring.profiles.active=dev

cd bin
pause