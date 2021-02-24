package com.gallotti.appcafebarrestaurante.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gallotti.appcafebarrestaurante.network.model.Store
import com.gallotti.appcafebarrestaurante.network.model.StoreDetail
import com.gallotti.appcafebarrestaurante.repository.StoreRepositoryContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailStoreViewModel(
    private val storeRepository: StoreRepositoryContract
): ViewModel() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    fun getDetailStore(store: Store) {
        _state.postValue(ScreenState.Loading)
        scope.launch {
            val response = storeRepository.getDetailStore(store.id)
            if (response.isSuccessful) {
                _state.postValue(
                    ScreenState.OnGetDetailStore(
                        response.body()!!
                    )
                )
            } else {
                val message = response.errorBody().toString()
                _state.postValue(
                    ScreenState.OnGeDetailStoreError(
                        message
                    )
                )
            }
        }
    }

    sealed class ScreenState{
        object Loading : ScreenState()

        data class OnGetDetailStore(val detailStore: StoreDetail) : ScreenState()
        data class OnGeDetailStoreError(val msg: String) : ScreenState()
    }

}