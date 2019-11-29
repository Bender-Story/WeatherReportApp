package com.rahul.weatherreportapp.network

import com.google.gson.Gson
import com.rahul.weatherreportapp.data.SearchResults
import com.rahul.weatherreportapp.data.WeatherDetailsResult
import io.reactivex.Observable
import retrofit2.mock.BehaviorDelegate


/**
 * Mock service Interface
 *
 */
class MockServiceImpl(private val delegate: BehaviorDelegate<ServiceInterface>) :ServiceInterface{
    override fun getSearchList(key: String?, location: String?, format: String): Observable<SearchResults> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeatherDetails(
        key: String?,
        location: String?,
        days: Int,
        format: String
    ): Observable<WeatherDetailsResult> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}