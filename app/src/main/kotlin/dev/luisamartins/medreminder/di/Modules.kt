package dev.luisamartins.medreminder.di

//val medReminderAppModule = module {
//
//
//    single<AppDatabase> {
//        Room.databaseBuilder(
//            androidContext(),
//            AppDatabase::class.java,
//            "medreminder-db"
//        ).build()
//    }
//
//    factory { get<AppDatabase>().medicationDao() }
//    factory { get<AppDatabase>().useRegisterDao() }
//    factoryOf(::MedicationRepositoryImpl) bind MedicationRepository::class
//    factoryOf(::UseRegisterRepositoryImpl) bind UseRegisterRepository::class
//}