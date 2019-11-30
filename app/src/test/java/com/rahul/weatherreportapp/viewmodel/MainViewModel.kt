package com.rahul.weatherreportapp.viewmodel

import android.net.NetworkInfo
import androidx.room.Room
import com.rahul.weatherreportapp.BaseTest
import com.rahul.weatherreportapp.data.Result
import com.rahul.weatherreportapp.data.SelectedData
import com.rahul.weatherreportapp.database.AppDatabase
import com.rahul.weatherreportapp.view.searchview.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert
import org.junit.Test
import org.koin.core.inject
import org.mockito.Mockito
import java.io.IOException

class MainViewModelTest :BaseTest(){
    private val viewModel: MainViewModel by inject()
    lateinit var appDatabase:AppDatabase
    @Test
    fun `get search results is not null or empty`(){
        viewModel.fetchSearchList("",{
            Assert.assertTrue(it != null)
            Assert.assertTrue(it?.search_api?.result?.isNotEmpty()?: false)
        },{
            assert(false)
        })
    }

    @Test
    fun `get search results size is 2`(){
        viewModel.fetchSearchList("",{
            Assert.assertTrue(it?.search_api?.result?.size==3)
        },{
            assert(false)
        })
    }

    @Test
    fun `get search results repository check Values`(){
        viewModel.fetchSearchList("",{
            Assert.assertTrue(it?.search_api?.result?.first()?.areaName?.first()?.value  =="London")
            Assert.assertTrue(it?.search_api?.result?.first()?.country?.first()?.value  =="United Kingdom")


        },{
            assert(false)
        })
    }

    @Test
    fun `check If List Are not Empty`(){
        var data= Mockito.mock(SelectedData::class.java)
        viewModel.recentList.postValue(listOf(data))
        var data2= Mockito.mock(Result::class.java)
        viewModel.searchList.postValue(listOf(data2))
        viewModel.checkIfListAreEmpty()
       Assert.assertFalse( viewModel.showNoItems.get()?:false)
    }

    @Test
    fun `check If List Are Empty`(){
        Assert.assertTrue( viewModel.showNoItems.get()?:false)
    }

    @Test
    fun `check If search results Are not Empty`(){
        var data= Mockito.mock(SelectedData::class.java)
        viewModel.recentList.postValue(listOf(data))
        var data2= Mockito.mock(Result::class.java)
        viewModel.searchList.postValue(listOf(data2))
        viewModel.isSearchNotEmpty()
        Assert.assertTrue( viewModel.showSearch.get()?:false)
    }

    @Test
    fun `check If search results Are Empty`(){
        Assert.assertFalse( viewModel.showSearch.get()?:false)
    }

    @Test
    fun `check If recent results Are not Empty`(){
       var data= Mockito.mock(SelectedData::class.java)
        viewModel.recentList.postValue(listOf(data))
        viewModel.isSearchNotEmpty()
        Assert.assertTrue( viewModel.showrecent.get()?:false)
    }

    @Test
    fun `check If recent results Are Empty`(){
        Assert.assertFalse( viewModel.showrecent.get()?:false)
    }
    @Test
    fun `get Search RowViewModel empty`(){
       var list= viewModel.getSearchRowViewModel {  }
        Assert.assertTrue( list.isNullOrEmpty())


    }

    @Test
    fun `get Search RowViewModel have data`(){
        viewModel.fetchSearchList("",{
            var list= viewModel.getSearchRowViewModel {  }
            Assert.assertTrue( list?.size==3)
            Assert.assertTrue( list?.get(0)?.result?.country=="United Kingdom")
        },{ assert(false)})

    }

    @Test
    fun `get recent RowViewModel empty`(){
        var list= viewModel.getRecentRowViewModel {  }
        Assert.assertTrue( list.isNullOrEmpty())


    }

    @Test
    fun `get recent RowViewModel have data`(){
       var data= SelectedData("london,uk","uk","london",0)
        viewModel.recentList.postValue(listOf(data))

        var list= viewModel.getRecentRowViewModel {  }
        Assert.assertTrue( list?.size==1)
        Assert.assertTrue( list?.get(0)?.result?.country=="uk")

    }

    @Test
    fun addDataToLocalDatabase(){

            var data = SelectedData("london,uk", "uk", "london", 0)
            createDb()

            viewModel.addDataLocalData(appDatabase, data)
            Assert.assertNotNull(viewModel.getDataFromLocalData(appDatabase))
            closeDb()
    }

    @Test
    fun getDataToLocalDatabase(){

        var data = SelectedData("london,uk", "uk", "london", 0)
        createDb()

        viewModel.addDataLocalData(appDatabase, data)
        Assert.assertTrue(viewModel.getDataFromLocalData(appDatabase)?.size==1)
        closeDb()
    }
@Test
    fun addSelectedData(){
        var data = SelectedData("london,uk", "uk", "london", 0)
        createDb()

        viewModel.addSelectedData(appDatabase,data)
        Assert.assertTrue(viewModel.getDataFromLocalData(appDatabase)?.size==1)
        closeDb()
    }

    @Test
    fun getSelectedData(){

        var data = SelectedData("london,uk", "uk", "london", 0)
        createDb()

        viewModel.addDataLocalData(appDatabase, data)

        Assert.assertNotNull(viewModel.getSelectedData(appDatabase))
        closeDb()
    }

    fun createDb() {
        appDatabase = Room.inMemoryDatabaseBuilder(context!!, AppDatabase::class.java!!).
            allowMainThreadQueries()
            .build()
    }




    @Throws(IOException::class)
    fun closeDb() {
        appDatabase.close()
    }




}