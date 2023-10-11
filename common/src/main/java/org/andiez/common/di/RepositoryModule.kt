package org.andiez.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.andiez.common.domain.repository.IAppRepository

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Module(includes = [DatabaseModule::class, RemoteModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideAppRepository(): IAppRepository
}