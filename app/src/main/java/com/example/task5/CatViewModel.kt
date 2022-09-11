package com.example.task5

import androidx.lifecycle.*
import androidx.paging.*
import com.example.task5.data.Cat
import com.example.task5.paging3.CatPagingSource
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalArgumentException

class CatViewModelFactory(private val catApiImpl: CatApiImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CatViewModel(catApiImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class CatViewModel constructor(private val catApiImpl: CatApiImpl) : ViewModel() {

    val cats: Flow<PagingData<Cat>> = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 10, initialLoadSize = 10)
    ) {
        CatPagingSource(catApiImpl)
    }.flow
        .cachedIn(viewModelScope)

//    private val _items = MutableLiveData<PagingData<Cat>>()
//    val items: LiveData<PagingData<Cat>>
//    get() = _items
//
//    init {
//        viewModelScope.launch {
//            val pager: Pager<Int, Cat> = Pager<Int, Cat>(PagingConfig(pageSize = 5), pagingSourceFactory = {CatPagingSource(catApiImpl)})
//            items.cachedIn(items.value = pager, viewModelScope)
//        }
//    }








//    private val _items = MutableLiveData<List<Cat>>()
//    val items: LiveData<List<Cat>>
//        get() = _items
//
//    init {
//        viewModelScope.launch {
//            _items.value = CatApiImpl.getListOfCats()
//        }
//    }
}
