import pytest
import requests
import json
from config.config import DEFAULT_HEADERS


ORDER_ID = 1234567890
payload = {
    "id": ORDER_ID,
    "petId": 198772,
    "quantity": 7,
    "shipDate": "2025-07-13T12:05:55.116Z",
    "status": "approved",
    "complete": True
}

def create_order():
    url = "http://localhost:8080/api/v3/store/order"
    response = requests.post(url, headers=DEFAULT_HEADERS, json=payload)
    response_data = response.json()
    assert response.status_code == 200
    assert response_data["id"] == ORDER_ID


@pytest.fixture(autouse=True)
def cleanup_order():
    """Fixture to delete the test order before each test"""

    url = f"http://localhost:8080/api/v3/store/order/{ORDER_ID}"
    
    try:
        requests.delete(url, headers=DEFAULT_HEADERS)
    except requests.RequestException:
        pass
    
    yield
    
    try:
        requests.delete(url, headers=DEFAULT_HEADERS)
    except requests.RequestException:
        pass


def test_get_store_order_with_invalid_id():
    """Test GET request to retrieve a store order"""
    url = "http://localhost:8080/api/v3/store/order/10"
    
    
    response = requests.post(url, headers=DEFAULT_HEADERS, json=payload)
    response_data = response.json()

    response = requests.get(url, headers=DEFAULT_HEADERS)
    
    assert response.status_code == 404
    assert 'Order not found' in response.text 


def test_create_store_order():
    """Test POST request to create a store order"""
    url = "http://localhost:8080/api/v3/store/order"
    
    response = requests.post(url, headers=DEFAULT_HEADERS, json=payload)
    response_data = response.json()

    assert response.status_code == 200
    payload_without_date = {k: v for k, v in payload.items() if k != 'shipDate'}
    response_without_date = {k: v for k, v in response_data.items() if k != 'shipDate'}
    
    assert response_without_date == payload_without_date


def test_get_store_order_with_valid_id():
    """Test GET request to retrieve a store order"""
    url = f"http://localhost:8080/api/v3/store/order/{ORDER_ID}"
    
    create_order()

    response = requests.get(url, headers=DEFAULT_HEADERS)
    response_data = response.json()
    
    assert response.status_code == 200
    payload_without_date = {k: v for k, v in payload.items() if k != 'shipDate'}
    response_without_date = {k: v for k, v in response_data.items() if k != 'shipDate'}
    
    assert response_without_date == payload_without_date


def test_delete_store_order():
    """Test DELETE request to delete a store order"""
    url = f"http://localhost:8080/api/v3/store/order/{ORDER_ID}"
    
    response = requests.delete(url, headers=DEFAULT_HEADERS)
    
    assert response.status_code == 200
   