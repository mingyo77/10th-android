package com.example.nikeappcompose.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.nikeappcompose.ApiClient
import com.example.nikeappcompose.UserData
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen() {
    var user by remember { mutableStateOf<UserData?>(null) }
    var userList by remember { mutableStateOf<List<UserData>>(emptyList()) }
    val apiKey = "reqres_df73b9dd436f45ee8ae8925f9dd0f2aa"

    LaunchedEffect(Unit) {
        try {
            val userResponse = ApiClient.userService.getUser(apiKey)
            if (userResponse.isSuccessful) {
                user = userResponse.body()?.data
            }

            val listResponse = ApiClient.userService.getUserList(apiKey)
            if (listResponse.isSuccessful) {
                userList = listResponse.body()?.data ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        AsyncImage(
            model = user?.avatar,
            contentDescription = "프로필",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${user?.firstName ?: ""} ${user?.lastName ?: ""}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Text(
            text = user?.email ?: "",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Text(
            text = "팔로잉 목록",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
        )

        if (userList.isNotEmpty()) {
            val pagerState = rememberPagerState(pageCount = { userList.size })

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                val followUser = userList[page]
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = followUser.avatar,
                        contentDescription = followUser.firstName,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "${followUser.firstName} ${followUser.lastName}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // 페이지 인디케이터
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(userList.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    Box(
                        modifier = Modifier
                            .size(if (isSelected) 10.dp else 8.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) Color.Black else Color.Gray
                            )
                    )
                }
            }
        }
    }
}