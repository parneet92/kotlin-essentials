package org.example.com.rockthejvm.oopfun

object DataClasses {

    class CityNaive(val name: String, val country: String, val population: Int){
        // overriding hashCode, toSTring, equals
    }

    // data class - The CHOICE for storing "information" in classes
    // compiler will generate the implementation for hasCode, toString, equals
    // copy
    // destructuring - componentN functions made by the compiler
    // restriction: all the constructor args must be properties
    // restriction: must have at least one property
    // cannot inherit a data class ( FINAL by default )
    data class City(val name: String, val country: String, val population: Int){
        // new properties, methods, ....
    }

    // meant to be passed around and stored

    // Hardly gets used, more like a formality
    data object NoOperation


    @JvmStatic
    fun main(args: Array<String>) {
        val bucharest = City("BUcharest", "Romania", 2000000)
        val bucharest_v2 = City("Bucharest", "Romania", 2000000)
        val grownBucharest = bucharest_v2.copy(population = 2500000)

        println(bucharest_v2)  // bucharest.toString
        println(bucharest == bucharest_v2)
        println(grownBucharest)
        //destructuring
        /*
        val name = bucharest.component1()
        val country = bucharest.component2()
        val pop = bucharest.component3()
         */
        val (name, country, pop) = bucharest
        println("$name, $country, $pop")
    }
}