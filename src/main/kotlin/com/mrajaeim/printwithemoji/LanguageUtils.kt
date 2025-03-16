package com.mrajaeim.printwithemoji

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.PsiFile

object LanguageUtils {
    fun getPrintStatement(psiFile: PsiFile, variableName: String): String {
        val language = psiFile.language.id.lowercase()
        val fileType = psiFile.fileType.name.lowercase()
        val emoji = EmojiGenerator.getRandomEmoji()

        return when {
            language.contains("java") -> "System.out.println(\"$emoji $variableName = \" + $variableName);"
            language.contains("kotlin") -> "println(\"$emoji $variableName = \${$variableName}\")"
            language.contains("python") || fileType.contains("python") -> "print(f\"$emoji $variableName = {$variableName}\")"
            language.contains("javascript") || language.contains("ecmascript") ||
                    fileType.contains("js") || fileType.contains("jsx") ||
                    fileType.contains("ts") || fileType.contains("tsx") -> "console.log(\"$emoji $variableName\", $variableName);"
            else -> "console.log(\"$emoji $variableName\", $variableName);" // Default to JavaScript
        }
    }

    fun getDefaultPrintStatement(psiFile: PsiFile): String {
        val language = psiFile.language.id.lowercase()
        val fileType = psiFile.fileType.name.lowercase()
        val emoji = EmojiGenerator.getRandomEmoji()

        return when {
            language.contains("java") -> "System.out.println(\"$emoji Debug point\");"
            language.contains("kotlin") -> "println(\"$emoji Debug point\")"
            language.contains("python") || fileType.contains("python") -> "print(\"$emoji Debug point\")"
            language.contains("javascript") || language.contains("ecmascript") ||
                    fileType.contains("js") || fileType.contains("jsx") ||
                    fileType.contains("ts") || fileType.contains("tsx") -> "console.log(\"$emoji Debug point\");"
            else -> "console.log(\"$emoji Debug point\");" // Default to JavaScript
        }
    }

    fun isLanguageSupported(language: Language): Boolean {
        val languageId = language.id.lowercase()
        return languageId.contains("java") ||
                languageId.contains("kotlin") ||
                languageId.contains("python") ||
                languageId.contains("javascript") ||
                languageId.contains("ecmascript") ||
                languageId.contains("typescript") ||
                languageId.contains("jsx") ||
                languageId.contains("tsx")
    }
}