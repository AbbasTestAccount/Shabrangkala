package com.example.shabrangkala.model.data.repository.searchRepository

import com.example.shabrangkala.model.data.Search
import com.example.shabrangkala.model.db.SearchedDao

class SearchRepositoryImp(private val searchedDao: SearchedDao) : SearchRepository {
    override suspend fun getAllSearches(): List<Search> {
        return searchedDao.getAllSearch()
    }

    override suspend fun addSearchToList(search: Search) {
        searchedDao.addSearchToList(search)
    }

    override suspend fun deleteSearchItem(search: Search) {
        searchedDao.deleteSearchItem(search)
    }
}