package com.grayherring.devtalks.ui.home

import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.grayherring.devtalks.R.id.*
import com.grayherring.devtalks.base.events.ClickEvent
import com.grayherring.devtalks.base.ui.BaseActivity
import com.grayherring.devtalks.base.util.applyThrottle
import com.grayherring.devtalks.ui.edittalk.EditTalkActivity
import com.grayherring.devtalks.ui.home.events.Tab1Clicked
import com.grayherring.devtalks.ui.home.events.Tab2Clicked
import com.grayherring.devtalks.ui.home.events.Tab3Clicked
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class HomeActivity : BaseActivity<HomeState, HomeViewModel>() {
  var tab = 0

  override fun viewLayout() = com.grayherring.devtalks.R.layout.activity_main

  override fun viewModelClass() = HomeViewModel::class.java

  override fun setUpEventStream() {
    disposable.add(
        navigation
            .itemSelections()
            .distinctUntilChanged()
            .applyThrottle()
            .map(TabToClickEvent())
            .subscribe { viewModel.addEvent(it) }
    )
    fab.setOnClickListener {
      Timber.d("setOnClickListener")
      viewModel.playback()
    }
  }

  //todo also move to base //maybe move all the navigation to a navigationer class
  override fun bindView(state: HomeState) {
    if (state.tab != tab)
      when (state.tab) {
        1 -> {
          tab = 1
          talkFragment()
          navigation.selectedItemId = navigation_home
        }
        2 -> {
          tab = 2
          detailFragment()
          navigation.selectedItemId = navigation_dashboard
        }
        3 -> {
          tab = 3
          addFragment()
          navigation.selectedItemId = navigation_notifications
        }
      }
    progress_spinner.visibility = if (state.loading) VISIBLE else GONE

    if (state.editScreen) {
      //start activity
      EditTalkActivity.start(this, state.selectedTalk)
    }
  }

  private fun TabToClickEvent(): (MenuItem) -> ClickEvent {
    return {
      when (it.itemId) {
        navigation_home -> Tab1Clicked()
        navigation_dashboard -> Tab2Clicked()
        navigation_notifications -> Tab3Clicked()
        else -> {
          ClickEvent()
        }
      }
    }
  }

  fun talkFragment() {
    val talkFragment = TalkItemFragment.Companion.newInstance(1)
    supportFragmentManager.beginTransaction()
        .replace(com.grayherring.devtalks.R.id.container, talkFragment)
        .commitAllowingStateLoss()
  }

  fun detailFragment() {
    val detailFragment = DetailViewFragment.Companion.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(com.grayherring.devtalks.R.id.container, detailFragment)
        .commitAllowingStateLoss()
  }

  fun addFragment() {
    val addTalkFragment = AddTalkFragment.Companion.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(com.grayherring.devtalks.R.id.container, addTalkFragment)
        .commitAllowingStateLoss()
  }

}