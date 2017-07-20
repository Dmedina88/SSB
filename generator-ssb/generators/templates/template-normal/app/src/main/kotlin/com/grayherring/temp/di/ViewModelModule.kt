package <%= appPackage %>.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import <%= appPackage %>.ui.home.HomeViewModel
import <%= appPackage %>.viewmodel.ViewModelFactory
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
  internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
