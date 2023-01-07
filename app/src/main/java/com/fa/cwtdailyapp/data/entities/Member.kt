package com.fa.cwtdailyapp.data.entities

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.compose.ui.graphics.Color
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Member(
    var name: String = "",
    var avatar: Int? = null,
    var durationTalking: Int = 0,
    var isAttending:Boolean=false
):Parcelable