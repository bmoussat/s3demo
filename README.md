# s3demo  spring boot app with localstack
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

To run Localstack, execute `start_localaws.sh` file that is in the `localstackS3Scripts` repository.

To kill Localstack, execute `stop_localaws.sh` file that is in the `localstackS3Scripts` repository.

Then run the s3demo spring boot app in your commandline using : `mvn spring-boot:run` or with your preferred IDE. 
