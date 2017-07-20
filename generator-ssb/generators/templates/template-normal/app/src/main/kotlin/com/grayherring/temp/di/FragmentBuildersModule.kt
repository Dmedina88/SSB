package <%= appPackage %>.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import <%= appPackage %>.ui.home.HomeFragment

@Module
abstract class FragmentBuildersModule {
  @ContributesAndroidInjector
  abstract fun contributeTalkItemFragment(): HomeFragment

}
