from config.config import BASE_URLS, DEFAULT_HEADERS
import os
import pytest
import json

ENVIRONMENT = os.getenv('TEST_ENV', 'dev')


def return_json_response(response):
    """Helper function to return a JSON response"""
    try:
        response_data = response.json()
    except json.JSONDecodeError:
        pytest.fail("Response is not valid JSON")
    return response_data


def load_test_data(filename):
    """Load test data from JSON file"""
    with open(f'data/{filename}') as f:
        return json.load(f)

