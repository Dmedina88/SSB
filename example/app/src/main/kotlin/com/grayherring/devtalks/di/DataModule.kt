package com.grayherring.devtalks.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.grayherring.devtalks.base.util.capture.GsonPrefRecorder
import com.grayherring.devtalks.data.repository.Repository
import com.grayherring.devtalks.data.repository.api.DevTalkApi
import com.grayherring.devtalks.data.repository.api.DevTalkApiClient
import com.grayherring.devtalks.data.repository.api.ExceptionInterceptor
import com.grayherring.devtalks.ui.home.HomeState
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = arrayOf(ViewModelModule::class))
class DataModule {

  // @PerApp @Provides fun provideAppDatabase(context: Context) = AppDatabase.createPersistentDatabase(context)

  @Provides @Singleton fun providesGson() =
      GsonBuilder()
          .setDateFormat("yyyy-dd-MM")
          .create()

  @Provides @Singleton fun provideExceptionInterceptor(gson: Gson) = ExceptionInterceptor(gson)

  @Provides @Singleton fun provideOkHttpClient(exceptionInterceptor: ExceptionInterceptor,
                                               application: Application): OkHttpClient {
    val clientBuilder = Builder()
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = BODY
    clientBuilder.addInterceptor(ChuckInterceptor(application))
    clientBuilder.addInterceptor(httpLoggingInterceptor)
    clientBuilder.addInterceptor(exceptionInterceptor)
    return clientBuilder.build()
  }


  @Provides @Singleton fun provideHttpUrl(): HttpUrl =
      HttpUrl.parse("https://dev-talk-test.herokuapp.com") as HttpUrl


  @Provides @Singleton fun provideDevTalkApiClient(devTalkApi: DevTalkApi) = DevTalkApiClient(
      devTalkApi)

  @Provides @Singleton fun provideRepository(devTalkApi: DevTalkApiClient) = Repository(devTalkApi)


  @Provides @Singleton fun provideRetrofit(baseUrl: HttpUrl,
                                           client: OkHttpClient,
                                           gson: Gson): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }

  @Provides @Singleton fun provideDevTalkApi(retrofit: Retrofit): DevTalkApi {
    return retrofit.create(DevTalkApi::class.java)
  }

  @Provides @Singleton fun provideHomeCapturer(application: Application,
                                               gson: Gson): GsonPrefRecorder<HomeState> {
    return GsonPrefRecorder<HomeState>(application.applicationContext.getSharedPreferences("preft",
        0), "stat", Array<HomeState>::class.java, gson)
  }

//    @Provides @Singleton fun provideLocalTalkDB(context: Context): TalkDB
//            = Room.databaseBuilder(context, TalkDB::class.java, "database-name").build()


}

