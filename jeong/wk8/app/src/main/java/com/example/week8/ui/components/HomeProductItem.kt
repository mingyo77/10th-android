package com.example.week8.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week8.R
import com.example.week8.model.Product

// [item_home_product.xml] + [HomeProductAdapter.HomeProductViewHolder.bind] 를 대체함.

@Composable
fun HomeProductItem(
    product: Product, // bind(item: Product) 에 넘기던 데이터
    onClick: () -> Unit, // 해당 아이템을 클릭하면 어떤 동작을 할지 컴포넌트 내부가 아닌 호출하는 쪽에서 정하도록
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .width(180.dp)
            .clickable(onClick = onClick)
            .padding(end = 10.dp),
    ) {
        Image(
            painter = painterResource(product.imageResId), // binding.ivProduct.setImageResource
            contentDescription = product.name, // 접근성용 설명 (스크린 리더)
            contentScale = ContentScale.Crop, // android:scaleType="centerCrop"
            modifier = Modifier
                .width(180.dp)
                .height(178.dp)
                .background(colorResource(R.color.gray_100)),
        )
        Text(
            text = product.name, // binding.tvName.text = item.name
            color = colorResource(R.color.black),
            fontSize = 16.sp,
            maxLines = 1, // android:maxLines="1"
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = product.price, // binding.tvPrice.text = item.price
            color = colorResource(R.color.black),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}
