from config.config import BASE_URLS, DEFAULT_HEADERS
import os
import requests

ENVIRONMENT = os.getenv('TEST_ENV', 'dev')


def get_inventory():
    """Get store inventory"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/inventory"
    return requests.get(url, headers=DEFAULT_HEADERS)


def delete_inventory():
    """Delete store inventory (should return 405)"""
    url = f"{BASE_URLS[ENVIRONMENT]}/store/inventory"
    return requests.delete(url, headers=DEFAULT_HEADERS)


def assert_inventory_updated(initial_inventory, order_status, order_quantity):
    """Assert inventory was updated correctly"""
    updated_inventory = initial_inventory.copy()
    updated_inventory[order_status] += order_quantity
    return updated_inventory
