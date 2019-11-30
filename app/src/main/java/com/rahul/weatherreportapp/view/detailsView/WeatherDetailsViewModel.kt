package com.rahul.weatherreportapp.view.detailsView

import androidx.lifecycle.ViewModel
import com.rahul.weatherreportapp.ServiceType
import com.rahul.weatherreportapp.network.AppServiceRepo
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class WeatherDetailsViewModel : ViewModel(), KoinComponent {
    private val appServiceRepo: AppServiceRepo by inject{ parametersOf(ServiceType.API) }

    /**
     *  call service to fetch trending list from the github and update the mutable list
     */
    fun  fetchCityWeatherDetails(location:String?,
                         onSuccess: () -> Unit,
                         onError: (String) -> Unit
    ) { appServiceRepo.getweatherDetailsResults(location,{ response ->
        onSuccess.invoke()
    }, {
        onError.invoke(it)
    })
    }

}