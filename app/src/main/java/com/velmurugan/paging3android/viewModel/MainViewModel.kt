package com.velmurugan.paging3android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.velmurugan.paging3android.Model.Movie
import com.velmurugan.paging3android.Repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()

    fun getMovieList(): LiveData<PagingData<Movie>> {
        return mainRepository.getAllMovies().cachedIn(viewModelScope)
    }

}