### About
 An e-commerce store owner is obsessed with checking his sales every minute. One day, he gets tired of being constantly distracted by his habit and asks his team to build an api that gives him the transaction value and data in the last ten minutes, anytime he wants to check.

### What this really is
Using the store owner's obsession as an excuse to build a simple api that explores Java's concurrency power and implements a simple caching mechanism that is being accessed and updated concurrently.

This project has been built using Dropwizard for learning and experimentation purposes

### Run the application
    mvn clean install
    java -jar target/kconcurrent-app-1.0-SNAPSHOT.jar server config.yml

