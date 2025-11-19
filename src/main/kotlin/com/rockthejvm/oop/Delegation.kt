package org.example.com.rockthejvm.oop

import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream

object Delegation {

    interface TextTransformer {
        val id: String
        fun transform(text: String): String
    }

    class Translator(val from: String, val to: String): TextTransformer{
        override val id: String = "Translator $from -> $to"
        override fun transform(text: String): String = "[$id] translating from $from to $to: $text"
    }

    class GPT4: TextTransformer{
        override val id: String = "GPT-4"
        override fun transform(text: String): String =
            "[$id] something an AI would say"
    }

    //create a transformer
    val transformer: TextTransformer = Translator("English", "Romanian")
    // use it directly
    val transformedText = transformer.transform("This is a Kotlin lesson")

    // debate - composition vs inheritance
    // Decorator Pattern
    class TextProcessor(private val t: TextTransformer): TextTransformer {
        override val id: String = t.id
        override fun transform(text: String): String = t.transform(text) // delegation to t
    }

    // Decorator pattern example in JAVA - Java Stream API - InputStream
    val bis = DataInputStream(BufferedInputStream(FileInputStream(File("src/main/kotlin/com/rockthejvm/oop/Delegation.kt"))))

    val processor = TextProcessor(Translator("English", "Romanian"))
    val transformedText_v2 = processor.transform("This is a Kotlin lesson")

    // IN Kotlin decorator pattern is in built and its called DELEGATION
    class TextProcessorV2(private val t: TextTransformer): TextTransformer by t // same as TextProcessor

    val processor_v2 = TextProcessorV2(Translator("English", "Romanian"))
    val transformedText_v3 = processor_v2.transform("This is a Kotlin Lesson")  // same thing

    // Can you override any method in Delegation ?
    // Yes you can , however BE CAREFUL as the underlying implementation for properties/methods is tied to t itself

    @JvmStatic
    fun main(args: Array<String>) {
        println(transformedText)
        println(transformedText_v2)
        println(transformedText_v3)
    }
}