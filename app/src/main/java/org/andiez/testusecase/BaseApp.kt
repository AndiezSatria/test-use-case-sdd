package org.andiez.testusecase

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.github.ajalt.timberkt.Timber
import dagger.hilt.android.HiltAndroidApp
import org.andiez.common.data.source.local.pref.BasePreference

/**
 * Created by AndiezSatria on 17/04/2023.
 */
@HiltAndroidApp
class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        // Initial Preferences
        BasePreference.init(applicationContext)

        // plant timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}