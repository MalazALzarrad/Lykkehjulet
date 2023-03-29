package com.lykkehjulet.di

import android.content.Context
import com.lykkehjulet.SpinViewModel
import com.lykkehjulet.data.PuzzleDatabase
import com.lykkehjulet.data.PuzzleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BaseModule {

    @Singleton
    @Provides
    fun getViewModel( puzzleRepository: PuzzleRepository ) : SpinViewModel {
        return SpinViewModel(puzzleRepository)
    }



    @Singleton
    @Provides
    fun getRepository(puzzleDatabase: PuzzleDatabase) : PuzzleRepository {
       return PuzzleRepository(puzzleDatabase.puzzleDao)
    }


    @Singleton
    @Provides
    fun getDatabaseIntent(@ApplicationContext context: Context) : PuzzleDatabase {
        return PuzzleDatabase.getInstance(context)
    }

}