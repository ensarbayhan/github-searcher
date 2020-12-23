package com.eb.githubsearcher.network

import com.eb.githubsearcher.models.RepositoryResponse
import com.eb.githubsearcher.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by ebayhan on 12/23/20.
 */
interface GithubApi {
    @GET("repositories")
    fun searchRepositories(@Query("q") query: String): Call<RepositoryResponse>

    @GET("users")
    fun searchUsers(@Query("q") query: String): Call<UserResponse>
}