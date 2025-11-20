package org.example.com.rockthejvm.oop

object Generics {

    // goal - reuse code/logic on many (potentially unrelated) types

    // linked list
    // "head" = first element
    // "tail" = the rest of the list  = a linked list
    // [1,2,3,4] -> head = 1, tail = [2,3,4]

    // if you have linked list of integers , then how do you add a linked list of Strings? or something else
    // 1. Duplicate the code
    //      Persons?, Doubles?, anyting else?, not sustainable
    // 2. Use Any
    //      pro: logic applies to all types
    //      cons: can store strings and persons and ints and animals in the same list! (lost type safety)
    // 3. Generics

    interface MyLinkedList<A> {
        fun head(): A
        fun tail(): MyLinkedList<A>

        companion object {
            fun <A> single(elem: A): MyLinkedList<A> =
                NonEmptyList(elem, EmptyList())
        }
    }

    class EmptyList<A>: MyLinkedList<A> {
        override fun head(): A = throw NoSuchElementException()
        override fun tail(): MyLinkedList<A> = throw NoSuchElementException()
    }

    class NonEmptyList<A> (private val h: A, private val t: MyLinkedList<A>): MyLinkedList<A> {
        override fun head(): A = h
        override fun tail(): MyLinkedList<A> = t
    }

    /*
        - No code duplication
        - can support any type, even unrelated types
        - maintained type safety
            - homogeneous lists
            - correct type returned
            - enforce correct types
     */

    // generic features in Kotlin
    // can specify multiple type arguments
    interface MyMap<K,V>

    // generic functions
    fun <A> singleElem(elem: A): MyLinkedList<A> =
        NonEmptyList(elem, EmptyList())

    // Objects cannot have generic type arguments
    // companion objects can use generic method

    @JvmStatic
    fun main(args: Array<String>) {
        val simpleNumbers = NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, NonEmptyList(4, EmptyList()))))
        val firstNumber: Int = simpleNumbers.head()

        val simpleStrings = NonEmptyList("I", NonEmptyList("Love", NonEmptyList("Kotlin", EmptyList())))
        val firstString = simpleStrings.head()

        val singleNumber = MyLinkedList.single(42)
    }
}