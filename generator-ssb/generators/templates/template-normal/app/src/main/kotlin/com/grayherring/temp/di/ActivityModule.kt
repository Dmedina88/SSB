package <%= appPackage %>.di

import <%= appPackage %>.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
  @ContributesAndroidInjector(modules = arrayOf(FragmentBuildersModule::class))
  abstract fun contributeHomeActivity(): HomeActivity

}
