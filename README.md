# GitHub API based fault tolerance repository managment project

The project allows to use GitHub API based independent repository data retrieval mechanism with data collection and data warehouse.

  - Find remote repositories
  - Collect and capture remote data in data warehouse
  - Retrieve statistics
  - Be independent from remote external sources as a backdoor

# Cluster

  - The project cluster consist of internal Core MS and exposed Gateway

### Docker
The project build.gradle contains gradle buildDockerImage task by dint of which docker images can be easily built
### Build and Run
```sh
$ ./gradlew clean build
$ ./gradlew buildDockerImage
$ docker run -d -p 8080:8080 --network=host core:master
$ docker run -d -p 8081:8081 --network=host gateway:master
```

### Implementation details
- External client fault tolerance
 Because external client is able to return error code (as an example error because of GitHub request limitations) as a backdoor local data is suggested. Fault tolerance is implemented using resilence4j library
- Well tested
The project is tested in multiple arcitectural layers such as 
   - service-core
   - service-impl
   - service-integration-test
   - platform-impl
   - facade
