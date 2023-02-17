package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.CategoryItem
import com.mhdsuhail.ratemyproperty.ui.theme.Blue100

class CategoryItemPreviewProvider : PreviewParameterProvider<CategoryItem> {
    override val values = sequenceOf(
        CategoryItem("Houses", R.drawable.home, Color.White, Blue100),
        CategoryItem("Condos", R.drawable.condo, Color.White, Blue100),
        CategoryItem("Storage", R.drawable.shelves, Color.White, Blue100),
        CategoryItem("Commercial", R.drawable.commercial, Color.White, Blue100),
        )
}