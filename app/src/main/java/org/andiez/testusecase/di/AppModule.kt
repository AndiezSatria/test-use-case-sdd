package org.andiez.testusecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.andiez.common.domain.usecase.AppInteractor
import org.andiez.common.domain.usecase.AppUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideMovieUseCase(appInteractor: AppInteractor): AppUseCase
}