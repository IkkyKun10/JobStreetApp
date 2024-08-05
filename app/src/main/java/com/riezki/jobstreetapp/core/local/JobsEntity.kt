package com.riezki.jobstreetapp.core.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.riezki.jobstreetapp.domain.models.JobsItem

@Entity
data class JobsEntity(
    @PrimaryKey
    val idEntity: Int? = null,
    val id: String?,
    val company: String?,
    val companyLogo: String?,
    val companyUrl: String?,
    val createdAt: String?,
    val description: String?,
    val howToApply: String?,
    val location: String?,
    val title: String?,
    val type: String?,
    val url: String?
) {
    fun toDomain() : JobsItem {
        return JobsItem(
            idEntity = idEntity,
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
