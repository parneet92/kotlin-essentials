package org.example.com.rockthejvm.oop

object Inheritance {

    open class Animal { // class can be inherited
        open fun eat(){
            println("I am eating Naf, Naf !!")
        }
    }

    class Dog: Animal() {
        // Dog extends/inherits from Animal
        // Dog is a subtype of animal
        // Dog IS AN ANIMAL

        // method eat is available

        // override = change behavior of the eat method
        override fun eat() {
            // super.eat()     // calls the eat method in the parent class
            println("I am a dog! I chew things !!")
        }
    }

    val lassie = Dog()
    val anAnimal: Animal = Dog()

    // restrictions
    // need to provide constructor for the parent class
    open class Person(open val name: String, open val age: Int)
    class Adult(override val name: String, override val age: Int, idCard: String): Person(name, age)

    // restrict inheritance with the final keyword
    open class Travel(val destination: String){
        final fun confirm(): String = "Congrats! You're going to $destination"  // restricts overriding
        // final cannot be overriden
    }

    open class Leisure{
        open fun confirmExperience(): String = "Chill"
    }

    open class Travel_v2(val destination: String): Leisure() {
        final override fun confirmExperience(): String =
            "Congrats! You're going to $destination"
    }

    class SpecialTickets: Travel_v2("USA"){
        /*override fun confirmExperience(): String {
            return super.confirmExperience()        // overriding stop at Travel_v2
        }*/
    }

    //sealing a type hierarchy = restricts inheritance to this FILE only
    sealed class ProtocolMessage(contents: String)  // automatically open
    class BeginnerExchange(flag: String, contents: String): ProtocolMessage(contents)
    class Exchange(sender: String, receiver: String, contents: String): ProtocolMessage(contents)
    object EndExchange: ProtocolMessage("")
    // no other subtypes of protocol message may exist outside this file

    /*
    Any is the supertype of all types in Kotlin.
        Similarly, Any? is the supertype of all Nullable types
    Nothing is the last type in chain for all the types in Kotlin.
        Similarly, Nothing? is the last type for the nullable types in Kotlin
     */

    val nothing: Nothing = throw RuntimeException("Nothing")

    @JvmStatic
    fun main(args: Array<String>) {
        lassie.eat()
        anAnimal.eat()
    }
}