package com.rahul.weatherreportapp.di


import com.rahul.weatherreportapp.ServiceType
import com.rahul.weatherreportapp.data.WeatherDetailsResult
import com.rahul.weatherreportapp.network.AppServiceRepo
import com.rahul.weatherreportapp.network.ServiceAPIHelper
import com.rahul.weatherreportapp.view.detailsView.WeatherDetailsViewModel
import com.rahul.weatherreportapp.view.searchview.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * App Module to load all the Koin injections
 *
 */
val appModule= module {
    factory { (serviceType : ServiceType) -> AppServiceRepo(serviceType) }
    factory { (serviceType : ServiceType) -> ServiceAPIHelper(serviceType) }
    viewModel { MainViewModel() }
    viewModel { WeatherDetailsViewModel() }
}