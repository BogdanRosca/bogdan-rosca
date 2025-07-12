package com.monefy.mobile.utils

data class Transaction(
    val amount: Double,
    val paymentType: String,
    val category: String,
    val note: String = "",
    val date: String = DateUtils.getCurrentDateFormatted()
)