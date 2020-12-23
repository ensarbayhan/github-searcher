package com.eb.githubsearcher.network

/**
 * Created by ebayhan on 12/23/20.
 */
internal enum class ErrorCodes(var message: String) {
    CONNECTION_ERROR("Please check your internet connection."),
    CONNECTION_TIMEOUT_ERROR("Connection timed out."),
    CONNECTION_SSL_ERROR("SSL connection cannot be verified."),
    CONNECTION_UNKNOWN_ERROR("Unknown connection error."),
    BAD_REQUEST("Bad request!"),
    API_ERROR("Unknown API error!"),
}