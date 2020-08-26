# Deploying Decision Server Rules libraries to a Maven repository

To deploy the libraries to a Maven repository, run the following command: <br/>
`mvn clean deploy -Dodm.home=<ODM HOME> -DrepositoryId=<YOUR REPOSITORY ID> -DrepositoryUrl=<YOUR REPOSITORY URL>`

## Building and running a project using the Decision Server Rules API

The `executionserver-engine-dependencies.xml` pom references the engine and its required third 
party libraries. Add it as a dependency pom in your project to quickly build and generate a runnable jar of your program. 
``` 
<dependency>
     <groupId>com.ibm.odm</groupId>
     <artifactId>executionserver-engine-dependencies</artifactId>
     <version><YOUR ODM VERSION></version>
     <type>pom</type>
</dependency>
```

A usage example can be found in the github repository. In `samples/executionserver/j2serulenegine`.

## Building and running a project using the Decision Server Rules Java SE factories

The `executionserver-j2serulesession-dependencies.xml` pom references the dependencies to build and run 
client applications using Decision Server factories API. Add it as a dependency pom in your project to quickly build and generate a runnable jar of your application. 
``` 
<dependency>
     <groupId>com.ibm.odm</groupId>
     <artifactId>executionserver-j2serulesession-dependencies</artifactId>
     <version><YOUR ODM VERSION></version>
     <type>pom</type>
</dependency>
```

A usage example can be found in the github repository. In `samples/executionserver/j2serulesession`.

# TODO 
* Build J2EE application war with Execution Server libs using Maven  
