package com.dirzaaulia.multimodulesample.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bts.id.domain.utils.pagingSucceeded
import com.dirzaaulia.multimodulesample.data.source.ServiceDataSource
import com.dirzaaulia.multimodulesample.domain.model.Movie

class MoviePagingSource(
    private val remoteDataSource: ServiceDataSource
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1

        return remoteDataSource.discoverMovie(page).pagingSucceeded { response ->
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page == response.totalPages) null else page + 1
            )
        }
    }

}