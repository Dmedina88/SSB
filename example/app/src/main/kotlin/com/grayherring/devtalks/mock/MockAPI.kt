package com.grayherring.devtalks.mock

import com.grayherring.devtalks.base.util.observeOnMainThread
import com.grayherring.devtalks.data.model.Talk
import com.grayherring.devtalks.data.repository.api.DevTalkApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*

class MockAPI : DevTalkApi {

  val talks = ArrayList<Talk>()

  init {
    val talk = Talk(
        presenter = "david",
        title = "Super Saiyan Blue For android",
        platform = "android",
        description = "who knows",
        email = "d.medina@gmail.com",
        date = "2017-12-12",
        id = Calendar.getInstance().time.time.toString()

    )
    talks.add(talk)
    talks.add(talk.copy("Frank", "frankland lives"))
    talks.add(talk.copy("Andrew", "How to Cook an egg"))
    talks.add(talk.copy("Egg", "frankland lives"))

  }

  override fun talks(): Observable<List<Talk>> {
    return Observable.just(talks as List<Talk>).subscribeOn(AndroidSchedulers.mainThread()).observeOnMainThread()
  }

  override fun talk(id: String): Observable<Talk?> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun insertOrUpdate(vararg talks: Talk): Observable<Talk> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun delete(talk: Talk): Observable<Talk> {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }


}