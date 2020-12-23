package com.eb.githubsearcher.network

import com.eb.githubsearcher.models.BaseResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException

/**
 * Created by ebayhan on 12/23/20.
 */
internal fun <T : BaseResponse> Call<T>.sendRequest(listener: ResponseListener<T>) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>?, response: Response<T>?) {
            val baseResponse = response?.body()
            if (baseResponse == null || !response.isSuccessful) {
                listener.onFailure(ErrorCodes.BAD_REQUEST.message)
                return
            }

            if (!baseResponse.incompleteResults) {
                listener.onSuccess(baseResponse)
            } else {

                listener.onFailure(ErrorCodes.API_ERROR.message)
            }
        }

        override fun onFailure(call: Call<T>?, t: Throwable?) {
            when (t) {
                is ConnectException -> {
                    listener.onFailure(ErrorCodes.CONNECTION_ERROR.message)
                }
                is TimeoutException -> {
                    listener.onFailure(ErrorCodes.CONNECTION_TIMEOUT_ERROR.message)
                }
                is SSLHandshakeException, is SSLPeerUnverifiedException -> {
                    listener.onFailure(ErrorCodes.CONNECTION_SSL_ERROR.message)
                }
                else -> {
                    t?.printStackTrace()
                    listener.onFailure(ErrorCodes.CONNECTION_UNKNOWN_ERROR.message)
                }
            }
        }
    })
}