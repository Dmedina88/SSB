package com.grayherring.devtalks.ui.home

import com.grayherring.devtalks.base.events.*
import com.grayherring.devtalks.base.ui.BaseViewModel
import com.grayherring.devtalks.base.util.capture.GsonPrefRecorder
import com.grayherring.devtalks.base.util.intervalEmitted
import com.grayherring.devtalks.base.util.plusAssign
import com.grayherring.devtalks.data.repository.Repository
import com.grayherring.devtalks.ui.home.events.*
import io.reactivex.Observable
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Created by davidmedina on 5/19/17 =).
 */
class HomeViewModel @Inject constructor(private val repository: Repository,
                                        private val homeRecorder: GsonPrefRecorder<HomeState>) : BaseViewModel<HomeState>() {

  override fun init() {
    super.init()
    if (state.talks.isEmpty()) {
      homeRecorder.clear()
      getBooks()
    }
  }

  var replaing = false

  override fun initState() = HomeState()

  override fun handleEvents(event: Event): HomeState {
    Timber.d(event.toString())

    val newState = when (event) {
      is ClickEvent -> clickEventReducer(event, state)
      is NetworkEvent -> networkReducer(event, state)
      is ItemClickEvent -> state.copy(selectedTalk = event.talk, tab = 2)
      is AllTalksEvent -> state.copy(loading = false, talks = event.talks)
      is TextChangeEvent -> textChangeReducer(event, state)
      is ErrorEvent -> state.copy(loading = false, error = event.errorMessage)
      else -> state
    }
    Timber.d(newState.toString())

    if (!replaing) {
      homeRecorder.save(newState)
    }
    return newState
  }


  private fun clickEventReducer(clickEvent: ClickEvent, state: HomeState): HomeState {
    return when (clickEvent) {
      is Tab1Clicked -> state.copy(tab = 1)
      is Tab2Clicked -> state.copy(tab = 2)
      is Tab3Clicked -> state.copy(tab = 3)
      is EditClicked -> state.copy(editScreen = !state.editScreen)
      else -> state
    }
  }

  private fun networkReducer(networkEvent: NetworkEvent, state: HomeState): HomeState {
    return when (networkEvent) {
      is GetTalkEvent -> {
        getBooks()
        state.copy(loading = true)
      }
      else -> state
    }
  }

  private fun textChangeReducer(textChangeEvent: TextChangeEvent, state: HomeState): HomeState {
    val newState = textChangeEvent.value.run {
      when (textChangeEvent) {
        is PresenterChangeEvent -> state.copy(newTalk = state.newTalk.copy(presenter = this))
        is TitleChangeEvent -> state.copy(newTalk = state.newTalk.copy(title = this))
        is PlatformChangeEvent -> state.copy(newTalk = state.newTalk.copy(platform = this))
        is DescriptionChangeEvent -> state.copy(newTalk = state.newTalk.copy(description = this))
        is DateChangeEvent -> state.copy(newTalk = state.newTalk.copy(date = this))
        is EmailChangeEvent -> state.copy(newTalk = state.newTalk.copy(email = this))
        is SlidesChangeEvent -> state.copy(newTalk = state.newTalk.copy(slides = this))
        is StreamChangeEvent -> state.copy(newTalk = state.newTalk.copy(stream = this))
        else -> state
      }
    }
    //check state if new talk is valid
    return newState
  }

  fun getBooks() {
    disposable += repository.makeAllTalksCall(this::addEvent)
  }

  fun playback() {
    val states = homeRecorder.states
    replaing = true
    Timber.i("${states.size}")
    Observable.fromIterable(states)
        .intervalEmitted(500, TimeUnit.MILLISECONDS)
        .doOnComplete { replaing = false }
        .subscribe({
          Timber.i("##replay ${states.indexOf(it)} $it ")
          state = it
        }, {
          Timber.e(it)
          replaing = false
        })
  }

  override fun onCleared() {
    //should clear this in debug draw
    homeRecorder.clear()
    super.onCleared()
  }


}