
# Deploying IBM Operational Decision Manager libraries on a Maven repository

[![GitHub release](https://img.shields.io/github/release/ODMDev/odm-libs-in-maven.svg)](https://github.com/ODMDev/odm-libs-in-maven/releases)
![GitHub last commit](https://img.shields.io/github/last-commit/ODMDev/odm-libs-in-maven)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This repository centralizes the material to deploy IBM Operational Decision Manager libraries to a Maven repository. 
It includes Maven pom files to deploy the libraries, and dependency poms that list all the required libraries to build
ODM client applications. 

## Requirements
- ODM installed.
- Maven installed and configured. We assume the `settings.xml`
contains the Maven repository and credentials where the ODM libraries will be deployed.  

## Deployment
### Get the materials
- Download the `Source code.zip` corresponding to your ODM version from the [releases](../../releases) tab.
- Unzip it on the machine where ODM is installed. The zip contains two folders: 
  - `decisioncenter` contains the pom to deploy Decision Center libraries.  
  - `executionserver` contains the pom to deploy Decision Server Rules libraries.   

### Deploying the libraries to a Maven repository

To deploy the libraries:  
- Move to the folder where you unzipped the downloaded zip file. 
- Move to the component folder `decisioncenter` or `executionserver`
- Run the following command: <br/>
    `mvn clean deploy -Dodm.home=<ODM HOME> -DrepositoryId=<REPOSITORY ID> -DrepositoryUrl=<REPOSITORY URL>`<br/>
    Where: 
    - `<ODM_HOME>` is the location where ODM is installed.
    - `<REPOSITORY ID>` is the Maven repository id where you want to deploy the libraries. It is defined in 
    your Maven `settings.xml`.
    - `<REPOSITORY URL>` the url to that repository. 

## Building ODM client application

In addition to the ODM libraries, the maven deploy goal also deploys the following pom files. Add them as a dependency 
pom in the dependencies section of your project pom to quickly build and run ODM client applications: 
- `decisioncenter-dependencies.xml` pom references decisioncenter libraries and its required third party libraries
to build a Decision Center client application.
    ```
    <dependency>
     <groupId>com.ibm.odm</groupId>
     <artifactId>decisioncenter-dependencies</artifactId>
     <version><YOUR ODM VERSION></version>
     <type>pom</type>
    </dependency>
    ```
- `executionserver-engine-dependencies.xml` pom references the engine and its required third party libraries to build a 
Decision Server Rules application using the engine API. 
   ```
    <dependency>
        <groupId>com.ibm.odm</groupId>
        <artifactId>executionserver-engine-dependencies</artifactId>
        <version><YOUR ODM VERSION></version>
        <type>pom</type>
    </dependency>
   ``` 
- `executionserver-j2serulesession-dependencies.xml` pom references the dependencies to build and run a client 
application using Decision Server factories API.
   ```
    <dependency>
        <groupId>com.ibm.odm</groupId>
        <artifactId>executionserver-j2serulesession-dependencies</artifactId>
        <version><YOUR ODM VERSION></version>
        <type>pom</type>
    </dependency>
   ``` 
  
- `executionserver-j2eerulesession-dependencies.xml` pom references the dependencies to build and run a client 
application using Java EE rules session.
   ```
    <dependency>
        <groupId>com.ibm.odm</groupId>
        <artifactId>executionserver-j2eerulesession-dependencies</artifactId>
        <version><YOUR ODM VERSION></version>
        <type>pom</type>
    </dependency>
   ```   

For example, to build a Decision Center client application, add the following code to your pom:

``` 
<dependency>
     <groupId>com.ibm.odm</groupId>
     <artifactId>decisioncenter-dependencies</artifactId>
     <version>8.10.2.0</version>
     <type>pom</type>
</dependency>
```

Usage examples can be found in [samples](../../tree/master/samples). 

# Issues and contributions

For issues relating specifically to the Maven pom and scripts, please use the [GitHub issue tracker](../../issues). 
For more general issues relating to IBM Operational Decision Manager you can [get help](https://developer.ibm.com/odm/home/connect/) 
through the ODMDev community or, if you have production licenses for Operational Decision Manager, via the usual support channels.
We welcome contributions following [our guidelines](CONTRIBUTING.md).


# License
The pom files and associated scripts found in this project are licensed under the [Apache License 2.0](LICENSE).

# Notice
© Copyright IBM Corporation 2020.
