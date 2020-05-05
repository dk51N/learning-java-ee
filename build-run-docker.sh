#!/bin/bash
set -eu

mvn clean package

docker run --rm \
  -p 8080:8080 \
  -v $(pwd)/target/cars.war:/glassfish5/glassfish/domains/domain1/autodeploy/cars.war \
  oracle/glassfish:5.0

#FROM jboss/wildfly
#COPY SimpleJavaWebApp_war/SimpleJavaWebApp_war.war /opt/jboss/wildfly/standalone/deployments/
