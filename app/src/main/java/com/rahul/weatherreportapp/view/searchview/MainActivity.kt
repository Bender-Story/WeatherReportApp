package com.rahul.weatherreportapp.view.searchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.rahul.weatherreportapp.R
import com.rahul.weatherreportapp.component.ActivityUIExt
import com.rahul.weatherreportapp.database.AppDatabase
import com.rahul.weatherreportapp.databinding.ActivityMainBinding
import com.rahul.weatherreportapp.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_recyclerview_recent.*
import kotlinx.android.synthetic.main.view_recylerview_results.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.app.Activity

import android.view.inputmethod.InputMethodManager
import com.rahul.weatherreportapp.Constants.LOCATION
import com.rahul.weatherreportapp.navigation.Navigator
import com.rahul.weatherreportapp.view.detailsView.WeatherDetailsActivity


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding:ActivityMainBinding

    var database: AppDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
         binding.viewModel=viewModel
        binding.executePendingBindings()
        initDataBase()
        onSearchClick()
        addObservers()
    }

    /**
     * observer to check live data changes
     */
    private fun addObservers(){
        viewModel.searchList.observe(this, Observer {
            initSearchRecyclerView()
            viewModel.checkIfListAreEmpty()
            viewModel.isSearchNotEmpty()
        })

        viewModel.recentList.observe(this, Observer {
            initRecentRecyclerView()
            viewModel.checkIfListAreEmpty()
            viewModel.isRecentNotEmpty()
        })

    }

    /**
     * load search recyclerview with latest changes
     */
    private fun initSearchRecyclerView() {

            val rowViewModels = viewModel?.getSearchRowViewModel{
                var selectedData=it?.copy(timeStamp = System.currentTimeMillis())
                    viewModel.addSelectedData(database,selectedData)
                    goNext(it?.key?:"")

            } as ArrayList<MainRowViewModel>?
            // Add list to adapter using UI Thread
            searchResultsRecyclerView.adapter =
                MainAdapter(rowViewModels)
            searchResultsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)


    }

    /**
     * load recent recyclerview with latest changes
     */
    private fun initRecentRecyclerView() {

            val rowViewModels = viewModel?.getRecentRowViewModel{
                                        goNext(it?.key?:"")
            } as ArrayList<MainRowViewModel>?

            // Add list to adapter using UI Thread
            recentSearchRecyclerView.adapter =
                MainAdapter(rowViewModels)
            recentSearchRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)


    }

    private fun goNext(key: String) {
        var bundle=Bundle()
        bundle.putString(LOCATION,key)
        Navigator.goToActivity(this, WeatherDetailsActivity::class.java,false,bundle = bundle)
    }

   private fun hideSoftKeyboard() {
        val inputMethodManager = getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            currentFocus!!.windowToken, 0
        )
    }

    /**
     * search click listener
     */
    private fun onSearchClick(){
            searchButton.setOnClickListener {
                hideSoftKeyboard()
                val text=searchEditText.text.toString()
                if(text.isNotEmpty()){
                    searchEditText.text.clear()
                    fetchSearchResults(text)
                    }
                else
                    viewModel.searchList.postValue(listOf())
            }

    }

    private fun fetchSearchResults(location:String?){
        if(NetworkUtils.isNetworkAvailable(this)) {
            val dialog=showLoadingDialog()
            viewModel.fetchSearchList(location, {
                dialog.dismiss()
            }, {
                dialog.dismiss()
                showErrorDialog(location,it)
            })
        }else{
            showErrorDialog(location,getString(R.string.network_error))
        }
    }

    private fun showLoadingDialog(): AlertDialog {
       return ActivityUIExt(this).buildLoadingDialog()
    }



    private fun showErrorDialog(location:String?,error:String){
        ActivityUIExt(this).buildDialog(error,onReload={
            fetchSearchResults(location)
        },onNegitive={viewModel.searchList.postValue(listOf())})
    }

    private fun initDataBase(){
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        viewModel.getSelectedData(database)
    }
}


