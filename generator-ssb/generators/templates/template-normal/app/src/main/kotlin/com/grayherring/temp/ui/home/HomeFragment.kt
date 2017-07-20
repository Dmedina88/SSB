package <%= appPackage %>.ui.home

import <%= appPackage %>.R
import <%= appPackage %>.base.ui.BaseFragment

class HomeFragment : BaseFragment<HomeState, HomeViewModel>() {
  override fun viewModelClass() = HomeViewModel::class.java

  override fun viewLayout() = R.layout.fragment_home

  override fun bindView(state: HomeState) {
  }

  override fun setUpEventStream() {
  }

  companion object {
    fun newInstance(): HomeFragment {
      return HomeFragment()
    }
  }
}