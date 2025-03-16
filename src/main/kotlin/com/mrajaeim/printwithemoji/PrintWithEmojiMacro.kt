package com.mrajaeim.printwithemoji

import com.intellij.codeInsight.template.*

class PrintWithEmojiMacro : Macro() {
    override fun getName(): String {
        return "printWithEmoji"
    }

    override fun getPresentableName(): String {
        return "printWithEmoji()"
    }

    override fun calculateResult(params: Array<Expression>, context: ExpressionContext): Result? {
        // Obtain the current file from the context
        val file = context.psiElementAtStartOffset?.containingFile ?: return null

        // Generate the print statement based on the file type using your LanguageUtils helper
        val printStatement = LanguageUtils.getDefaultPrintStatement(file)

        return TextResult(printStatement)
    }

    override fun calculateQuickResult(params: Array<Expression>, context: ExpressionContext): Result? {
        return calculateResult(params, context)
    }
}
