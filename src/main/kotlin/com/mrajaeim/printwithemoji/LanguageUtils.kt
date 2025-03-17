package com.mrajaeim.printwithemoji

import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.PsiFile

object LanguageUtils {
    private val supportedExtensions = setOf(
        "java", "kt", "py", "js", "jsx", "ts", "tsx"
    )

    fun getPrintStatement(file: PsiFile, variableName: String): String {
        val extension = getFileExtension(file)
        val emoji = EmojiGenerator.getNextEmoji()

        return when (extension) {
            "java" -> "System.out.println(\"$emoji $variableName = \" + $variableName);"
            "kt" -> "println(\"$emoji $variableName = \${$variableName}\")"
            "py" -> "print(f\"$emoji $variableName = {$variableName}\")"
            "js", "jsx", "ts", "tsx" -> "console.log(\"$emoji $variableName\", $variableName);"
            else -> "print(\"$emoji $variableName\", $variableName);" // Default to JavaScript
        }
    }

    fun getDefaultPrintStatement(file: PsiFile, randomEmoji: Boolean = false): String {
        val extension = getFileExtension(file)
        val emoji = if (randomEmoji) EmojiGenerator.getRandomEmoji() else EmojiGenerator.getNextEmoji()

        return when (extension) {
            "java" -> "System.out.println(\"$emoji Debug point\");"
            "kt" -> "println(\"$emoji Debug point\")"
            "py" -> "print(\"$emoji Debug point\")"
            "js", "jsx", "ts", "tsx" -> "console.log(\"$emoji Debug point\");"
            else -> "print(\"$emoji Debug point\");" // Default to JavaScript
        }
    }

    fun isFileSupported(file: PsiFile): Boolean {
        val extension = getFileExtension(file)
        return supportedExtensions.contains(extension)
    }

    private fun getFileExtension(file: PsiFile): String {
        val fileName = file.name
        val lastDotIndex = fileName.lastIndexOf('.')
        return if (lastDotIndex != -1) {
            fileName.substring(lastDotIndex + 1).lowercase()
        } else {
            ""
        }
    }
}