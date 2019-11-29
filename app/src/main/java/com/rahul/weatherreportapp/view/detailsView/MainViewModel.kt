package com.rahul.weatherreportapp.view.detailsView

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahul.weatherreportapp.ServiceType
import com.rahul.weatherreportapp.data.Result
import com.rahul.weatherreportapp.network.AppServiceRepo
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class MainViewModel : ViewModel(), KoinComponent {
    val searchList: MutableLiveData<List<Result>?> = MutableLiveData()
    val recentList: MutableLiveData<List<Result>?> = MutableLiveData()
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
    fun getSearchRowViewModel(onSelected: (Result?) -> Unit): List<MainRowViewModel>? {
        return searchList.value?.map {
            MainRowViewModel(it) { result->
                onSelected.invoke(result)
            }
        }
    }

    /**
     *  Create a  recent MainRowViewModel list from the mutable list
     */
    fun getRecentRowViewModel(onSelected: (Result?) -> Unit): List<MainRowViewModel>? {
        return recentList.value?.map {
            MainRowViewModel(it) { result->
                onSelected.invoke(result)
            }
        }
    }



}