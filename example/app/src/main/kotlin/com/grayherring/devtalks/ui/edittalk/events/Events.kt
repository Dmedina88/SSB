package com.grayherring.devtalks.ui.edittalk.events

import com.grayherring.devtalks.base.events.Event
import com.grayherring.devtalks.data.model.Talk

class EditInitEvent(val talk: Talk) : Event()