package io.github.yamacraft.app.sample.architecture2017.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.yamacraft.app.sample.architecture2017.repository.GithubRepository
import io.github.yamacraft.app.sample.architecture2017.repository.GithubRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindGithubRepository(repository: GithubRepositoryImpl): GithubRepository
}
