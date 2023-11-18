bin/windows/zookeeper-server-start.bat config/zookeeper.properties 

# Start the Kafka broker service
bin/windows/kafka-server-start.bat config/server.properties

c: 
cd C:\kafka_2.13-3.6.0


Посмотреть сообщение из топика data-temperature
bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic data-temperature --from-beginning
