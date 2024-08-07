package com.riezki.jobstreetapp.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobsItem(
    val company: String?,
    val companyLogo: String?,
    val companyUrl: String?,
    val createdAt: String?,
    val description: String?,
    val howToApply: String?,
    val id: String?,
    val location: String?,
    val title: String?,
    val type: String?,
    val url: String?,
) : Parcelable
