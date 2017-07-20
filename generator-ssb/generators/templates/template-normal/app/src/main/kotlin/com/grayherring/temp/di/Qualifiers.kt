package <%= appPackage %>.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

class Qualifiers private constructor() {

  init {
    throw AssertionError("No instances.")
  }

  @Qualifier @Retention(RUNTIME) annotation class API

  @Qualifier @Retention(RUNTIME) annotation class MockPref

}
