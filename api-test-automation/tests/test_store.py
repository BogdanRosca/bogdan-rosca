from config.config import BASE_URLS, DEFAULT_HEADERS, ORDER_ID
from fixtures.fixtures import cleanup_order
from utils.test_utils import create_order, compare_order_data
import json
import os
import pytest
import requests


ENVIRONMENT = os.getenv('TEST_ENV', 'dev')


with open('data/order.json') as f:
    order_payload = json.load(f)


def test_get_store_order_with_invalid_id():
    """Test GET request to retrieve a store order with invalid ID"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order/123123123"
    
    response = requests.get(url, headers=DEFAULT_HEADERS)
    
    assert response.status_code == 404
    assert 'Order not found' in response.text 


def test_get_store_order_with_valid_id():
    """Test GET request to retrieve a store order"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{ORDER_ID}"
    
    order_payload["id"] = ORDER_ID
    create_order(order_payload)

    response = requests.get(url, headers=DEFAULT_HEADERS)
    assert response.status_code == 200
    
    try:
        response_data = response.json()
    except json.JSONDecodeError:
        pytest.fail("Response is not valid JSON")
    
    assert compare_order_data(order_payload, response_data)

    
def test_create_store_order():
    """Test POST request to create a store order"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order"
    
    response = requests.post(url, headers=DEFAULT_HEADERS, json=order_payload)
    assert response.status_code == 200
    
    try:
        response_data = response.json()
    except json.JSONDecodeError:
        pytest.fail("Response is not valid JSON")
    
    assert compare_order_data(order_payload, response_data)


def test_delete_store_order():
    """Test DELETE request to delete a store order"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{ORDER_ID}"
    
    # Create an order to delete
    order_payload["id"] = ORDER_ID
    create_order(order_payload)
    
    # Verify the order exists
    get_response = requests.get(url, headers=DEFAULT_HEADERS)
    assert get_response.status_code == 200
    
    # Delete the order
    delete_response = requests.delete(url, headers=DEFAULT_HEADERS)
    assert delete_response.status_code == 200
    
    # Verify the order was deleted
    get_response_after_delete = requests.get(url, headers=DEFAULT_HEADERS)
    assert get_response_after_delete.status_code == 404
   