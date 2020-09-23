package ru.geekbrains.kozirfm.kotlinproject.application

import android.app.Application
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        VK.addTokenExpiredHandler(tokenTracker)
    }

    private val tokenTracker = object: VKTokenExpiredHandler{
        override fun onTokenExpired() {
            //token expired
        }

    }
}