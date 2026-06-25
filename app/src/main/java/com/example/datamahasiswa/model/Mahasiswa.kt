package com.example.datamahasiswa.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Mahasiswa(
    @StringRes val nameResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val emailResourceId: Int
)
