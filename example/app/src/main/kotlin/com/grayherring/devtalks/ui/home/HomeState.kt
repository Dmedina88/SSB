package com.grayherring.devtalks.ui.home

import com.grayherring.devtalks.base.ui.BaseState
import com.grayherring.devtalks.data.model.Talk

data class HomeState(
    val tab: Int = 1,
    val talks: List<Talk> = ArrayList<Talk>(),
    val selectedTalk: Talk = Talk(id = null),
    val loading: Boolean = false,
    val error: String = "",
    val newTalk: Talk = Talk(id = null),
    val editScreen: Boolean = false ) : BaseState()