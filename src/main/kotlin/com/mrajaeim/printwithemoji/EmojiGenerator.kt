package com.mrajaeim.printwithemoji

object EmojiGenerator {
    private val emojis = listOf(
        "😀", "😎", "🚀", "💡", "🔥",
        "⚡", "🌟", "💯", "🎯", "🎮",
        "🌈", "🍕", "🦄", "🔍", "💻",
        "🤖", "👽", "🦸", "🧙", "🦊"
    )

    fun getRandomEmoji(): String {
        return emojis.random()
    }
}