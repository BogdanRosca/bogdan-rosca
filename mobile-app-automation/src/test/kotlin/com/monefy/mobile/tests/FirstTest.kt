package com.monefy.mobile.tests

import org.testng.annotations.Test

class FirstTest {
    
    @Test
    fun testHelloWorld() {
        val expected = "Monefy"
        val actual = "Monefy"

        println("First mobile automation test!")
        
        assert(actual == expected) { "Names should match!" }
    }
} 