FROM akodali/java

COPY target/pricing-0.9.0-SNAPSHOT.jar /app/pricing-0.9.0-SNAPSHOT.jar

COPY app.yaml /app/app.yaml
COPY app-tags.yml /app/app-tags.yml
COPY wf-reporting-config.yml /app/wf-reporting-config.yml
COPY run.sh /app/run.sh

WORKDIR /app
ENTRYPOINT /bin/bash run.sh
