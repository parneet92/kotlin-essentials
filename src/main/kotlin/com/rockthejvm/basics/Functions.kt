package org.example.com.rockthejvm.basics

import kotlin.math.roundToInt
import kotlin.math.sqrt

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

/**
 * Exercises :
 * 1. a greeting function (name,age) => "Hi my name is ... and I am ... years old"
 * 2. a factorial function
 * 3. Fibonacci function n => nth Fibonacci number
 * 4. If a number is prime , isPrime(n)
 */

// Solution 1 :

fun greeting(name: String, age: Int) = "Hi my name is $name and I am $age years old"

// Solution 2 :
fun fact(num: Int): Long {
    if (num <= 0) return 0
    var result = 1L
    for(i in num downTo 1){
        result *= i
    }
    return result
}

fun factorialRec(n: Int): Long =
    if(n <= 0) 0
    else if(n==1) 1
    else n * factorialRec(n-1)

// solution 3
fun fib(n: Int): Int {
    if( n<=0) return 0
    if (n <= 2 ) return n
    else return fib(n-1) + fib(n-2)
}

//solution 3 (2)
// 1,2,3,5,8,13,21
fun fib_2(n: Int): Long{
    if (n == 0) return -1L
    if(n == 1) return 1L
    if(n == 2) return 2L
    var n1: Long = 1L
    var n2: Long = 2L
    var temp = 0L
    for(i in 3 .. n){
        temp = n1 + n2
        n1 = n2
        n2 = temp
    }
    return n2
}

// solution 4
fun isPrime(n: Int): Boolean{
    if(n <= 1) return false
    val upperLimit: Int = sqrt(n.toDouble()).roundToInt()
    for(i in 2 .. upperLimit){
        if((n % i).toInt() == 0) return false
    }
    return true
}
/*
no stack frames to allocate
 */
tailrec fun isPrimeRec(n: Int, d: Int = 2): Boolean =
    if( n % d == 0) false
    else if ( d > n/2 ) true
    else isPrimeRec(n, d+1) // recursive call is computed last on the branch
//  ^^^^^^^^^^^^^^^^^^^^^^^^ tail position  == TAIL RECURSIVE


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

    println("---------------")

    println(fib(6))
    println(fib_2(6))
    println("isPrime 81 - ${isPrime(81)}")
    println("isPrime 7 - ${isPrime(7)}")
    println("isPrime 2003 - ${isPrime(2003)}")
    println("Faqctorial - ${fact(50000)}")
    println("Print Fibonacci numbers - Iterative")
    for(i in 1 .. 10){
        println(fib_2(i))
    }
    println("Print Fibonacci numbers - recursive")
    for(i in 1 .. 10){
        println(fib(i))
    }
}