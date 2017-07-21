## Android Kotlin MVVM + Flux  

yeoman generator to set up a flux MVVM(without databinding  lib by default) type project for android with DI set up and debug draw

## How to run 
cd to generator-ssb

run: npm link

cd to folder you want to create app in

run: yo ssb

## This project uses:
- [Support libraries](https://developer.android.com/topic/libraries/support-library/index.html)
- [RxJava2](https://github.com/ReactiveX/RxJava)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [Retrofit](http://square.github.io/retrofit/) / [OkHttp](http://square.github.io/okhttp/)
- [Gson](https://github.com/google/gson)
- [Dagger 2](http://google.github.io/dagger/)
- [Timber](https://github.com/JakeWharton/timber)
- [DebugDrawer](https://github.com/palaima/DebugDrawer)

MVVM + Flux is a user interface architectural pattern engineered to facilitate Unidirectional data flow:

- The __ViewModel__ gets an __Event__ via add event then mutates the __State__ and/or triggers asynchronous action that will comeback with a new __Event__ after state is mutated its published to the __State__ relay for observes to react to.
- The __State__ is the model that represents the current state of the app or feature.
- The __View__ (BaseActivity/BaseFragment) Any componite that is responsable for updateing the view based on __State__.  Can optionally talk to the ViewModel via Events that happen to ui componite.
- The __Event__  is a type that can be accepted by a event relay inorder to communicate to the __ViewModel__
