package xyz.zolbooo.hetevch.repository

import android.app.Application
import androidx.test.core.app.ApplicationProvider

actual fun getTestDriverFactory(): DriverFactory {
    val app = ApplicationProvider.getApplicationContext<Application>()
    return DriverFactory(app)
}
