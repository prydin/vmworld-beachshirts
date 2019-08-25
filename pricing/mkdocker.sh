#!/bin/bash

if [[ -z "${DEMO_REGISTRY}" ]]; then
    echo "Env variable DEMO_REGISTRY must be set"
    exit 1 
fi

mvn clean install
docker build -t $DEMO_REGISTRY/pricing .
docker push $DEMO_REGISTRY/pricing
