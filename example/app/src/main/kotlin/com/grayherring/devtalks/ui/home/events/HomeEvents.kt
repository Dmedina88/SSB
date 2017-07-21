package com.grayherring.devtalks.ui.home.events

import com.grayherring.devtalks.base.events.ClickEvent
import com.grayherring.devtalks.base.events.Event
import com.grayherring.devtalks.base.events.NetworkEvent
import com.grayherring.devtalks.base.events.TextChangeEvent
import com.grayherring.devtalks.data.model.Talk

class Tab1Clicked : ClickEvent()

class Tab2Clicked : ClickEvent()
class Tab3Clicked : ClickEvent()
class EditClicked : ClickEvent()

class ItemClickEvent(val talk: Talk) : Event()

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