# Project showcasing Spring Boot microservices with GraphQL, Oauth2.0, Prometheus, Grafana, GitLab CI/CD, and FluxCD on Kubernetes
This project is much more than what the name suggests. It demonstrates how to run Springboot microservices on a local Kubernetes cluster with Kustomize and Helm charts, integrated with Prometheus and Grafana for monitoring, and ELK stack for logging. The project also showcases a comprehensive CI/CD pipeline using GitLab CI/CD and FluxCD for continuous deployment.
In solutions such as this GraphQL demonstration, where individual distributed components are responsible for assembling a complete user response, it is essential to track and monitor the behavior of all components working together to deliver a seamless user experience. This is where Prometheus and Grafana come into play, providing powerful monitoring and visualization capabilities to ensure the health, performance, and reliability of the entire system.
## Demonstration Outcomes
This project aims to demonstrate the following outcomes:
- How to build, run, and deploy a Spring Boot application with various integrations and features commonly used in modern microservices architecture.
- How to protect REST endpoints using Spring Security with Oauth2.0 and JWT, how to collect and visualize application metrics using Micrometer, Prometheus, and Grafana.
- How to integrate multiple RESTful services, databases, and messaging systems using Spring Data JPA, Spring Data MongoDB, Spring Kafka, and more.
- How to validate and handle exceptions in a standardized way using Spring Validation and Controller Advice with Problem+JSON.
- How to document APIs using OpenAPI/Swagger and Spring REST Docs.
- How to test the application using JUnit, Mockito, Testcontainers, and WireMock.
- How to containerize the application using Docker and deploy it to a Kubernetes cluster using Helm charts and Kustomize.
- How to set up a CI/CD pipeline using GitLab CI/CD and FluxCD for continuous deployment to Kubernetes.
- How to monitor and log the application using Prometheus, Grafana, and the ELK stack (Elasticsearch, Logstash, Kibana).
- How to implement cross-cutting concerns such as security, scalability, service integration, and more using Spring Boot and related technologies.
- How to integrate GraphQL API using Spring GraphQL and demonstrate its usage alongside RESTful APIs.

## Feature list
This project is a comprehensive Spring Boot application that integrates various technologies and frameworks to provide a robust and scalable solution. The application demonstrates the following key features:
- Spring Boot 3.2.x with Java 21
- Gradle build system
- RESTful API development with Spring Web MVC
- MongoDB integration using Spring Data MongoDB
- Integration with external RESTful services using RestTemplate and WebClient
- GraphQL API development using Spring GraphQL
- Exception handling with Controller Advice and Problem+JSON
- Input validation using Spring Validation
- Spring Boot Actuator for monitoring and management
- Micrometer for application metrics
- OpenAPI/Swagger for API documentation
- Testing with JUnit, Mockito, Testcontainers, and WireMock
- Dockerfile for containerization
- Helm charts for Kubernetes deployment
- Kustomize for Kubernetes manifest customization
- CI/CD pipeline using GitLab CI/CD and FluxCD
- Monitoring with Prometheus and Grafana
- Integration tests with Testcontainers for PostgreSQL, MongoDB
- Lombok for reducing boilerplate code
- Spring Boot Test for testing support
- Spring REST Docs for API documentation
- Spring Validation for input validation
- Spring Retry for retrying operations
- Database migrations using Flyway
- OIDC Client Credential flow for service-to-service authentication
- Security with Spring Security, OAuth2, and JWT
- OAuth2 client and resource server setup with JWT validation
- Method-level security with @PreAuthorize annotations
- Custom JwtAuthenticationConverter for extracting roles from JWT
- BCryptPasswordEncoder for password hashing
- Graceful shutdown with Kubernetes readiness checks and custom health indicators
- API Gateway with Spring Cloud Gateway
- Continuous Integration and Continuous Deployment (CI/CD) using GitLab CI/CD and FluxCD

## FluxCD in short
FluxCD is a GitOps tool that automates the deployment of applications to Kubernetes clusters. It continuously monitors a Git repository for changes and applies those changes to the cluster, ensuring that the desired state defined in the Git repository is always reflected in the cluster. This approach allows for version control, collaboration, and easy rollback of changes, making it an ideal solution for managing Kubernetes deployments in a GitOps workflow.    
Operating as a "pull-based" agent inside the cluster, it automates deployment by watching for repository changes, making it highly secure, auditable, and reliable for managing infrastructure and application updates.
This project demonstrates how to set up FluxCD to deploy Spring Boot MicroServices, Prometheus and Grafana to a local Kubernetes cluster, showcasing the benefits of GitOps for continuous deployment and infrastructure management.

