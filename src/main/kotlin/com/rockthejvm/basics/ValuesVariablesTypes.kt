package org.example.com.rockthejvm.basics

fun main(){
    // statically types language
    val meaningOfLife: Int = 42
    // val meaningOfLife: Int = 44 // val cannot be reassigned

    var objectiveInLife: Int = 32
    objectiveInLife = 34    // vars can be reassigned

    // type inference = compiler figure out the type from the RHS assignment
    val meaningOfLife_v2 = 45
    val meaningOfLife_v3 = 49

    // common types: Int, Short, Long, Float, Boolean, Char, Double, String, Byte
    val aBoolean: Boolean = false
    val aChar: Char = 'k'
    val aByte: Byte = 127   // -127 -127 1 Byte representation
    val aShort: Short = 7643    // 2 Bytes
    val aInt: Int = 32  // 4 bytes
    val aLong: Long = 76278L    // 8 bytes
    val aFloat: Float = 7.98f   // 4 bytes
    val aDouble: Double = 7673.98   // 8 bytes

    // string
    val aString: String = "heello there!!!"

}

// top level values = constants
const val appWideMoL: Int = 89  // computed first