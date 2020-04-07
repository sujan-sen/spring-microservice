# spring-microservice
 This code base will contain a demo for integration with Spring Boot, Microservice, Eureka, Zuul, Ribon, Security


discovery-service runs on port 8000
gateway-service runs on port 9000
rest are dynamically generated(port 0)

oauth-token can be obtained by the following curl
curl test-client:test123@localhost:8080/oauth/token -dgrant_type=client_credentials -dscope=any