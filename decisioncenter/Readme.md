# Deploying ODM libraries to a Maven repository

To deploy ODM libraries to a Maven repository, run the following command: <br/>
`mvn clean deploy -Dodm.home=<ODM HOME> -DrepositoryId=<YOUR REPOSITORY ID> -DrepositoryUrl=<YOUR REPOSITORY URL>`

# Building and running a project using the decisioncenter API

The `decisioncenter-dependencies.xml` pom references decisioncenter libraries and its required third 
party libraries. Add it as a dependency pom in your project to quickly build and generate a runnable jar of your program. 
``` 
<dependency>
     <groupId>com.ibm.odm</groupId>
     <artifactId>decisioncenter-dependencies</artifactId>
     <version><YOUR ODM VERSION></version>
     <type>pom</type>
</dependency>
```

A usage example can be found in the github repository. In `samples/decisioncenter/serverqueryexecute`.
