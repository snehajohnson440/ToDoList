package com.example.todolist

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabaseInstance(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun getDao(database: AppDatabase) : ToDoListDao{
        return database.dao()
    }
}