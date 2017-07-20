package com.grayherring.devtalks.data.model

import com.grayherring.devtalks.base.util.notBlank
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Talk(
    val presenter: String = "",
    val title: String = "",
    val platform: String = "",
    val description: String = "",
    val date: String = "",
    val email: String = "",
    val slides: String = "",
    val stream: String = "",
    val tags: List<String> = ArrayList<String>(),
    val id: String?
): PaperParcelable {
  companion object {
    @JvmField val CREATOR = PaperParcelTalk.CREATOR
  }

  fun isValid():Boolean{
    return notBlank(presenter,title,platform,description,date,email,slides,stream)
  }
}

