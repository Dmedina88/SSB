package <%= appPackage %>.data.repository.api

import <%= appPackage %>.mock.MockAPI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by David Medina on 6/25/2017.
 */
class MockDevTalkApiClient(devTalkApi: MockAPI = MockAPI()) : ApiClient(devTalkApi)