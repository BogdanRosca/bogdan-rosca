"""
Test suite for store inventory operations
"""
from tests.base_test import BaseTest
from utils.store_inventory_utils import get_inventory, delete_inventory
from utils.test_utils import return_json_response
import pytest
import utils.store_order_utils as order_utils


class TestInventoryOperations(BaseTest):
    """Test suite for inventory CRUD operations"""
    
    ORDER_QUANTITY = 8
    
    def test_get_initial_inventory(self):
        """Test retrieving initial store inventory"""
        response = get_inventory()
        
        # Check initial inventory
        assert response.status_code == 200
        response_data = return_json_response(response)
        assert response_data == self.initial_inventory
    
    def test_inventory_deletion_not_allowed(self):
        """Test that inventory deletion is not allowed"""
        response = delete_inventory()
        
        # Check that deletion is not allowed
        assert response.status_code == 405
        response_data = return_json_response(response)
        assert response_data["message"] == "HTTP 405 Method Not Allowed"
        assert response_data["code"] == 405


class TestInventoryUpdates(BaseTest):
    """Test suite for inventory update scenarios"""
    
    ORDER_QUANTITY = 8
    
    @pytest.mark.parametrize("order_status", ["approved", "placed", "delivered"])
    def test_inventory_updates_on_order_creation(self, order_status):
        """Test inventory updates when creating orders with different statuses"""
        # Create order with specific status
        new_order = self.create_test_order(
            quantity=self.ORDER_QUANTITY, 
            status=order_status
        )
        order_utils.create_order(new_order)
        
        # Get updated inventory
        response = get_inventory()
        
        # Check quantity was updated
        assert response.status_code == 200
        response_data = return_json_response(response)
        expected_inventory = self.get_expected_inventory_after_order(
            order_status, self.ORDER_QUANTITY
        )
        assert response_data == expected_inventory
    
    @pytest.mark.parametrize("order_status", ["approved", "placed", "delivered"])
    def test_inventory_updates_on_order_deletion(self, order_status):
        """Test inventory updates when deleting orders"""
        # Create order
        new_order = self.create_test_order(
            quantity=self.ORDER_QUANTITY, 
            status=order_status
        )
        order_utils.create_order(new_order)
        
        # Verify inventory was updated
        response = get_inventory()
        response_data = return_json_response(response)
        expected_inventory = self.get_expected_inventory_after_order(
            order_status, self.ORDER_QUANTITY
        )
        assert response_data == expected_inventory
        
        # Delete order
        delete_response = order_utils.delete_order(new_order["id"])
        assert delete_response.status_code == 200

        # Verify inventory returned to initial state
        response = get_inventory()
        response_data = return_json_response(response)
        assert response_data == self.initial_inventory
    
    def test_inventory_updates_on_order_status_change(self):
        """Test inventory updates when changing order status"""
        # Create initial order
        new_order = self.create_test_order(quantity=self.ORDER_QUANTITY)
        order_utils.create_order(new_order)
        
        # Verify inventory after first order
        response = get_inventory()
        response_data = return_json_response(response)
        expected_inventory = self.get_expected_inventory_after_order(
            "approved", self.ORDER_QUANTITY
        )
        assert response_data == expected_inventory
        
        # Update order status to delivered
        updated_order = self.create_test_order(
            quantity=self.ORDER_QUANTITY, 
            status="delivered"
        )
        order_utils.create_order(updated_order)
        
        # Verify inventory after status change
        response = get_inventory()
        response_data = return_json_response(response)
        expected_inventory = self.get_expected_inventory_after_order(
            "delivered", self.ORDER_QUANTITY
        )
        assert response_data == expected_inventory 