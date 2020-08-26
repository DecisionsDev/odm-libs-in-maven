# Server Query Execute Sample

## import the project in Eclipse
The project can be imported in Eclipse as a Maven project or generate an eclipse project using this command: <br/>
`mvn clean eclipse:eclipse`

## Generate a runnable jar 

To generate a runnable jar run: <br/> 
`mvn clean package`

Run the serverquery sample using: <br/>
`java -jar target\serverqueryexecute.jar <serverUrl> <datasource> <login> <password> <project>  <queryFile>
`
