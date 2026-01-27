package org.example.com.rockthejvm.fp

object ScopeFunctions {

    fun obtainExternalList = listOf(1,2,3,4,5)

    fun demoLet(){
        val numbers = obtainExternalList()
        val tenXNumbers = numbers.map { it * 10 }
        println("The 10x values are ${tenXNumbers}")
        val sumNumbers = tenXNumbers.reduce { a,b -> a + b }
        println("The sum of 10x numbers is ${sumNumbers}")

        // let allows you to use the old computation for the next stop
        obtainExternalList().let { numbers ->   // let is a scope function
            val tenXNumbers = numbers.map { it * 10 }
            println("The 10x values are ${tenXNumbers}")
            val sumNumbers = tenXNumbers.reduce { a,b -> a + b }
            println("The sum of 10x numbers is ${sumNumbers}")
        }

        // mental clarity and flow especially in chained computation
        /*
            keep just the even numbers - filter
            multiply the numbers by 10 - map
            "avg" = list.sum / (list.size + 1)
         */

        val result = obtainExternalList()
            .filter { it % 2 == 0 }
            .map { it * 10 }
            .let { list -> list.sum() / (list.size + 1) }

    }

    // run =  same as let, but has access to the internal scope of the object on which you're running
    data class Person(var name: String, var age: Int)
    fun demoRun(){
        val masterYoda = Person("Master Yoda", 65)
        masterYoda.age = 876
        masterYoda.name = "Yoda the master Jedi"
        val result = "${masterYoda.name} (${masterYoda.age})"

        val result_v2 = masterYoda.run {
            // you have access to the internals of masteryoda as `this` instance
            age = 765
            name = "Yodi the master Jedi"
            "${name} (${age})"  // this is the result of the lambda
        }
        println(result)
        println(result_v2)
    }

    // with = similar to run, but it's not an extension method
    data class GamingChannel(val playerA: String, val playerB: String, var open: Boolean) {
        fun msg(content: String) = println("[${playerA}] [to ${playerB}] $content")
    }

    fun demoWith(){
        val channel = GamingChannel("Alice", "Bob", true)
        channel.msg("build up your forces!!")
        channel.msg("attack here !!!!")
        channel.open = false

        // with is useful when we use resources
        with(channel) {
            // inside this scope, `this` is the object you are calling `with on
            msg("Doing with WITH build up your forces")
            msg("Attack here!!!")
            open = false
        }
    }

    // apply = same as run but it returns the same object that you're processing
    fun demoApply() {
        val alice = Person("Alice", 23)
        alice.name = "Alice in wonderland"
        alice.age = 24

        val modifiedAlice = alice.apply {
            // alice == `this` in this scope
            name = "Alice in wonderland"
            age = 25
        }

        println(modifiedAlice == alice) // same instance
    }

    // also = same as let but the lambda doesn't return anything, the expr returns the same instance
    // useful for side effects
    fun demoAlso(){
        val result = obtainExternalList()
            .filter { it % 2 == 0 }
            .map { it * 10 }
            .also { list -> list.sum() / (list.size + 1) }  // takes a lambda that returns unit
            .forEach { println(it) }    // that list we can use later
    }


    // context object  = the instance subject to the scope function

    @JvmStatic
    fun main(args: Array<String>) {

    }
}