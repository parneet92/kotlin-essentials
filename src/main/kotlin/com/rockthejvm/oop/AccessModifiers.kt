package org.example.com.rockthejvm.oop

object AccessModifiers {

    open class Person(open val name: String){
        // protected = accessible inside this class AND all child types on THIS instance
        protected fun sayHi() = "Hi my name is $name."

        // private propeties/methods can only be accessed inside this body
        private fun watchNetflix(): String = " I'm binge watching my favourite series."

        // no modifier = "public"

        // modifier "internal" -> property/method accessible inside the same compilation module ( only inside the compilation unit)
        // useful for libraries
    }

    class Kid(override val name: String, age: Int): Person(name) {
        fun greetPolitely(): String =
            sayHi() + "I love to Play!"
    }

    // complication
    class KidWithParent(override val name: String, val age: Int, val mom: Person, val dad: Person): Person(name){
        // sayHi() is protected , so I should be able to access it
        // ... only on THIS instance, not on other Instances!
        fun everyoneIntroduceThemselves(): String =
           // "Hi , I'm $name. Here are my parents! ${mom.sayHi()} ${dad.sayHi()}"
            "${this.sayHi()} Here are my parents! ${mom.name} ${dad.name}"
    }

    //private constructor
    class MyService private constructor(url: String) {
        // comes with a companion object
        companion object {
            fun local(): MyService =
                MyService("127.0.0.1")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val alice = Person("Alice")
        val bob = Person("Bob")
        val kid = KidWithParent("Dennis", 5 , alice, bob)
        // println(aPerson.sayHi())

        // val myService = MyService("127.0.0.1")  // cannot build directly
        val myService = MyService.local()
    }
}