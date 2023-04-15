package com.example.hey.di

import android.app.Application
import androidx.room.Room
import com.example.data.db.ImvDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBase {

    @Provides
    @Singleton
    fun providesDatabase(app:Application):ImvDatabase=
        Room.databaseBuilder(app,ImvDatabase::class.java,"ImvDataBase")
            .fallbackToDestructiveMigration()
            .build();

    @Provides
    @Singleton
    fun providesPhotoDao(db:ImvDatabase)=db.photoDao()

    @Provides
    @Singleton
    fun providesVideoDao(db:ImvDatabase)=db.videoDao();

    @Provides
    @Singleton
    fun providesPhotoRemoteKeys(db: ImvDatabase)=db.remoteKeysPhotosDao()

    @Provides
    @Singleton
    fun providesVideoRemoteKeys(db: ImvDatabase)=db.remoteKeysVideoDao()
}