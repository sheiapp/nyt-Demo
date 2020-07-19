package com.demo.nytdemo

import android.app.Application
import com.demo.nytdemo.utils.apiService
import com.demo.nytdemo.utils.injectionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApp)
            modules(listOf(injectionModule,apiService))
        }
    }
}