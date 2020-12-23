package com.eb.githubsearcher.models

import com.google.gson.annotations.SerializedName

/**
 * Created by ebayhan on 12/23/20.
 */
open class BaseResponse(
        @SerializedName("total_count")
        var totalCount: Int = 0,

        @SerializedName("incomplete_results")
        var incompleteResults: Boolean = false
)