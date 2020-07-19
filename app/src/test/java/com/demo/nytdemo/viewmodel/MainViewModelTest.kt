package com.demo.nytdemo.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.nytdemo.base.BaseTest
import com.demo.nytdemo.repository.MostPopularApiRepo
import com.demo.nytdemo.utils.DemoService
import com.demo.nytdemo.utils.adapter.RecyclerViewAdapter
import com.dev.ccodetest.di.configureTestAppComponent
import com.test.dynamicdemo.util.Coroutines
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.*
import org.koin.core.context.startKoin
import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import org.koin.test.KoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
class MainViewModelTest : BaseTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val vm: MainViewModel by inject()
    private val repo: MostPopularApiRepo by inject()


    @Test
    fun checkApiResponse_and_resettingTheList() = runBlocking {
        val response = repo.getApiResponse()
        vm.resetList(response)
        assertNotNull(response)
        assertNotNull(vm.mData)
        print(vm.mData)
    }

}
