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

## Project strucutre

    .
    ├── src                             # Project source folder
    │   └── planets_api                 # Main namespace project folder
    │       ├── controllers             # Folder for control the requests
    │       │   └── planets_ctl.clj     # Responsible for planets controller
    |       ├── models                  # Folder for write and read data and validations
    │       │   └── planets_md.clj      # Responsible for planets model
    │       ├── utils                   # Folder for useful functions
    │       │   ├── database.cj         # Responsible for communicate with database
    │       │   └── swapi.clj           # Responsible for communicate with swapi.co
    |       ├── main.clj                # Responsible for startup the api
    │       └── routes.js               # Responsible for indicate the endpoints of the api
    ├── test                            # Test source folder
    │   └── test_planets_api            # Main namespace project test folder
    │       ├── e2e                     # Folder for end to end tests
    │       │   ├── not_found_test.clj  # Responsible for test 404 endpoints requests
    │       │   └── planets_test.clj    # Responsible for test planets endpoints requests
    │       ├── helpers                 # Folder to help the tests source files
    │       │   ├── factories.cj        # Give some objects prepare to use in tests 
    │       │   ├── fixture.cj          # Give some functions to prepare each test
    │       │   └── utils.clj           # Give some useful functions to use in tests
    │       └── units                   # Folder for units tests
    │           └── models              # Folder for test the models folder
    │               └── planets_md.clj  # Responsible for test the planets_ctl.clj file
    └── project.clj                     # Project setup file
