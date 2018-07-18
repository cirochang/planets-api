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
cd planets-api
```

Build the project
```
lein ring uberjar
```

## How to run

Start the mongo service
```
sudo service mongod start
```

Start the planets-api service
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


## The solution

When the software runs, it creates a webserver on port 3000 and creates a database called "planets-api" with some properties.

The application creates the following endpoints:

#### POST /planets

Add a planet to the database.

Example input:
```json
{
  "name": "Alderaan",
  "climate": "temperate",
  "terraint": "glasslands, mountains"
}
```

Example output:
```
ok
```

#### GET /planets

Get a list of all planets or a planet by name.

Query Strings:
```
name={planet_name}
```
OBS: `planet_name` is case sensitive. 

Example output:
```json
{
  "planets": [
    {
      "_id": "507f1f77bcf86cd799439011",
      "name": "Alderaan",
      "climate": "temperate",
      "terraint": "glasslands, mountains",
      "films": 2
    },
    {
      "_id": "607f1f77bcf86cd799439012",
      "name": "Yavin IV",
      "climate": "temperate, tropical",
      "terraint": "jungle, rainforests",
      "films": 1
    },
    {
      "_id": "707f1f77bcf86cd799439013",
      "name": "Hoth",
      "climate": "frozen",
      "terraint": "tundra, ice caves, mountain ranges",
      "films": 1
    }
  ]
}
```

#### GET /planets/{planet_id}

Get a planet by id. 

Example output:
```json
{
  "_id": "507f1f77bcf86cd799439011",
  "name": "Alderaan",
  "climate": "temperate",
  "terraint": "glasslands, mountains",
  "films": 2
}
```


#### DELETE /planets/{planet_id}

Delete a planet by id. 

Example output:
```
ok
```
