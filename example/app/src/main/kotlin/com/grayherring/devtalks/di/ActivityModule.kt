package com.grayherring.devtalks.di

import com.grayherring.devtalks.ui.edittalk.EditTalkActivity
import com.grayherring.devtalks.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
  @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
  abstract fun contributeHomeActivity(): HomeActivity

  @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
  abstract fun contributeEditTalkActivity(): EditTalkActivity
}
