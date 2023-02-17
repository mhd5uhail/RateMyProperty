package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.data.CategoryItem
import com.mhdsuhail.ratemyproperty.data.preview.CategoryItemPreviewProvider
import com.mhdsuhail.ratemyproperty.ui.theme.*
import kotlin.math.ceil


@Preview
@Composable
fun CategoriesPreview() {
    CategoryMatrix(items = CategoryItemPreviewProvider().values.toList(), 2)
}


fun generateCategoryMatrix(itemsPerRow: Int, items: List<CategoryItem>): List<List<CategoryItem>> {
    val matrix: MutableList<MutableList<CategoryItem>> = ArrayList()

    val noOfRows = ceil((items.size.toFloat() / itemsPerRow))
    var ptr = 0
    for (i in 0 until noOfRows.toInt()) {
        val row = ArrayList<CategoryItem>()
        while (ptr < items.size) {
            row.add(items[ptr])
            ++ptr
            if (ptr % itemsPerRow == 0)
                break
        }
        matrix.add(row)
    }
    return matrix
}

@Composable
fun CategoryMatrix(items: List<CategoryItem>, itemsPerRow: Int) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(240.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val matrix = generateCategoryMatrix(itemsPerRow, items)
        for (i in matrix.indices) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Center,
                content = {
                    for (j in 0 until matrix[i].size) {
                        if (matrix[i].getOrNull(j) != null)
                            CategoryItemCard(item = matrix[i][j])
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun CategoryCardPreview(@PreviewParameter(CategoryItemPreviewProvider::class) category: CategoryItem) {
    RateMyPropertyTheme() {
        CategoryItemCard(item = category)
    }
}


@Composable
fun CategoryItemCard(
    modifier: Modifier = Modifier, item: CategoryItem
) {
    Card(modifier = modifier.padding(5.dp), shape = RoundedCornerShape(15.dp)) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .height(60.dp)
                .width(180.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f).background(Color.LightGray.copy(0.5f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        painter = painterResource(id = item.imageResourceId),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Realtor Contact Picture"
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(item.tagColor)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = item.textColor,
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}