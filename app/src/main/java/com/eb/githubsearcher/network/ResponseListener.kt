package com.eb.githubsearcher.network

import com.eb.githubsearcher.models.BaseResponse

/**
 * Created by ebayhan on 12/23/20.
 */
interface ResponseListener<T : BaseResponse> {

    fun onSuccess(response: T?)
    fun onFailure(errorMessage: String)
}