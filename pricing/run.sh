#!/bin/bash
if [ -z "$PROXYHOST" ]
then
    export PROXHOST=wavefront-proxy
fi
sed -i "s/@PROXYHOST@/$PROXYHOST/g" /app/wf-reporting-config.yml
java -jar /app/pricing-0.9.0-SNAPSHOT.jar server /app/app.yaml
