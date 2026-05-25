package com.example.umc

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLightSystemBars(window)

        setContent {
            ShoppingApp()
        }
    }
}

private fun setLightSystemBars(window: Window) {
    window.statusBarColor = android.graphics.Color.WHITE
    window.navigationBarColor = android.graphics.Color.WHITE
    WindowCompat.getInsetsController(window, window.decorView).apply {
        isAppearanceLightStatusBars = true
        isAppearanceLightNavigationBars = true
    }
}

private enum class BottomTab(
    val label: String,
    val iconResId: Int
) {
    Home("홈", R.drawable.housesimple),
    Buy("구매하기", R.drawable.listmagnifyingglass),
    Wishlist("위시리스트", R.drawable.heartstraight),
    Cart("장바구니", R.drawable.bagsimple),
    Profile("프로필", R.drawable.user)
}

@Composable
private fun ShoppingApp() {
    var selectedTab by rememberSaveable { mutableStateOf(BottomTab.Home) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp)
                .statusBarsPadding()
        ) {
            when (selectedTab) {
                BottomTab.Home -> HomeScreen()
                BottomTab.Buy -> BuyScreen()
                BottomTab.Wishlist -> SimpleTextScreen("위시리스트 화면")
                BottomTab.Cart -> CartScreen(onOrderClick = { selectedTab = BottomTab.Buy })
                BottomTab.Profile -> SimpleTextScreen("프로필 화면")
            }
        }

        BottomNavigationBar(
            selectedTab = selectedTab,
            onTabClick = { selectedTab = it },
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.homelogo),
            contentDescription = "홈 로고",
            modifier = Modifier.width(220.dp)
        )
    }
}

@Composable
private fun BuyScreen() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf("전체", "Tops & T-Shirts", "sale")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ScreenTitle(title = "구매하기")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            tabs.forEachIndexed { index, title ->
                PurchaseTabItem(
                    title = title,
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index }
                )
            }
        }

        Spacer(modifier = Modifier.fillMaxSize())
    }
}

@Composable
private fun PurchaseTabItem(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val textColor = if (selected) Color(0xFF111111) else Color(0xFF8A8A8A)

    Column(
        modifier = Modifier
            .height(48.dp)
            .width(if (title == "Tops & T-Shirts") 116.dp else 64.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = title,
            color = textColor,
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
                .background(if (selected) Color(0xFF111111) else Color.Transparent)
        )
    }
}

@Composable
private fun CartScreen(onOrderClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        ScreenTitle(title = "장바구니")

        EmptyCartBox(
            modifier = Modifier.align(Alignment.Center)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 24.dp, end = 24.dp, bottom = 32.dp)
                .fillMaxWidth()
                .height(52.dp)
                .clip(RoundedCornerShape(26.dp))
                .background(Color.Black)
                .clickable(onClick = onOrderClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "주문하기",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
private fun EmptyCartBox(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.bagcircle),
            contentDescription = "장바구니 아이콘",
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "장바구니가 비어 있습니다.",
            color = Color(0xFF222222),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "제품을 추가하면 여기에 표시됩니다.",
            color = Color(0xFF888888),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun SimpleTextScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color(0xFF111111),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ScreenTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(start = 20.dp, top = 20.dp),
        color = Color(0xFF111111),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun BottomNavigationBar(
    selectedTab: BottomTab,
    onTabClick: (BottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(width = 0.5.dp, color = Color(0xFFE8E8E8))
            .height(64.dp)
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomTab.entries.forEach { tab ->
            BottomNavigationItem(
                tab = tab,
                selected = tab == selectedTab,
                onClick = { onTabClick(tab) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomNavigationItem(
    tab: BottomTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val color = if (selected) Color(0xFF111111) else Color(0xFF8A8A8A)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = tab.iconResId),
            contentDescription = tab.label,
            modifier = Modifier.size(22.dp),
            colorFilter = ColorFilter.tint(color)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = tab.label,
            color = color,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingAppPreview() {
    ShoppingApp()
}
