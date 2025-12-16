package org.example.com.rockthejvm.oopfun

object Extensions {

    // We can add new methods and properties to the existing types using extensions
    // 3.multiply("Kotlin")
    fun Int.multiply(aString: String) : String {
        //^^^^^^^^^^extension method
        var result = ""
        for(i in 1 .. this)
            result += aString
        return result
    }

    // extension properties
    val Int.nDigits: Int    // extendion property ( We can't get the value directly as there is no backing field )
        get() {
            var result = 0
            var theNumber = this
            while(theNumber != 0){
                theNumber = theNumber / 10
                result++
            }
            return result
        }

    //restriction: can be shadowed(have the same signature) as a real method from the class
    //in this case real method is called
    class Person(val name: String) {
        fun greet() = "Hi everyone I am $name"
    }

    fun Person.greet() =
        //^^^^^^^^^^the receiver type
        "$name says I hate everyone!!"

    /*
    compiler makes new synthetic function(hidden)
    fun greet($this: Person): String = ....

     */
    @JvmStatic
    fun main(args: Array<String>) {
        val kotlinx3 = 3.multiply("Kotlin")
        println(kotlinx3)
    }
}