from config.config import BASE_URLS, DEFAULT_HEADERS
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
    """Helper function to create an order with the provided payload"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{order_id}"

    response = requests.delete(url, headers=DEFAULT_HEADERS)
    
    assert response.status_code == 200


def compare_order_data(expected, actual):
    """Helper function to compare order data excluding shipDate field"""
    expected_without_date = {k: v for k, v in expected.items() if k != 'shipDate'}
    actual_without_date = {k: v for k, v in actual.items() if k != 'shipDate'}
    return expected_without_date == actual_without_date
