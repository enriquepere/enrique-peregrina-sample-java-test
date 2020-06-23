# Payclip Java Backend Assessment

### Requirements
* [Operative Systems] - Any that supports JVM version 8 or latest
* [Maven] - Ver 3 or latest
* [Jdk] - 1.8 or greater

### Instalation
```sh
$ git clone https://github.com/enriquepere/enrique-peregrina-sample-java-test.git
$ cd enrique-peregrina-sample-java-test/
$ mvn install
$ cd <mvn_Repo_dir>\com\payclip\assessment\PayClipJavaAssessment\0.0.1\
$ java -jar PayClipJavaAssessment-0.0.1.jar
```
You can preload data putting [transactions.txt](https://github.com/enriquepere/enrique-peregrina-sample-java-test/blob/master/transactions.txt) file into application folder


## APIs

#### /transactions/{user_id}/add
##### Post method
####
##### Example
####
```sh
Request 
http://localhost:8080/transactions/207/add
Body
{
   "amount":24.3,
   "description":"First transaction",
   "date":"2018-06-11",
   "user_id":207
}

Response 
200 code
{
   "transaction_id":"4de90858-b651-492d-bf91-d0eb1e3e9490",
   "data":{
      "amount":24.3,
      "description":"First transaction",
      "date":"2018-06-11",
      "user_id":207
   }
}

400 code
Invalid user provided

400 code
Invalid date provided, valid date has YYYY/mm/DD format
```

#### /transactions/{user_id}/singleTransaction/{transaction_id}
##### Get method
####
##### Example
####
```sh
Request
http://localhost:8080/transactions/207/singleTransaction/4de90858-b651-492d-bf91-d0eb1e3e9490

Response
200 code
{
   "transaction_id":"4de90858-b651-492d-bf91-d0eb1e3e9490",
   "data":{
      "amount":24.3,
      "description":"First transaction",
      "date":"2018-06-11",
      "user_id":207
   }
}

404 code
Transaction not found
```

#### /transactions/{user_id}
##### Get method
####
##### Example
####
```sh
Request
http://localhost:8080/transactions/207

Respone
200 code
[
   {
      "transaction_id":"4de90858-b651-492d-bf91-d0eb1e3e9490",
      "data":{
         "amount":24.3,
         "description":"First transaction",
         "date":"2018-06-11",
         "user_id":207
      }
   },
   {
      "transaction_id":"db3dd242-8d7a-4dd1-8aaf-5c3574f49d58",
      "data":{
         "amount":10.0,
         "description":"First transaction",
         "date":"2020-03-04",
         "user_id":207
      }
   },
   {
      "transaction_id":"cb46ef0e-73ee-49ab-b1ca-eb106a4ad2fa",
      "data":{
         "amount":10.0,
         "description":"First transaction",
         "date":"2020-03-14",
         "user_id":207
      }
   },
   {
      "transaction_id":"f424409b-8ddb-4877-9e9d-2388d15d83e7",
      "data":{
         "amount":10.0,
         "description":"First transaction",
         "date":"2020-03-17",
         "user_id":207
      }
   },
   {
      "transaction_id":"847932af-2695-41d6-8a56-a572d3f84bff",
      "data":{
         "amount":10.0,
         "description":"First transaction",
         "date":"2020-03-24",
         "user_id":207
      }
   },
   {
      "transaction_id":"56ea2bd9-3350-451a-a3f5-528fdd01ea01",
      "data":{
         "amount":10.0,
         "description":"First transaction",
         "date":"2020-03-27",
         "user_id":207
      }
   },
   {
      "transaction_id":"9beaa343-bc95-42e2-8aea-adca6e8c7c41",
      "data":{
         "amount":10.0,
         "description":"First transaction",
         "date":"2020-03-29",
         "user_id":207
      }
   },
   {
      "transaction_id":"6e1636e0-a9ad-41a3-9c9d-374f5ac5aa49",
      "data":{
         "amount":10.0,
         "description":"First transaction",
         "date":"2020-03-30",
         "user_id":207
      }
   }
]
```

#### /transactions/{user_id}/sum
##### Get method
####
##### Example
####
```sh
Request
http://localhost:8080/transactions/207/sum

Response

200 code
{
   "user_id":207,
   "sum":94.3
}
```

#### /transactions/{user_id}/report
##### Get method
####
##### Example
####
```sh
Request
http://localhost:8080/transactions/207/report

Response 
200 code
[
   {
      "user_id":207,
      "wk_start_date":"2018-06-08 FRIDAY",
      "wk_finish_date":"2018-06-14 THURSDAY",
      "quantity":1,
      "amount":24.3
   },
   {
      "user_id":207,
      "wk_start_date":"2020-03-01 SUNDAY",
      "wk_finish_date":"2020-03-05 THURSDAY",
      "quantity":1,
      "amount":10.0
   },
   {
      "user_id":207,
      "wk_start_date":"2020-03-13 FRIDAY",
      "wk_finish_date":"2020-03-19 THURSDAY",
      "quantity":2,
      "amount":20.0
   },
   {
      "user_id":207,
      "wk_start_date":"2020-03-20 FRIDAY",
      "wk_finish_date":"2020-03-26 THURSDAY",
      "quantity":1,
      "amount":10.0
   },
   {
      "user_id":207,
      "wk_start_date":"2020-03-27 FRIDAY",
      "wk_finish_date":"2020-03-31 TUESDAY",
      "quantity":3,
      "amount":30.0
   }
]
```

#### /transactions/random
##### Get method
####
##### Example
####
```sh
Request
http://localhost:8080/transactions/random

Response
200 code
{
   "transaction_id":"847932af-2695-41d6-8a56-a572d3f84bff",
   "data":{
      "amount":10.0,
      "description":"First transaction",
      "date":"2020-03-24",
      "user_id":207
   }
}
```
