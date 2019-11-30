package com.rahul.weatherreportapp.network

import com.rahul.weatherreportapp.BaseTest
import com.rahul.weatherreportapp.ServiceType
import org.junit.Assert
import org.junit.Test
import org.koin.core.inject
import org.koin.core.parameter.parametersOf


class AppServiceRepoTest: BaseTest(){
    val appServiceRepo: AppServiceRepo by inject{ parametersOf(ServiceType.MOCK) }

    @Test
    fun `get search results is not null or empty`(){
        appServiceRepo.getSearchResults("",{
            Assert.assertTrue(it != null)
            Assert.assertTrue(it?.search_api?.result?.isNotEmpty()?: false)
        },{
            assert(false)
        })
    }

    @Test
    fun `get search results repository size is 2`(){
        appServiceRepo.getSearchResults("",{
            Assert.assertTrue(it?.search_api?.result?.size==3)
        },{
            assert(false)
        })
    }

    @Test
    fun `get search results repository check Values`(){
        appServiceRepo.getSearchResults("",{
            Assert.assertTrue(it?.search_api?.result?.first()?.areaName?.first()?.value  =="London")
            Assert.assertTrue(it?.search_api?.result?.first()?.country?.first()?.value  =="United Kingdom")


        },{
            assert(false)
        })
    }

    @Test
    fun `get weather details is not null or empty`(){
        appServiceRepo.getweatherDetailsResults("",{
            Assert.assertTrue(it != null)
            Assert.assertTrue(it?.data?.current_condition?.isNotEmpty()?: false)
        },{
            assert(false)
        })
    }

    @Test
    fun `get weather details repository size is 2`(){
        appServiceRepo.getweatherDetailsResults("",{
            Assert.assertTrue(it?.data?.current_condition?.size==1)
        },{
            assert(false)
        })
    }

    @Test
    fun `get weather details repository check Values`(){
        appServiceRepo.getweatherDetailsResults("",{
            Assert.assertTrue(it?.data?.current_condition?.first()?.FeelsLikeC  =="-1")
            Assert.assertTrue(it?.data?.current_condition?.first()?.FeelsLikeF  =="30")


        },{
            assert(false)
        })
    }
}