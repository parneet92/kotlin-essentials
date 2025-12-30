package org.example.com.rockthejvm.oopfun

// Useful to create wrappers around types
object ValueClasses {

    // In usual cases
    // wrapper types ("boxing")
    // value class
    // downside = memory overhead when we use data class
    data class ProductName(val name: String)
    data class ProductDescription(val description: String)

    // To avoid the memory overhead , Kotlin provides "value class"
    // JvmInline do NOT do any BOXING
    @JvmInline value class ProductName1(val name: String)
    @JvmInline value class ProductDescription1(val description: String)
    //^^^^^^^ necessary if the compile target is the JVM

    data class Product(val name: ProductName, val description: ProductDescription)  // other fields

    val kotlinCourse = Product(
        ProductName("Kotlin Essentials"),
        ProductDescription("Learn Kotlin effectively")
        // 37 other properties
    )

    @JvmStatic
    fun main(args: Array<String>) {

    }
}