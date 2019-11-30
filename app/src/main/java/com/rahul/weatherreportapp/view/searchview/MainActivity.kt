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
import android.text.TextWatcher

import com.rahul.weatherreportapp.Constants.LOCATION
import com.rahul.weatherreportapp.navigation.Navigator
import com.rahul.weatherreportapp.view.detailsView.WeatherDetailsActivity
import android.text.Editable
import android.os.Handler


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.executePendingBindings()
        initDataBase()
        addTextWatcher()
        addObservers()
    }

    /**
     * search click listener
     */
    private fun getSearchList(text: String) {
        if (text.isNotEmpty()) {
            fetchSearchResults(text)
        } else
            viewModel.searchList.postValue(listOf())


    }

    private fun addTextWatcher() {

        searchEditText.addTextChangedListener(object : TextWatcher {
            var considerChange = false
            override fun afterTextChanged(s: Editable) {
                if (considerChange) {
                    Handler().postDelayed({
                        getSearchList(s.toString())
                    }, 100)
                    considerChange = false
                }

            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                considerChange = true
            }
        })

    }

    /**
     * observer to check live data changes
     */
    private fun addObservers() {
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

        val rowViewModels = viewModel?.getSearchRowViewModel {
            var selectedData = it?.copy(timeStamp = System.currentTimeMillis())
            viewModel.addSelectedData(database, selectedData)
            goNext(it?.key ?: "")

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

        val rowViewModels = viewModel?.getRecentRowViewModel {
            var selectedData = it?.copy(timeStamp = System.currentTimeMillis())
            viewModel.addSelectedData(database, selectedData)
            goNext(it?.key ?: "")
        } as ArrayList<MainRowViewModel>?

        // Add list to adapter using UI Thread
        recentSearchRecyclerView.adapter =
            MainAdapter(rowViewModels)
        recentSearchRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)


    }

    private fun goNext(key: String) {
        var bundle = Bundle()
        bundle.putString(LOCATION, key)
        Navigator.goToActivity(this, WeatherDetailsActivity::class.java, false, bundle = bundle)
    }



    private fun fetchSearchResults(location: String?) {
        if (NetworkUtils.isNetworkAvailable(this)) {
//            val dialog=showLoadingDialog()
            viewModel.fetchSearchList(location, {
                //                dialog.dismiss()
            }, {
                //                dialog.dismiss()
                showErrorDialog(location, it)
            })
        } else {
            showErrorDialog(location, getString(R.string.network_error))
        }
    }

    private fun showLoadingDialog(): AlertDialog {
        return ActivityUIExt(this).buildLoadingDialog()
    }


    private fun showErrorDialog(location: String?, error: String) {
        ActivityUIExt(this).buildDialog(error, onReload = {
            fetchSearchResults(location)
        }, onNegitive = { viewModel.searchList.postValue(listOf()) })
    }

    private fun initDataBase() {
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        viewModel.getSelectedData(database)
    }
}


