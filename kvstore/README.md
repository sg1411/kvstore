**Key-Value Data Store**
=================

## Run the springboot application.
## Access the service via 'localhost:8080/'

## API's to get/put/getAverage/getExpiredKeyValues in KeyValueController.java
## localhost:8080/get/a - get value of key=a
## localhost:8080/put/a/1 - put key=a with value=1
## localhost:8080/average - get average value
## localhost:8080/expired - get expired list

## Give Expiry value in miliseconds in application.properties(default value of 20seconds given in file)
## data.timeoutMs=20000

## Currently keeping the expired values in memory(read the list using API)
## List = KeyValueData.expiredEntries

