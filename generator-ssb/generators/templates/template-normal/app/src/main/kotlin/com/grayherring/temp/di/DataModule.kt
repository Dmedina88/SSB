package <%= appPackage %>.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import <%= appPackage %>.data.repository.api.Api
import <%= appPackage %>.data.repository.api.ApiClient
import <%= appPackage %>.data.repository.api.ExceptionInterceptor
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
import com.squareup.picasso.Picasso


@Module(includes = arrayOf(ViewModelModule::class))
class DataModule {

  @Provides @Singleton fun providesGson() =
      GsonBuilder()
          .setDateFormat("yyyy-dd-MM")
          .create()

  @Provides @Singleton fun provideExceptionInterceptor(gson: Gson) = ExceptionInterceptor(gson)

  @Provides @Singleton fun provideOkHttpClient(exceptionInterceptor: ExceptionInterceptor, application: Application): OkHttpClient {
    val clientBuilder = Builder()
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = BODY
    clientBuilder.addInterceptor(ChuckInterceptor(application))
    clientBuilder.addInterceptor(httpLoggingInterceptor)
    clientBuilder.addInterceptor(exceptionInterceptor)
    return clientBuilder.build()
  }


  @Provides @Singleton fun provideHttpUrl(): HttpUrl =
      HttpUrl.parse("http://helligan.group/") as HttpUrl



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

  @Provides @Singleton fun provideTalkApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
  }

  @Provides @Singleton fun provideApiClient(api: Api) = ApiClient(api)


  @Provides @Singleton fun providePicasso(application: Application): Picasso {
   return Picasso.Builder(application).loggingEnabled(true).build()
  }
}

