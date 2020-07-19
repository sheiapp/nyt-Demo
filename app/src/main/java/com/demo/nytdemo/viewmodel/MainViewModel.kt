package com.demo.nytdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.nytdemo.model.ApiResponse
import com.demo.nytdemo.model.Result
import com.demo.nytdemo.repository.MostPopularApiRepo
import com.demo.nytdemo.utils.Event
import com.demo.nytdemo.utils.ItemClickHandler
import com.demo.nytdemo.utils.adapter.RecyclerViewAdapter
import com.test.dynamicdemo.util.Coroutines
import kotlinx.coroutines.Job
import org.jetbrains.annotations.TestOnly
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MainViewModel(private val mostPopularApiRepo: MostPopularApiRepo) : ViewModel(),
    KoinComponent, ItemClickHandler {
    private lateinit var getApiDataJob: Job
    private val failedMessage = MutableLiveData<String>()
    var mData = mutableListOf<Result>()
    val navigateToDetails = MutableLiveData<Event<Result>>()
    private val userClickedData = MutableLiveData<Result>()


    val adapter: RecyclerViewAdapter by inject {
        parametersOf(mData, this)
    }

    fun getFailureMessage(): LiveData<String> {
        return failedMessage
    }

    fun getUserClickedData(): LiveData<Result> {
        return userClickedData
    }

    val userClickedEvent: LiveData<Event<Result>>
        get() = navigateToDetails


    fun getApiData() {
        getApiDataJob = Coroutines.ioThenMain({
            mostPopularApiRepo.getApiResponse()
        },
            {
                it?.let { resetList(it) }
                adapter.notifyDataSetChanged()
            }, {
                failedMessage.value = it!!
            })
    }


    override fun onCleared() {
        super.onCleared()
        if (::getApiDataJob.isInitialized) getApiDataJob.cancel()
    }

     fun resetList(it: ApiResponse<List<Result>>) {
        mData.clear()
        it.results?.let { mData.addAll(it) }
        adapter.elements = mData
    }

    override fun onItemClick(clickedData: Result) {
        navigateToDetails.value = Event(clickedData)
        userClickedData.value = clickedData
    }
}