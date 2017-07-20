package com.grayherring.devtalks.data.repository.api

import com.grayherring.devtalks.data.model.Talk
import io.reactivex.Observable
import retrofit2.http.GET


interface DevTalkApi {

  @GET("all")
  fun talks(): Observable<List<Talk>>

  fun talk(id: String): Observable<Talk?>

  fun insertOrUpdate(vararg talks: Talk): Observable<Talk>

  fun delete(talk: Talk): Observable<Talk>
}




