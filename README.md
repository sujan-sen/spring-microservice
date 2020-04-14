# spring-microservice
 This code base will contain a demo for integration with Spring Boot, Microservice, Eureka, Zuul, Ribon, Security

### Service & Ports
discovery-service runs on port 8000
gateway-service runs on port 9000
rest are dynamically generated(port 0)

### OAuth Config
oauth-token can be obtained by the following curl
curl test-client:test123@localhost:8080/oauth/token -dgrant_type=client_credentials -dscope=any

### Kafka Config
1. Start the ZooKeeper Server.... 
bin/zookeeper-server-start.sconfig/zookeeper.properties
2. Start the Kafka Server....
bin/kafka-server-start.sh config/server.properties
3. Create the Topics
-> bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic credit-service-event
-> bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic order-service-event
4. Validate the Topics:
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

### Database Config
1. Create Schemas in MYSQL DB order_schema & credit_schema
2. CREATE TABLE `credit_schema`.`credit_master` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `max_credit_amt` double NOT NULL DEFAULT '0',
  `credit_used` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

3. CREATE TABLE `order_schema`.`order_master` (
  `order_no` int NOT NULL AUTO_INCREMENT,
  `order_quantity` int NOT NULL,
  `total_order_amount` double NOT NULL,
  `user_id` int NOT NULL,
  `order_status` varchar(45) DEFAULT NULL,
  `message` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

### SAGA Config
https://microservices.io/patterns/data/saga.html

### To-Do
1. Web module service added. It will run on localhost:8888. On hitting this url, the system should display a login page.
1. We need to plug-in an authentication service call to first authenticate the credentials and then generate the token.
1. Once it is done, we should navigate the user to /home.
