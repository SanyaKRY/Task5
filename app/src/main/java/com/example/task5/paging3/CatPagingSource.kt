package com.example.task5.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.task5.CatApiImpl
import com.example.task5.data.Cat

class CatPagingSource(val catApiImpl: CatApiImpl) : PagingSource<Int, Cat>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
        return try {

            // понять номер страницы, получить, номер страницы и получить pageSize(ск-ко элементов запрашивать)

            // если проперти key null, тогда следовательно запрашиваю первую страницу
            val page: Int = params.key ?: 1

            val pageSize: Int = params.loadSize

            // запрос к сервису
            var listOfCats: List<Cat> = catApiImpl.getListOfCats()

            // либо предыдущая страница зфпу - 1, либо null, если page = 1
            val prevKey = if (page == 1) null else page - 1

            // следующий ключ, если есть следующаая страница, то это просто текущая страница + 1,
            // если уже нет следующей страницы, значит меньше
            // если количество загруженных элементов меньше, чем количество запрашиваемых элементов, значит достигли конца
            // и больше ничего не будет и возвращаем null, в противном возвращаем следующую страницу
            val nextKey = if (listOfCats.size < pageSize) null else page + 1

            LoadResult.Page(listOfCats, prevKey, nextKey)
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
