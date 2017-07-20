package com.grayherring.devtalks.ui.edittalk

import com.grayherring.devtalks.base.ui.BaseFragment
import com.grayherring.devtalks.base.util.TextChangeSkipInit
import com.grayherring.devtalks.base.util.tentativelyUpdate
import com.grayherring.devtalks.ui.home.events.*
import kotlinx.android.synthetic.main.fragment_add_talk.*


class EditTalkFragment : BaseFragment<EditState, EditViewModel>() {

  override fun viewModelClass() = EditViewModel::class.java
  override fun viewLayout(): Int = com.grayherring.devtalks.R.layout.fragment_add_talk

  override fun initView(state: EditState) {
    state.talk.let {
      presenter.tentativelyUpdate(it.presenter)
      title.tentativelyUpdate(it.title)
      platform.tentativelyUpdate(it.platform)
      description.tentativelyUpdate(it.description)
      date.tentativelyUpdate(it.date)
      email.tentativelyUpdate(it.email)
      slides.tentativelyUpdate(it.slides)
      streams.tentativelyUpdate(it.stream)
    }
  }

  override fun bindView(state: EditState) {
    state.talk.let {
      if (it.id != null) {
        presenter.tentativelyUpdate(it.presenter)
        title.tentativelyUpdate(it.title)
        platform.tentativelyUpdate(it.platform)
        description.tentativelyUpdate(it.description)
        date.tentativelyUpdate(it.date)
        email.tentativelyUpdate(it.email)
        slides.tentativelyUpdate(it.slides)
        streams.tentativelyUpdate(it.stream)
      }
      submit.isEnabled = it.isValid()
    }
  }

  override fun setUpEventStream() {
    viewModel.apply {
      disposable.addAll(
          presenter.TextChangeSkipInit().subscribe { addEvent(PresenterChangeEvent(it)) },
          title.TextChangeSkipInit().subscribe { addEvent(TitleChangeEvent(it)) },
          platform.TextChangeSkipInit().subscribe { addEvent(PlatformChangeEvent(it)) },
          description.TextChangeSkipInit().subscribe { addEvent(DescriptionChangeEvent(it)) },
          date.TextChangeSkipInit().subscribe { addEvent(DateChangeEvent(it)) },
          email.TextChangeSkipInit().subscribe { addEvent(EmailChangeEvent(it)) },
          slides.TextChangeSkipInit().subscribe { addEvent(SlidesChangeEvent(it)) },
          streams.TextChangeSkipInit().subscribe { addEvent(StreamChangeEvent(it)) })
    }
  }


  companion object {
    fun newInstance(): EditTalkFragment {
      val fragment = EditTalkFragment()
      return fragment
    }
  }
}


