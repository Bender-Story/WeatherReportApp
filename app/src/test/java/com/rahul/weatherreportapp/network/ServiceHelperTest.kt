package com.rahul.weatherreportapp.network


import com.rahul.weatherreportapp.BaseTest
import com.rahul.weatherreportapp.ServiceType
import org.junit.Assert
import org.junit.Test

import java.util.*

class ServiceHelperTest : BaseTest(){

    @Test
    fun `check if instance of ServiceInterface is SimpleInterface`(){
        var serviceAPIHelper= ServiceAPIHelper(ServiceType.API)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is ServiceInterface)
    }

    @Test
    fun `check if instance of ServiceInterface is mockServiceImpl`(){
        var serviceAPIHelper=ServiceAPIHelper(ServiceType.MOCK)
        Assert.assertTrue(serviceAPIHelper.getServiceinterface() is MockServiceImpl)
    }
}