package com.grayherring.devtalks.ui.home

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.grayherring.devtalks.R.layout.fragment_talkitem_list
import com.grayherring.devtalks.base.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_talkitem_list.*


class TalkItemFragment : BaseFragment<HomeState, HomeViewModel>() {
  override fun viewModelClass() = HomeViewModel::class.java

  override fun viewLayout() = fragment_talkitem_list

  override fun bindView(state: HomeState) {
    talkList.adapter.notifyDataSetChanged()
  }

  private var mColumnCount = 1

  override fun setUpEventStream() {
    if (mColumnCount <= 1) {
      talkList.layoutManager = LinearLayoutManager(
          context)
    } else {
      talkList.layoutManager = GridLayoutManager(
          context,
          mColumnCount)
    }
    talkList.adapter = TalkViewAdapter(viewModel)

  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      mColumnCount = arguments.getInt(ARG_COLUMN_COUNT)
    }
  }

  companion object {
    // TODO: Customize parameter argument names
    private val ARG_COLUMN_COUNT = "column-count"

    // TODO: Customize parameter initialization
    fun newInstance(columnCount: Int): TalkItemFragment {
      val fragment = TalkItemFragment()
      val args = Bundle()
      args.putInt(ARG_COLUMN_COUNT,
          columnCount)
      fragment.arguments = args
      return fragment
    }
  }
}
