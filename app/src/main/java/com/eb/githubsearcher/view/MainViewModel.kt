package com.eb.githubsearcher.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eb.githubsearcher.models.BaseModel
import com.eb.githubsearcher.models.Repository
import com.eb.githubsearcher.models.RepositoryResponse
import com.eb.githubsearcher.models.UserResponse
import com.eb.githubsearcher.network.GithubApi
import com.eb.githubsearcher.network.ResponseListener
import com.eb.githubsearcher.network.sendRequest
import com.eb.githubsearcher.util.log

/**
 * Created by ebayhan on 12/23/20.
 */
class MainViewModel(private var githubApi: GithubApi) : ViewModel() {

    private val results: MutableLiveData<List<BaseModel>> by lazy {
        MutableLiveData<List<BaseModel>>().apply {
            value = emptyList()
        }
    }

    fun getResults(): LiveData<List<BaseModel>> {
        return results
    }

    fun sendSearchRequest(text: String) {
        githubApi.searchRepositories(text).sendRequest(object : ResponseListener<RepositoryResponse> {
            override fun onSuccess(response: RepositoryResponse?) {
                response?.let {
                    sendUserRequest(text, it.items)
                }
            }

            override fun onFailure(errorMessage: String) {
                log(errorMessage)
            }
        })
    }

    private fun sendUserRequest(text: String, repositories: ArrayList<Repository>) {
        githubApi.searchUsers(text).sendRequest(object : ResponseListener<UserResponse> {
            override fun onSuccess(response: UserResponse?) {
                response?.let {
                    results.value = arrayListOf<BaseModel>().apply {
                        addAll(repositories)
                        addAll(response.items)
                    }
                }
            }

            override fun onFailure(errorMessage: String) {
                log(errorMessage)
            }
        })
    }
}