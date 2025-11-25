package org.example.com.rockthejvm.oop

import kotlin.jvm.Throws

object Exceptions {

    fun mayBeString(): String? = null

    fun demoExceptions(){
        // code might fail
        // val divison = 42 / 0    // Arithmetic Exception

        // throws NullPointerException
        val nullable: String? = mayBeString()
        val theString = nullable!!
    }

    class Person private constructor(val name: String, val age: Int){
        companion object {
            @Throws(IllegalArgumentException::class)    // can specify exceptions thrown, just a hint
            fun create(name: String, age: Int): Person {
                if( age < 0 ) throw IllegalArgumentException("Age must be Non Negative")
                else return Person(name, age)
            }
        }
    }

    /*
        Throwable
            - Exception - something wrong with the logic, we can control
                - IO Exception, FileNotFoundException, ... ( checked exceptions )
                - RuntimeExceptions ( unchecked exceptions )
                    - NullPointerExceptions, IllegalArgumentExceptions, ...
                - In Kotlin all exceptions are "unchecked", meaning we don't have to catch them when the underlying code
                is throwing the exceptions
            - Error -  something wrong with the jvm
                - StackOverflow error
                - OutofMemoryError


     */

    class MyException(val count: Int): RuntimeException("Something Wrong") {
        // properties, methods
    }


    @JvmStatic
    fun main(args: Array<String>) {
        // catching exceptions
        val mayBePerson: Person = try {
            Person.create("Daniel", -10)
        } catch (e: NullPointerException) {
            Person.create("Daniel", 100)
        } catch (e: IllegalArgumentException){
            Person.create("Daniel", 99)
        } finally {
            // runs no matter of what
            // Release of resources
            println(" Something needs to be released no matter what!! ")
        }
    }
}