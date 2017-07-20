package com.grayherring.kotlintest.util

import android.app.Activity
import android.view.KeyEvent

/**
 * Created by David Medina.
 */
interface KeyUpListener {
  companion object : KeyUpListener

  fun onKeyUp(activity: Activity, keyCode: Int, event: KeyEvent) {}

}


