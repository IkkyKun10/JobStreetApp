package com.riezki.jobstreetapp.core.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.riezki.jobstreetapp.domain.models.JobsItem

@Entity
data class JobsEntity(
    @PrimaryKey(autoGenerate = true) val idEntity: Int? = null,
    val id: String? = null,
    val company: String? = null,
    val companyLogo: String? = null,
    val companyUrl: String? = null,
    val createdAt: String? = null,
    val description: String? = null,
    val howToApply: String? = null,
    val location: String? = null,
    val title: String? = null,
    val type: String? = null,
    val url: String? = null
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
