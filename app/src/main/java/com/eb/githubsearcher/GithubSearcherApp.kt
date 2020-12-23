package com.eb.githubsearcher

import android.app.Application
import com.eb.githubsearcher.di.GithubSearcherKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by ebayhan on 12/23/20.
 */
class GithubSearcherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GithubSearcherApp)
            modules(GithubSearcherKoin.appModule)
        }
    }
}