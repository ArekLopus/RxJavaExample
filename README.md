# Simple Java Application that uses JAX-RS, RxJava, Microprofile, Prometheus and Grafana	

- Uses:
  - JAX-RS Endpoints and Client	- to retrieve the data
  - RxJava - to asynchronously transfer the data to Microprofile Metrics
  - Microprofile Metrics - source for Prometheus scraper
  - Prometheus - scraps data every 5 seconds to monitor the data
  - Grafana - to visualize the data


-JAX-RS client retrieves the data from another microservice (for simplicity it is just the same app, using localhost) every 5 seconds using EJBs scheduler (action.Grabber class).

-Next, the data is passed asynchronously, using RxJava framework (rx.RxJavaService class), to the Microprofile Metrics framework (metrics.MetricsService class) which produces metrics in Prometheus format on 'host:port/metrics/application' HTTP endpoint.

-Prometheus scrappes the data every 5 seconds to monitor it and Grafana visualizes it.


### Grafana
- user: admin
- pass: admin

### Docker Compose
There is the docker-compose.yml in docker-compose directory.
- docker-compose up - starts the containers
- docker-compose stop - stops the containers
- docker-compose down - removes the containers

After the containers are up, you can access Grafana at localhost:3000 to see visualization.

### Kubernetes
There is the RxJavaExample.yml in docker-kubernetes directory

-Use this command to start it:\
kubectl create -f https://raw.githubusercontent.com/ArkadyYea/RxJavaExample/master/docker-kubernetes/RxJavaExample.yml

-Check if all pods are up:\
kubectl get pod

-Grafana can be accessed
- locally at port 3000
- as NodePort at 30000


-Here is a nice testing place:\
https://www.katacoda.com/courses/kubernetes/launch-single-node-cluster  
- run: minikube start
- After it completes (Done! kubectl is now configured to use "minikube"), run the kubectl create command above  
- After all pods are up you can access Grafana externally by clicking Dashboard button (next to Terminal)
<br/>

### Example Grafana Dashboard view after more than 2 hours:
![Example Grafana Dashboard view](https://github.com/ArkadyYea/RxJavaExample/blob/master/grafana/Grafana.jpg)
