# boot.cognito

Simple API to serve as a sample of Spring Boot + AWS Cognito

App can be run as-is via your favorite IDE or with
> .\gradlew clean bootRun

One simple endpoint from AdminController is active with no security. 
- It has a single function which will print user pool information from AWS Cognito
- AWS SDK will look for and attempt to use credentials from your machine, provided by env vars, etc. -  

Next steps:

- add security config to props
- set up security
- do some user things
- tf* files to gitigonre