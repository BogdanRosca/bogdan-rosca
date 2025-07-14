# API Test Automation Framework

## API under test: 
`https://github.com/swagger-api/swagger-petstore`

## Technology Stack Selection
For this project I decided to go with my main programming language, **Python**. The rational is that Python is a very strong candidate for backend automation and one of the most common programming languages for API development. As a test orchestrator I used **Pytest**, light, fast to setup and feature reach. Lastly to make network requests, I decided to use **requests** library.  


## Project Structure
To maintain a clean and scalable solution, I implemented a clear separation between tests and their dependencies. This architecture follows industry best practices for test automation frameworks and ensures maintainability, and scalability through architecture principles like: separation of concerns, modular design, environments and configuration management. 

```
api-test-automation/
├── config/                
│   └── config.py              # Environment and API configuration settings
├── data/                   
│   ├── inventory.json         # Sample inventory data for testing
│   └── order.json             # Sample order data for testing
├── fixtures/                
│   └── fixtures.py            # Reusable test fixtures and data generators
├── petstore-api/              # Swagger Petstore API (git submodule)
│   ├── src/main/java/         
│   ├── Dockerfile            
│   └── pom.xml             
├── reports/                
│   └── report.html            # Generated HTML test reports
├── tests/                   
│   └── test_store.py          # Store API endpoint tests
├── utils/                  
│   └── test_utils.py          # Common testing utilities and helpers
├── venv/                      # Python virtual environment (local)
├── gitignore                  # Git ignore patterns
├── Dockerfile                 # Test framework containerization
├── docker-compose.yml         # Multi-service container orchestration
├── pyproject.toml             # Python project configuration and dependencies
├── README.md                  # Project documentation
└── requirements.txt           # Python package dependencies
```


## How to run the tests

### Running locally
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Python is installed ( I use v3.13.5)

**Setup steps:** 
1. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`  
2. Make sure you are in `/api-test-automation` folder       
3. [Optional] Create a virtual environment `python3 -m venv virtual-env`. Activate it `source virtual-env/bin/activate` 
4. Install Python package `pip install -e .` (if pip command is not fould try `pip3 install -e .`)   
5. A the API repository as submodule 
   ```
   git submodule add https://github.com/swagger-api/swagger-petstore petstore-api
   git submodule update --init --recursive
   git submodule update --remote
   ```
6. [Optional] If you don't have Maven installed it is require you run `brew install maven`

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
3. [Optional] Create a virtual environment `python3 -m venv virtual-env`. Activate it `source virtual-env/bin/activate` 
4. Install Python package `pip install -e .` (if pip command is not fould try `pip3 install -e .`)   
5. A the API repository as submodule 
   ```
   git submodule add https://github.com/swagger-api/swagger-petstore petstore-api
   git submodule update --init --recursive
   git submodule update --remote
   ```
6. [Optional] If you don't have Maven installed it is mandatory you run `brew install maven`
7. Build the project `cd petstore-api && mvn package jetty:run`

**Test execution steps:**  
1. Make sure the API service is stoped (otherwise it will block port 8080)
2. Make sure you are in `/api-test-automation/` folder       
3. Run docker compose command `docker-compose up --build`
 

## Test report
With every test execution a test report is generated in form of an `html` file at `api-test-automation/reports`. It contains information about environment, the executed summary and duration.


## Findings
- `store/order/<id>` DELETE method can be called even on unexisting objects 
- `store/order/<id>` POST method does not respect the provided `shipDate` value