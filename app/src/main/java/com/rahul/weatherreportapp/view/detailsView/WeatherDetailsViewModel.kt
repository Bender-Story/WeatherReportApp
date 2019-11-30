package com.rahul.weatherreportapp.view.detailsView

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.rahul.weatherreportapp.ServiceType
import com.rahul.weatherreportapp.data.WeatherDetailsResult
import com.rahul.weatherreportapp.network.AppServiceRepo
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class WeatherDetailsViewModel : ViewModel(), KoinComponent {
    private val appServiceRepo: AppServiceRepo by inject{ parametersOf(ServiceType.API) }
    val city: ObservableField<String> = ObservableField("")
    val imageUrl: ObservableField<String> = ObservableField("")
    val humidty: ObservableField<String> = ObservableField("")
    val weatherText: ObservableField<String> = ObservableField("")
    val tempInC: ObservableField<String> = ObservableField("")
    val tempInF: ObservableField<String> = ObservableField("")

    /**
     *  call service to fetch trending list from the github and update the mutable list
     */
    fun  fetchCityWeatherDetails(location:String?,
                                 onSuccess: (WeatherDetailsResult?) -> Unit,
                                 onError: (String) -> Unit
    ) { appServiceRepo.getweatherDetailsResults(location,{ response ->
        onSuccess.invoke(response)
        city.set("Location : $location")
        imageUrl.set(response?.data?.current_condition?.first()?.weatherIconUrl?.first()?.value ?:"")
        humidty.set("Humidity : " +response?.data?.current_condition?.first()?.humidity )
        weatherText.set("Description : "+response?.data?.current_condition?.first()?.weatherDesc?.first()?.value ?:"")
        tempInC.set("Centigrade : "+response?.data?.current_condition?.first()?.temp_C )
        tempInF.set("Fahrenheit :"+response?.data?.current_condition?.first()?.temp_F ?:"")
    }, {
        onError.invoke(it)
    })
    }

}