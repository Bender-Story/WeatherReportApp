package com.rahul.weatherreportapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class SearchResults(
    val search_api: SearchApi
)

data class SearchApi(
    val result: List<Result>
)

data class Result(
    val areaName: List<AreaName>,
    val country: List<Country>,
    val latitude: String,
    val longitude: String,
    val population: String,
    val region: List<Region>,
    val weatherUrl: List<WeatherUrl>
)

data class AreaName(
    val value: String
)

data class Country(
    val value: String
)

data class Region(
    val value: String
)
data class WeatherUrl(
    val value: String
)
@Entity(tableName = "selectedDatabase")
data class SelectedData(
    @PrimaryKey val key:String,
    @ColumnInfo(name = "countryName") val country:String,
    @ColumnInfo(name = "areaName") val areaName:String,
    @ColumnInfo(name = "timestamp") val timeStamp:Long=0

)