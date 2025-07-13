"""
Configuration file for API test framework
"""

# Base URLs for different environments
BASE_URLS = {
    'dev': 'https://api-dev.example.com',
    'staging': 'https://api-staging.example.com',
    'prod': 'https://api.example.com'
}

# Default environment
DEFAULT_ENV = 'dev'

# API endpoints
ENDPOINTS = {
    'users': '/users',
    'posts': '/posts',
    'comments': '/comments',
    'auth': '/auth'
}

# Request headers
DEFAULT_HEADERS = {
    'Content-Type': 'application/json',
    'Accept': 'application/json'
}