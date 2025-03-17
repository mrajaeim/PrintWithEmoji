package com.mrajaeim.printwithemoji

import java.util.Queue
import java.util.LinkedList

object EmojiGenerator {
    private val emojis = listOf(
        "☕", "🤘", "🚀", "💡", "🔥",
        "⚡", "🌟", "💯", "🎯", "🎮",
        "🌈", "🍕", "🦄", "🔍", "💻",
        "🤖", "👽", "🦸", "🧙", "🦊",
        "👌", "☄️", "🏔️", "⚡", "💸", "🎉", "🧿",
        "🎨", "🎭", "🎵", "🎲", "🎤",
        "📸", "🎯", "🚦", "🛸", "🏆",
        "🦜", "🦥", "🦩", "🦖", "🦕"
    )

    private val emojiQueue: Queue<String> = LinkedList(emojis)

    fun getNextEmoji(): String {
        val nextEmoji = emojiQueue.poll() ?: return emojis[0]
        emojiQueue.offer(nextEmoji)
        return nextEmoji
    }

    fun getRandomEmoji(): String {
        return emojis.random()
    }
}