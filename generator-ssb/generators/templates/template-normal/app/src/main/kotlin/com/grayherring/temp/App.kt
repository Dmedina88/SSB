package <%= appPackage %>

import android.app.Activity
import android.app.Application
import <%= appPackage %>.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject
import io.palaima.debugdrawer.timber.data.LumberYard



open class App : Application(), HasActivityInjector {

  @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

  override fun activityInjector(): DispatchingAndroidInjector<Activity> {
    return dispatchingActivityInjector
  }

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) {
      val lumberYard = LumberYard.getInstance(this)
      lumberYard.cleanUp()
      Timber.plant(lumberYard.tree())
      Timber.plant(Timber.DebugTree())
    }
    AppInjector.init(this)
  }
}