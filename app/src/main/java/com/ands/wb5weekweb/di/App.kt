package com.ands.wb5weekweb.di

import android.app.Application
import com.ands.wb5weekweb.utils.TinderDataBase
import com.facebook.drawee.backends.pipeline.Fresco
import com.github.terrakok.cicerone.Cicerone
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class App : Application() {
    //cicerone инициализируйте библиотеку, только не в этом классе
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(applicationContext)
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}