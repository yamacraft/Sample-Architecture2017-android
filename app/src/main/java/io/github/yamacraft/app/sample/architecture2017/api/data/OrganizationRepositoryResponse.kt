package io.github.yamacraft.app.sample.architecture2017.api.data

import com.squareup.moshi.Json

// 全部を取得するのは大変なので、一部を抜粋
// https://docs.github.com/ja/rest/repos/repos?apiVersion=2022-11-28#list-organization-repositories
data class OrganizationRepositoryResponse(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "full_name")
    val fullName: String
)

