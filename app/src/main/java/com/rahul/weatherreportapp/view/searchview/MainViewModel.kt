package com.rahul.weatherreportapp.view.searchview

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.weatherreportapp.ServiceType
import com.rahul.weatherreportapp.data.Result
import com.rahul.weatherreportapp.data.SelectedData
import com.rahul.weatherreportapp.database.*
import com.rahul.weatherreportapp.network.AppServiceRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MainViewModel : ViewModel(), KoinComponent {
    val searchList: MutableLiveData<List<Result>?> = MutableLiveData()
    val recentList: MutableLiveData<List<SelectedData?>?> = MutableLiveData()
    val showNoItems: ObservableField<Boolean> = ObservableField(true)
    val showSearch: ObservableField<Boolean> = ObservableField(false)
    val showrecent: ObservableField<Boolean> = ObservableField(false)
   private val appServiceRepo: AppServiceRepo by inject{ parametersOf(ServiceType.API) }

    /**
     *  call service to fetch trending list from the github and update the mutable list
     */
    fun  fetchSearchList(location:String?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) { appServiceRepo.getSearchResults(location,{ response ->
            onSuccess.invoke()
            searchList.postValue(response?.search_api?.result)
        }, {
                onError.invoke(it)
                checkIfListAreEmpty()
        })
    }

    // check if lists are empty
    fun checkIfListAreEmpty(){
        showNoItems.set(searchList.value.isNullOrEmpty() && recentList.value.isNullOrEmpty())
    }

    fun isSearchNotEmpty(){
        showSearch.set( !searchList.value.isNullOrEmpty())
    }
    fun isRecentNotEmpty(){
        showrecent.set( !recentList.value.isNullOrEmpty())
    }

    /**
     *  Create a  search MainRowViewModel list from the mutable list
     */
    fun getSearchRowViewModel(onSelected: (SelectedData?) -> Unit): List<MainRowViewModel>? {
        return searchList.value?.map {
            MainRowViewModel(
                SelectedData(
                    it.country.first().value + "," + it.areaName.first().value,
                    it.country.first().value,
                    it.areaName.first().value
                )
            ) { result ->
                onSelected.invoke(result)
            }
        }
    }

    /**
     *  Create a  recent MainRowViewModel list from the mutable list
     */
    fun getRecentRowViewModel(onSelected: (SelectedData?) -> Unit): List<MainRowViewModel>? {
        var list= recentList.value?.map {
            MainRowViewModel(it) { result ->
                onSelected.invoke(result)
            }
        }
        return list
//        return list?.sortedByDescending { it.result?.timeStamp }?.toList()
    }

    fun addSelectedData(db: AppDatabase?, selectedData: SelectedData?){
        GlobalScope.launch(Dispatchers.IO) {
            addDataLocalData(db, selectedData)
            var local = getDataFromLocalData(db)
            recentList.postValue(local)
        }
    }

    fun getSelectedData(db: AppDatabase?){
        GlobalScope.launch(Dispatchers.IO) {
            var local = getDataFromLocalData(db)
            recentList.postValue(local)
        }
    }

    fun addDataLocalData(db: AppDatabase?, selectedData: SelectedData?){
        db?.addDataToLocalDatabase(selectedData)
    }

    fun deleteLocalData(db: AppDatabase?){
        db?.deleteall()
    }

    fun getDataFromLocalData(db: AppDatabase?):  List<SelectedData?>? {
       return db?.getDataFromLocalDataBase()
    }

    fun deleteLocalData(db: AppDatabase?, selectedData: SelectedData){
        db?.delete(selectedData)
    }




}