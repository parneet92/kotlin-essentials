package org.example.com.rockthejvm.oopfun

object Anonymous {

    abstract class Plant {
        abstract fun grow(): String
    }

    class Rose(val color: String): Plant(){
        override fun grow(): String =
            "Pretty little rose grows with color - $color"
    }

    class Pepper(val spicyFactor: Int): Plant() {
        override fun grow(): String =
            "Peppers with spicy factor - $spicyFactor/10 grows here"

    }
    // a weird plant that grows only HERE in this context, so we only need ONE instance of it
    object WeirdPlant: Plant(){
        override fun grow(): String =
            "Some weird plant grows here that no one has seen before!"
    }

    val rose = Rose("Blue")
    val pepper = Pepper(6)
    //val plantCollection = listOf(rose, pepper, WeirdPlant)
    val weirdPlant = WeirdPlant
    val plantCollection = listOf(rose, pepper, weirdPlant)

    // To replace the unnecessary assignment of object to val to keep the code consistent,
    // we could use anonymous classes
    val weirdPlant1 = object: Plant() {     // anonymous class
        override fun grow(): String =
            "Some weird plant grows here that no one has seen before!"
    }

    // anonymous classes works with abstract class, open class, interfaces and whichever is inheritable


    @JvmStatic
    fun main(args: Array<String>) {

    }
}