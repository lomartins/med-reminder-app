package dev.luisamartins.medreminder

import android.app.Application
import dev.luisamartins.medreminder.data.di.dataModule
import dev.luisamartins.medreminder.domain.di.domainModule
import dev.luisamartins.medreminder.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MedReminderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MedReminderApplication)
            modules(
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}