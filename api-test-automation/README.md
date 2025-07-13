# API Test Automation Framework

## How to run the tests

### Running locally
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Python is installed ( I use v3.13.5)

**Setup steps:** 
1. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`  
2. Make sure you are in `/mobile-app-automation` folder        
3. Install Python package `pip install -e .`    

**Test execution steps:**  
1. Run tests `pytest`

### Running using Docker 
Used machine Macbook Pro M1 - Sequoia 15.5

Prerequisits: 
- Docker v4.37.2

**Setup steps:** 
1. Clone the repositoty `git@github.com:BogdanRosca/bogdan-rosca.git`  
2. Make sure you are in `/mobile-app-automation` folder        
3. Run docker compose command `docker-compose up --build`
 
# Findings
- `store/order/<id>` DELETE method can be called even on unexisting objects 
- `store/order/<id>` POST method does not respect the provided `shipDate` value