package com.dev.ccodetest.di

import com.demo.nytdemo.utils.apiService
import com.demo.nytdemo.utils.injectionModule

fun configureTestAppComponent()
        = listOf(
    apiService,
    injectionModule
    )

