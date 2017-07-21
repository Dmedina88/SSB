package com.grayherring.devtalks.base.ui

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import android.view.KeyEvent
import com.grayherring.devtalks.base.util.plusAssign
import com.grayherring.devtalks.viewmodel.ViewModelFactory
import com.grayherring.kotlintest.util.KeyUpListener
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


abstract class BaseActivity<V : BaseState, T : BaseViewModel<V>> : LifecycleActivity(), HasSupportFragmentInjector {

  override fun supportFragmentInjector() = dispatchingAndroidInjector

  @Inject
  protected lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
  @Inject
  protected lateinit var viewModelFactory: ViewModelFactory
  @Inject lateinit internal var keyUpListener: KeyUpListener

  protected lateinit var viewModel: T
  protected val disposable = CompositeDisposable()

  //not sure if this would be better then the function but the issue is u can override it as var
  // abstract val viewLayout: Int

  override fun onCreate(savedInstanceState: android.os.Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(viewLayout())
    initViewModel()
  }

  /**
   * setup for view model and stream
   */
  fun initViewModel() {
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass())
    viewModel.init()
    initView(viewModel.lastState)
    disposable += viewModel.observeState { bindView(it) }
    setUpEventStream()
  }

  /**
   * if initializing view if you have to
   */
  open fun initView(state: V) {}

  /**
   * class needed to get the viewModel
   */
  abstract internal fun viewModelClass(): Class<T>

  /**
   * layout id for view
   */
  abstract internal fun viewLayout(): Int

  /**
   * hook for handling view updates
   */
  abstract internal fun bindView(state: V)

  /**
   * put code that creates events for into the even stream here remember to add to [disposable]
   */
  abstract fun setUpEventStream()

  //activity
  override fun onDestroy() {
    disposable.clear()
    super.onDestroy()
  }

  //maybe move this to an other sort of base base class extending this
  /**
   * used to start debug draw
   */
  override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
    keyUpListener.onKeyUp(this, keyCode, event)
    return super.onKeyUp(keyCode, event)
  }
}
