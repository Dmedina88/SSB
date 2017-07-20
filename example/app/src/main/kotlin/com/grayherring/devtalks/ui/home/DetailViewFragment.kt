package com.grayherring.devtalks.ui.home

import android.view.View.GONE
import android.view.View.VISIBLE
import com.grayherring.devtalks.base.util.applyThrottle
import com.grayherring.devtalks.base.util.plusAssign
import com.grayherring.devtalks.base.util.tentativelyUpdate
import com.grayherring.devtalks.ui.home.events.EditClicked
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.fragment_detail_view.*


class DetailViewFragment : com.grayherring.devtalks.base.ui.BaseFragment<HomeState, HomeViewModel>() {
  override fun viewModelClass() = HomeViewModel::class.java

  override fun viewLayout() = com.grayherring.devtalks.R.layout.fragment_detail_view

  override fun bindView(state: HomeState) {
    state.selectedTalk.let {
      presenter.tentativelyUpdate(it.presenter)
      title.tentativelyUpdate(it.title)
      platform.tentativelyUpdate(it.platform)
      description.tentativelyUpdate(it.description)
      date.tentativelyUpdate(it.date)
      email.tentativelyUpdate(it.email)
      slides.tentativelyUpdate(it.slides)
      streams.tentativelyUpdate(it.stream)
      edit.visibility = if (it.id == null) GONE else VISIBLE
    }
  }

  override fun setUpEventStream() {
    disposable += edit
        .clicks()
        .applyThrottle()
        .map { EditClicked() }
        .subscribe { viewModel.addEvent(it) }
  }

  companion object {
    fun newInstance(): com.grayherring.devtalks.ui.home.DetailViewFragment {
      val fragment = com.grayherring.devtalks.ui.home.DetailViewFragment()
      return fragment
    }
  }
}
