from config.config import BASE_URLS, DEFAULT_HEADERS, ORDER_ID
import pytest
import os
import requests


ENVIRONMENT = os.getenv('TEST_ENV', 'dev')


@pytest.fixture(autouse=True)
def cleanup_order():
    """Fixture to delete the test order before and after each test"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{ORDER_ID}"
    
    # Clean up before test
    try:
        requests.delete(url, headers=DEFAULT_HEADERS)
    except requests.RequestException:
        pass
    
    yield
    
    # Clean up after test
    try:
        requests.delete(url, headers=DEFAULT_HEADERS)
    except requests.RequestException:
        pass
