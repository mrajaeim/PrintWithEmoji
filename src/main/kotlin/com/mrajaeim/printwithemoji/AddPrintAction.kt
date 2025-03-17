package com.mrajaeim.printwithemoji

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiManager

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
        val lineStartOffset = document.getLineStartOffset(lineNumber)
        val lineEndOffset = document.getLineEndOffset(lineNumber)

        // Get the indentation of the current line
        val currentLineText = document.getText(TextRange(lineStartOffset, lineEndOffset))
        val indentation = currentLineText.takeWhile { it.isWhitespace() }

        val printStatement = if (selectedText.isNullOrEmpty()) {
            LanguageUtils.getDefaultPrintStatement(psiFile)
        } else {
            LanguageUtils.getPrintStatement(psiFile, selectedText)
        }

        WriteCommandAction.runWriteCommandAction(project) {
            // Insert the print statement with proper indentation
            document.insertString(lineEndOffset, "\n$indentation$printStatement")
        }
    }

    override fun update(e: AnActionEvent) {
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        val editor = e.getData(CommonDataKeys.EDITOR)
        val project = e.project
        val virtualFile = e.getData(CommonDataKeys.VIRTUAL_FILE)

        val psiFileVirtual = virtualFile?.let {
            PsiManager.getInstance(project ?: return@let null).findFile(it)
        }

        println("#### Virtual File: $virtualFile")
        println("#### PSI Virtual File: $psiFileVirtual")



        println("#### psiFile: $psiFile")
        println("#### editor: $editor")

        val isSupported = psiFile?.let { LanguageUtils.isFileSupported(it) } ?: false
        println("#### isSupported: $isSupported")

        e.presentation.isEnabledAndVisible = (psiFile != null && editor != null && isSupported)
    }
}