package com.blueray.montak.helper
import java.io.File

class UpdateXmls {

    fun main() {
        val directoryPath = "./app/src/main/res/layout" // Change this to your path
        val directory = File(directoryPath)

        if (!directory.exists() || !directory.isDirectory) {
            println("Directory does not exist!")
            return
        }

        directory.walkTopDown().forEach { file ->
            if (file.isFile && file.extension == "xml") {
                val content = file.readText()

                if (!content.contains("android:layoutDirection")) {
                    val updatedContent = content.replace("<RelativeLayout", "<RelativeLayout\nandroid:layoutDirection=\"ltr\"")
                        .replace("<LinearLayout", "<LinearLayout\nandroid:layoutDirection=\"ltr\"")
                        .replace("<FrameLayout", "<FrameLayout\nandroid:layoutDirection=\"ltr\"")
                    // Add other layout types if needed

                    file.writeText(updatedContent)
                    println("Updated ${file.absolutePath}")
                }
            }
        }
    }

}