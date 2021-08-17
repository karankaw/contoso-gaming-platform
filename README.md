# contoso-gaming-platform

> Source Code  Github Link
https://github.com/karankaw/contoso-gaming-platform

> Swagger API Url 
https://app.swaggerhub.com/apis/karankaw/contoso-actor_locator_api_ec_2/1.0

> Swagger API Docs Url
https://app.swaggerhub.com/apis-docs/karankaw/contoso-actor_locator_api_ec_2/1.0  


> Direct URL APIs
http://ec2-3-92-42-240.compute-1.amazonaws.com:8080/actorLocator  
  
      
## CICD Approach
- I have used Maven to Build Executable Jar file
 - I have used DockerFile" to build image 
 - The Jar has been invoked using   Docker
 - Container  is  using 8080:8080 PORT Mapping 
****
 
 
## Docker Instructions
    cd ~
    docker image build -t contoso-gaming-platform:latest ./contoso-gaming-platform
    docker container run -p 8080:8080 -d --rm --name actorLocatorAPI contoso-gaming-platform:latest
    docker container ls
    docker container stop actorLocatorAPI
****

## Database

 - I have not done any DB Integration Here
 - Everything is in memory and if Server stops ,State is lost
 - Objective was to show algorithm

## JUNIT Test

 - I have added 2 Junit Tests as well just to show coverage

## Streams

 - Have Tried to use Stream of JDK 8 as much as i could to keep code
   readable

## Logger

 - Have Used SLF4J/Logback Logging as well

  - `Log Path :   /home/ec2-user/logs`
    

## EC2 Instance Deployment

 - Public IP changes if server is stopped/started
 - I could use - LoadBalancer->TargetGroups to bypass this limitation or
   API Gateway but Cost would have been more as I dont know when will be my solution evaluated
 - I am planning to keep server running for days -so that Public URL   does not change.

Public IP Address [May Change if server restarts]

    ec2-3-92-42-240.compute-1.amazonaws.com

    3.92.42.240

Private Ip Address

    172.31.5.93


## IAM Console Credentials

    proserve
    Red1$Apple
    061116847625

## Programmatic Access

    AKIAQ4OWZCYE7WML63MS
    U5EsjRecyjUU+QvJ0RYwdKelhLGPd5melG8QP/nW

## SSH Login Command (Non Putty)

    ssh -i "ContosoGamingPlatform.pem" ec2-user@ec2-3-92-42-240.compute-1.amazonaws.com


## Health-Check

 - Have implemented HealthCheck URL on /actorLocator  
 

## Known Limitations (Time Limitations)

 - I could have added more Models/Type Safety/Validation inside the code
 - I have used String as Landmark/Node names for simplicity as given in sample input.
 - Error Handling Mechanism - Generic
 - Could have implemented Monitoring and Cloudwatch Integration of Logs

