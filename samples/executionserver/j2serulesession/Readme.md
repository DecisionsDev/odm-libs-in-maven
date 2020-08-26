# Executing rulesets using Decision Server Rules session factories 

## Requirements
Install the sample Execution Object Model (XOM) in your local Maven repository if it is not yet installed. <br/>
Run: <br/>
`mvn install:install-file -Dfile=xom/loan-validation-xom.jar -DgroupId=com.ibm.odm -DartifactId=loan-validation-xom 
-Dversion=1.0 -Dpackaging=jar`


## Import the project in Eclipse
The project can be imported in Eclipse as a Maven project or generate an eclipse project using this command: <br/>
`mvn clean eclipse:eclipse`

## Generate a runnable jar 

To generate a runnable jar run: <br/> 
`mvn clean package`

Run the j2serulesession sample using: <br/>
`java -jar target\j2serulesession.jar
`
