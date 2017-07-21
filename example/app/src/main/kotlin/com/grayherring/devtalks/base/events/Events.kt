package com.grayherring.devtalks.base.events

open class Event
open class ClickEvent : Event()
open class NetworkEvent : Event()
open class TextChangeEvent(val value: String) : Event()
open class ErrorEvent(val errorMessage: String) : Event()
