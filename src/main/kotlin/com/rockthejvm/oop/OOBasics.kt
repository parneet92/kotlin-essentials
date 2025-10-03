package org.example.com.rockthejvm.oop

class Person(val firstName: String, val lastName: String, age: Int) {

    // can define PROPERTIES ( data = val, vars) and METHODS(behavior = functions )
    val fullName = "$firstName $lastName"   // -- PROPERTY

    fun greet() = "Hi my name is $firstName"    // METHOD

    // OVERLOADING = multiple methods with the same name and different signatures
    fun greet(firstName: String): String =
        "Hi $firstName, my name is ${this.firstName} , how dodood??"

    // secondary overloaded constructor
    // Must always invoke the other constructor
    constructor(firstName: String, lastName: String): this(firstName, lastName, 0)
    constructor(): this("Jane", "Doe")

}

fun main() {
    val daniel = Person("Daniel", "Koorg", 34)
    val danielFullName = daniel.fullName
    println(danielFullName)
    println(daniel.greet())
    println(daniel.greet("Anna"))
    val simplePerson = Person()
    println(simplePerson.fullName)
}