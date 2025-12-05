package org.example.com.rockthejvm.oopfun

object SpecialMethods {

    // special methods in java/jvm - equals, hashCode, toString
    class Person(val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean = when(other) {
            is Person -> name == other.name && age == other.age
            else -> false
        }

        /*
            a "unique" number for this instance
            IMPORTANT: 2 "equal" instances should produce the same hashcode
            used in hash based data structures - sets, maps
         */
        override fun hashCode(): Int =
            name.hashCode() * 31 + age

        override fun toString(): String =
            "Person($name, $age"

        // infix methods only work for methods with 1 arguments
        infix fun likes(movie: String) =
            "$name says: I LOVE $movie!"
    }

    // infix methods - syntax sugar

    @JvmStatic
    fun main(args: Array<String>) {
        val person1 = Person("Alice", 35)
        val person2 = Person("Alice", 35)

        println(person1 == person2) // person1.equals(person2)
        println(person2 likes "Forrest Gump")
    }
}