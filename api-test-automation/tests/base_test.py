"""
Base test class providing common functionality for all test classes
"""
import pytest
from config.config import ORDER_ID
from utils.test_utils import load_test_data
from utils.store_order_utils import build_order_payload


class BaseTest:
    """Base class for all test classes with common setup and utilities"""
    
    @pytest.fixture(autouse=True)
    def setup_test_data(self):
        """Load common test data for all tests"""
        self.order_id = ORDER_ID
        self.order_payload = build_order_payload(self.order_id)
        self.initial_inventory = load_test_data('inventory.json')
        
    def create_test_order(self, order_id=None, quantity=7, status="approved"):
        """Create a test order with specified parameters"""
        if order_id is None:
            order_id = self.order_id
        return build_order_payload(order_id, quantity, status)
    
    def get_expected_inventory_after_order(self, order_status, order_quantity):
        """Calculate expected inventory after order creation"""
        expected_inventory = self.initial_inventory.copy()
        expected_inventory[order_status] += order_quantity
        return expected_inventory 