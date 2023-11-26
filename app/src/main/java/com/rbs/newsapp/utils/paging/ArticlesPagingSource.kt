package com.rbs.newsapp.utils.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rbs.newsapp.BuildConfig
import com.rbs.newsapp.data.model.ArticlesItem
import com.rbs.newsapp.data.network.ApiService

class ArticlesPagingSource(private val query: String, private val apiService: ApiService) :
    PagingSource<Int, ArticlesItem>() {
    override fun getRefreshKey(state: PagingState<Int, ArticlesItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticlesItem> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getSearchArticles(query, position, params.loadSize, BuildConfig.KEY)
            LoadResult.Page(
                data = responseData.articles,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.articles.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}