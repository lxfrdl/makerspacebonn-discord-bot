package de.makerspacebonn.discordbot.reaction

import discord4j.core.event.domain.Event
import discord4j.core.event.domain.message.MessageCreateEvent
import discord4j.core.event.domain.message.MessageUpdateEvent
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger

object PingPongReaction : EventReaction {
    override val logger: Logger = getLogger(PingPongReaction::class.java)

    override fun matches(event: Event): Boolean =
        (event is MessageUpdateEvent && event.currentContent.get() == "!ping") ||
                (event is MessageCreateEvent && event.message.content == "!ping")

    override suspend fun react(event: Event) {
        when (event) {
            is MessageCreateEvent -> event.message.channel
            is MessageUpdateEvent -> event.channel
            else -> {
                logger.warn("Unhandled event type: ${event::class.simpleName}")
                return
            }
        }.awaitSingle().createMessage("Pong!").awaitSingle()
    }
}
