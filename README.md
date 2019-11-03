# Installing & Running Code

## Prerequisites:

#### Ensure jdk 1.8 and maven 3.6.1 or above is set in your class path
#### Unzip the project to your local machine 


## Running the code on Windows(recomended):

#### Open command prompt navigate to checkout-price-calculator
#### > mvn clean install

#### Run the integration test (provided in the pdf)
#### > mvn integration-test

#### Run the rest server (enhancement to the task)
#### > java -jar target/checkout-price-calculator-0.0.1-SNAPSHOT.jar

# Task side notes
## Enhancement can be done:
#### Can be made restful and provision for refreshing cart (added it as demontration)
#### Database can be introduced for holding the rules

## Assumption:
#### The goods are associated with a basic attribute of good name and good cost per unit

## Changes made to the original:
#### Made changes in the test cases to adapt it to spring runned and decoupled coding