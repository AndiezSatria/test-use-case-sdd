package org.andiez.testusecase.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.andiez.common.domain.usecase.AppUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface UseCaseModuleDependencies {

    fun movieUseCase(): AppUseCase
}