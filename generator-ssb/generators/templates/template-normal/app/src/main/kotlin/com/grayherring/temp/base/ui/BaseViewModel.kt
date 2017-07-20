package <%= appPackage %>.base.ui


import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import <%= appPackage %>.base.events.Event
import <%= appPackage %>.base.util.observeOnMainThread
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlin.properties.Delegates

//todo think about having the starting state be passes as a peram,
//todo make all this base stuff a modual and make some things internal
/**
 *  gets [Event]s via add event then mutates [State]  and/or triggers asynchronous action that will comeback with a new [Event]
 *  after state is mutated its published to the state relay for observes to react to
 */
abstract class BaseViewModel<T : BaseState> : ViewModel() {

   protected var state by Delegates.observable(initState, { _, old, new ->
    if (old != new) {
      stateDataRelay.accept(new)
    }
  })
  protected val disposable = CompositeDisposable()
  private val initState get() = initState()
  //todo maybe inject?  think about wrapping into its own class?
  protected val eventRelay: PublishRelay<Event> = PublishRelay.create()
  private val stateDataRelay = BehaviorRelay.createDefault(initState)

  init {
    stateDataRelay.accept(state)
  }

  /**
   * observeState takes an [observer] fun that gets called when ever state updates
   */
  internal fun observeState(observer: (T) -> Unit): Disposable {
    return stateDataRelay.observeOnMainThread().subscribe { observer(it) }
  }

  /**
   * adds an [Event] to the [eventRelay]
   */
  fun addEvent(event: Event) {
    eventRelay.accept(event)
  }

  @CallSuper
  open fun init() {
    if (disposable.size() == 0) {
      createEventStream()
    }
  }

  /**
   * [lastState] gives you the last state in the [stateDataRelay]
   */
  val lastState: T get() = stateDataRelay.blockingFirst(initState())

  /**
   * set up for event stream and adds hook for handling events in [handleEvents]
   */
  protected fun createEventStream() {
    disposable.add(
        eventRelay.observeOnMainThread()
            .map(this::handleEvents)
            .subscribe { state = it })
  }

  /**
   * handles incoming events and returns new state [T]
   */
  abstract fun handleEvents(event: Event): T

  /**
   * starting state for view
   */
  abstract fun initState(): T

  override fun onCleared() {
    disposable.dispose()
    super.onCleared()
  }

}