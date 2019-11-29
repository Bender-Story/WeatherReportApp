package com.rahul.weatherreportapp.network

import com.rahul.weatherreportapp.Constants.KEY
import com.rahul.weatherreportapp.ServiceType
import com.rahul.weatherreportapp.data.SearchResults
import com.rahul.weatherreportapp.data.WeatherDetailsResult
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf


/**
 * App service repo fetchs data for the viewmodel
 * helps to load data in database
 *
 */

class AppServiceRepo(serviceType: ServiceType):KoinComponent{
    val serviceAPIHelper: ServiceAPIHelper by inject{ parametersOf(serviceType) }
    /**
     * fetch search results
     * @param onSuccess success callback
     * @param onError error callback
     */
    fun getSearchResults(location:String, onSuccess: (SearchResults?) -> Unit,
                         onError: (String) -> Unit){

        serviceAPIHelper.getServiceinterface()!!.getSearchList(KEY,location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }

    /**
     * fetch weatherDetails results
     * @param onSuccess success callback
     * @param onError error callback
     */
    fun getweatherDetailsResults(location:String, onSuccess: (WeatherDetailsResult?) -> Unit,
                                 onError: (String) -> Unit){

        serviceAPIHelper.getServiceinterface()!!.getWeatherDetails(KEY,location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                        result -> onSuccess.invoke(result)
                },
                {
                        error -> onError.invoke(error.toString())
                }
            )
    }
}


