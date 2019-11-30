package com.rahul.weatherreportapp.viewmodel

import com.rahul.weatherreportapp.BaseTest
import com.rahul.weatherreportapp.view.detailsView.WeatherDetailsViewModel
import org.junit.Assert
import org.junit.Test
import org.koin.core.inject

class WeatherDetailsViewModelTest :BaseTest(){
    private val viewModel: WeatherDetailsViewModel by inject()
    @Test
    fun `get weather details is not null or empty`(){
        viewModel.fetchCityWeatherDetails("",{
            Assert.assertTrue(it != null)
            Assert.assertTrue(it?.data?.current_condition?.isNotEmpty()?: false)
        },{
            assert(false)
        })
    }

    @Test
    fun `get weather details repository size is 2`(){
        viewModel.fetchCityWeatherDetails("",{
            Assert.assertTrue(it?.data?.current_condition?.size==1)
        },{
            assert(false)
        })
    }

    @Test
    fun `get weather details repository check Values`(){
        viewModel.fetchCityWeatherDetails("",{
            Assert.assertTrue(it?.data?.current_condition?.first()?.FeelsLikeC  =="-1")
            Assert.assertTrue(it?.data?.current_condition?.first()?.FeelsLikeF  =="30")


        },{
            assert(false)
        })
    }
}