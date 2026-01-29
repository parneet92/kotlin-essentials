package org.example.com.rockthejvm.practice

import java.awt.image.BufferedImage
import java.io.File
import java.util.HexFormat
import javax.imageio.ImageIO

// manipulating images
// 24 bit Integer= Int
// 00000000rrrrrrrrggggggggbbbbbbbb

/*
    Exercise :
    1. Define a color class that takes 3 int as arguments:
        - red
        - green
        - blue
    2. Makes sure that the properties of the color(red, green, blue) are always in between 0 and 255
    3. Add a method toInt() that returns a single Integer with the representation above
        00000000rrrrrrrrggggggggbbbbbbbb
        hint: use shl, shr, and , or , xor, .. bitwise operators
    4. Add a draw(height, width, path) that draws an image of width x height, all with the same color

 */
class Color(r: Int, g: Int, b: Int) {
    val red = filter(r)
    val green = filter(g)
    val blue = filter(b)

    private fun filter(value: Int): Int =
        if(value <= 0) 0
        else if(value < 255) 255
        else value

    fun toInt(): Int =
        red.shl(16) or green.shl(8) or blue

    fun draw(width: Int, height: Int, path: String) {
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val pixels = IntArray(width * height) { toInt() }
        image.setRGB(0, 0, width, height, pixels, 0, width)
        ImageIO.write(image, "JPG", File(path))
    }

    fun clampColor(v: Int) =
        if(v <= 0) 0
        else if(v >= 255) 255
        else v

    operator fun plus(other: Color): Color =
        Color(
            clampColor(red + other.red),
            clampColor(green + other.green),
            clampColor(blue + other.blue)
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Color

        if (red != other.red) return false
        if (green != other.green) return false
        if (blue != other.blue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = red
        result = 31 * result + green
        result = 31 * result + blue
        return result
    }


    companion object {
        val BLACK = Color(0,0,0)
        val RED = Color(255,0,0)
        val GREEN = Color(0,255,0)
        val BLUE = Color(0,0,255)
        val GRAY = Color(128,128,128)

        fun fromHex(hexValue: Int): Color {
            // 0x00ff0000
            // 00000000111111110000000000000001
            val red = (hexValue and 0xff0000) shr 16
            val green = (hexValue and 0xFF00) shr 8
            val blue = hexValue and 0xff
            return Color(red, green, blue)
        }
    }
}

fun drawColor(width: Int, height: Int, path: String) {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val pixels = IntArray(width * height) { 0xFF0000 }
    image.setRGB(0, 0, width, height, pixels, 0, width)
    ImageIO.write(image, "JPG", File(path))
}

/*
    Exercise: create a companion object for color so that you can write
        COLOR.BLACK, COLOR.RED,.....
        COLOR.fromHex(int) : Color Instance out of that

 */

fun main(){

    /*val test: Int = 1 // 100000
    println("Byte representation : ${test.shl(5)}")
    val magenta = Color(255, 0 , 255)
    magenta.draw(20, 20, "src/main/resources/magenta.jpg")
    val randomColor = Color(255, 255 , 0)
    randomColor.draw(20, 20, "src/main/resources/randomColor.jpg")

    Color.fromHex(0xEDEDED).draw(20, 20, "src/main/resources/ed.jpg")*/
    Color.BLUE.draw(1693, 2554, "src/main/resources/blue.jpg")
}