package com.grayherring.devtalks.ui.home

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.View
import android.widget.TextView
import com.grayherring.devtalks.R.*
import com.grayherring.devtalks.data.model.Talk
import com.grayherring.devtalks.ui.home.events.ItemClickEvent

class TalkViewAdapter(private val homeViewModel: HomeViewModel) : Adapter<TalkViewAdapter.ViewHolder>() {

  val data get() = homeViewModel.lastState.talks

  override fun getItemCount(): Int {
    return data.size
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.mItem = data[position]
    holder.mIdView.text = data[position].title
    holder.mContentView.text = data[position].presenter
    holder.mView.setOnClickListener {
      homeViewModel.addEvent(ItemClickEvent(data[position]))
    }

  }

  override fun onCreateViewHolder(parent: android.view.ViewGroup,
                                  viewType: Int): ViewHolder {
    val view = android.view.LayoutInflater.from(parent.context)
        .inflate(layout.fragment_talkitem, parent, false)
    return ViewHolder(view)
  }

  inner class ViewHolder(val mView: android.view.View) : RecyclerView.ViewHolder(mView) {
    val mIdView: TextView
    val mContentView: TextView
    var mItem: Talk? = null

    init {
      mIdView = mView.findViewById<View>(id.title) as TextView
      mContentView = mView.findViewById<View>(id.name) as TextView
    }

    override fun toString(): String {
      return super.toString() + " '" + mContentView.text + "'"
    }
  }
}
