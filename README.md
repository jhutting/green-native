# GraalVM for native
Grab one of the latest copies of GraalVM [here](https://www.graalvm.org) or through sdkman for your device and set up the JAVA_HOME to its path. It's advised to also set the GRAALVM_HOME to the same path.  
This should allow you to build native images, or simply run the jar with a performant JVM.

# Performance test
If you don't have them, install both these apps:
```shell script
brew install hey visualvm
```

To run the performance test, start the app, connect visualvm to the process and issue the following command in a terminal  
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

# hacks
If no mutations are performed, the h2 database can also be opened with ;FILE_LOCK=NO allowing multiple applications to read at once. To prevent applications interfering with one another we'll leave this on for now.