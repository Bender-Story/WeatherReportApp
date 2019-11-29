package com.rahul.weatherreportapp.view.detailsView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahul.weatherreportapp.databinding.RowViewSearchBinding

/**
 * Main Adapter this the adapter used to load
 * the data in the recyclerview of Main activity
 */
class MainAdapter(val items: ArrayList<MainRowViewModel>?) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowViewSearchBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items?.get(position)!!)

    inner class ViewHolder(val binding: RowViewSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainRowViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }
    }
}
