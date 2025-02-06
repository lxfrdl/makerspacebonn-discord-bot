package de.makerspacebonn.discordbot.reaction

import discord4j.core.event.domain.Event
import org.slf4j.Logger

interface EventReaction {
    val logger: Logger

    fun matches(event: Event): Boolean

    suspend fun react(event: Event): Unit
}