FluxCD manifest files can be found on [this repository](https://github.com/agilesolutions/fluxcd-infra). 

## Overview Platform Architecture
This diagram illustrates the architecture of the Spring Boot application and its integration with various components and services.

<img title="Spring Boot Application Architecture" alt="Alt text" src="/images/architecture.png">

## Prerequisites
- Java 21 or higher
- Gradle 7.0 or higher
- Docker
- Kubernetes one node cluster running on Docker for Desktop
- GitLab account with a project

## GraphQL API testing with Bruno
Bruno is a GraphQL client that allows you to test and interact with GraphQL APIs. It provides a user-friendly interface for sending GraphQL queries and mutations, making it easier to explore and debug your GraphQL endpoints. You can use Bruno to test the GraphQL API of this Spring Boot application by sending queries and mutations to the gateway service, which will then route the requests to the appropriate microservices.
To use Bruno, you can follow these steps:
1. Download and install Bruno from the official website: [https://www.usebruno.com/](https://www.usebruno.com/)
2. Open Bruno and create a new GraphQL endpoint by entering the URL of the gateway service (e.g., http://localhost:30082/graphql).
3. Use the GraphQL query editor in Bruno to write and send GraphQL queries and mutations to the gateway service.
4. Analyze the responses from the gateway service to verify that the GraphQL API is working correctly and that the data is being retrieved and manipulated as expected.
5. You can also use Bruno's features such as variable support, query history, and response formatting to enhance your testing experience and make it easier to work with the GraphQL API.
6. For more information on how to use Bruno and its features, you can refer to the [official documentation](https://docs.usebruno.com/?_gl=1*1506q58*_ga*NDYwNjg2MTEwLjE3NjQxNjYyNDQ.*_ga_CH4MNV2D3Z*czE3NzE0Mjc3NjIkbzIkZzAkdDE3NzE0Mjc3NjIkajYwJGwwJGgw). 

Bruno collection and request to test the GraphQL API can be found on [this repository](/bruno/test-graphql/clients%20k8s.bru). You can import this collection into Bruno to quickly test the GraphQL API of this Spring Boot application.


## Project Structure
```
spring-graphql/
├── Dockerfile
├── .gitlab-ci.yml
├── build.gradle
├── settings.gradle
├── README.md
├── gateway/
│   └── GatewayApplication.java
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/springgraphql/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── config/
│   │   │       └── SpringGraphqlApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── schema/
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/example/springgraphql/
│               ├── controller/
│               ├── service/
│               └── SpringGraphqlApplicationTests.java
├── kustomize/
│   ├── base/
│   └── overlays/
│       ├── local/
│       ├── staging/
│       └── production/
├── charts/
│   └── spring-graphql/
│       ├── templates/
│       └── Chart.yaml
└── metrics/
    ├── prometheus-deployment.yaml
    ├── prometheus-service.yaml
    ├── grafana-deployment.yaml
    └── grafana-service.yaml
├── account-service/ (another microservice)
│   └── ...
├── client-service/ (another microservice)
│   └── ...
```

### Run the Application Locally on Kubernetes
Make sure you have Docker for Desktop running with Kubernetes enabled. You can build and deploy the application to your local Kubernetes cluster using the provided Gradle tasks and Kustomize overlays.
```bash
gradle release # compile, test, package and docker build each microservices
kubectl apply -k ./kustomize/overlays/local # kustomize deploy gateway, client-service and account-service to local k8s cluster
kubectl apply -f ./metrics # deploy k8s deployments and services to run Prometheus and Grafana.
kubectl logs -f -n services -l app=gateway # check logs gateway service
kubectl logs -f -n services -l app=client-service # tail the logs on client-service
kubectl logs -f -n services -l app=account-service # tail the logs on account-service
kubectl logs -f -n monitoring -l app=prometheus # tail the logs on prometheus
kubectl logs -f -n monitoring -l app=grafana # tail the logs on grafana
```
Check the actuator endpoints from within the k8s cluster
```
kubectl run curl --image=appropriate/curl -n services -it --restart=Never -- sh
curl http://client-service:8080/actuator # check client-service actuator endpoint
curl http://account-service:8081/actuator # check account-service actuator endpoint
curl http://gateway:8082/actuator # check gateway actuator endpoint
```
## The importancy of Observability with Micrometer to collecting JVM, CPU and HTTP metrics
While traditional monitoring tells you when a system is failing, observability enables you to understand why by analyzing its external outputs—logs, metrics, and traces.
<img title="Grafana SpringBoot APM Dashboard" alt="Alt text" src="/images/observability.png">
- Logs and Events provide context about what happened in the system at a specific point in time.
- Metrics offer quantitative data about system performance and resource usage over time.
- Traces illustrate the flow of requests through various components of the system, helping to identify bottlenecks and latency issues.
- Distributed Tracing allows tracking of requests as they propagate through multiple services, providing insights into the end-to-end journey of a request.
### Prometheus and Grafana setup
Deploy Prometheus and Grafana to your Kubernetes cluster using the provided manifests in the `metrics` directory and tail the logs to ensure they are running correctly.
```bash
kubectl apply -f ./metrics # deploy k8s deployments and services to run Prometheus and Grafana.
kubectl logs -f -n monitoring -l app=prometheus # tail the logs on prometheus
kubectl logs -f -n monitoring -l app=grafana # tail the logs on grafana
```
- [Swagger UI](http://localhost:30080/swagger-ui.html)
- [Grafana UI](http://localhost:30070/)
- [Prometheus UI](http://localhost:30090/)

### Setup grafana dashboard
- use admin/admin credentials to login and resetting password
- import dashboard [12900](https://grafana.com/grafana/dashboards/12900-springboot-apm-dashboard/)
- import dashboard [11378](https://grafana.com/grafana/dashboards/11378-justai-system-monitor/)
- select prometheus datasource (pre-configured on deployment)
- And your first runtime statistics will show...


## GitLab CI/CD Pipeline
The GitLab CI/CD pipeline is defined in the `.gitlab-ci.yml` file. It includes the following stages:
- `build`: Builds the application and creates a JAR file.
- `test`: Runs the tests.
- `docker`: Builds the Docker image.
- `helm`: Packages the application using Helm.
- `kustomize`: Customizes the Kubernetes manifests using Kustomize.
- `deploy`: Deploys the application to a Kubernetes cluster using FluxCD.

## Oauth2.0 client and resource server setup
- I am faking up OAuth2 server (/oauth2/token + JWKS) issues valid RS256-signed JWTs.
- The resource server validates the JWTs using the JWKS endpoint exposed by the fake OAuth2 server.
- The client application uses the OAuth2 Authorization Code flow to obtain access tokens from the fake OAuth2 server.
- The client application includes the access tokens in the Authorization header when making requests to the resource server.
- A JwtDecoder that validates the signature, issuer, and audience of the JWTs.
- An OAuth2AuthorizedClientService that stores authorized clients in memory.
- The resource server validates the access tokens and authorizes access to protected resources based on the scopes and roles contained in the tokens (A JWKS endpoint (/.well-known/jwks.json) exposing the public key)
- The application uses method-level security annotations (e.g., @PreAuthorize) to enforce authorization rules on specific endpoints, Maps the roles claim into Spring Security authorities so @PreAuthorize("hasRole('ADMIN')") works.
- The application uses a custom UserDetailsService to load user details from an in-memory store for authentication purposes.
- The application uses BCryptPasswordEncoder to hash and verify user passwords.
- The application uses a custom JwtAuthenticationConverter to extract roles from the JWT and map them to Spring Security authorities.

<img title="OIDC Authorization Code Flow" alt="Alt text" src="/images/oauth2_architecture.png">

### Run GraphQL query example
Run following curl command to test GraphQL endpoint via gateway
```bash
curl --request POST \
  --url http://localhost:30082/graphql \
  --header 'content-type: application/json' \
  --data '{"query":"{\n  clients {\n    id\n    firstName\n    lastName\n    middleName\n    accounts {\n      number\n      description\n      lineOfBusiness\n      amount\n      openingDayBalance\n      maturityDate\n    }\n  }\n}\n"}'
```
        
## Observability with Micrometer to collecting JVM, CPU and HTTP metrics
- JVM Memory: jvm.memory.used, jvm.memory.max, jvm.gc.pause, etc.
- CPU: system.cpu.usage, process.cpu.usage, system.load.average.1m, etc.
- HTTP Requests: http.server.requests — counts, timers, percentiles, tags by status, method, URI.

## Prometheus and Grafana setup
Deploy Prometheus and Grafana to your Kubernetes cluster using the provided manifests in the `metrics` directory and tail the logs to ensure they are running correctly.
```bash
kubectl apply -f ./metrics # deploy k8s deployments and services to run Prometheus and Grafana.
kubectl logs -f -n monitoring -l app=prometheus # tail the logs on prometheus
kubectl logs -f -n monitoring -l app=grafana # tail the logs on grafana
```

- [Swagger UI](http://localhost:30080/swagger-ui.html)
- [Grafana UI](http://localhost:30070/)
- [Prometheus UI](http://localhost:30090/)

### Setup grafana dashboard
- import dashboard [12900](https://grafana.com/grafana/dashboards/12900-springboot-apm-dashboard/)
- select prometheus datasource (pre-configured on deployment)

<img title="Grafana SpringBoot APM Dashboard" alt="Alt text" src="/images/dashboard.png">

## References
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [GitLab CI/CD Documentation](https://docs.gitlab.com/ee/ci/)
- [FluxCD Documentation](https://fluxcd.io/docs/)
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
- [Helm Documentation](https://helm.sh/docs/)
- [Kustomize Documentation](https://kubernetes-sigs.github.io/kustomize/)
- [Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)
- [Docker Documentation](https://docs.docker.com/)
- [Prometheus Documentation](https://prometheus.io/docs/introduction/overview/)
- [Grafana Documentation](https://grafana.com/docs/)
- [Spring REST Dos](https://spring.io/projects/spring-restdocs)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [Spring Data MongoDB Documentation](https://spring.io/projects/spring-data-mongodb)
- [Spring Kafka Documentation](https://spring.io/projects/spring-kafka)
- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/api/rest/actuator/index.html)
- [Spring Boot DevTools Documentation](https://docs.spring.io/spring-boot/reference/using/devtools.html)
- [Spring Boot Test Documentation](https://docs.spring.io/spring-boot/reference/testing/index.html)
- [Spring Boot Starter Web Documentation](https://docs.spring.io/spring-boot/reference/web/servlet.html)
- [Spring Boot Holistic Project overview](https://spring.io/projects/spring-boot)
