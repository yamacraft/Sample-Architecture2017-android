package io.github.yamacraft.app.sample.architecture2017.repository

interface GithubRepository {
    suspend fun getOrganizationRepositories(organizationName: String): Result<List<String>>
}
