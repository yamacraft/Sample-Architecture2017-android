package io.github.yamacraft.app.sample.architecture2017.repository

import io.github.yamacraft.app.sample.architecture2017.api.GithubService
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService
) : GithubRepository {
    override suspend fun getOrganizationRepositories(organizationName: String): Result<List<String>> {
        val response = githubService.getOrganizationRepositories(organizationName)
        if (response.isSuccessful) {
            val names = response.body()?.map {
                it.fullName
            } ?: listOf()
            return Result.success(names)
        }
        return Result.failure(Exception(response.message()))
    }
}
