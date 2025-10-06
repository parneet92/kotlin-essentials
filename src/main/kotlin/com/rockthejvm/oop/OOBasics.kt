package org.example.com.rockthejvm.oop

class Person(val firstName: String, val lastName: String, age: Int) {

    init {
        // run arbitrary code when this class is initialized
        println("Initializing a person with name $firstName $lastName")
    }

    // can run multiple init blocks, they run one after another in the order they're defined in the class
    init {
        println("some arbitrary code")
    }

    // can define PROPERTIES ( data = val, vars) and METHODS(behavior = functions )
    val fullName = "$firstName $lastName"   // -- PROPERTY

    var favMovie: String = " Forrest Gump" //{It gives no backing field error when we initialize it here}
        // no backing field = no memory zone for this var
        get() = field
        set(value: String) {
            // run any code here
            println("Setting the value of fav movie to $value")
            field = value    // set value
        }

    /*
    Properties with get() and set(value) may or may not have backing field
    Create a backing field by simply using `field` in the implementation of get() or set(value)
    The compiler detects if you have backing field or not
    - If you have a backing field - you must initialize the property
    - If you don't have a backing field, you cannot initialize the property
     */
    // read("get"), write("set")
    /*fun getFavMovie(): String = favMovie
    fun setFacMovie(value: String) {
        favMovie = value
    }*/

    // initialization
    lateinit var favLanguage: String
    fun initializeFavLanguage() {
        if(!this::favLanguage.isInitialized)
            favLanguage = "Kotlin"
    }

    fun greet() = "Hi my name is $firstName"    // METHOD

    // OVERLOADING = multiple methods with the same name and different signatures
    fun greet(firstName: String): String =
        "Hi $firstName, my name is ${this.firstName} , how dodood??"

    // secondary overloaded constructor
    // Must always invoke the other constructor
    constructor(firstName: String, lastName: String): this(firstName, lastName, 0)
    constructor(): this("Jane", "Doe")

}

// immutable = data cannot be changed, must create another instance
// mutable = data can be changed without allocating another instance

fun main() {
    val daniel = Person("Daniel", "Koorg", 34)
    val danielFullName = daniel.fullName
    println(danielFullName)
    println(daniel.greet())
    println(daniel.greet("Anna"))
    val simplePerson = Person()
    println(simplePerson.fullName)

    // get and set
    println("Getting and Setting")
    //println(daniel.getFavMovie())
    println(daniel.favMovie)    // calling the get method on fav movie property
    daniel.favMovie = "Mission Impossible"  // calling the set method on fav movie property
}