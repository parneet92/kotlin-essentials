package org.example.com.rockthejvm.oop

object Nullables {

    class Developer(val name: String, val favLang: String) {
        fun writeCode(code: String = ""){
            println("$name writing code in $favLang: $code")
        }
    }

    // null = no value
    // Developer daniel = null; // possible in other languages
    // val daniel: Developer = Null // Not possible in Kotlin
    //NPE

    val mayBeDeveloper: Developer? = null   // possible
    //                  ^^^^^^^^^^ nullable type

    fun createDeveloper(name: String) =
        if(name.isNotEmpty()) Developer(name, "Kotlin")
        else null


    val mayBeDeveloper_v2: Developer? = createDeveloper("Master Yoda")
    // Once you have a nullable, you cannot access properties or methods
    // val masterYoda = mayBeDeveloper_v2.name

    fun makeDevWriteCode(dev: Developer?, code: String) =
        if(dev != null) dev.writeCode(code)
        // ^ on this branch the compiler knows that the value is not null -> can use properties/methods
        else println("Error, Developer is Null")

    // If you know that nullable is not null, then you can force value to be of concrete type
    val masterYoda = mayBeDeveloper_v2!!    // type is now concrete
    // if you are wrong then it will crash with NPE
    // do not use !! unless you really know what you're doing

    // safe call operator ?. + property or methods
    val maybeName: String? = mayBeDeveloper?.name

    val definitiveDeveloper: Developer = mayBeDeveloper?: Developer("John Doe", "Cobol")
    //                                                  ^ Elvis Operator

    // side not : safe casting
    val something: Any = 42
    // if you know you have an Int, you can cast it down
    val anInt = something as Int    // crashes if you are wrong
    val mayBeInt = something as? Int    // does not crash but returns nullable


    @JvmStatic
    fun main(args: Array<String>) {

    }
}