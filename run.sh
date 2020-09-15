#!/bin/bash
mvn clean package
docker build --tag kalah .
docker run --publish 8080:8080 kalah