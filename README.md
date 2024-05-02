# Boot.cognito

Simple API to serve as a sample of Spring Boot + AWS Cognito

## Local pre-requisites
To run locally the following are needed:
- JDK/JRE 21
- AWS Credentials file on local machine with `[default]` profile and valid secrets
  - Alternative credential providers available are detailed here:
    - https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/credentials-chain.html
    
## Running
App can be with a spring boot configuration from your favorite IDE or via:
> .\gradlew clean bootRun

## Functionality
One simple endpoint from AdminController is active with no security. 
- It has a single function which will print user pool information from AWS Cognito
- AWS SDK will look for and attempt to use credentials from your machine, provided by env vars, etc. -  

## Next steps:
- Enable spring security
  - Add basic mcguffin endpoints secured with @PreAuthorize
- Add an endpoint to login / fetch a token for a Cognito user
- Test coverage - AWS SDK / Cognito validation
- Update mcguffin endpoints to use a service/repo and actually be able to work with data
- Explore multi-tenancy in Cognito and how it could affect what data users have access to or how to customize responses based on tenant
