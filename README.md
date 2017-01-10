# Instructions for setup and installation

#### These instructions assume that the user has installed Java and Maven.
Java can be downloaded [here](https://www.oracle.com/downloads/index.html) or through `brew install java` for mac users.
Maven can be downloaded [here](https://maven.apache.org/) or through `brew install maven` for mac users.


Clone or download this repository to your local machine.

Open a new command line or terminal window at the root of this repo on your local machine.

Run `mvn clean compile install`

This should complete with

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

Run `mvn spring-boot:run` to start the application. 

This should not complete. You should see something similar to

```
2017-01-10 00:09:53.731  INFO 18212 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-01-10 00:09:53.738  INFO 18212 --- [           main] application.Application                  : Started Application in 8.792 seconds (JVM running for 12.89)
```

as the last few lines in the console.

You are ready to go!

Open your favorite method of testing API endpoints and direct requests to localhost:8080/

Feel free to email me with any questions or concerns :)


