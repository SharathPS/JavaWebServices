# Java Application Web Services

Run elevator application on tomcat :
``
mvn clean install -Dmaven.tomcat.port=8292 tomcat:run-war
``

#### Application is secured with credentials for all requests need to pass credentials in the request headers
##### Default Credentials: username: admin password: root123

## REST Service
1. ``<applicationhosted>/list``: Retrieves elements from database and displays in JSON format (GET) 

2. ``<applicationhosted>/push?i1=<value1>&i2=<value2>``: Pass the two numbers for calculating GCD and persisting in database (POST)

## SOAP Service

1. ``<applicationhosted>/services/gcdprocessor?wsdl``: SOAP WSDL

	### Services available
		``gcdList`` : Retrieves all GCD's present in database
		``gcd`` : Retrieves GCD of the first JMS message 
		``gcdSum`` : Returns the sum of all GCD's in database

## JMS: Active MQ

1. Active MQ is used, for easy execution of application updated the configuration as embedded application

## Database: MY SQL

1. Need to pre configure the database ``gcd`` and setup table ``gcd_numbers``

#### Under Consideration:

1. Planning to embed the database by default if not configured

##### applicationhosted: http://localhost:8292/JavaAppWebServices
