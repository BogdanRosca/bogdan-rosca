from config.config import BASE_URLS
from config.config import DEFAULT_HEADERS
import json
import os
import requests


ENVIRONMENT = os.getenv('TEST_ENV', 'dev')


def create_order(order_payload):
    url = f"{BASE_URLS[ENVIRONMENT]}/store/order"

    response = requests.post(url, headers=DEFAULT_HEADERS, json=order_payload)
    response_data = response.json()
    
    assert response.status_code == 200
    assert response_data["id"] == order_payload["id"]