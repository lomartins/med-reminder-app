package dev.luisamartins.medreminder.di

import dev.luisamartins.medreminder.data.di.dataModule
import dev.luisamartins.medreminder.domain.di.domainModule
import dev.luisamartins.medreminder.ui.di.uiModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verifyAll

class CheckKoinModulesTest : KoinTest {
    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkKoinModules() {
        listOf(
            dataModule,
            domainModule,
            uiModule
        ).verifyAll()
    }
}