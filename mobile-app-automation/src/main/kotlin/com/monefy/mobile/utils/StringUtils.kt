package com.monefy.mobile.utils

class StringUtils() {
    fun formatBalance(balance: Double): String {
        if (balance < 0) {
            return String.format("-$%,.2f", -balance)
        } else {
            return String.format("$%,.2f", balance)
        }
    }
} 