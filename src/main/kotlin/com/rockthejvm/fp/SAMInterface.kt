package org.example.com.rockthejvm.fp

object SAMInterface {

    fun runNTimes(n: Int, runnable: Runnable){
        (1..n).forEach { _ ->
            runnable.run()
        }
    }

    // or we can also rewrite this as
    fun runNTimesFunc(n: Int, func: () -> Unit){
        (1..n).forEach { _ ->
            func()
        }
    }

    fun demoRunnable(){
        runNTimes(10, object : Runnable {
            override fun run() {
                println("This is runnable")
            }
        })

        runNTimesFunc(10) { println("This is runnable") }
    }

    // SAM = single abstract method
    fun demoRunnable_v2(){
        runNTimes(10) { println("This is runnable") }
        /*
            rewritten by the compiler to
            runNTimes(10, object : Runnable {
                override fun run() {
                    println("This is runnable")
                }
            })

         */

        // works automatically with SAM interfaces from JAVA
    }

    fun interface Transformer {
        // can only have one abstract method
        fun process(n: Int): Int
        // compiler can rewrite a transformer to (Int) -> Int
    }

    fun processNTimes(seed: Int, n: Int, transformer: Transformer): Int =
        if(n <= 0) seed
        else processNTimes(transformer.process(seed), n-1, transformer)

    @JvmStatic
    fun main(args: Array<String>) {
        println(processNTimes(0, 10) { it + 1} )
    }
}