package <%= appPackage %>.ui.home

import android.os.Bundle
import android.view.MenuItem
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import <%= appPackage %>.R.id.*
import <%= appPackage %>.base.events.ClickEvent
import <%= appPackage %>.base.ui.BaseActivity
import <%= appPackage %>.base.util.applyThrottle
import <%= appPackage %>.ui.home.events.Tab1Clicked
import <%= appPackage %>.ui.home.events.Tab2Clicked
import <%= appPackage %>.ui.home.events.Tab3Clicked


class HomeActivity : BaseActivity<HomeState, HomeViewModel>() {

  override fun viewLayout() = <%= appPackage %>.R.layout.activity_main

  override fun viewModelClass() = HomeViewModel::class.java

  override fun setUpEventStream() {
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    homeFragment()
  }

  override fun bindView(state: HomeState) {

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

  fun homeFragment() {
    val talkFragment = HomeFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(<%= appPackage %>.R.id.container, talkFragment)
        .commitAllowingStateLoss()
  }

}