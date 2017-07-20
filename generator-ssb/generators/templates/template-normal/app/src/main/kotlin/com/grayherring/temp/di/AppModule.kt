package <%= appPackage %>.di;

import <%= appPackage %>.BuildConfig
import <%= appPackage %>.ui.util.DebugKeyUpListener
import com.grayherring.kotlintest.util.KeyUpListener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

  @Provides @Singleton fun provideKeyUpListener(): KeyUpListener {
    if (BuildConfig.DEBUG) {
      return DebugKeyUpListener()
    } else {
      return KeyUpListener
    }
  }
}
