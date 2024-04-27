package com.tech.mynewsappmvvm.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.preferKeepClear
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.tech.mynewsappmvvm.R
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.presentation.Dimens.MediumPadding1
import com.tech.mynewsappmvvm.presentation.common.ArticlesList
import com.tech.mynewsappmvvm.presentation.common.SearchBar
import com.tech.mynewsappmvvm.presentation.nvgraph.Route

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 0) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5 ") { it.title }
            } else {
                ""
            }
        }
    }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo), contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .height(30.dp)
                    .padding(horizontal = MediumPadding1)
            )

            Spacer(modifier = Modifier.height(MediumPadding1))

            SearchBar(
                modifier = Modifier.padding(horizontal = MediumPadding1),
                text = "",
                readOnly = false,
                onValueChange = {},
                onClick = {
                   navigateToSearch()
                },
                onSearch = {}
            )
            Spacer(modifier = Modifier.height(MediumPadding1))
            Text(
                text = titles,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = MediumPadding1)
                    .basicMarquee(),
                fontSize = 12.sp,
                color = colorResource(id = R.color.placeholder)
            )
            Spacer(modifier = Modifier.height(MediumPadding1))

            ArticlesList(articles = articles, modifier = Modifier.padding(MediumPadding1)) {
                navigateToDetails(it)
            }
        }
}
