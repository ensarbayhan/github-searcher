package com.eb.githubsearcher.di

import com.eb.githubsearcher.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by ebayhan on 12/23/20.
 */
object GithubSearcherKoin {
    val appModule = module {
        viewModel { MainViewModel(get()) }
        single { NetworkModule.getRetrofit() }
    }
}