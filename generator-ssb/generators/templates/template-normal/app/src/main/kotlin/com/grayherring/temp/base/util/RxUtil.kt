package <%= appPackage %>.base.util

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
  this.add(disposable)
}
fun <T> Observable<T>.intervalEmitted(time: Long = 1,
                                      timeUnit: TimeUnit = TimeUnit.SECONDS): Observable<T> {
  return this.zipWith(Observable.interval(time, timeUnit), BiFunction { value, _ -> value })
}
