package com.rasyidin.screeningtestsuitmedia.data.di

import android.content.Context
import com.rasyidin.screeningtestsuitmedia.data.AppRepository
import com.rasyidin.screeningtestsuitmedia.data.source.GuestRemoteDataSource
import com.rasyidin.screeningtestsuitmedia.data.utils.JsonHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesJsonHelper(@ApplicationContext context: Context) = JsonHelper(context)

    @Provides
    @Singleton
    fun providesGuestRemoteDataSource(jsonHelper: JsonHelper) = GuestRemoteDataSource(jsonHelper)

    @Provides
    @Singleton
    fun providesAppRepository(remoteDataSource: GuestRemoteDataSource) = AppRepository(remoteDataSource)
}
