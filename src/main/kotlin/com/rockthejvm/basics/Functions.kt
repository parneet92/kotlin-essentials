package org.example.com.rockthejvm.basics

fun simpleFunction(arg: String): Unit{
    println("Just passed an argument : $arg")   // string template or interpolation
    // 1000 lines of code
}

fun printHello(){
    println("I am a simple no arg function")
}

fun concatenateString(aString: String, count: Int) : String {
    var result = ""
    for(i in 1 .. count)
        result += aString
    return result
}

// special syntax for single expression function
fun combineString(strA: String, strB: String) =
    "$strA --- $strB"

// recursion ( functional programming )
/*
    csr("Kotlin", 3) =
    "Kotlin" + csr("Kotlin", 2) = "Kotlin" + "KotlinKotlin" = "KotlinKotlinKotlin"

    csr("Kotlin", 2) =
    "Kotlin" + csr("Kotlin", 1) = "Kotlin" + "Kotlin" = "KotlinKotlin"

    csr("Kotlin", 1) =
    "Kotlin" + csr("Kotlin", 0) = "Kotlin" + "" = "Kotlin"

    csr("Kotlin",0) = ""
 */
fun concatenateStringRec(aString: String, count: Int): String =
    if (count <= 0) ""
    else aString + concatenateStringRec(aString, count-1)

// default args
fun defaultArgsDemo(regularArg: String = "", intArg: Int = 0) =
    "$regularArg------$intArg"

//nested function call
fun complexFunction(someArgs: String){
    // very complex code
    fun innerFunction(innerArg: Int){
        println("Outer Arg: $someArgs, Inner args : $innerArg")
    }

    // can use nested functions inside here
    innerFunction(45)
}

fun main(){

    simpleFunction("Kotlin")
    simpleFunction("Scala")
    println(concatenateString("Kotlin", 3))
    println(concatenateString("Kotlin", 10))
    println(concatenateStringRec("Kotlin", 3))
    println(concatenateStringRec("Kotlin", 10))

    // default args demo
    val normalCall = defaultArgsDemo("Kotlin", 32)
    val defaultCall = defaultArgsDemo("Kotlin")
    val secondNormalCall = defaultArgsDemo(intArg = 99) // name an argument
    val kotlinx3 = concatenateString(aString = "Kotlin", count = 3)

}