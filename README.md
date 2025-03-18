# Project to show workflow with Java Spring Boot

This is a project to show the usability of Java and Spring Boot to open REST APIs
endpoints

## How to run the Service
1. Clone this repo into your local system:
2. run the command `mvn clean spring-boot:run`

## Test the endPoints
The Service will have two local Endpoints ( `localhost:8080` ) open named:
- customer/newAccount
- customer/getInfo

Both endpoints are POST requests.
* newAccount takes a json type of argument with this structure:
 ` {"customerId": Long, "initialCredit": Long} `

* getInfo takes a ` Long ` integer as an input:
---

To test the endpoints you have two options:
1. Run a  command from the terminal
    1.  `curl -X POST localhost:8080/customer/newAccount -H 'Content-type:application/json' -d '{"customerId": 1741900320602, "initialCredit": 100}' `
    2.  `curl -X POST localhost:8080/customer/getInfo -H 'Content-type:application/json' -d '1741900320602' `
 
You can also use the UI provided in a different Repository.
