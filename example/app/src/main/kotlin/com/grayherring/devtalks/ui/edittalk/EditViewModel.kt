package com.grayherring.devtalks.ui.edittalk

import com.grayherring.devtalks.base.events.Event
import com.grayherring.devtalks.base.events.TextChangeEvent
import com.grayherring.devtalks.base.ui.BaseViewModel
import com.grayherring.devtalks.data.repository.api.DevTalkApiClient
import com.grayherring.devtalks.ui.edittalk.events.EditInitEvent
import com.grayherring.devtalks.ui.home.events.*
import javax.inject.Inject

class EditViewModel @Inject constructor(private val devTalkApi: DevTalkApiClient) : BaseViewModel<EditState>() {
  override fun initState() = EditState()


  override fun handleEvents(event: Event): EditState {
    return when (event) {
      is EditInitEvent -> if (state.talk.id == null) state.copy(talk = event.talk) else state
      is TextChangeEvent -> textChangeReducer(event, state)
      else -> state
    }
  }

  private fun textChangeReducer(textChangeEvent: TextChangeEvent, state: EditState): EditState {
    val newState = textChangeEvent.value.run {
      when (textChangeEvent) {
        is PresenterChangeEvent -> state.copy(talk = state.talk.copy(presenter = this))
        is TitleChangeEvent -> state.copy(talk = state.talk.copy(title = this))
        is PlatformChangeEvent -> state.copy(talk = state.talk.copy(platform = this))
        is DescriptionChangeEvent -> state.copy(talk = state.talk.copy(description = this))
        is DateChangeEvent -> state.copy(talk = state.talk.copy(date = this))
        is EmailChangeEvent -> state.copy(talk = state.talk.copy(email = this))
        is SlidesChangeEvent -> state.copy(talk = state.talk.copy(slides = this))
        is StreamChangeEvent -> state.copy(talk = state.talk.copy(stream = this))
        else -> state
      }
    }
    //check state if new talk is valid
    return newState
  }

}