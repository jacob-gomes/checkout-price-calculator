REM Building the project using MVN
mvn clean install

REM Running the server for REST
java -jar target/checkout-price-calculator-0.0.1-SNAPSHOT.jar
