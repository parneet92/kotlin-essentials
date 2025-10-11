package org.example.com.rockthejvm.oop

// object in Kotlin = definition of a type + the only instance of the type
// Singleton pattern = single e.g. service, connection, data source, state

object MySingleton {    // type + the only instance of the type
    // add properties and methods
    val aProperty = 42
    fun aMethod(arg: Int): Int {
        println("Hello from Singleton object: $arg")
        return arg + aProperty
    }

    // define entry points to your kotlin application
    // public static void main(String[] args) == equivalent Java syntax
    @JvmStatic
    fun main(args: Array<String>) {
        println("Singleton Entry point")
    }
}

object ObjectsCompanions{
    // companion objects
    class Guitar(val nStrings: Int, val type: String) {
        //properties and methods
        fun play(){
            println("$type guitar with $nStrings strings playing!!!!")
        }

        companion object {
            // properties and methods specific to the type
            val HAS_STRING = true
            fun createSimpleGuitar(type: String): Guitar = Guitar(6, type)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val gibson = Guitar(6, "Electric")
        gibson.play()

        val guitarHasStrings = Guitar.HAS_STRING
    }
}

fun main(){
    val theSingleton = MySingleton
    val anotherSingleton = MySingleton
    println(theSingleton == anotherSingleton)
    val result = MySingleton.aMethod(65)
    println("Result of singleton method : $result")
}