package com.tandem.codingchallenge.ui

import androidx.paging.PagingSource
import com.tandem.codingchallenge.data.MyApi
import com.tandem.codingchallenge.data.models.CommunityData
import com.tandem.codingchallenge.data.models.CommunitiesResponse

class CommunitiesDataSource(private val api: MyApi) : PagingSource<Int, CommunityData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommunityData> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response: CommunitiesResponse = api.getCommunitiesData(nextPageNumber)

            LoadResult.Page(
                data = response.response,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < 20) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}