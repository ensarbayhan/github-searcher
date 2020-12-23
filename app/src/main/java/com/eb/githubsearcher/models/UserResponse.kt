package com.eb.githubsearcher.models

import com.google.gson.annotations.SerializedName

/**
 * Created by ebayhan on 12/23/20.
 */
data class UserResponse(

        @SerializedName("items")
        var items: ArrayList<User> = arrayListOf()

) : BaseResponse()