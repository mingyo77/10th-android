package com.example.wk3.data

import java.io.Serializable

class Product(
    val name: String,
    val price: String,
    val image: Int,
    val explain: String = "",
    var isWish: Boolean = false)
    : Serializable// 데이터 통째로 넘길 수 있게 해주는 코드
{}