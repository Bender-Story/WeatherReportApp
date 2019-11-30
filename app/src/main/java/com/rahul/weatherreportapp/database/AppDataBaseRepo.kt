package com.rahul.weatherreportapp.database

import com.rahul.weatherreportapp.data.SelectedData

/**
 * App database repo fetchs data for the viewmodel
 * helps to load and retrive data from database
 *
 */

    fun AppDatabase.addDataToLocalDatabase(selectedData: SelectedData?){
            searchResultDao()?.insertAll(selectedData)
    }

    fun AppDatabase.deleteall(){
       searchResultDao()?.deleteAll()
    }

    fun AppDatabase.getDataFromLocalDataBase(): List<SelectedData?>? {
        return searchResultDao()?.getAll()
    }

    fun AppDatabase.delete(selectedData: SelectedData){
        searchResultDao()?.delete(selectedData)
    }