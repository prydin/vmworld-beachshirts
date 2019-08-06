# Istio Lab Guide

##Create your namespace
```
create namespace <your login>

kubectl config set-context --current --namespace=<your login>
```

##Starting the application
```
cd ~/sample-app-java/

kubectl apply -f k8s/beachshirts.yml
```

## Starting the load generator
```
kubectl apply -f loadgen/loadgen.yaml
```

## Checking the load generator

```
kubectl get po

kubectl logs loadgen-XXXXXXX # Replace with actual loadgen pod id
```

## Installing Istio
### Install the CLI

```
cd ~

curl -L https://git.io/getLatestIstio | ISTIO_VERSION=1.2.2 sh -

cd istio-1.2.2/

export PATH=~/istio-1.2.2/bin:$PATH
```

### Push into your Kubernetes cluster
```
for i in install/kubernetes/helm/istio-init/files/crd*yaml; do kubectl apply -f $i; done

kubectl apply -f install/kubernetes/istio-demo.yaml
```

###Wait for the cluster to stabilize
```
kubectl get po -A
```

All pods should be in state "Completed" or "Running"

###Configure the Wavefront Proxy
```
vi k8s/proxy.yml
```

Replace Wavefront URL and token with the values provided 
```
# Need to change YOUR_CLUSTER and YOUR_API_TOKEN accordingly

apiVersion: apps/v1
# Kubernetes versions after 1.9.0 should use apps/v1
# Kubernetes version 1.8.x should use apps/v1beta2
# Kubernetes versions before 1.8.0 should use apps/v1beta1
kind: Deployment
metadata:
  labels:
    app: wavefront-proxy
    name: wavefront-proxy
  name: wavefront-proxy
  namespace: default
spec:
  replicas: 1
  selector:
    matchLabels:
      app: wavefront-proxy
  template:
    metadata:
      labels:
        app: wavefront-proxy
    spec:
      containers:
      - name: wavefront-proxy
        image: wavefronthq/proxy:latest
        imagePullPolicy: Always
        env:
        - name: WAVEFRONT_URL
          value: https://<my instance>.wavefront.com/api/
        - name: WAVEFRONT_TOKEN
          value: <your wavefront token>
        # Uncomment the below lines to consume Zipkin/Istio traces
        - name: WAVEFRONT_PROXY_ARGS
          value: "--traceZipkinListenerPorts 9411 --pushBlockedSamples 5"
        ports:
        - containerPort: 2878
          protocol: TCP
        # Uncomment the below lines to consume Zipkin/Istio traces
        - containerPort: 9411
          protocol: TCP
        securityContext:
          privileged: false
---
apiVersion: v1
kind: Service
metadata:
  name: wavefront-proxy
  labels:
    app: wavefront-proxy
  namespace: default
spec:
  ports:
  - name: wavefront
    port: 2878
    protocol: TCP
  # Uncomment the below lines to consume Zipkin/Istio traces
  - name: http
    port: 9411
    targetPort: 9411
    protocol: TCP
  selector:
    app: wavefront-proxy
```

###Start a Wavefront proxy

```
kubectl apply -f k8s/proxy.yml -n default
```

###Deploy with Istio sidecars

```
kubectl apply -f <(istioctl kube-inject -f ~/sample-app-java/k8s/beachshirts.yml)
```


