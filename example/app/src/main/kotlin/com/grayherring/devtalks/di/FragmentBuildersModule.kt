package com.grayherring.devtalks.di

import com.grayherring.devtalks.ui.edittalk.EditTalkFragment
import com.grayherring.devtalks.ui.home.AddTalkFragment
import com.grayherring.devtalks.ui.home.DetailViewFragment
import com.grayherring.devtalks.ui.home.TalkItemFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeTalkItemFragment(): TalkItemFragment

  @ContributesAndroidInjector
  abstract fun contributeDetailView(): DetailViewFragment

  @ContributesAndroidInjector
  abstract fun contributeAddTalkFragment(): AddTalkFragment

  @ContributesAndroidInjector
  abstract fun contributeEditTalkFragment(): EditTalkFragment
}
