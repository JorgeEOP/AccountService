# Project to show workflow with Java Spring Boot

This is a project to show the usability of Java and Spring Boot to open REST APIs
endpoints

## How to run the Service
1. Clone this repo into your local system
2. Run the command `mvn clean spring-boot:run`

## Test the EndPoints
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
 
2. Use the UI provided in a different Repository.

## Available Customers
The Service comes with some pre defined customers and information about their accounts.
These are the initial CustomerIds:

| CustomerId    |
| -----------   |
| 1741900320602 |
| 1741901379255 |
| 1742075403758 |
| 1742042813409 |

## Access to the DB
The service uses the JPA hibernate package to store data into memory. 
You can have a look at the Databases under the following Link (localhost):
`http://localhost:8080/h2-console/`

Connect to it and you should see three Tables: *ACCOUNT*, *CUSTOMER* and *TRANSACTION*
with the initial values.

Everytime you add an account to a customer, the *ACCOUNT* and *TRANSACTION* tables
will be updated.

Each Account can be referenced to a customerId. The relationship is ManyToOne.
