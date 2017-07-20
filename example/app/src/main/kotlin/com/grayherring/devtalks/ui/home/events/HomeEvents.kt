package com.grayherring.devtalks.ui.home.events

import com.grayherring.devtalks.base.events.*
import com.grayherring.devtalks.base.ui.BaseState
import com.grayherring.devtalks.data.model.Talk
import com.grayherring.devtalks.ui.home.HomeState
import timber.log.Timber

class Tab1Clicked : ClickEvent()

class Tab2Clicked : ClickEvent()
class Tab3Clicked : ClickEvent()


  class EditClicked : ClickEvent(), AnalyticStateEvent{
    override fun <T : BaseState> run(state: T) {
      if (state is HomeState){
        Timber.d("### $state")
      }
    }

  }
  class ItemClickEvent(val talk: Talk) : Event(), AnalyticsEvent {
    override fun run() {
      Timber.d("### $talk")
    }
  }

  class AllTalksEvent(val talks: List<Talk> = ArrayList()) : Event()
  class GetTalkEvent : NetworkEvent()
  class PresenterChangeEvent(string: String) : TextChangeEvent(string)
class TitleChangeEvent(string: String) : TextChangeEvent(string)
class PlatformChangeEvent(string: String) : TextChangeEvent(string)
class DescriptionChangeEvent(string: String) : TextChangeEvent(string)
class DateChangeEvent(string: String) : TextChangeEvent(string)
class EmailChangeEvent(string: String) : TextChangeEvent(string)
class SlidesChangeEvent(string: String) : TextChangeEvent(string)
class StreamChangeEvent(string: String) : TextChangeEvent(string)