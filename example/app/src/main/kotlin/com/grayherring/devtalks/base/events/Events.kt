package com.grayherring.devtalks.base.events

import com.grayherring.devtalks.base.ui.BaseState

open class Event
open class ClickEvent : Event()
open class NetworkEvent : Event()
open class TextChangeEvent(val value: String) : Event()
open class ErrorEvent(val errorMessage: String) : Event()


  interface AnalyticsEvent {
    fun run()
  }
  interface AnalyticStateEvent{
    fun<T:BaseState>  run(state:T)
  }