package <%= appPackage %>.data.repository.api

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

class ExceptionInterceptor(val gson: Gson) : Interceptor {

  @Throws(IOException::class)
  override fun intercept(chain: Chain?): Response {
    val request = chain?.request()
    val response = chain?.proceed(request)
    Timber.i(response.toString())
    return response!!
  }

}