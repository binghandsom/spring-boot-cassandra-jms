#Project that shows reading messages from jms and inserting into cassandra cluster 

### Cassandra cluster using docker and the below docker images
- docker run --name='cass0' -d zmarcantel/cassandra
- docker run --name='cass1' -d zmarcantel/cassandra
- docker run --name='cass2' -d zmarcantel/cassandra
- docker run --name='activemq' -e 'ACTIVEMQ_MIN_MEMORY=512' -e 'ACTIVEMQ_MAX_MEMORY=2048' -d  webcenter/activemq:latest

### Machine used: 
- OS: Ubuntu 15.10
- Intel I7 - Octa core,  16GB RAM

#### All docker containers including JMeter and Spring boot app are run on this machine

### Keyspace Table structure
 CREATE TABLE springbootcassandrajms."message" (
	uuid uuid,
	source text,
	destination text,
	payloadChannel text,
	payloadFormat text,
	payloadType text,
	payload blob,
	valueDate text,
	whenReceived timeuuid,
	status text,
	PRIMARY KEY (source, destination, payloadType,valueDate,whenReceived,status,uuid)
);

### JMeter Performance Test Results
#### Test #1
- Parameters (10 threads and 10000 messages)
- Time taken 7.44 minutes
