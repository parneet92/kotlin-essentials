package org.example.com.rockthejvm.fp

object FunctionalCollections {

    fun concatenate(n: Int, s: String): String =
        if(n <= 0) ""
        else concatenate(n-1, s)

    fun demoLists() {
        val numbers = listOf(1,2,3,4,5)
        //mapping
        val tenXNumbers = numbers.map { x -> x*10 }
        // may return a different list type
        val kotlinRepeated = numbers.map { x -> concatenate(x, "Kotlin") }

        //filtering
        val evenNumbers = numbers.filter { x -> x % 2 == 0 }

        // doing something
        numbers.forEach { x ->
            println(x)
        }

        // expand a list
        val expandedList = numbers.flatMap { x -> (1..x).toList() }
        println(expandedList)

        // reducing a list to a single value
        val numbersSum = numbers.fold(0) { x,y -> x+y}
        val numbersSumV2 = numbers.reduce { x,y -> x+y }

        // processing with predicates
        val firstEven  = numbers.find { x -> x % 2 == 0 } // returns nullable , first Item in the list passing the predicate
        val evenPrefix = numbers.takeWhile { x -> x % 2 == 0 } // returns a list containing the first items of the list , as long as the predicate holds true
        val evenCount = numbers.count { x -> x % 2 == 0 }

        // many more
        val stringRep = numbers.joinToString("|", "{","}") { x -> x.toString() }
        println(stringRep)

    }

    fun demoSets() {
        val numbers = setOf(1,2,3,4,5)

        // check whether an entire set satisfies the predicate
        val lt10 = numbers.all { x -> x < 10 }
        val lt100 = numbers.none { x -> x >= 100 }
    }

    fun demoMaps() {
        val phonebook = mapOf(
            "Daniel" to 9872439,
            "Alice" to 87234872
        )

        // map, flatmap , filter ,... - just for PAIRS not for individual items

        // filtering keys
        val sectionA = phonebook.filterKeys { it.startsWith("A") }  // map of all pairs whose keys starts with A

        // mapping values
        val addSuffix = phonebook.mapValues { pair -> pair.value * 10 } // map of all pairs with same key but value transform

        // construct a map with default value ( avoid crashes during invalid key lookup )
        val phoneWithDefaults = phonebook.withDefault { invalidKey -> -9000 }


    }

    /*
        Exercises :
            1. You have a list of strings -> return list of those strings length
            2. You have 2 lists of number of the same size. return a sum of corresponding elements
                [1,2,3] [4,5,6] -> [5,7,9]
                use the function zip
            3. 2 Lists of things , return all combination as strings
                [1,2,3] ["Black","white"] -> ["1-black","1-white","2-Black","2-white"]
            4. Lists of strings, return the concatenation of all strings
                ["Kotlin", "is", "cool"] -> "kotliniscool"
                - reduce
                - fold
            5. concatenate string a number of times usimg the standard library API

     */

    fun exercises(){
        val strings = listOf("Still", "trying to", "figure out!!")
        val listOfStringLength = strings.map { it.length }
        println("Exercise 1 : $listOfStringLength")
    }

    fun exercise2(list1: List<Int>, list2: List<Int>): List<Int> =
        list1.zip(list2).map { it.first + it.second }

    fun exercise2v2(list1: List<Int>, list2: List<Int>): List<Int> =
        list1.zip(list2) { a,b -> a + b }

    fun exercise3(list1: List<Any>, list2: List<Any>): List<String> =
        list1.flatMap { l1 -> list2.map { l2 -> "$l1-$l2" } }

    fun exercise4(list: List<String>): String =
        list.reduce { s1, s2 -> "$s1$s2" }

    fun exercise4V2(list: List<String>): String =
        list.fold("") { s1, s2 -> "$s1$s2" }

    fun exercise5(n: Int, s: String): String =
        (1..n).joinToString { _ -> s }

    fun exercise5v2(n: Int, s: String): String =
        (1..n).toList().map { _ -> s }.reduce { s1,s2 -> s1+s2 }



    @JvmStatic
    fun main(args: Array<String>) {
        demoLists()
        exercises()
        println("Exercise 2 - ${exercise2(listOf(1,2,3,4), listOf(5,6,7,8))}")
        //println("Exercise 3 - ${exercise3(listOf(1,2,3,4), listOf("Black","White"))}")
        println("Exercise 4 - ${exercise4(listOf("Hello","How","are","you?"))}")
        println("Exercise 4 v2 - ${exercise4V2(listOf("Hello","How","are","you?"))}")
        println("Exercise 5 - ${exercise5(4,"Kotlin")}")
    }
}