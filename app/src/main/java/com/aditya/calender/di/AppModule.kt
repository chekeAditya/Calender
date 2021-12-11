package com.aditya.calender.di

import android.content.Context
import androidx.room.Room
import com.aditya.calender.extras.Constants.BASE_URL
import com.aditya.calender.remote.interfaces.APIClient
import com.aditya.calender.remote.localDb.AppDao
import com.aditya.calender.remote.localDb.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApiService() : APIClient{
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return builder.create(APIClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDB(@ApplicationContext context: Context): AppDatabase {
        val builder = Room.databaseBuilder(context, AppDatabase::class.java, "_news_db")
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideDataToDb(db: AppDatabase): AppDao {
        return db.getResponseFromDB()
    }


}