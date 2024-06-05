# Performance test

To run the performance test, start the app and issue the following command in a terminal  
Spring-boot:
```shell script
hey -n=250000 -c=8 http://localhost:8080/tasks/random/130
```

Quarkus:
```shell script
hey -n=250000 -c=8 http://localhost:8081/tasks/random/130
```

Micronaut:
```shell script
hey -n=250000 -c=8 http://localhost:8082/tasks/random/130
```
