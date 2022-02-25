package com.tandem.codingchallenge.data.models

data class CommunityData(
    val __v: Int,
    val id: Int,
    val topic: String,
    val firstName: String,
    val pictureUrl: String,
    val natives: ArrayList<String>,
    val learns: ArrayList<String>,
    val referenceCnt: Int
)