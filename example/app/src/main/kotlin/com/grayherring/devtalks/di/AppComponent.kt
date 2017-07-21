package com.grayherring.devtalks.di

import android.app.Application
import com.grayherring.devtalks.DevTalkApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,
    DataModule::class,
    ViewModelModule::class,
    AppModule::class,
    ActivityModule::class))
interface AppComponent {
  @Component.Builder interface Builder {
    @BindsInstance fun application(application: Application): Builder
    fun build(): AppComponent
  }

  fun inject(devTalkApp: DevTalkApp)
}
