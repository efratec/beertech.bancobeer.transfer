# beertech.bancobeer.transfer
Legacy POC Individal for Becks group

#Access credentials
RabbitURL = http://localhost:15672/
SwaggerUI = http://localhost:8080/swagger-ui.html

Rabbit user: guest
Rabbit password: guest

Postgres user: postgres
Postgres password: password

#How to test locally
Java 8 / Maven / your favourite IDE / Git

Tests must be performed in the FastUnits file

Ports 8080 and 8081 must be available

Clone the repo @ https://github.com/efratec/beertech.bancobeer.transfer.git

'docker-compose up' in the root directory to start the postgres and the rabbit container

Login into rabbit, go to the 'Queues' tab to add a new queue named 'rk.consumer.rabbitmq'

Start both the API and the Consumer applications on your IDE ('BancoBeerApiApplication.java' and 'BancoBeerConsumerApplication.java')

Use swagger to create accounts and check the balance of a certain account

Post a message on the rabbit queue to execute transactions

#How to post a message on the rabbit queue
Go to queues and click on your recently created queue
Go to the Publish Message section and add a payload with the following structure: {"operation":"", "value":"", "originAccount":"", "destinationAccount":""}
Operations can be (T/D/S)

#Possible errors and how to handle them
If you get a Flyway error about the Postgres password, its probably because you have another version of postgres installed, try uninstalling it and running through docker only.





