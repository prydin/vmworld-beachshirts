#!/bin/bash
export DEMO_REGISTRY=gcr.io/prydin-beachshirts
mvn clean install
cd delivery
./mkdocker.sh
cd ../packaging
./mkdocker.sh
cd ../printing
./mkdocker.sh
cd ../shopping
./mkdocker.sh
cd ../styling
./mkdocker.sh
cd ..
