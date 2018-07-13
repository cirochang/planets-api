# planets-api 1.0.0

This is an exercise of planets API provided by B2W
To see the enunciation of the proposed exercise click [here](/resources/exercise.txt).

## Pre requirements

- Java 1.8.0
- Leiningen 2.8.1
- MongoDB v3.4.10

## How to build

Clone the repository and enter in the project folder
```
git clone git@gitlab.com:cirochang/planets-api.git
cd job-queues
```

Build the project
```
lein ring uberjar
```

## How to run

```
java -jar target/uberjar/planets-api-1.0.0-standalone.jar
```

OR
```
lein ring server-headless
```

## How to run the tests
```
lein test
```
