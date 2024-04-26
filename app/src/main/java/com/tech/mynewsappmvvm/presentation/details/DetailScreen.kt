package com.tech.mynewsappmvvm.presentation.details

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tech.mynewsappmvvm.R
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.domain.model.Source
import com.tech.mynewsappmvvm.presentation.Dimens.ArticleImageHeight
import com.tech.mynewsappmvvm.presentation.Dimens.MediumPadding1
import com.tech.mynewsappmvvm.presentation.details.components.DetailTopBar
import com.tech.mynewsappmvvm.ui.theme.MyNewsAppMVVMTheme

@Composable
fun DetailScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigationUp: () -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plan"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookMarkClick = {
                event(DetailsEvent.SaveArticle)
            },
            onBackClick = {
                navigationUp()
            })
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailScreenPreview() {
    MyNewsAppMVVMTheme {
        Box(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        ) {
            DetailScreen(article = Article(
                author = "Joel Khalili",
                content = "On March 12, Russian-Swedish national Roman Sterlingov was found guilty of money laundering conspiracy and other violations by a federal jury in Washington, DC, for having operated Bitcoin Fog, a ser… [+3654 chars]",
                description = "A jury convicted Roman Sterlingov of money laundering this month. His defense team says it will appeal, saying the crypto-tracing technique at the heart of the case is “pseudoscience.",
                publishedAt = "2024-03-27T11:00:00Z",
                source = Source(
                    id = "",
                    name = "ReadWrite"
                ),
                title = "The Science of Crypto Forensics Survives a Court Battle—for Now",
                url = "https://www.wired.com/story/the-science-of-crypto-forensics-court-battle/",
                urlToImage = "https://media.wired.com/photos/6603759fd3a75d0aa76d16ab/191:100/w_1280,c_limit/business_crypto_tracing_forensics_trial.jpg"
            ), event = {}) {

            }
        }
    }
}