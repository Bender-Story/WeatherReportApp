package com.rahul.weatherreportapp.view.searchview

import com.rahul.weatherreportapp.data.SelectedData

/**
 * View model for the row item of the list which loads data through databinding
 */
class MainRowViewModel(val result: SelectedData?,val  onSelected: (SelectedData?) -> Unit){
    /**
     * get the star param and convert it to string
     */
    fun getLocationName():String? {
       return result?.let { it.areaName + " - "+ it.country}
    }

    fun onClick() {
        onSelected.invoke(result)
    }

}