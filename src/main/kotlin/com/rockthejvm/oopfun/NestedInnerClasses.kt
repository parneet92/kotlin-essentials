package org.example.com.rockthejvm.oopfun

object NestedInnerClasses {

    class Outer {
        val aProp = 4

        // nested class == "static" class
        // depends on the Outer Type, no connection with any INSTANCE of Outer
        class Nested {
            val nestedProp = 42
            //val summerProp = aProp + 10     // cannot refer to aProp from outer class
        }

        // inner class
        // depends on the current INSTANCE that created Inner
        inner class Inner {
            val innerProp = aProp + 10  // possible, compiler knows this property is from outer so no need to use qualified this
            val outerInstance = this@Outer
            //                  ^^^^^^^^^^ "qualified this" this is used to refer to the outer instance
        }
    }

    fun demoClasses(){
        //nested
        val nested = Outer.Nested()
        println(nested.nestedProp)

        // inner
        val outerInstance = Outer()
        val innerInstance = outerInstance.Inner()
        println(innerInstance.innerProp)
    }

    // some protocol for massive Game
    // Nested class or nested type hierarchy is useful when Message type hierarchy is conceptually ties to the gameing protocol
    // In other words : Nested classes are useful when the nested types are conceptually tied to the wrapping (outer) type
    // and they are relevant for the definition of a particular service
    interface MyProtocol {
        sealed class Message
        data class Start(val nPlayers: Int): Message()
        data class GameEvent(val type: String, val playerId: String): Message()
        // ..
    }

    val gameMessage: MyProtocol.Message = MyProtocol.Start(10)

    // Inner classes are useful when the types are tied to the implementation of the wrapping instance
    class PermissionService {

        // only relevant in THIS instance and not the Permission service type
        open inner class Role(name: String)
        inner class Admin: Role("ADMIN")
        inner class Moderator: Role("MODERATOR")
        inner class Player: Role("PLAYER")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoClasses()
    }
}