package com.dirzaaulia.multimodulesample.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Genre(
    val id: Int?,
    val name: String?
) : Parcelable