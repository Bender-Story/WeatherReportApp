package com.rahul.weatherreportapp.view.detailsView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahul.weatherreportapp.R
import com.rahul.weatherreportapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_recyclerview_recent.*
import kotlinx.android.synthetic.main.view_recylerview_results.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
         binding.viewModel=viewModel
        binding.executePendingBindings()

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
        GlobalScope.launch(Dispatchers.Main) {
            val rowViewModels = viewModel?.getSearchRowViewModel{

            } as ArrayList<MainRowViewModel>?
            // Add list to adapter using UI Thread
            searchResultsRecyclerView.adapter = MainAdapter(rowViewModels)
            searchResultsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    /**
     * load recent recyclerview with latest changes
     */
    private fun initRecentRecyclerView() {
        GlobalScope.launch(Dispatchers.Main) {

            val rowViewModels = viewModel?.getRecentRowViewModel{

            } as ArrayList<MainRowViewModel>?

            // Add list to adapter using UI Thread
            recentSearchRecyclerView.adapter = MainAdapter(rowViewModels)
            recentSearchRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    /**
     * search click listener
     */
    private fun onSearchClick(){
            searchButton.setOnClickListener {
                val text=searchEditText.text.toString()
                if(text.isNotEmpty())
                    fetchSearchResults(text)
            }

    }

    private fun fetchSearchResults(location:String?){
        viewModel.fetchSearchList(location,{},{})
    }
}


