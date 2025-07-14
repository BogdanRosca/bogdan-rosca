# API Test Automation Framework

## How to run the tests

### Running locally
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Python is installed ( I use v3.13.5)

**Setup steps:** 
1. Clone the API repository `git clone https://github.com/swagger-api/swagger-petstore.git` 
2. [Optional ] If you don't have Maven installed it is require you run `brew install maven`
3. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`  
4. Make sure you are in `/api-test-automation` folder       
5. Install Python package `pip install -e .` (if pip command is not fould try `pip3 install -e .`)   

**Test execution steps:**  
1. Start the API service. At the root of **swagger-petstore.git** do `mvn package jetty:run`
2. Run tests. Go to root of **bogdan-rosca.git** and run `pytest`

### Running using Docker 
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Docker v4.37.2

**Setup steps:** 
1. Clone the API repository `git clone https://github.com/swagger-api/swagger-petstore.git` 
2. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`  
3. Make sure you are in `/api-test-automation/` folder   
4. Change the `docker-compose.yml` line 4 ( `petstore-api` -> `build` -> `context`) by pointing to **swagger-petstore's** Dockers's file 

**Test execution steps:**  
3. Make sure you are in `/api-test-automation/` folder        
3. Run docker compose command `docker-compose up --build`
 
# Findings
- `store/order/<id>` DELETE method can be called even on unexisting objects 
- `store/order/<id>` POST method does not respect the provided `shipDate` value