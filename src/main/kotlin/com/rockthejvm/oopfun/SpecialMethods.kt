package org.example.com.rockthejvm.oopfun

import kotlin.math.sqrt

object SpecialMethods {

    // special methods in java/jvm - equals, hashCode, toString
    class Person(val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean = when(other) {
            is Person -> name == other.name && age == other.age
            else -> false
        }

        /*
            a "unique" number for this instance
            IMPORTANT: 2 "equal" instances should produce the same hashcode
            used in hash based data structures - sets, maps
         */
        override fun hashCode(): Int =
            name.hashCode() * 31 + age

        override fun toString(): String =
            "Person($name, $age"

        // infix methods only work for methods with 1 arguments
        infix fun likes(movie: String) =
            "$name says: I LOVE $movie!"

        /*
            this < another => this.compareTo(another) < 0
            this > another => this.compareTo(another) > 0
            same for >=, <=

            IMPORTANT : If this.equals(another), then make sure that this.compareTo(another) == 0
         */
        operator fun compareTo(another: Person): Int =
            this.age - another.age
    }

    // infix methods - syntax sugar

    class ComplexNumber(var re: Double, var im: Double) {

        /*fun add(other: ComplexNumber) =
            ComplexNumber(re + other.re, im + other.im)*/
        // operator overloading
        // plus, minus, div, rem
        operator fun plus(other: ComplexNumber) =
            ComplexNumber(re + other.re, im + other.im)

        operator fun plus(number: Double) =
            ComplexNumber(re + number, im)

        // compound operators - must return UNIT
        // plusAssign, minusAssign, ...
        operator fun plusAssign(number: Double): Unit {
            re = re + number
        }

        // inc, dec
        operator fun inc(): ComplexNumber = ComplexNumber(re + 1, im)
        // unaryPlus, unaryMinus - for -x, +x
        operator fun unaryMinus(): ComplexNumber = ComplexNumber(-re, -im)

        // access elements
        // cn[0], cn[1]
        // cn[1] == cn.get(index)
        operator fun get(index: Int): Double =
            when(index) {
                0 -> re
                1 -> im
                else -> throw IllegalArgumentException("Complex numbers have only 2 fields")
            }

        // matrix[r,c] == matrix.get(r,c)
        // cn[1,0]
        operator fun get(index1: Int, index2: Int): Double =
            get(index1) + get(index2)

        operator fun get(part: String): Double =
            when(part) {
                "re" -> re
                "im" -> im
                else -> throw IllegalArgumentException("Invalid field")
            }

        // cn[1] = 4.3
        operator fun set(index: Int, value: Double): Unit {
            when(index) {
                0 -> re = value
                1 -> im = value
                else -> throw IllegalArgumentException("Complex numbers have only 2 fields")
            }
        }

        // contains operator
        // 2 in [1,2,3]
        // useful for collections
        operator fun contains(v: Double) : Boolean =
            re == v || im == v

        // destructuring operators (Python style declarations )
        // val (x,y) = cn
        // it should be in order like 1,2,3,... ( don't leave gap in your components)
        operator fun component1() = re
        operator fun component2() = im
        operator fun component3() = sqrt(re * re + im * im)

        // how you can "call" this instance like a function
        // when a type looks like a function , it's often useful to have invoke overwritten
        operator fun invoke(origin: ComplexNumber): ComplexNumber =
            ComplexNumber(re - origin.re, im - origin.im)

        override fun toString(): String =
            "($re, $im)"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val person1 = Person("Alice", 35)
        val person2 = Person("Alice", 35)
        val bob = Person("Bob", 32)

        println(person1 == person2) // person1.equals(person2)
        println(person2 likes "Forrest Gump")

        // operators
        val cn = ComplexNumber(1.2, 2.6)
        val acn = ComplexNumber(0.6, 2.9)
        // println(cn.add(acn)) -> Standard notation
        println(cn + acn)   // same expression as above using operator overloading
        println(cn + 9.5)
        //reassignment
        cn += 8.9
        println(-cn)
        println(bob < person1)  // same as bob.compareTo(person1) < 0

        println(cn[0])  // same as cn.get(0)
        println(cn["re"])   // same as cn.get("re")
        cn[1] = 4.3 // same as cn.set(1, 4.3)
        println(2.0 in cn)  // same as cn.contains(2.0)

        /*
            val x = cn.component1()
            val y = cn.component2()
            val l = cn.component3()
         */
        val(x,y,l) = cn
        println(x)
        println(y)
        println(l)

        val relative = cn(acn)  // cn.invoke(acn)
    }
}