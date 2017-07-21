package com.grayherring.devtalks.ui

import com.grayherring.devtalks.data.repository.api.MockDevTalkApiClient
import com.grayherring.devtalks.ui.home.HomeState
import com.grayherring.devtalks.ui.home.HomeViewModel
import com.grayherring.devtalks.ui.home.events.Tab1Clicked
import com.grayherring.devtalks.ui.home.events.Tab2Clicked
import com.grayherring.devtalks.ui.home.events.Tab3Clicked
import org.junit.Assert
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test

class HomeViewModelTest {

  companion object {
    @ClassRule
    @JvmField
    val schedulers = RxImmediateSchedulerRule()
  }

  lateinit var homeViewModel: HomeViewModel
  lateinit var mockAPI: MockDevTalkApiClient


  @Before
  fun setUp() {
    mockAPI = MockDevTalkApiClient()
    //   homeViewModel = HomeViewModel(Repository(MockAPI))
  }

  @Test
  fun tab1ClickedTest() {
    homeViewModel.init()
    homeViewModel.addEvent(Tab1Clicked())
    Assert.assertTrue(homeViewModel.lastState.tab == 1)
    homeViewModel.addEvent(Tab2Clicked())
    Assert.assertTrue(homeViewModel.lastState.tab == 2)
    homeViewModel.addEvent(Tab3Clicked())
    Assert.assertTrue(homeViewModel.lastState.tab == 3)
  }

  @Test
  fun init() {
    //todo make a state for all the counts
    var count = 0
    homeViewModel.observeState {
      when (count) {
        0 -> Assert.assertEquals(homeViewModel.lastState, HomeState())
        1 -> Assert.assertTrue(homeViewModel.lastState.toString(), homeViewModel.lastState.loading)
        2 -> Assert.assertTrue(homeViewModel.lastState.toString(), (
            homeViewModel.lastState.talks.containsAll(mockAPI.mockTalksData) && !homeViewModel.lastState.loading))

      }
      count++
    }
    Assert.assertEquals(homeViewModel.lastState, HomeState())
    homeViewModel.init()
    Assert.assertTrue(homeViewModel.lastState.talks.containsAll(mockAPI.mockTalksData))
    Assert.assertFalse(homeViewModel.lastState.toString(), homeViewModel.lastState.loading)
  }


}