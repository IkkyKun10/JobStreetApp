package com.riezki.jobstreetapp.core.remote


import com.riezki.jobstreetapp.domain.models.JobsItem
import com.squareup.moshi.Json

data class JobsItemDto(
    @Json(name = "company")
    val company: String?,
    @Json(name = "company_logo")
    val companyLogo: String?,
    @Json(name = "company_url")
    val companyUrl: String?,
    @Json(name = "created_at")
    val createdAt: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "how_to_apply")
    val howToApply: String?,
    @Json(name = "id")
    val id: String?,
    @Json(name = "location")
    val location: String?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "url")
    val url: String?
) {
    fun toDomain() : JobsItem {
        return JobsItem(
            id = id,
            title = title,
            company = company,
            companyLogo = companyLogo,
            companyUrl = companyUrl,
            description = description,
            location = location,
            url = url,
            createdAt = createdAt,
            howToApply = howToApply,
            type = type
        )
    }
}