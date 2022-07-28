package com.dededev.moviehero.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.dededev.moviehero.core.data.source.remote.network.ApiResponse
import com.dededev.moviehero.core.utils.AppExecutors

abstract class NetworkBoundResource<ResultType, RequestType>(private val appExecutors: AppExecutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.Loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.Success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success ->
                    appExecutors.diskIO().execute {
                        saveCallResult(response.data)
                        appExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.Success(newData)
                            }
                        }
                    }
                is ApiResponse.Empty ->
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource.Success(newData)
                        }
                    }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDB()) { newData ->
                            result.value = Resource.Success(newData)
                        }
                    }
                }

            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}