package org.example.com.rockthejvm.practice

import java.io.IOException

/*
    1. Create an interface Transformation with a single process method
        taking an Image and returning another image
    2. Create 3 subclasses of Transformation
        - Crop : x,y,w,h as constructor args
        - Blend : fgImage, blendMode as constructor args
        - Noop : does nothing
        - stub the process method e.g. printing something
    3. Add a `parse` method in the transformation companion
        taking a string and returning a transformation instance
        - parse("crop 0 10 100 200) -> Crop(0,10, 100, 200)
        - parse("blend paris.jpg transparency")->Blend(Image(..), Transparency)
        - parse("master yoda") -> Noop
    4. Application main method.
    5. Parse transformations in the main app
    6. Implement the transformations
 */

interface Transformation {
    fun process(image: Image): Image
    companion object{
        fun parse(args: String): Transformation {
            val wordsArray = args.split(" ")
            val command = wordsArray[0]
            return when(command){
                "crop" ->
                    try{
                        Crop(
                            wordsArray[1].toInt(),
                            wordsArray[2].toInt(),
                            wordsArray[3].toInt(),
                            wordsArray[4].toInt()
                        )
                    } catch (e: Exception){
                        println("Invalid crop format. Usage: `crop [x] [y] [w] [h]")
                        Noop
                    }
                "blend" ->
                    try{
                        Blend(
                            Image.loadResource(wordsArray[1]),
                            BlendMode.parse(wordsArray[2])
                        )
                    } catch (e: IOException){
                        println("Invalid Image!!!")
                        Noop
                    }
                    catch (e: Exception){
                        println("Invalid blend format. Usage: `blend [path] [mode]`")
                        Noop
                    }
                else -> Noop
            }
        }
    }
}

class Crop(private val x: Int, private val y: Int, private val width: Int, private val height: Int): Transformation{
    override fun process(image: Image): Image =
        try {
            image.crop(x, y, width, height)!!
        } catch (e: Exception) {
            println(" Error : Coordinates are out of bounds, Max coordinates: ${image.width} x ${image.height}")
            image
        }

}

/*
    1. Make sure that the images have the exact same dimesnsions
    2. Create a black image from the dimensions
    3. For every pixel in fgImage with every corresponding pixel in bgImage, use the blend mode to
        combine the colors
        write that pixel in those coordinates in the resulting image
    4. Return the image
 */
class Blend(private val fgImage: Image, private val blendMode: BlendMode): Transformation{
    override fun process(bgImage: Image): Image {
        if(fgImage.height != bgImage.height || fgImage.width != bgImage.width){
            println("Error: Images don't have the same sizes: ${fgImage.width} x ${fgImage.height} vs  ${bgImage.width} x ${bgImage.height}")
            return bgImage
        }

        val resultingImage = Image.black(bgImage.width, bgImage.height)
        for( x in 0 ..< resultingImage.width){
            for( y in 0 ..< resultingImage.height){
                val combinedColor = blendMode.combine(fgImage.getColor(x,y), bgImage.getColor(x, y))
                resultingImage.setColor(x, y, combinedColor)
            }
        }
        return resultingImage

    }
}

object Noop: Transformation{
    override fun process(image: Image): Image {
        println("Calling no op [process method")
        return image
    }
}