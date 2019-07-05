package abc;

//-Uses:
// • JAX-RS Endpoints and Client	- to retrieve the data
// • RxJava							- to asynchronously transfer the data to Microprofile Metrics
// • Microprofile Metrics			- source for Prometheus scraper
// • Prometheus						- scraps data every 5 seconds to monitor the data
// • Grafana						- to visualize the data


//-JAX-RS client retrieves the data from another microservice (for simplicity it is just the same app, using localhost)
// every 5 seconds using EJBs scheduler (action.Grabber class).
//-Next, the data is passed asynchronously, using RxJava framework (rx.RxJavaService class), to the Microprofile Metrics framework
// (metrics.MetricsService class) which produces metrics in Prometheus format on 'host:port/metrics/application' HTTP endpoint.
//-Prometheus scrappes the data every 5 seconds to monitor it and Grafana visualizes it.


//-Grafana (host:3000)
//	user		admin
//	password	admin

//-Prometheus (host:9090)

//-Microprofile metrics (host:8080/metrics/application)

public class Info {}
