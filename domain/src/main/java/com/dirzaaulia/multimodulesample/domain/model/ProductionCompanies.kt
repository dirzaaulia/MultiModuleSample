package com.dirzaaulia.multimodulesample.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ProductionCompanies(
    val id: Int?,
    @Json(name = "logo_path")
    val logoPath: String?,
    val name: String?
) : Parcelable