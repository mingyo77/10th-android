package com.example.week7.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.R
import com.example.week7.ui.theme.AppColors

@Composable
fun ShopScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppColors.White)
            .padding(horizontal = 24.dp, vertical = 24.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.Top,
        ) {
            Column(modifier = Modifier.padding(end = 24.dp)) {
                Text(
                    text = stringResource(R.string.shop_category_all),
                    color = AppColors.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(6.dp))
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(2.dp)
                        .background(AppColors.Black),
                )
            }
            Text(
                text = stringResource(R.string.shop_category_tops),
                color = AppColors.Gray500,
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 24.dp),
            )
            Text(
                text = stringResource(R.string.shop_category_shoes),
                color = AppColors.Gray500,
                fontSize = 14.sp,
            )
        }
    }
}
