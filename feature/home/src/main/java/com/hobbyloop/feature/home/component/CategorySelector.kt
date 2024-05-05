package com.hobbyloop.feature.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hobbyloop.feature.home.model.CategorySelectorItemUiModel
import com.hobbyloop.feature.home.model.TicketCategory
import com.hobbyloop.member.ui.theme.HobbyLoopTypo
import com.hobbyloop.ui.R

@Composable
fun CategorySelector(
    modifier: Modifier = Modifier,
    items: List<CategorySelectorItemUiModel> = listOf(),
    itemSpacing: Dp = 24.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    LazyRow(
        modifier = modifier
            .background(color = Color.White)
            .padding(contentPadding),
        horizontalArrangement = Arrangement.spacedBy(itemSpacing)
    ) {
        items(items) { item ->
            CategoryItem(item = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCategorySelector() {
    CategorySelector()
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    item: CategorySelectorItemUiModel
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier,
            painter = painterResource(id = item.icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = item.category.description,
            style = HobbyLoopTypo.body14,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreViewCategoryItem() {
    CategoryItem(
        item = CategorySelectorItemUiModel(category = TicketCategory.PT, icon = R.drawable.pictogram_pt)
    )
}