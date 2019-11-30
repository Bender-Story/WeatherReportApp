package com.rahul.weatherreportapp.view.detailsView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rahul.weatherreportapp.Constants.LOCATION
import com.rahul.weatherreportapp.R
import com.rahul.weatherreportapp.databinding.ActivityWeatherDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDetailsActivity : AppCompatActivity() {
    private val viewModel by viewModel<WeatherDetailsViewModel>()
    private lateinit var binding:ActivityWeatherDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_weather_details)
        binding.viewModel=viewModel
        binding.executePendingBindings()
        fetchDetails()
    }

    private fun fetchDetails(){

        viewModel.fetchCityWeatherDetails( intent.extras.getString(LOCATION),{},{})
    }
}
