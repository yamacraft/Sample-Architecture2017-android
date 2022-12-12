package io.github.yamacraft.app.sample.architecture2017.api

import io.github.yamacraft.app.sample.architecture2017.api.data.OrganizationRepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubService {
    @Headers(
        "Accept: application/vnd.github.v3.full+json",
        "User-Agent: Sample-Architecture-2017-App"
    )
    @GET("/orgs/{org}/repos")
    suspend fun getOrganizationRepositories(
        @Path("org") organizationName: String
    ): Response<List<OrganizationRepositoryResponse>>
}
