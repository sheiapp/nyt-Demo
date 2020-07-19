package com.demo.nytdemo.utils


import com.demo.nytdemo.model.ApiResponse
import com.demo.nytdemo.model.Result
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface DemoService {


    @GET("{section}/1.json?api-key=Zdie6yIOJDMopbnlAnmJ3Q45S2piAQkr")
    suspend fun getMostPopularApiResponse(@Path("section")section: String): Response<ApiResponse<List<Result>>>


    companion object {
        operator fun invoke(): DemoService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor {
                    val originalRequest = it.request()
                    val builder =
                        originalRequest.newBuilder()
                            .addHeader("Content-Type", "application/json")
                    val newRequest = builder.build()
                    it.proceed(newRequest)
                }
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/mostviewed/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DemoService::class.java)
        }
    }

}