package org.example.com.rockthejvm.oop

object AbstractClassesInterfaces {

    abstract class Plant(scientificName: String) {  // class with possibly abstract properties and/or methods
        abstract val maxHeight: Int     // property with no value
        abstract fun grow(): String     // method signature with no Impl
        // can define "regular" properties/methods
        val growthMechanism: String = "photosynthesis"
    }

    // val myPlant = Plant("rose")  // abstract classes cannot be instantiated

    class Strawberry: Plant("fragaria") {
        // implement all abstract properties/methods
        override val maxHeight: Int = 100
        override fun grow(): String = "nice tasty strawberries"

    }

    // interfaces = ultimate abstract types
    interface Carnivore {
        //can define properties/methods - automatically abstract & open
        fun eat(animal: Inheritance.Animal): String = "eating this poor fella"
        val preferredMeal: String
            get() = "meat"  // may only provide an impl if the property has no backing field, and with get()/set() only
        // can provide concrete properties/methods = "default implementation"
    }

    interface Herbivore {
        fun eat(plant: Plant): String
    }

    // inheritance model in kotlin: extend ONE class, but maybe multiple interfaces
    class Crocodile: Inheritance.Animal(), Carnivore {
        override val preferredMeal: String = "gazelle"
        override fun eat(animal: Inheritance.Animal): String = "crunching this poor thing in one bite"
    }

    class Human: Carnivore, Herbivore {
        override val preferredMeal: String = "Sugar"
        override fun eat(animal: Inheritance.Animal): String = "hopefully this makes a good steak"
        override fun eat(plant: Plant): String  = "eating this plant"

    }

    // an interface can extend another interface
    interface Omnivore: Carnivore, Herbivore
    // a class can just extend an interface
    abstract class Human_v2: Omnivore
    // what if 2 interfaces have the same method

    interface Instrument{
        fun play(): String
    }

    interface Game {
        fun play(): String
    }

    class GuitarApp: Instrument, Game {
        override fun play(): String = "both an instrument and a game"
    }

    @JvmStatic
    fun main(args: Array<String>) {

    }
}