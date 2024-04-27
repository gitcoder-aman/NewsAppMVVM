package com.tech.mynewsappmvvm.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.tech.mynewsappmvvm.R
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.presentation.Dimens.MediumPadding1
import com.tech.mynewsappmvvm.presentation.common.ArticlesList
import com.tech.mynewsappmvvm.presentation.nvgraph.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
    ) {
        Text(
            text = "Bookmark", style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_title)
            )
        )
        Spacer(modifier = Modifier.height(
            MediumPadding1
        ))
        ArticlesList(articles = state.articles) {
            navigateToDetails(it)
        }
    }
}