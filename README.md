# Running spring boot app with localstack
This project is a spring boot web application that let you upload files and store them in a localstack s3 bucket.

## Prerequisites

- docker
- docker-compose
- awscli
- pip de python
- Localstack
- Spring boot
- Lombok

## Usage

To run Localstack, execute the `start_localaws.sh` file that is in the `localstackS3Scripts` repository:
```
./start_localaws.sh
```

To kill Localstack, execute the `stop_localaws.sh` file that is in the `localstackS3Scripts` repository.

```
./stop_localaws.sh
```
Then run the s3demo spring boot app in your commandline using : 
```
mvn spring-boot:run
```

 or with your preferred IDE. 

Go to http://localhost:8080/bucket/home and start uploading files

You can check the uploaded files using the command line with the following command : 
```
aws --endpoint-url=http://localhost:4572 s3 ls s3://local-s3-bucket
```