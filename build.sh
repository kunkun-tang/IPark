#cp -r seyren-web/target/seyren-web-1.3.0-SNAPSHOT/js/* seyren-web/src/main/webapp/js/
#cp -r seyren-web/target/seyren-web-1.3.0-SNAPSHOT/html/* seyren-web/src/main/webapp/html/
#cp -r seyren-web/target/seyren-web-1.3.0-SNAPSHOT/img/* seyren-web/src/main/webapp/img/
#cp -r seyren-web/target/seyren-web-1.3.0-SNAPSHOT/css/* seyren-web/src/main/webapp/css/
#cp seyren-web/target/seyren-web-1.3.0-SNAPSHOT/index.html seyren-web/src/main/webapp/index.html
mvn clean verify
