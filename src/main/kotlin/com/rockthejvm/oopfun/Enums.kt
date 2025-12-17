package org.example.com.rockthejvm.oopfun

object Enums {


    sealed interface PermissionNaive {
        companion object {
            data object READ: PermissionNaive
            data object WRITE: PermissionNaive
            data object EXECUTE: PermissionNaive
            data object NONE
        }
    }

    // final (cannot be extended)
    // compiler overrides toString, hashCode and equals

    enum class Permission{
        READ, WRITE, EXECUTE, NONE;         // semicolon (;) is important if you want to add properties/methods
        // 0    1       2       3           ordinal
    }

    // can pass constructor arguments as well
    enum class PermissionBitMask(val bits: Int){
        READ(4),    // 0100
        WRITE(2),   // 0010
        EXECUTE(1), // 0001
        NONE(0)     // 0000
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val readPermission = Permission.READ
        val readPermission_v2 = Permission.READ

        println(readPermission)
        println(readPermission == readPermission_v2)
        // parse an enum from string
        println(Permission.valueOf("READ"))
        println(Permission.entries.toList())
        println(readPermission.ordinal)

    }
}