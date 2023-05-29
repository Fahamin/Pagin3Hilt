package com.velmurugan.paging3android.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.velmurugan.paging3android.Model.Movie
import com.velmurugan.paging3android.Retrofit.RetrofitService
import com.velmurugan.paging3android.Utils.Constance.API_KEY
import java.lang.Exception

class MoviePagingSource  (private val apiService: RetrofitService): PagingSource<Int, Movie>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val position = params.key ?: 1
            val response = apiService.getTopRatedMovies(API_KEY,"en-US",position)
            LoadResult.Page(data = response.body()!!.results, prevKey = if (position == 1) null else position - 1,
                nextKey = position + 1)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}
