# Executing rulesets using Java EE rules session

## Requirements
Build and install the application war archive in your local Maven repository if it is not yet installed. <br/>
Run: <br/>
`mvn clean install -f pom-war-j2ee.xml`

## Build the rule session application ear archive 

To generate the application ear archive run: <br/> 
`mvn clean package`

To run the j2eerulesession sample: <br/>
deploy the ear file `target\j2eerulesession.ear` in an existing ODM environment.
