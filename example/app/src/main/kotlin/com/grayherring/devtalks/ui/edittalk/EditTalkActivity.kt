package com.grayherring.devtalks.ui.edittalk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.grayherring.devtalks.R
import com.grayherring.devtalks.R.layout.activity_edit
import com.grayherring.devtalks.base.ui.BaseActivity
import com.grayherring.devtalks.data.model.Talk
import com.grayherring.devtalks.ui.edittalk.events.EditInitEvent
import kotlinx.android.synthetic.main.activity_edit.*


class EditTalkActivity : BaseActivity<EditState, EditViewModel>() {

  override fun viewLayout() = activity_edit

  override fun viewModelClass() = EditViewModel::class.java


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel.addEvent(EditInitEvent(intent.getParcelableExtra(TALK_EXTRA)))
    talkFragment()
  }

  override fun bindView(state: EditState) {
    progress_spinner.visibility = if (state.loading) View.VISIBLE else View.GONE
  }

  override fun setUpEventStream() {}

  companion object {
    val TALK_EXTRA = "TALK_EXTRA"
    fun start(context: Context, talk: Talk) {
      val intent = Intent(context, EditTalkActivity::class.java)
      intent.putExtra(TALK_EXTRA, talk)
      context.startActivity(intent)
    }
  }

  fun talkFragment() {
    val editTalkFragment = EditTalkFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, editTalkFragment)
        .commitAllowingStateLoss()
  }
}