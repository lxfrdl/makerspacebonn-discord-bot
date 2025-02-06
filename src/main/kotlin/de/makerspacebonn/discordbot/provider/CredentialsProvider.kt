package de.makerspacebonn.discordbot.provider

import org.slf4j.LoggerFactory


class CredentialsProvider(private val args: Array<String>) {
    companion object {
        private const val DISCORD_TOKEN_NAME = "DISCORD_TOKEN"
    }

    private val logger = LoggerFactory.getLogger(CredentialsProvider::class.java)

    private fun firstArgument(): String? {
        return args.getOrNull(0)
    }

    private fun dotEnv(): String? {
        return DotenvProvider.dotenv[DISCORD_TOKEN_NAME]
    }

    private fun systemEnv(): String? {
        return System.getenv(DISCORD_TOKEN_NAME)
    }

    private fun String.inform(source: String) = this.also { logger.info("Found credentials in $source") }

    val discordToken by lazy {
        firstArgument()?.inform("first argument")
            ?: dotEnv()?.inform(".env file")
            ?: systemEnv()?.inform("env var")
            ?: run {
                throw IllegalStateException(
                    "No Discord token found. Please provide it " +
                            "as first argument, " +
                            "in a .env file called $DISCORD_TOKEN_NAME " +
                            "or as an environment variable called $DISCORD_TOKEN_NAME."
                )
            }
    }
}