package com.pdm0126.rankeuca.data

import android.content.Context
import com.pdm0126.rankeuca.data.database.AppDatabase
import com.pdm0126.rankeuca.repository.OptionRepository
import com.pdm0126.rankeuca.repository.OptionRepositoryImpl

class AppProvider (context: Context) {

    private val appDatabase = AppDatabase.getDatabase(context)
    private val optionDao = appDatabase.OptionDao()

    private val optionRepository: OptionRepository =
        OptionRepositoryImpl(optionDao)

    fun provideOptionRepository(): OptionRepository{
        return optionRepository
    }
}