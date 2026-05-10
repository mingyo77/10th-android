package com.example.nikeappcompose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.example.nikeappcompose.R

@Composable
fun ProfileScreen() {
    val followingList = listOf(
        Product("George Bluth", "george.bluth@reqres.in"),
        Product("Janet Weaver", "janet.weaver@reqres.in"),
        Product("Emma Wong", "emma.wong@reqres.in"),
        Product("Eve Holt", "eve.holt@reqres.in"),
        Product("Charles Morris", "charles.morris@reqres.in"),
        Product("Tracey Ramos", "tracey.ramos@reqres.in")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(modifier = Modifier.height(60.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = "프로필",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "George Bluth",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "george.bluth@reqres.in",
                fontSize = 14.sp,
                color = androidx.compose.ui.graphics.Color.Gray,
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
        }

        items(followingList) { user ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = user.name,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = user.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}