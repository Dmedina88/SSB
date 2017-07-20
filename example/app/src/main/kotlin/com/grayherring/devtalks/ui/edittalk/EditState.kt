package com.grayherring.devtalks.ui.edittalk

import com.grayherring.devtalks.base.ui.BaseState
import com.grayherring.devtalks.data.model.Talk


data class EditState(
    val talk: Talk = Talk(id = null),
    val loading: Boolean = false) : BaseState()