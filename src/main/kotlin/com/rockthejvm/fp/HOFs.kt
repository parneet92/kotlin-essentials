package org.example.com.rockthejvm.fp

object HOFs {

    // higher order functions = function that takes other functions as arguments or returns other functions as return type
    val hof: (Int, (Int) -> Int) -> Int = { x, func -> x + func(1) }
    val anotherHof: (Int) -> ((Int) -> Int) = { x -> { y -> y + 2 * x} }

    val four = hof(2) { arg -> arg + 1 }
    val four_v2 = anotherHof(1)(2)
    //             ^^^^^^^^^^^^^^^ currying
    // curried functions are functions that return other function instances

    // curried functions
    val superAdder: (Int) -> (Int) -> Int = { x -> { y -> x + y } }
    val add3: (Int) -> Int = superAdder(3)
    val seven = add3(4)

    /*
        Exercises
            1. conversion methods from curried and uncurried functions
            2. compose function values

     */
    fun <A,B,C> toCurry(f: (A, B) -> C): (A) -> (B) -> C =
        { x -> { y -> f(x,y) } }

    fun <A,B,C> fromCurry(f: (A) -> (B) -> C): (A,B) -> C =
        { x, y -> f(x)(y)  }


    val regAdder = { x:Int, y:Int -> x + y }
    val superAdder_v2 = toCurry(regAdder)
    val seven_v2 = superAdder_v2(3)(4)

    val regAdder_v2 = fromCurry(superAdder)
    val seven_v3 = regAdder_v2(3,4)

    // compose -> another function that given an "x" -> f(g(x))
    // andThen -> another function that given an "x" -> g(f(x))
    fun <A,B,C> compose(f: (B) -> C, g: (A) -> B):(A) -> C =
        { x -> f(g(x)) }

    fun <A,B,C> andThen(f: (A) -> B, g: (B) -> C):(A) -> C =
        { x -> g(f(x)) }

    val incrementor = { x: Int -> x + 1 }
    val doubler = { x: Int -> x * 2 }
    val composed = compose(incrementor, doubler)
    val chained = andThen(incrementor, doubler)


    @JvmStatic
    fun main(args: Array<String>) {
        println(hof(10){ arg -> arg + 10 })
        println(anotherHof(43)(32))
        println(four)
        println(four_v2)
        println(seven)
        println(seven_v2)
        println(seven_v3)
        println(composed(10))
        println(chained(10))
    }
}