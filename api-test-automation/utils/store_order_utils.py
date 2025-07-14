from config.config import BASE_URLS, DEFAULT_HEADERS
from utils.test_utils import return_json_response
import os
import requests


ENVIRONMENT = os.getenv('TEST_ENV', 'dev')

def create_order(order_payload):
    """Helper function to create an order with the provided payload"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order"

    response = requests.post(url, headers=DEFAULT_HEADERS, json=order_payload)
    response_data = response.json()
    
    assert response.status_code == 200
    assert response_data["id"] == order_payload["id"]


def delete_order(order_id):
    """Helper function to delete an order with the provided ID"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{order_id}"

    response = requests.delete(url, headers=DEFAULT_HEADERS)
    
    return response


def compare_order_data(expected, actual):
    """Helper function to compare order data excluding shipDate field"""
    expected_without_date = {k: v for k, v in expected.items() if k != 'shipDate'}
    actual_without_date = {k: v for k, v in actual.items() if k != 'shipDate'}
    return expected_without_date == actual_without_date



def get_order(order_id):
    """Get order by ID"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{order_id}"
    return requests.get(url, headers=DEFAULT_HEADERS)


def post_order(order_payload):
    """Create or update order"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order"
    return requests.post(url, headers=DEFAULT_HEADERS, json=order_payload)


def build_order_payload(order_id, quantity=7, status="approved", pet_id=198772):
    """Build order payload with default values"""
    return {
        "complete": True,
        "id": order_id,
        "petId": pet_id,
        "quantity": quantity,
        "shipDate": "2025-07-13T12:05:55.116Z",
        "status": status
    }


def assert_order_created_successfully(response, expected_payload):
    """Assert order creation was successful"""
    assert response.status_code == 200
    response_data = return_json_response(response)
    assert compare_order_data(expected_payload, response_data)


def assert_order_not_found(response):
    """Assert order was not found"""
    assert response.status_code == 404
    assert 'Order not found' in response.text
