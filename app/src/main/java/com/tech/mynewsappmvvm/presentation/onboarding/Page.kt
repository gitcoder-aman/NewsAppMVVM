package com.tech.mynewsappmvvm.presentation.onboarding

import androidx.annotation.DrawableRes
import com.tech.mynewsappmvvm.R

data class Page(
    val title : String,
    val description : String,
    @DrawableRes val image : Int
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipusm is simply dummly text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipusm is simply dummly text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipusm is simply dummly text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)
