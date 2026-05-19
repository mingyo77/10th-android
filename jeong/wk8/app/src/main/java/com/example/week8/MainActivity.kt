package com.example.week8

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.week8.ui.NikeApp
import com.example.week8.ui.theme.Week8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 상태바·내비게이션 바 뒤까지 콘텐츠 확장 (wk5 WindowCompat.setDecorFitsSystemWindows(false) 대응)
        enableEdgeToEdge()

        // setContentView(R.layout.activity_main) 대신 Compose UI 트리를 Activity에 연결
        setContent {
            Week8Theme { // Material3 테마 적용
                NikeApp() // 하단 네비 + 화면 전환 + Lazy 리스트 화면 전체
            }
        }
    }
}
