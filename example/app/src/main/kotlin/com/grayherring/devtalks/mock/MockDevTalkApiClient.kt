package com.grayherring.devtalks.data.repository.api

import com.grayherring.devtalks.data.model.Talk
import com.grayherring.devtalks.mock.MockAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by David Medina on 6/25/2017.
 */
class MockDevTalkApiClient(devTalkApi: MockAPI = MockAPI()) : DevTalkApiClient(devTalkApi) {
  val mockTalksData get() = (devTalkApi as MockAPI).talks

  override fun talks(): Observable<List<Talk>> = devTalkApi.talks().subscribeOn(AndroidSchedulers.mainThread())
  override fun talk(id: String): Observable<Talk?> = devTalkApi.talk(id)
  override fun insertOrUpdate(vararg talks: Talk): Observable<Talk> = devTalkApi.insertOrUpdate(*talks)
  override fun delete(talk: Talk): Observable<Talk> = devTalkApi.delete(talk)
}