"""
Test suite for store order operations
"""
from tests.base_test import BaseTest
import utils.store_order_utils as order_utils

class TestOrderOperations(BaseTest):
    """Test suite for basic order CRUD operations"""
    
    def test_create_order_successfully(self):
        """Test successful order creation"""
        response = order_utils.post_order(self.order_payload)
        order_utils.assert_order_created_successfully(response, self.order_payload)
    
    def test_get_existing_order(self):
        """Test retrieving an existing order"""
        order_utils.create_order(self.order_payload)
        response = order_utils.get_order(self.order_id)
        
        assert response.status_code == 200
        response_data = response.json()
        assert response_data["id"] == self.order_id
    
    def test_update_existing_order(self):
        """Test updating an existing order"""
        # Create initial order
        order_utils.create_order(self.order_payload)
        
        # Update order status
        updated_order = self.create_test_order(status="delivered")
        response = order_utils.post_order(updated_order)
        
        order_utils.assert_order_created_successfully(response, updated_order)
    
    def test_delete_order_successfully(self):
        """Test successful order deletion"""
        # Create order to delete
        order_utils.create_order(self.order_payload)
        
        # Verify order exists
        get_response = order_utils.get_order(self.order_id)
        assert get_response.status_code == 200
        
        # Delete order
        delete_response = order_utils.delete_order(self.order_id)
        assert delete_response.status_code == 200
        
        # Verify order was deleted
        get_response_after_delete = order_utils.get_order(self.order_id)
        order_utils.assert_order_not_found(get_response_after_delete)


class TestOrderValidation(BaseTest):
    """Test suite for order validation and error handling"""
    
    def test_get_nonexistent_order(self):
        """Test retrieving a non-existent order"""
        response = order_utils.get_order(123123123)
        order_utils.assert_order_not_found(response)
    
    def test_create_order_without_body(self):
        """Test creating order without request body"""
        response = order_utils.post_order(None)

        assert response.status_code == 400
        assert response.text == "No Order provided. Try again?"
    
    def test_create_order_with_missing_quantity(self):
        """Test creating order with missing required field"""
        invalid_order = self.order_payload.copy()
        invalid_order.pop("quantity")
        
        response = order_utils.post_order(invalid_order)
        assert response.status_code == 405 