package xyz.zolbooo.hetevch.android

import android.app.Application
import xyz.zolbooo.hetevch.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class HetevchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@HetevchApplication)
        }
    }
}
