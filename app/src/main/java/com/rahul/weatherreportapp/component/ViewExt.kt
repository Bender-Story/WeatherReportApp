package com.rahul.weatherreportapp.component

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
/**
 * View extension class any modifications to any class that extends
 * view will be handled here
 *
 *
 */

/**
 * Make View visible and Gone
 * @param visible  when true it is visble else its gone
 */
@BindingAdapter("makeVisible")
fun View.makeVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

/**
 * Make TextView visible only when text is not empty
 * @param visible if text is empty the view will be made gone else visible
 */
@BindingAdapter("visibleOnText")
fun AppCompatTextView.visibleOnText(visible: String?) {
    this.visibility = if (visible.isNullOrEmpty()) View.GONE else View.VISIBLE
}