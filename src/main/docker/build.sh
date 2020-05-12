#!/bin/bash

cp ../../../target/MySpringBoot-1.0-spring-boot.jar .
sudo docker build -t ceq-test01 .
rm MySpringBoot-1.0-spring-boot.jar

