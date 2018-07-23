#!/usr/bin/env bash

mvn clean
mvn assembly:assembly
#docker build -t JavaTest3 -f docker/msyql/Dockerfile .
docker build -t JavaTest3 -f docker/java/Dockerfile .