package <%= appPackage %>.ui.home.events

import <%= appPackage %>.base.events.ClickEvent
import <%= appPackage %>.base.events.Event
import <%= appPackage %>.base.events.NetworkEvent
import <%= appPackage %>.base.events.TextChangeEvent

class Tab1Clicked : ClickEvent()
class Tab2Clicked : ClickEvent()
class Tab3Clicked : ClickEvent()
class EditClicked : ClickEvent()
class SubmitClicked : ClickEvent()


class GetTalkEvent : NetworkEvent()

