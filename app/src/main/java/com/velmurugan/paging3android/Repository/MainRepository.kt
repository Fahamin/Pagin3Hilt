package com.velmurugan.paging3android.Repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.velmurugan.paging3android.Model.Movie
import com.velmurugan.paging3android.Paging.MoviePagingSource
import com.velmurugan.paging3android.Retrofit.RetrofitService
import com.velmurugan.paging3android.Utils.Constance.NETWORK_PAGE_SIZE
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies(): LiveData<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 2
            ),
            pagingSourceFactory = {
                MoviePagingSource(retrofitService)
            }, initialKey = 1
        ).liveData
    }

}