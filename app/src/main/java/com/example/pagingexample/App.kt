package com.example.pagingexample

import android.app.Application
import com.example.pagingexample.data.UserRepo
import com.example.pagingexample.network.GitHubService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(module {
                single { UserRepo(get(), mutableMapOf()) }
                single { provideRetrofit()}
            })
        }
    }

    private fun provideRetrofit(): GitHubService = Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).build().create(
        GitHubService::class.java)
}