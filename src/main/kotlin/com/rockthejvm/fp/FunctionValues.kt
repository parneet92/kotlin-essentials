package org.example.com.rockthejvm.fp

// function as values
object FunctionValues {

    /*
    The only diff between the 2 function is the logic (n*10) vs (n+5)
     */
    fun tenXList(list: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        for(n in list)
            result.add(n*10)
        return result
    }
    fun add5List(list: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        for(n in list)
            result.add(n+5)
        return result
    }

    interface Transformation {  // It is like a function as it has only invoke and being used as function
        operator fun invoke(n: Int): Int
    }

    fun transformList(list: List<Int>, transformation: Transformation): List<Int> {
        val result = mutableListOf<Int>()
        for(n in list)
            result.add(transformation(n))
        return result
    }

    fun transformListV2(list: List<Int>, transformation: (Int) -> Int): List<Int> {
        //                                              ^^^^^^^^^^^^^ Function type ( can have instances )
        val result = mutableListOf<Int>()
        for(n in list)
            result.add(transformation(n))
        return result
    }

    fun <A,B> transformListV3(list: List<A>, transformation: (A) -> B): List<B> {
        //                                              ^^^^^^^^^^^^^ Function type ( can have instances )
        val result = mutableListOf<B>()
        for(n in list)
            result.add(transformation(n))
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = listOf(1,2,3,4)
        val tenXTransformation = object: Transformation{
            override fun invoke(n: Int): Int = n*10
        }
        println(transformList(numbers, tenXTransformation))
        val add5Transformation = object: Transformation{
            override fun invoke(n: Int): Int = n+5
        }
        println(transformList(numbers, add5Transformation))

        // functional programming = ability to pass function as values, return function as results
        // function values
        val tenXFunc = fun(x: Int): Int { return x*10 } // anonymous function == function value
        val tenXFuncV2 = { x: Int -> x*10 } // same thing as above , anonymous function AKA LAMBDA

        println(transformListV2(numbers, tenXFunc))

        // Kotlin has a built in syntax to transform collections
        // .map
        val tenXNumbers = numbers.map ({ x:Int -> x*10 })   // passing a function as an argument
        val tenXNumbersV2 = numbers.map { x:Int -> x*10 }   // simplified , when last argument is LAMBDA
        val tenXNumbersV3 = numbers.map { x -> x*10 }   // We can omit Int due to compiler inference
        val tenXNumbersV4 = numbers.map { it * 10 } // default name is "it" , only works for single argument LAMBDAS

        // multi arg LAMBDA
        val adderFun = { a:Int, b: Int -> a+b }
        val adderFunV2: (Int, Int) -> Int = { a,b -> a+b }
    }
}