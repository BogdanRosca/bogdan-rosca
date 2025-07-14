"""
Configuration file for API test framework
"""

BASE_URLS = {
    'dev': 'http://localhost:8080/api/v3',
    'docker': 'http://petstore-api:8080/api/v3'
}

DEFAULT_HEADERS = {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
}

ENDPOINTS = {
    'pet': '/pet',
    'store': '/store',
    'user': '/user',
}

ORDER_ID = 1234567890
