package com.demo.nytdemo.repository

import com.demo.nytdemo.base.BaseTest
import com.demo.nytdemo.utils.DemoService
import com.dev.ccodetest.di.configureTestAppComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
class MostPopularApiRepoTest : BaseTest() {

    private val demoService: DemoService by inject()


    @Test
    fun checkApiResponse() = runBlocking {
        val mostPopularApi = demoService.getMostPopularApiResponse("all-sections")
        assertNotNull(mostPopularApi.body()!!.results)
        assertEquals(mostPopularApi.code(), 200)
        print(mostPopularApi.body()!!.results)
    }
    @Test
    fun checkFailure()= runBlocking {
        val mostPopularApi = demoService.getMostPopularApiResponse("")
        assertFalse(mostPopularApi.isSuccessful)
        print(mostPopularApi)
    }
}