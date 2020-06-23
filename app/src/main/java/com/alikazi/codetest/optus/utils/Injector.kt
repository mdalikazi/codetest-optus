package com.alikazi.codetest.optus.utils

import android.content.Context
import com.alikazi.codetest.optus.database.AppDatabase
import com.alikazi.codetest.optus.network.Repository
import com.alikazi.codetest.optus.viewmodels.MyViewModelFactory

object Injector {

    fun provideMyViewModelFactory(context: Context): MyViewModelFactory {
        return MyViewModelFactory(provideRepository(context))
    }

    private fun provideRepository(context: Context): Repository {
        return Repository.getInstance(provideAppDatabase(context))
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

}