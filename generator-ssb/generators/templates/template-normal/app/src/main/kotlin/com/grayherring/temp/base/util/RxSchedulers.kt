package <%= appPackage %>.base.util

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS

val THROTTLE = 300L
val THROTTLE_TEXT = 1000L


fun <T> Flowable<T>.applySchedulers(): Flowable<T> =
    subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.applyThrottle(): Flowable<T> = throttleFirst(THROTTLE, MILLISECONDS)

fun <T> Flowable<T>.doAll(): Flowable<T> = applySchedulers().applyThrottle()


fun <T> Observable<T>.applySchedulers(): Observable<T> =
    subscribeOn(Schedulers.computation()).observeOnMainThread()

fun <T> Observable<T>.observeOnMainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.mainThread(): Observable<T> =
    observeOn(AndroidSchedulers.mainThread()).subscribeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.applyThrottle(): Observable<T> = throttleFirst(THROTTLE, MILLISECONDS)

fun <T> Observable<T>.doAll(): Observable<T> = applySchedulers().applyThrottle()

fun <T> Observable<T>.throttleLast(): Observable<T> = throttleLast(THROTTLE_TEXT, MILLISECONDS)
