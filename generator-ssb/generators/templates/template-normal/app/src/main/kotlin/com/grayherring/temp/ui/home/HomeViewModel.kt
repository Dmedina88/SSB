package <%= appPackage %>.ui.home

import <%= appPackage %>.base.events.*
import <%= appPackage %>.base.ui.BaseViewModel

import <%= appPackage %>.base.util.plusAssign
import <%= appPackage %>.data.repository.api.ApiClient
import <%= appPackage %>.ui.home.events.*
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val api: ApiClient) : BaseViewModel<HomeState>() {
  override fun handleEvents(event: Event): HomeState {
    return state
  }

  override fun initState(): HomeState {
    return HomeState(0)
  }

}