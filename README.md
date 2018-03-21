# PgBulkInsert

Project base on [PgBulkInsert tool](https://github.com/bytefish/PgBulkInsert).
More information in [PgBulkInsert bulk processor](https://bytefish.de/blog/pgbulkinsert_bulkprocessor/)

## Build
- Download the [Project](https://github.com/guedim/PgBulkInsert) 
```sh
$ git clone https://github.com/guedim/PgBulkInsert
```
- package it
```sh
$ mvn package
```

## Run

Run the project. It is Mandatory to set the entity and file name to read.

- **entity**: The entity name to import data. The valid values are:
	- **PAYMENT_REFERENCE**: Set to import the data into PaymentReference entity
	- **EXTRA_PARAMETER**: Set to import the data into PaymentReference Extra Parameter entity.
	- **ADDITIONAL_VALUE**: Set to import the data into PaymentReference Additional values entity.
- **'filename'**: The absolute path name of the input file.

```sh
$ java -Dfile.encoding=UTF-8 -jar target/pgbulkinsert-1.0.0.jar {PAYMENT_REFERENCE | EXTRA_PARAMETER | ADDITIONAL_VALUE}  {'filename'}");
```

>
> The PaymentReference, PaymentReferenceAdditionalValue and PaymentReferenceExtraParameter have foreign keys.
> It is mandatory import the entities in the following order:
>
- Import data into PaymentReference entity
```sh
$ java -Dfile.encoding=UTF-8 -jar target/pgbulkinsert-1.0.0.jar PAYMENT_REFERENCE "/location/to/files/payment_reference.txt"
```
- Import data into PaymentReferenceExtraParameter entity
```sh
$ java -Dfile.encoding=UTF-8 -jar target/pgbulkinsert-1.0.0.jar EXTRA_PARAMETER "/location/to/files/payment_reference_extra_parameters.txt"
```

- Import data into PaymentReferenceAdditionalValue entity
```sh
$ java -Dfile.encoding=UTF-8 -jar target/pgbulkinsert-1.0.0.jar ADDITIONAL_VALUE "/location/to/files/payment_reference_additional_values.txt"
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
