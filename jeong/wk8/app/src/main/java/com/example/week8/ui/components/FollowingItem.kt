package com.example.week8.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.week8.R

/**
 * wk5 [item_following.xml] + [FollowingAdapter.ViewHolder] UI.
 * UI-only: 회색 원형 placeholder (wk5 API 아바타 연동은 추후).
 */
@Composable
fun FollowingItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(88.dp)
            .clip(CircleShape)
            .background(colorResource(R.color.gray_200)),
    )
}
