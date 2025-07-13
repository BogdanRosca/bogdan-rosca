from config.config import BASE_URLS, DEFAULT_HEADERS, ORDER_ID
#from fixtures.fixtures import cleanup_order
from utils.test_utils import create_order, compare_order_data, delete_order
import json
import os
import pytest
import requests


ENVIRONMENT = os.getenv('TEST_ENV', 'dev')


class TestStoreOrders:
    """Test suite for store order operations"""
    
    @pytest.fixture(autouse=True)
    def setup_order_data(self):
        """Load order test data for all tests"""
        with open('data/order.json') as f:
            self.order_payload = json.load(f)
            self.order_payload["id"] = ORDER_ID
    
    def test_get_store_order_with_invalid_id(self):
        """Test GET request to retrieve a store order with invalid ID"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/order/123123123"
        
        response = requests.get(url, headers=DEFAULT_HEADERS)
        
        assert response.status_code == 404
        assert 'Order not found' in response.text 

    def test_get_store_order_with_valid_id(self):
        """Test GET request to retrieve a store order"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{ORDER_ID}"
        
        create_order(self.order_payload)

        response = requests.get(url, headers=DEFAULT_HEADERS)
        assert response.status_code == 200
        
        try:
            response_data = response.json()
        except json.JSONDecodeError:
            pytest.fail("Response is not valid JSON")
        
        assert compare_order_data(self.order_payload, response_data)

    def test_create_store_order(self):
        """Test POST request to create a store order"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/order"
        
        response = requests.post(url, headers=DEFAULT_HEADERS, json=self.order_payload)
        assert response.status_code == 200
        
        try:
            response_data = response.json()
        except json.JSONDecodeError:
            pytest.fail("Response is not valid JSON")
        
        assert compare_order_data(self.order_payload, response_data)


    def test_update_existing_store_order(self):
        """Test POST request to update an existing store order"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/order"
        
        response = requests.post(url, headers=DEFAULT_HEADERS, json=self.order_payload)
        assert response.status_code == 200
        
        try:
            response_data = response.json()
        except json.JSONDecodeError:
            pytest.fail("Response is not valid JSON")
        
        assert compare_order_data(self.order_payload, response_data)

        updated_order = self.order_payload.copy()
        updated_order["status"] = "delivered"
        create_order(updated_order)
        
        response = requests.post(url, headers=DEFAULT_HEADERS, json=updated_order)
        assert response.status_code == 200
        
        try:
            response_data = response.json()
        except json.JSONDecodeError:
            pytest.fail("Response is not valid JSON")
        
        assert compare_order_data(updated_order, response_data)


    def test_delete_store_order(self):
        """Test DELETE request to delete a store order"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/order/{ORDER_ID}"
        
        # Create an order to delete
        create_order(self.order_payload)
        
        # Verify the order exists
        get_response = requests.get(url, headers=DEFAULT_HEADERS)
        assert get_response.status_code == 200
        
        # Delete the order
        delete_response = requests.delete(url, headers=DEFAULT_HEADERS)
        assert delete_response.status_code == 200
        
        # Verify the order was deleted
        get_response_after_delete = requests.get(url, headers=DEFAULT_HEADERS)
        assert get_response_after_delete.status_code == 404
   

class TestStoreInventory:
    """Test suite for store order operations"""

    ORDER_QUANTIY = 8

    @pytest.fixture(autouse=True)
    def setup_order_data(self):
        """Load order test data for all tests"""
        with open('data/inventory.json') as f:
            self.initial_inventory = json.load(f)

        """Load order test data for all tests"""
        with open('data/order.json') as f:
            self.order_payload = json.load(f)
            self.order_payload["id"] = ORDER_ID
            
        
    def test_get_initial_store_inventory(self):
        """Test GET request to retrieve the store initial inventory"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/inventory"
        
        response = requests.get(url, headers=DEFAULT_HEADERS)
        response_data = response.json()
        
        assert response.status_code == 200
        assert self.initial_inventory == response_data

    @pytest.mark.parametrize("order_satus", ["approved", "placed", "delivered"])
    def test_inventory_updates_on_order_creation(self, order_satus):
        """Test GET request to retrieve the store inventory after order creation with different quantities"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/inventory"
        
        new_order = self.order_payload.copy()
        new_order["quantity"] = self.ORDER_QUANTIY
        new_order["status"] = order_satus
        create_order(new_order)
        
        updated_inventory = self.initial_inventory.copy()
        updated_inventory[order_satus] += self.ORDER_QUANTIY

        response = requests.get(url, headers=DEFAULT_HEADERS)
        response_data = response.json()
        
        assert response.status_code == 200
        assert response_data == updated_inventory

   
    @pytest.mark.parametrize("order_satus", ["approved", "placed", "delivered"])
    def test_inventory_updates_on_order_deletion(self, order_satus):
        """Test GET request to retrieve the store inventory after order creation with different quantities"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/inventory"
        
        new_order = self.order_payload.copy()
        new_order["quantity"] = self.ORDER_QUANTIY
        new_order["status"] = order_satus
        create_order(new_order)
        
        updated_inventory = self.initial_inventory.copy()
        updated_inventory[order_satus] += self.ORDER_QUANTIY

        response = requests.get(url, headers=DEFAULT_HEADERS)
        response_data = response.json()
        
        assert response.status_code == 200
        assert response_data == updated_inventory

        delete_order(new_order["id"])

        response = requests.get(url, headers=DEFAULT_HEADERS)
        response_data = response.json()

        assert response.status_code == 200
        assert response_data == self.initial_inventory


    def test_inventory_updates_on_order_overwrite(self):
        """Test GET request to retrieve the store inventory after order creation with different quantities"""
        url = f"{BASE_URLS[ENVIRONMENT]}/store/inventory"
        
        new_order = self.order_payload.copy()
        new_order["quantity"] = self.ORDER_QUANTIY
        create_order(new_order)
        
        inventory_afrer_first_order = self.initial_inventory.copy()
        inventory_afrer_first_order["approved"] += self.ORDER_QUANTIY

        response = requests.get(url, headers=DEFAULT_HEADERS)
        response_data = response.json()
        
        assert response.status_code == 200
        assert response_data == inventory_afrer_first_order

        updated_order = new_order
        updated_order["status"] = "delivered"

        create_order(updated_order)

        inventory_afrer_order_update = self.initial_inventory.copy()
        inventory_afrer_order_update["delivered"] += self.ORDER_QUANTIY

        response = requests.get(url, headers=DEFAULT_HEADERS)
        response_data = response.json()

        assert response.status_code == 200
        assert response_data == inventory_afrer_order_update