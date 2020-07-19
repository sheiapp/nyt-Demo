package com.demo.nytdemo.utils

import com.demo.nytdemo.utils.adapter.RecyclerViewAdapter
import org.koin.dsl.module
import com.demo.nytdemo.model.Result
import com.demo.nytdemo.repository.MostPopularApiRepo
import com.demo.nytdemo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

val injectionModule = module {

    single { MostPopularApiRepo(get()) }
    viewModel { MainViewModel(get()) }
    factory { (elements: ArrayList<Result>,itemClickHandler:ItemClickHandler) -> RecyclerViewAdapter(elements,itemClickHandler) }
}
val apiService= module {
    single { DemoService() }
}