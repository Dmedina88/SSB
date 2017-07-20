package com.grayherring.devtalks.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.grayherring.devtalks.ui.edittalk.EditViewModel
import com.grayherring.devtalks.ui.home.HomeViewModel
import com.grayherring.devtalks.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(HomeViewModel::class)
  internal abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(EditViewModel::class)
  internal abstract fun bindEditViewModel(editViewModel: EditViewModel): ViewModel

  @Binds
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
