package com.demo.nytdemo.repository

import com.demo.nytdemo.utils.DemoService
import com.test.dynamicdemo.util.SafeApiRequestApproach

class MostPopularApiRepo(private val demoService: DemoService): SafeApiRequestApproach() {
    suspend fun getApiResponse()=apiRequest {
        demoService.getMostPopularApiResponse("all-sections")
    }
}