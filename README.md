# PgBulkInsert

Project base on [PgBulkInsert tool](https://github.com/bytefish/PgBulkInsert).
More information in [PgBulkInsert bulk processor](https://bytefish.de/blog/pgbulkinsert_bulkprocessor/)

## Build
- Download the [Project](https://github.com/guedim/PgBulkInsert) 
```sh
$git clone https://github.com/guedim/PgBulkInsert
```
- package it
```sh
$ mvn package
```

## Run

Run the project. It is Mandatory to set the file name to read

```sh
$ java -jar target/pgbulkinsert-0.0.1-SNAPSHOT.jar "/file/name/to/import/in/postgres"
```

>
> The PaymentReference, PaymentReferenceAdditionalValue and PaymentReferenceExtraParameter have foreign keys  :(
> It is mandatory import the entities in the following order:
>
- Import data into PaymentReference entity
```sh
$ java -jar target/pgbulkinsert-0.0.1-SNAPSHOT.jar "/location/to/files/payment_reference.txt"
```
- Import data into PaymentReferenceAdditionalValue entity
```sh
$ java -jar target/pgbulkinsert-0.0.1-SNAPSHOT.jar "/location/to/files/payment_reference_additional_values.txt"
```
- Import data into PaymentReferenceExtraParameter entity
```sh
$ java -jar target/pgbulkinsert-0.0.1-SNAPSHOT.jar "/location/to/files/payment_reference_extra_parameters.txt"
```

##  Test

1. Insert 10MM records in  5 minutes
2. 10MM records equals  to 850 MB


## TODO:

Flag for selecting the table to writte:
- PaymentReference
- ExtraParameter
- AdditionalValue

Read big files:
http://www.baeldung.com/java-read-lines-large-file
