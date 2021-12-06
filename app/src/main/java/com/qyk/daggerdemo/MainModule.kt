package com.qyk.daggerdemo

import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideCat(): Cat {
        return Cat("xxx")
    }
}