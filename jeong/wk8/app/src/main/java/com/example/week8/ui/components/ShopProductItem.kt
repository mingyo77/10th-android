package com.example.week8.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week8.R
import com.example.week8.model.Product

// [item_shop_product.xml] + [ShopProductAdapter.ShopProductViewHolder.bind] 대체함.
// XML의 FrameLayout(이미지+하트) + LinearLayout(텍스트들) 구조를 Box + Column 으로 동일하게 재구성.

@Composable
fun ShopProductItem(
    product: Product,
    // wk5 Adapter 생성자: showWishlistButton — 구매하기 true, 위시리스트 false
    showWishlistButton: Boolean,
    onClickItem: () -> Unit,
    onClickWishlist: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 11.dp) // layout_marginHorizontal/ Bottom
            .clickable(onClick = onClickItem), // binding.root.setOnClickListener
    ) {
        // FrameLayout 대응: 이미지 위에 하트 버튼을 겹쳐 배치
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(product.imageResId),
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(colorResource(R.color.gray_100)),
            )
            if (showWishlistButton) {
                IconButton(
                    onClick = onClickWishlist, // binding.btnWishlist.setOnClickListener
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .size(28.dp),
                ) {
                    Icon(
                        painter = painterResource(
                            if (product.isWishlisted) {
                                R.drawable.ic_heart_filled
                            } else {
                                R.drawable.ic_heart
                            },
                        ),
                        contentDescription = "위시리스트",
                        tint = colorResource(R.color.black),
                    )
                }
            }
        }
        Text(
            text = product.name,
            color = colorResource(R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            modifier = Modifier.padding(top = 10.dp),
        )
        Text(
            text = product.subtitle,
            color = colorResource(R.color.gray_500),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp),
        )
        Text(
            text = product.colors,
            color = colorResource(R.color.gray_500),
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 2.dp),
        )
        Text(
            text = product.price,
            color = colorResource(R.color.black),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}
