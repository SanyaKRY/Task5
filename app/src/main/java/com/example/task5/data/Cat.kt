package com.example.task5.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cat(
    val id: String?,
    val imageUrl: String?
) : Parcelable
