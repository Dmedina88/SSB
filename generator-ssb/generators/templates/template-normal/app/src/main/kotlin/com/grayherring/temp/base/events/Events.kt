package <%= appPackage %>.base.events

/**
 * is a type that can be accepted by a event relay inorder to communicate to the [ViewModel]
 */
open class Event
open class ClickEvent : Event()
open class NetworkEvent : Event()
open class TextChangeEvent(val value: String) : Event()
open class ErrorEvent(val errorMessage: String) : Event()

