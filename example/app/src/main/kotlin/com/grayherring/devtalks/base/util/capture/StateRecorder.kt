package com.grayherring.devtalks.base.util.capture

import com.grayherring.devtalks.base.ui.BaseState


abstract class StateRecorder<T : BaseState> {


  abstract fun save(t: T)

  abstract fun stateAt(index: Int): T?

  abstract val states: List<T>

  abstract fun clear()

}