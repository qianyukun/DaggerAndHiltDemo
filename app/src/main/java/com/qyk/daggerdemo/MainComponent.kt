package com.qyk.daggerdemo

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)

    fun userRepository() : UserRepository
}