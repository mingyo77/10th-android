package com.example.week7.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week7.R
import com.example.week7.ui.theme.AppColors
import java.util.Calendar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val dateLine = remember { koreanCalendarDateLine() }
    Column(
        modifier = modifier
            .background(AppColors.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(top = 50.dp, bottom = 24.dp),
    ) {
        Text(
            text = stringResource(R.string.home_discover),
            color = AppColors.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = dateLine,
            color = AppColors.Gray500,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp),
        )
        Spacer(modifier = Modifier.height(50.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(505.dp)
                .background(AppColors.Gray200),
        )
    }
}

private fun koreanCalendarDateLine(): String {
    val c = Calendar.getInstance()
    val month = c.get(Calendar.MONTH) + 1
    val day = c.get(Calendar.DAY_OF_MONTH)
    val dow = when (c.get(Calendar.DAY_OF_WEEK)) {
        Calendar.SUNDAY -> "일요일"
        Calendar.MONDAY -> "월요일"
        Calendar.TUESDAY -> "화요일"
        Calendar.WEDNESDAY -> "수요일"
        Calendar.THURSDAY -> "목요일"
        Calendar.FRIDAY -> "금요일"
        Calendar.SATURDAY -> "토요일"
        else -> ""
    }
    return "${month}월 ${day}일 $dow"
}
