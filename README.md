export MAVEN_OPTS="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=n"
mvn jetty:run

http://localhost:8082/lightf/api/init

http://localhost:8082/lightf/api/ls?path=/d1

http://localhost:8082/lightf/api/touch?path=/d1/f12&content=aaaaa
http://localhost:8082/lightf/api/cat?path=/d1/f12

mkdir server/target/aaa
protoc --java_out=server/target/aaa server/src/main/proto/*.proto -Iserver/src/main/proto


