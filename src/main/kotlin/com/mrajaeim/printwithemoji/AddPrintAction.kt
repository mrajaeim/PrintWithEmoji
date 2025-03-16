package com.mrajaeim.printwithemoji

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class AddPrintAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val psiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return

        if (!LanguageUtils.isFileSupported(psiFile)) {
            return
        }

        val document = editor.document
        val caretModel = editor.caretModel
        val primaryCaret = caretModel.primaryCaret
        val selectedText = primaryCaret.selectedText

        val offset = primaryCaret.offset
        val lineNumber = document.getLineNumber(offset)
        val lineEndOffset = document.getLineEndOffset(lineNumber)

        val printStatement = if (selectedText.isNullOrEmpty()) {
            LanguageUtils.getDefaultPrintStatement(psiFile)
        } else {
            LanguageUtils.getPrintStatement(psiFile, selectedText)
        }

        WriteCommandAction.runWriteCommandAction(project) {
            document.insertString(lineEndOffset, "\n${printStatement}")
        }
    }

    override fun update(e: AnActionEvent) {
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        val editor = e.getData(CommonDataKeys.EDITOR)

        e.presentation.isEnabledAndVisible = (psiFile != null &&
                editor != null &&
                LanguageUtils.isFileSupported(psiFile))
    }
}