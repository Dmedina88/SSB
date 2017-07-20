package <%= appPackage %>.base.ui

/**
 *  gets [Event]s via add event then mutates [State]  and/or triggers asynchronous action that will comeback with a new [Event]
 *  after state is mutated its published to the state relay for observes to react to
 */
open class BaseState