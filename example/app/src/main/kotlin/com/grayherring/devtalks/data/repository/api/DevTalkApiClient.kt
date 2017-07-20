package com.grayherring.devtalks.data.repository.api

import com.grayherring.devtalks.base.util.applySchedulers
import com.grayherring.devtalks.data.model.Talk
import io.reactivex.Observable

open class DevTalkApiClient(val devTalkApi: DevTalkApi) : DevTalkApi {

  override fun talks(): Observable<List<Talk>> = devTalkApi.talks().applySchedulers()
  override fun talk(id: String): Observable<Talk?> = devTalkApi.talk(id).applySchedulers()
  override fun insertOrUpdate(vararg talks: Talk): Observable<Talk> = devTalkApi.insertOrUpdate(*talks).applySchedulers()
  override fun delete(talk: Talk): Observable<Talk> = devTalkApi.delete(talk).applySchedulers()
}