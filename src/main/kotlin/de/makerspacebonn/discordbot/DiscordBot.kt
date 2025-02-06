package de.makerspacebonn.discordbot

import de.makerspacebonn.discordbot.provider.CredentialsProvider
import de.makerspacebonn.discordbot.reaction.EventReaction
import de.makerspacebonn.discordbot.reaction.PingPongReaction
import discord4j.core.DiscordClient
import discord4j.core.event.domain.Event
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.mono
import org.slf4j.LoggerFactory

fun main(args: Array<String>) = DiscordBot(args).start()

class DiscordBot(args: Array<String>) {

    private val credentialsProvider = CredentialsProvider(args)
    private val client = DiscordClient.create(credentialsProvider.discordToken)
    private val logger = LoggerFactory.getLogger(DiscordBot::class.java)

    private val reactions: List<EventReaction> = listOf(
        PingPongReaction
    )

    fun start() {
        client.withGateway { gateway ->
            logger.info("Bot is starting...")
            mono {
                gateway.on(Event::class.java)
                    .asFlow()
                    .collect { event ->
                        reactions
                            .filter { it.matches(event) }
                            .forEach { it.react(event) }
                    }
            }
        }.block()
    }
}