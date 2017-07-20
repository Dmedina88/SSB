package com.grayherring.devtalks.base.ui;

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.LifecycleRegistryOwner
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.grayherring.devtalks.base.util.plusAssign
import com.grayherring.devtalks.di.Injectable
import com.grayherring.devtalks.viewmodel.ViewModelFactory
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

abstract class BaseFragment<V : BaseState, T : BaseViewModel<V>> : LifecycleFragment(), LifecycleRegistryOwner, Injectable {

  @Inject lateinit var viewModelFactory: ViewModelFactory
  protected lateinit var viewModel: T
  protected val disposable = CompositeDisposable()

  override fun onCreateView(inflater: LayoutInflater?,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater!!.inflate(viewLayout(), container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    //if we are going to bind butterKnife do it here
    initViewModel()
    
  }

  override fun onDestroyView() {
    disposable.clear()
    super.onDestroyView()
  }

  //todo this code maybe the same in the fragment and activity maybe abstract?
  /**
   * setup for view model and stream
   */
  fun initViewModel() {
    viewModel = ViewModelProviders.of(activity, viewModelFactory).get(viewModelClass())
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

  override fun onDestroy() {
    disposable.clear()
    super.onDestroy()
  }

}
