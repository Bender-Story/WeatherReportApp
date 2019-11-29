package com.rahul.weatherreportapp.network

import com.rahul.weatherreportapp.data.SearchResults
import com.rahul.weatherreportapp.data.WeatherDetailsResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Service Interface to fetch data
 *
 */
interface ServiceInterface {
    /**
     * get repository list from the service
     */
    @GET("search.ashx")
    fun getSearchList(
        @Query("key") key: String?, @Query("q") location: String?,
        @Query("format") format: String = "json"
    ): Observable<SearchResults>

    @GET("weather.ashx")
    fun getWeatherDetails(
        @Query("key") key: String?, @Query("q") location: String?,
        @Query("num_of_days") days: Int = 1,
        @Query("format") format: String = "json"
    ): Observable<WeatherDetailsResult>
}