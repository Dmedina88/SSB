package com.grayherring.devtalks.ui.util

import android.app.Activity
import android.os.Bundle
import com.grayherring.devtalks.R
import com.readystatesoftware.chuck.Chuck
import io.palaima.debugdrawer.actions.ActionsModule
import io.palaima.debugdrawer.actions.ButtonAction
import io.palaima.debugdrawer.commons.BuildModule
import io.palaima.debugdrawer.commons.DeviceModule
import io.palaima.debugdrawer.commons.NetworkModule
import io.palaima.debugdrawer.commons.SettingsModule
import io.palaima.debugdrawer.timber.TimberModule
import io.palaima.debugdrawer.view.DebugView


class DebugViewActivity : Activity() {

  private lateinit var debugView: DebugView


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_debug_view)

    debugView = findViewById(R.id.debug_view)

    val chuckBtn = ButtonAction(
        "ChuckInterceptor ",
        { startActivity(Chuck.getLaunchIntent(this)) }
    )

    debugView.modules(
        ActionsModule(
            chuckBtn),
        TimberModule(),
        BuildModule(this),
        DeviceModule(this),
        NetworkModule(this),
        SettingsModule(this)
    )
  }

  override protected fun onStart() {
    super.onStart()
    debugView.onStart()
  }

  override protected fun onResume() {
    super.onResume()
    debugView.onResume()
  }

  override protected fun onPause() {
    super.onPause()
    debugView.onPause()
  }

  override protected fun onStop() {
    super.onStop()
    debugView.onStop()
  }
}