# API Test Automation Framework


## How to run the tests

### Running locally
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Python is installed ( I use v3.13.5)

**Setup steps:** 
1. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`  
2. Make sure you are in `/api-test-automation` folder       
3. Install Python package `pip install -e .` (if pip command is not fould try `pip3 install -e .`)   
4. A the API repository as submodule 
   ```
   git submodule add https://github.com/swagger-api/swagger-petstore petstore-api
   git submodule update --init --recursive
   git submodule update --remote
   ```
5. [Optional ] If you don't have Maven installed it is require you run `brew install maven`

**Test execution steps:**  
1. Start the API service. At the root of **swagger-petstore.git** do `mvn package jetty:run`
2. Run tests. Go to root of **bogdan-rosca.git** and run `pytest`

### Running using Docker 
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Docker v4.37.2

**Setup steps:** 
1. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`  
2. Make sure you are in `/api-test-automation` folder       
3. Install Python package `pip install -e .` (if pip command is not fould try `pip3 install -e .`)   
4. A the API repository as submodule 
   ```
   git submodule add https://github.com/swagger-api/swagger-petstore petstore-api
   git submodule update --init --recursive
   git submodule update --remote
   ```
5. [Optional ] If you don't have Maven installed it is require you run `brew install maven`
6. Build the project `cd petstore-api && mvn package jetty:run`

**Test execution steps:**  
1. Make sure the API service is stoped (otherwise it will block port 8080)
2. Make sure you are in `/api-test-automation/` folder       
3. Run docker compose command `docker-compose up --build`
 

## Test report
With every test execution a test report is generated in form of an `html` file at `api-test-automation/reports`. It contains information about environment, the executed summary and duration.


## Findings
- `store/order/<id>` DELETE method can be called even on unexisting objects 
- `store/order/<id>` POST method does not respect the provided `shipDate` value