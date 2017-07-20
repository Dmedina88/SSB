package com.grayherring.devtalks.data.repository

import com.grayherring.devtalks.base.events.ErrorEvent
import com.grayherring.devtalks.base.events.Event
import com.grayherring.devtalks.data.repository.api.DevTalkApiClient
import com.grayherring.devtalks.ui.home.events.AllTalksEvent
import io.reactivex.disposables.Disposable

  class Repository(val devTalkApi: DevTalkApiClient) {

    fun makeAllTalksCall(receiver: (Event) -> Unit): Disposable {
      return devTalkApi.talks()
          .map { AllTalksEvent(it) as Event }
          .onErrorReturn { ErrorEvent(it.message ?: "") }
          .subscribe(receiver)
    }

  }