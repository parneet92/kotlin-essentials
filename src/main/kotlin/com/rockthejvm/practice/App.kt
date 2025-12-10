package org.example.com.rockthejvm.practice

import java.awt.Dimension
import java.awt.Graphics
import java.util.Scanner
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.WindowConstants
import kotlin.system.exitProcess

// Java Swing
object App {

    private lateinit var frame: JFrame
    private lateinit var imagePanel: ImagePanel

    class ImagePanel(private var image: Image): JPanel() {
        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            // render the picture inside this "graphics"
            image.draw(g)
        }
        //TODO
        override fun getPreferredSize(): Dimension =
            Dimension(image.width, image.height)

        fun replaceImage(newImage: Image) {
            image = newImage
            revalidate()
            repaint()

        }

        fun getImage(): Image = image
    }

    fun loadResource(path: String) {
        val image = Image.loadResource(path)
        if(!this::frame.isInitialized) {
            frame = JFrame("Kotlin rocks image app")
            imagePanel = ImagePanel(image)

            frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            frame.contentPane.add(imagePanel)
            frame.pack()
            frame.isVisible = true
        } else {
            imagePanel.replaceImage(image)
            frame.pack()    // resizes the window to the "preferred dimensions"
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
       val scanner = Scanner(System.`in`)
        while (true){
            print("> ")
            val command = scanner.nextLine()
            /*
                TODO
                    "load wikicrop.jpg -> load this path into the UI
                    "save newPicture.jpg" -> save the current image to this path
                    "exit" -> exitProcess(0)

             */
            val words = command.split(" ")
            val action = words[0]
            when(action){
                "load" -> try{
                            loadResource(words[1])
                          }catch (e: Exception){
                              println("Error : cannot load image at path ${words[1]}.")
                          }
                "save" -> if(!this::frame.isInitialized)
                            println("Error! No image loaded")
                          else
                            imagePanel.getImage().saveResource(words[1])
                "exit" -> exitProcess(0)
                /*
                    1. Use transformation.parse(command)
                    2. run the transformation on the current image -> a new image
                    3. replace the current image with the new image
                 */
                else -> {
                    val transformation = Transformation.parse(command)
                    val newImage = transformation(imagePanel.getImage())
                    imagePanel.replaceImage(newImage)
                    frame.pack()    // have the windows specifically sized for the new image
                }

            }

        }

    }
}