#!/bin/sh

docker run \
    -e MONGO_INITDB_ROOT_USERNAME=mongoadmin \
    -e MONGO_INITDB_ROOT_PASSWORD=my-secret \
    -p 27018:27017/tcp \
    -t mongo:6.0.3
    