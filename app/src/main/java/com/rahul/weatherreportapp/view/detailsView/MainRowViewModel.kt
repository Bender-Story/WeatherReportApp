package com.rahul.weatherreportapp.view.detailsView

import com.rahul.weatherreportapp.data.Result

/**
 * View model for the row item of the list which loads data through databinding
 */
class MainRowViewModel(val result: Result?,val  onSelected: (Result?) -> Unit){
    /**
     * get the star param and convert it to string
     */
    fun getLocationName():String? {
       return result?.let { it.areaName.first().value + " - "+ it.country.first().value}
    }

    fun onClick() {
        onSelected.invoke(result)
    }

}