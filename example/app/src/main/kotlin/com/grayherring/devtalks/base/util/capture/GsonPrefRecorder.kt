package com.grayherring.devtalks.base.util.capture

import android.content.SharedPreferences
import com.google.gson.Gson
import com.grayherring.devtalks.base.ui.BaseState
import java.lang.reflect.Type


open class GsonPrefRecorder<T : BaseState>(val sharedPreferences: SharedPreferences,
                                           internal val key: String,
                                           val token: Type,
                                           val gson: Gson = Gson()) : StateRecorder<T>() {


  protected val editer = sharedPreferences.edit()


  override fun save(t: T) {
    val newStates = states
    newStates.add(t)
    editer.putString(key, gson.toJson(newStates))
    editer.apply()
  }

  override fun stateAt(index: Int): T? {
    if (states.size > index) {
      return states[index]
    }
    return null
  }

  override val states: MutableList<T>
    get() {
      val jsonEventList = sharedPreferences.getString(key, "")
      val states: MutableList<T> = ArrayList()
      if (!jsonEventList!!.isEmpty()) {
        val list: Array<T> = gson.fromJson(jsonEventList, token)
        states.addAll(list)
      }
      return states
      //  return emptyList()
    }

  override fun clear() {
    editer.clear()
    editer.apply()
  }

  val gsonString: String get() = sharedPreferences.getString(key, "")

  companion object {
    protected val CAPTURER_PREF = "FLUX_pref_FLux_mess"
  }

}