package com.mrajaeim.printwithemoji

import java.util.Queue
import java.util.LinkedList

object EmojiGenerator {
    private val emojis = listOf(
        "😀", "😎", "🚀", "💡", "🔥",
        "⚡", "🌟", "💯", "🎯", "🎮",
        "🌈", "🍕", "🦄", "🔍", "💻",
        "🤖", "👽", "🦸", "🧙", "🦊"
    )

    private val emojiQueue: Queue<String> = LinkedList(emojis)

    fun getNextEmoji(): String {
        // Get the next emoji from the queue
        val nextEmoji = emojiQueue.poll() ?: run {
            // If the queue is empty, refill it and get the next emoji
            emojiQueue.addAll(emojis)
            emojiQueue.poll() ?: "😎" // Fallback to a default emoji
        }
        // Add the emoji back to the end of the queue
        emojiQueue.offer(nextEmoji)
        return nextEmoji
    }
}