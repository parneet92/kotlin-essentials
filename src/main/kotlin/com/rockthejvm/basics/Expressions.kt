package org.example.com.rockthejvm.basics

fun main(){

    // expressions = structures that are truned into value
    val meaningOfLife = 78

    // math expressions: +,-,/,*,%
    val mathExpression = 2 + 7 * 7

    // bitwise operators: shl, shr, ushl, ushr, and, or, xor, inv
    val bitwiseExpression = 2 shl 2 // 10 -> 1000

    // comparison expressions: == (equality), != (not equality), <, <=, >, === (reference equality), !==
    val equalityTest = 1 > 2    // false

    // boolean expressions: !(not), && (and), || (or)
    val nonEqualityTest = !equalityTest

    // Instruction vs Expression
        // Expressions are evaluated to a value - functional programming approach
        // Instructions are executed - imperative programming

    // if structure as instruction
    val aCondition = 1 > 2
    if (aCondition)
        println("the condition is true")
    else
        println("the condition is false")

    // if structure as an expression
    val anIfExpression = if(aCondition) 42 else 999

    println(anIfExpression)

    println(if(aCondition) 42 else 999)

    //when - "switch on steroids"
    when(meaningOfLife) {
        42 -> println("The meaning of life from HGG")
        43 -> println("Not the meaning of life but quite close")
        else -> println("something else!!")
    }

    // when expression
    val meaningOfLifeExpression_v2 = when (meaningOfLife) {
        42 -> println("The meaning of life from HGG")
        43 -> println("Not the meaning of life but quite close")
        else -> println("something else!!")
    }

    // a branch in when with mutlple values
    val meaningOfLifeExpression_v3 = when (meaningOfLife) {
        42, 43 -> println("The meaning of life or close enough")
        else -> println("something else!!")
    }

    // condition in branches
    val meaningOfLifeExpression_v4 = when {
        meaningOfLife < 42 -> "Meaning of Life is too small"
        meaningOfLife > 100 -> "Meaning of life is too big"
        else -> "close enough"
    }

    //test for types in when clause
    val something: Any = 46
    val someExpression = when(something){
        is Int -> "It's an Integer, maybe meaning of life"
        is String -> "It's a string, so maybe not meaning of life"
        else -> "Not sure what this can be"
    }

    // Looping - Instructions only
    // for loops
    println("Inclusive range")
    for(i in 1 .. 10){
        println(i)
    }

    println("Exlusive range")
    for(i in 1 ..< 10){
        println(i)
    }

    println("Exclusive range v2")
    for(i in 1 until 10 ){
        println(i)
    }

    println("Inclusive range step 2")
    for(i in 1 .. 10 step 2)
        println(i)

    println("Descending range")
    for(i in 10 downTo 1){
        println(i)
    }

    // arrays
    println("Iterating over collection")
    val anArray = arrayOf(3,5,7,6,5,3,6,3,32)
    for(elem in anArray){
        println(elem)
    }

    // while
    println("While loops")
    var i = 1
    while(i <= 10){
        println(i)
        i += 1
    }

    // do while
    println("Do while loops")
    var j = 10
    do{
        println(j)
        j -= 1
    }while(j >= 1)

}