package com.rockthejvm.practice

import org.example.com.rockthejvm.practice.Color
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotSame
import kotlin.test.assertSame
import kotlin.test.assertTrue

class ColorTestingDemo {

    /*
        Absolute basic testing style -
        Unit testing,
        Integration Testing
            End to End testing
     */

    private val black = Color(0,0,0)
    private val red = Color(255, 0 , 0)
    private val green = Color(0, 255, 0)
    private val blue = Color(0,0,255)
    private val yellow = Color(255, 255, 0)

    // initialieze this before every test
    private lateinit var colors: List<Color>

    @BeforeEach
    fun individualSetUp(){
        println("Individual Setup")
        colors = listOf(red, green , blue, yellow)
    }

    @AfterEach
    fun individualCleanUp(){
        println(" Individual test clean up")
        colors = listOf()
    }

    @Test
    fun testCombine(){
        println("Testing the plus operator on colors")
        assertTrue {
            red + green == yellow
        }

        // testing lib has assertions
        // equality
        assertEquals(red + green, yellow, "Operator + should combine color channels ")
        // "sameness = testing the same INSTANCE ( reference equality )
        assertSame(red + green, yellow)
        // negative assertions: notSame, notContains
        assertNotSame(red + green, yellow)
        // assertContains, whether a collection contains an element
        // assertThrows: to test whether the code throws an expected exception
        assertThrows<RuntimeException> {
            if( 42 > 0)
                throw RuntimeException("Some randome error")
        }

        // can fail the test
        if( red + green != yellow) {
            fail(" Failing the test")
        }

    }

    @Test
    fun simpleTest(){
        println("Simple Test")
    }

    companion object {
        // "static" functions
        @BeforeAll  // compiles the static function into bytecode
        @JvmStatic
        fun suiteSetup(){   //will run before any function in the suite
            println("Suite setting up")
        }

        @AfterAll
        @JvmStatic
        fun suiteCleanUp(){
            println(" Suite clean up")
        }
    }
}