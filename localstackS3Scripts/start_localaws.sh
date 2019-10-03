#!/bin/bash

docker-compose -p localaws up -d

# waits for localstack docker container initialization
sleep 5

# s3
aws --endpoint-url=http://localhost:4572 s3 mb s3://local-s3-bucket

