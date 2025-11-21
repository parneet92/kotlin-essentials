package org.example.com.rockthejvm.oop

object Collections {

    // lists

    fun demoList(){
        //immutable
        val aList: List<Int> = listOf(1,2,3)
        // main API: index of an element + size
        val thirdElement = aList.get(2)
        val thirdElement_v2 = aList[2]
        val length = aList.size

        // other API
        val find3 = aList.indexOf(3)
        val subList = aList.subList(1,2)    //  from(inclusive) to(exclusive)
        val has3 = aList.contains(3)
        val with4 = aList.plus(4)   // new list with element 4
        // plus others[FP]

        // mutable List
        val mutableList = mutableListOf(1,2,3)
        mutableList.add(0, 42)
        mutableList.removeAt(0)
        mutableList.set(1, 56)
        mutableList[1] = 56
        println(mutableList)

        // work best with immutable lists

        val uniqueItems = aList.toSet()
    }

    // arrays - map to JVM arrays - map to OS-level arrays (very fast)
    // always mutable
    fun demoArrays(){
        val anArray = arrayOf(1,2,3,4)
        // only have "get" and "set"
        val secondItem = anArray[1]
        //set
        anArray[1] = 100
        //length
        val length = anArray.size

        for(element in anArray) // iterate through elements of a collections - lists, map, arrays, sets, ..
            println(element)
    }

    // sets - don't contain duplicates
    fun demoSets(){
        //immutable
        val aSet = setOf(1,2,3,4,5,4,2)
        // API - contains
        val contains1 = aSet.contains(1)
        val contains1_v2 = 1 in aSet    // kotlin syntax sugar
        // other APIs
        val add7 = aSet.plus(7)
        val add7_v2 = aSet + 7
        val without3 = aSet.minus(3)
        val without3_v2 = aSet - 3
        val combined = aSet.plus(setOf(3,4,5))
        val diff = aSet.minus(setOf(7,8,9))
        val intersect = aSet.intersect(setOf(3,6,7))

        // mutable
        val mutableSet = mutableSetOf(1,2,3,4,6,2,3,1)
        mutableSet.add(9)
        mutableSet.remove(4)
        // same secondary API

        println(mutableSet)

        // convert back to another collection
        val numbers = aSet.toList()
    }

    // maps - key-value association (key is unique)
    fun demoMaps(){
        val phoneBook = mapOf(
            Pair("daniel", 234434),
            "Alice" to 34124134
        )
        // fundamental API
        val hasAlice = phoneBook.contains("Alice")
        val aliceNumber = phoneBook.get("Alice")
        val aliceNumber_v2 = phoneBook["Alice"]

        // secondary API
        val newMap = phoneBook.plus("Bob" to 324234214)
        val withoutDaniel = phoneBook.minus("Daniel")
        val pairs = phoneBook.toList()
        // vice versa
        val pairs_v2 = listOf(Pair("Daniel", 13414124), "Alice" to 2341413)
        val phonebook_v2 =pairs_v2.toMap()  // only for lists of pairs

        // mutable maps
        val mutablePhonebook = mutableMapOf(
            Pair("daniel", 234434),
            "Alice" to 34124134
        )
        mutablePhonebook.remove("Daniel")
        mutablePhonebook.put("Bob" , 23411312)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoList()
    }
}